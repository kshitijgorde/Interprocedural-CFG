// 
// Decompiled by Procyon v0.5.30
// 

package com.fluendo.jst;

import java.util.Enumeration;
import java.util.Vector;

public abstract class Element extends Object
{
    public static final int FLAG_IS_SINK = 32;
    public static final int ELEMENT_FLAG_LAST = 1048576;
    protected Vector pads;
    protected java.lang.Object stateLock;
    private Vector padListeners;
    protected Clock clock;
    protected Bus bus;
    protected long baseTime;
    public static final int NONE = 0;
    public static final int STOP = 1;
    public static final int PAUSE = 2;
    public static final int PLAY = 3;
    private static final int SHIFT = 4;
    private static final int MASK = 15;
    public static final int STOP_PAUSE = 18;
    public static final int PAUSE_PLAY = 35;
    public static final int PLAY_PAUSE = 50;
    public static final int PAUSE_STOP = 33;
    public static final int FAILURE = 0;
    public static final int SUCCESS = 1;
    public static final int ASYNC = 2;
    public static final int NO_PREROLL = 3;
    protected int currentState;
    protected int nextState;
    protected int pendingState;
    protected int lastReturn;
    private Object objClass;
    
    public Object getObjClass() {
        return this.objClass;
    }
    
    public void setObjClass(final Object objClass) {
        this.objClass = objClass;
    }
    
    public String getMime() {
        return null;
    }
    
    public abstract String getFactoryName();
    
    public int typeFind(final byte[] data, final int offset, final int length) {
        return -1;
    }
    
    public Element() {
        this(null);
    }
    
    public Element(final String name) {
        super(name);
        this.pads = new Vector();
        this.stateLock = new java.lang.Object();
        this.padListeners = new Vector();
        this.currentState = 1;
        this.nextState = 0;
        this.pendingState = 0;
        this.lastReturn = 1;
    }
    
    public String toString() {
        return "Element: [" + this.getName() + "]";
    }
    
    public synchronized void setClock(final Clock newClock) {
        this.clock = newClock;
    }
    
    public synchronized Clock getClock() {
        return this.clock;
    }
    
    public synchronized void setBus(final Bus newBus) {
        this.bus = newBus;
    }
    
    public synchronized Bus getBus() {
        return this.bus;
    }
    
    public synchronized void addPadListener(final PadListener listener) {
        this.padListeners.addElement(listener);
    }
    
    public synchronized void removePadListener(final PadListener listener) {
        this.padListeners.removeElement(listener);
    }
    
    private synchronized void doPadListeners(final int method, final Pad pad) {
        final Enumeration e = this.padListeners.elements();
        while (e.hasMoreElements()) {
            final PadListener listener = e.nextElement();
            switch (method) {
                case 0: {
                    listener.padAdded(pad);
                    continue;
                }
                case 1: {
                    listener.padRemoved(pad);
                    continue;
                }
                case 2: {
                    listener.noMorePads();
                    continue;
                }
            }
        }
    }
    
    public synchronized Pad getPad(final String name) {
        final Enumeration e = this.pads.elements();
        while (e.hasMoreElements()) {
            final Pad pad = e.nextElement();
            if (name.equals(pad.getName())) {
                return pad;
            }
        }
        return null;
    }
    
    public synchronized boolean addPad(final Pad newPad) {
        if (!newPad.setParent(this)) {
            return false;
        }
        this.pads.addElement(newPad);
        this.doPadListeners(0, newPad);
        return true;
    }
    
    public synchronized boolean removePad(final Pad aPad) {
        if (aPad.getParent() != this) {
            return false;
        }
        aPad.unParent();
        this.pads.removeElement(aPad);
        this.doPadListeners(1, aPad);
        return true;
    }
    
    public synchronized void noMorePads() {
        this.doPadListeners(2, null);
    }
    
    public Enumeration enumPads() {
        return this.pads.elements();
    }
    
    public void postMessage(final Message message) {
        final Bus myBus;
        synchronized (this) {
            myBus = this.bus;
        }
        if (myBus != null) {
            myBus.post(message);
        }
    }
    
    public synchronized int getState(final int[] resState, final int[] resPending, final long timeout) {
        if (this.lastReturn == 2 && this.pendingState != 0) {
            long t;
            if (timeout == 0L) {
                t = 0L;
            }
            else if (timeout < 1000L) {
                t = 1L;
            }
            else {
                t = timeout / 1000L;
            }
            try {
                this.wait(t);
            }
            catch (InterruptedException ex) {}
        }
        if (resState != null) {
            resState[0] = this.currentState;
        }
        if (resPending != null) {
            resPending[0] = this.pendingState;
        }
        return this.lastReturn;
    }
    
    private boolean padsActivate(final boolean active) {
        final int mode = active ? 1 : 0;
        boolean res = true;
        final Enumeration e = this.pads.elements();
        while (e.hasMoreElements()) {
            final Pad pad = e.nextElement();
            res &= pad.activate(mode);
            if (!res) {
                return res;
            }
        }
        return res;
    }
    
    public int getStateNext(final int current, final int pending) {
        int sign = pending - current;
        if (sign > 0) {
            sign = 1;
        }
        else if (sign < 0) {
            sign = -1;
        }
        else {
            sign = 0;
        }
        return current + sign;
    }
    
    public int getTransition(final int current, final int next) {
        return current << 4 | next;
    }
    
    public int getTransitionCurrent(final int transition) {
        return transition >> 4;
    }
    
    public int getTransitionNext(final int transition) {
        return transition & 0xF;
    }
    
    public int continueState(int result) {
        Message message = null;
        int transition = 0;
        synchronized (this) {
            final int oldRet = this.lastReturn;
            this.lastReturn = result;
            final int pending = this.pendingState;
            if (pending == 0) {
                return result;
            }
            final int oldState = this.currentState;
            final int nextState;
            final int oldNext = nextState = this.nextState;
            this.currentState = nextState;
            final int current = nextState;
            if (pending == current) {
                this.pendingState = 0;
                this.nextState = 0;
                transition = 0;
                if (oldState != oldNext || oldRet == 2) {
                    message = Message.newStateChanged(this, oldState, oldNext, pending);
                }
            }
            else {
                final int next = this.getStateNext(current, pending);
                transition = this.getTransition(current, next);
                this.nextState = next;
                this.lastReturn = 2;
                message = Message.newStateChanged(this, oldState, oldNext, pending);
            }
        }
        if (message != null) {
            this.postMessage(message);
        }
        if (transition != 0) {
            result = this.doChangeState(transition);
        }
        else {
            synchronized (this) {
                this.notifyAll();
            }
        }
        return result;
    }
    
    public synchronized void abortState() {
        if (this.pendingState != 0 && this.lastReturn != 0) {
            this.lastReturn = 0;
            this.notifyAll();
        }
    }
    
    public void lostState() {
        boolean post = false;
        int current = 0;
        synchronized (this) {
            if (this.pendingState == 0 && this.lastReturn != 0) {
                current = this.currentState;
                final int currentState = this.currentState;
                this.nextState = currentState;
                this.pendingState = currentState;
                this.lastReturn = 2;
                post = true;
            }
        }
        if (post) {
            this.postMessage(Message.newStateChanged(this, current, current, current));
            this.postMessage(Message.newStateDirty(this));
        }
    }
    
    protected int changeState(final int transition) {
        final int current = this.getTransitionCurrent(transition);
        final int next = this.getTransitionNext(transition);
        if (next == 0 || current == next) {
            return this.lastReturn;
        }
        boolean res = false;
        switch (transition) {
            case 18: {
                res = this.padsActivate(true);
                break;
            }
            case 35: {
                res = true;
                break;
            }
            case 50: {
                res = true;
                break;
            }
            case 33: {
                res = this.padsActivate(false);
                break;
            }
            default: {
                res = false;
                break;
            }
        }
        if (res) {
            return 1;
        }
        return 0;
    }
    
    private int doChangeState(final int transition) {
        final int current = this.getTransitionCurrent(transition);
        final int next = this.getTransitionNext(transition);
        int result = this.changeState(transition);
        switch (result) {
            case 0: {
                this.abortState();
                break;
            }
            case 1:
            case 3: {
                result = this.continueState(result);
                break;
            }
            case 2: {
                if (current < next) {
                    synchronized (this) {
                        if (this.pendingState != 0) {
                            this.lastReturn = result;
                        }
                    }
                    break;
                }
                result = this.continueState(1);
                break;
            }
        }
        return result;
    }
    
    public final int setState(final int newState) {
        final int result;
        synchronized (this.stateLock) {
            final int transition;
            synchronized (this) {
                if (this.lastReturn == 0) {
                    this.nextState = 0;
                    this.pendingState = 0;
                    this.lastReturn = 1;
                }
                final int oldPending = this.pendingState;
                this.pendingState = newState;
                if (oldPending != 0) {
                    if (oldPending <= newState) {
                        return this.lastReturn = 2;
                    }
                    if (this.nextState == newState) {
                        return this.lastReturn = 2;
                    }
                    if (this.nextState > newState && this.lastReturn == 2) {
                        this.currentState = this.nextState;
                    }
                }
                this.nextState = this.getStateNext(this.currentState, newState);
                transition = this.getTransition(this.currentState, this.nextState);
            }
            result = this.doChangeState(transition);
        }
        return result;
    }
    
    public boolean sendEvent(final Event event) {
        return false;
    }
    
    public boolean query(final Query query) {
        return false;
    }
}

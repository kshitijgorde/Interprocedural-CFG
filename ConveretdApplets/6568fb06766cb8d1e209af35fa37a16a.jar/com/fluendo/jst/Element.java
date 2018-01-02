// 
// Decompiled by Procyon v0.5.30
// 

package com.fluendo.jst;

import java.util.Enumeration;
import com.fluendo.utils.Debug;
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
    protected static String[] stateNames;
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
    public static String[] stateReturnNames;
    protected int currentState;
    protected int nextState;
    protected int pendingState;
    protected int lastReturn;
    
    public static String getStateName(final int n) {
        return Element.stateNames[n];
    }
    
    public static String getStateReturnName(final int n) {
        return Element.stateReturnNames[n];
    }
    
    public String getMime() {
        return null;
    }
    
    public abstract String getFactoryName();
    
    public int typeFind(final byte[] array, final int n, final int n2) {
        return -1;
    }
    
    public Element() {
        this(null);
    }
    
    public Element(final String s) {
        super(s);
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
    
    public synchronized void setClock(final Clock clock) {
        Debug.debug(this + ".setClock(" + clock + ")");
        this.clock = clock;
    }
    
    public synchronized Clock getClock() {
        return this.clock;
    }
    
    public synchronized void setBus(final Bus bus) {
        this.bus = bus;
    }
    
    public synchronized Bus getBus() {
        return this.bus;
    }
    
    public synchronized void addPadListener(final PadListener padListener) {
        this.padListeners.addElement(padListener);
    }
    
    public synchronized void removePadListener(final PadListener padListener) {
        this.padListeners.removeElement(padListener);
    }
    
    private synchronized void doPadListeners(final int n, final Pad pad) {
        final Enumeration<PadListener> elements = this.padListeners.elements();
        while (elements.hasMoreElements()) {
            final PadListener padListener = elements.nextElement();
            switch (n) {
                case 0: {
                    padListener.padAdded(pad);
                    continue;
                }
                case 1: {
                    padListener.padRemoved(pad);
                    continue;
                }
                case 2: {
                    padListener.noMorePads();
                    continue;
                }
            }
        }
    }
    
    public synchronized Pad getPad(final String s) {
        final Enumeration<Pad> elements = this.pads.elements();
        while (elements.hasMoreElements()) {
            final Pad pad = elements.nextElement();
            if (s.equals(pad.getName())) {
                return pad;
            }
        }
        return null;
    }
    
    public synchronized boolean addPad(final Pad pad) {
        if (!pad.setParent(this)) {
            return false;
        }
        this.pads.addElement(pad);
        this.doPadListeners(0, pad);
        return true;
    }
    
    public synchronized boolean removePad(final Pad pad) {
        if (pad.getParent() != this) {
            return false;
        }
        pad.unParent();
        this.pads.removeElement(pad);
        this.doPadListeners(1, pad);
        return true;
    }
    
    public synchronized void noMorePads() {
        this.doPadListeners(2, null);
    }
    
    public Enumeration enumPads() {
        return this.pads.elements();
    }
    
    public void postMessage(final Message message) {
        final Bus bus;
        synchronized (this) {
            bus = this.bus;
        }
        if (bus != null) {
            bus.post(message);
        }
    }
    
    public synchronized int getState(final int[] array, final int[] array2, final long n) {
        if (this.lastReturn == 2 && this.pendingState != 0) {
            long n2;
            if (n == 0L) {
                n2 = 0L;
            }
            else if (n < 1000L) {
                n2 = 1L;
            }
            else {
                n2 = n / 1000L;
            }
            try {
                this.wait(n2);
            }
            catch (InterruptedException ex) {}
        }
        if (array != null) {
            array[0] = this.currentState;
        }
        if (array2 != null) {
            array2[0] = this.pendingState;
        }
        return this.lastReturn;
    }
    
    private boolean padsActivate(final boolean b) {
        final int n = b ? 1 : 0;
        boolean b2 = true;
        final Enumeration<Pad> elements = this.pads.elements();
        while (elements.hasMoreElements()) {
            b2 &= elements.nextElement().activate(n);
            if (!b2) {
                return b2;
            }
        }
        return b2;
    }
    
    public int getStateNext(final int n, final int n2) {
        final int n3 = n2 - n;
        int n4;
        if (n3 > 0) {
            n4 = 1;
        }
        else if (n3 < 0) {
            n4 = -1;
        }
        else {
            n4 = 0;
        }
        return n + n4;
    }
    
    public int getTransition(final int n, final int n2) {
        return n << 4 | n2;
    }
    
    public int getTransitionCurrent(final int n) {
        return n >> 4;
    }
    
    public int getTransitionNext(final int n) {
        return n & 0xF;
    }
    
    public int continueState(int doChangeState) {
        Message message = null;
        int transition = 0;
        synchronized (this) {
            final int lastReturn = this.lastReturn;
            this.lastReturn = doChangeState;
            final int pendingState = this.pendingState;
            if (pendingState == 0) {
                return doChangeState;
            }
            final int currentState = this.currentState;
            final int nextState;
            final int n = nextState = this.nextState;
            this.currentState = nextState;
            final int n2 = nextState;
            if (pendingState == n2) {
                this.pendingState = 0;
                this.nextState = 0;
                transition = 0;
                if (currentState != n || lastReturn == 2) {
                    message = Message.newStateChanged(this, currentState, n, pendingState);
                }
            }
            else {
                final int stateNext = this.getStateNext(n2, pendingState);
                transition = this.getTransition(n2, stateNext);
                this.nextState = stateNext;
                this.lastReturn = 2;
                message = Message.newStateChanged(this, currentState, n, pendingState);
            }
        }
        if (message != null) {
            this.postMessage(message);
        }
        if (transition != 0) {
            doChangeState = this.doChangeState(transition);
        }
        else {
            synchronized (this) {
                this.notifyAll();
            }
        }
        return doChangeState;
    }
    
    public synchronized void abortState() {
        if (this.pendingState != 0 && this.lastReturn != 0) {
            this.lastReturn = 0;
            this.notifyAll();
        }
    }
    
    public void lostState() {
        boolean b = false;
        int currentState = 0;
        synchronized (this) {
            if (this.pendingState == 0 && this.lastReturn != 0) {
                currentState = this.currentState;
                final int currentState2 = this.currentState;
                this.nextState = currentState2;
                this.pendingState = currentState2;
                this.lastReturn = 2;
                b = true;
            }
        }
        if (b) {
            this.postMessage(Message.newStateChanged(this, currentState, currentState, currentState));
            this.postMessage(Message.newStateDirty(this));
        }
    }
    
    protected int changeState(final int n) {
        final int transitionCurrent = this.getTransitionCurrent(n);
        final int transitionNext = this.getTransitionNext(n);
        if (transitionNext == 0 || transitionCurrent == transitionNext) {
            return this.lastReturn;
        }
        boolean b = false;
        switch (n) {
            case 18: {
                b = this.padsActivate(true);
                break;
            }
            case 35: {
                b = true;
                break;
            }
            case 50: {
                b = true;
                break;
            }
            case 33: {
                b = this.padsActivate(false);
                break;
            }
            default: {
                b = false;
                break;
            }
        }
        if (b) {
            return 1;
        }
        return 0;
    }
    
    private int doChangeState(final int n) {
        final int transitionCurrent = this.getTransitionCurrent(n);
        final int transitionNext = this.getTransitionNext(n);
        int lastReturn = this.changeState(n);
        switch (lastReturn) {
            case 0: {
                this.abortState();
                break;
            }
            case 1:
            case 3: {
                lastReturn = this.continueState(lastReturn);
                break;
            }
            case 2: {
                if (transitionCurrent < transitionNext) {
                    synchronized (this) {
                        if (this.pendingState != 0) {
                            this.lastReturn = lastReturn;
                        }
                    }
                    break;
                }
                lastReturn = this.continueState(1);
                break;
            }
        }
        return lastReturn;
    }
    
    public final int setState(final int pendingState) {
        final int doChangeState;
        synchronized (this.stateLock) {
            final int transition;
            synchronized (this) {
                if (this.lastReturn == 0) {
                    this.nextState = 0;
                    this.pendingState = 0;
                    this.lastReturn = 1;
                }
                final int pendingState2 = this.pendingState;
                this.pendingState = pendingState;
                if (pendingState2 != 0) {
                    if (pendingState2 <= pendingState) {
                        return this.lastReturn = 2;
                    }
                    if (this.nextState == pendingState) {
                        return this.lastReturn = 2;
                    }
                    if (this.nextState > pendingState && this.lastReturn == 2) {
                        this.currentState = this.nextState;
                    }
                }
                this.nextState = this.getStateNext(this.currentState, pendingState);
                transition = this.getTransition(this.currentState, this.nextState);
            }
            doChangeState = this.doChangeState(transition);
        }
        return doChangeState;
    }
    
    public boolean sendEvent(final Event event) {
        return false;
    }
    
    public boolean query(final Query query) {
        return false;
    }
    
    public Pad requestSinkPad(final Pad pad) {
        return null;
    }
    
    static {
        Element.stateNames = new String[] { "none", "stop", "pause", "play" };
        Element.stateReturnNames = new String[] { "FAILURE", "SUCCESS", "ASYNC", "NO_PREROLL" };
    }
}

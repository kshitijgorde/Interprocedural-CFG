// 
// Decompiled by Procyon v0.5.30
// 

package com.fluendo.jst;

import com.fluendo.utils.Debug;

public abstract class Sink extends Element
{
    private java.lang.Object prerollLock;
    private boolean isEOS;
    private boolean flushing;
    private boolean havePreroll;
    private boolean needPreroll;
    private Clock.ClockID clockID;
    protected boolean discont;
    protected long segStart;
    protected long segStop;
    protected long segPosition;
    protected long pauseTime;
    protected long lastTime;
    protected long maxLateness;
    protected Pad sinkpad;
    
    protected int preroll(final Buffer buffer) {
        return 0;
    }
    
    protected boolean doEvent(final Event event) {
        return true;
    }
    
    protected WaitStatus doSync(long n) {
        WaitStatus waitID = new WaitStatus();
        Clock.ClockID clockID = null;
        synchronized (this) {
            if (this.flushing) {
                waitID.status = 2;
                return waitID;
            }
            if (n == -1L) {
                waitID.status = 0;
                return waitID;
            }
            n = n - this.segStart + super.baseTime;
            if (super.clock != null) {
                final Clock.ClockID singleShotID = super.clock.newSingleShotID(n);
                this.clockID = singleShotID;
                clockID = singleShotID;
            }
        }
        if (clockID != null) {
            waitID = clockID.waitID();
        }
        else {
            waitID.status = 0;
        }
        synchronized (this) {
            this.clockID = null;
        }
        return waitID;
    }
    
    protected boolean setCapsFunc(final Caps caps) {
        return true;
    }
    
    protected int render(final Buffer buffer) {
        return 0;
    }
    
    public Sink() {
        this.prerollLock = new java.lang.Object();
        this.segStart = 0L;
        this.maxLateness = -1L;
        this.addPad(this.sinkpad = new Pad(2, "sink") {
            private int finishPreroll(final Buffer buffer) {
                synchronized (Sink.this.prerollLock) {
                    int preroll = 0;
                    final Sink sink = (Sink)super.parent;
                    if (this.isFlushing()) {
                        return -2;
                    }
                    if (Sink.this.needPreroll) {
                        Sink.this.havePreroll = true;
                        try {
                            preroll = Sink.this.preroll(buffer);
                        }
                        catch (Throwable t) {
                            Sink.this.postMessage(Message.newError(this, "preroll exception: " + t.getMessage()));
                            return -5;
                        }
                        boolean b = false;
                        boolean b2 = false;
                        final int currentState;
                        final int nextState;
                        int pendingState;
                        final int currentState2;
                        synchronized (sink) {
                            currentState = Sink.this.currentState;
                            nextState = Sink.this.nextState;
                            currentState2 = (pendingState = Sink.this.pendingState);
                            Label_0326: {
                                switch (currentState2) {
                                    case 3: {
                                        Sink.this.needPreroll = false;
                                        b2 = true;
                                        if (currentState == 1) {
                                            b = true;
                                            break;
                                        }
                                        break;
                                    }
                                    case 2: {
                                        Sink.this.needPreroll = true;
                                        b = true;
                                        pendingState = 0;
                                        break;
                                    }
                                    case 1: {
                                        Sink.this.havePreroll = false;
                                        Sink.this.needPreroll = false;
                                        return -2;
                                    }
                                    case 0: {
                                        switch (currentState) {
                                            case 3: {
                                                Sink.this.needPreroll = false;
                                                break Label_0326;
                                            }
                                            case 2: {
                                                Sink.this.needPreroll = true;
                                                break Label_0326;
                                            }
                                            default: {
                                                Sink.this.havePreroll = false;
                                                Sink.this.needPreroll = false;
                                                return -2;
                                            }
                                        }
                                        break;
                                    }
                                }
                            }
                            if (currentState2 != 0) {
                                Sink.this.currentState = currentState2;
                                Sink.this.nextState = 0;
                                Sink.this.pendingState = 0;
                                Sink.this.lastReturn = 1;
                            }
                        }
                        if (b) {
                            Sink.this.postMessage(Message.newStateChanged(this, currentState, nextState, pendingState));
                        }
                        if (b2) {
                            Sink.this.postMessage(Message.newStateChanged(this, nextState, currentState2, 0));
                        }
                        if (b || b2) {
                            Sink.this.postMessage(Message.newStateDirty(this));
                        }
                        synchronized (sink) {
                            sink.notifyAll();
                        }
                        if (Sink.this.needPreroll) {
                            Sink.this.needPreroll = false;
                            try {
                                Sink.this.prerollLock.wait();
                            }
                            catch (InterruptedException ex) {}
                            Sink.this.havePreroll = false;
                        }
                    }
                    if (this.isFlushing()) {
                        return -2;
                    }
                    return preroll;
                }
            }
            
            protected boolean eventFunc(final Event event) {
                final Sink sink = (Sink)super.parent;
                Sink.this.doEvent(event);
                switch (event.getType()) {
                    case 1: {
                        synchronized (sink) {
                            sink.flushing = true;
                            if (Sink.this.clockID != null) {
                                Sink.this.clockID.unschedule();
                            }
                        }
                        synchronized (Sink.this.prerollLock) {
                            sink.isEOS = false;
                            Sink.this.needPreroll = true;
                            Sink.this.prerollLock.notify();
                            Sink.this.havePreroll = false;
                        }
                        synchronized (super.streamLock) {
                            Debug.debug(this + " synced " + Sink.this.havePreroll + " " + Sink.this.needPreroll);
                            Sink.this.lostState();
                        }
                        break;
                    }
                    case 2: {
                        synchronized (sink) {
                            sink.flushing = false;
                            Sink.this.pauseTime = 0L;
                        }
                        break;
                    }
                    case 4: {
                        if (event.parseNewsegmentFormat() == 3) {
                            Sink.this.segStart = event.parseNewsegmentStart();
                            Sink.this.segStop = event.parseNewsegmentStop();
                            Sink.this.segPosition = event.parseNewsegmentPosition();
                            Sink.this.lastTime = Sink.this.segPosition;
                            break;
                        }
                        break;
                    }
                    case 3: {
                        synchronized (Sink.this.prerollLock) {
                            Sink.this.isEOS = true;
                            Debug.log(3, this + " got EOS");
                            Sink.this.postMessage(Message.newEOS(super.parent));
                        }
                        break;
                    }
                }
                return true;
            }
            
            protected int chainFunc(final Buffer buffer) {
                if (buffer.isFlagSet(1)) {
                    Sink.this.discont = true;
                }
                final long timestamp = buffer.timestamp;
                Debug.debug(super.parent.getName() + " <<< " + timestamp);
                if (timestamp != -1L) {
                    if (timestamp < Sink.this.segStart) {
                        Debug.debug(super.parent.getName() + " " + timestamp + " >>> PRE-SEGMENT DROP");
                        buffer.free();
                        return 0;
                    }
                    Sink.this.lastTime = timestamp - Sink.this.segStart + Sink.this.segPosition;
                }
                buffer.setFlag(1, Sink.this.discont);
                Sink.this.discont = false;
                int n;
                if ((n = this.finishPreroll(buffer)) != 0) {
                    Debug.debug(super.parent.getName() + " " + timestamp + " >>> PREROLL DROP");
                    return n;
                }
                Debug.debug(super.parent.getName() + " sync " + timestamp);
                final WaitStatus doSync = Sink.this.doSync(timestamp);
                Label_0358: {
                    switch (doSync.status) {
                        case 1: {
                            if (Sink.this.maxLateness != -1L && doSync.jitter > Sink.this.maxLateness) {
                                Debug.debug(super.parent.getName() + " " + timestamp + " >>> LATE, DROPPED");
                                break;
                            }
                            break Label_0358;
                        }
                        case 0: {
                            try {
                                Debug.debug(super.parent.getName() + " >>> " + timestamp);
                                n = Sink.this.render(buffer);
                            }
                            catch (Throwable t) {
                                Sink.this.postMessage(Message.newError(this, "render exception: " + t.getMessage()));
                                n = -5;
                            }
                            break;
                        }
                        default: {
                            Debug.debug(super.parent.getName() + " " + timestamp + " >>> SYNC DROP");
                            n = 0;
                            break;
                        }
                    }
                }
                buffer.free();
                return n;
            }
            
            protected boolean setCapsFunc(final Caps capsFunc) {
                return ((Sink)super.parent).setCapsFunc(capsFunc);
            }
            
            protected boolean activateFunc(final int n) {
                if (n == 0) {
                    synchronized (Sink.this.prerollLock) {
                        if (Sink.this.havePreroll) {
                            Sink.this.prerollLock.notify();
                        }
                        Sink.this.needPreroll = false;
                        Sink.this.havePreroll = false;
                        super.flushing = true;
                    }
                    Sink.this.isEOS = false;
                }
                else {
                    super.flushing = false;
                }
                return true;
            }
        });
        this.setFlag(32);
    }
    
    public boolean sendEvent(final Event event) {
        return this.sinkpad.pushEvent(event);
    }
    
    public boolean query(final Query query) {
        switch (query.getType()) {
            case 2: {
                return this.sinkpad.getPeer().query(query);
            }
            case 1: {
                long n = -1L;
                if (query.parsePositionFormat() == 3) {
                    synchronized (this) {
                        if (super.currentState == 3) {
                            if (super.clock != null) {
                                n = super.clock.getTime() - super.baseTime + this.segPosition + this.segStart;
                            }
                        }
                        else {
                            n = this.pauseTime + this.segPosition + this.segStart;
                        }
                    }
                    query.setPosition(3, n);
                    return true;
                }
                return this.sinkpad.getPeer().query(query);
            }
            default: {
                return this.sinkpad.getPeer().query(query);
            }
        }
    }
    
    protected int changeState(final int n) {
        int n2 = 1;
        switch (n) {
            case 18: {
                this.isEOS = false;
                synchronized (this.prerollLock) {
                    this.needPreroll = true;
                    this.havePreroll = false;
                }
                n2 = 2;
                break;
            }
            case 35: {
                synchronized (this.prerollLock) {
                    if (this.havePreroll) {
                        this.needPreroll = false;
                        this.prerollLock.notify();
                    }
                    else {
                        this.needPreroll = false;
                    }
                }
                break;
            }
            case 50: {
                synchronized (this) {
                    this.pauseTime = super.clock.getTime() - super.baseTime;
                }
                break;
            }
        }
        final int changeState = super.changeState(n);
        if (changeState == 0) {
            Debug.debug(this + " super state change failed");
            return changeState;
        }
        switch (n) {
            case 50: {
                Debug.debug(this + " play->paused");
                final boolean isEOS;
                synchronized (this) {
                    if (this.clockID != null) {
                        Debug.debug(this + " unschedule clockID: " + this.clockID);
                        this.clockID.unschedule();
                    }
                    isEOS = this.isEOS;
                    Debug.debug(this + " checkEOS: " + isEOS);
                }
                synchronized (this.prerollLock) {
                    Debug.debug(this + " havePreroll: " + this.havePreroll);
                    if (!this.havePreroll && !isEOS && super.pendingState == 2) {
                        this.needPreroll = true;
                        n2 = 2;
                    }
                }
            }
        }
        return n2;
    }
    
    public synchronized boolean setProperty(final String s, final java.lang.Object o) {
        boolean b = true;
        if (s.equals("max-lateness")) {
            this.maxLateness = Long.parseLong((String)o);
        }
        else {
            b = false;
        }
        return b;
    }
}

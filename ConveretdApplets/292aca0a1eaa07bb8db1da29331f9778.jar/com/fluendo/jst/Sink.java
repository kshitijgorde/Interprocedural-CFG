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
    protected Pad sinkpad;
    
    protected int preroll(final Buffer buf) {
        return 0;
    }
    
    protected boolean doEvent(final Event event) {
        return true;
    }
    
    protected int doSync(long time) {
        Clock.ClockID id = null;
        synchronized (this) {
            if (this.flushing) {
                return 2;
            }
            if (time == -1L) {
                return 0;
            }
            time = time - this.segStart + this.baseTime;
            if (this.clock != null) {
                final Clock.ClockID singleShotID = this.clock.newSingleShotID(time);
                this.clockID = singleShotID;
                id = singleShotID;
            }
        }
        int ret;
        if (id != null) {
            ret = id.waitID();
        }
        else {
            ret = 0;
        }
        synchronized (this) {
            this.clockID = null;
        }
        return ret;
    }
    
    protected boolean setCapsFunc(final Caps caps) {
        return true;
    }
    
    protected int render(final Buffer buf) {
        return 0;
    }
    
    public Sink() {
        this.prerollLock = new java.lang.Object();
        this.addPad(this.sinkpad = new Pad(2, "sink") {
            private int finishPreroll(final Buffer buf) {
                synchronized (Sink.this.prerollLock) {
                    int res = 0;
                    final Sink sink = (Sink)this.parent;
                    if (this.isFlushing()) {
                        return -2;
                    }
                    if (Sink.this.needPreroll) {
                        Sink.this.havePreroll = true;
                        try {
                            res = Sink.this.preroll(buf);
                        }
                        catch (Throwable t) {
                            Sink.this.postMessage(Message.newError(this, "preroll exception: " + t.getMessage()));
                            return -5;
                        }
                        boolean postPause = false;
                        boolean postPlaying = false;
                        final int current;
                        final int next;
                        int postPending;
                        final int pending;
                        synchronized (sink) {
                            current = Sink.this.currentState;
                            next = Sink.this.nextState;
                            pending = (postPending = Sink.this.pendingState);
                            switch (pending) {
                                case 3: {
                                    Sink.this.needPreroll = false;
                                    postPlaying = true;
                                    if (current == 1) {
                                        postPause = true;
                                        break;
                                    }
                                    break;
                                }
                                case 2: {
                                    Sink.this.needPreroll = true;
                                    postPause = true;
                                    postPending = 0;
                                    break;
                                }
                                case 1: {
                                    Sink.this.havePreroll = false;
                                    Sink.this.needPreroll = false;
                                    return -2;
                                }
                            }
                            if (pending != 0) {
                                Sink.this.currentState = pending;
                                Sink.this.nextState = 0;
                                Sink.this.pendingState = 0;
                                Sink.this.lastReturn = 1;
                            }
                        }
                        if (postPause) {
                            Sink.this.postMessage(Message.newStateChanged(this, current, next, postPending));
                        }
                        if (postPlaying) {
                            Sink.this.postMessage(Message.newStateChanged(this, next, pending, 0));
                        }
                        if (postPause || postPlaying) {
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
                    return res;
                }
            }
            
            protected boolean eventFunc(final Event event) {
                final Sink sink = (Sink)this.parent;
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
                        synchronized (this.streamLock) {
                            Debug.log(4, this + " synced " + Sink.this.havePreroll + " " + Sink.this.needPreroll);
                            Sink.this.lostState();
                        }
                        break;
                    }
                    case 2: {
                        synchronized (sink) {
                            sink.flushing = false;
                        }
                        break;
                    }
                    case 4: {
                        final int segFmt = event.parseNewsegmentFormat();
                        if (segFmt == 3) {
                            Sink.this.segStart = event.parseNewsegmentStart();
                            Sink.this.segStop = event.parseNewsegmentStop();
                            Sink.this.segPosition = event.parseNewsegmentPosition();
                            break;
                        }
                        break;
                    }
                    case 3: {
                        synchronized (Sink.this.prerollLock) {
                            Sink.this.isEOS = true;
                            Debug.log(3, this + " got EOS");
                            Sink.this.postMessage(Message.newEOS(this.parent));
                        }
                        break;
                    }
                }
                return true;
            }
            
            protected int chainFunc(final Buffer buf) {
                if (buf.isFlagSet(1)) {
                    Sink.this.discont = true;
                }
                final long time = buf.timestamp;
                if (time != -1L && time < Sink.this.segStart) {
                    buf.free();
                    return 0;
                }
                buf.setFlag(1, Sink.this.discont);
                Sink.this.discont = false;
                int res;
                if ((res = this.finishPreroll(buf)) != 0) {
                    return res;
                }
                final int status = Sink.this.doSync(time);
                switch (status) {
                    case 0:
                    case 1: {
                        try {
                            res = Sink.this.render(buf);
                        }
                        catch (Throwable t) {
                            Sink.this.postMessage(Message.newError(this, "render exception: " + t.getMessage()));
                            res = -5;
                        }
                        break;
                    }
                    default: {
                        res = 0;
                        break;
                    }
                }
                buf.free();
                return res;
            }
            
            protected boolean setCapsFunc(final Caps caps) {
                final Sink sink = (Sink)this.parent;
                final boolean res = sink.setCapsFunc(caps);
                return res;
            }
            
            protected boolean activateFunc(final int mode) {
                if (mode == 0) {
                    synchronized (Sink.this.prerollLock) {
                        if (Sink.this.havePreroll) {
                            Sink.this.prerollLock.notify();
                        }
                        Sink.this.needPreroll = false;
                        Sink.this.havePreroll = false;
                        this.flushing = true;
                    }
                    Sink.this.isEOS = false;
                }
                else {
                    this.flushing = false;
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
                long position = -1L;
                if (query.parsePositionFormat() == 3) {
                    synchronized (this) {
                        if (this.currentState == 3) {
                            if (this.clock != null) {
                                position = this.clock.getTime() - this.baseTime + this.segPosition;
                            }
                        }
                        else {
                            position = this.segPosition;
                        }
                    }
                    query.setPosition(3, position);
                    return true;
                }
                return this.sinkpad.getPeer().query(query);
            }
            default: {
                return this.sinkpad.getPeer().query(query);
            }
        }
    }
    
    protected int changeState(final int transition) {
        int result = 1;
        switch (transition) {
            case 18: {
                this.isEOS = false;
                synchronized (this.prerollLock) {
                    this.needPreroll = true;
                    this.havePreroll = false;
                }
                result = 2;
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
        }
        final int presult = super.changeState(transition);
        if (presult == 0) {
            Debug.log(4, this + " super state change failed");
            return presult;
        }
        switch (transition) {
            case 50: {
                Debug.log(4, this + " play->paused");
                final boolean checkEOS;
                synchronized (this) {
                    if (this.clockID != null) {
                        Debug.log(4, this + " unschedule clockID: " + this.clockID);
                        this.clockID.unschedule();
                    }
                    checkEOS = this.isEOS;
                    Debug.log(4, this + " checkEOS: " + checkEOS);
                }
                synchronized (this.prerollLock) {
                    Debug.log(4, this + " havePreroll: " + this.havePreroll);
                    if (!this.havePreroll && !checkEOS && this.pendingState == 2) {
                        this.needPreroll = true;
                        result = 2;
                    }
                }
            }
        }
        return result;
    }
}

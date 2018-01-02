// 
// Decompiled by Procyon v0.5.30
// 

package com.fluendo.jst;

import java.util.Enumeration;
import java.util.Vector;

public class Pad extends Object implements Runnable
{
    public static final int UNKNOWN = 0;
    public static final int SRC = 1;
    public static final int SINK = 2;
    public static final int OK = 0;
    public static final int NOT_LINKED = -1;
    public static final int WRONG_STATE = -2;
    public static final int UNEXPECTED = -3;
    public static final int NOT_NEGOTIATED = -4;
    public static final int ERROR = -5;
    public static final int NOT_SUPPORTED = -6;
    public static final int MODE_NONE = 0;
    public static final int MODE_PUSH = 1;
    public static final int MODE_PULL = 2;
    protected Pad peer;
    protected int direction;
    protected boolean flushing;
    protected java.lang.Object streamLock;
    int mode;
    private Vector capsListeners;
    protected Caps caps;
    private static final int T_STOP = 0;
    private static final int T_PAUSE = 1;
    private static final int T_START = 2;
    private Thread thread;
    private int taskState;
    
    public static final boolean isFlowFatal(final int ret) {
        return ret <= -3;
    }
    
    public static final boolean isFlowSuccess(final int ret) {
        return ret >= 0;
    }
    
    public static final String getFlowName(final int ret) {
        switch (ret) {
            case 0: {
                return "ok";
            }
            case -1: {
                return "not-linked";
            }
            case -2: {
                return "wrong-state";
            }
            case -3: {
                return "unexpected";
            }
            case -4: {
                return "not-negotiated";
            }
            case -5: {
                return "error";
            }
            case -6: {
                return "not-supported";
            }
            default: {
                return "unknown";
            }
        }
    }
    
    public Pad(final int direction) {
        this(direction, null);
    }
    
    public Pad(final int direction, final String name) {
        super(name);
        this.direction = 0;
        this.streamLock = new java.lang.Object();
        this.capsListeners = new Vector();
        this.direction = direction;
    }
    
    public String toString() {
        String parentName;
        if (this.parent != null) {
            parentName = this.parent.getName();
        }
        else {
            parentName = "";
        }
        String thisName = this.getName();
        if (thisName == null) {
            thisName = "";
        }
        return "Pad: " + parentName + ":" + thisName + " [" + super.toString() + "]";
    }
    
    public synchronized void addCapsListener(final CapsListener listener) {
        this.capsListeners.addElement(listener);
    }
    
    public synchronized void removeCapsListener(final CapsListener listener) {
        this.capsListeners.removeElement(listener);
    }
    
    private synchronized void doCapsListeners(final Caps caps) {
        final Enumeration e = this.capsListeners.elements();
        while (e.hasMoreElements()) {
            final CapsListener listener = e.nextElement();
            listener.capsChanged(caps);
        }
    }
    
    public synchronized boolean link(final Pad newPeer) {
        if (this.peer != null) {
            return false;
        }
        if (this.direction != 1) {
            return false;
        }
        synchronized (newPeer) {
            if (newPeer.direction != 2) {
                return false;
            }
            if (newPeer.peer != null) {
                return false;
            }
            this.peer = newPeer;
            this.peer.peer = this;
        }
        return true;
    }
    
    public synchronized void unlink() {
        if (this.peer == null) {
            return;
        }
        if (this.direction == 1) {
            this.peer.unlink();
        }
        this.peer = null;
    }
    
    public synchronized Pad getPeer() {
        return this.peer;
    }
    
    protected boolean eventFunc(final Event event) {
        switch (event.getType()) {
            default: {
                final boolean result = false;
                return result;
            }
        }
    }
    
    public final boolean sendEvent(final Event event) {
        boolean result = false;
        switch (event.getType()) {
            case 1: {
                this.setFlushing(true);
                result = this.eventFunc(event);
                break;
            }
            case 2: {
                synchronized (this.streamLock) {
                    this.setFlushing(false);
                    result = this.eventFunc(event);
                }
                break;
            }
            case 3:
            case 4: {
                synchronized (this.streamLock) {
                    result = this.eventFunc(event);
                }
                break;
            }
            case 5: {
                result = this.eventFunc(event);
                break;
            }
            default: {
                result = false;
                break;
            }
        }
        return result;
    }
    
    public boolean query(final Query query) {
        return false;
    }
    
    public synchronized Caps getCaps() {
        return this.caps;
    }
    
    protected boolean setCapsFunc(final Caps caps) {
        return true;
    }
    
    public boolean setCaps(final Caps caps) {
        boolean res = true;
        if (caps != null) {
            res = this.setCapsFunc(caps);
        }
        if (res && (this.caps = caps) != null) {
            this.doCapsListeners(caps);
        }
        return res;
    }
    
    private final int chain(final Buffer buffer) {
        synchronized (this.streamLock) {
            synchronized (this) {
                if (this.flushing) {
                    return -2;
                }
                if (buffer.caps != null && buffer.caps != this.caps && !this.setCaps(buffer.caps)) {
                    buffer.free();
                    return -4;
                }
            }
            final int res = this.chainFunc(buffer);
            return res;
        }
    }
    
    protected int chainFunc(final Buffer buffer) {
        return -5;
    }
    
    public final int push(final Buffer buffer) {
        if (this.peer == null) {
            return -1;
        }
        return this.peer.chain(buffer);
    }
    
    public final boolean pushEvent(final Event event) {
        return this.peer != null && this.peer.sendEvent(event);
    }
    
    public synchronized void setFlushing(final boolean flush) {
        this.flushing = flush;
    }
    
    public synchronized boolean isFlushing() {
        return this.flushing;
    }
    
    protected boolean activateFunc(final int mode) {
        return true;
    }
    
    public final boolean activate(final int newMode) {
        if (this.mode == newMode) {
            return true;
        }
        if (newMode == 0) {
            this.setFlushing(true);
        }
        final boolean res;
        if (!(res = this.activateFunc(newMode))) {
            return false;
        }
        if (newMode != 0) {
            this.setFlushing(false);
        }
        else {
            synchronized (this.streamLock) {
                this.setCaps(null);
            }
        }
        this.mode = newMode;
        return res;
    }
    
    protected void taskFunc() {
    }
    
    public void run() {
        synchronized (this.streamLock) {
            while (this.taskState != 0) {
                while (this.taskState == 1) {
                    try {
                        this.streamLock.wait();
                    }
                    catch (InterruptedException ie) {}
                }
                if (this.taskState == 0) {
                    break;
                }
                try {
                    this.taskFunc();
                }
                catch (Throwable t) {
                    t.printStackTrace();
                }
            }
        }
    }
    
    public boolean startTask(final String name) {
        synchronized (this.streamLock) {
            this.taskState = 2;
            if (this.thread == null) {
                (this.thread = new Thread(this, name)).start();
            }
            this.streamLock.notifyAll();
        }
        return true;
    }
    
    public boolean pauseTask() {
        this.taskState = 1;
        synchronized (this.streamLock) {
            this.taskState = 1;
        }
        return true;
    }
    
    public boolean stopTask() {
        this.taskState = 0;
        final Thread t;
        synchronized (this.streamLock) {
            this.taskState = 0;
            this.streamLock.notifyAll();
            t = this.thread;
            this.thread = null;
        }
        try {
            t.join();
        }
        catch (InterruptedException ex) {}
        return true;
    }
}

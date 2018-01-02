// 
// Decompiled by Procyon v0.5.30
// 

package com.fluendo.jst;

import com.fluendo.utils.Debug;
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
    
    public static final boolean isFlowFatal(final int n) {
        return n <= -3;
    }
    
    public static final boolean isFlowSuccess(final int n) {
        return n >= 0;
    }
    
    public static final String getFlowName(final int n) {
        switch (n) {
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
    
    public Pad(final int n) {
        this(n, null);
    }
    
    public Pad(final int direction, final String s) {
        super(s);
        this.direction = 0;
        this.streamLock = new java.lang.Object();
        this.capsListeners = new Vector();
        this.direction = direction;
    }
    
    public String toString() {
        String name;
        if (super.parent != null) {
            name = super.parent.getName();
        }
        else {
            name = "";
        }
        String name2 = this.getName();
        if (name2 == null) {
            name2 = "";
        }
        return "Pad: " + name + ":" + name2;
    }
    
    public synchronized void addCapsListener(final CapsListener capsListener) {
        this.capsListeners.addElement(capsListener);
    }
    
    public synchronized void removeCapsListener(final CapsListener capsListener) {
        this.capsListeners.removeElement(capsListener);
    }
    
    private synchronized void doCapsListeners(final Caps caps) {
        final Enumeration<CapsListener> elements = this.capsListeners.elements();
        while (elements.hasMoreElements()) {
            elements.nextElement().capsChanged(caps);
        }
    }
    
    public synchronized boolean link(final Pad peer) {
        if (this.peer != null) {
            return false;
        }
        if (this.direction != 1) {
            return false;
        }
        synchronized (peer) {
            if (peer.direction != 2) {
                return false;
            }
            if (peer.peer != null) {
                return false;
            }
            this.peer = peer;
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
                return false;
            }
        }
    }
    
    public final boolean sendEvent(final Event event) {
        Debug.debug(this + " got event " + event);
        boolean b = false;
        switch (event.getType()) {
            case 1: {
                this.setFlushing(true);
                b = this.eventFunc(event);
                break;
            }
            case 2: {
                synchronized (this.streamLock) {
                    this.setFlushing(false);
                    b = this.eventFunc(event);
                }
                break;
            }
            case 3:
            case 4: {
                synchronized (this.streamLock) {
                    b = this.eventFunc(event);
                }
                break;
            }
            case 5: {
                b = this.eventFunc(event);
                break;
            }
            default: {
                b = false;
                break;
            }
        }
        return b;
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
        boolean setCapsFunc = true;
        if (caps != null) {
            setCapsFunc = this.setCapsFunc(caps);
        }
        if (setCapsFunc && (this.caps = caps) != null) {
            this.doCapsListeners(caps);
        }
        return setCapsFunc;
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
            return this.chainFunc(buffer);
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
    
    public synchronized void setFlushing(final boolean flushing) {
        this.flushing = flushing;
    }
    
    public synchronized boolean isFlushing() {
        return this.flushing;
    }
    
    protected boolean activateFunc(final int n) {
        return true;
    }
    
    public final boolean activate(final int mode) {
        Debug.debug(this + " activate mode = " + ((mode == 0) ? "none" : "push"));
        if (this.mode == mode) {
            return true;
        }
        if (mode == 0) {
            this.setFlushing(true);
        }
        final boolean activateFunc;
        if (!(activateFunc = this.activateFunc(mode))) {
            return false;
        }
        if (mode != 0) {
            this.setFlushing(false);
        }
        else {
            synchronized (this.streamLock) {
                this.setCaps(null);
            }
        }
        this.mode = mode;
        return activateFunc;
    }
    
    protected void taskFunc() {
    }
    
    public void run() {
        synchronized (this.streamLock) {
            while (this.taskState != 0) {
                while (this.taskState == 1) {
                    Debug.debug(super.parent.getName() + ":" + this.getName() + " paused, waiting...");
                    try {
                        this.streamLock.wait();
                    }
                    catch (InterruptedException ex) {}
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
    
    public boolean startTask(final String s) {
        synchronized (this.streamLock) {
            this.taskState = 2;
            if (this.thread == null) {
                (this.thread = new Thread(this, s)).start();
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
        final Thread thread;
        synchronized (this.streamLock) {
            this.taskState = 0;
            this.streamLock.notifyAll();
            thread = this.thread;
            this.thread = null;
        }
        try {
            thread.join();
        }
        catch (InterruptedException ex) {}
        return true;
    }
}

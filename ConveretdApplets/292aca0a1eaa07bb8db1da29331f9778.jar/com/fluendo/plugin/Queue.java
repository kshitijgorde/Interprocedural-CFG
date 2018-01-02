// 
// Decompiled by Procyon v0.5.30
// 

package com.fluendo.plugin;

import com.fluendo.utils.Debug;
import com.fluendo.jst.Event;
import com.fluendo.jst.Message;
import java.util.Enumeration;
import com.fluendo.jst.Buffer;
import com.fluendo.jst.Pad;
import java.util.Vector;
import com.fluendo.jst.Element;

public class Queue extends Element
{
    private static final int DEFAULT_MAX_BUFFERS = 100;
    private static final int DEFAULT_MAX_SIZE = -1;
    private static final boolean DEFAULT_IS_BUFFER = false;
    private static final int DEFAULT_LOW_PERCENT = 10;
    private static final int DEFAULT_HIGH_PERCENT = 70;
    private Vector queue;
    private int srcResult;
    private int size;
    private boolean isBuffering;
    private boolean isEOS;
    private int maxBuffers;
    private int maxSize;
    private boolean isBuffer;
    private int lowPercent;
    private int highPercent;
    private Pad srcpad;
    private Pad sinkpad;
    
    private boolean isFilled() {
        if (this.maxSize != -1) {
            return this.size >= this.maxSize;
        }
        return this.queue.size() > this.maxBuffers;
    }
    
    private boolean isEmpty() {
        return this.queue.size() == 0;
    }
    
    private void clearQueue() {
        final Enumeration e = this.queue.elements();
        while (e.hasMoreElements()) {
            final java.lang.Object obj = e.nextElement();
            if (obj instanceof Buffer) {
                ((Buffer)obj).free();
            }
        }
        this.queue.setSize(0);
        this.size = 0;
        this.isBuffering = true;
    }
    
    private void updateBuffering() {
        if (!this.isBuffer || this.srcResult != 0) {
            return;
        }
        if (this.isEOS) {
            this.isBuffering = false;
            return;
        }
        int percent = this.size * 100 / this.maxSize;
        if (percent > 100) {
            percent = 100;
        }
        if (this.isBuffering) {
            if (percent >= this.highPercent) {
                this.isBuffering = false;
            }
            this.postMessage(Message.newBuffering(this, this.isBuffering, percent));
        }
        else if (percent < this.lowPercent) {
            this.isBuffering = true;
        }
    }
    
    public Queue() {
        this.queue = new Vector();
        this.srcResult = -2;
        this.maxBuffers = 100;
        this.maxSize = -1;
        this.isBuffer = false;
        this.lowPercent = 10;
        this.highPercent = 70;
        this.srcpad = new Pad(1, "src") {
            protected void taskFunc() {
                final Object obj;
                synchronized (Queue.this.queue) {
                    if (Queue.this.srcResult != 0) {
                        return;
                    }
                    while (Queue.this.isEmpty()) {
                        try {
                            Queue.this.queue.wait();
                            if (Queue.this.srcResult != 0) {
                                return;
                            }
                            continue;
                        }
                        catch (InterruptedException ie) {}
                    }
                    obj = Queue.this.queue.elementAt(Queue.this.queue.size() - 1);
                    Queue.this.queue.removeElement(obj);
                    Queue.this.queue.notifyAll();
                }
                int res;
                if (obj instanceof Event) {
                    final Event event = (Event)obj;
                    this.pushEvent(event);
                    res = 0;
                    if (event.getType() == 3) {
                        Queue.this.postMessage(Message.newStreamStatus(this, false, 0, "flow stopped, EOS"));
                        this.pauseTask();
                    }
                }
                else {
                    final Buffer buf = (Buffer)obj;
                    Queue.this.size -= buf.length;
                    res = this.push(buf);
                }
                synchronized (Queue.this.queue) {
                    if (res != 0) {
                        Queue.this.srcResult = res;
                        if (Pad.isFlowFatal(res)) {
                            this.pushEvent(Event.newEOS());
                        }
                        Queue.this.postMessage(Message.newStreamStatus(this, false, res, "flow stopped"));
                        this.pauseTask();
                    }
                    Queue.this.updateBuffering();
                }
            }
            
            protected boolean activateFunc(final int mode) {
                boolean res = true;
                switch (mode) {
                    case 0: {
                        synchronized (Queue.this.queue) {
                            Queue.this.clearQueue();
                            Queue.this.srcResult = -2;
                            Queue.this.queue.notifyAll();
                        }
                        Queue.this.postMessage(Message.newStreamStatus(this, false, -2, "stopping"));
                        res = this.stopTask();
                        break;
                    }
                    case 1: {
                        Queue.this.isEOS = false;
                        synchronized (Queue.this.queue) {
                            Queue.this.srcResult = 0;
                            if (!Queue.this.isBuffer) {
                                Queue.this.isBuffering = false;
                            }
                            else {
                                Queue.this.isBuffering = true;
                                Queue.this.postMessage(Message.newBuffering(this, true, 0));
                            }
                            Queue.this.postMessage(Message.newStreamStatus(this, true, 0, "activating"));
                            res = this.startTask("cortado-Queue-Stream-" + Debug.genId());
                        }
                        break;
                    }
                    default: {
                        synchronized (Queue.this.queue) {
                            Queue.this.srcResult = -2;
                        }
                        res = false;
                        break;
                    }
                }
                return res;
            }
        };
        this.sinkpad = new Pad(2, "sink") {
            protected boolean eventFunc(final Event event) {
                final int type = event.getType();
                boolean doQueue = true;
                switch (type) {
                    case 1: {
                        Queue.this.srcpad.pushEvent(event);
                        synchronized (Queue.this.queue) {
                            Queue.this.srcResult = -2;
                            Queue.this.queue.notifyAll();
                        }
                        synchronized (this.streamLock) {
                            Debug.log(4, this + " synced");
                        }
                        Queue.this.postMessage(Message.newStreamStatus(Queue.this.srcpad, false, -2, "flush start"));
                        Queue.this.srcpad.pauseTask();
                        doQueue = false;
                        break;
                    }
                    case 2: {
                        Queue.this.srcpad.pushEvent(event);
                        Queue.this.isEOS = false;
                        synchronized (Queue.this.queue) {
                            Queue.this.clearQueue();
                            Queue.this.srcResult = 0;
                            Queue.this.queue.notifyAll();
                        }
                        if (Queue.this.isBuffer) {
                            Queue.this.isBuffering = true;
                            Queue.this.postMessage(Message.newBuffering(this, true, 0));
                        }
                        Queue.this.postMessage(Message.newStreamStatus(Queue.this.srcpad, true, 0, "restart after flush"));
                        Queue.this.srcpad.startTask("cortado-Queue-Stream-" + Debug.genId());
                        doQueue = false;
                        break;
                    }
                    case 3: {
                        Queue.this.isEOS = true;
                        Debug.log(3, "got EOS: " + this);
                        if (Queue.this.isBuffer && Queue.this.isBuffering) {
                            Queue.this.isBuffering = false;
                            Queue.this.postMessage(Message.newBuffering(this, Queue.this.isBuffering, 100));
                            break;
                        }
                        break;
                    }
                }
                if (doQueue) {
                    synchronized (Queue.this.queue) {
                        Queue.this.queue.insertElementAt(event, 0);
                        Queue.this.queue.notifyAll();
                    }
                }
                return true;
            }
            
            protected int chainFunc(final Buffer buf) {
                synchronized (Queue.this.queue) {
                    if (Queue.this.srcResult != 0) {
                        buf.free();
                        return Queue.this.srcResult;
                    }
                    while (Queue.this.isFilled()) {
                        try {
                            Queue.this.queue.wait();
                            if (Queue.this.srcResult != 0) {
                                buf.free();
                                return Queue.this.srcResult;
                            }
                            continue;
                        }
                        catch (InterruptedException ie) {
                            ie.printStackTrace();
                            buf.free();
                            return -2;
                        }
                        break;
                    }
                    Queue.this.size += buf.length;
                    Queue.this.updateBuffering();
                    Queue.this.queue.insertElementAt(buf, 0);
                    Queue.this.queue.notifyAll();
                }
                return 0;
            }
        };
        this.addPad(this.srcpad);
        this.addPad(this.sinkpad);
    }
    
    public String getFactoryName() {
        return "queue";
    }
    
    public boolean setProperty(final String name, final java.lang.Object value) {
        if (name.equals("maxBuffers")) {
            this.maxBuffers = Integer.valueOf(value.toString());
        }
        else if (name.equals("maxSize")) {
            this.maxSize = Integer.valueOf(value.toString());
        }
        else if (name.equals("isBuffer")) {
            this.isBuffer = String.valueOf(value).equalsIgnoreCase("true");
        }
        else if (name.equals("lowPercent")) {
            this.lowPercent = Integer.valueOf(value.toString());
        }
        else {
            if (!name.equals("highPercent")) {
                return false;
            }
            this.highPercent = Integer.valueOf(value.toString());
        }
        return true;
    }
    
    public java.lang.Object getProperty(final String name) {
        if (name.equals("maxBuffers")) {
            return new Integer(this.maxBuffers);
        }
        if (name.equals("maxSize")) {
            return new Integer(this.maxSize);
        }
        if (name.equals("isBuffer")) {
            return this.isBuffer ? "true" : "false";
        }
        if (name.equals("lowPercent")) {
            return new Integer(this.lowPercent);
        }
        if (name.equals("highPercent")) {
            return new Integer(this.highPercent);
        }
        return null;
    }
}

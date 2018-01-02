// 
// Decompiled by Procyon v0.5.30
// 

package com.fluendo.plugin;

import com.fluendo.jst.Event;
import com.fluendo.utils.Debug;
import com.fluendo.jst.Object;
import com.fluendo.jst.Message;
import java.util.Enumeration;
import com.fluendo.jst.Buffer;
import com.fluendo.jst.Pad;
import java.util.Vector;
import com.fluendo.jst.Element;

public class Queue extends Element
{
    public static final int NO_LEAK = 0;
    public static final int LEAK_UPSTREAM = 1;
    public static final int LEAK_DOWNSTREAM = 2;
    private static final int DEFAULT_MAX_BUFFERS = 100;
    private static final int DEFAULT_MAX_SIZE = -1;
    private static final boolean DEFAULT_IS_BUFFER = false;
    private static final int DEFAULT_LOW_PERCENT = 10;
    private static final int DEFAULT_HIGH_PERCENT = 70;
    private static final int DEFAULT_LEAKY = 0;
    private Vector queue;
    private int srcResult;
    private int size;
    private boolean isBuffering;
    private boolean isEOS;
    private boolean headNeedsDiscont;
    private boolean tailNeedsDiscont;
    private int maxBuffers;
    private int maxSize;
    private boolean isBuffer;
    private int lowPercent;
    private int highPercent;
    private int leaky;
    private Pad srcpad;
    private Pad sinkpad;
    
    private boolean isFilled() {
        if (this.maxSize != -1) {
            return this.size >= this.maxSize;
        }
        return this.queue.size() >= this.maxBuffers;
    }
    
    private boolean isEmpty() {
        return this.queue.size() == 0;
    }
    
    private void clearQueue() {
        final Enumeration<Buffer> elements = this.queue.elements();
        while (elements.hasMoreElements()) {
            final Buffer nextElement = elements.nextElement();
            if (nextElement instanceof Buffer) {
                nextElement.free();
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
            if (this.isBuffering) {
                this.isBuffering = false;
                this.postMessage(Message.newBuffering(this, false, 0));
            }
            return;
        }
        int n = this.size * 100 / this.maxSize;
        if (n > 100) {
            n = 100;
        }
        if (this.isBuffering) {
            if (n >= this.highPercent) {
                this.isBuffering = false;
            }
            this.postMessage(Message.newBuffering(this, this.isBuffering, n));
        }
        else if (n < this.lowPercent) {
            this.isBuffering = true;
        }
    }
    
    private void leakDownstream() {
        while (this.isFilled()) {
            synchronized (this.queue) {
                final Buffer lastElement = this.queue.lastElement();
                if (lastElement == null) {
                    Debug.error("There is nothing to dequeue and the queue is still filled. This should not happen.");
                }
                this.queue.removeElementAt(this.queue.size() - 1);
                if (lastElement instanceof Buffer) {
                    lastElement.free();
                }
                this.headNeedsDiscont = true;
                this.queue.notifyAll();
            }
        }
    }
    
    public Queue() {
        this.queue = new Vector();
        this.srcResult = -2;
        this.headNeedsDiscont = false;
        this.tailNeedsDiscont = false;
        this.maxBuffers = 100;
        this.maxSize = -1;
        this.isBuffer = false;
        this.lowPercent = 10;
        this.highPercent = 70;
        this.leaky = 0;
        this.srcpad = new Pad(1, "src") {
            protected void taskFunc() {
                final Buffer element;
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
                        catch (InterruptedException ex) {}
                    }
                    element = Queue.this.queue.elementAt(Queue.this.queue.size() - 1);
                    Queue.this.queue.removeElement(element);
                    Queue.this.queue.notifyAll();
                }
                int push;
                if (element instanceof Event) {
                    final Event event = (Event)element;
                    this.pushEvent(event);
                    push = 0;
                    if (event.getType() == 3) {
                        Queue.this.postMessage(Message.newStreamStatus(this, false, 0, "flow stopped, EOS"));
                        this.pauseTask();
                    }
                }
                else {
                    final Buffer buffer = element;
                    if (Queue.this.headNeedsDiscont) {
                        buffer.setFlag(1, true);
                        Queue.this.headNeedsDiscont = false;
                    }
                    Queue.this.size -= buffer.length;
                    Debug.log(4, super.parent.getName() + " >>> " + buffer);
                    push = this.push(buffer);
                    if (Queue.this.maxSize == -1) {
                        Debug.log(4, super.parent.getName() + " count = " + Queue.this.queue.size() + "/" + Queue.this.maxBuffers);
                    }
                    else {
                        Debug.log(4, super.parent.getName() + " size = " + Queue.this.size + "/" + Queue.this.maxSize);
                    }
                }
                synchronized (Queue.this.queue) {
                    if (push != 0) {
                        Queue.this.srcResult = push;
                        if (Pad.isFlowFatal(push)) {
                            this.pushEvent(Event.newEOS());
                        }
                        Queue.this.postMessage(Message.newStreamStatus(this, false, push, "flow stopped"));
                        this.pauseTask();
                    }
                    Queue.this.updateBuffering();
                }
            }
            
            protected boolean activateFunc(final int n) {
                boolean b = true;
                switch (n) {
                    case 0: {
                        synchronized (Queue.this.queue) {
                            Queue.this.clearQueue();
                            Queue.this.srcResult = -2;
                            Queue.this.queue.notifyAll();
                        }
                        if (Queue.this.isBuffer && Queue.this.isBuffering) {
                            Queue.this.isBuffering = false;
                            Queue.this.postMessage(Message.newBuffering(this, false, 0));
                        }
                        Queue.this.postMessage(Message.newStreamStatus(this, false, -2, "stopping"));
                        b = this.stopTask();
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
                            b = this.startTask("cortado-Queue-Stream-" + Debug.genId());
                        }
                        break;
                    }
                    default: {
                        synchronized (Queue.this.queue) {
                            Queue.this.srcResult = -2;
                        }
                        b = false;
                        break;
                    }
                }
                return b;
            }
        };
        this.sinkpad = new Pad(2, "sink") {
            protected boolean eventFunc(final Event event) {
                final int type = event.getType();
                boolean b = true;
                switch (type) {
                    case 1: {
                        Queue.this.srcpad.pushEvent(event);
                        synchronized (Queue.this.queue) {
                            Queue.this.srcResult = -2;
                            Queue.this.queue.notifyAll();
                        }
                        synchronized (super.streamLock) {
                            Debug.log(4, this + " synced");
                        }
                        Queue.this.postMessage(Message.newStreamStatus(Queue.this.srcpad, false, -2, "flush start"));
                        Queue.this.srcpad.pauseTask();
                        b = false;
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
                        b = false;
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
                if (b) {
                    synchronized (Queue.this.queue) {
                        Queue.this.queue.insertElementAt(event, 0);
                        Queue.this.queue.notifyAll();
                    }
                }
                return true;
            }
            
            protected int chainFunc(final Buffer buffer) {
                synchronized (Queue.this.queue) {
                    if (Queue.this.srcResult != 0) {
                        buffer.free();
                        return Queue.this.srcResult;
                    }
                Label_0225:
                    while (Queue.this.isFilled()) {
                        switch (Queue.this.leaky) {
                            case 1: {
                                Queue.this.tailNeedsDiscont = true;
                                Debug.debug(super.parent.getName() + "is full, leaking buffer on upstream end");
                                buffer.free();
                                Queue.this.queue.notifyAll();
                                return 0;
                            }
                            case 2: {
                                Queue.this.leakDownstream();
                                continue;
                            }
                            default: {
                                Debug.warn("Unknown leaky type, using default");
                            }
                            case 0: {
                                try {
                                    Debug.debug(super.parent.getName() + " full, waiting...");
                                    Queue.this.queue.wait();
                                    if (Queue.this.srcResult != 0) {
                                        buffer.free();
                                        return Queue.this.srcResult;
                                    }
                                    continue;
                                }
                                catch (InterruptedException ex) {
                                    ex.printStackTrace();
                                    buffer.free();
                                    return -2;
                                }
                                break Label_0225;
                            }
                        }
                    }
                    if (Queue.this.tailNeedsDiscont) {
                        buffer.setFlag(1, true);
                        Queue.this.tailNeedsDiscont = false;
                    }
                    Queue.this.size += buffer.length;
                    Queue.this.updateBuffering();
                    Debug.log(4, super.parent.getName() + " <<< " + buffer);
                    Queue.this.queue.insertElementAt(buffer, 0);
                    if (Queue.this.maxSize == -1) {
                        Debug.log(4, super.parent.getName() + " count = " + Queue.this.queue.size() + "/" + Queue.this.maxBuffers);
                    }
                    else {
                        Debug.log(4, super.parent.getName() + " size = " + Queue.this.size + "/" + Queue.this.maxSize);
                    }
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
    
    public boolean setProperty(final String s, final java.lang.Object o) {
        if (s.equals("maxBuffers")) {
            this.maxBuffers = Integer.valueOf(o.toString());
        }
        else if (s.equals("maxSize")) {
            this.maxSize = Integer.valueOf(o.toString());
        }
        else if (s.equals("isBuffer")) {
            this.isBuffer = String.valueOf(o).equalsIgnoreCase("true");
        }
        else if (s.equals("lowPercent")) {
            this.lowPercent = Integer.valueOf(o.toString());
        }
        else if (s.equals("highPercent")) {
            this.highPercent = Integer.valueOf(o.toString());
        }
        else {
            if (!s.equals("leaky")) {
                return false;
            }
            this.leaky = Integer.valueOf(o.toString());
        }
        return true;
    }
    
    public java.lang.Object getProperty(final String s) {
        if (s.equals("maxBuffers")) {
            return new Integer(this.maxBuffers);
        }
        if (s.equals("maxSize")) {
            return new Integer(this.maxSize);
        }
        if (s.equals("isBuffer")) {
            return this.isBuffer ? "true" : "false";
        }
        if (s.equals("lowPercent")) {
            return new Integer(this.lowPercent);
        }
        if (s.equals("highPercent")) {
            return new Integer(this.highPercent);
        }
        if (s.equals("leaky")) {
            return new Integer(this.leaky);
        }
        return null;
    }
}

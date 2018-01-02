// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.jms.msg.event;

import com.stonewall.cornerstone.utility.Transaction;
import org.xmodel.Xlate;
import org.xmodel.IModelObject;
import com.stonewall.cornerstone.jms.msg.JmsMessage;
import org.xmodel.log.Log;

public class ProcessEvent extends Event
{
    static final String Tag;
    static final Log log;
    
    static {
        Tag = Type.process.getQualifiedName();
        log = Log.getLog(ProcessEvent.class);
    }
    
    public static void send(final Status status) {
        if (Event.getThreadCorrelation() == null) {
            ProcessEvent.log.warn("Event correlation not-found.");
        }
        new ProcessEvent(0, 0L, status).send();
    }
    
    public static void send(final int pct, final long timeRemaining, final Status status) {
        if (Event.getThreadCorrelation() == null) {
            ProcessEvent.log.warn("Event correlation not-found.");
        }
        new ProcessEvent(pct, timeRemaining, status).send();
    }
    
    public ProcessEvent(final int pct, final long timeRemaining, final Status status) {
        super(ProcessEvent.Tag, ProcessEvent.log);
        this.setPriority(status.getPriority());
        this.root.getCreateChild("evt:status").setValue(status.name());
        this.setPercent(String.valueOf(pct));
        this.setTimeRemaining(String.valueOf(timeRemaining));
    }
    
    public ProcessEvent(final JmsMessage message, final IModelObject root) throws Exception {
        super(message, root);
    }
    
    public ProcessEvent(final IModelObject e) {
        super(e, ProcessEvent.log);
    }
    
    @Override
    public Status getStatus() {
        return Status.valueOf(Xlate.get(this.root.getFirstChild("evt:status"), (String)null));
    }
    
    public int getPercent() {
        return new Integer(Xlate.get(this.root.getFirstChild("evt:percent"), (String)null));
    }
    
    private void setPercent(final String value) {
        this.root.getCreateChild("evt:percent").setValue(value);
    }
    
    public long getTimeRemaining() {
        return new Long(Xlate.get(this.root.getFirstChild("evt:timeRemaining"), (String)null));
    }
    
    private void setTimeRemaining(final String value) {
        this.root.getCreateChild("evt:timeRemaining").setValue(value);
    }
    
    @Override
    public void send() {
        final Transaction tr = Transaction.getCurrent();
        if (tr != null && this.shouldBuffer()) {
            tr.add(this);
        }
        else {
            super.send();
        }
    }
    
    private boolean shouldBuffer() {
        final Status s = this.getStatus();
        return s.equals(Status.completed) || s.equals(Status.cancelled) || s.equals(Status.failed);
    }
    
    public enum Status
    {
        started("started", 0, 6), 
        completed("completed", 1, 4), 
        failed("failed", 2, 4), 
        cancelled("cancelled", 3, 4), 
        progress("progress", 4, 6);
        
        private int priority;
        
        private Status(final String s, final int n, final int priority) {
            this.priority = 0;
            this.priority = priority;
        }
        
        public int getPriority() {
            return this.priority;
        }
    }
}

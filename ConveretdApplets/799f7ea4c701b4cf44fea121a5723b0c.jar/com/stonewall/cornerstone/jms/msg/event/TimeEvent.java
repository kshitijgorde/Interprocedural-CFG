// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.jms.msg.event;

import org.xmodel.Xlate;
import org.xmodel.IModelObject;
import com.stonewall.cornerstone.jms.msg.JmsMessage;
import org.xmodel.log.Log;

public class TimeEvent extends Event
{
    public static final String Tag;
    static final Log log;
    
    static {
        Tag = Type.timesync.getQualifiedName();
        log = Log.getLog(TimeEvent.class);
    }
    
    public static void send(final long tm) {
        new TimeEvent(tm).send();
    }
    
    public TimeEvent(final long tm) {
        super(TimeEvent.Tag, TimeEvent.log);
        this.root.setValue(String.valueOf(tm));
    }
    
    public TimeEvent(final JmsMessage message, final IModelObject root) throws Exception {
        super(message, root);
    }
    
    public TimeEvent(final IModelObject e) throws Exception {
        super(e, TimeEvent.log);
    }
    
    public long getTime() {
        return Xlate.get(this.root, 0L);
    }
    
    public Log getLog() {
        return TimeEvent.log;
    }
}

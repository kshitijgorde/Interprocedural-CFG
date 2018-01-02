// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.jms.msg.event;

import org.xmodel.Xlate;
import java.util.ArrayList;
import java.util.Iterator;
import org.xmodel.IModelObject;
import com.stonewall.cornerstone.jms.msg.JmsMessage;
import com.stonewall.cornerstone.security.Mutex;
import java.util.List;
import org.xmodel.log.Log;

public class SecurityEvent extends Event
{
    static final String Tag;
    static final Log log;
    
    static {
        Tag = Type.security.getQualifiedName();
        log = Log.getLog(SecurityEvent.class);
    }
    
    public static void send(final Action a, final List<Mutex> mutexes) {
        new SecurityEvent(a, null, mutexes, null).send();
    }
    
    public static void send(final Action a, final Status s, final String reason) {
        new SecurityEvent(a, s, null, reason).send();
    }
    
    public SecurityEvent(final JmsMessage message, final IModelObject root) throws Exception {
        super(message, root);
    }
    
    public SecurityEvent(final IModelObject e) {
        super(e, SecurityEvent.log);
    }
    
    public SecurityEvent(final Action a, Status s, final List<Mutex> mutexes, final String reason) {
        super(SecurityEvent.Tag, SecurityEvent.log);
        this.root.getCreateChild("evt:action").setValue(a.name());
        if (s == null) {
            s = Status.completed;
        }
        this.root.getCreateChild("evt:status").setValue(s.name());
        if (mutexes != null) {
            for (final Mutex m : mutexes) {
                this.root.addChild(m.getRoot());
            }
        }
        if (reason != null) {
            this.root.getCreateChild("evt:reason").setValue(reason);
        }
    }
    
    public List<Mutex> getMutexes() {
        final List<Mutex> l = new ArrayList<Mutex>();
        final List<IModelObject> mutexes = this.root.getChildren("en:mutex");
        for (final IModelObject mutex : mutexes) {
            l.add(new Mutex(mutex.cloneTree()));
        }
        return l;
    }
    
    @Override
    public Action getAction() {
        return Action.valueOf(Xlate.get(this.root.getFirstChild("evt:action"), (String)null));
    }
    
    @Override
    public Status getStatus() {
        return Status.valueOf(Xlate.get(this.root.getFirstChild("evt:status"), (String)null));
    }
    
    public String getReason() {
        return Xlate.get(this.root.getFirstChild("evt:reason"), (String)null);
    }
    
    public enum Action
    {
        init("init", 0), 
        insert("insert", 1), 
        remove("remove", 2), 
        lock("lock", 3), 
        permission("permission", 4);
        
        private Action(final String s, final int n) {
        }
    }
    
    public enum Status
    {
        completed("completed", 0), 
        failed("failed", 1);
        
        private Status(final String s, final int n) {
        }
    }
}

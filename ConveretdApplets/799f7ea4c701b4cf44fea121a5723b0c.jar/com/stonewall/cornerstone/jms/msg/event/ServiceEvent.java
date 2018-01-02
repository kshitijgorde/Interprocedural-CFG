// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.jms.msg.event;

import org.xmodel.Xlate;
import com.stonewall.cornerstone.jms.msg.JmsMessage;
import org.xmodel.IModelObject;
import java.util.Iterator;
import org.xmodel.Element;
import java.util.List;
import java.util.Collections;
import org.xmodel.log.Log;

public class ServiceEvent extends Event implements Cloneable
{
    public static String Tag;
    static final Log log;
    
    static {
        ServiceEvent.Tag = Type.service.getQualifiedName();
        log = Log.getLog(ServiceEvent.class);
    }
    
    public static void send(final Status s, final String reason) {
        new ServiceEvent(s, reason, null, Collections.EMPTY_LIST, Action.unknown).send();
    }
    
    public static void send(final Action action, final Status s, final Subtype type, final String id) {
        new ServiceEvent(s, null, type, Collections.singletonList(id), action).send();
    }
    
    public static void send(final Action action, final Status s, final Subtype type, final String id, final String reason) {
        new ServiceEvent(s, reason, type, Collections.singletonList(id), action).send();
    }
    
    ServiceEvent() {
        super(ServiceEvent.Tag, ServiceEvent.log);
    }
    
    public ServiceEvent(final Status s, final String reason) {
        this(s, reason, null, Collections.EMPTY_LIST, Action.unknown);
    }
    
    public ServiceEvent(final Action action, final Subtype type, final List<String> ids) {
        this(null, null, type, ids, action);
    }
    
    public ServiceEvent(final Action action, final Subtype type, final String id) {
        this(null, null, type, Collections.singletonList(id), action);
    }
    
    public ServiceEvent(final Action action, final Status s, final Subtype type, final String id) {
        this(s, null, type, Collections.singletonList(id), action);
    }
    
    public ServiceEvent(final Action action, final Status s, final Subtype type, final List<String> ids) {
        this(s, null, type, ids, action);
    }
    
    public ServiceEvent(final Status s, final String reason, final Subtype type, final List<String> ids, final Action action) {
        super(ServiceEvent.Tag, ServiceEvent.log);
        if (type != null) {
            this.setSubtype(type);
        }
        this.setStatus(s);
        if (reason != null) {
            this.setReason(reason);
        }
        for (final String id : ids) {
            final IModelObject o = new Element("evt:id");
            o.setValue(id);
            this.root.addChild(o);
        }
        this.setAction(action);
    }
    
    public ServiceEvent(final JmsMessage message, final IModelObject root) throws Exception {
        super(message, root);
    }
    
    public ServiceEvent(final IModelObject e) {
        super(e, ServiceEvent.log);
    }
    
    @Override
    public Type getType() {
        return Type.service;
    }
    
    @Override
    public Status getStatus() {
        Status status = Status.completed;
        final String s = Xlate.get(this.root.getFirstChild("evt:status"), (String)null);
        if (s != null) {
            status = Status.valueOf(s);
        }
        return status;
    }
    
    public void setStatus(final Status status) {
        if (status == null) {
            return;
        }
        final IModelObject e = this.root.getCreateChild("evt:status");
        e.setValue(status.name());
    }
    
    public String getReason() {
        return Xlate.get(this.root.getFirstChild("evt:reason"), (String)null);
    }
    
    public void setReason(final String s) {
        final IModelObject reason = this.root.getCreateChild("evt:reason");
        reason.setValue(s);
    }
    
    @Override
    public Action getAction() {
        return Action.valueOf(Xlate.get(this.root.getFirstChild("evt:action"), (String)null));
    }
    
    public void setAction(final Action value) {
        final IModelObject e = this.root.getCreateChild("evt:action");
        e.setValue(value.name());
    }
    
    public Subtype getSubtype() {
        return Subtype.valueOf(Xlate.get(this.root.getFirstChild("evt:type"), (String)null));
    }
    
    public void setSubtype(final Subtype type) {
        this.root.getCreateChild("evt:type").setValue(type.name());
    }
    
    public void addId(final String id) {
        final IModelObject o = new Element("evt:id");
        o.setValue(id);
        this.root.addChild(o);
    }
    
    public Object clone() {
        final ServiceEvent evt = new ServiceEvent(this.getRoot().cloneTree());
        evt.getRoot().setID(ServiceEvent.identityFactory.next());
        return evt;
    }
    
    public enum Action
    {
        insert("insert", 0), 
        update("update", 1), 
        delete("delete", 2), 
        preview("preview", 3), 
        deploy("deploy", 4), 
        discover("discover", 5), 
        audit("audit", 6), 
        infer("infer", 7), 
        assocNetwork("assocNetwork", 8), 
        disassocNetwork("disassocNetwork", 9), 
        baseline("baseline", 10), 
        moveDevice("moveDevice", 11), 
        repair("repair", 12), 
        reparent("reparent", 13), 
        rollback("rollback", 14), 
        run("run", 15), 
        login("login", 16), 
        logout("logout", 17), 
        unknown("unknown", 18);
        
        private Action(final String s, final int n) {
        }
    }
    
    public enum Status
    {
        started("started", 0), 
        completed("completed", 1), 
        failed("failed", 2), 
        exception("exception", 3), 
        userRequested("userRequested", 4), 
        scheduled("scheduled", 5);
        
        private Status(final String s, final int n) {
        }
    }
    
    public enum Subtype
    {
        device("device", 0, "Device"), 
        ipInterface("ipInterface", 1, "IP Interface"), 
        sitePolicy("sitePolicy", 2, "Site Policy"), 
        devicePolicy("devicePolicy", 3, "Device Policy"), 
        natPolicy("natPolicy", 4, "Nat Policy"), 
        compliancePolicy("compliancePolicy", 5, "Compliance Policy"), 
        site("site", 6, "Site"), 
        network("network", 7, "Network"), 
        user("user", 8, "User"), 
        label("label", 9, "Label"), 
        segment("segment", 10, "Segment"), 
        tunnel("tunnel", 11, "Tunnel"), 
        deviceManager("deviceManager", 12, "Device Manager"), 
        protocolServer("protocolServer", 13, "Protocol Server"), 
        ipService("ipService", 14, "IP Service"), 
        ipServiceGroup("ipServiceGroup", 15, "IP Service Group"), 
        addressGroup("addressGroup", 16, "Address Group"), 
        addressRange("addressRange", 17, "Address Range"), 
        report("report", 18, "Report"), 
        host("host", 19, "Host"), 
        unknown("unknown", 20, "Unknown"), 
        p1Proposal("p1Proposal", 21, "P1 Proposal"), 
        p2Proposal("p2Proposal", 22, "P2 Proposal"), 
        notification("notification", 23, "Notification"), 
        tag("tag", 24, "Resource Tag");
        
        private String display;
        
        private Subtype(final String s, final int n, final String display) {
            this.display = display;
        }
        
        public String getDisplayValue() {
            return this.display;
        }
    }
}

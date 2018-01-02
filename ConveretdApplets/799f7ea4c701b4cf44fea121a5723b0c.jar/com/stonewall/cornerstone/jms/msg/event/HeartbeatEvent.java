// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.jms.msg.event;

import org.xmodel.Xlate;
import com.stonewall.cornerstone.jms.msg.JmsMessage;
import org.xmodel.IModelObject;
import org.xmodel.Element;
import com.stonewall.cornerstone.component.ComponentServer;
import org.xmodel.log.Log;

public class HeartbeatEvent extends Event
{
    public static final String Tag;
    static final Log log;
    
    static {
        Tag = Type.heartbeat.getQualifiedName();
        log = Log.getLog(HeartbeatEvent.class);
    }
    
    public static void send(final ComponentServer.Type type, final String id) {
        new HeartbeatEvent(type, id).send();
    }
    
    public HeartbeatEvent(final ComponentServer.Type type, final String id) {
        super(HeartbeatEvent.Tag, HeartbeatEvent.log);
        this.setPriority(HeartbeatEvent.MaxPriority);
        final IModelObject typeElement = new Element("evt:type");
        typeElement.setValue(type.name());
        this.root.addChild(typeElement);
        final IModelObject idElement = new Element("evt:id");
        idElement.setValue(id);
        this.root.addChild(idElement);
    }
    
    public HeartbeatEvent(final JmsMessage message, final IModelObject root) throws Exception {
        super(message, root);
    }
    
    public HeartbeatEvent(final IModelObject e) throws Exception {
        super(e, HeartbeatEvent.log);
    }
    
    public ComponentServer.Type getComponentType() {
        final String s = Xlate.get(this.root.getFirstChild("evt:type"), (String)null);
        return ComponentServer.Type.valueOf(s);
    }
    
    public String getServerId() {
        return Xlate.get(this.root.getFirstChild("evt:id"), (String)null);
    }
    
    @Override
    protected Topic getTopic() {
        return new Topic(this.getType(), this.getComponentType().name());
    }
}

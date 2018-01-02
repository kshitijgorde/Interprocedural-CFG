// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.jms.msg.event;

import java.lang.reflect.Constructor;
import org.xmodel.IModelObject;
import java.util.HashMap;
import java.util.Map;

public class EventFactory
{
    private static Map<Event.Type, Class> classes;
    private static EventFactory instance;
    
    static {
        (EventFactory.classes = new HashMap<Event.Type, Class>()).put(Event.Type.alarm, AlarmEvent.class);
        EventFactory.classes.put(Event.Type.db, DbEvent.class);
        EventFactory.classes.put(Event.Type.heartbeat, HeartbeatEvent.class);
        EventFactory.classes.put(Event.Type.mail, MailEvent.class);
        EventFactory.classes.put(Event.Type.process, ProcessEvent.class);
        EventFactory.classes.put(Event.Type.security, SecurityEvent.class);
        EventFactory.classes.put(Event.Type.service, ServiceEvent.class);
        EventFactory.classes.put(Event.Type.timesync, TimeEvent.class);
    }
    
    public static EventFactory getInstance() {
        if (EventFactory.instance == null) {
            EventFactory.instance = new EventFactory();
        }
        return EventFactory.instance;
    }
    
    public Event createEvent(final IModelObject e) throws Exception {
        final Event.Type type = Event.Type.valueOf(e);
        final Class clazz = EventFactory.classes.get(type);
        final Class[] classes = { IModelObject.class };
        final Object[] params = { e };
        final Constructor con = clazz.getConstructor((Class[])classes);
        return con.newInstance(params);
    }
}

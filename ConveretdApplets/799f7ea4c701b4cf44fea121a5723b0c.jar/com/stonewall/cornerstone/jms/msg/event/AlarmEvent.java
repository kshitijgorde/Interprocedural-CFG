// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.jms.msg.event;

import java.util.Collections;
import java.util.Collection;
import org.xmodel.Xlate;
import com.stonewall.cornerstone.jms.msg.JmsMessage;
import org.xmodel.IModelObject;
import org.xmodel.Element;
import com.stonewall.cornerstone.entity.Alarm;
import com.stonewall.cornerstone.entity.EntityReference;
import org.xmodel.log.Log;

public class AlarmEvent extends Event
{
    public static String Tag;
    static final Log log;
    
    static {
        AlarmEvent.Tag = Type.alarm.getQualifiedName();
        log = Log.getLog(AlarmEvent.class);
    }
    
    public static void sendOpen(final EntityReference ref, final Alarm.Severity severity, final Alarm.Type type, final String text) {
        final Alarm alarm = new Alarm();
        alarm.open(ref, severity, type, text);
        new AlarmEvent(Action.open, alarm).send();
    }
    
    public static void sendClear(final EntityReference ref, final Alarm.Type type) {
        final Alarm alarm = new Alarm();
        alarm.clear(ref, type);
        new AlarmEvent(Action.clear, alarm).send();
    }
    
    public static void sendClearAll(final EntityReference ref) {
        final Alarm alarm = new Alarm();
        alarm.clear(ref, null);
        new AlarmEvent(Action.clearAll, alarm).send();
    }
    
    public static void send(final Alarm alarm) {
        Event e = null;
        switch (alarm.getState()) {
            case opened: {
                e = new AlarmEvent(Action.open, alarm);
                break;
            }
            case cleared: {
                e = new AlarmEvent(Action.clear, alarm);
                break;
            }
        }
        if (e != null) {
            e.send();
        }
    }
    
    public AlarmEvent(final Action action, final Alarm alarm) {
        super(AlarmEvent.Tag, AlarmEvent.log);
        final Element element = new Element("evt:action");
        element.setValue(action.name());
        this.root.addChild(element);
        this.root.addChild(alarm.getRoot().cloneTree());
    }
    
    public AlarmEvent(final JmsMessage message, final IModelObject root) throws Exception {
        super(message, root);
    }
    
    public AlarmEvent(final IModelObject e) {
        super(e, AlarmEvent.log);
    }
    
    public Alarm getAlarm() {
        return new Alarm(this.root.getFirstChild("en:alarm"));
    }
    
    public Alarm.Type getAlarmType() {
        return this.getAlarm().getType();
    }
    
    @Override
    public Action getAction() {
        return Action.valueOf(Xlate.get(this.root.getFirstChild("evt:action"), (String)null));
    }
    
    public Collection<EntityReference> getEntityReferences() {
        return Collections.singletonList(this.getAlarm().getAlarmedReference());
    }
    
    public enum Action
    {
        open("open", 0), 
        clear("clear", 1), 
        clearAll("clearAll", 2);
        
        private Action(final String s, final int n) {
        }
    }
}

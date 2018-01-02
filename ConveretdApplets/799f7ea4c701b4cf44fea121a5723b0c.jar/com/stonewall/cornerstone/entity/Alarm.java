// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.entity;

import java.text.SimpleDateFormat;
import org.xmodel.Xlate;
import com.stonewall.cornerstone.db.DbException;
import com.stonewall.cornerstone.entity.db.AlarmDbAccess;
import java.util.Date;
import org.xmodel.Element;
import org.xmodel.IModelObject;

public class Alarm extends Entity
{
    public static final String tag;
    
    static {
        tag = EntityFactory.EntityType.alarm.getQualifiedName();
    }
    
    public Alarm() {
        super(Alarm.tag);
        this.init();
    }
    
    public Alarm(final String id) {
        super(Alarm.tag, id);
    }
    
    public Alarm(final IModelObject e) {
        super(e);
    }
    
    private void init() {
        this.root.addChild(new Element("en:severity"));
        this.root.addChild(new Element("en:entity"));
        this.root.addChild(new Element("en:alarmText"));
        this.root.addChild(new Element("en:alarmType"));
        this.root.addChild(new Element("en:state"));
        this.setCreatedTs(new Date().getTime());
    }
    
    @Override
    public void insert() throws DbException {
        new AlarmDbAccess().insert(this);
    }
    
    @Override
    public void delete() throws DbException {
        new AlarmDbAccess().delete(this);
    }
    
    public void open(final EntityReference reference, final Severity severity, final Type type, final String text) {
        this.setSeverity(severity);
        this.setAlarmText(text);
        this.setState(State.opened);
        this.setType(type);
        this.setCreatedTs(new Date().getTime());
        this.setAlarmedReference(reference);
    }
    
    public void clear(final EntityReference reference, final Type type) {
        this.setAlarmedReference(reference);
        this.setState(State.cleared);
        this.setType(type);
        this.setClearedTs(new Date().getTime());
    }
    
    public EntityReference getAlarmedReference() {
        final IModelObject entity = this.root.getFirstChild("en:entity");
        final IModelObject e = entity.getChild(0);
        return new EntityReference(e);
    }
    
    public void setAlarmedReference(final EntityReference ref) {
        this.root.getCreateChild("en:entity").addChild(ref.cloneContent());
    }
    
    public State getState() {
        return State.valueOf(Xlate.get(this.root.getFirstChild("en:state"), (String)null));
    }
    
    public void setState(final String value) {
        this.setChild(this.root, "en:state", value);
    }
    
    public void setState(final State value) {
        this.setState(value.name());
    }
    
    public void setSeverity(final Severity value) {
        this.setChild(this.root, "en:severity", value.name());
    }
    
    public Severity getSeverity() {
        return Severity.valueOf(Xlate.get(this.root.getFirstChild("en:severity"), (String)null));
    }
    
    public String getAlarmText() {
        return Xlate.get(this.root.getFirstChild("en:alarmText"), (String)null);
    }
    
    public void setAlarmText(final String value) {
        this.setChild(this.root, "en:alarmText", value);
    }
    
    public void setType(final Type value) {
        if (value == null) {
            return;
        }
        this.setChild(this.root, "en:alarmType", value.name());
    }
    
    public Type getType() {
        return Type.valueOf(Xlate.get(this.root.getFirstChild("en:alarmType"), (String)null));
    }
    
    public void clear(final long timestamp) {
        this.setState(State.cleared);
        this.setClearedTs(timestamp);
    }
    
    public void setCreatedTs(final long ts) {
        final IModelObject child = this.root.getCreateChild("en:createdTs");
        child.setValue(String.valueOf(ts));
        final Date date = new Date(ts);
        final SimpleDateFormat sdf = new SimpleDateFormat();
        child.setAttribute("view", sdf.format(date));
    }
    
    public long getCreatedTs() {
        return new Long(Xlate.get(this.root.getFirstChild("en:createdTs"), (String)null));
    }
    
    public void setClearedTs(final long ts) {
        if (ts == 0L) {
            return;
        }
        final IModelObject child = this.root.getCreateChild("en:clearedTs");
        child.setValue(String.valueOf(ts));
        final Date date = new Date(ts);
        final SimpleDateFormat sdf = new SimpleDateFormat();
        child.setAttribute("view", sdf.format(date));
    }
    
    public long getClearedTs() {
        final String ts = Xlate.get(this.root.getFirstChild("en:clearedTs"), (String)null);
        if (ts == null) {
            return 0L;
        }
        return new Long(ts);
    }
    
    public boolean isClear() {
        return this.getState().equals(State.cleared);
    }
    
    public boolean isOpen() {
        return this.getState().equals(State.opened);
    }
    
    public enum Severity
    {
        none("none", 0, "None"), 
        minor("minor", 1, "Minor"), 
        major("major", 2, "Major"), 
        critical("critical", 3, "Critical");
        
        private String display;
        
        private Severity(final String s, final int n, final String display) {
            this.display = display;
        }
        
        public String getDisplayValue() {
            return this.display;
        }
    }
    
    public enum State
    {
        opened("opened", 0, "Opened"), 
        cleared("cleared", 1, "Cleared");
        
        private String display;
        
        private State(final String s, final int n, final String display) {
            this.display = display;
        }
        
        public String getDisplayValue() {
            return this.display;
        }
    }
    
    public enum Type
    {
        invalidConfig("invalidConfig", 0, "Invalid Configuration"), 
        unsupportedConfig("unsupportedConfig", 1, "Unsupported Configuration"), 
        outOfSync("outOfSync", 2, "Out of Sync"), 
        discoverFailed("discoverFailed", 3, "Discover Failed"), 
        deployFailed("deployFailed", 4, "Deploy Failed"), 
        policyCompliance("policyCompliance", 5, "Policy Compliance"), 
        heartbeat("heartbeat", 6, "Heartbeat Overdue"), 
        communication("communication", 7, "Communication");
        
        private String display;
        
        private Type(final String s, final int n, final String display) {
            this.display = display;
        }
        
        public String getDisplayValue() {
            return this.display;
        }
    }
}

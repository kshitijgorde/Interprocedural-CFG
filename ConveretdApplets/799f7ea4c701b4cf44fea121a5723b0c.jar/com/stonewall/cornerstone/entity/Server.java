// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.entity;

import com.stonewall.cornerstone.component.ComponentServer;
import com.stonewall.cornerstone.rmi.Destination;
import com.stonewall.cornerstone.db.DbException;
import com.stonewall.cornerstone.jms.msg.event.DbEvent;
import com.stonewall.cornerstone.entity.db.DeviceManagerDbAccess;
import org.xmodel.Xlate;
import org.xmodel.IModelObject;

public abstract class Server extends Entity implements AlarmableEntity
{
    public Server(final IModelObject root) {
        super(root);
    }
    
    public Server(final String tag, final String id) {
        super(tag, id);
    }
    
    public Server(final String tag) {
        super(tag);
        this.init();
    }
    
    protected void init() {
        this.setChild(this.root, "en:highestAlarmSeverity", Alarm.Severity.none.name());
        this.setChild(this.root, "en:serialNumber", "");
    }
    
    public boolean isConnected() {
        return !Alarm.Severity.valueOf(Xlate.get(this.root.getFirstChild("en:highestAlarmSeverity"), (String)null)).equals(Alarm.Severity.critical);
    }
    
    @Override
    public void updateAlarmSeverity(final Alarm.Severity severity) throws DbException {
        this.setHighestAlarmSeverity(severity);
        new DeviceManagerDbAccess().updateAlarmSeverity(this.getId(), severity);
        DbEvent.sendUpdate(this.getReference());
    }
    
    public Alarm.Severity getHighestAlarmSeverity() {
        return Alarm.Severity.valueOf(Xlate.get(this.root.getFirstChild("en:highestAlarmSeverity"), (String)null));
    }
    
    public void setHighestAlarmSeverity(final Alarm.Severity severity) {
        this.setChild(this.root, "en:highestAlarmSeverity", severity.name());
    }
    
    public Alarm.Severity getAlarmSeverity() {
        return Alarm.Severity.valueOf(Xlate.get(this.root.getFirstChild("en:highestAlarmSeverity"), (String)null));
    }
    
    public void setSerialNumber(final String value) {
        this.setChild(this.root, "en:serialNumber", value);
    }
    
    public String getSerialNumber() {
        return Xlate.get(this.root.getFirstChild("en:serialNumber"), (String)null);
    }
    
    public abstract Destination getRMIDestination();
    
    public abstract boolean isLocal(final ComponentServer p0);
}

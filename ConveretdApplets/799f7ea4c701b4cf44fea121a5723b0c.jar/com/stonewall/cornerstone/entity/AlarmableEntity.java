// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.entity;

import com.stonewall.cornerstone.db.DbException;

public interface AlarmableEntity
{
    void updateAlarmSeverity(final Alarm.Severity p0) throws DbException;
    
    EntityReference getReference();
}

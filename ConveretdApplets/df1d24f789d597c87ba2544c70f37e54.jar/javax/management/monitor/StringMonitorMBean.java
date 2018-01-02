// 
// Decompiled by Procyon v0.5.30
// 

package javax.management.monitor;

import javax.management.ObjectName;

public interface StringMonitorMBean extends MonitorMBean
{
    String getDerivedGauge();
    
    long getDerivedGaugeTimeStamp();
    
    String getDerivedGauge(final ObjectName p0);
    
    long getDerivedGaugeTimeStamp(final ObjectName p0);
    
    String getStringToCompare();
    
    void setStringToCompare(final String p0) throws IllegalArgumentException;
    
    boolean getNotifyMatch();
    
    void setNotifyMatch(final boolean p0);
    
    boolean getNotifyDiffer();
    
    void setNotifyDiffer(final boolean p0);
}

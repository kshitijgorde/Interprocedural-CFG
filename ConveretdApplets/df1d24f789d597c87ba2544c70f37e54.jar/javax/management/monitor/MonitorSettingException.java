// 
// Decompiled by Procyon v0.5.30
// 

package javax.management.monitor;

import javax.management.JMRuntimeException;

public class MonitorSettingException extends JMRuntimeException
{
    private static final long serialVersionUID = -8807913418190202007L;
    
    public MonitorSettingException() {
    }
    
    public MonitorSettingException(final String message) {
        super(message);
    }
}

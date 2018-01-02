// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.jms.msg;

import org.xmodel.IModelObject;
import org.xmodel.log.Log;

public class JMSPropertySelector extends RegistrySelector
{
    String propertyName;
    String propertyValue;
    static final Log log;
    
    static {
        log = Log.getLog(JMSPropertySelector.class);
    }
    
    public JMSPropertySelector(final String propertyName, final String propertyValue, final String methodName) {
        super(methodName);
        this.propertyName = propertyName;
        this.propertyValue = propertyValue;
    }
    
    @Override
    public boolean select(final JmsMessage message, final IModelObject content) {
        try {
            if (this.propertyValue.equals(message.getStringProperty(this.propertyName))) {
                return true;
            }
        }
        catch (Exception e) {
            JMSPropertySelector.log.error(message, e);
        }
        return false;
    }
}

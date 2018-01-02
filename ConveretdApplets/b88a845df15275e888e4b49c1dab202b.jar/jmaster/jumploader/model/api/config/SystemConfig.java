// 
// Decompiled by Procyon v0.5.30
// 

package jmaster.jumploader.model.api.config;

import java.util.MissingResourceException;
import jmaster.jumploader.model.api.B;
import jmaster.util.property.D;

public class SystemConfig
{
    private static final String A = "SystemConfig.properties";
    private D B;
    
    public SystemConfig(final B b) {
        try {
            this.B = jmaster.util.property.B.C().G("SystemConfig.properties");
        }
        catch (MissingResourceException ex) {}
    }
    
    public String toString() {
        return "properties=" + this.B + "\r\n" + "";
    }
    
    public D getProperties() {
        return this.B;
    }
    
    public void setProperties(final D b) {
        this.B = b;
    }
}

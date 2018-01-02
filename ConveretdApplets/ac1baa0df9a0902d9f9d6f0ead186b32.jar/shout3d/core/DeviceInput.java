// 
// Decompiled by Procyon v0.5.30
// 

package shout3d.core;

import java.io.Serializable;

public abstract class DeviceInput
{
    public double timeStamp;
    public int modifiers;
    public static final int SHIFT_MASK = 1;
    public static final int CTRL_MASK = 2;
    public static final int META_MASK = 4;
    public static final int ALT_MASK = 8;
    
    public DeviceInput() {
    }
    
    public DeviceInput(final double timeStamp, final int modifiers) {
        this.timeStamp = timeStamp;
        this.modifiers = modifiers;
    }
    
    public boolean isOfType(final String s) {
        Serializable s2 = this.getClass();
        while (true) {
            final String name = ((Class)s2).getName();
            final String substring = name.substring(name.lastIndexOf(".") + 1, name.length());
            if (substring.equals(s)) {
                return true;
            }
            if (substring.equals("DeviceInput")) {
                return false;
            }
            s2 = ((Class<? extends DeviceInput>)s2).getSuperclass();
        }
    }
    
    public String getTypeName() {
        final String name = this.getClass().getName();
        return name.substring(name.lastIndexOf(".") + 1, name.length());
    }
}

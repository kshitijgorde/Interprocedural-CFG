// 
// Decompiled by Procyon v0.5.30
// 

package jmaster.util.property.loader;

import jmaster.util.property.D;
import jmaster.util.property.A;

public class BytePropertyLoader implements A
{
    public Object loadProperty(final String s, final D d, final String s2, final int n) {
        return new Byte(D.A(s));
    }
    
    public Class getPropertyClass() {
        return Byte.TYPE;
    }
}

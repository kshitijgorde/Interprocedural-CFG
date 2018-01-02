// 
// Decompiled by Procyon v0.5.30
// 

package jmaster.util.property.loader;

import jmaster.util.property.D;
import jmaster.util.property.A;

public class CharPropertyLoader implements A
{
    public Object loadProperty(final String s, final D d, final String s2, final int n) {
        return new Character(s.charAt(0));
    }
    
    public Class getPropertyClass() {
        return Character.TYPE;
    }
}

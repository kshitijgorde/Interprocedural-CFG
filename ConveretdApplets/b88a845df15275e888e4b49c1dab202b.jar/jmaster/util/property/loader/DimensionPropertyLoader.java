// 
// Decompiled by Procyon v0.5.30
// 

package jmaster.util.property.loader;

import java.awt.Dimension;
import java.util.StringTokenizer;
import jmaster.util.property.D;
import jmaster.util.property.A;

public class DimensionPropertyLoader implements A
{
    static /* synthetic */ Class class$java$awt$Dimension;
    
    public Object loadProperty(final String s, final D d, final String s2, final int n) {
        final StringTokenizer stringTokenizer = new StringTokenizer(s, ",");
        return new Dimension(D.R(stringTokenizer.nextToken()), D.R(stringTokenizer.nextToken()));
    }
    
    public Class getPropertyClass() {
        return (DimensionPropertyLoader.class$java$awt$Dimension == null) ? (DimensionPropertyLoader.class$java$awt$Dimension = class$("java.awt.Dimension")) : DimensionPropertyLoader.class$java$awt$Dimension;
    }
    
    static /* synthetic */ Class class$(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError().initCause(ex);
        }
    }
}

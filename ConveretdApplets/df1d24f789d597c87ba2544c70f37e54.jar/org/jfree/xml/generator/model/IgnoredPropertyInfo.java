// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.xml.generator.model;

public class IgnoredPropertyInfo extends PropertyInfo
{
    static /* synthetic */ Class class$java$lang$Object;
    
    public IgnoredPropertyInfo(final String s) {
        super(s, (IgnoredPropertyInfo.class$java$lang$Object == null) ? (IgnoredPropertyInfo.class$java$lang$Object = class$("java.lang.Object")) : IgnoredPropertyInfo.class$java$lang$Object);
    }
    
    static /* synthetic */ Class class$(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
}

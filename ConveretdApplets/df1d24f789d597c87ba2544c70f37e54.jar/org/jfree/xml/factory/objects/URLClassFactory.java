// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.xml.factory.objects;

public class URLClassFactory extends ClassFactoryImpl
{
    static /* synthetic */ Class class$java$net$URL;
    
    public URLClassFactory() {
        this.registerClass((URLClassFactory.class$java$net$URL == null) ? (URLClassFactory.class$java$net$URL = class$("java.net.URL")) : URLClassFactory.class$java$net$URL, new URLObjectDescription());
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

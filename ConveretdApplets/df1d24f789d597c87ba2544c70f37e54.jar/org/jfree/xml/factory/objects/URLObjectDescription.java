// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.xml.factory.objects;

import org.jfree.io.IOUtils;
import org.jfree.util.Log;
import java.net.URL;

public class URLObjectDescription extends AbstractObjectDescription
{
    static /* synthetic */ Class class$java$net$URL;
    static /* synthetic */ Class class$java$lang$String;
    
    public URLObjectDescription() {
        super((URLObjectDescription.class$java$net$URL == null) ? (URLObjectDescription.class$java$net$URL = class$("java.net.URL")) : URLObjectDescription.class$java$net$URL);
        this.setParameterDefinition("value", (URLObjectDescription.class$java$lang$String == null) ? (URLObjectDescription.class$java$lang$String = class$("java.lang.String")) : URLObjectDescription.class$java$lang$String);
    }
    
    public Object createObject() {
        final String s = (String)this.getParameter("value");
        final String configProperty = this.getConfig().getConfigProperty("content-base");
        try {
            try {
                return new URL(new URL(configProperty), s);
            }
            catch (Exception ex) {
                Log.warn("BaseURL is invalid: ", ex);
                return new URL(s);
            }
        }
        catch (Exception ex2) {
            return null;
        }
    }
    
    public void setParameterFromObject(final Object o) throws ObjectFactoryException {
        if (!(o instanceof URL)) {
            throw new ObjectFactoryException("Is no instance of java.net.URL");
        }
        final URL url = (URL)o;
        final String configProperty = this.getConfig().getConfigProperty("content-base");
        try {
            this.setParameter("value", IOUtils.getInstance().createRelativeURL(url, new URL(configProperty)));
        }
        catch (Exception ex) {
            Log.warn("BaseURL is invalid: ", ex);
        }
        this.setParameter("value", url.toExternalForm());
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

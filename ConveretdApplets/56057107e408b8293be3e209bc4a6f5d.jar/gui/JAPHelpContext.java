// 
// Decompiled by Procyon v0.5.30
// 

package gui;

import java.net.URL;
import java.awt.Component;

public final class JAPHelpContext
{
    public static final String INDEX = "index";
    public static final IHelpContext INDEX_CONTEXT;
    
    public static IHelpContext createHelpContext(final String s) {
        return createHelpContext(s, null);
    }
    
    public static IHelpContext createHelpContext(final String s, final Component component) {
        return new IHelpContext() {
            public Component getHelpExtractionDisplayContext() {
                return component;
            }
            
            public String getHelpContext() {
                return s;
            }
        };
    }
    
    static {
        INDEX_CONTEXT = createHelpContext("index");
    }
    
    public interface IURLHelpContext extends IHelpContext
    {
        String getURLMessage();
        
        URL getHelpURL();
        
        String getHelpContext();
        
        Component getHelpExtractionDisplayContext();
    }
    
    public interface IHelpContext
    {
        String getHelpContext();
        
        Component getHelpExtractionDisplayContext();
    }
}

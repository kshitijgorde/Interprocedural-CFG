// 
// Decompiled by Procyon v0.5.30
// 

package javax.swing.event;

import java.net.URL;
import java.util.EventObject;

public class HyperlinkEvent extends EventObject
{
    private EventType type;
    private URL u;
    private String desc;
    
    public HyperlinkEvent(final Object o, final EventType type, final URL u) {
        super(o);
        this.type = type;
        this.u = u;
    }
    
    public HyperlinkEvent(final Object o, final EventType type, final URL u, final String desc) {
        super(o);
        this.type = type;
        this.u = u;
        this.desc = desc;
    }
    
    public String getDescription() {
        return this.desc;
    }
    
    public EventType getEventType() {
        return this.type;
    }
    
    public URL getURL() {
        return this.u;
    }
    
    public static final class EventType
    {
        public static final EventType ENTERED;
        public static final EventType EXITED;
        public static final EventType ACTIVATED;
        private String typeString;
        
        static {
            ENTERED = new EventType("ENTERED");
            EXITED = new EventType("EXITED");
            ACTIVATED = new EventType("ACTIVATED");
        }
        
        private EventType(final String typeString) {
            this.typeString = typeString;
        }
        
        public String toString() {
            return this.typeString;
        }
    }
}

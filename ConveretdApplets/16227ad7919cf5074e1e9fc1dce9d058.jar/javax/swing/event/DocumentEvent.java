// 
// Decompiled by Procyon v0.5.30
// 

package javax.swing.event;

import javax.swing.text.Document;
import javax.swing.text.Element;

public interface DocumentEvent
{
    ElementChange getChange(final Element p0);
    
    Document getDocument();
    
    int getLength();
    
    int getOffset();
    
    EventType getType();
    
    public static final class EventType
    {
        public static final EventType INSERT;
        public static final EventType REMOVE;
        public static final EventType CHANGE;
        private String typeString;
        
        static {
            INSERT = new EventType("INSERT");
            REMOVE = new EventType("REMOVE");
            CHANGE = new EventType("CHANGE");
        }
        
        private EventType(final String typeString) {
            this.typeString = typeString;
        }
        
        public String toString() {
            return this.typeString;
        }
    }
    
    public interface ElementChange
    {
        Element[] getChildrenAdded();
        
        Element[] getChildrenRemoved();
        
        Element getElement();
        
        int getIndex();
    }
}

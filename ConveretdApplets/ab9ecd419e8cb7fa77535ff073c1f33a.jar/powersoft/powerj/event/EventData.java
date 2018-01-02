// 
// Decompiled by Procyon v0.5.30
// 

package powersoft.powerj.event;

import java.util.EventObject;

public class EventData extends EventObject
{
    private transient Object _rawEvent;
    private transient boolean _handled;
    private transient int _eventID;
    
    public EventData(final Object source) {
        super(source);
        this._rawEvent = null;
        super.source = source;
    }
    
    public EventData(final Object source, final Object rawEvent) {
        super(source);
        this._rawEvent = null;
        this._rawEvent = rawEvent;
        super.source = source;
    }
    
    public void setSource(final Object source) {
        super.source = source;
    }
    
    public int getEventID() {
        return this._eventID;
    }
    
    public void setEventID(final int id) {
        this._eventID = id;
    }
    
    public Object getRawEvent() {
        return this._rawEvent;
    }
    
    public boolean getHandled() {
        return this._handled;
    }
    
    public void setHandled(final boolean handled) {
        this._handled = handled;
    }
}

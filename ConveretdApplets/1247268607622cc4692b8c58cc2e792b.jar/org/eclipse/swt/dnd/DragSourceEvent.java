// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.dnd;

import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.events.TypedEvent;

public class DragSourceEvent extends TypedEvent
{
    public int detail;
    public boolean doit;
    public int x;
    public int y;
    public TransferData dataType;
    public Image image;
    public int offsetX;
    public int offsetY;
    static final long serialVersionUID = 3257002142513770808L;
    
    public DragSourceEvent(final DNDEvent dndEvent) {
        super(dndEvent);
        this.data = dndEvent.data;
        this.detail = dndEvent.detail;
        this.doit = dndEvent.doit;
        this.dataType = dndEvent.dataType;
        this.x = dndEvent.x;
        this.y = dndEvent.y;
        this.image = dndEvent.image;
        this.offsetX = dndEvent.offsetX;
        this.offsetY = dndEvent.offsetY;
    }
    
    void updateEvent(final DNDEvent dndEvent) {
        dndEvent.widget = this.widget;
        dndEvent.time = this.time;
        dndEvent.data = this.data;
        dndEvent.detail = this.detail;
        dndEvent.doit = this.doit;
        dndEvent.dataType = this.dataType;
        dndEvent.x = this.x;
        dndEvent.y = this.y;
        dndEvent.image = this.image;
        dndEvent.offsetX = this.offsetX;
        dndEvent.offsetY = this.offsetY;
    }
    
    public String toString() {
        final String string = super.toString();
        return String.valueOf(string.substring(0, string.length() - 1)) + " operation=" + this.detail + " type=" + ((this.dataType != null) ? this.dataType.type : 0) + " doit=" + this.doit + "}";
    }
}

// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.dnd;

import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Widget;
import org.eclipse.swt.events.TypedEvent;

public class DropTargetEvent extends TypedEvent
{
    public int x;
    public int y;
    public int detail;
    public int operations;
    public int feedback;
    public Widget item;
    public TransferData currentDataType;
    public TransferData[] dataTypes;
    static final long serialVersionUID = 3256727264573338678L;
    
    public DropTargetEvent(final DNDEvent dndEvent) {
        super(dndEvent);
        this.data = dndEvent.data;
        this.x = dndEvent.x;
        this.y = dndEvent.y;
        this.detail = dndEvent.detail;
        this.currentDataType = dndEvent.dataType;
        this.dataTypes = dndEvent.dataTypes;
        this.operations = dndEvent.operations;
        this.feedback = dndEvent.feedback;
        this.item = dndEvent.item;
    }
    
    void updateEvent(final DNDEvent dndEvent) {
        dndEvent.widget = this.widget;
        dndEvent.time = this.time;
        dndEvent.data = this.data;
        dndEvent.x = this.x;
        dndEvent.y = this.y;
        dndEvent.detail = this.detail;
        dndEvent.dataType = this.currentDataType;
        dndEvent.dataTypes = this.dataTypes;
        dndEvent.operations = this.operations;
        dndEvent.feedback = this.feedback;
        dndEvent.item = this.item;
    }
    
    public String toString() {
        final String string = super.toString();
        final StringBuffer sb = new StringBuffer();
        sb.append(string.substring(0, string.length() - 1));
        sb.append(" x=");
        sb.append(this.x);
        sb.append(" y=");
        sb.append(this.y);
        sb.append(" item=");
        sb.append(this.item);
        sb.append(" operations=");
        sb.append(this.operations);
        sb.append(" operation=");
        sb.append(this.detail);
        sb.append(" feedback=");
        sb.append(this.feedback);
        sb.append(" dataTypes={ ");
        if (this.dataTypes != null) {
            for (int i = 0; i < this.dataTypes.length; ++i) {
                sb.append(this.dataTypes[i].type);
                sb.append(' ');
            }
        }
        sb.append('}');
        sb.append(" currentDataType=");
        sb.append((this.currentDataType != null) ? this.currentDataType.type : 48);
        sb.append('}');
        return sb.toString();
    }
}

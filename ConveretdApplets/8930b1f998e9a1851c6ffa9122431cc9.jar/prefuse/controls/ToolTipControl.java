// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.controls;

import prefuse.Display;
import java.awt.event.MouseEvent;
import prefuse.visual.VisualItem;

public class ToolTipControl extends ControlAdapter
{
    private String[] label;
    private StringBuffer sbuf;
    
    public ToolTipControl(final String s) {
        this(new String[] { s });
    }
    
    public ToolTipControl(final String[] label) {
        this.label = label;
        if (label.length > 1) {
            this.sbuf = new StringBuffer();
        }
    }
    
    public void itemEntered(final VisualItem visualItem, final MouseEvent mouseEvent) {
        final Display display = (Display)mouseEvent.getSource();
        if (this.label.length == 1) {
            if (visualItem.canGetString(this.label[0])) {
                display.setToolTipText(visualItem.getString(this.label[0]));
            }
        }
        else {
            this.sbuf.delete(0, this.sbuf.length());
            for (int i = 0; i < this.label.length; ++i) {
                if (visualItem.canGetString(this.label[i])) {
                    if (this.sbuf.length() > 0) {
                        this.sbuf.append("; ");
                    }
                    this.sbuf.append(visualItem.getString(this.label[i]));
                }
            }
            display.setToolTipText(this.sbuf.toString());
        }
    }
    
    public void itemExited(final VisualItem visualItem, final MouseEvent mouseEvent) {
        ((Display)mouseEvent.getSource()).setToolTipText(null);
    }
}

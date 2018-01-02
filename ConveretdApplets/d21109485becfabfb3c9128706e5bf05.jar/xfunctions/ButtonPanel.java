// 
// Decompiled by Procyon v0.5.30
// 

package xfunctions;

import xfunctions.graphs.CoordinateRect;
import java.awt.Event;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.GridLayout;
import java.awt.Button;
import java.awt.Panel;

class ButtonPanel extends Panel
{
    private GenericPanel owner;
    static final boolean HORIZANTAL = true;
    static final boolean VERTICAL = false;
    
    ButtonPanel(final GenericPanel genericPanel) {
        this(genericPanel, null, true);
    }
    
    ButtonPanel(final GenericPanel genericPanel, final Button[] array) {
        this(genericPanel, array, true);
    }
    
    ButtonPanel(final GenericPanel genericPanel, final boolean b) {
        this(genericPanel, null, b);
    }
    
    ButtonPanel(final GenericPanel owner, final Button[] array, final boolean b) {
        this.owner = owner;
        int n = 3;
        if (array != null) {
            n += array.length;
        }
        if (b) {
            this.setLayout(new GridLayout(1, n));
        }
        else {
            this.setLayout(new GridLayout(n, 1));
        }
        if (array != null) {
            for (int i = 0; i < array.length; ++i) {
                this.add(array[i]);
            }
        }
        this.add(new Button("Zoom In"));
        this.add(new Button("Zoom Out"));
        this.add(new Button("Equalize"));
    }
    
    public boolean action(final Event event, final Object o) {
        final String string = o.toString();
        if (this.owner == null || this.owner.canvas == null) {
            return true;
        }
        if (this.owner.doButtonCommand(string)) {
            return true;
        }
        final CoordinateRect coords = this.owner.canvas.getCoords();
        if (coords == null) {
            return true;
        }
        double[] values;
        if (string.equals("Zoom In")) {
            values = coords.zoomIn();
        }
        else if (string.equals("Zoom Out")) {
            values = coords.zoomOut();
        }
        else {
            if (!string.equals("Equalize")) {
                return true;
            }
            values = coords.equalizeAxes();
        }
        this.owner.canvas.invalidateCanvas();
        if (values != null && this.owner.numberInput != null) {
            this.owner.numberInput.setValues(values);
        }
        return true;
    }
}

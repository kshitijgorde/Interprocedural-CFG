// 
// Decompiled by Procyon v0.5.30
// 

package doppler;

import edu.davidson.display.Format;
import java.awt.Color;
import java.awt.Event;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Label;
import java.awt.TextField;
import java.awt.Scrollbar;
import java.awt.Panel;

public final class VarScrollBar extends Panel
{
    String caption;
    Scrollbar scrollBar;
    double value;
    double min;
    double max;
    TextField valFld;
    boolean hasChanged;
    Label captionLbl;
    
    public VarScrollBar(final String caption, final double value, final double min, final double max) {
        this.hasChanged = false;
        this.caption = caption;
        this.setLayout(new BorderLayout());
        this.value = value;
        this.min = min;
        this.max = max;
        if (this.value > this.max) {
            this.value = this.max;
        }
        if (this.value < this.min) {
            this.value = this.min;
        }
        this.scrollBar = new Scrollbar(0, (int)(100 * (this.value - this.min) / (this.max - this.min)), 4, 0, 100);
        this.add("West", this.captionLbl = new Label(caption, 0));
        this.add("Center", this.scrollBar);
        this.add("East", this.valFld = new TextField(String.valueOf("").concat(String.valueOf(value))));
    }
    
    public Insets insets() {
        int n = (this.size().height - 30) / 2;
        if (n < 0) {
            n = 0;
        }
        return new Insets(n, 2, n, 2);
    }
    
    public Dimension preferredSize() {
        return new Dimension(100, 20);
    }
    
    public Dimension minimumSize() {
        return new Dimension(40, 15);
    }
    
    public boolean keyUp(final Event event, final int n) {
        if (event.target.equals(this.valFld)) {
            this.valFld.setBackground(Color.yellow);
            return true;
        }
        return false;
    }
    
    public void setCaption(final String caption) {
        this.caption = caption;
        this.captionLbl.setText(this.caption);
    }
    
    public double getValue() {
        this.hasChanged = false;
        this.valFld.setBackground(Color.white);
        this.valFld.setText(String.valueOf("").concat(String.valueOf(this.value)));
        this.scrollBar.setValue((int)(100 * (this.value - this.min) / (this.max - this.min)));
        return this.value;
    }
    
    public void setValue(final double value) {
        this.hasChanged = false;
        this.valFld.setBackground(Color.white);
        this.value = value;
        this.valFld.setText(String.valueOf("").concat(String.valueOf(this.value)));
        this.scrollBar.setValue((int)(100 * (this.value - this.min) / (this.max - this.min)));
    }
    
    public void setMinMax(final double min, final double max) {
        this.hasChanged = false;
        this.valFld.setBackground(Color.white);
        this.min = min;
        this.max = max;
        this.valFld.setText(String.valueOf("").concat(String.valueOf(this.value)));
        this.scrollBar.setValue((int)(100 * (this.value - this.min) / (this.max - this.min)));
    }
    
    public boolean action(final Event event, final Object o) {
        if (event.target.equals(this.valFld)) {
            this.value = Format.atof((String)o);
            this.scrollBar.setValue((int)(100 * (this.value - this.min) / (this.max - this.min)));
            this.valFld.setBackground(Color.white);
            this.valFld.setText(this.valFld.getText());
            this.deliverEvent(new ChangeValueEvent(this, event, 1));
            return true;
        }
        return false;
    }
    
    public boolean handleEvent(final Event event) {
        if (event.id == 605 || event.id == 602 || event.id == 601 || event.id == 604 || event.id == 603) {
            this.value = this.min + (this.max - this.min) * this.scrollBar.getValue() / 100.0;
            this.valFld.setBackground(Color.white);
            this.valFld.setText(String.valueOf("").concat(String.valueOf(this.value)));
            this.deliverEvent(new ChangeValueEvent(this, event, 1));
            return true;
        }
        return super.handleEvent(event);
    }
}

// 
// Decompiled by Procyon v0.5.30
// 

package jagoclient.dialogs;

import java.awt.event.AdjustmentEvent;
import java.awt.Component;
import jagoclient.gui.MyLabel;
import jagoclient.gui.MyPanel;
import java.awt.LayoutManager;
import java.awt.GridLayout;
import jagoclient.gui.IntField;
import java.awt.Scrollbar;
import jagoclient.gui.DoActionListener;
import java.awt.event.AdjustmentListener;
import java.awt.Panel;

class ColorScrollbar extends Panel implements AdjustmentListener, DoActionListener
{
    public int Value;
    ColorEdit CE;
    Scrollbar SB;
    IntField L;
    
    public ColorScrollbar(final ColorEdit ce, final String s, final int value) {
        this.CE = ce;
        this.setLayout(new GridLayout(1, 0));
        this.Value = value;
        final MyPanel myPanel = new MyPanel();
        myPanel.setLayout(new GridLayout(1, 0));
        myPanel.add(new MyLabel(s));
        myPanel.add(this.L = new IntField(this, "L", this.Value, 4));
        this.add(myPanel);
        this.add(this.SB = new Scrollbar(0, value, 40, 0, 295));
        this.SB.addAdjustmentListener(this);
    }
    
    public void doAction(final String s) {
        if ("L".equals(s)) {
            this.Value = this.L.value(0, 255);
            this.SB.setValue(this.Value);
            this.CE.setcolor();
        }
    }
    
    public void itemAction(final String s, final boolean b) {
    }
    
    public void adjustmentValueChanged(final AdjustmentEvent adjustmentEvent) {
        this.Value = this.SB.getValue();
        this.L.set(this.Value);
        this.SB.setValue(this.Value);
        this.CE.setcolor();
    }
    
    public int value() {
        return this.Value;
    }
}

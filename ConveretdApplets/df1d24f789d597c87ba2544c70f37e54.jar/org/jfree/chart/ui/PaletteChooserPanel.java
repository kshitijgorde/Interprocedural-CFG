// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.chart.ui;

import java.awt.Component;
import javax.swing.ListCellRenderer;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import javax.swing.JComboBox;
import javax.swing.JPanel;

public class PaletteChooserPanel extends JPanel
{
    private JComboBox selector;
    
    public PaletteChooserPanel(final PaletteSample current, final PaletteSample[] available) {
        this.setLayout(new BorderLayout());
        (this.selector = new JComboBox((E[])available)).setSelectedItem(current);
        this.selector.setRenderer(new PaletteSample(new RainbowPalette()));
        this.add(this.selector);
    }
    
    public ColorPalette getSelectedPalette() {
        final PaletteSample sample = (PaletteSample)this.selector.getSelectedItem();
        return sample.getPalette();
    }
}

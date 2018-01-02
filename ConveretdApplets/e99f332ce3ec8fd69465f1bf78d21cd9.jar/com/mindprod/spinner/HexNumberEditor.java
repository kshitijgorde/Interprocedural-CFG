// 
// Decompiled by Procyon v0.5.30
// 

package com.mindprod.spinner;

import javax.swing.JFormattedTextField;
import javax.swing.JSpinner;

public final class HexNumberEditor extends JSpinner.NumberEditor
{
    public HexNumberEditor(final JSpinner spinner, final int width) {
        super(spinner);
        final JFormattedTextField ftf = this.getTextField();
        ftf.setEditable(true);
        ftf.setFormatterFactory(new HexNumberFormatterFactory(width));
    }
}

// 
// Decompiled by Procyon v0.5.30
// 

package com.mindprod.spinner;

import javax.swing.JFormattedTextField;
import javax.swing.JSpinner;

public final class DollarNumberEditor extends JSpinner.NumberEditor
{
    public DollarNumberEditor(final JSpinner spinner) {
        super(spinner);
        final JFormattedTextField ftf = this.getTextField();
        ftf.setEditable(true);
        ftf.setFormatterFactory(new DollarNumberFormatterFactory());
    }
}

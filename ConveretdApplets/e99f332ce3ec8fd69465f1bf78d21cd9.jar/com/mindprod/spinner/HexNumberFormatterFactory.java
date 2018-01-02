// 
// Decompiled by Procyon v0.5.30
// 

package com.mindprod.spinner;

import javax.swing.JFormattedTextField;
import javax.swing.text.DefaultFormatterFactory;

final class HexNumberFormatterFactory extends DefaultFormatterFactory
{
    public HexNumberFormatterFactory(final int width) {
        super(new HexNumberFormatter(width));
    }
}

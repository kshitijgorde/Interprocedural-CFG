// 
// Decompiled by Procyon v0.5.30
// 

package com.mindprod.spinner;

import javax.swing.JFormattedTextField;
import javax.swing.text.DefaultFormatterFactory;

final class LZNumberFormatterFactory extends DefaultFormatterFactory
{
    public LZNumberFormatterFactory(final int width) {
        super(new LZNumberFormatter(width));
    }
}

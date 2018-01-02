// 
// Decompiled by Procyon v0.5.30
// 

package com.mindprod.spinner;

import javax.swing.JFormattedTextField;
import javax.swing.text.DefaultFormatterFactory;

final class DollarNumberFormatterFactory extends DefaultFormatterFactory
{
    public DollarNumberFormatterFactory() {
        super(new DollarNumberFormatter());
    }
}

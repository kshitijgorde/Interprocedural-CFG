// 
// Decompiled by Procyon v0.5.30
// 

package com.objectbox.runner.gui;

import java.awt.Label;

public class FocusLabel extends Label
{
    public FocusLabel() {
    }
    
    public FocusLabel(final String s) {
        super(s);
    }
    
    public FocusLabel(final String s, final int n) {
        super(s, n);
    }
    
    public boolean isFocusTraversable() {
        return true;
    }
}

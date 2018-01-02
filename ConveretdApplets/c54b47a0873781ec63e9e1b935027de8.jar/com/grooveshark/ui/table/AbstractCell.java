// 
// Decompiled by Procyon v0.5.30
// 

package com.grooveshark.ui.table;

import javax.swing.JPanel;

public abstract class AbstractCell extends JPanel
{
    private static final long serialVersionUID = -809591028557464688L;
    
    public abstract void setStyle(final CellStyle p0);
    
    public abstract void setValue(final Object p0);
    
    public Object getValue() {
        return null;
    }
}

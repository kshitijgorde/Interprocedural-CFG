// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.ole.win32;

import org.eclipse.swt.widgets.Widget;

public class OleEvent
{
    public int type;
    public Widget widget;
    public int detail;
    public boolean doit;
    public Variant[] arguments;
    
    public OleEvent() {
        this.doit = true;
    }
}

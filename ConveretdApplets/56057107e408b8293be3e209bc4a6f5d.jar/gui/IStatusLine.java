// 
// Decompiled by Procyon v0.5.30
// 

package gui;

import java.awt.event.ActionListener;

public interface IStatusLine
{
    int addStatusMsg(final String p0, final int p1, final boolean p2);
    
    int addStatusMsg(final String p0, final int p1, final boolean p2, final ActionListener p3);
    
    void removeStatusMsg(final int p0);
}

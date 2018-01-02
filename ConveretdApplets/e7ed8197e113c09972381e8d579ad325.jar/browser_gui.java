import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Panel;

// 
// Decompiled by Procyon v0.5.30
// 

class browser_gui extends Panel
{
    groupboard_gui gui;
    
    browser_gui(final groupboard groupboard, final boolean b, final boolean b2, final boolean b3, final boolean b4, final boolean b5, final boolean b6, final boolean b7, final boolean b8, final boolean b9, final boolean b10, final boolean b11, final String s, final int n, final int n2, final boolean b12, final int n3, final int n4, final int n5) {
        this.setLayout(new BorderLayout(0, 0));
        this.add(this.gui = new groupboard_gui(groupboard, b, b2, b3, b4, b5, b6, b7, b8, b9, b10, b11, s, n, n2, !groupboard.is_applet, b12, n3, n4, n5));
    }
}

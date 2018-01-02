// 
// Decompiled by Procyon v0.5.30
// 

package ABLjemsty;

import ABLwidgets.menu_item;
import java.awt.Image;
import java.awt.Container;
import java.awt.Component;
import ABLwidgets.scroll;

public class jemScrollPanel extends scroll
{
    public int a;
    public int b;
    public int c;
    public int d;
    public int e;
    public int f;
    public int g;
    public int h;
    public int i;
    
    public jemScrollPanel(final Component component, final Container container, final Image image, final menu_item menu_item, final menu_item menu_item2) {
        super(component, container, image, menu_item, menu_item2);
    }
    
    public boolean a(final jemScrollPanel jemScrollPanel) {
        return super.a(jemScrollPanel) && this.a == jemScrollPanel.a && this.b == jemScrollPanel.b && this.c == jemScrollPanel.c && this.d == jemScrollPanel.d;
    }
}

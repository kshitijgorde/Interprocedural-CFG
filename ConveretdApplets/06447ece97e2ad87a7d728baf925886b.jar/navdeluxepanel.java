import java.awt.Graphics;
import java.awt.LayoutManager;
import java.awt.Panel;

// 
// Decompiled by Procyon v0.5.30
// 

public class navdeluxepanel extends Panel
{
    int menulevel;
    int column;
    navdeluxene AppParent;
    
    navdeluxepanel(final int menulevel, final navdeluxene appParent) {
        this.column = 1;
        this.AppParent = appParent;
        this.menulevel = menulevel;
        super.setLayout(null);
        super.hide();
        super.setBackground(this.AppParent.menucolor[0]);
    }
    
    public void paint(final Graphics graphics) {
        if (this.AppParent.panelborder != null) {
            graphics.setColor(this.AppParent.panelborder);
            graphics.draw3DRect(0, 0, super.size().width - 1, super.size().height - 1, true);
        }
    }
}

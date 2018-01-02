// 
// Decompiled by Procyon v0.5.30
// 

package jagoclient.board;

import java.awt.Component;
import java.awt.Panel;

class BoardCommentPanel extends Panel
{
    Component C1;
    Component C2;
    Board B;
    
    public BoardCommentPanel(final Component c1, final Component c2, final Board b) {
        this.C1 = c1;
        this.C2 = c2;
        this.B = b;
        this.add(this.C1);
        this.add(this.C2);
    }
    
    public void doLayout() {
        this.C1.setSize(this.getSize().width, this.getSize().height);
        this.C1.doLayout();
        this.C1.setSize(this.B.getSize().height, this.getSize().height);
        this.C1.setLocation(0, 0);
        this.C2.setSize(this.getSize().width - this.B.getSize().height, this.getSize().height);
        this.C2.setLocation(this.B.getSize().height, 0);
        this.C1.doLayout();
        this.C2.doLayout();
    }
}

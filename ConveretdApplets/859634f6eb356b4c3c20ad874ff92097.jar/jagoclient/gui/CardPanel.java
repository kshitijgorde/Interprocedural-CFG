// 
// Decompiled by Procyon v0.5.30
// 

package jagoclient.gui;

import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Panel;

public class CardPanel extends Panel
{
    Panel P;
    Panel Bp;
    CardLayout CL;
    
    public CardPanel() {
        this.setLayout(new BorderLayout());
        (this.P = new Panel()).setLayout(this.CL = new CardLayout());
        this.add("Center", new Panel3D(this.P));
        this.Bp = new MyPanel();
        this.add("South", new Panel3D(this.Bp));
    }
    
    public void add(final Component component, final String s) {
        this.P.add(s, component);
        this.Bp.add(new CardPanelButton(s, this.CL, s, this.P));
    }
}

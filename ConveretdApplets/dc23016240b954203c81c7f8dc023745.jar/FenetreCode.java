import java.awt.Container;
import java.awt.Event;
import java.awt.Font;
import java.awt.Component;
import java.awt.Label;
import java.awt.GridLayout;
import util102.gui.BorderPanel;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Button;
import java.awt.TextArea;
import java.awt.Frame;

// 
// Decompiled by Procyon v0.5.30
// 

public final class FenetreCode extends Frame
{
    TextArea leCode;
    Button bouttonOK;
    boolean ouverte;
    
    FenetreCode(final String s) {
        super(s);
        this.ouverte = false;
        this.setBackground(Color.lightGray);
        this.setLayout(new BorderLayout(0, 0));
        final BorderPanel borderPanel = new BorderPanel(10, 10, 10, 10, 3);
        ((Container)borderPanel).setLayout(new BorderLayout(5, 5));
        final BorderPanel borderPanel2 = new BorderPanel(5, 5, 5, 5, 7);
        ((Container)borderPanel2).setLayout(new GridLayout(1, 1, 0, 0));
        ((Container)borderPanel2).add(new Label("Copy-paste the code", 1));
        ((Container)borderPanel).add("North", (Component)borderPanel2);
        final BorderPanel borderPanel3 = new BorderPanel(5, 5, 5, 5, 7);
        ((Container)borderPanel3).setLayout(new GridLayout(1, 1, 0, 0));
        (this.leCode = new TextArea(20, 60)).setFont(new Font("Courier", 0, 12));
        this.leCode.setBackground(Color.white);
        ((Container)borderPanel3).add(this.leCode);
        ((Container)borderPanel).add("Center", (Component)borderPanel3);
        final BorderPanel borderPanel4 = new BorderPanel(5, 5, 5, 5, 7);
        ((Container)borderPanel4).setLayout(new GridLayout(1, 1, 0, 0));
        ((Container)borderPanel4).add(this.bouttonOK = new Button("Ok"));
        ((Container)borderPanel).add("South", (Component)borderPanel4);
        this.add("Center", (Component)borderPanel);
        this.validate();
        this.pack();
    }
    
    public final boolean action(final Event event, final Object o) {
        if (event.target instanceof Button) {
            this.ouverte = false;
            this.hide();
            return true;
        }
        return false;
    }
}

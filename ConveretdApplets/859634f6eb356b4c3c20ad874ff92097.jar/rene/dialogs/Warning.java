// 
// Decompiled by Procyon v0.5.30
// 

package rene.dialogs;

import rene.gui.DoActionListener;
import rene.gui.ButtonAction;
import rene.gui.Global;
import java.awt.Component;
import rene.gui.MyLabel;
import java.awt.LayoutManager;
import java.awt.FlowLayout;
import rene.gui.MyPanel;
import java.awt.Frame;
import java.awt.event.ActionListener;
import rene.gui.CloseDialog;

public class Warning extends CloseDialog implements ActionListener
{
    public boolean Result;
    Frame F;
    
    public Warning(final Frame f, final String s, final String s2, final boolean b) {
        super(f, s2, b);
        this.F = f;
        final MyPanel myPanel = new MyPanel();
        final FlowLayout layout = new FlowLayout();
        myPanel.setLayout(layout);
        layout.setAlignment(1);
        myPanel.add(new MyLabel(" " + s + " "));
        this.add("Center", myPanel);
        final MyPanel myPanel2 = new MyPanel();
        myPanel2.add(new ButtonAction(this, Global.name("close"), "Close"));
        this.add("South", myPanel2);
        this.pack();
    }
    
    public Warning(final Frame frame, final String s, final String s2) {
        this(frame, s, s2, true);
    }
}

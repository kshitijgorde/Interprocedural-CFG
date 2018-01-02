// 
// Decompiled by Procyon v0.5.30
// 

package com.daysofwonder.applet;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.WindowEvent;
import com.daysofwonder.util.t;
import java.awt.event.ActionEvent;
import java.awt.Window;
import java.awt.FlowLayout;
import java.awt.Component;
import java.awt.Font;
import java.awt.Label;
import java.awt.GridLayout;
import java.awt.Color;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.Panel;
import java.awt.Button;
import java.awt.event.ActionListener;
import java.awt.Dialog;

public class g extends Dialog implements ActionListener
{
    private Button a;
    private Panel b;
    private Frame c;
    private boolean d;
    private int e;
    private String f;
    private String[] g;
    
    public g(final Frame c, final String f, final boolean d, final String[] g, final int e) {
        super(c);
        this.c = c;
        this.f = f;
        this.d = d;
        this.g = g;
        this.e = e;
    }
    
    public void a() {
        this.setTitle(this.f);
        this.enableEvents(64L);
        this.setResizable(false);
        this.setLayout(new BorderLayout());
        this.setBackground(Color.lightGray);
        final Panel panel = new Panel();
        panel.setLayout(new GridLayout(0, 1));
        final boolean b = this.e != 0;
        for (int i = 0; i < this.g.length; ++i) {
            final Label label = new Label("  " + this.g[i] + "  ", b ? 1 : 0);
            label.setFont(new Font("Serif", 0, 13));
            panel.add(label);
        }
        (this.a = new Button("  OK  ")).setActionCommand("OK");
        this.a.addActionListener(this);
        (this.b = new Panel()).setLayout(new FlowLayout());
        this.b.add(this.a);
        this.add("Center", panel);
        this.add("South", this.b);
        a(this);
        this.setModal(true);
        this.pack();
        this.show();
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        if (actionEvent.getSource() == this.a) {
            t.a("OK button");
            this.dispose();
        }
    }
    
    protected void processWindowEvent(final WindowEvent windowEvent) {
        t.a("window event: " + windowEvent);
        super.processWindowEvent(windowEvent);
        switch (windowEvent.getID()) {
            case 201: {
                t.a("window event window closing");
                this.dispose();
                break;
            }
        }
    }
    
    public static void a(final Window window) {
        final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        if (screenSize.width < window.getSize().width) {
            window.setSize(screenSize.width, window.getSize().height);
        }
        if (screenSize.height < window.getSize().height) {
            window.setSize(window.getSize().width, screenSize.height);
        }
        window.setLocation((screenSize.width - window.getSize().width) / 2, (screenSize.height - window.getSize().height) / 2);
    }
}

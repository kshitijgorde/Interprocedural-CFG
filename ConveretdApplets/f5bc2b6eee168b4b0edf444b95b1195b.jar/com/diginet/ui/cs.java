// 
// Decompiled by Procyon v0.5.30
// 

package com.diginet.ui;

import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Event;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Font;
import java.awt.Window;

public class cs extends Window
{
    private Font a;
    private GridBagLayout b;
    private GridBagConstraints c;
    
    public final void a(final String s) {
        if (s != null && s.length() > 0) {
            final b1 b1 = new b1(s, this.a);
            this.b.setConstraints(b1, this.c);
            this.add(b1);
        }
    }
    
    public final void a(final int n, final int n2) {
        this.pack();
        this.reshape(n, n2, this.size().width, this.size().height);
        super.show();
    }
    
    public boolean handleEvent(final Event event) {
        return super.handleEvent(event);
    }
    
    public cs(final Frame frame, final Font a) {
        super(frame);
        this.b = new GridBagLayout();
        this.c = new GridBagConstraints();
        this.a = a;
        this.setBackground(Color.white);
        this.setLayout(this.b);
        this.c.insets = new Insets(4, 4, 4, 4);
        this.c.gridwidth = 0;
        this.c.weightx = 1.0;
        this.c.fill = 2;
    }
}

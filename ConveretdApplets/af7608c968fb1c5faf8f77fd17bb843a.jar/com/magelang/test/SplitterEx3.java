// 
// Decompiled by Procyon v0.5.30
// 

package com.magelang.test;

import java.awt.TextField;
import java.awt.Label;
import com.magelang.splitter.SplitterSpace;
import java.awt.GridLayout;
import com.magelang.splitter.SplitterBar;
import java.awt.Component;
import java.awt.Button;
import java.awt.LayoutManager;
import com.magelang.splitter.SplitterLayout;
import java.applet.Applet;

public class SplitterEx3 extends Applet
{
    public void init() {
        super.init();
        this.setLayout(new SplitterLayout(0));
        this.add("1", new Button("A (1)"));
        final SplitterBar splitterBar = new SplitterBar();
        splitterBar.setLayout(new GridLayout(1, 0));
        splitterBar.add(new SplitterSpace());
        splitterBar.add(new Label("Status"));
        splitterBar.add(new TextField("Enter your name"));
        splitterBar.add(new SplitterSpace());
        this.add(splitterBar);
        this.add("2", new Button("B (2)"));
        final SplitterBar splitterBar2 = new SplitterBar();
        splitterBar2.setLayout(new SplitterLayout(1));
        splitterBar2.add("5", new SplitterSpace());
        splitterBar2.add(new SplitterBar());
        splitterBar2.add("10", new Label("Status"));
        splitterBar2.add(new SplitterBar());
        splitterBar2.add("40", new TextField("Enter your name"));
        this.add(splitterBar2);
        this.add("4", new Button("C (4)"));
    }
}

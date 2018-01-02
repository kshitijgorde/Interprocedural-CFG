// 
// Decompiled by Procyon v0.5.30
// 

package com.magelang.test;

import java.awt.Component;
import java.awt.Button;
import java.awt.LayoutManager;
import com.magelang.splitter.SplitterLayout;
import java.applet.Applet;

public class SplitterEx1 extends Applet
{
    public void init() {
        super.init();
        this.setLayout(new SplitterLayout(1));
        this.add("1", new Button("A (1)"));
        this.add("2", new Button("B (2)"));
        this.add("4", new Button("C (4)"));
        this.add("8", new Button("D (8)"));
        this.add("3", new Button("E (3)"));
    }
}

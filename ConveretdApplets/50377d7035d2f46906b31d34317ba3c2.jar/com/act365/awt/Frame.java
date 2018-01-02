// 
// Decompiled by Procyon v0.5.30
// 

package com.act365.awt;

import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowListener;

public class Frame extends java.awt.Frame
{
    public Frame(final String s) {
        super(s);
        this.addWindowListener(new WindowEventHandler());
    }
    
    class WindowEventHandler extends WindowAdapter
    {
        public void windowClosing(final WindowEvent we) {
            Frame.this.dispose();
        }
    }
}

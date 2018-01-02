// 
// Decompiled by Procyon v0.5.30
// 

package edu.wise.correl;

import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import java.awt.Component;
import java.awt.event.WindowListener;
import java.awt.Frame;

public class CorrelFrame extends Frame
{
    private Cor_app applet;
    
    public CorrelFrame(final String s) {
        super(s);
        this.setResizable(false);
        this.addWindowListener(new CorrelAdapter());
        (this.applet = new Cor_app()).init();
        this.applet.start();
        this.add(this.applet);
    }
    
    class CorrelAdapter extends WindowAdapter
    {
        public void windowClosing(final WindowEvent windowEvent) {
            CorrelFrame.this.applet.stop();
            CorrelFrame.this.applet.destroy();
            System.exit(0);
        }
    }
}

// 
// Decompiled by Procyon v0.5.30
// 

package de.mmkh.tams;

import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.applet.Applet;

public class YieldApplet extends Applet
{
    YieldDemo wafer;
    YieldGUI gui;
    
    public String getAppletInfo() {
        return "VLSI chip yield demo (20030306) \n" + "(C) 2003, F.N.Hendrich, hendrich@informatik.uni-hamburg.de";
    }
    
    public void init() {
        this.wafer = new YieldDemo();
        (this.gui = new YieldGUI()).setWafer(this.wafer);
        this.setLayout(new BorderLayout());
        this.add("North", this.gui);
        this.add("South", this.wafer);
    }
    
    public void start() {
    }
    
    public void stop() {
        super.stop();
    }
}

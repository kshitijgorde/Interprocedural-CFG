// 
// Decompiled by Procyon v0.5.30
// 

package com.fluendo.player;

import java.awt.Dimension;
import java.awt.event.WindowListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Button;
import java.awt.Component;
import java.awt.TextArea;
import java.awt.Frame;
import java.awt.Dialog;

class AboutFrame extends AppFrame
{
    Dialog d;
    
    public String getRevision() {
        return "$Revision: 4059 $";
    }
    
    public AboutFrame(final CortadoPipeline pipeline) {
        super("AboutFrame");
        final Configure configure = new Configure();
        final Dist dist = new Dist();
        this.setSize(200, 100);
        (this.d = new Dialog(this, "About Cortado", false)).setVisible(true);
        final TextArea ta = new TextArea("", 8, 40, 1);
        this.d.add(ta);
        ta.append("This is Cortado " + configure.buildVersion + ".\n");
        ta.append("Brought to you by Wim Taymans.\n");
        ta.append("(C) Copyright 2004,2005,2006 Fluendo\n\n");
        ta.append("Built on " + configure.buildDate + "\n");
        ta.append("Built in " + configure.buildType + " mode.\n");
        ta.append("Built from SVN branch " + dist.branch + ", revision " + dist.revision + "\n");
        ta.append("Running on Java VM " + System.getProperty("java.version") + " from " + System.getProperty("java.vendor") + "\n");
        if (pipeline.isAudioEnabled()) {
            if (pipeline.usingJavaX) {
                ta.append("Using the javax.sound backend.");
            }
            else {
                ta.append("Using the sun.audio backend.\n\n");
                ta.append("NOTE: you should install the Java(TM) from Sun for better audio quality.");
            }
        }
        ta.append("\n\nThis version of Cortado was developed by Joseph Pellett for\n");
        ta.append("TRZ Communications - Kent, OH");
        ta.setCaretPosition(0);
        final Button dbtn;
        this.d.add(dbtn = new Button("OK"), "South");
        final Dimension dim = this.d.getPreferredSize();
        this.d.setSize(dim);
        dbtn.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                AboutFrame.this.d.setVisible(false);
            }
        });
        this.d.addWindowListener(new WindowAdapter() {
            public void windowClosing(final WindowEvent e) {
                AboutFrame.this.d.setVisible(false);
            }
        });
    }
}

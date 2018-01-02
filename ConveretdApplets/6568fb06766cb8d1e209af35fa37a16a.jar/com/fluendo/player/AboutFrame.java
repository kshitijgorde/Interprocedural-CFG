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
import com.fluendo.jst.SourceInfo;
import java.awt.Dialog;

class AboutFrame extends AppFrame
{
    private static final long serialVersionUID = 1L;
    Dialog d;
    
    public String getRevision() {
        return "$Revision$";
    }
    
    public AboutFrame(final CortadoPipeline cortadoPipeline) {
        super("AboutFrame");
        final Configure configure = new Configure();
        final SourceInfo sourceInfo = new SourceInfo();
        this.setSize(200, 100);
        (this.d = new Dialog(this, "About Cortado", false)).setVisible(true);
        final TextArea textArea = new TextArea("", 8, 40, 3);
        this.d.add(textArea);
        textArea.appendText("This is Cortado " + configure.buildVersion + ".\n");
        textArea.appendText("Brought to you by Wim Taymans.\n");
        textArea.appendText("(C) Copyright 2004,2005,2006 Fluendo\n\n");
        textArea.appendText("Built on " + configure.buildDate + "\n");
        textArea.appendText("Built in " + configure.buildType + " mode.\n");
        textArea.appendText("Built from git branch " + sourceInfo.branch + ", revision " + sourceInfo.revision + "\n");
        textArea.appendText("Running on Java VM " + System.getProperty("java.version") + " from " + System.getProperty("java.vendor") + "\n");
        if (cortadoPipeline.isAudioEnabled()) {
            if (cortadoPipeline.usingJavaX) {
                textArea.appendText("Using the javax.sound backend.");
            }
            else {
                textArea.appendText("Using the sun.audio backend.\n\n");
                textArea.appendText("NOTE: you should install the Java(TM) from Sun for better audio quality.");
            }
        }
        final Button button;
        this.d.add(button = new Button("OK"), "South");
        final Dimension preferredSize;
        final Dimension size = preferredSize = this.d.getPreferredSize();
        preferredSize.height += 30;
        this.d.setSize(size);
        button.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                AboutFrame.this.d.setVisible(false);
            }
        });
        this.d.addWindowListener(new WindowAdapter() {
            public void windowClosing(final WindowEvent windowEvent) {
                AboutFrame.this.d.setVisible(false);
            }
        });
    }
}

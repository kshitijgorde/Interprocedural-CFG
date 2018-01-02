// 
// Decompiled by Procyon v0.5.30
// 

package com.mindprod.common11;

import java.awt.Component;
import java.awt.event.WindowListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import java.awt.Frame;
import java.applet.Applet;

public final class Hybrid
{
    public static void fireup(final Applet applet, final String title, final int applicationWidth, final int applicationHeight) {
        final Frame frame = new Frame(title);
        frame.setSize(applicationWidth + 16, applicationHeight + 36);
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(final WindowEvent e) {
                applet.stop();
                applet.destroy();
                System.exit(0);
            }
        });
        frame.add(applet);
        applet.init();
        frame.validate();
        frame.setVisible(true);
        applet.start();
    }
}

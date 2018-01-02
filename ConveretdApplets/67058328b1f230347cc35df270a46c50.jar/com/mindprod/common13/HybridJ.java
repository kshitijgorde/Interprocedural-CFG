// 
// Decompiled by Procyon v0.5.30
// 

package com.mindprod.common13;

import javax.swing.SwingUtilities;
import java.awt.Component;
import java.awt.event.WindowListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import javax.swing.JFrame;
import javax.swing.JApplet;

public final class HybridJ
{
    public static void fireup(final JApplet applet, final String title, final int applicationWidth, final int applicationHeight) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                JFrame.setDefaultLookAndFeelDecorated(true);
                final JFrame frame = new JFrame(title);
                frame.setResizable(true);
                frame.setUndecorated(false);
                frame.setDefaultCloseOperation(0);
                frame.setSize(applicationWidth + 22, applicationHeight + 32);
                frame.addWindowListener(new WindowAdapter() {
                    public void windowClosing(final WindowEvent e) {
                        applet.stop();
                        applet.destroy();
                        System.exit(0);
                    }
                });
                frame.getContentPane().add(applet);
                applet.init();
                frame.validate();
                frame.setVisible(true);
                applet.start();
            }
        });
    }
}

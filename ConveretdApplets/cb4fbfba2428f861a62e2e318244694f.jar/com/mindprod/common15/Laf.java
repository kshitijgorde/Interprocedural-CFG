// 
// Decompiled by Procyon v0.5.30
// 

package com.mindprod.common15;

import java.awt.Window;
import javax.swing.JFrame;
import java.awt.Component;
import javax.swing.SwingUtilities;
import java.awt.Frame;
import java.util.prefs.Preferences;
import javax.swing.LookAndFeel;
import javax.swing.AbstractButton;
import javax.swing.JMenuItem;
import javax.swing.ButtonGroup;
import javax.swing.UIManager;
import javax.swing.JRadioButtonMenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenu;

public final class Laf
{
    public static JMenu buildLookAndFeelMenu() {
        System.setProperty("swing.aatext", "true");
        final JMenu menuLAF = new JMenu("Look & Feel");
        final ActionListener changeLAFListener = new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                final JRadioButtonMenuItem rbmi = (JRadioButtonMenuItem)e.getSource();
                Laf.setLookAndFeel(rbmi.getActionCommand());
                Laf.propagateLookAndFeelChange();
                Laf.persistLookAndFeel(new UIManager.LookAndFeelInfo(rbmi.getText(), rbmi.getActionCommand()));
            }
        };
        UIManager.LookAndFeelInfo laf = getPersistedLookAndFeel();
        if (laf == null) {
            laf = getPreferredLookAndFeel();
            if (laf == null) {
                laf = getCurrentLookAndFeel();
            }
        }
        setLookAndFeel(laf.getClassName());
        final ButtonGroup bg = new ButtonGroup();
        final UIManager.LookAndFeelInfo[] arr$;
        final UIManager.LookAndFeelInfo[] installed = arr$ = UIManager.getInstalledLookAndFeels();
        for (final UIManager.LookAndFeelInfo info : arr$) {
            final JRadioButtonMenuItem rbmi = new JRadioButtonMenuItem(info.getName(), isSameLookAndFeel(info, laf));
            menuLAF.add(rbmi);
            bg.add(rbmi);
            rbmi.setActionCommand(info.getClassName());
            rbmi.addActionListener(changeLAFListener);
        }
        return menuLAF;
    }
    
    public static UIManager.LookAndFeelInfo getCurrentLookAndFeel() {
        final LookAndFeel current = UIManager.getLookAndFeel();
        final String currentLAFName = current.getName();
        final String currentLAFClassName = current.getClass().getName();
        if (currentLAFName.length() > 0) {
            return new UIManager.LookAndFeelInfo(currentLAFName, currentLAFClassName);
        }
        return null;
    }
    
    public static UIManager.LookAndFeelInfo getPersistedLookAndFeel() {
        final Preferences lafPref = Preferences.userRoot().node("/com/mindprod/common15/laf");
        final String persistedLAFName = lafPref.get("lafName", "");
        final String persistedLAFClassName = lafPref.get("lafClassName", "");
        if (persistedLAFName.length() <= 0) {
            return null;
        }
        final UIManager.LookAndFeelInfo laf = new UIManager.LookAndFeelInfo(persistedLAFName, persistedLAFClassName);
        if (isLookAndFeelSupported(laf)) {
            return laf;
        }
        return null;
    }
    
    public static UIManager.LookAndFeelInfo getPreferredLookAndFeel() {
        final String[] preferredClassNames = { "ch.randelshofer.quaqua.QuaquaLookAndFeel", "com.sun.java.swing.plaf.mac.MacLookAndFeel", "com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel", "javax.swing.plaf.metal.MetalLookAndFeel", UIManager.getSystemLookAndFeelClassName() };
        final UIManager.LookAndFeelInfo[] installed = UIManager.getInstalledLookAndFeels();
        for (final String preferredClassName : preferredClassNames) {
            for (final UIManager.LookAndFeelInfo anInstalled : installed) {
                if (preferredClassName.equals(anInstalled.getClassName())) {
                    return new UIManager.LookAndFeelInfo(anInstalled.getName(), preferredClassName);
                }
            }
        }
        return null;
    }
    
    public static boolean isLookAndFeelSupported(final UIManager.LookAndFeelInfo laf) {
        final UIManager.LookAndFeelInfo[] arr$;
        final UIManager.LookAndFeelInfo[] installed = arr$ = UIManager.getInstalledLookAndFeels();
        for (final UIManager.LookAndFeelInfo info : arr$) {
            if (isSameLookAndFeel(info, laf)) {
                return true;
            }
        }
        return false;
    }
    
    public static void persistLookAndFeel(final UIManager.LookAndFeelInfo laf) {
        final Preferences lafPref = Preferences.userRoot().node("/com/mindprod/common15/laf");
        lafPref.put("lafName", laf.getName());
        lafPref.put("lafClassName", laf.getClassName());
    }
    
    public static void propagateLookAndFeelChange() {
        final Frame[] frames = Frame.getFrames();
        if (frames.length == 0) {
            return;
        }
        for (final Frame frame : frames) {
            SwingUtilities.updateComponentTreeUI(frame);
            final Window[] arr$2;
            final Window[] windows = arr$2 = frame.getOwnedWindows();
            for (final Window window : arr$2) {
                SwingUtilities.updateComponentTreeUI(window);
            }
        }
        try {
            final JFrame outerFrame = (JFrame)frames[0];
            outerFrame.setVisible(false);
            outerFrame.dispose();
            outerFrame.setUndecorated(false);
            outerFrame.getRootPane().setWindowDecorationStyle(0);
            outerFrame.setVisible(true);
        }
        catch (ClassCastException ex) {}
    }
    
    public static void setLookAndFeel(final String lookAndFeelClassName) {
        if (UIManager.getLookAndFeel().getClass().getName().equals(lookAndFeelClassName)) {
            return;
        }
        try {
            UIManager.setLookAndFeel(lookAndFeelClassName);
        }
        catch (Exception exception) {
            System.err.println("Setting Look and Feel failed");
            System.err.println(exception.getMessage());
        }
    }
    
    private static boolean isSameLookAndFeel(final UIManager.LookAndFeelInfo a, final UIManager.LookAndFeelInfo b) {
        return a == b || (a != null && b != null && a.getName().equals(b.getName()) && a.getClassName().equals(b.getClassName()));
    }
    
    public static void main(final String[] args) {
    }
}

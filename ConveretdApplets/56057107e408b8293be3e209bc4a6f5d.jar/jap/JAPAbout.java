// 
// Decompiled by Procyon v0.5.30
// 

package jap;

import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import anon.util.ResourceLoader;
import anon.util.JAPMessages;
import gui.GUIUtils;
import java.awt.event.WindowListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import java.awt.Component;
import java.awt.Window;
import gui.JAPAboutAutoScroller;
import gui.dialog.JAPDialog;

final class JAPAbout extends JAPDialog
{
    private static final int ABOUT_DY = 173;
    private static final int ABOUT_DX = 350;
    private JAPAboutAutoScroller sp;
    
    public JAPAbout(final Window window) {
        super(window, "Info...", false);
        super.setVisible(false);
        try {
            this.init();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    private void init() {
        this.setVisible(false);
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(final WindowEvent windowEvent) {
                JAPAbout.this.OKPressed();
            }
        });
        this.setLocation(-380, -200);
        this.setSize(10, 10);
        (this.sp = new JAPAboutAutoScroller(350, 173, GUIUtils.loadImageIcon("info.gif", true, false).getImage(), 5, 62, 210, 101, new String(ResourceLoader.loadResource(JAPMessages.getString("htmlfileAbout"))))).addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                JAPAbout.this.OKPressed();
            }
        });
        this.getContentPane().setBackground(new Color(204, 204, 204));
        this.getContentPane().setLayout(null);
        this.getContentPane().add(this.sp);
        this.setVisible(true);
        this.setVisible(true);
        this.setResizable(false);
        final Insets insets = this.getInsets();
        this.setSize(350 + insets.left + insets.right, 173 + insets.bottom + insets.top);
        this.setResizable(false);
        this.setLocationCenteredOnOwner();
        this.toFront();
        this.sp.startScrolling(95);
    }
    
    private void OKPressed() {
        this.sp.stopScrolling();
        this.dispose();
    }
}

// 
// Decompiled by Procyon v0.5.30
// 

package org.chaingang.common;

import java.net.MalformedURLException;
import java.net.URL;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import java.awt.Dimension;
import java.awt.Container;
import javax.swing.Box;
import javax.swing.JButton;
import java.awt.Component;
import javax.swing.JLabel;
import java.awt.LayoutManager;
import javax.swing.BoxLayout;
import javax.swing.BorderFactory;
import java.applet.Applet;
import java.awt.event.ActionListener;
import javax.swing.JDialog;

public class AboutBox extends JDialog implements ActionListener
{
    private final String ACTION_OK = "a_ok";
    private final String ACTION_LINK = "a_link";
    Applet applet;
    String url;
    
    public AboutBox(final String s, final String s2, final String s3) {
        this(s, s2, s3, null);
    }
    
    public AboutBox(final String s, final String s2, final String url, final Applet applet) {
        this.applet = null;
        this.url = null;
        this.applet = applet;
        this.setTitle("About");
        this.setResizable(false);
        this.setModal(true);
        final Container contentPane = this.getContentPane();
        this.getRootPane().setBorder(BorderFactory.createLineBorder(contentPane.getBackground(), 5));
        contentPane.setLayout(new BoxLayout(contentPane, 1));
        final JLabel label = new JLabel(s);
        label.setAlignmentX(0.5f);
        contentPane.add(label);
        final JLabel label2 = new JLabel(s2);
        label2.setAlignmentX(0.5f);
        contentPane.add(label2);
        if (applet != null) {
            this.url = url;
            final JButton button = new JButton(url);
            button.setBorder(BorderFactory.createEmptyBorder());
            button.setAlignmentX(0.5f);
            button.setActionCommand("a_link");
            button.addActionListener(this);
            contentPane.add(button);
        }
        else {
            final JLabel label3 = new JLabel(url);
            label3.setAlignmentX(0.5f);
            contentPane.add(label3);
        }
        contentPane.add(Box.createVerticalStrut(10));
        final JButton button2 = new JButton("OK");
        button2.setAlignmentX(0.5f);
        button2.setActionCommand("a_ok");
        button2.addActionListener(this);
        contentPane.add(button2);
        this.pack();
        this.centerScreen();
    }
    
    public void centerScreen() {
        final Dimension dimension = new Dimension(this.getSize());
        final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(screenSize.width / 2 - dimension.width / 2, screenSize.height / 2 - dimension.height / 2);
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        final String actionCommand = actionEvent.getActionCommand();
        if (actionCommand.equals("a_ok")) {
            this.setVisible(false);
        }
        else if (actionCommand.equals("a_link")) {
            URL url = null;
            try {
                url = new URL(this.url);
            }
            catch (MalformedURLException ex) {}
            if (url != null) {
                try {
                    this.applet.getAppletContext().showDocument(url, "_blank");
                }
                catch (Exception ex2) {}
            }
        }
    }
}

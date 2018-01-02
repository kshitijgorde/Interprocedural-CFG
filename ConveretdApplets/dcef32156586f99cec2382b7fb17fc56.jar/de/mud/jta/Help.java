// 
// Decompiled by Procyon v0.5.30
// 

package de.mud.jta;

import java.io.BufferedReader;
import java.awt.event.WindowListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import java.io.IOException;
import javax.swing.JOptionPane;
import java.net.URL;
import java.awt.Component;
import javax.swing.JEditorPane;

public class Help
{
    public static JEditorPane helpText;
    static /* synthetic */ Class class$de$mud$jta$Help;
    
    public static void show(final Component parent, final String url) {
        final BufferedReader reader = null;
        System.err.println("Help: " + url);
        try {
            Help.helpText.setPage(((Help.class$de$mud$jta$Help == null) ? (Help.class$de$mud$jta$Help = class$("de.mud.jta.Help")) : Help.class$de$mud$jta$Help).getResource(url));
        }
        catch (IOException e) {
            try {
                Help.helpText.setPage(new URL(url));
            }
            catch (Exception ee) {
                System.err.println("unable to load help");
                JOptionPane.showMessageDialog(parent, "JTA - Telnet/SSH for the JAVA(tm) platform\r\n(c) 1996-2005 Matthias L. Jugel, Marcus Mei\u221a\u00fcner\r\n\r\n", "jta", 1);
                return;
            }
        }
        Help.helpText.setEditable(false);
        final JScrollPane scrollPane = new JScrollPane(Help.helpText, 22, 30);
        scrollPane.setSize(800, 600);
        final JFrame frame = new JFrame("HELP");
        frame.getContentPane().setLayout(new BorderLayout());
        frame.getContentPane().add("Center", scrollPane);
        final JPanel panel = new JPanel();
        final JButton close = new JButton("Close Help");
        panel.add(close);
        close.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                frame.setVisible(false);
            }
        });
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(final WindowEvent e) {
                frame.setVisible(false);
            }
        });
        frame.getContentPane().add("South", close);
        frame.setSize(800, 600);
        frame.setVisible(true);
    }
    
    static /* synthetic */ Class class$(final String x0) {
        try {
            return Class.forName(x0);
        }
        catch (ClassNotFoundException x) {
            throw new NoClassDefFoundError(x.getMessage());
        }
    }
    
    static {
        Help.helpText = new JEditorPane();
    }
}

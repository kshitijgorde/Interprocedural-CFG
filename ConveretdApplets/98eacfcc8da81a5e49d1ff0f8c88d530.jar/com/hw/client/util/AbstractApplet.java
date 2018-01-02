// 
// Decompiled by Procyon v0.5.30
// 

package com.hw.client.util;

import java.util.Hashtable;
import java.util.Enumeration;
import java.util.Properties;
import java.security.AccessControlException;
import java.security.Permission;
import java.security.AccessController;
import java.util.PropertyPermission;
import javax.swing.SwingUtilities;
import java.awt.Component;
import java.awt.Font;
import java.awt.Color;
import VT_6_1_0_11.aT;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import javax.swing.text.html.parser.ParserDelegator;
import javax.swing.JApplet;

public class AbstractApplet extends JApplet
{
    public void init() {
        new ParserDelegator();
        if (!a()) {
            a.e("applet has not been trusted");
            this.getContentPane().setLayout(new BorderLayout());
            final aT at;
            (at = new aT("<html><center>Untrusted applet. Please quit and relaunch your browser, reopen the Voice Tools and trust the certificate.</center>")).setForeground(Color.black);
            at.setBackground(Color.white);
            at.setFont(new Font("verdana", 0, 10));
            this.getContentPane().add(at, "Center");
            return;
        }
        System.setProperty("sun.net.client.defaultConnectTimeout", "60000");
        System.setProperty("sun.net.client.defaultReadTimeout", "60000");
        System.setProperty("http.keepAlive", "true");
        System.setProperty("swing.aatext", "true");
        if (a.a()) {
            a.b(d());
        }
        this.getRootPane().setDoubleBuffered(true);
        try {
            SwingUtilities.invokeLater(new b(this, 1));
        }
        catch (Exception ex) {
            a.b("unable to initializing the applet", ex);
        }
    }
    
    public void start() {
        SwingUtilities.invokeLater(new b(this, 2));
    }
    
    public void stop() {
        SwingUtilities.invokeLater(new b(this, 3));
    }
    
    public void destroy() {
        SwingUtilities.invokeLater(new b(this, 4));
    }
    
    public static boolean a() {
        try {
            AccessController.checkPermission(new PropertyPermission("user.home", "read"));
            return true;
        }
        catch (AccessControlException ex) {
            a.a("unable to read user.home property", ex);
            return false;
        }
    }
    
    public void b() {
    }
    
    public void c() {
    }
    
    private static String d() {
        final StringBuffer sb = new StringBuffer();
        final Properties properties;
        final Enumeration<Object> keys = (properties = System.getProperties()).keys();
        sb.append("=== System Properties ===\n");
        while (keys.hasMoreElements()) {
            final String s = keys.nextElement();
            sb.append("\t").append(s).append(" = ").append(((Hashtable<K, Object>)properties).get(s)).append("\n");
        }
        sb.append("=== System Properties ===\n\n");
        return sb.toString();
    }
}

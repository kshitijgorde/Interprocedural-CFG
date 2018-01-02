// 
// Decompiled by Procyon v0.5.30
// 

package com.wimba.clients.vboard;

import java.util.Iterator;
import java.util.List;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.Arrays;
import java.net.URL;
import VT_6_1_0_11.ca;
import java.awt.Component;
import java.beans.PropertyChangeEvent;
import java.awt.event.ActionEvent;
import javax.swing.filechooser.FileFilter;
import java.awt.event.WindowListener;
import java.awt.Point;
import java.awt.Container;
import javax.swing.Icon;
import java.util.Vector;
import javax.swing.JOptionPane;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JComboBox;
import java.beans.PropertyChangeListener;
import java.awt.event.ActionListener;
import javax.swing.JDialog;

public final class a extends JDialog implements ActionListener, PropertyChangeListener
{
    private JComboBox a;
    private JComboBox b;
    private JFileChooser c;
    private int d;
    private int e;
    private File f;
    private JOptionPane g;
    private String h;
    private String i;
    private boolean j;
    private static File k;
    private static String[] l;
    private static String[] m;
    private n n;
    
    private String e() {
        return com.wimba.clients.vboard.a.l[this.e];
    }
    
    public final boolean a() {
        return this.j;
    }
    
    public a(final n n, final boolean b) {
        this.j = false;
        this.setModal(true);
        this.n = n;
        this.setTitle("Export");
        this.h = n.e("btn_next");
        this.i = n.e("btn_cancel");
        final Vector<Object> vector = new Vector<Object>();
        String s;
        if (n.G()) {
            if (b) {
                s = n.e("export_msg_what_to_export");
                vector.insertElementAt(n.e("export_export_entire_podcaster"), 0);
                vector.add(n.e("export_export_selected_only"));
                (this.a = new JComboBox((Vector<E>)vector)).setSelectedIndex(1);
            }
            else {
                s = n.e("export_msg_export_entire_podcaster");
            }
        }
        else {
            s = n.e("export_msg_what_to_export");
            vector.insertElementAt(n.e("export_export_entire_board"), 0);
            vector.insertElementAt(n.e("export_export_top_level"), 1);
            if (b) {
                vector.insertElementAt(n.e("export_export_selected_only"), 2);
            }
            this.a = new JComboBox((Vector<E>)vector);
            if (b) {
                this.a.setSelectedIndex(2);
            }
        }
        final String e = n.e("export_msg_export_format");
        final Vector<String> vector2;
        (vector2 = new Vector<String>()).add(n.e("export_format_wvb"));
        vector2.add(n.e("export_format_spx"));
        vector2.add(n.e("export_format_wav"));
        vector2.add(n.e("export_format_mp3"));
        this.b = new JComboBox((Vector<E>)vector2);
        this.c = null;
        Object[] array;
        if (this.a != null) {
            array = new Object[] { s, this.a, e, this.b };
        }
        else {
            array = new Object[] { s, "\n", e, this.b };
        }
        final Object[] array2 = { this.h, this.i };
        this.setContentPane(this.g = new JOptionPane(array, 3, 0, null, array2, array2[0]));
        this.pack();
        this.setLocation(new Point(n.getLocation().x + n.getLocationOnScreen().x + (n.getSize().width - this.getSize().width) / 2, n.getLocation().y + n.getLocationOnScreen().y + (n.getSize().height - this.getSize().height) / 3));
        this.setDefaultCloseOperation(0);
        this.addWindowListener(new d(this));
        this.g.addPropertyChangeListener(this);
    }
    
    private JFileChooser f() {
        if (this.c == null) {
            (this.c = new JFileChooser()).setDialogType(1);
            this.c.setFileSelectionMode(2);
            this.c.setControlButtonsAreShown(true);
            this.c.setAcceptAllFileFilterUsed(false);
            this.c.addChoosableFileFilter(new k(com.wimba.clients.vboard.a.m[this.e], this.n.e("export_format_" + com.wimba.clients.vboard.a.l[this.e])));
            if (com.wimba.clients.vboard.a.k != null) {
                this.c.setCurrentDirectory(com.wimba.clients.vboard.a.k);
            }
        }
        return this.c;
    }
    
    public final void actionPerformed(final ActionEvent actionEvent) {
        this.g.setValue(this.h);
    }
    
    public final void propertyChange(final PropertyChangeEvent propertyChangeEvent) {
        final String propertyName = propertyChangeEvent.getPropertyName();
        if (this.isVisible() && propertyChangeEvent.getSource() == this.g && ("value".equals(propertyName) || "inputValue".equals(propertyName))) {
            final Object value;
            if ((value = this.g.getValue()) == JOptionPane.UNINITIALIZED_VALUE) {
                return;
            }
            this.g.setValue(JOptionPane.UNINITIALIZED_VALUE);
            if (this.h.equals(value)) {
                if (this.n.G()) {
                    if (this.a == null) {
                        this.d = 0;
                    }
                    else {
                        this.d = ((this.a.getSelectedIndex() == 1) ? 2 : 0);
                    }
                }
                else {
                    this.d = this.a.getSelectedIndex();
                }
                this.e = this.b.getSelectedIndex();
                this.setVisible(false);
                if (this.f().showSaveDialog(this.n) != 0) {
                    this.f = null;
                    this.j = false;
                    com.wimba.clients.vboard.a.k = this.f().getCurrentDirectory();
                    return;
                }
                this.f = this.f().getSelectedFile();
                final String a;
                if ((a = ca.a(this.f)) == null || a.length() == 0) {
                    this.f = new File(this.f.getParentFile(), this.f.getName() + this.g());
                }
                this.j = true;
                if (this.f.exists()) {
                    this.j = (0 == JOptionPane.showConfirmDialog(this.n, this.n.e("export_confirm_override_msg"), this.n.e("export_confirm_override_title"), 0));
                }
                com.wimba.clients.vboard.a.k = this.f().getCurrentDirectory();
            }
            else {
                this.f = null;
                this.j = false;
                com.wimba.clients.vboard.a.k = this.f().getCurrentDirectory();
                this.setVisible(false);
            }
        }
    }
    
    public final void b() {
        this.pack();
        this.setVisible(true);
    }
    
    private String g() {
        if (this.e().equalsIgnoreCase("wvb")) {
            return ".wvb";
        }
        if (this.e().equals("mp3")) {
            return ".zip";
        }
        if (this.e().equals("spx")) {
            return ".zip";
        }
        if (this.e().equalsIgnoreCase("wav")) {
            return ".zip";
        }
        return "";
    }
    
    public final URL c() {
        Object o = null;
        final int d = this.d;
        final String e = this.e();
        List<String> list = new Vector<String>();
        if (d == 2) {
            list = Arrays.asList(this.n.O().a());
        }
        if (com.hw.client.util.a.a()) {
            com.hw.client.util.a.b("VTDesktopPane.sendExportRequest: export: " + d + " - format: " + e + " - messageids: " + list);
        }
        final String a = this.n.a.a();
        try {
            final Iterator<String> iterator = list.iterator();
            final StringBuffer sb = new StringBuffer();
            while (iterator.hasNext()) {
                sb.append("&mid=").append(URLEncoder.encode(iterator.next(), "UTF-8"));
            }
            final StringBuffer sb2 = new StringBuffer();
            if (this.n.h != null && this.n.h.length() > 0) {
                sb2.append("&nid=").append(URLEncoder.encode(this.n.h, "UTF-8"));
            }
            else {
                sb2.append("&login=").append(URLEncoder.encode(this.n.T(), "UTF-8"));
                sb2.append("&screenName=").append(URLEncoder.encode(this.n.U(), "UTF-8"));
            }
            o = new URL(this.n.g, this.n.k + "/export?sid=" + URLEncoder.encode(a, "UTF-8") + "&rid=" + URLEncoder.encode(this.n.j, "UTF-8") + sb2.toString() + "&export=" + d + "&format=" + e + sb.toString());
        }
        catch (IOException ex) {
            com.hw.client.util.a.b("ExportProgressFrame.open: could not build URL", ex);
        }
        if (com.hw.client.util.a.a()) {
            com.hw.client.util.a.b("ExportProgressFrame.run: exportUrl=" + o);
        }
        return (URL)o;
    }
    
    public final File d() {
        final String string = this.n.p + this.g();
        final File f;
        if ((f = this.f) != null && f.isDirectory()) {
            return new File(f, string);
        }
        if (f != null && !f.isDirectory()) {
            return f;
        }
        return null;
    }
    
    static JOptionPane a(final a a) {
        return a.g;
    }
    
    static {
        a.k = null;
        a.l = new String[] { "wvb", "spx", "wav", "mp3" };
        a.m = new String[] { "wvb", "zip", "zip", "zip" };
    }
}

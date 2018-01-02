// 
// Decompiled by Procyon v0.5.30
// 

package com.wimba.clients.vboard;

import com.hw.client.util.a;
import java.util.Date;
import java.text.DateFormat;
import java.awt.Dimension;
import java.awt.Container;
import VT_6_1_0_11.ca;
import java.awt.GridBagLayout;
import com.hw.client.util.c;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Component;
import javax.swing.JScrollPane;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.Insets;
import java.awt.Color;
import VT_6_1_0_11.aP;
import java.awt.Font;
import VT_6_1_0_11.aT;
import VT_6_1_0_11.dz;
import java.util.ResourceBundle;
import VT_6_1_0_11.aH;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JPanel;

public final class h extends JPanel
{
    private JLabel a;
    private JLabel b;
    private JLabel c;
    private JTextArea d;
    private aH e;
    private ResourceBundle f;
    
    public h(final dz dz, final ResourceBundle f, final boolean b) {
        this.f = f;
        final aT at = new aT(this.a("msg_lbl_subject"), 4);
        final aT at2 = new aT(this.a("msg_lbl_date"), 4);
        final aT at3 = new aT(this.a("msg_lbl_from"), 4);
        final Font font = new Font(VT_6_1_0_11.h.a(), 0, 12);
        final Font font2 = new Font(font.getName(), 1, font.getSize());
        at.setFont(font2);
        at2.setFont(font2);
        at3.setFont(font2);
        this.a = new aT("<html>");
        this.b = new aT("<html>");
        this.c = new aT("<html>");
        this.a.setFont(font2);
        this.b.setFont(font2);
        this.c.setFont(font2);
        final JPanel j;
        (j = VT_6_1_0_11.h.j()).setBackground(VT_6_1_0_11.h.e());
        (this.d = new aP("", 1, 1, f)).setFont(font);
        this.d.setEditable(false);
        this.d.setBackground(Color.white);
        this.d.setBorder(new EmptyBorder(new Insets(5, 5, 5, 5)));
        final JScrollPane scrollPane;
        (scrollPane = new JScrollPane(this.d)).getViewport().setBackground(Color.white);
        scrollPane.setVerticalScrollBarPolicy(20);
        scrollPane.setHorizontalScrollBarPolicy(31);
        scrollPane.setBorder(null);
        this.d.setLineWrap(true);
        this.d.setWrapStyleWord(true);
        final JPanel panel;
        (panel = new JPanel(new BorderLayout())).setOpaque(false);
        panel.add(dz, "North");
        panel.add(com.hw.client.util.c.a(), "Center");
        this.setLayout(new GridBagLayout());
        if (!b) {
            final JPanel panel2;
            (panel2 = new JPanel(new GridBagLayout())).setOpaque(false);
            ca.a(panel2, at, 0, 0, 1, 1, 0.0, 0.0, 1, 0, 1, 3);
            ca.a(panel2, this.a, 1, 0, 1, 1, 1.0, 0.0, 1, 3, 1, 3);
            ca.a(panel2, at2, 0, 1, 1, 1, 0.0, 0.0, 1, 0, 1, 3);
            ca.a(panel2, this.b, 1, 1, 1, 1, 1.0, 0.0, 1, 3, 1, 3);
            ca.a(panel2, at3, 0, 2, 1, 1, 0.0, 0.0, 1, 0, 1, 3);
            ca.a(panel2, this.c, 1, 2, 1, 1, 1.0, 0.0, 1, 3, 1, 3);
            ca.a(this, panel2, 0, 0, 1, 1, 1.0, 0.0, 5, 20, 0, 5);
            ca.a(this, panel, 1, 0, 1, 1, 0.0, 0.0, 6, 0, 6, 8);
            ca.a(this, j, 0, 1, 2, 1, 1.0, 0.0, 0, 0, 0, 0);
            ca.a(this, scrollPane, 0, 2, 2, 1, 1.0, 1.0, 0, 0, 0, 0);
            this.setMinimumSize(new Dimension(200, 60));
        }
        else {
            ca.a(this, panel, 0, 0, 1, 1, 0.0, 0.0, 6, 10, 6, 10);
            ca.a(this, j, 0, 1, 1, 1, 1.0, 0.0, 0, 0, 0, 0);
            ca.a(this, scrollPane, 0, 2, 1, 1, 1.0, 1.0, 0, 0, 0, 0);
            this.setMinimumSize(new Dimension(215, 60));
        }
        this.setBackground(VT_6_1_0_11.h.c());
    }
    
    public final void a(final aH e) {
        this.e = e;
        if (e != null) {
            final String c = e.c();
            final String format = DateFormat.getDateTimeInstance(3, 3).format(new Date(e.f()));
            final String d = e.d();
            this.a.setText(c);
            this.a.setToolTipText(c);
            this.b.setText(format);
            this.b.setToolTipText(format);
            this.c.setText(d);
            this.c.setToolTipText(d);
            if (!e.h()) {
                this.d.setText("");
                return;
            }
            this.d.setText(this.a("msg_txt_loading_message"));
        }
        else {
            this.a.setText("");
            this.a.setToolTipText("");
            this.b.setText("");
            this.b.setToolTipText("");
            this.c.setText("");
            this.c.setToolTipText("");
            this.d.setText("");
        }
    }
    
    private String a(final String s) {
        return this.f.getString(s);
    }
    
    public final void a(final String s, final String text) {
        com.hw.client.util.a.b("text=" + text);
        com.hw.client.util.a.b("messageId=" + s);
        if (s != null && this.e != null && s.equals(this.e.a())) {
            com.hw.client.util.a.b("messageHeaders.messageId=" + this.e.a());
            this.d.setText(text);
        }
        else {
            this.d.setText("");
        }
        this.d.select(0, 0);
    }
}

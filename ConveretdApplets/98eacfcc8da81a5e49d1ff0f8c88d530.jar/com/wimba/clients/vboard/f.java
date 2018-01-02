// 
// Decompiled by Procyon v0.5.30
// 

package com.wimba.clients.vboard;

import com.hw.client.util.a;
import java.awt.event.ActionEvent;
import VT_6_1_0_11.au;
import VT_6_1_0_11.bj;
import javax.swing.JButton;
import javax.swing.Icon;
import com.hw.client.util.c;
import javax.swing.JPanel;
import javax.swing.event.InternalFrameListener;
import java.awt.Container;
import VT_6_1_0_11.ca;
import javax.swing.border.Border;
import javax.swing.JScrollPane;
import VT_6_1_0_11.aP;
import javax.swing.event.DocumentListener;
import VT_6_1_0_11.aV;
import java.awt.Color;
import java.awt.Font;
import VT_6_1_0_11.h;
import VT_6_1_0_11.aT;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.GridBagLayout;
import VT_6_1_0_11.aH;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import VT_6_1_0_11.bD;

public final class f extends bD implements ActionListener
{
    private JLabel a;
    private JLabel b;
    private JTextField c;
    private JTextField d;
    private JTextArea e;
    private boolean f;
    private n g;
    private aH h;
    
    public f(final n g) {
        super("");
        this.h = null;
        this.g = g;
        this.f = false;
        this.getContentPane().setLayout(new GridBagLayout());
        final JPanel a = bD.a(new GridBagLayout());
        a.add(this.f(), new GridBagConstraints(0, 0, 1, 1, 0.1, 0.0, 10, 10, new Insets(0, 0, 0, 0), 0, 0));
        final aT at = new aT(this.g.e("sim_lbl_email_address"), 4);
        final aT at2 = new aT(this.g.e("sim_lbl_enter_recipients"), 4);
        final aT at3 = new aT(this.g.e("sim_lbl_subject"), 4);
        this.b = new aT("<html>", 0);
        final Font font = new Font(VT_6_1_0_11.h.a(), 0, 12);
        final Font font2 = new Font(font.getName(), 1, font.getSize());
        at.setFont(font);
        at2.setFont(font);
        at3.setFont(font);
        this.b.setFont(font2);
        this.b.setForeground(Color.red);
        final i i = new i(this);
        (this.c = new aV(this.g.a())).setFont(font);
        this.c.getDocument().addDocumentListener(i);
        (this.d = new aV(this.g.a())).setFont(font);
        this.d.getDocument().addDocumentListener(i);
        (this.a = new aT("<html>")).setFont(font);
        (this.e = new aP("", 1, 1, this.g.a())).setFont(font);
        this.e.getDocument().addDocumentListener(i);
        this.e.setEditable(true);
        this.e.setBackground(Color.white);
        this.e.setLineWrap(true);
        this.e.setWrapStyleWord(true);
        final JScrollPane scrollPane;
        (scrollPane = new JScrollPane(this.e)).getViewport().setBackground(Color.white);
        scrollPane.setVerticalScrollBarPolicy(20);
        scrollPane.setHorizontalScrollBarPolicy(31);
        scrollPane.setBorder(null);
        final JPanel j;
        (j = VT_6_1_0_11.h.j()).setBackground(VT_6_1_0_11.h.e());
        this.d.setNextFocusableComponent(this.c);
        this.c.setNextFocusableComponent(this.e);
        this.e.setNextFocusableComponent(this.d);
        final JPanel a2;
        (a2 = bD.a(new GridBagLayout())).setBackground(VT_6_1_0_11.h.c());
        a2.setOpaque(true);
        a2.setBorder(VT_6_1_0_11.h.i());
        ca.a(a2, this.b, 0, 0, 2, 1, 0.0, 0.0, 0, 2, 0, 2);
        ca.a(a2, at, 0, 1, 1, 1, 0.0, 0.0, 10, 2, 0, 2);
        ca.a(a2, this.a, 1, 1, 1, 1, 1.0, 0.0, 10, 2, 0, 2);
        ca.a(a2, at2, 0, 2, 1, 1, 0.0, 0.0, 10, 2, 0, 2);
        ca.a(a2, this.d, 1, 2, 1, 1, 1.0, 0.0, 10, 2, 0, 2);
        ca.a(a2, at3, 0, 3, 1, 1, 0.0, 0.0, 10, 2, 10, 2);
        ca.a(a2, this.c, 1, 3, 1, 1, 1.0, 0.0, 10, 2, 10, 2);
        ca.a(a2, j, 0, 4, 2, 1, 1.0, 0.0, 0, 0, 0, 0);
        ca.a(a2, scrollPane, 0, 5, 2, 1, 1.0, 1.0, 0, 0, 0, 0);
        this.getContentPane().add(a2, new GridBagConstraints(0, 0, 1, 1, 1.0, 1.0, 10, 1, new Insets(10, 15, 10, 15), 0, 0));
        this.getContentPane().add(a, new GridBagConstraints(0, 1, 1, 1, 1.0, 0.0, 10, 1, new Insets(16, 15, 10, 15), 0, 0));
        this.setBackground(VT_6_1_0_11.h.d());
        this.addInternalFrameListener(new j(this));
    }
    
    protected final void b() {
        final JButton a;
        (a = this.a("/images/common/btn_send.png", this.g.e("btn_send"), this.g.e("btn_send"), "ACTION_EMAIL_SEND")).setDisabledIcon(com.hw.client.util.c.a("/images/common/btn_send_disabled.png"));
        a.setRolloverIcon(com.hw.client.util.c.a("/images/common/btn_send_rollover.png"));
        a.setPressedIcon(com.hw.client.util.c.a("/images/common/btn_send_pressed.png"));
        a.addActionListener(this);
    }
    
    private void a(final String text) {
        this.b.setText(text);
    }
    
    private void b(final String text) {
        this.c.setText(text);
    }
    
    public final void a(final aH h) {
        this.setTitle(this.g.e("fwd_frame_name"));
        this.h = h;
        final String t = this.g.T();
        this.a("");
        if (this.g.I()) {
            this.a.setText(t);
            this.b(this.g.e("fwd_mail_subject") + " " + h.c());
            this.f = false;
            this.setVisible(true);
            if (this.c != null && bj.e()) {
                this.d.requestFocus();
            }
        }
        else {
            this.g.F().a(this.g, this.g.e("sim_dlg_forbidden_title"), this.g.e("sim_dlg_forbidden_msg"), this.g.e("btn_ok"), "AC_CLOSE");
        }
    }
    
    public final void a() {
        this.e.setText("");
        this.d.setText("");
        this.b("");
        this.setVisible(false);
    }
    
    public final void actionPerformed(final ActionEvent actionEvent) {
        final String actionCommand = actionEvent.getActionCommand();
        this.a("");
        this.c(actionCommand);
    }
    
    private void c(final String s) {
        if (s.equals("ACTION_EMAIL_CANCEL")) {
            if (this.f) {
                this.g.F().a(this.g, this.g.e("sim_dlg_confirm_cancel_title"), this.g.e("sim_dlg_confirm_cancel_msg"), this.g.e("btn_yes"), "AC_OK_CANCEL_COMPOSE", this.g.e("btn_no"), "AC_CANCEL_CANCEL_COMPOSE");
                return;
            }
            this.a();
        }
        else {
            if (!s.equals("ACTION_EMAIL_SEND")) {
                com.hw.client.util.a.d("VBEmailFrame.actionPerformedr: unknows ac=" + s);
                return;
            }
            if (this.a.getText().trim().length() == 0) {
                this.a(this.g.e("error_invalid_email"));
                return;
            }
            if (this.d.getText().trim().length() == 0) {
                this.a(this.g.e("error_empty_recipients"));
                return;
            }
            if (!ca.b(this.d.getText().trim())) {
                this.a(this.g.e("error_invalid_recipients", this.d.getText()));
                return;
            }
            this.g.F().a(null, this.g.e("sim_dlg_send_title"), this.g.e("sim_dlg_send_msg"));
            this.g.a(this.h, this.c.getText().trim(), this.e.getText(), ca.a(this.d.getText(), ",; "));
        }
    }
    
    static void a(final f f, final String s) {
        f.c(s);
    }
    
    static boolean a(final f f, final boolean b) {
        return f.f = true;
    }
}

// 
// Decompiled by Procyon v0.5.30
// 

package com.wimba.clients.vmail;

import javax.swing.event.DocumentEvent;
import java.net.MalformedURLException;
import java.net.URL;
import java.applet.Applet;
import VT_6_1_0_11.au;
import VT_6_1_0_11.bj;
import java.awt.event.ActionEvent;
import javax.swing.text.JTextComponent;
import javax.swing.Icon;
import com.hw.client.util.c;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Component;
import javax.swing.JScrollPane;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import VT_6_1_0_11.aP;
import VT_6_1_0_11.aV;
import java.awt.Color;
import VT_6_1_0_11.aT;
import java.awt.Font;
import VT_6_1_0_11.h;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.GridBagLayout;
import javax.swing.JComponent;
import VT_6_1_0_11.ca;
import VT_6_1_0_11.l;
import javax.swing.JButton;
import VT_6_1_0_11.du;
import javax.swing.JPanel;
import java.util.Vector;
import VT_6_1_0_11.X;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.event.DocumentListener;
import java.awt.event.ActionListener;
import VT_6_1_0_11.U;

public final class a extends U implements ActionListener, DocumentListener
{
    private JLabel k;
    private JLabel l;
    private JLabel m;
    private JLabel n;
    private JLabel o;
    private JLabel p;
    private JLabel q;
    private JTextField r;
    private JTextField s;
    private JTextField t;
    private JTextField u;
    private JTextField v;
    private JTextArea w;
    private X z;
    private Vector A;
    private Vector B;
    private JPanel C;
    private du D;
    protected JButton a;
    protected JButton b;
    
    public a(final l l, final du d) {
        super(l);
        this.D = d;
        this.a("/images/common/Wlogo-W_only-32_26.png");
        this.b(this.f);
        this.g().a();
        this.B = ca.a(this.f().a("hide", "hide").toLowerCase(), ";, ");
        this.A = ca.a(this.f().a("disable", "disable").toLowerCase(), ";, ");
        (this.z = new X(this.f(), d)).g(false);
        this.z.q();
        this.n();
        this.c();
    }
    
    protected final JComponent a() {
        final JPanel panel;
        (panel = new JPanel(new GridBagLayout())).setOpaque(false);
        final GridBagConstraints gridBagConstraints = new GridBagConstraints(0, 0, 1, 1, 1.0, 1.0, 10, 1, new Insets(0, 0, 0, 0), 0, 0);
        final JPanel panel2 = panel;
        if (this.C == null) {
            final Font font = new Font(VT_6_1_0_11.h.a(), 0, 12);
            final Font font2 = new Font(font.getName(), 1, font.getSize());
            this.k = new aT(this.h.e("vmail_lbl_subject"), 4);
            this.l = new aT(this.h.e("vmail_lbl_from"), 4);
            this.m = new aT(this.h.e("vmail_lbl_to"), 4);
            this.n = new aT(this.h.e("vmail_lbl_cc"), 4);
            this.o = new aT(this.h.e("vmail_lbl_bcc"), 4);
            this.q = new aT(this.h.e("vmail_lbl_audio"), 4);
            this.p = new aT("<html>", 0);
            this.k.setFont(font);
            this.l.setFont(font);
            this.k.setFont(font);
            this.m.setFont(font);
            this.n.setFont(font);
            this.o.setFont(font);
            this.q.setFont(font);
            this.q.setVerticalAlignment(1);
            this.p.setFont(font2);
            this.p.setForeground(Color.red);
            this.s = new aV(this.h.a());
            this.s.getDocument().addDocumentListener(this);
            this.t = new aV(this.h.a());
            this.t.getDocument().addDocumentListener(this);
            this.u = new aV(this.h.a());
            this.u.getDocument().addDocumentListener(this);
            this.v = new aV(this.h.a());
            this.v.getDocument().addDocumentListener(this);
            (this.r = new aV(this.h.a())).setFont(font);
            this.r.getDocument().addDocumentListener(this);
            (this.w = new aP("", 1, 1, this.h.a())).setFont(font);
            this.w.getDocument().addDocumentListener(this);
            this.w.setEditable(true);
            this.w.setBackground(Color.white);
            this.w.setBorder(new EmptyBorder(5, 5, 5, 5));
            final JScrollPane scrollPane;
            (scrollPane = new JScrollPane(this.w)).setBorder(null);
            scrollPane.getViewport().setBackground(Color.white);
            scrollPane.setVerticalScrollBarPolicy(20);
            scrollPane.setHorizontalScrollBarPolicy(31);
            this.w.setLineWrap(true);
            this.w.setWrapStyleWord(true);
            final JPanel c;
            (c = new JPanel(new GridBagLayout())).setBackground(VT_6_1_0_11.h.c());
            c.setOpaque(true);
            c.setBorder(VT_6_1_0_11.h.i());
            int n = -1;
            final JPanel panel3;
            (panel3 = new JPanel()).setBackground(VT_6_1_0_11.h.c());
            panel3.setBorder(null);
            panel3.setMaximumSize(new Dimension(5000, 10));
            final JPanel panel4 = c;
            final JPanel panel5 = panel3;
            final int n2 = 0;
            ++n;
            ca.a(panel4, panel5, n2, 0, 2, 1, 1.0, 0.0, 0, 0, 0, 0);
            final JPanel panel6 = c;
            final JLabel p = this.p;
            final int n3 = 0;
            ++n;
            ca.a(panel6, p, n3, 1, 2, 1, 0.0, 0.0, 0, 15, 0, 2);
            if (!this.B.contains("from")) {
                final JPanel panel7 = c;
                final JLabel l = this.l;
                final int n4 = 0;
                ++n;
                ca.a(panel7, l, n4, 2, 1, 1, 0.0, 0.0, 0, 5, 6, 10);
                ca.a(c, this.s, 1, 2, 1, 1, 1.0, 0.0, 0, 0, 6, 15);
            }
            if (!this.B.contains("to")) {
                ca.a(c, this.m, 0, ++n, 1, 1, 0.0, 0.0, 0, 5, 6, 10);
                ca.a(c, this.t, 1, n, 1, 1, 1.0, 0.0, 0, 0, 6, 15);
            }
            if (!this.B.contains("cc")) {
                ca.a(c, this.n, 0, ++n, 1, 1, 0.0, 0.0, 0, 5, 6, 10);
                ca.a(c, this.u, 1, n, 1, 1, 1.0, 0.0, 0, 0, 6, 15);
            }
            if (!this.B.contains("bcc")) {
                ca.a(c, this.o, 0, ++n, 1, 1, 0.0, 0.0, 0, 5, 6, 10);
                ca.a(c, this.v, 1, n, 1, 1, 1.0, 0.0, 0, 0, 6, 15);
            }
            if (!this.B.contains("subject")) {
                ca.a(c, this.k, 0, ++n, 1, 1, 0.0, 0.0, 0, 5, 6, 10);
                ca.a(c, this.r, 1, n, 1, 1, 1.0, 0.0, 0, 0, 6, 15);
            }
            ca.a(c, this.q, 0, ++n, 1, 1, 0.0, 0.0, 0, 5, 10, 10);
            ca.a(c, this.e(), 1, n, 1, 1, 1.0, 0.0, 0, 0, 10, 15);
            final JPanel j;
            (j = VT_6_1_0_11.h.j()).setBackground(VT_6_1_0_11.h.e());
            ca.a(c, j, 0, ++n, 2, 1, 1.0, 0.0, 0, 0, 0, 0);
            ca.a(c, scrollPane, 0, ++n, 2, 1, 1.0, 1.0, 0, 0, 0, 0);
            if (this.B.contains("text")) {
                this.w.setEnabled(false);
                this.w.setEditable(false);
            }
            this.C = c;
        }
        panel2.add(this.C, gridBagConstraints);
        return panel;
    }
    
    protected final void b() {
        (this.b = VT_6_1_0_11.h.a("/images/common/btn_archives.png", this.h.e("btn_archives"), this.h.e("btn_archives_tooltip"), "ACTION_SHOW_ARCHIVE")).setRolloverIcon(com.hw.client.util.c.a("/images/common/btn_archives_rollover.png"));
        this.b.setPressedIcon(com.hw.client.util.c.a("/images/common/btn_archives_pressed.png"));
        (this.a = VT_6_1_0_11.h.a("/images/common/btn_send.png", this.h.e("btn_send"), this.h.e("btn_send_tooltip"), "ACTION_EMAIL_SEND")).setDisabledIcon(com.hw.client.util.c.a("/images/common/btn_send_disabled.png"));
        this.a.setRolloverIcon(com.hw.client.util.c.a("/images/common/btn_send_rollover.png"));
        this.a.setPressedIcon(com.hw.client.util.c.a("/images/common/btn_send_pressed.png"));
        this.a.addActionListener(this);
        this.b.addActionListener(this);
    }
    
    private void d(final String text) {
        this.p.setText(text);
        this.revalidate();
        this.repaint();
    }
    
    public final void c() {
        this.z.a(true);
        if (this.s.getText().length() == 0) {
            this.a(this.s, "from");
        }
        this.a(this.t, "to");
        this.a(this.u, "cc");
        this.a(this.v, "bcc");
        this.a(this.r, "subject");
        this.a(this.w, "text");
    }
    
    private void a(final JTextComponent textComponent, final String s) {
        textComponent.setText(this.f().a(s, s));
        if (textComponent instanceof JTextField) {
            textComponent.setEnabled(!this.A.contains(s));
            return;
        }
        textComponent.setEditable(!this.A.contains(s));
    }
    
    public final void d() {
        this.d("");
        this.e().a(true);
        this.setVisible(true);
    }
    
    public final X e() {
        if (this.z == null) {
            this.z = new X(this.f(), this.D);
        }
        return this.z;
    }
    
    public final void actionPerformed(ActionEvent a) {
        this.e().a("");
        this.d("");
        final String actionCommand;
        if ((actionCommand = a.getActionCommand()).equals("ACTION_EMAIL_SEND")) {
            final String trim = this.s.getText().trim();
            final String trim2 = this.t.getText().trim();
            final String trim3 = this.u.getText().trim();
            final String trim4 = this.v.getText().trim();
            final String trim5 = this.r.getText().trim();
            final String trim6 = this.w.getText().trim();
            this.z.p();
            if (trim5.length() == 0) {
                this.r.setText("");
                if (bj.e()) {
                    this.r.requestFocus();
                }
                this.d(this.f().e("error_empty_subject"));
            }
            else if (!ca.a(trim)) {
                this.s.selectAll();
                if (bj.e()) {
                    this.s.requestFocus();
                }
                this.d(this.f().e("error_invalid_sender", trim));
            }
            else if (!ca.b(trim2) && !trim2.equals("")) {
                this.t.selectAll();
                if (bj.e()) {
                    this.t.requestFocus();
                }
                this.d(this.f().e("error_invalid_recipients", trim2));
            }
            else if (ca.c(trim2) > 1000) {
                this.t.selectAll();
                if (bj.e()) {
                    this.t.requestFocus();
                }
                this.d(this.f().e("error_too_many_recipients", trim2));
            }
            else if (!ca.b(trim3) && !trim3.equals("")) {
                this.u.selectAll();
                if (bj.e()) {
                    this.u.requestFocus();
                }
                this.d(this.f().e("error_invalid_recipients", trim3));
            }
            else if (ca.c(trim3) > 1000) {
                this.u.selectAll();
                if (bj.e()) {
                    this.u.requestFocus();
                }
                this.d(this.f().e("error_too_many_recipients", trim3));
            }
            else if (!ca.b(trim4) && !trim4.equals("")) {
                this.v.selectAll();
                if (bj.e()) {
                    this.v.requestFocus();
                }
                this.d(this.f().e("error_invalid_recipients", trim4));
            }
            else if (ca.c(trim4) > 1000) {
                this.v.selectAll();
                if (bj.e()) {
                    this.v.requestFocus();
                }
                this.d(this.f().e("error_too_many_recipients", trim4));
            }
            else {
                if (trim2.length() != 0 || trim3.length() != 0 || trim4.length() != 0) {
                    this.f().F().a(null, this.f().e("i18n_vmail_dlg_sending_title"), this.f().e("i18n_vmail_dlg_sending_msg"));
                    this.f().a(trim, trim2, trim3, trim4, trim5, trim6);
                    return;
                }
                if (bj.e()) {
                    this.t.requestFocus();
                }
                this.d(this.f().e("error_empty_recipients"));
            }
        }
        else {
            if (actionCommand.equals("ACTION_SHOW_ARCHIVE")) {
                this.f().x();
                return;
            }
            if (!actionCommand.equals("ACTION_OPTIONS") && actionCommand.equals("ACTION_HELP")) {
                a = null;
                try {
                    a = (ActionEvent)ca.a(this.f().t, "help_page_url", "http://www.wimba.com/support.php");
                    this.f().t.getAppletContext().showDocument(new URL((String)a), "_blank");
                }
                catch (MalformedURLException ex) {
                    a.d("VmailContentPane.action: Help URL is not valid: " + (String)a);
                }
            }
        }
    }
    
    public final void insertUpdate(final DocumentEvent documentEvent) {
    }
    
    public final void removeUpdate(final DocumentEvent documentEvent) {
    }
    
    public final void changedUpdate(final DocumentEvent documentEvent) {
    }
}

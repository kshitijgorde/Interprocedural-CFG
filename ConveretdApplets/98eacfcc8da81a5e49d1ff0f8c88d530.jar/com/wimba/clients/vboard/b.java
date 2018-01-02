// 
// Decompiled by Procyon v0.5.30
// 

package com.wimba.clients.vboard;

import com.hw.client.util.a;
import java.awt.event.ActionEvent;
import VT_6_1_0_11.au;
import javax.swing.event.DocumentEvent;
import java.awt.FontMetrics;
import VT_6_1_0_11.bj;
import java.util.StringTokenizer;
import javax.swing.JButton;
import javax.swing.Icon;
import com.hw.client.util.c;
import VT_6_1_0_11.l;
import javax.swing.event.InternalFrameListener;
import java.awt.Container;
import VT_6_1_0_11.ca;
import javax.swing.border.Border;
import javax.swing.JScrollPane;
import javax.swing.text.Document;
import java.awt.Color;
import VT_6_1_0_11.aT;
import java.awt.Font;
import VT_6_1_0_11.h;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.GridBagLayout;
import java.awt.Dimension;
import VT_6_1_0_11.du;
import javax.swing.JPanel;
import VT_6_1_0_11.X;
import VT_6_1_0_11.aP;
import VT_6_1_0_11.aV;
import javax.swing.JLabel;
import VT_6_1_0_11.aH;
import javax.swing.event.DocumentListener;
import java.awt.event.ActionListener;
import VT_6_1_0_11.bD;

public final class b extends bD implements ActionListener, DocumentListener
{
    private n a;
    private boolean b;
    private aH c;
    private JLabel d;
    private JLabel e;
    private JLabel f;
    private aV g;
    private aV h;
    private aP i;
    private boolean j;
    private X k;
    private JPanel l;
    private du m;
    
    public b(final n a, final du m, final boolean j) {
        super("");
        this.b = false;
        this.setMinimumSize(new Dimension(280, 300));
        this.a = a;
        this.j = j;
        this.m = m;
        this.getContentPane().setLayout(new GridBagLayout());
        final JPanel a2 = bD.a(new GridBagLayout());
        a2.add(this.f(), new GridBagConstraints(0, 0, 1, 1, 0.1, 0.0, 10, 10, new Insets(0, 0, 0, 0), 0, 0));
        if (this.l == null) {
            final Font font = new Font(VT_6_1_0_11.h.a(), 0, 12);
            final Font font2 = new Font(font.getName(), 1, font.getSize());
            (this.d = new aT(this.a.e("comp_lbl_subject"), 4)).setFont(font);
            final aT at;
            (at = new aT(this.a.e("comp_lbl_audio"), 4)).setFont(font);
            at.setVerticalAlignment(1);
            if (this.j) {
                this.e = new aT("<html>" + this.a.e("comp_lbl_url"), 4);
                this.h = new aV("", this.a.a());
                this.e.setFont(font);
            }
            (this.f = new aT("<html>", 0)).setFont(font2);
            this.f.setForeground(Color.red);
            (this.g = new aV("", this.a.a())).setDocument(new s(this));
            this.g.setFont(font);
            this.g.getDocument().addDocumentListener(this);
            (this.i = new aP("", 1, 1, this.a.a())).setFont(font);
            this.i.getDocument().addDocumentListener(this);
            this.i.setEditable(true);
            this.i.setBackground(Color.white);
            this.i.setLineWrap(true);
            this.i.setWrapStyleWord(true);
            final JPanel i;
            (i = VT_6_1_0_11.h.j()).setBackground(VT_6_1_0_11.h.e());
            final JScrollPane scrollPane;
            (scrollPane = new JScrollPane(this.i)).getViewport().setBackground(Color.white);
            scrollPane.setVerticalScrollBarPolicy(20);
            scrollPane.setHorizontalScrollBarPolicy(31);
            scrollPane.setBorder(null);
            final JPanel a3;
            (a3 = bD.a(new GridBagLayout())).setBackground(VT_6_1_0_11.h.c());
            a3.setOpaque(true);
            a3.setBorder(VT_6_1_0_11.h.i());
            int n = -1;
            final JPanel panel = a3;
            final JLabel f = this.f;
            final int n2 = 0;
            ++n;
            ca.a(panel, f, n2, 0, 2, 1, 0.0, 0.0, 0, 2, 0, 2);
            final JPanel panel2 = a3;
            final JLabel d = this.d;
            final int n3 = 0;
            ++n;
            ca.a(panel2, d, n3, 1, 1, 1, 0.2, 0.0, 10, 2, 0, 2);
            ca.a(a3, this.g, 1, 1, 1, 1, 0.8, 0.0, 10, 2, 0, 10);
            if (this.j) {
                final JPanel panel3 = a3;
                final JLabel e = this.e;
                final int n4 = 0;
                ++n;
                ca.a(panel3, e, n4, 2, 1, 1, 0.0, 0.0, 10, 2, 0, 2);
                ca.a(a3, this.h, 1, 2, 1, 1, 1.0, 0.0, 10, 2, 0, 10);
            }
            if (!this.h()) {
                ca.a(a3, at, 0, ++n, 1, 1, 0.0, 0.0, 10, 2, 10, 2);
                ca.a(a3, this.a(), 1, n, 1, 1, 1.0, 0.0, 10, 2, 10, 10);
            }
            else {
                ca.a(a3, this.a(), 0, ++n, 2, 1, 1.0, 0.0, 10, 10, 10, 10);
            }
            ca.a(a3, i, 0, ++n, 2, 1, 1.0, 0.0, 0, 0, 0, 0);
            ca.a(a3, scrollPane, 0, ++n, 2, 1, 1.0, 1.0, 0, 0, 0, 0);
            this.l = a3;
        }
        this.getContentPane().add(this.l, new GridBagConstraints(0, 0, 1, 1, 1.0, 1.0, 10, 1, new Insets(10, 15, 0, 15), 0, 0));
        this.getContentPane().add(a2, new GridBagConstraints(0, 1, 1, 1, 1.0, 0.0, 10, 1, new Insets(16, 15, 10, 15), 0, 0));
        this.setBackground(VT_6_1_0_11.h.d());
        this.addInternalFrameListener(new g(this));
    }
    
    public final X a() {
        if (this.k == null) {
            final X k;
            (k = new X(this.a, this.m)).g(false);
            k.q();
            this.k = k;
        }
        return this.k;
    }
    
    protected final void b() {
        final JButton a;
        (a = this.a("/images/common/btn_send.png", this.a.e("btn_post"), this.a.e("btn_post_tooltip"), "ACTION_COMPOSE_SEND")).setDisabledIcon(com.hw.client.util.c.a("/images/common/btn_send_disabled.png"));
        a.setRolloverIcon(com.hw.client.util.c.a("/images/common/btn_send_rollover.png"));
        a.setPressedIcon(com.hw.client.util.c.a("/images/common/btn_send_pressed.png"));
        a.addActionListener(this);
    }
    
    private String g() {
        if (this.h == null) {
            return null;
        }
        return this.h.getText();
    }
    
    private void b(final String text) {
        this.g.setText(text);
    }
    
    private void c(final String text) {
        if (this.h == null) {
            return;
        }
        this.h.setText(text);
    }
    
    public final void a(final String text) {
        if (this.b) {
            this.i.setText(text);
        }
        else {
            final StringTokenizer stringTokenizer = new StringTokenizer(text, "\n", true);
            final FontMetrics fontMetrics = this.i.getFontMetrics(this.i.getFont());
            this.i.append("\n\n");
            while (stringTokenizer.hasMoreTokens()) {
                final String nextToken;
                if ((nextToken = stringTokenizer.nextToken()).equals("\n")) {
                    this.i.append(nextToken);
                }
                else if (nextToken.startsWith("> ")) {
                    this.i.append("> ");
                    this.i.append(nextToken);
                }
                else {
                    final StringTokenizer stringTokenizer2 = new StringTokenizer(nextToken, " ");
                    StringBuffer sb;
                    (sb = new StringBuffer()).append("> ");
                    while (stringTokenizer2.hasMoreTokens()) {
                        final String nextToken2 = stringTokenizer2.nextToken();
                        if (fontMetrics.stringWidth(sb.toString() + nextToken2 + " ") + 10 < this.i.getSize().width) {
                            sb.append(nextToken2).append(" ");
                        }
                        else {
                            sb.append("\n");
                            this.i.append(sb.toString());
                            (sb = new StringBuffer()).append("> ").append(nextToken2).append(" ");
                        }
                    }
                    this.i.append(sb.toString());
                }
            }
            this.i.append("\n");
        }
        if (bj.e()) {
            this.i.requestFocus();
        }
        this.i.getCaret().setDot(0);
    }
    
    private void d(final String text) {
        this.f.setText(text);
    }
    
    public final void insertUpdate(final DocumentEvent documentEvent) {
    }
    
    public final void removeUpdate(final DocumentEvent documentEvent) {
    }
    
    public final void changedUpdate(final DocumentEvent documentEvent) {
    }
    
    public final void c() {
        this.a().a(true);
        this.a.u();
        this.b("");
        this.i.setText("");
        this.c("");
        this.setVisible(false);
    }
    
    public final void d() {
        this.b = false;
        if (this.a.G()) {
            this.setTitle(this.a.e("comp_frame_name"));
        }
        else if (this.a.D()) {
            this.setTitle(this.a.e("comp_frame_name"));
        }
        else {
            this.setTitle(this.a.e("comp_vp_frame_name"));
            this.h.setVisible(true);
            this.e.setVisible(true);
            this.g.setVisible(true);
            this.d.setVisible(true);
        }
        this.c((aH)null);
    }
    
    public final void a(final aH ah) {
        this.b = false;
        if (this.a.D()) {
            this.setTitle(this.a.e("reply_frame_name"));
        }
        else {
            this.setTitle(this.a.e("reply_vp_frame_name"));
            this.h.setVisible(false);
            this.e.setVisible(false);
            this.g.setVisible(false);
            this.d.setVisible(false);
            this.g.setText(this.a.e("reply_vp_frame_name"));
        }
        this.c(ah);
    }
    
    private void c(final aH c) {
        this.a().a("");
        this.d("");
        if ((c == null && this.a.L()) || (c != null && this.a.M())) {
            this.a(true);
            this.c = c;
            this.setVisible(true);
            if (c == null && this.g != null && this.g.isVisible()) {
                if (bj.e()) {
                    this.g.requestFocus();
                }
            }
            else if (c != null && this.i != null && this.i.isVisible() && bj.e()) {
                this.i.requestFocus();
            }
            if (this.h()) {
                this.c("http://");
            }
            if (c != null) {
                this.b(this.a.e("comp_reply_subject") + " " + c.c());
                if (c.h()) {
                    this.a.F().a(null, this.a.e("comp_dlg_load_message_content_title"), this.a.e("comp_dlg_load_message_content_msg"));
                    this.a.c(c.a());
                }
            }
        }
        else {
            this.a.F().a(this.a, this.a.e("comp_dlg_forbidden_title"), this.a.e("comp_dlg_forbidden_msg"), this.a.e("btn_ok"), "AC_CLOSE");
        }
    }
    
    public final void b(final aH c) {
        this.setTitle(this.a.e("edit_frame_name"));
        this.d("");
        if (!this.a.D() && !this.a.G()) {
            if (c.b() == null) {
                this.h.setVisible(true);
                this.e.setVisible(true);
                this.g.setVisible(true);
                this.d.setVisible(true);
            }
            else {
                this.h.setVisible(false);
                this.e.setVisible(false);
                this.g.setVisible(false);
                this.d.setVisible(false);
            }
        }
        this.b = true;
        this.a(!this.b);
        this.c = c;
        this.b(c.c());
        this.c(c.e());
        this.i.setText("");
        this.setVisible(true);
        if (c.h()) {
            this.a.F().a(null, this.a.e("comp_dlg_load_message_content_title"), this.a.e("comp_dlg_load_message_content_msg"));
            this.a.c(c.a());
        }
    }
    
    private void a(final boolean b) {
        this.c = null;
        this.k.a(b);
    }
    
    public final void actionPerformed(final ActionEvent actionEvent) {
        this.a().a("");
        final String actionCommand = actionEvent.getActionCommand();
        this.d("");
        this.e(actionCommand);
    }
    
    private void e(final String s) {
        if (s == null) {
            return;
        }
        if (s.equals("ACTION_COMPOSE_CANCEL")) {
            if (this.i.a() || this.k.b()) {
                this.a.F().a(this.a, this.a.e("comp_dlg_confirm_cancel_title"), this.a.e("comp_dlg_confirm_cancel_msg"), this.a.e("btn_yes"), "AC_OK_CANCEL_COMPOSE", this.a.e("btn_no"), "AC_CANCEL_CANCEL_COMPOSE");
                return;
            }
            this.c();
        }
        else {
            if (!s.equals("ACTION_COMPOSE_SEND")) {
                com.hw.client.util.a.d("VBComposeFrame.actionPerformed: unknown ac=" + s);
                return;
            }
            this.a().p();
            if (this.a.G() && !this.a().b()) {
                this.d(this.a.e("error_no_audio_podcaster"));
                return;
            }
            if (this.g.getText() == null || this.g.getText().trim().length() == 0) {
                this.d(this.a.e("error_empty_subject"));
                return;
            }
            this.a.F().a(null, this.a.e("comp_dlg_send_title"), this.a.e("comp_dlg_send_msg"));
            if (this.b) {
                this.a.b(this.c.a(), this.g.getText().trim(), this.i.getText(), this.g());
                return;
            }
            this.a.a(this.c, this.g.getText().trim(), this.i.getText(), this.g());
        }
    }
    
    private boolean h() {
        return this.a.z().equals("presentation");
    }
    
    static void a(final b b, final String s) {
        b.e(s);
    }
}

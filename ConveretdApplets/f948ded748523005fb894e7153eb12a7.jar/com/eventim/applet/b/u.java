// 
// Decompiled by Procyon v0.5.30
// 

package com.eventim.applet.b;

import com.eventim.common.transfer.Reply;
import com.eventim.common.transfer.saalplan.ReCaptchaDetails;
import java.awt.GridLayout;
import java.awt.Font;
import java.awt.event.MouseListener;
import com.eventim.applet.a.i;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import java.awt.LayoutManager;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Color;
import com.eventim.applet.EventimApplet;
import javax.swing.JTextField;
import javax.swing.JLabel;

public final class u extends ag
{
    private JLabel a;
    private JLabel b;
    private JLabel c;
    private JLabel d;
    private JLabel e;
    private JLabel f;
    private JLabel g;
    private JLabel h;
    private JLabel i;
    private JTextField j;
    private JLabel k;
    private EventimApplet l;
    private JLabel m;
    
    public u(final boolean b, final Color color, final Color color2, final Color color3, final EventimApplet l) {
        super(true, true, color, color2, color3);
        this.l = l;
        this.g = new JLabel("<html><b>" + l.d().A().a("security_check") + "</b></html>");
        this.d().add(this.g);
        this.b().setBackground(color3);
        final JPanel panel;
        (panel = new JPanel(new BorderLayout())).setBackground(color3);
        if (l.d().g() != null) {
            (this.h = new JLabel(new ImageIcon(l.d().g()))).setBorder(new EmptyBorder(3, 0, 3, 0));
            panel.add(this.h, "North");
        }
        final JPanel panel2 = new JPanel(new BorderLayout());
        panel2.add(this.i = new JLabel(l.d().A().a("captcha_input_text")), "North");
        panel2.setBackground(color3);
        panel2.add(this.j = new JTextField(), "South");
        final JPanel panel3 = new JPanel(new BorderLayout());
        (this.k = new JLabel(com.eventim.applet.a.i.a("recaptcha_retry.gif", l.d()))).addMouseListener(new f(this));
        panel3.add(this.k, "East");
        (this.m = new JLabel(com.eventim.applet.a.i.a("addtocart.gif", l.d()))).addMouseListener(new g(this));
        panel3.add(this.m, "West");
        panel3.setBackground(color3);
        panel3.setBorder(new EmptyBorder(3, 0, 7, 0));
        final JPanel panel4;
        (panel4 = new JPanel(new BorderLayout())).add(panel2, "North");
        panel4.add(panel3, "South");
        panel.add(panel4, "Center");
        final JPanel panel5;
        (panel5 = new JPanel(new BorderLayout())).setBackground(color3);
        this.a = new JLabel("<html><b>" + l.d().A().a("captcha_instructions_1") + "</b></html>");
        final Font font = this.a.getFont();
        final Font font2 = new Font(font.getName(), font.getStyle(), 9);
        this.a.setFont(font2);
        this.a.setBorder(new EmptyBorder(0, 0, 2, 0));
        panel5.add(this.a, "North");
        final JPanel panel6;
        (panel6 = new JPanel(new GridLayout(0, 1))).setBackground(color3);
        (this.b = new JLabel(l.d().A().a("captcha_instructions_2"))).setFont(font2);
        panel6.add(this.b);
        (this.c = new JLabel(l.d().A().a("captcha_instructions_3"))).setFont(font2);
        panel6.add(this.c);
        (this.d = new JLabel(l.d().A().a("captcha_instructions_4"))).setFont(font2);
        panel6.add(this.d);
        (this.e = new JLabel(l.d().A().a("captcha_instructions_5"))).setFont(font2);
        panel6.add(this.e);
        (this.f = new JLabel(l.d().A().a("captcha_instructions_6"))).setFont(font2);
        panel6.add(this.f);
        panel5.add(panel6, "Center");
        panel.add(panel5, "South");
        this.b().add(panel);
        if (b) {
            this.a();
        }
    }
    
    static JTextField a(final u u) {
        return u.j;
    }
    
    static EventimApplet b(final u u) {
        return u.l;
    }
    
    public final void a() {
        final Reply f;
        if ((f = this.l.f()).ret > 0) {
            this.l.d().a((ReCaptchaDetails)f.objects[0]);
            if (this.l.d().g() != null) {
                this.h.setIcon(new ImageIcon(this.l.d().g()));
            }
            this.j.setText("");
            return;
        }
        EventimApplet.a("Unable to load new ReCAPTCHA image: " + f.text, 3);
        this.l.a(f.ret);
    }
}

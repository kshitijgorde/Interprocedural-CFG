// 
// Decompiled by Procyon v0.5.30
// 

package com.wimba.clients.voicedirect;

import java.awt.event.FocusEvent;
import javax.swing.Timer;
import VT_6_1_0_11.bj;
import java.awt.event.ActionEvent;
import java.util.StringTokenizer;
import VT_6_1_0_11.bQ;
import VT_6_1_0_11.an;
import java.awt.event.KeyListener;
import VT_6_1_0_11.cP;
import java.awt.Font;
import VT_6_1_0_11.aV;
import javax.swing.JTextField;
import java.awt.event.FocusListener;
import java.awt.event.ActionListener;

public final class j implements ActionListener, FocusListener
{
    protected JTextField a;
    private g b;
    private h c;
    
    public j(final g b) {
        this.b = b;
        (this.a = new aV(b.a())).setFont(new Font(VT_6_1_0_11.h.a(), 0, 12));
        this.a.setBorder(VT_6_1_0_11.h.g());
        this.a.addActionListener(this);
        (this.c = new h()).setBorder(VT_6_1_0_11.h.g());
        new cP(b.e("btn_send")).addActionListener(this);
    }
    
    public final JTextField a() {
        return this.a;
    }
    
    public final h b() {
        return this.c;
    }
    
    public final void a(final KeyListener keyListener) {
        this.a.addKeyListener(keyListener);
        this.c.addKeyListener(keyListener);
    }
    
    public final void a(final an an) {
        final bQ bq;
        String s;
        if ((s = (bq = (bQ)an).b()) != null && s.startsWith("i18n:")) {
            final StringTokenizer stringTokenizer;
            (stringTokenizer = new StringTokenizer(s, ":")).nextToken();
            final String nextToken = stringTokenizer.nextToken();
            String nextToken2 = null;
            String nextToken3 = null;
            if (stringTokenizer.hasMoreTokens()) {
                nextToken2 = stringTokenizer.nextToken();
            }
            if (stringTokenizer.hasMoreTokens()) {
                nextToken3 = stringTokenizer.nextToken();
            }
            s = this.b.b(nextToken, nextToken2, nextToken3);
        }
        this.c.a(bq.a(), s);
    }
    
    public final void actionPerformed(final ActionEvent actionEvent) {
        if (actionEvent.getSource() == this.a) {
            final String trim;
            if ((trim = this.a.getText().trim()).length() != 0) {
                final bQ bq = new bQ(this.b.D(), trim);
                this.a.setText("");
                this.b.a(bq);
            }
            if (bj.e()) {
                this.a.requestFocus();
            }
            return;
        }
        if (actionEvent.getSource() == null) {
            try {
                if (bj.e()) {
                    this.a.requestFocus();
                }
            }
            catch (Exception ex) {
                null.stop();
            }
        }
    }
    
    public final void focusGained(final FocusEvent focusEvent) {
    }
    
    public final void focusLost(final FocusEvent focusEvent) {
    }
}

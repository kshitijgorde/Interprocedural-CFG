// 
// Decompiled by Procyon v0.5.30
// 

package doook;

import java.awt.Color;
import java.awt.Component;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Label;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Event;
import java.awt.Checkbox;
import java.awt.TextField;
import java.awt.Panel;

public class bt extends Panel implements Runnable
{
    private al a;
    public TextField j;
    public TextField h;
    public TextField i;
    public TextField k;
    Checkbox h;
    Checkbox d;
    private be a;
    private String d;
    private String e;
    private boolean g;
    private boolean q;
    private boolean k;
    private boolean l;
    private boolean y;
    private bq a;
    
    public void c() {
        final String g = this.a.g();
        if (g != null) {
            this.j.setText(g);
            this.h.requestFocus();
        }
        else {
            this.j.setText("");
            this.j.requestFocus();
        }
        if (this.a.I != null) {
            this.i.setText(this.a.I);
        }
        else {
            this.i.setText("");
        }
        this.h.setText("");
        if (this.a.I == -999) {
            this.k.setText("");
        }
        else {
            this.k.setText(String.valueOf(this.a.I));
        }
    }
    
    public void a() {
        new Thread(this).start();
    }
    
    public boolean action(final Event event, final Object o) {
        if (event.target instanceof TextField) {
            this.a.e();
            return true;
        }
        if (event.target == this.a) {
            this.a();
            return true;
        }
        return super.action(event, o);
    }
    
    public void b() {
        this.a.b();
        this.d.setState(false);
    }
    
    public void layout() {
        super.layout();
        this.c();
    }
    
    public void run() {
        this.a.g();
        final String trim = this.j.getText().trim();
        final String text = this.i.getText();
        final String text2 = this.h.getText();
        if (trim.indexOf(44) != -1 || trim.indexOf(34) != -1) {
            this.j.requestFocus();
            this.j.selectAll();
            new E(this.a.f, aG.a("Error"), aG.a("You can not use commas or quotes in your nickname.  Please re-enter this information."), this.a).setVisible(true);
            this.b();
            return;
        }
        int int1;
        try {
            int1 = Integer.parseInt(this.k.getText());
        }
        catch (NumberFormatException ex) {
            this.k.requestFocus();
            this.k.selectAll();
            new E(this.a.f, aG.a("Error"), aG.a("The siteID you entered is invalid.  Please re-enter this information."), this.a).setVisible(true);
            this.b();
            return;
        }
        if (trim.length() == 0) {
            this.j.requestFocus();
            this.j.selectAll();
            new E(this.a.f, aG.a("Note"), aG.a("You must enter a name.  Please re-enter this information."), this.a).setVisible(true);
            this.b();
            return;
        }
        if (trim.length() > 99) {
            this.j.setText(trim.substring(0, 98));
            this.j.requestFocus();
            this.j.selectAll();
            new E(this.a.f, aG.a("Note"), aG.a("Nickname must be 99 characters or less.  Please re-enter this information"), this.a).setVisible(true);
            this.b();
            return;
        }
        if (this.q && text2.length() == 0) {
            this.h.requestFocus();
            this.h.selectAll();
            new E(this.a.f, aG.a("Note"), aG.a("You must enter a password.  Please re-enter this information."), this.a).setVisible(true);
            this.b();
            return;
        }
        if (this.q && text2.length() < 3) {
            this.h.requestFocus();
            this.h.selectAll();
            new E(this.a.f, aG.a("Note"), aG.a("Passwords must be at least three characters long.  Please re-enter this information."), this.a).setVisible(true);
            this.b();
            return;
        }
        if (this.y && !this.d.getState()) {
            new E(this.a.f, aG.a("Note"), this.d, this.a).setVisible(true);
            this.b();
            return;
        }
        M m;
        String s;
        if (this.e != null && !this.q) {
            m = new M(this.e);
            s = trim;
        }
        else if (text2.length() > 0) {
            m = new M(text2);
            s = trim;
        }
        else {
            m = null;
            s = "Guest";
        }
        this.a.I = text;
        this.a.I = int1;
        this.a.a(23, this.h.getState());
        try {
            this.a.a(trim, s, m, text, int1);
        }
        catch (Exception ex2) {
            this.b();
        }
    }
    
    public bt(final bq bq, final be be, final boolean b, final boolean b2, final boolean b3, final boolean b4, final String s, final String s2) {
        this(bq, be, b, b2, b3, b4, s, s2, null);
    }
    
    public void update(final Graphics graphics) {
        if (this.a != null) {
            this.paint(graphics);
        }
    }
    
    public void paint(final Graphics graphics) {
        if (this.a.q != null) {
            for (int i = 0; i < this.size().height; i += this.a.q.getHeight(null)) {
                for (int j = 0; j < this.size().width; j += this.a.q.getWidth(null)) {
                    graphics.drawImage(this.a.q, j, i, this);
                }
            }
        }
        else {
            graphics.clearRect(0, 0, this.size().width, this.size().height);
        }
    }
    
    public bt(final bq a, final be a2, final boolean g, final boolean q, final boolean k, final boolean l, final String label, final String d, final String e) {
        this.a = a;
        this.a = new al(null, 70, 20, true, a.getParameter("btnBackColor"), a.getParameter("btnTextColor"));
        this.j = new TextField(12);
        this.h = new TextField(12);
        this.i = new TextField(12);
        this.k = new TextField(10);
        this.h = new Checkbox(aG.a("Invisible"));
        this.d = new Checkbox();
        this.y = false;
        this.a = a2;
        this.g = g;
        this.q = q;
        this.k = k;
        this.l = l;
        this.e = e;
        final Label label2 = new Label(aG.a("Nickname"));
        final Label label3 = new Label(aG.a("Password"));
        final Label label4 = new Label(aG.a("Host"));
        final Label label5 = new Label(aG.a("Site ID"));
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        final GridBagLayout gridBagLayout = new GridBagLayout();
        this.setLayout(a.ad ? null : gridBagLayout);
        label2.setFont(aK.g);
        label3.setFont(aK.g);
        this.h.setEchoCharacter('*');
        label4.setFont(aK.g);
        label5.setFont(aK.g);
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        gridBagConstraints.anchor = 17;
        if (g) {
            if (a.q == null) {
                gridBagConstraints.gridwidth = -1;
                gridBagLayout.setConstraints(label2, gridBagConstraints);
                this.add(label2);
            }
            if (!a.ad) {
                final aX ax = new aX(this.j);
                gridBagConstraints.gridwidth = 0;
                gridBagLayout.setConstraints(ax, gridBagConstraints);
                this.add(ax);
            }
            else {
                this.j.setBounds(Integer.parseInt(a.i.get("nickname_x").toString()) - 1, Integer.parseInt(a.i.get("nickname_y").toString()) - 1, 96, 21);
                this.add(this.j);
            }
        }
        if (q) {
            if (a.q == null) {
                gridBagConstraints.gridwidth = -1;
                gridBagLayout.setConstraints(label3, gridBagConstraints);
                this.add(label3);
            }
            gridBagConstraints.gridwidth = 0;
            final aX ax2 = new aX(this.h);
            gridBagLayout.setConstraints(ax2, gridBagConstraints);
            this.add(ax2);
        }
        if (k) {
            if (a.q == null) {
                gridBagConstraints.gridwidth = -1;
                gridBagLayout.setConstraints(label4, gridBagConstraints);
                this.add(label4);
            }
            gridBagConstraints.gridwidth = 0;
            final aX ax3 = new aX(this.i);
            gridBagLayout.setConstraints(ax3, gridBagConstraints);
            this.add(ax3);
        }
        if (l) {
            if (a.q == null) {
                gridBagConstraints.gridwidth = -1;
                gridBagLayout.setConstraints(label5, gridBagConstraints);
                this.add(label5);
            }
            gridBagConstraints.gridwidth = 0;
            final aX ax4 = new aX(this.k);
            gridBagLayout.setConstraints(ax4, gridBagConstraints);
            this.add(ax4);
        }
        if (q) {
            gridBagConstraints.gridwidth = 0;
            gridBagLayout.setConstraints(this.h, gridBagConstraints);
            this.h.setState(false);
            this.add(this.h);
        }
        if (label != null && d != null) {
            this.y = true;
            this.d = d;
            gridBagConstraints.gridwidth = 0;
            gridBagLayout.setConstraints(this.d, gridBagConstraints);
            this.d.setState(false);
            this.d.setLabel(label);
            this.add(this.d);
        }
        this.a.a(aG.a("Connect"));
        this.a.f();
        this.a.setForeground(Color.black);
        final P p9 = new P(this.a);
        if (!a.ad) {
            gridBagConstraints.anchor = 13;
            gridBagConstraints.gridwidth = 0;
            gridBagLayout.setConstraints(p9, gridBagConstraints);
        }
        else {
            p9.setBounds(Integer.parseInt(a.i.get("connect_x").toString()) - 1, Integer.parseInt(a.i.get("connect_y").toString()) - 1, this.a.getSize().width + 6, this.a.getSize().height + 7);
        }
        this.add(p9);
        this.c();
        if (!g && !k && !q && !l && !this.a.T) {
            this.a();
        }
    }
}

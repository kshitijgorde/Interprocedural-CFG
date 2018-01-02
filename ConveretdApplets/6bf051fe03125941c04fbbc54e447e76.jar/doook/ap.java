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

public class ap extends Panel implements Runnable
{
    private ax a;
    public TextField b;
    public TextField a;
    public TextField c;
    public TextField d;
    Checkbox a;
    Checkbox f;
    private as e;
    private String p;
    private String q;
    private boolean t;
    private boolean u;
    private boolean v;
    private boolean w;
    private boolean x;
    private aX a;
    
    public void f() {
        final String d = this.e.d();
        if (d != null) {
            this.b.setText(d);
            this.a.requestFocus();
        }
        else {
            this.b.setText("");
            this.b.requestFocus();
        }
        if (this.e.A != null) {
            this.c.setText(this.e.A);
        }
        else {
            this.c.setText("");
        }
        this.a.setText("");
        if (this.e.K == -999) {
            this.d.setText("");
        }
        else {
            this.d.setText(String.valueOf(this.e.K));
        }
    }
    
    public void c() {
        new Thread(this).start();
    }
    
    public boolean action(final Event event, final Object o) {
        if (event.target instanceof TextField) {
            this.a.l();
            return true;
        }
        if (event.target == this.a) {
            this.c();
            return true;
        }
        return super.action(event, o);
    }
    
    public void g() {
        this.a.c();
        this.f.setState(false);
    }
    
    public void layout() {
        super.layout();
        this.f();
    }
    
    public void run() {
        this.a.g();
        final String trim = this.b.getText().trim();
        final String text = this.c.getText();
        final String text2 = this.a.getText();
        if (trim.indexOf(44) != -1 || trim.indexOf(34) != -1) {
            this.b.requestFocus();
            this.b.selectAll();
            new S(this.e.c, ar.b("Error"), ar.b("You can not use commas or quotes in your nickname.  Please re-enter this information."), this.e).setVisible(true);
            this.g();
            return;
        }
        int int1;
        try {
            int1 = Integer.parseInt(this.d.getText());
        }
        catch (NumberFormatException ex) {
            this.d.requestFocus();
            this.d.selectAll();
            new S(this.e.c, ar.b("Error"), ar.b("The siteID you entered is invalid.  Please re-enter this information."), this.e).setVisible(true);
            this.g();
            return;
        }
        if (trim.length() == 0) {
            this.b.requestFocus();
            this.b.selectAll();
            new S(this.e.c, ar.b("Note"), ar.b("You must enter a name.  Please re-enter this information."), this.e).setVisible(true);
            this.g();
            return;
        }
        if (trim.length() > 99) {
            this.b.setText(trim.substring(0, 98));
            this.b.requestFocus();
            this.b.selectAll();
            new S(this.e.c, ar.b("Note"), ar.b("Nickname must be 99 characters or less.  Please re-enter this information"), this.e).setVisible(true);
            this.g();
            return;
        }
        if (this.u && text2.length() == 0) {
            this.a.requestFocus();
            this.a.selectAll();
            new S(this.e.c, ar.b("Note"), ar.b("You must enter a password.  Please re-enter this information."), this.e).setVisible(true);
            this.g();
            return;
        }
        if (this.u && text2.length() < 3) {
            this.a.requestFocus();
            this.a.selectAll();
            new S(this.e.c, ar.b("Note"), ar.b("Passwords must be at least three characters long.  Please re-enter this information."), this.e).setVisible(true);
            this.g();
            return;
        }
        if (this.x && !this.f.getState()) {
            new S(this.e.c, ar.b("Note"), this.p, this.e).setVisible(true);
            this.g();
            return;
        }
        r r;
        String s;
        if (this.q != null && !this.u) {
            r = new r(this.q);
            s = trim;
        }
        else if (text2.length() > 0) {
            r = new r(text2);
            s = trim;
        }
        else {
            r = null;
            s = "Guest";
        }
        this.e.A = text;
        this.e.K = int1;
        this.e.a(23, this.a.getState());
        try {
            this.e.a(trim, s, r, text, int1);
        }
        catch (Exception ex2) {
            this.g();
        }
    }
    
    public ap(final aX ax, final as as, final boolean b, final boolean b2, final boolean b3, final boolean b4, final String s, final String s2) {
        this(ax, as, b, b2, b3, b4, s, s2, null);
    }
    
    public void update(final Graphics graphics) {
        if (this.a != null) {
            this.paint(graphics);
        }
    }
    
    public void paint(final Graphics graphics) {
        if (this.a.n != null) {
            for (int i = 0; i < this.size().height; i += this.a.n.getHeight(null)) {
                for (int j = 0; j < this.size().width; j += this.a.n.getWidth(null)) {
                    graphics.drawImage(this.a.n, j, i, this);
                }
            }
        }
        else {
            graphics.clearRect(0, 0, this.size().width, this.size().height);
        }
    }
    
    public ap(final aX a, final as e, final boolean t, final boolean u, final boolean v, final boolean w, final String label, final String p9, final String q) {
        this.a = a;
        this.a = new ax(null, 70, 20, true, a.getParameter("btnBackColor"), a.getParameter("btnTextColor"));
        this.b = new TextField(12);
        this.a = new TextField(12);
        this.c = new TextField(12);
        this.d = new TextField(10);
        this.a = new Checkbox(ar.b("Invisible"));
        this.f = new Checkbox();
        this.x = false;
        this.e = e;
        this.t = t;
        this.u = u;
        this.v = v;
        this.w = w;
        this.q = q;
        final Label label2 = new Label(ar.b("Nickname"));
        final Label label3 = new Label(ar.b("Password"));
        final Label label4 = new Label(ar.b("Host"));
        final Label label5 = new Label(ar.b("Site ID"));
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        final GridBagLayout gridBagLayout = new GridBagLayout();
        this.setLayout(a.ad ? null : gridBagLayout);
        label2.setFont(ay.b);
        label3.setFont(ay.b);
        this.a.setEchoCharacter('*');
        label4.setFont(ay.b);
        label5.setFont(ay.b);
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        gridBagConstraints.anchor = 17;
        if (t) {
            if (a.n == null) {
                gridBagConstraints.gridwidth = -1;
                gridBagLayout.setConstraints(label2, gridBagConstraints);
                this.add(label2);
            }
            if (!a.ad) {
                final aw aw = new aw(this.b);
                gridBagConstraints.gridwidth = 0;
                gridBagLayout.setConstraints(aw, gridBagConstraints);
                this.add(aw);
            }
            else {
                this.b.setBounds(Integer.parseInt(a.f.get("nickname_x").toString()) - 1, Integer.parseInt(a.f.get("nickname_y").toString()) - 1, 96, 21);
                this.add(this.b);
            }
        }
        if (u) {
            if (a.n == null) {
                gridBagConstraints.gridwidth = -1;
                gridBagLayout.setConstraints(label3, gridBagConstraints);
                this.add(label3);
            }
            gridBagConstraints.gridwidth = 0;
            final aw aw2 = new aw(this.a);
            gridBagLayout.setConstraints(aw2, gridBagConstraints);
            this.add(aw2);
        }
        if (v) {
            if (a.n == null) {
                gridBagConstraints.gridwidth = -1;
                gridBagLayout.setConstraints(label4, gridBagConstraints);
                this.add(label4);
            }
            gridBagConstraints.gridwidth = 0;
            final aw aw3 = new aw(this.c);
            gridBagLayout.setConstraints(aw3, gridBagConstraints);
            this.add(aw3);
        }
        if (w) {
            if (a.n == null) {
                gridBagConstraints.gridwidth = -1;
                gridBagLayout.setConstraints(label5, gridBagConstraints);
                this.add(label5);
            }
            gridBagConstraints.gridwidth = 0;
            final aw aw4 = new aw(this.d);
            gridBagLayout.setConstraints(aw4, gridBagConstraints);
            this.add(aw4);
        }
        if (u) {
            gridBagConstraints.gridwidth = 0;
            gridBagLayout.setConstraints(this.a, gridBagConstraints);
            this.a.setState(false);
            this.add(this.a);
        }
        if (label != null && p9 != null) {
            this.x = true;
            this.p = p9;
            gridBagConstraints.gridwidth = 0;
            gridBagLayout.setConstraints(this.f, gridBagConstraints);
            this.f.setState(false);
            this.f.setLabel(label);
            this.add(this.f);
        }
        this.a.a(ar.b("Connect"));
        this.a.p();
        this.a.setForeground(Color.black);
        final au au = new au(this.a);
        if (!a.ad) {
            gridBagConstraints.anchor = 13;
            gridBagConstraints.gridwidth = 0;
            gridBagLayout.setConstraints(au, gridBagConstraints);
        }
        else {
            au.setBounds(Integer.parseInt(a.f.get("connect_x").toString()) - 1, Integer.parseInt(a.f.get("connect_y").toString()) - 1, this.a.getSize().width + 6, this.a.getSize().height + 7);
        }
        this.add(au);
        this.f();
        if (!t && !v && !u && !w && !this.e.N) {
            this.c();
        }
    }
}

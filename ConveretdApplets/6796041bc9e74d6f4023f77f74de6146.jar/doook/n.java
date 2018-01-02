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

public class n extends Panel implements Runnable
{
    private aS a;
    public TextField b;
    public TextField c;
    public TextField d;
    public TextField e;
    Checkbox d;
    Checkbox e;
    Checkbox f;
    private t b;
    private String l;
    private String m;
    private boolean q;
    private boolean r;
    private boolean s;
    private boolean t;
    private boolean u;
    private y a;
    
    public void a() {
        final String f = this.b.f();
        if (f != null) {
            this.b.setText(f);
            this.c.requestFocus();
        }
        else {
            this.b.setText("");
            this.b.requestFocus();
        }
        if (this.b.w != null) {
            this.d.setText(this.b.w);
        }
        else {
            this.d.setText("");
        }
        this.c.setText("");
        if (this.b.u == -999) {
            this.e.setText("");
        }
        else {
            this.e.setText(String.valueOf(this.b.u));
        }
    }
    
    public void c() {
        new Thread(this).start();
    }
    
    public boolean action(final Event event, final Object o) {
        if (event.target instanceof TextField) {
            this.a.s();
            return true;
        }
        if (event.target == this.a) {
            this.c();
            return true;
        }
        return super.action(event, o);
    }
    
    public void d() {
        this.a.c();
        this.e.setState(false);
    }
    
    public void layout() {
        super.layout();
        this.a();
    }
    
    public void run() {
        this.a.d();
        final String trim = this.b.getText().trim();
        final String text = this.d.getText();
        final String text2 = this.c.getText();
        if (trim.indexOf(44) != -1 || trim.indexOf(34) != -1) {
            this.b.requestFocus();
            this.b.selectAll();
            new E(this.b.d, ao.e("Error"), ao.e("You can not use commas or quotes in your nickname.  Please re-enter this information."), this.b).setVisible(true);
            this.d();
            return;
        }
        int int1;
        try {
            int1 = Integer.parseInt(this.e.getText());
        }
        catch (NumberFormatException ex) {
            this.e.requestFocus();
            this.e.selectAll();
            new E(this.b.d, ao.e("Error"), ao.e("The siteID you entered is invalid.  Please re-enter this information."), this.b).setVisible(true);
            this.d();
            return;
        }
        if (trim.length() == 0) {
            this.b.requestFocus();
            this.b.selectAll();
            new E(this.b.d, ao.e("Note"), ao.e("You must enter a name.  Please re-enter this information."), this.b).setVisible(true);
            this.d();
            return;
        }
        if (trim.length() > 99) {
            this.b.setText(trim.substring(0, 98));
            this.b.requestFocus();
            this.b.selectAll();
            new E(this.b.d, ao.e("Note"), ao.e("Nickname must be 99 characters or less.  Please re-enter this information"), this.b).setVisible(true);
            this.d();
            return;
        }
        if (this.r && text2.length() == 0) {
            this.c.requestFocus();
            this.c.selectAll();
            new E(this.b.d, ao.e("Note"), ao.e("You must enter a password.  Please re-enter this information."), this.b).setVisible(true);
            this.d();
            return;
        }
        if (this.r && text2.length() < 3) {
            this.c.requestFocus();
            this.c.selectAll();
            new E(this.b.d, ao.e("Note"), ao.e("Passwords must be at least three characters long.  Please re-enter this information."), this.b).setVisible(true);
            this.d();
            return;
        }
        if (this.u && !this.e.getState()) {
            new E(this.b.d, ao.e("Note"), this.l, this.b).setVisible(true);
            this.d();
            return;
        }
        a a;
        String s;
        if (this.m != null && !this.r) {
            a = new a(this.m);
            s = trim;
        }
        else if (text2.length() > 0) {
            a = new a(text2);
            s = trim;
        }
        else {
            a = null;
            s = "Guest";
        }
        this.b.w = text;
        this.b.u = int1;
        this.b.a(23, this.d.getState());
        this.b.a(25, this.f.getState());
        try {
            this.b.a(trim, s, a, text, int1);
        }
        catch (Exception ex2) {
            this.d();
        }
    }
    
    public n(final y y, final t t, final boolean b, final boolean b2, final boolean b3, final boolean b4) {
        this(y, t, b, b2, b3, b4, null, null, null);
    }
    
    public void update(final Graphics graphics) {
        if (this.a.d != null) {
            this.paint(graphics);
        }
    }
    
    public void paint(final Graphics graphics) {
        if (this.a.d != null) {
            for (int i = 0; i < this.size().height; i += this.a.d.getHeight(null)) {
                for (int j = 0; j < this.size().width; j += this.a.d.getWidth(null)) {
                    graphics.drawImage(this.a.d, j, i, this);
                }
            }
        }
        else {
            graphics.clearRect(0, 0, this.size().width, this.size().height);
        }
    }
    
    public n(final y a, final t b, final boolean q, final boolean r, final boolean s, final boolean t, final String s2, final String s3, final String m) {
        this.a = a;
        this.a = new aS(null, 70, 20, true, a.getParameter("btnBackColor"), a.getParameter("btnTextColor"));
        this.b = new TextField(12);
        this.c = new TextField(12);
        this.d = new TextField(12);
        this.e = new TextField(10);
        this.d = new Checkbox();
        this.e = new Checkbox();
        this.f = new Checkbox();
        if (a.d == null) {
            this.d.setLabel(ao.e("Invisible"));
            this.f.setLabel(ao.e("Ghost"));
        }
        this.u = false;
        this.b = b;
        this.q = q;
        this.r = r;
        this.s = s;
        this.t = t;
        this.m = m;
        final Label label = new Label(ao.e("Nickname"));
        final Label label2 = new Label(ao.e("Password"));
        final Label label3 = new Label(ao.e("Host"));
        final Label label4 = new Label(ao.e("Site ID"));
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        final GridBagLayout gridBagLayout = new GridBagLayout();
        this.setLayout(a.V ? null : gridBagLayout);
        label.setFont(bL.g);
        label2.setFont(bL.g);
        this.c.setEchoCharacter('*');
        label3.setFont(bL.g);
        label4.setFont(bL.g);
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        gridBagConstraints.anchor = 17;
        if (q) {
            if (a.d == null) {
                gridBagConstraints.gridwidth = -1;
                gridBagLayout.setConstraints(label, gridBagConstraints);
                this.add(label);
            }
            if (!a.V) {
                final aR ar = new aR(this.b);
                gridBagConstraints.gridwidth = 0;
                gridBagLayout.setConstraints(ar, gridBagConstraints);
                this.add(ar);
            }
            else {
                this.b.setBounds(Integer.parseInt(a.c.get("nickname_x").toString()) - 1, Integer.parseInt(a.c.get("nickname_y").toString()) - 1, 96, 21);
                this.add(this.b);
            }
        }
        if (r) {
            if (a.d == null) {
                gridBagConstraints.gridwidth = -1;
                gridBagLayout.setConstraints(label2, gridBagConstraints);
                this.add(label2);
            }
            if (!a.V) {
                final aR ar2 = new aR(this.c);
                gridBagConstraints.gridwidth = 0;
                gridBagLayout.setConstraints(ar2, gridBagConstraints);
                this.add(ar2);
            }
            else {
                this.c.setBounds(Integer.parseInt(a.c.get("password_x").toString()) - 1, Integer.parseInt(a.c.get("password_y").toString()) - 1, 96, 21);
                this.add(this.c);
            }
        }
        if (s) {
            gridBagConstraints.gridwidth = -1;
            gridBagLayout.setConstraints(label3, gridBagConstraints);
            this.add(label3);
            gridBagConstraints.gridwidth = 0;
            final aR ar3 = new aR(this.d);
            gridBagLayout.setConstraints(ar3, gridBagConstraints);
            this.add(ar3);
        }
        if (t) {
            gridBagConstraints.gridwidth = -1;
            gridBagLayout.setConstraints(label4, gridBagConstraints);
            this.add(label4);
            gridBagConstraints.gridwidth = 0;
            final aR ar4 = new aR(this.e);
            gridBagLayout.setConstraints(ar4, gridBagConstraints);
            this.add(ar4);
        }
        if (r) {
            this.d.setState(false);
            this.f.setState(false);
            if (!a.V) {
                gridBagConstraints.gridwidth = -1;
                gridBagLayout.setConstraints(this.d, gridBagConstraints);
                this.add(this.d);
                gridBagConstraints.gridwidth = 0;
                gridBagLayout.setConstraints(this.f, gridBagConstraints);
                this.add(this.f);
            }
            else {
                this.d.setBounds(Integer.parseInt(a.c.get("invisible_x").toString()) - 1, Integer.parseInt(a.c.get("invisible_y").toString()) - 1, 13, 13);
                this.f.setBounds(Integer.parseInt(a.c.get("ghost_x").toString()) - 1, Integer.parseInt(a.c.get("ghost_y").toString()) - 1, 13, 13);
                this.add(this.d);
                this.add(this.f);
            }
        }
        this.a.a(ao.e("Connect"));
        this.a.t();
        this.a.setForeground(Color.black);
        final aQ aq = new aQ(this.a);
        if (!a.V) {
            gridBagConstraints.anchor = 13;
            gridBagConstraints.gridwidth = 0;
            gridBagLayout.setConstraints(aq, gridBagConstraints);
        }
        else {
            aq.setBounds(Integer.parseInt(a.c.get("connect_x").toString()) - 1, Integer.parseInt(a.c.get("connect_y").toString()) - 1, this.a.getSize().width + 6, this.a.getSize().height + 7);
        }
        this.add(aq);
        this.a();
        if (!q && !s && !r && !t) {
            this.c();
        }
    }
}

// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.awt.Component;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Label;
import java.awt.Event;
import java.awt.image.ImageObserver;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Checkbox;
import java.awt.TextField;
import java.awt.Panel;

public final class cl extends Panel implements Runnable
{
    private M q;
    private TextField q;
    private TextField w;
    private TextField e;
    private TextField r;
    private Checkbox q;
    private Checkbox w;
    private Checkbox e;
    private bp q;
    private String w;
    private String e;
    private boolean q;
    private boolean w;
    h q;
    Image q;
    String q;
    
    public final void paint(final Graphics graphics) {
        if (graphics != null && this.q != null) {
            graphics.drawImage(this.q, 0, 0, Color.white, null);
        }
    }
    
    public final void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    private void w() {
        final String e;
        if ((e = this.q.e) != null) {
            this.q.setText(e);
            this.w.requestFocus();
        }
        else {
            this.q.setText("");
            this.q.requestFocus();
        }
        if (this.q.a_() != null) {
            this.e.setText(this.q.a_());
        }
        else {
            this.e.setText("");
        }
        this.w.setText("");
        if (this.q.a_() == -999) {
            this.r.setText("");
            return;
        }
        this.r.setText(String.valueOf(this.q.a_()));
    }
    
    private void e() {
        new Thread(this).start();
    }
    
    public final boolean action(final Event event, final Object o) {
        if (event.target instanceof TextField) {
            this.q.r();
            return true;
        }
        if (event.target == this.q) {
            this.e();
            return true;
        }
        return super.action(event, o);
    }
    
    public final void q() {
        this.q.w();
        this.w.setState(false);
    }
    
    public final void layout() {
        super.layout();
        this.w();
    }
    
    public final void run() {
        this.q.e();
        final String trim = this.q.getText().trim();
        final String text = this.e.getText();
        final String text2 = this.w.getText();
        if (trim.indexOf(44) != -1 || trim.indexOf(34) != -1) {
            this.q.requestFocus();
            this.q.selectAll();
            new bR(this.q.q(), ak.q("Error"), ak.q("You can not use commas or quotes in your nickname.  Please re-enter this information."), this.q).setVisible(true);
            this.q();
            return;
        }
        int int1;
        try {
            int1 = Integer.parseInt(this.r.getText());
        }
        catch (NumberFormatException ex) {
            this.r.requestFocus();
            this.r.selectAll();
            new bR(this.q.q(), ak.q("Error"), ak.q("The siteID you entered is invalid.  Please re-enter this information."), this.q).setVisible(true);
            this.q();
            return;
        }
        if (trim.length() == 0) {
            this.q.requestFocus();
            this.q.selectAll();
            new bR(this.q.q(), ak.q("Note"), ak.q("You must enter a name.  Please re-enter this information."), this.q).setVisible(true);
            this.q();
            return;
        }
        if (this.q && text2.length() == 0) {
            this.w.requestFocus();
            this.w.selectAll();
            new bR(this.q.q(), ak.q("Note"), ak.q("You must enter a password.  Please re-enter this information."), this.q).setVisible(true);
            this.q();
            return;
        }
        if (text2.length() == 0 && cs.q == 1) {
            this.w.requestFocus();
            this.w.selectAll();
            new bR(this.q.q(), ak.q("Note"), ak.q("You must enter a password.  Please re-enter this information."), this.q).setVisible(true);
            this.q();
            return;
        }
        if (this.q && text2.length() < 3) {
            this.w.requestFocus();
            this.w.selectAll();
            new bR(this.q.q(), ak.q("Note"), ak.q("Passwords must be at least three characters long.  Please re-enter this information."), this.q).setVisible(true);
            this.q();
            return;
        }
        if (this.w && !this.w.getState()) {
            new bR(this.q.q(), ak.q("Note"), this.w, this.q).setVisible(true);
            this.q();
            return;
        }
        ck ck;
        String s;
        if (this.e != null && !this.q) {
            ck = new ck(this.e);
            s = trim;
        }
        else if (text2.length() > 0) {
            ck = new ck(text2);
            s = trim;
        }
        else {
            ck = null;
            s = "Guest";
        }
        this.q.q(text);
        if (cs.q == 1 && this.q.a_() >= 0 && this.q.a_() != int1) {
            this.q.q();
        }
        this.q.a_(int1);
        if (cs.q()) {
            this.q.q(23, this.q.getState());
            this.q.q(25, this.e.getState());
        }
        try {
            this.q.e = trim;
            this.q.q(trim, s, ck, int1);
        }
        catch (Exception ex2) {
            this.q();
        }
    }
    
    public cl(final h h, final bp bp, final boolean b, final boolean b2, final boolean b3, final boolean b4, final String s, final String s2, final String s3) {
        this(h, bp, b, false, false, false, s, s2, null, s3);
    }
    
    private cl(final h q, final bp q2, final boolean b, final boolean q3, final boolean b2, final boolean b3, final String label, final String w, final String s, final String q4) {
        this.q = q;
        this.q = q4;
        this.q = new M(70, 20);
        this.q = new TextField(12);
        this.w = new TextField(12);
        this.e = new TextField(12);
        this.r = new TextField(10);
        if (cs.q == 1) {
            this.q = new Checkbox(ak.q("Invisible"));
            this.e = new Checkbox(ak.q("Ghost"));
        }
        this.w = new Checkbox();
        this.w = false;
        this.q = q2;
        this.q = q3;
        this.e = null;
        final Label label2 = new Label(ak.q("Nickname"));
        final Label label3 = new Label(ak.q("Password"));
        final Label label4 = new Label(ak.q("Host"));
        final Label label5 = new Label(ak.q("Site ID"));
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        final GridBagLayout layout = new GridBagLayout();
        this.setLayout(layout);
        label2.setFont(bd.t);
        label3.setFont(bd.t);
        this.w.setEchoCharacter('*');
        label4.setFont(bd.t);
        label5.setFont(bd.t);
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        gridBagConstraints.anchor = 17;
        if (b) {
            gridBagConstraints.gridwidth = -1;
            layout.setConstraints(label2, gridBagConstraints);
            this.add(label2);
            gridBagConstraints.gridwidth = 0;
            final bb bb = new bb(this.q);
            layout.setConstraints(bb, gridBagConstraints);
            this.add(bb);
        }
        else {
            q.e = this.q.w();
        }
        if (q3) {
            gridBagConstraints.gridwidth = -1;
            layout.setConstraints(label3, gridBagConstraints);
            this.add(label3);
            gridBagConstraints.gridwidth = 0;
            final bb bb2 = new bb(this.w);
            layout.setConstraints(bb2, gridBagConstraints);
            this.add(bb2);
        }
        if (b2) {
            gridBagConstraints.gridwidth = -1;
            layout.setConstraints(label4, gridBagConstraints);
            this.add(label4);
            gridBagConstraints.gridwidth = 0;
            final bb bb3 = new bb(this.e);
            layout.setConstraints(bb3, gridBagConstraints);
            this.add(bb3);
        }
        if (b3) {
            gridBagConstraints.gridwidth = -1;
            layout.setConstraints(label5, gridBagConstraints);
            this.add(label5);
            gridBagConstraints.gridwidth = 0;
            final bb bb4 = new bb(this.r);
            layout.setConstraints(bb4, gridBagConstraints);
            this.add(bb4);
        }
        if (q3) {
            if (cs.q == 1) {
                gridBagConstraints.gridwidth = 1;
            }
        }
        else {
            gridBagConstraints.gridwidth = 0;
        }
        if (cs.q()) {
            layout.setConstraints(this.q, gridBagConstraints);
            this.q.setState(false);
            this.add(this.q);
            gridBagConstraints.gridwidth = 0;
            layout.setConstraints(this.e, gridBagConstraints);
            this.e.setState(false);
            this.add(this.e);
        }
        if (label != null && w != null) {
            this.w = true;
            this.w = w;
            gridBagConstraints.gridwidth = 0;
            layout.setConstraints(this.w, gridBagConstraints);
            this.w.setState(false);
            this.w.setLabel(label);
            this.add(this.w);
        }
        gridBagConstraints.anchor = 13;
        gridBagConstraints.gridwidth = 0;
        this.q.q(ak.q("Connect"));
        this.q.t();
        this.q.setForeground(Color.black);
        final Z z = new Z(this.q);
        layout.setConstraints(z, gridBagConstraints);
        this.add(z);
        this.w();
        if (!b && !b2 && !q3 && !b3) {
            this.e();
        }
    }
}

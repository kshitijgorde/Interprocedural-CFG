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
    private N q;
    private TextField q;
    private TextField w;
    private TextField e;
    private TextField r;
    private Checkbox q;
    private Checkbox w;
    private Checkbox e;
    private bq q;
    private String q;
    private String w;
    private boolean q;
    private boolean w;
    private h q;
    private Image q;
    
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
            new p(this.q.q(), al.q("Error"), al.q("You can not use commas or quotes in your nickname.  Please re-enter this information."), this.q).setVisible(true);
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
            new p(this.q.q(), al.q("Error"), al.q("The siteID you entered is invalid.  Please re-enter this information."), this.q).setVisible(true);
            this.q();
            return;
        }
        if (trim.length() == 0) {
            this.q.requestFocus();
            this.q.selectAll();
            new p(this.q.q(), al.q("Note"), al.q("You must enter a name.  Please re-enter this information."), this.q).setVisible(true);
            this.q();
            return;
        }
        if (this.q && text2.length() == 0) {
            this.w.requestFocus();
            this.w.selectAll();
            new p(this.q.q(), al.q("Note"), al.q("You must enter a password.  Please re-enter this information."), this.q).setVisible(true);
            this.q();
            return;
        }
        if (text2.length() == 0 && cs.q == 1) {
            this.w.requestFocus();
            this.w.selectAll();
            new p(this.q.q(), al.q("Note"), al.q("You must enter a password.  Please re-enter this information."), this.q).setVisible(true);
            this.q();
            return;
        }
        if (this.q && text2.length() < 3) {
            this.w.requestFocus();
            this.w.selectAll();
            new p(this.q.q(), al.q("Note"), al.q("Passwords must be at least three characters long.  Please re-enter this information."), this.q).setVisible(true);
            this.q();
            return;
        }
        if (this.w && !this.w.getState()) {
            new p(this.q.q(), al.q("Note"), this.q, this.q).setVisible(true);
            this.q();
            return;
        }
        ck ck;
        String s;
        if (this.w != null && !this.q) {
            ck = new ck(this.w);
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
    
    public cl(final h h, final bq bq, final boolean b, final boolean b2, final boolean b3, final boolean b4, final String s, final String s2, final Image image) {
        this(h, bq, b, false, false, false, s, s2, null, image);
    }
    
    private cl(final h q, final bq q2, final boolean b, final boolean q3, final boolean b2, final boolean b3, final String label, final String q4, final String s, final Image q5) {
        this.q = q;
        this.q = q5;
        this.q = new N(70, 20);
        this.q = new TextField(12);
        this.w = new TextField(12);
        this.e = new TextField(12);
        this.r = new TextField(10);
        if (cs.q == 1) {
            this.q = new Checkbox(al.q("Invisible"));
            this.e = new Checkbox(al.q("Ghost"));
        }
        this.w = new Checkbox();
        this.w = false;
        this.q = q2;
        this.q = q3;
        this.w = null;
        final Label label2 = new Label(al.q("Nickname"));
        final Label label3 = new Label(al.q("Password"));
        final Label label4 = new Label(al.q("Host"));
        final Label label5 = new Label(al.q("Site ID"));
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        final GridBagLayout layout = new GridBagLayout();
        this.setLayout(layout);
        label2.setFont(be.t);
        label3.setFont(be.t);
        this.w.setEchoCharacter('*');
        label4.setFont(be.t);
        label5.setFont(be.t);
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        gridBagConstraints.anchor = 17;
        if (b) {
            gridBagConstraints.gridwidth = -1;
            layout.setConstraints(label2, gridBagConstraints);
            this.add(label2);
            gridBagConstraints.gridwidth = 0;
            final bc bc = new bc(this.q);
            layout.setConstraints(bc, gridBagConstraints);
            this.add(bc);
        }
        else {
            q.e = this.q.w();
        }
        if (q3) {
            gridBagConstraints.gridwidth = -1;
            layout.setConstraints(label3, gridBagConstraints);
            this.add(label3);
            gridBagConstraints.gridwidth = 0;
            final bc bc2 = new bc(this.w);
            layout.setConstraints(bc2, gridBagConstraints);
            this.add(bc2);
        }
        if (b2) {
            gridBagConstraints.gridwidth = -1;
            layout.setConstraints(label4, gridBagConstraints);
            this.add(label4);
            gridBagConstraints.gridwidth = 0;
            final bc bc3 = new bc(this.e);
            layout.setConstraints(bc3, gridBagConstraints);
            this.add(bc3);
        }
        if (b3) {
            gridBagConstraints.gridwidth = -1;
            layout.setConstraints(label5, gridBagConstraints);
            this.add(label5);
            gridBagConstraints.gridwidth = 0;
            final bc bc4 = new bc(this.r);
            layout.setConstraints(bc4, gridBagConstraints);
            this.add(bc4);
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
        if (label != null && q4 != null) {
            this.w = true;
            this.q = q4;
            gridBagConstraints.gridwidth = 0;
            layout.setConstraints(this.w, gridBagConstraints);
            this.w.setState(false);
            this.w.setLabel(label);
            this.add(this.w);
        }
        gridBagConstraints.anchor = 13;
        gridBagConstraints.gridwidth = 0;
        this.q.q(al.q("Connect"));
        this.q.t();
        this.q.setForeground(Color.black);
        final ab ab = new ab(this.q);
        layout.setConstraints(ab, gridBagConstraints);
        this.add(ab);
        this.w();
        if (!b && !b2 && !q3 && !b3) {
            this.e();
        }
    }
}

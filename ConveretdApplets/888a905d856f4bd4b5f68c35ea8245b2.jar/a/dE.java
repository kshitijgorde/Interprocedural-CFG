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

public final class dE extends Panel implements Runnable
{
    private ad q;
    public TextField q;
    private TextField e;
    private TextField r;
    public TextField w;
    private Checkbox q;
    private Checkbox w;
    private Checkbox e;
    private cs q;
    private String w;
    private String e;
    private boolean q;
    private boolean w;
    m q;
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
    
    public final void q() {
        final String e;
        if ((e = this.q.e) != null) {
            this.q.setText(e);
            this.e.requestFocus();
        }
        else {
            this.q.setText("");
            this.q.requestFocus();
        }
        if (this.q.a_() != null) {
            this.r.setText(this.q.a_());
        }
        else {
            this.r.setText("");
        }
        this.e.setText("");
        if (this.q.a_() == -999) {
            this.w.setText("");
            return;
        }
        this.w.setText(String.valueOf(this.q.a_()));
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
    
    public final void w() {
        this.q.w();
        this.w.setState(false);
    }
    
    public final void layout() {
        super.layout();
        this.q();
    }
    
    public final void run() {
        this.q.e();
        final String trim = this.q.getText().trim();
        final String text = this.r.getText();
        final String text2 = this.e.getText();
        if (trim.indexOf(44) != -1 || trim.indexOf(34) != -1) {
            this.q.requestFocus();
            this.q.selectAll();
            new dd(this.q.q(), be.w("Error"), be.w("You can not use commas or quotes in your nickname.  Please re-enter this information."), this.q).setVisible(true);
            this.w();
            return;
        }
        int int1;
        try {
            int1 = Integer.parseInt(this.w.getText());
        }
        catch (NumberFormatException ex) {
            this.w.requestFocus();
            this.w.selectAll();
            new dd(this.q.q(), be.w("Error"), be.w("The siteID you entered is invalid.  Please re-enter this information."), this.q).setVisible(true);
            this.w();
            return;
        }
        if (trim.length() == 0) {
            this.q.requestFocus();
            this.q.selectAll();
            new dd(this.q.q(), be.w("Note"), be.w("You must enter a name.  Please re-enter this information."), this.q).setVisible(true);
            this.w();
            return;
        }
        if (this.q && text2.length() == 0) {
            this.e.requestFocus();
            this.e.selectAll();
            new dd(this.q.q(), be.w("Note"), be.w("You must enter a password.  Please re-enter this information."), this.q).setVisible(true);
            this.w();
            return;
        }
        if (text2.length() == 0 && dN.q == 1) {
            this.e.requestFocus();
            this.e.selectAll();
            new dd(this.q.q(), be.w("Note"), be.w("You must enter a password.  Please re-enter this information."), this.q).setVisible(true);
            this.w();
            return;
        }
        if (this.q && text2.length() < 3) {
            this.e.requestFocus();
            this.e.selectAll();
            new dd(this.q.q(), be.w("Note"), be.w("Passwords must be at least three characters long.  Please re-enter this information."), this.q).setVisible(true);
            this.w();
            return;
        }
        if (this.w && !this.w.getState()) {
            new dd(this.q.q(), be.w("Note"), this.w, this.q).setVisible(true);
            this.w();
            return;
        }
        dD dd;
        String s;
        if (this.e != null && !this.q) {
            dd = new dD(this.e);
            s = trim;
        }
        else if (text2.length() > 0) {
            dd = new dD(text2);
            s = trim;
        }
        else {
            dd = null;
            s = "Guest";
        }
        this.q.q(text);
        if (dN.q == 1 && this.q.a_() >= 0 && this.q.a_() != int1) {
            this.q.q();
        }
        this.q.a_(int1);
        if (dN.q()) {
            this.q.q(23, this.q.getState());
            this.q.q(25, this.e.getState());
        }
        try {
            this.q.e = trim;
            this.q.q(trim, s, dd, int1);
        }
        catch (Exception ex2) {
            this.w();
        }
    }
    
    public dE(final m m, final cs cs, final boolean b, final boolean b2, final boolean b3, final boolean b4) {
        this(m, cs, true, true, true, true, null, null, null, null);
    }
    
    public dE(final m q, final cs q2, final boolean b, final boolean q3, final boolean b2, final boolean b3, final String label, final String w, final String e, final String q4) {
        this.q = q;
        this.q = q4;
        this.q = new ad(70, 20);
        this.q = new TextField(12);
        this.e = new TextField(12);
        this.r = new TextField(12);
        this.w = new TextField(10);
        if (dN.q == 1) {
            this.q = new Checkbox(be.w("Invisible"));
            this.e = new Checkbox(be.w("Ghost"));
        }
        this.w = new Checkbox();
        this.w = false;
        this.q = q2;
        this.q = q3;
        this.e = e;
        final Label label2 = new Label(be.w("Nickname"));
        final Label label3 = new Label(be.w("Password"));
        final Label label4 = new Label(be.w("Host"));
        final Label label5 = new Label(be.w("Site ID"));
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        final GridBagLayout layout = new GridBagLayout();
        this.setLayout(layout);
        label2.setFont(cb.t);
        label3.setFont(cb.t);
        this.e.setEchoCharacter('*');
        label4.setFont(cb.t);
        label5.setFont(cb.t);
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        gridBagConstraints.anchor = 17;
        if (b) {
            gridBagConstraints.gridwidth = -1;
            layout.setConstraints(label2, gridBagConstraints);
            this.add(label2);
            gridBagConstraints.gridwidth = 0;
            final bZ bz = new bZ(this.q);
            layout.setConstraints(bz, gridBagConstraints);
            this.add(bz);
        }
        else {
            q.e = this.q.w();
        }
        if (q3) {
            gridBagConstraints.gridwidth = -1;
            layout.setConstraints(label3, gridBagConstraints);
            this.add(label3);
            gridBagConstraints.gridwidth = 0;
            final bZ bz2 = new bZ(this.e);
            layout.setConstraints(bz2, gridBagConstraints);
            this.add(bz2);
        }
        if (b2) {
            gridBagConstraints.gridwidth = -1;
            layout.setConstraints(label4, gridBagConstraints);
            this.add(label4);
            gridBagConstraints.gridwidth = 0;
            final bZ bz3 = new bZ(this.r);
            layout.setConstraints(bz3, gridBagConstraints);
            this.add(bz3);
        }
        if (b3) {
            gridBagConstraints.gridwidth = -1;
            layout.setConstraints(label5, gridBagConstraints);
            this.add(label5);
            gridBagConstraints.gridwidth = 0;
            final bZ bz4 = new bZ(this.w);
            layout.setConstraints(bz4, gridBagConstraints);
            this.add(bz4);
        }
        if (q3) {
            if (dN.q == 1) {
                gridBagConstraints.gridwidth = 1;
            }
        }
        else {
            gridBagConstraints.gridwidth = 0;
        }
        if (dN.q()) {
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
        this.q.q(be.w("Connect"));
        this.q.t();
        this.q.setForeground(Color.black);
        final as as = new as(this.q);
        layout.setConstraints(as, gridBagConstraints);
        this.add(as);
        this.q();
        if (!b && !b2 && !q3 && !b3) {
            this.e();
        }
    }
}

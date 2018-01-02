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
import com.spilka.client.muc.AppletAbstract;
import java.awt.Checkbox;
import java.awt.TextField;
import java.awt.Panel;

public final class bt extends Panel implements Runnable
{
    private e q;
    private TextField q;
    private TextField w;
    private TextField e;
    private TextField r;
    private Checkbox q;
    private Checkbox w;
    private Checkbox e;
    private bz q;
    private String q;
    private String w;
    private boolean q;
    private boolean w;
    private AppletAbstract q;
    private Image q;
    private String e;
    
    public final void q() {
        if (this.e != null) {
            this.q = this.q.getImage(this.q.getCodeBase(), "Resources/" + bH.p + "/" + this.e);
        }
    }
    
    public final void paint(final Graphics graphics) {
        if (graphics != null && this.q != null) {
            graphics.drawImage(this.q, 0, 0, Color.white, null);
        }
    }
    
    public final void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    private void e() {
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
    
    private void r() {
        new Thread(this).start();
    }
    
    public final boolean action(final Event event, final Object o) {
        if (event.target instanceof TextField) {
            this.q.r();
            return true;
        }
        if (event.target == this.q) {
            this.r();
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
        this.e();
    }
    
    public final void run() {
        this.q.e();
        final String trim = this.q.getText().trim();
        final String text = this.e.getText();
        final String text2 = this.w.getText();
        if (trim.indexOf(44) != -1 || trim.indexOf(34) != -1) {
            this.q.requestFocus();
            this.q.selectAll();
            new b(this.q.q(), cv.q("Error"), cv.q("You can not use commas or quotes in your nickname.  Please re-enter this information."), this.q).setVisible(true);
            this.w();
            return;
        }
        int int1;
        try {
            int1 = Integer.parseInt(this.r.getText());
        }
        catch (NumberFormatException ex) {
            this.r.requestFocus();
            this.r.selectAll();
            new b(this.q.q(), cv.q("Error"), cv.q("The siteID you entered is invalid.  Please re-enter this information."), this.q).setVisible(true);
            this.w();
            return;
        }
        if (trim.length() == 0) {
            this.q.requestFocus();
            this.q.selectAll();
            new b(this.q.q(), cv.q("Note"), cv.q("You must enter a name.  Please re-enter this information."), this.q).setVisible(true);
            this.w();
            return;
        }
        if (this.q && text2.length() == 0) {
            this.w.requestFocus();
            this.w.selectAll();
            new b(this.q.q(), cv.q("Note"), cv.q("You must enter a password.  Please re-enter this information."), this.q).setVisible(true);
            this.w();
            return;
        }
        if (text2.length() == 0 && a.q == 1) {
            this.w.requestFocus();
            this.w.selectAll();
            new b(this.q.q(), cv.q("Note"), cv.q("You must enter a password.  Please re-enter this information."), this.q).setVisible(true);
            this.w();
            return;
        }
        if (this.q && text2.length() < 3) {
            this.w.requestFocus();
            this.w.selectAll();
            new b(this.q.q(), cv.q("Note"), cv.q("Passwords must be at least three characters long.  Please re-enter this information."), this.q).setVisible(true);
            this.w();
            return;
        }
        if (this.w && !this.w.getState()) {
            new b(this.q.q(), cv.q("Note"), this.q, this.q).setVisible(true);
            this.w();
            return;
        }
        cH ch;
        String s;
        if (this.w != null && !this.q) {
            ch = new cH(this.w);
            s = trim;
        }
        else if (text2.length() > 0) {
            ch = new cH(text2);
            s = trim;
        }
        else {
            ch = null;
            s = "Guest";
        }
        this.q.q(text);
        if (a.q == 1 && this.q.a_() >= 0 && this.q.a_() != int1) {
            this.q.r();
        }
        this.q.a_(int1);
        if (a.q()) {
            this.q.q(23, this.q.getState());
            this.q.q(25, this.e.getState());
        }
        try {
            this.q.e = trim;
            this.q.q(trim, s, ch, int1);
        }
        catch (Exception ex2) {
            this.w();
        }
    }
    
    public bt(final AppletAbstract appletAbstract, final bz bz, final boolean b, final boolean b2, final boolean b3, final boolean b4, final String s, final String s2, final String s3) {
        this(appletAbstract, bz, b, false, false, false, s, s2, null, s3);
    }
    
    private bt(final AppletAbstract q, final bz q2, final boolean b, final boolean q3, final boolean b2, final boolean b3, final String label, final String q4, final String s, final String e) {
        this.q = q;
        this.e = e;
        this.q = new e(70, 20);
        this.q = new TextField(12);
        this.w = new TextField(12);
        this.e = new TextField(12);
        this.r = new TextField(10);
        if (a.q == 1) {
            this.q = new Checkbox(cv.q("Invisible"));
            this.e = new Checkbox(cv.q("Ghost"));
        }
        this.w = new Checkbox();
        this.w = false;
        this.q = q2;
        this.q = q3;
        this.w = null;
        final Label label2 = new Label(cv.q("Nickname"));
        final Label label3 = new Label(cv.q("Password"));
        final Label label4 = new Label(cv.q("Host"));
        final Label label5 = new Label(cv.q("Site ID"));
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        final GridBagLayout layout = new GridBagLayout();
        this.setLayout(layout);
        label2.setFont(k.t);
        label3.setFont(k.t);
        this.w.setEchoCharacter('*');
        label4.setFont(k.t);
        label5.setFont(k.t);
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        gridBagConstraints.anchor = 17;
        if (b) {
            gridBagConstraints.gridwidth = -1;
            layout.setConstraints(label2, gridBagConstraints);
            this.add(label2);
            gridBagConstraints.gridwidth = 0;
            final r r = new r(this.q);
            layout.setConstraints(r, gridBagConstraints);
            this.add(r);
        }
        else {
            q.e = this.q.getName();
        }
        if (q3) {
            gridBagConstraints.gridwidth = -1;
            layout.setConstraints(label3, gridBagConstraints);
            this.add(label3);
            gridBagConstraints.gridwidth = 0;
            final r r2 = new r(this.w);
            layout.setConstraints(r2, gridBagConstraints);
            this.add(r2);
        }
        if (b2) {
            gridBagConstraints.gridwidth = -1;
            layout.setConstraints(label4, gridBagConstraints);
            this.add(label4);
            gridBagConstraints.gridwidth = 0;
            final r r3 = new r(this.e);
            layout.setConstraints(r3, gridBagConstraints);
            this.add(r3);
        }
        if (b3) {
            gridBagConstraints.gridwidth = -1;
            layout.setConstraints(label5, gridBagConstraints);
            this.add(label5);
            gridBagConstraints.gridwidth = 0;
            final r r4 = new r(this.r);
            layout.setConstraints(r4, gridBagConstraints);
            this.add(r4);
        }
        if (q3) {
            if (a.q == 1) {
                gridBagConstraints.gridwidth = 1;
            }
        }
        else {
            gridBagConstraints.gridwidth = 0;
        }
        if (a.q()) {
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
        this.q.q(cv.q("Connect"));
        this.q.t();
        this.q.setForeground(Color.black);
        final d d = new d(this.q);
        layout.setConstraints(d, gridBagConstraints);
        this.add(d);
        this.e();
        if (!b && !b2 && !q3 && !b3) {
            this.r();
        }
    }
}

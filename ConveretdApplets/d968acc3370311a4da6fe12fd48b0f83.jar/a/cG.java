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

public final class cG extends Panel implements Runnable
{
    private g q;
    public TextField q;
    private TextField e;
    private TextField r;
    public TextField w;
    private Checkbox q;
    private Checkbox w;
    private Checkbox e;
    private cM q;
    private String q;
    private String w;
    private boolean q;
    private boolean w;
    private AppletAbstract q;
    private Image q;
    private String e;
    
    public final void q() {
        if (this.e != null) {
            this.q = this.q.getImage(this.q.getCodeBase(), "Resources/" + cU.a + "/" + this.e);
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
    
    public final void w() {
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
    
    public final void e() {
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
        final String text = this.r.getText();
        final String text2 = this.e.getText();
        if (trim.indexOf(44) != -1 || trim.indexOf(34) != -1) {
            this.q.requestFocus();
            this.q.selectAll();
            new b(this.q.q(), eb.q("Error"), eb.q("You can not use commas or quotes in your nickname.  Please re-enter this information."), this.q).setVisible(true);
            this.e();
            return;
        }
        int int1;
        try {
            int1 = Integer.parseInt(this.w.getText());
        }
        catch (NumberFormatException ex) {
            this.w.requestFocus();
            this.w.selectAll();
            new b(this.q.q(), eb.q("Error"), eb.q("The siteID you entered is invalid.  Please re-enter this information."), this.q).setVisible(true);
            this.e();
            return;
        }
        if (trim.length() == 0) {
            this.q.requestFocus();
            this.q.selectAll();
            new b(this.q.q(), eb.q("Note"), eb.q("You must enter a name.  Please re-enter this information."), this.q).setVisible(true);
            this.e();
            return;
        }
        if (this.q && text2.length() == 0) {
            this.e.requestFocus();
            this.e.selectAll();
            new b(this.q.q(), eb.q("Note"), eb.q("You must enter a password.  Please re-enter this information."), this.q).setVisible(true);
            this.e();
            return;
        }
        if (text2.length() == 0 && a.q == 1) {
            this.e.requestFocus();
            this.e.selectAll();
            new b(this.q.q(), eb.q("Note"), eb.q("You must enter a password.  Please re-enter this information."), this.q).setVisible(true);
            this.e();
            return;
        }
        if (this.q && text2.length() < 3) {
            this.e.requestFocus();
            this.e.selectAll();
            new b(this.q.q(), eb.q("Note"), eb.q("Passwords must be at least three characters long.  Please re-enter this information."), this.q).setVisible(true);
            this.e();
            return;
        }
        if (this.w && !this.w.getState()) {
            new b(this.q.q(), eb.q("Note"), this.q, this.q).setVisible(true);
            this.e();
            return;
        }
        ep ep;
        String s;
        if (this.w != null && !this.q) {
            ep = new ep(this.w);
            s = trim;
        }
        else if (text2.length() > 0) {
            ep = new ep(text2);
            s = trim;
        }
        else {
            ep = null;
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
            this.q.q(trim, s, ep, int1);
        }
        catch (Exception ex2) {
            this.e();
        }
    }
    
    public cG(final AppletAbstract appletAbstract, final cM cm, final boolean b, final boolean b2, final boolean b3, final boolean b4) {
        this(appletAbstract, cm, true, true, true, true, null, null, null, null);
    }
    
    public cG(final AppletAbstract q, final cM q2, final boolean b, final boolean q3, final boolean b2, final boolean b3, final String label, final String q4, final String w, final String e) {
        this.q = q;
        this.e = e;
        this.q = new g(70, 20);
        this.q = new TextField(12);
        this.e = new TextField(12);
        this.r = new TextField(12);
        this.w = new TextField(10);
        if (a.q == 1) {
            this.q = new Checkbox(eb.q("Invisible"));
            this.e = new Checkbox(eb.q("Ghost"));
        }
        this.w = new Checkbox();
        this.w = false;
        this.q = q2;
        this.q = q3;
        this.w = w;
        final Label label2 = new Label(eb.q("Nickname"));
        final Label label3 = new Label(eb.q("Password"));
        final Label label4 = new Label(eb.q("Host"));
        final Label label5 = new Label(eb.q("Site ID"));
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        final GridBagLayout layout = new GridBagLayout();
        this.setLayout(layout);
        label2.setFont(m.t);
        label3.setFont(m.t);
        this.e.setEchoCharacter('*');
        label4.setFont(m.t);
        label5.setFont(m.t);
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        gridBagConstraints.anchor = 17;
        if (b) {
            gridBagConstraints.gridwidth = -1;
            layout.setConstraints(label2, gridBagConstraints);
            this.add(label2);
            gridBagConstraints.gridwidth = 0;
            final t t = new t(this.q);
            layout.setConstraints(t, gridBagConstraints);
            this.add(t);
        }
        else {
            q.e = this.q.getName();
        }
        if (q3) {
            gridBagConstraints.gridwidth = -1;
            layout.setConstraints(label3, gridBagConstraints);
            this.add(label3);
            gridBagConstraints.gridwidth = 0;
            final t t2 = new t(this.e);
            layout.setConstraints(t2, gridBagConstraints);
            this.add(t2);
        }
        if (b2) {
            gridBagConstraints.gridwidth = -1;
            layout.setConstraints(label4, gridBagConstraints);
            this.add(label4);
            gridBagConstraints.gridwidth = 0;
            final t t3 = new t(this.r);
            layout.setConstraints(t3, gridBagConstraints);
            this.add(t3);
        }
        if (b3) {
            gridBagConstraints.gridwidth = -1;
            layout.setConstraints(label5, gridBagConstraints);
            this.add(label5);
            gridBagConstraints.gridwidth = 0;
            final t t4 = new t(this.w);
            layout.setConstraints(t4, gridBagConstraints);
            this.add(t4);
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
        this.q.q(eb.q("Connect"));
        this.q.t();
        this.q.setForeground(Color.black);
        final f f = new f(this.q);
        layout.setConstraints(f, gridBagConstraints);
        this.add(f);
        this.w();
        if (!b && !b2 && !q3 && !b3) {
            this.r();
        }
    }
}

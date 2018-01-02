// 
// Decompiled by Procyon v0.5.30
// 

package com.diginet.digichat.client;

import com.diginet.digichat.awt.u;
import com.diginet.digichat.awt.aw;
import com.diginet.digichat.util.a5;
import com.diginet.digichat.awt.t;
import java.awt.Component;
import java.awt.Insets;
import com.diginet.digichat.awt.dw;
import java.awt.Label;
import java.awt.LayoutManager;
import java.awt.Panel;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import com.diginet.digichat.common.j;
import com.diginet.digichat.awt.a6;
import com.esial.util.c;
import com.diginet.digichat.util.dx;
import java.awt.Event;
import com.diginet.digichat.common.e;
import java.awt.Frame;
import java.awt.Color;
import java.awt.Checkbox;
import java.awt.Choice;
import java.awt.TextField;
import com.diginet.digichat.awt.r;
import com.diginet.digichat.awt.ag;

public final class c2 extends ag
{
    private r a;
    private r b;
    private TextField c;
    private Choice chcLocations;
    private TextField txfLogin;
    private TextField txfPass;
    private TextField txfConf;
    private Checkbox chkBuddy;
    private Checkbox chkLocation;
    private Color clrBack;
    private i iUser;
    private Buddy bdUser;
    private by byParent;
    private Location[] locations;
    private Frame e;
    
    private void selectLocation() {
        boolean fBuddy;
        boolean fLocation = fBuddy = false;
        String s = null;
        e e = null;
        final Universal universal;
        final boolean b;
        if (b = ((universal = (Universal)this.locations[this.chcLocations.getSelectedIndex()]) instanceof Universal)) {
            if (this.bdUser != null) {
                s = this.bdUser.strLogin;
                e = this.bdUser.ePass;
                fBuddy = this.bdUser.fBuddy;
                fLocation = this.bdUser.fLocation;
            }
            else {
                if (universal != null) {
                    final Universal universal2 = universal;
                    s = universal2.strLogin;
                    e = universal2.ePass;
                }
                fBuddy = true;
            }
        }
        this.txfLogin.enable(b);
        this.txfPass.enable(b);
        this.txfConf.enable(b);
        this.chkBuddy.enable(b);
        this.chkLocation.enable(fBuddy);
        this.txfLogin.setText((s == null || this.iUser.x().equals(s)) ? "" : s);
        final String text;
        this.txfPass.setText(text = ((e == null) ? "" : e.toString()));
        this.txfConf.setText(text);
        this.chkBuddy.setState(fBuddy);
        this.chkLocation.setState(fLocation);
    }
    
    public final boolean handleEvent(final Event event) {
        switch (event.id) {
            case 401: {
                if (event.key == 10 || event.key == dx.a) {
                    this.a.e();
                    return true;
                }
                if (event.key == 27) {
                    this.b.e();
                    return true;
                }
                break;
            }
            case 1001: {
                if (event.target == this.chcLocations) {
                    this.selectLocation();
                    return true;
                }
                if (event.target == this.chkBuddy) {
                    if (this.chkBuddy.getState()) {
                        this.chkLocation.enable();
                    }
                    else {
                        this.chkLocation.disable();
                        this.chkLocation.setState(false);
                    }
                    return true;
                }
                if (event.target == this.a) {
                    final String trim = this.c.getText().trim();
                    if (trim.length() == 0) {
                        this.c.requestFocus();
                        this.c.selectAll();
                        new a6(this.e, com.esial.util.c.a("Note"), com.esial.util.c.a("You must provide a name for your Buddy.  Please re-enter this information."), this.iUser).setVisible(true);
                        return true;
                    }
                    if (trim.indexOf(44) != -1 || trim.indexOf(34) != -1) {
                        this.c.requestFocus();
                        this.c.selectAll();
                        new a6(this.e, com.esial.util.c.a("Error"), com.esial.util.c.a("Names can not have commas or quotes.  Please re-enter this information."), this.iUser).setVisible(true);
                        return true;
                    }
                    e e = null;
                    String s = null;
                    final Location location;
                    if ((location = this.locations[this.chcLocations.getSelectedIndex()]) instanceof Universal) {
                        final String trim2;
                        if (!this.txfConf.getText().trim().equals(trim2 = this.txfPass.getText().trim())) {
                            this.txfPass.requestFocus();
                            this.txfPass.selectAll();
                            new a6(this.e, com.esial.util.c.a("Note"), com.esial.util.c.a("The confirmation password does not match the new password. Please re-enter this information."), this.iUser).setVisible(true);
                            return true;
                        }
                        if (trim2.length() != 0) {
                            e = new e(trim2);
                        }
                        if ((s = this.txfLogin.getText().trim()).length() == 0) {
                            s = this.iUser.x();
                        }
                    }
                    final Buddy buddy = new Buddy(-999, String.valueOf(String.valueOf(trim).concat(String.valueOf('\n'))).concat(String.valueOf(location.x())), location, s, e, this.chkBuddy.getState(), this.chkLocation.getState(), this.byParent.getLoc());
                    if (this.bdUser == null || !trim.equals(this.bdUser.x()) || buddy.notEq(this.bdUser)) {
                        this.byParent.setBuddy(buddy, true, true);
                    }
                    event.target = this.b;
                }
                if (event.target == this.b) {
                    this.dispose();
                    return true;
                }
                break;
            }
        }
        return super.handleEvent(event);
    }
    
    public c2(final Frame frame, final i iUser, final by byParent, final Buddy bdUser) {
        super(frame, com.esial.util.c.a("Add Buddy"), true);
        this.a = new r(com.esial.util.c.a("OK"));
        this.b = new r(com.esial.util.c.a("Cancel"));
        this.setBackground(iUser.cc.j);
        this.setForeground(iUser.cc.i);
        this.iUser = iUser;
        this.byParent = byParent;
        this.bdUser = bdUser;
        final GridBagLayout gridBagLayout = new GridBagLayout();
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        final Panel panel = new Panel();
        this.setResizable(false);
        this.setLayout(gridBagLayout);
        panel.setLayout(gridBagLayout);
        final Label label = new Label(com.esial.util.c.a("Buddy Nickname"));
        label.setFont(dw.d);
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        gridBagConstraints.anchor = 17;
        gridBagLayout.setConstraints(label, gridBagConstraints);
        panel.add(label);
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.fill = 2;
        final TextField c = new TextField(20);
        this.c = c;
        this.clrBack = c.getBackground();
        if (bdUser != null) {
            this.c.setText(bdUser.x());
        }
        final t t = new t(this.c);
        gridBagLayout.setConstraints(t, gridBagConstraints);
        panel.add(t);
        final Label label2 = new Label(com.esial.util.c.a("Buddy Location"));
        label2.setFont(dw.d);
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.weightx = 0.0;
        gridBagConstraints.fill = 0;
        gridBagLayout.setConstraints(label2, gridBagConstraints);
        panel.add(label2);
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.fill = 2;
        final t t2 = new t(this.chcLocations = new Choice());
        iUser.locations.a(false);
        int n = 0;
        int n2 = 0;
        final Location[] array = new Location[iUser.locations.b()];
        try {
            for (int i = 0; i < array.length; ++i) {
                final Location location;
                if ((location = (Location)iUser.locations.d(i)).i(61)) {
                    if (bdUser != null && bdUser.location == location) {
                        n2 = n;
                    }
                    array[n++] = location;
                    this.chcLocations.addItem(location.x());
                }
            }
        }
        finally {
            iUser.locations.a();
        }
        System.arraycopy(array, 0, this.locations = new Location[n], 0, n);
        this.chcLocations.select(n2);
        gridBagLayout.setConstraints(t2, gridBagConstraints);
        panel.add(t2);
        gridBagLayout.setConstraints(panel, gridBagConstraints);
        final Label label3 = new Label(com.esial.util.c.a("Your Login"));
        label3.setFont(dw.d);
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.weightx = 0.0;
        gridBagConstraints.fill = 0;
        gridBagLayout.setConstraints(label3, gridBagConstraints);
        panel.add(label3);
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.fill = 2;
        final t t3 = new t(this.txfLogin = new TextField(20));
        gridBagLayout.setConstraints(t3, gridBagConstraints);
        panel.add(t3);
        final aw aw = new aw(a5.a(com.esial.util.c.a("You must leave this field is blank if location of your buddy is %1. You can leave this field is blank if your login for this location coincide with your actual nickname."), new String[] { DigiChatAppletAbstract.OEM_DigiChat }));
        gridBagLayout.setConstraints(aw, gridBagConstraints);
        panel.add(aw);
        final Label label4 = new Label(com.esial.util.c.a("Your Password:"));
        label4.setFont(dw.d);
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.weightx = 0.0;
        gridBagConstraints.fill = 0;
        gridBagLayout.setConstraints(label4, gridBagConstraints);
        panel.add(label4);
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.fill = 2;
        final t t4 = new t(this.txfPass = new TextField(20));
        this.txfPass.setEchoChar('*');
        gridBagLayout.setConstraints(t4, gridBagConstraints);
        panel.add(t4);
        final Label label5 = new Label(com.esial.util.c.a("Confirm Password:"));
        label5.setFont(dw.d);
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.weightx = 0.0;
        gridBagConstraints.fill = 0;
        gridBagLayout.setConstraints(label5, gridBagConstraints);
        panel.add(label5);
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.fill = 2;
        final t t5 = new t(this.txfConf = new TextField(20));
        this.txfConf.setEchoChar('*');
        gridBagLayout.setConstraints(t5, gridBagConstraints);
        panel.add(t5);
        final aw aw2 = new aw(a5.a(com.esial.util.c.a("You must leave this field is blank if location of your buddy is %1. You can leave this field is blank if for this location of your buddy not need password for entry."), new String[] { DigiChatAppletAbstract.OEM_DigiChat }));
        gridBagLayout.setConstraints(aw2, gridBagConstraints);
        panel.add(aw2);
        gridBagLayout.setConstraints(this.chkBuddy = new Checkbox(com.esial.util.c.a("Save my login/password for this buddy.")), gridBagConstraints);
        panel.add(this.chkBuddy);
        gridBagLayout.setConstraints(this.chkLocation = new Checkbox(com.esial.util.c.a("Save my login/password for this location.")), gridBagConstraints);
        panel.add(this.chkLocation);
        this.selectLocation();
        this.add(panel);
        gridBagConstraints.weightx = 0.0;
        gridBagConstraints.fill = 0;
        gridBagConstraints.anchor = 13;
        final Panel panel2 = new Panel();
        panel2.add(this.b);
        panel2.add(new u(this.a));
        gridBagLayout.setConstraints(panel2, gridBagConstraints);
        this.add(panel2);
        this.pack();
        this.c.requestFocus();
        this.setVisible(true);
    }
}

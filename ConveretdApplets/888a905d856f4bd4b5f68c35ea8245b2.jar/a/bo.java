// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.awt.Dimension;
import java.util.Date;
import java.awt.Event;
import java.util.Vector;
import java.awt.LayoutManager;
import java.awt.Panel;
import java.awt.FlowLayout;
import java.awt.Component;
import java.awt.Color;
import java.awt.Choice;
import java.awt.Label;
import java.awt.TextField;

public final class bo extends G
{
    private TextField q;
    private TextField w;
    private TextField e;
    private cc w;
    private bV w;
    protected cu q;
    private ab q;
    private ab w;
    private dx q;
    private dx w;
    private Label q;
    private ad t;
    private ad y;
    private ad u;
    private dS e;
    private dS r;
    private TextField r;
    private TextField t;
    private Choice q;
    private ad i;
    private Choice w;
    private aE q;
    private aE w;
    private boolean e;
    
    public final bp q() {
        final cA ca;
        (ca = new cA(-999, "")).q(2590695664122855432L);
        ca.d(52);
        return ca;
    }
    
    private void t() {
        this.q.q(0L);
        this.w.q(0L);
        this.q.setText(bX.q);
    }
    
    public final void q(final bp bp) {
        final cA q = (cA)bp;
        this.q.setText(q.a);
        this.t.setText(q.w);
        if (q.q > 0L) {
            this.q.q(q.q);
            if (q.w > 0L) {
                this.w.q(q.w);
                this.q.setText(dx.q(this.q.q(), this.w.q()));
            }
            else {
                this.t();
            }
        }
        else {
            this.t();
        }
        if ("Guest".equalsIgnoreCase(q.a) || "Chatmaster".equalsIgnoreCase(q.a)) {
            this.q.disable();
            this.w.disable();
            this.q.disable();
            this.t.disable();
        }
        else {
            this.q.enable();
            this.w.enable();
            this.q.enable();
            this.t.enable();
        }
        this.e.setBackground(new Color(q.w));
        this.r.setBackground(new Color(q.e));
        this.r.setText(q.q);
        this.w.removeAll();
        this.w.add(de.q);
        for (int i = 0; i < this.q.y.q; ++i) {
            final de de;
            if ((de = (de)this.q.y.q(i)).q()) {
                this.w.add(de.a);
            }
            if (de.s == q.q) {
                this.w.select(de.a);
            }
        }
        if (q.q != null) {
            this.w.setText(q.q.toString());
            this.e.setText(q.q.toString());
        }
        else {
            this.w.setText("");
            this.e.setText("");
        }
        final int s;
        if ((s = bp.s) < 1000 && s != -999) {
            this.q.disable();
            if (s == 2) {
                this.w.disable();
                this.e.disable();
            }
        }
        else {
            this.w.enable();
            this.e.enable();
            this.q.enable();
        }
        this.w.q(this.w, s != 2);
        this.w.q(this.q, s != 2);
        this.q.q(q.w());
        this.w.q(q.g);
        this.q = q;
        this.w.t();
    }
    
    public final boolean q(final bp bp) {
        final cA ca = (cA)bp;
        final String text = this.w.getText();
        final String trim;
        if ((trim = this.q.getText().trim()).length() == 0) {
            new dd(super.q, be.w("Note"), be.w("You must provide a name for this account. Please re-enter this information."), super.q).setVisible(true);
            return false;
        }
        if ("Admin".equalsIgnoreCase(trim)) {
            new dd(super.q, be.w("Note"), be.w("This account name is reserved.  Please choose another name."), super.q).setVisible(true);
            return false;
        }
        final String s = trim;
        final cA ca2 = (cA)bp;
        final String s2 = s;
        int i = 0;
        while (true) {
            while (i < this.q()) {
                final bp q = this.q(i);
                if (ca2 != q && q.a.equalsIgnoreCase(s2)) {
                    final boolean b = true;
                    if (b) {
                        new dd(super.q, be.w("Note"), B.q(be.w("There is already an account named \"%1.\" Please choose another name."), new String[] { trim }), super.q).setVisible(true);
                        return false;
                    }
                    if (text.length() < 3 && bp.s != 2) {
                        this.w.selectAll();
                        new dd(super.q, be.w("Note"), be.w("Passwords must be at least three characters long.  Please re-enter this information."), super.q).setVisible(true);
                        return false;
                    }
                    if (this.e != null && !this.e.getText().equals(text)) {
                        this.e.selectAll();
                        new dd(super.q, be.w("Note"), be.w("The confirmation password does not match the new password.  Please re-enter this information."), super.q).setVisible(true);
                        return false;
                    }
                    ca.a = trim;
                    ca.q = new dD(text);
                    ca.e = this.w.getSelectedItem();
                    ca.q = this.q.q(ca.e);
                    if (this.q.q() < 0L) {
                        return false;
                    }
                    if (this.w.q() < 0L) {
                        return false;
                    }
                    ca.q = this.q.q();
                    ca.w = this.w.q();
                    ca.q = this.r.getText();
                    ca.w = this.t.getText();
                    ca.p(this.q.q());
                    ca.o(this.w.q());
                    ca.q(this.e.q());
                    ca.w(this.r.q());
                    return true;
                }
                else {
                    ++i;
                }
            }
            final boolean b = false;
            continue;
        }
    }
    
    public final void q(final dK dk) {
        dk.q(be.w("Name:"), this.q, 0);
        dk.q(be.w("Password:"), this.w, 0);
        dk.q(be.w("Confirm Password:"), this.e, 0);
        dk.q(be.w("Decoration:"), this.r, 0);
        dk.q(be.w("Picture:"), this.t, 0);
        final Panel panel;
        (panel = new Panel(new FlowLayout())).add(this.q);
        panel.add(new Label(be.w("Back:")));
        panel.add(this.w);
        panel.add(new Label(be.w("Public:")));
        panel.add(this.e);
        panel.add(new Label(be.w("Private:")));
        panel.add(this.r);
        dk.q(be.w("Color:"), panel, 0);
        dk.q(be.w("Start Date:"), new Component[] { this.q, this.y });
        dk.q(be.w("End Date:"), new Component[] { this.w, this.u });
        final Panel panel2;
        (panel2 = new Panel()).add(this.q);
        panel2.add(this.i);
        dk.q(be.w("Copy Settings:"), panel2, 0);
        dk.q(be.w("Group:"), this.w, 0);
        dk.q(new bZ(this.w), 1, 1.0f, 0.0f);
    }
    
    public final void q() {
        if (super.w) {
            final Vector<cu> vector = new Vector<cu>();
            final Vector<cu> vector2 = new Vector<cu>();
            for (int i = 0; i < this.q(); ++i) {
                final cu cu;
                if ((cu = (cu)this.q(i)).q(63)) {
                    vector2.addElement(cu);
                }
                else {
                    final cu cu2;
                    if ((cu2 = (cu)this.q.e.w(cu.s)) == null || cu.q(cu2) != 0) {
                        vector.addElement(cu);
                    }
                }
            }
            this.w(vector2);
            this.q(vector);
            super.w = false;
        }
    }
    
    private void q(final Vector vector) {
        if (vector.size() == 0) {
            return;
        }
        final dI di;
        (di = new dI(17237761, vector.size())).w = -1;
        di.q = -1;
        for (int i = 0; i < vector.size(); ++i) {
            final cu cu = vector.elementAt(i);
            di.q(i, cu.w());
            di.q(i, 0, cu.s);
            di.q(i, 0, cu.a);
            if (!cu.q(63)) {
                di.q(i, 0, cu.q);
                di.q(i, 1, cu.w());
                di.q(i, 2, cu.g);
                di.q(i, 3, cu.q);
                di.q(i, 4, cu.h);
                di.q(i, 5, cu.w);
                di.q(i, 6, cu.q);
                di.q(i, 8, cu.w);
                di.q(i, 13, cu.e);
                di.q(i, 1, cu.q);
                di.q(i, 2, cu.w);
            }
        }
        super.q.o(di);
    }
    
    private void w(final Vector vector) {
        if (vector.size() == 0) {
            return;
        }
        final dI di;
        (di = new dI(17237762, vector.size())).w = -1;
        di.q = -1;
        for (int i = 0; i < vector.size(); ++i) {
            final cu cu = vector.elementAt(i);
            di.q(i, cu.w());
            di.q(i, 0, cu.s);
        }
        super.q.o(di);
    }
    
    public final void w() {
        super.w();
        try {
            for (int i = 0; i < this.q.e.q; ++i) {
                final cA ca;
                (ca = new cA((cu)this.q.e.q(i))).d(62);
                final int s = ca.s;
                ca.t = (s >= 1000);
                this.e(ca);
                super.q.q(ca, s > 1 || (super.q.q(62) && s > 0));
                ca.e = this.q.q(ca.q);
            }
        }
        finally {}
    }
    
    public final boolean q(final Event event) {
        switch (event.id) {
            case 1001: {
                if (event.target == this.t) {
                    this.q.q(new Date().getTime());
                    this.w.q(new Date().getTime());
                    return true;
                }
                if (event.target == this.y) {
                    this.q.q(this.q.q());
                    this.q.show();
                }
                if (event.target == this.u) {
                    this.w.q(this.w.q());
                    this.w.show();
                }
            }
            case 401: {
                final Component[] components = this.w.getComponents();
                for (int i = 0; i < components.length; ++i) {
                    if (event.target == components[i]) {
                        this.e = true;
                    }
                }
                if (event.target == this.i) {
                    cu cu = null;
                    for (int j = 0; j < this.q.e.q; ++j) {
                        final cu cu2;
                        if ((cu2 = (cu)this.q.e.q(j)).a.equalsIgnoreCase(this.q.getSelectedItem())) {
                            cu = cu2;
                        }
                    }
                    this.q.q(cu.w());
                    this.q.p(cu.w());
                    this.q.o(cu.g);
                    this.q.q(cu.w);
                    this.q.w(cu.e);
                    this.q.q = cu.q;
                    this.q.w = cu.w;
                    this.q.q = cu.q;
                    this.q.h = cu.h;
                    this.q.q = cu.q;
                    this.q((bp)this.q);
                    break;
                }
                break;
            }
            case 1005: {
                if (this.e) {
                    final Component[] components2 = this.w.getComponents();
                    int k = 0;
                    while (k < components2.length) {
                        if (event.target == components2[k]) {
                            this.e = false;
                            if (this.q.q() > 0L && this.w.q() > 0L) {
                                this.q.setText(dx.q(this.q.q(), this.w.q()));
                                break;
                            }
                            break;
                        }
                        else {
                            ++k;
                        }
                    }
                    break;
                }
                break;
            }
        }
        return false;
    }
    
    public bo(final cT ct) {
        super(ct, be.w("Accounts"), be.w("Account"));
        this.w = new dR();
        this.e = false;
        this.q = new TextField(30);
        this.w = new TextField(15);
        this.e = new TextField(15);
        this.t = new ad(be.w("Now"));
        this.q = new dx();
        this.w = new dx();
        this.y = new ad(be.w("Choose"));
        this.u = new ad(be.w("Choose"));
        this.q = new aE(this.q.q.q(), new V(this, this.q));
        this.w = new aE(this.q.q.q(), new V(this, this.w));
        this.q = new Label(bX.q);
        this.e = new dS();
        this.r = new dS();
        this.r = new TextField(15);
        this.t = new TextField(15);
        this.w.w(120);
        this.q(new aX(be.w("Group"), "group"));
        this.w = new cc();
        this.w = new bV("enabled");
        super.q = true;
        this.e.setEchoCharacter('*');
        this.w.setEchoCharacter('*');
        this.q = new dR();
        for (int i = 0; i < this.q.e.q; ++i) {
            final cu cu;
            if (!(cu = (cu)this.q.e.q(i)).a.equalsIgnoreCase("Guest")) {
                this.q.add(cu.a);
            }
        }
        (this.i = new ad(120, 20)).q("Copy Settings");
        final aX ax = new aX(be.w("Option"), "name");
        this.w.w(this.w);
        this.w.r(true);
        this.w.w(ax);
        this.w.e(new ab(this, this, be.w("Connect"), B.q(be.w("Check this item to allow users of this account to connect to %1."), new String[] { dN.e }), 35));
        this.w.e(new ab(this, this, be.w("Change Nickname"), be.w("Check this item to allow users of this account to change their nickname."), 31));
        this.w.e(new ab(this, this, be.w("Change Icon"), be.w("Check this item to allow users of this account to change their personal icon."), 30));
        this.w.e(new ab(this, this, be.w("Change Profile"), be.w("Check this item to allow users of this account to change their profile information, not including nickname or icon."), 37));
        this.q = new ab(this, this, be.w("Change Password"), be.w("Check this item to allow users of this account to change their password."), 45);
        this.w.e(this.q);
        ((cc)(this.w = new ab(this, this, be.w("Must Change Password"), be.w("Check this item to require users of this account to change their password on their next login."), 58))).e(this.w);
        this.w.e(new ab(this, this, be.w("Moderator"), be.w("Check this item to allow users of this account to serve as a moderator in moderated chat rooms."), 60));
        this.w.e(new ab(this, this, be.w("Guest Speaker"), be.w("Check this item to allow users of this account to serve as a guest speaker in moderated chat rooms."), 59));
        this.w.e(new ab(this, this, be.w("Create Permanent Private Rooms"), be.w("Check this item to allow users of this account to create permanent private rooms on-the-fly."), 29));
        this.w.e(new ab(this, this, be.w("Create Temporary Private Rooms"), be.w("Check this item to allow users of this account to create temporary private rooms on-the-fly."), 38));
        this.w.e(new ab(this, this, be.w("Create Permanent Public Rooms"), be.w("Check this item to allow users of this account to create permanent public rooms on-the-fly."), 27));
        this.w.e(new ab(this, this, be.w("Create Temporary Public Rooms"), be.w("Check this item to allow users of this account to create temporary public rooms on-the-fly."), 28));
        this.w.e(new ab(this, this, "Access Reserved Icons", "Check this item to allow users of this account to use icons that have been marked as reserved.", 36));
        this.w.e(new ab(this, this, be.w("Edit Accounts"), be.w("Check this item to allow users of this account to add, edit, and remove accounts."), 52));
        this.w.e(new ab(this, this, be.w("Edit Banned Users"), be.w("Check this item to allow users of this account to add, edit, and remove banned IPs and host names."), 49));
        this.w.e(new ab(this, this, be.w("Edit Banners"), be.w("Check this item to allow users of this account to add, edit, and remove banners."), 57));
        this.w.e(new ab(this, this, be.w("Edit ChatWatch"), be.w("Check this item to allow users of this account to add, and remove ChatWatch words."), 53));
        this.w.e(new ab(this, this, be.w("Edit Shortcut"), be.w("Check this item to allow users of this account to add, and remove Shortcuts."), 50));
        this.w.e(new ab(this, this, be.w("Edit Emoticons"), be.w("Check this item to allow users of this account to add, and remove Emoticons."), 26));
        this.w.e(new ab(this, this, be.w("Edit Stars"), be.w("Check this item to allow users of this account to add, and remove Stars."), 48));
        this.w.e(new ab(this, this, be.w("Edit ChatCast"), be.w("Check this item to allow users of this account to add, edit, and remove ChatCast messages."), 54));
        this.w.e(new ab(this, this, be.w("Edit Hosts"), be.w("Check this item to allow users of this account to add, edit, and remove hosts."), 46));
        this.w.e(new ab(this, this, be.w("Edit Icons and Themes"), be.w("Check this item to allow users of this account to add, edit, and remove icons and themes."), 55));
        this.w.e(new ab(this, this, be.w("Edit Options"), be.w("Check this item to allow users of this account to set options for logo link URL and transcripts."), 47));
        this.w.e(new ab(this, this, be.w("Edit Rooms"), be.w("Check this item to allow users of this account to add, edit, and remove rooms."), 56));
        this.w.e(new ab(this, this, be.w("Add/Edit Notes"), be.w("Check this item to allow users of this account to view, add, edit, remove notes."), 16));
        this.w.e(new ab(this, this, be.w("View Notes"), be.w("Check this item to allow users of this account to view notes."), 15));
        this.w.e(new ab(this, this, be.w("Kick Users"), be.w("Check this item to allow users of this account to kick other users."), 44));
        this.w.e(new ab(this, this, be.w("Do Not Kick"), be.w("Check this item to allow users of this account to not be kicked."), 34));
        this.w.e(new ab(this, this, be.w("Give OnceKick"), be.w("Check this item to allow users of this account to give OnceKick for other users."), 12));
        this.w.e(new ab(this, this, be.w("Kick Users On Same IP"), be.w("Check this item to allow users of this account to kick users connected on the same IP address."), 11));
        this.w.e(new ab(this, this, be.w("Show Users On Same IP"), be.w("Check this item to allow users of this account to show users connected on same IP address."), 9));
        this.w.e(new ab(this, this, be.w("Send Chat Broadcast"), be.w("Check this item to allow users of this account to send Chat Broadcast messages."), 42));
        this.w.e(new ab(this, this, be.w("Send Private Messages"), be.w("Check this item to allow users of this account to send private messages to other users."), 43));
        this.w.e(new ab(this, this, be.w("Ghost"), be.w("Check this item to allow users of this account to be invisible for users and masters."), 32));
        this.w.e(new ab(this, this, be.w("Invisible"), be.w("Check this item to allow users of this account to be invisible and see other invisible users."), 24));
        this.w.e(new ab(this, this, be.w("Bot"), be.w("Check this item if this account is a bot user."), 22));
        this.w.e(new ab(this, this, be.w("File Transfer Receive"), be.w("Check this item to allow users of this account to receive files."), 21));
        this.w.e(new ab(this, this, be.w("File Transfer Send"), be.w("Check this item to allow users of this account to send files."), 20));
        this.w.e(new ab(this, this, be.w("Add Banned Users"), be.w("Check this item to allow users of this account to add banned users."), 1));
        this.w.e(new ab(this, this, be.w("Remove Banned Users"), be.w("Check this item to allow users of this account to remove banned users."), 2));
        this.w.e(new ab(this, this, be.w("Ban Country"), be.w("Check this item to allow users of this account to ban users country."), 39));
        this.w.e(new ab(this, this, be.w("Change Own Color"), be.w("Check this item to allow users of this account to change his own color."), 3));
        this.w.e(new ab(this, this, be.w("Change Own Background Color"), be.w("Check this item to allow users of this account to change his own background color."), 17));
        this.w.e(new ab(this, this, be.w("Add Predefined Colors"), be.w("Check this item to allow users of this account to add predefinsd colors."), 4));
        this.w.e(new ab(this, this, be.w("Change User Color"), be.w("Check this item to allow users of this account to change users colors."), 5));
        this.w.e(new ab(this, this, be.w("Change Guest Background Color"), be.w("Check this item to allow users of this account to change guest background colors."), 18));
        this.w.e(new ab(this, this, be.w("Change User Display Name"), be.w("Check this item to allow users of this account to change users display name."), 8));
        this.w.e(new ab(this, this, be.w("Change Guest Icon"), be.w("Check this item to allow users of this account to change guest icon."), 10));
        this.w.e(new ab(this, this, be.w("Change Guest Group"), be.w("Check this item to allow users of this account to assign guest a group."), 14));
        this.w.e(new ab(this, this, be.w("Enable And Disable Users From Writing In Public Rooms"), be.w("Check this item to allow users of this account to enable and disable users from writing in public rooms."), 6));
        this.w.e(new ab(this, this, be.w("Move Users To Other Rooms"), be.w("Check this item to allow users of this account to move users to other rooms."), 7));
        this.w.e(new ab(this, this, be.w("Enter Private Rooms Without Password"), be.w("Check this item to allow users of this account to enter private rooms without password."), 40));
        this.w.e(new ab(this, this, be.w("Restart Site"), be.w("Check this item to allow users of this account to restart given site."), 13));
        this.w.e(new ab(this, this, be.w("View IP/Host/Country"), be.w("Check this item to allow users of this account to view IP/Host/Country information."), 41));
        this.w.e(new ab(this, this, be.w("Login to Full Room"), be.w("Check this item to allow users of this account to login to full room."), 19));
        this.w.e(new ab(this, this, be.w("Change Background Writing"), be.w("Check this item to allow users of this account to change background writing."), 0));
        this.w.resize(new Dimension(50, this.w.e() * 10));
        this.w.w(false);
        this.w.e(0);
    }
    
    static {
        Calendar.getInstance();
        new SimpleDateFormat("dd/MM/yyyy");
    }
}

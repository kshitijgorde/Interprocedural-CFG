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

public final class bt extends bs
{
    private TextField q;
    private TextField w;
    private TextField e;
    private w w;
    private C w;
    protected bU q;
    private bv q;
    private bv w;
    private T q;
    private T w;
    private Label q;
    private g y;
    private g u;
    private g i;
    private j e;
    private j r;
    private TextField r;
    private TextField t;
    private Choice q;
    private g o;
    private Choice w;
    private ab q;
    private ab w;
    private boolean e;
    
    public final bZ q() {
        final bU bu;
        (bu = new bU(-999, "")).q(2590695664122855432L);
        bu.o(52);
        return bu;
    }
    
    private void t() {
        this.q.q(0L);
        this.w.q(0L);
        this.q.setText(ds.q);
    }
    
    public final void q(final bZ bz) {
        final bU q = (bU)bz;
        this.q.setText(q.getName());
        this.t.setText(q.w);
        if (q.q > 0L) {
            this.q.q(q.q);
            if (q.w > 0L) {
                this.w.q(q.w);
                this.q.setText(T.q(this.q.q(), this.w.q()));
            }
            else {
                this.t();
            }
        }
        else {
            this.t();
        }
        if ("Guest".equalsIgnoreCase(q.getName()) || "Chatmaster".equalsIgnoreCase(q.getName())) {
            this.q.disable();
            this.w.disable();
            this.q.disable();
            this.y.disable();
        }
        else {
            this.q.enable();
            this.w.enable();
            this.q.enable();
            this.y.enable();
        }
        this.e.setBackground(new Color(q.w()));
        this.r.setBackground(new Color(q.e()));
        this.r.setText(q.q);
        this.w.removeAll();
        this.w.add(cl.q);
        for (int i = 0; i < this.q.x.q(); ++i) {
            final cl cl;
            if ((cl = (cl)this.q.x.q(i)).q()) {
                this.w.add(cl.getName());
            }
            if (cl.q() == q.q) {
                this.w.select(cl.getName());
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
        final int q2;
        if ((q2 = bz.q()) < 1000 && q2 != -999) {
            this.q.disable();
            if (q2 == 2) {
                this.w.disable();
                this.e.disable();
            }
        }
        else {
            this.w.enable();
            this.e.enable();
            this.q.enable();
        }
        this.w.q(this.w, q2 != 2);
        this.w.q(this.q, q2 != 2);
        this.q.q(q.y());
        this.w.q(q.t());
        this.q = q;
        this.w.t();
    }
    
    public final boolean q(final bZ bz) {
        final bU bu = (bU)bz;
        final String text = this.w.getText();
        final String trim;
        if ((trim = this.q.getText().trim()).length() == 0) {
            new b(super.q, eb.q("Note"), eb.q("You must provide a name for this account. Please re-enter this information."), super.q).setVisible(true);
            return false;
        }
        if ("Admin".equalsIgnoreCase(trim)) {
            new b(super.q, eb.q("Note"), eb.q("This account name is reserved.  Please choose another name."), super.q).setVisible(true);
            return false;
        }
        final String s = trim;
        final bU bu2 = (bU)bz;
        final String s2 = s;
        int i = 0;
        while (true) {
            while (i < this.q()) {
                final bZ q = this.q(i);
                if (bu2 != q && q.getName().equalsIgnoreCase(s2)) {
                    final boolean b = true;
                    if (b) {
                        new b(super.q, eb.q("Note"), ec.q(eb.q("There is already an account named \"%1.\" Please choose another name."), new String[] { trim }), super.q).setVisible(true);
                        return false;
                    }
                    if (text.length() < 3 && bz.q() != 2) {
                        this.w.selectAll();
                        new b(super.q, eb.q("Note"), eb.q("Passwords must be at least three characters long.  Please re-enter this information."), super.q).setVisible(true);
                        return false;
                    }
                    if (this.e != null && !this.e.getText().equals(text)) {
                        this.e.selectAll();
                        new b(super.q, eb.q("Note"), eb.q("The confirmation password does not match the new password.  Please re-enter this information."), super.q).setVisible(true);
                        return false;
                    }
                    bu.a_(trim);
                    bu.q = new ep(text);
                    bu.e = this.w.getSelectedItem();
                    bu.q = this.q.q(bu.e);
                    if (this.q.q() < 0L) {
                        return false;
                    }
                    if (this.w.q() < 0L) {
                        return false;
                    }
                    bu.q = this.q.q();
                    bu.w = this.w.q();
                    bu.q = this.r.getText();
                    bu.w = this.t.getText();
                    bu.y(this.q.q());
                    bu.t(this.w.q());
                    bu.q(this.e.q());
                    bu.w(this.r.q());
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
    
    public final void q(final bw bw) {
        bw.q(eb.q("Name:"), this.q);
        bw.q(eb.q("Password:"), this.w);
        bw.q(eb.q("Confirm Password:"), this.e);
        bw.q(eb.q("Decoration:"), this.r);
        bw.q(eb.q("Picture:"), this.t);
        final Panel panel;
        (panel = new Panel(new FlowLayout())).add(this.q);
        panel.add(new Label(eb.q("Back:")));
        panel.add(this.w);
        panel.add(new Label(eb.q("Public:")));
        panel.add(this.e);
        panel.add(new Label(eb.q("Private:")));
        panel.add(this.r);
        bw.q(eb.q("Color:"), panel);
        bw.q(eb.q("Start Date:"), new Component[] { this.q, this.u });
        bw.q(eb.q("End Date:"), new Component[] { this.w, this.i });
        final Panel panel2;
        (panel2 = new Panel()).add(this.q);
        panel2.add(this.o);
        bw.q(eb.q("Copy Settings:"), panel2);
        bw.q(eb.q("Group:"), this.w);
        bw.q(new t(this.w), 1, 1.0f, 0.0f);
    }
    
    public final void q() {
        if (super.q) {
            final Vector<bT> vector = new Vector<bT>();
            final Vector<bT> vector2 = new Vector<bT>();
            for (int i = 0; i < this.q(); ++i) {
                final bT bt;
                if ((bt = (bT)this.q(i)).q(63)) {
                    vector2.addElement(bt);
                }
                else {
                    final bT bt2;
                    if ((bt2 = (bT)this.q.b.w(bt.q())) == null || bt.q(bt2) != 0) {
                        vector.addElement(bt);
                    }
                }
            }
            this.w(vector2);
            this.q(vector);
            super.q = false;
        }
    }
    
    private void q(final Vector vector) {
        if (vector.size() == 0) {
            return;
        }
        final es es;
        (es = new es(17237761, vector.size())).w = -1;
        es.q = -1;
        for (int i = 0; i < vector.size(); ++i) {
            final bT bt = vector.elementAt(i);
            es.q(i, bt.q());
            es.q(i, 0, bt.q());
            es.q(i, 0, bt.getName());
            if (!bt.q(63)) {
                es.q(i, 0, bt.q);
                es.q(i, 1, bt.y());
                es.q(i, 2, bt.t());
                es.q(i, 3, bt.q);
                es.q(i, 4, bt.i);
                es.q(i, 5, bt.w());
                es.q(i, 6, bt.q);
                es.q(i, 8, bt.w);
                es.q(i, 11, bt.e());
                es.q(i, 1, bt.q);
                es.q(i, 2, bt.w);
            }
        }
        super.q.q(es);
    }
    
    private void w(final Vector vector) {
        if (vector.size() == 0) {
            return;
        }
        final es es;
        (es = new es(17237762, vector.size())).w = -1;
        es.q = -1;
        for (int i = 0; i < vector.size(); ++i) {
            final bT bt = vector.elementAt(i);
            es.q(i, bt.q());
            es.q(i, 0, bt.q());
        }
        super.q.q(es);
    }
    
    public final void w() {
        super.w();
        final dW b = this.q.b;
        dW.q();
        try {
            for (int i = 0; i < this.q.b.q(); ++i) {
                final bU bu;
                (bu = new bU((bT)this.q.b.q(i))).o(62);
                final int q = bu.q();
                bu.q = (q >= 1000);
                this.e(bu);
                super.q.q(bu, q > 1 || (super.q.q(62) && q > 0));
                bu.e = this.q.q(bu.q);
            }
        }
        finally {
            final dW b2 = this.q.b;
            dW.w();
        }
    }
    
    public final boolean q(final Event event) {
        switch (event.id) {
            case 1001: {
                if (event.target == this.y) {
                    this.q.q(new Date().getTime());
                    this.w.q(new Date().getTime());
                    return true;
                }
                if (event.target == this.u) {
                    this.q.q(this.q.q());
                    this.q.show();
                }
                if (event.target == this.i) {
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
                if (event.target == this.o) {
                    bT bt = null;
                    for (int j = 0; j < this.q.b.q(); ++j) {
                        final bT bt2;
                        if ((bt2 = (bT)this.q.b.q(j)).getName().equalsIgnoreCase(this.q.getSelectedItem())) {
                            bt = bt2;
                        }
                    }
                    this.q.q(bt.q());
                    this.q.y(bt.y());
                    this.q.t(bt.t());
                    this.q.q(bt.w());
                    this.q.w(bt.e());
                    this.q.q = bt.q;
                    this.q.w = bt.w;
                    this.q.q = bt.q;
                    this.q.i = bt.i;
                    this.q.q = bt.q;
                    this.q.a_(this.q.getText());
                    this.q.q = new ep(this.w.getText());
                    this.q((bZ)this.q);
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
                                this.q.setText(T.q(this.q.q(), this.w.q()));
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
    
    public bt(final dz dz) {
        super(dz, eb.q("Accounts"), eb.q("Account"));
        this.w = new h();
        this.e = false;
        this.q = new TextField(30);
        this.w = new TextField(15);
        this.e = new TextField(15);
        this.y = new g(eb.q("Now"));
        this.q = new T();
        this.w = new T();
        this.u = new g(eb.q("Choose"));
        this.i = new g(eb.q("Choose"));
        this.q = new ab(this.q.q.q(), new bu(this, this.q));
        this.w = new ab(this.q.q.q(), new bu(this, this.w));
        this.q = new Label(ds.q);
        this.e = new j();
        this.r = new j();
        this.r = new TextField(15);
        this.t = new TextField(15);
        this.w.w(120);
        this.q(new y(eb.q("Group"), "group"));
        this.w = new w();
        this.w = new C("enabled");
        super.w = true;
        this.e.setEchoCharacter('*');
        this.w.setEchoCharacter('*');
        this.q = new h();
        for (int i = 0; i < this.q.b.q(); ++i) {
            final bT bt;
            if (!(bt = (bT)this.q.b.q(i)).getName().equalsIgnoreCase("Guest")) {
                this.q.add(bt.getName());
            }
        }
        (this.o = new g(120, 20)).q("Copy Settings");
        final y y = new y(eb.q("Option"), "name");
        this.w.w(this.w);
        this.w.r(true);
        this.w.w(y);
        this.w.e(new bv(this, this, eb.q("Connect"), ec.q(eb.q("Check this item to allow users of this account to connect to %1."), new String[] { a.e }), 35));
        this.w.e(new bv(this, this, eb.q("Change Nickname"), eb.q("Check this item to allow users of this account to change their nickname."), 31));
        this.w.e(new bv(this, this, eb.q("Change Icon"), eb.q("Check this item to allow users of this account to change their personal icon."), 30));
        this.w.e(new bv(this, this, eb.q("Change Profile"), eb.q("Check this item to allow users of this account to change their profile information, not including nickname or icon."), 37));
        this.q = new bv(this, this, eb.q("Change Password"), eb.q("Check this item to allow users of this account to change their password."), 45);
        this.w.e(this.q);
        ((w)(this.w = new bv(this, this, eb.q("Must Change Password"), eb.q("Check this item to require users of this account to change their password on their next login."), 58))).e(this.w);
        this.w.e(new bv(this, this, eb.q("Moderator"), eb.q("Check this item to allow users of this account to serve as a moderator in moderated chat rooms."), 60));
        this.w.e(new bv(this, this, eb.q("Guest Speaker"), eb.q("Check this item to allow users of this account to serve as a guest speaker in moderated chat rooms."), 59));
        this.w.e(new bv(this, this, eb.q("Create Permanent Private Rooms"), eb.q("Check this item to allow users of this account to create permanent private rooms on-the-fly."), 29));
        this.w.e(new bv(this, this, eb.q("Create Temporary Private Rooms"), eb.q("Check this item to allow users of this account to create temporary private rooms on-the-fly."), 38));
        this.w.e(new bv(this, this, eb.q("Create Permanent Public Rooms"), eb.q("Check this item to allow users of this account to create permanent public rooms on-the-fly."), 27));
        this.w.e(new bv(this, this, eb.q("Create Temporary Public Rooms"), eb.q("Check this item to allow users of this account to create temporary public rooms on-the-fly."), 28));
        this.w.e(new bv(this, this, eb.q("Access Reserved Icons"), "Check this item to allow users of this account to use icons that have been marked as reserved.", 36));
        this.w.e(new bv(this, this, eb.q("Edit Accounts"), eb.q("Check this item to allow users of this account to add, edit, and remove accounts."), 52));
        this.w.e(new bv(this, this, eb.q("Edit Banned Users"), eb.q("Check this item to allow users of this account to add, edit, and remove banned IPs and host names."), 49));
        this.w.e(new bv(this, this, eb.q("Edit Banners"), eb.q("Check this item to allow users of this account to add, edit, and remove banners."), 57));
        this.w.e(new bv(this, this, eb.q("Edit ChatWatch"), eb.q("Check this item to allow users of this account to add, and remove ChatWatch words."), 53));
        this.w.e(new bv(this, this, eb.q("Edit Shortcut"), eb.q("Check this item to allow users of this account to add, and remove Shortcuts."), 50));
        this.w.e(new bv(this, this, eb.q("Edit Emoticons"), eb.q("Check this item to allow users of this account to add, and remove Emoticons."), 26));
        this.w.e(new bv(this, this, eb.q("Edit Stars"), eb.q("Check this item to allow users of this account to add, and remove Stars."), 48));
        this.w.e(new bv(this, this, eb.q("Edit ChatCast"), eb.q("Check this item to allow users of this account to add, edit, and remove ChatCast messages."), 54));
        this.w.e(new bv(this, this, eb.q("Edit Hosts"), eb.q("Check this item to allow users of this account to add, edit, and remove hosts."), 46));
        this.w.e(new bv(this, this, eb.q("Edit Icons and Themes"), eb.q("Check this item to allow users of this account to add, edit, and remove icons and themes."), 55));
        this.w.e(new bv(this, this, eb.q("Edit Options"), eb.q("Check this item to allow users of this account to set options for logo link URL and transcripts."), 47));
        this.w.e(new bv(this, this, eb.q("Edit Rooms"), eb.q("Check this item to allow users of this account to add, edit, and remove rooms."), 56));
        this.w.e(new bv(this, this, eb.q("Add/Edit Notes"), eb.q("Check this item to allow users of this account to view, add, edit, remove notes."), 16));
        this.w.e(new bv(this, this, eb.q("View Notes"), eb.q("Check this item to allow users of this account to view notes."), 15));
        this.w.e(new bv(this, this, eb.q("Add Sms"), eb.q("Check this item to allow users of this account to add sms."), 70));
        this.w.e(new bv(this, this, eb.q("Edit Sms"), eb.q("Check this item to allow users of this account to edit sms."), 71));
        this.w.e(new bv(this, this, eb.q("Kick Users"), eb.q("Check this item to allow users of this account to kick other users."), 44));
        this.w.e(new bv(this, this, eb.q("Do Not Kick"), eb.q("Check this item to allow users of this account to not be kicked."), 34));
        this.w.e(new bv(this, this, eb.q("Give OnceKick"), eb.q("Check this item to allow users of this account to give OnceKick for other users."), 12));
        this.w.e(new bv(this, this, eb.q("Kick Users On Same IP"), eb.q("Check this item to allow users of this account to kick users connected on the same IP address."), 11));
        this.w.e(new bv(this, this, eb.q("Show Users On Same IP"), eb.q("Check this item to allow users of this account to show users connected on same IP address."), 9));
        this.w.e(new bv(this, this, eb.q("Send Chat Broadcast"), eb.q("Check this item to allow users of this account to send Chat Broadcast messages."), 42));
        this.w.e(new bv(this, this, eb.q("Send Private Messages"), eb.q("Check this item to allow users of this account to send private messages to other users."), 43));
        this.w.e(new bv(this, this, eb.q("Ghost"), eb.q("Check this item to allow users of this account to be invisible for users and masters."), 32));
        this.w.e(new bv(this, this, eb.q("Invisible"), eb.q("Check this item to allow users of this account to be invisible and see other invisible users."), 24));
        this.w.e(new bv(this, this, eb.q("Bot"), eb.q("Check this item if this account is a bot user."), 22));
        this.w.e(new bv(this, this, eb.q("File Transfer Receive"), eb.q("Check this item to allow users of this account to receive files."), 21));
        this.w.e(new bv(this, this, eb.q("File Transfer Send"), eb.q("Check this item to allow users of this account to send files."), 20));
        this.w.e(new bv(this, this, eb.q("Add Banned Users"), eb.q("Check this item to allow users of this account to add banned users."), 1));
        this.w.e(new bv(this, this, eb.q("Remove Banned Users"), eb.q("Check this item to allow users of this account to remove banned users."), 2));
        this.w.e(new bv(this, this, eb.q("Ban Country"), eb.q("Check this item to allow users of this account to ban users country."), 39));
        this.w.e(new bv(this, this, eb.q("Add Ban Forever"), eb.q("Check this item to allow users of this account to add ban forever."), 85));
        this.w.e(new bv(this, this, eb.q("Remove/Edit Ban Forever"), eb.q("Check this item to allow users of this account to remove/edit ban forever."), 86));
        this.w.e(new bv(this, this, eb.q("Change Own Color"), eb.q("Check this item to allow users of this account to change his own color."), 3));
        this.w.e(new bv(this, this, eb.q("Change Own Background Color"), eb.q("Check this item to allow users of this account to change his own background color."), 17));
        this.w.e(new bv(this, this, eb.q("Add Predefined Colors"), eb.q("Check this item to allow users of this account to add predefinsd colors."), 4));
        this.w.e(new bv(this, this, eb.q("Change User Color"), eb.q("Check this item to allow users of this account to change users colors."), 5));
        this.w.e(new bv(this, this, eb.q("Change Guest Background Color"), eb.q("Check this item to allow users of this account to change guest background colors."), 18));
        this.w.e(new bv(this, this, eb.q("Change User Display Name"), eb.q("Check this item to allow users of this account to change users display name."), 8));
        this.w.e(new bv(this, this, eb.q("Change Guest Icon"), eb.q("Check this item to allow users of this account to change guest icon."), 10));
        this.w.e(new bv(this, this, eb.q("Change Guest Group"), eb.q("Check this item to allow users of this account to assign guest a group."), 14));
        this.w.e(new bv(this, this, eb.q("Change Own Group"), eb.q("Check this item to allow users of this account to change own group."), 73));
        this.w.e(new bv(this, this, eb.q("Enable And Disable Users From Writing In Public Rooms"), eb.q("Check this item to allow users of this account to enable and disable users from writing in public rooms."), 6));
        this.w.e(new bv(this, this, eb.q("Move Users To Other Rooms"), eb.q("Check this item to allow users of this account to move users to other rooms."), 7));
        this.w.e(new bv(this, this, eb.q("Enter Private Rooms Without Password"), eb.q("Check this item to allow users of this account to enter private rooms without password."), 40));
        this.w.e(new bv(this, this, eb.q("Restart Site"), eb.q("Check this item to allow users of this account to restart given site."), 13));
        this.w.e(new bv(this, this, eb.q("View IP/Host/Country"), eb.q("Check this item to allow users of this account to view IP/Host/Country information."), 41));
        this.w.e(new bv(this, this, eb.q("Login to Full Room"), eb.q("Check this item to allow users of this account to login to full room."), 19));
        this.w.e(new bv(this, this, eb.q("Change Background Writing"), eb.q("Check this item to allow users of this account to change background writing."), 0));
        this.w.e(new bv(this, this, eb.q("See User Country"), eb.q("Check this item to allow users of this account to see other users country."), 66));
        this.w.e(new bv(this, this, eb.q("Search Users List"), eb.q("Check this item to allow users of this account to search users list."), 67));
        this.w.e(new bv(this, this, eb.q("Send Warn"), eb.q("Check this item to allow users of this account to send warn messages."), 68));
        this.w.e(new bv(this, this, eb.q("Edit Font Style"), eb.q("Check this item to allow users of this account to edit input font style."), 69));
        this.w.e(new bv(this, this, eb.q("View Shortcuts Nickname"), eb.q("Check this item to allow users of this account to view shortcuts nickname."), 75));
        this.w.e(new bv(this, this, eb.q("Hide Star Checkbox"), eb.q("Check this item to allow users of this account to be unable to hide his star."), 77));
        this.w.e(new bv(this, this, eb.q("Hide Icon Checkbox"), eb.q("Check this item to allow users of this account to be unable to hide icon checkbox."), 78));
        this.w.e(new bv(this, this, eb.q("Receive System Messages"), eb.q("Check this item to allow users of this account to receive system messages."), 79));
        this.w.e(new bv(this, this, eb.q("Invite To Chat Group"), eb.q("Check this item to allow users of this account to invite other users to chat group."), 80));
        this.w.e(new bv(this, this, eb.q("Up Guest Nickname"), eb.q("Check this item to allow users of this account to up other guest nicknames."), 81));
        this.w.e(new bv(this, this, eb.q("Edit Bad Word"), eb.q("Check this item to allow users of this account to bad words."), 72));
        this.w.resize(new Dimension(50, this.w.e() * 10));
        this.w.w(false);
        this.w.e(0);
    }
    
    static {
        Calendar.getInstance();
        new SimpleDateFormat("dd/MM/yyyy");
    }
}

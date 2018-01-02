// 
// Decompiled by Procyon v0.5.30
// 

package doook;

import java.awt.event.MouseListener;
import java.awt.LayoutManager;
import java.awt.FlowLayout;
import java.awt.Component;
import java.awt.Color;
import java.util.StringTokenizer;
import java.awt.Panel;
import java.awt.Choice;
import java.awt.TextField;

public class bo extends bF
{
    private TextField a;
    private TextField b;
    private TextField c;
    private TextField x;
    private TextField y;
    private Choice q;
    private Choice r;
    private Choice s;
    private Panel c;
    private Choice t;
    private aS t;
    private Panel d;
    private l e;
    private cf a;
    protected aJ a;
    private bn a;
    private bn b;
    private u l;
    private aX d;
    private aX b;
    private Choice j;
    private TextField z;
    
    public cF a() {
        final aW aw = new aW(-999, "");
        aw.a(2590695664122855424L);
        aw.h(52);
        return aw;
    }
    
    public void a(final cF cf) {
        final aW a = (aW)cf;
        this.a.setText(a.f());
        if (a.ak != null) {
            this.y.setText(a.ak);
        }
        else {
            this.y.setText("");
        }
        if (a.aj != null) {
            this.x.setText(a.aj);
        }
        else {
            this.x.setText("");
        }
        if (a.al != null) {
            this.z.setText(a.al);
        }
        else {
            this.z.setText("");
        }
        if (a.ai != null && !a.ai.equals("") && !a.ai.equals("--")) {
            final StringTokenizer stringTokenizer = new StringTokenizer(a.ai, "-");
            this.q.select(stringTokenizer.nextToken());
            this.r.select(stringTokenizer.nextToken());
            this.s.select(stringTokenizer.nextToken());
        }
        else {
            this.q.select(0);
            this.r.select(0);
            this.s.select(0);
        }
        this.j.select(0);
        if (a.aO != 0) {
            this.l.l.a(false);
            try {
                for (int i = 0; i < this.l.l.b(); ++i) {
                    if (a.aO == ((cF)this.l.l.a(i)).h()) {
                        this.j.select(i + 1);
                        break;
                    }
                }
            }
            finally {
                this.l.l.a();
            }
        }
        this.d.a(new Color(a.aN));
        this.b.a(new Color(a.d));
        if (a.b != null) {
            this.b.setText(a.b.toString());
            this.c.setText(a.b.toString());
        }
        else {
            this.b.setText("");
            this.c.setText("");
        }
        final int h = cf.h();
        if (h < 1000 && h != -999) {
            this.a.disable();
            if (h == 2) {
                this.b.disable();
                this.c.disable();
            }
        }
        else {
            this.b.enable();
            this.c.enable();
            this.a.enable();
        }
        this.e.a(this.b, h != 2);
        this.e.a(this.a, h != 2);
        this.a = a;
        this.e.g();
    }
    
    public boolean a(final cF cf) {
        final aW aw = (aW)cf;
        final String text = this.b.getText();
        final String trim = this.a.getText().trim();
        if (trim.length() == 0) {
            new E(this.b(), ao.e("Note"), ao.e("You must provide a name for this account. Please re-enter this information."), super.i).setVisible(true);
            return false;
        }
        if ("Admin".equalsIgnoreCase(trim)) {
            new E(this.b(), ao.e("Note"), ao.e("This account name is reserved.  Please choose another name."), super.i).setVisible(true);
            return false;
        }
        if (this.a(trim, (aW)cf)) {
            new E(this.b(), ao.e("Note"), am.a(ao.e("There is already an account named \"%1.\" Please choose another name."), new String[] { trim }), super.i).setVisible(true);
            return false;
        }
        if (text.length() < 3 && cf.h() != 2) {
            this.b.selectAll();
            new E(this.b(), ao.e("Note"), ao.e("Passwords must be at least three characters long.  Please re-enter this information."), super.i).setVisible(true);
            return false;
        }
        if (this.c != null && !this.c.getText().equals(text)) {
            this.c.selectAll();
            new E(this.b(), ao.e("Note"), ao.e("The confirmation password does not match the new password.  Please re-enter this information."), super.i).setVisible(true);
            return false;
        }
        if ((this.q.getSelectedIndex() != 0 || this.r.getSelectedIndex() != 0 || this.s.getSelectedIndex() != 0) && (this.q.getSelectedIndex() == 0 || this.r.getSelectedIndex() == 0 || this.s.getSelectedIndex() == 0)) {
            new E(this.b(), ao.e("Note"), ao.e("Invalid expiry date for this account."), super.i).setVisible(true);
            return false;
        }
        aw.d(trim);
        aw.aN = this.d.aA;
        aw.d = this.b.aA;
        aw.b = new a(text);
        aw.aj = this.x.getText();
        aw.ak = this.y.getText();
        aw.ai = this.q.getSelectedItem() + "-" + this.r.getSelectedItem() + "-" + this.s.getSelectedItem();
        if (this.j.getSelectedIndex() != 0) {
            aw.aO = ((cF)this.l.l.a(this.j.getSelectedIndex() - 1)).h();
        }
        else {
            aw.aO = 0;
        }
        aw.al = this.z.getText();
        return true;
    }
    
    public void a(final bk bk) {
        bk.a(ao.e("Name:"), this.a);
        bk.a(ao.e("Password:"), this.b);
        bk.a(ao.e("Confirm Password:"), this.c);
        bk.a(ao.e("User Color:"), this.d);
        bk.a(ao.e("Back Color:"), this.b);
        bk.a(ao.e("User Image:"), this.x);
        bk.a(ao.e("Nickname Shortcut:"), this.y);
        bk.a(ao.e("Expiry Date:"), this.c);
        bk.a(ao.e("User Group:"), this.j);
        bk.a(ao.e("Notes:"), this.z);
        bk.a(ao.e("Copy Settings:"), this.d);
        bk.a(new aR(this.e), 1, 1.0f, 0.0f);
    }
    
    public boolean a(final String s, final aW aw) {
        for (int i = 0; i < this.d(); ++i) {
            final cF a = this.a(i);
            if (aw != a && a.f().equalsIgnoreCase(s)) {
                return true;
            }
        }
        return false;
    }
    
    public void c() {
        if (super.e) {
            final cD cd = new cD(67335, this.j());
            cd.j = -1;
            cd.o = -1;
            int i = 0;
            int n = 0;
            while (i < this.d()) {
                final aJ aj = (aJ)this.a(i);
                if (aj.aw) {
                    cd.a(n, aj.d());
                    cd.a(n, 0, aj.h());
                    if (!aj.d(63)) {
                        cd.a(n, 0, aj.f());
                        cd.a(n, 0, aj.b);
                        cd.a(n, 1, aj.aN);
                        cd.a(n, 2, aj.d);
                        cd.a(n, 3, aj.aO);
                        cd.a(n, 1, aj.aj);
                        cd.a(n, 2, aj.ai);
                        cd.a(n, 3, aj.ak);
                        cd.a(n, 4, aj.al);
                    }
                    ++n;
                }
                ++i;
            }
            super.i.o(cd);
            super.e = false;
        }
    }
    
    public void d() {
        super.d();
        super.i.g.a(false);
        try {
            for (int i = 0; i < super.i.g.b(); ++i) {
                final aW aw = new aW((aJ)super.i.g.a(i));
                final int h = aw.h();
                aw.a = (h >= 1000);
                this.b(aw);
                super.f.a(aw, h > 1 || (super.i.d(62) && h > 0));
            }
        }
        finally {
            super.i.g.a();
        }
    }
    
    public void b(final cF cf) {
        super.b(cf);
        super.f.a(cf, new Color(cf.aN), Color.white, cf.d);
    }
    
    public bo(final u l) {
        super(l, ao.e("Accounts"), ao.e("Account"));
        this.l = l;
        this.a = new TextField(30);
        this.b = new TextField(15);
        this.c = new TextField(15);
        this.x = new TextField(30);
        this.y = new TextField(30);
        this.z = new TextField(30);
        (this.j = new Choice()).setForeground(Color.black);
        this.j.addItem("");
        l.l.a(false);
        try {
            for (int i = 0; i < l.l.b(); ++i) {
                this.j.addItem(((cF)l.l.a(i)).f());
            }
        }
        finally {
            l.l.a();
        }
        (this.t = new aS(120, 20)).a(ao.e("Copy Settings"));
        (this.t = new Choice()).setForeground(Color.black);
        l.g.a(false);
        try {
            for (int j = 0; j < l.g.b(); ++j) {
                this.t.addItem(((cF)l.g.a(j)).f());
            }
        }
        finally {
            l.g.a();
        }
        (this.d = new Panel()).setLayout(new FlowLayout());
        this.d.add(this.t);
        this.d.add(this.t);
        this.t.addMouseListener(new cl(this));
        (this.q = new Choice()).addItem("");
        for (int k = 1; k < 32; ++k) {
            this.q.addItem(String.valueOf(k));
        }
        (this.r = new Choice()).addItem("");
        for (int n = 1; n < 13; ++n) {
            this.r.addItem(String.valueOf(n));
        }
        (this.s = new Choice()).addItem("");
        for (int n2 = 0; n2 < 10; ++n2) {
            this.s.addItem(String.valueOf(2008 + n2));
        }
        (this.c = new Panel()).setLayout(new FlowLayout());
        this.c.add(this.q);
        this.c.add(this.r);
        this.c.add(this.s);
        (this.d = new aX(l)).a(l.k, false, true);
        (this.b = new aX(l)).a(l.k, false, true);
        this.e = new l();
        this.a = new cf("enabled");
        super.k = true;
        this.c.setEchoCharacter('*');
        this.b.setEchoCharacter('*');
        final j m = new j(ao.e("Option"), "name");
        this.e.b(this.a);
        this.a.d(true);
        this.e.b(m);
        this.e.c(new bn(this, ao.e("Connect"), am.a(ao.e("Check this item to allow users of this account to connect to %1."), new String[] { doook.z.G }), 35));
        this.e.c(new bn(this, ao.e("Change Nickname"), ao.e("Check this item to allow users of this account to change their nickname."), 31));
        this.e.c(new bn(this, ao.e("Change Icon"), ao.e("Check this item to allow users of this account to change their personal icon."), 30));
        this.e.c(new bn(this, ao.e("Change Profile"), ao.e("Check this item to allow users of this account to change their profile information, not including nickname or icon."), 37));
        this.a = new bn(this, ao.e("Change Password"), ao.e("Check this item to allow users of this account to change their password."), 45);
        this.e.c(this.a);
        this.b = new bn(this, ao.e("Must Change Password"), ao.e("Check this item to require users of this account to change their password on their next login."), 58);
        this.e.c(this.b);
        this.e.c(new bn(this, ao.e("Moderator"), ao.e("Check this item to allow users of this account to serve as a moderator in moderated chat rooms."), 60));
        this.e.c(new bn(this, ao.e("Guest Speaker"), ao.e("Check this item to allow users of this account to serve as a guest speaker in moderated chat rooms."), 59));
        this.e.c(new bn(this, ao.e("Create Permanent Private Rooms"), ao.e("Check this item to allow users of this account to create permanent private rooms on-the-fly."), 29));
        this.e.c(new bn(this, ao.e("Create Temporary Private Rooms"), ao.e("Check this item to allow users of this account to create temporary private rooms on-the-fly."), 38));
        this.e.c(new bn(this, ao.e("Create Permanent Public Rooms"), ao.e("Check this item to allow users of this account to create permanent public rooms on-the-fly."), 27));
        this.e.c(new bn(this, ao.e("Create Temporary Public Rooms"), ao.e("Check this item to allow users of this account to create temporary public rooms on-the-fly."), 28));
        this.e.c(new bn(this, ao.e("Access Reserved Icons"), ao.e("Check this item to allow users of this account to use icons that have been marked as reserved."), 36));
        this.e.c(new bn(this, ao.e("Edit Accounts"), ao.e("Check this item to allow users of this account to add, edit, and remove accounts."), 52));
        this.e.c(new bn(this, ao.e("Add Banned Users"), ao.e("Check this item to allow users of this account to add banned IPs and host names."), 49));
        this.e.c(new bn(this, ao.e("Edit Banned Users"), ao.e("Check this item to allow users of this account to edit and remove banned IPs and host names."), 16));
        this.e.c(new bn(this, ao.e("Edit Banners"), ao.e("Check this item to allow users of this account to add, edit, and remove banners."), 57));
        this.e.c(new bn(this, ao.e("Edit ChatWatch"), ao.e("Check this item to allow users of this account to add, and remove ChatWatch words."), 53));
        this.e.c(new bn(this, ao.e("Edit Nicknames Shortcuts"), ao.e("Check this item to allow users of this account to add, and remove Nicknames Shortcuts."), 1));
        this.e.c(new bn(this, ao.e("Edit Emoticons"), ao.e("Check this item to allow users of this account to add, and remove Emoticons."), 26));
        this.e.c(new bn(this, ao.e("Edit DigiCast"), ao.e("Check this item to allow users of this account to add, edit, and remove DigiCast messages."), 54));
        this.e.c(new bn(this, ao.e("Edit Hosts"), ao.e("Check this item to allow users of this account to add, edit, and remove hosts."), 46));
        this.e.c(new bn(this, ao.e("Edit Icons and Themes"), ao.e("Check this item to allow users of this account to add, edit, and remove icons and themes."), 55));
        this.e.c(new bn(this, ao.e("Edit Options"), ao.e("Check this item to allow users of this account to set options for logo link URL and transcripts."), 47));
        this.e.c(new bn(this, ao.e("Edit Rooms"), ao.e("Check this item to allow users of this account to add, edit, and remove rooms."), 56));
        this.e.c(new bn(this, ao.e("Kick Users"), ao.e("Check this item to allow users of this account to kick other users."), 44));
        this.e.c(new bn(this, ao.e("Do Not Kick"), ao.e("Check this item to allow users of this account to not be kicked."), 34));
        this.e.c(new bn(this, ao.e("Send Chat Broadcast"), ao.e("Check this item to allow users of this account to send Chat Broadcast messages."), 42));
        this.e.c(new bn(this, ao.e("Send Private Messages"), ao.e("Check this item to allow users of this account to send private messages to other users."), 43));
        this.e.c(new bn(this, ao.e("Invisible"), ao.e("Check this item to allow users of this account to be invisible and see other invisible users."), 24));
        this.e.c(new bn(this, ao.e("Ghost"), ao.e("Check this item to allow users of this account to be ghosts and see other ghost users."), 25));
        this.e.c(new bn(this, ao.e("Edit Colors"), ao.e("Check this item to allow users of this account to add, edit and remove colors."), 2));
        this.e.c(new bn(this, ao.e("Warn Users"), ao.e("Check this item to allow users of this account to send warning messages."), 3));
        this.e.c(new bn(this, ao.e("Change His Color"), ao.e("Check this item to allow users of this account to change their own color."), 21));
        this.e.c(new bn(this, ao.e("Change His Background Color"), ao.e("Check this item to allow users of this account to change their own background color."), 51));
        this.e.c(new bn(this, ao.e("View User's Country Details"), ao.e("Check this item to allow users of this account to view user's country details in his profile."), 20));
        this.e.c(new bn(this, ao.e("Change Rooms Color"), ao.e("Check this item to allow users of this account to change rooms color."), 0));
        this.e.c(new bn(this, ao.e("Change User Color"), ao.e("Check this item to allow users of this account to change users colors."), 5));
        this.e.c(new bn(this, ao.e("Change User Background Color"), ao.e("Check this item to allow users of this account to change users background colors."), 19));
        this.e.c(new bn(this, ao.e("Change User Display Name"), ao.e("Check this item to allow users of this account to change users display name."), 6));
        this.e.c(new bn(this, ao.e("Move Users To Other Rooms"), ao.e("Check this item to allow users of this account to move users to other rooms."), 7));
        this.e.c(new bn(this, ao.e("Search Users List"), ao.e("Check this item to allow users of this account to search users list."), 8));
        this.e.c(new bn(this, ao.e("Show Users On Same IP"), ao.e("Check this item to allow users of this account to show users connected on same IP address."), 9));
        this.e.c(new bn(this, ao.e("Kick Users On Same IP"), ao.e("Check this item to allow users of this account to kick users connected on the same IP address."), 11));
        this.e.c(new bn(this, ao.e("Enable And Disable Users From Writing In Public Rooms"), ao.e("Check this item to allow users of this account to enable and disable users from writing in public rooms."), 12));
        this.e.c(new bn(this, ao.e("Restart Chat"), ao.e("Check this item to allow users of this account to restart chat."), 13));
        this.e.c(new bn(this, ao.e("Give OnceKick"), ao.e("Check this item to allow users of this account to give OnceKick for other users."), 14));
        this.e.c(new bn(this, ao.e("Block User's Country"), ao.e("Check this item to allow users of this account to block user's country."), 32));
        this.e.c(new bn(this, ao.e("View Server Logs"), ao.e("Check this item to allow users of this account to view server logs."), 4));
        this.e.c(new bn(this, ao.e("Edit RSS Settings"), ao.e("Check this item to allow users of this account to edit RSS settings."), 15));
        this.e.c(new bn(this, ao.e("Edit Users Groups"), ao.e("Check this item to allow users of this account to edit users groups."), 48));
        this.e.c(new bn(this, ao.e("Change Users Groups"), ao.e("Check this item to allow users of this account to change users groups."), 41));
        this.e.c(new bn(this, ao.e("Change His Group"), ao.e("Check this item to allow users of this account to change their own group."), 39));
        this.e.c(new bn(this, ao.e("Hide His Star Image"), ao.e("Check this item to allow users of this account to hide their own star image."), 50));
        this.e.c(new bn(this, ao.e("Auto Disable Account If Expired"), ao.e("Check this item to automatically disable this account on expiry date."), 10));
        this.e.resize(100, this.e.e() * 10);
        this.a.c(0);
    }
    
    static Choice a(final bo bo) {
        return bo.t;
    }
    
    static u a(final bo bo) {
        return bo.l;
    }
    
    static l a(final bo bo) {
        return bo.e;
    }
}

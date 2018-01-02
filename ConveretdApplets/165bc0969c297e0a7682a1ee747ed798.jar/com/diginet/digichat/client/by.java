// 
// Decompiled by Procyon v0.5.30
// 

package com.diginet.digichat.client;

import com.diginet.digichat.common.k;
import com.diginet.digichat.common.e;
import java.util.StringTokenizer;
import com.diginet.digichat.awt.ap;
import com.diginet.digichat.awt.t;
import java.awt.Label;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import com.diginet.digichat.common.bp;
import java.awt.image.ImageObserver;
import com.diginet.digichat.network.v;
import com.diginet.digichat.common.j;
import com.diginet.digichat.awt.m;
import java.util.Enumeration;
import java.awt.Event;
import com.diginet.digichat.awt.bv;
import java.awt.Checkbox;
import com.diginet.digichat.util.a5;
import com.esial.util.c;
import java.awt.Image;
import com.diginet.digichat.awt.bk;
import com.diginet.digichat.awt.r;
import java.awt.Component;
import java.util.Hashtable;
import java.awt.Canvas;
import com.diginet.digichat.awt.ao;
import java.awt.Color;
import com.diginet.digichat.util.s;
import java.awt.Panel;

public class by extends Panel implements s, Runnable
{
    public static final Color a;
    public static final Color b;
    public static final Color c;
    public static final Color d;
    private static final long[] lCheck;
    private static final String strBuddies = "buddies";
    private boolean fChecks;
    private boolean[] fLoc;
    private int nCheck;
    private ao e;
    private Canvas btnProf;
    private Canvas btnPriv;
    private Canvas btnRem;
    private Canvas btnCheck;
    private Canvas btnAdd;
    private Canvas btnInv;
    private Canvas btnNote;
    private h i;
    private Hashtable hshCookie;
    private Hashtable hshBuddies;
    private Component chkLoc;
    private Component chkAll;
    private Component chkOnl;
    private Thread trdCheck;
    private Object objSync;
    
    private final void a(final Canvas canvas) {
        if (canvas instanceof r) {
            ((r)canvas).c();
        }
        else {
            ((bk)canvas).setEnabled(false);
        }
    }
    
    private final void b(final Canvas canvas) {
        if (canvas instanceof r) {
            ((r)canvas).b();
        }
        else {
            ((bk)canvas).setEnabled(true);
        }
    }
    
    private final Canvas a(final String s, final String s2, final String s3) {
        s a;
        if (!this.i.cc.l() || s == null) {
            a = new r(28, 28);
            ((r)a).a(this.i.a(s3, false, 20));
        }
        else {
            final Image a2 = this.i.a(String.valueOf(String.valueOf(s).concat(String.valueOf(s2))).concat(String.valueOf("_button_up.gif")), true);
            final Image a3 = this.i.a(String.valueOf(String.valueOf(s).concat(String.valueOf(s2))).concat(String.valueOf("_button_dn.gif")), true);
            final Image a4 = this.i.a(String.valueOf(String.valueOf(s).concat(String.valueOf(s2))).concat(String.valueOf("_button_disabled.gif")), true);
            if (a2 == null || a3 == null || a4 == null) {
                a = new r(28, 28);
                ((r)a).a(this.i.a(s3, false, 20));
            }
            else {
                a = bk.a(a2, a3, a4);
            }
        }
        return (r)a;
    }
    
    public String a(final Object o) {
        if (o == this.btnAdd) {
            return com.esial.util.c.a("Click here to type in the name of the Buddy you want to add to the Buddy List.");
        }
        if (o != this.btnCheck) {
            if (o instanceof r || o instanceof bk) {
                final Buddy buddy = (Buddy)this.e.g();
                final Location location = (buddy == null) ? null : buddy.location;
                if (location == null) {
                    return com.esial.util.c.a("This button is disabled because no buddy is selected.");
                }
                if (o == this.btnRem) {
                    return a5.a(com.esial.util.c.a("Click here to remove %1 from your Buddy List."), new String[] { buddy.x() });
                }
                if (!buddy.fKnown) {
                    return a5.a(com.esial.util.c.a("This button is disabled because your buddy %1 is unknown."), new String[] { buddy.x() });
                }
                if (o == this.btnProf) {
                    if (!location.i(31)) {
                        return a5.a(com.esial.util.c.a("Location of %1 not supported getting of profile."), new String[] { buddy.x() });
                    }
                    if (buddy.fOnline || location.i(30)) {
                        return a5.a(com.esial.util.c.a("Click here to get the profile of %1."), new String[] { buddy.x() });
                    }
                    return a5.a(com.esial.util.c.a("%1 is not available for getting of profile now."), new String[] { buddy.x() });
                }
                else if (o == this.btnPriv) {
                    if (!location.i(29)) {
                        return a5.a(com.esial.util.c.a("Location of %1 not supported private conversation."), new String[] { buddy.x() });
                    }
                    if (buddy.fOnline || location.i(28)) {
                        return a5.a(com.esial.util.c.a("Click here to enter a private conversation with %1."), new String[] { buddy.x() });
                    }
                    return a5.a(com.esial.util.c.a("%1 is not available for private messages now."), new String[] { buddy.x() });
                }
                else if (o == this.btnInv) {
                    if (!location.i(27)) {
                        return a5.a(com.esial.util.c.a("Location of %1 not supported send of invitation messages."), new String[] { buddy.x() });
                    }
                    if (buddy.fOnline || location.i(26)) {
                        return a5.a(com.esial.util.c.a("Click here to enter an invitation message for %1."), new String[] { buddy.x() });
                    }
                    return a5.a(com.esial.util.c.a("%1 is not available for invitation messages now."), new String[] { buddy.x() });
                }
                else if (o == this.btnNote) {
                    if (!location.i(25)) {
                        return a5.a(com.esial.util.c.a("Location of %1 not supported of draw attention."), new String[] { buddy.x() });
                    }
                    if (buddy.fOnline || location.i(24)) {
                        return a5.a(com.esial.util.c.a("Click here to draw attention of %1."), new String[] { buddy.x() });
                    }
                    return a5.a(com.esial.util.c.a("%1 is not available for draw attention now."), new String[] { buddy.x() });
                }
            }
            return null;
        }
        if (this.fChecks) {
            return com.esial.util.c.a("Click here to check which your buddies has locations, which not support auto update.");
        }
        return com.esial.util.c.a("This button is disabled because all your buddies have locations, which support auto update.");
    }
    
    private final boolean getState(final Component component) {
        return (component instanceof Checkbox) ? ((Checkbox)component).getState() : ((bv)component).a();
    }
    
    public void show() {
        super.show();
        this.e.requestFocus();
    }
    
    private void setEnable(final Canvas canvas, final Buddy buddy, final int n) {
        final Location location;
        if (buddy.fKnown && (location = buddy.location) != null && location.i(n) && (buddy.fOnline || location.i(n - 1))) {
            this.b(canvas);
        }
        else {
            this.a(canvas);
        }
    }
    
    private void enableButtons(final Buddy buddy) {
        this.setEnable(this.btnProf, buddy, 31);
        this.setEnable(this.btnPriv, buddy, 29);
        this.setEnable(this.btnInv, buddy, 27);
        this.setEnable(this.btnNote, buddy, 25);
    }
    
    public boolean handleEvent(final Event event) {
        switch (event.id) {
            case 1001: {
                final Buddy buddy = (Buddy)this.e.g();
                if (buddy != null) {
                    final Location location = buddy.location;
                    if (buddy.fKnown) {
                        if (event.target == this.btnProf && (buddy.fOnline || location.i(30))) {
                            buddy.callProf(this.i.y.b(), this.i);
                            return true;
                        }
                        if (this.i.i(43) && (event.target == this.e || event.target == this.btnPriv) && (buddy.fOnline || location.i(28))) {
                            buddy.callPriv(null);
                            return true;
                        }
                        if (event.target == this.btnInv && (buddy.fOnline || location.i(26))) {
                            location.callInv(buddy);
                            return true;
                        }
                        if (event.target == this.btnNote && (buddy.fOnline || location.i(24))) {
                            location.callNote(buddy);
                            return true;
                        }
                    }
                    if (event.target == this.btnRem) {
                        this.a(buddy);
                        return true;
                    }
                }
                if (event.target == this.chkLoc) {
                    this.fLoc[0] = ((this.chkLoc instanceof Checkbox) ? ((Checkbox)this.chkLoc).getState() : ((bv)this.chkLoc).a());
                    this.e.b();
                    return true;
                }
                if (event.target == this.chkOnl || event.target == this.chkAll) {
                    this.chkOnl.enable(!this.getState(this.chkAll));
                    this.updateList();
                    return true;
                }
                if (event.target == this.btnAdd) {
                    new c2(this.i.y.b(), this.i, this, buddy);
                    return true;
                }
                if (event.target == this.btnCheck) {
                    if (this.trdCheck == null) {
                        this.checkBuddies();
                    }
                    else {
                        synchronized (this.objSync) {
                            this.objSync.notify();
                        }
                        // monitorexit(this.objSync)
                    }
                    return true;
                }
                break;
            }
            case 701: {
                this.enableButtons((Buddy)this.e.g());
                this.b(this.btnRem);
                return true;
            }
            case 702: {
                this.a(this.btnProf);
                this.a(this.btnRem);
                this.a(this.btnPriv);
                this.a(this.btnInv);
                this.a(this.btnNote);
                return true;
            }
        }
        return super.handleEvent(event);
    }
    
    private void changeChecks(final boolean fChecks) {
        if (fChecks != this.fChecks) {
            this.fChecks = fChecks;
            if (fChecks) {
                this.b(this.btnCheck);
                if (this.i.nCheck != 0) {
                    (this.trdCheck = new Thread(this)).start();
                }
            }
            else {
                this.a(this.btnCheck);
                if (this.trdCheck != null) {
                    this.trdCheck = null;
                    synchronized (this.objSync) {
                        this.objSync.notify();
                    }
                    // monitorexit(this.objSync)
                }
            }
        }
    }
    
    private void saveBuddies() {
        boolean checkable = false;
        Scripts.setCookie(this.i.createName("buddies"), this.hshCookie);
        final Enumeration elements = this.hshBuddies.elements();
        while (elements.hasMoreElements() && !(checkable = elements.nextElement().isCheckable())) {}
        this.changeChecks(checkable);
    }
    
    public boolean a(final Buddy buddy) {
        this.hshCookie.remove(buddy.strKey);
        this.hshBuddies.remove(buddy.strKey);
        buddy.closeBuddy();
        this.saveBuddies();
        return this.e.e(buddy);
    }
    
    public boolean[] getLoc() {
        return this.fLoc;
    }
    
    private Buddy getBuddy(final String s, final int n) {
        final int length = s.length();
        final Location location;
        char[] array;
        if ((location = (Location)this.i.locations.e(n)) instanceof Local) {
            array = new char[length];
        }
        else {
            final String x;
            array = new char[length + (x = location.x()).length() + 1];
            array[length] = '\n';
            x.getChars(0, x.length(), array, length + 1);
        }
        s.getChars(0, length, array, 0);
        return (Buddy)this.hshBuddies.get(new String(array));
    }
    
    public void changeChecks() {
        if (this.fChecks && this.nCheck != this.i.nCheck) {
            if (this.trdCheck == null) {
                (this.trdCheck = new Thread(this)).start();
            }
            else {
                if (this.i.nCheck == 0) {
                    this.trdCheck = null;
                }
                synchronized (this.objSync) {
                    this.objSync.notify();
                }
                // monitorexit(this.objSync)
            }
        }
    }
    
    private boolean isShowing(final Buddy buddy) {
        return this.getState(this.chkAll) || (buddy.fKnown && (buddy.fOnline || !this.getState(this.chkOnl)));
    }
    
    private final void setItem(final Buddy buddy) {
        if (buddy.clrName != null) {
            this.e.a(buddy, buddy.clrName, buddy.clrName);
        }
        else if (buddy.c) {
            this.e.a(buddy, Color.red, Color.pink);
        }
        else if (buddy.i(59)) {
            this.e.a(buddy, by.c, by.d);
        }
        else if (buddy.i(61) || buddy.i(62)) {
            this.e.a(buddy, by.a, by.b);
        }
        else {
            this.e.a(buddy, Color.black, Color.white);
        }
        this.e.setLines(buddy, buddy.fKnown ? (!buddy.fOnline) : 2);
        this.e.setBackground(buddy, buddy.clrBack);
        this.e.b();
    }
    
    private void addItem(final Buddy item) {
        if (this.isShowing(item)) {
            this.e.d(item);
            this.setItem(item);
        }
    }
    
    public void setBuddy(final j params, final boolean b, final boolean fOnline) {
        boolean b2 = false;
        if (!this.i.x().equals(params.x())) {
            Buddy buddy = null;
            if (params instanceof Buddy) {
                buddy = (Buddy)params;
            }
            Buddy buddy2 = null;
            if (params.x() != null) {
                buddy2 = this.getBuddy(params.x(), (buddy == null) ? 0 : buddy.locId());
            }
            if (b && buddy != null && buddy2 == null) {
                b2 = true;
                buddy.initBuddy();
                this.addItem(buddy);
            }
            else if (buddy2 != null) {
                this.e.e(buddy2);
                if (buddy == null) {
                    buddy2.setParams(params);
                    buddy2.fOnline = fOnline;
                    buddy2.fKnown = true;
                    buddy = buddy2;
                }
                else if (buddy.notEq(buddy2)) {
                    b2 = true;
                    buddy2.closeBuddy();
                    buddy.initBuddy();
                }
                this.addItem(buddy);
            }
            if (b2) {
                final StringBuffer sb = new StringBuffer();
                if (buddy.fBuddy && (buddy.fLocation || buddy.strLogin != null || buddy.ePass != null)) {
                    ((Universal)buddy.location).strLogin = buddy.strLogin;
                    ((Universal)buddy.location).ePass = buddy.ePass;
                    final Enumeration<Buddy> elements = this.hshBuddies.elements();
                    while (elements.hasMoreElements()) {
                        final Buddy buddy3;
                        if ((buddy3 = elements.nextElement()).location == buddy.location) {
                            buddy3.fLocation = (buddy3.fLocation && buddy.isEq(buddy3));
                        }
                    }
                    if (buddy.strLogin != null) {
                        sb.append(buddy.strLogin);
                    }
                    if (buddy.fLocation || buddy.ePass != null) {
                        sb.append('\n');
                    }
                    if (buddy.ePass != null) {
                        sb.append(buddy.ePass.toString());
                    }
                    if (buddy.fLocation) {
                        sb.append("\n#");
                    }
                }
                this.hshCookie.put(buddy.strKey, sb.toString());
                this.hshBuddies.put(buddy.strKey, buddy);
                this.saveBuddies();
            }
        }
    }
    
    private Buddy getBuddy(final v v) {
        return this.getBuddy(v.c(0, 0), v.a(0, 0));
    }
    
    private void changeItem(final Buddy item) {
        if (!this.isShowing(item)) {
            this.e.e(item);
        }
        else {
            if (this.e.b(item) == -1) {
                this.e.d(item);
            }
            this.setItem(item);
        }
        if (this.e.g() == item) {
            this.enableButtons(item);
        }
    }
    
    public void setParams(final v params) {
        final Buddy buddy;
        if ((buddy = this.getBuddy(params)) != null) {
            buddy.setParams(params);
            this.changeItem(buddy);
        }
    }
    
    public void setProf(final v v) {
        final Buddy buddy;
        final BuddyProf bdProf;
        if ((buddy = this.getBuddy(v)) != null && (bdProf = buddy.bdProf) != null) {
            Image loadImage = null;
            Image image;
            if ((image = Buddy.getImage(v)) == null) {
                image = buddy.imgIcon;
            }
            String strSite = null;
            if (buddy.location instanceof Universal) {
                loadImage = this.i.loadImage(((Universal)buddy.location).getLogo(), bdProf, 310);
                strSite = ((Universal)buddy.location).strSite;
            }
            bdProf.setProf(image, strSite, loadImage, v.c(0, 1), v.c(0, 2), v.c(0, 3));
        }
    }
    
    public void addMess(final String s, final int n, final bd bd, final String[] array, final long[] array2) {
        final Buddy buddy;
        if ((buddy = this.getBuddy(s, n)) != null) {
            m m;
            Image image;
            String s2;
            if (bd == null) {
                m = buddy;
                image = buddy.imgIcon;
                s2 = buddy.getName();
            }
            else {
                m = bd;
                image = null;
                final bp a;
                if ((a = bd.a) != null) {
                    image = a.a;
                }
                s2 = ((k)m).x();
            }
            for (int i = 0; i < array.length; ++i) {
                final bf bf = new bf(array[i], s2, ((j)m).clrName, 3, image, null, ((k)m).w(), s, n, ((k)m).i(62) || ((k)m).i(61), ((k)m).i(59), array2[i]);
                bf.s = (((k)m).i(23) || ((k)m).i(79));
                final bh bhPriv;
                if ((bhPriv = buddy.bhPriv) != null) {
                    bhPriv.a(bf);
                }
                else if (this.i.bb) {
                    buddy.callPriv(bf);
                }
                else if (this.i.y != null) {
                    this.i.y.a(bf);
                }
            }
        }
    }
    
    public boolean callPriv(final bf bf) {
        final Buddy buddy;
        if ((buddy = this.getBuddy(bf.strLoc, bf.nLoc)) == null) {
            return false;
        }
        buddy.callPriv(bf);
        return true;
    }
    
    private void updateList() {
        final Enumeration<Buddy> elements = this.hshBuddies.elements();
        while (elements.hasMoreElements()) {
            this.changeItem(elements.nextElement());
        }
    }
    
    private void checkBuddies() {
        final Enumeration<Buddy> elements = this.hshBuddies.elements();
        while (elements.hasMoreElements()) {
            final Buddy buddy;
            if ((buddy = elements.nextElement()).isCheckable()) {
                buddy.checkBuddy();
            }
        }
    }
    
    public void run() {
        long n = System.currentTimeMillis();
        while (true) {
            if (this.i.nCheck == 0) {
                this.trdCheck = null;
            }
            else {
                final long n2 = n;
                final long[] lCheck = by.lCheck;
                final int nCheck = this.i.nCheck;
                this.nCheck = nCheck;
                final long n3;
                if ((n3 = n2 + (lCheck[nCheck - 1] - System.currentTimeMillis())) > 0) {
                    synchronized (this.objSync) {
                        Label_0075: {
                            try {
                                this.objSync.wait(n3);
                                break Label_0075;
                            }
                            catch (InterruptedException ex) {
                            }
                            // monitorexit(this.objSync)
                        }
                    }
                }
            }
            if (this.trdCheck != Thread.currentThread()) {
                break;
            }
            this.checkBuddies();
            n = System.currentTimeMillis();
        }
    }
    
    private Component addCheck(final String s, final String s2, final String s3, final boolean state, final GridBagLayout layout, final GridBagConstraints gridBagConstraints) {
        if (this.i.cc.l()) {
            final Image a = this.i.a(String.valueOf(String.valueOf(s3).concat(String.valueOf(s2))).concat(String.valueOf("_checked.gif")), true);
            final Image a2 = this.i.a(String.valueOf(String.valueOf(s3).concat(String.valueOf(s2))).concat(String.valueOf("_unchecked.gif")), true);
            if (a != null && a2 != null) {
                final bv bv = new bv(a, a2);
                bv.a(state);
                final Panel panel = new Panel();
                panel.setLayout(layout);
                gridBagConstraints.weightx = 0.0;
                gridBagConstraints.gridwidth = -1;
                gridBagConstraints.anchor = 17;
                gridBagConstraints.insets = new Insets(0, 5, 0, 0);
                layout.setConstraints(bv, gridBagConstraints);
                panel.add(bv);
                final Label label = new Label(com.esial.util.c.a(s));
                gridBagConstraints.weightx = 1.0;
                gridBagConstraints.gridwidth = 0;
                gridBagConstraints.fill = 2;
                gridBagConstraints.anchor = 17;
                layout.setConstraints(label, gridBagConstraints);
                panel.add(label);
                gridBagConstraints.gridwidth = 0;
                layout.setConstraints(panel, gridBagConstraints);
                this.add(panel);
                return bv;
            }
        }
        final Checkbox checkbox = new Checkbox(com.esial.util.c.a(s));
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new Insets(0, 0, 0, 0);
        gridBagConstraints.gridwidth = 0;
        layout.setConstraints(checkbox, gridBagConstraints);
        checkbox.setState(state);
        this.add(checkbox);
        return checkbox;
    }
    
    public void updateLocations() {
        boolean b = false;
        final Enumeration<Buddy> elements = (Enumeration<Buddy>)this.hshBuddies.elements();
        while (elements.hasMoreElements()) {
            final Buddy buddy;
            (buddy = elements.nextElement()).closeBuddy();
            final String strLoc;
            Location location;
            if ((strLoc = buddy.strLoc) == null) {
                location = (Location)this.i.locations.e(0);
            }
            else {
                location = null;
                for (int i = 0; i < this.i.locations.b(); ++i) {
                    final Location location2;
                    if (strLoc.equals((location2 = (Location)this.i.locations.d(i)).x())) {
                        location = location2;
                        break;
                    }
                }
            }
            final Buddy buddy2 = buddy;
            final Location location3 = location;
            buddy2.location = location3;
            if (location3 != null && location.i(61)) {
                buddy.initBuddy();
            }
            else {
                final Buddy buddy3 = buddy;
                final Buddy buddy4 = buddy;
                final boolean b2 = false;
                buddy4.fOnline = b2;
                buddy3.fKnown = b2;
            }
            b = (b || buddy.isCheckable());
            this.changeItem(buddy);
        }
        this.changeChecks(b);
    }
    
    public void closeBuddies() {
        final Enumeration<Buddy> elements = this.hshBuddies.elements();
        while (elements.hasMoreElements()) {
            elements.nextElement().closeBuddy();
        }
    }
    
    public by(final h i) {
        this.fLoc = new boolean[1];
        this.e = new ao();
        this.i = i;
        this.setBackground(i.cc.j);
        this.setForeground(i.cc.i);
        final String f = i.cc.f();
        this.btnProf = this.a(f, "profile", "userInfoIcon.GIF");
        this.btnAdd = this.a(f, "addbuddy", "addBuddyIcon.GIF");
        this.btnRem = this.a(f, "removebuddy", "removeBuddyIcon.GIF");
        this.btnCheck = this.a(f, "check", "checkingIcon.GIF");
        this.btnPriv = this.a(f, "private", "whisperIcon.GIF");
        this.btnInv = this.a(f, "invite", "inviteIcon.GIF");
        this.btnNote = this.a(f, "notice", "noticeIcon.GIF");
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        final GridBagLayout layout = new GridBagLayout();
        this.setLayout(layout);
        gridBagConstraints.insets = new Insets(2, 1, 2, 1);
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.anchor = 17;
        layout.setConstraints(this.btnProf, gridBagConstraints);
        this.add(this.btnProf);
        layout.setConstraints(this.btnAdd, gridBagConstraints);
        this.add(this.btnAdd);
        layout.setConstraints(this.btnRem, gridBagConstraints);
        this.add(this.btnRem);
        layout.setConstraints(this.btnCheck, gridBagConstraints);
        this.add(this.btnCheck);
        if (i.i(43)) {
            layout.setConstraints(this.btnPriv, gridBagConstraints);
            this.add(this.btnPriv);
        }
        layout.setConstraints(this.btnInv, gridBagConstraints);
        this.add(this.btnInv);
        gridBagConstraints.gridwidth = 0;
        layout.setConstraints(this.btnNote, gridBagConstraints);
        this.add(this.btnNote);
        this.chkLoc = this.addCheck("Show locations", "locations", f, true, layout, gridBagConstraints);
        this.chkAll = this.addCheck("Show all buddies", "allbuds", f, false, layout, gridBagConstraints);
        this.chkOnl = this.addCheck("Show only online buddies", "buddies", f, true, layout, gridBagConstraints);
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.fill = 1;
        final t t = new t(this.e);
        layout.setConstraints(t, gridBagConstraints);
        this.add(t);
        final ap ap = new ap(null, "icon");
        final ap ap2 = new ap(com.esial.util.c.a("Nickname"), "name");
        ap.d(24);
        ap.c(0);
        ap2.c(true);
        this.e.a(true);
        this.e.b(ap);
        this.e.b(ap2);
        this.e.a(ap2);
        this.e.l(24);
        this.a(this.btnCheck);
        this.nCheck = -1;
        this.fLoc[0] = true;
        this.trdCheck = null;
        this.fChecks = false;
        this.objSync = new Object();
        this.hshBuddies = new Hashtable();
        if ((this.hshCookie = Scripts.getCookie(i.createName("buddies"))) == null) {
            this.hshCookie = new Hashtable();
        }
        else {
            final Enumeration<String> keys = this.hshCookie.keys();
            while (keys.hasMoreElements()) {
                try {
                    final String s;
                    final StringTokenizer stringTokenizer = new StringTokenizer(this.hshCookie.get(s = keys.nextElement()), "\n");
                    String s2 = null;
                    e e = null;
                    boolean hasMoreTokens = false;
                    final boolean hasMoreTokens2;
                    if (hasMoreTokens2 = stringTokenizer.hasMoreTokens()) {
                        final String nextToken;
                        if ((nextToken = stringTokenizer.nextToken()).length() != 0) {
                            s2 = nextToken;
                        }
                        if (stringTokenizer.hasMoreTokens()) {
                            final String nextToken2;
                            if ((nextToken2 = stringTokenizer.nextToken()).length() != 0) {
                                e = new e(nextToken2);
                            }
                            hasMoreTokens = stringTokenizer.hasMoreTokens();
                        }
                    }
                    this.hshBuddies.put(s, new Buddy(-999, s, null, s2, e, hasMoreTokens2, hasMoreTokens, this.fLoc));
                }
                catch (Exception ex) {}
            }
            this.updateLocations();
        }
    }
    
    static {
        a = new Color(153);
        b = new Color(10079487);
        c = new Color(16711680);
        d = new Color(10079487);
        lCheck = new long[] { 15000L, 60000L, 300000L, 900000L, 1800000L, 3600000L };
    }
}

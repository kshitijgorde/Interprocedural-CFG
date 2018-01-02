// 
// Decompiled by Procyon v0.5.30
// 

package com.diginet.digichat.client;

import com.diginet.digichat.awt.ap;
import com.diginet.digichat.awt.t;
import java.awt.Label;
import java.awt.Insets;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Color;
import java.awt.Container;
import java.awt.Frame;
import com.diginet.digichat.client.chatmaster.c0;
import com.diginet.digichat.common.e;
import java.awt.Event;
import java.util.Enumeration;
import com.diginet.digichat.util.a5;
import com.esial.util.c;
import com.diginet.digichat.common.j;
import com.diginet.digichat.network.v;
import com.diginet.digichat.awt.MenuPanel;
import java.awt.LayoutManager;
import java.awt.FlowLayout;
import java.awt.Image;
import com.diginet.digichat.awt.bk;
import com.diginet.digichat.awt.r;
import com.diginet.digichat.awt.bv;
import java.awt.Checkbox;
import com.diginet.digichat.awt.MenuItem;
import com.diginet.digichat.awt.MenuPopup;
import java.awt.Component;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.Canvas;
import com.diginet.digichat.util.s;

public class bt extends bu implements s
{
    private boolean fWebIM;
    private Canvas[] cnvButtons;
    private TextField txfSearch;
    private Panel pnlButtons;
    private Component g;
    private MenuPopup mpuContx;
    private MenuItem[] itmContx;
    
    public boolean a() {
        if (this.g instanceof Checkbox) {
            return ((Checkbox)this.g).getState();
        }
        return ((bv)this.g).a();
    }
    
    public void a(final boolean state) {
        if (this.g instanceof Checkbox) {
            ((Checkbox)this.g).setState(state);
        }
        else {
            ((bv)this.g).a(state);
        }
    }
    
    private final void b(final int n) {
        final Canvas canvas;
        if ((canvas = this.cnvButtons[n]) == null) {
            return;
        }
        if (canvas instanceof r) {
            ((r)canvas).c();
        }
        else {
            ((bk)canvas).setEnabled(false);
        }
    }
    
    private final void c(final int n) {
        final Canvas canvas;
        if ((canvas = this.cnvButtons[n]) == null) {
            return;
        }
        if (canvas instanceof r) {
            ((r)canvas).b();
        }
        else {
            ((bk)canvas).setEnabled(true);
        }
    }
    
    private void enableElem(final int n, final boolean b) {
        if (b) {
            this.c(n);
        }
        else {
            this.b(n);
        }
        final MenuItem menuItem;
        if ((menuItem = this.itmContx[n]) != null) {
            menuItem.enable(b);
        }
    }
    
    public boolean a(final int n) {
        final Canvas canvas;
        if ((canvas = this.cnvButtons[n]) == null) {
            return false;
        }
        if (canvas instanceof r) {
            return ((r)canvas).a();
        }
        return ((bk)canvas).isEnabled();
    }
    
    private void loadImages(final String s, final String s2, final Image[] array) {
        final String f;
        if (super.g.cc.l() && (f = super.g.cc.f()) != null && (array[0] = super.g.a(String.valueOf(String.valueOf(f).concat(String.valueOf(s))).concat(String.valueOf("_button_up.gif")), true)) != null) {
            final int n = 1;
            final Image a = super.g.a(String.valueOf(String.valueOf(f).concat(String.valueOf(s))).concat(String.valueOf("_button_dn.gif")), true);
            array[n] = a;
            if (a == null || (array[2] = super.g.a(String.valueOf(String.valueOf(f).concat(String.valueOf(s))).concat(String.valueOf("_button_disabled.gif")), true)) == null) {
                array[0] = null;
            }
        }
        if (array[0] == null) {
            array[3] = super.g.a(s2, false, 20);
        }
    }
    
    private Panel createButton(final int n, final String s, final String s2, final Image[] array) {
        this.loadImages(s, s2, array);
        s a;
        if (array[0] == null) {
            final r r;
            (r = new r(28, 28)).a(array[3]);
            a = r;
        }
        else {
            a = bk.a(array[0], array[1], array[2]);
        }
        final Panel panel = new Panel(new FlowLayout(1, 0, 0));
        panel.add(this.cnvButtons[n] = (Canvas)a);
        return panel;
    }
    
    private MenuItem cteateItem(final String s, final int n, final String s2, final String s3, final Image[] array) {
        if (array[0] == null && array[3] == null) {
            this.loadImages(s2, s3, array);
        }
        return this.itmContx[n] = new MenuItem(s, (array[0] == null) ? array[3] : array[0], null);
    }
    
    private void addElem(final String s, final int n, final String s2, final String s3) {
        if (super.g.isElem(n)) {
            final Image[] array = { null, null, null, null };
            final int n2;
            if (v.a(super.g.lElems, n2 = n << 1)) {
                this.pnlButtons.add(this.createButton(n, s2, s3, array));
            }
            if (v.a(super.g.lElems, n2 + 1)) {
                this.mpuContx.add(this.cteateItem(s, n, s2, s3, array));
            }
        }
    }
    
    private boolean checkSpec(final j j) {
        return (super.g.yyy() >> 61 & 0x7) >= (j.yyy() >> 61 & 0x7);
    }
    
    private boolean checkKisk(final j j) {
        final int n;
        return (n = ((int)(super.g.yyy() >> 61) & 0x7)) >= ((int)(j.yyy() >> 61) & 0x7) && super.g.i(44) && j.w() != super.g.w() && (n != 0 || !j.i(34));
    }
    
    public String a(final Object o) {
        if (o instanceof r || o instanceof bk) {
            final j j = (j)super.f.g();
            if (o == this.cnvButtons[2]) {
                if (this.a(2)) {
                    return (j == null) ? com.esial.util.c.a("Click here for reading messages to you through 1:1.") : a5.a(com.esial.util.c.a("Click here to enter a conversation with %1 through 1:1."), new String[] { j.x() });
                }
                return com.esial.util.c.a("This button is disabled because no user is selected and you no have unreading messages through 1:1.");
            }
            else {
                if (j == null) {
                    return com.esial.util.c.a("This button is disabled because no user is selected.");
                }
                if (o == this.cnvButtons[1]) {
                    return a5.a(com.esial.util.c.a("Click here to enter a private conversation with %1."), new String[] { j.x() });
                }
                if (o == this.cnvButtons[5]) {
                    if (!j.x().equals(super.g.x())) {
                        return a5.a(com.esial.util.c.a("Click here to add %1 to your Buddy List."), new String[] { j.x() });
                    }
                    return a5.a(com.esial.util.c.a("This button is disabled because you can not add yourself to your Buddy List."), new String[] { j.x() });
                }
                else {
                    if (o == this.cnvButtons[3]) {
                        return j.c ? a5.a(com.esial.util.c.a("Click here to stop flagging messages from %1."), new String[] { j.x() }) : a5.a(com.esial.util.c.a("Click here to flag messages from %1.  Flagged messages will appear in the color specified in your settings."), new String[] { j.x() });
                    }
                    if (o == this.cnvButtons[0]) {
                        return a5.a(com.esial.util.c.a("Click here to get the profile of %1. This will return information entered by the user, such as his or her real name."), new String[] { j.x() });
                    }
                    if (o == this.cnvButtons[4]) {
                        if (this.a(4)) {
                            return j.d ? a5.a(com.esial.util.c.a("Click here to stop ignoring messages from %1."), new String[] { j.x() }) : a5.a(com.esial.util.c.a("Click here to ignore messages from %1, and to prevent this user from accessing your profile."), new String[] { j.x() });
                        }
                        if (j.i(33)) {
                            return a5.a(com.esial.util.c.a("This button is disabled because %1 cannot be ignored."), new String[] { j.x() });
                        }
                    }
                    if (o == this.cnvButtons[6]) {
                        if (this.a(6)) {
                            return a5.a(com.esial.util.c.a("Click here to kick %1."), new String[] { j.x() });
                        }
                        if (!this.checkKisk(j)) {
                            return a5.a(com.esial.util.c.a("This button is disabled because you not have permission for kick %1."), new String[] { j.x() });
                        }
                    }
                    if (o == this.cnvButtons[7]) {
                        if (this.a(7)) {
                            return a5.a(com.esial.util.c.a("Click here for open advanced user settings of %1."), new String[] { j.x() });
                        }
                        if (!this.checkSpec(j)) {
                            return a5.a(com.esial.util.c.a("This button is disabled because you not have permission for access to advanced user settings of %1."), new String[] { j.x() });
                        }
                    }
                }
            }
        }
        return null;
    }
    
    private Location getLocal() {
        final Location location;
        return (((h)super.g).k() && (location = (Location)super.g.locations.e(0)) != null && location.i(61)) ? location : null;
    }
    
    private boolean checkWebIM() {
        final Enumeration elements = super.g.vecIMMess.elements();
        while (elements.hasMoreElements()) {
            if (super.g.aa.d((int)((Object[])elements.nextElement())[0]) != null) {
                return true;
            }
        }
        return false;
    }
    
    private void updateElem(final String s, final int n, final String s2, final String s3, final int[] array, final boolean b) {
        final boolean elem = super.g.isElem(n);
        final Image[] array2 = { null, null, null, null };
        final int n2;
        if (v.a(super.g.lElems, n2 = n << 1) && elem) {
            if (this.cnvButtons[n] == null) {
                this.pnlButtons.add(this.createButton(n, s2, s3, array2), array[0]);
            }
        }
        else if (this.cnvButtons[n] != null) {
            this.pnlButtons.remove(this.cnvButtons[n].getParent());
            this.cnvButtons[n] = null;
        }
        if (this.cnvButtons[n] != null) {
            final int n3 = 0;
            ++array[n3];
        }
        if (v.a(super.g.lElems, n2 + 1) && elem) {
            if (this.itmContx[n] == null) {
                this.mpuContx.insert(array[1], this.cteateItem(s, n, s2, s3, array2));
            }
        }
        else if (this.itmContx[n] != null) {
            this.mpuContx.remove(this.itmContx[n]);
            this.itmContx[n] = null;
        }
        if (this.itmContx[n] != null) {
            final int n4 = 1;
            ++array[n4];
        }
        this.enableElem(n, b);
        this.validate();
    }
    
    public void updateElems() {
        final int[] array = { 0, 0 };
        final bd bd;
        final boolean b = (bd = (bd)super.f.g()) != null;
        this.updateElem("User profile", 0, "profile", "userInfoIcon.GIF", array, b);
        this.updateElem("Private conversation", 1, "private", "whisperIcon.GIF", array, b);
        this.updateElem("1:1 conversation", 2, "mess", "messOptionIcon.gif", array, b || this.checkWebIM());
        this.updateElem("Flagging messages", 3, "flag", "flagUserIcon.GIF", array, b);
        this.updateElem("Ignoring messages", 4, "mute", "muteUserIcon.GIF", array, b && !bd.i(33));
        this.updateElem("Add buddy", 5, "addbuddy", "addBuddyIcon.GIF", array, b && !bd.x().equals(super.g.x()));
        this.updateElem("Kick user", 6, "kick", "kickUserIcon.GIF", array, b && this.checkKisk(bd));
        this.updateElem("Advanced settings", 7, "advanced", "advUserIcon.GIF", array, b && this.checkSpec(bd));
    }
    
    public void updateOpt() {
        this.mpuContx.setDirect(v.a(super.g.as, 48));
        super.f.e(5).setOver(v.a(super.g.as, 49) ? 0 : -1, 11);
        super.f.e(2).setOver(v.a(super.g.as, 50) ? 6 : -1, 6);
    }
    
    public void updateList() {
        final boolean imList;
        if ((imList = super.g.isImList()) != this.fWebIM) {
            for (int i = 0; i < super.g.ab.b(); ++i) {
                final bd bd = (bd)super.g.ab.d(i);
                if (imList) {
                    super.g.setIMIcons(bd, bd.i(77), bd.i(76));
                }
                else {
                    final bd bd2 = bd;
                    final bd bd3 = bd;
                    final Image image = null;
                    bd3.imgVideo = image;
                    bd2.imgAudio = image;
                }
            }
            super.f.b(0L);
            this.fWebIM = imList;
        }
    }
    
    public boolean handleEvent(final Event event) {
        final bd bd = (bd)super.f.g();
        switch (event.id) {
            case 1001: {
                if (bd != null) {
                    final String x = bd.x();
                    int n = -1;
                    if (event.target != super.f) {
                        for (int i = 0; i < this.cnvButtons.length; ++i) {
                            if (event.target == this.cnvButtons[i] || event.target == this.itmContx[i]) {
                                n = i;
                                break;
                            }
                        }
                    }
                    else if (super.g.i(43)) {
                        n = 1;
                    }
                    if (n >= 0) {
                        switch (n) {
                            case 0: {
                                final v v = new v(67074, 1);
                                v.a(0, 0, super.g.w());
                                v.k = bd.w();
                                super.g.an(v);
                                break;
                            }
                            case 1: {
                                super.g.a(null, (j)bd);
                                break;
                            }
                            case 2: {
                                super.g.callWebIM((bd)super.f.g());
                                break;
                            }
                            case 3: {
                                bd.c = !bd.c;
                                bd.d = false;
                                super.g.ap.removeElement(x);
                                if (!bd.c) {
                                    super.g.aq.removeElement(x);
                                }
                                else if (!super.g.aq.contains(x)) {
                                    super.g.aq.addElement(x);
                                }
                                this.postEvent(new Event(event.target, 7689, this.a(event.target)));
                                this.b(bd);
                                break;
                            }
                            case 4: {
                                bd.d = !bd.d;
                                bd.c = false;
                                if (!bd.d) {
                                    super.g.ap.removeElement(x);
                                }
                                else if (!super.g.ap.contains(x)) {
                                    super.g.ap.addElement(x);
                                }
                                this.postEvent(new Event(event.target, 7689, this.a(event.target)));
                                this.b(bd);
                                break;
                            }
                            case 5: {
                                final Location local;
                                if ((local = this.getLocal()) != null) {
                                    final a7 a7 = (a7)super.g.y;
                                    a7.setBuddy(new Buddy(bd.w(), bd.x(), local, null, null, false, false, a7.getLoc()), true, true);
                                    super.g.a(super.g.bv);
                                }
                                break;
                            }
                            case 6: {
                                new c0(super.g, bd, false);
                                break;
                            }
                            case 7: {
                                super.g.callAdv(bd);
                                break;
                            }
                        }
                        return true;
                    }
                }
                if (event.target == this.g) {
                    super.g.c(this.a());
                }
                break;
            }
            case 402: {
                final String text;
                if (event.target == this.txfSearch && !super.strSearch.equalsIgnoreCase(text = this.txfSearch.getText())) {
                    super.strSearch = text.toLowerCase();
                    this.updateSearch();
                }
                break;
            }
            case 701: {
                final j j = (j)event.arg;
                this.enableElem(0, true);
                this.enableElem(1, true);
                this.enableElem(2, true);
                this.enableElem(3, true);
                this.enableElem(4, !j.i(33));
                this.enableElem(5, !j.x().equals(super.g.x()));
                this.enableElem(6, this.checkKisk(j));
                this.enableElem(7, this.checkSpec(j));
                if (event.metaDown()) {}
                return true;
            }
            case 702: {
                for (int k = 0; k < 8; ++k) {
                    this.b(k);
                }
                this.enableElem(2, this.checkWebIM());
                return true;
            }
            default: {
                if (bd == null) {
                    break;
                }
                this.enableElem(4, !bd.i(33));
                this.enableElem(6, this.checkKisk(bd));
                this.enableElem(7, this.checkSpec(bd));
                break;
            }
        }
        return super.handleEvent(event);
    }
    
    public boolean isSearch(final Event event, final Component component) {
        if (event.target != this.txfSearch) {
            return false;
        }
        this.txfSearch.handleEvent(event);
        Container parent = this;
        while ((parent = parent.getParent()) != null && !(parent instanceof Frame)) {}
        if (parent != null) {
            ((Frame)parent).setTitle(this.txfSearch.getText());
        }
        return true;
    }
    
    public bt(final h g) {
        this.g = null;
        super.g = g;
        super.strSearch = "";
        this.fWebIM = false;
        this.itmContx = new MenuItem[8];
        this.cnvButtons = new Canvas[8];
        for (int i = 0; i < 8; ++i) {
            this.itmContx[i] = null;
            this.cnvButtons[i] = null;
        }
        this.setBackground(g.cc.j);
        this.setForeground(g.cc.i);
        this.mpuContx = new MenuPopup();
        if (g.cc.getColors()) {
            this.mpuContx.setStyle(g.cc.getMenuFont(), g.cc.clrMenuText, g.cc.clrMenuBack, g.cc.clrHghlText, g.cc.clrHghlBack);
        }
        else {
            this.mpuContx.setStyle(g.cc.getMenuFont(), null, null, null, null);
        }
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        final GridBagLayout gridBagLayout = new GridBagLayout();
        this.pnlButtons = new Panel(new GridLayout(1, 0, 2, 1));
        this.addElem("User profile", 0, "profile", "userInfoIcon.GIF");
        this.addElem("Private conversation", 1, "private", "whisperIcon.GIF");
        this.addElem("1:1 conversation", 2, "mess", "messOptionIcon.gif");
        this.addElem("Flagging messages", 3, "flag", "flagUserIcon.GIF");
        this.addElem("Ignoring messages", 4, "mute", "muteUserIcon.GIF");
        this.addElem("Add buddy", 5, "addbuddy", "addBuddyIcon.GIF");
        this.addElem("Kick user", 6, "kick", "kickUserIcon.GIF");
        this.addElem("Advanced settings", 7, "advanced", "advUserIcon.GIF");
        if (g.cc.l()) {
            final String f = g.cc.f();
            final Image a = g.a(String.valueOf(f).concat(String.valueOf("allusers_unchecked.gif")), true);
            final Image a2 = g.a(String.valueOf(f).concat(String.valueOf("allusers_checked.gif")), true);
            if (a != null && a2 != null) {
                this.g = new bv(a, a2);
            }
        }
        if (this.g == null) {
            this.g = new Checkbox(com.esial.util.c.a("Show users in all rooms"));
        }
        this.setLayout(gridBagLayout);
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.fill = 2;
        gridBagLayout.setConstraints(this.pnlButtons, gridBagConstraints);
        this.add(this.pnlButtons);
        if (this.g instanceof bv) {
            final Panel panel = new Panel();
            panel.setLayout(gridBagLayout);
            gridBagConstraints.weightx = 0.0;
            gridBagConstraints.gridwidth = -1;
            gridBagConstraints.anchor = 17;
            gridBagConstraints.insets = new Insets(0, 5, 0, 0);
            gridBagLayout.setConstraints(this.g, gridBagConstraints);
            panel.add(this.g);
            final Label label = new Label(com.esial.util.c.a("Show users in all rooms"));
            gridBagConstraints.weightx = 1.0;
            gridBagConstraints.gridwidth = 0;
            gridBagConstraints.fill = 2;
            gridBagConstraints.anchor = 17;
            gridBagLayout.setConstraints(label, gridBagConstraints);
            panel.add(label);
            gridBagConstraints.gridwidth = 0;
            gridBagLayout.setConstraints(panel, gridBagConstraints);
            this.add(panel);
        }
        else {
            gridBagConstraints.weightx = 1.0;
            gridBagConstraints.insets = new Insets(0, 0, 0, 0);
            gridBagLayout.setConstraints(this.g, gridBagConstraints);
            this.add(this.g);
        }
        gridBagConstraints.fill = 2;
        final t t = new t(this.txfSearch = new TextField(1));
        this.txfSearch.setBackground(g.cc.clrSrchBack);
        this.txfSearch.setForeground(g.cc.clrSrchText);
        gridBagLayout.setConstraints(t, gridBagConstraints);
        this.add(t);
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.fill = 1;
        super.f.setSize(193, 50);
        final t t2 = new t(super.f);
        gridBagLayout.setConstraints(t2, gridBagConstraints);
        this.add(t2);
        final ap ap = new ap(null, "icon");
        final ap ap2 = new ap(null, "star", false);
        final ap ap3 = new ap(null, "flag", false);
        final ap ap4 = new ap(null, "audio", false);
        final ap ap5 = new ap(null, "video", false);
        final ap ap6 = new ap(null, "state", false);
        final ap ap7 = new ap(com.esial.util.c.a("Nickname"), "name");
        ap.d(24);
        ap.c(24);
        ap2.d(13);
        ap2.c(0);
        ap3.d(18);
        ap3.c(0);
        ap4.d(15);
        ap4.c(0);
        ap5.d(15);
        ap5.c(0);
        ap6.d(19);
        ap6.c(0);
        ap7.c(true);
        super.f.a(true);
        super.f.b(ap);
        super.f.b(ap2);
        super.f.b(ap3);
        super.f.b(ap4);
        super.f.b(ap5);
        super.f.b(ap6);
        super.f.b(ap7);
        super.f.a(ap7);
        super.f.l(24);
        super.f.setPopup(this.mpuContx);
        this.updateOpt();
        this.updateList();
    }
}

// 
// Decompiled by Procyon v0.5.30
// 

package com.diginet.digichat.client;

import java.awt.Color;
import java.awt.BorderLayout;
import com.diginet.digichat.awt.u;
import com.diginet.digichat.awt.t;
import com.diginet.digichat.awt.DragListener;
import com.diginet.digichat.awt.DragContainer;
import java.awt.LayoutManager;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.MediaTracker;
import java.lang.reflect.Constructor;
import com.diginet.digichat.awt.MenuBar;
import com.diginet.digichat.network.v;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import com.diginet.digichat.util.a5;
import com.diginet.digichat.common.j;
import java.net.URL;
import com.diginet.digichat.util.dx;
import java.awt.Event;
import com.esial.util.c;
import java.awt.Container;
import java.awt.Frame;
import java.awt.Component;
import com.diginet.digichat.common.bn;
import com.diginet.digichat.awt.r;
import com.diginet.digichat.awt.bk;
import com.diginet.digichat.awt.MenuPanel;
import com.diginet.digichat.awt.MenuItem;
import com.diginet.digichat.common.Game;
import com.diginet.digichat.awt.a6;
import java.util.Hashtable;
import com.diginet.digichat.awt.LayeredContainer;
import java.awt.Insets;
import java.awt.Dimension;
import java.awt.Image;
import com.diginet.digichat.awt.bs;
import java.awt.Canvas;
import java.awt.Panel;
import com.diginet.digichat.awt.bj;
import com.diginet.digichat.awt.ScrollLine;
import com.diginet.digichat.awt.a8;
import com.diginet.digichat.util.ae;
import com.diginet.digichat.util.s;
import com.diginet.digichat.awt.br;

public class bq extends br implements s, a7, ae
{
    private static final String strYes;
    private static final String[] strStates;
    private static final String[] strImages;
    public a8 a;
    public ScrollLine scrlLine;
    public bj b;
    public bj bjGames;
    private bt usTab;
    private bw rmTab;
    private by bdTab;
    private WebIMTab imTab;
    private GamesTab gmTab;
    public h g;
    public TextPanel txpMess;
    public Panel pnlHelp;
    private Canvas i;
    private Canvas j;
    private Canvas k;
    private Canvas btnGames;
    private bs l;
    private bi m;
    private Image n;
    private int o;
    private int p;
    private int q;
    private int r;
    private Dimension s;
    private Insets t;
    private Image u;
    private b0 v;
    private LayeredContainer lrcParent;
    private Hashtable x;
    private int y;
    private int nQuery;
    private a6 a6Query;
    private bd bdQuery;
    private Game gmQuery;
    private MenuItem itmFavor;
    private MenuItem itmLeave;
    private MenuPanel mnuMain;
    private MenuPanel mnuStates;
    private MenuPanel mnuRooms;
    private MenuPanel mnuGames;
    private int iRooms;
    private int iGames;
    private int[] mRooms;
    private int[] mGames;
    
    private final Canvas a(final String s, final String s2, final String s3, final String s4, final String s5) {
        s a = null;
        if (this.g.cc.l() && s != null) {
            final Image a2 = this.g.a(String.valueOf(String.valueOf(s).concat(String.valueOf(s2))).concat(String.valueOf("_button_up.gif")), true);
            final Image a3 = this.g.a(String.valueOf(String.valueOf(s).concat(String.valueOf(s2))).concat(String.valueOf("_button_dn.gif")), true);
            final Image a4 = this.g.a(String.valueOf(String.valueOf(s).concat(String.valueOf(s2))).concat(String.valueOf("_button_disabled.gif")), true);
            if (a2 != null && a3 != null && (a4 != null || s5 == null)) {
                a = bk.a(a2, a3, a4);
                ((bk)a).a(s4, s5);
            }
        }
        if (a == null) {
            if (s3 == null) {
                a = new r(70, 20);
            }
            else {
                ((r)(a = new r(28, 20))).a(this.g.a(s3, false, 130));
            }
            ((r)a).a(s4, s5);
        }
        return (r)a;
    }
    
    private final Canvas a(final String s, final String s2, final String s3, final String s4) {
        s a = null;
        if (this.g.cc.l() && s != null) {
            final Image a2 = this.g.a(String.valueOf(String.valueOf(s).concat(String.valueOf(s2))).concat(String.valueOf("_button_up.gif")), true);
            final Image a3 = this.g.a(String.valueOf(String.valueOf(s).concat(String.valueOf(s2))).concat(String.valueOf("_button_dn.gif")), true);
            if (a2 != null && a3 != null) {
                a = bk.a(a2, a3, null);
                ((bk)a).a(s4, null);
            }
        }
        if (a == null) {
            if (s3 == null) {
                a = new r(70, 20);
            }
            else {
                a = new r(s3, 70, 20);
            }
            ((r)a).a(s4, null);
        }
        return (r)a;
    }
    
    public void a(final bn bn) {
        this.a.a(bn);
    }
    
    public void a(final bf bf) {
        this.m.a(bf);
    }
    
    public void a() {
        this.a.a();
        if (DigiChatAppletAbstract.embedded) {
            this.lrcParent.removeBar();
            this.lrcParent.setMain(DigiChatAppletAbstract.login);
        }
    }
    
    public a8 e() {
        return this.a;
    }
    
    public Frame b() {
        return this.v;
    }
    
    public Container c() {
        return this;
    }
    
    public void a(final Object o, final Object o2) {
        if (o == this.a6Query) {
            if (bq.strYes.equals(o2)) {
                this.g.callGame(this.gmQuery, this.bdQuery.w(), this.nQuery);
            }
            else {
                this.g.sendJoin(this.bdQuery.w(), this.nQuery, true);
            }
        }
    }
    
    public String a(final Object o) {
        try {
            if (o == this.m) {
                return com.esial.util.c.a("Chat messages are displayed here.  Single-click on a private message to reply.");
            }
            return null;
        }
        catch (Exception ex) {
            return null;
        }
    }
    
    public boolean handleEvent(final Event event) {
        switch (event.id) {
            case 201: {
                if (this.k instanceof r) {
                    ((r)this.k).e();
                }
                else {
                    ((bk)this.k).a();
                }
                return true;
            }
            case 401: {
                if (!this.txpMess.isMess(event)) {
                    break;
                }
                if (event.key == 27) {
                    this.txpMess.appendText("\n");
                    break;
                }
                if (event.key != 10 && event.key != dx.a) {
                    break;
                }
                if (this.j instanceof r) {
                    ((r)this.j).e();
                }
                else {
                    ((bk)this.j).a();
                }
                return true;
            }
            case 1001: {
                if (event.target == this.j) {
                    final String message;
                    if ((message = this.txpMess.getMessage()).length() > 0) {
                        final bo bo;
                        if (this.g.i(64) && ((bo = (bo)this.g.ac.e(this.g.b)) == null || bo.h == null)) {
                            new a6(this.v, com.esial.util.c.a("Note"), com.esial.util.c.a("For you is forbidden to write in public chat room"), this.g).setVisible(true);
                        }
                        else {
                            if (System.currentTimeMillis() - this.g.p() < this.g.q()) {
                                try {
                                    Thread.sleep(this.g.q() - (System.currentTimeMillis() - this.g.p()));
                                }
                                catch (InterruptedException ex) {}
                            }
                            if (this.b(message)) {
                                this.g.c(message, this.txpMess.getStyle());
                                this.m.e();
                            }
                        }
                    }
                    if (dx.d) {
                        this.txpMess.requestFocus();
                    }
                    return true;
                }
                if (event.target == this.i) {
                    this.g.c(0);
                    return true;
                }
                if (event.target == this.btnGames) {
                    this.g.callGames();
                    return true;
                }
                if (event.target == this.k) {
                    this.g.u();
                    return true;
                }
                if (event.target instanceof MenuItem) {
                    if (this.g.an.a(event.target)) {
                        this.g.a((URL)this.g.ao.d(this.g.an.b(event.target)), "_blank");
                    }
                    else if (event.target == this.itmFavor) {
                        this.g.getScripts().addFavor();
                    }
                    else {
                        final int index;
                        if ((index = this.mnuStates.indexOf(event.target)) >= 0) {
                            this.g.saveState(index);
                        }
                        else {
                            final int index2;
                            if (this.mnuRooms != null && (index2 = this.mnuRooms.indexOf(event.target)) >= 0) {
                                this.g.a((bo)this.g.ac.e(this.mRooms[index2]));
                            }
                            else {
                                final int index3;
                                if (this.mnuGames != null && (index3 = this.mnuGames.indexOf(event.target)) >= 0) {
                                    this.g.callGame((Game)this.g.games.e(this.mGames[index3]));
                                }
                                else {
                                    if (event.target != this.itmLeave) {
                                        return this.g.a(event);
                                    }
                                    this.g.leaveOffline();
                                }
                            }
                        }
                    }
                    return true;
                }
                if (event.arg instanceof bf) {
                    final bf bf = (bf)event.arg;
                    final bd bd = (bd)this.g.ab.e(bf.f);
                    if (bf.q && bf.f != this.g.w()) {
                        this.g.a(bf, bd);
                        break;
                    }
                    switch (bf.nType) {
                        case 1: {
                            if (bd != null && this.g.i(43)) {
                                this.g.a(bf, (j)bd);
                                return true;
                            }
                            break;
                        }
                        case 2: {
                            if (bd != null) {
                                this.g.callWebIM(bd);
                                return true;
                            }
                            break;
                        }
                        case 3: {
                            if (this.bdTab != null && this.bdTab.callPriv(bf)) {
                                return true;
                            }
                            break;
                        }
                        case 4: {
                            final Game game;
                            if (bd != null && (game = (Game)this.g.games.e(bf.nId)) != null) {
                                if (bf.fDouble) {
                                    this.g.callGame(game, bd.w(), bf.nLoc);
                                }
                                else {
                                    this.nQuery = bf.nLoc;
                                    final b0 v = this.v;
                                    final String a = com.esial.util.c.a("Query");
                                    final String[] array = { bq.strYes, com.esial.util.c.a("No") };
                                    final String[] array2 = { null };
                                    final int n = 0;
                                    final String a2 = com.esial.util.c.a("%1 invite you play to %2.\n\nYou confirm this invitation?");
                                    final String[] array3 = new String[2];
                                    final int n2 = 0;
                                    final bd bdQuery = bd;
                                    this.bdQuery = bdQuery;
                                    array3[n2] = bdQuery.x();
                                    final int n3 = 1;
                                    final Game gmQuery = game;
                                    this.gmQuery = gmQuery;
                                    array3[n3] = gmQuery.x();
                                    array2[n] = a5.a(a2, array3);
                                    (this.a6Query = new a6(v, a, array, array2, this, this.g)).setVisible(true);
                                }
                                return true;
                            }
                            break;
                        }
                    }
                    break;
                }
                else {
                    if (event.arg instanceof URL) {
                        this.g.a((URL)event.arg, "_blank");
                        return true;
                    }
                    break;
                }
                break;
            }
        }
        return super.handleEvent(event);
    }
    
    public void paint(final Graphics graphics) {
        super.paint(graphics);
        if (this.n != null) {
            this.s = this.size();
            this.t = this.insets();
            this.o = this.s.width - this.t.right - this.t.left + 30;
            this.p = this.s.height - this.t.bottom - this.t.top + 30;
            this.q = this.n.getWidth(this);
            this.r = this.n.getHeight(this);
            this.u = this.createImage(this.o, this.p);
            final Graphics graphics2 = this.u.getGraphics();
            graphics2.drawImage(this.n, 0, 0, null);
            for (int i = 0; i <= this.o / this.q; ++i) {
                for (int j = 0; j <= this.p / this.r; ++j) {
                    if (i + j > 0) {
                        graphics2.copyArea(0, 0, this.q, this.r, i * this.q, j * this.r);
                    }
                }
            }
            graphics2.dispose();
            graphics.drawImage(this.u, 0, 0, this);
        }
    }
    
    public void d() {
        this.m.d();
        this.txpMess.setFont(this.g.cc.b());
    }
    
    public boolean a(final bd bd) {
        final boolean a = this.usTab.a(bd);
        if (this.imTab != null) {
            this.imTab.removeList(bd);
        }
        return a;
    }
    
    public void b(final bo bo) {
        if (this.v != null) {
            this.v.a(bo);
        }
    }
    
    private void setLogo() {
        boolean help;
        String s;
        if (this.g.fBrand) {
            this.g.bm = this.g.urlBrand;
            help = (this.g.fTheme && this.g.cc.getBrand());
            s = "brandLogo.gif";
        }
        else {
            this.g.bm = this.g.urlHelp;
            help = this.g.cc.getHelp();
            s = "helpLogo.gif";
        }
        if (!help || (this.g.cc.x = this.g.loadImage(String.valueOf(this.g.cc.f()).concat(String.valueOf(s)), this, 80)) == null) {
            this.g.cc.x = this.g.loadImage(s, this, 80);
        }
    }
    
    public void updateOpt() {
        this.lrcParent.setDirect(com.diginet.digichat.network.v.a(this.g.as, 48));
        this.usTab.updateOpt();
    }
    
    public void setPlus() {
        final MenuBar bar;
        if ((bar = this.lrcParent.getBar()) != null) {
            bar.setLabel(DigiChatAppletAbstract.OEM_DigiChat, 0);
        }
        Label_0227: {
            if (this.v != null) {
                final bo bo;
                if ((bo = (bo)this.g.ac.e(this.g.b)) != null) {
                    this.v.a(bo);
                }
                final String s = "microsoft";
                boolean b;
                try {
                    final String lowerCase;
                    final int index;
                    final int n;
                    b = ((index = (lowerCase = System.getProperty("java.vendor").toLowerCase()).indexOf(s)) < 0 || (index > 0 && !Character.isSpaceChar(lowerCase.charAt(index - 1))) || ((n = index + s.length()) < lowerCase.length() && !Character.isSpaceChar(lowerCase.charAt(n))));
                }
                catch (Throwable t) {
                    b = false;
                }
                final Image loadImage = this.g.loadImage("siteIcon.gif", this, 320);
                if (!b) {
                    if (!this.g.fIcon || loadImage == null || this.v.getIconImage() != null) {
                        break Label_0227;
                    }
                }
                try {
                    this.v.setIconImage(this.g.fIcon ? loadImage : null);
                }
                catch (Throwable t2) {}
            }
        }
        this.setLogo();
        this.changeImage(this.g.cc.x, this.g.bm);
        this.g.setPlus();
    }
    
    public void updateMess() {
        this.txpMess.setButtons();
        for (int i = 0; i < this.g.z.b(); ++i) {
            ((aa)this.g.z.d(i)).txpMess.setButtons();
        }
    }
    
    public void setAppear() {
        this.updateButtons();
        this.updateMess();
    }
    
    public void updateButtons() {
        this.usTab.updateElems();
    }
    
    private Panel updateTab(Panel panel, final Class clazz, final boolean b, final int n, final String s, final String s2) {
        if (b) {
            if (panel == null) {
                final Constructor[] constructors = clazz.getConstructors();
                for (int i = 0; i < constructors.length; ++i) {
                    final Class[] parameterTypes;
                    if ((parameterTypes = constructors[i].getParameterTypes()).length == 1) {
                        if (parameterTypes[0] != Class.forName("com.diginet.digichat.client.i")) {
                            if (parameterTypes[0] != Class.forName("com.diginet.digichat.client.h")) {
                                continue;
                            }
                        }
                        try {
                            panel = (Panel)constructors[i].newInstance(this.g);
                            final String f = this.g.cc.f();
                            this.l.a(com.esial.util.c.a(s), panel, n, this.g.a(String.valueOf(String.valueOf(f).concat(String.valueOf(s2))).concat(String.valueOf("_tab_up.gif")), true), this.g.a(String.valueOf(String.valueOf(f).concat(String.valueOf(s2))).concat(String.valueOf("_tab_down.gif")), true));
                            this.l.invalidate();
                        }
                        catch (Exception ex) {}
                        break;
                    }
                }
            }
        }
        else if (panel != null) {
            if (this.l.getComponentAt(0, 0) == panel) {
                this.l.b(0);
            }
            this.l.remove(panel);
            panel = null;
            this.l.invalidate();
        }
        return panel;
    }
    
    public void updateTabs() {
        final Panel updateTab;
        if ((updateTab = this.updateTab(this.rmTab, Class.forName("com.diginet.digichat.client.bw"), this.g.r(), 1, "Rooms", "rooms")) != null && this.rmTab == null) {
            for (int i = 0; i < this.g.ac.b(); ++i) {
                ((bw)updateTab).b((bo)this.g.ac.d(i));
            }
        }
        this.rmTab = (bw)updateTab;
        final Panel updateTab2;
        if ((updateTab2 = this.updateTab(this.bdTab, Class.forName("com.diginet.digichat.client.by"), this.g.k(), (this.rmTab == null) ? 1 : 2, "Buddies", "buddies")) == null && this.bdTab != null) {
            this.bdTab.closeBuddies();
        }
        this.bdTab = (by)updateTab2;
        final Panel updateTab3;
        if ((updateTab3 = this.updateTab(this.imTab, Class.forName("com.diginet.digichat.client.WebIMTab"), this.g.isImTab(), (this.rmTab == null && this.bdTab == null) ? 1 : ((this.rmTab != null && this.bdTab != null) ? 3 : 2), "1:1 Users", "imusers")) != null && this.imTab == null) {
            ((WebIMTab)updateTab3).updateList();
        }
        this.imTab = (WebIMTab)updateTab3;
        this.gmTab = (GamesTab)this.updateTab(this.gmTab, Class.forName("com.diginet.digichat.client.GamesTab"), this.g.isGmTab(), -1, "Games", "games");
        if (!this.l.isValid()) {
            final Component[] components = this.l.getComponents();
            for (int j = 0; j < components.length; ++j) {
                if (this.g.cc.l()) {
                    components[j].setSize(175, 100);
                }
                else {
                    components[j].setSize(1, 1);
                }
                components[j].invalidate();
                components[j].validate();
            }
            this.l.setSize(1, 1);
            final Component[] components2 = this.getComponents();
            for (int k = 0; k < components2.length; ++k) {
                components2[k].invalidate();
                components2[k].validate();
            }
            this.l.repaint();
            this.invalidate();
            this.validate();
        }
    }
    
    public void updateLocations() {
        if (this.bdTab != null) {
            this.bdTab.updateLocations();
        }
    }
    
    public void closeBuddies() {
        if (this.bdTab != null) {
            this.bdTab.closeBuddies();
        }
    }
    
    public void updateList() {
        this.usTab.updateList();
    }
    
    public void setScrolls() {
        this.scrlLine.setScrolls();
    }
    
    public void setVisible(final boolean visible) {
        super.setVisible(visible);
    }
    
    public void a(final boolean b) {
        this.l.b(0);
        this.usTab.a(b);
    }
    
    public boolean[] getLoc() {
        final boolean[] array;
        if (this.bdTab == null) {
            array = new boolean[] { false };
        }
        else {
            this.bdTab.getLoc();
        }
        return array;
    }
    
    public void setBuddy(final j j, final boolean b, final boolean b2) {
        if (this.bdTab != null) {
            this.bdTab.setBuddy(j, b, b2);
        }
    }
    
    public void setParams(final v params) {
        if (this.bdTab != null) {
            this.bdTab.setParams(params);
        }
    }
    
    public void setProf(final v prof) {
        if (this.bdTab != null) {
            this.bdTab.setProf(prof);
        }
    }
    
    public void addMess(final String s, final int n, final bd bd, final String[] array, final long[] array2) {
        if (this.bdTab != null) {
            this.bdTab.addMess(s, n, bd, array, array2);
        }
    }
    
    public void updateList(final bd bd, final boolean b) {
        this.usTab.updateList(bd, b);
        if (this.imTab != null) {
            this.imTab.updateList(bd);
        }
    }
    
    private void flood(final String s, final int n) {
        if (!com.diginet.digichat.network.v.a(this.g.as, n) || (!com.diginet.digichat.network.v.a(this.g.as, 54) && !com.diginet.digichat.network.v.a(this.g.as, 55)) || this.g.i(125) || this.g.i(93)) {
            new a6(this.v, com.esial.util.c.a("Note"), String.valueOf(String.valueOf(s).concat(String.valueOf("\n"))).concat(String.valueOf(com.esial.util.c.a("Your message not will be sent."))), this.g).setVisible(true);
        }
        else {
            this.g.v(s);
        }
    }
    
    private final boolean b(final String s) {
        this.x.put(new Integer(this.y), new Object[] { new Long(System.currentTimeMillis()), s });
        if (this.g.b4 != 0 && this.g.b5 != 0) {
            final Object[] array = this.x.get(new Integer(this.y - this.g.b4 + 1));
            if (array != null && System.currentTimeMillis() - (long)array[0] < this.g.b5 * 1000) {
                this.flood(a5.a(com.esial.util.c.a("You send messages more often than %1 per %2 seconds."), new String[] { Integer.toString(this.g.b4 - 1), Integer.toString(this.g.b5) }), 44);
                return false;
            }
        }
        if (this.g.nChars != 0 && s.length() > this.g.nChars) {
            this.flood(a5.a(com.esial.util.c.a("You message longer than %1 chars."), new String[] { Integer.toString(this.g.nChars) }), 45);
            return false;
        }
        if (this.g.b3 != 0) {
            int n = 1;
            for (int i = this.y - 1; i > this.y - this.g.b3; --i) {
                final Object[] array2 = this.x.get(new Integer(i));
                if (array2 == null) {
                    break;
                }
                (long)array2[0];
                if (((String)array2[1]).equals(s)) {
                    ++n;
                }
            }
            if (n >= this.g.b3) {
                this.flood(a5.a(com.esial.util.c.a("You send same message more than %1 times."), new String[] { Integer.toString(this.g.b3 - 1) }), 46);
                return false;
            }
        }
        ++this.y;
        return true;
    }
    
    private int[] updateMenu(final MenuItem menuItem, final int n, final String s, final MenuPanel menuPanel, final int[] array) {
        final int length = menuPanel.length();
        for (int i = 0; i < length; ++i) {
            if (array[i] == n) {
                menuPanel.set(i, menuItem);
                return array;
            }
        }
        int length2 = -1;
        for (int j = 0; j < length; ++j) {
            if (s.compareTo(menuPanel.getLabel(j)) < 0) {
                menuPanel.insert(length2 = j, menuItem);
                break;
            }
        }
        if (length2 < 0) {
            length2 = menuPanel.length();
            menuPanel.add(menuItem);
        }
        final int length3;
        int[] array2;
        if (array.length < (length3 = menuPanel.length())) {
            array2 = new int[length3];
            if (length2 > 0) {
                System.arraycopy(array, 0, array2, 0, length2);
            }
        }
        else {
            array2 = array;
        }
        final int n2;
        if ((n2 = length3 - (length2 + 1)) > 0) {
            System.arraycopy(array, length2, array2, length2 + 1, n2);
        }
        array2[length2] = n;
        return array2;
    }
    
    private void updateRoom(final bo bo) {
        final MenuItem menuItem = new MenuItem(bo.x(), (bo.h == null || bo.h.a()) ? null : com.diginet.digichat.client.i.c, null);
        menuItem.setColor(bo.clrName, bo.clrBack);
        menuItem.setSuff(Integer.toString(bo.c));
        menuItem.check(bo.a);
        this.mRooms = this.updateMenu(menuItem, bo.w(), bo.x(), this.mnuRooms, this.mRooms);
    }
    
    private void updateGame(final Game game) {
        if (this.mnuGames != null) {
            if (this.g.fLogos && game.imgLogo == null) {
                game.imgLogo = this.g.a(game.getLogo(), true);
            }
            this.mGames = this.updateMenu(new MenuItem(game.x(), game.imgLogo, null), game.w(), game.x(), this.mnuGames, this.mGames);
        }
    }
    
    private MenuItem createRooms() {
        this.mnuRooms = new MenuPanel();
        for (int i = 0; i < this.g.ac.b(); ++i) {
            this.updateRoom((bo)this.g.ac.d(i));
        }
        return new MenuItem(com.esial.util.c.a("Rooms"), null, this.mnuRooms);
    }
    
    private MenuItem createGames() {
        this.mnuGames = new MenuPanel();
        for (int i = 0; i < this.g.games.b(); ++i) {
            this.updateGame((Game)this.g.games.d(i));
        }
        return new MenuItem(com.esial.util.c.a("Games"), null, this.mnuGames);
    }
    
    public void updateRooms() {
        if (this.g.nRooms == 1) {
            if (this.mnuRooms == null) {
                this.iGames += 2;
                this.mnuMain.insert(this.iRooms, this.createRooms());
                this.mnuMain.insertSeparator(this.iRooms);
            }
        }
        else if (this.mnuRooms != null) {
            this.mnuMain.remove(this.iRooms);
            this.mnuMain.remove(this.iRooms);
            this.mnuRooms = null;
            this.iGames -= 2;
        }
    }
    
    public void updateGames() {
        if (this.g.isGmMenu()) {
            if (this.mnuGames == null) {
                this.iGames = this.iRooms;
                if (this.mnuRooms != null) {
                    this.iGames += 2;
                }
                this.mnuMain.insert(this.iGames, this.createGames());
                this.mnuMain.insertSeparator(this.iGames);
            }
        }
        else if (this.mnuGames != null) {
            this.mnuMain.remove(this.iGames);
            this.mnuMain.remove(this.iGames);
            this.mnuGames = null;
        }
        if (this.g.isGmButton()) {
            if (!this.pnlHelp.isAncestorOf(this.bjGames)) {
                this.pnlHelp.add(this.bjGames);
            }
        }
        else if (this.pnlHelp.isAncestorOf(this.bjGames)) {
            this.pnlHelp.remove(this.bjGames);
        }
        if (!this.pnlHelp.isValid()) {
            this.pnlHelp.validate();
        }
    }
    
    private void removeMenu(final MenuPanel menuPanel, final int n, final int[] array) {
        if (menuPanel != null) {
            for (int i = 0; i < menuPanel.length(); ++i) {
                if (array[i] == n) {
                    menuPanel.remove(i);
                    final int n2;
                    if ((n2 = menuPanel.length() - i) > 0) {
                        System.arraycopy(array, i + 1, array, i, n2);
                    }
                    return;
                }
            }
        }
    }
    
    public void setState(final int n) {
        this.mnuStates.check(n);
    }
    
    public void setRoom(final bo bo) {
        switch (this.g.nRooms) {
            case 0: {
                if (this.rmTab != null) {
                    this.rmTab.b(bo);
                }
                break;
            }
            case 1: {
                this.updateRoom(bo);
                break;
            }
        }
    }
    
    public void deleteRoom(final bo bo) {
        switch (this.g.nRooms) {
            case 0: {
                this.rmTab.a(bo);
                break;
            }
            case 1: {
                this.removeMenu(this.mnuRooms, bo.w(), this.mRooms);
                break;
            }
        }
    }
    
    public void setGame(final Game game) {
        switch (this.g.nGames) {
            case 2: {
                if (this.gmTab != null) {
                    this.gmTab.setGame(game);
                }
                break;
            }
            case 3: {
                this.updateGame(game);
                break;
            }
        }
    }
    
    public void deleteGame(final Game game) {
        switch (this.g.nGames) {
            case 2: {
                if (this.gmTab != null) {
                    this.gmTab.removeGame(game);
                }
                break;
            }
            case 3: {
                this.removeMenu(this.mnuGames, game.w(), this.mGames);
                break;
            }
        }
    }
    
    public bq(final h g, final LayeredContainer lrcParent) {
        super(0);
        this.x = new Hashtable();
        this.y = 0;
        this.lrcParent = lrcParent;
        this.v = ((lrcParent instanceof b0) ? ((b0)lrcParent) : null);
        this.setBackground(g.cc.c);
        this.g = g;
        g.a0 = new MediaTracker(this);
        com.diginet.digichat.client.i.c = g.a("lockIcon.gif", false, 20);
        com.diginet.digichat.client.i.imgPointer = g.a("pointerIcon.gif", false, 20);
        this.setLogo();
        g.imgShield = g.a("shieldLogo.gif", false, 210);
        for (int i = 0; i < bq.strImages.length; ++i) {
            Image image;
            if (!g.cc.getState() || (image = g.a(String.valueOf(g.cc.f()).concat(String.valueOf(bq.strImages[i])), true)) == null) {
                image = g.a(bq.strImages[i], false);
            }
            g.cc.imgStates[i] = image;
        }
        this.mRooms = new int[0];
        this.mGames = new int[0];
        final MenuPanel menuPanel = null;
        this.mnuGames = menuPanel;
        this.mnuRooms = menuPanel;
        final GridBagLayout gridBagLayout = new GridBagLayout();
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        final bj bj = new bj(g.cc.v);
        final Panel panel = new Panel();
        final Panel panel2 = new Panel();
        panel.setLayout(gridBagLayout);
        panel2.setLayout(gridBagLayout);
        bj.setBackground(g.cc.d);
        this.setLayout(gridBagLayout);
        bj.setLayout(gridBagLayout);
        this.txpMess = new TextPanel(g, lrcParent, g, "Type your message here, then hit ENTER or click \"Send\" to send it to all users in the current room.");
        (this.m = new bi(g, true)).setFont(g.cc.b());
        final boolean b = g.r() || g.s() || g.k();
        if (!b || g.cc.l()) {
            gridBagConstraints.gridwidth = 0;
        }
        else {
            gridBagConstraints.gridwidth = 1;
        }
        gridBagConstraints.insets = new Insets(2, 3, 2, 3);
        gridBagConstraints.fill = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        final t t = new t(this.m);
        gridBagLayout.setConstraints(t, gridBagConstraints);
        if (g.cc.l()) {
            panel.add(t);
        }
        else {
            bj.add(t);
        }
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.weightx = 0.0;
        gridBagConstraints.fill = 1;
        Image a = null;
        Image a2 = null;
        Image a3 = null;
        Image a4 = null;
        Image a5 = null;
        Image a6 = null;
        Image a7 = null;
        Image a8 = null;
        Image a9 = null;
        Image a10 = null;
        if (g.cc.m()) {
            final String f = g.cc.f();
            a = g.a(String.valueOf(f).concat(String.valueOf("users_tab_up.gif")), true);
            a2 = g.a(String.valueOf(f).concat(String.valueOf("users_tab_down.gif")), true);
            a3 = g.a(String.valueOf(f).concat(String.valueOf("rooms_tab_up.gif")), true);
            a4 = g.a(String.valueOf(f).concat(String.valueOf("rooms_tab_down.gif")), true);
            a5 = g.a(String.valueOf(f).concat(String.valueOf("buddies_tab_up.gif")), true);
            a6 = g.a(String.valueOf(f).concat(String.valueOf("buddies_tab_down.gif")), true);
            a7 = g.a(String.valueOf(f).concat(String.valueOf("imusers_tab_up.gif")), true);
            a8 = g.a(String.valueOf(f).concat(String.valueOf("imusers_tab_down.gif")), true);
            a9 = g.a(String.valueOf(f).concat(String.valueOf("games_tab_up.gif")), true);
            a10 = g.a(String.valueOf(f).concat(String.valueOf("games_tab_down.gif")), true);
            if (a == null || a2 == null || a3 == null || a4 == null || a5 == null || a6 == null || a7 == null || a8 == null || a9 == null || a10 == null) {
                g.cc.e(false);
            }
        }
        this.l = new bs(g, g.cc.m());
        this.usTab = new bt(g);
        if (g.s()) {
            this.l.a(com.esial.util.c.a("Users"), this.usTab, a, a2);
        }
        if (g.r()) {
            this.rmTab = new bw(this.g);
            if (this.g.cc.l()) {
                this.rmTab.setSize(175, 100);
            }
            this.l.a(com.esial.util.c.a("Rooms"), this.rmTab, a3, a4);
        }
        if (g.k()) {
            this.bdTab = new by(g);
            if (g.cc.l()) {
                this.bdTab.setSize(175, 100);
            }
            this.l.a(com.esial.util.c.a("Buddies"), this.bdTab, a5, a6);
        }
        if (g.isImTab()) {
            this.imTab = new WebIMTab(this.g);
            if (this.g.cc.l()) {
                this.imTab.setSize(175, 100);
            }
            this.l.a(com.esial.util.c.a("1:1 Users"), this.imTab, a7, a8);
        }
        if (g.isGmTab()) {
            this.gmTab = new GamesTab(this.g);
            if (this.g.cc.l()) {
                this.gmTab.setSize(175, 100);
            }
            this.l.a(com.esial.util.c.a("Games"), this.gmTab, a9, a10);
        }
        gridBagLayout.setConstraints(this.l, gridBagConstraints);
        if (b) {
            if (g.cc.l()) {
                panel2.add(this.l);
            }
            else {
                bj.add(this.l);
            }
        }
        this.txpMess.setFont(g.cc.b());
        this.txpMess.setBackground((!dx.f || dx.b == 4) ? g.cc.h : g.cc.h.darker());
        this.txpMess.setForeground(g.cc.g);
        gridBagConstraints.gridheight = 0;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.0;
        gridBagLayout.setConstraints(this.txpMess, gridBagConstraints);
        if (g.cc.l()) {
            panel.add(this.txpMess);
        }
        else {
            bj.add(this.txpMess);
        }
        gridBagConstraints.gridheight = -1;
        gridBagConstraints.weightx = (g.cc.l() ? 0.0 : 1.0E-5);
        final String f2 = g.cc.f();
        this.btnGames = this.a(f2, "games", "gamesOptionIcon.gif", com.esial.util.c.a("Click here for play the games."), com.esial.util.c.a("Games are disabled."));
        this.i = this.a(f2, "settings", com.esial.util.c.a("Settings"), com.esial.util.c.a("Click here to enter personal information, such as your name and icon, or to change options such as font size and color."));
        this.j = this.a(f2, "send", null, com.esial.util.c.a("Click here, or press the RETURN or ENTER key, to send your message to all users in the current room."));
        this.k = this.a(f2, "logout", com.esial.util.c.a("Logout"), com.diginet.digichat.util.a5.a(com.esial.util.c.a("Click here to logout and end your %1 session."), new String[] { DigiChatAppletAbstract.OEM_DigiChat }));
        final Panel panel3 = g.cc.l() ? new Panel() : null;
        if (g.cc.l()) {
            panel3.setLayout(gridBagLayout);
            gridBagConstraints.insets = new Insets(0, 0, 0, 0);
        }
        gridBagConstraints.gridwidth = (g.e ? 0 : -1);
        gridBagLayout.setConstraints(this.i, gridBagConstraints);
        if (g.cc.l()) {
            panel3.add(this.i);
        }
        else {
            bj.add(this.i);
        }
        if (!g.e) {
            gridBagConstraints.gridwidth = 0;
            gridBagLayout.setConstraints(this.k, gridBagConstraints);
            if (g.cc.l()) {
                panel3.add(this.k);
            }
            else {
                bj.add(this.k);
            }
        }
        if (!g.cc.l()) {
            gridBagConstraints.insets = new Insets(2, 3, 2, 3);
        }
        gridBagConstraints.weightx = 0.0;
        gridBagConstraints.fill = 2;
        gridBagConstraints.gridheight = 0;
        gridBagConstraints.gridwidth = 0;
        if (!g.cc.l()) {
            final u u = new u(this.j);
            gridBagLayout.setConstraints(u, gridBagConstraints);
            bj.add(u);
        }
        else {
            gridBagLayout.setConstraints(this.j, gridBagConstraints);
            panel3.add(this.j);
            gridBagConstraints.insets = new Insets(2, 3, 2, 3);
            gridBagConstraints.gridheight = -1;
            gridBagConstraints.weightx = 1.0E-5;
            gridBagLayout.setConstraints(panel3, gridBagConstraints);
            if (b) {
                panel2.add(panel3);
            }
            else {
                panel.add(panel3);
            }
            gridBagConstraints.gridwidth = 1;
            gridBagConstraints.fill = 1;
            gridBagConstraints.weightx = 1.0;
            gridBagConstraints.weighty = 1.0;
            gridBagLayout.setConstraints(panel, gridBagConstraints);
            bj.add(panel);
            gridBagConstraints.gridwidth = 0;
            gridBagConstraints.fill = 3;
            gridBagConstraints.weightx = 0.0;
            gridBagConstraints.weighty = 0.0;
            gridBagLayout.setConstraints(panel2, gridBagConstraints);
            bj.add(panel2);
        }
        if (this.j instanceof r) {
            ((r)this.j).a(com.esial.util.c.a("Send"));
        }
        this.b = new bj(2, 2, 2, 2, 0);
        (this.a = new a8(g)).b((g.b1 <= 0) ? 7 : g.b1);
        this.b.setLayout(gridBagLayout);
        gridBagLayout.setConstraints(this.a, new GridBagConstraints());
        this.b.add(this.a);
        final GridBagLayout layout = new GridBagLayout();
        final GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
        final Panel panel4 = new Panel();
        panel4.setLayout(layout);
        gridBagConstraints2.gridwidth = 0;
        layout.setConstraints(this.b, gridBagConstraints2);
        panel4.add(this.b);
        gridBagConstraints2.weightx = 1.0;
        gridBagConstraints2.fill = 2;
        layout.setConstraints(this.scrlLine = new ScrollLine(g), gridBagConstraints2);
        panel4.add(this.scrlLine);
        gridBagConstraints.insets = new Insets(2, 3, 2, 3);
        gridBagConstraints.fill = 2;
        gridBagConstraints.gridheight = 1;
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.weightx = 0.0;
        gridBagConstraints.weighty = 0.0;
        gridBagLayout.setConstraints(panel4, gridBagConstraints);
        this.add(panel4);
        this.b.setVisible(false);
        final bz bz = new bz(this, g);
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.gridheight = -1;
        gridBagConstraints.fill = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagLayout.setConstraints(bj, gridBagConstraints);
        this.add(bj);
        gridBagConstraints.weighty = 0.0;
        gridBagConstraints.fill = 2;
        gridBagConstraints.gridwidth = 0;
        this.pnlHelp = new Panel(gridBagLayout);
        final Component a11 = this.a(g.cc.v);
        a11.setBackground(g.cc.f);
        a11.setForeground(g.cc.e);
        gridBagConstraints2.gridwidth = 1;
        gridBagLayout.setConstraints(a11, gridBagConstraints2);
        this.pnlHelp.add(a11);
        this.a(g.cc.x);
        this.a(g.bm);
        this.setShield(g.imgShield, g.urlShield);
        (this.bjGames = new bj(0)).setLayout(new BorderLayout(3, 3));
        this.bjGames.setBackground(g.cc.d);
        this.bjGames.add("Center", this.btnGames);
        gridBagConstraints2.weightx = 0.0;
        gridBagConstraints2.weighty = 1.0;
        gridBagConstraints2.gridwidth = 0;
        gridBagConstraints2.fill = 3;
        gridBagLayout.setConstraints(this.bjGames, gridBagConstraints2);
        if (g.isGmButton()) {
            this.pnlHelp.add(this.bjGames);
        }
        gridBagLayout.setConstraints(this.pnlHelp, gridBagConstraints);
        this.add(this.pnlHelp);
        lrcParent.setMain(this);
        this.mnuMain = new MenuPanel();
        final MenuItem menuItem = null;
        this.itmLeave = menuItem;
        this.itmFavor = menuItem;
        if (this.g.getScripts().isAdded()) {
            this.mnuMain.add(this.itmFavor = new MenuItem(com.esial.util.c.a("Add To Favorites...")));
            this.mnuMain.addSeparator();
        }
        for (int j = 1; j <= this.g.an.b(); ++j) {
            this.mnuMain.add((MenuItem)this.g.an.e(j));
        }
        if (this.g.an.b() > 0) {
            this.mnuMain.addSeparator();
        }
        this.mnuStates = new MenuPanel();
        for (int k = 0; k < bq.strStates.length; ++k) {
            this.mnuStates.add(new MenuItem(com.esial.util.c.a(bq.strStates[k]), (k == 0) ? null : this.g.cc.imgStates[k - 1], null));
        }
        this.mnuStates.check(this.g.nState);
        this.mnuMain.add(new MenuItem(com.esial.util.c.a("My Status"), null, this.mnuStates));
        this.iRooms = this.mnuMain.length();
        if (this.g.nRooms == 1) {
            this.mnuMain.addSeparator();
            this.mnuMain.add(this.createRooms());
        }
        this.iGames = this.mnuMain.length();
        if (this.g.isGmMenu()) {
            this.mnuMain.addSeparator();
            this.mnuMain.add(this.createGames());
        }
        if (this.g.i(111)) {
            this.mnuMain.addSeparator();
            this.mnuMain.add(this.itmLeave = new MenuItem(com.esial.util.c.a("Leave Offline Messages")));
        }
        this.g.createOptions(this.mnuMain);
        final MenuBar bar = new MenuBar();
        if (this.g.cc.getColors()) {
            bar.setStyle(this.g.cc.getMenuFont(), this.g.cc.clrMenuText, this.g.cc.clrMenuBack, this.g.cc.clrHghlText, this.g.cc.clrHghlBack);
        }
        else {
            bar.setStyle(this.g.cc.getMenuFont(), null, null, null, null);
        }
        bar.add(DigiChatAppletAbstract.OEM_DigiChat, this.mnuMain);
        lrcParent.setBar(bar);
        this.n = g.cc.g();
        this.updateOpt();
    }
    
    static {
        strYes = c.a("Yes");
        strStates = new String[] { "Online", "Busy", "Away", "Call", "Vb" };
        strImages = new String[] { "busyStateIcon.gif", "awayStateIcon.gif", "callStateIcon.gif", "vbStateIcon.gif" };
    }
}

// 
// Decompiled by Procyon v0.5.30
// 

package com.diginet.digichat.client;

import com.diginet.digichat.awt.MenuPanel;
import java.awt.Font;
import java.io.DataOutput;
import com.diginet.digichat.common.Game;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;
import java.applet.Applet;
import java.util.Enumeration;
import com.diginet.digichat.awt.CommonStyle;
import com.diginet.digichat.common.k;
import com.diginet.digichat.common.bg;
import com.diginet.digichat.util.l;
import com.diginet.digichat.common.d;
import com.diginet.digichat.util.a5;
import com.diginet.digichat.awt.a6;
import com.esial.util.c;
import com.diginet.digichat.common.bp;
import java.awt.image.ImageObserver;
import java.awt.Button;
import java.awt.Component;
import java.net.MalformedURLException;
import java.awt.Event;
import com.diginet.digichat.common.e;
import java.awt.Color;
import com.diginet.digichat.common.be;
import java.io.IOException;
import java.io.DataInput;
import com.diginet.digichat.network.v;
import java.awt.Rectangle;
import java.util.Random;
import java.text.SimpleDateFormat;
import java.util.Locale;
import com.diginet.digichat.common.a2;
import java.awt.Frame;
import com.diginet.digichat.util.o;
import java.applet.AudioClip;
import java.awt.MediaTracker;
import java.net.URL;
import com.diginet.digichat.util.ImagesLoader;
import java.util.Vector;
import com.diginet.digichat.common.SmilesArray;
import com.diginet.digichat.util.n;
import java.net.Socket;
import java.io.DataOutputStream;
import java.io.DataInputStream;
import java.awt.Image;
import com.diginet.digichat.common.j;

public abstract class i extends j implements Runnable
{
    private static final byte[] stdKey;
    protected static final String[] a;
    protected static final String[] b;
    private static final String[] strIMMess;
    private static final String[] strIMVars;
    public static final int ELM_PROF = 0;
    public static final int ELM_PRIV = 1;
    public static final int ELM_MESS = 2;
    public static final int ELM_FLAG = 3;
    public static final int ELM_MUTE = 4;
    public static final int ELM_ADDB = 5;
    public static final int ELM_KICK = 6;
    public static final int ELM_SPEC = 7;
    public static final int GUEST_TEXT_TEXT = 0;
    public static final int SUPER_TEXT_TEXT = 1;
    public static final int GUEST_TEXT_BACK = 2;
    public static final int SUPER_TEXT_BACK = 3;
    public static final int GUEST_NAME_TEXT = 4;
    public static final int SUPER_NAME_TEXT = 5;
    public static final int GUEST_NAME_BACK = 6;
    public static final int SUPER_NAME_BACK = 7;
    public static Image c;
    public static Image imgPointer;
    public boolean e;
    public boolean f;
    public boolean g;
    public boolean h;
    public boolean i;
    public boolean fBrand;
    public boolean fTheme;
    public boolean fIcon;
    public boolean fShorts;
    public boolean fSmiles;
    protected int j;
    protected long k;
    public int l;
    public int nSite;
    public int nBill;
    public int nPort;
    protected long m;
    protected boolean o;
    protected boolean p;
    protected boolean q;
    protected DataInputStream r;
    protected DataOutputStream s;
    protected CodedInputStream inpCoded;
    protected CodedOutputStream outCoded;
    protected String t;
    protected String u;
    protected b8 v;
    protected Thread w;
    protected Socket x;
    public x y;
    protected n z;
    public n aa;
    public n stars;
    public n ab;
    public n ac;
    public n ad;
    public n scrolls;
    public n ae;
    public n af;
    public n ag;
    public n resWatch;
    public n ah;
    public n ai;
    public n resellers;
    public n aj;
    public n ak;
    public n al;
    public n am;
    public n games;
    public n locations;
    public n an;
    public n ao;
    public n webIMUsers;
    public n recips;
    public n offlines;
    public SmilesArray smiles;
    public Vector ap;
    public Vector aq;
    public Vector ar;
    public Vector vecIMMess;
    public Vector vecPlays;
    public Vector[] palettes;
    public ImagesLoader iconsLoader;
    public ImagesLoader smilesLoader;
    public ImagesLoader starsLoader;
    public long as;
    public long at;
    public long av;
    public long aw;
    public long lDelta;
    public long lPlus;
    public long[] lChange;
    public long lAppear;
    public long lScrolls;
    public long lElems;
    public URL ax;
    protected boolean ay;
    protected boolean az;
    public MediaTracker a0;
    protected AudioClip[] a1;
    protected o a2;
    protected Frame a3;
    protected int a4;
    protected int a5;
    protected int a6;
    protected String a7;
    public boolean a8;
    public boolean a9;
    public boolean bb;
    public boolean fEmbed;
    public boolean fLogos;
    public boolean fLocations;
    public boolean fBuddies;
    public boolean fRight;
    public boolean fOne;
    public String be;
    public String bf;
    public String bg;
    public String bh;
    public String bi;
    public String bj;
    public URL bm;
    public URL urlHelp;
    public URL urlBrand;
    public URL urlShield;
    public URL bn;
    public URL bo;
    public int bp;
    public int bq;
    public int nSave;
    public int nLast;
    public int nState;
    public int nFrame;
    public int nCheck;
    public int nStyles;
    public int nRooms;
    public int nIMList;
    public int nGames;
    public int br;
    public int bs;
    public int bt;
    public int bu;
    public int bv;
    public int bw;
    public int bx;
    public int by;
    public int bz;
    public int b0;
    public int b1;
    public int b2;
    public int b3;
    public int b4;
    public int b5;
    public int nChars;
    public int nReplGuest;
    public int nSmileGuest;
    public int nReplSuper;
    public int nSmileSuper;
    public int nLife;
    public int nRepl;
    public int nSmile;
    public int nChatWidth;
    public int nChatHeight;
    public int nOfflineGuest;
    public int nOfflineMaster;
    public int nScrolls;
    public int nDelay;
    public int nStep;
    public int nInter;
    public boolean b6;
    public boolean b7;
    public boolean b8;
    public boolean b9;
    public boolean ca;
    public boolean cb;
    public a2 cc;
    public a2 a2Def;
    public Image imgShield;
    public Image imgAudio;
    public Image imgVideo;
    public WebIMList imlist;
    public Locale locale;
    public SimpleDateFormat format;
    public String strEntry;
    public String strVers;
    public String strHome;
    private bd bdTarget;
    protected int gamesHeight;
    protected GamesBox gamesBox;
    protected Scripts scripts;
    protected Random inpRandom;
    protected Random outRandom;
    protected Rectangle bounds;
    protected Object webIMWin;
    
    public boolean n() throws IOException {
        this.x.setSoTimeout(5000);
        this.inpCoded.setKey(com.diginet.digichat.client.i.stdKey);
        final v v = new v(this.r);
        if (v.b() == 67587) {
            this.m = v.b(0, 0);
            final long b = v.b(0, 2);
            this.lDelta = System.currentTimeMillis() - b;
            this.inpRandom = new Random(b);
            this.outRandom = new Random(b);
            this.x.setSoTimeout(0);
            final v v2 = new v(67587, 1);
            v2.a(0, 0, 288233675760336897L);
            this.an(v2);
            return true;
        }
        return false;
    }
    
    public String b(String s) {
        if (s != null && this.b7) {
            this.ag.a(false);
            try {
                synchronized (this.ag) {
                    for (int i = 0; i < this.ag.b(); ++i) {
                        s = ((be)this.ag.d(i)).a(s);
                    }
                }
                // monitorexit(this.ag)
            }
            finally {
                this.ag.a();
            }
            this.resWatch.a(false);
            try {
                synchronized (this.resWatch) {
                    for (int j = 0; j < this.resWatch.b(); ++j) {
                        s = ((be)this.resWatch.d(j)).a(s);
                    }
                }
                // monitorexit(this.resWatch)
            }
            finally {
                this.resWatch.a();
            }
            this.ah.a(false);
            try {
                synchronized (this.ah) {
                    for (int k = 0; k < this.ah.b(); ++k) {
                        s = ((be)this.ah.d(k)).a(s);
                    }
                }
                // monitorexit(this.ah)
            }
            finally {
                this.ah.a();
            }
        }
        return s;
    }
    
    protected abstract void a();
    
    protected int colorValue(final Color color) {
        return (color == null) ? 0 : (color.getRGB() | 0xFF000000);
    }
    
    protected Color colorValue(final int n) {
        return (n == 0) ? null : new Color(n & 0xFFFFFF);
    }
    
    public void a(final String a7, final String s, final String s2, final Color color, final Color color2, final boolean b) {
        this.a7 = a7;
        final v v = new v(67330, 1);
        v.k = -1;
        v.j = -1;
        v.l = this.w();
        v.a(0, 0, -999);
        v.a(0, 5, this.colorValue(color));
        v.a(0, 6, this.colorValue(color2));
        v.a(0, 0, a7);
        v.a(0, 1, s);
        v.a(0, 0, new e(s2));
        if (!b) {
            v.f(0, 58);
        }
        this.an(v);
    }
    
    public void o() {
        new c1(this.y.b(), this).setVisible(true);
    }
    
    public abstract void a(final v p0);
    
    public void f(final int n) {
        final bo bo = (bo)this.ac.e(n);
        if (bo != null) {
            this.a(bo);
        }
        else {
            System.err.println(String.valueOf("no such room! ").concat(String.valueOf(n)));
        }
    }
    
    public void a(final bo bo) {
        if (bo.b && bo.h != null) {
            new ba(this.y.b(), this, bo).setVisible(true);
        }
        if (!bo.b || bo.h == null) {
            final v v = new v(66049, 1);
            v.a(0, 0, this.w());
            v.a(0, 1, bo.w());
            v.k = -1;
            v.j = -1;
            this.an(v);
        }
    }
    
    public long p() {
        return this.k;
    }
    
    public int q() {
        return this.l;
    }
    
    public boolean isMaster() {
        return this.nBill != 2;
    }
    
    public boolean r() {
        return this.nRooms == 0;
    }
    
    public boolean k() {
        return this.fBuddies && (!this.i(109) || this.p);
    }
    
    public boolean isImTab() {
        return this.nIMList == 2 && com.diginet.digichat.network.v.a(this.lPlus, 21);
    }
    
    public boolean isImList() {
        return this.nIMList == 3 && com.diginet.digichat.network.v.a(this.lPlus, 21);
    }
    
    public boolean isGmTab() {
        return this.nGames == 2 && com.diginet.digichat.network.v.a(this.lPlus, 22);
    }
    
    public boolean s() {
        return this.o;
    }
    
    public boolean t() {
        return this.q;
    }
    
    public boolean isGmMenu() {
        return this.nGames == 3 && com.diginet.digichat.network.v.a(this.lPlus, 22);
    }
    
    public boolean isGmButton() {
        return this.nGames < 2 && com.diginet.digichat.network.v.a(this.lPlus, 22);
    }
    
    public boolean isElem(final int n) {
        switch (n) {
            case 1: {
                return this.i(43);
            }
            case 2: {
                return com.diginet.digichat.network.v.a(this.lPlus, 21);
            }
            case 5: {
                return this.k();
            }
            case 6: {
                return this.i(44);
            }
            case 7: {
                return this.isMaster() && ((this.yyy() & 0x1001FF0000L) != 0x0 || this.i(44) || this.i(49));
            }
            default: {
                return true;
            }
        }
    }
    
    public int g(final int n) {
        int n2 = 0;
        this.ab.a(false);
        try {
            synchronized (this.ab) {
                for (int i = 0; i < this.ab.b(); ++i) {
                    final bd bd = (bd)this.ab.d(i);
                    if (bd.b == n && (!bd.i(23) || this.i(24)) && !bd.i(79)) {
                        ++n2;
                    }
                }
            }
            // monitorexit(this.ab)
        }
        finally {
            this.ab.a();
        }
        return n2;
    }
    
    public abstract void saveState(final int p0);
    
    public boolean a(final Event event) {
        return false;
    }
    
    public URL getURL(final String s, final boolean b) throws MalformedURLException {
        return new URL(this.ax, String.valueOf("Resources/").concat(String.valueOf(b ? String.valueOf(String.valueOf(this.t).concat(String.valueOf("/"))).concat(String.valueOf(s)) : s)));
    }
    
    public Image a(final String s, final boolean b) {
        try {
            MediaTracker a0;
            if (this.a0 == null) {
                final Button button;
                a0 = new MediaTracker(button);
                button = new Button();
            }
            else {
                a0 = this.a0;
            }
            final MediaTracker mediaTracker = a0;
            Image a2 = this.a(this.getURL(s, b));
            if (a2 != null) {
                final int n = this.j++;
                mediaTracker.addImage(a2, n);
                try {
                    mediaTracker.waitForID(n);
                }
                catch (InterruptedException ex) {}
                if (mediaTracker.isErrorID(n)) {
                    a2 = null;
                }
            }
            return a2;
        }
        catch (MalformedURLException ex2) {
            return null;
        }
    }
    
    public abstract Image a(final String p0, final boolean p1, final int p2);
    
    public abstract Image a(final URL p0);
    
    public abstract Image loadImage(final String p0, final ImageObserver p1, final int p2);
    
    public abstract boolean checkIcon(final bp p0);
    
    public abstract bp getIcon(final int p0);
    
    public abstract bp getStar(final int p0);
    
    public abstract void a(final String p0, final String p1, final e p2, final String p3, final int p4) throws IOException;
    
    public void u() {
        this.h = true;
        if (this.s != null) {
            final v v = new v(65794, 1);
            v.a(0, 0, this.w());
            v.a(0, 0, this.bh);
            v.k = -1;
            v.j = -1;
            this.an(v);
            super.b = -999;
            if (this.y != null) {
                final Frame b = this.y.b();
                this.y.a();
                if (b != null) {
                    b.dispose();
                }
            }
        }
        this.ay = true;
        this.am(new v(66561, 0));
        this.a();
        this.f = false;
        this.g = false;
        if (this.bo != null) {
            this.a(this.bo, "_self");
        }
    }
    
    public void a(final bf bf, final bd bd) {
    }
    
    public void a(final bf bf) {
    }
    
    public void a(final byte[] array) {
    }
    
    public abstract void a(final URL p0, final String p1);
    
    public void a(final int n) {
    }
    
    protected void p(final v v) {
        for (int i = 0; i < v.c(); ++i) {
            final bd bd = (bd)this.ab.e(v.a(i, 0));
            if (bd != null) {
                this.a(bd, v.a(i, 1), v.i(), v.h());
            }
        }
    }
    
    protected abstract void a(final bd p0, final int p1, final long p2, final int p3);
    
    protected void ae(final v v) {
        if (this.bn != null) {
            this.a(this.bn, "_blank");
        }
        else {
            new a6(new Frame(), com.esial.util.c.a("You have been kicked"), v.c(0, 0), this).setVisible(true);
        }
        this.ay = true;
        this.am(new v(66561, 0));
        this.a();
    }
    
    protected void af(final v v) {
        final String[] array = new String[v.c()];
        for (int i = 0; i < array.length; ++i) {
            array[i] = v.c(i, 0);
        }
        new a6(this.a3, com.esial.util.c.a("Operation not allowed"), array, this).setVisible(true);
    }
    
    protected void k(final v v) {
        String s = null;
        final int a = v.a(0, 1);
        switch (v.a(0, 0)) {
            case 2: {
                s = com.diginet.digichat.util.a5.a(com.esial.util.c.a("You may not connect to %1 because your account has been disabled."), new String[] { DigiChatAppletAbstract.OEM_DigiChat });
                break;
            }
            case 4: {
                s = com.diginet.digichat.util.a5.a(com.esial.util.c.a("You may not connect to %1 because the maximum number of simultaneous connections for this site has been reached."), new String[] { DigiChatAppletAbstract.OEM_DigiChat });
                break;
            }
            case 3: {
                s = com.diginet.digichat.util.a5.a(com.esial.util.c.a("You may not connect to %1 because the version of the %1 Client (%2) is not compatible with the version of %1 Server (%3)."), new String[] { DigiChatAppletAbstract.OEM_DigiChat, com.diginet.digichat.common.d.a(), com.diginet.digichat.common.d.a(this.m) });
                break;
            }
            case 5: {
                s = com.diginet.digichat.util.a5.a(com.esial.util.c.a("You may not connect to %1 because you have been banned from this %1 site."), new String[] { DigiChatAppletAbstract.OEM_DigiChat });
                break;
            }
            case 6: {
                s = com.diginet.digichat.util.a5.a(com.esial.util.c.a("You may not connect to %1 because no site with the specified ID could be found."), new String[] { DigiChatAppletAbstract.OEM_DigiChat });
                break;
            }
            case 8: {
                s = com.diginet.digichat.util.a5.a(com.esial.util.c.a("You are attempting to connect to %1 from an invalid host. Please contact the %1 Site administrator."), new String[] { DigiChatAppletAbstract.OEM_DigiChat });
                break;
            }
            case 10: {
                s = com.diginet.digichat.util.a5.a(com.esial.util.c.a("You may not connect to %1 because the Server license has expired."), new String[] { DigiChatAppletAbstract.OEM_DigiChat });
                break;
            }
            case 9: {
                s = com.diginet.digichat.util.a5.a(com.esial.util.c.a("You may not connect to %1 because the Server license has been exceeded."), new String[] { DigiChatAppletAbstract.OEM_DigiChat });
                break;
            }
            case 0: {
                if (a == 65793) {
                    s = com.diginet.digichat.util.a5.a(com.esial.util.c.a("You may not connect to %1 because another user is already using the name \"%2.\" Please choose another and login again."), new String[] { DigiChatAppletAbstract.OEM_DigiChat, this.x() });
                }
                else {
                    s = com.diginet.digichat.util.a5.a(com.esial.util.c.a("Your nickname could not be changed because the name \"%1\" is already in use by another user, or is reserved."), new String[] { v.c(0, 0) });
                }
                break;
            }
            case 1:
            case 7: {
                if (a == 65793) {
                    s = com.diginet.digichat.util.a5.a(com.esial.util.c.a("You may not connect to %1 because your name or password was not entered correctly.  Please re-enter this information and login again."), new String[] { DigiChatAppletAbstract.OEM_DigiChat });
                    break;
                }
                break;
            }
        }
        if (s == null) {
            s = com.esial.util.c.a("Your request could not be fulfilled.");
        }
        if (a == 65793) {
            this.ay = true;
            this.am(new v(66561, 0));
            this.a();
        }
        new a6(this.a3, com.esial.util.c.a("Note"), s, this).setVisible(true);
    }
    
    protected void ag(final v v) {
        if (!this.ay) {
            new a6(this.a3, com.esial.util.c.a("Alert"), new String[] { v.c(0, 0), v.c(0, 1) }, this).setVisible(true);
        }
    }
    
    protected void ah(final v v) {
        for (int i = 0; i < v.c(); ++i) {
            if (!v.e(i, 17) || this.isMaster()) {
                final String c = v.c(i, 0);
                final String c2 = v.c(i, 1);
                if (v.e(i, 18) && this.y != null) {
                    final bf bf = new bf(c2, c, this.colorValue(v.a(i, 3)), 0, this.getIcon((v.a(i, 1) == this.nSite) ? v.a(i, 2) : this.a5), null, 0, false, false, v.b(i, 4));
                    bf.a(v.i(), v.h());
                    this.y.a(bf);
                }
                else {
                    new a6(this.a3, c, c2, this).setVisible(true);
                }
            }
        }
    }
    
    protected void ai(final v v) {
        new a6(new Frame(), com.esial.util.c.a("Inactivity Timeout"), com.diginet.digichat.util.a5.a(com.esial.util.c.a("You have been disconnected because you have been inactive for the past %1 minutes."), new String[] { String.valueOf(v.a(0, 0)) }), this).setVisible(true);
        this.ay = true;
        this.am(new v(66561, 0));
        this.a();
    }
    
    protected void aj(final v v) {
        final long n = v.e(-1, 59) ? (v.e(-1, 58) ? 216172782113783808L : 270215977642229760L) : 283726776524341248L;
        this.at = ((this.at & (n ^ 0L - 1L)) | (v.a(-1) & n));
        final boolean b7 = this.b7;
        this.b7 = ((this.at & this.at >> 2 & this.at >> 4 & 0x30000000000000L) == 0x0);
        this.ag.a(true);
        this.resWatch.a(true);
        this.ah.a(true);
        try {
            if (v.e(-1, 63)) {
                ((this.nSite == 0) ? ((this.i(126) || !v.e(-1, 58)) ? this.ag : this.ah) : (v.e(-1, 59) ? (v.e(-1, 58) ? this.ah : this.resWatch) : this.ag)).d();
            }
            else {
                for (int i = 0; i < v.c(); ++i) {
                    final int a = v.a(i, 0);
                    n ag;
                    final n n2 = (this.nSite == 0) ? (ag = ((this.i(126) || !v.e(i, 58)) ? this.ag : this.ah)) : (v.e(i, 59) ? (ag = (v.e(i, 58) ? this.ah : this.resWatch)) : (ag = this.ag));
                    final n n3 = ag;
                    be be = (be)n2.e(a);
                    if (v.e(i, 63)) {
                        if (be != null) {
                            n3.g(a);
                        }
                    }
                    else {
                        if (be == null) {
                            be = new be(a, v.c(i, 0));
                            n3.a(be);
                        }
                        else {
                            be.d(v.c(i, 0));
                        }
                        be.a = v.c(i, 1);
                        be.a(v.a(i));
                    }
                }
            }
        }
        finally {
            this.ag.a();
            this.resWatch.a();
            this.ah.a();
        }
        if (this.y != null && this.b7 != b7) {
            this.y.updateMess();
        }
    }
    
    protected void i(final v v) {
        this.u = v.c(0, 0);
    }
    
    protected abstract void callAdv(final bd p0);
    
    protected abstract void setRoom(final bd p0);
    
    protected abstract void closeAdv(final bd p0);
    
    protected void ak(final v v) {
        bo bo = null;
        k k = null;
        this.ac.a(true);
        try {
            for (int i = 0; i < v.c(); ++i) {
                final int a = v.a(i, 0);
                bo room = (bo)this.ac.e(a);
                if (v.e(i, 63) || v.c(i, 0) == null) {
                    if (room != null) {
                        this.ac.g(a);
                        if (this.y != null) {
                            this.y.deleteRoom(room);
                        }
                    }
                    if (a == super.b) {
                        k = room;
                    }
                }
                else {
                    if (room == null) {
                        String c = v.c(i, 0);
                        if (c == null) {
                            System.err.println("null room name received.");
                            c = new String("no name");
                        }
                        room = new bo(a, this.b(c));
                        this.ac.a(room);
                    }
                    else {
                        room.d(this.b(v.c(i, 0)));
                    }
                    room.d = v.a(i, 1);
                    room.e = v.a(i, 2);
                    room.f = v.a(i, 3);
                    room.c = v.a(i, 4);
                    room.clrName = this.colorValue(v.a(i, 5));
                    room.clrBack = this.colorValue(v.a(i, 6));
                    room.a = this.b(v.c(i, 1));
                    room.b = this.b(v.c(i, 2));
                    final e h = room.h;
                    room.h = v.d(i, 0);
                    if (room.h != null && !room.h.equals(h)) {
                        room.b = true;
                    }
                    room.a(v.a(i));
                    if (v.e(i, 62)) {
                        this.a4 = a;
                    }
                    if (this.y != null) {
                        this.y.setRoom(room);
                    }
                    if (this.a7 != null && room.x().equals(this.a7)) {
                        this.a7 = null;
                        bo = room;
                        bo.b = false;
                    }
                }
            }
            if (this.a4 == -999) {
                this.a4 = v.a(0, 0);
            }
        }
        finally {
            this.ac.a();
        }
        if (bo != null) {
            this.a(bo);
            this.y.a(true);
        }
        else if (k != null) {
            final bg bg = (bg)this.ac.e(this.a4);
            this.f(this.a4);
            new a6(this.a3, DigiChatAppletAbstract.OEM_DigiChat, com.diginet.digichat.util.a5.a(com.esial.util.c.a("The room you were in, \"%1,\" has closed.  You have been moved to \"%2.\""), new String[] { this.b(k.x()), this.b(bg.x()) }), this).setVisible(true);
        }
    }
    
    protected void al(final v v) {
        this.am.a(true);
        try {
            a2 cc = null;
            a2 cc2 = null;
            for (int i = 0; i < v.c(); ++i) {
                int n = v.a(i, 0);
                a2 a2 = (a2)this.am.e(n);
                if (v.e(i, 63)) {
                    this.am.g(n);
                }
                else {
                    if (a2 == null) {
                        a2 = new a2(n, v.c(i, 0));
                        n = this.am.a(a2, n);
                        v.a(i, 0, n);
                    }
                    else {
                        a2.d(v.c(i, 0));
                    }
                    a2.c = new Color(v.a(i, 1));
                    a2.d = new Color(v.a(i, 2));
                    a2.e = new Color(v.a(i, 3));
                    a2.f = new Color(v.a(i, 4));
                    a2.i = new Color(v.a(i, 5));
                    a2.j = new Color(v.a(i, 6));
                    a2.k = new Color(v.a(i, 7));
                    a2.l = new Color(v.a(i, 8));
                    a2.m = new Color(v.a(i, 9));
                    a2.n = new Color(v.a(i, 10));
                    a2.o = new Color(v.a(i, 11));
                    a2.p = v.c(i, 1);
                    a2.r = v.a(i, 12);
                    a2.q = v.a(i, 13);
                    a2.d();
                    a2.a(v.a(i));
                    a2.a(v.c(i, 2));
                    a2.v = v.a(i, 14);
                    a2.g = new Color(v.a(i, 15));
                    a2.h = new Color(v.a(i, 16));
                    a2.clrOutText = new Color(v.a(i, 17));
                    a2.clrInnText = new Color(v.a(i, 18));
                    a2.clrIMText = new Color(v.a(i, 19));
                    a2.clrIMBack = new Color(v.a(i, 20));
                    a2.clrSrchText = new Color(v.a(i, 21));
                    a2.clrSrchBack = new Color(v.a(i, 22));
                    a2.clrCtrlText = new Color(v.a(i, 23));
                    a2.clrCtrlBack = new Color(v.a(i, 24));
                    a2.clrScrlText = new Color(v.a(i, 25));
                    a2.clrScrlBack = new Color(v.a(i, 26));
                    a2.clrBaddyText = new Color(v.a(i, 27));
                    a2.clrBaddyBack = new Color(v.a(i, 28));
                    a2.clrInvText = new Color(v.a(i, 29));
                    a2.clrInvBack = new Color(v.a(i, 30));
                    a2.clrMenuText = new Color(v.a(i, 31));
                    a2.clrMenuBack = new Color(v.a(i, 32));
                    a2.clrHghlText = new Color(v.a(i, 33));
                    a2.clrHghlBack = new Color(v.a(i, 34));
                    a2.nMenuSize = v.a(i, 35);
                    a2.nMenuStyle = v.a(i, 36);
                    a2.strMenuFont = v.c(i, 3);
                    if (this.cc != null) {
                        a2.imgStates = this.cc.imgStates;
                    }
                    if (a2.i(62)) {
                        cc = (a2)a2.clone();
                    }
                    if (n == DigiChatAppletAbstract.preferredTheme) {
                        cc2 = (a2)a2.clone();
                    }
                }
            }
            if (cc2 != null) {
                this.cc = cc2;
            }
            else if (cc != null) {
                this.cc = cc;
            }
            else {
                this.cc = this.a2Def;
            }
            CommonStyle.fAnim = this.cc.getAnim();
            CommonStyle.fGrad = this.cc.getGrad();
            CommonStyle.clrCtrlText = this.cc.clrCtrlText;
            CommonStyle.clrCtrlBack = this.cc.clrCtrlBack;
            if (this.y != null) {
                this.y.d();
            }
            if (this.z != null) {
                for (int j = 0; j < this.z.b(); ++j) {
                    ((aa)this.z.d(j)).a();
                }
            }
        }
        finally {
            this.am.a();
        }
    }
    
    private String webIMURL(final String s) {
        try {
            return new URL(this.ax, String.valueOf("WebIM/").concat(String.valueOf(s))).toExternalForm();
        }
        catch (MalformedURLException ex) {
            return null;
        }
    }
    
    public Scripts getScripts() {
        if (this.scripts == null) {
            this.scripts = this.createScripts();
        }
        return this.scripts;
    }
    
    protected void updateIMList() {
        if (this.imlist == null) {
            switch (this.nIMList) {
                case 1: {
                    this.imlist = new WebIMBox(this);
                    break;
                }
            }
        }
        if (this.imlist != null) {
            this.imlist.updateList();
            if (this.imlist instanceof WebIMBox) {
                ((WebIMBox)this.imlist).show();
                this.getScripts().upWin(this.webIMWin);
            }
        }
    }
    
    protected void callWebIM(final bd bdTarget) {
        this.bdTarget = bdTarget;
        if (bdTarget == null) {
            final Enumeration elements = this.vecIMMess.elements();
            while (elements.hasMoreElements() && (this.bdTarget = (bd)this.aa.d((int)((Object[])elements.nextElement())[0])) == null) {}
        }
        if (this.getScripts().isWinClosed(this.webIMWin)) {
            if (this.bdTarget == null) {
                new a6(this.a3, com.esial.util.c.a("Note"), com.esial.util.c.a("You not have any message through 1:1.\nTo connect through 1:1 please select user in users list."), this).setVisible(true);
                return;
            }
            if ((this.webIMWin = this.getScripts().callWebIM(this.webIMURL("index.html"), this.w(), this.nIMList != 0)) == null) {
                new a6(this.a3, com.esial.util.c.a("Note"), com.esial.util.c.a("Cannot open 1:1 window."), this).setVisible(true);
                return;
            }
        }
        else if (this.bdTarget != null) {
            try {
                this.setIMValue("senderId", this.bdTarget.w());
                this.setIMValue("senderName", this.bdTarget.x());
                this.setIMValue("addUser", 1);
            }
            catch (NumberFormatException ex) {}
        }
        this.updateIMList();
    }
    
    public String getIMValue(final String s) {
        String s2 = null;
    Label_0382:
        for (int i = 0; i < com.diginet.digichat.client.i.strIMVars.length; ++i) {
            if (s.equals(com.diginet.digichat.client.i.strIMVars[i])) {
                switch (i) {
                    case 0: {
                        s2 = Integer.toString((this.nIMList != 0) ? 0 : 222);
                        break Label_0382;
                    }
                    case 1: {
                        s2 = com.diginet.digichat.util.a5.a(com.esial.util.c.a("%1 - 1:1"), new String[] { DigiChatAppletAbstract.OEM_DigiChat });
                        break Label_0382;
                    }
                    case 2: {
                        s2 = Integer.toString(this.w());
                        break Label_0382;
                    }
                    case 3: {
                        s2 = this.x();
                        break Label_0382;
                    }
                    case 4: {
                        s2 = Integer.toString(this.bdTarget.w());
                        break Label_0382;
                    }
                    case 5: {
                        s2 = this.bdTarget.x();
                        break Label_0382;
                    }
                    case 6: {
                        s2 = String.valueOf(String.valueOf(this.ax.getHost()).concat(String.valueOf("/messange/dcmessange"))).concat(String.valueOf(Integer.toString(this.nSite)));
                        break Label_0382;
                    }
                    case 7: {
                        s2 = DigiChatAppletAbstract.OEM_DigiChat;
                        break Label_0382;
                    }
                    case 8: {
                        s2 = String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(this.webIMURL("userslist.swf")).concat(String.valueOf("?fmsURL="))).concat(String.valueOf(this.ax.getHost()))).concat(String.valueOf("%2Flist%2Fdcmessange"))).concat(String.valueOf(Integer.toString(this.nSite)))).concat(String.valueOf("&userId="))).concat(String.valueOf(Integer.toString(this.w())))).concat(String.valueOf("&userName="))).concat(String.valueOf(this.x()))).concat(String.valueOf("&messCount="))).concat(String.valueOf(Integer.toString(this.vecIMMess.size())));
                        break Label_0382;
                    }
                    case 9: {
                        s2 = com.esial.util.c.a("Cannot call 1:1.\nProbably inadmissible type of browser.");
                        break Label_0382;
                    }
                }
            }
        }
        return s2;
    }
    
    public void addIMList() {
        try {
            this.getScripts().addIMList(this.webIMWin);
        }
        catch (Throwable t) {
            new a6(this.a3, com.esial.util.c.a("Note"), com.esial.util.c.a("Cannot create embedded users list."), this).setVisible(true);
        }
    }
    
    public void errorIMList() {
        this.nIMList = 3;
        this.removeIMList();
        new a6(this.a3, com.esial.util.c.a("Note"), com.esial.util.c.a("Cannot create embedded users list."), this).setVisible(true);
    }
    
    public void createWebIMPanel(final Object o) {
        try {
            final WebIMPanel imlist;
            ((Applet)o).add(imlist = new WebIMPanel(this));
            this.imlist = imlist;
            this.updateIMList();
        }
        catch (Throwable t) {
            this.errorIMList();
        }
    }
    
    protected void setIMIcons(final WebIMIcons webIMIcons, final boolean b, final boolean b2) {
        Image imgAudio;
        Image imgVideo = imgAudio = null;
        if (b && (imgAudio = this.imgAudio) == null) {
            final Image a = this.a("audioIcon.gif", false);
            this.imgAudio = a;
            imgAudio = a;
        }
        if (b2 && (imgVideo = this.imgVideo) == null) {
            final Image a2 = this.a("videoIcon.gif", false);
            this.imgVideo = a2;
            imgVideo = a2;
        }
        webIMIcons.setIcons(imgAudio, imgVideo);
    }
    
    public void setIMList(final String s) {
        this.webIMUsers.a(true);
        this.webIMUsers.d();
        final StringTokenizer stringTokenizer = new StringTokenizer(s, ";");
        while (stringTokenizer.hasMoreTokens()) {
            try {
                final StringTokenizer stringTokenizer2;
                final int int1;
                final bd bd;
                if ((bd = (bd)this.ab.e(int1 = Integer.parseInt((stringTokenizer2 = new StringTokenizer(stringTokenizer.nextToken(), ",")).nextToken()))) == null) {
                    continue;
                }
                final boolean booleanValue = new Boolean(stringTokenizer2.nextToken());
                final boolean booleanValue2 = new Boolean(stringTokenizer2.nextToken());
                final WebIMUser webIMUser;
                this.webIMUsers.a(webIMUser = new WebIMUser(bd), int1);
                this.setIMIcons(webIMUser, booleanValue2, booleanValue);
                if (int1 != this.w()) {
                    continue;
                }
                final v v = new v(67178253, 1);
                v.k = -1;
                v.j = -1;
                v.f(0, 14);
                v.a(0, 13, booleanValue2);
                v.a(0, 12, booleanValue);
                v.a(0, 0, int1);
                this.an(v);
            }
            catch (NumberFormatException ex) {}
            catch (NoSuchElementException ex2) {}
        }
        this.webIMUsers.a();
        this.updateIMList();
    }
    
    public void delIMUser(final String s) {
        try {
            if (this.webIMUsers.g(Integer.parseInt(s))) {
                this.updateIMList();
            }
        }
        catch (NumberFormatException ex) {}
    }
    
    public void addIMMess(final String s, final String s2, final String s3, final String s4, final String s5, final String s6) {
        final v v;
        (v = new v(67178252, 1)).j = -1;
        try {
            v.k = Integer.parseInt(s);
            v.a(0, 0, this.w());
            v.a(0, 1, Integer.parseInt(s3));
            v.a(0, 0, s2);
            v.a(0, 1, s4);
            v.a(0, 2, s5);
            v.a(0, 3, s6);
            this.an(v);
        }
        catch (NumberFormatException ex) {}
    }
    
    public void clearIMMess() {
        this.vecIMMess.removeAllElements();
    }
    
    private void setIMValue(final String s, final int n) {
        this.getScripts().setIMValue(this.webIMWin, s, n);
    }
    
    private void setIMValue(final String s, final String s2) {
        this.getScripts().setIMValue(this.webIMWin, s, s2);
    }
    
    public void getIMMess(final String s) {
        try {
            final int int1;
            final Object[] array;
            final Long n;
            final int intValue;
            final j j;
            if ((int1 = Integer.parseInt(s)) < this.vecIMMess.size() && (j = (j)this.ab.e(intValue = (int)(Object)(n = (Long)(array = (Object[])this.vecIMMess.elementAt(int1))[0]))) != null) {
                this.setIMValue("receiverId", this.w());
                this.setIMValue("senderId", intValue);
                this.setIMValue("senderName", j.x());
                this.setIMValue("sendTxtStyle", (int)(n >> 32));
                for (int i = 0; i < com.diginet.digichat.client.i.strIMMess.length; ++i) {
                    this.setIMValue(com.diginet.digichat.client.i.strIMMess[i], (String)array[i + 1]);
                }
                this.setIMValue("doneGet", 2);
                return;
            }
        }
        catch (NumberFormatException ex) {}
        this.setIMValue("doneGet", 1);
    }
    
    public void removeIMList() {
        try {
            this.getScripts().removeIMList(this.webIMWin);
            this.imlist = null;
        }
        catch (Throwable t) {
            new a6(this.a3, com.esial.util.c.a("Note"), com.esial.util.c.a("Cannot remove embedded users list."), this).setVisible(true);
        }
    }
    
    protected void closeIMList() {
        if (this.imlist != null) {
            if (this.imlist instanceof WebIMBox) {
                ((WebIMBox)this.imlist).dispose();
            }
            this.imlist = null;
        }
    }
    
    public void closeIMPage() {
        this.closeIMList();
        final v v = new v(67178253, 1);
        v.k = -1;
        v.j = -1;
        v.a(0, 0, this.w());
        this.an(v);
    }
    
    public void callGames() {
        if (this.fLogos) {
            this.games.a(true);
            try {
                for (int i = 0; i < this.games.b(); ++i) {
                    final Game game = (Game)this.games.d(i);
                    if (game.imgLogo == null) {
                        game.imgLogo = this.a(game.getLogo(), true);
                    }
                }
                this.fLogos = false;
            }
            finally {
                this.games.a();
            }
        }
        switch (this.nGames) {
            case 0: {
                try {
                    if (this.getScripts().callGames(new URL(this.ax, "Games/index.html").toExternalForm()) != null) {
                        break;
                    }
                }
                catch (MalformedURLException ex) {}
                new a6(this.a3, com.esial.util.c.a("Note"), com.esial.util.c.a("Cannot open games window."), this).setVisible(true);
                break;
            }
            case 1: {
                if (this.gamesBox == null) {
                    this.gamesBox = new GamesBox(this);
                }
                this.gamesBox.requestFocus();
                this.gamesBox.toFront();
                break;
            }
        }
    }
    
    public void addPlay(final Play play) {
        final Enumeration<Play> elements = this.vecPlays.elements();
        while (elements.hasMoreElements()) {
            final Play play2;
            if (this.getScripts().isWinClosed((play2 = elements.nextElement()).objTop)) {
                this.vecPlays.removeElement(play2);
            }
        }
        this.vecPlays.addElement(play);
    }
    
    protected void callGame(final Game game, final int n, final int n2) {
        final Enumeration<Play> elements = this.vecPlays.elements();
        while (elements.hasMoreElements()) {
            final Play play;
            if ((play = elements.nextElement()).nGame == game.w() && play.mInvites > 0 && !this.getScripts().isWinClosed(play.objTop)) {
                new InviteBox(play, this);
                return;
            }
        }
        try {
            final URL url = this.getURL(String.valueOf(String.valueOf("/Games/").concat(String.valueOf(game.strDir))).concat(String.valueOf("/index.html")), true);
            final Play callGame;
            if ((callGame = this.getScripts().callGame(url.toExternalForm(), game, n, n2)) == null) {
                DigiChatAppletAbstract.applet.getAppletContext().showDocument(url, game.x());
            }
            else {
                this.addPlay(callGame);
            }
        }
        catch (MalformedURLException ex) {}
    }
    
    public void callGame(final Game game) {
        this.callGame(game, this.w(), -1);
    }
    
    public void createGamesPanel(final Object o) {
        final Play setPanel;
        if ((setPanel = this.getScripts().setPanel(o, this)) == null) {
            new a6(this.a3, com.esial.util.c.a("Note"), com.esial.util.c.a("Cannot create embedded games switch."), this).setVisible(true);
        }
        else {
            this.addPlay(setPanel);
        }
    }
    
    protected void setGames(final v v) {
        this.games.a(true);
        try {
            this.fLogos = true;
            for (int i = 0; i < v.c(); ++i) {
                final int a = v.a(i, 0);
                Game game = (Game)this.games.e(a);
                if (v.e(i, 63)) {
                    this.games.g(a);
                    if (this.y != null) {
                        this.y.deleteGame(game);
                    }
                }
                else {
                    if (game == null) {
                        game = new Game(a, v.c(i, 0));
                        v.a(i, 0, this.games.a(game, a));
                    }
                    else {
                        game.d(v.c(i, 0));
                    }
                    game.nWidth = v.a(i, 1);
                    game.nHeight = v.a(i, 2);
                    game.strDir = v.c(i, 1);
                    game.strDesc = v.c(i, 2);
                    game.imgLogo = null;
                    game.a(v.a(i));
                }
                if (this.y != null) {
                    this.y.setGame(game);
                }
            }
        }
        finally {
            this.games.a();
        }
    }
    
    public void leaveOffline() {
        new LeaveBox(this);
    }
    
    protected void sendMove(final Play play, final v v) {
        final byte[] d;
        String s;
        if ((d = v.d()) == null) {
            s = null;
        }
        else {
            final char[] array = new char[d.length << 1];
            int n = 0;
            for (int i = 0; i < d.length; ++i) {
                array[n] = Character.forDigit(d[i] >> 4 & 0xF, 16);
                array[n + 1] = Character.forDigit(d[i] & 0xF, 16);
                n += 2;
            }
            s = new String(array);
        }
        this.getScripts().sendMove(play.objFrame, v.a(0, 0), v.a(0, 1), s);
    }
    
    protected void addPlayer(final Play play, final j j) {
        play.nPlayers[play.mPlayers++] = j.w();
        if (play.nState == 1) {
            this.getScripts().addPlayer(play.objFrame, j.x(), j.w() == this.w());
        }
    }
    
    protected void startPlay(final Play play, final int nLimit, final boolean b) {
        play.nLimit = nLimit;
        if (nLimit == play.mPlayers && play.nState == 1) {
            this.getScripts().startPlay(play.objFrame, b);
            while (!play.vecMoves.isEmpty()) {
                this.sendMove(play, play.vecMoves.elementAt(0));
                play.vecMoves.removeElementAt(0);
            }
            play.nState = 2;
        }
    }
    
    protected void sendJoin(final int k, final int n, final boolean b) {
        final v v = new v(67436803, 1);
        v.a(0, 63, b);
        v.a(0, 0, this.w());
        v.a(0, 1, n);
        v.k = k;
        v.j = -1;
        this.an(v);
    }
    
    protected static int getInt(final Object o) {
        return (int)((o instanceof Double) ? o : ((o instanceof Integer) ? o : -1));
    }
    
    public void initPlay(final String s, final String s2, final Object o, final Object o2) {
        try {
            final int int1 = Integer.parseInt(s.substring(4));
            final Enumeration<Play> elements = (Enumeration<Play>)this.vecPlays.elements();
            while (elements.hasMoreElements()) {
                final Play play;
                if ((play = elements.nextElement()).nPlay == int1) {
                    synchronized (play) {
                        this.getScripts().setFrame(play, s2);
                        play.nState = 1;
                        if (play.nPlayers == null) {
                            play.nPlayers = new int[getInt(o2) + 1];
                        }
                        else {
                            this.ab.a(false);
                            try {
                                for (int i = 0; i < play.mPlayers; ++i) {
                                    final j j;
                                    if ((j = (j)this.ab.e(play.nPlayers[i])) != null) {
                                        this.getScripts().addPlayer(play.objFrame, j.x(), j.w() == this.w());
                                    }
                                }
                            }
                            finally {
                                this.ab.a();
                            }
                        }
                        if (play.nOwner == this.w()) {
                            this.addPlayer(play, this);
                            play.nMin = getInt(o);
                            play.nMax = getInt(o2);
                            new InviteBox(play, this);
                        }
                        else {
                            this.sendJoin(play.nOwner, play.nId, false);
                        }
                        // monitorexit(play)
                        return;
                    }
                }
            }
        }
        catch (Throwable t) {}
        new a6(this.a3, com.esial.util.c.a("Note"), com.esial.util.c.a("Invalid invite call."), this).setVisible(true);
    }
    
    public void sendMove(final String s, final Object o, final Object o2, final String s2) {
        final int int1 = Integer.parseInt(s.substring(4));
        final Enumeration<Play> elements = (Enumeration<Play>)this.vecPlays.elements();
        while (elements.hasMoreElements()) {
            final Play play;
            if ((play = elements.nextElement()).nPlay == int1) {
                final int int2 = getInt(o2);
                final int int3;
                v v;
                if ((int3 = getInt(o)) < 0) {
                    v = new v(67436808, play.mPlayers - 1);
                    v.k = -1;
                    int i = 0;
                    int n = 0;
                    while (i < play.mPlayers) {
                        if (play.nPlayers[i] != this.w()) {
                            v.a(n, (long)int2);
                            v.a(n++, 0, play.nPlayers[i]);
                        }
                        ++i;
                    }
                }
                else {
                    v = new v(67436809, 1);
                    v.k = play.nPlayers[int3];
                    v.a(0, 0, this.w());
                    v.a(0, 1, int2);
                }
                v.a(v.j = -1, play.nOwner << 32 | play.nId);
                if (s2 != null) {
                    final byte[] array = new byte[s2.length() >> 1];
                    int n2 = 0;
                    for (int j = 0; j < array.length; ++j) {
                        array[j] = (byte)Integer.parseInt(s2.substring(n2, n2 + 2), 16);
                        n2 += 2;
                    }
                    v.a(array);
                }
                this.an(v);
                break;
            }
        }
    }
    
    protected void closePlay(final Play play) {
        final int n;
        if ((n = play.mPlayers + play.mInvites - 1) > 0) {
            final v v = new v(67436801, n);
            int n2 = 0;
            final v v2 = v;
            final v v3 = v;
            final int n3 = -1;
            v3.j = n3;
            v2.k = n3;
            v.a(-1, play.nPlay << 32 | play.nGame);
            for (int i = 0; i < play.mPlayers; ++i) {
                final int n4;
                if ((n4 = play.nPlayers[i]) != this.w()) {
                    v.a(n2, 0, n4);
                    v.f(n2++, 63);
                }
            }
            for (int j = 0; j < play.mInvites; ++j) {
                v.a(n2, 0, play.nInvites[j]);
                v.f(n2++, 63);
            }
            this.an(v);
        }
        this.vecPlays.removeElement(play);
    }
    
    public void closePlay(final String s) {
        try {
            final int int1 = Integer.parseInt(s.substring(4));
            final Enumeration<Play> elements = (Enumeration<Play>)this.vecPlays.elements();
            while (elements.hasMoreElements()) {
                final Play play;
                if ((play = elements.nextElement()).nPlay == int1) {
                    synchronized (play) {
                        v v;
                        if (play.nState == 2) {
                            v = new v(67436810, play.mPlayers - 1);
                            v.k = -1;
                            int i = 0;
                            int n = 0;
                            while (i < play.mPlayers) {
                                if (play.nPlayers[i] != this.w()) {
                                    v.a(n++, 0, play.nPlayers[i]);
                                }
                                ++i;
                            }
                        }
                        else {
                            if (play.nOwner == this.w()) {
                                this.closePlay(play);
                                // monitorexit(play)
                                return;
                            }
                            v = new v(67436811, 1);
                            v.k = play.nOwner;
                            v.a(0, 0, this.w());
                        }
                        v.a(-1, play.nOwner << 32 | play.nId);
                        v.j = -1;
                        this.an(v);
                    }
                    // monitorexit(play)
                }
            }
        }
        catch (Throwable t) {}
    }
    
    public abstract Object getUpload(final Object p0);
    
    public synchronized void am(final v v) {
        this.a2.a(v);
        this.notify();
        this.notify();
    }
    
    public void i() {
        this.u();
        try {
            this.c();
        }
        catch (IOException ex) {}
    }
    
    public abstract void j();
    
    public void run() {
        this.a2.c();
        this.v.start();
        try {
            while (!this.ay || !this.a2.a()) {
                while (this.a2.a() && !this.ay) {
                    synchronized (this) {
                        this.wait();
                    }
                }
                this.a((v)this.a2.b());
            }
        }
        catch (Exception ex) {
            if (!this.ay) {
                this.ay = true;
                this.a();
            }
        }
    }
    
    public void c() throws IOException {
    }
    
    public void an(final v v) {
        try {
            final byte[] key = new byte[8];
            this.outRandom.nextBytes(key);
            this.outCoded.setKey(key);
            v.a(this.s);
            this.s.flush();
        }
        catch (IOException ex) {
            if (!this.ay) {
                this.a();
            }
        }
    }
    
    public void a(final String s, final int k, final int j, final long n) {
        final v v = new v(66305, 1);
        v.a(0, 0, s);
        v.a(0, 0, this.w());
        v.a(0, 2, n);
        v.k = k;
        v.j = j;
        this.an(v);
    }
    
    public void sendMess(final String s, final Buddy buddy, final long n, final boolean b) {
        final bd bd;
        if (!b && this.y != null && (bd = (bd)this.ab.e(this.w())) != null) {
            ((a7)this.y).addMess(buddy.x(), buddy.locId(), bd, new String[] { s }, new long[] { n });
        }
        final v v = new v(67371270, 2);
        v.a(0, 62, b);
        v.a(0, 0, buddy.locId());
        v.a(0, 0, buddy.x());
        v.a(1, 0, n);
        v.a(1, 0, s);
        this.an(v);
    }
    
    public void c(final String s, final long n) {
        this.a(s, -1, super.b, n);
        this.k = System.currentTimeMillis();
    }
    
    public abstract void a(final bf p0, final j p1);
    
    public void a(final Font font) {
        this.cc.p = font.getName();
        this.cc.r = font.getStyle();
        this.cc.q = font.getSize();
        this.cc.d();
        if (this.y != null) {
            this.y.d();
        }
        if (this.z != null) {
            for (int i = 0; i < this.z.b(); ++i) {
                ((aa)this.z.d(i)).a();
            }
        }
    }
    
    public abstract void setProf(final bd p0);
    
    public void updateList(final bd prof, final boolean b) {
        this.setProf(prof);
        this.y.updateList(prof, b);
    }
    
    public void c(final boolean az) {
        this.az = az;
        this.ab.a(false);
        try {
            synchronized (this.ab) {
                for (int i = 0; i < this.ab.b(); ++i) {
                    final bd bd = (bd)this.ab.d(i);
                    if (this.az || bd.b == super.b) {
                        this.updateList(bd, true);
                    }
                    else {
                        this.y.a(bd);
                    }
                }
            }
            // monitorexit(this.ab)
        }
        finally {
            this.ab.a();
        }
    }
    
    public void b(final boolean b) {
    }
    
    public void d(final boolean b) {
        if (!b) {
            this.nRooms = 2;
        }
        else if (this.nRooms == 2) {
            this.nRooms = 0;
        }
    }
    
    public void e(final boolean o) {
        this.o = o;
    }
    
    public void setSupp(final boolean q) {
        this.q = q;
    }
    
    public abstract void createOptions(final MenuPanel p0);
    
    public void c(final int n) {
    }
    
    public void v(final String s) {
        final String a = com.diginet.digichat.util.a5.a(com.esial.util.c.a("You have been disconnected from %1 for flooding."), new String[] { DigiChatAppletAbstract.OEM_DigiChat });
        final v v = new v(50400771, 1);
        v.k = this.w();
        v.a(0, this.as);
        v.a(0, 16, false);
        v.a(0, 17, true);
        v.a(0, 0, String.valueOf(s).concat(String.valueOf(a)));
        this.an(v);
        this.an(new v(67840, 0));
    }
    
    public abstract void a(final j p0);
    
    public abstract void a(final int p0, final j p1);
    
    protected abstract Scripts createScripts();
    
    public i() {
        super(-999, null);
        this.lChange = new long[2];
        this.e = false;
        this.f = false;
        this.g = false;
        this.h = false;
        this.i = false;
        final boolean fBrand = false;
        this.fIcon = fBrand;
        this.fTheme = fBrand;
        this.fBrand = fBrand;
        this.j = 1000;
        this.k = 0L;
        this.l = 250;
        this.o = true;
        this.p = false;
        this.q = false;
        this.x = null;
        this.z = new n();
        this.aa = new n();
        this.stars = new n();
        this.ab = new n();
        this.ac = new n();
        this.ad = new n();
        this.scrolls = new n();
        this.ae = new n();
        this.af = new n();
        this.ag = new n();
        this.resWatch = new n();
        this.ah = new n();
        this.ai = new n();
        this.resellers = new n();
        this.aj = new n();
        this.ak = new n();
        this.al = new n();
        this.am = new n();
        this.games = new n();
        this.locations = new n();
        this.an = new n();
        this.ao = new n();
        this.webIMUsers = new n();
        this.recips = new n();
        this.offlines = new n();
        this.smiles = new SmilesArray();
        this.ap = new Vector();
        this.aq = new Vector();
        this.ar = new Vector();
        this.vecIMMess = new Vector();
        this.vecPlays = new Vector();
        final ImagesLoader iconsLoader = null;
        this.starsLoader = iconsLoader;
        this.smilesLoader = iconsLoader;
        this.iconsLoader = iconsLoader;
        this.at = 0L;
        this.ay = true;
        this.az = false;
        this.a2 = new o();
        this.a3 = null;
        this.a4 = -999;
        this.a5 = -999;
        this.a6 = -999;
        this.a8 = false;
        this.a9 = true;
        this.bb = false;
        final boolean checkEmbed = Scripts.checkEmbed();
        this.fEmbed = checkEmbed;
        final boolean b = !checkEmbed;
        this.nGames = (b ? 1 : 0);
        this.nIMList = (b ? 1 : 0);
        final boolean b2 = false;
        this.nRooms = (b2 ? 1 : 0);
        this.nStyles = (b2 ? 1 : 0);
        this.bp = -999;
        this.bq = 2;
        final boolean nSave = false;
        this.nCheck = (nSave ? 1 : 0);
        this.nFrame = (nSave ? 1 : 0);
        this.nState = (nSave ? 1 : 0);
        this.nLast = (nSave ? 1 : 0);
        this.nSave = (nSave ? 1 : 0);
        this.br = -999;
        this.bs = -999;
        this.bt = -999;
        this.bu = 0;
        this.bv = 8;
        this.bw = 3;
        this.bx = 4;
        this.by = 9;
        this.bz = 1;
        this.b0 = 0;
        this.b1 = 7;
        this.b2 = 2;
        this.b3 = 0;
        this.b4 = 0;
        this.b5 = 0;
        this.gamesHeight = 0;
        this.b6 = false;
        this.b7 = false;
        this.b8 = false;
        this.b9 = false;
        this.ca = false;
        this.cb = false;
        this.strEntry = "";
        final String s = null;
        this.strHome = s;
        this.strVers = s;
        this.nScrolls = 0;
        this.nDelay = 50;
        this.nStep = 2;
        this.nOfflineGuest = 50;
        this.nOfflineMaster = 100;
        this.nInter = 100;
        this.lScrolls = 1126329403703346L;
        this.lElems = -1L;
        final boolean b3 = true;
        this.fOne = b3;
        this.fRight = b3;
        final boolean fShorts = true;
        this.fLogos = fShorts;
        this.fSmiles = fShorts;
        this.fShorts = fShorts;
        final boolean b4 = false;
        this.fBuddies = b4;
        this.fLocations = b4;
        final a2 a2 = new a2(com.diginet.digichat.common.a2.a.w(), com.diginet.digichat.common.a2.a.x());
        this.a2Def = a2;
        this.cc = a2;
        final Image image = null;
        this.imgVideo = image;
        this.imgAudio = image;
        this.imlist = null;
        this.format = null;
        this.scripts = null;
        this.bdTarget = null;
        this.gamesBox = null;
        this.webIMWin = null;
        this.palettes = new Vector[8];
        for (int i = 0; i < this.palettes.length; ++i) {
            this.palettes[i] = null;
        }
    }
    
    static {
        stdKey = new byte[] { 1, 2, 3, 4, 5, 6, 7, 8 };
        a = new String[] { null, null, null, null, null, null, null, null, null };
        b = new String[] { "bass.au", "bell.au", "castanet.au", "chime.au", "conga.au", "cowBell.au", "doubleBell.au", "drumRoll.au", "harp.au" };
        strIMMess = new String[] { "sendTime", "stringColor", "messType", "sendTxt" };
        strIMVars = new String[] { "addition", "title", "userid", "username", "otherid", "othername", "chatrtmp", "copyright", "listmovie", "error" };
    }
}

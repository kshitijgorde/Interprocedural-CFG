// 
// Decompiled by Procyon v0.5.30
// 

package com.diginet.digichat.client;

import com.diginet.digichat.common.Recip;
import com.diginet.digichat.common.Game;
import java.awt.Component;
import java.awt.Point;
import java.awt.Rectangle;
import com.diginet.digichat.awt.LayeredBox;
import com.diginet.digichat.awt.ScrollItem;
import java.util.Date;
import com.diginet.digichat.common.bg;
import java.awt.Dimension;
import com.diginet.digichat.util.MD5;
import com.diginet.digichat.util.n;
import com.diginet.digichat.util.ImagesLoader;
import java.util.Vector;
import com.diginet.digichat.util.l;
import com.diginet.digichat.common.bn;
import com.diginet.digichat.common.k;
import com.diginet.digichat.common.d;
import com.diginet.digichat.awt.LayeredContainer;
import java.awt.Color;
import com.diginet.digichat.awt.CommonStyle;
import java.util.TimeZone;
import java.util.SimpleTimeZone;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.StringTokenizer;
import com.diginet.digichat.common.b1;
import com.diginet.digichat.common.bp;
import com.diginet.digichat.common.j;
import java.net.NoRouteToHostException;
import java.io.InterruptedIOException;
import java.net.UnknownHostException;
import com.diginet.digichat.awt.a6;
import com.diginet.digichat.util.a5;
import com.esial.util.c;
import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.OutputStream;
import java.io.BufferedOutputStream;
import java.net.Socket;
import com.diginet.digichat.util.tunnel3.b3;
import com.diginet.digichat.common.p;
import com.diginet.digichat.util.dx;
import java.io.IOException;
import com.diginet.digichat.common.e;
import java.io.InputStream;
import java.awt.Toolkit;
import java.io.ByteArrayOutputStream;
import java.awt.image.ImageObserver;
import java.net.MalformedURLException;
import java.awt.Image;
import java.applet.AudioClip;
import java.net.URL;
import com.diginet.digichat.network.v;
import java.awt.Frame;
import java.util.Enumeration;
import java.awt.Dialog;
import java.util.Hashtable;
import com.diginet.digichat.awt.DragListener;
import com.diginet.digichat.awt.BoundsListener;
import com.diginet.digichat.util.ae;

public abstract class h extends i implements ae, BoundsListener, DragListener
{
    private static final String strSave = "Save";
    private static final String strSuper = "super";
    private static final String strNick = "Nick";
    private static final String strName = "Name";
    private static final String strBack = "Back";
    private static final String strPow1 = "Pow1";
    private static final String strPow2 = "Pow2";
    private static final String strIcon = "Icon";
    private static final String strStar = "Star";
    private static final String strKeep = "Keep";
    private static final String strUser = "user";
    private static final String strScale = "Scale";
    private static final String strState = "State";
    private static final String strLeft = "Left";
    private static final String strTop = "Top";
    private static final String strRooms = "Rooms";
    private static final String strList = "List";
    private static final String strGames = "Games";
    private static final String strCheck = "Check";
    private static final String strSpeed = "Speed";
    private static final String strStyles = "Styles";
    private static final String strElems = "Elems";
    private static final String strNormal = "Normal";
    private static final String strFlagBack = "FlagBack";
    private static final String strPrivMess = "PrivMess";
    private static final String strPrivBack = "PrivBack";
    private static final String strStyle = "Style";
    private static final String strSize = "Size";
    private static final String strNew = "New";
    private static final String strMess = "Mess";
    private static final String strFlag = "Flag";
    private static final String strPriv = "Priv";
    private static final String strExit = "Exit";
    private static final String strNameColor = "NameColor";
    private static final String strBackColor = "BackColor";
    private static final String strX = "X";
    private static final String strY = "Y";
    private static final String strWidth = "Width";
    private static final String strHeight = "Height";
    private static final String strYes;
    private boolean fYes;
    private ac a;
    private Hashtable c;
    private Hashtable hshUser;
    private Hashtable hshFlags;
    
    private void closeWin(final Object o) {
        if (!this.getScripts().isWinClosed(o)) {
            this.getScripts().closeWin(o);
        }
    }
    
    protected void a() {
        super.aa.d();
        super.stars.d();
        super.ab.d();
        super.ac.d();
        super.ad.d();
        super.ae.d();
        super.af.d();
        super.ag.d();
        super.ai.d();
        super.resellers.d();
        super.aj.d();
        super.ak.d();
        if (super.z != null) {
            for (int i = 0; i < super.z.b(); ++i) {
                ((aa)super.z.d(i)).dispose();
            }
        }
        if (super.y != null) {
            ((a7)super.y).closeBuddies();
        }
        super.locations.d();
        final Enumeration<Dialog> elements = this.c.elements();
        while (elements.hasMoreElements()) {
            final Dialog nextElement = elements.nextElement();
            if (nextElement instanceof Dialog) {
                nextElement.dispose();
            }
        }
        this.closeWin(super.webIMWin);
        final Enumeration<Play> elements2 = super.vecPlays.elements();
        while (elements2.hasMoreElements()) {
            this.getScripts().closeWin(elements2.nextElement().objTop);
        }
        this.j();
        if (super.y != null) {
            final Frame b = super.y.b();
            super.y.a();
            super.y = null;
            if (this.a != null) {
                this.a.setVisible(false);
            }
            this.a = null;
            if (b != null) {
                b.dispose();
            }
        }
        try {
            Thread.sleep(500L);
        }
        catch (InterruptedException ex) {}
        if (super.x != null) {
            try {
                super.r.close();
                super.s.close();
                super.x.close();
            }
            catch (Exception ex2) {}
        }
        super.x = null;
        super.s = null;
        super.r = null;
        super.ay = true;
    }
    
    public void a(final v recips) {
        switch (recips.b()) {
            case 67585: {
                this.k(recips);
                break;
            }
            case 65794: {
                this.q(recips);
                break;
            }
            case 66049: {
                this.p(recips);
                break;
            }
            case 66305: {
                this.u(recips);
                break;
            }
            case 66308: {
                this.r(recips);
                break;
            }
            case 66306: {
                this.t(recips);
                break;
            }
            case 66307: {
                this.ah(recips);
                break;
            }
            case 66561: {
                this.ag(recips);
                break;
            }
            case 66816:
            case 50400771: {
                this.ae(recips);
                break;
            }
            case 66817: {
                this.ai(recips);
                break;
            }
            case 67073: {
                this.v(recips);
                break;
            }
            case 67074: {
                this.s(recips);
                break;
            }
            case 67329: {
                this.w(recips);
                break;
            }
            case 67330: {
                this.ak(recips);
                break;
            }
            case 67331: {
                this.y(recips);
                break;
            }
            case 67333: {
                this.aj(recips);
                break;
            }
            case 67334: {
                this.aa(recips);
                break;
            }
            case 67584: {
                this.m(recips);
                break;
            }
            case 67586: {
                this.n(recips);
                break;
            }
            case 67843: {
                this.z(recips);
                break;
            }
            case 68608: {
                this.af(recips);
                break;
            }
            case 67338: {
                this.i(recips);
                break;
            }
            case 67341: {
                this.al(recips);
                break;
            }
            case 33621775: {
                this.x(recips);
                break;
            }
            case 67178241: {
                this.changeUser(recips);
                break;
            }
            case 67178243: {
                this.warningUser(recips);
                break;
            }
            case 67178244: {
                this.setState(recips);
                break;
            }
            case 67178245: {
                this.setPalette(recips);
                break;
            }
            case 67178246: {
                this.setScrolls(recips);
                break;
            }
            case 67178248: {
                this.setStars(recips);
                break;
            }
            case 67178249: {
                this.setOpt2(recips);
            }
            case 67178250: {
                this.setPlus(recips);
            }
            case 67178251: {
                this.setGames(recips);
            }
            case 67178252: {
                this.addIMMess(recips);
            }
            case 67178253: {
                this.setIMUser(recips);
            }
            case 67178254: {
                this.setAppear(recips);
            }
            case 67371265: {
                this.setLocations(recips);
            }
            case 67371268: {
                this.setParams(recips);
            }
            case 67371269: {
                this.setProf(recips);
            }
            case 67371270: {
                this.addMess(recips);
            }
            case 67436802: {
                this.addInvite(recips);
            }
            case 67436803: {
                this.setJoin(recips);
            }
            case 67436805: {
                this.setPlayers(recips);
            }
            case 67436807: {
                this.startPlay(recips);
            }
            case 67436809: {
                this.sendMove(recips);
            }
            case 67436811: {
                this.closePlay(recips);
            }
            case 67502337: {
                this.setRecips(recips);
                break;
            }
        }
    }
    
    public abstract AudioClip b(final URL p0);
    
    public abstract Image a(final URL p0);
    
    private Image a(final String s, final boolean b, final int n, final boolean b2, final boolean b3) {
        try {
            final Image a = this.a(this.getURL(s, b));
            if (a != null) {
                super.a0.addImage(a, b2 ? n : super.j);
                if (b3) {
                    try {
                        super.a0.waitForID(b2 ? n : super.j);
                    }
                    catch (InterruptedException ex) {}
                }
                super.a0.statusID(b2 ? n : super.j, true);
                if (!b2) {
                    ++super.j;
                }
            }
            return a;
        }
        catch (MalformedURLException ex2) {
            return null;
        }
    }
    
    public Image a(final String s, final boolean b, final int n, final boolean b2) {
        return this.a(s, b, n, b2, false);
    }
    
    public Image a(final String s, final boolean b, final int n) {
        return this.a(s, b, n, true);
    }
    
    public Image a(final String s, final int n) {
        return this.a(s, true, n, true, true);
    }
    
    public Image loadImage(final String s, final ImageObserver imageObserver, final int n) {
        final byte[] array = new byte[256];
        try {
            final InputStream openStream = this.getURL(s, true).openStream();
            final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            int read;
            while ((read = openStream.read(array)) >= 0) {
                byteArrayOutputStream.write(array, 0, read);
            }
            openStream.close();
            byteArrayOutputStream.close();
            final Image image;
            if ((image = Toolkit.getDefaultToolkit().createImage(byteArrayOutputStream.toByteArray())) != null) {
                super.a0.addImage(image, n);
                try {
                    super.a0.waitForID(n);
                }
                catch (InterruptedException ex) {}
                super.f = super.a0.isErrorID(n);
                super.a0.removeImage(image);
                return (super.f || image.getWidth(imageObserver) <= 0 || image.getHeight(imageObserver) <= 0) ? null : image;
            }
        }
        catch (Exception ex2) {}
        return null;
    }
    
    public void l() {
    }
    
    public AudioClip[] c(final URL url) throws MalformedURLException {
        final AudioClip[] array = new AudioClip[com.diginet.digichat.client.i.a.length];
        for (int i = 0; i < array.length; ++i) {
            array[i] = this.b(new URL(url, String.valueOf("Sounds/").concat(String.valueOf(com.diginet.digichat.client.i.b[i]))));
        }
        return array;
    }
    
    public abstract void a(final String p0, final String p1, final e p2, final String p3, final int p4) throws IOException;
    
    public void a(final String strEntry, final String s, final e e, final URL ax, final int n, final String s2) throws IOException {
        try {
            this.c();
            super.az = false;
            super.ag.d();
            String s3;
            if (DigiChatAppletAbstract.altHost != null) {
                s3 = DigiChatAppletAbstract.altHost;
            }
            else {
                s3 = ax.getHost();
            }
            super.ax = ax;
            this.d(super.strEntry = strEntry);
            Label_0098: {
                if (super.a1 == null) {
                    if (dx.c < 66048) {
                        if (dx.f) {
                            break Label_0098;
                        }
                    }
                    try {
                        super.a1 = this.c(super.ax);
                    }
                    catch (Throwable t) {
                        super.a1 = null;
                    }
                }
            }
            int n2;
            if (dx.e && dx.c < 65792 && dx.b == 1) {
                n2 = 2;
            }
            else {
                n2 = 256;
            }
            boolean b = false;
            if (DigiChatAppletAbstract.preferredPort != 0) {
                try {
                    if (DigiChatAppletAbstract.preferredPort == com.diginet.digichat.common.p.c) {
                        if (com.diginet.digichat.common.p.d == "") {
                            throw new Exception("HttpServlet address not specified");
                        }
                        super.x = new b3(com.diginet.digichat.common.p.d);
                    }
                    else {
                        super.x = new Socket(s3, DigiChatAppletAbstract.preferredPort);
                    }
                    final CodedOutputStream outCoded = new CodedOutputStream(new BufferedOutputStream(super.x.getOutputStream(), 256));
                    super.outCoded = outCoded;
                    super.s = new DataOutputStream(outCoded);
                    if (DigiChatAppletAbstract.preferredPort == com.diginet.digichat.common.p.c) {
                        final CodedInputStream inpCoded = new CodedInputStream(super.x.getInputStream());
                        super.inpCoded = inpCoded;
                        super.r = new DataInputStream(inpCoded);
                    }
                    else {
                        final CodedInputStream inpCoded2 = new CodedInputStream(new BufferedInputStream(super.x.getInputStream(), n2));
                        super.inpCoded = inpCoded2;
                        super.r = new DataInputStream(inpCoded2);
                    }
                    b = this.n();
                }
                catch (Exception ex8) {}
            }
            if (!b) {
                for (int n3 = 0; !b && n3 < com.diginet.digichat.common.p.a.size(); ++n3) {
                    final int a = com.diginet.digichat.common.p.a(n3);
                    try {
                        if (a != DigiChatAppletAbstract.preferredPort) {
                            if (a == com.diginet.digichat.common.p.c) {
                                if (com.diginet.digichat.common.p.d == "") {
                                    throw new Exception("HttpServlet address not specified");
                                }
                                super.x = new b3(com.diginet.digichat.common.p.d);
                            }
                            else {
                                super.x = new Socket(s3, a);
                            }
                            final CodedOutputStream outCoded2 = new CodedOutputStream(new BufferedOutputStream(super.x.getOutputStream(), 256));
                            super.outCoded = outCoded2;
                            super.s = new DataOutputStream(outCoded2);
                            if (com.diginet.digichat.common.p.a(n3) == com.diginet.digichat.common.p.c) {
                                final CodedInputStream inpCoded3 = new CodedInputStream(super.x.getInputStream());
                                super.inpCoded = inpCoded3;
                                super.r = new DataInputStream(inpCoded3);
                            }
                            else {
                                final CodedInputStream inpCoded4 = new CodedInputStream(new BufferedInputStream(super.x.getInputStream(), n2));
                                super.inpCoded = inpCoded4;
                                super.r = new DataInputStream(inpCoded4);
                            }
                            b = this.n();
                        }
                    }
                    catch (Exception ex) {
                        if (n3 == com.diginet.digichat.common.p.a.size() - 1) {
                            throw ex;
                        }
                    }
                }
            }
            if (b) {
                if (super.v != null && super.v.isAlive()) {
                    super.v.suspend();
                }
                if (super.w != null && super.w.isAlive()) {
                    super.w.suspend();
                }
                super.v = new b8(this);
                super.w = new Thread(this, "Decoder");
                super.ay = false;
                super.w.start();
                final v v = new v(65793, 1);
                v.a(0, 0, n);
                v.a(0, 1, super.a6);
                v.a(0, 0, strEntry);
                v.a(0, 1, "en");
                v.a(0, 2, s2);
                v.a(0, 3, s);
                v.a(0, 0, e);
                if (!this.r() && super.a6 != -999) {
                    v.f(0, 2);
                }
                if (this.i(23)) {
                    v.f(0, 23);
                }
                if (this.i(79)) {
                    v.f(0, 25);
                }
                if (this.t()) {
                    v.f(0, 3);
                }
                if (super.i && (!dx.e || dx.b != 1)) {
                    v.f(0, 21);
                    v.f(0, 20);
                }
                this.an(v);
            }
            else {
                System.err.println("acknowledge() failed!!!");
            }
        }
        catch (UnknownHostException ex2) {
            this.j();
            new a6(super.a3, com.diginet.digichat.util.a5.a(com.esial.util.c.a("%1 could not locate the specified host. Please make sure you are using the correct host name."), new String[] { DigiChatAppletAbstract.OEM_DigiChat }), ex2, this).setVisible(true);
        }
        catch (InterruptedIOException ex3) {
            this.j();
            new a6(super.a3, com.diginet.digichat.util.a5.a(com.esial.util.c.a("No response was received from the server.  The %1 Server may not be running, or may not be a compatible version. Please contact the administrator in charge of the site."), new String[] { DigiChatAppletAbstract.OEM_DigiChat }), ex3, this).setVisible(true);
        }
        catch (NoRouteToHostException ex4) {
            this.j();
            new a6(super.a3, com.diginet.digichat.util.a5.a(com.esial.util.c.a("%1 could not connect to the server. If you are connecting through a firewall or proxy server, you may not be able to use %1."), new String[] { DigiChatAppletAbstract.OEM_DigiChat }), ex4, this).setVisible(true);
        }
        catch (SecurityException ex5) {
            this.j();
            new a6(super.a3, com.diginet.digichat.util.a5.a(com.esial.util.c.a("%1 could not connect to the server. If you are connecting through a firewall or proxy server, you may not be able to use %1."), new String[] { DigiChatAppletAbstract.OEM_DigiChat }), ex5, this).setVisible(true);
        }
        catch (IOException ex6) {
            ex6.printStackTrace();
            this.j();
            new a6(super.a3, com.diginet.digichat.util.a5.a(com.esial.util.c.a("%1 could not connect to the specified host.  Please verify that the %1 Server is running and that you are using the correct host name."), new String[] { DigiChatAppletAbstract.OEM_DigiChat }), ex6, this).setVisible(true);
        }
        catch (Exception ex7) {
            ex7.printStackTrace();
            this.j();
            new a6(super.a3, com.diginet.digichat.util.a5.a(com.esial.util.c.a("An unknown error occurred while connecting to the %1 Server.  Please contact the administrator in charge of the site."), new String[] { DigiChatAppletAbstract.OEM_DigiChat }), ex7, this).setVisible(true);
        }
        super.f = false;
        super.g = false;
        super.h = false;
    }
    
    public void a(final URL url, final String s) {
    }
    
    public void a(final int n) {
        if (super.a1 != null && n > 0 && n <= super.a1.length && super.a1[n - 1] != null) {
            try {
                super.a1[n - 1].stop();
                super.a1[n - 1].play();
            }
            catch (Throwable t) {}
        }
    }
    
    private void setMess(final v v, final int n, final j j, final boolean b, final boolean b2, final long n2) {
        final int n3 = (v.k != -1 && v.k != -2 && v.k != -3 && !b) ? 1 : 0;
        if (v.d() != null) {
            this.a(v.c(n, 0), j, v.k, v.j, n3, b2, v.i(), v.h(), n2, v.d());
        }
        else {
            this.a(v.c(n, 0), j, v.k, v.j, n3, false, v.i(), v.h(), n2, null);
        }
    }
    
    public boolean checkIcon(final bp bp) {
        if (bp != null && bp.a == null) {
            if (super.iconsLoader != null && super.iconsLoader.isActive()) {
                return true;
            }
            bp.a = this.loadImage(String.valueOf("userIcons/").concat(String.valueOf(bp.x())), null, 40);
        }
        return false;
    }
    
    public bp getIcon(final int n) {
        final bp bp;
        if (this.checkIcon(bp = (bp)super.aa.e(n))) {
            super.iconsLoader.load(bp);
        }
        return bp;
    }
    
    public boolean checkSmile(final b1 b1) {
        if (b1 != null && b1.a == null) {
            if (super.smilesLoader != null && super.smilesLoader.isActive()) {
                return true;
            }
            b1.a = this.loadImage(String.valueOf("emoticons/").concat(String.valueOf(b1.x())), null, 50);
        }
        return false;
    }
    
    public b1 getSmile(final b1 b1) {
        if (super.smilesLoader != null && super.smilesLoader.isActive()) {
            super.smilesLoader.load(b1);
        }
        else {
            b1.a = this.loadImage(String.valueOf("emoticons/").concat(String.valueOf(b1.x())), null, 50);
        }
        return b1;
    }
    
    public bp getStar(final int n) {
        final bp bp;
        if ((bp = ((n <= 0) ? null : ((bp)super.stars.e(n)))) != null && bp.a == null) {
            if (super.starsLoader != null && super.starsLoader.isActive()) {
                super.starsLoader.load(bp);
            }
            else {
                bp.a = this.a(String.valueOf("stars/").concat(String.valueOf(bp.x())), 60);
            }
        }
        return bp;
    }
    
    private void a(final String s, final j j, final int n, final int n2, final int n3, final boolean b, final long n4, final int n5, final long n6, final int nId, final int nLoc, final byte[] a) {
        if (super.y != null) {
            int w = j.w();
            if (w == this.w()) {
                w = n;
            }
            j.x();
            final bp icon = this.getIcon(j.a);
            final StringBuffer sb = new StringBuffer((n3 == 4) ? s : this.b(s));
            if (j.i(65)) {
                sb.append('\n');
                sb.append(j.timeStamp("Time tracing"));
            }
            final bf bf = new bf(sb.toString(), j, n3, icon, this, n6);
            bf.nId = nId;
            bf.nLoc = nLoc;
            bf.a(n == -3 || n == -2);
            bf.a(n4, n5);
            if (!b) {
                bf.a = a;
            }
            if (n3 == 1) {
                final bh bh = (bh)super.z.e(w);
                if (bh != null) {
                    bh.a(bf);
                }
                else if (super.bb) {
                    this.a(bf, j);
                }
                else {
                    super.y.a(bf);
                }
            }
            else {
                bf.n = j.c;
                if (n == -3 || n == -2) {
                    this.a(bf);
                }
                else {
                    super.y.a(bf);
                }
            }
        }
    }
    
    public void a(final String s, final j j, final int n, final int n2, final int n3, final boolean b, final long n4, final int n5, final long n6, final byte[] array) {
        this.a(s, j, n, n2, n3, b, n4, n5, n6, 0, 0, array);
    }
    
    public void loadProf() {
        if (this.hshUser == null) {
            this.hshUser = new Hashtable();
            final Hashtable cookie;
            if ((cookie = Scripts.getCookie(this.createName("user"))) != null) {
                final String s;
                if ((s = cookie.get("Scale")) != null) {
                    this.hshUser.put("Scale", s);
                }
                final String s2;
                if ((s2 = cookie.get("State")) != null) {
                    this.hshUser.put("State", s2);
                }
                final String s3;
                if ((s3 = cookie.get("Left")) != null) {
                    this.hshUser.put("Left", s3);
                }
                final String s4;
                if ((s4 = cookie.get("Top")) != null) {
                    this.hshUser.put("Top", s4);
                }
                final String s5;
                if ((s5 = cookie.get("Rooms")) != null) {
                    this.hshUser.put("Rooms", s5);
                }
                final String s6;
                if ((s6 = cookie.get("List")) != null) {
                    this.hshUser.put("List", s6);
                }
                final String s7;
                if ((s7 = cookie.get("Games")) != null) {
                    this.hshUser.put("Games", s7);
                }
                final String s8;
                if ((s8 = cookie.get("Check")) != null) {
                    this.hshUser.put("Check", s8);
                }
                final String s9;
                if ((s9 = cookie.get("Speed")) != null) {
                    this.hshUser.put("Speed", s9);
                }
                final String s10;
                if ((s10 = cookie.get("Styles")) != null) {
                    this.hshUser.put("Styles", s10);
                }
                final String s11;
                if ((s11 = cookie.get("Elems")) != null) {
                    this.hshUser.put("Elems", s11);
                }
                final String s12;
                if ((s12 = cookie.get("Normal")) != null) {
                    this.hshUser.put("Normal", s12);
                }
                final String s13;
                if ((s13 = cookie.get("FlagBack")) != null) {
                    this.hshUser.put("FlagBack", s13);
                }
                final String s14;
                if ((s14 = cookie.get("Back")) != null) {
                    this.hshUser.put("Back", s14);
                }
                final String s15;
                if ((s15 = cookie.get("PrivMess")) != null) {
                    this.hshUser.put("PrivMess", s15);
                }
                final String s16;
                if ((s16 = cookie.get("PrivBack")) != null) {
                    this.hshUser.put("PrivBack", s16);
                }
                final String s17;
                if ((s17 = cookie.get("Name")) != null) {
                    this.hshUser.put("Name", s17);
                }
                final String s18;
                if ((s18 = cookie.get("Style")) != null) {
                    this.hshUser.put("Style", s18);
                }
                final String s19;
                if ((s19 = cookie.get("Size")) != null) {
                    this.hshUser.put("Size", s19);
                }
                final String s20;
                if ((s20 = cookie.get("New")) != null) {
                    this.hshUser.put("New", s20);
                }
                final String s21;
                if ((s21 = cookie.get("Mess")) != null) {
                    this.hshUser.put("Mess", s21);
                }
                final String s22;
                if ((s22 = cookie.get("Flag")) != null) {
                    this.hshUser.put("Flag", s22);
                }
                final String s23;
                if ((s23 = cookie.get("Priv")) != null) {
                    this.hshUser.put("Priv", s23);
                }
                final String s24;
                if ((s24 = cookie.get("Exit")) != null) {
                    this.hshUser.put("Exit", s24);
                }
                final String s25;
                if ((s25 = cookie.get("X")) != null) {
                    this.hshUser.put("X", s25);
                }
                final String s26;
                if ((s26 = cookie.get("Y")) != null) {
                    this.hshUser.put("Y", s26);
                }
                final String s27;
                if ((s27 = cookie.get("Width")) != null) {
                    this.hshUser.put("Width", s27);
                }
                final String s28;
                if ((s28 = cookie.get("Height")) != null) {
                    this.hshUser.put("Height", s28);
                }
            }
        }
    }
    
    protected void m(final v v) {
        this.h(v.k);
        final String c = v.c(0, 1);
        final String[] array = new String[4];
        final StringTokenizer stringTokenizer = new StringTokenizer(c, "\n");
        for (int i = 0; i < 4; ++i) {
            array[i] = (stringTokenizer.hasMoreTokens() ? stringTokenizer.nextToken() : "");
        }
        try {
            final String s = array[0];
            final Locale locale = new Locale(array[1], array[2], array[3]);
            super.locale = locale;
            (super.format = new SimpleDateFormat(s, locale)).setTimeZone(new SimpleTimeZone(v.a(0, 3), ""));
        }
        catch (Throwable t) {}
        super.nBill = v.a(0, 4);
        super.nPort = v.a(0, 5);
        if (DigiChatAppletAbstract.initialWindowWidth <= 0) {
            DigiChatAppletAbstract.initialWindowWidth = v.a(0, 6);
        }
        if (DigiChatAppletAbstract.initialWindowHeight <= 0) {
            DigiChatAppletAbstract.initialWindowHeight = v.a(0, 7);
        }
        this.aaa(v.a(0), v.b(0, 0));
        super.t = v.c(0, 0);
        super.strVers = v.c(0, 2);
        super.strHome = v.c(0, 3);
        com.diginet.digichat.client.i.a[0] = com.esial.util.c.a("Bass");
        com.diginet.digichat.client.i.a[1] = com.esial.util.c.a("Bell");
        com.diginet.digichat.client.i.a[2] = com.esial.util.c.a("Castanet");
        com.diginet.digichat.client.i.a[3] = com.esial.util.c.a("Chime");
        com.diginet.digichat.client.i.a[4] = com.esial.util.c.a("Conga");
        com.diginet.digichat.client.i.a[5] = com.esial.util.c.a("Cow Bell");
        com.diginet.digichat.client.i.a[6] = com.esial.util.c.a("Double Bell");
        com.diginet.digichat.client.i.a[7] = com.esial.util.c.a("Drum Roll");
        com.diginet.digichat.client.i.a[8] = com.esial.util.c.a("Harp");
        if ((super.nSite = v.a(0, 2)) != 0) {
            if (super.cc.i()) {
                super.cc.z = this.a(String.valueOf(super.cc.f()).concat(String.valueOf("background.gif")), true);
            }
            if (super.cc.j()) {
                super.cc.aa = this.a(String.valueOf(super.cc.f()).concat(String.valueOf("chatbackground.gif")), true);
            }
            final Hashtable cookie;
            if ((cookie = Scripts.getCookie(this.createName("user"))) != null) {
                final String s2;
                if ((s2 = cookie.get("Scale")) != null) {
                    final long long1;
                    CommonStyle.fAnim = v.a(long1 = Long.parseLong(s2), 0);
                    super.bb = v.a(long1, 1);
                    super.b7 = v.a(long1, 5);
                    if (this.i(109)) {
                        super.p = v.a(long1, 6);
                    }
                }
                final String s3;
                if ((s3 = cookie.get("Check")) != null) {
                    super.nCheck = Integer.parseInt(s3);
                }
                final String s4;
                if ((s4 = cookie.get("Speed")) != null) {
                    super.bq = Integer.parseInt(s4);
                }
                if (this.i(109)) {
                    final String s5;
                    if ((s5 = cookie.get("Elems")) != null) {
                        super.lElems = Long.parseLong(s5);
                    }
                    final String s6;
                    if ((s6 = cookie.get("Styles")) != null) {
                        super.nStyles = Integer.parseInt(s6);
                    }
                    final String s7;
                    if ((s7 = cookie.get("Rooms")) != null) {
                        super.nRooms = Integer.parseInt(s7);
                    }
                    final String s8;
                    if ((s8 = cookie.get("List")) != null) {
                        super.nIMList = Integer.parseInt(s8);
                    }
                    final String s9;
                    if ((s9 = cookie.get("Games")) != null) {
                        super.nGames = Integer.parseInt(s9);
                    }
                }
                final String s10;
                final int int1;
                if ((s10 = cookie.get("Normal")) != null && (int1 = Integer.parseInt(s10)) != super.cc.k.getRGB()) {
                    super.cc.k = new Color(int1);
                }
                final String s11;
                final int int2;
                if ((s11 = cookie.get("FlagBack")) != null && (int2 = Integer.parseInt(s11)) != super.cc.i.getRGB()) {
                    super.cc.l = new Color(int2);
                }
                final String s12;
                final int int3;
                if ((s12 = cookie.get("Back")) != null && (int3 = Integer.parseInt(s12)) != super.cc.m.getRGB()) {
                    super.cc.m = new Color(int3);
                }
                final String s13;
                final int int4;
                if ((s13 = cookie.get("PrivMess")) != null && (int4 = Integer.parseInt(s13)) != super.cc.n.getRGB()) {
                    super.cc.n = new Color(int4);
                }
                final String s14;
                final int int5;
                if ((s14 = cookie.get("PrivBack")) != null && (int5 = Integer.parseInt(s14)) != super.cc.o.getRGB()) {
                    super.cc.o = new Color(int5);
                }
                final String p;
                if ((p = cookie.get("Name")) != null) {
                    super.cc.p = p;
                }
                final String s15;
                if ((s15 = cookie.get("Style")) != null) {
                    super.cc.r = Integer.parseInt(s15);
                }
                final String s16;
                if ((s16 = cookie.get("Size")) != null) {
                    super.cc.q = Integer.parseInt(s16);
                }
                final String s17;
                if ((s17 = cookie.get("New")) != null) {
                    super.bv = Integer.parseInt(s17);
                }
                final String s18;
                if ((s18 = cookie.get("Mess")) != null) {
                    super.bw = Integer.parseInt(s18);
                }
                final String s19;
                if ((s19 = cookie.get("Flag")) != null) {
                    super.bx = Integer.parseInt(s19);
                }
                final String s20;
                if ((s20 = cookie.get("Priv")) != null) {
                    super.by = Integer.parseInt(s20);
                }
                final String s21;
                if ((s21 = cookie.get("Exit")) != null) {
                    super.bz = Integer.parseInt(s21);
                }
            }
            if (DigiChatAppletAbstract.embedded) {
                super.y = new bq(this, (LayeredContainer)DigiChatAppletAbstract.applet);
            }
            else {
                final b0 a3 = new b0(this);
                super.a3 = a3;
                super.y = (x)a3.a();
                if (cookie != null) {
                    final String s22;
                    final String s23;
                    if ((s22 = cookie.get("X")) != null && (s23 = cookie.get("Y")) != null) {
                        a3.setLocation(Integer.parseInt(s22), Integer.parseInt(s23));
                    }
                    final String s24;
                    final String s25;
                    if ((s24 = cookie.get("Width")) != null && (s25 = cookie.get("Height")) != null) {
                        a3.setSize(Integer.parseInt(s24), Integer.parseInt(s25));
                    }
                }
                super.bounds = a3.getBounds();
                a3.addBoundsListener(this);
            }
            this.l();
        }
    }
    
    protected void n(final v v) {
        super.f = true;
        super.g = true;
        super.b = -999;
        this.l();
        if (this.getIcon(super.bp) == null) {
            this.b(super.a5);
        }
        else {
            this.b(super.bp);
        }
        final bo bo = (bo)super.ac.e(super.a6);
        final bo bo2 = (bo)super.ac.e(super.a4);
        if (bo == null || (bo.h == null && bo2.h != null)) {
            this.a(bo2);
        }
        else {
            this.a(bo);
        }
        if (super.y != null) {
            ((a7)super.y).e().b();
        }
    }
    
    protected void m() {
        if (!com.diginet.digichat.common.d.a) {
            return;
        }
        if (!super.f && !super.g) {
            if (super.y != null) {
                ((a7)super.y).e().b();
            }
            int b;
            if (super.ac.e(super.a6) == null) {
                b = super.a4;
            }
            else {
                b = super.a6;
            }
            super.g = true;
            final bo bo = (bo)super.ac.e(b);
            if (bo != null && bo.b && bo.h != null) {
                super.b = -999;
                new ba(super.y.b(), this, bo).setVisible(true);
            }
            super.b = b;
        }
    }
    
    protected void a(final bd room, final int n, final long n2, final int n3) {
        final int b = room.b;
        final bo room2 = (bo)super.ac.e(n);
        final bo room3 = (bo)super.ac.e(room.b);
        this.i(26);
        this.m();
        if (room.w() == this.w()) {
            if (room3 != null) {
                room3.a = false;
            }
            room2.a = true;
            super.b = n;
            if (super.y != null) {
                if (!super.az) {
                    this.c(false);
                }
                super.y.b(room2);
                if (!super.y.isVisible()) {
                    super.y.setVisible(true);
                }
                super.y.c().validate();
            }
        }
        if (room2 != null) {
            room2.c = this.g(room2.w());
            if (room3 != null && room2 != null) {
                if (room3.w() != room2.w() && (!room.i(23) || this.i(24)) && !room.i(79)) {
                    final bo bo = room2;
                    ++bo.c;
                }
            }
            else if (room3 == null && room2 != null && (!room.i(23) || this.i(24)) && !room.i(79)) {
                final bo bo2 = room2;
                ++bo2.c;
            }
            if (super.y != null) {
                super.y.setRoom(room2);
            }
        }
        if (room3 != null) {
            room3.c = this.g(room3.w());
            if (room3 != null && room2 != null && room3.w() != room2.w() && (!room.i(23) || this.i(24)) && !room.i(79)) {
                final bo bo3 = room3;
                --bo3.c;
            }
            if (super.y != null) {
                super.y.setRoom(room3);
            }
        }
        if (n == super.b && room.b != n) {
            if (room2 != null && !room2.i(57) && (!room.i(23) || this.i(24)) && !room.i(79)) {
                final bf bf = new bf(com.diginet.digichat.util.a5.a(com.esial.util.c.a("(This user has entered %1)"), new String[] { this.b(room2.x()) }), room, 0, this.getIcon(room.a), this);
                bf.a(n2, n3);
                this.a(super.bv);
                if (super.y != null) {
                    super.y.a(bf);
                }
            }
        }
        else if (room.b == super.b && n != super.b && room3 != null && room2 != null && !room3.i(57) && (!room.i(23) || this.i(24)) && !room.i(79)) {
            final bf bf2 = new bf(com.diginet.digichat.util.a5.a(com.esial.util.c.a("(This user has moved to %1)"), new String[] { this.b(room2.x()) }), room, 0, this.getIcon(room.a), this);
            bf2.a(n2, n3);
            this.a(super.bz);
            if (super.y != null) {
                super.y.a(bf2);
            }
        }
        room.b = n;
        this.setRoom(room);
        if (super.y != null) {
            ((a7)super.y).setBuddy(room, false, true);
            if (super.az || n == super.b) {
                super.updateList(room, true);
            }
            else if (!super.az && b == super.b) {
                super.y.a(room);
            }
        }
    }
    
    public void a(final Object o, final Object o2) {
        this.fYes = h.strYes.equals(o2);
    }
    
    private void setRefuse(final Play play, final String s) {
        if (play.mInvites + play.mPlayers < play.nMin + 1) {
            new a6(super.a3, com.esial.util.c.a("Note"), String.valueOf(String.valueOf(s).concat(String.valueOf("\n\n"))).concat(String.valueOf(com.esial.util.c.a("Invitations must be corrected."))), this).setVisible(true);
            this.fYes = true;
        }
        else {
            new a6(super.a3, com.esial.util.c.a("Note"), new String[] { com.esial.util.c.a("No"), h.strYes }, new String[] { String.valueOf(String.valueOf(s).concat(String.valueOf("\n\n"))).concat(String.valueOf(com.esial.util.c.a("Are you wanted correcting of invitations?"))) }, this, this).setVisible(true);
        }
        if (this.fYes) {
            new InviteBox(play, this);
        }
    }
    
    private boolean removePlayer(final Play play, final j j) {
        synchronized (play) {
            final String a = com.diginet.digichat.util.a5.a(com.esial.util.c.a("%1 has left play to %2."), new String[] { j.x(), ((k)super.games.e(play.nGame)).x() });
            for (int i = 0; i < play.mInvites; ++i) {
                if (play.nInvites[i] == j.w()) {
                    final int mInvites = play.mInvites - 1;
                    play.mInvites = mInvites;
                    final int n;
                    if ((n = mInvites - i) > 0) {
                        System.arraycopy(play.nInvites, i + 1, play.nInvites, i, n);
                    }
                    this.setRefuse(play, a);
                    // monitorexit(play)
                    return false;
                }
            }
            for (int k = 0; k < play.mPlayers; ++k) {
                if (play.nPlayers[k] == j.w()) {
                    final int mPlayers = play.mPlayers - 1;
                    play.mPlayers = mPlayers;
                    final int n2;
                    if ((n2 = mPlayers - k) > 0) {
                        System.arraycopy(play.nPlayers, k + 1, play.nPlayers, k, n2);
                    }
                    this.getScripts().removePlayer(play.objFrame, k);
                    if (play.nState == 1 && play.nOwner == this.w()) {
                        this.setRefuse(play, a);
                    }
                    // monitorexit(play)
                    return false;
                }
            }
        }
        return true;
    }
    
    protected void q(final v v) {
        this.i(26);
        this.m();
        for (int i = 0; i < v.c(); ++i) {
            final bd bd = (bd)super.ab.e(v.a(i, 0));
            if (bd != null) {
                final bo room = (bo)super.ac.e(bd.b);
                if (room != null) {
                    if ((!bd.i(23) || this.i(24)) && !bd.i(79)) {
                        final bo bo = room;
                        --bo.c;
                    }
                    if (super.y != null) {
                        super.y.setRoom(room);
                    }
                    if (bd.b == super.b && !room.i(57) && (!bd.i(23) || this.i(24)) && !bd.i(79)) {
                        final String c = v.c(i, 0);
                        String s;
                        if (c == null) {
                            s = com.diginet.digichat.util.a5.a(com.esial.util.c.a("(This user has left %1)"), new String[] { DigiChatAppletAbstract.OEM_DigiChat });
                        }
                        else {
                            s = String.valueOf(String.valueOf(this.b(c)).concat(String.valueOf(" "))).concat(String.valueOf(com.diginet.digichat.util.a5.a(com.esial.util.c.a("(This user has left %1)"), new String[] { DigiChatAppletAbstract.OEM_DigiChat })));
                        }
                        final bf bf = new bf(s, bd, 0, this.getIcon(bd.a), this);
                        bf.a(v.i(), v.h());
                        this.a(super.bz);
                        if (super.y != null) {
                            super.y.a(bf);
                        }
                    }
                }
                this.closeAdv(bd);
                if (super.y != null) {
                    super.y.a(bd);
                    ((a7)super.y).setBuddy(bd, false, false);
                }
                final bh bh = (bh)super.z.e(bd.w());
                if (bh != null) {
                    bh.dispose();
                }
                super.ab.a(true);
                try {
                    super.ab.d(bd);
                }
                finally {
                    super.ab.a();
                }
                final Enumeration<Play> elements = (Enumeration<Play>)super.vecPlays.elements();
                while (elements.hasMoreElements()) {
                    if (!this.removePlayer(elements.nextElement(), bd)) {
                        continue;
                    }
                }
                return;
            }
        }
    }
    
    protected void r(final v v) {
        for (int i = 0; i < v.c(); ++i) {
            if (v.d() != null) {
                this.a(v.d());
            }
            j j = (j)super.ab.e(v.a(i, 0));
            if (j == null && this.t() && v.e(0, 3)) {
                j = new j(-999, v.c(0, 1));
                j.a = v.a(0, 1);
            }
            if (j == null) {
                j = new j(-999, "Guest");
                j.a = super.a5;
            }
            if (j != null) {
                int n = j.w();
                if (n == this.w()) {
                    n = v.k;
                }
                if (!j.d) {
                    switch (v.k) {
                        case -3:
                        case -2:
                        case -1: {
                            if (j.c) {
                                this.a(super.bx);
                            }
                            else {
                                this.a(super.bw);
                            }
                            break;
                        }
                        default: {
                            if (super.z.a(n)) {
                                this.a(super.bw);
                                break;
                            }
                            this.a(super.by);
                            break;
                        }
                    }
                    this.setMess(v, i, j, true, false, 0L);
                }
            }
        }
    }
    
    protected void s(final v v) {
        final j j = (j)super.ab.e(v.a(0, 0));
        if (j != null) {
            final v v2 = new v(67073, 1);
            v2.k = j.w();
            v2.a(0, 0, this.w());
            if (!super.b6 || j.i(39)) {
                v2.a(0, 1, super.bs);
                if (super.be != null && super.be.length() > 0) {
                    v2.a(0, 0, super.be);
                }
                if (super.bi != null && super.bi.length() > 0) {
                    v2.a(0, 1, super.bi);
                }
                if (super.bf != null && super.bf.length() > 0) {
                    v2.a(0, 2, super.bf);
                }
                if (super.bg != null && super.bg.length() > 0) {
                    v2.a(0, 3, super.bg);
                }
                if (super.bt != -999) {
                    v2.f(0, super.bt);
                }
            }
            this.an(v2);
        }
    }
    
    protected void t(final v v) {
        this.i(26);
        if (super.y != null) {
            for (int i = 0; i < v.c(); ++i) {
                if (v.e(i, super.isMaster() ? 17 : 18)) {
                    final bf bf = new bf(this.b(v.c(i, 1)), this.b(v.c(i, 0)), this.colorValue(v.a(i, 1)), 0, this.getIcon(v.a(i, 0)), null, -999, false, false, v.b(i, 2));
                    bf.a(v.i(), v.h());
                    super.y.a(bf);
                    if (v.k == this.w()) {
                        this.a(super.by);
                    }
                    else {
                        this.a(super.bw);
                    }
                }
            }
        }
    }
    
    protected void u(final v v) {
        this.m();
        for (int i = 0; i < v.c(); ++i) {
            j j = (j)super.ab.e(v.a(i, 0));
            if (j == null && this.t() && v.e(0, 3)) {
                j = new j(-999, v.c(0, 1));
                j.a = v.a(0, 1);
            }
            if (j != null) {
                int n = j.w();
                if (n == this.w()) {
                    n = v.k;
                }
                if (v.k != this.w() && v.e(0, 20)) {
                    return;
                }
                if (!j.d) {
                    if (!v.m) {
                        switch (v.k) {
                            case -3:
                            case -2:
                            case -1: {
                                if (j.c) {
                                    this.a(super.bx);
                                }
                                else {
                                    this.a(super.bw);
                                }
                                break;
                            }
                            default: {
                                if (super.z.a(n)) {
                                    this.a(super.bw);
                                    break;
                                }
                                this.a(super.by);
                                break;
                            }
                        }
                    }
                    this.setMess(v, i, j, false, v.e(0, 20), v.b(i, 2));
                }
            }
        }
    }
    
    protected void v(final v v) {
        for (int i = 0; i < v.c(); ++i) {
            final j j = (j)super.ab.e(v.a(i, 0));
            if (j != null) {
                if (super.b9) {
                    final String c = v.c(i, 2);
                    if (c != null) {
                        try {
                            this.a(new URL(c), "_blank");
                        }
                        catch (MalformedURLException ex) {}
                    }
                }
                else {
                    new bl(super.y.b(), this, j, v, i);
                }
            }
        }
    }
    
    protected void w(final v v) {
        super.av = v.a(-1);
        super.ad.a(true);
        try {
            for (int i = 0; i < v.c(); ++i) {
                final int a = v.a(i, 0);
                bn bn = (bn)super.ad.e(a);
                if (v.e(i, 63)) {
                    if (bn != null) {
                        super.ad.g(a);
                    }
                }
                else {
                    if (bn == null) {
                        bn = new bn(a, v.c(i, 0));
                        super.ad.a(bn);
                    }
                    else {
                        bn.d(v.c(i, 0));
                    }
                    bn.d = v.a(i, 1);
                    bn.e = v.a(i, 2);
                    bn.b = v.c(i, 1);
                    bn.a = v.c(i, 2);
                    bn.c = v.c(i, 3);
                    bn.a(v.a(i));
                    bn.f = null;
                    if (super.y != null) {
                        ((a7)super.y).a(bn);
                    }
                }
            }
        }
        finally {
            super.ad.a();
        }
    }
    
    protected void x(final v v) {
        super.smiles.a(true);
        try {
            boolean b = false;
            final Vector<b1> vector = new Vector<b1>();
            for (int i = 0; i < v.c(); ++i) {
                final int a = v.a(i, 0);
                b1 b2 = (b1)super.smiles.e(a);
                if (v.e(i, 63)) {
                    if (b2 != null) {
                        super.smiles.f(a);
                    }
                }
                else {
                    if (b2 == null) {
                        b2 = new b1(a, v.c(i, 1));
                        super.smiles.a(b2);
                    }
                    else {
                        b2.d(v.c(i, 1));
                    }
                    b = true;
                    vector.addElement(b2);
                    b2.c = v.c(i, 0);
                    b2.a(v.a(i));
                }
            }
            if (b) {
                if (super.smilesLoader == null || !super.smilesLoader.isActive()) {
                    super.smilesLoader = new ImagesLoader(this, super.smiles, "emoticons/", 50, vector);
                }
                else {
                    super.smilesLoader.reset(vector);
                }
            }
        }
        finally {
            super.smiles.a();
        }
    }
    
    protected void y(final v v) {
        super.aa.a(true);
        try {
            boolean b = false;
            final Vector<bp> vector = new Vector<bp>();
            for (int i = 0; i < v.c(); ++i) {
                final int a = v.a(i, 0);
                bp bp = (bp)super.aa.e(a);
                if (v.e(i, 63)) {
                    if (bp != null) {
                        super.aa.g(a);
                    }
                }
                else {
                    if (bp == null) {
                        bp = new bp(a, v.c(i, 0));
                        super.aa.a(bp);
                    }
                    else {
                        bp.d(v.c(i, 0));
                    }
                    b = true;
                    vector.addElement(bp);
                    bp.a(v.a(i));
                    if (v.e(i, 62)) {
                        super.a5 = a;
                    }
                }
            }
            if (b) {
                if (super.iconsLoader == null || !super.iconsLoader.isActive()) {
                    super.iconsLoader = new ImagesLoader(this, super.aa, "userIcons/", 40, vector);
                }
                else {
                    super.iconsLoader.reset(vector);
                }
            }
        }
        finally {
            super.aa.a();
        }
    }
    
    protected void z(final v v) {
        final long as = super.as;
        super.as = v.a(0);
        super.b0 = v.a(0, 0);
        super.b1 = v.a(0, 1);
        super.b2 = v.a(0, 2);
        super.b3 = v.a(0, 3);
        super.b4 = v.a(0, 4);
        super.b5 = v.a(0, 5);
        super.nChars = v.a(0, 6);
        if (super.y != null && ((as ^ super.as) & 0x7000000000000L) != 0x0) {
            super.y.updateOpt();
        }
    }
    
    private void updateStar(final bd bd) {
        bd.bpStar = this.getStar(bd.nStar);
    }
    
    public Object[] createName(String hexString) {
        final byte[] array = new byte[8];
        final ByteArrayOutputStream byteArrayOutputStream;
        final DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream = new ByteArrayOutputStream());
        try {
            dataOutputStream.writeBytes(super.strEntry);
            dataOutputStream.writeInt(super.nSite);
            dataOutputStream.writeBytes(hexString);
            dataOutputStream.close();
        }
        catch (IOException ex) {}
        final byte[] digest = new MD5().digest(byteArrayOutputStream.toByteArray());
        final StringBuffer sb = new StringBuffer();
        for (int i = 0; i < digest.length; ++i) {
            if ((hexString = Integer.toHexString(digest[i] & 0xFF)).length() < 2) {
                sb.append('0');
            }
            sb.append(hexString);
        }
        for (int j = 0; j < 8; ++j) {
            array[j] = (byte)(digest[j << 1] + digest[(j << 1) + 1]);
        }
        return new Object[] { sb.toString(), array };
    }
    
    private void sendChanges(final v v) {
        v.k = -1;
        v.j = -1;
        v.l = -1234;
        v.f(-1, 62);
        v.a(0, 0, this.w());
        this.an(v);
    }
    
    private void sendSuper(final Hashtable hashtable) {
        if (hashtable != null) {
            super.f = false;
            v v = new v(67178241, 1);
            final String s;
            if ((s = hashtable.get("Name")) != null) {
                super.f = true;
                v.a(0, 1, Integer.parseInt(s));
            }
            final String s2;
            if ((s2 = hashtable.get("Back")) != null) {
                super.f = true;
                v.a(0, 2, Integer.parseInt(s2));
            }
            final String s3;
            final int int1;
            if ((s3 = hashtable.get("Icon")) != null && super.bp != (int1 = Integer.parseInt(s3))) {
                super.f = true;
                v.a(0, 3, super.bp = int1);
            }
            final String s4;
            if ((s4 = hashtable.get("Star")) != null) {
                super.f = true;
                v.a(0, 4, Integer.parseInt(s4));
            }
            final String s5 = hashtable.get("Pow2");
            final String s6;
            if (!this.i(127) && ((s6 = hashtable.get("Pow1")) != null || s5 != null)) {
                super.f = true;
                final long n2;
                final long n = (n2 = ((s6 == null) ? 0L : Long.parseLong(s6))) ^ this.y();
                final long n4;
                final long n3 = (n4 = ((s5 == null) ? 0L : Long.parseLong(s5))) ^ this.yyy();
                final long n6;
                final long n5 = (n6 = (n & n2)) ^ n2;
                final long n8;
                final long n7 = (n8 = (n3 & n4)) ^ n4;
                if (n5 == 0 && n7 == 0) {
                    v.a(0, 5, n6);
                    v.a(0, 7, n8);
                }
                else {
                    v.a(0, 5, n5 ^ 0L - 1L);
                    v.a(0, 7, n7 ^ 0L - 1L);
                    if (n6 != 0 || n8 != 0) {
                        this.sendChanges(v);
                        v = new v(67178241, 1);
                        v.a(0, 5, n6);
                        v.a(0, 7, n8);
                    }
                }
            }
            final String s7;
            if ((s7 = hashtable.get("Keep")) != null) {
                super.f = true;
                v.a(0, 9, Long.parseLong(s7));
            }
            final String s8;
            if ((s8 = hashtable.get("Nick")) != null && !s8.equals(this.x())) {
                super.f = true;
                v.a(0, 0, s8);
                this.d(s8);
            }
            if (super.f) {
                this.sendChanges(v);
            }
        }
    }
    
    private void sendState(int n) {
        final v v;
        (v = new v(67178244, 1)).k = -1;
        v.j = -1;
        v.l = -1234;
        v.a(0, 0, this.w());
        if (n == 0) {
            n = -1;
        }
        v.a(0, 1, n);
        this.an(v);
    }
    
    private void setState(final int n) {
        super.nState = n;
        if (super.y != null) {
            super.y.setState(n);
        }
    }
    
    private void sendUser(final Hashtable hashtable) {
        if (hashtable != null) {
            final String s;
            if ((s = hashtable.get("State")) != null) {
                this.setState(Integer.parseInt(s));
                this.sendState(super.nState);
            }
            final String s2;
            final int bp = ((s2 = hashtable.get("Icon")) == null) ? super.bp : Integer.parseInt(s2);
            final String s3;
            final int n = ((s3 = hashtable.get("NameColor")) == null) ? this.colorValue(super.clrName) : Integer.parseInt(s3);
            final String s4;
            final int n2 = ((s4 = hashtable.get("BackColor")) == null) ? this.colorValue(super.clrBack) : Integer.parseInt(s4);
            String x;
            if ((x = hashtable.get("Nick")) == null) {
                x = this.x();
            }
            if (bp != super.bp || n != this.colorValue(super.clrName) || n2 != this.colorValue(super.clrBack) || !x.equals(this.x())) {
                this.d(x);
                final v v = new v(67334, 1);
                v.k = -1;
                v.j = -1;
                v.a(0, 0, this.w());
                v.a(0, 1, super.bp = bp);
                v.a(0, 3, n);
                super.clrName = this.colorValue(n);
                v.a(0, 4, n2);
                super.clrBack = this.colorValue(n2);
                v.a(0, 0, x);
                this.an(v);
            }
        }
    }
    
    public void saveProf(final Hashtable hashtable, final long n) {
        Scripts.setCookie(this.createName("user"), hashtable, n);
    }
    
    public void saveProf(final Hashtable hashtable) {
        Scripts.setCookie(this.createName("user"), hashtable);
    }
    
    protected void aa(final v v) {
        boolean b = false;
        this.m();
        super.ab.a(true);
        try {
            for (int i = 0; i < v.c(); ++i) {
                final int a = v.a(i, 0);
                bd bd = (bd)super.ab.e(a);
                if (v.e(i, 63)) {
                    if (bd != null) {
                        final bo room = (bo)super.ac.e(v.a(i, 2));
                        if (room != null) {
                            if ((!bd.i(23) || this.i(24)) && !bd.i(79)) {
                                final bo bo = room;
                                --bo.c;
                            }
                            if (super.y != null) {
                                super.y.setRoom(room);
                            }
                        }
                        super.ab.g(a);
                        this.closeAdv(bd);
                        if (super.y != null) {
                            super.y.a(bd);
                            ((a7)super.y).setBuddy(bd, false, false);
                        }
                        final bh bh = (bh)super.z.e(bd.w());
                        if (bh != null) {
                            bh.dispose();
                        }
                    }
                }
                else {
                    final String b2 = this.b(v.c(i, 0));
                    if (bd == null) {
                        bd = new bd(a, b2);
                        super.ab.a(bd);
                    }
                    else {
                        bd.d(b2);
                    }
                    bd.a = v.a(i, 1);
                    bd.a = this.getIcon(bd.a);
                    bd.b = v.a(i, 2);
                    bd.aaa(v.a(i), v.b(i, 7));
                    final String c = v.c(i, 1);
                    final String c2 = v.c(i, 2);
                    if (c != null) {
                        bd.e = c;
                    }
                    if (c2 != null) {
                        bd.f = c2;
                    }
                    bd.strLong = v.c(i, 3);
                    bd.strShort = v.c(i, 4);
                    if (a == this.w()) {
                        b = true;
                        this.d(bd.x());
                        if (bd.a != -999) {
                            super.bp = bd.a;
                        }
                        final int a2;
                        if ((a2 = v.a(i, 3)) != 0) {
                            super.clrName = new Color(a2 & 0xFFFFFF);
                        }
                        final int a3;
                        if ((a3 = v.a(i, 4)) != 0) {
                            super.clrBack = new Color(a3 & 0xFFFFFF);
                        }
                        super.nStar = v.a(i, 5);
                        super.lChange[0] = v.b(i, 11);
                        super.lChange[1] = v.b(i, 13);
                    }
                    final int a4;
                    if ((a4 = v.a(i, 3)) != 0) {
                        bd.clrName = new Color(a4 & 0xFFFFFF);
                    }
                    final int a5;
                    if ((a5 = v.a(i, 4)) != 0) {
                        bd.clrBack = new Color(a5 & 0xFFFFFF);
                    }
                    bd.nStar = v.a(i, 5);
                    this.updateStar(bd);
                    final int a6;
                    bd.imgState = (((a6 = v.a(i, 6)) <= 0) ? null : super.cc.imgStates[a6 - 1]);
                    if (this.i(108) && bd.strShort != null && (bd.imgFlag = (Image)this.hshFlags.get(bd.strShort)) == null && (bd.imgFlag = this.a(String.valueOf(String.valueOf("../Countries/flags/").concat(String.valueOf(bd.strShort))).concat(String.valueOf(".gif")), false)) != null) {
                        this.hshFlags.put(bd.strShort, bd.imgFlag);
                    }
                    bd.lEntry = v.b(i, 9) + System.currentTimeMillis();
                    if (super.y != null) {
                        super.updateList(bd, false);
                        ((a7)super.y).setBuddy(bd, false, true);
                    }
                }
            }
        }
        finally {
            super.ab.a();
        }
        if (b) {
            final Hashtable cookie = Scripts.getCookie(this.createName("user"));
            if (v.e(-1, 62)) {
                final Hashtable cookie2;
                final String s;
                final String s2;
                if ((cookie2 = Scripts.getCookie(this.createName("super"))) == null || (s = cookie2.get("Save")) == null || (cookie != null && (s2 = cookie.get("Save")) != null && Long.parseLong(s2) > Long.parseLong(s))) {
                    this.sendSuper(cookie2);
                    this.sendUser(cookie);
                }
                else {
                    this.sendUser(cookie);
                    this.sendSuper(cookie2);
                }
            }
            else if (v.e(-1, 61)) {
                this.loadProf();
                this.hshUser.put("Nick", this.x());
                this.hshUser.put("Icon", Integer.toString(super.bp));
                this.hshUser.put("NameColor", Integer.toString(this.colorValue(super.clrName)));
                this.hshUser.put("BackColor", Integer.toString(this.colorValue(super.clrBack)));
                this.saveProf(this.hshUser);
            }
        }
    }
    
    public bh createPriv(final j j, final boolean b) {
        final bh bh = new bh(super.y.b(), this, j, b);
        final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int n = screenSize.width / 2 - 20;
        int n2 = screenSize.height / 2 - 20;
        int n3 = 0;
        int n4 = 0;
        switch (super.z.b() % 4) {
            case 0: {
                n3 = 0;
                n4 = 0;
                break;
            }
            case 1: {
                n3 = 0;
                n4 = n + 10;
                break;
            }
            case 2: {
                n3 = n2 + 10;
                n4 = 0;
                break;
            }
            default: {
                n3 = n2 + 10;
                n4 = n + 10;
                break;
            }
        }
        if (n > 400) {
            n = 400;
        }
        if (n2 > 300) {
            n2 = 300;
        }
        bh.reshape(2 + n4, 10 + n3, n, n2);
        return bh;
    }
    
    public void a(final bf bf, final j j) {
        final int w = j.w();
        if (((bg)super.ac.e(j.b)).a() && j.i(59) && !super.a8) {
            return;
        }
        aa priv = (aa)super.z.e(w);
        if (priv == null) {
            priv = this.createPriv(j, false);
            super.z.a(priv, w);
        }
        if (bf != null) {
            priv.a(bf);
        }
        priv.setVisible(true);
    }
    
    public void b(final int n) {
        final v v = new v(67334, 1);
        v.a(0, 0, this.w());
        v.a(0, 1, n);
        v.a(0, 3, 0);
        v.a(0, 4, 0);
        v.a(0, 0, this.x());
        v.k = -1;
        v.j = -1;
        this.an(v);
    }
    
    public void b(final boolean p) {
        super.p = p;
    }
    
    public void c(final int n) {
        if (super.y.b() != null) {
            super.y.b().setCursor(3);
        }
        if (this.a == null) {
            this.a = new ac(super.y.b(), this);
        }
        if (n >= 0) {
            this.a.b(n);
        }
        if (this.a.isVisible()) {
            this.a.toFront();
        }
        else {
            this.a.setVisible(true);
        }
        if (super.y.b() != null) {
            super.y.b().setCursor(0);
        }
    }
    
    public void a(final j j) {
        this.c.remove(j);
    }
    
    public void a(final int n, final j j) {
        final v v = new v(50400770, 1);
        v.a(0, 0, this.w());
        v.a(0, 1, n);
        v.k = j.w();
        v.j = -1;
        this.an(v);
    }
    
    private void setChanges(final j j, final v v) {
        final int a;
        if (((a = v.a(0, 1)) & 0xFF000000) != 0x0) {
            j.clrName = new Color(a & 0xFFFFFF);
        }
        final int a2;
        if (((a2 = v.a(0, 2)) & 0xFF000000) != 0x0) {
            j.clrBack = new Color(a2 & 0xFFFFFF);
        }
        final int a3;
        if ((a3 = v.a(0, 3)) != 0) {
            j.a = a3;
        }
        final int a4;
        if ((a4 = v.a(0, 4)) != 0) {
            j.nStar = a4;
        }
        final long b = v.b(0, 5);
        final long b2;
        long n;
        long n2;
        if ((b2 = v.b(0, 7)) < 0) {
            n = (j.y() & (b ^ 0L - 1L));
            n2 = (j.yyy() & (b2 ^ Long.MAX_VALUE));
        }
        else {
            n = (b | j.y());
            n2 = (b2 | j.yyy());
        }
        j.aaa(n, n2);
        final String c;
        if ((c = v.c(0, 0)) != null) {
            j.d(c);
        }
    }
    
    protected final void changeUser(final v v) {
        final int a;
        if ((a = v.a(0, 0)) == this.w()) {
            this.setChanges(this, v);
            int a2;
            if ((a2 = v.a(0, 3)) != 0) {
                super.bp = a2;
            }
            final long b;
            if (((b = v.b(0, 9)) < 16 && ((a2 = (int)b) & 0x8) != 0x0) || !v.e(-1, 62)) {
                if (b == 0L) {
                    Scripts.setCookie(this.createName("super"), null, 0L);
                }
                else {
                    long time;
                    if ((time = b) < 16) {
                        switch (a2 & 0x7) {
                            case 1: {
                                time = System.currentTimeMillis() + 86400000L;
                                break;
                            }
                            case 2: {
                                time = System.currentTimeMillis() + 604800000L;
                                break;
                            }
                            case 3: {
                                final Date date = new Date();
                                date.setMonth(date.getMonth() + 1);
                                time = date.getTime();
                                break;
                            }
                            case 4: {
                                time = 140737488355327L;
                                break;
                            }
                        }
                    }
                    final Hashtable<String, String> hashtable = new Hashtable<String, String>();
                    hashtable.put("Nick", this.x());
                    hashtable.put("Name", Integer.toString(this.colorValue(super.clrName)));
                    hashtable.put("Back", Integer.toString(this.colorValue(super.clrBack)));
                    hashtable.put("Icon", Integer.toString(super.bp));
                    hashtable.put("Star", Integer.toString(super.nStar));
                    hashtable.put("Pow1", Long.toString(this.y()));
                    hashtable.put("Pow2", Long.toString(this.yyy()));
                    hashtable.put("Keep", Long.toString(b));
                    final Hashtable cookie;
                    final String s;
                    hashtable.put("Save", (v.e(-1, 62) && (cookie = Scripts.getCookie(this.createName("super"))) != null && (s = cookie.get("Save")) == null) ? s : Long.toString(System.currentTimeMillis()));
                    Scripts.setCookie(this.createName("super"), hashtable, time);
                }
            }
        }
        final bd bd;
        if ((bd = (bd)super.ab.e(a)) != null) {
            this.setChanges(bd, v);
            final int a3;
            if ((a3 = v.a(0, 3)) != 0) {
                bd.a = this.getIcon(a3);
            }
            if (v.a(0, 4) != 0) {
                this.updateStar(bd);
            }
            if (super.y != null) {
                super.updateList(bd, false);
                ((a7)super.y).setBuddy(bd, false, true);
            }
        }
    }
    
    protected final void warningUser(final v v) {
        final j j;
        new a6(super.a3, ((j = (j)super.ab.e(v.a(0, 0))) == null) ? com.esial.util.c.a("Warning") : com.diginet.digichat.util.a5.a(com.esial.util.c.a("Warning from %1"), new String[] { j.x() }), v.c(0, 0), this).setVisible(true);
    }
    
    protected final void setState(final v v) {
        final bd bd;
        if ((bd = (bd)super.ab.e(v.a(0, 0))) != null) {
            final int a;
            bd.imgState = (((a = v.a(0, 1)) > 0) ? super.cc.imgStates[a - 1] : null);
            if (super.y != null) {
                super.updateList(bd, false);
            }
        }
    }
    
    protected void setScrolls(final v v) {
        super.scrolls.a(true);
        try {
            super.nScrolls = 0;
            super.fOne = v.e(-1, 51);
            super.fRight = v.e(-1, 50);
            super.nFrame = (v.e(-1, 49) ? 2 : (v.e(-1, 48) ? 1 : 0));
            final long a = v.a(-1);
            super.lScrolls = a;
            super.nDelay = (int)(a & 0xFFFF);
            super.nStep = (int)(super.lScrolls >> 16 & 0xFFFF);
            super.nInter = (int)(super.lScrolls >> 32 & 0xFFFF);
            for (int i = 0; i < v.c(); ++i) {
                final int a2 = v.a(i, 0);
                ScrollItem scrollItem = (ScrollItem)super.scrolls.e(a2);
                if (v.e(i, 63)) {
                    if (scrollItem != null) {
                        super.scrolls.g(a2);
                    }
                }
                else {
                    if (scrollItem == null) {
                        super.scrolls.a(scrollItem = new ScrollItem(a2, v.c(i, 0)));
                    }
                    else {
                        scrollItem.d(v.c(i, 0));
                    }
                    scrollItem.a(v.a(i));
                    scrollItem.nStyle = v.a(i, 1);
                    scrollItem.nSize = v.a(i, 2);
                    final int a3;
                    scrollItem.clrText = ((((a3 = v.a(i, 3)) & 0xFF000000) == 0x0) ? new Color(a3) : null);
                    final int a4;
                    scrollItem.clrBack = ((((a4 = v.a(i, 4)) & 0xFF000000) == 0x0) ? new Color(a4) : null);
                    scrollItem.strFont = v.c(i, 1);
                    scrollItem.fntText = null;
                    scrollItem.nLimits = null;
                    if (scrollItem.i(29) && scrollItem.i(super.isMaster() ? 17 : 18)) {
                        ++super.nScrolls;
                    }
                }
            }
        }
        finally {
            super.scrolls.a();
            if (super.y != null) {
                super.y.setScrolls();
            }
        }
    }
    
    protected void setPalette(final v v) {
        final int a = v.a(0, 0);
        final byte[] d;
        if ((d = v.d()) == null) {
            super.palettes[a] = null;
        }
        else {
            final int size = d.length / 3;
            Vector vector;
            if ((vector = super.palettes[a]) == null) {
                vector = (super.palettes[a] = new Vector(size));
            }
            vector.setSize(size);
            for (int i = 0, n = 0; i < size; ++i, n += 3) {
                vector.setElementAt(new Color(d[n] & 0xFF, d[n + 1] & 0xFF, d[n + 2] & 0xFF), i);
            }
        }
    }
    
    protected void setStars(final v v) {
        super.stars.a(true);
        try {
            for (int i = 0; i < v.c(); ++i) {
                final int a = v.a(i, 0);
                bp bp = (bp)super.stars.e(a);
                if (v.e(i, 63)) {
                    if (bp != null) {
                        super.stars.g(a);
                    }
                }
                else {
                    if (bp == null) {
                        bp = new bp(a, v.c(i, 0));
                        super.stars.a(bp);
                    }
                    else {
                        bp.d(v.c(i, 0));
                    }
                    bp.a = null;
                    bp.a(v.a(i));
                }
            }
        }
        finally {
            super.stars.a();
        }
    }
    
    protected void setOpt2(final v v) {
        super.nReplGuest = v.a(0, 0);
        super.nSmileGuest = v.a(0, 1);
        super.nReplSuper = v.a(0, 2);
        super.nSmileSuper = v.a(0, 3);
        super.nLife = v.a(0, 4);
        super.nChatWidth = v.a(0, 5);
        super.nChatHeight = v.a(0, 6);
        super.nOfflineGuest = v.a(0, 7);
        super.nOfflineMaster = v.a(0, 8);
        super.nRepl = (super.isMaster() ? super.nReplSuper : super.nReplGuest);
        super.nSmile = (super.isMaster() ? super.nSmileSuper : super.nSmileGuest);
        final boolean k = this.k();
        final long lPlus = super.lPlus;
        final boolean fLocations = super.fLocations;
        final long a = v.a(0);
        super.lPlus = a;
        super.fBuddies = (fLocations & v.a(a, 23));
        if (super.y != null) {
            if (((lPlus ^ super.lPlus) & 0x1F0000L) != 0x0) {
                super.y.updateMess();
            }
            final long n;
            if (((n = (v.a(super.lPlus, 23, this.k()) ^ v.a(lPlus, 23, k))) & 0xE00000L) != 0x0) {
                super.y.updateTabs();
                if (v.a(n, 21)) {
                    super.y.updateList();
                }
                if (v.a(n, 22)) {
                    super.y.updateGames();
                }
                if (v.a(n, 23)) {
                    super.y.updateButtons();
                }
            }
        }
    }
    
    protected void setPlus(final v v) {
        super.fBrand = v.e(0, 48);
        super.fTheme = v.e(0, 49);
        super.fIcon = v.e(0, 51);
        final String c;
        if ((c = v.c(0, 0)) != null) {
            DigiChatAppletAbstract.OEM_DigiChat = c;
        }
        if (super.y != null) {
            super.y.setPlus();
        }
    }
    
    public void setPlus() {
    }
    
    public void saveProf() {
        if (super.nLast == 0) {
            this.loadProf();
            this.hshUser.put("Scale", Long.toString(com.diginet.digichat.network.v.a(com.diginet.digichat.network.v.a(com.diginet.digichat.network.v.a(com.diginet.digichat.network.v.a(0L, 0, CommonStyle.fAnim), 1, super.bb), 5, super.b7), 6, super.p)));
            this.hshUser.put("Check", Integer.toString(super.nCheck));
            this.hshUser.put("Speed", Integer.toString(super.bq));
            if (this.i(109)) {
                this.hshUser.put("Elems", Long.toString(super.lElems));
                this.hshUser.put("Styles", Integer.toString(super.nStyles));
                this.hshUser.put("Rooms", Integer.toString(super.nRooms));
                this.hshUser.put("List", Integer.toString(super.nIMList));
                this.hshUser.put("Games", Integer.toString(super.nGames));
            }
            this.hshUser.put("Normal", Integer.toString(super.cc.k.getRGB()));
            this.hshUser.put("FlagBack", Integer.toString(super.cc.l.getRGB()));
            this.hshUser.put("Back", Integer.toString(super.cc.m.getRGB()));
            this.hshUser.put("PrivMess", Integer.toString(super.cc.n.getRGB()));
            this.hshUser.put("PrivBack", Integer.toString(super.cc.o.getRGB()));
            this.hshUser.put("Name", super.cc.p);
            this.hshUser.put("Style", Integer.toString(super.cc.r));
            this.hshUser.put("Size", Integer.toString(super.cc.q));
            this.hshUser.put("New", Integer.toString(super.bv));
            this.hshUser.put("Mess", Integer.toString(super.bw));
            this.hshUser.put("Flag", Integer.toString(super.bx));
            this.hshUser.put("Priv", Integer.toString(super.by));
            this.hshUser.put("Exit", Integer.toString(super.bz));
            this.hshUser.put("Save", Long.toString(System.currentTimeMillis()));
            this.saveProf(this.hshUser);
        }
    }
    
    public void saveState(final int state) {
        if (super.nState != state) {
            this.setState(state);
            this.loadProf();
            this.hshUser.put("State", Integer.toString(state));
            this.saveProf(this.hshUser);
            this.sendState(state);
        }
    }
    
    public void exetBounds(final LayeredBox layeredBox) {
        this.loadProf();
        final Rectangle bounds = layeredBox.getBounds();
        if (bounds.x != super.bounds.x || bounds.y != super.bounds.y) {
            super.f = true;
            this.hshUser.put("X", Integer.toString(bounds.x));
            this.hshUser.put("Y", Integer.toString(bounds.y));
        }
        if (bounds.width != super.bounds.width || bounds.height != super.bounds.height) {
            super.f = true;
            this.hshUser.put("Width", Integer.toString(bounds.width));
            this.hshUser.put("Height", Integer.toString(bounds.height));
        }
        if (super.f) {
            this.saveProf();
            super.bounds = bounds;
        }
    }
    
    public Point getPoint() {
        this.loadProf();
        final String s;
        final String s2;
        return ((s = this.hshUser.get("Left")) == null || (s2 = this.hshUser.get("Top")) == null) ? new Point() : new Point(Integer.parseInt(s), Integer.parseInt(s2));
    }
    
    public void startDrag(final Component component, final int n, final int n2) {
    }
    
    public void endDrag(final Component component, final int n, final int n2) {
        this.loadProf();
        this.hshUser.put("Left", Integer.toString(n));
        this.hshUser.put("Top", Integer.toString(n2));
        this.saveProf(this.hshUser);
    }
    
    protected void addIMMess(final v v) {
        for (int i = 0; i < v.c(); ++i) {
            final int a;
            final j j;
            if ((j = (j)super.ab.e(a = v.a(i, 0))) != null) {
                super.vecIMMess.addElement(new Object[] { new Long(v.a(i, 1) << 32 | a), v.c(i, 0), v.c(i, 1), v.c(i, 2), v.c(i, 3) });
                this.a(super.by);
                this.a(v.c(i, 3), j, v.k, v.j, 2, false, 0L, 0, 0L, null);
            }
        }
    }
    
    protected void setIMUser(final v v) {
        final bd bd;
        if ((bd = (bd)super.ab.e(v.a(0, 0))) != null) {
            bd.aaa(bd.y(), (bd.yyy() & 0xFFFFFFFFFFFF8FFFL) | (v.a(0) & 0x7000L));
            if (super.y != null) {
                super.updateList(bd, false);
            }
        }
    }
    
    protected void setAppear(final long lElems, final int nStyles) {
        if (lElems != super.lElems || nStyles != super.nStyles) {
            super.lElems = lElems;
            super.nStyles = nStyles;
            if (super.y != null) {
                super.y.setAppear();
            }
        }
    }
    
    protected void setLists(boolean b, final int nRooms, final int nimList, final int nGames) {
        boolean b2 = false;
        if (nRooms != super.nRooms) {
            if (super.nRooms == 0 || nRooms == 0) {
                b = true;
            }
            super.nRooms = nRooms;
            if (super.y != null) {
                super.y.updateRooms();
            }
        }
        if (nimList != super.nIMList) {
            if (super.nIMList == 2 || nimList == 2) {
                b = true;
            }
            if (super.nIMList == 3 || nimList == 3) {
                b2 = true;
            }
            super.nIMList = nimList;
            if (!super.getScripts().isWinClosed(super.webIMWin)) {
                if (super.imlist instanceof WebIMPanel) {
                    super.removeIMList();
                }
                else {
                    super.closeIMList();
                }
                switch (nimList) {
                    case 0: {
                        super.addIMList();
                        break;
                    }
                    case 1: {
                        super.updateIMList();
                        break;
                    }
                }
            }
        }
        if (nGames != super.nGames) {
            if (super.nGames == 2 || nGames == 2) {
                b = true;
            }
            super.nGames = nGames;
            if (super.gamesBox != null) {
                super.gamesBox.dispose();
                if (nGames < 2) {
                    super.callGames();
                }
            }
            if (super.y != null) {
                super.y.updateGames();
            }
        }
        if (super.y != null) {
            if (b2) {
                super.y.updateList();
            }
            if (b) {
                super.y.updateTabs();
            }
        }
    }
    
    protected void setAppear(final v v) {
        super.lAppear = v.a(0);
        if (!this.i(109)) {
            this.setAppear(super.lAppear & 0xFFFFFFFFL, (int)(super.lAppear >> 32) & 0xF);
            this.setLists(false, (int)(super.lAppear >> 36) & 0xF, (int)(super.lAppear >> 40) & 0xF, (int)(super.lAppear >> 44) & 0xF);
        }
    }
    
    protected void setLocations(final v v) {
        super.locations.a(true);
        try {
            for (int i = 0; i < v.c(); ++i) {
                final int a = v.a(i, 0);
                if (v.e(i, 63)) {
                    super.locations.g(a);
                }
                else {
                    final String c = v.c(i, 0);
                    Location location = (Location)super.locations.e(a);
                    if (location == null) {
                        location = ((a < 1000) ? new Local(this, a, c) : new Universal(this, a, c));
                        v.a(i, 0, super.locations.a(location, a));
                    }
                    else {
                        location.d(c);
                    }
                    location.a(v.a(i));
                    if (location instanceof Universal) {
                        final Universal universal = (Universal)location;
                        universal.nName = v.a(i, 1);
                        universal.nBack = v.a(i, 2);
                        universal.nIcon = v.a(i, 3);
                        universal.strDir = v.c(i, 1);
                        universal.strArch = v.c(i, 2);
                        universal.strFile = v.c(i, 3);
                        universal.strSite = v.c(i, 4);
                    }
                }
            }
            final Location location2;
            if ((location2 = (Location)super.locations.e(0)) == null) {
                super.locations.a(new Local(this, 0, DigiChatAppletAbstract.OEM_DigiChat), 0);
            }
            else {
                location2.d(DigiChatAppletAbstract.OEM_DigiChat);
            }
            super.fLocations = false;
            for (int j = 0; j < super.locations.b(); ++j) {
                if (((Location)super.locations.d(j)).i(61)) {
                    super.fLocations = true;
                    break;
                }
            }
        }
        finally {
            super.locations.a();
        }
        final boolean k = this.k();
        super.fBuddies = (super.fLocations && v.a(super.lPlus, 23));
        if (super.y != null && (k || this.k())) {
            if (k == this.k()) {
                ((a7)super.y).updateLocations();
            }
            else {
                ((a7)super.y).updateTabs();
                ((a7)super.y).updateButtons();
            }
        }
    }
    
    protected void setParams(final v params) {
        if (super.y != null) {
            ((a7)super.y).setParams(params);
        }
    }
    
    protected void setProf(final v prof) {
        if (super.y != null) {
            ((a7)super.y).setProf(prof);
        }
    }
    
    protected void addMess(final v v) {
        final int n;
        if ((n = v.c() - 1) == 0) {
            final Location location;
            new a6(super.a3, com.esial.util.c.a("Note"), com.diginet.digichat.util.a5.a(com.esial.util.c.a("Cannot send %1 to %2"), new String[] { com.esial.util.c.a((new String[] { "message", "invitation", "notification" })[(int)v.a(0)]), String.valueOf(v.c(0, 0)).concat(String.valueOf(((location = (Location)super.locations.e(v.a(0, 0))) == null) ? "" : String.valueOf(String.valueOf("(").concat(String.valueOf(location.x()))).concat(String.valueOf(")")))) }), this).setVisible(true);
        }
        else if (super.y != null) {
            final long[] array = new long[n];
            final String[] array2 = new String[n];
            for (int i = 0; i < n; ++i) {
                array[i] = v.b(i + 1, 0);
                array2[i] = v.c(i + 1, 0);
            }
            ((a7)super.y).addMess(v.c(0, 0), v.a(0, 0), null, array2, array);
        }
    }
    
    protected void addInvite(final v v) {
        final j j;
        final Game game;
        if ((j = (j)super.ab.e(v.a(0, 0))) != null && (game = (Game)super.games.e(v.a(0, 1))) != null) {
            int a;
            String s;
            if (v.e(0, 63)) {
                a = -1;
                s = "(This user recall his invitation play to %1)";
            }
            else {
                a = v.a(0, 2);
                s = "(This user invite you play to %1)";
            }
            this.a(com.diginet.digichat.util.a5.a(com.esial.util.c.a(s), new String[] { game.x() }), j, v.k, v.j, 4, false, 0L, 0, 0L, v.a(0, 1), a, null);
        }
        else if (!v.e(0, 63)) {
            this.sendJoin(v.a(0, 0), v.a(0, 2), true);
        }
    }
    
    protected void setJoin(final v v) {
        final j j;
        if ((j = (j)super.ab.e(v.a(0, 0))) != null) {
            final Enumeration<Play> elements = (Enumeration<Play>)super.vecPlays.elements();
            while (elements.hasMoreElements()) {
                final Play play;
                if ((play = elements.nextElement()).nPlay == v.a(0, 1)) {
                    synchronized (play) {
                        int i = 0;
                        while (i < play.mInvites) {
                            if (v.a(0, 0) == play.nInvites[i]) {
                                final Play play2 = play;
                                final int mInvites = play2.mInvites - 1;
                                play2.mInvites = mInvites;
                                final int n;
                                if ((n = mInvites - i) > 0) {
                                    System.arraycopy(play.nInvites, i + 1, play.nInvites, i, n);
                                }
                                if (v.e(0, 63)) {
                                    this.setRefuse(play, com.diginet.digichat.util.a5.a(com.esial.util.c.a("%1 has rejected your invitation to play %2."), new String[] { j.x(), ((k)super.games.e(play.nGame)).x() }));
                                    // monitorexit(play)
                                    return;
                                }
                                final int n2;
                                if ((n2 = play.mPlayers - 1) > 0) {
                                    final v v4;
                                    final v v3;
                                    final v v2 = v3 = (v4 = new v(67436804, n2));
                                    final int n3 = -1;
                                    v3.j = n3;
                                    v4.k = n3;
                                    v2.a(-1, j.w() << 32 | v.a(0, 1));
                                    for (int k = 1; k < play.mPlayers; ++k) {
                                        v2.a(k - 1, 0, play.nPlayers[k]);
                                    }
                                    this.an(v2);
                                }
                                this.addPlayer(play, j);
                                final v v5 = new v(67436805, play.nPlayers.length);
                                v5.j = -1;
                                v5.k = j.w();
                                v5.a(-1, this.w() << 32 | v.a(0, 1));
                                for (int l = 0; l < play.nPlayers.length; ++l) {
                                    v5.a(l, 0, (l < play.mPlayers) ? play.nPlayers[l] : -1);
                                }
                                this.an(v5);
                                if (play.mInvites == 0 && play.nState == 1) {
                                    final v v8;
                                    final v v7;
                                    final v v6 = v7 = (v8 = new v(67436806, play.mPlayers - 1));
                                    final int n4 = -1;
                                    v7.j = n4;
                                    v8.k = n4;
                                    v6.a(-1, play.mPlayers << 32 | v.a(0, 1));
                                    for (int n5 = 1; n5 < play.mPlayers; ++n5) {
                                        v6.a(n5 - 1, 0, play.nPlayers[n5]);
                                    }
                                    this.an(v6);
                                    this.startPlay(play, play.mPlayers, true);
                                }
                                // monitorexit(play)
                                return;
                            }
                            else {
                                ++i;
                            }
                        }
                    }
                    // monitorexit(play)
                }
            }
        }
    }
    
    protected void setPlayers(final v v) {
        final Enumeration<Play> elements = super.vecPlays.elements();
        while (elements.hasMoreElements()) {
            final Play play = elements.nextElement();
            if ((play.nOwner << 32 | play.nId) == v.a(-1)) {
                synchronized (play) {
                    if (play.nPlayers == null) {
                        play.nPlayers = new int[v.c()];
                    }
                    else {
                        final int n;
                        if ((n = play.mPlayers + v.c()) > play.nPlayers.length) {
                            final int[] nPlayers;
                            System.arraycopy(play.nPlayers, 0, nPlayers = new int[n], 0, play.mPlayers);
                            play.nPlayers = nPlayers;
                        }
                    }
                    super.ab.a(false);
                    try {
                        for (int i = 0; i < v.c(); ++i) {
                            final j j;
                            if ((j = (j)super.ab.e(v.a(i, 0))) != null) {
                                this.addPlayer(play, j);
                            }
                        }
                    }
                    finally {
                        super.ab.a();
                    }
                    // monitorexit(play)
                    break;
                }
            }
        }
    }
    
    protected void startPlay(final v v) {
        final Enumeration<Play> elements = super.vecPlays.elements();
        while (elements.hasMoreElements()) {
            final Play play = elements.nextElement();
            if ((play.nOwner << 32 | play.nId) == v.a(-1)) {
                synchronized (play) {
                    this.startPlay(play, v.a(0, 0), false);
                    // monitorexit(play)
                    break;
                }
            }
        }
    }
    
    protected void sendMove(final v v) {
        final Enumeration<Play> elements = super.vecPlays.elements();
        while (elements.hasMoreElements()) {
            final Play play = elements.nextElement();
            if ((play.nOwner << 32 | play.nId) == v.a(-1)) {
                synchronized (play) {
                    if (play.nState == 2) {
                        this.sendMove(play, v);
                    }
                    else {
                        play.vecMoves.addElement(v);
                    }
                    // monitorexit(play)
                    break;
                }
            }
        }
    }
    
    protected void closePlay(final v v) {
        final j j;
        if ((j = (j)super.ab.e(v.a(0, 0))) != null) {
            final Enumeration<Play> elements = super.vecPlays.elements();
            while (elements.hasMoreElements()) {
                final Play play = elements.nextElement();
                if ((play.nOwner << 32 | play.nId) == v.a(-1)) {
                    this.removePlayer(play, j);
                }
            }
        }
    }
    
    protected void setRecips(final v v) {
        super.recips.a(true);
        try {
            for (int i = 0; i < v.c(); ++i) {
                final int a = v.a(i, 0);
                Recip recip = (Recip)super.recips.e(a);
                if (v.e(i, 63)) {
                    if (recip != null) {
                        super.recips.g(a);
                    }
                }
                else {
                    if (recip == null) {
                        super.recips.a(recip = new Recip(a, v.c(i, 0)));
                    }
                    else {
                        recip.d(v.c(i, 0));
                    }
                    recip.a(v.a(i));
                }
            }
        }
        finally {
            super.recips.a();
        }
    }
    
    public String a(final String s) {
        if (s.length() < 4) {
            return String.valueOf(s).concat(String.valueOf(" bytes"));
        }
        try {
            double n = Long.parseLong(s) / 1024L;
            boolean b = false;
            if (n > 1024.0) {
                n /= 1024.0;
                b = true;
            }
            String s2 = String.valueOf(Math.floor(n * 1000.0 + 5.0) / 1000.0).concat(String.valueOf(""));
            final int index = s2.indexOf(46);
            final int n2 = s2.length() - 1;
            if (index < 0) {
                s2 = String.valueOf(s2).concat(String.valueOf(".00"));
            }
            else if (index == 0) {
                s2 = String.valueOf("0").concat(String.valueOf(s2));
            }
            else if (index == n2) {
                s2 = String.valueOf(s2).concat(String.valueOf("00"));
            }
            else if (index == n2 - 1) {
                s2 = String.valueOf(s2).concat(String.valueOf("0"));
            }
            else if (index + 2 < n2) {
                s2 = s2.substring(0, index + 3);
            }
            return String.valueOf(s2).concat(String.valueOf(b ? " MB" : " KB"));
        }
        catch (NumberFormatException ex) {
            return String.valueOf(s).concat(String.valueOf(" bytes"));
        }
    }
    
    public h() {
        this.hshUser = null;
        this.c = new Hashtable();
        this.hshFlags = new Hashtable();
    }
    
    static {
        strYes = com.esial.util.c.a("Yes");
    }
}

// 
// Decompiled by Procyon v0.5.30
// 

package com.easypano.tourweaver;

import java.awt.event.KeyEvent;
import java.awt.Rectangle;
import com.easypano.tourweaver.b.hb;
import java.net.URL;
import java.awt.Component;
import java.awt.LayoutManager;
import com.easypano.tourweaver.a.e;
import java.applet.Applet;

public class TWViewerApplet extends Applet implements ViewerAction
{
    private static final long serialVersionUID = -1978756173190336302L;
    public static r a;
    p b;
    n c;
    public static g d;
    private static String[] z;
    
    public void init() {
        e.a();
        this.enableEvents(8L);
        this.setLayout(null);
        TWViewerApplet.a = new r();
        TWViewerApplet.d = TWViewerApplet.a.j();
        this.c = TWViewerApplet.a.i();
        this.b = TWViewerApplet.a.h();
        TWViewerApplet.a.a(this);
        e.i = System.getProperty(TWViewerApplet.z[23]).charAt(2) - '0';
        e.a(this.getDocumentBase());
        e.b(this.getCodeBase());
        final URL a = e.a(this.getCodeBase(), this.getParameter(TWViewerApplet.z[7]));
        final hb f = TWViewerApplet.a.f();
        if (a != null) {
            f.a(TWViewerApplet.z[10], a.toString());
        }
        this.add(f);
        final Rectangle bounds = this.getBounds();
        f.setBounds(0, 0, bounds.width, bounds.height);
        f.a(TWViewerApplet.z[19], this.getParameter(TWViewerApplet.z[3]));
        f.a(TWViewerApplet.z[17], this.getParameter(TWViewerApplet.z[11]));
        f.a(TWViewerApplet.z[20], this.getParameter(TWViewerApplet.z[5]));
        f.a(TWViewerApplet.z[14], this.getParameter(TWViewerApplet.z[13]));
        final String parameter = this.getParameter(TWViewerApplet.z[18]);
        final String parameter2 = this.getParameter(TWViewerApplet.z[4]);
        final boolean e = com.easypano.tourweaver.a.e.e(parameter);
        final boolean e2 = com.easypano.tourweaver.a.e.e(parameter2);
        final boolean e3 = com.easypano.tourweaver.a.e.e(this.getParameter(TWViewerApplet.z[15]));
        TWViewerApplet.d.m = com.easypano.tourweaver.a.e.e(this.getParameter(TWViewerApplet.z[12]));
        TWViewerApplet.d.n = com.easypano.tourweaver.a.e.e(this.getParameter(TWViewerApplet.z[21]));
        TWViewerApplet.d.o = com.easypano.tourweaver.a.e.e(this.getParameter(TWViewerApplet.z[6]));
        TWViewerApplet.a.a(e3);
        TWViewerApplet.a.g().setInitFullScreen(e);
        TWViewerApplet.a.g().setInitShowToolbar(e2);
        final String parameter3 = this.getParameter(TWViewerApplet.z[9]);
        this.b.b(parameter3);
        this.c.setDefaultMovie(parameter3);
        this.c.setAutoRunMovie(com.easypano.tourweaver.a.e.e(this.getParameter(TWViewerApplet.z[8])));
        if (a != null) {
            this.b.b(a, 4);
        }
        this.b.b(com.easypano.tourweaver.a.e.a(this.getCodeBase(), this.getParameter(TWViewerApplet.z[16])), 3);
        this.b.b(com.easypano.tourweaver.a.e.a(this.getCodeBase(), this.getParameter(TWViewerApplet.z[2])), 2);
        this.b.b();
        System.out.println(TWViewerApplet.z[22] + com.easypano.tourweaver.a.e.c() + " " + com.easypano.tourweaver.a.e.d());
    }
    
    public void processKeyEvent(final KeyEvent keyEvent) {
        System.out.println(TWViewerApplet.z[24] + keyEvent);
    }
    
    public boolean emailTo(String trim) {
        if (trim == null) {
            return false;
        }
        System.out.println(TWViewerApplet.z[25] + trim);
        trim = trim.trim();
        if (trim.length() == 0) {
            System.out.println(TWViewerApplet.z[26] + trim);
            TWViewerApplet.d.emailto("");
        }
        else {
            final int index = trim.indexOf("@");
            if (index == -1 || index == 0 || index == trim.length()) {
                return false;
            }
            System.out.println(TWViewerApplet.z[27] + trim);
            TWViewerApplet.d.emailto(trim);
        }
        return true;
    }
    
    public void setFullScreen(final boolean initFullScreen) {
        TWViewerApplet.a.g().setInitFullScreen(initFullScreen);
    }
    
    public void destroy() {
        if (TWViewerApplet.a != null) {
            TWViewerApplet.a.a();
        }
        e.b();
        System.gc();
    }
    
    public void addCommunicationAction(final CommunicationAction playerListener) {
        if (this.c != null) {
            this.c.addPlayerListener(playerListener);
            this.b.a(playerListener);
            TWViewerApplet.a.g().setPlayerListener(playerListener);
        }
    }
    
    public void stop() {
        TWViewerApplet.d.stop();
    }
    
    public boolean hasMapViewer() {
        return TWViewerApplet.a.b();
    }
    
    public boolean hasSceneViewer() {
        return TWViewerApplet.a.c();
    }
    
    public int getSceneCount() {
        return TWViewerApplet.a.i().getRenderNum(1);
    }
    
    public int getMapCount() {
        return TWViewerApplet.a.i().getRenderNum(2);
    }
    
    public void zoomIn() {
        TWViewerApplet.d.zoomin(TWViewerApplet.z[0], 60);
    }
    
    public void zoomIn(final int n) {
        TWViewerApplet.d.zoomin(TWViewerApplet.z[0], n);
    }
    
    public void zoomOut() {
        TWViewerApplet.d.zoomout(TWViewerApplet.z[0], 60);
    }
    
    public void zoomOut(final int n) {
        TWViewerApplet.d.zoomout(TWViewerApplet.z[0], n);
    }
    
    public void left() {
        TWViewerApplet.d.left(TWViewerApplet.z[0], 60);
    }
    
    public void left(final int n) {
        TWViewerApplet.d.left(TWViewerApplet.z[0], n);
    }
    
    public void right() {
        TWViewerApplet.d.right(TWViewerApplet.z[0], 60);
    }
    
    public void right(final int n) {
        TWViewerApplet.d.right(TWViewerApplet.z[0], n);
    }
    
    public void up() {
        TWViewerApplet.d.up(TWViewerApplet.z[0], 60);
    }
    
    public void up(final int n) {
        TWViewerApplet.d.up(TWViewerApplet.z[0], n);
    }
    
    public void down() {
        TWViewerApplet.d.down(TWViewerApplet.z[0], 60);
    }
    
    public void down(final int n) {
        TWViewerApplet.d.down(TWViewerApplet.z[0], n);
    }
    
    public void reset() {
        TWViewerApplet.d.reset();
    }
    
    public void autoPanAtRate(final double n, final double n2, final double n3) {
        TWViewerApplet.d.autopanscene(n, n2, n3, TWViewerApplet.z[0]);
    }
    
    public void autoPan(final int n, final int n2, final int n3) {
        TWViewerApplet.d.autopanscene(n, n2, n3, TWViewerApplet.z[0]);
    }
    
    public void gotoView(final double n, final double n2, final double n3) {
        this.c.goToViewer(n, n2, n3);
    }
    
    public void gotoView(final double n, final double n2, final double n3, final int n4) {
        this.c.goToViewer(n, n2, n3, n4);
    }
    
    public void showHideHotspot(final String s) {
        TWViewerApplet.d.showhidehotspot(s);
    }
    
    public void switchToScene(final String s) {
        TWViewerApplet.d.switchtoscene(s);
    }
    
    public void switchToScene(final String s, final double n, final double n2, final double n3) {
        TWViewerApplet.d.switchtoscene(s, n, n2, n3);
    }
    
    public void switchToScene(final String s, final double n, final double n2, final double n3, final String s2, final int n4) {
        TWViewerApplet.d.switchtoscene(s, n, n2, n3, s2, n4, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, null, null, null);
    }
    
    public void nextScene() {
        TWViewerApplet.d.nextscene();
    }
    
    public void previousScene() {
        TWViewerApplet.d.previousscene();
    }
    
    public void forward() {
        TWViewerApplet.d.forward();
    }
    
    public void backward() {
        TWViewerApplet.d.backward();
    }
    
    public void playPauseMovie(final String s, final boolean b) {
        TWViewerApplet.d.switchtomovie(s, b);
    }
    
    public void stopMovie() {
        this.c.stopMovie();
    }
    
    public void playSound(final int n, final boolean b) {
    }
    
    public void mute() {
    }
    
    public void fullScreen(final String s) {
        TWViewerApplet.d.fullscreen(s);
    }
    
    public void closeWindow() {
        TWViewerApplet.d.closewindow();
    }
    
    public void showHelp() {
        TWViewerApplet.d.showhelp();
    }
    
    public void print() {
        TWViewerApplet.d.print();
    }
    
    public void emailTo() {
        this.emailTo("");
    }
    
    public void switchToMap(final String s) {
        TWViewerApplet.d.switchtomap(s);
    }
    
    public void switchToMap(final String s, final String s2, final int n) {
        TWViewerApplet.d.switchtomap(s, s2, n);
    }
    
    public void linkToDetailImage(final String s) {
        TWViewerApplet.d.linktodetailimage(s);
    }
    
    public void mZoomIn() {
        TWViewerApplet.d.zoomin(TWViewerApplet.z[1], 60);
    }
    
    public void mZoomIn(final int n) {
        TWViewerApplet.d.zoomin(TWViewerApplet.z[1], n);
    }
    
    public void mZoomOut() {
        TWViewerApplet.d.zoomout(TWViewerApplet.z[1], 60);
    }
    
    public void mZoomOut(final int n) {
        TWViewerApplet.d.zoomout(TWViewerApplet.z[1], n);
    }
    
    public void mLeft() {
        TWViewerApplet.d.right(TWViewerApplet.z[1], 60);
    }
    
    public void mLeft(final int n) {
        TWViewerApplet.d.right(TWViewerApplet.z[1], n);
    }
    
    public void mRight() {
        TWViewerApplet.d.left(TWViewerApplet.z[1], 60);
    }
    
    public void mRight(final int n) {
        TWViewerApplet.d.left(TWViewerApplet.z[1], n);
    }
    
    public void mUp() {
        TWViewerApplet.d.up(TWViewerApplet.z[1], 60);
    }
    
    public void mUp(final int n) {
        TWViewerApplet.d.up(TWViewerApplet.z[1], n);
    }
    
    public void mDown() {
        TWViewerApplet.d.down(TWViewerApplet.z[1], 60);
    }
    
    public void mDown(final int n) {
        TWViewerApplet.d.down(TWViewerApplet.z[1], n);
    }
    
    public void mAutoPan(final int n, final int n2, final int n3) {
        TWViewerApplet.d.autopanmap(n, n2, n3, TWViewerApplet.z[1]);
    }
    
    public void mGotoView(final double n, final double n2, final double n3) {
        TWViewerApplet.d.mgotoview(n, n2, n3);
    }
    
    public void stopAutoPan() {
        this.stop();
    }
    
    static {
        final String[] z = new String[28];
        final int n = 0;
        final char[] charArray = "t\u0007xk<q\rxr<U".toCharArray();
        int length;
        int n3;
        final int n2 = n3 = (length = charArray.length);
        int n4 = 0;
        while (true) {
            Label_0097: {
                if (n2 > 1) {
                    break Label_0097;
                }
                length = (n3 = n4);
                do {
                    final char c = charArray[n3];
                    char c2 = '\0';
                    switch (n4 % 5) {
                        case 0: {
                            c2 = '\'';
                            break;
                        }
                        case 1: {
                            c2 = 'd';
                            break;
                        }
                        case 2: {
                            c2 = '\u001d';
                            break;
                        }
                        case 3: {
                            c2 = '\u0005';
                            break;
                        }
                        default: {
                            c2 = 'Y';
                            break;
                        }
                    }
                    charArray[length] = (char)(c ^ c2);
                    ++n4;
                } while (n2 == 0);
            }
            if (n2 > n4) {
                continue;
            }
            break;
        }
        z[n] = new String(charArray).intern();
        final int n5 = 1;
        final char[] charArray2 = "j\u0005mS0B\u0013xw".toCharArray();
        int length2;
        int n7;
        final int n6 = n7 = (length2 = charArray2.length);
        int n8 = 0;
        while (true) {
            Label_0213: {
                if (n6 > 1) {
                    break Label_0213;
                }
                length2 = (n7 = n8);
                do {
                    final char c3 = charArray2[n7];
                    char c4 = '\0';
                    switch (n8 % 5) {
                        case 0: {
                            c4 = '\'';
                            break;
                        }
                        case 1: {
                            c4 = 'd';
                            break;
                        }
                        case 2: {
                            c4 = '\u001d';
                            break;
                        }
                        case 3: {
                            c4 = '\u0005';
                            break;
                        }
                        default: {
                            c4 = 'Y';
                            break;
                        }
                    }
                    charArray2[length2] = (char)(c3 ^ c4);
                    ++n8;
                } while (n6 == 0);
            }
            if (n6 > n8) {
                continue;
            }
            break;
        }
        z[n5] = new String(charArray2).intern();
        final int n9 = 2;
        final char[] charArray3 = "T\u000ftkwF\u0016~m0Q\u0001".toCharArray();
        int length3;
        int n11;
        final int n10 = n11 = (length3 = charArray3.length);
        int n12 = 0;
        while (true) {
            Label_0329: {
                if (n10 > 1) {
                    break Label_0329;
                }
                length3 = (n11 = n12);
                do {
                    final char c5 = charArray3[n11];
                    char c6 = '\0';
                    switch (n12 % 5) {
                        case 0: {
                            c6 = '\'';
                            break;
                        }
                        case 1: {
                            c6 = 'd';
                            break;
                        }
                        case 2: {
                            c6 = '\u001d';
                            break;
                        }
                        case 3: {
                            c6 = '\u0005';
                            break;
                        }
                        default: {
                            c6 = 'Y';
                            break;
                        }
                    }
                    charArray3[length3] = (char)(c5 ^ c6);
                    ++n12;
                } while (n10 == 0);
            }
            if (n10 > n12) {
                continue;
            }
            break;
        }
        z[n9] = new String(charArray3).intern();
        final int n13 = 3;
        final char[] charArray4 = "K\u0013_b\u001aH\brw".toCharArray();
        int length4;
        int n15;
        final int n14 = n15 = (length4 = charArray4.length);
        int n16 = 0;
        while (true) {
            Label_0445: {
                if (n14 > 1) {
                    break Label_0445;
                }
                length4 = (n15 = n16);
                do {
                    final char c7 = charArray4[n15];
                    char c8 = '\0';
                    switch (n16 % 5) {
                        case 0: {
                            c8 = '\'';
                            break;
                        }
                        case 1: {
                            c8 = 'd';
                            break;
                        }
                        case 2: {
                            c8 = '\u001d';
                            break;
                        }
                        case 3: {
                            c8 = '\u0005';
                            break;
                        }
                        default: {
                            c8 = 'Y';
                            break;
                        }
                    }
                    charArray4[length4] = (char)(c7 ^ c8);
                    ++n16;
                } while (n14 == 0);
            }
            if (n14 > n16) {
                continue;
            }
            break;
        }
        z[n13] = new String(charArray4).intern();
        final int n17 = 4;
        final char[] charArray5 = "N\u0017Nm6P0rj5E\u0005o".toCharArray();
        int length5;
        int n19;
        final int n18 = n19 = (length5 = charArray5.length);
        int n20 = 0;
        while (true) {
            Label_0561: {
                if (n18 > 1) {
                    break Label_0561;
                }
                length5 = (n19 = n20);
                do {
                    final char c9 = charArray5[n19];
                    char c10 = '\0';
                    switch (n20 % 5) {
                        case 0: {
                            c10 = '\'';
                            break;
                        }
                        case 1: {
                            c10 = 'd';
                            break;
                        }
                        case 2: {
                            c10 = '\u001d';
                            break;
                        }
                        case 3: {
                            c10 = '\u0005';
                            break;
                        }
                        default: {
                            c10 = 'Y';
                            break;
                        }
                    }
                    charArray5[length5] = (char)(c9 ^ c10);
                    ++n20;
                } while (n18 == 0);
            }
            if (n18 > n20) {
                continue;
            }
            break;
        }
        z[n17] = new String(charArray5).intern();
        final int n21 = 5;
        final char[] charArray6 = "K\u0013_d+d\u000bqj+".toCharArray();
        int length6;
        int n23;
        final int n22 = n23 = (length6 = charArray6.length);
        int n24 = 0;
        while (true) {
            Label_0677: {
                if (n22 > 1) {
                    break Label_0677;
                }
                length6 = (n23 = n24);
                do {
                    final char c11 = charArray6[n23];
                    char c12 = '\0';
                    switch (n24 % 5) {
                        case 0: {
                            c12 = '\'';
                            break;
                        }
                        case 1: {
                            c12 = 'd';
                            break;
                        }
                        case 2: {
                            c12 = '\u001d';
                            break;
                        }
                        case 3: {
                            c12 = '\u0005';
                            break;
                        }
                        default: {
                            c12 = 'Y';
                            break;
                        }
                    }
                    charArray6[length6] = (char)(c11 ^ c12);
                    ++n24;
                } while (n22 == 0);
            }
            if (n22 > n24) {
                continue;
            }
            break;
        }
        z[n21] = new String(charArray6).intern();
        final int n25 = 6;
        final char[] charArray7 = "O\u0005nD:S\rrk\nH\u0011sa".toCharArray();
        int length7;
        int n27;
        final int n26 = n27 = (length7 = charArray7.length);
        int n28 = 0;
        while (true) {
            Label_0793: {
                if (n26 > 1) {
                    break Label_0793;
                }
                length7 = (n27 = n28);
                do {
                    final char c13 = charArray7[n27];
                    char c14 = '\0';
                    switch (n28 % 5) {
                        case 0: {
                            c14 = '\'';
                            break;
                        }
                        case 1: {
                            c14 = 'd';
                            break;
                        }
                        case 2: {
                            c14 = '\u001d';
                            break;
                        }
                        case 3: {
                            c14 = '\u0005';
                            break;
                        }
                        default: {
                            c14 = 'Y';
                            break;
                        }
                    }
                    charArray7[length7] = (char)(c13 ^ c14);
                    ++n28;
                } while (n26 == 0);
            }
            if (n26 > n28) {
                continue;
            }
            break;
        }
        z[n25] = new String(charArray7).intern();
        final int n29 = 7;
        final char[] charArray8 = "K\u0013Th>".toCharArray();
        int length8;
        int n31;
        final int n30 = n31 = (length8 = charArray8.length);
        int n32 = 0;
        while (true) {
            Label_0909: {
                if (n30 > 1) {
                    break Label_0909;
                }
                length8 = (n31 = n32);
                do {
                    final char c15 = charArray8[n31];
                    char c16 = '\0';
                    switch (n32 % 5) {
                        case 0: {
                            c16 = '\'';
                            break;
                        }
                        case 1: {
                            c16 = 'd';
                            break;
                        }
                        case 2: {
                            c16 = '\u001d';
                            break;
                        }
                        case 3: {
                            c16 = '\u0005';
                            break;
                        }
                        default: {
                            c16 = 'Y';
                            break;
                        }
                    }
                    charArray8[length8] = (char)(c15 ^ c16);
                    ++n32;
                } while (n30 == 0);
            }
            if (n30 > n32) {
                continue;
            }
            break;
        }
        z[n29] = new String(charArray8).intern();
        final int n33 = 8;
        final char[] charArray9 = "N\u0017\\p-H6hk\u0014H\u0012t`".toCharArray();
        int length9;
        int n35;
        final int n34 = n35 = (length9 = charArray9.length);
        int n36 = 0;
        while (true) {
            Label_1025: {
                if (n34 > 1) {
                    break Label_1025;
                }
                length9 = (n35 = n36);
                do {
                    final char c17 = charArray9[n35];
                    char c18 = '\0';
                    switch (n36 % 5) {
                        case 0: {
                            c18 = '\'';
                            break;
                        }
                        case 1: {
                            c18 = 'd';
                            break;
                        }
                        case 2: {
                            c18 = '\u001d';
                            break;
                        }
                        case 3: {
                            c18 = '\u0005';
                            break;
                        }
                        default: {
                            c18 = 'Y';
                            break;
                        }
                    }
                    charArray9[length9] = (char)(c17 ^ c18);
                    ++n36;
                } while (n34 == 0);
            }
            if (n34 > n36) {
                continue;
            }
            break;
        }
        z[n33] = new String(charArray9).intern();
        final int n37 = 9;
        final char[] charArray10 = "C\u0001{d,K\u0010Pj/N\u0001".toCharArray();
        int length10;
        int n39;
        final int n38 = n39 = (length10 = charArray10.length);
        int n40 = 0;
        while (true) {
            Label_1141: {
                if (n38 > 1) {
                    break Label_1141;
                }
                length10 = (n39 = n40);
                do {
                    final char c19 = charArray10[n39];
                    char c20 = '\0';
                    switch (n40 % 5) {
                        case 0: {
                            c20 = '\'';
                            break;
                        }
                        case 1: {
                            c20 = 'd';
                            break;
                        }
                        case 2: {
                            c20 = '\u001d';
                            break;
                        }
                        case 3: {
                            c20 = '\u0005';
                            break;
                        }
                        default: {
                            c20 = 'Y';
                            break;
                        }
                    }
                    charArray10[length10] = (char)(c19 ^ c20);
                    ++n40;
                } while (n38 == 0);
            }
            if (n38 > n40) {
                continue;
            }
            break;
        }
        z[n37] = new String(charArray10).intern();
        final int n41 = 10;
        final char[] charArray11 = "E\u0003Th>".toCharArray();
        int length11;
        int n43;
        final int n42 = n43 = (length11 = charArray11.length);
        int n44 = 0;
        while (true) {
            Label_1257: {
                if (n42 > 1) {
                    break Label_1257;
                }
                length11 = (n43 = n44);
                do {
                    final char c21 = charArray11[n43];
                    char c22 = '\0';
                    switch (n44 % 5) {
                        case 0: {
                            c22 = '\'';
                            break;
                        }
                        case 1: {
                            c22 = 'd';
                            break;
                        }
                        case 2: {
                            c22 = '\u001d';
                            break;
                        }
                        case 3: {
                            c22 = '\u0005';
                            break;
                        }
                        default: {
                            c22 = 'Y';
                            break;
                        }
                    }
                    charArray11[length11] = (char)(c21 ^ c22);
                    ++n44;
                } while (n42 == 0);
            }
            if (n42 > n44) {
                continue;
            }
            break;
        }
        z[n41] = new String(charArray11).intern();
        final int n45 = 11;
        final char[] charArray12 = "K\u0013_d+e\u0003^j5H\u0016".toCharArray();
        int length12;
        int n47;
        final int n46 = n47 = (length12 = charArray12.length);
        int n48 = 0;
        while (true) {
            Label_1373: {
                if (n46 > 1) {
                    break Label_1373;
                }
                length12 = (n47 = n48);
                do {
                    final char c23 = charArray12[n47];
                    char c24 = '\0';
                    switch (n48 % 5) {
                        case 0: {
                            c24 = '\'';
                            break;
                        }
                        case 1: {
                            c24 = 'd';
                            break;
                        }
                        case 2: {
                            c24 = '\u001d';
                            break;
                        }
                        case 3: {
                            c24 = '\u0005';
                            break;
                        }
                        default: {
                            c24 = 'Y';
                            break;
                        }
                    }
                    charArray12[length12] = (char)(c23 ^ c24);
                    ++n48;
                } while (n46 == 0);
            }
            if (n46 > n48) {
                continue;
            }
            break;
        }
        z[n45] = new String(charArray12).intern();
        final int n49 = 12;
        final char[] charArray13 = "O\u0005nV:B\nxV6R\ny".toCharArray();
        int length13;
        int n51;
        final int n50 = n51 = (length13 = charArray13.length);
        int n52 = 0;
        while (true) {
            Label_1489: {
                if (n50 > 1) {
                    break Label_1489;
                }
                length13 = (n51 = n52);
                do {
                    final char c25 = charArray13[n51];
                    char c26 = '\0';
                    switch (n52 % 5) {
                        case 0: {
                            c26 = '\'';
                            break;
                        }
                        case 1: {
                            c26 = 'd';
                            break;
                        }
                        case 2: {
                            c26 = '\u001d';
                            break;
                        }
                        case 3: {
                            c26 = '\u0005';
                            break;
                        }
                        default: {
                            c26 = 'Y';
                            break;
                        }
                    }
                    charArray13[length13] = (char)(c25 ^ c26);
                    ++n52;
                } while (n50 == 0);
            }
            if (n50 > n52) {
                continue;
            }
            break;
        }
        z[n49] = new String(charArray13).intern();
        final int n53 = 13;
        final char[] charArray14 = "K\u0013_d+e\u000bhk=T".toCharArray();
        int length14;
        int n55;
        final int n54 = n55 = (length14 = charArray14.length);
        int n56 = 0;
        while (true) {
            Label_1605: {
                if (n54 > 1) {
                    break Label_1605;
                }
                length14 = (n55 = n56);
                do {
                    final char c27 = charArray14[n55];
                    char c28 = '\0';
                    switch (n56 % 5) {
                        case 0: {
                            c28 = '\'';
                            break;
                        }
                        case 1: {
                            c28 = 'd';
                            break;
                        }
                        case 2: {
                            c28 = '\u001d';
                            break;
                        }
                        case 3: {
                            c28 = '\u0005';
                            break;
                        }
                        default: {
                            c28 = 'Y';
                            break;
                        }
                    }
                    charArray14[length14] = (char)(c27 ^ c28);
                    ++n56;
                } while (n54 == 0);
            }
            if (n54 > n56) {
                continue;
            }
            break;
        }
        z[n53] = new String(charArray14).intern();
        final int n57 = 14;
        final char[] charArray15 = "E\u0005oG6R\nyv".toCharArray();
        int length15;
        int n59;
        final int n58 = n59 = (length15 = charArray15.length);
        int n60 = 0;
        while (true) {
            Label_1721: {
                if (n58 > 1) {
                    break Label_1721;
                }
                length15 = (n59 = n60);
                do {
                    final char c29 = charArray15[n59];
                    char c30 = '\0';
                    switch (n60 % 5) {
                        case 0: {
                            c30 = '\'';
                            break;
                        }
                        case 1: {
                            c30 = 'd';
                            break;
                        }
                        case 2: {
                            c30 = '\u001d';
                            break;
                        }
                        case 3: {
                            c30 = '\u0005';
                            break;
                        }
                        default: {
                            c30 = 'Y';
                            break;
                        }
                    }
                    charArray15[length15] = (char)(c29 ^ c30);
                    ++n60;
                } while (n58 == 0);
            }
            if (n58 > n60) {
                continue;
            }
            break;
        }
        z[n57] = new String(charArray15).intern();
        final int n61 = 15;
        final char[] charArray16 = "O\u0005nG>t\u000bhk=".toCharArray();
        int length16;
        int n63;
        final int n62 = n63 = (length16 = charArray16.length);
        int n64 = 0;
        while (true) {
            Label_1837: {
                if (n62 > 1) {
                    break Label_1837;
                }
                length16 = (n63 = n64);
                do {
                    final char c31 = charArray16[n63];
                    char c32 = '\0';
                    switch (n64 % 5) {
                        case 0: {
                            c32 = '\'';
                            break;
                        }
                        case 1: {
                            c32 = 'd';
                            break;
                        }
                        case 2: {
                            c32 = '\u001d';
                            break;
                        }
                        case 3: {
                            c32 = '\u0005';
                            break;
                        }
                        default: {
                            c32 = 'Y';
                            break;
                        }
                    }
                    charArray16[length16] = (char)(c31 ^ c32);
                    ++n64;
                } while (n62 == 0);
            }
            if (n62 > n64) {
                continue;
            }
            break;
        }
        z[n61] = new String(charArray16).intern();
        final int n65 = 16;
        final char[] charArray17 = "N\ntC0K\u0001".toCharArray();
        int length17;
        int n67;
        final int n66 = n67 = (length17 = charArray17.length);
        int n68 = 0;
        while (true) {
            Label_1953: {
                if (n66 > 1) {
                    break Label_1953;
                }
                length17 = (n67 = n68);
                do {
                    final char c33 = charArray17[n67];
                    char c34 = '\0';
                    switch (n68 % 5) {
                        case 0: {
                            c34 = '\'';
                            break;
                        }
                        case 1: {
                            c34 = 'd';
                            break;
                        }
                        case 2: {
                            c34 = '\u001d';
                            break;
                        }
                        case 3: {
                            c34 = '\u0005';
                            break;
                        }
                        default: {
                            c34 = 'Y';
                            break;
                        }
                    }
                    charArray17[length17] = (char)(c33 ^ c34);
                    ++n68;
                } while (n66 == 0);
            }
            if (n66 > n68) {
                continue;
            }
            break;
        }
        z[n65] = new String(charArray17).intern();
        final int n69 = 17;
        final char[] charArray18 = "E\u0005oG>d\u000bqj+".toCharArray();
        int length18;
        int n71;
        final int n70 = n71 = (length18 = charArray18.length);
        int n72 = 0;
        while (true) {
            Label_2069: {
                if (n70 > 1) {
                    break Label_2069;
                }
                length18 = (n71 = n72);
                do {
                    final char c35 = charArray18[n71];
                    char c36 = '\0';
                    switch (n72 % 5) {
                        case 0: {
                            c36 = '\'';
                            break;
                        }
                        case 1: {
                            c36 = 'd';
                            break;
                        }
                        case 2: {
                            c36 = '\u001d';
                            break;
                        }
                        case 3: {
                            c36 = '\u0005';
                            break;
                        }
                        default: {
                            c36 = 'Y';
                            break;
                        }
                    }
                    charArray18[length18] = (char)(c35 ^ c36);
                    ++n72;
                } while (n70 == 0);
            }
            if (n70 > n72) {
                continue;
            }
            break;
        }
        z[n69] = new String(charArray18).intern();
        final int n73 = 18;
        final char[] charArray19 = "N\u0017Tk0S\"hi5t\u0007o`<I".toCharArray();
        int length19;
        int n75;
        final int n74 = n75 = (length19 = charArray19.length);
        int n76 = 0;
        while (true) {
            Label_2185: {
                if (n74 > 1) {
                    break Label_2185;
                }
                length19 = (n75 = n76);
                do {
                    final char c37 = charArray19[n75];
                    char c38 = '\0';
                    switch (n76 % 5) {
                        case 0: {
                            c38 = '\'';
                            break;
                        }
                        case 1: {
                            c38 = 'd';
                            break;
                        }
                        case 2: {
                            c38 = '\u001d';
                            break;
                        }
                        case 3: {
                            c38 = '\u0005';
                            break;
                        }
                        default: {
                            c38 = 'Y';
                            break;
                        }
                    }
                    charArray19[length19] = (char)(c37 ^ c38);
                    ++n76;
                } while (n74 == 0);
            }
            if (n74 > n76) {
                continue;
            }
            break;
        }
        z[n73] = new String(charArray19).intern();
        final int n77 = 19;
        final char[] charArray20 = "E\u0003^j5H\u0016".toCharArray();
        int length20;
        int n79;
        final int n78 = n79 = (length20 = charArray20.length);
        int n80 = 0;
        while (true) {
            Label_2301: {
                if (n78 > 1) {
                    break Label_2301;
                }
                length20 = (n79 = n80);
                do {
                    final char c39 = charArray20[n79];
                    char c40 = '\0';
                    switch (n80 % 5) {
                        case 0: {
                            c40 = '\'';
                            break;
                        }
                        case 1: {
                            c40 = 'd';
                            break;
                        }
                        case 2: {
                            c40 = '\u001d';
                            break;
                        }
                        case 3: {
                            c40 = '\u0005';
                            break;
                        }
                        default: {
                            c40 = 'Y';
                            break;
                        }
                    }
                    charArray20[length20] = (char)(c39 ^ c40);
                    ++n80;
                } while (n78 == 0);
            }
            if (n78 > n80) {
                continue;
            }
            break;
        }
        z[n77] = new String(charArray20).intern();
        final int n81 = 20;
        final char[] charArray21 = "E\u0005oF6K\u000bo".toCharArray();
        int length21;
        int n83;
        final int n82 = n83 = (length21 = charArray21.length);
        int n84 = 0;
        while (true) {
            Label_2417: {
                if (n82 > 1) {
                    break Label_2417;
                }
                length21 = (n83 = n84);
                do {
                    final char c41 = charArray21[n83];
                    char c42 = '\0';
                    switch (n84 % 5) {
                        case 0: {
                            c42 = '\'';
                            break;
                        }
                        case 1: {
                            c42 = 'd';
                            break;
                        }
                        case 2: {
                            c42 = '\u001d';
                            break;
                        }
                        case 3: {
                            c42 = '\u0005';
                            break;
                        }
                        default: {
                            c42 = 'Y';
                            break;
                        }
                    }
                    charArray21[length21] = (char)(c41 ^ c42);
                    ++n84;
                } while (n82 == 0);
            }
            if (n82 > n84) {
                continue;
            }
            break;
        }
        z[n81] = new String(charArray21).intern();
        final int n85 = 21;
        final char[] charArray22 = "O\u0005nH6Q\rxV6R\ny".toCharArray();
        int length22;
        int n87;
        final int n86 = n87 = (length22 = charArray22.length);
        int n88 = 0;
        while (true) {
            Label_2533: {
                if (n86 > 1) {
                    break Label_2533;
                }
                length22 = (n87 = n88);
                do {
                    final char c43 = charArray22[n87];
                    char c44 = '\0';
                    switch (n88 % 5) {
                        case 0: {
                            c44 = '\'';
                            break;
                        }
                        case 1: {
                            c44 = 'd';
                            break;
                        }
                        case 2: {
                            c44 = '\u001d';
                            break;
                        }
                        case 3: {
                            c44 = '\u0005';
                            break;
                        }
                        default: {
                            c44 = 'Y';
                            break;
                        }
                    }
                    charArray22[length22] = (char)(c43 ^ c44);
                    ++n88;
                } while (n86 == 0);
            }
            if (n86 > n88) {
                continue;
            }
            break;
        }
        z[n85] = new String(charArray22).intern();
        final int n89 = 22;
        final char[] charArray23 = "|X!F,UD\\u)K\u0001i%0I\u0017id7D\u0001#;\u0004\u0007".toCharArray();
        int length23;
        int n91;
        final int n90 = n91 = (length23 = charArray23.length);
        int n92 = 0;
        while (true) {
            Label_2649: {
                if (n90 > 1) {
                    break Label_2649;
                }
                length23 = (n91 = n92);
                do {
                    final char c45 = charArray23[n91];
                    char c46 = '\0';
                    switch (n92 % 5) {
                        case 0: {
                            c46 = '\'';
                            break;
                        }
                        case 1: {
                            c46 = 'd';
                            break;
                        }
                        case 2: {
                            c46 = '\u001d';
                            break;
                        }
                        case 3: {
                            c46 = '\u0005';
                            break;
                        }
                        default: {
                            c46 = 'Y';
                            break;
                        }
                    }
                    charArray23[length23] = (char)(c45 ^ c46);
                    ++n92;
                } while (n90 == 0);
            }
            if (n90 > n92) {
                continue;
            }
            break;
        }
        z[n89] = new String(charArray23).intern();
        final int n93 = 23;
        final char[] charArray24 = "M\u0005kdwQ\u0001ov0H\n".toCharArray();
        int length24;
        int n95;
        final int n94 = n95 = (length24 = charArray24.length);
        int n96 = 0;
        while (true) {
            Label_2765: {
                if (n94 > 1) {
                    break Label_2765;
                }
                length24 = (n95 = n96);
                do {
                    final char c47 = charArray24[n95];
                    char c48 = '\0';
                    switch (n96 % 5) {
                        case 0: {
                            c48 = '\'';
                            break;
                        }
                        case 1: {
                            c48 = 'd';
                            break;
                        }
                        case 2: {
                            c48 = '\u001d';
                            break;
                        }
                        case 3: {
                            c48 = '\u0005';
                            break;
                        }
                        default: {
                            c48 = 'Y';
                            break;
                        }
                    }
                    charArray24[length24] = (char)(c47 ^ c48);
                    ++n96;
                } while (n94 == 0);
            }
            if (n94 > n96) {
                continue;
            }
            break;
        }
        z[n93] = new String(charArray24).intern();
        final int n97 = 24;
        final char[] charArray25 = "l\u0001d@/B\ni%0ID\\u)K\u0001i%".toCharArray();
        int length25;
        int n99;
        final int n98 = n99 = (length25 = charArray25.length);
        int n100 = 0;
        while (true) {
            Label_2881: {
                if (n98 > 1) {
                    break Label_2881;
                }
                length25 = (n99 = n100);
                do {
                    final char c49 = charArray25[n99];
                    char c50 = '\0';
                    switch (n100 % 5) {
                        case 0: {
                            c50 = '\'';
                            break;
                        }
                        case 1: {
                            c50 = 'd';
                            break;
                        }
                        case 2: {
                            c50 = '\u001d';
                            break;
                        }
                        case 3: {
                            c50 = '\u0005';
                            break;
                        }
                        default: {
                            c50 = 'Y';
                            break;
                        }
                    }
                    charArray25[length25] = (char)(c49 ^ c50);
                    ++n100;
                } while (n98 == 0);
            }
            if (n98 > n100) {
                continue;
            }
            break;
        }
        z[n97] = new String(charArray25).intern();
        final int n101 = 25;
        final char[] charArray26 = "B\t|l5s\u000b=4c\u0007".toCharArray();
        int length26;
        int n103;
        final int n102 = n103 = (length26 = charArray26.length);
        int n104 = 0;
        while (true) {
            Label_2997: {
                if (n102 > 1) {
                    break Label_2997;
                }
                length26 = (n103 = n104);
                do {
                    final char c51 = charArray26[n103];
                    char c52 = '\0';
                    switch (n104 % 5) {
                        case 0: {
                            c52 = '\'';
                            break;
                        }
                        case 1: {
                            c52 = 'd';
                            break;
                        }
                        case 2: {
                            c52 = '\u001d';
                            break;
                        }
                        case 3: {
                            c52 = '\u0005';
                            break;
                        }
                        default: {
                            c52 = 'Y';
                            break;
                        }
                    }
                    charArray26[length26] = (char)(c51 ^ c52);
                    ++n104;
                } while (n102 == 0);
            }
            if (n102 > n104) {
                continue;
            }
            break;
        }
        z[n101] = new String(charArray26).intern();
        final int n105 = 26;
        final char[] charArray27 = "B\t|l5s\u000b=`4W\u0010d?y".toCharArray();
        int length27;
        int n107;
        final int n106 = n107 = (length27 = charArray27.length);
        int n108 = 0;
        while (true) {
            Label_3113: {
                if (n106 > 1) {
                    break Label_3113;
                }
                length27 = (n107 = n108);
                do {
                    final char c53 = charArray27[n107];
                    char c54 = '\0';
                    switch (n108 % 5) {
                        case 0: {
                            c54 = '\'';
                            break;
                        }
                        case 1: {
                            c54 = 'd';
                            break;
                        }
                        case 2: {
                            c54 = '\u001d';
                            break;
                        }
                        case 3: {
                            c54 = '\u0005';
                            break;
                        }
                        default: {
                            c54 = 'Y';
                            break;
                        }
                    }
                    charArray27[length27] = (char)(c53 ^ c54);
                    ++n108;
                } while (n106 == 0);
            }
            if (n106 > n108) {
                continue;
            }
            break;
        }
        z[n105] = new String(charArray27).intern();
        final int n109 = 27;
        final char[] charArray28 = "B\t|l5s\u000b'%".toCharArray();
        int length28;
        int n111;
        final int n110 = n111 = (length28 = charArray28.length);
        int n112 = 0;
        while (true) {
            Label_3229: {
                if (n110 > 1) {
                    break Label_3229;
                }
                length28 = (n111 = n112);
                do {
                    final char c55 = charArray28[n111];
                    char c56 = '\0';
                    switch (n112 % 5) {
                        case 0: {
                            c56 = '\'';
                            break;
                        }
                        case 1: {
                            c56 = 'd';
                            break;
                        }
                        case 2: {
                            c56 = '\u001d';
                            break;
                        }
                        case 3: {
                            c56 = '\u0005';
                            break;
                        }
                        default: {
                            c56 = 'Y';
                            break;
                        }
                    }
                    charArray28[length28] = (char)(c55 ^ c56);
                    ++n112;
                } while (n110 == 0);
            }
            if (n110 <= n112) {
                z[n109] = new String(charArray28).intern();
                TWViewerApplet.z = z;
                return;
            }
            continue;
        }
    }
}

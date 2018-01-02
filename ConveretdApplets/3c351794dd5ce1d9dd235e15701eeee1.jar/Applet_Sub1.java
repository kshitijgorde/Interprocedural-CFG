import java.awt.Container;
import jagex3.jagmisc.jagmisc;
import java.awt.Rectangle;
import java.applet.AppletContext;
import java.net.URL;
import java.lang.reflect.Method;
import java.awt.event.FocusEvent;
import java.awt.Frame;
import java.awt.Insets;
import java.awt.MenuContainer;
import java.awt.LayoutManager;
import java.awt.Component;
import java.awt.Color;
import java.awt.event.WindowEvent;
import java.awt.Graphics;
import java.awt.event.WindowListener;
import java.awt.event.FocusListener;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public abstract class Applet_Sub1 extends Applet implements Runnable, FocusListener, WindowListener
{
    private boolean aBoolean1;
    static int anInt2;
    private boolean aBoolean3;
    public static int anInt4;
    public static boolean aBoolean5;
    public static boolean aBoolean6;
    public static int anInt7;
    public static boolean aBoolean8;
    public static int anInt9;
    public static int anInt10;
    public static int anInt11;
    public static boolean aBoolean12;
    public static int anInt13;
    public static int anInt14;
    public static int anInt15;
    public static boolean aBoolean16;
    public static boolean aBoolean17;
    public static int anInt18;
    public static int anInt19;
    public static int anInt20;
    public static int anInt21;
    public static int anInt22;
    
    @Override
    public final void update(final Graphics graphics) {
        try {
            this.paint(graphics);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ue.update(" + ((graphics != null) ? "{...}" : "null") + ')');
        }
    }
    
    final boolean method80(final int n) {
        try {
            if (n != 0) {
                this.windowActivated(null);
            }
            return Class134_Sub1.method2246("jagmisc", (byte)(-36));
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ue.P(" + n + ')');
        }
    }
    
    @Override
    public final void windowClosed(final WindowEvent windowEvent) {
    }
    
    static final int method81(int n, final byte b, int n2) {
        try {
            if (b != -9) {
                method96(null, -42, null, -65, 42, 54, -75, 7, null, false, -21, 124, 62, -66, 123);
            }
            int n3 = 0;
            while (~n < -1) {
                n3 = (n3 << -2140156575 | (0x1 & n2));
                n2 >>>= 1;
                --n;
            }
            return n3;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ue.O(" + n + ',' + b + ',' + n2 + ')');
        }
    }
    
    final boolean method82(final int n) {
        try {
            if (n != -21568) {
                this.aBoolean3 = true;
            }
            return Class134_Sub1.method2246("jaclib", (byte)(-36));
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ue.G(" + n + ')');
        }
    }
    
    @Override
    public final void destroy() {
        try {
            if (Class246_Sub3_Sub5_Sub2.anApplet_Sub1_6271 == this && !Class98_Sub10_Sub21.aBoolean5646) {
                Class180.aLong3404 = Class343.method3819(-47);
                Class246_Sub7.method3131(0, 5000L);
                Class98_Sub40.aClass88_4192 = null;
                this.method98(false, 18192);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ue.destroy()");
        }
    }
    
    @Override
    public final void windowIconified(final WindowEvent windowEvent) {
    }
    
    @Override
    public final void windowOpened(final WindowEvent windowEvent) {
    }
    
    final boolean method83(final boolean b) {
        try {
            if (!b) {
                this.aBoolean1 = false;
            }
            return Class134_Sub1.method2246("jagtheora", (byte)(-36));
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ue.C(" + b + ')');
        }
    }
    
    final void method84(final int anInt1273, final int n, final int n2, final int n3, final String s, final int n4, final int n5) {
        try {
            try {
                if (Class246_Sub3_Sub5_Sub2.anApplet_Sub1_6271 != null) {
                    ++Class98_Sub41.anInt4200;
                    if (~Class98_Sub41.anInt4200 <= -4) {
                        this.method97((byte)74, "alreadyloaded");
                    }
                    else {
                        this.getAppletContext().showDocument(this.getDocumentBase(), "_self");
                    }
                }
                else {
                    Class164.anInt1273 = anInt1273;
                    Class131.anApplet1038 = Class76_Sub11.anApplet3799;
                    Class39_Sub1.anInt3593 = n5;
                    Class188.anInt1453 = n5;
                    Class98_Sub25.anInt4024 = n;
                    Class123_Sub1.anInt4744 = n;
                    Class98_Sub46_Sub10.anInt6022 = 0;
                    if (n2 == 4) {
                        Class191.anInt1479 = 0;
                        Class246_Sub3_Sub5_Sub2.anApplet_Sub1_6271 = this;
                        Class98_Sub40.aClass88_4192 = (Class98_Sub43_Sub2.aClass88_5907 = new Class88(n4, s, n3, Class76_Sub11.anApplet3799 != null));
                        while (~Class98_Sub43_Sub2.aClass88_5907.method858(1, this, n2 - 3).anInt1163 == -1) {
                            Class246_Sub7.method3131(0, 10L);
                        }
                    }
                }
            }
            catch (Throwable t) {
                Class305_Sub1.method3585(t, -128, null);
                this.method97((byte)(-50), "crash");
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ue.E(" + anInt1273 + ',' + n + ',' + n2 + ',' + n3 + ',' + ((s != null) ? "{...}" : "null") + ',' + n4 + ',' + n5 + ')');
        }
    }
    
    @Override
    public final String getParameter(final String s) {
        try {
            if (Class284.aFrame2168 != null) {
                return null;
            }
            if (Class76_Sub11.anApplet3799 != null && Class76_Sub11.anApplet3799 != this) {
                return Class76_Sub11.anApplet3799.getParameter(s);
            }
            return super.getParameter(s);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ue.getParameter(" + ((s != null) ? "{...}" : "null") + ')');
        }
    }
    
    @Override
    public final void windowActivated(final WindowEvent windowEvent) {
    }
    
    private final void method85(final int n) {
        try {
            if (n != 6341) {
                Applet_Sub1.anInt2 = -22;
            }
            final long method3819 = Class343.method3819(-47);
            final long n2 = Class271.aLongArray2034[Class295.anInt2410];
            Class271.aLongArray2034[Class295.anInt2410] = method3819;
            if (~n2 == -1L || ~method3819 < ~n2) {}
            Class295.anInt2410 = (0x1F & 1 + Class295.anInt2410);
            synchronized (this) {
                Class4.aBoolean84 = Class153.aBoolean1228;
            }
            this.method86((byte)(-6));
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ue.S(" + n + ')');
        }
    }
    
    abstract void method86(final byte p0);
    
    synchronized void method87(final int n) {
        try {
            if (Class42_Sub3.aCanvas5361 != null) {
                Class42_Sub3.aCanvas5361.removeFocusListener(this);
                Class42_Sub3.aCanvas5361.getParent().setBackground(Color.black);
                Class42_Sub3.aCanvas5361.getParent().remove(Class42_Sub3.aCanvas5361);
            }
            MenuContainer menuContainer;
            if (Class98_Sub18.aFrame3950 != null) {
                menuContainer = Class98_Sub18.aFrame3950;
            }
            else if (Class284.aFrame2168 != null) {
                menuContainer = Class284.aFrame2168;
            }
            else if (Class76_Sub11.anApplet3799 != null) {
                menuContainer = Class76_Sub11.anApplet3799;
            }
            else {
                menuContainer = Class246_Sub3_Sub5_Sub2.anApplet_Sub1_6271;
            }
            ((Container)menuContainer).setLayout(null);
            ((Container)menuContainer).add(Class42_Sub3.aCanvas5361 = new Canvas_Sub1(this));
            Class42_Sub3.aCanvas5361.setSize(Class39_Sub1.anInt3593, Class98_Sub25.anInt4024);
            Class42_Sub3.aCanvas5361.setVisible(true);
            if (Class284.aFrame2168 != menuContainer) {
                Class42_Sub3.aCanvas5361.setLocation(Class98_Sub46_Sub10.anInt6022, Class191.anInt1479);
            }
            else {
                final Insets insets = Class284.aFrame2168.getInsets();
                Class42_Sub3.aCanvas5361.setLocation(Class98_Sub46_Sub10.anInt6022 + insets.left, Class191.anInt1479 + insets.top);
            }
            Class42_Sub3.aCanvas5361.addFocusListener(this);
            Class42_Sub3.aCanvas5361.requestFocus();
            Class4.aBoolean84 = true;
            Class153.aBoolean1228 = true;
            Class246_Sub10.aBoolean5152 = true;
            Class224_Sub2_Sub1.aBoolean6142 = false;
            if (n != 0) {
                this.aBoolean3 = true;
            }
            client.aLong3547 = Class343.method3819(-47);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ue.J(" + n + ')');
        }
    }
    
    @Override
    public final void start() {
        try {
            if (Class246_Sub3_Sub5_Sub2.anApplet_Sub1_6271 == this && !Class98_Sub10_Sub21.aBoolean5646) {
                Class180.aLong3404 = 0L;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ue.start()");
        }
    }
    
    @Override
    public final void windowDeiconified(final WindowEvent windowEvent) {
    }
    
    final void method88(final boolean b, final String s, final int n, final int n2, final int n3, final boolean b2, final int anInt1273, final int n4) {
        try {
            try {
                Class246_Sub3_Sub5_Sub2.anApplet_Sub1_6271 = this;
                Class98_Sub46_Sub10.anInt6022 = 0;
                Class98_Sub25.anInt4024 = n2;
                Class123_Sub1.anInt4744 = n2;
                Class39_Sub1.anInt3593 = n4;
                Class188.anInt1453 = n4;
                Class131.anApplet1038 = null;
                Class191.anInt1479 = 0;
                Class164.anInt1273 = anInt1273;
                (Class284.aFrame2168 = new Frame()).setTitle("Jagex");
                Class284.aFrame2168.setResizable(true);
                Class284.aFrame2168.addWindowListener(this);
                Class284.aFrame2168.setVisible(true);
                Class284.aFrame2168.toFront();
                final Insets insets = Class284.aFrame2168.getInsets();
                Class284.aFrame2168.setSize(insets.left + (Class188.anInt1453 + insets.right), insets.bottom + (Class123_Sub1.anInt4744 - -insets.top));
                Class98_Sub40.aClass88_4192 = (Class98_Sub43_Sub2.aClass88_5907 = new Class88(n3, s, n, b2));
                while (~Class98_Sub43_Sub2.aClass88_5907.method858(1, this, 1).anInt1163 == -1) {
                    Class246_Sub7.method3131(0, 10L);
                }
            }
            catch (Exception ex) {
                Class305_Sub1.method3585(ex, -128, null);
            }
        }
        catch (RuntimeException ex2) {
            throw Class64_Sub27.method667(ex2, "ue.L(" + b + ',' + ((s != null) ? "{...}" : "null") + ',' + n + ',' + n2 + ',' + n3 + ',' + b2 + ',' + anInt1273 + ',' + n4 + ')');
        }
    }
    
    @Override
    public final void focusLost(final FocusEvent focusEvent) {
        try {
            Class153.aBoolean1228 = false;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ue.focusLost(" + ((focusEvent != null) ? "{...}" : "null") + ')');
        }
    }
    
    @Override
    public final void run() {
        try {
            try {
                if (Class88.aString696 != null) {
                    final String lowerCase = Class88.aString696.toLowerCase();
                    if (lowerCase.indexOf("sun") == -1 && ~lowerCase.indexOf("apple") == 0x0) {
                        if (~lowerCase.indexOf("ibm") != 0x0 && (Class88.aString692 == null || Class88.aString692.equals("1.4.2"))) {
                            this.method97((byte)87, "wrongjava");
                            return;
                        }
                    }
                    else {
                        final String aString692 = Class88.aString692;
                        if (aString692.equals("1.1") || aString692.startsWith("1.1.") || aString692.equals("1.2") || aString692.startsWith("1.2.")) {
                            this.method97((byte)88, "wrongjava");
                            return;
                        }
                    }
                }
                if (Class88.aString692 != null && Class88.aString692.startsWith("1.")) {
                    int n = 2;
                    int n2 = 0;
                    while (~n > ~Class88.aString692.length()) {
                        final char char1 = Class88.aString692.charAt(n);
                        if (char1 < '0') {
                            break;
                        }
                        if (~char1 < -58) {
                            break;
                        }
                        n2 = 10 * n2 - ('0' + -char1);
                        ++n;
                    }
                    if (n2 >= 5) {
                        Class237.aBoolean1803 = true;
                    }
                }
                Applet applet = Class246_Sub3_Sub5_Sub2.anApplet_Sub1_6271;
                if (Class76_Sub11.anApplet3799 != null) {
                    applet = Class76_Sub11.anApplet3799;
                }
                final Method aMethod678 = Class88.aMethod678;
                if (aMethod678 != null) {
                    try {
                        aMethod678.invoke(applet, Boolean.TRUE);
                    }
                    catch (Throwable t2) {}
                }
                Class96.method927(107);
                Class85.method839(12345);
                this.method87(0);
                this.method95(-13395);
                OutputStream_Sub1.aClass240_36 = Class109.method1737(72);
                while (~Class180.aLong3404 == -1L || ~Class343.method3819(-47) > ~Class180.aLong3404) {
                    Class42_Sub1.anInt5356 = OutputStream_Sub1.aClass240_36.method2925(0, Class212.aLong1599);
                    for (int n3 = 0; Class42_Sub1.anInt5356 > n3; ++n3) {
                        this.method85(6341);
                    }
                    this.method91(784382337);
                    PlayerUpdate.method2857(Class98_Sub43_Sub2.aClass88_5907, 31668, Class42_Sub3.aCanvas5361);
                }
            }
            catch (ThreadDeath threadDeath) {
                throw threadDeath;
            }
            catch (Throwable t) {
                Class305_Sub1.method3585(t, -123, this.method94(0));
                this.method97((byte)(-88), "crash");
            }
            finally {
                this.method98(true, 18192);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ue.run()");
        }
    }
    
    final boolean method89(final int n) {
        try {
            String s = this.getDocumentBase().getHost().toLowerCase();
            if (s.equals("jagex.com") || s.endsWith(".jagex.com")) {
                return true;
            }
            if (s.equals("runescape.com") || s.endsWith(".runescape.com")) {
                return true;
            }
            if (s.equals("stellardawn.com") || s.endsWith(".stellardawn.com")) {
                return true;
            }
            if (s.endsWith("127.0.0.1")) {
                return true;
            }
            if (s.equalsIgnoreCase(RunClient.mainurl)) {
                return true;
            }
            while (s.length() > 0 && s.charAt(-1 + s.length()) >= '0' && ~s.charAt(-1 + s.length()) >= -58) {
                s = s.substring(0, -1 + s.length());
            }
            if (s.endsWith("192.168.1.")) {
                return true;
            }
            this.method97((byte)112, "invalidhost");
            return false;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ue.M(" + n + ')');
        }
    }
    
    abstract void method90(final int p0);
    
    public static final void provideLoaderApplet(final Applet anApplet3799) {
        try {
            Class76_Sub11.anApplet3799 = anApplet3799;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ue.provideLoaderApplet(" + ((anApplet3799 != null) ? "{...}" : "null") + ')');
        }
    }
    
    private final void method91(final int n) {
        try {
            final long method3819 = Class343.method3819(n - 784382384);
            final long n2 = Class89.aLongArray709[Class98_Sub46_Sub11.anInt6024];
            Class89.aLongArray709[Class98_Sub46_Sub11.anInt6024] = method3819;
            if (n != 784382337) {
                this.windowOpened(null);
            }
            Class98_Sub46_Sub11.anInt6024 = (0x1F & Class98_Sub46_Sub11.anInt6024 + 1);
            if (~n2 != -1L && method3819 > n2) {
                final int n3 = (int)(method3819 + -n2);
                Class338.anInt2842 = ((n3 >> 784382337) + 32000) / n3;
            }
            Label_0203: {
                if (~(OutputStream_Sub1.anInt32++) < -51) {
                    Class246_Sub10.aBoolean5152 = true;
                    OutputStream_Sub1.anInt32 -= 50;
                    Class42_Sub3.aCanvas5361.setSize(Class39_Sub1.anInt3593, Class98_Sub25.anInt4024);
                    Class42_Sub3.aCanvas5361.setVisible(true);
                    if (Class284.aFrame2168 == null || Class98_Sub18.aFrame3950 != null) {
                        Class42_Sub3.aCanvas5361.setLocation(Class98_Sub46_Sub10.anInt6022, Class191.anInt1479);
                        if (!client.aBoolean3553) {
                            break Label_0203;
                        }
                    }
                    final Insets insets = Class284.aFrame2168.getInsets();
                    Class42_Sub3.aCanvas5361.setLocation(insets.left + Class98_Sub46_Sub10.anInt6022, Class191.anInt1479 + insets.top);
                }
            }
            this.method93(n - 784382227);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ue.R(" + n + ')');
        }
    }
    
    abstract void method92(final boolean p0);
    
    @Override
    public final URL getDocumentBase() {
        try {
            if (Class284.aFrame2168 != null) {
                return null;
            }
            if (Class76_Sub11.anApplet3799 != null && Class76_Sub11.anApplet3799 != this) {
                return Class76_Sub11.anApplet3799.getDocumentBase();
            }
            return super.getDocumentBase();
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ue.getDocumentBase()");
        }
    }
    
    abstract void method93(final int p0);
    
    @Override
    public final AppletContext getAppletContext() {
        try {
            if (Class284.aFrame2168 != null) {
                return null;
            }
            if (Class76_Sub11.anApplet3799 != null && Class76_Sub11.anApplet3799 != this) {
                return Class76_Sub11.anApplet3799.getAppletContext();
            }
            return super.getAppletContext();
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ue.getAppletContext()");
        }
    }
    
    @Override
    public final URL getCodeBase() {
        try {
            if (Class284.aFrame2168 != null) {
                return null;
            }
            if (Class76_Sub11.anApplet3799 != null && Class76_Sub11.anApplet3799 != this) {
                return Class76_Sub11.anApplet3799.getCodeBase();
            }
            return super.getCodeBase();
        }
        catch (RuntimeException ex) {
            throw ex;
        }
    }
    
    @Override
    public final void stop() {
        try {
            if (this == Class246_Sub3_Sub5_Sub2.anApplet_Sub1_6271 && !Class98_Sub10_Sub21.aBoolean5646) {
                Class180.aLong3404 = Class343.method3819(-47) + 4000L;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ue.stop()");
        }
    }
    
    @Override
    public abstract void init();
    
    @Override
    public final void windowDeactivated(final WindowEvent windowEvent) {
    }
    
    String method94(final int n) {
        try {
            if (n != 0) {
                this.aBoolean1 = true;
            }
            return null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ue.H(" + n + ')');
        }
    }
    
    @Override
    public final void windowClosing(final WindowEvent windowEvent) {
        try {
            this.destroy();
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ue.windowClosing(" + ((windowEvent != null) ? "{...}" : "null") + ')');
        }
    }
    
    @Override
    public final synchronized void paint(final Graphics graphics) {
        try {
            if (Class246_Sub3_Sub5_Sub2.anApplet_Sub1_6271 == this && !Class98_Sub10_Sub21.aBoolean5646) {
                Class246_Sub10.aBoolean5152 = true;
                if (Class237.aBoolean1803 && -client.aLong3547 + Class343.method3819(-47) > 1000L) {
                    final Rectangle clipBounds = graphics.getClipBounds();
                    if (clipBounds == null || (~clipBounds.width <= ~Class188.anInt1453 && ~clipBounds.height <= ~Class123_Sub1.anInt4744)) {
                        Class224_Sub2_Sub1.aBoolean6142 = true;
                    }
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ue.paint(" + ((graphics != null) ? "{...}" : "null") + ')');
        }
    }
    
    abstract void method95(final int p0);
    
    static final int method96(final Class243 class243, final int n, final int[] array, final int n2, final int n3, final int n4, final int n5, final int n6, final int[] array2, final boolean b, final int n7, final int n8, final int n9, final int n10, final int n11) {
        try {
            for (int i = 0; i < 128; ++i) {
                for (int j = 0; j < 128; ++j) {
                    PlayerUpdateMask.anIntArrayArray528[i][j] = 0;
                    Class339.anIntArrayArray2846[i][j] = 99999999;
                }
            }
            boolean b2;
            if (n != 1) {
                if (~n == 0xFFFFFFFD) {
                    b2 = Class146_Sub2.method2384(class243, n5, n11, n2, n8, n3, n10, n9 ^ 0x29DDF632, n4, n7, n6);
                }
                else {
                    b2 = Class98_Sub46_Sub10.method1568(n, n5, n10, n3, n6, (byte)20, n8, n2, n11, n7, n4, class243);
                }
            }
            else {
                b2 = Class98_Sub43_Sub4.method1506(n8, 14664, n5, n10, n2, n7, class243, n4, n3, n11, n6);
            }
            final int n12 = n10 - 64;
            final int n13 = n6 - 64;
            if (n9 != 48) {
                Applet_Sub1.anInt2 = -57;
            }
            int anInt1539 = Class199.anInt1539;
            int anInt1540 = Class22.anInt217;
            if (!b2) {
                if (!b) {
                    return -1;
                }
                int n14 = Integer.MAX_VALUE;
                int n15 = Integer.MAX_VALUE;
                for (int n16 = 10, n17 = -n16 + n7; ~n17 >= ~(n7 + n16); ++n17) {
                    for (int n18 = -n16 + n4; n16 + n4 >= n18; ++n18) {
                        final int n19 = n17 - n12;
                        final int n20 = n18 - n13;
                        if (n19 >= 0 && ~n20 <= -1 && n19 < 128 && n20 < 128 && Class339.anIntArrayArray2846[n19][n20] < 100) {
                            int n21 = 0;
                            if (n7 > n17) {
                                n21 = -n17 + n7;
                            }
                            else if (n7 + (n11 - 1) < n17) {
                                n21 = -n11 + (-n7 + 1) + n17;
                            }
                            int n22 = 0;
                            if (~n4 >= ~n18) {
                                if (n18 > n5 + (n4 - 1)) {
                                    n22 = n18 + 1 + (-n5 + -n4);
                                }
                            }
                            else {
                                n22 = -n18 + n4;
                            }
                            final int n23 = n21 * n21 + n22 * n22;
                            if (n14 > n23 || (~n14 == ~n23 && Class339.anIntArrayArray2846[n19][n20] < n15)) {
                                anInt1540 = n18;
                                anInt1539 = n17;
                                n14 = n23;
                                n15 = Class339.anIntArrayArray2846[n19][n20];
                            }
                        }
                    }
                }
                if (n14 == Integer.MAX_VALUE) {
                    return -1;
                }
            }
            if (~n10 == ~anInt1539 && ~anInt1540 == ~n6) {
                return 0;
            }
            int n24 = 0;
            Class359.anIntArray3060[n24] = anInt1539;
            Class75.anIntArray580[n24++] = anInt1540;
            int n26;
            int n25 = n26 = PlayerUpdateMask.anIntArrayArray528[-n12 + anInt1539][-n13 + anInt1540];
            while (~n10 != ~anInt1539 || ~anInt1540 != ~n6) {
                if (~n25 != ~n26) {
                    n25 = n26;
                    Class359.anIntArray3060[n24] = anInt1539;
                    Class75.anIntArray580[n24++] = anInt1540;
                }
                if ((n26 & 0x2) == 0x0) {
                    if ((n26 & 0x8) != 0x0) {
                        --anInt1539;
                    }
                }
                else {
                    ++anInt1539;
                }
                if (~(n26 & 0x1) == -1) {
                    if (~(0x4 & n26) != -1) {
                        --anInt1540;
                    }
                }
                else {
                    ++anInt1540;
                }
                n26 = PlayerUpdateMask.anIntArrayArray528[-n12 + anInt1539][-n13 + anInt1540];
            }
            int n27 = 0;
            while (~(n24--) < -1) {
                array[n27] = Class359.anIntArray3060[n24];
                array2[n27++] = Class75.anIntArray580[n24];
                if (~array.length >= ~n27) {
                    break;
                }
            }
            return n27;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ue.K(" + ((class243 != null) ? "{...}" : "null") + ',' + n + ',' + ((array != null) ? "{...}" : "null") + ',' + n2 + ',' + n3 + ',' + n4 + ',' + n5 + ',' + n6 + ',' + ((array2 != null) ? "{...}" : "null") + ',' + b + ',' + n7 + ',' + n8 + ',' + n9 + ',' + n10 + ',' + n11 + ')');
        }
    }
    
    public Applet_Sub1() {
        this.aBoolean3 = false;
        this.aBoolean1 = false;
    }
    
    @Override
    public final void focusGained(final FocusEvent focusEvent) {
        try {
            Class153.aBoolean1228 = true;
            Class246_Sub10.aBoolean5152 = true;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ue.focusGained(" + ((focusEvent != null) ? "{...}" : "null") + ')');
        }
    }
    
    final void method97(final byte b, final String s) {
        try {
            if (!this.aBoolean3) {
                this.aBoolean3 = true;
                System.out.println("error_game_" + s);
                try {
                    Class203.method2704("loggedout", Class76_Sub11.anApplet3799, -26978);
                }
                catch (Throwable t) {}
                try {
                    this.getAppletContext().showDocument(new URL(this.getCodeBase(), "error_game_" + s + ".ws"), "_top");
                }
                catch (Exception ex2) {}
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ue.A(" + b + ',' + ((s != null) ? "{...}" : "null") + ')');
        }
    }
    
    private final void method98(final boolean b, final int n) {
        try {
            synchronized (this) {
                if (Class98_Sub10_Sub21.aBoolean5646) {
                    return;
                }
                if (n != 18192) {
                    return;
                }
                Class98_Sub10_Sub21.aBoolean5646 = true;
            }
            System.out.println("Shutdown start - clean:" + b);
            if (Class76_Sub11.anApplet3799 != null) {
                Class76_Sub11.anApplet3799.destroy();
            }
            try {
                this.method92(false);
            }
            catch (Exception ex2) {}
            if (this.aBoolean1) {
                try {
                    jagmisc.quit();
                }
                catch (Throwable t) {}
                this.aBoolean1 = false;
            }
            Class192.method2652(-87, true);
            Class351.method3847(-91);
            if (Class42_Sub3.aCanvas5361 != null) {
                try {
                    Class42_Sub3.aCanvas5361.removeFocusListener(this);
                    Class42_Sub3.aCanvas5361.getParent().remove(Class42_Sub3.aCanvas5361);
                }
                catch (Exception ex3) {}
            }
            if (Class98_Sub43_Sub2.aClass88_5907 != null) {
                try {
                    Class98_Sub43_Sub2.aClass88_5907.method874(103);
                }
                catch (Exception ex4) {}
            }
            this.method90(0);
            if (Class284.aFrame2168 != null) {
                Class284.aFrame2168.setVisible(false);
                Class284.aFrame2168.dispose();
                Class284.aFrame2168 = null;
            }
            System.out.println("Shutdown complete - clean:" + b);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ue.I(" + b + ',' + n + ')');
        }
    }
}

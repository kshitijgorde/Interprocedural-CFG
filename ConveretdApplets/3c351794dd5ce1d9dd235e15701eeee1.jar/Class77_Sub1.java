import java.lang.reflect.Method;
import java.awt.event.FocusEvent;
import java.awt.event.KeyEvent;
import java.awt.Component;
import java.awt.event.FocusListener;
import java.awt.event.KeyListener;

// 
// Decompiled by Procyon v0.5.30
// 

final class Class77_Sub1 extends Class77 implements KeyListener, FocusListener
{
    static Class348 aClass348_3801;
    private Class148 aClass148_3802;
    static int anInt3803;
    static int[] anIntArray3804;
    static Class326 aClass326_3805;
    private Class148 aClass148_3806;
    private Component aComponent3807;
    private boolean[] aBooleanArray3808;
    
    @Override
    public final synchronized void keyTyped(final KeyEvent keyEvent) {
        try {
            final char keyChar = keyEvent.getKeyChar();
            if (keyChar != '\0' && Class111.method2089((byte)102, keyChar)) {
                this.method786(3, -1, keyChar, false);
                keyEvent.consume();
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "oca.keyTyped(" + ((keyEvent != null) ? "{...}" : "null") + ')');
        }
    }
    
    @Override
    public final synchronized void keyReleased(final KeyEvent keyEvent) {
        try {
            this.method783((byte)61, 1, keyEvent);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "oca.keyReleased(" + ((keyEvent != null) ? "{...}" : "null") + ')');
        }
    }
    
    @Override
    public final void focusGained(final FocusEvent focusEvent) {
    }
    
    static final boolean method781(final byte b, final String s) {
        try {
            if (b != 53) {
                Class77_Sub1.anIntArray3804 = null;
            }
            return Class48_Sub1_Sub2.method468(true, 30883, 10, s);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "oca.J(" + b + ',' + ((s != null) ? "{...}" : "null") + ')');
        }
    }
    
    @Override
    final void method773(final byte b) {
        try {
            if (b <= -9) {
                this.method782(128);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "oca.B(" + b + ')');
        }
    }
    
    private final void method782(final int n) {
        try {
            if (this.aComponent3807 != null) {
                this.aComponent3807.removeKeyListener(this);
                this.aComponent3807.removeFocusListener(this);
                this.aComponent3807 = null;
                for (int i = 0; i < 112; ++i) {
                    this.aBooleanArray3808[i] = false;
                }
                if (n != 128) {
                    Class77_Sub1.aClass348_3801 = null;
                }
                this.aClass148_3802.method2422((byte)47);
                this.aClass148_3806.method2422((byte)47);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "oca.Q(" + n + ')');
        }
    }
    
    private final void method783(final byte b, final int n, final KeyEvent keyEvent) {
        try {
            final int keyCode = keyEvent.getKeyCode();
            if (b != 61) {
                this.aBooleanArray3808 = null;
            }
            int n3;
            if (keyCode != 0) {
                if (keyCode >= 0 && ~keyCode > ~Class87.anIntArray667.length) {
                    final int n2 = Class87.anIntArray667[keyCode];
                    if (n != 0 || ~(n2 & 0x80) == -1) {
                        n3 = (n2 & 0xFFFFFF7F);
                    }
                    else {
                        n3 = 0;
                    }
                }
                else {
                    n3 = 0;
                }
            }
            else {
                n3 = 0;
            }
            if (~n3 != -1) {
                this.method786(n, n3, '\0', false);
                keyEvent.consume();
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "oca.K(" + b + ',' + n + ',' + ((keyEvent != null) ? "{...}" : "null") + ')');
        }
    }
    
    private final int method784(final byte b) {
        try {
            int n = 0;
            if (this.aBooleanArray3808[81]) {
                n |= 0x1;
            }
            if (b <= 32) {
                return -81;
            }
            if (this.aBooleanArray3808[82]) {
                n |= 0x4;
            }
            if (this.aBooleanArray3808[86]) {
                n |= 0x2;
            }
            return n;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "oca.L(" + b + ')');
        }
    }
    
    @Override
    final synchronized void method774(final byte b) {
        try {
            this.aClass148_3802.method2422((byte)47);
            for (Class98_Sub8 class98_Sub8 = (Class98_Sub8)this.aClass148_3806.method2421(6494); class98_Sub8 != null; class98_Sub8 = (Class98_Sub8)this.aClass148_3806.method2421(6494)) {
                class98_Sub8.anInt3266 = this.method784((byte)127);
                if (class98_Sub8.anInt3268 != 0) {
                    if (~class98_Sub8.anInt3268 == 0xFFFFFFFE) {
                        if (this.aBooleanArray3808[class98_Sub8.anInt3269]) {
                            this.aClass148_3802.method2419(class98_Sub8, -20911);
                            this.aBooleanArray3808[class98_Sub8.anInt3269] = false;
                        }
                    }
                    else if (class98_Sub8.anInt3268 == -1) {
                        for (int anInt3269 = 0; ~anInt3269 > -113; ++anInt3269) {
                            if (this.aBooleanArray3808[anInt3269]) {
                                final Class98_Sub8 class98_Sub9 = new Class98_Sub8();
                                class98_Sub9.anInt3266 = class98_Sub8.anInt3266;
                                class98_Sub9.anInt3269 = anInt3269;
                                class98_Sub9.aLong3267 = class98_Sub8.aLong3267;
                                class98_Sub9.anInt3268 = 1;
                                class98_Sub9.aChar3265 = '\0';
                                this.aClass148_3802.method2419(class98_Sub9, -20911);
                                this.aBooleanArray3808[anInt3269] = false;
                            }
                        }
                    }
                    else if (class98_Sub8.anInt3268 == 3) {
                        this.aClass148_3802.method2419(class98_Sub8, -20911);
                    }
                }
                else {
                    if (!this.aBooleanArray3808[class98_Sub8.anInt3269]) {
                        final Class98_Sub8 class98_Sub10 = new Class98_Sub8();
                        class98_Sub10.aLong3267 = class98_Sub8.aLong3267;
                        class98_Sub10.anInt3266 = class98_Sub8.anInt3266;
                        class98_Sub10.anInt3268 = 0;
                        class98_Sub10.aChar3265 = '\0';
                        class98_Sub10.anInt3269 = class98_Sub8.anInt3269;
                        this.aClass148_3802.method2419(class98_Sub10, -20911);
                        this.aBooleanArray3808[class98_Sub8.anInt3269] = true;
                    }
                    class98_Sub8.anInt3268 = 2;
                    this.aClass148_3802.method2419(class98_Sub8, -20911);
                }
            }
            if (b > -74) {
                method789(102);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "oca.D(" + b + ')');
        }
    }
    
    public static void method785(final int n) {
        try {
            if (n != 3) {
                method785(48);
            }
            Class77_Sub1.aClass348_3801 = null;
            Class77_Sub1.anIntArray3804 = null;
            Class77_Sub1.aClass326_3805 = null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "oca.O(" + n + ')');
        }
    }
    
    @Override
    public final synchronized void focusLost(final FocusEvent focusEvent) {
        try {
            this.method786(-1, 0, '\0', false);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "oca.focusLost(" + ((focusEvent != null) ? "{...}" : "null") + ')');
        }
    }
    
    private final void method786(final int anInt3268, final int anInt3269, final char aChar3265, final boolean b) {
        try {
            final Class98_Sub8 class98_Sub8 = new Class98_Sub8();
            class98_Sub8.anInt3269 = anInt3269;
            class98_Sub8.aChar3265 = aChar3265;
            class98_Sub8.anInt3268 = anInt3268;
            class98_Sub8.aLong3267 = Class343.method3819(-47);
            if (b) {
                Class77_Sub1.aClass326_3805 = null;
            }
            this.aClass148_3806.method2419(class98_Sub8, -20911);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "oca.N(" + anInt3268 + ',' + anInt3269 + ',' + aChar3265 + ',' + b + ')');
        }
    }
    
    static final void method787(final byte b, final int n) {
        try {
            if (b <= 88) {
                method787((byte)(-39), 78);
            }
            Class246_Sub4_Sub1.aClass79_6170.method800((byte)62, n);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "oca.I(" + b + ',' + n + ')');
        }
    }
    
    private final void method788(final Component aComponent3807, final int n) {
        try {
            this.method782(128);
            this.aComponent3807 = aComponent3807;
            final Method aMethod700 = Class88.aMethod700;
            if (aMethod700 != null) {
                try {
                    aMethod700.invoke(this.aComponent3807, Boolean.FALSE);
                }
                catch (Throwable t) {}
            }
            this.aComponent3807.addKeyListener(this);
            this.aComponent3807.addFocusListener(this);
            if (n > -117) {
                this.keyReleased(null);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "oca.P(" + ((aComponent3807 != null) ? "{...}" : "null") + ',' + n + ')');
        }
    }
    
    @Override
    public final synchronized void keyPressed(final KeyEvent keyEvent) {
        try {
            this.method783((byte)61, 0, keyEvent);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "oca.keyPressed(" + ((keyEvent != null) ? "{...}" : "null") + ')');
        }
    }
    
    static final void method789(final int n) {
        try {
            for (Class98_Sub33 class98_Sub33 = (Class98_Sub33)Class191.aClass148_1478.method2418(32); class98_Sub33 != null; class98_Sub33 = (Class98_Sub33)Class191.aClass148_1478.method2417(94)) {
                if (class98_Sub33.aBoolean4124) {
                    class98_Sub33.method942(79);
                }
                else {
                    class98_Sub33.aBoolean4123 = true;
                    if (class98_Sub33.anInt4112 >= 0 && class98_Sub33.anInt4113 >= 0 && ~class98_Sub33.anInt4112 > ~Class165.anInt1276 && ~class98_Sub33.anInt4113 > ~Class98_Sub10_Sub7.anInt5572) {
                        Class98_Sub46_Sub9.method1558((byte)109, class98_Sub33);
                    }
                }
            }
            for (Class98_Sub33 class98_Sub34 = (Class98_Sub33)Class98_Sub11.aClass148_3866.method2418(32); class98_Sub34 != null; class98_Sub34 = (Class98_Sub33)Class98_Sub11.aClass148_3866.method2417(98)) {
                if (!class98_Sub34.aBoolean4124) {
                    class98_Sub34.aBoolean4123 = true;
                }
                else {
                    class98_Sub34.method942(53);
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "oca.M(" + n + ')');
        }
    }
    
    @Override
    final boolean method779(final int n, final int n2) {
        try {
            return ~n <= -1 && n < 112 && (n2 != 5503 || this.aBooleanArray3808[n]);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "oca.C(" + n + ',' + n2 + ')');
        }
    }
    
    @Override
    final Interface7 method776(final byte b) {
        try {
            if (b != 31) {
                this.aClass148_3802 = null;
            }
            return (Interface7)this.aClass148_3802.method2421(6494);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "oca.A(" + b + ')');
        }
    }
    
    Class77_Sub1(final Component component) {
        this.aClass148_3802 = new Class148();
        this.aClass148_3806 = new Class148();
        this.aBooleanArray3808 = new boolean[112];
        try {
            Class202.method2703(false);
            this.method788(component, -124);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "oca.<init>(" + ((component != null) ? "{...}" : "null") + ')');
        }
    }
    
    static {
        Class77_Sub1.anInt3803 = -2;
        Class77_Sub1.aClass348_3801 = new Class348(1, 2, 2, 0);
    }
}

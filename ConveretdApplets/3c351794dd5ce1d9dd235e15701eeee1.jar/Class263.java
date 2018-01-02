import java.awt.Component;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.MenuContainer;
import java.awt.Color;
import java.util.zip.Inflater;

// 
// Decompiled by Procyon v0.5.30
// 

final class Class263
{
    private Inflater anInflater1963;
    static OutgoingOpcode aClass171_1964;
    static int anInt1965;
    static double aDouble1966;
    static int anInt1967;
    
    static final void method3216(final int n) {
        try {
            if (Class98_Sub18.aFrame3950 == null) {
                final int anInt6022 = Class98_Sub46_Sub10.anInt6022;
                final int anInt6023 = Class191.anInt1479;
                final int n2 = Class188.anInt1453 + -Class39_Sub1.anInt3593 + -anInt6022;
                final int n3 = Class123_Sub1.anInt4744 + (-Class98_Sub25.anInt4024 + -anInt6023);
                if (n == 14059) {
                    if (anInt6022 <= 0 && n2 <= 0 && ~anInt6023 >= -1) {
                        if (n3 <= 0) {
                            return;
                        }
                    }
                    try {
                        MenuContainer menuContainer;
                        if (Class284.aFrame2168 != null) {
                            menuContainer = Class284.aFrame2168;
                        }
                        else if (Class76_Sub11.anApplet3799 != null) {
                            menuContainer = Class76_Sub11.anApplet3799;
                        }
                        else {
                            menuContainer = Class246_Sub3_Sub5_Sub2.anApplet_Sub1_6271;
                        }
                        int left = 0;
                        int top = 0;
                        if (Class284.aFrame2168 == menuContainer) {
                            final Insets insets = Class284.aFrame2168.getInsets();
                            left = insets.left;
                            top = insets.top;
                        }
                        final Graphics graphics = ((Component)menuContainer).getGraphics();
                        graphics.setColor(Color.black);
                        if (~anInt6022 < -1) {
                            graphics.fillRect(left, top, anInt6022, Class123_Sub1.anInt4744);
                        }
                        if (anInt6023 > 0) {
                            graphics.fillRect(left, top, Class188.anInt1453, anInt6023);
                        }
                        if (n2 > 0) {
                            graphics.fillRect(Class188.anInt1453 + (left + -n2), top, n2, Class123_Sub1.anInt4744);
                        }
                        if (n3 > 0) {
                            graphics.fillRect(left, -n3 + (top - -Class123_Sub1.anInt4744), Class188.anInt1453, n3);
                        }
                    }
                    catch (Exception ex2) {}
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "qha.D(" + n + ')');
        }
    }
    
    static final char method3217(final int n, final char c) {
        try {
            if (~c == 0xFFFFFF39) {
                return 'E';
            }
            if (~c == 0xFFFFFF19) {
                return 'e';
            }
            if (c == '\u00df') {
                return 's';
            }
            if (c == '\u0152') {
                return 'E';
            }
            if (~c == 0xFFFFFEAC) {
                return 'e';
            }
            if (n != 14561) {
                Class263.anInt1965 = 124;
            }
            return '\0';
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "qha.A(" + n + ',' + c + ')');
        }
    }
    
    final void method3218(final byte[] array, final Class98_Sub22 class98_Sub22, final int n) {
        try {
            if (n == 18762) {
                if (~class98_Sub22.aByteArray3992[class98_Sub22.anInt3991] != 0xFFFFFFE0 || ~class98_Sub22.aByteArray3992[class98_Sub22.anInt3991 + 1] != 0x74) {
                    throw new RuntimeException("Invalid GZIP header!");
                }
                if (this.anInflater1963 == null) {
                    this.anInflater1963 = new Inflater(true);
                }
                try {
                    this.anInflater1963.setInput(class98_Sub22.aByteArray3992, 10 + class98_Sub22.anInt3991, -18 - class98_Sub22.anInt3991 + class98_Sub22.aByteArray3992.length);
                    this.anInflater1963.inflate(array);
                }
                catch (Exception ex2) {
                    this.anInflater1963.reset();
                    throw new RuntimeException("Invalid GZIP compressed data!");
                }
                this.anInflater1963.reset();
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "qha.F(" + ((array != null) ? "{...}" : "null") + ',' + ((class98_Sub22 != null) ? "{...}" : "null") + ',' + n + ')');
        }
    }
    
    public Class263() {
        this(-1, 1000000, 1000000);
    }
    
    static final int method3219(final boolean b, final int n, final int n2, final int n3) {
        try {
            if (b) {
                Class263.aDouble1966 = 0.7636388514984377;
            }
            if (~n2 < ~n3) {
                return n2;
            }
            if (n >= n3) {
                return n3;
            }
            return n;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "qha.E(" + b + ',' + n + ',' + n2 + ',' + n3 + ')');
        }
    }
    
    final byte[] method3220(final byte b, final byte[] array) {
        try {
            final Class98_Sub22 class98_Sub22 = new Class98_Sub22(array);
            class98_Sub22.anInt3991 = -4 + array.length;
            final byte[] array2 = new byte[class98_Sub22.method1202((byte)(-63))];
            class98_Sub22.anInt3991 = 0;
            this.method3218(array2, class98_Sub22, 18762);
            if (b < 90) {
                return null;
            }
            return array2;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "qha.B(" + b + ',' + ((array != null) ? "{...}" : "null") + ')');
        }
    }
    
    private Class263(final int n, final int n2, final int n3) {
    }
    
    public static void method3221(final int n) {
        try {
            if (n > -97) {
                Class263.aDouble1966 = 0.1934401666895311;
            }
            Class263.aClass171_1964 = null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "qha.C(" + n + ')');
        }
    }
    
    static {
        Class263.aClass171_1964 = new OutgoingOpcode(60, 7);
    }
}

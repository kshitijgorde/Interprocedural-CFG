import java.awt.event.MouseEvent;
import java.awt.Component;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;

// 
// Decompiled by Procyon v0.5.30
// 

final class Class299_Sub2 extends Class299 implements MouseListener, MouseMotionListener
{
    private Class148 aClass148_5287;
    private int anInt5288;
    private int anInt5289;
    private int anInt5290;
    static byte[][] aByteArrayArray5291;
    private Class148 aClass148_5292;
    private int anInt5293;
    private int anInt5294;
    private int anInt5295;
    static OutgoingOpcode aClass171_5296;
    static Class354 aClass354_5297;
    static int anInt5298;
    private boolean aBoolean5299;
    private Component aComponent5300;
    static int[] anIntArray5301;
    
    @Override
    final boolean method3510(final byte b) {
        try {
            if (b != -19) {
                this.method3512(-127);
            }
            return ~(this.anInt5290 & 0x2) != -1;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "un.C(" + b + ')');
        }
    }
    
    @Override
    final boolean method3506(final byte b) {
        try {
            return ~(0x1 & this.anInt5290) != -1;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "un.L(" + b + ')');
        }
    }
    
    @Override
    public final synchronized void mousePressed(final MouseEvent mouseEvent) {
        try {
            final int method3528 = this.method3528(23542, mouseEvent);
            if (~method3528 == 0xFFFFFFFE) {
                this.method3529(22661, mouseEvent.getX(), mouseEvent.getClickCount(), mouseEvent.getY(), 0);
            }
            else if (~method3528 != 0xFFFFFFFB) {
                if (~method3528 == 0xFFFFFFFD) {
                    this.method3529(22661, mouseEvent.getX(), mouseEvent.getClickCount(), mouseEvent.getY(), 1);
                }
            }
            else {
                this.method3529(22661, mouseEvent.getX(), mouseEvent.getClickCount(), mouseEvent.getY(), 2);
            }
            this.anInt5294 |= method3528;
            if (mouseEvent.isPopupTrigger()) {
                mouseEvent.consume();
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "un.mousePressed(" + ((mouseEvent != null) ? "{...}" : "null") + ')');
        }
    }
    
    @Override
    final boolean method3512(final int n) {
        try {
            return n == 1 && ~(0x4 & this.anInt5290) != -1;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "un.I(" + n + ')');
        }
    }
    
    @Override
    final void method3515(final int n) {
        try {
            if (n < -110) {
                this.method3530((byte)119);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "un.H(" + n + ')');
        }
    }
    
    @Override
    public final synchronized void mouseClicked(final MouseEvent mouseEvent) {
        try {
            if (mouseEvent.isPopupTrigger()) {
                mouseEvent.consume();
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "un.mouseClicked(" + ((mouseEvent != null) ? "{...}" : "null") + ')');
        }
    }
    
    @Override
    public final synchronized void mouseMoved(final MouseEvent mouseEvent) {
        try {
            this.method3531(-22490, mouseEvent.getX(), mouseEvent.getY());
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "un.mouseMoved(" + ((mouseEvent != null) ? "{...}" : "null") + ')');
        }
    }
    
    @Override
    public final synchronized void mouseExited(final MouseEvent mouseEvent) {
        try {
            this.method3531(-22490, mouseEvent.getX(), mouseEvent.getY());
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "un.mouseExited(" + ((mouseEvent != null) ? "{...}" : "null") + ')');
        }
    }
    
    public static void method3522(final boolean b) {
        try {
            Class299_Sub2.aByteArrayArray5291 = null;
            Class299_Sub2.aClass354_5297 = null;
            if (b) {
                method3526(-120, -17);
            }
            Class299_Sub2.anIntArray5301 = null;
            Class299_Sub2.aClass171_5296 = null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "un.S(" + b + ')');
        }
    }
    
    static final void method3523(final int n, final int n2, final int n3) {
        try {
            if (~Class257.anInt1948 != -1) {
                if (n2 < 0) {
                    for (int n4 = 0; ~n4 > -17; ++n4) {
                        Class77_Sub1.anIntArray3804[n4] = n;
                    }
                }
                else {
                    Class77_Sub1.anIntArray3804[n2] = n;
                }
            }
            Class366.aClass98_Sub31_Sub2_3122.method1363(-17, n2, n);
            if (n3 != 0) {
                method3527(35, -66);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "un.O(" + n + ',' + n2 + ',' + n3 + ')');
        }
    }
    
    static final Class154 method3524(final byte b, final Class246_Sub3_Sub4 aClass246_Sub3_Sub4_1232) {
        try {
            if (b != -90) {
                return null;
            }
            Class154 aClass154_4718 = null;
            Label_0057: {
                if (Class119_Sub1.aClass154_4718 == null) {
                    aClass154_4718 = new Class154();
                    if (!client.aBoolean3553) {
                        break Label_0057;
                    }
                }
                aClass154_4718 = Class119_Sub1.aClass154_4718;
                Class119_Sub1.aClass154_4718 = Class119_Sub1.aClass154_4718.aClass154_1233;
                --Class76_Sub8.anInt3766;
                aClass154_4718.aClass154_1233 = null;
            }
            aClass154_4718.aClass246_Sub3_Sub4_1232 = aClass246_Sub3_Sub4_1232;
            return aClass154_4718;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "un.N(" + b + ',' + ((aClass246_Sub3_Sub4_1232 != null) ? "{...}" : "null") + ')');
        }
    }
    
    private final void method3525(final int n, final Component aComponent5300) {
        try {
            this.method3530((byte)(-40));
            (this.aComponent5300 = aComponent5300).addMouseListener(this);
            this.aComponent5300.addMouseMotionListener(this);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "un.R(" + n + ',' + ((aComponent5300 != null) ? "{...}" : "null") + ')');
        }
    }
    
    @Override
    final int method3514(final int n) {
        try {
            if (n <= 4) {
                return 121;
            }
            return this.anInt5289;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "un.E(" + n + ')');
        }
    }
    
    @Override
    final Class98_Sub17 method3508(final int n) {
        try {
            if (n != 600) {
                this.aClass148_5292 = null;
            }
            return (Class98_Sub17)this.aClass148_5287.method2421(6494);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "un.G(" + n + ')');
        }
    }
    
    static final boolean method3526(final int n, final int n2) {
        try {
            return n >= 113 && (n2 == 13 || n2 == 23 || ~n2 == 0xFFFFFFFD || n2 == 30 || n2 == 18 || (n2 == 58 || ~n2 == 0xFFFFFC0F));
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "un.M(" + n + ',' + n2 + ')');
        }
    }
    
    @Override
    public final synchronized void mouseDragged(final MouseEvent mouseEvent) {
        try {
            this.method3531(-22490, mouseEvent.getX(), mouseEvent.getY());
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "un.mouseDragged(" + ((mouseEvent != null) ? "{...}" : "null") + ')');
        }
    }
    
    static final int method3527(final int n, final int n2) {
        try {
            if (n2 != 770356935) {
                Class299_Sub2.aClass354_5297 = null;
            }
            return n >>> 770356935;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "un.V(" + n + ',' + n2 + ')');
        }
    }
    
    private final int method3528(final int n, final MouseEvent mouseEvent) {
        try {
            final int modifiers = mouseEvent.getModifiers();
            final boolean b = (modifiers & 0x10) != 0x0;
            int n2 = ((0x8 & modifiers) != 0x0) ? 1 : 0;
            final boolean b2 = (0x4 & modifiers) != 0x0;
            if (n2 != 0 && (b || b2)) {
                n2 = 0;
            }
            if (b && b2) {
                return 4;
            }
            if (b) {
                return 1;
            }
            if (n != 23542) {
                this.anInt5290 = -41;
            }
            if (n2 != 0) {
                return 2;
            }
            if (b2) {
                return 4;
            }
            return 0;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "un.Q(" + n + ',' + ((mouseEvent != null) ? "{...}" : "null") + ')');
        }
    }
    
    @Override
    final synchronized void method3516(final byte b) {
        try {
            this.anInt5288 = this.anInt5293;
            this.anInt5290 = this.anInt5294;
            this.anInt5289 = this.anInt5295;
            final Class148 aClass148_5287 = this.aClass148_5287;
            this.aClass148_5287 = this.aClass148_5292;
            this.aClass148_5292 = aClass148_5287;
            if (b <= 103) {
                this.anInt5290 = 55;
            }
            this.aClass148_5292.method2422((byte)47);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "un.A(" + b + ')');
        }
    }
    
    @Override
    final int method3507(final byte b) {
        try {
            if (b < 24) {
                this.mouseExited(null);
            }
            return this.anInt5288;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "un.B(" + b + ')');
        }
    }
    
    private final void method3529(final int n, final int anInt5775, final int anInt5776, final int anInt5777, final int anInt5778) {
        try {
            final Class98_Sub17_Sub1 class98_Sub17_Sub1 = new Class98_Sub17_Sub1();
            class98_Sub17_Sub1.anInt5774 = anInt5776;
            class98_Sub17_Sub1.anInt5779 = anInt5778;
            class98_Sub17_Sub1.anInt5775 = anInt5775;
            class98_Sub17_Sub1.anInt5776 = anInt5777;
            class98_Sub17_Sub1.aLong5777 = Class343.method3819(n ^ 0xFFFFA754);
            if (n != 22661) {
                this.aClass148_5287 = null;
            }
            this.aClass148_5292.method2419(class98_Sub17_Sub1, n ^ 0xFFFFF6D4);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "un.U(" + n + ',' + anInt5775 + ',' + anInt5776 + ',' + anInt5777 + ',' + anInt5778 + ')');
        }
    }
    
    @Override
    public final synchronized void mouseEntered(final MouseEvent mouseEvent) {
        try {
            this.method3531(-22490, mouseEvent.getX(), mouseEvent.getY());
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "un.mouseEntered(" + ((mouseEvent != null) ? "{...}" : "null") + ')');
        }
    }
    
    @Override
    public final synchronized void mouseReleased(final MouseEvent mouseEvent) {
        try {
            int n = this.method3528(23542, mouseEvent);
            if (~(n & this.anInt5294) == -1) {
                n = this.anInt5294;
            }
            if ((0x1 & n) != 0x0) {
                this.method3529(22661, mouseEvent.getX(), mouseEvent.getClickCount(), mouseEvent.getY(), 3);
            }
            if ((0x4 & n) != 0x0) {
                this.method3529(22661, mouseEvent.getX(), mouseEvent.getClickCount(), mouseEvent.getY(), 5);
            }
            if (~(0x2 & n) != -1) {
                this.method3529(22661, mouseEvent.getX(), mouseEvent.getClickCount(), mouseEvent.getY(), 4);
            }
            this.anInt5294 &= ~n;
            if (mouseEvent.isPopupTrigger()) {
                mouseEvent.consume();
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "un.mouseReleased(" + ((mouseEvent != null) ? "{...}" : "null") + ')');
        }
    }
    
    private final void method3530(final byte b) {
        try {
            if (this.aComponent5300 != null) {
                this.aComponent5300.removeMouseMotionListener(this);
                this.aComponent5300.removeMouseListener(this);
                final boolean anInt5295 = false;
                this.anInt5294 = (anInt5295 ? 1 : 0);
                this.anInt5293 = (anInt5295 ? 1 : 0);
                this.anInt5295 = (anInt5295 ? 1 : 0);
                final boolean anInt5296 = false;
                this.anInt5290 = (anInt5296 ? 1 : 0);
                this.anInt5288 = (anInt5296 ? 1 : 0);
                this.anInt5289 = (anInt5296 ? 1 : 0);
                this.aComponent5300 = null;
                this.aClass148_5287 = null;
                this.aClass148_5292 = null;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "un.T(" + b + ')');
        }
    }
    
    Class299_Sub2(final Component component, final boolean aBoolean5299) {
        this.aClass148_5287 = new Class148();
        this.aClass148_5292 = new Class148();
        try {
            this.method3525(-32, component);
            this.aBoolean5299 = aBoolean5299;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "un.<init>(" + ((component != null) ? "{...}" : "null") + ',' + aBoolean5299 + ')');
        }
    }
    
    private final void method3531(final int n, final int anInt5295, final int anInt5296) {
        try {
            if (n != -22490) {
                this.method3525(-122, null);
            }
            this.anInt5293 = anInt5296;
            this.anInt5295 = anInt5295;
            if (this.aBoolean5299) {
                this.method3529(22661, anInt5295, 0, anInt5296, -1);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "un.P(" + n + ',' + anInt5295 + ',' + anInt5296 + ')');
        }
    }
    
    static {
        Class299_Sub2.aClass171_5296 = new OutgoingOpcode(72, 3);
        Class299_Sub2.anInt5298 = 16777215;
        Class299_Sub2.anIntArray5301 = new int[] { 1, 2, 4, 8 };
    }
}

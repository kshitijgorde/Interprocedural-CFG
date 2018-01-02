import java.awt.Component;
import javax.accessibility.Accessible;

// 
// Decompiled by Procyon v0.5.30
// 

final class Class47 implements Runnable
{
    private boolean aBoolean397;
    private Interface10 anInterface10_398;
    static Class113 aClass113_399;
    private volatile boolean aBoolean400;
    private int anInt401;
    private Interface10 anInterface10_402;
    private String aString403;
    private int anInt404;
    private long aLong405;
    private long aLong406;
    static int anInt407;
    private Class75 aClass75_408;
    
    final int method440(final int n) {
        try {
            if (n >= -113) {
                return 34;
            }
            return this.anInt404;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "dga.A(" + n + ')');
        }
    }
    
    final synchronized boolean method441(final byte b) {
        try {
            return this.anInterface10_398.method28(-105, this.aLong406);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "dga.C(" + b + ')');
        }
    }
    
    final Class75 method442(final byte b) {
        try {
            if (b != 54) {
                this.method441((byte)(-74));
            }
            return this.aClass75_408;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "dga.F(" + b + ')');
        }
    }
    
    final String method443(final byte b) {
        try {
            if (b != -46) {
                this.method447(66);
            }
            return this.aString403;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "dga.E(" + b + ')');
        }
    }
    
    final synchronized void method444(final byte b, final Interface10 anInterface10_398) {
        try {
            if (b >= -17) {
                this.anInterface10_398 = null;
            }
            this.anInterface10_402 = this.anInterface10_398;
            this.anInterface10_398 = anInterface10_398;
            this.aLong406 = Class343.method3819(-47);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "dga.L(" + b + ',' + ((anInterface10_398 != null) ? "{...}" : "null") + ')');
        }
    }
    
    final long method445(final byte b) {
        try {
            return this.aLong405;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "dga.I(" + b + ')');
        }
    }
    
    @Override
    public final void run() {
        try {
            while (!this.aBoolean400) {
                final long method3819 = Class343.method3819(-47);
                synchronized (this) {
                    try {
                        ++this.anInt401;
                        if (!(this.anInterface10_398 instanceof Class292)) {
                            final long method3820 = Class343.method3819(-47);
                            if (Class265.aHa1974 == null || this.anInterface10_402 == null || this.anInterface10_402.method25(-24591) == 0 || ~this.aLong406 > ~(-this.anInterface10_402.method25(-24591) + method3820)) {
                                if (this.anInterface10_402 != null) {
                                    this.aBoolean397 = true;
                                    this.anInterface10_402.method24(-26363);
                                    this.anInterface10_402 = null;
                                }
                                if (this.aBoolean397) {
                                    Class263.method3216(14059);
                                    if (Class265.aHa1974 != null) {
                                        Class265.aHa1974.GA(0);
                                    }
                                }
                                this.anInterface10_398.method23(32210, this.aBoolean397 || (Class265.aHa1974 != null && Class265.aHa1974.method1819()));
                            }
                            else {
                                final int n = (int)((method3820 - this.aLong406) * 255L / this.anInterface10_402.method25(-24591));
                                final int n2 = -n + 255;
                                Class263.method3216(14059);
                                final int n3 = 0xFFFFFF | n2 << 511614744;
                                final int n4 = 0xFFFFFF | n << -1078525736;
                                Class265.aHa1974.GA(0);
                                final Class332 method3821 = Class265.aHa1974.method1739(Class39_Sub1.anInt3593, Class98_Sub25.anInt4024, true);
                                Class265.aHa1974.method1763(-74, method3821);
                                this.anInterface10_402.method23(32210, true);
                                Class265.aHa1974.method1776();
                                method3821.method3748(0, 0, 0, n3, 1);
                                Class265.aHa1974.method1763(-123, method3821);
                                Class265.aHa1974.GA(0);
                                this.anInterface10_398.method23(32210, true);
                                Class265.aHa1974.method1776();
                                method3821.method3748(0, 0, 0, n4, 1);
                            }
                            try {
                                if (Class265.aHa1974 != null && !(this.anInterface10_398 instanceof Class292)) {
                                    Class265.aHa1974.method1754(-128);
                                }
                            }
                            catch (Exception_Sub1 exception_Sub1) {
                                Class305_Sub1.method3585(exception_Sub1, -121, exception_Sub1.getMessage() + " (Recovered) " + Class315.aClient3529.method94(0));
                                Class76_Sub4.method754(0, true, 57);
                            }
                        }
                        else {
                            this.anInterface10_398.method23(32210, this.aBoolean397);
                        }
                        Accessible accessible;
                        if (Class284.aFrame2168 == null) {
                            if (Class76_Sub11.anApplet3799 == null) {
                                accessible = Class246_Sub3_Sub5_Sub2.anApplet_Sub1_6271;
                            }
                            else {
                                accessible = Class76_Sub11.anApplet3799;
                            }
                        }
                        else {
                            accessible = Class284.aFrame2168;
                        }
                        ((Component)accessible).getSize();
                        ((Component)accessible).getSize();
                        if (Class284.aFrame2168 == accessible) {
                            Class284.aFrame2168.getInsets();
                        }
                        this.aBoolean397 = false;
                        if (Class265.aHa1974 != null && !(this.anInterface10_398 instanceof Class292) && ~this.aClass75_408.method736((byte)(-10)) > ~Class75.aClass75_577.method736((byte)(-10))) {
                            Class98_Sub31_Sub2.method1336((byte)(-90));
                        }
                    }
                    catch (Exception ex2) {
                        continue;
                    }
                }
                final int n5 = (int)(20L + method3819 + -Class343.method3819(-47));
                if (~n5 < -1) {
                    Class246_Sub7.method3131(0, n5);
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "dga.run()");
        }
    }
    
    final synchronized void method446(final long aLong405, final byte b, final int anInt404, final Class75 aClass75_408, final String aString403) {
        try {
            this.aLong405 = aLong405;
            this.aClass75_408 = aClass75_408;
            this.anInt404 = anInt404;
            this.aString403 = aString403;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "dga.G(" + aLong405 + ',' + b + ',' + anInt404 + ',' + ((aClass75_408 != null) ? "{...}" : "null") + ',' + ((aString403 != null) ? "{...}" : "null") + ')');
        }
    }
    
    final int method447(final int n) {
        try {
            if (n != 6119) {
                this.anInt401 = -126;
            }
            if (this.aClass75_408 == null) {
                return 0;
            }
            final int method736 = this.aClass75_408.method736((byte)(-10));
            if (this.aClass75_408.aBoolean553 && ~this.anInt404 > ~this.aClass75_408.anInt557) {
                return this.anInt404 + 1;
            }
            if (~method736 > -1 || method736 >= -1 + Class98_Sub10_Sub1.aClass75Array5542.length) {
                return 100;
            }
            if (~this.anInt404 == ~this.aClass75_408.anInt552) {
                return this.aClass75_408.anInt557;
            }
            return this.aClass75_408.anInt552;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "dga.B(" + n + ')');
        }
    }
    
    final void method448(final boolean b) {
        try {
            this.aBoolean400 = true;
            if (b) {
                return;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "dga.M(" + b + ')');
        }
    }
    
    public static void method449(final int n) {
        try {
            Class47.aClass113_399 = null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "dga.H(" + n + ')');
        }
    }
    
    final int method450(final byte b) {
        try {
            if (b != 3) {
                return -56;
            }
            return this.anInt401;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "dga.D(" + b + ')');
        }
    }
    
    static final Class259[] method451(final int n) {
        try {
            if (n < 118) {
                Class47.aClass113_399 = null;
            }
            if (PlayerUpdateMask.aClass259Array527 == null) {
                final Class259[] method490 = Class52.method490(Class98_Sub43_Sub2.aClass88_5907, (byte)(-68));
                final Class259[] array = new Class259[method490.length];
                int i = 0;
                final int method491 = Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub16_4040.method614((byte)123);
            Label_0258:
                for (int n2 = 0; method490.length > n2; ++n2) {
                    final Class259 class259 = method490[n2];
                    if ((~class259.anInt1955 >= -1 || ~class259.anInt1955 <= -25) && class259.anInt1953 >= 800 && ~class259.anInt1956 <= -601 && (~method491 != 0xFFFFFFFD || (class259.anInt1953 <= 800 && class259.anInt1956 <= 600)) && (~method491 != 0xFFFFFFFE || (class259.anInt1953 <= 1024 && ~class259.anInt1956 >= -769))) {
                        int n3 = 0;
                        while (i > n3) {
                            final Class259 class260 = array[n3];
                            if (~class260.anInt1953 == ~class259.anInt1953 && ~class260.anInt1956 == ~class259.anInt1956) {
                                if (class259.anInt1955 > class260.anInt1955) {
                                    array[n3] = class259;
                                }
                                continue Label_0258;
                            }
                            else {
                                ++n3;
                            }
                        }
                        array[i] = class259;
                        ++i;
                    }
                }
                Class236.method2892(array, 0, PlayerUpdateMask.aClass259Array527 = new Class259[i], 0, i);
                final int[] array2 = new int[PlayerUpdateMask.aClass259Array527.length];
                for (int n4 = 0; ~n4 > ~PlayerUpdateMask.aClass259Array527.length; ++n4) {
                    final Class259 class261 = PlayerUpdateMask.aClass259Array527[n4];
                    array2[n4] = class261.anInt1956 * class261.anInt1953;
                }
                Class98_Sub46_Sub13_Sub1.method1597(array2, PlayerUpdateMask.aClass259Array527, 0);
            }
            return PlayerUpdateMask.aClass259Array527;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "dga.J(" + n + ')');
        }
    }
    
    final synchronized void method452(final int n) {
        try {
            this.aBoolean397 = true;
            if (n != -7423) {
                this.method442((byte)77);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "dga.K(" + n + ')');
        }
    }
    
    public Class47() {
        this.anInterface10_398 = new Class292();
        this.anInterface10_402 = null;
    }
    
    static {
        Class47.aClass113_399 = new Class113(1, 2);
        Class47.anInt407 = 0;
    }
}

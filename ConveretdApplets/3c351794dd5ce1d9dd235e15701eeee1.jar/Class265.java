import jagex3.jagmisc.jagmisc;
import java.net.InetAddress;

// 
// Decompiled by Procyon v0.5.30
// 

final class Class265 implements Runnable
{
    static short aShort1973;
    static ha aHa1974;
    static int anInt1975;
    private Thread aThread1976;
    static short[] aShortArray1977;
    static String aString1978;
    static Class293 aClass293_1979;
    private Class148 aClass148_1980;
    static int[] anIntArray1981;
    static Class98_Sub49 aClass98_Sub49_1982;
    static int anInt1983;
    
    private final void method3228(final int n, final Class98 class98) {
        try {
            synchronized (this.aClass148_1980) {
                this.aClass148_1980.method2419(class98, n - 20911);
                this.aClass148_1980.notify();
            }
            if (n != 0) {
                method3231(-48);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "qia.A(" + n + ',' + ((class98 != null) ? "{...}" : "null") + ')');
        }
    }
    
    @Override
    public final void run() {
        try {
            while (true) {
                final Class98_Sub4 class98_Sub4;
                synchronized (this.aClass148_1980) {
                    Class98 class98;
                    for (class98 = this.aClass148_1980.method2421(6494); class98 == null; class98 = this.aClass148_1980.method2421(6494)) {
                        try {
                            this.aClass148_1980.wait();
                        }
                        catch (InterruptedException ex2) {}
                    }
                    if (!(class98 instanceof Class98_Sub4)) {
                        break;
                    }
                    class98_Sub4 = (Class98_Sub4)class98;
                }
                int ping;
                try {
                    final byte[] address = InetAddress.getByName(class98_Sub4.aString3829).getAddress();
                    ping = jagmisc.ping(address[0], address[1], address[2], address[3], 1000L);
                }
                catch (Throwable t) {
                    ping = 1000;
                }
                class98_Sub4.anInt3827 = ping;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "qia.run()");
        }
    }
    
    final Class98_Sub4 method3229(final int n, final String s) {
        try {
            if (n <= 43) {
                return null;
            }
            if (this.aThread1976 == null) {
                throw new IllegalStateException("");
            }
            if (s == null) {
                throw new IllegalArgumentException("");
            }
            final Class98_Sub4 class98_Sub4 = new Class98_Sub4(s);
            this.method3228(0, class98_Sub4);
            return class98_Sub4;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "qia.D(" + n + ',' + ((s != null) ? "{...}" : "null") + ')');
        }
    }
    
    static final boolean method3230(final int n, final int n2) {
        try {
            return ~n2 == -1 || n2 == 1 || n2 == 2;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "qia.E(" + n + ',' + n2 + ')');
        }
    }
    
    public static void method3231(final int n) {
        try {
            Class265.aClass293_1979 = null;
            Class265.anIntArray1981 = null;
            Class265.aClass98_Sub49_1982 = null;
            Class265.aString1978 = null;
            Class265.aHa1974 = null;
            Class265.aShortArray1977 = null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "qia.C(" + n + ')');
        }
    }
    
    public Class265() {
        this.aClass148_1980 = new Class148();
        try {
            (this.aThread1976 = new Thread(this)).setDaemon(true);
            this.aThread1976.start();
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "qia.<init>()");
        }
    }
    
    final void method3232(final byte b) {
        try {
            if (this.aThread1976 != null) {
                this.method3228(0, new Class98());
                if (b == -103) {
                    try {
                        this.aThread1976.join();
                    }
                    catch (InterruptedException ex2) {}
                    this.aThread1976 = null;
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "qia.B(" + b + ')');
        }
    }
    
    static {
        Class265.aString1978 = null;
        Class265.aShort1973 = 256;
        Class265.aShortArray1977 = new short[256];
        Class265.aClass293_1979 = null;
        Class265.anIntArray1981 = new int[14];
        Class265.anInt1983 = 0;
        Class265.aClass98_Sub49_1982 = new Class98_Sub49(0, -1);
    }
}

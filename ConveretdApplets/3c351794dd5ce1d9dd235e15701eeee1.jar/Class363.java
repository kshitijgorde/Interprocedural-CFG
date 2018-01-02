import java.net.MalformedURLException;
import java.net.URL;
import java.io.IOException;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.DataInputStream;

// 
// Decompiled by Procyon v0.5.30
// 

final class Class363 implements Runnable
{
    private Class368[] aClass368Array3094;
    static int anInt3095;
    private Class143 aClass143_3096;
    private Thread aThread3097;
    static int anInt3098;
    static int anInt3099;
    private volatile boolean aBoolean3100;
    
    @Override
    public final void run() {
        try {
            try {
                final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader((InputStream)this.aClass143_3096.anObject1162));
                String s = bufferedReader.readLine();
                final Class267 method1010 = Class98_Sub10_Sub2.method1010(120);
                while (s != null) {
                    method1010.method3245(s, 0);
                    s = bufferedReader.readLine();
                }
                final String[] method1011 = method1010.method3244(20780);
                if (~(method1011.length % 3) != -1) {
                    return;
                }
                this.aClass368Array3094 = new Class368[method1011.length / 3];
                for (int n = 0; ~n > ~method1011.length; n += 3) {
                    this.aClass368Array3094[n / 3] = new Class368(method1011[n], method1011[n + 1], method1011[2 + n]);
                }
            }
            catch (IOException ex2) {}
            this.aBoolean3100 = true;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "vv.run()");
        }
    }
    
    final boolean method3928(final int n) {
        try {
            if (n != -1) {
                return false;
            }
            if (this.aBoolean3100) {
                return true;
            }
            if (this.aClass143_3096 == null) {
                try {
                    this.aClass143_3096 = Class98_Sub43_Sub2.aClass88_5907.method866(-106, new URL("http://" + Class98_Sub46_Sub17.aClass354_6050.aString3016 + ":" + ((Class64_Sub29.aClass196_3720 == Class43.aClass196_375) ? 80 : (7000 + Class98_Sub46_Sub17.aClass354_6050.anInt3011)) + "/news.ws?game=" + Class4.aClass279_86.anInt2095));
                }
                catch (MalformedURLException ex2) {
                    return true;
                }
            }
            if (this.aClass143_3096 == null || this.aClass143_3096.anInt1163 == 2) {
                return true;
            }
            if (~this.aClass143_3096.anInt1163 != 0xFFFFFFFE) {
                return false;
            }
            if (this.aThread3097 == null) {
                (this.aThread3097 = new Thread(this)).start();
            }
            return this.aBoolean3100;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "vv.B(" + n + ')');
        }
    }
    
    final Class368 method3929(final int n, final int n2) {
        try {
            if (n != 23885) {
                return null;
            }
            if (this.aClass368Array3094 == null || n2 < 0 || n2 >= this.aClass368Array3094.length) {
                return null;
            }
            return this.aClass368Array3094[n2];
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "vv.C(" + n + ',' + n2 + ')');
        }
    }
    
    static final void method3930(final boolean b, final byte b2, final Class246_Sub3_Sub4_Sub2_Sub2 class246_Sub3_Sub4_Sub2_Sub2, final int n, final int n2) {
        try {
            final int n3 = class246_Sub3_Sub4_Sub2_Sub2.anIntArray6437[0];
            final int n4 = class246_Sub3_Sub4_Sub2_Sub2.anIntArray6438[0];
            if (~n3 <= -1 && ~n3 > ~Class165.anInt1276 && ~n4 <= -1 && ~n4 > ~Class98_Sub10_Sub7.anInt5572 && ~n2 <= -1 && ~n2 > ~Class165.anInt1276 && ~n <= -1 && ~n > ~Class98_Sub10_Sub7.anInt5572) {
                final int method96 = Applet_Sub1.method96(Class167.aClass243Array1281[class246_Sub3_Sub4_Sub2_Sub2.aByte5088], class246_Sub3_Sub4_Sub2_Sub2.method3034(0), Class76_Sub5.anIntArray3743, 0, 0, n, 0, n4, Class117.anIntArray974, true, n2, -4, 48, n3, 0);
                if (~method96 <= -2 && method96 <= 3) {
                    if (b) {
                        Class363.anInt3098 = 115;
                    }
                    for (int n5 = 0; -1 + method96 > n5; ++n5) {
                        class246_Sub3_Sub4_Sub2_Sub2.method3056(b2, 1, Class117.anIntArray974[n5], Class76_Sub5.anIntArray3743[n5]);
                    }
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "vv.A(" + b + ',' + b2 + ',' + ((class246_Sub3_Sub4_Sub2_Sub2 != null) ? "{...}" : "null") + ',' + n + ',' + n2 + ')');
        }
    }
    
    static {
        Class363.anInt3099 = 0;
    }
}

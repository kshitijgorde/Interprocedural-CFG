import java.util.Locale;
import java.security.NoSuchAlgorithmException;
import java.security.MessageDigest;
import java.io.PrintStream;
import java.util.Date;
import java.text.SimpleDateFormat;

// 
// Decompiled by Procyon v0.5.30
// 

public final class e
{
    static boolean a;
    static boolean b;
    static boolean c;
    static boolean d;
    static boolean e;
    static VoipApplet f;
    public static SimpleDateFormat g;
    
    private static String a(final Date date) {
        return e.g.format(date);
    }
    
    private static String a() {
        return a(new Date());
    }
    
    public static void a(final VoipApplet f) {
        e.f = f;
    }
    
    public static void a(final String s, final String s2) {
        if (!e.e) {
            return;
        }
        e.f.a.append("LOG  \t" + s + "\t" + s2.replace('\n', ' ').trim() + "\n");
        System.out.println("LOG  \t" + a() + "\t" + s + "\t" + s2.replace('\n', ' ').trim());
    }
    
    public static void b(final String s, final String s2) {
        if (!e.b) {
            return;
        }
        e.f.a.append("DEBUG\t" + s + "\t" + s2.replace('\n', ' ').trim() + "\n");
        System.out.println("DEBUG\t" + a() + "\t" + s + "\t" + s2.replace('\n', ' ').trim());
    }
    
    public static void c(final String s, final String s2) {
        if (!e.a) {
            return;
        }
        e.f.a.append("ERROR\t" + s + "\t" + s2.replace('\n', ' ').trim() + "\n");
        System.out.println("ERROR\t" + a() + "\t" + s + "\t" + s2.replace('\n', ' ').trim());
    }
    
    public static void d(final String s, final String s2) {
        if (!s.startsWith("SIP") || !e.d) {
            if (!e.c) {
                return;
            }
        }
        final String[] split;
        PrintStream printStream;
        StringBuilder sb;
        String trim;
        if ((split = s2.split("\n")).length > 1) {
            e.f.a.append("PACKT\t" + s + "\t" + "-----------BEGIN\n");
            System.out.println("PACKT\t" + a() + "\t" + s + "\t" + "-----------BEGIN");
            for (int i = 0; i < split.length; ++i) {
                e.f.a.append("PACKT\t" + s + "\t" + split[i].trim() + "\n");
                System.out.println("PACKT\t" + a() + "\t" + s + "\t" + split[i].trim());
            }
            e.f.a.append("PACKT\t" + s + "\t" + "-----------END\n");
            printStream = System.out;
            sb = new StringBuilder().append("PACKT\t").append(a()).append("\t").append(s).append("\t");
            trim = "-----------END";
        }
        else {
            e.f.a.append("PACKT\t" + s + "\t" + s2.trim() + "\n");
            printStream = System.out;
            sb = new StringBuilder().append("PACKT\t").append(a()).append("\t").append(s).append("\t");
            trim = s2.trim();
        }
        printStream.println(sb.append(trim).toString());
    }
    
    public static String a(final String s) {
        final StringBuffer sb = new StringBuffer();
        final byte[] bytes = s.getBytes();
        try {
            final MessageDigest instance;
            (instance = MessageDigest.getInstance("MD5")).reset();
            instance.update(bytes);
            final byte[] digest = instance.digest();
            for (int i = 0; i < digest.length; ++i) {
                final String hexString;
                if ((hexString = Integer.toHexString(0xFF & digest[i])).length() == 1) {
                    sb.append('0');
                }
                sb.append(hexString);
            }
        }
        catch (NoSuchAlgorithmException ex) {}
        return sb.toString();
    }
    
    public static long b(final String s) {
        final String[] split;
        if ((split = s.split("\\.")).length != 4) {
            return 0L;
        }
        return (Long.parseLong(split[0]) << 24) + (Long.parseLong(split[1]) << 16) + (Long.parseLong(split[2]) << 8) + Long.parseLong(split[3]);
    }
    
    public static String a(long n) {
        String s = null;
        for (int i = 1; i <= 4; ++i) {
            final long n2 = n % 256L;
            n /= 256L;
            s = ((i == 1) ? new Long(n2).toString() : (new Long(n2).toString() + "." + s));
        }
        return s;
    }
    
    static {
        e.a = true;
        e.b = true;
        e.c = false;
        e.d = false;
        e.e = true;
        new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
        e.g = new SimpleDateFormat("EEE', 'dd' 'MMM' 'yyyy' 'HH:mm:ss' 'Z", Locale.US);
    }
}

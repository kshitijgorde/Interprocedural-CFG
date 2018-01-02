import java.util.StringTokenizer;
import java.io.IOException;
import java.io.DataInputStream;
import java.net.URL;
import java.util.Hashtable;

// 
// Decompiled by Procyon v0.5.30
// 

public class u
{
    static String oa = "\ua752\ua763\ua763\ua77f\ua776\ua767\ua733\ua77b\ua772\ua760\ua733\ua772\ua733\ua763\ua761\ua77c\ua771\ua77f\ua776\ua77e\ua73f\ua733\ua770\ua77c\ua77d\ua767\ua772\ua770\ua767\ua733\ua767\ua77b\ua776\ua733\ua764\ua776\ua771\ua77e\ua772\ua760\ua767\ua776\ua761";
    static String pa = "\ua75f\ua77c\ua772\ua777\ua77a\ua77d\ua774\ua73f\ua733\ua763\ua77f\ua776\ua772\ua760\ua776\ua733\ua764\ua772\ua77a\ua767\ua73d\ua73d\ua73d";
    static String qa = "\ua752\ua77d\ua733\ua776\ua761\ua761\ua77c\ua761\ua733\ua77b\ua772\ua760\ua733\ua77c\ua770\ua770\ua766\ua761\ua776\ua777";
    private Hashtable ra;
    private static String d = "";
    private static String e = "\ua73c\ua73c";
    private static String f = "\ua72e";
    private static String g = "\ua761\ua776\ua772\ua777\ua77a\ua77d\ua774\ua733\ua775\ua761\ua77c\ua77e\ua733";
    private static String h = "\ua733\ua77b\ua772\ua760\ua733\ua77d\ua77c\ua767\ua733\ua760\ua766\ua770\ua770\ua776\ua776\ua777\ua776\ua777";
    private static String i = "\ua760\ua767\ua772\ua767\ua766\ua760\ua771\ua74c\ua77e\ua760\ua774";
    private static String j = "\ua752\ua763\ua763\ua77f\ua776\ua767\ua733\ua77b\ua772\ua760\ua733\ua772\ua733\ua763\ua761\ua77c\ua771\ua77f\ua776\ua77e\ua73f\ua733\ua770\ua77c\ua77d\ua767\ua772\ua770\ua767\ua733\ua767\ua77b\ua776\ua733\ua764\ua776\ua771\ua77e\ua772\ua760\ua767\ua776\ua761";
    private static String v = "\ua77f\ua77c\ua772\ua777\ua77a\ua77d\ua774\ua74c\ua77e\ua760\ua774";
    private static String w = "\ua75f\ua77c\ua772\ua777\ua77a\ua77d\ua774\ua73f\ua733\ua763\ua77f\ua776\ua772\ua760\ua776\ua733\ua764\ua772\ua77a\ua767\ua73d\ua73d\ua73d";
    private static String x = "\ua77f\ua77c\ua772\ua777\ua776\ua761\ua761\ua74c\ua77e\ua760\ua774";
    private static String y = "\ua752\ua77d\ua733\ua776\ua761\ua761\ua77c\ua761\ua733\ua77b\ua772\ua760\ua733\ua77c\ua770\ua770\ua766\ua761\ua776\ua777";
    private static String z = "\ua737";
    private static String A = "\ua733";
    
    public u(final URL url, final String s) {
        this.ra = new Hashtable();
        try {
            String line;
            while ((line = new DataInputStream(new URL(url, s).openStream()).readLine()) != null) {
                final String trim = line.trim();
                if (!trim.equals(u.d)) {
                    if (trim.startsWith(u.e)) {
                        continue;
                    }
                    final int index = trim.indexOf(u.f);
                    if (index == -1) {
                        continue;
                    }
                    this.ra.put(trim.substring(0, index).toLowerCase().trim(), trim.substring(index + 1).replace('\"', ' ').trim());
                }
            }
        }
        catch (IOException ex) {
            System.out.println(u.g + s + u.h);
        }
    }
    
    public String a() {
        String j = this.ra.get(u.i);
        if (j == null) {
            j = u.j;
        }
        return j;
    }
    
    public String b() {
        String w = this.ra.get(u.v);
        if (w == null) {
            w = u.w;
        }
        return w;
    }
    
    public String _() {
        String y = this.ra.get(u.x);
        if (y == null) {
            y = u.y;
        }
        return y;
    }
    
    public String j(final String s) {
        String d = this.ra.get(s);
        if (d == null) {
            d = u.d;
        }
        return d;
    }
    
    public String _(final String s, final String[] array) {
        final String s2 = this.ra.get(s);
        if (s2 != null) {
            final StringTokenizer stringTokenizer = new StringTokenizer(s2);
            final StringBuffer sb = new StringBuffer();
            int n = 0;
            while (stringTokenizer.hasMoreTokens() && n < array.length) {
                final String nextToken = stringTokenizer.nextToken();
                if (nextToken.startsWith(u.z)) {
                    sb.append(array[n++]);
                }
                else {
                    sb.append(nextToken);
                }
                sb.append(u.A);
            }
            sb.setLength(sb.length() - 1);
            return sb.toString();
        }
        return u.d;
    }
    
    private static String a(final String s) {
        final int length = s.length();
        final char[] array = new char[length];
        for (int i = 0; i < length; ++i) {
            array[i] = (char)(s.charAt(i) ^ 0xFFFFA713);
        }
        return new String(array);
    }
    
    static {
        u.oa = a(u.oa);
        u.pa = a(u.pa);
        u.qa = a(u.qa);
        u.d = a(u.d);
        u.e = a(u.e);
        u.f = a(u.f);
        u.g = a(u.g);
        u.h = a(u.h);
        u.i = a(u.i);
        u.j = a(u.j);
        u.v = a(u.v);
        u.w = a(u.w);
        u.x = a(u.x);
        u.y = a(u.y);
        u.z = a(u.z);
        u.A = a(u.A);
    }
}

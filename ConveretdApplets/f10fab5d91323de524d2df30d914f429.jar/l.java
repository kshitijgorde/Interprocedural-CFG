import java.util.Vector;
import java.util.StringTokenizer;
import java.security.MessageDigest;

// 
// Decompiled by Procyon v0.5.30
// 

class l
{
    static String a(final String s) throws Exception {
        final String s2 = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyzABC";
        final byte[] bytes = s2.concat(s2).concat(s2).getBytes();
        final MessageDigest instance;
        (instance = MessageDigest.getInstance("MD5")).update(s.getBytes());
        final byte[] digest = instance.digest();
        String concat = "";
        for (int n = digest.length - 1; -1 < n; --n) {
            concat = concat.concat(new String(bytes, digest[n] & 0x7F, 1));
        }
        return concat;
    }
    
    public static int a(final String s, final int n) {
        if (s == null) {
            return n;
        }
        int int1 = -1;
        try {
            int1 = Integer.parseInt(s);
        }
        catch (Exception ex) {}
        if (int1 == -1) {
            return n;
        }
        return int1;
    }
    
    public static long a(final String s, final long n) {
        if (s == null) {
            return n;
        }
        long long1 = -1L;
        try {
            long1 = Long.parseLong(s);
        }
        catch (Exception ex) {}
        if (long1 == -1L) {
            return n;
        }
        return long1;
    }
    
    public static int b(String trim, final int n) {
        if (trim == null) {
            return n;
        }
        trim = trim.replace('#', ' ').trim();
        int int1 = -1;
        try {
            int1 = Integer.parseInt(trim, 16);
        }
        catch (Exception ex) {}
        if (int1 == -1) {
            return n;
        }
        return int1;
    }
    
    public static String[] a(final String s, final String s2) {
        final boolean a = d.a;
        String[] array = { "" };
        String s3 = s;
        if (!a) {
            if (s == null) {
                return array;
            }
            s3 = s2;
        }
        if (s3 != null) {
            final StringTokenizer stringTokenizer = new StringTokenizer(s, s2);
            final Vector vector = new Vector<String>();
            while (true) {
                Label_0081: {
                    if (!a) {
                        break Label_0081;
                    }
                    final String trim = stringTokenizer.nextToken().trim();
                    if (trim.length() != 0) {
                        vector.addElement(trim);
                    }
                }
                stringTokenizer.hasMoreTokens();
                int i = 0;
                while (i == 0) {
                    final int size = vector.size();
                    int n;
                    i = (n = size);
                    if (!a && !a) {
                        if (!a) {
                            if (i == 0) {
                                return array;
                            }
                            array = new String[size];
                            n = 0;
                        }
                        int n2 = n;
                        while (true) {
                            Label_0148: {
                                if (!a) {
                                    break Label_0148;
                                }
                                final String[] array2;
                                array2[n2] = vector.elementAt(n2);
                                ++n2;
                            }
                            if (n2 < size) {
                                continue;
                            }
                            final String[] array2 = array;
                            if (!a) {
                                return array2;
                            }
                            continue;
                        }
                    }
                }
                continue;
            }
        }
        return array;
    }
}

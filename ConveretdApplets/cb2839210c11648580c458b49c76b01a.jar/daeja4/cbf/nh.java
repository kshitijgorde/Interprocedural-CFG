// 
// Decompiled by Procyon v0.5.30
// 

package daeja4.cbf;

import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import java.util.Date;
import java.io.IOException;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ByteArrayInputStream;

public class nh
{
    public static final int a(final byte[] array, final int n, final int n2) {
        if (n2 > 2) {
            throw new IllegalArgumentException("Cannot convert more than two bytes into a integer");
        }
        return (int)b(array, n, n2);
    }
    
    public static final int a(final byte[] array, final int n, final int n2, final String s) {
        final long b = b(array, n, n2);
        if (b == 4294967295L) {
            return -1;
        }
        if (b > Integer.MAX_VALUE) {
            throw new IllegalStateException(String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(s))).append(" out of range :  ").append(b).append(", max is ").append(Integer.MAX_VALUE))));
        }
        return (int)b;
    }
    
    public static final long b(final byte[] array, final int n, final int n2) {
        if (n2 > 4) {
            throw new IllegalArgumentException("Cannot convert more than four bytes into a long");
        }
        if (n2 == 0) {
            throw new IllegalArgumentException("Cannot convert zero bytes");
        }
        int n3 = 0;
        long n4 = 0L;
        for (int i = n; i < n + n2; ++i) {
            n4 |= (array[i] & 0xFF) << n3;
            n3 += 8;
        }
        return n4;
    }
    
    public static final char[] c(final byte[] array, final int n, final int n2) {
        try {
            final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(array);
            final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(byteArrayInputStream));
            bufferedReader.skip(n);
            final char[] array2 = new char[n2];
            bufferedReader.read(array2);
            byteArrayInputStream.close();
            return array2;
        }
        catch (IOException ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }
    
    public static final String d(final byte[] array, final int n, final int n2) {
        return new String(c(array, n, n2));
    }
    
    public static final String e(final byte[] array, final int n, final int n2) {
        final StringBuffer sb = new StringBuffer();
        for (int i = 0; i < n2; ++i) {
            if (i == 4 || i == 6 || i == 8 || i == 10) {
                sb.append("-");
            }
            final String hexString = Integer.toHexString(a(array, n + i, 1));
            if (hexString.length() == 1) {
                sb.append("0");
            }
            sb.append(hexString);
        }
        return sb.toString().toUpperCase();
    }
    
    public static final Date f(final byte[] array, final int n, final int n2) {
        Date time = null;
        final int a = a(array, n, 1);
        final int a2 = a(array, n + 1, 1);
        if (a != 0 || a2 != 0) {
            final Calendar instance = Calendar.getInstance();
            instance.setTimeInMillis(0L);
            instance.add(13, a);
            instance.add(12, a2);
            time = instance.getTime();
        }
        return time;
    }
    
    public static final String g(final byte[] array, final int n, final int n2) {
        try {
            return new String(array, n, n2, "UTF-16BE");
        }
        catch (UnsupportedEncodingException ex) {
            throw new RuntimeException(ex);
        }
    }
    
    public static final String h(final byte[] array, final int n, final int n2) {
        try {
            return new String(array, n, n2, "UTF-16LE");
        }
        catch (UnsupportedEncodingException ex) {
            throw new RuntimeException(ex);
        }
    }
    
    public static final StringBuffer a(final StringBuffer sb, final long n) {
        if (n == 4294967294L) {
            sb.append("ENDOFCHAIN");
        }
        else if (n == 4294967293L) {
            sb.append("FATSECT");
        }
        else if (n == 4294967292L) {
            sb.append("DIFFSECT");
        }
        else if (n == 4294967295L) {
            sb.append("FREESECT");
        }
        else {
            sb.append(Long.toString(n, 10));
        }
        return sb;
    }
    
    public static final StringBuffer b(final StringBuffer sb, final long n) {
        return sb.append(a(n));
    }
    
    public static final String a(final long n) {
        String value;
        if (n == 4294967294L) {
            value = "ENDOFCHAIN";
        }
        else if (n == 4294967293L) {
            value = "FATSECT";
        }
        else if (n == 4294967292L) {
            value = "DIFFSECT";
        }
        else if (n == 4294967295L) {
            value = "FREESECT";
        }
        else {
            value = String.valueOf(String.valueOf(new StringBuffer("0x").append(Long.toString(n, 16)).append(": ").append(Long.toString(n, 10))));
        }
        return value;
    }
    
    public static final void b(final long n) {
        if (n == 4294967295L) {
            return;
        }
        if (n > Integer.MAX_VALUE) {
            throw new IllegalStateException("Integer overflow");
        }
    }
}

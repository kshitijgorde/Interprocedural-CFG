// 
// Decompiled by Procyon v0.5.30
// 

package doook;

import java.io.UnsupportedEncodingException;
import java.io.DataInput;
import java.io.DataOutput;

public class r
{
    private byte[] a;
    
    public void a(final DataOutput dataOutput) {
        if (this.a == null) {
            dataOutput.writeInt(0);
        }
        else {
            dataOutput.writeInt(this.a.length);
            dataOutput.write(this.a);
        }
    }
    
    public boolean b() {
        return this.a == null;
    }
    
    public String toString() {
        if (this.a == null) {
            return "";
        }
        final int n = (this.a.length + 1) / 2;
        final char[] array = new char[n];
        char c = (char)(this.a[this.a.length - 1] + 48);
        for (int i = n - 1; i > 0; --i) {
            short n2 = this.a[2 * i - 1];
            short n3 = this.a[2 * i - 2];
            if (n2 < 0) {
                n2 += 256;
            }
            if (n3 < 0) {
                n3 += 256;
            }
            array[i] = c;
            c = (char)((n2 + (n3 << 8)) / c);
        }
        array[0] = c;
        return d(new String(array));
    }
    
    public boolean equals(final Object o) {
        if (o instanceof String) {
            return o.toString().equals(this.toString());
        }
        if (o instanceof r) {
            final byte[] a = ((r)o).a();
            if (this.a == a) {
                return true;
            }
            if (this.a != null && a != null && a.length == this.a.length) {
                for (int i = 0; i < a.length; ++i) {
                    if (a[i] != this.a[i]) {
                        return false;
                    }
                }
                return true;
            }
        }
        return false;
    }
    
    public byte[] a() {
        return this.a;
    }
    
    public r(final DataInput dataInput) {
        final int int1 = dataInput.readInt();
        if (int1 != 0) {
            dataInput.readFully(this.a = new byte[int1]);
        }
        else {
            this.a = null;
        }
    }
    
    public r(String c) {
        if (((c != null) ? c.length() : 0) == 0) {
            this.a = null;
        }
        else {
            c = c(c);
            final int length = c.length();
            this.a = new byte[2 * length - 1];
            for (int i = 0; i < length - 1; ++i) {
                final char c2 = (char)((c.charAt(i) & '\u00ff') * (c.charAt(i + 1) & '\u00ff'));
                this.a[2 * i + 1] = (byte)(c2 & '\u00ff');
                this.a[2 * i] = (byte)(c2 >> 8 & '\u00ff');
            }
            this.a[2 * length - 2] = (byte)((c.charAt(length - 1) - '0') % 'K');
        }
    }
    
    public r() {
    }
    
    public static String c(final String s) {
        try {
            final byte[] bytes = s.getBytes("UTF8");
            final StringBuffer sb = new StringBuffer(bytes.length * 2);
            for (int i = 0; i < bytes.length; ++i) {
                final String upperCase = Integer.toHexString(bytes[i] & 0xFF).toUpperCase();
                if (upperCase.length() == 1) {
                    sb.append("0");
                }
                sb.append(upperCase);
            }
            return sb.toString();
        }
        catch (UnsupportedEncodingException ex) {
            return "";
        }
    }
    
    public static String d(final String s) {
        final byte[] array = new byte[s.length() / 2];
        for (int i = 0; i < array.length; ++i) {
            array[i] = (byte)Integer.parseInt(s.substring(i * 2, i * 2 + 2), 16);
        }
        try {
            return new String(array, "UTF8");
        }
        catch (UnsupportedEncodingException ex) {
            return "";
        }
    }
}
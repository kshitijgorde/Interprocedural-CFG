// 
// Decompiled by Procyon v0.5.30
// 

package c;

import webphone.aw;

public class f
{
    boolean for;
    aw int;
    boolean if;
    private h char;
    private c try;
    private int new;
    private int byte;
    private int do;
    byte[] a;
    byte[] case;
    
    public f(final aw int1) {
        this.for = true;
        this.int = null;
        this.if = false;
        this.char = null;
        this.try = null;
        this.new = 50;
        this.byte = 480;
        this.do = 30;
        this.a = new byte[480];
        this.case = new byte[50];
        this.int = int1;
    }
    
    public boolean a(final boolean b) {
        this.do = this.int.b5;
        return this.a(b, this.do);
    }
    
    public boolean a(final boolean for1, final int do1) {
        try {
            this.do = do1;
            this.for = for1;
            if (this.for) {
                this.try = new c(this.do);
            }
            else {
                this.char = new h(this.do, 0);
            }
            if (this.do == 20) {
                this.new = g.aB;
                this.byte = 320;
            }
            else {
                this.new = g.aF;
                this.byte = 480;
            }
            return this.if = true;
        }
        catch (Exception ex) {
            this.int.a(3, "ilbcinit", ex);
            return false;
        }
    }
    
    public int if(final byte[] array, final int n, final byte[] array2) {
        try {
            if (!this.if) {
                this.int.a(3, "ERROR,ilbc using unitialized codec  on encode");
                return 0;
            }
            if (!this.for) {
                this.int.a(3, "ERROR,ilbc using encoder on decoder codec");
                return 0;
            }
            if (n < 160 || n > 9900) {
                this.int.a(3, "ERROR,invalid pcm to ilbc packet length " + this.int.c(n));
                return 0;
            }
            if (n % this.byte != 0) {
                if (n % 480 == 0) {
                    this.int.a(3, "WARNING,ilbc reinit1");
                    this.a(this.for, 30);
                }
                else if (n % 320 == 0) {
                    this.int.a(3, "WARNING,ilbc reinit2");
                    this.a(this.for, 20);
                }
            }
            if (this.int.eK >= 4 && n % this.byte != 0) {
                this.int.a(4, "ERROR,invalid pcm to ilbc packet length inner0 " + this.int.c(n) + " should be " + this.int.c(this.byte));
            }
            if (n < this.byte + this.byte / 2) {
                final int for1 = this.for(array, n, array2);
                if (this.int.eK >= 4 && for1 != this.new) {
                    this.int.a(4, "ERROR,invalid pcm to ilbc packet length inner1 " + this.int.c(for1));
                }
                return for1;
            }
            int n2 = 0;
            for (int n3 = 0; n3 + this.byte <= n; n3 += this.byte) {
                System.arraycopy(array, n3, this.a, 0, this.byte);
                final int for2 = this.for(this.a, this.byte, this.case);
                if (this.int.eK >= 4 && for2 != this.new) {
                    this.int.a(4, "ERROR,invalid pcm to ilbc packet length inner2 " + this.int.c(for2));
                }
                else {
                    System.arraycopy(this.case, 0, array2, n2, this.new);
                    n2 += this.new;
                }
            }
            return n2;
        }
        catch (Exception ex) {
            this.int.a(3, "ilbcencode", ex);
            return 0;
        }
    }
    
    public int for(final byte[] array, final int n, final byte[] array2) {
        final short[] a = a(array, 0, n, true);
        final short[] array3 = new short[this.new / 2];
        this.try.a(array3, a);
        a(array3, array2, false);
        return this.new;
    }
    
    public int do(final byte[] array, final int n, final byte[] array2) {
        try {
            if (!this.if) {
                this.int.a(3, "ERROR,ilbc using unitialized codec  on decode");
                return 0;
            }
            if (this.for) {
                this.int.a(3, "ERROR,ilbc using decode on encoder codec");
                return 0;
            }
            if (n < 19 || n > 9900) {
                this.int.a(3, "ERROR,invalid ilbc tp pcm packet length " + this.int.c(n));
                return 0;
            }
            if (n % this.new != 0) {
                if (n % 50 == 0) {
                    this.int.a(3, "WARNING,ilbc reinit3");
                    this.a(this.for, 30);
                }
                else if (n % 38 == 0) {
                    this.int.a(3, "WARNING,ilbc reinit4");
                    this.a(this.for, 20);
                }
            }
            if (this.int.eK >= 4 && n % this.new != 0) {
                this.int.a(4, "ERROR,invalid ilbc tp pcm  packet length inner0 " + this.int.c(n) + " should be " + this.int.c(this.new));
            }
            if (n < this.new + this.new / 2) {
                final int a = this.a(array, n, array2);
                if (this.int.eK >= 4 && a != this.byte) {
                    this.int.a(4, "ERROR,invalid ilbc tp pcm  packet length inner1 " + this.int.c(a));
                }
                return a;
            }
            int n2 = 0;
            for (int n3 = 0; n3 + this.new <= n; n3 += this.new) {
                System.arraycopy(array, n3, this.a, 0, this.new);
                final int a2 = this.a(this.a, this.new, this.case);
                if (this.int.eK >= 4 && a2 != this.byte) {
                    this.int.a(4, "ERROR,invalid ilbc tp pcm  packet length inner2 " + this.int.c(a2));
                }
                else {
                    System.arraycopy(this.case, 0, array2, n2, this.byte);
                    n2 += this.byte;
                }
            }
            return n2;
        }
        catch (Exception ex) {
            this.int.a(3, "ilbcdecode", ex);
            return 0;
        }
    }
    
    public int a(final byte[] array, final int n, final byte[] array2) {
        try {
            final short[] a = a(array, 0, n, false);
            final short[] array3 = new short[this.char.char.new];
            this.char.a(array3, a, (short)1);
            a(array3, array2, true);
            return this.char.char.new * 2;
        }
        catch (Exception ex) {
            this.int.a(3, "ilbcdecode", ex);
            return 0;
        }
    }
    
    public static byte[] a(final short n, final boolean b) {
        final byte[] array = new byte[2];
        if (b) {
            array[0] = (byte)(n & 0xFF);
            array[1] = (byte)(n >>> 8 & 0xFF);
        }
        else {
            array[0] = (byte)(n >>> 8 & 0xFF);
            array[1] = (byte)(n & 0xFF);
        }
        return array;
    }
    
    public static void a(final short[] array, final byte[] array2, final boolean b) {
        int n = 0;
        for (int i = 0; i < array.length; ++i) {
            final byte[] a = a(array[i], b);
            array2[n++] = a[0];
            array2[n++] = a[1];
        }
    }
    
    public static short[] a(final byte[] array, final int n, final int n2, final boolean b) throws ArrayIndexOutOfBoundsException {
        if (0 < n2 && n + n2 <= array.length) {
            final int n3 = n2 / 2;
            final short[] array2 = new short[n3];
            int n4 = n;
            for (int i = 0; i < n3; ++i) {
                int n5;
                if (b) {
                    n5 = ((array[n4++] & 0xFF) | (0xFF00 & array[n4++] << 8));
                }
                else {
                    n5 = (array[n4++] << 8 | (0xFF & array[n4++]));
                }
                array2[i] = (short)n5;
            }
            return array2;
        }
        throw new ArrayIndexOutOfBoundsException("offset: " + n + ", length: " + n2 + ", array length: " + array.length);
    }
}

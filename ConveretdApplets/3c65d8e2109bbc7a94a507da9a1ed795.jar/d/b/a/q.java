// 
// Decompiled by Procyon v0.5.30
// 

package d.b.a;

import java.io.StreamCorruptedException;

public class q
{
    public static final String case = "Java Speex Decoder v0.9.7 ($Revision: 1.4 $)";
    private int try;
    private int for;
    private float[] int;
    private short[] new;
    private int do;
    private ad byte;
    private aa a;
    private int if;
    
    public q() {
        this.byte = new ad();
        this.try = 0;
        this.for = 0;
    }
    
    public boolean a(final int n, final int try1, final int for1, final boolean b) {
        switch (n) {
            case 0: {
                this.a = new j();
                ((j)this.a).for();
                break;
            }
            case 1: {
                this.a = new f();
                ((f)this.a).goto();
                break;
            }
            case 2: {
                this.a = new f();
                ((f)this.a).char();
                break;
            }
            default: {
                return false;
            }
        }
        this.a.a(b);
        this.if = this.a.try();
        this.try = try1;
        this.for = for1;
        final int n2 = try1 * for1;
        this.int = new float[n2 * 2];
        this.new = new short[n2 * 2];
        this.do = 0;
        this.byte.for();
        return true;
    }
    
    public int if() {
        return this.try;
    }
    
    public int do() {
        return this.for;
    }
    
    public int a(final byte[] array, final int n) {
        if (this.do <= 0) {
            return this.do;
        }
        for (int i = 0; i < this.do; ++i) {
            final int n2 = n + (i << 1);
            array[n2] = (byte)(this.new[i] & 0xFF);
            array[n2 + 1] = (byte)(this.new[i] >> 8 & 0xFF);
        }
        final int n3 = this.do * 2;
        this.do = 0;
        return n3;
    }
    
    public int a(final short[] array, final int n) {
        if (this.do <= 0) {
            return this.do;
        }
        System.arraycopy(this.new, 0, array, n, this.do);
        final int do1 = this.do;
        this.do = 0;
        return do1;
    }
    
    public int a() {
        return this.do * 2;
    }
    
    public void a(final byte[] array, final int n, final int n2) throws StreamCorruptedException {
        if (array == null) {
            this.a(true);
        }
        else {
            this.byte.a(array, n, n2);
            this.a(false);
        }
    }
    
    public void a(final boolean b) throws StreamCorruptedException {
        if (b) {
            this.a.a(null, this.int);
        }
        else {
            this.a.a(this.byte, this.int);
        }
        if (this.for == 2) {
            this.a.a(this.int, this.if);
        }
        for (int i = 0; i < this.if * this.for; ++i) {
            if (this.int[i] > 32767.0f) {
                this.int[i] = 32767.0f;
            }
            else if (this.int[i] < -32768.0f) {
                this.int[i] = -32768.0f;
            }
        }
        for (int j = 0; j < this.if * this.for; ++j, ++this.do) {
            this.new[this.do] = ((this.int[j] > 0.0f) ? ((short)(this.int[j] + 0.5)) : ((short)(this.int[j] - 0.5)));
        }
    }
}

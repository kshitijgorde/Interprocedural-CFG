// 
// Decompiled by Procyon v0.5.30
// 

package a;

public final class cL
{
    private final int q;
    private final int w;
    private final int e;
    private int[] q;
    private int[] w;
    private char[] q;
    private int r;
    private int t;
    private byte[] q;
    private char[] w;
    private int y;
    private int u;
    private char[] e;
    private int i;
    private int o;
    
    public cL() {
        this.q = 32768;
        this.w = 32768;
        this.e = 32768;
        this.q = new byte[32768];
        this.w = new char[32768];
        this.e = new char[32768];
    }
    
    public final void q(final char[] q) {
        this.q = q;
        this.w = new int[q.length];
        this.q = new int[q.length];
        this.r = 0;
        this.t = 0;
        for (int i = 0; i < this.q.length; ++i) {
            this.q[i] = 0;
        }
        this.y = 0;
        this.i = 0;
        this.o = 0;
        this.q(this.u = 0, 0);
    }
    
    private int q(final int n, final int n2) {
        for (int i = n; i < this.q.length; ++i) {
            if (this.q[i] == '[') {
                i = this.q(i + 1, n2 + 1) + 1;
            }
            else if (this.q[i] == ']') {
                if (n2 == 0) {
                    System.err.println("ERROR: " + new String(this.q));
                    throw new Exception("Unopened braket.");
                }
                this.w[i] = n - 1;
                return this.q[n - 1] = i;
            }
        }
        if (n2 > 0) {
            System.err.println("ERROR: " + new String(this.q));
            throw new Exception("Unclosed braket.");
        }
        return -239;
    }
    
    public final byte[] q() {
        char[] array3;
        char[] array2;
        if (this.u < this.y) {
            final char[] array = new char[this.y - this.u];
            for (int i = 0; i < array.length; ++i) {
                array[i] = this.w[this.u];
                ++this.u;
            }
            array2 = (array3 = array);
        }
        else {
            array2 = (array3 = null);
        }
        final char[] array4 = array3;
        if (array2 == null) {
            return null;
        }
        final byte[] array5 = new byte[array4.length];
        for (int j = 0; j < array4.length; ++j) {
            array5[j] = (byte)array4[j];
        }
        return array5;
    }
    
    public final boolean q() {
    Label_0349:
        while (this.r <= this.q.length - 1) {
            switch (this.q[this.r]) {
                case '+': {
                    final byte[] q = this.q;
                    final int t = this.t;
                    ++q[t];
                    ++this.r;
                    continue;
                }
                case '-': {
                    final byte[] q2 = this.q;
                    final int t2 = this.t;
                    --q2[t2];
                    ++this.r;
                    continue;
                }
                case '<': {
                    this.t = (this.t - 1 + 32768) % 32768;
                    ++this.r;
                    continue;
                }
                case '>': {
                    this.t = (this.t + 1) % 32768;
                    ++this.r;
                    continue;
                }
                case '[': {
                    this.q();
                    continue;
                }
                case ']': {
                    this.r = this.w[this.r];
                    this.q();
                    continue;
                }
                case ',': {
                    boolean b;
                    if (this.o >= 0) {
                        b = true;
                    }
                    else {
                        this.q[this.t] = (byte)this.e[this.o];
                        ++this.o;
                        ++this.r;
                        b = false;
                    }
                    if (b) {
                        break Label_0349;
                    }
                    continue;
                }
                case '.': {
                    this.w[this.y] = (char)this.q[this.t];
                    ++this.y;
                    ++this.r;
                    continue;
                }
            }
        }
        return this.r != this.q.length;
    }
    
    private void q() {
        if (this.q[this.t] == 0) {
            this.r = this.q[this.r] + 1;
            return;
        }
        ++this.r;
    }
}

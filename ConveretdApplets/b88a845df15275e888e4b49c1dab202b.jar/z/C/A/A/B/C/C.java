// 
// Decompiled by Procyon v0.5.30
// 

package z.C.A.A.B.C;

import java.util.Hashtable;
import java.util.BitSet;
import java.util.Vector;
import java.io.Serializable;
import z.C.A.A.B.A.G;

public final class C implements G, Serializable
{
    static final int H = -1;
    static final int A = 1;
    int P;
    int F;
    int K;
    String G;
    Vector M;
    Vector[] C;
    Vector I;
    BitSet J;
    BitSet N;
    BitSet[] E;
    BitSet B;
    Hashtable O;
    boolean R;
    boolean[] D;
    boolean L;
    boolean Q;
    
    C(final String g, final K k) {
        this.L = false;
        this.Q = false;
        this.G = g;
        this.F = k.A - 1;
        this.E = k.B;
        this.M = new Vector();
        this.I = new Vector();
        this.B = new BitSet();
        (this.J = new BitSet(k.A)).or(k.D.C());
        final int[] array = new int[256];
        this.M.addElement(array);
        this.M.addElement(array);
        this.P = 1;
        if (this.J.get(this.F)) {
            this.B.set(this.P);
        }
        final J j = new J((BitSet)this.J.clone(), this.P);
        (this.O = new Hashtable()).put(j.A, j);
        this.I.addElement(j);
        this.I.addElement(j);
        ++this.P;
        this.J.xor(this.J);
        this.N = new BitSet(k.A);
        this.C = new Vector[256];
        for (int i = 0; i < 256; ++i) {
            this.C[i] = new Vector();
            for (int l = 0; l < k.A; ++l) {
                if (k.C[l].A((char)i)) {
                    this.C[i].addElement(k.C[l]);
                }
            }
        }
        this.D = k.A();
        this.R = this.B.get(1);
    }
    
    void A(final int n, final int n2, final int[] array) {
        final J j = this.I.elementAt(n);
        int size = this.C[n2].size();
        this.J.xor(this.J);
        while (size-- > 0) {
            final int c = this.C[n2].elementAt(size).C;
            if (j.A.get(c)) {
                this.J.or(this.E[c]);
            }
        }
        if (!this.O.containsKey(this.J)) {
            final J i = new J((BitSet)this.J.clone(), this.P++);
            this.I.addElement(i);
            this.O.put(i.A, i);
            this.M.addElement(new int[256]);
            if (!this.J.equals(this.N)) {
                array[n2] = this.P - 1;
                if (this.J.get(this.F)) {
                    this.B.set(this.P - 1);
                }
            }
            else {
                array[n2] = -1;
            }
        }
        else if (this.J.equals(this.N)) {
            array[n2] = -1;
        }
        else {
            array[n2] = this.O.get(this.J).B;
        }
    }
    
    int[] A(final int n) {
        return this.M.elementAt(n);
    }
    
    public String B() {
        return this.G;
    }
    
    public int A() {
        return this.K;
    }
}

// 
// Decompiled by Procyon v0.5.30
// 

package net.sf.jmimemagic;

import jmaster.util.log.B;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Collection;
import java.util.Map;
import java.util.ArrayList;
import java.nio.ByteBuffer;
import jmaster.util.log.A;

public class F implements Cloneable
{
    private static A G;
    private String B;
    private String K;
    private String L;
    private ByteBuffer I;
    private int F;
    private int D;
    private String J;
    private long C;
    private char A;
    private ArrayList E;
    private Map H;
    static /* synthetic */ Class class$net$sf$jmimemagic$F;
    
    public F() {
        this.B = null;
        this.K = null;
        this.L = null;
        this.I = null;
        this.F = 0;
        this.D = 0;
        this.J = "";
        this.C = 4294967295L;
        this.A = '\0';
        this.E = new ArrayList(0);
        net.sf.jmimemagic.F.G.D("instantiated");
    }
    
    public String C() {
        final StringBuffer sb = new StringBuffer();
        sb.append("\n");
        sb.append("mime type: ").append(this.B).append("\n");
        sb.append("description: ").append(this.L).append("\n");
        sb.append("extension: ").append(this.K).append("\n");
        sb.append("offset: ").append(this.F).append("\n");
        sb.append("length: ").append(this.D).append("\n");
        sb.append("test: ").append(new String(this.I.array())).append("\n");
        sb.append("type: ").append(this.J).append("\n");
        sb.append("comparator: ").append(this.A).append("\n");
        sb.append("bitmask: ").append(this.C);
        return sb.toString();
    }
    
    public void B(final String b) {
        this.B = b;
    }
    
    public String H() {
        return this.B;
    }
    
    public void H(final String k) {
        this.K = k;
    }
    
    public String A() {
        return this.K;
    }
    
    public void F(final String l) {
        this.L = l;
    }
    
    public String K() {
        return this.L;
    }
    
    public void A(final ByteBuffer i) {
        this.I = i;
    }
    
    public ByteBuffer I() {
        return this.I;
    }
    
    public void B(final int f) {
        this.F = f;
    }
    
    public int G() {
        return this.F;
    }
    
    public void A(final int d) {
        this.D = d;
    }
    
    public int B() {
        return this.D;
    }
    
    public void C(final String j) {
        this.J = j;
    }
    
    public String F() {
        return this.J;
    }
    
    public void E(final String s) {
        if (s != null) {
            this.C = (int)(Object)Long.decode(s);
        }
    }
    
    public long D() {
        return this.C;
    }
    
    public void A(final String s) {
        this.A = s.charAt(0);
    }
    
    public char E() {
        return this.A;
    }
    
    public void A(final Map h) {
        this.H = h;
    }
    
    public Map L() {
        return this.H;
    }
    
    public void A(final F f) {
        net.sf.jmimemagic.F.G.D("adding submatch '" + f.K() + "' to '" + this.K() + "'");
        this.E.add(f);
    }
    
    public void A(final Collection collection) {
        net.sf.jmimemagic.F.G.D("setting submatches for '" + this.K() + "'");
        this.E.clear();
        this.E.addAll(collection);
    }
    
    public Collection J() {
        return this.E;
    }
    
    public boolean G(final String s) {
        if (this.L != null && this.L.equals(s)) {
            return true;
        }
        final Iterator<F> iterator = this.J().iterator();
        while (iterator.hasNext()) {
            if (iterator.next().G(s)) {
                return true;
            }
        }
        return false;
    }
    
    public boolean D(final String s) {
        if (this.B != null && this.B.equals(s)) {
            return true;
        }
        final Iterator<F> iterator = this.J().iterator();
        while (iterator.hasNext()) {
            if (iterator.next().D(s)) {
                return true;
            }
        }
        return false;
    }
    
    protected Object clone() throws CloneNotSupportedException {
        final F f = new F();
        f.E(Long.toString(this.C, 8));
        f.A("" + this.A);
        f.F(this.L);
        f.H(this.K);
        f.A(this.D);
        f.B(this.B);
        f.B(this.F);
        final HashMap hashMap = new HashMap();
        hashMap.putAll(this.H);
        f.A(hashMap);
        final Iterator<F> iterator = this.E.iterator();
        final ArrayList<F> list = new ArrayList<F>();
        while (iterator.hasNext()) {
            list.add(iterator.next());
        }
        f.A(list);
        f.A(this.I);
        f.C(this.J);
        return f;
    }
    
    static /* synthetic */ Class class$(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError().initCause(ex);
        }
    }
    
    static {
        F.G = B.getInstance().getLog((F.class$net$sf$jmimemagic$F == null) ? (F.class$net$sf$jmimemagic$F = class$("net.sf.jmimemagic.F")) : F.class$net$sf$jmimemagic$F);
    }
}

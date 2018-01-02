// 
// Decompiled by Procyon v0.5.30
// 

package com.daysofwonder.req;

public class G extends p implements Cloneable
{
    private boolean a;
    private transient int b;
    private static int c;
    
    public G(final int n, final int n2) {
        super(n, n2);
        this.C();
    }
    
    public G() {
        this.C();
    }
    
    public int y() {
        return this.g;
    }
    
    public int z() {
        return this.j;
    }
    
    public void j(final int j) {
        this.j = j;
    }
    
    public void a() {
        this.u();
        this.writeInt(this.j);
    }
    
    public void b() {
        this.j = this.readInt();
    }
    
    public synchronized boolean A() {
        return this.a;
    }
    
    public synchronized void B() {
        this.a = true;
        this.notify();
    }
    
    public synchronized void C() {
        this.b = G.c++;
    }
    
    public synchronized void D() {
        super.i(this.i());
        this.a();
    }
    
    public synchronized void k(final int n) {
        this.x();
        super.i(n);
    }
    
    public int i() {
        return 4;
    }
    
    public String toString() {
        final StringBuffer sb = new StringBuffer();
        this.a(sb);
        return sb.toString();
    }
    
    public StringBuffer a(final StringBuffer sb) {
        sb.append("Request=[").append(this.b).append(",type=").append(this.g).append(",id=").append(this.j).append("]");
        return sb;
    }
}

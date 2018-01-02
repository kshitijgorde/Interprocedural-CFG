// 
// Decompiled by Procyon v0.5.30
// 

package jmaster.util.D.A;

public class A extends E
{
    static final long Q = -4753886159026796838L;
    protected static float[] U;
    private int T;
    
    public A() {
        super(A.U.clone());
        this.T = 2;
    }
    
    public void F(final int t) {
        this.T = t;
        final float[] array = A.U.clone();
        array[4] = t / 10.0f;
        final K k = new K(3, 3, array);
        k.B();
        this.A(k);
    }
    
    public int G() {
        return this.T;
    }
    
    public String toString() {
        return "Blur/Simple Blur...";
    }
    
    static {
        A.U = new float[] { 0.1f, 0.2f, 0.1f, 0.2f, 0.2f, 0.2f, 0.1f, 0.2f, 0.1f };
    }
}

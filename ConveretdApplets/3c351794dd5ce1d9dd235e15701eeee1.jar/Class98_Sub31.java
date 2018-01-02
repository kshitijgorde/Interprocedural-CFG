// 
// Decompiled by Procyon v0.5.30
// 

abstract class Class98_Sub31 extends Class98
{
    Class98_Sub31 aClass98_Sub31_4101;
    volatile boolean aBoolean4102;
    int anInt4103;
    Class98_Sub24 aClass98_Sub24_4104;
    
    abstract void method1321(final int p0);
    
    abstract Class98_Sub31 method1322();
    
    int method1323() {
        return 255;
    }
    
    final void method1324(final int[] array, final int n, final int n2) {
        if (this.aBoolean4102) {
            this.method1325(array, n, n2);
        }
        else {
            this.method1321(n2);
        }
    }
    
    abstract void method1325(final int[] p0, final int p1, final int p2);
    
    abstract int method1326();
    
    abstract Class98_Sub31 method1327();
    
    public Class98_Sub31() {
        this.aBoolean4102 = true;
    }
}

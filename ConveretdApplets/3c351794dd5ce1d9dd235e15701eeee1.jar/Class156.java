import jaclib.memory.Buffer;

// 
// Decompiled by Procyon v0.5.30
// 

class Class156
{
    private ha_Sub1 aHa_Sub1_1246;
    Buffer aBuffer1247;
    
    final void method2496(final byte[] array, final int n) {
        if (this.aBuffer1247 == null || this.aBuffer1247.getSize() < n) {
            this.aBuffer1247 = this.aHa_Sub1_1246.aNativeHeap4323.a(n, false);
        }
        this.aBuffer1247.a(array, 0, 0, n);
    }
    
    Class156(final ha_Sub1 aHa_Sub1_1246, final byte[] array, final int n) {
        this.aHa_Sub1_1246 = aHa_Sub1_1246;
        this.aBuffer1247 = this.aHa_Sub1_1246.aNativeHeap4323.a(n, false);
        if (array != null) {
            this.aBuffer1247.a(array, 0, 0, n);
        }
    }
    
    Class156(final ha_Sub1 aHa_Sub1_1246, final Buffer aBuffer1247) {
        this.aHa_Sub1_1246 = aHa_Sub1_1246;
        this.aBuffer1247 = aBuffer1247;
    }
}

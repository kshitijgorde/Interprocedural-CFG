import java.awt.Component;

// 
// Decompiled by Procyon v0.5.30
// 

final class Class268_Sub1 extends Class268
{
    private int anInt5160;
    private static Interface11 anInterface11_5161;
    
    @Override
    final void method3250(final int n) throws Exception {
        if (n > 32768) {
            throw new IllegalArgumentException();
        }
        Class268_Sub1.anInterface11_5161.method32(n, false, this.anInt5160);
    }
    
    @Override
    final void method3257() {
        Class268_Sub1.anInterface11_5161.method33(this.anInt5160, super.anIntArray2005);
    }
    
    @Override
    final void method3262() {
        Class268_Sub1.anInterface11_5161.method31(111, this.anInt5160);
    }
    
    @Override
    final int method3258() {
        return Class268_Sub1.anInterface11_5161.method34((byte)125, this.anInt5160);
    }
    
    @Override
    final void method3259() {
        Class268_Sub1.anInterface11_5161.method30(this.anInt5160, (byte)(-123));
    }
    
    public static void method3263() {
        Class268_Sub1.anInterface11_5161 = null;
    }
    
    Class268_Sub1(final Class88 class88, final int anInt5160) {
        Class268_Sub1.anInterface11_5161 = (Interface11)class88.method867(false);
        this.anInt5160 = anInt5160;
    }
    
    @Override
    final void method3253(final Component component) throws Exception {
        Class268_Sub1.anInterface11_5161.method29(Class151_Sub7.aBoolean5007, 103, component, Class64_Sub15.anInt3678);
    }
}

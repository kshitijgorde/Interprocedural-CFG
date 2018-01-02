// 
// Decompiled by Procyon v0.5.30
// 

final class Class235
{
    Runnable aRunnable1752;
    static long aLong1753;
    int anInt1754;
    int anInt1755;
    private ha_Sub2 aHa_Sub2_1756;
    int anInt1757;
    boolean aBoolean1758;
    boolean aBoolean1759;
    Class111_Sub2 aClass111_Sub2_1760;
    int anInt1761;
    boolean aBoolean1762;
    int anInt1763;
    static int[] anIntArray1764;
    int[] anIntArray1765;
    int[] anIntArray1766;
    Class12 aClass12_1767;
    int[] anIntArray1768;
    Class146_Sub1 aClass146_Sub1_1769;
    int[] anIntArray1770;
    int anInt1771;
    Class146_Sub1 aClass146_Sub1_1772;
    int[] anIntArray1773;
    Class146_Sub1 aClass146_Sub1_1774;
    int[] anIntArray1775;
    Class146_Sub1 aClass146_Sub1_1776;
    int[] anIntArray1777;
    Class146_Sub1 aClass146_Sub1_1778;
    int anInt1779;
    int[] anIntArray1780;
    int[] anIntArray1781;
    int[] anIntArray1782;
    int anInt1783;
    int[] anIntArray1784;
    int[] anIntArray1785;
    int[] anIntArray1786;
    Class146_Sub1 aClass146_Sub1_1787;
    int[] anIntArray1788;
    int[] anIntArray1789;
    Class146_Sub1 aClass146_Sub1_1790;
    int[] anIntArray1791;
    int[] anIntArray1792;
    Class146_Sub1 aClass146_Sub1_1793;
    int[] anIntArray1794;
    int[] anIntArray1795;
    int[] anIntArray1796;
    int[] anIntArray1797;
    Class146_Sub1 aClass146_Sub1_1798;
    float[] aFloatArray1799;
    Class146_Sub1 aClass146_Sub1_1800;
    
    final void method2888(final byte b) {
        try {
            if (b < -34) {
                this.aClass12_1767 = new Class12(this.aHa_Sub2_1756, this);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ou.A(" + b + ')');
        }
    }
    
    final void method2889(final byte b, final Runnable aRunnable1752) {
        try {
            this.aRunnable1752 = aRunnable1752;
            if (b != 34) {
                this.aFloatArray1799 = null;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ou.C(" + b + ',' + ((aRunnable1752 != null) ? "{...}" : "null") + ')');
        }
    }
    
    public static void method2890(final int n) {
        try {
            Class235.anIntArray1764 = null;
            if (n != 64) {
                Class235.aLong1753 = -27L;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ou.B(" + n + ')');
        }
    }
    
    Class235(final ha_Sub2 aHa_Sub2_1756) {
        this.anInt1757 = 0;
        this.anInt1755 = 0;
        this.anInt1754 = 0;
        this.aBoolean1762 = true;
        this.anInt1763 = 0;
        this.aBoolean1759 = false;
        this.aClass111_Sub2_1760 = new Class111_Sub2();
        this.anIntArray1766 = new int[Class146_Sub1.anInt4825];
        this.anIntArray1765 = new int[Class146_Sub1.anInt4825];
        this.anIntArray1770 = new int[10];
        this.anIntArray1781 = new int[64];
        this.anIntArray1777 = new int[10];
        this.anIntArray1785 = new int[10000];
        this.anIntArray1784 = new int[64];
        this.anIntArray1792 = new int[8];
        this.anIntArray1780 = new int[Class146_Sub1.anInt4825];
        this.anIntArray1768 = new int[10];
        this.anIntArray1789 = new int[10];
        this.aFloatArray1799 = new float[2];
        this.anIntArray1797 = new int[8];
        this.anIntArray1786 = new int[Class146_Sub1.anInt4825];
        this.anIntArray1795 = new int[64];
        this.anIntArray1782 = new int[10000];
        this.anIntArray1796 = new int[Class146_Sub1.anInt4825];
        this.anIntArray1791 = new int[8];
        this.anIntArray1794 = new int[Class146_Sub1.anInt4825];
        this.anIntArray1788 = new int[64];
        this.anIntArray1775 = new int[Class146_Sub1.anInt4825];
        try {
            this.aHa_Sub2_1756 = aHa_Sub2_1756;
            this.anInt1761 = -255 + this.aHa_Sub2_1756.anInt4484;
            this.aClass12_1767 = new Class12(aHa_Sub2_1756, this);
            this.aClass146_Sub1_1774 = new Class146_Sub1(this.aHa_Sub2_1756);
            this.aClass146_Sub1_1790 = new Class146_Sub1(this.aHa_Sub2_1756);
            this.aClass146_Sub1_1778 = new Class146_Sub1(this.aHa_Sub2_1756);
            this.aClass146_Sub1_1769 = new Class146_Sub1(this.aHa_Sub2_1756);
            this.aClass146_Sub1_1776 = new Class146_Sub1(this.aHa_Sub2_1756);
            this.aClass146_Sub1_1800 = new Class146_Sub1(this.aHa_Sub2_1756);
            this.aClass146_Sub1_1787 = new Class146_Sub1(this.aHa_Sub2_1756);
            this.aClass146_Sub1_1793 = new Class146_Sub1(this.aHa_Sub2_1756);
            this.aClass146_Sub1_1798 = new Class146_Sub1(this.aHa_Sub2_1756);
            this.aClass146_Sub1_1772 = new Class146_Sub1(this.aHa_Sub2_1756);
            this.anIntArray1773 = new int[Class146_Sub1.anInt4810];
            for (int n = 0; ~n > ~Class146_Sub1.anInt4810; ++n) {
                this.anIntArray1773[n] = -1;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ou.<init>(" + ((aHa_Sub2_1756 != null) ? "{...}" : "null") + ')');
        }
    }
    
    static {
        Class235.anIntArray1764 = null;
    }
}

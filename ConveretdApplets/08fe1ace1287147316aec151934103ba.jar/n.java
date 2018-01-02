// 
// Decompiled by Procyon v0.5.30
// 

class n extends m
{
    public static int Za;
    public static int _b;
    private static String w = "\ueb70";
    private static String x = "\ueb7f";
    private static String y = "\ueb78";
    private static String z = "\ueb71";
    private static String A = "\ueb64";
    
    public n(final int n, final int n2, final int n3, final int n4, final int n5, final int n6) {
        super(n, n2, n3, n4, n5, n6);
    }
    
    public int a() {
        if (super.ab) {
            return super.bb;
        }
        return this._(Math.max(10, super.bb - n.Za / 100));
    }
    
    private int _(final int n) {
        if (n <= 10) {
            return 10;
        }
        if (n < 50) {
            return 50;
        }
        if (n < 70) {
            return 100;
        }
        if (n < 90) {
            return 500;
        }
        return 1000;
    }
    
    public String b(final int n) {
        switch (n) {
            case 50: {
                return n.w;
            }
            case 100: {
                return n.x;
            }
            case 500: {
                return n.y;
            }
            case 1000: {
                return n.z;
            }
            default: {
                return n.A;
            }
        }
    }
    
    private static String b(final String s) {
        final int length = s.length();
        final char[] array = new char[length];
        for (int i = 0; i < length; ++i) {
            array[i] = (char)(s.charAt(i) ^ 0xFFFFEB3C);
        }
        return new String(array);
    }
    
    static {
        n.w = b(n.w);
        n.x = b(n.x);
        n.y = b(n.y);
        n.z = b(n.z);
        n.A = b(n.A);
    }
}

// 
// Decompiled by Procyon v0.5.30
// 

final class Class106
{
    static long aLong904;
    int anInt905;
    int anInt906;
    int anInt907;
    int anInt908;
    
    final Class106 method1719(final int n, final int n2) {
        try {
            if (n2 > -100) {
                return null;
            }
            return new Class106(this.anInt905, n, this.anInt908, this.anInt907);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "gn.A(" + n + ',' + n2 + ')');
        }
    }
    
    Class106(final int anInt905, final int anInt906, final int anInt907, final int anInt908) {
        try {
            this.anInt906 = anInt906;
            this.anInt907 = anInt908;
            this.anInt908 = anInt907;
            this.anInt905 = anInt905;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "gn.<init>(" + anInt905 + ',' + anInt906 + ',' + anInt907 + ',' + anInt908 + ')');
        }
    }
}

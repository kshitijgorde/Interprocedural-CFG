// 
// Decompiled by Procyon v0.5.30
// 

package a.a.a.a;

public final class ar extends Throwable
{
    String a;
    
    ar(final String a) {
        this.a = a;
    }
    
    void a() {
        System.out.print("Error Line ");
    }
    
    void a(final ah ah) {
        int n = 0;
        int n2 = 0;
        int n3 = 0;
        System.out.print(": " + this.a + "\n");
        int if1 = ah.if();
        int else1 = ah.else();
        final char[] long1 = ah.long();
        if (else1 == long1.length) {
            --else1;
        }
        else if (long1[else1] == ' ' || long1[else1] == '\t' || long1[else1] == '\n' || long1[else1] == '\r' || long1[else1] == '\0' || long1[else1] == '/') {
            --else1;
        }
        while (if1 < long1.length && long1[if1] != '\n' && long1[if1] != '\r' && long1[if1] != '\0') {
            System.out.print(long1[if1]);
            if (if1 == else1) {
                n3 = n;
            }
            if (long1[if1] == '\t' && n3 == 0) {
                ++n2;
            }
            else {
                ++n;
            }
            ++if1;
        }
        System.out.print("\n");
        for (int i = 0; i < n2; ++i) {
            System.out.print("\t");
        }
        for (int j = 0; j < n3; ++j) {
            System.out.print(" ");
        }
        System.out.print("^\n");
    }
}

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
    
    void a(final ah ah) {
        int n = 0;
        int n2 = 0;
        System.out.print("Error : " + this.a + "\n");
        System.out.print("Line : " + ah.new() + "\n");
        int a = ah.a();
        int char1 = ah.char();
        final char[] goto1 = ah.goto();
        if (char1 == goto1.length) {
            --char1;
        }
        else if (goto1[char1] == ' ' || goto1[char1] == '\t' || goto1[char1] == '\n' || goto1[char1] == '\r' || goto1[char1] == '\0' || goto1[char1] == '/') {
            --char1;
        }
        while (a < goto1.length && goto1[a] != '\n' && goto1[a] != '\r' && goto1[a] != '\0') {
            System.out.print(goto1[a]);
            if (a == char1) {
                n2 = n;
            }
            if (goto1[a] == '\t') {
                n += 7;
            }
            ++n;
            ++a;
        }
        System.out.print("\n");
        for (int i = 0; i < n2; ++i) {
            System.out.print(" ");
        }
        System.out.print("^\n\n");
    }
}

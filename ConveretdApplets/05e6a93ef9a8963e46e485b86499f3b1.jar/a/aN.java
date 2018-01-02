// 
// Decompiled by Procyon v0.5.30
// 

package a;

public class aN extends aK
{
    public String q;
    
    public final String e() {
        return aL.q(this.q, this.e());
    }
    
    public final String r() {
        return this.q;
    }
    
    public final void q(final String s) {
        boolean b = false;
        boolean b2 = false;
        if (s.indexOf(aL.q('i')) >= 0) {
            b = true;
        }
        if (s.indexOf(aL.q('b')) >= 0) {
            b2 = true;
        }
        this.o((b && b2) ? 3 : (b ? 2 : b2));
        this.q = aL.q(aL.q(s, 'i'), 'b');
    }
    
    private static boolean q(final String s, final int n) {
        return n < 0 || n >= s.length() || !Character.isLetter(s.charAt(n));
    }
    
    public final String q(String string) {
        int n = 0;
        final String lowerCase = super.o.toLowerCase();
        int i;
        do {
            if ((i = string.toLowerCase().indexOf(lowerCase, n)) != -1) {
                final int length = lowerCase.length();
                final int n2 = i + length;
                if (q(string, i - 1) && q(string, n2)) {
                    String s;
                    if (this.e() == null || this.e().length() == 0) {
                        s = "***********************************************************".substring(0, lowerCase.length());
                    }
                    else {
                        final String substring;
                        if (Character.isUpperCase((substring = string.substring(i, i + length)).charAt(0))) {
                            int n3 = 1;
                            boolean upperCase = false;
                            if (1 < substring.length()) {
                                while ((upperCase = Character.isUpperCase(substring.charAt(n3++))) && n3 < substring.length()) {}
                            }
                            if (upperCase) {
                                s = this.q.toUpperCase();
                            }
                            else {
                                s = Character.toUpperCase(this.q.charAt(0)) + this.q.substring(1, this.q.length());
                            }
                        }
                        else {
                            s = this.e();
                        }
                    }
                    string = string.substring(0, i) + s + string.substring(i + length);
                    n = i + s.length();
                }
                else {
                    n = i + length;
                }
            }
        } while (i != -1);
        return string;
    }
    
    public aN(final int n, final String s) {
        super(n, s);
    }
}

// 
// Decompiled by Procyon v0.5.30
// 

package a;

public class cd extends bZ
{
    public String q;
    
    public final String w() {
        return dR.q(this.q, this.u());
    }
    
    public final String e() {
        return this.q;
    }
    
    public final void w(final String s) {
        boolean b = false;
        boolean b2 = false;
        if (s.indexOf(dR.q('i')) >= 0) {
            b = true;
        }
        if (s.indexOf(dR.q('b')) >= 0) {
            b2 = true;
        }
        this.u((b && b2) ? 3 : (b ? 2 : b2));
        this.q = dR.q(dR.q(s, 'i'), 'b');
    }
    
    private static boolean q(final String s, final int n) {
        return n < 0 || n >= s.length() || !Character.isLetter(s.charAt(n));
    }
    
    public final String q(String string) {
        int n = 0;
        final String lowerCase = this.getName().toLowerCase();
        this.getName();
        int i;
        do {
            if ((i = string.toLowerCase().indexOf(lowerCase, n)) != -1) {
                final int length = lowerCase.length();
                final int n2 = i + length;
                if (q(string, i - 1) && q(string, n2)) {
                    String s;
                    if (this.w() == null || this.w().length() == 0) {
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
                            s = this.w();
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
    
    public cd(final int n, final String s) {
        super(n, s);
    }
    
    public final int q(final cd cd) {
        final int q;
        if ((q = super.q(cd)) != 0) {
            return q;
        }
        if (this.q.compareTo(cd.q) != 0) {
            return this.q.compareTo(cd.q);
        }
        return 0;
    }
}

// 
// Decompiled by Procyon v0.5.30
// 

package doook;

public class aq extends D
{
    public String m;
    
    public static boolean a(final String s, final int n) {
        return n < 0 || n >= s.length() || !Character.isLetter(s.charAt(n));
    }
    
    public String a(final A a) {
        if (a.count >= F.b[a.type]) {
            return a.k;
        }
        final String lowerCase = this.d().toLowerCase();
        this.d();
        String string = new String(a.k);
        int n = 0;
        int index;
        do {
            index = string.toLowerCase().indexOf(lowerCase, n);
            if (index != -1) {
                final int length = lowerCase.length();
                final int n2 = index + length;
                if (a(string, index - 1) && a(string, n2)) {
                    String s;
                    if (this.m == null || this.m.length() == 0) {
                        s = "***********************************************************".substring(0, lowerCase.length());
                    }
                    else {
                        final String substring = string.substring(index, index + length);
                        if (Character.isUpperCase(substring.charAt(0))) {
                            int n3 = 1;
                            boolean upperCase;
                            do {
                                upperCase = Character.isUpperCase(substring.charAt(n3++));
                            } while (upperCase && n3 < substring.length());
                            if (upperCase) {
                                s = this.m.toUpperCase();
                            }
                            else {
                                s = Character.toUpperCase(this.m.charAt(0)) + this.m.substring(1, this.m.length());
                            }
                        }
                        else {
                            s = this.m;
                        }
                    }
                    if (!this.a(37)) {
                        s = "[color=" + String.valueOf(super.u) + "]" + s + "[/color] ";
                    }
                    string = string.substring(0, index) + s + string.substring(index + length);
                    n = index + s.length();
                }
                else {
                    n = index + length;
                }
                ++a.count;
            }
        } while (index != -1 && a.count < F.b[a.type]);
        return string;
    }
    
    public String b(final String s) {
        final String lowerCase = this.d().toLowerCase();
        this.d();
        String string = new String(s);
        int n = 0;
        int i;
        do {
            i = string.toLowerCase().indexOf(lowerCase, n);
            if (i != -1) {
                final int length = lowerCase.length();
                final int n2 = i + length;
                if (a(string, i - 1) && a(string, n2)) {
                    String s2;
                    if (this.m == null || this.m.length() == 0) {
                        s2 = "***********************************************************".substring(0, lowerCase.length());
                    }
                    else {
                        final String substring = string.substring(i, i + length);
                        if (Character.isUpperCase(substring.charAt(0))) {
                            int n3 = 1;
                            boolean upperCase;
                            do {
                                upperCase = Character.isUpperCase(substring.charAt(n3++));
                            } while (upperCase && n3 < substring.length());
                            if (upperCase) {
                                s2 = this.m.toUpperCase();
                            }
                            else {
                                s2 = Character.toUpperCase(this.m.charAt(0)) + this.m.substring(1, this.m.length());
                            }
                        }
                        else {
                            s2 = this.m;
                        }
                    }
                    string = string.substring(0, i) + s2 + string.substring(i + length);
                    n = i + s2.length();
                }
                else {
                    n = i + length;
                }
            }
        } while (i != -1);
        return string;
    }
    
    public aq(final int n, final String s) {
        super(n, s);
    }
}

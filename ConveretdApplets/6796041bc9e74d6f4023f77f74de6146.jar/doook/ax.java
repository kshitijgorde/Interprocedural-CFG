// 
// Decompiled by Procyon v0.5.30
// 

package doook;

public class ax extends cF
{
    public String a;
    
    public static boolean a(final String s, final int n) {
        return n < 0 || n >= s.length() || !Character.isLetter(s.charAt(n));
    }
    
    public String a(final cE ce) {
        if (ce.count >= cG.h[ce.type]) {
            return ce.ah;
        }
        final String lowerCase = this.f().toLowerCase();
        this.f();
        String string = new String(ce.ah);
        int n = 0;
        int index;
        do {
            index = string.toLowerCase().indexOf(lowerCase, n);
            if (index != -1) {
                final int length = lowerCase.length();
                final int n2 = index + length;
                if (a(string, index - 1) && a(string, n2)) {
                    String s;
                    if (this.a == null || this.a.length() == 0) {
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
                                s = this.a.toUpperCase();
                            }
                            else {
                                s = Character.toUpperCase(this.a.charAt(0)) + this.a.substring(1, this.a.length());
                            }
                        }
                        else {
                            s = this.a;
                        }
                    }
                    if (!this.d(37)) {
                        s = "[color=" + String.valueOf(super.aN) + "]" + s + "[/color] ";
                    }
                    string = string.substring(0, index) + s + string.substring(index + length);
                    n = index + s.length();
                }
                else {
                    n = index + length;
                }
                ++ce.count;
            }
        } while (index != -1 && ce.count < cG.h[ce.type]);
        return string;
    }
    
    public String e(final String s) {
        final String lowerCase = this.f().toLowerCase();
        this.f();
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
                    if (this.a == null || this.a.length() == 0) {
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
                                s2 = this.a.toUpperCase();
                            }
                            else {
                                s2 = Character.toUpperCase(this.a.charAt(0)) + this.a.substring(1, this.a.length());
                            }
                        }
                        else {
                            s2 = this.a;
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
    
    public ax(final int n, final String s) {
        super(n, s);
    }
}

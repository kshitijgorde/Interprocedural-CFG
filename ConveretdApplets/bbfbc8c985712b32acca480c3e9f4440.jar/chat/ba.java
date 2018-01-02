// 
// Decompiled by Procyon v0.5.30
// 

package chat;

public class ba extends U
{
    public String a;
    public int a;
    
    private static boolean a(final String s, final int n) {
        return n < 0 || n >= s.length() || !Character.isLetter(s.charAt(n));
    }
    
    public final String a(final String s) {
        final String lowerCase = super.d.toLowerCase();
        String string = new String(s);
        int n = 0;
        int i;
        do {
            if ((i = string.toLowerCase().indexOf(lowerCase, n)) != -1) {
                final int length = lowerCase.length();
                final int n2 = i + length;
                if (a(string, i - 1) && a(string, n2)) {
                    String s2;
                    if (this.a == null || this.a.length() == 0) {
                        s2 = "***********************************************************".substring(0, lowerCase.length());
                    }
                    else {
                        final String substring;
                        if (Character.isUpperCase((substring = string.substring(i, i + length)).charAt(0)) && substring.length() > 1) {
                            int n3 = 1;
                            boolean upperCase;
                            while ((upperCase = Character.isUpperCase(substring.charAt(n3++))) && n3 < substring.length()) {}
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
    
    public final String[][] a(final String s, final int n, int n2) {
        final String lowerCase = super.d.toLowerCase();
        String string = new String(s);
        int n3 = 0;
        int index;
        do {
            if ((index = string.toLowerCase().indexOf(lowerCase, n3)) != -1) {
                final int length = lowerCase.length();
                final int n4 = index + length;
                if (a(string, index - 1) && a(string, n4)) {
                    String s2;
                    if (this.a == null || this.a.length() == 0) {
                        s2 = "***********************************************************".substring(0, lowerCase.length());
                    }
                    else {
                        final String substring;
                        if (Character.isUpperCase((substring = string.substring(index, index + length)).charAt(0)) && substring.length() > 1) {
                            int n5 = 1;
                            boolean upperCase;
                            while ((upperCase = Character.isUpperCase(substring.charAt(n5++))) && n5 < substring.length()) {}
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
                    string = string.substring(0, index) + s2 + string.substring(index + length);
                    n3 = index + s2.length();
                }
                else {
                    n3 = index + length;
                }
                ++n2;
            }
        } while (index != -1 && n2 < n);
        final String[][] array;
        (array = new String[2][2])[0][0] = string;
        array[0][1] = String.valueOf(n2);
        return array;
    }
    
    public ba(final int n, final String s) {
        super(n, s);
    }
}

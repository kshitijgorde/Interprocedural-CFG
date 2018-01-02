import java.net.URL;
import java.awt.MediaTracker;
import java.awt.Component;
import java.awt.Panel;
import java.awt.Image;

// 
// Decompiled by Procyon v0.5.30
// 

public class bs
{
    final int a = 50;
    final String b = "|";
    private String[] c;
    private Image[] d;
    private String[] e;
    private boolean[] f;
    private int g;
    private int h;
    
    public void a(final Panel panel, final bb bb) {
        int n = 0;
        while (true) {
            Label_0046: {
                if (!bm.dX) {
                    break Label_0046;
                }
                if (this.f[n]) {
                    panel.add(new c(this.d[n], bb, this.e[n]));
                }
                ++n;
            }
            if (n == this.g) {
                return;
            }
            continue;
        }
    }
    
    public int a(final MediaTracker mediaTracker, final int n) {
        int n2 = 0;
        while (true) {
            Label_0024: {
                if (!bm.dX) {
                    break Label_0024;
                }
                mediaTracker.addImage(this.d[n2], n + n2);
                ++n2;
            }
            if (n2 == this.g) {
                return n + n2;
            }
            continue;
        }
    }
    
    public void a(String substring) {
        if (this.g == 50) {
            return;
        }
        final int index = substring.indexOf("|");
        if (index < 0) {
            return;
        }
        this.c[this.g] = substring.substring(0, index);
        substring = substring.substring(index + 1);
        int index2 = substring.indexOf("|");
        if (index2 < 0) {
            return;
        }
        if (index2 < substring.length() && substring.charAt(index2 + 1) == "|".charAt(0)) {
            ++index2;
        }
        this.e[this.g] = substring.substring(0, index2).toLowerCase();
        this.f[this.g] = this.b(substring.substring(index2 + 1));
        ++this.g;
    }
    
    public bs() {
        this.c = new String[50];
        this.d = new Image[50];
        this.e = new String[50];
        this.f = new boolean[50];
        this.g = 0;
    }
    
    public void a(final irc irc, final URL url) {
        int n = 0;
        while (true) {
            Label_0047: {
                if (!bm.dX) {
                    break Label_0047;
                }
                this.d[n] = irc.getImage(url, irc.w + this.c[n]);
                ++n;
            }
            if (n == this.g) {
                return;
            }
            continue;
        }
    }
    
    private boolean b(final String s) {
        return s.toLowerCase().equals(c("]2U"));
    }
    
    public int a(final char[] array, int n, final int n2) {
        final boolean dx = bm.dX;
        this.h = -1;
        final int n3 = n;
        final char[] array2 = array.clone();
        int h = 0;
        while (true) {
            while (true) {
                Label_0064: {
                    if (!dx) {
                        break Label_0064;
                    }
                    if (array2[h] >= 'A' && array2[h] <= 'Z') {
                        final char[] array3 = array2;
                        final int n4 = h;
                        array3[n4] += ' ';
                    }
                    ++h;
                }
                if (h != array2.length) {
                    continue;
                }
                break;
            }
            h = 0;
            if (dx) {
                if (dx) {
                    continue;
                }
            }
            break;
        }
        while (true) {
            final int h2;
            if (h == this.g) {
                h2 = this.h;
                if (!dx) {
                    if (h2 == -1) {
                        return n3;
                    }
                    return n;
                }
            }
            else {
                n = n3;
                this.e[h].length();
            }
            final int n5 = h2;
            int n6 = 0;
            int n7 = 0;
            int n8 = 0;
            Label_0157: {
            Label_0153:
                while (true) {
                    Label_0136: {
                        if (!dx) {
                            break Label_0136;
                        }
                        if (array2[n] != this.e[h].charAt(n6)) {
                            break Label_0153;
                        }
                        ++n6;
                        ++n;
                    }
                    if (n6 != n5) {
                        n7 = n;
                        n8 = n2;
                        if (dx) {
                            break Label_0157;
                        }
                        if (n7 < n2) {
                            continue;
                        }
                    }
                    break;
                }
                n8 = n5;
            }
            if (n7 == n8) {
                this.h = h;
                return n - 1;
            }
            ++h;
        }
    }
    
    public Image a() {
        if (this.h >= 0) {
            return this.d[this.h];
        }
        return null;
    }
    
    public int b() {
        return 50;
    }
    
    private static String c(final String s) {
        final char[] charArray = s.toCharArray();
        for (int length = charArray.length, i = 0; i < length; ++i) {
            final char[] array = charArray;
            final int n = i;
            final char c = array[n];
            char c2 = '\0';
            switch (i % 5) {
                case 0: {
                    c2 = '$';
                    break;
                }
                case 1: {
                    c2 = 'W';
                    break;
                }
                case 2: {
                    c2 = '&';
                    break;
                }
                case 3: {
                    c2 = '\u0011';
                    break;
                }
                default: {
                    c2 = 'u';
                    break;
                }
            }
            array[n] = (char)(c ^ c2);
        }
        return new String(charArray);
    }
}

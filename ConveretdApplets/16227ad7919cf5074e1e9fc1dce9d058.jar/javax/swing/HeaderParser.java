// 
// Decompiled by Procyon v0.5.30
// 

package javax.swing;

class HeaderParser
{
    String raw;
    String[][] tab;
    
    public HeaderParser(final String raw) {
        this.raw = raw;
        this.tab = new String[10][2];
        this.parse();
    }
    
    public int findInt(final String s, final int n) {
        try {
            return Integer.parseInt(this.findValue(s, String.valueOf(n)));
        }
        catch (Throwable t) {
            return n;
        }
    }
    
    public String findKey(final int n) {
        if (n < 0 || n > 10) {
            return null;
        }
        return this.tab[n][0];
    }
    
    public String findValue(final int n) {
        if (n < 0 || n > 10) {
            return null;
        }
        return this.tab[n][1];
    }
    
    public String findValue(final String s) {
        return this.findValue(s, null);
    }
    
    public String findValue(final String s, final String s2) {
        if (s == null) {
            return s2;
        }
        s.toLowerCase();
        for (int i = 0; i < 10; ++i) {
            if (this.tab[i][0] == null) {
                return s2;
            }
            if (s.equals(this.tab[i][0])) {
                return this.tab[i][1];
            }
        }
        return s2;
    }
    
    private void parse() {
        if (this.raw != null) {
            this.raw = this.raw.trim();
            final char[] charArray = this.raw.toCharArray();
            int n = 0;
            int i = 0;
            int n2 = 0;
            int n3 = 1;
            int n4 = 0;
            final int length = charArray.length;
            while (i < length) {
                final char c = charArray[i];
                if (c == '=') {
                    this.tab[n2][0] = new String(charArray, n, i - n).toLowerCase();
                    n3 = 0;
                    n = ++i;
                }
                else if (c == '\"') {
                    if (n4 != 0) {
                        this.tab[n2++][1] = new String(charArray, n, i - n);
                        n4 = 0;
                        while (++i < length && (charArray[i] == ' ' || charArray[i] == ',')) {}
                        n3 = 1;
                        n = i;
                    }
                    else {
                        n4 = 1;
                        n = ++i;
                    }
                }
                else if (c == ' ' || c == ',') {
                    if (n4 != 0) {
                        ++i;
                    }
                    else {
                        if (n3 != 0) {
                            this.tab[n2++][0] = new String(charArray, n, i - n).toLowerCase();
                        }
                        else {
                            this.tab[n2++][1] = new String(charArray, n, i - n);
                        }
                        while (i < length && (charArray[i] == ' ' || charArray[i] == ',')) {
                            ++i;
                        }
                        n3 = 1;
                        n = i;
                    }
                }
                else {
                    ++i;
                }
            }
            if (--i > n) {
                if (n3 == 0) {
                    if (charArray[i] == '\"') {
                        this.tab[n2++][1] = new String(charArray, n, i - n);
                    }
                    else {
                        this.tab[n2++][1] = new String(charArray, n, i - n + 1);
                    }
                }
                else {
                    this.tab[n2][0] = new String(charArray, n, i - n + 1).toLowerCase();
                }
            }
            else if (i == n) {
                if (n3 == 0) {
                    if (charArray[i] == '\"') {
                        this.tab[n2++][1] = String.valueOf(charArray[i - 1]);
                    }
                    else {
                        this.tab[n2++][1] = String.valueOf(charArray[i]);
                    }
                }
                else {
                    this.tab[n2][0] = String.valueOf(charArray[i]).toLowerCase();
                }
            }
        }
    }
}

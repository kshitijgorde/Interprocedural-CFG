import java.io.IOException;
import java.io.DataInputStream;
import java.net.URL;

// 
// Decompiled by Procyon v0.5.30
// 

class bc
{
    int a;
    String[] b;
    String[] c;
    int d;
    
    void a(final v v) {
        int n = 0;
        while (true) {
            Label_0061: {
                if (!bm.dX) {
                    break Label_0061;
                }
                v.a(irc.R + this.b[n] + d("2\u0012") + this.c[n], bn.e, false);
                ++n;
            }
            if (n == this.d) {
                return;
            }
            continue;
        }
    }
    
    String a(String upperCase) {
        upperCase = upperCase.toUpperCase();
        int n = 0;
        while (true) {
            Label_0039: {
                if (!bm.dX) {
                    break Label_0039;
                }
                if (upperCase.equals(this.b[n].toUpperCase())) {
                    return this.c[n];
                }
                ++n;
            }
            if (n == this.d) {
                return null;
            }
            continue;
        }
    }
    
    int b(String upperCase) {
        final boolean dx = bm.dX;
        upperCase = upperCase.toUpperCase();
        int n = 0;
        while (true) {
            while (true) {
                Label_0036: {
                    if (!dx) {
                        break Label_0036;
                    }
                    upperCase.equals(this.b[n].toUpperCase());
                    final int n2;
                    if (n2 != 0) {
                        return n;
                    }
                    ++n;
                }
                if (n != this.d) {
                    continue;
                }
                break;
            }
            final int n2 = -1;
            if (!dx) {
                return n2;
            }
            continue;
        }
    }
    
    bc() {
        this.a = 50;
        this.b = new String[this.a];
        this.c = new String[this.a];
        this.d = 0;
    }
    
    void a() {
        this.d = 0;
    }
    
    void a(final URL url) {
        try {
            final DataInputStream dataInputStream = new DataInputStream(url.openStream());
            do {
                final String line = dataInputStream.readLine();
                if (line == null) {
                    break;
                }
                this.c(line);
            } while (!bm.dX);
        }
        catch (IOException ex) {}
    }
    
    int c(final String s) {
        final boolean dx = bm.dX;
        final int index = s.indexOf(32);
        if (index < 0) {
            return 2;
        }
        String s2 = null;
        Label_0098: {
            if (s.substring(0, index).trim().charAt(0) != '/') {
                s2 = new String("/" + s.substring(0, index).trim());
                if (!dx) {
                    break Label_0098;
                }
            }
            s2 = new String(s.substring(0, index).trim());
        }
        String s3 = null;
        Label_0181: {
            if (s.substring(index + 1).trim().charAt(0) != '/') {
                s3 = new String("/" + s.substring(index + 1).trim());
                if (!dx) {
                    break Label_0181;
                }
            }
            s3 = new String(s.substring(index + 1).trim());
        }
        final int b = this.b(s2);
        int d = 0;
        Label_0206: {
            if (b >= 0) {
                d = b;
                if (!dx) {
                    break Label_0206;
                }
            }
            d = this.d;
        }
        if (d == this.a) {
            return 1;
        }
        this.b[d] = new String(s2);
        this.c[d] = new String(s3);
        if (b < 0) {
            ++this.d;
        }
        return 0;
    }
    
    private static String d(final String s) {
        final char[] charArray = s.toCharArray();
        for (int length = charArray.length, i = 0; i < length; ++i) {
            final char[] array = charArray;
            final int n = i;
            final char c = array[n];
            char c2 = '\0';
            switch (i % 5) {
                case 0: {
                    c2 = '\u0012';
                    break;
                }
                case 1: {
                    c2 = '2';
                    break;
                }
                case 2: {
                    c2 = 'd';
                    break;
                }
                case 3: {
                    c2 = 'j';
                    break;
                }
                default: {
                    c2 = 'Q';
                    break;
                }
            }
            array[n] = (char)(c ^ c2);
        }
        return new String(charArray);
    }
}

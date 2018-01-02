// 
// Decompiled by Procyon v0.5.30
// 

package equis.metastock;

import java.util.Vector;

public class j
{
    int a;
    Vector b;
    
    public j(final String s) throws IllegalStateException {
        this.b = new Vector(5);
        this.a(s);
        this.a = 0;
    }
    
    public int a() {
        return this.b.size();
    }
    
    public String b() {
        if (this.a >= this.b.size()) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return this.b.elementAt(this.a++);
    }
    
    public boolean c() {
        return this.a < this.b.size();
    }
    
    private void a(final String s) throws IllegalStateException {
        int n = 0;
        int length;
        final int n2 = length = s.length();
        int n3 = 0;
        int i;
        for (i = 0; i < n2; ++i) {
            final char char1 = s.charAt(i);
            if (n != 0) {
                if (char1 == '\"') {
                    if (i > length) {
                        this.b.addElement(s.substring(length, i));
                    }
                    length = n2;
                    n = 0;
                    n3 = i + 1;
                }
            }
            else {
                switch (char1) {
                    case 34: {
                        if (i > n3) {
                            this.b.addElement(s.substring(n3, i));
                            n3 = n2;
                        }
                        length = i + 1;
                        n = 1;
                        break;
                    }
                    case 32:
                    case 44: {
                        if (i > n3) {
                            this.b.addElement(s.substring(n3, i));
                        }
                        n3 = i + 1;
                        break;
                    }
                    case 42:
                    case 43:
                    case 45:
                    case 47: {
                        if (i == n3) {
                            this.b.addElement(new Character(char1).toString());
                            n3 = i + 1;
                            break;
                        }
                        break;
                    }
                }
            }
        }
        if (n != 0 && i > length) {
            this.b.addElement(s.substring(length, i));
            return;
        }
        if (i > n3) {
            this.b.addElement(s.substring(n3, i));
        }
    }
}

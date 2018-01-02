// 
// Decompiled by Procyon v0.5.30
// 

package ABLwidgets;

public class fields
{
    public String a;
    public int b;
    public int c;
    public boolean d;
    public boolean e;
    private int f;
    private boolean g;
    private boolean h;
    
    public fields(final String a, final boolean d, final boolean e) {
        this.a = a;
        if (this.a == null) {
            this.a = "";
        }
        this.b = 0;
        this.d = d;
        this.e = e;
    }
    
    public String a(final int n) {
        final int b = this.b;
        this.c = 0;
        String a;
        while ((a = this.a()) != null) {
            ++this.c;
            if (this.c == n) {
                break;
            }
        }
        this.b = b;
        return a;
    }
    
    public String a() {
        final StringBuffer sb = new StringBuffer();
        if (this.b >= this.a.length()) {
            return null;
        }
        this.g = false;
        this.h = false;
        this.f = this.b;
        while (this.f < this.a.length()) {
            Label_0064: {
                try {
                    if (this.a(sb, this.f)) {
                        break Label_0064;
                    }
                }
                catch (Exception ex) {}
                break;
            }
            ++this.f;
        }
        this.b = this.f + 1;
        return sb.toString();
    }
    
    private boolean a(final StringBuffer sb, int n) {
        final char char1 = this.a.charAt(n);
        if (char1 == '\"' && this.e) {
            if ((this.g || this.h) && n + 1 < this.a.length() && this.a.charAt(n + 1) == char1) {
                sb.append(char1);
                ++n;
            }
            else if (!this.g) {
                this.h = !this.h;
            }
            else {
                sb.append(char1);
            }
        }
        else if (char1 == '\'' && this.d) {
            if ((this.g || this.h) && n + 1 < this.a.length() && this.a.charAt(n + 1) == char1) {
                sb.append(char1);
                ++n;
            }
            else if (!this.h) {
                this.g = !this.g;
            }
            else {
                sb.append(char1);
            }
        }
        else {
            if (!this.g && !this.h && char1 == ',') {
                return false;
            }
            sb.append(char1);
        }
        return true;
    }
}

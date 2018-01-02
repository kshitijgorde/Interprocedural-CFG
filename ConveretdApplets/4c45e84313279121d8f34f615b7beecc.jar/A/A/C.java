// 
// Decompiled by Procyon v0.5.30
// 

package A.A;

public class C
{
    private String B;
    private int A;
    
    public C(final String b) {
        this.B = b;
    }
    
    public boolean C() {
        return this.A < this.B.length();
    }
    
    public String A() {
        if (this.B.charAt(this.A) == '<') {
            return this.D();
        }
        return this.B();
    }
    
    private String D() {
        boolean b = false;
        final int a = this.A;
        do {
            switch (this.B.charAt(this.A)) {
                case '\"': {
                    b = !b;
                    break;
                }
            }
            ++this.A;
        } while (this.A < this.B.length() && (this.B.charAt(this.A) != '>' || b));
        if (this.A < this.B.length()) {
            ++this.A;
            return this.B.substring(a, this.A);
        }
        throw new Error("Tokenizer error: < without > at end of text");
    }
    
    private String B() {
        boolean b = false;
        final int a = this.A;
        do {
            switch (this.B.charAt(this.A)) {
                case '\"': {
                    b = !b;
                    break;
                }
            }
            ++this.A;
        } while (this.A < this.B.length() && (this.B.charAt(this.A) != '<' || b));
        return this.B.substring(a, this.A);
    }
}

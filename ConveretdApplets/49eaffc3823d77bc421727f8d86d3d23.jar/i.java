import java.util.NoSuchElementException;
import java.util.StringTokenizer;

// 
// Decompiled by Procyon v0.5.30
// 

public class i
{
    protected String a;
    protected String do;
    protected int if;
    protected int for;
    
    public i(final String s, final int n, final int n2) {
        this.if = n;
        this.for = n2;
        final StringTokenizer stringTokenizer = new StringTokenizer(s, ";", true);
        try {
            this.a = stringTokenizer.nextToken();
            stringTokenizer.nextToken();
            this.do = stringTokenizer.nextToken();
            if (this.do.equals(";")) {
                this.do = "";
            }
            else {
                stringTokenizer.nextToken();
            }
            final String nextToken = stringTokenizer.nextToken();
            if (!nextToken.equals(";")) {
                try {
                    this.if = Integer.parseInt(nextToken);
                }
                catch (NumberFormatException ex) {}
                if (this.if < 0) {
                    this.if = n;
                }
                stringTokenizer.nextToken();
            }
            final String nextToken2 = stringTokenizer.nextToken();
            if (!nextToken2.equals(";")) {
                try {
                    this.for = Integer.parseInt(nextToken2);
                }
                catch (NumberFormatException ex2) {}
                if (this.for < 0) {
                    this.for = n2;
                }
            }
        }
        catch (NoSuchElementException ex3) {}
    }
    
    public String a() {
        return this.a;
    }
    
    public String for() {
        return this.do;
    }
    
    public int if() {
        return this.if;
    }
    
    public int do() {
        return this.for;
    }
}

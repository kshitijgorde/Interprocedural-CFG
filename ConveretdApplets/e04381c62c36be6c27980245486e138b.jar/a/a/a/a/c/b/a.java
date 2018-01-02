// 
// Decompiled by Procyon v0.5.30
// 

package a.a.a.a.c.b;

import java.util.StringTokenizer;

public class a
{
    public float try;
    public float new;
    public float if;
    public float a;
    public String for;
    public String do;
    public String int;
    public boolean byte;
    
    private a(final float try1, final float if1, final float new1, final float a, final String for1, final String do1, final String int1, final boolean byte1) {
        this.try = try1;
        this.if = if1;
        this.new = new1;
        this.a = a;
        this.for = for1;
        this.do = do1;
        this.int = int1;
        this.byte = byte1;
    }
    
    public static a a(final String s) {
        final StringTokenizer stringTokenizer = new StringTokenizer(s, ",");
        float floatValue;
        float floatValue2;
        float floatValue3;
        float floatValue4;
        try {
            final String nextToken = stringTokenizer.nextToken();
            if (nextToken == null) {
                return null;
            }
            floatValue = Float.valueOf(nextToken.trim());
            final String nextToken2 = stringTokenizer.nextToken();
            if (nextToken2 == null) {
                return null;
            }
            floatValue2 = Float.valueOf(nextToken2.trim());
            final String nextToken3 = stringTokenizer.nextToken();
            if (nextToken3 == null) {
                return null;
            }
            floatValue3 = Float.valueOf(nextToken3.trim());
            if (floatValue3 <= floatValue) {
                return null;
            }
            final String nextToken4 = stringTokenizer.nextToken();
            if (nextToken4 == null) {
                return null;
            }
            floatValue4 = Float.valueOf(nextToken4.trim());
            if (floatValue4 <= floatValue2) {
                return null;
            }
        }
        catch (Exception ex) {
            return null;
        }
        String trim;
        try {
            final String nextToken5 = stringTokenizer.nextToken();
            if (nextToken5 == null) {
                return null;
            }
            trim = nextToken5.trim();
        }
        catch (Exception ex2) {
            return null;
        }
        String s2;
        try {
            final String nextToken6 = stringTokenizer.nextToken();
            s2 = ((nextToken6 == null) ? trim : nextToken6.trim());
        }
        catch (Exception ex3) {
            s2 = trim;
        }
        String s3;
        try {
            final String nextToken7 = stringTokenizer.nextToken();
            s3 = ((nextToken7 == null) ? "_blank" : nextToken7.trim());
        }
        catch (Exception ex4) {
            s3 = "_blank";
        }
        boolean b;
        try {
            b = true;
            final String nextToken8 = stringTokenizer.nextToken();
            if (nextToken8 != null && nextToken8.equalsIgnoreCase("false")) {
                b = false;
            }
        }
        catch (Exception ex5) {
            b = true;
        }
        return new a(floatValue, floatValue3, floatValue2, floatValue4, trim, s2, s3, b);
    }
    
    public String toString() {
        return "[" + this.try + ", " + this.new + ", " + this.if + ", " + this.a + "] : url=" + this.for + " : description=" + this.do + " : target frame=" + this.int + " : visible=" + this.byte;
    }
}

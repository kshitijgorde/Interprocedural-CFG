import java.io.IOException;
import java.io.OutputStream;
import java.io.FileOutputStream;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;
import java.math.BigInteger;

// 
// Decompiled by Procyon v0.5.30
// 

public class al extends ce
{
    public String g1;
    public String g0;
    
    public al(final String g0, final String g2, final BigInteger bigInteger, final BigInteger bigInteger2) {
        super(bigInteger, bigInteger2);
        this.g0 = g0;
        this.g1 = g2;
    }
    
    public static al fl(final String s, final String s2) throws NoSuchElementException {
        final StringTokenizer stringTokenizer = new StringTokenizer(s2);
        String nextToken = null;
        stringTokenizer.nextToken();
        final String nextToken2 = stringTokenizer.nextToken();
        final String nextToken3 = stringTokenizer.nextToken();
        if (stringTokenizer.hasMoreElements()) {
            nextToken = stringTokenizer.nextToken();
        }
        return new al(s, nextToken, new BigInteger(nextToken2), new BigInteger(nextToken3));
    }
    
    public final String fk() {
        return this.g0;
    }
    
    public final String toString() {
        return String.valueOf((this.g0 != null) ? new StringBuffer(String.valueOf(this.g0)).append(" ").toString() : "") + super.m4() + " " + super.m3() + " " + super.m2() + " " + ((this.g1 != null) ? this.g1 : "");
    }
    
    public final void fj(final String s) throws IOException {
        final bu bu = new bu(new FileOutputStream(s));
        bu.writeBytes(this.toString());
        bu.close();
    }
}

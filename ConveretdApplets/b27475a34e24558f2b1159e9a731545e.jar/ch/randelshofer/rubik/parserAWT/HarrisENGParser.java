// 
// Decompiled by Procyon v0.5.30
// 

package ch.randelshofer.rubik.parserAWT;

import java.util.StringTokenizer;
import java.util.Hashtable;

public class HarrisENGParser extends ScriptParser
{
    private static final String COMPRESSED_TOKENS = "R;U;F;L;D;B;R';U';F';L';D';B';R2;U2;F2;L2;D2;B2;R2';U2';F2';L2';D2';B2';r;u;f;l;d;b;r';u';f';l';d';b';r2;u2;f2;l2;d2;b2;r2';u2';f2';l2';d2';b2';M';E';S;M;E;S';M2';E2';S2;M2;E2;S2';m';e';s;m;e;s';m2';e2';s2;m2;e2;s2';x;y;z;x';y';z';x2;y2;z2;x2';y2';z2';.;;;;;;;;;;;';;( {;) };;;;*;;;;;;;;[;];;";
    
    public HarrisENGParser() {
        super(getTokens(), new Hashtable(), 1, 1, -1, -1, -1, true);
    }
    
    private static String[] getTokens() {
        final String[] array = new String[113];
        int n = 0;
        final StringTokenizer stringTokenizer = new StringTokenizer("R;U;F;L;D;B;R';U';F';L';D';B';R2;U2;F2;L2;D2;B2;R2';U2';F2';L2';D2';B2';r;u;f;l;d;b;r';u';f';l';d';b';r2;u2;f2;l2;d2;b2;r2';u2';f2';l2';d2';b2';M';E';S;M;E;S';M2';E2';S2;M2;E2;S2';m';e';s;m;e;s';m2';e2';s2;m2;e2;s2';x;y;z;x';y';z';x2;y2;z2;x2';y2';z2';.;;;;;;;;;;;';;( {;) };;;;*;;;;;;;;[;];;", ";", true);
        while (stringTokenizer.hasMoreTokens()) {
            final String nextToken = stringTokenizer.nextToken();
            if (nextToken.equals(";")) {
                ++n;
            }
            else {
                array[n] = nextToken;
            }
        }
        return array;
    }
}

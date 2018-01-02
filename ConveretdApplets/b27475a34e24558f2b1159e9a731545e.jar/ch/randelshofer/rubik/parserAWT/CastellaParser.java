// 
// Decompiled by Procyon v0.5.30
// 

package ch.randelshofer.rubik.parserAWT;

import java.util.StringTokenizer;
import java.util.Hashtable;

public class CastellaParser extends ScriptParser
{
    private static final String COMPRESSED_TOKENS = "R;O;F;Li;U;D;Ri;Oi;Fi;L;Ui;Di;R2;O2;F2;Li2;U2;D2;Ri2;Oi2;Fi2;L2;Ui2;Di2;r;o;f;li;u;d;ri;oi;fi;l;ui;di;r2;o2;f2;li2;u2;d2;ri2;ui2;fi2;l2;ui2;di2;Mi;Ei;S;M;E;Si;Mi2;Ei2;S2;M2;E2;Si2;mi;ei;s;m;e;si;mi2;ei2;s2;m2;e2;si2;x;y;z;xi;yi;zi;x2;y2;z2;xi2;yi2;zi2;.;;;;;;;;;;;';;( {;) };;;;*;;;;;;;;[;];;";
    
    public CastellaParser() {
        super(getTokens(), new Hashtable(), 1, 1, -1, -1, -1, true);
    }
    
    private static String[] getTokens() {
        final String[] array = new String[113];
        int n = 0;
        final StringTokenizer stringTokenizer = new StringTokenizer("R;O;F;Li;U;D;Ri;Oi;Fi;L;Ui;Di;R2;O2;F2;Li2;U2;D2;Ri2;Oi2;Fi2;L2;Ui2;Di2;r;o;f;li;u;d;ri;oi;fi;l;ui;di;r2;o2;f2;li2;u2;d2;ri2;ui2;fi2;l2;ui2;di2;Mi;Ei;S;M;E;Si;Mi2;Ei2;S2;M2;E2;Si2;mi;ei;s;m;e;si;mi2;ei2;s2;m2;e2;si2;x;y;z;xi;yi;zi;x2;y2;z2;xi2;yi2;zi2;.;;;;;;;;;;;';;( {;) };;;;*;;;;;;;;[;];;", ";", true);
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

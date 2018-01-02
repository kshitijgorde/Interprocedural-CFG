// 
// Decompiled by Procyon v0.5.30
// 

package ch.randelshofer.rubik.parserAWT;

import java.util.StringTokenizer;
import java.util.Hashtable;

public class RandelshoferGERParser extends ScriptParser
{
    private static final String COMPRESSED_TOKENS = "R;O;V;L;U;H;R-;O-;V-;L-;U-;H-;R2;O2;V2;L2;U2;H2;R2-;O2-;V2-;L2-;U2-;H2-;;;;;;;;;;;;;;;;;;;;;;;;;MR;MO;MV;ML;MU;MH;MR2;MO2;MV2;ML2;MU2;MH2;;;;;;;;;;;;;BR;BO;BV;BL;BU;BH;BR2;BO2;BV2;BL2;BU2;BH2;.;r;o;v;l;u;h;+;-;++;;-;;( [ {;) ] };,;(;);;;;;;;;;/*;*/;//;";
    
    public RandelshoferGERParser() {
        super(getTokens(), new Hashtable(), 1, 1, -1, -1, -1, true);
    }
    
    private static String[] getTokens() {
        final String[] array = new String[113];
        int n = 0;
        final StringTokenizer stringTokenizer = new StringTokenizer("R;O;V;L;U;H;R-;O-;V-;L-;U-;H-;R2;O2;V2;L2;U2;H2;R2-;O2-;V2-;L2-;U2-;H2-;;;;;;;;;;;;;;;;;;;;;;;;;MR;MO;MV;ML;MU;MH;MR2;MO2;MV2;ML2;MU2;MH2;;;;;;;;;;;;;BR;BO;BV;BL;BU;BH;BR2;BO2;BV2;BL2;BU2;BH2;.;r;o;v;l;u;h;+;-;++;;-;;( [ {;) ] };,;(;);;;;;;;;;/*;*/;//;", ";", true);
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

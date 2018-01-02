// 
// Decompiled by Procyon v0.5.30
// 

package ch.randelshofer.rubik.parserAWT;

import java.util.Hashtable;
import java.util.StringTokenizer;

public class TouchardDeledicqFRAParser extends ScriptParser
{
    private static final String COMPRESSED_TOKENS = "D;H;A;G;B;P;D-;H-;A-;G-;B-;P-;D2;H2;A2;G2;B2;P2;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;Ds;Hs;As;Gs;Bs;Ps;D2s;H2s;A2s;G2s;B2s;P2s;;;;;;;;;;;;;.;;;;;;;;;;;-;;( [ {;) ] };;;;;;;;;;;;/*;*/;//;";
    private static final String COMPRESSED_MACROS = "";
    
    public TouchardDeledicqFRAParser() {
        super(getTokens(), getMacros(), 1, 1, -1, -1, -1, true);
    }
    
    private static String[] getTokens() {
        final String[] array = new String[113];
        int n = 0;
        final StringTokenizer stringTokenizer = new StringTokenizer("D;H;A;G;B;P;D-;H-;A-;G-;B-;P-;D2;H2;A2;G2;B2;P2;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;Ds;Hs;As;Gs;Bs;Ps;D2s;H2s;A2s;G2s;B2s;P2s;;;;;;;;;;;;;.;;;;;;;;;;;-;;( [ {;) ] };;;;;;;;;;;;/*;*/;//;", ";", true);
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
    
    private static Hashtable getMacros() {
        final Hashtable<String, String> hashtable = new Hashtable<String, String>();
        final StringTokenizer stringTokenizer = new StringTokenizer("", ";", false);
        while (stringTokenizer.hasMoreTokens()) {
            final StringTokenizer stringTokenizer2 = new StringTokenizer(stringTokenizer.nextToken());
            final String nextToken = stringTokenizer.nextToken();
            while (stringTokenizer2.hasMoreTokens()) {
                hashtable.put(stringTokenizer2.nextToken(), nextToken);
            }
        }
        return hashtable;
    }
}

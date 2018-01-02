// 
// Decompiled by Procyon v0.5.30
// 

package ch.randelshofer.rubik.parserAWT;

import java.util.Hashtable;
import java.util.StringTokenizer;

public class ScriptFRAParser extends ScriptParser
{
    private static final String COMPRESSED_TOKENS = "Dh:Hg:Fm:Gb:Bd:Am:Db:Hd:F:Gh:Bg:A:DD:HH:FF:GG:BB:AA:::::::::::::::::::::::::::::::Mh:MCg:MFg:Mb:MCd:MFd:MM:MCC:MFF::::::::::::::::Ch:Cg:CRd:Cb:Cd:CRg:CC:CGG:CRR::::.:d:h:f:g:b:a:+:-:++:;:'::( [ {:) ] }:,:(:):;;;;;;/*;*/;//;";
    private static final String COMPRESSED_MACROS = "";
    
    public ScriptFRAParser() {
        super(getTokens(), getMacros(), 1, 1, -1, -1, -1, true);
    }
    
    private static String[] getTokens() {
        final String[] array = new String[113];
        int n = 0;
        final StringTokenizer stringTokenizer = new StringTokenizer("Dh:Hg:Fm:Gb:Bd:Am:Db:Hd:F:Gh:Bg:A:DD:HH:FF:GG:BB:AA:::::::::::::::::::::::::::::::Mh:MCg:MFg:Mb:MCd:MFd:MM:MCC:MFF::::::::::::::::Ch:Cg:CRd:Cb:Cd:CRg:CC:CGG:CRR::::.:d:h:f:g:b:a:+:-:++:;:'::( [ {:) ] }:,:(:):;;;;;;/*;*/;//;", ":", true);
        while (stringTokenizer.hasMoreTokens()) {
            final String nextToken = stringTokenizer.nextToken();
            if (nextToken.equals(":")) {
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
        final StringTokenizer stringTokenizer = new StringTokenizer("", ":", false);
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

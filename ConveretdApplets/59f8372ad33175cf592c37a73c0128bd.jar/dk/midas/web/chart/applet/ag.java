// 
// Decompiled by Procyon v0.5.30
// 

package dk.midas.web.chart.applet;

import java.util.StringTokenizer;
import java.awt.Frame;

public class ag extends ba
{
    public static String void;
    public static String null;
    
    public ag(final Frame frame, final ak[] array, final String s) {
        super(frame, ag.void, array, s, null);
    }
    
    public ag(final Frame frame, final ak[] array) {
        super(frame, ag.void, array, null, null);
    }
    
    public ag(final Frame frame, final ak[] array, final a a) {
        super(frame, ag.void, array, null, a);
    }
    
    public void setTitle(final String s) {
        final StringTokenizer stringTokenizer = new StringTokenizer(ag.void, "?");
        final String nextToken = stringTokenizer.nextToken();
        if (stringTokenizer.hasMoreElements()) {
            super.setTitle(nextToken + s + stringTokenizer.nextToken());
        }
        else {
            super.setTitle(nextToken);
        }
    }
    
    static {
        ag.void = new String("Input [?]");
        ag.null = new String("Period:");
    }
}

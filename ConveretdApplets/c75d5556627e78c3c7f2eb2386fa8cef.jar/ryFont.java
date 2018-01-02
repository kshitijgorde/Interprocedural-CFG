import java.util.Arrays;
import java.util.StringTokenizer;
import java.awt.GraphicsEnvironment;

// 
// Decompiled by Procyon v0.5.30
// 

public class ryFont
{
    private String[] \u0101;
    
    protected ryFont() {
        this.\u0101 = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
        for (int i = 0; i < this.\u0101.length; ++i) {
            this.\u0101[i] = this.\u0101[i].toLowerCase();
        }
    }
    
    protected String \u0102(final String s) {
        final StringTokenizer stringTokenizer = new StringTokenizer(s, "|");
        String s2 = "";
        while (stringTokenizer.hasMoreTokens()) {
            final String nextToken = stringTokenizer.nextToken();
            if (Arrays.binarySearch(this.\u0101, nextToken.toLowerCase().trim()) > -1) {
                s2 = nextToken;
                break;
            }
        }
        return s2;
    }
}

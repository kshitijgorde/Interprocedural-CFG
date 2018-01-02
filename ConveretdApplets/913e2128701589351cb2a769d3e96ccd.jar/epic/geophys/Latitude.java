// 
// Decompiled by Procyon v0.5.30
// 

package epic.geophys;

import java.util.StringTokenizer;

public class Latitude
{
    public static final double MAX_LATITUDE = 90.0;
    public static final double MIN_LATITUDE = -90.0;
    private double lat;
    
    public Latitude(final double n) {
        this.lat = Math.min(90.0, Math.max(-90.0, n));
    }
    
    public Latitude(final Latitude latitude) {
        this.lat = latitude.LatValue();
    }
    
    public Latitude(final String s) throws LatInvalidException {
        int n = 1;
        String s2 = new String(s).trim().toUpperCase();
        if (s2.endsWith("N")) {
            n = 1;
            s2 = s2.substring(0, s2.length() - 1).trim();
        }
        else if (s2.endsWith("S")) {
            n = -1;
            s2 = s2.substring(0, s2.length() - 1).trim();
        }
        if (new StringTokenizer(s2, " +-.0123456789", false).countTokens() > 0) {
            throw new LatInvalidException();
        }
        final StringTokenizer stringTokenizer = new StringTokenizer(s2, " ", false);
        Label_0309: {
            switch (stringTokenizer.countTokens()) {
                case 1: {
                    try {
                        this.lat = Double.valueOf(s2);
                        break Label_0309;
                    }
                    catch (NumberFormatException ex) {
                        throw new LatInvalidException();
                    }
                }
                case 2: {
                    final String nextToken = stringTokenizer.nextToken();
                    if (new StringTokenizer(nextToken, "+-0123456789", false).countTokens() > 0) {
                        throw new LatInvalidException();
                    }
                    final String nextToken2 = stringTokenizer.nextToken();
                    if (new StringTokenizer(nextToken2, ".0123456789", false).countTokens() > 0) {
                        throw new LatInvalidException();
                    }
                    try {
                        this.lat = Double.valueOf(nextToken);
                        this.lat += Double.valueOf(nextToken2) / 60.0;
                        break Label_0309;
                    }
                    catch (NumberFormatException ex2) {
                        throw new LatInvalidException();
                    }
                    break;
                }
            }
            throw new LatInvalidException();
        }
        this.lat *= n;
        this.lat = Math.min(90.0, Math.max(-90.0, this.lat));
    }
    
    public double LatValue() {
        return this.lat;
    }
    
    public boolean equals(final Object o) {
        boolean b = false;
        if (o instanceof Latitude && this.lat == ((Latitude)o).LatValue()) {
            b = true;
        }
        return b;
    }
    
    public String toString() {
        return new Double(this.lat).toString();
    }
    
    public String toString(final String s) {
        final int n = (int)(this.lat * 10.0);
        String s2;
        if (n < 0) {
            s2 = "S";
        }
        else {
            s2 = "N";
        }
        return String.valueOf(Math.abs(n) / 10.0f) + s2;
    }
}

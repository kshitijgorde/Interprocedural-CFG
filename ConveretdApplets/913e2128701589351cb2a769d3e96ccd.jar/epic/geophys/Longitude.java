// 
// Decompiled by Procyon v0.5.30
// 

package epic.geophys;

import java.util.StringTokenizer;

public class Longitude
{
    public static final double MAX_LONGITUDE = 180.0;
    public static final double MIN_LONGITUDE = -180.0;
    private double lon;
    
    public Longitude(final double n) {
        this.lon = this.normalize(n);
    }
    
    public Longitude(final Longitude longitude) {
        this.lon = longitude.LonValue();
    }
    
    public Longitude(final String s) throws LonInvalidException {
        int n = 1;
        String s2 = new String(s).trim().toUpperCase();
        if (s2.endsWith("E")) {
            n = 1;
            s2 = s2.substring(0, s2.length() - 1).trim();
        }
        else if (s2.endsWith("W")) {
            n = -1;
            s2 = s2.substring(0, s2.length() - 1).trim();
        }
        if (new StringTokenizer(s2, " +-.0123456789", false).countTokens() > 0) {
            throw new LonInvalidException();
        }
        final StringTokenizer stringTokenizer = new StringTokenizer(s2, " ", false);
        Label_0309: {
            switch (stringTokenizer.countTokens()) {
                case 1: {
                    try {
                        this.lon = Double.valueOf(s2);
                        break Label_0309;
                    }
                    catch (NumberFormatException ex) {
                        throw new LonInvalidException();
                    }
                }
                case 2: {
                    final String nextToken = stringTokenizer.nextToken();
                    if (new StringTokenizer(nextToken, "+-0123456789", false).countTokens() > 0) {
                        throw new LonInvalidException();
                    }
                    final String nextToken2 = stringTokenizer.nextToken();
                    if (new StringTokenizer(nextToken2, ".0123456789", false).countTokens() > 0) {
                        throw new LonInvalidException();
                    }
                    try {
                        this.lon = Double.valueOf(nextToken);
                        this.lon += Double.valueOf(nextToken2) / 60.0;
                        break Label_0309;
                    }
                    catch (NumberFormatException ex2) {
                        throw new LonInvalidException();
                    }
                    break;
                }
            }
            throw new LonInvalidException();
        }
        this.lon = this.normalize(this.lon) * n;
    }
    
    public double LonValue() {
        return this.lon;
    }
    
    public boolean equals(final Object o) {
        boolean b = false;
        if (o instanceof Longitude && this.lon == ((Longitude)o).LonValue()) {
            b = true;
        }
        return b;
    }
    
    private double normalize(final double n) {
        double n2 = n % 360.0;
        if (n2 > 180.0) {
            n2 -= 360.0;
        }
        else if (n2 < -180.0) {
            n2 += 360.0;
        }
        return n2;
    }
    
    public String toString() {
        return new Double(this.lon).toString();
    }
    
    public String toString(final String s) {
        final int n = (int)(this.lon * 10.0);
        String s2;
        if (n < 0) {
            s2 = "W";
        }
        else {
            s2 = "E";
        }
        return String.valueOf(Math.abs(n) / 10.0f) + s2;
    }
}

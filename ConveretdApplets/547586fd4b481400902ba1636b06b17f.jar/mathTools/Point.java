// 
// Decompiled by Procyon v0.5.30
// 

package mathTools;

import java.text.DecimalFormat;

public class Point
{
    private double x;
    private double y;
    
    public Point(final double x, final double y) {
        this.x = x;
        this.y = y;
    }
    
    public double getX() {
        return this.x;
    }
    
    public double getY() {
        return this.y;
    }
    
    public String toString() {
        return "( " + this.round(this.x, 2, 0) + ", " + this.round(this.y, 5, 0) + " )";
    }
    
    private String round(final double n, final int n2, final int n3) {
        String string = "#.";
        for (int i = 1; i <= n2; ++i) {
            string = String.valueOf(string) + "0";
        }
        String s;
        for (s = new DecimalFormat(string).format(n); s.length() < n3; s = " " + s) {}
        return s;
    }
}

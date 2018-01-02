// 
// Decompiled by Procyon v0.5.30
// 

package astro;

public class UdMath
{
    static double fmod(final double n, final double n2) {
        return n - Math.ceil(n / n2) * n2;
    }
    
    static double udsin(final double n) {
        return Math.sin(n * 3.141592653589793 / 180.0);
    }
    
    static double udcos(final double n) {
        return Math.cos(n * 3.141592653589793 / 180.0);
    }
    
    static double udtan(final double n) {
        return Math.tan(n * 3.141592653589793 / 180.0);
    }
    
    static double degmal(final double n) {
        double n2 = 360.0 * (n / 360.0 - Math.floor(n / 360.0));
        if (n2 < 0.0) {
            n2 += 360.0;
        }
        if (n2 >= 360.0) {
            n2 -= 360.0;
        }
        return n2;
    }
    
    static double radmal(final double n) {
        double n2 = 6.283185307179586 * (n / 6.283185307179586 - Math.floor(n / 6.283185307179586));
        if (n2 < 0.0) {
            n2 += 6.283185307179586;
        }
        if (n2 >= 6.283185307179586) {
            n2 -= 6.283185307179586;
        }
        return n2;
    }
    
    static double deg2rad(final double n) {
        return n * 3.141592653589793 / 180.0;
    }
    
    static double rad2deg(final double n) {
        return n * 180.0 / 3.141592653589793;
    }
    
    static double arccosh(final double n) {
        return Math.log(n + Math.sqrt(n * n - 1.0));
    }
    
    static double sinh(final double n) {
        return (Math.exp(n) - Math.exp(-n)) / 2.0;
    }
    
    static double cosh(final double n) {
        return (Math.exp(n) + Math.exp(-n)) / 2.0;
    }
}

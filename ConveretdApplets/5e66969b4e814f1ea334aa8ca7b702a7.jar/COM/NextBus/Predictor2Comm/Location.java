// 
// Decompiled by Procyon v0.5.30
// 

package COM.NextBus.Predictor2Comm;

import COM.NextBus.util.a;
import java.io.DataInputStream;
import java.io.Serializable;

public class Location implements Serializable
{
    private static final long serialVersionUID = 3933883419146969998L;
    private final int _latitudeInTenMillionths;
    private final int _longitudeInTenMillionths;
    
    public Location() {
        this._latitudeInTenMillionths = 0;
        this._longitudeInTenMillionths = 0;
    }
    
    public Location(final int n, final int n2) {
        this._latitudeInTenMillionths = n / 6 * 10;
        this._longitudeInTenMillionths = n2 / 6 * 10;
    }
    
    public Location(double n, double n2) {
        if (n < -90.0 || n > 90.0) {
            if ((n %= 360.0) > 90.0 && n <= 270.0) {
                n = 180.0 - n;
            }
            else if (n > 270.0 && n <= 360.0) {
                n -= 360.0;
            }
            else if (n < -90.0 && n >= -270.0) {
                n = -180.0 - n;
            }
            else if (n < -270.0 && n >= -360.0) {
                n += 360.0;
            }
        }
        this._latitudeInTenMillionths = (int)(n * 1.0E7);
        if (n2 < -180.0 || n2 > 180.0) {
            if ((n2 %= 360.0) > 180.0) {
                n2 -= 360.0;
            }
            else if (n2 < 180.0) {
                n2 += 360.0;
            }
        }
        this._longitudeInTenMillionths = (int)(n2 * 1.0E7);
    }
    
    public final double a() {
        return this._latitudeInTenMillionths / 1.0E7;
    }
    
    public final double b() {
        return this._longitudeInTenMillionths / 1.0E7;
    }
    
    public final int c() {
        return this._latitudeInTenMillionths / 10 * 6;
    }
    
    public final int d() {
        return this._longitudeInTenMillionths / 10 * 6;
    }
    
    public String toString() {
        return "Lat=" + this.a() + ",Lon=" + this.b();
    }
    
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o != null && this.getClass() == o.getClass()) {
            final Location location = (Location)o;
            if (this._latitudeInTenMillionths == location._latitudeInTenMillionths && this._longitudeInTenMillionths == location._longitudeInTenMillionths) {
                return true;
            }
        }
        return false;
    }
    
    public static Location a(final DataInputStream dataInputStream) {
        return new Location(dataInputStream);
    }
    
    private Location(final DataInputStream dataInputStream) {
        this._latitudeInTenMillionths = dataInputStream.readInt();
        this._longitudeInTenMillionths = dataInputStream.readInt();
    }
    
    public static Location a(Location location, final Location location2, final double n) {
        a.a(a(n, 0.0, 1.0, true), "Interp Fraction > 1 : " + n);
        final double n2 = location._latitudeInTenMillionths;
        final double n3 = location._longitudeInTenMillionths;
        final double n4 = location2._latitudeInTenMillionths;
        final double n5 = location2._longitudeInTenMillionths;
        final double n6 = n2 + n * (n4 - n2);
        final double n7 = n3 + n * (n5 - n3);
        a.a(a(n6, n2, n4, true), "interpolation error: " + n6 + ", " + n2 + ", " + n4);
        a.a(a(n7, n3, n5, true), "interpolation error: " + n7 + ", " + n3 + ", " + n5);
        return location = new Location(n6 / 1.0E7, n7 / 1.0E7);
    }
    
    private static boolean a(final double n, final double n2, final double n3, final boolean b) {
        return n == n2 || n == n3 || ((n > n2 && n < n3) || (n > n3 && n < n2));
    }
}

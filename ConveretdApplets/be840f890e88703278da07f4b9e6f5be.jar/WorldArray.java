// 
// Decompiled by Procyon v0.5.30
// 

class WorldArray extends RectArray
{
    public boolean IsMOF;
    public static float dtr;
    public static float rtd;
    public static float pi;
    public static float halfpi;
    public static float twopi;
    public static float r0;
    public float lon_pts;
    public float lat_pts;
    public float col_per_rad;
    public float row_per_rad;
    public float rad_per_col;
    public float rad_per_row;
    
    public static float sin(final float n) {
        return (float)Math.sin(n);
    }
    
    public static float cos(final float n) {
        return (float)Math.cos(n);
    }
    
    public static float acos(final float n) {
        return (float)Math.acos(n);
    }
    
    public static float asin(final float n) {
        return (float)Math.asin(n);
    }
    
    public static float fmin(final float n, final float n2) {
        if (n < n2) {
            return n;
        }
        return n2;
    }
    
    public static float fmax(final float n, final float n2) {
        if (n < n2) {
            return n2;
        }
        return n;
    }
    
    public static float fmin(final float n, final double n2) {
        if (n < (float)n2) {
            return n;
        }
        return (float)n2;
    }
    
    public static float fmax(final float n, final double n2) {
        if (n < (float)n2) {
            return (float)n2;
        }
        return n;
    }
    
    public static float sqrt(final float n) {
        return (float)Math.sqrt(n);
    }
    
    public static float log(final float n) {
        return (float)Math.log(n);
    }
    
    public static float pow(final float n, final float n2) {
        return (float)Math.pow(n, n2);
    }
    
    public static float exp(final float n) {
        return (float)Math.exp(n);
    }
    
    public static float atan(final float n) {
        return (float)Math.atan(n);
    }
    
    public static float fabs(final float n) {
        return (n < 0.0f) ? (-n) : n;
    }
    
    public static float amod(final float n, final float n2) {
        return (n > n2) ? (n - n2) : ((n < -n2) ? (n + n2) : n);
    }
    
    public static float sygn(final float n) {
        return (float)((n > 0.0f) ? 1.0 : ((n < 0.0f) ? -1.0 : 0.0));
    }
    
    public float row_to_rad(final float n) {
        return WorldArray.halfpi - n * this.rad_per_row - this.rad_per_row / 2.0f;
    }
    
    public int rad_to_row(final float n) {
        return (int)((WorldArray.halfpi - n) * this.row_per_rad);
    }
    
    public float col_to_rad(final float n) {
        return n * this.rad_per_col + this.rad_per_col / 2.0f;
    }
    
    public int rad_to_col(final float n) {
        return (int)(n * this.col_per_rad);
    }
    
    public float compute_range(final float n, final float n2, final float n3, final float n4) {
        return acos(fmax(fmin(sin(n) * sin(n3) + cos(n) * cos(n3) * cos(n2 - n4), 1.0f), -1.0f));
    }
    
    public float compute_azim(final float n, final float n2, final float n3, final float n4) {
        final float fmax = fmax(fmin(sin(n) * sin(n3) + cos(n) * cos(n3) * cos(n2 - n4), 1.0f), -1.0f);
        final float acos = acos(fmax);
        float acos2;
        if (acos < 1.0E-5) {
            acos2 = 0.0f;
        }
        else {
            acos2 = acos(fmax(fmin((sin(n3) - sin(n) * fmax) / (cos(n) * sin(acos)), 1.0f), -1.0f));
        }
        if (sin(n2 - n4) < 0.0f) {
            acos2 = WorldArray.twopi - acos2;
        }
        return acos2;
    }
    
    public static float compute_lat(final float n, final float n2, final float n3, final float n4) {
        final float sin = sin(n);
        final float cos = cos(n);
        final float cos2 = cos(n3);
        final float fmin = fmin(fmax(sin * cos2 + cos * sin(n3) * cos(n4), -1.0), 1.0);
        final float acos = acos(fmin);
        float n5;
        if (fabs(n5 = cos * sin(acos)) < 1.0E-5) {
            n5 = 1.0E-5f * sygn(n5);
        }
        acos(fmin(fmax((cos2 - sin * fmin) / n5, -1.0f), 1.0f));
        return WorldArray.halfpi - acos;
    }
    
    public static float compute_lon(final float n, final float n2, final float n3, final float n4) {
        final float sin = sin(n);
        final float cos = cos(n);
        final float cos2 = cos(n3);
        final float fmin = fmin(fmax(sin * cos2 + cos * sin(n3) * cos(n4), -1.0f), 1.0f);
        final float acos = acos(fmin);
        float n5 = cos * sin(acos);
        if (fabs(n5) < 1.0E-5) {
            n5 = 1.0E-5f * sygn(n5);
        }
        final float acos2 = acos(fmin(fmax((cos2 - sin * fmin) / n5, -1.0f), 1.0f));
        final float n6 = WorldArray.halfpi - acos;
        float n7;
        if (sin(n4) >= 0.0) {
            n7 = amod(n2 - acos2, WorldArray.twopi);
        }
        else {
            n7 = amod(n2 + acos2, WorldArray.twopi);
        }
        return n7;
    }
    
    public WorldArray(final int n, final int n2) {
        super(n, n2);
        this.lon_pts = n2;
        this.lat_pts = n;
        this.col_per_rad = (float)(WorldArray.rtd * this.lon_pts / 360.0);
        this.row_per_rad = (float)(WorldArray.rtd * this.lat_pts / 180.0);
        this.rad_per_col = 1.0f / this.col_per_rad;
        this.rad_per_row = 1.0f / this.row_per_rad;
    }
    
    private int Latr2Index(float n) {
        if (n > 3.1415927f) {
            n = 3.1415927f;
        }
        if (n < 0.0f) {
            n = 0.0f;
        }
        return (int)(this.Length() * (n / 3.1415927f) + 0.5);
    }
    
    private int Lonr2Index(float n) {
        while (n < 0.0f) {
            n += 6.2831855f;
        }
        while (n > 6.2831855f) {
            n -= 6.2831855f;
        }
        return (int)(this.Width() * (n / 6.2831855f) + 0.5);
    }
    
    public float GetValueLLr() {
        return this.GetValue();
    }
    
    public float GetValueLLr(final float n) {
        return this.GetValue(this.Latr2Index(n));
    }
    
    public float GetValueLLr(final float n, final float n2) {
        return this.GetValue(this.Latr2Index(n), this.Lonr2Index(n2));
    }
    
    static {
        WorldArray.dtr = 0.017453292f;
        WorldArray.rtd = 57.29578f;
        WorldArray.pi = 3.1415927f;
        WorldArray.halfpi = 1.5707964f;
        WorldArray.twopi = 6.2831855f;
        WorldArray.r0 = 6371.0f;
    }
}

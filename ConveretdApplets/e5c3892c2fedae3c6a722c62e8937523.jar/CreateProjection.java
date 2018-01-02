import java.awt.Point;

// 
// Decompiled by Procyon v0.5.30
// 

public class CreateProjection
{
    private static WRS2Model wrs2;
    private static final int SOUTH_POLE = 1001;
    public static final int NORTH_AMERICA = 1002;
    private static final int SOUTH_AMERICA = 1003;
    private static final int EUROPE = 1004;
    private static final int AFRICA = 1005;
    private static final int AUSTRALIA = 1006;
    private static final int ASIA = 1007;
    private static final int SOUTH_PACIFIC = 1008;
    public static final int SINUSOIDAL = 1010;
    public static final int FAKE_GEOGRAPHIC = 1011;
    public static final int POLAR_STEREOGRAPHIC = 1012;
    public static final int OUTTA_RANGE = 1100;
    
    public static ProjectionTransformation fromProjectionNumber(final int n) {
        ProjectionTransformation projectionTransformation = null;
        switch (n) {
            case 1001: {
                projectionTransformation = new LamAzProjection(6370997.0, 0.0, -90.0);
                break;
            }
            case 1002: {
                projectionTransformation = new LamAzProjection(6370997.0, -100.0, 50.0);
                break;
            }
            case 1003: {
                projectionTransformation = new LamAzProjection(6370997.0, -60.0, -15.0);
                break;
            }
            case 1004: {
                projectionTransformation = new LamAzProjection(6370997.0, 20.0, 55.0);
                break;
            }
            case 1005: {
                projectionTransformation = new LamAzProjection(6370997.0, 20.0, 5.0);
                break;
            }
            case 1006: {
                projectionTransformation = new LamAzProjection(6370997.0, 135.0, -15.0);
                break;
            }
            case 1010: {
                projectionTransformation = new SinusoidalProjection(6371007.181, 0.0);
                break;
            }
            case 1008: {
                projectionTransformation = new LamAzProjection(6370997.0, -140.0, -15.0);
                break;
            }
            case 1011: {
                projectionTransformation = new GeographicProjection();
                break;
            }
            case 1012: {
                projectionTransformation = new PolarStereographicProjection(6378137.0, 6356752.3142, 0.0, -71.0, 0.0, 0.0);
                break;
            }
            default: {
                projectionTransformation = new LamAzProjection(6370997.0, 100.0, 45.0);
                break;
            }
        }
        return projectionTransformation;
    }
    
    public static int getDefaultProjectionCode(final Sensor sensor, final LatLong latLong) {
        if (sensor.defaultProjectionCode > 0) {
            return sensor.defaultProjectionCode;
        }
        final Point latLongToGrid = CreateProjection.wrs2.latLongToGrid(latLong.latitude, latLong.longitude);
        final int x = latLongToGrid.x;
        final int y = latLongToGrid.y;
        final int n = 1100;
        if (y < 1) {
            return n;
        }
        if (y > 124) {
            return n;
        }
        if (x < 1) {
            return n;
        }
        if (x > 233) {
            return n;
        }
        if (y > 101 && y < 144) {
            return 1001;
        }
        final int n2 = 1002;
        if (y < 30 && x < 85) {
            return n2;
        }
        if (y < 20 && x > 223) {
            return n2;
        }
        if (y < 10 && x < 88) {
            return n2;
        }
        if (y < 30 && x > 223) {
            return n2;
        }
        if (y < 45 && x < 81) {
            return n2;
        }
        if (y < 35 && x > 223) {
            return n2;
        }
        if (y < 50 && x > 223) {
            return n2;
        }
        if (y < 51 && x < 76) {
            return n2;
        }
        if (y < 58 && x > 12 && x < 76) {
            return n2;
        }
        if (x < 224 && x > 174) {
            final int n3 = 1004;
            if (y < 34) {
                return n3;
            }
            if (y < 35 && x > 196) {
                return n3;
            }
            if (y < 36 && x > 198) {
                return n3;
            }
            if (y < 36 && x < 191) {
                return n3;
            }
            if (y < 37 && x < 186) {
                return n3;
            }
        }
        final int n4 = 1003;
        if (y < 58 && x < 12) {
            return n4;
        }
        if (y < 58 && x > 210) {
            return n4;
        }
        if (y < 61 && x > 210) {
            return n4;
        }
        if (y > 60 && x > 200) {
            return n4;
        }
        if (y < 102 && x < 30) {
            return n4;
        }
        final int n5 = 1005;
        if (y > 57 && x < 201 && x > 149) {
            return n5;
        }
        if (y > 36 && x < 211 && x > 154) {
            return n5;
        }
        if (y > 35 && x < 211 && x > 184) {
            return n5;
        }
        if (y > 34 && x < 200 && x > 189) {
            return n5;
        }
        if (y > 33 && x < 198 && x > 189) {
            return n5;
        }
        final int n6 = 1006;
        if (y > 57 && y < 123 && x > 65) {
            return n6;
        }
        if (y > 55 && x > 74 && x < 136) {
            return n6;
        }
        if (y > 53 && x > 114 && x < 136) {
            return n6;
        }
        final int n7 = 1008;
        if (y > 57 && x < 66 && x > 29) {
            return n7;
        }
        return 1007;
    }
    
    static {
        CreateProjection.wrs2 = new WRS2Model();
    }
}

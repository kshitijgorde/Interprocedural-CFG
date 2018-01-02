// 
// Decompiled by Procyon v0.5.30
// 

package netcharts.graphics;

public class NFCoord
{
    public static final int PIXEL = 9;
    public static final int PERCENT = 10;
    public static final int AXIS = -1;
    public static final String PIXEL_STRING = "PIXEL";
    public static final String PERCENT_STRING = "PERCENT";
    
    public static int getMapType(String trim) {
        if (trim == null || trim.trim().length() == 0) {
            return -1;
        }
        trim = trim.trim();
        if (trim.equalsIgnoreCase("PIXEL")) {
            return 9;
        }
        if (trim.equalsIgnoreCase("PERCENT")) {
            return 10;
        }
        return -1;
    }
    
    public static double getValue(final Object o, final String s, final NFAxisMap nfAxisMap) {
        if (o == null) {
            return 0.0;
        }
        switch (getMapType(s)) {
            default: {
                return ((Number)o).doubleValue();
            }
            case -1: {
                final NFAxis nfAxis = (nfAxisMap == null) ? null : nfAxisMap.getAxis(s);
                if (nfAxis == null) {
                    return 0.0;
                }
                return nfAxis.getValue(o);
            }
        }
    }
    
    public static double getValue(double n, final String s, final NFAxisMap nfAxisMap, final int n2) {
        switch (getMapType(s)) {
            default: {
                return n;
            }
            case 10: {
                if (n < 0.0) {
                    n = 0.0;
                }
                if (n >= n2) {
                    n = n2 - 1.0;
                }
                return n / n2;
            }
            case -1: {
                final NFAxis nfAxis = (nfAxisMap == null) ? null : nfAxisMap.getAxis(s);
                if (nfAxis == null) {
                    return n;
                }
                return nfAxis.unMapValue(n);
            }
        }
    }
    
    public static double getPixel(final Object o, final String s, final NFAxisMap nfAxisMap, final int n, final double n2) {
        if (o == null || (o instanceof Double && ((Double)o).isNaN())) {
            return n2;
        }
        final double value = getValue(o, s, nfAxisMap);
        switch (getMapType(s)) {
            default: {
                return value;
            }
            case 10: {
                if (value <= 1.0) {
                    return n * value;
                }
                return n * (value / 100.0);
            }
            case -1: {
                final NFAxis nfAxis = (nfAxisMap == null) ? null : nfAxisMap.getAxis(s);
                if (nfAxis == null) {
                    return n2;
                }
                switch (nfAxis.getTicPosition()) {
                    case 1:
                    case 4: {
                        return nfAxis.mapValue(value).x;
                    }
                    case 2:
                    case 3: {
                        return nfAxis.mapValue(value).y;
                    }
                    default: {
                        return n2;
                    }
                }
                break;
            }
        }
    }
}

// 
// Decompiled by Procyon v0.5.30
// 

package mapapplet;

public class MapProjection implements Cloneable
{
    private float lat0;
    private float lon0;
    private float x0;
    private float y0;
    private float lat2;
    private float lon2;
    private float x2;
    private float y2;
    private float xPerLon;
    private float yPerLat;
    protected float xLoc1;
    protected float xLoc2;
    protected float yLoc1;
    protected float yLoc2;
    protected float maxLat;
    private float pxPerLat;
    public String hemisphere;
    public int projection;
    public static int RECTANGLE_PROJECTION;
    public static int POLAR_PROJECTION;
    
    public MapProjection(final float x1, final float y1, final float lat1, final float lon1, float x2, float y2, float lat2, float lon2) {
        this.xPerLon = (x2 - x1) / (lon2 - lon1);
        this.yPerLat = (y2 - y1) / (lat2 - lat1);
        this.lat0 = lat1;
        this.lon0 = lon1;
        this.x0 = x1;
        this.y0 = y1;
        this.hemisphere = "n";
        lat2 = lat2;
        lon2 = lon2;
        x2 = x2;
        y2 = y2;
        this.projection = MapProjection.RECTANGLE_PROJECTION;
    }
    
    public MapProjection(final float x1, final float y1, final float x2, final float y2, final float ml, final String hemisph) {
        this.hemisphere = hemisph;
        this.pxPerLat = (x2 - x1) / 2.0f / (((hemisph.compareTo("n") == 0) ? 90 : -90) - ml);
        this.xLoc1 = x1;
        this.xLoc2 = x2;
        this.yLoc1 = y1;
        this.yLoc2 = y2;
        this.maxLat = ml;
        this.projection = MapProjection.POLAR_PROJECTION;
    }
    
    public float[] ll2xy(final float lat, float lon) {
        if (this.projection == MapProjection.RECTANGLE_PROJECTION) {
            final float[] res = { this.x0 + this.xPerLon * (lon - this.lon0), this.y0 + this.yPerLat * (lat - this.lat0) };
            return res;
        }
        if (this.projection == MapProjection.POLAR_PROJECTION) {
            final float[] res = new float[2];
            if (this.hemisphere.compareTo("n") == 0) {
                res[0] = (this.xLoc2 - this.xLoc1) / 2.0f + (90.0f - lat) * (float)Math.sin(lon * 3.141592653589793 / 180.0) * this.pxPerLat;
                res[1] = (this.yLoc2 - this.yLoc1) / 2.0f + (90.0f - lat) * (float)Math.cos(lon * 3.141592653589793 / 180.0) * this.pxPerLat;
            }
            if (this.hemisphere.compareTo("s") == 0) {
                lon -= 180.0f;
                lon = -lon;
                res[0] = (this.xLoc2 - this.xLoc1) / 2.0f - (-90.0f - lat) * (float)Math.sin(lon * 3.141592653589793 / 180.0) * this.pxPerLat;
                res[1] = (this.yLoc2 - this.yLoc1) / 2.0f - (-90.0f - lat) * (float)Math.cos(lon * 3.141592653589793 / 180.0) * this.pxPerLat;
            }
            return res;
        }
        return null;
    }
    
    public float[] xy2ll(final float x, final float y) {
        if (this.projection == MapProjection.RECTANGLE_PROJECTION) {
            final float[] res = { this.lat0 + (y - this.y0) / this.yPerLat, this.lon0 + (x - this.x0) / this.xPerLon };
            return res;
        }
        if (this.projection == MapProjection.POLAR_PROJECTION) {
            final float[] res = new float[2];
            if (this.hemisphere.compareTo("n") == 0) {
                res[0] = 90.0f - (float)Math.sqrt(Math.pow((x - (this.xLoc2 - this.xLoc1) / 2.0f) / this.pxPerLat, 2.0) + Math.pow((y - (this.yLoc2 - this.yLoc1) / 2.0f) / this.pxPerLat, 2.0));
                res[1] = -(float)Math.atan(((this.xLoc2 - this.xLoc1) / 2.0f - x) / this.pxPerLat / ((y - (this.yLoc2 - this.yLoc1) / 2.0f) / this.pxPerLat));
                if (y < (this.yLoc2 - this.yLoc1) / 2.0f && x > (this.xLoc2 - this.xLoc1) / 2.0f) {
                    final float[] array = res;
                    final int n = 1;
                    array[n] += 3.141592653589793;
                }
                else if (y < (this.yLoc2 - this.yLoc1) / 2.0f && x < (this.xLoc2 - this.xLoc1) / 2.0f) {
                    final float[] array2 = res;
                    final int n2 = 1;
                    array2[n2] -= 3.141592653589793;
                }
            }
            else {
                res[0] = -90.0f + (float)Math.sqrt(Math.pow((x - (this.xLoc2 - this.xLoc1) / 2.0f) / this.pxPerLat, 2.0) + Math.pow((y - (this.yLoc2 - this.yLoc1) / 2.0f) / this.pxPerLat, 2.0));
                res[1] = (float)Math.atan(((this.xLoc2 - this.xLoc1) / 2.0f - x) / this.pxPerLat / ((y - (this.yLoc2 - this.yLoc1) / 2.0f) / this.pxPerLat));
                if (y < (this.yLoc2 - this.yLoc1) / 2.0f && x > (this.xLoc2 - this.xLoc1) / 2.0f) {
                    final float[] array3 = res;
                    final int n3 = 1;
                    array3[n3] -= 3.141592653589793;
                }
                else if (y < (this.yLoc2 - this.yLoc1) / 2.0f && x < (this.xLoc2 - this.xLoc1) / 2.0f) {
                    final float[] array4 = res;
                    final int n4 = 1;
                    array4[n4] += 3.141592653589793;
                }
            }
            final float[] array5 = res;
            final int n5 = 1;
            array5[n5] *= 57.29577951308232;
            return res;
        }
        return null;
    }
    
    public Object clone() {
        if (this.projection == MapProjection.RECTANGLE_PROJECTION) {
            return new MapProjection(this.x0, this.y0, this.lat0, this.lon0, this.x2, this.y2, this.lat2, this.lon2);
        }
        if (this.projection == MapProjection.POLAR_PROJECTION) {
            return new MapProjection(this.xLoc1, this.yLoc1, this.xLoc2, this.yLoc2, this.maxLat, this.hemisphere);
        }
        return null;
    }
    
    static {
        MapProjection.RECTANGLE_PROJECTION = 0;
        MapProjection.POLAR_PROJECTION = 1;
    }
}

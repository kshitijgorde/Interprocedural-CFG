// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.util;

import java.awt.BasicStroke;
import prefuse.util.collections.IntObjectHashMap;

public class StrokeLib
{
    private static final IntObjectHashMap strokeMap;
    private static int misses;
    private static int lookups;
    public static final float[] DOTS;
    public static final float[] DASHES;
    public static final float[] LONG_DASHES;
    
    public static BasicStroke getStroke(final float n) {
        return getStroke(n, 2, 0);
    }
    
    public static BasicStroke getStroke(final float n, final float[] array) {
        return getStroke(n, 2, 0, 10.0f, array, 0.0f);
    }
    
    public static BasicStroke getStroke(final float n, final int n2, final int n3) {
        return getStroke(n, n2, n3, 10.0f, null, 0.0f);
    }
    
    public static BasicStroke getStroke(final float n, final int n2, final int n3, final float n4, final float[] array, final float n5) {
        final int strokeKey = getStrokeKey(n, n2, n3, n4, array, n5);
        BasicStroke basicStroke;
        if ((basicStroke = (BasicStroke)StrokeLib.strokeMap.get(strokeKey)) == null) {
            basicStroke = new BasicStroke(n, n2, n3, n4, array, n5);
            StrokeLib.strokeMap.put(strokeKey, basicStroke);
            ++StrokeLib.misses;
        }
        ++StrokeLib.lookups;
        return basicStroke;
    }
    
    protected static int getStrokeKey(final float n, final int n2, final int n3, final float n4, final float[] array, final float n5) {
        int n6 = ((Float.floatToIntBits(n) * 31 + n3) * 31 + n2) * 31 + Float.floatToIntBits(n4);
        if (array != null) {
            n6 = n6 * 31 + Float.floatToIntBits(n5);
            for (int i = 0; i < array.length; ++i) {
                n6 = n6 * 31 + Float.floatToIntBits(array[i]);
            }
        }
        return n6;
    }
    
    public static BasicStroke getDerivedStroke(final BasicStroke basicStroke, final float n) {
        if (basicStroke.getLineWidth() == n) {
            return basicStroke;
        }
        return getStroke(n * basicStroke.getLineWidth(), basicStroke.getEndCap(), basicStroke.getLineJoin(), basicStroke.getMiterLimit(), basicStroke.getDashArray(), basicStroke.getDashPhase());
    }
    
    public static int getCacheMissCount() {
        return StrokeLib.misses;
    }
    
    public static int getCacheLookupCount() {
        return StrokeLib.lookups;
    }
    
    public static void clearCache() {
        StrokeLib.strokeMap.clear();
    }
    
    static {
        strokeMap = new IntObjectHashMap();
        StrokeLib.misses = 0;
        StrokeLib.lookups = 0;
        DOTS = new float[] { 1.0f, 2.0f };
        DASHES = new float[] { 5.0f, 5.0f };
        LONG_DASHES = new float[] { 10.0f, 10.0f };
    }
}

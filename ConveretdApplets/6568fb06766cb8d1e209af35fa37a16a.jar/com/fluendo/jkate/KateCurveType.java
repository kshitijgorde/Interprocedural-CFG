// 
// Decompiled by Procyon v0.5.30
// 

package com.fluendo.jkate;

public class KateCurveType
{
    public static final KateCurveType kate_curve_none;
    public static final KateCurveType kate_curve_static;
    public static final KateCurveType kate_curve_linear;
    public static final KateCurveType kate_curve_catmull_rom_spline;
    public static final KateCurveType kate_curve_bezier_cubic_spline;
    public static final KateCurveType kate_curve_bspline;
    private static final KateCurveType[] list;
    
    public static KateCurveType CreateCurveType(final int n) throws KateException {
        if (n < 0 || n >= KateCurveType.list.length) {
            throw new KateException("Curve type " + n + " out of bounds");
        }
        return KateCurveType.list[n];
    }
    
    static {
        kate_curve_none = new KateCurveType();
        kate_curve_static = new KateCurveType();
        kate_curve_linear = new KateCurveType();
        kate_curve_catmull_rom_spline = new KateCurveType();
        kate_curve_bezier_cubic_spline = new KateCurveType();
        kate_curve_bspline = new KateCurveType();
        list = new KateCurveType[] { KateCurveType.kate_curve_none, KateCurveType.kate_curve_static, KateCurveType.kate_curve_linear, KateCurveType.kate_curve_catmull_rom_spline, KateCurveType.kate_curve_bezier_cubic_spline, KateCurveType.kate_curve_bspline };
    }
}

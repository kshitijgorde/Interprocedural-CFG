// 
// Decompiled by Procyon v0.5.30
// 

public class Geometry3d
{
    float I;
    float cos;
    float drawCilinder;
    boolean drawPixelsPrim;
    
    public static final void drawSegment(final Plot3d plot3d, final float n, final float n2, final float n3, final float n4, final float n5, final float n6, final float n7) {
        drawCilinder(plot3d, n, n2, n3, n4, n5, n6, n7 / 2.0f);
    }
    
    public static final void drawCilinder(final Plot3d plot3d, final float n, final float n2, final float n3, float n4, float n5, float n6, final float n7) {
        final float n8 = (float)Math.sqrt(plot3d.view[0][0] * plot3d.view[0][0] + plot3d.view[1][0] * plot3d.view[1][0] + plot3d.view[2][0] * plot3d.view[2][0]);
        final float n9 = (float)Math.sqrt(plot3d.view[0][1] * plot3d.view[0][1] + plot3d.view[1][1] * plot3d.view[1][1] + plot3d.view[2][1] * plot3d.view[2][1]);
        final float n10 = (float)Math.sqrt(plot3d.view[0][2] * plot3d.view[0][2] + plot3d.view[1][2] * plot3d.view[1][2] + plot3d.view[2][2] * plot3d.view[2][2]);
        final float n11 = n4 - n;
        final float n12 = n5 - n2;
        final float n13 = n6 - n3;
        float n21;
        float n22;
        float n23;
        float n25;
        float n26;
        float n27;
        if (n11 != 0.0f || n12 != 0.0f) {
            final float n14 = n12;
            final float n15 = -n11;
            final float n16 = 0.0f;
            final float n17 = n12 * n16 - n13 * n15;
            final float n18 = n13 * n14 - n11 * n16;
            final float n19 = n11 * n15 - n12 * n14;
            final float n20 = (float)Math.sqrt(n14 * n14 + n15 * n15);
            n21 = n14 / n20 * n7;
            n22 = n15 / n20 * n7;
            n23 = n16 / n20 * n7;
            final float n24 = (float)Math.sqrt(n17 * n17 + n18 * n18 + n19 * n19);
            n25 = n17 / n24 * n7;
            n26 = n18 / n24 * n7;
            n27 = n19 / n24 * n7;
        }
        else {
            n21 = n7;
            n22 = 0.0f;
            n23 = 0.0f;
            n25 = 0.0f;
            n26 = n7;
            n27 = 0.0f;
        }
        final float n28 = n21 / n8;
        final float n29 = n25 / n8;
        final float n30 = n22 / n9;
        final float n31 = n26 / n9;
        final float n32 = n23 / n10;
        final float n33 = n27 / n10;
        float n34 = n28;
        float n35 = n30;
        float n36 = n32;
        final int n37 = 8;
        final int n38 = 1;
        final float n39 = n11 / n38;
        final float n40 = n12 / n38;
        final float n41 = n13 / n38;
        float n43;
        final float n42 = n43 = 6.2831855f / n37;
        for (int i = 1; i <= n37; ++i) {
            final float n44 = n34;
            final float n45 = n35;
            final float n46 = n36;
            final float n47 = (float)Math.cos(n43);
            final float n48 = (float)Math.sin(n43);
            n34 = n47 * n28 + n48 * n29;
            n35 = n47 * n30 + n48 * n31;
            n36 = n47 * n32 + n48 * n33;
            plot3d.drawTriangle(true, n44 + n, n45 + n2, n46 + n3, n34 + n, n35 + n2, n36 + n3, n, n2, n3);
            n4 = n;
            n5 = n2;
            n6 = n3;
            for (int j = 1; j <= n38; ++j) {
                n4 += n39;
                n5 += n40;
                n6 += n41;
                plot3d.drawTriangle(true, n44 + n, n45 + n2, n46 + n3, n34 + n, n35 + n2, n36 + n3, n44 + n4, n45 + n5, n46 + n6);
                plot3d.drawTriangle(true, n34 + n, n35 + n2, n36 + n3, n34 + n4, n35 + n5, n36 + n6, n44 + n4, n45 + n5, n46 + n6);
            }
            plot3d.drawTriangle(true, n44 + n4, n45 + n5, n46 + n6, n34 + n4, n35 + n5, n36 + n6, n4, n5, n6);
            n43 += n42;
        }
    }
    
    public final void preparePixels(final Plot3d plot3d, final boolean b, final float n, final float n2, final float n3, final Object[] array) {
        final int intValue = (int)array[0];
        final int intValue2 = (int)array[1];
        if (Plot3d.mult3(plot3d.view, n, n2, n3) < 0.0f) {
            this.drawPixelsPrim = false;
            return;
        }
        this.drawPixelsPrim = true;
        float mult0;
        float mult2;
        if (b) {
            mult0 = plot3d.over_x;
            mult2 = plot3d.over_y;
        }
        else {
            mult0 = Plot3d.mult0(plot3d.view, n, n2, n3);
            mult2 = Plot3d.mult1(plot3d.view, n, n2, n3);
        }
        final float n4 = b ? -3.4028235E38f : (Plot3d.mult2(plot3d.view, n, n2, n3) - intValue / 2.0f);
        if (plot3d.setOver) {
            final float n5 = mult0 - plot3d.over_x;
            final float n6 = mult2 - plot3d.over_y;
            if (n5 * n5 + n6 * n6 < intValue * intValue / 4.0f + plot3d.tol) {
                plot3d.overFound = true;
                plot3d.over_z = n4;
            }
        }
        final float i = mult0 - (intValue - 1) / 2.0f;
        final float cos = mult2 - (intValue2 - 1) / 2.0f;
        this.I = i;
        this.cos = cos;
        this.drawCilinder = n4;
    }
    
    public final void drawPixelsPoint(final Plot3d plot3d, final boolean b, final float n, final float n2, final float n3, final Object[] array) {
        this.preparePixels(plot3d, b, n, n2, n3, array);
        this.drawPreparedPixels(plot3d, array);
    }
    
    public final void drawPreparedPixels(final Plot3d plot3d, final Object[] array) {
        if (this.drawPixelsPrim) {
            plot3d.drawPixelsPrim(this.I, this.cos, this.drawCilinder, array);
        }
    }
}

import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Component;
import java.awt.Color;

// 
// Decompiled by Procyon v0.5.30
// 

public class NorthArrowMapLayer extends MapLayer
{
    private imgViewer applet;
    private double northAngle;
    private boolean canDisplay;
    
    public NorthArrowMapLayer(final imgViewer applet, final int n) {
        super(applet.imgArea, "North Arrow", Color.WHITE, n, true);
        this.applet = applet;
    }
    
    @Override
    public int setDisplayAreaUsingLatLong(final LatLong latLong, final LatLong latLong2, final int n) {
        return 0;
    }
    
    @Override
    public int setDisplayAreaUsingProjCoords(final Point point, final Point point2, final int n) {
        return 0;
    }
    
    @Override
    public void read(final CancelLoad cancelLoad, final Point point, final int n, final MapLayerLoadingCallback mapLayerLoadingCallback) {
    }
    
    @Override
    public void clip(final Point point, final int n, final Dimension dimension, final ProjectionTransformation projectionTransformation) {
        final int n2 = point.x + dimension.width * n;
        final int n3 = point.y - dimension.height * n;
        int n4 = point.x + (n2 - point.x) / 2;
        int n5 = point.y - (point.y - n3) / 2;
        LatLong latLong = projectionTransformation.projToLatLong(n4, n5);
        if (latLong == null) {
            for (int i = 0; i < 2; ++i) {
                for (int j = 0; j < 2; ++j) {
                    n4 = point.x + (n2 - point.x) / 3 * (i + 1);
                    n5 = point.y - (point.y - n3) / 3 * (j + 1);
                    latLong = projectionTransformation.projToLatLong(n4, n5);
                    if (latLong != null) {
                        break;
                    }
                }
                if (latLong != null) {
                    break;
                }
            }
        }
        if (latLong == null) {
            this.canDisplay = false;
            return;
        }
        final Point latLongToProj = projectionTransformation.latLongToProj(new LatLong(latLong.latitude + 1.0, latLong.longitude));
        this.northAngle = -Math.atan2(n4 - latLongToProj.x, latLongToProj.y - n5);
        this.canDisplay = true;
    }
    
    @Override
    public void draw(final Graphics graphics) {
        if (!this.canDisplay) {
            return;
        }
        graphics.setFont(this.applet.largeBoldFont);
        final double sin = Math.sin(this.northAngle);
        final double cos = Math.cos(this.northAngle);
        final int n = 50;
        final int n2 = 60;
        final int n3 = 0;
        final int n4 = 0;
        final int n5 = 6;
        final int n6 = 4;
        final int n7 = 3;
        final int n8 = 18;
        final double n9 = 2.0;
        final int n10 = 18;
        final double[] array = { n3, n3 - n5, n3 - n9, n3 - n9, n3 + n9, n3 + n9, n3 + n5 };
        final double[] array2 = { n4 - n8, n4 + n6, n4 + n6 - n7, n4 + n10, n4 + n10, n4 + n6 - n7, n4 + n6 };
        final Point viewPosition = this.applet.imgScroll.getViewport().getViewPosition();
        final int n11 = viewPosition.x + n;
        final int n12 = viewPosition.y + n2;
        final int[] array3 = new int[array.length];
        final int[] array4 = new int[array2.length];
        for (int i = 0; i < array.length; ++i) {
            array3[i] = n11 + (int)Math.round(array[i] * cos - array2[i] * sin);
            array4[i] = n12 + (int)Math.round(array[i] * sin + array2[i] * cos);
        }
        final FontMetrics fontMetrics = graphics.getFontMetrics();
        final int height = fontMetrics.getHeight();
        final int n13 = -fontMetrics.charWidth('N') / 2;
        final int n14 = -22 - height / 2;
        final int n15 = n11 + (int)Math.round(n13 * cos - n14 * sin);
        final int n16 = n12 + (int)Math.round(n13 * sin + n14 * cos) + height / 4;
        graphics.setColor(Color.BLACK);
        graphics.drawString("N", n15 - 1, n16 - 1);
        graphics.drawString("N", n15 - 1, n16 + 1);
        graphics.drawString("N", n15 + 1, n16 + 1);
        graphics.drawString("N", n15 + 1, n16 - 1);
        graphics.setColor(this.color);
        graphics.fillPolygon(array3, array4, 7);
        graphics.setColor(Color.BLACK);
        graphics.drawPolygon(array3, array4, 7);
        graphics.setColor(this.color);
        graphics.drawString("N", n15, n16);
    }
}

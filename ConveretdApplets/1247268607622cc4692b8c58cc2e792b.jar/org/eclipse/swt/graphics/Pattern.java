// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.graphics;

import org.eclipse.swt.internal.gdip.PointF;
import org.eclipse.swt.internal.win32.OS;
import org.eclipse.swt.internal.gdip.Gdip;
import org.eclipse.swt.SWT;

public class Pattern extends Resource
{
    public int handle;
    
    public Pattern(final Device device, final Image image) {
        super(device);
        if (image == null) {
            SWT.error(4);
        }
        if (image.isDisposed()) {
            SWT.error(5);
        }
        this.device.checkGDIP();
        final int[] gdipImage = image.createGdipImage();
        final int n = gdipImage[0];
        this.handle = Gdip.TextureBrush_new(n, 0, 0.0f, 0.0f, Gdip.Image_GetWidth(n), Gdip.Image_GetHeight(n));
        Gdip.Bitmap_delete(n);
        if (gdipImage[1] != 0) {
            OS.HeapFree(OS.GetProcessHeap(), 0, gdipImage[1]);
        }
        if (this.handle == 0) {
            SWT.error(2);
        }
        this.init();
    }
    
    public Pattern(final Device device, final float n, final float n2, final float n3, final float n4, final Color color, final Color color2) {
        this(device, n, n2, n3, n4, color, 255, color2, 255);
    }
    
    public Pattern(final Device device, final float x, final float y, final float x2, final float y2, final Color color, final int n, final Color color2, final int n2) {
        super(device);
        if (color == null) {
            SWT.error(4);
        }
        if (color.isDisposed()) {
            SWT.error(5);
        }
        if (color2 == null) {
            SWT.error(4);
        }
        if (color2.isDisposed()) {
            SWT.error(5);
        }
        this.device.checkGDIP();
        final int handle = color.handle;
        final int color_new = Gdip.Color_new((n & 0xFF) << 24 | ((handle >> 16 & 0xFF) | (handle & 0xFF00) | (handle & 0xFF) << 16));
        if (x == x2 && y == y2) {
            this.handle = Gdip.SolidBrush_new(color_new);
            if (this.handle == 0) {
                SWT.error(2);
            }
        }
        else {
            final int handle2 = color2.handle;
            final int color_new2 = Gdip.Color_new((n2 & 0xFF) << 24 | ((handle2 >> 16 & 0xFF) | (handle2 & 0xFF00) | (handle2 & 0xFF) << 16));
            final PointF pointF = new PointF();
            pointF.X = x;
            pointF.Y = y;
            final PointF pointF2 = new PointF();
            pointF2.X = x2;
            pointF2.Y = y2;
            this.handle = Gdip.LinearGradientBrush_new(pointF, pointF2, color_new, color_new2);
            if (this.handle == 0) {
                SWT.error(2);
            }
            if (n != 255 || n2 != 255) {
                final int color_new3 = Gdip.Color_new((int)((n & 0xFF) * 0.5f + (n2 & 0xFF) * 0.5f) << 24 | (int)(((handle & 0xFF) >> 0) * 0.5f + ((handle2 & 0xFF) >> 0) * 0.5f) << 16 | (int)(((handle & 0xFF00) >> 8) * 0.5f + ((handle2 & 0xFF00) >> 8) * 0.5f) << 8 | (int)(((handle & 0xFF0000) >> 16) * 0.5f + ((handle2 & 0xFF0000) >> 16) * 0.5f));
                Gdip.LinearGradientBrush_SetInterpolationColors(this.handle, new int[] { color_new, color_new3, color_new2 }, new float[] { 0.0f, 0.5f, 1.0f }, 3);
                Gdip.Color_delete(color_new3);
            }
            Gdip.Color_delete(color_new2);
        }
        Gdip.Color_delete(color_new);
        this.init();
    }
    
    void destroy() {
        switch (Gdip.Brush_GetType(this.handle)) {
            case 0: {
                Gdip.SolidBrush_delete(this.handle);
                break;
            }
            case 1: {
                Gdip.HatchBrush_delete(this.handle);
                break;
            }
            case 4: {
                Gdip.LinearGradientBrush_delete(this.handle);
                break;
            }
            case 2: {
                Gdip.TextureBrush_delete(this.handle);
                break;
            }
        }
        this.handle = 0;
    }
    
    public boolean isDisposed() {
        return this.handle == 0;
    }
    
    public String toString() {
        if (this.isDisposed()) {
            return "Pattern {*DISPOSED*}";
        }
        return "Pattern {" + this.handle + "}";
    }
}

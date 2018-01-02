// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.graphics;

import org.eclipse.swt.internal.win32.OS;
import org.eclipse.swt.internal.gdip.RectF;
import org.eclipse.swt.SWT;
import org.eclipse.swt.internal.gdip.Gdip;
import org.eclipse.swt.internal.gdip.PointF;

public class Path extends Resource
{
    public int handle;
    PointF currentPoint;
    PointF startPoint;
    
    public Path(final Device device) {
        super(device);
        this.currentPoint = new PointF();
        this.startPoint = new PointF();
        this.device.checkGDIP();
        this.handle = Gdip.GraphicsPath_new(0);
        if (this.handle == 0) {
            SWT.error(2);
        }
        this.init();
    }
    
    public Path(final Device device, final Path path, float max) {
        super(device);
        this.currentPoint = new PointF();
        this.startPoint = new PointF();
        if (path == null) {
            SWT.error(4);
        }
        if (path.isDisposed()) {
            SWT.error(5);
        }
        max = Math.max(0.0f, max);
        this.handle = Gdip.GraphicsPath_Clone(path.handle);
        if (max != 0.0f) {
            Gdip.GraphicsPath_Flatten(this.handle, 0, max);
        }
        if (this.handle == 0) {
            SWT.error(2);
        }
        this.init();
    }
    
    public Path(final Device device, final PathData pathData) {
        this(device);
        if (pathData == null) {
            SWT.error(4);
        }
        this.init(pathData);
    }
    
    public void addArc(float n, float n2, float n3, float n4, final float n5, final float n6) {
        if (this.isDisposed()) {
            SWT.error(44);
        }
        if (n3 < 0.0f) {
            n += n3;
            n3 = -n3;
        }
        if (n4 < 0.0f) {
            n2 += n4;
            n4 = -n4;
        }
        if (n3 == 0.0f || n4 == 0.0f || n6 == 0.0f) {
            return;
        }
        if (n3 == n4) {
            Gdip.GraphicsPath_AddArc(this.handle, n, n2, n3, n4, -n5, -n6);
        }
        else {
            final int graphicsPath_new = Gdip.GraphicsPath_new(0);
            if (graphicsPath_new == 0) {
                SWT.error(2);
            }
            final int matrix_new = Gdip.Matrix_new(n3, 0.0f, 0.0f, n4, n, n2);
            if (matrix_new == 0) {
                SWT.error(2);
            }
            Gdip.GraphicsPath_AddArc(graphicsPath_new, 0.0f, 0.0f, 1.0f, 1.0f, -n5, -n6);
            Gdip.GraphicsPath_Transform(graphicsPath_new, matrix_new);
            Gdip.GraphicsPath_AddPath(this.handle, graphicsPath_new, true);
            Gdip.Matrix_delete(matrix_new);
            Gdip.GraphicsPath_delete(graphicsPath_new);
        }
        Gdip.GraphicsPath_GetLastPoint(this.handle, this.currentPoint);
    }
    
    public void addPath(final Path path) {
        if (this.isDisposed()) {
            SWT.error(44);
        }
        if (path == null) {
            SWT.error(4);
        }
        if (path.isDisposed()) {
            SWT.error(5);
        }
        Gdip.GraphicsPath_AddPath(this.handle, path.handle, false);
        this.currentPoint.X = path.currentPoint.X;
        this.currentPoint.Y = path.currentPoint.Y;
    }
    
    public void addRectangle(final float n, final float n2, final float width, final float height) {
        if (this.isDisposed()) {
            SWT.error(44);
        }
        final RectF rectF = new RectF();
        rectF.X = n;
        rectF.Y = n2;
        rectF.Width = width;
        rectF.Height = height;
        Gdip.GraphicsPath_AddRectangle(this.handle, rectF);
        this.currentPoint.X = n;
        this.currentPoint.Y = n2;
    }
    
    public void addString(final String s, final float n, final float y, final Font font) {
        if (this.isDisposed()) {
            SWT.error(44);
        }
        if (font == null) {
            SWT.error(4);
        }
        if (font.isDisposed()) {
            SWT.error(5);
        }
        final int length = s.length();
        final char[] array = new char[length];
        s.getChars(0, length, array, 0);
        final int internal_new_GC = this.device.internal_new_GC(null);
        final int[] array2 = { 0 };
        final int gdipFont = GC.createGdipFont(internal_new_GC, font.handle, 0, this.device.fontCollection, array2, null);
        final PointF pointF = new PointF();
        pointF.X = n - Gdip.Font_GetSize(gdipFont) / 6.0f;
        pointF.Y = y;
        Gdip.GraphicsPath_AddString(this.handle, array, length, array2[0], Gdip.Font_GetStyle(gdipFont), Gdip.Font_GetSize(gdipFont), pointF, 0);
        Gdip.GraphicsPath_GetLastPoint(this.handle, this.currentPoint);
        Gdip.FontFamily_delete(array2[0]);
        Gdip.Font_delete(gdipFont);
        this.device.internal_dispose_GC(internal_new_GC, null);
    }
    
    public void close() {
        if (this.isDisposed()) {
            SWT.error(44);
        }
        Gdip.GraphicsPath_CloseFigure(this.handle);
        this.currentPoint.X = this.startPoint.X;
        this.currentPoint.Y = this.startPoint.Y;
    }
    
    public boolean contains(final float n, final float n2, final GC gc, final boolean b) {
        if (this.isDisposed()) {
            SWT.error(44);
        }
        if (gc == null) {
            SWT.error(4);
        }
        if (gc.isDisposed()) {
            SWT.error(5);
        }
        gc.initGdip();
        gc.checkGC(120);
        Gdip.GraphicsPath_SetFillMode(this.handle, (OS.GetPolyFillMode(gc.handle) == 2) ? 1 : 0);
        if (b) {
            return Gdip.GraphicsPath_IsOutlineVisible(this.handle, n, n2, gc.data.gdipPen, gc.data.gdipGraphics);
        }
        return Gdip.GraphicsPath_IsVisible(this.handle, n, n2, gc.data.gdipGraphics);
    }
    
    public void cubicTo(final float n, final float n2, final float n3, final float n4, final float n5, final float n6) {
        if (this.isDisposed()) {
            SWT.error(44);
        }
        Gdip.GraphicsPath_AddBezier(this.handle, this.currentPoint.X, this.currentPoint.Y, n, n2, n3, n4, n5, n6);
        Gdip.GraphicsPath_GetLastPoint(this.handle, this.currentPoint);
    }
    
    void destroy() {
        Gdip.GraphicsPath_delete(this.handle);
        this.handle = 0;
    }
    
    public void getBounds(final float[] array) {
        if (this.isDisposed()) {
            SWT.error(44);
        }
        if (array == null) {
            SWT.error(4);
        }
        if (array.length < 4) {
            SWT.error(5);
        }
        final RectF rectF = new RectF();
        Gdip.GraphicsPath_GetBounds(this.handle, rectF, 0, 0);
        array[0] = rectF.X;
        array[1] = rectF.Y;
        array[2] = rectF.Width;
        array[3] = rectF.Height;
    }
    
    public void getCurrentPoint(final float[] array) {
        if (this.isDisposed()) {
            SWT.error(44);
        }
        if (array == null) {
            SWT.error(4);
        }
        if (array.length < 2) {
            SWT.error(5);
        }
        array[0] = this.currentPoint.X;
        array[1] = this.currentPoint.Y;
    }
    
    public PathData getPathData() {
        if (this.isDisposed()) {
            SWT.error(44);
        }
        final int graphicsPath_GetPointCount = Gdip.GraphicsPath_GetPointCount(this.handle);
        final byte[] array = new byte[graphicsPath_GetPointCount];
        final float[] points = new float[graphicsPath_GetPointCount * 2];
        Gdip.GraphicsPath_GetPathTypes(this.handle, array, graphicsPath_GetPointCount);
        Gdip.GraphicsPath_GetPathPoints(this.handle, points, graphicsPath_GetPointCount);
        byte[] types = new byte[graphicsPath_GetPointCount * 2];
        int i = 0;
        int n = 0;
        while (i < graphicsPath_GetPointCount) {
            final byte b = array[i];
            int n2 = 0;
            switch (b & 0x7) {
                case 0: {
                    types[n++] = 1;
                    n2 = (((b & 0x80) != 0x0) ? 1 : 0);
                    ++i;
                    break;
                }
                case 1: {
                    types[n++] = 2;
                    n2 = (((b & 0x80) != 0x0) ? 1 : 0);
                    ++i;
                    break;
                }
                case 3: {
                    types[n++] = 4;
                    n2 = (((array[i + 2] & 0x80) != 0x0) ? 1 : 0);
                    i += 3;
                    break;
                }
                default: {
                    ++i;
                    break;
                }
            }
            if (n2 != 0) {
                types[n++] = 5;
            }
        }
        if (n != types.length) {
            final byte[] array2 = new byte[n];
            System.arraycopy(types, 0, array2, 0, n);
            types = array2;
        }
        final PathData pathData = new PathData();
        pathData.types = types;
        pathData.points = points;
        return pathData;
    }
    
    public void lineTo(final float n, final float n2) {
        if (this.isDisposed()) {
            SWT.error(44);
        }
        Gdip.GraphicsPath_AddLine(this.handle, this.currentPoint.X, this.currentPoint.Y, n, n2);
        Gdip.GraphicsPath_GetLastPoint(this.handle, this.currentPoint);
    }
    
    void init(final PathData pathData) {
        final byte[] types = pathData.types;
        final float[] points = pathData.points;
        int i = 0;
        int n = 0;
        while (i < types.length) {
            switch (types[i]) {
                case 1: {
                    this.moveTo(points[n++], points[n++]);
                    break;
                }
                case 2: {
                    this.lineTo(points[n++], points[n++]);
                    break;
                }
                case 4: {
                    this.cubicTo(points[n++], points[n++], points[n++], points[n++], points[n++], points[n++]);
                    break;
                }
                case 3: {
                    this.quadTo(points[n++], points[n++], points[n++], points[n++]);
                    break;
                }
                case 5: {
                    this.close();
                    break;
                }
                default: {
                    this.dispose();
                    SWT.error(5);
                    break;
                }
            }
            ++i;
        }
    }
    
    public boolean isDisposed() {
        return this.handle == 0;
    }
    
    public void moveTo(final float n, final float n2) {
        if (this.isDisposed()) {
            SWT.error(44);
        }
        Gdip.GraphicsPath_StartFigure(this.handle);
        final PointF currentPoint = this.currentPoint;
        this.startPoint.X = n;
        currentPoint.X = n;
        final PointF currentPoint2 = this.currentPoint;
        this.startPoint.Y = n2;
        currentPoint2.Y = n2;
    }
    
    public void quadTo(final float n, final float n2, final float n3, final float n4) {
        if (this.isDisposed()) {
            SWT.error(44);
        }
        final float n5 = this.currentPoint.X + 2.0f * (n - this.currentPoint.X) / 3.0f;
        final float n6 = this.currentPoint.Y + 2.0f * (n2 - this.currentPoint.Y) / 3.0f;
        Gdip.GraphicsPath_AddBezier(this.handle, this.currentPoint.X, this.currentPoint.Y, n5, n6, n5 + (n3 - this.currentPoint.X) / 3.0f, n6 + (n4 - this.currentPoint.Y) / 3.0f, n3, n4);
        Gdip.GraphicsPath_GetLastPoint(this.handle, this.currentPoint);
    }
    
    public String toString() {
        if (this.isDisposed()) {
            return "Path {*DISPOSED*}";
        }
        return "Path {" + this.handle + "}";
    }
}

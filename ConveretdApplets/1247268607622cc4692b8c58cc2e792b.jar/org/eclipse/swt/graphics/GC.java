// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.graphics;

import org.eclipse.swt.SWTException;
import org.eclipse.swt.internal.win32.TRIVERTEX;
import org.eclipse.swt.internal.win32.GRADIENT_RECT;
import org.eclipse.swt.internal.win32.GCP_RESULTS;
import org.eclipse.swt.internal.gdip.RectF;
import org.eclipse.swt.internal.win32.TEXTMETRIC;
import org.eclipse.swt.internal.win32.TEXTMETRICA;
import org.eclipse.swt.internal.win32.TEXTMETRICW;
import org.eclipse.swt.internal.win32.TCHAR;
import org.eclipse.swt.internal.win32.SIZE;
import org.eclipse.swt.internal.win32.LOGPEN;
import org.eclipse.swt.internal.win32.BITMAPINFOHEADER;
import org.eclipse.swt.internal.win32.BLENDFUNCTION;
import org.eclipse.swt.internal.win32.BITMAP;
import org.eclipse.swt.internal.win32.ICONINFO;
import org.eclipse.swt.internal.win32.POINT;
import org.eclipse.swt.internal.gdip.Rect;
import org.eclipse.swt.internal.Compatibility;
import org.eclipse.swt.internal.win32.LOGFONT;
import org.eclipse.swt.internal.win32.LOGFONTA;
import org.eclipse.swt.internal.win32.LOGFONTW;
import org.eclipse.swt.internal.win32.RECT;
import org.eclipse.swt.internal.win32.LOGBRUSH;
import org.eclipse.swt.internal.gdip.PointF;
import org.eclipse.swt.internal.win32.OS;
import org.eclipse.swt.internal.gdip.Gdip;
import org.eclipse.swt.SWT;

public final class GC extends Resource
{
    public int handle;
    Drawable drawable;
    GCData data;
    static final int FOREGROUND = 1;
    static final int BACKGROUND = 2;
    static final int FONT = 4;
    static final int LINE_STYLE = 8;
    static final int LINE_WIDTH = 16;
    static final int LINE_CAP = 32;
    static final int LINE_JOIN = 64;
    static final int LINE_MITERLIMIT = 128;
    static final int FOREGROUND_TEXT = 256;
    static final int BACKGROUND_TEXT = 512;
    static final int BRUSH = 1024;
    static final int PEN = 2048;
    static final int NULL_BRUSH = 4096;
    static final int NULL_PEN = 8192;
    static final int DRAW_OFFSET = 16384;
    static final int DRAW = 22777;
    static final int FILL = 9218;
    static final float[] LINE_DOT_ZERO;
    static final float[] LINE_DASH_ZERO;
    static final float[] LINE_DASHDOT_ZERO;
    static final float[] LINE_DASHDOTDOT_ZERO;
    
    static {
        LINE_DOT_ZERO = new float[] { 3.0f, 3.0f };
        LINE_DASH_ZERO = new float[] { 18.0f, 6.0f };
        LINE_DASHDOT_ZERO = new float[] { 9.0f, 6.0f, 3.0f, 6.0f };
        LINE_DASHDOTDOT_ZERO = new float[] { 9.0f, 3.0f, 3.0f, 3.0f, 3.0f, 3.0f };
    }
    
    GC() {
    }
    
    public GC(final Drawable drawable) {
        this(drawable, 0);
    }
    
    public GC(final Drawable drawable, final int n) {
        if (drawable == null) {
            SWT.error(4);
        }
        final GCData gcData = new GCData();
        gcData.style = checkStyle(n);
        final int internal_new_GC = drawable.internal_new_GC(gcData);
        Device device = gcData.device;
        if (device == null) {
            device = Device.getDevice();
        }
        if (device == null) {
            SWT.error(4);
        }
        final GCData gcData2 = gcData;
        final Device device2 = device;
        gcData2.device = device2;
        this.device = device2;
        this.init(drawable, gcData, internal_new_GC);
        this.init();
    }
    
    static int checkStyle(int n) {
        if ((n & 0x2000000) != 0x0) {
            n &= 0xFBFFFFFF;
        }
        return n & 0x6000000;
    }
    
    void checkGC(final int n) {
        final int state = this.data.state;
        if ((state & n) == n) {
            return;
        }
        int n2 = (state ^ n) & n;
        final GCData data = this.data;
        data.state |= n;
        final int gdipGraphics = this.data.gdipGraphics;
        if (gdipGraphics != 0) {
            int gdipPen = this.data.gdipPen;
            final float lineWidth = this.data.lineWidth;
            if ((n2 & 0x1) != 0x0 || (gdipPen == 0 && (n2 & 0xF8) != 0x0)) {
                if (this.data.gdipFgBrush != 0) {
                    Gdip.SolidBrush_delete(this.data.gdipFgBrush);
                }
                this.data.gdipFgBrush = 0;
                final Pattern foregroundPattern = this.data.foregroundPattern;
                int n3;
                if (foregroundPattern != null) {
                    n3 = foregroundPattern.handle;
                    if ((this.data.style & 0x8000000) != 0x0) {
                        switch (Gdip.Brush_GetType(n3)) {
                            case 2: {
                                n3 = Gdip.Brush_Clone(n3);
                                if (n3 == 0) {
                                    SWT.error(2);
                                }
                                Gdip.TextureBrush_ScaleTransform(n3, -1.0f, 1.0f, 0);
                                this.data.gdipFgBrush = n3;
                                break;
                            }
                        }
                    }
                }
                else {
                    final int foreground = this.data.foreground;
                    final int color_new = Gdip.Color_new(this.data.alpha << 24 | ((foreground >> 16 & 0xFF) | (foreground & 0xFF00) | (foreground & 0xFF) << 16));
                    if (color_new == 0) {
                        SWT.error(2);
                    }
                    n3 = Gdip.SolidBrush_new(color_new);
                    if (n3 == 0) {
                        SWT.error(2);
                    }
                    Gdip.Color_delete(color_new);
                    this.data.gdipFgBrush = n3;
                }
                if (gdipPen != 0) {
                    Gdip.Pen_SetBrush(gdipPen, n3);
                }
                else {
                    final GCData data2 = this.data;
                    final int pen_new = Gdip.Pen_new(n3, lineWidth);
                    data2.gdipPen = pen_new;
                    gdipPen = pen_new;
                }
            }
            if ((n2 & 0x10) != 0x0) {
                Gdip.Pen_SetWidth(gdipPen, lineWidth);
                switch (this.data.lineStyle) {
                    case 6: {
                        n2 |= 0x8;
                        break;
                    }
                }
            }
            if ((n2 & 0x8) != 0x0) {
                float[] array = null;
                float n4 = 0.0f;
                int n5 = 0;
                switch (this.data.lineStyle) {
                    case 3: {
                        n5 = 2;
                        if (lineWidth == 0.0f) {
                            array = GC.LINE_DOT_ZERO;
                            break;
                        }
                        break;
                    }
                    case 2: {
                        n5 = 1;
                        if (lineWidth == 0.0f) {
                            array = GC.LINE_DASH_ZERO;
                            break;
                        }
                        break;
                    }
                    case 4: {
                        n5 = 3;
                        if (lineWidth == 0.0f) {
                            array = GC.LINE_DASHDOT_ZERO;
                            break;
                        }
                        break;
                    }
                    case 5: {
                        n5 = 4;
                        if (lineWidth == 0.0f) {
                            array = GC.LINE_DASHDOTDOT_ZERO;
                            break;
                        }
                        break;
                    }
                    case 6: {
                        if (this.data.lineDashes != null) {
                            n4 = this.data.lineDashesOffset / Math.max(1.0f, lineWidth);
                            array = new float[this.data.lineDashes.length * 2];
                            for (int i = 0; i < this.data.lineDashes.length; ++i) {
                                final float n6 = this.data.lineDashes[i] / Math.max(1.0f, lineWidth);
                                array[i] = n6;
                                array[i + this.data.lineDashes.length] = n6;
                            }
                            break;
                        }
                        break;
                    }
                }
                if (array != null) {
                    Gdip.Pen_SetDashPattern(gdipPen, array, array.length);
                    Gdip.Pen_SetDashStyle(gdipPen, 5);
                    Gdip.Pen_SetDashOffset(gdipPen, n4);
                }
                else {
                    Gdip.Pen_SetDashStyle(gdipPen, n5);
                }
            }
            if ((n2 & 0x80) != 0x0) {
                Gdip.Pen_SetMiterLimit(gdipPen, this.data.lineMiterLimit);
            }
            if ((n2 & 0x40) != 0x0) {
                int n7 = 0;
                switch (this.data.lineJoin) {
                    case 1: {
                        n7 = 0;
                        break;
                    }
                    case 3: {
                        n7 = 1;
                        break;
                    }
                    case 2: {
                        n7 = 2;
                        break;
                    }
                }
                Gdip.Pen_SetLineJoin(gdipPen, n7);
            }
            if ((n2 & 0x20) != 0x0) {
                int n8 = 0;
                int n9 = 0;
                switch (this.data.lineCap) {
                    case 1: {
                        n9 = 0;
                        break;
                    }
                    case 2: {
                        n9 = 2;
                        n8 = 2;
                        break;
                    }
                    case 3: {
                        n9 = 1;
                        break;
                    }
                }
                Gdip.Pen_SetLineCap(gdipPen, n9, n9, n8);
            }
            if ((n2 & 0x2) != 0x0) {
                if (this.data.gdipBgBrush != 0) {
                    Gdip.SolidBrush_delete(this.data.gdipBgBrush);
                }
                this.data.gdipBgBrush = 0;
                final Pattern backgroundPattern = this.data.backgroundPattern;
                if (backgroundPattern != null) {
                    this.data.gdipBrush = backgroundPattern.handle;
                    if ((this.data.style & 0x8000000) != 0x0) {
                        switch (Gdip.Brush_GetType(this.data.gdipBrush)) {
                            case 2: {
                                final int brush_Clone = Gdip.Brush_Clone(this.data.gdipBrush);
                                if (brush_Clone == 0) {
                                    SWT.error(2);
                                }
                                Gdip.TextureBrush_ScaleTransform(brush_Clone, -1.0f, 1.0f, 0);
                                final GCData data3 = this.data;
                                final GCData data4 = this.data;
                                final int n10 = brush_Clone;
                                data4.gdipBgBrush = n10;
                                data3.gdipBrush = n10;
                                break;
                            }
                        }
                    }
                }
                else {
                    final int background = this.data.background;
                    final int color_new2 = Gdip.Color_new(this.data.alpha << 24 | ((background >> 16 & 0xFF) | (background & 0xFF00) | (background & 0xFF) << 16));
                    if (color_new2 == 0) {
                        SWT.error(2);
                    }
                    final int solidBrush_new = Gdip.SolidBrush_new(color_new2);
                    if (solidBrush_new == 0) {
                        SWT.error(2);
                    }
                    Gdip.Color_delete(color_new2);
                    final GCData data5 = this.data;
                    final GCData data6 = this.data;
                    final int n11 = solidBrush_new;
                    data6.gdipBgBrush = n11;
                    data5.gdipBrush = n11;
                }
            }
            if ((n2 & 0x4) != 0x0) {
                final Font font = this.data.font;
                OS.SelectObject(this.handle, font.handle);
                final int[] array2 = { 0 };
                final int gdipFont = createGdipFont(this.handle, font.handle, gdipGraphics, this.device.fontCollection, null, array2);
                if (array2[0] != 0) {
                    OS.SelectObject(this.handle, array2[0]);
                }
                if (this.data.hGDIFont != 0) {
                    OS.DeleteObject(this.data.hGDIFont);
                }
                this.data.hGDIFont = array2[0];
                if (this.data.gdipFont != 0) {
                    Gdip.Font_delete(this.data.gdipFont);
                }
                this.data.gdipFont = gdipFont;
            }
            if ((n2 & 0x4000) != 0x0) {
                final GCData data7 = this.data;
                final GCData data8 = this.data;
                final float n12 = 0.0f;
                data8.gdipYOffset = n12;
                data7.gdipXOffset = n12;
                final int matrix_new = Gdip.Matrix_new(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f);
                final PointF pointF3;
                final PointF pointF2;
                final PointF pointF = pointF2 = (pointF3 = new PointF());
                final float n13 = 1.0f;
                pointF2.Y = n13;
                pointF3.X = n13;
                Gdip.Graphics_GetTransform(gdipGraphics, matrix_new);
                Gdip.Matrix_TransformVectors(matrix_new, pointF, 1);
                Gdip.Matrix_delete(matrix_new);
                float x = pointF.X;
                if (x < 0.0f) {
                    x = -x;
                }
                final float n14 = this.data.lineWidth * x;
                if (n14 == 0.0f || (int)n14 % 2 == 1) {
                    this.data.gdipXOffset = 0.5f / x;
                }
                float y = pointF.Y;
                if (y < 0.0f) {
                    y = -y;
                }
                final float n15 = this.data.lineWidth * y;
                if (n15 == 0.0f || (int)n15 % 2 == 1) {
                    this.data.gdipYOffset = 0.5f / y;
                }
            }
            return;
        }
        if ((n2 & 0x79) != 0x0) {
            final int foreground2 = this.data.foreground;
            final int n16 = (int)this.data.lineWidth;
            int[] array3 = null;
            int n17 = 0;
            switch (this.data.lineStyle) {
                case 2: {
                    n17 = 1;
                    break;
                }
                case 3: {
                    n17 = 2;
                    break;
                }
                case 4: {
                    n17 = 3;
                    break;
                }
                case 5: {
                    n17 = 4;
                    break;
                }
                case 6: {
                    if (this.data.lineDashes != null) {
                        n17 = 7;
                        array3 = new int[this.data.lineDashes.length];
                        for (int j = 0; j < array3.length; ++j) {
                            array3[j] = (int)this.data.lineDashes[j];
                        }
                        break;
                    }
                    break;
                }
            }
            if ((n2 & 0x8) != 0x0) {
                OS.SetBkMode(this.handle, (this.data.lineStyle == 1) ? 2 : 1);
            }
            int n18 = 0;
            switch (this.data.lineJoin) {
                case 1: {
                    n18 = 8192;
                    break;
                }
                case 2: {
                    n18 = 0;
                    break;
                }
                case 3: {
                    n18 = 4096;
                    break;
                }
            }
            int n19 = 0;
            switch (this.data.lineCap) {
                case 2: {
                    n19 = 0;
                    break;
                }
                case 1: {
                    n19 = 512;
                    break;
                }
                case 3: {
                    n19 = 256;
                    break;
                }
            }
            final int n20 = n17 | n18 | n19;
            int n21;
            if (OS.IsWinCE || (n16 == 0 && n17 != 7) || n20 == 0) {
                n21 = OS.CreatePen(n20 & 0xF, n16, foreground2);
            }
            else {
                final LOGBRUSH logbrush = new LOGBRUSH();
                logbrush.lbStyle = 0;
                logbrush.lbColor = foreground2;
                n21 = OS.ExtCreatePen(n20 | 0x10000, Math.max(1, n16), logbrush, (array3 != null) ? array3.length : 0, array3);
            }
            OS.SelectObject(this.handle, n21);
            final GCData data9 = this.data;
            data9.state |= 0x800;
            final GCData data10 = this.data;
            data10.state &= 0xFFFFDFFF;
            if (this.data.hPen != 0) {
                OS.DeleteObject(this.data.hPen);
            }
            final GCData data11 = this.data;
            final GCData data12 = this.data;
            final int n22 = n21;
            data12.hOldPen = n22;
            data11.hPen = n22;
        }
        else if ((n2 & 0x800) != 0x0) {
            OS.SelectObject(this.handle, this.data.hOldPen);
            final GCData data13 = this.data;
            data13.state &= 0xFFFFDFFF;
        }
        else if ((n2 & 0x2000) != 0x0) {
            this.data.hOldPen = OS.SelectObject(this.handle, OS.GetStockObject(8));
            final GCData data14 = this.data;
            data14.state &= 0xFFFFF7FF;
        }
        if ((n2 & 0x2) != 0x0) {
            final int createSolidBrush = OS.CreateSolidBrush(this.data.background);
            OS.SelectObject(this.handle, createSolidBrush);
            final GCData data15 = this.data;
            data15.state |= 0x400;
            final GCData data16 = this.data;
            data16.state &= 0xFFFFEFFF;
            if (this.data.hBrush != 0) {
                OS.DeleteObject(this.data.hBrush);
            }
            final GCData data17 = this.data;
            final GCData data18 = this.data;
            final int n23 = createSolidBrush;
            data18.hBrush = n23;
            data17.hOldBrush = n23;
        }
        else if ((n2 & 0x400) != 0x0) {
            OS.SelectObject(this.handle, this.data.hOldBrush);
            final GCData data19 = this.data;
            data19.state &= 0xFFFFEFFF;
        }
        else if ((n2 & 0x1000) != 0x0) {
            this.data.hOldBrush = OS.SelectObject(this.handle, OS.GetStockObject(5));
            final GCData data20 = this.data;
            data20.state &= 0xFFFFFBFF;
        }
        if ((n2 & 0x200) != 0x0) {
            OS.SetBkColor(this.handle, this.data.background);
        }
        if ((n2 & 0x100) != 0x0) {
            OS.SetTextColor(this.handle, this.data.foreground);
        }
        if ((n2 & 0x4) != 0x0) {
            OS.SelectObject(this.handle, this.data.font.handle);
        }
    }
    
    public void copyArea(final Image image, final int n, final int n2) {
        if (this.handle == 0) {
            SWT.error(44);
        }
        if (image == null) {
            SWT.error(4);
        }
        if (image.type != 0 || image.isDisposed()) {
            SWT.error(5);
        }
        final Rectangle bounds = image.getBounds();
        final int createCompatibleDC = OS.CreateCompatibleDC(this.handle);
        final int selectObject = OS.SelectObject(createCompatibleDC, image.handle);
        OS.BitBlt(createCompatibleDC, 0, 0, bounds.width, bounds.height, this.handle, n, n2, 13369376);
        OS.SelectObject(createCompatibleDC, selectObject);
        OS.DeleteDC(createCompatibleDC);
    }
    
    public void copyArea(final int n, final int n2, final int n3, final int n4, final int n5, final int n6) {
        this.copyArea(n, n2, n3, n4, n5, n6, true);
    }
    
    public void copyArea(final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final boolean b) {
        if (this.handle == 0) {
            SWT.error(44);
        }
        final int hwnd = this.data.hwnd;
        if (hwnd == 0) {
            OS.BitBlt(this.handle, n5, n6, n3, n4, this.handle, n, n2, 13369376);
        }
        else {
            RECT rect = null;
            final int createRectRgn = OS.CreateRectRgn(0, 0, 0, 0);
            if (OS.GetClipRgn(this.handle, createRectRgn) == 1) {
                rect = new RECT();
                OS.GetRgnBox(createRectRgn, rect);
            }
            OS.DeleteObject(createRectRgn);
            final RECT rect2 = new RECT();
            OS.SetRect(rect2, n, n2, n + n3, n2 + n4);
            if (OS.ScrollWindowEx(hwnd, n5 - n, n6 - n2, rect2, rect, 0, null, b ? 6 : 0) == 0 && OS.IsWinCE) {
                OS.BitBlt(this.handle, n5, n6, n3, n4, this.handle, n, n2, 13369376);
                if (b) {
                    final int n7 = n5 - n;
                    final int n8 = n6 - n2;
                    if (n5 + n3 < n || n + n3 < n5 || n6 + n4 < n2 || n2 + n4 < n6) {
                        OS.InvalidateRect(hwnd, rect2, true);
                    }
                    else {
                        if (n7 != 0) {
                            int n9 = n5 - n7;
                            if (n7 < 0) {
                                n9 = n5 + n3;
                            }
                            OS.SetRect(rect2, n9, n2, n9 + Math.abs(n7), n2 + n4);
                            OS.InvalidateRect(hwnd, rect2, true);
                        }
                        if (n8 != 0) {
                            int n10 = n6 - n8;
                            if (n8 < 0) {
                                n10 = n6 + n4;
                            }
                            OS.SetRect(rect2, n, n10, n + n3, n10 + Math.abs(n8));
                            OS.InvalidateRect(hwnd, rect2, true);
                        }
                    }
                }
            }
        }
    }
    
    static int createGdipFont(final int n, final int n2, final int n3, final int n4, final int[] array, final int[] array2) {
        int n5 = Gdip.Font_new(n, n2);
        if (n5 == 0) {
            SWT.error(2);
        }
        int n6 = 0;
        if (!Gdip.Font_IsAvailable(n5)) {
            Gdip.Font_delete(n5);
            final LOGFONT logfont = OS.IsUnicode ? new LOGFONTW() : new LOGFONTA();
            OS.GetObject(n2, LOGFONT.sizeof, logfont);
            final int abs = Math.abs(logfont.lfHeight);
            int n7 = 0;
            if (logfont.lfWeight == 700) {
                n7 |= 0x1;
            }
            if (logfont.lfItalic != 0) {
                n7 |= 0x2;
            }
            char[] lfFaceName;
            if (OS.IsUnicode) {
                lfFaceName = ((LOGFONTW)logfont).lfFaceName;
            }
            else {
                lfFaceName = new char[32];
                final byte[] lfFaceName2 = ((LOGFONTA)logfont).lfFaceName;
                OS.MultiByteToWideChar(0, 1, lfFaceName2, lfFaceName2.length, lfFaceName, lfFaceName.length);
            }
            int n8;
            for (n8 = 0; n8 < lfFaceName.length && lfFaceName[n8] != '\0'; ++n8) {}
            String s = new String(lfFaceName, 0, n8);
            if (Compatibility.equalsIgnoreCase(s, "Courier")) {
                s = "Courier New";
            }
            final char[] array3 = new char[s.length() + 1];
            s.getChars(0, s.length(), array3, 0);
            if (n4 != 0) {
                n6 = Gdip.FontFamily_new(array3, n4);
                if (!Gdip.FontFamily_IsAvailable(n6)) {
                    Gdip.FontFamily_delete(n6);
                    n6 = Gdip.FontFamily_new(array3, 0);
                    if (!Gdip.FontFamily_IsAvailable(n6)) {
                        Gdip.FontFamily_delete(n6);
                        n6 = 0;
                    }
                }
            }
            if (n6 != 0) {
                n5 = Gdip.Font_new(n6, abs, n7, 2);
            }
            else {
                n5 = Gdip.Font_new(array3, abs, n7, 2, 0);
            }
            if (array2 != null && n5 != 0) {
                final int getProcessHeap = OS.GetProcessHeap();
                final int heapAlloc = OS.HeapAlloc(getProcessHeap, 8, LOGFONTW.sizeof);
                Gdip.Font_GetLogFontW(n5, n3, heapAlloc);
                array2[0] = OS.CreateFontIndirectW(heapAlloc);
                OS.HeapFree(getProcessHeap, 0, heapAlloc);
            }
        }
        if (array != null && n5 != 0) {
            if (n6 == 0) {
                n6 = Gdip.FontFamily_new();
                Gdip.Font_GetFamily(n5, n6);
            }
            array[0] = n6;
        }
        else if (n6 != 0) {
            Gdip.FontFamily_delete(n6);
        }
        if (n5 == 0) {
            SWT.error(2);
        }
        return n5;
    }
    
    static void destroyGdipBrush(final int n) {
        switch (Gdip.Brush_GetType(n)) {
            case 0: {
                Gdip.SolidBrush_delete(n);
                break;
            }
            case 1: {
                Gdip.HatchBrush_delete(n);
                break;
            }
            case 4: {
                Gdip.LinearGradientBrush_delete(n);
                break;
            }
            case 2: {
                Gdip.TextureBrush_delete(n);
                break;
            }
        }
    }
    
    void destroy() {
        final boolean b = this.data.gdipGraphics != 0;
        this.disposeGdip();
        if (b && (this.data.style & 0x8000000) != 0x0) {
            OS.SetLayout(this.handle, OS.GetLayout(this.handle) | 0x1);
        }
        if (this.data.hPen != 0) {
            OS.SelectObject(this.handle, OS.GetStockObject(8));
            OS.DeleteObject(this.data.hPen);
            this.data.hPen = 0;
        }
        if (this.data.hBrush != 0) {
            OS.SelectObject(this.handle, OS.GetStockObject(5));
            OS.DeleteObject(this.data.hBrush);
            this.data.hBrush = 0;
        }
        final int hNullBitmap = this.data.hNullBitmap;
        if (hNullBitmap != 0) {
            OS.SelectObject(this.handle, hNullBitmap);
            this.data.hNullBitmap = 0;
        }
        final Image image = this.data.image;
        if (image != null) {
            image.memGC = null;
        }
        if (this.drawable != null) {
            this.drawable.internal_dispose_GC(this.handle, this.data);
        }
        this.drawable = null;
        this.handle = 0;
        this.data.image = null;
        this.data.ps = null;
        this.data = null;
    }
    
    void disposeGdip() {
        if (this.data.gdipPen != 0) {
            Gdip.Pen_delete(this.data.gdipPen);
        }
        if (this.data.gdipBgBrush != 0) {
            destroyGdipBrush(this.data.gdipBgBrush);
        }
        if (this.data.gdipFgBrush != 0) {
            destroyGdipBrush(this.data.gdipFgBrush);
        }
        if (this.data.gdipFont != 0) {
            Gdip.Font_delete(this.data.gdipFont);
        }
        if (this.data.hGDIFont != 0) {
            OS.DeleteObject(this.data.hGDIFont);
        }
        if (this.data.gdipGraphics != 0) {
            Gdip.Graphics_delete(this.data.gdipGraphics);
        }
        final GCData data = this.data;
        final GCData data2 = this.data;
        final GCData data3 = this.data;
        final GCData data4 = this.data;
        final GCData data5 = this.data;
        final GCData data6 = this.data;
        final GCData data7 = this.data;
        final boolean gdipGraphics = false;
        data7.hGDIFont = (gdipGraphics ? 1 : 0);
        data6.gdipPen = (gdipGraphics ? 1 : 0);
        data5.gdipFont = (gdipGraphics ? 1 : 0);
        data4.gdipFgBrush = (gdipGraphics ? 1 : 0);
        data3.gdipBgBrush = (gdipGraphics ? 1 : 0);
        data2.gdipBrush = (gdipGraphics ? 1 : 0);
        data.gdipGraphics = (gdipGraphics ? 1 : 0);
    }
    
    public void drawArc(int n, int n2, int n3, int n4, int n5, int n6) {
        if (this.handle == 0) {
            SWT.error(44);
        }
        this.checkGC(22777);
        if (n3 < 0) {
            n += n3;
            n3 = -n3;
        }
        if (n4 < 0) {
            n2 += n4;
            n4 = -n4;
        }
        if (n3 == 0 || n4 == 0 || n6 == 0) {
            return;
        }
        final int gdipGraphics = this.data.gdipGraphics;
        if (gdipGraphics != 0) {
            Gdip.Graphics_TranslateTransform(gdipGraphics, this.data.gdipXOffset, this.data.gdipYOffset, 0);
            if (n3 == n4) {
                Gdip.Graphics_DrawArc(gdipGraphics, this.data.gdipPen, n, n2, n3, n4, -n5, -n6);
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
                Gdip.Graphics_DrawPath(gdipGraphics, this.data.gdipPen, graphicsPath_new);
                Gdip.Matrix_delete(matrix_new);
                Gdip.GraphicsPath_delete(graphicsPath_new);
            }
            Gdip.Graphics_TranslateTransform(gdipGraphics, -this.data.gdipXOffset, -this.data.gdipYOffset, 0);
            return;
        }
        if ((this.data.style & 0x8000000) != 0x0 && this.data.lineWidth != 0.0f && this.data.lineWidth % 2.0f == 0.0f) {
            --n;
        }
        if (OS.IsWinCE) {
            if (n6 < 0) {
                n5 += n6;
                n6 = -n6;
            }
            if (n6 > 360) {
                n6 = 360;
            }
            final int[] array = new int[(n6 + 1) * 2];
            final int n7 = 2 * n + n3;
            final int n8 = 2 * n2 + n4;
            int n9 = 0;
            for (int i = 0; i <= n6; ++i) {
                array[n9++] = Compatibility.cos(n5 + i, n3) + n7 >> 1;
                array[n9++] = n8 - Compatibility.sin(n5 + i, n4) >> 1;
            }
            OS.Polyline(this.handle, array, array.length / 2);
        }
        else {
            int n11;
            int n10;
            int n13;
            int n12;
            if (n6 >= 360 || n6 <= -360) {
                n10 = (n11 = n + n3);
                n12 = (n13 = n2 + n4 / 2);
            }
            else {
                final boolean b = n6 < 0;
                n6 += n5;
                if (b) {
                    final int n14 = n5;
                    n5 = n6;
                    n6 = n14;
                }
                n11 = Compatibility.cos(n5, n3) + n + n3 / 2;
                n13 = -1 * Compatibility.sin(n5, n4) + n2 + n4 / 2;
                n10 = Compatibility.cos(n6, n3) + n + n3 / 2;
                n12 = -1 * Compatibility.sin(n6, n4) + n2 + n4 / 2;
            }
            OS.Arc(this.handle, n, n2, n + n3 + 1, n2 + n4 + 1, n11, n13, n10, n12);
        }
    }
    
    public void drawFocus(final int n, final int n2, final int n3, final int n4) {
        if (this.handle == 0) {
            SWT.error(44);
        }
        if ((this.data.uiState & 0x1) != 0x0) {
            return;
        }
        this.data.focusDrawn = true;
        int n5 = this.handle;
        int saveDC = 0;
        final int gdipGraphics = this.data.gdipGraphics;
        if (gdipGraphics != 0) {
            int region_GetHRGN = 0;
            Gdip.Graphics_SetPixelOffsetMode(gdipGraphics, 3);
            final int region_new = Gdip.Region_new();
            if (region_new == 0) {
                SWT.error(2);
            }
            Gdip.Graphics_GetClip(gdipGraphics, region_new);
            if (!Gdip.Region_IsInfinite(region_new, gdipGraphics)) {
                region_GetHRGN = Gdip.Region_GetHRGN(region_new, gdipGraphics);
            }
            Gdip.Region_delete(region_new);
            Gdip.Graphics_SetPixelOffsetMode(gdipGraphics, 4);
            float[] array = null;
            final int matrix_new = Gdip.Matrix_new(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f);
            if (matrix_new == 0) {
                SWT.error(2);
            }
            Gdip.Graphics_GetTransform(gdipGraphics, matrix_new);
            if (!Gdip.Matrix_IsIdentity(matrix_new)) {
                array = new float[6];
                Gdip.Matrix_GetElements(matrix_new, array);
            }
            Gdip.Matrix_delete(matrix_new);
            n5 = Gdip.Graphics_GetHDC(gdipGraphics);
            saveDC = OS.SaveDC(n5);
            if (array != null) {
                OS.SetGraphicsMode(n5, 2);
                OS.SetWorldTransform(n5, array);
            }
            if (region_GetHRGN != 0) {
                OS.SelectClipRgn(n5, region_GetHRGN);
                OS.DeleteObject(region_GetHRGN);
            }
        }
        OS.SetBkColor(n5, 16777215);
        OS.SetTextColor(n5, 0);
        final RECT rect = new RECT();
        OS.SetRect(rect, n, n2, n + n3, n2 + n4);
        OS.DrawFocusRect(n5, rect);
        if (gdipGraphics != 0) {
            OS.RestoreDC(n5, saveDC);
            Gdip.Graphics_ReleaseHDC(gdipGraphics, n5);
        }
        else {
            final GCData data = this.data;
            data.state &= 0xFFFFFCFF;
        }
    }
    
    public void drawImage(final Image image, final int n, final int n2) {
        if (this.handle == 0) {
            SWT.error(44);
        }
        if (image == null) {
            SWT.error(4);
        }
        if (image.isDisposed()) {
            SWT.error(5);
        }
        this.drawImage(image, 0, 0, -1, -1, n, n2, -1, -1, true);
    }
    
    public void drawImage(final Image image, final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7, final int n8) {
        if (this.handle == 0) {
            SWT.error(44);
        }
        if (n3 == 0 || n4 == 0 || n7 == 0 || n8 == 0) {
            return;
        }
        if (n < 0 || n2 < 0 || n3 < 0 || n4 < 0 || n7 < 0 || n8 < 0) {
            SWT.error(5);
        }
        if (image == null) {
            SWT.error(4);
        }
        if (image.isDisposed()) {
            SWT.error(5);
        }
        this.drawImage(image, n, n2, n3, n4, n5, n6, n7, n8, false);
    }
    
    void drawImage(final Image image, final int n, final int n2, int n3, int n4, final int x, final int y, int width, int height, final boolean b) {
        if (this.data.gdipGraphics != 0) {
            final int[] gdipImage = image.createGdipImage();
            final int n5 = gdipImage[0];
            final int image_GetWidth = Gdip.Image_GetWidth(n5);
            final int image_GetHeight = Gdip.Image_GetHeight(n5);
            if (b) {
                width = (n3 = image_GetWidth);
                height = (n4 = image_GetHeight);
            }
            else {
                if (n + n3 > image_GetWidth || n2 + n4 > image_GetHeight) {
                    SWT.error(5);
                }
                final boolean b2 = n == 0 && n2 == 0 && n3 == width && width == image_GetWidth && n4 == height && height == image_GetHeight;
            }
            final Rect rect = new Rect();
            rect.X = x;
            rect.Y = y;
            rect.Width = width;
            rect.Height = height;
            final int imageAttributes_new = Gdip.ImageAttributes_new();
            Gdip.ImageAttributes_SetWrapMode(imageAttributes_new, 3);
            if (this.data.alpha != 255) {
                Gdip.ImageAttributes_SetColorMatrix(imageAttributes_new, new float[] { 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, this.data.alpha / 255.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f }, 0, 1);
            }
            int graphics_Save = 0;
            if ((this.data.style & 0x8000000) != 0x0) {
                graphics_Save = Gdip.Graphics_Save(this.data.gdipGraphics);
                Gdip.Graphics_ScaleTransform(this.data.gdipGraphics, -1.0f, 1.0f, 0);
                Gdip.Graphics_TranslateTransform(this.data.gdipGraphics, -2 * x - width, 0.0f, 0);
            }
            Gdip.Graphics_DrawImage(this.data.gdipGraphics, n5, rect, n, n2, n3, n4, 2, imageAttributes_new, 0, 0);
            if ((this.data.style & 0x8000000) != 0x0) {
                Gdip.Graphics_Restore(this.data.gdipGraphics, graphics_Save);
            }
            Gdip.ImageAttributes_delete(imageAttributes_new);
            Gdip.Bitmap_delete(n5);
            if (gdipImage[1] != 0) {
                OS.HeapFree(OS.GetProcessHeap(), 0, gdipImage[1]);
            }
            return;
        }
        switch (image.type) {
            case 0: {
                this.drawBitmap(image, n, n2, n3, n4, x, y, width, height, b);
                break;
            }
            case 1: {
                this.drawIcon(image, n, n2, n3, n4, x, y, width, height, b);
                break;
            }
        }
    }
    
    void drawIcon(final Image image, final int n, final int n2, int n3, int n4, final int n5, final int n6, int n7, int n8, final boolean b) {
        final int getDeviceCaps = OS.GetDeviceCaps(this.handle, 2);
        boolean b2 = true;
        int n9 = 3;
        int x = 0;
        int y = 0;
        if (!OS.IsWinCE && OS.WIN32_VERSION >= OS.VERSION(5, 1)) {
            if ((OS.GetLayout(this.handle) & 0x1) != 0x0) {
                n9 |= 0x10;
                final POINT point = new POINT();
                OS.GetWindowOrgEx(this.handle, point);
                x = point.x;
                y = point.y;
            }
        }
        else if (!OS.IsWinCE && OS.WIN32_VERSION >= OS.VERSION(4, 10)) {
            b2 = ((OS.GetLayout(this.handle) & 0x1) == 0x0);
        }
        if (b && getDeviceCaps != 2 && b2) {
            if (x != 0 || y != 0) {
                OS.SetWindowOrgEx(this.handle, 0, 0, null);
            }
            OS.DrawIconEx(this.handle, n5 - x, n6 - y, image.handle, 0, 0, 0, 0, n9);
            if (x != 0 || y != 0) {
                OS.SetWindowOrgEx(this.handle, x, y, null);
            }
            return;
        }
        final ICONINFO iconinfo = new ICONINFO();
        if (OS.IsWinCE) {
            Image.GetIconInfo(image, iconinfo);
        }
        else {
            OS.GetIconInfo(image.handle, iconinfo);
        }
        int n10 = iconinfo.hbmColor;
        if (n10 == 0) {
            n10 = iconinfo.hbmMask;
        }
        final BITMAP bitmap = new BITMAP();
        OS.GetObject(n10, BITMAP.sizeof, bitmap);
        final int bmWidth = bitmap.bmWidth;
        int bmHeight = bitmap.bmHeight;
        if (n10 == iconinfo.hbmMask) {
            bmHeight /= 2;
        }
        if (b) {
            n7 = (n3 = bmWidth);
            n8 = (n4 = bmHeight);
        }
        final boolean b3 = n + n3 > bmWidth || n2 + n4 > bmHeight;
        if (!b3) {
            final boolean b4 = n == 0 && n2 == 0 && n3 == n7 && n4 == n8 && n3 == bmWidth && n4 == bmHeight;
            if (!b2) {
                this.drawBitmapMask(image, iconinfo.hbmColor, iconinfo.hbmMask, n, n2, n3, n4, n5, n6, n7, n8, b4, bmWidth, bmHeight, false);
            }
            else if (b4 && getDeviceCaps != 2) {
                if (x != 0 || y != 0) {
                    OS.SetWindowOrgEx(this.handle, 0, 0, null);
                }
                OS.DrawIconEx(this.handle, n5 - x, n6 - y, image.handle, 0, 0, 0, 0, n9);
                if (x != 0 || y != 0) {
                    OS.SetWindowOrgEx(this.handle, x, y, null);
                }
            }
            else {
                final ICONINFO iconinfo2 = new ICONINFO();
                iconinfo2.fIcon = true;
                final int createCompatibleDC = OS.CreateCompatibleDC(this.handle);
                final int createCompatibleDC2 = OS.CreateCompatibleDC(this.handle);
                int n11 = n2;
                int n12 = iconinfo.hbmColor;
                if (n12 == 0) {
                    n12 = iconinfo.hbmMask;
                    n11 += bmHeight;
                }
                final int selectObject = OS.SelectObject(createCompatibleDC, n12);
                iconinfo2.hbmColor = OS.CreateCompatibleBitmap(createCompatibleDC, n7, n8);
                if (iconinfo2.hbmColor == 0) {
                    SWT.error(2);
                }
                final int selectObject2 = OS.SelectObject(createCompatibleDC2, iconinfo2.hbmColor);
                final boolean b5 = !b4 && (n3 != n7 || n4 != n8);
                if (b5) {
                    if (!OS.IsWinCE) {
                        OS.SetStretchBltMode(createCompatibleDC2, 3);
                    }
                    OS.StretchBlt(createCompatibleDC2, 0, 0, n7, n8, createCompatibleDC, n, n11, n3, n4, 13369376);
                }
                else {
                    OS.BitBlt(createCompatibleDC2, 0, 0, n7, n8, createCompatibleDC, n, n11, 13369376);
                }
                OS.SelectObject(createCompatibleDC, iconinfo.hbmMask);
                iconinfo2.hbmMask = OS.CreateBitmap(n7, n8, 1, 1, null);
                if (iconinfo2.hbmMask == 0) {
                    SWT.error(2);
                }
                OS.SelectObject(createCompatibleDC2, iconinfo2.hbmMask);
                if (b5) {
                    OS.StretchBlt(createCompatibleDC2, 0, 0, n7, n8, createCompatibleDC, n, n2, n3, n4, 13369376);
                }
                else {
                    OS.BitBlt(createCompatibleDC2, 0, 0, n7, n8, createCompatibleDC, n, n2, 13369376);
                }
                if (getDeviceCaps == 2) {
                    OS.SelectObject(createCompatibleDC, iconinfo2.hbmColor);
                    OS.SelectObject(createCompatibleDC2, iconinfo2.hbmMask);
                    this.drawBitmapTransparentByClipping(createCompatibleDC, createCompatibleDC2, 0, 0, n7, n8, n5, n6, n7, n8, true, n7, n8);
                    OS.SelectObject(createCompatibleDC, selectObject);
                    OS.SelectObject(createCompatibleDC2, selectObject2);
                }
                else {
                    OS.SelectObject(createCompatibleDC, selectObject);
                    OS.SelectObject(createCompatibleDC2, selectObject2);
                    final int createIconIndirect = OS.CreateIconIndirect(iconinfo2);
                    if (createIconIndirect == 0) {
                        SWT.error(2);
                    }
                    if (x != 0 || y != 0) {
                        OS.SetWindowOrgEx(this.handle, 0, 0, null);
                    }
                    OS.DrawIconEx(this.handle, n5 - x, n6 - y, createIconIndirect, n7, n8, 0, 0, n9);
                    if (x != 0 || y != 0) {
                        OS.SetWindowOrgEx(this.handle, x, y, null);
                    }
                    OS.DestroyIcon(createIconIndirect);
                }
                OS.DeleteObject(iconinfo2.hbmMask);
                OS.DeleteObject(iconinfo2.hbmColor);
                OS.DeleteDC(createCompatibleDC2);
                OS.DeleteDC(createCompatibleDC);
            }
        }
        OS.DeleteObject(iconinfo.hbmMask);
        if (iconinfo.hbmColor != 0) {
            OS.DeleteObject(iconinfo.hbmColor);
        }
        if (b3) {
            SWT.error(5);
        }
    }
    
    void drawBitmap(final Image image, final int n, final int n2, int n3, int n4, final int n5, final int n6, int n7, int n8, boolean b) {
        final BITMAP bitmap = new BITMAP();
        OS.GetObject(image.handle, BITMAP.sizeof, bitmap);
        final int bmWidth = bitmap.bmWidth;
        final int bmHeight = bitmap.bmHeight;
        if (b) {
            n7 = (n3 = bmWidth);
            n8 = (n4 = bmHeight);
        }
        else {
            if (n + n3 > bmWidth || n2 + n4 > bmHeight) {
                SWT.error(5);
            }
            b = (n == 0 && n2 == 0 && n3 == n7 && n7 == bmWidth && n4 == n8 && n8 == bmHeight);
        }
        boolean b2 = false;
        final GC memGC = image.memGC;
        if (memGC != null && !memGC.isDisposed()) {
            memGC.flush();
            b2 = true;
            final GCData data = memGC.data;
            if (data.hNullBitmap != 0) {
                OS.SelectObject(memGC.handle, data.hNullBitmap);
                data.hNullBitmap = 0;
            }
        }
        if (image.alpha != -1 || image.alphaData != null) {
            this.drawBitmapAlpha(image, n, n2, n3, n4, n5, n6, n7, n8, b, bitmap, bmWidth, bmHeight);
        }
        else if (image.transparentPixel != -1) {
            this.drawBitmapTransparent(image, n, n2, n3, n4, n5, n6, n7, n8, b, bitmap, bmWidth, bmHeight);
        }
        else {
            this.drawBitmap(image, n, n2, n3, n4, n5, n6, n7, n8, b, bitmap, bmWidth, bmHeight);
        }
        if (b2) {
            memGC.data.hNullBitmap = OS.SelectObject(memGC.handle, image.handle);
        }
    }
    
    void drawBitmapAlpha(final Image image, int n, int n2, int max, int max2, int x, int y, int width, int height, final boolean b, final BITMAP bitmap, final int n3, final int n4) {
        if (image.alpha == 0) {
            return;
        }
        if (image.alpha == 255) {
            this.drawBitmap(image, n, n2, max, max2, x, y, width, height, b, bitmap, n3, n4);
            return;
        }
        boolean b2 = OS.IsWinNT && OS.WIN32_VERSION >= OS.VERSION(4, 10);
        final boolean b3 = OS.GetDeviceCaps(this.handle, 2) == 2;
        if (b2 && b3) {
            final int getDeviceCaps = OS.GetDeviceCaps(this.handle, 120);
            if (getDeviceCaps != 0) {
                if (image.alpha != -1) {
                    b2 = ((getDeviceCaps & 0x1) != 0x0);
                }
                else {
                    b2 = ((getDeviceCaps & 0x2) != 0x0);
                }
            }
        }
        if (b2) {
            final BLENDFUNCTION blendfunction = new BLENDFUNCTION();
            blendfunction.BlendOp = 0;
            final int createCompatibleDC = OS.CreateCompatibleDC(this.handle);
            final int selectObject = OS.SelectObject(createCompatibleDC, image.handle);
            if (image.alpha != -1) {
                blendfunction.SourceConstantAlpha = (byte)image.alpha;
                OS.AlphaBlend(this.handle, x, y, width, height, createCompatibleDC, n, n2, max, max2, blendfunction);
            }
            else {
                final int dib = Image.createDIB(max, max2, 32);
                if (dib == 0) {
                    SWT.error(2);
                }
                final int createCompatibleDC2 = OS.CreateCompatibleDC(this.handle);
                final int selectObject2 = OS.SelectObject(createCompatibleDC2, dib);
                final BITMAP bitmap2 = new BITMAP();
                OS.GetObject(dib, BITMAP.sizeof, bitmap2);
                OS.BitBlt(createCompatibleDC2, 0, 0, max, max2, createCompatibleDC, n, n2, 13369376);
                final byte[] array = new byte[bitmap2.bmWidthBytes * bitmap2.bmHeight];
                OS.MoveMemory(array, bitmap2.bmBits, array.length);
                final int n5 = n3 - max;
                int n6 = n2 * n3 + n;
                int n7 = 0;
                final byte[] alphaData = image.alphaData;
                for (int i = 0; i < max2; ++i) {
                    for (int j = 0; j < max; ++j) {
                        final int n8 = alphaData[n6++] & 0xFF;
                        final int n9 = (array[n7 + 0] & 0xFF) * n8 + 128;
                        final int n10 = n9 + (n9 >> 8) >> 8;
                        final int n11 = (array[n7 + 1] & 0xFF) * n8 + 128;
                        final int n12 = n11 + (n11 >> 8) >> 8;
                        final int n13 = (array[n7 + 2] & 0xFF) * n8 + 128;
                        final int n14 = n13 + (n13 >> 8) >> 8;
                        array[n7 + 0] = (byte)n10;
                        array[n7 + 1] = (byte)n12;
                        array[n7 + 2] = (byte)n14;
                        array[n7 + 3] = (byte)n8;
                        n7 += 4;
                    }
                    n6 += n5;
                }
                OS.MoveMemory(bitmap2.bmBits, array, array.length);
                blendfunction.SourceConstantAlpha = -1;
                blendfunction.AlphaFormat = 1;
                OS.AlphaBlend(this.handle, x, y, width, height, createCompatibleDC2, 0, 0, max, max2, blendfunction);
                OS.SelectObject(createCompatibleDC2, selectObject2);
                OS.DeleteDC(createCompatibleDC2);
                OS.DeleteObject(dib);
            }
            OS.SelectObject(createCompatibleDC, selectObject);
            OS.DeleteDC(createCompatibleDC);
            return;
        }
        final Rectangle intersection = this.getClipping().intersection(new Rectangle(x, y, width, height));
        if (intersection.isEmpty()) {
            return;
        }
        final int n15 = n + (intersection.x - x) * max / width;
        final int n16 = n + (intersection.x + intersection.width - x) * max / width;
        final int n17 = n2 + (intersection.y - y) * max2 / height;
        final int n18 = n2 + (intersection.y + intersection.height - y) * max2 / height;
        x = intersection.x;
        y = intersection.y;
        width = intersection.width;
        height = intersection.height;
        n = n15;
        n2 = n17;
        max = Math.max(1, n16 - n15);
        max2 = Math.max(1, n18 - n17);
        final int createCompatibleDC3 = OS.CreateCompatibleDC(this.handle);
        final int selectObject3 = OS.SelectObject(createCompatibleDC3, image.handle);
        final int createCompatibleDC4 = OS.CreateCompatibleDC(this.handle);
        final int dib2 = Image.createDIB(Math.max(max, width), Math.max(max2, height), 32);
        if (dib2 == 0) {
            SWT.error(2);
        }
        final int selectObject4 = OS.SelectObject(createCompatibleDC4, dib2);
        final BITMAP bitmap3 = new BITMAP();
        OS.GetObject(dib2, BITMAP.sizeof, bitmap3);
        final int n19 = bitmap3.bmWidthBytes * bitmap3.bmHeight;
        OS.BitBlt(createCompatibleDC4, 0, 0, width, height, this.handle, x, y, 13369376);
        final byte[] array2 = new byte[n19];
        OS.MoveMemory(array2, bitmap3.bmBits, n19);
        OS.BitBlt(createCompatibleDC4, 0, 0, max, max2, createCompatibleDC3, n, n2, 13369376);
        final byte[] array3 = new byte[n19];
        OS.MoveMemory(array3, bitmap3.bmBits, n19);
        int alpha = image.alpha;
        final boolean b4 = image.alpha == -1;
        if (b4) {
            final int n20 = n3 - max;
            final int n21 = bitmap3.bmWidthBytes - max * 4;
            int n22 = n2 * n3 + n;
            int n23 = 3;
            final byte[] alphaData2 = image.alphaData;
            for (int k = 0; k < max2; ++k) {
                for (int l = 0; l < max; ++l) {
                    array3[n23] = alphaData2[n22++];
                    n23 += 4;
                }
                n22 += n20;
                n23 += n21;
            }
        }
        OS.MoveMemory(bitmap3.bmBits, array3, n19);
        if ((OS.IsWinCE && (width > max || height > max2)) || (!OS.IsWinNT && !OS.IsWinCE) || b3) {
            final int createCompatibleDC5 = OS.CreateCompatibleDC(this.handle);
            final int dib3 = Image.createDIB(width, height, 32);
            if (dib3 == 0) {
                SWT.error(2);
            }
            final int selectObject5 = OS.SelectObject(createCompatibleDC5, dib3);
            if (!b && (max != width || max2 != height)) {
                if (!OS.IsWinCE) {
                    OS.SetStretchBltMode(createCompatibleDC4, 3);
                }
                OS.StretchBlt(createCompatibleDC5, 0, 0, width, height, createCompatibleDC4, 0, 0, max, max2, 13369376);
            }
            else {
                OS.BitBlt(createCompatibleDC5, 0, 0, width, height, createCompatibleDC4, 0, 0, 13369376);
            }
            OS.BitBlt(createCompatibleDC4, 0, 0, width, height, createCompatibleDC5, 0, 0, 13369376);
            OS.SelectObject(createCompatibleDC5, selectObject5);
            OS.DeleteObject(dib3);
            OS.DeleteDC(createCompatibleDC5);
        }
        else if (!b && (max != width || max2 != height)) {
            if (!OS.IsWinCE) {
                OS.SetStretchBltMode(createCompatibleDC4, 3);
            }
            OS.StretchBlt(createCompatibleDC4, 0, 0, width, height, createCompatibleDC4, 0, 0, max, max2, 13369376);
        }
        else {
            OS.BitBlt(createCompatibleDC4, 0, 0, width, height, createCompatibleDC4, 0, 0, 13369376);
        }
        OS.MoveMemory(array3, bitmap3.bmBits, n19);
        final int n24 = bitmap3.bmWidthBytes - width * 4;
        int n25 = 0;
        for (int n26 = 0; n26 < height; ++n26) {
            for (int n27 = 0; n27 < width; ++n27) {
                if (b4) {
                    alpha = (array3[n25 + 3] & 0xFF);
                }
                final byte[] array4 = array2;
                final int n28 = n25;
                array4[n28] += (byte)(((array3[n25] & 0xFF) - (array2[n25] & 0xFF)) * alpha / 255);
                final byte[] array5 = array2;
                final int n29 = n25 + 1;
                array5[n29] += (byte)(((array3[n25 + 1] & 0xFF) - (array2[n25 + 1] & 0xFF)) * alpha / 255);
                final byte[] array6 = array2;
                final int n30 = n25 + 2;
                array6[n30] += (byte)(((array3[n25 + 2] & 0xFF) - (array2[n25 + 2] & 0xFF)) * alpha / 255);
                n25 += 4;
            }
            n25 += n24;
        }
        OS.MoveMemory(bitmap3.bmBits, array2, n19);
        OS.BitBlt(this.handle, x, y, width, height, createCompatibleDC4, 0, 0, 13369376);
        OS.SelectObject(createCompatibleDC4, selectObject4);
        OS.DeleteDC(createCompatibleDC4);
        OS.DeleteObject(dib2);
        OS.SelectObject(createCompatibleDC3, selectObject3);
        OS.DeleteDC(createCompatibleDC3);
    }
    
    void drawBitmapTransparentByClipping(final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7, final int n8, final int n9, final int n10, final boolean b, final int n11, final int n12) {
        int createRectRgn = OS.CreateRectRgn(0, 0, 0, 0);
        for (int i = 0; i < n12; ++i) {
            for (int j = 0; j < n11; ++j) {
                if (OS.GetPixel(n2, j, i) == 0) {
                    final int createRectRgn2 = OS.CreateRectRgn(j, i, j + 1, i + 1);
                    OS.CombineRgn(createRectRgn, createRectRgn, createRectRgn2, 2);
                    OS.DeleteObject(createRectRgn2);
                }
            }
        }
        if (n9 != n5 || n10 != n6) {
            final int getRegionData = OS.GetRegionData(createRectRgn, 0, null);
            final int[] array = new int[getRegionData / 4];
            OS.GetRegionData(createRectRgn, getRegionData, array);
            final int extCreateRegion = OS.ExtCreateRegion(new float[] { n9 / n5, 0.0f, 0.0f, n10 / n6, 0.0f, 0.0f }, getRegionData, array);
            OS.DeleteObject(createRectRgn);
            createRectRgn = extCreateRegion;
        }
        OS.OffsetRgn(createRectRgn, n7, n8);
        final int createRectRgn3 = OS.CreateRectRgn(0, 0, 0, 0);
        final int getClipRgn = OS.GetClipRgn(this.handle, createRectRgn3);
        if (getClipRgn == 1) {
            OS.CombineRgn(createRectRgn, createRectRgn, createRectRgn3, 1);
        }
        OS.SelectClipRgn(this.handle, createRectRgn);
        int n13;
        if (!OS.IsWinCE) {
            n13 = OS.GetROP2(this.handle);
        }
        else {
            n13 = OS.SetROP2(this.handle, 13);
            OS.SetROP2(this.handle, n13);
        }
        final int n14 = (n13 == 7) ? 6684742 : 13369376;
        if (!b && (n5 != n9 || n6 != n10)) {
            int setStretchBltMode = 0;
            if (!OS.IsWinCE) {
                setStretchBltMode = OS.SetStretchBltMode(this.handle, 3);
            }
            OS.StretchBlt(this.handle, n7, n8, n9, n10, n, n3, n4, n5, n6, n14);
            if (!OS.IsWinCE) {
                OS.SetStretchBltMode(this.handle, setStretchBltMode);
            }
        }
        else {
            OS.BitBlt(this.handle, n7, n8, n9, n10, n, n3, n4, n14);
        }
        OS.SelectClipRgn(this.handle, (getClipRgn == 1) ? createRectRgn3 : false);
        OS.DeleteObject(createRectRgn3);
        OS.DeleteObject(createRectRgn);
    }
    
    void drawBitmapMask(final Image image, int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7, final int n8, final int n9, final int n10, final boolean b, final int n11, final int n12, final boolean b2) {
        int n13 = n4;
        if (n == 0) {
            n = n2;
            n13 += n12;
        }
        final int createCompatibleDC = OS.CreateCompatibleDC(this.handle);
        final int selectObject = OS.SelectObject(createCompatibleDC, n);
        int handle = this.handle;
        int n14 = n7;
        int n15 = n8;
        int createCompatibleDC2 = 0;
        int createCompatibleBitmap = 0;
        int selectObject2 = 0;
        int setBkColor = 0;
        int setTextColor = 0;
        if (b2) {
            createCompatibleDC2 = OS.CreateCompatibleDC(this.handle);
            createCompatibleBitmap = OS.CreateCompatibleBitmap(this.handle, n9, n10);
            selectObject2 = OS.SelectObject(createCompatibleDC2, createCompatibleBitmap);
            OS.BitBlt(createCompatibleDC2, 0, 0, n9, n10, this.handle, n7, n8, 13369376);
            handle = createCompatibleDC2;
            n15 = (n14 = 0);
        }
        else {
            setBkColor = OS.SetBkColor(this.handle, 16777215);
            setTextColor = OS.SetTextColor(this.handle, 0);
        }
        if (!b && (n5 != n9 || n6 != n10)) {
            int setStretchBltMode = 0;
            if (!OS.IsWinCE) {
                setStretchBltMode = OS.SetStretchBltMode(this.handle, 3);
            }
            OS.StretchBlt(handle, n14, n15, n9, n10, createCompatibleDC, n3, n13, n5, n6, 6684742);
            OS.SelectObject(createCompatibleDC, n2);
            OS.StretchBlt(handle, n14, n15, n9, n10, createCompatibleDC, n3, n4, n5, n6, 8913094);
            OS.SelectObject(createCompatibleDC, n);
            OS.StretchBlt(handle, n14, n15, n9, n10, createCompatibleDC, n3, n13, n5, n6, 6684742);
            if (!OS.IsWinCE) {
                OS.SetStretchBltMode(this.handle, setStretchBltMode);
            }
        }
        else {
            OS.BitBlt(handle, n14, n15, n9, n10, createCompatibleDC, n3, n13, 6684742);
            OS.SetTextColor(handle, 0);
            OS.SelectObject(createCompatibleDC, n2);
            OS.BitBlt(handle, n14, n15, n9, n10, createCompatibleDC, n3, n4, 8913094);
            OS.SelectObject(createCompatibleDC, n);
            OS.BitBlt(handle, n14, n15, n9, n10, createCompatibleDC, n3, n13, 6684742);
        }
        if (b2) {
            OS.BitBlt(this.handle, n7, n8, n9, n10, createCompatibleDC2, 0, 0, 13369376);
            OS.SelectObject(createCompatibleDC2, selectObject2);
            OS.DeleteDC(createCompatibleDC2);
            OS.DeleteObject(createCompatibleBitmap);
        }
        else {
            OS.SetBkColor(this.handle, setBkColor);
            OS.SetTextColor(this.handle, setTextColor);
        }
        OS.SelectObject(createCompatibleDC, selectObject);
        OS.DeleteDC(createCompatibleDC);
    }
    
    void drawBitmapTransparent(final Image image, final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7, final int n8, final boolean b, final BITMAP bitmap, final int n9, final int n10) {
        final boolean b2 = bitmap.bmBits != 0;
        final int handle = image.handle;
        final int createCompatibleDC = OS.CreateCompatibleDC(this.handle);
        final int selectObject = OS.SelectObject(createCompatibleDC, handle);
        byte[] array = null;
        int transparentColor = image.transparentColor;
        if (transparentColor == -1) {
            int n11 = 0;
            int n12 = 0;
            int n13 = 0;
            boolean b3 = false;
            if (bitmap.bmBitsPixel <= 8) {
                if (b2) {
                    if (OS.IsWinCE) {
                        final byte[] array2 = { 0 };
                        OS.MoveMemory(array2, bitmap.bmBits, 1);
                        final byte b4 = array2[0];
                        array2[0] = (byte)(image.transparentPixel << 8 - bitmap.bmBitsPixel | (array2[0] & ~(255 << 8 - bitmap.bmBitsPixel & 0xFF)));
                        OS.MoveMemory(bitmap.bmBits, array2, 1);
                        final int getPixel = OS.GetPixel(createCompatibleDC, 0, 0);
                        array2[0] = b4;
                        OS.MoveMemory(bitmap.bmBits, array2, 1);
                        n11 = (getPixel & 0xFF0000) >> 16;
                        n12 = (getPixel & 0xFF00) >> 8;
                        n13 = (getPixel & 0xFF);
                    }
                    else {
                        final int n14 = 1 << bitmap.bmBitsPixel;
                        final byte[] array3 = new byte[n14 * 4];
                        OS.GetDIBColorTable(createCompatibleDC, 0, n14, array3);
                        final int n15 = image.transparentPixel * 4;
                        for (int i = 0; i < array3.length; i += 4) {
                            if (i != n15 && array3[n15] == array3[i] && array3[n15 + 1] == array3[i + 1] && array3[n15 + 2] == array3[i + 2]) {
                                b3 = true;
                                break;
                            }
                        }
                        if (b3) {
                            final byte[] array4 = new byte[array3.length];
                            n12 = (n13 = (n11 = 255));
                            array4[n15] = (byte)n11;
                            array4[n15 + 1] = (byte)n12;
                            array4[n15 + 2] = (byte)n13;
                            OS.SetDIBColorTable(createCompatibleDC, 0, n14, array4);
                            array = array3;
                        }
                        else {
                            n11 = (array3[n15] & 0xFF);
                            n12 = (array3[n15 + 1] & 0xFF);
                            n13 = (array3[n15 + 2] & 0xFF);
                        }
                    }
                }
                else {
                    final int n16 = 1 << bitmap.bmBitsPixel;
                    final BITMAPINFOHEADER bitmapinfoheader = new BITMAPINFOHEADER();
                    bitmapinfoheader.biSize = BITMAPINFOHEADER.sizeof;
                    bitmapinfoheader.biPlanes = bitmap.bmPlanes;
                    bitmapinfoheader.biBitCount = bitmap.bmBitsPixel;
                    final byte[] array5 = new byte[BITMAPINFOHEADER.sizeof + n16 * 4];
                    OS.MoveMemory(array5, bitmapinfoheader, BITMAPINFOHEADER.sizeof);
                    if (OS.IsWinCE) {
                        SWT.error(20);
                    }
                    OS.GetDIBits(createCompatibleDC, image.handle, 0, 0, 0, array5, 0);
                    final int n17 = BITMAPINFOHEADER.sizeof + 4 * image.transparentPixel;
                    n13 = (array5[n17 + 2] & 0xFF);
                    n12 = (array5[n17 + 1] & 0xFF);
                    n11 = (array5[n17] & 0xFF);
                }
            }
            else {
                final int transparentPixel = image.transparentPixel;
                switch (bitmap.bmBitsPixel) {
                    case 16: {
                        n11 = (transparentPixel & 0x1F) << 3;
                        n12 = (transparentPixel & 0x3E0) >> 2;
                        n13 = (transparentPixel & 0x7C00) >> 7;
                        break;
                    }
                    case 24: {
                        n11 = (transparentPixel & 0xFF0000) >> 16;
                        n12 = (transparentPixel & 0xFF00) >> 8;
                        n13 = (transparentPixel & 0xFF);
                        break;
                    }
                    case 32: {
                        n11 = (transparentPixel & 0xFF000000) >>> 24;
                        n12 = (transparentPixel & 0xFF0000) >> 16;
                        n13 = (transparentPixel & 0xFF00) >> 8;
                        break;
                    }
                }
            }
            transparentColor = (n11 << 16 | n12 << 8 | n13);
            if (!b3) {
                image.transparentColor = transparentColor;
            }
        }
        if (OS.IsWinCE) {
            OS.TransparentImage(this.handle, n5, n6, n7, n8, createCompatibleDC, n, n2, n3, n4, transparentColor);
        }
        else if (array == null && OS.IsWinNT && OS.WIN32_VERSION >= OS.VERSION(4, 10)) {
            final int setStretchBltMode = OS.SetStretchBltMode(this.handle, 3);
            OS.TransparentBlt(this.handle, n5, n6, n7, n8, createCompatibleDC, n, n2, n3, n4, transparentColor);
            OS.SetStretchBltMode(this.handle, setStretchBltMode);
        }
        else {
            final int createCompatibleDC2 = OS.CreateCompatibleDC(this.handle);
            final int createBitmap = OS.CreateBitmap(n9, n10, 1, 1, null);
            final int selectObject2 = OS.SelectObject(createCompatibleDC2, createBitmap);
            OS.SetBkColor(createCompatibleDC, transparentColor);
            OS.BitBlt(createCompatibleDC2, 0, 0, n9, n10, createCompatibleDC, 0, 0, 13369376);
            if (array != null) {
                OS.SetDIBColorTable(createCompatibleDC, 0, 1 << bitmap.bmBitsPixel, array);
            }
            if (OS.GetDeviceCaps(this.handle, 2) == 2) {
                this.drawBitmapTransparentByClipping(createCompatibleDC, createCompatibleDC2, n, n2, n3, n4, n5, n6, n7, n8, b, n9, n10);
            }
            else {
                final int createCompatibleDC3 = OS.CreateCompatibleDC(this.handle);
                final int createCompatibleBitmap = OS.CreateCompatibleBitmap(this.handle, n7, n8);
                final int selectObject3 = OS.SelectObject(createCompatibleDC3, createCompatibleBitmap);
                OS.BitBlt(createCompatibleDC3, 0, 0, n7, n8, this.handle, n5, n6, 13369376);
                if (!b && (n3 != n7 || n4 != n8)) {
                    if (!OS.IsWinCE) {
                        OS.SetStretchBltMode(createCompatibleDC3, 3);
                    }
                    OS.StretchBlt(createCompatibleDC3, 0, 0, n7, n8, createCompatibleDC, n, n2, n3, n4, 6684742);
                    OS.StretchBlt(createCompatibleDC3, 0, 0, n7, n8, createCompatibleDC2, n, n2, n3, n4, 8913094);
                    OS.StretchBlt(createCompatibleDC3, 0, 0, n7, n8, createCompatibleDC, n, n2, n3, n4, 6684742);
                }
                else {
                    OS.BitBlt(createCompatibleDC3, 0, 0, n7, n8, createCompatibleDC, n, n2, 6684742);
                    OS.BitBlt(createCompatibleDC3, 0, 0, n7, n8, createCompatibleDC2, n, n2, 8913094);
                    OS.BitBlt(createCompatibleDC3, 0, 0, n7, n8, createCompatibleDC, n, n2, 6684742);
                }
                OS.BitBlt(this.handle, n5, n6, n7, n8, createCompatibleDC3, 0, 0, 13369376);
                OS.SelectObject(createCompatibleDC3, selectObject3);
                OS.DeleteDC(createCompatibleDC3);
                OS.DeleteObject(createCompatibleBitmap);
            }
            OS.SelectObject(createCompatibleDC2, selectObject2);
            OS.DeleteDC(createCompatibleDC2);
            OS.DeleteObject(createBitmap);
        }
        OS.SelectObject(createCompatibleDC, selectObject);
        if (handle != image.handle) {
            OS.DeleteObject(handle);
        }
        OS.DeleteDC(createCompatibleDC);
    }
    
    void drawBitmap(final Image image, final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7, final int n8, final boolean b, final BITMAP bitmap, final int n9, final int n10) {
        final int createCompatibleDC = OS.CreateCompatibleDC(this.handle);
        final int selectObject = OS.SelectObject(createCompatibleDC, image.handle);
        int n11;
        if (!OS.IsWinCE) {
            n11 = OS.GetROP2(this.handle);
        }
        else {
            n11 = OS.SetROP2(this.handle, 13);
            OS.SetROP2(this.handle, n11);
        }
        final int n12 = (n11 == 7) ? 6684742 : 13369376;
        if (!b && (n3 != n7 || n4 != n8)) {
            int setStretchBltMode = 0;
            if (!OS.IsWinCE) {
                setStretchBltMode = OS.SetStretchBltMode(this.handle, 3);
            }
            OS.StretchBlt(this.handle, n5, n6, n7, n8, createCompatibleDC, n, n2, n3, n4, n12);
            if (!OS.IsWinCE) {
                OS.SetStretchBltMode(this.handle, setStretchBltMode);
            }
        }
        else {
            OS.BitBlt(this.handle, n5, n6, n7, n8, createCompatibleDC, n, n2, n12);
        }
        OS.SelectObject(createCompatibleDC, selectObject);
        OS.DeleteDC(createCompatibleDC);
    }
    
    public void drawLine(int n, final int n2, int n3, final int n4) {
        if (this.handle == 0) {
            SWT.error(44);
        }
        this.checkGC(22777);
        final int gdipGraphics = this.data.gdipGraphics;
        if (gdipGraphics != 0) {
            Gdip.Graphics_TranslateTransform(gdipGraphics, this.data.gdipXOffset, this.data.gdipYOffset, 0);
            Gdip.Graphics_DrawLine(gdipGraphics, this.data.gdipPen, n, n2, n3, n4);
            Gdip.Graphics_TranslateTransform(gdipGraphics, -this.data.gdipXOffset, -this.data.gdipYOffset, 0);
            return;
        }
        if ((this.data.style & 0x8000000) != 0x0 && this.data.lineWidth != 0.0f && this.data.lineWidth % 2.0f == 0.0f) {
            --n;
            --n3;
        }
        if (OS.IsWinCE) {
            final int[] array = { n, n2, n3, n4 };
            OS.Polyline(this.handle, array, array.length / 2);
        }
        else {
            OS.MoveToEx(this.handle, n, n2, 0);
            OS.LineTo(this.handle, n3, n4);
        }
        if (this.data.lineWidth <= 1.0f) {
            OS.SetPixel(this.handle, n3, n4, this.data.foreground);
        }
    }
    
    public void drawOval(int n, final int n2, final int n3, final int n4) {
        if (this.handle == 0) {
            SWT.error(44);
        }
        this.checkGC(22777);
        final int gdipGraphics = this.data.gdipGraphics;
        if (gdipGraphics != 0) {
            Gdip.Graphics_TranslateTransform(gdipGraphics, this.data.gdipXOffset, this.data.gdipYOffset, 0);
            Gdip.Graphics_DrawEllipse(gdipGraphics, this.data.gdipPen, n, n2, n3, n4);
            Gdip.Graphics_TranslateTransform(gdipGraphics, -this.data.gdipXOffset, -this.data.gdipYOffset, 0);
            return;
        }
        if ((this.data.style & 0x8000000) != 0x0 && this.data.lineWidth != 0.0f && this.data.lineWidth % 2.0f == 0.0f) {
            --n;
        }
        OS.Ellipse(this.handle, n, n2, n + n3 + 1, n2 + n4 + 1);
    }
    
    public void drawPath(final Path path) {
        if (this.handle == 0) {
            SWT.error(44);
        }
        if (path == null) {
            SWT.error(4);
        }
        if (path.handle == 0) {
            SWT.error(5);
        }
        this.initGdip();
        this.checkGC(22777);
        final int gdipGraphics = this.data.gdipGraphics;
        Gdip.Graphics_TranslateTransform(gdipGraphics, this.data.gdipXOffset, this.data.gdipYOffset, 0);
        Gdip.Graphics_DrawPath(gdipGraphics, this.data.gdipPen, path.handle);
        Gdip.Graphics_TranslateTransform(gdipGraphics, -this.data.gdipXOffset, -this.data.gdipYOffset, 0);
    }
    
    public void drawPoint(final int n, final int n2) {
        if (this.handle == 0) {
            SWT.error(44);
        }
        if (this.data.gdipGraphics != 0) {
            this.checkGC(22777);
            Gdip.Graphics_FillRectangle(this.data.gdipGraphics, this.getFgBrush(), n, n2, 1, 1);
            return;
        }
        OS.SetPixel(this.handle, n, n2, this.data.foreground);
    }
    
    public void drawPolygon(final int[] array) {
        if (this.handle == 0) {
            SWT.error(44);
        }
        if (array == null) {
            SWT.error(4);
        }
        this.checkGC(22777);
        final int gdipGraphics = this.data.gdipGraphics;
        if (gdipGraphics != 0) {
            Gdip.Graphics_TranslateTransform(gdipGraphics, this.data.gdipXOffset, this.data.gdipYOffset, 0);
            Gdip.Graphics_DrawPolygon(gdipGraphics, this.data.gdipPen, array, array.length / 2);
            Gdip.Graphics_TranslateTransform(gdipGraphics, -this.data.gdipXOffset, -this.data.gdipYOffset, 0);
            return;
        }
        if ((this.data.style & 0x8000000) != 0x0 && this.data.lineWidth != 0.0f && this.data.lineWidth % 2.0f == 0.0f) {
            for (int i = 0; i < array.length; i += 2) {
                final int n = i;
                --array[n];
            }
        }
        OS.Polygon(this.handle, array, array.length / 2);
        if ((this.data.style & 0x8000000) != 0x0 && this.data.lineWidth != 0.0f && this.data.lineWidth % 2.0f == 0.0f) {
            for (int j = 0; j < array.length; j += 2) {
                final int n2 = j;
                ++array[n2];
            }
        }
    }
    
    public void drawPolyline(final int[] array) {
        if (this.handle == 0) {
            SWT.error(44);
        }
        if (array == null) {
            SWT.error(4);
        }
        this.checkGC(22777);
        final int gdipGraphics = this.data.gdipGraphics;
        if (gdipGraphics != 0) {
            Gdip.Graphics_TranslateTransform(gdipGraphics, this.data.gdipXOffset, this.data.gdipYOffset, 0);
            Gdip.Graphics_DrawLines(gdipGraphics, this.data.gdipPen, array, array.length / 2);
            Gdip.Graphics_TranslateTransform(gdipGraphics, -this.data.gdipXOffset, -this.data.gdipYOffset, 0);
            return;
        }
        if ((this.data.style & 0x8000000) != 0x0 && this.data.lineWidth != 0.0f && this.data.lineWidth % 2.0f == 0.0f) {
            for (int i = 0; i < array.length; i += 2) {
                final int n = i;
                --array[n];
            }
        }
        OS.Polyline(this.handle, array, array.length / 2);
        final int length = array.length;
        if (length >= 2 && this.data.lineWidth <= 1.0f) {
            OS.SetPixel(this.handle, array[length - 2], array[length - 1], this.data.foreground);
        }
        if ((this.data.style & 0x8000000) != 0x0 && this.data.lineWidth != 0.0f && this.data.lineWidth % 2.0f == 0.0f) {
            for (int j = 0; j < array.length; j += 2) {
                final int n2 = j;
                ++array[n2];
            }
        }
    }
    
    public void drawRectangle(int n, int n2, int n3, int n4) {
        if (this.handle == 0) {
            SWT.error(44);
        }
        this.checkGC(22777);
        final int gdipGraphics = this.data.gdipGraphics;
        if (gdipGraphics != 0) {
            if (n3 < 0) {
                n += n3;
                n3 = -n3;
            }
            if (n4 < 0) {
                n2 += n4;
                n4 = -n4;
            }
            Gdip.Graphics_TranslateTransform(gdipGraphics, this.data.gdipXOffset, this.data.gdipYOffset, 0);
            Gdip.Graphics_DrawRectangle(gdipGraphics, this.data.gdipPen, n, n2, n3, n4);
            Gdip.Graphics_TranslateTransform(gdipGraphics, -this.data.gdipXOffset, -this.data.gdipYOffset, 0);
            return;
        }
        if ((this.data.style & 0x8000000) != 0x0) {
            if (this.data.lineWidth > 1.0f) {
                if (this.data.lineWidth % 2.0f == 1.0f) {
                    ++n;
                }
            }
            else if (this.data.hPen != 0 && OS.GetObject(this.data.hPen, 0, 0) != LOGPEN.sizeof) {
                ++n;
            }
        }
        OS.Rectangle(this.handle, n, n2, n + n3 + 1, n2 + n4 + 1);
    }
    
    public void drawRectangle(final Rectangle rectangle) {
        if (rectangle == null) {
            SWT.error(4);
        }
        this.drawRectangle(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
    }
    
    public void drawRoundRectangle(int n, int n2, int n3, int n4, int n5, int n6) {
        if (this.handle == 0) {
            SWT.error(44);
        }
        this.checkGC(22777);
        if (this.data.gdipGraphics != 0) {
            this.drawRoundRectangleGdip(this.data.gdipGraphics, this.data.gdipPen, n, n2, n3, n4, n5, n6);
            return;
        }
        if ((this.data.style & 0x8000000) != 0x0 && this.data.lineWidth != 0.0f && this.data.lineWidth % 2.0f == 0.0f) {
            --n;
        }
        if (OS.IsWinCE) {
            if (n3 == 0 || n4 == 0) {
                return;
            }
            if (n5 == 0 || n6 == 0) {
                this.drawRectangle(n, n2, n3, n4);
                return;
            }
            if (n3 < 0) {
                n += n3;
                n3 = -n3;
            }
            if (n4 < 0) {
                n2 += n4;
                n4 = -n4;
            }
            if (n5 < 0) {
                n5 = -n5;
            }
            if (n6 < 0) {
                n6 = -n6;
            }
            if (n5 > n3) {
                n5 = n3;
            }
            if (n6 > n4) {
                n6 = n4;
            }
            if (n5 < n3) {
                this.drawLine(n + n5 / 2, n2, n + n3 - n5 / 2, n2);
                this.drawLine(n + n5 / 2, n2 + n4, n + n3 - n5 / 2, n2 + n4);
            }
            if (n6 < n4) {
                this.drawLine(n, n2 + n6 / 2, n, n2 + n4 - n6 / 2);
                this.drawLine(n + n3, n2 + n6 / 2, n + n3, n2 + n4 - n6 / 2);
            }
            if (n5 != 0 && n6 != 0) {
                this.drawArc(n, n2, n5, n6, 90, 90);
                this.drawArc(n + n3 - n5, n2, n5, n6, 0, 90);
                this.drawArc(n + n3 - n5, n2 + n4 - n6, n5, n6, 0, -90);
                this.drawArc(n, n2 + n4 - n6, n5, n6, 180, 90);
            }
        }
        else {
            OS.RoundRect(this.handle, n, n2, n + n3 + 1, n2 + n4 + 1, n5, n6);
        }
    }
    
    void drawRoundRectangleGdip(final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7, final int n8) {
        int n9 = n3;
        int n10 = n4;
        int n11 = n5;
        int n12 = n6;
        int n13 = n7;
        int n14 = n8;
        if (n11 < 0) {
            n11 = 0 - n11;
            n9 -= n11;
        }
        if (n12 < 0) {
            n12 = 0 - n12;
            n10 -= n12;
        }
        if (n13 < 0) {
            n13 = 0 - n13;
        }
        if (n14 < 0) {
            n14 = 0 - n14;
        }
        Gdip.Graphics_TranslateTransform(n, this.data.gdipXOffset, this.data.gdipYOffset, 0);
        if (n13 == 0 || n14 == 0) {
            Gdip.Graphics_DrawRectangle(n, this.data.gdipPen, n3, n4, n5, n6);
        }
        else {
            final int graphicsPath_new = Gdip.GraphicsPath_new(0);
            if (graphicsPath_new == 0) {
                SWT.error(2);
            }
            if (n11 > n13) {
                if (n12 > n14) {
                    Gdip.GraphicsPath_AddArc(graphicsPath_new, n9 + n11 - n13, n10, n13, n14, 0.0f, -90.0f);
                    Gdip.GraphicsPath_AddArc(graphicsPath_new, n9, n10, n13, n14, -90.0f, -90.0f);
                    Gdip.GraphicsPath_AddArc(graphicsPath_new, n9, n10 + n12 - n14, n13, n14, -180.0f, -90.0f);
                    Gdip.GraphicsPath_AddArc(graphicsPath_new, n9 + n11 - n13, n10 + n12 - n14, n13, n14, -270.0f, -90.0f);
                }
                else {
                    Gdip.GraphicsPath_AddArc(graphicsPath_new, n9 + n11 - n13, n10, n13, n12, -270.0f, -180.0f);
                    Gdip.GraphicsPath_AddArc(graphicsPath_new, n9, n10, n13, n12, -90.0f, -180.0f);
                }
            }
            else if (n12 > n14) {
                Gdip.GraphicsPath_AddArc(graphicsPath_new, n9, n10, n11, n14, 0.0f, -180.0f);
                Gdip.GraphicsPath_AddArc(graphicsPath_new, n9, n10 + n12 - n14, n11, n14, -180.0f, -180.0f);
            }
            else {
                Gdip.GraphicsPath_AddArc(graphicsPath_new, n9, n10, n11, n12, 0.0f, 360.0f);
            }
            Gdip.GraphicsPath_CloseFigure(graphicsPath_new);
            Gdip.Graphics_DrawPath(n, n2, graphicsPath_new);
            Gdip.GraphicsPath_delete(graphicsPath_new);
        }
        Gdip.Graphics_TranslateTransform(n, -this.data.gdipXOffset, -this.data.gdipYOffset, 0);
    }
    
    public void drawString(final String s, final int n, final int n2) {
        this.drawString(s, n, n2, false);
    }
    
    public void drawString(final String s, int left, final int top, final boolean b) {
        if (this.handle == 0) {
            SWT.error(44);
        }
        if (s == null) {
            SWT.error(4);
        }
        final int length = s.length();
        if (length == 0) {
            return;
        }
        final char[] array = new char[length];
        s.getChars(0, length, array, 0);
        final int gdipGraphics = this.data.gdipGraphics;
        if (gdipGraphics != 0) {
            this.checkGC(0x5 | (b ? 0 : 2));
            this.drawText(gdipGraphics, s, left, top, b ? 1 : 0, null);
            return;
        }
        int n;
        if (OS.IsWinCE) {
            n = OS.SetROP2(this.handle, 13);
            OS.SetROP2(this.handle, n);
        }
        else {
            n = OS.GetROP2(this.handle);
        }
        this.checkGC(772);
        final int setBkMode = OS.SetBkMode(this.handle, b ? 1 : 2);
        RECT rect = null;
        SIZE size = null;
        int n2 = 0;
        if ((this.data.style & 0x8000000) != 0x0) {
            if (!b) {
                size = new SIZE();
                OS.GetTextExtentPoint32W(this.handle, array, length, size);
                rect = new RECT();
                rect.left = left;
                rect.right = left + size.cx;
                rect.top = top;
                rect.bottom = top + size.cy;
                n2 = 4;
            }
            --left;
        }
        if (n != 7) {
            OS.ExtTextOutW(this.handle, left, top, n2, rect, array, length, null);
        }
        else {
            final int getTextColor = OS.GetTextColor(this.handle);
            if (b) {
                if (size == null) {
                    size = new SIZE();
                    OS.GetTextExtentPoint32W(this.handle, array, length, size);
                }
                final int cx = size.cx;
                final int cy = size.cy;
                final int createCompatibleBitmap = OS.CreateCompatibleBitmap(this.handle, cx, cy);
                if (createCompatibleBitmap == 0) {
                    SWT.error(2);
                }
                final int createCompatibleDC = OS.CreateCompatibleDC(this.handle);
                final int selectObject = OS.SelectObject(createCompatibleDC, createCompatibleBitmap);
                OS.PatBlt(createCompatibleDC, 0, 0, cx, cy, 66);
                OS.SetBkMode(createCompatibleDC, 1);
                OS.SetTextColor(createCompatibleDC, getTextColor);
                OS.SelectObject(createCompatibleDC, OS.GetCurrentObject(this.handle, 6));
                OS.ExtTextOutW(createCompatibleDC, 0, 0, 0, null, array, length, null);
                OS.BitBlt(this.handle, left, top, cx, cy, createCompatibleDC, 0, 0, 6684742);
                OS.SelectObject(createCompatibleDC, selectObject);
                OS.DeleteDC(createCompatibleDC);
                OS.DeleteObject(createCompatibleBitmap);
            }
            else {
                OS.SetTextColor(this.handle, getTextColor ^ OS.GetBkColor(this.handle));
                OS.ExtTextOutW(this.handle, left, top, n2, rect, array, length, null);
                OS.SetTextColor(this.handle, getTextColor);
            }
        }
        OS.SetBkMode(this.handle, setBkMode);
    }
    
    public void drawText(final String s, final int n, final int n2) {
        this.drawText(s, n, n2, 6);
    }
    
    public void drawText(final String s, final int n, final int n2, final boolean b) {
        int n3 = 6;
        if (b) {
            n3 |= 0x1;
        }
        this.drawText(s, n, n2, n3);
    }
    
    public void drawText(final String s, final int n, final int n2, final int n3) {
        if (this.handle == 0) {
            SWT.error(44);
        }
        if (s == null) {
            SWT.error(4);
        }
        if (s.length() == 0) {
            return;
        }
        final int gdipGraphics = this.data.gdipGraphics;
        if (gdipGraphics != 0) {
            this.checkGC(0x5 | (((n3 & 0x1) != 0x0) ? 0 : 2));
            this.drawText(gdipGraphics, s, n, n2, n3, null);
            return;
        }
        final TCHAR tchar = new TCHAR(this.getCodePage(), s, false);
        final int length = tchar.length();
        if (length == 0) {
            return;
        }
        final RECT rect = new RECT();
        final int n4 = OS.IsWin95 ? 32767 : 117440511;
        OS.SetRect(rect, n, n2, n4, n4);
        int n5 = 0;
        if ((n3 & 0x2) == 0x0) {
            n5 |= 0x20;
        }
        if ((n3 & 0x4) != 0x0) {
            n5 |= 0x40;
        }
        if ((n3 & 0x8) == 0x0) {
            n5 |= 0x800;
        }
        if ((n3 & 0x8) != 0x0 && (this.data.uiState & 0x2) != 0x0) {
            n5 |= 0x100000;
        }
        int n6;
        if (OS.IsWinCE) {
            n6 = OS.SetROP2(this.handle, 13);
            OS.SetROP2(this.handle, n6);
        }
        else {
            n6 = OS.GetROP2(this.handle);
        }
        this.checkGC(772);
        final int setBkMode = OS.SetBkMode(this.handle, ((n3 & 0x1) != 0x0) ? 1 : 2);
        if (n6 != 7) {
            OS.DrawText(this.handle, tchar, length, rect, n5);
        }
        else {
            final int getTextColor = OS.GetTextColor(this.handle);
            if ((n3 & 0x1) != 0x0) {
                OS.DrawText(this.handle, tchar, tchar.length(), rect, n5 | 0x400);
                final int n7 = rect.right - rect.left;
                final int n8 = rect.bottom - rect.top;
                final int createCompatibleBitmap = OS.CreateCompatibleBitmap(this.handle, n7, n8);
                if (createCompatibleBitmap == 0) {
                    SWT.error(2);
                }
                final int createCompatibleDC = OS.CreateCompatibleDC(this.handle);
                final int selectObject = OS.SelectObject(createCompatibleDC, createCompatibleBitmap);
                OS.PatBlt(createCompatibleDC, 0, 0, n7, n8, 66);
                OS.SetBkMode(createCompatibleDC, 1);
                OS.SetTextColor(createCompatibleDC, getTextColor);
                OS.SelectObject(createCompatibleDC, OS.GetCurrentObject(this.handle, 6));
                OS.SetRect(rect, 0, 0, 32767, 32767);
                OS.DrawText(createCompatibleDC, tchar, length, rect, n5);
                OS.BitBlt(this.handle, n, n2, n7, n8, createCompatibleDC, 0, 0, 6684742);
                OS.SelectObject(createCompatibleDC, selectObject);
                OS.DeleteDC(createCompatibleDC);
                OS.DeleteObject(createCompatibleBitmap);
            }
            else {
                OS.SetTextColor(this.handle, getTextColor ^ OS.GetBkColor(this.handle));
                OS.DrawText(this.handle, tchar, length, rect, n5);
                OS.SetTextColor(this.handle, getTextColor);
            }
        }
        OS.SetBkMode(this.handle, setBkMode);
    }
    
    boolean useGDIP(final int n, final char[] array) {
        if (OS.IsWinCE || !OS.IsUnicode) {
            return false;
        }
        final short[] array2 = new short[array.length];
        OS.GetGlyphIndicesW(n, array, array.length, array2, 1);
        for (int i = 0; i < array2.length; ++i) {
            if (array2[i] == -1) {
                switch (array[i]) {
                    case '\t':
                    case '\n':
                    case '\r': {
                        break;
                    }
                    default: {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    
    void drawText(final int n, final String s, final int n2, final int n3, final int n4, final Point point) {
        int length = s.length();
        final char[] array = new char[length];
        s.getChars(0, length, array, 0);
        final int graphics_GetHDC = Gdip.Graphics_GetHDC(n);
        int n5 = this.data.hGDIFont;
        if (n5 == 0 && this.data.font != null) {
            n5 = this.data.font.handle;
        }
        int selectObject = 0;
        if (n5 != 0) {
            selectObject = OS.SelectObject(graphics_GetHDC, n5);
        }
        final TEXTMETRIC textmetric = OS.IsUnicode ? new TEXTMETRICW() : new TEXTMETRICA();
        OS.GetTextMetrics(graphics_GetHDC, textmetric);
        final boolean useGDIP = this.useGDIP(graphics_GetHDC, array);
        if (n5 != 0) {
            OS.SelectObject(graphics_GetHDC, selectObject);
        }
        Gdip.Graphics_ReleaseHDC(n, graphics_GetHDC);
        if (useGDIP) {
            this.drawTextGDIP(n, s, n2, n3, n4, point == null, point);
            return;
        }
        int i = 0;
        int n6 = 0;
        int n7 = 0;
        int n8 = n2;
        int n9 = n3;
        int max = 0;
        int n10 = -1;
        if ((n4 & 0xE) != 0x0) {
            final int n11 = textmetric.tmAveCharWidth * 8;
            while (i < length) {
                final char[] array2 = array;
                final int n12 = n7++;
                final char c = array[i++];
                array2[n12] = c;
                final char c2 = c;
                switch (c2) {
                    case 9: {
                        if ((n4 & 0x4) != 0x0) {
                            n8 = n2 + (((int)(n8 + Math.ceil(this.drawText(n, array, n6, n7 - n6 - 1, n8, n9, n4, n10, textmetric, point == null).Width)) - n2) / n11 + 1) * n11;
                            n10 = -1;
                            n6 = n7;
                            continue;
                        }
                        continue;
                    }
                    case 38: {
                        if ((n4 & 0x8) == 0x0) {
                            continue;
                        }
                        if (i == length) {
                            --n7;
                            continue;
                        }
                        if (array[i] == '&') {
                            ++i;
                            continue;
                        }
                        n10 = --n7 - n6;
                        continue;
                    }
                    case 10:
                    case 13: {
                        if ((n4 & 0x2) != 0x0) {
                            final int n13 = n7 - n6 - 1;
                            if (c2 == '\r' && n7 != length && array[n7] == '\n') {
                                ++n7;
                                ++i;
                            }
                            final RectF drawText = this.drawText(n, array, n6, n13, n8, n9, n4, n10, textmetric, point == null);
                            n9 += (int)Math.ceil(drawText.Height);
                            max = Math.max(max, n8 + (int)Math.ceil(drawText.Width));
                            n8 = n2;
                            n10 = -1;
                            n6 = n7;
                            continue;
                        }
                        continue;
                    }
                    default: {
                        continue;
                    }
                }
            }
            length = n7;
        }
        final RectF drawText2 = this.drawText(n, array, n6, length - n6, n8, n9, n4, n10, textmetric, point == null);
        if (point != null) {
            final int y = (int)(n9 + Math.ceil(drawText2.Height));
            point.x = Math.max(max, n8 + (int)Math.ceil(drawText2.Width));
            point.y = y;
        }
    }
    
    RectF drawText(final int n, char[] array, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7, final TEXTMETRIC textmetric, final boolean b) {
        final boolean b2 = b && n7 != -1 && (this.data.uiState & 0x2) == 0x0;
        final boolean b3 = !b || b2 || (n6 & 0x1) == 0x0 || (this.data.style & 0x8000000) != 0x0 || (n6 & 0x2) != 0x0;
        if (n3 <= 0) {
            RectF rectF = null;
            if (b3) {
                rectF = new RectF();
                rectF.Height = textmetric.tmHeight;
            }
            return rectF;
        }
        final int nGlyphs = n3 * 3 / 2 + 16;
        final GCP_RESULTS gcp_RESULTS = new GCP_RESULTS();
        gcp_RESULTS.lStructSize = GCP_RESULTS.sizeof;
        gcp_RESULTS.nGlyphs = nGlyphs;
        final int getProcessHeap = OS.GetProcessHeap();
        final GCP_RESULTS gcp_RESULTS2 = gcp_RESULTS;
        final int heapAlloc = OS.HeapAlloc(getProcessHeap, 8, nGlyphs * 4);
        gcp_RESULTS2.lpDx = heapAlloc;
        final int n8 = heapAlloc;
        final GCP_RESULTS gcp_RESULTS3 = gcp_RESULTS;
        final int heapAlloc2 = OS.HeapAlloc(getProcessHeap, 8, nGlyphs * 2);
        gcp_RESULTS3.lpGlyphs = heapAlloc2;
        final int n9 = heapAlloc2;
        int n10 = 0;
        final int n11 = 50;
        if (b2) {
            final GCP_RESULTS gcp_RESULTS4 = gcp_RESULTS;
            final int heapAlloc3 = OS.HeapAlloc(getProcessHeap, 8, nGlyphs * 4);
            gcp_RESULTS4.lpOrder = heapAlloc3;
            n10 = heapAlloc3;
        }
        final int graphics_GetHDC = Gdip.Graphics_GetHDC(n);
        int n12 = this.data.hGDIFont;
        if (n12 == 0 && this.data.font != null) {
            n12 = this.data.font.handle;
        }
        int selectObject = 0;
        if (n12 != 0) {
            selectObject = OS.SelectObject(graphics_GetHDC, n12);
        }
        if (n2 != 0) {
            final char[] array2 = new char[n3];
            System.arraycopy(array, n2, array2, 0, n3);
            array = array2;
        }
        if ((this.data.style & 0x8000000) != 0x0) {
            OS.SetLayout(graphics_GetHDC, OS.GetLayout(graphics_GetHDC) | 0x1);
        }
        OS.GetCharacterPlacementW(graphics_GetHDC, array, n3, 0, gcp_RESULTS, n11);
        if ((this.data.style & 0x8000000) != 0x0) {
            OS.SetLayout(graphics_GetHDC, OS.GetLayout(graphics_GetHDC) & 0xFFFFFFFE);
        }
        if (n12 != 0) {
            OS.SelectObject(graphics_GetHDC, selectObject);
        }
        Gdip.Graphics_ReleaseHDC(n, graphics_GetHDC);
        final int nGlyphs2 = gcp_RESULTS.nGlyphs;
        int n13 = n4;
        final int n14 = n5 + textmetric.tmAscent;
        final int[] array3 = new int[nGlyphs2];
        OS.MoveMemory(array3, gcp_RESULTS.lpDx, nGlyphs2 * 4);
        final float[] array4 = new float[array3.length * 2];
        int i = 0;
        int n15 = 0;
        while (i < array3.length) {
            array4[n15++] = n13;
            array4[n15++] = n14;
            n13 += array3[i];
            ++i;
        }
        RectF rectF2 = null;
        if (b3) {
            rectF2 = new RectF();
            Gdip.Graphics_MeasureDriverString(n, n9, nGlyphs2, this.data.gdipFont, array4, 0, 0, rectF2);
        }
        if (b) {
            if ((n6 & 0x1) == 0x0) {
                Gdip.Graphics_FillRectangle(n, this.data.gdipBrush, n4, n5, (int)Math.ceil(rectF2.Width), (int)Math.ceil(rectF2.Height));
            }
            int graphics_Save = 0;
            final int fgBrush = this.getFgBrush();
            if ((this.data.style & 0x8000000) != 0x0) {
                switch (Gdip.Brush_GetType(fgBrush)) {
                    case 4: {
                        Gdip.LinearGradientBrush_ScaleTransform(fgBrush, -1.0f, 1.0f, 0);
                        Gdip.LinearGradientBrush_TranslateTransform(fgBrush, -2 * n4 - rectF2.Width, 0.0f, 0);
                        break;
                    }
                    case 2: {
                        Gdip.TextureBrush_ScaleTransform(fgBrush, -1.0f, 1.0f, 0);
                        Gdip.TextureBrush_TranslateTransform(fgBrush, -2 * n4 - rectF2.Width, 0.0f, 0);
                        break;
                    }
                }
                graphics_Save = Gdip.Graphics_Save(n);
                Gdip.Graphics_ScaleTransform(n, -1.0f, 1.0f, 0);
                Gdip.Graphics_TranslateTransform(n, -2 * n4 - rectF2.Width, 0.0f, 0);
            }
            Gdip.Graphics_DrawDriverString(n, n9, gcp_RESULTS.nGlyphs, this.data.gdipFont, fgBrush, array4, 0, 0);
            if ((this.data.style & 0x8000000) != 0x0) {
                switch (Gdip.Brush_GetType(fgBrush)) {
                    case 4: {
                        Gdip.LinearGradientBrush_ResetTransform(fgBrush);
                        break;
                    }
                    case 2: {
                        Gdip.TextureBrush_ResetTransform(fgBrush);
                        break;
                    }
                }
                Gdip.Graphics_Restore(n, graphics_Save);
            }
            if (b2) {
                final int pen_new = Gdip.Pen_new(fgBrush, 1.0f);
                if (pen_new != 0) {
                    final int[] array5 = { 0 };
                    OS.MoveMemory(array5, gcp_RESULTS.lpOrder + n7 * 4, 4);
                    int n16;
                    int n17;
                    if ((this.data.style & 0x8000000) != 0x0) {
                        n16 = (int)Math.ceil(rectF2.Width) - (int)array4[array5[0] * 2] + 2 * n4;
                        n17 = n16 - array3[array5[0]];
                    }
                    else {
                        n16 = (int)array4[array5[0] * 2];
                        n17 = n16 + array3[array5[0]];
                    }
                    final int n18 = n5 + textmetric.tmAscent + 2;
                    final int graphics_GetSmoothingMode = Gdip.Graphics_GetSmoothingMode(n);
                    Gdip.Graphics_SetSmoothingMode(n, 3);
                    Gdip.Graphics_DrawLine(n, pen_new, n16, n18, n17, n18);
                    Gdip.Graphics_SetSmoothingMode(n, graphics_GetSmoothingMode);
                    Gdip.Pen_delete(pen_new);
                }
            }
        }
        if (n10 != 0) {
            OS.HeapFree(getProcessHeap, 0, n10);
        }
        OS.HeapFree(getProcessHeap, 0, n9);
        OS.HeapFree(getProcessHeap, 0, n8);
        return rectF2;
    }
    
    void drawTextGDIP(final int n, final String s, final int n2, final int n3, final int n4, final boolean b, final Point point) {
        final boolean b2 = !b || (n4 & 0x1) == 0x0;
        final int length = s.length();
        char[] array;
        if (length != 0) {
            array = new char[length];
            s.getChars(0, length, array, 0);
        }
        else {
            if (b) {
                return;
            }
            array = new char[] { ' ' };
        }
        final PointF pointF = new PointF();
        final int stringFormat_Clone = Gdip.StringFormat_Clone(Gdip.StringFormat_GenericTypographic());
        int n5 = Gdip.StringFormat_GetFormatFlags(stringFormat_Clone) | 0x800;
        if ((this.data.style & 0x8000000) != 0x0) {
            n5 |= 0x1;
        }
        Gdip.StringFormat_SetFormatFlags(stringFormat_Clone, n5);
        final float[] array2;
        if ((n4 & 0x4) != 0x0) {
            array2 = new float[] { this.measureSpace(this.data.gdipFont, stringFormat_Clone) * 8.0f };
        }
        else {
            final float[] array3 = { 0.0f };
        }
        final float[] array4 = array2;
        Gdip.StringFormat_SetTabStops(stringFormat_Clone, 0.0f, array4.length, array4);
        int n6 = ((n4 & 0x8) != 0x0) ? 1 : 0;
        if ((n4 & 0x8) != 0x0 && (this.data.uiState & 0x2) != 0x0) {
            n6 = 2;
        }
        Gdip.StringFormat_SetHotkeyPrefix(stringFormat_Clone, n6);
        RectF rectF = null;
        if (b2) {
            rectF = new RectF();
            Gdip.Graphics_MeasureString(n, array, array.length, this.data.gdipFont, pointF, stringFormat_Clone, rectF);
        }
        if (b) {
            if ((n4 & 0x1) == 0x0) {
                Gdip.Graphics_FillRectangle(n, this.data.gdipBrush, n2, n3, (int)Math.ceil(rectF.Width), (int)Math.ceil(rectF.Height));
            }
            int graphics_Save = 0;
            final int fgBrush = this.getFgBrush();
            if ((this.data.style & 0x8000000) != 0x0) {
                switch (Gdip.Brush_GetType(fgBrush)) {
                    case 4: {
                        Gdip.LinearGradientBrush_ScaleTransform(fgBrush, -1.0f, 1.0f, 0);
                        Gdip.LinearGradientBrush_TranslateTransform(fgBrush, -2 * n2, 0.0f, 0);
                        break;
                    }
                    case 2: {
                        Gdip.TextureBrush_ScaleTransform(fgBrush, -1.0f, 1.0f, 0);
                        Gdip.TextureBrush_TranslateTransform(fgBrush, -2 * n2, 0.0f, 0);
                        break;
                    }
                }
                graphics_Save = Gdip.Graphics_Save(n);
                Gdip.Graphics_ScaleTransform(n, -1.0f, 1.0f, 0);
                Gdip.Graphics_TranslateTransform(n, -2 * n2, 0.0f, 0);
            }
            pointF.X = n2;
            pointF.Y = n3;
            Gdip.Graphics_DrawString(n, array, array.length, this.data.gdipFont, pointF, stringFormat_Clone, fgBrush);
            if ((this.data.style & 0x8000000) != 0x0) {
                switch (Gdip.Brush_GetType(fgBrush)) {
                    case 4: {
                        Gdip.LinearGradientBrush_ResetTransform(fgBrush);
                        break;
                    }
                    case 2: {
                        Gdip.TextureBrush_ResetTransform(fgBrush);
                        break;
                    }
                }
                Gdip.Graphics_Restore(n, graphics_Save);
            }
        }
        Gdip.StringFormat_delete(stringFormat_Clone);
        if (length == 0) {
            rectF.Width = 0.0f;
        }
        if (point != null) {
            point.x = (int)Math.ceil(rectF.Width);
            point.y = (int)Math.ceil(rectF.Height);
        }
    }
    
    public boolean equals(final Object o) {
        return o == this || (o instanceof GC && this.handle == ((GC)o).handle);
    }
    
    public void fillArc(int n, int n2, int n3, int n4, int n5, int n6) {
        if (this.handle == 0) {
            SWT.error(44);
        }
        this.checkGC(9218);
        if (n3 < 0) {
            n += n3;
            n3 = -n3;
        }
        if (n4 < 0) {
            n2 += n4;
            n4 = -n4;
        }
        if (n3 == 0 || n4 == 0 || n6 == 0) {
            return;
        }
        final int gdipGraphics = this.data.gdipGraphics;
        if (gdipGraphics != 0) {
            if (n3 == n4) {
                Gdip.Graphics_FillPie(gdipGraphics, this.data.gdipBrush, n, n2, n3, n4, -n5, -n6);
            }
            else {
                final int graphics_Save = Gdip.Graphics_Save(gdipGraphics);
                Gdip.Graphics_TranslateTransform(gdipGraphics, n, n2, 0);
                Gdip.Graphics_ScaleTransform(gdipGraphics, n3, n4, 0);
                Gdip.Graphics_FillPie(gdipGraphics, this.data.gdipBrush, 0, 0, 1, 1, -n5, -n6);
                Gdip.Graphics_Restore(gdipGraphics, graphics_Save);
            }
            return;
        }
        if ((this.data.style & 0x8000000) != 0x0) {
            --n;
        }
        if (OS.IsWinCE) {
            if (n6 < 0) {
                n5 += n6;
                n6 = -n6;
            }
            boolean b = true;
            if (n6 >= 360) {
                n6 = 360;
                b = false;
            }
            final int[] array = new int[(n6 + 1) * 2 + (b ? 4 : 0)];
            final int n7 = 2 * n + n3;
            final int n8 = 2 * n2 + n4;
            int n9 = b ? 2 : 0;
            for (int i = 0; i <= n6; ++i) {
                array[n9++] = Compatibility.cos(n5 + i, n3) + n7 >> 1;
                array[n9++] = n8 - Compatibility.sin(n5 + i, n4) >> 1;
            }
            if (b) {
                array[0] = (array[array.length - 2] = n7 >> 1);
                array[1] = (array[array.length - 1] = n8 >> 1);
            }
            OS.Polygon(this.handle, array, array.length / 2);
        }
        else {
            int n11;
            int n10;
            int n13;
            int n12;
            if (n6 >= 360 || n6 <= -360) {
                n10 = (n11 = n + n3);
                n12 = (n13 = n2 + n4 / 2);
            }
            else {
                final boolean b2 = n6 < 0;
                n6 += n5;
                if (b2) {
                    final int n14 = n5;
                    n5 = n6;
                    n6 = n14;
                }
                n11 = Compatibility.cos(n5, n3) + n + n3 / 2;
                n13 = -1 * Compatibility.sin(n5, n4) + n2 + n4 / 2;
                n10 = Compatibility.cos(n6, n3) + n + n3 / 2;
                n12 = -1 * Compatibility.sin(n6, n4) + n2 + n4 / 2;
            }
            OS.Pie(this.handle, n, n2, n + n3 + 1, n2 + n4 + 1, n11, n13, n10, n12);
        }
    }
    
    public void fillGradientRectangle(int x, int y, int n, int n2, final boolean b) {
        if (this.handle == 0) {
            SWT.error(44);
        }
        if (n == 0 || n2 == 0) {
            return;
        }
        final RGB rgb = this.getBackground().getRGB();
        RGB rgb3;
        final RGB rgb2 = rgb3 = this.getForeground().getRGB();
        RGB rgb4 = rgb;
        boolean b2 = false;
        if (n < 0) {
            x += n;
            n = -n;
            if (!b) {
                b2 = true;
            }
        }
        if (n2 < 0) {
            y += n2;
            n2 = -n2;
            if (b) {
                b2 = true;
            }
        }
        if (b2) {
            rgb3 = rgb;
            rgb4 = rgb2;
        }
        if (rgb3.equals(rgb4)) {
            this.fillRectangle(x, y, n, n2);
            return;
        }
        if (this.data.gdipGraphics != 0) {
            this.initGdip();
            final PointF pointF = new PointF();
            final PointF pointF2 = new PointF();
            pointF.X = x;
            pointF.Y = y;
            if (b) {
                pointF2.X = pointF.X;
                pointF2.Y = pointF.Y + n2;
            }
            else {
                pointF2.X = pointF.X + n;
                pointF2.Y = pointF.Y;
            }
            final int color_new = Gdip.Color_new(this.data.alpha << 24 | ((rgb3.red & 0xFF) << 16 | (rgb3.green & 0xFF) << 8 | (rgb3.blue & 0xFF)));
            if (color_new == 0) {
                SWT.error(2);
            }
            final int color_new2 = Gdip.Color_new(this.data.alpha << 24 | ((rgb4.red & 0xFF) << 16 | (rgb4.green & 0xFF) << 8 | (rgb4.blue & 0xFF)));
            if (color_new2 == 0) {
                SWT.error(2);
            }
            final int linearGradientBrush_new = Gdip.LinearGradientBrush_new(pointF, pointF2, color_new, color_new2);
            Gdip.Graphics_FillRectangle(this.data.gdipGraphics, linearGradientBrush_new, x, y, n, n2);
            Gdip.LinearGradientBrush_delete(linearGradientBrush_new);
            Gdip.Color_delete(color_new);
            Gdip.Color_delete(color_new2);
            return;
        }
        int n3;
        if (OS.IsWinCE) {
            n3 = OS.SetROP2(this.handle, 13);
            OS.SetROP2(this.handle, n3);
        }
        else {
            n3 = OS.GetROP2(this.handle);
        }
        if (OS.IsWinNT && n3 != 7 && OS.GetDeviceCaps(this.handle, 2) != 2) {
            final int getProcessHeap = OS.GetProcessHeap();
            final int heapAlloc = OS.HeapAlloc(getProcessHeap, 8, GRADIENT_RECT.sizeof + TRIVERTEX.sizeof * 2);
            if (heapAlloc == 0) {
                SWT.error(2);
            }
            final int n4 = heapAlloc + GRADIENT_RECT.sizeof;
            final GRADIENT_RECT gradient_RECT = new GRADIENT_RECT();
            gradient_RECT.UpperLeft = 0;
            gradient_RECT.LowerRight = 1;
            OS.MoveMemory(heapAlloc, gradient_RECT, GRADIENT_RECT.sizeof);
            final TRIVERTEX trivertex = new TRIVERTEX();
            trivertex.x = x;
            trivertex.y = y;
            trivertex.Red = (short)(rgb3.red << 8 | rgb3.red);
            trivertex.Green = (short)(rgb3.green << 8 | rgb3.green);
            trivertex.Blue = (short)(rgb3.blue << 8 | rgb3.blue);
            trivertex.Alpha = -1;
            OS.MoveMemory(n4, trivertex, TRIVERTEX.sizeof);
            trivertex.x = x + n;
            trivertex.y = y + n2;
            trivertex.Red = (short)(rgb4.red << 8 | rgb4.red);
            trivertex.Green = (short)(rgb4.green << 8 | rgb4.green);
            trivertex.Blue = (short)(rgb4.blue << 8 | rgb4.blue);
            trivertex.Alpha = -1;
            OS.MoveMemory(n4 + TRIVERTEX.sizeof, trivertex, TRIVERTEX.sizeof);
            final boolean gradientFill = OS.GradientFill(this.handle, n4, 2, heapAlloc, 1, b ? 1 : 0);
            OS.HeapFree(getProcessHeap, 0, heapAlloc);
            if (gradientFill) {
                return;
            }
        }
        final int getDeviceCaps = OS.GetDeviceCaps(this.handle, 12);
        final int n5 = (getDeviceCaps >= 24) ? 8 : ((getDeviceCaps >= 15) ? 5 : 0);
        ImageData.fillGradientRectangle(this, this.data.device, x, y, n, n2, b, rgb3, rgb4, n5, n5, n5);
    }
    
    public void fillOval(int n, final int n2, final int n3, final int n4) {
        if (this.handle == 0) {
            SWT.error(44);
        }
        this.checkGC(9218);
        if (this.data.gdipGraphics != 0) {
            Gdip.Graphics_FillEllipse(this.data.gdipGraphics, this.data.gdipBrush, n, n2, n3, n4);
            return;
        }
        if ((this.data.style & 0x8000000) != 0x0) {
            --n;
        }
        OS.Ellipse(this.handle, n, n2, n + n3 + 1, n2 + n4 + 1);
    }
    
    public void fillPath(final Path path) {
        if (this.handle == 0) {
            SWT.error(44);
        }
        if (path == null) {
            SWT.error(4);
        }
        if (path.handle == 0) {
            SWT.error(5);
        }
        this.initGdip();
        this.checkGC(9218);
        Gdip.GraphicsPath_SetFillMode(path.handle, (OS.GetPolyFillMode(this.handle) == 2) ? 1 : 0);
        Gdip.Graphics_FillPath(this.data.gdipGraphics, this.data.gdipBrush, path.handle);
    }
    
    public void fillPolygon(final int[] array) {
        if (this.handle == 0) {
            SWT.error(44);
        }
        if (array == null) {
            SWT.error(4);
        }
        this.checkGC(9218);
        if (this.data.gdipGraphics != 0) {
            Gdip.Graphics_FillPolygon(this.data.gdipGraphics, this.data.gdipBrush, array, array.length / 2, (OS.GetPolyFillMode(this.handle) == 2) ? 1 : 0);
            return;
        }
        if ((this.data.style & 0x8000000) != 0x0) {
            for (int i = 0; i < array.length; i += 2) {
                final int n = i;
                --array[n];
            }
        }
        OS.Polygon(this.handle, array, array.length / 2);
        if ((this.data.style & 0x8000000) != 0x0) {
            for (int j = 0; j < array.length; j += 2) {
                final int n2 = j;
                ++array[n2];
            }
        }
    }
    
    public void fillRectangle(int n, int n2, int n3, int n4) {
        if (this.handle == 0) {
            SWT.error(44);
        }
        this.checkGC(9218);
        if (this.data.gdipGraphics != 0) {
            if (n3 < 0) {
                n += n3;
                n3 = -n3;
            }
            if (n4 < 0) {
                n2 += n4;
                n4 = -n4;
            }
            Gdip.Graphics_FillRectangle(this.data.gdipGraphics, this.data.gdipBrush, n, n2, n3, n4);
            return;
        }
        int n5;
        if (OS.IsWinCE) {
            n5 = OS.SetROP2(this.handle, 13);
            OS.SetROP2(this.handle, n5);
        }
        else {
            n5 = OS.GetROP2(this.handle);
        }
        OS.PatBlt(this.handle, n, n2, n3, n4, (n5 == 7) ? 5898313 : 15728673);
    }
    
    public void fillRectangle(final Rectangle rectangle) {
        if (rectangle == null) {
            SWT.error(4);
        }
        this.fillRectangle(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
    }
    
    public void fillRoundRectangle(int n, final int n2, final int n3, final int n4, final int n5, final int n6) {
        if (this.handle == 0) {
            SWT.error(44);
        }
        this.checkGC(9218);
        if (this.data.gdipGraphics != 0) {
            this.fillRoundRectangleGdip(this.data.gdipGraphics, this.data.gdipBrush, n, n2, n3, n4, n5, n6);
            return;
        }
        if ((this.data.style & 0x8000000) != 0x0) {
            --n;
        }
        OS.RoundRect(this.handle, n, n2, n + n3 + 1, n2 + n4 + 1, n5, n6);
    }
    
    void fillRoundRectangleGdip(final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7, final int n8) {
        int n9 = n3;
        int n10 = n4;
        int n11 = n5;
        int n12 = n6;
        int n13 = n7;
        int n14 = n8;
        if (n11 < 0) {
            n11 = 0 - n11;
            n9 -= n11;
        }
        if (n12 < 0) {
            n12 = 0 - n12;
            n10 -= n12;
        }
        if (n13 < 0) {
            n13 = 0 - n13;
        }
        if (n14 < 0) {
            n14 = 0 - n14;
        }
        if (n13 == 0 || n14 == 0) {
            Gdip.Graphics_FillRectangle(this.data.gdipGraphics, this.data.gdipBrush, n3, n4, n5, n6);
        }
        else {
            final int graphicsPath_new = Gdip.GraphicsPath_new(0);
            if (graphicsPath_new == 0) {
                SWT.error(2);
            }
            if (n11 > n13) {
                if (n12 > n14) {
                    Gdip.GraphicsPath_AddArc(graphicsPath_new, n9 + n11 - n13, n10, n13, n14, 0.0f, -90.0f);
                    Gdip.GraphicsPath_AddArc(graphicsPath_new, n9, n10, n13, n14, -90.0f, -90.0f);
                    Gdip.GraphicsPath_AddArc(graphicsPath_new, n9, n10 + n12 - n14, n13, n14, -180.0f, -90.0f);
                    Gdip.GraphicsPath_AddArc(graphicsPath_new, n9 + n11 - n13, n10 + n12 - n14, n13, n14, -270.0f, -90.0f);
                }
                else {
                    Gdip.GraphicsPath_AddArc(graphicsPath_new, n9 + n11 - n13, n10, n13, n12, -270.0f, -180.0f);
                    Gdip.GraphicsPath_AddArc(graphicsPath_new, n9, n10, n13, n12, -90.0f, -180.0f);
                }
            }
            else if (n12 > n14) {
                Gdip.GraphicsPath_AddArc(graphicsPath_new, n9, n10, n11, n14, 0.0f, -180.0f);
                Gdip.GraphicsPath_AddArc(graphicsPath_new, n9, n10 + n12 - n14, n11, n14, -180.0f, -180.0f);
            }
            else {
                Gdip.GraphicsPath_AddArc(graphicsPath_new, n9, n10, n11, n12, 0.0f, 360.0f);
            }
            Gdip.GraphicsPath_CloseFigure(graphicsPath_new);
            Gdip.Graphics_FillPath(n, n2, graphicsPath_new);
            Gdip.GraphicsPath_delete(graphicsPath_new);
        }
    }
    
    void flush() {
        if (this.data.gdipGraphics != 0) {
            Gdip.Graphics_Flush(this.data.gdipGraphics, 0);
            Gdip.Graphics_ReleaseHDC(this.data.gdipGraphics, Gdip.Graphics_GetHDC(this.data.gdipGraphics));
        }
    }
    
    public int getAdvanceWidth(final char c) {
        if (this.handle == 0) {
            SWT.error(44);
        }
        this.checkGC(4);
        if (OS.IsWinCE) {
            final SIZE size = new SIZE();
            OS.GetTextExtentPoint32W(this.handle, new char[] { c }, 1, size);
            return size.cx;
        }
        int tchar;
        if ((tchar = c) > 127) {
            tchar = new TCHAR(this.getCodePage(), c, false).tcharAt(0);
        }
        final int[] array = { 0 };
        OS.GetCharWidth(this.handle, tchar, tchar, array);
        return array[0];
    }
    
    public boolean getAdvanced() {
        if (this.handle == 0) {
            SWT.error(44);
        }
        return this.data.gdipGraphics != 0;
    }
    
    public int getAlpha() {
        if (this.handle == 0) {
            SWT.error(44);
        }
        return this.data.alpha;
    }
    
    public int getAntialias() {
        if (this.handle == 0) {
            SWT.error(44);
        }
        if (this.data.gdipGraphics == 0) {
            return -1;
        }
        switch (Gdip.Graphics_GetSmoothingMode(this.data.gdipGraphics)) {
            case 0: {
                return -1;
            }
            case 1:
            case 3: {
                return 0;
            }
            case 2:
            case 4:
            case 5: {
                return 1;
            }
            default: {
                return -1;
            }
        }
    }
    
    public Color getBackground() {
        if (this.handle == 0) {
            SWT.error(44);
        }
        return Color.win32_new(this.data.device, this.data.background);
    }
    
    public Pattern getBackgroundPattern() {
        if (this.handle == 0) {
            SWT.error(44);
        }
        return this.data.backgroundPattern;
    }
    
    public int getCharWidth(final char c) {
        if (this.handle == 0) {
            SWT.error(44);
        }
        this.checkGC(4);
        if (!OS.IsWinCE) {
            int tchar;
            if ((tchar = c) > 127) {
                tchar = new TCHAR(this.getCodePage(), c, false).tcharAt(0);
            }
            final int[] array = new int[3];
            if (OS.GetCharABCWidths(this.handle, tchar, tchar, array)) {
                return array[1];
            }
        }
        final TEXTMETRIC textmetric = OS.IsUnicode ? new TEXTMETRICW() : new TEXTMETRICA();
        OS.GetTextMetrics(this.handle, textmetric);
        final SIZE size = new SIZE();
        OS.GetTextExtentPoint32W(this.handle, new char[] { c }, 1, size);
        return size.cx - textmetric.tmOverhang;
    }
    
    public Rectangle getClipping() {
        if (this.handle == 0) {
            SWT.error(44);
        }
        final int gdipGraphics = this.data.gdipGraphics;
        if (gdipGraphics != 0) {
            final Rect rect = new Rect();
            Gdip.Graphics_SetPixelOffsetMode(gdipGraphics, 3);
            Gdip.Graphics_GetVisibleClipBounds(gdipGraphics, rect);
            Gdip.Graphics_SetPixelOffsetMode(gdipGraphics, 4);
            return new Rectangle(rect.X, rect.Y, rect.Width, rect.Height);
        }
        final RECT rect2 = new RECT();
        OS.GetClipBox(this.handle, rect2);
        return new Rectangle(rect2.left, rect2.top, rect2.right - rect2.left, rect2.bottom - rect2.top);
    }
    
    public void getClipping(final Region region) {
        if (this.handle == 0) {
            SWT.error(44);
        }
        if (region == null) {
            SWT.error(4);
        }
        if (region.isDisposed()) {
            SWT.error(5);
        }
        final int gdipGraphics = this.data.gdipGraphics;
        if (gdipGraphics != 0) {
            final int region_new = Gdip.Region_new();
            Gdip.Graphics_GetClip(this.data.gdipGraphics, region_new);
            if (Gdip.Region_IsInfinite(region_new, gdipGraphics)) {
                final Rect rect = new Rect();
                Gdip.Graphics_SetPixelOffsetMode(gdipGraphics, 3);
                Gdip.Graphics_GetVisibleClipBounds(gdipGraphics, rect);
                Gdip.Graphics_SetPixelOffsetMode(gdipGraphics, 4);
                OS.SetRectRgn(region.handle, rect.X, rect.Y, rect.X + rect.Width, rect.Y + rect.Height);
            }
            else {
                final int matrix_new = Gdip.Matrix_new(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f);
                final int matrix_new2 = Gdip.Matrix_new(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f);
                Gdip.Graphics_GetTransform(gdipGraphics, matrix_new);
                Gdip.Graphics_SetTransform(gdipGraphics, matrix_new2);
                final int region_GetHRGN = Gdip.Region_GetHRGN(region_new, this.data.gdipGraphics);
                Gdip.Graphics_SetTransform(gdipGraphics, matrix_new);
                Gdip.Matrix_delete(matrix_new2);
                Gdip.Matrix_delete(matrix_new);
                if (!OS.IsWinCE) {
                    final POINT point = new POINT();
                    OS.GetWindowOrgEx(this.handle, point);
                    OS.OffsetRgn(region_GetHRGN, point.x, point.y);
                }
                OS.CombineRgn(region.handle, region_GetHRGN, 0, 5);
                OS.DeleteObject(region_GetHRGN);
            }
            Gdip.Region_delete(region_new);
            return;
        }
        final POINT point2 = new POINT();
        if (!OS.IsWinCE) {
            OS.GetWindowOrgEx(this.handle, point2);
        }
        if (OS.GetClipRgn(this.handle, region.handle) != 1) {
            final RECT rect2 = new RECT();
            OS.GetClipBox(this.handle, rect2);
            OS.SetRectRgn(region.handle, rect2.left, rect2.top, rect2.right, rect2.bottom);
        }
        else {
            OS.OffsetRgn(region.handle, point2.x, point2.y);
        }
        if (!OS.IsWinCE) {
            final int createRectRgn = OS.CreateRectRgn(0, 0, 0, 0);
            if (OS.GetMetaRgn(this.handle, createRectRgn) != 0) {
                OS.OffsetRgn(createRectRgn, point2.x, point2.y);
                OS.CombineRgn(region.handle, createRectRgn, region.handle, 1);
            }
            OS.DeleteObject(createRectRgn);
            final int hwnd = this.data.hwnd;
            if (hwnd != 0 && this.data.ps != null) {
                int createRectRgn2 = OS.CreateRectRgn(0, 0, 0, 0);
                if (OS.GetRandomRgn(this.handle, createRectRgn2, 4) == 1) {
                    if (OS.WIN32_VERSION >= OS.VERSION(4, 10) && (OS.GetLayout(this.handle) & 0x1) != 0x0) {
                        final int getRegionData = OS.GetRegionData(createRectRgn2, 0, null);
                        final int[] array = new int[getRegionData / 4];
                        OS.GetRegionData(createRectRgn2, getRegionData, array);
                        final int extCreateRegion = OS.ExtCreateRegion(new float[] { -1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f }, getRegionData, array);
                        OS.DeleteObject(createRectRgn2);
                        createRectRgn2 = extCreateRegion;
                    }
                    if (OS.IsWinNT) {
                        OS.MapWindowPoints(0, hwnd, point2, 1);
                        OS.OffsetRgn(createRectRgn2, point2.x, point2.y);
                    }
                    OS.CombineRgn(region.handle, createRectRgn2, region.handle, 1);
                }
                OS.DeleteObject(createRectRgn2);
            }
        }
    }
    
    int getCodePage() {
        if (OS.IsUnicode) {
            return 0;
        }
        final int[] array = new int[8];
        OS.TranslateCharsetInfo(OS.GetTextCharset(this.handle), array, 1);
        return array[1];
    }
    
    int getFgBrush() {
        return (this.data.foregroundPattern != null) ? this.data.foregroundPattern.handle : this.data.gdipFgBrush;
    }
    
    public int getFillRule() {
        if (this.handle == 0) {
            SWT.error(44);
        }
        if (OS.IsWinCE) {
            return 1;
        }
        return (OS.GetPolyFillMode(this.handle) == 2) ? 2 : 1;
    }
    
    public Font getFont() {
        if (this.handle == 0) {
            SWT.error(44);
        }
        return this.data.font;
    }
    
    public FontMetrics getFontMetrics() {
        if (this.handle == 0) {
            SWT.error(44);
        }
        this.checkGC(4);
        final TEXTMETRIC textmetric = OS.IsUnicode ? new TEXTMETRICW() : new TEXTMETRICA();
        OS.GetTextMetrics(this.handle, textmetric);
        return FontMetrics.win32_new(textmetric);
    }
    
    public Color getForeground() {
        if (this.handle == 0) {
            SWT.error(44);
        }
        return Color.win32_new(this.data.device, this.data.foreground);
    }
    
    public Pattern getForegroundPattern() {
        if (this.handle == 0) {
            SWT.error(44);
        }
        return this.data.foregroundPattern;
    }
    
    public GCData getGCData() {
        if (this.handle == 0) {
            SWT.error(44);
        }
        return this.data;
    }
    
    public int getInterpolation() {
        if (this.handle == 0) {
            SWT.error(44);
        }
        if (this.data.gdipGraphics == 0) {
            return -1;
        }
        switch (Gdip.Graphics_GetInterpolationMode(this.data.gdipGraphics)) {
            case 0: {
                return -1;
            }
            case 5: {
                return 0;
            }
            case 1:
            case 3: {
                return 1;
            }
            case 2:
            case 4:
            case 6:
            case 7: {
                return 2;
            }
            default: {
                return -1;
            }
        }
    }
    
    public LineAttributes getLineAttributes() {
        if (this.handle == 0) {
            SWT.error(44);
        }
        float[] array = null;
        if (this.data.lineDashes != null) {
            array = new float[this.data.lineDashes.length];
            System.arraycopy(this.data.lineDashes, 0, array, 0, array.length);
        }
        return new LineAttributes(this.data.lineWidth, this.data.lineCap, this.data.lineJoin, this.data.lineStyle, array, this.data.lineDashesOffset, this.data.lineMiterLimit);
    }
    
    public int getLineCap() {
        if (this.handle == 0) {
            SWT.error(44);
        }
        return this.data.lineCap;
    }
    
    public int[] getLineDash() {
        if (this.handle == 0) {
            SWT.error(44);
        }
        if (this.data.lineDashes == null) {
            return null;
        }
        final int[] array = new int[this.data.lineDashes.length];
        for (int i = 0; i < array.length; ++i) {
            array[i] = (int)this.data.lineDashes[i];
        }
        return array;
    }
    
    public int getLineJoin() {
        if (this.handle == 0) {
            SWT.error(44);
        }
        return this.data.lineJoin;
    }
    
    public int getLineStyle() {
        if (this.handle == 0) {
            SWT.error(44);
        }
        return this.data.lineStyle;
    }
    
    public int getLineWidth() {
        if (this.handle == 0) {
            SWT.error(44);
        }
        return (int)this.data.lineWidth;
    }
    
    public int getStyle() {
        if (this.handle == 0) {
            SWT.error(44);
        }
        return this.data.style;
    }
    
    public int getTextAntialias() {
        if (this.handle == 0) {
            SWT.error(44);
        }
        if (this.data.gdipGraphics == 0) {
            return -1;
        }
        switch (Gdip.Graphics_GetTextRenderingHint(this.data.gdipGraphics)) {
            case 0: {
                return -1;
            }
            case 1:
            case 2: {
                return 0;
            }
            case 3:
            case 4:
            case 5: {
                return 1;
            }
            default: {
                return -1;
            }
        }
    }
    
    public void getTransform(final Transform transform) {
        if (this.handle == 0) {
            SWT.error(44);
        }
        if (transform == null) {
            SWT.error(4);
        }
        if (transform.isDisposed()) {
            SWT.error(5);
        }
        final int gdipGraphics = this.data.gdipGraphics;
        if (gdipGraphics != 0) {
            Gdip.Graphics_GetTransform(gdipGraphics, transform.handle);
            final int identity = this.identity();
            Gdip.Matrix_Invert(identity);
            Gdip.Matrix_Multiply(transform.handle, identity, 1);
            Gdip.Matrix_delete(identity);
        }
        else {
            transform.setElements(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f);
        }
    }
    
    public boolean getXORMode() {
        if (this.handle == 0) {
            SWT.error(44);
        }
        int n;
        if (OS.IsWinCE) {
            n = OS.SetROP2(this.handle, 13);
            OS.SetROP2(this.handle, n);
        }
        else {
            n = OS.GetROP2(this.handle);
        }
        return n == 7;
    }
    
    void initGdip() {
        this.data.device.checkGDIP();
        if (this.data.gdipGraphics != 0) {
            return;
        }
        final int createRectRgn = OS.CreateRectRgn(0, 0, 0, 0);
        final int getClipRgn = OS.GetClipRgn(this.handle, createRectRgn);
        if (!OS.IsWinCE) {
            final POINT point = new POINT();
            OS.GetWindowOrgEx(this.handle, point);
            OS.OffsetRgn(createRectRgn, point.x, point.y);
        }
        OS.SelectClipRgn(this.handle, 0);
        if ((this.data.style & 0x8000000) != 0x0) {
            OS.SetLayout(this.handle, OS.GetLayout(this.handle) & 0xFFFFFFFE);
        }
        final GCData data = this.data;
        final int graphics_new = Gdip.Graphics_new(this.handle);
        data.gdipGraphics = graphics_new;
        final int n = graphics_new;
        if (n == 0) {
            SWT.error(2);
        }
        Gdip.Graphics_SetPageUnit(n, 2);
        Gdip.Graphics_SetPixelOffsetMode(n, 4);
        if ((this.data.style & 0x8000000) != 0x0) {
            final int identity = this.identity();
            Gdip.Graphics_SetTransform(n, identity);
            Gdip.Matrix_delete(identity);
        }
        if (getClipRgn == 1) {
            this.setClipping(createRectRgn);
        }
        OS.DeleteObject(createRectRgn);
        this.data.state = 0;
        if (this.data.hPen != 0) {
            OS.SelectObject(this.handle, OS.GetStockObject(8));
            OS.DeleteObject(this.data.hPen);
            this.data.hPen = 0;
        }
        if (this.data.hBrush != 0) {
            OS.SelectObject(this.handle, OS.GetStockObject(5));
            OS.DeleteObject(this.data.hBrush);
            this.data.hBrush = 0;
        }
    }
    
    int identity() {
        if ((this.data.style & 0x8000000) != 0x0) {
            int n;
            if (OS.GetDeviceCaps(this.handle, 2) == 2) {
                n = OS.GetDeviceCaps(this.handle, 110);
            }
            else {
                final Image image = this.data.image;
                if (image != null) {
                    final BITMAP bitmap = new BITMAP();
                    OS.GetObject(image.handle, BITMAP.sizeof, bitmap);
                    n = bitmap.bmWidth;
                }
                else {
                    final int n2 = OS.IsWinCE ? this.data.hwnd : OS.WindowFromDC(this.handle);
                    if (n2 != 0) {
                        final RECT rect = new RECT();
                        OS.GetClientRect(n2, rect);
                        n = rect.right - rect.left;
                    }
                    else {
                        final int getCurrentObject = OS.GetCurrentObject(this.handle, 7);
                        final BITMAP bitmap2 = new BITMAP();
                        OS.GetObject(getCurrentObject, BITMAP.sizeof, bitmap2);
                        n = bitmap2.bmWidth;
                    }
                }
            }
            final POINT point = new POINT();
            if (!OS.IsWinCE) {
                OS.GetWindowOrgEx(this.handle, point);
            }
            return Gdip.Matrix_new(-1.0f, 0.0f, 0.0f, 1.0f, n + 2 * point.x, 0.0f);
        }
        return Gdip.Matrix_new(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f);
    }
    
    void init(final Drawable drawable, final GCData data, final int handle) {
        if (data.foreground != -1) {
            data.state &= 0xFFFFF6FE;
        }
        else {
            data.foreground = OS.GetTextColor(handle);
        }
        if (data.background != -1) {
            data.state &= 0xFFFFF9FD;
        }
        else {
            data.background = OS.GetBkColor(handle);
        }
        data.state &= 0xFFFFCFFF;
        if (data.font != null) {
            data.state &= 0xFFFFFFFB;
        }
        else {
            data.font = Font.win32_new(this.device, OS.GetCurrentObject(handle, 6));
        }
        final int hPalette = data.device.hPalette;
        if (hPalette != 0) {
            OS.SelectPalette(handle, hPalette, true);
            OS.RealizePalette(handle);
        }
        final Image image = data.image;
        if (image != null) {
            data.hNullBitmap = OS.SelectObject(handle, image.handle);
            image.memGC = this;
        }
        final int layout = data.layout;
        if (layout != -1 && !OS.IsWinCE && OS.WIN32_VERSION >= OS.VERSION(4, 10)) {
            final int getLayout = OS.GetLayout(handle);
            if ((getLayout & 0x1) != (layout & 0x1)) {
                OS.SetLayout(handle, (getLayout & 0xFFFFFFFE) | layout);
            }
            if ((data.style & 0x4000000) != 0x0) {
                data.style |= 0x8000000;
            }
        }
        this.drawable = drawable;
        this.data = data;
        this.handle = handle;
    }
    
    public int hashCode() {
        return this.handle;
    }
    
    public boolean isClipped() {
        if (this.handle == 0) {
            SWT.error(44);
        }
        final int gdipGraphics = this.data.gdipGraphics;
        if (gdipGraphics != 0) {
            final int region_new = Gdip.Region_new();
            Gdip.Graphics_GetClip(this.data.gdipGraphics, region_new);
            final boolean region_IsInfinite = Gdip.Region_IsInfinite(region_new, gdipGraphics);
            Gdip.Region_delete(region_new);
            return !region_IsInfinite;
        }
        final int createRectRgn = OS.CreateRectRgn(0, 0, 0, 0);
        final int getClipRgn = OS.GetClipRgn(this.handle, createRectRgn);
        OS.DeleteObject(createRectRgn);
        return getClipRgn > 0;
    }
    
    public boolean isDisposed() {
        return this.handle == 0;
    }
    
    float measureSpace(final int n, final int n2) {
        final PointF pointF = new PointF();
        final RectF rectF = new RectF();
        Gdip.Graphics_MeasureString(this.data.gdipGraphics, new char[] { ' ' }, 1, n, pointF, n2, rectF);
        return rectF.Width;
    }
    
    public void setAdvanced(final boolean b) {
        if (this.handle == 0) {
            SWT.error(44);
        }
        if (b && this.data.gdipGraphics != 0) {
            return;
        }
        if (b) {
            try {
                this.initGdip();
            }
            catch (SWTException ex) {}
        }
        else {
            this.disposeGdip();
            this.data.alpha = 255;
            final GCData data = this.data;
            final GCData data2 = this.data;
            final Pattern pattern = null;
            data2.foregroundPattern = pattern;
            data.backgroundPattern = pattern;
            this.setClipping(this.data.state = 0);
            if ((this.data.style & 0x8000000) != 0x0) {
                OS.SetLayout(this.handle, OS.GetLayout(this.handle) | 0x1);
            }
        }
    }
    
    public void setAntialias(final int n) {
        if (this.handle == 0) {
            SWT.error(44);
        }
        if (this.data.gdipGraphics == 0 && n == -1) {
            return;
        }
        int n2 = 0;
        switch (n) {
            case -1: {
                n2 = 0;
                break;
            }
            case 0: {
                n2 = 3;
                break;
            }
            case 1: {
                n2 = 4;
                break;
            }
            default: {
                SWT.error(5);
                break;
            }
        }
        this.initGdip();
        Gdip.Graphics_SetSmoothingMode(this.data.gdipGraphics, n2);
    }
    
    public void setAlpha(final int n) {
        if (this.handle == 0) {
            SWT.error(44);
        }
        if (this.data.gdipGraphics == 0 && (n & 0xFF) == 0xFF) {
            return;
        }
        this.initGdip();
        this.data.alpha = (n & 0xFF);
        final GCData data = this.data;
        data.state &= 0xFFFFFFFC;
    }
    
    public void setBackground(final Color color) {
        if (this.handle == 0) {
            SWT.error(44);
        }
        if (color == null) {
            SWT.error(4);
        }
        if (color.isDisposed()) {
            SWT.error(5);
        }
        if (this.data.backgroundPattern == null && this.data.background == color.handle) {
            return;
        }
        this.data.backgroundPattern = null;
        this.data.background = color.handle;
        final GCData data = this.data;
        data.state &= 0xFFFFFDFD;
    }
    
    public void setBackgroundPattern(final Pattern backgroundPattern) {
        if (this.handle == 0) {
            SWT.error(44);
        }
        if (backgroundPattern != null && backgroundPattern.isDisposed()) {
            SWT.error(5);
        }
        if (this.data.gdipGraphics == 0 && backgroundPattern == null) {
            return;
        }
        this.initGdip();
        if (this.data.backgroundPattern == backgroundPattern) {
            return;
        }
        this.data.backgroundPattern = backgroundPattern;
        final GCData data = this.data;
        data.state &= 0xFFFFFFFD;
    }
    
    void setClipping(final int n) {
        final int gdipGraphics = this.data.gdipGraphics;
        if (gdipGraphics != 0) {
            if (n != 0) {
                final int region_new = Gdip.Region_new(n);
                Gdip.Graphics_SetClip(gdipGraphics, region_new, 0);
                Gdip.Region_delete(region_new);
            }
            else {
                Gdip.Graphics_ResetClip(gdipGraphics);
            }
        }
        else {
            POINT point = null;
            if (n != 0 && !OS.IsWinCE) {
                point = new POINT();
                OS.GetWindowOrgEx(this.handle, point);
                OS.OffsetRgn(n, -point.x, -point.y);
            }
            OS.SelectClipRgn(this.handle, n);
            if (n != 0 && !OS.IsWinCE) {
                OS.OffsetRgn(n, point.x, point.y);
            }
        }
    }
    
    public void setClipping(final int n, final int n2, final int n3, final int n4) {
        if (this.handle == 0) {
            SWT.error(44);
        }
        final int createRectRgn = OS.CreateRectRgn(n, n2, n + n3, n2 + n4);
        this.setClipping(createRectRgn);
        OS.DeleteObject(createRectRgn);
    }
    
    public void setClipping(final Path path) {
        if (this.handle == 0) {
            SWT.error(44);
        }
        if (path != null && path.isDisposed()) {
            SWT.error(5);
        }
        this.setClipping(0);
        if (path != null) {
            this.initGdip();
            Gdip.GraphicsPath_SetFillMode(path.handle, (OS.GetPolyFillMode(this.handle) == 2) ? 1 : 0);
            Gdip.Graphics_SetClipPath(this.data.gdipGraphics, path.handle);
        }
    }
    
    public void setClipping(final Rectangle rectangle) {
        if (this.handle == 0) {
            SWT.error(44);
        }
        if (rectangle == null) {
            this.setClipping(0);
        }
        else {
            this.setClipping(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
        }
    }
    
    public void setClipping(final Region region) {
        if (this.handle == 0) {
            SWT.error(44);
        }
        if (region != null && region.isDisposed()) {
            SWT.error(5);
        }
        this.setClipping((region != null) ? region.handle : 0);
    }
    
    public void setFillRule(final int n) {
        if (this.handle == 0) {
            SWT.error(44);
        }
        if (OS.IsWinCE) {
            return;
        }
        int n2 = 1;
        switch (n) {
            case 2: {
                n2 = 2;
                break;
            }
            case 1: {
                n2 = 1;
                break;
            }
            default: {
                SWT.error(5);
                break;
            }
        }
        OS.SetPolyFillMode(this.handle, n2);
    }
    
    public void setFont(final Font font) {
        if (this.handle == 0) {
            SWT.error(44);
        }
        if (font != null && font.isDisposed()) {
            SWT.error(5);
        }
        this.data.font = ((font != null) ? font : this.data.device.systemFont);
        final GCData data = this.data;
        data.state &= 0xFFFFFFFB;
    }
    
    public void setForeground(final Color color) {
        if (this.handle == 0) {
            SWT.error(44);
        }
        if (color == null) {
            SWT.error(4);
        }
        if (color.isDisposed()) {
            SWT.error(5);
        }
        if (this.data.foregroundPattern == null && color.handle == this.data.foreground) {
            return;
        }
        this.data.foregroundPattern = null;
        this.data.foreground = color.handle;
        final GCData data = this.data;
        data.state &= 0xFFFFFEFE;
    }
    
    public void setForegroundPattern(final Pattern foregroundPattern) {
        if (this.handle == 0) {
            SWT.error(44);
        }
        if (foregroundPattern != null && foregroundPattern.isDisposed()) {
            SWT.error(5);
        }
        if (this.data.gdipGraphics == 0 && foregroundPattern == null) {
            return;
        }
        this.initGdip();
        if (this.data.foregroundPattern == foregroundPattern) {
            return;
        }
        this.data.foregroundPattern = foregroundPattern;
        final GCData data = this.data;
        data.state &= 0xFFFFFFFE;
    }
    
    public void setInterpolation(final int n) {
        if (this.handle == 0) {
            SWT.error(44);
        }
        if (this.data.gdipGraphics == 0 && n == -1) {
            return;
        }
        int n2 = 0;
        switch (n) {
            case -1: {
                n2 = 0;
                break;
            }
            case 0: {
                n2 = 5;
                break;
            }
            case 1: {
                n2 = 1;
                break;
            }
            case 2: {
                n2 = 2;
                break;
            }
            default: {
                SWT.error(5);
                break;
            }
        }
        this.initGdip();
        Gdip.Graphics_SetInterpolationMode(this.data.gdipGraphics, n2);
    }
    
    public void setLineAttributes(final LineAttributes lineAttributes) {
        if (this.handle == 0) {
            SWT.error(44);
        }
        if (lineAttributes == null) {
            SWT.error(4);
        }
        int n = 0;
        final float width = lineAttributes.width;
        if (width != this.data.lineWidth) {
            n |= 0x4010;
        }
        int style = lineAttributes.style;
        if (style != this.data.lineStyle) {
            n |= 0x8;
            switch (style) {
                case 1:
                case 2:
                case 3:
                case 4:
                case 5: {
                    break;
                }
                case 6: {
                    if (lineAttributes.dash == null) {
                        style = 1;
                        break;
                    }
                    break;
                }
                default: {
                    SWT.error(5);
                    break;
                }
            }
        }
        final int join = lineAttributes.join;
        if (join != this.data.lineJoin) {
            n |= 0x40;
            switch (join) {
                case 1:
                case 2:
                case 3: {
                    break;
                }
                default: {
                    SWT.error(5);
                    break;
                }
            }
        }
        final int cap = lineAttributes.cap;
        if (cap != this.data.lineCap) {
            n |= 0x20;
            switch (cap) {
                case 1:
                case 2:
                case 3: {
                    break;
                }
                default: {
                    SWT.error(5);
                    break;
                }
            }
        }
        float[] dash = lineAttributes.dash;
        final float[] lineDashes = this.data.lineDashes;
        if (dash != null && dash.length > 0) {
            int n2 = (lineDashes == null || lineDashes.length != dash.length) ? 1 : 0;
            for (int i = 0; i < dash.length; ++i) {
                final float n3 = dash[i];
                if (n3 <= 0.0f) {
                    SWT.error(5);
                }
                if (n2 == 0 && lineDashes[i] != n3) {
                    n2 = 1;
                }
            }
            if (n2 != 0) {
                final float[] array = new float[dash.length];
                System.arraycopy(dash, 0, array, 0, dash.length);
                dash = array;
                n |= 0x8;
            }
            else {
                dash = lineDashes;
            }
        }
        else if (lineDashes != null && lineDashes.length > 0) {
            n |= 0x8;
        }
        else {
            dash = lineDashes;
        }
        final float dashOffset = lineAttributes.dashOffset;
        if (dashOffset != this.data.lineDashesOffset) {
            n |= 0x8;
        }
        final float miterLimit = lineAttributes.miterLimit;
        if (miterLimit != this.data.lineMiterLimit) {
            n |= 0x80;
        }
        this.initGdip();
        if (n == 0) {
            return;
        }
        this.data.lineWidth = width;
        this.data.lineStyle = style;
        this.data.lineCap = cap;
        this.data.lineJoin = join;
        this.data.lineDashes = dash;
        this.data.lineDashesOffset = dashOffset;
        this.data.lineMiterLimit = miterLimit;
        final GCData data = this.data;
        data.state &= ~n;
    }
    
    public void setLineCap(final int lineCap) {
        if (this.handle == 0) {
            SWT.error(44);
        }
        if (this.data.lineCap == lineCap) {
            return;
        }
        switch (lineCap) {
            case 1:
            case 2:
            case 3: {
                break;
            }
            default: {
                SWT.error(5);
                break;
            }
        }
        this.data.lineCap = lineCap;
        final GCData data = this.data;
        data.state &= 0xFFFFFFDF;
    }
    
    public void setLineDash(final int[] array) {
        if (this.handle == 0) {
            SWT.error(44);
        }
        final float[] lineDashes = this.data.lineDashes;
        if (array != null && array.length > 0) {
            int n = (this.data.lineStyle != 6 || lineDashes == null || lineDashes.length != array.length) ? 1 : 0;
            for (int i = 0; i < array.length; ++i) {
                final int n2 = array[i];
                if (n2 <= 0) {
                    SWT.error(5);
                }
                if (n == 0 && lineDashes[i] != n2) {
                    n = 1;
                }
            }
            if (n == 0) {
                return;
            }
            this.data.lineDashes = new float[array.length];
            for (int j = 0; j < array.length; ++j) {
                this.data.lineDashes[j] = array[j];
            }
            this.data.lineStyle = 6;
        }
        else {
            if (this.data.lineStyle == 1 && (lineDashes == null || lineDashes.length == 0)) {
                return;
            }
            this.data.lineDashes = null;
            this.data.lineStyle = 1;
        }
        final GCData data = this.data;
        data.state &= 0xFFFFFFF7;
    }
    
    public void setLineJoin(final int lineJoin) {
        if (this.handle == 0) {
            SWT.error(44);
        }
        if (this.data.lineJoin == lineJoin) {
            return;
        }
        switch (lineJoin) {
            case 1:
            case 2:
            case 3: {
                break;
            }
            default: {
                SWT.error(5);
                break;
            }
        }
        this.data.lineJoin = lineJoin;
        final GCData data = this.data;
        data.state &= 0xFFFFFFBF;
    }
    
    public void setLineStyle(int lineStyle) {
        if (this.handle == 0) {
            SWT.error(44);
        }
        if (this.data.lineStyle == lineStyle) {
            return;
        }
        switch (lineStyle) {
            case 1:
            case 2:
            case 3:
            case 4:
            case 5: {
                break;
            }
            case 6: {
                if (this.data.lineDashes == null) {
                    lineStyle = 1;
                    break;
                }
                break;
            }
            default: {
                SWT.error(5);
                break;
            }
        }
        this.data.lineStyle = lineStyle;
        final GCData data = this.data;
        data.state &= 0xFFFFFFF7;
    }
    
    public void setLineWidth(final int n) {
        if (this.handle == 0) {
            SWT.error(44);
        }
        if (this.data.lineWidth == n) {
            return;
        }
        this.data.lineWidth = n;
        final GCData data = this.data;
        data.state &= 0xFFFFBFEF;
    }
    
    public void setXORMode(final boolean b) {
        if (this.handle == 0) {
            SWT.error(44);
        }
        OS.SetROP2(this.handle, b ? 7 : 13);
    }
    
    public void setTextAntialias(final int n) {
        if (this.handle == 0) {
            SWT.error(44);
        }
        if (this.data.gdipGraphics == 0 && n == -1) {
            return;
        }
        int n2 = 0;
        switch (n) {
            case -1: {
                n2 = 0;
                break;
            }
            case 0: {
                n2 = 1;
                break;
            }
            case 1: {
                final int[] array = { 0 };
                OS.SystemParametersInfo(8202, 0, array, 0);
                if (array[0] == 2) {
                    n2 = 5;
                    break;
                }
                n2 = 3;
                break;
            }
            default: {
                SWT.error(5);
                break;
            }
        }
        this.initGdip();
        Gdip.Graphics_SetTextRenderingHint(this.data.gdipGraphics, n2);
    }
    
    public void setTransform(final Transform transform) {
        if (this.handle == 0) {
            SWT.error(44);
        }
        if (transform != null && transform.isDisposed()) {
            SWT.error(5);
        }
        if (this.data.gdipGraphics == 0 && transform == null) {
            return;
        }
        this.initGdip();
        final int identity = this.identity();
        if (transform != null) {
            Gdip.Matrix_Multiply(identity, transform.handle, 0);
        }
        Gdip.Graphics_SetTransform(this.data.gdipGraphics, identity);
        Gdip.Matrix_delete(identity);
        final GCData data = this.data;
        data.state &= 0xFFFFBFFF;
    }
    
    public Point stringExtent(final String s) {
        if (this.handle == 0) {
            SWT.error(44);
        }
        if (s == null) {
            SWT.error(4);
        }
        this.checkGC(4);
        final int length = s.length();
        final int gdipGraphics = this.data.gdipGraphics;
        if (gdipGraphics != 0) {
            final Point point = new Point(0, 0);
            this.drawText(gdipGraphics, s, 0, 0, 0, point);
            return point;
        }
        final SIZE size = new SIZE();
        if (length == 0) {
            OS.GetTextExtentPoint32W(this.handle, new char[] { ' ' }, 1, size);
            return new Point(0, size.cy);
        }
        final char[] array = new char[length];
        s.getChars(0, length, array, 0);
        OS.GetTextExtentPoint32W(this.handle, array, length, size);
        return new Point(size.cx, size.cy);
    }
    
    public Point textExtent(final String s) {
        return this.textExtent(s, 6);
    }
    
    public Point textExtent(final String s, final int n) {
        if (this.handle == 0) {
            SWT.error(44);
        }
        if (s == null) {
            SWT.error(4);
        }
        this.checkGC(4);
        final int gdipGraphics = this.data.gdipGraphics;
        if (gdipGraphics != 0) {
            final Point point = new Point(0, 0);
            this.drawText(gdipGraphics, s, 0, 0, n, point);
            return point;
        }
        if (s.length() == 0) {
            final SIZE size = new SIZE();
            OS.GetTextExtentPoint32W(this.handle, new char[] { ' ' }, 1, size);
            return new Point(0, size.cy);
        }
        final RECT rect = new RECT();
        final TCHAR tchar = new TCHAR(this.getCodePage(), s, false);
        int n2 = 1024;
        if ((n & 0x2) == 0x0) {
            n2 |= 0x20;
        }
        if ((n & 0x4) != 0x0) {
            n2 |= 0x40;
        }
        if ((n & 0x8) == 0x0) {
            n2 |= 0x800;
        }
        OS.DrawText(this.handle, tchar, tchar.length(), rect, n2);
        return new Point(rect.right, rect.bottom);
    }
    
    public String toString() {
        if (this.isDisposed()) {
            return "GC {*DISPOSED*}";
        }
        return "GC {" + this.handle + "}";
    }
    
    public static GC win32_new(final Drawable drawable, final GCData gcData) {
        final GC gc = new GC();
        final int internal_new_GC = drawable.internal_new_GC(gcData);
        gc.device = gcData.device;
        gc.init(drawable, gcData, internal_new_GC);
        return gc;
    }
    
    public static GC win32_new(final int n, final GCData gcData) {
        final GC gc = new GC();
        gc.device = gcData.device;
        gcData.style |= 0x2000000;
        if (!OS.IsWinCE && OS.WIN32_VERSION >= OS.VERSION(4, 10) && (OS.GetLayout(n) & 0x1) != 0x0) {
            gcData.style |= 0xC000000;
        }
        gc.init(null, gcData, n);
        return gc;
    }
}

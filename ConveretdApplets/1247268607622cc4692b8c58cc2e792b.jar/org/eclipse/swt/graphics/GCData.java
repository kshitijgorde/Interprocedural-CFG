// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.graphics;

import org.eclipse.swt.internal.win32.PAINTSTRUCT;

public final class GCData
{
    public Device device;
    public int style;
    public int state;
    public int foreground;
    public int background;
    public Font font;
    public Pattern foregroundPattern;
    public Pattern backgroundPattern;
    public int lineStyle;
    public float lineWidth;
    public int lineCap;
    public int lineJoin;
    public float lineDashesOffset;
    public float[] lineDashes;
    public float lineMiterLimit;
    public int alpha;
    public Image image;
    public int hPen;
    public int hOldPen;
    public int hBrush;
    public int hOldBrush;
    public int hNullBitmap;
    public int hwnd;
    public PAINTSTRUCT ps;
    public int layout;
    public int gdipGraphics;
    public int gdipPen;
    public int gdipBrush;
    public int gdipFgBrush;
    public int gdipBgBrush;
    public int gdipFont;
    public int hGDIFont;
    public float gdipXOffset;
    public float gdipYOffset;
    public int uiState;
    public boolean focusDrawn;
    
    public GCData() {
        this.state = -1;
        this.foreground = -1;
        this.background = -1;
        this.lineStyle = 1;
        this.lineCap = 1;
        this.lineJoin = 1;
        this.lineMiterLimit = 10.0f;
        this.alpha = 255;
        this.layout = -1;
        this.uiState = 0;
    }
}

// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.gdip;

import org.eclipse.swt.internal.Library;
import org.eclipse.swt.internal.Platform;

public class Gdip extends Platform
{
    public static final float FlatnessDefault = 0.25f;
    public static final int BrushTypeSolidColor = 0;
    public static final int BrushTypeHatchFill = 1;
    public static final int BrushTypeTextureFill = 2;
    public static final int BrushTypePathGradient = 3;
    public static final int BrushTypeLinearGradient = 4;
    public static final int ColorAdjustTypeBitmap = 1;
    public static final int ColorMatrixFlagsDefault = 0;
    public static final int CombineModeReplace = 0;
    public static final int CombineModeIntersect = 1;
    public static final int CombineModeUnion = 2;
    public static final int CombineModeXor = 3;
    public static final int CombineModeExclude = 4;
    public static final int CombineModeComplement = 5;
    public static final int FillModeAlternate = 0;
    public static final int FillModeWinding = 1;
    public static final int DashCapFlat = 0;
    public static final int DashCapRound = 2;
    public static final int DashCapTriangle = 3;
    public static final int DashStyleSolid = 0;
    public static final int DashStyleDash = 1;
    public static final int DashStyleDot = 2;
    public static final int DashStyleDashDot = 3;
    public static final int DashStyleDashDotDot = 4;
    public static final int DashStyleCustom = 5;
    public static final int DriverStringOptionsRealizedAdvance = 4;
    public static final int FontStyleRegular = 0;
    public static final int FontStyleBold = 1;
    public static final int FontStyleItalic = 2;
    public static final int FontStyleBoldItalic = 3;
    public static final int FontStyleUnderline = 4;
    public static final int FontStyleStrikeout = 8;
    public static final int PaletteFlagsHasAlpha = 1;
    public static final int FlushIntentionFlush = 0;
    public static final int FlushIntentionSync = 1;
    public static final int HotkeyPrefixNone = 0;
    public static final int HotkeyPrefixShow = 1;
    public static final int HotkeyPrefixHide = 2;
    public static final int LineJoinMiter = 0;
    public static final int LineJoinBevel = 1;
    public static final int LineJoinRound = 2;
    public static final int LineCapFlat = 0;
    public static final int LineCapSquare = 1;
    public static final int LineCapRound = 2;
    public static final int MatrixOrderPrepend = 0;
    public static final int MatrixOrderAppend = 1;
    public static final int QualityModeDefault = 0;
    public static final int QualityModeLow = 1;
    public static final int QualityModeHigh = 2;
    public static final int InterpolationModeDefault = 0;
    public static final int InterpolationModeLowQuality = 1;
    public static final int InterpolationModeHighQuality = 2;
    public static final int InterpolationModeBilinear = 3;
    public static final int InterpolationModeBicubic = 4;
    public static final int InterpolationModeNearestNeighbor = 5;
    public static final int InterpolationModeHighQualityBilinear = 6;
    public static final int InterpolationModeHighQualityBicubic = 7;
    public static final int PathPointTypeStart = 0;
    public static final int PathPointTypeLine = 1;
    public static final int PathPointTypeBezier = 3;
    public static final int PathPointTypePathTypeMask = 7;
    public static final int PathPointTypePathDashMode = 16;
    public static final int PathPointTypePathMarker = 32;
    public static final int PathPointTypeCloseSubpath = 128;
    public static final int PathPointTypeBezier3 = 3;
    public static final int PixelFormatIndexed = 65536;
    public static final int PixelFormatGDI = 131072;
    public static final int PixelFormatAlpha = 262144;
    public static final int PixelFormatPAlpha = 524288;
    public static final int PixelFormatExtended = 1048576;
    public static final int PixelFormatCanonical = 2097152;
    public static final int PixelFormat1bppIndexed = 196865;
    public static final int PixelFormat4bppIndexed = 197634;
    public static final int PixelFormat8bppIndexed = 198659;
    public static final int PixelFormat16bppGrayScale = 1052676;
    public static final int PixelFormat16bppRGB555 = 135173;
    public static final int PixelFormat16bppRGB565 = 135174;
    public static final int PixelFormat16bppARGB1555 = 397319;
    public static final int PixelFormat24bppRGB = 137224;
    public static final int PixelFormat32bppRGB = 139273;
    public static final int PixelFormat32bppARGB = 2498570;
    public static final int PixelFormat32bppPARGB = 925707;
    public static final int PixelFormat48bppRGB = 1060876;
    public static final int PixelFormat64bppARGB = 3424269;
    public static final int PixelFormat64bppPARGB = 1851406;
    public static final int PixelFormatMax = 15;
    public static final int PixelOffsetModeNone = 3;
    public static final int PixelOffsetModeHalf = 4;
    public static final int SmoothingModeDefault = 0;
    public static final int SmoothingModeHighSpeed = 1;
    public static final int SmoothingModeHighQuality = 2;
    public static final int SmoothingModeNone = 3;
    public static final int SmoothingModeAntiAlias8x4 = 4;
    public static final int SmoothingModeAntiAlias = 4;
    public static final int SmoothingModeAntiAlias8x8 = 5;
    public static final int StringFormatFlagsDirectionRightToLeft = 1;
    public static final int StringFormatFlagsDirectionVertical = 2;
    public static final int StringFormatFlagsNoFitBlackBox = 4;
    public static final int StringFormatFlagsDisplayFormatControl = 32;
    public static final int StringFormatFlagsNoFontFallback = 1024;
    public static final int StringFormatFlagsMeasureTrailingSpaces = 2048;
    public static final int StringFormatFlagsNoWrap = 4096;
    public static final int StringFormatFlagsLineLimit = 8192;
    public static final int StringFormatFlagsNoClip = 16384;
    public static final int TextRenderingHintSystemDefault = 0;
    public static final int TextRenderingHintSingleBitPerPixelGridFit = 1;
    public static final int TextRenderingHintSingleBitPerPixel = 2;
    public static final int TextRenderingHintAntiAliasGridFit = 3;
    public static final int TextRenderingHintAntiAlias = 4;
    public static final int TextRenderingHintClearTypeGridFit = 5;
    public static final int UnitPixel = 2;
    public static final int WrapModeTile = 0;
    public static final int WrapModeTileFlipX = 1;
    public static final int WrapModeTileFlipY = 2;
    public static final int WrapModeTileFlipXY = 3;
    public static final int WrapModeClamp = 4;
    
    static {
        Library.loadLibrary("swt-gdip");
    }
    
    public static final native int ColorPalette_sizeof();
    
    public static final native int GdiplusStartupInput_sizeof();
    
    public static final native int GdiplusStartup(final int[] p0, final GdiplusStartupInput p1, final int p2);
    
    public static final native void GdiplusShutdown(final int p0);
    
    public static final native int Bitmap_new(final int p0, final int p1);
    
    public static final native int Bitmap_new(final int p0);
    
    public static final native int Bitmap_new(final int p0, final int p1, final int p2, final int p3, final int p4);
    
    public static final native int Bitmap_new(final char[] p0, final boolean p1);
    
    public static final native void Bitmap_delete(final int p0);
    
    public static final native int Bitmap_GetHBITMAP(final int p0, final int p1, final int[] p2);
    
    public static final native int Bitmap_GetHICON(final int p0, final int[] p1);
    
    public static final native int BitmapData_new();
    
    public static final native void BitmapData_delete(final int p0);
    
    public static final native int Bitmap_LockBits(final int p0, final int p1, final int p2, final int p3, final int p4);
    
    public static final native int Bitmap_UnlockBits(final int p0, final int p1);
    
    public static final native int Brush_Clone(final int p0);
    
    public static final native int Brush_GetType(final int p0);
    
    public static final native int Color_new(final int p0);
    
    public static final native void Color_delete(final int p0);
    
    public static final native int PrivateFontCollection_new();
    
    public static final native void PrivateFontCollection_delete(final int p0);
    
    public static final native int PrivateFontCollection_AddFontFile(final int p0, final char[] p1);
    
    public static final native int Font_new(final int p0, final int p1);
    
    public static final native int Font_new(final int p0, final float p1, final int p2, final int p3);
    
    public static final native int Font_new(final char[] p0, final float p1, final int p2, final int p3, final int p4);
    
    public static final native void Font_delete(final int p0);
    
    public static final native int Font_GetFamily(final int p0, final int p1);
    
    public static final native float Font_GetSize(final int p0);
    
    public static final native int Font_GetStyle(final int p0);
    
    public static final native int Font_GetLogFontW(final int p0, final int p1, final int p2);
    
    public static final native boolean Font_IsAvailable(final int p0);
    
    public static final native int FontFamily_new();
    
    public static final native int FontFamily_new(final char[] p0, final int p1);
    
    public static final native void FontFamily_delete(final int p0);
    
    public static final native int FontFamily_GetFamilyName(final int p0, final char[] p1, final char p2);
    
    public static final native boolean FontFamily_IsAvailable(final int p0);
    
    public static final native int Graphics_new(final int p0);
    
    public static final native void Graphics_delete(final int p0);
    
    public static final native int Graphics_DrawArc(final int p0, final int p1, final int p2, final int p3, final int p4, final int p5, final float p6, final float p7);
    
    public static final native int Graphics_DrawDriverString(final int p0, final int p1, final int p2, final int p3, final int p4, final PointF p5, final int p6, final int p7);
    
    public static final native int Graphics_DrawDriverString(final int p0, final int p1, final int p2, final int p3, final int p4, final float[] p5, final int p6, final int p7);
    
    public static final native int Graphics_DrawEllipse(final int p0, final int p1, final int p2, final int p3, final int p4, final int p5);
    
    public static final native int Graphics_DrawImage(final int p0, final int p1, final int p2, final int p3);
    
    public static final native int Graphics_DrawImage(final int p0, final int p1, final Rect p2, final int p3, final int p4, final int p5, final int p6, final int p7, final int p8, final int p9, final int p10);
    
    public static final native int Graphics_DrawLine(final int p0, final int p1, final int p2, final int p3, final int p4, final int p5);
    
    public static final native int Graphics_DrawLines(final int p0, final int p1, final int[] p2, final int p3);
    
    public static final native int Graphics_DrawPath(final int p0, final int p1, final int p2);
    
    public static final native int Graphics_DrawPolygon(final int p0, final int p1, final int[] p2, final int p3);
    
    public static final native int Graphics_DrawRectangle(final int p0, final int p1, final int p2, final int p3, final int p4, final int p5);
    
    public static final native int Graphics_DrawString(final int p0, final char[] p1, final int p2, final int p3, final PointF p4, final int p5);
    
    public static final native int Graphics_DrawString(final int p0, final char[] p1, final int p2, final int p3, final PointF p4, final int p5, final int p6);
    
    public static final native int Graphics_FillEllipse(final int p0, final int p1, final int p2, final int p3, final int p4, final int p5);
    
    public static final native int Graphics_FillPath(final int p0, final int p1, final int p2);
    
    public static final native void Graphics_Flush(final int p0, final int p1);
    
    public static final native int Graphics_FillPie(final int p0, final int p1, final int p2, final int p3, final int p4, final int p5, final float p6, final float p7);
    
    public static final native int Graphics_FillPolygon(final int p0, final int p1, final int[] p2, final int p3, final int p4);
    
    public static final native int Graphics_FillRectangle(final int p0, final int p1, final int p2, final int p3, final int p4, final int p5);
    
    public static final native int Graphics_GetClipBounds(final int p0, final RectF p1);
    
    public static final native int Graphics_GetClipBounds(final int p0, final Rect p1);
    
    public static final native int Graphics_GetClip(final int p0, final int p1);
    
    public static final native int Graphics_GetHDC(final int p0);
    
    public static final native void Graphics_ReleaseHDC(final int p0, final int p1);
    
    public static final native int Graphics_GetInterpolationMode(final int p0);
    
    public static final native int Graphics_GetSmoothingMode(final int p0);
    
    public static final native int Graphics_GetTextRenderingHint(final int p0);
    
    public static final native int Graphics_GetTransform(final int p0, final int p1);
    
    public static final native int Graphics_GetVisibleClipBounds(final int p0, final Rect p1);
    
    public static final native int Graphics_MeasureDriverString(final int p0, final int p1, final int p2, final int p3, final float[] p4, final int p5, final int p6, final RectF p7);
    
    public static final native int Graphics_MeasureString(final int p0, final char[] p1, final int p2, final int p3, final PointF p4, final RectF p5);
    
    public static final native int Graphics_MeasureString(final int p0, final char[] p1, final int p2, final int p3, final PointF p4, final int p5, final RectF p6);
    
    public static final native int Graphics_ResetClip(final int p0);
    
    public static final native int Graphics_Restore(final int p0, final int p1);
    
    public static final native int Graphics_Save(final int p0);
    
    public static final native int Graphics_ScaleTransform(final int p0, final float p1, final float p2, final int p3);
    
    public static final native int Graphics_SetClip(final int p0, final int p1, final int p2);
    
    public static final native int Graphics_SetClip(final int p0, final Rect p1, final int p2);
    
    public static final native int Graphics_SetClipPath(final int p0, final int p1);
    
    public static final native int Graphics_SetClipPath(final int p0, final int p1, final int p2);
    
    public static final native int Graphics_SetCompositingQuality(final int p0, final int p1);
    
    public static final native int Graphics_SetPageUnit(final int p0, final int p1);
    
    public static final native int Graphics_SetPixelOffsetMode(final int p0, final int p1);
    
    public static final native int Graphics_SetSmoothingMode(final int p0, final int p1);
    
    public static final native int Graphics_SetTransform(final int p0, final int p1);
    
    public static final native int Graphics_SetInterpolationMode(final int p0, final int p1);
    
    public static final native int Graphics_SetTextRenderingHint(final int p0, final int p1);
    
    public static final native int Graphics_TranslateTransform(final int p0, final float p1, final float p2, final int p3);
    
    public static final native int GraphicsPath_new(final int p0);
    
    public static final native int GraphicsPath_new(final int[] p0, final byte[] p1, final int p2, final int p3);
    
    public static final native void GraphicsPath_delete(final int p0);
    
    public static final native int GraphicsPath_AddArc(final int p0, final float p1, final float p2, final float p3, final float p4, final float p5, final float p6);
    
    public static final native int GraphicsPath_AddBezier(final int p0, final float p1, final float p2, final float p3, final float p4, final float p5, final float p6, final float p7, final float p8);
    
    public static final native int GraphicsPath_AddLine(final int p0, final float p1, final float p2, final float p3, final float p4);
    
    public static final native int GraphicsPath_AddPath(final int p0, final int p1, final boolean p2);
    
    public static final native int GraphicsPath_AddRectangle(final int p0, final RectF p1);
    
    public static final native int GraphicsPath_AddString(final int p0, final char[] p1, final int p2, final int p3, final int p4, final float p5, final PointF p6, final int p7);
    
    public static final native int GraphicsPath_CloseFigure(final int p0);
    
    public static final native int GraphicsPath_Clone(final int p0);
    
    public static final native int GraphicsPath_Flatten(final int p0, final int p1, final float p2);
    
    public static final native int GraphicsPath_GetBounds(final int p0, final RectF p1, final int p2, final int p3);
    
    public static final native int GraphicsPath_GetLastPoint(final int p0, final PointF p1);
    
    public static final native int GraphicsPath_GetPathPoints(final int p0, final float[] p1, final int p2);
    
    public static final native int GraphicsPath_GetPathTypes(final int p0, final byte[] p1, final int p2);
    
    public static final native int GraphicsPath_GetPointCount(final int p0);
    
    public static final native boolean GraphicsPath_IsOutlineVisible(final int p0, final float p1, final float p2, final int p3, final int p4);
    
    public static final native boolean GraphicsPath_IsVisible(final int p0, final float p1, final float p2, final int p3);
    
    public static final native int GraphicsPath_SetFillMode(final int p0, final int p1);
    
    public static final native int GraphicsPath_StartFigure(final int p0);
    
    public static final native int GraphicsPath_Transform(final int p0, final int p1);
    
    public static final native int HatchBrush_new(final int p0, final int p1, final int p2);
    
    public static final native int Image_GetLastStatus(final int p0);
    
    public static final native int Image_GetPixelFormat(final int p0);
    
    public static final native int Image_GetWidth(final int p0);
    
    public static final native int Image_GetHeight(final int p0);
    
    public static final native int Image_GetPalette(final int p0, final int p1, final int p2);
    
    public static final native int Image_GetPaletteSize(final int p0);
    
    public static final native int ImageAttributes_new();
    
    public static final native void ImageAttributes_delete(final int p0);
    
    public static final native int ImageAttributes_SetWrapMode(final int p0, final int p1);
    
    public static final native int ImageAttributes_SetColorMatrix(final int p0, final float[] p1, final int p2, final int p3);
    
    public static final native void HatchBrush_delete(final int p0);
    
    public static final native int LinearGradientBrush_new(final PointF p0, final PointF p1, final int p2, final int p3);
    
    public static final native void LinearGradientBrush_delete(final int p0);
    
    public static final native int LinearGradientBrush_SetInterpolationColors(final int p0, final int[] p1, final float[] p2, final int p3);
    
    public static final native int LinearGradientBrush_SetWrapMode(final int p0, final int p1);
    
    public static final native int LinearGradientBrush_ResetTransform(final int p0);
    
    public static final native int LinearGradientBrush_ScaleTransform(final int p0, final float p1, final float p2, final int p3);
    
    public static final native int LinearGradientBrush_TranslateTransform(final int p0, final float p1, final float p2, final int p3);
    
    public static final native int Matrix_new(final float p0, final float p1, final float p2, final float p3, final float p4, final float p5);
    
    public static final native void Matrix_delete(final int p0);
    
    public static final native int Matrix_GetElements(final int p0, final float[] p1);
    
    public static final native int Matrix_Invert(final int p0);
    
    public static final native boolean Matrix_IsIdentity(final int p0);
    
    public static final native int Matrix_Multiply(final int p0, final int p1, final int p2);
    
    public static final native int Matrix_Rotate(final int p0, final float p1, final int p2);
    
    public static final native int Matrix_Scale(final int p0, final float p1, final float p2, final int p3);
    
    public static final native int Matrix_Shear(final int p0, final float p1, final float p2, final int p3);
    
    public static final native int Matrix_TransformPoints(final int p0, final PointF p1, final int p2);
    
    public static final native int Matrix_TransformPoints(final int p0, final float[] p1, final int p2);
    
    public static final native int Matrix_TransformVectors(final int p0, final PointF p1, final int p2);
    
    public static final native int Matrix_Translate(final int p0, final float p1, final float p2, final int p3);
    
    public static final native int Matrix_SetElements(final int p0, final float p1, final float p2, final float p3, final float p4, final float p5, final float p6);
    
    public static final native void MoveMemory(final ColorPalette p0, final int p1, final int p2);
    
    public static final native void MoveMemory(final BitmapData p0, final int p1);
    
    public static final native int PathGradientBrush_new(final int p0);
    
    public static final native void PathGradientBrush_delete(final int p0);
    
    public static final native int PathGradientBrush_SetCenterColor(final int p0, final int p1);
    
    public static final native int PathGradientBrush_SetCenterPoint(final int p0, final PointF p1);
    
    public static final native int PathGradientBrush_SetInterpolationColors(final int p0, final int[] p1, final float[] p2, final int p3);
    
    public static final native int PathGradientBrush_SetSurroundColors(final int p0, final int[] p1, final int[] p2);
    
    public static final native int PathGradientBrush_SetGraphicsPath(final int p0, final int p1);
    
    public static final native int PathGradientBrush_SetWrapMode(final int p0, final int p1);
    
    public static final native int Pen_new(final int p0, final float p1);
    
    public static final native void Pen_delete(final int p0);
    
    public static final native int Pen_GetBrush(final int p0);
    
    public static final native int Pen_SetBrush(final int p0, final int p1);
    
    public static final native int Pen_SetDashOffset(final int p0, final float p1);
    
    public static final native int Pen_SetDashPattern(final int p0, final float[] p1, final int p2);
    
    public static final native int Pen_SetDashStyle(final int p0, final int p1);
    
    public static final native int Pen_SetLineCap(final int p0, final int p1, final int p2, final int p3);
    
    public static final native int Pen_SetLineJoin(final int p0, final int p1);
    
    public static final native int Pen_SetMiterLimit(final int p0, final float p1);
    
    public static final native int Pen_SetWidth(final int p0, final float p1);
    
    public static final native int Point_new(final int p0, final int p1);
    
    public static final native void Point_delete(final int p0);
    
    public static final native int Region_new(final int p0);
    
    public static final native int Region_newGraphicsPath(final int p0);
    
    public static final native int Region_new();
    
    public static final native void Region_delete(final int p0);
    
    public static final native int Region_GetHRGN(final int p0, final int p1);
    
    public static final native boolean Region_IsInfinite(final int p0, final int p1);
    
    public static final native int SolidBrush_new(final int p0);
    
    public static final native void SolidBrush_delete(final int p0);
    
    public static final native void StringFormat_delete(final int p0);
    
    public static final native int StringFormat_Clone(final int p0);
    
    public static final native int StringFormat_GenericDefault();
    
    public static final native int StringFormat_GenericTypographic();
    
    public static final native int StringFormat_GetFormatFlags(final int p0);
    
    public static final native int StringFormat_SetHotkeyPrefix(final int p0, final int p1);
    
    public static final native int StringFormat_SetFormatFlags(final int p0, final int p1);
    
    public static final native int StringFormat_SetTabStops(final int p0, final float p1, final int p2, final float[] p3);
    
    public static final native int TextureBrush_new(final int p0, final int p1, final float p2, final float p3, final float p4, final float p5);
    
    public static final native void TextureBrush_delete(final int p0);
    
    public static final native int TextureBrush_SetTransform(final int p0, final int p1);
    
    public static final native int TextureBrush_ResetTransform(final int p0);
    
    public static final native int TextureBrush_ScaleTransform(final int p0, final float p1, final float p2, final int p3);
    
    public static final native int TextureBrush_TranslateTransform(final int p0, final float p1, final float p2, final int p3);
}

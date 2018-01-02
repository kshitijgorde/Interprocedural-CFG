// 
// Decompiled by Procyon v0.5.30
// 

public class C20
{
    public native int EndPage() throws Error;
    
    public native int CreatePen(final int p0, final int p1, final int p2) throws Error;
    
    public native int DeleteDC() throws Error;
    
    public native int CreateBrushIndirect(final int p0, final int p1) throws Error;
    
    public native int SetTextColor(final int p0) throws Error;
    
    public native int DeleteObject(final int p0) throws Error;
    
    public native int StartPage() throws Error;
    
    public native int SelectBrushObject() throws Error;
    
    public native int GetDeviceMode(final int p0) throws Error;
    
    public native int DeleteBrushObject() throws Error;
    
    public native int TextOut(final int p0, final int p1, final String p2, final int p3) throws Error;
    
    public native int SelectObject(final int p0) throws Error;
    
    public native int GetDeviceCaps(final int p0) throws Error;
    
    public native int SelectFontObject() throws Error;
    
    public native int CreatePen2(final int p0, final int p1, final int p2, final int p3) throws Error;
    
    public native int LogFont(final int p0, final String p1) throws Error;
    
    public native int EndDoc() throws Error;
    
    public native int DelObject(final int p0) throws Error;
    
    public native int SetBkMode(final int p0) throws Error;
    
    public native int CreateFontIndirect() throws Error;
    
    public native int GetCX() throws Error;
    
    public native boolean PrintDlg() throws Error;
    
    public native int StartDoc() throws Error;
    
    public native int GetCY() throws Error;
    
    public native int SetTextAlign(final int p0) throws Error;
    
    public native int Arc(final int p0, final int p1, final int p2, final int p3, final int p4, final int p5, final int p6, final int p7) throws Error;
    
    public native int DeleteFontObject() throws Error;
    
    public native boolean Polyline(final int[] p0, final int p1) throws Error;
    
    public native int SelObject(final int p0) throws Error;
    
    public native boolean GetTextExtentPoint32(final String p0, final int p1) throws Error;
    
    public native boolean Polygon(final int[] p0, final int p1) throws Error;
    
    public native boolean DocInfo() throws Error;
}

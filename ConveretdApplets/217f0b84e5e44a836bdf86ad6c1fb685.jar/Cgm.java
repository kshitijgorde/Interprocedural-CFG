import java.awt.Color;

// 
// Decompiled by Procyon v0.5.30
// 

abstract class Cgm
{
    String name;
    CgmViewApplet applet;
    double Width;
    double Height;
    double ax;
    double bx;
    double ay;
    double by;
    int EdgeWidthMode;
    int LineWidthMode;
    int MarkerSizeMode;
    boolean EdgesVisible;
    int EdgeType;
    int LineType;
    int MarkerType;
    double EdgeWidth;
    double LineWidth;
    double MarkerSize;
    int InteriorStyle;
    int HatchIndex;
    Color FillColor;
    Color EdgeColor;
    Color LineColor;
    Color MarkerColor;
    Color BackColor;
    Color TextColor;
    double CharacterHeight;
    double CharacterExpansion;
    double CharacterSpacing;
    int TextPath;
    double CharOri;
    double CharSlant;
    int FontIndex;
    int TextAlignVert;
    int TextAlignHor;
    
    Cgm() {
        this.name = "";
        this.ax = 1.0;
        this.bx = 0.0;
        this.ay = -1.0;
        this.by = 0.0;
        this.EdgeWidthMode = 0;
        this.LineWidthMode = 0;
        this.MarkerSizeMode = 0;
        this.EdgesVisible = false;
        this.EdgeType = 1;
        this.LineType = 1;
        this.MarkerType = 3;
        this.EdgeWidth = 1.0;
        this.LineWidth = 1.0;
        this.MarkerSize = 3.0;
        this.InteriorStyle = 1;
        this.HatchIndex = 1;
        this.CharacterHeight = 0.015;
        this.CharacterExpansion = 1.0;
        this.CharacterSpacing = 0.0;
        this.TextPath = 0;
        this.CharOri = 0.0;
        this.CharSlant = 0.0;
        this.FontIndex = 1;
        this.TextAlignVert = 0;
        this.TextAlignHor = 0;
    }
    
    abstract int replaceText(final int p0, final String p1);
    
    final void vdcExt(final double n, final double n2, final double n3, final double n4) {
        this.Width = n3 - n;
        this.Height = n4 - n2;
        this.ax = 1.0 / Math.max(this.Width, this.Height);
        this.ay = -this.ax;
        this.bx = -this.ax * n;
        this.by = -this.ay * n4;
    }
}

import java.awt.Image;
import java.awt.Font;
import java.awt.Color;

// 
// Decompiled by Procyon v0.5.30
// 

final class Look_n_Feel
{
    public Color ShapeColor;
    public Color ActiveShapeColor;
    public int ShapeType;
    public Color IconColor;
    public Color ActiveIconColor;
    public int IconType;
    public int VSpace;
    public Font font;
    public int texthi;
    public Image[] ShapeImage;
    public Image[] IconImage;
    
    public Look_n_Feel() {
        this.ShapeImage = new Image[4];
        this.IconImage = new Image[4];
    }
}

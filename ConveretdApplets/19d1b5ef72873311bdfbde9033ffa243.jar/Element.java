import java.awt.Graphics;
import java.awt.Color;

// 
// Decompiled by Procyon v0.5.30
// 

class Element
{
    protected int x;
    protected int y;
    int oldX;
    int oldY;
    protected int xInShape;
    protected int yInShape;
    protected int OriginalX;
    protected int OriginalY;
    protected int OriginalXInShape;
    protected int OriginalYInShape;
    protected Color clr;
    protected boolean ErasePossible;
    
    public Element(final int n, final int n2, final Color clr) {
        this.ErasePossible = false;
        this.OriginalXInShape = n;
        this.xInShape = n;
        this.OriginalYInShape = n2;
        this.yInShape = n2;
        final int n3 = n + 5 - 2;
        this.OriginalX = n3;
        this.x = n3;
        this.OriginalY = n2;
        this.y = n2;
        this.clr = clr;
    }
    
    public void Init() {
        this.ErasePossible = false;
        this.x = this.OriginalX;
        this.y = this.OriginalY;
        this.xInShape = this.OriginalXInShape;
        this.yInShape = this.OriginalYInShape;
    }
    
    public void hide(final Graphics graphics, final int n, final int n2) {
        if (this.ErasePossible) {
            final int n3 = 15;
            graphics.setColor(Color.black);
            graphics.fillRect(n + this.oldX * n3, n2 + this.oldY * n3, n3, n3);
            this.ErasePossible = false;
        }
    }
    
    public void Display(final Graphics graphics, final int n, final int n2) {
        final int n3 = 15;
        graphics.setColor(this.clr);
        graphics.fillRect(n + this.x * n3 + 1, n2 + this.y * n3 + 1, n3 - 2, n3 - 2);
        graphics.setColor(Color.white);
        graphics.drawRect(n + this.x * n3, n2 + this.y * n3, n3 - 1, n3 - 1);
        this.oldX = this.x;
        this.oldY = this.y;
        this.ErasePossible = true;
    }
    
    public void DisplayAbs(final Graphics graphics, final int n, final int n2) {
        final int n3 = 15;
        graphics.setColor(this.clr);
        graphics.fillRect(n + this.OriginalXInShape * n3 + 1, n2 + this.OriginalYInShape * n3 + 1, n3 - 2, n3 - 2);
        graphics.setColor(Color.white);
        graphics.drawRect(n + this.OriginalXInShape * n3, n2 + this.OriginalYInShape * n3, n3 - 1, n3 - 1);
    }
    
    public boolean CheckIfElementFits(final Color[][] array, int n, int n2, final boolean b) {
        if (b) {
            n += 3 - this.yInShape - this.xInShape;
            n2 += this.xInShape - this.yInShape;
        }
        return this.x + n >= 0 && this.x + n < 10 && this.y + n2 < 18 && array[this.x + n][this.y + n2] == Color.black;
    }
    
    public void ChangeElementPosition(int n, int n2, final boolean b) {
        if (b) {
            n += 3 - this.yInShape - this.xInShape;
            n2 += this.xInShape - this.yInShape;
            final int xInShape = this.xInShape;
            this.xInShape = 3 - this.yInShape;
            this.yInShape = xInShape;
        }
        this.x += n;
        this.y += n2;
    }
    
    public int GetXPos() {
        return this.x;
    }
    
    public int GetYPos() {
        return this.y;
    }
    
    public Color GetColor() {
        return this.clr;
    }
}

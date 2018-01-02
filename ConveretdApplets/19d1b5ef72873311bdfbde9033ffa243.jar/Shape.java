import java.awt.Graphics;
import java.awt.Color;
import java.util.Vector;

// 
// Decompiled by Procyon v0.5.30
// 

class Shape
{
    protected Vector Elements;
    protected int Value;
    protected boolean DrawReady;
    
    public Shape() {
        this.DrawReady = true;
        this.DrawReady = true;
    }
    
    public void Init() {
        this.DrawReady = true;
        for (int i = 0; i < this.Elements.size(); ++i) {
            ((Element)this.Elements.elementAt(i)).Init();
        }
    }
    
    public Shape(final int n, final int n2, final int n3, final int n4, final Color color, final int value) {
        this.DrawReady = true;
        this.Value = value;
        this.Elements = new Vector();
        this.AddElements(0, n, color);
        this.AddElements(1, n2, color);
        this.AddElements(2, n3, color);
        this.AddElements(3, n4, color);
        this.Init();
    }
    
    protected void AddElements(final int n, final int n2, final Color color) {
        if ((n2 & 0xF000) > 0) {
            this.Elements.addElement(new Element(0, n, color));
        }
        if ((n2 & 0xF00) > 0) {
            this.Elements.addElement(new Element(1, n, color));
        }
        if ((n2 & 0xF0) > 0) {
            this.Elements.addElement(new Element(2, n, color));
        }
        if ((n2 & 0xF) > 0) {
            this.Elements.addElement(new Element(3, n, color));
        }
    }
    
    public void hide(final Graphics graphics, final int n) {
        for (int i = 0; i < this.Elements.size(); ++i) {
            ((Element)this.Elements.elementAt(i)).hide(graphics, n, 0);
        }
    }
    
    public void Display(final Graphics graphics, final int n) {
        this.DrawReady = false;
        for (int i = 0; i < this.Elements.size(); ++i) {
            ((Element)this.Elements.elementAt(i)).Display(graphics, n, 0);
        }
        this.DrawReady = true;
    }
    
    public void DisplayAbs(final Graphics graphics, final int n, final int n2) {
        for (int i = 0; i < this.Elements.size(); ++i) {
            ((Element)this.Elements.elementAt(i)).DisplayAbs(graphics, n, n2);
        }
    }
    
    public boolean CheckIfShapeFits(final Color[][] array, final int n, final int n2, final boolean b) {
        for (int i = 0; i < this.Elements.size(); ++i) {
            if (!((Element)this.Elements.elementAt(i)).CheckIfElementFits(array, n, n2, b)) {
                return false;
            }
        }
        return true;
    }
    
    public void ChangePosition(final int n, final int n2, final boolean b) {
        for (int i = 0; i < this.Elements.size(); ++i) {
            ((Element)this.Elements.elementAt(i)).ChangeElementPosition(n, n2, b);
        }
    }
    
    public void PlaceInPlayField(final Color[][] array) {
        for (int i = 0; i < this.Elements.size(); ++i) {
            final Element element = this.Elements.elementAt(i);
            array[element.GetXPos()][element.GetYPos()] = element.GetColor();
        }
    }
    
    public int GetValue() {
        return this.Value;
    }
    
    public boolean IsReady() {
        return this.DrawReady;
    }
}

// 
// Decompiled by Procyon v0.5.30
// 

package classes.anaxee.gui.hand;

import java.awt.Stroke;
import java.awt.Color;

class ShapeDrawSettings
{
    private Color fillColor;
    private Color strokeColor;
    private Stroke stroke;
    
    public ShapeDrawSettings(final Color fillColor, final Stroke stroke, final Color strokeColor) {
        this.fillColor = fillColor;
        this.stroke = stroke;
        this.strokeColor = strokeColor;
    }
    
    public Color getFillColor() {
        return this.fillColor;
    }
    
    public Color getStrokeColor() {
        return this.strokeColor;
    }
    
    public Stroke getStroke() {
        return this.stroke;
    }
    
    public void setFillColor(final Color fillColor) {
        this.fillColor = fillColor;
    }
    
    public void setStrokeColor(final Color strokeColor) {
        this.strokeColor = strokeColor;
    }
    
    public void setStroke(final Stroke stroke) {
        this.stroke = stroke;
    }
}

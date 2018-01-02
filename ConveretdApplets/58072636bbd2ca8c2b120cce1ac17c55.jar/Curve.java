import java.awt.Color;

// 
// Decompiled by Procyon v0.5.30
// 

public class Curve
{
    private String expression;
    private boolean visible;
    private Color color;
    
    public Curve(final String expression, final Color color) {
        this.expression = expression;
        this.color = color;
        this.visible = true;
    }
    
    public void setExpression(final String expression) {
        this.expression = expression;
    }
    
    public void setColor(final Color color) {
        this.color = color;
    }
    
    public void setVisibility(final boolean visible) {
        this.visible = visible;
    }
    
    public String getExpression() {
        return this.expression;
    }
    
    public Color getColor() {
        return this.color;
    }
    
    public boolean isVisible() {
        return this.visible;
    }
}

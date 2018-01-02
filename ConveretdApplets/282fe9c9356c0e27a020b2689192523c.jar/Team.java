import java.awt.Color;

// 
// Decompiled by Procyon v0.5.30
// 

public class Team
{
    private String name;
    private Color color;
    private Color legcolor;
    private int[] positions;
    
    public Team(final String name, final Color color, final Color legcolor, final int[] positions) {
        this.setName(name);
        this.setColor(color);
        this.setLegColor(legcolor);
        this.setPositions(positions);
    }
    
    public Team() {
        this.setName("");
        this.setColor(null);
        this.setLegColor(null);
        this.setPositions(null);
    }
    
    public String getName() {
        return this.name;
    }
    
    public void setName(final String name) {
        this.name = name;
    }
    
    public Color getColor() {
        return this.color;
    }
    
    public void setColor(final Color color) {
        this.color = color;
    }
    
    public Color getLegColor() {
        return this.legcolor;
    }
    
    public void setLegColor(final Color legcolor) {
        this.legcolor = legcolor;
    }
    
    public int[] getPositions() {
        return this.positions;
    }
    
    public void setPositions(final int[] positions) {
        this.positions = positions;
    }
}

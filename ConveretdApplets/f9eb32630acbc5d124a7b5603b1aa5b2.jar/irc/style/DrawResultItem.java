// 
// Decompiled by Procyon v0.5.30
// 

package irc.style;

public class DrawResultItem
{
    public StyledRectangle rectangle;
    public String item;
    public String originalword;
    public String originalstrippedword;
    public DrawResult parent;
    
    public boolean equals(final Object o) {
        if (!(o instanceof DrawResultItem)) {
            return false;
        }
        final DrawResultItem drawResultItem = (DrawResultItem)o;
        return drawResultItem.rectangle.equals(this.rectangle) && drawResultItem.originalword.equals(this.originalword) && drawResultItem.parent.rectangle.equals(this.parent.rectangle);
    }
    
    public int hashCode() {
        return this.rectangle.hashCode() + this.originalword.hashCode();
    }
    
    public String toString() {
        return this.item + " at " + this.rectangle + " with parent at " + this.parent.rectangle;
    }
}

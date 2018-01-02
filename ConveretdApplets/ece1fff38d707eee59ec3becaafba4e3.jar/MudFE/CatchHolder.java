// 
// Decompiled by Procyon v0.5.30
// 

package MudFE;

class CatchHolder
{
    Attribute colour;
    DisplayInterface display;
    
    public Attribute getColour() {
        return this.colour;
    }
    
    public DisplayInterface getDisplay() {
        return this.display;
    }
    
    CatchHolder(final Attribute a, final DisplayInterface d) {
        this.colour = a;
        this.display = d;
    }
}

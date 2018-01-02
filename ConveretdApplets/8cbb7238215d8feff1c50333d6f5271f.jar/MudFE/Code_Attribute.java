// 
// Decompiled by Procyon v0.5.30
// 

package MudFE;

import java.awt.Color;

class Code_Attribute
{
    String description;
    Attribute attribute;
    
    public Attribute getAttribute() {
        return this.attribute;
    }
    
    public void setAttribute(final Attribute att) {
        this.attribute = att;
    }
    
    public Code_Attribute(final String d, final Color fg, final Color bg) {
        this.description = d;
        this.attribute = new Attribute(fg, bg);
    }
    
    public Code_Attribute(final Color fg, final Color bg) {
        this.description = null;
        this.attribute = new Attribute(fg, bg);
    }
    
    public Code_Attribute() {
        this.description = null;
        this.attribute = new Attribute();
    }
    
    public String getString() {
        return this.description;
    }
}

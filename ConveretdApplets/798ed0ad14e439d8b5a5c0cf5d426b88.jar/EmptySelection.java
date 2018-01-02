// 
// Decompiled by Procyon v0.5.30
// 

public class EmptySelection extends AbstractSelection
{
    EmptyBox Z;
    
    public EmptySelection(final EmptyBox z) {
        this.Z = z;
    }
    
    public final boolean buida() {
        return true;
    }
    
    public final boolean espotborrardirectament() {
        return true;
    }
    
    public final boolean espotsubstituir() {
        return true;
    }
    
    public final BoxPosition getCurpos() {
        return new BoxPosition(this.Z, 0);
    }
    
    public final Rectangles getRectangles(final BoxComponent boxComponent) {
        return new Rectangles(this.Z.getRectangleSeleccio(0, 0, boxComponent));
    }
    
    public final void script(final BoxScripting boxScripting) {
        this.Z.script(boxScripting);
    }
    
    public final BoxPosition suprimeix(final BoxComponent boxComponent) {
        super.esValida = false;
        return new BoxPosition(this.Z, 0);
    }
    
    public final AbstractBox getMama() {
        return this.Z;
    }
}

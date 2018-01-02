import gamelib.ActionBuffer;
import gamelib.OffComponent;

// 
// Decompiled by Procyon v0.5.30
// 

class Warhead extends OffComponent
{
    Warhead(final ActionBuffer actionBuffer) {
        super(actionBuffer);
    }
    
    public Object clone() {
        final Object o = null;
        try {
            super.clone();
        }
        catch (CloneNotSupportedException ex) {}
        return o;
    }
    
    void ignite(final int n, final int n2) {
        this.setPosition(n - super.bounds.width / 2, n2 - super.bounds.height / 2);
        this.setVisible(true);
        for (int i = 0; i < super.buffer.childs.size(); ++i) {
            if (super.buffer.childs.elementAt(i) instanceof Ground) {
                super.buffer.childs.removeElement(this);
                super.buffer.childs.insertElementAt(this, i);
                break;
            }
        }
        this.setActive(true);
    }
}

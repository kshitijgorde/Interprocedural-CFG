import java.util.Vector;

// 
// Decompiled by Procyon v0.5.30
// 

class CircuitNode
{
    int x;
    int y;
    Vector links;
    boolean internal;
    
    CircuitNode() {
        this.links = new Vector();
    }
}

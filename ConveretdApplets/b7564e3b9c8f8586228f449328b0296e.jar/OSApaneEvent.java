import java.util.EventObject;

// 
// Decompiled by Procyon v0.5.30
// 

public class OSApaneEvent extends EventObject
{
    public int L;
    public int j;
    public int g;
    public boolean gotPivot;
    public int planeType;
    public boolean tilt;
    
    public OSApaneEvent(final OSApane osApane) {
        super(osApane);
    }
    
    public OSApaneEvent(final OSApane osApane, final int l, final int j, final int g, final boolean gotPivot) {
        super(osApane);
        this.L = l;
        this.j = j;
        this.g = g;
        this.gotPivot = gotPivot;
    }
}

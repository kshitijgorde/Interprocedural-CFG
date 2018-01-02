// 
// Decompiled by Procyon v0.5.30
// 

package shout3d.core;

public class NavigationInfo extends Bindable
{
    public final BooleanField headlight;
    public final FloatField visibilityLimit;
    
    public NavigationInfo() {
        this(null);
    }
    
    public NavigationInfo(final Shout3DViewer shout3DViewer) {
        super(shout3DViewer);
        this.headlight = new BooleanField(this, "headlight", 0, true);
        this.visibilityLimit = new FloatField(this, "visibilityLimit", 15, 0.0f);
    }
}

// 
// Decompiled by Procyon v0.5.30
// 

package shout3d.core;

public class MouseInput extends DeviceInput
{
    public static final int DOWN = 0;
    public static final int UP = 1;
    public static final int MOVE = 2;
    public static final int DRAG = 3;
    public int which;
    public int x;
    public int y;
    public int button;
    
    public MouseInput() {
    }
    
    public MouseInput(final int n, final int n2, final int n3, final int n4, final int n5, final double n6) {
        this.a(n, n2, n3, n4, n5, n6);
    }
    
    public void a(final int which, final int x, final int y, final int button, final int modifiers, final double timeStamp) {
        this.which = which;
        this.x = x;
        this.y = y;
        this.button = button;
        super.modifiers = modifiers;
        super.timeStamp = timeStamp;
    }
}

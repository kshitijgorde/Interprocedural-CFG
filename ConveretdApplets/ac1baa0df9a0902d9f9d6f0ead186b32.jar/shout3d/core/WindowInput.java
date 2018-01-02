// 
// Decompiled by Procyon v0.5.30
// 

package shout3d.core;

public class WindowInput extends DeviceInput
{
    public static final int CLOSE = 0;
    public static final int ICONIFY = 1;
    public static final int DEICONIFY = 2;
    public int which;
    
    public WindowInput() {
    }
    
    public WindowInput(final int n, final int n2, final double n3) {
        this.a(n, n2, n3);
    }
    
    public void a(final int which, final int modifiers, final double timeStamp) {
        this.which = which;
        super.modifiers = modifiers;
        super.timeStamp = timeStamp;
    }
}

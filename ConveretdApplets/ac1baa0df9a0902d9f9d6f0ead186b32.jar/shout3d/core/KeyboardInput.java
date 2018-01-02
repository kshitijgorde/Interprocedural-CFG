// 
// Decompiled by Procyon v0.5.30
// 

package shout3d.core;

public class KeyboardInput extends DeviceInput
{
    public static final int PRESS = 0;
    public static final int RELEASE = 1;
    public static final int HOME = 1000;
    public static final int END = 1001;
    public static final int PGUP = 1002;
    public static final int PGDN = 1003;
    public static final int UP = 1004;
    public static final int DOWN = 1005;
    public static final int LEFT = 1006;
    public static final int RIGHT = 1007;
    public static final int F1 = 1008;
    public static final int F2 = 1009;
    public static final int F3 = 1010;
    public static final int F4 = 1011;
    public static final int F5 = 1012;
    public static final int F6 = 1013;
    public static final int F7 = 1014;
    public static final int F8 = 1015;
    public static final int F9 = 1016;
    public static final int F10 = 1017;
    public static final int F11 = 1018;
    public static final int F12 = 1019;
    public static final int PRINT_SCREEN = 1020;
    public static final int SCROLL_LOCK = 1021;
    public static final int CAPS_LOCK = 1022;
    public static final int NUM_LOCK = 1023;
    public static final int PAUSE = 1024;
    public static final int INSERT = 1025;
    public int which;
    public int key;
    
    public KeyboardInput() {
    }
    
    public KeyboardInput(final int n, final int n2, final int n3, final double n4) {
        this.a(n, n2, n3, n4);
    }
    
    public void a(final int which, final int key, final int modifiers, final double timeStamp) {
        this.which = which;
        this.key = key;
        super.modifiers = modifiers;
        super.timeStamp = timeStamp;
    }
}

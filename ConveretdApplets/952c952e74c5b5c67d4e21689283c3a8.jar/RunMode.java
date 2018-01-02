// 
// Decompiled by Procyon v0.5.30
// 

public class RunMode
{
    public static final int STEP = 0;
    public static final int SLOW = 1;
    public static final int FAST = 2;
    public static final int HALT = -1;
    private int runMode;
    
    public void setMode(final int runMode) {
        if (runMode < -1 || runMode > 2) {
            this.runMode = -1;
        }
        else {
            this.runMode = runMode;
        }
    }
    
    public int getMode() {
        return this.runMode;
    }
}

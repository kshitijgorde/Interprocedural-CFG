// 
// Decompiled by Procyon v0.5.30
// 

package jaclib.hardware_info;

public class HardwareInfo
{
    public static final native String[] getDXDiagSystemProps();
    
    public static final native String[] getOpenGLProps();
    
    public static final native String[][] getDXDiagDisplayDevicesProps();
    
    public static final native int[] getCPUInfo();
}

// 
// Decompiled by Procyon v0.5.30
// 

package jagdx;

import java.awt.Canvas;

public class D3DPRESENT_PARAMETERS
{
    public int MultiSampleType;
    public int Flags;
    public int PresentationInterval;
    public int FullScreen_RefreshRateInHz;
    public int BackBufferWidth;
    public boolean Windowed;
    public boolean EnableAutoDepthStencil;
    public int BackBufferFormat;
    public int BackBufferHeight;
    public Canvas DeviceWindow;
    public int BackBufferCount;
    public int AutoDepthStencilFormat;
    public int SwapEffect;
    public int MultiSampleQuality;
    
    public D3DPRESENT_PARAMETERS(final Canvas deviceWindow) {
        this.MultiSampleType = 0;
        this.BackBufferWidth = 0;
        this.PresentationInterval = 0;
        this.BackBufferCount = 0;
        this.BackBufferHeight = 0;
        this.MultiSampleQuality = 0;
        this.SwapEffect = 1;
        this.AutoDepthStencilFormat = 0;
        this.BackBufferFormat = 0;
        try {
            this.DeviceWindow = deviceWindow;
        }
        catch (RuntimeException ex) {
            throw ex;
        }
    }
}

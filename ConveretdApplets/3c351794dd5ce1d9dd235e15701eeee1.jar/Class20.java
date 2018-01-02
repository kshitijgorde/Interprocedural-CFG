import com.ms.win32.User32;
import com.ms.awt.WComponentPeer;
import com.ms.com.IUnknown;
import com.ms.directX.DDSurfaceDesc;
import java.awt.Component;
import java.awt.Frame;
import com.ms.directX.DirectDraw;
import com.ms.directX.IEnumModesCallback;

// 
// Decompiled by Procyon v0.5.30
// 

final class Class20 implements IEnumModesCallback
{
    private static int[] anIntArray3249;
    private static int anInt3250;
    private DirectDraw aDirectDraw3251;
    
    final int[] method252(final boolean b) {
        try {
            if (b) {
                Class20.anIntArray3249 = null;
            }
            this.aDirectDraw3251.enumDisplayModes(0, null, null, this);
            Class20.anIntArray3249 = new int[Class20.anInt3250];
            Class20.anInt3250 = 0;
            this.aDirectDraw3251.enumDisplayModes(0, null, null, this);
            final int[] anIntArray3249 = Class20.anIntArray3249;
            Class20.anInt3250 = 0;
            Class20.anIntArray3249 = null;
            return anIntArray3249;
        }
        catch (RuntimeException ex) {
            throw ex;
        }
    }
    
    final void method253(final int n, final Frame frame) {
        try {
            this.aDirectDraw3251.restoreDisplayMode();
            this.aDirectDraw3251.setCooperativeLevel(frame, 8);
        }
        catch (RuntimeException ex) {
            throw ex;
        }
    }
    
    public final void method254(final DDSurfaceDesc ddSurfaceDesc, final IUnknown unknown) {
        try {
            if (Class20.anIntArray3249 != null) {
                Class20.anIntArray3249[Class20.anInt3250++] = ddSurfaceDesc.width;
                Class20.anIntArray3249[Class20.anInt3250++] = ddSurfaceDesc.height;
                Class20.anIntArray3249[Class20.anInt3250++] = ddSurfaceDesc.rgbBitCount;
                Class20.anIntArray3249[Class20.anInt3250++] = ddSurfaceDesc.refreshRate;
            }
            else {
                Class20.anInt3250 += 4;
            }
        }
        catch (RuntimeException ex) {
            throw ex;
        }
    }
    
    final void method255(final int n, final int n2, final int n3, final int n4, final int n5, final Frame frame) {
        try {
            frame.setVisible(true);
            final int hwnd = ((WComponentPeer)frame.getPeer()).getHwnd();
            User32.SetWindowLong(hwnd, -16, n2);
            User32.SetWindowLong(hwnd, -20, 8);
            this.aDirectDraw3251.setCooperativeLevel(frame, 17);
            this.aDirectDraw3251.setDisplayMode(n3, n5, n, n4, 0);
            frame.setBounds(0, 0, n3, n5);
            frame.toFront();
            frame.requestFocus();
        }
        catch (RuntimeException ex) {
            throw ex;
        }
    }
    
    public Class20() {
        try {
            (this.aDirectDraw3251 = new DirectDraw()).initialize(null);
        }
        catch (RuntimeException ex) {
            throw ex;
        }
    }
}

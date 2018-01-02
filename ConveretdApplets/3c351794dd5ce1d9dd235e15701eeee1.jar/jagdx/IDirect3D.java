// 
// Decompiled by Procyon v0.5.30
// 

package jagdx;

import java.awt.Canvas;
import jaclib.peer.jaa;
import jaclib.peer.IUnknown;

public class IDirect3D extends IUnknown
{
    private jaa b;
    
    public final IDirect3DDevice a(final int n, final int n2, final Canvas canvas, final int n3, final D3DPRESENT_PARAMETERS d3DPRESENT_PARAMETERS) {
        IDirect3DDevice direct3DDevice2;
        try {
            final IDirect3DDevice direct3DDevice = new IDirect3DDevice(this.b);
            final int createDevice = this._CreateDevice(n, n2, canvas, n3, d3DPRESENT_PARAMETERS, direct3DDevice);
            if (kg.b(-7175, createDevice)) {
                throw new jc(String.valueOf(createDevice));
            }
            direct3DDevice2 = direct3DDevice;
        }
        catch (RuntimeException ex) {
            throw ex;
        }
        return direct3DDevice2;
    }
    
    public final native int CheckDeviceMultiSampleType(final int p0, final int p1, final int p2, final boolean p3, final int p4);
    
    public final D3DCAPS a(final int n, final int n2) {
        D3DCAPS d3DCAPS2;
        try {
            final D3DCAPS d3DCAPS = new D3DCAPS();
            final int getDeviceCaps = this._GetDeviceCaps(n, n2, d3DCAPS);
            if (kg.b(-7175, getDeviceCaps)) {
                throw new jc(String.valueOf(getDeviceCaps));
            }
            d3DCAPS2 = d3DCAPS;
        }
        catch (RuntimeException ex) {
            throw ex;
        }
        return d3DCAPS2;
    }
    
    public final D3DADAPTER_IDENTIFIER b(final int n, final int n2) {
        D3DADAPTER_IDENTIFIER d3DADAPTER_IDENTIFIER2;
        try {
            final D3DADAPTER_IDENTIFIER d3DADAPTER_IDENTIFIER = new D3DADAPTER_IDENTIFIER();
            final int getAdapterIdentifier = this._GetAdapterIdentifier(n, n2, d3DADAPTER_IDENTIFIER);
            if (kg.b(-7175, getAdapterIdentifier)) {
                throw new jc(String.valueOf(getAdapterIdentifier));
            }
            d3DADAPTER_IDENTIFIER2 = d3DADAPTER_IDENTIFIER;
        }
        catch (RuntimeException ex) {
            throw ex;
        }
        return d3DADAPTER_IDENTIFIER2;
    }
    
    public final native int CheckDepthStencilMatch(final int p0, final int p1, final int p2, final int p3, final int p4);
    
    private final native int _GetAdapterIdentifier(final int p0, final int p1, final D3DADAPTER_IDENTIFIER p2);
    
    public static final IDirect3D a(final int n, final jaa jaa) {
        IDirect3D direct3D2;
        try {
            final IDirect3D direct3D = new IDirect3D(jaa);
            final int direct3DCreate = _Direct3DCreate(n, direct3D);
            if (kg.b(-7175, direct3DCreate)) {
                throw new jc(String.valueOf(direct3DCreate));
            }
            direct3D2 = direct3D;
        }
        catch (RuntimeException ex) {
            throw ex;
        }
        return direct3D2;
    }
    
    private final native int _GetDeviceCaps(final int p0, final int p1, final D3DCAPS p2);
    
    public final native int CheckDeviceFormat(final int p0, final int p1, final int p2, final int p3, final int p4, final int p5);
    
    public final native int CheckDeviceType(final int p0, final int p1, final int p2, final int p3, final boolean p4);
    
    public final int a(final int n, final D3DDISPLAYMODE d3DDISPLAYMODE) {
        int getAdapterDisplayMode;
        try {
            getAdapterDisplayMode = this._GetAdapterDisplayMode(n, d3DDISPLAYMODE);
        }
        catch (RuntimeException ex) {
            throw ex;
        }
        return getAdapterDisplayMode;
    }
    
    private IDirect3D(final jaa b) {
        super(b);
        try {
            this.b = b;
        }
        catch (RuntimeException ex) {
            throw ex;
        }
    }
    
    private final native int _CreateDevice(final int p0, final int p1, final Canvas p2, final int p3, final D3DPRESENT_PARAMETERS p4, final IDirect3DDevice p5);
    
    private final native int _GetAdapterDisplayMode(final int p0, final D3DDISPLAYMODE p1);
    
    private static final native int _Direct3DCreate(final int p0, final IDirect3D p1);
}

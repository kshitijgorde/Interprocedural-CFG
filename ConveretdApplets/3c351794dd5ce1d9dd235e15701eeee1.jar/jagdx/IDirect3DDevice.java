// 
// Decompiled by Procyon v0.5.30
// 

package jagdx;

import jaclib.peer.jaa;
import jaclib.peer.IUnknown;

public final class IDirect3DDevice extends IUnknown
{
    private jaa b;
    private static float[] c;
    
    private final native int _CreateVertexDeclaration(final VertexElementCollection p0, final IDirect3DVertexDeclaration p1);
    
    public final native int SetViewport(final int p0, final int p1, final int p2, final int p3, final float p4, final float p5);
    
    public final native int _CreateTexture(final int p0, final int p1, final int p2, final int p3, final int p4, final int p5, final IDirect3DTexture p6);
    
    public final native int _CreateCubeTexture(final int p0, final int p1, final int p2, final int p3, final int p4, final IDirect3DCubeTexture p5);
    
    public final native int DrawIndexedPrimitive(final int p0, final int p1, final int p2, final int p3, final int p4, final int p5);
    
    private final native int _GetSwapChain(final int p0, final IDirect3DSwapChain p1);
    
    public final native int TestCooperativeLevel();
    
    private final native int _CreateIndexBuffer(final int p0, final int p1, final int p2, final int p3, final IDirect3DIndexBuffer p4);
    
    public final native int SetSamplerState(final int p0, final int p1, final int p2);
    
    public final native int EndScene();
    
    private final native int _CreateDepthStencilSurface(final int p0, final int p1, final int p2, final int p3, final int p4, final boolean p5, final IDirect3DSurface p6);
    
    public final native int SetVertexShader(final IDirect3DVertexShader p0);
    
    private final native int _GetDepthStencilSurface(final IDirect3DSurface p0);
    
    public final native int Clear(final int p0, final int p1, final float p2, final int p3);
    
    public final native int SetLight(final int p0, final D3DLIGHT p1);
    
    public final int a(final int n, final float n2, final float n3, final float n4, final float n5) {
        int setPixelShaderConstantF;
        try {
            IDirect3DDevice.c[0] = n2;
            IDirect3DDevice.c[1] = n3;
            IDirect3DDevice.c[3] = n5;
            IDirect3DDevice.c[2] = n4;
            setPixelShaderConstantF = this.SetPixelShaderConstantF(n, IDirect3DDevice.c, 1);
        }
        catch (RuntimeException ex) {
            throw ex;
        }
        return setPixelShaderConstantF;
    }
    
    public final IDirect3DSurface b() {
        IDirect3DSurface direct3DSurface2;
        try {
            final IDirect3DSurface direct3DSurface = new IDirect3DSurface(this.b);
            final int getDepthStencilSurface = this._GetDepthStencilSurface(direct3DSurface);
            if (kg.b(-7175, getDepthStencilSurface)) {
                throw new jc(String.valueOf(getDepthStencilSurface));
            }
            direct3DSurface2 = direct3DSurface;
        }
        catch (RuntimeException ex) {
            throw ex;
        }
        return direct3DSurface2;
    }
    
    public final native int Reset(final D3DPRESENT_PARAMETERS p0);
    
    private final native int _GetRenderTarget(final int p0, final IDirect3DSurface p1);
    
    private final native int SetRenderStatef(final int p0, final float p1);
    
    public final IDirect3DVertexDeclaration a(final VertexElementCollection collection, IDirect3DVertexDeclaration direct3DVertexDeclaration) {
        IDirect3DVertexDeclaration direct3DVertexDeclaration2;
        try {
            if (direct3DVertexDeclaration == null) {
                direct3DVertexDeclaration = new IDirect3DVertexDeclaration(this.b);
            }
            else {
                direct3DVertexDeclaration.b(57);
            }
            final int createVertexDeclaration = this._CreateVertexDeclaration(collection, direct3DVertexDeclaration);
            if (kg.b(-7175, createVertexDeclaration)) {
                throw new jc(String.valueOf(createVertexDeclaration));
            }
            direct3DVertexDeclaration2 = direct3DVertexDeclaration;
        }
        catch (RuntimeException ex) {
            throw ex;
        }
        return direct3DVertexDeclaration2;
    }
    
    public final native int SetVertexDeclaration(final IDirect3DVertexDeclaration p0);
    
    public final native int StretchRect(final IDirect3DSurface p0, final int p1, final int p2, final int p3, final int p4, final IDirect3DSurface p5, final int p6, final int p7, final int p8, final int p9, final int p10);
    
    private final native int _GetBackBuffer(final int p0, final int p1, final int p2, final IDirect3DSurface p3);
    
    public final int b(final int n, final float n2, final float n3, final float n4, final float n5) {
        int setVertexShaderConstantF;
        try {
            IDirect3DDevice.c[1] = n3;
            IDirect3DDevice.c[3] = n5;
            IDirect3DDevice.c[2] = n4;
            IDirect3DDevice.c[0] = n2;
            setVertexShaderConstantF = this.SetVertexShaderConstantF(n, IDirect3DDevice.c, 1);
        }
        catch (RuntimeException ex) {
            throw ex;
        }
        return setVertexShaderConstantF;
    }
    
    public final IDirect3DEventQuery c() {
        IDirect3DEventQuery direct3DEventQuery2;
        try {
            final IDirect3DEventQuery direct3DEventQuery = new IDirect3DEventQuery(this.b);
            if (kg.a(-21593, this._CreateEventQuery(direct3DEventQuery))) {
                if (!direct3DEventQuery.a(25759)) {
                    return null;
                }
                return direct3DEventQuery;
            }
            else {
                direct3DEventQuery2 = null;
            }
        }
        catch (RuntimeException ex) {
            throw ex;
        }
        return direct3DEventQuery2;
    }
    
    public final native int SetFVF(final int p0);
    
    public final native int SetVertexShaderConstantF(final int p0, final float[] p1, final int p2);
    
    private final native int SetRenderStateb(final int p0, final boolean p1);
    
    public final native int SetTextureStageState(final int p0, final int p1, final int p2);
    
    public final native int SetTransform(final int p0, final float[] p1);
    
    public final native int SetRenderState(final int p0, final int p1);
    
    public final native int _CreateVertexShader(final byte[] p0, final IDirect3DVertexShader p1);
    
    private final native int _CreateRenderTarget(final int p0, final int p1, final int p2, final int p3, final int p4, final boolean p5, final IDirect3DSurface p6);
    
    public final native int BeginScene();
    
    public final native int DrawPrimitive(final int p0, final int p1, final int p2);
    
    IDirect3DDevice(final jaa b) {
        super(b);
        try {
            this.b = b;
        }
        catch (RuntimeException ex) {
            throw ex;
        }
    }
    
    public final IDirect3DCubeTexture a(final int n, final int n2, final int n3, final int n4, final int n5) {
        IDirect3DCubeTexture direct3DCubeTexture2;
        try {
            final IDirect3DCubeTexture direct3DCubeTexture = new IDirect3DCubeTexture(this.b);
            final int createCubeTexture = this._CreateCubeTexture(n, n2, n3, n4, n5, direct3DCubeTexture);
            if (kg.b(-7175, createCubeTexture)) {
                throw new jc(String.valueOf(createCubeTexture));
            }
            direct3DCubeTexture2 = direct3DCubeTexture;
        }
        catch (RuntimeException ex) {
            throw ex;
        }
        return direct3DCubeTexture2;
    }
    
    public final int a(final int n, final boolean b) {
        int setRenderStateb;
        try {
            setRenderStateb = this.SetRenderStateb(n, b);
        }
        catch (RuntimeException ex) {
            throw ex;
        }
        return setRenderStateb;
    }
    
    public final IDirect3DVertexShader a(final byte[] array) {
        IDirect3DVertexShader direct3DVertexShader2;
        try {
            if (null == array) {
                return null;
            }
            final IDirect3DVertexShader direct3DVertexShader = new IDirect3DVertexShader(this.b);
            final int createVertexShader = this._CreateVertexShader(array, direct3DVertexShader);
            if (kg.b(-7175, createVertexShader)) {
                throw new jc(String.valueOf(createVertexShader));
            }
            direct3DVertexShader2 = direct3DVertexShader;
        }
        catch (RuntimeException ex) {
            throw ex;
        }
        return direct3DVertexShader2;
    }
    
    private final native int _CreateOffscreenPlainSurface(final int p0, final int p1, final int p2, final int p3, final IDirect3DSurface p4);
    
    public final native int SetPixelShaderConstantF(final int p0, final float[] p1, final int p2);
    
    public final native int SetTexture(final int p0, final IDirect3DBaseTexture p1);
    
    public final IDirect3DVertexBuffer a(final int b, final int n, final int n2, final int n3, IDirect3DVertexBuffer direct3DVertexBuffer) {
        IDirect3DVertexBuffer direct3DVertexBuffer2;
        try {
            if (null == direct3DVertexBuffer) {
                direct3DVertexBuffer = new IDirect3DVertexBuffer(this.b);
            }
            else {
                direct3DVertexBuffer.b(61);
            }
            final int createVertexBuffer = this._CreateVertexBuffer(b, n, n2, n3, direct3DVertexBuffer);
            if (kg.b(-7175, createVertexBuffer)) {
                throw new jc(String.valueOf(createVertexBuffer));
            }
            direct3DVertexBuffer.b = b;
            direct3DVertexBuffer2 = direct3DVertexBuffer;
        }
        catch (RuntimeException ex) {
            throw ex;
        }
        return direct3DVertexBuffer2;
    }
    
    public final native int _CreatePixelShader(final byte[] p0, final IDirect3DPixelShader p1);
    
    private final native int _CreateEventQuery(final IDirect3DEventQuery p0);
    
    public final IDirect3DIndexBuffer a(final int n, final int n2, final int n3, final int n4, IDirect3DIndexBuffer direct3DIndexBuffer) {
        IDirect3DIndexBuffer direct3DIndexBuffer2;
        try {
            if (null == direct3DIndexBuffer) {
                direct3DIndexBuffer = new IDirect3DIndexBuffer(this.b);
            }
            else {
                direct3DIndexBuffer.b(84);
            }
            final int createIndexBuffer = this._CreateIndexBuffer(n, n2, n3, n4, direct3DIndexBuffer);
            if (kg.b(-7175, createIndexBuffer)) {
                throw new jc(String.valueOf(createIndexBuffer));
            }
            direct3DIndexBuffer2 = direct3DIndexBuffer;
        }
        catch (RuntimeException ex) {
            throw ex;
        }
        return direct3DIndexBuffer2;
    }
    
    public final native int SetIndices(final IDirect3DIndexBuffer p0);
    
    public final IDirect3DSurface a(final int n, final int n2, final int n3, final int n4, final int n5, final boolean b) {
        IDirect3DSurface direct3DSurface2;
        try {
            final IDirect3DSurface direct3DSurface = new IDirect3DSurface(this.b);
            final int createRenderTarget = this._CreateRenderTarget(n, n2, n3, n4, n5, b, direct3DSurface);
            if (kg.b(-7175, createRenderTarget)) {
                throw new jc(String.valueOf(createRenderTarget));
            }
            direct3DSurface2 = direct3DSurface;
        }
        catch (RuntimeException ex) {
            throw ex;
        }
        return direct3DSurface2;
    }
    
    public final IDirect3DVolumeTexture a(final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7) {
        IDirect3DVolumeTexture direct3DVolumeTexture2;
        try {
            final IDirect3DVolumeTexture direct3DVolumeTexture = new IDirect3DVolumeTexture(this.b);
            final int createVolumeTexture = this._CreateVolumeTexture(n, n2, n3, n4, n5, n6, n7, direct3DVolumeTexture);
            if (kg.b(-7175, createVolumeTexture)) {
                throw new jc(String.valueOf(createVolumeTexture));
            }
            direct3DVolumeTexture2 = direct3DVolumeTexture;
        }
        catch (RuntimeException ex) {
            throw ex;
        }
        return direct3DVolumeTexture2;
    }
    
    public final native int _CreateVolumeTexture(final int p0, final int p1, final int p2, final int p3, final int p4, final int p5, final int p6, final IDirect3DVolumeTexture p7);
    
    public final native int SetPixelShader(final IDirect3DPixelShader p0);
    
    public final native int SetScissorRect(final int p0, final int p1, final int p2, final int p3);
    
    public final native boolean LightEnable(final int p0, final boolean p1);
    
    public final native int SetStreamSource(final int p0, final IDirect3DVertexBuffer p1, final int p2, final int p3);
    
    public final int a(final int n, final float n2) {
        int setRenderStatef;
        try {
            setRenderStatef = this.SetRenderStatef(n, n2);
        }
        catch (RuntimeException ex) {
            throw ex;
        }
        return setRenderStatef;
    }
    
    public final IDirect3DTexture a(final int n, final int n2, final int n3, final int n4, final int n5, final int n6) {
        IDirect3DTexture direct3DTexture2;
        try {
            final IDirect3DTexture direct3DTexture = new IDirect3DTexture(this.b);
            final int createTexture = this._CreateTexture(n, n2, n3, n4, n5, n6, direct3DTexture);
            if (kg.b(-7175, createTexture)) {
                throw new jc(String.valueOf(createTexture));
            }
            direct3DTexture2 = direct3DTexture;
        }
        catch (RuntimeException ex) {
            throw ex;
        }
        return direct3DTexture2;
    }
    
    private final native int _CreateVertexBuffer(final int p0, final int p1, final int p2, final int p3, final IDirect3DVertexBuffer p4);
    
    public final IDirect3DPixelShader b(final byte[] array) {
        IDirect3DPixelShader direct3DPixelShader2;
        try {
            if (array == null) {
                return null;
            }
            final IDirect3DPixelShader direct3DPixelShader = new IDirect3DPixelShader(this.b);
            final int createPixelShader = this._CreatePixelShader(array, direct3DPixelShader);
            if (kg.b(-7175, createPixelShader)) {
                throw new jc(String.valueOf(createPixelShader));
            }
            direct3DPixelShader2 = direct3DPixelShader;
        }
        catch (RuntimeException ex) {
            throw ex;
        }
        return direct3DPixelShader2;
    }
    
    public final IDirect3DSwapChain c(final int n) {
        IDirect3DSwapChain direct3DSwapChain2;
        try {
            final IDirect3DSwapChain direct3DSwapChain = new IDirect3DSwapChain(this.b);
            final int getSwapChain = this._GetSwapChain(n, direct3DSwapChain);
            if (kg.b(-7175, getSwapChain)) {
                throw new jc(String.valueOf(getSwapChain));
            }
            direct3DSwapChain2 = direct3DSwapChain;
        }
        catch (RuntimeException ex) {
            throw ex;
        }
        return direct3DSwapChain2;
    }
    
    public final IDirect3DSurface d(final int n) {
        IDirect3DSurface direct3DSurface2;
        try {
            final IDirect3DSurface direct3DSurface = new IDirect3DSurface(this.b);
            final int getRenderTarget = this._GetRenderTarget(n, direct3DSurface);
            if (kg.b(-7175, getRenderTarget)) {
                throw new jc(String.valueOf(getRenderTarget));
            }
            direct3DSurface2 = direct3DSurface;
        }
        catch (RuntimeException ex) {
            throw ex;
        }
        return direct3DSurface2;
    }
    
    public final int a(final int n, final float[] array) {
        int setVertexShaderConstantF;
        try {
            setVertexShaderConstantF = this.SetVertexShaderConstantF(n, array, array.length / 4);
        }
        catch (RuntimeException ex) {
            throw ex;
        }
        return setVertexShaderConstantF;
    }
    
    static {
        IDirect3DDevice.c = new float[4];
    }
}

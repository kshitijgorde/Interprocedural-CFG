// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.opengl;

import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.internal.opengl.win32.WGL;
import org.eclipse.swt.internal.win32.OS;
import org.eclipse.swt.internal.opengl.win32.PIXELFORMATDESCRIPTOR;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Canvas;

public class GLCanvas extends Canvas
{
    int context;
    int pixelFormat;
    static final String USE_OWNDC_KEY = "org.eclipse.swt.internal.win32.useOwnDC";
    
    public GLCanvas(final Composite composite, final int n, final GLData glData) {
        super(composite, checkStyle(composite, n));
        composite.getDisplay().setData("org.eclipse.swt.internal.win32.useOwnDC", new Boolean(false));
        if (glData == null) {
            SWT.error(4);
        }
        final PIXELFORMATDESCRIPTOR pixelformatdescriptor = new PIXELFORMATDESCRIPTOR();
        pixelformatdescriptor.nSize = 40;
        pixelformatdescriptor.nVersion = 1;
        pixelformatdescriptor.dwFlags = 36;
        pixelformatdescriptor.dwLayerMask = 0;
        pixelformatdescriptor.iPixelType = 0;
        if (glData.doubleBuffer) {
            final PIXELFORMATDESCRIPTOR pixelformatdescriptor2 = pixelformatdescriptor;
            pixelformatdescriptor2.dwFlags |= 0x1;
        }
        if (glData.stereo) {
            final PIXELFORMATDESCRIPTOR pixelformatdescriptor3 = pixelformatdescriptor;
            pixelformatdescriptor3.dwFlags |= 0x2;
        }
        pixelformatdescriptor.cRedBits = (byte)glData.redSize;
        pixelformatdescriptor.cGreenBits = (byte)glData.greenSize;
        pixelformatdescriptor.cBlueBits = (byte)glData.blueSize;
        pixelformatdescriptor.cAlphaBits = (byte)glData.alphaSize;
        pixelformatdescriptor.cDepthBits = (byte)glData.depthSize;
        pixelformatdescriptor.cStencilBits = (byte)glData.stencilSize;
        pixelformatdescriptor.cAccumRedBits = (byte)glData.accumRedSize;
        pixelformatdescriptor.cAccumGreenBits = (byte)glData.accumGreenSize;
        pixelformatdescriptor.cAccumBlueBits = (byte)glData.accumBlueSize;
        pixelformatdescriptor.cAccumAlphaBits = (byte)glData.accumAlphaSize;
        pixelformatdescriptor.cAccumBits = (byte)(pixelformatdescriptor.cAccumRedBits + pixelformatdescriptor.cAccumGreenBits + pixelformatdescriptor.cAccumBlueBits + pixelformatdescriptor.cAccumAlphaBits);
        final int getDC = OS.GetDC(this.handle);
        this.pixelFormat = WGL.ChoosePixelFormat(getDC, pixelformatdescriptor);
        if (this.pixelFormat == 0 || !WGL.SetPixelFormat(getDC, this.pixelFormat, pixelformatdescriptor)) {
            OS.ReleaseDC(this.handle, getDC);
            this.dispose();
            SWT.error(38);
        }
        this.context = WGL.wglCreateContext(getDC);
        if (this.context == 0) {
            OS.ReleaseDC(this.handle, getDC);
            SWT.error(2);
        }
        OS.ReleaseDC(this.handle, getDC);
        if (glData.shareContext != null) {
            WGL.wglShareLists(glData.shareContext.context, this.context);
        }
        this.addListener(12, new Listener() {
            public void handleEvent(final Event event) {
                switch (event.type) {
                    case 12: {
                        WGL.wglDeleteContext(GLCanvas.this.context);
                        break;
                    }
                }
            }
        });
    }
    
    static int checkStyle(final Composite composite, final int n) {
        if (composite != null && !OS.IsWinCE && OS.WIN32_VERSION >= OS.VERSION(6, 0)) {
            composite.getDisplay().setData("org.eclipse.swt.internal.win32.useOwnDC", new Boolean(true));
        }
        return n;
    }
    
    public GLData getGLData() {
        this.checkWidget();
        final GLData glData = new GLData();
        final PIXELFORMATDESCRIPTOR pixelformatdescriptor = new PIXELFORMATDESCRIPTOR();
        pixelformatdescriptor.nSize = 40;
        final int getDC = OS.GetDC(this.handle);
        WGL.DescribePixelFormat(getDC, this.pixelFormat, 40, pixelformatdescriptor);
        OS.ReleaseDC(this.handle, getDC);
        glData.doubleBuffer = ((pixelformatdescriptor.dwFlags & 0x1) != 0x0);
        glData.stereo = ((pixelformatdescriptor.dwFlags & 0x2) != 0x0);
        glData.redSize = pixelformatdescriptor.cRedBits;
        glData.greenSize = pixelformatdescriptor.cGreenBits;
        glData.blueSize = pixelformatdescriptor.cBlueBits;
        glData.alphaSize = pixelformatdescriptor.cAlphaBits;
        glData.depthSize = pixelformatdescriptor.cDepthBits;
        glData.stencilSize = pixelformatdescriptor.cStencilBits;
        glData.accumRedSize = pixelformatdescriptor.cAccumRedBits;
        glData.accumGreenSize = pixelformatdescriptor.cAccumGreenBits;
        glData.accumBlueSize = pixelformatdescriptor.cAccumBlueBits;
        glData.accumAlphaSize = pixelformatdescriptor.cAccumAlphaBits;
        return glData;
    }
    
    public boolean isCurrent() {
        this.checkWidget();
        return WGL.wglGetCurrentContext() == this.context;
    }
    
    public void setCurrent() {
        this.checkWidget();
        if (WGL.wglGetCurrentContext() == this.context) {
            return;
        }
        final int getDC = OS.GetDC(this.handle);
        WGL.wglMakeCurrent(getDC, this.context);
        OS.ReleaseDC(this.handle, getDC);
    }
    
    public void swapBuffers() {
        this.checkWidget();
        final int getDC = OS.GetDC(this.handle);
        WGL.SwapBuffers(getDC);
        OS.ReleaseDC(this.handle, getDC);
    }
}

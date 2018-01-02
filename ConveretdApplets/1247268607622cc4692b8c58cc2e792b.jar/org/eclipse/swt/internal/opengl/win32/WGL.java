// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.opengl.win32;

import org.eclipse.swt.internal.Library;
import org.eclipse.swt.internal.Platform;

public class WGL extends Platform
{
    public static final int WGL_FONT_LINES = 0;
    public static final int WGL_FONT_POLYGONS = 1;
    public static final int LPD_DOUBLEBUFFER = 1;
    public static final int LPD_STEREO = 2;
    public static final int LPD_SUPPORT_GDI = 16;
    public static final int LPD_SUPPORT_OPENGL = 32;
    public static final int LPD_SHARE_DEPTH = 64;
    public static final int LPD_SHARE_STENCIL = 128;
    public static final int LPD_SHARE_ACCUM = 256;
    public static final int LPD_SWAP_EXCHANGE = 512;
    public static final int LPD_SWAP_COPY = 1024;
    public static final int LPD_TRANSPARENT = 4096;
    public static final int LPD_TYPE_RGBA = 0;
    public static final int LPD_TYPE_COLORINDEX = 1;
    public static final int WGL_SWAP_MAIN_PLANE = 1;
    public static final int WGL_SWAP_OVERLAY1 = 2;
    public static final int WGL_SWAP_OVERLAY2 = 4;
    public static final int WGL_SWAP_OVERLAY3 = 8;
    public static final int WGL_SWAP_OVERLAY4 = 16;
    public static final int WGL_SWAP_OVERLAY5 = 32;
    public static final int WGL_SWAP_OVERLAY6 = 64;
    public static final int WGL_SWAP_OVERLAY7 = 128;
    public static final int WGL_SWAP_OVERLAY8 = 256;
    public static final int WGL_SWAP_OVERLAY9 = 512;
    public static final int WGL_SWAP_OVERLAY10 = 1024;
    public static final int WGL_SWAP_OVERLAY11 = 2048;
    public static final int WGL_SWAP_OVERLAY12 = 4096;
    public static final int WGL_SWAP_OVERLAY13 = 8192;
    public static final int WGL_SWAP_OVERLAY14 = 16384;
    public static final int WGL_SWAP_OVERLAY15 = 32768;
    public static final int WGL_SWAP_UNDERLAY1 = 65536;
    public static final int WGL_SWAP_UNDERLAY2 = 131072;
    public static final int WGL_SWAP_UNDERLAY3 = 262144;
    public static final int WGL_SWAP_UNDERLAY4 = 524288;
    public static final int WGL_SWAP_UNDERLAY5 = 1048576;
    public static final int WGL_SWAP_UNDERLAY6 = 2097152;
    public static final int WGL_SWAP_UNDERLAY7 = 4194304;
    public static final int WGL_SWAP_UNDERLAY8 = 8388608;
    public static final int WGL_SWAP_UNDERLAY9 = 16777216;
    public static final int WGL_SWAP_UNDERLAY10 = 33554432;
    public static final int WGL_SWAP_UNDERLAY11 = 67108864;
    public static final int WGL_SWAP_UNDERLAY12 = 134217728;
    public static final int WGL_SWAP_UNDERLAY13 = 268435456;
    public static final int WGL_SWAP_UNDERLAY14 = 536870912;
    public static final int WGL_SWAP_UNDERLAY15 = 1073741824;
    public static final int PFD_TYPE_RGBA = 0;
    public static final int PFD_TYPE_COLORINDEX = 1;
    public static final int PFD_MAIN_PLANE = 0;
    public static final int PFD_OVERLAY_PLANE = 1;
    public static final int PFD_UNDERLAY_PLANE = -1;
    public static final int PFD_DOUBLEBUFFER = 1;
    public static final int PFD_STEREO = 2;
    public static final int PFD_DRAW_TO_WINDOW = 4;
    public static final int PFD_DRAW_TO_BITMAP = 8;
    public static final int PFD_SUPPORT_GDI = 16;
    public static final int PFD_SUPPORT_OPENGL = 32;
    public static final int PFD_GENERIC_FORMAT = 64;
    public static final int PFD_NEED_PALETTE = 128;
    public static final int PFD_NEED_SYSTEM_PALETTE = 256;
    public static final int PFD_SWAP_EXCHANGE = 512;
    public static final int PFD_SWAP_COPY = 1024;
    public static final int PFD_SWAP_LAYER_BUFFERS = 2048;
    public static final int PFD_GENERIC_ACCELERATED = 4096;
    public static final int PFD_SUPPORT_DIRECTDRAW = 8192;
    public static final int PFD_DEPTH_DONTCARE = 536870912;
    public static final int PFD_DOUBLEBUFFER_DONTCARE = 1073741824;
    public static final int PFD_STEREO_DONTCARE = Integer.MIN_VALUE;
    
    static {
        Library.loadLibrary("swt-wgl");
    }
    
    public static final native int ChoosePixelFormat(final int p0, final PIXELFORMATDESCRIPTOR p1);
    
    public static final native int DescribePixelFormat(final int p0, final int p1, final int p2, final PIXELFORMATDESCRIPTOR p3);
    
    public static final native int GetPixelFormat(final int p0);
    
    public static final native boolean SetPixelFormat(final int p0, final int p1, final PIXELFORMATDESCRIPTOR p2);
    
    public static final native boolean SwapBuffers(final int p0);
    
    public static final native boolean wglCopyContext(final int p0, final int p1, final int p2);
    
    public static final native int wglCreateContext(final int p0);
    
    public static final native int wglCreateLayerContext(final int p0, final int p1);
    
    public static final native boolean wglDeleteContext(final int p0);
    
    public static final native int wglGetCurrentContext();
    
    public static final native int wglGetCurrentDC();
    
    public static final native int wglGetProcAddress(final byte[] p0);
    
    public static final native boolean wglMakeCurrent(final int p0, final int p1);
    
    public static final native boolean wglShareLists(final int p0, final int p1);
    
    public static final native boolean wglDescribeLayerPlane(final int p0, final int p1, final int p2, final int p3, final LAYERPLANEDESCRIPTOR p4);
    
    public static final native int wglSetLayerPaletteEntries(final int p0, final int p1, final int p2, final int p3, final int[] p4);
    
    public static final native int wglGetLayerPaletteEntries(final int p0, final int p1, final int p2, final int p3, final int[] p4);
    
    public static final native boolean wglRealizeLayerPalette(final int p0, final int p1, final boolean p2);
    
    public static final native boolean wglSwapLayerBuffers(final int p0, final int p1);
}

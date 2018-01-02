// 
// Decompiled by Procyon v0.5.30
// 

package ji.filter.uv;

import java.awt.Rectangle;
import ji.util.i;
import ji.filter.dll.n3;

public class jiUVDoc extends n3
{
    public jiUVDoc(final String s) {
        super(s);
    }
    
    protected final boolean l() {
        return false;
    }
    
    protected final boolean m() {
        return ji.util.i.c(5);
    }
    
    protected native boolean _openDoc(final long p0, final String p1, final boolean p2);
    
    protected native void _messageLoop();
    
    protected native long _createWindow();
    
    protected native long _getThreadId();
    
    protected native void _endMessageLoop(final long p0);
    
    protected native boolean _closeDoc(final long p0);
    
    protected native Rectangle _getMediaBox(final long p0, final int p1, final double p2);
    
    protected native void _beginDrawPage(final long p0);
    
    protected native void _endDrawPage(final long p0);
    
    protected native int _drawPageToMemory(final long p0, final int p1, final byte[] p2, final int p3, final int p4, final int p5, final int p6, final int p7, final int p8, final int p9, final int p10, final int p11, final double p12);
    
    protected native int _drawPageToCompressedMemory(final long p0, final int p1, final byte[] p2, final int p3, final int p4, final int p5, final int p6, final int p7, final double p8, final int p9);
    
    protected native int _getDrawTop(final long p0);
    
    protected native int _getDrawBottom(final long p0);
    
    protected native int _getCurrentPercent(final long p0);
    
    protected native boolean _lastPageFound(final long p0);
    
    protected native int _highestPageNum(final long p0);
    
    protected native boolean _processPage(final long p0, final int p1);
    
    protected native String _getDocTypeString(final long p0);
    
    protected native int _getDocTypeId(final long p0);
    
    protected native int _getDisplayEngineType(final long p0);
}

// 
// Decompiled by Procyon v0.5.30
// 

package ji.filter.filenetcold;

import java.awt.Rectangle;
import java.io.File;
import ji.io.h;
import ji.util.d;
import java.util.Vector;
import ji.util.i;
import ji.filter.dll.n3;

public class jiFilenetColdDoc extends n3
{
    public jiFilenetColdDoc(final String s) {
        super(s);
    }
    
    protected final boolean l() {
        return false;
    }
    
    protected final boolean m() {
        return ji.util.i.c(5);
    }
    
    public zo b(final int n) {
        zo zo = super.e.get("".concat(String.valueOf(String.valueOf(n))));
        if (zo == null) {
            zo = new zr(n, this);
            super.e.put("".concat(String.valueOf(String.valueOf(n))), zo);
        }
        return zo;
    }
    
    public boolean a(final String s, final String s2, final boolean b, final boolean b2) {
        final Vector<String> vector = new Vector<String>();
        vector.addElement(s);
        vector.addElement(s2);
        return this.a(vector, b2);
    }
    
    public boolean a(final long n, final Vector vector, final boolean b) {
        final String e = ji.util.i.e(1);
        if (!ji.util.d.by(e)) {
            boolean setProperties = false;
            try {
                setProperties = this._setProperties(n, e);
            }
            catch (Error error) {
                ji.util.d.a(error);
            }
            if (!setProperties) {
                ji.io.h.d(super.a, "Could not set the properties for the External Filenet COLD module");
            }
        }
        String absolutePath;
        try {
            absolutePath = new File(vector.elementAt(0)).getAbsolutePath();
        }
        catch (Exception ex) {
            ji.util.d.a(ex);
            absolutePath = "";
        }
        String absolutePath2;
        try {
            absolutePath2 = vector.elementAt(1);
            if (!ji.util.d.by(absolutePath2)) {
                absolutePath2 = new File(absolutePath2).getAbsolutePath();
            }
        }
        catch (Exception ex2) {
            ji.util.d.a(ex2);
            absolutePath2 = "";
        }
        if (ji.util.i.c(5)) {
            ji.io.h.d(super.a, String.valueOf(String.valueOf(new StringBuffer("COLD DLL openDoc call on ").append(absolutePath).append(", ").append(absolutePath2))));
        }
        return this._openDoc(n, absolutePath, absolutePath2, b);
    }
    
    protected int _getDisplayEngineType(final long n) {
        return 2;
    }
    
    protected native boolean _setProperties(final long p0, final String p1);
    
    protected native boolean _openDoc(final long p0, final String p1, final boolean p2);
    
    protected native boolean _openDoc(final long p0, final String p1, final String p2, final boolean p3);
    
    protected native void _messageLoop();
    
    protected native long _createWindow();
    
    protected native long _getThreadId();
    
    protected native void _endMessageLoop(final long p0);
    
    protected native boolean _closeDoc(final long p0);
    
    protected native double _getImageResolution(final long p0, final int p1);
    
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
    
    public class zr extends zo
    {
        protected zr(final int n, final n3 n2) {
            super(n, n2);
        }
        
        public final Rectangle b(final double n) {
            final Rectangle a = this.a(n);
            if (a != null) {
                return new Rectangle(a);
            }
            return null;
        }
        
        public final double g() {
            return jiFilenetColdDoc.this._getImageResolution(jiFilenetColdDoc.this.f, super.b);
        }
    }
}

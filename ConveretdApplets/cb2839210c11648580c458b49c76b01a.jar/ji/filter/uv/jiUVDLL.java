// 
// Decompiled by Procyon v0.5.30
// 

package ji.filter.uv;

import ji.ext.n;

public class jiUVDLL implements n
{
    protected native int ViewerVersion(final boolean p0, final int p1);
    
    protected native int libraryVersionFileId(final int p0);
    
    protected native int libraryVersionViewingTechnology(final int p0);
    
    protected native boolean _initFileIDLib();
    
    protected native void _deInitFileIDLib();
    
    protected native long _getFileID(final String p0, final boolean p1);
    
    public boolean a(final Object[] array) throws Exception {
        return true;
    }
    
    public boolean h(final String s) throws Exception {
        return true;
    }
}

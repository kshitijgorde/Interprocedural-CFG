// 
// Decompiled by Procyon v0.5.30
// 

package ji.filter.output;

import java.util.Properties;
import ji.image.cy;
import ji.document.ad;
import ji.v1event.af;
import ji.io.ac;

public interface ga
{
    void releaseResources();
    
    boolean supportsMultiPage();
    
    boolean saveEnd(final ac p0, final af p1, final ad p2, final String p3, final boolean[] p4) throws Exception;
    
    boolean saveStart(final int p0, final boolean[] p1) throws Exception;
    
    boolean saveImage(final ac p0, final ac p1, final String p2, final String p3, final cy p4, final ad p5, final boolean p6, final boolean p7, final int p8, final String p9, final int p10, final int p11, final int p12, final int p13, final boolean p14, final int p15, final int p16, final af p17, final boolean p18, final Object p19, final boolean p20, final boolean p21, final int p22, final int p23) throws Exception;
    
    void setParentId(final String p0);
    
    void setImageOutputParams(final Properties p0);
}

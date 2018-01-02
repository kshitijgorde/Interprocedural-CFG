// 
// Decompiled by Procyon v0.5.30
// 

package ji.filter.pdf;

import ji.burn.jiBurnerListener;
import ji.image.dx;
import ji.annotate.df;
import ji.document.ad;

public interface f8
{
    void setParent(final ad p0, final String p1);
    
    void setLogMessagePrepend(final String p0);
    
    boolean burn(final String p0, final String p1, final df p2, final dx p3, final jiBurnerListener p4, final String p5, final int p6, final int p7) throws Exception;
    
    String getErrorMessage();
}

// 
// Decompiled by Procyon v0.5.30
// 

package ji.filter;

import java.io.OutputStream;
import ji.document.gm;
import ji.document.ap;
import ji.document.es;
import ji.v1event.af;
import ji.annotate.df;
import ji.document.ad;
import ji.io.ac;
import ji.image.dx;

public interface ck
{
    dx a(final fh p0, final boolean p1) throws Exception;
    
    void b(final fh p0, final boolean p1) throws Exception;
    
    void fillDibInternal(final fh p0) throws Exception;
    
    int[] getPalette(final ac p0, final dx p1, final String p2) throws Exception;
    
    void abort(final dx p0);
    
    boolean isAborted(final dx p0, final String p1);
    
    void clearAbort(final dx p0, final String p1);
    
    boolean d();
    
    void close(final dx p0, final ad p1);
    
    df a(final ac p0, final df p1, final dx p2, final af p3, final ad p4, final String p5, final int p6, final boolean p7, final int p8) throws Exception;
    
    void a(final String p0);
    
    boolean e();
    
    boolean f();
    
    String getFilterName();
    
    boolean supportsResolutionChange();
    
    boolean a();
    
    void a(final es p0, final ad p1, final ap p2, final dx p3, final String p4, final int p5) throws Exception;
    
    int getResolution();
    
    void setResolution(final int p0);
    
    boolean getAutoLimitResolution();
    
    void setAutoLimitResolution(final boolean p0);
    
    boolean isDefinitelySupportedMimeType(final String p0, final ad p1);
    
    int isFileType(final ac p0, final String p1, final String p2, final ad p3, final boolean p4, final String p5, final String p6, final af p7, final boolean p8);
    
    boolean b(final String p0);
    
    String c(final String p0);
    
    boolean d(final String p0);
    
    String b();
    
    boolean a(final ad p0);
    
    void a(final dx p0, final dx p1);
    
    boolean isAttachmentAvailable();
    
    gm[] getAttachmentData() throws Exception;
    
    void streamAttachment(final gm p0, final OutputStream p1) throws Exception;
}

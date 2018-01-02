// 
// Decompiled by Procyon v0.5.30
// 

package ji.filter.pdf;

import java.awt.Component;
import ji.document.ap;
import ji.document.es;
import ji.annotate.df;
import ji.io.ac;
import ji.image.dx;
import ji.filter.fh;
import ji.document.ad;

public interface ha
{
    void setParent(final String p0, final ad p1);
    
    void setPDFLibraryVersion(final int p0);
    
    dx processHeader(final fh p0, final String p1, final double p2) throws Exception;
    
    int[] getPalette(final ac p0, final dx p1) throws Exception;
    
    void fillDib(final fh p0, final String p1) throws Exception;
    
    void cleanUp() throws Exception;
    
    df getAnnotations(final String p0, final dx p1, final ad p2, final int p3) throws Exception;
    
    void find(final es p0, final ap p1, final dx p2, final String p3, final int p4) throws Exception;
    
    void abort() throws Exception;
    
    void removeDecoder(final Component p0, final String p1);
    
    boolean isAborted();
    
    void clearAbort();
    
    boolean isCancelled();
    
    boolean isInvalidPassword();
    
    String getModuleError();
    
    int getModuleErrorType();
    
    void resetFlags();
    
    void closePDFL();
    
    void closePDFLDoc();
    
    boolean isForceRemoval();
}

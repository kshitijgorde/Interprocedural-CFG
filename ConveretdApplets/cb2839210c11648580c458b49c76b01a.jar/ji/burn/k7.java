// 
// Decompiled by Procyon v0.5.30
// 

package ji.burn;

import ji.filter.output.jiUnknownImageWriterException;
import java.util.Properties;

public interface k7
{
    void setExtendedLogging(final boolean p0);
    
    void setBurnPDFToPDF(final boolean p0);
    
    void setBurnAnyFileFormat(final boolean p0);
    
    void setIgnoreInvalidAnnotations(final boolean p0);
    
    void releaseResources();
    
    String burnAnnotations(final String p0, final int p1, final int p2, final String p3) throws jiBurnFailedException, Exception;
    
    int getDocumentPageCount();
    
    String burnAnnotations(final String p0, final String p1) throws jiBurnFailedException, Exception;
    
    String burnAnnotations(final String[] p0, final String p1) throws jiBurnFailedException, Exception;
    
    String getParentId();
    
    String getLastErrorText();
    
    void setBurnListener(final jiBurnerListener p0);
    
    void setOutputFormat(final String p0, final Properties p1) throws jiUnknownImageWriterException;
    
    void setHighMemTrigger(final long p0);
}

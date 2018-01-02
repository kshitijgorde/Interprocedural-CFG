// 
// Decompiled by Procyon v0.5.30
// 

package ji.util;

import java.awt.Image;
import java.awt.Dimension;
import ji.v1event.af;
import java.awt.Component;

public interface jiPrinti
{
    void load(final Component p0, final af p1, final String p2);
    
    boolean isLoaded();
    
    boolean getPrintJob(final String p0, final int p1, final int p2, final boolean p3, final int p4, final boolean p5, final String p6, final boolean p7, final String p8, final int p9, final int p10, final boolean p11);
    
    void setDebug(final boolean p0);
    
    void setMessages(final boolean p0);
    
    void initTopWindow();
    
    void reverseMonoColors();
    
    void forgetPrintSettings();
    
    int getFromPage();
    
    int getToPage();
    
    boolean isTopDown();
    
    Dimension getPageDimension();
    
    int getCopies();
    
    int getXResolution();
    
    int getYResolution();
    
    int getLineHeight(final String p0, final Component p1);
    
    int getPageLineHeight();
    
    boolean startDoc(final String p0, final boolean p1);
    
    boolean endDoc();
    
    boolean abortDoc();
    
    boolean startPage(final String p0, final String p1, final Component p2);
    
    void startPageTrailer(final String p0, final String p1, final Component p2);
    
    void setEndOfLastImage(final int p0);
    
    int getEndOfLastImage();
    
    boolean endPage();
    
    void drawImage(final Object p0, final Image p1, final int p2, final int p3, final int p4, final int p5, final int p6, final int p7, final boolean p8, final boolean p9, final int p10, final boolean p11, final int p12, final boolean p13);
    
    void drawImage(final Object p0, final int[] p1, final int p2, final int p3, final int p4, final int p5, final int p6, final int p7, final boolean p8, final boolean p9, final int p10, final boolean p11, final int p12, final boolean p13);
    
    void drawMonoImage(final Object p0, final byte[] p1, final int p2, final int p3, final int p4, final int p5, final int p6, final int p7, final byte p8, final byte p9, final byte p10, final byte p11, final boolean p12, final boolean p13, final boolean p14, final int p15, final boolean p16, final int p17, final boolean p18);
    
    void drawString(final String p0, final int p1, final Component p2, final boolean p3, final boolean p4);
    
    void setOriginalSize(final boolean p0, final double p1, final double p2);
    
    void releaseResources();
    
    void releasePrintingResources();
    
    boolean isImageLargerThanPage(final double p0, final double p1, final int p2, final int p3);
    
    void setImagesPerPage(final int p0);
    
    void printNoteAnnotationPageText(final Component p0, final int p1, final String[] p2, final String[] p3, final String p4, final int p5);
    
    void endNoteAnnotationPages();
    
    void startNoteAnnotationPages(final Component p0, final String p1);
}

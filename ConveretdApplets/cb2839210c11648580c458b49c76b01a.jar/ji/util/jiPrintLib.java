// 
// Decompiled by Procyon v0.5.30
// 

package ji.util;

import java.awt.image.ImageProducer;
import ji.render.c1;
import java.awt.Image;
import java.awt.Dimension;
import ji.sec.f;
import ji.io.ac;
import ji.res.z;
import ji.v1event.af;
import java.awt.Component;
import v1com.daeja.v1.v1base.jiP86;
import ji.io.q;
import ji.ext.f4;

public class jiPrintLib extends f4 implements jiPrinti
{
    private static final String strSourceLibName = "jip86.dll";
    private static final String strDestName = "jip86.dll";
    private q fileCache;
    private static String strLibFileName;
    private static boolean booLibraryTested;
    private static boolean booLoaded;
    private static boolean pDebug;
    private static boolean pMessages;
    private jiP86 p86;
    private int maxBandSize;
    private static final long maxMemPerBand = 2097152L;
    private int[] rgb;
    private String parentId;
    
    public jiPrintLib() {
        this.fileCache = null;
        this.p86 = new jiP86();
        this.maxBandSize = 65;
        this.rgb = null;
        this.parentId = null;
    }
    
    public void load(final Component component, final af af, final String parentId) {
        this.parentId = parentId;
        if (!jiPrintLib.booLibraryTested && d.a0(parentId)) {
            try {
                e.a(true, 759, null, null);
                final byte[] a = z.a(component, "jip86.dll", d.b("jip86.dll", d.bh("jip86.dll"), "v1"), af, null, parentId, new y());
                if (this.fileCache == null) {
                    this.fileCache = q.a(component, parentId);
                }
                if (d.eg() && d.av(parentId)) {
                    final String property = System.getProperty("file.separator");
                    jiPrintLib.strLibFileName = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(d.a1(parentId)))).append(property).append("java").append(property).append("bin").append(property).append("jip86.dll")));
                    try {
                        ac.c(jiPrintLib.strLibFileName, parentId);
                    }
                    catch (Exception ex2) {}
                }
                else {
                    jiPrintLib.strLibFileName = this.fileCache.o();
                }
                try {
                    final ac ac = new ac(jiPrintLib.strLibFileName, true, false, 0, component, parentId);
                    ac.b(a);
                    ac.a(component);
                }
                catch (Exception ex) {
                    ex.printStackTrace();
                }
                f.a(jiPrintLib.strLibFileName, component, parentId, this.fileCache.o());
                jiPrintLib.booLoaded = true;
            }
            catch (Throwable t) {
                t.printStackTrace();
            }
            finally {
                e.a(false, 0, af, component);
            }
        }
        try {
            if (jiPrintLib.booLoaded) {
                this.p86.load();
                jiPrintLib.booLoaded = this.p86.isLoaded();
            }
        }
        catch (Throwable t2) {
            jiPrintLib.booLoaded = false;
            t2.printStackTrace();
        }
        jiPrintLib.booLibraryTested = true;
    }
    
    public void setTested(final boolean booLibraryTested) {
        jiPrintLib.booLibraryTested = booLibraryTested;
    }
    
    public boolean isTested() {
        return jiPrintLib.booLibraryTested;
    }
    
    public void setLoaded(final boolean booLoaded) {
        jiPrintLib.booLoaded = booLoaded;
    }
    
    public void setFileCache(final q fileCache) {
        this.fileCache = fileCache;
    }
    
    public boolean isFileCacheSet() {
        return this.fileCache != null;
    }
    
    public q getFileCache() {
        return this.fileCache;
    }
    
    public void setLibFileName(final String strLibFileName, final String s) {
        try {
            if (strLibFileName != null) {
                jiPrintLib.strLibFileName = ac.b(strLibFileName, s);
            }
            else {
                jiPrintLib.strLibFileName = null;
            }
        }
        catch (Exception ex) {
            jiPrintLib.strLibFileName = strLibFileName;
        }
    }
    
    public String getLibFileName(final String s) {
        return jiPrintLib.strLibFileName;
    }
    
    public boolean isLoaded() {
        return jiPrintLib.booLoaded;
    }
    
    public final void initTopWindow() {
        this.p86.initTopWindow();
    }
    
    public final void reverseMonoColors() {
        this.p86.reverseMonoColors();
    }
    
    public final void forgetPrintSettings() {
        this.p86.forgetPrintSettings();
    }
    
    public final void setDebug(final boolean b) {
        jiPrintLib.pDebug = b;
        this.p86.setDebug(b);
    }
    
    public final void setMessages(final boolean b) {
        jiPrintLib.pMessages = b;
        this.p86.setMessages(b);
    }
    
    public final boolean isNetscape6() {
        boolean b = false;
        try {
            final String property = System.getProperty("mozilla.workaround");
            if (property != null && property.toLowerCase().indexOf("true") >= 0) {
                b = true;
            }
        }
        catch (Exception ex) {}
        return b;
    }
    
    public boolean getPrintJob(final String s, final int n, final int n2, final boolean b, final int n3, final boolean b2, final String s2, final boolean b3, final String printer, final int n4, final int n5, final boolean b4) {
        if (printer != null) {
            this.p86.setPrinter(printer);
        }
        return this.p86.getPrintJob(s, n, n2, b, n3, b2, s2, b3, n4, n5, b4);
    }
    
    public boolean isTopDown() {
        return this.p86.isTopDown();
    }
    
    public int getCopies() {
        return this.p86.getCopies();
    }
    
    public int getToPage() {
        return this.p86.getToPage();
    }
    
    public int getFromPage() {
        return this.p86.getFromPage();
    }
    
    public Dimension getPageDimension() {
        return this.p86.getPageDimension();
    }
    
    public int getXResolution() {
        return this.p86.getXResolution();
    }
    
    public int getYResolution() {
        return this.p86.getYResolution();
    }
    
    public int getPageLineHeight() {
        return this.p86.getPageLineHeight();
    }
    
    public int getLineHeight(final String s, final Component component) {
        return this.p86.getLineHeight(s, component);
    }
    
    public void setEndOfLastImage(final int endOfLastImage) {
        this.p86.setEndOfLastImage(endOfLastImage);
    }
    
    public void setImagesPerPage(final int imagesPerPage) {
        this.p86.setImagesPerPage(imagesPerPage);
    }
    
    public int getEndOfLastImage() {
        return this.p86.getEndOfLastImage();
    }
    
    public boolean startDoc(final String s, final boolean b) {
        return this.p86.startDoc(s, b);
    }
    
    public boolean endDoc() {
        this.releasePrintingResources();
        return this.p86.endDoc();
    }
    
    public boolean abortDoc() {
        return this.p86.abortDoc();
    }
    
    public boolean startPage(final String s, final String s2, final Component component) {
        return this.p86.startPage(s, s2, component, i.c(43));
    }
    
    public void startPageTrailer(final String s, final String s2, final Component component) {
        this.p86.startPageTrailer(s, s2, component, i.c(43));
    }
    
    public boolean endPage() {
        return this.p86.endPage();
    }
    
    public void drawImage(final Object o, final Image image, final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final boolean b, final boolean b2, final int n7, final boolean b3, final int n8, final boolean b4) {
        final ImageProducer source = image.getSource();
        if (source instanceof c1) {
            this.drawImage(o, (c1)source, n, n2, n3, n4, n5, n6, b, b2, n7, b3, n8, b4);
        }
        else {
            this.drawImageImage(o, image, n, n2, n3, n4, n5, n6, b, b2, n7, b3, n8, b4);
        }
    }
    
    private void drawImage(final Object o, final c1 c1, final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final boolean b, final boolean b2, final int n7, final boolean b3, final int n8, final boolean b4) {
        if (jiPrintLib.booLoaded) {
            try {
                boolean b5 = false;
                int n9;
                if (b4) {
                    n9 = 2097152 / (n5 * 4);
                }
                else {
                    n9 = 2097152 / n5;
                }
                if (n4 > this.maxBandSize) {
                    b5 = true;
                }
                c1.g();
                if (b5) {
                    if (this.rgb == null) {
                        this.rgb = new int[n3 * n9];
                    }
                    else if (this.rgb.length < n3 * n9) {
                        this.rgb = new int[n3 * n9];
                    }
                    int i = 0;
                    while (i < n4) {
                        c1.a(0, i, n3, n9, this.rgb);
                        this.drawImage(this.rgb, n9 * n3, n, n2 + i, n3, n9, n5, n6, b, b2, n7, b3, n8, b4);
                        i += n9 - 1;
                        if (n9 + i >= n4) {
                            n9 = n4 - i + 1;
                        }
                    }
                }
                else {
                    if (this.rgb == null) {
                        this.rgb = new int[n3 * n4];
                    }
                    else if (this.rgb.length < n3 * n4) {
                        this.rgb = new int[n3 * n4];
                    }
                    c1.a(0, 0, n3, n4, this.rgb);
                    this.drawImage(this.rgb, n3 * n4, n, n2, n3, n4, n5, n6, b, b2, n7, b3, n8, b4);
                }
                c1.f();
            }
            catch (Throwable t) {
                t.printStackTrace();
            }
        }
    }
    
    private void drawImageImage(final Object o, final Image image, final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final boolean b, final boolean b2, final int n7, final boolean b3, final int n8, final boolean b4) {
        if (jiPrintLib.booLoaded) {
            try {
                boolean b5 = false;
                int n9;
                if (b4) {
                    n9 = 2097152 / (n5 * 4);
                }
                else {
                    n9 = 2097152 / n5;
                }
                if (n4 > n9) {
                    b5 = true;
                }
                final c3 a = d.a(image, this.parentId, o);
                if (b5) {
                    int n10 = n9;
                    if (this.rgb == null) {
                        this.rgb = new int[n3 * n10];
                    }
                    else if (this.rgb.length < n3 * n10) {
                        this.rgb = new int[n3 * n10];
                    }
                    int n11 = 0;
                    final int n12 = 1;
                    while (n11 + n12 < n4) {
                        d.a(o, this.parentId, image, a, 0, n11, n3, n10, n3, this.rgb);
                        this.drawImage(this.rgb, n10 * n3, n, n2 + n11, n3, n10, n5, n6, b, b2, n7, b3, n8, b4);
                        n11 += n10 - n12;
                        if (n10 + n11 >= n4) {
                            n10 = n4 - n11;
                            if (n10 <= n9) {
                                continue;
                            }
                            n10 = n9;
                        }
                    }
                }
                else {
                    if (this.rgb == null) {
                        this.rgb = new int[n3 * n4];
                    }
                    else if (this.rgb.length < n3 * n4) {
                        this.rgb = new int[n3 * n4];
                    }
                    d.a(o, this.parentId, image, a, 0, 0, n3, n4, n3, this.rgb);
                    this.drawImage(this.rgb, n3 * n4, n, n2, n3, n4, n5, n6, b, b2, n7, b3, n8, b4);
                }
                d.a(image, a);
            }
            catch (Throwable t) {
                t.printStackTrace();
            }
        }
    }
    
    private void drawImage(final int[] array, final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7, final boolean b, final boolean b2, final int n8, final boolean b3, final int n9, final boolean b4) {
        this.p86.drawImage(array, n, n2, n3, n4, n5, n6, n7, b, b2, n8, b3, n9, b4);
    }
    
    public void drawImage(final Object o, final int[] array, final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final boolean b, final boolean b2, final int n7, final boolean b3, final int n8, final boolean b4) {
        this.p86.drawImage(array, array.length, n, n2, n3, n4, n5, n6, b, b2, n7, b3, n8, b4);
    }
    
    public void drawMonoImage(final Object o, final byte[] array, final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final byte b, final byte b2, final byte b3, final byte b4, final boolean b5, final boolean b6, final boolean b7, final int n7, final boolean b8, final int n8, final boolean b9) {
        this.p86.drawMonoImage(array, n, n2, n3, n4, n5, n6, b, b2, b3, b4, b5, b6, b7, n7, b8, n8, b9);
    }
    
    public void drawString(final String s, final int n, final Component component, final boolean b, final boolean b2) {
        try {
            this.p86.drawString(s, n, component, b, b2);
        }
        catch (Throwable t) {}
    }
    
    public void setOriginalSize(final boolean b, final double n, final double n2) {
        try {
            this.p86.setOriginalSize(b, n, n2);
        }
        catch (Throwable t) {}
    }
    
    public boolean isImageLargerThanPage(final double n, final double n2, final int n3, final int n4) {
        try {
            return this.p86.isImageLargerThanPage(n, n2, n3, n4);
        }
        catch (Throwable t) {
            t.printStackTrace();
            return false;
        }
    }
    
    public void printNoteAnnotationPageText(final Component component, final int n, final String[] array, final String[] array2, final String s, final int n2) {
        try {
            this.p86.printNoteAnnotationPageText(component, n, array, array2, s, n2);
        }
        catch (Throwable t) {
            d.a(t);
        }
    }
    
    public void endNoteAnnotationPages() {
        try {
            this.p86.endNoteAnnotationPages();
        }
        catch (Throwable t) {
            d.a(t);
        }
    }
    
    public void startNoteAnnotationPages(final Component component, final String s) {
        try {
            this.p86.startNoteAnnotationPages(component, s);
        }
        catch (Throwable t) {
            d.a(t);
        }
    }
    
    public void releasePrintingResources() {
        this.rgb = null;
        if (this.p86 != null) {
            this.p86.releasePrintingResources();
        }
    }
    
    public void releaseResources() {
        try {
            this.releasePrintingResources();
            this.p86.releaseResources();
            this.p86 = null;
        }
        catch (Throwable t) {
            d.a(t);
        }
    }
    
    public void testLibrary(final Object[] array) throws Exception {
        this.p86.load();
        jiPrintLib.booLoaded = this.p86.isLoaded();
    }
    
    static {
        jiPrintLib.strLibFileName = null;
        jiPrintLib.booLibraryTested = false;
        jiPrintLib.booLoaded = false;
        jiPrintLib.pDebug = false;
        jiPrintLib.pMessages = true;
    }
}

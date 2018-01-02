// 
// Decompiled by Procyon v0.5.30
// 

package ji.document;

import ji.util.fn;
import java.awt.Rectangle;
import java.net.URL;

public class cl
{
    public int a;
    public int b;
    public int c;
    public String d;
    public String e;
    public URL f;
    public String g;
    public URL h;
    public Object[] i;
    public Object[] j;
    public boolean k;
    public Object[] l;
    public Object[] m;
    public Object n;
    public Rectangle o;
    public boolean p;
    public double q;
    public Object r;
    public int s;
    public int t;
    public String[] u;
    public boolean v;
    private tu w;
    private boolean x;
    private boolean y;
    private boolean z;
    private boolean aa;
    private boolean ab;
    private int ac;
    private String ad;
    private Object ae;
    private long af;
    private Throwable ag;
    
    public String toString() {
        String s = "UNKNOWN";
        switch (this.a) {
            case 0: {
                s = "cmdClose";
                break;
            }
            case 1: {
                s = "cmdView";
                break;
            }
            case 2: {
                s = "cmdScale";
                break;
            }
            case 3: {
                s = "cmdOpen1";
                break;
            }
            case 4: {
                s = "cmdOpen2";
                break;
            }
            case 5: {
                s = "cmdOpen3";
                break;
            }
            case 6: {
                s = "cmdOpen4";
                break;
            }
            case 7: {
                s = "cmdOpen5";
                break;
            }
            case 8: {
                s = "cmdOpen6";
                break;
            }
            case 78: {
                s = "cmdOpen7";
                break;
            }
            case 79: {
                s = "cmdOpen8";
                break;
            }
            case 9: {
                s = "cmdPage";
                break;
            }
            case 10: {
                s = "cmdInvert";
                break;
            }
            case 12: {
                s = "cmdFlip";
                break;
            }
            case 13: {
                s = "cmdRotation";
                break;
            }
            case 14: {
                s = "cmdAreaZoom";
                break;
            }
            case 15: {
                s = "cmdStatusBar";
                break;
            }
            case 16: {
                s = "cmdMagnifier";
                break;
            }
            case 17: {
                s = "cmdMagFactor";
                break;
            }
            case 18: {
                s = "cmdZoomIn";
                break;
            }
            case 19: {
                s = "cmdZoomOut";
                break;
            }
            case 20: {
                s = "cmdFileButtons";
                break;
            }
            case 21: {
                s = "cmdPrintButtons";
                break;
            }
            case 22: {
                s = "cmdImageButtons";
                break;
            }
            case 23: {
                s = "cmdInvertButtons";
                break;
            }
            case 24: {
                s = "cmdTopButtons";
                break;
            }
            case 25: {
                s = "cmdViewButtons";
                break;
            }
            case 26: {
                s = "cmdNW1Buttons";
                break;
            }
            case 27: {
                s = "cmdNW2Buttons";
                break;
            }
            case 28: {
                s = "cmdViewAndTopButtons";
                break;
            }
            case 29: {
                s = "cmdAllButtons";
                break;
            }
            case 30: {
                s = "cmdPageButtons";
                break;
            }
            case 31: {
                s = "cmdPrintDoc";
                break;
            }
            case 32: {
                s = "cmdPrintPage";
                break;
            }
            case 33: {
                s = "cmdPrintSelected";
                break;
            }
            case 34: {
                s = "cmdPrintRange";
                break;
            }
            case 35: {
                s = "cmdPrintVisible";
                break;
            }
            case 36: {
                s = "cmdBrightness";
                break;
            }
            case 37: {
                s = "cmdResetBrightness";
                break;
            }
            case 38: {
                s = "cmdContrast";
                break;
            }
            case 39: {
                s = "cmdResetContrast";
                break;
            }
            case 40: {
                s = "cmdLuminance";
                break;
            }
            case 41: {
                s = "cmdResetLuminance";
                break;
            }
            case 42: {
                s = "cmdWakeUp";
                break;
            }
            case 43: {
                s = "cmdLabels";
                break;
            }
            case 44: {
                s = "cmdAppletInit";
                break;
            }
            case 45: {
                s = "cmdAppletStart";
                break;
            }
            case 46: {
                s = "cmdAppletStop";
                break;
            }
            case 47: {
                s = "cmdAppletDestroy";
                break;
            }
            case 48: {
                s = "cmdDocButtons";
                break;
            }
            case 49: {
                s = "cmdMagnifierInternal";
                break;
            }
            case 52: {
                s = "cmdAnnotations";
                break;
            }
            case 53: {
                s = "cmdAnnotationsSaveHandler";
                break;
            }
            case 54: {
                s = "cmdShowControls";
                break;
            }
            case 55: {
                s = "cmdAnnotEditAllowed";
                break;
            }
            case 57: {
                s = "cmdZoomArea";
                break;
            }
            case 58: {
                s = "cmdMagnifierNoPrefs";
                break;
            }
            case 59: {
                s = "cmdAddAnnotation";
                break;
            }
            case 60: {
                s = "cmdDeleteAnnotation";
                break;
            }
            case 61: {
                s = "cmdDeleteAllAnnotations";
                break;
            }
            case 62: {
                s = "cmdModifyAnnotation";
                break;
            }
            case 63: {
                s = "cmdAnnotationToolbar";
                break;
            }
            case 64: {
                s = "cmdSelectAnnotation";
                break;
            }
            case 65: {
                s = "cmdZoomAndScale";
                break;
            }
            case 66: {
                s = "cmdStates";
                break;
            }
            case 67: {
                s = "cmdPrintTransformed";
                break;
            }
            case 68: {
                s = "cmdSeparatorAnnotations";
                break;
            }
            case 69: {
                s = "cmdAnnotationsTemplate";
                break;
            }
            case 70: {
                s = "cmdThumbsDisplayMode";
                break;
            }
            case 71: {
                s = "cmdDocumentIndex";
                break;
            }
            case 90: {
                s = "cmdDocumentNameIndex";
                break;
            }
            case 72: {
                s = "cmdDocumentIndexFile";
                break;
            }
            case 91: {
                s = "cmdDocumentNameIndexFile";
                break;
            }
            case 73: {
                s = "cmdSeparatorList";
                break;
            }
            case 74: {
                s = "cmdGotoPage";
                break;
            }
            case 75: {
                s = "cmdReloadAnnotations";
                break;
            }
            case 81: {
                s = "cmdSaveDocument";
                break;
            }
            case 82: {
                s = "cmdBackImageEnabled";
                break;
            }
            case 83: {
                s = "cmdBackImages";
                break;
            }
            case 84: {
                s = "cmdBackImage";
                break;
            }
            case 86: {
                s = "cmdRotationForPages";
                break;
            }
            case 89: {
                s = "cmdStartAnnotWithProperties";
                break;
            }
            case 93: {
                s = "cmdNewWindow";
                break;
            }
            case 94: {
                s = "cmdShowAnnots";
                break;
            }
            case 96: {
                s = "cmdCopyClipboard";
                break;
            }
            case 97: {
                s = "cmdAddDocumentIndexItem";
                break;
            }
            case 98: {
                s = "cmdRemoveDocumentIndexItem";
                break;
            }
            case 99: {
                s = "cmdScrollImage";
                break;
            }
            case 100: {
                s = "cmdZoomImage";
                break;
            }
            case 101: {
                s = "cmdViewMode";
                break;
            }
            case 102: {
                s = "cmdScale2";
                break;
            }
            case 103: {
                s = "cmdZoom1002";
                break;
            }
            case 104: {
                s = "cmdZoomIn2";
                break;
            }
            case 105: {
                s = "cmdZoomOut2";
                break;
            }
            case 107: {
                s = "cmdRotateAll2";
                break;
            }
            case 108: {
                s = "cmdFlip2";
                break;
            }
            case 109: {
                s = "cmdFlipVAll2";
                break;
            }
            case 110: {
                s = "cmdFlipHAll2";
                break;
            }
            case 111: {
                s = "cmdInvert2";
                break;
            }
            case 112: {
                s = "cmdInvertAll2";
                break;
            }
            case 113: {
                s = "cmdZoomArea2";
                break;
            }
            case 114: {
                s = "cmdPage2";
                break;
            }
            case 115: {
                s = "cmdMag2";
                break;
            }
            case 116: {
                s = "cmdMagUpdate2";
                break;
            }
            case 117: {
                s = "cmdMagAdjust2";
                break;
            }
            case 118: {
                s = "cmdThumbsMode";
                break;
            }
            case 119: {
                s = "cmdLinkEndScroll";
                break;
            }
            case 120: {
                s = "cmdSetThumbsDocArray";
                break;
            }
            case 121: {
                s = "cmdLinkStartScroll";
                break;
            }
            case 122: {
                s = "cmdLinkPrefs";
                break;
            }
            case 123: {
                s = "cmdEnhanceStateAndMode";
                break;
            }
            case 124: {}
            case 125: {}
            case 126: {
                s = "cmdDeleteAllAnnotationsJavascript";
                break;
            }
            case 129: {
                s = "cmdModifyAnnotationNoRedraw";
                break;
            }
            case 130: {
                s = "cmdRedraw";
                break;
            }
            case 131: {
                s = "cmdFindDialog";
                break;
            }
            case 132: {
                s = "cmdPerformFind";
                break;
            }
            case 133: {
                s = "cmdClearTempAnnotResources";
                break;
            }
            case 134: {
                s = "cmdReleaseUtilResources";
                break;
            }
            case 135: {
                s = "cmdJavascriptGetAnnotationOnLabel";
                break;
            }
            case 136: {
                s = "cmdRender";
                break;
            }
            case 137: {
                s = "cmdOpenDirectory";
                break;
            }
            case 145: {
                s = "cmdSaveAttachments";
                break;
            }
            case 139: {
                s = "cmdLinkShowAnnotations";
                break;
            }
            case 141: {
                s = "cmdAppendSearchResults";
                break;
            }
            case 142: {
                s = "cmdSetSearchPercentage";
                break;
            }
            case 143: {
                s = "cmdUpdateThumbs";
                break;
            }
            case 144: {
                s = "cmdRefreshPageCount";
                break;
            }
            case 146: {
                s = "cmdBulkJS";
                break;
            }
            case 147: {
                s = "cmdBulkJSEnd";
                break;
            }
        }
        return String.valueOf(String.valueOf(new StringBuffer("jiCommand(").append(s).append(")")));
    }
    
    public final void a() {
        try {
            boolean b = true;
            synchronized (this) {
                if (this.aa) {
                    b = false;
                }
            }
            if (b) {
                synchronized (this.w) {
                    if (!this.w.a) {
                        this.w.wait(120000L);
                    }
                }
                // monitorexit(this.w)
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public final void b() {
        try {
            synchronized (this) {
                this.aa = true;
            }
            synchronized (this.w) {
                this.w.a = true;
                this.w.notifyAll();
            }
            // monitorexit(this.w)
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public cl c() {
        final cl cl = new cl(this.a);
        cl.b = this.b;
        cl.c = this.c;
        cl.d = this.d;
        cl.e = this.e;
        cl.f = this.f;
        cl.g = this.g;
        cl.h = this.h;
        cl.i = this.i;
        cl.j = this.j;
        cl.k = this.k;
        cl.l = this.l;
        cl.m = this.m;
        cl.n = this.n;
        cl.o = this.o;
        cl.p = this.p;
        cl.q = this.q;
        cl.r = this.r;
        cl.s = this.s;
        cl.t = this.t;
        cl.u = this.u;
        return cl;
    }
    
    public cl(final int a) {
        this.w = new tu((ad7)null);
        this.x = false;
        this.y = false;
        this.z = false;
        this.aa = false;
        this.ab = false;
        this.ac = -1;
        this.ad = null;
        this.ae = null;
        this.af = -1L;
        this.ag = null;
        this.a = a;
    }
    
    public cl(final int a, final int b) {
        this.w = new tu((ad7)null);
        this.x = false;
        this.y = false;
        this.z = false;
        this.aa = false;
        this.ab = false;
        this.ac = -1;
        this.ad = null;
        this.ae = null;
        this.af = -1L;
        this.ag = null;
        this.a = a;
        this.b = b;
    }
    
    public cl(final int a, final int b, final int c, final int s, final int t) {
        this.w = new tu((ad7)null);
        this.x = false;
        this.y = false;
        this.z = false;
        this.aa = false;
        this.ab = false;
        this.ac = -1;
        this.ad = null;
        this.ae = null;
        this.af = -1L;
        this.ag = null;
        this.a = a;
        this.b = b;
        this.c = c;
        this.s = s;
        this.t = t;
    }
    
    public cl(final int a, final double q) {
        this.w = new tu((ad7)null);
        this.x = false;
        this.y = false;
        this.z = false;
        this.aa = false;
        this.ab = false;
        this.ac = -1;
        this.ad = null;
        this.ae = null;
        this.af = -1L;
        this.ag = null;
        this.a = a;
        this.q = q;
    }
    
    public cl(final int a, final int b, final int c, final double q) {
        this.w = new tu((ad7)null);
        this.x = false;
        this.y = false;
        this.z = false;
        this.aa = false;
        this.ab = false;
        this.ac = -1;
        this.ad = null;
        this.ae = null;
        this.af = -1L;
        this.ag = null;
        this.a = a;
        this.b = b;
        this.c = c;
        this.q = q;
    }
    
    public cl(final int a, final int b, final boolean k) {
        this.w = new tu((ad7)null);
        this.x = false;
        this.y = false;
        this.z = false;
        this.aa = false;
        this.ab = false;
        this.ac = -1;
        this.ad = null;
        this.ae = null;
        this.af = -1L;
        this.ag = null;
        this.a = a;
        this.b = b;
        this.k = k;
    }
    
    public cl(final int a, final String d, final boolean k) {
        this.w = new tu((ad7)null);
        this.x = false;
        this.y = false;
        this.z = false;
        this.aa = false;
        this.ab = false;
        this.ac = -1;
        this.ad = null;
        this.ae = null;
        this.af = -1L;
        this.ag = null;
        this.a = a;
        this.d = d;
        this.k = k;
    }
    
    public cl(final int a, final Object n) {
        this.w = new tu((ad7)null);
        this.x = false;
        this.y = false;
        this.z = false;
        this.aa = false;
        this.ab = false;
        this.ac = -1;
        this.ad = null;
        this.ae = null;
        this.af = -1L;
        this.ag = null;
        this.a = a;
        this.n = n;
    }
    
    public cl(final int a, final URL f, final boolean k) {
        this.w = new tu((ad7)null);
        this.x = false;
        this.y = false;
        this.z = false;
        this.aa = false;
        this.ab = false;
        this.ac = -1;
        this.ad = null;
        this.ae = null;
        this.af = -1L;
        this.ag = null;
        this.a = a;
        this.f = f;
        this.k = k;
    }
    
    public cl(final int a, final URL f, final String d) {
        this.w = new tu((ad7)null);
        this.x = false;
        this.y = false;
        this.z = false;
        this.aa = false;
        this.ab = false;
        this.ac = -1;
        this.ad = null;
        this.ae = null;
        this.af = -1L;
        this.ag = null;
        this.a = a;
        this.f = f;
        this.d = d;
    }
    
    public cl(final int a, final Rectangle rectangle, final boolean k, final int b) {
        this.w = new tu((ad7)null);
        this.x = false;
        this.y = false;
        this.z = false;
        this.aa = false;
        this.ab = false;
        this.ac = -1;
        this.ad = null;
        this.ae = null;
        this.af = -1L;
        this.ag = null;
        this.a = a;
        this.o = new Rectangle(rectangle);
        this.k = k;
        this.b = b;
    }
    
    public cl(final int a, final boolean k) {
        this.w = new tu((ad7)null);
        this.x = false;
        this.y = false;
        this.z = false;
        this.aa = false;
        this.ab = false;
        this.ac = -1;
        this.ad = null;
        this.ae = null;
        this.af = -1L;
        this.ag = null;
        this.a = a;
        this.k = k;
    }
    
    public cl(final int a, final boolean k, final boolean p3) {
        this.w = new tu((ad7)null);
        this.x = false;
        this.y = false;
        this.z = false;
        this.aa = false;
        this.ab = false;
        this.ac = -1;
        this.ad = null;
        this.ae = null;
        this.af = -1L;
        this.ag = null;
        this.a = a;
        this.k = k;
        this.p = p3;
    }
    
    public cl(final int a, final int b, final int c) {
        this.w = new tu((ad7)null);
        this.x = false;
        this.y = false;
        this.z = false;
        this.aa = false;
        this.ab = false;
        this.ac = -1;
        this.ad = null;
        this.ae = null;
        this.af = -1L;
        this.ag = null;
        this.a = a;
        this.b = b;
        this.c = c;
    }
    
    public cl(final int a, final String d, final int b) {
        this.w = new tu((ad7)null);
        this.x = false;
        this.y = false;
        this.z = false;
        this.aa = false;
        this.ab = false;
        this.ac = -1;
        this.ad = null;
        this.ae = null;
        this.af = -1L;
        this.ag = null;
        this.a = a;
        this.b = b;
        this.d = d;
    }
    
    public cl(final int a, final String d, final int b, final int c) {
        this.w = new tu((ad7)null);
        this.x = false;
        this.y = false;
        this.z = false;
        this.aa = false;
        this.ab = false;
        this.ac = -1;
        this.ad = null;
        this.ae = null;
        this.af = -1L;
        this.ag = null;
        this.a = a;
        this.d = d;
        this.b = b;
        this.c = c;
    }
    
    public cl(final int a, final String d, final String e) {
        this.w = new tu((ad7)null);
        this.x = false;
        this.y = false;
        this.z = false;
        this.aa = false;
        this.ab = false;
        this.ac = -1;
        this.ad = null;
        this.ae = null;
        this.af = -1L;
        this.ag = null;
        this.a = a;
        this.d = d;
        this.e = e;
    }
    
    public cl(final int a, final URL f, final int b) {
        this.w = new tu((ad7)null);
        this.x = false;
        this.y = false;
        this.z = false;
        this.aa = false;
        this.ab = false;
        this.ac = -1;
        this.ad = null;
        this.ae = null;
        this.af = -1L;
        this.ag = null;
        this.a = a;
        this.b = b;
        this.f = f;
    }
    
    public cl(final int a, final Object[] i, final int b) {
        this.w = new tu((ad7)null);
        this.x = false;
        this.y = false;
        this.z = false;
        this.aa = false;
        this.ab = false;
        this.ac = -1;
        this.ad = null;
        this.ae = null;
        this.af = -1L;
        this.ag = null;
        this.a = a;
        this.b = b;
        this.i = i;
    }
    
    public cl(final int a, final Object[] i, final Object[] j, final Object[] l, final Object[] m) {
        this.w = new tu((ad7)null);
        this.x = false;
        this.y = false;
        this.z = false;
        this.aa = false;
        this.ab = false;
        this.ac = -1;
        this.ad = null;
        this.ae = null;
        this.af = -1L;
        this.ag = null;
        this.a = a;
        this.i = i;
        this.j = j;
        this.l = l;
        this.m = m;
    }
    
    public cl(final int a, final Object[] i, final Object[] j) {
        this.w = new tu((ad7)null);
        this.x = false;
        this.y = false;
        this.z = false;
        this.aa = false;
        this.ab = false;
        this.ac = -1;
        this.ad = null;
        this.ae = null;
        this.af = -1L;
        this.ag = null;
        this.a = a;
        this.i = i;
        this.j = j;
    }
    
    public cl(final int a, final Object[] i, final Object r) {
        this.w = new tu((ad7)null);
        this.x = false;
        this.y = false;
        this.z = false;
        this.aa = false;
        this.ab = false;
        this.ac = -1;
        this.ad = null;
        this.ae = null;
        this.af = -1L;
        this.ag = null;
        this.a = a;
        this.i = i;
        this.r = r;
    }
    
    public cl(final int a, final Object[] i) {
        this.w = new tu((ad7)null);
        this.x = false;
        this.y = false;
        this.z = false;
        this.aa = false;
        this.ab = false;
        this.ac = -1;
        this.ad = null;
        this.ae = null;
        this.af = -1L;
        this.ag = null;
        this.a = a;
        this.i = i;
    }
    
    public cl(final int a, final Object r, final int b) {
        this.w = new tu((ad7)null);
        this.x = false;
        this.y = false;
        this.z = false;
        this.aa = false;
        this.ab = false;
        this.ac = -1;
        this.ad = null;
        this.ae = null;
        this.af = -1L;
        this.ag = null;
        this.a = a;
        this.r = r;
        this.b = b;
    }
    
    public cl(final int a, final String[] u, final boolean k) {
        this.w = new tu((ad7)null);
        this.x = false;
        this.y = false;
        this.z = false;
        this.aa = false;
        this.ab = false;
        this.ac = -1;
        this.ad = null;
        this.ae = null;
        this.af = -1L;
        this.ag = null;
        this.a = a;
        this.u = u;
        this.k = k;
    }
    
    public cl(final int a, final Object r, final Object[] i, final int b) {
        this.w = new tu((ad7)null);
        this.x = false;
        this.y = false;
        this.z = false;
        this.aa = false;
        this.ab = false;
        this.ac = -1;
        this.ad = null;
        this.ae = null;
        this.af = -1L;
        this.ag = null;
        this.a = a;
        this.r = r;
        this.i = i;
        this.b = b;
    }
    
    public cl(final int a, final Object r, final Object[] i, final Object[] j, final int b) {
        this.w = new tu((ad7)null);
        this.x = false;
        this.y = false;
        this.z = false;
        this.aa = false;
        this.ab = false;
        this.ac = -1;
        this.ad = null;
        this.ae = null;
        this.af = -1L;
        this.ag = null;
        this.a = a;
        this.r = r;
        this.i = i;
        this.j = j;
        this.b = b;
    }
    
    public cl(final int a, final Object r, final String d, final int b) {
        this.w = new tu((ad7)null);
        this.x = false;
        this.y = false;
        this.z = false;
        this.aa = false;
        this.ab = false;
        this.ac = -1;
        this.ad = null;
        this.ae = null;
        this.af = -1L;
        this.ag = null;
        this.a = a;
        this.r = r;
        this.d = d;
        this.b = b;
    }
    
    public cl(final int a, final Object r, final String d, final boolean k, final boolean p6, final boolean v) {
        this.w = new tu((ad7)null);
        this.x = false;
        this.y = false;
        this.z = false;
        this.aa = false;
        this.ab = false;
        this.ac = -1;
        this.ad = null;
        this.ae = null;
        this.af = -1L;
        this.ag = null;
        this.a = a;
        this.r = r;
        this.d = d;
        this.k = k;
        this.p = p6;
        this.v = v;
    }
    
    public void a(final boolean x) {
        this.x = x;
    }
    
    public boolean d() {
        return this.x;
    }
    
    public void b(final boolean y) {
        this.y = y;
    }
    
    public boolean e() {
        return this.y;
    }
    
    public void f() {
        this.z = true;
    }
    
    public final boolean g() {
        return this.z;
    }
    
    public void h() {
        this.b();
    }
    
    public void a(final Throwable ag) {
        synchronized (this) {
            this.ag = ag;
        }
        this.b();
    }
    
    public void b(final Throwable ag) {
        synchronized (this) {
            this.ag = ag;
        }
    }
    
    public void i() {
        synchronized (this) {
            if (this.ag != null) {
                throw new fn(this.ag);
            }
        }
    }
    
    public void a(final int ac) {
        this.ac = ac;
    }
    
    public int j() {
        return this.ac;
    }
    
    public void a(final long af) {
        this.af = af;
    }
    
    public long k() {
        return this.af;
    }
    
    public void a(final String ad) {
        this.ad = ad;
    }
    
    public String l() {
        return this.ad;
    }
    
    public void a(final Object ae) {
        this.ae = ae;
    }
    
    public Object m() {
        return this.ae;
    }
    
    public void c(final boolean ab) {
        this.ab = ab;
    }
    
    public boolean n() {
        return this.ab;
    }
    
    public cl(final cl cl) {
        this.w = new tu((ad7)null);
        this.x = false;
        this.y = false;
        this.z = false;
        this.aa = false;
        this.ab = false;
        this.ac = -1;
        this.ad = null;
        this.ae = null;
        this.af = -1L;
        this.ag = null;
        this.a = cl.a;
        this.b = cl.b;
        this.c = cl.c;
        this.d = cl.d;
        this.e = cl.e;
        this.f = cl.f;
        this.g = cl.g;
        this.h = cl.h;
        this.i = cl.i;
        this.j = cl.j;
        this.k = cl.k;
        this.l = cl.l;
        this.m = cl.m;
        this.n = cl.n;
        this.o = cl.o;
        this.p = cl.p;
        this.q = cl.q;
        this.r = cl.r;
        this.s = cl.s;
        this.t = cl.t;
        this.u = cl.u;
        this.x = cl.x;
        this.y = cl.y;
    }
    
    public void o() {
        this.aa = true;
        this.a = 0;
        this.b = 0;
        this.c = 0;
        this.d = null;
        this.e = null;
        this.f = null;
        this.g = null;
        this.h = null;
        this.i = null;
        this.j = null;
        this.k = false;
        this.l = null;
        this.m = null;
        this.n = null;
        this.o = null;
        this.r = null;
        this.s = 0;
        this.t = 0;
        this.u = null;
    }
    
    private class tu
    {
        public boolean a;
        
        private tu(final cl cl) {
            this.a = false;
        }
    }
    
    interface ad7
    {
    }
}

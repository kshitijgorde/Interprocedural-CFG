// 
// Decompiled by Procyon v0.5.30
// 

package ji.filter.filenetcold;

import java.util.Enumeration;
import ji.filter.dll.n3;
import java.awt.Rectangle;
import ji.sec.f;
import ji.res.aa;
import ji.zip.a4;
import ji.util.y;
import ji.sec.aw;
import ji.res.z;
import ji.util.e;
import ji.v1event.a6;
import ji.util.cn;
import ji.v1event.af;
import ji.document.ad;
import ji.filter.dll.n0;
import ji.v1event.am;
import ji.io.ac;
import java.awt.Component;
import ji.res.ay;
import ji.io.h;
import ji.util.i;
import ji.util.d;
import ji.sec.g;
import ji.filter.fh;
import java.util.Hashtable;
import ji.ext.oa;
import ji.ext.v;
import ji.io.q;
import ji.image.dx;
import ji.io.p;
import ji.annotate.df;

public class jiFilenetColdDecoder implements n9
{
    boolean COMPRESSED_WRITE;
    private static final String[] dllWinFilenetCOLD;
    private static final String fontsDirectory = "fontfiles";
    private static final String[] fontFiles;
    private static final String pixtranDirectory = "pixtran";
    private static final String[] pixtranFiles;
    private static final String logPrefix = "Filenet COLD DLL";
    private static final String logAutoUpdatePrefix = "Auto-Update";
    private static final String loadedPrefName = "ColdDllsLoaded";
    private static zs librariesLoaded;
    private static boolean permissionsFailureOnLoad;
    private static boolean fontsAddedNotInstalled;
    private static final boolean useExternalLoadLibrary = true;
    private static final int supportedPlatforms = 1;
    df annotations;
    private p prefs;
    private int[] palette;
    private int pixelDepth;
    private boolean drawingCancelled;
    private boolean subPage;
    private dx previousHeader;
    private String parentId;
    private q fileCache;
    private v jiex1;
    private oa jiex2;
    private Object PROCESSING_LOCK;
    private static Hashtable openDocs;
    private String errorMsg;
    
    private native boolean _setDebugging(final boolean p0) throws UnsatisfiedLinkError;
    
    private jiFilenetColdDoc getDocForFilename(final fh fh) {
        synchronized (this.PROCESSING_LOCK) {
            try {
                if (!this.loadExternalLibraries(fh)) {
                    // monitorexit(this.PROCESSING_LOCK)
                    return null;
                }
                String s = fh.f;
                if (s == null) {
                    s = fh.d.h;
                }
                if (s == null) {
                    // monitorexit(this.PROCESSING_LOCK)
                    return null;
                }
                String s2 = fh.x;
                if (s2 == null) {
                    s2 = fh.d.cc;
                }
                this.logTraceFilter("File is not open so open it.");
                jiFilenetColdDoc jiFilenetColdDoc3 = new jiFilenetColdDoc(fh.u);
                boolean b = true;
                if (fh.o != null && fh.o.e0()) {
                    b = false;
                }
                g.b(s, this.parentId);
                g.b(s2, this.parentId);
                String s3 = s;
                String s4 = s2;
                int c2 = d.c2() ? 1 : 0;
                if (c2 != 0) {
                    String s5 = fh.e;
                    if (s5 == null) {
                        s5 = fh.d.f;
                    }
                    if (s5 != null && !d.bj(s5)) {
                        c2 = 0;
                    }
                }
                if (c2 != 0) {
                    if (i.c(208)) {
                        h.d(this.parentId, d.b(1237, this.parentId));
                        d.a(ay.a(), d.f(d.b(1237, this.parentId), 70), fh.o, 60, null, fh.g, this.parentId);
                        // monitorexit(this.PROCESSING_LOCK)
                        return null;
                    }
                    String s6 = fh.e;
                    if (s6 == null && fh.d != null) {
                        s6 = fh.d.f;
                    }
                    final String a = this.fileCache.a(d.cq(s6));
                    if (!ac.d(a, this.parentId)) {
                        ac.a(s, a, null, this.parentId);
                    }
                    s3 = a;
                    String s7 = null;
                    if (fh.y != null) {
                        if (fh.y.a != null) {
                            s7 = fh.y.a.toString();
                        }
                        if (s7 == null && fh.d != null) {
                            s7 = fh.d.cb.a.toString();
                        }
                    }
                    if (s7 != null) {
                        final String a2 = this.fileCache.a(d.cq(s7));
                        if (!ac.d(a2, this.parentId)) {
                            ac.a(s2, a2, null, this.parentId);
                        }
                        s4 = a2;
                    }
                }
                if (jiFilenetColdDoc3.a(s3, s4, b, i.c(220))) {
                    this.logTraceFilter(String.valueOf(String.valueOf(new StringBuffer("Document has been opened.").append(s3).append(", ").append(s4))));
                    jiFilenetColdDecoder.openDocs.put(s, jiFilenetColdDoc3);
                }
                else {
                    this.logPrefixedText("Could not open document ".concat(String.valueOf(String.valueOf(s))));
                    jiFilenetColdDoc3.c((dx)null);
                    jiFilenetColdDoc3 = null;
                }
                if (jiFilenetColdDoc3 != null) {
                    jiFilenetColdDoc3.a(fh.o);
                    jiFilenetColdDoc3.b(this);
                }
                // monitorexit(this.PROCESSING_LOCK)
                return jiFilenetColdDoc3;
            }
            catch (Exception ex) {
                this.logTraceFilter("Exception occured locating document ".concat(String.valueOf(String.valueOf(ex.getMessage()))));
                d.a(ex);
            }
            catch (Error error) {
                this.logTraceFilter("Exception occured locating document ".concat(String.valueOf(String.valueOf(error.getMessage()))));
                d.a(error);
            }
            // monitorexit(this.PROCESSING_LOCK)
            return null;
        }
    }
    
    private boolean loadExternalLibraries(final fh fh) {
        boolean b;
        synchronized (jiFilenetColdDecoder.librariesLoaded) {
            b = jiFilenetColdDecoder.librariesLoaded.a;
            if (!jiFilenetColdDecoder.librariesLoaded.a) {
                jiFilenetColdDecoder.librariesLoaded.a = this.loadExternalLibraries(fh.o, fh.u, fh.g, fh.q, fh.r);
                if (!jiFilenetColdDecoder.librariesLoaded.a || this.isDrawingCancelled()) {
                    jiFilenetColdDecoder.librariesLoaded.a = false;
                }
                b = jiFilenetColdDecoder.librariesLoaded.a;
            }
        }
        // monitorexit(jiFilenetColdDecoder.librariesLoaded)
        return b;
    }
    
    public jiFilenetColdDecoder() {
        this.COMPRESSED_WRITE = false;
        this.annotations = null;
        this.prefs = null;
        this.palette = null;
        this.pixelDepth = 24;
        this.subPage = false;
        this.previousHeader = null;
        this.PROCESSING_LOCK = new Object();
        this.errorMsg = null;
        this.jiex1 = new v();
        this.jiex2 = new oa();
    }
    
    public final void setParentId(final String parentId) {
        this.parentId = parentId;
        if (this.fileCache == null) {
            this.fileCache = q.a((Object)null, parentId);
        }
    }
    
    private String getLibraryPackageName() {
        return "fncld.zip";
    }
    
    private boolean loadExternalLibraries(final ad ad, final String s, final af af, final boolean b, final boolean b2) {
        this.getLibraryPackageName();
        boolean b3 = true;
        try {
            this.logTraceFilter("Loading libaries...");
            boolean b4 = false;
            final String b5 = d.b(1234, s);
            final String value = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(d.b(758, s)))).append(" ").append(d.b(270, s))));
            this.updateStatus(af, b, b2, b5);
            final boolean a = cn.a(ad, s, af, null, jiFilenetColdDecoder.dllWinFilenetCOLD, b5, false, false, false);
            this.logTraceFilter("Current library state: ".concat(String.valueOf(String.valueOf(a))));
            boolean b6 = false;
            if (a && !this.hasPreviouslyLoaded(ad, s)) {
                b6 = true;
            }
            if (!a) {
                if (d.b2() > 1) {
                    d.a(ay.a(), String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(d.b(1232, s)))).append("\n\n").append(d.b(1233, s)))), ad, 120, null, af, s);
                    return false;
                }
                b3 = this.downloadModule(ad, s, af, b, b2, !i.c(207));
                if (b3) {
                    b4 = true;
                }
            }
            this.logTraceFilter("Loaded status: ".concat(String.valueOf(String.valueOf(b3))));
            if (b3) {
                b3 = cn.a(ad, s, af, null, jiFilenetColdDecoder.dllWinFilenetCOLD, b5, false, false, false);
            }
            if (b3) {
                try {
                    if (b6) {
                        b4 = true;
                    }
                    if (b4) {
                        this.logAutoUpdateText("loading Filenet COLD library...");
                        this.updateStatus(af, b, b2, b5);
                    }
                    b3 = this.loadLibraries(ad, s, af, jiFilenetColdDecoder.dllWinFilenetCOLD, b4);
                    if (b3) {
                        b3 = this.loadPixtranLibraries(ad, s, af, "pixtran", jiFilenetColdDecoder.pixtranFiles, b4);
                    }
                    this.fireStatus(af, new a6(this, 4, 0));
                    if (b4) {
                        this.logAutoUpdateText("loaded.");
                    }
                    this.fireStatus(af, new a6(this, 1, ""));
                }
                catch (Exception ex) {
                    ex.printStackTrace();
                }
                e.ag(null);
            }
            if (b3) {
                boolean a2 = false;
                if (e.y(s) && this.jiex1.c(s, ad)) {
                    a2 = this.jiex2.a(s, ad);
                }
                if (!a2) {
                    if (!this.allFontsOK(ad, s, jiFilenetColdDecoder.fontFiles)) {
                        b3 = this.copyFonts(ad, s, "fontfiles", jiFilenetColdDecoder.fontFiles);
                    }
                    if (!b3) {
                        b3 = this.addFontsInProcess(ad, s, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(ac.b(z.a(s), s)))).append("\\").append("fontfiles"))), jiFilenetColdDecoder.fontFiles);
                        if (b3) {
                            jiFilenetColdDecoder.fontsAddedNotInstalled = true;
                        }
                    }
                    if (!b3) {
                        jiFilenetColdDecoder.permissionsFailureOnLoad = true;
                        return false;
                    }
                }
                else {
                    b3 = false;
                    h.c(s, "Internet Explorer is in Protected Mode");
                    final String b7 = this.jiex2.b(s, ad);
                    this.jiex2.a(false, s, ad);
                    if (b7 != null) {
                        b3 = this.copyFontsProtectedModePath(ad, s, "fontfiles", jiFilenetColdDecoder.fontFiles, b7);
                        if (b3) {
                            b3 = this.addFontsInProcess(ad, s, b7, jiFilenetColdDecoder.fontFiles);
                        }
                        if (b3) {
                            jiFilenetColdDecoder.fontsAddedNotInstalled = true;
                        }
                    }
                    if (!b3) {
                        jiFilenetColdDecoder.permissionsFailureOnLoad = true;
                        return false;
                    }
                }
            }
            if (b3) {
                this.updateStatus(af, b, b2, value);
            }
        }
        catch (Exception ex2) {
            ex2.printStackTrace();
            b3 = false;
        }
        finally {
            this.logTraceFilter("Load complete.");
            e.a(false, 0, null, null);
        }
        return b3;
    }
    
    private boolean hasPreviouslyLoaded(final Component component, final String s) {
        boolean b = true;
        this.initPrefs(s);
        final String b2 = this.prefs.b("ColdDllsLoaded", "no", component);
        if (!d.by(b2)) {
            if (!b2.equals("yes")) {
                b = false;
            }
        }
        else {
            b = false;
        }
        return b;
    }
    
    private boolean allFontsOK(final Component component, final String s, final String[] array) {
        boolean b = false;
        final String a = this.jiex1.a(component, s);
        if (a != null) {
            final String concat = String.valueOf(String.valueOf(a)).concat("\\fonts");
            if (!ac.d(concat, s)) {
                this.logTraceFilter("Could not find system fonts directory: ".concat(String.valueOf(String.valueOf(concat))));
            }
            else {
                b = true;
                for (int i = 0; i < array.length; ++i) {
                    if (!ac.d(String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(concat))).append(d.bu(s)).append(array[i]))), s)) {
                        this.logTraceFilter(String.valueOf(String.valueOf(new StringBuffer("Font ").append(concat).append(d.bu(s)).append(array[i]).append(" has not been installed."))));
                        b = false;
                    }
                    else {
                        this.logTraceFilter(String.valueOf(String.valueOf(new StringBuffer("Font ").append(concat).append(d.bu(s)).append(array[i]).append(" is already installed."))));
                    }
                }
            }
        }
        else {
            this.logTraceFilter("Could not retrieve Windows Directory to work out if fonts already installed.");
        }
        return b;
    }
    
    private boolean copyFonts(final Component component, final String s, final String s2, final String[] array) {
        boolean b = false;
        final String a = this.jiex1.a(component, s);
        if (a != null) {
            final String value = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(ac.b(z.a(s), s)))).append(d.bu(s)).append(s2)));
            final String concat = String.valueOf(String.valueOf(a)).concat("\\fonts");
            if (!ac.d(concat, s)) {
                this.logTraceFilter("Could not find system fonts directory: ".concat(String.valueOf(String.valueOf(concat))));
            }
            else {
                b = true;
                for (int i = 0; i < array.length; ++i) {
                    try {
                        ac.a(String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(value))).append(d.bu(s)).append(array[i]))), String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(concat))).append(d.bu(s)).append(array[i]))), component, s);
                    }
                    catch (Exception ex) {
                        this.logTraceFilter(String.valueOf(String.valueOf(new StringBuffer("Could not copy font ").append(array[i]).append(" into the directory").append(s2))));
                        ex.printStackTrace();
                        b = false;
                        break;
                    }
                    if (!ac.d(String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(concat))).append(d.bu(s)).append(array[i]))), s)) {
                        this.logTraceFilter(String.valueOf(String.valueOf(new StringBuffer("Font ").append(array[i]).append(" has not been copied into the directory ").append(s2))));
                        b = false;
                        break;
                    }
                    this.logTraceFilter(String.valueOf(String.valueOf(new StringBuffer("Font ").append(array[i]).append(" has now been copied into the directory ").append(s2))));
                }
            }
        }
        else {
            this.logTraceFilter("Could not retrieve Windows Directory to work out if fonts already installed.");
        }
        return b;
    }
    
    private boolean copyFontsProtectedModePath(final Component component, final String s, final String s2, final String[] array, final String s3) {
        boolean b = false;
        if (!ac.d(s3, s)) {
            this.logTraceFilter("Could not find directory: ".concat(String.valueOf(String.valueOf(s3))));
        }
        else {
            b = true;
            final String value = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(ac.b(z.a(s), s)))).append(d.bu(s)).append(s2).append(d.bu(s))));
            for (int i = 0; i < array.length; ++i) {
                try {
                    this.copyFontProtectedMode(component, s, String.valueOf(String.valueOf(value)).concat(String.valueOf(String.valueOf(jiFilenetColdDecoder.fontFiles[i]))), String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(s3))).append(d.bu(s)).append(array[i]))));
                }
                catch (Exception ex) {
                    this.logTraceFilter(String.valueOf(String.valueOf(new StringBuffer("Could not copy font ").append(array[i]).append(" into the directory").append(s3))));
                    ex.printStackTrace();
                    b = false;
                    break;
                }
                if (!ac.d(String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(s3))).append(d.bu(s)).append(array[i]))), s)) {
                    this.logTraceFilter(String.valueOf(String.valueOf(new StringBuffer("Font ").append(array[i]).append(" has not been copied into the directory ").append(s3))));
                    b = false;
                    break;
                }
                this.logTraceFilter(String.valueOf(String.valueOf(new StringBuffer("Font ").append(array[i]).append(" has now been copied into the directory ").append(s3))));
            }
        }
        return b;
    }
    
    private void copyFontProtectedMode(final Component component, final String s, final String s2, final String s3) throws Exception {
        final ac ac = new ac(s2, false, false, 0, component, s);
        final aw aw = new aw(s3, s);
        try {
            final byte[] array = new byte[102400];
            int a;
            for (int n = (int)ac.w(), i = 0; i < n; i += a) {
                a = ac.a(array);
                if (a <= 0) {
                    break;
                }
                aw.write(array, 0, a);
            }
        }
        finally {
            try {
                if (ac != null) {
                    ac.a(component);
                }
            }
            catch (Exception ex) {}
            try {
                if (aw != null) {
                    aw.flush();
                    aw.close();
                }
            }
            catch (Exception ex2) {}
        }
        g.b(s3, s);
    }
    
    private boolean addFontsInProcess(final Component component, final String s, final String s2, final String[] array) {
        final boolean c = i.c(220);
        this.logTraceFilter("Adding font in process.");
        if (!ac.d(s2, s)) {
            this.logTraceFilter("Fonts have not been downloaded correctly.");
            return false;
        }
        for (int i = 0; i < array.length; ++i) {
            final boolean addFontResource = this.addFontResource(component, s, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(s2))).append("\\").append(array[i]))), c);
            if (!addFontResource) {
                this.logTraceFilter(String.valueOf(String.valueOf(new StringBuffer("Could not add font ").append(array[i]).append(" in process. Error: ").append(addFontResource))));
                return false;
            }
            this.logTraceFilter(String.valueOf(String.valueOf(new StringBuffer("Font ").append(array[i]).append(" has been added in process."))));
        }
        return true;
    }
    
    private void removeFontsInProcess(final Component component, final String s, final String s2, final String[] array) {
        final boolean c = i.c(220);
        this.logTraceFilter("Removing In Process installed fonts.");
        final String value = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(ac.b(z.a(s), s)))).append(d.bu(s)).append(s2)));
        for (int i = 0; i < array.length; ++i) {
            if (this.removeFontResource(component, s, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(value))).append("\\").append(array[i]))), c)) {
                this.logTraceFilter(String.valueOf(String.valueOf(new StringBuffer("Font ").append(array[i]).append(" has been removed in process."))));
            }
            else {
                this.logTraceFilter(String.valueOf(String.valueOf(new StringBuffer("Could not remove font ").append(array[i]).append(" in process."))));
            }
        }
    }
    
    private boolean downloadModule(final ad ad, final String s, final af af, final boolean b, final boolean b2, final boolean b3) throws Exception {
        final String libraryPackageName = this.getLibraryPackageName();
        final String b4 = d.b(1234, s);
        final String value = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(d.b(708, s)))).append(" ").append(d.b(1235, s))));
        final String value2 = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(d.b(709, s)))).append(" ").append(d.b(1235, s))));
        e.a(true, 708, null, null);
        this.logTraceFilter(String.valueOf(String.valueOf(new StringBuffer("loading ").append(libraryPackageName).append("..."))));
        final String s2 = b3 ? d.b(libraryPackageName, d.bh(libraryPackageName), "v1") : libraryPackageName;
        String s3 = z.a(ad, libraryPackageName, s2, af, s, ad);
        if (s3 != null) {
            s3 = ac.b(s3, s);
        }
        if (s3 != null) {
            try {
                if (ac.a(s3, s) <= 0) {
                    this.removeDecoder(ad, s);
                    try {
                        ac.c(s3, s);
                    }
                    catch (Exception ex) {}
                    s3 = null;
                }
            }
            catch (Exception ex2) {}
        }
        final String b5 = ac.b(z.a(s), s);
        if (s3 == null) {
            this.updateStatus(af, b, b2, value);
            final String value3 = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(b5))).append("/").append(s2)));
            final String value4 = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(d.b(726, s)))).append("\n").append(d.b(727, s)).append("\n").append("\n").append(d.b(728, s)).append("\n").append(d.b(729, s)).append("\n").append(d.b(730, s))));
            this.logTraceFilter(String.valueOf(String.valueOf(new StringBuffer("downloading ").append(s2).append(" to ").append(b5).append("..."))));
            this.logAutoUpdateText(String.valueOf(String.valueOf(new StringBuffer("downloading Filenet COLD library ").append(s2).append(" to ").append(b5).append("..."))));
            z.a(s2, e.an(), 3, af, ad, s, false, value3, value4, false, new y(), false);
            s3 = z.a(ad, s2, s2, af, s, ad);
        }
        if (s3 != null && b3) {
            this.updateStatus(af, b, b2, value2);
            this.fireStatus(af, new a6(this, 4, "0"));
            final String value5 = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(b5))).append("/").append(libraryPackageName)));
            d.a(s3, value5, s2, false, ad, s, af);
            s3 = value5;
        }
        this.updateStatus(af, b, b2, b4);
        if (s3 != null) {
            final long a = ac.a(s3, s);
            if (a > 0) {
                s3 = ac.b(s3, s);
                this.logAutoUpdateText(String.valueOf(String.valueOf(new StringBuffer("#11 downloaded (").append(d.a(a, s)).append(")..."))));
            }
            else {
                try {
                    this.removeDecoder(ad, s);
                    ac.c(s3, s);
                }
                catch (Exception ex3) {}
                final String concat = String.valueOf(String.valueOf(e.am())).concat(String.valueOf(String.valueOf(s2)));
                this.logAutoUpdateText(String.valueOf(String.valueOf(new StringBuffer("file not found! (").append(concat).append(")..."))));
                d.a(ay.a(), String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(concat))).append("\n ----- ").append(d.b(260, s)).append(" -----"))), ad, 60, null, af, s);
                s3 = null;
            }
        }
        boolean b6;
        if (s3 != null) {
            this.logAutoUpdateText(String.valueOf(String.valueOf(new StringBuffer("exporting Filenet COLD library ").append(libraryPackageName).append("..."))));
            this.updateStatus(af, b, b2, value2);
            final String a2 = cn.a(s);
            if (a4.a(s3, a2, null, true, ad, s, false, true, af, true)) {
                if (aa.b(ad, s)) {
                    final String[] a3 = a4.a(s3, ad, s);
                    if (!this.isDrawingCancelled()) {
                        cn.a(ad, s, a2, a3);
                    }
                }
                this.logAutoUpdateText("expanded.");
                b6 = true;
            }
            else {
                try {
                    this.removeDecoder(ad, s);
                    ac.c(s3, s);
                }
                catch (Exception ex4) {}
                this.logAutoUpdateText(String.valueOf(String.valueOf(new StringBuffer("unable to expand ").append(libraryPackageName).append("!"))));
                b6 = false;
            }
            try {
                if (s3 != null) {
                    ac.c(s3, s);
                }
            }
            catch (Exception ex5) {}
            try {
                if (s2 != null) {
                    ac.c(d.b(ac.b(String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(a2))).append("/").append(s2))), s), "\\", "/"), s);
                }
            }
            catch (Exception ex6) {}
        }
        else {
            b6 = false;
        }
        return b6;
    }
    
    private void updateStatus(final af af, final boolean b, final boolean b2, final String s) {
        if (af != null) {
            this.fireStatus(af, new a6(this, 1, s));
            if (!b && !b2) {
                e.ag(s);
                this.fireStatus(af, new a6(this, 9, s));
            }
        }
    }
    
    private boolean loadLibraries(final ad ad, final String s, final af af, final String[] array, final boolean b) {
        boolean b2 = true;
        final long currentTimeMillis = System.currentTimeMillis();
        Label_0266: {
            try {
                int n = 1;
                int n2 = 5;
                final int length = array.length;
                final boolean c = i.c(220);
                String a = null;
                Block_11: {
                    for (int n3 = 0; n3 < length && !this.isDrawingCancelled(); ++n3) {
                        a = ac.a(cn.b(array[n3], s));
                        this.logTraceFilter("Load library: ".concat(String.valueOf(String.valueOf(a))));
                        if (n3 == array.length - 1) {
                            if (this.fileCache != null) {
                                final String o = this.fileCache.o();
                                f.a(a, ad, s, o);
                                jiFilenetColdDecoder.librariesLoaded.b = o;
                            }
                            else {
                                f.a(a, ad, s, null);
                            }
                        }
                        else if (this.jiex1.a(a, c, s, ad) == 0) {
                            break Block_11;
                        }
                        final int n4 = 100 * n++ / length;
                        if (--n2 <= 0) {
                            n2 = n2;
                            this.fireStatus(af, new a6(this, 4, n4));
                        }
                    }
                    break Label_0266;
                }
                this.logPrefixedText(String.valueOf(String.valueOf(new StringBuffer("Load library failed aborting (").append(a).append(") *****"))));
                b2 = false;
            }
            catch (UnsatisfiedLinkError unsatisfiedLinkError) {
                this.logPrefixedText("Load libraries failed ".concat(String.valueOf(String.valueOf(unsatisfiedLinkError.getMessage()))));
            }
        }
        if (b && !this.isDrawingCancelled()) {
            this.logTraceFilter("Load library successful, setting pref");
            this.initPrefs(s);
            this.prefs.a("ColdDllsLoaded", "yes", ad);
        }
        if (b && this.isDrawingCancelled()) {
            this.removeDecoder(ad, s);
            b2 = false;
        }
        this.logTraceFilter(String.valueOf(String.valueOf(new StringBuffer("Load library total took: ").append(System.currentTimeMillis() - currentTimeMillis).append("ms"))));
        return b2;
    }
    
    private boolean loadPixtranLibraries(final ad ad, final String s, final af af, final String s2, final String[] array, final boolean b) {
        boolean b2 = true;
        final long currentTimeMillis = System.currentTimeMillis();
        Label_0250: {
            try {
                int n = 1;
                int n2 = 5;
                final int length = array.length;
                final boolean c = i.c(220);
                String a = null;
                Block_9: {
                    for (int n3 = 0; n3 < length && !this.isDrawingCancelled(); ++n3) {
                        a = ac.a(String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(z.a(s)))).append(d.bu(s)).append(s2).append(d.bu(s)).append(array[n3]))));
                        this.logTraceFilter("Load library: ".concat(String.valueOf(String.valueOf(a))));
                        if (this.jiex1.a(a, c, s, ad) == 0) {
                            break Block_9;
                        }
                        final int n4 = 100 * n++ / length;
                        if (--n2 <= 0) {
                            n2 = n2;
                            this.fireStatus(af, new a6(this, 4, n4));
                        }
                    }
                    break Label_0250;
                }
                this.logPrefixedText(String.valueOf(String.valueOf(new StringBuffer("Load library failed aborting (").append(a).append(") *****"))));
                b2 = false;
            }
            catch (UnsatisfiedLinkError unsatisfiedLinkError) {
                this.logPrefixedText("Load libraries failed ".concat(String.valueOf(String.valueOf(unsatisfiedLinkError.getMessage()))));
            }
        }
        if (b && !this.isDrawingCancelled()) {
            this.logTraceFilter("Load library successful, setting pref");
            this.initPrefs(s);
            this.prefs.a("ColdDllsLoaded", "yes", ad);
        }
        if (b && this.isDrawingCancelled()) {
            this.removeDecoder(ad, s);
            b2 = false;
        }
        this.logTraceFilter(String.valueOf(String.valueOf(new StringBuffer("Load library total took: ").append(System.currentTimeMillis() - currentTimeMillis).append("ms"))));
        return b2;
    }
    
    private void fireStatus(final af af, final a6 a6) {
        if (af != null) {
            af.a(a6);
        }
    }
    
    public final void removeDecoder(final ad ad, final String s) {
        if (jiFilenetColdDecoder.openDocs.size() > 0) {
            this.logTraceFilter("Can't remove decoder, we have a document open");
            return;
        }
        synchronized (jiFilenetColdDecoder.librariesLoaded) {
            jiFilenetColdDecoder.librariesLoaded.a = false;
            boolean al = false;
            try {
                al = d.al;
                d.al = true;
                if (jiFilenetColdDecoder.fontsAddedNotInstalled) {
                    this.removeFontsInProcess(ad, s, "fontfiles", jiFilenetColdDecoder.fontFiles);
                }
                if (!d.by(jiFilenetColdDecoder.librariesLoaded.b)) {
                    cn.a(ad, s, null, null, new String[] { jiFilenetColdDecoder.librariesLoaded.b }, null, true, false, true);
                    jiFilenetColdDecoder.librariesLoaded.b = null;
                }
                try {
                    final String[] array = new String[jiFilenetColdDecoder.dllWinFilenetCOLD.length];
                    System.arraycopy(jiFilenetColdDecoder.dllWinFilenetCOLD, 0, array, 0, jiFilenetColdDecoder.dllWinFilenetCOLD.length);
                    cn.a(ad, s, null, null, array, null, true, false, false);
                }
                catch (Exception ex) {
                    d.a(ex);
                }
                String s2;
                if (d.av(s)) {
                    s2 = ac.b(cn.a(s), s);
                }
                else {
                    s2 = ac.b(z.a(s), s);
                }
                final String value = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(s2))).append("/").append(this.getLibraryPackageName())));
                final String value2 = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(s2))).append("/").append(d.b(this.getLibraryPackageName(), d.bh(this.getLibraryPackageName()), "v1"))));
                try {
                    ac.c(value, s);
                }
                catch (Exception ex3) {}
                try {
                    ac.c(value2, s);
                }
                catch (Exception ex4) {}
            }
            catch (Exception ex2) {
                d.a(ex2);
            }
            finally {
                d.al = al;
            }
        }
        // monitorexit(jiFilenetColdDecoder.librariesLoaded)
    }
    
    public final dx processHeader(final dx dx, final fh fh, final String s) throws Exception {
        return this.processHeaderAction(dx, fh, s);
    }
    
    private dx processHeaderAction(dx previousHeader, final fh fh, final String s) throws Exception {
        if (this.isDrawingCancelled()) {
            return null;
        }
        final jiFilenetColdDoc docForFilename = this.getDocForFilename(fh);
        if (docForFilename != null && fh != null) {
            final long currentTimeMillis = System.currentTimeMillis();
            try {
                this.logTraceFilter("Processing header...");
                if (!docForFilename.a()) {
                    docForFilename.b(true);
                }
                this.setDrawingCancelled(false);
                final boolean b = fh.r && fh.j != 6;
                try {
                    fh.h = Math.max(fh.h, 1);
                    final int h = fh.h;
                    final int validPageNumber = this.getValidPageNumber(docForFilename, h, fh, fh.o);
                    final jiFilenetColdDoc.zr zr = (jiFilenetColdDoc.zr)docForFilename.b(validPageNumber);
                    final double g = zr.g();
                    final Rectangle b2 = zr.b(g);
                    if (b2 == null) {
                        this.logTraceFilter("Unable to retrieve CB for doc");
                        return null;
                    }
                    previousHeader.m = b2.width;
                    previousHeader.n = b2.height;
                    switch (this.pixelDepth) {
                        case 1: {
                            previousHeader.z = 1;
                            previousHeader.aa = 1;
                            previousHeader.am = 1;
                            break;
                        }
                        case 8: {
                            previousHeader.z = 8;
                            previousHeader.aa = 1;
                            previousHeader.am = 8;
                            break;
                        }
                        default: {
                            previousHeader.z = 8;
                            previousHeader.aa = 3;
                            previousHeader.am = 24;
                            break;
                        }
                    }
                    previousHeader.bk = new Hashtable();
                    if (fh.a != null) {
                        if (fh.a.toString().indexOf("#") != -1) {
                            previousHeader.u = 1;
                        }
                        else {
                            previousHeader.u = docForFilename.h();
                        }
                    }
                    else {
                        previousHeader.u = 1;
                    }
                    previousHeader.i = ac.a(fh.b.a(), s);
                    if (fh.f != null) {
                        previousHeader.h = fh.f;
                    }
                    if (fh.x != null) {
                        previousHeader.cc = fh.x;
                    }
                    previousHeader.v = h;
                    previousHeader.w = validPageNumber;
                    previousHeader.ac = g;
                    previousHeader.ad = g;
                    previousHeader.cs = this.subPage;
                }
                finally {}
            }
            catch (UnsatisfiedLinkError unsatisfiedLinkError) {
                unsatisfiedLinkError.printStackTrace();
                previousHeader = null;
            }
            catch (Exception ex) {
                ex.printStackTrace();
                previousHeader = null;
            }
            this.logTraceFilter(String.valueOf(String.valueOf(new StringBuffer("Processed header in ").append(System.currentTimeMillis() - currentTimeMillis).append("ms"))));
        }
        if (docForFilename != null && previousHeader != null) {
            if (this.previousHeader != null) {
                docForFilename.b(this.previousHeader);
            }
            docForFilename.a(this.previousHeader = previousHeader);
        }
        return previousHeader;
    }
    
    private final void initPrefs(final String s) {
        try {
            if (this.prefs == null) {
                this.prefs = new p(s);
            }
        }
        catch (Exception ex) {}
    }
    
    public final int[] getPalette(final ac ac, final dx dx) {
        switch (this.pixelDepth) {
            case 1: {
                if (this.palette == null) {
                    this.palette = get8BitGraScalePalette();
                    break;
                }
                break;
            }
            case 8: {
                if (this.palette == null) {
                    this.palette = get8BitGraScalePalette();
                    break;
                }
                break;
            }
        }
        return this.palette;
    }
    
    public final df getAnnotations(final String s, final dx dx, final ad ad, final int n) throws Exception {
        return null;
    }
    
    public final void clearAbort() {
        this.setDrawingCancelled(false);
    }
    
    public final boolean isAborted() {
        return this.drawingCancelled;
    }
    
    private int getValidPageNumber(final jiFilenetColdDoc jiFilenetColdDoc, final int n, final fh fh, final ad ad) {
        int n2 = n;
        try {
            this.subPage = false;
            if (jiFilenetColdDoc != null) {
                if (fh != null) {
                    final String string = fh.a.toString();
                    if (string != null) {
                        if (string.toString().indexOf("#") >= 0) {
                            n2 = d.b0(string);
                            this.subPage = true;
                        }
                        else {
                            try {
                                if (ad.e0()) {
                                    n2 = 1;
                                    this.subPage = true;
                                }
                            }
                            catch (Exception ex) {}
                        }
                    }
                }
                if (!jiFilenetColdDoc.g()) {
                    n2 = Math.min(n2, jiFilenetColdDoc.h());
                }
            }
        }
        catch (Exception ex2) {}
        return n2;
    }
    
    public final void fillDib(final fh fh, final String s) throws Exception {
        this.fillDibAction(fh, s);
    }
    
    private void fillDibAction(final fh fh, final String s) throws Exception {
        if (this.isDrawingCancelled()) {
            return;
        }
        final long currentTimeMillis = System.currentTimeMillis();
        final jiFilenetColdDoc docForFilename = this.getDocForFilename(fh);
        try {
            this.logTraceFilter(String.valueOf(String.valueOf(new StringBuffer("open doc ").append(System.currentTimeMillis() - currentTimeMillis).append("ms"))));
            if (docForFilename != null && fh.d.v > 0) {
                int n = 270;
                if (fh.d.v > 1) {
                    n = 271;
                }
                final String value = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(d.b(758, s)))).append(" ").append(d.b(n, s)).append("...")));
                final boolean b = fh.r && fh.j != 6;
                if (!b && !fh.q) {
                    e.ag(value);
                }
                if (fh.g != null) {
                    this.fireStatus(fh.g, new a6(this, 1, value));
                }
                final double ac = fh.d.ac;
                final n3.zo b2 = docForFilename.b(fh.d.w);
                try {
                    boolean b3 = false;
                    int n2 = 0;
                    switch (this.pixelDepth) {
                        case 1: {
                            b3 = true;
                            fh.c.b(1, false, fh.o);
                            n2 = 8;
                            break;
                        }
                        case 8: {
                            int n3 = d.k6;
                            if (e.av() || d.em()) {
                                n3 = d.k7;
                            }
                            if (fh.d.m * fh.d.n < n3 * 2) {
                                b3 = true;
                            }
                            fh.c.b(3, b3, fh.o);
                            n2 = 8;
                            break;
                        }
                        default: {
                            if (i.c(210)) {
                                fh.c.b(1, false, fh.o);
                            }
                            else {
                                fh.c.b(4, false, fh.o);
                            }
                            n2 = 8;
                            break;
                        }
                    }
                    final Rectangle rectangle = new Rectangle();
                    rectangle.x = 0;
                    rectangle.width = fh.d.m;
                    rectangle.height = fh.d.n;
                    rectangle.y = 0;
                    final double n4 = ac / 72.0;
                    if (rectangle.width == 0 || rectangle.height == 0) {
                        fh.c.e(fh.o);
                        return;
                    }
                    a6 a6 = new a6(this, 4, "0");
                    final af g = fh.g;
                    boolean b4 = false;
                    if (this.pixelDepth != 8 && d.du()) {
                        b4 = true;
                        a6 = new a6(this, 23, "0");
                        if (g != null && !b && !fh.q) {
                            this.fireStatus(g, new a6(this, 13, ""));
                        }
                    }
                    if (g != null) {
                        a6.a("0");
                        this.fireStatus(g, a6);
                    }
                    final int n5 = 0;
                    final int a7 = b2.a(n5, null, null, n2, rectangle, null, 0, ac);
                    if (a7 > -1 && !this.isDrawingCancelled()) {
                        final int m = fh.d.m;
                        final int n6 = fh.d.n;
                        final int n7 = m + 3 & 0xFFFFFFFC;
                        int n8 = d.k6;
                        if (e.av() || d.em()) {
                            n8 = d.k7 * 2;
                        }
                        if (this.pixelDepth == 1 || this.pixelDepth == 8) {
                            n8 = Math.min(a7, n8);
                        }
                        int n9 = 0;
                        int n10 = 0;
                        switch (this.pixelDepth) {
                            case 8: {
                                n10 = 1;
                                break;
                            }
                            case 24: {
                                n10 = 4;
                                break;
                            }
                            default: {
                                n10 = 4;
                                break;
                            }
                        }
                        int n11 = n8 / (m * n10);
                        final int n12 = (int)Math.ceil(n6 / n11);
                        final int n13 = n8;
                        final double n14 = 100.0f / n12;
                        double n15 = 0.0;
                        final Rectangle rectangle2 = null;
                        final Rectangle rectangle3 = new Rectangle();
                        final Rectangle rectangle4 = rectangle;
                        final Rectangle rectangle5 = new Rectangle();
                        this.setupFixedLimits(rectangle4, rectangle2, fh.d.m, rectangle5, rectangle3, 0);
                        final int n16 = 100;
                        long n17 = System.currentTimeMillis();
                        final long currentTimeMillis2 = System.currentTimeMillis();
                        final long n18 = i.d(0);
                        int n19 = 0;
                        final long currentTimeMillis3 = System.currentTimeMillis();
                        final boolean j = fh.c.j();
                        final boolean x = fh.c.x();
                        this.logTraceFilter(String.valueOf(String.valueOf(new StringBuffer("before decode ").append(currentTimeMillis3 - currentTimeMillis).append("ms"))));
                        switch (this.pixelDepth) {
                            case 1: {
                                final byte[] array = new byte[n8];
                                final byte[] array2 = new byte[n7];
                                for (int n20 = 0; n20 < n12 && !this.isDrawingCancelled(); ++n20) {
                                    if (n19 == 0 && g != null && System.currentTimeMillis() - currentTimeMillis2 > n18 && !b4) {
                                        n19 = 1;
                                        if (g != null && !b && !fh.q) {
                                            this.fireStatus(g, new a6(this, 9, value));
                                        }
                                    }
                                    this.moveBands(rectangle4, rectangle2, n6, n20, n12, rectangle5, rectangle3, 0, n4);
                                    b2.a(n5, rectangle4, rectangle3, n2, rectangle5, array, n13, ac);
                                    if (!this.isDrawingCancelled()) {
                                        for (int length = array.length, i = 0; i < length; ++i) {
                                            array[i] = (byte)(255 - (array[i] & 0xFF));
                                        }
                                        final int n21 = rectangle5.height - rectangle5.y;
                                        int n22 = 0;
                                        for (int k = 0; k < n21; ++k) {
                                            System.arraycopy(array, n22, array2, 0, n7);
                                            n22 += m;
                                            final short[] a8 = d.a(array2, d.k4, this.getPalette(fh.b, fh.d));
                                            if (j) {
                                                if (x) {
                                                    final short[] a9 = fh.c.a(a8, a8.length, n9);
                                                    final int length2 = a9.length;
                                                    final int[] array3 = new int[length2];
                                                    for (int l = 0; l < length2; ++l) {
                                                        array3[l] = a9[l];
                                                    }
                                                    fh.c.a(array3, length2, n9, false, true);
                                                }
                                                else {
                                                    final int length3 = a8.length;
                                                    final int[] array4 = new int[length3];
                                                    for (int n23 = 0; n23 < length3; ++n23) {
                                                        array4[n23] = a8[n23];
                                                    }
                                                    fh.c.a(array4, length3, n9, false, true);
                                                }
                                            }
                                            else {
                                                fh.c.a(a8, a8.length);
                                            }
                                            ++n9;
                                        }
                                        if (n9 + n11 > n6) {
                                            n11 = n6 - n9;
                                        }
                                        n15 += n14;
                                        if (g != null) {
                                            if (System.currentTimeMillis() - n17 > n16) {
                                                a6.a(String.valueOf((int)n15));
                                                this.fireStatus(g, a6);
                                            }
                                            n17 = System.currentTimeMillis();
                                        }
                                    }
                                }
                                break;
                            }
                            case 8: {
                                Object ag = null;
                                if (b3) {
                                    ag = fh.c.ag();
                                }
                                final byte[] array5 = new byte[n13];
                                fh.c.a(this.getPalette(fh.b, fh.d), fh.d);
                                int n24 = 0;
                                for (int n25 = 0; n25 < n12 && !this.isDrawingCancelled(); ++n25) {
                                    if (n19 == 0 && g != null && System.currentTimeMillis() - currentTimeMillis2 > n18 && !b4) {
                                        n19 = 1;
                                        if (g != null && !b && !fh.q) {
                                            this.fireStatus(g, new a6(this, 9, value));
                                        }
                                    }
                                    this.moveBands(rectangle4, rectangle2, n6, n25, n12, rectangle5, rectangle3, 0, n4);
                                    int a10 = b2.a(n5, rectangle4, rectangle3, n2, rectangle5, array5, n13, ac);
                                    if (!this.isDrawingCancelled()) {
                                        if (b3) {
                                            if (a10 >= array5.length) {
                                                a10 = array5.length - 1;
                                            }
                                            System.arraycopy(array5, 0, ag, n24, (n24 + a10 < ag.length) ? a10 : (ag.length - n24));
                                        }
                                        else {
                                            if (a10 >= array5.length) {
                                                a10 = array5.length - 1;
                                            }
                                            fh.c.b(array5, a10, fh.o, n9, n9, true);
                                        }
                                        n24 += a10;
                                        n15 += n14;
                                        if (g != null && n12 < 100) {
                                            if (System.currentTimeMillis() - n17 > n16 || b4) {
                                                a6.a(String.valueOf((int)n15));
                                                this.fireStatus(g, a6);
                                            }
                                            n17 = System.currentTimeMillis();
                                        }
                                    }
                                }
                                break;
                            }
                            default: {
                                if (i.c(87)) {
                                    fh.c.e(true);
                                }
                                final long n26 = 0L;
                                Label_2748: {
                                    if (this.COMPRESSED_WRITE) {
                                        b2.a();
                                        try {
                                            final long currentTimeMillis4 = System.currentTimeMillis();
                                            int a11;
                                            while ((a11 = b2.a(n5, rectangle4, n2, new byte[n13], n13, ac, 1)) > -1) {
                                                if (n19 == 0 && g != null && System.currentTimeMillis() - currentTimeMillis2 > n18 && !b4) {
                                                    n19 = 1;
                                                    if (g != null && !b && !fh.q) {
                                                        this.fireStatus(g, new a6(this, 9, value));
                                                    }
                                                }
                                                b2.d();
                                                b2.e();
                                                if (g != null && n12 < 100) {
                                                    if (System.currentTimeMillis() - n17 > n16 || b4) {
                                                        a6.a(String.valueOf(b2.f()));
                                                        this.fireStatus(g, a6);
                                                    }
                                                    n17 = System.currentTimeMillis();
                                                }
                                            }
                                            final long n27 = n26 + (System.currentTimeMillis() - currentTimeMillis4);
                                            break Label_2748;
                                        }
                                        finally {
                                            b2.b();
                                        }
                                    }
                                    b2.a();
                                    try {
                                        final long currentTimeMillis5 = System.currentTimeMillis();
                                        final byte[] array6 = new byte[n13];
                                        for (int n28 = 0; n28 < n12 && !this.isDrawingCancelled(); ++n28) {
                                            if (n19 == 0 && g != null && System.currentTimeMillis() - currentTimeMillis2 > n18 && !b4) {
                                                n19 = 1;
                                                if (g != null && !b && !fh.q) {
                                                    this.fireStatus(g, new a6(this, 9, value));
                                                }
                                            }
                                            this.moveBands(rectangle4, rectangle2, n6, n28, n12, rectangle5, rectangle3, 0, n4);
                                            final int a12 = b2.a(n5, rectangle4, rectangle3, n2, rectangle5, array6, n13, ac);
                                            final int n29 = rectangle5.height - rectangle5.y;
                                            if (!this.isDrawingCancelled()) {
                                                if (i.c(210)) {
                                                    final int n30 = a12 / 4;
                                                    final int[] array7 = new int[n30];
                                                    final int[] array8 = new int[m];
                                                    for (int n31 = 0, n32 = 0; n31 < n30; ++n31, n32 += 4) {
                                                        array7[n31] = (255 - (array6[n32] & 0xFF) << 24 | 255 - (array6[n32 + 1] & 0xFF) << 16 | 255 - (array6[n32 + 2] & 0xFF) << 8 | 255 - (array6[n32 + 3] & 0xFF));
                                                    }
                                                    int n33 = 0;
                                                    for (int n34 = 0; n34 < n29; ++n34) {
                                                        System.arraycopy(array7, n33, array8, 0, Math.min(m, array6.length - n33));
                                                        n33 += m;
                                                        final short[] a13 = d.a(array8, d.k5);
                                                        if (j) {
                                                            if (x) {
                                                                final short[] a14 = fh.c.a(a13, a13.length, n9);
                                                                final int length4 = a14.length;
                                                                final int[] array9 = new int[length4];
                                                                for (int n35 = 0; n35 < length4; ++n35) {
                                                                    array9[n35] = a14[n35];
                                                                }
                                                                fh.c.a(array9, length4, n9, false, true);
                                                            }
                                                            else {
                                                                final int length5 = a13.length;
                                                                final int[] array10 = new int[length5];
                                                                for (int n36 = 0; n36 < length5; ++n36) {
                                                                    array10[n36] = a13[n36];
                                                                }
                                                                fh.c.a(array10, length5, n9, false, true);
                                                            }
                                                        }
                                                        else {
                                                            fh.c.a(a13, a13.length);
                                                        }
                                                    }
                                                }
                                                else {
                                                    fh.c.a(array6, a12, fh.o, n9 + 1, n9 + n29 + 1, true);
                                                }
                                                n9 += n29;
                                                n15 += n14;
                                                if (g != null && n12 < 100) {
                                                    if (System.currentTimeMillis() - n17 > n16 || b4) {
                                                        a6.a(String.valueOf((int)n15));
                                                        this.fireStatus(g, a6);
                                                    }
                                                    n17 = System.currentTimeMillis();
                                                }
                                            }
                                        }
                                        final long n37 = n26 + (System.currentTimeMillis() - currentTimeMillis5);
                                    }
                                    finally {
                                        b2.b();
                                    }
                                }
                                this.logTraceFilter(String.valueOf(String.valueOf(new StringBuffer("after decode ").append(System.currentTimeMillis() - currentTimeMillis3).append("ms"))));
                                break;
                            }
                        }
                        if (g != null) {
                            a6.a("100");
                            this.fireStatus(g, a6);
                        }
                    }
                }
                catch (Exception ex) {
                    ex.printStackTrace();
                }
                finally {
                    b2.c();
                    e.ag(null);
                }
            }
            if (this.pixelDepth == 8 && !fh.c.g()) {
                fh.c.e(fh.o);
            }
            else if (this.pixelDepth == 24) {
                fh.c.e(fh.o);
            }
        }
        catch (Exception ex2) {
            if (d.cy()) {
                ex2.printStackTrace();
            }
        }
        finally {
            if (fh.g != null) {
                this.fireStatus(fh.g, new a6(this, 8, ""));
                if (!fh.q) {
                    this.fireStatus(fh.g, new a6(this, 13, ""));
                }
            }
        }
    }
    
    private void setupFixedLimits(final Rectangle rectangle, final Rectangle rectangle2, final int width, final Rectangle rectangle3, final Rectangle rectangle4, final int n) {
        rectangle3.x = 0;
        rectangle3.width = width;
        if (n == 0) {
            final int x = rectangle.x;
            final int width2 = rectangle.width;
            rectangle4.x = x;
            rectangle4.width = width2;
        }
    }
    
    private void moveBands(final Rectangle rectangle, final Rectangle rectangle2, final int height, final int n, final int n2, final Rectangle rectangle3, final Rectangle rectangle4, final int n3, final double n4) {
        final int n5 = (int)Math.ceil(height / n2);
        rectangle3.y = n5 * n;
        rectangle3.height = n5 * (n + 1);
        if (rectangle3.height > height) {
            rectangle3.height = height;
        }
        final double n6 = height / rectangle3.height;
        final double n7 = height / rectangle3.y;
        if (n3 == 0) {
            final int n8 = rectangle.height - rectangle.y;
            final int n9 = (int)Math.floor(n8 - n8 * 1 / n6);
            final int n10 = (int)Math.ceil(n8 - n8 * 1 / n7);
            final int y = rectangle.y;
            rectangle4.height = rectangle.y + n10;
            rectangle4.y = rectangle.y + n9;
            if (rectangle4.y < y) {
                rectangle4.y = y;
            }
        }
    }
    
    private void fireListener(final af af, final int n, final int n2) {
    }
    
    private static int[] get8BitGraScalePalette() {
        final int[] array = new int[256];
        for (int i = 0; i < 256; ++i) {
            array[i] = (0xFF000000 | i << 16 | i << 8 | i);
        }
        return array;
    }
    
    public final boolean releaseResources(final String s) {
        this.prefs = null;
        return true;
    }
    
    public final void close(final dx dx) throws Exception {
        if (dx != null) {
            final String h = dx.h;
            this.logTraceFilter("jiFilterColdDecoder asked to close ".concat(String.valueOf(String.valueOf(h))));
            if (h != null && jiFilenetColdDecoder.openDocs.containsKey(h)) {
                final jiFilenetColdDoc jiFilenetColdDoc = jiFilenetColdDecoder.openDocs.get(h);
                jiFilenetColdDoc.b(dx);
                jiFilenetColdDoc.c(this);
                this.logTraceFilter("jiFilterColdDecoder client count = ".concat(String.valueOf(String.valueOf(jiFilenetColdDoc.c()))));
                if (jiFilenetColdDoc.c() == 0) {
                    this.logTraceFilter("jiFilterColdDecoder asked to close ".concat(String.valueOf(String.valueOf(h))));
                    this.logTraceFilter("jiFilterColdDecoder COMPLETELY CLOSING ".concat(String.valueOf(String.valueOf(h))));
                    jiFilenetColdDoc.c((dx)null);
                    jiFilenetColdDecoder.openDocs.remove(h);
                }
            }
        }
    }
    
    private void setDrawingCancelled(final boolean drawingCancelled) {
        synchronized (this) {
            this.drawingCancelled = drawingCancelled;
        }
    }
    
    private boolean isDrawingCancelled() {
        synchronized (this) {
            return this.drawingCancelled;
        }
    }
    
    public final void abort(final dx dx) throws Exception {
        this.logTraceFilter("abort called");
        try {
            this.setDrawingCancelled(true);
            synchronized (this.PROCESSING_LOCK) {
                final Enumeration<jiFilenetColdDoc> elements = jiFilenetColdDecoder.openDocs.elements();
                while (elements.hasMoreElements()) {
                    final jiFilenetColdDoc jiFilenetColdDoc = elements.nextElement();
                    if (jiFilenetColdDoc != null && jiFilenetColdDoc.a(this)) {
                        jiFilenetColdDoc.d(dx);
                    }
                }
            }
            // monitorexit(this.PROCESSING_LOCK)
        }
        catch (Exception ex) {
            if (d.cy()) {
                ex.printStackTrace();
            }
            this.logTraceFilter("Could not abort");
        }
    }
    
    private boolean addFontResource(final Component component, final String s, final String s2, final boolean b) {
        boolean b2;
        if (e.x(s)) {
            b2 = this.jiex2.a(s, component, s2, b);
        }
        else {
            b2 = this.jiex1.a(component, s, s2, b);
        }
        return b2;
    }
    
    private boolean removeFontResource(final Component component, final String s, final String s2, final boolean b) {
        boolean b2;
        if (e.x(s)) {
            b2 = this.jiex2.b(s, component, s2, b);
        }
        else {
            b2 = this.jiex1.b(component, s, s2, b);
        }
        return b2;
    }
    
    public final boolean isDecoderLoaded() {
        synchronized (jiFilenetColdDecoder.librariesLoaded) {
            // monitorexit(jiFilenetColdDecoder.librariesLoaded)
            return jiFilenetColdDecoder.librariesLoaded.a;
        }
    }
    
    public final boolean loadFailedDueToPermissions() {
        return jiFilenetColdDecoder.permissionsFailureOnLoad;
    }
    
    private void logAutoUpdateText(final String s) {
        this.logText("Auto-Update, ".concat(String.valueOf(String.valueOf(s))));
    }
    
    private void logPrefixedText(final String s) {
        this.logText("Filenet COLD DLL: ".concat(String.valueOf(String.valueOf(s))));
    }
    
    private void logText(final String s) {
        h.d(this.parentId, s);
    }
    
    private void logTraceFilter(final String s) {
        if (i.c(5)) {
            this.logPrefixedText(s);
        }
    }
    
    static {
        dllWinFilenetCOLD = new String[] { "pixpermn", "pixlocn", "pixdfltn", "pixmdln", "pixtiffn", "pixmpn", "pixn20", "IDMColdRegistry", "f4appinf", "f4cdcd", "f4dfm", "f4util", "f4displb", "f4inttrn", "f4pcodlb", "f4tiff", "idmcold", "jicold" };
        fontFiles = new String[] { "aa000wlp.fon", "ab000wlp.fon", "ac000wlp.fon", "ai000wlp.fon", "aj000wlp.fon", "ak000wlp.fon", "bs000wfp.fon", "cq000wlp.fon", "f1000wlp.fon", "f2000wlp.fon", "f3000wlp.fon", "f4000wlp.fon", "f4000wlp.fon" };
        pixtranFiles = new String[] { "PIXBMP.PXJ", "PIXFNET.PXJ", "PIXIFTIF.PXJ", "PIXJPEG.PXJ", "PIXPCX.PXJ" };
        jiFilenetColdDecoder.librariesLoaded = new zs(null);
        jiFilenetColdDecoder.permissionsFailureOnLoad = false;
        jiFilenetColdDecoder.fontsAddedNotInstalled = false;
        jiFilenetColdDecoder.openDocs = new Hashtable();
    }
    
    private static class zs
    {
        boolean a;
        String b;
        
        private zs() {
            this.a = false;
            this.b = null;
        }
    }
    
    interface aei
    {
    }
}

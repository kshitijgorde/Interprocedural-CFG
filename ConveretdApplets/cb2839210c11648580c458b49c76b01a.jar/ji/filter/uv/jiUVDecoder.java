// 
// Decompiled by Procyon v0.5.30
// 

package ji.filter.uv;

import java.net.MalformedURLException;
import java.net.URL;
import java.io.File;
import java.util.Enumeration;
import ji.filter.dll.n3;
import ji.filter.n7;
import java.awt.Dimension;
import ji.annotate.dg;
import ji.util.n8;
import ji.util.gq;
import java.awt.Rectangle;
import java.io.FilenameFilter;
import ji.sec.u;
import ji.ext.n;
import ji.ext.w;
import ji.zip.a4;
import ji.util.y;
import ji.v1event.a6;
import ji.res.z;
import ji.util.cn;
import ji.io.fb;
import ji.res.s;
import ji.util.e;
import ji.res.aa;
import java.awt.Component;
import ji.res.ay;
import ji.io.ac;
import ji.util.d;
import ji.sec.g;
import ji.document.ad;
import ji.filter.dll.n0;
import ji.v1event.am;
import ji.io.h;
import ji.util.i;
import ji.filter.fh;
import java.util.Hashtable;
import ji.ext.v;
import ji.io.q;
import ji.image.dx;
import ji.io.p;
import ji.annotate.df;
import ji.v1event.af;

public class jiUVDecoder implements nz
{
    boolean COMPRESSED_WRITE;
    private int loadFileIDLibraryAttempt;
    private int loadViewingTechnologyLibraryAttempt;
    private static final String JNI_Library = "jiuv";
    private static final String librarySubdir = "uv";
    private static n1 outsideInLibraries;
    private static final boolean useExternalLoadLibrary = true;
    private static final int supportedPlatforms = 1;
    private af listener;
    private boolean thumbnail;
    df annotations;
    private p prefs;
    private int[] palette;
    private int pixelDepth;
    private boolean drawingCancelled;
    private boolean openCancelled;
    private boolean invalidPassword;
    private boolean subPage;
    private dx previousHeader;
    private String parentId;
    private q fileCache;
    private v jiex1;
    private static zq loadedLibraries;
    private boolean isInDocTypeAvoidList;
    private int[] idsToAvoid;
    private int[] textIDs;
    private int[] htmlIDs;
    private static Hashtable openDocs;
    
    private jiUVDoc getDocForFilename(final fh fh) {
        if (!this.loadLibraries(fh.o, fh.u, fh.g, !fh.q && !fh.r)) {
            return null;
        }
        synchronized (jiUVDecoder.openDocs) {
            try {
                String s = fh.f;
                if (s == null) {
                    s = fh.d.h;
                }
                if (s == null) {
                    // monitorexit(jiUVDecoder.openDocs)
                    return null;
                }
                jiUVDoc jiUVDoc2;
                if (jiUVDecoder.openDocs.containsKey(s)) {
                    jiUVDoc2 = jiUVDecoder.openDocs.get(s);
                    jiUVDoc2.a(true);
                }
                else {
                    jiUVDoc2 = new jiUVDoc(fh.u);
                    boolean b = true;
                    if (fh.o != null && fh.o.e0() && !this.hashPage(fh, fh.o)) {
                        b = false;
                    }
                    final String unobfuscatedFile = this.getUnobfuscatedFile(fh.o, fh.e, (fh.d != null) ? fh.d.f : null, s, fh.g, fh.n);
                    if (unobfuscatedFile == null) {
                        // monitorexit(jiUVDecoder.openDocs)
                        return null;
                    }
                    if (jiUVDoc2.a(unobfuscatedFile, b, i.c(220))) {
                        jiUVDecoder.openDocs.put(s, jiUVDoc2);
                    }
                    else {
                        h.d(this.parentId, "Universal: Could not open document ".concat(String.valueOf(String.valueOf(s))));
                        jiUVDoc2.c((dx)null);
                        jiUVDoc2 = null;
                    }
                }
                if (jiUVDoc2 != null) {
                    jiUVDoc2.a(fh.o);
                    jiUVDoc2.b(this);
                }
                // monitorexit(jiUVDecoder.openDocs)
                return jiUVDoc2;
            }
            catch (Throwable t) {
                h.c(fh.u, "Universal: Exception occured locating document ".concat(String.valueOf(String.valueOf(t.getMessage()))));
                // monitorexit(jiUVDecoder.openDocs)
                return null;
            }
        }
    }
    
    private String getUnobfuscatedFile(final ad ad, final String s, final String s2, final String s3, final af af, final String s4) throws Exception {
        String s5 = s3;
        g.b(s5, this.parentId);
        int c2 = d.c2() ? 1 : 0;
        if (c2 != 0 && s != null && !d.bj(s)) {
            c2 = 0;
        }
        if (ac.a(this.parentId, ad, s5)) {
            c2 = 1;
        }
        String s6 = s;
        if (s6 == null && s2 != null) {
            s6 = s2;
        }
        final long cq = d.cq(s6);
        if (c2 != 0) {
            if (!i.c(213)) {
                final String a = this.fileCache.a(cq);
                if (!ac.d(a, this.parentId)) {
                    ac.a(s3, a, null, this.parentId);
                }
                s5 = a;
            }
            else {
                h.d(this.parentId, d.b(1244, this.parentId));
                h.d(this.parentId, "Use the obfuscateUV HTML parameter set to false to override this security setting.");
                d.a(ay.a(), d.f(d.b(1244, this.parentId), 70), ad, 60, null, af, this.parentId);
                this.drawingCancelled = true;
                s5 = null;
            }
        }
        if (s5 != null && i.c(293)) {
            final boolean endsWith = s5.endsWith(".ps");
            if ("application/postscript".equalsIgnoreCase(s4) && !endsWith) {
                final String a2 = this.fileCache.a("ps", cq);
                ac.a(s5, a2, null, this.parentId);
                s5 = a2;
            }
        }
        return s5;
    }
    
    private boolean loadLibraries(final ad ad, final String s, final af af, final boolean b) {
        boolean b2 = true;
        synchronized (jiUVDecoder.loadedLibraries) {
            b2 = this.loadFileIDLibraries(ad, s, af, b);
            if (b2) {
                this.loadViewingTechnologyLibraries(ad, s, af, b);
            }
            b2 = jiUVDecoder.loadedLibraries.a;
        }
        // monitorexit(jiUVDecoder.loadedLibraries)
        return b2;
    }
    
    private String checkModuleVersion(final Object o, final String s, final String s2, final int n) {
        boolean b = false;
        String c;
        try {
            c = aa.c(s);
            if (d.by(c)) {
                c = "<version undetermined>";
                b = true;
                h.c(s, String.valueOf(String.valueOf(s2)).concat(": Current version can't be determined"));
            }
            else {
                h.c(s, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(s2))).append(": Current version: ").append(c))));
            }
        }
        catch (Exception ex) {
            c = "<version undetermined>";
            h.c(s, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(s2))).append(": Current version can't be determined due to ").append(ex.getMessage()))));
        }
        try {
            if (!b) {
                final int viewerVersion = jiUVDecoder.loadedLibraries.c.ViewerVersion(e.u(), 1);
                final int viewerVersion2 = jiUVDecoder.loadedLibraries.c.ViewerVersion(e.u(), 2);
                final int viewerVersion3 = jiUVDecoder.loadedLibraries.c.ViewerVersion(e.u(), 3);
                if (!aa.a(s2, viewerVersion, viewerVersion2, viewerVersion3, o, s, false)) {
                    final String value = String.valueOf(String.valueOf(new StringBuffer("").append(viewerVersion).append(".").append(viewerVersion2).append(".").append(viewerVersion3)));
                    final String value2 = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(s2))).append(": ").append(d.b(d.b(s.a(1291, s), "<VERSIONSERVER>", value), "<VERSIONLOCAL>", c))));
                    h.c(s, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(s2))).append(": Version ").append(value).append(" of the module has been found on the server. Should be using version ").append(c))));
                    return value2;
                }
            }
            else {
                h.c(s, String.valueOf(String.valueOf(s2)).concat(": Module Version Number Not checked"));
            }
        }
        catch (UnsatisfiedLinkError unsatisfiedLinkError) {
            final String value3 = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(s2))).append(": ").append(d.b(s.a(1292, s), "<VERSIONLOCAL>", c))));
            h.c(s, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(s2))).append(": An old version of the module has been found on the server. Should be using version ").append(c))));
            return value3;
        }
        catch (Exception ex2) {
            final String concat = String.valueOf(String.valueOf(s2)).concat(": Problem occurred checking version: ");
            String s3;
            if (ex2.getMessage() != null) {
                s3 = String.valueOf(String.valueOf(concat)).concat(String.valueOf(String.valueOf(ex2.getMessage())));
            }
            else {
                s3 = String.valueOf(String.valueOf(concat)).concat(String.valueOf(String.valueOf(ex2.getClass().getName())));
            }
            h.c(s, s3);
            return s3;
        }
        return null;
    }
    
    private String checkLibraryVersion(final Object o, final String s, final String s2, final int n) {
        final String a = jiUVDecoder.outsideInLibraries.a();
        try {
            int n2;
            int n3;
            int n4;
            int n5;
            if (n == 1) {
                n2 = jiUVDecoder.loadedLibraries.c.libraryVersionFileId(1);
                n3 = jiUVDecoder.loadedLibraries.c.libraryVersionFileId(2);
                n4 = jiUVDecoder.loadedLibraries.c.libraryVersionFileId(3);
                n5 = jiUVDecoder.loadedLibraries.c.libraryVersionFileId(4);
            }
            else {
                n2 = jiUVDecoder.loadedLibraries.c.libraryVersionViewingTechnology(1);
                n3 = jiUVDecoder.loadedLibraries.c.libraryVersionViewingTechnology(2);
                n4 = jiUVDecoder.loadedLibraries.c.libraryVersionViewingTechnology(3);
                n5 = jiUVDecoder.loadedLibraries.c.libraryVersionViewingTechnology(4);
            }
            final boolean a2 = jiUVDecoder.outsideInLibraries.a(n2, n3, n4, n5);
            final String value = String.valueOf(String.valueOf(new StringBuffer("").append(n2).append(".").append(n3).append(".").append(n4).append(".").append(n5)));
            if (!a2) {
                final String value2 = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(s2))).append(": ").append(d.b(d.b(s.a(1291, s), "<VERSIONSERVER>", value), "<VERSIONLOCAL>", a))));
                h.c(s, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(s2))).append(": Version ").append(value).append(" of the module has been found on the server. Should be using version ").append(a))));
                return value2;
            }
            h.c(s, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(s2))).append(" Library Version installed: ").append(value))));
        }
        catch (UnsatisfiedLinkError unsatisfiedLinkError) {
            final String value3 = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(s2))).append(": ").append(d.b(s.a(1292, s), "<VERSIONLOCAL>", a))));
            h.c(s, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(s2))).append(": An old version of the module has been found on the server. Should be using version ").append(a))));
            return value3;
        }
        catch (Exception ex) {
            final String concat = String.valueOf(String.valueOf(s2)).concat(": Problem occurred checking version: ");
            String s3;
            if (ex.getMessage() != null) {
                s3 = String.valueOf(String.valueOf(concat)).concat(String.valueOf(String.valueOf(ex.getMessage())));
            }
            else {
                s3 = String.valueOf(String.valueOf(concat)).concat(String.valueOf(String.valueOf(ex.getClass().getName())));
            }
            h.c(s, s3);
            return s3;
        }
        return null;
    }
    
    private boolean loadFileIDLibraries(final ad ad, final String s, final af af, final boolean b) {
        boolean b2 = true;
        try {
            synchronized (jiUVDecoder.loadedLibraries) {
                if (b2 && !jiUVDecoder.loadedLibraries.b) {
                    jiUVDecoder.loadedLibraries.b = this.loadFileIDExternalLibraries(ad, s, af, b);
                    if (this.isDrawingCancelled()) {
                        jiUVDecoder.loadedLibraries.b = false;
                    }
                    b2 = jiUVDecoder.loadedLibraries.b;
                }
                b2 = this.loadUVJNIExternalLibrary(ad, s, af);
                if (jiUVDecoder.loadedLibraries.b && jiUVDecoder.loadedLibraries.c == null && b2) {
                    jiUVDecoder.loadedLibraries.c = new jiUVDLL();
                    try {
                        if (i.c(254)) {
                            final String checkModuleVersion = this.checkModuleVersion(ad, s, "Universal File ID", 1);
                            if (!d.by(checkModuleVersion)) {
                                throw new fb(checkModuleVersion);
                            }
                            final String checkLibraryVersion = this.checkLibraryVersion(ad, s, "Universal File ID", 1);
                            if (!d.by(checkLibraryVersion)) {
                                throw new fb(checkLibraryVersion);
                            }
                        }
                        if (!jiUVDecoder.loadedLibraries.c._initFileIDLib()) {
                            jiUVDecoder.loadedLibraries.c = null;
                            jiUVDecoder.loadedLibraries.b = false;
                            b2 = false;
                        }
                    }
                    catch (Exception ex) {
                        jiUVDecoder.loadedLibraries.c = null;
                        jiUVDecoder.loadedLibraries.b = false;
                        throw ex;
                    }
                }
            }
            // monitorexit(jiUVDecoder.loadedLibraries)
        }
        catch (Exception ex2) {
            h.c(s, "Universal: Exception occured loading UV Libraries ".concat(String.valueOf(String.valueOf(ex2.getMessage()))));
        }
        return b2;
    }
    
    private boolean loadViewingTechnologyLibraries(final ad ad, final String s, final af af, final boolean b) {
        boolean b2 = true;
        try {
            synchronized (jiUVDecoder.loadedLibraries) {
                boolean a = jiUVDecoder.loadedLibraries.a;
                b2 = jiUVDecoder.loadedLibraries.a;
                if (!a) {
                    a = this.loadViewingTechnologyExternalLibraries(ad, s, af, b);
                    if (this.isDrawingCancelled()) {
                        a = false;
                    }
                }
                if (a) {
                    a = this.loadUVJNIExternalLibrary(ad, s, af);
                    if (i.c(254) && a) {
                        final String checkModuleVersion = this.checkModuleVersion(ad, s, "Universal Viewing", 2);
                        if (!d.by(checkModuleVersion)) {
                            throw new fb(checkModuleVersion);
                        }
                        final String checkLibraryVersion = this.checkLibraryVersion(ad, s, "Universal Viewing", 2);
                        if (!d.by(checkLibraryVersion)) {
                            this.removeDecoder(ad, s);
                            throw new fb(checkLibraryVersion);
                        }
                    }
                }
                jiUVDecoder.loadedLibraries.a = a;
                b2 = jiUVDecoder.loadedLibraries.a;
            }
            // monitorexit(jiUVDecoder.loadedLibraries)
        }
        catch (Exception ex) {
            h.c(s, "Universal: Exception occured loading UV Libraries ".concat(String.valueOf(String.valueOf(ex.getMessage()))));
        }
        return b2;
    }
    
    public jiUVDecoder() {
        this.COMPRESSED_WRITE = false;
        this.loadFileIDLibraryAttempt = 0;
        this.loadViewingTechnologyLibraryAttempt = 0;
        this.listener = null;
        this.thumbnail = false;
        this.annotations = null;
        this.prefs = null;
        this.palette = null;
        this.pixelDepth = 24;
        this.openCancelled = false;
        this.invalidPassword = false;
        this.subPage = false;
        this.previousHeader = null;
        this.isInDocTypeAvoidList = false;
        this.idsToAvoid = new int[] { 1500, 1501, 1503, 1504, 1514, 1535, 1557, 1574, 1584, 1599, 1602, 1639, 1646, 1999, 4001, 4006 };
        this.textIDs = new int[] { 1170, 1184, 4000, 4002, 4003, 4004, 4005, 4007, 4008, 4009, 4010, 4011, 4012, 4013, 4014, 4015, 4016, 4017, 4018, 4019, 4020, 4021, 4022, 4023, 4024, 4025, 4026, 4027, 4028, 4029, 4030, 4031, 4032, 4033, 4034, 4035, 4036, 4037, 4050, 4051, 4052, 4053, 4054, 4055, 4056, 4057, 4058, 4059, 4060, 4061, 4062, 4063, 4064, 4065, 4066, 4067, 4068, 4069, 4070, 4071, 4072, 4073, 4074, 4075, 4076, 4077, 4078, 4079, 4080, 4081, 4082, 4083, 4084, 4085, 4086, 4087, 4088, 4089, 4090, 4091, 4092, 4093 };
        this.htmlIDs = new int[] { 1101, 1109, 1110, 1111, 1112, 1113, 1114, 1115, 1116, 1117, 1147, 1148, 1149, 1153, 1172, 1173, 1174, 1175, 1176, 1178, 1181, 1182, 1190, 1198, 1199, 4038, 4039, 4040, 4041, 4042, 4043, 4044, 4045, 4046, 4047, 4048, 4049, 4094, 4095, 4096, 4097, 4098, 4099, 4100, 4101, 4102, 4103, 4104, 4105, 4106, 4107, 4108, 4109, 4110, 4111, 4112, 4113, 4114, 4115, 4116, 4117, 4118, 4119, 4120, 4121, 4122, 4123, 4124, 4125, 4126, 4127, 4128, 4129, 4130, 4131, 4132 };
        this.jiex1 = new v();
    }
    
    public final void setParentId(final String parentId) {
        this.parentId = parentId;
        if (this.fileCache == null) {
            this.fileCache = q.a((Object)null, parentId);
        }
    }
    
    private final boolean loadFileIDExternalLibraries(final ad ad, final String s, final af af, final boolean b) {
        ++this.loadFileIDLibraryAttempt;
        final String concat = String.valueOf(String.valueOf(jiUVDecoder.outsideInLibraries.c())).concat(": ");
        final String[] a = jiUVDecoder.outsideInLibraries.a(s);
        final String b2 = jiUVDecoder.outsideInLibraries.b();
        boolean b3 = true;
        try {
            h.c(s, String.valueOf(String.valueOf(concat)).concat("Loading libaries..."));
            boolean b4 = false;
            final String b5 = d.b(1289, s);
            final String value = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(d.b(708, s)))).append(" ").append(d.b(1290, s))));
            final String value2 = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(d.b(709, s)))).append(" ").append(d.b(1290, s))));
            e.ag(b5);
            final boolean a2 = cn.a(ad, s, af, "uv", d.c(a), b5, false, true, false);
            if (!a2) {
                this.removeFileIDDecoder(ad, s);
            }
            h.c(s, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(concat))).append("Current library state: ").append(a2))));
            this.initPrefs(s);
            boolean b6 = false;
            final boolean d = this.prefs.d(ad);
            if (a2 && !d) {
                b6 = true;
            }
            if (!a2) {
                if (ji.util.d.b2() > 1 && this.loadFileIDLibraryAttempt > 1) {
                    ji.util.d.a(ay.a(), String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(ji.util.d.b(1198, s)))).append("\n\n").append(ji.util.d.b(1199, s)))), ad, 120, null, af, s);
                    return false;
                }
                e.a(true, 708, null, null);
                h.c(s, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(concat))).append("loading ").append(b2).append("..."))));
                String s2 = ji.util.d.b(b2, ji.util.d.bh(b2), "v1");
                String a3 = null;
                String s3 = z.a(ad, b2, s2, af, s, ad);
                if (s3 != null) {
                    s3 = ac.b(s3, s);
                }
                if (s3 != null) {
                    try {
                        if (ac.a(s3, s) <= 0) {
                            try {
                                ac.c(s3, s);
                            }
                            catch (Exception ex3) {}
                            s3 = null;
                        }
                    }
                    catch (Exception ex4) {}
                }
                if (s3 == null) {
                    e.ag(value);
                    this.fireStatus(af, new a6(this, 1, value));
                    if (b) {
                        this.fireStatus(af, new a6(this, 9, value));
                    }
                    final String b7 = ac.b(z.a(s), s);
                    final String b8 = ji.util.d.b(b2, ji.util.d.bh(b2), "v1");
                    final String value3 = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(b7))).append("/").append(b8)));
                    final String value4 = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(ji.util.d.b(726, s)))).append("\n").append(ji.util.d.b(727, s)).append("\n").append("\n").append(ji.util.d.b(728, s)).append("\n").append(ji.util.d.b(729, s)).append("\n").append(ji.util.d.b(730, s))));
                    h.c(s, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(concat))).append("downloading ").append(s2).append(" to ").append(b7).append("..."))));
                    h.d(s, String.valueOf(String.valueOf(new StringBuffer("Auto-Update, downloading Universal Viewing library ").append(s2).append(" to ").append(b7).append("..."))));
                    z.a(b8, e.an(), 3, af, ad, s, false, value3, value4, false, new y(), false);
                    a3 = z.a(ad, s2, s2, af, s, ad);
                    if (a3 != null) {
                        e.ag(value2);
                        if (af != null) {
                            af.a(new a6(this, 1, value2));
                        }
                        if (b) {
                            this.fireStatus(af, new a6(this, 9, value2));
                        }
                        this.fireStatus(af, new a6(this, 4, "0"));
                        s3 = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(b7))).append("/").append(b2)));
                        ji.util.d.a(a3, s3, b8, true, ad, s, af);
                    }
                    e.ag(b5);
                    if (s3 != null) {
                        final long a4 = ac.a(s3, s);
                        if (a4 > 0) {
                            s3 = ac.b(s3, s);
                            h.d(s, String.valueOf(String.valueOf(new StringBuffer("Auto-Update, #11 downloaded (").append(ji.util.d.a(a4, s)).append(")..."))));
                        }
                        else {
                            try {
                                this.removeFileIDDecoder(ad, s);
                                ac.c(s3, s);
                            }
                            catch (Exception ex5) {}
                            s2 = String.valueOf(String.valueOf(e.am())).concat(String.valueOf(String.valueOf(s2)));
                            h.d(s, String.valueOf(String.valueOf(new StringBuffer("Auto-Update, file not found! (").append(s2).append(")..."))));
                            ji.util.d.a(ay.a(), String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(s2))).append("\n ----- ").append(ji.util.d.b(260, s)).append(" -----"))), ad, 60, null, af, s);
                            b3 = false;
                            s3 = null;
                        }
                    }
                }
                if (s3 != null) {
                    h.d(s, String.valueOf(String.valueOf(new StringBuffer("Auto-Update, exporting Universal Viewing library ").append(b2).append("..."))));
                    e.ag(value2);
                    this.fireStatus(af, new a6(this, 1, value2));
                    if (b) {
                        this.fireStatus(af, new a6(this, 9, value2));
                    }
                    final String a5 = cn.a("uv", s);
                    if (a4.a(s3, a5, null, true, ad, s, false, true, af, true)) {
                        if (aa.b(ad, s)) {
                            final String[] a6 = a4.a(s3, ad, s);
                            if (a6 != null && !this.isDrawingCancelled()) {
                                cn.a(ad, s, a5, a6);
                            }
                        }
                        h.d(s, "Auto-Update, expanded.");
                        b4 = true;
                    }
                    else {
                        try {
                            this.removeFileIDDecoder(ad, s);
                            ac.c(s3, s);
                        }
                        catch (Exception ex6) {}
                        h.d(s, String.valueOf(String.valueOf(new StringBuffer("Auto-Update, unable to expand ").append(b2).append("!"))));
                        b3 = false;
                    }
                    try {
                        if (s3 != null) {
                            ac.c(s3, s);
                        }
                    }
                    catch (Exception ex7) {}
                    try {
                        if (s2 != null) {
                            ac.c(ji.util.d.b(ac.b(a3, s), "\\", "/"), s);
                        }
                    }
                    catch (Exception ex8) {}
                }
                else {
                    b3 = false;
                }
            }
            h.c(s, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(concat))).append("Loaded status: ").append(b3))));
            if (b3) {
                if (cn.a(ad, s, af, "uv", a, b5, false, false, false)) {
                    try {
                        if (b6) {
                            b4 = true;
                        }
                        if (b4) {
                            h.d(s, "Auto-Update, loading Universal Viewing File ID library...");
                            e.ag(b5);
                            this.fireStatus(af, new a6(this, 1, b5));
                            if (b) {
                                this.fireStatus(af, new a6(this, 9, b5));
                            }
                        }
                        if (b3) {
                            try {
                                int n = 1;
                                final long n2 = a.length;
                                final long currentTimeMillis = System.currentTimeMillis();
                                final boolean c = i.c(220);
                                for (int n3 = 0; n3 < a.length && !this.isDrawingCancelled(); ++n3) {
                                    final String a7 = ac.a(cn.a("uv", a[n3], s));
                                    h.c(s, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(concat))).append("Load library: ").append(a7))));
                                    if (this.jiex1.a(a7, c, s, ad) == 0) {
                                        h.d(s, String.valueOf(String.valueOf(new StringBuffer("Universal: Load library failed aborting (").append(a7).append(") *****"))));
                                        b3 = false;
                                        break;
                                    }
                                    this.fireStatus(af, new a6(this, 4, (int)(100 * n++ / n2)));
                                }
                                if (b4 && this.isDrawingCancelled()) {
                                    this.removeFileIDDecoder(ad, s);
                                    b3 = false;
                                }
                                h.c(s, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(concat))).append("Load library total took: ").append(System.currentTimeMillis() - currentTimeMillis).append("ms"))));
                            }
                            catch (UnsatisfiedLinkError unsatisfiedLinkError) {
                                h.c(s, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(concat))).append("Load libraries failed ").append(unsatisfiedLinkError.getMessage()))));
                                h.d(s, "Universal: Load libraries failed ".concat(String.valueOf(String.valueOf(unsatisfiedLinkError.getMessage()))));
                            }
                        }
                        this.fireStatus(af, new a6(this, 4, 0));
                        if (b4) {
                            h.d(s, "Auto-Update, loaded.");
                        }
                        this.fireStatus(af, new a6(this, 1, ""));
                    }
                    catch (Exception ex) {
                        ex.printStackTrace();
                    }
                    e.ag(null);
                }
                else {
                    b3 = false;
                }
            }
            if (b3) {
                if (af != null) {
                    af.a(new a6(this, 1, ""));
                }
                if (af != null && b) {
                    af.a(new a6(this, 10, ""));
                }
            }
        }
        catch (Exception ex2) {
            ex2.printStackTrace();
            b3 = false;
        }
        finally {
            h.c(s, String.valueOf(String.valueOf(concat)).concat("Load complete."));
            e.a(false, 0, null, null);
        }
        return b3;
    }
    
    private final boolean loadViewingTechnologyExternalLibraries(final ad ad, final String s, final af af, final boolean b) {
        ++this.loadViewingTechnologyLibraryAttempt;
        final String[] b2 = jiUVDecoder.outsideInLibraries.b(s);
        final String d = jiUVDecoder.outsideInLibraries.d();
        final String concat = String.valueOf(String.valueOf(jiUVDecoder.outsideInLibraries.e())).concat(": ");
        boolean b3 = true;
        try {
            h.c(s, String.valueOf(String.valueOf(concat)).concat("Loading libaries..."));
            boolean b4 = false;
            final String b5 = ji.util.d.b(1200, s);
            final String value = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(ji.util.d.b(708, s)))).append(" ").append(ji.util.d.b(1194, s))));
            final String value2 = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(ji.util.d.b(709, s)))).append(" ").append(ji.util.d.b(1194, s))));
            final String value3 = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(ji.util.d.b(758, s)))).append(" ").append(ji.util.d.b(270, s))));
            e.ag(b5);
            final boolean a = cn.a(ad, s, af, "uv", b2, b5, false, true, false);
            h.c(s, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(concat))).append("Current library state: ").append(a))));
            this.initPrefs(s);
            boolean b6 = false;
            final boolean e = this.prefs.e(ad);
            if (a && !e) {
                b6 = true;
            }
            if (!a) {
                if (ji.util.d.b2() > 1 && this.loadViewingTechnologyLibraryAttempt > 1) {
                    ji.util.d.a(ay.a(), String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(ji.util.d.b(1198, s)))).append("\n\n").append(ji.util.d.b(1199, s)))), ad, 120, null, af, s);
                    return false;
                }
                this.removeDecoder(ad, s);
                ji.util.e.a(true, 708, null, null);
                h.c(s, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(concat))).append("loading ").append(d).append("..."))));
                String s2 = ji.util.d.b(d, ji.util.d.bh(d), "v1");
                String a2 = null;
                String s3 = z.a(ad, d, s2, af, s, ad);
                if (s3 != null) {
                    s3 = ac.b(s3, s);
                }
                if (s3 != null) {
                    try {
                        if (ac.a(s3, s) <= 0) {
                            try {
                                ac.c(s3, s);
                            }
                            catch (Exception ex3) {}
                            s3 = null;
                        }
                    }
                    catch (Exception ex4) {}
                }
                if (s3 == null) {
                    ji.util.e.ag(value);
                    this.fireStatus(af, new a6(this, 1, value));
                    if (b) {
                        this.fireStatus(af, new a6(this, 9, value));
                    }
                    final String b7 = ac.b(z.a(s), s);
                    final String b8 = ji.util.d.b(d, ji.util.d.bh(d), "v1");
                    final String value4 = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(b7))).append("/").append(b8)));
                    final String value5 = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(ji.util.d.b(726, s)))).append("\n").append(ji.util.d.b(727, s)).append("\n").append("\n").append(ji.util.d.b(728, s)).append("\n").append(ji.util.d.b(729, s)).append("\n").append(ji.util.d.b(730, s))));
                    h.c(s, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(concat))).append("downloading ").append(s2).append(" to ").append(b7).append("..."))));
                    h.d(s, String.valueOf(String.valueOf(new StringBuffer("Auto-Update, downloading Universal Viewing library ").append(s2).append(" to ").append(b7).append("..."))));
                    z.a(b8, ji.util.e.an(), 3, af, ad, s, false, value4, value5, false, new y(), false);
                    a2 = z.a(ad, s2, s2, af, s, ad);
                    if (a2 != null) {
                        ji.util.e.ag(value2);
                        if (af != null) {
                            af.a(new a6(this, 1, value2));
                        }
                        if (b) {
                            this.fireStatus(af, new a6(this, 9, value2));
                        }
                        this.fireStatus(af, new a6(this, 4, "0"));
                        s3 = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(b7))).append("/").append(d)));
                        ji.util.d.a(a2, s3, b8, true, ad, s, af);
                    }
                    ji.util.e.ag(b5);
                    if (s3 != null) {
                        final long a3 = ac.a(s3, s);
                        if (a3 > 0) {
                            s3 = ac.b(s3, s);
                            h.d(s, String.valueOf(String.valueOf(new StringBuffer("Auto-Update, #11 downloaded (").append(ji.util.d.a(a3, s)).append(")..."))));
                        }
                        else {
                            try {
                                this.removeDecoder(ad, s);
                                ac.c(s3, s);
                            }
                            catch (Exception ex5) {}
                            s2 = String.valueOf(String.valueOf(ji.util.e.am())).concat(String.valueOf(String.valueOf(s2)));
                            h.d(s, String.valueOf(String.valueOf(new StringBuffer("Auto-Update, file not found! (").append(s2).append(")..."))));
                            ji.util.d.a(ay.a(), String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(s2))).append("\n ----- ").append(ji.util.d.b(260, s)).append(" -----"))), ad, 60, null, af, s);
                            b3 = false;
                            s3 = null;
                        }
                    }
                }
                if (s3 != null) {
                    h.d(s, String.valueOf(String.valueOf(new StringBuffer("Auto-Update, exporting Universal Viewing library ").append(d).append("..."))));
                    ji.util.e.ag(value2);
                    this.fireStatus(af, new a6(this, 1, value2));
                    if (b) {
                        this.fireStatus(af, new a6(this, 9, value2));
                    }
                    final String a4 = cn.a("uv", s);
                    if (ji.zip.a4.a(s3, a4, null, true, ad, s, false, true, af, true)) {
                        if (aa.b(ad, s)) {
                            final String[] a5 = ji.zip.a4.a(s3, ad, s);
                            if (a5 != null && !this.isDrawingCancelled()) {
                                cn.a(ad, s, a4, a5);
                            }
                        }
                        h.d(s, "Auto-Update, expanded.");
                        b4 = true;
                    }
                    else {
                        try {
                            this.removeViewingTechnologyDecoder(ad, s);
                            ac.c(s3, s);
                        }
                        catch (Exception ex6) {}
                        h.d(s, String.valueOf(String.valueOf(new StringBuffer("Auto-Update, unable to expand ").append(d).append("!"))));
                        b3 = false;
                    }
                    try {
                        if (s3 != null) {
                            ac.c(s3, s);
                        }
                    }
                    catch (Exception ex7) {}
                    try {
                        if (s2 != null) {
                            ac.c(ji.util.d.b(ac.b(a2, s), "\\", "/"), s);
                        }
                    }
                    catch (Exception ex8) {}
                }
                else {
                    b3 = false;
                }
            }
            h.c(s, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(concat))).append("Loaded status: ").append(b3))));
            if (b3) {
                if (cn.a(ad, s, af, "uv", b2, b5, false, true, false)) {
                    try {
                        if (b6) {
                            b4 = true;
                        }
                        if (b4) {
                            h.d(s, "Auto-Update, loading Universal Viewing library...");
                            ji.util.e.ag(b5);
                            this.fireStatus(af, new a6(this, 1, b5));
                            if (b) {
                                this.fireStatus(af, new a6(this, 9, b5));
                            }
                        }
                        final long currentTimeMillis = System.currentTimeMillis();
                        double n = b2.length;
                        final String[] filterDllList = this.getFilterDllList();
                        int length;
                        if (filterDllList != null) {
                            length = filterDllList.length;
                        }
                        else {
                            length = 0;
                        }
                        if (length != jiUVDecoder.outsideInLibraries.c) {
                            h.c(s, String.valueOf(String.valueOf(concat)).concat("Invalid number of filter libraries, aborting to force reload"));
                            b3 = false;
                        }
                        if (b3) {
                            if (b4) {
                                n += length;
                            }
                            int n2 = 1;
                            try {
                                int n4;
                                final int n3 = n4 = 5;
                                final boolean c = i.c(220);
                                for (int n5 = 0; n5 < b2.length && !this.isDrawingCancelled(); ++n5) {
                                    final String a6 = ac.a(cn.a("uv", b2[n5], s));
                                    h.c(s, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(concat))).append("Load library: ").append(a6))));
                                    if (this.jiex1.a(a6, c, s, ad) == 0) {
                                        h.d(s, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(concat))).append("Load library failed aborting (").append(a6).append(") *****"))));
                                        b3 = false;
                                        break;
                                    }
                                    final int n6 = (int)(100 * n2++ / n);
                                    if (--n4 <= 0) {
                                        n4 = n3;
                                        this.fireStatus(af, new a6(this, 4, n6));
                                    }
                                }
                                if (b4 && b3 && !this.isDrawingCancelled()) {
                                    int n7 = 0;
                                    try {
                                        for (n7 = 0; n7 < filterDllList.length && !this.isDrawingCancelled(); ++n7) {
                                            final String a7 = ac.a(cn.a("uv", filterDllList[n7], s));
                                            h.c(s, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(concat))).append("Load library: ").append(a7))));
                                            if (this.jiex1.a(a7, c, s, ad) == 0) {
                                                h.d(s, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(concat))).append("Load library filters failed aborting + (").append(a7).append(") *****"))));
                                                b3 = false;
                                                break;
                                            }
                                            final int n8 = (int)(100 * n2++ / n);
                                            if (--n4 <= 0) {
                                                n4 = n3;
                                                this.fireStatus(af, new a6(this, 4, n8));
                                            }
                                        }
                                    }
                                    finally {
                                        if (n7 < filterDllList.length - 1) {
                                            h.c(s, String.valueOf(String.valueOf(concat)).concat("Load library failed, removing decoder"));
                                            ji.util.d.b9(true);
                                            this.removeDecoder(ad, s);
                                        }
                                        else {
                                            h.c(s, String.valueOf(String.valueOf(concat)).concat("Load library successful, setting pref"));
                                            this.prefs.k(true, ad);
                                        }
                                    }
                                }
                                if (b4 && this.isDrawingCancelled()) {
                                    this.removeDecoder(ad, s);
                                    b3 = false;
                                }
                                h.c(s, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(concat))).append("Load library total took: ").append(System.currentTimeMillis() - currentTimeMillis).append("ms"))));
                            }
                            catch (UnsatisfiedLinkError unsatisfiedLinkError) {
                                h.d(s, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(concat))).append("Load libraries failed ").append(unsatisfiedLinkError.getMessage()))));
                            }
                        }
                        this.fireStatus(af, new a6(this, 4, 0));
                        if (b4) {
                            h.d(s, "Auto-Update, loaded.");
                        }
                        this.fireStatus(af, new a6(this, 1, ""));
                    }
                    catch (Exception ex) {
                        ex.printStackTrace();
                    }
                    ji.util.e.ag(null);
                }
                else {
                    b3 = false;
                }
            }
            if (b3) {
                if (af != null && b) {
                    ji.util.e.ag(value3);
                }
                if (af != null) {
                    af.a(new a6(this, 1, value3));
                }
                if (af != null && b) {
                    af.a(new a6(this, 9, value3));
                }
            }
        }
        catch (Exception ex2) {
            ex2.printStackTrace();
            b3 = false;
        }
        finally {
            h.c(s, String.valueOf(String.valueOf(concat)).concat("Load complete."));
            e.a(false, 0, null, null);
        }
        return b3;
    }
    
    private final boolean loadUVJNIExternalLibrary(final Component component, final String s, final af af) {
        try {
            synchronized (jiUVDecoder.loadedLibraries) {
                if (jiUVDecoder.loadedLibraries.d) {
                    // monitorexit(jiUVDecoder.loadedLibraries)
                    return true;
                }
                if (w.a(component, s, af, new jiUVDLL(), "jiuv", 767, 1, false)) {
                    jiUVDecoder.loadedLibraries.d = true;
                    // monitorexit(jiUVDecoder.loadedLibraries)
                    return true;
                }
                // monitorexit(jiUVDecoder.loadedLibraries)
                return false;
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }
    
    private final void fireStatus(final af af, final a6 a6) {
        if (af != null) {
            af.a(a6);
        }
    }
    
    private String[] getFilterDllList() throws Exception {
        return new u(cn.a("uv", this.parentId), this.parentId).a(new zj());
    }
    
    private final void removeRegistryEntries(final ad ad, final String s) {
        try {
            this.initPrefs(s);
            if (this.jiex1 == null) {
                this.jiex1 = new v();
            }
            if (this.jiex1 != null) {
                this.jiex1.a(true, s, ad);
                if (this.jiex1.a("Software\\SCC\\Viewer Technology\\OEM5P", s, ad)) {
                    if (!this.jiex1.b("Software\\SCC\\Viewer Technology\\OEM5P", s, ad)) {
                        h.c(s, "Universal: Cannot delete registry key");
                    }
                }
                else {
                    h.c(s, "Universal: Registry keys not present, not removing");
                }
            }
        }
        catch (Exception ex) {
            d.a(ex);
        }
    }
    
    public final void removeDecoder(final ad ad, final String s) {
        this.removeViewingTechnologyDecoder(ad, s);
    }
    
    public final boolean removeViewingTechnologyDecoder(final ad ad, final String s) {
        synchronized (jiUVDecoder.openDocs) {
            if (jiUVDecoder.openDocs.size() > 0) {
                h.c(s, "Universal: Can't remove decoder, we have a document open");
                // monitorexit(jiUVDecoder.openDocs)
                return false;
            }
        }
        // monitorexit(jiUVDecoder.openDocs)
        boolean al = false;
        synchronized (jiUVDecoder.loadedLibraries) {
            try {
                al = d.al;
                d.al = true;
                try {
                    jiUVDecoder.loadedLibraries.a = false;
                    this.getFilterDllList();
                    jiUVDecoder.outsideInLibraries.a(ad, s, this.getFilterDllList());
                }
                catch (Exception ex) {
                    d.a(ex);
                }
                this.removeRegistryEntries(ad, s);
                this.prefs.k(false, ad);
                String s2;
                if (d.av(s)) {
                    s2 = ac.b(cn.a(s), s);
                }
                else {
                    s2 = ac.b(z.a(s), s);
                }
                final String d = jiUVDecoder.outsideInLibraries.d();
                final String value = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(s2))).append("/").append(d)));
                final String value2 = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(s2))).append("/").append(ji.util.d.b(d, ji.util.d.bh(d), "v1"))));
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
        // monitorexit(jiUVDecoder.loadedLibraries)
        return true;
    }
    
    public final void removeFileIDDecoder(final ad ad, final String s) {
        boolean al = false;
        if (!this.removeViewingTechnologyDecoder(ad, s)) {
            return;
        }
        synchronized (jiUVDecoder.loadedLibraries) {
            if (jiUVDecoder.loadedLibraries.a) {
                // monitorexit(jiUVDecoder.loadedLibraries)
                return;
            }
            try {
                al = d.al;
                d.al = true;
                if (jiUVDecoder.loadedLibraries.c != null) {
                    try {
                        jiUVDecoder.loadedLibraries.c._deInitFileIDLib();
                    }
                    catch (Exception ex) {
                        d.a(ex);
                    }
                    jiUVDecoder.loadedLibraries.c = null;
                }
                try {
                    jiUVDecoder.loadedLibraries.b = false;
                    jiUVDecoder.outsideInLibraries.b(ad, s, null);
                }
                catch (Exception ex2) {
                    d.a(ex2);
                }
                String s2;
                if (d.av(s)) {
                    s2 = ac.b(cn.a(s), s);
                }
                else {
                    s2 = ac.b(z.a(s), s);
                }
                final String b = jiUVDecoder.outsideInLibraries.b();
                final String value = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(s2))).append("/").append(b)));
                final String value2 = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(s2))).append("/").append(d.b(b, d.bh(b), "v1"))));
                try {
                    ac.c(value, s);
                }
                catch (Exception ex4) {}
                try {
                    ac.c(value2, s);
                }
                catch (Exception ex5) {}
            }
            catch (Exception ex3) {
                d.a(ex3);
            }
            finally {
                d.al = al;
            }
        }
        // monitorexit(jiUVDecoder.loadedLibraries)
    }
    
    public dx processHeader(final fh fh, final String s, final double n) throws Exception {
        return this.processHeaderAction(fh, s, n);
    }
    
    private boolean invalidTypeId(final int n) {
        for (int i = 0; i < this.idsToAvoid.length; ++i) {
            if (this.idsToAvoid[i] == n) {
                return true;
            }
        }
        for (int j = 0; j < this.textIDs.length; ++j) {
            if (this.textIDs[j] == n) {
                return true;
            }
        }
        for (int k = 0; k < this.htmlIDs.length; ++k) {
            if (this.htmlIDs[k] == n) {
                return true;
            }
        }
        return false;
    }
    
    private dx processHeaderAction(final fh fh, final String s, final double ad) throws Exception {
        if (this.isDrawingCancelled()) {
            return null;
        }
        dx d = null;
        jiUVDoc docForFilename = this.getDocForFilename(fh);
        if (docForFilename != null && fh != null) {
            final long currentTimeMillis = System.currentTimeMillis();
            try {
                h.c(s, "Universal: Processing header...");
                final String i = docForFilename.i();
                final int j = docForFilename.j();
                this.thumbnail = (fh.r && fh.j != 6);
                if (!docForFilename.a()) {
                    h.c(s, String.valueOf(String.valueOf(new StringBuffer("Universal: Checking document type... ").append(i).append("(").append(j).append(")"))));
                    this.isInDocTypeAvoidList = this.invalidTypeId(j);
                    if (j == -1 || this.isInDocTypeAvoidList) {
                        h.c(s, "Universal: Invalid or unknown type, not opening");
                        String s2 = fh.f;
                        if (s2 == null) {
                            s2 = fh.d.h;
                        }
                        docForFilename.b(fh.o);
                        docForFilename.c(this);
                        docForFilename.c((dx)null);
                        synchronized (jiUVDecoder.openDocs) {
                            jiUVDecoder.openDocs.remove(s2);
                        }
                        // monitorexit(jiUVDecoder.openDocs)
                        docForFilename = null;
                        return null;
                    }
                    final boolean b = (fh.o != null && fh.o.eg() == 2) || fh.o.eg() == 3;
                    if (!this.thumbnail || !b) {
                        docForFilename.b(!this.hashPage(fh, fh.o));
                    }
                }
                else if (docForFilename != null && !docForFilename.d()) {
                    final boolean b2 = (fh.o != null && fh.o.eg() == 2) || fh.o.eg() == 3;
                    if (!this.thumbnail || !b2) {
                        docForFilename.b(!this.hashPage(fh, fh.o));
                    }
                }
                this.setDrawingCancelled(false);
                this.listener = fh.g;
                d = null;
                this.initPrefs(s);
                try {
                    fh.h = Math.max(fh.h, 1);
                    final int h = fh.h;
                    if (fh.d == null) {
                        d = new dx();
                    }
                    else {
                        d = fh.d;
                    }
                    d.ai = ad;
                    final int a = docForFilename.a(this.getValidPageNumber(docForFilename, h, fh, fh.o));
                    if (a == -1) {
                        ji.io.h.c(s, "Universal: Problem waiting for page ".concat(String.valueOf(String.valueOf(a))));
                        return null;
                    }
                    final Rectangle b3 = docForFilename.b(a).b(ad);
                    if (b3 == null) {
                        ji.io.h.c(s, "Universal: Unable to retrieve CB for doc");
                        return null;
                    }
                    d.m = b3.width;
                    d.n = b3.height;
                    if (docForFilename.k() == 7) {
                        d.ac = 72.0;
                        d.ad = 72.0;
                        d.ai = 72.0;
                        d.ah = false;
                    }
                    else {
                        d.ac = ad;
                        d.ad = ad;
                        d.ah = true;
                    }
                    this.initPrefs(s);
                    if (this.prefs.f(fh.o)) {
                        final int m = d.m;
                        final int n = d.n;
                        long n2 = this.prefs.g(fh.o);
                        if (n2 < 1048576) {
                            n2 = fc.g;
                        }
                        long n3 = m * n;
                        if (this.pixelDepth > 8) {
                            n3 *= 4;
                            n2 *= 2;
                        }
                        if (n3 > n2) {
                            final double n4 = Math.round(ad / Math.sqrt(n3 / n2));
                            d.m = (int)Math.ceil(d.m / ad * n4);
                            d.n = (int)Math.ceil(d.n / ad * n4);
                            d.ac = n4;
                            d.ad = n4;
                        }
                    }
                    final Object[] array = { i };
                    switch (this.pixelDepth) {
                        case 1: {
                            d.l = s.a(1305, s, array);
                            d.z = 1;
                            d.aa = 1;
                            d.am = 1;
                            break;
                        }
                        case 8: {
                            d.l = s.a(1306, s, array);
                            d.z = 8;
                            d.aa = 1;
                            d.am = 8;
                            break;
                        }
                        default: {
                            d.l = s.a(1307, s, array);
                            d.z = 8;
                            d.aa = 3;
                            d.am = 24;
                            break;
                        }
                    }
                    d.bk = new Hashtable();
                    if (fh.a.toString().indexOf("#") != -1) {
                        d.u = 1;
                    }
                    else {
                        synchronized (d) {
                            if (docForFilename.b()) {
                                d.c2 = false;
                            }
                            d.u = docForFilename.h();
                        }
                    }
                    d.i = ac.a(fh.b.a(), s);
                    d.h = fh.f;
                    d.v = h;
                    d.w = a;
                    d.cs = this.subPage;
                }
                finally {}
            }
            catch (UnsatisfiedLinkError unsatisfiedLinkError) {
                unsatisfiedLinkError.printStackTrace();
                d = null;
            }
            catch (Exception ex) {
                ex.printStackTrace();
                d = null;
            }
            h.c(s, String.valueOf(String.valueOf(new StringBuffer("Universal: Processed header in ").append(System.currentTimeMillis() - currentTimeMillis).append("ms"))));
        }
        if (docForFilename != null && d != null) {
            if (this.previousHeader != null) {
                docForFilename.b(this.previousHeader);
            }
            docForFilename.a(this.previousHeader = d);
        }
        return d;
    }
    
    private final void initPrefs(final String s) {
        try {
            if (this.prefs == null) {
                this.prefs = new p(s);
            }
        }
        catch (Exception ex) {}
    }
    
    public int[] getPalette(final ac ac, final dx dx) {
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
    
    private void setupAnnotations(final String s, final dx dx, final ad ad, final int n) throws Exception {
        if (!ad.bi(29)) {
            return;
        }
        if (ad.aa()) {
            h.c(s, "Local document - no attachment processing required");
            return;
        }
        h.c(s, "checking file for type EML");
        final n5 n2 = new n5(new ac(dx.h, false, false, 1024, false, ad, s));
        if (n2.a()) {
            h.c(s, "EML file parsed");
            if (n2.b()) {
                if (this.annotations != null) {
                    return;
                }
                this.annotations = new df(s);
                final Dimension a = ad.a(n);
                final double n3 = a.width;
                final double n4 = a.height;
                final double n5 = n3 / 21.0;
                final double n6 = n5 * 100.0 / n3;
                final double n7 = n5 * 100.0 / n4;
                final double n8 = n5;
                double n10;
                double n9 = n10 = n5 / 2.0;
                Object[] bk = null;
                String bj = ad.bj(5);
                Object concat = null;
                final String[] bk2 = ad.bk(1);
                if (bj == null) {
                    bj = ".";
                    h.c(s, "no attachment base path specified, using: ".concat(String.valueOf(String.valueOf(bj))));
                }
                if (ad.e0()) {
                    bk = ad.bk(0);
                    h.c(s, "Multifile mode: ".concat(String.valueOf(String.valueOf(gq.a(bk)))));
                    if (bk == null) {
                        bk = new String[] { bj };
                        h.c(s, "Multifile mode - no base paths specified, using ".concat(String.valueOf(String.valueOf(bj))));
                    }
                }
                final String bj2 = ad.bj(6);
                String s2 = null;
                final n7[] c = n2.c();
                h.c(s, "attachments present:  ".concat(String.valueOf(String.valueOf(c.length))));
                if (c.length < 20) {
                    n9 += n5 * (20 - c.length);
                }
                for (int i = 0; i < c.length; ++i) {
                    boolean b = true;
                    String s3 = this.getIconFilename(ad, s, ji.util.n8.a(c[i].b(), c[i].c()));
                    if (s3 == null) {
                        h.c(s, "No imagePath for the requested type:  ".concat(String.valueOf(String.valueOf(ji.util.n8.a(c[i].b(), c[i].c())))));
                    }
                    else {
                        s3 = "image:file:".concat(String.valueOf(String.valueOf(s3)));
                    }
                    final dg dg = new dg(String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(n))).append(".").append(i))), d.cm(), s, 1, ad.hc());
                    dg.a(ad);
                    dg.gm();
                    dg.f(false);
                    dg.a("SYSTEM", 9, s3, (int)n9, (int)n10);
                    dg.a((long)n5, (long)n8);
                    dg.a(ad, s3);
                    dg.j(ad.e0() ? dx.v : 1);
                    dg.m(c[i].a());
                    dg.d(ad.getBackground());
                    dg.c(ad.getForeground());
                    dg.a(n6, n7);
                    dg.a(a, n);
                    final int n11 = ad.j7() - 1;
                    if (ad.e0() && bk.length > n11) {
                        concat = bk[n11];
                    }
                    if (concat == null) {
                        concat = bj;
                    }
                    if (bk2 != null) {
                        if (i < bk2.length) {
                            s2 = bk2[i];
                            if (s2 != null) {
                                if (s2.startsWith("#")) {
                                    s2 = null;
                                }
                                else if (s2.toLowerCase().startsWith("file") || s2.toLowerCase().startsWith("http")) {
                                    concat = "";
                                    b = false;
                                }
                                else if (s2.equalsIgnoreCase("default")) {
                                    s2 = c[i].a();
                                }
                            }
                        }
                    }
                    else {
                        s2 = c[i].a();
                    }
                    if (concat != null && s2 != null) {
                        if (b && !((String)concat).endsWith("/") && !s2.startsWith("/") && ((String)concat).indexOf("?") == -1 && s2.indexOf("?") == -1) {
                            concat = String.valueOf(String.valueOf(concat)).concat("/");
                        }
                        dg.l(String.valueOf(String.valueOf(new StringBuffer("<web><").append((String)concat).append(s2).append("><").append(bj2).append(">"))));
                        dg.a("0#2#0#0#0#1.0#50#50#256#256#256", ad);
                    }
                    s2 = null;
                    concat = null;
                    this.annotations.a(dg);
                    n9 += n5;
                    if (i % 20 == 19) {
                        n10 += n8;
                        n9 = n5 / 2;
                        if (c.length - i < 20) {
                            n9 += n5 * (21 - (c.length - i));
                        }
                    }
                }
            }
        }
        else {
            h.c(s, "File is not EML");
        }
    }
    
    public df getAnnotations(final String s, final dx dx, final ad ad, final int n) throws Exception {
        this.setupAnnotations(s, dx, ad, n);
        return this.annotations;
    }
    
    public void clearAbort() {
        this.setDrawingCancelled(false);
    }
    
    public void resetFlags() {
        this.openCancelled = false;
        this.invalidPassword = false;
    }
    
    public boolean isCancelled() {
        return this.openCancelled;
    }
    
    public boolean isInvalidPassword() {
        return this.invalidPassword;
    }
    
    public boolean isAborted() {
        return this.drawingCancelled;
    }
    
    private final int getValidPageNumber(final jiUVDoc jiUVDoc, final int n, final fh fh, final ad ad) {
        int n2 = n;
        try {
            this.subPage = false;
            if (jiUVDoc != null) {
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
                if (!jiUVDoc.g()) {
                    n2 = Math.min(n2, jiUVDoc.h());
                }
            }
        }
        catch (Exception ex2) {}
        return n2;
    }
    
    private boolean hashPage(final fh fh, final ad ad) {
        boolean b = false;
        try {
            if (fh != null) {
                final String string = fh.a.toString();
                if (string != null) {
                    if (string.toString().indexOf("#") >= 0) {
                        if (d.e(string, -1) != -1) {
                            b = true;
                        }
                    }
                    else {
                        try {
                            if (ad.e0()) {
                                b = true;
                            }
                        }
                        catch (Exception ex) {}
                    }
                }
            }
        }
        catch (Exception ex2) {}
        return b;
    }
    
    public void fillDib(final fh fh, final String s) throws Exception {
        this.fillDibAction(fh, s);
    }
    
    public Class classForName(String replace) {
        replace = replace.replace('/', '.');
        Class<?> forName = null;
        try {
            forName = Class.forName(replace);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return forName;
    }
    
    private void fillDibAction(final fh fh, final String s) throws Exception {
        if (this.isDrawingCancelled()) {
            return;
        }
        final long currentTimeMillis = System.currentTimeMillis();
        final jiUVDoc docForFilename = this.getDocForFilename(fh);
        try {
            h.c(s, String.valueOf(String.valueOf(new StringBuffer("Universal: open doc ").append(System.currentTimeMillis() - currentTimeMillis).append("ms"))));
            if (docForFilename != null && fh.d.v > 0) {
                docForFilename.c(this.isCancelled());
                int n = 270;
                if (fh.d.v > 1) {
                    n = 271;
                }
                final String value = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(d.b(758, s)))).append(" ").append(d.b(n, s)).append("...")));
                this.thumbnail = (fh.r && fh.j != 6);
                if (!this.thumbnail && !fh.q) {
                    e.ag(value);
                }
                if (this.listener != null) {
                    this.fireStatus(this.listener, new a6(this, 1, value));
                }
                final double ac = fh.d.ac;
                final n3.zo b = docForFilename.b(fh.d.w);
                try {
                    boolean b2 = false;
                    this.initPrefs(s);
                    int n2 = 0;
                    switch (this.pixelDepth) {
                        case 1: {
                            b2 = true;
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
                                b2 = true;
                            }
                            fh.c.b(3, b2, fh.o);
                            n2 = 8;
                            break;
                        }
                        default: {
                            fh.c.b(4, false, fh.o);
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
                    boolean b3 = false;
                    if (this.pixelDepth != 8 && d.du() && fh.d.ak) {
                        b3 = true;
                        a6 = new a6(this, 23, "0");
                        if (g != null && !this.thumbnail && !fh.q) {
                            this.fireStatus(g, new a6(this, 13, ""));
                        }
                    }
                    if (g != null) {
                        a6.a("0");
                        this.fireStatus(g, a6);
                    }
                    final int n5 = 0;
                    final int a7 = b.a(n5, null, null, n2, rectangle, null, 0, ac);
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
                        h.c(s, String.valueOf(String.valueOf(new StringBuffer("Universal: before decode ").append(currentTimeMillis3 - currentTimeMillis).append("ms"))));
                        switch (this.pixelDepth) {
                            case 1: {
                                final byte[] array = new byte[n8];
                                final byte[] array2 = new byte[n7];
                                final boolean j = fh.c.j();
                                final boolean x = fh.c.x();
                                for (int n20 = 0; n20 < n12 && !this.isDrawingCancelled(); ++n20) {
                                    if (n19 == 0 && g != null && System.currentTimeMillis() - currentTimeMillis2 > n18 && !b3) {
                                        n19 = 1;
                                        if (g != null && !this.thumbnail && !fh.q) {
                                            this.fireStatus(g, new a6(this, 9, value));
                                        }
                                    }
                                    this.moveBands(rectangle4, rectangle2, n6, n20, n12, rectangle5, rectangle3, 0, n4);
                                    b.a(n5, rectangle4, rectangle3, n2, rectangle5, array, n13, ac);
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
                                if (b2) {
                                    ag = fh.c.ag();
                                }
                                final byte[] array5 = new byte[n13];
                                fh.c.a(this.getPalette(fh.b, fh.d), fh.d);
                                int n24 = 0;
                                for (int n25 = 0; n25 < n12 && !this.isDrawingCancelled(); ++n25) {
                                    if (n19 == 0 && g != null && System.currentTimeMillis() - currentTimeMillis2 > n18 && !b3) {
                                        n19 = 1;
                                        if (g != null && !this.thumbnail && !fh.q) {
                                            this.fireStatus(g, new a6(this, 9, value));
                                        }
                                    }
                                    this.moveBands(rectangle4, rectangle2, n6, n25, n12, rectangle5, rectangle3, 0, n4);
                                    int a10 = b.a(n5, rectangle4, rectangle3, n2, rectangle5, array5, n13, ac);
                                    if (!this.isDrawingCancelled()) {
                                        if (b2) {
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
                                            if (System.currentTimeMillis() - n17 > n16 || b3) {
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
                                Label_2437: {
                                    if (this.COMPRESSED_WRITE) {
                                        b.a();
                                        try {
                                            final long currentTimeMillis4 = System.currentTimeMillis();
                                            int a11;
                                            while ((a11 = b.a(n5, rectangle4, n2, new byte[n13], n13, ac, 1)) > -1) {
                                                if (n19 == 0 && g != null && System.currentTimeMillis() - currentTimeMillis2 > n18 && !b3) {
                                                    n19 = 1;
                                                    if (g != null && !this.thumbnail && !fh.q) {
                                                        this.fireStatus(g, new a6(this, 9, value));
                                                    }
                                                }
                                                b.d();
                                                b.e();
                                                if (g != null && n12 < 100) {
                                                    if (System.currentTimeMillis() - n17 > n16 || b3) {
                                                        a6.a(String.valueOf(b.f()));
                                                        this.fireStatus(g, a6);
                                                    }
                                                    n17 = System.currentTimeMillis();
                                                }
                                            }
                                            final long n27 = n26 + (System.currentTimeMillis() - currentTimeMillis4);
                                            break Label_2437;
                                        }
                                        finally {
                                            b.b();
                                        }
                                    }
                                    b.a();
                                    try {
                                        final long currentTimeMillis5 = System.currentTimeMillis();
                                        final byte[] array6 = new byte[n13];
                                        for (int n28 = 0; n28 < n12 && !this.isDrawingCancelled(); ++n28) {
                                            if (n19 == 0 && g != null && System.currentTimeMillis() - currentTimeMillis2 > n18 && !b3) {
                                                n19 = 1;
                                                if (g != null && !this.thumbnail && !fh.q) {
                                                    this.fireStatus(g, new a6(this, 9, value));
                                                }
                                            }
                                            this.moveBands(rectangle4, rectangle2, n6, n28, n12, rectangle5, rectangle3, 0, n4);
                                            final int a12 = b.a(n5, rectangle4, rectangle3, n2, rectangle5, array6, n13, ac);
                                            final int n29 = rectangle5.height - rectangle5.y;
                                            if (!this.isDrawingCancelled()) {
                                                fh.c.a(array6, a12, fh.o, n9 + 1, n9 + n29 + 1, true);
                                                n9 += n29;
                                                n15 += n14;
                                                if (g != null && n12 < 100) {
                                                    if (System.currentTimeMillis() - n17 > n16 || b3) {
                                                        a6.a(String.valueOf((int)n15));
                                                        this.fireStatus(g, a6);
                                                    }
                                                    n17 = System.currentTimeMillis();
                                                }
                                            }
                                        }
                                        final long n30 = n26 + (System.currentTimeMillis() - currentTimeMillis5);
                                    }
                                    finally {
                                        b.b();
                                    }
                                }
                                h.c(s, String.valueOf(String.valueOf(new StringBuffer("Universal: after decode ").append(System.currentTimeMillis() - currentTimeMillis3).append("ms"))));
                                break;
                            }
                        }
                        if (g != null) {
                            a6.a("100");
                            this.fireStatus(g, a6);
                        }
                    }
                    else {
                        h.c(s, String.valueOf(String.valueOf(new StringBuffer("Universal: failed to render page ").append(fh.d.w).append(this.thumbnail ? " as thumbnail" : ""))));
                    }
                }
                catch (Exception ex) {
                    ex.printStackTrace();
                }
                finally {
                    b.c();
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
            if (this.listener != null) {
                this.fireStatus(this.listener, new a6(this, 8, ""));
                if (!fh.q) {
                    this.fireStatus(this.listener, new a6(this, 13, ""));
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
    
    public boolean releaseResources(final String s) {
        try {
            this.prefs = null;
            if (this.annotations != null) {
                this.annotations.m();
                this.annotations = null;
            }
        }
        catch (Exception ex) {}
        this.listener = null;
        return true;
    }
    
    public void close(final dx dx) throws Exception {
        if (dx != null) {
            final String h = dx.h;
            ji.io.h.c(this.parentId, "jiFilterUV asked to close ".concat(String.valueOf(String.valueOf(h))));
            if (h == null) {
                return;
            }
            synchronized (jiUVDecoder.openDocs) {
                if (jiUVDecoder.openDocs.containsKey(h)) {
                    final jiUVDoc jiUVDoc = jiUVDecoder.openDocs.get(h);
                    jiUVDoc.b(dx);
                    jiUVDoc.c(this);
                    ji.io.h.c(this.parentId, "jiFilterUV client count = ".concat(String.valueOf(String.valueOf(jiUVDoc.c()))));
                    if (jiUVDoc.c() == 0) {
                        ji.io.h.c(this.parentId, "jiFilterUV asked to close ".concat(String.valueOf(String.valueOf(h))));
                        ji.io.h.c(this.parentId, "jiFilterUV COMPLETELY CLOSING ".concat(String.valueOf(String.valueOf(h))));
                        jiUVDoc.c((dx)null);
                        jiUVDecoder.openDocs.remove(h);
                    }
                }
                // monitorexit(jiUVDecoder.openDocs)
                return;
            }
        }
        h.c(this.parentId, "jiFilterUV asked to close but header null");
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
    
    public void abort(final dx dx) throws Exception {
        h.c(this.parentId, "Universal: abort called");
        try {
            this.setDrawingCancelled(true);
            synchronized (jiUVDecoder.openDocs) {
                final Enumeration<jiUVDoc> elements = jiUVDecoder.openDocs.elements();
                while (elements.hasMoreElements()) {
                    final jiUVDoc jiUVDoc = elements.nextElement();
                    if (jiUVDoc != null && jiUVDoc.a(this)) {
                        jiUVDoc.d(dx);
                    }
                }
            }
            // monitorexit(jiUVDecoder.openDocs)
        }
        catch (Exception ex) {
            if (d.cy()) {
                ex.printStackTrace();
            }
            h.c(this.parentId, "Universal: Could not abort");
        }
    }
    
    public boolean isKnownNonDisplayable() {
        return this.isInDocTypeAvoidList;
    }
    
    public final boolean isViewingTechnologyLoaded() {
        synchronized (jiUVDecoder.loadedLibraries) {
            // monitorexit(jiUVDecoder.loadedLibraries)
            return jiUVDecoder.loadedLibraries.a;
        }
    }
    
    public final int isFileType(final ac ac, final ad ad, final String s, final String s2, final String s3, final af af, final String s4) {
        if (!this.loadFileIDLibraries(ad, s, af, false)) {
            return 6;
        }
        String unobfuscatedFile = null;
        try {
            unobfuscatedFile = this.getUnobfuscatedFile(ad, s2, null, s3, af, s4);
        }
        catch (Exception ex) {
            h.d(s, "Universal: FileID: Exception occured locating document ".concat(String.valueOf(String.valueOf(ex.getMessage()))));
        }
        if (unobfuscatedFile == null) {
            return 6;
        }
        int n = -1;
        Label_0176: {
            try {
                synchronized (jiUVDecoder.loadedLibraries) {
                    if (jiUVDecoder.loadedLibraries.c != null) {
                        n = (int)jiUVDecoder.loadedLibraries.c._getFileID(unobfuscatedFile, i.c(220));
                        // monitorexit(jiUVDecoder.loadedLibraries)
                        break Label_0176;
                    }
                    h.d(s, "Universal: FileID: Error while attempting to retrieve File ID, File ID library is not initialized.");
                    // monitorexit(jiUVDecoder.loadedLibraries)
                    return 6;
                }
            }
            catch (Throwable t) {
                h.d(s, "Universal: FileID: Error while attempting to retrieve File ID, ".concat(String.valueOf(String.valueOf(t.getMessage()))));
                d.a(t);
                return 6;
            }
        }
        if (this.invalidTypeId(n)) {
            return 0;
        }
        return 1;
    }
    
    public static final void releaseStaticResource() {
        synchronized (jiUVDecoder.loadedLibraries) {
            if (jiUVDecoder.loadedLibraries.c != null) {
                jiUVDecoder.loadedLibraries.c._deInitFileIDLib();
                jiUVDecoder.loadedLibraries.c = null;
                w.b("jiuv", "");
            }
        }
        // monitorexit(jiUVDecoder.loadedLibraries)
    }
    
    private String getIconFilename(final ad ad, final String s, final String s2) {
        final File file = new File(z.c(s), "res6");
        if (!file.exists() || !file.isDirectory()) {
            file.mkdir();
        }
        String value = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(file.getAbsolutePath()))).append(File.separator).append(s2)));
        if (!ac.d(value, s)) {
            h.c(s, "Loading res6.v1");
            try {
                final URL j = e.j(s);
                String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(file.getAbsolutePath()))).append(File.separator).append("res6.zip")));
                z.a(new URL(String.valueOf(String.valueOf(j)).concat("res6.v1")), "res6.v1", ad, s, ad);
                final String a = z.a("res6.v1", ad, s, true, true, s);
                try {
                    a4.a(a, file.getAbsolutePath(), null, false, ad, s, false, true, ad, true);
                    final File[] listFiles = file.listFiles();
                    for (int i = 0; i < listFiles.length; ++i) {
                        listFiles[i].renameTo(new File(listFiles[i].getParent(), "xe".concat(String.valueOf(String.valueOf(listFiles[i].getName())))));
                    }
                }
                catch (Exception ex) {
                    ex.printStackTrace();
                    h.d(s, "Error accessing res6.v1:  ".concat(String.valueOf(String.valueOf(ex.getMessage()))));
                    value = null;
                }
            }
            catch (MalformedURLException ex2) {
                ex2.printStackTrace();
                h.d(s, "Address error for res6.v1: ".concat(String.valueOf(String.valueOf(ex2.getMessage()))));
                value = null;
            }
            if (!ac.d(value, s)) {
                h.c(s, "No icon available for ".concat(String.valueOf(String.valueOf(s2))));
                value = null;
            }
        }
        return value;
    }
    
    static {
        jiUVDecoder.outsideInLibraries = new zp();
        jiUVDecoder.loadedLibraries = new zq(null);
        jiUVDecoder.openDocs = new Hashtable();
    }
    
    private static class zq
    {
        boolean a;
        boolean b;
        jiUVDLL c;
        boolean d;
        
        private zq() {
            this.c = null;
        }
    }
    
    static class zp extends n1
    {
        public zp() {
            super("uv");
            this.a(new String[] { "sccch", "sccfa", "sccda", "sccfmt", "sccdu", "sccind", "sccca", "sccanno", "sccvw", "sccole", "oswin32" }, new String[] { "lwpapin", "ltscsn10", "isgdi32", "dewp.dll", "detree", "dess", "demet", "dehex", "debmp" });
            this.b(new String[] { "wvcore", "sccut", "scclo", "sccfut", "sccfi" }, null);
            this.c(new String[] { "msvcr80", "msvcp80" }, new String[] { "msvcr80.dll", "msvcm80.dll", "msvcp80.dll", "Microsoft.VC80.CRT.manifest" });
            super.c = 142;
            this.b(8, 3, -1, -1);
        }
    }
    
    interface aeh
    {
    }
}

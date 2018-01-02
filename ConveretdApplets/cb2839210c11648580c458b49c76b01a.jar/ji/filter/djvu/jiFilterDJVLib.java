// 
// Decompiled by Procyon v0.5.30
// 

package ji.filter.djvu;

import ji.v1event.a6;
import ji.util.e;
import java.util.Hashtable;
import ji.sec.g;
import ji.util.d;
import ji.io.h;
import ji.util.i;
import ji.image.dx;
import ji.filter.fh;
import ji.io.ac;
import ji.io.q;
import v1com.daeja.v1.v1base.jiFDJV;
import ji.ext.f4;

public class jiFilterDJVLib extends f4 implements gy
{
    private static boolean pDebug;
    private static boolean pMessages;
    private static jiFDJV filter;
    private static boolean booLoaded;
    private static boolean booTested;
    private q cacheFile;
    private static String libFileName;
    private static String fileName;
    private static String localFileName;
    private static int pageCount;
    private static int page;
    private boolean aborted;
    private boolean busy;
    private static final Object LOCK;
    private static boolean useSynchronized;
    
    public jiFilterDJVLib() {
        this.busy = false;
    }
    
    public void testLibrary(final Object[] array) throws Exception {
        jiFilterDJVLib.filter.load(array);
        jiFilterDJVLib.booLoaded = jiFilterDJVLib.filter.isLoaded();
    }
    
    public void setTested(final boolean booTested) {
        jiFilterDJVLib.booTested = booTested;
    }
    
    public void setFileCache(final q cacheFile) {
        this.cacheFile = cacheFile;
    }
    
    public q getFileCache() {
        return this.cacheFile;
    }
    
    public void setLoaded(final boolean booLoaded) {
        jiFilterDJVLib.booLoaded = booLoaded;
    }
    
    public void setLibFileName(final String s, final String s2) {
        try {
            if (s != null) {
                jiFilterDJVLib.libFileName = ac.b(s, s2);
            }
            else {
                jiFilterDJVLib.libFileName = null;
            }
        }
        catch (Exception ex) {}
    }
    
    public String getLibFileName(final String s) {
        return jiFilterDJVLib.libFileName;
    }
    
    public boolean isFileCacheSet() {
        return this.cacheFile != null;
    }
    
    public boolean isTested() {
        return jiFilterDJVLib.booTested;
    }
    
    public boolean isLoaded() {
        return jiFilterDJVLib.booLoaded;
    }
    
    public dx loadImageHeaderInternal(final fh fh) throws Exception {
        if (jiFilterDJVLib.useSynchronized) {
            if (i.c(36)) {
                h.d(fh.u, "FilterDJV: loadHeader synchronizing");
            }
            synchronized (jiFilterDJVLib.LOCK) {
                if (i.c(36)) {
                    h.d(fh.u, "FilterDJV: loadHeader synchronized");
                }
                final dx loadImageHeaderInternalAction = this.loadImageHeaderInternalAction(fh);
                if (i.c(36)) {
                    h.d(fh.u, "FilterDJV: loadHeader end synchronized");
                }
                // monitorexit(jiFilterDJVLib.LOCK)
                return loadImageHeaderInternalAction;
            }
        }
        return this.loadImageHeaderInternalAction(fh);
    }
    
    private dx loadImageHeaderInternalAction(final fh fh) throws Exception {
        if (jiFilterDJVLib.filter.isLoaded()) {
            jiFilterDJVLib.filter.setDebug(d.c8());
            jiFilterDJVLib.filter.setMessages(d.c9());
        }
        dx dx = null;
        boolean b = true;
        if (jiFilterDJVLib.localFileName != null && fh.b.a() != null && fh.b.a().equals(jiFilterDJVLib.localFileName)) {
            b = false;
        }
        jiFilterDJVLib.localFileName = fh.b.a();
        jiFilterDJVLib.fileName = fh.e;
        g.b(jiFilterDJVLib.localFileName, fh.u);
        if (b) {
            jiFilterDJVLib.pageCount = jiFilterDJVLib.filter.validFile(fh.b.a().toLowerCase());
            if (jiFilterDJVLib.pageCount == -1) {
                h.d(fh.u, jiFilterDJVLib.filter.getLastError());
            }
        }
        if (jiFilterDJVLib.pageCount != -1) {
            jiFilterDJVLib.pageCount = Math.max(jiFilterDJVLib.pageCount, 1);
            jiFilterDJVLib.page = this.getValidPageNumber(fh.h, fh, jiFilterDJVLib.pageCount);
            if (!jiFilterDJVLib.filter.setPageNumber(jiFilterDJVLib.page - 1)) {
                h.d(fh.u, jiFilterDJVLib.filter.getLastError());
            }
            dx = new dx();
            dx.bk = new Hashtable();
            dx.bl = new Hashtable();
            if (fh.a.toString().indexOf("#") != -1) {
                dx.u = 1;
            }
            else {
                dx.u = jiFilterDJVLib.pageCount;
            }
            if (fh.r) {
                dx.ax = this.getThumbnailImage(fh, dx, jiFilterDJVLib.page);
                if (dx.ax == null) {
                    dx.m = jiFilterDJVLib.filter.getWidth();
                    dx.n = jiFilterDJVLib.filter.getHeight();
                }
            }
            dx.am = jiFilterDJVLib.filter.getPixelDepth();
            dx.z = jiFilterDJVLib.filter.getBitsPerSample();
            dx.aa = jiFilterDJVLib.filter.getSamplesPerPixel();
            if (dx.ax == null) {
                dx.m = jiFilterDJVLib.filter.getWidth();
                dx.n = jiFilterDJVLib.filter.getHeight();
            }
            else {
                dx.am = 24;
                dx.z = 8;
                dx.aa = 3;
            }
            dx.f = fh.e;
            dx.h = fh.f;
            dx.i = fh.b.v();
            dx.v = fh.h;
            dx.w = jiFilterDJVLib.page;
            dx.l = "Lizardtech DjVU format";
        }
        return dx;
    }
    
    private final byte[] getThumbnailImage(final fh fh, final dx dx, final int n) {
        int max;
        int i;
        for (max = Math.max(fh.s, fh.t), i = 2; i < max; i *= 2) {}
        final byte[] array = new byte[i * i * 4];
        final int thumbNail = jiFilterDJVLib.filter.getThumbNail(n - 1, i, array, 0, 0);
        byte[] array3;
        if (thumbNail > 0) {
            final byte[] array2 = new byte[thumbNail];
            System.arraycopy(array, 0, array2, 0, thumbNail);
            array3 = array2;
            dx.m = jiFilterDJVLib.filter.getThumbnailWidth();
            dx.n = jiFilterDJVLib.filter.getThumbnailHeight();
        }
        else {
            array3 = null;
        }
        return array3;
    }
    
    public final boolean isBusy() {
        return this.busy;
    }
    
    public final boolean isAborted() {
        return this.aborted;
    }
    
    public void clearAbort() {
        this.aborted = false;
    }
    
    public void fillDibInternal(final fh fh) throws Exception {
        if (jiFilterDJVLib.useSynchronized) {
            if (i.c(36)) {
                h.d(fh.u, "FilterDJV: filldib synchronizing");
            }
            synchronized (jiFilterDJVLib.LOCK) {
                if (i.c(36)) {
                    h.d(fh.u, "FilterDJV: filldib synchronized");
                }
                this.fillDibInternalAction(fh);
                if (i.c(36)) {
                    h.d(fh.u, "FilterDJV: filldib end synchronized");
                }
                // monitorexit(jiFilterDJVLib.LOCK)
                return;
            }
        }
        this.fillDibInternalAction(fh);
    }
    
    private void fillDibInternalAction(final fh fh) throws Exception {
        int n = 0;
        if (fh.d.a0 && fh.d.ax != null) {
            n = 1;
        }
        boolean b = true;
        if (jiFilterDJVLib.localFileName != null && fh.b.a() != null) {
            g.b(jiFilterDJVLib.localFileName, fh.u);
            if (fh.b.a().equals(jiFilterDJVLib.localFileName)) {
                b = false;
            }
        }
        jiFilterDJVLib.localFileName = fh.b.a();
        jiFilterDJVLib.fileName = fh.e;
        if (b) {
            jiFilterDJVLib.pageCount = jiFilterDJVLib.filter.validFile(fh.b.a().toLowerCase());
            if (jiFilterDJVLib.pageCount == -1) {
                h.d(fh.u, jiFilterDJVLib.filter.getLastError());
            }
            if (jiFilterDJVLib.pageCount != -1) {
                jiFilterDJVLib.pageCount = Math.max(jiFilterDJVLib.pageCount, 1);
                jiFilterDJVLib.page = Math.min(fh.d.w, jiFilterDJVLib.pageCount);
                if (!jiFilterDJVLib.filter.setPageNumber(jiFilterDJVLib.page - 1)) {
                    h.d(fh.u, jiFilterDJVLib.filter.getLastError());
                }
            }
        }
        if (n == 0) {
            int w = 1;
            if (fh.d.w > 0) {
                w = fh.d.w;
            }
            if (!jiFilterDJVLib.filter.setPageNumber(w - 1)) {
                h.d(fh.u, jiFilterDJVLib.filter.getLastError());
            }
        }
        final int m = fh.d.m;
        this.busy = true;
        try {
            this.aborted = false;
            int bufferSize = d.ai;
            if (e.av()) {
                bufferSize = d.aj;
            }
            else if (d.em()) {
                bufferSize = d.aj;
            }
            switch (fh.d.am) {
                case 1: {
                    fh.c.b(1, false, fh.o);
                    if (n == 0) {
                        bufferSize = Math.min(Math.max(bufferSize * 8 / fh.d.m, 10), fh.d.n) * fh.d.m / 8;
                        break;
                    }
                    break;
                }
                default: {
                    fh.c.b(4, false, fh.o);
                    if (n == 0) {
                        int n2 = m / 8;
                        if (m % 8 > 0) {
                            ++n2;
                        }
                        int n3 = n2;
                        final int n4 = n3 % 4;
                        if (n4 > 0) {
                            n3 += 4 - n4;
                        }
                        final int n5 = n3 * 8;
                        bufferSize = Math.min(Math.max(bufferSize / (4 * n5), 10), fh.d.n) * 4 * n5;
                        break;
                    }
                    break;
                }
            }
            boolean setBufferSize = true;
            if (!setBufferSize) {
                h.d(fh.u, jiFilterDJVLib.filter.getLastError());
            }
            this.aborted = false;
            byte[] array = null;
            if (n == 0) {
                array = new byte[bufferSize];
                setBufferSize = jiFilterDJVLib.filter.setBufferSize(bufferSize);
            }
            if (!setBufferSize) {
                h.d(fh.u, jiFilterDJVLib.filter.getLastError());
            }
            int n6 = 0;
            int n7 = 0;
            this.aborted = false;
            if (i.c(87)) {
                fh.c.e(true);
            }
            if (fh.r) {
                if (fh.d.ax == null) {
                    fh.d.ax = this.getThumbnailImage(fh, fh.d, jiFilterDJVLib.page);
                }
                if (fh.d.ax != null) {
                    n = 1;
                    try {
                        fh.c.a((byte[])fh.d.ax, ((byte[])fh.d.ax).length, fh.o, 1, fh.d.n, true);
                    }
                    catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }
            if (n == 0) {
                final boolean j = fh.c.j();
                final boolean x = fh.c.x();
                while (jiFilterDJVLib.filter.hasMoreData() && !this.aborted) {
                    final int nextDataSet = jiFilterDJVLib.filter.getNextDataSet(array);
                    if (nextDataSet < 1) {
                        h.d(fh.u, jiFilterDJVLib.filter.getLastError());
                        break;
                    }
                    if (array == null) {
                        continue;
                    }
                    switch (fh.c.v) {
                        case 1: {
                            int n8 = m / 8;
                            if (m % 8 > 0) {
                                ++n8;
                            }
                            int n9 = n8;
                            final int n10 = n9 % 4;
                            if (n10 > 0) {
                                n9 += 4 - n10;
                            }
                            final int n11 = m;
                            final int n12 = nextDataSet / n9;
                            int n13 = nextDataSet;
                            final byte[] array2 = new byte[n9];
                            final short[] array3 = new short[fh.d.m];
                            int[] array4 = null;
                            if (j) {
                                array4 = new int[array3.length];
                            }
                            for (int n14 = 0; n14 < n12 && !this.aborted; ++n14) {
                                n13 -= n9;
                                System.arraycopy(array, n13, array2, 0, n9);
                                final int a = d.a(0, n11, array2, 0, array3);
                                if (!this.aborted) {
                                    if (j) {
                                        if (x) {
                                            final short[] a2 = fh.c.a(array3, array3.length, n14);
                                            final int length = a2.length;
                                            array4 = new int[length];
                                            for (int i = 0; i < length; ++i) {
                                                array4[i] = a2[i];
                                            }
                                            fh.c.a(array4, length, n14, false, true);
                                        }
                                        else {
                                            for (int k = 0; k < a; ++k) {
                                                array4[k] = (short)(array3[k] & 0xFFFF);
                                            }
                                            fh.c.a(array4, a, n14, false, true);
                                        }
                                    }
                                    else {
                                        fh.c.a(array3, a);
                                    }
                                }
                                ++n6;
                                final int n15 = 100 * n6 / fh.d.n;
                                if (fh.g != null && n15 > n7 + 5) {
                                    n7 = n15;
                                    if (n7 != 100) {
                                        fh.g.a(new a6(this, 4, "".concat(String.valueOf(String.valueOf(n7)))));
                                    }
                                }
                            }
                            continue;
                        }
                        default: {
                            final int n16 = nextDataSet / (fh.d.m * 4);
                            if (!this.aborted) {
                                fh.c.a(array, Math.min(nextDataSet, array.length), fh.o, n6 + 1, n6 + n16, true);
                            }
                            n6 += n16;
                            final int n17 = 100 * n6 / fh.d.n;
                            if (fh.g == null || n17 <= n7 + 5) {
                                continue;
                            }
                            n7 = n17;
                            if (n7 == 100) {
                                continue;
                            }
                            if (d.du() && fh.d.ak) {
                                fh.g.a(new a6(this, 23, "".concat(String.valueOf(String.valueOf(n7)))));
                                continue;
                            }
                            fh.g.a(new a6(this, 4, "".concat(String.valueOf(String.valueOf(n7)))));
                            continue;
                        }
                    }
                }
            }
            if (fh.g != null) {
                fh.g.a(new a6(this, 4, "0"));
            }
            if (!this.aborted && fh.d.z > 1) {
                fh.c.e(fh.o);
            }
            if (this.aborted) {
                fh.c.a(fh.o);
            }
        }
        finally {
            this.busy = false;
        }
    }
    
    private final int getValidPageNumber(final int n, final fh fh, final int n2) {
        int d = n;
        try {
            if (fh.a != null && fh.a.toString().indexOf("#") >= 0) {
                d = ji.util.d.d(fh.a);
            }
        }
        catch (Exception ex) {}
        return Math.min(d, n2);
    }
    
    public void abort() {
        if (e.ai()) {
            this.aborted = true;
        }
    }
    
    public void close(final dx dx) {
        this.releaseResources();
    }
    
    public void releaseResources() {
        jiFilterDJVLib.filter.releaseResources();
    }
    
    static {
        jiFilterDJVLib.pDebug = false;
        jiFilterDJVLib.pMessages = true;
        jiFilterDJVLib.filter = new jiFDJV();
        jiFilterDJVLib.page = 0;
        LOCK = new Object();
        jiFilterDJVLib.useSynchronized = false;
    }
}

// 
// Decompiled by Procyon v0.5.30
// 

package v1com.daeja.v1.v1base;

import ji.util.i;

public class jiFDJV
{
    private static boolean booLoaded;
    
    private native boolean jiExtCheckDjvOK(final String p0, final boolean p1);
    
    private native int jiExtGetWidth();
    
    private native int jiExtGetHeight();
    
    private native int jiExtGetBitsPerSample();
    
    private native int jiExtGetSamplesPerPixel();
    
    private native int jiExtGetPixelDepth();
    
    private native int jiExtGetThumbHeight();
    
    private native int jiExtGetThumbWidth();
    
    private native void jiExtReleaseResourcesDjv();
    
    private native int jiExtValidFile(final String p0, final boolean p1);
    
    private native boolean jiExtSetAreaOfInterest(final int p0, final int p1, final int p2, final int p3);
    
    private native boolean jiExtSetDebugDjv(final boolean p0);
    
    private native boolean jiExtSetMessagesDjv(final boolean p0);
    
    private native boolean jiExtSetBufferSize(final int p0);
    
    private native boolean jiExtSetPageNumber(final int p0);
    
    private native boolean jiExtHasMoreData();
    
    private native int jiExtGetNextDataSet(final byte[] p0, final int p1);
    
    private native void jiExtGetLastError(final byte[] p0);
    
    private native int jiExtGetThumbNail(final int p0, final int p1, final byte[] p2, final int p3, final int p4);
    
    public void load(final Object[] array) throws Exception {
        if (array != null && array.length > 0 && array[0] instanceof String) {
            final String s = (String)array[0];
            try {
                jiFDJV.booLoaded = this.jiExtCheckDjvOK(s, i.c(220));
            }
            catch (Throwable t) {
                t.printStackTrace();
            }
            return;
        }
        throw new Exception("Unable to load filter - lib path name is null");
    }
    
    public int validFile(final String s) {
        int jiExtValidFile = -1;
        if (jiFDJV.booLoaded) {
            try {
                jiExtValidFile = this.jiExtValidFile(s, i.c(220));
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return jiExtValidFile;
    }
    
    public boolean isLoaded() {
        return jiFDJV.booLoaded;
    }
    
    public void setDebug(final boolean b) {
        if (jiFDJV.booLoaded) {
            try {
                this.jiExtSetDebugDjv(b);
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
    
    public void setMessages(final boolean b) {
        if (jiFDJV.booLoaded) {
            try {
                this.jiExtSetMessagesDjv(b);
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
    
    public final int getWidth() {
        int jiExtGetWidth = 0;
        if (jiFDJV.booLoaded) {
            try {
                jiExtGetWidth = this.jiExtGetWidth();
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return jiExtGetWidth;
    }
    
    public final int getThumbnailWidth() {
        int jiExtGetThumbWidth = 0;
        if (jiFDJV.booLoaded) {
            try {
                jiExtGetThumbWidth = this.jiExtGetThumbWidth();
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return jiExtGetThumbWidth;
    }
    
    public final int getThumbnailHeight() {
        int jiExtGetThumbHeight = 0;
        if (jiFDJV.booLoaded) {
            try {
                jiExtGetThumbHeight = this.jiExtGetThumbHeight();
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return jiExtGetThumbHeight;
    }
    
    public final String getLastError() {
        final byte[] array = new byte[256];
        if (jiFDJV.booLoaded) {
            try {
                this.jiExtGetLastError(array);
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        final String s = new String(array);
        return s.substring(0, s.indexOf("\u0000"));
    }
    
    public final int getHeight() {
        int jiExtGetHeight = 0;
        if (jiFDJV.booLoaded) {
            try {
                jiExtGetHeight = this.jiExtGetHeight();
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return jiExtGetHeight;
    }
    
    public final int getBitsPerSample() {
        int jiExtGetBitsPerSample = 0;
        if (jiFDJV.booLoaded) {
            try {
                jiExtGetBitsPerSample = this.jiExtGetBitsPerSample();
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return jiExtGetBitsPerSample;
    }
    
    public final int getSamplesPerPixel() {
        int jiExtGetSamplesPerPixel = 0;
        if (jiFDJV.booLoaded) {
            try {
                jiExtGetSamplesPerPixel = this.jiExtGetSamplesPerPixel();
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return jiExtGetSamplesPerPixel;
    }
    
    public final int getPixelDepth() {
        int jiExtGetPixelDepth = 0;
        if (jiFDJV.booLoaded) {
            try {
                jiExtGetPixelDepth = this.jiExtGetPixelDepth();
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return jiExtGetPixelDepth;
    }
    
    public boolean setBufferSize(final int n) {
        boolean jiExtSetBufferSize = false;
        if (jiFDJV.booLoaded) {
            try {
                jiExtSetBufferSize = this.jiExtSetBufferSize(n);
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return jiExtSetBufferSize;
    }
    
    public boolean setPageNumber(final int n) {
        boolean jiExtSetPageNumber = false;
        if (jiFDJV.booLoaded) {
            try {
                jiExtSetPageNumber = this.jiExtSetPageNumber(n);
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return jiExtSetPageNumber;
    }
    
    public boolean setAreaOfInterest(final int n, final int n2, final int n3, final int n4) {
        boolean jiExtSetAreaOfInterest = false;
        if (jiFDJV.booLoaded) {
            try {
                jiExtSetAreaOfInterest = this.jiExtSetAreaOfInterest(n, n2, n3, n4);
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return jiExtSetAreaOfInterest;
    }
    
    public boolean hasMoreData() {
        boolean jiExtHasMoreData = false;
        if (jiFDJV.booLoaded) {
            try {
                jiExtHasMoreData = this.jiExtHasMoreData();
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return jiExtHasMoreData;
    }
    
    public int getNextDataSet(final byte[] array) {
        int jiExtGetNextDataSet = 0;
        if (jiFDJV.booLoaded) {
            try {
                jiExtGetNextDataSet = this.jiExtGetNextDataSet(array, array.length);
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return jiExtGetNextDataSet;
    }
    
    public int getThumbNail(final int n, final int n2, final byte[] array, final int n3, final int n4) {
        int jiExtGetThumbNail = 0;
        if (jiFDJV.booLoaded) {
            try {
                jiExtGetThumbNail = this.jiExtGetThumbNail(n, n2, array, n3, n4);
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return jiExtGetThumbNail;
    }
    
    public final void releaseResources() {
        if (jiFDJV.booLoaded) {
            try {
                this.jiExtReleaseResourcesDjv();
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
    
    static {
        jiFDJV.booLoaded = false;
    }
}

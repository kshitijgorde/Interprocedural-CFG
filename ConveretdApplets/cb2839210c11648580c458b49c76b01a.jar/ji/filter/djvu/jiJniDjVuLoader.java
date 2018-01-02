// 
// Decompiled by Procyon v0.5.30
// 

package ji.filter.djvu;

import ji.io.ac;
import ji.io.q;
import ji.ext.f4;

public class jiJniDjVuLoader extends f4 implements gx
{
    private q cacheFile;
    private static boolean booLoaded;
    private static String libFileName;
    private static boolean booTested;
    
    public void setTested(final boolean booTested) {
        jiJniDjVuLoader.booTested = booTested;
    }
    
    public q getFileCache() {
        return this.cacheFile;
    }
    
    public void setFileCache(final q cacheFile) {
        this.cacheFile = cacheFile;
    }
    
    public void setLoaded(final boolean booLoaded) {
        jiJniDjVuLoader.booLoaded = booLoaded;
    }
    
    public void setLibFileName(final String s, final String s2) {
        try {
            if (s != null) {
                jiJniDjVuLoader.libFileName = ac.b(s, s2);
            }
            else {
                jiJniDjVuLoader.libFileName = null;
            }
        }
        catch (Exception ex) {}
    }
    
    public String getLibFileName(final String s) {
        return jiJniDjVuLoader.libFileName;
    }
    
    public boolean isFileCacheSet() {
        return this.cacheFile != null;
    }
    
    public boolean isTested() {
        return jiJniDjVuLoader.booTested;
    }
    
    public boolean isLoaded() {
        return jiJniDjVuLoader.booLoaded;
    }
    
    public void testLibrary(final Object[] array) throws Exception {
        jiJniDjVuLoader.booLoaded = true;
    }
    
    public String getLibName(final String s) {
        return jiJniDjVuLoader.libFileName;
    }
}

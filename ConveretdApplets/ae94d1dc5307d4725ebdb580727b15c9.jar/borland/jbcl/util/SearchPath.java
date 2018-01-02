// 
// Decompiled by Procyon v0.5.30
// 

package borland.jbcl.util;

import java.io.File;

public class SearchPath
{
    private String baseDirList;
    private String relDir;
    private String dirPath;
    
    public SearchPath(final String baseDirList, final String relDir) {
        this.baseDirList = baseDirList;
        this.relDir = relDir;
    }
    
    public File getFile(final String name) {
        if (this.dirPath != null) {
            return new File(String.valueOf(String.valueOf(String.valueOf(this.dirPath).concat(String.valueOf(File.separator))).concat(String.valueOf(name))).concat(String.valueOf(File.separator)));
        }
        File file = null;
        String baseDir = this.baseDirList;
        while (baseDir.length() > 0) {
            final int n = baseDir.indexOf(File.pathSeparatorChar);
            String p;
            if (n == -1) {
                p = baseDir;
                baseDir = "";
            }
            else {
                p = baseDir.substring(0, n);
                baseDir = baseDir.substring(n + 1);
            }
            this.dirPath = String.valueOf(String.valueOf(String.valueOf(p).concat(String.valueOf(File.separator))).concat(String.valueOf(this.relDir))).concat(String.valueOf(File.separator));
            file = new File(String.valueOf(this.dirPath).concat(String.valueOf(name)));
            if (file.exists()) {
                final String s = null;
                this.relDir = s;
                this.baseDirList = s;
                return file;
            }
        }
        if (file == null || !file.exists()) {
            this.dirPath = null;
        }
        return file;
    }
    
    public String getPath(final String name) {
        return this.getFile(name).getPath();
    }
    
    public String toString() {
        return (this.dirPath != null) ? this.dirPath : String.valueOf(String.valueOf(this.baseDirList).concat(String.valueOf("+"))).concat(String.valueOf(this.relDir));
    }
}

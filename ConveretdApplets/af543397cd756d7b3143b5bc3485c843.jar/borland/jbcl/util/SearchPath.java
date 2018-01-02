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
    
    public File getFile(final String s) {
        if (this.dirPath != null) {
            return new File(String.valueOf(String.valueOf(String.valueOf(this.dirPath).concat(String.valueOf(File.separator))).concat(String.valueOf(s))).concat(String.valueOf(File.separator)));
        }
        File file = null;
        String s2 = this.baseDirList;
        while (s2.length() > 0) {
            final int index = s2.indexOf(File.pathSeparatorChar);
            String substring;
            if (index == -1) {
                substring = s2;
                s2 = "";
            }
            else {
                substring = s2.substring(0, index);
                s2 = s2.substring(index + 1);
            }
            this.dirPath = String.valueOf(String.valueOf(String.valueOf(substring).concat(String.valueOf(File.separator))).concat(String.valueOf(this.relDir))).concat(String.valueOf(File.separator));
            file = new File(String.valueOf(this.dirPath).concat(String.valueOf(s)));
            if (file.exists()) {
                final String s3 = null;
                this.relDir = s3;
                this.baseDirList = s3;
                return file;
            }
        }
        if (file == null || !file.exists()) {
            this.dirPath = null;
        }
        return file;
    }
    
    public String getPath(final String s) {
        return this.getFile(s).getPath();
    }
    
    public String toString() {
        return (this.dirPath != null) ? this.dirPath : String.valueOf(String.valueOf(this.baseDirList).concat(String.valueOf("+"))).concat(String.valueOf(this.relDir));
    }
}

// 
// Decompiled by Procyon v0.5.30
// 

package ji.filter.uv;

import java.io.File;
import java.io.FilenameFilter;

class zj implements FilenameFilter
{
    public boolean accept(final File file, final String s) {
        return s != null && s.length() > 0 && s.toLowerCase().startsWith("vs") && s.toLowerCase().endsWith(".dll");
    }
}

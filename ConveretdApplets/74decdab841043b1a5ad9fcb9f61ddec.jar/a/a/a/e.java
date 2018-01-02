// 
// Decompiled by Procyon v0.5.30
// 

package a.a.a;

import java.io.File;
import java.io.FilenameFilter;

final class e implements FilenameFilter
{
    public final boolean accept(final File file, final String s) {
        return !s.endsWith(".lck") && s.contains(".log");
    }
}

// 
// Decompiled by Procyon v0.5.30
// 

package matt;

import java.io.File;
import java.io.FilenameFilter;

public class ABCFilter implements FilenameFilter
{
    public boolean accept(final File dir, final String name) {
        return name.contains(".abc") || name.contains(".ABC");
    }
}

// 
// Decompiled by Procyon v0.5.30
// 

package com.sun.jna.platform.mac;

import java.util.List;
import java.util.ArrayList;
import java.io.IOException;
import java.io.File;
import com.sun.jna.platform.FileUtils;

public class MacFileUtils extends FileUtils
{
    public boolean hasTrash() {
        return true;
    }
    
    public void moveToTrash(final File[] files) throws IOException {
        final File home = new File(System.getProperty("user.home"));
        final File trash = new File(home, ".Trash");
        if (!trash.exists()) {
            throw new IOException("The Trash was not found in its expected location (" + trash + ")");
        }
        final List<File> failed = new ArrayList<File>();
        for (int i = 0; i < files.length; ++i) {
            final File src = files[i];
            final File target = new File(trash, src.getName());
            if (!src.renameTo(target)) {
                failed.add(src);
            }
        }
        if (failed.size() > 0) {
            throw new IOException("The following files could not be trashed: " + failed);
        }
    }
}

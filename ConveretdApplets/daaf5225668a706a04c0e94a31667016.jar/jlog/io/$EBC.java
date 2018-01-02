// 
// Decompiled by Procyon v0.5.30
// 

package jlog.io;

import java.io.File;

public class $EBC
{
    public static boolean $FBC(final File file) {
        boolean b = true;
        if (file == null || !file.exists()) {
            return b;
        }
        if (file.isFile()) {
            return file.delete();
        }
        if (!file.isDirectory()) {
            return false;
        }
        final String[] list = file.list();
        if (list != null) {
            for (int i = 0; i < list.length; ++i) {
                if (list[i] != null && list[i].length() != 0) {
                    b &= $FBC(new File(file, list[i]));
                }
            }
        }
        return b && file.delete();
    }
}

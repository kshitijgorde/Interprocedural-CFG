// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.io;

import java.util.StringTokenizer;
import java.io.File;

public class FileUtilities
{
    protected FileUtilities() {
        throw new UnsupportedOperationException();
    }
    
    public static File findFileOnClassPath(final String s) {
        final String property = System.getProperty("java.class.path");
        final String property2 = System.getProperty("path.separator");
        final String property3 = System.getProperty("file.separator");
        final StringTokenizer stringTokenizer = new StringTokenizer(property, property2);
        while (stringTokenizer.hasMoreTokens()) {
            final String nextToken = stringTokenizer.nextToken();
            final File absoluteFile = new File(nextToken).getAbsoluteFile();
            if (absoluteFile.isFile()) {
                final File file = new File(absoluteFile.getParent() + property3 + s);
                if (file.exists()) {
                    return file;
                }
                continue;
            }
            else {
                final File file2 = new File(nextToken + property3 + s);
                if (file2.exists()) {
                    return file2;
                }
                continue;
            }
        }
        return null;
    }
}

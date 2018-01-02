// 
// Decompiled by Procyon v0.5.30
// 

package irc;

import java.io.FileInputStream;
import java.io.File;
import java.io.InputStream;

public class LocalFileHandler implements FileHandler
{
    public InputStream getInputStream(final String s) {
        try {
            return new FileInputStream(new File(s));
        }
        catch (Exception ex) {
            return null;
        }
    }
}

// 
// Decompiled by Procyon v0.5.30
// 

package jlog.util.$UD;

import java.io.InputStream;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.FileOutputStream;
import java.io.File;

public class $FE extends $XD
{
    public String[] $GE(final String[] array) {
        final String[] array2 = new String[array.length];
        int n = 0;
        for (int i = 0; i < array.length; ++i) {
            final String s = array[i];
            if (s.startsWith("-D")) {
                final String substring = s.substring(2);
                final int index = substring.indexOf(61);
                String substring2;
                String substring3;
                if (index > -1) {
                    substring2 = substring.substring(0, index);
                    substring3 = substring.substring(index + 1);
                }
                else {
                    substring2 = substring;
                    substring3 = "";
                }
                this.$IE(substring2, substring3);
            }
            else {
                array2[n++] = array[i];
            }
        }
        String[] array3;
        if (n == array.length) {
            array3 = array;
        }
        else {
            array3 = new String[n];
            if (n != 0) {
                System.arraycopy(array2, 0, array3, 0, n);
            }
        }
        return array3;
    }
    
    public void $JE(final String s) throws IOException {
        if (this.$KE()) {
            final String property = System.getProperty("user.home", "");
            final File file = new File(String.valueOf(property) + (property.equals("") ? "" : File.separator) + s.replace('/', File.separatorChar));
            new File(file.getParent()).mkdirs();
            final FileOutputStream fileOutputStream = new FileOutputStream(file);
            try {
                this.save(fileOutputStream, "");
            }
            finally {
                fileOutputStream.close();
            }
        }
    }
    
    public void $OE(final String s) throws IOException {
        final FileOutputStream fileOutputStream = new FileOutputStream(s.replace('/', File.separatorChar));
        try {
            super.$YD.save(fileOutputStream, "");
        }
        finally {
            fileOutputStream.close();
        }
    }
    
    public boolean $ZD(final String s, final String s2) throws IOException {
        boolean b = false;
        if (s2 != null && s2.length() != 0) {
            final String property = System.getProperty("user.home", ".");
            final String string = String.valueOf(property) + (property.endsWith(File.separator) ? "" : File.separator) + s2;
            try {
                final BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(string.replace('/', File.separatorChar)));
                try {
                    this.load(bufferedInputStream);
                    b = true;
                }
                finally {
                    bufferedInputStream.close();
                }
            }
            catch (IOException ex) {}
        }
        super.$DE = false;
        if (s != null && s.length() != 0) {
            final InputStream systemResourceAsStream = ClassLoader.getSystemResourceAsStream(s);
            try {
                this.$EE(systemResourceAsStream);
            }
            finally {
                systemResourceAsStream.close();
            }
        }
        return b;
    }
}

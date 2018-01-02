// 
// Decompiled by Procyon v0.5.30
// 

package netcharts.util;

import java.io.IOException;
import java.io.BufferedInputStream;
import java.util.Hashtable;
import java.io.InputStream;

public class NFKeyFilter
{
    public static StringBuffer filterStream(final InputStream inputStream, final Hashtable hashtable) throws IOException {
        final BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
        final StringBuffer sb = new StringBuffer();
        StringBuffer sb2 = new StringBuffer();
        int n = 0;
        while (true) {
            final int read = bufferedInputStream.read();
            if (read < 0) {
                break;
            }
            final char c = (char)read;
            if (n != 0) {
                if (Character.isLetterOrDigit(c) || c == '_') {
                    sb2.append(c);
                }
                else {
                    final String s = hashtable.get(sb2.toString());
                    if (s == null) {
                        sb.append(sb2.toString());
                    }
                    else {
                        sb.append(s);
                    }
                    sb2 = new StringBuffer();
                    n = 0;
                    if (c == '$') {
                        n = 1;
                        sb2.append(c);
                    }
                    else {
                        sb.append(c);
                    }
                }
            }
            else if (c == '$') {
                n = 1;
                sb2.append(c);
            }
            else {
                sb.append(c);
            }
        }
        inputStream.close();
        return sb;
    }
}

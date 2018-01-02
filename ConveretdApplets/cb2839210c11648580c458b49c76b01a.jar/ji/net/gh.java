// 
// Decompiled by Procyon v0.5.30
// 

package ji.net;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.ByteArrayOutputStream;

public class gh
{
    static int[] a;
    static String b;
    
    public static String a(final String s, final String s2) throws Exception {
        boolean b = false;
        int n = 0;
        final int n2 = 10;
        final StringBuffer sb = new StringBuffer(s.length());
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(n2);
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(byteArrayOutputStream, s2);
        for (int i = 0; i < s.length(); ++i) {
            int char1 = s.charAt(i);
            boolean b2 = false;
            for (int j = 0; j < gh.a.length; ++j) {
                if (gh.a[j] == char1) {
                    b2 = true;
                    break;
                }
            }
            if (b2) {
                if (char1 == 32) {
                    char1 = 43;
                    b = true;
                }
                sb.append((char)char1);
                n = 1;
            }
            else {
                try {
                    if (n != 0) {
                        outputStreamWriter = new OutputStreamWriter(byteArrayOutputStream, s2);
                        n = 0;
                    }
                    outputStreamWriter.write(char1);
                    if (char1 >= 55296 && char1 <= 56319 && i + 1 < s.length()) {
                        final char char2 = s.charAt(i + 1);
                        if (char2 >= '\udc00' && char2 <= '\udfff') {
                            outputStreamWriter.write(char2);
                            ++i;
                        }
                    }
                    outputStreamWriter.flush();
                }
                catch (IOException ex) {
                    byteArrayOutputStream.reset();
                    continue;
                }
                final byte[] byteArray = byteArrayOutputStream.toByteArray();
                for (int k = 0; k < byteArray.length; ++k) {
                    sb.append('%');
                    char forDigit = Character.forDigit(byteArray[k] >> 4 & 0xF, 16);
                    if (Character.isLetter(forDigit)) {
                        forDigit -= ' ';
                    }
                    sb.append(forDigit);
                    char forDigit2 = Character.forDigit(byteArray[k] & 0xF, 16);
                    if (Character.isLetter(forDigit2)) {
                        forDigit2 -= ' ';
                    }
                    sb.append(forDigit2);
                }
                byteArrayOutputStream.reset();
                b = true;
            }
        }
        return b ? sb.toString() : s;
    }
    
    static {
        gh.b = null;
        gh.a = new int[67];
        int n = 0;
        for (int i = 97; i <= 122; ++i) {
            gh.a[n++] = i;
        }
        for (int j = 65; j <= 90; ++j) {
            gh.a[n++] = j;
        }
        for (int k = 48; k <= 57; ++k) {
            gh.a[n++] = k;
        }
        gh.a[n++] = 32;
        gh.a[n++] = 45;
        gh.a[n++] = 95;
        gh.a[n++] = 46;
        gh.a[n] = 42;
    }
}

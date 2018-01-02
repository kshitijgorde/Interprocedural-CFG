// 
// Decompiled by Procyon v0.5.30
// 

package com.easypano.tourweaver;

import java.io.OutputStream;
import java.io.PrintStream;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.File;

public class k
{
    private static String[] z;
    
    public static void a(final String[] array) {
        final boolean v = g.v;
        final k k = new k();
        final File file = new File(com.easypano.tourweaver.k.z[1]);
        final File file2 = new File(com.easypano.tourweaver.k.z[0]);
        try {
            final FileInputStream fileInputStream = new FileInputStream(file);
            final FileOutputStream fileOutputStream = new FileOutputStream(file2);
            final PrintStream printStream = new PrintStream(fileOutputStream);
            final byte[] array2 = new byte[10000];
            final int read = fileInputStream.read(array2);
            int i = 0;
            while (i < read) {
                if (v) {
                    return;
                }
                if (i % 40 == 0) {
                    printStream.println();
                }
                printStream.print(array2[i] + ",");
                ++i;
                if (v) {
                    break;
                }
            }
            fileInputStream.close();
            fileOutputStream.close();
        }
        catch (Exception ex) {}
    }
    
    static {
        final String[] z = new String[2];
        final int n = 0;
        final char[] charArray = "A\"\u00043gB;\u0019".toCharArray();
        int length;
        int n3;
        final int n2 = n3 = (length = charArray.length);
        int n4 = 0;
        while (true) {
            Label_0098: {
                if (n2 > 1) {
                    break Label_0098;
                }
                length = (n3 = n4);
                do {
                    final char c = charArray[n3];
                    char c2 = '\0';
                    switch (n4 % 5) {
                        case 0: {
                            c2 = '6';
                            break;
                        }
                        case 1: {
                            c2 = 'C';
                            break;
                        }
                        case 2: {
                            c2 = 'm';
                            break;
                        }
                        case 3: {
                            c2 = 'G';
                            break;
                        }
                        default: {
                            c2 = 'I';
                            break;
                        }
                    }
                    charArray[length] = (char)(c ^ c2);
                    ++n4;
                } while (n2 == 0);
            }
            if (n2 > n4) {
                continue;
            }
            break;
        }
        z[n] = new String(charArray).intern();
        final int n5 = 1;
        final char[] charArray2 = "F1\b+&W'\u0004)._.\f ,\u0018$\u0004!".toCharArray();
        int length2;
        int n7;
        final int n6 = n7 = (length2 = charArray2.length);
        int n8 = 0;
        while (true) {
            Label_0214: {
                if (n6 > 1) {
                    break Label_0214;
                }
                length2 = (n7 = n8);
                do {
                    final char c3 = charArray2[n7];
                    char c4 = '\0';
                    switch (n8 % 5) {
                        case 0: {
                            c4 = '6';
                            break;
                        }
                        case 1: {
                            c4 = 'C';
                            break;
                        }
                        case 2: {
                            c4 = 'm';
                            break;
                        }
                        case 3: {
                            c4 = 'G';
                            break;
                        }
                        default: {
                            c4 = 'I';
                            break;
                        }
                    }
                    charArray2[length2] = (char)(c3 ^ c4);
                    ++n8;
                } while (n6 == 0);
            }
            if (n6 <= n8) {
                z[n5] = new String(charArray2).intern();
                k.z = z;
                return;
            }
            continue;
        }
    }
}

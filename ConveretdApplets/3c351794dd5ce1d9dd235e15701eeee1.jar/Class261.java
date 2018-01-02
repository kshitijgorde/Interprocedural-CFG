import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.DataFlavor;
import java.io.IOException;
import java.io.EOFException;
import java.io.DataInputStream;
import java.io.InputStream;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.File;

// 
// Decompiled by Procyon v0.5.30
// 

final class Class261
{
    static final void method3210(final byte b, final byte[] array, int n, final int n2, int n3, final int n4) {
        try {
            if (n4 < n2) {
                n3 += n4;
                n = -n4 + n2 >> 93881506;
                while (--n >= 0) {
                    array[n3++] = 1;
                    array[n3++] = 1;
                    array[n3++] = 1;
                    array[n3++] = 1;
                }
                n = (-n4 + n2 & 0x3);
                if (b != -104) {
                    method3210((byte)(-13), null, -55, -86, -52, 78);
                }
                while (--n >= 0) {
                    array[n3++] = 1;
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "qga.B(" + b + ',' + ((array != null) ? "{...}" : "null") + ',' + n + ',' + n2 + ',' + n3 + ',' + n4 + ')');
        }
    }
    
    static final void method3211(final byte[] array, final int n, final int n2, final File file) throws IOException {
        try {
            if (n < 122) {
                method3212(72);
            }
            final DataInputStream dataInputStream = new DataInputStream(new BufferedInputStream(new FileInputStream(file)));
            try {
                dataInputStream.readFully(array, 0, n2);
            }
            catch (EOFException ex2) {}
            dataInputStream.close();
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "qga.C(" + ((array != null) ? "{...}" : "null") + ',' + n + ',' + n2 + ',' + ((file != null) ? "{...}" : "null") + ')');
        }
    }
    
    static final void method3212(final int n) {
        try {
            if (Class98_Sub10_Sub15.anInt5618 < 102) {
                Class98_Sub10_Sub15.anInt5618 += 6;
            }
            if (~Class169.anInt1306 != 0x0 && ~Class343.method3819(-47) < ~Class198.aLong1525) {
                for (int anInt1306 = Class169.anInt1306; ~anInt1306 > ~Class147.aStringArray1189.length; ++anInt1306) {
                    if (Class147.aStringArray1189[anInt1306].startsWith("pause")) {
                        int int1 = 5;
                        try {
                            int1 = Integer.parseInt(Class147.aStringArray1189[anInt1306].substring(6));
                        }
                        catch (Exception ex2) {}
                        Class98_Sub46.method1525("Pausing for " + int1 + " seconds...", -109);
                        Class169.anInt1306 = 1 + anInt1306;
                        Class198.aLong1525 = Class343.method3819(n - 151) + int1 * 1000;
                        return;
                    }
                    Class45.aString382 = Class147.aStringArray1189[anInt1306];
                    Class295.method3484(false, false);
                }
                Class169.anInt1306 = -1;
            }
            if (Class319.anInt2699 != 0) {
                Class54.anInt3395 -= 5 * Class319.anInt2699;
                if (Class54.anInt3395 >= Class98_Sub28.anInt4080) {
                    Class54.anInt3395 = -1 + Class98_Sub28.anInt4080;
                }
                if (~Class54.anInt3395 > -1) {
                    Class54.anInt3395 = 0;
                }
                Class319.anInt2699 = 0;
            }
            int i = 0;
            if (n == 104) {
                while (i < Class329.anInt2765) {
                    final Interface7 interface7 = Class21_Sub1.anInterface7Array5385[i];
                    final int method17 = interface7.method17(true);
                    final char method18 = interface7.method15(13313);
                    final int method19 = interface7.method16((byte)82);
                    if (~method17 == 0xFFFFFFAB) {
                        Class295.method3484(false, false);
                    }
                    if (~method17 == 0xFFFFFFAF) {
                        Class295.method3484(true, false);
                    }
                    else if (~method17 != 0xFFFFFFBD || (0x4 & method19) == 0x0) {
                        if (method17 != 67 || ~(method19 & 0x4) == -1) {
                            if (method17 == 85 && Class198.anInt1524 > 0) {
                                Class45.aString382 = Class45.aString382.substring(0, -1 + Class198.anInt1524) + Class45.aString382.substring(Class198.anInt1524);
                                --Class198.anInt1524;
                            }
                            else if (method17 == 101 && Class198.anInt1524 < Class45.aString382.length()) {
                                Class45.aString382 = Class45.aString382.substring(0, Class198.anInt1524) + Class45.aString382.substring(1 + Class198.anInt1524);
                            }
                            else if (method17 == 96 && Class198.anInt1524 > 0) {
                                --Class198.anInt1524;
                            }
                            else if (~method17 != 0xFFFFFF9E || Class198.anInt1524 >= Class45.aString382.length()) {
                                if (method17 == 102) {
                                    Class198.anInt1524 = 0;
                                }
                                else if (method17 == 103) {
                                    Class198.anInt1524 = Class45.aString382.length();
                                }
                                else if (method17 != 104 || Class98_Sub31_Sub2.anInt5822 >= Class98_Sub46_Sub20.aStringArray6073.length) {
                                    if (~method17 == 0xFFFFFF96 && ~Class98_Sub31_Sub2.anInt5822 < -1) {
                                        --Class98_Sub31_Sub2.anInt5822;
                                        Class206.method2724((byte)123);
                                        Class198.anInt1524 = Class45.aString382.length();
                                    }
                                    else if (Class114.method2147(method18, n ^ 0x4) || ~method18 == 0xFFFFFFA3 || method18 == '/' || method18 == '.' || ~method18 == 0xFFFFFFC5 || method18 == ',' || method18 == ' ' || ~method18 == 0xFFFFFFA0 || ~method18 == 0xFFFFFFD2 || method18 == '+' || ~method18 == 0xFFFFFFA4 || method18 == ']') {
                                        Class45.aString382 = Class45.aString382.substring(0, Class198.anInt1524) + Class21_Sub1.anInterface7Array5385[i].method15(13313) + Class45.aString382.substring(Class198.anInt1524);
                                        ++Class198.anInt1524;
                                    }
                                }
                                else {
                                    ++Class98_Sub31_Sub2.anInt5822;
                                    Class206.method2724((byte)127);
                                    Class198.anInt1524 = Class45.aString382.length();
                                }
                            }
                            else {
                                ++Class198.anInt1524;
                            }
                        }
                        else if (Class8.aClipboard113 != null) {
                            final Transferable contents = Class8.aClipboard113.getContents(null);
                            if (contents != null) {
                                try {
                                    final String s = (String)contents.getTransferData(DataFlavor.stringFlavor);
                                    if (s != null) {
                                        Class98_Sub10_Sub13.method1044((byte)120, Class112.method2142(s, '\n', false));
                                    }
                                }
                                catch (Exception ex3) {}
                            }
                        }
                    }
                    else if (Class8.aClipboard113 != null) {
                        String string = "";
                        for (int j = -1 + Class98_Sub46_Sub20.aStringArray6073.length; j >= 0; --j) {
                            if (Class98_Sub46_Sub20.aStringArray6073[j] != null && ~Class98_Sub46_Sub20.aStringArray6073[j].length() < -1) {
                                string = string + Class98_Sub46_Sub20.aStringArray6073[j] + '\n';
                            }
                        }
                        Class8.aClipboard113.setContents(new StringSelection(string), null);
                    }
                    ++i;
                }
                Class98_Sub46_Sub1.anInt5949 = 0;
                Class329.anInt2765 = 0;
                Class98_Sub43.method1481(2);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "qga.A(" + n + ')');
        }
    }
}

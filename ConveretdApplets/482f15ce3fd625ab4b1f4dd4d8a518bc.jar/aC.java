import java.util.zip.ZipEntry;
import java.net.URLConnection;
import java.io.InputStream;
import java.util.zip.CRC32;
import java.io.FileNotFoundException;
import java.net.ConnectException;
import java.util.zip.ZipInputStream;
import java.util.zip.GZIPInputStream;
import java.io.BufferedInputStream;
import java.net.URL;

// 
// Decompiled by Procyon v0.5.30
// 

public final class aC
{
    public byte[] a;
    private au a;
    public at a;
    public int a;
    public String a;
    private boolean e;
    public int b;
    public int c;
    public int d;
    public int[] a;
    public int[] b;
    public int[] c;
    public boolean a;
    public boolean b;
    public boolean c;
    public boolean d;
    public long a;
    public int e;
    public String b;
    
    public aC(final au a) {
        this.a = null;
        this.a = null;
        this.a = 0;
        this.a = "No Mapper";
        this.e = false;
        this.b = 0;
        this.c = 0;
        this.d = 0;
        this.c = new int[0];
        this.a = false;
        this.b = false;
        this.c = false;
        this.d = false;
        this.a = 0L;
        this.e = 0;
        this.b = null;
        this.a = a;
    }
    
    public final boolean a(String s) {
        this.b = 0;
        URL url = null;
        int[] array;
        try {
            InputStream resourceAsStream;
            if (s == null || s == "") {
                resourceAsStream = this.getClass().getResourceAsStream("nescafe.nes");
                s = "nescafe.nes";
            }
            else {
                if (!s.toUpperCase().startsWith("HTTP://") && !s.toUpperCase().startsWith("FILE:") && !s.toUpperCase().startsWith("HTTPS://")) {
                    if (s.startsWith("/")) {
                        s = "http://" + this.a.a.getCodeBase().getHost() + s;
                    }
                    else {
                        s = this.a.a.getCodeBase() + (this.a.a.getCodeBase().toString().endsWith("/") ? "" : "/") + s;
                    }
                }
                url = new URL(s);
                this.a.a("Loading from " + url);
                try {
                    final URLConnection openConnection;
                    (openConnection = url.openConnection()).setUseCaches(false);
                    resourceAsStream = new BufferedInputStream(openConnection.getInputStream());
                }
                catch (Exception ex2) {
                    throw new Exception("Failed to open connection to website");
                }
            }
            int available = 0;
            Label_0434: {
                if (s.toUpperCase().endsWith(".NES.GZ")) {
                    available = (resourceAsStream = new GZIPInputStream(resourceAsStream)).available();
                }
                else {
                    Label_0431: {
                        if (!s.toUpperCase().endsWith(".ZIP") && !s.toUpperCase().endsWith(".ZIP.PHP")) {
                            if (!s.toUpperCase().endsWith(".ZIP.ASP")) {
                                break Label_0431;
                            }
                        }
                        try {
                            ZipInputStream zipInputStream;
                            ZipEntry zipEntry;
                            for (zipEntry = (zipInputStream = new ZipInputStream(resourceAsStream)).getNextEntry(); zipEntry != null && (zipEntry.isDirectory() || !zipEntry.getName().toUpperCase().endsWith("NES")); zipEntry = zipInputStream.getNextEntry()) {}
                            if (zipEntry == null) {
                                zipInputStream.close();
                                resourceAsStream.close();
                                this.b = 21;
                                return true;
                            }
                            resourceAsStream = zipInputStream;
                            available = (int)zipEntry.getSize();
                            break Label_0434;
                        }
                        catch (Exception ex3) {
                            throw new Exception("Failed to read the ZIP file containing the game");
                        }
                    }
                    available = -1;
                }
            }
            if (available == -1) {
                array = new int[0];
            }
            else {
                array = new int[available];
            }
            final float n = available;
            this.a.a.a.a(0.0f);
            Label_0777: {
                if (available <= 0) {
                    final byte[] array2 = new byte[2097152];
                    int read;
                    for (int n2 = 0; n2 + 4096 <= array2.length; n2 += read) {
                        if ((read = resourceAsStream.read(array2, n2, 4096)) < 0) {
                            this.a.a("Read " + n2 + " bytes for Cart");
                            array = new int[n2];
                            this.a = new byte[n2];
                            for (int i = 0; i < n2; ++i) {
                                array[i] = (array2[i] + 256 & 0xFF);
                                this.a[i] = array2[i];
                            }
                            break Label_0777;
                        }
                    }
                    this.b = 20;
                    throw new Exception("NES ROM too large");
                }
                this.a = new byte[available];
                int n3 = 0;
                final byte[] array3 = new byte[8192];
                int j = 0;
                while (j < available) {
                    final int read2;
                    if ((read2 = resourceAsStream.read(array3)) < 0) {
                        resourceAsStream.close();
                        this.b = 20;
                        return true;
                    }
                    for (int k = 0; k < read2; ++k) {
                        array[j++] = (array3[k] + 256 & 0xFF);
                        this.a[n3++] = array3[k];
                    }
                    this.a.a.a.a(j / n);
                }
            }
            resourceAsStream.close();
            this.a.a.a.a(1.0f);
            if (array[0] == 78 && array[1] == 69 && array[2] == 78 && array[3] == 67 && array[4] == 82 && array[5] == 82 && array[6] == 79 && array[7] == 77) {
                final String upperCase = (this.a.a.getCodeBase() + "nescafe.jar").toUpperCase();
                char c = '\0';
                for (char c2 = '\0'; c2 < upperCase.length(); ++c2) {
                    c += (char)(upperCase.charAt(c2) * c2);
                }
                int n4 = (c & '\u00ff') >> 0;
                final char c3 = (char)((c & '\uff00') >> 8);
                for (int l = 0; l < array.length - 16; ++l) {
                    array[l] = (n4 ^ array[l + 16]);
                    n4 = (n4 + c3 & '\u00ff');
                }
                if (array[0] != 78 || array[1] != 69 || array[2] != 83 || array[3] != 26) {
                    this.b = 26;
                    return true;
                }
            }
            if (array[0] != 78 || array[1] != 69 || array[2] != 83 || array[3] != 26) {
                this.b = 16;
                return true;
            }
            this.c = array[4];
            this.d = array[5];
            final int n5 = array[6];
            this.a = ((n5 & 0x1) != 0x0);
            this.d = ((n5 & 0x2) != 0x0);
            this.c = ((n5 & 0x4) != 0x0);
            this.b = ((n5 & 0x8) != 0x0);
            this.a = ((n5 >> 4 & 0xF) | (array[7] & 0xF0));
            for (int n6 = 0; n6 < 8; ++n6) {
                if (array[8 + n6] != 0) {
                    this.a &= 0xF;
                }
            }
        }
        catch (ConnectException ex4) {
            if (url != null && this.a.a != null) {
                final an an;
                (an = new an(12, this.a)).a("Unable to connect to the URL below:", "URL: " + url.toString(), "Host: " + this.a.a.getCodeBase().getHost());
                this.a.a.a(an);
            }
            this.b = 27;
            return true;
        }
        catch (FileNotFoundException ex5) {
            this.b = 24;
            return true;
        }
        catch (Exception ex) {
            final an an2;
            (an2 = new an(12, this.a)).a("An error occurred when opening the file:", ex.getMessage());
            this.a.a.a(an2);
            if (s.toUpperCase().endsWith(".ZIP")) {
                this.b = 28;
                return true;
            }
            if (s.toUpperCase().endsWith(".NES.GZ")) {
                this.b = 28;
                return true;
            }
            this.b = 20;
            return true;
        }
        this.e = 0;
        this.b = null;
        this.a.b = false;
        int[] a = null;
        String s2;
        if (s.length() > 4 && (s.toUpperCase().endsWith(".NES") || s.toUpperCase().endsWith(".ZIP")) && (s2 = this.a.a.getParameter("IPSURL")) != null && !s2.equals("")) {
            if (!s2.toUpperCase().startsWith("HTTP:") && !s2.toUpperCase().startsWith("HTTPS:")) {
                s2 = this.a.a.getCodeBase() + s2;
            }
            final av av;
            if ((a = (av = new av()).a(array, s2)) != null) {
                this.e = 2;
                this.b = "Game successfully patched by IPS";
                this.a.b = true;
            }
            else {
                this.e = 1;
                this.b = "FAILURE: " + av.a;
            }
        }
        final int[] array4 = (this.e == 2) ? a : array;
        try {
            if (this.c) {
                this.a.a("ROM has Trainer");
                this.c = new int[512];
                for (int n7 = 0; n7 < this.c.length; ++n7) {
                    this.c[n7] = array4[16 + n7];
                }
            }
            this.a.a("cartdata size=" + array4.length);
            this.a.a("numProgramROMBanks=" + this.c);
            this.a = new int[16384 * this.c];
            for (int n8 = 0; n8 < this.a.length; ++n8) {
                this.a[n8] = array4[this.c.length + 16 + n8];
            }
            this.a.a("numCharacterROMBanks=" + this.d);
            this.b = new int[8192 * this.d];
            for (int n9 = 0; n9 < this.b.length; ++n9) {
                this.b[n9] = array4[this.c.length + this.a.length + 16 + n9];
            }
        }
        catch (Exception ex6) {
            this.b = 20;
            return true;
        }
        final byte[] array5 = new byte[this.a.length + this.b.length];
        for (int n10 = 0; n10 < array5.length; ++n10) {
            array5[n10] = (byte)(array[n10 + 16] & 0xFF);
        }
        final CRC32 crc32;
        (crc32 = new CRC32()).update(array5);
        this.a = crc32.getValue();
        if (this.a == 674943524L) {
            this.a = 32;
        }
        else if (this.a == 283262982L) {
            this.a = 48;
        }
        else if (this.a == 2055219138L) {
            this.a = 47;
        }
        switch (this.a) {
            case 0: {
                this.a = "No Mapper";
                this.e = true;
                this.a = new at();
                break;
            }
            case 1: {
                this.a = "Nintendo MMC1";
                this.e = true;
                this.a = new u();
                break;
            }
            case 2: {
                this.a = "Simple PROM Switch (UNROM)";
                this.e = true;
                this.a = new q(2);
                break;
            }
            case 3: {
                this.a = "Simple VROM Switch (CNROM)";
                this.e = true;
                this.a = new t();
                break;
            }
            case 4: {
                this.a = "Nintendo MMC3";
                this.e = true;
                this.a = new s();
                break;
            }
            case 5: {
                this.a = "Nintendo MMC5";
                this.e = true;
                this.a = new B();
                break;
            }
            case 6: {
                this.a = "Konami FFE F4xxx";
                this.e = true;
                this.a = new x();
                break;
            }
            case 7: {
                this.a = "Rare AOROM";
                this.e = true;
                this.a = new q(7);
                break;
            }
            case 8: {
                this.a = "Konami FFE F3xxx";
                this.e = true;
                this.a = new q(8);
                break;
            }
            case 9: {
                this.a = "Nintendo MMC2";
                this.e = true;
                this.a = new H();
                break;
            }
            case 10: {
                this.a = "Nintendo MMC4";
                this.e = true;
                this.a = new ar();
                break;
            }
            case 11: {
                this.a = "Color Dreams";
                this.e = true;
                this.a = new q(11);
                break;
            }
            case 13: {
                this.a = "CPROM";
                this.e = false;
                this.a = null;
                break;
            }
            case 15: {
                this.a = "100-in-1";
                this.e = true;
                this.a = new Y();
                break;
            }
            case 16: {
                this.a = "Bandai Chip";
                this.e = false;
                this.a = null;
                break;
            }
            case 17: {
                this.a = "Konami FFE F8xxx";
                this.e = true;
                this.a = new U();
                break;
            }
            case 18: {
                this.a = "Jaleco SS8806";
                this.e = true;
                this.a = new X();
                break;
            }
            case 19: {
                this.a = "Namcot 106";
                this.e = false;
                this.a = null;
                break;
            }
            case 20: {
                this.a = "Nintendo Disk System";
                this.e = false;
                this.a = null;
                break;
            }
            case 21: {
                this.a = "Konami VRC4 2A";
                this.e = true;
                this.a = new aM();
                break;
            }
            case 22: {
                this.a = "Konami VRC4 type 1B";
                this.e = true;
                this.a = new aN();
                break;
            }
            case 23: {
                this.a = "Konami VRC2 type B";
                this.e = true;
                this.a = new aw();
                break;
            }
            case 24: {
                this.a = "Konami VRC6";
                this.e = false;
                this.a = null;
                break;
            }
            case 25: {
                this.a = "Konami VRC4";
                this.e = false;
                this.a = null;
                break;
            }
            case 26: {
                this.a = "Konami VRC6V";
                this.e = false;
                this.a = null;
                break;
            }
            case 32: {
                this.a = "Irem G-101";
                this.e = true;
                this.a = new m();
                break;
            }
            case 33: {
                this.a = "Taito TC0190 TC0350";
                this.e = true;
                this.a = new l();
                break;
            }
            case 34: {
                this.a = "Nina-1";
                this.e = true;
                this.a = new q(34);
                break;
            }
            case 40: {
                this.a = "SMB2J";
                this.e = true;
                this.a = new L();
                break;
            }
            case 41: {
                this.a = "Caltron 6-in-1";
                this.e = true;
                this.a = new N();
                break;
            }
            case 42: {
                this.a = "Mario Baby";
                this.e = false;
                this.a = null;
                break;
            }
            case 43: {
                this.a = "SMB2J";
                this.e = false;
                this.a = null;
                break;
            }
            case 44: {
                this.a = "Super Hik 7-in-1";
                this.e = false;
                this.a = null;
                break;
            }
            case 45: {
                this.a = "1000000-in-1";
                this.e = false;
                this.a = null;
                break;
            }
            case 46: {
                this.a = "Rumble Station";
                this.e = true;
                this.a = new G();
                break;
            }
            case 47: {
                this.a = "NES-QJ";
                this.e = true;
                this.a = new C();
                break;
            }
            case 48: {
                this.a = "Taito TC190V";
                this.e = true;
                this.a = new E();
                break;
            }
            case 49: {
                this.a = "Super Hik 4-in-1";
                this.e = false;
                this.a = null;
                break;
            }
            case 50: {
                this.a = "SMB2J";
                this.e = true;
                this.a = new aq();
                break;
            }
            case 51: {
                this.a = "11-in-1 Ball Games";
                this.e = true;
                this.a = new ao();
                break;
            }
            case 52: {
                this.a = "Mario 7-in-1";
                this.e = false;
                this.a = null;
                break;
            }
            case 57: {
                this.a = "54-in-1";
                this.e = true;
                this.a = new af();
                break;
            }
            case 58: {
                this.a = "68-in-1";
                this.e = true;
                this.a = new q(58);
                break;
            }
            case 60: {
                this.a = "65-in-1";
                this.e = true;
                this.a = new q(60);
                break;
            }
            case 62: {
                this.a = "Mapper 62";
                this.e = true;
                this.a = new q(62);
                break;
            }
            case 64: {
                this.a = "Tengen Rambo-1";
                this.e = true;
                this.a = new aG();
                break;
            }
            case 65: {
                this.a = "Irem H-3001";
                this.e = true;
                this.a = new aF();
                break;
            }
            case 66: {
                this.a = "Bandai 74161/32";
                this.e = true;
                this.a = new q(66);
                break;
            }
            case 67: {
                this.a = "Sunsoft Mapper 3";
                this.e = true;
                this.a = new aA();
                break;
            }
            case 68: {
                this.a = "Sunsoft Mapper 4";
                this.e = true;
                this.a = new aB();
                break;
            }
            case 69: {
                this.a = "Sunsoft Mapper 5";
                this.e = false;
                this.a = null;
                break;
            }
            case 70: {
                this.a = "74161/32";
                this.e = true;
                this.a = new c();
                break;
            }
            case 71: {
                this.a = "Camerica Mapper";
                this.e = true;
                this.a = new q(71);
                break;
            }
            case 72: {
                this.a = "Jaleco Early Mapper 0";
                this.e = true;
                this.a = new q(72);
                break;
            }
            case 73: {
                this.a = "Konami VRC3";
                this.e = true;
                this.a = new b();
                break;
            }
            case 75: {
                this.a = "Jaleco/Konami VRC1";
                this.e = true;
                this.a = new e();
                break;
            }
            case 76: {
                this.a = "Namco 109";
                this.e = true;
                this.a = new d();
                break;
            }
            case 77: {
                this.a = "Irem Early Mapper 0";
                this.e = true;
                this.a = new q(77);
                break;
            }
            case 78: {
                this.a = "Jaleco 74161/32";
                this.e = true;
                this.a = new q(78);
                break;
            }
            case 79: {
                this.a = "Nina-3 (AVE)";
                this.e = true;
                this.a = new q(79);
                break;
            }
            case 80: {
                this.a = "Taito X-005";
                this.e = true;
                this.a = new A();
                break;
            }
            case 82: {
                this.a = "Taito C075";
                this.e = true;
                this.a = new F();
                break;
            }
            case 83: {
                this.a = "Cony";
                this.e = false;
                this.a = null;
                break;
            }
            case 85: {
                this.a = "Konami VRC7";
                this.e = false;
                this.a = null;
                break;
            }
            case 86: {
                this.a = "Jaleco Early Mapper 2";
                this.e = true;
                this.a = new q(86);
                break;
            }
            case 87: {
                this.a = "Konami 74161/32";
                this.e = true;
                this.a = new q(87);
                break;
            }
            case 88: {
                this.a = "Namco 118";
                this.e = true;
                this.a = new K();
                break;
            }
            case 89: {
                this.a = "Sunsoft Early Mapper";
                this.e = true;
                this.a = new q(89);
                break;
            }
            case 90: {
                this.a = "PC-JY-??";
                this.e = false;
                this.a = null;
                break;
            }
            case 91: {
                this.a = "PC-HK-SF3";
                this.e = true;
                this.a = new W();
                break;
            }
            case 92: {
                this.a = "Jaleco Early Mapper 1";
                this.e = true;
                this.a = new q(92);
                break;
            }
            case 93: {
                this.a = "Sunsoft 74161/32";
                this.e = true;
                this.a = new q(93);
                break;
            }
            case 94: {
                this.a = "Capcom 74161/32";
                this.e = true;
                this.a = new q(94);
                break;
            }
            case 95: {
                this.a = "Namco 106M";
                this.e = false;
                this.a = null;
                break;
            }
            case 96: {
                this.a = "Bandai 74161/32";
                this.e = false;
                this.a = null;
                break;
            }
            case 97: {
                this.a = "Irem 74161/32";
                this.e = true;
                this.a = new q(97);
                break;
            }
            case 99: {
                this.a = "VS Unisystem";
                this.e = true;
                this.a = new q(99);
                break;
            }
            case 100: {
                this.a = "Nesticle MMC3";
                this.e = false;
                this.a = null;
                break;
            }
            case 101: {
                this.a = "Jaleco 74161/32";
                this.e = true;
                this.a = new q(101);
                break;
            }
            case 105: {
                this.a = "Nintendo World Championship";
                this.e = true;
                this.a = new as();
                break;
            }
            case 112: {
                this.a = "PC-Asder";
                this.e = false;
                this.a = null;
                break;
            }
            case 113: {
                this.a = "PC-Sachen/Hacker";
                this.e = true;
                this.a = new ad();
                break;
            }
            case 117: {
                this.a = "PC-Future";
                this.e = true;
                this.a = new ai();
                break;
            }
            case 118: {
                this.a = "IQS MMC3";
                this.e = true;
                this.a = new ah();
                break;
            }
            case 119: {
                this.a = "TQ-ROM";
                this.e = true;
                this.a = new V();
                break;
            }
            case 122: {
                this.a = "Sunsoft 74161/32";
                this.e = true;
                this.a = new az();
                break;
            }
            case 140: {
                this.a = "Mapper 140";
                this.e = true;
                this.a = new q(140);
                break;
            }
            case 151: {
                this.a = "VS Unisystem (Konami)";
                this.e = true;
                this.a = new q(151);
                break;
            }
            case 160: {
                this.a = "PC-Aladdin";
                this.e = false;
                this.a = null;
                break;
            }
            case 180: {
                this.a = "Nichibutsu";
                this.e = true;
                this.a = new q(180);
                break;
            }
            case 181: {
                this.a = "Hacker Internation Type 2";
                this.e = true;
                this.a = new q(181);
                break;
            }
            case 182: {
                this.a = "PC-SuperDonkeyKong";
                this.e = true;
                this.a = new Q();
                break;
            }
            case 183: {
                this.a = "Gimmick (Bootleg)";
                this.e = true;
                this.a = new P();
                break;
            }
            case 184: {
                this.a = "Sunsoft 74161/32";
                this.e = true;
                this.a = new q(184);
                break;
            }
            case 185: {
                this.a = "CHR-ROM Disable Protect";
                this.e = true;
                this.a = new q(185);
                break;
            }
            case 187: {
                this.a = "Street Fighter Zero 2 97";
                this.e = false;
                this.a = null;
                break;
            }
            case 188: {
                this.a = "Bandai Karaoke Studio";
                this.e = false;
                this.a = null;
                break;
            }
            case 189: {
                this.a = "Street Fighter 2 Yoko";
                this.e = true;
                this.a = new z();
                break;
            }
            case 222: {
                this.a = "Mapper 0xDE";
                this.e = true;
                this.a = new q(222);
                break;
            }
            case 225: {
                this.a = "72-in-1";
                this.e = true;
                this.a = new i();
                break;
            }
            case 226: {
                this.a = "76-in-1";
                this.e = true;
                this.a = new j();
                break;
            }
            case 227: {
                this.a = "1200-in-1";
                this.e = true;
                this.a = new k();
                break;
            }
            case 228: {
                this.a = "Action 52";
                this.e = true;
                this.a = new n();
                break;
            }
            case 229: {
                this.a = "31-in-1";
                this.e = true;
                this.a = new q(229);
                break;
            }
            case 230: {
                this.a = "22-in-1";
                this.e = false;
                this.a = null;
                break;
            }
            case 231: {
                this.a = "20-in-1";
                this.e = true;
                this.a = new q(231);
                break;
            }
            case 232: {
                this.a = "Quattro Games";
                this.e = true;
                this.a = new q(232);
                break;
            }
            case 233: {
                this.a = "42-in-1";
                this.e = true;
                this.a = new q(233);
                break;
            }
            case 234: {
                this.a = "Maxi 15";
                this.e = false;
                this.a = null;
                break;
            }
            case 235: {
                this.a = "150-in-1";
                this.e = false;
                this.a = null;
                break;
            }
            case 236: {
                this.a = "800-in-1";
                this.e = true;
                this.a = new M();
                break;
            }
            case 237: {
                this.a = "70-in-1";
                this.e = false;
                this.a = null;
                break;
            }
            case 240: {
                this.a = "Gen Ke Le Zhuan";
                this.e = true;
                this.a = new q(240);
                break;
            }
            case 242: {
                this.a = "Wai Xing Zhan Shi";
                this.e = true;
                this.a = new q(242);
                break;
            }
            case 243: {
                this.a = "PC-Sachen/Hacker";
                this.e = true;
                this.a = new g();
                break;
            }
            case 244: {
                this.a = "Mapper 244";
                this.e = true;
                this.a = new q(244);
                break;
            }
            case 245: {
                this.a = "Yong Zhe Dou E Long";
                this.e = true;
                this.a = new f();
                break;
            }
            case 246: {
                this.a = "Phone Serm Berm";
                this.e = true;
                this.a = new q(246);
                break;
            }
            case 248: {
                this.a = "Bao Qing Tian";
                this.e = true;
                this.a = new o();
                break;
            }
            case 251: {
                this.a = "Mapper 251";
                this.e = true;
                this.a = new ax();
                break;
            }
            case 255: {
                this.a = "110-in-1";
                this.e = true;
                this.a = new aJ();
                break;
            }
            default: {
                this.a = "Unknown (0x" + aK.a(this.a, 2) + ")";
                this.e = false;
                this.a = null;
                break;
            }
        }
        if (!this.e || this.a == null) {
            this.b = 18;
            return true;
        }
        if (this.c == 0) {
            this.b = 19;
            return true;
        }
        return false;
    }
}

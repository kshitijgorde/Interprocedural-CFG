// 
// Decompiled by Procyon v0.5.30
// 

package de.mud.ssh;

import cryptix.crypt.MD5;
import java.util.Date;
import java.io.IOException;

class SshMisc
{
    private static long[] crc32_tab;
    
    public static String getString(int offset, final byte[] byteArray) throws IOException {
        short d0 = byteArray[offset++];
        short d2 = byteArray[offset++];
        short d3 = byteArray[offset++];
        short d4 = byteArray[offset++];
        if (d0 < 0) {
            d0 += 256;
        }
        if (d2 < 0) {
            d2 += 256;
        }
        if (d3 < 0) {
            d3 += 256;
        }
        if (d4 < 0) {
            d4 += 256;
        }
        final int length = d0 * 16777216 + d2 * 65536 + d3 * 256 + d4;
        String str = "";
        for (int i = 0; i < length; ++i) {
            if (byteArray[offset] > 0) {
                str += (char)byteArray[offset++];
            }
            else {
                str += (char)(256 + byteArray[offset++]);
            }
        }
        return str;
    }
    
    public static byte getNotZeroRandomByte() {
        final Date date = new Date();
        final String randomString = String.valueOf(date.getTime() * Math.random());
        final byte[] randomBytes = MD5.hash(randomString);
        for (int i = 0; i < 20; ++i) {
            byte b = 0;
            if (i < randomBytes.length) {
                b = randomBytes[i];
            }
            if (b != 0) {
                return b;
            }
        }
        return getNotZeroRandomByte();
    }
    
    public static byte[] addArrayOfBytes(final byte[] a, final byte[] b) {
        if (a == null) {
            return b;
        }
        if (b == null) {
            return a;
        }
        final byte[] temp = new byte[a.length + b.length];
        for (int i = 0; i < a.length; ++i) {
            temp[i] = a[i];
        }
        for (int j = 0; j < b.length; ++j) {
            temp[j + a.length] = b[j];
        }
        return temp;
    }
    
    public static byte[] XORArrayOfBytes(final byte[] a, final byte[] b) {
        if (a == null) {
            return null;
        }
        if (b == null) {
            return null;
        }
        if (a.length != b.length) {
            return null;
        }
        final byte[] result = new byte[a.length];
        for (int i = 0; i < result.length; ++i) {
            result[i] = (byte)(((a[i] & 0xFF) ^ (b[i] & 0xFF)) & 0xFF);
        }
        return result;
    }
    
    public static byte[] getMpInt(int offset, final byte[] byteArray) throws IOException {
        short d0 = byteArray[offset++];
        short d2 = byteArray[offset++];
        if (d0 < 0) {
            d0 += 256;
        }
        if (d2 < 0) {
            d2 += 256;
        }
        final int byteLength = (d0 * 256 + d2 + 7) / 8;
        final byte[] MpInt = new byte[byteLength];
        for (int i = 0; i < byteLength; ++i) {
            MpInt[i] = byteArray[offset++];
        }
        return MpInt;
    }
    
    public static byte[] createString(final String str) throws IOException {
        final int length = str.length();
        final byte[] value = new byte[4 + length];
        value[3] = (byte)(length & 0xFF);
        value[2] = (byte)(length >> 8 & 0xFF);
        value[1] = (byte)(length >> 16 & 0xFF);
        value[0] = (byte)(length >> 24 & 0xFF);
        final byte[] strByte = str.getBytes();
        for (int i = 0; i < length; ++i) {
            value[i + 4] = strByte[i];
        }
        return value;
    }
    
    public static long crc32(final byte[] s, final int len) {
        long crc32val = 0L;
        for (int i = 0; i < len; ++i) {
            crc32val = (SshMisc.crc32_tab[(int)((crc32val ^ s[i]) & 0xFFL)] ^ crc32val >> 8);
        }
        return crc32val;
    }
    
    static {
        SshMisc.crc32_tab = new long[] { 0L, 1996959894L, 3993919788L, 2567524794L, 124634137L, 1886057615L, 3915621685L, 2657392035L, 249268274L, 2044508324L, 3772115230L, 2547177864L, 162941995L, 2125561021L, 3887607047L, 2428444049L, 498536548L, 1789927666L, 4089016648L, 2227061214L, 450548861L, 1843258603L, 4107580753L, 2211677639L, 325883990L, 1684777152L, 4251122042L, 2321926636L, 335633487L, 1661365465L, 4195302755L, 2366115317L, 997073096L, 1281953886L, 3579855332L, 2724688242L, 1006888145L, 1258607687L, 3524101629L, 2768942443L, 901097722L, 1119000684L, 3686517206L, 2898065728L, 853044451L, 1172266101L, 3705015759L, 2882616665L, 651767980L, 1373503546L, 3369554304L, 3218104598L, 565507253L, 1454621731L, 3485111705L, 3099436303L, 671266974L, 1594198024L, 3322730930L, 2970347812L, 795835527L, 1483230225L, 3244367275L, 3060149565L, 1994146192L, 31158534L, 2563907772L, 4023717930L, 1907459465L, 112637215L, 2680153253L, 3904427059L, 2013776290L, 251722036L, 2517215374L, 3775830040L, 2137656763L, 141376813L, 2439277719L, 3865271297L, 1802195444L, 476864866L, 2238001368L, 4066508878L, 1812370925L, 453092731L, 2181625025L, 4111451223L, 1706088902L, 314042704L, 2344532202L, 4240017532L, 1658658271L, 366619977L, 2362670323L, 4224994405L, 1303535960L, 984961486L, 2747007092L, 3569037538L, 1256170817L, 1037604311L, 2765210733L, 3554079995L, 1131014506L, 879679996L, 2909243462L, 3663771856L, 1141124467L, 855842277L, 2852801631L, 3708648649L, 1342533948L, 654459306L, 3188396048L, 3373015174L, 1466479909L, 544179635L, 3110523913L, 3462522015L, 1591671054L, 702138776L, 2966460450L, 3352799412L, 1504918807L, 783551873L, 3082640443L, 3233442989L, 3988292384L, 2596254646L, 62317068L, 1957810842L, 3939845945L, 2647816111L, 81470997L, 1943803523L, 3814918930L, 2489596804L, 225274430L, 2053790376L, 3826175755L, 2466906013L, 167816743L, 2097651377L, 4027552580L, 2265490386L, 503444072L, 1762050814L, 4150417245L, 2154129355L, 426522225L, 1852507879L, 4275313526L, 2312317920L, 282753626L, 1742555852L, 4189708143L, 2394877945L, 397917763L, 1622183637L, 3604390888L, 2714866558L, 953729732L, 1340076626L, 3518719985L, 2797360999L, 1068828381L, 1219638859L, 3624741850L, 2936675148L, 906185462L, 1090812512L, 3747672003L, 2825379669L, 829329135L, 1181335161L, 3412177804L, 3160834842L, 628085408L, 1382605366L, 3423369109L, 3138078467L, 570562233L, 1426400815L, 3317316542L, 2998733608L, 733239954L, 1555261956L, 3268935591L, 3050360625L, 752459403L, 1541320221L, 2607071920L, 3965973030L, 1969922972L, 40735498L, 2617837225L, 3943577151L, 1913087877L, 83908371L, 2512341634L, 3803740692L, 2075208622L, 213261112L, 2463272603L, 3855990285L, 2094854071L, 198958881L, 2262029012L, 4057260610L, 1759359992L, 534414190L, 2176718541L, 4139329115L, 1873836001L, 414664567L, 2282248934L, 4279200368L, 1711684554L, 285281116L, 2405801727L, 4167216745L, 1634467795L, 376229701L, 2685067896L, 3608007406L, 1308918612L, 956543938L, 2808555105L, 3495958263L, 1231636301L, 1047427035L, 2932959818L, 3654703836L, 1088359270L, 936918000L, 2847714899L, 3736837829L, 1202900863L, 817233897L, 3183342108L, 3401237130L, 1404277552L, 615818150L, 3134207493L, 3453421203L, 1423857449L, 601450431L, 3009837614L, 3294710456L, 1567103746L, 711928724L, 3020668471L, 3272380065L, 1510334235L, 755167117L };
    }
}

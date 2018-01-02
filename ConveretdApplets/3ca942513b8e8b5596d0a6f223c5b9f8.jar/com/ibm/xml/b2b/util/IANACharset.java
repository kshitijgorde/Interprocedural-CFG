// 
// Decompiled by Procyon v0.5.30
// 

package com.ibm.xml.b2b.util;

public class IANACharset
{
    public static final int UTF_8 = 0;
    public static final int US_ASCII = 1;
    public static final int ISO_8859_1 = 2;
    public static final int ISO_8859_2 = 3;
    public static final int ISO_8859_3 = 4;
    public static final int ISO_8859_4 = 5;
    public static final int ISO_8859_5 = 6;
    public static final int ISO_8859_6 = 7;
    public static final int ISO_8859_7 = 8;
    public static final int ISO_8859_8 = 9;
    public static final int ISO_8859_9 = 10;
    public static final int UTF_16 = 11;
    public static final int UTF_16BE = 12;
    public static final int UTF_16LE = 13;
    public static final int UTF_32BE = 14;
    public static final int UTF_32LE = 15;
    public static final int ISO_2022_JP = 16;
    public static final int SHIFT_JIS = 17;
    public static final int EUC_JP = 18;
    public static final int GB2312 = 19;
    public static final int BIG5 = 20;
    public static final int EUC_KR = 21;
    public static final int ISO_2022_KR = 22;
    public static final int KOI8_R = 23;
    public static final int ISO_2022_CN = 24;
    public static final int WINDOWS_31J = 25;
    public static final int JIS_C6226_1983 = 26;
    public static final int JIS_X0212_1990 = 27;
    public static final int JIS_X0201 = 28;
    public static final int ISO_10646_UCS_2 = 29;
    public static final int ISO_10646_UCS_4 = 30;
    public static final int IBM037 = 31;
    public static final int IBM273 = 32;
    public static final int IBM277 = 33;
    public static final int IBM278 = 34;
    public static final int IBM280 = 35;
    public static final int IBM284 = 36;
    public static final int IBM285 = 37;
    public static final int IBM297 = 38;
    public static final int IBM420 = 39;
    public static final int IBM424 = 40;
    public static final int IBM500 = 41;
    public static final int IBM868 = 42;
    public static final int IBM869 = 43;
    public static final int IBM870 = 44;
    public static final int IBM871 = 45;
    public static final int IBM918 = 46;
    public static final int IBM01140 = 47;
    public static final int IBM01141 = 48;
    public static final int IBM01142 = 49;
    public static final int IBM01143 = 50;
    public static final int IBM01144 = 51;
    public static final int IBM01145 = 52;
    public static final int IBM01146 = 53;
    public static final int IBM01147 = 54;
    public static final int IBM01148 = 55;
    public static final int IBM01149 = 56;
    public static final int WINDOWS_1250 = 57;
    public static final int WINDOWS_1251 = 58;
    public static final int WINDOWS_1252 = 59;
    public static final int WINDOWS_1253 = 60;
    public static final int WINDOWS_1254 = 61;
    public static final int WINDOWS_1255 = 62;
    public static final int WINDOWS_1256 = 63;
    public static final int WINDOWS_1257 = 64;
    public static final int WINDOWS_1258 = 65;
    public static final int TIS_620 = 66;
    public static final int IBM01047 = 67;
    private static final String[] fgCharsetNames;
    private static final int[] fgHashCodes;
    
    public static EncodingSupport getEncodingSupport(final String s) {
        switch (searchForCharset(s)) {
            default: {
                return JavaEncodingSupport.getInstance(s);
            }
            case 0: {
                return UTF8EncodingSupport.getInstance();
            }
            case 1: {
                return ASCIIEncodingSupport.getInstance();
            }
            case 2: {
                return Latin1EncodingSupport.getInstance();
            }
            case 3: {
                return JavaEncodingSupport.getInstance("ISO8859_2");
            }
            case 4: {
                return JavaEncodingSupport.getInstance("ISO8859_3");
            }
            case 5: {
                return JavaEncodingSupport.getInstance("ISO8859_4");
            }
            case 6: {
                return JavaEncodingSupport.getInstance("ISO8859_5");
            }
            case 7: {
                return JavaEncodingSupport.getInstance("ISO8859_6");
            }
            case 8: {
                return JavaEncodingSupport.getInstance("ISO8859_7");
            }
            case 9: {
                return JavaEncodingSupport.getInstance("ISO8859_8");
            }
            case 10: {
                return JavaEncodingSupport.getInstance("ISO8859_9");
            }
            case 11: {
                return UCSEncodingSupport.getInstance(4);
            }
            case 12: {
                return UCSEncodingSupport.getInstance(0);
            }
            case 13: {
                return UCSEncodingSupport.getInstance(1);
            }
            case 14: {
                return UCSEncodingSupport.getInstance(2);
            }
            case 15: {
                return UCSEncodingSupport.getInstance(3);
            }
            case 16: {
                return JavaEncodingSupport.getInstance("ISO2022JP");
            }
            case 17: {
                return JavaEncodingSupport.getInstance("SJIS");
            }
            case 18: {
                return JavaEncodingSupport.getInstance("EUC_JP");
            }
            case 19: {
                return JavaEncodingSupport.getInstance("GB2312");
            }
            case 20: {
                return JavaEncodingSupport.getInstance("Big5");
            }
            case 21: {
                return JavaEncodingSupport.getInstance("EUC_KR");
            }
            case 22: {
                return JavaEncodingSupport.getInstance("ISO2022KR");
            }
            case 23: {
                return JavaEncodingSupport.getInstance("KOI8_R");
            }
            case 24: {
                return JavaEncodingSupport.getInstance("ISO2022CN");
            }
            case 25: {
                return JavaEncodingSupport.getInstance("MS932");
            }
            case 26: {
                return JavaEncodingSupport.getInstance("JIS0208");
            }
            case 27: {
                return JavaEncodingSupport.getInstance("JIS0212");
            }
            case 28: {
                return JavaEncodingSupport.getInstance("JIS0201");
            }
            case 29: {
                return UCSEncodingSupport.getInstance(5);
            }
            case 30: {
                return UCSEncodingSupport.getInstance(6);
            }
            case 31: {
                return EBCDICEncodingSupport.getInstance(0);
            }
            case 32: {
                return EBCDICEncodingSupport.getInstance(1);
            }
            case 33: {
                return EBCDICEncodingSupport.getInstance(2);
            }
            case 34: {
                return EBCDICEncodingSupport.getInstance(3);
            }
            case 35: {
                return EBCDICEncodingSupport.getInstance(4);
            }
            case 36: {
                return EBCDICEncodingSupport.getInstance(5);
            }
            case 37: {
                return EBCDICEncodingSupport.getInstance(6);
            }
            case 38: {
                return EBCDICEncodingSupport.getInstance(7);
            }
            case 39: {
                return JavaEncodingSupport.getInstance("Cp420");
            }
            case 40: {
                return JavaEncodingSupport.getInstance("Cp424");
            }
            case 41: {
                return EBCDICEncodingSupport.getInstance(8);
            }
            case 42: {
                return JavaEncodingSupport.getInstance("Cp868");
            }
            case 43: {
                return JavaEncodingSupport.getInstance("Cp869");
            }
            case 44: {
                return JavaEncodingSupport.getInstance("Cp870");
            }
            case 45: {
                return EBCDICEncodingSupport.getInstance(9);
            }
            case 46: {
                return JavaEncodingSupport.getInstance("Cp918");
            }
            case 47: {
                return EBCDICEncodingSupport.getInstance(11);
            }
            case 48: {
                return EBCDICEncodingSupport.getInstance(12);
            }
            case 49: {
                return EBCDICEncodingSupport.getInstance(13);
            }
            case 50: {
                return EBCDICEncodingSupport.getInstance(14);
            }
            case 51: {
                return EBCDICEncodingSupport.getInstance(15);
            }
            case 52: {
                return EBCDICEncodingSupport.getInstance(16);
            }
            case 53: {
                return EBCDICEncodingSupport.getInstance(17);
            }
            case 54: {
                return EBCDICEncodingSupport.getInstance(18);
            }
            case 55: {
                return EBCDICEncodingSupport.getInstance(19);
            }
            case 56: {
                return EBCDICEncodingSupport.getInstance(20);
            }
            case 57: {
                return Windows125xEncodingSupport.getInstance(0);
            }
            case 58: {
                return Windows125xEncodingSupport.getInstance(1);
            }
            case 59: {
                return Windows125xEncodingSupport.getInstance(2);
            }
            case 60: {
                return Windows125xEncodingSupport.getInstance(3);
            }
            case 61: {
                return Windows125xEncodingSupport.getInstance(4);
            }
            case 62: {
                return Windows125xEncodingSupport.getInstance(5);
            }
            case 63: {
                return Windows125xEncodingSupport.getInstance(6);
            }
            case 64: {
                return Windows125xEncodingSupport.getInstance(7);
            }
            case 65: {
                return Windows125xEncodingSupport.getInstance(8);
            }
            case 66: {
                return JavaEncodingSupport.getInstance("TIS620");
            }
            case 67: {
                return EBCDICEncodingSupport.getInstance(10);
            }
        }
    }
    
    public static int hashString(final String s) {
        final int length = s.length();
        int n = 0;
        for (int i = 0; i < length; ++i) {
            n += n * 37 + (n >> 24) + s.charAt(i);
        }
        return n & Integer.MAX_VALUE;
    }
    
    private static int searchForCharset(final String s) {
        int i = 0;
        int n = IANACharset.fgHashCodes.length / 3 - 1;
        final int hashString = hashString(s);
        while (i <= n) {
            final int n2 = (i + n) / 2;
            final int n3 = IANACharset.fgHashCodes[n2 * 3];
            if (n3 < hashString) {
                i = n2 + 1;
            }
            else if (n3 > hashString) {
                n = n2 - 1;
            }
            else {
                if (s.equals(IANACharset.fgCharsetNames[IANACharset.fgHashCodes[n2 * 3 + 1]])) {
                    return IANACharset.fgHashCodes[n2 * 3 + 2];
                }
                break;
            }
        }
        return -1;
    }
    
    static {
        fgCharsetNames = new String[] { "UTF-8", "US-ASCII", "ANSI_X3.4-1968", "ISO-IR-6", "ANSI_X3.4-1986", "ASCII", "ISO646-US", "US", "IBM367", "CP367", "CSASCII", "ISO-8859-1", "ISO-IR-100", "ISO_8859-1", "LATIN1", "L1", "IBM819", "CP819", "CSISOLATIN1", "ISO-8859-2", "ISO-IR-101", "ISO_8859-2", "LATIN2", "L2", "CSISOLATIN2", "ISO-8859-3", "ISO-IR-109", "ISO_8859-3", "LATIN3", "L3", "CSISOLATIN3", "ISO-8859-4", "ISO-IR-110", "ISO_8859-4", "LATIN4", "L4", "CSISOLATIN4", "ISO-8859-5", "ISO-IR-144", "ISO_8859-5", "CYRILLIC", "CSISOLATINCYRILLIC", "ISO-8859-6", "ISO-IR-127", "ISO_8859-6", "ECMA-114", "ASMO-708", "ARABIC", "CSISOLATINARABIC", "ISO-8859-7", "ISO-IR-126", "ISO_8859-7", "ELOT_928", "ECMA-118", "GREEK", "GREEK8", "CSISOLATINGREEK", "ISO-8859-8", "ISO-IR-138", "ISO_8859-8", "HEBREW", "CSISOLATINHEBREW", "ISO-8859-9", "ISO-IR-148", "ISO_8859-9", "LATIN5", "L5", "CSISOLATIN5", "UTF-16", "UTF-16BE", "UTF-16LE", "UTF-32BE", "UTF-32LE", "ISO-2022-JP", "CSISO2022JP", "SHIFT_JIS", "MS_KANJI", "CSSHIFTJIS", "EUC-JP", "EXTENDED_UNIX_CODE_PACKED_FORMAT_FOR_JAPANESE", "CSEUCPKDFMTJAPANESE", "GB2312", "CSGB2312", "BIG5", "CSBIG5", "EUC-KR", "CSEUCKR", "ISO-2022-KR", "CSISO2022KR", "KOI8-R", "CSKOI8R", "ISO-2022-CN", "WINDOWS-31J", "CSWINDOWS31J", "JIS_C6226-1983", "ISO-IR-87", "X0208", "JIS_X0208-1983", "CSISO87JISX0208", "JIS_X0212-1990", "X0212", "ISO-IR-159", "CSISO159JISX02121990", "JIS_X0201", "X0201", "CSHALFWIDTHKATAKANA", "ISO-10646-UCS-2", "CSUNICODE", "ISO-10646-UCS-4", "CSUCS4", "IBM037", "CP037", "EBCDIC-CP-US", "EBCDIC-CP-CA", "EBCDIC-CP-WT", "EBCDIC-CP-NL", "CSIBM037", "IBM273", "CP273", "CSIBM273", "IBM277", "EBCDIC-CP-DK", "EBCDIC-CP-NO", "CSIBM277", "IBM278", "CP278", "EBCDIC-CP-FI", "EBCDIC-CP-SE", "CSIBM278", "IBM280", "CP280", "EBCDIC-CP-IT", "CSIBM280", "IBM284", "CP284", "EBCDIC-CP-ES", "CSIBM284", "IBM285", "CP285", "EBCDIC-CP-GB", "CSIBM285", "IBM297", "CP297", "EBCDIC-CP-FR", "CSIBM297", "IBM420", "CP420", "EBCDIC-CP-AR1", "CSIBM420", "IBM424", "CP424", "EBCDIC-CP-HE", "CSIBM424", "IBM500", "CP500", "EBCDIC-CP-BE", "EBCDIC-CP-CH", "CSIBM500", "IBM868", "CP868", "CP-AR", "CSIBM868", "IBM869", "CP869", "CP-GR", "CSIBM869", "IBM870", "CP870", "EBCDIC-CP-ROECE", "EBCDIC-CP-YU", "CSIBM870", "IBM871", "CP871", "EBCDIC-CP-IS", "CSIBM871", "IBM918", "CP918", "EBCDIC-CP-AR2", "CSIBM918", "IBM01140", "CCSID01140", "CP01140", "IBM01141", "CCSID01141", "CP01141", "IBM01142", "CCSID01142", "CP01142", "IBM01143", "CCSID01143", "CP01143", "IBM01144", "CCSID01144", "CP01144", "IBM01145", "CCSID01145", "CP01145", "IBM01146", "CCSID01146", "CP01146", "IBM01147", "CCSID01147", "CP01147", "IBM01148", "CCSID01148", "CP01148", "IBM01149", "CCSID01149", "CP01149", "WINDOWS-1250", "WINDOWS-1251", "WINDOWS-1252", "WINDOWS-1253", "WINDOWS-1254", "WINDOWS-1255", "WINDOWS-1256", "WINDOWS-1257", "WINDOWS-1258", "TIS-620", "IBM01047", "CCSID01047", "CP01047", "CP1047" };
        fgHashCodes = new int[] { 2937, 15, 2, 2938, 23, 3, 2939, 29, 4, 2940, 35, 5, 2941, 66, 10, 3313, 7, 1, 3729715, 83, 20, 121372723, 106, 29, 121372725, 108, 30, 140187811, 5, 1, 143531001, 10, 1, 144161404, 160, 42, 144161632, 164, 43, 144165177, 111, 31, 144168213, 118, 32, 144168218, 125, 34, 144168248, 130, 35, 144168252, 134, 36, 144168253, 138, 37, 144168293, 142, 38, 144169623, 9, 1, 144170908, 146, 39, 144170912, 150, 40, 144172276, 154, 41, 144176655, 17, 2, 144176844, 159, 42, 144176845, 163, 43, 144176874, 167, 44, 144176875, 172, 45, 144178098, 176, 46, 151981374, 86, 21, 152646493, 54, 8, 161111872, 105, 28, 164170900, 90, 23, 181948654, 0, 0, 186199897, 104, 28, 186199904, 96, 26, 186199936, 100, 27, 191330783, 45, 7, 191330787, 53, 8, 203209166, 2, 1, 203209240, 4, 1, 234769869, 69, 12, 234770249, 70, 13, 234873837, 71, 14, 234874217, 72, 15, 251897025, 168, 44, 278283363, 11, 2, 278283364, 19, 3, 278283365, 25, 4, 278283366, 31, 5, 278283367, 37, 6, 278283368, 42, 7, 278283369, 49, 8, 278283370, 57, 9, 278283371, 62, 10, 283397209, 94, 26, 307899836, 93, 25, 349486598, 56, 8, 391443059, 41, 6, 394224275, 102, 27, 447571143, 61, 9, 458563975, 91, 24, 458564243, 73, 16, 458564283, 87, 22, 471597706, 68, 11, 501248855, 13, 2, 501248856, 21, 3, 501248857, 27, 4, 501248858, 33, 5, 501248859, 39, 6, 501248860, 44, 7, 501248861, 51, 8, 501248862, 59, 9, 501248863, 64, 10, 515597166, 209, 57, 515597167, 210, 58, 515597168, 211, 59, 515597169, 212, 60, 515597170, 213, 61, 515597171, 214, 62, 515597172, 215, 63, 515597173, 216, 64, 515597174, 217, 65, 615921725, 77, 17, 722338379, 95, 26, 805534737, 107, 29, 859086828, 97, 26, 902559341, 46, 7, 919043323, 76, 17, 1029964609, 47, 7, 1180401486, 218, 66, 1183359919, 222, 67, 1190584971, 84, 20, 1191619330, 109, 30, 1224120905, 40, 6, 1247128595, 99, 27, 1256851221, 92, 25, 1281504927, 18, 2, 1281504928, 24, 3, 1281504929, 30, 4, 1281504930, 36, 5, 1281504931, 67, 10, 1315594157, 103, 28, 1331527911, 74, 16, 1331527951, 88, 22, 1353240160, 78, 18, 1353240200, 85, 21, 1390131750, 6, 1, 1471167773, 81, 19, 1488339816, 3, 1, 1505599503, 55, 8, 1548301286, 147, 39, 1548301287, 177, 46, 1557581862, 60, 9, 1595760524, 98, 26, 1598208865, 82, 19, 1607394163, 80, 18, 1631115402, 110, 31, 1631118438, 117, 32, 1631118442, 120, 33, 1631118443, 124, 34, 1631118473, 129, 35, 1631118477, 133, 36, 1631118478, 137, 37, 1631118518, 141, 38, 1631119848, 8, 1, 1631121133, 145, 39, 1631121137, 149, 40, 1631122501, 153, 41, 1631126880, 16, 2, 1631127069, 158, 42, 1631127070, 162, 43, 1631127099, 166, 44, 1631127100, 171, 45, 1631128323, 175, 46, 1662367273, 75, 17, 1679044343, 12, 2, 1679044344, 20, 3, 1679044352, 26, 4, 1679044381, 32, 5, 1679044425, 50, 8, 1679044426, 43, 7, 1679044465, 58, 9, 1679044499, 38, 6, 1679044503, 63, 10, 1679044542, 101, 27, 1688448255, 219, 67, 1688449692, 179, 47, 1688449693, 182, 48, 1688449694, 185, 49, 1688449695, 188, 50, 1688449696, 191, 51, 1688449697, 194, 52, 1688449698, 197, 53, 1688449699, 200, 54, 1688449700, 203, 55, 1688449701, 206, 56, 1720797658, 52, 8, 1728992593, 1, 1, 1758156494, 116, 31, 1758159530, 119, 32, 1758159534, 123, 33, 1758159535, 128, 34, 1758159565, 132, 35, 1758159569, 136, 36, 1758159570, 140, 37, 1758159610, 144, 38, 1758162225, 148, 39, 1758162229, 152, 40, 1758163593, 157, 41, 1758168161, 161, 42, 1758168162, 165, 43, 1758168191, 170, 44, 1758168192, 174, 45, 1758169415, 178, 46, 1816484369, 89, 23, 1850605607, 79, 18, 1867156994, 14, 2, 1867156995, 22, 3, 1867156996, 28, 4, 1867156997, 34, 5, 1867156998, 65, 10, 1946603574, 220, 67, 1946605011, 180, 47, 1946605012, 183, 48, 1946605013, 186, 49, 1946605014, 189, 50, 1946605015, 192, 51, 1946605016, 195, 52, 1946605017, 198, 53, 1946605018, 201, 54, 1946605019, 204, 55, 1946605020, 207, 56, 2015967933, 221, 67, 2015969370, 181, 47, 2015969371, 184, 48, 2015969372, 187, 49, 2015969373, 190, 50, 2015969374, 193, 51, 2015969375, 196, 52, 2015969376, 199, 53, 2015969377, 202, 54, 2015969378, 205, 55, 2015969379, 208, 56, 2067437539, 48, 7, 2075202984, 155, 41, 2075203018, 113, 31, 2075203025, 156, 41, 2075203066, 121, 33, 2075203112, 135, 36, 2075203140, 126, 34, 2075203149, 143, 38, 2075203171, 139, 37, 2075203212, 151, 40, 2075203264, 173, 45, 2075203265, 131, 35, 2075203447, 115, 31, 2075203450, 122, 33, 2075203630, 127, 34, 2075203720, 112, 31, 2075203797, 114, 31, 2075203874, 169, 44 };
    }
}

// 
// Decompiled by Procyon v0.5.30
// 

class exifInfo
{
    final boolean DEBUG = false;
    final int M_SOF0 = 192;
    final int M_SOF1 = 193;
    final int M_SOF2 = 194;
    final int M_SOF3 = 195;
    final int M_SOF5 = 197;
    final int M_SOF6 = 198;
    final int M_SOF7 = 199;
    final int M_SOF8 = 200;
    final int M_SOF9 = 201;
    final int M_SOF10 = 202;
    final int M_SOF11 = 203;
    final int M_SOF12 = 204;
    final int M_SOF13 = 205;
    final int M_SOF14 = 206;
    final int M_SOF15 = 207;
    final int M_SOI = 216;
    final int M_EOI = 217;
    final int M_SOS = 218;
    final int M_JFIF = 224;
    final int M_EXIF = 225;
    final int M_COM = 254;
    final int M_APP14 = 237;
    final int EXIF_OFFSET_TAG = 34665;
    private static final int TAG_EXIF_OFFSET = 34665;
    private static final int TAG_INTEROP_OFFSET = 40965;
    private static final int TAG_MAKE = 271;
    private static final int TAG_MODEL = 272;
    private static final int TAG_DESCRIPTION = 270;
    private static final int TAG_EXPOSURETIME = 33434;
    private static final int TAG_FNUMBER = 33437;
    private static final int TAG_SHUTTERSPEED = 37377;
    private static final int TAG_APERTURE = 37378;
    private static final int TAG_MAXAPERTURE = 37381;
    private static final int TAG_FOCALLENGTH = 37386;
    private static final int TAG_DATETIME = 306;
    private static final int TAG_DATETIME_ORIGINAL = 36867;
    private static final int TAG_USERCOMMENT = 37510;
    private static final int TAG_SUBJECT_DISTANCE = 37382;
    private static final int TAG_FLASH = 37385;
    private static final int TAG_FOCALPLANEXRES = 41486;
    private static final int TAG_FOCALPLANEUNITS = 41488;
    private static final int TAG_EXIF_IMAGEWIDTH = 40962;
    private static final int TAG_EXIF_IMAGELENGTH = 40963;
    private static final int TAG_EXPOSURE_BIAS = 37380;
    private static final int TAG_WHITEBALANCE = 37384;
    private static final int TAG_METERING_MODE = 37383;
    private static final int TAG_EXPOSURE_PROGRAM = 34850;
    private static final int TAG_ISO_EQUIVALENT = 34855;
    private static final int TAG_COMPRESSION_LEVEL = 37122;
    private static final int TAG_THUMBNAIL_OFFSET = 513;
    private static final int TAG_THUMBNAIL_LENGTH = 514;
    private static final int TAG_ORIENTATION = 274;
    private static final int TAG_X_RESOLUTION = 282;
    private static final int TAG_Y_RESOLUTION = 283;
    private static final int TAG_RESOLUTION_UNIT = 296;
    private static final int TAG_YCBCR_POSITIONING = 531;
    private static final int TAG_LIGHT_SOURCE = 37384;
    private static final int IPTC_BYLINE = 80;
    private static final int IPTC_BYLINETITLE = 85;
    private static final int IPTC_CREDITS = 110;
    private static final int IPTC_SOURCE = 115;
    private static final int IPTC_OBJECTNAME = 5;
    private static final int IPTC_DATECREATED = 55;
    private static final int IPTC_CITY = 90;
    private static final int IPTC_STATE = 95;
    private static final int IPTC_COUNTRY = 101;
    private static final int IPTC_ORIGTRANSREF = 103;
    private static final int IPTC_COPYRIGHT = 116;
    private static final int IPTC_CAPTION = 120;
    private static final int IPTC_CAPTIONWRITER = 122;
    private static final int IPTC_HEADLINE = 105;
    private static final int IPTC_SPECIALINSTRUCTIONS = 40;
    private static final int IPTC_CATEGORIES = 15;
    private static final int IPTC_SUPPLEMENTALCATEGORIES = 20;
    private static final int IPTC_KEYWORDS = 25;
    private static final boolean PRINT_TAGS = false;
    static int[] bytesPerFormat;
    private static final int NUM_FORMATS = 12;
    private static final int FMT_BYTE = 1;
    private static final int FMT_STRING = 2;
    private static final int FMT_USHORT = 3;
    private static final int FMT_ULONG = 4;
    private static final int FMT_URATIONAL = 5;
    private static final int FMT_SBYTE = 6;
    private static final int FMT_UNDEFINED = 7;
    private static final int FMT_SSHORT = 8;
    private static final int FMT_SLONG = 9;
    private static final int FMT_SRATIONAL = 10;
    private static final int FMT_SINGLE = 11;
    private static final int FMT_DOUBLE = 12;
    int lastExifRefd;
    int MAX_SECTIONS;
    byte[] data;
    int dataLength;
    boolean is_motorola;
    int tiffHeadStart;
    int startExif;
    String exifString;
    int exifOrientation;
    
    private String formatNumber(final int n, final int n2, final int n3) {
        final StringBuffer sb = new StringBuffer();
        switch (n3) {
            case 1:
            case 6: {
                sb.append(this.data[n2]);
                break;
            }
            case 3: {
                sb.append(this.get16Bits(n2));
                break;
            }
            case 4:
            case 9: {
                sb.append(this.get32Bits(n2));
                break;
            }
            case 8: {
                sb.append((short)this.get16Bits(n2));
                break;
            }
            case 5:
            case 10: {
                final int get32Bits = this.get32Bits(n2);
                final int get32Bits2 = this.get32Bits(n2 + 4);
                if (n != 33434 || get32Bits >= get32Bits2) {
                    sb.append(get32Bits / get32Bits2);
                    break;
                }
                if (get32Bits2 / get32Bits * get32Bits == get32Bits2) {
                    sb.append("1/" + String.valueOf(get32Bits2 / get32Bits));
                    break;
                }
                sb.append(String.valueOf(get32Bits) + "/" + String.valueOf(get32Bits2));
                break;
            }
            case 11: {
                sb.append((double)this.data[n2]);
                break;
            }
            case 12: {
                sb.append((double)this.data[n2]);
                break;
            }
            case 2: {
                sb.append(this.getStringExif(n2));
                break;
            }
            default: {
                return null;
            }
        }
        return sb.toString();
    }
    
    private int get16Bits(final int n) {
        return this.getShortExif(n);
    }
    
    private String getTagDetails(final int n, String trim) {
        trim = trim.trim();
        String s = "";
        Label_1586: {
            if (trim.length() > 0) {
                switch (n) {
                    case 271: {
                        s = "Make: " + trim;
                        break;
                    }
                    case 272: {
                        s = "Model: " + trim;
                        break;
                    }
                    case 270: {
                        s = "Description: " + trim;
                        break;
                    }
                    case 37510: {
                        s = "Comment: " + trim;
                        break;
                    }
                    case 33434: {
                        s = "Exposure: " + trim + " sec";
                        break;
                    }
                    case 33437: {
                        s = "Aperture: F" + trim;
                        break;
                    }
                    case 37386: {
                        s = "Focal Length: " + trim + " mm";
                        break;
                    }
                    case 36867: {
                        s = "Date/Time: " + trim;
                        break;
                    }
                    case 34855: {
                        s = "ISO: " + trim;
                        break;
                    }
                    case 274: {
                        s = "Orientation: " + trim;
                        if (trim.trim().length() > 0) {
                            this.exifOrientation = (short)(Object)Integer.valueOf(trim);
                            break;
                        }
                        break;
                    }
                    case 37382: {
                        s = "Subject Distance: " + trim + " m";
                        break;
                    }
                    case 37380: {
                        s = "Exposure Bias: " + trim;
                        break;
                    }
                    case 37383: {
                        final String s2 = "Metering Mode: ";
                        switch (Integer.valueOf(trim)) {
                            case 0: {
                                s = s2 + "unknown";
                                break Label_1586;
                            }
                            case 1: {
                                s = s2 + "average";
                                break Label_1586;
                            }
                            case 2: {
                                s = s2 + "center weighted average";
                                break Label_1586;
                            }
                            case 3: {
                                s = s2 + "spot";
                                break Label_1586;
                            }
                            case 4: {
                                s = s2 + "multi-spot";
                                break Label_1586;
                            }
                            case 5: {
                                s = s2 + "multi-segment";
                                break Label_1586;
                            }
                            case 6: {
                                s = s2 + "partial";
                                break Label_1586;
                            }
                            default: {
                                s = s2 + "other (" + trim + ")";
                                break Label_1586;
                            }
                        }
                        break;
                    }
                    case 34850: {
                        final String s3 = "Exposure Program: ";
                        switch (Integer.valueOf(trim)) {
                            case 1: {
                                s = s3 + "manual control";
                                break Label_1586;
                            }
                            case 2: {
                                s = s3 + "program normal";
                                break Label_1586;
                            }
                            case 3: {
                                s = s3 + "aperture priority";
                                break Label_1586;
                            }
                            case 4: {
                                s = s3 + "shutter priority";
                                break Label_1586;
                            }
                            case 5: {
                                s = s3 + "program creative (slow program)";
                                break Label_1586;
                            }
                            case 6: {
                                s = s3 + "program action (high-speed program)";
                                break Label_1586;
                            }
                            case 7: {
                                s = s3 + "portrait mode";
                                break Label_1586;
                            }
                            case 8: {
                                s = s3 + "landscape mode";
                                break Label_1586;
                            }
                            default: {
                                s = s3 + "unknown (" + trim + ")";
                                break Label_1586;
                            }
                        }
                        break;
                    }
                    case 37385: {
                        final String s4 = "Flash: ";
                        switch (Integer.valueOf(trim)) {
                            case 0: {
                                s = s4 + "no flash";
                                break Label_1586;
                            }
                            case 1: {
                                s = s4 + "flash fired";
                                break Label_1586;
                            }
                            case 5: {
                                s = s4 + "flash fired but strobe return light not detected";
                                break Label_1586;
                            }
                            case 7: {
                                s = s4 + "flash fired and strobe return light detected";
                                break Label_1586;
                            }
                            default: {
                                s = s4 + "unknown (" + trim + ")";
                                break Label_1586;
                            }
                        }
                        break;
                    }
                    case 37384: {
                        final String s5 = "Light Source: ";
                        switch (Integer.valueOf(trim)) {
                            case 1: {
                                s = s5 + "daylight";
                                break Label_1586;
                            }
                            case 2: {
                                s = s5 + "flourescent";
                                break Label_1586;
                            }
                            case 3: {
                                s = s5 + "tungsten";
                                break Label_1586;
                            }
                            case 10: {
                                s = s5 + "flash";
                                break Label_1586;
                            }
                            case 17: {
                                s = s5 + "standard light A";
                                break Label_1586;
                            }
                            case 18: {
                                s = s5 + "standard light B";
                                break Label_1586;
                            }
                            case 19: {
                                s = s5 + "standard light C";
                                break Label_1586;
                            }
                            case 20: {
                                s = s5 + "D55";
                                break Label_1586;
                            }
                            case 21: {
                                s = s5 + "D65";
                                break Label_1586;
                            }
                            case 22: {
                                s = s5 + "D75";
                                break Label_1586;
                            }
                            case 255: {
                                s = s5 + "other";
                                break Label_1586;
                            }
                            default: {
                                s = s5 + "unknown (" + trim + ")";
                                break Label_1586;
                            }
                        }
                        break;
                    }
                }
            }
        }
        return s;
    }
    
    public exifInfo(final byte[] data, final int dataLength) {
        this.lastExifRefd = -1;
        this.MAX_SECTIONS = 20;
        this.is_motorola = false;
        this.tiffHeadStart = 6;
        this.startExif = 0;
        this.exifString = "";
        this.exifOrientation = 1;
        this.data = data;
        this.dataLength = dataLength;
    }
    
    private int findStartOfHeader() {
        final int n = -1;
        int i = 2;
        final String s = "8BIM\u0004\u0004";
        if (this.getData(0) != 255 || this.getData(1) != 216) {
            return -1;
        }
        for (int j = 0; j < this.MAX_SECTIONS - 1; ++j) {
            final int n2 = i;
            if (this.getData(i++) != 255) {
                return -1;
            }
            final int data = this.getData(i++);
            if (data == 255) {
                return -1;
            }
            final int n3 = (this.exif(i) << 8) + this.exif(i + 1);
            if (n3 < 2) {
                return -1;
            }
            switch (data) {
                case 237: {
                    int n4 = -1;
                    final int length = s.length();
                    for (int n5 = i + n3 - length; i < n5; ++i) {
                        for (int n6 = 0; n6 < length && this.getData(i + n6) == s.charAt(n6); ++n6) {
                            if (n6 == length - 1) {
                                n4 = i + 2;
                                i = n5;
                            }
                        }
                    }
                    if (n4 != -1) {
                        final int data2 = this.getData(n4 + 4);
                        int n7;
                        if (data2 != 0) {
                            n7 = n4 + 4 + 2 + 1 + data2 + 2;
                        }
                        else {
                            n7 = n4 + 10;
                        }
                        this.parseIPTC(n7);
                        this.addPreString("IPTC details");
                        this.addString("");
                        break;
                    }
                    break;
                }
                case 225: {
                    this.startExif = n2 + 4;
                    if (this.exif(0) == 69 && this.exif(1) == 120 && this.exif(2) == 105 && this.exif(3) == 102 && this.exif(4) == 0 && this.exif(5) == 0) {
                        return n2 + 4;
                    }
                    if (this.exif(0) == 255 && this.exif(1) == 225) {
                        return n2 + 8;
                    }
                    break;
                }
            }
            i = n2 + 2 + n3;
        }
        return n;
    }
    
    private void processExifDir(final int n, final int n2) throws ExifProcessingException {
        final int get16Bits = this.get16Bits(n);
        final int dirEntryAddress = this.getDirEntryAddress(n, get16Bits);
        if (dirEntryAddress + 4 > n2 + this.data.length && dirEntryAddress + 2 != n2 + this.data.length && dirEntryAddress != n2 + this.data.length) {
            throw new ExifProcessingException("Illegally sized directory");
        }
        if (dirEntryAddress < this.lastExifRefd) {
            this.lastExifRefd = dirEntryAddress;
        }
        for (int i = 0; i < get16Bits; ++i) {
            final int dirEntryAddress2 = this.getDirEntryAddress(n, i);
            final int get16Bits2 = this.get16Bits(dirEntryAddress2);
            final int get16Bits3 = this.get16Bits(dirEntryAddress2 + 2);
            final int get32Bits = this.get32Bits(dirEntryAddress2 + 4);
            if (get16Bits3 - 1 >= 12) {
                throw new ExifProcessingException("Illegal format code in EXIF dir");
            }
            final int n3 = get32Bits * exifInfo.bytesPerFormat[get16Bits3];
            int n4;
            if (n3 > 4) {
                final int get32Bits2 = this.get32Bits(dirEntryAddress2 + 8);
                if (get32Bits2 + n3 > this.data.length) {
                    throw new ExifProcessingException("Illegal pointer offset value in EXIF");
                }
                n4 = n2 + get32Bits2;
            }
            else {
                n4 = dirEntryAddress2 + 8;
            }
            if (this.lastExifRefd < n4 + n3) {
                this.lastExifRefd = n4 + n3;
            }
            if (get16Bits2 == 34665 || get16Bits2 == 40965) {
                final int n5 = n2 + this.get32Bits(n4);
                if (n5 < n2 || n5 > n2 + this.data.length) {
                    throw new ExifProcessingException("Illegal subdirectory link 1");
                }
                this.processExifDir(n5, n2);
            }
            else {
                final String s = "";
                switch (get16Bits3) {
                    case 2:
                    case 7: {
                        final String tagDetails = this.getTagDetails(get16Bits2, this.getStringExif(n4));
                        if (tagDetails.length() > 0) {
                            this.addString(s + tagDetails);
                            break;
                        }
                        break;
                    }
                    default: {
                        final String tagDetails2 = this.getTagDetails(get16Bits2, this.formatNumber(get16Bits2, n4, get16Bits3));
                        if (tagDetails2.length() > 0) {
                            this.addString(s + tagDetails2);
                            break;
                        }
                        break;
                    }
                }
            }
        }
        if (this.getDirEntryAddress(n, get16Bits) + 4 <= n2 + this.data.length) {
            final int get32Bits3 = this.get32Bits(n + 2 + 12 * get16Bits);
            if (get32Bits3 != 0) {
                final int n6 = n2 + get32Bits3;
                if (n6 > n2 + this.data.length) {
                    if (n6 < n2 + this.data.length + 20) {
                        throw new ExifProcessingException("Illegal subdirectory link 2");
                    }
                    if (n6 <= n2 + this.data.length) {
                        this.processExifDir(n6, n2);
                    }
                }
            }
        }
    }
    
    private int getData(final int n) {
        return this.data[n] & 0xFF;
    }
    
    private int exif(final int n) {
        return this.getData(this.startExif + n);
    }
    
    static {
        exifInfo.bytesPerFormat = new int[] { 0, 1, 1, 2, 4, 8, 1, 1, 2, 4, 8, 4, 8 };
    }
    
    private int getShortExif(final long n) {
        final int n2 = (int)n;
        int n3;
        if (!this.is_motorola) {
            n3 = (this.exif(n2 + 1) << 8) + this.exif(n2);
        }
        else {
            n3 = (this.exif(n2) << 8) + this.exif(n2 + 1);
        }
        return n3;
    }
    
    private String getStringExif(final long n) {
        int n2;
        int n3;
        for (n2 = 200, n3 = 0; this.data[(int)(this.startExif + n + n3)] != 0 && n3 < n2; ++n3) {}
        return new String(this.data, (int)(this.startExif + n), n3);
    }
    
    private int getDirEntryAddress(final int n, final int n2) {
        return n + 2 + 12 * n2;
    }
    
    public String pullExifInfo() {
        this.exifOrientation = 1;
        this.exifString = "";
        try {
            this.startExif = this.findStartOfHeader();
            final int n = (this.exif(-2) << 8) + this.exif(-1);
            if (this.startExif >= 0) {
                this.is_motorola = (this.exif(this.tiffHeadStart) == 77 && this.exif(this.tiffHeadStart + 1) == 77);
                this.addString("EXIF Picture Details");
                this.processExifDir(14, 6);
                this.addString("");
            }
        }
        catch (Exception ex) {}
        return this.exifString;
    }
    
    private double convertNumber(final int n, final int n2) {
        double n3 = 0.0;
        switch (n2) {
            case 6: {
                n3 = this.data[n];
                break;
            }
            case 1: {
                n3 = (char)this.data[n];
                break;
            }
            case 3: {
                n3 = this.get16Bits(n);
                break;
            }
            case 4: {
                n3 = this.get32Bits(n);
                break;
            }
            case 5:
            case 10: {
                final int get32Bits = this.get32Bits(n);
                final int get32Bits2 = this.get32Bits(n + 4);
                if (get32Bits2 == 0) {
                    n3 = 0.0;
                    break;
                }
                n3 = get32Bits / get32Bits2;
                break;
            }
            case 8: {
                n3 = this.get16Bits(n);
                break;
            }
            case 9: {
                n3 = this.get32Bits(n);
                break;
            }
            case 11: {
                n3 = this.data[n];
                break;
            }
            case 12: {
                n3 = this.data[n];
                break;
            }
        }
        return n3;
    }
    
    private int get32Bits(final int n) {
        return (int)this.getLongExif(n);
    }
    
    private void parseIPTC(final int n) {
        final String[] array = { "Byline", "Byline Title", "Credits", "Source", "Object Name", "Date Created", "City", "State", "Country", "Original Transmission Reference", "Copyright", "Caption", "Caption Writer", "Headline", "Special Instructions", "Category", "Supplemental Categories", "Keywords" };
        final int[] array2 = { 80, 85, 110, 115, 5, 55, 90, 95, 101, 103, 116, 120, 122, 105, 40, 15, 20, 25 };
        int short1;
        for (int i = n; i < this.getShort(n); i += short1 + 5) {
            if (this.getShort(i) != 7170) {
                return;
            }
            final int data = this.getData(i + 2);
            short1 = this.getShort(i + 3);
            final String s = new String(this.data, i + 5, short1);
            for (int j = 0; j < array2.length; ++j) {
                if (array2[j] == data) {
                    this.addPreString(array[j] + ": " + s);
                    break;
                }
            }
        }
    }
    
    private long getLongExif(final long n) {
        final int n2 = (int)n;
        long n3;
        if (!this.is_motorola) {
            n3 = (this.exif(n2 + 3) << 24) + (this.exif(n2 + 2) << 16) + (this.exif(n2 + 1) << 8) + this.exif(n2);
        }
        else {
            n3 = (this.exif(n2) << 24) + (this.exif(n2 + 1) << 16) + (this.exif(n2 + 2) << 8) + this.exif(n2 + 3);
        }
        return n3;
    }
    
    private void addString(final String s) {
        this.exifString = this.exifString + s + "\n";
    }
    
    private void addPreString(final String s) {
        this.exifString = s + "\n" + this.exifString;
    }
    
    private int getShort(final long n) {
        return (this.getData((int)n) << 8) + this.getData((int)n + 1);
    }
    
    public int getExifOrientation() {
        return this.exifOrientation;
    }
}

import java.awt.Graphics;
import java.awt.image.ImageObserver;
import java.awt.Component;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.util.Vector;
import java.awt.Image;
import java.io.ByteArrayInputStream;
import java.awt.image.IndexColorModel;
import java.awt.image.ImageProducer;
import java.awt.image.BufferedImage;
import java.awt.image.PixelGrabber;
import java.awt.image.ColorModel;
import java.awt.image.MemoryImageSource;
import java.awt.Color;
import java.io.UnsupportedEncodingException;
import java.util.Hashtable;
import java.io.IOException;
import java.io.InputStream;
import java.awt.Canvas;
import java.io.DataInputStream;

// 
// Decompiled by Procyon v0.5.30
// 

class ObjReader
{
    DataInputStream s;
    Object[][] objTable;
    static final int ObjectRefID = 99;
    static final Object[] empty;
    static final Canvas canvas;
    static final byte[] macRomanToISOLatin;
    static final byte[] squeakColors;
    
    ObjReader(final InputStream inputStream) {
        this.s = new DataInputStream(inputStream);
    }
    
    Object[][] readObjects(final LContext lContext) throws IOException {
        this.readInfo();
        this.readObjTable(lContext);
        return this.objTable;
    }
    
    Hashtable readInfo() throws IOException {
        final byte[] array = new byte[10];
        this.s.read(array);
        if (!"ScratchV01".equals(new String(array)) && !"ScratchV02".equals(new String(array))) {
            throw new IOException();
        }
        this.s.readInt();
        this.readObjTable(null);
        final Object[] array2 = (Object[])this.objTable[0][0];
        final Hashtable hashtable = new Hashtable<Object, Object>(array2.length);
        for (int i = 0; i < array2.length - 1; i += 2) {
            hashtable.put(array2[i], array2[i + 1]);
        }
        return hashtable;
    }
    
    void readObjTable(final LContext lContext) throws IOException {
        final byte[] array = new byte[4];
        this.s.read(array);
        if (!"ObjS".equals(new String(array)) || this.s.readByte() != 1) {
            throw new IOException();
        }
        this.s.read(array);
        if (!"Stch".equals(new String(array)) || this.s.readByte() != 1) {
            throw new IOException();
        }
        final int int1 = this.s.readInt();
        this.objTable = new Object[int1][];
        for (int i = 0; i < int1; ++i) {
            this.objTable[i] = this.readObj();
        }
        this.createSpritesAndWatchers(lContext);
        this.buildImagesAndSounds();
        this.fixSounds();
        this.resolveReferences();
        this.uncompressMedia();
    }
    
    Object[] readObj() throws IOException {
        final int unsignedByte = this.s.readUnsignedByte();
        Object[] array;
        if (unsignedByte < 99) {
            array = new Object[] { this.readFixedFormat(unsignedByte), new Integer(unsignedByte) };
        }
        else {
            final int unsignedByte2 = this.s.readUnsignedByte();
            array = new Object[3 + this.s.readUnsignedByte()];
            array[0] = ObjReader.empty;
            array[1] = new Integer(unsignedByte);
            array[2] = new Integer(unsignedByte2);
            for (int i = 3; i < array.length; ++i) {
                array[i] = this.readField();
            }
        }
        return array;
    }
    
    Object readField() throws IOException {
        final int unsignedByte = this.s.readUnsignedByte();
        if (unsignedByte == 99) {
            return new Ref((this.s.readUnsignedByte() << 16) + (this.s.readUnsignedByte() << 8) + this.s.readUnsignedByte());
        }
        return this.readFixedFormat(unsignedByte);
    }
    
    Object readFixedFormat(final int n) throws IOException {
        switch (n) {
            case 1: {
                return ObjReader.empty;
            }
            case 2: {
                return Boolean.TRUE;
            }
            case 3: {
                return Boolean.FALSE;
            }
            case 4: {
                return new Integer(this.s.readInt());
            }
            case 5: {
                return new Integer(this.s.readShort());
            }
            case 6:
            case 7: {
                double n2 = 0.0;
                double n3 = 1.0;
                for (short short1 = this.s.readShort(), n4 = 0; n4 < short1; ++n4) {
                    n2 += n3 * this.s.readUnsignedByte();
                    n3 *= 256.0;
                }
                if (n == 7) {
                    n2 = -n2;
                }
                return new Double(n2);
            }
            case 8: {
                return new Double(this.s.readDouble());
            }
            case 9:
            case 10: {
                final int int1 = this.s.readInt();
                final byte[] array;
                this.s.read(array = new byte[int1]);
                for (int i = 0; i < int1; ++i) {
                    if (array[i] < 0) {
                        array[i] = ObjReader.macRomanToISOLatin[array[i] + 128];
                    }
                }
                try {
                    return new String(array, "ISO-8859-1");
                }
                catch (UnsupportedEncodingException ex) {
                    return new String(array);
                }
            }
            case 11: {
                final byte[] array2;
                this.s.read(array2 = new byte[this.s.readInt()]);
                return array2;
            }
            case 12: {
                final byte[] array3;
                this.s.read(array3 = new byte[2 * this.s.readInt()]);
                return array3;
            }
            case 13: {
                final int[] array4 = new int[this.s.readInt()];
                for (int j = 0; j < array4.length; ++j) {
                    array4[j] = this.s.readInt();
                }
                return array4;
            }
            case 14: {
                final byte[] array5;
                this.s.read(array5 = new byte[this.s.readInt()]);
                try {
                    return new String(array5, "UTF-8");
                }
                catch (UnsupportedEncodingException ex2) {
                    return new String(array5);
                }
            }
            case 20:
            case 21:
            case 22:
            case 23: {
                final Object[] array6 = new Object[this.s.readInt()];
                for (int k = 0; k < array6.length; ++k) {
                    array6[k] = this.readField();
                }
                return array6;
            }
            case 24:
            case 25: {
                final Object[] array7 = new Object[2 * this.s.readInt()];
                for (int l = 0; l < array7.length; ++l) {
                    array7[l] = this.readField();
                }
                return array7;
            }
            case 30:
            case 31: {
                final int int2 = this.s.readInt();
                int unsignedByte = 255;
                if (n == 31) {
                    unsignedByte = this.s.readUnsignedByte();
                }
                return new Color(int2 >> 22 & 0xFF, int2 >> 12 & 0xFF, int2 >> 2 & 0xFF, unsignedByte);
            }
            case 32: {
                return new Object[] { this.readField(), this.readField() };
            }
            case 33: {
                return new Object[] { this.readField(), this.readField(), this.readField(), this.readField() };
            }
            case 34:
            case 35: {
                final Object[] array8 = new Object[6];
                for (int n5 = 0; n5 < 5; ++n5) {
                    array8[n5] = this.readField();
                }
                if (n == 35) {
                    array8[5] = this.readField();
                }
                return array8;
            }
            default: {
                System.out.println("Unknown fixed-format class " + n);
                throw new IOException();
            }
        }
    }
    
    void createSpritesAndWatchers(final LContext lContext) {
        for (int i = 0; i < this.objTable.length; ++i) {
            final Object[] array = this.objTable[i];
            final int intValue = ((Number)array[1]).intValue();
            if (intValue == 124 || intValue == 125) {
                array[0] = new Sprite(lContext);
            }
            if (intValue == 155) {
                array[0] = new Watcher(lContext);
                if (((Number)array[2]).intValue() > 3) {
                    final Number n = (Number)array[23];
                    final Number n2 = (Number)array[24];
                    if (this.floatWithoutFraction(n) || this.floatWithoutFraction(n2)) {
                        array[24] = new Double(n2.doubleValue() + 1.0E-8);
                    }
                }
            }
        }
    }
    
    boolean floatWithoutFraction(final Object o) {
        if (!(o instanceof Double)) {
            return false;
        }
        final double doubleValue = (double)o;
        return Math.round(doubleValue) == doubleValue;
    }
    
    void resolveReferences() throws IOException {
        for (int i = 0; i < this.objTable.length; ++i) {
            final int intValue = ((Number)this.objTable[i][1]).intValue();
            if (intValue >= 20 && intValue <= 29) {
                final Object[] array = (Object[])this.objTable[i][0];
                for (int j = 0; j < array.length; ++j) {
                    final Object o = array[j];
                    if (o instanceof Ref) {
                        array[j] = this.deRef((Ref)o);
                    }
                }
            }
            if (intValue > 99) {
                for (int k = 3; k < this.objTable[i].length; ++k) {
                    final Object o2 = this.objTable[i][k];
                    if (o2 instanceof Ref) {
                        this.objTable[i][k] = this.deRef((Ref)o2);
                    }
                }
            }
        }
    }
    
    Object deRef(final Ref ref) {
        final Object[] array = this.objTable[ref.index];
        return (array[0] == null || array[0] == ObjReader.empty) ? array : array[0];
    }
    
    void buildImagesAndSounds() throws IOException {
        for (int i = 0; i < this.objTable.length; ++i) {
            final int intValue = ((Number)this.objTable[i][1]).intValue();
            if (intValue == 34 || intValue == 35) {
                final Object[] array = (Object[])this.objTable[i][0];
                final int intValue2 = ((Number)array[0]).intValue();
                final int intValue3 = ((Number)array[1]).intValue();
                final int intValue4 = ((Number)array[2]).intValue();
                final int[] decodePixels = this.decodePixels(this.objTable[((Ref)array[4]).index][0]);
                ImageProducer imageProducer = null;
                this.objTable[i][0] = ObjReader.empty;
                if (intValue4 <= 8) {
                    IndexColorModel indexColorModel;
                    if (array[5] != null) {
                        indexColorModel = this.customColorMap(intValue4, (Object[])this.objTable[((Ref)array[5]).index][0]);
                    }
                    else {
                        indexColorModel = this.squeakColorMap(intValue4);
                    }
                    imageProducer = new MemoryImageSource(intValue2, intValue3, indexColorModel, this.rasterToByteRaster(decodePixels, intValue2, intValue3, intValue4), 0, intValue2);
                }
                if (intValue4 == 16) {
                    imageProducer = new MemoryImageSource(intValue2, intValue3, this.raster16to32(decodePixels, intValue2, intValue3), 0, intValue2);
                }
                if (intValue4 == 32) {
                    imageProducer = new MemoryImageSource(intValue2, intValue3, this.rasterAddAlphaTo32(decodePixels), 0, intValue2);
                }
                if (imageProducer != null) {
                    final int[] array2 = new int[intValue2 * intValue3];
                    final PixelGrabber pixelGrabber = new PixelGrabber(imageProducer, 0, 0, intValue2, intValue3, array2, 0, intValue2);
                    try {
                        pixelGrabber.grabPixels();
                    }
                    catch (InterruptedException ex) {}
                    final BufferedImage bufferedImage = new BufferedImage(intValue2, intValue3, 2);
                    bufferedImage.getRaster().setDataElements(0, 0, intValue2, intValue3, array2);
                    this.objTable[i][0] = bufferedImage;
                }
            }
            if (intValue == 109) {
                this.objTable[i][0] = new ScratchSound(((Number)this.objTable[i][7]).intValue(), (byte[])this.objTable[((Ref)this.objTable[i][6]).index][0]);
            }
        }
    }
    
    int[] decodePixels(final Object o) throws IOException {
        if (o instanceof int[]) {
            return (int[])o;
        }
        final DataInputStream dataInputStream = new DataInputStream(new ByteArrayInputStream((byte[])o));
        final int decodeInt = this.decodeInt(dataInputStream);
        final int[] array = new int[decodeInt];
        int n = 0;
        while (dataInputStream.available() > 0 & n < decodeInt) {
            final int decodeInt2 = this.decodeInt(dataInputStream);
            final int n2 = decodeInt2 >> 2;
            switch (decodeInt2 & 0x3) {
                case 0: {
                    n += n2;
                    continue;
                }
                case 1: {
                    final int unsignedByte = dataInputStream.readUnsignedByte();
                    final int n3 = unsignedByte << 24 | unsignedByte << 16 | unsignedByte << 8 | unsignedByte;
                    for (int i = 0; i < n2; ++i) {
                        array[n++] = n3;
                    }
                    continue;
                }
                case 2: {
                    final int int1 = dataInputStream.readInt();
                    for (int j = 0; j < n2; ++j) {
                        array[n++] = int1;
                    }
                    continue;
                }
                case 3: {
                    for (int k = 0; k < n2; ++k) {
                        array[n++] = (dataInputStream.readUnsignedByte() << 24 | dataInputStream.readUnsignedByte() << 16 | dataInputStream.readUnsignedByte() << 8 | dataInputStream.readUnsignedByte());
                    }
                    continue;
                }
            }
        }
        return array;
    }
    
    int decodeInt(final DataInputStream dataInputStream) throws IOException {
        final int unsignedByte = dataInputStream.readUnsignedByte();
        if (unsignedByte <= 223) {
            return unsignedByte;
        }
        if (unsignedByte <= 254) {
            return (unsignedByte - 224) * 256 + dataInputStream.readUnsignedByte();
        }
        return dataInputStream.readInt();
    }
    
    int[] rasterAddAlphaTo32(final int[] array) {
        for (int i = 0; i < array.length; ++i) {
            final int n = array[i];
            if (n != 0) {
                array[i] = (0xFF000000 | n);
            }
        }
        return array;
    }
    
    int[] raster16to32(final int[] array, final int n, final int n2) {
        final int[] array2 = new int[n * n2];
        final int n3 = (n + 1) / 2;
        for (int i = 0; i < n2; ++i) {
            int n4 = 16;
            for (int j = 0; j < n; ++j) {
                final int n5 = array[i * n3 + j / 2] >> n4 & 0xFFFF;
                final int n6 = (n5 >> 10 & 0x1F) << 3;
                final int n7 = (n5 >> 5 & 0x1F) << 3;
                final int n8 = (n5 & 0x1F) << 3;
                array2[i * n + j] = ((n6 + n7 + n8 == 0) ? 0 : (0xFF000000 | n6 << 16 | n7 << 8 | n8));
                n4 = ((n4 == 16) ? 0 : 16);
            }
        }
        return array2;
    }
    
    byte[] rasterToByteRaster(final int[] array, final int n, final int n2, final int n3) {
        final byte[] array2 = new byte[n * n2];
        final int n4 = array.length / n2;
        final int n5 = (1 << n3) - 1;
        final int n6 = 32 / n3;
        for (int i = 0; i < n2; ++i) {
            for (int j = 0; j < n; ++j) {
                array2[i * n + j] = (byte)(array[i * n4 + j / n6] >> n3 * (n6 - j % n6 - 1) & n5);
            }
        }
        return array2;
    }
    
    IndexColorModel squeakColorMap(final int n) {
        return new IndexColorModel(n, 256, ObjReader.squeakColors, 0, false, (n == 1) ? -1 : 0);
    }
    
    IndexColorModel customColorMap(final int n, final Object[] array) {
        final byte[] array2 = new byte[4 * array.length];
        int n2 = 0;
        for (int i = 0; i < array.length; ++i) {
            final Color color = (Color)this.objTable[((Ref)array[i]).index][0];
            array2[n2++] = (byte)color.getRed();
            array2[n2++] = (byte)color.getGreen();
            array2[n2++] = (byte)color.getBlue();
            array2[n2++] = (byte)color.getAlpha();
        }
        return new IndexColorModel(n, array.length, array2, 0, true, 0);
    }
    
    void fixSounds() {
        boolean b = false;
        for (int i = 0; i < this.objTable.length; ++i) {
            if (((Number)this.objTable[i][1]).intValue() == 109 && ((ScratchSound)this.objTable[i][0]).isByteReversed()) {
                b = true;
            }
        }
        if (!b) {
            return;
        }
        for (int j = 0; j < this.objTable.length; ++j) {
            if (((Number)this.objTable[j][1]).intValue() == 109) {
                ((ScratchSound)this.objTable[j][0]).reverseBytes();
            }
        }
    }
    
    void uncompressMedia() {
        for (int i = 0; i < this.objTable.length; ++i) {
            final Object[] array = this.objTable[i];
            final int intValue = ((Number)array[1]).intValue();
            int intValue2 = -1;
            if (intValue >= 100) {
                intValue2 = ((Number)array[2]).intValue();
            }
            if (intValue == 162 && intValue2 >= 4) {
                if (array[7] instanceof byte[]) {
                    final BufferedImage jpegDecode = this.jpegDecode((byte[])array[7]);
                    if (jpegDecode != null) {
                        if (array[4] instanceof Image) {
                            ((Image)array[4]).flush();
                        }
                        array[4] = jpegDecode;
                        array[7] = ObjReader.empty;
                    }
                }
                if (array[8] instanceof BufferedImage) {
                    array[4] = array[8];
                    array[8] = ObjReader.empty;
                }
            }
            if (intValue == 164 && intValue2 >= 2 && array[9] instanceof byte[]) {
                final int intValue3 = ((Number)array[7]).intValue();
                final int intValue4 = ((Number)array[8]).intValue();
                final byte[] array2 = (byte[])array[9];
                array[4] = new ScratchSound(intValue3, new ADPCMDecoder(array2, intValue4).decode((array2.length * 8 + (intValue4 - 1)) / intValue4));
                final Object[] array3 = array;
                final int n = 7;
                final Object[] array4 = array;
                final int n2 = 8;
                final Object[] array5 = array;
                final int n3 = 9;
                final Object[] empty = ObjReader.empty;
                array5[n3] = empty;
                array3[n] = (array4[n2] = empty);
            }
        }
    }
    
    void canonicalizeMedia() {
        final Vector vector = new Vector(100);
        final Vector vector2 = new Vector(100);
        for (int i = 0; i < this.objTable.length; ++i) {
            final Object[] array = this.objTable[i];
            final int intValue = ((Number)array[1]).intValue();
            if (intValue == 162) {
                final BufferedImage bufferedImage = (BufferedImage)array[4];
            }
            if (intValue == 164) {
                final ScratchSound scratchSound = (ScratchSound)array[4];
            }
        }
    }
    
    BufferedImage jpegDecode(final byte[] array) {
        final Image image = Toolkit.getDefaultToolkit().createImage(array);
        final MediaTracker mediaTracker = new MediaTracker(ObjReader.canvas);
        mediaTracker.addImage(image, 0);
        try {
            mediaTracker.waitForID(0);
        }
        catch (InterruptedException ex) {}
        if (image == null) {
            return null;
        }
        final int width = image.getWidth(null);
        final int height = image.getHeight(null);
        final BufferedImage bufferedImage = new BufferedImage(width, height, 2);
        final Graphics graphics = bufferedImage.getGraphics();
        graphics.drawImage(image, 0, 0, width, height, null);
        graphics.dispose();
        image.flush();
        return bufferedImage;
    }
    
    void printit(final Object o) {
        if (o instanceof Object[] && ((Object[])o).length == 0) {
            System.out.print(" []");
            return;
        }
        if (o instanceof BufferedImage) {
            final BufferedImage bufferedImage = (BufferedImage)o;
            System.out.print(" BufferedImage_" + o.hashCode() + "(" + bufferedImage.getWidth(null) + "x" + bufferedImage.getHeight(null) + ")");
            return;
        }
        System.out.print(" " + o);
    }
    
    static {
        empty = new Object[0];
        canvas = new Canvas();
        macRomanToISOLatin = new byte[] { -60, -59, -57, -55, -47, -42, -36, -31, -32, -30, -28, -29, -27, -25, -23, -24, -22, -21, -19, -20, -18, -17, -15, -13, -14, -12, -10, -11, -6, -7, -5, -4, -122, -80, -94, -93, -89, -107, -74, -33, -82, -87, -103, -76, -88, -128, -58, -40, -127, -79, -118, -115, -91, -75, -114, -113, -112, -102, -99, -86, -70, -98, -26, -8, -65, -95, -84, -90, -125, -83, -78, -85, -69, -123, -96, -64, -61, -43, -116, -100, -106, -105, -109, -108, -111, -110, -9, -77, -1, -97, -71, -92, -117, -101, -68, -67, -121, -73, -126, -124, -119, -62, -54, -63, -53, -56, -51, -50, -49, -52, -45, -44, -66, -46, -38, -37, -39, -48, -120, -104, -81, -41, -35, -34, -72, -16, -3, -2 };
        squeakColors = new byte[] { -1, -1, -1, 0, 0, 0, -1, -1, -1, -128, -128, -128, -1, 0, 0, 0, -1, 0, 0, 0, -1, 0, -1, -1, -1, -1, 0, -1, 0, -1, 32, 32, 32, 64, 64, 64, 96, 96, 96, -97, -97, -97, -65, -65, -65, -33, -33, -33, 8, 8, 8, 16, 16, 16, 24, 24, 24, 40, 40, 40, 48, 48, 48, 56, 56, 56, 72, 72, 72, 80, 80, 80, 88, 88, 88, 104, 104, 104, 112, 112, 112, 120, 120, 120, -121, -121, -121, -113, -113, -113, -105, -105, -105, -89, -89, -89, -81, -81, -81, -73, -73, -73, -57, -57, -57, -49, -49, -49, -41, -41, -41, -25, -25, -25, -17, -17, -17, -9, -9, -9, 0, 0, 0, 0, 51, 0, 0, 102, 0, 0, -103, 0, 0, -52, 0, 0, -1, 0, 0, 0, 51, 0, 51, 51, 0, 102, 51, 0, -103, 51, 0, -52, 51, 0, -1, 51, 0, 0, 102, 0, 51, 102, 0, 102, 102, 0, -103, 102, 0, -52, 102, 0, -1, 102, 0, 0, -103, 0, 51, -103, 0, 102, -103, 0, -103, -103, 0, -52, -103, 0, -1, -103, 0, 0, -52, 0, 51, -52, 0, 102, -52, 0, -103, -52, 0, -52, -52, 0, -1, -52, 0, 0, -1, 0, 51, -1, 0, 102, -1, 0, -103, -1, 0, -52, -1, 0, -1, -1, 51, 0, 0, 51, 51, 0, 51, 102, 0, 51, -103, 0, 51, -52, 0, 51, -1, 0, 51, 0, 51, 51, 51, 51, 51, 102, 51, 51, -103, 51, 51, -52, 51, 51, -1, 51, 51, 0, 102, 51, 51, 102, 51, 102, 102, 51, -103, 102, 51, -52, 102, 51, -1, 102, 51, 0, -103, 51, 51, -103, 51, 102, -103, 51, -103, -103, 51, -52, -103, 51, -1, -103, 51, 0, -52, 51, 51, -52, 51, 102, -52, 51, -103, -52, 51, -52, -52, 51, -1, -52, 51, 0, -1, 51, 51, -1, 51, 102, -1, 51, -103, -1, 51, -52, -1, 51, -1, -1, 102, 0, 0, 102, 51, 0, 102, 102, 0, 102, -103, 0, 102, -52, 0, 102, -1, 0, 102, 0, 51, 102, 51, 51, 102, 102, 51, 102, -103, 51, 102, -52, 51, 102, -1, 51, 102, 0, 102, 102, 51, 102, 102, 102, 102, 102, -103, 102, 102, -52, 102, 102, -1, 102, 102, 0, -103, 102, 51, -103, 102, 102, -103, 102, -103, -103, 102, -52, -103, 102, -1, -103, 102, 0, -52, 102, 51, -52, 102, 102, -52, 102, -103, -52, 102, -52, -52, 102, -1, -52, 102, 0, -1, 102, 51, -1, 102, 102, -1, 102, -103, -1, 102, -52, -1, 102, -1, -1, -103, 0, 0, -103, 51, 0, -103, 102, 0, -103, -103, 0, -103, -52, 0, -103, -1, 0, -103, 0, 51, -103, 51, 51, -103, 102, 51, -103, -103, 51, -103, -52, 51, -103, -1, 51, -103, 0, 102, -103, 51, 102, -103, 102, 102, -103, -103, 102, -103, -52, 102, -103, -1, 102, -103, 0, -103, -103, 51, -103, -103, 102, -103, -103, -103, -103, -103, -52, -103, -103, -1, -103, -103, 0, -52, -103, 51, -52, -103, 102, -52, -103, -103, -52, -103, -52, -52, -103, -1, -52, -103, 0, -1, -103, 51, -1, -103, 102, -1, -103, -103, -1, -103, -52, -1, -103, -1, -1, -52, 0, 0, -52, 51, 0, -52, 102, 0, -52, -103, 0, -52, -52, 0, -52, -1, 0, -52, 0, 51, -52, 51, 51, -52, 102, 51, -52, -103, 51, -52, -52, 51, -52, -1, 51, -52, 0, 102, -52, 51, 102, -52, 102, 102, -52, -103, 102, -52, -52, 102, -52, -1, 102, -52, 0, -103, -52, 51, -103, -52, 102, -103, -52, -103, -103, -52, -52, -103, -52, -1, -103, -52, 0, -52, -52, 51, -52, -52, 102, -52, -52, -103, -52, -52, -52, -52, -52, -1, -52, -52, 0, -1, -52, 51, -1, -52, 102, -1, -52, -103, -1, -52, -52, -1, -52, -1, -1, -1, 0, 0, -1, 51, 0, -1, 102, 0, -1, -103, 0, -1, -52, 0, -1, -1, 0, -1, 0, 51, -1, 51, 51, -1, 102, 51, -1, -103, 51, -1, -52, 51, -1, -1, 51, -1, 0, 102, -1, 51, 102, -1, 102, 102, -1, -103, 102, -1, -52, 102, -1, -1, 102, -1, 0, -103, -1, 51, -103, -1, 102, -103, -1, -103, -103, -1, -52, -103, -1, -1, -103, -1, 0, -52, -1, 51, -52, -1, 102, -52, -1, -103, -52, -1, -52, -52, -1, -1, -52, -1, 0, -1, -1, 51, -1, -1, 102, -1, -1, -103, -1, -1, -52, -1, -1, -1, -1 };
    }
}

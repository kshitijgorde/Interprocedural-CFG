import java.io.EOFException;
import java.util.Date;
import java.io.ByteArrayInputStream;
import java.awt.Dimension;
import java.awt.Image;
import java.io.IOException;
import java.net.URL;
import java.io.InputStream;

// 
// Decompiled by Procyon v0.5.30
// 

class F360Decoder extends ImageDecoder
{
    protected InputStream stream;
    protected int skipSize;
    protected int imageLength;
    protected int headerSize;
    protected float iFOVRad;
    
    private static String FOUR_CC_STRING(final int fourcc) {
        String str = new String();
        str = String.valueOf(str) + (char)((fourcc & 0xFF000000) >> 24);
        str = String.valueOf(str) + (char)((fourcc & 0xFF0000) >> 16);
        str = String.valueOf(str) + (char)((fourcc & 0xFF00) >> 8);
        str = String.valueOf(str) + (char)(fourcc & 0xFF);
        return str;
    }
    
    F360Decoder(final URL url, float iniFOVDeg) {
        super(url);
        this.stream = null;
        if (iniFOVDeg > 0.0f) {
            iniFOVDeg = Util.limit(iniFOVDeg, 0.0f, 114.59f);
        }
        this.iFOVRad = iniFOVDeg * 3.1415927f / 180.0f;
    }
    
    void setStream(final InputStream stream) {
        this.stream = new ProgressFilterInputStream(this.listener, stream);
    }
    
    protected void doDecode() throws IOException, RuntimeException, InterruptedException, Exception {
        final int Kh = 958122090;
        final int Kl = 73959415;
        long key = Kh ^ Kl;
        key <<= 32;
        final long Ko = ~Kl;
        key ^= Ko;
        if (!this.loadHeader(this.frame)) {
            this.listener.decodeUpdate(-1, 0.0f);
            return;
        }
        ((ProgressFilterInputStream)this.stream).setStreamEnd(this.headerSize + this.skipSize + this.imageLength);
        final byte[] buf = new byte[this.skipSize];
        this.readFully(this.stream, buf);
        super.setStream(new EncryptedInputStream(this.stream, key));
        this.decode(this.frame, this.imageLength);
    }
    
    protected void setImage(final Source frame, final Image image) throws InterruptedException {
        frame.setImage(image);
        final Dimension size = frame.getSize();
        final float[] center = new float[2];
        center[1] = (center[0] = size.width / 2.0f - 0.5f);
        frame.setCenter(center);
    }
    
    protected boolean loadHeader(final Source frame) throws IOException {
        final int BU_VERSION = 6;
        final short BU_TYPE = 25203;
        final byte java = -112;
        final byte javal = -125;
        final byte javah = -117;
        final float DEG2RAD = 0.017453292f;
        final short buType = EndianL2B.readShort(this.stream);
        if (buType != 25203) {
            return false;
        }
        this.headerSize = EndianL2B.readShort(this.stream);
        this.headerSize &= 0xFFFF;
        final int buChecksum = EndianL2B.readInt(this.stream);
        final byte[] bih = new byte[this.headerSize - 8];
        this.readFully(this.stream, bih);
        if (crc(bih) != buChecksum) {
            return false;
        }
        try {
            final InputStream stream = new ByteArrayInputStream(bih);
            final byte buVersion = EndianL2B.readByte(stream);
            if (buVersion != 6) {
                return false;
            }
            stream.skip(1L);
            frame.setProperty("vfov", new Float(EndianL2B.readShort(stream) * 0.017453292f));
            stream.skip(2L);
            final byte buCompression16 = EndianL2B.readByte(stream);
            if (buCompression16 != -112 && (buCompression16 > -117 || buCompression16 < -125)) {
                return false;
            }
            stream.skip(1L);
            final Float radius = new Float(EndianL2B.readInt(stream));
            frame.setProperty("irad", radius);
            this.skipSize = EndianL2B.readInt(stream) * 4;
            this.skipSize += EndianL2B.readInt(stream);
            this.skipSize += EndianL2B.readInt(stream);
            this.imageLength = EndianL2B.readInt(stream);
            if (this.imageLength == 0) {
                return false;
            }
            stream.skip(4L);
            final boolean buSphere = EndianL2B.readByte(stream) != 0;
            final boolean buMirror = EndianL2B.readByte(stream) != 0;
            frame.setProperty("mirr", buMirror ? Boolean.TRUE : Boolean.FALSE);
            if (buSphere) {
                frame.setProperty("frmt", "SPHERE");
            }
            else if (buMirror) {
                frame.setProperty("frmt", "MIRRORED");
            }
            else {
                frame.setProperty("frmt", "HEMISPHERE");
                frame.setProperty("hfov", new Float(3.1415927f));
            }
            stream.skip(334L);
            this.skipSize += EndianL2B.readInt(stream);
            this.skipSize += EndianL2B.readInt(stream);
            this.skipSize += EndianL2B.readInt(stream);
            final float[] ref = { EndianL2B.readFloat(stream) * 0.017453292f, EndianL2B.readFloat(stream) * -0.017453292f, EndianL2B.readFloat(stream) * -0.017453292f, 0.0f };
            frame.setProperty("rfvp", ref);
            final float[] def = { EndianL2B.readFloat(stream) * 0.017453292f, EndianL2B.readFloat(stream) * -0.017453292f, 0.0f, 0.0f };
            stream.skip(4L);
            def[3] = EndianL2B.readFloat(stream);
            if (this.iFOVRad > 0.0f) {
                def[3] = 1.0f / (1.28f * (float)Math.tan(this.iFOVRad / 2.0f));
            }
            frame.setProperty("invp", def);
            this.skipSize += EndianL2B.readInt(stream);
            this.skipSize += EndianL2B.readInt(stream);
            stream.skip(1L);
            long days = EndianL2B.read24bit(stream);
            if (days != 0L) {
                final Date RefDate = new Date(97, 0, 1);
                final Date expire = new Date(RefDate.getTime() + days * 24L * 60L * 60L * 1000L);
                if (expire.before(new Date())) {
                    return false;
                }
            }
            frame.setProperty("zmax", new Float(EndianL2B.readFloat(stream)));
            frame.setProperty("zmin", new Float(EndianL2B.readFloat(stream)));
            frame.setProperty("glng", new Float(EndianL2B.readFloat(stream)));
            frame.setProperty("glat", new Float(EndianL2B.readFloat(stream)));
            stream.skip(12L);
            days = EndianL2B.read24bit(stream);
            if (days != 0L) {
                final Date RefDate = new Date(97, 0, 1);
                final Date start = new Date(RefDate.getTime() + days * 24L * 60L * 60L * 1000L);
                if (start.after(new Date())) {
                    return false;
                }
            }
            stream.skip(1L);
            final int tagDataSize = EndianL2B.readInt(stream);
            if (tagDataSize > 0) {
                final TagData tag = new TagData();
                tag.read(stream);
                for (int i = 0; i < tag.getNumChildren(); ++i) {
                    final TagData childTag = tag.getChild(i);
                    if (childTag.getID() == Hotspot.IP_HOTSPOTMEDIA) {
                        final int numHotspots = childTag.getNumChildren();
                        if (numHotspots > 0) {
                            final Hotspot[] hs = new Hotspot[numHotspots];
                            for (int j = 0; j < numHotspots; ++j) {
                                (hs[j] = new Hotspot()).readFromTag(childTag.getChild(j));
                                hs[j].setRadius(radius);
                            }
                            frame.setProperty("spts", hs);
                        }
                    }
                    else {
                        switch (childTag.getType()) {
                            case 1:
                            case 2: {
                                frame.setProperty(FOUR_CC_STRING(childTag.getID()), new Integer(childTag.getByte()));
                                break;
                            }
                            case 3:
                            case 4: {
                                frame.setProperty(FOUR_CC_STRING(childTag.getID()), new Integer(childTag.getShort()));
                                break;
                            }
                            case 5:
                            case 6: {
                                frame.setProperty(FOUR_CC_STRING(childTag.getID()), new Integer(childTag.getInt()));
                                break;
                            }
                            case 7:
                            case 8: {
                                frame.setProperty(FOUR_CC_STRING(childTag.getID()), new Long(childTag.getLong()));
                                break;
                            }
                            case 9: {
                                frame.setProperty(FOUR_CC_STRING(childTag.getID()), new Float(childTag.getFloat()));
                                break;
                            }
                            case 10: {
                                frame.setProperty(FOUR_CC_STRING(childTag.getID()), new Double(childTag.getDouble()));
                                break;
                            }
                            case 13: {
                                frame.setProperty(FOUR_CC_STRING(childTag.getID()), childTag.getString());
                                break;
                            }
                        }
                    }
                }
            }
        }
        catch (EOFException ex) {}
        return true;
    }
    
    static final int crc(final byte[] cmd) {
        int reg = 0;
        for (int j = 0; j < cmd.length; ++j) {
            byte tmpc = cmd[j];
            for (int i = 0; i < 9; ++i) {
                final boolean x = ((reg & 0x1) ^ (tmpc & 0x1)) != 0x0;
                reg >>>= 1;
                if (x) {
                    reg ^= 0xA001;
                }
                tmpc = (byte)(tmpc >> 1 & 0x7F);
            }
        }
        return reg;
    }
    
    private final void readFully(final InputStream in, final byte[] b) throws IOException {
        int count;
        for (int n = 0; n < b.length; n += count) {
            count = in.read(b, n, b.length - n);
            if (count < 0) {
                throw new EOFException();
            }
        }
    }
}

import java.io.IOException;
import java.io.InputStream;
import java.util.Vector;

// 
// Decompiled by Procyon v0.5.30
// 

class TagData
{
    public static final byte K_TAG_INVALID = 0;
    public static final byte K_TAG_INT8 = 1;
    public static final byte K_TAG_UINT8 = 2;
    public static final byte K_TAG_INT16 = 3;
    public static final byte K_TAG_UINT16 = 4;
    public static final byte K_TAG_INT32 = 5;
    public static final byte K_TAG_UINT32 = 6;
    public static final byte K_TAG_INT64 = 7;
    public static final byte K_TAG_UINT64 = 8;
    public static final byte K_TAG_FLOAT32 = 9;
    public static final byte K_TAG_FLOAT64 = 10;
    public static final byte K_TAG_STRING = 13;
    public static final byte K_TAG_BINARY = 14;
    public static final byte K_TAG_LIST = -1;
    private static final byte[] K_TAG_DATA_SIZES;
    private byte mType;
    private int mID;
    private int mSize;
    private byte[] mData;
    public Vector mTags;
    
    static {
        K_TAG_DATA_SIZES = new byte[] { 1, 1, 1, 2, 2, 4, 4, 8, 8, 4, 8, 4, 8 };
    }
    
    TagData() {
        this.mTags = new Vector(0, 1);
        this.mID = 0;
        this.mType = 0;
    }
    
    public int read(final InputStream stream) {
        int bytesRead = this.readTagHeader(stream);
        if (bytesRead == 0) {
            return 0;
        }
        if (this.mType != -1) {
            if (this.mData == null || (this.mSize > 0 && this.mSize != this.mData.length)) {
                this.mData = new byte[this.mSize];
            }
            try {
                bytesRead += stream.read(this.mData, 0, this.mSize);
            }
            catch (IOException ex) {}
        }
        else {
            int dataBytesRead = 0;
            while (dataBytesRead < this.mSize) {
                final TagData tag = new TagData();
                dataBytesRead += tag.read(stream);
                this.mTags.addElement(tag);
            }
            bytesRead += dataBytesRead;
        }
        return bytesRead;
    }
    
    private int readTagHeader(final InputStream stream) {
        int bytesRead = 0;
        try {
            final byte[] p = new byte[4];
            stream.read(p);
            this.mID = ((p[0] << 24 & 0xFF000000) | (p[1] << 16 & 0xFF0000) | (p[2] << 8 & 0xFF00) | (p[3] & 0xFF));
            bytesRead += 4;
            this.mType = EndianL2B.readByte(stream);
            ++bytesRead;
            if (this.mType > 0 && this.mType <= 10) {
                this.mSize = TagData.K_TAG_DATA_SIZES[this.mType];
            }
            else {
                this.mSize = EndianL2B.readInt(stream);
                bytesRead += 4;
            }
        }
        catch (IOException ex) {}
        return bytesRead;
    }
    
    public int getID() {
        return this.mID;
    }
    
    public byte getType() {
        return this.mType;
    }
    
    public int getNumChildren() {
        if (this.mType != -1) {
            return 0;
        }
        return this.mTags.size();
    }
    
    public TagData getChild(final int index) {
        if (this.mTags.size() == 0) {
            return null;
        }
        if (this.mType != -1) {
            return null;
        }
        if (index >= this.mTags.size()) {
            return null;
        }
        return this.mTags.elementAt(index);
    }
    
    public TagData getChildByID(final int fourcc) {
        if (this.mType != -1) {
            return null;
        }
        for (int i = 0; i < this.mTags.size(); ++i) {
            if (i >= this.mTags.size()) {
                return null;
            }
            final TagData tagData = this.mTags.elementAt(i);
            if (tagData.getID() == fourcc) {
                return tagData;
            }
        }
        return null;
    }
    
    public int getDataSize() {
        if (this.mType == -1) {
            return 0;
        }
        return this.mData.length;
    }
    
    public int getByte() {
        if (this.mData == null || (this.mType != 1 && this.mType != 2)) {
            return 0;
        }
        return EndianL2B.ToByte(this.mData);
    }
    
    public int getShort() {
        if (this.mData == null || (this.mType != 3 && this.mType != 4)) {
            return 0;
        }
        return EndianL2B.ToShort(this.mData);
    }
    
    public int getInt() {
        if (this.mData == null || (this.mType != 5 && this.mType != 6)) {
            return 0;
        }
        return EndianL2B.ToInt(this.mData);
    }
    
    public long getLong() {
        if (this.mData == null || (this.mType != 7 && this.mType != 8)) {
            return 0L;
        }
        return EndianL2B.ToLong(this.mData);
    }
    
    public float getFloat() {
        if (this.mData == null || this.mType != 9) {
            return 0.0f;
        }
        return EndianL2B.ToFloat(this.mData);
    }
    
    public double getDouble() {
        if (this.mData == null || this.mType != 10) {
            return 0.0;
        }
        return EndianL2B.ToDouble(this.mData);
    }
    
    public String getString() {
        if (this.mType != 13) {
            return "";
        }
        return new String(this.mData);
    }
    
    public byte[] getBinary() {
        return this.mData;
    }
}

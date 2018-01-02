// 
// Decompiled by Procyon v0.5.30
// 

package sexy.gif;

final class LZWDecompStringTable
{
    private static final int NO_PREFIX = -1;
    private static final int DEFAULT_TABLE_SIZE = 4096;
    protected byte[][] strings_;
    protected int size_;
    protected int codeSize_;
    protected int tableSize_;
    
    public LZWDecompStringTable(final int n) {
        this(n, 4096);
    }
    
    public LZWDecompStringTable(final int codeSize_, final int tableSize_) {
        this.size_ = 0;
        this.tableSize_ = tableSize_;
        this.codeSize_ = codeSize_;
        this.initTable();
    }
    
    public int expandCode(final byte[] array, final int n, final int n2, final int n3) {
        final int n4 = this.strings_[n2].length - n3;
        final int n5 = array.length - n;
        final int n6 = (n5 > n4) ? n4 : n5;
        System.arraycopy(this.strings_[n2], n3, array, n, n6);
        if (n4 > n6) {
            return -(n6 + n3);
        }
        return n6;
    }
    
    public final boolean contains(final int n) {
        return n < this.size_;
    }
    
    public int addCharString(final int n, final byte b) {
        if (n == -1) {
            this.strings_[this.size_] = new byte[] { b };
        }
        else {
            final int n2 = this.strings_[n].length + 1;
            final byte[] array = new byte[n2];
            System.arraycopy(this.strings_[n], 0, array, 0, n2 - 1);
            array[n2 - 1] = b;
            this.strings_[this.size_] = array;
        }
        return this.size_++;
    }
    
    protected void initTable() {
        this.strings_ = new byte[this.tableSize_][];
        for (int n = (1 << this.codeSize_) + 2, i = 0; i < n; ++i) {
            this.addCharString(-1, (byte)i);
        }
    }
    
    public void clearTable() {
        this.size_ = (1 << this.codeSize_) + 2;
    }
}

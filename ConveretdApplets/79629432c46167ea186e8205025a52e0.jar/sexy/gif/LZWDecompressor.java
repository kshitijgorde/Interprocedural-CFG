// 
// Decompiled by Procyon v0.5.30
// 

package sexy.gif;

import java.io.InputStream;
import java.io.IOException;

public final class LZWDecompressor
{
    private int CLEAR_CODE;
    private int END_OF_INPUT;
    protected BitInput input_;
    protected int initialCodeSize_;
    protected int codeSize_;
    protected int limit_;
    protected boolean tiff_;
    protected LZWDecompStringTable table_;
    protected int count_;
    protected int oldCode_;
    protected byte oldCodeFirstChar_;
    protected int leftOverCode_;
    protected int leftOverIndex_;
    protected int leftOverOldCode_;
    protected boolean isLeftOver_;
    
    public void gifFinishBlocks() throws IOException {
        if (!this.tiff_) {
            this.input_.gifFinishBlocks();
        }
    }
    
    protected void incrementCodeSize() {
        if (this.codeSize_ != 12) {
            ++this.codeSize_;
            this.limit_ = (1 << this.codeSize_) - 1;
            if (this.tiff_) {
                --this.limit_;
            }
            this.input_.setNumBits(this.codeSize_);
        }
    }
    
    public void setInputStream(final InputStream inputStream) {
        this.input_ = new BitInput(inputStream, true);
        this.clearTable();
        this.resetCodeSize();
    }
    
    public LZWDecompressor(final InputStream inputStream, final int initialCodeSize_, final boolean tiff_) {
        this.initialCodeSize_ = initialCodeSize_;
        this.tiff_ = tiff_;
        this.CLEAR_CODE = 1 << initialCodeSize_;
        this.END_OF_INPUT = (1 << initialCodeSize_) + 1;
        this.table_ = new LZWDecompStringTable(initialCodeSize_);
        this.setInputStream(inputStream);
        this.clearTable();
    }
    
    protected int writeCode(final byte[] array, final int n, final int n2) {
        return this.writeCode(array, n, n2, 0);
    }
    
    protected int writeCode(final byte[] array, final int n, final int leftOverCode_, final int n2) {
        final int expandCode = this.table_.expandCode(array, n, leftOverCode_, n2);
        if (expandCode < 0) {
            this.leftOverCode_ = leftOverCode_;
            this.leftOverOldCode_ = this.oldCode_;
            this.isLeftOver_ = true;
            this.leftOverIndex_ = -expandCode;
        }
        else {
            this.isLeftOver_ = false;
        }
        return expandCode;
    }
    
    public int decompress(final byte[] array) throws IOException {
        final int n = 0;
        final int writeLeftOver = this.writeLeftOver(array);
        if (this.isLeftOver_) {
            return array.length;
        }
        int i = n + writeLeftOver;
        while (i < array.length) {
            final int nextCode = this.getNextCode();
            if (nextCode == this.END_OF_INPUT) {
                break;
            }
            if (nextCode == this.CLEAR_CODE) {
                this.clearTable();
                final int nextCode2 = this.getNextCode();
                if (nextCode2 == this.END_OF_INPUT) {
                    break;
                }
                array[i++] = (byte)nextCode2;
                this.oldCode_ = nextCode2;
                this.oldCodeFirstChar_ = (byte)nextCode2;
            }
            else if (this.table_.contains(nextCode)) {
                final int writeCode = this.writeCode(array, i, nextCode);
                if (this.table_.addCharString(this.oldCode_, array[i]) == this.limit_) {
                    this.incrementCodeSize();
                }
                this.oldCode_ = nextCode;
                this.oldCodeFirstChar_ = array[i];
                if (writeCode < 0) {
                    return array.length;
                }
                i += writeCode;
            }
            else {
                final int addCharString = this.table_.addCharString(this.oldCode_, this.oldCodeFirstChar_);
                final int writeCode2 = this.writeCode(array, i, addCharString);
                this.oldCode_ = nextCode;
                this.oldCodeFirstChar_ = array[i];
                if (addCharString == this.limit_) {
                    this.incrementCodeSize();
                }
                if (writeCode2 < 0) {
                    return array.length;
                }
                i += writeCode2;
            }
        }
        return i;
    }
    
    protected int getNextCode() throws IOException {
        return this.input_.read();
    }
    
    protected int writeLeftOver(final byte[] array) {
        if (!this.isLeftOver_) {
            return 0;
        }
        return this.writeCode(array, 0, this.leftOverCode_, this.leftOverIndex_);
    }
    
    protected void resetCodeSize() {
        this.count_ = 0;
        this.codeSize_ = this.initialCodeSize_;
        this.incrementCodeSize();
    }
    
    protected void clearTable() {
        this.table_.clearTable();
        this.resetCodeSize();
    }
}

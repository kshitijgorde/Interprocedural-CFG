// 
// Decompiled by Procyon v0.5.30
// 

package sexy.gif;

import java.io.IOException;
import java.io.EOFException;
import java.io.InputStream;

public class BitInput
{
    InputStream in_;
    int bits_;
    int bitsCount_;
    int byteCount_;
    int numBits_;
    int numBitsMask_;
    boolean blocks_;
    
    public void gifFinishBlocks() throws IOException {
        if (this.blocks_) {
            while (true) {
                if (this.byteCount_ == 0) {
                    this.byteCount_ = this.in_.read();
                    if (this.byteCount_ == -1) {
                        throw new EOFException();
                    }
                    this.byteCount_ &= 0xFF;
                    if (this.byteCount_ == 0) {
                        break;
                    }
                }
                if (this.byteCount_ != 0) {
                    this.in_.read();
                    --this.byteCount_;
                }
            }
        }
    }
    
    public BitInput(final InputStream in_, final boolean blocks_) {
        this.in_ = in_;
        this.blocks_ = blocks_;
        this.bits_ = 0;
        this.bitsCount_ = 0;
        this.byteCount_ = 0;
    }
    
    public int read() throws IOException {
        while (this.bitsCount_ < this.numBits_) {
            if (this.blocks_) {
                if (this.byteCount_ == 0) {
                    this.byteCount_ = this.in_.read();
                    if (this.byteCount_ == -1) {
                        throw new EOFException();
                    }
                    this.byteCount_ &= 0xFF;
                }
                --this.byteCount_;
            }
            final int read = this.in_.read();
            if (read == -1) {
                throw new EOFException();
            }
            if (this.blocks_) {
                this.bits_ = ((this.bits_ & (1 << this.bitsCount_) - 1) | (read & 0xFF) << this.bitsCount_);
            }
            else {
                this.bits_ = ((this.bits_ << 8 & 0xFFFFFF00) | (read & 0xFF));
            }
            this.bitsCount_ += 8;
        }
        int n;
        if (this.blocks_) {
            n = (this.bits_ & this.numBitsMask_);
            this.bits_ >>>= this.numBits_;
        }
        else {
            n = (this.bits_ >>> this.bitsCount_ - this.numBits_ & this.numBitsMask_);
        }
        this.bitsCount_ -= this.numBits_;
        return n;
    }
    
    public void setNumBits(final int numBits_) {
        this.numBits_ = numBits_;
        this.numBitsMask_ = (1 << this.numBits_) - 1;
    }
}

// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.image;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.ImageLoaderEvent;
import org.eclipse.swt.graphics.ImageLoader;
import org.eclipse.swt.graphics.ImageData;

final class LZWCodec
{
    int bitsPerPixel;
    int blockSize;
    int blockIndex;
    int currentByte;
    int bitsLeft;
    int codeSize;
    int clearCode;
    int endCode;
    int newCodes;
    int topSlot;
    int currentSlot;
    int imageWidth;
    int imageHeight;
    int imageX;
    int imageY;
    int pass;
    int line;
    int codeMask;
    byte[] block;
    byte[] lineArray;
    int[] stack;
    int[] suffix;
    int[] prefix;
    LZWNode[] nodeStack;
    LEDataInputStream inputStream;
    LEDataOutputStream outputStream;
    ImageData image;
    ImageLoader loader;
    boolean interlaced;
    static final int[] MASK_TABLE;
    
    static {
        MASK_TABLE = new int[] { 1, 3, 7, 15, 31, 63, 127, 255, 511, 1023, 2047, 4095 };
    }
    
    void decode() {
        int n = 0;
        int n2 = 0;
        final byte[] array = new byte[this.imageWidth];
        int i = 0;
        int n3 = 0;
        int nextCode;
        while ((nextCode = this.nextCode()) != this.endCode) {
            if (nextCode == this.clearCode) {
                this.codeSize = this.bitsPerPixel + 1;
                this.codeMask = LZWCodec.MASK_TABLE[this.bitsPerPixel];
                this.currentSlot = this.newCodes;
                this.topSlot = 1 << this.codeSize;
                int nextCode2;
                while ((nextCode2 = this.nextCode()) == this.clearCode) {}
                if (nextCode2 == this.endCode) {
                    continue;
                }
                n2 = (n = nextCode2);
                array[n3] = (byte)nextCode2;
                if (++n3 != this.imageWidth) {
                    continue;
                }
                this.nextPutPixels(array);
                n3 = 0;
            }
            else {
                int j = nextCode;
                if (j >= this.currentSlot) {
                    j = n;
                    this.stack[i] = n2;
                    ++i;
                }
                while (j >= this.newCodes) {
                    this.stack[i] = this.suffix[j];
                    ++i;
                    j = this.prefix[j];
                }
                this.stack[i] = j;
                ++i;
                if (this.currentSlot < this.topSlot) {
                    n2 = j;
                    this.suffix[this.currentSlot] = n2;
                    this.prefix[this.currentSlot] = n;
                    ++this.currentSlot;
                    n = nextCode;
                }
                if (this.currentSlot >= this.topSlot && this.codeSize < 12) {
                    this.codeMask = LZWCodec.MASK_TABLE[this.codeSize];
                    ++this.codeSize;
                    this.topSlot += this.topSlot;
                }
                while (i > 0) {
                    --i;
                    array[n3] = (byte)this.stack[i];
                    if (++n3 == this.imageWidth) {
                        this.nextPutPixels(array);
                        n3 = 0;
                    }
                }
            }
        }
        if (n3 != 0 && this.line < this.imageHeight) {
            this.nextPutPixels(array);
        }
    }
    
    public void decode(final LEDataInputStream inputStream, final ImageLoader loader, final ImageData image, final boolean interlaced, final int bitsPerPixel) {
        this.inputStream = inputStream;
        this.loader = loader;
        this.image = image;
        this.interlaced = interlaced;
        this.bitsPerPixel = bitsPerPixel;
        this.initializeForDecoding();
        this.decode();
    }
    
    void encode() {
        this.nextPutCode(this.clearCode);
        this.nextPutCode(this.encodeLoop());
        this.nextPutCode(this.endCode);
        if (this.bitsLeft == 8) {
            this.block[0] = (byte)(this.blockIndex - 1);
        }
        else {
            this.block[0] = (byte)this.blockIndex;
        }
        this.writeBlock();
        if (this.block[0] != 0) {
            this.block[0] = 0;
            this.writeBlock();
        }
    }
    
    public void encode(final LEDataOutputStream outputStream, final ImageData image) {
        this.outputStream = outputStream;
        this.image = image;
        this.initializeForEncoding();
        this.encode();
    }
    
    int encodeLoop() {
        int suffix = this.nextPixel();
        while (true) {
            int code = suffix;
            LZWNode lzwNode = this.nodeStack[code];
            boolean b = true;
            suffix = this.nextPixel();
            if (suffix < 0) {
                return code;
            }
            while (b && lzwNode.children != null) {
                lzwNode = lzwNode.children;
                while (b && lzwNode.suffix != suffix) {
                    if (suffix < lzwNode.suffix) {
                        if (lzwNode.left == null) {
                            lzwNode.left = new LZWNode();
                            b = false;
                        }
                        lzwNode = lzwNode.left;
                    }
                    else {
                        if (lzwNode.right == null) {
                            lzwNode.right = new LZWNode();
                            b = false;
                        }
                        lzwNode = lzwNode.right;
                    }
                }
                if (b) {
                    code = lzwNode.code;
                    suffix = this.nextPixel();
                    if (suffix < 0) {
                        return code;
                    }
                    continue;
                }
            }
            if (b) {
                lzwNode.children = new LZWNode();
                lzwNode = lzwNode.children;
            }
            lzwNode.children = null;
            lzwNode.left = null;
            lzwNode.right = null;
            lzwNode.code = this.currentSlot;
            lzwNode.prefix = code;
            lzwNode.suffix = suffix;
            this.nextPutCode(code);
            ++this.currentSlot;
            if (this.currentSlot < 4096) {
                if (this.currentSlot <= this.topSlot) {
                    continue;
                }
                ++this.codeSize;
                this.codeMask = LZWCodec.MASK_TABLE[this.codeSize - 1];
                this.topSlot *= 2;
            }
            else {
                this.nextPutCode(this.clearCode);
                for (int i = 0; i < this.nodeStack.length; ++i) {
                    this.nodeStack[i].children = null;
                }
                this.codeSize = this.bitsPerPixel + 1;
                this.codeMask = LZWCodec.MASK_TABLE[this.codeSize - 1];
                this.currentSlot = this.newCodes;
                this.topSlot = 1 << this.codeSize;
            }
        }
    }
    
    void initializeForDecoding() {
        this.pass = 1;
        this.line = 0;
        this.codeSize = this.bitsPerPixel + 1;
        this.topSlot = 1 << this.codeSize;
        this.clearCode = 1 << this.bitsPerPixel;
        this.endCode = this.clearCode + 1;
        final int n = this.endCode + 1;
        this.currentSlot = n;
        this.newCodes = n;
        this.currentByte = -1;
        final boolean b = false;
        this.bitsLeft = (b ? 1 : 0);
        this.blockSize = (b ? 1 : 0);
        this.blockIndex = 0;
        this.codeMask = LZWCodec.MASK_TABLE[this.codeSize - 1];
        this.stack = new int[4096];
        this.suffix = new int[4096];
        this.prefix = new int[4096];
        this.block = new byte[256];
        this.imageWidth = this.image.width;
        this.imageHeight = this.image.height;
    }
    
    void initializeForEncoding() {
        this.interlaced = false;
        this.bitsPerPixel = this.image.depth;
        this.codeSize = this.bitsPerPixel + 1;
        this.topSlot = 1 << this.codeSize;
        this.clearCode = 1 << this.bitsPerPixel;
        this.endCode = this.clearCode + 1;
        final int n = this.endCode + 1;
        this.currentSlot = n;
        this.newCodes = n;
        this.bitsLeft = 8;
        this.currentByte = 0;
        this.blockIndex = 1;
        this.blockSize = 255;
        (this.block = new byte[this.blockSize])[0] = (byte)(this.blockSize - 1);
        this.nodeStack = new LZWNode[1 << this.bitsPerPixel];
        for (int i = 0; i < this.nodeStack.length; ++i) {
            final LZWNode lzwNode = new LZWNode();
            lzwNode.code = i + 1;
            lzwNode.prefix = -1;
            lzwNode.suffix = i + 1;
            this.nodeStack[i] = lzwNode;
        }
        this.imageWidth = this.image.width;
        this.imageHeight = this.image.height;
        this.imageY = -1;
        this.lineArray = new byte[this.imageWidth];
        this.imageX = this.imageWidth + 1;
    }
    
    int nextCode() {
        int currentByte;
        if (this.bitsLeft == 0) {
            if (this.blockIndex >= this.blockSize) {
                this.blockSize = this.readBlock();
                this.blockIndex = 0;
                if (this.blockSize == 0) {
                    return this.endCode;
                }
            }
            ++this.blockIndex;
            this.currentByte = (this.block[this.blockIndex] & 0xFF);
            this.bitsLeft = 8;
            currentByte = this.currentByte;
        }
        else {
            final int n = this.bitsLeft - 8;
            if (n < 0) {
                currentByte = this.currentByte >> 0 - n;
            }
            else {
                currentByte = this.currentByte << n;
            }
        }
        while (this.codeSize > this.bitsLeft) {
            if (this.blockIndex >= this.blockSize) {
                this.blockSize = this.readBlock();
                this.blockIndex = 0;
                if (this.blockSize == 0) {
                    return this.endCode;
                }
            }
            ++this.blockIndex;
            this.currentByte = (this.block[this.blockIndex] & 0xFF);
            currentByte += this.currentByte << this.bitsLeft;
            this.bitsLeft += 8;
        }
        this.bitsLeft -= this.codeSize;
        return currentByte & this.codeMask;
    }
    
    int nextPixel() {
        ++this.imageX;
        if (this.imageX > this.imageWidth) {
            ++this.imageY;
            if (this.imageY >= this.imageHeight) {
                return -1;
            }
            this.nextPixels(this.lineArray, this.imageWidth);
            this.imageX = 1;
        }
        return this.lineArray[this.imageX - 1] & 0xFF;
    }
    
    void nextPixels(final byte[] array, final int n) {
        if (this.image.depth == 8) {
            System.arraycopy(this.image.data, this.imageY * this.image.bytesPerLine, array, 0, n);
        }
        else {
            this.image.getPixels(0, this.imageY, n, array, 0);
        }
    }
    
    void nextPutCode(final int n) {
        final int codeSize = this.codeSize;
        this.currentByte |= (n & LZWCodec.MASK_TABLE[this.bitsLeft - 1]) << 8 - this.bitsLeft;
        this.block[this.blockIndex] = (byte)this.currentByte;
        int i = codeSize - this.bitsLeft;
        if (i < 1) {
            this.bitsLeft -= this.codeSize;
            if (this.bitsLeft == 0) {
                this.bitsLeft = 8;
                ++this.blockIndex;
                if (this.blockIndex >= this.blockSize) {
                    this.writeBlock();
                    this.blockIndex = 1;
                }
                this.currentByte = 0;
            }
            return;
        }
        int currentByte = n >> this.bitsLeft;
        ++this.blockIndex;
        if (this.blockIndex >= this.blockSize) {
            this.writeBlock();
            this.blockIndex = 1;
        }
        while (i >= 8) {
            this.currentByte = (currentByte & 0xFF);
            this.block[this.blockIndex] = (byte)this.currentByte;
            currentByte >>= 8;
            i -= 8;
            ++this.blockIndex;
            if (this.blockIndex >= this.blockSize) {
                this.writeBlock();
                this.blockIndex = 1;
            }
        }
        this.bitsLeft = 8 - i;
        this.currentByte = currentByte;
        this.block[this.blockIndex] = (byte)this.currentByte;
    }
    
    void nextPutPixels(final byte[] array) {
        if (this.image.depth == 8) {
            final int n = this.line * this.image.bytesPerLine;
            for (int i = 0; i < this.imageWidth; ++i) {
                this.image.data[n + i] = array[i];
            }
        }
        else {
            this.image.setPixels(0, this.line, this.imageWidth, array, 0);
        }
        if (this.interlaced) {
            if (this.pass == 1) {
                this.copyRow(array, 7);
                this.line += 8;
            }
            else if (this.pass == 2) {
                this.copyRow(array, 3);
                this.line += 8;
            }
            else if (this.pass == 3) {
                this.copyRow(array, 1);
                this.line += 4;
            }
            else if (this.pass == 4) {
                this.line += 2;
            }
            else if (this.pass == 5) {
                this.line += 0;
            }
            if (this.line >= this.imageHeight) {
                ++this.pass;
                if (this.pass == 2) {
                    this.line = 4;
                }
                else if (this.pass == 3) {
                    this.line = 2;
                }
                else if (this.pass == 4) {
                    this.line = 1;
                }
                else if (this.pass == 5) {
                    this.line = 0;
                }
                if (this.pass < 5 && this.loader.hasListeners()) {
                    this.loader.notifyListeners(new ImageLoaderEvent(this.loader, (ImageData)this.image.clone(), this.pass - 2, false));
                }
            }
            if (this.line >= this.imageHeight) {
                this.line = 0;
            }
        }
        else {
            ++this.line;
        }
    }
    
    void copyRow(final byte[] array, final int n) {
        for (int i = 1; i <= n; ++i) {
            if (this.line + i < this.imageHeight) {
                this.image.setPixels(0, this.line + i, this.imageWidth, array, 0);
            }
        }
    }
    
    int readBlock() {
        int n = -1;
        try {
            n = this.inputStream.read();
            if (n == -1) {
                SWT.error(40);
            }
            this.block[0] = (byte)n;
            n = this.inputStream.read(this.block, 1, n);
            if (n == -1) {
                SWT.error(40);
            }
        }
        catch (Exception ex) {
            SWT.error(39, ex);
        }
        return n;
    }
    
    void writeBlock() {
        try {
            this.outputStream.write(this.block, 0, (this.block[0] & 0xFF) + 1);
        }
        catch (Exception ex) {
            SWT.error(39, ex);
        }
    }
}

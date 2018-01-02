// 
// Decompiled by Procyon v0.5.30
// 

public final class Background extends DrawingArea
{
    public byte[] aByteArray1450;
    public final int[] anIntArray1451;
    public int anInt1452;
    public int anInt1453;
    public int anInt1454;
    public int anInt1455;
    public int anInt1456;
    private int anInt1457;
    
    public Background(final StreamLoader streamLoader, final String s, final int n) {
        final Stream stream = new Stream(streamLoader.getDataForName(s + ".dat"));
        final Stream stream2 = new Stream(streamLoader.getDataForName("index.dat"));
        stream2.currentOffset = stream.readUnsignedWord();
        this.anInt1456 = stream2.readUnsignedWord();
        this.anInt1457 = stream2.readUnsignedWord();
        final int unsignedByte = stream2.readUnsignedByte();
        this.anIntArray1451 = new int[unsignedByte];
        for (int i = 0; i < unsignedByte - 1; ++i) {
            this.anIntArray1451[i + 1] = stream2.read3Bytes();
        }
        for (int j = 0; j < n; ++j) {
            final Stream stream3 = stream2;
            stream3.currentOffset += 2;
            final Stream stream4 = stream;
            stream4.currentOffset += stream2.readUnsignedWord() * stream2.readUnsignedWord();
            final Stream stream5 = stream2;
            ++stream5.currentOffset;
        }
        this.anInt1454 = stream2.readUnsignedByte();
        this.anInt1455 = stream2.readUnsignedByte();
        this.anInt1452 = stream2.readUnsignedWord();
        this.anInt1453 = stream2.readUnsignedWord();
        final int unsignedByte2 = stream2.readUnsignedByte();
        final int n2 = this.anInt1452 * this.anInt1453;
        this.aByteArray1450 = new byte[n2];
        if (unsignedByte2 == 0) {
            for (int k = 0; k < n2; ++k) {
                this.aByteArray1450[k] = stream.readSignedByte();
            }
            return;
        }
        if (unsignedByte2 == 1) {
            for (int l = 0; l < this.anInt1452; ++l) {
                for (int n3 = 0; n3 < this.anInt1453; ++n3) {
                    this.aByteArray1450[l + n3 * this.anInt1452] = stream.readSignedByte();
                }
            }
        }
    }
    
    public void method356() {
        this.anInt1456 /= 2;
        this.anInt1457 /= 2;
        final byte[] aByteArray1450 = new byte[this.anInt1456 * this.anInt1457];
        int n = 0;
        for (int i = 0; i < this.anInt1453; ++i) {
            for (int j = 0; j < this.anInt1452; ++j) {
                aByteArray1450[(j + this.anInt1454 >> 1) + (i + this.anInt1455 >> 1) * this.anInt1456] = this.aByteArray1450[n++];
            }
        }
        this.aByteArray1450 = aByteArray1450;
        this.anInt1452 = this.anInt1456;
        this.anInt1453 = this.anInt1457;
        this.anInt1454 = 0;
        this.anInt1455 = 0;
    }
    
    public void method357() {
        if (this.anInt1452 == this.anInt1456 && this.anInt1453 == this.anInt1457) {
            return;
        }
        final byte[] aByteArray1450 = new byte[this.anInt1456 * this.anInt1457];
        int n = 0;
        for (int i = 0; i < this.anInt1453; ++i) {
            for (int j = 0; j < this.anInt1452; ++j) {
                aByteArray1450[j + this.anInt1454 + (i + this.anInt1455) * this.anInt1456] = this.aByteArray1450[n++];
            }
        }
        this.aByteArray1450 = aByteArray1450;
        this.anInt1452 = this.anInt1456;
        this.anInt1453 = this.anInt1457;
        this.anInt1454 = 0;
        this.anInt1455 = 0;
    }
    
    public void method358() {
        final byte[] aByteArray1450 = new byte[this.anInt1452 * this.anInt1453];
        int n = 0;
        for (int i = 0; i < this.anInt1453; ++i) {
            for (int j = this.anInt1452 - 1; j >= 0; --j) {
                aByteArray1450[n++] = this.aByteArray1450[j + i * this.anInt1452];
            }
        }
        this.aByteArray1450 = aByteArray1450;
        this.anInt1454 = this.anInt1456 - this.anInt1452 - this.anInt1454;
    }
    
    public void method359() {
        final byte[] aByteArray1450 = new byte[this.anInt1452 * this.anInt1453];
        int n = 0;
        for (int i = this.anInt1453 - 1; i >= 0; --i) {
            for (int j = 0; j < this.anInt1452; ++j) {
                aByteArray1450[n++] = this.aByteArray1450[j + i * this.anInt1452];
            }
        }
        this.aByteArray1450 = aByteArray1450;
        this.anInt1455 = this.anInt1457 - this.anInt1453 - this.anInt1455;
    }
    
    public void method360(final int n, final int n2, final int n3) {
        for (int i = 0; i < this.anIntArray1451.length; ++i) {
            int n4 = (this.anIntArray1451[i] >> 16 & 0xFF) + n;
            if (n4 < 0) {
                n4 = 0;
            }
            else if (n4 > 255) {
                n4 = 255;
            }
            int n5 = (this.anIntArray1451[i] >> 8 & 0xFF) + n2;
            if (n5 < 0) {
                n5 = 0;
            }
            else if (n5 > 255) {
                n5 = 255;
            }
            int n6 = (this.anIntArray1451[i] & 0xFF) + n3;
            if (n6 < 0) {
                n6 = 0;
            }
            else if (n6 > 255) {
                n6 = 255;
            }
            this.anIntArray1451[i] = (n4 << 16) + (n5 << 8) + n6;
        }
    }
    
    public void drawBackground(int topX, int topY) {
        topX += this.anInt1454;
        topY += this.anInt1455;
        int n = topX + topY * DrawingArea.width;
        int n2 = 0;
        int anInt1453 = this.anInt1453;
        int anInt1454 = this.anInt1452;
        int n3 = DrawingArea.width - anInt1454;
        int n4 = 0;
        if (topY < DrawingArea.topY) {
            final int n5 = DrawingArea.topY - topY;
            anInt1453 -= n5;
            topY = DrawingArea.topY;
            n2 += n5 * anInt1454;
            n += n5 * DrawingArea.width;
        }
        if (topY + anInt1453 > DrawingArea.bottomY) {
            anInt1453 -= topY + anInt1453 - DrawingArea.bottomY;
        }
        if (topX < DrawingArea.topX) {
            final int n6 = DrawingArea.topX - topX;
            anInt1454 -= n6;
            topX = DrawingArea.topX;
            n2 += n6;
            n += n6;
            n4 += n6;
            n3 += n6;
        }
        if (topX + anInt1454 > DrawingArea.bottomX) {
            final int n7 = topX + anInt1454 - DrawingArea.bottomX;
            anInt1454 -= n7;
            n4 += n7;
            n3 += n7;
        }
        if (anInt1454 > 0 && anInt1453 > 0) {
            this.method362(anInt1453, DrawingArea.pixels, this.aByteArray1450, n3, n, anInt1454, n2, this.anIntArray1451, n4);
        }
    }
    
    private void method362(final int n, final int[] array, final byte[] array2, final int n2, int n3, int n4, int n5, final int[] array3, final int n6) {
        final int n7 = -(n4 >> 2);
        n4 = -(n4 & 0x3);
        for (int i = -n; i < 0; ++i) {
            for (int j = n7; j < 0; ++j) {
                final byte b = array2[n5++];
                if (b != 0) {
                    array[n3++] = array3[b & 0xFF];
                }
                else {
                    ++n3;
                }
                final byte b2 = array2[n5++];
                if (b2 != 0) {
                    array[n3++] = array3[b2 & 0xFF];
                }
                else {
                    ++n3;
                }
                final byte b3 = array2[n5++];
                if (b3 != 0) {
                    array[n3++] = array3[b3 & 0xFF];
                }
                else {
                    ++n3;
                }
                final byte b4 = array2[n5++];
                if (b4 != 0) {
                    array[n3++] = array3[b4 & 0xFF];
                }
                else {
                    ++n3;
                }
            }
            for (int k = n4; k < 0; ++k) {
                final byte b5 = array2[n5++];
                if (b5 != 0) {
                    array[n3++] = array3[b5 & 0xFF];
                }
                else {
                    ++n3;
                }
            }
            n3 += n2;
            n5 += n6;
        }
    }
}

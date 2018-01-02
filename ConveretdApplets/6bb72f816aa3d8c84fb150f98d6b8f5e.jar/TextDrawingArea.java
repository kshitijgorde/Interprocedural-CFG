import java.util.Random;

// 
// Decompiled by Procyon v0.5.30
// 

public final class TextDrawingArea extends DrawingArea
{
    public byte[][] aByteArrayArray1491;
    public int[] anIntArray1492;
    public int[] anIntArray1493;
    public int[] anIntArray1494;
    public int[] anIntArray1495;
    public int[] anIntArray1496;
    public int anInt1497;
    public Random aRandom1498;
    public boolean aBoolean1499;
    
    public TextDrawingArea(final boolean b, final String s, final StreamLoader streamLoader) {
        this.aByteArrayArray1491 = new byte[256][];
        this.anIntArray1492 = new int[256];
        this.anIntArray1493 = new int[256];
        this.anIntArray1494 = new int[256];
        this.anIntArray1495 = new int[256];
        this.anIntArray1496 = new int[256];
        this.aRandom1498 = new Random();
        this.aBoolean1499 = false;
        final Stream stream = new Stream(streamLoader.getDataForName(s + ".dat"));
        final Stream stream2 = new Stream(streamLoader.getDataForName("index.dat"));
        stream2.currentOffset = stream.readUnsignedWord() + 4;
        final int unsignedByte = stream2.readUnsignedByte();
        if (unsignedByte > 0) {
            final Stream stream3 = stream2;
            stream3.currentOffset += 3 * (unsignedByte - 1);
        }
        for (int i = 0; i < 256; ++i) {
            this.anIntArray1494[i] = stream2.readUnsignedByte();
            this.anIntArray1495[i] = stream2.readUnsignedByte();
            final int[] anIntArray1492 = this.anIntArray1492;
            final int n = i;
            final int unsignedWord = stream2.readUnsignedWord();
            anIntArray1492[n] = unsignedWord;
            final int n2 = unsignedWord;
            final int[] anIntArray1493 = this.anIntArray1493;
            final int n3 = i;
            final int unsignedWord2 = stream2.readUnsignedWord();
            anIntArray1493[n3] = unsignedWord2;
            final int anInt1497 = unsignedWord2;
            final int unsignedByte2 = stream2.readUnsignedByte();
            final int n4 = n2 * anInt1497;
            this.aByteArrayArray1491[i] = new byte[n4];
            if (unsignedByte2 == 0) {
                for (int j = 0; j < n4; ++j) {
                    this.aByteArrayArray1491[i][j] = stream.readSignedByte();
                }
            }
            else if (unsignedByte2 == 1) {
                for (int k = 0; k < n2; ++k) {
                    for (int l = 0; l < anInt1497; ++l) {
                        this.aByteArrayArray1491[i][k + l * n2] = stream.readSignedByte();
                    }
                }
            }
            if (anInt1497 > this.anInt1497 && i < 128) {
                this.anInt1497 = anInt1497;
            }
            this.anIntArray1494[i] = 1;
            this.anIntArray1496[i] = n2 + 2;
            byte b2 = 0;
            for (int n5 = anInt1497 / 7; n5 < anInt1497; ++n5) {
                b2 += this.aByteArrayArray1491[i][n5 * n2];
            }
            if (b2 <= anInt1497 / 7) {
                final int[] anIntArray1494 = this.anIntArray1496;
                final int n6 = i;
                --anIntArray1494[n6];
                this.anIntArray1494[i] = 0;
            }
            byte b3 = 0;
            for (int n7 = anInt1497 / 7; n7 < anInt1497; ++n7) {
                b3 += this.aByteArrayArray1491[i][n2 - 1 + n7 * n2];
            }
            if (b3 <= anInt1497 / 7) {
                final int[] anIntArray1495 = this.anIntArray1496;
                final int n8 = i;
                --anIntArray1495[n8];
            }
        }
        if (b) {
            this.anIntArray1496[32] = this.anIntArray1496[73];
        }
        else {
            this.anIntArray1496[32] = this.anIntArray1496[105];
        }
    }
    
    public void method380(final String s, final int n, final int n2, final int n3) {
        this.method385(n2, s, n3, n - this.method384(s));
    }
    
    public void drawText(final int n, final String s, final int n2, final int n3) {
        this.drawText(n, s, n2, n3, true);
    }
    
    public void drawText(final int n, final String s, final int n2, final int n3, final boolean b) {
        this.method385(n, s, n2, b ? (n3 - this.method384(s) / 2) : n3);
    }
    
    public void method382(final int n, final int n2, final String s, final int n3, final boolean b) {
        this.method389(b, n2 - this.getTextWidth(s) / 2, n, s, n3);
    }
    
    public void drawChatInput(final int n, final int n2, final String s, final int n3, final boolean b) {
        this.method389(b, n2, n, s, n3);
    }
    
    public int getTextWidth(final String s) {
        if (s == null) {
            return 0;
        }
        int n = 0;
        for (int i = 0; i < s.length(); ++i) {
            if (s.charAt(i) == '@' && i + 4 < s.length() && s.charAt(i + 4) == '@') {
                i += 4;
            }
            else {
                n += this.anIntArray1496[s.charAt(i)];
            }
        }
        return n;
    }
    
    public int method384(final String s) {
        if (s == null) {
            return 0;
        }
        int n = 0;
        for (int i = 0; i < s.length(); ++i) {
            n += this.anIntArray1496[s.charAt(i)];
        }
        return n;
    }
    
    public void method385(final int n, final String s, int n2, int n3) {
        if (s == null) {
            return;
        }
        n2 -= this.anInt1497;
        for (int i = 0; i < s.length(); ++i) {
            final char char1 = s.charAt(i);
            if (char1 != ' ') {
                this.method392(this.aByteArrayArray1491[char1], n3 + this.anIntArray1494[char1], n2 + this.anIntArray1495[char1], this.anIntArray1492[char1], this.anIntArray1493[char1], n);
            }
            n3 += this.anIntArray1496[char1];
        }
    }
    
    public void method386(final int n, final String s, int n2, final int n3, int n4) {
        if (s == null) {
            return;
        }
        n2 -= this.method384(s) / 2;
        n4 -= this.anInt1497;
        for (int i = 0; i < s.length(); ++i) {
            final char char1 = s.charAt(i);
            if (char1 != ' ') {
                this.method392(this.aByteArrayArray1491[char1], n2 + this.anIntArray1494[char1], n4 + this.anIntArray1495[char1] + (int)(Math.sin(i / 2.0 + n3 / 5.0) * 5.0), this.anIntArray1492[char1], this.anIntArray1493[char1], n);
            }
            n2 += this.anIntArray1496[char1];
        }
    }
    
    public void method387(int n, final String s, final int n2, int n3, final int n4) {
        if (s == null) {
            return;
        }
        n -= this.method384(s) / 2;
        n3 -= this.anInt1497;
        for (int i = 0; i < s.length(); ++i) {
            final char char1 = s.charAt(i);
            if (char1 != ' ') {
                this.method392(this.aByteArrayArray1491[char1], n + this.anIntArray1494[char1] + (int)(Math.sin(i / 5.0 + n2 / 5.0) * 5.0), n3 + this.anIntArray1495[char1] + (int)(Math.sin(i / 3.0 + n2 / 5.0) * 5.0), this.anIntArray1492[char1], this.anIntArray1493[char1], n4);
            }
            n += this.anIntArray1496[char1];
        }
    }
    
    public void method388(final int n, final String s, final int n2, int n3, int n4, final int n5) {
        if (s == null) {
            return;
        }
        double n6 = 7.0 - n / 8.0;
        if (n6 < 0.0) {
            n6 = 0.0;
        }
        n4 -= this.method384(s) / 2;
        n3 -= this.anInt1497;
        for (int i = 0; i < s.length(); ++i) {
            final char char1 = s.charAt(i);
            if (char1 != ' ') {
                this.method392(this.aByteArrayArray1491[char1], n4 + this.anIntArray1494[char1], n3 + this.anIntArray1495[char1] + (int)(Math.sin(i / 1.5 + n2) * n6), this.anIntArray1492[char1], this.anIntArray1493[char1], n5);
            }
            n4 += this.anIntArray1496[char1];
        }
    }
    
    public void method389(final boolean b, int n, int n2, final String s, int n3) {
        this.aBoolean1499 = false;
        final int n4 = n;
        if (s == null) {
            return;
        }
        n3 -= this.anInt1497;
        for (int i = 0; i < s.length(); ++i) {
            if (s.charAt(i) == '@' && i + 4 < s.length() && s.charAt(i + 4) == '@') {
                final int colorByName = this.getColorByName(s.substring(i + 1, i + 4));
                if (colorByName != -1) {
                    n2 = colorByName;
                }
                i += 4;
            }
            else {
                final char char1 = s.charAt(i);
                if (char1 != ' ') {
                    if (b) {
                        this.method392(this.aByteArrayArray1491[char1], n + this.anIntArray1494[char1] + 1, n3 + this.anIntArray1495[char1] + 1, this.anIntArray1492[char1], this.anIntArray1493[char1], 0);
                    }
                    this.method392(this.aByteArrayArray1491[char1], n + this.anIntArray1494[char1], n3 + this.anIntArray1495[char1], this.anIntArray1492[char1], this.anIntArray1493[char1], n2);
                }
                n += this.anIntArray1496[char1];
            }
        }
        if (this.aBoolean1499) {
            DrawingArea.method339(n3 + (int)(this.anInt1497 * 0.7), 8388608, n - n4, n4);
        }
    }
    
    public void method390(int n, int n2, final String s, final int n3, int n4) {
        if (s == null) {
            return;
        }
        this.aRandom1498.setSeed(n3);
        final int n5 = 192 + (this.aRandom1498.nextInt() & 0x1F);
        n4 -= this.anInt1497;
        for (int i = 0; i < s.length(); ++i) {
            if (s.charAt(i) == '@' && i + 4 < s.length() && s.charAt(i + 4) == '@') {
                final int colorByName = this.getColorByName(s.substring(i + 1, i + 4));
                if (colorByName != -1) {
                    n2 = colorByName;
                }
                i += 4;
            }
            else {
                final char char1 = s.charAt(i);
                if (char1 != ' ') {
                    this.method394(192, n + this.anIntArray1494[char1] + 1, this.aByteArrayArray1491[char1], this.anIntArray1492[char1], n4 + this.anIntArray1495[char1] + 1, this.anIntArray1493[char1], 0);
                    this.method394(n5, n + this.anIntArray1494[char1], this.aByteArrayArray1491[char1], this.anIntArray1492[char1], n4 + this.anIntArray1495[char1], this.anIntArray1493[char1], n2);
                }
                n += this.anIntArray1496[char1];
                if ((this.aRandom1498.nextInt() & 0x3) == 0x0) {
                    ++n;
                }
            }
        }
    }
    
    private int getColorByName(final String s) {
        if (s.equals("369")) {
            return 3368601;
        }
        if (s.equals("mon")) {
            return 65408;
        }
        if (s.equals("red")) {
            return 16711680;
        }
        if (s.equals("gre")) {
            return 65280;
        }
        if (s.equals("blu")) {
            return 255;
        }
        if (s.equals("yel")) {
            return 16776960;
        }
        if (s.equals("cya")) {
            return 65535;
        }
        if (s.equals("mag")) {
            return 16711935;
        }
        if (s.equals("whi")) {
            return 16777215;
        }
        if (s.equals("bla")) {
            return 0;
        }
        if (s.equals("lre")) {
            return 16748608;
        }
        if (s.equals("dre")) {
            return 8388608;
        }
        if (s.equals("dbl")) {
            return 128;
        }
        if (s.equals("or1")) {
            return 16756736;
        }
        if (s.equals("or2")) {
            return 16740352;
        }
        if (s.equals("or3")) {
            return 16723968;
        }
        if (s.equals("gr1")) {
            return 12648192;
        }
        if (s.equals("gr2")) {
            return 8453888;
        }
        if (s.equals("gr3")) {
            return 4259584;
        }
        if (s.equals("str")) {
            this.aBoolean1499 = true;
        }
        if (s.equals("end")) {
            this.aBoolean1499 = false;
        }
        return -1;
    }
    
    private void method392(final byte[] array, int topX, int topY, int n, int n2, final int n3) {
        int n4 = topX + topY * TextDrawingArea.width;
        int n5 = TextDrawingArea.width - n;
        int n6 = 0;
        int n7 = 0;
        if (topY < TextDrawingArea.topY) {
            final int n8 = TextDrawingArea.topY - topY;
            n2 -= n8;
            topY = TextDrawingArea.topY;
            n7 += n8 * n;
            n4 += n8 * TextDrawingArea.width;
        }
        if (topY + n2 >= TextDrawingArea.bottomY) {
            n2 -= topY + n2 - TextDrawingArea.bottomY;
        }
        if (topX < TextDrawingArea.topX) {
            final int n9 = TextDrawingArea.topX - topX;
            n -= n9;
            topX = TextDrawingArea.topX;
            n7 += n9;
            n4 += n9;
            n6 += n9;
            n5 += n9;
        }
        if (topX + n >= TextDrawingArea.bottomX) {
            final int n10 = topX + n - TextDrawingArea.bottomX;
            n -= n10;
            n6 += n10;
            n5 += n10;
        }
        if (n > 0 && n2 > 0) {
            this.method393(TextDrawingArea.pixels, array, n3, n7, n4, n, n2, n5, n6);
        }
    }
    
    private void method393(final int[] array, final byte[] array2, final int n, int n2, int n3, int n4, final int n5, final int n6, final int n7) {
        final int n8 = -(n4 >> 2);
        n4 = -(n4 & 0x3);
        for (int i = -n5; i < 0; ++i) {
            for (int j = n8; j < 0; ++j) {
                if (array2[n2++] != 0) {
                    array[n3++] = n;
                }
                else {
                    ++n3;
                }
                if (array2[n2++] != 0) {
                    array[n3++] = n;
                }
                else {
                    ++n3;
                }
                if (array2[n2++] != 0) {
                    array[n3++] = n;
                }
                else {
                    ++n3;
                }
                if (array2[n2++] != 0) {
                    array[n3++] = n;
                }
                else {
                    ++n3;
                }
            }
            for (int k = n4; k < 0; ++k) {
                if (array2[n2++] != 0) {
                    array[n3++] = n;
                }
                else {
                    ++n3;
                }
            }
            n3 += n6;
            n2 += n7;
        }
    }
    
    private void method394(final int n, int topX, final byte[] array, int n2, int topY, int n3, final int n4) {
        int n5 = topX + topY * TextDrawingArea.width;
        int n6 = TextDrawingArea.width - n2;
        int n7 = 0;
        int n8 = 0;
        if (topY < TextDrawingArea.topY) {
            final int n9 = TextDrawingArea.topY - topY;
            n3 -= n9;
            topY = TextDrawingArea.topY;
            n8 += n9 * n2;
            n5 += n9 * TextDrawingArea.width;
        }
        if (topY + n3 >= TextDrawingArea.bottomY) {
            n3 -= topY + n3 - TextDrawingArea.bottomY;
        }
        if (topX < TextDrawingArea.topX) {
            final int n10 = TextDrawingArea.topX - topX;
            n2 -= n10;
            topX = TextDrawingArea.topX;
            n8 += n10;
            n5 += n10;
            n7 += n10;
            n6 += n10;
        }
        if (topX + n2 >= TextDrawingArea.bottomX) {
            final int n11 = topX + n2 - TextDrawingArea.bottomX;
            n2 -= n11;
            n7 += n11;
            n6 += n11;
        }
        if (n2 <= 0 || n3 <= 0) {
            return;
        }
        this.method395(array, n3, n5, TextDrawingArea.pixels, n8, n2, n7, n6, n4, n);
    }
    
    private void method395(final byte[] array, final int n, int n2, final int[] array2, int n3, final int n4, final int n5, final int n6, int n7, int n8) {
        n7 = ((n7 & 0xFF00FF) * n8 & 0xFF00FF00) + ((n7 & 0xFF00) * n8 & 0xFF0000) >> 8;
        n8 = 256 - n8;
        for (int i = -n; i < 0; ++i) {
            for (int j = -n4; j < 0; ++j) {
                if (array[n3++] != 0) {
                    final int n9 = array2[n2];
                    array2[n2++] = (((n9 & 0xFF00FF) * n8 & 0xFF00FF00) + ((n9 & 0xFF00) * n8 & 0xFF0000) >> 8) + n7;
                }
                else {
                    ++n2;
                }
            }
            n2 += n6;
            n3 += n5;
        }
    }
}

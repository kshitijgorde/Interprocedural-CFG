import javax.swing.ImageIcon;
import java.awt.Image;
import java.awt.image.PixelGrabber;
import java.awt.image.ImageObserver;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.awt.Component;

// 
// Decompiled by Procyon v0.5.30
// 

public final class Sprite extends DrawingArea
{
    public String location;
    public int[] myPixels;
    public int myWidth;
    public int myHeight;
    public int anInt1442;
    public int anInt1443;
    public int anInt1444;
    public int anInt1445;
    
    public Sprite(final int n, final int n2) {
        this.location = SignLink.findcachedir() + "/Sprites/";
        this.myPixels = new int[n * n2];
        this.anInt1444 = n;
        this.myWidth = n;
        this.anInt1445 = n2;
        this.myHeight = n2;
        final boolean b = false;
        this.anInt1443 = (b ? 1 : 0);
        this.anInt1442 = (b ? 1 : 0);
    }
    
    public void drawSpriteOpacity(int topX, int topY, final int n) {
        topX += this.anInt1442;
        topY += this.anInt1443;
        int n2 = topX + topY * DrawingArea.width;
        int n3 = 0;
        int myHeight = this.myHeight;
        int myWidth = this.myWidth;
        int n4 = DrawingArea.width - myWidth;
        int n5 = 0;
        if (topY < DrawingArea.topY) {
            final int n6 = DrawingArea.topY - topY;
            myHeight -= n6;
            topY = DrawingArea.topY;
            n3 += n6 * myWidth;
            n2 += n6 * DrawingArea.width;
        }
        if (topY + myHeight > DrawingArea.bottomY) {
            myHeight -= topY + myHeight - DrawingArea.bottomY;
        }
        if (topX < DrawingArea.topX) {
            final int n7 = DrawingArea.topX - topX;
            myWidth -= n7;
            topX = DrawingArea.topX;
            n3 += n7;
            n2 += n7;
            n5 += n7;
            n4 += n7;
        }
        if (topX + myWidth > DrawingArea.bottomX) {
            final int n8 = topX + myWidth - DrawingArea.bottomX;
            myWidth -= n8;
            n5 += n8;
            n4 += n8;
        }
        this.setTransparency(255, 255, 255);
        if (myWidth > 0 && myHeight > 0) {
            this.method351(n3, myWidth, DrawingArea.pixels, this.myPixels, n5, myHeight, n4, n, n2);
        }
    }
    
    public void setTransparency(final int n, final int n2, final int n3) {
        for (int i = 0; i < this.myPixels.length; ++i) {
            if ((this.myPixels[i] >> 16 & 0xFF) == n && (this.myPixels[i] >> 8 & 0xFF) == n2 && (this.myPixels[i] & 0xFF) == n3) {
                this.myPixels[i] = 0;
            }
        }
    }
    
    public Sprite(final byte[] array, final Component component) {
        this.location = SignLink.findcachedir() + "/Sprites/";
        try {
            final Image image = Toolkit.getDefaultToolkit().createImage(array);
            final MediaTracker mediaTracker = new MediaTracker(component);
            mediaTracker.addImage(image, 0);
            mediaTracker.waitForAll();
            this.myWidth = image.getWidth(component);
            this.myHeight = image.getHeight(component);
            this.anInt1444 = this.myWidth;
            this.anInt1445 = this.myHeight;
            this.anInt1442 = 0;
            this.anInt1443 = 0;
            this.myPixels = new int[this.myWidth * this.myHeight];
            new PixelGrabber(image, 0, 0, this.myWidth, this.myHeight, this.myPixels, 0, this.myWidth).grabPixels();
        }
        catch (Exception ex) {
            System.out.println("Error converting jpg");
        }
    }
    
    public Sprite(final String s, final int myWidth, final int myHeight, final boolean b) {
        this.location = SignLink.findcachedir() + "/Sprites/";
        try {
            Image image;
            if (b) {
                image = Toolkit.getDefaultToolkit().createImage(this.location + s + ".png");
            }
            else {
                image = Toolkit.getDefaultToolkit().createImage(FileOperations.ReadFile(s));
            }
            this.myWidth = myWidth;
            this.myHeight = myHeight;
            this.anInt1444 = this.myWidth;
            this.anInt1445 = this.myHeight;
            this.anInt1442 = 0;
            this.anInt1443 = 0;
            this.myPixels = new int[this.myWidth * this.myHeight];
            new PixelGrabber(image, 0, 0, this.myWidth, this.myHeight, this.myPixels, 0, this.myWidth).grabPixels();
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
    }
    
    public Sprite(final String s) {
        this.location = SignLink.findcachedir() + "/Sprites/";
        try {
            final Image image = Toolkit.getDefaultToolkit().getImage(this.location + s + ".png");
            final ImageIcon imageIcon = new ImageIcon(image);
            this.myWidth = imageIcon.getIconWidth();
            this.myHeight = imageIcon.getIconHeight();
            this.anInt1444 = this.myWidth;
            this.anInt1445 = this.myHeight;
            this.anInt1442 = 0;
            this.anInt1443 = 0;
            this.myPixels = new int[this.myWidth * this.myHeight];
            new PixelGrabber(image, 0, 0, this.myWidth, this.myHeight, this.myPixels, 0, this.myWidth).grabPixels();
            this.setTransparency(255, 0, 255);
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
    }
    
    public Sprite(final StreamLoader streamLoader, final String s, final int n) {
        this.location = SignLink.findcachedir() + "/Sprites/";
        final Stream stream = new Stream(streamLoader.getDataForName(s + ".dat"));
        final Stream stream2 = new Stream(streamLoader.getDataForName("index.dat"));
        stream2.currentOffset = stream.readUnsignedWord();
        this.anInt1444 = stream2.readUnsignedWord();
        this.anInt1445 = stream2.readUnsignedWord();
        final int unsignedByte = stream2.readUnsignedByte();
        final int[] array = new int[unsignedByte];
        for (int i = 0; i < unsignedByte - 1; ++i) {
            array[i + 1] = stream2.read3Bytes();
            if (array[i + 1] == 0) {
                array[i + 1] = 1;
            }
        }
        for (int j = 0; j < n; ++j) {
            final Stream stream3 = stream2;
            stream3.currentOffset += 2;
            final Stream stream4 = stream;
            stream4.currentOffset += stream2.readUnsignedWord() * stream2.readUnsignedWord();
            final Stream stream5 = stream2;
            ++stream5.currentOffset;
        }
        this.anInt1442 = stream2.readUnsignedByte();
        this.anInt1443 = stream2.readUnsignedByte();
        this.myWidth = stream2.readUnsignedWord();
        this.myHeight = stream2.readUnsignedWord();
        final int unsignedByte2 = stream2.readUnsignedByte();
        final int n2 = this.myWidth * this.myHeight;
        this.myPixels = new int[n2];
        if (unsignedByte2 == 0) {
            for (int k = 0; k < n2; ++k) {
                this.myPixels[k] = array[stream.readUnsignedByte()];
            }
            this.setTransparency(255, 0, 255);
            return;
        }
        if (unsignedByte2 == 1) {
            for (int l = 0; l < this.myWidth; ++l) {
                for (int n3 = 0; n3 < this.myHeight; ++n3) {
                    this.myPixels[l + n3 * this.myWidth] = array[stream.readUnsignedByte()];
                }
            }
        }
        this.setTransparency(255, 0, 255);
    }
    
    public void method343() {
        DrawingArea.initDrawingArea(this.myHeight, this.myWidth, this.myPixels);
    }
    
    public void method344(final int n, final int n2, final int n3) {
        for (int i = 0; i < this.myPixels.length; ++i) {
            final int n4 = this.myPixels[i];
            if (n4 != 0) {
                int n5 = (n4 >> 16 & 0xFF) + n;
                if (n5 < 1) {
                    n5 = 1;
                }
                else if (n5 > 255) {
                    n5 = 255;
                }
                int n6 = (n4 >> 8 & 0xFF) + n2;
                if (n6 < 1) {
                    n6 = 1;
                }
                else if (n6 > 255) {
                    n6 = 255;
                }
                int n7 = (n4 & 0xFF) + n3;
                if (n7 < 1) {
                    n7 = 1;
                }
                else if (n7 > 255) {
                    n7 = 255;
                }
                this.myPixels[i] = (n5 << 16) + (n6 << 8) + n7;
            }
        }
    }
    
    public void method345() {
        try {
            final int[] myPixels = new int[this.anInt1444 * this.anInt1445];
            for (int i = 0; i < this.myHeight; ++i) {
                for (int j = 0; j < this.myWidth; ++j) {
                    myPixels[(i + this.anInt1443) * this.anInt1444 + (j + this.anInt1442)] = this.myPixels[i * this.myWidth + j];
                }
            }
            this.myPixels = myPixels;
            this.myWidth = this.anInt1444;
            this.myHeight = this.anInt1445;
            this.anInt1442 = 0;
            this.anInt1443 = 0;
        }
        catch (RuntimeException ex) {
            SignLink.reporterror("26341, " + ex.toString());
            throw new RuntimeException();
        }
    }
    
    public void method346(int topX, int topY) {
        topX += this.anInt1442;
        topY += this.anInt1443;
        int n = topX + topY * DrawingArea.width;
        int n2 = 0;
        int myHeight = this.myHeight;
        int myWidth = this.myWidth;
        int n3 = DrawingArea.width - myWidth;
        int n4 = 0;
        if (topY < DrawingArea.topY) {
            final int n5 = DrawingArea.topY - topY;
            myHeight -= n5;
            topY = DrawingArea.topY;
            n2 += n5 * myWidth;
            n += n5 * DrawingArea.width;
        }
        if (topY + myHeight > DrawingArea.bottomY) {
            myHeight -= topY + myHeight - DrawingArea.bottomY;
        }
        if (topX < DrawingArea.topX) {
            final int n6 = DrawingArea.topX - topX;
            myWidth -= n6;
            topX = DrawingArea.topX;
            n2 += n6;
            n += n6;
            n4 += n6;
            n3 += n6;
        }
        if (topX + myWidth > DrawingArea.bottomX) {
            final int n7 = topX + myWidth - DrawingArea.bottomX;
            myWidth -= n7;
            n4 += n7;
            n3 += n7;
        }
        if (myWidth > 0) {
            if (myHeight > 0) {
                this.method347(n, myWidth, myHeight, n4, n2, n3, this.myPixels, DrawingArea.pixels);
            }
        }
    }
    
    private void method347(int n, int n2, final int n3, final int n4, int n5, final int n6, final int[] array, final int[] array2) {
        final int n7 = -(n2 >> 2);
        n2 = -(n2 & 0x3);
        for (int i = -n3; i < 0; ++i) {
            for (int j = n7; j < 0; ++j) {
                array2[n++] = array[n5++];
                array2[n++] = array[n5++];
                array2[n++] = array[n5++];
                array2[n++] = array[n5++];
            }
            for (int k = n2; k < 0; ++k) {
                array2[n++] = array[n5++];
            }
            n += n6;
            n5 += n4;
        }
    }
    
    public void drawSprite1(int topX, int topY) {
        final int n = 128;
        topX += this.anInt1442;
        topY += this.anInt1443;
        int n2 = topX + topY * DrawingArea.width;
        int n3 = 0;
        int myHeight = this.myHeight;
        int myWidth = this.myWidth;
        int n4 = DrawingArea.width - myWidth;
        int n5 = 0;
        if (topY < DrawingArea.topY) {
            final int n6 = DrawingArea.topY - topY;
            myHeight -= n6;
            topY = DrawingArea.topY;
            n3 += n6 * myWidth;
            n2 += n6 * DrawingArea.width;
        }
        if (topY + myHeight > DrawingArea.bottomY) {
            myHeight -= topY + myHeight - DrawingArea.bottomY;
        }
        if (topX < DrawingArea.topX) {
            final int n7 = DrawingArea.topX - topX;
            myWidth -= n7;
            topX = DrawingArea.topX;
            n3 += n7;
            n2 += n7;
            n5 += n7;
            n4 += n7;
        }
        if (topX + myWidth > DrawingArea.bottomX) {
            final int n8 = topX + myWidth - DrawingArea.bottomX;
            myWidth -= n8;
            n5 += n8;
            n4 += n8;
        }
        if (myWidth > 0 && myHeight > 0) {
            this.method351(n3, myWidth, DrawingArea.pixels, this.myPixels, n5, myHeight, n4, n, n2);
        }
    }
    
    public void drawSprite3(int topX, int topY) {
        final int n = 255;
        topX += this.anInt1442;
        topY += this.anInt1443;
        int n2 = topX + topY * DrawingArea.width;
        int n3 = 0;
        int myHeight = this.myHeight;
        int myWidth = this.myWidth;
        int n4 = DrawingArea.width - myWidth;
        int n5 = 0;
        if (topY < DrawingArea.topY) {
            final int n6 = DrawingArea.topY - topY;
            myHeight -= n6;
            topY = DrawingArea.topY;
            n3 += n6 * myWidth;
            n2 += n6 * DrawingArea.width;
        }
        if (topY + myHeight > DrawingArea.bottomY) {
            myHeight -= topY + myHeight - DrawingArea.bottomY;
        }
        if (topX < DrawingArea.topX) {
            final int n7 = DrawingArea.topX - topX;
            myWidth -= n7;
            topX = DrawingArea.topX;
            n3 += n7;
            n2 += n7;
            n5 += n7;
            n4 += n7;
        }
        if (topX + myWidth > DrawingArea.bottomX) {
            final int n8 = topX + myWidth - DrawingArea.bottomX;
            myWidth -= n8;
            n5 += n8;
            n4 += n8;
        }
        if (myWidth > 0 && myHeight > 0) {
            this.method351(n3, myWidth, DrawingArea.pixels, this.myPixels, n5, myHeight, n4, n, n2);
        }
    }
    
    public void drawSprite(int topX, int topY) {
        topX += this.anInt1442;
        topY += this.anInt1443;
        int n = topX + topY * DrawingArea.width;
        int n2 = 0;
        int myHeight = this.myHeight;
        int myWidth = this.myWidth;
        int n3 = DrawingArea.width - myWidth;
        int n4 = 0;
        if (topY < DrawingArea.topY) {
            final int n5 = DrawingArea.topY - topY;
            myHeight -= n5;
            topY = DrawingArea.topY;
            n2 += n5 * myWidth;
            n += n5 * DrawingArea.width;
        }
        if (topY + myHeight > DrawingArea.bottomY) {
            myHeight -= topY + myHeight - DrawingArea.bottomY;
        }
        if (topX < DrawingArea.topX) {
            final int n6 = DrawingArea.topX - topX;
            myWidth -= n6;
            topX = DrawingArea.topX;
            n2 += n6;
            n += n6;
            n4 += n6;
            n3 += n6;
        }
        if (topX + myWidth > DrawingArea.bottomX) {
            final int n7 = topX + myWidth - DrawingArea.bottomX;
            myWidth -= n7;
            n4 += n7;
            n3 += n7;
        }
        if (myWidth > 0 && myHeight > 0) {
            this.method349(DrawingArea.pixels, this.myPixels, n2, n, myWidth, myHeight, n3, n4);
        }
    }
    
    public void drawSprite(int topX, int topY, final int n) {
        final int n2 = this.myWidth + 2;
        final int n3 = this.myHeight + 2;
        final int[] array = new int[n2 * n3];
        for (int i = 0; i < this.myWidth; ++i) {
            for (int j = 0; j < this.myHeight; ++j) {
                if (this.myPixels[i + j * this.myWidth] != 0) {
                    array[i + 1 + (j + 1) * n2] = this.myPixels[i + j * this.myWidth];
                }
            }
        }
        for (int k = 0; k < n2; ++k) {
            for (int l = 0; l < n3; ++l) {
                if (array[k + l * n2] == 0) {
                    if (k < n2 - 1 && array[k + 1 + l * n2] > 0 && array[k + 1 + l * n2] != 16777215) {
                        array[k + l * n2] = n;
                    }
                    if (k > 0 && array[k - 1 + l * n2] > 0 && array[k - 1 + l * n2] != 16777215) {
                        array[k + l * n2] = n;
                    }
                    if (l < n3 - 1 && array[k + (l + 1) * n2] > 0 && array[k + (l + 1) * n2] != 16777215) {
                        array[k + l * n2] = n;
                    }
                    if (l > 0 && array[k + (l - 1) * n2] > 0 && array[k + (l - 1) * n2] != 16777215) {
                        array[k + l * n2] = n;
                    }
                }
            }
        }
        --topX;
        --topY;
        topX += this.anInt1442;
        topY += this.anInt1443;
        int n4 = topX + topY * DrawingArea.width;
        int n5 = 0;
        int n6 = n3;
        int n7 = n2;
        int n8 = DrawingArea.width - n7;
        int n9 = 0;
        if (topY < DrawingArea.topY) {
            final int n10 = DrawingArea.topY - topY;
            n6 -= n10;
            topY = DrawingArea.topY;
            n5 += n10 * n7;
            n4 += n10 * DrawingArea.width;
        }
        if (topY + n6 > DrawingArea.bottomY) {
            n6 -= topY + n6 - DrawingArea.bottomY;
        }
        if (topX < DrawingArea.topX) {
            final int n11 = DrawingArea.topX - topX;
            n7 -= n11;
            topX = DrawingArea.topX;
            n5 += n11;
            n4 += n11;
            n9 += n11;
            n8 += n11;
        }
        if (topX + n7 > DrawingArea.bottomX) {
            final int n12 = topX + n7 - DrawingArea.bottomX;
            n7 -= n12;
            n9 += n12;
            n8 += n12;
        }
        if (n7 > 0 && n6 > 0) {
            this.method349(DrawingArea.pixels, array, n5, n4, n7, n6, n8, n9);
        }
    }
    
    public void drawSprite2(int topX, int topY) {
        final int n = 225;
        topX += this.anInt1442;
        topY += this.anInt1443;
        int n2 = topX + topY * DrawingArea.width;
        int n3 = 0;
        int myHeight = this.myHeight;
        int myWidth = this.myWidth;
        int n4 = DrawingArea.width - myWidth;
        int n5 = 0;
        if (topY < DrawingArea.topY) {
            final int n6 = DrawingArea.topY - topY;
            myHeight -= n6;
            topY = DrawingArea.topY;
            n3 += n6 * myWidth;
            n2 += n6 * DrawingArea.width;
        }
        if (topY + myHeight > DrawingArea.bottomY) {
            myHeight -= topY + myHeight - DrawingArea.bottomY;
        }
        if (topX < DrawingArea.topX) {
            final int n7 = DrawingArea.topX - topX;
            myWidth -= n7;
            topX = DrawingArea.topX;
            n3 += n7;
            n2 += n7;
            n5 += n7;
            n4 += n7;
        }
        if (topX + myWidth > DrawingArea.bottomX) {
            final int n8 = topX + myWidth - DrawingArea.bottomX;
            myWidth -= n8;
            n5 += n8;
            n4 += n8;
        }
        if (myWidth > 0 && myHeight > 0) {
            this.method351(n3, myWidth, DrawingArea.pixels, this.myPixels, n5, myHeight, n4, n, n2);
        }
    }
    
    private void method349(final int[] array, final int[] array2, int n, int n2, int n3, final int n4, final int n5, final int n6) {
        final int n7 = -(n3 >> 2);
        n3 = -(n3 & 0x3);
        for (int i = -n4; i < 0; ++i) {
            for (int j = n7; j < 0; ++j) {
                final int n8 = array2[n++];
                if (n8 != 0 && n8 != -1) {
                    array[n2++] = n8;
                }
                else {
                    ++n2;
                }
                final int n9 = array2[n++];
                if (n9 != 0 && n9 != -1) {
                    array[n2++] = n9;
                }
                else {
                    ++n2;
                }
                final int n10 = array2[n++];
                if (n10 != 0 && n10 != -1) {
                    array[n2++] = n10;
                }
                else {
                    ++n2;
                }
                final int n11 = array2[n++];
                if (n11 != 0 && n11 != -1) {
                    array[n2++] = n11;
                }
                else {
                    ++n2;
                }
            }
            for (int k = n3; k < 0; ++k) {
                final int n12 = array2[n++];
                if (n12 != 0 && n12 != -1) {
                    array[n2++] = n12;
                }
                else {
                    ++n2;
                }
            }
            n2 += n5;
            n += n6;
        }
    }
    
    private void method351(int n, final int n2, final int[] array, final int[] array2, final int n3, final int n4, final int n5, final int n6, int n7) {
        final int n8 = 256 - n6;
        for (int i = -n4; i < 0; ++i) {
            for (int j = -n2; j < 0; ++j) {
                final int n9 = array2[n++];
                if (n9 != 0) {
                    final int n10 = array[n7];
                    array[n7++] = ((n9 & 0xFF00FF) * n6 + (n10 & 0xFF00FF) * n8 & 0xFF00FF00) + ((n9 & 0xFF00) * n6 + (n10 & 0xFF00) * n8 & 0xFF0000) >> 8;
                }
                else {
                    ++n7;
                }
            }
            n7 += n5;
            n += n3;
        }
    }
    
    public void method352(final int n, final int n2, final int[] array, final int n3, final int[] array2, final int n4, int i, int j, final int n5, final int n6) {
        try {
            final int n7 = -n5 / 2;
            final int n8 = -n / 2;
            final int n9 = (int)(Math.sin(n2 / 326.11) * 65536.0);
            final int n10 = (int)(Math.cos(n2 / 326.11) * 65536.0);
            final int n11 = n9 * n3 >> 8;
            final int n12 = n10 * n3 >> 8;
            int n13 = (n6 << 16) + (n8 * n11 + n7 * n12);
            int n14 = (n4 << 16) + (n8 * n12 - n7 * n11);
            int n15 = j + i * DrawingArea.width;
            int n16;
            int n17;
            int n18;
            int n19;
            for (i = 0; i < n; ++i) {
                n16 = array2[i];
                n17 = n15 + n16;
                n18 = n13 + n12 * n16;
                n19 = n14 - n11 * n16;
                for (j = -array[i]; j < 0; ++j) {
                    DrawingArea.pixels[n17++] = this.myPixels[(n18 >> 16) + (n19 >> 16) * this.myWidth];
                    n18 += n12;
                    n19 -= n11;
                }
                n13 += n11;
                n14 += n12;
                n15 += DrawingArea.width;
            }
        }
        catch (Exception ex) {}
    }
    
    public void method353(int i, final double n, int j) {
        final int n2 = 15;
        final int n3 = 20;
        final int n4 = 15;
        final int n5 = 256;
        final int n6 = 20;
        try {
            final int n7 = -n3 / 2;
            final int n8 = -n6 / 2;
            final int n9 = (int)(Math.sin(n) * 65536.0);
            final int n10 = (int)(Math.cos(n) * 65536.0);
            final int n11 = n9 * n5 >> 8;
            final int n12 = n10 * n5 >> 8;
            int n13 = (n4 << 16) + (n8 * n11 + n7 * n12);
            int n14 = (n2 << 16) + (n8 * n12 - n7 * n11);
            int n15 = j + i * DrawingArea.width;
            int n16;
            int n17;
            int n18;
            int n19;
            for (i = 0; i < n6; ++i) {
                n16 = n15;
                n17 = n13;
                n18 = n14;
                for (j = -n3; j < 0; ++j) {
                    n19 = this.myPixels[(n17 >> 16) + (n18 >> 16) * this.myWidth];
                    if (n19 != 0) {
                        DrawingArea.pixels[n16++] = n19;
                    }
                    else {
                        ++n16;
                    }
                    n17 += n12;
                    n18 -= n11;
                }
                n13 += n11;
                n14 += n12;
                n15 += DrawingArea.width;
            }
        }
        catch (Exception ex) {}
    }
    
    public void method354(final Background background, int topY, int topX) {
        topX += this.anInt1442;
        topY += this.anInt1443;
        int n = topX + topY * DrawingArea.width;
        int n2 = 0;
        int myHeight = this.myHeight;
        int myWidth = this.myWidth;
        int n3 = DrawingArea.width - myWidth;
        int n4 = 0;
        if (topY < DrawingArea.topY) {
            final int n5 = DrawingArea.topY - topY;
            myHeight -= n5;
            topY = DrawingArea.topY;
            n2 += n5 * myWidth;
            n += n5 * DrawingArea.width;
        }
        if (topY + myHeight > DrawingArea.bottomY) {
            myHeight -= topY + myHeight - DrawingArea.bottomY;
        }
        if (topX < DrawingArea.topX) {
            final int n6 = DrawingArea.topX - topX;
            myWidth -= n6;
            topX = DrawingArea.topX;
            n2 += n6;
            n += n6;
            n4 += n6;
            n3 += n6;
        }
        if (topX + myWidth > DrawingArea.bottomX) {
            final int n7 = topX + myWidth - DrawingArea.bottomX;
            myWidth -= n7;
            n4 += n7;
            n3 += n7;
        }
        if (myWidth > 0 && myHeight > 0) {
            this.method355(this.myPixels, myWidth, background.aByteArray1450, myHeight, DrawingArea.pixels, 0, n3, n, n4, n2);
        }
    }
    
    private void method355(final int[] array, int n, final byte[] array2, final int n2, final int[] array3, int n3, final int n4, int n5, final int n6, int n7) {
        final int n8 = -(n >> 2);
        n = -(n & 0x3);
        for (int i = -n2; i < 0; ++i) {
            for (int j = n8; j < 0; ++j) {
                n3 = array[n7++];
                if (n3 != 0 && array2[n5] == 0) {
                    array3[n5++] = n3;
                }
                else {
                    ++n5;
                }
                n3 = array[n7++];
                if (n3 != 0 && array2[n5] == 0) {
                    array3[n5++] = n3;
                }
                else {
                    ++n5;
                }
                n3 = array[n7++];
                if (n3 != 0 && array2[n5] == 0) {
                    array3[n5++] = n3;
                }
                else {
                    ++n5;
                }
                n3 = array[n7++];
                if (n3 != 0 && array2[n5] == 0) {
                    array3[n5++] = n3;
                }
                else {
                    ++n5;
                }
            }
            for (int k = n; k < 0; ++k) {
                n3 = array[n7++];
                if (n3 != 0 && array2[n5] == 0) {
                    array3[n5++] = n3;
                }
                else {
                    ++n5;
                }
            }
            n5 += n4;
            n7 += n6;
        }
    }
}

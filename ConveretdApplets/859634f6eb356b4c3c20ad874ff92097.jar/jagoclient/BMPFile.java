// 
// Decompiled by Procyon v0.5.30
// 

package jagoclient;

import java.awt.image.PixelGrabber;
import java.awt.Image;
import java.io.FileOutputStream;

public class BMPFile implements Runnable
{
    private static final int BITMAPFILEHEADER_SIZE = 14;
    private static final int BITMAPINFOHEADER_SIZE = 40;
    private byte[] bitmapFileHeader;
    private byte[] bfType;
    private int bfSize;
    private int bfReserved1;
    private int bfReserved2;
    private int bfOffBits;
    private byte[] bitmapInfoHeader;
    private int biSize;
    private int biWidth;
    private int biHeight;
    private int biPlanes;
    private int biBitCount;
    private int biCompression;
    private int biSizeImage;
    private int biXPelsPerMeter;
    private int biYPelsPerMeter;
    private int biClrUsed;
    private int biClrImportant;
    private int linepad;
    private int[] bitmap;
    private FileOutputStream fo;
    String parFilename;
    Image parImage;
    int parWidth;
    int parHeight;
    
    public BMPFile() {
        this.bitmapFileHeader = new byte[14];
        this.bfType = new byte[] { 66, 77 };
        this.bfOffBits = 54;
        this.bitmapInfoHeader = new byte[40];
        this.biSize = 40;
        this.biPlanes = 1;
        this.biBitCount = 24;
        this.biSizeImage = 196608;
    }
    
    public void saveBitmap(final String parFilename, final Image parImage, final int parWidth, final int parHeight) {
        this.parFilename = parFilename;
        this.parImage = parImage;
        this.parWidth = parWidth;
        this.parHeight = parHeight;
        final Thread thread = new Thread(this);
        thread.setPriority(thread.getPriority() - 1);
        thread.start();
    }
    
    public void run() {
        try {
            this.fo = new FileOutputStream(this.parFilename);
            this.save(this.parImage, this.parWidth, this.parHeight);
            this.fo.close();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    private void save(final Image image, final int n, final int n2) {
        try {
            this.convertImage(image, n, n2);
            this.writeBitmapFileHeader();
            this.writeBitmapInfoHeader();
            this.writeBitmap();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    private boolean convertImage(final Image image, final int biWidth, final int biHeight) {
        this.bitmap = new int[biWidth * biHeight];
        final PixelGrabber pixelGrabber = new PixelGrabber(image, 0, 0, biWidth, biHeight, this.bitmap, 0, biWidth);
        try {
            pixelGrabber.grabPixels();
        }
        catch (InterruptedException ex) {
            ex.printStackTrace();
            return false;
        }
        this.linepad = 4 - biWidth * 3 % 4;
        if (this.linepad == 4) {
            this.linepad = 0;
        }
        this.biSizeImage = (biWidth * 3 + this.linepad) * biHeight;
        this.bfSize = this.biSizeImage + 14 + 40;
        this.biWidth = biWidth;
        this.biHeight = biHeight;
        return true;
    }
    
    private void writeBitmap() {
        final byte[] array = new byte[3];
        try {
            for (int i = this.biHeight - 1; i >= 0; --i) {
                for (int j = 0; j < this.biWidth; ++j) {
                    final int n = this.bitmap[i * this.biWidth + j];
                    array[0] = (byte)(n & 0xFF);
                    array[1] = (byte)(n >> 8 & 0xFF);
                    array[2] = (byte)(n >> 16 & 0xFF);
                    this.fo.write(array);
                }
                for (int k = 0; k < this.linepad; ++k) {
                    this.fo.write(0);
                }
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    private void writeBitmapFileHeader() {
        try {
            this.fo.write(this.bfType);
            this.fo.write(this.intToDWord(this.bfSize));
            this.fo.write(this.intToWord(this.bfReserved1));
            this.fo.write(this.intToWord(this.bfReserved2));
            this.fo.write(this.intToDWord(this.bfOffBits));
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    private void writeBitmapInfoHeader() {
        try {
            this.fo.write(this.intToDWord(this.biSize));
            this.fo.write(this.intToDWord(this.biWidth));
            this.fo.write(this.intToDWord(this.biHeight));
            this.fo.write(this.intToWord(this.biPlanes));
            this.fo.write(this.intToWord(this.biBitCount));
            this.fo.write(this.intToDWord(this.biCompression));
            this.fo.write(this.intToDWord(this.biSizeImage));
            this.fo.write(this.intToDWord(this.biXPelsPerMeter));
            this.fo.write(this.intToDWord(this.biYPelsPerMeter));
            this.fo.write(this.intToDWord(this.biClrUsed));
            this.fo.write(this.intToDWord(this.biClrImportant));
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    private byte[] intToWord(final int n) {
        return new byte[] { (byte)(n & 0xFF), (byte)(n >> 8 & 0xFF) };
    }
    
    private byte[] intToDWord(final int n) {
        return new byte[] { (byte)(n & 0xFF), (byte)(n >> 8 & 0xFF), (byte)(n >> 16 & 0xFF), (byte)(n >> 24 & 0xFF) };
    }
}

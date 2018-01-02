// 
// Decompiled by Procyon v0.5.30
// 

package sexy.res;

import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.awt.Graphics;
import java.awt.Font;
import java.awt.Color;
import java.awt.image.ImageObserver;
import sexy.gui.SexyGraphics;
import java.net.URLConnection;
import java.io.InputStream;
import java.io.IOException;
import java.net.URL;
import java.awt.image.RGBImageFilter;
import java.util.Enumeration;
import java.awt.image.PixelGrabber;
import java.util.Hashtable;
import java.awt.Image;
import java.util.Vector;
import sexy.gui.SexyApplet;
import java.applet.AudioClip;
import sexy.gui.SexyImage;

public class ResLoader implements Runnable
{
    public SexyImage[] mImages;
    public AudioClip[] mSounds;
    public SexyApplet mApplet;
    public int mNumSoundsLoaded;
    public int[][] mImagePixels;
    public boolean mStarted;
    public boolean mLoaded;
    public boolean mImagesLoaded;
    public boolean mLoadingStarted;
    public boolean mLoadingFailed;
    public boolean mSlowFilter;
    public boolean mBrokenFilter;
    public boolean mSoundsLoaded;
    public boolean mShutdown;
    public String mResourceBase;
    Vector mImagesWaitingOn;
    Vector mErrorImages;
    public SexyImage[] mNumberImages;
    int mTotalResCount;
    int mLoadedResCount;
    public int mTotalCustomFilterCount;
    public int mProcessedCustomFilterCount;
    public Image mTestImage;
    long mResourceLoadStart;
    public Hashtable mAsyncImagesHash;
    static final double EPSILON = 1.0E-11;
    private static /* synthetic */ Class class$java$io$InputStream;
    
    public void ExtractImageNames(final String s, final String[] array) {
        String s2 = null;
        String s3 = null;
        String substring = "";
        String substring2 = s;
        final int lastIndex = substring2.lastIndexOf(47);
        if (lastIndex != -1) {
            substring = substring2.substring(0, lastIndex + 1);
            substring2 = substring2.substring(lastIndex + 1);
        }
        if (substring2.charAt(0) == '?') {
            s2 = substring + substring2.substring(1);
            s3 = substring + "_" + substring2.substring(1);
        }
        else if (substring2.charAt(0) == '_') {
            s3 = substring + substring2;
        }
        else {
            s2 = substring + substring2;
        }
        array[0] = s2;
        array[1] = s3;
    }
    
    public synchronized void Shutdown() {
        this.mShutdown = true;
        this.notifyAll();
        for (int i = 0; i < this.mNumSoundsLoaded; ++i) {
            this.mSounds[i].stop();
        }
    }
    
    public synchronized SexyImage AsyncGetImage(final String s, final String s2) {
        final SexyImage sexyImage = new SexyImage();
        Image getImage = null;
        Image getImage2 = null;
        if (s != null) {
            try {
                getImage = this.mApplet.GetImage(this.mResourceBase + s);
            }
            catch (Exception ex) {
                ex.printStackTrace();
                return null;
            }
            this.mAsyncImagesHash.put(getImage, sexyImage);
        }
        if (s2 != null) {
            try {
                getImage2 = this.mApplet.GetImage(this.mResourceBase + s2);
            }
            catch (Exception ex2) {
                ex2.printStackTrace();
                return null;
            }
            this.mAsyncImagesHash.put(getImage2, sexyImage);
        }
        sexyImage.Create(this, getImage, getImage2);
        return sexyImage;
    }
    
    public SexyImage AsyncGetImage(final String s) {
        final String[] array = new String[2];
        this.ExtractImageNames(s, array);
        return this.AsyncGetImage(array[0], array[1]);
    }
    
    public String[] GetSoundFileNames() {
        return new String[0];
    }
    
    public void SetResourceBase(final String mResourceBase) {
        this.mResourceBase = mResourceBase;
    }
    
    synchronized void ProcessAsyncImages() {
        Enumeration<SexyImage> enumeration = this.mAsyncImagesHash.elements();
        while (enumeration.hasMoreElements()) {
            final SexyImage sexyImage = enumeration.nextElement();
            if (sexyImage.mAWTColorImage != null && sexyImage.mAWTColorImageGrabber == null) {
                (sexyImage.mAWTColorImageGrabber = new PixelGrabber(sexyImage.mAWTColorImage, 0, 0, -1, -1, true)).startGrabbing();
            }
            if (sexyImage.mAWTAlphaImage != null && sexyImage.mAWTAlphaImageGrabber == null) {
                (sexyImage.mAWTAlphaImageGrabber = new PixelGrabber(sexyImage.mAWTAlphaImage, 0, 0, -1, -1, true)).startGrabbing();
            }
            if (sexyImage.mAWTColorImage != null && (sexyImage.mAWTColorImageGrabber.getStatus() & 0x40) != 0x0 && !sexyImage.mHasFailed) {
                final Image reGetImage = this.mApplet.ReGetImage(sexyImage.mAWTColorImage);
                if (reGetImage != null) {
                    sexyImage.mAWTColorImage = reGetImage;
                    sexyImage.mAWTColorImageGrabber = null;
                    continue;
                }
                System.out.println("Failed to load async image");
                sexyImage.mHasFailed = true;
            }
            if (sexyImage.mAWTAlphaImage != null && (sexyImage.mAWTAlphaImageGrabber.getStatus() & 0x40) != 0x0 && !sexyImage.mHasFailed) {
                final Image reGetImage2 = this.mApplet.ReGetImage(sexyImage.mAWTAlphaImage);
                if (reGetImage2 != null) {
                    sexyImage.mAWTAlphaImage = reGetImage2;
                    sexyImage.mAWTAlphaImageGrabber = null;
                    continue;
                }
                System.out.println("Failed to load async image");
                sexyImage.mHasFailed = true;
            }
            if ((sexyImage.mAWTColorImage == null || (sexyImage.mAWTColorImageGrabber.getStatus() & 0x30) != 0x0) && (sexyImage.mAWTAlphaImage == null || (sexyImage.mAWTAlphaImageGrabber.getStatus() & 0x30) != 0x0)) {
                int[] array = null;
                int[] array2 = null;
                int mWidth = 0;
                int mHeight = 0;
                if (sexyImage.mAWTColorImage != null) {
                    mWidth = sexyImage.mAWTColorImageGrabber.getWidth();
                    mHeight = sexyImage.mAWTColorImageGrabber.getHeight();
                    array = (int[])sexyImage.mAWTColorImageGrabber.getPixels();
                    this.mAsyncImagesHash.remove(sexyImage.mAWTColorImage);
                    enumeration = this.mAsyncImagesHash.elements();
                    sexyImage.mAWTColorImage.flush();
                    sexyImage.mAWTColorImage = null;
                }
                if (sexyImage.mAWTAlphaImage != null) {
                    mWidth = sexyImage.mAWTAlphaImageGrabber.getWidth();
                    mHeight = sexyImage.mAWTAlphaImageGrabber.getHeight();
                    array2 = (int[])sexyImage.mAWTAlphaImageGrabber.getPixels();
                    this.mAsyncImagesHash.remove(sexyImage.mAWTAlphaImage);
                    enumeration = this.mAsyncImagesHash.elements();
                    sexyImage.mAWTAlphaImage.flush();
                    sexyImage.mAWTAlphaImage = null;
                }
                final int[] mBits = new int[mWidth * mHeight];
                if (array != null) {
                    if (array2 != null) {
                        for (int i = 0; i < mBits.length; ++i) {
                            mBits[i] = (array2[i] << 24 | (array[i] & 0xFFFFFF));
                        }
                    }
                    else {
                        for (int j = 0; j < mBits.length; ++j) {
                            mBits[j] = array[j];
                        }
                    }
                }
                else if (array2 != null) {
                    for (int k = 0; k < mBits.length; ++k) {
                        mBits[k] = (array2[k] << 24 | 0xFFFFFF);
                    }
                }
                synchronized (this) {
                    sexyImage.mBits = mBits;
                    sexyImage.mWidth = mWidth;
                    sexyImage.mHeight = mHeight;
                    sexyImage.BitsChanged();
                }
            }
        }
    }
    
    public SexyImage ScaleImage(final SexyImage sexyImage, final double n) {
        return new SexyImage(this.ScalePixels(sexyImage.GetBits(), sexyImage.GetWidth(), sexyImage.GetHeight(), n), sexyImage.GetWidth(), sexyImage.GetHeight());
    }
    
    public int[] Mirror(final int[] array, final int n, final int n2) {
        final int[] array2 = new int[n * n2];
        for (int i = 0; i < n2; ++i) {
            for (int j = 0; j < n; ++j) {
                array2[i * n + j] = array[i * n + (n - j - 1)];
            }
        }
        return array2;
    }
    
    public int[] FilterPixels(final int[] array, final int n, final int n2, final RGBImageFilter rgbImageFilter) {
        final int[] array2 = new int[array.length];
        for (int i = 0; i < n2; ++i) {
            for (int j = 0; j < n; ++j) {
                array2[i * n + j] = rgbImageFilter.filterRGB(j, i, array[i * n + j]);
            }
        }
        return array2;
    }
    
    public void DrawToPixels(final int[] array, final int n, final int n2, final int n3, final int n4, final int[] array2, final int n5, final int n6) {
        for (int i = 0; i < n6; ++i) {
            for (int j = 0; j < n5; ++j) {
                final int n7 = array2[i * n5 + j];
                if ((n7 >> 24 & 0xFF) >= 128) {
                    array[(n2 + i) * n3 + n + j] = n7;
                }
            }
        }
    }
    
    public int[] AlphaBlendPixels(final int[] array, final int[] array2, final int[] array3, final int n, final int n2) {
        final int[] array4 = new int[n * n2];
        for (int i = 0; i < array2.length; ++i) {
            if (array3[i] >= 248) {
                array4[i] = array2[i];
            }
            else if (array3[i] > 8) {
                final int n3 = array2[i];
                final int n4 = array[i];
                final int n5 = array3[i];
                array4[i] = (0xFF000000 | ((char)(n3 >> 16 & 0xFF) * (char)n5 >> 8) + ((char)(n4 >> 16 & 0xFF) * (char)(255 - n5) >> 8) << 16 | ((char)(n3 >> 8 & 0xFF) * (char)n5 >> 8) + ((char)(n4 >> 8 & 0xFF) * (char)(255 - n5) >> 8) << 8 | ((char)(n3 & 0xFF) * (char)n5 >> 8) + ((char)(n4 & 0xFF) * (char)(255 - n5) >> 8));
            }
            else {
                array4[i] = array[i];
            }
        }
        return array4;
    }
    
    public double[] PixelToHSL(final int[] array) {
        final double[] array2 = new double[array.length * 3];
        for (int i = 0; i < array.length; ++i) {
            final double n = (array[i] >> 16 & 0xFF) / 255.0;
            final double n2 = (array[i] >> 8 & 0xFF) / 255.0;
            final double n3 = (array[i] & 0xFF) / 255.0;
            final double max = Math.max(n, Math.max(n2, n3));
            final double min = Math.min(n, Math.min(n2, n3));
            double n4 = -1.0;
            double n5 = 0.0;
            final double n6 = 0.50000000001 * (min + max);
            final double n7 = max - min;
            if (n7 != 0.0) {
                n5 = n7 / ((n6 <= 0.5) ? (min + max) : (2.0 - max - min));
                double n8;
                if (n == max) {
                    n8 = ((n2 == min) ? (5.0 + (max - n3) / n7) : (1.0 - (max - n2) / n7));
                }
                else if (n2 == max) {
                    n8 = ((n3 == min) ? (1.0 + (max - n) / n7) : (3.0 - (max - n3) / n7));
                }
                else {
                    n8 = ((n == min) ? (3.0 + (max - n2) / n7) : (5.0 - (max - n) / n7));
                }
                n4 = n8 / 6.0;
            }
            array2[i * 3] = n4;
            array2[i * 3 + 1] = n5;
            array2[i * 3 + 2] = n6;
        }
        return array2;
    }
    
    public byte[] GetDataFile(final String s, final boolean b) {
        int n = 0;
        boolean b2 = false;
        byte[] array = null;
        while (!b2) {
            array = null;
            InputStream inputStream = null;
            try {
                final URL url = new URL(this.mApplet.getCodeBase(), this.mResourceBase + s);
                URLConnection openConnection = null;
                synchronized (this) {
                    openConnection = url.openConnection();
                    inputStream = openConnection.getInputStream();
                }
                final int contentLength = openConnection.getContentLength();
                byte[] array2 = new byte[8192];
                int n2 = 8192;
                int n3 = 0;
                while (true) {
                    final int read = inputStream.read();
                    if (read == -1) {
                        break;
                    }
                    array2[n3++] = (byte)read;
                    if (n3 < n2) {
                        continue;
                    }
                    final byte[] array3 = array2;
                    n2 *= 2;
                    array2 = new byte[n2];
                    System.arraycopy(array3, 0, array2, 0, n3);
                }
                final byte[] array4 = array2;
                final byte[] array5 = new byte[n3];
                System.arraycopy(array4, 0, array5, 0, n3);
                array = array5;
                if (array.length < contentLength) {
                    System.out.println("Length mismatch on " + s + ": " + array.length + " < " + contentLength);
                    throw new IOException("Partial Transfer");
                }
                b2 = true;
            }
            catch (Exception ex) {
                System.out.println("Failed loading text file: " + s);
                ex.printStackTrace();
                if (++n == 3 && b) {
                    this.mApplet.FatalError("loading", "text file: " + s);
                    try {
                        if (inputStream != null) {
                            inputStream.close();
                        }
                    }
                    catch (Exception ex2) {}
                    return null;
                }
            }
            try {
                if (inputStream == null) {
                    continue;
                }
                inputStream.close();
            }
            catch (Exception ex3) {}
        }
        return array;
    }
    
    public String GetTextFile(final String s, final boolean b) {
        final byte[] getDataFile = this.GetDataFile(s, b);
        if (getDataFile == null) {
            return null;
        }
        return new String(getDataFile);
    }
    
    public String GetTextFile(final String s) {
        return this.GetTextFile(s, true);
    }
    
    public SexyImage[] CopyImages(final Image[] array, final boolean b, final int[][] array2) {
        final int length = array.length;
        final SexyImage[] array3 = new SexyImage[length];
        final PixelGrabber[] array4 = new PixelGrabber[length];
        for (int i = 0; i < length; ++i) {
            if (array[i] != null) {
                (array4[i] = new PixelGrabber(array[i], 0, 0, -1, -1, true)).startGrabbing();
            }
        }
        for (int j = 0; j < length; ++j) {
            if (array4[j] != null) {
                if (b && this.mShutdown) {
                    return null;
                }
                try {
                    array4[j].grabPixels();
                    if ((array4[j].getStatus() & 0x80) != 0x0) {
                        final Image reGetImage = this.mApplet.ReGetImage(array[j]);
                        if (reGetImage == null) {
                            if (b) {
                                this.mApplet.WriteDebug("Failed to load image " + this.GetImageFileNames()[j]);
                            }
                            else {
                                System.err.println("CopyImages Failed to copy image #" + j);
                            }
                            return null;
                        }
                        array[j] = reGetImage;
                        (array4[j] = new PixelGrabber(array[j], 0, 0, -1, -1, true)).startGrabbing();
                        --j;
                    }
                    else {
                        final int[] array5 = (int[])array4[j].getPixels();
                        if (array2 != null) {
                            array2[j] = array5;
                        }
                        array3[j] = new SexyImage(array5, array4[j].getWidth(), array4[j].getHeight());
                        if (b) {
                            ++this.mLoadedResCount;
                        }
                    }
                }
                catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
        return array3;
    }
    
    public SexyImage FilterImage(final SexyImage sexyImage, final RGBImageFilter rgbImageFilter) {
        final SexyImage sexyImage2 = new SexyImage();
        sexyImage2.Create(sexyImage.mWidth, sexyImage.mHeight);
        new SexyGraphics(sexyImage2).DrawImage(sexyImage, 0, 0);
        if (rgbImageFilter != null) {
            for (int i = 0; i < sexyImage2.mHeight; ++i) {
                for (int j = 0; j < sexyImage2.mWidth; ++j) {
                    sexyImage2.mBits[j + i * sexyImage2.mWidth] = rgbImageFilter.filterRGB(j, i, sexyImage2.mBits[j + i * sexyImage2.mWidth]);
                }
            }
        }
        return sexyImage2;
    }
    
    public String[] GetImageFileNames() {
        return new String[0];
    }
    
    public int[] CombineTrans(final int[] array, final int[] array2, final int n, final int n2) {
        final int[] array3 = new int[n * n2];
        for (int i = 0; i < array3.length; ++i) {
            if ((array2[i] >> 24 & 0xFF) < 128) {
                array3[i] = array[i];
            }
            else {
                array3[i] = array2[i];
            }
        }
        return array3;
    }
    
    public int[] GetPixels(final SexyImage sexyImage) {
        return sexyImage.GetBits();
    }
    
    public int[] GetPixels(final Image image) {
        int[] array = null;
        try {
            final PixelGrabber pixelGrabber = new PixelGrabber(image, 0, 0, image.getWidth(null), image.getHeight(null), true);
            pixelGrabber.grabPixels();
            array = (int[])pixelGrabber.getPixels();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return array;
    }
    
    public int[] GetPixels(final SexyImage sexyImage, final int n, final int n2, final int n3, final int n4) {
        final int[] getPixels = this.GetPixels(sexyImage);
        final int mWidth = sexyImage.mWidth;
        final int[] array = new int[n3 * n4];
        for (int i = 0; i < n4; ++i) {
            System.arraycopy(getPixels, n + (i + n2) * mWidth, array, i * n3, n3);
        }
        return array;
    }
    
    public int[] GetAlphaPixels(final SexyImage sexyImage) {
        final int[] getBits = sexyImage.GetBits();
        final int[] array = new int[getBits.length];
        for (int i = 0; i < getBits.length; ++i) {
            array[i] = (getBits[i] & 0xFF);
        }
        return array;
    }
    
    public int[] AddPixels(final int[] array, final int[] array2, final int n, final int n2) {
        final int[] array3 = new int[n * n2];
        for (int i = 0; i < array3.length; ++i) {
            int n3 = (array[i] >> 1 & 0x7F7F7F7F) + (array2[i] >> 1 & 0x7F7F7F7F);
            if ((n3 & 0x80) != 0x0) {
                n3 = ((n3 & 0xFFFFFF00) | 0x7F);
            }
            if ((n3 & 0x8000) != 0x0) {
                n3 = ((n3 & 0xFFFF00FF) | 0x7F00);
            }
            if ((n3 & 0x800000) != 0x0) {
                n3 = ((n3 & 0xFF00FFFF) | 0x7F0000);
            }
            array3[i] = (n3 << 1 | 0xFF000000);
        }
        return array3;
    }
    
    public int[] ScalePixels(final int[] array, final int n, final int n2, final double n3) {
        final int[] array2 = new int[n * n2];
        final float n4 = (float)n3;
        if (n3 >= 1.0) {
            final float n5 = n / 2 - n / n4 / 2.0f;
            float n6 = n2 / 2 - n2 / n4 / 2.0f;
            final float n7 = 1.0f / n4;
            for (int i = 0; i < n2; ++i) {
                float n8 = n5;
                for (int j = 0; j < n; ++j) {
                    final int[][][] array3 = new int[2][2][4];
                    int n9 = 0;
                    do {
                        int n10 = 0;
                        do {
                            final int n11 = (int)n8 + n10 - 1;
                            final int n12 = (int)n6 + n9 - 1;
                            if (n11 >= 0 && n11 < n && n12 >= 0 && n12 < n2) {
                                final int n13 = array[n12 * n + n11];
                                array3[n9][n10][0] = (n13 & 0xFF);
                                array3[n9][n10][1] = (n13 >> 8 & 0xFF);
                                array3[n9][n10][2] = (n13 >> 16 & 0xFF);
                                array3[n9][n10][3] = (n13 >> 24 & 0xFF);
                            }
                            else {
                                array3[n9][n10][0] = 0;
                                array3[n9][n10][1] = 0;
                                array3[n9][n10][2] = 0;
                                array3[n9][n10][3] = 0;
                            }
                        } while (++n10 < 2);
                    } while (++n9 < 2);
                    final int[][] array4 = new int[2][4];
                    final float n14 = n8 - (int)n8;
                    int n15 = 0;
                    do {
                        int n16 = 0;
                        do {
                            array4[n15][n16] = (int)(array3[n15][0][n16] * (1.0 - n14) + array3[n15][1][n16] * n14);
                        } while (++n16 < 4);
                    } while (++n15 < 2);
                    final int[] array5 = new int[4];
                    final float n17 = n6 - (int)n6;
                    int n18 = 0;
                    do {
                        array5[n18] = (int)(array4[0][n18] * (1.0 - n17) + array4[1][n18] * n17);
                    } while (++n18 < 4);
                    array2[i * n + j] = (array5[0] | array5[1] << 8 | array5[2] << 16 | array5[3] << 24);
                    n8 += n7;
                }
                n6 += n7;
            }
        }
        else {
            final float[][] array6 = new float[n * n2][4];
            for (int k = 0; k < n2; ++k) {
                for (int l = 0; l < n; ++l) {
                    final float n19 = n4;
                    final float n20 = 1.0f / n19;
                    final float n21 = (n - n * n4) / 2.0f + l * n4;
                    final float n22 = (n2 - n2 * n4) / 2.0f + k * n4;
                    int n23 = 0;
                    do {
                        int n24 = 0;
                        do {
                            float n25 = n4 * n4;
                            final int n26 = (int)(n21 + n24 * n19);
                            final int n27 = (int)(n22 + n23 * n19);
                            if (n24 == 0) {
                                final float n28 = ((int)n21 + 1 - n21) * n20;
                                if (n28 < 1.0) {
                                    n25 *= n28;
                                }
                            }
                            else {
                                final float n29 = (n21 + n19 - ((int)n21 + 1)) * n20;
                                if (n29 < 0.0) {
                                    n25 = 0.0f;
                                }
                                else if (n29 < 1.0) {
                                    n25 *= n29;
                                }
                            }
                            if (n23 == 0) {
                                final float n30 = ((int)n22 + 1 - n22) * n20;
                                if (n30 < 1.0) {
                                    n25 *= n30;
                                }
                            }
                            else {
                                final float n31 = (n22 + n19 - ((int)n22 + 1)) * n20;
                                if (n31 < 0.0) {
                                    n25 = 0.0f;
                                }
                                else if (n31 < 1.0) {
                                    n25 *= n31;
                                }
                            }
                            if (n26 >= 0 && n26 < n && n27 >= 0 && n27 < n2) {
                                int n32 = 0;
                                do {
                                    final float[] array7 = array6[n27 * n + n26];
                                    final int n33 = n32;
                                    array7[n33] += (array[k * n + l] >> n32 * 8 & 0xFF) * n25;
                                } while (++n32 < 4);
                            }
                        } while (++n24 < 2);
                    } while (++n23 < 2);
                }
            }
            for (int n34 = 0; n34 < n2; ++n34) {
                for (int n35 = 0; n35 < n; ++n35) {
                    int n36 = 0;
                    do {
                        final int[] array8 = array2;
                        final int n37 = n34 * n + n35;
                        array8[n37] |= Math.min(Math.round(array6[n34 * n + n35][n36]), 255) << n36 * 8;
                    } while (++n36 < 4);
                }
            }
        }
        return array2;
    }
    
    public int[] HSLToPixel(final double[] array) {
        final int[] array2 = new int[array.length];
        for (int i = 0; i < array.length / 3; ++i) {
            final double n = array[i * 3];
            final double n2 = array[i * 3 + 1];
            final double n3 = array[i * 3 + 2];
            final double n4 = (n3 <= 0.5) ? (n3 * (1.0 + n2)) : (n3 + n2 - n3 * n2);
            int n5;
            int n6;
            int n7;
            if (n2 == 0.0 || n == -1.0) {
                n5 = (int)(255.0 * n3 + 0.5);
                n6 = (int)(255.0 * n3 + 0.5);
                n7 = (int)(255.0 * n3 + 0.5);
            }
            else {
                final double n8 = 2.0 * n3 - n4;
                final double n9 = n8 + (n4 - n8) * (6.0 * n - Math.floor(6.0 * n));
                final double n10 = n4 - (n4 - n8) * (6.0 * n - Math.floor(6.0 * n));
                double n11 = 0.0;
                double n12 = 0.0;
                double n13 = 0.0;
                switch ((int)(6.0 * n)) {
                    case 0: {
                        n11 = n4;
                        n12 = n9;
                        n13 = n8;
                        break;
                    }
                    case 1: {
                        n11 = n10;
                        n12 = n4;
                        n13 = n8;
                        break;
                    }
                    case 2: {
                        n11 = n8;
                        n12 = n4;
                        n13 = n9;
                        break;
                    }
                    case 3: {
                        n11 = n8;
                        n12 = n10;
                        n13 = n4;
                        break;
                    }
                    case 4: {
                        n11 = n9;
                        n12 = n8;
                        n13 = n4;
                        break;
                    }
                    case 5: {
                        n11 = n4;
                        n12 = n8;
                        n13 = n10;
                        break;
                    }
                    default: {
                        n11 = n4;
                        n12 = n9;
                        n13 = n8;
                        break;
                    }
                }
                n5 = (int)(255.0 * n11 + 0.5);
                n6 = (int)(255.0 * n12 + 0.5);
                n7 = (int)(255.0 * n13 + 0.5);
                if (n5 > 255) {
                    n5 = 255;
                }
                if (n6 > 255) {
                    n6 = 255;
                }
                if (n7 > 255) {
                    n7 = 255;
                }
            }
            array2[i] = (0xFF000000 | n5 << 16 | n6 << 8 | n7);
        }
        return array2;
    }
    
    public SexyImage CreateImageWithAlpha(final SexyImage sexyImage, final SexyImage sexyImage2) {
        SexyImage sexyImage3 = null;
        if (sexyImage != null && sexyImage2 != null) {
            final int getWidth = sexyImage.GetWidth();
            final int getHeight = sexyImage.GetHeight();
            if (getWidth != sexyImage2.GetWidth() || getHeight != sexyImage2.GetHeight()) {
                return null;
            }
            sexyImage3 = new SexyImage();
            sexyImage3.Create(getWidth, getHeight);
            final int[] getBits = sexyImage.GetBits();
            final int[] getBits2 = sexyImage2.GetBits();
            final int[] getBits3 = sexyImage3.GetBits();
            for (int n = getWidth * getHeight, i = 0; i < n; ++i) {
                getBits3[i] = (getBits2[i] << 24 | (getBits[i] & 0xFFFFFF));
            }
            sexyImage3.BitsChanged();
        }
        else if (sexyImage != null) {
            final int getWidth2 = sexyImage.GetWidth();
            final int getHeight2 = sexyImage.GetHeight();
            sexyImage3 = new SexyImage();
            sexyImage3.Create(getWidth2, getHeight2);
            final int[] getBits4 = sexyImage.GetBits();
            final int[] getBits5 = sexyImage3.GetBits();
            for (int n2 = getWidth2 * getHeight2, j = 0; j < n2; ++j) {
                getBits5[j] = getBits4[j];
            }
            sexyImage3.BitsChanged();
        }
        else if (sexyImage2 != null) {
            final int getWidth3 = sexyImage2.GetWidth();
            final int getHeight3 = sexyImage2.GetHeight();
            sexyImage3 = new SexyImage();
            sexyImage3.Create(getWidth3, getHeight3);
            final int[] getBits6 = sexyImage2.GetBits();
            final int[] getBits7 = sexyImage3.GetBits();
            for (int n3 = getWidth3 * getHeight3, k = 0; k < n3; ++k) {
                getBits7[k] = (getBits6[k] << 24 | 0xFFFFFF);
            }
            sexyImage3.BitsChanged();
        }
        return sexyImage3;
    }
    
    public void MakeNumberImages() {
        final Font createSysFont = this.mApplet.CreateSysFont("Serif", 1, 16);
        final Image image = this.mApplet.createImage(20, 20);
        final int n = 14;
        final int n2 = 20;
        int n3 = 0;
        do {
            final Graphics graphics = image.getGraphics();
            graphics.setColor(new Color(0, 0, 0));
            graphics.fillRect(0, 0, 20, 20);
            final int n4 = 2;
            final int n5 = 15;
            final String string = "" + n3;
            graphics.setFont(createSysFont);
            graphics.setColor(new Color(128, 128, 128));
            int n6 = -2;
            do {
                int n7 = -2;
                do {
                    graphics.drawString(string, n4 + n6, n5 + n7);
                } while (++n7 <= 2);
            } while (++n6 <= 2);
            graphics.setColor(new Color(255, 255, 255));
            graphics.drawString(string, n4, n5);
            graphics.dispose();
            final PixelGrabber pixelGrabber = new PixelGrabber(image, 0, 0, n, n2, true);
            try {
                pixelGrabber.grabPixels();
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
            final Color color = new Color(0, 0, 0);
            final Color color2 = new Color(128, 255, 128);
            final int[] array = (int[])pixelGrabber.getPixels();
            (this.mNumberImages[n3] = new SexyImage()).Create(n, n2);
            final int[] getBits = this.mNumberImages[n3].GetBits();
            for (int i = 0; i < array.length; ++i) {
                double n8 = (Math.max(array[i] & 0xFF, array[i] >> 8 & 0xFF) - 128) / 127.0;
                if (n8 < -0.5) {
                    array[i] = 0;
                }
                else {
                    if (n8 < 0.0) {
                        n8 = 0.0;
                    }
                    getBits[i] = new Color((int)(n8 * color2.getRed() + (1.0 - n8) * color.getRed()), (int)(n8 * color2.getGreen() + (1.0 - n8) * color.getGreen()), (int)(n8 * color2.getBlue() + (1.0 - n8) * color.getBlue())).getRGB();
                }
            }
            this.mNumberImages[n3].BitsChanged();
        } while (++n3 < 10);
    }
    
    public void ReadGifZipImages(final String s) {
        int n = 0;
        final int mLoadedResCount = this.mLoadedResCount;
        InputStream inputStream = null;
        final String[] getImageFileNames = this.GetImageFileNames();
        final SexyImage[] array = new SexyImage[getImageFileNames.length];
        while (true) {
            try {
                final URL url = new URL(this.mApplet.getCodeBase(), s);
                synchronized (this) {
                    inputStream = url.openConnection().getInputStream();
                }
                final ZipInputStream zipInputStream = new ZipInputStream(inputStream);
                for (ZipEntry zipEntry = zipInputStream.getNextEntry(); zipEntry != null; zipEntry = zipInputStream.getNextEntry()) {
                    final Class<?> forName = Class.forName("sexy.gif.gifReader");
                    final Object instance = forName.getConstructor((ResLoader.class$java$io$InputStream != null) ? ResLoader.class$java$io$InputStream : (ResLoader.class$java$io$InputStream = class$("java.io.InputStream"))).newInstance(zipInputStream);
                    forName.getMethod("readAll", (Class[])null).invoke(instance, (Object[])null);
                    final SexyImage sexyImage = (SexyImage)Class.forName("sexy.gif.gifImage").getMethod("frame", (Class<?>[])null).invoke(forName.getMethod("getFrame", Integer.TYPE).invoke(instance, new Integer(0)), (Object[])null);
                    boolean b = false;
                    for (int i = 0; i < getImageFileNames.length; ++i) {
                        final String[] array2 = new String[2];
                        this.ExtractImageNames(getImageFileNames[i], array2);
                        if (array2[0] != null && array2[0].equals(zipEntry.getName())) {
                            this.mImages[i] = sexyImage;
                            b = true;
                        }
                        else if (array2[1] != null && array2[1].equals(zipEntry.getName())) {
                            array[i] = sexyImage;
                            b = true;
                        }
                    }
                    if (b) {
                        ++this.mLoadedResCount;
                    }
                    else {
                        System.out.println("WARNING: Unreferenced zip image '" + zipEntry.getName() + "'");
                    }
                }
            }
            catch (Exception ex) {
                ex.printStackTrace();
                System.out.println("Failed to load gif zip: " + s);
                if (++n >= 3) {
                    this.mApplet.FatalError("loading", "gif zip fail");
                    return;
                }
                this.mLoadedResCount = mLoadedResCount;
                continue;
            }
            break;
        }
        for (int j = 0; j < getImageFileNames.length; ++j) {
            if (array[j] != null) {
                final int[] getBits = array[j].GetBits();
                if (this.mImages[j] != null) {
                    final int[] getBits2 = this.mImages[j].GetBits();
                    for (int k = 0; k < getBits2.length; ++k) {
                        getBits2[k] = ((getBits2[k] & 0xFFFFFF) | (getBits[k] & 0xFF) << 24);
                    }
                }
                else {
                    this.mImages[j] = array[j];
                    for (int l = 0; l < getBits.length; ++l) {
                        getBits[l] = (0xFFFFFF | (getBits[l] & 0xFF) << 24);
                    }
                }
                this.mImages[j].BitsChanged();
            }
        }
        try {
            if (inputStream != null) {
                inputStream.close();
            }
        }
        catch (Exception ex2) {}
    }
    
    public void PostFiltering() {
    }
    
    public ResLoader(final SexyApplet mApplet) {
        this.mResourceBase = "";
        this.mImagesWaitingOn = new Vector();
        this.mErrorImages = new Vector();
        this.mNumberImages = new SexyImage[10];
        this.mAsyncImagesHash = new Hashtable();
        this.mApplet = mApplet;
        this.mTestImage = this.mApplet.createImage(1, 1);
        new Thread(this).start();
    }
    
    public void DoSoundLoading() {
        final String[] getSoundFileNames = this.GetSoundFileNames();
        for (int n = 0; n < this.GetOptionalSoundStart() && !this.mShutdown; ++n) {
            try {
                this.mSounds[n] = this.mApplet.getAudioClip(this.mApplet.getCodeBase(), this.mResourceBase + "sounds/" + getSoundFileNames[n]);
                if (n == 0) {
                    if (System.getProperty("os.name").startsWith("Win")) {
                        this.mSounds[n].loop();
                    }
                }
                else {
                    this.mSounds[n].play();
                    this.mSounds[n].stop();
                }
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
            ++this.mNumSoundsLoaded;
            ++this.mLoadedResCount;
        }
        this.mSoundsLoaded = true;
        if (this.mImagesLoaded && this.mSoundsLoaded) {
            System.out.println("Resource loading time: " + (System.currentTimeMillis() - this.mResourceLoadStart) + "ms");
        }
        while (true) {
            Label_0285: {
                synchronized (this) {
                    this.notifyAll();
                    break Label_0285;
                }
                synchronized (this) {
                    Label_0264: {
                        try {
                            this.wait();
                            break Label_0264;
                        }
                        catch (Exception ex3) {
                        }
                        // monitorexit(this)
                    }
                }
            }
            if (this.mImagesLoaded || this.mShutdown) {
                for (int getOptionalSoundStart = this.GetOptionalSoundStart(); getOptionalSoundStart < getSoundFileNames.length && !this.mShutdown; ++getOptionalSoundStart) {
                    try {
                        synchronized (this) {
                            this.mSounds[getOptionalSoundStart] = this.mApplet.getAudioClip(this.mApplet.getCodeBase(), this.mResourceBase + "sounds/" + getSoundFileNames[getOptionalSoundStart]);
                        }
                        if (getOptionalSoundStart == 0) {
                            if (System.getProperty("os.name").startsWith("Win")) {
                                this.mSounds[getOptionalSoundStart].loop();
                            }
                        }
                        else {
                            this.mSounds[getOptionalSoundStart].play();
                            this.mSounds[getOptionalSoundStart].stop();
                        }
                    }
                    catch (Exception ex2) {
                        ex2.printStackTrace();
                    }
                    ++this.mNumSoundsLoaded;
                    ++this.mLoadedResCount;
                }
                return;
            }
            continue;
        }
    }
    
    public int GetOptionalSoundStart() {
        return this.GetSoundFileNames().length;
    }
    
    boolean BrokenFilter() {
        final Graphics graphics = this.mTestImage.getGraphics();
        graphics.setColor(new Color(255, 0, 0));
        graphics.fillRect(0, 0, 1, 1);
        graphics.dispose();
        final int[] array = { 0 };
        final PixelGrabber pixelGrabber = new PixelGrabber(this.mTestImage, 0, 0, 1, 1, array, 0, 1);
        try {
            pixelGrabber.grabPixels();
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return true;
        }
        return (array[0] >> 24 & 0xFF) != 0xFF || (array[0] >> 16 & 0xFF) < 248 || (array[0] >> 8 & 0xFF) != 0x0 || (array[0] & 0xFF) != 0x0;
    }
    
    public int GetSlowFilterTime() {
        return 3500;
    }
    
    public int[] RotateClockwise(final int[] array, final int n, final int n2) {
        final int[] array2 = new int[n * n2];
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n2; ++j) {
                array2[i * n2 + j] = array[(n2 - j - 1) * n + i];
            }
        }
        return array2;
    }
    
    public void CustomFiltering() {
    }
    
    public int[] CreateTransPixels(final int[] array, final int[] array2, final int n, final int n2) {
        final int n3 = n * n2;
        final int[] array3 = new int[n3];
        for (int i = 0; i < n3; ++i) {
            array3[i] = (array2[i] << 24 | (array[i] & 0xFFFFFF));
        }
        return array3;
    }
    
    public int[] CopyAreaPixels(final int[] array, final int n, final int n2, final int n3, final int n4, final int n5, final int n6) {
        final int[] array2 = new int[n5 * n6];
        for (int i = 0; i < n6; ++i) {
            for (int j = 0; j < n5; ++j) {
                array2[i * n5 + j] = array[(n4 + i) * n + n3 + j];
            }
        }
        return array2;
    }
    
    public SexyImage CopyAndFilter(final SexyImage sexyImage, final int n, final int n2, final int n3, final int n4, final RGBImageFilter rgbImageFilter) {
        final SexyImage sexyImage2 = new SexyImage();
        sexyImage2.Create(n3, n4);
        new SexyGraphics(sexyImage2).DrawImage(sexyImage, -n, -n2);
        if (rgbImageFilter != null) {
            for (int i = 0; i < sexyImage2.mHeight; ++i) {
                for (int j = 0; j < sexyImage2.mWidth; ++j) {
                    sexyImage2.mBits[j + i * sexyImage2.mWidth] = rgbImageFilter.filterRGB(j, i, sexyImage2.mBits[j + i * sexyImage2.mWidth]);
                }
            }
        }
        return sexyImage2;
    }
    
    private static /* synthetic */ Class class$(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
    
    public double GetCustomFilteringPortion() {
        return 0.0;
    }
    
    protected void finalize() throws Throwable {
        System.out.println("Resources finalized");
    }
    
    public void run() {
        while (!this.mStarted) {
            if (this.mShutdown) {
                return;
            }
            this.ProcessAsyncImages();
            synchronized (this) {
                Label_0031: {
                    try {
                        this.wait(50L);
                        break Label_0031;
                    }
                    catch (Exception ex3) {
                    }
                    // monitorexit(this)
                }
            }
        }
        if (this.mLoadingStarted) {
            this.DoSoundLoading();
            return;
        }
        this.mLoadingStarted = true;
        final String[] getImageFileNames = this.GetImageFileNames();
        this.GetSoundFileNames();
        try {
            Thread.currentThread().setPriority(1);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        try {
            this.mBrokenFilter = this.BrokenFilter();
            this.mTotalResCount = this.GetOptionalSoundStart() + getImageFileNames.length;
            if (this.mApplet.mDebug) {
                System.out.print("GIFS: ");
                for (int i = 0; i < getImageFileNames.length; ++i) {
                    if (getImageFileNames[i].endsWith(".gif")) {
                        System.out.print(getImageFileNames[i] + " ");
                    }
                }
                System.out.println();
                System.out.print("NON-GIFS: ");
                for (int j = 0; j < getImageFileNames.length; ++j) {
                    if (!getImageFileNames[j].endsWith(".gif")) {
                        System.out.print(getImageFileNames[j] + " ");
                    }
                }
                System.out.println();
            }
            for (int k = 0; k < getImageFileNames.length; ++k) {
                final String[] array = new String[2];
                this.ExtractImageNames(getImageFileNames[k], array);
                if (array[0] != null && array[1] != null) {
                    ++this.mTotalResCount;
                }
            }
            this.mLoadedResCount = 0;
            this.mResourceLoadStart = System.currentTimeMillis();
            new Thread(this).start();
            final Image[] array2 = new Image[getImageFileNames.length];
            final Image[] array3 = new Image[getImageFileNames.length];
            this.mImages = new SexyImage[getImageFileNames.length];
            final String parameter = this.mApplet.getParameter("RequiredGifZip");
            if (parameter != null) {
                this.ReadGifZipImages(parameter);
            }
            for (int l = 0; l < getImageFileNames.length; ++l) {
                if (this.mImages[l] == null) {
                    final String s = getImageFileNames[l];
                    final String[] array4 = new String[2];
                    this.ExtractImageNames(this.mResourceBase + "images/" + s, array4);
                    if (array4[0] != null) {
                        array2[l] = this.mApplet.GetImage(array4[0]);
                    }
                    if (array4[1] != null) {
                        array3[l] = this.mApplet.GetImage(array4[1]);
                    }
                }
            }
            this.mImagePixels = new int[array2.length][];
            final SexyImage[] copyImages = this.CopyImages(array2, true, this.mImagePixels);
            if (copyImages == null) {
                this.mApplet.FatalError("loading");
                this.mLoadingFailed = true;
                return;
            }
            for (int n = 0; n < copyImages.length; ++n) {
                if (copyImages[n] != null) {
                    this.mImages[n] = copyImages[n];
                }
            }
            final SexyImage[] copyImages2 = this.CopyImages(array3, true, new int[array3.length][]);
            if (copyImages2 == null) {
                this.mApplet.FatalError("loading");
                this.mLoadingFailed = true;
                return;
            }
            for (int n2 = 0; n2 < getImageFileNames.length; ++n2) {
                if (copyImages2[n2] != null) {
                    if (this.mImages[n2] == null) {
                        final int[] getBits = copyImages2[n2].GetBits();
                        for (int n3 = 0; n3 < getBits.length; ++n3) {
                            getBits[n3] = ((getBits[n3] & 0xFF) << 24 | 0xFFFFFF);
                        }
                        this.mImages[n2] = copyImages2[n2];
                    }
                    else {
                        final int[] getBits2 = this.mImages[n2].GetBits();
                        final int[] getBits3 = copyImages2[n2].GetBits();
                        for (int n4 = 0; n4 < getBits2.length; ++n4) {
                            getBits2[n4] = ((getBits2[n4] & 0xFFFFFF) | (getBits3[n4] & 0xFF) << 24);
                        }
                    }
                    this.mImages[n2].BitsChanged();
                }
            }
            if (this.mImages == null) {
                this.mApplet.FatalError("loading");
                this.mLoadingFailed = true;
                return;
            }
            final long currentTimeMillis = System.currentTimeMillis();
            this.CustomFiltering();
            final int n5 = (int)(System.currentTimeMillis() - currentTimeMillis);
            System.out.println("Filter time: " + n5);
            if (n5 > this.GetSlowFilterTime()) {
                this.mSlowFilter = true;
            }
            this.mImagesLoaded = true;
            if (this.mImagesLoaded && this.mSoundsLoaded) {
                System.out.println("Resource loading time: " + (System.currentTimeMillis() - this.mResourceLoadStart) + "ms");
            }
            while (true) {
                Label_1001: {
                    synchronized (this) {
                        this.notifyAll();
                        break Label_1001;
                    }
                    synchronized (this) {
                        Label_0980: {
                            try {
                                this.wait();
                                break Label_0980;
                            }
                            catch (Exception ex4) {
                            }
                            // monitorexit(this)
                        }
                    }
                }
                if (this.mSoundsLoaded || this.mShutdown) {
                    this.mLoaded = true;
                    this.mApplet.RequiredResoucesLoaded();
                    this.PostFiltering();
                    while (!this.mShutdown) {
                        this.ProcessAsyncImages();
                        synchronized (this) {
                            Label_1054: {
                                try {
                                    this.wait(50L);
                                    break Label_1054;
                                }
                                catch (Exception ex5) {
                                }
                                // monitorexit(this)
                            }
                        }
                    }
                    System.out.println("Resource thread exiting");
                    return;
                }
                continue;
            }
        }
        catch (Exception ex2) {
            ex2.printStackTrace();
            System.out.println("Resource thread failed due to exception!");
            this.mApplet.CaughtThrowable(ex2);
        }
    }
    
    public double GetProgressPercent() {
        if (this.mLoaded) {
            return 100.0;
        }
        if (this.mLoadedResCount < this.mTotalResCount || this.mTotalCustomFilterCount == 0) {
            return 100.0 * this.mLoadedResCount * (1.0 - this.GetCustomFilteringPortion()) / this.mTotalResCount;
        }
        return 100.0 * (1.0 - this.GetCustomFilteringPortion() + this.mProcessedCustomFilterCount * this.GetCustomFilteringPortion() / this.mTotalCustomFilterCount);
    }
    
    public int[] FadeBetween(final int[] array, final int[] array2, final int n, final int n2, final double n3) {
        final int[] array3 = new int[n * n2];
        for (int i = 0; i < array3.length; ++i) {
            final int n4 = array[i];
            final int n5 = n4 & 0xFF;
            final int n6 = n4 >> 8 & 0xFF;
            final int n7 = n4 >> 16 & 0xFF;
            final int n8 = n4 >> 24 & 0xFF;
            final int n9 = array2[i];
            array3[i] = ((int)(n5 * (1.0 - n3) + (n9 & 0xFF) * n3) | (int)(n6 * (1.0 - n3) + (n9 >> 8 & 0xFF) * n3) << 8 | (int)(n7 * (1.0 - n3) + (n9 >> 16 & 0xFF) * n3) << 16 | Math.max(n8, n9 >> 24 & 0xFF) << 24);
        }
        return array3;
    }
    
    public synchronized void Start() {
        if (!this.mStarted) {
            this.mStarted = true;
            this.mImages = new SexyImage[this.GetImageFileNames().length];
            this.mSounds = new AudioClip[this.GetSoundFileNames().length];
            this.notifyAll();
        }
    }
}

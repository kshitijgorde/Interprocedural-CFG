// 
// Decompiled by Procyon v0.5.30
// 

package netcharts.graphics;

import java.awt.Image;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.awt.Component;
import java.awt.Canvas;
import java.awt.Frame;
import java.awt.image.FilteredImageSource;
import java.awt.Toolkit;
import java.awt.image.IndexColorModel;
import java.awt.image.DirectColorModel;
import netcharts.util.NFImageChunk;
import java.util.Hashtable;
import java.awt.Color;
import java.awt.image.ColorModel;
import java.util.Vector;
import java.awt.image.ImageConsumer;
import java.awt.image.ImageFilter;

public class NFLabelRotate extends ImageFilter implements ImageConsumer
{
    protected int[][] image;
    protected int width;
    protected int height;
    protected Vector chunks;
    protected ColorModel model;
    protected int rotate;
    protected Color transColor;
    protected Vector red;
    protected Vector green;
    protected Vector blue;
    protected Hashtable map;
    
    public NFLabelRotate() {
        this.image = null;
        this.width = 0;
        this.height = 0;
        this.chunks = new Vector();
        this.model = null;
        this.rotate = 0;
        this.transColor = null;
        this.red = new Vector();
        this.green = new Vector();
        this.blue = new Vector();
        this.map = new Hashtable();
    }
    
    protected void resetVariables() {
        this.image = null;
        this.width = 0;
        this.height = 0;
        this.red.removeAllElements();
        this.green.removeAllElements();
        this.blue.removeAllElements();
        this.map.clear();
        this.model = null;
        this.chunks.removeAllElements();
    }
    
    public void init(final int rotate, final Color transColor) {
        this.rotate = rotate;
        this.transColor = transColor;
        final int red = this.transColor.getRed();
        final int green = this.transColor.getGreen();
        final int blue = this.transColor.getBlue();
        this.red.addElement(new Integer(red));
        this.green.addElement(new Integer(green));
        this.blue.addElement(new Integer(blue));
        this.map.put(String.valueOf("" + red + "," + green + "," + blue), new Integer(this.red.size() - 1));
    }
    
    public void setDimensions(final int width, final int height) {
        this.width = width;
        this.height = height;
    }
    
    public void setProperties(final Hashtable hashtable) {
    }
    
    public void setColorModel(final ColorModel model) {
        this.model = model;
    }
    
    public void setHints(final int n) {
    }
    
    public void setPixels(final int n, final int n2, final int n3, final int n4, final ColorModel colorModel, final byte[] array, final int n5, final int n6) {
        final int[][] array2 = new int[n3][n4];
        for (int i = 0; i < n4; ++i) {
            for (int j = 0; j < n3; ++j) {
                array2[j][i] = (array[i * n6 + j + n5] & 0xFF);
            }
        }
        this.chunks.addElement(new NFImageChunk(n, n2, n3, n4, array2));
    }
    
    public void setPixels(final int n, final int n2, final int n3, final int n4, final ColorModel colorModel, final int[] array, final int n5, final int n6) {
        final int[][] array2 = new int[n3][n4];
        for (int i = 0; i < n4; ++i) {
            for (int j = 0; j < n3; ++j) {
                final int n7 = array[i * n6 + j + n5];
                final int red = colorModel.getRed(n7);
                final int green = colorModel.getGreen(n7);
                final int blue = colorModel.getBlue(n7);
                final String value = String.valueOf("" + red + "," + green + "," + blue);
                Integer n8;
                if ((n8 = this.map.get(value)) == null) {
                    this.red.addElement(new Integer(red));
                    this.green.addElement(new Integer(green));
                    this.blue.addElement(new Integer(blue));
                    this.map.put(value, n8 = new Integer(this.red.size() - 1));
                }
                array2[j][i] = n8;
            }
        }
        this.chunks.addElement(new NFImageChunk(n, n2, n3, n4, array2));
    }
    
    public static ColorModel getModel(final Vector vector, final Vector vector2, final Vector vector3, final Color color, final ColorModel colorModel) {
        IndexColorModel indexColorModel;
        if (colorModel instanceof DirectColorModel) {
            final byte[] array = new byte[vector.size()];
            final byte[] array2 = new byte[vector2.size()];
            final byte[] array3 = new byte[vector3.size()];
            for (int i = 1; i < vector.size(); ++i) {
                array[i] = (byte)(int)vector.elementAt(i);
                array2[i] = (byte)(int)vector2.elementAt(i);
                array3[i] = (byte)(int)vector3.elementAt(i);
            }
            array[0] = (byte)color.getRed();
            array2[0] = (byte)color.getGreen();
            array3[0] = (byte)color.getBlue();
            indexColorModel = new IndexColorModel(8, vector.size(), array, array2, array3, 0);
        }
        else {
            final IndexColorModel indexColorModel2 = (IndexColorModel)colorModel;
            final int mapSize = indexColorModel2.getMapSize();
            final byte b = (byte)color.getRed();
            final byte b2 = (byte)color.getGreen();
            final byte b3 = (byte)color.getBlue();
            final byte[] array4 = new byte[256];
            final byte[] array5 = new byte[256];
            final byte[] array6 = new byte[256];
            final byte[] array7 = new byte[256];
            indexColorModel2.getReds(array4);
            indexColorModel2.getGreens(array5);
            indexColorModel2.getBlues(array6);
            indexColorModel2.getAlphas(array7);
            for (int j = 0; j < mapSize; ++j) {
                if (array4[j] == b && array5[j] == b2 && array6[j] == b3) {
                    array7[j] = 0;
                }
                else {
                    array7[j] = -1;
                }
            }
            indexColorModel = new IndexColorModel(8, mapSize, array4, array5, array6, array7);
        }
        return indexColorModel;
    }
    
    public void imageComplete(final int n) {
        this.build();
        final byte[] array = new byte[this.width * this.height];
        int n2 = this.width;
        int n3 = this.height;
        if (this.rotate == 0) {
            n2 = this.width;
            n3 = this.height;
            for (int i = 0; i < this.image.length; ++i) {
                for (int j = 0; j < this.image[i].length; ++j) {
                    array[j * this.image.length + i] = (byte)this.image[i][j];
                }
            }
        }
        else if (this.rotate == 180) {
            n2 = this.width;
            n3 = this.height;
            for (int k = 0; k < this.image.length; ++k) {
                for (int l = 0; l < this.image[k].length; ++l) {
                    array[(this.image[0].length - l - 1) * this.image.length + (this.image.length - k - 1)] = (byte)this.image[k][l];
                }
            }
        }
        else if (this.rotate == 90) {
            n2 = this.height;
            n3 = this.width;
            for (int n4 = 0; n4 < this.image.length; ++n4) {
                for (int n5 = 0; n5 < this.image[n4].length; ++n5) {
                    array[(this.image.length - n4 - 1) * this.image[0].length + n5] = (byte)this.image[n4][n5];
                }
            }
        }
        else if (this.rotate == 270) {
            n2 = this.height;
            n3 = this.width;
            for (int n6 = 0; n6 < this.image.length; ++n6) {
                for (int n7 = 0; n7 < this.image[n6].length; ++n7) {
                    array[n6 * this.image[0].length + (this.image[0].length - n7 - 1)] = (byte)this.image[n6][n7];
                }
            }
        }
        super.consumer.setHints(0);
        super.consumer.setDimensions(n2, n3);
        super.consumer.setPixels(0, 0, n2, n3, getModel(this.red, this.green, this.blue, this.transColor, this.model), array, 0, n2);
        super.consumer.imageComplete(n);
        this.resetVariables();
    }
    
    protected void build() {
        this.image = new int[this.width][this.height];
        for (int i = 0; i < this.chunks.size(); ++i) {
            final NFImageChunk nfImageChunk = this.chunks.elementAt(i);
            final int x = nfImageChunk.getX();
            final int y = nfImageChunk.getY();
            final int width = nfImageChunk.getWidth();
            final int height = nfImageChunk.getHeight();
            final int[][] pixels = nfImageChunk.getPixels();
            for (int j = 0; j < height; ++j) {
                for (int k = 0; k < width; ++k) {
                    this.image[x + k][y + j] = pixels[k][j];
                }
            }
        }
        this.chunks.removeAllElements();
    }
    
    public static void main(final String[] array) {
        final Image image = Toolkit.getDefaultToolkit().getImage(array[1]);
        final NFLabelRotate nfLabelRotate = new NFLabelRotate();
        nfLabelRotate.init(Integer.parseInt(array[0]), Color.red);
        final FilteredImageSource filteredImageSource = new FilteredImageSource(image.getSource(), nfLabelRotate);
        final Frame frame = new Frame();
        final Canvas canvas = new Canvas();
        frame.add(canvas);
        frame.pack();
        frame.show();
        frame.resize(500, 500);
        final Image image2 = canvas.createImage(filteredImageSource);
        while (true) {
            canvas.getGraphics().drawImage(image2, 0, 0, image2.getWidth(canvas), image2.getHeight(canvas), canvas);
            try {
                Thread.sleep(1000L);
            }
            catch (Exception ex) {}
        }
    }
}

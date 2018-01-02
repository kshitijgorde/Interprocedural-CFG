import java.awt.image.ImageProducer;
import java.awt.image.ImageFilter;
import java.awt.image.FilteredImageSource;
import java.awt.Component;
import java.awt.MediaTracker;
import java.awt.Image;
import java.applet.Applet;
import java.util.StringTokenizer;
import java.net.URL;
import java.io.InputStream;
import java.io.FileInputStream;
import java.util.Vector;
import java.io.DataInputStream;
import java.awt.image.RGBImageFilter;

// 
// Decompiled by Procyon v0.5.30
// 

public class Enhance extends RGBImageFilter
{
    private DataInputStream dis;
    private Vector names;
    private Vector tables;
    private Vector list;
    private int[] myRGBTab;
    
    public Enhance(final String s) throws Exception {
        this.dis = null;
        this.names = new Vector();
        this.tables = new Vector();
        this.list = new Vector();
        try {
            this.buildList(this.dis = new DataInputStream(new FileInputStream(s)));
        }
        catch (Exception ex) {
            throw new Exception("URL for enh.tab not found");
        }
    }
    
    public Enhance(final URL url) throws Exception {
        this.dis = null;
        this.names = new Vector();
        this.tables = new Vector();
        this.list = new Vector();
        try {
            this.buildList(this.dis = new DataInputStream(url.openStream()));
        }
        catch (Exception ex) {
            throw new Exception("URL for enh.tab not found");
        }
    }
    
    private void buildList(final DataInputStream dataInputStream) {
        this.myRGBTab = new int[256];
        super.canFilterIndexColorModel = true;
        try {
            while (true) {
                String s = dataInputStream.readLine();
                if (s == null) {
                    break;
                }
                final int index = s.indexOf("#");
                if (index == 0) {
                    continue;
                }
                if (s.length() < 2) {
                    continue;
                }
                if (index > 0) {
                    s = s.substring(0, index);
                }
                if (s.startsWith("*")) {
                    this.names.addElement(s.substring(1));
                    this.list = new Vector();
                    this.tables.addElement(this.list);
                }
                else {
                    final StringTokenizer stringTokenizer = new StringTokenizer(s, " ");
                    if (stringTokenizer.countTokens() != 8) {
                        continue;
                    }
                    final int[] array = new int[8];
                    for (int i = 0; i < 8; ++i) {
                        array[i] = Integer.parseInt(stringTokenizer.nextToken().trim());
                    }
                    this.list.addElement(array);
                }
            }
        }
        catch (Exception ex) {
            System.out.println("Ex reading:" + ex);
        }
    }
    
    public String[] getNames() {
        final int size = this.names.size();
        final String[] array = new String[size];
        for (int i = 0; i < size; ++i) {
            array[i] = (String)this.names.elementAt(i);
        }
        return array;
    }
    
    public int[] getTable(final int n) {
        final Vector<int[]> vector = this.tables.elementAt(n);
        for (int i = 0; i < 256; ++i) {
            this.myRGBTab[i] = 0;
        }
        for (int j = 0; j < vector.size(); ++j) {
            final int[] array = vector.elementAt(j);
            final int n2 = array[1] - array[0];
            float n3 = array[2];
            final float n4 = (array[3] - n3) / n2;
            float n5 = array[4];
            final float n6 = (array[5] - n5) / n2;
            float n7 = array[6];
            final float n8 = (array[7] - n7) / n2;
            for (int k = array[0]; k <= array[1]; ++k) {
                this.myRGBTab[k] = ((int)n3 << 16 | (int)n5 << 8 | (int)n7);
                n3 += n4;
                n5 += n6;
                n7 += n8;
            }
        }
        return this.myRGBTab;
    }
    
    public Image[] EnhanceImages(final Applet applet, final Image[] array, final int n) {
        this.getTable(n);
        final MediaTracker mediaTracker = new MediaTracker(applet);
        final int length = array.length;
        if (length < 1) {
            return null;
        }
        final Image[] array2 = new Image[length];
        for (int i = 0; i < length; ++i) {
            try {
                array2[i] = applet.createImage(new FilteredImageSource(array[i].getSource(), this));
            }
            catch (OutOfMemoryError outOfMemoryError) {
                array2[i] = null;
            }
            mediaTracker.addImage(array2[i], 0);
        }
        try {
            mediaTracker.waitForID(0);
        }
        catch (InterruptedException ex) {}
        return array2;
    }
    
    public int filterRGB(final int n, final int n2, final int n3) {
        final int n4 = (n3 & 0xFF0000) >> 16;
        final int n5 = (n3 & 0xFF00) >> 8;
        final int n6 = n3 & 0xFF;
        if (Math.abs(n4 - n5) < 5 && Math.abs(n5 - n6) < 5) {
            return (n3 & 0xFF000000) | this.myRGBTab[n4];
        }
        return n3;
    }
    
    public static void main(final String[] array) {
        final String s = "enh.tab";
        System.out.println("Enh test");
        try {
            final Enhance enhance = new Enhance(s);
            final String[] names = enhance.getNames();
            for (int i = 0; i < names.length; ++i) {
                System.out.println("i=" + i + "  name=" + names[i]);
                final int[] table = enhance.getTable(i);
                for (int j = 0; j < 256; ++j) {
                    System.out.println("k=" + j + "  r/g/b=" + (table[j] >> 16) % 256 + " " + (table[j] >> 8) % 256 + " " + table[j] % 256);
                }
            }
        }
        catch (Exception ex) {
            System.out.println("####  " + ex);
        }
    }
}

import java.awt.Component;
import java.awt.MediaTracker;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.io.IOException;
import java.net.MalformedURLException;
import java.io.DataInputStream;
import java.net.URL;
import java.awt.image.ImageProducer;
import java.awt.image.ImageFilter;
import java.awt.image.FilteredImageSource;
import java.awt.image.CropImageFilter;
import java.awt.Image;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class Digits
{
    Applet theApplet;
    String digitDirectory;
    Image theDigits;
    Image[] singleDigits;
    int[] digitWidths;
    
    public void separateDigits() {
        for (int i = 0; i < 10; ++i) {
            this.singleDigits[i] = this.theApplet.createImage(new FilteredImageSource(this.theDigits.getSource(), new CropImageFilter(this.getOffset(i), 0, this.digitWidths[i], this.digitWidths[10])));
        }
    }
    
    public int getOffset(final int n) {
        int n2 = 0;
        for (int i = 0; i < n; ++i) {
            n2 += this.digitWidths[i];
        }
        return n2;
    }
    
    public void getDigitWidths() {
        try {
            final DataInputStream dataInputStream = new DataInputStream(new URL(this.theApplet.getCodeBase(), "digits/" + this.digitDirectory + "/digitwidths.txt").openConnection().getInputStream());
            int n = 0;
            String line;
            while ((line = dataInputStream.readLine()) != null) {
                try {
                    this.digitWidths[n] = Integer.parseInt(line);
                }
                catch (NumberFormatException ex3) {}
                ++n;
            }
            dataInputStream.close();
        }
        catch (MalformedURLException ex) {
            System.out.println("MalformedURLException: " + ex);
        }
        catch (IOException ex2) {
            System.out.println("IOException: " + ex2);
        }
    }
    
    public Image getSingleDigit(final int n) {
        return this.singleDigits[n];
    }
    
    public int getHeight() {
        return this.digitWidths[10];
    }
    
    public int drawDigits(final long n, final int n2, final int n3, final Graphics graphics) {
        int n4 = 0;
        final Long n5 = new Long(n);
        final char[] array = { '0' };
        final String s = new String("0");
        graphics.getClipRect();
        final String string = n5.toString();
        for (int i = 0; i < string.length(); ++i) {
            int int1 = 0;
            final Integer n6 = new Integer(0);
            try {
                array[0] = string.charAt(i);
                int1 = Integer.parseInt(new String(array));
            }
            catch (StringIndexOutOfBoundsException ex) {}
            catch (NumberFormatException ex2) {}
            graphics.drawImage(this.singleDigits[int1], n2 + n4, n3, this.theApplet);
            n4 += this.digitWidths[int1];
        }
        return n4;
    }
    
    public Digits(final Applet theApplet, final String digitDirectory) {
        this.digitDirectory = digitDirectory;
        this.digitWidths = new int[11];
        this.singleDigits = new Image[10];
        this.theApplet = theApplet;
        final MediaTracker mediaTracker = new MediaTracker(this.theApplet);
        mediaTracker.addImage(this.theDigits = this.theApplet.getImage(this.theApplet.getCodeBase(), "digits/" + this.digitDirectory + "/digits.gif"), 0);
        try {
            this.theApplet.showStatus("JCount: downloading counter digits...");
            mediaTracker.waitForAll();
            this.theApplet.showStatus("JCount: done downloading digits...");
        }
        catch (InterruptedException ex) {}
        this.getDigitWidths();
        this.separateDigits();
    }
}

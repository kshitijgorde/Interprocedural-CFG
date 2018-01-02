import java.awt.image.ImageProducer;
import java.awt.image.PixelGrabber;
import java.net.MalformedURLException;
import java.awt.Component;
import java.awt.MediaTracker;
import java.awt.Event;
import java.awt.image.ImageObserver;
import java.awt.FontMetrics;
import java.awt.Color;
import java.net.URL;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.MemoryImageSource;
import java.awt.Font;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class DS_InterleaveText extends Applet implements Runnable
{
    Thread 0;
    int 3;
    Font 4;
    int 5;
    int 6;
    int[] 7;
    int[] 8;
    MemoryImageSource 9;
    Image 0b;
    int 1b;
    long 2b;
    String 3b;
    String 4b;
    int 5b;
    int 6b;
    String 7b;
    String 8b;
    String 9b;
    boolean 0c;
    boolean 1c;
    Image 2c;
    Graphics 3c;
    Image 4c;
    int 5c;
    int 6c;
    int 7c;
    int 8c;
    int 9c;
    int 0d;
    int 1d;
    int 2d;
    int 3d;
    int 4d;
    int 5d;
    int 6d;
    int 7d;
    String 8d;
    int 9d;
    boolean 0e;
    boolean 1e;
    Font[] 2e;
    String[] 3e;
    int[] 4e;
    int[] 5e;
    int[] 6e;
    int[] 7e;
    int 8e;
    int[] 9e;
    int 0f;
    int 1f;
    int[] 2f;
    int 3f;
    int 4f;
    int 5f;
    int 6f;
    int 7f;
    int 8f;
    String 9f;
    URL[] 0g;
    String[] 1g;
    String[] 2g;
    URL 3g;
    boolean 4g;
    byte[] 5g;
    byte[] 6g;
    String[] 7g;
    Color[] 8g;
    Color[] 9g;
    Font[] 0h;
    FontMetrics[] 1h;
    String[] 2h;
    int[] 3h;
    int[] 4h;
    
    public String getAppletInfo() {
        return "DS PageFlipText v1.0\nby Dario Sciacca\ndario@dseffects.com\nwww.dseffects.com";
    }
    
    public void init() {
        this.5();
        this.showStatus("Please wait ...");
        this.6b = this.getFontMetrics(this.4).stringWidth(this.7b);
        this.5 = this.size().width;
        this.6 = this.size().height;
        this.6();
        this.8 = new int[this.5 * this.6];
        this.9 = new MemoryImageSource(this.5, this.6, this.8, 0, this.5);
        this.2c = this.createImage(this.5, this.6);
        this.3c = this.2c.getGraphics();
        this.2c();
        this.5c();
        if (!this.0c && !this.1c) {
            this.5b = 0;
        }
        else if (this.0c && !this.1c) {
            this.5b = 1;
        }
        else if (!this.0c && this.1c) {
            this.5b = 2;
        }
        else {
            this.5b = 3;
        }
        this.3b();
        this.1c();
        if (!this.9()) {
            this.1e = false;
        }
        this.7();
        this.4b();
        if (this.7d == -16777216) {
            this.3 = 1;
        }
    }
    
    public void start() {
        (this.0 = new Thread(this)).start();
    }
    
    public void stop() {
        if (this.0 != null) {
            this.0.stop();
            this.0 = null;
        }
    }
    
    public void run() {
        this.showStatus(this.8d);
        System.gc();
        final Graphics graphics = this.getGraphics();
        this.2b = System.currentTimeMillis();
        while (this.0 != null) {
            if (this.3 == 1) {
                this.6b();
            }
            this.3(graphics);
            this.0();
            if (this.1b++ > 10) {
                System.gc();
                this.1b = 0;
            }
        }
    }
    
    synchronized void 0() {
        Thread.yield();
        this.getToolkit().sync();
        final long n = 10L - (System.currentTimeMillis() - this.2b);
        if (n > 0L) {
            try {
                Thread.sleep(n);
            }
            catch (InterruptedException ex) {}
        }
        else {
            try {
                Thread.sleep(1L);
            }
            catch (InterruptedException ex2) {}
        }
        this.2b = System.currentTimeMillis();
    }
    
    public void update(final Graphics graphics) {
    }
    
    void 3(final Graphics graphics) {
        final int n = this.5 >> 1;
        final int n2 = this.6 >> 1;
        if (this.3 == 0) {
            graphics.drawString("Error ...", 10, 10);
            return;
        }
        if (this.0b != null) {
            if (this.5b == 0) {
                this.3c.drawImage(this.0b, 0, 0, this);
            }
            else if (this.5b == 1) {
                this.3c.drawImage(this.0b, 0, 0, this);
                this.4c(this.3c);
            }
            else if (this.5b == 2) {
                this.3c.drawImage(this.0b, 0, 0, this);
                this.3c.drawImage(this.4c, this.5c, this.6c, this);
            }
            else {
                this.3c.drawImage(this.0b, 0, 0, this);
                this.4c(this.3c);
                this.3c.drawImage(this.4c, this.5c, this.6c, this);
            }
        }
        if (this.0e && !this.4g) {
            this.3c.setColor(Color.white);
            this.3c.drawLine(n - 64, n2 - 8, n + 64, n2 - 8);
            this.3c.drawLine(n - 64, n2 + 8, n + 64, n2 + 8);
            this.3c.drawLine(n - 64, n2 - 8, n - 64, n2 + 8);
            this.3c.drawLine(n + 64, n2 - 8, n + 64, n2 + 8);
            this.3c.setColor(Color.blue);
            this.3c.fillRect(n - 63, n2 - 7, 127, 15);
            this.3c.setFont(this.4);
            this.3c.setColor(Color.yellow);
            this.3c.drawString(this.7b, n - (this.6b >> 1), n2 + 5);
        }
        graphics.drawImage(this.2c, 0, 0, this);
    }
    
    private final void 4() {
        while (true) {
            this.showStatus(this.8b);
        }
    }
    
    public final boolean mouseEnter(final Event event, final int n, final int n2) {
        this.showStatus(this.2g[this.1f]);
        return this.0e = true;
    }
    
    public final boolean mouseExit(final Event event, final int n, final int n2) {
        this.0e = false;
        this.showStatus("");
        return true;
    }
    
    public final boolean mouseMove(final Event event, final int n, final int n2) {
        return this.0e = true;
    }
    
    void 5() {
        final String parameter = this.getParameter("credits");
        if (parameter != null) {
            if (!parameter.equals(this.9b)) {
                this.4();
            }
        }
        else {
            this.4();
        }
        this.5d = 1;
    }
    
    void 6() {
        this.6d = 1;
        for (int i = 0; i < 11; ++i) {
            if (this.4b.charAt(i) == this.3b.charAt(i) || this.5d == 0) {
                this.4();
            }
        }
        this.4g = false;
    }
    
    void 7() {
        if (this.5d == 0 || this.6d == 0) {
            this.4();
        }
        for (int i = 0; i < 17; ++i) {
            if (this.4b.charAt(i) == this.7b.charAt(i)) {
                this.4();
            }
        }
        this.7d = -16777216;
        if (this.3b.charAt(1) != 'p' || this.3b.charAt(7) != 'b' || this.3b.charAt(21) != 'c' || this.3b.charAt(17) != 'c' || this.3b.charAt(12) != 'r' || this.3b.charAt(11) != 'a') {
            this.4();
        }
    }
    
    int[] 8(final int[] array, final int n, final int n2, final int[] array2, final int n3, final int n4) {
        int n5 = 0;
        final double n6 = n3 / n;
        final double n7 = n4 / n2;
        for (int i = 0; i < n2; ++i) {
            final int n8 = (int)(i * n7);
            for (int j = 0; j < n; ++j) {
                array[n5++] = array2[n8 * n3 + (int)(j * n6)];
            }
        }
        return array;
    }
    
    boolean 9() {
        final String parameter = this.getParameter("image");
        if (parameter == null) {
            return false;
        }
        final Image 0b = this.0b(parameter);
        if (0b == null) {
            this.showStatus("Error loading image ");
            return false;
        }
        final int width = 0b.getWidth(this);
        final int height = 0b.getHeight(this);
        this.7 = new int[this.5 * this.6];
        if (width != this.5 || height != this.6) {
            final int[] array = new int[width * height];
            if (!this.1b(0b, array, width, height)) {
                return false;
            }
            this.7 = this.8(this.7, this.5, this.6, array, width, height);
        }
        else if (!this.1b(0b, this.7, this.5, this.6)) {
            return false;
        }
        0b.flush();
        System.gc();
        return true;
    }
    
    Image 0b(final String s) {
        this.showStatus("Loading Image ...");
        final MediaTracker mediaTracker = new MediaTracker(this);
        URL url = null;
        try {
            url = new URL(this.getDocumentBase(), s);
        }
        catch (MalformedURLException ex) {}
        Image image;
        try {
            image = this.getImage(url);
            mediaTracker.addImage(image, 1);
            mediaTracker.waitForID(1);
        }
        catch (InterruptedException ex2) {
            image = null;
        }
        if (mediaTracker.isErrorID(1)) {
            image = null;
        }
        return image;
    }
    
    boolean 1b(final Image image, final int[] array, final int n, final int n2) {
        final PixelGrabber pixelGrabber = new PixelGrabber(image, 0, 0, n, n2, array, 0, n);
        try {
            if (!pixelGrabber.grabPixels()) {
                this.showStatus("Image error");
                return false;
            }
        }
        catch (InterruptedException ex) {
            this.showStatus("Image error");
            return false;
        }
        if ((pixelGrabber.status() & 0x80) != 0x0) {
            this.showStatus("Image error");
            return false;
        }
        return true;
    }
    
    Color 2b(final String s, final Color color) {
        final String parameter = this.getParameter(s);
        Color color2;
        if (parameter != null && parameter.length() == 6) {
            try {
                color2 = new Color(Integer.parseInt(parameter, 16));
            }
            catch (NumberFormatException ex) {
                color2 = color;
            }
        }
        else {
            color2 = color;
        }
        return color2;
    }
    
    void 3b() {
        this.0f = 1;
        while (this.getParameter("text" + String.valueOf(this.0f)) != null) {
            ++this.0f;
        }
        --this.0f;
        if (this.0f == 0) {
            this.0f = 1;
        }
        this.2e = new Font[this.0f];
        this.3e = new String[this.0f];
        this.4e = new int[this.0f];
        this.5e = new int[this.0f];
        this.6e = new int[this.0f];
        for (int i = 1; i < this.0f + 1; ++i) {
            final String parameter = this.getParameter("text" + i);
            if (parameter == null) {
                this.3e[i - 1] = "DS Effects";
            }
            else {
                this.3e[i - 1] = parameter;
            }
            String parameter2 = this.getParameter("textfont" + i);
            if (parameter2 == null) {
                parameter2 = "Helvetica";
            }
            final String parameter3 = this.getParameter("textstyle" + i);
            int n;
            if (parameter3 == null) {
                n = 1;
            }
            else if (parameter3.equalsIgnoreCase("PLAIN")) {
                n = 0;
            }
            else if (parameter3.equalsIgnoreCase("BOLD")) {
                n = 1;
            }
            else if (parameter3.equalsIgnoreCase("ITALIC")) {
                n = 2;
            }
            else if (parameter3.equalsIgnoreCase("BOLD ITALIC")) {
                n = 3;
            }
            else {
                n = 1;
            }
            final String parameter4 = this.getParameter("textsize" + i);
            int int1;
            if (parameter4 == null) {
                int1 = 42;
            }
            else {
                int1 = Integer.parseInt(parameter4);
            }
            this.2e[i - 1] = new Font(parameter2, n, int1);
            final String parameter5 = this.getParameter("textx" + i);
            if (parameter5 == null) {
                this.4e[i - 1] = -10000;
            }
            else {
                this.4e[i - 1] = Integer.valueOf(parameter5);
            }
            final String parameter6 = this.getParameter("texty" + i);
            if (parameter6 == null) {
                this.5e[i - 1] = -10000;
            }
            else {
                this.5e[i - 1] = Integer.valueOf(parameter6);
            }
            this.6e[i - 1] = this.2b("textcolor" + i, new Color(16711680)).getRGB();
        }
        final String parameter7 = this.getParameter("direction");
        if (parameter7 == null) {
            this.8f = 0;
        }
        else if (parameter7.equalsIgnoreCase("horizontal")) {
            this.8f = 0;
        }
        else if (parameter7.equalsIgnoreCase("vertical")) {
            this.8f = 1;
        }
        this.8e = this.2b("bgcolor", new Color(0)).getRGB();
        String parameter8 = this.getParameter("speed");
        if (parameter8 == null) {
            parameter8 = "2";
        }
        this.6f = Integer.valueOf(parameter8);
        this.6f = ((this.6f < 1) ? 1 : ((this.6f > 8) ? 8 : this.6f));
        String parameter9 = this.getParameter("pause");
        if (parameter9 == null) {
            parameter9 = "1";
        }
        this.7f = Integer.valueOf(parameter9);
        this.7f = ((this.7f < 0) ? 0 : ((this.7f > 8) ? 8 : this.7f));
        this.7f *= 50;
        final String parameter10 = this.getParameter("interactive");
        if (parameter10 == null) {
            this.9d = 1;
        }
        else if (parameter10.equalsIgnoreCase("IN")) {
            this.9d = 0;
        }
        else if (parameter10.equalsIgnoreCase("OUT")) {
            this.9d = 1;
        }
        else {
            this.9d = 2;
        }
        this.0g = new URL[this.0f];
        this.1g = new String[this.0f];
        this.2g = new String[this.0f];
        for (int j = 1; j <= this.0f; ++j) {
            this.2g[j - 1] = this.3b;
        }
        final String parameter11 = this.getParameter("regkey");
        if (parameter11 != null) {
            this.9f = parameter11;
            for (int k = 1; k <= this.0f; ++k) {
                final String parameter12 = this.getParameter("reglink" + k);
                if (parameter12 != null) {
                    try {
                        this.0g[k - 1] = new URL("http://" + parameter12);
                    }
                    catch (MalformedURLException ex) {
                        this.0g[k - 1] = null;
                    }
                    final String parameter13 = this.getParameter("regtarget" + k);
                    if (parameter13 != null) {
                        this.1g[k - 1] = parameter13;
                    }
                }
                final String parameter14 = this.getParameter("regstatusmsg" + k);
                if (parameter14 != null) {
                    this.2g[k - 1] = parameter14;
                }
            }
        }
    }
    
    void 4b() {
        this.1f = 0;
        final int n = this.5 * this.6;
        this.7e = new int[n];
        this.9e = new int[n];
        this.9b();
        if (!this.1e) {
            this.7 = new int[n];
            for (int i = 0; i < n; ++i) {
                this.7[i] = this.8e;
            }
        }
        for (int j = 0; j < this.5 * this.6; ++j) {
            if (this.7e[j] == 255) {
                this.9e[j] = this.6e[this.1f];
            }
            else {
                this.9e[j] = 16777215;
            }
        }
        int n2;
        if (this.8f == 0) {
            n2 = this.5;
        }
        else {
            n2 = this.6;
        }
        this.7f *= this.6f;
        this.4f = this.7f + n2 + n2;
        this.2f = new int[this.4f];
        for (int k = 0; k < n2; ++k) {
            this.2f[k] = k;
        }
        for (int l = n2; l < n2 + this.7f; ++l) {
            this.2f[l] = n2;
        }
        for (int n3 = n2 + this.7f; n3 < n2 + n2 + this.7f; ++n3) {
            this.2f[n3] = n2 + n2 + this.7f - n3;
        }
    }
    
    void 5b() {
        if (this.3f > this.5f) {
            this.3f -= this.6f;
            if (this.3f < this.5f) {
                this.3f = this.5f;
            }
        }
        else {
            this.3f += this.6f;
            if (this.3f > this.5f) {
                this.3f = this.5f;
            }
        }
    }
    
    void 6b() {
        if (this.9d == 0) {
            if (this.0e) {
                this.3f += this.6f;
            }
            else {
                this.5b();
            }
        }
        else if (this.9d == 1) {
            if (this.0e) {
                this.5b();
            }
            else {
                this.3f += this.6f;
            }
        }
        else {
            this.3f += this.6f;
        }
        if (this.3f >= this.4f) {
            this.3f = 0;
            this.1f = (this.1f + 1) % this.0f;
            this.9b();
            for (int i = 0; i < this.5 * this.6; ++i) {
                if (this.7e[i] == 255) {
                    this.9e[i] = this.6e[this.1f];
                }
                else {
                    this.9e[i] = 16777215;
                }
            }
        }
        final int n = this.2f[this.3f];
        if (this.8f == 0) {
            this.7b(n);
        }
        else if (this.8f == 1) {
            this.8b(n);
        }
        this.0b = this.createImage(this.9);
    }
    
    void 7b(final int n) {
        for (int n2 = this.5 * this.6, i = 0; i < n2; ++i) {
            this.8[i] = this.7[i];
        }
        final int n3 = this.5 - 1;
        for (int j = 0; j < this.6; j += 2) {
            for (int k = 0; k < this.5; ++k) {
                final int n4 = this.5 - n + k;
                if (n4 >= 0 && n4 < this.5) {
                    final int n5 = this.9e[j * this.5 + k];
                    if (n5 != 16777215) {
                        this.8[j * this.5 + n4] = n5;
                    }
                }
            }
        }
        for (int l = 1; l < this.6; l += 2) {
            for (int n6 = 0; n6 < this.5; ++n6) {
                final int n7 = n - n6 - 1;
                if (n7 >= 0 && n7 < this.5) {
                    final int n8 = this.9e[l * this.5 + n3 - n6];
                    if (n8 != 16777215) {
                        this.8[l * this.5 + n7] = n8;
                    }
                }
            }
        }
    }
    
    void 8b(final int n) {
        for (int n2 = this.5 * this.6, i = 0; i < n2; ++i) {
            this.8[i] = this.7[i];
        }
        final int n3 = this.6 - 1;
        for (int j = 0; j < this.5; j += 2) {
            for (int k = 0; k < this.6; ++k) {
                final int n4 = this.6 - n + k;
                if (n4 >= 0 && n4 < this.6) {
                    final int n5 = this.9e[k * this.5 + j];
                    if (n5 != 16777215) {
                        this.8[n4 * this.5 + j] = n5;
                    }
                }
            }
        }
        for (int l = 1; l < this.5; l += 2) {
            for (int n6 = 0; n6 < this.6; ++n6) {
                final int n7 = n - n6 - 1;
                if (n7 >= 0 && n7 < this.6) {
                    final int n8 = this.9e[(n3 - n6) * this.5 + l];
                    if (n8 != 16777215) {
                        this.8[n7 * this.5 + l] = n8;
                    }
                }
            }
        }
    }
    
    void 9b() {
        new MemoryImageSource(this.5, this.6, this.7e, 0, this.5);
        final Image image = this.createImage(this.5, this.6);
        final Graphics graphics = image.getGraphics();
        graphics.setColor(Color.black);
        graphics.fillRect(0, 0, this.5, this.6);
        graphics.setColor(Color.white);
        graphics.setFont(this.2e[this.1f]);
        final FontMetrics fontMetrics = graphics.getFontMetrics(this.2e[this.1f]);
        final int stringWidth = fontMetrics.stringWidth(this.3e[this.1f]);
        final int height = fontMetrics.getHeight();
        final int n = (height >> 1) + (height >> 3);
        if (this.4e[this.1f] == -10000) {
            this.4e[this.1f] = (this.5 >> 1) - (stringWidth >> 1);
        }
        if (this.5e[this.1f] == -10000) {
            this.5e[this.1f] = (this.6 >> 1) - (n >> 1);
        }
        graphics.drawString(this.3e[this.1f], this.4e[this.1f], this.5e[this.1f] + n);
        final PixelGrabber pixelGrabber = new PixelGrabber(image, 0, 0, this.5, this.6, this.7e, 0, this.5);
        try {
            pixelGrabber.grabPixels();
        }
        catch (InterruptedException ex) {}
        for (int i = 0; i < this.5 * this.6; ++i) {
            if ((this.7e[i] & 0xFF) == 0xFF) {
                this.7e[i] = 255;
            }
            else {
                this.7e[i] = 0;
            }
        }
    }
    
    void 0c(final int n, final int n2, final int n3, final int n4) {
        final int n5 = this.5 >> 1;
        for (int i = n2; i < n3; ++i) {
            final double n6 = -(i * 3.141592653589793 * 2.0) / n4;
            final int n7 = (int)Math.abs(256.0 * Math.cos(n6));
            final int n8 = (int)(n5 * Math.sin(n6));
            final int n9 = (int)(n5 * Math.cos(n6));
            for (int j = 0; j < this.6; ++j) {
                final int n10 = n9 + j - n5;
                final int n11 = this.5 - 1 - (n - i);
                final int n12 = this.5 - 1 - (n8 + n);
                if (n12 >= 0 && n11 >= 0 && n12 < this.5 && n11 < this.5 && n10 < this.6 && n10 >= 0) {
                    final int n13 = this.9e[n11 + j * this.5];
                    if (n13 == this.6e[this.1f]) {
                        this.8[n12 + n10 * this.5] = (0xFF000000 | (n13 >> 16 & 0xFF) * n7 >> 8 << 16 | (n13 >> 8 & 0xFF) * n7 >> 8 << 8 | (n13 & 0xFF) * n7 >> 8);
                    }
                    else {
                        this.8[n12 + n10 * this.5] = this.7[n12 + n10 * this.5];
                    }
                }
            }
        }
    }
    
    void 1c() {
        final String host = this.getDocumentBase().getHost();
        if (host.length() > 0 && this.9f.length() > 9) {
            final int n = this.9f.length() - 9;
            final int n2 = n + 9;
            this.5g = new byte[n];
            this.9f.getBytes(1, n + 1, this.5g, 0);
            this.6g = new byte[n2];
            this.9f.getBytes(0, n2, this.6g, 0);
            int n3 = n % 7;
            final int n4 = n % 3;
            for (int i = 0; i < n; ++i) {
                final byte b = this.5g[i];
                final byte b2 = (byte)(b + n3);
                if (b >= 48 && b <= 57) {
                    this.5g[i] = ((b2 > 57) ? ((byte)(b2 - 10)) : b2);
                }
                else if (b >= 65 && b <= 90) {
                    this.5g[i] = ((b2 > 90) ? ((byte)(b2 - 26)) : b2);
                }
                else if (b >= 97 && b <= 122) {
                    this.5g[i] = ((b2 > 122) ? ((byte)(b2 - 26)) : b2);
                }
                else if (b == 42) {
                    this.5g[i] = 45;
                }
                else if (b == 45) {
                    this.5g[i] = 46;
                }
                n3 = (n3 + n4) % 7;
            }
            int n5 = n % 7;
            final int n6 = n % 3;
            for (int j = 0; j < n; ++j) {
                final byte b3 = this.5g[j];
                final byte b4 = (byte)(b3 - n5);
                if (b3 >= 48 && b3 <= 57) {
                    this.6g[j + 1] = ((b4 < 48) ? ((byte)(b4 + 10)) : b4);
                }
                else if (b3 >= 65 && b3 <= 90) {
                    this.6g[j + 1] = ((b4 < 65) ? ((byte)(b4 + 26)) : b4);
                }
                else if (b3 >= 97 && b3 <= 122) {
                    this.6g[j + 1] = ((b4 < 97) ? ((byte)(b4 + 26)) : b4);
                }
                else if (b3 == 45) {
                    this.6g[j + 1] = 42;
                }
                else if (b3 == 46) {
                    this.6g[j + 1] = 45;
                }
                n5 = (n5 + n6) % 7;
            }
            byte[] array;
            if (n > 4) {
                array = new byte[n - 4];
                for (int k = 0; k < n - 4; ++k) {
                    array[k] = this.5g[k + 4];
                }
            }
            else {
                array = new byte[] { 0 };
            }
            if (this.6g[0] == this.6g[n >> 1] && this.6g[1 + n] == this.6g[1] && this.6g[1 + n + 1] == this.6g[n >> 1] && this.6g[1 + n + 2] == (byte)(97 + n5) && this.6g[1 + n + 3] == 45 && this.6g[1 + n + 4] == (byte)(122 - n6) && this.6g[1 + n + 5] == (byte)(110 + n5) && this.6g[1 + n + 6] == this.6g[1] && this.6g[1 + n + 7] == this.6g[n] && (host.equalsIgnoreCase(new String(this.5g, 0)) || host.equalsIgnoreCase(new String(array, 0)))) {
                this.4g = true;
            }
        }
        try {
            this.3g = new URL("http://" + this.7b);
        }
        catch (MalformedURLException ex) {
            this.3g = null;
        }
    }
    
    public final boolean mouseDown(final Event event, final int n, final int n2) {
        if (!this.4g) {
            this.getAppletContext().showDocument(this.3g, "_blank");
        }
        else if (this.0g[this.1f] != null) {
            if (this.1g[this.1f] != null) {
                this.getAppletContext().showDocument(this.0g[this.1f], this.1g[this.1f]);
            }
            else {
                this.getAppletContext().showDocument(this.0g[this.1f]);
            }
        }
        return true;
    }
    
    void 2c() {
        int 0d = 0;
        do {
            ++0d;
        } while (this.getParameter("overtext" + 0d) != null);
        if (--0d > 0) {
            this.0c = true;
            this.0d = 0d;
            this.7g = new String[this.0d];
            this.8g = new Color[this.0d];
            this.9g = new Color[this.0d];
            this.0h = new Font[this.0d];
            this.1h = new FontMetrics[this.0d];
            this.2h = new String[this.0d];
            this.3h = new int[this.0d];
            this.4h = new int[this.0d];
            for (int i = 0; i < this.0d; ++i) {
                this.7g[i] = this.getParameter("overtext" + String.valueOf(i + 1));
                this.8g[i] = this.2b("overtextcol" + String.valueOf(i + 1), new Color(16777215));
                this.9g[i] = this.2b("overtextcols" + String.valueOf(i + 1), new Color(0));
                final String parameter = this.getParameter("overtexty" + String.valueOf(i + 1));
                if (parameter == null) {
                    this.4h[i] = 10;
                }
                else {
                    this.4h[i] = Integer.parseInt(parameter);
                }
                String parameter2 = this.getParameter("overTextFont" + String.valueOf(i + 1));
                if (parameter2 == null) {
                    parameter2 = "Helvetica";
                }
                final String parameter3 = this.getParameter("overTextStyle" + String.valueOf(i + 1));
                int n;
                if (parameter3 == null) {
                    n = 0;
                }
                else if (parameter3.equalsIgnoreCase("PLAIN")) {
                    n = 0;
                }
                else if (parameter3.equalsIgnoreCase("BOLD")) {
                    n = 1;
                }
                else if (parameter3.equalsIgnoreCase("ITALIC")) {
                    n = 2;
                }
                else if (parameter3.equalsIgnoreCase("BOLD ITALIC")) {
                    n = 3;
                }
                else {
                    n = 0;
                }
                final String parameter4 = this.getParameter("overTextSize" + String.valueOf(i + 1));
                int int1;
                if (parameter4 == null) {
                    int1 = 24;
                }
                else {
                    int1 = Integer.parseInt(parameter4);
                }
                this.0h[i] = new Font(parameter2, n, int1);
                this.1h[i] = this.3c.getFontMetrics(this.0h[i]);
                this.2h[i] = this.getParameter("overTextType" + String.valueOf(i + 1));
                if (this.2h[i] == null) {
                    this.2h[i] = "scrollleft";
                }
                final String parameter5 = this.getParameter("overtextspeed" + String.valueOf(i + 1));
                if (parameter5 == null) {
                    this.3h[i] = 2;
                }
                else {
                    this.3h[i] = Integer.valueOf(parameter5);
                    if (this.3h[i] < 1 || this.3h[i] > 4) {
                        this.3h[i] = 2;
                    }
                }
            }
            this.3c();
        }
    }
    
    void 3c() {
        this.3d = this.1h[this.9c].stringWidth(this.7g[this.9c]);
        this.4d = this.1h[this.9c].getHeight();
        if (this.2h[this.9c].equalsIgnoreCase("scrolldown")) {
            this.1d = this.5 - this.3d >> 1;
            this.2d = 0;
            return;
        }
        if (this.2h[this.9c].equalsIgnoreCase("scrollup")) {
            this.1d = this.5 - this.3d >> 1;
            this.2d = this.6 + this.4d;
            return;
        }
        if (this.2h[this.9c].equalsIgnoreCase("scrollright")) {
            this.1d = -this.3d;
            this.2d = this.4h[this.9c] + (this.4d >> 1) + (this.4d >> 3);
            return;
        }
        this.1d = this.5;
        this.2d = this.4h[this.9c] + (this.4d >> 1) + (this.4d >> 3);
    }
    
    void 4c(final Graphics graphics) {
        graphics.setFont(this.0h[this.9c]);
        graphics.setColor(this.9g[this.9c]);
        graphics.drawString(this.7g[this.9c], this.1d + 1, this.2d + 1);
        graphics.setColor(this.8g[this.9c]);
        graphics.drawString(this.7g[this.9c], this.1d, this.2d);
        if (this.2h[this.9c].equalsIgnoreCase("scrolldown")) {
            this.2d += this.3h[this.9c];
        }
        else if (this.2h[this.9c].equalsIgnoreCase("scrollup")) {
            this.2d -= this.3h[this.9c];
        }
        else if (this.2h[this.9c].equalsIgnoreCase("scrollright")) {
            this.1d += this.3h[this.9c];
        }
        else {
            this.1d -= this.3h[this.9c];
        }
        if (this.2d > this.6 + this.4d || this.2d < -this.4d || this.1d > this.5 || this.1d < -this.3d) {
            ++this.9c;
            if (this.9c >= this.0d) {
                this.9c = 0;
            }
            this.3c();
        }
    }
    
    void 5c() {
        final String parameter = this.getParameter("OverImage");
        if (parameter != null) {
            this.4c = this.0b(parameter);
        }
        if (this.4c != null) {
            this.1c = true;
            this.7c = this.4c.getWidth(this);
            this.8c = this.4c.getHeight(this);
            final String parameter2 = this.getParameter("OverImageX");
            if (parameter2 == null) {
                this.5c = (this.5 >> 1) - (this.7c >> 1);
            }
            else {
                this.5c = Integer.valueOf(parameter2);
            }
            final String parameter3 = this.getParameter("OverImageY");
            if (parameter3 == null) {
                this.6c = (this.6 >> 1) - (this.8c >> 1);
                return;
            }
            this.6c = Integer.valueOf(parameter3);
        }
    }
    
    public DS_InterleaveText() {
        this.4 = new Font("Helvetica", 1, 12);
        this.3b = "Applet by Dario Sciacca";
        this.4b = "dario@dseffects.com";
        this.7b = "www.dseffects.com";
        this.8b = "Don't remove Dario Sciacca's credits line";
        this.9b = this.3b + " (" + this.7b + ")";
        this.0c = false;
        this.1c = false;
        this.8d = "InterleaveText started";
        this.0e = false;
        this.1e = true;
        this.9f = "";
        this.4g = false;
    }
}

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

public class DS_Melt extends Applet implements Runnable
{
    Thread 0;
    int 3;
    Font 4;
    int 5;
    int 6;
    int[] 7;
    int[] 8;
    int[] 9;
    int[] 0b;
    MemoryImageSource 1b;
    Image 2b;
    int 3b;
    long 4b;
    String 5b;
    String 6b;
    int 7b;
    int 8b;
    String 9b;
    String 0c;
    String 1c;
    boolean 2c;
    boolean 3c;
    Image 4c;
    Graphics 5c;
    Image 6c;
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
    int 8d;
    int 9d;
    int 0e;
    int 1e;
    String 2e;
    int 3e;
    boolean 4e;
    int 5e;
    int 6e;
    int 7e;
    int 8e;
    int[] 9e;
    int 0f;
    int 1f;
    int 2f;
    int 3f;
    int 4f;
    String 5f;
    URL[] 6f;
    String[] 7f;
    String[] 8f;
    URL 9f;
    boolean 0g;
    byte[] 1g;
    byte[] 2g;
    String[] 3g;
    Color[] 4g;
    Color[] 5g;
    Font[] 6g;
    FontMetrics[] 7g;
    String[] 8g;
    int[] 9g;
    int[] 0h;
    
    public String getAppletInfo() {
        return "DS Melt v1.0\nby Dario Sciacca\ndario@dseffects.com\nwww.dseffects.com";
    }
    
    public void init() {
        this.5();
        this.showStatus("Please wait ...");
        this.8b = this.getFontMetrics(this.4).stringWidth(this.9b);
        this.5 = this.size().width;
        this.6 = this.size().height;
        this.6();
        this.0b = new int[this.5 * this.6];
        this.1b = new MemoryImageSource(this.5, this.6, this.0b, 0, this.5);
        this.4c = this.createImage(this.5, this.6);
        this.5c = this.4c.getGraphics();
        this.2c();
        this.5c();
        if (!this.2c && !this.3c) {
            this.7b = 0;
        }
        else if (this.2c && !this.3c) {
            this.7b = 1;
        }
        else if (!this.2c && this.3c) {
            this.7b = 2;
        }
        else {
            this.7b = 3;
        }
        if (this.9()) {
            this.3b();
            this.1c();
            this.7();
            this.4b();
            if (this.9d == -16777216) {
                this.3 = 1;
            }
            return;
        }
        while (true) {
            this.showStatus("Error loading image ");
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
        this.showStatus(this.2e);
        System.gc();
        final Graphics graphics = this.getGraphics();
        this.4b = System.currentTimeMillis();
        while (this.0 != null) {
            if (this.3 == 1) {
                this.6b();
            }
            this.3(graphics);
            this.0();
            if (this.3b++ > 10) {
                System.gc();
                this.3b = 0;
            }
        }
    }
    
    synchronized void 0() {
        Thread.yield();
        this.getToolkit().sync();
        final long n = 10L - (System.currentTimeMillis() - this.4b);
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
        this.4b = System.currentTimeMillis();
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
        if (this.2b != null) {
            if (this.7b == 0) {
                this.5c.drawImage(this.2b, 0, 0, this);
            }
            else if (this.7b == 1) {
                this.5c.drawImage(this.2b, 0, 0, this);
                this.4c(this.5c);
            }
            else if (this.7b == 2) {
                this.5c.drawImage(this.2b, 0, 0, this);
                this.5c.drawImage(this.6c, this.7c, this.8c, this);
            }
            else {
                this.5c.drawImage(this.2b, 0, 0, this);
                this.4c(this.5c);
                this.5c.drawImage(this.6c, this.7c, this.8c, this);
            }
        }
        if (this.4e && !this.0g) {
            this.5c.setColor(Color.white);
            this.5c.drawLine(n - 64, n2 - 8, n + 64, n2 - 8);
            this.5c.drawLine(n - 64, n2 + 8, n + 64, n2 + 8);
            this.5c.drawLine(n - 64, n2 - 8, n - 64, n2 + 8);
            this.5c.drawLine(n + 64, n2 - 8, n + 64, n2 + 8);
            this.5c.setColor(Color.blue);
            this.5c.fillRect(n - 63, n2 - 7, 127, 15);
            this.5c.setFont(this.4);
            this.5c.setColor(Color.yellow);
            this.5c.drawString(this.9b, n - (this.8b >> 1), n2 + 5);
        }
        graphics.drawImage(this.4c, 0, 0, this);
    }
    
    private final void 4() {
        while (true) {
            this.showStatus(this.0c);
        }
    }
    
    public final boolean mouseEnter(final Event event, final int n, final int n2) {
        this.showStatus(this.8f[this.6e]);
        return this.4e = true;
    }
    
    public final boolean mouseExit(final Event event, final int n, final int n2) {
        this.4e = false;
        this.showStatus("");
        return true;
    }
    
    public final boolean mouseMove(final Event event, final int 0e, final int 1e) {
        this.0e = 0e;
        this.1e = 1e;
        this.4e = true;
        this.showStatus(this.8f[this.6e]);
        return true;
    }
    
    void 5() {
        final String parameter = this.getParameter("credits");
        if (parameter != null) {
            if (!parameter.equals(this.1c)) {
                this.4();
            }
        }
        else {
            this.4();
        }
        this.7d = 1;
    }
    
    void 6() {
        this.8d = 1;
        for (int i = 0; i < 11; ++i) {
            if (this.6b.charAt(i) == this.5b.charAt(i) || this.7d == 0) {
                this.4();
            }
        }
        this.0g = false;
    }
    
    void 7() {
        if (this.7d == 0 || this.8d == 0) {
            this.4();
        }
        for (int i = 0; i < 17; ++i) {
            if (this.6b.charAt(i) == this.9b.charAt(i)) {
                this.4();
            }
        }
        this.9d = -16777216;
        if (this.5b.charAt(1) != 'p' || this.5b.charAt(7) != 'b' || this.5b.charAt(21) != 'c' || this.5b.charAt(17) != 'c' || this.5b.charAt(12) != 'r' || this.5b.charAt(11) != 'a') {
            this.4();
        }
    }
    
    int[] 8(final int n, final int[] array, final int n2, final int n3, final int[] array2, final int n4, final int n5) {
        int n6 = n;
        final double n7 = n4 / n2;
        final double n8 = n5 / n3;
        for (int i = 0; i < n3; ++i) {
            final int n9 = (int)(i * n8);
            for (int j = 0; j < n2; ++j) {
                array[n6++] = array2[n9 * n4 + (int)(j * n7)];
            }
        }
        return array;
    }
    
    boolean 9() {
        this.5e = 1;
        while (this.getParameter("image" + String.valueOf(this.5e)) != null) {
            ++this.5e;
        }
        --this.5e;
        if (this.5e >= 2) {
            final String[] array = new String[this.5e];
            this.7 = new int[this.5 * this.6 * this.5e];
            final Image[] array2 = new Image[this.5e];
            array2[0] = null;
            for (int i = 0; i < this.5e; ++i) {
                array[i] = this.getParameter("image" + String.valueOf(i + 1));
            }
            int n = 0;
            final int n2 = this.5 * this.6;
            for (int j = 0; j < this.5e; ++j) {
                array2[j] = this.0b(array[j], j + 1);
                if (array2[j] == null) {
                    this.showStatus("Error loading image ");
                    return false;
                }
                final int width = array2[j].getWidth(this);
                final int height = array2[j].getHeight(this);
                if (width != this.5 || height != this.6) {
                    final int[] array3 = new int[width * height];
                    if (!this.1b(0, array2[j], array3, width, height)) {
                        return false;
                    }
                    this.7 = this.8(n, this.7, this.5, this.6, array3, width, height);
                }
                else if (!this.1b(n, array2[j], this.7, this.5, this.6)) {
                    return false;
                }
                this.6f = new URL[this.5e];
                this.7f = new String[this.5e];
                this.8f = new String[this.5e];
                array2[j].flush();
                array2[j] = null;
                System.gc();
                n += n2;
            }
            return true;
        }
        while (true) {
            this.showStatus("Error, at least 2 images required");
        }
    }
    
    Image 0b(final String s, final int n) {
        this.showStatus("Loading Images ...");
        final MediaTracker mediaTracker = new MediaTracker(this);
        URL url = null;
        try {
            url = new URL(this.getDocumentBase(), s);
        }
        catch (MalformedURLException ex) {}
        Image image;
        try {
            image = this.getImage(url);
            mediaTracker.addImage(image, n);
            mediaTracker.waitForID(n);
        }
        catch (InterruptedException ex2) {
            image = null;
        }
        if (mediaTracker.isErrorID(n)) {
            image = null;
        }
        return image;
    }
    
    boolean 1b(final int n, final Image image, final int[] array, final int n2, final int n3) {
        final PixelGrabber pixelGrabber = new PixelGrabber(image, 0, 0, n2, n3, array, n, n2);
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
        final String parameter = this.getParameter("direction");
        if (parameter == null) {
            this.4f = 0;
        }
        else if (parameter.equalsIgnoreCase("up")) {
            this.4f = 0;
        }
        else if (parameter.equalsIgnoreCase("down")) {
            this.4f = 1;
        }
        else if (parameter.equalsIgnoreCase("left")) {
            this.4f = 2;
        }
        else {
            this.4f = 3;
        }
        String parameter2 = this.getParameter("speed");
        if (parameter2 == null) {
            parameter2 = "2";
        }
        this.2f = Integer.valueOf(parameter2);
        this.2f = ((this.2f < 1) ? 1 : ((this.2f > 8) ? 8 : this.2f));
        String parameter3 = this.getParameter("pause");
        if (parameter3 == null) {
            parameter3 = "1";
        }
        this.3f = Integer.valueOf(parameter3);
        this.3f = ((this.3f < 0) ? 0 : ((this.3f > 8) ? 8 : this.3f));
        this.3f *= 50;
        final String parameter4 = this.getParameter("interactive");
        if (parameter4 == null) {
            this.3e = 1;
        }
        else if (parameter4.equalsIgnoreCase("IN")) {
            this.3e = 0;
        }
        else if (parameter4.equalsIgnoreCase("OUT")) {
            this.3e = 1;
        }
        else {
            this.3e = 2;
        }
        for (int i = 1; i <= this.5e; ++i) {
            this.8f[i - 1] = this.5b;
        }
        final String parameter5 = this.getParameter("regkey");
        if (parameter5 != null) {
            this.5f = parameter5;
            for (int j = 1; j <= this.5e; ++j) {
                final String parameter6 = this.getParameter("reglink" + j);
                if (parameter6 != null) {
                    try {
                        this.6f[j - 1] = new URL("http://" + parameter6);
                    }
                    catch (MalformedURLException ex) {
                        this.6f[j - 1] = null;
                    }
                    final String parameter7 = this.getParameter("regtarget" + j);
                    if (parameter7 != null) {
                        this.7f[j - 1] = parameter7;
                    }
                }
                final String parameter8 = this.getParameter("regstatusmsg" + j);
                if (parameter8 != null) {
                    this.8f[j - 1] = parameter8;
                }
            }
        }
    }
    
    void 4b() {
        this.0f = 0;
        this.6e = 0;
        int n;
        if (this.4f == 0 || this.4f == 1) {
            n = this.6;
        }
        else {
            n = this.5;
        }
        this.3f *= this.2f;
        this.1f = this.3f + n;
        this.9e = new int[this.1f];
        for (int i = 0; i < this.3f; ++i) {
            this.9e[i] = 0;
        }
        for (int j = this.3f; j < n + this.3f; ++j) {
            this.9e[j] = j - this.3f;
        }
        this.8 = new int[this.5 * this.6];
        this.9 = new int[this.5 * this.6];
        for (int k = 0; k < this.5 * this.6; ++k) {
            this.8[k] = this.7[k];
            this.9[k] = this.7[k + this.5 * this.6];
        }
        this.7e = 0;
        this.8e = this.5 * this.6;
    }
    
    void 5b() {
        this.0f -= this.2f;
        if (this.0f < 0) {
            this.0f = 0;
        }
    }
    
    void 6b() {
        if (this.3e == 0) {
            if (this.4e) {
                this.0f += this.2f;
            }
            else {
                this.5b();
            }
        }
        else if (this.3e == 1) {
            if (!this.4e) {
                this.0f += this.2f;
            }
            else {
                this.5b();
            }
        }
        else {
            this.0f += this.2f;
        }
        final int n = this.5 * this.6;
        final int n2 = this.5 * this.6 * this.5e;
        if (this.0f > this.1f - this.2f) {
            this.0f = 0;
            this.6e = (this.6e + 1) % this.5e;
            this.7e = (this.7e + n) % n2;
            this.8e = (this.8e + n) % n2;
            for (int i = 0; i < this.5 * this.6; ++i) {
                this.8[i] = this.7[i + this.7e];
                this.9[i] = this.7[i + this.8e];
            }
        }
        final int n3 = this.9e[this.0f];
        if (this.4f == 0) {
            this.7b(n3);
        }
        else if (this.4f == 1) {
            this.8b(n3);
        }
        else if (this.4f == 2) {
            this.9b(n3);
        }
        else {
            this.0c(n3);
        }
        this.2b = this.createImage(this.1b);
    }
    
    void 7b(final int n) {
        int n2 = 0;
        int i;
        for (int n3 = i = this.6 - 1; i >= 0; --i) {
            for (int j = 0; j < this.5; ++j, ++n2) {
                if (i <= n) {
                    this.0b[n2] = this.8[(n3 - n) * this.5 + j];
                }
                else {
                    this.0b[n2] = this.8[(n3 - i) * this.5 + j];
                }
            }
        }
    }
    
    void 8b(final int n) {
        int n2 = 0;
        for (int i = 0; i < this.6; ++i) {
            for (int j = 0; j < this.5; ++j, ++n2) {
                if (i <= n) {
                    this.0b[n2] = this.8[n * this.5 + j];
                }
                else {
                    this.0b[n2] = this.8[i * this.5 + j];
                }
            }
        }
    }
    
    void 9b(final int n) {
        int i;
        for (int n2 = i = this.5 - 1; i >= 0; --i) {
            for (int j = 0; j < this.6; ++j) {
                final int n3 = j * this.5 + n2 - i;
                if (i <= n) {
                    this.0b[n3] = this.8[j * this.5 + n2 - n];
                }
                else {
                    this.0b[n3] = this.8[j * this.5 + n2 - i];
                }
            }
        }
    }
    
    void 0c(final int n) {
        for (int i = 0; i < this.5; ++i) {
            for (int j = 0; j < this.6; ++j) {
                final int n2 = j * this.5 + i;
                if (i <= n) {
                    this.0b[n2] = this.8[j * this.5 + n];
                }
                else {
                    this.0b[n2] = this.8[n2];
                }
            }
        }
    }
    
    void 1c() {
        final String host = this.getDocumentBase().getHost();
        if (host.length() > 0 && this.5f.length() > 9) {
            final int n = this.5f.length() - 9;
            final int n2 = n + 9;
            this.1g = new byte[n];
            this.5f.getBytes(1, n + 1, this.1g, 0);
            this.2g = new byte[n2];
            this.5f.getBytes(0, n2, this.2g, 0);
            int n3 = n % 7;
            final int n4 = n % 3;
            for (int i = 0; i < n; ++i) {
                final byte b = this.1g[i];
                final byte b2 = (byte)(b + n3);
                if (b >= 48 && b <= 57) {
                    this.1g[i] = ((b2 > 57) ? ((byte)(b2 - 10)) : b2);
                }
                else if (b >= 65 && b <= 90) {
                    this.1g[i] = ((b2 > 90) ? ((byte)(b2 - 26)) : b2);
                }
                else if (b >= 97 && b <= 122) {
                    this.1g[i] = ((b2 > 122) ? ((byte)(b2 - 26)) : b2);
                }
                else if (b == 42) {
                    this.1g[i] = 45;
                }
                else if (b == 45) {
                    this.1g[i] = 46;
                }
                n3 = (n3 + n4) % 7;
            }
            int n5 = n % 7;
            final int n6 = n % 3;
            for (int j = 0; j < n; ++j) {
                final byte b3 = this.1g[j];
                final byte b4 = (byte)(b3 - n5);
                if (b3 >= 48 && b3 <= 57) {
                    this.2g[j + 1] = ((b4 < 48) ? ((byte)(b4 + 10)) : b4);
                }
                else if (b3 >= 65 && b3 <= 90) {
                    this.2g[j + 1] = ((b4 < 65) ? ((byte)(b4 + 26)) : b4);
                }
                else if (b3 >= 97 && b3 <= 122) {
                    this.2g[j + 1] = ((b4 < 97) ? ((byte)(b4 + 26)) : b4);
                }
                else if (b3 == 45) {
                    this.2g[j + 1] = 42;
                }
                else if (b3 == 46) {
                    this.2g[j + 1] = 45;
                }
                n5 = (n5 + n6) % 7;
            }
            byte[] array;
            if (n > 4) {
                array = new byte[n - 4];
                for (int k = 0; k < n - 4; ++k) {
                    array[k] = this.1g[k + 4];
                }
            }
            else {
                array = new byte[] { 0 };
            }
            if (this.2g[0] == this.2g[n >> 1] && this.2g[1 + n] == this.2g[1] && this.2g[1 + n + 1] == this.2g[n >> 1] && this.2g[1 + n + 2] == (byte)(97 + n5) && this.2g[1 + n + 3] == 45 && this.2g[1 + n + 4] == (byte)(122 - n6) && this.2g[1 + n + 5] == (byte)(110 + n5) && this.2g[1 + n + 6] == this.2g[1] && this.2g[1 + n + 7] == this.2g[n] && (host.equalsIgnoreCase(new String(this.1g, 0)) || host.equalsIgnoreCase(new String(array, 0)))) {
                this.0g = true;
            }
        }
        try {
            this.9f = new URL("http://" + this.9b);
        }
        catch (MalformedURLException ex) {
            this.9f = null;
        }
    }
    
    public final boolean mouseDown(final Event event, final int n, final int n2) {
        if (!this.0g) {
            this.getAppletContext().showDocument(this.9f, "_blank");
        }
        else if (this.6f[this.6e] != null) {
            if (this.7f[this.6e] != null) {
                this.getAppletContext().showDocument(this.6f[this.6e], this.7f[this.6e]);
            }
            else {
                this.getAppletContext().showDocument(this.6f[this.6e]);
            }
        }
        return true;
    }
    
    void 2c() {
        int 2d = 0;
        do {
            ++2d;
        } while (this.getParameter("overtext" + 2d) != null);
        if (--2d > 0) {
            this.2c = true;
            this.2d = 2d;
            this.3g = new String[this.2d];
            this.4g = new Color[this.2d];
            this.5g = new Color[this.2d];
            this.6g = new Font[this.2d];
            this.7g = new FontMetrics[this.2d];
            this.8g = new String[this.2d];
            this.9g = new int[this.2d];
            this.0h = new int[this.2d];
            for (int i = 0; i < this.2d; ++i) {
                this.3g[i] = this.getParameter("overtext" + String.valueOf(i + 1));
                this.4g[i] = this.2b("overtextcol" + String.valueOf(i + 1), new Color(16777215));
                this.5g[i] = this.2b("overtextcols" + String.valueOf(i + 1), new Color(0));
                final String parameter = this.getParameter("overtexty" + String.valueOf(i + 1));
                if (parameter == null) {
                    this.0h[i] = 10;
                }
                else {
                    this.0h[i] = Integer.parseInt(parameter);
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
                this.6g[i] = new Font(parameter2, n, int1);
                this.7g[i] = this.5c.getFontMetrics(this.6g[i]);
                this.8g[i] = this.getParameter("overTextType" + String.valueOf(i + 1));
                if (this.8g[i] == null) {
                    this.8g[i] = "scrollleft";
                }
                final String parameter5 = this.getParameter("overtextfadespeed" + String.valueOf(i + 1));
                if (parameter5 == null) {
                    this.9g[i] = 2;
                }
                else {
                    this.9g[i] = Integer.valueOf(parameter5);
                    if (this.9g[i] < 1 || this.9g[i] > 4) {
                        this.9g[i] = 2;
                    }
                }
            }
            this.3c();
        }
    }
    
    void 3c() {
        this.5d = this.7g[this.1d].stringWidth(this.3g[this.1d]);
        this.6d = this.7g[this.1d].getHeight();
        if (this.8g[this.1d].equalsIgnoreCase("scrolldown")) {
            this.3d = this.5 - this.5d >> 1;
            this.4d = 0;
            return;
        }
        if (this.8g[this.1d].equalsIgnoreCase("scrollup")) {
            this.3d = this.5 - this.5d >> 1;
            this.4d = this.6 + this.6d;
            return;
        }
        if (this.8g[this.1d].equalsIgnoreCase("scrollright")) {
            this.3d = -this.5d;
            this.4d = this.0h[this.1d] + (this.6d >> 1) + (this.6d >> 3);
            return;
        }
        this.3d = this.5;
        this.4d = this.0h[this.1d] + (this.6d >> 1) + (this.6d >> 3);
    }
    
    void 4c(final Graphics graphics) {
        graphics.setFont(this.6g[this.1d]);
        graphics.setColor(this.5g[this.1d]);
        graphics.drawString(this.3g[this.1d], this.3d + 1, this.4d + 1);
        graphics.setColor(this.4g[this.1d]);
        graphics.drawString(this.3g[this.1d], this.3d, this.4d);
        if (this.8g[this.1d].equalsIgnoreCase("scrolldown")) {
            this.4d += this.9g[this.1d];
        }
        else if (this.8g[this.1d].equalsIgnoreCase("scrollup")) {
            this.4d -= this.9g[this.1d];
        }
        else if (this.8g[this.1d].equalsIgnoreCase("scrollright")) {
            this.3d += this.9g[this.1d];
        }
        else {
            this.3d -= this.9g[this.1d];
        }
        if (this.4d > this.6 + this.6d || this.4d < -this.6d || this.3d > this.5 || this.3d < -this.5d) {
            ++this.1d;
            if (this.1d >= this.2d) {
                this.1d = 0;
            }
            this.3c();
        }
    }
    
    void 5c() {
        final String parameter = this.getParameter("OverImage");
        if (parameter != null) {
            this.6c = this.0b(parameter, 0);
        }
        if (this.6c != null) {
            this.3c = true;
            this.9c = this.6c.getWidth(this);
            this.0d = this.6c.getHeight(this);
            final String parameter2 = this.getParameter("OverImageX");
            if (parameter2 == null) {
                this.7c = (this.5 >> 1) - (this.9c >> 1);
            }
            else {
                this.7c = Integer.valueOf(parameter2);
            }
            final String parameter3 = this.getParameter("OverImageY");
            if (parameter3 == null) {
                this.8c = (this.6 >> 1) - (this.0d >> 1);
                return;
            }
            this.8c = Integer.valueOf(parameter3);
        }
    }
    
    public DS_Melt() {
        this.4 = new Font("Helvetica", 1, 12);
        this.5b = "Applet by Dario Sciacca";
        this.6b = "dario@dseffects.com";
        this.9b = "www.dseffects.com";
        this.0c = "Don't remove Dario Sciacca's credits line";
        this.1c = this.5b + " (" + this.9b + ")";
        this.2c = false;
        this.3c = false;
        this.2e = "Melt started";
        this.4e = false;
        this.5f = "";
        this.0g = false;
    }
}

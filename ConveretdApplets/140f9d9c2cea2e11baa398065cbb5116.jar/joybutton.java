import java.awt.image.ImageProducer;
import java.awt.image.MemoryImageSource;
import java.awt.image.PixelGrabber;
import java.awt.Component;
import java.awt.MediaTracker;
import java.awt.Font;
import java.awt.Event;
import java.net.MalformedURLException;
import java.net.URL;
import java.awt.image.ImageObserver;
import java.util.StringTokenizer;
import java.applet.AudioClip;
import java.awt.Image;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.Color;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class joybutton extends Applet implements Runnable
{
    Color[] fontColor;
    int[] num;
    int[] fontX;
    int[] fontY;
    int[] fontSize;
    int[] fontStyle;
    int[] font;
    int[] fontMessages;
    String[] message;
    Dimension offDimension;
    Thread animationThread;
    Graphics offGraphics;
    Graphics[] btn_offGraphics;
    int w;
    int h;
    int bk_x;
    int bk_y;
    int start;
    int[] button_w;
    int[] button_h;
    int[] button_x;
    int[] button_y;
    int num_buttons;
    int[] btn_style;
    int[] image_x;
    int[] image_y;
    int last_button_move;
    int[] border_shading;
    int[] border_thickness;
    int[] border;
    int fontIndex;
    int BkShading;
    int BkThickness;
    int BkBorderState;
    int[] border_up;
    int delayTime;
    Color[] btn_color;
    Color[] btn_border_color;
    Color BkBorderColor;
    Color bkcolor;
    Image[] image;
    Image[] btn_offImage;
    Image offImage;
    Image bkimage;
    String[] image_name;
    String bkimage_name;
    String[] status;
    String[] frame;
    String[] link;
    String[] sound_name;
    boolean bk_resize;
    boolean tile;
    boolean registered;
    boolean[] resize;
    AudioClip[] sound;
    
    public void init() {
        this.w = this.size().width;
        this.h = this.size().height;
        this.registered = false;
        int n = 0;
        final StringTokenizer stringTokenizer = new StringTokenizer(this.getParameter("G"), "|");
        this.num_buttons = Integer.parseInt(stringTokenizer.nextToken());
        this.bkimage_name = stringTokenizer.nextToken();
        this.bkcolor = new Color(Integer.parseInt(stringTokenizer.nextToken(), 16));
        if (stringTokenizer.nextToken().equalsIgnoreCase("1")) {
            this.bk_resize = true;
        }
        if (stringTokenizer.nextToken().equalsIgnoreCase("1")) {
            this.tile = true;
        }
        this.bk_x = Integer.parseInt(stringTokenizer.nextToken());
        this.bk_y = Integer.parseInt(stringTokenizer.nextToken());
        if (stringTokenizer.nextToken().equalsIgnoreCase("7")) {
            this.registered = true;
        }
        final int int1 = Integer.parseInt(stringTokenizer.nextToken());
        this.BkShading = Integer.parseInt(stringTokenizer.nextToken());
        this.BkThickness = Integer.parseInt(stringTokenizer.nextToken());
        this.BkBorderColor = new Color(Integer.parseInt(stringTokenizer.nextToken(), 16));
        this.BkBorderState = Integer.parseInt(stringTokenizer.nextToken());
        final int n2 = this.num_buttons * 3;
        this.font = new int[int1];
        this.fontX = new int[int1];
        this.fontY = new int[int1];
        this.fontSize = new int[int1];
        this.fontStyle = new int[int1];
        this.message = new String[int1];
        this.fontColor = new Color[int1];
        this.fontMessages = new int[n2];
        this.last_button_move = this.num_buttons;
        this.btn_offGraphics = new Graphics[n2];
        this.btn_offImage = new Image[n2];
        this.btn_color = new Color[n2];
        this.btn_border_color = new Color[n2];
        this.resize = new boolean[n2];
        this.border = new int[n2];
        this.border_thickness = new int[n2];
        this.border_shading = new int[n2];
        this.border_up = new int[n2];
        this.image = new Image[n2];
        this.image_x = new int[n2];
        this.image_y = new int[n2];
        this.image_name = new String[n2];
        this.sound_name = new String[n2];
        this.sound = new AudioClip[n2];
        this.link = new String[n2];
        this.frame = new String[n2];
        this.button_w = new int[this.num_buttons];
        this.button_h = new int[this.num_buttons];
        this.button_x = new int[this.num_buttons];
        this.button_y = new int[this.num_buttons];
        this.status = new String[n2];
        for (int i = 0; i < this.num_buttons; ++i) {
            final StringTokenizer stringTokenizer2 = new StringTokenizer(this.getParameter(Integer.toString(i)), "|");
            this.button_x[i] = Integer.parseInt(stringTokenizer2.nextToken());
            this.button_y[i] = Integer.parseInt(stringTokenizer2.nextToken());
            this.button_w[i] = Integer.parseInt(stringTokenizer2.nextToken());
            this.button_h[i] = Integer.parseInt(stringTokenizer2.nextToken());
            for (int j = 0; j < 3; ++j) {
                final int n3 = i * 3 + j;
                this.border[n3] = Integer.parseInt(stringTokenizer2.nextToken());
                this.border_thickness[n3] = Integer.parseInt(stringTokenizer2.nextToken());
                this.border_shading[n3] = Integer.parseInt(stringTokenizer2.nextToken());
                this.btn_border_color[n3] = new Color(Integer.parseInt(stringTokenizer2.nextToken(), 16));
                this.btn_color[n3] = new Color(Integer.parseInt(stringTokenizer2.nextToken(), 16));
                this.border_up[n3] = Integer.parseInt(stringTokenizer2.nextToken());
                this.sound_name[n3] = stringTokenizer2.nextToken();
                this.status[n3] = stringTokenizer2.nextToken();
                this.link[n3] = stringTokenizer2.nextToken();
                if (this.link[n3].equalsIgnoreCase(" ")) {
                    this.link[n3] = null;
                }
                this.frame[n3] = stringTokenizer2.nextToken();
                if (this.frame[n3].equalsIgnoreCase(" ")) {
                    this.frame[n3] = null;
                }
                this.fontMessages[n3] = Integer.parseInt(stringTokenizer2.nextToken());
                for (int k = 0; k < this.fontMessages[n3]; ++k) {
                    this.fontColor[n] = new Color(Integer.parseInt(stringTokenizer2.nextToken(), 16));
                    this.fontX[n] = Integer.parseInt(stringTokenizer2.nextToken());
                    this.fontY[n] = Integer.parseInt(stringTokenizer2.nextToken());
                    this.fontSize[n] = Integer.parseInt(stringTokenizer2.nextToken());
                    this.fontStyle[n] = Integer.parseInt(stringTokenizer2.nextToken());
                    this.font[n] = Integer.parseInt(stringTokenizer2.nextToken());
                    this.message[n] = stringTokenizer2.nextToken();
                    ++n;
                }
                this.image_name[n3] = stringTokenizer2.nextToken();
                this.image_x[n3] = Integer.parseInt(stringTokenizer2.nextToken());
                this.image_y[n3] = Integer.parseInt(stringTokenizer2.nextToken());
                if (stringTokenizer2.nextToken().equalsIgnoreCase("1")) {
                    this.resize[n3] = true;
                }
            }
        }
        if (this.bkimage_name.equalsIgnoreCase(" ")) {
            this.bkimage_name = null;
            this.bkimage = null;
        }
        else {
            this.loadimage(this.bkimage = this.getImage(this.getCodeBase(), this.bkimage_name));
        }
        for (int l = 0; l < n2; ++l) {
            if (this.image_name[l].equalsIgnoreCase(" ")) {
                this.image_name[l] = null;
            }
            else {
                this.loadimage(this.image[l] = this.getImage(this.getCodeBase(), this.image_name[l]));
            }
            if (this.sound_name[l].equalsIgnoreCase(" ")) {
                this.sound_name[l] = null;
            }
            else {
                this.sound[l] = this.getAudioClip(this.getCodeBase(), this.sound_name[l]);
            }
        }
        final char[] array = { this.getDocumentBase().toString().charAt(1), this.getDocumentBase().toString().charAt(2), ':' };
        if (array[0] == array[2] || array[1] == array[2] || (this.getDocumentBase().toString().startsWith("file") && !this.registered)) {
            this.registered = true;
        }
    }
    
    int find_mouse_button(final int n, final int n2, final int n3) {
        int i = 0;
        while (i < this.num_buttons + 1) {
            if (i == this.num_buttons) {
                this.showStatus("");
                break;
            }
            if (n >= this.button_x[i] && n < this.button_x[i] + this.button_w[i] && n2 >= this.button_y[i] && n2 < this.button_y[i] + this.button_h[i]) {
                if (n3 != 1 || i != this.last_button_move) {
                    this.super_event(i, n3);
                    break;
                }
                break;
            }
            else {
                ++i;
            }
        }
        return i;
    }
    
    void super_event(final int n, final int n2) {
        if (this.registered) {
            final int n3 = n * 3 + n2;
            this.offGraphics.drawImage(this.btn_offImage[n3], this.button_x[n], this.button_y[n], null);
            this.showStatus(this.status[n3]);
            this.repaint();
            if (this.sound[n3] != null) {
                this.sound[n3].play();
            }
            if (this.link[n3] == null) {
                return;
            }
            try {
                final URL url = new URL(this.getDocumentBase(), this.link[n3]);
                if (this.frame[n3] != null) {
                    this.getAppletContext().showDocument(url, this.frame[n3]);
                    return;
                }
                this.getAppletContext().showDocument(url);
                return;
            }
            catch (MalformedURLException ex) {
                return;
            }
        }
        try {
            this.getAppletContext().showDocument(new URL("http://www.siliconjoy.com/register.html"));
        }
        catch (MalformedURLException ex2) {}
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        this.find_mouse_button(n, n2, 2);
        return true;
    }
    
    public boolean mouseUp(final Event event, final int n, final int n2) {
        this.find_mouse_button(n, n2, 0);
        return true;
    }
    
    public boolean mouseMove(final Event event, final int n, final int n2) {
        if (this.registered) {
            final int find_mouse_button = this.find_mouse_button(n, n2, 1);
            if (this.last_button_move != find_mouse_button && this.last_button_move != this.num_buttons) {
                this.offGraphics.drawImage(this.btn_offImage[this.last_button_move * 3], this.button_x[this.last_button_move], this.button_y[this.last_button_move], null);
                this.repaint();
            }
            this.last_button_move = find_mouse_button;
        }
        return true;
    }
    
    public boolean mouseExit(final Event event, final int n, final int n2) {
        if (this.registered) {
            if (this.last_button_move != this.num_buttons) {
                this.offGraphics.drawImage(this.btn_offImage[this.last_button_move * 3], this.button_x[this.last_button_move], this.button_y[this.last_button_move], null);
                this.repaint();
                this.showStatus("");
            }
            this.last_button_move = this.num_buttons;
        }
        return true;
    }
    
    public void draw_button(final Graphics graphics, final int n, final int n2) {
        if (this.border[n2] == 0) {
            graphics.setColor(this.btn_color[n2]);
            graphics.fillRect(0, 0, this.button_w[n], this.button_h[n]);
        }
        else {
            graphics.drawImage(this.offImage, this.button_x[n] * -1, this.button_y[n] * -1, null);
        }
        final int red = this.btn_border_color[n2].getRed();
        final int green = this.btn_border_color[n2].getGreen();
        final int blue = this.btn_border_color[n2].getBlue();
        boolean b = true;
        boolean b2 = true;
        switch (this.border_up[n2]) {
            case 0: {
                b = true;
                b2 = false;
                break;
            }
            case 1: {
                b = false;
                b2 = true;
                break;
            }
            case 2: {
                b = true;
                b2 = true;
                break;
            }
            case 3: {
                b = false;
                b2 = false;
                break;
            }
        }
        for (int i = 0; i < this.border_thickness[n2]; ++i) {
            graphics.setColor(this.get_color(this.border_shading[n2], this.border_thickness[n2], red, green, blue, i, b));
            graphics.drawLine(i, i, this.button_w[n] - 1 - i, i);
            graphics.drawLine(i, i, i, this.button_h[n] - 1 - i);
            graphics.setColor(this.get_color(this.border_shading[n2], this.border_thickness[n2], red, green, blue, i, b2));
            graphics.drawLine(i, this.button_h[n] - 1 - i, this.button_w[n] - 1 - i, this.button_h[n] - 1 - i);
            graphics.drawLine(this.button_w[n] - 1 - i, i, this.button_w[n] - 1 - i, this.button_h[n] - 1 - i);
        }
        if (this.image[n2] != null) {
            graphics.drawImage(this.image[n2], this.image_x[n2], this.image_y[n2], null);
        }
        for (int j = 0; j < this.fontMessages[n2]; ++j) {
            int n3 = 0;
            if (this.fontStyle[this.fontIndex] == 1) {
                n3 = 2;
            }
            else if (this.fontStyle[this.fontIndex] == 2) {
                n3 = 1;
            }
            String s = "TimesRoman";
            switch (this.font[this.fontIndex]) {
                case 1: {
                    s = "Courier";
                    break;
                }
                case 2: {
                    s = "Helvetica";
                    break;
                }
            }
            graphics.setFont(new Font(s, n3, this.fontSize[this.fontIndex]));
            graphics.setColor(this.fontColor[this.fontIndex]);
            graphics.drawString(this.message[this.fontIndex], this.fontX[this.fontIndex], this.fontY[this.fontIndex]);
            ++this.fontIndex;
        }
    }
    
    Color get_color(final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final boolean b) {
        Color color;
        if (b) {
            color = new Color(Math.max(0, Math.min(255, n3 + (n * n2 - n6 * n))), Math.max(0, Math.min(255, n4 + (n * n2 - n6 * n))), Math.max(0, Math.min(255, n5 + (n * n2 - n6 * n))));
        }
        else {
            color = new Color(Math.min(255, Math.max(0, n3 - (n * n2 - n6 * n))), Math.min(255, Math.max(0, n4 - (n * n2 - n6 * n))), Math.min(255, Math.max(0, n5 - (n * n2 - n6 * n))));
        }
        return color;
    }
    
    public void update(final Graphics graphics) {
        final Dimension size = this.size();
        if (this.offGraphics == null || size.width != this.offDimension.width || size.height != this.offDimension.height) {
            this.offDimension = size;
            this.offImage = this.createImage(size.width, size.height);
            this.offGraphics = this.offImage.getGraphics();
            if (!this.registered) {
                this.offGraphics.setColor(Color.black);
                this.offGraphics.fillRect(0, 0, this.w, this.h);
                this.offGraphics.setFont(new Font("Helvetica", 0, 10));
                this.offGraphics.setColor(Color.white);
                this.offGraphics.drawString("       You have the Unregistered", 0, 10);
                this.offGraphics.drawString("     version of the Button Factory", 0, 20);
                this.offGraphics.drawString("    You need the registered version", 0, 30);
                this.offGraphics.drawString("for this Applet to work on the internet", 0, 40);
                this.offGraphics.setColor(Color.yellow);
                this.offGraphics.drawString("Click HERE for registration instructions", 0, 50);
            }
            else {
                this.offGraphics.setColor(this.bkcolor);
                this.offGraphics.fillRect(0, 0, this.w, this.h);
                if (this.bkimage != null) {
                    if (this.tile) {
                        for (int i = 0; i < this.h / this.bkimage.getHeight(this) + 1; ++i) {
                            for (int j = 0; j < this.w / this.bkimage.getWidth(this) + 1; ++j) {
                                this.offGraphics.drawImage(this.bkimage, j * this.bkimage.getWidth(this), i * this.bkimage.getHeight(this), null);
                            }
                        }
                    }
                    else if (this.bk_resize) {
                        this.bkimage = this.resize(this.bkimage, this.w - this.BkThickness * 2, this.h - this.BkThickness * 2);
                        this.bk_x = this.BkThickness;
                        this.bk_y = this.BkThickness;
                    }
                    this.offGraphics.drawImage(this.bkimage, this.bk_x, this.bk_y, null);
                    this.bkimage = null;
                }
                boolean b = true;
                boolean b2 = true;
                switch (this.BkBorderState) {
                    case 0: {
                        b = true;
                        b2 = false;
                        break;
                    }
                    case 1: {
                        b = false;
                        b2 = true;
                        break;
                    }
                    case 2: {
                        b = true;
                        b2 = true;
                        break;
                    }
                    case 3: {
                        b = false;
                        b2 = false;
                        break;
                    }
                }
                final int red = this.BkBorderColor.getRed();
                final int green = this.BkBorderColor.getGreen();
                final int blue = this.BkBorderColor.getBlue();
                for (int k = 0; k < this.BkThickness; ++k) {
                    this.offGraphics.setColor(this.get_color(this.BkShading, this.BkThickness, red, green, blue, k, b));
                    this.offGraphics.drawLine(k, k, this.w - 1 - k, k);
                    this.offGraphics.drawLine(k, k, k, this.h - 1 - k);
                    this.offGraphics.setColor(this.get_color(this.BkShading, this.BkThickness, red, green, blue, k, b2));
                    this.offGraphics.drawLine(k, this.h - 1 - k, this.w - 1 - k, this.h - 1 - k);
                    this.offGraphics.drawLine(this.w - 1 - k, k, this.w - 1 - k, this.h - 1 - k);
                }
                for (int l = 0; l < this.num_buttons; ++l) {
                    for (int n = 0; n < 3; ++n) {
                        final int n2 = l * 3 + n;
                        this.btn_offImage[n2] = this.createImage(this.button_w[l], this.button_h[l]);
                        this.btn_offGraphics[n2] = this.btn_offImage[n2].getGraphics();
                        if (this.resize[n2] && this.image[n2] != null) {
                            this.image[n2] = this.resize(this.image[n2], this.button_w[l] - this.border_thickness[n2] * 2, this.button_h[l] - this.border_thickness[n2] * 2);
                            this.image_x[n2] = this.border_thickness[n2];
                            this.image_y[n2] = this.border_thickness[n2];
                        }
                        this.draw_button(this.btn_offGraphics[n2], l, n2);
                        this.paintFrame(this.btn_offGraphics[n2]);
                        this.image[n2] = null;
                    }
                }
            }
            for (int n3 = 0; n3 < this.num_buttons; ++n3) {
                this.offGraphics.drawImage(this.btn_offImage[n3 * 3], this.button_x[n3], this.button_y[n3], null);
            }
        }
        this.paintFrame(this.offGraphics);
        graphics.drawImage(this.offImage, 0, 0, null);
    }
    
    public void destroy() {
        for (int i = 0; i < this.num_buttons * 3; ++i) {
            this.btn_offImage[i] = null;
        }
    }
    
    void loadimage(final Image image) {
        final MediaTracker mediaTracker = new MediaTracker(this);
        mediaTracker.addImage(image, 0);
        try {
            mediaTracker.waitForAll();
        }
        catch (InterruptedException ex) {
            this.stop();
        }
    }
    
    public void stop() {
        this.animationThread = null;
    }
    
    public void start() {
        if (this.animationThread == null) {
            (this.animationThread = new Thread(this)).start();
            Thread.currentThread().setPriority(10);
        }
    }
    
    public void run() {
        while (this.animationThread != null) {
            try {
                Thread.sleep(this.delayTime);
            }
            catch (InterruptedException ex) {}
            if (this.delayTime == 0) {
                this.delayTime = 4000;
            }
            this.repaint();
        }
    }
    
    public void paint(final Graphics graphics) {
        if (this.offImage != null) {
            graphics.drawImage(this.offImage, 0, 0, null);
        }
    }
    
    public void paintFrame(final Graphics graphics) {
    }
    
    Image resize(final Image image, final int n, final int n2) {
        int n3 = 0;
        final int width = image.getWidth(this);
        final int height = image.getHeight(this);
        final int[] array = new int[height * width];
        final PixelGrabber pixelGrabber = new PixelGrabber(image, 0, 0, width, height, array, 0, width);
        try {
            pixelGrabber.grabPixels();
        }
        catch (InterruptedException ex) {}
        final int[] array2 = new int[n2 * n];
        final double abs = Math.abs(width / n);
        final double abs2 = Math.abs(height / n2);
        for (int i = 0; i < n2; ++i) {
            for (int j = 0; j < n; ++j) {
                int n4 = (int)Math.round(j * abs + Math.round(i * abs2) * width);
                if (n4 < 0) {
                    n4 = 0;
                }
                if (n4 < width * height) {
                    array2[n3] = array[n4];
                }
                else {
                    array2[n3] = array[width * height - 1];
                }
                ++n3;
            }
        }
        return this.createImage(new MemoryImageSource(n, n2, array2, 0, n));
    }
}

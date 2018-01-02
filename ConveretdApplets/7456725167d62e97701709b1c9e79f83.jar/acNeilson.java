import java.awt.image.ImageObserver;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Event;
import java.awt.Component;
import java.awt.Image;
import java.awt.MediaTracker;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class acNeilson extends Applet implements Runnable
{
    Thread thread;
    MediaTracker tracker;
    Image numbers;
    int number_frames;
    int number_width;
    int number_height;
    int how_many;
    int delay;
    String value;
    boolean first;
    int state;
    
    public void init() {
        this.tracker = new MediaTracker(this);
        try {
            this.numbers = this.getImage(this.getCodeBase(), "../Images/acNeilson.gif");
            this.tracker.addImage(this.numbers, 0);
            this.number_frames = Integer.parseInt(this.getParameter("NUMBER_FRAMES"));
            this.number_width = Integer.parseInt(this.getParameter("NUMBER_WIDTH"));
            this.number_height = Integer.parseInt(this.getParameter("NUMBER_HEIGHT"));
            this.how_many = Integer.parseInt(this.getParameter("HOW_MANY"));
            this.delay = Integer.parseInt(this.getParameter("DELAY"));
            this.value = this.getParameter("VALUE");
            if (this.value == null) {
                this.value = String.valueOf((long)(Math.random() * (long)Math.pow(10.0, this.how_many)));
            }
        }
        catch (Exception ex) {
            return;
        }
        this.resize(this.how_many * this.number_width, this.number_height);
        (this.thread = new Thread(this)).start();
    }
    
    public void stop() {
        if (this.thread != null) {
            this.thread.stop();
            this.thread = null;
        }
    }
    
    public void run() {
        this.tracker.checkAll(true);
        while (true) {
            this.repaint();
            try {
                Thread.sleep(this.delay);
            }
            catch (Exception ex) {}
        }
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        if (this.thread != null) {
            this.thread.stop();
            this.thread = null;
        }
        else {
            (this.thread = new Thread(this)).start();
        }
        return true;
    }
    
    public boolean keyDown(final Event event, final int n) {
        switch (n) {
            case 43: {
                this.delay -= 100;
                if (this.delay < 10) {
                    this.delay = 10;
                    break;
                }
                break;
            }
            case 45: {
                this.delay += 100;
                break;
            }
            case 48: {
                this.value = String.valueOf(0);
                this.state = 0;
                break;
            }
            default: {
                this.value = String.valueOf((long)(Math.random() * (long)Math.pow(10.0, this.how_many)));
                this.state = 0;
                break;
            }
        }
        return true;
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void paint(final Graphics graphics) {
        if (!this.tracker.checkAll()) {
            graphics.setColor(Color.black);
            graphics.fillRect(0, 0, this.how_many * this.number_width, this.number_height);
            return;
        }
        final int n = this.how_many - this.value.length();
        for (int i = 0; i < n; ++i) {
            final Graphics create = graphics.create(i * this.number_width, 0, this.number_width, this.number_height);
            create.drawImage(this.numbers, 0, 0, this);
            create.dispose();
        }
        int n2 = 1;
        for (int j = this.value.length() - 1; j >= 0; --j) {
            final char c = (char)(this.value.charAt(j) - '0');
            final Graphics create2 = graphics.create((n + j) * this.number_width, 0, this.number_width, this.number_height);
            if (n2 != 0) {
                switch (this.state) {
                    case 0: {
                        if (c == '\0') {
                            create2.drawImage(this.numbers, 0, -(9 * this.number_height + this.number_height / 2), this);
                        }
                        create2.drawImage(this.numbers, 0, -(c * this.number_height - this.number_height / 2), this);
                        break;
                    }
                    case 1: {
                        create2.drawImage(this.numbers, 0, -(c * this.number_height), this);
                        break;
                    }
                    case 2: {
                        create2.drawImage(this.numbers, 0, -(c * this.number_height + this.number_height / 2), this);
                        if (c == '\t') {
                            create2.drawImage(this.numbers, 0, -(0 * this.number_height - this.number_height / 2), this);
                            break;
                        }
                        break;
                    }
                }
            }
            else {
                create2.drawImage(this.numbers, 0, -(c * this.number_height), this);
            }
            create2.dispose();
            if (c != '\t') {
                n2 = 0;
            }
        }
        if (this.state >= 2) {
            this.state = 0;
            this.value = String.valueOf(Long.parseLong(this.value) + 1L);
        }
        ++this.state;
    }
    
    public acNeilson() {
        this.first = true;
    }
}

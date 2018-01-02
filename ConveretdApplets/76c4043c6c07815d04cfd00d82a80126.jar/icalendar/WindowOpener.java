// 
// Decompiled by Procyon v0.5.30
// 

package icalendar;

import java.awt.event.MouseEvent;
import java.applet.AppletContext;
import java.applet.AudioClip;
import java.net.URL;
import java.awt.event.MouseListener;

public class WindowOpener implements MouseListener, Runnable
{
    Window front_;
    Window back_;
    Window content_;
    int x_;
    int y_;
    int width_;
    int height_;
    int curr_width_;
    int curr_height_;
    int open_dir_;
    boolean can_open_;
    URL link_;
    AudioClip audio_;
    boolean opening_;
    static final int OPEN_LEFT = 1;
    static final int OPEN_RIGHT = 2;
    static final int OPEN_UP = 3;
    static final int OPEN_DOWN = 4;
    static int totStep_;
    static int delay_;
    static AudioClip openwin_;
    static AudioClip noopenwin_;
    static AppletContext ac_;
    static String target_;
    int step_;
    boolean can_open;
    
    public WindowOpener(final Window front_, final Window back_, final Window content_, final int x_, final int y_, final int n, final int n2, final int open_dir_, final boolean can_open_, final URL link_, final AudioClip audio_) {
        this.step_ = 0;
        this.can_open = true;
        this.front_ = front_;
        this.back_ = back_;
        this.content_ = content_;
        this.x_ = x_;
        this.y_ = y_;
        this.width_ = n;
        this.height_ = n2;
        this.curr_width_ = n;
        this.curr_height_ = n2;
        this.open_dir_ = open_dir_;
        this.can_open_ = can_open_;
        this.link_ = link_;
        this.audio_ = audio_;
    }
    
    public static void setTotStep(final int totStep_) {
        WindowOpener.totStep_ = totStep_;
    }
    
    public static int getTotStep() {
        return WindowOpener.totStep_;
    }
    
    public static void setDelay(final int delay_) {
        WindowOpener.delay_ = delay_;
    }
    
    public static int getDelay() {
        return WindowOpener.delay_;
    }
    
    public static void setOpenWin(final AudioClip openwin_) {
        WindowOpener.openwin_ = openwin_;
    }
    
    public static AudioClip getOpenWin() {
        return WindowOpener.openwin_;
    }
    
    public static void setNoOpenWin(final AudioClip noopenwin_) {
        WindowOpener.noopenwin_ = noopenwin_;
    }
    
    public static AudioClip getNoOpenWin() {
        return WindowOpener.noopenwin_;
    }
    
    public static void setShow(final AppletContext ac_, final String target_) {
        WindowOpener.ac_ = ac_;
        WindowOpener.target_ = target_;
    }
    
    public int getX() {
        return this.x_;
    }
    
    public int getY() {
        return this.y_;
    }
    
    public int getWidth() {
        return this.width_;
    }
    
    public int getHeight() {
        return this.height_;
    }
    
    public void setBounds(final Window window) {
        int width_;
        int height_;
        if (window == this.content_) {
            width_ = this.width_;
            height_ = this.height_;
        }
        else {
            final double cos = Math.cos(1.5707963267948966 / WindowOpener.totStep_ * this.step_);
            final boolean b = this.open_dir_ == 1 || this.open_dir_ == 2;
            final double n = b ? cos : 1.0;
            final double n2 = b ? 1.0 : cos;
            width_ = (int)(this.width_ * n);
            height_ = (int)(this.height_ * n2);
        }
        int curr_width_ = 0;
        int curr_height_ = 0;
        if (window == this.front_) {
            switch (this.open_dir_) {
                case 2: {
                    curr_width_ = this.curr_width_ - width_;
                    break;
                }
                case 4: {
                    curr_height_ = this.curr_height_ - height_;
                    break;
                }
            }
        }
        int n3 = 1;
        int n4 = 1;
        if (window == this.back_) {
            switch (this.open_dir_) {
                case 1: {
                    n3 = -1;
                    curr_width_ = width_;
                    break;
                }
                case 2: {
                    n3 = -1;
                    curr_width_ = this.curr_width_;
                    break;
                }
                case 3: {
                    n4 = -1;
                    curr_height_ = height_;
                    break;
                }
                case 4: {
                    n4 = -1;
                    curr_height_ = this.curr_height_;
                    break;
                }
            }
        }
        window.setBounds(this.x_ + curr_width_, this.y_ + curr_height_, width_ * n3, height_ * n4);
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
        if (mouseEvent.getComponent() == this.front_) {
            this.opening_ = true;
            new Thread(this).start();
        }
        if (mouseEvent.getComponent() == this.back_) {
            this.opening_ = false;
            new Thread(this).start();
        }
        if (mouseEvent.getComponent() == this.content_) {
            WindowOpener.ac_.showDocument(this.link_, WindowOpener.target_);
        }
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
    }
    
    protected void step() {
        this.setBounds(this.front_);
        this.setBounds(this.back_);
        try {
            Thread.sleep(WindowOpener.delay_);
        }
        catch (InterruptedException ex) {}
    }
    
    public synchronized void move() {
        if (!this.opening_) {
            this.audio_.stop();
            while (this.step_ > 0) {
                --this.step_;
                this.step();
            }
        }
        if (this.opening_) {
            if (this.can_open_) {
                WindowOpener.openwin_.play();
                while (this.step_ < WindowOpener.totStep_ * 2) {
                    ++this.step_;
                    this.step();
                }
                this.audio_.play();
            }
            else {
                WindowOpener.noopenwin_.play();
            }
        }
    }
    
    public void run() {
        this.move();
    }
}

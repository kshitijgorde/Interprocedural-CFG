// 
// Decompiled by Procyon v0.5.30
// 

package com.fluendo.player;

import java.awt.event.MouseEvent;
import java.awt.Image;
import java.awt.image.ImageObserver;
import com.fluendo.plugin.AudioSinkJ2;
import java.awt.Stroke;
import java.awt.BasicStroke;
import java.awt.Paint;
import java.awt.Graphics2D;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.util.Enumeration;
import java.util.Vector;
import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.awt.Component;

public class Status extends Component implements MouseListener, MouseMotionListener
{
    private static final long serialVersionUID = 1L;
    private int bufferPercent;
    private boolean buffering;
    private String message;
    private Rectangle rectangle;
    private Component component;
    private Font font;
    private boolean haveAudio;
    private boolean havePercent;
    private boolean seekable;
    private static final int NONE = 0;
    private static final int BUTTON1 = 1;
    private static final int BUTTON2 = 2;
    private static final int BUTTON3 = 5;
    private static final int SEEKER = 3;
    private static final int HEIGHT_DOWN = 60;
    private static final int BUTTON_BORDER_THICKNESS = 1;
    private static final int LENGTH_TOP_BUTTON = 40;
    private static final int LENGTH_TOP_COMBO_BOX = 10;
    private static final int TEXT_DOWN = 13;
    private static final int START_TEXT_COMBOBOX_X = 15;
    private static final Color BUTTON_COLOR_DEFAULT;
    private static final Color BUTTON_COLOR_MOUSE_UP;
    private static final Color BUTTON_COLOR_ICON_DEFAULT;
    private static final Color BUTTON_COLOR_ICON_MOUSE_UP;
    private static final Color BUTTON_COLOR_TEXT_DEFAULT;
    private static final Color BUTTON_COLOR_TEXT_MOUSE_UP;
    private int clicked;
    private Color button1Color;
    private Color button2Color;
    private Color button3Color;
    private Color darkBlue;
    private Color myButtonBorderColor;
    private static final int SEEK_END = 60;
    public static final int STATE_STOPPED = 0;
    public static final int STATE_PAUSED = 1;
    public static final int STATE_PLAYING = 2;
    public static final int STATE_REPLAYING = 3;
    private int state;
    private double position;
    private long time;
    private double duration;
    private String speaker;
    private Vector listeners;
    private CortadoPipeline pipeline;
    private SimplePost myPost;
    private long time20;
    private long time0;
    private int x_Button1;
    private int y_Button1;
    private int w_Button1;
    private int h_Button1;
    private int x_Button2;
    private int y_Button2;
    private int w_Button2;
    private int h_Button2;
    private int x_Button3;
    private int y_Button3;
    private int w_Button3;
    private int h_Button3;
    private Color buttonColorIcon1;
    private Color buttonColorIcon2;
    private Color buttonColorIcon3;
    private Color buttonColorText3;
    
    public Status(final Component comp, final CortadoPipeline pipeline) {
        this.font = new Font("SansSerif", 0, 10);
        this.clicked = 0;
        this.state = 0;
        this.position = 0.0;
        this.time = 0L;
        this.speaker = "\u0000\u0000\u0000\u0000\u0000\u00ef\u0000\u0000\u00efU\u0017\u001e\u0000\u0000\u0000\u0000\u00ef\u00ef\u0000\u0000\u0000\u00efU\u0018\u0000\u0000\u0000\u00ef\u0000\u00ef\u0000\u00ef\u0000\u0000\u00ef\u0013\u00ef\u00ef\u00ef\u0000\u001c\u00ef\u0000Z\u00ef\u0000\u00ef\\\u00ef\u0000)+F\u00ef\u0000\u0000\u00ef\u0000\u00efr\u00efIbz\u0091\u00ef\u0000\u0000\u00ef\u0000\u00efr\u00ef\u00ef\u00ef¾\u00d3\u00ef\u0000Z\u00ef\u0000\u00ef\\\u0000\u0000\u0000\u00ef\u00ef\u00ef\u0000\u00ef\u0000\u0000\u00ef\u0000\u0000\u0000\u0000\u0000\u00ef\u00ef\u0000\u0000\u0000\u00ef\\\u0000\u0000\u0000\u0000\u0000\u0000\u00ef\u0000\u0000\u00ef\\\u0000\u0000";
        this.listeners = new Vector();
        this.time20 = 0L;
        this.time0 = 0L;
        this.pipeline = pipeline;
        final int[] pixels = new int[120];
        this.component = comp;
        for (int i = 0; i < 120; ++i) {
            pixels[i] = (0xFF000000 | this.speaker.charAt(i) << 16 | this.speaker.charAt(i) << 8 | this.speaker.charAt(i));
        }
        this.darkBlue = new Color(18, 19, 112);
        this.myButtonBorderColor = Color.BLACK;
        this.button1Color = Status.BUTTON_COLOR_DEFAULT;
        this.button2Color = Status.BUTTON_COLOR_DEFAULT;
        this.button3Color = Status.BUTTON_COLOR_DEFAULT;
        this.buttonColorIcon1 = Status.BUTTON_COLOR_ICON_DEFAULT;
        this.buttonColorIcon2 = Status.BUTTON_COLOR_ICON_DEFAULT;
        this.buttonColorIcon3 = Status.BUTTON_COLOR_ICON_DEFAULT;
        this.buttonColorText3 = Status.BUTTON_COLOR_TEXT_DEFAULT;
    }
    
    private void setButtonsPosition() {
        final int rectangle2Height = this.rectangle.height - 60;
        this.x_Button1 = 20;
        this.y_Button1 = 40;
        this.w_Button1 = rectangle2Height - 2;
        this.h_Button1 = rectangle2Height - 2;
        this.x_Button2 = this.x_Button1 + 20 + rectangle2Height;
        this.y_Button2 = 40;
        this.w_Button2 = rectangle2Height - 2;
        this.h_Button2 = rectangle2Height - 2;
        this.x_Button3 = this.x_Button2 + 20 + rectangle2Height;
        this.y_Button3 = 40;
        this.w_Button3 = rectangle2Height + 3 + 75;
        this.h_Button3 = rectangle2Height - 2;
    }
    
    public void addStatusListener(final StatusListener l) {
        this.listeners.addElement(l);
    }
    
    public void removeStatusListener(final StatusListener l) {
        this.listeners.remove(l);
    }
    
    public void notifyNewState(final int newState) {
        final Enumeration e = this.listeners.elements();
        while (e.hasMoreElements()) {
            e.nextElement().newState(newState);
        }
    }
    
    public void notifySeek(final double position) {
        final Enumeration e = this.listeners.elements();
        while (e.hasMoreElements()) {
            e.nextElement().newSeek(position);
        }
    }
    
    public void update(final Graphics g) {
        this.paint(g);
    }
    
    private void paintBox(final Graphics g) {
        g.setColor(this.darkBlue);
        g.fillRect(0, 0, this.rectangle.width - 1, this.rectangle.height - 1);
        g.setColor(Color.WHITE);
        final Paint paint = new GradientPaint(4.0f, 4.0f, Color.WHITE, 40.0f, this.rectangle.height - 330, this.darkBlue, true);
        final Graphics2D g2 = (Graphics2D)g;
        g2.setPaint(paint);
        g.fillRect(4, 4, this.rectangle.width - 8, this.rectangle.height - 8);
    }
    
    private void paintPercent(final Graphics g) {
        if (this.havePercent) {
            this.createComboBox(g);
            g.setColor(Color.white);
            g.drawString("" + this.bufferPercent + "%", this.rectangle.width - 38, 23);
        }
    }
    
    private void paintPlayPause(final Graphics g) {
        final int x = this.x_Button1;
        final int y = this.y_Button1;
        final int h = this.h_Button1;
        final int w = this.w_Button1;
        g.setColor(this.button1Color);
        g.fillRect(x + 1, y + 1, w - 2, h - 2);
        g.setColor(this.myButtonBorderColor);
        final Graphics2D g2 = (Graphics2D)g;
        g2.setStroke(new BasicStroke(3.0f));
        g.drawRect(x, y, w, h);
        if (this.state == 2) {
            g.setColor(this.buttonColorIcon1);
            g.fillRect((int)(w * 0.25) + x, (int)(h * 0.25) + y, (int)(w * 0.2), (int)(h * 0.5));
            g.fillRect((int)(w * 0.6) + x, (int)(h * 0.25) + y, (int)(w * 0.2), (int)(h * 0.5));
        }
        else {
            final int[] triangleX = { (int)(w * 0.3) + x, (int)(w * 0.3) + x, (int)(w * 0.8) + x };
            final int[] triangleY = { (int)(w * 0.2) + y, (int)(w * 0.8) + y, (int)(w * 0.5) + y };
            g.setColor(this.buttonColorIcon1);
            g.fillPolygon(triangleX, triangleY, 3);
        }
    }
    
    private void paintStop(final Graphics g) {
        final int x = this.x_Button2;
        final int y = this.y_Button2;
        final int h = this.h_Button2;
        final int w = this.w_Button2;
        g.setColor(this.button2Color);
        g.fillRect(x + 1, y + 1, w - 2, h - 2);
        g.setColor(this.myButtonBorderColor);
        final Graphics2D g2 = (Graphics2D)g;
        g2.setStroke(new BasicStroke(3.0f));
        g.drawRect(x, y, w, h);
        g.setColor(this.buttonColorIcon2);
        g.fillRect(x + (int)(w * 0.25), (int)(w * 0.27) + y, (int)(w * 0.5), (int)(w * 0.5));
    }
    
    private void paintReplay(final Graphics g) {
        final int x = this.x_Button3;
        final int y = this.y_Button3;
        final int h = this.h_Button3;
        int w = this.w_Button3;
        g.setColor(this.button3Color);
        g.fillRect(x + 1, y + 1, w - 2, h - 2);
        g.setColor(this.myButtonBorderColor);
        Graphics2D g2 = (Graphics2D)g;
        g2.setStroke(new BasicStroke(3.0f));
        g.drawRect(x, y, w, h);
        g.setColor(Color.white);
        w -= 75;
        final int[] triangleX = { (int)(w * 0.7) + x, (int)(w * 0.7) + x, (int)(w * 0.2) + x };
        final int[] triangleY = { (int)(w * 0.2) - 1 + y, (int)(w * 0.6) - 1 + y, (int)(w * 0.4) - 1 + y };
        w += 75;
        final Paint paint = new GradientPaint(4.0f, 4.0f, Color.RED, 4.0f, this.rectangle.height - 8, Color.RED, true);
        g2 = (Graphics2D)g;
        g2.setPaint(paint);
        g.fillPolygon(triangleX, triangleY, triangleX.length);
        final int x_0 = x + 25;
        final int y_0 = y + 20;
        final int[] ypoints = { y_0, y_0, y_0 + 10, y_0 + 30, y_0 + 30, y_0 + 15, y_0 + 10, y_0 + 10 };
        final int[] xpoints = { x_0, 90 + x_0, 100 + x_0, 100 + x_0, 90 + x_0, 90 + x_0, 85 + x_0, x_0 };
        g.fillPolygon(xpoints, ypoints, xpoints.length);
        g.setColor(this.buttonColorText3);
        final Font oldFont = g.getFont();
        final Font newFont = new Font(oldFont.getFamily(), oldFont.getStyle() | 0x1, oldFont.getSize() + 3);
        g.setFont(newFont);
        g.drawString("Instant Replay", x + 10, y - 88 + w);
        g.setFont(oldFont);
    }
    
    private void paintMessage(final Graphics g, final int pos) {
        if (this.message != null) {
            this.createComboBox(g);
            g.setColor(Color.white);
            g.drawString(this.message, 15, 23);
        }
    }
    
    private void paintBuffering(final Graphics g, final int pos) {
        System.out.println("Buffering, please wait...");
        this.createComboBox(g);
        g.setColor(Color.white);
        g.drawString("Buffering, please wait...", 15, 23);
    }
    
    private void paintPlaying(final Graphics g, final int pos) {
        this.createComboBox(g);
        g.setColor(Color.white);
        g.drawString("Playing LIVE", 15, 23);
    }
    
    private void paintRePlaying(final Graphics g, final int pos) {
        final AudioSinkJ2 audioSinkJ2 = (AudioSinkJ2)this.pipeline.audiosink;
        if (audioSinkJ2.isLiveStream()) {
            this.notifyNewState(this.state = 2);
        }
        else {
            this.createComboBox(g);
            g.setColor(Color.white);
            g.drawString("Replaying " + this.pipeline.getRePlayTime() + "s.", 15, 23);
        }
    }
    
    private void createComboBox(final Graphics g) {
        final int h = 20;
        g.setColor(this.myButtonBorderColor);
        g.fillRect(10, 10, this.rectangle.width - 20, h);
        g.setColor(new Color(65, 65, 189));
        g.fillRect(11, 11, this.rectangle.width - 20 - 2, h - 2);
    }
    
    private void paintTime(final Graphics g) {
        if (this.time < 0L) {
            return;
        }
        final long sec = this.time % 60L;
        long min = this.time / 60L;
        final long hour = min / 60L;
        min %= 60L;
        this.rectangle = this.getBounds();
        this.setButtonsPosition();
        final int end = this.rectangle.width - 50;
        g.setColor(Color.white);
        g.drawString("" + hour + ":" + ((min < 10L) ? ("0" + min) : ("" + min)) + ":" + ((sec < 10L) ? ("0" + sec) : ("" + sec)), end, 23);
    }
    
    private void paintTimeRePlay(final Graphics g) {
        long time2 = this.time - this.time20;
        time2 = this.pipeline.getRePlayTime() - time2;
        if (time2 < 0L) {
            return;
        }
        final long sec = time2 % 60L;
        long min = time2 / 60L;
        final long hour = min / 60L;
        min %= 60L;
        this.rectangle = this.getBounds();
        this.setButtonsPosition();
        final int end = this.rectangle.width - 50;
        g.setColor(Color.white);
        g.drawString("" + hour + ":" + ((min < 10L) ? ("0" + min) : ("" + min)) + ":" + ((sec < 10L) ? ("0" + sec) : ("" + sec)), end, 23);
    }
    
    private void paintFoot(final Graphics g) {
        g.setColor(Color.WHITE);
        g.drawString("All Rights Reserved - Copyright TRZ Sports Services.", 10, this.rectangle.height - 8);
    }
    
    public void paint(final Graphics g) {
        if (!this.isVisible()) {
            return;
        }
        this.rectangle = this.getBounds();
        this.setButtonsPosition();
        final Image img = this.component.createImage(this.rectangle.width, this.rectangle.height);
        if (img == null) {
            return;
        }
        final Graphics g2 = img.getGraphics();
        if (g2 == null) {
            return;
        }
        g2.setFont(this.font);
        this.paintBox(g2);
        if (this.seekable) {
            this.paintPlayPause(g2);
            this.paintStop(g2);
            this.paintReplay(g2);
            if (this.buffering) {
                this.paintPercent(g2);
                this.paintBuffering(g2, 2);
            }
            else if (this.state == 0) {
                this.paintMessage(g2, 2);
            }
            else if (this.state == 3) {
                this.paintRePlaying(g2, 2);
                this.paintTimeRePlay(g2);
            }
            else {
                this.paintPlaying(g2, 2);
                this.paintTime(g2);
            }
        }
        else if (this.buffering) {
            this.paintBuffering(g2, 2);
            this.paintPercent(g2);
        }
        else {
            this.paintMessage(g2, 2);
            this.paintTime(g2);
        }
        this.paintFoot(g2);
        g.drawImage(img, this.rectangle.x, this.rectangle.y, null);
        img.flush();
    }
    
    public void setBufferPercent(final boolean buffering, final int bp) {
        this.buffering = buffering;
        this.bufferPercent = bp;
        this.component.repaint();
    }
    
    public void setTime(final double seconds) {
        if (this.clicked == 0) {
            if (this.time0 == 0L) {
                this.time0 = (long)seconds;
            }
            this.time = (long)seconds - this.time0;
            this.component.repaint();
        }
    }
    
    public void setDuration(final double seconds) {
        this.duration = seconds;
        this.component.repaint();
    }
    
    public void setMessage(final String m) {
        this.message = m;
        this.component.repaint();
    }
    
    public void setHaveAudio(final boolean a) {
        this.haveAudio = a;
        this.component.repaint();
    }
    
    public void setHavePercent(final boolean p) {
        this.havePercent = p;
        this.component.repaint();
    }
    
    public void setSeekable(final boolean s) {
        this.seekable = s;
        this.component.repaint();
    }
    
    public void setState(final int aState) {
        this.state = aState;
        this.component.repaint();
    }
    
    private boolean intersectButton1(final MouseEvent e) {
        return this.rectangle != null && e.getX() >= this.x_Button1 && e.getX() <= this.x_Button1 + this.w_Button1 && e.getY() >= this.y_Button1 && e.getY() <= this.y_Button1 + this.h_Button1;
    }
    
    private boolean intersectButton2(final MouseEvent e) {
        return this.rectangle != null && e.getX() >= this.x_Button2 && e.getX() <= this.x_Button2 + this.w_Button2 && e.getY() >= this.y_Button2 && e.getY() <= this.y_Button2 + this.h_Button2;
    }
    
    private boolean intersectButton3(final MouseEvent e) {
        return this.rectangle != null && e.getX() >= this.x_Button3 && e.getX() <= this.x_Button3 + this.w_Button3 && e.getY() >= this.y_Button3 && e.getY() <= this.y_Button3 + this.h_Button3;
    }
    
    private int findComponent(final MouseEvent e) {
        if (this.intersectButton1(e)) {
            return 1;
        }
        if (this.intersectButton2(e)) {
            return 2;
        }
        if (this.intersectButton3(e)) {
            return 5;
        }
        return 0;
    }
    
    public void mouseClicked(final MouseEvent e) {
    }
    
    public void mouseEntered(final MouseEvent e) {
    }
    
    public void mouseExited(final MouseEvent e) {
    }
    
    public void mousePressed(final MouseEvent e) {
        if (this.seekable) {
            e.translatePoint(-1, -1);
            this.clicked = this.findComponent(e);
        }
    }
    
    public void mouseReleased(final MouseEvent e) {
        if (this.seekable) {
            e.translatePoint(-1, -1);
            int comp = this.findComponent(e);
            if (this.clicked != comp) {
                if (this.clicked != 3) {
                    return;
                }
                comp = this.clicked;
            }
            switch (comp) {
                case 1: {
                    if (this.state == 3) {
                        final AudioSinkJ2 audioSinkJ2 = (AudioSinkJ2)this.pipeline.audiosink;
                        if (this.state != 0) {
                            audioSinkJ2.setLiveStreaming(true);
                        }
                        this.notifyNewState(this.state = 2);
                        break;
                    }
                    if (this.state == 2) {
                        this.notifyNewState(this.state = 1);
                        break;
                    }
                    this.notifyNewState(this.state = 2);
                    break;
                }
                case 2: {
                    this.notifyNewState(this.state = 0);
                    break;
                }
                case 5: {
                    System.out.println("Instant Replay");
                    final AudioSinkJ2 audioSinkJ2 = (AudioSinkJ2)this.pipeline.audiosink;
                    if (this.state != 0) {
                        audioSinkJ2.setLiveStreaming(false);
                        this.time20 = this.time;
                        this.notifyNewState(this.state = 3);
                    }
                    System.out.println("PHP Post tried to run...");
                    try {
                        SimplePost.do_post("http://stan.teamline.cc/post.html", "value", "clicked!");
                        System.out.println("PHP was run!");
                    }
                    catch (Exception ex) {
                        System.out.println("My Post Failed! Error was: " + ex);
                    }
                    break;
                }
            }
            this.clicked = 0;
            this.component.repaint();
        }
    }
    
    public void mouseDragged(final MouseEvent e) {
        if (this.seekable) {
            e.translatePoint(-1, -1);
            if (this.clicked == 3) {
                final int end = this.rectangle.width - 60 - this.rectangle.height * 2 - 110;
                final double pos = (e.getX() - (this.rectangle.height * 2 + 110)) / end;
                if (pos < 0.0) {
                    this.position = 0.0;
                }
                else if (pos > 1.0) {
                    this.position = 1.0;
                }
                else {
                    this.position = pos;
                }
                this.time = (long)(this.duration * this.position);
                this.component.repaint();
            }
        }
    }
    
    public void mouseMoved(final MouseEvent e) {
        e.translatePoint(-1, -1);
        final int comp = this.findComponent(e);
        if (comp == 1) {
            this.button1Color = Status.BUTTON_COLOR_MOUSE_UP;
            this.buttonColorIcon1 = Status.BUTTON_COLOR_ICON_MOUSE_UP;
        }
        else {
            this.button1Color = Status.BUTTON_COLOR_DEFAULT;
            this.buttonColorIcon1 = Status.BUTTON_COLOR_ICON_DEFAULT;
        }
        if (comp == 2) {
            this.button2Color = Status.BUTTON_COLOR_MOUSE_UP;
            this.buttonColorIcon2 = Status.BUTTON_COLOR_ICON_MOUSE_UP;
        }
        else {
            this.button2Color = Status.BUTTON_COLOR_DEFAULT;
            this.buttonColorIcon2 = Status.BUTTON_COLOR_ICON_DEFAULT;
        }
        if (comp == 5) {
            this.button3Color = Status.BUTTON_COLOR_MOUSE_UP;
            this.buttonColorIcon3 = Status.BUTTON_COLOR_ICON_MOUSE_UP;
            this.buttonColorText3 = Status.BUTTON_COLOR_TEXT_MOUSE_UP;
        }
        else {
            this.button3Color = Status.BUTTON_COLOR_DEFAULT;
            this.buttonColorIcon3 = Status.BUTTON_COLOR_ICON_DEFAULT;
            this.buttonColorText3 = Status.BUTTON_COLOR_TEXT_DEFAULT;
        }
        this.component.repaint();
    }
    
    static {
        BUTTON_COLOR_DEFAULT = new Color(192, 192, 192, 0);
        BUTTON_COLOR_MOUSE_UP = new Color(18, 19, 112, 210);
        BUTTON_COLOR_ICON_DEFAULT = new Color(18, 19, 112);
        BUTTON_COLOR_ICON_MOUSE_UP = new Color(192, 192, 192);
        BUTTON_COLOR_TEXT_DEFAULT = new Color(18, 19, 112);
        BUTTON_COLOR_TEXT_MOUSE_UP = Color.WHITE;
    }
}

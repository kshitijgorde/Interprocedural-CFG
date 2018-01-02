// 
// Decompiled by Procyon v0.5.30
// 

package com.fluendo.player;

import java.awt.event.MouseEvent;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.util.Enumeration;
import java.awt.image.ImageProducer;
import java.awt.image.MemoryImageSource;
import java.util.Vector;
import java.awt.Image;
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
    private String error;
    private Rectangle r;
    private Component component;
    private Font font;
    private boolean haveAudio;
    private boolean havePercent;
    private boolean seekable;
    private boolean live;
    private boolean showSpeaker;
    private boolean clearedScreen;
    private static final int NONE = 0;
    private static final int BUTTON1 = 1;
    private static final int BUTTON2 = 2;
    private static final int SEEKER = 3;
    private static final int SEEKBAR = 4;
    private int clicked;
    private Color button1Color;
    private Color button2Color;
    private Color seekColor;
    private static final int SPEAKER_WIDTH = 12;
    private static final int SPEAKER_HEIGHT = 10;
    private static final int TIME_WIDTH = 38;
    private static final int SEEK_TIME_GAP = 10;
    private static final int THUMB_WIDTH = 9;
    public static final int STATE_STOPPED = 0;
    public static final int STATE_PAUSED = 1;
    public static final int STATE_PLAYING = 2;
    private int state;
    private double position;
    private long time;
    private double duration;
    private long byteDuration;
    private long bytePosition;
    private String speaker;
    private Image speakerImg;
    private int speakerWidth;
    private Vector listeners;
    
    public Status(final Component component) {
        this.font = new Font("SansSerif", 0, 10);
        this.clicked = 0;
        this.state = 0;
        this.position = 0.0;
        this.speaker = "\u0000\u0000\u0000\u0000\u0000\u00ef\u0000\u0000\u00efU\u0017\u001e\u0000\u0000\u0000\u0000\u00ef\u00ef\u0000\u0000\u0000\u00efU\u0018\u0000\u0000\u0000\u00ef\u0000\u00ef\u0000\u00ef\u0000\u0000\u00ef\u0013\u00ef\u00ef\u00ef\u0000\u001c\u00ef\u0000Z\u00ef\u0000\u00ef\\\u00ef\u0000)+F\u00ef\u0000\u0000\u00ef\u0000\u00efr\u00efIbz\u0091\u00ef\u0000\u0000\u00ef\u0000\u00efr\u00ef\u00ef\u00ef¾\u00d3\u00ef\u0000Z\u00ef\u0000\u00ef\\\u0000\u0000\u0000\u00ef\u00ef\u00ef\u0000\u00ef\u0000\u0000\u00ef\u0000\u0000\u0000\u0000\u0000\u00ef\u00ef\u0000\u0000\u0000\u00ef\\\u0000\u0000\u0000\u0000\u0000\u0000\u00ef\u0000\u0000\u00ef\\\u0000\u0000";
        this.listeners = new Vector();
        final int[] array = new int[120];
        this.component = component;
        for (int i = 0; i < 120; ++i) {
            array[i] = (0xFF000000 | this.speaker.charAt(i) << 16 | this.speaker.charAt(i) << 8 | this.speaker.charAt(i));
        }
        this.speakerImg = component.getToolkit().createImage(new MemoryImageSource(12, 10, array, 0, 12));
        this.button1Color = Color.black;
        this.button2Color = Color.black;
        this.seekColor = Color.black;
    }
    
    public void addStatusListener(final StatusListener statusListener) {
        this.listeners.addElement(statusListener);
    }
    
    public void removeStatusListener(final StatusListener statusListener) {
        this.listeners.removeElement(statusListener);
    }
    
    public void notifyNewState(final int n) {
        final Enumeration<StatusListener> elements = this.listeners.elements();
        while (elements.hasMoreElements()) {
            elements.nextElement().newState(n);
        }
    }
    
    public void notifySeek(final double n) {
        final Enumeration<StatusListener> elements = this.listeners.elements();
        while (elements.hasMoreElements()) {
            elements.nextElement().newSeek(n);
        }
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    private void paintBox(final Graphics graphics) {
        graphics.setColor(Color.darkGray);
        graphics.drawRect(0, 0, this.r.width - 1, this.r.height - 1);
        graphics.setColor(Color.black);
        graphics.fillRect(1, 1, this.r.width - 2, this.r.height - 2);
    }
    
    private void paintPercent(final Graphics graphics) {
        if (this.havePercent) {
            graphics.setColor(Color.white);
            graphics.drawString("" + this.bufferPercent + "%", this.r.width - 26 - this.speakerWidth, this.r.height - 2);
        }
    }
    
    private void paintButton1(final Graphics graphics) {
        final int n = 1;
        final int n2 = 1;
        final int n3 = this.r.height - 2;
        final int n4 = this.r.height - 2;
        graphics.setColor(Color.darkGray);
        graphics.drawRect(n, n2, n3, n4);
        graphics.setColor(this.button1Color);
        graphics.fillRect(n + 1, n2 + 1, n3 - 1, n4 - 1);
        if (this.state == 2) {
            graphics.setColor(Color.white);
            if (this.live) {
                graphics.fillRect((int)(n3 * 0.4), (int)(n3 * 0.4), (int)(n3 * 0.5), (int)(n3 * 0.5));
            }
            else {
                graphics.fillRect((int)(n3 * 0.4), (int)(n4 * 0.4), (int)(n3 * 0.2), (int)(n4 * 0.5));
                graphics.fillRect((int)(n3 * 0.7), (int)(n4 * 0.4), (int)(n3 * 0.2), (int)(n4 * 0.5));
            }
        }
        else {
            final int[] array = { (int)(n3 * 0.4), (int)(n3 * 0.4), (int)(n3 * 0.9) };
            final int[] array2 = { (int)(n3 * 0.3), (int)(n3 * 0.9), (int)(n3 * 0.6) };
            graphics.setColor(Color.white);
            graphics.fillPolygon(array, array2, 3);
        }
    }
    
    private void paintButton2(final Graphics graphics) {
        final int n = this.r.height + 1;
        final int n2 = 1;
        final int n3 = this.r.height - 2;
        final int n4 = this.r.height - 2;
        graphics.setColor(Color.darkGray);
        graphics.drawRect(n, n2, n3, n4);
        graphics.setColor(this.button2Color);
        graphics.fillRect(n + 1, n2 + 1, n3 - 1, n4 - 1);
        graphics.setColor(Color.white);
        graphics.fillRect(this.r.height + (int)(n3 * 0.4), (int)(n3 * 0.4), (int)(n3 * 0.5), (int)(n3 * 0.5));
    }
    
    private void paintMessage(final Graphics graphics, final int n) {
        if (this.message != null) {
            graphics.setColor(Color.white);
            graphics.drawString(this.message, n, this.r.height - 2);
        }
    }
    
    private void paintBuffering(final Graphics graphics, final int n) {
        graphics.setColor(Color.white);
        graphics.drawString("Buffering", n, this.r.height - 2);
    }
    
    private Rectangle getSeekBarRect() {
        return new Rectangle(this.r.height * 2 + 1, 2, this.r.width - 10 - 38 - this.speakerWidth - this.r.height * 2, this.r.height - 4);
    }
    
    private Rectangle getThumbRect() {
        final Rectangle seekBarRect = this.getSeekBarRect();
        return new Rectangle((int)((seekBarRect.width - 9) * this.position) + seekBarRect.x, 1, 9, this.r.height - 2);
    }
    
    private void paintSeekBar(final Graphics graphics) {
        final Rectangle seekBarRect = this.getSeekBarRect();
        final Rectangle thumbRect = this.getThumbRect();
        graphics.setColor(Color.darkGray);
        graphics.drawRect(seekBarRect.x, seekBarRect.y, seekBarRect.width, seekBarRect.height);
        graphics.setColor(Color.gray);
        graphics.fillRect(seekBarRect.x + 2, seekBarRect.y + 3, thumbRect.x - (seekBarRect.x + 2), seekBarRect.height - 6);
        graphics.setColor(Color.white);
        graphics.drawLine(thumbRect.x + 1, thumbRect.y, thumbRect.x + thumbRect.width - 1, thumbRect.y);
        graphics.drawLine(thumbRect.x + 1, thumbRect.y + thumbRect.height, thumbRect.x + thumbRect.width - 1, thumbRect.y + thumbRect.height);
        graphics.drawLine(thumbRect.x, thumbRect.y + 1, thumbRect.x, thumbRect.y + thumbRect.height - 1);
        graphics.drawLine(thumbRect.x + thumbRect.width, thumbRect.y + 1, thumbRect.x + thumbRect.width, thumbRect.y + thumbRect.height - 1);
        graphics.setColor(this.seekColor);
        graphics.fillRect(thumbRect.x + 1, thumbRect.y + 1, thumbRect.width - 1, thumbRect.height - 1);
    }
    
    private void paintTime(final Graphics graphics) {
        if (this.time < 0L) {
            return;
        }
        final long n = this.time % 60L;
        final long n2 = this.time / 60L;
        final long n3 = n2 / 60L;
        final long n4 = n2 % 60L;
        this.r = this.getBounds();
        final int n5 = this.r.width - this.speakerWidth - 38;
        graphics.setColor(Color.white);
        graphics.drawString("" + n3 + ":" + ((n4 < 10L) ? ("0" + n4) : ("" + n4)) + ":" + ((n < 10L) ? ("0" + n) : ("" + n)), n5, this.r.height - 2);
    }
    
    private void paintSpeaker(final Graphics graphics) {
        if (this.haveAudio) {
            graphics.drawImage(this.speakerImg, this.r.width - 12, this.r.height - 10 - 1, null);
        }
    }
    
    public void paint(final Graphics graphics) {
        if (!this.isVisible() && this.clearedScreen) {
            return;
        }
        this.r = this.getBounds();
        if (!this.isVisible() && !this.clearedScreen) {
            graphics.clearRect(this.r.x, this.r.y, this.r.width, this.r.height);
            this.clearedScreen = true;
            return;
        }
        this.clearedScreen = false;
        final Image image = this.component.createImage(this.r.width, this.r.height);
        if (image == null) {
            return;
        }
        final Graphics graphics2 = image.getGraphics();
        if (graphics2 == null) {
            return;
        }
        graphics2.setFont(this.font);
        this.paintBox(graphics2);
        if (!this.buffering) {
            this.paintButton1(graphics2);
        }
        int height;
        if (!this.live) {
            this.paintButton2(graphics2);
            height = this.r.height * 2;
        }
        else {
            height = this.r.height;
        }
        if (this.buffering) {
            this.paintPercent(graphics2);
            this.paintBuffering(graphics2, height + 3);
        }
        else if (this.state == 0 || !this.seekable) {
            this.paintMessage(graphics2, height + 3);
            this.paintTime(graphics2);
        }
        else if (this.seekable) {
            this.paintSeekBar(graphics2);
            this.paintTime(graphics2);
        }
        if (this.showSpeaker) {
            this.paintSpeaker(graphics2);
        }
        graphics.drawImage(image, this.r.x, this.r.y, null);
        image.flush();
    }
    
    public void setBufferPercent(final boolean buffering, final int bufferPercent) {
        if (this.buffering != buffering | this.bufferPercent != bufferPercent) {
            this.buffering = buffering;
            this.bufferPercent = bufferPercent;
            this.component.repaint();
        }
    }
    
    public void setTime(final double n) {
        if (this.clicked == 0) {
            if (n < this.duration || this.seekable) {
                this.time = (long)n;
            }
            else {
                this.time = (long)this.duration;
            }
            if (this.duration > -1.0) {
                final double position = this.time / this.duration;
                if (position != this.position) {
                    this.position = position;
                    this.component.repaint();
                }
            }
            else {
                this.position = this.bytePosition / this.byteDuration;
                this.component.repaint();
            }
        }
    }
    
    public void setDuration(final double duration) {
        this.duration = duration;
        this.component.repaint();
    }
    
    public void setByteDuration(final long byteDuration) {
        this.byteDuration = byteDuration;
        if (this.duration == -1.0) {
            this.position = this.bytePosition / this.byteDuration;
            this.component.repaint();
        }
    }
    
    public void setBytePosition(final long bytePosition) {
        this.bytePosition = bytePosition;
        if (this.duration == -1.0) {
            this.position = this.bytePosition / this.byteDuration;
            this.component.repaint();
        }
    }
    
    public void setMessage(final String message) {
        this.message = message;
        this.component.repaint();
    }
    
    public void setHaveAudio(final boolean haveAudio) {
        this.haveAudio = haveAudio;
        this.component.repaint();
    }
    
    public void setHavePercent(final boolean havePercent) {
        this.havePercent = havePercent;
        this.component.repaint();
    }
    
    public void setSeekable(final boolean seekable) {
        this.seekable = seekable;
        this.component.repaint();
    }
    
    public void setLive(final boolean live) {
        this.live = live;
        this.component.repaint();
    }
    
    public void setShowSpeaker(final boolean showSpeaker) {
        this.showSpeaker = showSpeaker;
        this.speakerWidth = (showSpeaker ? 12 : 0);
        this.component.repaint();
    }
    
    public void setState(final int state) {
        if (this.state != state) {
            this.state = state;
            this.component.repaint();
        }
    }
    
    private boolean intersectButton1(final MouseEvent mouseEvent) {
        return this.r != null && mouseEvent.getX() >= 0 && mouseEvent.getX() <= this.r.height - 2 && mouseEvent.getY() > 0 && mouseEvent.getY() <= this.r.height - 2;
    }
    
    private boolean intersectButton2(final MouseEvent mouseEvent) {
        return this.r != null && mouseEvent.getX() >= this.r.height && mouseEvent.getX() <= this.r.height + this.r.height - 2 && mouseEvent.getY() > 0 && mouseEvent.getY() <= this.r.height - 2;
    }
    
    private boolean intersectSeeker(final MouseEvent mouseEvent) {
        this.r = this.getBounds();
        return this.getThumbRect().contains(mouseEvent.getPoint());
    }
    
    private boolean intersectSeekbar(final MouseEvent mouseEvent) {
        this.r = this.getBounds();
        return this.getSeekBarRect().contains(mouseEvent.getPoint());
    }
    
    private int findComponent(final MouseEvent mouseEvent) {
        if (!this.buffering && this.intersectButton1(mouseEvent)) {
            return 1;
        }
        if (this.intersectButton2(mouseEvent)) {
            return 2;
        }
        if (this.seekable && this.intersectSeeker(mouseEvent)) {
            return 3;
        }
        if (this.seekable && this.intersectSeekbar(mouseEvent)) {
            return 4;
        }
        return 0;
    }
    
    public void cancelMouseOperation() {
        this.button1Color = Color.black;
        this.button2Color = Color.black;
        this.seekColor = Color.black;
        this.clicked = 0;
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        mouseEvent.translatePoint(-1, -1);
        this.clicked = this.findComponent(mouseEvent);
        if (this.clicked == 4 && this.state != 0) {
            this.clicked = 3;
            this.seekColor = Color.gray;
            this.mouseDragged(mouseEvent);
        }
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
        mouseEvent.translatePoint(-1, -1);
        int n = this.findComponent(mouseEvent);
        if (this.clicked != n) {
            if (this.clicked != 3) {
                return;
            }
            n = this.clicked;
        }
        switch (n) {
            case 1: {
                if (this.state == 2) {
                    if (this.live) {
                        this.state = 0;
                    }
                    else {
                        this.state = 1;
                    }
                    this.notifyNewState(this.state);
                    break;
                }
                this.notifyNewState(this.state = 2);
                break;
            }
            case 2: {
                this.notifyNewState(this.state = 0);
                break;
            }
            case 3: {
                if (this.state != 0) {
                    this.notifySeek(this.position);
                    break;
                }
                break;
            }
        }
        this.clicked = 0;
        this.component.repaint();
    }
    
    public void mouseDragged(final MouseEvent mouseEvent) {
        if (this.seekable) {
            mouseEvent.translatePoint(-1, -1);
            if (this.clicked == 3) {
                final Rectangle seekBarRect = this.getSeekBarRect();
                double position = (mouseEvent.getX() - seekBarRect.x - 4) / (seekBarRect.width - 9);
                if (position < 0.0) {
                    position = 0.0;
                }
                else if (position > 1.0) {
                    position = 1.0;
                }
                if (position != this.position) {
                    this.position = position;
                    this.time = (long)(this.duration * this.position);
                    this.component.repaint();
                }
            }
        }
    }
    
    public void mouseMoved(final MouseEvent mouseEvent) {
        boolean b = false;
        mouseEvent.translatePoint(-1, -1);
        if (!this.buffering) {
            if (this.intersectButton1(mouseEvent)) {
                if (this.button1Color != Color.gray) {
                    this.button1Color = Color.gray;
                    b = true;
                }
            }
            else if (this.button1Color != Color.black) {
                this.button1Color = Color.black;
                b = true;
            }
        }
        if (this.intersectButton2(mouseEvent)) {
            if (this.button2Color != Color.gray) {
                this.button2Color = Color.gray;
                b = true;
            }
        }
        else if (this.button2Color != Color.black) {
            this.button2Color = Color.black;
            b = true;
        }
        if (this.seekable) {
            if (this.intersectSeeker(mouseEvent)) {
                if (this.seekColor != Color.gray) {
                    this.seekColor = Color.gray;
                    b = true;
                }
            }
            else if (this.seekColor != Color.black) {
                this.seekColor = Color.black;
                b = true;
            }
        }
        if (b) {
            this.component.repaint();
        }
    }
}

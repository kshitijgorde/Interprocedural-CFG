// 
// Decompiled by Procyon v0.5.30
// 

package org.bm.bmtron;

import java.io.IOException;
import java.io.InputStream;
import java.awt.Component;
import org.bm.util.ResourceLoader;
import java.awt.Font;
import java.awt.Color;
import java.awt.Image;
import java.net.URL;

public class Skin
{
    private static final byte[] FIRST_BYTES;
    private static final String NAME = "BMTron Skin";
    public static final int VERSION = 3;
    public static final int DATA_INT = 4;
    public static final int DATA_IMAGE = 8;
    public static final int DATA_COLOR = 9;
    public static final int DATA_FONT = 10;
    private URL source;
    private boolean loaded;
    Image background;
    Image title;
    Image activeTitle;
    Image field;
    Image settings;
    Image yes;
    Image no;
    Image start;
    Image blow;
    Image appear;
    Image[] controls;
    Image[] snake;
    Image colorPreview;
    Color backColor;
    Color statusTextColor;
    Color messageTextColor1;
    Color messageTextColor2;
    Color messageBackColor;
    Color messageShadowColor;
    Color messageBorderColor;
    Color linkColor;
    Color buttonBorderColor;
    Color activeButtonBorderColor;
    Color buttonBackColor;
    Color buttonTextColor;
    Color activePanelBorderColor;
    Font statusFont;
    Font messageFont1;
    Font messageFont2;
    Font linkFont;
    Font buttonFont;
    private final Game game;
    
    public Skin(final Game game, final URL source) {
        this.loaded = false;
        this.background = null;
        this.title = null;
        this.activeTitle = null;
        this.field = null;
        this.settings = null;
        this.yes = null;
        this.no = null;
        this.start = null;
        this.blow = null;
        this.appear = null;
        this.controls = new Image[7];
        this.snake = new Image[10];
        this.colorPreview = null;
        this.backColor = Color.white;
        this.statusTextColor = Color.blue;
        this.messageTextColor1 = Color.blue;
        this.messageTextColor2 = Color.red;
        this.messageBackColor = Color.white;
        this.messageShadowColor = Color.black;
        this.messageBorderColor = Color.red;
        this.linkColor = Color.blue;
        this.buttonBorderColor = Color.blue;
        this.activeButtonBorderColor = Color.red;
        this.buttonBackColor = Color.white;
        this.buttonTextColor = Color.blue;
        this.activePanelBorderColor = Color.white;
        this.statusFont = new Font("Helvetica", 1, 12);
        this.messageFont1 = new Font("Helvetica", 1, 32);
        this.messageFont2 = new Font("Helvetica", 1, 16);
        this.linkFont = new Font("Helvetica", 0, 12);
        this.buttonFont = new Font("Helvetiva", 1, 14);
        this.game = game;
        this.source = source;
    }
    
    public Object getSkinLock() {
        return this;
    }
    
    public void load() throws IOException, InterruptedException {
        final InputStream openStream = this.source.openStream();
        final ResourceLoader resourceLoader = new ResourceLoader(this.game, "BMTron Skin", openStream);
        int i;
        do {
            i = resourceLoader.loadNext();
            this.game.setStatus(i * 100 / 255 + "% loaded");
            switch (resourceLoader.getResourceType()) {
                default: {
                    continue;
                }
                case 8: {
                    this.setImage(resourceLoader.getResourceName(), resourceLoader.getImage());
                    continue;
                }
                case 9: {
                    this.setColor(resourceLoader.getResourceName(), resourceLoader.getColor());
                    continue;
                }
                case 10: {
                    this.setFont(resourceLoader.getResourceName(), resourceLoader.getFont());
                    continue;
                }
            }
        } while (i != 255);
        openStream.close();
        this.loaded = true;
    }
    
    public boolean isLoaded() {
        return this.loaded;
    }
    
    private void setFont(final String s, final Font buttonFont) {
        synchronized (this.getSkinLock()) {
            if (s.equals("status")) {
                this.statusFont = buttonFont;
            }
            else if (s.equals("message1")) {
                this.messageFont1 = buttonFont;
            }
            else if (s.equals("message2")) {
                this.messageFont2 = buttonFont;
            }
            else if (s.equals("link")) {
                this.linkFont = buttonFont;
            }
            else {
                if (!s.equals("button")) {
                    return;
                }
                this.buttonFont = buttonFont;
            }
        }
        this.game.repaint();
    }
    
    private void setImage(final String s, final Image colorPreview) {
        synchronized (this.getSkinLock()) {
            if (s.equals("background")) {
                this.background = colorPreview;
            }
            else if (s.equals("title")) {
                this.title = colorPreview;
            }
            else if (s.equals("active_title")) {
                this.activeTitle = colorPreview;
            }
            else if (s.equals("field")) {
                this.field = colorPreview;
            }
            else if (s.equals("settings")) {
                this.settings = colorPreview;
            }
            else if (s.equals("yes")) {
                this.yes = colorPreview;
            }
            else if (s.equals("no")) {
                this.no = colorPreview;
            }
            else if (s.equals("start")) {
                this.start = colorPreview;
            }
            else if (s.equals("blow")) {
                this.blow = colorPreview;
            }
            else if (s.equals("appear")) {
                this.appear = colorPreview;
            }
            else if (s.startsWith("control")) {
                final int int1 = Integer.parseInt(s.substring(7));
                if (int1 < this.controls.length) {
                    this.controls[int1] = colorPreview;
                }
            }
            else {
                if (!s.startsWith("snake")) {
                    return;
                }
                final int int2 = Integer.parseInt(s.substring(5));
                if (int2 < this.snake.length) {
                    this.snake[int2] = colorPreview;
                }
                if (int2 == 1) {
                    this.colorPreview = colorPreview;
                }
            }
        }
        this.game.repaint();
    }
    
    private void setColor(final String s, final Color messageBackColor) {
        synchronized (this.getSkinLock()) {
            if (s.equals("background")) {
                this.backColor = messageBackColor;
            }
            else if (s.equals("status_text")) {
                this.statusTextColor = messageBackColor;
            }
            else if (s.equals("message_text1")) {
                this.messageTextColor1 = messageBackColor;
            }
            else if (s.equals("message_text2")) {
                this.messageTextColor2 = messageBackColor;
            }
            else if (s.equals("message_shadow")) {
                this.messageShadowColor = messageBackColor;
            }
            else if (s.equals("message_border")) {
                this.messageBorderColor = messageBackColor;
            }
            else if (s.equals("link")) {
                this.linkColor = messageBackColor;
            }
            else if (s.equals("button_border")) {
                this.buttonBorderColor = messageBackColor;
            }
            else if (s.equals("active_button_border")) {
                this.activeButtonBorderColor = messageBackColor;
            }
            else if (s.equals("button_text")) {
                this.buttonTextColor = messageBackColor;
            }
            else if (s.equals("button_background")) {
                this.buttonBackColor = messageBackColor;
            }
            else if (s.equals("active_panel_border")) {
                this.activePanelBorderColor = messageBackColor;
            }
            else {
                if (!s.equals("message_background")) {
                    return;
                }
                try {
                    this.messageBackColor = new Color(messageBackColor.getRed(), messageBackColor.getGreen(), messageBackColor.getBlue(), 210);
                }
                catch (IncompatibleClassChangeError incompatibleClassChangeError) {
                    this.messageBackColor = messageBackColor;
                }
            }
        }
        this.game.repaint();
    }
    
    public String toString() {
        return this.getClass().getName() + "[" + this.source + "]";
    }
    
    static {
        FIRST_BYTES = "(BM Resources)".getBytes();
    }
}

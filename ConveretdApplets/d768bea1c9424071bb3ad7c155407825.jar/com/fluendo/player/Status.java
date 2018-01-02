// 
// Decompiled by Procyon v0.5.30
// 

package com.fluendo.player;

import java.awt.image.ImageObserver;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.ImageProducer;
import java.awt.image.MemoryImageSource;
import java.awt.Image;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.Component;

public class Status extends Component
{
    private int bufferPercent;
    private String message;
    private Rectangle r;
    private Component component;
    private Font font;
    private boolean haveAudio;
    private boolean havePercent;
    private String speaker;
    private Image speakerImg;
    
    public Status(final Component component) {
        this.font = new Font("SansSerif", 0, 10);
        this.speaker = "\u0000\u0000\u0000\u0000\u0000\u00ef\u0000\u0000\u00efU\u0017\u001e\u0000\u0000\u0000\u0000\u00ef\u00ef\u0000\u0000\u0000\u00efU\u0018\u0000\u0000\u0000\u00ef\u0000\u00ef\u0000\u00ef\u0000\u0000\u00ef\u0013\u00ef\u00ef\u00ef\u0000\u001c\u00ef\u0000Z\u00ef\u0000\u00ef\\\u00ef\u0000)+F\u00ef\u0000\u0000\u00ef\u0000\u00efr\u00efIbz\u0091\u00ef\u0000\u0000\u00ef\u0000\u00efr\u00ef\u00ef\u00ef¾\u00d3\u00ef\u0000Z\u00ef\u0000\u00ef\\\u0000\u0000\u0000\u00ef\u00ef\u00ef\u0000\u00ef\u0000\u0000\u00ef\u0000\u0000\u0000\u0000\u0000\u00ef\u00ef\u0000\u0000\u0000\u00ef\\\u0000\u0000\u0000\u0000\u0000\u0000\u00ef\u0000\u0000\u00ef\\\u0000\u0000";
        final int[] array = new int[120];
        this.component = component;
        for (int i = 0; i < 120; ++i) {
            array[i] = (0xFF000000 | this.speaker.charAt(i) << 16 | this.speaker.charAt(i) << 8 | this.speaker.charAt(i));
        }
        this.speakerImg = component.getToolkit().createImage(new MemoryImageSource(12, 10, array, 0, 12));
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void paint(final Graphics graphics) {
        if (!this.isVisible()) {
            return;
        }
        this.r = this.getBounds();
        final Image image = this.component.createImage(this.r.width, this.r.height);
        final Graphics graphics2 = image.getGraphics();
        graphics2.setColor(Color.darkGray);
        graphics2.setFont(this.font);
        graphics2.drawRect(0, 0, this.r.width - 1, this.r.height - 1);
        graphics2.setColor(Color.black);
        graphics2.fillRect(1, 1, this.r.width - 2, this.r.height - 2);
        graphics2.setColor(Color.white);
        if (this.havePercent) {
            graphics2.drawString("" + this.bufferPercent + "%", this.r.width - 38, this.r.height - 2);
        }
        if (this.message != null) {
            graphics2.drawString(this.message, 2, this.r.height - 2);
        }
        if (this.haveAudio) {
            graphics2.drawImage(this.speakerImg, this.r.width - 12, this.r.height - 11, null);
        }
        graphics.drawImage(image, this.r.x, this.r.y, null);
        image.flush();
    }
    
    public void setBufferPercent(final int bufferPercent) {
        this.bufferPercent = bufferPercent;
    }
    
    public void setMessage(final String message) {
        this.message = message;
    }
    
    public void setHaveAudio(final boolean haveAudio) {
        this.haveAudio = haveAudio;
    }
    
    public void setHavePercent(final boolean havePercent) {
        this.havePercent = havePercent;
    }
}

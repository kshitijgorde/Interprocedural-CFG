// 
// Decompiled by Procyon v0.5.30
// 

package ru.zhuk.gui;

import java.awt.Dimension;
import ru.zhuk.graphics.b;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.awt.image.ImageFilter;
import java.awt.image.FilteredImageSource;
import ru.zhuk.graphics.c;
import java.awt.Toolkit;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.Image;
import java.awt.Component;

public class a extends Component implements Runnable
{
    private Image h;
    private Image a;
    private boolean f;
    private boolean b;
    private int e;
    private boolean c;
    int g;
    Thread d;
    
    public a() {
        this.g = 0;
    }
    
    public void a(final Image h) {
        this.h = h;
        this.repaint();
    }
    
    public void processMouseEvent(final MouseEvent mouseEvent) {
        switch (mouseEvent.getID()) {
            case 501: {
                this.b = true;
                break;
            }
            case 502: {
                this.b = false;
                break;
            }
        }
        this.repaint();
        super.processMouseEvent(mouseEvent);
    }
    
    public void a(final int e) {
        this.e = e;
    }
    
    public void a(final boolean c) {
        this.c = c;
    }
    
    public void setForeground(final Color foreground) {
        this.a = null;
        super.setForeground(foreground);
        this.repaint();
    }
    
    public void b(final boolean f) {
        this.f = f;
        this.repaint();
        if (this.d != null) {
            this.d.interrupt();
            this.d = null;
        }
        if (f) {
            (this.d = new Thread(this)).start();
        }
    }
    
    public void run() {
        while (true) {
            try {
                Thread.currentThread();
                Thread.sleep(300L);
            }
            catch (Exception ex) {
                break;
            }
            if (this.getParent() == null) {
                break;
            }
            ++this.g;
            this.repaint();
        }
    }
    
    public void paint(final Graphics graphics) {
        final Dimension size = this.getSize();
        Image image = this.h;
        if (this.c) {
            if (this.a == null && this.h != null) {
                this.a = Toolkit.getDefaultToolkit().createImage(new FilteredImageSource(this.h.getSource(), new c(Color.black, this.getForeground())));
            }
            image = this.a;
        }
        if (image != null) {
            if (this.e == 0) {
                graphics.drawImage(image, 0, 0, size.width, size.height, this);
            }
            else if (this.e == 1) {
                final int width = image.getWidth(this);
                final int height = image.getHeight(this);
                if (width > 0 && height > 0) {
                    final int n = size.width / width;
                    for (int n2 = size.height / height, i = 0; i <= n2; ++i) {
                        for (int j = 0; j <= n; ++j) {
                            graphics.drawImage(image, j * width, i * height, this);
                        }
                    }
                }
            }
        }
        if (this.f && !this.b) {
            graphics.setColor(Color.white);
            graphics.setXORMode(Color.black);
            ru.zhuk.graphics.b.a(graphics, 0, 0, size.width - 1, size.height - 1, this.g);
            final int d = ru.zhuk.gui.d.d;
            graphics.fillRect(1, 1, d, d);
            graphics.fillRect((size.width - d) / 2, 1, d, d);
            graphics.fillRect(size.width - 1 - d, 1, d, d);
            graphics.fillRect(1, (size.height - d) / 2, d, d);
            graphics.fillRect(size.width - 1 - d, (size.height - d) / 2, d, d);
            graphics.fillRect(1, size.height - 1 - d, d, d);
            graphics.fillRect((size.width - d) / 2, size.height - 1 - d, d, d);
            graphics.fillRect(size.width - 1 - d, size.height - 1 - d, d, d);
        }
    }
}

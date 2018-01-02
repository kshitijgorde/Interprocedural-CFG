// 
// Decompiled by Procyon v0.5.30
// 

package netcharts.graphics;

import netcharts.util.NF11Util;
import netcharts.util.NFUtil;
import java.awt.Component;
import java.awt.Image;
import java.awt.Graphics;
import java.awt.MediaTracker;
import java.awt.image.ImageObserver;
import java.awt.Canvas;

public class NFImage extends Canvas implements ImageObserver
{
    public boolean ready;
    public static final int TILE = 0;
    public static final int SIZE = 1;
    public static final int CENTER = 4;
    private static MediaTracker a;
    
    public NFImage() {
        this.ready = false;
    }
    
    public static synchronized void tileImage(final Graphics graphics, final Image image, final int n, final int n2, final int n3, final int n4) {
        final Graphics create = graphics.create(n, n2, n3, n4);
        final int width = image.getWidth(null);
        final int height = image.getHeight(null);
        final int n5 = n3 / width + 1;
        for (int n6 = n4 / height + 1, i = 0; i < n6; ++i) {
            for (int j = 0; j < n5; ++j) {
                create.drawImage(image, j * width, i * height, null);
            }
        }
        create.dispose();
    }
    
    public synchronized boolean imageUpdate(final Image image, final int n, final int n2, final int n3, final int n4, final int n5) {
        boolean b = true;
        if ((n & 0x3) != 0x0) {
            b = false;
        }
        if ((n & 0x20) != 0x0) {
            this.ready = true;
            b = false;
        }
        if ((n & 0x8) != 0x0) {
            this.ready = true;
            b = true;
        }
        if ((n & 0x40) != 0x0) {
            b = false;
            this.ready = true;
        }
        return b;
    }
    
    public static void drawBackgroundImage(final Graphics graphics, final Image image, final int n, final int n2, final int n3, final int n4, final int n5) {
        switch (n) {
            case 0: {
                tileImage(graphics, image, n2, n3, n4, n5);
                break;
            }
            case 4: {
                graphics.drawImage(image, n2 + n4 / 2 - image.getWidth(null) / 2, n3 + n5 / 2 - image.getHeight(null) / 2, null);
                break;
            }
            default: {
                if (NFImage.a == null) {
                    NFImage.a = new MediaTracker(new Canvas());
                }
                NFImage.a.addImage(image, 0, n4, n5);
                try {
                    NFImage.a.waitForID(0);
                }
                catch (Exception ex) {}
                if (NFUtil.getJDKVersion() >= 1.1) {
                    NF11Util.removeImage(NFImage.a, image, 0, n4, n5);
                }
                graphics.drawImage(image, n2, n3, n4, n5, null);
                break;
            }
        }
    }
    
    static {
        NFImage.a = null;
    }
}

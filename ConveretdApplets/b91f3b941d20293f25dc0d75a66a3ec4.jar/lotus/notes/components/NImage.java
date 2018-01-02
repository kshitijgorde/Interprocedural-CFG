// 
// Decompiled by Procyon v0.5.30
// 

package lotus.notes.components;

import java.awt.Graphics;
import java.awt.image.ImageObserver;
import java.awt.Component;
import java.awt.Image;

public class NImage
{
    private Image i_image;
    private Image[] imagesArray;
    private int wellWidth;
    private int wellHeight;
    private Component parent;
    public static final int NORMAL_STATE = 0;
    public static final int MOUSEOVER_STATE = 1;
    public static final int SELECTED_STATE = 2;
    public static final int MOUSEDOWN_STATE = 3;
    private int state;
    public static final int REPEAT_ONCE = 0;
    public static final int REPEAT_VERTICAL = 1;
    public static final int REPEAT_HORIZONTAL = 2;
    public static final int REPEAT_BOTHWAYS = 3;
    public static final int REPEAT_SIZETOFIT = 4;
    public static final int REPEAT_CENTER = 5;
    private int repeatAttr;
    
    public NImage(final Image i_image, final Component parent, final int repeatAttr) {
        this.i_image = null;
        this.imagesArray = null;
        this.wellWidth = 0;
        this.wellHeight = 0;
        this.parent = null;
        this.state = 0;
        this.repeatAttr = 0;
        this.i_image = i_image;
        this.parent = parent;
        if (repeatAttr > 0 && repeatAttr <= 5) {
            this.repeatAttr = repeatAttr;
        }
    }
    
    public Image getImage() {
        return this.i_image;
    }
    
    public void setState(final int state) {
        if (state < 0 || state > 3) {
            return;
        }
        this.state = state;
    }
    
    public void setImageWell(final int n, final int n2) {
        if (this.i_image == null) {
            return;
        }
        final int height = this.i_image.getHeight(null);
        final int width = this.i_image.getWidth(null);
        if (width == -1 || height == -1) {
            return;
        }
        if (n <= 1 && n2 <= 1) {
            return;
        }
        final int n3 = (width - (n - 1)) / n;
        final int n4 = (height - (n2 - 1)) / n2;
        this.imagesArray = new Image[n];
        int n5 = 0;
        for (int i = 0; i < n; ++i) {
            this.imagesArray[i] = this.parent.createImage(n3, n4);
            final Graphics graphics = this.imagesArray[i].getGraphics();
            graphics.drawImage(this.i_image, n5, 0, this.parent);
            graphics.dispose();
            n5 -= n3 + 1;
        }
    }
    
    public Image getStateImage(final int n) {
        Image i_image = this.i_image;
        if (this.imagesArray != null) {
            if (n < 0 || n > 3 || n >= this.imagesArray.length) {
                return this.imagesArray[0];
            }
            i_image = this.imagesArray[n];
        }
        return i_image;
    }
    
    public void paint(final Graphics graphics, final int n, final int n2, final int n3, final int n4) {
        Image image = this.i_image;
        final int height = image.getHeight(this.parent);
        final int width = image.getWidth(this.parent);
        if (this.imagesArray != null) {
            image = this.getStateImage(this.state);
        }
        switch (this.repeatAttr) {
            case 4: {
                graphics.drawImage(image, n, n2, n3, n4, null);
                break;
            }
            case 1:
            case 2:
            case 3: {
                int n5 = n;
                int n6 = n2;
                int i = 1;
                while (i != 0) {
                    graphics.drawImage(image, n5, n6, null);
                    if (this.repeatAttr == 2) {
                        n5 += width;
                        if (n5 <= n3 + n && width > 0) {
                            continue;
                        }
                        i = 0;
                    }
                    else if (this.repeatAttr == 1) {
                        n6 += height;
                        if (n6 <= n4 + n2 && height > 0) {
                            continue;
                        }
                        i = 0;
                    }
                    else if (width > 0 && n5 + width < n3 + n) {
                        n5 += width;
                    }
                    else if (height > 0 && n6 + height < n4 + n2) {
                        n5 = n;
                        n6 += height;
                    }
                    else {
                        i = 0;
                    }
                }
                break;
            }
            case 5: {
                graphics.drawImage(image, (n + n3) / 2 - width / 2, (n2 + n4) / 2 - height / 2, null);
                break;
            }
            default: {
                graphics.drawImage(image, n, n2, null);
                break;
            }
        }
    }
}

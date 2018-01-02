// 
// Decompiled by Procyon v0.5.30
// 

package geracemenu;

import java.awt.Insets;
import geracemenu.parser.StringValue;
import geracemenu.parser.Type;
import java.util.Hashtable;
import java.awt.Image;
import java.awt.image.ImageProducer;
import java.awt.image.ImageFilter;
import java.awt.image.FilteredImageSource;
import java.awt.Toolkit;
import java.awt.Color;
import java.awt.Point;
import java.awt.Graphics;

public class EmbossedItem extends MenuItem
{
    public static final int CARVING_SUNK = 1;
    public static final int CARVING_RAISED = 2;
    private VImage disabledLook1;
    private VImage disabledLook2;
    private int carving;
    
    public void paintItem(final Graphics graphics) {
        if (this.disabledLook1 == null || this.disabledLook2 == null) {
            this.makeDisabled();
        }
        switch (this.carving) {
            case 1: {
                this.disabledLook1.paint(graphics, new Point(1, 1));
                this.disabledLook2.paint(graphics, new Point(0, 0));
                break;
            }
            case 2: {
                this.disabledLook1.paint(graphics, new Point(0, 0));
                this.disabledLook2.paint(graphics, new Point(1, 1));
                break;
            }
        }
    }
    
    private synchronized void makeDisabled() {
        final Image image = this.createImage(this.getWidth(), this.getHeight());
        final Graphics graphics = image.getGraphics();
        graphics.setColor(Color.white);
        graphics.fillRect(0, 0, this.getWidth(), this.getHeight());
        graphics.setColor(Color.black);
        this.getLayout().paint(graphics, new Point(0, 0));
        final Image image2 = Toolkit.getDefaultToolkit().createImage(new FilteredImageSource(image.getSource(), new OpaqueImageFilter(this.getBackground().brighter())));
        final Image image3 = Toolkit.getDefaultToolkit().createImage(new FilteredImageSource(image.getSource(), new OpaqueImageFilter(this.getBackground().darker())));
        this.disabledLook1 = new VImage(image2, false);
        this.disabledLook2 = new VImage(image3, false);
        graphics.dispose();
    }
    
    private synchronized void clearDisabled() {
        final VImage vImage = null;
        this.disabledLook2 = vImage;
        this.disabledLook1 = vImage;
    }
    
    public EmbossedItem(final String s) {
        this(s, (Hashtable)null);
    }
    
    public EmbossedItem(final String s, final Hashtable hashtable) {
        super(s, hashtable);
        this.disabledLook1 = null;
        this.disabledLook2 = null;
        this.carving = 1;
        final Type type;
        if (hashtable != null && (type = hashtable.get("carvingType")) != null && "raised".equalsIgnoreCase((String)((StringValue)type).getValue())) {
            this.carving = 2;
        }
        super.insets = new Insets(5, 3, 5, 3);
    }
}

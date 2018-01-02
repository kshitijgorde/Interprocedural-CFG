// 
// Decompiled by Procyon v0.5.30
// 

package borland.jbcl.control;

import borland.jbcl.model.SingletonViewManager;
import borland.jbcl.model.ItemEditor;
import borland.jbcl.model.BasicViewManager;
import borland.jbcl.view.CompositeItemPainter;
import COM.objectspace.jgl.Pair;
import borland.jbcl.model.ItemPainter;
import borland.jbcl.view.FocusableItemPainter;
import borland.jbcl.view.TextItemPainter;
import java.awt.Insets;
import borland.jbcl.view.ImageItemPainter;
import java.awt.image.ImageObserver;
import java.net.MalformedURLException;
import java.awt.Component;
import borland.jbcl.util.ImageLoader;
import borland.jbcl.model.SingletonModel;
import borland.jbcl.model.BasicSingletonContainer;
import java.net.URL;
import java.awt.Image;
import borland.jbcl.util.BlackBox;
import borland.jbcl.view.ButtonView;

public class ButtonControl extends ButtonView implements BlackBox
{
    private int orientation;
    private boolean imageFirst;
    private Image image;
    private URL url;
    private String imageName;
    private String label;
    
    public ButtonControl() {
        this.imageFirst = true;
        this.setModel(new BasicSingletonContainer());
        this.setupPainters();
    }
    
    public ButtonControl(final String label) {
        this();
        this.setLabel(label);
    }
    
    public ButtonControl(final Image image) {
        this();
        this.setImage(image);
    }
    
    public ButtonControl(final String label, final Image image) {
        this();
        this.setLabel(label);
        this.setImage(image);
    }
    
    public void setOrientation(final int o) {
        this.orientation = o;
        this.setupPainters();
    }
    
    public int getOrientation() {
        return this.orientation;
    }
    
    public void setImageFirst(final boolean first) {
        this.imageFirst = first;
        this.setupPainters();
    }
    
    public boolean isImageFirst() {
        return this.imageFirst;
    }
    
    public void setLabel(final String l) {
        this.label = l;
        this.setupPainters();
        this.invalidate();
        this.repaint(50L);
    }
    
    public String getLabel() {
        return this.label;
    }
    
    public void setImage(final Image image) {
        this.setImage(image, "");
    }
    
    public Image getImage() {
        return this.image;
    }
    
    public void setImageURL(final URL url) {
        this.url = url;
        this.setImage(ImageLoader.load(url, this), url.toString());
    }
    
    public URL getImageURL() {
        return this.url;
    }
    
    public void setImageName(final String name) {
        Image image = ImageLoader.loadFromResource(name, this);
        if (image == null) {
            try {
                image = ImageLoader.load(new URL(name), this);
            }
            catch (MalformedURLException e) {
                image = ImageLoader.load(name, this);
            }
        }
        this.setImage(image, name);
    }
    
    public String getImageName() {
        return this.imageName;
    }
    
    protected void setImage(final Image im, final String path) {
        if (this.isReadOnly()) {
            throw new IllegalStateException(Res.getString(70));
        }
        if (im != null) {
            this.prepareImage(im, this);
        }
        this.image = im;
        this.imageName = path;
        this.getWriteModel().set(this.image);
        this.setupPainters();
    }
    
    public boolean imageUpdate(final Image im, final int flags, final int x, final int y, final int w, final int h) {
        if ((flags & 0x3) != 0x0) {
            this.invalidate();
        }
        return super.imageUpdate(im, flags, x, y, w, h);
    }
    
    private void setupPainters() {
        final ItemPainter imagePainter = (this.image != null) ? new ImageItemPainter(this, this.getAlignment()) : null;
        final ItemPainter labelPainter = new FocusableItemPainter(new TextItemPainter(this.getAlignment(), new Insets(1, 1, 1, 1)), false);
        ItemPainter painter = null;
        Object data = null;
        if (this.image == null) {
            painter = labelPainter;
            data = this.label;
        }
        else if (this.image != null && this.label == null) {
            painter = new FocusableItemPainter(imagePainter, false);
            data = this.image;
        }
        else if (this.image != null && this.label != null) {
            ItemPainter firstPainter;
            ItemPainter secondPainter;
            if (this.imageFirst) {
                firstPainter = imagePainter;
                secondPainter = labelPainter;
                data = new Pair(this.image, this.label);
            }
            else {
                firstPainter = labelPainter;
                secondPainter = imagePainter;
                data = new Pair(this.label, this.image);
            }
            painter = new CompositeItemPainter(firstPainter, secondPainter, this.orientation, this.getAlignment());
        }
        this.setViewManager(new BasicViewManager(painter, null));
        if (!this.isReadOnly()) {
            this.getWriteModel().set(data);
        }
    }
}

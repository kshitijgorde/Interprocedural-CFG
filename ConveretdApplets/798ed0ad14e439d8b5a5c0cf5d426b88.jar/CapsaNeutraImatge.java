import java.net.URL;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.util.Hashtable;
import java.awt.Point;
import java.awt.Component;
import java.awt.Image;
import java.awt.image.ImageObserver;

// 
// Decompiled by Procyon v0.5.30
// 

public class CapsaNeutraImatge extends NeuterBox implements ImageObserver
{
    int append;
    String clear;
    String[] currentThread;
    private Image[] images;
    private Image[] zimages;
    private int[] z;
    private Component component;
    private Point componentOrigin;
    AbstractBox drawImage;
    boolean drawRect;
    private boolean autosize;
    boolean err;
    static int get;
    static Hashtable getComponent;
    
    public final void calculRect(final BoxComponent boxComponent) {
        final int dpi = boxComponent.getDPI();
        final int styleDim = Attributes.getStyleDim(this.drawImage, "height", 10, boxComponent.getDPI());
        final int styleDim2 = Attributes.getStyleDim(this.drawImage, "width", 10, boxComponent.getDPI());
        if (this.autosize && this.images[this.append] != null) {
            final int width = this.images[this.append].getWidth(this);
            final int height = this.images[this.append].getHeight(this);
            if (width > 0 && height > 0) {
                super.width = width * dpi / 72;
                super.height = height * dpi / 72;
            }
        }
        else {
            super.height = styleDim;
            super.width = styleDim2;
        }
        super.baseline = super.height;
        this.component = boxComponent.getComponent();
        this.componentOrigin = boxComponent.getOrigin();
        for (int i = 0; i < this.images.length; ++i) {
            if (this.images[i] == null && this.currentThread[i] != null && this.currentThread[i].length() > 0) {
                this.images[i] = loadImage(this.I(i), boxComponent.getResourceProvider(), boxComponent);
                if (this.images[i] != null) {
                    Toolkit.getDefaultToolkit().prepareImage(this.images[i], -1, -1, this);
                }
            }
            if (this.images[i] != null && (this.zimages[i] == null || this.z[i] != dpi)) {
                this.zimages[i] = this.images[i].getScaledInstance(super.width, super.height, 16);
                this.z[i] = dpi;
                if (this.zimages[i] != null) {
                    Toolkit.getDefaultToolkit().prepareImage(this.zimages[i], -1, -1, this);
                }
            }
        }
    }
    
    public final void onPaint(final BoxComponent boxComponent, final Graphics graphics) {
        final int dpi = boxComponent.getDPI();
        if (this.images[this.append] == null) {
            if (boxComponent.isDesign()) {
                graphics.drawRect(0, 0, Math.max(super.width - 1, 0), Math.max(super.height - 1, 0));
            }
        }
        else if (dpi == 72) {
            this.drawImage(graphics, this.images[this.append], 0, 0);
        }
        else {
            this.drawImage(graphics, this.zimages[this.append], 0, 0);
        }
    }
    
    public final void drawImage(final Graphics graphics, final Image image, final int n, final int n2) {
        this.err = false;
        if (!graphics.drawImage(image, n, n2, this)) {
            while (!this.err) {
                try {
                    Thread.currentThread();
                    Thread.sleep(1L);
                }
                catch (InterruptedException ex) {}
            }
            graphics.drawImage(image, n, n2, this);
        }
    }
    
    private final String I(final int n) {
        return Utils.urlCat(this.clear, this.currentThread[n]);
    }
    
    public final boolean imageUpdate(final Image image, final int n, int n2, int n3, int width, int height) {
        if (this.drawRect) {
            return false;
        }
        int n4 = -1;
        if ((n & 0x30) != 0x0) {
            n4 = 0;
            n2 = 0;
            n3 = 0;
            width = super.width;
            height = super.height;
        }
        else if ((n & 0x8) != 0x0) {
            n4 = 100;
        }
        if (n4 < 0 || image == this.images[this.append] || image == this.zimages[this.append]) {}
        this.err = ((n & 0xA0) != 0x0);
        return (n & 0xA0) == 0x0;
    }
    
    public final void enEsborrar() {
        this.drawImage = null;
        this.drawRect = true;
        this.component = null;
    }
    
    private static Image loadImage(final String s, final ResourceProvider resourceProvider, final BoxComponent boxComponent) {
        final URL externalResourceURL = resourceProvider.getExternalResourceURL(s, ((FormulaEditorCalc)boxComponent).getWithDocBase());
        Image externalImage;
        if (externalResourceURL != null) {
            externalImage = CapsaNeutraImatge.getComponent.get(externalResourceURL);
        }
        else {
            externalImage = null;
        }
        if (externalImage == null) {
            externalImage = resourceProvider.getExternalImage(s, ((FormulaEditorCalc)boxComponent).getWithDocBase());
            if (externalImage == null) {
                System.err.println("Cannot load image " + s);
            }
            else {
                CapsaNeutraImatge.getComponent.put(externalResourceURL, externalImage);
            }
        }
        return externalImage;
    }
    
    public static final void clearCache() {
        CapsaNeutraImatge.getComponent.clear();
    }
    
    static {
        CapsaNeutraImatge.get = 0;
        CapsaNeutraImatge.getComponent = new Hashtable();
    }
}

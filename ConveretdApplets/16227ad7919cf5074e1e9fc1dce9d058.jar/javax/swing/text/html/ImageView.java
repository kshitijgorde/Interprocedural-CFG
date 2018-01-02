// 
// Decompiled by Procyon v0.5.30
// 

package javax.swing.text.html;

import javax.swing.text.SimpleAttributeSet;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import javax.swing.text.BadLocationException;
import javax.swing.text.Position;
import java.io.IOException;
import java.io.InputStream;
import javax.swing.ImageIcon;
import java.io.ByteArrayOutputStream;
import java.io.BufferedInputStream;
import javax.swing.JEditorPane;
import java.awt.Toolkit;
import java.util.Dictionary;
import javax.swing.text.Document;
import javax.swing.text.AbstractDocument;
import java.net.MalformedURLException;
import java.net.URL;
import javax.swing.text.JTextComponent;
import javax.swing.text.StyledDocument;
import java.awt.Color;
import javax.swing.text.ViewFactory;
import java.awt.Shape;
import javax.swing.event.DocumentEvent;
import javax.swing.Icon;
import java.awt.Point;
import java.awt.Component;
import java.awt.Rectangle;
import java.awt.Container;
import java.awt.Image;
import javax.swing.text.Element;
import javax.swing.text.AttributeSet;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.awt.image.ImageObserver;
import javax.swing.text.View;

class ImageView extends View implements ImageObserver, MouseListener, MouseMotionListener
{
    public static final String TOP = "top";
    public static final String TEXTTOP = "texttop";
    public static final String MIDDLE = "middle";
    public static final String ABSMIDDLE = "absmiddle";
    public static final String CENTER = "center";
    public static final String BOTTOM = "bottom";
    private static boolean sIsInc;
    private static int sIncRate;
    private AttributeSet attr;
    private Element fElement;
    private Image fImage;
    private int fHeight;
    private int fWidth;
    private Container fContainer;
    private Rectangle fBounds;
    private Component fComponent;
    private Point fGrowBase;
    private boolean fGrowProportionally;
    private boolean loading;
    private static Icon sPendingImageIcon;
    private static Icon sMissingImageIcon;
    private static final String PENDING_IMAGE_SRC = "icons/image-delayed.gif";
    private static final String MISSING_IMAGE_SRC = "icons/image-failed.gif";
    private static final boolean DEBUG = false;
    static final String IMAGE_CACHE_PROPERTY = "imageCache";
    private static final int DEFAULT_WIDTH = 32;
    private static final int DEFAULT_HEIGHT = 32;
    private static final int DEFAULT_BORDER = 2;
    static /* synthetic */ Class class$javax$swing$text$html$ImageView;
    
    static {
        ImageView.sIsInc = true;
        ImageView.sIncRate = 100;
    }
    
    public ImageView(final Element element) {
        super(element);
        this.initialize(element);
        this.attr = this.getStyleSheet().getViewAttributes(this);
    }
    
    public void changedUpdate(final DocumentEvent documentEvent, final Shape shape, final ViewFactory viewFactory) {
        super.changedUpdate(documentEvent, shape, viewFactory);
        final float verticalAlignment = this.getVerticalAlignment();
        final int fHeight = this.fHeight;
        final int fWidth = this.fWidth;
        this.initialize(this.getElement());
        final boolean b = this.fHeight != fHeight;
        final boolean b2 = this.fWidth != fWidth;
        if (b || b2 || this.getVerticalAlignment() != verticalAlignment) {
            this.getParent().preferenceChanged(this, b, b2);
        }
    }
    
    static /* synthetic */ Class class$(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
    
    public float getAlignment(final int n) {
        switch (n) {
            case 1: {
                return this.getVerticalAlignment();
            }
            default: {
                return super.getAlignment(n);
            }
        }
    }
    
    public AttributeSet getAttributes() {
        return this.attr;
    }
    
    int getBorder() {
        return this.getIntAttr(HTML.Attribute.BORDER, this.isLink() ? 2 : 0);
    }
    
    Color getBorderColor() {
        return ((StyledDocument)this.getDocument()).getForeground(this.getAttributes());
    }
    
    protected Color getHighlightColor() {
        return ((JTextComponent)this.fContainer).getSelectionColor();
    }
    
    private int getIntAttr(final HTML.Attribute attribute, final int n) {
        final AttributeSet attributes = this.fElement.getAttributes();
        if (attributes.isDefined(attribute)) {
            final String s = (String)attributes.getAttribute(attribute);
            int max;
            if (s == null) {
                max = n;
            }
            else {
                try {
                    max = Math.max(0, Integer.parseInt(s));
                }
                catch (NumberFormatException ex) {
                    max = n;
                }
            }
            return max;
        }
        return n;
    }
    
    public float getPreferredSpan(final int n) {
        final int n2 = 2 * (this.getBorder() + this.getSpace(n));
        switch (n) {
            case 0: {
                return this.fWidth + n2;
            }
            case 1: {
                return this.fHeight + n2;
            }
            default: {
                throw new IllegalArgumentException("Invalid axis: " + n);
            }
        }
    }
    
    protected int getSelectionState() {
        final int startOffset = this.fElement.getStartOffset();
        final int endOffset = this.fElement.getEndOffset();
        if (this.fContainer instanceof JTextComponent) {
            final JTextComponent textComponent = (JTextComponent)this.fContainer;
            final int selectionStart = textComponent.getSelectionStart();
            final int selectionEnd = textComponent.getSelectionEnd();
            if (selectionStart <= startOffset && selectionEnd >= endOffset) {
                if (selectionStart == startOffset && selectionEnd == endOffset && this.isEditable()) {
                    return 2;
                }
                return 1;
            }
        }
        return 0;
    }
    
    private URL getSourceURL() {
        final String s = (String)this.fElement.getAttributes().getAttribute(HTML.Attribute.SRC);
        if (s == null) {
            return null;
        }
        final URL base = ((HTMLDocument)this.getDocument()).getBase();
        try {
            return new URL(base, s);
        }
        catch (MalformedURLException ex) {
            return null;
        }
    }
    
    int getSpace(final int n) {
        return this.getIntAttr((n == 0) ? HTML.Attribute.HSPACE : HTML.Attribute.VSPACE, 0);
    }
    
    protected StyleSheet getStyleSheet() {
        return ((HTMLDocument)this.getDocument()).getStyleSheet();
    }
    
    float getVerticalAlignment() {
        final String s = (String)this.fElement.getAttributes().getAttribute(HTML.Attribute.ALIGN);
        if (s != null) {
            final String lowerCase = s.toLowerCase();
            if (lowerCase.equals("top") || lowerCase.equals("texttop")) {
                return 0.0f;
            }
            if (lowerCase.equals("center") || lowerCase.equals("middle") || lowerCase.equals("absmiddle")) {
                return 0.5f;
            }
        }
        return 1.0f;
    }
    
    boolean hasPixels(final ImageObserver imageObserver) {
        return this.fImage != null && this.fImage.getHeight(imageObserver) > 0 && this.fImage.getWidth(imageObserver) > 0;
    }
    
    public boolean imageUpdate(final Image image, final int n, final int n2, final int n3, final int fWidth, final int fHeight) {
        if (this.fImage == null || this.fImage != image) {
            return false;
        }
        if ((n & 0xC0) != 0x0) {
            this.fImage = null;
            this.repaint(0L);
            return false;
        }
        short n4 = 0;
        if ((n & 0x2) != 0x0 && !this.getElement().getAttributes().isDefined(HTML.Attribute.HEIGHT)) {
            n4 |= 0x1;
        }
        if ((n & 0x1) != 0x0 && !this.getElement().getAttributes().isDefined(HTML.Attribute.WIDTH)) {
            n4 |= 0x2;
        }
        synchronized (this) {
            if ((n4 & 0x1) == 0x1) {
                this.fWidth = fWidth;
            }
            if ((n4 & 0x2) == 0x2) {
                this.fHeight = fHeight;
            }
            if (this.loading) {
                // monitorexit(this)
                return true;
            }
        }
        if (n4 != 0) {
            final Document document = this.getDocument();
            try {
                if (document instanceof AbstractDocument) {
                    ((AbstractDocument)document).readLock();
                }
                this.preferenceChanged(this, true, true);
            }
            finally {
                if (document instanceof AbstractDocument) {
                    ((AbstractDocument)document).readUnlock();
                }
            }
            return true;
        }
        if ((n & 0x30) != 0x0) {
            this.repaint(0L);
        }
        else if ((n & 0x8) != 0x0 && ImageView.sIsInc) {
            this.repaint(ImageView.sIncRate);
        }
        return (n & 0x20) == 0x0;
    }
    
    private void initialize(final Element fElement) {
        synchronized (this) {
            this.loading = true;
            final boolean b = false;
            this.fHeight = (b ? 1 : 0);
            this.fWidth = (b ? 1 : 0);
        }
        int fWidth = 0;
        int fHeight = 0;
        boolean b2 = false;
        boolean b3 = false;
        try {
            (this.fElement = fElement).getAttributes();
            final URL sourceURL = this.getSourceURL();
            if (sourceURL != null) {
                final Dictionary dictionary = (Dictionary)this.getDocument().getProperty("imageCache");
                if (dictionary != null) {
                    this.fImage = dictionary.get(sourceURL);
                }
                else {
                    this.fImage = Toolkit.getDefaultToolkit().getImage(sourceURL);
                }
            }
            fHeight = this.getIntAttr(HTML.Attribute.HEIGHT, -1);
            b3 = (fHeight > 0);
            if (!b3 && this.fImage != null) {
                fHeight = this.fImage.getHeight(this);
            }
            if (fHeight <= 0) {
                fHeight = 32;
            }
            fWidth = this.getIntAttr(HTML.Attribute.WIDTH, -1);
            b2 = (fWidth > 0);
            if (!b2 && this.fImage != null) {
                fWidth = this.fImage.getWidth(this);
            }
            if (fWidth <= 0) {
                fWidth = 32;
            }
            if (this.fImage != null) {
                if (b2 && b3) {
                    Toolkit.getDefaultToolkit().prepareImage(this.fImage, fHeight, fWidth, this);
                }
                else {
                    Toolkit.getDefaultToolkit().prepareImage(this.fImage, -1, -1, this);
                }
            }
        }
        finally {
            synchronized (this) {
                this.loading = false;
                if (b2 || this.fWidth == 0) {
                    this.fWidth = fWidth;
                }
                if (b3 || this.fHeight == 0) {
                    this.fHeight = fHeight;
                }
            }
        }
    }
    
    protected boolean isEditable() {
        return this.fContainer instanceof JEditorPane && ((JEditorPane)this.fContainer).isEditable();
    }
    
    boolean isLink() {
        final AttributeSet set = (AttributeSet)this.fElement.getAttributes().getAttribute(HTML.Tag.A);
        return set != null && set.isDefined(HTML.Attribute.HREF);
    }
    
    private void loadIcons() {
        try {
            if (ImageView.sPendingImageIcon == null) {
                ImageView.sPendingImageIcon = this.makeIcon("icons/image-delayed.gif");
            }
            if (ImageView.sMissingImageIcon == null) {
                ImageView.sMissingImageIcon = this.makeIcon("icons/image-failed.gif");
            }
        }
        catch (Exception ex) {
            System.err.println("ImageView: Couldn't load image icons");
        }
    }
    
    private Icon makeIcon(final String s) throws IOException {
        final InputStream resourceAsStream = HTMLEditorKit.getResourceAsStream(s);
        if (resourceAsStream == null) {
            System.err.println(String.valueOf(((ImageView.class$javax$swing$text$html$ImageView != null) ? ImageView.class$javax$swing$text$html$ImageView : (ImageView.class$javax$swing$text$html$ImageView = class$("javax.swing.text.html.ImageView"))).getName()) + "/" + s + " not found.");
            return null;
        }
        final BufferedInputStream bufferedInputStream = new BufferedInputStream(resourceAsStream);
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(1024);
        final byte[] array = new byte[1024];
        int read;
        while ((read = bufferedInputStream.read(array)) > 0) {
            byteArrayOutputStream.write(array, 0, read);
        }
        bufferedInputStream.close();
        byteArrayOutputStream.flush();
        final byte[] byteArray = byteArrayOutputStream.toByteArray();
        if (byteArray.length == 0) {
            System.err.println("warning: " + s + " is zero-length");
            return null;
        }
        return new ImageIcon(byteArray);
    }
    
    public Shape modelToView(final int n, final Shape shape, final Position.Bias bias) throws BadLocationException {
        final int startOffset = this.getStartOffset();
        final int endOffset = this.getEndOffset();
        if (n >= startOffset && n <= endOffset) {
            final Rectangle bounds = shape.getBounds();
            if (n == endOffset) {
                final Rectangle rectangle = bounds;
                rectangle.x += bounds.width;
            }
            bounds.width = 0;
            return bounds;
        }
        return null;
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
        mouseEvent.getClickCount();
    }
    
    public void mouseDragged(final MouseEvent mouseEvent) {
        if (this.fGrowBase != null) {
            final Point locationOnScreen = this.fComponent.getLocationOnScreen();
            int max = Math.max(2, locationOnScreen.x + mouseEvent.getX() - this.fGrowBase.x);
            int max2 = Math.max(2, locationOnScreen.y + mouseEvent.getY() - this.fGrowBase.y);
            if (mouseEvent.isShiftDown() && this.fImage != null) {
                final float n = this.fImage.getWidth(this);
                final float n2 = this.fImage.getHeight(this);
                if (n > 0.0f && n2 > 0.0f) {
                    final float n3 = n2 / n;
                    final float n4 = max2 / n3;
                    final float n5 = max * n3;
                    if (n4 > max) {
                        max = (int)n4;
                    }
                    else {
                        max2 = (int)n5;
                    }
                }
            }
            this.resize(max, max2);
        }
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
    }
    
    public void mouseMoved(final MouseEvent mouseEvent) {
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        final Dimension size = this.fComponent.getSize();
        if (mouseEvent.getX() >= size.width - 7 && mouseEvent.getY() >= size.height - 7 && this.getSelectionState() == 2) {
            final Point locationOnScreen = this.fComponent.getLocationOnScreen();
            this.fGrowBase = new Point(locationOnScreen.x + mouseEvent.getX() - this.fWidth, locationOnScreen.y + mouseEvent.getY() - this.fHeight);
            this.fGrowProportionally = mouseEvent.isShiftDown();
        }
        else {
            this.fGrowBase = null;
            final JTextComponent textComponent = (JTextComponent)this.fContainer;
            final int startOffset = this.fElement.getStartOffset();
            final int endOffset = this.fElement.getEndOffset();
            final int mark = textComponent.getCaret().getMark();
            final int dot = textComponent.getCaret().getDot();
            if (mouseEvent.isShiftDown()) {
                if (mark <= startOffset) {
                    textComponent.moveCaretPosition(endOffset);
                }
                else {
                    textComponent.moveCaretPosition(startOffset);
                }
            }
            else {
                if (mark != startOffset) {
                    textComponent.setCaretPosition(startOffset);
                }
                if (dot != endOffset) {
                    textComponent.moveCaretPosition(endOffset);
                }
            }
        }
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
        this.fGrowBase = null;
    }
    
    public void paint(final Graphics graphics, final Shape shape) {
        final Color color = graphics.getColor();
        this.fBounds = shape.getBounds();
        int border = this.getBorder();
        int n = this.fBounds.x + border + this.getSpace(0);
        int n2 = this.fBounds.y + border + this.getSpace(1);
        int fWidth = this.fWidth;
        int fHeight = this.fHeight;
        final int selectionState = this.getSelectionState();
        if (!this.hasPixels(this)) {
            graphics.setColor(Color.lightGray);
            graphics.drawRect(n, n2, fWidth - 1, fHeight - 1);
            graphics.setColor(color);
            this.loadIcons();
            final Icon icon = (this.fImage == null) ? ImageView.sMissingImageIcon : ImageView.sPendingImageIcon;
            if (icon != null) {
                icon.paintIcon(this.getContainer(), graphics, n, n2);
            }
        }
        if (this.fImage != null) {
            graphics.drawImage(this.fImage, n, n2, fWidth, fHeight, this);
        }
        Color borderColor = this.getBorderColor();
        if (selectionState == 2) {
            final int n3 = 2 - border;
            if (n3 > 0) {
                n += n3;
                n2 += n3;
                fWidth -= n3 << 1;
                fHeight -= n3 << 1;
                border = 2;
            }
            borderColor = null;
            graphics.setColor(Color.black);
            graphics.fillRect(n + fWidth - 5, n2 + fHeight - 5, 5, 5);
        }
        if (border > 0) {
            if (borderColor != null) {
                graphics.setColor(borderColor);
            }
            for (int i = 1; i <= border; ++i) {
                graphics.drawRect(n - i, n2 - i, fWidth - 1 + i + i, fHeight - 1 + i + i);
            }
            graphics.setColor(color);
        }
    }
    
    protected void repaint(final long n) {
        if (this.fContainer != null && this.fBounds != null) {
            this.fContainer.repaint(n, this.fBounds.x, this.fBounds.y, this.fBounds.width, this.fBounds.height);
        }
    }
    
    protected void resize(final int fWidth, final int fHeight) {
        if (fWidth == this.fWidth && fHeight == this.fHeight) {
            return;
        }
        this.fWidth = fWidth;
        this.fHeight = fHeight;
        final SimpleAttributeSet set = new SimpleAttributeSet();
        set.addAttribute(HTML.Attribute.WIDTH, Integer.toString(fWidth));
        set.addAttribute(HTML.Attribute.HEIGHT, Integer.toString(fHeight));
        ((StyledDocument)this.getDocument()).setCharacterAttributes(this.fElement.getStartOffset(), this.fElement.getEndOffset(), set, false);
    }
    
    public void setParent(final View parent) {
        super.setParent(parent);
        this.fContainer = ((parent != null) ? this.getContainer() : null);
        if (parent == null && this.fComponent != null) {
            this.fComponent.getParent().remove(this.fComponent);
            this.fComponent = null;
        }
    }
    
    public void setSize(final float n, final float n2) {
    }
    
    public int viewToModel(final float n, final float n2, final Shape shape, final Position.Bias[] array) {
        final Rectangle rectangle = (Rectangle)shape;
        if (n < rectangle.x + rectangle.width) {
            array[0] = Position.Bias.Forward;
            return this.getStartOffset();
        }
        array[0] = Position.Bias.Backward;
        return this.getEndOffset();
    }
}

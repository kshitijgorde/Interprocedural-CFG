// 
// Decompiled by Procyon v0.5.30
// 

package jclass.bwt;

import java.awt.Event;
import java.awt.Graphics;
import jclass.util.JCStringTokenizer;
import java.awt.Font;
import java.awt.Insets;
import java.awt.Component;
import jclass.util.JCString;
import java.awt.Image;
import java.applet.Applet;
import java.net.URL;
import java.awt.Rectangle;

public class JCLabel extends JCComponent
{
    public static final int TOPLEFT = 0;
    public static final int LEFT = 0;
    public static final int TOPCENTER = 1;
    public static final int CENTER = 1;
    public static final int TOPRIGHT = 2;
    public static final int RIGHT = 2;
    public static final int MIDDLELEFT = 3;
    public static final int MIDDLECENTER = 4;
    public static final int MIDDLE = 4;
    public static final int MIDDLERIGHT = 5;
    public static final int BOTTOMLEFT = 6;
    public static final int BOTTOMCENTER = 7;
    public static final int BOTTOMRIGHT = 8;
    public static final int SHADOW_NONE = 0;
    public static final int SHADOW_ETCHED_IN = 1;
    public static final int SHADOW_ETCHED_OUT = 2;
    public static final int SHADOW_IN = 3;
    public static final int SHADOW_OUT = 4;
    public static final int SHADOW_PLAIN = 5;
    public static final int SHADOW_FRAME_IN = 6;
    public static final int SHADOW_FRAME_OUT = 7;
    int alignment;
    Object label;
    protected int label_width;
    protected int label_height;
    protected Rectangle label_rect;
    protected transient String url;
    transient URL label_url;
    private static final String base = "label";
    private static int nameCounter;
    
    public JCLabel() {
        this(null, null, null);
    }
    
    public JCLabel(final Object o) {
        this(o, null, null);
    }
    
    public JCLabel(final String s, final Image image, final int n) {
        this(new JCString(s, image, n), null, null);
    }
    
    public JCLabel(final String s, final String s2, final Applet applet, final int n) {
        this(null, null, null);
        this.setLabel(new JCString(s, JCComponent.conv.toImage(applet, s2), n));
    }
    
    public JCLabel(final Object label, final Applet applet, final String s) {
        super(applet, s);
        this.alignment = 4;
        this.label_rect = new Rectangle();
        if (s == null) {
            this.setName(String.valueOf("label").concat(String.valueOf(JCLabel.nameCounter++)));
        }
        final boolean b = false;
        super.shadow = (b ? 1 : 0);
        super.highlight = (b ? 1 : 0);
        super.traversable = false;
        super.insets = new Insets(2, 5, 2, 5);
        if (this.getClass().getName().equals("jclass.bwt.JCLabel")) {
            this.getParameters(applet);
        }
        if (label != null) {
            this.setLabel(label);
        }
    }
    
    protected void getParameters() {
        super.getParameters();
        LabelConverter.getParams(this);
    }
    
    public int getAlignment() {
        return this.alignment;
    }
    
    public void setAlignment(final int alignment) {
        LabelConverter.checkAlignment(alignment);
        if (alignment != this.alignment) {
            this.alignment = alignment;
            this.layout();
            this.repaint();
        }
    }
    
    public synchronized void setFont(final Font font) {
        final boolean b = this.getFont() != null && !this.getFont().equals(font);
        super.setFont(font);
        if (b) {
            this.setLabelSize(this.label);
            this.layout();
            this.repaint();
        }
    }
    
    synchronized void setFontInternal(final Font font) {
        super.setFont(font);
        this.setLabelSize(this.label);
        this.layout();
    }
    
    public Object getLabel() {
        return this.label;
    }
    
    public void setLabel(final Object label) {
        this.setLabelSize(this.label = label);
        this.layout();
        this.repaint();
    }
    
    public String getText() {
        return (this.label != null) ? this.label.toString() : null;
    }
    
    public void setText(final String label) {
        this.setLabel(label);
    }
    
    public String[] getTextList() {
        if (this.label == null) {
            return null;
        }
        final JCStringTokenizer jcStringTokenizer = new JCStringTokenizer(this.label.toString());
        final String[] array = new String[jcStringTokenizer.countTokens('\n')];
        int n = 0;
        while (jcStringTokenizer.hasMoreTokens()) {
            array[n] = jcStringTokenizer.nextToken('\n').trim();
            ++n;
        }
        return array;
    }
    
    public void setTextList(final String[] array) {
        if (array == null) {
            this.setLabel(null);
            return;
        }
        final StringBuffer sb = new StringBuffer();
        for (int i = 0; i < array.length; ++i) {
            sb.append(array[i]);
            if (i < array.length - 1) {
                sb.append('\n');
            }
        }
        this.setLabel(new String(sb));
    }
    
    public URL getLabelImage() {
        return this.label_url;
    }
    
    public void setLabelImage(final URL label_url) {
        this.label_url = label_url;
        this.setLabel(this.getToolkit().getImage(label_url));
    }
    
    public int getShadowType() {
        return super.shadow_type;
    }
    
    public void setShadowType(final int shadow_type) {
        super.shadow_type = shadow_type;
        this.layout();
        this.repaint();
    }
    
    protected synchronized void drawValue(final Graphics graphics, final Object o) {
        BWTUtil.draw(this, graphics, o, this.alignment, this.label_rect);
    }
    
    protected void paintComponent(final Graphics graphics) {
        if (super.needs_layout) {
            this.layout();
        }
        this.drawValue(graphics, this.label);
    }
    
    protected void setLabelSize(final Object o) {
        this.setLabelSize(o, this.getFont());
    }
    
    protected void setLabelSize(final Object o, final Font font) {
        if (this.getPeer() == null) {
            return;
        }
        this.label_width = BWTUtil.getWidth(o, this, font);
        this.label_height = BWTUtil.getHeight(o, this, font);
        if (super.applet_context != null && BWTUtil.is_jcstring(o)) {
            this.enableEvents(32L);
        }
    }
    
    public void addNotify() {
        super.addNotify();
        this.setLabelSize(this.label);
        if (super.needs_layout) {
            this.layout();
        }
    }
    
    protected int preferredWidth() {
        return Math.max(20, this.label_width);
    }
    
    protected int preferredHeight() {
        return Math.max(20, this.label_height);
    }
    
    public Rectangle getLabelBounds() {
        return this.label_rect;
    }
    
    public synchronized void layout() {
        if (this.getPeer() == null) {
            return;
        }
        this.getDrawingArea(this.label_rect);
        if (this.label_rect.width <= 0 || this.label_rect.height <= 0) {
            super.needs_layout = true;
            return;
        }
        super.needs_layout = false;
        if (BWTUtil.isRight(this.alignment)) {
            final Rectangle label_rect = this.label_rect;
            label_rect.x += this.label_rect.width - this.label_width;
        }
        else if (BWTUtil.isCenter(this.alignment)) {
            final Rectangle label_rect2 = this.label_rect;
            label_rect2.x += (this.label_rect.width - this.label_width) / 2;
        }
        if (BWTUtil.isMiddle(this.alignment)) {
            final Rectangle label_rect3 = this.label_rect;
            label_rect3.y += (this.label_rect.height - this.label_height) / 2;
        }
        else if (BWTUtil.isBottom(this.alignment)) {
            final Rectangle label_rect4 = this.label_rect;
            label_rect4.y += this.label_rect.height - this.label_height;
        }
        this.label_rect.resize(this.label_width, this.label_height);
    }
    
    protected void layout(final Object labelSize) {
        this.setLabelSize(labelSize);
        this.layout();
    }
    
    public boolean mouseMove(final Event event, final int n, final int n2) {
        if (super.applet_context != null && BWTUtil.is_jcstring(this.label)) {
            this.url = JCString.getURL(super.applet, this.label, n, n2);
            if (this.url != null) {
                this.setCursor(12);
                return true;
            }
        }
        this.url = null;
        this.setCursor(0);
        return false;
    }
    
    public boolean mouseUp(final Event event, final int n, final int n2) {
        if (this.url != null) {
            JCString.showURL(this.url, super.applet_context, super.applet);
        }
        return false;
    }
    
    static {
        JCLabel.nameCounter = 0;
    }
}

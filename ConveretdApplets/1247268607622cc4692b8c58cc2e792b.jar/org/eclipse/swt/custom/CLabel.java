// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.custom;

import org.eclipse.swt.graphics.Device;
import org.eclipse.swt.graphics.TextLayout;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.SWTException;
import org.eclipse.swt.accessibility.Accessible;
import org.eclipse.swt.accessibility.AccessibleControlListener;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.accessibility.AccessibleControlEvent;
import org.eclipse.swt.accessibility.AccessibleControlAdapter;
import org.eclipse.swt.accessibility.AccessibleListener;
import org.eclipse.swt.accessibility.AccessibleEvent;
import org.eclipse.swt.accessibility.AccessibleAdapter;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.graphics.Drawable;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.events.TraverseEvent;
import org.eclipse.swt.events.TraverseListener;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Canvas;

public class CLabel extends Canvas
{
    private static final int GAP = 5;
    private static final int DEFAULT_MARGIN = 3;
    private static final String ELLIPSIS = "...";
    private int align;
    private int leftMargin;
    private int topMargin;
    private int rightMargin;
    private int bottomMargin;
    private String text;
    private Image image;
    private String appToolTipText;
    private boolean ignoreDispose;
    private Image backgroundImage;
    private Color[] gradientColors;
    private int[] gradientPercents;
    private boolean gradientVertical;
    private Color background;
    private static int DRAW_FLAGS;
    
    static {
        CLabel.DRAW_FLAGS = 15;
    }
    
    public CLabel(final Composite composite, int n) {
        super(composite, checkStyle(n));
        this.align = 16384;
        this.leftMargin = 3;
        this.topMargin = 3;
        this.rightMargin = 3;
        this.bottomMargin = 3;
        if ((n & 0x1020000) == 0x0) {
            n |= 0x4000;
        }
        if ((n & 0x1000000) != 0x0) {
            this.align = 16777216;
        }
        if ((n & 0x20000) != 0x0) {
            this.align = 131072;
        }
        if ((n & 0x4000) != 0x0) {
            this.align = 16384;
        }
        this.addPaintListener(new PaintListener() {
            public void paintControl(final PaintEvent paintEvent) {
                CLabel.this.onPaint(paintEvent);
            }
        });
        this.addTraverseListener(new TraverseListener() {
            public void keyTraversed(final TraverseEvent traverseEvent) {
                if (traverseEvent.detail == 128) {
                    CLabel.this.onMnemonic(traverseEvent);
                }
            }
        });
        this.addListener(12, new Listener() {
            public void handleEvent(final Event event) {
                CLabel.this.onDispose(event);
            }
        });
        this.initAccessible();
    }
    
    private static int checkStyle(int n) {
        if ((n & 0x800) != 0x0) {
            n |= 0x4;
        }
        n &= 0x600002C;
        return n |= 0x20080000;
    }
    
    public Point computeSize(final int x, final int y, final boolean b) {
        this.checkWidget();
        final Point totalSize = this.getTotalSize(this.image, this.text);
        if (x == -1) {
            final Point point = totalSize;
            point.x += this.leftMargin + this.rightMargin;
        }
        else {
            totalSize.x = x;
        }
        if (y == -1) {
            final Point point2 = totalSize;
            point2.y += this.topMargin + this.bottomMargin;
        }
        else {
            totalSize.y = y;
        }
        return totalSize;
    }
    
    private void drawBevelRect(final GC gc, final int n, final int n2, final int n3, final int n4, final Color foreground, final Color foreground2) {
        gc.setForeground(foreground2);
        gc.drawLine(n + n3, n2, n + n3, n2 + n4);
        gc.drawLine(n, n2 + n4, n + n3, n2 + n4);
        gc.setForeground(foreground);
        gc.drawLine(n, n2, n + n3 - 1, n2);
        gc.drawLine(n, n2, n, n2 + n4 - 1);
    }
    
    char _findMnemonic(final String s) {
        if (s == null) {
            return '\0';
        }
        int n = 0;
        final int length = s.length();
        while (true) {
            if (n >= length || s.charAt(n) == '&') {
                if (++n >= length) {
                    return '\0';
                }
                if (s.charAt(n) != '&') {
                    return Character.toLowerCase(s.charAt(n));
                }
                if (++n >= length) {
                    return '\0';
                }
                continue;
            }
            else {
                ++n;
            }
        }
    }
    
    public int getAlignment() {
        return this.align;
    }
    
    public int getBottomMargin() {
        return this.bottomMargin;
    }
    
    public Image getImage() {
        return this.image;
    }
    
    public int getLeftMargin() {
        return this.leftMargin;
    }
    
    public int getRightMargin() {
        return this.rightMargin;
    }
    
    private Point getTotalSize(final Image image, final String s) {
        final Point point = new Point(0, 0);
        if (image != null) {
            final Rectangle bounds = image.getBounds();
            final Point point2 = point;
            point2.x += bounds.width;
            final Point point3 = point;
            point3.y += bounds.height;
        }
        final GC gc = new GC(this);
        if (s != null && s.length() > 0) {
            final Point textExtent = gc.textExtent(s, CLabel.DRAW_FLAGS);
            final Point point4 = point;
            point4.x += textExtent.x;
            point.y = Math.max(point.y, textExtent.y);
            if (image != null) {
                final Point point5 = point;
                point5.x += 5;
            }
        }
        else {
            point.y = Math.max(point.y, gc.getFontMetrics().getHeight());
        }
        gc.dispose();
        return point;
    }
    
    public int getStyle() {
        int style = super.getStyle();
        switch (this.align) {
            case 131072: {
                style |= 0x20000;
                break;
            }
            case 16777216: {
                style |= 0x1000000;
                break;
            }
            case 16384: {
                style |= 0x4000;
                break;
            }
        }
        return style;
    }
    
    public String getText() {
        return this.text;
    }
    
    public String getToolTipText() {
        this.checkWidget();
        return this.appToolTipText;
    }
    
    public int getTopMargin() {
        return this.topMargin;
    }
    
    private void initAccessible() {
        final Accessible accessible = this.getAccessible();
        accessible.addAccessibleListener(new AccessibleAdapter() {
            public void getName(final AccessibleEvent accessibleEvent) {
                accessibleEvent.result = CLabel.this.getText();
            }
            
            public void getHelp(final AccessibleEvent accessibleEvent) {
                accessibleEvent.result = CLabel.this.getToolTipText();
            }
            
            public void getKeyboardShortcut(final AccessibleEvent accessibleEvent) {
                final char findMnemonic = CLabel.this._findMnemonic(CLabel.this.text);
                if (findMnemonic != '\0') {
                    accessibleEvent.result = "Alt+" + findMnemonic;
                }
            }
        });
        accessible.addAccessibleControlListener(new AccessibleControlAdapter() {
            public void getChildAtPoint(final AccessibleControlEvent accessibleControlEvent) {
                accessibleControlEvent.childID = -1;
            }
            
            public void getLocation(final AccessibleControlEvent accessibleControlEvent) {
                final Rectangle map = CLabel.this.getDisplay().map(CLabel.this.getParent(), null, CLabel.this.getBounds());
                accessibleControlEvent.x = map.x;
                accessibleControlEvent.y = map.y;
                accessibleControlEvent.width = map.width;
                accessibleControlEvent.height = map.height;
            }
            
            public void getChildCount(final AccessibleControlEvent accessibleControlEvent) {
                accessibleControlEvent.detail = 0;
            }
            
            public void getRole(final AccessibleControlEvent accessibleControlEvent) {
                accessibleControlEvent.detail = 41;
            }
            
            public void getState(final AccessibleControlEvent accessibleControlEvent) {
                accessibleControlEvent.detail = 64;
            }
        });
    }
    
    void onDispose(final Event event) {
        if (this.ignoreDispose) {
            this.ignoreDispose = false;
            return;
        }
        this.ignoreDispose = true;
        this.notifyListeners(event.type, event);
        event.type = 0;
        this.gradientColors = null;
        this.gradientPercents = null;
        this.backgroundImage = null;
        this.text = null;
        this.image = null;
        this.appToolTipText = null;
    }
    
    void onMnemonic(final TraverseEvent traverseEvent) {
        final char findMnemonic = this._findMnemonic(this.text);
        if (findMnemonic == '\0') {
            return;
        }
        if (Character.toLowerCase(traverseEvent.character) != findMnemonic) {
            return;
        }
        for (Composite composite = this.getParent(); composite != null; composite = composite.getParent()) {
            Control[] children;
            int n;
            for (children = composite.getChildren(), n = 0; n < children.length && children[n] != this; ++n) {}
            if (++n < children.length && children[n].setFocus()) {
                traverseEvent.doit = true;
                traverseEvent.detail = 0;
            }
        }
    }
    
    void onPaint(final PaintEvent paintEvent) {
        final Rectangle clientArea = this.getClientArea();
        if (clientArea.width == 0 || clientArea.height == 0) {
            return;
        }
        boolean b = false;
        final String text = this.text;
        Image image = this.image;
        final int max = Math.max(0, clientArea.width - (this.leftMargin + this.rightMargin));
        Point point = this.getTotalSize(image, text);
        if (point.x > max) {
            image = null;
            point = this.getTotalSize(image, text);
            if (point.x > max) {
                b = true;
            }
        }
        final GC gc = paintEvent.gc;
        final String[] array = (String[])((this.text == null) ? null : this.splitString(this.text));
        if (b) {
            point.x = 0;
            for (int i = 0; i < array.length; ++i) {
                final Point textExtent = gc.textExtent(array[i], CLabel.DRAW_FLAGS);
                if (textExtent.x > max) {
                    array[i] = this.shortenText(gc, array[i], max);
                    point.x = Math.max(point.x, this.getTotalSize(null, array[i]).x);
                }
                else {
                    point.x = Math.max(point.x, textExtent.x);
                }
            }
            if (this.appToolTipText == null) {
                super.setToolTipText(this.text);
            }
        }
        else {
            super.setToolTipText(this.appToolTipText);
        }
        int n = clientArea.x + this.leftMargin;
        if (this.align == 16777216) {
            n = (clientArea.width - point.x) / 2;
        }
        if (this.align == 131072) {
            n = clientArea.width - this.rightMargin - point.x;
        }
        try {
            if (this.backgroundImage != null) {
                final Rectangle bounds = this.backgroundImage.getBounds();
                gc.setBackground(this.getBackground());
                gc.fillRectangle(clientArea);
                for (int j = 0; j < clientArea.width; j += bounds.width) {
                    for (int k = 0; k < clientArea.height; k += bounds.height) {
                        gc.drawImage(this.backgroundImage, j, k);
                    }
                }
            }
            else if (this.gradientColors != null) {
                final Color background = gc.getBackground();
                if (this.gradientColors.length == 1) {
                    if (this.gradientColors[0] != null) {
                        gc.setBackground(this.gradientColors[0]);
                    }
                    gc.fillRectangle(0, 0, clientArea.width, clientArea.height);
                }
                else {
                    final Color foreground = gc.getForeground();
                    Color color = this.gradientColors[0];
                    if (color == null) {
                        color = background;
                    }
                    int n2 = 0;
                    for (int l = 0; l < this.gradientPercents.length; ++l) {
                        gc.setForeground(color);
                        color = this.gradientColors[l + 1];
                        if (color == null) {
                            color = background;
                        }
                        gc.setBackground(color);
                        if (this.gradientVertical) {
                            final int n3 = this.gradientPercents[l] * clientArea.height / 100 - n2;
                            gc.fillGradientRectangle(0, n2, clientArea.width, n3, true);
                            n2 += n3;
                        }
                        else {
                            final int n4 = this.gradientPercents[l] * clientArea.width / 100 - n2;
                            gc.fillGradientRectangle(n2, 0, n4, clientArea.height, false);
                            n2 += n4;
                        }
                    }
                    if (this.gradientVertical && n2 < clientArea.height) {
                        gc.setBackground(this.getBackground());
                        gc.fillRectangle(0, n2, clientArea.width, clientArea.height - n2);
                    }
                    if (!this.gradientVertical && n2 < clientArea.width) {
                        gc.setBackground(this.getBackground());
                        gc.fillRectangle(n2, 0, clientArea.width - n2, clientArea.height);
                    }
                    gc.setForeground(foreground);
                }
                gc.setBackground(background);
            }
            else if (this.background != null || (this.getStyle() & 0x20000000) == 0x0) {
                gc.setBackground(this.getBackground());
                gc.fillRectangle(clientArea);
            }
        }
        catch (SWTException ex) {
            if ((this.getStyle() & 0x20000000) == 0x0) {
                gc.setBackground(this.getBackground());
                gc.fillRectangle(clientArea);
            }
        }
        final int style = this.getStyle();
        if ((style & 0x4) != 0x0 || (style & 0x8) != 0x0) {
            this.paintBorder(gc, clientArea);
        }
        Rectangle bounds2 = null;
        int height = 0;
        int n5 = 0;
        int height2 = 0;
        if (image != null) {
            bounds2 = image.getBounds();
            height2 = bounds2.height;
        }
        if (array != null) {
            height = gc.getFontMetrics().getHeight();
            n5 = array.length * height;
        }
        int topMargin;
        int topMargin2;
        if (height2 > n5) {
            if (this.topMargin == 3 && this.bottomMargin == 3) {
                topMargin = clientArea.y + (clientArea.height - height2) / 2;
            }
            else {
                topMargin = this.topMargin;
            }
            topMargin2 = topMargin + height2 / 2 - n5 / 2;
        }
        else {
            if (this.topMargin == 3 && this.bottomMargin == 3) {
                topMargin2 = clientArea.y + (clientArea.height - n5) / 2;
            }
            else {
                topMargin2 = this.topMargin;
            }
            topMargin = topMargin2 + n5 / 2 - height2 / 2;
        }
        if (image != null) {
            gc.drawImage(image, 0, 0, bounds2.width, height2, n, topMargin, bounds2.width, height2);
            n += bounds2.width + 5;
            final Point point2 = point;
            point2.x -= bounds2.width + 5;
        }
        if (array != null) {
            gc.setForeground(this.getForeground());
            for (int n6 = 0; n6 < array.length; ++n6) {
                int max2 = n;
                if (array.length > 1) {
                    if (this.align == 16777216) {
                        max2 = n + Math.max(0, (point.x - gc.textExtent(array[n6], CLabel.DRAW_FLAGS).x) / 2);
                    }
                    if (this.align == 131072) {
                        max2 = Math.max(n, clientArea.x + clientArea.width - this.rightMargin - gc.textExtent(array[n6], CLabel.DRAW_FLAGS).x);
                    }
                }
                gc.drawText(array[n6], max2, topMargin2, CLabel.DRAW_FLAGS);
                topMargin2 += height;
            }
        }
    }
    
    private void paintBorder(final GC gc, final Rectangle rectangle) {
        final Display display = this.getDisplay();
        Color color = null;
        Color color2 = null;
        final int style = this.getStyle();
        if ((style & 0x4) != 0x0) {
            color = display.getSystemColor(18);
            color2 = display.getSystemColor(20);
        }
        if ((style & 0x8) != 0x0) {
            color = display.getSystemColor(19);
            color2 = display.getSystemColor(18);
        }
        if (color != null && color2 != null) {
            gc.setLineWidth(1);
            this.drawBevelRect(gc, rectangle.x, rectangle.y, rectangle.width - 1, rectangle.height - 1, color, color2);
        }
    }
    
    public void setAlignment(final int align) {
        this.checkWidget();
        if (align != 16384 && align != 131072 && align != 16777216) {
            SWT.error(5);
        }
        if (this.align != align) {
            this.align = align;
            this.redraw();
        }
    }
    
    public void setBackground(final Color color) {
        super.setBackground(color);
        if (this.backgroundImage == null && this.gradientColors == null && this.gradientPercents == null) {
            if (color == null) {
                if (this.background == null) {
                    return;
                }
            }
            else if (color.equals(this.background)) {
                return;
            }
        }
        this.background = color;
        this.backgroundImage = null;
        this.gradientColors = null;
        this.gradientPercents = null;
        this.redraw();
    }
    
    public void setBackground(final Color[] array, final int[] array2) {
        this.setBackground(array, array2, false);
    }
    
    public void setBackground(Color[] array, int[] array2, final boolean gradientVertical) {
        this.checkWidget();
        if (array != null) {
            if (array2 == null || array2.length != array.length - 1) {
                SWT.error(5);
            }
            if (this.getDisplay().getDepth() < 15) {
                array = new Color[] { array[array.length - 1] };
                array2 = new int[0];
            }
            for (int i = 0; i < array2.length; ++i) {
                if (array2[i] < 0 || array2[i] > 100) {
                    SWT.error(5);
                }
                if (i > 0 && array2[i] < array2[i - 1]) {
                    SWT.error(5);
                }
            }
        }
        final Color background = this.getBackground();
        if (this.backgroundImage == null) {
            if (this.gradientColors != null && array != null && this.gradientColors.length == array.length) {
                boolean b = false;
                for (int j = 0; j < this.gradientColors.length; ++j) {
                    b = (this.gradientColors[j] == array[j] || (this.gradientColors[j] == null && array[j] == background) || (this.gradientColors[j] == background && array[j] == null));
                    if (!b) {
                        break;
                    }
                }
                if (b) {
                    for (int k = 0; k < this.gradientPercents.length; ++k) {
                        b = (this.gradientPercents[k] == array2[k]);
                        if (!b) {
                            break;
                        }
                    }
                }
                if (b && this.gradientVertical == gradientVertical) {
                    return;
                }
            }
        }
        else {
            this.backgroundImage = null;
        }
        if (array == null) {
            this.gradientColors = null;
            this.gradientPercents = null;
            this.gradientVertical = false;
        }
        else {
            this.gradientColors = new Color[array.length];
            for (int l = 0; l < array.length; ++l) {
                this.gradientColors[l] = ((array[l] != null) ? array[l] : background);
            }
            this.gradientPercents = new int[array2.length];
            for (int n = 0; n < array2.length; ++n) {
                this.gradientPercents[n] = array2[n];
            }
            this.gradientVertical = gradientVertical;
        }
        this.redraw();
    }
    
    public void setBackground(final Image backgroundImage) {
        this.checkWidget();
        if (backgroundImage == this.backgroundImage) {
            return;
        }
        if (backgroundImage != null) {
            this.gradientColors = null;
            this.gradientPercents = null;
        }
        this.backgroundImage = backgroundImage;
        this.redraw();
    }
    
    public void setBottomMargin(final int bottomMargin) {
        this.checkWidget();
        if (this.bottomMargin == bottomMargin || bottomMargin < 0) {
            return;
        }
        this.bottomMargin = bottomMargin;
        this.redraw();
    }
    
    public void setFont(final Font font) {
        super.setFont(font);
        this.redraw();
    }
    
    public void setImage(final Image image) {
        this.checkWidget();
        if (image != this.image) {
            this.image = image;
            this.redraw();
        }
    }
    
    public void setLeftMargin(final int leftMargin) {
        this.checkWidget();
        if (this.leftMargin == leftMargin || leftMargin < 0) {
            return;
        }
        this.leftMargin = leftMargin;
        this.redraw();
    }
    
    public void setMargins(final int n, final int n2, final int n3, final int n4) {
        this.checkWidget();
        this.leftMargin = Math.max(0, n);
        this.topMargin = Math.max(0, n2);
        this.rightMargin = Math.max(0, n3);
        this.bottomMargin = Math.max(0, n4);
        this.redraw();
    }
    
    public void setRightMargin(final int rightMargin) {
        this.checkWidget();
        if (this.rightMargin == rightMargin || rightMargin < 0) {
            return;
        }
        this.rightMargin = rightMargin;
        this.redraw();
    }
    
    public void setText(String text) {
        this.checkWidget();
        if (text == null) {
            text = "";
        }
        if (!text.equals(this.text)) {
            this.text = text;
            this.redraw();
        }
    }
    
    public void setToolTipText(final String toolTipText) {
        super.setToolTipText(toolTipText);
        this.appToolTipText = super.getToolTipText();
    }
    
    public void setTopMargin(final int topMargin) {
        this.checkWidget();
        if (this.topMargin == topMargin || topMargin < 0) {
            return;
        }
        this.topMargin = topMargin;
        this.redraw();
    }
    
    protected String shortenText(final GC gc, final String text, final int n) {
        if (text == null) {
            return null;
        }
        final int x = gc.textExtent("...", CLabel.DRAW_FLAGS).x;
        if (n <= x) {
            return text;
        }
        final int length = text.length();
        int n2 = length / 2;
        int n3 = 0;
        final int n4 = (n2 + n3) / 2 - 1;
        if (n4 <= 0) {
            return text;
        }
        final TextLayout textLayout = new TextLayout(this.getDisplay());
        textLayout.setText(text);
        int n5 = this.validateOffset(textLayout, n4);
        while (n3 < n5 && n5 < n2) {
            final String substring = text.substring(0, n5);
            final String substring2 = text.substring(this.validateOffset(textLayout, length - n5), length);
            final int x2 = gc.textExtent(substring, CLabel.DRAW_FLAGS).x;
            final int x3 = gc.textExtent(substring2, CLabel.DRAW_FLAGS).x;
            if (x2 + x + x3 > n) {
                n2 = n5;
                n5 = this.validateOffset(textLayout, (n2 + n3) / 2);
            }
            else if (x2 + x + x3 < n) {
                n3 = n5;
                n5 = this.validateOffset(textLayout, (n2 + n3) / 2);
            }
            else {
                n3 = n2;
            }
        }
        final String s = (n5 == 0) ? text : (String.valueOf(text.substring(0, n5)) + "..." + text.substring(this.validateOffset(textLayout, length - n5), length));
        textLayout.dispose();
        return s;
    }
    
    int validateOffset(final TextLayout textLayout, final int n) {
        final int nextOffset = textLayout.getNextOffset(n, 2);
        if (nextOffset != n) {
            return textLayout.getPreviousOffset(nextOffset, 2);
        }
        return n;
    }
    
    private String[] splitString(final String s) {
        String[] array = { null };
        int n = 0;
        int i;
        do {
            i = s.indexOf(10, n);
            if (i == -1) {
                array[array.length - 1] = s.substring(n);
            }
            else {
                array[array.length - 1] = s.substring(n, i - ((i > 0 && s.charAt(i - 1) == '\r') ? 1 : 0));
                n = i + 1;
                final String[] array2 = new String[array.length + 1];
                System.arraycopy(array, 0, array2, 0, array.length);
                array = array2;
            }
        } while (i != -1);
        return array;
    }
}

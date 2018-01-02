// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.custom;

import org.eclipse.swt.graphics.TextLayout;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.graphics.Region;
import org.eclipse.swt.graphics.Device;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.graphics.Color;

public class CTabFolderRenderer
{
    protected CTabFolder parent;
    int[] curve;
    int[] topCurveHighlightStart;
    int[] topCurveHighlightEnd;
    int curveWidth;
    int curveIndent;
    int lastTabHeight;
    Color fillColor;
    Color selectionHighlightGradientBegin;
    Color[] selectionHighlightGradientColorsCache;
    Color selectedOuterColor;
    Color selectedInnerColor;
    Color tabAreaColor;
    Color lastBorderColor;
    static final int[] TOP_LEFT_CORNER_HILITE;
    static final int[] TOP_LEFT_CORNER;
    static final int[] TOP_RIGHT_CORNER;
    static final int[] BOTTOM_LEFT_CORNER;
    static final int[] BOTTOM_RIGHT_CORNER;
    static final int[] SIMPLE_TOP_LEFT_CORNER;
    static final int[] SIMPLE_TOP_RIGHT_CORNER;
    static final int[] SIMPLE_BOTTOM_LEFT_CORNER;
    static final int[] SIMPLE_BOTTOM_RIGHT_CORNER;
    static final int[] SIMPLE_UNSELECTED_INNER_CORNER;
    static final int[] TOP_LEFT_CORNER_BORDERLESS;
    static final int[] TOP_RIGHT_CORNER_BORDERLESS;
    static final int[] BOTTOM_LEFT_CORNER_BORDERLESS;
    static final int[] BOTTOM_RIGHT_CORNER_BORDERLESS;
    static final int[] SIMPLE_TOP_LEFT_CORNER_BORDERLESS;
    static final int[] SIMPLE_TOP_RIGHT_CORNER_BORDERLESS;
    static final int[] SIMPLE_BOTTOM_LEFT_CORNER_BORDERLESS;
    static final int[] SIMPLE_BOTTOM_RIGHT_CORNER_BORDERLESS;
    static final RGB CLOSE_FILL;
    static final int BUTTON_SIZE = 18;
    static final int BUTTON_BORDER = 17;
    static final int BUTTON_FILL = 25;
    static final int BORDER1_COLOR = 18;
    static final int ITEM_TOP_MARGIN = 2;
    static final int ITEM_BOTTOM_MARGIN = 2;
    static final int ITEM_LEFT_MARGIN = 4;
    static final int ITEM_RIGHT_MARGIN = 4;
    static final int INTERNAL_SPACING = 4;
    static final int FLAGS = 9;
    static final String ELLIPSIS = "...";
    public static final int PART_BODY = -1;
    public static final int PART_HEADER = -2;
    public static final int PART_BORDER = -3;
    public static final int PART_BACKGROUND = -4;
    public static final int PART_MAX_BUTTON = -5;
    public static final int PART_MIN_BUTTON = -6;
    public static final int PART_CHEVRON_BUTTON = -7;
    public static final int PART_CLOSE_BUTTON = -8;
    public static final int MINIMUM_SIZE = 16777216;
    
    static {
        TOP_LEFT_CORNER_HILITE = new int[] { 5, 2, 4, 2, 3, 3, 2, 4, 2, 5, 1, 6 };
        TOP_LEFT_CORNER = new int[] { 0, 6, 1, 5, 1, 4, 4, 1, 5, 1, 6, 0 };
        TOP_RIGHT_CORNER = new int[] { -6, 0, -5, 1, -4, 1, -1, 4, -1, 5, 0, 6 };
        BOTTOM_LEFT_CORNER = new int[] { 0, -6, 1, -5, 1, -4, 4, -1, 5, -1, 6, 0 };
        BOTTOM_RIGHT_CORNER = new int[] { -6, 0, -5, -1, -4, -1, -1, -4, -1, -5, 0, -6 };
        SIMPLE_TOP_LEFT_CORNER = new int[] { 0, 2, 1, 1, 2, 0 };
        SIMPLE_TOP_RIGHT_CORNER = new int[] { -2, 0, -1, 1, 0, 2 };
        SIMPLE_BOTTOM_LEFT_CORNER = new int[] { 0, -2, 1, -1, 2, 0 };
        SIMPLE_BOTTOM_RIGHT_CORNER = new int[] { -2, 0, -1, -1, 0, -2 };
        SIMPLE_UNSELECTED_INNER_CORNER = new int[2];
        TOP_LEFT_CORNER_BORDERLESS = new int[] { 0, 6, 1, 5, 1, 4, 4, 1, 5, 1, 6, 0 };
        TOP_RIGHT_CORNER_BORDERLESS = new int[] { -7, 0, -6, 1, -5, 1, -2, 4, -2, 5, -1, 6 };
        BOTTOM_LEFT_CORNER_BORDERLESS = new int[] { 0, -6, 1, -6, 1, -5, 2, -4, 4, -2, 5, -1, 6, -1, 6, 0 };
        BOTTOM_RIGHT_CORNER_BORDERLESS = new int[] { -7, 0, -7, -1, -6, -1, -5, -2, -3, -4, -2, -5, -2, -6, -1, -6 };
        SIMPLE_TOP_LEFT_CORNER_BORDERLESS = new int[] { 0, 2, 1, 1, 2, 0 };
        SIMPLE_TOP_RIGHT_CORNER_BORDERLESS = new int[] { -3, 0, -2, 1, -1, 2 };
        SIMPLE_BOTTOM_LEFT_CORNER_BORDERLESS = new int[] { 0, -3, 1, -2, 2, -1, 3, 0 };
        SIMPLE_BOTTOM_RIGHT_CORNER_BORDERLESS = new int[] { -4, 0, -3, -1, -2, -2, -1, -3 };
        CLOSE_FILL = new RGB(252, 160, 160);
    }
    
    protected CTabFolderRenderer(final CTabFolder parent) {
        this.curveWidth = 0;
        this.curveIndent = 0;
        this.lastTabHeight = -1;
        this.selectionHighlightGradientBegin = null;
        this.selectionHighlightGradientColorsCache = null;
        this.selectedOuterColor = null;
        this.selectedInnerColor = null;
        this.tabAreaColor = null;
        this.lastBorderColor = null;
        if (parent == null) {
            return;
        }
        if (parent.isDisposed()) {
            SWT.error(5);
        }
        this.parent = parent;
    }
    
    void antialias(final int[] array, final Color foreground, final Color foreground2, final GC gc) {
        if (this.parent.simple) {
            return;
        }
        final String platform = SWT.getPlatform();
        if ("cocoa".equals(platform)) {
            return;
        }
        if ("carbon".equals(platform)) {
            return;
        }
        if ("wpf".equals(platform)) {
            return;
        }
        if (this.parent.getDisplay().getDepth() < 15) {
            return;
        }
        if (foreground2 != null) {
            int n = 0;
            boolean b = true;
            int n2 = this.parent.onBottom ? 0 : this.parent.getSize().y;
            final int[] array2 = new int[array.length];
            for (int i = 0; i < array.length / 2; ++i) {
                if (b && n + 3 < array.length) {
                    b = (this.parent.onBottom ? (n2 <= array[n + 3]) : (n2 >= array[n + 3]));
                    n2 = array[n + 1];
                }
                array2[n] = array[n++] + (b ? -1 : 1);
                array2[n] = array[n++];
            }
            gc.setForeground(foreground2);
            gc.drawPolyline(array2);
        }
        if (foreground != null) {
            final int[] array3 = new int[array.length];
            int n3 = 0;
            boolean b2 = true;
            int n4 = this.parent.onBottom ? 0 : this.parent.getSize().y;
            for (int j = 0; j < array.length / 2; ++j) {
                if (b2 && n3 + 3 < array.length) {
                    b2 = (this.parent.onBottom ? (n4 <= array[n3 + 3]) : (n4 >= array[n3 + 3]));
                    n4 = array[n3 + 1];
                }
                array3[n3] = array[n3++] + (b2 ? 1 : -1);
                array3[n3] = array[n3++];
            }
            gc.setForeground(foreground);
            gc.drawPolyline(array3);
        }
    }
    
    protected Point computeSize(final int n, final int n2, final GC gc, final int n3, final int n4) {
        int n5 = 0;
        int n6 = 0;
        switch (n) {
            case -2: {
                if (this.parent.fixedTabHeight != -1) {
                    n6 = ((this.parent.fixedTabHeight == 0) ? 0 : (this.parent.fixedTabHeight + 1));
                    break;
                }
                final CTabItem[] items = this.parent.items;
                if (items.length == 0) {
                    n6 = gc.textExtent("Default", 9).y + 2 + 2;
                }
                else {
                    for (int i = 0; i < items.length; ++i) {
                        n6 = Math.max(n6, this.computeSize(i, 0, gc, n3, n4).y);
                    }
                }
                gc.dispose();
                break;
            }
            case -8:
            case -6:
            case -5: {
                n6 = (n5 = 18);
                break;
            }
            case -7: {
                n5 = 27;
                n6 = 18;
                break;
            }
            default: {
                if (n < 0 || n >= this.parent.getItemCount()) {
                    break;
                }
                this.updateCurves();
                final CTabItem cTabItem = this.parent.items[n];
                if (cTabItem.isDisposed()) {
                    return new Point(0, 0);
                }
                final Image image = cTabItem.getImage();
                if (image != null) {
                    final Rectangle bounds = image.getBounds();
                    if ((n2 & 0x2) != 0x0 || this.parent.showUnselectedImage) {
                        n5 += bounds.width;
                    }
                    n6 = bounds.height;
                }
                String s;
                if ((n2 & 0x1000000) != 0x0) {
                    final int minChars = this.parent.minChars;
                    s = ((minChars == 0) ? null : cTabItem.getText());
                    if (s != null && s.length() > minChars) {
                        if (this.useEllipses()) {
                            s = s.substring(0, (minChars < "...".length() + 1) ? minChars : (minChars - "...".length()));
                            if (minChars > "...".length() + 1) {
                                s = String.valueOf(s) + "...";
                            }
                        }
                        else {
                            s = s.substring(0, minChars);
                        }
                    }
                }
                else {
                    s = cTabItem.getText();
                }
                if (s != null) {
                    if (n5 > 0) {
                        n5 += 4;
                    }
                    if (cTabItem.font == null) {
                        final Point textExtent = gc.textExtent(s, 9);
                        n5 += textExtent.x;
                        n6 = Math.max(n6, textExtent.y);
                    }
                    else {
                        final Font font = gc.getFont();
                        gc.setFont(cTabItem.font);
                        final Point textExtent2 = gc.textExtent(s, 9);
                        n5 += textExtent2.x;
                        n6 = Math.max(n6, textExtent2.y);
                        gc.setFont(font);
                    }
                }
                if ((this.parent.showClose || cTabItem.showClose) && ((n2 & 0x2) != 0x0 || this.parent.showUnselectedClose)) {
                    if (n5 > 0) {
                        n5 += 4;
                    }
                    n5 += this.computeSize(-8, 0, gc, -1, -1).x;
                    break;
                }
                break;
            }
        }
        final Rectangle computeTrim = this.computeTrim(n, n2, 0, 0, n5, n6);
        return new Point(computeTrim.width, computeTrim.height);
    }
    
    protected Rectangle computeTrim(final int n, final int n2, int n3, int n4, int n5, int n6) {
        final int borderVisible;
        final int n7 = borderVisible = (this.parent.borderVisible ? 1 : 0);
        final int n8 = this.parent.onBottom ? n7 : 0;
        final int n9 = this.parent.onBottom ? 0 : n7;
        final int tabHeight = this.parent.tabHeight;
        switch (n) {
            case -1: {
                final int style = this.parent.getStyle();
                int n10 = ((style & 0x800000) != 0x0) ? 1 : 3;
                final int n11 = ((style & 0x800000) != 0x0) ? 0 : 2;
                if (this.parent.fixedTabHeight == 0 && (style & 0x800000) != 0x0 && (style & 0x800) == 0x0) {
                    n10 = 0;
                }
                final int marginWidth = this.parent.marginWidth;
                final int marginHeight = this.parent.marginHeight;
                n3 = n3 - marginWidth - n11 - n7;
                n5 = n5 + n7 + borderVisible + 2 * marginWidth + 2 * n11;
                if (this.parent.minimized) {
                    n4 = (this.parent.onBottom ? (n4 - n8) : (n4 - n10 - tabHeight - n8));
                    n6 = n8 + n9 + tabHeight + n10;
                    break;
                }
                n4 = (this.parent.onBottom ? (n4 - marginHeight - n11 - n8) : (n4 - marginHeight - n10 - tabHeight - n8));
                n6 = n6 + n8 + n9 + 2 * marginHeight + tabHeight + n10 + n11;
                break;
            }
            case -2: {
                break;
            }
            case -3: {
                n3 -= n7;
                n5 = n5 + n7 + borderVisible;
                n4 -= n8;
                n6 = n6 + n8 + n9;
                break;
            }
            default: {
                if (n >= 0 && n < this.parent.getItemCount()) {
                    this.updateCurves();
                    n3 -= 4;
                    n5 = n5 + 4 + 4;
                    if (!this.parent.simple && !this.parent.single && (n2 & 0x2) != 0x0) {
                        n5 += this.curveWidth - this.curveIndent;
                    }
                    n4 -= 2;
                    n6 = n6 + 2 + 2;
                    break;
                }
                break;
            }
        }
        return new Rectangle(n3, n4, n5, n6);
    }
    
    void createAntialiasColors() {
        this.disposeAntialiasColors();
        this.lastBorderColor = this.parent.getDisplay().getSystemColor(18);
        final RGB rgb = this.lastBorderColor.getRGB();
        RGB rgb2 = this.parent.selectionBackground.getRGB();
        if (this.parent.selectionBgImage != null || (this.parent.selectionGradientColors != null && this.parent.selectionGradientColors.length > 1)) {
            rgb2 = null;
        }
        RGB rgb3 = this.parent.getBackground().getRGB();
        if (this.parent.gradientColors != null && this.parent.gradientColors.length > 1) {
            rgb3 = null;
        }
        if (rgb3 != null) {
            final RGB rgb4 = rgb;
            final RGB rgb5 = rgb3;
            this.selectedOuterColor = new Color(this.parent.getDisplay(), rgb4.red + 2 * (rgb5.red - rgb4.red) / 3, rgb4.green + 2 * (rgb5.green - rgb4.green) / 3, rgb4.blue + 2 * (rgb5.blue - rgb4.blue) / 3);
        }
        if (rgb2 != null) {
            final RGB rgb6 = rgb;
            final RGB rgb7 = rgb2;
            this.selectedInnerColor = new Color(this.parent.getDisplay(), rgb6.red + 2 * (rgb7.red - rgb6.red) / 3, rgb6.green + 2 * (rgb7.green - rgb6.green) / 3, rgb6.blue + 2 * (rgb7.blue - rgb6.blue) / 3);
        }
        final RGB rgb8 = this.parent.getParent().getBackground().getRGB();
        if (rgb8 != null) {
            final RGB rgb9 = rgb;
            final RGB rgb10 = rgb8;
            this.tabAreaColor = new Color(this.parent.getDisplay(), rgb9.red + 2 * (rgb10.red - rgb9.red) / 3, rgb9.green + 2 * (rgb10.green - rgb9.green) / 3, rgb9.blue + 2 * (rgb10.blue - rgb9.blue) / 3);
        }
    }
    
    void createSelectionHighlightGradientColors(final Color color) {
        this.disposeSelectionHighlightGradientColors();
        if (color == null) {
            return;
        }
        final int tabHeight = this.parent.tabHeight;
        final RGB rgb = color.getRGB();
        final RGB rgb2 = this.parent.selectionBackground.getRGB();
        this.selectionHighlightGradientColorsCache = new Color[tabHeight];
        final int n = tabHeight - 1;
        for (int i = 0; i < tabHeight; ++i) {
            final int n2 = n - i;
            final int n3 = i;
            this.selectionHighlightGradientColorsCache[i] = new Color(this.parent.getDisplay(), (rgb2.red * n3 + rgb.red * n2) / n, (rgb2.green * n3 + rgb.green * n2) / n, (rgb2.blue * n3 + rgb.blue * n2) / n);
        }
    }
    
    protected void dispose() {
        this.disposeAntialiasColors();
        this.disposeSelectionHighlightGradientColors();
        if (this.fillColor != null) {
            this.fillColor.dispose();
            this.fillColor = null;
        }
    }
    
    void disposeAntialiasColors() {
        if (this.tabAreaColor != null) {
            this.tabAreaColor.dispose();
        }
        if (this.selectedInnerColor != null) {
            this.selectedInnerColor.dispose();
        }
        if (this.selectedOuterColor != null) {
            this.selectedOuterColor.dispose();
        }
        final Color tabAreaColor = null;
        this.selectedOuterColor = tabAreaColor;
        this.selectedInnerColor = tabAreaColor;
        this.tabAreaColor = tabAreaColor;
    }
    
    void disposeSelectionHighlightGradientColors() {
        if (this.selectionHighlightGradientColorsCache == null) {
            return;
        }
        for (int i = 0; i < this.selectionHighlightGradientColorsCache.length; ++i) {
            this.selectionHighlightGradientColorsCache[i].dispose();
        }
        this.selectionHighlightGradientColorsCache = null;
    }
    
    protected void draw(final int n, final int n2, final Rectangle rectangle, final GC gc) {
        switch (n) {
            case -4: {
                this.drawBackground(gc, rectangle, n2);
                break;
            }
            case -1: {
                this.drawBody(gc, rectangle, n2);
                break;
            }
            case -2: {
                this.drawTabArea(gc, rectangle, n2);
                break;
            }
            case -5: {
                this.drawMaximize(gc, rectangle, n2);
                break;
            }
            case -6: {
                this.drawMinimize(gc, rectangle, n2);
                break;
            }
            case -7: {
                this.drawChevron(gc, rectangle, n2);
                break;
            }
            default: {
                if (n < 0 || n >= this.parent.getItemCount()) {
                    break;
                }
                if (rectangle.width == 0 || rectangle.height == 0) {
                    return;
                }
                if ((n2 & 0x2) != 0x0) {
                    this.drawSelected(n, gc, rectangle, n2);
                    break;
                }
                this.drawUnselected(n, gc, rectangle, n2);
                break;
            }
        }
    }
    
    void drawBackground(final GC gc, final Rectangle rectangle, final int n) {
        final boolean b = (n & 0x2) != 0x0;
        this.drawBackground(gc, null, rectangle.x, rectangle.y, rectangle.width, rectangle.height, b ? this.parent.selectionBackground : this.parent.getBackground(), b ? this.parent.selectionBgImage : null, b ? this.parent.selectionGradientColors : this.parent.gradientColors, b ? this.parent.selectionGradientPercents : this.parent.gradientPercents, b ? this.parent.selectionGradientVertical : this.parent.gradientVertical);
    }
    
    void drawBackground(final GC gc, final int[] array, final boolean b) {
        final Color color = b ? this.parent.selectionBackground : this.parent.getBackground();
        final Image image = b ? this.parent.selectionBgImage : null;
        final Color[] array2 = b ? this.parent.selectionGradientColors : this.parent.gradientColors;
        final int[] array3 = b ? this.parent.selectionGradientPercents : this.parent.gradientPercents;
        final boolean b2 = b ? this.parent.selectionGradientVertical : this.parent.gradientVertical;
        final Point size = this.parent.getSize();
        int x = size.x;
        final int n = this.parent.tabHeight + (((this.parent.getStyle() & 0x800000) != 0x0) ? 1 : 3);
        int n2 = 0;
        final int borderVisible = this.parent.borderVisible ? 1 : 0;
        final boolean b3 = (this.parent.onBottom ? borderVisible : false) != 0;
        final int n3 = this.parent.onBottom ? false : borderVisible;
        if (borderVisible > 0) {
            ++n2;
            x -= 2;
        }
        this.drawBackground(gc, array, n2, this.parent.onBottom ? (size.y - n3 - n) : b3, x, n, color, image, array2, array3, b2);
    }
    
    void drawBackground(final GC gc, final int[] array, final int n, int n2, final int n3, int y, final Color background, final Image image, final Color[] array2, final int[] array3, final boolean b) {
        Region clipping = null;
        Region clipping2 = null;
        if (array != null) {
            clipping = new Region();
            gc.getClipping(clipping);
            clipping2 = new Region();
            clipping2.add(array);
            clipping2.intersect(clipping);
            gc.setClipping(clipping2);
        }
        if (image != null) {
            gc.setBackground(background);
            gc.fillRectangle(n, n2, n3, y);
            final Rectangle bounds = image.getBounds();
            gc.drawImage(image, bounds.x, bounds.y, bounds.width, bounds.height, n, n2, n3, y);
        }
        else if (array2 != null) {
            if (array2.length == 1) {
                gc.setBackground((array2[0] != null) ? array2[0] : background);
                gc.fillRectangle(n, n2, n3, y);
            }
            else if (b) {
                if (this.parent.onBottom) {
                    int n4 = 0;
                    if (array3[array3.length - 1] < 100) {
                        n4 = (100 - array3[array3.length - 1]) * y / 100;
                        gc.setBackground(background);
                        gc.fillRectangle(n, n2, n3, n4);
                    }
                    Color color = array2[array2.length - 1];
                    if (color == null) {
                        color = background;
                    }
                    for (int i = array3.length - 1; i >= 0; --i) {
                        gc.setForeground(color);
                        color = array2[i];
                        if (color == null) {
                            color = background;
                        }
                        gc.setBackground(color);
                        final int n5 = ((i > 0) ? (array3[i] - array3[i - 1]) : array3[i]) * y / 100;
                        gc.fillGradientRectangle(n, n2 + n4, n3, n5, true);
                        n4 += n5;
                    }
                }
                else {
                    Color color2 = array2[0];
                    if (color2 == null) {
                        color2 = background;
                    }
                    int n6 = 0;
                    for (int j = 0; j < array3.length; ++j) {
                        gc.setForeground(color2);
                        color2 = array2[j + 1];
                        if (color2 == null) {
                            color2 = background;
                        }
                        gc.setBackground(color2);
                        final int n7 = ((j > 0) ? (array3[j] - array3[j - 1]) : array3[j]) * y / 100;
                        gc.fillGradientRectangle(n, n2 + n6, n3, n7, true);
                        n6 += n7;
                    }
                    if (n6 < y) {
                        gc.setBackground(background);
                        gc.fillRectangle(n, n6, n3, y - n6 + 1);
                    }
                }
            }
            else {
                n2 = 0;
                y = this.parent.getSize().y;
                Color color3 = array2[0];
                if (color3 == null) {
                    color3 = background;
                }
                int n8 = 0;
                for (int k = 0; k < array3.length; ++k) {
                    gc.setForeground(color3);
                    color3 = array2[k + 1];
                    if (color3 == null) {
                        color3 = background;
                    }
                    gc.setBackground(color3);
                    final int n9 = array3[k] * n3 / 100 - n8;
                    gc.fillGradientRectangle(n + n8, n2, n9, y, false);
                    n8 += n9;
                }
                if (n8 < n3) {
                    gc.setBackground(background);
                    gc.fillRectangle(n + n8, n2, n3 - n8, y);
                }
            }
        }
        else if ((this.parent.getStyle() & 0x40000) != 0x0 || !background.equals(this.parent.getBackground())) {
            gc.setBackground(background);
            gc.fillRectangle(n, n2, n3, y);
        }
        if (array != null) {
            gc.setClipping(clipping);
            clipping.dispose();
            clipping2.dispose();
        }
    }
    
    void drawBorder(final GC gc, final int[] array) {
        gc.setForeground(this.parent.getDisplay().getSystemColor(18));
        gc.drawPolyline(array);
    }
    
    void drawBody(final GC gc, final Rectangle rectangle, final int n) {
        final Point point = new Point(rectangle.width, rectangle.height);
        final int selectedIndex = this.parent.selectedIndex;
        final int tabHeight = this.parent.tabHeight;
        final int borderVisible;
        final int n2 = borderVisible = (this.parent.borderVisible ? 1 : 0);
        final int n3 = this.parent.onBottom ? n2 : 0;
        final int n4 = this.parent.onBottom ? 0 : n2;
        final int style = this.parent.getStyle();
        final int n5 = ((style & 0x800000) != 0x0) ? 1 : 3;
        final int n6 = ((style & 0x800000) != 0x0) ? 0 : 2;
        if (!this.parent.minimized) {
            final int n7 = point.x - n2 - borderVisible - 2 * n6;
            final int n8 = point.y - n3 - n4 - tabHeight - n5 - n6;
            if (n6 > 0) {
                final int[] array = null;
                int[] array2;
                if (this.parent.onBottom) {
                    final int n9 = n2;
                    final int n10 = n3;
                    final int n11 = point.x - borderVisible;
                    final int n12 = point.y - n4 - tabHeight - n5;
                    array2 = new int[] { n9, n10, n11, n10, n11, n12, n11 - n6, n12, n11 - n6, n10 + n6, n9 + n6, n10 + n6, n9 + n6, n12, n9, n12 };
                }
                else {
                    final int n13 = n2;
                    final int n14 = n3 + tabHeight + n5;
                    final int n15 = point.x - borderVisible;
                    final int n16 = point.y - n4;
                    array2 = new int[] { n13, n14, n13 + n6, n14, n13 + n6, n16 - n6, n15 - n6, n16 - n6, n15 - n6, n14, n15, n14, n15, n16, n13, n16 };
                }
                if (selectedIndex != -1 && this.parent.selectionGradientColors != null && this.parent.selectionGradientColors.length > 1 && !this.parent.selectionGradientVertical) {
                    this.drawBackground(gc, array2, true);
                }
                else if (selectedIndex == -1 && this.parent.gradientColors != null && this.parent.gradientColors.length > 1 && !this.parent.gradientVertical) {
                    this.drawBackground(gc, array2, false);
                }
                else {
                    gc.setBackground((selectedIndex == -1) ? this.parent.getBackground() : this.parent.selectionBackground);
                    gc.fillPolygon(array2);
                }
            }
            if ((this.parent.getStyle() & 0x40000) != 0x0) {
                gc.setBackground(this.parent.getBackground());
                final int marginWidth = this.parent.marginWidth;
                final int marginHeight = this.parent.marginHeight;
                final int n17 = n2 + marginWidth + n6;
                int n18;
                if (this.parent.onBottom) {
                    n18 = n3 + n6 + marginHeight;
                }
                else {
                    n18 = n3 + tabHeight + n5 + marginHeight;
                }
                gc.fillRectangle(n17 - marginWidth, n18 - marginHeight, n7, n8);
            }
        }
        else if ((this.parent.getStyle() & 0x40000) != 0x0) {
            final int n19 = n3 + tabHeight + n5 + n4;
            if (point.y > n19) {
                gc.setBackground(this.parent.getParent().getBackground());
                gc.fillRectangle(0, n19, point.x, point.y - n19);
            }
        }
        if (n2 > 0) {
            gc.setForeground(this.parent.getDisplay().getSystemColor(18));
            final int n20 = n2 - 1;
            final int n21 = point.x - borderVisible;
            final int n22 = this.parent.onBottom ? (n3 - 1) : (n3 + tabHeight);
            final int n23 = this.parent.onBottom ? (point.y - tabHeight - n4 - 1) : (point.y - n4);
            gc.drawLine(n20, n22, n20, n23);
            gc.drawLine(n21, n22, n21, n23);
            if (this.parent.onBottom) {
                gc.drawLine(n20, n22, n21, n22);
            }
            else {
                gc.drawLine(n20, n23, n21, n23);
            }
        }
    }
    
    void drawClose(final GC gc, final Rectangle rectangle, final int n) {
        if (rectangle.width == 0 || rectangle.height == 0) {
            return;
        }
        final Display display = this.parent.getDisplay();
        final int n2 = rectangle.x + Math.max(1, (rectangle.width - 9) / 2);
        final int n3 = rectangle.y + Math.max(1, (rectangle.height - 9) / 2) + (this.parent.onBottom ? -1 : 1);
        final Color systemColor = display.getSystemColor(17);
        switch (n & 0x2A) {
            case 0: {
                final int[] array = { n2, n3, n2 + 2, n3, n2 + 4, n3 + 2, n2 + 5, n3 + 2, n2 + 7, n3, n2 + 9, n3, n2 + 9, n3 + 2, n2 + 7, n3 + 4, n2 + 7, n3 + 5, n2 + 9, n3 + 7, n2 + 9, n3 + 9, n2 + 7, n3 + 9, n2 + 5, n3 + 7, n2 + 4, n3 + 7, n2 + 2, n3 + 9, n2, n3 + 9, n2, n3 + 7, n2 + 2, n3 + 5, n2 + 2, n3 + 4, n2, n3 + 2 };
                gc.setBackground(display.getSystemColor(25));
                gc.fillPolygon(array);
                gc.setForeground(systemColor);
                gc.drawPolygon(array);
                break;
            }
            case 32: {
                final int[] array2 = { n2, n3, n2 + 2, n3, n2 + 4, n3 + 2, n2 + 5, n3 + 2, n2 + 7, n3, n2 + 9, n3, n2 + 9, n3 + 2, n2 + 7, n3 + 4, n2 + 7, n3 + 5, n2 + 9, n3 + 7, n2 + 9, n3 + 9, n2 + 7, n3 + 9, n2 + 5, n3 + 7, n2 + 4, n3 + 7, n2 + 2, n3 + 9, n2, n3 + 9, n2, n3 + 7, n2 + 2, n3 + 5, n2 + 2, n3 + 4, n2, n3 + 2 };
                gc.setBackground(this.getFillColor());
                gc.fillPolygon(array2);
                gc.setForeground(systemColor);
                gc.drawPolygon(array2);
                break;
            }
            case 2: {
                final int[] array3 = { n2 + 1, n3 + 1, n2 + 3, n3 + 1, n2 + 5, n3 + 3, n2 + 6, n3 + 3, n2 + 8, n3 + 1, n2 + 10, n3 + 1, n2 + 10, n3 + 3, n2 + 8, n3 + 5, n2 + 8, n3 + 6, n2 + 10, n3 + 8, n2 + 10, n3 + 10, n2 + 8, n3 + 10, n2 + 6, n3 + 8, n2 + 5, n3 + 8, n2 + 3, n3 + 10, n2 + 1, n3 + 10, n2 + 1, n3 + 8, n2 + 3, n3 + 6, n2 + 3, n3 + 5, n2 + 1, n3 + 3 };
                gc.setBackground(this.getFillColor());
                gc.fillPolygon(array3);
                gc.setForeground(systemColor);
                gc.drawPolygon(array3);
                break;
            }
            case 8: {
                this.drawBackground(gc, new int[] { n2, n3, n2 + 10, n3, n2 + 10, n3 + 10, n2, n3 + 10 }, false);
                break;
            }
        }
    }
    
    void drawChevron(final GC gc, final Rectangle rectangle, final int n) {
        if (rectangle.width == 0 || rectangle.height == 0) {
            return;
        }
        final int selectedIndex = this.parent.selectedIndex;
        final Display display = this.parent.getDisplay();
        final Point dpi = display.getDPI();
        final int height = 720 / dpi.y;
        final FontData fontData = this.parent.getFont().getFontData()[0];
        fontData.setHeight(height);
        final Font font = new Font(display, fontData);
        final int max = Math.max(2, (rectangle.height - font.getFontData()[0].getHeight() * dpi.y / 72 - 4) / 2);
        final int n2 = rectangle.x + 2;
        final int n3 = rectangle.y + max;
        final int itemCount = this.parent.getItemCount();
        int n4;
        if (this.parent.single) {
            n4 = ((selectedIndex == -1) ? itemCount : (itemCount - 1));
        }
        else {
            int n5;
            for (n5 = 0; n5 < this.parent.priority.length && this.parent.items[this.parent.priority[n5]].showing; ++n5) {}
            n4 = itemCount - n5;
        }
        final String s = (n4 > 99) ? "99+" : String.valueOf(n4);
        switch (n & 0x22) {
            case 0: {
                gc.setForeground(this.parent.single ? this.parent.getSelectionForeground() : this.parent.getForeground());
                gc.setFont(font);
                gc.drawLine(n2, n3, n2 + 2, n3 + 2);
                gc.drawLine(n2 + 2, n3 + 2, n2, n3 + 4);
                gc.drawLine(n2 + 1, n3, n2 + 3, n3 + 2);
                gc.drawLine(n2 + 3, n3 + 2, n2 + 1, n3 + 4);
                gc.drawLine(n2 + 4, n3, n2 + 6, n3 + 2);
                gc.drawLine(n2 + 6, n3 + 2, n2 + 5, n3 + 4);
                gc.drawLine(n2 + 5, n3, n2 + 7, n3 + 2);
                gc.drawLine(n2 + 7, n3 + 2, n2 + 4, n3 + 4);
                gc.drawString(s, n2 + 7, n3 + 3, true);
                break;
            }
            case 32: {
                gc.setForeground(display.getSystemColor(17));
                gc.setBackground(display.getSystemColor(25));
                gc.setFont(font);
                gc.fillRoundRectangle(rectangle.x, rectangle.y, rectangle.width, rectangle.height, 6, 6);
                gc.drawRoundRectangle(rectangle.x, rectangle.y, rectangle.width - 1, rectangle.height - 1, 6, 6);
                gc.drawLine(n2, n3, n2 + 2, n3 + 2);
                gc.drawLine(n2 + 2, n3 + 2, n2, n3 + 4);
                gc.drawLine(n2 + 1, n3, n2 + 3, n3 + 2);
                gc.drawLine(n2 + 3, n3 + 2, n2 + 1, n3 + 4);
                gc.drawLine(n2 + 4, n3, n2 + 6, n3 + 2);
                gc.drawLine(n2 + 6, n3 + 2, n2 + 5, n3 + 4);
                gc.drawLine(n2 + 5, n3, n2 + 7, n3 + 2);
                gc.drawLine(n2 + 7, n3 + 2, n2 + 4, n3 + 4);
                gc.drawString(s, n2 + 7, n3 + 3, true);
                break;
            }
            case 2: {
                gc.setForeground(display.getSystemColor(17));
                gc.setBackground(display.getSystemColor(25));
                gc.setFont(font);
                gc.fillRoundRectangle(rectangle.x, rectangle.y, rectangle.width, rectangle.height, 6, 6);
                gc.drawRoundRectangle(rectangle.x, rectangle.y, rectangle.width - 1, rectangle.height - 1, 6, 6);
                gc.drawLine(n2 + 1, n3 + 1, n2 + 3, n3 + 3);
                gc.drawLine(n2 + 3, n3 + 3, n2 + 1, n3 + 5);
                gc.drawLine(n2 + 2, n3 + 1, n2 + 4, n3 + 3);
                gc.drawLine(n2 + 4, n3 + 3, n2 + 2, n3 + 5);
                gc.drawLine(n2 + 5, n3 + 1, n2 + 7, n3 + 3);
                gc.drawLine(n2 + 7, n3 + 3, n2 + 6, n3 + 5);
                gc.drawLine(n2 + 6, n3 + 1, n2 + 8, n3 + 3);
                gc.drawLine(n2 + 8, n3 + 3, n2 + 5, n3 + 5);
                gc.drawString(s, n2 + 8, n3 + 4, true);
                break;
            }
        }
        font.dispose();
    }
    
    void drawHighlight(final GC gc, final Rectangle rectangle, final int n, final int n2) {
        if (this.parent.simple || this.parent.onBottom) {
            return;
        }
        if (this.selectionHighlightGradientBegin == null) {
            return;
        }
        final Color[] selectionHighlightGradientColorsCache = this.selectionHighlightGradientColorsCache;
        if (selectionHighlightGradientColorsCache == null) {
            return;
        }
        final int length = selectionHighlightGradientColorsCache.length;
        if (length == 0) {
            return;
        }
        final int x = rectangle.x;
        final int y = rectangle.y;
        gc.setForeground(selectionHighlightGradientColorsCache[0]);
        gc.drawLine(CTabFolderRenderer.TOP_LEFT_CORNER_HILITE[0] + x + 1, 1 + y, n2 - this.curveIndent, 1 + y);
        final int[] top_LEFT_CORNER_HILITE = CTabFolderRenderer.TOP_LEFT_CORNER_HILITE;
        final int n3 = this.parent.tabHeight - this.topCurveHighlightEnd.length / 2;
        int n4 = 0;
        int n5 = 0;
        int n6 = 0;
        for (int i = 0; i < top_LEFT_CORNER_HILITE.length / 2; ++i) {
            final int n7 = top_LEFT_CORNER_HILITE[i * 2];
            final int n8 = top_LEFT_CORNER_HILITE[i * 2 + 1];
            n4 = n7 + x;
            n5 = n8 + y;
            n6 = n8 - 1;
            gc.setForeground(selectionHighlightGradientColorsCache[n6]);
            gc.drawPoint(n4, n5);
        }
        for (int j = n6; j < length; ++j) {
            gc.setForeground(selectionHighlightGradientColorsCache[j]);
            gc.drawPoint(n4, 1 + n5++);
        }
        final int n9 = n2 - this.curveIndent;
        for (int k = 0; k < this.topCurveHighlightStart.length / 2; ++k) {
            final int n10 = this.topCurveHighlightStart[k * 2];
            final int n11 = this.topCurveHighlightStart[k * 2 + 1];
            n4 = n10 + n9;
            n5 = n11 + y;
            n6 = n11 - 1;
            if (n6 >= length) {
                break;
            }
            gc.setForeground(selectionHighlightGradientColorsCache[n6]);
            gc.drawPoint(n4, n5);
        }
        for (int n12 = n6; n12 < n6 + n3 && n12 < length; ++n12) {
            gc.setForeground(selectionHighlightGradientColorsCache[n12]);
            gc.drawPoint(1 + n4++, 1 + n5++);
        }
        for (int l = 0; l < this.topCurveHighlightEnd.length / 2; ++l) {
            final int n13 = this.topCurveHighlightEnd[l * 2];
            final int n14 = this.topCurveHighlightEnd[l * 2 + 1];
            final int n15 = n13 + n9;
            final int n16 = n14 + y;
            final int n17 = n14 - 1;
            if (n17 >= length) {
                break;
            }
            gc.setForeground(selectionHighlightGradientColorsCache[n17]);
            gc.drawPoint(n15, n16);
        }
    }
    
    void drawLeftUnselectedBorder(final GC gc, final Rectangle rectangle, final int n) {
        final int x = rectangle.x;
        final int y = rectangle.y;
        final int height = rectangle.height;
        final int[] array = null;
        int[] array3;
        if (this.parent.onBottom) {
            final int[] array2 = this.parent.simple ? CTabFolderRenderer.SIMPLE_UNSELECTED_INNER_CORNER : CTabFolderRenderer.BOTTOM_LEFT_CORNER;
            array3 = new int[array2.length + 2];
            int n2 = 0;
            array3[n2++] = x;
            array3[n2++] = y - 1;
            for (int i = 0; i < array2.length / 2; ++i) {
                array3[n2++] = x + array2[2 * i];
                array3[n2++] = y + height + array2[2 * i + 1] - 1;
            }
        }
        else {
            final int[] array4 = this.parent.simple ? CTabFolderRenderer.SIMPLE_UNSELECTED_INNER_CORNER : CTabFolderRenderer.TOP_LEFT_CORNER;
            array3 = new int[array4.length + 2];
            int n3 = 0;
            array3[n3++] = x;
            array3[n3++] = y + height;
            for (int j = 0; j < array4.length / 2; ++j) {
                array3[n3++] = x + array4[2 * j];
                array3[n3++] = y + array4[2 * j + 1];
            }
        }
        this.drawBorder(gc, array3);
    }
    
    void drawMaximize(final GC gc, final Rectangle rectangle, final int n) {
        if (rectangle.width == 0 || rectangle.height == 0) {
            return;
        }
        final Display display = this.parent.getDisplay();
        final int n2 = rectangle.x + (rectangle.width - 10) / 2;
        final int n3 = rectangle.y + 3;
        gc.setForeground(display.getSystemColor(17));
        gc.setBackground(display.getSystemColor(25));
        switch (n & 0x22) {
            case 0: {
                if (!this.parent.getMaximized()) {
                    gc.fillRectangle(n2, n3, 9, 9);
                    gc.drawRectangle(n2, n3, 9, 9);
                    gc.drawLine(n2 + 1, n3 + 2, n2 + 8, n3 + 2);
                    break;
                }
                gc.fillRectangle(n2, n3 + 3, 5, 4);
                gc.fillRectangle(n2 + 2, n3, 5, 4);
                gc.drawRectangle(n2, n3 + 3, 5, 4);
                gc.drawRectangle(n2 + 2, n3, 5, 4);
                gc.drawLine(n2 + 3, n3 + 1, n2 + 6, n3 + 1);
                gc.drawLine(n2 + 1, n3 + 4, n2 + 4, n3 + 4);
                break;
            }
            case 32: {
                gc.fillRoundRectangle(rectangle.x, rectangle.y, rectangle.width, rectangle.height, 6, 6);
                gc.drawRoundRectangle(rectangle.x, rectangle.y, rectangle.width - 1, rectangle.height - 1, 6, 6);
                if (!this.parent.getMaximized()) {
                    gc.fillRectangle(n2, n3, 9, 9);
                    gc.drawRectangle(n2, n3, 9, 9);
                    gc.drawLine(n2 + 1, n3 + 2, n2 + 8, n3 + 2);
                    break;
                }
                gc.fillRectangle(n2, n3 + 3, 5, 4);
                gc.fillRectangle(n2 + 2, n3, 5, 4);
                gc.drawRectangle(n2, n3 + 3, 5, 4);
                gc.drawRectangle(n2 + 2, n3, 5, 4);
                gc.drawLine(n2 + 3, n3 + 1, n2 + 6, n3 + 1);
                gc.drawLine(n2 + 1, n3 + 4, n2 + 4, n3 + 4);
                break;
            }
            case 2: {
                gc.fillRoundRectangle(rectangle.x, rectangle.y, rectangle.width, rectangle.height, 6, 6);
                gc.drawRoundRectangle(rectangle.x, rectangle.y, rectangle.width - 1, rectangle.height - 1, 6, 6);
                if (!this.parent.getMaximized()) {
                    gc.fillRectangle(n2 + 1, n3 + 1, 9, 9);
                    gc.drawRectangle(n2 + 1, n3 + 1, 9, 9);
                    gc.drawLine(n2 + 2, n3 + 3, n2 + 9, n3 + 3);
                    break;
                }
                gc.fillRectangle(n2 + 1, n3 + 4, 5, 4);
                gc.fillRectangle(n2 + 3, n3 + 1, 5, 4);
                gc.drawRectangle(n2 + 1, n3 + 4, 5, 4);
                gc.drawRectangle(n2 + 3, n3 + 1, 5, 4);
                gc.drawLine(n2 + 4, n3 + 2, n2 + 7, n3 + 2);
                gc.drawLine(n2 + 2, n3 + 5, n2 + 5, n3 + 5);
                break;
            }
        }
    }
    
    void drawMinimize(final GC gc, final Rectangle rectangle, final int n) {
        if (rectangle.width == 0 || rectangle.height == 0) {
            return;
        }
        final Display display = this.parent.getDisplay();
        final int n2 = rectangle.x + (rectangle.width - 10) / 2;
        final int n3 = rectangle.y + 3;
        gc.setForeground(display.getSystemColor(17));
        gc.setBackground(display.getSystemColor(25));
        switch (n & 0x22) {
            case 0: {
                if (!this.parent.getMinimized()) {
                    gc.fillRectangle(n2, n3, 9, 3);
                    gc.drawRectangle(n2, n3, 9, 3);
                    break;
                }
                gc.fillRectangle(n2, n3 + 3, 5, 4);
                gc.fillRectangle(n2 + 2, n3, 5, 4);
                gc.drawRectangle(n2, n3 + 3, 5, 4);
                gc.drawRectangle(n2 + 2, n3, 5, 4);
                gc.drawLine(n2 + 3, n3 + 1, n2 + 6, n3 + 1);
                gc.drawLine(n2 + 1, n3 + 4, n2 + 4, n3 + 4);
                break;
            }
            case 32: {
                gc.fillRoundRectangle(rectangle.x, rectangle.y, rectangle.width, rectangle.height, 6, 6);
                gc.drawRoundRectangle(rectangle.x, rectangle.y, rectangle.width - 1, rectangle.height - 1, 6, 6);
                if (!this.parent.getMinimized()) {
                    gc.fillRectangle(n2, n3, 9, 3);
                    gc.drawRectangle(n2, n3, 9, 3);
                    break;
                }
                gc.fillRectangle(n2, n3 + 3, 5, 4);
                gc.fillRectangle(n2 + 2, n3, 5, 4);
                gc.drawRectangle(n2, n3 + 3, 5, 4);
                gc.drawRectangle(n2 + 2, n3, 5, 4);
                gc.drawLine(n2 + 3, n3 + 1, n2 + 6, n3 + 1);
                gc.drawLine(n2 + 1, n3 + 4, n2 + 4, n3 + 4);
                break;
            }
            case 2: {
                gc.fillRoundRectangle(rectangle.x, rectangle.y, rectangle.width, rectangle.height, 6, 6);
                gc.drawRoundRectangle(rectangle.x, rectangle.y, rectangle.width - 1, rectangle.height - 1, 6, 6);
                if (!this.parent.getMinimized()) {
                    gc.fillRectangle(n2 + 1, n3 + 1, 9, 3);
                    gc.drawRectangle(n2 + 1, n3 + 1, 9, 3);
                    break;
                }
                gc.fillRectangle(n2 + 1, n3 + 4, 5, 4);
                gc.fillRectangle(n2 + 3, n3 + 1, 5, 4);
                gc.drawRectangle(n2 + 1, n3 + 4, 5, 4);
                gc.drawRectangle(n2 + 3, n3 + 1, 5, 4);
                gc.drawLine(n2 + 4, n3 + 2, n2 + 7, n3 + 2);
                gc.drawLine(n2 + 2, n3 + 5, n2 + 5, n3 + 5);
                break;
            }
        }
    }
    
    void drawRightUnselectedBorder(final GC gc, final Rectangle rectangle, final int n) {
        final int x = rectangle.x;
        final int y = rectangle.y;
        final int width = rectangle.width;
        final int height = rectangle.height;
        final int[] array = null;
        final int n2 = x + width - 1;
        int[] array3;
        if (this.parent.onBottom) {
            final int[] array2 = this.parent.simple ? CTabFolderRenderer.SIMPLE_UNSELECTED_INNER_CORNER : CTabFolderRenderer.BOTTOM_RIGHT_CORNER;
            array3 = new int[array2.length + 2];
            int n3 = 0;
            for (int i = 0; i < array2.length / 2; ++i) {
                array3[n3++] = n2 + array2[2 * i];
                array3[n3++] = y + height + array2[2 * i + 1] - 1;
            }
            array3[n3++] = n2;
            array3[n3++] = y - 1;
        }
        else {
            final int[] array4 = this.parent.simple ? CTabFolderRenderer.SIMPLE_UNSELECTED_INNER_CORNER : CTabFolderRenderer.TOP_RIGHT_CORNER;
            array3 = new int[array4.length + 2];
            int n4 = 0;
            for (int j = 0; j < array4.length / 2; ++j) {
                array3[n4++] = n2 + array4[2 * j];
                array3[n4++] = y + array4[2 * j + 1];
            }
            array3[n4++] = n2;
            array3[n4++] = y + height;
        }
        this.drawBorder(gc, array3);
    }
    
    void drawSelected(final int n, final GC gc, final Rectangle rectangle, final int n2) {
        final CTabItem cTabItem = this.parent.items[n];
        final int x = rectangle.x;
        final int y = rectangle.y;
        final int height = rectangle.height;
        int width = rectangle.width;
        if (!this.parent.simple && !this.parent.single) {
            width -= this.curveWidth - this.curveIndent;
        }
        final int borderVisible;
        final int n3 = borderVisible = (this.parent.borderVisible ? 1 : 0);
        final int n4 = this.parent.onBottom ? n3 : 0;
        final int n5 = this.parent.onBottom ? 0 : n3;
        final Point size = this.parent.getSize();
        final int min = Math.min(x + width, this.parent.getRightItemEdge(gc));
        if ((n2 & 0x8) != 0x0) {
            final int n6 = ((this.parent.getStyle() & 0x800000) != 0x0) ? 1 : 3;
            final int n7 = n3;
            final int n8 = this.parent.onBottom ? (size.y - n5 - this.parent.tabHeight - n6) : (n4 + this.parent.tabHeight + 1);
            final int n9 = size.x - n3 - borderVisible;
            final int n10 = n6 - 1;
            final int[] array = { n7, n8, n7 + n9, n8, n7 + n9, n8 + n10, n7, n8 + n10 };
            if (this.parent.selectionGradientColors != null && !this.parent.selectionGradientVertical) {
                this.drawBackground(gc, array, true);
            }
            else {
                gc.setBackground(this.parent.selectionBackground);
                gc.fillRectangle(n7, n8, n9, n10);
            }
            if (this.parent.single) {
                if (!cTabItem.showing) {
                    return;
                }
            }
            else {
                if (!cTabItem.showing) {
                    final int max = Math.max(0, n3 - 1);
                    final int n11 = this.parent.onBottom ? (y - 1) : (y + height);
                    final int n12 = size.x - borderVisible;
                    gc.setForeground(this.parent.getDisplay().getSystemColor(18));
                    gc.drawLine(max, n11, n12, n11);
                    return;
                }
                final int[] array2 = null;
                int[] array5;
                if (this.parent.onBottom) {
                    int[] array3 = this.parent.simple ? CTabFolderRenderer.SIMPLE_BOTTOM_LEFT_CORNER : CTabFolderRenderer.BOTTOM_LEFT_CORNER;
                    final int[] array4 = this.parent.simple ? CTabFolderRenderer.SIMPLE_BOTTOM_RIGHT_CORNER : this.curve;
                    if (n3 == 0 && n == this.parent.firstIndex) {
                        array3 = new int[] { x, y + height };
                    }
                    array5 = new int[array3.length + array4.length + 8];
                    int n13 = 0;
                    array5[n13++] = x;
                    array5[n13++] = y - 1;
                    array5[n13++] = x;
                    array5[n13++] = y - 1;
                    for (int i = 0; i < array3.length / 2; ++i) {
                        array5[n13++] = x + array3[2 * i];
                        array5[n13++] = y + height + array3[2 * i + 1] - 1;
                    }
                    for (int j = 0; j < array4.length / 2; ++j) {
                        array5[n13++] = (this.parent.simple ? (min - 1 + array4[2 * j]) : (min - this.curveIndent + array4[2 * j]));
                        array5[n13++] = (this.parent.simple ? (y + height + array4[2 * j + 1] - 1) : (y + array4[2 * j + 1] - 2));
                    }
                    array5[n13++] = (this.parent.simple ? (min - 1) : (min + this.curveWidth - this.curveIndent));
                    array5[n13++] = y - 1;
                    array5[n13++] = (this.parent.simple ? (min - 1) : (min + this.curveWidth - this.curveIndent));
                    array5[n13++] = y - 1;
                }
                else {
                    int[] array6 = this.parent.simple ? CTabFolderRenderer.SIMPLE_TOP_LEFT_CORNER : CTabFolderRenderer.TOP_LEFT_CORNER;
                    final int[] array7 = this.parent.simple ? CTabFolderRenderer.SIMPLE_TOP_RIGHT_CORNER : this.curve;
                    if (n3 == 0 && n == this.parent.firstIndex) {
                        array6 = new int[] { x, y };
                    }
                    array5 = new int[array6.length + array7.length + 8];
                    int n14 = 0;
                    array5[n14++] = x;
                    array5[n14++] = y + height + 1;
                    array5[n14++] = x;
                    array5[n14++] = y + height + 1;
                    for (int k = 0; k < array6.length / 2; ++k) {
                        array5[n14++] = x + array6[2 * k];
                        array5[n14++] = y + array6[2 * k + 1];
                    }
                    for (int l = 0; l < array7.length / 2; ++l) {
                        array5[n14++] = (this.parent.simple ? (min - 1 + array7[2 * l]) : (min - this.curveIndent + array7[2 * l]));
                        array5[n14++] = y + array7[2 * l + 1];
                    }
                    array5[n14++] = (this.parent.simple ? (min - 1) : (min + this.curveWidth - this.curveIndent));
                    array5[n14++] = y + height + 1;
                    array5[n14++] = (this.parent.simple ? (min - 1) : (min + this.curveWidth - this.curveIndent));
                    array5[n14++] = y + height + 1;
                }
                final Rectangle clipping = gc.getClipping();
                final Rectangle bounds;
                final Rectangle rectangle2 = bounds = cTabItem.getBounds();
                ++bounds.height;
                if (this.parent.onBottom) {
                    final Rectangle rectangle3 = rectangle2;
                    --rectangle3.y;
                }
                final boolean intersects = clipping.intersects(rectangle2);
                if (intersects) {
                    if (this.parent.selectionGradientColors != null && !this.parent.selectionGradientVertical) {
                        this.drawBackground(gc, array5, true);
                    }
                    else {
                        final Color selectionBackground = this.parent.selectionBackground;
                        final Image selectionBgImage = this.parent.selectionBgImage;
                        final Color[] selectionGradientColors = this.parent.selectionGradientColors;
                        final int[] selectionGradientPercents = this.parent.selectionGradientPercents;
                        final boolean selectionGradientVertical = this.parent.selectionGradientVertical;
                        final int n15 = x;
                        final int n16 = this.parent.onBottom ? (y - 1) : (y + 1);
                        int n17 = width;
                        final int n18 = height;
                        if (!this.parent.single && !this.parent.simple) {
                            n17 += this.curveWidth - this.curveIndent;
                        }
                        this.drawBackground(gc, array5, n15, n16, n17, n18, selectionBackground, selectionBgImage, selectionGradientColors, selectionGradientPercents, selectionGradientVertical);
                    }
                }
                this.drawHighlight(gc, rectangle, n2, min);
                array5[0] = Math.max(0, n3 - 1);
                if (n3 == 0 && n == this.parent.firstIndex) {
                    array5[1] = (this.parent.onBottom ? (y + height - 1) : y);
                    array5[5] = (array5[3] = array5[1]);
                }
                array5[array5.length - 2] = size.x - borderVisible + 1;
                for (int n19 = 0; n19 < array5.length / 2; ++n19) {
                    if (array5[2 * n19 + 1] == y + height + 1) {
                        final int[] array8 = array5;
                        final int n20 = 2 * n19 + 1;
                        --array8[n20];
                    }
                }
                final Color systemColor = this.parent.getDisplay().getSystemColor(18);
                if (!systemColor.equals(this.lastBorderColor)) {
                    this.createAntialiasColors();
                }
                this.antialias(array5, this.selectedInnerColor, this.selectedOuterColor, gc);
                gc.setForeground(systemColor);
                gc.drawPolyline(array5);
                if (!intersects) {
                    return;
                }
            }
        }
        if ((n2 & 0x10) != 0x0) {
            final Rectangle computeTrim = this.computeTrim(n, 0, 0, 0, 0, 0);
            int n21 = x - computeTrim.x;
            if (this.parent.single && (this.parent.showClose || cTabItem.showClose)) {
                n21 += cTabItem.closeRect.width;
            }
            final Image image = cTabItem.getImage();
            if (image != null) {
                final Rectangle bounds2 = image.getBounds();
                int n22 = min - n21 - (computeTrim.width + computeTrim.x);
                if (!this.parent.single && cTabItem.closeRect.width > 0) {
                    n22 -= cTabItem.closeRect.width + 4;
                }
                if (bounds2.width < n22) {
                    gc.drawImage(image, n21, y + (height - bounds2.height) / 2 + (this.parent.onBottom ? -1 : 1));
                    n21 += bounds2.width + 4;
                }
            }
            int shortenedTextWidth = min - n21 - (computeTrim.width + computeTrim.x);
            if (!this.parent.single && cTabItem.closeRect.width > 0) {
                shortenedTextWidth -= cTabItem.closeRect.width + 4;
            }
            if (shortenedTextWidth > 0) {
                final Font font = gc.getFont();
                gc.setFont((cTabItem.font == null) ? this.parent.getFont() : cTabItem.font);
                if (cTabItem.shortenedText == null || cTabItem.shortenedTextWidth != shortenedTextWidth) {
                    cTabItem.shortenedText = this.shortenText(gc, cTabItem.getText(), shortenedTextWidth);
                    cTabItem.shortenedTextWidth = shortenedTextWidth;
                }
                final Point textExtent = gc.textExtent(cTabItem.shortenedText, 9);
                final int n23 = y + (height - textExtent.y) / 2 + (this.parent.onBottom ? -1 : 1);
                gc.setForeground(this.parent.selectionForeground);
                gc.drawText(cTabItem.shortenedText, n21, n23, 9);
                gc.setFont(font);
                if (this.parent.isFocusControl()) {
                    final Display display = this.parent.getDisplay();
                    if (this.parent.simple || this.parent.single) {
                        gc.setBackground(display.getSystemColor(2));
                        gc.setForeground(display.getSystemColor(1));
                        gc.drawFocus(n21 - 1, n23 - 1, textExtent.x + 2, textExtent.y + 2);
                    }
                    else {
                        gc.setForeground(display.getSystemColor(17));
                        gc.drawLine(n21, n23 + textExtent.y + 1, n21 + textExtent.x + 1, n23 + textExtent.y + 1);
                    }
                }
            }
            if (this.parent.showClose || cTabItem.showClose) {
                this.drawClose(gc, cTabItem.closeRect, cTabItem.closeImageState);
            }
        }
    }
    
    void drawTabArea(final GC gc, final Rectangle rectangle, final int n) {
        final Point size = this.parent.getSize();
        final int[] array = null;
        final Color systemColor = this.parent.getDisplay().getSystemColor(18);
        final int tabHeight = this.parent.tabHeight;
        final int style = this.parent.getStyle();
        final int borderVisible;
        final int n2 = borderVisible = (this.parent.borderVisible ? 1 : 0);
        final int n3 = this.parent.onBottom ? n2 : 0;
        final int n4 = this.parent.onBottom ? 0 : n2;
        final int selectedIndex = this.parent.selectedIndex;
        final int n5 = ((style & 0x800000) != 0x0) ? 1 : 3;
        if (tabHeight != 0) {
            final int max = Math.max(0, n2 - 1);
            final int n6 = this.parent.onBottom ? (size.y - n4 - tabHeight) : n3;
            final int n7 = size.x - n2 - borderVisible + 1;
            final int n8 = tabHeight - 1;
            final boolean simple = this.parent.simple;
            int[] array4;
            if (this.parent.onBottom) {
                int[] array2;
                int[] array3;
                if ((style & 0x800) != 0x0) {
                    array2 = (simple ? CTabFolderRenderer.SIMPLE_BOTTOM_LEFT_CORNER : CTabFolderRenderer.BOTTOM_LEFT_CORNER);
                    array3 = (simple ? CTabFolderRenderer.SIMPLE_BOTTOM_RIGHT_CORNER : CTabFolderRenderer.BOTTOM_RIGHT_CORNER);
                }
                else {
                    array2 = (simple ? CTabFolderRenderer.SIMPLE_BOTTOM_LEFT_CORNER_BORDERLESS : CTabFolderRenderer.BOTTOM_LEFT_CORNER_BORDERLESS);
                    array3 = (simple ? CTabFolderRenderer.SIMPLE_BOTTOM_RIGHT_CORNER_BORDERLESS : CTabFolderRenderer.BOTTOM_RIGHT_CORNER_BORDERLESS);
                }
                array4 = new int[array2.length + array3.length + 4];
                int n9 = 0;
                array4[n9++] = max;
                array4[n9++] = n6 - n5;
                for (int i = 0; i < array2.length / 2; ++i) {
                    array4[n9++] = max + array2[2 * i];
                    array4[n9++] = n6 + n8 + array2[2 * i + 1];
                    if (n2 == 0) {
                        final int[] array5 = array4;
                        final int n10 = n9 - 1;
                        ++array5[n10];
                    }
                }
                for (int j = 0; j < array3.length / 2; ++j) {
                    array4[n9++] = max + n7 + array3[2 * j];
                    array4[n9++] = n6 + n8 + array3[2 * j + 1];
                    if (n2 == 0) {
                        final int[] array6 = array4;
                        final int n11 = n9 - 1;
                        ++array6[n11];
                    }
                }
                array4[n9++] = max + n7;
                array4[n9++] = n6 - n5;
            }
            else {
                int[] array7;
                int[] array8;
                if ((style & 0x800) != 0x0) {
                    array7 = (simple ? CTabFolderRenderer.SIMPLE_TOP_LEFT_CORNER : CTabFolderRenderer.TOP_LEFT_CORNER);
                    array8 = (simple ? CTabFolderRenderer.SIMPLE_TOP_RIGHT_CORNER : CTabFolderRenderer.TOP_RIGHT_CORNER);
                }
                else {
                    array7 = (simple ? CTabFolderRenderer.SIMPLE_TOP_LEFT_CORNER_BORDERLESS : CTabFolderRenderer.TOP_LEFT_CORNER_BORDERLESS);
                    array8 = (simple ? CTabFolderRenderer.SIMPLE_TOP_RIGHT_CORNER_BORDERLESS : CTabFolderRenderer.TOP_RIGHT_CORNER_BORDERLESS);
                }
                array4 = new int[array7.length + array8.length + 4];
                int n12 = 0;
                array4[n12++] = max;
                array4[n12++] = n6 + n8 + n5 + 1;
                for (int k = 0; k < array7.length / 2; ++k) {
                    array4[n12++] = max + array7[2 * k];
                    array4[n12++] = n6 + array7[2 * k + 1];
                }
                for (int l = 0; l < array8.length / 2; ++l) {
                    array4[n12++] = max + n7 + array8[2 * l];
                    array4[n12++] = n6 + array8[2 * l + 1];
                }
                array4[n12++] = max + n7;
                array4[n12++] = n6 + n8 + n5 + 1;
            }
            this.drawBackground(gc, array4, this.parent.single && selectedIndex != -1);
            final Region region = new Region();
            region.add(new Rectangle(max, n6, n7 + 1, n8 + 1));
            region.subtract(array4);
            gc.setBackground(this.parent.getParent().getBackground());
            this.fillRegion(gc, region);
            region.dispose();
            if (selectedIndex == -1) {
                final int n13 = n2;
                final int n14 = this.parent.onBottom ? (size.y - n4 - tabHeight - 1) : (n3 + tabHeight);
                final int n15 = size.x - borderVisible;
                gc.setForeground(systemColor);
                gc.drawLine(n13, n14, n15, n14);
            }
            if (n2 > 0) {
                if (!systemColor.equals(this.lastBorderColor)) {
                    this.createAntialiasColors();
                }
                this.antialias(array4, null, this.tabAreaColor, gc);
                gc.setForeground(systemColor);
                gc.drawPolyline(array4);
            }
            return;
        }
        if ((style & 0x800000) != 0x0 && (style & 0x800) == 0x0) {
            return;
        }
        final int n16 = n2 - 1;
        final int n17 = size.x - borderVisible;
        final int n18 = this.parent.onBottom ? (size.y - n4 - n5 - 1) : (n3 + n5);
        int n19 = this.parent.onBottom ? (size.y - n4) : n3;
        if (n2 > 0 && this.parent.onBottom) {
            --n19;
        }
        final int[] array9 = { n16, n18, n16, n19, n17, n19, n17, n18 };
        if (selectedIndex != -1 && this.parent.selectionGradientColors != null && this.parent.selectionGradientColors.length > 1 && !this.parent.selectionGradientVertical) {
            this.drawBackground(gc, array9, true);
        }
        else if (selectedIndex == -1 && this.parent.gradientColors != null && this.parent.gradientColors.length > 1 && !this.parent.gradientVertical) {
            this.drawBackground(gc, array9, false);
        }
        else {
            gc.setBackground((selectedIndex == -1) ? this.parent.getBackground() : this.parent.selectionBackground);
            gc.fillPolygon(array9);
        }
        if (n2 > 0) {
            gc.setForeground(systemColor);
            gc.drawPolyline(array9);
        }
    }
    
    void drawUnselected(final int n, final GC gc, final Rectangle rectangle, final int n2) {
        final CTabItem cTabItem = this.parent.items[n];
        final int x = rectangle.x;
        final int y = rectangle.y;
        final int height = rectangle.height;
        final int width = rectangle.width;
        if (!cTabItem.showing) {
            return;
        }
        if (!gc.getClipping().intersects(rectangle)) {
            return;
        }
        if ((n2 & 0x8) != 0x0) {
            if (n > 0 && n < this.parent.selectedIndex) {
                this.drawLeftUnselectedBorder(gc, rectangle, n2);
            }
            if (n > this.parent.selectedIndex) {
                this.drawRightUnselectedBorder(gc, rectangle, n2);
            }
        }
        if ((n2 & 0x10) != 0x0) {
            final Rectangle computeTrim = this.computeTrim(n, 0, 0, 0, 0, 0);
            int n3 = x - computeTrim.x;
            final Image image = cTabItem.getImage();
            if (image != null && this.parent.showUnselectedImage) {
                final Rectangle bounds = image.getBounds();
                int n4 = x + width - n3 - (computeTrim.width + computeTrim.x);
                if (this.parent.showUnselectedClose && (this.parent.showClose || cTabItem.showClose)) {
                    n4 -= cTabItem.closeRect.width + 4;
                }
                if (bounds.width < n4) {
                    final int n5 = n3;
                    final int height2 = bounds.height;
                    final int n6 = y + (height - height2) / 2 + (this.parent.onBottom ? -1 : 1);
                    final int n7 = bounds.width * height2 / bounds.height;
                    gc.drawImage(image, bounds.x, bounds.y, bounds.width, bounds.height, n5, n6, n7, height2);
                    n3 += n7 + 4;
                }
            }
            int shortenedTextWidth = x + width - n3 - (computeTrim.width + computeTrim.x);
            if (this.parent.showUnselectedClose && (this.parent.showClose || cTabItem.showClose)) {
                shortenedTextWidth -= cTabItem.closeRect.width + 4;
            }
            if (shortenedTextWidth > 0) {
                final Font font = gc.getFont();
                gc.setFont((cTabItem.font == null) ? this.parent.getFont() : cTabItem.font);
                if (cTabItem.shortenedText == null || cTabItem.shortenedTextWidth != shortenedTextWidth) {
                    cTabItem.shortenedText = this.shortenText(gc, cTabItem.getText(), shortenedTextWidth);
                    cTabItem.shortenedTextWidth = shortenedTextWidth;
                }
                final int n8 = y + (height - gc.textExtent(cTabItem.shortenedText, 9).y) / 2 + (this.parent.onBottom ? -1 : 1);
                gc.setForeground(this.parent.getForeground());
                gc.drawText(cTabItem.shortenedText, n3, n8, 9);
                gc.setFont(font);
            }
            if (this.parent.showUnselectedClose && (this.parent.showClose || cTabItem.showClose)) {
                this.drawClose(gc, cTabItem.closeRect, cTabItem.closeImageState);
            }
        }
    }
    
    void fillRegion(final GC gc, final Region clipping) {
        final Region clipping2 = new Region();
        gc.getClipping(clipping2);
        clipping.intersect(clipping2);
        gc.setClipping(clipping);
        gc.fillRectangle(clipping.getBounds());
        gc.setClipping(clipping2);
        clipping2.dispose();
    }
    
    Color getFillColor() {
        if (this.fillColor == null) {
            this.fillColor = new Color(this.parent.getDisplay(), CTabFolderRenderer.CLOSE_FILL);
        }
        return this.fillColor;
    }
    
    boolean isSelectionHighlightColorsCacheHit(final Color color) {
        if (this.selectionHighlightGradientColorsCache == null) {
            return false;
        }
        if (this.selectionHighlightGradientColorsCache.length < 2) {
            return false;
        }
        final Color color2 = this.selectionHighlightGradientColorsCache[0];
        final Color color3 = this.selectionHighlightGradientColorsCache[this.selectionHighlightGradientColorsCache.length - 1];
        return color2.equals(color) && this.selectionHighlightGradientColorsCache.length == this.parent.tabHeight && color3.equals(this.parent.selectionBackground);
    }
    
    void setSelectionHighlightGradientColor(final Color selectionHighlightGradientBegin) {
        this.selectionHighlightGradientBegin = null;
        if (selectionHighlightGradientBegin == null) {
            return;
        }
        if (this.parent.getDisplay().getDepth() < 15) {
            return;
        }
        if (this.parent.selectionGradientColors.length < 2) {
            return;
        }
        this.selectionHighlightGradientBegin = selectionHighlightGradientBegin;
        if (!this.isSelectionHighlightColorsCacheHit(selectionHighlightGradientBegin)) {
            this.createSelectionHighlightGradientColors(selectionHighlightGradientBegin);
        }
    }
    
    String shortenText(final GC gc, final String s, final int n) {
        return this.useEllipses() ? this.shortenText(gc, s, n, "...") : this.shortenText(gc, s, n, "");
    }
    
    String shortenText(final GC gc, String substring, final int n, final String s) {
        if (gc.textExtent(substring, 9).x <= n) {
            return substring;
        }
        final int x = gc.textExtent(s, 9).x;
        final int length = substring.length();
        final TextLayout textLayout = new TextLayout(this.parent.getDisplay());
        textLayout.setText(substring);
        int i;
        for (i = textLayout.getPreviousOffset(length, 2); i > 0; i = textLayout.getPreviousOffset(i, 2)) {
            substring = substring.substring(0, i);
            if (gc.textExtent(substring, 9).x + x <= n) {
                break;
            }
        }
        textLayout.dispose();
        return (i == 0) ? substring.substring(0, 1) : (String.valueOf(substring) + s);
    }
    
    void updateCurves() {
        final int tabHeight = this.parent.tabHeight;
        if (tabHeight == this.lastTabHeight) {
            return;
        }
        if (this.parent.onBottom) {
            final int n = tabHeight - 12;
            this.curve = new int[] { 0, 13 + n, 0, 12 + n, 2, 12 + n, 3, 11 + n, 5, 11 + n, 6, 10 + n, 7, 10 + n, 9, 8 + n, 10, 8 + n, 11, 7 + n, 11 + n, 7, 12 + n, 6, 13 + n, 6, 15 + n, 4, 16 + n, 4, 17 + n, 3, 19 + n, 3, 20 + n, 2, 22 + n, 2, 23 + n, 1 };
            this.curveWidth = 26 + n;
            this.curveIndent = this.curveWidth / 3;
        }
        else {
            final int n2 = tabHeight - 12;
            this.curve = new int[] { 0, 0, 0, 1, 2, 1, 3, 2, 5, 2, 6, 3, 7, 3, 9, 5, 10, 5, 11, 6, 11 + n2, 6 + n2, 12 + n2, 7 + n2, 13 + n2, 7 + n2, 15 + n2, 9 + n2, 16 + n2, 9 + n2, 17 + n2, 10 + n2, 19 + n2, 10 + n2, 20 + n2, 11 + n2, 22 + n2, 11 + n2, 23 + n2, 12 + n2 };
            this.curveWidth = 26 + n2;
            this.curveIndent = this.curveWidth / 3;
            this.topCurveHighlightStart = new int[] { 0, 2, 1, 2, 2, 2, 3, 3, 4, 3, 5, 3, 6, 4, 7, 4, 8, 5, 9, 6, 10, 6 };
            this.topCurveHighlightEnd = new int[] { 10 + n2, 6 + n2, 11 + n2, 7 + n2, 12 + n2, 8 + n2, 13 + n2, 8 + n2, 14 + n2, 9 + n2, 15 + n2, 10 + n2, 16 + n2, 10 + n2, 17 + n2, 11 + n2, 18 + n2, 11 + n2, 19 + n2, 11 + n2, 20 + n2, 12 + n2, 21 + n2, 12 + n2, 22 + n2, 12 + n2 };
        }
    }
    
    boolean useEllipses() {
        return this.parent.simple;
    }
}

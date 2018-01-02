import java.awt.AWTEvent;
import java.awt.Rectangle;
import java.util.Vector;
import java.awt.FontMetrics;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.ImageObserver;
import java.awt.Image;
import java.util.Hashtable;

// 
// Decompiled by Procyon v0.5.30
// 

public class Slider extends AbstractBox
{
    private boolean vertical;
    private boolean showLines;
    private boolean isPressed;
    private boolean isTouched;
    private boolean iCanDrag;
    private double oneSplit;
    private double first;
    private double last;
    private double step;
    private double value;
    private double tmpClick;
    private double decimalMultipler;
    private int decimalDigits;
    private String backgroundColor;
    private String foregroundColor;
    private int linesMultipler;
    private Hashtable realImageList;
    private Image pointer_v;
    private Image pointer_h;
    private Image pointer_v_touched;
    private Image pointer_h_touched;
    private Image pointer_v_pressed;
    private Image pointer_h_pressed;
    private Image line_v;
    private Image line_h;
    private ImageObserver ImgObs;
    private static Hashtable imagesCache;
    private boolean needsUpdateColor;
    
    public Slider() {
        this.needsUpdateColor = false;
        this.foregroundColor = "";
    }
    
    public final void enCalculRect() {
        if (super.width == -1 || super.height == -1) {
            if (this.vertical) {
                super.width = 40;
                super.height = 200;
            }
            else {
                super.width = 170;
                super.height = 40;
            }
        }
        super.baseline = super.height / 2;
    }
    
    public final void onPaint(final BoxComponent boxComponent, final Graphics graphics) {
        this.updateColor(boxComponent);
        this.drawFrame(graphics);
        this.drawStatusLines(graphics);
        this.drawCenterLine(graphics);
        this.drawPointer(graphics);
        this.drawValue(graphics);
    }
    
    private void drawFrame(final Graphics graphics) {
        if (this.backgroundColor != "trans") {
            graphics.setColor(Color.decode(this.backgroundColor));
            graphics.fillRect(0, 0, super.width, super.height);
        }
    }
    
    private void drawStatusLines(final Graphics graphics) {
        if (this.showLines) {
            graphics.setColor(Color.black);
            final int n = (int)((this.last - this.first) / (this.step * this.linesMultipler));
            final double n2 = this.linesMultipler * this.step * this.oneSplit;
            final double n3 = 10.0 + (this.last - this.first) * this.oneSplit;
            if (this.vertical) {
                float n4 = 10.0f;
                final int n5 = super.width / 2;
                final int n6 = n5 + 4;
                for (int i = 0; i <= n; ++i) {
                    graphics.drawLine(n5, (int)n4, n6, (int)n4);
                    n4 += (float)n2;
                }
                if (n4 - n2 != n3) {
                    graphics.drawLine(n5, (int)n3, n6, (int)n3);
                }
            }
            else {
                float n7 = 10.0f;
                final int n8 = super.height / 2;
                final int n9 = n8 + 4;
                for (int j = 0; j <= n; ++j) {
                    graphics.drawLine((int)n7, n8, (int)n7, n9);
                    n7 += (float)n2;
                }
                if (n7 - n2 != n3) {
                    graphics.drawLine((int)n3, n8, (int)n3, n9);
                }
            }
        }
    }
    
    private void drawCenterLine(final Graphics graphics) {
        if (this.vertical) {
            graphics.drawImage(this.line_v, super.width / 2 - 1, 10, 2, super.height - 20, this.ImgObs);
        }
        else {
            graphics.drawImage(this.line_h, 10, super.height / 2 - 1, super.width - 20, 2, this.ImgObs);
        }
    }
    
    private void drawPointer(final Graphics graphics) {
        final int n = (int)(10.0 + this.oneSplit * (this.value - this.first));
        if (this.vertical) {
            if (this.isPressed) {
                graphics.drawImage(this.pointer_h_pressed, super.width / 2 - 10, n - 5, this.ImgObs);
            }
            else if (this.isTouched) {
                graphics.drawImage(this.pointer_h_touched, super.width / 2 - 10, n - 5, this.ImgObs);
            }
            else {
                graphics.drawImage(this.pointer_h, super.width / 2 - 10, n - 5, this.ImgObs);
            }
        }
        else if (this.isPressed) {
            graphics.drawImage(this.pointer_v_pressed, n - 5, super.height / 2 - 10, this.ImgObs);
        }
        else if (this.isTouched) {
            graphics.drawImage(this.pointer_v_touched, n - 5, super.height / 2 - 10, this.ImgObs);
        }
        else {
            graphics.drawImage(this.pointer_v, n - 5, super.height / 2 - 10, this.ImgObs);
        }
    }
    
    private void drawValue(final Graphics graphics) {
        if (this.getCanDrag()) {
            graphics.setColor(Color.gray);
            final String printDouble = printDouble(this.value, 8);
            final FontMetrics fontMetrics = graphics.getFontMetrics();
            int n = (int)(10.0 + this.oneSplit * (this.value - this.first) + 5.0 + 5.0);
            final int stringWidth = fontMetrics.stringWidth(printDouble);
            if (n + stringWidth > super.width - 10) {
                n -= stringWidth + 15 + 5;
            }
            graphics.drawString(printDouble, n, super.height / 2 - 5);
        }
    }
    
    public final WImage loadImage(final BoxComponent boxComponent, final String s, final int n) {
        try {
            Vector<Object> list = Slider.imagesCache.get(s);
            if (list == null) {
                list = (Vector<Object>)WImage.readList(((Formula)boxComponent).getResourceProvider().getInternalResourceStream(s), 8);
                Slider.imagesCache.put(s, list);
            }
            return (WImage)list.elementAt(n);
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
    
    public final double isMouseWithPointer(final float n, final float n2) {
        if (this.vertical) {
            final double n3 = super.width / 2;
            final double n4 = 10.0 + (this.value - this.first) * this.oneSplit;
            if (n2 >= n4 - 5.0 && n2 <= n4 + 5.0 && n >= n3 - 10.0 && n <= n3 + 10.0) {
                return n4;
            }
            return -1.0;
        }
        else {
            final double n5 = 10.0 + (this.value - this.first) * this.oneSplit;
            final double n6 = super.height / 2;
            if (n >= n5 - 5.0 && n <= n5 + 5.0 && n2 >= n6 - 10.0 && n2 <= n6 + 10.0) {
                return n5;
            }
            return -1.0;
        }
    }
    
    public final String scriptCommand(final BoxScripting boxScripting) {
        return "slider";
    }
    
    public final boolean hasProperties() {
        return true;
    }
    
    public final void exchangeProperties(final Hashtable hashtable, final int n) {
        this.vertical = Attributes.exchangeBool(hashtable, "vertical", n, this.vertical, false);
        this.showLines = Attributes.exchangeBool(hashtable, "showLines", n, this.showLines, true);
        if (this.vertical) {
            super.width = Attributes.exchangeInt(hashtable, "width", n, super.width, 40);
            super.height = Attributes.exchangeInt(hashtable, "height", n, super.height, 200);
        }
        else {
            super.width = Attributes.exchangeInt(hashtable, "width", n, super.width, 170);
            super.height = Attributes.exchangeInt(hashtable, "height", n, super.height, 40);
        }
        this.first = Attributes.exchangeDouble(hashtable, "first", n, this.first, 0.0);
        this.last = Attributes.exchangeDouble(hashtable, "last", n, this.last, 1000.0);
        this.step = Attributes.exchangeDouble(hashtable, "step", n, this.step, (this.last - this.first) / 1000.0);
        this.linesMultipler = 1;
        while ((this.last - this.first) / (this.step * this.linesMultipler) > 20.0) {
            ++this.linesMultipler;
        }
        this.value = Attributes.exchangeDouble(hashtable, "value", n, this.value, 0.0);
        if (this.value < this.first) {
            this.value = this.first;
        }
        else if (this.value > this.last) {
            this.value = this.last;
        }
        if (this.last == this.first) {
            this.oneSplit = 0.0;
        }
        else if (this.vertical) {
            this.oneSplit = (super.height - 20) / (this.last - this.first);
        }
        else {
            this.oneSplit = (super.width - 20) / (this.last - this.first);
        }
        this.decimalDigits = (int)Math.floor(I(Math.abs((this.last - this.first) / (super.width - 20))));
        this.decimalMultipler = Math.pow(10.0, Math.floor(I(Math.abs((this.last - this.first) / (super.width - 20)))));
        this.backgroundColor = Attributes.exchangeString(hashtable, "backgroundColor", n, this.backgroundColor, "trans");
        final String foregroundColor = this.foregroundColor;
        this.foregroundColor = Attributes.exchangeString(hashtable, "foregroundColor", n, this.foregroundColor, "#FFFFFF");
        if (!foregroundColor.equalsIgnoreCase(this.foregroundColor)) {
            this.needsUpdateColor = true;
        }
    }
    
    public final Image applyForegroundColor(final WImage wImage, final String s) {
        return WImage.fill(Color.decode(s).getRGB(), wImage).toJavaImage(null);
    }
    
    public final void setValue(double n) {
        if (n < this.first) {
            n = this.first;
        }
        else if (n > this.last) {
            n = this.last;
        }
        this.value = this.step * Math.round((n - this.first) / this.step) + this.first;
        if (this.value < this.first) {
            this.value = this.first;
        }
        else if (this.value > this.last) {
            this.value = this.last;
        }
    }
    
    public final double getValue() {
        return this.value;
    }
    
    private void setMouseValue(final double n, final double n2) {
        if (this.vertical) {
            this.setValue(this.first + (n2 - 10.0) / this.oneSplit);
        }
        else {
            this.setValue(this.first + (n - 10.0) / this.oneSplit);
        }
    }
    
    public final boolean onClick(final int n, final int n2, final BoxComponent boxComponent) {
        if (this.isStyle(8)) {
            return false;
        }
        if (this.isMouseWithPointer(n, n2) > -1.0) {
            this.onHit(n, n2, boxComponent);
        }
        else {
            this.iCanDrag = false;
        }
        return true;
    }
    
    public final void onHit(final int n, final int n2, final BoxComponent boxComponent) {
        this.iCanDrag = true;
        if (this.vertical) {
            this.tmpClick = n2 - this.isMouseWithPointer(n, n2);
            this.setMouseValue(n, this.isMouseWithPointer(n, n2));
        }
        else {
            this.tmpClick = n - this.isMouseWithPointer(n, n2);
            this.setMouseValue(this.isMouseWithPointer(n, n2), n2);
        }
        this.isPressed = true;
        ((Formula)boxComponent).repaint(this);
        ((Formula)boxComponent).mouseReleasedCapsa = this;
    }
    
    public final boolean onDragged(final int n, final int n2, final BoxComponent boxComponent) {
        if (this.iCanDrag) {
            if (this.vertical) {
                this.setMouseValue(n, n2 - this.tmpClick);
            }
            else {
                this.setMouseValue(n - this.tmpClick, n2);
            }
            ((Formula)boxComponent).repaint(this);
        }
        return true;
    }
    
    public final boolean onMoved(final int n, final int n2, final BoxComponent boxComponent) {
        if (this.isStyle(8)) {
            return false;
        }
        final boolean isTouched = this.isTouched;
        if (this.isMouseWithPointer(n, n2) > -1.0) {
            this.isTouched = true;
        }
        else {
            this.isTouched = false;
        }
        if (isTouched != this.isTouched) {
            ((Formula)boxComponent).repaint(this);
        }
        return true;
    }
    
    public final void processEvent(final BoxComponent boxComponent, final AWTEvent awtEvent) {
        if (awtEvent == null || awtEvent.getID() == 502) {
            this.isPressed = false;
            this.iCanDrag = false;
            if (boxComponent != null) {
                ((Formula)boxComponent).repaint(this);
            }
        }
    }
    
    public final boolean getCanDrag() {
        return this.iCanDrag;
    }
    
    public final void setColor(final String foregroundColor) {
        if (!this.foregroundColor.equals(foregroundColor)) {
            this.needsUpdateColor = true;
            this.foregroundColor = foregroundColor;
        }
    }
    
    private void updateColor(final BoxComponent boxComponent) {
        if (this.realImageList == null) {
            (this.realImageList = new Hashtable(8)).put("pointer_v", this.loadImage(boxComponent, "Icones/slider_items.wbi", 6));
            this.realImageList.put("pointer_h", this.loadImage(boxComponent, "Icones/slider_items.wbi", 3));
            this.realImageList.put("pointer_v_touched", this.loadImage(boxComponent, "Icones/slider_items.wbi", 0));
            this.realImageList.put("pointer_h_touched", this.loadImage(boxComponent, "Icones/slider_items.wbi", 5));
            this.realImageList.put("pointer_v_pressed", this.loadImage(boxComponent, "Icones/slider_items.wbi", 7));
            this.realImageList.put("pointer_h_pressed", this.loadImage(boxComponent, "Icones/slider_items.wbi", 4));
            this.realImageList.put("line_v", this.loadImage(boxComponent, "Icones/slider_items.wbi", 2));
            this.realImageList.put("line_h", this.loadImage(boxComponent, "Icones/slider_items.wbi", 1));
        }
        if (this.needsUpdateColor) {
            this.pointer_v = this.applyForegroundColor(this.realImageList.get("pointer_v"), this.foregroundColor);
            this.pointer_h = this.applyForegroundColor(this.realImageList.get("pointer_h"), this.foregroundColor);
            this.pointer_v_touched = this.applyForegroundColor(this.realImageList.get("pointer_v_touched"), this.foregroundColor);
            this.pointer_h_touched = this.applyForegroundColor(this.realImageList.get("pointer_h_touched"), this.foregroundColor);
            this.pointer_v_pressed = this.applyForegroundColor(this.realImageList.get("pointer_v_pressed"), this.foregroundColor);
            this.pointer_h_pressed = this.applyForegroundColor(this.realImageList.get("pointer_h_pressed"), this.foregroundColor);
            this.line_v = this.applyForegroundColor(this.realImageList.get("line_v"), this.foregroundColor);
            this.line_h = this.applyForegroundColor(this.realImageList.get("line_h"), this.foregroundColor);
            this.needsUpdateColor = false;
        }
    }
    
    public static final String printDouble(double n, final int n2) {
        boolean b = false;
        String string = "";
        if (n < 0.0) {
            b = true;
            n = -n;
        }
        if (n == 0.0) {
            return "0";
        }
        final int n3 = (int)Math.floor(I(n)) - n2;
        final long round = Math.round(n / Math.pow(10.0, n3));
        final String string2 = "" + round;
        String s;
        if (n3 >= 0 || n3 < -(n2 + n2 / 2)) {
            s = string2.substring(0, 1) + "." + string2.substring(1);
            string = "E" + (n3 + n2);
        }
        else if (n3 < -n2) {
            String string3 = "0.";
            for (int i = 0; i < -n3 - n2 - 1; ++i) {
                string3 += "0";
            }
            s = string3 + round;
        }
        else {
            final int n4 = n2 + n3 + 1;
            s = string2.substring(0, n4) + "." + string2.substring(n4);
        }
        int j;
        for (j = s.length() - 1; j >= 0; --j) {
            final char char1 = s.charAt(j);
            if (char1 == '.') {
                --j;
                break;
            }
            if (char1 != '0') {
                break;
            }
        }
        final String substring = s.substring(0, j + 1);
        if (b) {
            return "-" + substring + string;
        }
        return substring + string;
    }
    
    static final double I(final double n) {
        return Math.log(n) / Math.log(10.0);
    }
    
    static {
        Slider.imagesCache = new Hashtable();
    }
}

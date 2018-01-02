import java.util.Hashtable;
import java.awt.image.ImageObserver;
import java.awt.Image;
import java.awt.font.TextLayout;
import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Font;
import java.awt.Color;

// 
// Decompiled by Procyon v0.5.30
// 

class Watcher implements Drawable
{
    static final Color black;
    static final Font labelFont;
    static final int NORMAL_MODE = 1;
    static final int SLIDER_MODE = 2;
    static final int LARGE_MODE = 3;
    PlayerCanvas canvas;
    StretchyBox box;
    WatcherReadout readout;
    StretchyBox slider;
    String label;
    int mode;
    double sliderMin;
    double sliderMax;
    boolean isDiscrete;
    boolean isShowing;
    
    Watcher(final LContext lContext) {
        this.label = "x";
        this.mode = 1;
        this.sliderMin = 0.0;
        this.sliderMax = 100.0;
        this.isDiscrete = false;
        this.isShowing = true;
        if (lContext != null) {
            this.canvas = lContext.canvas;
        }
        (this.box = new StretchyBox()).setFrameImage(Skin.watcherOuterFrame);
        this.box.x = 50;
        this.box.y = 50;
        this.readout = new WatcherReadout(false);
        this.readout.color = new Color(74, 107, 214);
        (this.slider = new StretchyBox()).setFrameImage(Skin.sliderSlot);
        this.slider.h = 5;
    }
    
    void show() {
        this.isShowing = true;
        this.inval();
    }
    
    void hide() {
        this.isShowing = false;
        this.inval();
    }
    
    public boolean isShowing() {
        return this.isShowing;
    }
    
    public Rectangle rect() {
        return new Rectangle(this.box.x, this.box.y, this.box.w, this.box.h);
    }
    
    public Rectangle fullRect() {
        return this.rect();
    }
    
    void inval() {
        this.canvas.inval(this.rect());
    }
    
    public void paint(final Graphics graphics) {
        final int labelWidth = this.labelWidth(graphics);
        this.readout.beLarge(this.mode == 3);
        this.readout.adjustWidth(graphics);
        this.box.w = labelWidth + this.readout.w + 17;
        this.box.h = ((this.mode == 1) ? 21 : 31);
        if (this.mode == 3) {
            this.readout.x = this.box.x;
            this.readout.y = this.box.y;
            this.readout.paint(graphics);
            return;
        }
        this.box.paint(graphics);
        this.drawLabel(graphics);
        this.readout.x = this.box.x + labelWidth + 12;
        this.readout.y = this.box.y + 3;
        this.readout.paint(graphics);
        if (this.mode == 2) {
            this.drawSlider(graphics);
        }
    }
    
    public void paintBubble(final Graphics graphics) {
    }
    
    public void mouseDown(final int n, final int n2) {
    }
    
    void drawLabel(final Graphics graphics) {
        graphics.setColor(Watcher.black);
        graphics.setFont(Watcher.labelFont);
        graphics.drawString(this.label, this.box.x + 6, this.box.y + 14);
    }
    
    int labelWidth(final Graphics graphics) {
        if (this.label.length() == 0) {
            return 0;
        }
        return (int)new TextLayout(this.label, Watcher.labelFont, ((Graphics2D)graphics).getFontRenderContext()).getBounds().getBounds().getWidth();
    }
    
    void drawSlider(final Graphics graphics) {
        this.slider.x = this.box.x + 6;
        this.slider.y = this.box.y + 21;
        this.slider.w = this.box.w - 12;
        this.slider.paint(graphics);
        graphics.drawImage(Skin.sliderKnob, this.slider.x + Math.max(0, Math.min((int)Math.round((this.slider.w - 8) * ((this.getSliderValue() - this.sliderMin) / (this.sliderMax - this.sliderMin))), this.slider.w - 8)) - 1, this.slider.y - 3, null);
    }
    
    boolean inSlider(final int n, final int n2) {
        return this.mode == 2 && n2 >= this.slider.y - 1 && n2 <= this.slider.y + this.slider.h + 4 && n >= this.slider.x - 4 && n <= this.slider.x + this.slider.w + 5;
    }
    
    public void dragTo(final int n, final int n2) {
        this.setSliderValue((n - this.box.x - 10) * (this.sliderMax - this.sliderMin) / (this.slider.w - 8) + this.sliderMin);
    }
    
    void click(final int n, final int n2) {
        final double sliderValue = this.getSliderValue();
        final int n3 = (n < this.slider.x + (int)Math.round((this.slider.w - 8) * ((sliderValue - this.sliderMin) / (this.sliderMax - this.sliderMin))) + 5) ? -1 : 1;
        if (this.isDiscrete) {
            this.setSliderValue(sliderValue + n3);
        }
        else {
            this.setSliderValue(sliderValue + n3 * ((this.sliderMax - this.sliderMin) / 100.0));
        }
    }
    
    double getSliderValue() {
        final String prs = Logo.prs(this.getObjProp(this, "param"));
        final Hashtable varsTable = this.getVarsTable();
        if (prs == null || varsTable == null) {
            return (this.sliderMin + this.sliderMax) / 2.0;
        }
        final Number value = varsTable.get(Logo.aSymbol(prs, this.canvas.lc));
        return (value instanceof Number) ? value.doubleValue() : ((this.sliderMin + this.sliderMax) / 2.0);
    }
    
    void setSliderValue(final double n) {
        final double min = Math.min(this.sliderMax, Math.max(this.sliderMin, this.isDiscrete ? ((double)Math.round(n)) : n));
        final String prs = Logo.prs(this.getObjProp(this, "param"));
        final Hashtable varsTable = this.getVarsTable();
        if (prs == null || varsTable == null) {
            return;
        }
        varsTable.put(Logo.aSymbol(prs, this.canvas.lc), new Double(min));
        this.inval();
    }
    
    Hashtable getVarsTable() {
        final String prs = Logo.prs(this.getObjProp(this, "op"));
        Logo.prs(this.getObjProp(this, "param"));
        final Object objProp = this.getObjProp(this, "target");
        if (objProp == null || !prs.equals("getVar:")) {
            return null;
        }
        final Object objProp2 = this.getObjProp(objProp, "vars");
        if (objProp2 == null) {
            return null;
        }
        return (Hashtable)this.canvas.lc.props.get(objProp2);
    }
    
    Object getObjProp(final Object o, final String s) {
        final Hashtable<Object, Object> hashtable = this.canvas.lc.props.get(o);
        if (hashtable == null) {
            return null;
        }
        return hashtable.get(Logo.aSymbol(s, this.canvas.lc));
    }
    
    static {
        black = new Color(0, 0, 0);
        labelFont = new Font("Arial Unicode MS", 1, 10);
    }
}

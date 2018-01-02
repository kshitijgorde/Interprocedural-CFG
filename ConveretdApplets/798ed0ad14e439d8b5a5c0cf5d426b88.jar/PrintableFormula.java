import java.awt.Cursor;
import java.awt.Point;
import java.awt.Component;
import java.awt.FontMetrics;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;

// 
// Decompiled by Procyon v0.5.30
// 

public class PrintableFormula implements BoxComponent
{
    private Formula formula;
    private Rectangles pages;
    private AbstractBox rootBox;
    private FormulaPaging pageMaker;
    private Length footerHeight;
    private static final Font footerFont;
    private static final Color footerColor;
    private Length[] printingArea;
    private Graphics printGraphics;
    private int dpi;
    
    public PrintableFormula(final Formula formula) {
        this.footerHeight = new Length("0.8cm");
        this.printGraphics = null;
        this.dpi = -1;
        this.formula = formula;
        this.pageMaker = new FormulaPaging();
        this.pages = new Rectangles();
    }
    
    public final void init(final Graphics printGraphics, final Length recommendedWidth, final Length length, final int dpi) {
        this.rootBox = this.formula.parse(this.formula.script(this.formula.lacapsa));
        this.rootBox.recommendedWidth = recommendedWidth;
        this.printGraphics = printGraphics;
        this.setDpi(dpi);
        this.rootBox.Composa(this);
        this.printingArea = new Length[] { recommendedWidth, length };
        this.pageMaker.setPrintableArea(recommendedWidth, new Length(length.toUserUnit(this.rootBox.em(), dpi) - this.footerHeight.toUserUnit(this.rootBox.em(), dpi), 0));
        this.pages = this.pageMaker.computeBounds(this.rootBox, this.getDPI(), false);
        this.printGraphics = null;
    }
    
    public final int getPageNumber() {
        return this.pages.size();
    }
    
    public final boolean print(final Graphics printGraphics, final int n) {
        if (n < 0 || n >= this.pages.size()) {
            return false;
        }
        this.printGraphics = printGraphics;
        final Rectangle clipBounds = printGraphics.getClipBounds();
        final Rectangle rectangle = this.pages.rectangleAt(n);
        printGraphics.translate(-rectangle.x, -rectangle.y);
        printGraphics.clipRect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
        this.rootBox.DrawRectCorrecte(this, printGraphics);
        printGraphics.translate(rectangle.x, rectangle.y);
        printGraphics.setClip(clipBounds);
        this.paintFooter(printGraphics, "" + (n + 1) + "/" + this.pages.size());
        this.printGraphics = null;
        return true;
    }
    
    private void paintFooter(final Graphics graphics, final String s) {
        graphics.setFont(PrintableFormula.footerFont);
        graphics.setColor(PrintableFormula.footerColor);
        final FontMetrics fontMetrics = graphics.getFontMetrics();
        graphics.drawString(s, (this.printingArea[0].toUserUnit(this.rootBox.em(), this.dpi) - fontMetrics.stringWidth(s)) / 2, this.printingArea[1].toUserUnit(this.rootBox.em(), this.dpi) - fontMetrics.getDescent() - fontMetrics.getLeading());
    }
    
    private void setDpi(final int dpi) {
        this.dpi = dpi;
    }
    
    public final BoxScripting getBoxScripting() {
        return this.formula.getBoxScripting();
    }
    
    public final BoxPosition getCaret() {
        return this.formula.getCaret();
    }
    
    public final Color getColor(final int n, final AbstractBox abstractBox) {
        return this.formula.getColor(n, abstractBox);
    }
    
    public final Component getComponent() {
        return null;
    }
    
    public final Font getDefaultFont(final int n, final AbstractBox abstractBox) {
        final Font defaultFont = this.formula.getDefaultFont(n, abstractBox);
        return new Font(defaultFont.getName(), defaultFont.getStyle(), Math.round(this.dpi / this.formula.getDPI() * defaultFont.getSize()));
    }
    
    public final int getDPI() {
        if (this.dpi <= 0) {
            return this.formula.getDPI();
        }
        return this.dpi;
    }
    
    public final FontMetrics getFontMetrics(final Font font) {
        return this.printGraphics.getFontMetrics(font);
    }
    
    public final FontUtils getFontUtils() {
        return this.formula.getFontUtils();
    }
    
    public final Point getOrigin() {
        return new Point();
    }
    
    public final BoxComponent[] getPointer() {
        return new BoxComponent[] { this };
    }
    
    public final boolean isDesign() {
        return this.formula.isDesign();
    }
    
    public final AbstractBox parse(final String s) {
        return this.formula.parse(s);
    }
    
    public final void repaint() {
    }
    
    public final String script(final AbstractBox abstractBox) {
        return this.formula.script(abstractBox);
    }
    
    public final void setCaret(final BoxPosition boxPosition) {
    }
    
    public final void setColor(final Graphics graphics, final Color color) {
        this.formula.setColor(graphics, color);
    }
    
    public final void setCursor(final Cursor cursor) {
    }
    
    public final ResourceProvider getResourceProvider() {
        return this.formula.getResourceProvider();
    }
    
    public final boolean getMathRTL() {
        return this.formula.getMathRTL();
    }
    
    public final boolean isDisplayStyle() {
        return true;
    }
    
    static {
        footerFont = new Font("SansSerif", 0, 10);
        footerColor = Color.gray;
    }
}

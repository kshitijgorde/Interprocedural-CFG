import java.awt.Rectangle;
import java.awt.image.ImageObserver;
import java.awt.Cursor;
import java.awt.Container;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Insets;
import java.awt.Graphics;
import java.awt.Image;

// 
// Decompiled by Procyon v0.5.30
// 

public class FormulaPrintPreview extends WBackPanel
{
    private Formula formula;
    private PrintableFormula printableFormula;
    private float zoom;
    private int currentPage;
    private Image offImage;
    private Graphics offGraphics;
    private Length[] printableArea;
    private Length[] pageMargins;
    private int pageNumber;
    private static final Insets padding;
    private static final Color backColor;
    private static final Color pageColor;
    private static final Color pageLimitColor;
    private static final Color contentLimitColor;
    private Dimension preferredSize;
    private FormulaPrintManager printManager;
    
    public FormulaPrintPreview(final Formula contentFrom, final FormulaPrintManager printManager) {
        this.printableFormula = null;
        this.zoom = 0.75f;
        this.currentPage = 0;
        this.printableArea = FormulaPrintManager.Z;
        this.pageMargins = FormulaPrintManager.C;
        this.pageNumber = 0;
        this.preferredSize = new Dimension(100, 100);
        this.printManager = printManager;
        this.formula = new Formula(null, null);
        this.setBackground(FormulaPrintPreview.backColor);
        this.setContentFrom(contentFrom);
    }
    
    public final void setContentFrom(final Formula formula) {
        if (formula.lacapsa != null) {
            this.formula.colors = formula.colors.clone();
            this.formula.setFontUtils(formula.getFontUtils());
            this.formula.setString(formula.script(formula.lacapsa), true);
            this.formula.lacapsa.recommendedWidth = this.printableArea[0];
            this.formula.setDesign(formula.isDesign());
            this.update();
            this.setCurrentPage(this.currentPage);
        }
    }
    
    public final void update() {
        this.printableFormula = this.printManager.createPreviewFormula(this.formula);
        if (this.printableFormula != null) {
            this.pageNumber = this.printableFormula.getPageNumber();
        }
        else {
            this.pageNumber = 0;
        }
        if (this.currentPage >= this.pageNumber) {
            this.currentPage = this.pageNumber - 1;
        }
        final Length[] printableArea = this.printManager.getPrintableArea();
        final Length[] margins = this.printManager.getMargins();
        this.printableArea = printableArea;
        this.pageMargins = margins;
        this.clearOffImage();
        this.invalidate();
        this.validate();
        final Container parent = this.getParent();
        if (parent != null) {
            parent.validate();
        }
        this.repaint();
    }
    
    public final boolean print() {
        return this.printManager.print(this.formula);
    }
    
    public final void setCurrentPage(int currentPage) {
        if (currentPage < 0) {
            currentPage = 0;
        }
        else if (currentPage >= this.pageNumber) {
            currentPage = this.pageNumber - 1;
        }
        if (this.currentPage != currentPage) {
            this.currentPage = currentPage;
            this.clearOffImage();
            this.repaint();
        }
    }
    
    public final int getCurrentPage() {
        return this.currentPage;
    }
    
    public final int getPageNumber() {
        return this.pageNumber;
    }
    
    public final void setZoom(float zoom) {
        if (zoom <= 0.0f) {
            zoom = 1.0f;
        }
        this.zoom = zoom;
        this.update();
    }
    
    public final void setFontSize(final int fontSize) {
        this.formula.setFontSize(fontSize);
        this.update();
    }
    
    public final int getFontSize() {
        return this.formula.getDefaultFont(4, null).getSize();
    }
    
    public final void validate() {
        final boolean valid = this.isValid();
        super.validate();
        if (!valid) {
            final Dimension preferredSize = new Dimension();
            preferredSize.width = this.pageMargins[1].toUserUnit() + FormulaPrintPreview.padding.left + this.pageMargins[3].toUserUnit() + FormulaPrintPreview.padding.right + this.printableArea[0].toUserUnit();
            preferredSize.height = this.pageMargins[0].toUserUnit() + FormulaPrintPreview.padding.top + this.pageMargins[2].toUserUnit() + FormulaPrintPreview.padding.bottom + this.printableArea[1].toUserUnit();
            preferredSize.width = Math.round(this.zoom * preferredSize.width);
            preferredSize.height = Math.round(this.zoom * preferredSize.height);
            this.preferredSize = preferredSize;
        }
    }
    
    private void clearOffImage() {
        this.offImage = null;
        if (this.offGraphics != null) {
            this.offGraphics.dispose();
            this.offGraphics = null;
        }
    }
    
    public final Dimension getPreferredSize() {
        if (!this.isValid()) {
            this.validate();
        }
        return this.preferredSize;
    }
    
    public final void paint(final Graphics graphics) {
        super.paint(graphics);
        final Dimension size = this.getSize();
        if (this.offImage == null) {
            this.setCursor(Formula.WAIT_CURSOR);
            this.offImage = this.createImage(this.preferredSize.width, this.preferredSize.height);
            this.doPaint(this.offGraphics = this.offImage.getGraphics());
            this.setCursor(null);
        }
        graphics.drawImage(this.offImage, FormulaPrintPreview.padding.left + (size.width - this.preferredSize.width) / 2, FormulaPrintPreview.padding.top + (size.height - this.preferredSize.height) / 2, null);
    }
    
    private void doPaint(final Graphics graphics) {
        final Dimension preferredSize = this.preferredSize;
        graphics.setColor(this.getBackground());
        graphics.fillRect(0, 0, preferredSize.width, preferredSize.height);
        if (this.currentPage < 0 || this.currentPage >= this.pageNumber || this.printableFormula == null) {
            return;
        }
        final Insets insets = new Insets(this.pageMargins[0].toUserUnit(), this.pageMargins[1].toUserUnit(), this.pageMargins[2].toUserUnit(), this.pageMargins[3].toUserUnit());
        final Dimension dimension = new Dimension(this.printableArea[0].toUserUnit(), this.printableArea[1].toUserUnit());
        final Image image = this.createImage(dimension.width, dimension.height);
        insets.top = Math.round(this.zoom * insets.top);
        insets.bottom = Math.round(this.zoom * insets.bottom);
        insets.left = Math.round(this.zoom * insets.left);
        insets.right = Math.round(this.zoom * insets.right);
        dimension.width = Math.round(this.zoom * dimension.width);
        dimension.height = Math.round(this.zoom * dimension.height);
        final Rectangle rectangle = new Rectangle(0, 0, insets.left + insets.right + dimension.width, insets.top + insets.bottom + dimension.height);
        final int n = rectangle.x + insets.left;
        final int n2 = rectangle.y + insets.top;
        graphics.setColor(FormulaPrintPreview.pageColor);
        graphics.fillRect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
        graphics.setColor(FormulaPrintPreview.pageLimitColor);
        graphics.drawRect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
        final Graphics graphics2 = image.getGraphics();
        IsGraphics2D.setAntialiasing(graphics2, true, 4);
        graphics2.setColor(FormulaPrintPreview.pageColor);
        graphics2.fillRect(0, 0, image.getWidth(null), image.getHeight(null));
        this.printableFormula.print(graphics2, this.currentPage);
        final Image scaledInstance = image.getScaledInstance(dimension.width, dimension.height, 4);
        graphics2.dispose();
        graphics.drawImage(scaledInstance, n, n2, null);
        graphics.setColor(FormulaPrintPreview.contentLimitColor);
        graphics.drawRect(n, n2, dimension.width, dimension.height);
    }
    
    static {
        padding = new Insets(16, 16, 16, 16);
        backColor = new Color(140, 140, 140);
        pageColor = Color.white;
        pageLimitColor = Color.black;
        contentLimitColor = new Color(220, 220, 220);
    }
}

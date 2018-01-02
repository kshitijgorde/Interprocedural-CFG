import java.awt.event.ActionListener;
import java.util.Hashtable;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

// 
// Decompiled by Procyon v0.5.30
// 

public class MatrixBox extends AbstractMatrixBox
{
    public char tipus;
    public char tipusd;
    int AmpladaParentesis;
    int addMenuItem;
    int append;
    int appendScript;
    
    public MatrixBox() {
    }
    
    public MatrixBox(final int n, final int n2) {
        super(n, n2);
    }
    
    public final void inicialitzaModalitat(final Object o) {
        final String s = (String)o;
        this.tipus = s.charAt(0);
        if (s.length() > 1) {
            this.tipusd = s.charAt(1);
        }
        else {
            this.tipusd = this.tipus;
        }
    }
    
    public final void enCalculRect(final BoxComponent boxComponent) {
        if (super.columnes == 0 || super.files == 0) {
            return;
        }
        this.calculateMaxDimensions(boxComponent.getDPI());
        if (super.C.length() != 0) {
            this.AmpladaParentesis = Attributes.strToDim(this, super.C, boxComponent.getDPI());
            this.append = this.AmpladaParentesis;
            this.addMenuItem = 0;
            if (this.tipus != 'X') {
                this.addMenuItem = this.em(CapsaParentesis.AmpladaParentesis(this, this.tipus)) + this.AmpladaParentesis;
            }
            this.addMenuItem += this.AmpladaParentesis / 2;
            this.appendScript = this.append / 2;
        }
        else {
            this.AmpladaParentesis = this.em(0.25f);
            this.append = this.em(0.0f);
            if (this.tipus != 'X') {
                this.addMenuItem = this.em(CapsaParentesis.AmpladaParentesis(this, this.tipus)) + this.em(0.2f);
            }
            else {
                this.addMenuItem = this.AmpladaParentesis;
            }
            if (this.tipus == 'X' || super.A > 3 * this.em(0.25f)) {
                this.AmpladaParentesis *= 2;
            }
            this.appendScript = this.append / 2;
        }
        this.calculateCellPositions(boxComponent);
    }
    
    private Rectangle getParenthesisBounds() {
        final Rectangle rectangle = new Rectangle();
        rectangle.height = super.height;
        if (super.height > this.em()) {
            final int n = (int)Math.max(Math.min(super.height - this.em(), 3.0f * this.em() / 19.0f), 0.0f);
            final Rectangle rectangle2 = rectangle;
            rectangle2.y += n;
            final Rectangle rectangle3 = rectangle;
            rectangle3.height -= 2 * n;
        }
        rectangle.width = this.em(CapsaParentesis.AmpladaParentesis(this, this.tipus));
        return rectangle;
    }
    
    protected final void onPaint(final BoxComponent boxComponent, final Graphics graphics) {
        final Rectangle parenthesisBounds = this.getParenthesisBounds();
        parenthesisBounds.x = this.C();
        CapsaParentesis.dibuixaParentesis(this.tipus, super.I, super.EM, super.baseline, false, parenthesisBounds, graphics);
        parenthesisBounds.x = super.width - parenthesisBounds.width - parenthesisBounds.x;
        CapsaParentesis.dibuixaParentesis(this.tipusd, super.I, super.EM, super.baseline, true, parenthesisBounds, graphics);
        if (this.tipus == 'X' && (super.F || super.D || boxComponent.isDesign())) {
            final Color color = graphics.getColor();
            final int c = this.C();
            final int z = this.Z();
            final Rectangle rectangle = new Rectangle(c, z, super.width - 2 * c, super.height - 2 * z);
            if (super.F) {
                graphics.setColor(super.S);
                graphics.fillRect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
            }
            final boolean b = !super.D && boxComponent.isDesign();
            if (super.D || b) {
                Color color2;
                if (b) {
                    color2 = Color.lightGray;
                }
                else {
                    color2 = super.J;
                }
                graphics.setColor(color2);
                int x = rectangle.x;
                int y = rectangle.y;
                for (int i = 0; i < super.columnes; ++i) {
                    if (b) {
                        GraphicsUtils.drawDashLine(graphics, x, rectangle.y, rectangle.height, true, 2, 4);
                    }
                    else {
                        graphics.drawLine(x, rectangle.y, x, rectangle.y + rectangle.height - 1);
                    }
                    x = x + super.E[i] + this.AmpladaParentesis;
                }
                for (int j = 0; j < super.files; ++j) {
                    if (b) {
                        GraphicsUtils.drawDashLine(graphics, rectangle.x, y, rectangle.width, false, 2, 4);
                    }
                    else {
                        graphics.drawLine(rectangle.x, y, rectangle.x + rectangle.width - 1, y);
                    }
                    y = y + super.G[j] + this.append;
                }
                if (b) {
                    GraphicsUtils.drawDashBox(graphics, 2, 4, rectangle);
                }
                else {
                    graphics.drawRect(rectangle.x, rectangle.y, rectangle.width - 1, rectangle.height - 1);
                }
            }
            graphics.setColor(color);
        }
    }
    
    private final void AfegirNoCalc(final BoxScripting boxScripting, final int n, final int n2, final int n3, final int n4) {
        if (super.columnes * super.files > super.nfills) {
            return;
        }
        if (this.tipus == 'X') {
            boxScripting.appendScript("\\begintable ");
        }
        else {
            boxScripting.appendScript("\\begin" + this.tipus + "matrix ");
        }
        for (int i = n2; i < n4; ++i) {
            if (i > n2) {
                boxScripting.appendScript("\\cr ");
            }
            for (int j = n; j < n3; ++j) {
                if (j > n) {
                    boxScripting.appendScript("&");
                }
                super.fill[j + i * super.columnes].script(boxScripting);
            }
        }
        if (this.tipus == 'X') {
            boxScripting.appendScript("\\endtable ");
        }
        else {
            boxScripting.appendScript("\\end" + this.tipus + "matrix ");
        }
    }
    
    private final void AmpladaParentesis(final BoxScripting boxScripting, final int n, final int n2, final int n3, final int n4) {
        if (this.tipus == 'v') {
            boxScripting.appendText("determinant(");
        }
        if (this.tipus == 'V') {
            boxScripting.appendText("norm(");
        }
        boxScripting.appendText("[");
        for (int i = n2; i < n4; ++i) {
            if (i > n2) {
                boxScripting.appendText(",");
            }
            boxScripting.appendText("[");
            for (int j = n; j < n3; ++j) {
                if (j > n) {
                    boxScripting.appendText(",");
                }
                super.fill[j + i * super.columnes].script(boxScripting);
            }
            boxScripting.appendText("]");
        }
        boxScripting.appendText("]");
        if (this.tipus == 'v') {
            boxScripting.appendText(")");
        }
        if (this.tipus == 'V') {
            boxScripting.appendText(")");
        }
    }
    
    public static final MatrixBox NovaCapsa(final int n, final int n2, final char c) {
        final MatrixBox matrixBox = new MatrixBox(n, n2);
        matrixBox.inicialitzaModalitat("" + c);
        for (int i = 0; i < n2; ++i) {
            for (int j = 0; j < n; ++j) {
                matrixBox.AfegirNoCalc(new EmptyBox());
            }
        }
        return matrixBox;
    }
    
    protected final int Z() {
        if (super.B.length() == 0) {
            return this.em(0.09f);
        }
        return super.Z();
    }
    
    protected final int C() {
        if (super.B.length() == 0) {
            return this.em(0.2f);
        }
        return super.C();
    }
    
    protected final int I(final BoxComponent boxComponent, final int n) {
        if (n == 0 || n == super.columnes) {
            return this.addMenuItem;
        }
        return this.AmpladaParentesis;
    }
    
    protected final int Z(final BoxComponent boxComponent, final int n) {
        if (n == 0 || n == super.files) {
            return this.appendScript;
        }
        return this.append;
    }
    
    public final boolean hasMenuProperties() {
        return true;
    }
    
    public final boolean fbvisible(final int n) {
        return this.tipus != 'X';
    }
    
    public final void exchangeProperties(final Hashtable hashtable, final int n) {
        if (this.tipus != 'X') {
            return;
        }
        super.exchangeProperties(hashtable, n);
        super.D = Attributes.exchangeBool(hashtable, "grid", n, super.D, false);
        super.C = Attributes.exchangeString(hashtable, "margin", n, super.C, "");
        super.B = Attributes.exchangeString(hashtable, "exteriormargin", n, super.B, "");
        super.J = Attributes.exchangeColor(hashtable, "gridcolor", n, super.J, Color.black);
        super.F = Attributes.exchangeBool(hashtable, "fill", n, super.F, false);
        super.S = Attributes.exchangeColor(hashtable, "fillcolor", n, super.S, Color.lightGray);
        super.autoincrement = Attributes.exchangeBool(hashtable, "autoincrement", n, super.autoincrement, false);
        super.rowcol = Attributes.exchangeBool(hashtable, "rowcol", n, super.rowcol, true);
        super.toCopy = Attributes.exchangeInt(hashtable, "copylast", n, super.toCopy, 0);
    }
    
    protected final void I(final BoxScripting boxScripting, final int n, final int n2, final int n3, final int n4) {
        if (this.tipus != 'X') {
            boxScripting.openTag("mtable");
        }
        super.I(boxScripting, n, n2, n3, n4);
        if (this.tipus != 'X') {
            boxScripting.closeTag();
        }
    }
    
    public final void submatrix(final BoxScripting boxScripting, final int n, final int n2, final int n3, final int n4) {
        if (boxScripting.scriptMode == 1) {
            this.I(boxScripting, n, n2, n3, n4);
        }
        else if (boxScripting.scriptMode == 0) {
            this.AfegirNoCalc(boxScripting, n, n2, n3, n4);
        }
        else if (boxScripting.scriptMode == 2) {
            this.AmpladaParentesis(boxScripting, n, n2, n3, n4);
        }
    }
    
    public final String scriptCommand(final BoxScripting boxScripting) {
        if (boxScripting.scriptMode != 1) {
            return null;
        }
        if (this.tipus != 'X') {
            return "mfenced";
        }
        return "wtable";
    }
    
    public final void attributes(final BoxScripting boxScripting) {
        if (this.tipus != 'X') {
            CapsaParentesis.I(boxScripting, this.tipus, this.tipusd);
        }
        super.attributes(boxScripting);
    }
    
    public final void buildContextualMenu(final FormulaEditor formulaEditor) {
        if (super.row_minoccur < super.row_maxoccur) {
            final boolean b = super.files < super.row_maxoccur;
            formulaEditor.addMenuItem("Add row above", "addrowup", formulaEditor, b);
            formulaEditor.addMenuItem("Add row below", "addrowdown", formulaEditor, b);
            formulaEditor.addMenuItem("Erase row", "removerow", formulaEditor, super.files > 1 && super.files > super.row_minoccur);
        }
        if (super.column_minoccur < super.column_maxoccur) {
            final boolean b2 = super.columnes < super.column_maxoccur;
            formulaEditor.addMenuItem("Add left column", "addcolumnleft", formulaEditor, b2);
            formulaEditor.addMenuItem("Add right column", "addcolumnright", formulaEditor, b2);
            formulaEditor.addMenuItem("Erase column", "removecolumn", formulaEditor, super.columnes > 1 && super.columnes > super.column_minoccur);
        }
    }
    
    public final int getSplitFactor(final int n) {
        if (this.tipus == 'X') {
            return super.getSplitFactor(n);
        }
        final Rectangle parenthesisBounds = this.getParenthesisBounds();
        if (n >= parenthesisBounds.y && n < super.Z[0] + super.G[0]) {
            return 610;
        }
        if (n < parenthesisBounds.y + parenthesisBounds.height && n >= super.Z[super.files - 1]) {
            return 610;
        }
        return super.getSplitFactor(n);
    }
}

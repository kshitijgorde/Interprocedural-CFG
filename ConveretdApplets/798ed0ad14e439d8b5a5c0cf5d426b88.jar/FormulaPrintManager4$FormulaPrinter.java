import java.awt.RenderingHints;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.Graphics;
import java.awt.print.Printable;

// 
// Decompiled by Procyon v0.5.30
// 

class FormulaPrintManager4$FormulaPrinter implements Printable
{
    private PrintableFormula pformula;
    private boolean init;
    private final boolean onlyInit;
    private final FormulaPrintManager4 this$0;
    
    private FormulaPrintManager4$FormulaPrinter(final FormulaPrintManager4 this$0, final Formula formula, final boolean onlyInit) {
        this.this$0 = this$0;
        this.init = false;
        this.onlyInit = onlyInit;
        this.pformula = new PrintableFormula(formula);
    }
    
    public final int print(final Graphics graphics, final PageFormat pageFormat, final int n) {
        FormulaPrintManager4.I(this.this$0, graphics);
        FormulaPrintManager4.I(this.this$0, pageFormat);
        if (!this.init) {
            final Length[] i = FormulaPrintManager4.I(pageFormat);
            ((Graphics2D)graphics).setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
            this.pformula.init(graphics, i[0], i[1], 72);
            this.init = true;
        }
        if (this.onlyInit) {
            return 1;
        }
        graphics.translate((int)pageFormat.getImageableX(), (int)pageFormat.getImageableY());
        if (this.pformula.print(graphics, n)) {
            return 0;
        }
        return 1;
    }
    
    FormulaPrintManager4$FormulaPrinter(final FormulaPrintManager4 formulaPrintManager4, final Formula formula, final boolean b, final FormulaPrintManager4$1 formulaPrintManager4$1) {
        this(formulaPrintManager4, formula, b);
    }
    
    static final PrintableFormula I(final FormulaPrintManager4$FormulaPrinter formulaPrintManager4$FormulaPrinter) {
        return formulaPrintManager4$FormulaPrinter.pformula;
    }
}

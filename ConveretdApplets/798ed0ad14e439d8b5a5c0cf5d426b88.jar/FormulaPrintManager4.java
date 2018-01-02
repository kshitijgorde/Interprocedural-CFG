import javax.print.attribute.Attribute;
import javax.print.attribute.standard.MediaSizeName;
import javax.print.attribute.HashPrintRequestAttributeSet;
import java.awt.print.PrinterException;
import java.awt.print.Printable;
import javax.swing.UIManager;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.print.PageFormat;
import java.awt.print.PrinterJob;
import javax.print.attribute.PrintRequestAttributeSet;

// 
// Decompiled by Procyon v0.5.30
// 

final class FormulaPrintManager4 extends FormulaPrintManager
{
    private static PrintRequestAttributeSet currentPrintAttr;
    private PrinterJob printerJob;
    private PageFormat lastPageFormat;
    private Graphics previewGraphics;
    
    public FormulaPrintManager4(final Frame frame) {
        super(frame);
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch (Exception ex) {}
        this.printerJob = PrinterJob.getPrinterJob();
        this.lastPageFormat = null;
    }
    
    public final boolean showPrintDialog() {
        return this.printerJob.printDialog(FormulaPrintManager4.currentPrintAttr);
    }
    
    public final boolean print(final Formula formula) {
        if (formula == null) {
            throw new NullPointerException();
        }
        try {
            this.printerJob.setPrintable(new FormulaPrintManager4$FormulaPrinter(this, formula, false, null));
            this.printerJob.print(FormulaPrintManager4.currentPrintAttr);
            return true;
        }
        catch (PrinterException ex) {
            System.err.println("Printing error: " + ex);
            return false;
        }
    }
    
    public final boolean showPageDialog() {
        final PageFormat pageDialog = this.printerJob.pageDialog(FormulaPrintManager4.currentPrintAttr);
        if (pageDialog != null) {
            this.lastPageFormat = pageDialog;
            return true;
        }
        return false;
    }
    
    public final Length[] getPrintableArea() {
        final PageFormat lastPageFormat = this.lastPageFormat;
        if (lastPageFormat != null) {
            return getPrintableArea(lastPageFormat);
        }
        return FormulaPrintManager.Z.clone();
    }
    
    public final Length[] getMargins() {
        final PageFormat lastPageFormat = this.lastPageFormat;
        if (lastPageFormat != null) {
            return new Length[] { new Length(lastPageFormat.getImageableY(), 0), new Length(lastPageFormat.getImageableX(), 0), new Length(lastPageFormat.getHeight() - lastPageFormat.getImageableHeight() - lastPageFormat.getImageableY(), 0), new Length(lastPageFormat.getWidth() - lastPageFormat.getImageableWidth() - lastPageFormat.getImageableX(), 0) };
        }
        return FormulaPrintManager.C.clone();
    }
    
    private static Length[] getPrintableArea(final PageFormat pageFormat) {
        return new Length[] { new Length(pageFormat.getImageableWidth(), 0), new Length(pageFormat.getImageableHeight(), 0) };
    }
    
    public final PrintableFormula createPreviewFormula(final Formula formula) {
        final FormulaPrintManager4$FormulaPrinter printable = new FormulaPrintManager4$FormulaPrinter(this, formula, true, null);
        if (this.previewGraphics == null) {
            try {
                this.printerJob.setPrintable(printable);
                this.printerJob.print(FormulaPrintManager4.currentPrintAttr);
                return FormulaPrintManager4$FormulaPrinter.I(printable);
            }
            catch (PrinterException ex) {
                final PrintableFormula printableFormula = new PrintableFormula(formula);
                final Graphics graphics = super.I.getGraphics();
                final Length[] printableArea = this.getPrintableArea();
                printableFormula.init(graphics, printableArea[0], printableArea[1], 72);
                graphics.dispose();
                return printableFormula;
            }
        }
        printable.print(this.previewGraphics, this.lastPageFormat, 0);
        return FormulaPrintManager4$FormulaPrinter.I(printable);
    }
    
    static final Graphics I(final FormulaPrintManager4 formulaPrintManager4, final Graphics previewGraphics) {
        return formulaPrintManager4.previewGraphics = previewGraphics;
    }
    
    static final PageFormat I(final FormulaPrintManager4 formulaPrintManager4, final PageFormat lastPageFormat) {
        return formulaPrintManager4.lastPageFormat = lastPageFormat;
    }
    
    static final Length[] I(final PageFormat pageFormat) {
        return getPrintableArea(pageFormat);
    }
    
    static {
        (FormulaPrintManager4.currentPrintAttr = new HashPrintRequestAttributeSet()).add(MediaSizeName.ISO_A4);
    }
}

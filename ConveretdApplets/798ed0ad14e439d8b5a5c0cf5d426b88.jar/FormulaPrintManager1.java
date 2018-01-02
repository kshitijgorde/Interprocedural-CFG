import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.FocusEvent;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Frame;
import java.awt.TextField;
import java.awt.PrintJob;
import java.util.Properties;
import java.awt.event.FocusListener;

// 
// Decompiled by Procyon v0.5.30
// 

final class FormulaPrintManager1 extends FormulaPrintManager implements FocusListener
{
    private static Properties printProperties;
    private PrintJob printJob;
    private Length[] pageArea;
    private Length[] inputMargins;
    private Length[] margins;
    private static final String[] marginsName;
    private MessageBox pageDialog;
    private TextField[] marginsField;
    
    private FormulaPrintManager1(final Frame frame) {
        super(frame);
        this.printJob = null;
        this.pageArea = null;
        this.margins = FormulaPrintManager.C;
        this.pageDialog = null;
    }
    
    public static final FormulaPrintManager1 createInstance(final Frame frame) {
        final FormulaPrintManager1 formulaPrintManager1 = new FormulaPrintManager1(frame);
        if (formulaPrintManager1.getPrintJob()) {
            return formulaPrintManager1;
        }
        return null;
    }
    
    public final boolean showPrintDialog() {
        return this.getPrintJob();
    }
    
    public final boolean print(final Formula formula) {
        if (formula == null) {
            throw new NullPointerException();
        }
        if (this.printJob == null && !this.getPrintJob()) {
            return false;
        }
        if (this.printJob != null) {
            final Length[] printableArea = this.getPrintableArea();
            final PrintableFormula printableFormula = new PrintableFormula(formula);
            final Graphics graphics = this.printJob.getGraphics();
            final int pageResolution = this.printJob.getPageResolution();
            printableFormula.init(graphics, printableArea[0], printableArea[1], pageResolution);
            System.out.println("Printing: " + printableArea[0] + 'x' + printableArea[1] + "  dpi:" + pageResolution);
            final int userUnit = this.margins[0].toUserUnit(16, pageResolution);
            final int userUnit2 = this.margins[1].toUserUnit(16, pageResolution);
            final int pageNumber = printableFormula.getPageNumber();
            int n;
            int n2;
            if (this.printJob.lastPageFirst()) {
                n = pageNumber - 1;
                n2 = -1;
            }
            else {
                n = 0;
                n2 = 1;
            }
            graphics.translate(userUnit2, userUnit);
            printableFormula.print(graphics, n);
            for (int i = 1; i < pageNumber; ++i) {
                final Graphics graphics2 = this.printJob.getGraphics();
                graphics2.translate(userUnit2, userUnit);
                printableFormula.print(graphics2, n + n2 * i);
            }
            this.printJob.end();
            this.printJob = null;
            return true;
        }
        return false;
    }
    
    public final boolean showPageDialog() {
        if (this.pageDialog == null) {
            final MessageBox pageDialog = new MessageBox(super.I, "Page Setup", 3);
            this.marginsField = new TextField[4];
            for (int i = 0; i < 4; ++i) {
                this.marginsField[i] = new TextField(this.margins[i].toString());
                pageDialog.add(FormulaPrintManager1.marginsName[i], this.marginsField[i]);
                this.marginsField[i].addFocusListener(this);
            }
            this.pageDialog = pageDialog;
        }
        this.inputMargins = new Length[4];
        if (this.pageDialog.showDialog().equals("Ok")) {
            this.validateFields();
            this.margins = this.inputMargins;
            return true;
        }
        return false;
    }
    
    private boolean validateFields() {
        boolean b = true;
        for (int i = 0; i < 4; ++i) {
            final String text = this.marginsField[i].getText();
            Length length = null;
            try {
                length = new Length(text);
            }
            catch (IllegalArgumentException ex) {
                try {
                    length = new Length(text + "cm");
                }
                catch (IllegalArgumentException ex2) {}
            }
            if (length != null && length.getValue() >= 0.0 && length.getUnit() != 1) {
                this.inputMargins[i] = length;
            }
            else {
                b = false;
            }
        }
        return b;
    }
    
    public final void focusGained(final FocusEvent focusEvent) {
    }
    
    public final void focusLost(final FocusEvent focusEvent) {
        this.validateFields();
    }
    
    public final Length[] getPrintableArea() {
        Length[] array;
        if (this.pageArea != null) {
            array = new Length[] { new Length(this.pageArea[0].toUserUnit() - this.margins[1].toUserUnit() - this.margins[3].toUserUnit(), 0), new Length(this.pageArea[1].toUserUnit() - this.margins[0].toUserUnit() - this.margins[1].toUserUnit(), 0) };
        }
        else {
            array = FormulaPrintManager.Z.clone();
        }
        return array;
    }
    
    public final PrintableFormula createPreviewFormula(final Formula formula) {
        final PrintableFormula printableFormula = new PrintableFormula(formula);
        final Graphics graphics = super.I.getGraphics();
        final Length[] printableArea = this.getPrintableArea();
        printableFormula.init(graphics, printableArea[0], printableArea[1], this.printJob.getPageResolution());
        graphics.dispose();
        return printableFormula;
    }
    
    private boolean getPrintJob() {
        final PrintJob printJob = Toolkit.getDefaultToolkit().getPrintJob(super.I, "Wiris", FormulaPrintManager1.printProperties);
        if (printJob != null) {
            this.printJob = printJob;
            final Dimension pageDimension = this.printJob.getPageDimension();
            final int pageResolution = this.printJob.getPageResolution();
            this.pageArea = new Length[] { Length.create(pageDimension.width, pageResolution), Length.create(pageDimension.height, pageResolution) };
            return true;
        }
        return false;
    }
    
    public final Length[] getMargins() {
        return this.margins.clone();
    }
    
    static {
        FormulaPrintManager1.printProperties = new Properties();
        marginsName = new String[] { "Top margin", "Left margin", "Bottom margin", "Right margin" };
    }
}

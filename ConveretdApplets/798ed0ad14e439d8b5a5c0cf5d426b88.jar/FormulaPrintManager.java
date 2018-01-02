import java.awt.Frame;

// 
// Decompiled by Procyon v0.5.30
// 

public abstract class FormulaPrintManager
{
    protected Frame I;
    static final Length[] Z;
    static final Length[] C;
    
    protected FormulaPrintManager(final Frame i) {
        this.I = i;
    }
    
    public static final FormulaPrintManager newInstance(final Frame frame) {
        if (OmegaSystem.versio_java >= 3) {
            return new FormulaPrintManager4(frame);
        }
        return FormulaPrintManager1.createInstance(frame);
    }
    
    public abstract boolean showPageDialog();
    
    public abstract boolean showPrintDialog();
    
    public abstract boolean print(final Formula p0);
    
    public abstract Length[] getPrintableArea();
    
    public abstract Length[] getMargins();
    
    public abstract PrintableFormula createPreviewFormula(final Formula p0);
    
    static {
        Z = new Length[] { new Length("159.2mm"), new Length("246.2mm") };
        C = new Length[] { new Length("25.4mm"), new Length("25.4mm"), new Length("25.4mm"), new Length("25.4mm") };
    }
}

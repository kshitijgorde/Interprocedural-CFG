// 
// Decompiled by Procyon v0.5.30
// 

public class FormulaTransferJ1_1
{
    Object I;
    Formula copy;
    
    public FormulaTransferJ1_1(final Formula copy) {
        this.copy = copy;
    }
    
    public final void setFormulaTransfer(Object i) {
        if (OmegaSystem.versio_java >= 1) {
            if (this.I != null) {
                ((FormulaTransfer)this.I).I();
            }
            if (this.copy.H != null) {
                if (i == null) {
                    i = new FormulaTransfer();
                }
                this.I = i;
                ((FormulaTransfer)this.I).I(this.copy);
            }
        }
    }
    
    public final void unregister() {
        if (OmegaSystem.versio_java >= 1) {
            ((FormulaTransfer)this.I).I();
        }
    }
    
    public final boolean cut() {
        return OmegaSystem.versio_java >= 1 && ((FormulaTransfer)this.I).cut();
    }
    
    public final boolean copy() {
        return OmegaSystem.versio_java >= 1 && ((FormulaTransfer)this.I).copy();
    }
    
    public final boolean copyText(final int n) {
        return OmegaSystem.versio_java >= 1 && ((FormulaTransfer)this.I).copyText(n);
    }
    
    public final boolean paste() {
        return OmegaSystem.versio_java >= 1 && ((FormulaTransfer)this.I).paste();
    }
}

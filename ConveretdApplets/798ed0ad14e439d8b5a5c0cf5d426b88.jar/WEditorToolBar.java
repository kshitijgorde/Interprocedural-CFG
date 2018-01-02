import java.beans.PropertyChangeListener;

// 
// Decompiled by Procyon v0.5.30
// 

public final class WEditorToolBar extends WToolBar
{
    private Controller controller;
    private boolean listener;
    
    WEditorToolBar(final FormulaEditorCalc formulaEditorCalc, final FormulaToolBarFactory formulaToolBarFactory, final int n) {
        super(n);
        this.listener = false;
        this.setName("wirisToolbar");
        this.controller = new Controller(formulaEditorCalc, formulaToolBarFactory, this);
        this.setListener();
    }
    
    public final void addNotify() {
        super.addNotify();
        this.setListener();
    }
    
    public final void removeNotify() {
        this.removeListener();
        super.removeNotify();
    }
    
    public final Controller getController() {
        return this.controller;
    }
    
    private void setListener() {
        if (!this.listener) {
            this.controller.I.addPropertyChangeListener(this.controller);
            this.listener = true;
        }
    }
    
    private void removeListener() {
        if (this.listener) {
            this.controller.I.removePropertyChangeListener(this.controller);
            this.listener = false;
        }
    }
    
    public final void setFormulaEditor(final FormulaEditorCalc formulaEditor) {
        this.controller.setFormulaEditor(formulaEditor);
    }
}

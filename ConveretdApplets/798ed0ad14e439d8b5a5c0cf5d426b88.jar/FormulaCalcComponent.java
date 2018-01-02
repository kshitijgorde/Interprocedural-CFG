import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Container;
import java.awt.event.KeyEvent;
import java.awt.AWTEvent;

// 
// Decompiled by Procyon v0.5.30
// 

public class FormulaCalcComponent extends WPanel
{
    private Formula formula;
    
    public FormulaCalcComponent() {
        this.setName("formulaComponent");
    }
    
    public final void processEvent(final AWTEvent awtEvent) {
        try {
            synchronized (this) {
                if (this.formula instanceof FormulaEditorCalc) {
                    final FormulaEditorCalc formulaEditorCalc = (FormulaEditorCalc)this.formula;
                    try {
                        switch (awtEvent.getID()) {
                            case 2999: {
                                final OmegaAnswerEvent omegaAnswerEvent = (OmegaAnswerEvent)awtEvent;
                                formulaEditorCalc.I(omegaAnswerEvent.omega, omegaAnswerEvent.str);
                                return;
                            }
                            case 3000:
                            case 3233: {
                                ((Runnable)awtEvent).run();
                                return;
                            }
                        }
                    }
                    catch (Exception ex) {
                        ex.printStackTrace(OmegaSystem.err);
                    }
                }
                super.processEvent(awtEvent);
                if (awtEvent instanceof KeyEvent && awtEvent.getID() == 401) {
                    final KeyEvent keyEvent = (KeyEvent)awtEvent;
                    if (!keyEvent.isConsumed()) {
                        for (Container container = this.getParent(); container != null && !keyEvent.isConsumed(); container = container.getParent()) {
                            container.dispatchEvent(keyEvent);
                        }
                    }
                }
            }
        }
        finally {}
    }
    
    public final void setFormula(final Formula formula) {
        this.formula = formula;
    }
    
    public final Formula getFormula() {
        return this.formula;
    }
    
    public final void update(final Graphics graphics) {
        if (this.formula != null) {
            if (!this.loading()) {
                this.formula.update(graphics);
            }
            else {
                this.paintWirisLoading(graphics);
            }
        }
    }
    
    public final void paint(final Graphics graphics) {
        if (this.formula != null) {
            if (!this.loading()) {
                this.formula.paint(graphics);
            }
            else {
                this.paintWirisLoading(graphics);
            }
        }
    }
    
    public final void print(final Graphics graphics) {
        if (this.formula != null) {
            this.formula.print(graphics);
        }
    }
    
    public final Dimension getPreferredSize() {
        if (this.formula != null) {
            return this.formula.getPreferredSize();
        }
        return super.getPreferredSize();
    }
    
    public final boolean isFocusTraversable() {
        return true;
    }
    
    public final boolean loading() {
        return ((FormulaEditorCalc)this.formula).loading();
    }
    
    public final void paintWirisLoading(final Graphics graphics) {
        ((FormulaEditorCalc)this.formula).paintWirisLoading(graphics);
    }
}

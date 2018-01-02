import java.awt.TextArea;
import java.awt.Frame;
import java.util.Date;
import java.awt.AWTEvent;
import java.awt.Component;

// 
// Decompiled by Procyon v0.5.30
// 

public class Calculator extends Component
{
    private boolean calculating;
    boolean EXPAND;
    OmegaClient I;
    Component FALSE;
    FormulaEditorCalc J;
    int TRUE;
    boolean add;
    OmegaQueueEvaluation append;
    
    public Calculator() {
        try {
            this.I = OmegaClient.newOmega();
        }
        catch (OException ex) {
            ex.printStackTrace();
        }
        this.enableEvents(0L);
    }
    
    public final synchronized boolean isCalculating() {
        return this.calculating;
    }
    
    public final void setTimeout(final int true) {
        this.TRUE = true;
    }
    
    public final synchronized void evaluate(final FormulaEditorCalc j, final String[] array, final Component false, final boolean expand) {
        try {
            this.FALSE = false;
            this.EXPAND = expand;
            (this.J = j).firePropertyChange("preCalculate", Boolean.TRUE, Boolean.FALSE);
            if (this.add) {
                showKernel(array);
            }
            if (j.context != null) {
                this.I.setDocumentBase(j.context.getDocumentBase());
            }
            this.I.setLanguage("en");
            this.I.getGraphics().removeAllElements();
            (this.append = new OmegaQueueEvaluation()).setTimeout(this.TRUE);
            this.append.evaluate(this.I, array, FormulaEditorCalc.opcions(j), this);
            this.calculating = true;
        }
        catch (Exception ex) {
            ex.printStackTrace();
            this.calculating = false;
        }
    }
    
    public final synchronized void processEvent(final AWTEvent awtEvent) {
        if (awtEvent.getID() == 2999) {
            if (this.add) {
                showKernel(((OmegaAnswerEvent)awtEvent).str);
            }
            if (this.append.getError().equals("Cannot connect to the kernel.")) {}
            this.FALSE.dispatchEvent(awtEvent);
            this.J.firePropertyChange("calculated", Boolean.TRUE, Boolean.FALSE);
            if (this.J.backupCursor != null) {
                this.J.getComponent().setCursor(this.J.backupCursor);
            }
            this.J.igonreEventTime = new Date().getTime();
            this.calculating = false;
            this.FALSE = null;
            this.J = null;
            this.notify();
        }
    }
    
    public final void interrupt() {
        this.append.setError("Interrupted");
        if (this.append != null) {
            this.append.interrumpt();
        }
    }
    
    public final synchronized void waitTerminate() {
        if (!this.calculating) {
            return;
        }
        try {
            this.wait();
        }
        catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }
    
    public final void setSeed(final int seed) {
        this.I.setSeed(seed);
    }
    
    public final void setDebug(final boolean add) {
        this.add = add;
    }
    
    public static final void showKernel(final String[] array) {
        if (FormulaEditorCalc.showKernel) {
            final MessageBox messageBox = new MessageBox(null, "omega", 1);
            messageBox.add("Wiris commands send to kernel");
            final TextArea textArea = new TextArea("", 30, 60);
            messageBox.setResizable(true);
            messageBox.add(textArea, MessageBox.EXPAND);
            for (int i = 0; i < array.length; ++i) {
                textArea.append(array[i]);
                textArea.append("\n");
            }
            messageBox.show();
        }
    }
    
    public final boolean loading() {
        return this.I.loading();
    }
}

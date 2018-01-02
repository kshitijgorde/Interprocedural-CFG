// 
// Decompiled by Procyon v0.5.30
// 

package edu.hws.jcm.awt;

import java.awt.event.ItemEvent;
import java.awt.event.AdjustmentEvent;
import java.awt.event.TextEvent;
import java.awt.event.ActionEvent;
import java.util.Vector;
import java.awt.event.ItemListener;
import java.awt.event.TextListener;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentListener;
import java.io.Serializable;

public class Controller implements Serializable, Computable, InputObject, AdjustmentListener, ActionListener, TextListener, ItemListener
{
    protected Vector computables;
    protected Vector inputs;
    protected Vector ties;
    protected ErrorReporter errorReporter;
    protected Controller parent;
    protected String errorMessage;
    
    public void setErrorReporter(final ErrorReporter errorReporter) {
        this.errorReporter = errorReporter;
    }
    
    public ErrorReporter getErrorReporter() {
        return this.errorReporter;
    }
    
    public void add(final Object o) {
        if (o == null) {
            return;
        }
        if (o instanceof Controller) {
            final Controller controller = (Controller)o;
            if (controller.parent != null) {
                controller.parent.remove(this);
            }
            controller.parent = this;
        }
        if (o instanceof Computable) {
            if (this.computables == null) {
                this.computables = new Vector();
            }
            this.computables.addElement(o);
        }
        if (o instanceof InputObject) {
            if (this.inputs == null) {
                this.inputs = new Vector();
            }
            this.inputs.addElement(o);
        }
        if (o instanceof Tie) {
            if (this.ties == null) {
                this.ties = new Vector();
            }
            this.ties.addElement(o);
        }
    }
    
    public void remove(final Object o) {
        if (o == null) {
            return;
        }
        if (this.computables != null) {
            this.computables.removeElement(o);
            if (this.computables.size() == 0) {
                this.computables = null;
            }
        }
        if (this.inputs != null) {
            this.inputs.removeElement(o);
            if (this.inputs.size() == 0) {
                this.inputs = null;
            }
        }
        if (this.ties != null) {
            this.ties.removeElement(o);
            if (this.ties.size() == 0) {
                this.ties = null;
            }
        }
        if (o instanceof Controller && ((Controller)o).parent == this) {
            ((Controller)o).parent = null;
        }
    }
    
    public void removeFromParent() {
        if (this.parent != null) {
            this.parent.remove(this);
        }
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        this.compute();
    }
    
    public void textValueChanged(final TextEvent textEvent) {
        this.compute();
    }
    
    public void adjustmentValueChanged(final AdjustmentEvent adjustmentEvent) {
        this.compute();
    }
    
    public void itemStateChanged(final ItemEvent itemEvent) {
        this.compute();
    }
    
    public synchronized void compute() {
        try {
            this.checkInput();
            this.doTies();
            this.clearErrorMessage();
            this.doCompute();
        }
        catch (JCMError jcmError) {
            if (this.errorMessage == null || !this.errorMessage.equals(jcmError.getMessage())) {
                this.reportError(jcmError.getMessage());
            }
        }
        catch (RuntimeException ex) {
            this.reportError("Internal programmer's error detected?  " + ex);
            ex.printStackTrace();
        }
    }
    
    public void checkInput() {
        if (this.inputs != null) {
            for (int size = this.inputs.size(), i = 0; i < size; ++i) {
                ((InputObject)this.inputs.elementAt(i)).checkInput();
            }
        }
    }
    
    protected void doTies() {
        if (this.inputs != null) {
            for (int size = this.inputs.size(), i = 0; i < size; ++i) {
                if (this.inputs.elementAt(i) instanceof Controller) {
                    ((Controller)this.inputs.elementAt(i)).doTies();
                }
            }
        }
        if (this.ties != null) {
            for (int size2 = this.ties.size(), j = 0; j < size2; ++j) {
                ((Tie)this.ties.elementAt(j)).check();
            }
        }
    }
    
    protected void doCompute() {
        if (this.computables != null) {
            for (int size = this.computables.size(), i = 0; i < size; ++i) {
                final Object element = this.computables.elementAt(i);
                if (element instanceof Controller) {
                    ((Controller)element).doCompute();
                }
                else {
                    ((Controller)element).compute();
                }
            }
        }
    }
    
    public void reportError(final String s) {
        if (s == null) {
            this.clearErrorMessage();
        }
        if (this.errorReporter != null) {
            this.errorReporter.setErrorMessage(this, s);
            this.errorMessage = s;
        }
        else if (this.parent != null) {
            this.parent.reportError(this.errorMessage);
        }
        else {
            this.errorMessage = s;
            System.out.println("***** Error:  " + this.errorMessage);
        }
    }
    
    protected void clearErrorMessage() {
        if (this.errorReporter != null) {
            this.errorReporter.clearErrorMessage();
        }
        else if (this.parent != null) {
            this.parent.clearErrorMessage();
        }
        this.errorMessage = null;
    }
    
    public void errorCleared() {
        this.errorMessage = null;
    }
    
    public void notifyControllerOnChange(final Controller controller) {
        if (this.inputs != null) {
            for (int size = this.inputs.size(), i = 0; i < size; ++i) {
                ((InputObject)this.inputs.elementAt(i)).notifyControllerOnChange(controller);
            }
        }
    }
    
    public void gatherInputs() {
        this.notifyControllerOnChange(this);
    }
}

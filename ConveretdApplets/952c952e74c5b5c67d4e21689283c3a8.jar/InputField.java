import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.TextField;

// 
// Decompiled by Procyon v0.5.30
// 

public class InputField extends TextField
{
    private boolean waitingForInput;
    private String input;
    
    public InputField(final int n) {
        super(n);
        this.waitingForInput = false;
        this.input = "";
        this.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                InputField.this.setInputReady();
            }
        });
    }
    
    public synchronized String getInput() {
        this.setEditable(this.waitingForInput = true);
        this.setText("");
        this.requestFocus();
        while (this.waitingForInput) {
            try {
                this.wait();
            }
            catch (InterruptedException ex) {}
        }
        this.setEditable(this.waitingForInput = false);
        return this.input;
    }
    
    public synchronized void setInputReady() {
        this.input = this.getText();
        this.waitingForInput = false;
        this.notifyAll();
    }
    
    public boolean isWaiting() {
        return this.waitingForInput;
    }
}

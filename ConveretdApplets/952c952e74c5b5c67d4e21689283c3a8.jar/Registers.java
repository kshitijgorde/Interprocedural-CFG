import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Button;
import java.awt.Component;
import java.awt.Label;
import java.awt.LayoutManager;
import java.awt.GridLayout;
import java.awt.TextField;
import java.awt.Panel;

// 
// Decompiled by Procyon v0.5.30
// 

public class Registers extends Panel
{
    public static final boolean SHOW_MEMORY_REGISTERS = true;
    private int pc;
    private int mar;
    private int mdr;
    private int acc;
    private TextField pcDisplay;
    private TextField accDisplay;
    private TextField marDisplay;
    private TextField mdrDisplay;
    private InputField inbox;
    private TextField outbox;
    private ProducerConsumer counter;
    
    public Registers(final ProducerConsumer counter) {
        this.counter = counter;
        this.setLayout(new GridLayout(4, 4, 5, 5));
        this.setBackground(LittleMan.LIGHT_GREEN);
        (this.accDisplay = new TextField(4)).setEditable(false);
        (this.pcDisplay = new TextField(4)).setEditable(false);
        this.add(new Label("Accumulator: ", 2));
        this.add(this.accDisplay);
        this.add(new Label("Program Counter: ", 2));
        this.add(this.pcDisplay);
        (this.marDisplay = new TextField(4)).setEditable(false);
        (this.mdrDisplay = new TextField(4)).setEditable(false);
        this.add(new Label("MEM Address: ", 2));
        this.add(this.marDisplay);
        this.add(new Label("MEM Data: ", 2));
        this.add(this.mdrDisplay);
        (this.inbox = new InputField(4)).setEditable(false);
        (this.outbox = new TextField(4)).setEditable(false);
        this.add(new Label("In-Box: ", 2));
        this.add(this.inbox);
        this.add(new Label("Out-Box: ", 2));
        this.add(this.outbox);
        this.add(new Label(" "));
        final Button button = new Button("Enter");
        this.add(button);
        this.add(new Label(" "));
        this.add(new Label(" "));
        button.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                if (Registers.this.inbox.isWaiting()) {
                    Registers.this.counter.consumeAll();
                    Registers.this.inbox.setInputReady();
                }
            }
        });
        this.reset();
    }
    
    public InputField getInputField() {
        return this.inbox;
    }
    
    public TextField getOutputField() {
        return this.outbox;
    }
    
    public int getPC() {
        return this.pc;
    }
    
    public void setPC(final int pc) {
        this.pc = pc;
        this.pcDisplay.setText("" + this.pc);
    }
    
    public void incrementPC() {
        this.setPC(this.pc + 1);
    }
    
    public int getMAR() {
        return this.mar;
    }
    
    public void setMAR(final int mar) {
        this.mar = mar;
        this.marDisplay.setText("" + this.mar);
    }
    
    public int getMDR() {
        return this.mdr;
    }
    
    public void setMDR(final int mdr) {
        this.mdr = mdr;
        this.mdrDisplay.setText("" + this.mdr);
    }
    
    public void setACC(final int acc) {
        this.acc = acc;
        this.accDisplay.setText("" + this.acc);
    }
    
    public int getACC() {
        return this.acc;
    }
    
    public void reset() {
        this.setPC(0);
        this.setACC(0);
        this.setMDR(0);
        this.setMAR(0);
        this.inbox.setText("");
        this.outbox.setText("");
    }
}

import java.awt.event.ActionEvent;
import java.awt.FlowLayout;
import java.awt.Component;
import java.awt.Label;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.TextField;
import java.awt.Panel;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class LittleMan extends Applet implements ActionListener, Runnable
{
    public static final int MEMORY_WIDTH = 10;
    public static final int MEMORY_HEIGHT = 10;
    public static final int MEMORY_SIZE = 100;
    public static final Color LIGHT_GREEN;
    public static final Color LIGHT_PINK;
    private Panel buttonPanel;
    private Panel messagePanel;
    private Panel leftPanel;
    private Registers registers;
    private TextField accumulator;
    private TextField programCounter;
    private TextField outbox;
    private InputField inbox;
    private Button[] buttons;
    private Memory memory;
    private LittleManComputer processor;
    private LittleManCompiler compiler;
    private ProducerConsumer counter;
    private Thread processorThread;
    private Thread appletThread;
    private Thread handlerThread;
    private RunMode mode;
    private MessageBox messenger;
    
    public void init() {
        this.counter = new ProducerConsumer();
        this.messenger = new MessageBox();
        this.memory = new Memory(100, this.messenger);
        (this.mode = new RunMode()).setMode(-1);
        this.registers = new Registers(this.counter);
        this.compiler = new LittleManCompiler(this.memory, this.messenger);
        this.messenger.registerCompiler(this.compiler);
        this.processor = new LittleManComputer(this.memory, this.messenger, this.counter, this.mode, this.registers);
        this.processorThread = new Thread(this.processor);
        (this.leftPanel = new Panel()).setLayout(new BorderLayout());
        this.leftPanel.setBackground(LittleMan.LIGHT_PINK);
        this.leftPanel.add(new Label("Little Man Computer Memory:"), "North");
        this.leftPanel.add(this.memory, "West");
        final Panel panel = new Panel();
        panel.setLayout(new BorderLayout());
        panel.add(this.messenger, "Center");
        panel.add(this.registers, "South");
        (this.buttonPanel = new Panel()).setLayout(new FlowLayout());
        this.buttonPanel.setBackground(LittleMan.LIGHT_GREEN);
        (this.buttons = new Button[6])[0] = new Button("Clear");
        this.buttons[1] = new Button("Reset");
        this.buttons[2] = new Button("Run");
        this.buttons[3] = new Button("Slow");
        this.buttons[4] = new Button("Step");
        this.buttons[5] = new Button("Halt");
        for (int i = 0; i < 6; ++i) {
            this.buttons[i].addActionListener(this);
            this.buttonPanel.add(this.buttons[i]);
        }
        this.setLayout(new BorderLayout());
        this.setBackground(LittleMan.LIGHT_GREEN);
        this.add(this.leftPanel, "West");
        this.add(panel, "Center");
        this.add(this.buttonPanel, "South");
    }
    
    public void run() {
        if (this.appletThread == null) {
            (this.appletThread = new Thread(this)).start();
        }
    }
    
    private void resetRegisters() {
        this.registers.reset();
        this.memory.removeMemoryColors();
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        if (actionEvent.getSource() == this.buttons[0]) {
            this.clearAll();
            this.resetRegisters();
        }
        else if (actionEvent.getSource() == this.buttons[1]) {
            this.resetRegisters();
        }
        else if (actionEvent.getSource() == this.buttons[2]) {
            this.mode.setMode(2);
            this.doStep();
        }
        else if (actionEvent.getSource() == this.buttons[3]) {
            this.mode.setMode(1);
            this.doStep();
        }
        else if (actionEvent.getSource() == this.buttons[4]) {
            this.mode.setMode(0);
            this.doStep();
        }
        else if (actionEvent.getSource() == this.buttons[5]) {
            if (this.processor.isHalted()) {
                this.messenger.sendMessage("The computer is not running");
            }
            else {
                this.processor.halt();
                this.messenger.sendMessage("Execution terminated by user");
            }
        }
    }
    
    private void doStep() {
        if (!this.processorThread.isAlive()) {
            this.messenger.sendMessage("Restarting Processor");
            (this.processorThread = new Thread(this.processor)).start();
        }
        if (!this.processor.isWaitingForInput()) {
            this.counter.produce();
        }
    }
    
    private void clearAll() {
        this.registers.reset();
        this.messenger.clear();
        this.memory.clear();
    }
    
    static {
        LIGHT_GREEN = new Color(14745568);
        LIGHT_PINK = new Color(16769248);
    }
}

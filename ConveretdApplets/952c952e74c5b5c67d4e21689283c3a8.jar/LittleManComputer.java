import java.awt.Toolkit;
import java.awt.Color;
import java.awt.TextField;

// 
// Decompiled by Procyon v0.5.30
// 

public class LittleManComputer extends Thread
{
    public static final int SLOW_DELAY = 250;
    public static final int FAST_DELAY = 10;
    public static final int FLASH_DELAY = 20;
    private Memory memory;
    private int prior;
    private int input;
    private boolean waitingForInput;
    private Messenger messages;
    public static final int MAX_DATA_SIZE = 999;
    private boolean isHalted;
    private Registers registers;
    private TextField outputfield;
    private InputField inputfield;
    private ProducerConsumer counter;
    private boolean validProgramExists;
    private Thread runner;
    private RunMode mode;
    
    public LittleManComputer(final Memory memory, final Messenger messages, final ProducerConsumer counter, final RunMode mode, final Registers registers) {
        this.waitingForInput = false;
        this.memory = memory;
        this.messages = messages;
        this.counter = counter;
        this.mode = mode;
        this.registers = registers;
        this.isHalted = false;
        this.inputfield = this.registers.getInputField();
        this.outputfield = this.registers.getOutputField();
        this.registers.reset();
        this.validProgramExists = false;
    }
    
    public void run() {
        this.messages.sendMessage("Processor Starting");
        this.isHalted = false;
        while (!this.isHalted) {
            this.counter.consume();
            try {
                this.step();
            }
            catch (LittleManException ex) {
                this.messages.sendMessage(ex.toString());
            }
            if (this.mode.getMode() == 2) {
                try {
                    Thread.sleep(10L);
                }
                catch (InterruptedException ex2) {}
                this.counter.produce();
            }
            else if (this.mode.getMode() == 1) {
                try {
                    Thread.sleep(250L);
                }
                catch (InterruptedException ex3) {}
                this.counter.produce();
            }
            else {
                if (this.mode.getMode() != -1) {
                    continue;
                }
                this.isHalted = true;
            }
        }
        this.isHalted = true;
        this.messages.sendMessage("Processor Stopped");
    }
    
    public void step() throws LittleManException {
        if (this.isHalted) {
            this.messages.sendMessage("The Little Man Computer is Halted.Try \"Reset PC\"");
            this.counter.consumeAll();
        }
        else {
            try {
                this.memory.unSetAsCurrentInstruction(this.prior);
                this.memory.setAsCurrentInstruction(this.registers.getPC());
                final int fetch = this.fetch(this.registers.getPC());
                this.messages.sendMessage("\nPC = " + this.registers.getPC() + " : Instruction in Memory " + this.registers.getPC() + " is " + fetch);
                this.prior = this.registers.getPC();
                this.registers.incrementPC();
                this.execute(fetch);
            }
            catch (LittleManException ex) {
                this.message(ex.getMessage());
            }
        }
    }
    
    public void halt() {
        this.isHalted = true;
        this.messages.sendMessage("--> Execution Stopped");
        this.inputfield.setInputReady();
        this.mode.setMode(-1);
    }
    
    public boolean isWaitingForInput() {
        return this.inputfield.isWaiting();
    }
    
    public boolean isHalted() {
        return this.isHalted;
    }
    
    private void execute(final int n) throws LittleManException {
        final int op = this.getOp(n);
        final int address = this.getAddress(n);
        String s;
        if (address < 10) {
            s = "0" + address;
        }
        else {
            s = "" + address;
        }
        switch (op) {
            case 0: {
                if (address != 0) {
                    throw new LittleManException("Invalid Instruction");
                }
                this.messages.sendMessage("--> 0 represents: HALT");
                this.registers.setPC(this.registers.getPC() - 1);
                this.halt();
                break;
            }
            case 1: {
                this.messages.sendMessage("--> 1 represents: ADD");
                this.messages.sendMessage("--> " + s + " represents: source memory location");
                if (address >= this.memory.getMemorySize()) {
                    throw new LittleManException("Address " + address + " is out of range in line " + (this.registers.getPC() - 1));
                }
                this.registers.setMAR(address);
                this.registers.setMDR(this.memory.getData(this.registers.getMAR()));
                this.registers.setACC(this.registers.getACC() + this.registers.getMDR());
                this.registers.setACC(this.roll(this.registers.getACC()));
                this.messages.sendMessage("--> Value : " + this.registers.getMDR() + " from memory location " + s + " added to the Accumulator");
                break;
            }
            case 2: {
                this.messages.sendMessage("--> 2 represents: SUBTRACT");
                this.messages.sendMessage("--> " + s + " represents: source memory location");
                if (address >= this.memory.getMemorySize()) {
                    throw new LittleManException("Address " + address + " is invalid in line " + (this.registers.getPC() - 1));
                }
                this.registers.setMAR(address);
                this.registers.setMDR(this.memory.getData(this.registers.getMAR()));
                this.registers.setACC(this.registers.getACC() - this.registers.getMDR());
                this.registers.setACC(this.roll(this.registers.getACC()));
                this.messages.sendMessage("--> Value : " + this.registers.getMDR() + " from memory location " + s + " subtracted from the Accumulator");
                break;
            }
            case 3: {
                this.messages.sendMessage("--> 3 represents: STORE");
                this.messages.sendMessage("--> " + s + " represents: target memory location");
                if (address >= this.memory.getMemorySize()) {
                    throw new LittleManException("Address " + address + " is invalid in line " + (this.registers.getPC() - 1));
                }
                this.registers.setMAR(address);
                this.registers.setMDR(this.registers.getACC());
                this.memory.setData(this.registers.getMAR(), this.registers.getMDR());
                this.messages.sendMessage("--> Value : " + this.registers.getMDR() + " from the Accumulator stored" + "to memory location " + s);
                break;
            }
            case 5: {
                this.messages.sendMessage("--> 5 represents: LOAD");
                this.messages.sendMessage("--> " + s + " represents: source memory location");
                if (address >= 100) {
                    throw new LittleManException("Address " + address + " is invalid in line " + (this.registers.getPC() - 1));
                }
                this.registers.setMAR(address);
                this.registers.setMDR(this.memory.getData(this.registers.getMAR()));
                this.registers.setACC(this.registers.getMDR());
                this.messages.sendMessage("--> Value : " + this.registers.getMDR() + " from memory location " + s + " transfered to the Accumulator");
                break;
            }
            case 6: {
                this.messages.sendMessage("--> 6 represents: BRANCH");
                this.messages.sendMessage("--> " + s + " represents: source memory location");
                this.registers.setMAR(address);
                this.registers.setPC(this.registers.getMAR());
                this.messages.sendMessage("--> BRANCH to " + s + " : PC adjusted.");
                break;
            }
            case 7: {
                this.messages.sendMessage("--> 7 represents: BRANCH ON ZERO");
                this.messages.sendMessage("--> " + s + " represents: target memory location");
                this.messages.sendMessage("--> BRANCH on ZERO to " + s + " : Testing Accumulator...");
                if (this.registers.getACC() == 0) {
                    this.registers.setMAR(address);
                    this.registers.setPC(this.registers.getMAR());
                    this.messages.sendMessage("--> Accumulator " + this.registers.getACC() + " == 0, BRANCH to " + s + " : PC adjusted.");
                    break;
                }
                this.messages.sendMessage("--> Accumulator " + this.registers.getACC() + " not equal to 0," + "Branch NOT performed.");
                break;
            }
            case 8: {
                this.messages.sendMessage("--> 8 represents: BRANCH ON POSITIVE");
                this.messages.sendMessage("--> " + s + " represents: target memory location");
                this.messages.sendMessage("--> BRANCH on POSITIVE to " + s + " Testing Accumulator...");
                if (this.registers.getACC() >= 0) {
                    this.registers.setMAR(address);
                    this.registers.setPC(this.registers.getMAR());
                    this.messages.sendMessage("--> Accumulator " + this.registers.getACC() + " >= 0, BRANCH to " + s + " : PC adjusted.");
                    break;
                }
                this.messages.sendMessage("--> Accumulator " + this.registers.getACC() + " < 0, BRANCH not performed.");
                break;
            }
            case 9: {
                this.messages.sendMessage("--> 9 represents: INPUT or OUTPUT");
                this.messages.sendMessage("--> " + s + " represents: I/O" + " channel (01 = input, 02 = output)");
                this.registers.setMAR(address);
                if (this.registers.getMAR() == 1) {
                    this.registers.setACC(this.input());
                    this.messages.sendMessage("--> Value " + this.registers.getACC() + " copied from inbox to Accumulator");
                    break;
                }
                this.output(this.registers.getACC());
                this.messages.sendMessage("--> Value " + this.registers.getACC() + " copied from Accumulator to outbox");
                break;
            }
            default: {
                throw new LittleManException(op + " is not a valid instruction" + " in line " + (this.registers.getPC() - 1));
            }
        }
    }
    
    private int roll(final int n) throws LittleManException {
        int n2 = n;
        if (n <= 999 && n >= -999) {
            return n;
        }
        if (n < -999) {
            n2 = 1999 + n;
        }
        else if (n > 999) {
            n2 = -1999 + n;
        }
        if (n2 != n) {
            this.messages.sendMessage("Wrap around occured in the Accumulator");
        }
        return this.roll(n2);
    }
    
    private void output(final int n) {
        this.outputfield.setText(n + "");
        this.outputfield.setEditable(true);
        try {
            for (int i = 0; i < 3; ++i) {
                this.outputfield.setBackground(Color.YELLOW);
                Thread.sleep(20L);
                this.outputfield.setBackground(Color.LIGHT_GRAY);
            }
        }
        catch (InterruptedException ex) {}
        finally {
            this.outputfield.setEditable(false);
        }
    }
    
    private int input() {
        this.messages.sendMessage("\n Input is required by instruction " + this.registers.getPC());
        Toolkit.getDefaultToolkit().beep();
        final String input = this.inputfield.getInput();
        int int1;
        try {
            int1 = Integer.parseInt(input);
        }
        catch (NumberFormatException ex) {
            this.messages.sendMessage("Input must be an Integer");
            if (this.mode.getMode() == -1) {
                return 0;
            }
            return this.input();
        }
        if (Math.abs(int1) <= 999) {
            return int1;
        }
        this.messages.sendMessage("Input must be between -999 and 999");
        if (this.mode.getMode() == -1) {
            return 0;
        }
        return this.input();
    }
    
    private int getOp(final int n) {
        return n / 100;
    }
    
    private int getAddress(final int n) {
        return n % 100;
    }
    
    private int fetch(final int mar) throws LittleManException {
        this.registers.setMAR(mar);
        final int data = this.memory.getData(mar);
        this.registers.setMDR(data);
        return data;
    }
    
    private void message(final String s) {
        this.messages.sendMessage(s);
    }
    
    public void setInput(final int input) {
        this.input = input;
    }
    
    public void setValidProgramExists(final boolean validProgramExists) {
        this.validProgramExists = validProgramExists;
    }
}

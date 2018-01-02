import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.GridLayout;
import java.awt.Panel;

// 
// Decompiled by Procyon v0.5.30
// 

public class Memory extends Panel
{
    public static final int MEMORY_WIDTH = 10;
    private int memoryHeight;
    private MemoryCell[] memory;
    
    public Memory(final int n, final Messenger messenger) {
        this.memoryHeight = n / 10;
        this.memory = new MemoryCell[this.memoryHeight * 10];
        this.buildMemoryPanel(messenger);
    }
    
    public void setData(final int n, final int data) throws LittleManException {
        if (this.isValidAddress(n) && this.isValidValue(data)) {
            this.memory[n].setData(data);
        }
    }
    
    public int getData(final int n) throws LittleManException {
        if (this.isValidAddress(n)) {
            return this.memory[n].getData();
        }
        return 0;
    }
    
    public int getMemorySize() {
        return this.memory.length;
    }
    
    public MemoryCell[] getMemory() {
        return this.memory;
    }
    
    public void setAsCurrentInstruction(final int n) {
        this.memory[n].setAsCurrentInstruction();
    }
    
    public void unSetAsCurrentInstruction(final int n) {
        this.memory[n].unSetAsCurrentInstruction();
    }
    
    public void clear() {
        for (int i = 0; i < this.memory.length; ++i) {
            this.memory[i].setData(0);
            this.removeMemoryColors();
        }
    }
    
    public void removeMemoryColors() {
        for (int i = 0; i < this.memory.length; ++i) {
            this.unSetAsCurrentInstruction(i);
        }
    }
    
    private boolean isValidAddress(final int n) throws LittleManException {
        if (n < 0 || n >= this.memory.length) {
            throw new LittleManException(n + " is not a valid address");
        }
        return true;
    }
    
    private boolean isValidValue(final int n) throws LittleManException {
        if (n < -999 || n > 999) {
            throw new LittleManException(n + " is out of range");
        }
        return true;
    }
    
    private void buildMemoryPanel(final Messenger messenger) {
        this.setLayout(new GridLayout(10, this.memoryHeight));
        for (int i = 0; i < this.memoryHeight; ++i) {
            for (int j = 0; j < 10; ++j) {
                final int n = i * 10 + j;
                this.add(this.memory[n] = new MemoryCell(n, 0, messenger));
            }
        }
    }
}

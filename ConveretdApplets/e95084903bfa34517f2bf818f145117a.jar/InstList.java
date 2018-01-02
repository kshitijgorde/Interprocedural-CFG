import java.util.Vector;

// 
// Decompiled by Procyon v0.5.30
// 

class InstList
{
    private Instruction[] instructions;
    private Vector instructionList;
    
    public InstList() {
        this.instructionList = new Vector(10, 10);
    }
    
    public InstList(final Instruction instruction) {
        (this.instructions = new Instruction[1])[0] = instruction;
    }
    
    public void append(final Instruction instruction) {
        this.instructionList.addElement(instruction);
    }
    
    public Instruction[] getInstArry() {
        return this.instructions;
    }
    
    public String toString() {
        if (this.instructions == null) {
            return "";
        }
        int n = 0;
        int n2 = 0;
        final StringBuffer sb = new StringBuffer();
        for (int i = 0; i < this.instructions.length; ++i) {
            if (n2 != 0) {
                sb.append("  ");
                n2 = 0;
            }
            if (n != 0) {
                sb.append(" ");
            }
            final String string = this.instructions[i].toString();
            sb.append(string);
            n = 1;
            if (string.equals("\n")) {
                n2 = 1;
                n = 0;
            }
        }
        return sb.toString();
    }
    
    public void wrapup() {
        final int size = this.instructionList.size();
        if (size > 0) {
            this.instructions = new Instruction[size];
            for (int i = 0; i < size; ++i) {
                this.instructions[i] = (Instruction)this.instructionList.elementAt(i);
            }
        }
        else {
            this.instructions = null;
        }
        this.instructionList = null;
    }
}

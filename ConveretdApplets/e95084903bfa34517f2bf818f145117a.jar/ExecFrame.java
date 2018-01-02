// 
// Decompiled by Procyon v0.5.30
// 

public class ExecFrame
{
    private static final String CLASS_NAME = "ExecFrame";
    private int pc;
    private Object[] localVars;
    private String procName;
    private Instruction[] instructions;
    
    public ExecFrame(final Instruction instruction) {
        this(null, null, new Instruction[1]);
        this.instructions[0] = instruction;
    }
    
    public ExecFrame(final InstList list) {
        this(null, null, list.getInstArry());
    }
    
    public ExecFrame(final Instruction[] array) {
        this(null, null, array);
    }
    
    public ExecFrame(final String procName, final Object[] localVars, final Instruction[] instructions) {
        this.procName = procName;
        this.localVars = localVars;
        this.instructions = instructions;
        this.pc = 0;
    }
    
    public Object thing(final int n) {
        if (n < 0 || n >= this.localVars.length) {
            System.err.println("ExecFrame.thing: index out-of-bounds");
            return null;
        }
        return this.localVars[n];
    }
    
    public String getUserDefProcName() {
        return this.procName;
    }
    
    public boolean hasLocals() {
        return this.localVars != null;
    }
    
    public boolean isUserDefProc() {
        return this.procName != null;
    }
    
    public Instruction nextInst() {
        if (this.instructions != null && this.pc < this.instructions.length) {
            return this.instructions[this.pc++];
        }
        return null;
    }
    
    public void setValue(final int n, final Object o) {
        if (n < 0 || n >= this.localVars.length) {
            System.err.println("ExecFrame.setValue: index out-of-bounds");
        }
        else {
            this.localVars[n] = o;
        }
    }
    
    public String toString() {
        if (this.instructions == null) {
            return "";
        }
        int n = 0;
        int n2 = 0;
        final StringBuffer sb = new StringBuffer();
        if (this.procName != null) {
            sb.append("Procedure: ");
            sb.append(this.procName);
            sb.append('\n');
            n2 = 1;
        }
        sb.append("PC: ");
        sb.append(this.pc);
        if (this.pc > 0) {
            sb.append("  curInst: ");
            sb.append(this.instructions[this.pc - 1].toString());
        }
        sb.append('\n');
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
}

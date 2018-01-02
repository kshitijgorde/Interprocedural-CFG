import java.util.Vector;

// 
// Decompiled by Procyon v0.5.30
// 

class InvokeUserProc implements Instruction
{
    private static final String CLASS_NAME = "InvokeUserProc";
    private static int indentAmount;
    private static Vector traceProcs;
    private Object[] argExprs;
    protected String identifier;
    
    public InvokeUserProc(final String identifier, final Object[] argExprs) {
        this.identifier = identifier;
        this.argExprs = argExprs;
    }
    
    public void doIt() throws TTRuntimeError {
        this.printExitInfo(null);
    }
    
    protected void enteringProc(final Object[] array) {
        if (InvokeUserProc.traceProcs == null) {
            return;
        }
        for (int size = InvokeUserProc.traceProcs.size(), i = 0; i < size; ++i) {
            if (((String)InvokeUserProc.traceProcs.elementAt(i)).equalsIgnoreCase(this.identifier)) {
                final CommandCenter cmdCtr = TG.getCmdCtr();
                if (cmdCtr.getCurColNum() > 0) {
                    cmdCtr.println();
                }
                if (InvokeUserProc.indentAmount > 0) {
                    for (int j = InvokeUserProc.indentAmount; j > 0; --j) {
                        cmdCtr.print(" ");
                    }
                }
                cmdCtr.print("Entering " + this.identifier);
                if (this.argExprs != null) {
                    for (int k = 0; k < this.argExprs.length; ++k) {
                        cmdCtr.print(" " + array[k].toString());
                    }
                }
                cmdCtr.print("\n");
                ++InvokeUserProc.indentAmount;
            }
        }
    }
    
    protected void evalActArgExprs(final Object[] array) throws TTRuntimeError {
        if (this.argExprs != null) {
            for (int i = 0; i < this.argExprs.length; ++i) {
                if (this.argExprs[i] instanceof Expression) {
                    Object o;
                    for (o = ((Expression)this.argExprs[i]).getValue(); o instanceof Expression; o = ((Expression)this.argExprs[i]).getValue()) {}
                    array[i] = o;
                }
                else {
                    array[i] = this.argExprs[i];
                }
            }
        }
    }
    
    protected ExecFrame getExitTraceFrame() {
        ExecFrame execFrame = null;
        if (this.iAmBeingTraced()) {
            execFrame = new ExecFrame(new InvokeUserProc(this.identifier, null));
        }
        return execFrame;
    }
    
    protected boolean iAmBeingTraced() {
        if (InvokeUserProc.traceProcs == null) {
            return false;
        }
        for (int i = 0; i < InvokeUserProc.traceProcs.size(); ++i) {
            if (((String)InvokeUserProc.traceProcs.elementAt(i)).equalsIgnoreCase(this.identifier)) {
                return true;
            }
        }
        return false;
    }
    
    private void printExitInfo(final Object o) {
        final StringBuffer sb = new StringBuffer(40);
        --InvokeUserProc.indentAmount;
        final CommandCenter cmdCtr = TG.getCmdCtr();
        if (cmdCtr.getCurColNum() > 0) {
            sb.append('\n');
        }
        if (InvokeUserProc.indentAmount > 0) {
            for (int i = InvokeUserProc.indentAmount; i > 0; --i) {
                sb.append(' ');
            }
        }
        sb.append("Exiting ");
        sb.append(this.identifier);
        if (o != null) {
            sb.append(", output: ");
            sb.append(o.toString());
        }
        sb.append('\n');
        cmdCtr.print(sb.toString());
    }
    
    public void exitedOperator(final Object o) {
        if (this.iAmBeingTraced()) {
            this.printExitInfo(o);
        }
    }
    
    public static void reset() {
        if (InvokeUserProc.traceProcs != null) {
            InvokeUserProc.traceProcs.removeAllElements();
        }
    }
    
    public static void traceProc(final String s) {
        if (InvokeUserProc.traceProcs == null) {
            InvokeUserProc.traceProcs = new Vector(10, 5);
        }
        else {
            for (int i = 0; i < InvokeUserProc.traceProcs.size(); ++i) {
                if (((String)InvokeUserProc.traceProcs.elementAt(i)).equals(s)) {
                    return;
                }
            }
        }
        InvokeUserProc.traceProcs.addElement(s);
    }
    
    public static void untraceProc(final String s) {
        for (int i = 0; i < InvokeUserProc.traceProcs.size(); ++i) {
            if (((String)InvokeUserProc.traceProcs.elementAt(i)).equals(s)) {
                InvokeUserProc.traceProcs.removeElementAt(i);
            }
        }
    }
    
    public String toString() {
        if (this.argExprs == null) {
            return this.identifier;
        }
        final StringBuffer sb = new StringBuffer(this.identifier);
        for (int i = 0; i < this.argExprs.length; ++i) {
            sb.append(" ");
            sb.append(this.argExprs[i].toString());
        }
        return sb.toString();
    }
    
    static {
        InvokeUserProc.indentAmount = 0;
    }
}

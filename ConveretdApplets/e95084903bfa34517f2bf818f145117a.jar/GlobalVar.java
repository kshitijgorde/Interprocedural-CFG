// 
// Decompiled by Procyon v0.5.30
// 

public class GlobalVar
{
    private boolean beingTraced;
    private String identifier;
    private Object value;
    
    GlobalVar(final String identifier) {
        this.beingTraced = false;
        this.identifier = identifier;
        this.value = null;
    }
    
    private void printChangedValue() {
        final CommandCenter cmdCtr = TG.getCmdCtr();
        if (cmdCtr.getCurColNum() > 0) {
            cmdCtr.println();
        }
        final StringBuffer sb = new StringBuffer("GlobalVariable: ");
        sb.append(this.identifier);
        sb.append(" <-- ");
        sb.append(this.value);
        final String curUserProcName = TGDriver.getCurThreadTTI().getCurUserProcName();
        if (curUserProcName != null) {
            sb.append(" in ");
            sb.append(curUserProcName);
        }
        cmdCtr.println(sb.toString());
    }
    
    public String getIdentifier() {
        return this.identifier;
    }
    
    public Object thing() {
        return this.value;
    }
    
    public void setValue(final Object value) {
        this.value = value;
        if (this.beingTraced) {
            this.printChangedValue();
        }
    }
    
    public void traceVar() {
        this.beingTraced = true;
    }
    
    public void untraceVar() {
        this.beingTraced = false;
    }
}

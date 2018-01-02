// 
// Decompiled by Procyon v0.5.30
// 

class ProcDone extends Exception
{
    private Object output;
    
    public ProcDone(final Object output) {
        this.output = output;
    }
    
    public Object getOutput() {
        return this.output;
    }
}

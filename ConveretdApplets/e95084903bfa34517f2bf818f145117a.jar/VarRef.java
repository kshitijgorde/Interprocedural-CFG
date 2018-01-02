// 
// Decompiled by Procyon v0.5.30
// 

class VarRef implements Expression
{
    public static final boolean DESTINATION_REF = true;
    public static final boolean SOURCE_REF = false;
    private boolean isGlobal;
    private boolean isDestinationRef;
    private int index;
    private String identifier;
    private GlobalVar globalVar;
    
    VarRef(final String s, final int n) {
        this(s, n, false);
    }
    
    VarRef(final String identifier, final int index, final boolean isDestinationRef) {
        this.identifier = identifier;
        this.isDestinationRef = isDestinationRef;
        this.isGlobal = false;
        this.index = index;
    }
    
    VarRef(final String s, final GlobalVar globalVar) {
        this(s, globalVar, false);
    }
    
    VarRef(final String identifier, final GlobalVar globalVar, final boolean isDestinationRef) {
        this.identifier = identifier;
        this.isDestinationRef = isDestinationRef;
        this.isGlobal = true;
        this.globalVar = globalVar;
    }
    
    public Object getValue() throws TTRuntimeError {
        Object o = null;
        if (this.isDestinationRef) {
            System.err.println("VarRef.getValue: '" + this + "' is a destination ref");
        }
        else if (this.isGlobal) {
            o = this.globalVar.thing();
        }
        else {
            o = TGDriver.getCurThreadTTI().getLocalValue(this.index);
        }
        if (o == null) {
            throw new TTRuntimeError("Variable '" + this.identifier + "' has no value");
        }
        return o;
    }
    
    public void setValue(final Object value) {
        if (!this.isDestinationRef) {
            System.err.println("VarRef.setValue: '" + this + "' is not a destination");
            return;
        }
        if (this.isGlobal) {
            this.globalVar.setValue(value);
        }
        else {
            TGDriver.getCurThreadTTI().setLocalValue(this.index, value);
        }
    }
    
    public String toString() {
        if (this.isDestinationRef) {
            return "\"" + this.identifier;
        }
        return ":" + this.identifier;
    }
}

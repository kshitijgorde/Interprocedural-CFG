// 
// Decompiled by Procyon v0.5.30
// 

class InvokeUserOpr extends InvokeUserProc implements Expression
{
    public InvokeUserOpr(final String s, final Object[] array) {
        super(s, array);
    }
    
    public Object getValue() throws TTRuntimeError {
        final UserDefProc userDefProc = Workspace.getUserDefProc(super.identifier);
        Object[] array = null;
        final int numVars = userDefProc.numVars();
        if (numVars > 0) {
            array = new Object[numVars];
            super.evalActArgExprs(array);
        }
        super.enteringProc(array);
        final ExecFrame execFrame = new ExecFrame(super.identifier, array, userDefProc.getInstList().getInstArry());
        final Interpreter curThreadTTI = TGDriver.getCurThreadTTI();
        curThreadTTI.pushToDo(execFrame);
        Object o;
        try {
            o = curThreadTTI.doHeadFrame();
        }
        catch (ProcDone procDone) {
            o = procDone.getOutput();
        }
        super.exitedOperator(o);
        return o;
    }
}

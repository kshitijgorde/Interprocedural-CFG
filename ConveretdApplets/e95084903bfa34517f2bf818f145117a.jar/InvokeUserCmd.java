// 
// Decompiled by Procyon v0.5.30
// 

class InvokeUserCmd extends InvokeUserProc implements Instruction
{
    public InvokeUserCmd(final String s, final Object[] array) {
        super(s, array);
    }
    
    public void doIt() throws TTRuntimeError {
        final UserDefProc userDefProc = Workspace.getUserDefProc(super.identifier);
        Object[] array = null;
        final int numVars = userDefProc.numVars();
        if (numVars > 0) {
            array = new Object[numVars];
            super.evalActArgExprs(array);
        }
        super.enteringProc(array);
        final Interpreter curThreadTTI = TGDriver.getCurThreadTTI();
        final ExecFrame exitTraceFrame = super.getExitTraceFrame();
        if (exitTraceFrame != null) {
            curThreadTTI.pushToDo(exitTraceFrame);
        }
        curThreadTTI.pushToDo(new ExecFrame(super.identifier, array, userDefProc.getInstList().getInstArry()));
    }
}

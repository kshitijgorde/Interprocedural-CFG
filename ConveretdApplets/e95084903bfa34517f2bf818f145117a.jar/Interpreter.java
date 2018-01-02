import java.util.Vector;

// 
// Decompiled by Procyon v0.5.30
// 

class Interpreter implements QuitHandler, Runnable
{
    private static final String CLASS_NAME = "Interpreter";
    private static final String JVM_SOE = "JVM StackOverflowError";
    private boolean stopInterpreting;
    private String turtleName;
    private Thread myThread;
    private Vector toDo;
    
    public Interpreter() {
        this("t1");
    }
    
    public Interpreter(final String turtleName) {
        this.turtleName = turtleName;
        this.stopInterpreting = false;
        this.toDo = new Vector();
    }
    
    private void displayError(final String s) {
        final StringBuffer sb = new StringBuffer(s);
        final String curUserProcName = this.getCurUserProcName();
        if (curUserProcName != null) {
            sb.insert(0, ") ");
            sb.insert(0, curUserProcName);
            sb.insert(0, '(');
        }
        TGDriver.addRunTimeErrMsg(new ErrMsg(sb.toString()));
    }
    
    private ExecFrame peekToDo() {
        ExecFrame execFrame = null;
        synchronized (this.toDo) {
            if (this.toDo.size() > 0) {
                execFrame = this.toDo.firstElement();
            }
        }
        return execFrame;
    }
    
    private ExecFrame popToDo() {
        ExecFrame execFrame = null;
        synchronized (this.toDo) {
            if (this.toDo.size() > 0) {
                execFrame = this.toDo.firstElement();
                this.toDo.removeElementAt(0);
            }
        }
        return execFrame;
    }
    
    public void printToDo() {
        System.out.println("printToDo (" + this.turtleName + ") -----------------");
        System.out.println("toDo.size: " + this.toDo.size());
        synchronized (this.toDo) {
            for (int i = 0; i < this.toDo.size(); ++i) {
                System.out.println(this.toDo.elementAt(i));
            }
        }
        System.out.println("-------------------------------");
    }
    
    protected void finalize() throws Throwable {
        super.finalize();
        TG.getCmdCtr().removeQuitHandler(this);
        this.myThread = null;
        this.stopInterpreting = true;
    }
    
    public Object doHeadFrame() throws ProcDone, TTRuntimeError {
        final String s = "Interpreter.doHeadFrame: ";
        final ExecFrame peekToDo = this.peekToDo();
        ExecFrame peekToDo2;
        while ((peekToDo2 = this.peekToDo()) != null) {
            if (this.stopInterpreting) {
                throw new TTRuntimeError("*QUIT*");
            }
            final Instruction nextInst = peekToDo2.nextInst();
            if (nextInst != null) {
                try {
                    nextInst.doIt();
                }
                catch (ProcDone procDone) {
                    final Object output = procDone.getOutput();
                    ExecFrame execFrame = this.popToDo();
                    while (!execFrame.isUserDefProc() && execFrame != peekToDo) {
                        execFrame = this.popToDo();
                        if (execFrame == null) {
                            return output;
                        }
                    }
                    if (execFrame == peekToDo) {
                        throw procDone;
                    }
                    continue;
                }
            }
            else {
                if (this.popToDo() == peekToDo) {
                    return null;
                }
                continue;
            }
        }
        System.err.println(s + "lost frameToDo=" + peekToDo);
        return null;
    }
    
    public String getCurUserProcName() {
        synchronized (this.toDo) {
            for (int i = 0; i < this.toDo.size(); ++i) {
                final ExecFrame execFrame = this.toDo.elementAt(i);
                if (execFrame.isUserDefProc()) {
                    return execFrame.getUserDefProcName();
                }
            }
        }
        return null;
    }
    
    public Object getLocalValue(final int n) {
        synchronized (this.toDo) {
            for (int i = 0; i < this.toDo.size(); ++i) {
                final ExecFrame execFrame = this.toDo.elementAt(i);
                if (execFrame.hasLocals()) {
                    return execFrame.thing(n);
                }
            }
        }
        return null;
    }
    
    public String getName() {
        return this.turtleName;
    }
    
    public boolean isBusy() {
        synchronized (this.toDo) {
            return this.toDo.size() > 0;
        }
    }
    
    public void pushToDo(final ExecFrame execFrame) {
        synchronized (this.toDo) {
            this.toDo.insertElementAt(execFrame, 0);
        }
    }
    
    public void pushToDo(final InstList list) {
        synchronized (this.toDo) {
            this.toDo.insertElementAt(new ExecFrame(list), 0);
        }
    }
    
    public void queueToDo(final InstList list) {
        synchronized (this.toDo) {
            this.toDo.addElement(new ExecFrame(list));
            this.toDo.notifyAll();
        }
    }
    
    public void quit() {
        this.stopInterpreting = true;
    }
    
    public void run() {
        final String s = "Interpreter.run: ";
        TG.getCmdCtr().addQuitHandler(this);
        while (this.myThread == Thread.currentThread()) {
            if (this.peekToDo() == null) {
                TGDriver.interpreterDone(this);
                synchronized (this.toDo) {
                    this.toDo.notifyAll();
                    try {
                        this.toDo.wait();
                    }
                    catch (InterruptedException ex) {
                        System.err.println(s + "InterruptedException");
                    }
                    this.stopInterpreting = false;
                    continue;
                }
                break;
            }
            try {
                this.doHeadFrame();
            }
            catch (ProcDone procDone) {}
            catch (StackOverflowError stackOverflowError) {
                this.displayError("JVM StackOverflowError");
            }
            catch (TTRuntimeError ttRuntimeError) {
                if (ttRuntimeError.errMsg.equals("*QUIT*")) {
                    synchronized (this.toDo) {
                        this.toDo.removeAllElements();
                        continue;
                    }
                }
                this.displayError(ttRuntimeError.errMsg);
            }
        }
    }
    
    public void setLocalValue(final int n, final Object o) {
        synchronized (this.toDo) {
            for (int i = 0; i < this.toDo.size(); ++i) {
                final ExecFrame execFrame = this.toDo.elementAt(i);
                if (execFrame.hasLocals()) {
                    execFrame.setValue(n, o);
                    return;
                }
            }
            System.err.println("Interpreter.setLocalValue failed");
        }
    }
    
    public Thread startInterpreter(final int n) {
        (this.myThread = new Thread(this, "TI" + n)).setDaemon(true);
        this.myThread.start();
        return this.myThread;
    }
    
    public String toString() {
        final StringBuffer sb = new StringBuffer("Interpreter");
        sb.append("(");
        sb.append(this.turtleName);
        sb.append(") is ");
        final ExecFrame peekToDo = this.peekToDo();
        if (peekToDo != null) {
            sb.append("busy  \"");
            sb.append(peekToDo.toString());
            sb.append("\"");
        }
        else {
            sb.append("idle");
        }
        return sb.toString();
    }
}

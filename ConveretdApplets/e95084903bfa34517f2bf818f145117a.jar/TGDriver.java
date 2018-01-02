// 
// Decompiled by Procyon v0.5.30
// 

public class TGDriver implements Runnable, TGKeyHandler, TGMouseHandler, TTYInputHandler
{
    private static final String CLASS_NAME = "TGDriver";
    private static final int ERR_DISPLAY = 1;
    private static final int ERR_REMOVE = -1;
    private static final String interrupt = "InterruptedException";
    private static CommandCenter cmdCtr;
    private static int curInterpreter;
    private static Object waitLock;
    private static Parser parser;
    private static String defProc;
    private static Interpreter[] interpreters;
    private static Thread[] threadIds;
    private Workspace ws;
    private boolean needPrompt;
    private boolean waitingForInput;
    private StringBuffer internalInput;
    
    public TGDriver() {
        this.internalInput = new StringBuffer();
        TGDriver.curInterpreter = 0;
        TGDriver.parser = new Parser();
        this.ws = new Workspace();
        TGDriver.threadIds = new Thread[15];
        TGDriver.interpreters = new Interpreter[15];
        final Interpreter interpreter = new Interpreter();
        TGDriver.interpreters[0] = interpreter;
        TGDriver.threadIds[0] = interpreter.startInterpreter(0);
        TG.setCmdCtrTurtleName(interpreter.getName());
        (TGDriver.cmdCtr = TG.getCmdCtr()).addInputHandler(this);
        TGDriver.cmdCtr.println(";Welcome to TG version .9.19");
        this.needPrompt = true;
        this.waitingForInput = true;
        TGDriver.waitLock = new Object();
    }
    
    public void lineAvail() {
        synchronized (TGDriver.waitLock) {
            this.waitingForInput = false;
            TGDriver.waitLock.notify();
        }
    }
    
    public void processEscSeq(final String s) {
    }
    
    public void keyPressed(final int n) {
        if (Workspace.getUserDefProc("keypressed") == null) {
            return;
        }
        final String string = "keypressed " + n + "\n";
        synchronized (this.internalInput) {
            this.internalInput.insert(0, string);
        }
        synchronized (TGDriver.waitLock) {
            if (this.waitingForInput) {
                TGDriver.waitLock.notify();
            }
        }
    }
    
    public void mouseClicked() {
        if (Workspace.getUserDefProc("mouseclicked") == null) {
            return;
        }
        final String s = "mouseclicked\n";
        synchronized (this.internalInput) {
            this.internalInput.insert(0, s);
        }
        synchronized (TGDriver.waitLock) {
            if (this.waitingForInput) {
                TGDriver.waitLock.notify();
            }
        }
    }
    
    public void mouseMoved() {
        if (Workspace.getUserDefProc("mousemoved") == null) {
            return;
        }
        final String s = "mousemoved\n";
        synchronized (this.internalInput) {
            if (!this.internalInput.toString().startsWith(s)) {
                this.internalInput.insert(0, s);
            }
        }
        synchronized (TGDriver.waitLock) {
            if (this.waitingForInput) {
                TGDriver.waitLock.notify();
            }
        }
    }
    
    private String getInternalCmd() {
        String s = null;
        synchronized (this.internalInput) {
            final int length = this.internalInput.length();
            if (length > 0) {
                int length2;
                for (length2 = length - 2; length2 >= 0 && this.internalInput.charAt(length2) != '\n'; --length2) {}
                ++length2;
                final char[] array = new char[length - length2];
                this.internalInput.getChars(length2, length, array, 0);
                this.internalInput.setLength(length2);
                s = new String(array);
            }
        }
        return s;
    }
    
    private void interpretJLogo(final String s) {
        parseJLogo(s);
        final UserDefProc procedureBeingDefined;
        if ((procedureBeingDefined = TGDriver.parser.procedureBeingDefined()) != null) {
            if (TGDriver.defProc == null) {
                TG.setCmdCtrMessage(TGDriver.defProc = "Defining Procedure: " + procedureBeingDefined.getIdentifier());
            }
        }
        else if (TGDriver.defProc != null) {
            TG.clearCmdCtrMessage();
            TGDriver.defProc = null;
        }
        else if (TGDriver.interpreters[TGDriver.curInterpreter].isBusy()) {
            TG.setCmdCtrBusy();
        }
    }
    
    public static void abortProcDef() {
        TGDriver.parser.abortProcDef();
    }
    
    public static void addRunTimeErrMsg(final ErrMsg errMsg) {
        TGDriver.cmdCtr.getTextBuffer().addErrMsg(errMsg);
    }
    
    public static Interpreter getCurInterpreter() {
        return TGDriver.interpreters[TGDriver.curInterpreter];
    }
    
    public static Interpreter getCurThreadTTI() {
        final String s = "TGDriver.getCurThreadTTI: ";
        final Thread currentThread = Thread.currentThread();
        for (int i = 0; i < 15; ++i) {
            if (TGDriver.threadIds[i] == currentThread) {
                return TGDriver.interpreters[i];
            }
        }
        System.err.println(s + "couldn't match threadId");
        return TGDriver.interpreters[0];
    }
    
    public static Interpreter getTurtlesInterpreter(final String s) {
        for (int i = 0; i < 15; ++i) {
            final Interpreter interpreter = TGDriver.interpreters[i];
            if (interpreter != null && interpreter.getName().equalsIgnoreCase(s)) {
                return interpreter;
            }
        }
        return null;
    }
    
    public static Turtle getTurtle() {
        final String s = "TGDriver.getTurtle: ";
        final Thread currentThread = Thread.currentThread();
        for (int i = 0; i < 15; ++i) {
            if (TGDriver.threadIds[i] == currentThread) {
                return TG.getSprite(i);
            }
        }
        System.err.println(s + "couldn't match threadId");
        return TG.getSprite(0);
    }
    
    public static void interpreterDone(final Interpreter interpreter) {
        int i = 0;
        while (i < 15) {
            if (interpreter == TGDriver.interpreters[i]) {
                if (i == TGDriver.curInterpreter) {
                    TG.clearCmdCtrBusy();
                    break;
                }
                break;
            }
            else {
                ++i;
            }
        }
        if (TGDriver.waitLock != null) {
            synchronized (TGDriver.waitLock) {
                TGDriver.waitLock.notify();
            }
        }
    }
    
    public static Interpreter newTurtle(final String s) {
        if (getTurtlesInterpreter(s) != null) {
            return null;
        }
        int n;
        for (n = 1; n < 15 && TGDriver.interpreters[n] != null; ++n) {}
        if (n == 15) {
            return null;
        }
        final Interpreter interpreter = new Interpreter(s);
        TGDriver.interpreters[n] = interpreter;
        TGDriver.threadIds[n] = interpreter.startInterpreter(n);
        if (TG.getTGC() != null) {
            TG.addSprite(n);
        }
        return interpreter;
    }
    
    public static boolean parseJLogo(final String s) {
        if (s == null || s.length() == 0) {
            return true;
        }
        boolean jLogoTokenList = false;
        try {
            jLogoTokenList = TGDriver.parser.parseJLogoTokenList(new TokenList(new StringStream(s), '\0'));
        }
        catch (LexError lexError) {
            TGDriver.cmdCtr.getTextBuffer().addErrMsg(new ErrMsg(lexError.toString()));
        }
        return jLogoTokenList;
    }
    
    public static void printTurtleStates() {
        final Interpreter interpreter = TGDriver.interpreters[TGDriver.curInterpreter];
        if (TGDriver.cmdCtr.getCurColNum() > 0) {
            TGDriver.cmdCtr.println();
        }
        TGDriver.cmdCtr.println("CurrentTurtle: " + interpreter.getName());
        for (int i = 0; i < 15; ++i) {
            final Interpreter interpreter2 = TGDriver.interpreters[i];
            if (interpreter2 != null) {
                TGDriver.cmdCtr.println(interpreter2.toString());
            }
        }
        if (TGDriver.defProc == null) {
            TGDriver.cmdCtr.print("? ");
        }
        else {
            TGDriver.cmdCtr.print(">   ");
        }
    }
    
    public void reset() {
        for (int i = 0; i < 15; ++i) {
            if (TGDriver.interpreters[i] != null) {
                try {
                    TGDriver.interpreters[i].finalize();
                }
                catch (Throwable t) {}
                TGDriver.interpreters[i] = null;
            }
            TGDriver.threadIds[i] = null;
        }
        TGDriver.cmdCtr.clearText();
        TGDriver.cmdCtr.println(";Welcome to TG version .9.19");
        TGDriver.cmdCtr.print("? ");
        Workspace.clear();
        TGDriver.parser.reset();
        final Interpreter interpreter = new Interpreter();
        TGDriver.interpreters[0] = interpreter;
        TGDriver.threadIds[0] = interpreter.startInterpreter(0);
        TGDriver.curInterpreter = 0;
        TG.setCmdCtrTurtleName(interpreter.getName());
    }
    
    public void run() {
        final String s = "TGDriver.run: ";
        while (true) {
            final String internalCmd = this.getInternalCmd();
            if (internalCmd != null) {
                this.interpretJLogo(internalCmd);
            }
            else {
                final String inputLine = TGDriver.cmdCtr.getInputLine();
                if (inputLine == null) {
                    if (this.needPrompt) {
                        if (TGDriver.defProc == null) {
                            if (TGDriver.cmdCtr.getCurColNum() > 0) {
                                TGDriver.cmdCtr.println();
                            }
                            TGDriver.cmdCtr.print("? ");
                        }
                        else {
                            TGDriver.cmdCtr.print(">   ");
                        }
                        this.needPrompt = false;
                    }
                    synchronized (TGDriver.waitLock) {
                        this.waitingForInput = true;
                        try {
                            TGDriver.waitLock.wait();
                        }
                        catch (InterruptedException ex) {
                            System.err.println(s + "InterruptedException");
                        }
                        continue;
                    }
                }
                this.interpretJLogo(inputLine);
                this.needPrompt = true;
                try {
                    Thread.sleep(200L);
                }
                catch (InterruptedException ex2) {}
            }
        }
    }
    
    public static boolean setCurTurtle(final String cmdCtrTurtleName) {
        for (int i = 0; i < 15; ++i) {
            final Interpreter interpreter = TGDriver.interpreters[i];
            if (interpreter != null && interpreter.getName().equals(cmdCtrTurtleName)) {
                TGDriver.curInterpreter = i;
                TG.setCmdCtrTurtleName(cmdCtrTurtleName);
                return true;
            }
        }
        return false;
    }
    
    static {
        TGDriver.defProc = null;
    }
}

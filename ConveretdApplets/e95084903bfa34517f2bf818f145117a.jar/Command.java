// 
// Decompiled by Procyon v0.5.30
// 

class Command implements Instruction
{
    public static final int NOT_COMMAND = 0;
    public static final int COMMENT = 1;
    private static final int ARRAY_SETITEM = 2;
    private static final int CANVAS_BGCOLOR = 3;
    private static final int CANVAS_CLEAR = 4;
    private static final int CANVAS_FGCOLOR = 5;
    private static final int FLOCTL_FOREVER = 6;
    private static final int FLOCTL_IF = 7;
    private static final int FLOCTL_IFELSE = 8;
    private static final int FLOCTL_OUTPUT = 9;
    private static final int FLOCTL_REPEAT = 10;
    private static final int FLOCTL_STOP = 11;
    private static final int FLOCTL_WAIT = 12;
    private static final int TEXT_CLEAR = 13;
    private static final int TEXT_PRINT = 14;
    private static final int TEXT_PRINTLN = 15;
    private static final int TURTLE_BACK = 16;
    private static final int TURTLE_FILL = 17;
    private static final int TURTLE_LABELHT = 18;
    private static final int TURTLE_FWD = 19;
    private static final int TURTLE_HEADING = 20;
    private static final int TURTLE_HIDE = 21;
    private static final int TURTLE_HOME = 22;
    private static final int TURTLE_LABEL = 23;
    private static final int TURTLE_LEFT = 24;
    private static final int TURTLE_NEW = 25;
    private static final int TURTLE_PENCOLOR = 26;
    private static final int TURTLE_PENDOWN = 27;
    private static final int TURTLE_PENSIZE = 28;
    private static final int TURTLE_PENUP = 29;
    private static final int TURTLE_RIGHT = 30;
    private static final int TURTLE_SETPOS = 31;
    private static final int TURTLE_SETX = 32;
    private static final int TURTLE_SETXY = 33;
    private static final int TURTLE_SETY = 34;
    private static final int TURTLE_SHAPE = 35;
    private static final int TURTLE_SHOW = 36;
    private static final int TURTLE_TALKTO = 37;
    private static final int VAR_MAKE = 38;
    private static final String[] ifTyps;
    private static final String[] ifelTyps;
    private static final String[] makeTyps;
    private static final String[] oneNum;
    private static final String[] oneObj;
    private static final String[] oneSent;
    private static final String[] repTyps;
    private static final String[] sItmTyps;
    private static final String[] ntTtTyps;
    private static final String[] twoNums;
    private static final String[] twoObjs;
    private static final String AS_INPUT = " as input";
    private static final String BAD_TURTLE_NAME = "' is a bad turtle name";
    private static final String BAD_VAR_NAME = "' is a bad variable name";
    private static final String BAD_CMD = " bad cmdIdNum (";
    private static final String CANT_CREATE_TURTLE = "Can't create turtle named '";
    private static final String CANT_DO_SETITEM_ON = "Can't do setitem on ";
    private static final String CANT_TALKTO_TURTLE = "Can't talkto a turtle named '";
    private static final String CLASS_NAME = "Command";
    private static final String DOESNT_LIKE = " doesn't like ";
    private static final String ONLY_IN_PROC = " can only be used in a procedure";
    private int commandIdNum;
    private int[] maxRepeatCnt;
    private int[] repCount;
    private Object[] args;
    private Interpreter[] interpreters;
    
    public Command(final int n) {
        this(n, null);
    }
    
    public Command(final int commandIdNum, final Object[] args) {
        this.commandIdNum = commandIdNum;
        this.args = args;
    }
    
    private void back() throws TTRuntimeError {
        final Turtle turtle;
        if ((turtle = TGDriver.getTurtle()) != null) {
            turtle.bk(ExprToXxx.exprToNumber(this.args[0]).doubleValue());
        }
    }
    
    private void clean() {
        final TGCanvas tgc;
        if ((tgc = TG.getTGC()) != null) {
            tgc.clean();
        }
    }
    
    private void cleartext() {
        TG.getCmdCtr().clearText();
    }
    
    private String cmdIdToString() {
        switch (this.commandIdNum) {
            case 2: {
                return "setitem";
            }
            case 3: {
                return "";
            }
            case 4: {
                return "cg";
            }
            case 5: {
                return "";
            }
            case 1: {
                return ";";
            }
            case 7: {
                return "if";
            }
            case 8: {
                return "ifelse";
            }
            case 9: {
                return "output";
            }
            case 10: {
                return "repeat";
            }
            case 11: {
                return "stop";
            }
            case 12: {
                return "wait";
            }
            case 13: {
                return "ct";
            }
            case 14: {
                return "print";
            }
            case 15: {
                return "println";
            }
            case 16: {
                return "bk";
            }
            case 17: {
                return "fill";
            }
            case 18: {
                return "setlh";
            }
            case 19: {
                return "fd";
            }
            case 20: {
                return "seth";
            }
            case 21: {
                return "ht";
            }
            case 22: {
                return "home";
            }
            case 23: {
                return "label";
            }
            case 24: {
                return "lt";
            }
            case 25: {
                return "newturtle";
            }
            case 26: {
                return "setpc";
            }
            case 27: {
                return "pd";
            }
            case 28: {
                return "setps";
            }
            case 29: {
                return "pu";
            }
            case 30: {
                return "rt";
            }
            case 31: {
                return "setpos";
            }
            case 32: {
                return "setx";
            }
            case 33: {
                return "setxy";
            }
            case 34: {
                return "sety";
            }
            case 36: {
                return "st";
            }
            case 37: {
                return "talkto";
            }
            case 38: {
                return "make";
            }
            default: {
                System.err.println("Command.cmdIdToString: bad cmdIdNum (" + this.commandIdNum + ")");
                return "*BadCmd*";
            }
        }
    }
    
    private void comment() {
    }
    
    private void forward() throws TTRuntimeError {
        final Turtle turtle;
        if ((turtle = TGDriver.getTurtle()) != null) {
            turtle.fd(ExprToXxx.exprToNumber(this.args[0]).doubleValue());
        }
    }
    
    private void home() {
        final Turtle turtle;
        if ((turtle = TGDriver.getTurtle()) != null) {
            turtle.home();
        }
    }
    
    private void iff() throws TTRuntimeError {
        final boolean exprToBoolean = ExprToXxx.exprToBoolean(this.args[0]);
        final InstList list = (InstList)this.args[1];
        final Interpreter curThreadTTI = TGDriver.getCurThreadTTI();
        if (exprToBoolean) {
            curThreadTTI.pushToDo(list);
        }
    }
    
    private void ifelse() throws TTRuntimeError {
        final boolean exprToBoolean = ExprToXxx.exprToBoolean(this.args[0]);
        final InstList list = (InstList)this.args[1];
        final InstList list2 = (InstList)this.args[2];
        final Interpreter curThreadTTI = TGDriver.getCurThreadTTI();
        if (exprToBoolean) {
            curThreadTTI.pushToDo(list);
        }
        else {
            curThreadTTI.pushToDo(list2);
        }
    }
    
    private int interpreterToIndex(final Interpreter interpreter) {
        if (this.interpreters == null) {
            this.interpreters = new Interpreter[15];
            this.maxRepeatCnt = new int[15];
            this.repCount = new int[15];
            for (int i = 0; i < 15; ++i) {
                this.interpreters[i] = null;
            }
        }
        int n;
        for (n = 0; n < 15 && this.interpreters[n] != null; ++n) {
            if (this.interpreters[n] == interpreter) {
                return n;
            }
        }
        this.interpreters[n] = interpreter;
        return n;
    }
    
    private void label() throws TTRuntimeError {
        final Turtle turtle;
        if ((turtle = TGDriver.getTurtle()) != null) {
            turtle.label(ExprToXxx.exprToCollection(this.args[0]).toString());
        }
    }
    
    private void make() throws TTRuntimeError {
        final Object o = this.args[0];
        final Object exprToPrimitive = ExprToXxx.exprToPrimitive(this.args[1]);
        VarRef varRef;
        if (o instanceof VarRef) {
            varRef = (VarRef)o;
        }
        else {
            final Word exprToWord = ExprToXxx.exprToWord(o);
            if (!(exprToWord instanceof Word)) {
                throw new TTRuntimeError("'" + exprToWord + "' is a bad variable name");
            }
            final String string = exprToWord.toString();
            GlobalVar globalVar;
            if ((globalVar = Workspace.getGlobalVar(string)) == null) {
                globalVar = new GlobalVar(string);
                Workspace.addGlobalVar(globalVar);
            }
            varRef = new VarRef(string, globalVar, true);
        }
        varRef.setValue(exprToPrimitive);
    }
    
    private void newturtle() throws TTRuntimeError {
        final Word exprToWord = ExprToXxx.exprToWord(this.args[0]);
        final InstList list = (InstList)this.args[1];
        if (!(exprToWord instanceof Word)) {
            throw new TTRuntimeError("\"" + exprToWord + "' is a bad turtle name");
        }
        final String string = exprToWord.toString();
        Interpreter interpreter = TGDriver.getTurtlesInterpreter(string);
        if (interpreter == null && (interpreter = TGDriver.newTurtle(string)) == null) {
            throw new TTRuntimeError("Can't create turtle named '" + string + "'");
        }
        if (list != null) {
            interpreter.queueToDo(list);
        }
        else {
            TGDriver.setCurTurtle(string);
        }
    }
    
    private void pause() throws TTRuntimeError {
        final int intValue = ExprToXxx.exprToNumber(this.args[0]).intValue();
        try {
            Thread.sleep(intValue);
        }
        catch (InterruptedException ex) {}
    }
    
    private void setLabelHeight() throws TTRuntimeError {
        final Turtle turtle;
        if ((turtle = TGDriver.getTurtle()) != null) {
            turtle.setlabelheight(ExprToXxx.exprToNumber(this.args[0]).intValue());
        }
    }
    
    private void left() throws TTRuntimeError {
        final Turtle turtle;
        if ((turtle = TGDriver.getTurtle()) != null) {
            turtle.lt(ExprToXxx.exprToNumber(this.args[0]).doubleValue());
        }
    }
    
    private void print() throws TTRuntimeError {
        TG.getCmdCtr().print(ExprToXxx.exprToCollection(this.args[0]).toString());
    }
    
    private void println() throws TTRuntimeError {
        TG.getCmdCtr().println(ExprToXxx.exprToCollection(this.args[0]).toString());
    }
    
    private void repeat() throws TTRuntimeError, ProcDone {
        final int intValue = ExprToXxx.exprToNumber(this.args[0]).intValue();
        if (intValue < 0) {
            final StringBuffer sb = new StringBuffer("REPEAT");
            sb.append(" doesn't like ");
            sb.append("count of ");
            sb.append(intValue);
            throw new TTRuntimeError(sb.toString());
        }
        if (intValue == 0) {
            return;
        }
        final Interpreter curThreadTTI = TGDriver.getCurThreadTTI();
        final int interpreterToIndex = this.interpreterToIndex(curThreadTTI);
        this.maxRepeatCnt[interpreterToIndex] = intValue;
        this.repCount[interpreterToIndex] = 1;
        final InstList list = (InstList)this.args[1];
        while (this.repCount[interpreterToIndex] <= this.maxRepeatCnt[interpreterToIndex]) {
            curThreadTTI.pushToDo(list);
            curThreadTTI.doHeadFrame();
            final int[] repCount = this.repCount;
            final int n = interpreterToIndex;
            ++repCount[n];
        }
    }
    
    private void right() throws TTRuntimeError {
        final Turtle turtle;
        if ((turtle = TGDriver.getTurtle()) != null) {
            turtle.rt(ExprToXxx.exprToNumber(this.args[0]).doubleValue());
        }
    }
    
    private void setheading() throws TTRuntimeError {
        final Turtle turtle;
        if ((turtle = TGDriver.getTurtle()) != null) {
            turtle.seth(ExprToXxx.exprToNumber(this.args[0]).intValue());
        }
    }
    
    private void setitem() throws TTRuntimeError {
        final Collection exprToCollection = ExprToXxx.exprToCollection(this.args[1]);
        if (!(exprToCollection instanceof Array)) {
            throw new TTRuntimeError("Can't do setitem on " + exprToCollection);
        }
        final int intValue = ExprToXxx.exprToNumber(this.args[0]).intValue();
        Object value = this.args[2];
        if (value instanceof Expression) {
            value = ((Expression)value).getValue();
        }
        if (((Array)exprToCollection).setitem(intValue, value)) {
            return;
        }
        throw new TTRuntimeError("Setitem doesn't like " + intValue + " as input");
    }
    
    private void setpencolor() throws TTRuntimeError {
        final Turtle turtle;
        if ((turtle = TGDriver.getTurtle()) != null) {
            turtle.setpc(ExprToXxx.exprToNumber(this.args[0]).intValue());
        }
    }
    
    private void setpensize() throws TTRuntimeError {
        final Turtle turtle;
        if ((turtle = TGDriver.getTurtle()) != null) {
            turtle.setpensize(ExprToXxx.exprToNumber(this.args[0]).intValue());
        }
    }
    
    private void setpos() throws TTRuntimeError {
        final Turtle turtle = TGDriver.getTurtle();
        if (turtle == null) {
            return;
        }
        TGPoint exprToPoint;
        try {
            exprToPoint = ExprToXxx.exprToPoint(this.args[0]);
        }
        catch (TTRuntimeError ttRuntimeError) {
            throw new TTRuntimeError("Setpos: " + ttRuntimeError.errMsg);
        }
        turtle.setxy(exprToPoint);
    }
    
    private void setshape() throws TTRuntimeError {
        final Turtle turtle = TGDriver.getTurtle();
        if (turtle == null) {
            return;
        }
        final String s = "Setshape:";
        int[] array = null;
        final Object exprToPrimitive = ExprToXxx.exprToPrimitive(this.args[0]);
        int n = 0;
        Label_0228: {
            if (exprToPrimitive instanceof List) {
                final List list = (List)exprToPrimitive;
                int length = list.length();
                if (length < 1) {
                    throw new TTRuntimeError(s + " doesn't like " + "empty list" + " as input");
                }
                final Object first = list.first();
                try {
                    n = ExprToXxx.exprToNumber(first).intValue();
                    if (--length > 0) {
                        array = new int[length];
                        List list2 = (List)list.butFirst();
                        for (int i = 0; i < length; ++i) {
                            array[i] = ExprToXxx.exprToNumber(list2.first()).intValue();
                            list2 = (List)list2.butFirst();
                        }
                    }
                    break Label_0228;
                }
                catch (TTRuntimeError ttRuntimeError) {
                    throw new TTRuntimeError(s + " " + ttRuntimeError.errMsg);
                }
            }
            n = ExprToXxx.exprToNumber(exprToPrimitive).intValue();
        }
        turtle.setshape(n, array);
    }
    
    private void setx() throws TTRuntimeError {
        final Turtle turtle;
        if ((turtle = TGDriver.getTurtle()) != null) {
            turtle.setx(ExprToXxx.exprToNumber(this.args[0]).floatValue());
        }
    }
    
    private void setxy() throws TTRuntimeError {
        final Turtle turtle;
        if ((turtle = TGDriver.getTurtle()) != null) {
            turtle.setxy(new TGPoint(ExprToXxx.exprToNumber(this.args[0]).floatValue(), ExprToXxx.exprToNumber(this.args[1]).floatValue()));
        }
    }
    
    private void sety() throws TTRuntimeError {
        final Turtle turtle;
        if ((turtle = TGDriver.getTurtle()) != null) {
            turtle.sety(ExprToXxx.exprToNumber(this.args[0]).floatValue());
        }
    }
    
    private void talkto() throws TTRuntimeError {
        final Word exprToWord = ExprToXxx.exprToWord(this.args[0]);
        final InstList list = (InstList)this.args[1];
        if (!(exprToWord instanceof Word)) {
            throw new TTRuntimeError("'" + exprToWord + "' is a bad turtle name");
        }
        final String string = exprToWord.toString();
        final Interpreter turtlesInterpreter = TGDriver.getTurtlesInterpreter(string);
        if (turtlesInterpreter == null) {
            throw new TTRuntimeError("Can't talkto a turtle named '" + exprToWord + "'");
        }
        if (list != null) {
            turtlesInterpreter.queueToDo(list);
        }
        else {
            TGDriver.setCurTurtle(string);
        }
    }
    
    public void doIt() throws TTRuntimeError, ProcDone {
        switch (this.commandIdNum) {
            case 2: {
                this.setitem();
            }
            case 4: {
                final TGCanvas tgc;
                if ((tgc = TG.getTGC()) != null) {
                    tgc.clean();
                }
            }
            case 1: {}
            case 7: {
                this.iff();
            }
            case 8: {
                this.ifelse();
            }
            case 9: {
                throw new ProcDone(ExprToXxx.exprToPrimitive(this.args[0]));
            }
            case 10: {
                this.repeat();
            }
            case 11: {
                throw new ProcDone(new NoOutput());
            }
            case 12: {
                this.pause();
            }
            case 13: {
                this.cleartext();
            }
            case 14: {
                this.print();
            }
            case 15: {
                this.println();
            }
            case 16: {
                this.back();
            }
            case 17: {
                final Turtle turtle;
                if ((turtle = TGDriver.getTurtle()) != null) {
                    turtle.fill();
                }
            }
            case 18: {
                this.setLabelHeight();
            }
            case 19: {
                this.forward();
            }
            case 20: {
                this.setheading();
            }
            case 21: {
                final Turtle turtle2;
                if ((turtle2 = TGDriver.getTurtle()) != null) {
                    turtle2.ht();
                }
            }
            case 22: {
                final Turtle turtle3;
                if ((turtle3 = TGDriver.getTurtle()) != null) {
                    turtle3.home();
                }
            }
            case 23: {
                this.label();
            }
            case 24: {
                this.left();
            }
            case 25: {
                this.newturtle();
            }
            case 26: {
                this.setpencolor();
            }
            case 27: {
                final Turtle turtle4;
                if ((turtle4 = TGDriver.getTurtle()) != null) {
                    turtle4.pd();
                }
            }
            case 28: {
                this.setpensize();
            }
            case 29: {
                final Turtle turtle5;
                if ((turtle5 = TGDriver.getTurtle()) != null) {
                    turtle5.pu();
                }
            }
            case 30: {
                this.right();
            }
            case 36: {
                final Turtle turtle6;
                if ((turtle6 = TGDriver.getTurtle()) != null) {
                    turtle6.st();
                }
            }
            case 31: {
                this.setpos();
            }
            case 32: {
                this.setx();
            }
            case 33: {
                this.setxy();
            }
            case 34: {
                this.sety();
            }
            case 35: {
                this.setshape();
            }
            case 37: {
                this.talkto();
            }
            case 38: {
                this.make();
            }
            default: {
                System.err.println("Command.doIt: bad cmdIdNum (" + this.commandIdNum + ")");
            }
        }
    }
    
    public static int getCommandId(String lowerCase) {
        lowerCase = lowerCase.toLowerCase();
        if (lowerCase.equals(";")) {
            return 1;
        }
        if (lowerCase.equals("back") || lowerCase.equals("bk")) {
            return 16;
        }
        if (lowerCase.equals("clean") || lowerCase.equals("cg")) {
            return 4;
        }
        if (lowerCase.equals("cleartext") || lowerCase.equals("ct")) {
            return 13;
        }
        if (lowerCase.equals("fill")) {
            return 17;
        }
        if (lowerCase.equals("forever")) {
            return 6;
        }
        if (lowerCase.equals("forward") || lowerCase.equals("fd")) {
            return 19;
        }
        if (lowerCase.equals("hideturtle") || lowerCase.equals("ht")) {
            return 21;
        }
        if (lowerCase.equals("home")) {
            return 22;
        }
        if (lowerCase.equals("if")) {
            return 7;
        }
        if (lowerCase.equals("ifelse")) {
            return 8;
        }
        if (lowerCase.equals("label")) {
            return 23;
        }
        if (lowerCase.equals("left") || lowerCase.equals("lt")) {
            return 24;
        }
        if (lowerCase.equals("make")) {
            return 38;
        }
        if (lowerCase.equals("newturtle") || lowerCase.equals("nt")) {
            return 25;
        }
        if (lowerCase.equals("output") || lowerCase.equals("op")) {
            return 9;
        }
        if (lowerCase.equals("pendown") || lowerCase.equals("pd")) {
            return 27;
        }
        if (lowerCase.equals("penup") || lowerCase.equals("pu")) {
            return 29;
        }
        if (lowerCase.equals("print") || lowerCase.equals("type")) {
            return 14;
        }
        if (lowerCase.equals("println") || lowerCase.equals("pr") || lowerCase.equals("show")) {
            return 15;
        }
        if (lowerCase.equals("repeat")) {
            return 10;
        }
        if (lowerCase.equals("right") || lowerCase.equals("rt")) {
            return 30;
        }
        if (lowerCase.equals("setheading") || lowerCase.equals("seth")) {
            return 20;
        }
        if (lowerCase.equals("setitem")) {
            return 2;
        }
        if (lowerCase.equals("setlabelheight") || lowerCase.equals("lfs") || lowerCase.equals("setlh")) {
            return 18;
        }
        if (lowerCase.equals("setpencolor") || lowerCase.equals("setpc") || lowerCase.equals("setc")) {
            return 26;
        }
        if (lowerCase.equals("setpensize") || lowerCase.equals("setps")) {
            return 28;
        }
        if (lowerCase.equals("setpos")) {
            return 31;
        }
        if (lowerCase.equals("setshape") || lowerCase.equals("setsh")) {
            return 35;
        }
        if (lowerCase.equals("setx")) {
            return 32;
        }
        if (lowerCase.equals("setxy")) {
            return 33;
        }
        if (lowerCase.equals("sety")) {
            return 34;
        }
        if (lowerCase.equals("showturtle") || lowerCase.equals("st")) {
            return 36;
        }
        if (lowerCase.equals("stop")) {
            return 11;
        }
        if (lowerCase.equals("talkto")) {
            return 37;
        }
        if (lowerCase.equals("wait")) {
            return 12;
        }
        return 0;
    }
    
    public static String[] getInputTypes(final int n) {
        switch (n) {
            case 2: {
                return Command.sItmTyps;
            }
            case 1:
            case 4:
            case 11:
            case 13:
            case 17:
            case 21:
            case 22:
            case 27:
            case 29:
            case 36: {
                return null;
            }
            case 7: {
                return Command.ifTyps;
            }
            case 8: {
                return Command.ifelTyps;
            }
            case 10: {
                return Command.repTyps;
            }
            case 25:
            case 37: {
                return Command.ntTtTyps;
            }
            case 9:
            case 14:
            case 15:
            case 23:
            case 35: {
                return Command.oneObj;
            }
            case 12:
            case 16:
            case 18:
            case 19:
            case 20:
            case 24:
            case 26:
            case 28:
            case 30:
            case 32:
            case 34: {
                return Command.oneNum;
            }
            case 31: {
                return Command.oneSent;
            }
            case 33: {
                return Command.twoNums;
            }
            case 38: {
                return Command.makeTyps;
            }
            default: {
                System.err.print("Command.getInputTypes: bad cmdIdNum (" + n + ")");
                return null;
            }
        }
    }
    
    public static int getNumInputs(final int n) {
        switch (n) {
            case 4:
            case 11:
            case 13:
            case 17:
            case 21:
            case 22:
            case 27:
            case 29:
            case 36: {
                return 0;
            }
            case 1:
            case 9:
            case 12:
            case 14:
            case 15:
            case 16:
            case 18:
            case 19:
            case 20:
            case 23:
            case 24:
            case 25:
            case 26:
            case 28:
            case 30:
            case 31:
            case 32:
            case 34:
            case 35:
            case 37: {
                return 1;
            }
            case 7:
            case 10:
            case 33:
            case 38: {
                return 2;
            }
            case 2:
            case 8: {
                return 3;
            }
            default: {
                System.err.println("Command.getNumInputs: bad cmdIdNum (" + n + ")");
                return 0;
            }
        }
    }
    
    public int getRepeatCounter() {
        return this.repCount[this.interpreterToIndex(TGDriver.getCurThreadTTI())];
    }
    
    public static boolean isNewturtle(final int n) {
        return n == 25;
    }
    
    public boolean isNewturtleDirective() {
        return this.commandIdNum == 25 && this.args[1] == null;
    }
    
    public boolean isOutput() {
        return this.commandIdNum == 9;
    }
    
    public static boolean isRepeat(final int n) {
        return n == 10;
    }
    
    public boolean isStop() {
        return this.commandIdNum == 11;
    }
    
    public static boolean isTalkto(final int n) {
        return n == 37;
    }
    
    public boolean isTalktoDirective() {
        return this.commandIdNum == 37 && this.args[1] == null;
    }
    
    public String toString() {
        switch (this.commandIdNum) {
            case 3:
            case 4:
            case 5:
            case 11:
            case 13:
            case 17:
            case 21:
            case 22:
            case 27:
            case 29:
            case 36: {
                return this.cmdIdToString();
            }
            case 9:
            case 12:
            case 14:
            case 15:
            case 16:
            case 18:
            case 19:
            case 20:
            case 23:
            case 24:
            case 26:
            case 28:
            case 30:
            case 31:
            case 32:
            case 34:
            case 35: {
                return this.cmdIdToString() + " " + this.args[0].toString();
            }
            case 33:
            case 38: {
                final StringBuffer sb = new StringBuffer(this.cmdIdToString());
                sb.append(' ');
                sb.append(this.args[0].toString());
                sb.append(' ');
                sb.append(this.args[1].toString());
                return sb.toString();
            }
            case 2: {
                final StringBuffer sb2 = new StringBuffer(this.cmdIdToString());
                sb2.append(' ');
                sb2.append(this.args[0].toString());
                sb2.append(' ');
                sb2.append(this.args[1].toString());
                sb2.append(' ');
                sb2.append(this.args[2].toString());
                return sb2.toString();
            }
            case 7:
            case 10: {
                final StringBuffer sb3 = new StringBuffer(this.cmdIdToString());
                sb3.append(' ');
                sb3.append(this.args[0].toString());
                sb3.append(" [");
                sb3.append(this.args[1].toString());
                sb3.append(']');
                return sb3.toString();
            }
            case 8: {
                final StringBuffer sb4 = new StringBuffer(this.cmdIdToString());
                sb4.append(' ');
                sb4.append(this.args[0].toString());
                sb4.append(" [");
                sb4.append(this.args[1].toString());
                sb4.append("] [");
                sb4.append(this.args[2].toString());
                sb4.append(']');
                return sb4.toString();
            }
            case 1: {
                if (this.args != null) {
                    return ";" + this.args[0].toString();
                }
                return "\n";
            }
            case 25:
            case 37: {
                final StringBuffer sb5 = new StringBuffer(this.cmdIdToString());
                sb5.append(' ');
                sb5.append(this.args[0].toString());
                if (this.args[1] != null) {
                    sb5.append(" [");
                    sb5.append(this.args[1].toString());
                    sb5.append(']');
                }
                return sb5.toString();
            }
            default: {
                System.err.println("Command.toString: bad cmdIdNum (" + this.commandIdNum + ")");
                return "*BadCmd*";
            }
        }
    }
    
    static {
        ifTyps = new String[] { "java.lang.Boolean", "InstList" };
        ifelTyps = new String[] { "java.lang.Boolean", "InstList", "InstList" };
        makeTyps = new String[] { "VarRef", "java.lang.Object" };
        oneNum = new String[] { "java.lang.Number" };
        oneObj = new String[] { "java.lang.Object" };
        oneSent = new String[] { "List" };
        repTyps = new String[] { "java.lang.Number", "InstList" };
        sItmTyps = new String[] { "java.lang.Number", "Array", "java.lang.Object" };
        ntTtTyps = new String[] { "Word", "InstList" };
        twoNums = new String[] { "java.lang.Number", "java.lang.Number" };
        twoObjs = new String[] { "java.lang.Object", "java.lang.Object" };
    }
}

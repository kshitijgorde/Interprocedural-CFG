// 
// Decompiled by Procyon v0.5.30
// 

class Parser
{
    private static final String CLASS_NAME = "Parser";
    private static final int MAX_INPUTS = 8;
    private static final String ALREADY_DEF = " is already defined - a primitive ";
    private static final String AS_INPUT = "' as input";
    private static final String BAD_ID = "expected identifier, got ";
    private static final String BAD_PROC_INP = "Improper procedure input - ";
    private static final String BAD_PROC_NAME = "Improper procedure name - ";
    private static final String BAD_VAR_NAME = "' is an improper variable name";
    private static final String BE_USED_IN = " be used in a ";
    private static final String CAN_ONLY = " can only";
    private static final String CANT = " can not";
    private static final String CMD = " command";
    private static final String DOESNT_LIKE = " doesn't like '";
    private static final String DOESNT_OUTPUT_VALUE = " doesn't output value";
    private static final String ELEM = " elements";
    private static final String EMPTY_WD = "an empty word ";
    private static final String EXPECTED_A = "Expected a ";
    private static final String HOW_TO = "how to ";
    private static final String ID = " identifier";
    private static final String I_DONT_KNOW = "I don't know ";
    private static final String INP_MUST_BE_NAME = "input must be a name";
    private static final String INP_TO = " input to ";
    private static final String INS_TO = "Instructions to ";
    private static final String INSTEAD_OF = " instead of ";
    private static final String MORE_THAN = "An array must have more than ";
    private static final String MUST_BE_FOLLOWED_BY = " must be followed by ";
    private static final String NAMED = " named ";
    private static final String NO_PROC = "No procedure";
    private static final String OR_GLB = " or global ";
    private static final String OR_SENT = "or sentence";
    private static final String NOT_ENUF_INPUTS_TO = "Not enough inputs to ";
    private static final String OUTPUT_FROM = "output from ";
    private static final String PROC = "procedure";
    private static final String TOO_MANY_INPUTS = "Too many procedure inputs";
    private static final String VAR = "variable";
    private static final String WHAT_TO_DO_WITH = "what to do with ";
    private Command repeatCmd;
    private InstList instList;
    private UserDefProc procBeingDefined;
    
    public Parser() {
        this.instList = null;
        this.procBeingDefined = null;
        this.repeatCmd = null;
    }
    
    private VarRef chkDestVarRef(final Object o) throws ParsingError {
        if (o instanceof Number || o instanceof List) {
            throw new ParsingError("'" + o + "' is an improper variable name");
        }
        VarRef varRef = null;
        if (o instanceof Word) {
            final String string = ((Word)o).toString();
            if (this.procBeingDefined != null) {
                final int inputIndex = this.procBeingDefined.getInputIndex(string);
                if (inputIndex >= 0) {
                    varRef = new VarRef(string, inputIndex, true);
                }
                else {
                    final int localIndex;
                    if ((localIndex = this.procBeingDefined.getLocalIndex(string)) >= 0) {
                        varRef = new VarRef(string, localIndex, true);
                    }
                    else {
                        final GlobalVar globalVar;
                        if ((globalVar = Workspace.getGlobalVar(string)) != null) {
                            varRef = new VarRef(string, globalVar, true);
                        }
                    }
                }
            }
            else {
                GlobalVar globalVar2;
                if ((globalVar2 = Workspace.getGlobalVar(string)) == null) {
                    globalVar2 = new GlobalVar(string);
                    Workspace.addGlobalVar(globalVar2);
                }
                varRef = new VarRef(string, globalVar2, true);
            }
        }
        if (varRef == null) {
            throw new ParsingError("'" + o + "' is an improper variable name");
        }
        return varRef;
    }
    
    private void doComment(final Token token) throws ParsingError {
        if (this.procBeingDefined != null) {
            this.instList.append(new Command(1, new Object[] { token.value }));
        }
    }
    
    private void doEnd() {
        if (this.procBeingDefined != null) {
            this.instList.wrapup();
            Workspace.addUserDefProc(this.procBeingDefined);
            this.procBeingDefined = null;
            this.instList = null;
        }
    }
    
    private void doGlobal(final TokenStream tokenStream) throws ParsingError {
        final String s = "GLOBAL declaration ";
        if (this.procBeingDefined != null) {
            throw new ParsingError(s + " can not" + " be used in a " + "procedure");
        }
        String identifier;
        try {
            identifier = this.parseIdentifier(tokenStream.nextToken(), true);
        }
        catch (ParsingError parsingError) {
            throw new ParsingError(s + parsingError.errMsg);
        }
        if (identifier == null) {
            throw new ParsingError(s + " must be followed by " + "variable" + " identifier");
        }
        if (Workspace.isGlobalVar(identifier)) {
            return;
        }
        Workspace.addGlobalVar(new GlobalVar(identifier));
    }
    
    private void doLocal(TokenStream tokenStream) throws ParsingError {
        final String s = "LOCAL declaration";
        if (this.procBeingDefined == null) {
            throw new ParsingError(s + " can only" + " be used in a " + "procedure");
        }
        final Token nextToken = tokenStream.nextToken();
        if (nextToken.typeCode != 5 || ((String)nextToken.value).charAt(0) != '\"') {
            if (nextToken.typeCode == 2) {
                tokenStream = new TokenStream((TokenList)nextToken.value);
                Token token = tokenStream.currentToken();
                try {
                    String identifier;
                    while ((identifier = this.parseIdentifier(token, false)) != null) {
                        if (this.procBeingDefined.getInputIndex(identifier) >= 0) {
                            throw new ParsingError(s + " doesn't like '" + identifier + "' as input");
                        }
                        if (this.procBeingDefined.getLocalIndex(identifier) < 0) {
                            this.procBeingDefined.addLocalVar(identifier);
                        }
                        token = tokenStream.nextToken();
                    }
                    return;
                }
                catch (ParsingError parsingError) {
                    throw new ParsingError(s + " " + parsingError.errMsg);
                }
            }
            throw new ParsingError(s + " doesn't like '" + nextToken + "' as input");
        }
        String identifier2;
        try {
            identifier2 = this.parseIdentifier(nextToken, true);
        }
        catch (ParsingError parsingError2) {
            throw new ParsingError(s + " " + parsingError2.errMsg);
        }
        if (this.procBeingDefined.getInputIndex(identifier2) >= 0) {
            throw new ParsingError(s + " doesn't like '" + identifier2 + "' as input");
        }
        if (this.procBeingDefined.getLocalIndex(identifier2) < 0) {
            this.procBeingDefined.addLocalVar(identifier2);
        }
    }
    
    private void doPrintProcs() throws ParsingError {
        if (this.procBeingDefined != null) {
            throw new ParsingError("PRINTPROCS can not be used in a procedure");
        }
        final StringBuffer[] userDefProcHeaders = Workspace.getUserDefProcHeaders();
        final CommandCenter cmdCtr = TG.getCmdCtr();
        for (int i = 0; i < userDefProcHeaders.length; ++i) {
            cmdCtr.println(userDefProcHeaders[i]);
        }
    }
    
    private void doPrintText(final TokenStream tokenStream) throws ParsingError {
        if (this.procBeingDefined != null) {
            throw new ParsingError("PRINTTEXT can not be used in a procedure");
        }
        final Token nextToken = tokenStream.nextToken();
        if (nextToken.typeCode != 5) {
            throw new ParsingError("PRINTTEXT input must be a name");
        }
        final UserDefProc userDefProc = Workspace.getUserDefProc((String)nextToken.value);
        if (userDefProc != null) {
            TG.getCmdCtr().print(userDefProc.toString());
            return;
        }
        throw new ParsingError("No procedure named " + nextToken.value);
    }
    
    private void doQuit() {
        if (this.procBeingDefined != null) {
            this.abortProcDef();
            TG.getCmdCtr().print("^Q\n");
        }
    }
    
    private void doTo(final TokenStream tokenStream) throws ParsingError {
        final String s = "TO";
        if (this.procBeingDefined != null) {
            throw new ParsingError(s + " can not" + " be used in a " + "procedure");
        }
        if (this.instList != null) {
            throw new ParsingError(s + " can not" + " be used in a " + " command");
        }
        String identifier;
        try {
            identifier = this.parseIdentifier(tokenStream.nextToken(), true);
        }
        catch (ParsingError parsingError) {
            throw new ParsingError("Improper procedure name - " + parsingError.errMsg);
        }
        if (identifier == null) {
            throw new ParsingError(s + " must be followed by " + "procedure" + " identifier");
        }
        final int commandId = Command.getCommandId(identifier);
        final int operatorId = Operator.getOperatorId(identifier);
        if (commandId != 0 || operatorId != 0) {
            throw new ParsingError("'" + identifier + "'" + " is already defined - a primitive " + "procedure");
        }
        final String[] parameters = this.parseParameters(tokenStream);
        this.instList = new InstList();
        this.procBeingDefined = new UserDefProc(identifier, parameters, this.instList);
    }
    
    private void doTraceUntrace(final TokenStream tokenStream, final String s) throws ParsingError {
        if (this.procBeingDefined != null) {
            throw new ParsingError("'" + s + "'" + " can not" + " be used in a " + "procedure");
        }
        final Token nextToken = tokenStream.nextToken();
        if (nextToken == null) {
            throw new ParsingError(s.toUpperCase() + " must be followed by " + "an" + " identifier");
        }
        if (nextToken.typeCode != 5) {
            throw new ParsingError(s + "input must be a name");
        }
        if (Workspace.getUserDefProc((String)nextToken.value) != null) {
            if (s.equals("trace")) {
                InvokeUserProc.traceProc((String)nextToken.value);
            }
            else {
                InvokeUserProc.untraceProc((String)nextToken.value);
            }
        }
        else {
            final GlobalVar globalVar = Workspace.getGlobalVar((String)nextToken.value);
            if (globalVar == null) {
                throw new ParsingError("No procedure or global variable named " + (String)nextToken.value);
            }
            if (s.equals("trace")) {
                globalVar.traceVar();
            }
            else {
                globalVar.untraceVar();
            }
        }
    }
    
    private Object getTokenParseExpr(final TokenStream tokenStream) throws ParsingError {
        tokenStream.nextToken();
        return this.parseExpr(tokenStream);
    }
    
    private Object[] parseActualArgs(final TokenStream tokenStream, final UserDefProc userDefProc) throws ParsingError {
        final int numInputs = userDefProc.numInputs();
        final Object[] array = new Object[numInputs];
        for (int i = 0; i < numInputs; ++i) {
            final Object tokenParseExpr = this.getTokenParseExpr(tokenStream);
            if (tokenParseExpr == null) {
                throw new ParsingError("Not enough inputs to " + userDefProc.getIdentifier());
            }
            array[i] = tokenParseExpr;
        }
        return array;
    }
    
    private Array parseArray(final TokenList list) throws ParsingError {
        final int length = list.length();
        if (length <= 0) {
            final StringBuffer sb = new StringBuffer("An array must have more than ");
            sb.append(length);
            sb.append(" elements");
            throw new ParsingError(sb);
        }
        Array array = null;
        try {
            array = new Array(this.parseList(list));
        }
        catch (TTRuntimeError ttRuntimeError) {}
        return array;
    }
    
    private void parseCommand(final TokenStream tokenStream, final String s, final int n) throws ParsingError, TTRuntimeError {
        final boolean b = Command.isNewturtle(n) || Command.isTalkto(n);
        Object[] array = null;
        int numInputs;
        final int n2 = numInputs = Command.getNumInputs(n);
        if (b) {
            ++numInputs;
        }
        if (numInputs > 0) {
            array = new Object[numInputs];
        }
        final Command repeatCmd = new Command(n, array);
        final Command repeatCmd2 = this.repeatCmd;
        if (Command.isRepeat(n)) {
            this.repeatCmd = repeatCmd;
        }
        final String[] inputTypes = Command.getInputTypes(n);
        for (int i = 0; i < n2; ++i) {
            Object o;
            if (inputTypes[i].equals("InstList")) {
                o = this.parseInstList(tokenStream);
            }
            else {
                o = this.getTokenParseExpr(tokenStream);
            }
            if (inputTypes[i].equals("VarRef")) {
                o = this.chkDestVarRef(o);
            }
            if (o == null) {
                throw new ParsingError("Not enough inputs to " + s.toUpperCase());
            }
            array[i] = o;
        }
        if (b && (array[n2] = this.parseInstList(tokenStream)) == null && this.procBeingDefined != null) {
            throw new ParsingError("Not enough inputs to " + s.toUpperCase());
        }
        this.repeatCmd = repeatCmd2;
        if (this.instList != null) {
            if (this.procBeingDefined == null) {
                if (repeatCmd.isOutput() || repeatCmd.isStop()) {
                    throw new ParsingError(s.toUpperCase() + " can only" + " be used in a " + "procedure");
                }
                this.instList.append(repeatCmd);
            }
            else {
                if (repeatCmd.isOutput()) {
                    this.procBeingDefined.setIsOperator();
                }
                this.instList.append(repeatCmd);
            }
        }
        else {
            if (repeatCmd.isOutput() || repeatCmd.isStop()) {
                throw new ParsingError(s.toUpperCase() + " can only" + " be used in a " + "procedure");
            }
            if (!repeatCmd.isNewturtleDirective()) {
                if (!repeatCmd.isTalktoDirective()) {
                    TGDriver.getCurInterpreter().queueToDo(new InstList(repeatCmd));
                    return;
                }
            }
            try {
                repeatCmd.doIt();
            }
            catch (ProcDone procDone) {}
        }
    }
    
    private boolean parseDirective(final TokenStream tokenStream, final String s) throws ParsingError {
        if (s.equals("bye")) {
            TG.done();
            return true;
        }
        if (s.equals("end")) {
            this.doEnd();
            return true;
        }
        if (s.equals("global")) {
            this.doGlobal(tokenStream);
            return true;
        }
        if (s.equals("local")) {
            this.doLocal(tokenStream);
            return true;
        }
        if (s.equals("printprocs") || s.equals("pp")) {
            this.doPrintProcs();
            return true;
        }
        if (s.equals("printtext") || s.equals("pt") || s.equals("po")) {
            this.doPrintText(tokenStream);
            return true;
        }
        if (s.equals("to")) {
            this.doTo(tokenStream);
            return true;
        }
        if (s.equals("trace") || s.equals("untrace")) {
            this.doTraceUntrace(tokenStream, s);
            return true;
        }
        return false;
    }
    
    private String parseIdentifier(final Token token, final boolean b) throws ParsingError {
        if (token == null) {
            return null;
        }
        if (token.typeCode == 1) {
            return null;
        }
        if (token.typeCode != 5) {
            throw new ParsingError("expected identifier, got '" + token.value + "'");
        }
        String substring = (String)token.value;
        if (b && (substring.charAt(0) == '\"' || substring.charAt(0) == ':')) {
            substring = substring.substring(1, substring.length());
        }
        if (substring.length() == 0) {
            throw new ParsingError("expected identifier, got an empty word ");
        }
        final char char1 = substring.charAt(0);
        if (char1 < 'A' || char1 > 'z') {
            throw new ParsingError("expected identifier, got '" + token.value + "'");
        }
        if (char1 > 'Z' && char1 < 'a') {
            throw new ParsingError("expected identifier, got '" + token.value + "'");
        }
        return substring;
    }
    
    private InstList parseInstList(final TokenStream tokenStream) throws ParsingError, TTRuntimeError {
        final Token nextToken = tokenStream.nextToken();
        if (nextToken == null) {
            return null;
        }
        if (nextToken.typeCode != 2) {
            return null;
        }
        final InstList instList = this.instList;
        this.instList = new InstList();
        final StringStream stringStream = new StringStream(nextToken.value.toString() + ']');
        try {
            this.parsingDriver(new TokenStream(new TokenList(stringStream, ']')));
        }
        catch (LexError lexError) {
            throw new ParsingError(lexError.toString());
        }
        this.instList.wrapup();
        final InstList instList2 = this.instList;
        this.instList = instList;
        return instList2;
    }
    
    private void parseUsrDefCmdInvocation(final TokenStream tokenStream, final UserDefProc userDefProc) throws ParsingError {
        if (userDefProc.isOperator()) {
            final StringBuffer sb = new StringBuffer("I don't know ");
            sb.append("what to do with ");
            sb.append("output from ");
            sb.append(userDefProc.getIdentifier());
            throw new ParsingError(sb);
        }
        Object[] actualArgs = null;
        if (userDefProc.numInputs() > 0) {
            actualArgs = this.parseActualArgs(tokenStream, userDefProc);
        }
        final InvokeUserCmd invokeUserCmd = new InvokeUserCmd(userDefProc.getIdentifier(), actualArgs);
        if (this.instList != null) {
            this.instList.append(invokeUserCmd);
        }
        else {
            TGDriver.getCurInterpreter().queueToDo(new InstList(invokeUserCmd));
        }
    }
    
    private Object parseUsrDefOprInvocation(final TokenStream tokenStream, final UserDefProc userDefProc) throws ParsingError {
        Object[] actualArgs = null;
        if (userDefProc.numInputs() > 0) {
            actualArgs = this.parseActualArgs(tokenStream, userDefProc);
        }
        return new InvokeUserOpr(userDefProc.getIdentifier(), actualArgs);
    }
    
    private Object[] parseExprList(final TokenStream tokenStream, final int n) throws ParsingError {
        final Object[] array = new Object[n];
        for (int i = 0; i < n; ++i) {
            array[i] = this.getTokenParseExpr(tokenStream);
        }
        return array;
    }
    
    private Object parseExpr(final TokenStream tokenStream) throws ParsingError {
        Token currentToken;
        while ((currentToken = tokenStream.currentToken()) != null) {
            switch (currentToken.typeCode) {
                case 1: {
                    break;
                }
                case 3: {
                    return this.parseExpr(new TokenStream((TokenList)currentToken.value));
                }
                case 5: {
                    final String s = (String)currentToken.value;
                    final char char1 = s.charAt(0);
                    if (char1 == '\"') {
                        return new Word(s.substring(1));
                    }
                    if (char1 == ':') {
                        return this.parseVarRef(s.substring(1));
                    }
                    final int operatorId = Operator.getOperatorId(s);
                    if (operatorId != 0) {
                        if (Operator.isRepcount(operatorId)) {
                            if (this.repeatCmd != null) {
                                return new Operator(operatorId, this.repeatCmd);
                            }
                            final StringBuffer sb = new StringBuffer(s.toUpperCase());
                            sb.append(" can only");
                            sb.append(" be used in a ");
                            sb.append("REPEAT");
                            sb.append(" command");
                            throw new ParsingError(sb);
                        }
                        else {
                            final Operator operator = this.parseOperator(tokenStream, s, operatorId);
                            if (operator != null) {
                                return operator;
                            }
                        }
                    }
                    if (this.procBeingDefined != null && s.equalsIgnoreCase(this.procBeingDefined.getIdentifier())) {
                        return this.parseUsrDefOprInvocation(tokenStream, this.procBeingDefined);
                    }
                    final UserDefProc userDefProc = Workspace.getUserDefProc(s);
                    if (userDefProc != null) {
                        if (userDefProc.isOperator()) {
                            return this.parseUsrDefOprInvocation(tokenStream, userDefProc);
                        }
                        throw new ParsingError(s.toUpperCase() + " doesn't output value");
                    }
                    else {
                        if (Command.getCommandId(s) != 0) {
                            throw new ParsingError(s.toUpperCase() + " doesn't output value");
                        }
                        try {
                            return new Integer(s);
                        }
                        catch (NumberFormatException ex) {
                            try {
                                return new Float(s);
                            }
                            catch (NumberFormatException ex2) {
                                throw new ParsingError("I don't know how to " + s);
                            }
                        }
                        return this.parseList((TokenList)currentToken.value);
                    }
                    break;
                }
                case 2: {
                    return this.parseList((TokenList)currentToken.value);
                }
                case 4: {
                    return this.parseArray((TokenList)currentToken.value);
                }
                default: {
                    final StringBuffer sb2 = new StringBuffer("Parser");
                    sb2.append(".parseExpr: illegal token type code = ");
                    sb2.append(currentToken.typeCode);
                    System.err.println(sb2.toString());
                    break;
                }
            }
            tokenStream.nextToken();
        }
        return null;
    }
    
    private List parseList(final TokenList list) throws ParsingError {
        final List list2 = new List();
        if (list == null) {
            return list2;
        }
        final TokenStream tokenStream = new TokenStream(list);
        for (Token token = tokenStream.currentToken(); token != null; token = tokenStream.nextToken()) {
            switch (token.typeCode) {
                case 1: {
                    System.err.println("parseList: COMMENT in TokenList='" + token.value + "'");
                    break;
                }
                case 5: {
                    list2.append(new Word((String)token.value));
                    break;
                }
                case 2: {
                    list2.append(this.parseList((TokenList)token.value));
                    break;
                }
                case 4: {
                    list2.append(this.parseArray((TokenList)token.value));
                    break;
                }
                default: {
                    System.err.println("parseList: unexpected token='" + token.value + "'");
                    break;
                }
            }
        }
        return list2;
    }
    
    private String[] parseParameters(final TokenStream tokenStream) throws ParsingError {
        int i;
        String[] array;
        String identifier;
        for (i = 0, array = new String[8]; i < 8; array[i++] = identifier) {
            try {
                identifier = this.parseIdentifier(tokenStream.nextToken(), true);
            }
            catch (ParsingError parsingError) {
                throw new ParsingError("Improper procedure input - " + parsingError.errMsg);
            }
            if (identifier == null) {
                break;
            }
        }
        if (i > 8) {
            throw new ParsingError("Too many procedure inputs");
        }
        if (i == 0) {
            return null;
        }
        final String[] array2 = new String[i];
        for (int j = 0; j < i; ++j) {
            array2[j] = array[j];
        }
        return array2;
    }
    
    private Operator parseOperator(final TokenStream tokenStream, final String s, final int n) throws ParsingError {
        Object o = null;
        final int numInputs = Operator.getNumInputs(n);
        if (numInputs > 0) {
            o = new Object[numInputs];
        }
        for (int i = 0; i < numInputs; ++i) {
            final Object tokenParseExpr = this.getTokenParseExpr(tokenStream);
            if (tokenParseExpr == null) {
                throw new ParsingError("Not enough inputs to " + s.toUpperCase());
            }
            o[i] = tokenParseExpr;
        }
        if (numInputs == 1) {
            return new Operator(n, o[0]);
        }
        return new Operator(n, o);
    }
    
    private VarRef parseVarRef(final String s) throws ParsingError {
        if (this.procBeingDefined != null) {
            final int inputIndex = this.procBeingDefined.getInputIndex(s);
            if (inputIndex >= 0) {
                return new VarRef(s, inputIndex);
            }
            final int localIndex = this.procBeingDefined.getLocalIndex(s);
            if (localIndex >= 0) {
                return new VarRef(s, localIndex);
            }
        }
        final VarRef globalVarRef = Workspace.globalVarRef(s);
        if (globalVarRef == null) {
            throw new ParsingError("'" + globalVarRef + "' is an improper variable name");
        }
        return globalVarRef;
    }
    
    private void parsingDriver(final TokenStream tokenStream) throws ParsingError, TTRuntimeError {
        Token currentToken;
        while ((currentToken = tokenStream.currentToken()) != null) {
            switch (currentToken.typeCode) {
                case 1: {
                    this.doComment(currentToken);
                    break;
                }
                case 3: {
                    this.parsingDriver(new TokenStream((TokenList)currentToken.value));
                    break;
                }
                case 5: {
                    final String s = (String)currentToken.value;
                    final int commandId = Command.getCommandId(s);
                    if (commandId != 0) {
                        this.parseCommand(tokenStream, s, commandId);
                        break;
                    }
                    if (this.parseDirective(tokenStream, s)) {
                        break;
                    }
                    if (this.procBeingDefined != null && s.equalsIgnoreCase(this.procBeingDefined.getIdentifier())) {
                        this.parseUsrDefCmdInvocation(tokenStream, this.procBeingDefined);
                        break;
                    }
                    final UserDefProc userDefProc = Workspace.getUserDefProc(s);
                    if (userDefProc != null && !userDefProc.isOperator()) {
                        this.parseUsrDefCmdInvocation(tokenStream, userDefProc);
                        break;
                    }
                    final Object expr = this.parseExpr(tokenStream);
                    final StringBuffer sb = new StringBuffer("I don't know ");
                    if (expr != null) {
                        sb.append("what to do with ");
                        if (expr instanceof Operator || expr instanceof InvokeUserOpr) {
                            sb.append("output from ");
                        }
                        sb.append(expr.toString());
                    }
                    else {
                        sb.append("how to ");
                        sb.append(s.toUpperCase());
                    }
                    throw new ParsingError(sb);
                }
                default: {
                    final StringBuffer sb2 = new StringBuffer("Parser");
                    sb2.append(".parsingDriver: illegal token type code = ");
                    sb2.append(currentToken.typeCode);
                    break;
                }
            }
            tokenStream.nextToken();
        }
    }
    
    public void abortProcDef() {
        if (this.procBeingDefined != null) {
            this.instList.wrapup();
            this.instList = null;
            this.procBeingDefined = null;
        }
    }
    
    public UserDefProc procedureBeingDefined() {
        return this.procBeingDefined;
    }
    
    public boolean parseJLogoTokenList(final TokenList list) {
        boolean b = false;
        if (this.procBeingDefined != null) {
            b = true;
        }
        try {
            this.parsingDriver(new TokenStream(list));
        }
        catch (ParsingError parsingError) {
            final String errMsg = parsingError.errMsg;
            if (this.procBeingDefined != null) {
                new StringBuffer().append("(").append(this.procBeingDefined.getIdentifier()).append(") ").append(parsingError.errMsg).toString();
            }
            TG.addErrMsg(new ErrMsg(parsingError.errMsg));
            return false;
        }
        catch (TTRuntimeError ttRuntimeError) {
            TG.addErrMsg(new ErrMsg(ttRuntimeError.errMsg));
            return false;
        }
        if (b && this.procBeingDefined != null) {
            this.instList.append(new Command(1));
        }
        return true;
    }
    
    public void reset() {
        this.instList = null;
        this.procBeingDefined = null;
        InvokeUserProc.reset();
    }
    
    class ParsingError extends Exception
    {
        String errMsg;
        
        ParsingError(final Parser parser, final StringBuffer sb) {
            this(parser, sb.toString());
        }
        
        ParsingError(final String errMsg) {
            this.errMsg = errMsg;
        }
    }
}

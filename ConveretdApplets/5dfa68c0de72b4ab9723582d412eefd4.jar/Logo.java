import java.util.HashSet;

// 
// Decompiled by Procyon v0.5.30
// 

public class Logo
{
    static long starttime;
    
    static String runToplevel(final Object[] array, final LContext lContext) {
        lContext.iline = new MapList(array);
        lContext.timeToStop = false;
        try {
            evLine(lContext);
        }
        catch (LogoError logoError) {
            if (logoError.getMessage() != null) {
                return logoError.getMessage();
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return ex.toString();
        }
        catch (Error error) {
            return error.toString();
        }
        return null;
    }
    
    static void evLine(final LContext lContext) {
        while (!lContext.iline.eof() && lContext.ufunresult == null) {
            final Object eval;
            if ((eval = eval(lContext)) != null) {
                error("You don't say what to do with " + prs(eval), lContext);
            }
        }
    }
    
    static Object eval(final LContext lContext) {
        Object o = evalToken(lContext);
        while (infixNext(lContext.iline, lContext)) {
            if (o instanceof Nothing) {
                error(lContext.iline.peek() + " needs more inputs", lContext);
            }
            o = evalInfix(o, lContext);
        }
        return o;
    }
    
    static Object evalToken(final LContext lContext) {
        final Object next = lContext.iline.next();
        if (next instanceof QuotedSymbol) {
            return ((QuotedSymbol)next).sym;
        }
        if (next instanceof DottedSymbol) {
            return getValue(((DottedSymbol)next).sym, lContext);
        }
        if (next instanceof Symbol) {
            return evalSym((Symbol)next, null, lContext);
        }
        if (next instanceof String) {
            return evalSym(intern((String)next, lContext), null, lContext);
        }
        return next;
    }
    
    static Object evalSym(final Symbol cfun, Object[] evalArgs, final LContext lContext) {
        if (lContext.timeToStop) {
            error("Stopped!!!", lContext);
        }
        if (cfun.fcn == null) {
            error("I don't know how to " + cfun, lContext);
        }
        final Symbol cfun2 = lContext.cfun;
        lContext.cfun = cfun;
        final int priority = lContext.priority;
        lContext.priority = 0;
        Object dispatch = null;
        try {
            final Function fcn = cfun.fcn;
            final int nargs = fcn.nargs;
            if (evalArgs == null) {
                evalArgs = evalArgs(nargs, lContext);
            }
            dispatch = fcn.instance.dispatch(fcn.dispatchOffset, evalArgs, lContext);
        }
        catch (RuntimeException ex) {
            errorHandler(cfun, evalArgs, ex, lContext);
        }
        finally {
            lContext.cfun = cfun2;
            lContext.priority = priority;
        }
        if (lContext.mustOutput && dispatch == null) {
            error(cfun + " didn't output to " + lContext.cfun, lContext);
        }
        return dispatch;
    }
    
    static Object[] evalArgs(final int n, final LContext lContext) {
        final boolean mustOutput = lContext.mustOutput;
        lContext.mustOutput = true;
        final Object[] array = new Object[n];
        try {
            for (int i = 0; i < n; ++i) {
                if (lContext.iline.eof()) {
                    error(lContext.cfun + " needs more inputs", lContext);
                }
                array[i] = eval(lContext);
                if (array[i] instanceof Nothing) {
                    error(lContext.cfun + " needs more inputs", lContext);
                }
            }
        }
        finally {
            lContext.mustOutput = mustOutput;
        }
        return array;
    }
    
    static void runCommand(final Object[] array, final LContext lContext) {
        final boolean mustOutput = lContext.mustOutput;
        lContext.mustOutput = false;
        try {
            runList(array, lContext);
        }
        finally {
            lContext.mustOutput = mustOutput;
        }
    }
    
    static Object runList(final Object[] array, final LContext lContext) {
        final MapList iline = lContext.iline;
        lContext.iline = new MapList(array);
        Object eval = null;
        try {
            if (lContext.mustOutput) {
                eval = eval(lContext);
            }
            else {
                evLine(lContext);
            }
            checkListEmpty(lContext.iline, lContext);
        }
        finally {
            lContext.iline = iline;
        }
        return eval;
    }
    
    static Object evalOneArg(final MapList iline, final LContext lContext) {
        final boolean mustOutput = lContext.mustOutput;
        lContext.mustOutput = true;
        final MapList iline2 = lContext.iline;
        lContext.iline = iline;
        try {
            return eval(lContext);
        }
        finally {
            lContext.iline = iline2;
            lContext.mustOutput = mustOutput;
        }
    }
    
    static boolean infixNext(final MapList list, final LContext lContext) {
        final Object peek;
        final Function fcn;
        return !list.eof() && (peek = list.peek()) instanceof Symbol && (fcn = ((Symbol)peek).fcn) != null && fcn.nargs < lContext.priority;
    }
    
    static Object evalInfix(final Object o, final LContext lContext) {
        final Symbol cfun = (Symbol)lContext.iline.next();
        final Function fcn = cfun.fcn;
        final Symbol cfun2 = lContext.cfun;
        lContext.cfun = cfun;
        final int priority = lContext.priority;
        lContext.priority = fcn.nargs;
        Object dispatch = null;
        final Object[] array = { o, null };
        try {
            array[1] = evalArgs(1, lContext)[0];
            dispatch = fcn.instance.dispatch(fcn.dispatchOffset, array, lContext);
        }
        catch (RuntimeException ex) {
            errorHandler(cfun, array, ex, lContext);
        }
        finally {
            lContext.cfun = cfun2;
            lContext.priority = priority;
        }
        if (lContext.mustOutput && dispatch == null) {
            error(cfun + " didn't output to " + lContext.cfun, lContext);
        }
        return dispatch;
    }
    
    static Symbol intern(String s, final LContext lContext) {
        String substring;
        if (s.length() == 0) {
            substring = s;
        }
        else if (s.charAt(0) == '|') {
            s = (substring = s.substring(1));
        }
        else {
            substring = s;
        }
        Symbol symbol = lContext.oblist.get(substring);
        if (symbol == null) {
            lContext.oblist.put(substring, symbol = new Symbol(s));
        }
        return symbol;
    }
    
    static Object[] parse(final String s, final LContext lContext) {
        return new TokenStream(s).readList(lContext);
    }
    
    static String prs(final Object o) {
        return prs(o, 10);
    }
    
    static String prs(final Object o, final int n) {
        return prs(o, n, new HashSet());
    }
    
    static String prs(final Object o, final int n, final HashSet set) {
        if (o instanceof Number && n == 16) {
            return Long.toString(((Number)o).longValue(), 16).toUpperCase();
        }
        if (o instanceof Number && n == 8) {
            return Long.toString(((Number)o).longValue(), 8);
        }
        if (o instanceof Number && isInt((Number)o)) {
            return Long.toString(((Number)o).longValue(), 10);
        }
        if (!(o instanceof Object[])) {
            return o.toString();
        }
        final Object[] array = (Object[])o;
        if (array.length > 0 && set.contains(o)) {
            return "...";
        }
        if (array.length > 0) {
            set.add(o);
        }
        String s = "";
        for (int i = 0; i < array.length; ++i) {
            if (array[i] instanceof Object[]) {
                s += "[";
            }
            s += prs(array[i], n, set);
            if (array[i] instanceof Object[]) {
                s += "]";
            }
            if (i != array.length - 1) {
                s += " ";
            }
        }
        return s;
    }
    
    static boolean isInt(final Number n) {
        return n.doubleValue() == new Integer(n.intValue());
    }
    
    static boolean aValidNumber(final String s) {
        if (s.length() == 1 && "0123456789".indexOf(s.charAt(0)) == -1) {
            return false;
        }
        if ("eE.+-0123456789".indexOf(s.charAt(0)) == -1) {
            return false;
        }
        for (int i = 1; i < s.length(); ++i) {
            if ("eE.0123456789".indexOf(s.charAt(i)) == -1) {
                return false;
            }
        }
        return true;
    }
    
    static Object getValue(final Symbol symbol, final LContext lContext) {
        final Object value;
        if ((value = symbol.value) != null) {
            return value;
        }
        error(symbol + " has no value", lContext);
        return null;
    }
    
    static void setValue(final Symbol symbol, final Object value, final LContext lContext) {
        symbol.value = value;
    }
    
    static double aDouble(final Object o, final LContext lContext) {
        if (o instanceof Double) {
            return (double)o;
        }
        final String prs = prs(o);
        if (prs.length() > 0 && aValidNumber(prs)) {
            return Double.valueOf(prs);
        }
        error(lContext.cfun + " doesn't like " + prs(o) + " as input", lContext);
        return 0.0;
    }
    
    static int anInt(final Object o, final LContext lContext) {
        if (o instanceof Double) {
            return (int)o;
        }
        final String prs = prs(o);
        if (aValidNumber(prs)) {
            return (int)(Object)Double.valueOf(prs);
        }
        error(lContext.cfun + " doesn't like " + prs + " as input", lContext);
        return 0;
    }
    
    static long aLong(final Object o, final LContext lContext) {
        if (o instanceof Double) {
            return (long)o;
        }
        final String prs = prs(o);
        if (aValidNumber(prs)) {
            return (long)(Object)Double.valueOf(prs);
        }
        error(lContext.cfun + " doesn't like " + prs + " as input", lContext);
        return 0L;
    }
    
    static boolean aBoolean(final Object o, final LContext lContext) {
        if (o instanceof Boolean) {
            return (boolean)o;
        }
        if (o instanceof Symbol) {
            return ((Symbol)o).pname.equals("true");
        }
        error(lContext.cfun + " doesn't like " + prs(o) + " as input", lContext);
        return false;
    }
    
    static Object[] aList2Double(final Object o, final LContext lContext) {
        if (o instanceof Object[]) {
            if (((Object[])o).length == 2 && ((Object[])o)[0] instanceof Double && ((Object[])o)[1] instanceof Double) {
                return (Object[])o;
            }
            error(lContext.cfun + " doesn't like " + prs(o) + " as input", lContext);
        }
        return null;
    }
    
    static Object[] aList(final Object o, final LContext lContext) {
        if (o instanceof Object[]) {
            return (Object[])o;
        }
        error(lContext.cfun + " doesn't like " + prs(o) + " as input", lContext);
        return null;
    }
    
    static Symbol aSymbol(final Object o, final LContext lContext) {
        if (o instanceof Symbol) {
            return (Symbol)o;
        }
        if (o instanceof String) {
            return intern((String)o, lContext);
        }
        if (o instanceof Number) {
            return intern(String.valueOf(((Number)o).longValue()), lContext);
        }
        error(lContext.cfun + " doesn't like " + prs(o) + " as input", lContext);
        return null;
    }
    
    static String aString(final Object o, final LContext lContext) {
        if (o instanceof String) {
            return (String)o;
        }
        if (o instanceof Symbol) {
            return ((Symbol)o).toString();
        }
        error(lContext.cfun + " doesn't like " + prs(o) + " as input", lContext);
        return null;
    }
    
    static void setupPrims(final String[] array, final LContext lContext) {
        for (int i = 0; i < array.length; ++i) {
            setupPrims(array[i], lContext);
        }
    }
    
    static void setupPrims(final String s, final LContext lContext) {
        try {
            final Primitives primitives = (Primitives)Class.forName(s).newInstance();
            final String[] primlist = primitives.primlist();
            for (int i = 0; i < primlist.length; i += 2) {
                String substring = primlist[i + 1];
                final boolean startsWith = substring.startsWith("i");
                if (startsWith) {
                    substring = substring.substring(1);
                }
                intern(primlist[i], lContext).fcn = new Function(primitives, Integer.parseInt(substring), i / 2, startsWith);
            }
        }
        catch (Exception ex) {
            System.out.println(ex.toString());
        }
    }
    
    static void checkListEmpty(final MapList list, final LContext lContext) {
        if (!list.eof() && lContext.ufunresult == null) {
            error("You don't say what to do with " + prs(list.next()), lContext);
        }
    }
    
    static void errorHandler(final Symbol symbol, final Object[] array, final RuntimeException ex, final LContext lContext) {
        if (ex instanceof ArrayIndexOutOfBoundsException || ex instanceof StringIndexOutOfBoundsException || ex instanceof NegativeArraySizeException) {
            error(symbol + " doesn't like " + prs(array[0]) + " as input", lContext);
            return;
        }
        throw ex;
    }
    
    static void error(String string, final LContext lContext) {
        if (string.equals("")) {
            throw new LogoError((String)null);
        }
        string += ((lContext.ufun == null) ? "" : (" in " + lContext.ufun));
        throw new LogoError(string);
    }
    
    static void readAllFunctions(final String s, final LContext lContext) {
        final TokenStream tokenStream = new TokenStream(s);
    Label_0040:
        while (true) {
            switch (findKeyWord(tokenStream)) {
                case 0: {
                    break Label_0040;
                }
                case 1: {
                    doDefine(tokenStream, lContext);
                    continue;
                }
                case 2: {
                    doTo(tokenStream, lContext);
                    continue;
                }
            }
        }
    }
    
    static int findKeyWord(final TokenStream tokenStream) {
        while (!tokenStream.eof()) {
            if (tokenStream.startsWith("define ")) {
                return 1;
            }
            if (tokenStream.startsWith("to ")) {
                return 2;
            }
            tokenStream.skipToNextLine();
        }
        return 0;
    }
    
    static void doDefine(final TokenStream tokenStream, final LContext lContext) {
        tokenStream.readToken(lContext);
        final Symbol aSymbol = aSymbol(tokenStream.readToken(lContext), lContext);
        final Object[] aList = aList(tokenStream.readToken(lContext), lContext);
        aSymbol.fcn = new Function(new Ufun(aList, aList(tokenStream.readToken(lContext), lContext)), aList.length, 0);
    }
    
    static void doTo(final TokenStream tokenStream, final LContext lContext) {
        final Object[] parse = parse(tokenStream.nextLine(), lContext);
        final Object[] parse2 = parse(readBody(tokenStream, lContext), lContext);
        final Object[] arglistFromTitle = getArglistFromTitle(parse);
        aSymbol(parse[1], lContext).fcn = new Function(new Ufun(arglistFromTitle, parse2), arglistFromTitle.length, 0);
    }
    
    static String readBody(final TokenStream tokenStream, final LContext lContext) {
        String string = "";
        while (!tokenStream.eof()) {
            final String nextLine = tokenStream.nextLine();
            if (nextLine.startsWith("end") && "end".equals(((Symbol)parse(nextLine, lContext)[0]).pname)) {
                return string;
            }
            string = string + " " + nextLine;
        }
        return string;
    }
    
    static Object[] getArglistFromTitle(final Object[] array) {
        final Object[] array2 = new Object[array.length - 2];
        for (int i = 0; i < array2.length; ++i) {
            array2[i] = ((DottedSymbol)array[i + 2]).sym;
        }
        return array2;
    }
    
    static {
        Logo.starttime = System.currentTimeMillis();
    }
}

// 
// Decompiled by Procyon v0.5.30
// 

package xfunctions;

import xfunctions.functions.Function;
import xfunctions.functions.Expression;
import xfunctions.functions.Variable;
import xfunctions.functions.MathSymbol;
import xfunctions.functions.BezierFunction;
import xfunctions.functions.TableFunction;
import xfunctions.functions.ExpressionFunction;
import xfunctions.functions.ParseError;
import java.util.StringTokenizer;
import java.net.URL;
import java.io.IOException;
import java.io.InputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.util.Vector;
import xfunctions.functions.Parser;
import java.applet.Applet;

class Examples
{
    static Object[][] getExamples(final String s, final Applet applet, final Parser parser, final FunctionList list) {
        Object o = null;
        final Vector vector = new Vector();
        try {
            System.out.println("Trying to get examples...");
            if (s != null) {
                System.out.println("Reading example file " + s);
                DataInputStream dataInputStream = null;
                try {
                    dataInputStream = new DataInputStream(new FileInputStream(s));
                }
                catch (IOException ex2) {}
                if (dataInputStream == null) {
                    System.out.println(">>> Couldn't open example file");
                }
                else {
                    processStream(dataInputStream, vector, parser, list);
                }
            }
            String parameter = null;
            if (applet != null) {
                try {
                    parameter = applet.getParameter("file");
                }
                catch (Exception ex3) {
                    System.out.println("Can't get applet parameters.  Is there an applet context?");
                    parameter = null;
                }
            }
            if (applet != null && parameter != null) {
                try {
                    final URL url = new URL(applet.getDocumentBase(), parameter);
                    System.out.println("Reading examples from url " + url.toString());
                    processStream(new DataInputStream(url.openStream()), vector, parser, list);
                }
                catch (Exception ex4) {
                    System.out.println("Can't open data stream for specified url file name, " + parameter);
                }
            }
            if (applet != null) {
                processAppletParams(applet, vector, parser, list);
            }
            if (vector.size() > 0) {
                o = new Object[vector.size()][];
                vector.copyInto((Object[])o);
            }
        }
        catch (Exception ex) {
            System.out.println(">>> Unexpected error: " + ex.toString());
            System.out.println(">>> Further processing of example input aborted.");
        }
        return (Object[][])o;
    }
    
    private static void processAppletParams(final Applet applet, final Vector vector, final Parser parser, final FunctionList list) {
        String s = applet.getParameter("1");
        if (s == null) {
            return;
        }
        System.out.println("Reading applet parameters...");
        int n = 1;
        while (s != null) {
            String string = s;
            while (true) {
                ++n;
                s = applet.getParameter(String.valueOf(n));
                if (s == null) {
                    break;
                }
                s = s.trim();
                if (s.length() <= 0 || s.charAt(0) != '+') {
                    break;
                }
                string = String.valueOf(string) + s.substring(1);
            }
            try {
                System.out.println("Extracting example from " + string);
                final Object[] oneExample = getOneExample(string, parser, list);
                if (oneExample == null) {
                    continue;
                }
                vector.addElement(oneExample);
            }
            catch (IllegalArgumentException ex) {
                System.out.println("Illegal data:  " + ex.getMessage());
            }
            catch (Exception ex2) {
                System.out.println(">>> Error: " + ex2.toString());
                ex2.printStackTrace();
            }
        }
    }
    
    private static void processStream(final DataInputStream dataInputStream, final Vector vector, final Parser parser, final FunctionList list) throws IOException {
        String s = dataInputStream.readLine();
        if (s != null) {
            s = s.trim();
        }
        int n = 0;
        while (s != null) {
            String string = s;
            while (true) {
                s = dataInputStream.readLine();
                if (s == null) {
                    break;
                }
                s = s.trim();
                if (s.length() <= 0) {
                    continue;
                }
                if (s.charAt(0) != '+') {
                    break;
                }
                string = String.valueOf(string) + s.substring(1);
            }
            try {
                System.out.println("Extracting example from " + string);
                final Object[] oneExample = getOneExample(string, parser, list);
                if (oneExample != null) {
                    vector.addElement(oneExample);
                }
                n = 0;
            }
            catch (IllegalArgumentException ex) {
                System.out.println("Illegal data:  " + ex.getMessage());
                ++n;
            }
            catch (Exception ex2) {
                System.out.println(">>> Error: " + ex2.toString());
                ++n;
            }
            if (n >= 10) {
                System.out.println(">>> 10 successive errors in file; input aborted <<<");
            }
        }
    }
    
    private static Object[] getOneExample(final String s, final Parser parser, final FunctionList list) {
        final StringTokenizer stringTokenizer = new StringTokenizer(s, ";");
        final int countTokens = stringTokenizer.countTokens();
        if (countTokens < 4) {
            throw new IllegalArgumentException("Can't be a legal example:  Any valid example has at least 4 data items.");
        }
        final int exampleType = getExampleType(stringTokenizer.nextToken());
        if (exampleType == 0) {
            extractNewFunction(stringTokenizer, parser, list);
            return null;
        }
        final Vector vector = new Vector<Integer>();
        vector.addElement(new Integer(exampleType));
        vector.addElement((Integer)stringTokenizer.nextToken().trim());
        final int n = countTokens - 3;
        final Parser parser2 = new Parser(parser);
        switch (exampleType) {
            case 1: {
                vector.addElement((Integer)(Object)getLimits(stringTokenizer.nextToken(), null, 0, 0));
                if (n > 8) {
                    throw new IllegalArgumentException("Too many functions for Multigraph; maximum is eight.");
                }
                final String[] array = new String[n];
                parser2.defineVariable("x");
                for (int i = 0; i < n; ++i) {
                    array[i] = getExpression(stringTokenizer.nextToken(), parser2);
                }
                vector.addElement((Integer)(Object)array);
                break;
            }
            case 2: {
                vector.addElement((Integer)(Object)getLimits(stringTokenizer.nextToken(), "", 1, 1000));
                parser2.defineVariable("x");
                parser2.defineVariable("k");
                vector.addElement((Integer)getExpression(stringTokenizer.nextToken(), parser2));
                vector.addElement((Integer)(Object)getBoolean(stringTokenizer.nextToken()));
                break;
            }
            case 3: {
                vector.addElement((Integer)(Object)getLimits(stringTokenizer.nextToken(), "t", 2, 1000));
                if (n > 16) {
                    throw new IllegalArgumentException("Too many functions for Parametric curves; maximum is 16.");
                }
                if (n % 2 == 1) {
                    throw new IllegalArgumentException("Can't have an odd number of functions for Parametric Curves.");
                }
                final String[][] array2 = new String[n / 2][2];
                parser2.defineVariable("t");
                for (int j = 0; j < n / 2; ++j) {
                    array2[j][0] = getExpression(stringTokenizer.nextToken(), parser2);
                    array2[j][1] = getExpression(stringTokenizer.nextToken(), parser2);
                }
                vector.addElement((Integer)(Object)array2);
                break;
            }
            case 4: {
                vector.addElement((Integer)(Object)getLimits(stringTokenizer.nextToken(), null, 0, 0));
                parser2.defineVariable("x");
                vector.addElement((Integer)getExpression(stringTokenizer.nextToken(), parser2));
                break;
            }
            case 5: {
                vector.addElement((Integer)(Object)getLimits(stringTokenizer.nextToken(), null, 1, 512));
                parser2.defineVariable("x");
                vector.addElement((Integer)getExpression(stringTokenizer.nextToken(), parser2));
                vector.addElement(getInt(stringTokenizer.nextToken(), "Display style number", 1, 6));
                break;
            }
            case 6: {
                vector.addElement((Integer)(Object)getLimits(stringTokenizer.nextToken(), "", 0, 1));
                parser2.defineVariable("x");
                parser2.defineVariable("y");
                vector.addElement((Integer)getExpression(stringTokenizer.nextToken(), parser2));
                vector.addElement((Integer)getExpression(stringTokenizer.nextToken(), parser2));
                vector.addElement(getInt(stringTokenizer.nextToken(), "Integration method number", 1, 3));
                vector.addElement((Integer)(Object)getBoolean(stringTokenizer.nextToken()));
                final int countTokens2 = stringTokenizer.countTokens();
                final double[][] array3 = new double[countTokens2][];
                for (int k = 0; k < countTokens2; ++k) {
                    array3[k] = getNums(stringTokenizer.nextToken(), 2);
                }
                vector.addElement((Integer)(Object)array3);
                break;
            }
            case 7: {
                vector.addElement((Integer)(Object)getLimits(stringTokenizer.nextToken(), "z", 8, 64));
                parser2.defineVariable("x");
                parser2.defineVariable("y");
                vector.addElement((Integer)getExpression(stringTokenizer.nextToken(), parser2));
                vector.addElement(getInt(stringTokenizer.nextToken(), "Rendering method", 1, 4));
                break;
            }
        }
        final Object[] array4 = new Object[vector.size()];
        vector.copyInto(array4);
        return array4;
    }
    
    private static void extractNewFunction(final StringTokenizer stringTokenizer, final Parser parser, final FunctionList list) {
        final double[] limits = getLimits(stringTokenizer.nextToken(), null, 0, 0);
        final String trim = stringTokenizer.nextToken().trim();
        final String trim2 = stringTokenizer.nextToken().trim();
        if (trim2 == null || trim2.length() == 0) {
            throw new IllegalArgumentException("No name provided for function");
        }
        if (parser.getSymbol(trim2) != null) {
            throw new IllegalArgumentException("The name \"" + trim2 + "\" is already in use.");
        }
        if (!Character.isLetter(trim2.charAt(0))) {
            throw new IllegalArgumentException("Not a legal name for a function: " + trim2);
        }
        for (int i = 1; i < trim2.length(); ++i) {
            if (!Character.isLetter(trim2.charAt(i)) && !Character.isDigit(trim2.charAt(i))) {
                throw new IllegalArgumentException("Not a legal name for a function: " + trim2);
            }
        }
        final int countTokens = stringTokenizer.countTokens();
        if (countTokens == 0) {
            throw new IllegalArgumentException("No defining data provided for function " + trim2);
        }
        Function function = null;
        switch (Character.toLowerCase(trim.charAt(0))) {
            case 'e': {
                if (countTokens > 8) {
                    throw new IllegalArgumentException("Too many sub-expressions in expression function; maximum of 8.");
                }
                final String[] definitionStrings = new String[8];
                for (int j = 0; j < countTokens; ++j) {
                    definitionStrings[j] = stringTokenizer.nextToken();
                }
                for (int k = countTokens; k < 8; ++k) {
                    definitionStrings[k] = "";
                }
                final Parser parser2 = new Parser(parser);
                final Variable defineVariable = parser2.defineVariable("x");
                Expression expressionWithCases;
                try {
                    expressionWithCases = parser2.parseExpressionWithCases(definitionStrings);
                }
                catch (ParseError parseError) {
                    throw new IllegalArgumentException("Parse error at " + parseError.errorPosition + " in string number " + parseError.errorInStringNumber + ": " + parseError.getMessage());
                }
                function = new ExpressionFunction(trim2, defineVariable, expressionWithCases);
                ((ExpressionFunction)function).definitionStrings = definitionStrings;
                break;
            }
            case 't': {
                final Integer int1 = getInt(stringTokenizer.nextToken(), "Table function style number", 1, 3);
                final double[][] array = new double[countTokens - 1][];
                if (countTokens < 3) {
                    throw new IllegalArgumentException("Not enough data for Table functions; needs at least two points.");
                }
                for (int l = 0; l < countTokens - 1; ++l) {
                    array[l] = getNums(stringTokenizer.nextToken(), 2);
                }
                for (int n = 1; n < countTokens - 1; ++n) {
                    if (array[n - 1][0] >= array[n][0]) {
                        throw new IllegalArgumentException("X-values for Table function not in increasing order.");
                    }
                }
                function = new TableFunction(trim2, int1 - 1, array);
                break;
            }
            case 'g': {
                if (countTokens < 2) {
                    throw new IllegalArgumentException("Not enough data for Bezier functions; needs at least two segments.");
                }
                final double[][] array2 = new double[countTokens][6];
                for (int n2 = 0; n2 < countTokens; ++n2) {
                    array2[n2] = getNums(stringTokenizer.nextToken(), 6);
                }
                for (int n3 = 0; n3 < countTokens; ++n3) {
                    if (array2[n3][0] >= array2[n3][3]) {
                        throw new IllegalArgumentException("Left endpoint of segment " + (n3 + 1) + " comes after the right endpoint!.");
                    }
                    if (n3 > 0 && array2[n3 - 1][3] != array2[n3][0]) {
                        throw new IllegalArgumentException("Segment endpoints don't match up between segments " + n3 + " and " + (n3 + 1));
                    }
                }
                function = new BezierFunction(trim2, array2);
                break;
            }
            default: {
                throw new IllegalArgumentException("Specified function type is not Graph, Table or Expression.");
            }
        }
        function.preferredXmin = limits[0];
        function.preferredXmax = limits[1];
        function.preferredYmin = limits[2];
        function.preferredYmax = limits[3];
        parser.getSymbolTable().add(function);
        list.addFunction(function);
    }
    
    private static double[] getNums(final String s, final int n) {
        final StringTokenizer stringTokenizer = new StringTokenizer(s, ",");
        final int countTokens = stringTokenizer.countTokens();
        if (countTokens == 0) {
            return null;
        }
        if (countTokens != n) {
            throw new IllegalArgumentException("Expected " + n + " numbers, but found " + s);
        }
        final double[] array = new double[countTokens];
        for (int i = 0; i < countTokens; ++i) {
            final String trim = stringTokenizer.nextToken().trim();
            Double n2;
            try {
                n2 = new Double(trim);
                if (n2.isInfinite() || n2.isNaN()) {
                    n2 = null;
                }
            }
            catch (NumberFormatException ex) {
                n2 = null;
            }
            if (n2 == null) {
                throw new IllegalArgumentException("Illegal number found in " + s);
            }
            array[i] = n2;
        }
        return array;
    }
    
    private static double[] getLimits(final String s, final String s2, final int n, final int n2) {
        int n3 = (s2 == null) ? 4 : 6;
        if (n != n2) {
            ++n3;
        }
        final double[] nums = getNums(s, n3);
        if (nums[1] <= nums[0]) {
            throw new IllegalArgumentException("The limits xmin,xmax don't satisfy xmax > xmin.");
        }
        if (nums[3] <= nums[2]) {
            throw new IllegalArgumentException("The limits ymin,ymax don't satisfy ymax > ymin.");
        }
        if (s2 != null && s2.length() != 0 && nums[5] <= nums[4]) {
            throw new IllegalArgumentException("The limits don't satisfy " + s2 + "max > " + s2 + "min.");
        }
        if (n != n2) {
            if (n2 == 1) {
                if (nums[n3 - 1] <= 0.0) {
                    throw new IllegalArgumentException("Need a number greater than zero, instead of " + nums[n3 - 1]);
                }
            }
            else {
                final int n4 = (int)Math.round(nums[n3 - 1]);
                if (n4 < n || n4 > n2) {
                    throw new IllegalArgumentException("Need an integers between " + n + " and " + n2 + " instead of " + nums[n3 - 1]);
                }
                nums[n3 - 1] = n4;
            }
        }
        return nums;
    }
    
    private static int getExampleType(final String s) {
        switch (Character.toLowerCase(s.trim().charAt(0))) {
            case 'f': {
                return 0;
            }
            case 'm': {
                return 1;
            }
            case 'a': {
                return 2;
            }
            case 'p': {
                return 3;
            }
            case 'd': {
                return 4;
            }
            case 'r': {
                return 5;
            }
            case 'i': {
                return 6;
            }
            case 'g': {
                return 7;
            }
            default: {
                throw new IllegalArgumentException("Unknown example type: " + s);
            }
        }
    }
    
    private static Integer getInt(final String s, final String s2, final int n, final int n2) {
        Integer n3;
        try {
            n3 = new Integer(s.trim());
        }
        catch (Exception ex) {
            throw new IllegalArgumentException("Not a legal integer: " + s);
        }
        if (n < n2 && (n3 < n || n3 > n2)) {
            throw new IllegalArgumentException(String.valueOf(s2) + " out of legal range " + n + " to " + n2);
        }
        return n3;
    }
    
    private static Boolean getBoolean(final String s) {
        final char lowerCase = Character.toLowerCase(s.trim().charAt(0));
        if (lowerCase == 't') {
            return new Boolean(true);
        }
        if (lowerCase == 'f') {
            return new Boolean(false);
        }
        throw new IllegalArgumentException("Expected True or False instead of " + s);
    }
    
    private static String getExpression(final String s, final Parser parser) {
        try {
            parser.parse(s);
        }
        catch (ParseError parseError) {
            throw new IllegalArgumentException("Parser error in string \"" + s + "\": " + parseError.getMessage());
        }
        return s;
    }
}

// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.regexp;

import java.io.InputStreamReader;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.io.OutputStream;
import java.io.PrintWriter;

public class RETest
{
    RE r;
    REDebugCompiler compiler;
    static final boolean showSuccesses = false;
    char[] re1Instructions;
    REProgram re1;
    String expr;
    int n;
    int failures;
    
    public RETest() {
        this.r = new RE();
        this.compiler = new REDebugCompiler();
        this.re1Instructions = new char[] { '|', '\0', '\u001a', '|', '\0', '\r', 'A', '\u0001', '\u0004', 'a', '|', '\0', '\u0003', 'G', '\0', '\ufff6', '|', '\0', '\u0003', 'N', '\0', '\u0003', 'A', '\u0001', '\u0004', 'b', 'E', '\0', '\0' };
        this.re1 = new REProgram(this.re1Instructions);
        this.n = 0;
        this.failures = 0;
    }
    
    public RETest(final String[] array) {
        this.r = new RE();
        this.compiler = new REDebugCompiler();
        this.re1Instructions = new char[] { '|', '\0', '\u001a', '|', '\0', '\r', 'A', '\u0001', '\u0004', 'a', '|', '\0', '\u0003', 'G', '\0', '\ufff6', '|', '\0', '\u0003', 'N', '\0', '\u0003', 'A', '\u0001', '\u0004', 'b', 'E', '\0', '\0' };
        this.re1 = new REProgram(this.re1Instructions);
        this.n = 0;
        this.failures = 0;
        try {
            if (array.length == 2) {
                this.runInteractiveTests(array[1]);
            }
            else if (array.length == 1) {
                this.runAutomatedTests(array[0]);
            }
            else {
                System.out.println("Usage: RETest ([-i] [regex]) ([/path/to/testfile.txt])");
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    void die(final String s) {
        this.say("FATAL ERROR: " + s);
        System.exit(0);
    }
    
    void fail(final String s) {
        ++this.failures;
        this.say("\n");
        this.say("*******************************************************");
        this.say("*********************  FAILURE!  **********************");
        this.say("*******************************************************");
        this.say("\n");
        this.say(s);
        this.say("");
        this.compiler.dumpProgram(new PrintWriter(System.out));
        this.say("\n");
    }
    
    public static void main(final String[] array) {
        try {
            test();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    void runAutomatedTests(final String s) throws Exception {
        final long currentTimeMillis = System.currentTimeMillis();
        final RE re = new RE(this.re1);
        this.say("a*b");
        this.say("aaaab = " + re.match("aaab"));
        this.showParens(re);
        this.say("b = " + re.match("b"));
        this.showParens(re);
        this.say("c = " + re.match("c"));
        this.showParens(re);
        this.say("ccccaaaaab = " + re.match("ccccaaaaab"));
        this.showParens(re);
        final String[] grep = new RE("x+").grep(new RE("a*b").split("xxxxaabxxxxbyyyyaaabzzz"));
        for (int i = 0; i < grep.length; ++i) {
            System.out.println("s[" + i + "] = " + grep[i]);
        }
        final RE re2 = new RE("a*b");
        System.out.println("s = " + re2.subst("aaaabfooaaabgarplyaaabwackyb", "-"));
        final File file = new File(s);
        if (!file.exists()) {
            throw new Exception("Could not find: " + s);
        }
        final BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        try {
            while (bufferedReader.ready()) {
                while (bufferedReader.ready()) {
                    final String line = bufferedReader.readLine();
                    if (line == null) {
                        break;
                    }
                    final String trim = line.trim();
                    if (trim.startsWith("#")) {
                        break;
                    }
                    if (trim.equals("")) {
                        continue;
                    }
                    System.out.println("Script error.  Line = " + trim);
                    System.exit(0);
                }
                if (!bufferedReader.ready()) {
                    break;
                }
                this.expr = bufferedReader.readLine();
                ++this.n;
                this.say("");
                this.say(String.valueOf(this.n) + ". " + this.expr);
                this.say("");
                try {
                    re2.setProgram(this.compiler.compile(this.expr));
                }
                catch (Exception ex) {
                    if (bufferedReader.readLine().trim().equals("ERR")) {
                        this.say("   Match: ERR");
                        this.success("Produces an error (" + ex.toString() + "), as expected.");
                        continue;
                    }
                    this.fail("Produces the unexpected error \"" + ex.getMessage() + "\"");
                }
                catch (Error error) {
                    this.fail("Compiler threw fatal error \"" + error.getMessage() + "\"");
                    error.printStackTrace();
                }
                final String trim2 = bufferedReader.readLine().trim();
                this.say("   Match against: '" + trim2 + "'");
                if (trim2.equals("ERR")) {
                    this.fail("Was expected to be an error, but wasn't.");
                }
                else {
                    try {
                        final boolean match = re2.match(trim2);
                        final String trim3 = bufferedReader.readLine().trim();
                        if (match) {
                            this.say("   Match: YES");
                            if (trim3.equals("NO")) {
                                this.fail("Matched \"" + trim2 + "\", when not expected to.");
                            }
                            else if (trim3.equals("YES")) {
                                this.success("Matched \"" + trim2 + "\", as expected:");
                                this.say("   Paren count: " + re2.getParenCount());
                                for (int j = 0; j < re2.getParenCount(); ++j) {
                                    final String trim4 = bufferedReader.readLine().trim();
                                    this.say("   Paren " + j + " : " + re2.getParen(j));
                                    if (!trim4.equals(re2.getParen(j))) {
                                        this.fail("Register " + j + " should be = \"" + trim4 + "\", but is \"" + re2.getParen(j) + "\" instead.");
                                    }
                                }
                            }
                            else {
                                this.die("Test script error!");
                            }
                        }
                        else {
                            this.say("   Match: NO");
                            if (trim3.equals("YES")) {
                                this.fail("Did not match \"" + trim2 + "\", when expected to.");
                            }
                            else if (trim3.equals("NO")) {
                                this.success("Did not match \"" + trim2 + "\", as expected.");
                            }
                            else {
                                this.die("Test script error!");
                            }
                        }
                    }
                    catch (Exception ex2) {
                        this.fail("Matcher threw exception: " + ex2.toString());
                        ex2.printStackTrace();
                    }
                    catch (Error error2) {
                        this.fail("Matcher threw fatal error \"" + error2.getMessage() + "\"");
                        error2.printStackTrace();
                    }
                }
            }
        }
        finally {
            bufferedReader.close();
        }
        System.out.println("\n\nMatch time = " + (System.currentTimeMillis() - currentTimeMillis) + " ms.");
        System.out.println("\nTests complete.  " + this.n + " tests, " + this.failures + " failure(s).");
    }
    
    void runInteractiveTests(final String s) {
        try {
            this.r.setProgram(this.compiler.compile(s));
            this.say("\n" + s + "\n");
            this.compiler.dumpProgram(new PrintWriter(System.out));
            while (true) {
                final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
                System.out.print("> ");
                System.out.flush();
                if (this.r.match(bufferedReader.readLine())) {
                    this.say("Match successful.");
                }
                else {
                    this.say("Match failed.");
                }
                this.showParens(this.r);
            }
        }
        catch (Exception ex) {
            this.say("Error: " + ex.toString());
            ex.printStackTrace();
        }
    }
    
    void say(final String s) {
        System.out.println(s);
    }
    
    void show() {
        this.say("\n-----------------------\n");
        this.say("Expression #" + this.n + " \"" + this.expr + "\" ");
    }
    
    void showParens(final RE re) {
        for (int i = 0; i < re.getParenCount(); ++i) {
            this.say("$" + i + " = " + re.getParen(i));
        }
    }
    
    void success(final String s) {
    }
    
    public static boolean test() throws Exception {
        final RETest reTest = new RETest();
        reTest.runAutomatedTests("docs/RETest.txt");
        return reTest.failures == 0;
    }
}

// 
// Decompiled by Procyon v0.5.30
// 

package com.ibm.xslt4j.regexp;

import java.io.FileReader;
import java.io.File;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
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
    
    public static void main(final String[] arg) {
        try {
            test();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static boolean test() throws Exception {
        final RETest test = new RETest();
        test.runAutomatedTests("docs/RETest.txt");
        return test.failures == 0;
    }
    
    public RETest() {
        this.r = new RE();
        this.compiler = new REDebugCompiler();
        this.re1Instructions = new char[] { '|', '\0', '\u001a', '|', '\0', '\r', 'A', '\u0001', '\u0004', 'a', '|', '\0', '\u0003', 'G', '\0', '\ufff6', '|', '\0', '\u0003', 'N', '\0', '\u0003', 'A', '\u0001', '\u0004', 'b', 'E', '\0', '\0' };
        this.re1 = new REProgram(this.re1Instructions);
        this.n = 0;
        this.failures = 0;
    }
    
    public RETest(final String[] arg) {
        this.r = new RE();
        this.compiler = new REDebugCompiler();
        this.re1Instructions = new char[] { '|', '\0', '\u001a', '|', '\0', '\r', 'A', '\u0001', '\u0004', 'a', '|', '\0', '\u0003', 'G', '\0', '\ufff6', '|', '\0', '\u0003', 'N', '\0', '\u0003', 'A', '\u0001', '\u0004', 'b', 'E', '\0', '\0' };
        this.re1 = new REProgram(this.re1Instructions);
        this.n = 0;
        this.failures = 0;
        try {
            if (arg.length == 2) {
                this.runInteractiveTests(arg[1]);
            }
            else if (arg.length == 1) {
                this.runAutomatedTests(arg[0]);
            }
            else {
                System.out.println("Usage: RETest ([-i] [regex]) ([/path/to/testfile.txt])");
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    void runInteractiveTests(final String expr) {
        try {
            this.r.setProgram(this.compiler.compile(expr));
            this.say("\n" + expr + "\n");
            this.compiler.dumpProgram(new PrintWriter(System.out));
            while (true) {
                final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                System.out.print("> ");
                System.out.flush();
                final String match = br.readLine();
                if (this.r.match(match)) {
                    this.say("Match successful.");
                }
                else {
                    this.say("Match failed.");
                }
                this.showParens(this.r);
            }
        }
        catch (Exception e) {
            this.say("Error: " + e.toString());
            e.printStackTrace();
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
    
    void success(final String s) {
    }
    
    void say(final String s) {
        System.out.println(s);
    }
    
    void show() {
        this.say("\n-----------------------\n");
        this.say("Expression #" + this.n + " \"" + this.expr + "\" ");
    }
    
    void showParens(final RE r) {
        for (int i = 0; i < r.getParenCount(); ++i) {
            this.say("$" + i + " = " + r.getParen(i));
        }
    }
    
    void runAutomatedTests(final String testDocument) throws Exception {
        final long ms = System.currentTimeMillis();
        RE r = new RE(this.re1);
        this.say("a*b");
        this.say("aaaab = " + r.match("aaab"));
        this.showParens(r);
        this.say("b = " + r.match("b"));
        this.showParens(r);
        this.say("c = " + r.match("c"));
        this.showParens(r);
        this.say("ccccaaaaab = " + r.match("ccccaaaaab"));
        this.showParens(r);
        r = new RE("a*b");
        String[] s = r.split("xxxxaabxxxxbyyyyaaabzzz");
        r = new RE("x+");
        s = r.grep(s);
        for (int i = 0; i < s.length; ++i) {
            System.out.println("s[" + i + "] = " + s[i]);
        }
        r = new RE("a*b");
        final String s2 = r.subst("aaaabfooaaabgarplyaaabwackyb", "-");
        System.out.println("s = " + s2);
        final File testInput = new File(testDocument);
        if (!testInput.exists()) {
            throw new Exception("Could not find: " + testDocument);
        }
        final BufferedReader br = new BufferedReader(new FileReader(testInput));
        try {
            while (br.ready()) {
                String number = "";
                while (br.ready()) {
                    number = br.readLine();
                    if (number == null) {
                        break;
                    }
                    number = number.trim();
                    if (number.startsWith("#")) {
                        break;
                    }
                    if (number.equals("")) {
                        continue;
                    }
                    System.out.println("Script error.  Line = " + number);
                    System.exit(0);
                }
                if (!br.ready()) {
                    break;
                }
                this.expr = br.readLine();
                ++this.n;
                this.say("");
                this.say(String.valueOf(this.n) + ". " + this.expr);
                this.say("");
                try {
                    r.setProgram(this.compiler.compile(this.expr));
                }
                catch (Exception e) {
                    final String yesno = br.readLine().trim();
                    if (yesno.equals("ERR")) {
                        this.say("   Match: ERR");
                        this.success("Produces an error (" + e.toString() + "), as expected.");
                        continue;
                    }
                    this.fail("Produces the unexpected error \"" + e.getMessage() + "\"");
                }
                catch (Error e2) {
                    this.fail("Compiler threw fatal error \"" + e2.getMessage() + "\"");
                    e2.printStackTrace();
                }
                final String matchAgainst = br.readLine().trim();
                this.say("   Match against: '" + matchAgainst + "'");
                if (matchAgainst.equals("ERR")) {
                    this.fail("Was expected to be an error, but wasn't.");
                }
                else {
                    try {
                        final boolean b = r.match(matchAgainst);
                        final String yesno = br.readLine().trim();
                        if (b) {
                            this.say("   Match: YES");
                            if (yesno.equals("NO")) {
                                this.fail("Matched \"" + matchAgainst + "\", when not expected to.");
                            }
                            else if (yesno.equals("YES")) {
                                this.success("Matched \"" + matchAgainst + "\", as expected:");
                                this.say("   Paren count: " + r.getParenCount());
                                for (int p = 0; p < r.getParenCount(); ++p) {
                                    final String register = br.readLine().trim();
                                    this.say("   Paren " + p + " : " + r.getParen(p));
                                    if (!register.equals(r.getParen(p))) {
                                        this.fail("Register " + p + " should be = \"" + register + "\", but is \"" + r.getParen(p) + "\" instead.");
                                    }
                                }
                            }
                            else {
                                this.die("Test script error!");
                            }
                        }
                        else {
                            this.say("   Match: NO");
                            if (yesno.equals("YES")) {
                                this.fail("Did not match \"" + matchAgainst + "\", when expected to.");
                            }
                            else if (yesno.equals("NO")) {
                                this.success("Did not match \"" + matchAgainst + "\", as expected.");
                            }
                            else {
                                this.die("Test script error!");
                            }
                        }
                    }
                    catch (Exception e3) {
                        this.fail("Matcher threw exception: " + e3.toString());
                        e3.printStackTrace();
                    }
                    catch (Error e4) {
                        this.fail("Matcher threw fatal error \"" + e4.getMessage() + "\"");
                        e4.printStackTrace();
                    }
                }
            }
        }
        finally {
            br.close();
        }
        br.close();
        System.out.println("\n\nMatch time = " + (System.currentTimeMillis() - ms) + " ms.");
        System.out.println("\nTests complete.  " + this.n + " tests, " + this.failures + " failure(s).");
    }
}

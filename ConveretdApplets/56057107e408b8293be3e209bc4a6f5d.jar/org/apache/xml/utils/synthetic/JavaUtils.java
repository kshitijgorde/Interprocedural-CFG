// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xml.utils.synthetic;

import java.io.IOException;
import java.io.OutputStream;
import sun.tools.javac.Main;

public class JavaUtils
{
    private static boolean cantLoadCompiler;
    private static boolean debug;
    
    static {
        JavaUtils.cantLoadCompiler = false;
        JavaUtils.debug = false;
    }
    
    public static boolean JDKcompile(final String fileName, String classPath) {
        final String moreClassPath = System.getProperty("org.apache.xml.utils.synthetic.moreclasspath", "").trim();
        if (moreClassPath.length() > 0) {
            classPath = String.valueOf(moreClassPath) + ';' + classPath;
        }
        if (JavaUtils.debug) {
            System.err.println("JavaEngine: Compiling " + fileName);
            System.err.println("JavaEngine: Classpath is " + classPath);
        }
        final String code_option = JavaUtils.debug ? "-g" : "-O";
        if (!JavaUtils.cantLoadCompiler) {
            final String[] args = { code_option, "-classpath", classPath, fileName };
            try {
                return new Main((OutputStream)System.err, "javac").compile(args);
            }
            catch (Throwable t) {
                System.err.println("INFORMATIONAL: Unable to load Java compiler API (eg tools.jar).");
                System.err.println("\tSwitching to command-line invocation.");
                JavaUtils.cantLoadCompiler = true;
            }
        }
        final String javac_command = System.getProperty("org.apache.xml.utils.synthetic.javac", "javac");
        final String[] args2 = { javac_command, code_option, "-classpath", classPath, fileName };
        try {
            final Process p = Runtime.getRuntime().exec(args2);
            final int compileOK = waitHardFor(p);
            return compileOK == 0;
        }
        catch (IOException ex) {
            System.err.println("ERROR: IO exception during exec(javac).");
        }
        catch (SecurityException ex2) {
            System.err.println("ERROR: Unable to create subprocess to exec(javac).");
        }
        return false;
    }
    
    public static void setDebug(final boolean newDebug) {
        JavaUtils.debug = newDebug;
    }
    
    static int waitHardFor(final Process p) {
        boolean done = false;
        while (!done) {
            try {
                p.waitFor();
                done = true;
            }
            catch (InterruptedException ex) {
                System.err.println("(Compiler process wait interrupted and resumed)");
            }
        }
        final int ev = p.exitValue();
        return ev;
    }
}

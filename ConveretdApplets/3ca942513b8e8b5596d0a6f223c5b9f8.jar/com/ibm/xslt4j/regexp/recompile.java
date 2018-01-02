// 
// Decompiled by Procyon v0.5.30
// 

package com.ibm.xslt4j.regexp;

public class recompile
{
    public static void main(final String[] arg) {
        final RECompiler r = new RECompiler();
        if (arg.length <= 0 || arg.length % 2 != 0) {
            System.out.println("Usage: recompile <patternname> <pattern>");
            System.exit(0);
        }
        for (int i = 0; i < arg.length; i += 2) {
            try {
                final String name = arg[i];
                final String pattern = arg[i + 1];
                final String instructions = String.valueOf(name) + "PatternInstructions";
                System.out.print("\n    // Pre-compiled regular expression '" + pattern + "'\n" + "    private static char[] " + instructions + " = \n    {");
                final REProgram program = r.compile(pattern);
                final int numColumns = 7;
                final char[] p = program.getInstructions();
                for (int j = 0; j < p.length; ++j) {
                    if (j % numColumns == 0) {
                        System.out.print("\n        ");
                    }
                    String hex;
                    for (hex = Integer.toHexString(p[j]); hex.length() < 4; hex = "0" + hex) {}
                    System.out.print("0x" + hex + ", ");
                }
                System.out.println("\n    };");
                System.out.println("\n    private static RE " + name + "Pattern = new RE(new REProgram(" + instructions + "));");
            }
            catch (RESyntaxException e) {
                System.out.println("Syntax error in expression \"" + arg[i] + "\": " + e.toString());
            }
            catch (Exception e2) {
                System.out.println("Unexpected exception: " + e2.toString());
            }
            catch (Error e3) {
                System.out.println("Internal error: " + e3.toString());
            }
        }
    }
}

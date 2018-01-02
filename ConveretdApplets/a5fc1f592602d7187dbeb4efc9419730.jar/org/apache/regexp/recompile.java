// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.regexp;

public class recompile
{
    public static void main(final String[] array) {
        final RECompiler reCompiler = new RECompiler();
        if (array.length <= 0 || array.length % 2 != 0) {
            System.out.println("Usage: recompile <patternname> <pattern>");
            System.exit(0);
        }
        for (int i = 0; i < array.length; i += 2) {
            try {
                final String s = array[i];
                final String s2 = array[i + 1];
                final String string = String.valueOf(s) + "PatternInstructions";
                System.out.print("\n    // Pre-compiled regular expression '" + s2 + "'\n" + "    private static char[] " + string + " = \n    {");
                final REProgram compile = reCompiler.compile(s2);
                final int n = 7;
                final char[] instructions = compile.getInstructions();
                for (int j = 0; j < instructions.length; ++j) {
                    if (j % n == 0) {
                        System.out.print("\n        ");
                    }
                    String s3;
                    for (s3 = Integer.toHexString(instructions[j]); s3.length() < 4; s3 = "0" + s3) {}
                    System.out.print("0x" + s3 + ", ");
                }
                System.out.println("\n    };");
                System.out.println("\n    private static RE " + s + "Pattern = new RE(new REProgram(" + string + "));");
            }
            catch (RESyntaxException ex) {
                System.out.println("Syntax error in expression \"" + array[i] + "\": " + ex.toString());
            }
            catch (Exception ex2) {
                System.out.println("Unexpected exception: " + ex2.toString());
            }
            catch (Error error) {
                System.out.println("Internal error: " + error.toString());
            }
        }
    }
}

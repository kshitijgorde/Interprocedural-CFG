// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.regexp;

import java.io.PrintWriter;
import java.util.Hashtable;

public class REDebugCompiler extends RECompiler
{
    static Hashtable hashOpcode;
    
    static {
        (REDebugCompiler.hashOpcode = new Hashtable()).put(new Integer(56), "OP_RELUCTANTSTAR");
        REDebugCompiler.hashOpcode.put(new Integer(61), "OP_RELUCTANTPLUS");
        REDebugCompiler.hashOpcode.put(new Integer(47), "OP_RELUCTANTMAYBE");
        REDebugCompiler.hashOpcode.put(new Integer(69), "OP_END");
        REDebugCompiler.hashOpcode.put(new Integer(94), "OP_BOL");
        REDebugCompiler.hashOpcode.put(new Integer(36), "OP_EOL");
        REDebugCompiler.hashOpcode.put(new Integer(46), "OP_ANY");
        REDebugCompiler.hashOpcode.put(new Integer(91), "OP_ANYOF");
        REDebugCompiler.hashOpcode.put(new Integer(124), "OP_BRANCH");
        REDebugCompiler.hashOpcode.put(new Integer(65), "OP_ATOM");
        REDebugCompiler.hashOpcode.put(new Integer(42), "OP_STAR");
        REDebugCompiler.hashOpcode.put(new Integer(43), "OP_PLUS");
        REDebugCompiler.hashOpcode.put(new Integer(63), "OP_MAYBE");
        REDebugCompiler.hashOpcode.put(new Integer(78), "OP_NOTHING");
        REDebugCompiler.hashOpcode.put(new Integer(71), "OP_GOTO");
        REDebugCompiler.hashOpcode.put(new Integer(92), "OP_ESCAPE");
        REDebugCompiler.hashOpcode.put(new Integer(40), "OP_OPEN");
        REDebugCompiler.hashOpcode.put(new Integer(41), "OP_CLOSE");
        REDebugCompiler.hashOpcode.put(new Integer(35), "OP_BACKREF");
        REDebugCompiler.hashOpcode.put(new Integer(80), "OP_POSIXCLASS");
    }
    
    String charToString(final char c) {
        if (c < ' ' || c > '\u007f') {
            return "\\" + (int)c;
        }
        return String.valueOf(c);
    }
    
    public void dumpProgram(final PrintWriter printWriter) {
        short n = 0;
        while (n < super.lenInstruction) {
            final char c = super.instruction[n];
            final char c2 = super.instruction[n + 1];
            final short n2 = (short)super.instruction[n + 2];
            printWriter.print(String.valueOf(n) + ". " + this.nodeToString(n) + ", next = ");
            if (n2 == 0) {
                printWriter.print("none");
            }
            else {
                printWriter.print(n + n2);
            }
            n += 3;
            if (c == '[') {
                printWriter.print(", [");
                for (char c3 = c2, c4 = '\0'; c4 < c3; ++c4) {
                    final char c5 = super.instruction[n++];
                    final char c6 = super.instruction[n++];
                    if (c5 == c6) {
                        printWriter.print(this.charToString(c5));
                    }
                    else {
                        printWriter.print(String.valueOf(this.charToString(c5)) + "-" + this.charToString(c6));
                    }
                }
                printWriter.print("]");
            }
            if (c == 'A') {
                printWriter.print(", \"");
                int n3 = c2;
                while (n3-- != 0) {
                    printWriter.print(this.charToString(super.instruction[n++]));
                }
                printWriter.print("\"");
            }
            printWriter.println("");
        }
    }
    
    String nodeToString(final int n) {
        return String.valueOf(this.opcodeToString(super.instruction[n])) + ", opdata = " + (int)super.instruction[n + 1];
    }
    
    String opcodeToString(final char c) {
        String s = REDebugCompiler.hashOpcode.get(new Integer(c));
        if (s == null) {
            s = "OP_????";
        }
        return s;
    }
}

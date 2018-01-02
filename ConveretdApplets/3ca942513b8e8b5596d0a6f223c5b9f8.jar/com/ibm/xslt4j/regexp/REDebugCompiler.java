// 
// Decompiled by Procyon v0.5.30
// 

package com.ibm.xslt4j.regexp;

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
    
    String opcodeToString(final char opcode) {
        String ret = REDebugCompiler.hashOpcode.get(new Integer(opcode));
        if (ret == null) {
            ret = "OP_????";
        }
        return ret;
    }
    
    String charToString(final char c) {
        if (c < ' ' || c > '\u007f') {
            return "\\" + (int)c;
        }
        return String.valueOf(c);
    }
    
    String nodeToString(final int node) {
        final char opcode = super.instruction[node + 0];
        final int opdata = super.instruction[node + 1];
        return String.valueOf(this.opcodeToString(opcode)) + ", opdata = " + opdata;
    }
    
    public void dumpProgram(final PrintWriter p) {
        int i = 0;
        while (i < super.lenInstruction) {
            final char opcode = super.instruction[i + 0];
            final char opdata = super.instruction[i + 1];
            final short next = (short)super.instruction[i + 2];
            p.print(String.valueOf(i) + ". " + this.nodeToString(i) + ", next = ");
            if (next == 0) {
                p.print("none");
            }
            else {
                p.print(i + next);
            }
            i += 3;
            if (opcode == '[') {
                p.print(", [");
                for (int rangeCount = opdata, r = 0; r < rangeCount; ++r) {
                    final char charFirst = super.instruction[i++];
                    final char charLast = super.instruction[i++];
                    if (charFirst == charLast) {
                        p.print(this.charToString(charFirst));
                    }
                    else {
                        p.print(String.valueOf(this.charToString(charFirst)) + "-" + this.charToString(charLast));
                    }
                }
                p.print("]");
            }
            if (opcode == 'A') {
                p.print(", \"");
                int len = opdata;
                while (len-- != 0) {
                    p.print(this.charToString(super.instruction[i++]));
                }
                p.print("\"");
            }
            p.println("");
        }
    }
}

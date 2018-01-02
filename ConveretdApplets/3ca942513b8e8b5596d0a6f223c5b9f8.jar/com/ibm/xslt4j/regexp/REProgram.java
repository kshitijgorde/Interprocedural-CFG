// 
// Decompiled by Procyon v0.5.30
// 

package com.ibm.xslt4j.regexp;

public class REProgram
{
    static final int OPT_HASBACKREFS = 1;
    char[] instruction;
    int lenInstruction;
    char[] prefix;
    int flags;
    
    public REProgram(final char[] instruction) {
        this(instruction, instruction.length);
    }
    
    public REProgram(final char[] instruction, final int lenInstruction) {
        this.setInstructions(instruction, lenInstruction);
    }
    
    public char[] getInstructions() {
        if (this.lenInstruction != 0) {
            final char[] ret = new char[this.lenInstruction];
            System.arraycopy(this.instruction, 0, ret, 0, this.lenInstruction);
            return ret;
        }
        return null;
    }
    
    public void setInstructions(final char[] instruction, final int lenInstruction) {
        this.instruction = instruction;
        this.lenInstruction = lenInstruction;
        this.flags = 0;
        this.prefix = null;
        Label_0185: {
            if (instruction != null && lenInstruction != 0) {
                if (lenInstruction >= 3 && instruction[0] == '|') {
                    final int next = instruction[2];
                    if (instruction[next + 0] == 'E' && lenInstruction >= 6 && instruction[3] == 'A') {
                        final int lenAtom = instruction[4];
                        System.arraycopy(instruction, 6, this.prefix = new char[lenAtom], 0, lenAtom);
                    }
                }
                for (int i = 0; i < lenInstruction; i += 3) {
                    switch (instruction[i + 0]) {
                        case '[': {
                            i += instruction[i + 1] * '\u0002';
                            break;
                        }
                        case 'A': {
                            i += instruction[i + 1];
                            break;
                        }
                        case '#': {
                            this.flags |= 0x1;
                            break Label_0185;
                        }
                    }
                }
            }
        }
    }
}

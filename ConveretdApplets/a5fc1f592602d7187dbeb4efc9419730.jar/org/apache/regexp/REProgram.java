// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.regexp;

public class REProgram
{
    static final int OPT_HASBACKREFS = 1;
    char[] instruction;
    int lenInstruction;
    char[] prefix;
    int flags;
    
    public REProgram(final char[] array) {
        this(array, array.length);
    }
    
    public REProgram(final char[] array, final int n) {
        this.setInstructions(array, n);
    }
    
    public char[] getInstructions() {
        if (this.lenInstruction != 0) {
            final char[] array = new char[this.lenInstruction];
            System.arraycopy(this.instruction, 0, array, 0, this.lenInstruction);
            return array;
        }
        return null;
    }
    
    public void setInstructions(final char[] instruction, final int lenInstruction) {
        this.instruction = instruction;
        this.lenInstruction = lenInstruction;
        this.flags = 0;
        this.prefix = null;
        Label_0181: {
            if (instruction != null && lenInstruction != 0) {
                if (lenInstruction >= 3 && instruction[0] == '|' && instruction[instruction[2]] == 'E' && lenInstruction >= 6 && instruction[3] == 'A') {
                    final char c = instruction[4];
                    System.arraycopy(instruction, 6, this.prefix = new char[c], 0, c);
                }
                for (char c2 = '\0'; c2 < lenInstruction; c2 += '\u0003') {
                    switch (instruction[c2]) {
                        case '[': {
                            c2 += (char)(instruction[c2 + '\u0001'] * '\u0002');
                            break;
                        }
                        case 'A': {
                            c2 += instruction[c2 + '\u0001'];
                            break;
                        }
                        case '#': {
                            this.flags |= 0x1;
                            break Label_0181;
                        }
                    }
                }
            }
        }
    }
}

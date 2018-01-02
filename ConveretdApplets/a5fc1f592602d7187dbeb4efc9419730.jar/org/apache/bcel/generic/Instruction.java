// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.bcel.generic;

import org.apache.bcel.util.ByteSequence;
import org.apache.bcel.classfile.ConstantPool;
import org.apache.bcel.Constants;
import java.io.IOException;
import java.io.DataOutputStream;
import java.io.Serializable;

public abstract class Instruction implements Cloneable, Serializable
{
    protected short length;
    protected short opcode;
    
    Instruction() {
        this.length = 1;
        this.opcode = -1;
    }
    
    public Instruction(final short opcode, final short length) {
        this.length = 1;
        this.opcode = -1;
        this.length = length;
        this.opcode = opcode;
    }
    
    public void dump(final DataOutputStream out) throws IOException {
        out.writeByte(this.opcode);
    }
    
    public String toString(final boolean verbose) {
        if (verbose) {
            return Constants.OPCODE_NAMES[this.opcode] + "[" + this.opcode + "](" + this.length + ")";
        }
        return Constants.OPCODE_NAMES[this.opcode];
    }
    
    public String toString() {
        return this.toString(true);
    }
    
    public String toString(final ConstantPool cp) {
        return this.toString(false);
    }
    
    public Instruction copy() {
        Instruction i = null;
        if (InstructionConstants.INSTRUCTIONS[this.getOpcode()] != null) {
            i = this;
        }
        else {
            try {
                i = (Instruction)this.clone();
            }
            catch (CloneNotSupportedException e) {
                System.err.println(e);
            }
        }
        return i;
    }
    
    protected void initFromFile(final ByteSequence bytes, final boolean wide) throws IOException {
    }
    
    public static final Instruction readInstruction(final ByteSequence bytes) throws IOException {
        boolean wide = false;
        short opcode = (short)bytes.readUnsignedByte();
        Instruction obj = null;
        if (opcode == 196) {
            wide = true;
            opcode = (short)bytes.readUnsignedByte();
        }
        if (InstructionConstants.INSTRUCTIONS[opcode] != null) {
            return InstructionConstants.INSTRUCTIONS[opcode];
        }
        Class clazz;
        try {
            clazz = Class.forName(className(opcode));
        }
        catch (ClassNotFoundException cnfe) {
            throw new ClassGenException("Illegal opcode detected.");
        }
        try {
            obj = clazz.newInstance();
            if (wide && !(obj instanceof LocalVariableInstruction) && !(obj instanceof IINC) && !(obj instanceof RET)) {
                throw new Exception("Illegal opcode after wide: " + opcode);
            }
            obj.setOpcode(opcode);
            obj.initFromFile(bytes, wide);
        }
        catch (Exception e) {
            throw new ClassGenException(e.toString());
        }
        return obj;
    }
    
    private static final String className(final short opcode) {
        String name = Constants.OPCODE_NAMES[opcode].toUpperCase();
        try {
            final int len = name.length();
            final char ch1 = name.charAt(len - 2);
            final char ch2 = name.charAt(len - 1);
            if (ch1 == '_' && ch2 >= '0' && ch2 <= '5') {
                name = name.substring(0, len - 2);
            }
            if (name.equals("ICONST_M1")) {
                name = "ICONST";
            }
        }
        catch (StringIndexOutOfBoundsException e) {
            System.err.println(e);
        }
        return "org.apache.bcel.generic." + name;
    }
    
    public int consumeStack(final ConstantPoolGen cpg) {
        return Constants.CONSUME_STACK[this.opcode];
    }
    
    public int produceStack(final ConstantPoolGen cpg) {
        return Constants.PRODUCE_STACK[this.opcode];
    }
    
    public short getOpcode() {
        return this.opcode;
    }
    
    public int getLength() {
        return this.length;
    }
    
    private void setOpcode(final short opcode) {
        this.opcode = opcode;
    }
    
    void dispose() {
    }
    
    public abstract void accept(final Visitor p0);
}

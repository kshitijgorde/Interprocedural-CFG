// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.bcel.generic;

import org.apache.bcel.ExceptionConstants;
import org.apache.bcel.classfile.ConstantPool;
import org.apache.bcel.util.ByteSequence;
import java.io.IOException;
import java.io.DataOutputStream;

public final class INVOKEINTERFACE extends InvokeInstruction
{
    private int nargs;
    
    INVOKEINTERFACE() {
    }
    
    public INVOKEINTERFACE(final int index, final int nargs) {
        super((short)185, index);
        super.length = 5;
        if (nargs < 1) {
            throw new ClassGenException("Number of arguments must be > 0 " + nargs);
        }
        this.nargs = nargs;
    }
    
    public void dump(final DataOutputStream out) throws IOException {
        out.writeByte(super.opcode);
        out.writeShort(super.index);
        out.writeByte(this.nargs);
        out.writeByte(0);
    }
    
    public int getCount() {
        return this.nargs;
    }
    
    protected void initFromFile(final ByteSequence bytes, final boolean wide) throws IOException {
        super.initFromFile(bytes, wide);
        super.length = 5;
        this.nargs = bytes.readUnsignedByte();
        bytes.readByte();
    }
    
    public String toString(final ConstantPool cp) {
        return super.toString(cp) + " " + this.nargs;
    }
    
    public int consumeStack(final ConstantPoolGen cpg) {
        return this.nargs;
    }
    
    public Class[] getExceptions() {
        final Class[] cs = new Class[4 + ExceptionConstants.EXCS_INTERFACE_METHOD_RESOLUTION.length];
        System.arraycopy(ExceptionConstants.EXCS_INTERFACE_METHOD_RESOLUTION, 0, cs, 0, ExceptionConstants.EXCS_INTERFACE_METHOD_RESOLUTION.length);
        cs[ExceptionConstants.EXCS_INTERFACE_METHOD_RESOLUTION.length + 3] = ExceptionConstants.INCOMPATIBLE_CLASS_CHANGE_ERROR;
        cs[ExceptionConstants.EXCS_INTERFACE_METHOD_RESOLUTION.length + 2] = ExceptionConstants.ILLEGAL_ACCESS_ERROR;
        cs[ExceptionConstants.EXCS_INTERFACE_METHOD_RESOLUTION.length + 1] = ExceptionConstants.ABSTRACT_METHOD_ERROR;
        cs[ExceptionConstants.EXCS_INTERFACE_METHOD_RESOLUTION.length] = ExceptionConstants.UNSATISFIED_LINK_ERROR;
        return cs;
    }
    
    public void accept(final Visitor v) {
        v.visitExceptionThrower(this);
        v.visitTypedInstruction(this);
        v.visitStackConsumer(this);
        v.visitStackProducer(this);
        v.visitLoadClass(this);
        v.visitCPInstruction(this);
        v.visitFieldOrMethod(this);
        v.visitInvokeInstruction(this);
        v.visitINVOKEINTERFACE(this);
    }
}

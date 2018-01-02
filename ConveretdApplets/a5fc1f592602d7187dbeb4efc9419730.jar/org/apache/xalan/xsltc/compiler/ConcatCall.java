// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.xsltc.compiler;

import org.apache.bcel.generic.InstructionList;
import org.apache.bcel.generic.ConstantPoolGen;
import org.apache.bcel.generic.INVOKESPECIAL;
import org.apache.bcel.generic.InstructionConstants;
import org.apache.bcel.generic.Instruction;
import org.apache.bcel.generic.NEW;
import org.apache.bcel.generic.INVOKEVIRTUAL;
import org.apache.bcel.generic.CompoundInstruction;
import org.apache.bcel.generic.PUSH;
import org.apache.xalan.xsltc.compiler.util.MethodGenerator;
import org.apache.xalan.xsltc.compiler.util.ClassGenerator;
import org.apache.xalan.xsltc.compiler.util.TypeCheckError;
import org.apache.xalan.xsltc.compiler.util.Type;
import java.util.Vector;

final class ConcatCall extends FunctionCall
{
    public ConcatCall(final QName fname, final Vector arguments) {
        super(fname, arguments);
    }
    
    public Type typeCheck(final SymbolTable stable) throws TypeCheckError {
        for (int i = 0; i < this.argumentCount(); ++i) {
            final Expression exp = this.argument(i);
            if (!exp.typeCheck(stable).identicalTo(Type.String)) {
                this.setArgument(i, new CastExpr(exp, Type.String));
            }
        }
        return super._type = Type.String;
    }
    
    public void translate(final ClassGenerator classGen, final MethodGenerator methodGen) {
        final ConstantPoolGen cpg = classGen.getConstantPool();
        final InstructionList il = methodGen.getInstructionList();
        final int nArgs = this.argumentCount();
        switch (nArgs) {
            case 0: {
                il.append(new PUSH(cpg, ""));
                break;
            }
            case 1: {
                this.argument().translate(classGen, methodGen);
                break;
            }
            default: {
                final int initBuffer = cpg.addMethodref("java.lang.StringBuffer", "<init>", "()V");
                final Instruction append = new INVOKEVIRTUAL(cpg.addMethodref("java.lang.StringBuffer", "append", "(Ljava/lang/String;)Ljava/lang/StringBuffer;"));
                final int toString = cpg.addMethodref("java.lang.StringBuffer", "toString", "()Ljava/lang/String;");
                il.append(new NEW(cpg.addClass("java.lang.StringBuffer")));
                il.append(InstructionConstants.DUP);
                il.append(new INVOKESPECIAL(initBuffer));
                for (int i = 0; i < nArgs; ++i) {
                    this.argument(i).translate(classGen, methodGen);
                    il.append(append);
                }
                il.append(new INVOKEVIRTUAL(toString));
                break;
            }
        }
    }
}

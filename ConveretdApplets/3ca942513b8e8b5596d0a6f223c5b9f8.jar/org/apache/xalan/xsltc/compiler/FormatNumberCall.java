// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.xsltc.compiler;

import com.ibm.xslt4j.bcel.generic.InstructionList;
import com.ibm.xslt4j.bcel.generic.ConstantPoolGen;
import com.ibm.xslt4j.bcel.generic.INVOKESTATIC;
import com.ibm.xslt4j.bcel.generic.Instruction;
import com.ibm.xslt4j.bcel.generic.INVOKEVIRTUAL;
import com.ibm.xslt4j.bcel.generic.CompoundInstruction;
import com.ibm.xslt4j.bcel.generic.PUSH;
import org.apache.xalan.xsltc.compiler.util.MethodGenerator;
import org.apache.xalan.xsltc.compiler.util.ClassGenerator;
import org.apache.xalan.xsltc.compiler.util.TypeCheckError;
import org.apache.xalan.xsltc.compiler.util.StringType;
import org.apache.xalan.xsltc.compiler.util.RealType;
import org.apache.xalan.xsltc.compiler.util.Type;
import java.util.Vector;

final class FormatNumberCall extends FunctionCall
{
    private Expression _value;
    private Expression _format;
    private Expression _name;
    private QName _resolvedQName;
    
    public FormatNumberCall(final QName fname, final Vector arguments) {
        super(fname, arguments);
        this._resolvedQName = null;
        this._value = this.argument(0);
        this._format = this.argument(1);
        this._name = ((this.argumentCount() == 3) ? this.argument(2) : null);
    }
    
    public Type typeCheck(final SymbolTable stable) throws TypeCheckError {
        this.getStylesheet().numberFormattingUsed();
        final Type tvalue = this._value.typeCheck(stable);
        if (!(tvalue instanceof RealType)) {
            this._value = new CastExpr(this._value, Type.Real);
        }
        final Type tformat = this._format.typeCheck(stable);
        if (!(tformat instanceof StringType)) {
            this._format = new CastExpr(this._format, Type.String);
        }
        if (this.argumentCount() == 3) {
            final Type tname = this._name.typeCheck(stable);
            if (this._name instanceof LiteralExpr) {
                final LiteralExpr literal = (LiteralExpr)this._name;
                this._resolvedQName = this.getParser().getQNameIgnoreDefaultNs(literal.getValue());
            }
            else if (!(tname instanceof StringType)) {
                this._name = new CastExpr(this._name, Type.String);
            }
        }
        return super._type = Type.String;
    }
    
    public void translate(final ClassGenerator classGen, final MethodGenerator methodGen) {
        final ConstantPoolGen cpg = classGen.getConstantPool();
        final InstructionList il = methodGen.getInstructionList();
        this._value.translate(classGen, methodGen);
        this._format.translate(classGen, methodGen);
        final int fn3arg = cpg.addMethodref("org.apache.xalan.xsltc.runtime.BasisLibrary", "formatNumber", "(DLjava/lang/String;Ljava/text/DecimalFormat;)Ljava/lang/String;");
        final int get = cpg.addMethodref("org.apache.xalan.xsltc.runtime.AbstractTranslet", "getDecimalFormat", "(Ljava/lang/String;)Ljava/text/DecimalFormat;");
        il.append(classGen.loadTranslet());
        if (this._name == null) {
            il.append(new PUSH(cpg, ""));
        }
        else if (this._resolvedQName != null) {
            il.append(new PUSH(cpg, this._resolvedQName.toString()));
        }
        else {
            this._name.translate(classGen, methodGen);
        }
        il.append(new INVOKEVIRTUAL(get));
        il.append(new INVOKESTATIC(fn3arg));
    }
}

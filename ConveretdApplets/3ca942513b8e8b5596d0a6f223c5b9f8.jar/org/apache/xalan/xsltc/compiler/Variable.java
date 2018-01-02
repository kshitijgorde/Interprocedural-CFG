// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.xsltc.compiler;

import com.ibm.xslt4j.bcel.generic.PUTFIELD;
import com.ibm.xslt4j.bcel.classfile.Attribute;
import com.ibm.xslt4j.bcel.classfile.Field;
import com.ibm.xslt4j.bcel.generic.InstructionList;
import com.ibm.xslt4j.bcel.generic.ConstantPoolGen;
import com.ibm.xslt4j.bcel.generic.ACONST_NULL;
import com.ibm.xslt4j.bcel.generic.DCONST;
import org.apache.xalan.xsltc.compiler.util.RealType;
import com.ibm.xslt4j.bcel.generic.Instruction;
import com.ibm.xslt4j.bcel.generic.ICONST;
import org.apache.xalan.xsltc.compiler.util.BooleanType;
import org.apache.xalan.xsltc.compiler.util.NodeType;
import org.apache.xalan.xsltc.compiler.util.IntType;
import org.apache.xalan.xsltc.compiler.util.MethodGenerator;
import org.apache.xalan.xsltc.compiler.util.ClassGenerator;
import org.apache.xalan.xsltc.compiler.util.TypeCheckError;
import org.apache.xalan.xsltc.compiler.util.Type;

final class Variable extends VariableBase
{
    public int getIndex() {
        return (super._local != null) ? super._local.getIndex() : -1;
    }
    
    public void parseContents(final Parser parser) {
        super.parseContents(parser);
        final SyntaxTreeNode parent = this.getParent();
        if (parent instanceof Stylesheet) {
            super._isLocal = false;
            final Variable var = parser.getSymbolTable().lookupVariable(super._name);
            if (var != null) {
                final int us = this.getImportPrecedence();
                final int them = var.getImportPrecedence();
                if (us == them) {
                    final String name = super._name.toString();
                    this.reportError(this, parser, "VARIABLE_REDEF_ERR", name);
                }
                else {
                    if (them > us) {
                        super._ignore = true;
                        return;
                    }
                    var.disable();
                }
            }
            ((Stylesheet)parent).addVariable(this);
            parser.getSymbolTable().addVariable(this);
        }
        else {
            super._isLocal = true;
        }
    }
    
    public Type typeCheck(final SymbolTable stable) throws TypeCheckError {
        if (super._select != null) {
            super._type = super._select.typeCheck(stable);
        }
        else if (this.hasContents()) {
            this.typeCheckContents(stable);
            super._type = Type.ResultTree;
        }
        else {
            super._type = Type.Reference;
        }
        return Type.Void;
    }
    
    public void initialize(final ClassGenerator classGen, final MethodGenerator methodGen) {
        final ConstantPoolGen cpg = classGen.getConstantPool();
        final InstructionList il = methodGen.getInstructionList();
        if (this.isLocal() && !super._refs.isEmpty()) {
            if (super._local == null) {
                super._local = methodGen.addLocalVariable2(this.getEscapedName(), super._type.toJCType(), il.getEnd());
            }
            if (super._type instanceof IntType || super._type instanceof NodeType || super._type instanceof BooleanType) {
                il.append(new ICONST(0));
            }
            else if (super._type instanceof RealType) {
                il.append(new DCONST(0.0));
            }
            else {
                il.append(new ACONST_NULL());
            }
            il.append(super._type.STORE(super._local.getIndex()));
        }
    }
    
    public void translate(final ClassGenerator classGen, final MethodGenerator methodGen) {
        final ConstantPoolGen cpg = classGen.getConstantPool();
        final InstructionList il = methodGen.getInstructionList();
        if (super._refs.isEmpty()) {
            super._ignore = true;
        }
        if (super._ignore) {
            return;
        }
        super._ignore = true;
        final String name = this.getEscapedName();
        if (this.isLocal()) {
            this.translateValue(classGen, methodGen);
            if (super._local == null) {
                this.mapRegister(methodGen);
            }
            il.append(super._type.STORE(super._local.getIndex()));
        }
        else {
            final String signature = super._type.toSignature();
            if (classGen.containsField(name) == null) {
                classGen.addField(new Field(1, cpg.addUtf8(name), cpg.addUtf8(signature), null, cpg.getConstantPool()));
                il.append(classGen.loadTranslet());
                this.translateValue(classGen, methodGen);
                il.append(new PUTFIELD(cpg.addFieldref(classGen.getClassName(), name, signature)));
            }
        }
    }
}

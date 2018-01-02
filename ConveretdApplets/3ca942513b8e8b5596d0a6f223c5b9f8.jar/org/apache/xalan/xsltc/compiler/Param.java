// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.xsltc.compiler;

import com.ibm.xslt4j.bcel.generic.BranchHandle;
import com.ibm.xslt4j.bcel.generic.InstructionList;
import com.ibm.xslt4j.bcel.generic.ConstantPoolGen;
import com.ibm.xslt4j.bcel.generic.PUTFIELD;
import com.ibm.xslt4j.bcel.classfile.Attribute;
import com.ibm.xslt4j.bcel.classfile.Field;
import com.ibm.xslt4j.bcel.generic.CHECKCAST;
import com.ibm.xslt4j.bcel.generic.INVOKEVIRTUAL;
import com.ibm.xslt4j.bcel.generic.CompoundInstruction;
import com.ibm.xslt4j.bcel.generic.PUSH;
import com.ibm.xslt4j.bcel.generic.InstructionConstants;
import com.ibm.xslt4j.bcel.generic.BranchInstruction;
import com.ibm.xslt4j.bcel.generic.InstructionHandle;
import com.ibm.xslt4j.bcel.generic.IFNONNULL;
import org.apache.xalan.xsltc.runtime.BasisLibrary;
import org.apache.xalan.xsltc.compiler.util.MethodGenerator;
import org.apache.xalan.xsltc.compiler.util.ClassGenerator;
import org.apache.xalan.xsltc.compiler.util.TypeCheckError;
import org.apache.xalan.xsltc.compiler.util.ObjectType;
import org.apache.xalan.xsltc.compiler.util.ReferenceType;
import org.apache.xalan.xsltc.compiler.util.Type;
import com.ibm.xslt4j.bcel.generic.Instruction;

final class Param extends VariableBase
{
    private boolean _isInSimpleNamedTemplate;
    
    Param() {
        this._isInSimpleNamedTemplate = false;
    }
    
    public String toString() {
        return "param(" + super._name + ")";
    }
    
    public Instruction setLoadInstruction(final Instruction instruction) {
        final Instruction tmp = super._loadInstruction;
        super._loadInstruction = instruction;
        return tmp;
    }
    
    public Instruction setStoreInstruction(final Instruction instruction) {
        final Instruction tmp = super._storeInstruction;
        super._storeInstruction = instruction;
        return tmp;
    }
    
    public void display(final int indent) {
        this.indent(indent);
        System.out.println("param " + super._name);
        if (super._select != null) {
            this.indent(indent + 4);
            System.out.println("select " + super._select.toString());
        }
        this.displayContents(indent + 4);
    }
    
    public void parseContents(final Parser parser) {
        super.parseContents(parser);
        final SyntaxTreeNode parent = this.getParent();
        if (parent instanceof Stylesheet) {
            super._isLocal = false;
            final Param param = parser.getSymbolTable().lookupParam(super._name);
            if (param != null) {
                final int us = this.getImportPrecedence();
                final int them = param.getImportPrecedence();
                if (us == them) {
                    final String name = super._name.toString();
                    this.reportError(this, parser, "VARIABLE_REDEF_ERR", name);
                }
                else {
                    if (them > us) {
                        super._ignore = true;
                        return;
                    }
                    param.disable();
                }
            }
            ((Stylesheet)parent).addParam(this);
            parser.getSymbolTable().addParam(this);
        }
        else if (parent instanceof Template) {
            final Template template = (Template)parent;
            super._isLocal = true;
            template.addParameter(this);
            if (template.isSimpleNamedTemplate()) {
                this._isInSimpleNamedTemplate = true;
            }
        }
    }
    
    public Type typeCheck(final SymbolTable stable) throws TypeCheckError {
        if (super._select != null) {
            super._type = super._select.typeCheck(stable);
            if (!(super._type instanceof ReferenceType) && !(super._type instanceof ObjectType)) {
                super._select = new CastExpr(super._select, Type.Reference);
            }
        }
        else if (this.hasContents()) {
            this.typeCheckContents(stable);
        }
        super._type = Type.Reference;
        return Type.Void;
    }
    
    public void translate(final ClassGenerator classGen, final MethodGenerator methodGen) {
        final ConstantPoolGen cpg = classGen.getConstantPool();
        final InstructionList il = methodGen.getInstructionList();
        if (super._ignore) {
            return;
        }
        super._ignore = true;
        final String name = BasisLibrary.mapQNameToJavaName(super._name.toString());
        final String signature = super._type.toSignature();
        final String className = super._type.getClassName();
        if (this.isLocal()) {
            if (this._isInSimpleNamedTemplate) {
                il.append(this.loadInstruction());
                final BranchHandle ifBlock = il.append(new IFNONNULL(null));
                this.translateValue(classGen, methodGen);
                il.append(this.storeInstruction());
                ifBlock.setTarget(il.append(InstructionConstants.NOP));
                return;
            }
            il.append(classGen.loadTranslet());
            il.append(new PUSH(cpg, name));
            this.translateValue(classGen, methodGen);
            il.append(new PUSH(cpg, true));
            il.append(new INVOKEVIRTUAL(cpg.addMethodref("org.apache.xalan.xsltc.runtime.AbstractTranslet", "addParameter", "(Ljava/lang/String;Ljava/lang/Object;Z)Ljava/lang/Object;")));
            if (className != "") {
                il.append(new CHECKCAST(cpg.addClass(className)));
            }
            super._type.translateUnBox(classGen, methodGen);
            if (super._refs.isEmpty()) {
                il.append(super._type.POP());
                super._local = null;
            }
            else {
                super._local = methodGen.addLocalVariable2(name, super._type.toJCType(), il.getEnd());
                il.append(super._type.STORE(super._local.getIndex()));
            }
        }
        else if (classGen.containsField(name) == null) {
            classGen.addField(new Field(1, cpg.addUtf8(name), cpg.addUtf8(signature), null, cpg.getConstantPool()));
            il.append(classGen.loadTranslet());
            il.append(InstructionConstants.DUP);
            il.append(new PUSH(cpg, name));
            this.translateValue(classGen, methodGen);
            il.append(new PUSH(cpg, true));
            il.append(new INVOKEVIRTUAL(cpg.addMethodref("org.apache.xalan.xsltc.runtime.AbstractTranslet", "addParameter", "(Ljava/lang/String;Ljava/lang/Object;Z)Ljava/lang/Object;")));
            super._type.translateUnBox(classGen, methodGen);
            if (className != "") {
                il.append(new CHECKCAST(cpg.addClass(className)));
            }
            il.append(new PUTFIELD(cpg.addFieldref(classGen.getClassName(), name, signature)));
        }
    }
}

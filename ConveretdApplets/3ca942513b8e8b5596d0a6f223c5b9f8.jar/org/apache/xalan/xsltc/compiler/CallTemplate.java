// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.xsltc.compiler;

import java.util.Vector;
import com.ibm.xslt4j.bcel.generic.InstructionList;
import com.ibm.xslt4j.bcel.generic.ConstantPoolGen;
import com.ibm.xslt4j.bcel.generic.InstructionConstants;
import com.ibm.xslt4j.bcel.generic.INVOKEVIRTUAL;
import org.apache.xalan.xsltc.compiler.util.MethodGenerator;
import org.apache.xalan.xsltc.compiler.util.ClassGenerator;
import org.apache.xalan.xsltc.compiler.util.TypeCheckError;
import org.apache.xalan.xsltc.compiler.util.Type;
import org.apache.xalan.xsltc.compiler.util.ErrorMsg;
import org.apache.xml.utils.XML11Char;
import org.apache.xalan.xsltc.compiler.util.Util;

final class CallTemplate extends Instruction
{
    private QName _name;
    private Object[] _parameters;
    private Template _calleeTemplate;
    
    CallTemplate() {
        this._parameters = null;
        this._calleeTemplate = null;
    }
    
    public void display(final int indent) {
        this.indent(indent);
        System.out.print("CallTemplate");
        Util.println(" name " + this._name);
        this.displayContents(indent + 4);
    }
    
    public boolean hasWithParams() {
        return this.elementCount() > 0;
    }
    
    public void parseContents(final Parser parser) {
        final String name = this.getAttribute("name");
        if (name.length() > 0) {
            if (!XML11Char.isXML11ValidQName(name)) {
                final ErrorMsg err = new ErrorMsg("INVALID_QNAME_ERR", name, this);
                parser.reportError(3, err);
            }
            this._name = parser.getQNameIgnoreDefaultNs(name);
        }
        else {
            this.reportError(this, parser, "REQUIRED_ATTR_ERR", "name");
        }
        this.parseChildren(parser);
    }
    
    public Type typeCheck(final SymbolTable stable) throws TypeCheckError {
        final Template template = stable.lookupTemplate(this._name);
        if (template != null) {
            this.typeCheckContents(stable);
            return Type.Void;
        }
        final ErrorMsg err = new ErrorMsg("TEMPLATE_UNDEF_ERR", this._name, this);
        throw new TypeCheckError(err);
    }
    
    public void translate(final ClassGenerator classGen, final MethodGenerator methodGen) {
        final Stylesheet stylesheet = classGen.getStylesheet();
        final ConstantPoolGen cpg = classGen.getConstantPool();
        final InstructionList il = methodGen.getInstructionList();
        if (stylesheet.hasLocalParams() || this.hasContents()) {
            this._calleeTemplate = this.getCalleeTemplate();
            if (this._calleeTemplate != null) {
                this.buildParameterList();
            }
            else {
                final int push = cpg.addMethodref("org.apache.xalan.xsltc.runtime.AbstractTranslet", "pushParamFrame", "()V");
                il.append(classGen.loadTranslet());
                il.append(new INVOKEVIRTUAL(push));
                this.translateContents(classGen, methodGen);
            }
        }
        final String className = stylesheet.getClassName();
        final String methodName = Util.escape(this._name.toString());
        il.append(classGen.loadTranslet());
        il.append(methodGen.loadDOM());
        il.append(methodGen.loadIterator());
        il.append(methodGen.loadHandler());
        il.append(methodGen.loadCurrentNode());
        final StringBuffer methodSig = new StringBuffer("(Lorg/apache/xalan/xsltc/DOM;Lorg/apache/xml/dtm/DTMAxisIterator;Lorg/apache/xml/serializer/SerializationHandler;I");
        if (this._calleeTemplate != null) {
            final Vector calleeParams = this._calleeTemplate.getParameters();
            for (int numParams = this._parameters.length, i = 0; i < numParams; ++i) {
                final SyntaxTreeNode node = (SyntaxTreeNode)this._parameters[i];
                methodSig.append("Ljava/lang/Object;");
                if (node instanceof Param) {
                    il.append(InstructionConstants.ACONST_NULL);
                }
                else {
                    node.translate(classGen, methodGen);
                }
            }
        }
        methodSig.append(")V");
        il.append(new INVOKEVIRTUAL(cpg.addMethodref(className, methodName, methodSig.toString())));
        if (this._calleeTemplate == null && (stylesheet.hasLocalParams() || this.hasContents())) {
            final int pop = cpg.addMethodref("org.apache.xalan.xsltc.runtime.AbstractTranslet", "popParamFrame", "()V");
            il.append(classGen.loadTranslet());
            il.append(new INVOKEVIRTUAL(pop));
        }
    }
    
    public Template getCalleeTemplate() {
        final Template foundTemplate = this.getXSLTC().getParser().getSymbolTable().lookupTemplate(this._name);
        return foundTemplate.isSimpleNamedTemplate() ? foundTemplate : null;
    }
    
    private void buildParameterList() {
        final Vector defaultParams = this._calleeTemplate.getParameters();
        final int numParams = defaultParams.size();
        this._parameters = new Object[numParams];
        for (int i = 0; i < numParams; ++i) {
            this._parameters[i] = defaultParams.elementAt(i);
        }
        for (int count = this.elementCount(), j = 0; j < count; ++j) {
            final Object node = this.elementAt(j);
            if (node instanceof WithParam) {
                final WithParam withParam = (WithParam)node;
                final QName name = withParam.getName();
                for (int k = 0; k < numParams; ++k) {
                    final Object object = this._parameters[k];
                    if (object instanceof Param && ((Param)object).getName() == name) {
                        withParam.setDoParameterOptimization(true);
                        this._parameters[k] = withParam;
                        break;
                    }
                    if (object instanceof WithParam && ((WithParam)object).getName() == name) {
                        withParam.setDoParameterOptimization(true);
                        this._parameters[k] = withParam;
                        break;
                    }
                }
            }
        }
    }
}

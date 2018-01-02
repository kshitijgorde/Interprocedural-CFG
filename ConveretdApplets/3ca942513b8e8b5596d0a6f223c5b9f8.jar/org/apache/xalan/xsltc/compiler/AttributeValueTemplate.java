// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.xsltc.compiler;

import java.util.Enumeration;
import com.ibm.xslt4j.bcel.generic.InstructionList;
import com.ibm.xslt4j.bcel.generic.ConstantPoolGen;
import com.ibm.xslt4j.bcel.generic.INVOKESPECIAL;
import com.ibm.xslt4j.bcel.generic.InstructionConstants;
import com.ibm.xslt4j.bcel.generic.Instruction;
import com.ibm.xslt4j.bcel.generic.NEW;
import com.ibm.xslt4j.bcel.generic.INVOKEVIRTUAL;
import org.apache.xalan.xsltc.compiler.util.MethodGenerator;
import org.apache.xalan.xsltc.compiler.util.ClassGenerator;
import org.apache.xalan.xsltc.compiler.util.TypeCheckError;
import java.util.Vector;
import org.apache.xalan.xsltc.compiler.util.Type;
import java.util.StringTokenizer;
import java.util.NoSuchElementException;

final class AttributeValueTemplate extends AttributeValue
{
    static final int OUT_EXPR = 0;
    static final int IN_EXPR = 1;
    static final int IN_EXPR_SQUOTES = 2;
    static final int IN_EXPR_DQUOTES = 3;
    static final String DELIMITER = "\ufffe";
    
    public AttributeValueTemplate(final String value, final Parser parser, final SyntaxTreeNode parent) {
        this.setParent(parent);
        this.setParser(parser);
        try {
            this.parseAVTemplate(value, parser);
        }
        catch (NoSuchElementException e) {
            this.reportError(parent, parser, "ATTR_VAL_TEMPLATE_ERR", value);
        }
    }
    
    private void parseAVTemplate(final String text, final Parser parser) {
        StringTokenizer tokenizer = new StringTokenizer(text, "{}\"'", true);
        String t = null;
        String lookahead = null;
        final StringBuffer buffer = new StringBuffer();
        int state = 0;
        while (tokenizer.hasMoreTokens()) {
            if (lookahead != null) {
                t = lookahead;
                lookahead = null;
            }
            else {
                t = tokenizer.nextToken();
            }
            if (t.length() == 1) {
                switch (t.charAt(0)) {
                    case '{': {
                        switch (state) {
                            case 0: {
                                lookahead = tokenizer.nextToken();
                                if (lookahead.equals("{")) {
                                    buffer.append(lookahead);
                                    lookahead = null;
                                    continue;
                                }
                                buffer.append("\ufffe");
                                state = 1;
                                continue;
                            }
                            case 1:
                            case 2:
                            case 3: {
                                this.reportError(this.getParent(), parser, "ATTR_VAL_TEMPLATE_ERR", text);
                                continue;
                            }
                        }
                        continue;
                    }
                    case '}': {
                        switch (state) {
                            case 0: {
                                lookahead = tokenizer.nextToken();
                                if (lookahead.equals("}")) {
                                    buffer.append(lookahead);
                                    lookahead = null;
                                    continue;
                                }
                                this.reportError(this.getParent(), parser, "ATTR_VAL_TEMPLATE_ERR", text);
                                continue;
                            }
                            case 1: {
                                buffer.append("\ufffe");
                                state = 0;
                                continue;
                            }
                            case 2:
                            case 3: {
                                buffer.append(t);
                                continue;
                            }
                        }
                        continue;
                    }
                    case '\'': {
                        switch (state) {
                            case 1: {
                                state = 2;
                                break;
                            }
                            case 2: {
                                state = 1;
                                break;
                            }
                        }
                        buffer.append(t);
                        continue;
                    }
                    case '\"': {
                        switch (state) {
                            case 1: {
                                state = 3;
                                break;
                            }
                            case 3: {
                                state = 1;
                                break;
                            }
                        }
                        buffer.append(t);
                        continue;
                    }
                    default: {
                        buffer.append(t);
                        continue;
                    }
                }
            }
            else {
                buffer.append(t);
            }
        }
        if (state != 0) {
            this.reportError(this.getParent(), parser, "ATTR_VAL_TEMPLATE_ERR", text);
        }
        tokenizer = new StringTokenizer(buffer.toString(), "\ufffe", true);
        while (tokenizer.hasMoreTokens()) {
            t = tokenizer.nextToken();
            if (t.equals("\ufffe")) {
                this.addElement(parser.parseExpression(this, tokenizer.nextToken()));
                tokenizer.nextToken();
            }
            else {
                this.addElement(new LiteralExpr(t));
            }
        }
    }
    
    public Type typeCheck(final SymbolTable stable) throws TypeCheckError {
        final Vector contents = this.getContents();
        for (int n = contents.size(), i = 0; i < n; ++i) {
            final Expression exp = contents.elementAt(i);
            if (!exp.typeCheck(stable).identicalTo(Type.String)) {
                contents.setElementAt(new CastExpr(exp, Type.String), i);
            }
        }
        return super._type = Type.String;
    }
    
    public String toString() {
        final StringBuffer buffer = new StringBuffer("AVT:[");
        for (int count = this.elementCount(), i = 0; i < count; ++i) {
            buffer.append(this.elementAt(i).toString());
            if (i < count - 1) {
                buffer.append(' ');
            }
        }
        return buffer.append(']').toString();
    }
    
    public void translate(final ClassGenerator classGen, final MethodGenerator methodGen) {
        if (this.elementCount() == 1) {
            final Expression exp = (Expression)this.elementAt(0);
            exp.translate(classGen, methodGen);
        }
        else {
            final ConstantPoolGen cpg = classGen.getConstantPool();
            final InstructionList il = methodGen.getInstructionList();
            final int initBuffer = cpg.addMethodref("java.lang.StringBuffer", "<init>", "()V");
            final Instruction append = new INVOKEVIRTUAL(cpg.addMethodref("java.lang.StringBuffer", "append", "(Ljava/lang/String;)Ljava/lang/StringBuffer;"));
            final int toString = cpg.addMethodref("java.lang.StringBuffer", "toString", "()Ljava/lang/String;");
            il.append(new NEW(cpg.addClass("java.lang.StringBuffer")));
            il.append(InstructionConstants.DUP);
            il.append(new INVOKESPECIAL(initBuffer));
            final Enumeration elements = this.elements();
            while (elements.hasMoreElements()) {
                final Expression exp2 = elements.nextElement();
                exp2.translate(classGen, methodGen);
                il.append(append);
            }
            il.append(new INVOKEVIRTUAL(toString));
        }
    }
}

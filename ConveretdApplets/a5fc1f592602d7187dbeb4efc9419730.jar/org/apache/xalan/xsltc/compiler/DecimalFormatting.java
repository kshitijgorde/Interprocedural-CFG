// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.xsltc.compiler;

import org.apache.bcel.generic.InstructionList;
import org.apache.bcel.generic.ConstantPoolGen;
import org.apache.bcel.generic.INVOKEVIRTUAL;
import org.apache.bcel.generic.INVOKESPECIAL;
import org.apache.bcel.generic.GETSTATIC;
import org.apache.bcel.generic.InstructionConstants;
import org.apache.bcel.generic.Instruction;
import org.apache.bcel.generic.NEW;
import org.apache.bcel.generic.CompoundInstruction;
import org.apache.bcel.generic.PUSH;
import org.apache.xalan.xsltc.compiler.util.MethodGenerator;
import org.apache.xalan.xsltc.compiler.util.ClassGenerator;
import org.apache.xalan.xsltc.compiler.util.ErrorMsg;
import org.apache.xml.utils.XMLChar;
import org.apache.xalan.xsltc.compiler.util.TypeCheckError;
import org.apache.xalan.xsltc.compiler.util.Type;

final class DecimalFormatting extends TopLevelElement
{
    private static final String DFS_CLASS = "java.text.DecimalFormatSymbols";
    private static final String DFS_SIG = "Ljava/text/DecimalFormatSymbols;";
    private QName _name;
    
    DecimalFormatting() {
        this._name = null;
    }
    
    public Type typeCheck(final SymbolTable stable) throws TypeCheckError {
        return Type.Void;
    }
    
    public void parseContents(final Parser parser) {
        final String name = this.getAttribute("name");
        if (name.length() > 0 && !XMLChar.isValidQName(name)) {
            final ErrorMsg err = new ErrorMsg("INVALID_QNAME_ERR", name, this);
            parser.reportError(3, err);
        }
        this._name = parser.getQNameIgnoreDefaultNs(name);
        if (this._name == null) {
            this._name = parser.getQNameIgnoreDefaultNs("");
        }
        final SymbolTable stable = parser.getSymbolTable();
        if (stable.getDecimalFormatting(this._name) != null) {
            this.reportWarning(this, parser, "SYMBOLS_REDEF_ERR", this._name.toString());
        }
        else {
            stable.addDecimalFormatting(this._name, this);
        }
    }
    
    public void translate(final ClassGenerator classGen, final MethodGenerator methodGen) {
        final ConstantPoolGen cpg = classGen.getConstantPool();
        final InstructionList il = methodGen.getInstructionList();
        final int init = cpg.addMethodref("java.text.DecimalFormatSymbols", "<init>", "(Ljava/util/Locale;)V");
        il.append(classGen.loadTranslet());
        il.append(new PUSH(cpg, this._name.toString()));
        il.append(new NEW(cpg.addClass("java.text.DecimalFormatSymbols")));
        il.append(InstructionConstants.DUP);
        il.append(new GETSTATIC(cpg.addFieldref("java.util.Locale", "US", "Ljava/util/Locale;")));
        il.append(new INVOKESPECIAL(init));
        String tmp = this.getAttribute("NaN");
        if (tmp == null || tmp.equals("")) {
            final int nan = cpg.addMethodref("java.text.DecimalFormatSymbols", "setNaN", "(Ljava/lang/String;)V");
            il.append(InstructionConstants.DUP);
            il.append(new PUSH(cpg, "NaN"));
            il.append(new INVOKEVIRTUAL(nan));
        }
        tmp = this.getAttribute("infinity");
        if (tmp == null || tmp.equals("")) {
            final int inf = cpg.addMethodref("java.text.DecimalFormatSymbols", "setInfinity", "(Ljava/lang/String;)V");
            il.append(InstructionConstants.DUP);
            il.append(new PUSH(cpg, "Infinity"));
            il.append(new INVOKEVIRTUAL(inf));
        }
        for (int nAttributes = super._attributes.getLength(), i = 0; i < nAttributes; ++i) {
            final String name = super._attributes.getQName(i);
            final String value = super._attributes.getValue(i);
            boolean valid = true;
            int method = 0;
            if (name.equals("decimal-separator")) {
                method = cpg.addMethodref("java.text.DecimalFormatSymbols", "setDecimalSeparator", "(C)V");
            }
            else if (name.equals("grouping-separator")) {
                method = cpg.addMethodref("java.text.DecimalFormatSymbols", "setGroupingSeparator", "(C)V");
            }
            else if (name.equals("minus-sign")) {
                method = cpg.addMethodref("java.text.DecimalFormatSymbols", "setMinusSign", "(C)V");
            }
            else if (name.equals("percent")) {
                method = cpg.addMethodref("java.text.DecimalFormatSymbols", "setPercent", "(C)V");
            }
            else if (name.equals("per-mille")) {
                method = cpg.addMethodref("java.text.DecimalFormatSymbols", "setPerMill", "(C)V");
            }
            else if (name.equals("zero-digit")) {
                method = cpg.addMethodref("java.text.DecimalFormatSymbols", "setZeroDigit", "(C)V");
            }
            else if (name.equals("digit")) {
                method = cpg.addMethodref("java.text.DecimalFormatSymbols", "setDigit", "(C)V");
            }
            else if (name.equals("pattern-separator")) {
                method = cpg.addMethodref("java.text.DecimalFormatSymbols", "setPatternSeparator", "(C)V");
            }
            else if (name.equals("NaN")) {
                method = cpg.addMethodref("java.text.DecimalFormatSymbols", "setNaN", "(Ljava/lang/String;)V");
                il.append(InstructionConstants.DUP);
                il.append(new PUSH(cpg, value));
                il.append(new INVOKEVIRTUAL(method));
                valid = false;
            }
            else if (name.equals("infinity")) {
                method = cpg.addMethodref("java.text.DecimalFormatSymbols", "setInfinity", "(Ljava/lang/String;)V");
                il.append(InstructionConstants.DUP);
                il.append(new PUSH(cpg, value));
                il.append(new INVOKEVIRTUAL(method));
                valid = false;
            }
            else {
                valid = false;
            }
            if (valid) {
                il.append(InstructionConstants.DUP);
                il.append(new PUSH(cpg, value.charAt(0)));
                il.append(new INVOKEVIRTUAL(method));
            }
        }
        final int put = cpg.addMethodref("org.apache.xalan.xsltc.runtime.AbstractTranslet", "addDecimalFormat", "(Ljava/lang/String;Ljava/text/DecimalFormatSymbols;)V");
        il.append(new INVOKEVIRTUAL(put));
    }
    
    public static void translateDefaultDFS(final ClassGenerator classGen, final MethodGenerator methodGen) {
        final ConstantPoolGen cpg = classGen.getConstantPool();
        final InstructionList il = methodGen.getInstructionList();
        final int init = cpg.addMethodref("java.text.DecimalFormatSymbols", "<init>", "(Ljava/util/Locale;)V");
        il.append(classGen.loadTranslet());
        il.append(new PUSH(cpg, ""));
        il.append(new NEW(cpg.addClass("java.text.DecimalFormatSymbols")));
        il.append(InstructionConstants.DUP);
        il.append(new GETSTATIC(cpg.addFieldref("java.util.Locale", "US", "Ljava/util/Locale;")));
        il.append(new INVOKESPECIAL(init));
        final int nan = cpg.addMethodref("java.text.DecimalFormatSymbols", "setNaN", "(Ljava/lang/String;)V");
        il.append(InstructionConstants.DUP);
        il.append(new PUSH(cpg, "NaN"));
        il.append(new INVOKEVIRTUAL(nan));
        final int inf = cpg.addMethodref("java.text.DecimalFormatSymbols", "setInfinity", "(Ljava/lang/String;)V");
        il.append(InstructionConstants.DUP);
        il.append(new PUSH(cpg, "Infinity"));
        il.append(new INVOKEVIRTUAL(inf));
        final int put = cpg.addMethodref("org.apache.xalan.xsltc.runtime.AbstractTranslet", "addDecimalFormat", "(Ljava/lang/String;Ljava/text/DecimalFormatSymbols;)V");
        il.append(new INVOKEVIRTUAL(put));
    }
}

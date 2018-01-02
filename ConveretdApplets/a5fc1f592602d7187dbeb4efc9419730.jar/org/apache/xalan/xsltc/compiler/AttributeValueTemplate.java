// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.xsltc.compiler;

import java.util.Enumeration;
import org.apache.bcel.generic.InstructionList;
import org.apache.bcel.generic.ConstantPoolGen;
import org.apache.bcel.generic.INVOKESPECIAL;
import org.apache.bcel.generic.InstructionConstants;
import org.apache.bcel.generic.Instruction;
import org.apache.bcel.generic.NEW;
import org.apache.bcel.generic.INVOKEVIRTUAL;
import org.apache.xalan.xsltc.compiler.util.MethodGenerator;
import org.apache.xalan.xsltc.compiler.util.ClassGenerator;
import org.apache.xalan.xsltc.compiler.util.TypeCheckError;
import java.util.Vector;
import org.apache.xalan.xsltc.compiler.util.Type;

final class AttributeValueTemplate extends AttributeValue
{
    public AttributeValueTemplate(final String value, final Parser parser, final SyntaxTreeNode parent) {
        this.setParent(parent);
        this.setParser(parser);
        if (this.check(value, parser)) {
            this.parseAVTemplate(0, value, parser);
        }
    }
    
    private void parseAVTemplate(final int start, final String text, final Parser parser) {
        if (text == null) {
            return;
        }
        int open = start - 2;
        do {
            open = text.indexOf(123, open + 2);
        } while (open != -1 && open < text.length() - 1 && text.charAt(open + 1) == '{');
        if (open != -1) {
            int close = open - 2;
            do {
                close = text.indexOf(125, close + 2);
            } while (close != -1 && close < text.length() - 1 && text.charAt(close + 1) == '}');
            if (open > start) {
                final String str = this.removeDuplicateBraces(text.substring(start, open));
                this.addElement(new LiteralExpr(str));
            }
            if (close > open + 1) {
                String str = text.substring(open + 1, close);
                str = this.removeDuplicateBraces(text.substring(open + 1, close));
                this.addElement(parser.parseExpression(this, str));
            }
            this.parseAVTemplate(close + 1, text, parser);
        }
        else if (start < text.length()) {
            final String str = this.removeDuplicateBraces(text.substring(start));
            this.addElement(new LiteralExpr(str));
        }
    }
    
    public String removeDuplicateBraces(final String orig) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1         /* orig */
        //     1: astore_2        /* result */
        //     2: goto            39
        //     5: new             Ljava/lang/StringBuffer;
        //     8: dup            
        //     9: invokespecial   java/lang/StringBuffer.<init>:()V
        //    12: aload_2         /* result */
        //    13: iconst_0       
        //    14: iload_3        
        //    15: invokevirtual   java/lang/String.substring:(II)Ljava/lang/String;
        //    18: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
        //    21: aload_2         /* result */
        //    22: iload_3        
        //    23: iconst_1       
        //    24: iadd           
        //    25: aload_2         /* result */
        //    26: invokevirtual   java/lang/String.length:()I
        //    29: invokevirtual   java/lang/String.substring:(II)Ljava/lang/String;
        //    32: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
        //    35: invokevirtual   java/lang/StringBuffer.toString:()Ljava/lang/String;
        //    38: astore_2        /* result */
        //    39: aload_2         /* result */
        //    40: ldc             "{{"
        //    42: invokevirtual   java/lang/String.indexOf:(Ljava/lang/String;)I
        //    45: dup            
        //    46: istore_3        /* index */
        //    47: iconst_m1      
        //    48: if_icmpne       5
        //    51: goto            88
        //    54: new             Ljava/lang/StringBuffer;
        //    57: dup            
        //    58: invokespecial   java/lang/StringBuffer.<init>:()V
        //    61: aload_2         /* result */
        //    62: iconst_0       
        //    63: iload_3         /* index */
        //    64: invokevirtual   java/lang/String.substring:(II)Ljava/lang/String;
        //    67: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
        //    70: aload_2         /* result */
        //    71: iload_3         /* index */
        //    72: iconst_1       
        //    73: iadd           
        //    74: aload_2         /* result */
        //    75: invokevirtual   java/lang/String.length:()I
        //    78: invokevirtual   java/lang/String.substring:(II)Ljava/lang/String;
        //    81: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
        //    84: invokevirtual   java/lang/StringBuffer.toString:()Ljava/lang/String;
        //    87: astore_2        /* result */
        //    88: aload_2         /* result */
        //    89: ldc             "}}"
        //    91: invokevirtual   java/lang/String.indexOf:(Ljava/lang/String;)I
        //    94: dup            
        //    95: istore_3        /* index */
        //    96: iconst_m1      
        //    97: if_icmpne       54
        //   100: aload_2         /* result */
        //   101: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name    Signature
        //  -----  ------  ----  ------  --------------------------------------------------------
        //  0      102     0     this    Lorg/apache/xalan/xsltc/compiler/AttributeValueTemplate;
        //  0      102     1     orig    Ljava/lang/String;
        //  2      100     2     result  Ljava/lang/String;
        //  47     55      3     index   I
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        //     at com.strobel.decompiler.ast.AstBuilder.convertLocalVariables(AstBuilder.java:2985)
        //     at com.strobel.decompiler.ast.AstBuilder.performStackAnalysis(AstBuilder.java:2445)
        //     at com.strobel.decompiler.ast.AstBuilder.build(AstBuilder.java:108)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:210)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:757)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:655)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:532)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:130)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:105)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
        //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:317)
        //     at com.strobel.decompiler.DecompilerDriver.decompileJar(DecompilerDriver.java:238)
        //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:138)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
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
    
    private boolean check(final String value, final Parser parser) {
        if (value == null) {
            return true;
        }
        final char[] chars = value.toCharArray();
        int level = 0;
        for (int i = 0; i < chars.length; ++i) {
            switch (chars[i]) {
                case '{': {
                    if (i + 1 == chars.length || chars[i + 1] != '{') {
                        ++level;
                        break;
                    }
                    ++i;
                    break;
                }
                case '}': {
                    if (i + 1 == chars.length || chars[i + 1] != '}') {
                        --level;
                        break;
                    }
                    ++i;
                    break;
                }
                default: {
                    continue;
                }
            }
            switch (level) {
                case 0:
                case 1: {
                    break;
                }
                default: {
                    this.reportError(this.getParent(), parser, "ATTR_VAL_TEMPLATE_ERR", value);
                    return false;
                }
            }
        }
        if (level != 0) {
            this.reportError(this.getParent(), parser, "ATTR_VAL_TEMPLATE_ERR", value);
            return false;
        }
        return true;
    }
}

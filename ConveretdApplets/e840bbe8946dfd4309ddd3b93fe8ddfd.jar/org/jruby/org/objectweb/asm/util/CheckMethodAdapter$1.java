// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.org.objectweb.asm.util;

import java.io.Writer;
import java.io.PrintWriter;
import java.io.StringWriter;
import org.jruby.org.objectweb.asm.tree.analysis.Interpreter;
import org.jruby.org.objectweb.asm.tree.analysis.Analyzer;
import org.jruby.org.objectweb.asm.tree.analysis.BasicVerifier;
import org.jruby.org.objectweb.asm.MethodVisitor;
import org.jruby.org.objectweb.asm.tree.MethodNode;

class CheckMethodAdapter$1 extends MethodNode
{
    private final /* synthetic */ MethodVisitor val$mv;
    
    CheckMethodAdapter$1(final int n, final String s, final String s2, final String s3, final String[] array, final MethodVisitor val$mv) {
        super(n, s, s2, s3, array);
        this.val$mv = val$mv;
    }
    
    public void visitEnd() {
        final Analyzer analyzer = new Analyzer(new BasicVerifier());
        try {
            analyzer.analyze("dummy", this);
        }
        catch (Exception ex) {
            if (ex instanceof IndexOutOfBoundsException && this.maxLocals == 0 && this.maxStack == 0) {
                throw new RuntimeException("Data flow checking option requires valid, non zero maxLocals and maxStack values.");
            }
            ex.printStackTrace();
            final StringWriter stringWriter = new StringWriter();
            final PrintWriter printWriter = new PrintWriter(stringWriter, true);
            CheckClassAdapter.printAnalyzerResult(this, analyzer, printWriter);
            printWriter.close();
            throw new RuntimeException(ex.getMessage() + ' ' + stringWriter.toString());
        }
        this.accept(this.val$mv);
    }
}

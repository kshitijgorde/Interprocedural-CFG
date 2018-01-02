// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.compiler.ir;

import org.jruby.compiler.ir.compiler_pass.CompilerPass;
import org.jruby.compiler.ir.operands.Label;
import org.jruby.compiler.ir.operands.LocalVariable;
import org.jruby.parser.StaticScope;
import org.jruby.compiler.ir.operands.Variable;
import org.jruby.compiler.ir.instructions.Instr;
import org.jruby.compiler.ir.operands.Operand;

public interface IRScope
{
    Operand getContainer();
    
    IRScope getLexicalParent();
    
    IRModule getNearestModule();
    
    void addInstr(final Instr p0);
    
    void recordMethodAlias(final String p0, final String p1);
    
    String unaliasMethodName(final String p0);
    
    int getNextClosureId();
    
    Variable getNewTemporaryVariable();
    
    StaticScope getStaticScope();
    
    int getTemporaryVariableSize();
    
    int getRenamedVariableSize();
    
    LocalVariable getLocalVariable(final String p0);
    
    String getName();
    
    Label getNewLabel(final String p0);
    
    Label getNewLabel();
    
    void runCompilerPass(final CompilerPass p0);
    
    void prepareForInterpretation();
}

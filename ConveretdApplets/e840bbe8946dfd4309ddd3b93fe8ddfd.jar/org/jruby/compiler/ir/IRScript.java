// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.compiler.ir;

import org.jruby.compiler.ir.compiler_pass.CompilerPass;
import org.jruby.compiler.ir.operands.LocalVariable;
import org.jruby.compiler.ir.operands.StringLiteral;
import org.jruby.compiler.ir.operands.Operand;
import org.jruby.parser.StaticScope;

public class IRScript extends IRScopeImpl
{
    private final IRClass dummyClass;
    
    public IRScript(final String className, final String sourceName, final StaticScope staticScope) {
        super(null, null, sourceName, staticScope);
        this.dummyClass = new IRClass(this, null, null, "__SCRIPT_ROOT__", staticScope);
    }
    
    public Operand getFileName() {
        return new StringLiteral(this.getName());
    }
    
    public String getScopeName() {
        return "Script";
    }
    
    public IRMethod getRootMethod() {
        return this.dummyClass.getRootMethod();
    }
    
    public IRClass getRootClass() {
        return this.dummyClass;
    }
    
    public String toString() {
        return "Script: file: " + this.getFileName() + super.toString();
    }
    
    public LocalVariable getLocalVariable(final String name) {
        throw new UnsupportedOperationException("This should be happening on Root Method instead");
    }
    
    public void runCompilerPass(final CompilerPass p) {
        this.dummyClass.runCompilerPass(p);
    }
}

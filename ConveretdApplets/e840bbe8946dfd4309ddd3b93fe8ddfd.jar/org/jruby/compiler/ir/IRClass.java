// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.compiler.ir;

import org.jruby.parser.StaticScope;
import org.jruby.compiler.ir.operands.Operand;

public class IRClass extends IRModule
{
    public final Operand superClass;
    
    public IRClass(final IRScope lexicalParent, final Operand container, final Operand superClass, final String className, final StaticScope staticScope) {
        super(lexicalParent, container, className, staticScope);
        this.superClass = superClass;
    }
    
    public String getScopeName() {
        return "Class";
    }
}

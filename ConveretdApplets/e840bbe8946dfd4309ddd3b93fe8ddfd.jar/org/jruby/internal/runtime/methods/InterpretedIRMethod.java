// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.internal.runtime.methods;

import org.jruby.compiler.ir.representations.CFG;
import org.jruby.interpreter.InterpreterContext;
import org.jruby.interpreter.Interpreter;
import org.jruby.interpreter.NaiveInterpreterContext;
import org.jruby.runtime.Block;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.Visibility;
import org.jruby.RubyModule;
import org.jruby.compiler.ir.IRMethod;

public class InterpretedIRMethod extends DynamicMethod
{
    public final IRMethod method;
    private final int temporaryVariableSize;
    boolean displayedCFG;
    
    public InterpretedIRMethod(final IRMethod method, final RubyModule implementationClass) {
        super(implementationClass, Visibility.PRIVATE, CallConfiguration.FrameNoneScopeNone);
        this.displayedCFG = false;
        this.temporaryVariableSize = method.getTemporaryVariableSize();
        this.method = method;
    }
    
    public IRubyObject call(final ThreadContext context, final IRubyObject self, final RubyModule clazz, final String name, final IRubyObject[] args, final Block block) {
        final InterpreterContext interp = new NaiveInterpreterContext(context, self, this.method.getLocalVariablesCount(), this.temporaryVariableSize, this.method.getRenamedVariableSize(), args, block);
        if (Interpreter.isDebug()) {
            final String realName = (name == null || "".equals(name)) ? this.method.getName() : name;
            System.out.println("Executing '" + realName + "'");
        }
        CFG c = this.method.getCFG();
        if (c == null) {
            this.method.prepareForInterpretation();
            c = this.method.getCFG();
        }
        if (Interpreter.isDebug() && !this.displayedCFG) {
            System.out.println("CFG:\n" + c.getGraph());
            System.out.println("\nInstructions:\n" + c.toStringInstrs());
            this.displayedCFG = true;
        }
        return Interpreter.INTERPRET_METHOD(context, c, interp, name, this.getImplementationClass(), false);
    }
    
    public DynamicMethod dup() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}

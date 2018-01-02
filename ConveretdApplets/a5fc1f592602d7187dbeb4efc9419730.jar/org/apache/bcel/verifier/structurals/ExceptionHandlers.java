// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.bcel.verifier.structurals;

import org.apache.bcel.generic.InstructionHandle;
import org.apache.bcel.generic.CodeExceptionGen;
import java.util.HashSet;
import org.apache.bcel.generic.MethodGen;
import java.util.Hashtable;

public class ExceptionHandlers
{
    private Hashtable exceptionhandlers;
    
    public ExceptionHandlers(final MethodGen mg) {
        this.exceptionhandlers = new Hashtable();
        final CodeExceptionGen[] cegs = mg.getExceptionHandlers();
        for (int i = 0; i < cegs.length; ++i) {
            final ExceptionHandler eh = new ExceptionHandler(cegs[i].getCatchType(), cegs[i].getHandlerPC());
            for (InstructionHandle ih = cegs[i].getStartPC(); ih != cegs[i].getEndPC().getNext(); ih = ih.getNext()) {
                HashSet hs = this.exceptionhandlers.get(ih);
                if (hs == null) {
                    hs = new HashSet();
                    this.exceptionhandlers.put(ih, hs);
                }
                hs.add(eh);
            }
        }
    }
    
    public ExceptionHandler[] getExceptionHandlers(final InstructionHandle ih) {
        final HashSet hs = this.exceptionhandlers.get(ih);
        if (hs == null) {
            return new ExceptionHandler[0];
        }
        final ExceptionHandler[] ret = new ExceptionHandler[hs.size()];
        return (ExceptionHandler[])hs.toArray(ret);
    }
}

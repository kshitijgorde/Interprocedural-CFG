// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.exceptions;

import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.Ruby;
import org.jruby.RubyLocalJumpError;
import org.jruby.RubyInstanceConfig;

public class JumpException extends RuntimeException
{
    private static final long serialVersionUID = -228162532535826617L;
    public static final RetryJump RETRY_JUMP;
    public static final RedoJump REDO_JUMP;
    public static final SpecialJump SPECIAL_JUMP;
    
    public JumpException() {
    }
    
    public JumpException(final String msg) {
        super(msg);
    }
    
    public JumpException(final String msg, final Throwable cause) {
        super(msg, cause);
    }
    
    public Throwable fillInStackTrace() {
        if (RubyInstanceConfig.JUMPS_HAVE_BACKTRACE) {
            return this.originalFillInStackTrace();
        }
        return this;
    }
    
    protected Throwable originalFillInStackTrace() {
        return super.fillInStackTrace();
    }
    
    static {
        RETRY_JUMP = new RetryJump();
        REDO_JUMP = new RedoJump();
        SPECIAL_JUMP = new SpecialJump();
    }
    
    public static class FlowControlException extends JumpException implements Unrescuable
    {
        protected int target;
        protected Object value;
        protected final RubyLocalJumpError.Reason reason;
        
        public FlowControlException(final RubyLocalJumpError.Reason reason) {
            this.reason = reason;
        }
        
        public FlowControlException(final RubyLocalJumpError.Reason reason, final int target, final Object value) {
            this.reason = reason;
            this.target = target;
            this.value = value;
        }
        
        public int getTarget() {
            return this.target;
        }
        
        public void setTarget(final int target) {
            this.target = target;
        }
        
        public Object getValue() {
            return this.value;
        }
        
        public void setValue(final Object value) {
            this.value = value;
        }
        
        public RaiseException buildException(final Ruby runtime) {
            switch (this.reason) {
                case RETURN:
                case BREAK:
                case NEXT: {
                    return runtime.newLocalJumpError(this.reason, (IRubyObject)this.value, "unexpected " + this.reason);
                }
                case REDO:
                case RETRY: {
                    return runtime.newLocalJumpError(this.reason, runtime.getNil(), "unexpected " + this.reason);
                }
                default: {
                    return runtime.newLocalJumpError(this.reason, runtime.getNil(), "no reason");
                }
            }
        }
    }
    
    public static class BreakJump extends FlowControlException
    {
        public BreakJump(final int t, final Object v) {
            super(RubyLocalJumpError.Reason.BREAK, t, v);
        }
    }
    
    public static class NextJump extends FlowControlException
    {
        public NextJump(final Object v) {
            super(RubyLocalJumpError.Reason.NEXT, 0, v);
        }
    }
    
    public static class RetryJump extends FlowControlException
    {
        public RetryJump() {
            super(RubyLocalJumpError.Reason.RETRY);
        }
    }
    
    public static class RedoJump extends FlowControlException
    {
        public RedoJump() {
            super(RubyLocalJumpError.Reason.REDO);
        }
    }
    
    public static class SpecialJump extends FlowControlException
    {
        public SpecialJump() {
            super(RubyLocalJumpError.Reason.NOREASON);
        }
    }
    
    public static class ReturnJump extends FlowControlException
    {
        public ReturnJump(final int t, final Object v) {
            super(RubyLocalJumpError.Reason.RETURN, t, v);
        }
    }
}

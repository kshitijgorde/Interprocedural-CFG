// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.compiler.ir;

public enum Operation
{
    COPY(OpType.dont_care), 
    SET_RETADDR(OpType.dont_care), 
    NOT(OpType.dont_care), 
    ADD(OpType.alu_op), 
    SUB(OpType.alu_op), 
    MUL(OpType.alu_op), 
    DIV(OpType.alu_op), 
    RETURN(OpType.ret_op), 
    CLOSURE_RETURN(OpType.ret_op), 
    BREAK(OpType.ret_op), 
    RECV_ARG(OpType.recv_arg_op), 
    RECV_SELF(OpType.recv_arg_op), 
    RECV_CLOSURE(OpType.recv_arg_op), 
    RECV_OPT_ARG(OpType.recv_arg_op), 
    RECV_CLOSURE_ARG(OpType.recv_arg_op), 
    RECV_EXCEPTION(OpType.recv_arg_op), 
    CALL(OpType.call_op), 
    JRUBY_IMPL(OpType.call_op), 
    RUBY_INTERNALS(OpType.call_op), 
    METHOD_LOOKUP(OpType.dont_care), 
    YIELD(OpType.yield_op), 
    DEF_MODULE(OpType.dont_care), 
    DEF_CLASS(OpType.dont_care), 
    DEF_INST_METH(OpType.dont_care), 
    DEF_CLASS_METH(OpType.dont_care), 
    THROW(OpType.exc_op), 
    RETRY(OpType.dont_care), 
    LABEL(OpType.marker_op), 
    EXC_REGION_START(OpType.marker_op), 
    EXC_REGION_END(OpType.marker_op), 
    CASE(OpType.marker_op), 
    LINE_NUM(OpType.debug_op), 
    FILE_NAME(OpType.debug_op), 
    GET_CONST(OpType.load_op), 
    GET_GLOBAL_VAR(OpType.load_op), 
    GET_FIELD(OpType.load_op), 
    GET_CVAR(OpType.load_op), 
    GET_ARRAY(OpType.load_op), 
    BINDING_LOAD(OpType.load_op), 
    SEARCH_CONST(OpType.load_op), 
    PUT_CONST(OpType.store_op), 
    PUT_GLOBAL_VAR(OpType.store_op), 
    PUT_FIELD(OpType.store_op), 
    PUT_ARRAY(OpType.store_op), 
    PUT_CVAR(OpType.store_op), 
    BINDING_STORE(OpType.store_op), 
    ATTR_ASSIGN(OpType.store_op), 
    JUMP(OpType.branch_op), 
    JUMP_INDIRECT(OpType.branch_op), 
    BEQ(OpType.branch_op), 
    BNE(OpType.branch_op), 
    ALLOC_BINDING(OpType.dont_care), 
    THREAD_POLL(OpType.dont_care), 
    DECLARE_TYPE(OpType.declare_type_op), 
    IS_TRUE(OpType.compare_op), 
    EQQ(OpType.compare_op), 
    MODULE_VERSION_GUARD(OpType.guard_op), 
    METHOD_VERSION_GUARD(OpType.guard_op), 
    BOX_VALUE(OpType.box_op), 
    UNBOX_VALUE(OpType.box_op);
    
    private OpType type;
    
    private Operation(final OpType t) {
        this.type = t;
    }
    
    public boolean isALU() {
        return this.type == OpType.alu_op;
    }
    
    public boolean xfersControl() {
        return this.isBranch() || this.isReturn() || this.isException();
    }
    
    public boolean isBranch() {
        return this.type == OpType.branch_op;
    }
    
    public boolean isLoad() {
        return this.type == OpType.load_op;
    }
    
    public boolean isStore() {
        return this.type == OpType.store_op;
    }
    
    public boolean isCall() {
        return this.type == OpType.call_op;
    }
    
    public boolean isEval() {
        return this.type == OpType.eval_op;
    }
    
    public boolean isReturn() {
        return this.type == OpType.ret_op;
    }
    
    public boolean isException() {
        return this.type == OpType.exc_op;
    }
    
    public boolean isArgReceive() {
        return this.type == OpType.recv_arg_op;
    }
    
    public boolean startsBasicBlock() {
        return this == Operation.LABEL;
    }
    
    public boolean endsBasicBlock() {
        return this.xfersControl();
    }
    
    public boolean hasSideEffects() {
        return this.isCall() || this.isEval() || this.isStore() || this.isReturn() || this.isException() || this.type == OpType.yield_op;
    }
    
    public boolean canRaiseException() {
        return this.type != OpType.ret_op && this.type != OpType.debug_op && this.type != OpType.recv_arg_op && this.type != OpType.branch_op && this.type != OpType.marker_op;
    }
    
    public String toString() {
        return this.name().toLowerCase();
    }
}

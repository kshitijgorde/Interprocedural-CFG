// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.compiler.ir;

enum OpType
{
    dont_care, 
    debug_op, 
    obj_op, 
    alu_op, 
    call_op, 
    yield_op, 
    recv_arg_op, 
    ret_op, 
    eval_op, 
    branch_op, 
    compare_op, 
    exc_op, 
    load_op, 
    store_op, 
    declare_type_op, 
    guard_op, 
    box_op, 
    marker_op;
}

// 
// Decompiled by Procyon v0.5.30
// 

package russotto.zplet.zmachine;

import russotto.zplet.zmachine.state.ZState;

public class ZInstruction
{
    protected final short ZFALSE = 0;
    protected final short ZTRUE = 1;
    protected final short ZNOTDONE = 0;
    protected final short ZSAVE_SUCCESS = 1;
    protected final short ZRESTORE_SUCCESS = 2;
    protected static final int OP_JE = 1;
    protected static final int OP_JL = 2;
    protected static final int OP_JG = 3;
    protected static final int OP_DEC_CHK = 4;
    protected static final int OP_INC_CHK = 5;
    protected static final int OP_JIN = 6;
    protected static final int OP_TEST = 7;
    protected static final int OP_OR = 8;
    protected static final int OP_AND = 9;
    protected static final int OP_TEST_ATTR = 10;
    protected static final int OP_SET_ATTR = 11;
    protected static final int OP_CLEAR_ATTR = 12;
    protected static final int OP_STORE = 13;
    protected static final int OP_INSERT_OBJ = 14;
    protected static final int OP_LOADW = 15;
    protected static final int OP_LOADB = 16;
    protected static final int OP_GET_PROP = 17;
    protected static final int OP_GET_PROP_ADDR = 18;
    protected static final int OP_GET_NEXT_PROP = 19;
    protected static final int OP_ADD = 20;
    protected static final int OP_SUB = 21;
    protected static final int OP_MUL = 22;
    protected static final int OP_DIV = 23;
    protected static final int OP_MOD = 24;
    protected static final int OP_JZ = 128;
    protected static final int OP_GET_SIBLING = 129;
    protected static final int OP_GET_CHILD = 130;
    protected static final int OP_GET_PARENT = 131;
    protected static final int OP_GET_PROP_LEN = 132;
    protected static final int OP_INC = 133;
    protected static final int OP_DEC = 134;
    protected static final int OP_PRINT_ADDR = 135;
    public static final int OP_CALL_1S = 136;
    protected static final int OP_REMOVE_OBJ = 137;
    protected static final int OP_PRINT_OBJ = 138;
    protected static final int OP_RET = 139;
    protected static final int OP_JUMP = 140;
    protected static final int OP_PRINT_PADDR = 141;
    protected static final int OP_LOAD = 142;
    protected static final int OP_NOT = 143;
    protected static final int OP_RTRUE = 176;
    protected static final int OP_RFALSE = 177;
    protected static final int OP_PRINT = 178;
    protected static final int OP_PRINT_RET = 179;
    protected static final int OP_NOP = 180;
    protected static final int OP_SAVE = 181;
    protected static final int OP_RESTORE = 182;
    protected static final int OP_RESTART = 183;
    protected static final int OP_RET_POPPED = 184;
    protected static final int OP_POP = 185;
    protected static final int OP_QUIT = 186;
    protected static final int OP_NEW_LINE = 187;
    protected static final int OP_SHOW_STATUS = 188;
    protected static final int OP_VERIFY = 189;
    protected static final int OP_EXTENDED = 190;
    protected static final int OP_PIRACY = 191;
    protected static final int OP_CALL = 224;
    protected static final int OP_STOREW = 225;
    protected static final int OP_STOREB = 226;
    protected static final int OP_PUT_PROP = 227;
    protected static final int OP_SREAD = 228;
    protected static final int OP_PRINT_CHAR = 229;
    protected static final int OP_PRINT_NUM = 230;
    protected static final int OP_RANDOM = 231;
    protected static final int OP_PUSH = 232;
    protected static final int OP_PULL = 233;
    protected static final int OP_SPLIT_WINDOW = 234;
    protected static final int OP_SET_WINDOW = 235;
    protected static final int OP_CALL_VS2 = 236;
    protected static final int OP_OUTPUT_STREAM = 243;
    protected static final int OP_INPUT_STREAM = 244;
    protected static final int OP_SOUND_EFFECT = 245;
    protected static final int OP_CALL_VN2 = 250;
    protected static final int LOWER_WINDOW = 0;
    protected static final int UPPER_WINDOW = 1;
    protected int opnum;
    protected int count;
    protected int save_pc;
    protected short[] operands;
    protected short storevar;
    protected short branchoffset;
    protected boolean branchtype;
    protected ZMachine zm;
    static boolean[] store;
    static boolean[] branch;
    
    protected ZInstruction() {
    }
    
    public ZInstruction(final ZMachine zm) {
        this.zm = zm;
        if (ZInstruction.store == null) {
            ZInstruction.store = new boolean[256];
            ZInstruction.branch = new boolean[256];
            this.setupbs();
        }
        this.operands = new short[4];
    }
    
    public ZInstruction(final int opnum, final int count, final short[] operands, final short storevar, final short branchoffset, final boolean branchtype) {
        this.opnum = opnum;
        this.count = count;
        this.operands = operands;
        this.storevar = storevar;
        this.branchoffset = branchoffset;
        this.branchtype = branchtype;
    }
    
    public ZInstruction(final int n, final int n2, final short[] array) {
        this(n, n2, array, (short)0, (short)0, false);
    }
    
    protected boolean isbranch() {
        return ZInstruction.branch[this.opnum];
    }
    
    protected boolean isstore() {
        return ZInstruction.store[this.opnum];
    }
    
    protected boolean isret() {
        switch (this.opnum) {
            case 139:
            case 176:
            case 177:
            case 179:
            case 184: {
                return true;
            }
            default: {
                return false;
            }
        }
    }
    
    public void decode_instruction() {
        this.decode_first_half();
        this.save_pc = this.zm.pc;
        this.decode_second_half();
    }
    
    protected void decode_first_half() {
        int get_code_byte = 0;
        int n = 0;
        final int n2 = this.zm.get_code_byte() & 0xFF;
        if (n2 == 190) {
            n = 192;
        }
        switch (n | (n2 & 0xC0)) {
            case 192: {
                if (n != 192) {
                    this.opnum = n2;
                }
                else {
                    this.opnum = (0x100 | (this.zm.get_code_byte() & 0xFF));
                }
                if ((n2 & 0x20) == 0x0) {
                    this.opnum = (n2 & 0x1F);
                }
                this.count = 0;
                int get_code_byte2 = this.zm.get_code_byte();
                int n3;
                if (this.opnum == 236 || this.opnum == 250) {
                    n3 = 2;
                    get_code_byte = this.zm.get_code_byte();
                }
                else {
                    n3 = 1;
                }
                while (n3-- != 0) {
                    final int n4 = (get_code_byte2 & 0xC0) >> 6;
                    if (n4 == 3) {
                        break;
                    }
                    this.operands[this.count++] = this.zm.get_operand(n4);
                    final int n5 = (get_code_byte2 & 0x30) >> 4;
                    if (n5 == 3) {
                        break;
                    }
                    this.operands[this.count++] = this.zm.get_operand(n5);
                    final int n6 = (get_code_byte2 & 0xC) >> 2;
                    if (n6 == 3) {
                        break;
                    }
                    this.operands[this.count++] = this.zm.get_operand(n6);
                    final int n7 = get_code_byte2 & 0x3;
                    if (n7 == 3) {
                        break;
                    }
                    this.operands[this.count++] = this.zm.get_operand(n7);
                    get_code_byte2 = get_code_byte;
                }
                break;
            }
            case 128: {
                final int n8 = (n2 & 0x30) >> 4;
                if (n8 == 3) {
                    this.opnum = n2;
                    this.count = 0;
                    break;
                }
                this.opnum = (n2 & 0x8F);
                this.count = 1;
                this.operands[0] = this.zm.get_operand(n8);
                break;
            }
            default: {
                this.opnum = (n2 & 0x1F);
                this.count = 2;
                this.operands[0] = this.zm.get_operand(((n2 & 0x40) >> 6) + 1);
                this.operands[1] = this.zm.get_operand(((n2 & 0x20) >> 5) + 1);
                break;
            }
        }
    }
    
    protected void decode_second_half() {
        if (this.isstore()) {
            this.storevar = this.zm.get_code_byte();
        }
        if (this.isbranch()) {
            this.branchoffset = this.zm.get_code_byte();
            this.branchtype = ((this.branchoffset & 0x80) != 0x0);
            if ((this.branchoffset & 0x40) != 0x0) {
                this.branchoffset &= 0x3F;
            }
            else if ((this.branchoffset & 0x20) != 0x0) {
                this.branchoffset = (short)(0xC000 | (this.branchoffset << 8 | (this.zm.get_code_byte() & 0xFF)));
            }
            else {
                this.branchoffset = (short)((this.branchoffset & 0x3F) << 8 | (this.zm.get_code_byte() & 0xFF));
            }
        }
    }
    
    public void execute() {
        short n = 0;
        switch (this.opnum) {
            case 1: {
                n = this.op_je();
                break;
            }
            case 2: {
                n = this.op_jl();
                break;
            }
            case 3: {
                n = this.op_jg();
                break;
            }
            case 4: {
                n = this.op_dec_chk();
                break;
            }
            case 5: {
                n = this.op_inc_chk();
                break;
            }
            case 6: {
                n = this.op_jin();
                break;
            }
            case 7: {
                n = this.op_test();
                break;
            }
            case 8: {
                n = this.op_or();
                break;
            }
            case 9: {
                n = this.op_and();
                break;
            }
            case 10: {
                n = this.op_test_attr();
                break;
            }
            case 11: {
                n = this.op_set_attr();
                break;
            }
            case 12: {
                n = this.op_clear_attr();
                break;
            }
            case 13: {
                n = this.op_store();
                break;
            }
            case 14: {
                n = this.op_insert_obj();
                break;
            }
            case 15: {
                n = this.op_loadw();
                break;
            }
            case 16: {
                n = this.op_loadb();
                break;
            }
            case 17: {
                n = this.op_get_prop();
                break;
            }
            case 18: {
                n = this.op_get_prop_addr();
                break;
            }
            case 19: {
                n = this.op_get_next_prop();
                break;
            }
            case 20: {
                n = this.op_add();
                break;
            }
            case 21: {
                n = this.op_sub();
                break;
            }
            case 22: {
                n = this.op_mul();
                break;
            }
            case 23: {
                n = this.op_div();
                break;
            }
            case 24: {
                n = this.op_mod();
                break;
            }
            case 128: {
                n = this.op_jz();
                break;
            }
            case 129: {
                n = this.op_get_sibling();
                break;
            }
            case 130: {
                n = this.op_get_child();
                break;
            }
            case 131: {
                n = this.op_get_parent();
                break;
            }
            case 132: {
                n = this.op_get_prop_len();
                break;
            }
            case 133: {
                n = this.op_inc();
                break;
            }
            case 134: {
                n = this.op_dec();
                break;
            }
            case 135: {
                n = this.op_print_addr();
                break;
            }
            case 136: {
                n = this.op_call_1s();
                break;
            }
            case 137: {
                n = this.op_remove_obj();
                break;
            }
            case 138: {
                n = this.op_print_obj();
                break;
            }
            case 139: {
                n = this.op_ret();
                break;
            }
            case 140: {
                n = this.op_jump();
                break;
            }
            case 141: {
                n = this.op_print_paddr();
                break;
            }
            case 142: {
                n = this.op_load();
                break;
            }
            case 143: {
                n = this.op_not();
                break;
            }
            case 176: {
                n = this.op_rtrue();
                break;
            }
            case 177: {
                n = this.op_rfalse();
                break;
            }
            case 178: {
                n = this.op_print();
                break;
            }
            case 179: {
                n = this.op_print_ret();
                break;
            }
            case 180: {
                n = this.op_nop();
                break;
            }
            case 181: {
                n = this.op_save();
                break;
            }
            case 182: {
                n = this.op_restore();
                break;
            }
            case 183: {
                n = this.op_restart();
                break;
            }
            case 184: {
                n = this.op_ret_popped();
                break;
            }
            case 185: {
                n = this.op_pop();
                break;
            }
            case 186: {
                n = this.op_quit();
                break;
            }
            case 187: {
                n = this.op_new_line();
                break;
            }
            case 188: {
                n = this.op_show_status();
                break;
            }
            case 189: {
                n = this.op_verify();
                break;
            }
            case 190: {
                n = this.op_extended();
                break;
            }
            case 191: {
                n = this.op_piracy();
                break;
            }
            case 224: {
                n = this.op_call();
                break;
            }
            case 225: {
                n = this.op_storew();
                break;
            }
            case 226: {
                n = this.op_storeb();
                break;
            }
            case 227: {
                n = this.op_put_prop();
                break;
            }
            case 228: {
                n = this.op_sread();
                break;
            }
            case 229: {
                n = this.op_print_char();
                break;
            }
            case 230: {
                n = this.op_print_num();
                break;
            }
            case 231: {
                n = this.op_random();
                break;
            }
            case 232: {
                n = this.op_push();
                break;
            }
            case 233: {
                n = this.op_pull();
                break;
            }
            case 234: {
                n = this.op_split_window();
                break;
            }
            case 235: {
                n = this.op_set_window();
                break;
            }
            case 243: {
                n = this.op_output_stream();
                break;
            }
            case 244: {
                n = this.op_input_stream();
                break;
            }
            case 245: {
                n = this.op_sound_effect();
                break;
            }
            default: {
                n = this.op_illegal();
                break;
            }
        }
        if ((this.opnum != 224 && this.isstore()) || this.isret()) {
            this.zm.set_variable(this.storevar, n);
        }
        if (this.isbranch() && n == 0 != this.branchtype) {
            switch (this.branchoffset) {
                case 0: {
                    this.z_ret();
                    this.zm.set_variable(this.storevar, (short)0);
                    break;
                }
                case 1: {
                    this.z_ret();
                    this.zm.set_variable(this.storevar, (short)1);
                    break;
                }
                default: {
                    final ZMachine zm = this.zm;
                    zm.pc += this.branchoffset - 2;
                    break;
                }
            }
        }
    }
    
    protected short op_illegal() {
        this.zm.fatal("Unknown opcode");
        return 0;
    }
    
    protected short op_je() {
        for (int i = 1; i < this.count; ++i) {
            if (this.operands[0] == this.operands[i]) {
                return 1;
            }
        }
        return 0;
    }
    
    protected short op_jl() {
        if (this.operands[0] < this.operands[1]) {
            return 1;
        }
        return 0;
    }
    
    protected short op_jg() {
        if (this.operands[0] > this.operands[1]) {
            return 1;
        }
        return 0;
    }
    
    protected short op_dec_chk() {
        final short n = (short)(this.zm.get_variable(this.operands[0]) - 1);
        this.zm.set_variable(this.operands[0], n);
        if (n < this.operands[1]) {
            return 1;
        }
        return 0;
    }
    
    protected short op_inc_chk() {
        final short n = (short)(this.zm.get_variable(this.operands[0]) + 1);
        this.zm.set_variable(this.operands[0], n);
        if (n > this.operands[1]) {
            return 1;
        }
        return 0;
    }
    
    protected short op_jin() {
        if (this.zm.objects.parent(this.operands[0]) == this.operands[1]) {
            return 1;
        }
        return 0;
    }
    
    protected short op_test() {
        if ((this.operands[0] & this.operands[1]) == this.operands[1]) {
            return 1;
        }
        return 0;
    }
    
    protected short op_or() {
        return (short)((this.operands[0] | this.operands[1]) & 0xFFFF);
    }
    
    protected short op_and() {
        return (short)(this.operands[0] & this.operands[1] & 0xFFFF);
    }
    
    protected short op_test_attr() {
        if (this.zm.objects.attribute(this.operands[0], this.operands[1])) {
            return 1;
        }
        return 0;
    }
    
    protected short op_set_attr() {
        this.zm.objects.set_attribute(this.operands[0], this.operands[1]);
        return 0;
    }
    
    protected short op_clear_attr() {
        this.zm.objects.clear_attribute(this.operands[0], this.operands[1]);
        return 0;
    }
    
    protected short op_store() {
        this.zm.set_variable(this.operands[0], this.operands[1]);
        return 0;
    }
    
    protected short op_insert_obj() {
        this.detach_obj(this.operands[0]);
        final short child = this.zm.objects.child(this.operands[1]);
        this.zm.objects.set_child(this.operands[1], this.operands[0]);
        this.zm.objects.set_sibling(this.operands[0], child);
        this.zm.objects.set_parent(this.operands[0], this.operands[1]);
        return 0;
    }
    
    protected short op_loadw() {
        final int n = (this.operands[0] & 0xFFFF) + (this.operands[1] & 0xFFFF) * 2;
        return (short)((this.zm.memory_image[n] << 8 & 0xFF00) | (this.zm.memory_image[n + 1] & 0xFF));
    }
    
    protected short op_loadb() {
        return (short)(this.zm.memory_image[(this.operands[0] & 0xFFFF) + (this.operands[1] & 0xFFFF)] & 0xFF);
    }
    
    protected short op_get_prop() {
        return this.zm.objects.prop(this.operands[0], this.operands[1]);
    }
    
    protected short op_get_prop_addr() {
        return this.zm.objects.prop_address(this.operands[0], this.operands[1]);
    }
    
    protected short op_get_next_prop() {
        return this.zm.objects.next_prop(this.operands[0], this.operands[1]);
    }
    
    protected short op_add() {
        return (short)(this.operands[0] + this.operands[1]);
    }
    
    protected short op_sub() {
        return (short)(this.operands[0] - this.operands[1]);
    }
    
    protected short op_mul() {
        return (short)(this.operands[0] * this.operands[1]);
    }
    
    protected short op_div() {
        final short n = this.operands[0];
        final short n2 = this.operands[1];
        if (n2 == 0) {
            this.zm.fatal("Remainder from division by zero");
        }
        return (short)(n / n2);
    }
    
    protected short op_mod() {
        final short n = this.operands[0];
        final short n2 = this.operands[1];
        if (n2 == 0) {
            this.zm.fatal("Remainder from division by zero");
        }
        return (short)(n % n2);
    }
    
    protected short op_jz() {
        if (this.operands[0] == 0) {
            return 1;
        }
        return 0;
    }
    
    protected short op_get_sibling() {
        return this.zm.objects.sibling(this.operands[0]);
    }
    
    protected short op_get_child() {
        return this.zm.objects.child(this.operands[0]);
    }
    
    protected short op_get_parent() {
        return this.zm.objects.parent(this.operands[0]);
    }
    
    protected short op_get_prop_len() {
        return this.zm.objects.prop_len(this.operands[0]);
    }
    
    protected short op_inc() {
        this.zm.set_variable(this.operands[0], (short)(this.zm.get_variable(this.operands[0]) + 1));
        return 0;
    }
    
    protected short op_dec() {
        this.zm.set_variable(this.operands[0], (short)(this.zm.get_variable(this.operands[0]) - 1));
        return 0;
    }
    
    protected short op_print_addr() {
        this.zm.print_string(this.operands[0] & 0xFFFF);
        return 0;
    }
    
    protected short op_call_1s() {
        return 0;
    }
    
    void detach_obj(final short n) {
        final short parent = this.zm.objects.parent(n);
        if (parent != 0) {
            short n2 = this.zm.objects.child(parent);
            if (n2 == n) {
                this.zm.objects.set_child(parent, this.zm.objects.sibling(n));
            }
            else {
                while (this.zm.objects.sibling(n2) != n) {
                    n2 = this.zm.objects.sibling(n2);
                    if (n2 == 0) {
                        this.zm.fatal("Malformed object tree");
                    }
                }
                this.zm.objects.set_sibling(n2, this.zm.objects.sibling(n));
            }
        }
    }
    
    protected short op_remove_obj() {
        this.detach_obj(this.operands[0]);
        this.zm.objects.set_parent(this.operands[0], (short)0);
        this.zm.objects.set_sibling(this.operands[0], (short)0);
        return 0;
    }
    
    protected short op_print_obj() {
        this.zm.print_string(this.zm.objects.short_name_addr(this.operands[0]));
        return 1;
    }
    
    protected void z_ret() {
        short[] pop;
        do {
            pop = this.zm.zstack.pop();
        } while (!(pop instanceof short[]));
        this.zm.locals = pop;
        this.zm.pc = this.zm.zstack.pop();
        this.storevar = (short)(int)this.zm.zstack.pop();
        this.zm.zstack.pop();
    }
    
    protected short op_ret() {
        this.z_ret();
        return this.operands[0];
    }
    
    protected short op_jump() {
        final ZMachine zm = this.zm;
        zm.pc += this.operands[0] - 2;
        return 0;
    }
    
    protected short op_print_paddr() {
        this.zm.print_string(this.zm.string_address(this.operands[0]));
        return 0;
    }
    
    protected short op_load() {
        return this.zm.get_variable(this.operands[0]);
    }
    
    protected short op_not() {
        return (short)~this.operands[0];
    }
    
    protected short op_rtrue() {
        this.z_ret();
        return 1;
    }
    
    protected short op_rfalse() {
        this.z_ret();
        return 0;
    }
    
    protected short op_print() {
        final int print_string = this.zm.print_string(this.zm.pc);
        final ZMachine zm = this.zm;
        zm.pc += print_string;
        return 0;
    }
    
    protected short op_print_ret() {
        this.zm.print_string(this.zm.pc);
        this.zm.print_ascii_char((short)13);
        this.z_ret();
        return 1;
    }
    
    protected short op_nop() {
        return 0;
    }
    
    protected short op_save() {
        if (new ZState(this.zm).disk_save(this.zm.screen.getFrame(), this.save_pc)) {
            return 1;
        }
        return 0;
    }
    
    protected short op_restore() {
        final ZState zState = new ZState(this.zm);
        if (zState.restore_from_disk(this.zm.screen.getFrame())) {
            this.zm.restore(zState);
            this.decode_second_half();
            return 2;
        }
        return 0;
    }
    
    protected short op_restart() {
        this.zm.restart();
        return 0;
    }
    
    protected short op_ret_popped() {
        final short get_variable = this.zm.get_variable((short)0);
        this.z_ret();
        return get_variable;
    }
    
    protected short op_pop() {
        this.zm.get_variable((short)0);
        return 0;
    }
    
    protected short op_quit() {
        this.zm.print_ascii_char((short)42);
        this.zm.print_ascii_char((short)42);
        this.zm.print_ascii_char((short)42);
        this.zm.print_ascii_char((short)69);
        this.zm.print_ascii_char((short)78);
        this.zm.print_ascii_char((short)68);
        this.zm.print_ascii_char((short)32);
        this.zm.print_ascii_char((short)79);
        this.zm.print_ascii_char((short)70);
        this.zm.print_ascii_char((short)32);
        this.zm.print_ascii_char((short)83);
        this.zm.print_ascii_char((short)69);
        this.zm.print_ascii_char((short)83);
        this.zm.print_ascii_char((short)83);
        this.zm.print_ascii_char((short)73);
        this.zm.print_ascii_char((short)79);
        this.zm.print_ascii_char((short)78);
        this.zm.print_ascii_char((short)42);
        this.zm.print_ascii_char((short)42);
        this.zm.print_ascii_char((short)42);
        this.zm.print_ascii_char((short)13);
        this.zm.stop();
        return 0;
    }
    
    protected short op_new_line() {
        this.zm.print_ascii_char((short)13);
        return 0;
    }
    
    protected short op_show_status() {
        this.zm.update_status_line();
        return 0;
    }
    
    protected short op_verify() {
        final int file_length = this.zm.header.file_length();
        if (file_length > this.zm.memory_image.length || this.zm.header.checksum() != this.zm.checksum) {
            System.err.println("VERIFY failed: ");
            System.err.println("\texpected\tactual");
            System.err.println("length\t" + file_length + "\t" + this.zm.memory_image.length);
            System.err.println("checksum\t" + Integer.toString(this.zm.header.checksum() & 0xFFFF, 16) + "\t" + Integer.toString(this.zm.checksum, 16));
            return 0;
        }
        return 1;
    }
    
    protected short op_extended() {
        return 0;
    }
    
    protected short op_piracy() {
        return 0;
    }
    
    protected short op_call() {
        if (this.operands[0] == 0) {
            this.zm.set_variable(this.storevar, (short)0);
        }
        else {
            this.zm.zstack.push(new ZFrameBound(this.isstore()));
            this.zm.zstack.push(new Integer(this.storevar));
            this.zm.zstack.push(new Integer(this.zm.pc));
            this.zm.zstack.push(this.zm.locals);
            this.zm.pc = this.zm.routine_address(this.operands[0]);
            final byte get_code_byte = this.zm.get_code_byte();
            this.zm.locals = new short[get_code_byte];
            for (byte b = 0; b < get_code_byte; ++b) {
                final short n = (short)((this.zm.get_code_byte() << 8 & 0xFF00) | (this.zm.get_code_byte() & 0xFF));
                if (b < this.count - 1) {
                    this.zm.locals[b] = this.operands[b + 1];
                }
                else {
                    this.zm.locals[b] = n;
                }
            }
        }
        return 0;
    }
    
    protected short op_storew() {
        final int n = (this.operands[0] & 0xFFFF) + (this.operands[1] & 0xFFFF) * 2;
        this.zm.memory_image[n] = (byte)(this.operands[2] >>> 8);
        this.zm.memory_image[n + 1] = (byte)(this.operands[2] & 0xFF);
        return 0;
    }
    
    protected short op_storeb() {
        this.zm.memory_image[(this.operands[0] & 0xFFFF) + (this.operands[1] & 0xFFFF)] = (byte)(this.operands[2] & 0xFF);
        return 0;
    }
    
    protected short op_put_prop() {
        this.zm.objects.put_prop(this.operands[0], this.operands[1], this.operands[2]);
        return 0;
    }
    
    protected short op_sread() {
        final int n = this.operands[0] & 0xFFFF;
        this.zm.update_status_line();
        this.zm.current_window.flush();
        this.zm.current_window.reset_line_count();
        int n2 = this.zm.memory_image[n] & 0xFF;
        if (n2 < 2) {
            this.zm.fatal("Text Buffer < 3 bytes");
        }
        --n2;
        int n3 = 1;
        byte get_input_byte;
        while (n2 != 0 && (get_input_byte = this.zm.get_input_byte(true)) != 13 && get_input_byte != 10) {
            if (get_input_byte >= 65 && get_input_byte <= 90) {
                get_input_byte = (byte)(get_input_byte - 65 + 97);
            }
            this.zm.memory_image[n + n3] = get_input_byte;
            ++n3;
            --n2;
        }
        this.zm.memory_image[n + n3] = 0;
        this.zm.zd.tokenise(n + 1, n3 - 1, this.operands[1] & 0xFFFF);
        return 0;
    }
    
    protected short op_print_char() {
        this.zm.print_ascii_char(this.operands[0]);
        return 0;
    }
    
    protected short op_print_num() {
        final char[] charArray = Integer.toString(this.operands[0], 10).toCharArray();
        for (int i = 0; i < charArray.length; ++i) {
            this.zm.print_ascii_char((short)charArray[i]);
        }
        return 0;
    }
    
    protected short op_random() {
        if (this.operands[0] == 0) {
            this.zm.zrandom.setSeed(System.currentTimeMillis());
            return 0;
        }
        if (this.operands[0] < 0) {
            this.zm.zrandom.setSeed(-this.operands[0]);
            return 0;
        }
        return (short)((this.zm.zrandom.nextInt() & 0x7FFF) % this.operands[0] + 1);
    }
    
    protected short op_push() {
        this.zm.set_variable((short)0, this.operands[0]);
        return 0;
    }
    
    protected short op_pull() {
        this.zm.set_variable(this.operands[0], this.zm.get_variable((short)0));
        return 0;
    }
    
    protected void split_screen(final int n) {
        this.zm.window[1].flush();
        this.zm.window[0].flush();
        final int getx = this.zm.window[0].getx();
        int n2 = this.zm.window[0].gety() + this.zm.window[1].getlines() - n;
        if (n2 < 0) {
            n2 = 0;
        }
        this.zm.window[0].moveto(0, n);
        this.zm.window[1].moveto(0, 0);
        this.zm.window[0].resize(this.zm.screen.getchars(), this.zm.screen.getlines() - n);
        this.zm.window[1].resize(this.zm.screen.getchars(), n);
        if (n2 >= this.zm.screen.getlines() - n) {
            n2 = this.zm.screen.getlines() - n - 1;
        }
        this.zm.window[0].movecursor(getx, n2);
    }
    
    protected short op_split_window() {
        this.split_screen(this.operands[0]);
        return 0;
    }
    
    protected short op_set_window() {
        this.zm.current_window.flush();
        this.zm.current_window = this.zm.window[this.operands[0]];
        if (this.operands[0] == 1) {
            this.zm.current_window.movecursor(0, 0);
        }
        return 0;
    }
    
    protected short op_output_stream() {
        this.zm.current_window.flush();
        if (this.operands[0] == 3) {
            this.zm.printmemory = (this.operands[1] & 0xFFFF);
            this.zm.memory_image[this.zm.printmemory] = 0;
            this.zm.memory_image[this.zm.printmemory + 1] = 0;
        }
        else if (this.operands[0] == 2) {
            this.zm.header.set_transcripting(true);
        }
        else if (this.operands[0] == -2) {
            this.zm.header.set_transcripting(false);
        }
        if (this.operands[0] > 0) {
            this.zm.outputs[this.operands[0]] = true;
        }
        else {
            this.zm.outputs[-this.operands[0]] = false;
        }
        return 0;
    }
    
    protected short op_input_stream() {
        this.zm.inputstream = this.operands[0];
        return 0;
    }
    
    protected short op_sound_effect() {
        return 0;
    }
    
    protected void setupbs() {
        ZInstruction.branch[1] = true;
        ZInstruction.branch[2] = true;
        ZInstruction.branch[3] = true;
        ZInstruction.branch[4] = true;
        ZInstruction.branch[5] = true;
        ZInstruction.branch[6] = true;
        ZInstruction.branch[7] = true;
        ZInstruction.store[8] = true;
        ZInstruction.store[9] = true;
        ZInstruction.branch[10] = true;
        ZInstruction.store[15] = true;
        ZInstruction.store[16] = true;
        ZInstruction.store[17] = true;
        ZInstruction.store[18] = true;
        ZInstruction.store[19] = true;
        ZInstruction.store[20] = true;
        ZInstruction.store[21] = true;
        ZInstruction.store[22] = true;
        ZInstruction.store[23] = true;
        ZInstruction.store[24] = true;
        ZInstruction.branch[128] = true;
        ZInstruction.branch[129] = true;
        ZInstruction.store[129] = true;
        ZInstruction.branch[130] = true;
        ZInstruction.store[130] = true;
        ZInstruction.store[131] = true;
        ZInstruction.store[132] = true;
        ZInstruction.store[142] = true;
        ZInstruction.store[143] = true;
        ZInstruction.branch[181] = true;
        ZInstruction.branch[182] = true;
        ZInstruction.branch[189] = true;
        ZInstruction.branch[191] = true;
        ZInstruction.store[224] = true;
        ZInstruction.store[231] = true;
    }
    
    static {
        ZInstruction.store = null;
        ZInstruction.branch = null;
    }
}

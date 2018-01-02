// 
// Decompiled by Procyon v0.5.30
// 

class ZInstruction
{
    final short ZFALSE = 0;
    final short ZTRUE = 1;
    final short ZNOTDONE = 0;
    final short ZSAVE_SUCCESS = 1;
    final short ZRESTORE_SUCCESS = 2;
    static final int OP_JE = 1;
    static final int OP_JL = 2;
    static final int OP_JG = 3;
    static final int OP_DEC_CHK = 4;
    static final int OP_INC_CHK = 5;
    static final int OP_JIN = 6;
    static final int OP_TEST = 7;
    static final int OP_OR = 8;
    static final int OP_AND = 9;
    static final int OP_TEST_ATTR = 10;
    static final int OP_SET_ATTR = 11;
    static final int OP_CLEAR_ATTR = 12;
    static final int OP_STORE = 13;
    static final int OP_INSERT_OBJ = 14;
    static final int OP_LOADW = 15;
    static final int OP_LOADB = 16;
    static final int OP_GET_PROP = 17;
    static final int OP_GET_PROP_ADDR = 18;
    static final int OP_GET_NEXT_PROP = 19;
    static final int OP_ADD = 20;
    static final int OP_SUB = 21;
    static final int OP_MUL = 22;
    static final int OP_DIV = 23;
    static final int OP_MOD = 24;
    static final int OP_JZ = 128;
    static final int OP_GET_SIBLING = 129;
    static final int OP_GET_CHILD = 130;
    static final int OP_GET_PARENT = 131;
    static final int OP_GET_PROP_LEN = 132;
    static final int OP_INC = 133;
    static final int OP_DEC = 134;
    static final int OP_PRINT_ADDR = 135;
    static final int OP_CALL_1S = 136;
    static final int OP_REMOVE_OBJ = 137;
    static final int OP_PRINT_OBJ = 138;
    static final int OP_RET = 139;
    static final int OP_JUMP = 140;
    static final int OP_PRINT_PADDR = 141;
    static final int OP_LOAD = 142;
    static final int OP_NOT = 143;
    static final int OP_RTRUE = 176;
    static final int OP_RFALSE = 177;
    static final int OP_PRINT = 178;
    static final int OP_PRINT_RET = 179;
    static final int OP_NOP = 180;
    static final int OP_SAVE = 181;
    static final int OP_RESTORE = 182;
    static final int OP_RESTART = 183;
    static final int OP_RET_POPPED = 184;
    static final int OP_POP = 185;
    static final int OP_QUIT = 186;
    static final int OP_NEW_LINE = 187;
    static final int OP_SHOW_STATUS = 188;
    static final int OP_VERIFY = 189;
    static final int OP_EXTENDED = 190;
    static final int OP_PIRACY = 191;
    static final int OP_CALL = 224;
    static final int OP_STOREW = 225;
    static final int OP_STOREB = 226;
    static final int OP_PUT_PROP = 227;
    static final int OP_SREAD = 228;
    static final int OP_PRINT_CHAR = 229;
    static final int OP_PRINT_NUM = 230;
    static final int OP_RANDOM = 231;
    static final int OP_PUSH = 232;
    static final int OP_PULL = 233;
    static final int OP_SPLIT_WINDOW = 234;
    static final int OP_SET_WINDOW = 235;
    static final int OP_CALL_VS2 = 236;
    static final int OP_OUTPUT_STREAM = 243;
    static final int OP_INPUT_STREAM = 244;
    static final int OP_SOUND_EFFECT = 245;
    static final int OP_CALL_VN2 = 250;
    static final int LOWER_WINDOW = 0;
    static final int UPPER_WINDOW = 1;
    int opnum;
    int count;
    int save_pc;
    short[] operands;
    short storevar;
    short branchoffset;
    boolean branchtype;
    ZMachine zm;
    static boolean[] store;
    static boolean[] branch;
    
    protected ZInstruction() {
    }
    
    ZInstruction(final ZMachine zm) {
        this.zm = zm;
        if (ZInstruction.store == null) {
            ZInstruction.store = new boolean[256];
            ZInstruction.branch = new boolean[256];
            this.setupbs();
        }
        this.operands = new short[4];
    }
    
    ZInstruction(final int opnum, final int count, final short[] operands, final short storevar, final short branchoffset, final boolean branchtype) {
        this.opnum = opnum;
        this.count = count;
        this.operands = operands;
        this.storevar = storevar;
        this.branchoffset = branchoffset;
        this.branchtype = branchtype;
    }
    
    ZInstruction(final int opnum, final int count, final short[] operands) {
        this(opnum, count, operands, (short)0, (short)0, false);
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
    
    void decode_instruction() {
        this.decode_first_half();
        this.save_pc = this.zm.pc;
        this.decode_second_half();
    }
    
    protected void decode_first_half() {
        int optypes2 = 0;
        int isextended = 0;
        final int opcode = this.zm.get_code_byte() & 0xFF;
        if (opcode == 190) {
            isextended = 192;
        }
        switch (isextended | (opcode & 0xC0)) {
            case 192: {
                if (isextended != 192) {
                    this.opnum = opcode;
                }
                else {
                    this.opnum = (0x100 | (this.zm.get_code_byte() & 0xFF));
                }
                if ((opcode & 0x20) == 0x0) {
                    this.opnum = (opcode & 0x1F);
                }
                this.count = 0;
                int optypes3 = this.zm.get_code_byte();
                int optypebytes;
                if (this.opnum == 236 || this.opnum == 250) {
                    optypebytes = 2;
                    optypes2 = this.zm.get_code_byte();
                }
                else {
                    optypebytes = 1;
                }
                while (optypebytes-- != 0) {
                    int optype = (optypes3 & 0xC0) >> 6;
                    if (optype == 3) {
                        break;
                    }
                    this.operands[this.count++] = this.zm.get_operand(optype);
                    optype = (optypes3 & 0x30) >> 4;
                    if (optype == 3) {
                        break;
                    }
                    this.operands[this.count++] = this.zm.get_operand(optype);
                    optype = (optypes3 & 0xC) >> 2;
                    if (optype == 3) {
                        break;
                    }
                    this.operands[this.count++] = this.zm.get_operand(optype);
                    optype = (optypes3 & 0x3);
                    if (optype == 3) {
                        break;
                    }
                    this.operands[this.count++] = this.zm.get_operand(optype);
                    optypes3 = optypes2;
                }
                break;
            }
            case 128: {
                final int optype = (opcode & 0x30) >> 4;
                if (optype == 3) {
                    this.opnum = opcode;
                    this.count = 0;
                    break;
                }
                this.opnum = (opcode & 0x8F);
                this.count = 1;
                this.operands[0] = this.zm.get_operand(optype);
                break;
            }
            default: {
                this.opnum = (opcode & 0x1F);
                this.count = 2;
                int optype = ((opcode & 0x40) >> 6) + 1;
                this.operands[0] = this.zm.get_operand(optype);
                optype = ((opcode & 0x20) >> 5) + 1;
                this.operands[1] = this.zm.get_operand(optype);
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
    
    void execute() {
        short result = 0;
        switch (this.opnum) {
            case 1: {
                result = this.op_je();
                break;
            }
            case 2: {
                result = this.op_jl();
                break;
            }
            case 3: {
                result = this.op_jg();
                break;
            }
            case 4: {
                result = this.op_dec_chk();
                break;
            }
            case 5: {
                result = this.op_inc_chk();
                break;
            }
            case 6: {
                result = this.op_jin();
                break;
            }
            case 7: {
                result = this.op_test();
                break;
            }
            case 8: {
                result = this.op_or();
                break;
            }
            case 9: {
                result = this.op_and();
                break;
            }
            case 10: {
                result = this.op_test_attr();
                break;
            }
            case 11: {
                result = this.op_set_attr();
                break;
            }
            case 12: {
                result = this.op_clear_attr();
                break;
            }
            case 13: {
                result = this.op_store();
                break;
            }
            case 14: {
                result = this.op_insert_obj();
                break;
            }
            case 15: {
                result = this.op_loadw();
                break;
            }
            case 16: {
                result = this.op_loadb();
                break;
            }
            case 17: {
                result = this.op_get_prop();
                break;
            }
            case 18: {
                result = this.op_get_prop_addr();
                break;
            }
            case 19: {
                result = this.op_get_next_prop();
                break;
            }
            case 20: {
                result = this.op_add();
                break;
            }
            case 21: {
                result = this.op_sub();
                break;
            }
            case 22: {
                result = this.op_mul();
                break;
            }
            case 23: {
                result = this.op_div();
                break;
            }
            case 24: {
                result = this.op_mod();
                break;
            }
            case 128: {
                result = this.op_jz();
                break;
            }
            case 129: {
                result = this.op_get_sibling();
                break;
            }
            case 130: {
                result = this.op_get_child();
                break;
            }
            case 131: {
                result = this.op_get_parent();
                break;
            }
            case 132: {
                result = this.op_get_prop_len();
                break;
            }
            case 133: {
                result = this.op_inc();
                break;
            }
            case 134: {
                result = this.op_dec();
                break;
            }
            case 135: {
                result = this.op_print_addr();
                break;
            }
            case 136: {
                result = this.op_call_1s();
                break;
            }
            case 137: {
                result = this.op_remove_obj();
                break;
            }
            case 138: {
                result = this.op_print_obj();
                break;
            }
            case 139: {
                result = this.op_ret();
                break;
            }
            case 140: {
                result = this.op_jump();
                break;
            }
            case 141: {
                result = this.op_print_paddr();
                break;
            }
            case 142: {
                result = this.op_load();
                break;
            }
            case 143: {
                result = this.op_not();
                break;
            }
            case 176: {
                result = this.op_rtrue();
                break;
            }
            case 177: {
                result = this.op_rfalse();
                break;
            }
            case 178: {
                result = this.op_print();
                break;
            }
            case 179: {
                result = this.op_print_ret();
                break;
            }
            case 180: {
                result = this.op_nop();
                break;
            }
            case 181: {
                result = this.op_save();
                break;
            }
            case 182: {
                result = this.op_restore();
                break;
            }
            case 183: {
                result = this.op_restart();
                break;
            }
            case 184: {
                result = this.op_ret_popped();
                break;
            }
            case 185: {
                result = this.op_pop();
                break;
            }
            case 186: {
                result = this.op_quit();
                break;
            }
            case 187: {
                result = this.op_new_line();
                break;
            }
            case 188: {
                result = this.op_show_status();
                break;
            }
            case 189: {
                result = this.op_verify();
                break;
            }
            case 190: {
                result = this.op_extended();
                break;
            }
            case 191: {
                result = this.op_piracy();
                break;
            }
            case 224: {
                result = this.op_call();
                break;
            }
            case 225: {
                result = this.op_storew();
                break;
            }
            case 226: {
                result = this.op_storeb();
                break;
            }
            case 227: {
                result = this.op_put_prop();
                break;
            }
            case 228: {
                result = this.op_sread();
                break;
            }
            case 229: {
                result = this.op_print_char();
                break;
            }
            case 230: {
                result = this.op_print_num();
                break;
            }
            case 231: {
                result = this.op_random();
                break;
            }
            case 232: {
                result = this.op_push();
                break;
            }
            case 233: {
                result = this.op_pull();
                break;
            }
            case 234: {
                result = this.op_split_window();
                break;
            }
            case 235: {
                result = this.op_set_window();
                break;
            }
            case 243: {
                result = this.op_output_stream();
                break;
            }
            case 244: {
                result = this.op_input_stream();
                break;
            }
            case 245: {
                result = this.op_sound_effect();
                break;
            }
            default: {
                result = this.op_illegal();
                break;
            }
        }
        if ((this.opnum != 224 && this.isstore()) || this.isret()) {
            this.zm.set_variable(this.storevar, result);
        }
        if (this.isbranch() && result == 0 != this.branchtype) {
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
    
    short op_illegal() {
        this.zm.fatal("Unknown opcode");
        return 0;
    }
    
    short op_je() {
        for (int i = 1; i < this.count; ++i) {
            if (this.operands[0] == this.operands[i]) {
                return 1;
            }
        }
        return 0;
    }
    
    short op_jl() {
        if (this.operands[0] < this.operands[1]) {
            return 1;
        }
        return 0;
    }
    
    short op_jg() {
        if (this.operands[0] > this.operands[1]) {
            return 1;
        }
        return 0;
    }
    
    short op_dec_chk() {
        short vval = this.zm.get_variable(this.operands[0]);
        --vval;
        this.zm.set_variable(this.operands[0], vval);
        if (vval < this.operands[1]) {
            return 1;
        }
        return 0;
    }
    
    short op_inc_chk() {
        short vval = this.zm.get_variable(this.operands[0]);
        ++vval;
        this.zm.set_variable(this.operands[0], vval);
        if (vval > this.operands[1]) {
            return 1;
        }
        return 0;
    }
    
    short op_jin() {
        if (this.zm.objects.parent(this.operands[0]) == this.operands[1]) {
            return 1;
        }
        return 0;
    }
    
    short op_test() {
        if ((this.operands[0] & this.operands[1]) == this.operands[1]) {
            return 1;
        }
        return 0;
    }
    
    short op_or() {
        return (short)((this.operands[0] | this.operands[1]) & 0xFFFF);
    }
    
    short op_and() {
        return (short)(this.operands[0] & this.operands[1] & 0xFFFF);
    }
    
    short op_test_attr() {
        if (this.zm.objects.attribute(this.operands[0], this.operands[1])) {
            return 1;
        }
        return 0;
    }
    
    short op_set_attr() {
        this.zm.objects.set_attribute(this.operands[0], this.operands[1]);
        return 0;
    }
    
    short op_clear_attr() {
        this.zm.objects.clear_attribute(this.operands[0], this.operands[1]);
        return 0;
    }
    
    short op_store() {
        this.zm.set_variable(this.operands[0], this.operands[1]);
        return 0;
    }
    
    short op_insert_obj() {
        this.detach_obj(this.operands[0]);
        final short dchild = this.zm.objects.child(this.operands[1]);
        this.zm.objects.set_child(this.operands[1], this.operands[0]);
        this.zm.objects.set_sibling(this.operands[0], dchild);
        this.zm.objects.set_parent(this.operands[0], this.operands[1]);
        return 0;
    }
    
    short op_loadw() {
        final int byte_index = (this.operands[0] & 0xFFFF) + (this.operands[1] & 0xFFFF) * 2;
        final short result = (short)((this.zm.memory_image[byte_index] << 8 & 0xFF00) | (this.zm.memory_image[byte_index + 1] & 0xFF));
        return result;
    }
    
    short op_loadb() {
        final int byte_index = (this.operands[0] & 0xFFFF) + (this.operands[1] & 0xFFFF);
        return (short)(this.zm.memory_image[byte_index] & 0xFF);
    }
    
    short op_get_prop() {
        return this.zm.objects.prop(this.operands[0], this.operands[1]);
    }
    
    short op_get_prop_addr() {
        return this.zm.objects.prop_address(this.operands[0], this.operands[1]);
    }
    
    short op_get_next_prop() {
        return this.zm.objects.next_prop(this.operands[0], this.operands[1]);
    }
    
    short op_add() {
        return (short)(this.operands[0] + this.operands[1]);
    }
    
    short op_sub() {
        return (short)(this.operands[0] - this.operands[1]);
    }
    
    short op_mul() {
        return (short)(this.operands[0] * this.operands[1]);
    }
    
    short op_div() {
        final int dividend = this.operands[0];
        final int divisor = this.operands[1];
        if (divisor == 0) {
            this.zm.fatal("Remainder from division by zero");
        }
        return (short)(dividend / divisor);
    }
    
    short op_mod() {
        final int dividend = this.operands[0];
        final int divisor = this.operands[1];
        if (divisor == 0) {
            this.zm.fatal("Remainder from division by zero");
        }
        return (short)(dividend % divisor);
    }
    
    short op_jz() {
        if (this.operands[0] == 0) {
            return 1;
        }
        return 0;
    }
    
    short op_get_sibling() {
        return this.zm.objects.sibling(this.operands[0]);
    }
    
    short op_get_child() {
        return this.zm.objects.child(this.operands[0]);
    }
    
    short op_get_parent() {
        return this.zm.objects.parent(this.operands[0]);
    }
    
    short op_get_prop_len() {
        return this.zm.objects.prop_len(this.operands[0]);
    }
    
    short op_inc() {
        short vval = this.zm.get_variable(this.operands[0]);
        ++vval;
        this.zm.set_variable(this.operands[0], vval);
        return 0;
    }
    
    short op_dec() {
        short vval = this.zm.get_variable(this.operands[0]);
        --vval;
        this.zm.set_variable(this.operands[0], vval);
        return 0;
    }
    
    short op_print_addr() {
        this.zm.print_string(this.operands[0] & 0xFFFF);
        return 0;
    }
    
    short op_call_1s() {
        return 0;
    }
    
    void detach_obj(final short object) {
        final short parent = this.zm.objects.parent(object);
        if (parent != 0) {
            short cursor = this.zm.objects.child(parent);
            if (cursor == object) {
                this.zm.objects.set_child(parent, this.zm.objects.sibling(object));
            }
            else {
                while (this.zm.objects.sibling(cursor) != object) {
                    cursor = this.zm.objects.sibling(cursor);
                    if (cursor == 0) {
                        this.zm.fatal("Malformed object tree");
                    }
                }
                this.zm.objects.set_sibling(cursor, this.zm.objects.sibling(object));
            }
        }
    }
    
    short op_remove_obj() {
        this.detach_obj(this.operands[0]);
        this.zm.objects.set_parent(this.operands[0], (short)0);
        this.zm.objects.set_sibling(this.operands[0], (short)0);
        return 0;
    }
    
    short op_print_obj() {
        this.zm.print_string(this.zm.objects.short_name_addr(this.operands[0]));
        return 1;
    }
    
    protected void z_ret() {
        Object tos;
        do {
            tos = this.zm.zstack.pop();
        } while (!(tos instanceof short[]));
        this.zm.locals = (short[])tos;
        this.zm.pc = this.zm.zstack.pop();
        this.storevar = (short)(int)this.zm.zstack.pop();
    }
    
    short op_ret() {
        this.z_ret();
        return this.operands[0];
    }
    
    short op_jump() {
        final ZMachine zm = this.zm;
        zm.pc += this.operands[0] - 2;
        return 0;
    }
    
    short op_print_paddr() {
        this.zm.print_string(this.zm.string_address(this.operands[0]));
        return 0;
    }
    
    short op_load() {
        return this.zm.get_variable(this.operands[0]);
    }
    
    short op_not() {
        return (short)~this.operands[0];
    }
    
    short op_rtrue() {
        this.z_ret();
        return 1;
    }
    
    short op_rfalse() {
        this.z_ret();
        return 0;
    }
    
    short op_print() {
        final int nchars = this.zm.print_string(this.zm.pc);
        final ZMachine zm = this.zm;
        zm.pc += nchars;
        return 0;
    }
    
    short op_print_ret() {
        this.zm.print_string(this.zm.pc);
        this.zm.print_ascii_char((short)13);
        this.z_ret();
        return 1;
    }
    
    short op_nop() {
        return 0;
    }
    
    short op_save() {
        if (new ZState(this.zm).disk_save(this.zm.screen.getFrame(), this.save_pc)) {
            return 1;
        }
        return 0;
    }
    
    short op_restore() {
        final ZState restore_state = new ZState(this.zm);
        if (restore_state.restore_from_disk(this.zm.screen.getFrame())) {
            this.zm.restore(restore_state);
            this.decode_second_half();
            return 2;
        }
        return 0;
    }
    
    short op_restart() {
        this.zm.restart();
        return 0;
    }
    
    short op_ret_popped() {
        final short returnvalue = this.zm.get_variable((short)0);
        this.z_ret();
        return returnvalue;
    }
    
    short op_pop() {
        this.zm.get_variable((short)0);
        return 0;
    }
    
    short op_quit() {
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
    
    short op_new_line() {
        this.zm.print_ascii_char((short)13);
        return 0;
    }
    
    short op_show_status() {
        this.zm.update_status_line();
        return 0;
    }
    
    short op_verify() {
        final int filesize = this.zm.header.file_length();
        if (filesize > this.zm.memory_image.length || this.zm.header.checksum() != this.zm.checksum) {
            System.err.println("VERIFY failed: ");
            System.err.println("\texpected\tactual");
            System.err.println("length\t" + filesize + "\t" + this.zm.memory_image.length);
            System.err.println("checksum\t" + Integer.toString(this.zm.header.checksum() & 0xFFFF, 16) + "\t" + Integer.toString(this.zm.checksum, 16));
            return 0;
        }
        return 1;
    }
    
    short op_extended() {
        return 0;
    }
    
    short op_piracy() {
        return 0;
    }
    
    short op_call() {
        if (this.operands[0] == 0) {
            this.zm.set_variable(this.storevar, (short)0);
        }
        else {
            this.zm.zstack.push(new Integer(this.storevar));
            this.zm.zstack.push(new Integer(this.zm.pc));
            this.zm.zstack.push(this.zm.locals);
            this.zm.pc = this.zm.routine_address(this.operands[0]);
            final int nlocals = this.zm.get_code_byte();
            this.zm.locals = new short[nlocals];
            for (int i = 0; i < nlocals; ++i) {
                final short thislocal = (short)((this.zm.get_code_byte() << 8 & 0xFF00) | (this.zm.get_code_byte() & 0xFF));
                if (i < this.count - 1) {
                    this.zm.locals[i] = this.operands[i + 1];
                }
                else {
                    this.zm.locals[i] = thislocal;
                }
            }
        }
        return 0;
    }
    
    short op_storew() {
        final int byte_index = (this.operands[0] & 0xFFFF) + (this.operands[1] & 0xFFFF) * 2;
        this.zm.memory_image[byte_index] = (byte)(this.operands[2] >>> 8);
        this.zm.memory_image[byte_index + 1] = (byte)(this.operands[2] & 0xFF);
        return 0;
    }
    
    short op_storeb() {
        final int byte_index = (this.operands[0] & 0xFFFF) + (this.operands[1] & 0xFFFF);
        this.zm.memory_image[byte_index] = (byte)(this.operands[2] & 0xFF);
        return 0;
    }
    
    short op_put_prop() {
        this.zm.objects.put_prop(this.operands[0], this.operands[1], this.operands[2]);
        return 0;
    }
    
    short op_sread() {
        final int tbuf = this.operands[0] & 0xFFFF;
        this.zm.update_status_line();
        this.zm.current_window.flush();
        this.zm.current_window.reset_line_count();
        int tsize = this.zm.memory_image[tbuf] & 0xFF;
        if (tsize < 2) {
            this.zm.fatal("Text Buffer < 3 bytes");
        }
        --tsize;
        int bufloc = 1;
        byte ch;
        while (tsize != 0 && (ch = this.zm.get_input_byte(true)) != 13 && ch != 10) {
            if (ch >= 65 && ch <= 90) {
                ch = (byte)(ch - 65 + 97);
            }
            this.zm.memory_image[tbuf + bufloc] = ch;
            ++bufloc;
            --tsize;
        }
        this.zm.memory_image[tbuf + bufloc] = 0;
        this.zm.zd.tokenise(tbuf + 1, bufloc - 1, this.operands[1] & 0xFFFF);
        return 0;
    }
    
    short op_print_char() {
        this.zm.print_ascii_char(this.operands[0]);
        return 0;
    }
    
    short op_print_num() {
        final char[] mychars = Integer.toString(this.operands[0], 10).toCharArray();
        for (int i = 0; i < mychars.length; ++i) {
            this.zm.print_ascii_char((short)mychars[i]);
        }
        return 0;
    }
    
    short op_random() {
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
    
    short op_push() {
        this.zm.set_variable((short)0, this.operands[0]);
        return 0;
    }
    
    short op_pull() {
        this.zm.set_variable(this.operands[0], this.zm.get_variable((short)0));
        return 0;
    }
    
    void split_screen(final int lines) {
        this.zm.window[1].flush();
        this.zm.window[0].flush();
        final int cx = this.zm.window[0].getx();
        int cy = this.zm.window[0].gety() + this.zm.window[1].getlines();
        cy -= lines;
        if (cy < 0) {
            cy = 0;
        }
        this.zm.window[0].moveto(0, lines);
        this.zm.window[1].moveto(0, 0);
        this.zm.window[0].resize(this.zm.screen.getchars(), this.zm.screen.getlines() - lines);
        this.zm.window[1].resize(this.zm.screen.getchars(), lines);
        if (cy >= this.zm.screen.getlines() - lines) {
            cy = this.zm.screen.getlines() - lines - 1;
        }
        this.zm.window[0].movecursor(cx, cy);
    }
    
    short op_split_window() {
        this.split_screen(this.operands[0]);
        return 0;
    }
    
    short op_set_window() {
        this.zm.current_window.flush();
        this.zm.current_window = this.zm.window[this.operands[0]];
        if (this.operands[0] == 1) {
            this.zm.current_window.movecursor(0, 0);
        }
        return 0;
    }
    
    short op_output_stream() {
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
    
    short op_input_stream() {
        this.zm.inputstream = this.operands[0];
        return 0;
    }
    
    short op_sound_effect() {
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

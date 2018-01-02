// 
// Decompiled by Procyon v0.5.30
// 

class ZInstruction5 extends ZInstruction
{
    static final int OP_CALL_2S = 25;
    static final int OP_CALL_2N = 26;
    static final int OP_SET_COLOUR = 27;
    static final int OP_THROW = 28;
    static final int OP_CALL_1S = 136;
    static final int OP_CALL_1N = 143;
    static final int OP_OLD_SAVE = 181;
    static final int OP_OLD_RESTORE = 182;
    static final int OP_CATCH = 185;
    static final int OP_OLD_SHOW_STATUS = 188;
    static final int OP_VERIFY = 189;
    static final int OP_EXTENDED = 190;
    static final int OP_PIRACY = 191;
    static final int OP_CALL_VS = 224;
    static final int OP_AREAD = 228;
    static final int OP_CALL_VS2 = 236;
    static final int OP_ERASE_WINDOW = 237;
    static final int OP_ERASE_LINE = 238;
    static final int OP_SET_CURSOR = 239;
    static final int OP_GET_CURSOR = 240;
    static final int OP_SET_TEXT_STYLE = 241;
    static final int OP_BUFFER_MODE = 242;
    static final int OP_READ_CHAR = 246;
    static final int OP_SCAN_TABLE = 247;
    static final int OP_NOT = 248;
    static final int OP_CALL_VN = 249;
    static final int OP_CALL_VN2 = 250;
    static final int OP_TOKENISE = 251;
    static final int OP_ENCODE_TEXT = 252;
    static final int OP_COPY_TABLE = 253;
    static final int OP_PRINT_TABLE = 254;
    static final int OP_CHECK_ARG_COUNT = 255;
    static final int OP_SAVE = 256;
    static final int OP_RESTORE = 257;
    static final int OP_LOG_SHIFT = 258;
    static final int OP_ART_SHIFT = 259;
    static final int OP_SET_FONT = 260;
    static final int OP_SAVE_UNDO = 265;
    static final int OP_RESTORE_UNDO = 266;
    static final int SCREEN_UNSPLIT = -1;
    static final int SCREEN_NOUNSPLIT = -2;
    protected short call_opnum;
    protected boolean has_returned;
    static boolean[] store5;
    static boolean[] branch5;
    
    ZInstruction5(final ZMachine zm) {
        this.has_returned = false;
        super.zm = zm;
        if (ZInstruction5.store5 == null) {
            ZInstruction5.store5 = new boolean[285];
            ZInstruction5.branch5 = new boolean[285];
            this.setupbs();
        }
        super.operands = new short[8];
    }
    
    void decode_instruction() {
        this.has_returned = false;
        super.decode_instruction();
    }
    
    void execute() {
        short result = 0;
        switch (super.opnum) {
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
            case 25: {
                result = this.op_call_2s();
                break;
            }
            case 26: {
                result = this.op_call_2n();
                break;
            }
            case 27: {
                result = this.op_set_colour();
                break;
            }
            case 28: {
                result = this.op_throw();
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
                result = this.op_call_1n();
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
                result = this.op_illegal();
                break;
            }
            case 182: {
                result = this.op_illegal();
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
                result = this.op_catch();
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
                result = this.op_nop();
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
                result = this.op_call_vs();
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
                result = this.op_aread();
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
            case 236: {
                result = this.op_call_vs2();
                break;
            }
            case 237: {
                result = this.op_erase_window();
                break;
            }
            case 238: {
                result = this.op_erase_line();
                break;
            }
            case 239: {
                result = this.op_set_cursor();
                break;
            }
            case 240: {
                result = this.op_get_cursor();
                break;
            }
            case 241: {
                result = this.op_set_text_style();
                break;
            }
            case 242: {
                result = this.op_buffer_mode();
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
            case 246: {
                result = this.op_read_char();
                break;
            }
            case 247: {
                result = this.op_scan_table();
                break;
            }
            case 248: {
                result = this.op_not();
                break;
            }
            case 249: {
                result = this.op_call_vn();
                break;
            }
            case 250: {
                result = this.op_call_vn2();
                break;
            }
            case 251: {
                result = this.op_tokenise();
                break;
            }
            case 252: {
                result = this.op_encode_text();
                break;
            }
            case 253: {
                result = this.op_copy_table();
                break;
            }
            case 254: {
                result = this.op_print_table();
                break;
            }
            case 255: {
                result = this.op_check_arg_count();
                break;
            }
            case 256: {
                result = this.op_save();
                break;
            }
            case 257: {
                result = this.op_restore();
                break;
            }
            case 258: {
                result = this.op_log_shift();
                break;
            }
            case 259: {
                result = this.op_art_shift();
                break;
            }
            case 260: {
                result = this.op_set_font();
                break;
            }
            case 265: {
                result = this.op_save_undo();
                break;
            }
            case 266: {
                result = this.op_restore_undo();
                break;
            }
            default: {
                result = this.op_illegal();
                break;
            }
        }
        if (!this.iscall() && this.isstore()) {
            super.zm.set_variable(super.storevar, result);
        }
        if (this.isbranch() && result == 0 != super.branchtype) {
            switch (super.branchoffset) {
                case 0: {
                    this.z_ret();
                    if (this.isstore()) {
                        super.zm.set_variable(super.storevar, (short)0);
                        break;
                    }
                    break;
                }
                case 1: {
                    this.z_ret();
                    if (this.isstore()) {
                        super.zm.set_variable(super.storevar, (short)1);
                        break;
                    }
                    break;
                }
                default: {
                    final ZMachine zm = super.zm;
                    zm.pc += super.branchoffset - 2;
                    break;
                }
            }
        }
    }
    
    protected boolean isbranch() {
        return ZInstruction5.branch5[super.opnum];
    }
    
    protected boolean isstore() {
        if (this.has_returned) {
            return ZInstruction5.store5[this.call_opnum];
        }
        return ZInstruction5.store5[super.opnum];
    }
    
    protected boolean iscall() {
        switch (super.opnum) {
            case 25:
            case 26:
            case 136:
            case 143:
            case 224:
            case 236:
            case 249:
            case 250: {
                return true;
            }
            default: {
                return false;
            }
        }
    }
    
    protected short z_call() {
        if (super.operands[0] == 0) {
            if (this.isstore()) {
                super.zm.set_variable(super.storevar, (short)0);
            }
        }
        else {
            super.zm.zstack.push(new ZFrameBound(this.isstore()));
            if (this.isstore()) {
                super.zm.zstack.push(new Integer(super.storevar));
            }
            super.zm.zstack.push(new Integer(super.opnum));
            super.zm.zstack.push(new Integer(super.zm.pc));
            super.zm.zstack.push(new Integer(((ZMachine5)super.zm).argcount));
            super.zm.zstack.push(super.zm.locals);
            super.zm.pc = super.zm.routine_address(super.operands[0]);
            final int nlocals = super.zm.get_code_byte();
            ((ZMachine5)super.zm).argcount = (short)(super.count - 1);
            super.zm.locals = new short[nlocals];
            for (int i = 0; i < nlocals; ++i) {
                if (i < super.count - 1) {
                    super.zm.locals[i] = super.operands[i + 1];
                }
                else {
                    super.zm.locals[i] = 0;
                }
            }
        }
        return 0;
    }
    
    protected void z_ret() {
        Object tos;
        do {
            tos = super.zm.zstack.pop();
        } while (!(tos instanceof short[]));
        super.zm.locals = (short[])tos;
        ((ZMachine5)super.zm).argcount = (short)(int)super.zm.zstack.pop();
        super.zm.pc = super.zm.zstack.pop();
        this.call_opnum = (short)(int)super.zm.zstack.pop();
        this.has_returned = true;
        if (this.isstore()) {
            super.storevar = (short)(int)super.zm.zstack.pop();
        }
        super.zm.zstack.pop();
    }
    
    short op_call_2s() {
        this.z_call();
        return 0;
    }
    
    short op_call_2n() {
        this.z_call();
        return 0;
    }
    
    short op_set_colour() {
        int foreground = super.operands[0];
        int background = super.operands[1];
        if (foreground == 1) {
            foreground = ((ZHeader5)super.zm.header).default_foreground_color();
        }
        if (background == 1) {
            background = ((ZHeader5)super.zm.header).default_background_color();
        }
        super.zm.current_window.set_color(foreground, background);
        return 0;
    }
    
    short op_throw() {
        return 0;
    }
    
    short op_call_1s() {
        this.z_call();
        return 0;
    }
    
    short op_call_1n() {
        this.z_call();
        return 0;
    }
    
    short op_catch() {
        return 0;
    }
    
    short op_call_vs() {
        this.z_call();
        return 0;
    }
    
    short op_aread() {
        final int tbuf = super.operands[0] & 0xFFFF;
        super.zm.current_window.flush();
        super.zm.current_window.reset_line_count();
        int tsize = super.zm.memory_image[tbuf] & 0xFF;
        if (tsize < 3) {
            super.zm.fatal("Text Buffer < 3 bytes");
        }
        int bufloc = 2;
        byte ch;
        for (ch = super.zm.get_input_byte(true); tsize != 0 && ch != 13 && ch != 10; --tsize, ch = super.zm.get_input_byte(true)) {
            if (ch >= 65 && ch <= 90) {
                ch = (byte)(ch - 65 + 97);
            }
            super.zm.memory_image[tbuf + bufloc] = ch;
            ++bufloc;
        }
        super.zm.memory_image[tbuf + 1] = (byte)(bufloc - 2);
        if (super.operands[1] != 0) {
            super.zm.zd.tokenise(tbuf + 2, bufloc - 2, super.operands[1] & 0xFFFF);
        }
        return (short)(ch & 0xFF);
    }
    
    short op_call_vs2() {
        this.z_call();
        return 0;
    }
    
    short op_erase_window() {
        if (super.operands[0] == -1) {
            this.split_screen(0);
            super.zm.screen.clear();
            super.zm.window[0].movecursor(0, 0);
            super.zm.window[0].reset_line_count();
        }
        else if (super.operands[0] == -2) {
            super.zm.screen.clear();
            super.zm.window[0].movecursor(0, 0);
            super.zm.window[0].reset_line_count();
            super.zm.window[1].movecursor(0, 0);
            super.zm.window[1].reset_line_count();
        }
        else {
            super.zm.window[super.operands[0]].clear();
            super.zm.window[super.operands[0]].movecursor(0, 0);
            super.zm.window[super.operands[0]].reset_line_count();
        }
        return 0;
    }
    
    short op_erase_line() {
        super.zm.current_window.erase_line(super.operands[0]);
        return 0;
    }
    
    short op_set_cursor() {
        final int x = super.operands[1] & 0xFFFF;
        final int y = super.operands[0] & 0xFFFF;
        if (super.zm.current_window == super.zm.window[1]) {
            super.zm.current_window.movecursor(x - 1, y - 1);
        }
        return 0;
    }
    
    short op_get_cursor() {
        final int table = super.operands[0] & 0xFFFF;
        super.zm.current_window.flush();
        final int x = super.zm.current_window.getx() + 1;
        final int y = super.zm.current_window.gety() + 1;
        super.zm.memory_image[table] = (byte)(y >> 8 & 0xFF);
        super.zm.memory_image[table + 1] = (byte)(y & 0xFF);
        super.zm.memory_image[table + 2] = (byte)(x >> 8 & 0xFF);
        super.zm.memory_image[table + 3] = (byte)(x & 0xFF);
        return 0;
    }
    
    short op_set_text_style() {
        super.zm.current_window.set_text_style(super.operands[0] & 0xFFFF);
        return 0;
    }
    
    short op_buffer_mode() {
        super.zm.window[0].setwrapmode(super.operands[0] != 0);
        return 0;
    }
    
    short op_read_char() {
        super.zm.current_window.flush();
        super.zm.current_window.reset_line_count();
        final short ch = (short)(super.zm.get_input_byte(false) & 0xFF);
        return ch;
    }
    
    short op_scan_table() {
        int advance = 2;
        int location = super.operands[1] & 0xFFFF;
        final int len = super.operands[2] & 0xFFFF;
        boolean words = true;
        if (super.count == 4) {
            advance = (super.operands[3] & 0x7F);
            words = ((super.operands[3] & 0x80) == 0x80);
        }
        if (words) {
            for (int lastloc = location + (len << 1); location < lastloc; location += advance) {
                if ((super.zm.memory_image[location] & 0xFF) == (super.operands[0] >> 8 & 0xFF) && (super.zm.memory_image[location + 1] & 0xFF) == (super.operands[0] & 0xFF)) {
                    return (short)location;
                }
            }
        }
        else {
            for (int lastloc = location + len; location < lastloc; location += advance) {
                if ((super.zm.memory_image[location] & 0xFF) == (super.operands[0] & 0xFFFF)) {
                    return (short)location;
                }
            }
        }
        return 0;
    }
    
    short op_call_vn() {
        this.z_call();
        return 0;
    }
    
    short op_call_vn2() {
        this.z_call();
        return 0;
    }
    
    short op_tokenise() {
        int userdict;
        if (super.count < 3) {
            userdict = 0;
        }
        else {
            userdict = super.operands[2];
        }
        final boolean parseunknown = super.count < 3 || super.operands[3] == 0;
        if (userdict != 0) {
            System.err.println("tokenise opcode encountered (userdict)");
        }
        final int tbuf = super.operands[0] & 0xFFFF;
        final int tlen = super.zm.memory_image[tbuf + 1];
        ((ZDictionary5)super.zm.zd).tokenise(tbuf + 2, tlen, super.operands[1] & 0xFFFF, parseunknown);
        return 0;
    }
    
    short op_encode_text() {
        final int ascii_text = super.operands[0] & 65535 + super.operands[2];
        final int coded_text = super.operands[3] & 0xFFFF;
        final short[] encword = super.zm.encode_word(ascii_text, super.operands[1], 6);
        for (int i = 0; i < 3; ++i) {
            super.zm.memory_image[coded_text + i + i] = (byte)(encword[i] >> 8 & 0xFF);
            super.zm.memory_image[coded_text + i + i + 1] = (byte)(encword[i] & 0xFF);
        }
        return 0;
    }
    
    short op_copy_table() {
        final int first = super.operands[0] & 0xFFFF;
        final int second = super.operands[1] & 0xFFFF;
        int length = super.operands[2];
        if (second == 0) {
            if (length < 0) {
                length = -length;
            }
            for (int i = first + length - 1; i >= first; --i) {
                super.zm.memory_image[i] = 0;
            }
        }
        else if (length > 0) {
            System.arraycopy(super.zm.memory_image, first, super.zm.memory_image, second, length);
        }
        else {
            length = -length;
            for (int i = 0; i < length; ++i) {
                super.zm.memory_image[second + i] = super.zm.memory_image[first + i];
            }
        }
        return 0;
    }
    
    short op_print_table() {
        int textpos = super.operands[0] & 0xFFFF;
        final int width = super.operands[1] & 0xFFFF;
        int height = 1;
        int skip = 0;
        if (super.count > 2) {
            height = (super.operands[2] & 0xFFFF);
        }
        if (super.count > 3) {
            skip = (super.operands[3] & 0xFFFF);
        }
        super.zm.current_window.flush();
        final int x = super.zm.current_window.getx();
        final int y = super.zm.current_window.gety();
        for (int j = 0; j < height; ++j) {
            super.zm.current_window.movecursor(x, y + j);
            for (int i = 0; i < width; ++i) {
                super.zm.print_ascii_char(super.zm.memory_image[textpos++]);
            }
            textpos += skip;
        }
        return 0;
    }
    
    short op_check_arg_count() {
        if ((super.operands[0] & 0xFFFF) - 1 < ((ZMachine5)super.zm).argcount) {
            return 1;
        }
        return 0;
    }
    
    short op_restore() {
        final ZState restore_state = new ZState(super.zm);
        if (restore_state.restore_from_disk(super.zm.screen.getFrame())) {
            super.zm.restore(restore_state);
            super.storevar = super.zm.get_code_byte();
            return 2;
        }
        return 0;
    }
    
    short op_log_shift() {
        if (super.operands[1] >= 0) {
            return (short)((super.operands[0] & 0xFFFF) << super.operands[1]);
        }
        return (short)((super.operands[0] & 0xFFFF) >> -super.operands[1]);
    }
    
    short op_art_shift() {
        if (super.operands[1] >= 0) {
            return (short)(super.operands[0] << super.operands[1]);
        }
        return (short)(super.operands[0] << -super.operands[1]);
    }
    
    short op_set_font() {
        return 0;
    }
    
    short op_save_undo() {
        super.zm.zstack.push(new Integer(super.storevar));
        final short result = (short)((ZMachine5)super.zm).save_undo();
        if (result == 0) {
            super.zm.zstack.pop();
        }
        return result;
    }
    
    short op_restore_undo() {
        final short result = (short)((ZMachine5)super.zm).restore_undo();
        if (result != 0) {
            super.storevar = (short)(int)super.zm.zstack.pop();
        }
        return result;
    }
    
    protected void setupbs() {
        ZInstruction5.branch5[1] = true;
        ZInstruction5.branch5[2] = true;
        ZInstruction5.branch5[3] = true;
        ZInstruction5.branch5[4] = true;
        ZInstruction5.branch5[5] = true;
        ZInstruction5.branch5[6] = true;
        ZInstruction5.branch5[7] = true;
        ZInstruction5.store5[8] = true;
        ZInstruction5.store5[9] = true;
        ZInstruction5.branch5[10] = true;
        ZInstruction5.store5[15] = true;
        ZInstruction5.store5[16] = true;
        ZInstruction5.store5[17] = true;
        ZInstruction5.store5[18] = true;
        ZInstruction5.store5[19] = true;
        ZInstruction5.store5[20] = true;
        ZInstruction5.store5[21] = true;
        ZInstruction5.store5[22] = true;
        ZInstruction5.store5[23] = true;
        ZInstruction5.store5[24] = true;
        ZInstruction5.store5[25] = true;
        ZInstruction5.branch5[128] = true;
        ZInstruction5.branch5[129] = true;
        ZInstruction5.store5[129] = true;
        ZInstruction5.branch5[130] = true;
        ZInstruction5.store5[130] = true;
        ZInstruction5.store5[131] = true;
        ZInstruction5.store5[132] = true;
        ZInstruction5.store5[136] = true;
        ZInstruction5.store5[142] = true;
        ZInstruction5.store5[185] = true;
        ZInstruction5.branch5[189] = true;
        ZInstruction5.branch5[191] = true;
        ZInstruction5.store5[224] = true;
        ZInstruction5.store5[228] = true;
        ZInstruction5.store5[231] = true;
        ZInstruction5.store5[236] = true;
        ZInstruction5.store5[246] = true;
        ZInstruction5.store5[247] = true;
        ZInstruction5.branch5[247] = true;
        ZInstruction5.store5[248] = true;
        ZInstruction5.branch5[255] = true;
        ZInstruction5.store5[256] = true;
        ZInstruction5.store5[257] = true;
        ZInstruction5.store5[258] = true;
        ZInstruction5.store5[259] = true;
        ZInstruction5.store5[260] = true;
        ZInstruction5.store5[265] = true;
        ZInstruction5.store5[266] = true;
    }
    
    static {
        ZInstruction5.store5 = null;
        ZInstruction5.branch5 = null;
    }
}

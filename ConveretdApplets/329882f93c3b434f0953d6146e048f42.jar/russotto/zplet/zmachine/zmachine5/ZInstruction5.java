// 
// Decompiled by Procyon v0.5.30
// 

package russotto.zplet.zmachine.zmachine5;

import russotto.zplet.zmachine.state.ZState;
import russotto.zplet.zmachine.ZFrameBound;
import russotto.zplet.zmachine.ZMachine;
import russotto.zplet.zmachine.ZInstruction;

public class ZInstruction5 extends ZInstruction
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
    public static final int OP_CALL_VN = 249;
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
    
    public void decode_instruction() {
        this.has_returned = false;
        super.decode_instruction();
    }
    
    public void execute() {
        short n = 0;
        switch (super.opnum) {
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
            case 25: {
                n = this.op_call_2s();
                break;
            }
            case 26: {
                n = this.op_call_2n();
                break;
            }
            case 27: {
                n = this.op_set_colour();
                break;
            }
            case 28: {
                n = this.op_throw();
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
                n = this.op_call_1n();
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
                n = this.op_illegal();
                break;
            }
            case 182: {
                n = this.op_illegal();
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
                n = this.op_catch();
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
                n = this.op_nop();
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
                n = this.op_call_vs();
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
                n = this.op_aread();
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
            case 236: {
                n = this.op_call_vs2();
                break;
            }
            case 237: {
                n = this.op_erase_window();
                break;
            }
            case 238: {
                n = this.op_erase_line();
                break;
            }
            case 239: {
                n = this.op_set_cursor();
                break;
            }
            case 240: {
                n = this.op_get_cursor();
                break;
            }
            case 241: {
                n = this.op_set_text_style();
                break;
            }
            case 242: {
                n = this.op_buffer_mode();
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
            case 246: {
                n = this.op_read_char();
                break;
            }
            case 247: {
                n = this.op_scan_table();
                break;
            }
            case 248: {
                n = this.op_not();
                break;
            }
            case 249: {
                n = this.op_call_vn();
                break;
            }
            case 250: {
                n = this.op_call_vn2();
                break;
            }
            case 251: {
                n = this.op_tokenise();
                break;
            }
            case 252: {
                n = this.op_encode_text();
                break;
            }
            case 253: {
                n = this.op_copy_table();
                break;
            }
            case 254: {
                n = this.op_print_table();
                break;
            }
            case 255: {
                n = this.op_check_arg_count();
                break;
            }
            case 256: {
                n = this.op_save();
                break;
            }
            case 257: {
                n = this.op_restore();
                break;
            }
            case 258: {
                n = this.op_log_shift();
                break;
            }
            case 259: {
                n = this.op_art_shift();
                break;
            }
            case 260: {
                n = this.op_set_font();
                break;
            }
            case 265: {
                n = this.op_save_undo();
                break;
            }
            case 266: {
                n = this.op_restore_undo();
                break;
            }
            default: {
                n = this.op_illegal();
                break;
            }
        }
        if (!this.iscall() && this.isstore()) {
            super.zm.set_variable(super.storevar, n);
        }
        if (this.isbranch() && n == 0 != super.branchtype) {
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
            final byte get_code_byte = super.zm.get_code_byte();
            ((ZMachine5)super.zm).argcount = (short)(super.count - 1);
            super.zm.locals = new short[get_code_byte];
            for (byte b = 0; b < get_code_byte; ++b) {
                if (b < super.count - 1) {
                    super.zm.locals[b] = super.operands[b + 1];
                }
                else {
                    super.zm.locals[b] = 0;
                }
            }
        }
        return 0;
    }
    
    protected void z_ret() {
        short[] pop;
        do {
            pop = super.zm.zstack.pop();
        } while (!(pop instanceof short[]));
        super.zm.locals = pop;
        ((ZMachine5)super.zm).argcount = (short)(int)super.zm.zstack.pop();
        super.zm.pc = super.zm.zstack.pop();
        this.call_opnum = (short)(int)super.zm.zstack.pop();
        this.has_returned = true;
        if (this.isstore()) {
            super.storevar = (short)(int)super.zm.zstack.pop();
        }
        super.zm.zstack.pop();
    }
    
    protected short op_call_2s() {
        this.z_call();
        return 0;
    }
    
    protected short op_call_2n() {
        this.z_call();
        return 0;
    }
    
    protected short op_set_colour() {
        int default_foreground_color = super.operands[0];
        int default_background_color = super.operands[1];
        if (default_foreground_color == 1) {
            default_foreground_color = ((ZHeader5)super.zm.header).default_foreground_color();
        }
        if (default_background_color == 1) {
            default_background_color = ((ZHeader5)super.zm.header).default_background_color();
        }
        super.zm.current_window.set_color(default_foreground_color, default_background_color);
        return 0;
    }
    
    protected short op_throw() {
        return 0;
    }
    
    protected short op_call_1s() {
        this.z_call();
        return 0;
    }
    
    protected short op_call_1n() {
        this.z_call();
        return 0;
    }
    
    protected short op_catch() {
        return 0;
    }
    
    protected short op_call_vs() {
        this.z_call();
        return 0;
    }
    
    protected short op_aread() {
        final int n = super.operands[0] & 0xFFFF;
        super.zm.current_window.flush();
        super.zm.current_window.reset_line_count();
        int n2 = super.zm.memory_image[n] & 0xFF;
        if (n2 < 3) {
            super.zm.fatal("Text Buffer < 3 bytes");
        }
        int n3 = 2;
        byte b;
        for (b = super.zm.get_input_byte(true); n2 != 0 && b != 13 && b != 10; --n2, b = super.zm.get_input_byte(true)) {
            if (b >= 65 && b <= 90) {
                b = (byte)(b - 65 + 97);
            }
            super.zm.memory_image[n + n3] = b;
            ++n3;
        }
        super.zm.memory_image[n + 1] = (byte)(n3 - 2);
        if (super.operands[1] != 0) {
            super.zm.zd.tokenise(n + 2, n3 - 2, super.operands[1] & 0xFFFF);
        }
        return (short)(b & 0xFF);
    }
    
    protected short op_call_vs2() {
        this.z_call();
        return 0;
    }
    
    protected short op_erase_window() {
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
    
    protected short op_erase_line() {
        super.zm.current_window.erase_line(super.operands[0]);
        return 0;
    }
    
    protected short op_set_cursor() {
        final int n = super.operands[1] & 0xFFFF;
        final int n2 = super.operands[0] & 0xFFFF;
        if (super.zm.current_window == super.zm.window[1]) {
            super.zm.current_window.movecursor(n - 1, n2 - 1);
        }
        return 0;
    }
    
    protected short op_get_cursor() {
        final int n = super.operands[0] & 0xFFFF;
        super.zm.current_window.flush();
        final int n2 = super.zm.current_window.getx() + 1;
        final int n3 = super.zm.current_window.gety() + 1;
        super.zm.memory_image[n] = (byte)(n3 >> 8 & 0xFF);
        super.zm.memory_image[n + 1] = (byte)(n3 & 0xFF);
        super.zm.memory_image[n + 2] = (byte)(n2 >> 8 & 0xFF);
        super.zm.memory_image[n + 3] = (byte)(n2 & 0xFF);
        return 0;
    }
    
    protected short op_set_text_style() {
        super.zm.current_window.set_text_style(super.operands[0] & 0xFFFF);
        return 0;
    }
    
    protected short op_buffer_mode() {
        super.zm.window[0].setwrapmode(super.operands[0] != 0);
        return 0;
    }
    
    protected short op_read_char() {
        super.zm.current_window.flush();
        super.zm.current_window.reset_line_count();
        return (short)(super.zm.get_input_byte(false) & 0xFF);
    }
    
    protected short op_scan_table() {
        int n = 2;
        int i = super.operands[1] & 0xFFFF;
        final int n2 = super.operands[2] & 0xFFFF;
        int n3 = 1;
        if (super.count == 4) {
            n = (super.operands[3] & 0x7F);
            n3 = (((super.operands[3] & 0x80) == 0x80) ? 1 : 0);
        }
        if (n3 != 0) {
            while (i < i + (n2 << 1)) {
                if ((super.zm.memory_image[i] & 0xFF) == (super.operands[0] >> 8 & 0xFF) && (super.zm.memory_image[i + 1] & 0xFF) == (super.operands[0] & 0xFF)) {
                    return (short)i;
                }
                i += n;
            }
        }
        else {
            while (i < i + n2) {
                if ((super.zm.memory_image[i] & 0xFF) == (super.operands[0] & 0xFFFF)) {
                    return (short)i;
                }
                i += n;
            }
        }
        return 0;
    }
    
    protected short op_call_vn() {
        this.z_call();
        return 0;
    }
    
    protected short op_call_vn2() {
        this.z_call();
        return 0;
    }
    
    protected short op_tokenise() {
        int n;
        if (super.count < 3) {
            n = 0;
        }
        else {
            n = super.operands[2];
        }
        final boolean b = super.count < 3 || super.operands[3] == 0;
        if (n != 0) {
            System.err.println("tokenise opcode encountered (userdict)");
        }
        final int n2 = super.operands[0] & 0xFFFF;
        ((ZDictionary5)super.zm.zd).tokenise(n2 + 2, super.zm.memory_image[n2 + 1], super.operands[1] & 0xFFFF, b);
        return 0;
    }
    
    protected short op_encode_text() {
        final int n = super.operands[0] & 65535 + super.operands[2];
        final int n2 = super.operands[3] & 0xFFFF;
        final short[] encode_word = super.zm.encode_word(n, super.operands[1], 6);
        for (int i = 0; i < 3; ++i) {
            super.zm.memory_image[n2 + i + i] = (byte)(encode_word[i] >> 8 & 0xFF);
            super.zm.memory_image[n2 + i + i + 1] = (byte)(encode_word[i] & 0xFF);
        }
        return 0;
    }
    
    protected short op_copy_table() {
        final int n = super.operands[0] & 0xFFFF;
        final int n2 = super.operands[1] & 0xFFFF;
        int n3 = super.operands[2];
        if (n2 == 0) {
            if (n3 < 0) {
                n3 = -n3;
            }
            for (int i = n + n3 - 1; i >= n; --i) {
                super.zm.memory_image[i] = 0;
            }
        }
        else if (n3 > 0) {
            System.arraycopy(super.zm.memory_image, n, super.zm.memory_image, n2, n3);
        }
        else {
            for (int n4 = -n3, j = 0; j < n4; ++j) {
                super.zm.memory_image[n2 + j] = super.zm.memory_image[n + j];
            }
        }
        return 0;
    }
    
    protected short op_print_table() {
        int n = super.operands[0] & 0xFFFF;
        final int n2 = super.operands[1] & 0xFFFF;
        int n3 = 1;
        int n4 = 0;
        if (super.count > 2) {
            n3 = (super.operands[2] & 0xFFFF);
        }
        if (super.count > 3) {
            n4 = (super.operands[3] & 0xFFFF);
        }
        super.zm.current_window.flush();
        final int getx = super.zm.current_window.getx();
        final int gety = super.zm.current_window.gety();
        for (int i = 0; i < n3; ++i) {
            super.zm.current_window.movecursor(getx, gety + i);
            for (int j = 0; j < n2; ++j) {
                super.zm.print_ascii_char(super.zm.memory_image[n++]);
            }
            n += n4;
        }
        return 0;
    }
    
    protected short op_check_arg_count() {
        if ((super.operands[0] & 0xFFFF) - 1 < ((ZMachine5)super.zm).argcount) {
            return 1;
        }
        return 0;
    }
    
    protected short op_restore() {
        final ZState zState = new ZState(super.zm);
        if (zState.restore_from_disk(super.zm.screen.getFrame())) {
            super.zm.restore(zState);
            super.storevar = super.zm.get_code_byte();
            return 2;
        }
        return 0;
    }
    
    protected short op_log_shift() {
        if (super.operands[1] >= 0) {
            return (short)((super.operands[0] & 0xFFFF) << super.operands[1]);
        }
        return (short)((super.operands[0] & 0xFFFF) >> -super.operands[1]);
    }
    
    protected short op_art_shift() {
        if (super.operands[1] >= 0) {
            return (short)(super.operands[0] << super.operands[1]);
        }
        return (short)(super.operands[0] << -super.operands[1]);
    }
    
    protected short op_set_font() {
        return 0;
    }
    
    protected short op_save_undo() {
        super.zm.zstack.push(new Integer(super.storevar));
        final short n = (short)((ZMachine5)super.zm).save_undo();
        if (n == 0) {
            super.zm.zstack.pop();
        }
        return n;
    }
    
    protected short op_restore_undo() {
        final short n = (short)((ZMachine5)super.zm).restore_undo();
        if (n != 0) {
            super.storevar = (short)(int)super.zm.zstack.pop();
        }
        return n;
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

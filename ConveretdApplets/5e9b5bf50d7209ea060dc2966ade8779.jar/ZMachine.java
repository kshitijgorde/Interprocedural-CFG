import java.util.EmptyStackException;
import java.util.Random;
import java.util.Stack;

// 
// Decompiled by Procyon v0.5.30
// 

abstract class ZMachine extends Thread
{
    ZWindow current_window;
    int pc;
    ZWindow[] window;
    ZHeader header;
    ZScreen screen;
    ZObjectTree objects;
    ZDictionary zd;
    ZState restart_state;
    ZStatus status_line;
    byte[] memory_image;
    Stack zstack;
    Random zrandom;
    int globals;
    short[] locals;
    int inputstream;
    boolean[] outputs;
    int printmemory;
    int alphabet;
    short build_ascii;
    short built_ascii;
    short abbrev_mode;
    short checksum;
    ZInstruction zi;
    boolean status_redirect;
    String status_location;
    final String A2 = "0123456789.,!?_#'\"/\\-:()";
    static final int OP_LARGE = 0;
    static final int OP_SMALL = 1;
    static final int OP_VARIABLE = 2;
    static final int OP_OMITTED = 3;
    
    public ZMachine(final ZScreen screen, final ZStatus status_line, final byte[] memory_image) {
        this.screen = screen;
        this.status_line = status_line;
        this.memory_image = memory_image;
        this.locals = new short[0];
        this.zstack = new Stack();
        (this.restart_state = new ZState(this)).save_current();
        this.zrandom = new Random();
        this.inputstream = 0;
        (this.outputs = new boolean[5])[1] = true;
        this.alphabet = 0;
    }
    
    abstract void update_status_line();
    
    byte get_input_byte(final boolean buffered) {
        short code;
        if (this.inputstream == 0) {
            this.screen.set_input_window(this.current_window);
            if (buffered) {
                code = this.screen.read_buffered_code();
            }
            else {
                code = this.screen.read_code();
            }
        }
        else {
            this.fatal("Stream " + Integer.toString(this.inputstream, 10) + " not supported.");
            code = 13;
        }
        return (byte)code;
    }
    
    void print_ascii_char(final short ch) {
        if (this.status_redirect) {
            this.status_location = String.valueOf(this.status_location) + (char)ch;
        }
        else if (this.outputs[3]) {
            int nchars = (this.memory_image[this.printmemory] << 8 & 0xFF00) | (this.memory_image[this.printmemory + 1] & 0xFF);
            if (ch > 255) {
                this.memory_image[this.printmemory + nchars + 2] = 63;
            }
            else if (ch == 10) {
                this.memory_image[this.printmemory + nchars + 2] = 13;
            }
            else {
                this.memory_image[this.printmemory + nchars + 2] = (byte)ch;
            }
            ++nchars;
            this.memory_image[this.printmemory] = (byte)(nchars >>> 8);
            this.memory_image[this.printmemory + 1] = (byte)(nchars & 0xFF);
        }
        else {
            if (this.outputs[1]) {
                if (ch == 13 || ch == 10) {
                    this.current_window.newline();
                }
                else {
                    this.current_window.printzascii(ch);
                }
            }
            this.outputs[2] = this.header.transcripting();
            if (this.outputs[2] && this.current_window.transcripting()) {
                if (ch == 13 || ch == 10) {
                    System.out.println();
                }
                else {
                    System.out.print((char)ch);
                }
            }
        }
    }
    
    abstract int string_address(final short p0);
    
    abstract int routine_address(final short p0);
    
    short[] encode_word(final int wordloc, final int wordlen, final int nwords) {
        final short[] encword = new short[nwords];
        final int[] zchars = new int[nwords * 3];
        int zi = 0;
        while (true) {
            for (int i = 0; i < wordlen; ++i) {
                final int ch = this.memory_image[wordloc + i];
                if (ch >= 97 && ch <= 122) {
                    zchars[zi] = ch - 97 + 6;
                    if (++zi != nwords * 3) {
                        continue;
                    }
                }
                else if (ch >= 65 && ch <= 90) {
                    System.err.println("Tried to encode uppercase dictionary word");
                    zchars[zi] = ch - 65 + 6;
                    if (++zi != nwords * 3) {
                        continue;
                    }
                }
                else {
                    final int a2index;
                    if ((a2index = "0123456789.,!?_#'\"/\\-:()".indexOf(ch)) != -1) {
                        zchars[zi] = 5;
                        if (++zi != nwords * 3) {
                            zchars[zi] = a2index + 8;
                            if (++zi != nwords * 3) {
                                continue;
                            }
                        }
                    }
                    else {
                        zchars[zi] = 5;
                        if (++zi != nwords * 3) {
                            zchars[zi] = 6;
                            if (++zi != nwords * 3) {
                                zchars[zi] = ch >> 5;
                                if (++zi != nwords * 3) {
                                    zchars[zi] = (ch & 0x1F);
                                    if (++zi != nwords * 3) {
                                        continue;
                                    }
                                }
                            }
                        }
                    }
                }
                while (zi < nwords * 3) {
                    zchars[zi++] = 5;
                }
                zi = 0;
                for (i = 0; i < nwords; ++i) {
                    encword[i] = (short)(zchars[zi++] << 10);
                    final short[] array = encword;
                    final int n = i;
                    array[n] |= (short)(zchars[zi++] << 5);
                    final short[] array2 = encword;
                    final int n2 = i;
                    array2[n2] |= (short)zchars[zi++];
                }
                final short[] array3 = encword;
                final int n3 = nwords - 1;
                array3[n3] |= 0xFFFF8000;
                return encword;
            }
            continue;
        }
    }
    
    short alphabet_lookup(final byte zchar) {
        switch (this.alphabet) {
            case 0: {
                return (short)(97 + zchar - 6);
            }
            case 1: {
                return (short)(65 + zchar - 6);
            }
            case 2: {
                if (zchar == 7) {
                    return 13;
                }
                return (short)"0123456789.,!?_#'\"/\\-:()".charAt(zchar - 8);
            }
            default: {
                this.fatal("Bad Alphabet");
                return -1;
            }
        }
    }
    
    void print_abbrev(final int abbr_num) {
        this.abbrev_mode = -1;
        final int abbrev_index = this.header.abbrev_table() + 2 * abbr_num;
        final int string_addr = ((this.memory_image[abbrev_index] << 8 & 0xFF00) | (this.memory_image[abbrev_index + 1] & 0xFF)) * 2;
        this.print_string(string_addr);
    }
    
    void print_zchar(final byte zchar) {
        if (this.build_ascii > 0) {
            this.built_ascii = (short)(this.built_ascii << 5 | zchar);
            ++this.build_ascii;
            if (this.build_ascii == 3) {
                this.print_ascii_char(this.built_ascii);
                this.build_ascii = 0;
                this.built_ascii = 0;
            }
            this.alphabet = 0;
        }
        else if (this.abbrev_mode > 0) {
            this.print_abbrev(32 * (this.abbrev_mode - 1) + zchar);
            this.abbrev_mode = 0;
            this.build_ascii = 0;
            this.alphabet = 0;
        }
        else {
            switch (zchar) {
                case 0: {
                    this.print_ascii_char((short)32);
                    return;
                }
                case 1:
                case 2:
                case 3: {
                    if (this.abbrev_mode != 0) {
                        this.fatal("Abbreviation in abbreviation");
                    }
                    this.abbrev_mode = zchar;
                    this.alphabet = 0;
                    return;
                }
                case 4: {
                    this.alphabet = (this.alphabet + 1) % 3;
                    return;
                }
                case 5: {
                    this.alphabet = (this.alphabet + 2) % 3;
                    return;
                }
                case 6: {
                    if (this.alphabet == 2) {
                        this.build_ascii = 1;
                        this.alphabet = 0;
                        return;
                    }
                    break;
                }
            }
            this.print_ascii_char(this.alphabet_lookup(zchar));
            this.alphabet = 0;
        }
    }
    
    int print_string(int addr) {
        int nbytes = 0;
        this.build_ascii = 0;
        this.alphabet = 0;
        this.abbrev_mode = 0;
        final byte[] zchars = new byte[3];
        int zseq;
        do {
            zseq = ((this.memory_image[addr++] << 8 & 0xFF00) | (this.memory_image[addr++] & 0xFF));
            zchars[0] = (byte)(zseq >> 10 & 0x1F);
            zchars[1] = (byte)(zseq >> 5 & 0x1F);
            zchars[2] = (byte)(zseq & 0x1F);
            for (int i = 0; i < 3; ++i) {
                this.print_zchar(zchars[i]);
            }
            nbytes += 2;
        } while ((zseq & 0x8000) == 0x0);
        return nbytes;
    }
    
    public void start() {
        this.screen.clear();
        this.restart();
        this.header.set_transcripting(false);
        super.start();
    }
    
    public void run() {
        try {
            while (true) {
                this.zi.decode_instruction();
                this.zi.execute();
            }
        }
        catch (ArrayIndexOutOfBoundsException booga) {
            System.err.print("pc = ");
            System.err.println(Integer.toString(this.pc, 16));
            throw booga;
        }
        catch (ClassCastException booga2) {
            System.err.print("pc = ");
            System.err.println(Integer.toString(this.pc, 16));
            throw booga2;
        }
    }
    
    void calculate_checksum() {
        final int filesize = this.header.file_length();
        this.checksum = 0;
        if (filesize <= this.memory_image.length) {
            for (int i = 64; i < filesize; ++i) {
                this.checksum += (short)(this.memory_image[i] & 0xFF);
            }
        }
    }
    
    void restart() {
        this.restart_state.header.set_transcripting(this.header.transcripting());
        this.restart_state.restore_saved();
        this.set_header_flags();
        this.pc = this.header.initial_pc();
        this.calculate_checksum();
    }
    
    void restore(final ZState zs) {
        zs.header.set_transcripting(this.header.transcripting());
        this.restart();
        zs.restore_saved();
    }
    
    void set_header_flags() {
        this.header.set_revision(0, 2);
    }
    
    void fatal(final String s) {
        System.err.println(String.valueOf(s) + " @ $" + Integer.toString(this.pc, 16));
        System.exit(-1);
    }
    
    short get_variable(short varnum) {
        varnum &= 0xFF;
        short result;
        if (varnum == 0) {
            try {
                result = (short)(this.zstack.pop() & 0xFFFF);
            }
            catch (EmptyStackException ex) {
                this.fatal("Empty Stack");
                result = -1;
            }
        }
        else if (varnum >= 16) {
            result = (short)((this.memory_image[this.globals + (varnum - 16 << 1)] << 8 & 0xFF00) | (this.memory_image[this.globals + (varnum - 16 << 1) + 1] & 0xFF));
        }
        else {
            result = this.locals[varnum - 1];
        }
        return result;
    }
    
    void set_variable(short varnum, final short value) {
        varnum &= 0xFF;
        if (varnum == 0) {
            this.zstack.push(new Integer(value));
        }
        else if (varnum >= 16) {
            this.memory_image[this.globals + (varnum - 16 << 1)] = (byte)(value >>> 8);
            this.memory_image[this.globals + (varnum - 16 << 1) + 1] = (byte)(value & 0xFF);
        }
        else {
            this.locals[varnum - 1] = value;
        }
    }
    
    byte get_code_byte() {
        return this.memory_image[this.pc++];
    }
    
    short get_operand(final int optype) {
        switch (optype) {
            case 1: {
                return (short)(this.get_code_byte() & 0xFF);
            }
            case 0: {
                return (short)((this.get_code_byte() << 8 & 0xFF00) | (this.get_code_byte() & 0xFF));
            }
            case 2: {
                return this.get_variable(this.get_code_byte());
            }
            default: {
                return -1;
            }
        }
    }
}

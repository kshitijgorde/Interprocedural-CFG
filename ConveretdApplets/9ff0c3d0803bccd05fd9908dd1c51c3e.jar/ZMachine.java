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
    
    byte get_input_byte(final boolean b) {
        int n;
        if (this.inputstream == 0) {
            this.screen.set_input_window(this.current_window);
            if (b) {
                n = this.screen.read_buffered_code();
            }
            else {
                n = this.screen.read_code();
            }
        }
        else {
            this.fatal("Stream " + Integer.toString(this.inputstream, 10) + " not supported.");
            n = 13;
        }
        return (byte)n;
    }
    
    void print_ascii_char(final short ascii) {
        if (this.status_redirect) {
            this.status_location = String.valueOf(this.status_location) + (char)ascii;
            return;
        }
        if (this.outputs[3]) {
            int n = (this.memory_image[this.printmemory] << 8 & 0xFF00) | (this.memory_image[this.printmemory + 1] & 0xFF);
            if (ascii > 255) {
                this.memory_image[this.printmemory + n + 2] = 63;
            }
            else if (ascii == 10) {
                this.memory_image[this.printmemory + n + 2] = 13;
            }
            else {
                this.memory_image[this.printmemory + n + 2] = (byte)ascii;
            }
            ++n;
            this.memory_image[this.printmemory] = (byte)(n >>> 8);
            this.memory_image[this.printmemory + 1] = (byte)(n & 0xFF);
            return;
        }
        if (this.outputs[1]) {
            if (ascii == 13 || ascii == 10) {
                this.current_window.newline();
            }
            else {
                this.current_window.printzascii(ascii);
            }
        }
        this.outputs[2] = this.header.transcripting();
        if (this.outputs[2] && this.current_window.transcripting()) {
            if (ascii == 13 || ascii == 10) {
                System.out.println();
                return;
            }
            System.out.print((char)ascii);
        }
    }
    
    abstract int string_address(final short p0);
    
    abstract int routine_address(final short p0);
    
    short[] encode_word(final int n, final int n2, final int n3) {
        final short[] array = new short[n3];
        final int[] array2 = new int[n3 * 3];
        int i = 0;
        while (true) {
            for (int j = 0; j < n2; ++j) {
                final byte b = this.memory_image[n + j];
                if (b >= 97 && b <= 122) {
                    array2[i] = b - 97 + 6;
                    if (++i != n3 * 3) {
                        continue;
                    }
                }
                else if (b >= 65 && b <= 90) {
                    System.err.println("Tried to encode uppercase dictionary word");
                    array2[i] = b - 65 + 6;
                    if (++i != n3 * 3) {
                        continue;
                    }
                }
                else {
                    final int index;
                    if ((index = "0123456789.,!?_#'\"/\\-:()".indexOf(b)) != -1) {
                        array2[i] = 5;
                        if (++i != n3 * 3) {
                            array2[i] = index + 8;
                            if (++i != n3 * 3) {
                                continue;
                            }
                        }
                    }
                    else {
                        array2[i] = 5;
                        if (++i != n3 * 3) {
                            array2[i] = 6;
                            if (++i != n3 * 3) {
                                array2[i] = b >> 5;
                                if (++i != n3 * 3) {
                                    array2[i] = (b & 0x1F);
                                    if (++i != n3 * 3) {
                                        continue;
                                    }
                                }
                            }
                        }
                    }
                }
                while (i < n3 * 3) {
                    array2[i++] = 5;
                }
                int n4 = 0;
                for (int k = 0; k < n3; ++k) {
                    array[k] = (short)(array2[n4++] << 10);
                    final short[] array3 = array;
                    final int n5 = k;
                    array3[n5] |= (short)(array2[n4++] << 5);
                    final short[] array4 = array;
                    final int n6 = k;
                    array4[n6] |= (short)array2[n4++];
                }
                final short[] array5 = array;
                final int n7 = n3 - 1;
                array5[n7] |= 0xFFFF8000;
                return array;
            }
            continue;
        }
    }
    
    short alphabet_lookup(final byte b) {
        switch (this.alphabet) {
            case 0: {
                return (short)(97 + b - 6);
            }
            case 1: {
                return (short)(65 + b - 6);
            }
            case 2: {
                if (b == 7) {
                    return 13;
                }
                return (short)"0123456789.,!?_#'\"/\\-:()".charAt(b - 8);
            }
            default: {
                this.fatal("Bad Alphabet");
                return -1;
            }
        }
    }
    
    void print_abbrev(final int n) {
        this.abbrev_mode = -1;
        final int n2 = this.header.abbrev_table() + 2 * n;
        this.print_string(((this.memory_image[n2] << 8 & 0xFF00) | (this.memory_image[n2 + 1] & 0xFF)) * 2);
    }
    
    void print_zchar(final byte b) {
        if (this.build_ascii > 0) {
            this.built_ascii = (short)(this.built_ascii << 5 | b);
            ++this.build_ascii;
            if (this.build_ascii == 3) {
                this.print_ascii_char(this.built_ascii);
                this.build_ascii = 0;
                this.built_ascii = 0;
            }
            this.alphabet = 0;
            return;
        }
        if (this.abbrev_mode > 0) {
            this.print_abbrev(32 * (this.abbrev_mode - 1) + b);
            this.abbrev_mode = 0;
            this.build_ascii = 0;
            this.alphabet = 0;
            return;
        }
        switch (b) {
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
                this.abbrev_mode = b;
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
        this.print_ascii_char(this.alphabet_lookup(b));
        this.alphabet = 0;
    }
    
    int print_string(int n) {
        int n2 = 0;
        this.build_ascii = 0;
        this.alphabet = 0;
        this.abbrev_mode = 0;
        final byte[] array = new byte[3];
        int n3;
        do {
            n3 = ((this.memory_image[n++] << 8 & 0xFF00) | (this.memory_image[n++] & 0xFF));
            array[0] = (byte)(n3 >> 10 & 0x1F);
            array[1] = (byte)(n3 >> 5 & 0x1F);
            array[2] = (byte)(n3 & 0x1F);
            for (int i = 0; i < 3; ++i) {
                this.print_zchar(array[i]);
            }
            n2 += 2;
        } while ((n3 & 0x8000) == 0x0);
        return n2;
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
        catch (ArrayIndexOutOfBoundsException ex) {
            System.err.print("pc = ");
            System.err.println(Integer.toString(this.pc, 16));
            throw ex;
        }
        catch (ClassCastException ex2) {
            System.err.print("pc = ");
            System.err.println(Integer.toString(this.pc, 16));
            throw ex2;
        }
    }
    
    void calculate_checksum() {
        final int file_length = this.header.file_length();
        this.checksum = 0;
        if (file_length <= this.memory_image.length) {
            for (int i = 64; i < file_length; ++i) {
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
    
    void restore(final ZState zState) {
        zState.header.set_transcripting(this.header.transcripting());
        this.restart();
        zState.restore_saved();
    }
    
    void set_header_flags() {
        this.header.set_revision(0, 2);
    }
    
    void fatal(final String s) {
        System.err.println(String.valueOf(s) + " @ $" + Integer.toString(this.pc, 16));
        System.exit(-1);
    }
    
    short get_variable(final short n) {
        final short n2 = (short)(n & 0xFF);
        short n3;
        if (n2 == 0) {
            try {
                n3 = (short)(this.zstack.pop() & 0xFFFF);
            }
            catch (EmptyStackException ex) {
                this.fatal("Empty Stack");
                n3 = -1;
            }
        }
        else if (n2 >= 16) {
            n3 = (short)((this.memory_image[this.globals + (n2 - 16 << 1)] << 8 & 0xFF00) | (this.memory_image[this.globals + (n2 - 16 << 1) + 1] & 0xFF));
        }
        else {
            n3 = this.locals[n2 - 1];
        }
        return n3;
    }
    
    void set_variable(final short n, final short n2) {
        final short n3 = (short)(n & 0xFF);
        if (n3 == 0) {
            this.zstack.push(new Integer(n2));
            return;
        }
        if (n3 >= 16) {
            this.memory_image[this.globals + (n3 - 16 << 1)] = (byte)(n2 >>> 8);
            this.memory_image[this.globals + (n3 - 16 << 1) + 1] = (byte)(n2 & 0xFF);
            return;
        }
        this.locals[n3 - 1] = n2;
    }
    
    byte get_code_byte() {
        return this.memory_image[this.pc++];
    }
    
    short get_operand(final int n) {
        switch (n) {
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
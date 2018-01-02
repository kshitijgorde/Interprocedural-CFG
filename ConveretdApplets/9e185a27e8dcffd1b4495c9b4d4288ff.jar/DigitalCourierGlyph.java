import java.awt.Color;

// 
// Decompiled by Procyon v0.5.30
// 

public class DigitalCourierGlyph extends DigitalCell implements DigitalGlyph
{
    private Object[] chrCache;
    private String[] chr;
    private char chrGlyph;
    
    public DigitalCourierGlyph() {
        super(Color.green, 0, 0, 7, 9);
        this.chrCache = new Object[256];
    }
    
    public boolean setDigitalCharacter(final char character) {
        if (character == this.chrGlyph) {
            return false;
        }
        this.chrGlyph = character;
        if (this.chrCache[character] == null) {
            switch (character) {
                case 'a': {
                    this.chr = this.char_a();
                    break;
                }
                case 'b': {
                    this.chr = this.char_b();
                    break;
                }
                case 'c': {
                    this.chr = this.char_c();
                    break;
                }
                case 'd': {
                    this.chr = this.char_d();
                    break;
                }
                case 'e': {
                    this.chr = this.char_e();
                    break;
                }
                case 'f': {
                    this.chr = this.char_f();
                    break;
                }
                case 'g': {
                    this.chr = this.char_g();
                    break;
                }
                case 'h': {
                    this.chr = this.char_h();
                    break;
                }
                case 'i': {
                    this.chr = this.char_i();
                    break;
                }
                case 'j': {
                    this.chr = this.char_j();
                    break;
                }
                case 'k': {
                    this.chr = this.char_k();
                    break;
                }
                case 'l': {
                    this.chr = this.char_l();
                    break;
                }
                case 'm': {
                    this.chr = this.char_m();
                    break;
                }
                case 'n': {
                    this.chr = this.char_n();
                    break;
                }
                case 'o': {
                    this.chr = this.char_o();
                    break;
                }
                case 'p': {
                    this.chr = this.char_p();
                    break;
                }
                case 'q': {
                    this.chr = this.char_q();
                    break;
                }
                case 'r': {
                    this.chr = this.char_r();
                    break;
                }
                case 's': {
                    this.chr = this.char_s();
                    break;
                }
                case 't': {
                    this.chr = this.char_t();
                    break;
                }
                case 'u': {
                    this.chr = this.char_u();
                    break;
                }
                case 'v': {
                    this.chr = this.char_v();
                    break;
                }
                case 'w': {
                    this.chr = this.char_w();
                    break;
                }
                case 'x': {
                    this.chr = this.char_x();
                    break;
                }
                case 'y': {
                    this.chr = this.char_y();
                    break;
                }
                case 'z': {
                    this.chr = this.char_z();
                    break;
                }
                case 'A': {
                    this.chr = this.char_A();
                    break;
                }
                case 'B': {
                    this.chr = this.char_B();
                    break;
                }
                case 'C': {
                    this.chr = this.char_C();
                    break;
                }
                case 'D': {
                    this.chr = this.char_D();
                    break;
                }
                case 'E': {
                    this.chr = this.char_E();
                    break;
                }
                case 'F': {
                    this.chr = this.char_F();
                    break;
                }
                case 'G': {
                    this.chr = this.char_G();
                    break;
                }
                case 'H': {
                    this.chr = this.char_H();
                    break;
                }
                case 'I': {
                    this.chr = this.char_I();
                    break;
                }
                case 'J': {
                    this.chr = this.char_J();
                    break;
                }
                case 'K': {
                    this.chr = this.char_K();
                    break;
                }
                case 'L': {
                    this.chr = this.char_L();
                    break;
                }
                case 'M': {
                    this.chr = this.char_M();
                    break;
                }
                case 'N': {
                    this.chr = this.char_N();
                    break;
                }
                case 'O': {
                    this.chr = this.char_O();
                    break;
                }
                case 'P': {
                    this.chr = this.char_P();
                    break;
                }
                case 'Q': {
                    this.chr = this.char_Q();
                    break;
                }
                case 'R': {
                    this.chr = this.char_R();
                    break;
                }
                case 'S': {
                    this.chr = this.char_S();
                    break;
                }
                case 'T': {
                    this.chr = this.char_T();
                    break;
                }
                case 'U': {
                    this.chr = this.char_U();
                    break;
                }
                case 'V': {
                    this.chr = this.char_V();
                    break;
                }
                case 'W': {
                    this.chr = this.char_W();
                    break;
                }
                case 'X': {
                    this.chr = this.char_X();
                    break;
                }
                case 'Y': {
                    this.chr = this.char_Y();
                    break;
                }
                case 'Z': {
                    this.chr = this.char_Z();
                    break;
                }
                case '0': {
                    this.chr = this.char_O();
                    break;
                }
                case '1': {
                    this.chr = this.char_1();
                    break;
                }
                case '2': {
                    this.chr = this.char_2();
                    break;
                }
                case '3': {
                    this.chr = this.char_3();
                    break;
                }
                case '4': {
                    this.chr = this.char_4();
                    break;
                }
                case '5': {
                    this.chr = this.char_5();
                    break;
                }
                case '6': {
                    this.chr = this.char_6();
                    break;
                }
                case '7': {
                    this.chr = this.char_7();
                    break;
                }
                case '8': {
                    this.chr = this.char_8();
                    break;
                }
                case '9': {
                    this.chr = this.char_9();
                    break;
                }
                case ',': {
                    this.chr = this.char_comma();
                    break;
                }
                case '.': {
                    this.chr = this.char_fstop();
                    break;
                }
                case '/': {
                    this.chr = this.char_slash();
                    break;
                }
                case '?': {
                    this.chr = this.char_quest();
                    break;
                }
                case ';': {
                    this.chr = this.char_scolon();
                    break;
                }
                case ':': {
                    this.chr = this.char_colon();
                    break;
                }
                case '\'': {
                    this.chr = this.char_quote();
                    break;
                }
                case '\"': {
                    this.chr = this.char_dquote();
                    break;
                }
                case '-': {
                    this.chr = this.char_hyphen();
                    break;
                }
                case '+': {
                    this.chr = this.char_plus();
                    break;
                }
                case '(': {
                    this.chr = this.char_lbracket();
                    break;
                }
                case ')': {
                    this.chr = this.char_rbracket();
                    break;
                }
                case '!': {
                    this.chr = this.char_shriek();
                    break;
                }
                case '@': {
                    this.chr = this.char_at();
                    break;
                }
                case '$': {
                    this.chr = this.char_dollar();
                    break;
                }
                case '%': {
                    this.chr = this.char_percent();
                    break;
                }
                case ' ': {
                    this.chr = this.char_space();
                    break;
                }
                default: {
                    this.chr = this.char_other();
                    break;
                }
            }
            this.chrCache[character] = this.chr;
        }
        else {
            this.chr = (String[])this.chrCache[character];
        }
        this.setLightPattern(this.chr);
        return true;
    }
    
    private String[] char_a() {
        final String[] chr = { "0000000", "0000000", "0011100", "0100010", "0011110", "0100010", "0111111", "0000000", "0000000" };
        return chr;
    }
    
    private String[] char_b() {
        final String[] chr = { "1100000", "0100000", "0101100", "0110010", "0100010", "0100010", "1111100", "0000000", "0000000" };
        return chr;
    }
    
    private String[] char_c() {
        final String[] chr = { "0000000", "0000000", "0011110", "0100010", "0100000", "0100000", "0011110", "0000000", "0000000" };
        return chr;
    }
    
    private String[] char_d() {
        final String[] chr = { "0000110", "0000010", "0011010", "0100110", "0100010", "0100010", "0011111", "0000000", "0000000" };
        return chr;
    }
    
    private String[] char_e() {
        final String[] chr = { "0000000", "0000000", "0011100", "0100010", "0111110", "0100000", "0011110", "0000000", "0000000" };
        return chr;
    }
    
    private String[] char_f() {
        final String[] chr = { "0000110", "0001000", "0011110", "0001000", "0001000", "0001000", "0011110", "0000000", "0000000" };
        return chr;
    }
    
    private String[] char_g() {
        final String[] chr = { "0000000", "0000000", "0011011", "0100110", "0100010", "0100010", "0011110", "0000010", "0011100" };
        return chr;
    }
    
    private String[] char_h() {
        final String[] chr = { "1100000", "0100000", "0101100", "0110010", "0100010", "0100010", "1110111", "0000000", "0000000" };
        return chr;
    }
    
    private String[] char_i() {
        final String[] chr = { "0001000", "0000000", "0111000", "0001000", "0001000", "0001000", "0111110", "0000000", "0000000" };
        return chr;
    }
    
    private String[] char_j() {
        final String[] chr = { "0001000", "0000000", "0111100", "0000100", "0000100", "0000100", "0000100", "0000100", "0111000" };
        return chr;
    }
    
    private String[] char_k() {
        final String[] chr = { "1100000", "0100000", "0101110", "0100100", "0111000", "0100100", "1101110", "0000000", "0000000" };
        return chr;
    }
    
    private String[] char_l() {
        final String[] chr = { "0011000", "0001000", "0001000", "0001000", "0001000", "0001000", "0111110", "0000000", "0000000" };
        return chr;
    }
    
    private String[] char_m() {
        final String[] chr = { "0000000", "0000000", "1110100", "0101010", "0101010", "0101010", "1111111", "0000000", "0000000" };
        return chr;
    }
    
    private String[] char_n() {
        final String[] chr = { "0000000", "0000000", "1101100", "0110010", "0100010", "0100010", "1110111", "0000000", "0000000" };
        return chr;
    }
    
    private String[] char_o() {
        final String[] chr = { "0000000", "0000000", "0011100", "0100010", "0100010", "0100010", "0011100", "0000000", "0000000" };
        return chr;
    }
    
    private String[] char_p() {
        final String[] chr = { "0000000", "0000000", "1101100", "0110010", "0100010", "0100010", "0111100", "0100000", "1110000" };
        return chr;
    }
    
    private String[] char_q() {
        final String[] chr = { "0000000", "0000000", "0011011", "0100110", "0100010", "0100010", "0011110", "0000010", "0000111" };
        return chr;
    }
    
    private String[] char_r() {
        final String[] chr = { "0000000", "0000000", "0110110", "0011000", "0010000", "0010000", "0111100", "0000000", "0000000" };
        return chr;
    }
    
    private String[] char_s() {
        final String[] chr = { "0000000", "0000000", "0011110", "0100000", "0011100", "0000010", "0111100", "0000000", "0000000" };
        return chr;
    }
    
    private String[] char_t() {
        final String[] chr = { "0000000", "0100000", "1111100", "0100000", "0100000", "0100010", "0011100", "0000000", "0000000" };
        return chr;
    }
    
    private String[] char_u() {
        final String[] chr = { "0000000", "0000000", "1100110", "0100010", "0100010", "0100110", "0011011", "0000000", "0000000" };
        return chr;
    }
    
    private String[] char_v() {
        final String[] chr = { "0000000", "0000000", "1110111", "0100010", "0100100", "0010100", "0011000", "0000000", "0000000" };
        return chr;
    }
    
    private String[] char_w() {
        final String[] chr = { "0000000", "0000000", "1110111", "0100010", "0101010", "0101010", "0010100", "0000000", "0000000" };
        return chr;
    }
    
    private String[] char_x() {
        final String[] chr = { "0000000", "0000000", "0110110", "0010100", "0001000", "0010100", "0110110", "0000000", "0000000" };
        return chr;
    }
    
    private String[] char_y() {
        final String[] chr = { "0000000", "0000000", "1110111", "0100010", "0010100", "0010100", "0001000", "0001000", "0111000" };
        return chr;
    }
    
    private String[] char_z() {
        final String[] chr = { "0000000", "0000000", "0111110", "0100100", "0001000", "0010010", "0111110", "0000000", "0000000" };
        return chr;
    }
    
    private String[] char_A() {
        final String[] chr = { "0011000", "0001000", "0010100", "0010100", "0011100", "0100010", "1110111", "0000000", "0000000" };
        return chr;
    }
    
    private String[] char_B() {
        final String[] chr = { "1111100", "0100010", "0100010", "0111100", "0100010", "0100010", "1111100", "0000000", "0000000" };
        return chr;
    }
    
    private String[] char_C() {
        final String[] chr = { "0011110", "0100010", "0100000", "0100000", "0100000", "0100010", "0011100", "0000000", "0000000" };
        return chr;
    }
    
    private String[] char_D() {
        final String[] chr = { "1111000", "0100100", "0100010", "0100010", "0100010", "0100100", "1111000", "0000000", "0000000" };
        return chr;
    }
    
    private String[] char_E() {
        final String[] chr = { "0111110", "0010010", "0010100", "0011100", "0010100", "0010010", "0111110", "0000000", "0000000" };
        return chr;
    }
    
    private String[] char_F() {
        final String[] chr = { "0111110", "0010010", "0010100", "0011100", "0010100", "0010000", "0111000", "0000000", "0000000" };
        return chr;
    }
    
    private String[] char_G() {
        final String[] chr = { "0011110", "0100010", "0100000", "0100000", "0100111", "0100010", "0011100", "0000000", "0000000" };
        return chr;
    }
    
    private String[] char_H() {
        final String[] chr = { "1110111", "0100010", "0100010", "0111110", "0100010", "0100010", "1110111", "0000000", "0000000" };
        return chr;
    }
    
    private String[] char_I() {
        final String[] chr = { "0111110", "0001000", "0001000", "0001000", "0001000", "0001000", "0111110", "0000000", "0000000" };
        return chr;
    }
    
    private String[] char_J() {
        final String[] chr = { "0011110", "0000100", "0000100", "0000100", "0100100", "0100100", "0011000", "0000000", "0000000" };
        return chr;
    }
    
    private String[] char_K() {
        final String[] chr = { "1110111", "0100010", "0100100", "0111000", "0100100", "0100010", "1110011", "0000000", "0000000" };
        return chr;
    }
    
    private String[] char_L() {
        final String[] chr = { "0111000", "0010000", "0010000", "0010000", "0010010", "0010010", "0111110", "0000000", "0000000" };
        return chr;
    }
    
    private String[] char_M() {
        final String[] chr = { "1110111", "0110110", "0110110", "0101010", "0100010", "0100010", "1110111", "0000000", "0000000" };
        return chr;
    }
    
    private String[] char_N() {
        final String[] chr = { "1110111", "0110010", "0110010", "0101010", "0101010", "0100110", "1110110", "0000000", "0000000" };
        return chr;
    }
    
    private String[] char_O() {
        final String[] chr = { "0011100", "0100010", "0100010", "0100010", "0100010", "0100010", "0011100", "0000000", "0000000" };
        return chr;
    }
    
    private String[] char_P() {
        final String[] chr = { "0111100", "0010010", "0010010", "0010010", "0011100", "0010000", "0111000", "0000000", "0000000" };
        return chr;
    }
    
    private String[] char_Q() {
        final String[] chr = { "0011100", "0100010", "0100010", "0100010", "0100010", "0100010", "0011100", "0001110", "0000000" };
        return chr;
    }
    
    private String[] char_R() {
        final String[] chr = { "0111100", "0010010", "0010010", "0010010", "0011100", "0010010", "0111001", "0000000", "0000000" };
        return chr;
    }
    
    private String[] char_S() {
        final String[] chr = { "0011010", "0100110", "0100000", "0011100", "0000010", "0100010", "0111100", "0000000", "0000000" };
        return chr;
    }
    
    private String[] char_T() {
        final String[] chr = { "0111110", "0101010", "0001000", "0001000", "0001000", "0001000", "0011100", "0000000", "0000000" };
        return chr;
    }
    
    private String[] char_U() {
        final String[] chr = { "1110111", "0100010", "0100010", "0100010", "0100010", "0100010", "0011100", "0000000", "0000000" };
        return chr;
    }
    
    private String[] char_V() {
        final String[] chr = { "1110111", "0100010", "0100010", "0100100", "0010100", "0010100", "0011000", "0000000", "0000000" };
        return chr;
    }
    
    private String[] char_W() {
        final String[] chr = { "1110111", "0100010", "0101010", "0101010", "0101010", "0101010", "0010100", "0000000", "0000000" };
        return chr;
    }
    
    private String[] char_X() {
        final String[] chr = { "1100011", "0100010", "0010100", "0001000", "0010100", "0100010", "1100011", "0000000", "0000000" };
        return chr;
    }
    
    private String[] char_Y() {
        final String[] chr = { "1110111", "0100010", "0010100", "0001000", "0001000", "0001000", "0011100", "0000000", "0000000" };
        return chr;
    }
    
    private String[] char_Z() {
        final String[] chr = { "0111110", "0100010", "0000100", "0001000", "0010000", "0100010", "0111110", "0000000", "0000000" };
        return chr;
    }
    
    private String[] char_1() {
        final String[] chr = { "0011000", "0001000", "0001000", "0001000", "0001000", "0001000", "0111110", "0000000", "0000000" };
        return chr;
    }
    
    private String[] char_2() {
        final String[] chr = { "0011100", "0100010", "0000100", "0001000", "0010000", "0100000", "0111110", "0000000", "0000000" };
        return chr;
    }
    
    private String[] char_3() {
        final String[] chr = { "0011100", "0100010", "0000010", "0001100", "0000010", "0100010", "0011100", "0000000", "0000000" };
        return chr;
    }
    
    private String[] char_4() {
        final String[] chr = { "0000100", "0001100", "0010100", "0100100", "0111110", "0000100", "0001110", "0000000", "0000000" };
        return chr;
    }
    
    private String[] char_5() {
        final String[] chr = { "0011110", "0010000", "0010000", "0011100", "0000010", "0100010", "0011100", "0000000", "0000000" };
        return chr;
    }
    
    private String[] char_6() {
        final String[] chr = { "0001110", "0010000", "0100000", "0111100", "0100010", "0100010", "0011100", "0000000", "0000000" };
        return chr;
    }
    
    private String[] char_7() {
        final String[] chr = { "0111110", "0100010", "0000010", "0000100", "0000100", "0001000", "0001000", "0000000", "0000000" };
        return chr;
    }
    
    private String[] char_8() {
        final String[] chr = { "0011100", "0100010", "0100010", "0011100", "0100010", "0100010", "0011100", "0000000", "0000000" };
        return chr;
    }
    
    private String[] char_9() {
        final String[] chr = { "0011100", "0100010", "0100010", "0011110", "0000010", "0000100", "0111000", "0000000", "0000000" };
        return chr;
    }
    
    private String[] char_comma() {
        final String[] chr = { "0000000", "0000000", "0000000", "0000000", "0000000", "0000000", "0001100", "0011000", "0010000" };
        return chr;
    }
    
    private String[] char_fstop() {
        final String[] chr = { "0000000", "0000000", "0000000", "0000000", "0000000", "0000000", "0011000", "0000000", "0000000" };
        return chr;
    }
    
    private String[] char_slash() {
        final String[] chr = { "0000010", "0000100", "0000100", "0001000", "0001000", "0010000", "0010000", "0100000", "0000000" };
        return chr;
    }
    
    private String[] char_quest() {
        final String[] chr = { "0011000", "0100100", "0000100", "0001000", "0001000", "0000000", "0011000", "0000000", "0000000" };
        return chr;
    }
    
    private String[] char_scolon() {
        final String[] chr = { "0000000", "0000000", "0001100", "0000000", "0000000", "0001100", "0011000", "0010000", "0000000" };
        return chr;
    }
    
    private String[] char_colon() {
        final String[] chr = { "0000000", "0000000", "0011000", "0000000", "0000000", "0000000", "0011000", "0000000", "0000000" };
        return chr;
    }
    
    private String[] char_quote() {
        final String[] chr = { "0001000", "0001000", "0001000", "0000000", "0000000", "0000000", "0000000", "0000000", "0000000" };
        return chr;
    }
    
    private String[] char_dquote() {
        final String[] chr = { "0010100", "0010100", "0010100", "0000000", "0000000", "0000000", "0000000", "0000000", "0000000" };
        return chr;
    }
    
    private String[] char_hyphen() {
        final String[] chr = { "0000000", "0000000", "0000000", "0111110", "0000000", "0000000", "0000000", "0000000", "0000000" };
        return chr;
    }
    
    private String[] char_plus() {
        final String[] chr = { "0001000", "0001000", "0001000", "0111110", "0001000", "0001000", "0001000", "0000000", "0000000" };
        return chr;
    }
    
    private String[] char_lbracket() {
        final String[] chr = { "0000100", "0000100", "0001000", "0001000", "0001000", "0001000", "0000100", "0000100", "0000000" };
        return chr;
    }
    
    private String[] char_rbracket() {
        final String[] chr = { "0010000", "0010000", "0001000", "0001000", "0001000", "0001000", "0010000", "0010000", "0000000" };
        return chr;
    }
    
    private String[] char_shriek() {
        final String[] chr = { "0001000", "0001000", "0001000", "0001000", "0001000", "0000000", "0001000", "0000000", "0000000" };
        return chr;
    }
    
    private String[] char_at() {
        final String[] chr = { "0011100", "0100010", "0100110", "0101010", "0101010", "0100110", "0100000", "0011110", "0000000" };
        return chr;
    }
    
    private String[] char_dollar() {
        final String[] chr = { "0001000", "0011100", "0100000", "0011000", "0000100", "0111000", "0010000", "0010000", "0000000" };
        return chr;
    }
    
    private String[] char_percent() {
        final String[] chr = { "0010000", "0101000", "0010000", "0111100", "0001000", "0010100", "0001000", "0000000", "0000000" };
        return chr;
    }
    
    private String[] char_space() {
        final String[] chr = { "0000000", "0000000", "0000000", "0000000", "0000000", "0000000", "0000000", "0000000", "0000000" };
        return chr;
    }
    
    private String[] char_other() {
        final String[] chr = { "0000000", "0000000", "0101010", "0010100", "0101010", "0010100", "0101010", "0000000", "0000000" };
        return chr;
    }
}

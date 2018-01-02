import java.util.Stack;
import java.awt.Color;
import java.util.Vector;

// 
// Decompiled by Procyon v0.5.30
// 

class Parser
{
    public String text;
    public int text_index;
    char current_char;
    int last_text_index;
    char last_char;
    public Evaluator evaluator;
    static String single_quote_chars;
    static String breaking_chars;
    static String white_characters;
    static String comment_end;
    static String[] standard_parts;
    static String[] standard_short_parts;
    static String coded_char_names;
    static boolean[] isletter0to370;
    static boolean[] isletter774to1009;
    static boolean[] isletter8211to8504;
    static boolean[] isletter8706to8738;
    static boolean[] isletter9634to9839;
    static boolean[] isletter62977to63421;
    double scanned_real;
    boolean is_scanned_real;
    public static final int TOKEN_COMMA = 0;
    public static final int TOKEN_LEFT_PARENTHESIS = 1;
    public static final int TOKEN_RIGHT_PARENTHESIS = 2;
    public static final int TOKEN_LEFT_BRACKET = 3;
    public static final int TOKEN_RIGHT_BRACKET = 4;
    public static final int TOKEN_LEFT_BRACE = 5;
    public static final int TOKEN_RIGHT_BRACE = 6;
    public static final int TOKEN_PLUS = 7;
    public static final int TOKEN_MINUS = 8;
    public static final int TOKEN_TIMES = 9;
    public static final int TOKEN_DIVIDE = 10;
    public static final int TOKEN_POWER = 11;
    public static final int TOKEN_FACTORIAL = 12;
    public static final int TOKEN_FACTORIAL2 = 13;
    public static final int TOKEN_EQUAL = 14;
    public static final int TOKEN_UNEQUAL = 15;
    public static final int TOKEN_LESS = 16;
    public static final int TOKEN_GREATER = 17;
    public static final int TOKEN_LESSEQUAL = 18;
    public static final int TOKEN_GREATEREQUAL = 19;
    public static final int TOKEN_RIGHT_ARROW = 20;
    public static final int TOKEN_IDENTIFIER = 21;
    public static final int TOKEN_NUMBER = 22;
    public static final int TOKEN_STRING = 23;
    public static final int TOKEN_TERMINATE = 24;
    public static final int TOKEN_NONE = 25;
    public static final int TOKEN_VOID = 26;
    public static final int TOKEN_UNARY_PLUS = 27;
    public static final int TOKEN_UNARY_MINUS = 28;
    public static final int TOKEN_INVALID_IDENTIFIER = 29;
    public static final int TOKEN_FUNCTION_IDENTIFIER = 30;
    public static final int TOKEN_INEQUALITY = 31;
    public static final int TOKEN_AND = 32;
    public static final int TOKEN_OR = 33;
    public static final int TOKEN_NOT = 34;
    public static final int TOKEN_SEMICOLON = 35;
    public static final int TOKEN_SET = 36;
    static String structure_characters;
    static String number_start;
    static String digit_characters;
    static String octal_digits;
    static String hexadecimal_digits;
    int scanned_token_type;
    double scanned_number;
    String scanned_identifier;
    String scanned_string;
    int scanned_function_identifier;
    static int max_length_identifier;
    char[] scanned_identifier_chars;
    boolean scanned_left_bracket;
    boolean is_scanning_expression;
    boolean is_scanning_after_operand;
    public static int CONSTRUCT_NONE;
    public static int CONSTRUCT_FUNCTION;
    public static int CONSTRUCT_SYMBOL;
    public static int CONSTRUCT_LIST;
    public static int CONSTRUCT_RULE;
    public static int CONSTRUCT_UNIDENTIFIED;
    static String name_ShowAnimation;
    static String name_Animate;
    static String name_AnimationDisplayTime;
    static String name_AnimationDirection;
    static String name_Forward;
    static String name_Backward;
    static String name_ForwardBackward;
    static String name_Graphics3D;
    static String name_If;
    static String name_Scaled;
    static String name_Cuboid;
    static String name_Line;
    static String name_Point;
    static String name_Polygon;
    static String name_Text;
    static String name_StyleForm;
    static String name_Questionmark;
    static String name_TextStyle;
    static String name_FontWeight;
    static String name_Bold;
    static String name_FontSize;
    static String name_FontSlant;
    static String name_Italic;
    static String name_FontFamily;
    static String name_FontColor;
    static String name_URL;
    static String name_AbsolutePointSize;
    static String name_AbsoluteThickness;
    static String name_CMYKColor;
    static String name_EdgeForm;
    static String name_FaceForm;
    static String name_GrayLevel;
    static String name_Hue;
    static String name_PointSize;
    static String name_RGBColor;
    static String name_SurfaceColor;
    static String name_Thickness;
    static String name_AmbientLight;
    static String name_Axes;
    static String name_AxesLabel;
    static String name_AxesEdge;
    static String name_AxesStyle;
    static String name_PlotLabel;
    static String name_Background;
    static String name_Boxed;
    static String name_BoxRatios;
    static String name_BoxStyle;
    static String name_DefaultColor;
    static String name_Lighting;
    static String name_LightSources;
    static String name_PlotRange;
    static String name_Ticks;
    static String name_ViewPoint;
    static String name_ViewVertical;
    static String name_True;
    static String name_False;
    static String name_Automatic;
    static String name_All;
    static String name_None;
    static String name_Catalan;
    static String name_Degree;
    static String name_E;
    static String name_EulerGamma;
    static String name_Glaisher;
    static String name_GoldenRatio;
    static String name_Khinchin;
    static String name_Pi;
    static String name_I;
    static String name_Infinity;
    static String name_Indeterminate;
    static String name_ComplexInfinity;
    static final int FUNCTION_N = 0;
    static final int FUNCTION_PLUS = 1;
    static final int FUNCTION_SUBTRACT = 2;
    static final int FUNCTION_MINUS = 3;
    static final int FUNCTION_TIMES = 4;
    static final int FUNCTION_DIVIDE = 5;
    static final int FUNCTION_POWER = 6;
    static final int FUNCTION_ABS = 7;
    static final int FUNCTION_SIGN = 8;
    static final int FUNCTION_ROUND = 9;
    static final int FUNCTION_INTEGERPART = 10;
    static final int FUNCTION_FRACTIONALPART = 11;
    static final int FUNCTION_FLOOR = 12;
    static final int FUNCTION_CEILING = 13;
    static final int FUNCTION_CHOP = 14;
    static final int FUNCTION_MAX = 15;
    static final int FUNCTION_MIN = 16;
    static final int FUNCTION_RE = 17;
    static final int FUNCTION_IM = 18;
    static final int FUNCTION_CONJUGATE = 19;
    static final int FUNCTION_ARG = 20;
    static final int FUNCTION_MOD = 21;
    static final int FUNCTION_QUOTIENT = 22;
    static final int FUNCTION_RANDOM = 23;
    static final int FUNCTION_SEEDRANDOM = 24;
    static final int FUNCTION_LOG = 25;
    static final int FUNCTION_EXP = 26;
    static final int FUNCTION_SQRT = 27;
    static final int FUNCTION_SIN = 28;
    static final int FUNCTION_COS = 29;
    static final int FUNCTION_TAN = 30;
    static final int FUNCTION_CSC = 31;
    static final int FUNCTION_SEC = 32;
    static final int FUNCTION_COT = 33;
    static final int FUNCTION_ARCSIN = 34;
    static final int FUNCTION_ARCCOS = 35;
    static final int FUNCTION_ARCTAN = 36;
    static final int FUNCTION_ARCCSC = 37;
    static final int FUNCTION_ARCSEC = 38;
    static final int FUNCTION_ARCCOT = 39;
    static final int FUNCTION_SINH = 40;
    static final int FUNCTION_COSH = 41;
    static final int FUNCTION_TANH = 42;
    static final int FUNCTION_CSCH = 43;
    static final int FUNCTION_SECH = 44;
    static final int FUNCTION_COTH = 45;
    static final int FUNCTION_ARCSINH = 46;
    static final int FUNCTION_ARCCOSH = 47;
    static final int FUNCTION_ARCTANH = 48;
    static final int FUNCTION_ARCCSCH = 49;
    static final int FUNCTION_ARCSECH = 50;
    static final int FUNCTION_ARCCOTH = 51;
    static final int FUNCTION_FACTORIAL = 52;
    static final int FUNCTION_FACTORIAL2 = 53;
    static final int FUNCTION_BINOMIAL = 54;
    static final int FUNCTION_MULTINOMIAL = 55;
    static final int FUNCTION_POCHHAMMER = 56;
    static final int FUNCTION_GAMMA = 57;
    static final int FUNCTION_LOGGAMMA = 58;
    static final int FUNCTION_POWERMOD = 59;
    static final int FUNCTION_ERF = 60;
    static final int FUNCTION_ERFC = 61;
    static final int FUNCTION_ERFI = 62;
    static final int FUNCTION_RATIONALIZE = 63;
    static final int FUNCTION_EQUAL = 64;
    static final int FUNCTION_UNEQUAL = 65;
    static final int FUNCTION_LESS = 66;
    static final int FUNCTION_GREATER = 67;
    static final int FUNCTION_LESSEQUAL = 68;
    static final int FUNCTION_GREATEREQUAL = 69;
    static final int FUNCTION_NUMBERQ = 70;
    static final int FUNCTION_NUMERICQ = 71;
    static final int FUNCTION_INTEGERQ = 72;
    static final int FUNCTION_EVENQ = 73;
    static final int FUNCTION_ODDQ = 74;
    static final int FUNCTION_POSITIVE = 75;
    static final int FUNCTION_NEGATIVE = 76;
    static final int FUNCTION_NONPOSITIVE = 77;
    static final int FUNCTION_NONNEGATIVE = 78;
    static final int FUNCTION_TRUEQ = 79;
    static final int FUNCTION_VALUEQ = 80;
    static final int FUNCTION_NOT = 81;
    static final int FUNCTION_AND = 82;
    static final int FUNCTION_OR = 83;
    static final int FUNCTION_XOR = 84;
    static final int FUNCTION_IMPLIES = 85;
    static final int FUNCTION_IF = 86;
    static final int FUNCTION_WHICH = 87;
    static final int FUNCTION_SWITCH = 88;
    static final int FUNCTION_IDENTITY = 89;
    static final int FUNCTION_EVALUATE = 90;
    static final int FUNCTION_HOLD = 91;
    static final int FUNCTION_HOLDCOMPLETE = 92;
    static final int FUNCTION_HOLDFORM = 93;
    static final int FUNCTION_RELEASEHOLD = 94;
    static final int FUNCTION_NUMBERFORM = 95;
    static final int FUNCTION_SCIENTIFICFORM = 96;
    static final int FUNCTION_ENGINEERINGFORM = 97;
    static final int FUNCTION_ACCOUNTINGFORM = 98;
    static final int FUNCTION_PADDEDFORM = 99;
    static final int FUNCTION_STANDARDFORM = 100;
    static final int FUNCTION_TRADITIONALFORM = 101;
    static final int FUNCTION_INPUTFORM = 102;
    static final int FUNCTION_OUTPUTFORM = 103;
    static final int FUNCTION_DISPLAYFORM = 104;
    static final int FUNCTION_FULLFORM = 105;
    static final int FUNCTION_SESSIONTIME = 106;
    static final int FUNCTION_TIMEUSED = 107;
    static final int FUNCTION_ABSOLUTETIME = 108;
    static final int FUNCTION_DISCRETEDELTA = 109;
    static final int FUNCTION_KRONECKERDELTA = 110;
    static final int FUNCTION_UNITSTEP = 111;
    static final int FUNCTION_BETA = 112;
    static final int FUNCTION_SET = 113;
    static final int FUNCTIONS_COUNT = 114;
    static String[] function_names;
    boolean scanning_EdgeForm;
    boolean scanning_FaceForm;
    boolean scanning_FaceForm_back;
    boolean scanning_SurfaceColor;
    boolean scanning_SurfaceColor_specular;
    boolean scanning_AxesStyles;
    int scanned_AnimationDirection;
    double scanned_Animate_min;
    double scanned_Animate_max;
    double scanned_Animate_step;
    int scanned_Animate_variable;
    Vector scanned_frames;
    boolean scanning_animation_option;
    Graphics3D graphics;
    boolean is_scanned_AmbientLight;
    boolean is_scanned_Axes;
    boolean is_scanned_AxesLabel;
    boolean is_scanned_AxesStyle;
    boolean is_scanned_AxesEdge;
    boolean is_scanned_Ticks;
    boolean is_scanned_PlotLabel;
    boolean is_scanned_Background;
    boolean is_scanned_DefaultColor;
    boolean is_scanned_BoxStyle;
    boolean is_scanned_Boxed;
    boolean is_scanned_Lighting;
    boolean is_scanned_BoxRatios;
    boolean is_scanned_PlotRange;
    boolean is_scanned_LightSources;
    boolean is_scanned_ViewPoint;
    boolean is_scanned_ViewVertical;
    boolean is_scanned_TextStyle;
    boolean is_scanned_AnimationDisplayTime;
    boolean scanned_nothing;
    boolean scanned_unidentified;
    int recursion_depth;
    int if_recursion_depth;
    Primitive3D[] scanned_AxesStyles;
    static int max_count_scanned_numbers;
    double[] scanned_numbers;
    int count_scanned_numbers;
    int count_scanned_points;
    double[][] scanned_points;
    double[][] scanned_scaled_offsets;
    int[][] scanned_expressions;
    Vector points_scanned;
    Vector scaled_offsets_scanned;
    Vector expressions_scanned;
    Color scanned_color;
    String scanned_font_url;
    String scanned_font_family;
    int scanned_font_weight;
    int scanned_font_slant;
    int scanned_font_size;
    Color scanned_font_color;
    Color scanned_font_background;
    int scanned_expression;
    boolean is_scanned_expression_numeric;
    static int[] arguments_counts;
    
    public Parser(final String new_text, final Evaluator new_evaluator) {
        this.scanned_identifier_chars = new char[Parser.max_length_identifier];
        this.is_scanning_expression = false;
        this.is_scanning_after_operand = false;
        this.scanned_AnimationDirection = 1;
        this.scanned_Animate_min = 0.0;
        this.scanned_Animate_max = 0.0;
        this.scanned_Animate_step = 1.0;
        this.scanned_Animate_variable = -1;
        this.scanning_animation_option = false;
        this.recursion_depth = 0;
        this.if_recursion_depth = 0;
        this.scanned_AxesStyles = new Primitive3D[] { null, null, null };
        this.scanned_numbers = new double[Parser.max_count_scanned_numbers];
        this.evaluator = new_evaluator;
        this.set_text(new_text);
    }
    
    public void set_text(final String new_text) {
        this.text = new_text;
        this.text_index = -1;
        this.current_char = '\0';
        this.last_text_index = -1;
        this.last_char = '\0';
        this.is_scanning_expression = false;
        this.is_scanning_after_operand = false;
    }
    
    Color copied_color(final Color color) {
        if (null == color) {
            return null;
        }
        return new Color(color.getRed(), color.getGreen(), color.getBlue());
    }
    
    boolean isDigitOrLetterLike(final char ch) {
        return (ch <= '\u0172' && Parser.isletter0to370[ch]) || '\u025b' == ch || ('\u0306' <= ch && ch <= '\u03f1' && Parser.isletter774to1009[ch - '\u0306']) || ('\u2013' <= ch && ch <= '\u2138' && Parser.isletter8211to8504[ch - '\u2013']) || ('\u2013' <= ch && ch <= '\u2138' && Parser.isletter8211to8504[ch - '\u2013']) || ('\u2202' <= ch && ch <= '\u2222' && Parser.isletter8706to8738[ch - '\u2202']) || ('\u22ee' <= ch && ch <= '\u22f1') || ('\u25a2' <= ch && ch <= '\u266f' && Parser.isletter9634to9839[ch - '\u25a2']) || '\uf404' == ch || ('\uf527' <= ch && ch <= '\uf529') || ('\uf601' <= ch && ch <= '\uf7bd' && Parser.isletter62977to63421[ch - '\uf601']);
    }
    
    char next_char() {
        boolean processed = false;
        this.last_char = this.current_char;
        this.last_text_index = this.text_index;
        ++this.text_index;
        if (this.text_index < 0 || this.text_index >= this.text.length()) {
            return this.current_char = '\f';
        }
        while (!processed) {
            this.current_char = this.text.charAt(this.text_index);
            if ('\\' == this.current_char && this.text_index + 1 < this.text.length()) {
                ++this.text_index;
                this.current_char = this.text.charAt(this.text_index);
                if ('[' == this.current_char) {
                    ++this.text_index;
                    final int special_char_index = this.text_index;
                    while (this.text_index < this.text.length() && (this.isDigitOrLetterLike(this.text.charAt(this.text_index)) || 0 <= Parser.breaking_chars.indexOf(this.text.charAt(this.text_index)))) {
                        ++this.text_index;
                    }
                    if (this.text_index >= this.text.length() || ']' != this.text.charAt(this.text_index)) {
                        this.text_index = this.last_text_index + 1;
                        this.current_char = '\\';
                        processed = true;
                    }
                    else {
                        String char_name = this.text.substring(special_char_index, this.text_index);
                        int index;
                        int part_length;
                        for (int length = -1, offset = 0; length != char_name.length(); char_name = char_name.substring(0, offset) + Parser.standard_short_parts[index] + char_name.substring(offset + part_length), ++offset) {
                            length = char_name.length();
                            for (index = 0; (part_length = Parser.standard_parts[index].length()) > 0; ++index) {
                                if (char_name.regionMatches(offset, Parser.standard_parts[index], 0, part_length)) {
                                    break;
                                }
                            }
                        }
                        int uni_code = -1;
                        if (char_name.equals("n")) {
                            uni_code = 172;
                        }
                        else if (char_name.length() >= 2) {
                            int offset = 0;
                            final String pattern = char_name.substring(0, 2);
                            while (offset >= 0) {
                                offset = Parser.coded_char_names.indexOf(pattern, offset + 1);
                                if (offset >= 0 && Character.isDigit(Parser.coded_char_names.charAt(offset - 1))) {
                                    int digit_index;
                                    for (digit_index = offset + 2; !Character.isDigit(Parser.coded_char_names.charAt(digit_index)); ++digit_index) {}
                                    int end_digit_index;
                                    for (end_digit_index = digit_index + 1; Character.isDigit(Parser.coded_char_names.charAt(end_digit_index)); ++end_digit_index) {}
                                    if (!char_name.regionMatches(0, Parser.coded_char_names, offset, digit_index - offset)) {
                                        continue;
                                    }
                                    offset = -1;
                                    uni_code = Integer.parseInt(Parser.coded_char_names.substring(digit_index, end_digit_index));
                                }
                            }
                        }
                        if (uni_code >= 0) {
                            this.current_char = (char)uni_code;
                        }
                        else {
                            this.current_char = '¿';
                        }
                        processed = true;
                    }
                }
                else if (0 <= Parser.white_characters.indexOf(this.current_char)) {
                    ++this.text_index;
                    while (this.text_index < this.text.length() && 0 <= Parser.white_characters.indexOf(this.text.charAt(this.text_index))) {
                        ++this.text_index;
                    }
                    if (this.text_index >= this.text.length()) {
                        this.text_index = this.text.length() - 1;
                        this.current_char = ' ';
                        processed = true;
                    }
                    else {
                        processed = false;
                    }
                }
                else {
                    this.current_char = '\\';
                    --this.text_index;
                    processed = true;
                }
            }
            else if (0 <= Parser.single_quote_chars.indexOf(this.current_char) && this.text_index + 1 < this.text.length() && 0 <= Parser.single_quote_chars.indexOf(this.text.charAt(this.text_index + 1))) {
                this.current_char = '\"';
                ++this.text_index;
                processed = true;
            }
            else if ('(' == this.current_char && this.text_index + 1 < this.text.length() && '*' == this.text.charAt(this.text_index + 1)) {
                this.text_index = this.text.indexOf(Parser.comment_end, this.text_index + 1);
                if (-1 == this.text_index) {
                    this.text_index = this.text.length() - 1;
                }
                else {
                    ++this.text_index;
                }
                this.current_char = ' ';
                processed = true;
            }
            else {
                processed = true;
            }
        }
        return this.current_char;
    }
    
    void unget_char() {
        this.current_char = this.last_char;
        this.text_index = this.last_text_index;
    }
    
    char first_non_white_char() {
        while (this.text_index < this.text.length() && 0 <= Parser.white_characters.indexOf(this.current_char)) {
            this.next_char();
        }
        return this.current_char;
    }
    
    void scan_real() {
        int sign = 1;
        double integer_part = 0.0;
        double fraction_part = 0.0;
        int count_fraction_digits = 0;
        this.is_scanned_real = false;
        if ('-' == this.current_char) {
            sign = -1;
            this.next_char();
        }
        else if ('+' == this.current_char) {
            this.next_char();
        }
        int index = Parser.digit_characters.indexOf(this.current_char);
        if (0 <= index) {
            this.is_scanned_real = true;
        }
        while (0 <= index) {
            integer_part = 10.0 * integer_part + index;
            index = Parser.digit_characters.indexOf(this.next_char());
        }
        if ('.' == this.current_char) {
            index = Parser.digit_characters.indexOf(this.next_char());
            if (0 <= index) {
                this.is_scanned_real = true;
            }
            while (0 <= index) {
                fraction_part = 10.0 * fraction_part + index;
                ++count_fraction_digits;
                index = Parser.digit_characters.indexOf(this.next_char());
            }
        }
        if (this.is_scanned_real) {
            if (0 == count_fraction_digits) {
                this.scanned_real = sign * integer_part;
            }
            else {
                this.scanned_real = sign * (integer_part + Math.pow(0.1, count_fraction_digits) * fraction_part);
            }
        }
    }
    
    void scan_token() {
        if (this.text_index < 0) {
            this.text_index = -1;
            this.next_char();
        }
        this.first_non_white_char();
        while (this.text_index < this.text.length()) {
            if (this.is_scanning_expression && '-' == this.current_char) {
                if ('>' == this.next_char()) {
                    this.scanned_token_type = 20;
                    this.is_scanning_after_operand = false;
                    this.next_char();
                    return;
                }
                if (this.is_scanning_after_operand) {
                    this.scanned_token_type = 8;
                    this.is_scanning_after_operand = false;
                }
                else {
                    this.scanned_token_type = 28;
                }
                return;
            }
            else {
                if (this.is_scanning_expression && '+' == this.current_char) {
                    if (this.is_scanning_after_operand) {
                        this.scanned_token_type = 7;
                        this.is_scanning_after_operand = false;
                    }
                    else {
                        this.scanned_token_type = 27;
                    }
                    this.next_char();
                    return;
                }
                if (0 <= Parser.number_start.indexOf(this.current_char)) {
                    if (this.is_scanning_expression && this.is_scanning_after_operand) {
                        this.scanned_token_type = 9;
                        this.is_scanning_after_operand = false;
                        return;
                    }
                    double mantissa = 0.0;
                    double exponent = 0.0;
                    double sign = 1.0;
                    boolean in_parenthesis = false;
                    if ('-' == this.current_char) {
                        sign = -1.0;
                        this.next_char();
                        if ('>' == this.current_char) {
                            this.scanned_token_type = 20;
                            this.next_char();
                            return;
                        }
                        if ('(' == this.current_char) {
                            in_parenthesis = true;
                            this.next_char();
                        }
                    }
                    this.scan_real();
                    if (this.is_scanned_real) {
                        mantissa = this.scanned_real;
                        if ('`' == this.current_char) {
                            this.next_char();
                            if ('`' == this.current_char) {
                                this.next_char();
                            }
                            this.scan_real();
                        }
                        boolean should_have_exponent = false;
                        boolean has_exponent = false;
                        if ('*' == this.current_char || 'e' == this.current_char || 'E' == this.current_char) {
                            should_have_exponent = true;
                            if ('e' == this.current_char || 'E' == this.current_char) {
                                has_exponent = true;
                            }
                            else if ('1' == this.next_char()) {
                                if ('0' == this.next_char() && '^' == this.next_char()) {
                                    has_exponent = true;
                                }
                            }
                            else if ('^' == this.current_char) {
                                has_exponent = true;
                            }
                            else {
                                this.unget_char();
                                should_have_exponent = false;
                            }
                            if (has_exponent) {
                                this.next_char();
                                this.scan_real();
                                if (this.is_scanned_real) {
                                    exponent = this.scanned_real;
                                }
                                else {
                                    has_exponent = false;
                                }
                            }
                        }
                        if (!in_parenthesis || ')' == this.current_char) {
                            if (in_parenthesis) {
                                this.next_char();
                            }
                            if (has_exponent || !should_have_exponent) {
                                this.scanned_number = sign * mantissa * Math.pow(10.0, exponent);
                                this.scanned_token_type = 22;
                                if (this.is_scanning_expression) {
                                    this.is_scanning_after_operand = true;
                                }
                                return;
                            }
                        }
                    }
                }
                else if (this.isDigitOrLetterLike(this.current_char)) {
                    if (this.is_scanning_expression && this.is_scanning_after_operand) {
                        this.scanned_token_type = 9;
                        this.is_scanning_after_operand = false;
                        return;
                    }
                    this.scanned_identifier_chars[0] = this.current_char;
                    int index = 1;
                    while (this.isDigitOrLetterLike(this.next_char())) {
                        if (index < Parser.max_length_identifier) {
                            this.scanned_identifier_chars[index] = this.current_char;
                            ++index;
                        }
                    }
                    if (index == 1) {
                        switch (this.scanned_identifier_chars[0]) {
                            case '\u03c0': {
                                this.scanned_identifier = "Pi";
                                break;
                            }
                            case '\u221e': {
                                this.scanned_identifier = "Infinity";
                                break;
                            }
                            case '\uf74d': {
                                this.scanned_identifier = "E";
                                break;
                            }
                            case '\uf74e':
                            case '\uf74f': {
                                this.scanned_identifier = "I";
                                break;
                            }
                            case '°': {
                                this.scanned_identifier = "Degree";
                                break;
                            }
                            default: {
                                this.scanned_identifier = new String(this.scanned_identifier_chars, 0, 1);
                                break;
                            }
                        }
                    }
                    else {
                        this.scanned_identifier = new String(this.scanned_identifier_chars, 0, index);
                    }
                    if (this.is_scanning_expression) {
                        this.is_scanning_after_operand = true;
                    }
                    if (this.first_non_white_char() == '[') {
                        this.scanned_left_bracket = true;
                    }
                    else {
                        this.scanned_left_bracket = false;
                    }
                    this.scanned_token_type = 21;
                    return;
                }
                else {
                    if (0 <= Parser.structure_characters.indexOf(this.current_char)) {
                        this.scanned_token_type = Parser.structure_characters.indexOf(this.current_char);
                        if (this.is_scanning_expression) {
                            if (1 == this.scanned_token_type && this.is_scanning_after_operand) {
                                this.scanned_token_type = 9;
                                this.is_scanning_after_operand = false;
                                return;
                            }
                            if (2 == this.scanned_token_type || 4 == this.scanned_token_type || 6 == this.scanned_token_type) {
                                this.is_scanning_after_operand = true;
                            }
                            else {
                                this.is_scanning_after_operand = false;
                            }
                        }
                        this.next_char();
                        return;
                    }
                    if (':' == this.current_char) {
                        if ('>' == this.next_char()) {
                            this.scanned_token_type = 20;
                            this.next_char();
                            if (this.is_scanning_expression) {
                                this.is_scanning_after_operand = false;
                            }
                            return;
                        }
                    }
                    else {
                        if ('\uf522' == this.current_char || '\uf51f' == this.current_char) {
                            this.scanned_token_type = 20;
                            this.next_char();
                            if (this.is_scanning_expression) {
                                this.is_scanning_after_operand = false;
                            }
                            return;
                        }
                        if ('\"' == this.current_char) {
                            final StringBuffer buffer = new StringBuffer(60);
                            this.next_char();
                            while (this.text_index < this.text.length() && '\"' != this.current_char) {
                                if ('\\' == this.current_char) {
                                    this.next_char();
                                    if ('n' == this.current_char) {
                                        buffer.append('\n');
                                    }
                                    else if ('b' == this.current_char) {
                                        buffer.append('\b');
                                    }
                                    else if ('t' == this.current_char) {
                                        buffer.append('\t');
                                    }
                                    else if ('r' == this.current_char) {
                                        buffer.append('\r');
                                    }
                                    else if ('f' == this.current_char) {
                                        buffer.append('\f');
                                    }
                                    else if (0 <= Parser.single_quote_chars.indexOf(this.current_char) || '\"' == this.current_char || '\\' == this.current_char) {
                                        buffer.append(this.current_char);
                                        this.current_char = '\\';
                                    }
                                    else if (0 <= Parser.octal_digits.indexOf(this.current_char) || '.' == this.current_char || ':' == this.current_char) {
                                        int count_digits = 3;
                                        int uni_code = 0;
                                        if ('.' == this.current_char) {
                                            count_digits = 2;
                                            this.next_char();
                                        }
                                        if (':' == this.current_char) {
                                            count_digits = 4;
                                            this.next_char();
                                        }
                                        int digit_index;
                                        for (digit_index = 0; digit_index < count_digits; ++digit_index) {
                                            int index2 = Parser.hexadecimal_digits.indexOf(this.current_char);
                                            if (0 > index2) {
                                                break;
                                            }
                                            if (16 <= index2) {
                                                index2 -= 6;
                                            }
                                            if (3 == count_digits) {
                                                uni_code = uni_code * 8 + index2;
                                            }
                                            else {
                                                uni_code = uni_code * 16 + index2;
                                            }
                                            if (digit_index + 1 < count_digits) {
                                                this.next_char();
                                            }
                                        }
                                        buffer.append((char)uni_code);
                                        if (digit_index < count_digits) {
                                            buffer.append(this.current_char);
                                        }
                                    }
                                    else {
                                        buffer.append('\\');
                                        buffer.append(this.current_char);
                                    }
                                }
                                else if ('\n' != this.current_char && '\r' != this.current_char && '\t' != this.current_char && '\f' != this.current_char) {
                                    buffer.append(this.current_char);
                                }
                                this.next_char();
                            }
                            if (this.text_index < this.text.length()) {
                                this.scanned_token_type = 23;
                                this.scanned_string = new String(buffer);
                                if (this.is_scanning_expression) {
                                    this.is_scanning_after_operand = true;
                                }
                                this.next_char();
                                return;
                            }
                        }
                        else if (this.is_scanning_expression) {
                            if ('*' == this.current_char || '\u00d7' == this.current_char) {
                                this.scanned_token_type = 9;
                                this.is_scanning_after_operand = false;
                                this.next_char();
                                return;
                            }
                            if ('/' == this.current_char || '\u00f7' == this.current_char) {
                                this.scanned_token_type = 10;
                                this.is_scanning_after_operand = false;
                                this.next_char();
                                return;
                            }
                            if ('^' == this.current_char) {
                                this.scanned_token_type = 11;
                                this.is_scanning_after_operand = false;
                                this.next_char();
                                return;
                            }
                            if ('¬' == this.current_char) {
                                this.scanned_token_type = 34;
                                this.is_scanning_after_operand = false;
                                this.next_char();
                                return;
                            }
                            if ('\u2260' == this.current_char) {
                                this.scanned_token_type = 15;
                                this.is_scanning_after_operand = false;
                                this.next_char();
                                return;
                            }
                            if ('!' == this.current_char) {
                                if (!this.is_scanning_after_operand) {
                                    this.scanned_token_type = 34;
                                    this.is_scanning_after_operand = false;
                                    this.next_char();
                                    return;
                                }
                                this.next_char();
                                if ('=' == this.current_char) {
                                    this.scanned_token_type = 15;
                                    this.is_scanning_after_operand = false;
                                    this.next_char();
                                    return;
                                }
                                if ('!' == this.current_char) {
                                    this.scanned_token_type = 13;
                                    this.is_scanning_after_operand = true;
                                    this.next_char();
                                    return;
                                }
                                this.scanned_token_type = 12;
                                this.is_scanning_after_operand = true;
                                return;
                            }
                            else {
                                if ('\uf431' == this.current_char) {
                                    this.scanned_token_type = 14;
                                    this.is_scanning_after_operand = false;
                                    this.next_char();
                                    return;
                                }
                                if ('=' == this.current_char) {
                                    if ('=' == this.next_char()) {
                                        this.scanned_token_type = 14;
                                        this.is_scanning_after_operand = false;
                                        this.next_char();
                                        return;
                                    }
                                    this.scanned_token_type = 36;
                                    this.is_scanning_after_operand = false;
                                    return;
                                }
                                else {
                                    if ('\u2264' == this.current_char) {
                                        this.scanned_token_type = 18;
                                        this.is_scanning_after_operand = false;
                                        this.next_char();
                                        return;
                                    }
                                    if ('<' == this.current_char) {
                                        if ('=' == this.next_char()) {
                                            this.scanned_token_type = 18;
                                            this.is_scanning_after_operand = false;
                                            this.next_char();
                                            return;
                                        }
                                        this.scanned_token_type = 16;
                                        this.is_scanning_after_operand = false;
                                        return;
                                    }
                                    else {
                                        if ('\u2265' == this.current_char) {
                                            this.scanned_token_type = 19;
                                            this.is_scanning_after_operand = false;
                                            this.next_char();
                                            return;
                                        }
                                        if ('>' == this.current_char) {
                                            if ('=' == this.next_char()) {
                                                this.scanned_token_type = 19;
                                                this.is_scanning_after_operand = false;
                                                this.next_char();
                                                return;
                                            }
                                            this.scanned_token_type = 17;
                                            this.is_scanning_after_operand = false;
                                            return;
                                        }
                                        else {
                                            if ('\u2227' == this.current_char) {
                                                this.scanned_token_type = 32;
                                                this.next_char();
                                                this.is_scanning_after_operand = false;
                                                return;
                                            }
                                            if ('&' == this.current_char) {
                                                if ('&' == this.next_char()) {
                                                    this.scanned_token_type = 32;
                                                    this.next_char();
                                                    this.is_scanning_after_operand = false;
                                                    return;
                                                }
                                            }
                                            else {
                                                if ('\u2228' == this.current_char) {
                                                    this.scanned_token_type = 33;
                                                    this.next_char();
                                                    this.is_scanning_after_operand = false;
                                                    return;
                                                }
                                                if ('|' == this.current_char) {
                                                    if ('|' == this.next_char()) {
                                                        this.scanned_token_type = 33;
                                                        this.next_char();
                                                        this.is_scanning_after_operand = false;
                                                        return;
                                                    }
                                                }
                                                else if (';' == this.current_char) {
                                                    this.scanned_token_type = 35;
                                                    this.next_char();
                                                    this.is_scanning_after_operand = false;
                                                    return;
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                this.scan_salt();
            }
        }
        if (this.is_scanning_expression) {
            this.scanned_token_type = 24;
        }
        else {
            this.scanned_token_type = 25;
        }
    }
    
    void scan_salt() {
        int bracket_level = 0;
        while (this.text_index < this.text.length()) {
            if ('\"' == this.current_char) {
                this.next_char();
                while (this.text_index < this.text.length() && '\"' != this.current_char) {
                    if ('\\' == this.current_char) {
                        this.next_char();
                    }
                    this.next_char();
                }
            }
            else if (0 <= Parser.structure_characters.indexOf(this.current_char)) {
                if ('(' == this.current_char || '{' == this.current_char || '[' == this.current_char) {
                    ++bracket_level;
                }
                else if (')' == this.current_char || '}' == this.current_char || ']' == this.current_char) {
                    --bracket_level;
                    if (bracket_level < 0) {
                        break;
                    }
                }
                else if (',' == this.current_char && bracket_level == 0) {
                    break;
                }
            }
            this.next_char();
        }
    }
    
    void scan_right_bracket() {
        this.scan_salt();
        while (this.text_index < this.text.length() && ',' == this.current_char) {
            this.next_char();
            this.scan_salt();
        }
        this.scan_token();
        if (4 != this.scanned_token_type && 2 != this.scanned_token_type && 6 != this.scanned_token_type) {
            this.scanned_token_type = 25;
        }
    }
    
    public boolean scan_animation() {
        this.text_index = -1;
        this.scanned_AnimationDirection = 1;
        this.scanned_Animate_min = 0.0;
        this.scanned_Animate_max = 0.0;
        this.scanned_Animate_step = 1.0;
        this.scanned_Animate_variable = -1;
        this.scanned_frames = new Vector();
        this.scanning_animation_option = false;
        this.scan_token();
        if (21 != this.scanned_token_type) {
            return false;
        }
        if (this.scanned_identifier.equals(Parser.name_Graphics3D)) {
            if (!this.scan_Graphics3D()) {
                return false;
            }
            this.scanned_frames.addElement(this.graphics);
        }
        else if (this.scanned_identifier.equals(Parser.name_ShowAnimation)) {
            this.scan_token();
            if (3 != this.scanned_token_type) {
                return false;
            }
            this.scan_token();
            if (21 == this.scanned_token_type && this.scanned_identifier.equals(Parser.name_Graphics3D)) {
                if (!this.scan_Graphics3D()) {
                    return false;
                }
                this.scanned_frames.addElement(this.graphics);
            }
            else {
                if (5 != this.scanned_token_type) {
                    return false;
                }
                this.scanned_token_type = 0;
                while (0 == this.scanned_token_type) {
                    this.scan_token();
                    if (!this.scan_Graphics3D()) {
                        return false;
                    }
                    this.scanned_frames.addElement(this.graphics);
                    this.scan_token();
                }
                if (6 != this.scanned_token_type) {
                    return false;
                }
            }
            this.scan_token();
            this.scanning_animation_option = true;
            this.init_scan_Graphics3D();
            while (0 == this.scanned_token_type) {
                if (!this.scan_option()) {
                    return false;
                }
                this.scan_token();
            }
            if (4 != this.scanned_token_type) {
                return false;
            }
            this.distribute_options(this.scanned_frames);
        }
        else {
            if (!this.scanned_identifier.equals(Parser.name_Animate)) {
                return false;
            }
            this.scan_token();
            if (3 != this.scanned_token_type) {
                return false;
            }
            this.scan_token();
            if (21 != this.scanned_token_type || !this.scanned_identifier.equals(Parser.name_Graphics3D)) {
                return false;
            }
            if (!this.scan_Graphics3D()) {
                return false;
            }
            this.scanned_frames.addElement(this.graphics);
            this.scan_token();
            if (0 != this.scanned_token_type) {
                return false;
            }
            this.scan_token();
            if (5 != this.scanned_token_type) {
                return false;
            }
            this.scan_token();
            if (21 != this.scanned_token_type) {
                return false;
            }
            this.scanned_Animate_variable = this.evaluator.getVariableIndex(this.scanned_identifier);
            if (0 > this.scanned_Animate_variable) {
                return false;
            }
            if (!this.evaluator.isVariableIndependent(this.scanned_Animate_variable)) {
                return false;
            }
            this.scan_token();
            if (0 != this.scanned_token_type) {
                return false;
            }
            this.scan_token();
            if (22 != this.scanned_token_type) {
                return false;
            }
            this.scanned_Animate_min = this.scanned_number;
            this.scan_token();
            if (0 != this.scanned_token_type) {
                return false;
            }
            this.scan_token();
            if (22 != this.scanned_token_type) {
                return false;
            }
            this.scanned_Animate_max = this.scanned_number;
            this.scan_token();
            if (0 == this.scanned_token_type) {
                this.scan_token();
                if (22 != this.scanned_token_type) {
                    return false;
                }
                this.scanned_Animate_step = this.scanned_number;
                this.scan_token();
            }
            else {
                this.scanned_Animate_step = 1.0;
            }
            if (6 != this.scanned_token_type) {
                return false;
            }
            this.scan_token();
            this.scanning_animation_option = true;
            this.init_scan_Graphics3D();
            while (0 == this.scanned_token_type) {
                if (!this.scan_option()) {
                    return false;
                }
                this.scan_token();
            }
            if (4 != this.scanned_token_type) {
                return false;
            }
            this.distribute_options(this.scanned_frames);
        }
        return true;
    }
    
    public void init_scan_Graphics3D() {
        this.graphics = new Graphics3D();
        this.scanning_EdgeForm = false;
        this.scanning_FaceForm = false;
        this.scanning_SurfaceColor = false;
        this.is_scanned_AmbientLight = false;
        this.is_scanned_Axes = false;
        this.is_scanned_AxesLabel = false;
        this.is_scanned_AxesStyle = false;
        this.is_scanned_AxesEdge = false;
        this.is_scanned_Ticks = false;
        this.is_scanned_PlotLabel = false;
        this.is_scanned_Background = false;
        this.is_scanned_DefaultColor = false;
        this.is_scanned_BoxStyle = false;
        this.is_scanned_Boxed = false;
        this.is_scanned_Lighting = false;
        this.is_scanned_BoxRatios = false;
        this.is_scanned_PlotRange = false;
        this.is_scanned_LightSources = false;
        this.is_scanned_ViewPoint = false;
        this.is_scanned_ViewVertical = false;
        this.is_scanned_TextStyle = false;
        this.is_scanned_AnimationDisplayTime = false;
    }
    
    public void distribute_options(final Vector scanned_frames) {
        for (int frame_index = 0; frame_index < scanned_frames.size(); ++frame_index) {
            final Graphics3D frame = scanned_frames.elementAt(frame_index);
            if (this.is_scanned_AmbientLight) {
                frame.option_AmbientLight = this.graphics.option_AmbientLight;
            }
            if (this.is_scanned_Axes) {
                frame.option_Axes = this.graphics.option_Axes;
            }
            if (this.is_scanned_AxesLabel) {
                frame.option_AxesLabel = this.graphics.option_AxesLabel;
            }
            if (this.is_scanned_AxesStyle) {
                frame.option_AxesStyle = this.graphics.option_AxesStyle;
            }
            if (this.is_scanned_AxesEdge) {
                frame.option_AxesEdge = this.graphics.option_AxesEdge;
            }
            if (this.is_scanned_Ticks) {
                frame.option_Ticks = this.graphics.option_Ticks;
            }
            if (this.is_scanned_PlotLabel) {
                frame.option_PlotLabel = this.graphics.option_PlotLabel;
            }
            if (this.is_scanned_Background) {
                frame.option_Background = this.graphics.option_Background;
            }
            if (this.is_scanned_DefaultColor) {
                frame.option_DefaultColor = this.graphics.option_DefaultColor;
            }
            if (this.is_scanned_BoxStyle) {
                frame.option_BoxStyle = this.graphics.option_BoxStyle;
            }
            if (this.is_scanned_Boxed) {
                frame.option_Boxed = this.graphics.option_Boxed;
            }
            if (this.is_scanned_Lighting) {
                frame.option_Lighting = this.graphics.option_Lighting;
            }
            if (this.is_scanned_BoxRatios) {
                frame.option_BoxRatios = this.graphics.option_BoxRatios;
            }
            if (this.is_scanned_PlotRange) {
                frame.option_PlotRange = this.graphics.option_PlotRange;
            }
            if (this.is_scanned_LightSources) {
                frame.option_LightSources_vectors = this.graphics.option_LightSources_vectors;
                frame.option_LightSources_colors = this.graphics.option_LightSources_colors;
            }
            if (this.is_scanned_ViewPoint) {
                frame.option_ViewPoint = this.graphics.option_ViewPoint;
            }
            if (this.is_scanned_ViewVertical) {
                frame.option_ViewVertical = this.graphics.option_ViewVertical;
            }
            if (this.is_scanned_TextStyle) {
                frame.option_TextStyle_font_url = this.graphics.option_TextStyle_font_url;
                frame.option_TextStyle_font_family = this.graphics.option_TextStyle_font_family;
                frame.option_TextStyle_font_weight = this.graphics.option_TextStyle_font_weight;
                frame.option_TextStyle_font_slant = this.graphics.option_TextStyle_font_slant;
                frame.option_TextStyle_font_size = this.graphics.option_TextStyle_font_size;
                frame.option_TextStyle_font_color = this.graphics.option_TextStyle_font_color;
                frame.option_TextStyle_font_background = this.graphics.option_TextStyle_font_background;
            }
            if (this.is_scanned_AnimationDisplayTime) {
                frame.option_AnimationDisplayTime = this.graphics.option_AnimationDisplayTime;
                frame.option_AnimationDisplayTimeString = this.graphics.option_AnimationDisplayTimeString;
            }
        }
    }
    
    public boolean scan_Graphics3D() {
        this.init_scan_Graphics3D();
        final Primitive3D default_primitive = new Primitive3D();
        if (21 != this.scanned_token_type || !this.scanned_identifier.equals(Parser.name_Graphics3D)) {
            return false;
        }
        this.scan_token();
        if (3 != this.scanned_token_type) {
            return false;
        }
        if (!this.scan_primitive(default_primitive)) {
            return false;
        }
        this.scan_token();
        while (0 == this.scanned_token_type) {
            if (!this.scan_option()) {
                return false;
            }
            this.scan_token();
        }
        if (4 != this.scanned_token_type) {
            return false;
        }
        this.graphics.original_count_primitives = this.graphics.count_primitives;
        return true;
    }
    
    boolean scan_primitive(final Primitive3D default_primitive) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: iconst_0       
        //     2: putfield        Parser.scanned_nothing:Z
        //     5: aload_0         /* this */
        //     6: iconst_0       
        //     7: putfield        Parser.scanned_unidentified:Z
        //    10: aload_0         /* this */
        //    11: invokevirtual   Parser.scan_token:()V
        //    14: iconst_5       
        //    15: aload_0         /* this */
        //    16: getfield        Parser.scanned_token_type:I
        //    19: if_icmpne       162
        //    22: aload_0         /* this */
        //    23: getfield        Parser.scanning_EdgeForm:Z
        //    26: ifne            36
        //    29: aload_0         /* this */
        //    30: getfield        Parser.scanning_FaceForm:Z
        //    33: ifeq            51
        //    36: aload_0         /* this */
        //    37: getfield        Parser.scanning_AxesStyles:Z
        //    40: ifeq            60
        //    43: iconst_1       
        //    44: aload_0         /* this */
        //    45: getfield        Parser.recursion_depth:I
        //    48: if_icmpne       60
        //    51: new             LPrimitive3D;
        //    54: dup            
        //    55: aload_1         /* default_primitive */
        //    56: invokespecial   Primitive3D.<init>:(LPrimitive3D;)V
        //    59: astore_1        /* default_primitive */
        //    60: aload_0         /* this */
        //    61: aload_0         /* this */
        //    62: getfield        Parser.recursion_depth:I
        //    65: iconst_1       
        //    66: iadd           
        //    67: putfield        Parser.recursion_depth:I
        //    70: aload_0         /* this */
        //    71: aload_1         /* default_primitive */
        //    72: invokevirtual   Parser.scan_primitive:(LPrimitive3D;)Z
        //    75: ifeq            93
        //    78: aload_0         /* this */
        //    79: invokevirtual   Parser.scan_token:()V
        //    82: iconst_0       
        //    83: aload_0         /* this */
        //    84: getfield        Parser.scanned_token_type:I
        //    87: if_icmpeq       70
        //    90: goto            93
        //    93: aload_0         /* this */
        //    94: aload_0         /* this */
        //    95: getfield        Parser.recursion_depth:I
        //    98: iconst_1       
        //    99: isub           
        //   100: putfield        Parser.recursion_depth:I
        //   103: bipush          6
        //   105: aload_0         /* this */
        //   106: getfield        Parser.scanned_token_type:I
        //   109: if_icmpeq       114
        //   112: iconst_0       
        //   113: ireturn        
        //   114: aload_0         /* this */
        //   115: getfield        Parser.scanning_AxesStyles:Z
        //   118: ifeq            160
        //   121: iconst_1       
        //   122: aload_0         /* this */
        //   123: getfield        Parser.recursion_depth:I
        //   126: if_icmpne       160
        //   129: aload_0         /* this */
        //   130: getfield        Parser.scanned_AxesStyles:[LPrimitive3D;
        //   133: iconst_0       
        //   134: aload_0         /* this */
        //   135: getfield        Parser.scanned_AxesStyles:[LPrimitive3D;
        //   138: iconst_1       
        //   139: aaload         
        //   140: aastore        
        //   141: aload_0         /* this */
        //   142: getfield        Parser.scanned_AxesStyles:[LPrimitive3D;
        //   145: iconst_1       
        //   146: aload_0         /* this */
        //   147: getfield        Parser.scanned_AxesStyles:[LPrimitive3D;
        //   150: iconst_2       
        //   151: aaload         
        //   152: aastore        
        //   153: aload_0         /* this */
        //   154: getfield        Parser.scanned_AxesStyles:[LPrimitive3D;
        //   157: iconst_2       
        //   158: aload_1         /* default_primitive */
        //   159: aastore        
        //   160: iconst_1       
        //   161: ireturn        
        //   162: bipush          21
        //   164: aload_0         /* this */
        //   165: getfield        Parser.scanned_token_type:I
        //   168: if_icmpeq       178
        //   171: aload_0         /* this */
        //   172: iconst_1       
        //   173: putfield        Parser.scanned_nothing:Z
        //   176: iconst_0       
        //   177: ireturn        
        //   178: aload_0         /* this */
        //   179: getfield        Parser.scanning_EdgeForm:Z
        //   182: ifne            816
        //   185: aload_0         /* this */
        //   186: getfield        Parser.scanning_FaceForm:Z
        //   189: ifne            816
        //   192: aload_0         /* this */
        //   193: getfield        Parser.scanning_SurfaceColor:Z
        //   196: ifne            816
        //   199: aconst_null    
        //   200: aload_0         /* this */
        //   201: getfield        Parser.evaluator:LEvaluator;
        //   204: if_acmpeq       816
        //   207: aload_0         /* this */
        //   208: getfield        Parser.scanned_identifier:Ljava/lang/String;
        //   211: getstatic       Parser.name_If:Ljava/lang/String;
        //   214: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //   217: ifeq            816
        //   220: iconst_m1      
        //   221: istore          if_clause_min_index
        //   223: iconst_m1      
        //   224: istore          if_clause_max_index
        //   226: iconst_m1      
        //   227: istore          else_clause_min_index
        //   229: iconst_m1      
        //   230: istore          else_clause_max_index
        //   232: iconst_m1      
        //   233: istore          default_clause_min_index
        //   235: iconst_m1      
        //   236: istore          default_clause_max_index
        //   238: aload_0         /* this */
        //   239: invokevirtual   Parser.scan_token:()V
        //   242: iconst_3       
        //   243: aload_0         /* this */
        //   244: getfield        Parser.scanned_token_type:I
        //   247: if_icmpeq       252
        //   250: iconst_0       
        //   251: ireturn        
        //   252: aload_0         /* this */
        //   253: iconst_1       
        //   254: putfield        Parser.is_scanning_expression:Z
        //   257: aload_0         /* this */
        //   258: iconst_0       
        //   259: putfield        Parser.is_scanning_after_operand:Z
        //   262: aload_0         /* this */
        //   263: invokevirtual   Parser.scan_token:()V
        //   266: aload_0         /* this */
        //   267: iconst_1       
        //   268: invokevirtual   Parser.scan_expression:(Z)Z
        //   271: ifne            276
        //   274: iconst_0       
        //   275: ireturn        
        //   276: aload_0         /* this */
        //   277: iconst_0       
        //   278: putfield        Parser.is_scanning_expression:Z
        //   281: iconst_0       
        //   282: aload_0         /* this */
        //   283: getfield        Parser.scanned_token_type:I
        //   286: if_icmpeq       291
        //   289: iconst_0       
        //   290: ireturn        
        //   291: aload_0         /* this */
        //   292: getfield        Parser.scanned_expression:I
        //   295: istore_2        /* if_condition_expression */
        //   296: aload_0         /* this */
        //   297: getfield        Parser.scanned_number:D
        //   300: dstore          if_condition_number
        //   302: aload_0         /* this */
        //   303: getfield        Parser.is_scanned_expression_numeric:Z
        //   306: ifeq            314
        //   309: iconst_1       
        //   310: istore_3        /* is_if_condition_numeric */
        //   311: goto            316
        //   314: iconst_0       
        //   315: istore_3        /* is_if_condition_numeric */
        //   316: aload_0         /* this */
        //   317: aload_0         /* this */
        //   318: getfield        Parser.if_recursion_depth:I
        //   321: iconst_1       
        //   322: iadd           
        //   323: putfield        Parser.if_recursion_depth:I
        //   326: aload_0         /* this */
        //   327: getfield        Parser.graphics:LGraphics3D;
        //   330: getfield        Graphics3D.count_primitives:I
        //   333: istore          if_clause_min_index
        //   335: aload_0         /* this */
        //   336: aload_0         /* this */
        //   337: getfield        Parser.recursion_depth:I
        //   340: iconst_1       
        //   341: iadd           
        //   342: putfield        Parser.recursion_depth:I
        //   345: aload_0         /* this */
        //   346: new             LPrimitive3D;
        //   349: dup            
        //   350: aload_1         /* default_primitive */
        //   351: invokespecial   Primitive3D.<init>:(LPrimitive3D;)V
        //   354: invokevirtual   Parser.scan_primitive:(LPrimitive3D;)Z
        //   357: ifne            382
        //   360: aload_0         /* this */
        //   361: aload_0         /* this */
        //   362: getfield        Parser.recursion_depth:I
        //   365: iconst_1       
        //   366: isub           
        //   367: putfield        Parser.recursion_depth:I
        //   370: aload_0         /* this */
        //   371: aload_0         /* this */
        //   372: getfield        Parser.if_recursion_depth:I
        //   375: iconst_1       
        //   376: isub           
        //   377: putfield        Parser.if_recursion_depth:I
        //   380: iconst_0       
        //   381: ireturn        
        //   382: aload_0         /* this */
        //   383: aload_0         /* this */
        //   384: getfield        Parser.recursion_depth:I
        //   387: iconst_1       
        //   388: isub           
        //   389: putfield        Parser.recursion_depth:I
        //   392: aload_0         /* this */
        //   393: getfield        Parser.graphics:LGraphics3D;
        //   396: getfield        Graphics3D.count_primitives:I
        //   399: iconst_1       
        //   400: isub           
        //   401: istore          if_clause_max_index
        //   403: aload_0         /* this */
        //   404: invokevirtual   Parser.scan_token:()V
        //   407: iconst_0       
        //   408: aload_0         /* this */
        //   409: getfield        Parser.scanned_token_type:I
        //   412: if_icmpne       585
        //   415: aload_0         /* this */
        //   416: getfield        Parser.graphics:LGraphics3D;
        //   419: getfield        Graphics3D.count_primitives:I
        //   422: istore          else_clause_min_index
        //   424: aload_0         /* this */
        //   425: aload_0         /* this */
        //   426: getfield        Parser.recursion_depth:I
        //   429: iconst_1       
        //   430: iadd           
        //   431: putfield        Parser.recursion_depth:I
        //   434: aload_0         /* this */
        //   435: new             LPrimitive3D;
        //   438: dup            
        //   439: aload_1         /* default_primitive */
        //   440: invokespecial   Primitive3D.<init>:(LPrimitive3D;)V
        //   443: invokevirtual   Parser.scan_primitive:(LPrimitive3D;)Z
        //   446: ifne            471
        //   449: aload_0         /* this */
        //   450: aload_0         /* this */
        //   451: getfield        Parser.recursion_depth:I
        //   454: iconst_1       
        //   455: isub           
        //   456: putfield        Parser.recursion_depth:I
        //   459: aload_0         /* this */
        //   460: aload_0         /* this */
        //   461: getfield        Parser.if_recursion_depth:I
        //   464: iconst_1       
        //   465: isub           
        //   466: putfield        Parser.if_recursion_depth:I
        //   469: iconst_0       
        //   470: ireturn        
        //   471: aload_0         /* this */
        //   472: aload_0         /* this */
        //   473: getfield        Parser.recursion_depth:I
        //   476: iconst_1       
        //   477: isub           
        //   478: putfield        Parser.recursion_depth:I
        //   481: aload_0         /* this */
        //   482: getfield        Parser.graphics:LGraphics3D;
        //   485: getfield        Graphics3D.count_primitives:I
        //   488: iconst_1       
        //   489: isub           
        //   490: istore          else_clause_max_index
        //   492: aload_0         /* this */
        //   493: invokevirtual   Parser.scan_token:()V
        //   496: iconst_0       
        //   497: aload_0         /* this */
        //   498: getfield        Parser.scanned_token_type:I
        //   501: if_icmpne       585
        //   504: aload_0         /* this */
        //   505: getfield        Parser.graphics:LGraphics3D;
        //   508: getfield        Graphics3D.count_primitives:I
        //   511: istore          default_clause_min_index
        //   513: aload_0         /* this */
        //   514: aload_0         /* this */
        //   515: getfield        Parser.recursion_depth:I
        //   518: iconst_1       
        //   519: iadd           
        //   520: putfield        Parser.recursion_depth:I
        //   523: aload_0         /* this */
        //   524: new             LPrimitive3D;
        //   527: dup            
        //   528: aload_1         /* default_primitive */
        //   529: invokespecial   Primitive3D.<init>:(LPrimitive3D;)V
        //   532: invokevirtual   Parser.scan_primitive:(LPrimitive3D;)Z
        //   535: ifne            560
        //   538: aload_0         /* this */
        //   539: aload_0         /* this */
        //   540: getfield        Parser.recursion_depth:I
        //   543: iconst_1       
        //   544: isub           
        //   545: putfield        Parser.recursion_depth:I
        //   548: aload_0         /* this */
        //   549: aload_0         /* this */
        //   550: getfield        Parser.if_recursion_depth:I
        //   553: iconst_1       
        //   554: isub           
        //   555: putfield        Parser.if_recursion_depth:I
        //   558: iconst_0       
        //   559: ireturn        
        //   560: aload_0         /* this */
        //   561: aload_0         /* this */
        //   562: getfield        Parser.recursion_depth:I
        //   565: iconst_1       
        //   566: isub           
        //   567: putfield        Parser.recursion_depth:I
        //   570: aload_0         /* this */
        //   571: getfield        Parser.graphics:LGraphics3D;
        //   574: getfield        Graphics3D.count_primitives:I
        //   577: iconst_1       
        //   578: isub           
        //   579: istore          default_clause_max_index
        //   581: aload_0         /* this */
        //   582: invokevirtual   Parser.scan_token:()V
        //   585: iconst_4       
        //   586: aload_0         /* this */
        //   587: getfield        Parser.scanned_token_type:I
        //   590: if_icmpeq       605
        //   593: aload_0         /* this */
        //   594: aload_0         /* this */
        //   595: getfield        Parser.if_recursion_depth:I
        //   598: iconst_1       
        //   599: isub           
        //   600: putfield        Parser.if_recursion_depth:I
        //   603: iconst_0       
        //   604: ireturn        
        //   605: aload_0         /* this */
        //   606: aload_0         /* this */
        //   607: getfield        Parser.if_recursion_depth:I
        //   610: iconst_1       
        //   611: isub           
        //   612: putfield        Parser.if_recursion_depth:I
        //   615: aload_0         /* this */
        //   616: getfield        Parser.if_recursion_depth:I
        //   619: bipush          32
        //   621: if_icmpge       635
        //   624: iconst_1       
        //   625: aload_0         /* this */
        //   626: getfield        Parser.if_recursion_depth:I
        //   629: ishl           
        //   630: istore          inactive_flag
        //   632: goto            638
        //   635: iconst_0       
        //   636: istore          inactive_flag
        //   638: dconst_1       
        //   639: dload           if_condition_number
        //   641: dcmpl          
        //   642: ifne            690
        //   645: aload_0         /* this */
        //   646: getfield        Parser.graphics:LGraphics3D;
        //   649: iload           if_clause_min_index
        //   651: iload           if_clause_max_index
        //   653: iload           inactive_flag
        //   655: invokevirtual   Graphics3D.clearInactiveFlags:(III)Z
        //   658: pop            
        //   659: aload_0         /* this */
        //   660: getfield        Parser.graphics:LGraphics3D;
        //   663: iload           else_clause_min_index
        //   665: iload           else_clause_max_index
        //   667: iload           inactive_flag
        //   669: invokevirtual   Graphics3D.setInactiveFlags:(III)Z
        //   672: pop            
        //   673: aload_0         /* this */
        //   674: getfield        Parser.graphics:LGraphics3D;
        //   677: iload           default_clause_min_index
        //   679: iload           default_clause_max_index
        //   681: iload           inactive_flag
        //   683: invokevirtual   Graphics3D.setInactiveFlags:(III)Z
        //   686: pop            
        //   687: goto            784
        //   690: dconst_0       
        //   691: dload           if_condition_number
        //   693: dcmpl          
        //   694: ifne            742
        //   697: aload_0         /* this */
        //   698: getfield        Parser.graphics:LGraphics3D;
        //   701: iload           if_clause_min_index
        //   703: iload           if_clause_max_index
        //   705: iload           inactive_flag
        //   707: invokevirtual   Graphics3D.setInactiveFlags:(III)Z
        //   710: pop            
        //   711: aload_0         /* this */
        //   712: getfield        Parser.graphics:LGraphics3D;
        //   715: iload           else_clause_min_index
        //   717: iload           else_clause_max_index
        //   719: iload           inactive_flag
        //   721: invokevirtual   Graphics3D.clearInactiveFlags:(III)Z
        //   724: pop            
        //   725: aload_0         /* this */
        //   726: getfield        Parser.graphics:LGraphics3D;
        //   729: iload           default_clause_min_index
        //   731: iload           default_clause_max_index
        //   733: iload           inactive_flag
        //   735: invokevirtual   Graphics3D.setInactiveFlags:(III)Z
        //   738: pop            
        //   739: goto            784
        //   742: aload_0         /* this */
        //   743: getfield        Parser.graphics:LGraphics3D;
        //   746: iload           if_clause_min_index
        //   748: iload           if_clause_max_index
        //   750: iload           inactive_flag
        //   752: invokevirtual   Graphics3D.setInactiveFlags:(III)Z
        //   755: pop            
        //   756: aload_0         /* this */
        //   757: getfield        Parser.graphics:LGraphics3D;
        //   760: iload           else_clause_min_index
        //   762: iload           else_clause_max_index
        //   764: iload           inactive_flag
        //   766: invokevirtual   Graphics3D.setInactiveFlags:(III)Z
        //   769: pop            
        //   770: aload_0         /* this */
        //   771: getfield        Parser.graphics:LGraphics3D;
        //   774: iload           default_clause_min_index
        //   776: iload           default_clause_max_index
        //   778: iload           inactive_flag
        //   780: invokevirtual   Graphics3D.clearInactiveFlags:(III)Z
        //   783: pop            
        //   784: iload_3         /* is_if_condition_numeric */
        //   785: ifne            814
        //   788: aload_0         /* this */
        //   789: getfield        Parser.evaluator:LEvaluator;
        //   792: iload_2         /* if_condition_expression */
        //   793: aload_0         /* this */
        //   794: getfield        Parser.graphics:LGraphics3D;
        //   797: iload           inactive_flag
        //   799: iload           if_clause_min_index
        //   801: iload           if_clause_max_index
        //   803: iload           else_clause_min_index
        //   805: iload           else_clause_max_index
        //   807: iload           default_clause_min_index
        //   809: iload           default_clause_max_index
        //   811: invokevirtual   Evaluator.setExpressionIfCondition:(ILGraphics3D;IIIIIII)V
        //   814: iconst_1       
        //   815: ireturn        
        //   816: aload_0         /* this */
        //   817: getfield        Parser.scanning_EdgeForm:Z
        //   820: ifne            925
        //   823: aload_0         /* this */
        //   824: getfield        Parser.scanning_FaceForm:Z
        //   827: ifne            925
        //   830: aload_0         /* this */
        //   831: getfield        Parser.scanning_SurfaceColor:Z
        //   834: ifne            925
        //   837: aload_0         /* this */
        //   838: getfield        Parser.scanned_identifier:Ljava/lang/String;
        //   841: getstatic       Parser.name_EdgeForm:Ljava/lang/String;
        //   844: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //   847: ifeq            925
        //   850: aload_0         /* this */
        //   851: invokevirtual   Parser.scan_token:()V
        //   854: iconst_3       
        //   855: aload_0         /* this */
        //   856: getfield        Parser.scanned_token_type:I
        //   859: if_icmpeq       864
        //   862: iconst_0       
        //   863: ireturn        
        //   864: aload_0         /* this */
        //   865: iconst_1       
        //   866: putfield        Parser.scanning_EdgeForm:Z
        //   869: aload_1         /* default_primitive */
        //   870: iconst_0       
        //   871: putfield        Primitive3D.is_outlined:Z
        //   874: aload_0         /* this */
        //   875: aload_1         /* default_primitive */
        //   876: invokevirtual   Parser.scan_primitive:(LPrimitive3D;)Z
        //   879: ifeq            903
        //   882: aload_0         /* this */
        //   883: iconst_0       
        //   884: putfield        Parser.scanning_EdgeForm:Z
        //   887: aload_0         /* this */
        //   888: invokevirtual   Parser.scan_token:()V
        //   891: iconst_4       
        //   892: aload_0         /* this */
        //   893: getfield        Parser.scanned_token_type:I
        //   896: if_icmpne       901
        //   899: iconst_1       
        //   900: ireturn        
        //   901: iconst_0       
        //   902: ireturn        
        //   903: aload_0         /* this */
        //   904: getfield        Parser.scanned_nothing:Z
        //   907: ifeq            1245
        //   910: iconst_4       
        //   911: aload_0         /* this */
        //   912: getfield        Parser.scanned_token_type:I
        //   915: if_icmpne       1245
        //   918: aload_0         /* this */
        //   919: iconst_0       
        //   920: putfield        Parser.scanning_EdgeForm:Z
        //   923: iconst_1       
        //   924: ireturn        
        //   925: aload_0         /* this */
        //   926: getfield        Parser.scanning_EdgeForm:Z
        //   929: ifne            1061
        //   932: aload_0         /* this */
        //   933: getfield        Parser.scanning_FaceForm:Z
        //   936: ifne            1061
        //   939: aload_0         /* this */
        //   940: getfield        Parser.scanning_SurfaceColor:Z
        //   943: ifne            1061
        //   946: aload_0         /* this */
        //   947: getfield        Parser.scanned_identifier:Ljava/lang/String;
        //   950: getstatic       Parser.name_FaceForm:Ljava/lang/String;
        //   953: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //   956: ifeq            1061
        //   959: aload_0         /* this */
        //   960: invokevirtual   Parser.scan_token:()V
        //   963: iconst_3       
        //   964: aload_0         /* this */
        //   965: getfield        Parser.scanned_token_type:I
        //   968: if_icmpeq       973
        //   971: iconst_0       
        //   972: ireturn        
        //   973: aload_0         /* this */
        //   974: iconst_1       
        //   975: putfield        Parser.scanning_FaceForm:Z
        //   978: aload_0         /* this */
        //   979: iconst_0       
        //   980: putfield        Parser.scanning_FaceForm_back:Z
        //   983: aload_0         /* this */
        //   984: aload_1         /* default_primitive */
        //   985: invokevirtual   Parser.scan_primitive:(LPrimitive3D;)Z
        //   988: ifne            998
        //   991: aload_0         /* this */
        //   992: iconst_0       
        //   993: putfield        Parser.scanning_FaceForm:Z
        //   996: iconst_0       
        //   997: ireturn        
        //   998: aload_0         /* this */
        //   999: invokevirtual   Parser.scan_token:()V
        //  1002: iconst_0       
        //  1003: aload_0         /* this */
        //  1004: getfield        Parser.scanned_token_type:I
        //  1007: if_icmpne       1044
        //  1010: aload_0         /* this */
        //  1011: iconst_1       
        //  1012: putfield        Parser.scanning_FaceForm_back:Z
        //  1015: aload_0         /* this */
        //  1016: aload_1         /* default_primitive */
        //  1017: invokevirtual   Parser.scan_primitive:(LPrimitive3D;)Z
        //  1020: ifne            1035
        //  1023: aload_0         /* this */
        //  1024: iconst_0       
        //  1025: putfield        Parser.scanning_FaceForm:Z
        //  1028: aload_0         /* this */
        //  1029: iconst_0       
        //  1030: putfield        Parser.scanning_FaceForm_back:Z
        //  1033: iconst_0       
        //  1034: ireturn        
        //  1035: aload_0         /* this */
        //  1036: iconst_0       
        //  1037: putfield        Parser.scanning_FaceForm_back:Z
        //  1040: aload_0         /* this */
        //  1041: invokevirtual   Parser.scan_token:()V
        //  1044: aload_0         /* this */
        //  1045: iconst_0       
        //  1046: putfield        Parser.scanning_FaceForm:Z
        //  1049: iconst_4       
        //  1050: aload_0         /* this */
        //  1051: getfield        Parser.scanned_token_type:I
        //  1054: if_icmpeq       1059
        //  1057: iconst_0       
        //  1058: ireturn        
        //  1059: iconst_1       
        //  1060: ireturn        
        //  1061: aload_0         /* this */
        //  1062: getfield        Parser.scanning_EdgeForm:Z
        //  1065: ifne            1245
        //  1068: aload_0         /* this */
        //  1069: getfield        Parser.scanning_SurfaceColor:Z
        //  1072: ifne            1245
        //  1075: aload_0         /* this */
        //  1076: getfield        Parser.scanned_identifier:Ljava/lang/String;
        //  1079: getstatic       Parser.name_SurfaceColor:Ljava/lang/String;
        //  1082: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //  1085: ifeq            1245
        //  1088: aload_0         /* this */
        //  1089: invokevirtual   Parser.scan_token:()V
        //  1092: iconst_3       
        //  1093: aload_0         /* this */
        //  1094: getfield        Parser.scanned_token_type:I
        //  1097: if_icmpeq       1102
        //  1100: iconst_0       
        //  1101: ireturn        
        //  1102: aload_0         /* this */
        //  1103: iconst_1       
        //  1104: putfield        Parser.scanning_SurfaceColor:Z
        //  1107: aload_0         /* this */
        //  1108: iconst_0       
        //  1109: putfield        Parser.scanning_SurfaceColor_specular:Z
        //  1112: aload_0         /* this */
        //  1113: aload_1         /* default_primitive */
        //  1114: invokevirtual   Parser.scan_primitive:(LPrimitive3D;)Z
        //  1117: ifne            1127
        //  1120: aload_0         /* this */
        //  1121: iconst_0       
        //  1122: putfield        Parser.scanning_SurfaceColor:Z
        //  1125: iconst_0       
        //  1126: ireturn        
        //  1127: aload_0         /* this */
        //  1128: invokevirtual   Parser.scan_token:()V
        //  1131: iconst_0       
        //  1132: aload_0         /* this */
        //  1133: getfield        Parser.scanned_token_type:I
        //  1136: if_icmpne       1228
        //  1139: aload_0         /* this */
        //  1140: iconst_1       
        //  1141: putfield        Parser.scanning_SurfaceColor_specular:Z
        //  1144: aload_0         /* this */
        //  1145: aload_1         /* default_primitive */
        //  1146: invokevirtual   Parser.scan_primitive:(LPrimitive3D;)Z
        //  1149: ifne            1164
        //  1152: aload_0         /* this */
        //  1153: iconst_0       
        //  1154: putfield        Parser.scanning_SurfaceColor:Z
        //  1157: aload_0         /* this */
        //  1158: iconst_0       
        //  1159: putfield        Parser.scanning_SurfaceColor_specular:Z
        //  1162: iconst_0       
        //  1163: ireturn        
        //  1164: aload_0         /* this */
        //  1165: iconst_0       
        //  1166: putfield        Parser.scanning_SurfaceColor:Z
        //  1169: aload_0         /* this */
        //  1170: iconst_0       
        //  1171: putfield        Parser.scanning_SurfaceColor_specular:Z
        //  1174: aload_0         /* this */
        //  1175: invokevirtual   Parser.scan_token:()V
        //  1178: iconst_0       
        //  1179: aload_0         /* this */
        //  1180: getfield        Parser.scanned_token_type:I
        //  1183: if_icmpne       1228
        //  1186: aload_0         /* this */
        //  1187: invokevirtual   Parser.scan_token:()V
        //  1190: bipush          22
        //  1192: aload_0         /* this */
        //  1193: getfield        Parser.scanned_token_type:I
        //  1196: if_icmpeq       1201
        //  1199: iconst_0       
        //  1200: ireturn        
        //  1201: aload_0         /* this */
        //  1202: getfield        Parser.scanning_FaceForm_back:Z
        //  1205: ifne            1216
        //  1208: aload_1         /* default_primitive */
        //  1209: aload_0         /* this */
        //  1210: getfield        Parser.scanned_number:D
        //  1213: putfield        Primitive3D.front_specular_exponent:D
        //  1216: aload_1         /* default_primitive */
        //  1217: aload_0         /* this */
        //  1218: getfield        Parser.scanned_number:D
        //  1221: putfield        Primitive3D.back_specular_exponent:D
        //  1224: aload_0         /* this */
        //  1225: invokevirtual   Parser.scan_token:()V
        //  1228: aload_0         /* this */
        //  1229: iconst_0       
        //  1230: putfield        Parser.scanning_SurfaceColor:Z
        //  1233: iconst_4       
        //  1234: aload_0         /* this */
        //  1235: getfield        Parser.scanned_token_type:I
        //  1238: if_icmpeq       1243
        //  1241: iconst_0       
        //  1242: ireturn        
        //  1243: iconst_1       
        //  1244: ireturn        
        //  1245: aload_0         /* this */
        //  1246: invokevirtual   Parser.scan_color:()Z
        //  1249: ifne            1254
        //  1252: iconst_0       
        //  1253: ireturn        
        //  1254: aconst_null    
        //  1255: aload_0         /* this */
        //  1256: getfield        Parser.scanned_color:Ljava/awt/Color;
        //  1259: if_acmpeq       1424
        //  1262: aload_0         /* this */
        //  1263: getfield        Parser.scanning_SurfaceColor:Z
        //  1266: ifeq            1302
        //  1269: aload_0         /* this */
        //  1270: getfield        Parser.scanning_SurfaceColor_specular:Z
        //  1273: ifne            1302
        //  1276: aload_0         /* this */
        //  1277: getfield        Parser.scanning_FaceForm_back:Z
        //  1280: ifne            1291
        //  1283: aload_1         /* default_primitive */
        //  1284: aload_0         /* this */
        //  1285: getfield        Parser.scanned_color:Ljava/awt/Color;
        //  1288: putfield        Primitive3D.front_diffuse_color:Ljava/awt/Color;
        //  1291: aload_1         /* default_primitive */
        //  1292: aload_0         /* this */
        //  1293: getfield        Parser.scanned_color:Ljava/awt/Color;
        //  1296: putfield        Primitive3D.back_diffuse_color:Ljava/awt/Color;
        //  1299: goto            1422
        //  1302: aload_0         /* this */
        //  1303: getfield        Parser.scanning_SurfaceColor:Z
        //  1306: ifeq            1342
        //  1309: aload_0         /* this */
        //  1310: getfield        Parser.scanning_SurfaceColor_specular:Z
        //  1313: ifeq            1342
        //  1316: aload_0         /* this */
        //  1317: getfield        Parser.scanning_FaceForm_back:Z
        //  1320: ifne            1331
        //  1323: aload_1         /* default_primitive */
        //  1324: aload_0         /* this */
        //  1325: getfield        Parser.scanned_color:Ljava/awt/Color;
        //  1328: putfield        Primitive3D.front_specular_color:Ljava/awt/Color;
        //  1331: aload_1         /* default_primitive */
        //  1332: aload_0         /* this */
        //  1333: getfield        Parser.scanned_color:Ljava/awt/Color;
        //  1336: putfield        Primitive3D.back_specular_color:Ljava/awt/Color;
        //  1339: goto            1422
        //  1342: aload_0         /* this */
        //  1343: getfield        Parser.scanning_FaceForm:Z
        //  1346: ifeq            1375
        //  1349: aload_0         /* this */
        //  1350: getfield        Parser.scanning_FaceForm_back:Z
        //  1353: ifne            1364
        //  1356: aload_1         /* default_primitive */
        //  1357: aload_0         /* this */
        //  1358: getfield        Parser.scanned_color:Ljava/awt/Color;
        //  1361: putfield        Primitive3D.front_face_color:Ljava/awt/Color;
        //  1364: aload_1         /* default_primitive */
        //  1365: aload_0         /* this */
        //  1366: getfield        Parser.scanned_color:Ljava/awt/Color;
        //  1369: putfield        Primitive3D.back_face_color:Ljava/awt/Color;
        //  1372: goto            1422
        //  1375: aload_0         /* this */
        //  1376: getfield        Parser.scanning_EdgeForm:Z
        //  1379: ifeq            1398
        //  1382: aload_1         /* default_primitive */
        //  1383: aload_0         /* this */
        //  1384: getfield        Parser.scanned_color:Ljava/awt/Color;
        //  1387: putfield        Primitive3D.edge_color:Ljava/awt/Color;
        //  1390: aload_1         /* default_primitive */
        //  1391: iconst_1       
        //  1392: putfield        Primitive3D.is_outlined:Z
        //  1395: goto            1422
        //  1398: aload_1         /* default_primitive */
        //  1399: aload_0         /* this */
        //  1400: getfield        Parser.scanned_color:Ljava/awt/Color;
        //  1403: putfield        Primitive3D.standard_color:Ljava/awt/Color;
        //  1406: aload_1         /* default_primitive */
        //  1407: aload_0         /* this */
        //  1408: getfield        Parser.scanned_color:Ljava/awt/Color;
        //  1411: putfield        Primitive3D.front_face_color:Ljava/awt/Color;
        //  1414: aload_1         /* default_primitive */
        //  1415: aload_0         /* this */
        //  1416: getfield        Parser.scanned_color:Ljava/awt/Color;
        //  1419: putfield        Primitive3D.back_face_color:Ljava/awt/Color;
        //  1422: iconst_1       
        //  1423: ireturn        
        //  1424: aload_0         /* this */
        //  1425: getfield        Parser.scanning_SurfaceColor:Z
        //  1428: ifne            1543
        //  1431: aload_0         /* this */
        //  1432: getfield        Parser.scanning_EdgeForm:Z
        //  1435: ifne            1543
        //  1438: aload_0         /* this */
        //  1439: getfield        Parser.scanning_FaceForm:Z
        //  1442: ifne            1543
        //  1445: aload_0         /* this */
        //  1446: getfield        Parser.scanned_identifier:Ljava/lang/String;
        //  1449: getstatic       Parser.name_PointSize:Ljava/lang/String;
        //  1452: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //  1455: ifeq            1494
        //  1458: aload_0         /* this */
        //  1459: iconst_0       
        //  1460: iconst_0       
        //  1461: invokevirtual   Parser.scan_numbers:(ZZ)Z
        //  1464: ifeq            1475
        //  1467: iconst_1       
        //  1468: aload_0         /* this */
        //  1469: getfield        Parser.count_scanned_numbers:I
        //  1472: if_icmpeq       1477
        //  1475: iconst_0       
        //  1476: ireturn        
        //  1477: aload_1         /* default_primitive */
        //  1478: aload_0         /* this */
        //  1479: getfield        Parser.scanned_numbers:[D
        //  1482: iconst_0       
        //  1483: daload         
        //  1484: putfield        Primitive3D.original_point_size:D
        //  1487: aload_1         /* default_primitive */
        //  1488: iconst_0       
        //  1489: putfield        Primitive3D.is_absolute_point_size:Z
        //  1492: iconst_1       
        //  1493: ireturn        
        //  1494: aload_0         /* this */
        //  1495: getfield        Parser.scanned_identifier:Ljava/lang/String;
        //  1498: getstatic       Parser.name_AbsolutePointSize:Ljava/lang/String;
        //  1501: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //  1504: ifeq            1543
        //  1507: aload_0         /* this */
        //  1508: iconst_0       
        //  1509: iconst_0       
        //  1510: invokevirtual   Parser.scan_numbers:(ZZ)Z
        //  1513: ifeq            1524
        //  1516: iconst_1       
        //  1517: aload_0         /* this */
        //  1518: getfield        Parser.count_scanned_numbers:I
        //  1521: if_icmpeq       1526
        //  1524: iconst_0       
        //  1525: ireturn        
        //  1526: aload_1         /* default_primitive */
        //  1527: aload_0         /* this */
        //  1528: getfield        Parser.scanned_numbers:[D
        //  1531: iconst_0       
        //  1532: daload         
        //  1533: putfield        Primitive3D.original_point_size:D
        //  1536: aload_1         /* default_primitive */
        //  1537: iconst_1       
        //  1538: putfield        Primitive3D.is_absolute_point_size:Z
        //  1541: iconst_1       
        //  1542: ireturn        
        //  1543: aload_0         /* this */
        //  1544: getfield        Parser.scanning_SurfaceColor:Z
        //  1547: ifne            1715
        //  1550: aload_0         /* this */
        //  1551: getfield        Parser.scanning_FaceForm:Z
        //  1554: ifne            1715
        //  1557: aload_0         /* this */
        //  1558: getfield        Parser.scanned_identifier:Ljava/lang/String;
        //  1561: getstatic       Parser.name_Thickness:Ljava/lang/String;
        //  1564: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //  1567: ifeq            1636
        //  1570: aload_0         /* this */
        //  1571: iconst_0       
        //  1572: iconst_0       
        //  1573: invokevirtual   Parser.scan_numbers:(ZZ)Z
        //  1576: ifeq            1587
        //  1579: iconst_1       
        //  1580: aload_0         /* this */
        //  1581: getfield        Parser.count_scanned_numbers:I
        //  1584: if_icmpeq       1589
        //  1587: iconst_0       
        //  1588: ireturn        
        //  1589: aload_0         /* this */
        //  1590: getfield        Parser.scanning_EdgeForm:Z
        //  1593: ifeq            1619
        //  1596: aload_1         /* default_primitive */
        //  1597: iconst_1       
        //  1598: putfield        Primitive3D.is_outlined:Z
        //  1601: aload_1         /* default_primitive */
        //  1602: aload_0         /* this */
        //  1603: getfield        Parser.scanned_numbers:[D
        //  1606: iconst_0       
        //  1607: daload         
        //  1608: putfield        Primitive3D.original_edge_thickness:D
        //  1611: aload_1         /* default_primitive */
        //  1612: iconst_0       
        //  1613: putfield        Primitive3D.is_absolute_edge_thickness:Z
        //  1616: goto            1634
        //  1619: aload_1         /* default_primitive */
        //  1620: aload_0         /* this */
        //  1621: getfield        Parser.scanned_numbers:[D
        //  1624: iconst_0       
        //  1625: daload         
        //  1626: putfield        Primitive3D.original_thickness:D
        //  1629: aload_1         /* default_primitive */
        //  1630: iconst_0       
        //  1631: putfield        Primitive3D.is_absolute_thickness:Z
        //  1634: iconst_1       
        //  1635: ireturn        
        //  1636: aload_0         /* this */
        //  1637: getfield        Parser.scanned_identifier:Ljava/lang/String;
        //  1640: getstatic       Parser.name_AbsoluteThickness:Ljava/lang/String;
        //  1643: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //  1646: ifeq            1715
        //  1649: aload_0         /* this */
        //  1650: iconst_0       
        //  1651: iconst_0       
        //  1652: invokevirtual   Parser.scan_numbers:(ZZ)Z
        //  1655: ifeq            1666
        //  1658: iconst_1       
        //  1659: aload_0         /* this */
        //  1660: getfield        Parser.count_scanned_numbers:I
        //  1663: if_icmpeq       1668
        //  1666: iconst_0       
        //  1667: ireturn        
        //  1668: aload_0         /* this */
        //  1669: getfield        Parser.scanning_EdgeForm:Z
        //  1672: ifeq            1698
        //  1675: aload_1         /* default_primitive */
        //  1676: iconst_1       
        //  1677: putfield        Primitive3D.is_outlined:Z
        //  1680: aload_1         /* default_primitive */
        //  1681: aload_0         /* this */
        //  1682: getfield        Parser.scanned_numbers:[D
        //  1685: iconst_0       
        //  1686: daload         
        //  1687: putfield        Primitive3D.original_edge_thickness:D
        //  1690: aload_1         /* default_primitive */
        //  1691: iconst_1       
        //  1692: putfield        Primitive3D.is_absolute_edge_thickness:Z
        //  1695: goto            1713
        //  1698: aload_1         /* default_primitive */
        //  1699: aload_0         /* this */
        //  1700: getfield        Parser.scanned_numbers:[D
        //  1703: iconst_0       
        //  1704: daload         
        //  1705: putfield        Primitive3D.original_thickness:D
        //  1708: aload_1         /* default_primitive */
        //  1709: iconst_1       
        //  1710: putfield        Primitive3D.is_absolute_thickness:Z
        //  1713: iconst_1       
        //  1714: ireturn        
        //  1715: aload_0         /* this */
        //  1716: getfield        Parser.scanning_EdgeForm:Z
        //  1719: ifne            3130
        //  1722: aload_0         /* this */
        //  1723: getfield        Parser.scanning_FaceForm:Z
        //  1726: ifne            3130
        //  1729: aload_0         /* this */
        //  1730: getfield        Parser.scanning_SurfaceColor:Z
        //  1733: ifne            3130
        //  1736: aload_0         /* this */
        //  1737: getfield        Parser.scanned_identifier:Ljava/lang/String;
        //  1740: getstatic       Parser.name_Point:Ljava/lang/String;
        //  1743: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //  1746: ifeq            1852
        //  1749: aload_0         /* this */
        //  1750: invokevirtual   Parser.scan_token:()V
        //  1753: iconst_3       
        //  1754: aload_0         /* this */
        //  1755: getfield        Parser.scanned_token_type:I
        //  1758: if_icmpeq       1763
        //  1761: iconst_0       
        //  1762: ireturn        
        //  1763: aload_0         /* this */
        //  1764: invokevirtual   Parser.scan_points:()Z
        //  1767: ifeq            1778
        //  1770: iconst_1       
        //  1771: aload_0         /* this */
        //  1772: getfield        Parser.count_scanned_points:I
        //  1775: if_icmpeq       1780
        //  1778: iconst_0       
        //  1779: ireturn        
        //  1780: aload_0         /* this */
        //  1781: invokevirtual   Parser.scan_token:()V
        //  1784: iconst_4       
        //  1785: aload_0         /* this */
        //  1786: getfield        Parser.scanned_token_type:I
        //  1789: if_icmpeq       1794
        //  1792: iconst_0       
        //  1793: ireturn        
        //  1794: new             LPrimitive3D;
        //  1797: dup            
        //  1798: aload_1         /* default_primitive */
        //  1799: invokespecial   Primitive3D.<init>:(LPrimitive3D;)V
        //  1802: astore_2        /* primitive */
        //  1803: aload_2         /* primitive */
        //  1804: iconst_1       
        //  1805: putfield        Primitive3D.count_points:I
        //  1808: aload_2         /* primitive */
        //  1809: aload_0         /* this */
        //  1810: getfield        Parser.scanned_points:[[D
        //  1813: putfield        Primitive3D.original_points:[[D
        //  1816: aload_2         /* primitive */
        //  1817: aload_0         /* this */
        //  1818: getfield        Parser.scanned_scaled_offsets:[[D
        //  1821: putfield        Primitive3D.original_scaled_offsets:[[D
        //  1824: aload_2         /* primitive */
        //  1825: aload_0         /* this */
        //  1826: getfield        Parser.scanned_expressions:[[I
        //  1829: putfield        Primitive3D.original_expressions:[[I
        //  1832: aload_2         /* primitive */
        //  1833: iconst_0       
        //  1834: putfield        Primitive3D.is_filled:Z
        //  1837: aload_2         /* primitive */
        //  1838: iconst_0       
        //  1839: putfield        Primitive3D.is_outlined:Z
        //  1842: aload_0         /* this */
        //  1843: getfield        Parser.graphics:LGraphics3D;
        //  1846: aload_2         /* primitive */
        //  1847: invokevirtual   Graphics3D.addPrimitive:(LPrimitive3D;)V
        //  1850: iconst_1       
        //  1851: ireturn        
        //  1852: aload_0         /* this */
        //  1853: getfield        Parser.scanned_identifier:Ljava/lang/String;
        //  1856: getstatic       Parser.name_Line:Ljava/lang/String;
        //  1859: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //  1862: ifeq            1963
        //  1865: aload_0         /* this */
        //  1866: invokevirtual   Parser.scan_token:()V
        //  1869: iconst_3       
        //  1870: aload_0         /* this */
        //  1871: getfield        Parser.scanned_token_type:I
        //  1874: if_icmpeq       1879
        //  1877: iconst_0       
        //  1878: ireturn        
        //  1879: aload_0         /* this */
        //  1880: invokevirtual   Parser.scan_points:()Z
        //  1883: ifne            1888
        //  1886: iconst_0       
        //  1887: ireturn        
        //  1888: aload_0         /* this */
        //  1889: invokevirtual   Parser.scan_token:()V
        //  1892: iconst_4       
        //  1893: aload_0         /* this */
        //  1894: getfield        Parser.scanned_token_type:I
        //  1897: if_icmpeq       1902
        //  1900: iconst_0       
        //  1901: ireturn        
        //  1902: new             LPrimitive3D;
        //  1905: dup            
        //  1906: aload_1         /* default_primitive */
        //  1907: invokespecial   Primitive3D.<init>:(LPrimitive3D;)V
        //  1910: astore_2        /* primitive */
        //  1911: aload_2         /* primitive */
        //  1912: aload_0         /* this */
        //  1913: getfield        Parser.count_scanned_points:I
        //  1916: putfield        Primitive3D.count_points:I
        //  1919: aload_2         /* primitive */
        //  1920: aload_0         /* this */
        //  1921: getfield        Parser.scanned_points:[[D
        //  1924: putfield        Primitive3D.original_points:[[D
        //  1927: aload_2         /* primitive */
        //  1928: aload_0         /* this */
        //  1929: getfield        Parser.scanned_scaled_offsets:[[D
        //  1932: putfield        Primitive3D.original_scaled_offsets:[[D
        //  1935: aload_2         /* primitive */
        //  1936: aload_0         /* this */
        //  1937: getfield        Parser.scanned_expressions:[[I
        //  1940: putfield        Primitive3D.original_expressions:[[I
        //  1943: aload_2         /* primitive */
        //  1944: iconst_0       
        //  1945: putfield        Primitive3D.is_filled:Z
        //  1948: aload_2         /* primitive */
        //  1949: iconst_0       
        //  1950: putfield        Primitive3D.is_outlined:Z
        //  1953: aload_0         /* this */
        //  1954: getfield        Parser.graphics:LGraphics3D;
        //  1957: aload_2         /* primitive */
        //  1958: invokevirtual   Graphics3D.addPrimitive:(LPrimitive3D;)V
        //  1961: iconst_1       
        //  1962: ireturn        
        //  1963: aload_0         /* this */
        //  1964: getfield        Parser.scanned_identifier:Ljava/lang/String;
        //  1967: getstatic       Parser.name_Polygon:Ljava/lang/String;
        //  1970: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //  1973: ifeq            2274
        //  1976: aconst_null    
        //  1977: astore_2        /* scanned_edge_flags */
        //  1978: aload_0         /* this */
        //  1979: invokevirtual   Parser.scan_token:()V
        //  1982: iconst_3       
        //  1983: aload_0         /* this */
        //  1984: getfield        Parser.scanned_token_type:I
        //  1987: if_icmpeq       1992
        //  1990: iconst_0       
        //  1991: ireturn        
        //  1992: aload_0         /* this */
        //  1993: invokevirtual   Parser.scan_points:()Z
        //  1996: ifne            2001
        //  1999: iconst_0       
        //  2000: ireturn        
        //  2001: aload_0         /* this */
        //  2002: invokevirtual   Parser.scan_token:()V
        //  2005: iconst_0       
        //  2006: aload_0         /* this */
        //  2007: getfield        Parser.scanned_token_type:I
        //  2010: if_icmpne       2203
        //  2013: aload_0         /* this */
        //  2014: invokevirtual   Parser.scan_salt:()V
        //  2017: aload_0         /* this */
        //  2018: invokevirtual   Parser.scan_token:()V
        //  2021: iconst_0       
        //  2022: aload_0         /* this */
        //  2023: getfield        Parser.scanned_token_type:I
        //  2026: if_icmpne       2203
        //  2029: aload_0         /* this */
        //  2030: invokevirtual   Parser.scan_token:()V
        //  2033: iconst_5       
        //  2034: aload_0         /* this */
        //  2035: getfield        Parser.scanned_token_type:I
        //  2038: if_icmpeq       2052
        //  2041: aload_0         /* this */
        //  2042: invokevirtual   Parser.scan_salt:()V
        //  2045: aload_0         /* this */
        //  2046: invokevirtual   Parser.scan_token:()V
        //  2049: goto            2203
        //  2052: aload_0         /* this */
        //  2053: getfield        Parser.count_scanned_points:I
        //  2056: newarray        Z
        //  2058: astore_2        /* scanned_edge_flags */
        //  2059: iconst_0       
        //  2060: istore_3        /* edge_index */
        //  2061: bipush          6
        //  2063: aload_0         /* this */
        //  2064: getfield        Parser.scanned_token_type:I
        //  2067: if_icmpeq       2189
        //  2070: aload_0         /* this */
        //  2071: invokevirtual   Parser.scan_token:()V
        //  2074: bipush          21
        //  2076: aload_0         /* this */
        //  2077: getfield        Parser.scanned_token_type:I
        //  2080: if_icmpeq       2091
        //  2083: aload_0         /* this */
        //  2084: invokevirtual   Parser.scan_salt:()V
        //  2087: aload_0         /* this */
        //  2088: invokevirtual   Parser.scan_token:()V
        //  2091: aload_0         /* this */
        //  2092: getfield        Parser.scanned_identifier:Ljava/lang/String;
        //  2095: getstatic       Parser.name_True:Ljava/lang/String;
        //  2098: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //  2101: ifeq            2123
        //  2104: iload_3         /* edge_index */
        //  2105: aload_0         /* this */
        //  2106: getfield        Parser.count_scanned_points:I
        //  2109: if_icmpge       2116
        //  2112: aload_2         /* scanned_edge_flags */
        //  2113: iload_3         /* edge_index */
        //  2114: iconst_1       
        //  2115: bastore        
        //  2116: aload_0         /* this */
        //  2117: invokevirtual   Parser.scan_token:()V
        //  2120: goto            2163
        //  2123: aload_0         /* this */
        //  2124: getfield        Parser.scanned_identifier:Ljava/lang/String;
        //  2127: getstatic       Parser.name_False:Ljava/lang/String;
        //  2130: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //  2133: ifeq            2155
        //  2136: iload_3         /* edge_index */
        //  2137: aload_0         /* this */
        //  2138: getfield        Parser.count_scanned_points:I
        //  2141: if_icmpge       2148
        //  2144: aload_2         /* scanned_edge_flags */
        //  2145: iload_3         /* edge_index */
        //  2146: iconst_0       
        //  2147: bastore        
        //  2148: aload_0         /* this */
        //  2149: invokevirtual   Parser.scan_token:()V
        //  2152: goto            2163
        //  2155: aload_0         /* this */
        //  2156: invokevirtual   Parser.scan_salt:()V
        //  2159: aload_0         /* this */
        //  2160: invokevirtual   Parser.scan_token:()V
        //  2163: iconst_0       
        //  2164: aload_0         /* this */
        //  2165: getfield        Parser.scanned_token_type:I
        //  2168: if_icmpeq       2182
        //  2171: bipush          6
        //  2173: aload_0         /* this */
        //  2174: getfield        Parser.scanned_token_type:I
        //  2177: if_icmpeq       2182
        //  2180: iconst_0       
        //  2181: ireturn        
        //  2182: iload_3         /* edge_index */
        //  2183: iconst_1       
        //  2184: iadd           
        //  2185: istore_3        /* edge_index */
        //  2186: goto            2061
        //  2189: iload_3         /* edge_index */
        //  2190: aload_0         /* this */
        //  2191: getfield        Parser.count_scanned_points:I
        //  2194: if_icmpeq       2199
        //  2197: aconst_null    
        //  2198: astore_2        /* scanned_edge_flags */
        //  2199: aload_0         /* this */
        //  2200: invokevirtual   Parser.scan_token:()V
        //  2203: iconst_4       
        //  2204: aload_0         /* this */
        //  2205: getfield        Parser.scanned_token_type:I
        //  2208: if_icmpeq       2213
        //  2211: iconst_0       
        //  2212: ireturn        
        //  2213: new             LPrimitive3D;
        //  2216: dup            
        //  2217: aload_1         /* default_primitive */
        //  2218: invokespecial   Primitive3D.<init>:(LPrimitive3D;)V
        //  2221: astore_3        /* primitive */
        //  2222: aload_3         /* primitive */
        //  2223: aload_0         /* this */
        //  2224: getfield        Parser.count_scanned_points:I
        //  2227: putfield        Primitive3D.count_points:I
        //  2230: aload_3         /* primitive */
        //  2231: aload_0         /* this */
        //  2232: getfield        Parser.scanned_points:[[D
        //  2235: putfield        Primitive3D.original_points:[[D
        //  2238: aload_3         /* primitive */
        //  2239: aload_0         /* this */
        //  2240: getfield        Parser.scanned_scaled_offsets:[[D
        //  2243: putfield        Primitive3D.original_scaled_offsets:[[D
        //  2246: aload_3         /* primitive */
        //  2247: aload_0         /* this */
        //  2248: getfield        Parser.scanned_expressions:[[I
        //  2251: putfield        Primitive3D.original_expressions:[[I
        //  2254: aload_3         /* primitive */
        //  2255: aload_2         /* scanned_edge_flags */
        //  2256: putfield        Primitive3D.edge_flags:[Z
        //  2259: aload_3         /* primitive */
        //  2260: iconst_1       
        //  2261: putfield        Primitive3D.is_filled:Z
        //  2264: aload_0         /* this */
        //  2265: getfield        Parser.graphics:LGraphics3D;
        //  2268: aload_3         /* primitive */
        //  2269: invokevirtual   Graphics3D.addPrimitive:(LPrimitive3D;)V
        //  2272: iconst_1       
        //  2273: ireturn        
        //  2274: aload_0         /* this */
        //  2275: getfield        Parser.scanned_identifier:Ljava/lang/String;
        //  2278: getstatic       Parser.name_Cuboid:Ljava/lang/String;
        //  2281: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //  2284: ifeq            2933
        //  2287: bipush          6
        //  2289: anewarray       [I
        //  2292: dup            
        //  2293: iconst_0       
        //  2294: iconst_4       
        //  2295: newarray        I
        //  2297: dup            
        //  2298: iconst_0       
        //  2299: iconst_0       
        //  2300: iastore        
        //  2301: dup            
        //  2302: iconst_1       
        //  2303: iconst_1       
        //  2304: iastore        
        //  2305: dup            
        //  2306: iconst_2       
        //  2307: iconst_3       
        //  2308: iastore        
        //  2309: dup            
        //  2310: iconst_3       
        //  2311: iconst_2       
        //  2312: iastore        
        //  2313: aastore        
        //  2314: dup            
        //  2315: iconst_1       
        //  2316: iconst_4       
        //  2317: newarray        I
        //  2319: dup            
        //  2320: iconst_0       
        //  2321: iconst_0       
        //  2322: iastore        
        //  2323: dup            
        //  2324: iconst_1       
        //  2325: iconst_4       
        //  2326: iastore        
        //  2327: dup            
        //  2328: iconst_2       
        //  2329: iconst_5       
        //  2330: iastore        
        //  2331: dup            
        //  2332: iconst_3       
        //  2333: iconst_1       
        //  2334: iastore        
        //  2335: aastore        
        //  2336: dup            
        //  2337: iconst_2       
        //  2338: iconst_4       
        //  2339: newarray        I
        //  2341: dup            
        //  2342: iconst_0       
        //  2343: iconst_0       
        //  2344: iastore        
        //  2345: dup            
        //  2346: iconst_1       
        //  2347: iconst_2       
        //  2348: iastore        
        //  2349: dup            
        //  2350: iconst_2       
        //  2351: bipush          6
        //  2353: iastore        
        //  2354: dup            
        //  2355: iconst_3       
        //  2356: iconst_4       
        //  2357: iastore        
        //  2358: aastore        
        //  2359: dup            
        //  2360: iconst_3       
        //  2361: iconst_4       
        //  2362: newarray        I
        //  2364: dup            
        //  2365: iconst_0       
        //  2366: bipush          7
        //  2368: iastore        
        //  2369: dup            
        //  2370: iconst_1       
        //  2371: bipush          6
        //  2373: iastore        
        //  2374: dup            
        //  2375: iconst_2       
        //  2376: iconst_4       
        //  2377: iastore        
        //  2378: dup            
        //  2379: iconst_3       
        //  2380: iconst_5       
        //  2381: iastore        
        //  2382: aastore        
        //  2383: dup            
        //  2384: iconst_4       
        //  2385: iconst_4       
        //  2386: newarray        I
        //  2388: dup            
        //  2389: iconst_0       
        //  2390: bipush          7
        //  2392: iastore        
        //  2393: dup            
        //  2394: iconst_1       
        //  2395: iconst_3       
        //  2396: iastore        
        //  2397: dup            
        //  2398: iconst_2       
        //  2399: iconst_2       
        //  2400: iastore        
        //  2401: dup            
        //  2402: iconst_3       
        //  2403: bipush          6
        //  2405: iastore        
        //  2406: aastore        
        //  2407: dup            
        //  2408: iconst_5       
        //  2409: iconst_4       
        //  2410: newarray        I
        //  2412: dup            
        //  2413: iconst_0       
        //  2414: bipush          7
        //  2416: iastore        
        //  2417: dup            
        //  2418: iconst_1       
        //  2419: iconst_5       
        //  2420: iastore        
        //  2421: dup            
        //  2422: iconst_2       
        //  2423: iconst_1       
        //  2424: iastore        
        //  2425: dup            
        //  2426: iconst_3       
        //  2427: iconst_3       
        //  2428: iastore        
        //  2429: aastore        
        //  2430: astore          cuboid_codes
        //  2432: aload_0         /* this */
        //  2433: invokevirtual   Parser.scan_token:()V
        //  2436: iconst_3       
        //  2437: aload_0         /* this */
        //  2438: getfield        Parser.scanned_token_type:I
        //  2441: if_icmpeq       2446
        //  2444: iconst_0       
        //  2445: ireturn        
        //  2446: aload_0         /* this */
        //  2447: invokevirtual   Parser.scan_points:()Z
        //  2450: ifeq            2461
        //  2453: iconst_1       
        //  2454: aload_0         /* this */
        //  2455: getfield        Parser.count_scanned_points:I
        //  2458: if_icmpeq       2463
        //  2461: iconst_0       
        //  2462: ireturn        
        //  2463: aload_0         /* this */
        //  2464: getfield        Parser.scanned_points:[[D
        //  2467: iconst_0       
        //  2468: aaload         
        //  2469: astore_2        /* min_point */
        //  2470: aload_0         /* this */
        //  2471: getfield        Parser.scanned_scaled_offsets:[[D
        //  2474: iconst_0       
        //  2475: aaload         
        //  2476: astore_3        /* min_scaled_offset */
        //  2477: aconst_null    
        //  2478: aload_0         /* this */
        //  2479: getfield        Parser.scanned_expressions:[[I
        //  2482: if_acmpeq       2520
        //  2485: aload_0         /* this */
        //  2486: getfield        Parser.scanned_expressions:[[I
        //  2489: iconst_0       
        //  2490: aaload         
        //  2491: iconst_0       
        //  2492: iaload         
        //  2493: ifge            2518
        //  2496: aload_0         /* this */
        //  2497: getfield        Parser.scanned_expressions:[[I
        //  2500: iconst_0       
        //  2501: aaload         
        //  2502: iconst_1       
        //  2503: iaload         
        //  2504: ifge            2518
        //  2507: aload_0         /* this */
        //  2508: getfield        Parser.scanned_expressions:[[I
        //  2511: iconst_0       
        //  2512: aaload         
        //  2513: iconst_2       
        //  2514: iaload         
        //  2515: iflt            2520
        //  2518: iconst_0       
        //  2519: ireturn        
        //  2520: aload_0         /* this */
        //  2521: invokevirtual   Parser.scan_token:()V
        //  2524: iconst_4       
        //  2525: aload_0         /* this */
        //  2526: getfield        Parser.scanned_token_type:I
        //  2529: if_icmpne       2584
        //  2532: aconst_null    
        //  2533: aload_2         /* min_point */
        //  2534: if_acmpeq       2575
        //  2537: iconst_3       
        //  2538: newarray        D
        //  2540: astore          max_point
        //  2542: aload           max_point
        //  2544: iconst_0       
        //  2545: aload_2         /* min_point */
        //  2546: iconst_0       
        //  2547: daload         
        //  2548: dconst_1       
        //  2549: dadd           
        //  2550: dastore        
        //  2551: aload           max_point
        //  2553: iconst_1       
        //  2554: aload_2         /* min_point */
        //  2555: iconst_1       
        //  2556: daload         
        //  2557: dconst_1       
        //  2558: dadd           
        //  2559: dastore        
        //  2560: aload           max_point
        //  2562: iconst_2       
        //  2563: aload_2         /* min_point */
        //  2564: iconst_2       
        //  2565: daload         
        //  2566: dconst_1       
        //  2567: dadd           
        //  2568: dastore        
        //  2569: aload_3         /* min_scaled_offset */
        //  2570: astore          max_scaled_offset
        //  2572: goto            2687
        //  2575: aconst_null    
        //  2576: astore          max_point
        //  2578: aconst_null    
        //  2579: astore          max_scaled_offset
        //  2581: goto            2687
        //  2584: iconst_0       
        //  2585: aload_0         /* this */
        //  2586: getfield        Parser.scanned_token_type:I
        //  2589: if_icmpne       2685
        //  2592: aload_0         /* this */
        //  2593: invokevirtual   Parser.scan_points:()Z
        //  2596: ifeq            2607
        //  2599: iconst_1       
        //  2600: aload_0         /* this */
        //  2601: getfield        Parser.count_scanned_points:I
        //  2604: if_icmpeq       2609
        //  2607: iconst_0       
        //  2608: ireturn        
        //  2609: aconst_null    
        //  2610: aload_0         /* this */
        //  2611: getfield        Parser.scanned_expressions:[[I
        //  2614: if_acmpeq       2652
        //  2617: aload_0         /* this */
        //  2618: getfield        Parser.scanned_expressions:[[I
        //  2621: iconst_0       
        //  2622: aaload         
        //  2623: iconst_0       
        //  2624: iaload         
        //  2625: ifge            2650
        //  2628: aload_0         /* this */
        //  2629: getfield        Parser.scanned_expressions:[[I
        //  2632: iconst_0       
        //  2633: aaload         
        //  2634: iconst_1       
        //  2635: iaload         
        //  2636: ifge            2650
        //  2639: aload_0         /* this */
        //  2640: getfield        Parser.scanned_expressions:[[I
        //  2643: iconst_0       
        //  2644: aaload         
        //  2645: iconst_2       
        //  2646: iaload         
        //  2647: iflt            2652
        //  2650: iconst_0       
        //  2651: ireturn        
        //  2652: aload_0         /* this */
        //  2653: invokevirtual   Parser.scan_token:()V
        //  2656: iconst_4       
        //  2657: aload_0         /* this */
        //  2658: getfield        Parser.scanned_token_type:I
        //  2661: if_icmpeq       2666
        //  2664: iconst_0       
        //  2665: ireturn        
        //  2666: aload_0         /* this */
        //  2667: getfield        Parser.scanned_points:[[D
        //  2670: iconst_0       
        //  2671: aaload         
        //  2672: astore          max_point
        //  2674: aload_0         /* this */
        //  2675: getfield        Parser.scanned_scaled_offsets:[[D
        //  2678: iconst_0       
        //  2679: aaload         
        //  2680: astore          max_scaled_offset
        //  2682: goto            2687
        //  2685: iconst_0       
        //  2686: ireturn        
        //  2687: aconst_null    
        //  2688: aload_2         /* min_point */
        //  2689: if_acmpeq       2698
        //  2692: aconst_null    
        //  2693: aload           4
        //  2695: if_acmpne       2715
        //  2698: aconst_null    
        //  2699: aload_2         /* min_point */
        //  2700: if_acmpne       2931
        //  2703: aconst_null    
        //  2704: aload           4
        //  2706: if_acmpne       2931
        //  2709: aconst_null    
        //  2710: aload           5
        //  2712: if_acmpeq       2931
        //  2715: iconst_0       
        //  2716: istore          face_index
        //  2718: iload           face_index
        //  2720: bipush          6
        //  2722: if_icmpge       2931
        //  2725: new             LPrimitive3D;
        //  2728: dup            
        //  2729: aload_1         /* default_primitive */
        //  2730: invokespecial   Primitive3D.<init>:(LPrimitive3D;)V
        //  2733: astore          primitive
        //  2735: aload           primitive
        //  2737: iconst_4       
        //  2738: putfield        Primitive3D.count_points:I
        //  2741: aload           primitive
        //  2743: iconst_4       
        //  2744: iconst_3       
        //  2745: multianewarray  [[D
        //  2749: putfield        Primitive3D.original_points:[[D
        //  2752: aload           primitive
        //  2754: iconst_4       
        //  2755: iconst_3       
        //  2756: multianewarray  [[D
        //  2760: putfield        Primitive3D.original_scaled_offsets:[[D
        //  2763: aload           primitive
        //  2765: iconst_1       
        //  2766: putfield        Primitive3D.is_filled:Z
        //  2769: iconst_0       
        //  2770: istore          corner_index
        //  2772: iload           corner_index
        //  2774: iconst_4       
        //  2775: if_icmpge       2916
        //  2778: iconst_0       
        //  2779: istore          axes_index
        //  2781: iload           axes_index
        //  2783: iconst_3       
        //  2784: if_icmpge       2910
        //  2787: iconst_0       
        //  2788: aload           cuboid_codes
        //  2790: iload           face_index
        //  2792: aaload         
        //  2793: iload           corner_index
        //  2795: iaload         
        //  2796: iconst_1       
        //  2797: iload           axes_index
        //  2799: ishl           
        //  2800: iand           
        //  2801: if_icmpeq       2857
        //  2804: aconst_null    
        //  2805: aload           4
        //  2807: if_acmpeq       2829
        //  2810: aload           primitive
        //  2812: getfield        Primitive3D.original_points:[[D
        //  2815: iload           corner_index
        //  2817: aaload         
        //  2818: iload           axes_index
        //  2820: aload           4
        //  2822: iload           axes_index
        //  2824: daload         
        //  2825: dastore        
        //  2826: goto            2838
        //  2829: aload           primitive
        //  2831: getfield        Primitive3D.original_points:[[D
        //  2834: iload           corner_index
        //  2836: aconst_null    
        //  2837: aastore        
        //  2838: aload           primitive
        //  2840: getfield        Primitive3D.original_scaled_offsets:[[D
        //  2843: iload           corner_index
        //  2845: aaload         
        //  2846: iload           axes_index
        //  2848: aload           5
        //  2850: iload           axes_index
        //  2852: daload         
        //  2853: dastore        
        //  2854: goto            2904
        //  2857: aconst_null    
        //  2858: aload_2         /* min_point */
        //  2859: if_acmpeq       2880
        //  2862: aload           primitive
        //  2864: getfield        Primitive3D.original_points:[[D
        //  2867: iload           corner_index
        //  2869: aaload         
        //  2870: iload           axes_index
        //  2872: aload_2         /* min_point */
        //  2873: iload           axes_index
        //  2875: daload         
        //  2876: dastore        
        //  2877: goto            2889
        //  2880: aload           primitive
        //  2882: getfield        Primitive3D.original_points:[[D
        //  2885: iload           corner_index
        //  2887: aconst_null    
        //  2888: aastore        
        //  2889: aload           primitive
        //  2891: getfield        Primitive3D.original_scaled_offsets:[[D
        //  2894: iload           corner_index
        //  2896: aaload         
        //  2897: iload           axes_index
        //  2899: aload_3         /* min_scaled_offset */
        //  2900: iload           axes_index
        //  2902: daload         
        //  2903: dastore        
        //  2904: iinc            axes_index, 1
        //  2907: goto            2781
        //  2910: iinc            corner_index, 1
        //  2913: goto            2772
        //  2916: aload_0         /* this */
        //  2917: getfield        Parser.graphics:LGraphics3D;
        //  2920: aload           primitive
        //  2922: invokevirtual   Graphics3D.addPrimitive:(LPrimitive3D;)V
        //  2925: iinc            face_index, 1
        //  2928: goto            2718
        //  2931: iconst_1       
        //  2932: ireturn        
        //  2933: aload_0         /* this */
        //  2934: getfield        Parser.scanned_identifier:Ljava/lang/String;
        //  2937: getstatic       Parser.name_Text:Ljava/lang/String;
        //  2940: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //  2943: ifeq            3130
        //  2946: new             LPrimitive3D;
        //  2949: dup            
        //  2950: aload_1         /* default_primitive */
        //  2951: invokespecial   Primitive3D.<init>:(LPrimitive3D;)V
        //  2954: astore_2        /* primitive */
        //  2955: aload_0         /* this */
        //  2956: invokevirtual   Parser.scan_token:()V
        //  2959: iconst_3       
        //  2960: aload_0         /* this */
        //  2961: getfield        Parser.scanned_token_type:I
        //  2964: if_icmpeq       2969
        //  2967: iconst_0       
        //  2968: ireturn        
        //  2969: aload_0         /* this */
        //  2970: invokevirtual   Parser.scan_token:()V
        //  2973: aload_0         /* this */
        //  2974: aload_2         /* primitive */
        //  2975: iconst_1       
        //  2976: invokevirtual   Parser.scan_text:(LPrimitive3D;Z)Z
        //  2979: ifne            2984
        //  2982: iconst_0       
        //  2983: ireturn        
        //  2984: aload_0         /* this */
        //  2985: invokevirtual   Parser.scan_token:()V
        //  2988: iconst_0       
        //  2989: aload_0         /* this */
        //  2990: getfield        Parser.scanned_token_type:I
        //  2993: if_icmpeq       2998
        //  2996: iconst_0       
        //  2997: ireturn        
        //  2998: aload_0         /* this */
        //  2999: invokevirtual   Parser.scan_points:()Z
        //  3002: ifeq            3013
        //  3005: iconst_1       
        //  3006: aload_0         /* this */
        //  3007: getfield        Parser.count_scanned_points:I
        //  3010: if_icmpeq       3015
        //  3013: iconst_0       
        //  3014: ireturn        
        //  3015: aload_2         /* primitive */
        //  3016: iconst_1       
        //  3017: putfield        Primitive3D.count_points:I
        //  3020: aload_2         /* primitive */
        //  3021: aload_0         /* this */
        //  3022: getfield        Parser.scanned_points:[[D
        //  3025: putfield        Primitive3D.original_points:[[D
        //  3028: aload_2         /* primitive */
        //  3029: aload_0         /* this */
        //  3030: getfield        Parser.scanned_scaled_offsets:[[D
        //  3033: putfield        Primitive3D.original_scaled_offsets:[[D
        //  3036: aload_2         /* primitive */
        //  3037: aload_0         /* this */
        //  3038: getfield        Parser.scanned_expressions:[[I
        //  3041: putfield        Primitive3D.original_expressions:[[I
        //  3044: aload_0         /* this */
        //  3045: invokevirtual   Parser.scan_token:()V
        //  3048: dconst_0       
        //  3049: dstore_3        /* x_offset */
        //  3050: dconst_0       
        //  3051: dstore          y_offset
        //  3053: iconst_0       
        //  3054: aload_0         /* this */
        //  3055: getfield        Parser.scanned_token_type:I
        //  3058: if_icmpne       3099
        //  3061: aload_0         /* this */
        //  3062: iconst_0       
        //  3063: iconst_1       
        //  3064: invokevirtual   Parser.scan_numbers:(ZZ)Z
        //  3067: ifeq            3078
        //  3070: iconst_2       
        //  3071: aload_0         /* this */
        //  3072: getfield        Parser.count_scanned_numbers:I
        //  3075: if_icmpeq       3080
        //  3078: iconst_0       
        //  3079: ireturn        
        //  3080: aload_0         /* this */
        //  3081: getfield        Parser.scanned_numbers:[D
        //  3084: iconst_0       
        //  3085: daload         
        //  3086: dstore_3        /* x_offset */
        //  3087: aload_0         /* this */
        //  3088: getfield        Parser.scanned_numbers:[D
        //  3091: iconst_1       
        //  3092: daload         
        //  3093: dstore          y_offset
        //  3095: aload_0         /* this */
        //  3096: invokevirtual   Parser.scan_right_bracket:()V
        //  3099: iconst_4       
        //  3100: aload_0         /* this */
        //  3101: getfield        Parser.scanned_token_type:I
        //  3104: if_icmpeq       3109
        //  3107: iconst_0       
        //  3108: ireturn        
        //  3109: aload_2         /* primitive */
        //  3110: dload_3         /* x_offset */
        //  3111: putfield        Primitive3D.original_point_size:D
        //  3114: aload_2         /* primitive */
        //  3115: dload           y_offset
        //  3117: putfield        Primitive3D.original_thickness:D
        //  3120: aload_0         /* this */
        //  3121: getfield        Parser.graphics:LGraphics3D;
        //  3124: aload_2         /* primitive */
        //  3125: invokevirtual   Graphics3D.addPrimitive:(LPrimitive3D;)V
        //  3128: iconst_1       
        //  3129: ireturn        
        //  3130: aload_0         /* this */
        //  3131: invokevirtual   Parser.scan_salt:()V
        //  3134: bipush          44
        //  3136: aload_0         /* this */
        //  3137: getfield        Parser.current_char:C
        //  3140: if_icmpeq       3161
        //  3143: bipush          125
        //  3145: aload_0         /* this */
        //  3146: getfield        Parser.current_char:C
        //  3149: if_icmpeq       3161
        //  3152: bipush          93
        //  3154: aload_0         /* this */
        //  3155: getfield        Parser.current_char:C
        //  3158: if_icmpne       3168
        //  3161: aload_0         /* this */
        //  3162: iconst_1       
        //  3163: putfield        Parser.scanned_unidentified:Z
        //  3166: iconst_1       
        //  3167: ireturn        
        //  3168: iconst_0       
        //  3169: ireturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name                      Signature
        //  -----  ------  ----  ------------------------  -------------
        //  311    3       3     is_if_condition_numeric   Z
        //  632    3       6     inactive_flag             I
        //  296    520     2     if_condition_expression   I
        //  316    500     3     is_if_condition_numeric   Z
        //  302    514     4     if_condition_number       D
        //  638    178     6     inactive_flag             I
        //  223    593     7     if_clause_min_index       I
        //  226    590     8     if_clause_max_index       I
        //  229    587     9     else_clause_min_index     I
        //  232    584     10    else_clause_max_index     I
        //  235    581     11    default_clause_min_index  I
        //  238    578     12    default_clause_max_index  I
        //  1803   49      2     primitive                 LPrimitive3D;
        //  1911   52      2     primitive                 LPrimitive3D;
        //  2061   142     3     edge_index                I
        //  1978   296     2     scanned_edge_flags        [Z
        //  2222   52      3     primitive                 LPrimitive3D;
        //  2542   33      4     max_point                 [D
        //  2572   3       5     max_scaled_offset         [D
        //  2578   6       4     max_point                 [D
        //  2581   3       5     max_scaled_offset         [D
        //  2674   11      4     max_point                 [D
        //  2682   3       5     max_scaled_offset         [D
        //  2781   129     10    axes_index                I
        //  2772   144     9     corner_index              I
        //  2735   190     8     primitive                 LPrimitive3D;
        //  2718   213     7     face_index                I
        //  2470   463     2     min_point                 [D
        //  2477   456     3     min_scaled_offset         [D
        //  2432   501     6     cuboid_codes              [[I
        //  2955   175     2     primitive                 LPrimitive3D;
        //  3050   80      3     x_offset                  D
        //  3053   77      5     y_offset                  D
        //  0      3170    0     this                      LParser;
        //  0      3170    1     default_primitive         LPrimitive3D;
        // 
        // The error that occurred was:
        // 
        // java.lang.IndexOutOfBoundsException: Index: 0, Size: 0
        //     at java.util.ArrayList.rangeCheck(ArrayList.java:657)
        //     at java.util.ArrayList.get(ArrayList.java:433)
        //     at com.strobel.decompiler.ast.AstBuilder.convertLocalVariables(AstBuilder.java:3035)
        //     at com.strobel.decompiler.ast.AstBuilder.performStackAnalysis(AstBuilder.java:2445)
        //     at com.strobel.decompiler.ast.AstBuilder.build(AstBuilder.java:108)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:210)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:757)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:655)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:532)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:130)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:105)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
        //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:317)
        //     at com.strobel.decompiler.DecompilerDriver.decompileJar(DecompilerDriver.java:238)
        //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:138)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    boolean scan_numbers(final boolean chop, final boolean brace) {
        int index = 0;
        this.scan_token();
        if ((!brace && 3 != this.scanned_token_type) || (brace && 5 != this.scanned_token_type)) {
            return false;
        }
        while (index < Parser.max_count_scanned_numbers) {
            this.scan_token();
            if (22 != this.scanned_token_type) {
                break;
            }
            this.scanned_numbers[index] = this.scanned_number;
            if (chop) {
                if (this.scanned_number > 0.9999) {
                    this.scanned_numbers[index] = 0.9999;
                }
                else if (this.scanned_number < 1.0E-4) {
                    this.scanned_numbers[index] = 1.0E-4;
                }
            }
            ++index;
            this.scan_token();
            if (0 != this.scanned_token_type) {
                break;
            }
        }
        this.count_scanned_numbers = index;
        if (((!brace && 4 != this.scanned_token_type) || (brace && 6 != this.scanned_token_type)) && index >= Parser.max_count_scanned_numbers) {
            this.scan_right_bracket();
        }
        return (brace || 4 == this.scanned_token_type) && (!brace || 6 == this.scanned_token_type);
    }
    
    boolean scan_points() {
        boolean is_list = false;
        final boolean scanned_brace = false;
        int point_index = 0;
        this.scanned_points = null;
        this.scanned_scaled_offsets = null;
        this.scanned_expressions = null;
        this.points_scanned = new Vector(2);
        this.scaled_offsets_scanned = new Vector(2);
        if (null != this.evaluator) {
            this.is_scanning_expression = true;
            this.is_scanning_after_operand = false;
            this.expressions_scanned = new Vector(2);
        }
        while (0 == point_index || (is_list && 0 == this.scanned_token_type)) {
            final double[] point = { 0.0, 0.0, 0.0 };
            final double[] scaled_offset = { 0.0, 0.0, 0.0 };
            final int[] expressions = { -1, -1, -1 };
            this.scan_token();
            if (5 == this.scanned_token_type) {
                this.scan_token();
                if (0 == point_index) {
                    if (5 == this.scanned_token_type) {
                        is_list = true;
                        this.scan_token();
                    }
                    else if (21 == this.scanned_token_type && this.scanned_identifier.equals(Parser.name_Scaled)) {
                        is_list = true;
                    }
                }
            }
            if (21 == this.scanned_token_type && this.scanned_identifier.equals(Parser.name_Scaled)) {
                this.scan_token();
                if (3 != this.scanned_token_type) {
                    this.points_scanned = null;
                    this.scaled_offsets_scanned = null;
                    return this.is_scanning_expression = false;
                }
                if (!this.scan_numbers(false, true) || 3 != this.count_scanned_numbers) {
                    this.points_scanned = null;
                    this.scaled_offsets_scanned = null;
                    return this.is_scanning_expression = false;
                }
                scaled_offset[0] = this.scanned_numbers[0];
                scaled_offset[1] = this.scanned_numbers[1];
                scaled_offset[2] = this.scanned_numbers[2];
                this.scan_token();
                if (0 == this.scanned_token_type) {
                    if (!this.scan_numbers(false, true) || 3 != this.count_scanned_numbers) {
                        this.points_scanned = null;
                        this.scaled_offsets_scanned = null;
                        return this.is_scanning_expression = false;
                    }
                    point[0] = this.scanned_numbers[0];
                    point[1] = this.scanned_numbers[1];
                    point[2] = this.scanned_numbers[2];
                    this.scan_token();
                    if (4 != this.scanned_token_type) {
                        this.points_scanned = null;
                        this.scaled_offsets_scanned = null;
                        return this.is_scanning_expression = false;
                    }
                    this.points_scanned.addElement(point);
                    this.scaled_offsets_scanned.addElement(scaled_offset);
                    if (null != this.evaluator) {
                        this.expressions_scanned.addElement(expressions);
                    }
                }
                else {
                    if (4 != this.scanned_token_type) {
                        this.points_scanned = null;
                        this.scaled_offsets_scanned = null;
                        return this.is_scanning_expression = false;
                    }
                    this.points_scanned.addElement(null);
                    this.scaled_offsets_scanned.addElement(scaled_offset);
                    if (null != this.evaluator) {
                        this.expressions_scanned.addElement(expressions);
                    }
                }
            }
            else if (null != this.evaluator) {
                int index;
                for (index = 0; index < 3; ++index) {
                    if (!this.scan_expression(true)) {
                        this.points_scanned = null;
                        this.scaled_offsets_scanned = null;
                        this.expressions_scanned = null;
                        return false;
                    }
                    this.is_scanning_expression = true;
                    this.is_scanning_after_operand = false;
                    if (!this.is_scanned_expression_numeric) {
                        expressions[index] = this.scanned_expression;
                    }
                    point[index] = this.scanned_number;
                    if (0 != this.scanned_token_type) {
                        break;
                    }
                    this.scan_token();
                }
                if (index < 2 || 6 != this.scanned_token_type) {
                    this.points_scanned = null;
                    this.scaled_offsets_scanned = null;
                    this.expressions_scanned = null;
                    return this.is_scanning_expression = false;
                }
                this.points_scanned.addElement(point);
                this.scaled_offsets_scanned.addElement(scaled_offset);
                this.expressions_scanned.addElement(expressions);
            }
            else {
                if (22 != this.scanned_token_type) {
                    this.points_scanned = null;
                    this.scaled_offsets_scanned = null;
                    return this.is_scanning_expression = false;
                }
                int index;
                for (index = 0; index < 3 && 22 == this.scanned_token_type; ++index) {
                    point[index] = this.scanned_number;
                    this.scan_token();
                    if (0 != this.scanned_token_type) {
                        break;
                    }
                    this.scan_token();
                }
                if (index < 2 || 6 != this.scanned_token_type) {
                    this.points_scanned = null;
                    this.scaled_offsets_scanned = null;
                    return this.is_scanning_expression = false;
                }
                this.points_scanned.addElement(point);
                this.scaled_offsets_scanned.addElement(scaled_offset);
                if (null != this.evaluator) {
                    this.expressions_scanned.addElement(expressions);
                }
            }
            if (is_list) {
                this.scan_token();
            }
            ++point_index;
        }
        if (6 != this.scanned_token_type && 4 != this.scanned_token_type) {
            this.points_scanned = null;
            this.scaled_offsets_scanned = null;
            return this.is_scanning_expression = false;
        }
        this.count_scanned_points = point_index;
        this.scanned_points = new double[this.count_scanned_points][3];
        this.scanned_scaled_offsets = new double[this.count_scanned_points][3];
        if (null != this.evaluator) {
            this.scanned_expressions = new int[this.count_scanned_points][3];
        }
        for (point_index = 0; point_index < this.count_scanned_points; ++point_index) {
            this.scanned_points[point_index] = this.points_scanned.elementAt(point_index);
            this.scanned_scaled_offsets[point_index] = this.scaled_offsets_scanned.elementAt(point_index);
            if (null != this.evaluator) {
                this.scanned_expressions[point_index] = this.expressions_scanned.elementAt(point_index);
            }
        }
        this.points_scanned = null;
        this.scaled_offsets_scanned = null;
        this.expressions_scanned = null;
        this.is_scanning_expression = false;
        return true;
    }
    
    boolean scan_color() {
        this.scanned_color = null;
        if (this.scanned_identifier.equals(Parser.name_RGBColor)) {
            if (!this.scan_numbers(true, false) || 3 != this.count_scanned_numbers) {
                return false;
            }
            this.scanned_color = new Color((float)this.scanned_numbers[0], (float)this.scanned_numbers[1], (float)this.scanned_numbers[2]);
        }
        else if (this.scanned_identifier.equals(Parser.name_Hue)) {
            if (!this.scan_numbers(false, false)) {
                return false;
            }
            if (1 == this.count_scanned_numbers) {
                this.scanned_numbers[1] = 1.0;
                this.count_scanned_numbers = 2;
            }
            if (2 == this.count_scanned_numbers) {
                this.scanned_numbers[2] = 1.0;
                this.count_scanned_numbers = 3;
            }
            if (3 != this.count_scanned_numbers) {
                return false;
            }
            this.scanned_numbers[0] -= Math.floor(this.scanned_numbers[0]);
            for (int index = 0; index < 3; ++index) {
                if (0.9999 < this.scanned_numbers[index]) {
                    this.scanned_numbers[index] = 0.9999;
                }
                if (1.0E-4 > this.scanned_numbers[index]) {
                    this.scanned_numbers[index] = 1.0E-4;
                }
            }
            this.scanned_color = Color.getHSBColor((float)this.scanned_numbers[0], (float)this.scanned_numbers[1], (float)this.scanned_numbers[2]);
        }
        else if (this.scanned_identifier.equals(Parser.name_GrayLevel)) {
            if (!this.scan_numbers(true, false) || 1 != this.count_scanned_numbers) {
                return false;
            }
            this.scanned_color = new Color((float)this.scanned_numbers[0], (float)this.scanned_numbers[0], (float)this.scanned_numbers[0]);
        }
        else if (this.scanned_identifier.equals(Parser.name_CMYKColor)) {
            if (!this.scan_numbers(true, false) || 4 != this.count_scanned_numbers) {
                return false;
            }
            this.scanned_color = new Color((float)Math.max(0.0, 1.0 - this.scanned_numbers[0] - this.scanned_numbers[3]), (float)Math.max(0.0, 1.0 - this.scanned_numbers[1] - this.scanned_numbers[3]), (float)Math.max(0.0, 1.0 - this.scanned_numbers[2] - this.scanned_numbers[3]));
        }
        return true;
    }
    
    public boolean scan_text(final Primitive3D primitive, final boolean salt_follows) {
        primitive.text = Parser.name_Questionmark;
        primitive.front_face_color = null;
        primitive.back_face_color = null;
        primitive.is_filled = false;
        primitive.is_outlined = false;
        if (22 == this.scanned_token_type) {
            primitive.text = String.valueOf(this.scanned_number);
        }
        else if (23 == this.scanned_token_type) {
            primitive.text = this.scanned_string;
        }
        else if (21 == this.scanned_token_type) {
            if (!this.scanned_identifier.equals(Parser.name_StyleForm)) {
                primitive.text = this.scanned_identifier;
                if (null != this.evaluator && this.evaluator.getVariableIndex(primitive.text) >= 0) {
                    primitive.is_outlined = true;
                }
            }
            else {
                this.scan_token();
                if (3 != this.scanned_token_type) {
                    return false;
                }
                this.scan_token();
                if (22 == this.scanned_token_type) {
                    primitive.text = String.valueOf(this.scanned_number);
                }
                else if (23 == this.scanned_token_type) {
                    primitive.text = this.scanned_string;
                }
                else if (21 == this.scanned_token_type) {
                    primitive.text = this.scanned_identifier;
                    if (null != this.evaluator && this.evaluator.getVariableIndex(primitive.text) >= 0) {
                        primitive.is_outlined = true;
                    }
                }
                this.scan_salt();
                this.scan_token();
                if (4 == this.scanned_token_type) {
                    primitive.font_url = null;
                    primitive.font = null;
                    primitive.font_weight = -1;
                    primitive.font_slant = -1;
                    primitive.font_size = -1;
                    primitive.front_face_color = null;
                    primitive.back_face_color = null;
                }
                else {
                    if (0 != this.scanned_token_type) {
                        return false;
                    }
                    if (!this.scan_font_options() || 4 != this.scanned_token_type) {
                        return false;
                    }
                    primitive.font_url = this.scanned_font_url;
                    primitive.font = this.scanned_font_family;
                    primitive.font_weight = this.scanned_font_weight;
                    primitive.font_slant = this.scanned_font_slant;
                    primitive.font_size = this.scanned_font_size;
                    primitive.front_face_color = this.scanned_font_color;
                    primitive.back_face_color = this.scanned_font_background;
                }
            }
        }
        if (salt_follows) {
            this.scan_salt();
        }
        return true;
    }
    
    boolean scan_font_options() {
        this.scanned_font_url = null;
        this.scanned_font_family = null;
        this.scanned_font_weight = -1;
        this.scanned_font_slant = -1;
        this.scanned_font_size = -1;
        this.scanned_font_color = null;
        this.scanned_font_background = null;
        this.scan_token();
        while (21 == this.scanned_token_type) {
            if (this.scanned_identifier.equals(Parser.name_URL)) {
                this.scan_token();
                if (20 != this.scanned_token_type) {
                    return false;
                }
                this.scan_token();
                if (23 != this.scanned_token_type) {
                    return false;
                }
                this.scanned_font_url = this.scanned_string;
                this.scan_token();
            }
            else if (this.scanned_identifier.equals(Parser.name_FontFamily)) {
                this.scan_token();
                if (20 != this.scanned_token_type) {
                    return false;
                }
                this.scan_token();
                if (23 != this.scanned_token_type) {
                    return false;
                }
                this.scanned_font_family = this.scanned_string;
                this.scan_token();
            }
            else if (this.scanned_identifier.equals(Parser.name_FontWeight)) {
                this.scan_token();
                if (20 != this.scanned_token_type) {
                    return false;
                }
                this.scan_token();
                if (23 != this.scanned_token_type) {
                    return false;
                }
                if (this.scanned_string.equals(Parser.name_Bold)) {
                    this.scanned_font_weight = 1;
                }
                else {
                    this.scanned_font_weight = 0;
                }
                this.scan_token();
            }
            else if (this.scanned_identifier.equals(Parser.name_FontSlant)) {
                this.scan_token();
                if (20 != this.scanned_token_type) {
                    return false;
                }
                this.scan_token();
                if (23 != this.scanned_token_type) {
                    return false;
                }
                if (this.scanned_string.equals(Parser.name_Italic)) {
                    this.scanned_font_slant = 2;
                }
                else {
                    this.scanned_font_slant = 0;
                }
                this.scan_token();
            }
            else if (this.scanned_identifier.equals(Parser.name_FontSize)) {
                this.scan_token();
                if (20 != this.scanned_token_type) {
                    return false;
                }
                this.scan_token();
                if (22 != this.scanned_token_type) {
                    return false;
                }
                this.scanned_font_size = (int)this.scanned_number;
                this.scan_token();
            }
            else if (this.scanned_identifier.equals(Parser.name_FontColor)) {
                this.scan_token();
                if (20 != this.scanned_token_type) {
                    return false;
                }
                this.scan_token();
                if (21 != this.scanned_token_type) {
                    return false;
                }
                if (!this.scan_color()) {
                    return false;
                }
                this.scanned_font_color = this.scanned_color;
                this.scan_token();
            }
            else if (this.scanned_identifier.equals(Parser.name_Background)) {
                this.scan_token();
                if (20 != this.scanned_token_type) {
                    return false;
                }
                this.scan_token();
                if (21 != this.scanned_token_type) {
                    return false;
                }
                if (!this.scan_color()) {
                    return false;
                }
                this.scanned_font_background = this.scanned_color;
                this.scan_token();
            }
            else {
                this.scan_salt();
                this.scan_token();
            }
            if (0 != this.scanned_token_type) {
                break;
            }
            this.scan_token();
        }
        return true;
    }
    
    boolean scan_option() {
        this.scanned_nothing = false;
        this.scanning_FaceForm_back = false;
        this.scan_token();
        if (5 == this.scanned_token_type) {
            while (this.scan_option()) {
                this.scan_token();
                if (0 != this.scanned_token_type) {
                    break;
                }
            }
            return 6 == this.scanned_token_type;
        }
        if (21 != this.scanned_token_type) {
            this.scanned_nothing = true;
            return false;
        }
        if (!this.is_scanned_AmbientLight && this.scanned_identifier.equals(Parser.name_AmbientLight)) {
            this.is_scanned_AmbientLight = true;
            this.scan_token();
            if (20 != this.scanned_token_type) {
                return false;
            }
            final Primitive3D primitive = new Primitive3D();
            this.scanning_FaceForm = true;
            if (!this.scan_primitive(primitive)) {
                return this.scanning_FaceForm = false;
            }
            this.graphics.option_AmbientLight = primitive.front_face_color;
            this.scanning_FaceForm = false;
            return true;
        }
        else if (!this.is_scanned_Axes && this.scanned_identifier.equals(Parser.name_Axes)) {
            this.is_scanned_Axes = true;
            this.scan_token();
            if (20 != this.scanned_token_type) {
                return false;
            }
            this.scan_token();
            if (21 == this.scanned_token_type) {
                if (this.scanned_identifier.equals(Parser.name_True)) {
                    this.graphics.option_Axes[0] = true;
                    this.graphics.option_Axes[1] = true;
                    this.graphics.option_Axes[2] = true;
                }
                return true;
            }
            if (5 == this.scanned_token_type) {
                int index = 0;
                this.scanned_token_type = 0;
                while (index < 3 && 0 == this.scanned_token_type) {
                    this.scan_token();
                    if (21 == this.scanned_token_type) {
                        if (this.scanned_identifier.equals(Parser.name_True)) {
                            this.graphics.option_Axes[index] = true;
                        }
                        ++index;
                        this.scan_token();
                    }
                }
                if (6 == this.scanned_token_type) {
                    return true;
                }
            }
            return false;
        }
        else if (!this.is_scanned_AxesLabel && this.scanned_identifier.equals(Parser.name_AxesLabel)) {
            this.is_scanned_AxesLabel = true;
            this.scan_token();
            if (20 != this.scanned_token_type) {
                return false;
            }
            this.scan_token();
            int index = 2;
            boolean is_list = false;
            if (5 == this.scanned_token_type) {
                index = 0;
                is_list = true;
                this.scan_token();
            }
            else if (21 == this.scanned_token_type && this.scanned_identifier.equals(Parser.name_None)) {
                return true;
            }
            do {
                if (index > 0 && is_list) {
                    this.scan_token();
                }
                if (index > 2) {
                    return false;
                }
                if (21 != this.scanned_token_type || !this.scanned_identifier.equals(Parser.name_None)) {
                    final Primitive3D primitive2 = new Primitive3D();
                    this.graphics.option_AxesLabel[index] = primitive2;
                    if (!this.scan_text(primitive2, false)) {
                        return false;
                    }
                }
                if (!is_list) {
                    continue;
                }
                ++index;
                this.scan_token();
            } while (is_list && 0 == this.scanned_token_type);
            return !is_list || 6 == this.scanned_token_type;
        }
        else if (!this.is_scanned_AxesStyle && this.scanned_identifier.equals(Parser.name_AxesStyle)) {
            this.is_scanned_AxesStyle = true;
            this.scan_token();
            if (20 != this.scanned_token_type) {
                return false;
            }
            Primitive3D primitive = new Primitive3D();
            this.scanning_EdgeForm = true;
            this.scanning_AxesStyles = true;
            this.scanned_AxesStyles[0] = null;
            this.scanned_AxesStyles[1] = null;
            this.scanned_AxesStyles[2] = null;
            if (!this.scan_primitive(primitive)) {
                this.scanning_EdgeForm = false;
                return this.scanning_AxesStyles = false;
            }
            this.scanning_EdgeForm = false;
            this.scanning_AxesStyles = false;
            if (null == this.scanned_AxesStyles[2]) {
                this.scanned_AxesStyles[0] = primitive;
                this.scanned_AxesStyles[1] = primitive;
                this.scanned_AxesStyles[2] = primitive;
            }
            for (int index2 = 0; index2 < 3; ++index2) {
                primitive = this.scanned_AxesStyles[index2];
                if (null != primitive) {
                    primitive.original_thickness = primitive.original_edge_thickness;
                    primitive.is_absolute_thickness = primitive.is_absolute_edge_thickness;
                    primitive.standard_color = primitive.edge_color;
                }
                this.graphics.option_AxesStyle[index2] = primitive;
            }
            return true;
        }
        else if (!this.is_scanned_AxesEdge && this.scanned_identifier.equals(Parser.name_AxesEdge)) {
            this.is_scanned_AxesEdge = true;
            this.scan_token();
            if (20 != this.scanned_token_type) {
                return false;
            }
            this.scan_token();
            if (21 == this.scanned_token_type) {
                if (!this.scanned_identifier.equals(Parser.name_Automatic)) {
                    this.graphics.option_AxesEdge[0] = -2;
                    this.graphics.option_AxesEdge[1] = -2;
                    this.graphics.option_AxesEdge[2] = -2;
                }
                return true;
            }
            if (5 != this.scanned_token_type) {
                return false;
            }
            int index = 0;
            do {
                this.scan_token();
                if (21 == this.scanned_token_type) {
                    if (!this.scanned_identifier.equals(Parser.name_Automatic)) {
                        this.graphics.option_AxesEdge[index] = -2;
                    }
                }
                else {
                    if (5 != this.scanned_token_type) {
                        return false;
                    }
                    this.scan_token();
                    if (22 != this.scanned_token_type) {
                        return false;
                    }
                    int edge_code = 0;
                    if (this.scanned_number > 0.0) {
                        edge_code = 1;
                    }
                    this.scan_token();
                    if (0 != this.scanned_token_type) {
                        return false;
                    }
                    this.scan_token();
                    if (22 != this.scanned_token_type) {
                        return false;
                    }
                    if (this.scanned_number > 0.0) {
                        edge_code += 2;
                    }
                    this.scan_token();
                    if (6 != this.scanned_token_type) {
                        return false;
                    }
                    this.graphics.option_AxesEdge[index] = edge_code;
                }
                ++index;
                this.scan_token();
            } while (index < 3 && 0 == this.scanned_token_type);
            return 6 == this.scanned_token_type;
        }
        else if (!this.is_scanned_Ticks && this.scanned_identifier.equals(Parser.name_Ticks)) {
            this.is_scanned_Ticks = true;
            this.scan_token();
            if (20 != this.scanned_token_type) {
                return false;
            }
            this.scan_token();
            if (21 == this.scanned_token_type) {
                if (this.scanned_identifier.equals(Parser.name_None)) {
                    this.graphics.option_Ticks = new Vector[3];
                    return true;
                }
                if (this.scanned_identifier.equals(Parser.name_Automatic)) {
                    this.graphics.option_Ticks = new Vector[3];
                    for (int index = 0; index < 3; ++index) {
                        this.graphics.option_Ticks[index] = new Vector();
                    }
                    return true;
                }
                return false;
            }
            else {
                if (5 == this.scanned_token_type) {
                    this.graphics.option_Ticks = new Vector[3];
                    for (int index = 0; index < 3; ++index) {
                        this.graphics.option_Ticks[index] = null;
                    }
                    int axis_index = 0;
                    this.scanned_token_type = 0;
                    while (0 == this.scanned_token_type) {
                        this.scan_token();
                        if (21 == this.scanned_token_type) {
                            if (this.scanned_identifier.equals(Parser.name_Automatic)) {
                                this.graphics.option_Ticks[axis_index] = new Vector();
                            }
                            else if (!this.scanned_identifier.equals(Parser.name_None)) {
                                return false;
                            }
                        }
                        else {
                            if (5 != this.scanned_token_type) {
                                return false;
                            }
                            this.graphics.option_Ticks[axis_index] = new Vector();
                            this.scanned_token_type = 0;
                            while (0 == this.scanned_token_type) {
                                final Primitive3D primitive3 = new Primitive3D();
                                primitive3.front_specular_exponent = 0.01;
                                primitive3.back_specular_exponent = 0.0;
                                this.scan_token();
                                if (22 == this.scanned_token_type) {
                                    primitive3.original_point_size = this.scanned_number;
                                    primitive3.text = Double.toString(this.scanned_number);
                                }
                                else {
                                    if (5 != this.scanned_token_type) {
                                        return false;
                                    }
                                    this.scan_token();
                                    if (22 != this.scanned_token_type) {
                                        return false;
                                    }
                                    final double tick_mark_position = this.scanned_number;
                                    this.scan_token();
                                    if (0 == this.scanned_token_type) {
                                        this.scan_token();
                                        if (!this.scan_text(primitive3, false)) {
                                            return false;
                                        }
                                        this.scan_token();
                                        if (0 == this.scanned_token_type) {
                                            this.scan_token();
                                            if (22 == this.scanned_token_type) {
                                                primitive3.front_specular_exponent = this.scanned_number / 2.0;
                                                primitive3.back_specular_exponent = this.scanned_number / 2.0;
                                            }
                                            else {
                                                if (5 != this.scanned_token_type) {
                                                    return false;
                                                }
                                                this.scan_token();
                                                if (22 != this.scanned_token_type) {
                                                    return false;
                                                }
                                                primitive3.front_specular_exponent = this.scanned_number;
                                                this.scan_token();
                                                if (0 != this.scanned_token_type) {
                                                    return false;
                                                }
                                                this.scan_token();
                                                if (22 != this.scanned_token_type) {
                                                    return false;
                                                }
                                                primitive3.back_specular_exponent = this.scanned_number;
                                                this.scan_token();
                                                if (6 != this.scanned_token_type) {
                                                    return false;
                                                }
                                            }
                                            this.scan_token();
                                            if (0 == this.scanned_token_type) {
                                                this.scanning_EdgeForm = true;
                                                if (!this.scan_primitive(primitive3)) {
                                                    return this.scanning_EdgeForm = false;
                                                }
                                                primitive3.original_thickness = primitive3.original_edge_thickness;
                                                primitive3.is_absolute_thickness = primitive3.is_absolute_edge_thickness;
                                                primitive3.standard_color = primitive3.edge_color;
                                                this.scanning_EdgeForm = false;
                                                this.scan_token();
                                            }
                                        }
                                    }
                                    else {
                                        primitive3.text = Double.toString(tick_mark_position);
                                    }
                                    primitive3.original_point_size = tick_mark_position;
                                    if (6 != this.scanned_token_type) {
                                        return false;
                                    }
                                }
                                if (this.graphics.ticks_max_in_length[axis_index] < primitive3.front_specular_exponent) {
                                    this.graphics.ticks_max_in_length[axis_index] = primitive3.front_specular_exponent;
                                }
                                if (this.graphics.ticks_max_out_length[axis_index] < primitive3.back_specular_exponent) {
                                    this.graphics.ticks_max_out_length[axis_index] = primitive3.back_specular_exponent;
                                }
                                this.graphics.option_Ticks[axis_index].addElement(primitive3);
                                this.scan_token();
                            }
                            if (6 != this.scanned_token_type) {
                                return false;
                            }
                        }
                        ++axis_index;
                        this.scan_token();
                    }
                    return 6 == this.scanned_token_type;
                }
                return false;
            }
        }
        else if (!this.is_scanned_PlotLabel && this.scanned_identifier.equals(Parser.name_PlotLabel)) {
            this.is_scanned_PlotLabel = true;
            this.scan_token();
            if (20 != this.scanned_token_type) {
                return false;
            }
            this.scan_token();
            if (21 == this.scanned_token_type && this.scanned_identifier.equals(Parser.name_None)) {
                return true;
            }
            final Primitive3D primitive = new Primitive3D();
            this.graphics.option_PlotLabel = primitive;
            return this.scan_text(primitive, false);
        }
        else if (!this.is_scanned_Background && this.scanned_identifier.equals(Parser.name_Background)) {
            this.is_scanned_Background = true;
            this.scan_token();
            if (20 != this.scanned_token_type) {
                return false;
            }
            final Primitive3D primitive = new Primitive3D();
            this.scanning_FaceForm = true;
            if (!this.scan_primitive(primitive)) {
                return this.scanning_FaceForm = false;
            }
            this.graphics.option_Background = primitive.front_face_color;
            this.scanning_FaceForm = false;
            return true;
        }
        else if (!this.is_scanned_DefaultColor && this.scanned_identifier.equals(Parser.name_DefaultColor)) {
            this.is_scanned_DefaultColor = true;
            this.scan_token();
            if (20 != this.scanned_token_type) {
                return false;
            }
            final Primitive3D primitive = new Primitive3D();
            this.scanning_FaceForm = true;
            if (!this.scan_primitive(primitive)) {
                return this.scanning_FaceForm = false;
            }
            this.graphics.option_DefaultColor = primitive.front_face_color;
            this.scanning_FaceForm = false;
            return true;
        }
        else if (!this.is_scanned_BoxStyle && this.scanned_identifier.equals(Parser.name_BoxStyle)) {
            this.is_scanned_BoxStyle = true;
            this.scan_token();
            if (20 != this.scanned_token_type) {
                return false;
            }
            final Primitive3D primitive = new Primitive3D();
            this.scanning_EdgeForm = true;
            if (!this.scan_primitive(primitive)) {
                return this.scanning_EdgeForm = false;
            }
            primitive.original_thickness = primitive.original_edge_thickness;
            primitive.is_absolute_thickness = primitive.is_absolute_edge_thickness;
            primitive.standard_color = primitive.edge_color;
            this.graphics.option_BoxStyle = primitive;
            this.scanning_EdgeForm = false;
            return true;
        }
        else if (!this.is_scanned_Boxed && this.scanned_identifier.equals(Parser.name_Boxed)) {
            this.is_scanned_Boxed = true;
            this.scan_token();
            if (20 != this.scanned_token_type) {
                return false;
            }
            this.scan_token();
            if (21 == this.scanned_token_type) {
                if (this.scanned_identifier.equals(Parser.name_False)) {
                    this.graphics.option_Boxed = false;
                }
                return true;
            }
            return false;
        }
        else if (!this.is_scanned_Lighting && this.scanned_identifier.equals(Parser.name_Lighting)) {
            this.is_scanned_Lighting = true;
            this.scan_token();
            if (20 != this.scanned_token_type) {
                return false;
            }
            this.scan_token();
            if (21 == this.scanned_token_type) {
                if (this.scanned_identifier.equals(Parser.name_False)) {
                    this.graphics.option_Lighting = false;
                }
                return true;
            }
            return false;
        }
        else if (!this.is_scanned_BoxRatios && this.scanned_identifier.equals(Parser.name_BoxRatios)) {
            this.is_scanned_BoxRatios = true;
            this.scan_token();
            if (20 != this.scanned_token_type) {
                return false;
            }
            if (this.scan_numbers(false, true) && 3 == this.count_scanned_numbers) {
                (this.graphics.option_BoxRatios = new double[3])[0] = this.scanned_numbers[0];
                this.graphics.option_BoxRatios[1] = this.scanned_numbers[1];
                this.graphics.option_BoxRatios[2] = this.scanned_numbers[2];
                return true;
            }
            this.scan_salt();
            return true;
        }
        else if (!this.is_scanned_PlotRange && this.scanned_identifier.equals(Parser.name_PlotRange)) {
            this.is_scanned_PlotRange = true;
            this.scan_token();
            if (20 != this.scanned_token_type) {
                return false;
            }
            this.scan_token();
            if (5 != this.scanned_token_type) {
                this.scan_salt();
                return true;
            }
            final double[][] new_option_PlotRange = { { Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY }, { Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY }, { Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY } };
            int axes_index = 0;
            boolean is_only_z = false;
            this.scan_token();
            if (22 == this.scanned_token_type) {
                axes_index = 2;
                is_only_z = true;
            }
            else if (5 == this.scanned_token_type) {
                this.scan_token();
            }
            else if (21 != this.scanned_token_type) {
                return false;
            }
            while (axes_index < 3) {
                if (21 != this.scanned_token_type) {
                    for (int index3 = 0; index3 < 2; ++index3) {
                        if (22 != this.scanned_token_type) {
                            return false;
                        }
                        new_option_PlotRange[axes_index][index3] = this.scanned_number;
                        this.scan_token();
                        if (0 == index3) {
                            this.scan_token();
                        }
                    }
                    if (6 != this.scanned_token_type) {
                        return false;
                    }
                }
                if (!is_only_z) {
                    this.scan_token();
                    if (axes_index < 2) {
                        this.scan_token();
                        if (5 == this.scanned_token_type) {
                            this.scan_token();
                        }
                    }
                }
                ++axes_index;
            }
            if (6 != this.scanned_token_type) {
                return false;
            }
            this.graphics.option_PlotRange = new_option_PlotRange;
            return true;
        }
        else if (!this.is_scanned_LightSources && this.scanned_identifier.equals(Parser.name_LightSources)) {
            this.is_scanned_LightSources = true;
            this.scan_token();
            if (20 != this.scanned_token_type) {
                return false;
            }
            this.scan_token();
            if (5 != this.scanned_token_type) {
                this.scan_salt();
                return true;
            }
            this.graphics.option_LightSources_vectors = new Vector();
            this.graphics.option_LightSources_colors = new Vector();
            this.scanned_token_type = 0;
            while (0 == this.scanned_token_type) {
                final double[] new_light_vector = new double[3];
                this.scan_token();
                if (5 != this.scanned_token_type) {
                    return false;
                }
                if (!this.scan_numbers(false, true) || 3 != this.count_scanned_numbers) {
                    return false;
                }
                final double vector_length = Math.sqrt(this.scanned_numbers[0] * this.scanned_numbers[0] + this.scanned_numbers[1] * this.scanned_numbers[1] + this.scanned_numbers[2] * this.scanned_numbers[2]);
                new_light_vector[0] = this.scanned_numbers[0] / vector_length;
                new_light_vector[1] = this.scanned_numbers[1] / vector_length;
                new_light_vector[2] = this.scanned_numbers[2] / vector_length;
                this.scan_token();
                if (0 != this.scanned_token_type) {
                    return false;
                }
                final Primitive3D primitive4 = new Primitive3D();
                this.scanning_FaceForm = true;
                if (!this.scan_primitive(primitive4) || null == primitive4.front_face_color) {
                    return this.scanning_FaceForm = false;
                }
                this.scanning_FaceForm = false;
                this.graphics.option_LightSources_vectors.addElement(new_light_vector);
                this.graphics.option_LightSources_colors.addElement(primitive4.front_face_color);
                this.scan_token();
                if (6 != this.scanned_token_type) {
                    return false;
                }
                this.scan_token();
            }
            return 6 == this.scanned_token_type;
        }
        else if (!this.is_scanned_ViewPoint && this.scanned_identifier.equals(Parser.name_ViewPoint)) {
            this.is_scanned_ViewPoint = true;
            this.scan_token();
            if (20 != this.scanned_token_type) {
                return false;
            }
            if (!this.scan_numbers(false, true) || 3 != this.count_scanned_numbers) {
                return false;
            }
            (this.graphics.option_ViewPoint = new double[3])[0] = this.scanned_numbers[0];
            this.graphics.option_ViewPoint[1] = this.scanned_numbers[1];
            this.graphics.option_ViewPoint[2] = this.scanned_numbers[2];
            return true;
        }
        else if (!this.is_scanned_ViewVertical && this.scanned_identifier.equals(Parser.name_ViewVertical)) {
            this.is_scanned_ViewVertical = true;
            this.scan_token();
            if (20 != this.scanned_token_type) {
                return false;
            }
            if (!this.scan_numbers(false, true) || 3 != this.count_scanned_numbers) {
                return false;
            }
            (this.graphics.option_ViewVertical = new double[3])[0] = this.scanned_numbers[0];
            this.graphics.option_ViewVertical[1] = this.scanned_numbers[1];
            this.graphics.option_ViewVertical[2] = this.scanned_numbers[2];
            return true;
        }
        else if (!this.is_scanned_TextStyle && this.scanned_identifier.equals(Parser.name_TextStyle)) {
            this.is_scanned_TextStyle = true;
            this.scan_token();
            if (20 != this.scanned_token_type) {
                return false;
            }
            this.scan_token();
            if (21 == this.scanned_token_type) {
                return true;
            }
            if (5 != this.scanned_token_type) {
                return false;
            }
            if (!this.scan_font_options() || 6 != this.scanned_token_type) {
                return false;
            }
            this.graphics.option_TextStyle_font_url = this.scanned_font_url;
            this.graphics.option_TextStyle_font_family = this.scanned_font_family;
            this.graphics.option_TextStyle_font_weight = this.scanned_font_weight;
            this.graphics.option_TextStyle_font_slant = this.scanned_font_slant;
            this.graphics.option_TextStyle_font_size = this.scanned_font_size;
            this.graphics.option_TextStyle_font_color = this.scanned_font_color;
            this.graphics.option_TextStyle_font_background = this.scanned_font_background;
            return true;
        }
        else if ((!this.is_scanned_AnimationDisplayTime || this.scanning_animation_option) && this.scanned_identifier.equals(Parser.name_AnimationDisplayTime)) {
            this.is_scanned_AnimationDisplayTime = true;
            this.scan_token();
            if (20 != this.scanned_token_type) {
                return false;
            }
            this.scan_token();
            if (22 == this.scanned_token_type) {
                this.graphics.option_AnimationDisplayTime = this.scanned_number;
                this.graphics.option_AnimationDisplayTimeString = null;
                return true;
            }
            if (21 == this.scanned_token_type && null != this.evaluator) {
                final int variable_index = this.evaluator.getVariableIndex(this.scanned_identifier);
                if (variable_index >= 0) {
                    this.graphics.option_AnimationDisplayTime = 0.05;
                    this.graphics.option_AnimationDisplayTimeString = this.scanned_identifier;
                    return true;
                }
            }
            return false;
        }
        else {
            if (!this.scanning_animation_option || !this.scanned_identifier.equals(Parser.name_AnimationDirection)) {
                this.scan_salt();
                return ',' == this.current_char || '}' == this.current_char || ']' == this.current_char;
            }
            this.scan_token();
            if (20 != this.scanned_token_type) {
                return false;
            }
            this.scan_token();
            if (21 != this.scanned_token_type) {
                return false;
            }
            if (this.scanned_identifier.equals(Parser.name_Forward)) {
                this.scanned_AnimationDirection = 1;
            }
            else if (this.scanned_identifier.equals(Parser.name_Backward)) {
                this.scanned_AnimationDirection = -1;
            }
            else if (this.scanned_identifier.equals(Parser.name_ForwardBackward)) {
                this.scanned_AnimationDirection = 0;
            }
            return true;
        }
    }
    
    public boolean scan_independent_variables() {
        if (null == this.evaluator) {
            return false;
        }
        this.evaluator.clearAllVariables();
        this.text_index = -1;
        this.scan_token();
        if (5 != this.scanned_token_type) {
            return false;
        }
        this.scanned_token_type = 0;
        while (0 == this.scanned_token_type) {
            this.scan_token();
            if (21 != this.scanned_token_type) {
                break;
            }
            final String variable_name = this.scanned_identifier;
            this.scan_token();
            if (20 != this.scanned_token_type) {
                return false;
            }
            this.scan_token();
            if (22 != this.scanned_token_type) {
                return false;
            }
            final double variable_value = this.scanned_number;
            if (!this.evaluator.addIndependentVariable(variable_name, variable_value)) {
                return false;
            }
            this.scan_token();
        }
        return 6 == this.scanned_token_type;
    }
    
    public boolean scan_dependent_variables() {
        if (null == this.evaluator) {
            return false;
        }
        this.text_index = -1;
        this.scan_token();
        if (5 != this.scanned_token_type) {
            return false;
        }
        this.scanned_token_type = 0;
        while (0 == this.scanned_token_type) {
            this.scan_token();
            if (21 != this.scanned_token_type) {
                break;
            }
            final String variable_name = this.scanned_identifier;
            this.scan_token();
            if (20 != this.scanned_token_type) {
                return false;
            }
            this.is_scanning_expression = true;
            this.is_scanning_after_operand = false;
            this.scan_token();
            if (!this.scan_expression(false)) {
                return false;
            }
            if (this.is_scanned_expression_numeric) {
                if (!this.evaluator.addDependentVariable(variable_name, this.scanned_number, -1)) {
                    return false;
                }
                continue;
            }
            else {
                if (!this.evaluator.addDependentVariable(variable_name, 0.0, this.scanned_expression)) {
                    return false;
                }
                continue;
            }
        }
        return 6 == this.scanned_token_type;
    }
    
    public int left_precedence(final int token) {
        switch (token) {
            case 0: {
                return 1;
            }
            case 35: {
                return 3;
            }
            case 36: {
                return 5;
            }
            case 33: {
                return 8;
            }
            case 32: {
                return 10;
            }
            case 34: {
                return 12;
            }
            case 14:
            case 15:
            case 16:
            case 17:
            case 18:
            case 19: {
                return 14;
            }
            case 7: {
                return 16;
            }
            case 8: {
                return 16;
            }
            case 9: {
                return 18;
            }
            case 10: {
                return 18;
            }
            case 11: {
                return 18;
            }
            case 27:
            case 28: {
                return 18;
            }
            case 12:
            case 13: {
                return 20;
            }
            case 30: {
                return 22;
            }
            case 1:
            case 3: {
                return 0;
            }
            case 2:
            case 4: {
                return 101;
            }
            case 21:
            case 22: {
                return 101;
            }
            case 24:
            case 26: {
                return 0;
            }
            default: {
                return -1;
            }
        }
    }
    
    public int right_precedence(final int token) {
        switch (token) {
            case 0: {
                return 2;
            }
            case 35: {
                return 4;
            }
            case 36: {
                return 6;
            }
            case 33: {
                return 7;
            }
            case 32: {
                return 9;
            }
            case 34: {
                return 13;
            }
            case 14:
            case 15:
            case 16:
            case 17:
            case 18:
            case 19: {
                return 15;
            }
            case 7: {
                return 15;
            }
            case 8: {
                return 15;
            }
            case 9: {
                return 17;
            }
            case 10: {
                return 17;
            }
            case 11: {
                return 19;
            }
            case 27:
            case 28: {
                return 19;
            }
            case 12:
            case 13: {
                return 19;
            }
            case 30: {
                return 23;
            }
            case 1:
            case 3: {
                return 100;
            }
            case 2:
            case 4: {
                return 0;
            }
            case 21:
            case 22: {
                return 100;
            }
            case 24:
            case 26: {
                return 0;
            }
            default: {
                return -1;
            }
        }
    }
    
    public boolean scan_expression(final boolean evaluate) {
        final Stack stack = new Stack();
        final Vector program = new Vector();
        int nesting = 0;
        int relation_operators_count = 0;
        boolean is_scanning_first_argument_of_set = false;
        Parser.arguments_counts[0] = 1;
        if (null == this.evaluator) {
            System.out.println("no evaluator in scan_expression!");
            return false;
        }
        this.is_scanning_expression = true;
        this.is_scanning_after_operand = false;
        this.scanned_expression = -1;
        this.is_scanned_expression_numeric = false;
        this.expression_token();
        int current_token = this.scanned_token_type;
        double current_value;
        if (21 == current_token) {
            current_value = 0.5 + this.evaluator.getVariableIndex(this.scanned_identifier);
            if (current_value < 0.0) {
                return this.is_scanning_expression = false;
            }
            this.is_scanning_after_operand = true;
        }
        else if (30 == current_token) {
            current_value = 0.5 + this.scanned_function_identifier;
            if (current_value < 0.0) {
                return this.is_scanning_expression = false;
            }
            if (113 == this.scanned_function_identifier) {
                is_scanning_first_argument_of_set = true;
            }
        }
        else if (22 == current_token) {
            if (this.scanned_number >= 0.0) {
                current_value = this.scanned_number;
                this.is_scanning_after_operand = true;
            }
            else {
                System.out.println("LiveGraphics3D: Internal scanning error! (resuming)");
                stack.push(new ExpressionElement(28, 0.0, nesting));
                current_value = -this.scanned_number;
                this.is_scanning_after_operand = true;
            }
        }
        else {
            current_value = 0.0;
        }
        if (1 == current_token || 3 == current_token) {
            ++nesting;
            Parser.arguments_counts[nesting] = 1;
        }
        else if (2 == current_token || 4 == current_token) {
            --nesting;
            if (nesting < 0) {
                return this.is_scanning_expression = false;
            }
        }
        else if (0 == current_token) {
            if (0 == Parser.arguments_counts[nesting]) {
                return this.is_scanning_expression = false;
            }
            ++Parser.arguments_counts[nesting];
        }
        else if (0 == Parser.arguments_counts[nesting]) {
            Parser.arguments_counts[nesting] = 1;
        }
        if ((nesting == 0 && 0 == current_token) || 6 == current_token) {
            current_token = 24;
        }
        if (this.left_precedence(current_token) < 0 || this.right_precedence(current_token) < 0) {
            return this.is_scanning_expression = false;
        }
        while (true) {
            int previous_token;
            if (!stack.empty()) {
                previous_token = stack.peek().token;
            }
            else {
                previous_token = 26;
            }
            if (previous_token == 24 && current_token == 24) {
                program.addElement(new ExpressionElement(24, 0.0, nesting));
                if (program.size() < 2) {
                    return this.is_scanning_expression = false;
                }
                if (2 == program.size() && 22 == program.elementAt(0).token) {
                    this.scanned_number = program.elementAt(0).value;
                    this.is_scanned_expression_numeric = true;
                }
                else if (3 == program.size() && 22 == program.elementAt(0).token && 28 == program.elementAt(1).token) {
                    this.scanned_number = -program.elementAt(0).value;
                    this.is_scanned_expression_numeric = true;
                }
                else if (3 == program.size() && 22 == program.elementAt(0).token && 27 == program.elementAt(1).token) {
                    this.scanned_number = program.elementAt(0).value;
                    this.is_scanned_expression_numeric = true;
                }
                else {
                    final int[] tokens_list = new int[program.size()];
                    final double[] values_list = new double[program.size()];
                    for (int index = 0; index < program.size(); ++index) {
                        tokens_list[index] = program.elementAt(index).token;
                        values_list[index] = program.elementAt(index).value;
                    }
                    this.scanned_expression = this.evaluator.addExpression(new Expression(this.evaluator, program.size(), tokens_list, values_list));
                    if (evaluate) {
                        if (!this.evaluator.getExpression(this.scanned_expression).evaluate()) {
                            return this.is_scanning_expression = false;
                        }
                        this.scanned_number = this.evaluator.getExpression(this.scanned_expression).current_value;
                    }
                    else {
                        this.scanned_number = 0.0;
                    }
                }
                this.is_scanning_expression = false;
                return true;
            }
            else if (this.left_precedence(previous_token) <= this.right_precedence(current_token)) {
                stack.push(new ExpressionElement(current_token, current_value, nesting));
                if (24 == current_token) {
                    continue;
                }
                this.scan_token();
                this.expression_token();
                if (36 == this.scanned_token_type && 21 != current_token) {
                    return this.is_scanning_expression = false;
                }
                current_token = this.scanned_token_type;
                if (21 == current_token) {
                    current_value = 0.5 + this.evaluator.getVariableIndex(this.scanned_identifier);
                    if (current_value < 0.0) {
                        return this.is_scanning_expression = false;
                    }
                    if (is_scanning_first_argument_of_set) {
                        current_token = 22;
                    }
                }
                else if (30 == current_token) {
                    current_value = 0.5 + this.scanned_function_identifier;
                    if (current_value < 0.0) {
                        return this.is_scanning_expression = false;
                    }
                    if (113 == this.scanned_function_identifier) {
                        is_scanning_first_argument_of_set = true;
                    }
                }
                else if (22 == current_token) {
                    current_value = this.scanned_number;
                }
                else if (36 == current_token) {
                    if (!this.evaluator.isVariableIndependent((int)current_value)) {
                        return this.is_scanning_expression = false;
                    }
                }
                else {
                    current_value = 0.0;
                }
                if (1 == current_token || 3 == current_token) {
                    ++nesting;
                    Parser.arguments_counts[nesting] = 0;
                }
                else if (2 == current_token || 4 == current_token) {
                    is_scanning_first_argument_of_set = false;
                    --nesting;
                    if (nesting < 0) {
                        return this.is_scanning_expression = false;
                    }
                }
                else if (0 == current_token) {
                    is_scanning_first_argument_of_set = false;
                    if (0 == Parser.arguments_counts[nesting]) {
                        return this.is_scanning_expression = false;
                    }
                    ++Parser.arguments_counts[nesting];
                }
                else if (0 == Parser.arguments_counts[nesting]) {
                    Parser.arguments_counts[nesting] = 1;
                }
                if ((nesting == 0 && 0 == current_token) || 6 == current_token) {
                    current_token = 24;
                }
                if (this.left_precedence(current_token) < 0 || this.right_precedence(current_token) < 0) {
                    return this.is_scanning_expression = false;
                }
                continue;
            }
            else {
                ExpressionElement top_element = null;
                do {
                    top_element = stack.pop();
                    if (top_element.token == 30) {
                        top_element.value += 1024 * Parser.arguments_counts[top_element.nesting + 1];
                    }
                    if (top_element.token != 1 && top_element.token != 2 && top_element.token != 3 && top_element.token != 4 && top_element.token != 0) {
                        program.addElement(top_element);
                    }
                    if (!stack.empty()) {
                        previous_token = stack.peek().token;
                    }
                    else {
                        previous_token = 26;
                    }
                    if (16 == top_element.token || 17 == top_element.token || 14 == top_element.token || 15 == top_element.token || 18 == top_element.token || 19 == top_element.token) {
                        ++relation_operators_count;
                        if (16 != previous_token && 17 != previous_token && 14 != previous_token && 15 != previous_token && 18 != previous_token && 19 != previous_token) {
                            program.addElement(new ExpressionElement(31, relation_operators_count, 0));
                        }
                    }
                    else {
                        relation_operators_count = 0;
                    }
                    if (26 == previous_token) {
                        break;
                    }
                } while (this.left_precedence(previous_token) >= this.right_precedence(top_element.token));
            }
        }
    }
    
    public void expression_token() {
        if (this.scanned_token_type != 21) {
            return;
        }
        if (this.scanned_left_bracket) {
            this.scanned_token_type = 30;
            this.is_scanning_after_operand = false;
            this.scanned_function_identifier = -1;
            for (int function_id = 0; function_id < 114; ++function_id) {
                if (this.scanned_identifier.equals(Parser.function_names[function_id])) {
                    this.scanned_function_identifier = function_id;
                    break;
                }
            }
            return;
        }
        if (this.scanned_identifier.equals(Parser.name_E)) {
            this.scanned_number = 2.718281828459045;
            this.scanned_token_type = 22;
            return;
        }
        if (this.scanned_identifier.equals(Parser.name_Pi)) {
            this.scanned_number = 3.141592653589793;
            this.scanned_token_type = 22;
            return;
        }
        if (this.scanned_identifier.equals(Parser.name_GoldenRatio)) {
            this.scanned_number = 1.618033988749895;
            this.scanned_token_type = 22;
            return;
        }
        if (this.scanned_identifier.equals(Parser.name_EulerGamma)) {
            this.scanned_number = 0.5772156649015329;
            this.scanned_token_type = 22;
            return;
        }
        if (this.scanned_identifier.equals(Parser.name_Degree)) {
            this.scanned_number = 0.017453292519943295;
            this.scanned_token_type = 22;
            return;
        }
        if (this.scanned_identifier.equals(Parser.name_Catalan)) {
            this.scanned_number = 0.915965594177219;
            this.scanned_token_type = 22;
            return;
        }
        if (this.scanned_identifier.equals(Parser.name_Khinchin)) {
            this.scanned_number = 2.6854520010653062;
            this.scanned_token_type = 22;
            return;
        }
        if (this.scanned_identifier.equals(Parser.name_Glaisher)) {
            this.scanned_number = 1.2824271291006226;
            this.scanned_token_type = 22;
            return;
        }
        if (this.scanned_identifier.equals(Parser.name_True)) {
            this.scanned_number = 1.0;
            this.scanned_token_type = 22;
            return;
        }
        if (this.scanned_identifier.equals(Parser.name_False)) {
            this.scanned_number = 0.0;
            this.scanned_token_type = 22;
            return;
        }
        if (this.scanned_identifier.equals(Parser.name_I) || this.scanned_identifier.equals(Parser.name_Infinity) || this.scanned_identifier.equals(Parser.name_Indeterminate) || this.scanned_identifier.equals(Parser.name_ComplexInfinity)) {
            this.scanned_number = Double.NaN;
            this.scanned_token_type = 22;
        }
    }
    
    static {
        Parser.single_quote_chars = "'´`'";
        Parser.breaking_chars = " \r\n\t\\";
        Parser.white_characters = " \t\r\n\f";
        Parser.comment_end = "*)";
        Parser.standard_parts = new String[] { "Capital", "Script", "Gothic", "Not", "DoubleStruck", "Doubled", "Double", "Filled", "Left", "Right", "" };
        Parser.standard_short_parts = new String[] { "c", "s", "g", "n", "t", "v", "w", "f", "l", "r" };
        Parser.coded_char_names = "0AA225AB257AC259AD228AE230AG224AH226Ak63272Ale8501AliasD63332AliasI63336Alig63328Alp945Alt63441Andy63273And8743Angl8736Angs8491AR229As8944AT227AutoL62376AutoO62382AutoP62372AutoR62377AutoS62381Ba8726Bec8757Beta946Bet8502Br774Bu8226CAc263Cap8994cAA193cAB256cAC258cAD196cAE198cAG192cAH194cAl913cAR197cAT195cB914cCA262cCC199cCH268cCh935cDe916cDif63307cDig988cEA201cEB274cEC276cED203cEG200cEH202cEp917cEta919cEth208cG915cIA205cIC300cID207cIG204cIH206cIo921cKa922cKo990cLa923cLS321cM924cNT209cNu925cOA211cODoubleA336cODoubleD214cOG210cOH212cOme937cOmi927cOS216cOT213cPh934cPi928cPs936cR929cSa992cSH352cSi931cSt986cTa932cThe920cTho222cUA218cUDoubleA368cUDoubleD220cUG217cUH219cUp933cX926cY221cZ918CC231Ced807CenterD183CenterE8943Cent162CH269Ch967CircleD8857CircleM8854CircleP8853CircleT8855Cloc8754CloseCurlyD8221CloseCurlyQ8217Clov8984Clu9827Col8758Com63338Cong8801Conti62385Conto8750Contr63331Copr8720Copy169Cou8755Cr62624CupC8781Cup8995CurlyCapitalU978CurlyE603CurlyK1008CurlyPh981CurlyPi982CurlyR1009CurlyT977Curr164Dag8224Dal8504Das8211Deg176Dele63440Delt948Del8711Des8945Diame62468Diamond62753DiamondS9826Dif63308Dig63249Div247DotE8784DotlessI305DotlessJ63232Dott63313wC8751wDa8225vG63306wDot776wDow8659vP63305wlA8656wlr8660wlT62467wLongLeftA62720wLongLeftR62722wLongRightA62721wP8243wrA8658wrT8872tA63206tB63207tC63208tcA63396tcB63397tcC63398tcD63399tcE63400tcF63401tcG63402tcH63403tcI63404tcJ63405tcK63406tcL63407tcM63408tcN63409tcO63410tcP63411tcQ63412tcR63413tcS63414tcT63415tcU63416tcV63417tcW63418tcX63419tcY63420tcZ63421tD63209tE63210tF63211tG63212tH63213tI63214tJ63215tK63216tL63217tM63218tN63219tO63220tP63221tQ63222tR63223tS63224tT63225tU63226tV63227tW63228tX63229tY63230tZ63231wUpA8657wUpD8661wV8741DownArrowB62724DownArrowU62726DownArrow8595DownB785DownE161DownLeftR62731DownLeftT62734DownLeftVectorB62732DownLeftVector8637DownQ191DownRightT62735DownRightVectorB62733DownRightVector8641DownTeeA8615DownTee8868EA233EB275EC277ED235EG232EH234Ele8712Ell8230EmptyC9675EmptyDi9671EmptyDo9661EmptyR9647EmptySe8709EmptySmallC9702EmptySmallS62759EmptySq9635EmptyU9651EmptyV62768En63444Ep949EqualT8770Equal62513Equi8652Er63335Es63337Eta951Eth240Exi8707Exp63309Fe63274Fi63233fC9679fDi9670fDo9660fR9646fSmallC63312fSmallS62760fS9634fU9650fV62761Fin962Fiv9733Fla9837FlL63234Flo402Fo8704Fr63265Ga947Gi8503gA63180gB63181gC63182gcA63370gcB63371gcC63372gcD63373gcE63374gcF63375gcG63376gcH63377gcI63378gcJ63379gcK63380gcL63381gcM63382gcN63383gcO63384gcP63385gcQ63386gcR63387gcS63388gcT63389gcU63390gcV63391gcW63392gcX63393gcY63394gcZ63395gD63183gE63184gF63185gG63186gH63187gI63188gJ63189gK63190gL63191gM63192gN63193gO63194gP63195gQ63196gR63197gS63198gT63199gU63200gV63201gW63202gX63203gY63204gZ63205GrayC63315GrayS63314GreaterEqualL8923GreaterEqual8805GreaterF8807GreaterG8811GreaterL8823GreaterS62502GreaterT8819Hac780Hap9786HB8463He9825Ho62977HumpD8782HumpE8783Hy173IA237IC301ID239IG236IH238ImaginaryI63310ImaginaryJ63311Imp62755Ind62371Inf8734Integ8747Inter8898InvisibleC63333InvisiblePo62388InvisiblePr62387InvisibleS62304Io953Ka954Ker63318Key63443Ko63250La955lAn9001lArrowB8676lArrowR8646lArrow8592lB62979lC8968lwBracketi62981lwBracket12314lDownT62745lDownVectorB62743lDownVector8643lF8970lG171lM63339lrA8596lrV62725lS63329lTeeA8612lTeeV62729lTee8867lTriangleB62480lTriangleE8884lTriangle8882lUpD62741lUpT62744lUpVectorB62742lUpVector8639lVectorB62727lVector8636LessEqualG8922LessEqual8804LessF8806LessG8822LessL8810LessS62496LessT8818Li63267LongD8212LongLeftA62748LongLeftR62750LongR62749LowerL8601LowerR8600LS322Ma63319Mea8737Med62308Mh8487Mic181Min8723Mo63446Mu956Na9838NegativeM62339NegativeThic62340NegativeThin62338NegativeV62336NestedG62501NestedL62497Neu63266NoB62370Non160nCo8802nCu8813nw8742nEl8713nEqualT62464nEqual8800nEx8708nGreaterE8817nGreaterF8809nGreaterG62503nGreaterL8825nGreaterS62505nGreaterT8821nGreater8815nHumpD62466nHumpE62465nlTriangleB62482nlTriangleE8940nlTriangle8938nLessE8816nLessF8808nLessG8824nLessL62498nLessS62500nLessT8820nLess8814nNestedG62504nNestedL62499nPrecedesE8928nPrecedesS62507nPrecedesT8936nPrecedes8832nRe8716nrTriangleB62483nrTriangleE8941nrTriangle8939nSquareSubsetE8930nSquareSubset62510nSquareSupersetE8931nSquareSuperset62511nSubsetE8840nSubset8836nSucceedsE8929nSucceedsS62509nSucceedsT8937nSucceeds8833nSupersetE8841nSuperset8837nTildeE8772nTildeF8775nTildeT8777nTilde8769nV8740NT241Nul62368Num63268Nu957OA243ODoubleA337ODoubleD246OG242OH244Ome969Omi959OpenCurlyD8220OpenCurlyQ8216Opt63442Or8744OS248OT245OverBrace62994OverBrack62996OverP62992Para182Part8706Ph966Pi960Pla9633Plu177PrecedesE8828PrecedesS62506PrecedesT8830Precedes8826Pri8242Prod8719Proportiona8733Proportion8759Ps968Reg174ReturnI8629ReturnK63334ReverseD8245ReverseEl8715ReverseEq8651ReverseP8244ReverseU62747Rh961rAngleB9002rAngle8735rArrowB8677rArrowL8644rArrow8594rB62980rC8969rwBracketi62982rwBracket12315rDownT62740rDownVectorB62738rDownVector8642rF8971rG187rM63340rS63330rTeeA8614rTeeV62730rTee8866rTriangleB62481rTriangleE8885rTriangle8883rUpD62736rUpT62739rUpVectorB62737rUpVector8638rVectorB62728rVector8640RoundI62756RoundS62386RuleD62751Rule62754Sad9785Sam63251sA63154sB63155sC63156scA63344scB63345scC63346scD63347scE63348scF63349scG63350scH63351scI63352scJ63353scK63354scL63355scM63356scN63357scO63358scP63359scQ63360scR63361scS63362scT63363scU63364scV63365scW63366scX63367scY63368scZ63369sDotlessI63280sDotlessJ63281sD63157sE63158sF63159sG63160sH63161sI63162sJ63163sK63164sL63165sM63166sN63167sO63168sP63169sQ63170sR63171sS63172sT63173sU63174sV63175sW63176sX63177sY63178sZ63179Sec167Sel9632SH353Sha9839Shi63445ShortD62763ShortL62758ShortR62757ShortU62762Sig963Six63317Sk8259Sm8728SpaceI9251SpaceK63423Spa9824Sp8738Sqr8730SquareI8851SquareSubsetE8849SquareSubset8847SquareSupersetE8850SquareSuperset8848SquareU8852Square62752Sta8902Ste163Sti63248SubsetE8838Subset8834SucceedsE8829SucceedsS62508SucceedsT8831Succeeds8827Such8717Sum8721SupersetE8839Superset8835SZ223Tab63422Tau964Ther8756Thet952Thick62309Thin62307Tho254TildeE8771TildeF8773TildeT8776Tilde8764Tim215Tra63270Tri8411UA250UDoubleA369UDoubleD252UG249UH251UnderBrace62995UnderBrack62997UnderP62993UnionP8846Union8899Unk65533UpArrowB62723UpArrowD8645UpArrow8593UpD8597UpE62746UpperL8598UpperR8599Ups965UpTeeA8613UpTee8869Vee8897VerticalB8739VerticalE8942VerticalL62978VerticalS62514VerticalT8768Very62305Vi63271War63269Wat63316Wed8896Wei8472Wo63264Xi958YA253YD255Ye165Ze950   ";
        Parser.isletter0to370 = new boolean[] { false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, true, false, false, true, false, false, false, false, false, false, true, false, true, true, true, true, true, true, true, true, true, true, false, false, false, false, false, false, false, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, false, false, false, false, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, false, false, false, true, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, true, true, true, false, true, false, true, false, true, false, true, false, false, true, false, true, false, false, false, false, true, true, false, false, false, false, true, false, false, false, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, false, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, false, true, true, true, true, true, true, true, false, true, true, true, true, false, false, true, true, false, false, false, false, true, true, false, false, false, false, true, true, true, true, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, true, true, false, false, false, true, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, true, true, false, false, false, false, false, false, false, false, false, false, false, false, false, true, true, false, false, false, false, false, false, false, false, false, false, false, false, false, false, true, true, false, false, false, false, false, false, false, false, false, false, false, false, false, false, true, true, false };
        Parser.isletter774to1009 = new boolean[] { true, false, false, false, false, false, true, false, false, false, false, true, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, true, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, false, true, true, true, true, true, true, true, false, false, false, false, false, false, false, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, false, false, false, false, false, false, false, true, true, false, false, true, true, false, false, false, true, false, true, false, true, false, true, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, true, true };
        Parser.isletter8211to8504 = new boolean[] { true, true, false, false, false, false, false, false, false, false, false, false, false, true, true, true, false, false, false, true, false, false, false, false, false, false, false, false, false, false, false, true, true, true, true, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, true, false, false, false, false, false, false, false, false, true, false, false, false, false, false, false, false, false, false, false, false, false, false, false, true, false, false, false, true, false, false, false, false, false, false, false, false, false, true, true, true, true };
        Parser.isletter8706to8738 = new boolean[] { true, false, false, true, false, true, false, false, false, false, false, false, false, true, false, true, false, false, false, false, false, false, false, false, false, false, false, false, true, true, true, true, true };
        Parser.isletter9634to9839 = new boolean[] { true, true, false, false, false, false, false, false, false, false, false, false, true, true, false, false, true, true, false, false, false, false, false, false, false, false, true, true, false, false, false, false, false, false, false, false, true, true, false, false, false, true, false, false, false, true, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, true, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, true, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, true, true, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, true, true, true, true, false, false, false, false, false, false, false, false, false, true, true, true };
        Parser.isletter62977to63421 = new boolean[] { true, true, false, false, false, false, false, false, false, false, false, false, false, false, false, true, true, true, true, true, true, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, true, true, true, true, false, false, false, false, false, false, false, false, false, false, false, false, true, true, true, true, false, true, true, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true };
        Parser.structure_characters = ",()[]{}";
        Parser.number_start = "+-.0123456789";
        Parser.digit_characters = "0123456789";
        Parser.octal_digits = "01234567";
        Parser.hexadecimal_digits = "0123456789abcdefABCDEF";
        Parser.max_length_identifier = 100;
        Parser.CONSTRUCT_NONE = 0;
        Parser.CONSTRUCT_FUNCTION = 1;
        Parser.CONSTRUCT_SYMBOL = 2;
        Parser.CONSTRUCT_LIST = 3;
        Parser.CONSTRUCT_RULE = 4;
        Parser.CONSTRUCT_UNIDENTIFIED = 5;
        Parser.name_ShowAnimation = "ShowAnimation";
        Parser.name_Animate = "Animate";
        Parser.name_AnimationDisplayTime = "AnimationDisplayTime";
        Parser.name_AnimationDirection = "AnimationDirection";
        Parser.name_Forward = "Forward";
        Parser.name_Backward = "Backward";
        Parser.name_ForwardBackward = "ForwardBackward";
        Parser.name_Graphics3D = "Graphics3D";
        Parser.name_If = "If";
        Parser.name_Scaled = "Scaled";
        Parser.name_Cuboid = "Cuboid";
        Parser.name_Line = "Line";
        Parser.name_Point = "Point";
        Parser.name_Polygon = "Polygon";
        Parser.name_Text = "Text";
        Parser.name_StyleForm = "StyleForm";
        Parser.name_Questionmark = "?";
        Parser.name_TextStyle = "TextStyle";
        Parser.name_FontWeight = "FontWeight";
        Parser.name_Bold = "Bold";
        Parser.name_FontSize = "FontSize";
        Parser.name_FontSlant = "FontSlant";
        Parser.name_Italic = "Italic";
        Parser.name_FontFamily = "FontFamily";
        Parser.name_FontColor = "FontColor";
        Parser.name_URL = "URL";
        Parser.name_AbsolutePointSize = "AbsolutePointSize";
        Parser.name_AbsoluteThickness = "AbsoluteThickness";
        Parser.name_CMYKColor = "CMYKColor";
        Parser.name_EdgeForm = "EdgeForm";
        Parser.name_FaceForm = "FaceForm";
        Parser.name_GrayLevel = "GrayLevel";
        Parser.name_Hue = "Hue";
        Parser.name_PointSize = "PointSize";
        Parser.name_RGBColor = "RGBColor";
        Parser.name_SurfaceColor = "SurfaceColor";
        Parser.name_Thickness = "Thickness";
        Parser.name_AmbientLight = "AmbientLight";
        Parser.name_Axes = "Axes";
        Parser.name_AxesLabel = "AxesLabel";
        Parser.name_AxesEdge = "AxesEdge";
        Parser.name_AxesStyle = "AxesStyle";
        Parser.name_PlotLabel = "PlotLabel";
        Parser.name_Background = "Background";
        Parser.name_Boxed = "Boxed";
        Parser.name_BoxRatios = "BoxRatios";
        Parser.name_BoxStyle = "BoxStyle";
        Parser.name_DefaultColor = "DefaultColor";
        Parser.name_Lighting = "Lighting";
        Parser.name_LightSources = "LightSources";
        Parser.name_PlotRange = "PlotRange";
        Parser.name_Ticks = "Ticks";
        Parser.name_ViewPoint = "ViewPoint";
        Parser.name_ViewVertical = "ViewVertical";
        Parser.name_True = "True";
        Parser.name_False = "False";
        Parser.name_Automatic = "Automatic";
        Parser.name_All = "All";
        Parser.name_None = "None";
        Parser.name_Catalan = "Catalan";
        Parser.name_Degree = "Degree";
        Parser.name_E = "E";
        Parser.name_EulerGamma = "EulerGamma";
        Parser.name_Glaisher = "Glaisher";
        Parser.name_GoldenRatio = "GoldenRatio";
        Parser.name_Khinchin = "Khinchin";
        Parser.name_Pi = "Pi";
        Parser.name_I = "I";
        Parser.name_Infinity = "Infinity";
        Parser.name_Indeterminate = "Indeterminate";
        Parser.name_ComplexInfinity = "ComplexInfinity";
        Parser.function_names = new String[] { "N", "Plus", "Subtract", "Minus", "Times", "Divide", "Power", "Abs", "Sign", "Round", "IntegerPart", "FractionalPart", "Floor", "Ceiling", "Chop", "Max", "Min", "Re", "Im", "Conjugate", "Arg", "Mod", "Quotient", "Random", "SeedRandom", "Log", "Exp", "Sqrt", "Sin", "Cos", "Tan", "Csc", "Sec", "Cot", "ArcSin", "ArcCos", "ArcTan", "ArcCsc", "ArcSec", "ArcCot", "Sinh", "Cosh", "Tanh", "Csch", "Sech", "Coth", "ArcSinh", "ArcCosh", "ArcTanh", "ArcCsch", "ArcSech", "ArcCoth", "Factorial", "Factorial2", "Binomial", "Multinomial", "Pochhammer", "Gamma", "LogGamma", "PowerMod", "Erf", "Erfc", "Erfi", "Rationalize", "Equal", "Unequal", "Less", "Greater", "LessEqual", "GreaterEqual", "NumberQ", "NumericQ", "IntegerQ", "EvenQ", "OddQ", "Positive", "Negative", "NonPositive", "NonNegative", "TrueQ", "ValueQ", "Not", "And", "Or", "Xor", "Implies", "If", "Which", "Switch", "Identity", "Evaluate", "Hold", "HoldComplete", "HoldForm", "ReleaseHold", "NumberForm", "ScientificForm", "EngineeringForm", "AccountingForm", "PaddedForm", "StandardForm", "TraditionalForm", "InputForm", "OutputForm", "DisplayForm", "FullForm", "SessionTime", "TimeUsed", "AbsoluteTime", "DiscreteDelta", "KroneckerDelta", "UnitStep", "Beta", "Set" };
        Parser.max_count_scanned_numbers = 4;
        Parser.arguments_counts = new int[1000];
    }
}

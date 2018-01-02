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
    double scanned_AnimationDisplayTime;
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
    boolean scanned_nothing;
    boolean scanned_unidentified;
    int recursion_depth;
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
        this.scanned_AnimationDisplayTime = 0.05;
        this.scanned_AnimationDirection = 1;
        this.scanned_Animate_min = 0.0;
        this.scanned_Animate_max = 0.0;
        this.scanned_Animate_step = 1.0;
        this.scanned_Animate_variable = -1;
        this.scanning_animation_option = false;
        this.recursion_depth = 0;
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
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: istore_1        /* processed */
        //     2: aload_0         /* this */
        //     3: aload_0         /* this */
        //     4: getfield        Parser.current_char:C
        //     7: putfield        Parser.last_char:C
        //    10: aload_0         /* this */
        //    11: aload_0         /* this */
        //    12: getfield        Parser.text_index:I
        //    15: putfield        Parser.last_text_index:I
        //    18: aload_0         /* this */
        //    19: aload_0         /* this */
        //    20: getfield        Parser.text_index:I
        //    23: iconst_1       
        //    24: iadd           
        //    25: putfield        Parser.text_index:I
        //    28: aload_0         /* this */
        //    29: getfield        Parser.text_index:I
        //    32: iflt            49
        //    35: aload_0         /* this */
        //    36: getfield        Parser.text_index:I
        //    39: aload_0         /* this */
        //    40: getfield        Parser.text:Ljava/lang/String;
        //    43: invokevirtual   java/lang/String.length:()I
        //    46: if_icmplt       913
        //    49: aload_0         /* this */
        //    50: bipush          12
        //    52: putfield        Parser.current_char:C
        //    55: aload_0         /* this */
        //    56: getfield        Parser.current_char:C
        //    59: ireturn        
        //    60: aload_0         /* this */
        //    61: aload_0         /* this */
        //    62: getfield        Parser.text:Ljava/lang/String;
        //    65: aload_0         /* this */
        //    66: getfield        Parser.text_index:I
        //    69: invokevirtual   java/lang/String.charAt:(I)C
        //    72: putfield        Parser.current_char:C
        //    75: bipush          92
        //    77: aload_0         /* this */
        //    78: getfield        Parser.current_char:C
        //    81: if_icmpne       729
        //    84: aload_0         /* this */
        //    85: getfield        Parser.text_index:I
        //    88: iconst_1       
        //    89: iadd           
        //    90: aload_0         /* this */
        //    91: getfield        Parser.text:Ljava/lang/String;
        //    94: invokevirtual   java/lang/String.length:()I
        //    97: if_icmpge       729
        //   100: aload_0         /* this */
        //   101: aload_0         /* this */
        //   102: getfield        Parser.text_index:I
        //   105: iconst_1       
        //   106: iadd           
        //   107: putfield        Parser.text_index:I
        //   110: aload_0         /* this */
        //   111: aload_0         /* this */
        //   112: getfield        Parser.text:Ljava/lang/String;
        //   115: aload_0         /* this */
        //   116: getfield        Parser.text_index:I
        //   119: invokevirtual   java/lang/String.charAt:(I)C
        //   122: putfield        Parser.current_char:C
        //   125: bipush          91
        //   127: aload_0         /* this */
        //   128: getfield        Parser.current_char:C
        //   131: if_icmpne       593
        //   134: aload_0         /* this */
        //   135: aload_0         /* this */
        //   136: getfield        Parser.text_index:I
        //   139: iconst_1       
        //   140: iadd           
        //   141: putfield        Parser.text_index:I
        //   144: aload_0         /* this */
        //   145: getfield        Parser.text_index:I
        //   148: istore_2        /* special_char_index */
        //   149: goto            162
        //   152: aload_0         /* this */
        //   153: aload_0         /* this */
        //   154: getfield        Parser.text_index:I
        //   157: iconst_1       
        //   158: iadd           
        //   159: putfield        Parser.text_index:I
        //   162: aload_0         /* this */
        //   163: getfield        Parser.text_index:I
        //   166: aload_0         /* this */
        //   167: getfield        Parser.text:Ljava/lang/String;
        //   170: invokevirtual   java/lang/String.length:()I
        //   173: if_icmpge       215
        //   176: aload_0         /* this */
        //   177: aload_0         /* this */
        //   178: getfield        Parser.text:Ljava/lang/String;
        //   181: aload_0         /* this */
        //   182: getfield        Parser.text_index:I
        //   185: invokevirtual   java/lang/String.charAt:(I)C
        //   188: invokevirtual   Parser.isDigitOrLetterLike:(C)Z
        //   191: ifne            152
        //   194: iconst_0       
        //   195: getstatic       Parser.breaking_chars:Ljava/lang/String;
        //   198: aload_0         /* this */
        //   199: getfield        Parser.text:Ljava/lang/String;
        //   202: aload_0         /* this */
        //   203: getfield        Parser.text_index:I
        //   206: invokevirtual   java/lang/String.charAt:(I)C
        //   209: invokevirtual   java/lang/String.indexOf:(I)I
        //   212: if_icmple       152
        //   215: aload_0         /* this */
        //   216: getfield        Parser.text_index:I
        //   219: aload_0         /* this */
        //   220: getfield        Parser.text:Ljava/lang/String;
        //   223: invokevirtual   java/lang/String.length:()I
        //   226: if_icmpge       245
        //   229: bipush          93
        //   231: aload_0         /* this */
        //   232: getfield        Parser.text:Ljava/lang/String;
        //   235: aload_0         /* this */
        //   236: getfield        Parser.text_index:I
        //   239: invokevirtual   java/lang/String.charAt:(I)C
        //   242: if_icmpeq       266
        //   245: aload_0         /* this */
        //   246: aload_0         /* this */
        //   247: getfield        Parser.last_text_index:I
        //   250: iconst_1       
        //   251: iadd           
        //   252: putfield        Parser.text_index:I
        //   255: aload_0         /* this */
        //   256: bipush          92
        //   258: putfield        Parser.current_char:C
        //   261: iconst_1       
        //   262: istore_1        /* processed */
        //   263: goto            590
        //   266: aload_0         /* this */
        //   267: getfield        Parser.text:Ljava/lang/String;
        //   270: iload_2         /* special_char_index */
        //   271: aload_0         /* this */
        //   272: getfield        Parser.text_index:I
        //   275: invokevirtual   java/lang/String.substring:(II)Ljava/lang/String;
        //   278: astore_3        /* char_name */
        //   279: iconst_m1      
        //   280: istore          length
        //   282: iconst_0       
        //   283: istore          offset
        //   285: goto            387
        //   288: aload_3         /* char_name */
        //   289: invokevirtual   java/lang/String.length:()I
        //   292: istore          length
        //   294: iconst_0       
        //   295: istore          index
        //   297: goto            372
        //   300: aload_3         /* char_name */
        //   301: iload           offset
        //   303: getstatic       Parser.standard_parts:[Ljava/lang/String;
        //   306: iload           index
        //   308: aaload         
        //   309: iconst_0       
        //   310: iload           5
        //   312: invokevirtual   java/lang/String.regionMatches:(ILjava/lang/String;II)Z
        //   315: ifeq            369
        //   318: new             Ljava/lang/StringBuffer;
        //   321: dup            
        //   322: invokespecial   java/lang/StringBuffer.<init>:()V
        //   325: aload_3         /* char_name */
        //   326: iconst_0       
        //   327: iload           offset
        //   329: invokevirtual   java/lang/String.substring:(II)Ljava/lang/String;
        //   332: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
        //   335: getstatic       Parser.standard_short_parts:[Ljava/lang/String;
        //   338: iload           index
        //   340: aaload         
        //   341: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
        //   344: aload_3         /* char_name */
        //   345: iload           offset
        //   347: iload           5
        //   349: iadd           
        //   350: invokevirtual   java/lang/String.substring:(I)Ljava/lang/String;
        //   353: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
        //   356: invokevirtual   java/lang/StringBuffer.toString:()Ljava/lang/String;
        //   359: astore_3        /* char_name */
        //   360: iload           offset
        //   362: iconst_1       
        //   363: iadd           
        //   364: istore          offset
        //   366: goto            387
        //   369: iinc            index, 1
        //   372: getstatic       Parser.standard_parts:[Ljava/lang/String;
        //   375: iload           index
        //   377: aaload         
        //   378: invokevirtual   java/lang/String.length:()I
        //   381: dup            
        //   382: istore          part_length
        //   384: ifgt            300
        //   387: iload           length
        //   389: aload_3         /* char_name */
        //   390: invokevirtual   java/lang/String.length:()I
        //   393: if_icmpne       288
        //   396: iconst_m1      
        //   397: istore          uni_code
        //   399: aload_3         /* char_name */
        //   400: ldc             "n"
        //   402: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //   405: ifeq            416
        //   408: sipush          172
        //   411: istore          uni_code
        //   413: goto            566
        //   416: aload_3         /* char_name */
        //   417: invokevirtual   java/lang/String.length:()I
        //   420: iconst_2       
        //   421: if_icmplt       566
        //   424: iconst_0       
        //   425: istore          offset
        //   427: aload_3         /* char_name */
        //   428: iconst_0       
        //   429: iconst_2       
        //   430: invokevirtual   java/lang/String.substring:(II)Ljava/lang/String;
        //   433: astore          pattern
        //   435: goto            561
        //   438: getstatic       Parser.coded_char_names:Ljava/lang/String;
        //   441: aload           pattern
        //   443: iload           offset
        //   445: iconst_1       
        //   446: iadd           
        //   447: invokevirtual   java/lang/String.indexOf:(Ljava/lang/String;I)I
        //   450: istore          offset
        //   452: iload           offset
        //   454: iflt            561
        //   457: getstatic       Parser.coded_char_names:Ljava/lang/String;
        //   460: iload           offset
        //   462: iconst_1       
        //   463: isub           
        //   464: invokevirtual   java/lang/String.charAt:(I)C
        //   467: invokestatic    java/lang/Character.isDigit:(C)Z
        //   470: ifeq            561
        //   473: iload           offset
        //   475: iconst_2       
        //   476: iadd           
        //   477: istore          digit_index
        //   479: goto            485
        //   482: iinc            digit_index, 1
        //   485: getstatic       Parser.coded_char_names:Ljava/lang/String;
        //   488: iload           digit_index
        //   490: invokevirtual   java/lang/String.charAt:(I)C
        //   493: invokestatic    java/lang/Character.isDigit:(C)Z
        //   496: ifeq            482
        //   499: iload           digit_index
        //   501: iconst_1       
        //   502: iadd           
        //   503: istore          end_digit_index
        //   505: goto            511
        //   508: iinc            end_digit_index, 1
        //   511: getstatic       Parser.coded_char_names:Ljava/lang/String;
        //   514: iload           end_digit_index
        //   516: invokevirtual   java/lang/String.charAt:(I)C
        //   519: invokestatic    java/lang/Character.isDigit:(C)Z
        //   522: ifne            508
        //   525: aload_3         /* char_name */
        //   526: iconst_0       
        //   527: getstatic       Parser.coded_char_names:Ljava/lang/String;
        //   530: iload           offset
        //   532: iload           digit_index
        //   534: iload           offset
        //   536: isub           
        //   537: invokevirtual   java/lang/String.regionMatches:(ILjava/lang/String;II)Z
        //   540: ifeq            561
        //   543: iconst_m1      
        //   544: istore          offset
        //   546: getstatic       Parser.coded_char_names:Ljava/lang/String;
        //   549: iload           digit_index
        //   551: iload           end_digit_index
        //   553: invokevirtual   java/lang/String.substring:(II)Ljava/lang/String;
        //   556: invokestatic    java/lang/Integer.parseInt:(Ljava/lang/String;)I
        //   559: istore          uni_code
        //   561: iload           offset
        //   563: ifge            438
        //   566: iload           uni_code
        //   568: iflt            581
        //   571: aload_0         /* this */
        //   572: iload           uni_code
        //   574: i2c            
        //   575: putfield        Parser.current_char:C
        //   578: goto            588
        //   581: aload_0         /* this */
        //   582: sipush          191
        //   585: putfield        Parser.current_char:C
        //   588: iconst_1       
        //   589: istore_1        /* processed */
        //   590: goto            913
        //   593: iconst_0       
        //   594: getstatic       Parser.white_characters:Ljava/lang/String;
        //   597: aload_0         /* this */
        //   598: getfield        Parser.current_char:C
        //   601: invokevirtual   java/lang/String.indexOf:(I)I
        //   604: if_icmpgt       708
        //   607: aload_0         /* this */
        //   608: aload_0         /* this */
        //   609: getfield        Parser.text_index:I
        //   612: iconst_1       
        //   613: iadd           
        //   614: putfield        Parser.text_index:I
        //   617: goto            630
        //   620: aload_0         /* this */
        //   621: aload_0         /* this */
        //   622: getfield        Parser.text_index:I
        //   625: iconst_1       
        //   626: iadd           
        //   627: putfield        Parser.text_index:I
        //   630: aload_0         /* this */
        //   631: getfield        Parser.text_index:I
        //   634: aload_0         /* this */
        //   635: getfield        Parser.text:Ljava/lang/String;
        //   638: invokevirtual   java/lang/String.length:()I
        //   641: if_icmpge       665
        //   644: iconst_0       
        //   645: getstatic       Parser.white_characters:Ljava/lang/String;
        //   648: aload_0         /* this */
        //   649: getfield        Parser.text:Ljava/lang/String;
        //   652: aload_0         /* this */
        //   653: getfield        Parser.text_index:I
        //   656: invokevirtual   java/lang/String.charAt:(I)C
        //   659: invokevirtual   java/lang/String.indexOf:(I)I
        //   662: if_icmple       620
        //   665: aload_0         /* this */
        //   666: getfield        Parser.text_index:I
        //   669: aload_0         /* this */
        //   670: getfield        Parser.text:Ljava/lang/String;
        //   673: invokevirtual   java/lang/String.length:()I
        //   676: if_icmplt       703
        //   679: aload_0         /* this */
        //   680: aload_0         /* this */
        //   681: getfield        Parser.text:Ljava/lang/String;
        //   684: invokevirtual   java/lang/String.length:()I
        //   687: iconst_1       
        //   688: isub           
        //   689: putfield        Parser.text_index:I
        //   692: aload_0         /* this */
        //   693: bipush          32
        //   695: putfield        Parser.current_char:C
        //   698: iconst_1       
        //   699: istore_1        /* processed */
        //   700: goto            913
        //   703: iconst_0       
        //   704: istore_1        /* processed */
        //   705: goto            913
        //   708: aload_0         /* this */
        //   709: bipush          92
        //   711: putfield        Parser.current_char:C
        //   714: aload_0         /* this */
        //   715: aload_0         /* this */
        //   716: getfield        Parser.text_index:I
        //   719: iconst_1       
        //   720: isub           
        //   721: putfield        Parser.text_index:I
        //   724: iconst_1       
        //   725: istore_1        /* processed */
        //   726: goto            913
        //   729: iconst_0       
        //   730: getstatic       Parser.single_quote_chars:Ljava/lang/String;
        //   733: aload_0         /* this */
        //   734: getfield        Parser.current_char:C
        //   737: invokevirtual   java/lang/String.indexOf:(I)I
        //   740: if_icmpgt       803
        //   743: aload_0         /* this */
        //   744: getfield        Parser.text_index:I
        //   747: iconst_1       
        //   748: iadd           
        //   749: aload_0         /* this */
        //   750: getfield        Parser.text:Ljava/lang/String;
        //   753: invokevirtual   java/lang/String.length:()I
        //   756: if_icmpge       803
        //   759: iconst_0       
        //   760: getstatic       Parser.single_quote_chars:Ljava/lang/String;
        //   763: aload_0         /* this */
        //   764: getfield        Parser.text:Ljava/lang/String;
        //   767: aload_0         /* this */
        //   768: getfield        Parser.text_index:I
        //   771: iconst_1       
        //   772: iadd           
        //   773: invokevirtual   java/lang/String.charAt:(I)C
        //   776: invokevirtual   java/lang/String.indexOf:(I)I
        //   779: if_icmpgt       803
        //   782: aload_0         /* this */
        //   783: bipush          34
        //   785: putfield        Parser.current_char:C
        //   788: aload_0         /* this */
        //   789: aload_0         /* this */
        //   790: getfield        Parser.text_index:I
        //   793: iconst_1       
        //   794: iadd           
        //   795: putfield        Parser.text_index:I
        //   798: iconst_1       
        //   799: istore_1        /* processed */
        //   800: goto            913
        //   803: bipush          40
        //   805: aload_0         /* this */
        //   806: getfield        Parser.current_char:C
        //   809: if_icmpne       911
        //   812: aload_0         /* this */
        //   813: getfield        Parser.text_index:I
        //   816: iconst_1       
        //   817: iadd           
        //   818: aload_0         /* this */
        //   819: getfield        Parser.text:Ljava/lang/String;
        //   822: invokevirtual   java/lang/String.length:()I
        //   825: if_icmpge       911
        //   828: bipush          42
        //   830: aload_0         /* this */
        //   831: getfield        Parser.text:Ljava/lang/String;
        //   834: aload_0         /* this */
        //   835: getfield        Parser.text_index:I
        //   838: iconst_1       
        //   839: iadd           
        //   840: invokevirtual   java/lang/String.charAt:(I)C
        //   843: if_icmpne       911
        //   846: aload_0         /* this */
        //   847: aload_0         /* this */
        //   848: getfield        Parser.text:Ljava/lang/String;
        //   851: getstatic       Parser.comment_end:Ljava/lang/String;
        //   854: aload_0         /* this */
        //   855: getfield        Parser.text_index:I
        //   858: iconst_1       
        //   859: iadd           
        //   860: invokevirtual   java/lang/String.indexOf:(Ljava/lang/String;I)I
        //   863: putfield        Parser.text_index:I
        //   866: iconst_m1      
        //   867: aload_0         /* this */
        //   868: getfield        Parser.text_index:I
        //   871: if_icmpne       890
        //   874: aload_0         /* this */
        //   875: aload_0         /* this */
        //   876: getfield        Parser.text:Ljava/lang/String;
        //   879: invokevirtual   java/lang/String.length:()I
        //   882: iconst_1       
        //   883: isub           
        //   884: putfield        Parser.text_index:I
        //   887: goto            900
        //   890: aload_0         /* this */
        //   891: aload_0         /* this */
        //   892: getfield        Parser.text_index:I
        //   895: iconst_1       
        //   896: iadd           
        //   897: putfield        Parser.text_index:I
        //   900: aload_0         /* this */
        //   901: bipush          32
        //   903: putfield        Parser.current_char:C
        //   906: iconst_1       
        //   907: istore_1        /* processed */
        //   908: goto            913
        //   911: iconst_1       
        //   912: istore_1        /* processed */
        //   913: iload_1         /* processed */
        //   914: ifeq            60
        //   917: aload_0         /* this */
        //   918: getfield        Parser.current_char:C
        //   921: ireturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name                Signature
        //  -----  ------  ----  ------------------  ------------------
        //  0      922     0     this                LParser;
        //  2      920     1     processed           Z
        //  149    441     2     special_char_index  I
        //  279    311     3     char_name           Ljava/lang/String;
        //  282    308     4     length              I
        //  384    206     5     part_length         I
        //  285    305     6     offset              I
        //  297    90      7     index               I
        //  399    191     7     uni_code            I
        //  435    131     8     pattern             Ljava/lang/String;
        //  479    82      9     digit_index         I
        //  505    56      10    end_digit_index     I
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        //     at com.strobel.decompiler.ast.AstBuilder.convertLocalVariables(AstBuilder.java:2985)
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
                while (this.text_index < this.text.length()) {
                    if ('\"' == this.current_char) {
                        break;
                    }
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
        this.scanned_AnimationDisplayTime = 0.05;
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
    
    boolean scan_primitive(Primitive3D default_primitive) {
        this.scanned_nothing = false;
        this.scanned_unidentified = false;
        this.scan_token();
        if (5 == this.scanned_token_type) {
            if ((!this.scanning_EdgeForm && !this.scanning_FaceForm) || (this.scanning_AxesStyles && 1 == this.recursion_depth)) {
                default_primitive = new Primitive3D(default_primitive);
            }
            ++this.recursion_depth;
            while (this.scan_primitive(default_primitive)) {
                this.scan_token();
                if (0 != this.scanned_token_type) {
                    break;
                }
            }
            --this.recursion_depth;
            if (6 != this.scanned_token_type) {
                return false;
            }
            if (this.scanning_AxesStyles && 1 == this.recursion_depth) {
                this.scanned_AxesStyles[0] = this.scanned_AxesStyles[1];
                this.scanned_AxesStyles[1] = this.scanned_AxesStyles[2];
                this.scanned_AxesStyles[2] = default_primitive;
            }
            return true;
        }
        else {
            if (21 != this.scanned_token_type) {
                this.scanned_nothing = true;
                return false;
            }
            if (!this.scanning_EdgeForm && !this.scanning_FaceForm && !this.scanning_SurfaceColor && this.scanned_identifier.equals(Parser.name_EdgeForm)) {
                this.scan_token();
                if (3 != this.scanned_token_type) {
                    return false;
                }
                this.scanning_EdgeForm = true;
                default_primitive.is_outlined = false;
                if (this.scan_primitive(default_primitive)) {
                    this.scanning_EdgeForm = false;
                    this.scan_token();
                    return 4 == this.scanned_token_type;
                }
                if (this.scanned_nothing && 4 == this.scanned_token_type) {
                    this.scanning_EdgeForm = false;
                    return true;
                }
            }
            else if (!this.scanning_EdgeForm && !this.scanning_FaceForm && !this.scanning_SurfaceColor && this.scanned_identifier.equals(Parser.name_FaceForm)) {
                this.scan_token();
                if (3 != this.scanned_token_type) {
                    return false;
                }
                this.scanning_FaceForm = true;
                this.scanning_FaceForm_back = false;
                if (!this.scan_primitive(default_primitive)) {
                    return this.scanning_FaceForm = false;
                }
                this.scan_token();
                if (0 == this.scanned_token_type) {
                    this.scanning_FaceForm_back = true;
                    if (!this.scan_primitive(default_primitive)) {
                        this.scanning_FaceForm = false;
                        return this.scanning_FaceForm_back = false;
                    }
                    this.scanning_FaceForm_back = false;
                    this.scan_token();
                }
                this.scanning_FaceForm = false;
                return 4 == this.scanned_token_type;
            }
            else if (!this.scanning_EdgeForm && !this.scanning_SurfaceColor && this.scanned_identifier.equals(Parser.name_SurfaceColor)) {
                this.scan_token();
                if (3 != this.scanned_token_type) {
                    return false;
                }
                this.scanning_SurfaceColor = true;
                this.scanning_SurfaceColor_specular = false;
                if (!this.scan_primitive(default_primitive)) {
                    return this.scanning_SurfaceColor = false;
                }
                this.scan_token();
                if (0 == this.scanned_token_type) {
                    this.scanning_SurfaceColor_specular = true;
                    if (!this.scan_primitive(default_primitive)) {
                        this.scanning_SurfaceColor = false;
                        return this.scanning_SurfaceColor_specular = false;
                    }
                    this.scanning_SurfaceColor = false;
                    this.scanning_SurfaceColor_specular = false;
                    this.scan_token();
                    if (0 == this.scanned_token_type) {
                        this.scan_token();
                        if (22 != this.scanned_token_type) {
                            return false;
                        }
                        if (!this.scanning_FaceForm_back) {
                            default_primitive.front_specular_exponent = this.scanned_number;
                        }
                        default_primitive.back_specular_exponent = this.scanned_number;
                        this.scan_token();
                    }
                }
                this.scanning_SurfaceColor = false;
                return 4 == this.scanned_token_type;
            }
            if (!this.scan_color()) {
                return false;
            }
            if (null != this.scanned_color) {
                if (this.scanning_SurfaceColor && !this.scanning_SurfaceColor_specular) {
                    if (!this.scanning_FaceForm_back) {
                        default_primitive.front_diffuse_color = this.scanned_color;
                    }
                    default_primitive.back_diffuse_color = this.scanned_color;
                }
                else if (this.scanning_SurfaceColor && this.scanning_SurfaceColor_specular) {
                    if (!this.scanning_FaceForm_back) {
                        default_primitive.front_specular_color = this.scanned_color;
                    }
                    default_primitive.back_specular_color = this.scanned_color;
                }
                else if (this.scanning_FaceForm) {
                    if (!this.scanning_FaceForm_back) {
                        default_primitive.front_face_color = this.scanned_color;
                    }
                    default_primitive.back_face_color = this.scanned_color;
                }
                else if (this.scanning_EdgeForm) {
                    default_primitive.edge_color = this.scanned_color;
                    default_primitive.is_outlined = true;
                }
                else {
                    default_primitive.standard_color = this.scanned_color;
                    default_primitive.front_face_color = this.scanned_color;
                    default_primitive.back_face_color = this.scanned_color;
                }
                return true;
            }
            if (!this.scanning_SurfaceColor && !this.scanning_EdgeForm && !this.scanning_FaceForm) {
                if (this.scanned_identifier.equals(Parser.name_PointSize)) {
                    if (!this.scan_numbers(false, false) || 1 != this.count_scanned_numbers) {
                        return false;
                    }
                    default_primitive.original_point_size = this.scanned_numbers[0];
                    default_primitive.is_absolute_point_size = false;
                    return true;
                }
                else if (this.scanned_identifier.equals(Parser.name_AbsolutePointSize)) {
                    if (!this.scan_numbers(false, false) || 1 != this.count_scanned_numbers) {
                        return false;
                    }
                    default_primitive.original_point_size = this.scanned_numbers[0];
                    return default_primitive.is_absolute_point_size = true;
                }
            }
            if (!this.scanning_SurfaceColor && !this.scanning_FaceForm) {
                if (this.scanned_identifier.equals(Parser.name_Thickness)) {
                    if (!this.scan_numbers(false, false) || 1 != this.count_scanned_numbers) {
                        return false;
                    }
                    if (this.scanning_EdgeForm) {
                        default_primitive.is_outlined = true;
                        default_primitive.original_edge_thickness = this.scanned_numbers[0];
                        default_primitive.is_absolute_edge_thickness = false;
                    }
                    else {
                        default_primitive.original_thickness = this.scanned_numbers[0];
                        default_primitive.is_absolute_thickness = false;
                    }
                    return true;
                }
                else if (this.scanned_identifier.equals(Parser.name_AbsoluteThickness)) {
                    if (!this.scan_numbers(false, false) || 1 != this.count_scanned_numbers) {
                        return false;
                    }
                    if (this.scanning_EdgeForm) {
                        default_primitive.is_outlined = true;
                        default_primitive.original_edge_thickness = this.scanned_numbers[0];
                        default_primitive.is_absolute_edge_thickness = true;
                    }
                    else {
                        default_primitive.original_thickness = this.scanned_numbers[0];
                        default_primitive.is_absolute_thickness = true;
                    }
                    return true;
                }
            }
            if (!this.scanning_EdgeForm && !this.scanning_FaceForm && !this.scanning_SurfaceColor) {
                if (this.scanned_identifier.equals(Parser.name_Point)) {
                    this.scan_token();
                    if (3 != this.scanned_token_type) {
                        return false;
                    }
                    if (!this.scan_points() || 1 != this.count_scanned_points) {
                        return false;
                    }
                    this.scan_token();
                    if (4 != this.scanned_token_type) {
                        return false;
                    }
                    final Primitive3D primitive = new Primitive3D(default_primitive);
                    primitive.count_points = 1;
                    primitive.original_points = this.scanned_points;
                    primitive.original_scaled_offsets = this.scanned_scaled_offsets;
                    primitive.original_expressions = this.scanned_expressions;
                    primitive.is_filled = false;
                    primitive.is_outlined = false;
                    this.graphics.addPrimitive(primitive);
                    return true;
                }
                else if (this.scanned_identifier.equals(Parser.name_Line)) {
                    this.scan_token();
                    if (3 != this.scanned_token_type) {
                        return false;
                    }
                    if (!this.scan_points()) {
                        return false;
                    }
                    this.scan_token();
                    if (4 != this.scanned_token_type) {
                        return false;
                    }
                    final Primitive3D primitive = new Primitive3D(default_primitive);
                    primitive.count_points = this.count_scanned_points;
                    primitive.original_points = this.scanned_points;
                    primitive.original_scaled_offsets = this.scanned_scaled_offsets;
                    primitive.original_expressions = this.scanned_expressions;
                    primitive.is_filled = false;
                    primitive.is_outlined = false;
                    this.graphics.addPrimitive(primitive);
                    return true;
                }
                else if (this.scanned_identifier.equals(Parser.name_Polygon)) {
                    boolean[] scanned_edge_flags = null;
                    this.scan_token();
                    if (3 != this.scanned_token_type) {
                        return false;
                    }
                    if (!this.scan_points()) {
                        return false;
                    }
                    this.scan_token();
                    if (0 == this.scanned_token_type) {
                        this.scan_salt();
                        this.scan_token();
                        if (0 == this.scanned_token_type) {
                            this.scan_token();
                            if (5 != this.scanned_token_type) {
                                this.scan_salt();
                                this.scan_token();
                            }
                            else {
                                scanned_edge_flags = new boolean[this.count_scanned_points];
                                int edge_index = 0;
                                while (6 != this.scanned_token_type) {
                                    this.scan_token();
                                    if (21 != this.scanned_token_type) {
                                        this.scan_salt();
                                        this.scan_token();
                                    }
                                    if (this.scanned_identifier.equals(Parser.name_True)) {
                                        if (edge_index < this.count_scanned_points) {
                                            scanned_edge_flags[edge_index] = true;
                                        }
                                        this.scan_token();
                                    }
                                    else if (this.scanned_identifier.equals(Parser.name_False)) {
                                        if (edge_index < this.count_scanned_points) {
                                            scanned_edge_flags[edge_index] = false;
                                        }
                                        this.scan_token();
                                    }
                                    else {
                                        this.scan_salt();
                                        this.scan_token();
                                    }
                                    if (0 != this.scanned_token_type && 6 != this.scanned_token_type) {
                                        return false;
                                    }
                                    ++edge_index;
                                }
                                if (edge_index != this.count_scanned_points) {
                                    scanned_edge_flags = null;
                                }
                                this.scan_token();
                            }
                        }
                    }
                    if (4 != this.scanned_token_type) {
                        return false;
                    }
                    final Primitive3D primitive2 = new Primitive3D(default_primitive);
                    primitive2.count_points = this.count_scanned_points;
                    primitive2.original_points = this.scanned_points;
                    primitive2.original_scaled_offsets = this.scanned_scaled_offsets;
                    primitive2.original_expressions = this.scanned_expressions;
                    primitive2.edge_flags = scanned_edge_flags;
                    primitive2.is_filled = true;
                    this.graphics.addPrimitive(primitive2);
                    return true;
                }
                else if (this.scanned_identifier.equals(Parser.name_Cuboid)) {
                    final int[][] cuboid_codes = { { 0, 1, 3, 2 }, { 0, 4, 5, 1 }, { 0, 2, 6, 4 }, { 7, 6, 4, 5 }, { 7, 3, 2, 6 }, { 7, 5, 1, 3 } };
                    this.scan_token();
                    if (3 != this.scanned_token_type) {
                        return false;
                    }
                    if (!this.scan_points() || 1 != this.count_scanned_points) {
                        return false;
                    }
                    final double[] min_point = this.scanned_points[0];
                    final double[] min_scaled_offset = this.scanned_scaled_offsets[0];
                    if (null != this.scanned_expressions && (this.scanned_expressions[0][0] >= 0 || this.scanned_expressions[0][1] >= 0 || this.scanned_expressions[0][2] >= 0)) {
                        return false;
                    }
                    this.scan_token();
                    double[] max_point;
                    double[] max_scaled_offset;
                    if (4 == this.scanned_token_type) {
                        if (null != min_point) {
                            max_point = new double[] { min_point[0] + 1.0, min_point[1] + 1.0, min_point[2] + 1.0 };
                            max_scaled_offset = min_scaled_offset;
                        }
                        else {
                            max_point = null;
                            max_scaled_offset = null;
                        }
                    }
                    else {
                        if (0 != this.scanned_token_type) {
                            return false;
                        }
                        if (!this.scan_points() || 1 != this.count_scanned_points) {
                            return false;
                        }
                        if (null != this.scanned_expressions && (this.scanned_expressions[0][0] >= 0 || this.scanned_expressions[0][1] >= 0 || this.scanned_expressions[0][2] >= 0)) {
                            return false;
                        }
                        this.scan_token();
                        if (4 != this.scanned_token_type) {
                            return false;
                        }
                        max_point = this.scanned_points[0];
                        max_scaled_offset = this.scanned_scaled_offsets[0];
                    }
                    if ((null != min_point && null != max_point) || (null == min_point && null == max_point && null != max_scaled_offset)) {
                        for (int face_index = 0; face_index < 6; ++face_index) {
                            final Primitive3D primitive3 = new Primitive3D(default_primitive);
                            primitive3.count_points = 4;
                            primitive3.original_points = new double[4][3];
                            primitive3.original_scaled_offsets = new double[4][3];
                            primitive3.is_filled = true;
                            for (int corner_index = 0; corner_index < 4; ++corner_index) {
                                for (int axes_index = 0; axes_index < 3; ++axes_index) {
                                    if (0x0 != (cuboid_codes[face_index][corner_index] & 1 << axes_index)) {
                                        if (null != max_point) {
                                            primitive3.original_points[corner_index][axes_index] = max_point[axes_index];
                                        }
                                        else {
                                            primitive3.original_points[corner_index] = null;
                                        }
                                        primitive3.original_scaled_offsets[corner_index][axes_index] = max_scaled_offset[axes_index];
                                    }
                                    else {
                                        if (null != min_point) {
                                            primitive3.original_points[corner_index][axes_index] = min_point[axes_index];
                                        }
                                        else {
                                            primitive3.original_points[corner_index] = null;
                                        }
                                        primitive3.original_scaled_offsets[corner_index][axes_index] = min_scaled_offset[axes_index];
                                    }
                                }
                            }
                            this.graphics.addPrimitive(primitive3);
                        }
                    }
                    return true;
                }
                else if (this.scanned_identifier.equals(Parser.name_Text)) {
                    final Primitive3D primitive = new Primitive3D(default_primitive);
                    this.scan_token();
                    if (3 != this.scanned_token_type) {
                        return false;
                    }
                    this.scan_token();
                    if (!this.scan_text(primitive, true)) {
                        return false;
                    }
                    this.scan_token();
                    if (0 != this.scanned_token_type) {
                        return false;
                    }
                    if (!this.scan_points() || 1 != this.count_scanned_points) {
                        return false;
                    }
                    primitive.count_points = 1;
                    primitive.original_points = this.scanned_points;
                    primitive.original_scaled_offsets = this.scanned_scaled_offsets;
                    primitive.original_expressions = this.scanned_expressions;
                    this.scan_token();
                    double x_offset = 0.0;
                    double y_offset = 0.0;
                    if (0 == this.scanned_token_type) {
                        if (!this.scan_numbers(false, true) || 2 != this.count_scanned_numbers) {
                            return false;
                        }
                        x_offset = this.scanned_numbers[0];
                        y_offset = this.scanned_numbers[1];
                        this.scan_right_bracket();
                    }
                    if (4 != this.scanned_token_type) {
                        return false;
                    }
                    primitive.original_point_size = x_offset;
                    primitive.original_thickness = y_offset;
                    this.graphics.addPrimitive(primitive);
                    return true;
                }
            }
            this.scan_salt();
            return (',' == this.current_char || '}' == this.current_char || ']' == this.current_char) && (this.scanned_unidentified = true);
        }
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
        if (22 == this.scanned_token_type) {
            primitive.text = String.valueOf(this.scanned_number);
        }
        else if (23 == this.scanned_token_type) {
            primitive.text = this.scanned_string;
        }
        else if (21 == this.scanned_token_type) {
            if (!this.scanned_identifier.equals(Parser.name_StyleForm)) {
                primitive.text = this.scanned_identifier;
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
                                final Primitive3D primitive2 = new Primitive3D();
                                primitive2.front_specular_exponent = 0.01;
                                primitive2.back_specular_exponent = 0.0;
                                this.scan_token();
                                if (22 == this.scanned_token_type) {
                                    primitive2.original_point_size = this.scanned_number;
                                    primitive2.text = Double.toString(this.scanned_number);
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
                                        if (!this.scan_text(primitive2, false)) {
                                            return false;
                                        }
                                        this.scan_token();
                                        if (0 == this.scanned_token_type) {
                                            this.scan_token();
                                            if (22 == this.scanned_token_type) {
                                                primitive2.front_specular_exponent = this.scanned_number / 2.0;
                                                primitive2.back_specular_exponent = this.scanned_number / 2.0;
                                            }
                                            else {
                                                if (5 != this.scanned_token_type) {
                                                    return false;
                                                }
                                                this.scan_token();
                                                if (22 != this.scanned_token_type) {
                                                    return false;
                                                }
                                                primitive2.front_specular_exponent = this.scanned_number;
                                                this.scan_token();
                                                if (0 != this.scanned_token_type) {
                                                    return false;
                                                }
                                                this.scan_token();
                                                if (22 != this.scanned_token_type) {
                                                    return false;
                                                }
                                                primitive2.back_specular_exponent = this.scanned_number;
                                                this.scan_token();
                                                if (6 != this.scanned_token_type) {
                                                    return false;
                                                }
                                            }
                                            this.scan_token();
                                            if (0 == this.scanned_token_type) {
                                                this.scanning_EdgeForm = true;
                                                if (!this.scan_primitive(primitive2)) {
                                                    return this.scanning_EdgeForm = false;
                                                }
                                                primitive2.original_thickness = primitive2.original_edge_thickness;
                                                primitive2.is_absolute_thickness = primitive2.is_absolute_edge_thickness;
                                                primitive2.standard_color = primitive2.edge_color;
                                                this.scanning_EdgeForm = false;
                                                this.scan_token();
                                            }
                                        }
                                    }
                                    else {
                                        primitive2.text = Double.toString(tick_mark_position);
                                    }
                                    primitive2.original_point_size = tick_mark_position;
                                    if (6 != this.scanned_token_type) {
                                        return false;
                                    }
                                }
                                if (this.graphics.ticks_max_in_length[axis_index] < primitive2.front_specular_exponent) {
                                    this.graphics.ticks_max_in_length[axis_index] = primitive2.front_specular_exponent;
                                }
                                if (this.graphics.ticks_max_out_length[axis_index] < primitive2.back_specular_exponent) {
                                    this.graphics.ticks_max_out_length[axis_index] = primitive2.back_specular_exponent;
                                }
                                this.graphics.option_Ticks[axis_index].addElement(primitive2);
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
                final Primitive3D primitive3 = new Primitive3D();
                this.scanning_FaceForm = true;
                if (!this.scan_primitive(primitive3) || null == primitive3.front_face_color) {
                    return this.scanning_FaceForm = false;
                }
                this.scanning_FaceForm = false;
                this.graphics.option_LightSources_vectors.addElement(new_light_vector);
                this.graphics.option_LightSources_colors.addElement(primitive3.front_face_color);
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
        else if (this.scanning_animation_option && this.scanned_identifier.equals(Parser.name_AnimationDisplayTime)) {
            this.scan_token();
            if (20 != this.scanned_token_type) {
                return false;
            }
            this.scan_token();
            if (22 != this.scanned_token_type) {
                return false;
            }
            this.scanned_AnimationDisplayTime = this.scanned_number;
            return true;
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

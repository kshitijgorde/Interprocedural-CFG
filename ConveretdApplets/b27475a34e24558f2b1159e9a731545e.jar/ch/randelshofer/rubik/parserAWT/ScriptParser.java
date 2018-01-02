// 
// Decompiled by Procyon v0.5.30
// 

package ch.randelshofer.rubik.parserAWT;

import ch.randelshofer.rubik.RubiksCubeCore;
import java.util.Enumeration;
import ch.randelshofer.io.ParseException;
import ch.randelshofer.gui.tree.DefaultMutableTreeNode;
import ch.randelshofer.io.StreamPosTokenizer;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.StringTokenizer;
import java.util.Hashtable;

public class ScriptParser
{
    public static final int POSITION_UNSUPPORTED = -1;
    public static final int POSITION_PREFIX = 0;
    public static final int POSITION_SUFFIX = 1;
    public static final int POSITION_HEADER = 2;
    private boolean DEBUG;
    private String[][] tokens;
    private Hashtable transformationMap;
    private Hashtable permutationMap;
    private Hashtable macroMap;
    private int commutatorPos;
    private int conjugatorPos;
    private int invertorPos;
    private int repetitorPos;
    private int reflectorPos;
    private boolean isSequenceSupported;
    private boolean isAmbiguousSeqBeginPermBegin;
    private boolean isAmbiguousSeqBeginCmtrBegin;
    private boolean isAmbiguousSeqBeginCngrBegin;
    private boolean isAmbiguousCngrBeginCmtrBegin;
    private boolean isAmbiguousCngrDelimCmtrDelim;
    private boolean isAmbiguousCngrBeginPermBegin;
    private boolean isAmbiguousCmtrBeginPermBegin;
    private boolean isAmbiguousSeqEndCmtrEnd;
    private boolean isAmbiguousSeqEndCngrEnd;
    private boolean isAmbiguousCngrEndCmtrEnd;
    public static final int R = 0;
    public static final int U = 1;
    public static final int F = 2;
    public static final int L = 3;
    public static final int D = 4;
    public static final int B = 5;
    public static final int Ri = 6;
    public static final int Ui = 7;
    public static final int Fi = 8;
    public static final int Li = 9;
    public static final int Di = 10;
    public static final int Bi = 11;
    public static final int R2 = 12;
    public static final int U2 = 13;
    public static final int F2 = 14;
    public static final int L2 = 15;
    public static final int D2 = 16;
    public static final int B2 = 17;
    public static final int R2i = 18;
    public static final int U2i = 19;
    public static final int F2i = 20;
    public static final int L2i = 21;
    public static final int D2i = 22;
    public static final int B2i = 23;
    public static final int TR = 24;
    public static final int TU = 25;
    public static final int TF = 26;
    public static final int TL = 27;
    public static final int TD = 28;
    public static final int TB = 29;
    public static final int TRi = 30;
    public static final int TUi = 31;
    public static final int TFi = 32;
    public static final int TLi = 33;
    public static final int TDi = 34;
    public static final int TBi = 35;
    public static final int TR2 = 36;
    public static final int TU2 = 37;
    public static final int TF2 = 38;
    public static final int TL2 = 39;
    public static final int TD2 = 40;
    public static final int TB2 = 41;
    public static final int TR2i = 42;
    public static final int TU2i = 43;
    public static final int TF2i = 44;
    public static final int TL2i = 45;
    public static final int TD2i = 46;
    public static final int TB2i = 47;
    public static final int MR = 48;
    public static final int MU = 49;
    public static final int FB = 50;
    public static final int ML = 51;
    public static final int MD = 52;
    public static final int BF = 53;
    public static final int MR2 = 54;
    public static final int MU2 = 55;
    public static final int MF2 = 56;
    public static final int ML2 = 57;
    public static final int MD2 = 58;
    public static final int MB2 = 59;
    public static final int SR = 60;
    public static final int SU = 61;
    public static final int SB = 62;
    public static final int SL = 63;
    public static final int SD = 64;
    public static final int SF = 65;
    public static final int SR2 = 66;
    public static final int SU2 = 67;
    public static final int SF2 = 68;
    public static final int SL2 = 69;
    public static final int SD2 = 70;
    public static final int SB2 = 71;
    public static final int CR = 72;
    public static final int CU = 73;
    public static final int CF = 74;
    public static final int CL = 75;
    public static final int CD = 76;
    public static final int CB = 77;
    public static final int CR2 = 78;
    public static final int CU2 = 79;
    public static final int CF2 = 80;
    public static final int CL2 = 81;
    public static final int CD2 = 82;
    public static final int CB2 = 83;
    public static final int NOP = 84;
    private static final int TWIST_FIRST_TOKEN = 0;
    private static final int TWIST_LAST_TOKEN = 84;
    private static final int FACE_FIRST_TOKEN = 0;
    private static final int FACE_LAST_TOKEN = 23;
    private static final int TWOLAYER_FIRST_TOKEN = 24;
    private static final int TWOLAYER_LAST_TOKEN = 47;
    private static final int MIDLAYER_FIRST_TOKEN = 48;
    private static final int MIDLAYER_LAST_TOKEN = 59;
    private static final int SLICE_FIRST_TOKEN = 60;
    private static final int SLICE_LAST_TOKEN = 71;
    private static final int ROTATION_FIRST_TOKEN = 72;
    private static final int ROTATION_LAST_TOKEN = 83;
    public static final int PR = 85;
    public static final int PU = 86;
    public static final int PF = 87;
    public static final int PL = 88;
    public static final int PD = 89;
    public static final int PB = 90;
    public static final int PPLUS = 91;
    public static final int PMINUS = 92;
    public static final int PPLUSPLUS = 93;
    private static final int PERMUTATION_FIRST_TOKEN = 85;
    private static final int PERMUTATION_LAST_TOKEN = 93;
    public static final int STATEMENT_DELIMITER = 94;
    public static final int INVERTOR = 95;
    public static final int REFLECTOR = 96;
    public static final int SEQUENCE_BEGIN = 97;
    public static final int SEQUENCE_END = 98;
    public static final int PERMUTATION_DELIMITER = 99;
    public static final int PERMUTATION_BEGIN = 100;
    public static final int PERMUTATION_END = 101;
    public static final int REPETITOR_BEGIN = 102;
    public static final int REPETITOR_END = 103;
    public static final int COMMUTATOR_BEGIN = 104;
    public static final int COMMUTATOR_END = 105;
    public static final int COMMUTATOR_DELIMITER = 106;
    public static final int CONJUGATOR_BEGIN = 107;
    public static final int CONJUGATOR_END = 108;
    public static final int CONJUGATOR_DELIMITER = 109;
    public static final int COMMENT_BEGIN = 110;
    public static final int COMMENT_END = 111;
    public static final int SINGLE_LINE_COMMENT_BEGIN = 112;
    public static final int TOKEN_COUNT = 113;
    public static final int SCRIPT_EXPRESSION = 113;
    public static final int MACRO_EXPRESSION = 114;
    public static final int STATEMENT_EXPRESSION = 115;
    public static final int SEQUENCE_EXPRESSION = 116;
    public static final int INVERSION_EXPRESSION = 117;
    public static final int REPETITION_EXPRESSION = 118;
    public static final int PERMUTATION_EXPRESSION = 119;
    public static final int COMMUTATION_EXPRESSION = 120;
    public static final int CONJUGATION_EXPRESSION = 121;
    public static final int REFLECTION_EXPRESSION = 122;
    private static String[] defaultTokens;
    private static final String COMPRESSED_TOKENS = "R;U;F;L;D;B;Ri;Ui;Fi;Li;Di;Bi;R2;U2;F2;L2;D2;B2;R2i;U2i;F2i;L2i;D2i;B2i;TR;TU;TF;TL;TD;TB;TRi;TUi;TFi;TLi;TDi;TBi;TR2;TU2;TF2;TL2;TD2;TB2;TR2i;TU2i;TF2i;TL2i;TD2i;TB2i;MR;MU;MF;ML;MD;MB;MR2;MU2;MF2;ML2;MD2;MB2;SR;SU;SF;SL;SD;SB;SR2;SU2;SF2;SL2;SD2;SB2;CR;CU;CF;CL;CD;CB;CR2;CU2;CF2;CL2;CD2;CB2;NOP;permR;permU;permB;permL;permD;permF;permPlus;permMinus;permPlusPlus;statementDelimiter;invertor;reflector;sequenceBegin;sequenceEnd;permutationDelimiter;permutationBegin;permutationEnd;repetitorBegin;repetitorEnd;commutatorBegin;commutatorEnd;commutatorDelimiter;conjugatorBegin;conjugatorEnd;conjugatorDelimiter;commentBegin;commentEnd;singleLineCommentBegin;";
    
    public ScriptParser() {
        this(getDefaultTokens(), 1, 1, -1, 0, 0);
    }
    
    public ScriptParser(final String[] array, final int n, final int n2, final int n3, final int n4, final int n5) {
        this(array, null, n, n2, n3, n4, n5, true);
    }
    
    public ScriptParser(final String[] array, final Hashtable macroMap, final int repetitorPos, final int invertorPos, final int reflectorPos, final int conjugatorPos, final int commutatorPos, final boolean isSequenceSupported) {
        this.DEBUG = false;
        this.transformationMap = new Hashtable();
        this.permutationMap = new Hashtable();
        this.macroMap = new Hashtable();
        this.commutatorPos = -1;
        this.conjugatorPos = -1;
        this.invertorPos = -1;
        this.repetitorPos = -1;
        this.reflectorPos = -1;
        if (macroMap != null) {
            this.macroMap = macroMap;
        }
        this.isSequenceSupported = isSequenceSupported;
        this.repetitorPos = repetitorPos;
        this.invertorPos = invertorPos;
        this.reflectorPos = reflectorPos;
        this.conjugatorPos = conjugatorPos;
        this.commutatorPos = commutatorPos;
        this.tokens = new String[array.length][0];
        for (int i = 0; i < array.length; ++i) {
            if (array[i] != null) {
                final Integer n = new Integer(i);
                final StringTokenizer stringTokenizer = new StringTokenizer(array[i], " ", false);
                this.tokens[i] = new String[stringTokenizer.countTokens()];
                for (int j = 0; j < this.tokens[i].length; ++j) {
                    final String nextToken = stringTokenizer.nextToken();
                    this.tokens[i][j] = nextToken;
                    if (85 <= i && i <= 93) {
                        if (nextToken != null) {
                            this.permutationMap.put(nextToken, n);
                        }
                    }
                    else if (nextToken != null) {
                        this.transformationMap.put(nextToken, n);
                    }
                }
            }
        }
        this.isAmbiguousSeqBeginPermBegin = this.isAmbiguous(97, 100);
        this.isAmbiguousSeqBeginCmtrBegin = this.isAmbiguous(97, 104);
        this.isAmbiguousSeqBeginCngrBegin = this.isAmbiguous(97, 107);
        this.isAmbiguousCngrBeginCmtrBegin = this.isAmbiguous(104, 107);
        this.isAmbiguousCngrDelimCmtrDelim = this.isAmbiguous(106, 109);
        this.isAmbiguousCngrBeginPermBegin = this.isAmbiguous(107, 100);
        this.isAmbiguousCmtrBeginPermBegin = this.isAmbiguous(104, 100);
        this.isAmbiguousSeqEndCmtrEnd = this.isAmbiguous(98, 105);
        this.isAmbiguousSeqEndCngrEnd = this.isAmbiguous(98, 108);
        this.isAmbiguousCngrEndCmtrEnd = this.isAmbiguous(105, 108);
        if ((this.tokens[110].length != 0 || this.tokens[111].length != 0) && (this.tokens[110].length == 0 || this.tokens[111].length == 0 || this.tokens[110].length != 1 || this.tokens[111].length != 1 || this.tokens[110][0].length() < 1 || this.tokens[110][0].length() > 2 || this.tokens[111][0].length() < 1 || this.tokens[111][0].length() > 2)) {
            throw new IllegalArgumentException("Illegal Comment Tokens " + this.tokens[110] + " " + this.tokens[111]);
        }
        if (this.tokens[112].length != 0 && (this.tokens[112].length != 1 || this.tokens[112][0].length() < 1 || this.tokens[112][0].length() > 2)) {
            final StringBuffer sb = new StringBuffer();
            sb.append("Illegal Single Line Comment Token ");
            for (int k = 0; k < this.tokens[112].length; ++k) {
                if (k > 0) {
                    sb.append(',');
                }
                sb.append('\'');
                sb.append(this.tokens[112][k]);
                sb.append('\'');
            }
            throw new IllegalArgumentException(sb.toString());
        }
    }
    
    private boolean isAmbiguous(final int n, final int n2) {
        final String[] array = this.tokens[n];
        final String[] array2 = this.tokens[n2];
        if (array != null && array2 != null) {
            for (int i = 0; i < array.length; ++i) {
                if (array[i].length() > 0) {
                    for (int j = 0; j < array2.length; ++j) {
                        if (array[i].equals(array2[j])) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
    
    public int getInvertorPosition() {
        return this.invertorPos;
    }
    
    public boolean isInversionSupported() {
        return this.invertorPos != -1;
    }
    
    public boolean isSequenceSupported() {
        return this.isSequenceSupported;
    }
    
    public int getRepetitorPosition() {
        return this.repetitorPos;
    }
    
    public boolean isRepetitionSupported() {
        return this.repetitorPos != -1;
    }
    
    public boolean isPermutationSupported() {
        return this.permutationMap.size() > 0;
    }
    
    public ScriptNode parse(final String s) throws IOException {
        return this.parse(new StringReader(s), null);
    }
    
    public ScriptNode parse(final Reader reader) throws IOException {
        return this.parse(reader, null);
    }
    
    public ScriptNode parse(final Reader reader, final ScriptNode scriptNode) throws IOException {
        if (this.DEBUG) {
            System.out.println("BEGIN PARSE");
        }
        final StreamPosTokenizer streamPosTokenizer = new StreamPosTokenizer(reader);
        streamPosTokenizer.resetSyntax();
        streamPosTokenizer.wordChars(33, 65535);
        streamPosTokenizer.whitespaceChars(0, 32);
        streamPosTokenizer.eolIsSignificant(false);
        if (this.tokens[110].length != 0) {
            streamPosTokenizer.slashStarComments(true);
            streamPosTokenizer.setSlashStarTokens(this.tokens[110][0], this.tokens[111][0]);
        }
        if (this.tokens[112].length != 0) {
            streamPosTokenizer.slashSlashComments(true);
            streamPosTokenizer.setSlashSlashToken(this.tokens[112][0]);
        }
        final ScriptNode scriptNode2 = new ScriptNode();
        if (scriptNode != null) {
            scriptNode.add(scriptNode2);
        }
        scriptNode2.setStartPosition(0);
        while (streamPosTokenizer.nextToken() != -1) {
            streamPosTokenizer.pushBack();
            this.parseExpression(streamPosTokenizer, scriptNode2);
        }
        scriptNode2.setEndPosition(streamPosTokenizer.getEndPosition());
        if (this.DEBUG) {
            System.out.println("END PARSE");
        }
        return scriptNode2;
    }
    
    private void printVerbose(final StreamPosTokenizer streamPosTokenizer, final String s, final ScriptNode scriptNode) throws IOException {
        if (this.DEBUG) {
            int depth = scriptNode.getDepth();
            final StringBuffer sb = new StringBuffer();
            while (depth-- > 0) {
                sb.append('.');
            }
            sb.append(s);
            sb.append(' ');
            streamPosTokenizer.nextToken();
            sb.append(streamPosTokenizer.sval);
            streamPosTokenizer.pushBack();
            System.out.println(sb.toString());
        }
    }
    
    private ExpressionNode parseExpression(final StreamPosTokenizer streamPosTokenizer, final ScriptNode scriptNode) throws IOException {
        if (this.DEBUG) {
            this.printVerbose(streamPosTokenizer, "expression", scriptNode);
        }
        final String greedy;
        final Integer n;
        if (streamPosTokenizer.nextToken() == -3 && (n = (Integer)this.transformationMap.get(greedy = this.parseGreedy(streamPosTokenizer.sval))) != null && (n == 94 || n == 99)) {
            this.consumeGreedy(streamPosTokenizer, greedy);
            return null;
        }
        streamPosTokenizer.pushBack();
        final ExpressionNode expressionNode = new ExpressionNode();
        scriptNode.add(expressionNode);
        expressionNode.setStartPosition(streamPosTokenizer.getStartPosition());
        ScriptNode prefix = expressionNode;
        ScriptNode scriptNode2 = expressionNode;
        while ((prefix = this.parsePrefix(streamPosTokenizer, prefix)) != null) {
            scriptNode2 = prefix;
        }
        expressionNode.setEndPosition(this.parseStatement(streamPosTokenizer, scriptNode2).getEndPosition());
        ScriptNode scriptNode3 = (ScriptNode)expressionNode.getChildAt(0);
        ScriptNode suffix;
        while ((suffix = this.parseSuffix(streamPosTokenizer, expressionNode)) != null) {
            suffix.add(scriptNode3);
            scriptNode3 = suffix;
            expressionNode.setEndPosition(suffix.getEndPosition());
        }
        return expressionNode;
    }
    
    private ScriptNode parsePrefix(final StreamPosTokenizer streamPosTokenizer, final ScriptNode scriptNode) throws IOException {
        if (this.DEBUG) {
            this.printVerbose(streamPosTokenizer, "prefix", scriptNode);
        }
        String greedyInt = null;
        if (streamPosTokenizer.nextToken() != -3) {
            streamPosTokenizer.pushBack();
            return null;
        }
        final Integer n = this.transformationMap.get(this.parseGreedy(streamPosTokenizer.sval));
        if (n == null) {
            greedyInt = this.parseGreedyInt(streamPosTokenizer.sval);
        }
        streamPosTokenizer.pushBack();
        if (n == null && greedyInt == "\u0000") {
            return null;
        }
        if (n == null) {
            if (this.repetitorPos == 0) {
                return this.parseRepetitor(streamPosTokenizer, scriptNode);
            }
            return null;
        }
        else {
            final int intValue = n;
            if (this.invertorPos == 0 && intValue == 95) {
                return this.parseInvertor(streamPosTokenizer, scriptNode);
            }
            if (this.repetitorPos == 0 && intValue == 102) {
                return this.parseRepetitor(streamPosTokenizer, scriptNode);
            }
            return null;
        }
    }
    
    private ScriptNode parseSuffix(final StreamPosTokenizer streamPosTokenizer, final ScriptNode scriptNode) throws IOException {
        if (this.DEBUG) {
            this.printVerbose(streamPosTokenizer, "suffix", scriptNode);
        }
        String greedyInt = null;
        if (streamPosTokenizer.nextToken() != -3) {
            streamPosTokenizer.pushBack();
            return null;
        }
        final Integer n = this.transformationMap.get(this.parseGreedy(streamPosTokenizer.sval));
        if (n == null) {
            greedyInt = this.parseGreedyInt(streamPosTokenizer.sval);
        }
        streamPosTokenizer.pushBack();
        if (n == null && greedyInt == "\u0000") {
            return null;
        }
        if (n == null) {
            if (this.repetitorPos == 1) {
                return this.parseRepetitor(streamPosTokenizer, scriptNode);
            }
            return null;
        }
        else {
            final int intValue = n;
            if (this.invertorPos == 1 && intValue == 95) {
                return this.parseInvertor(streamPosTokenizer, scriptNode);
            }
            if (this.repetitorPos == 1 && intValue == 102) {
                return this.parseRepetitor(streamPosTokenizer, scriptNode);
            }
            return null;
        }
    }
    
    private ScriptNode parseInvertor(final StreamPosTokenizer streamPosTokenizer, final ScriptNode scriptNode) throws IOException {
        if (this.DEBUG) {
            this.printVerbose(streamPosTokenizer, "invertor", scriptNode);
        }
        final InversionNode inversionNode = new InversionNode();
        scriptNode.add(inversionNode);
        inversionNode.setStartPosition(streamPosTokenizer.getStartPosition());
        if (streamPosTokenizer.nextToken() != -3) {
            throw new ParseException("Invertor: Token missing.", streamPosTokenizer.getStartPosition(), streamPosTokenizer.getEndPosition());
        }
        final String greedy;
        final Integer n = this.transformationMap.get(greedy = this.parseGreedy(streamPosTokenizer.sval));
        if (n != null && n == 95) {
            inversionNode.setEndPosition(streamPosTokenizer.getStartPosition() + greedy.length() - 1);
            this.consumeGreedy(streamPosTokenizer, greedy);
            return inversionNode;
        }
        throw new ParseException("Invertor: Illegal token " + streamPosTokenizer.sval, streamPosTokenizer.getStartPosition(), streamPosTokenizer.getEndPosition());
    }
    
    private ScriptNode parseRepetitor(final StreamPosTokenizer streamPosTokenizer, final ScriptNode scriptNode) throws IOException {
        if (this.DEBUG) {
            this.printVerbose(streamPosTokenizer, "repetitor", scriptNode);
        }
        final RepetitionNode repetitionNode = new RepetitionNode();
        scriptNode.add(repetitionNode);
        repetitionNode.setStartPosition(streamPosTokenizer.getStartPosition());
        if (streamPosTokenizer.nextToken() != -3) {
            throw new ParseException("Repetitor: Token missing.", streamPosTokenizer.getStartPosition(), streamPosTokenizer.getEndPosition());
        }
        final String greedy;
        final Integer n = this.transformationMap.get(greedy = this.parseGreedy(streamPosTokenizer.sval));
        if (n != null && n == 102) {
            this.consumeGreedy(streamPosTokenizer, greedy);
        }
        else {
            streamPosTokenizer.pushBack();
        }
        if (streamPosTokenizer.nextToken() != -3) {
            throw new ParseException("Repetitor: Repeat count missing.", streamPosTokenizer.getStartPosition(), streamPosTokenizer.getEndPosition());
        }
        final String greedyInt;
        if ((greedyInt = this.parseGreedyInt(streamPosTokenizer.sval)) == "\u0000") {
            throw new ParseException("Repetitor: Invalid repeat count " + streamPosTokenizer.sval, streamPosTokenizer.getStartPosition(), streamPosTokenizer.getEndPosition());
        }
        int int1;
        try {
            int1 = Integer.parseInt(greedyInt);
        }
        catch (NumberFormatException ex) {
            throw new ParseException("Repetitor: Internal Error " + ex.getMessage(), streamPosTokenizer.getStartPosition(), streamPosTokenizer.getEndPosition());
        }
        if (int1 < 1) {
            throw new ParseException("Repetitor: Invalid repeat count " + int1, streamPosTokenizer.getStartPosition(), streamPosTokenizer.getEndPosition());
        }
        repetitionNode.setRepeatCount(int1);
        repetitionNode.setEndPosition(streamPosTokenizer.getStartPosition() + greedyInt.length() - 1);
        this.consumeGreedy(streamPosTokenizer, greedyInt);
        if (streamPosTokenizer.nextToken() != -3) {
            streamPosTokenizer.pushBack();
            return repetitionNode;
        }
        final String greedy2;
        final Integer n2 = this.transformationMap.get(greedy2 = this.parseGreedy(streamPosTokenizer.sval));
        if (n2 == null) {
            streamPosTokenizer.pushBack();
            return repetitionNode;
        }
        if (n2 == 103) {
            this.consumeGreedy(streamPosTokenizer, greedy2);
        }
        else {
            streamPosTokenizer.pushBack();
        }
        return repetitionNode;
    }
    
    private ScriptNode parseStatement(final StreamPosTokenizer streamPosTokenizer, final ScriptNode scriptNode) throws IOException {
        if (this.DEBUG) {
            this.printVerbose(streamPosTokenizer, "statement", scriptNode);
        }
        if (streamPosTokenizer.nextToken() != -3) {
            throw new ParseException("Statement: Token missing.", streamPosTokenizer.getStartPosition(), streamPosTokenizer.getEndPosition());
        }
        final String greedy;
        final Integer n = this.transformationMap.get(greedy = this.parseGreedy(streamPosTokenizer.sval));
        if (n == null) {
            if (this.macroMap.get(greedy) != null) {
                streamPosTokenizer.pushBack();
                return this.parseMacro(streamPosTokenizer, scriptNode);
            }
            throw new ParseException("Statement: Unknown token " + streamPosTokenizer.sval, streamPosTokenizer.getStartPosition(), streamPosTokenizer.getEndPosition());
        }
        else {
            final int intValue = n;
            if (0 <= intValue && intValue <= 84) {
                streamPosTokenizer.pushBack();
                return this.parseTwist(streamPosTokenizer, scriptNode);
            }
            if (!this.isAmbiguousSeqBeginPermBegin && intValue == 97) {
                final int startPosition = streamPosTokenizer.getStartPosition();
                this.consumeGreedy(streamPosTokenizer, greedy);
                return this.parseSequence(streamPosTokenizer, scriptNode, startPosition, 0x1 | ((this.conjugatorPos == 2 && this.isAmbiguousSeqBeginCngrBegin) ? 2 : 0) | ((this.commutatorPos == 2 && this.isAmbiguousSeqBeginCmtrBegin) ? 4 : 0));
            }
            if (intValue == 100 && !this.isAmbiguousSeqBeginPermBegin) {
                final int startPosition2 = streamPosTokenizer.getStartPosition();
                this.consumeGreedy(streamPosTokenizer, greedy);
                return this.parsePermutation(streamPosTokenizer, scriptNode, startPosition2);
            }
            if (intValue != 97 && intValue != 100 && (intValue != 107 || this.conjugatorPos != 2) && (intValue != 104 || this.commutatorPos != 2)) {
                throw new ParseException("Statement: Illegal Token " + streamPosTokenizer.sval, streamPosTokenizer.getStartPosition(), streamPosTokenizer.getEndPosition());
            }
            final int startPosition3 = streamPosTokenizer.getStartPosition();
            this.consumeGreedy(streamPosTokenizer, greedy);
            if (streamPosTokenizer.nextToken() != -3) {
                throw new ParseException("Statement: Token missing.", streamPosTokenizer.getStartPosition(), streamPosTokenizer.getEndPosition());
            }
            final Integer n2 = this.permutationMap.get(this.parseGreedy(streamPosTokenizer.sval));
            streamPosTokenizer.pushBack();
            if (n2 != null && 85 <= n2 && n2 <= 93) {
                return this.parsePermutation(streamPosTokenizer, scriptNode, startPosition3);
            }
            return this.parseSequence(streamPosTokenizer, scriptNode, startPosition3, 0x1 | ((this.conjugatorPos == 2 && this.isAmbiguousSeqBeginCngrBegin) ? 2 : 0) | ((this.commutatorPos == 2 && this.isAmbiguousSeqBeginCmtrBegin) ? 4 : 0));
        }
    }
    
    private ScriptNode parseSequence(final StreamPosTokenizer streamPosTokenizer, final ScriptNode scriptNode, final int startPosition, int n) throws IOException {
        if (this.DEBUG) {
            this.printVerbose(streamPosTokenizer, "sequence", scriptNode);
        }
        final ScriptNode scriptNode2 = new ScriptNode();
        scriptNode2.setStartPosition(startPosition);
        scriptNode.add(scriptNode2);
        ScriptNode scriptNode3 = null;
        ScriptNode scriptNode4 = scriptNode2;
    Label_0630:
        while (true) {
            switch (streamPosTokenizer.nextToken()) {
                case -3: {
                    final String greedy = this.parseGreedy(streamPosTokenizer.sval);
                    final Integer n2 = this.transformationMap.get(greedy);
                    final int n3 = (n2 == null) ? -1 : n2;
                    if (n3 == 98 || n3 == 101) {
                        if (!this.isAmbiguousSeqEndCngrEnd) {
                            n &= 0x5;
                        }
                        if (!this.isAmbiguousSeqEndCmtrEnd) {
                            n &= 0x3;
                        }
                        scriptNode4.setEndPosition(streamPosTokenizer.getStartPosition() + greedy.length() - 1);
                        this.consumeGreedy(streamPosTokenizer, greedy);
                        break Label_0630;
                    }
                    if (n3 == 109 && this.conjugatorPos == 2) {
                        if ((n & 0x1) == 0x1 && !this.isAmbiguousSeqBeginCngrBegin) {
                            throw new ParseException("Sequence: Illegal delimiter.", streamPosTokenizer.getStartPosition(), streamPosTokenizer.getEndPosition());
                        }
                        if (!this.isAmbiguousCngrDelimCmtrDelim) {
                            n = 2;
                        }
                        if (scriptNode3 == null) {
                            scriptNode2.setEndPosition(streamPosTokenizer.getStartPosition());
                            scriptNode3 = new ScriptNode();
                            scriptNode3.setStartPosition(streamPosTokenizer.getEndPosition());
                            scriptNode.add(scriptNode3);
                            scriptNode4 = scriptNode3;
                            this.consumeGreedy(streamPosTokenizer, greedy);
                            continue;
                        }
                        throw new ParseException("Conjugation: Delimiter must occur only once", streamPosTokenizer.getStartPosition(), streamPosTokenizer.getEndPosition());
                    }
                    else if (n3 == 106 && this.commutatorPos == 2) {
                        if ((n & 0x1) == 0x1 && !this.isAmbiguousSeqBeginCmtrBegin) {
                            throw new ParseException("Sequence: Illegal delimiter.", streamPosTokenizer.getStartPosition(), streamPosTokenizer.getEndPosition());
                        }
                        if (!this.isAmbiguousCngrDelimCmtrDelim) {
                            n = 4;
                        }
                        if (scriptNode3 == null) {
                            scriptNode2.setEndPosition(streamPosTokenizer.getStartPosition());
                            scriptNode3 = new ScriptNode();
                            scriptNode3.setStartPosition(streamPosTokenizer.getEndPosition());
                            scriptNode.add(scriptNode3);
                            scriptNode4 = scriptNode3;
                            this.consumeGreedy(streamPosTokenizer, greedy);
                            continue;
                        }
                        throw new ParseException("Commutation: Delimiter must occur only once", streamPosTokenizer.getStartPosition(), streamPosTokenizer.getEndPosition());
                    }
                    else {
                        if (n3 == 108 && this.conjugatorPos == 2) {
                            if (!this.isAmbiguousCngrEndCmtrEnd) {
                                n &= 0x3;
                            }
                            if (!this.isAmbiguousSeqEndCngrEnd) {
                                n &= 0x6;
                            }
                            scriptNode4.setEndPosition(streamPosTokenizer.getStartPosition() + greedy.length() - 1);
                            this.consumeGreedy(streamPosTokenizer, greedy);
                            break Label_0630;
                        }
                        if (n3 == 105 && this.commutatorPos == 2) {
                            if (!this.isAmbiguousCngrEndCmtrEnd) {
                                n &= 0x5;
                            }
                            if (!this.isAmbiguousSeqEndCmtrEnd) {
                                n &= 0x6;
                            }
                            scriptNode4.setEndPosition(streamPosTokenizer.getStartPosition() + greedy.length() - 1);
                            this.consumeGreedy(streamPosTokenizer, greedy);
                            break Label_0630;
                        }
                        streamPosTokenizer.pushBack();
                        this.parseExpression(streamPosTokenizer, scriptNode4);
                        continue;
                    }
                    break;
                }
                case -1: {
                    throw new ParseException("Sequence: Close bracket missing.", streamPosTokenizer.getStartPosition(), streamPosTokenizer.getEndPosition());
                }
                default: {
                    throw new ParseException("Sequence: Internal error.", streamPosTokenizer.getStartPosition(), streamPosTokenizer.getEndPosition());
                }
            }
        }
        scriptNode2.removeFromParent();
        if (scriptNode3 != null) {
            scriptNode3.removeFromParent();
        }
        switch (n) {
            case 1: {
                if (scriptNode3 != null) {
                    throw new ParseException("Sequence: Illegal Sequence.", startPosition, streamPosTokenizer.getEndPosition());
                }
                scriptNode4 = new SequenceNode(startPosition, streamPosTokenizer.getEndPosition());
                scriptNode4.add(scriptNode2);
                break;
            }
        }
        scriptNode.add(scriptNode4);
        return scriptNode4;
    }
    
    private ScriptNode parsePermutation(final StreamPosTokenizer streamPosTokenizer, final ScriptNode scriptNode, final int startPosition) throws IOException {
        if (this.DEBUG) {
            this.printVerbose(streamPosTokenizer, "permutation", scriptNode);
        }
        final PermutationNode permutationNode = new PermutationNode();
        scriptNode.add(permutationNode);
        permutationNode.setStartPosition(startPosition);
        while (true) {
            switch (streamPosTokenizer.nextToken()) {
                case -3: {
                    final String greedy = this.parseGreedy(streamPosTokenizer.sval);
                    final Integer n = this.transformationMap.get(greedy);
                    final int n2 = (n == null) ? -1 : n;
                    if (n2 == 98 || n2 == 101) {
                        permutationNode.setEndPosition(streamPosTokenizer.getStartPosition() + greedy.length() - 1);
                        this.consumeGreedy(streamPosTokenizer, greedy);
                        if (permutationNode.getPermItemCount() == 0) {
                            throw new ParseException("Permutation: Illegal empty Permutation.", permutationNode.getStartPosition(), permutationNode.getEndPosition());
                        }
                        return permutationNode;
                    }
                    else {
                        streamPosTokenizer.pushBack();
                        this.parsePermutationItem(streamPosTokenizer, permutationNode);
                        if (streamPosTokenizer.nextToken() != -3) {
                            streamPosTokenizer.pushBack();
                            continue;
                        }
                        final String greedy2 = this.parseGreedy(streamPosTokenizer.sval);
                        if ((int)this.transformationMap.get(greedy2) == 99) {
                            this.consumeGreedy(streamPosTokenizer, greedy2);
                            continue;
                        }
                        streamPosTokenizer.pushBack();
                        continue;
                    }
                    break;
                }
                case -1: {
                    throw new ParseException("Permutation: Close bracket missing.", streamPosTokenizer.getStartPosition(), streamPosTokenizer.getEndPosition());
                }
                default: {
                    throw new ParseException("Permutation: Internal error.", streamPosTokenizer.getStartPosition(), streamPosTokenizer.getEndPosition());
                }
            }
        }
    }
    
    private void parsePermutationItem(final StreamPosTokenizer streamPosTokenizer, final PermutationNode permutationNode) throws IOException {
        if (this.DEBUG) {
            this.printVerbose(streamPosTokenizer, "permutationItem", permutationNode);
        }
        if (streamPosTokenizer.nextToken() != -3) {
            throw new ParseException("PermutationItem: Token missing.", streamPosTokenizer.getStartPosition(), streamPosTokenizer.getEndPosition());
        }
        final int startPosition = streamPosTokenizer.getStartPosition();
        final String greedy = this.parseGreedy(streamPosTokenizer.sval);
        final Integer n = this.permutationMap.get(greedy);
        final int n2 = (n == null) ? -1 : n;
        if (n2 == -1) {
            throw new ParseException("PermutationItem: Illegal token " + streamPosTokenizer.sval, streamPosTokenizer.getStartPosition(), streamPosTokenizer.getEndPosition());
        }
        int n3 = 0;
        switch (n2) {
            case 91:
            case 92:
            case 93: {
                n3 = n2;
                this.consumeGreedy(streamPosTokenizer, greedy);
                break;
            }
            default: {
                n3 = 0;
                streamPosTokenizer.pushBack();
                break;
            }
        }
        final int[] array = new int[3];
        int i = 0;
        Label_0538: {
            while (i < 3) {
                if (streamPosTokenizer.nextToken() != -3) {
                    throw new ParseException("PermutationItem: Face token missing.", streamPosTokenizer.getStartPosition(), streamPosTokenizer.getEndPosition());
                }
                final String greedy2 = this.parseGreedy(streamPosTokenizer.sval);
                final Integer n4 = this.permutationMap.get(greedy2);
                final int n5 = (n4 == null) ? -1 : n4;
                if (n5 == -1) {
                    throw new ParseException("PermutationItem: Illegal or unknown token " + streamPosTokenizer.sval, streamPosTokenizer.getStartPosition(), streamPosTokenizer.getEndPosition());
                }
                if (85 > n5 || n5 > 90) {
                    throw new ParseException("PermutationItem: Illegal token " + streamPosTokenizer.sval, streamPosTokenizer.getStartPosition(), streamPosTokenizer.getEndPosition());
                }
                if (this.DEBUG) {
                    this.printVerbose(streamPosTokenizer, "permutationItem Face:" + greedy2, permutationNode);
                }
                array[i++] = n5;
                this.consumeGreedy(streamPosTokenizer, greedy2);
                if (streamPosTokenizer.nextToken() != -3) {
                    throw new ParseException("PermutationItem: Token missing.", streamPosTokenizer.getStartPosition(), streamPosTokenizer.getEndPosition());
                }
                final Integer n6 = this.transformationMap.get(this.parseGreedy(streamPosTokenizer.sval));
                final int n7 = (n6 == null) ? -1 : n6;
                streamPosTokenizer.pushBack();
                switch (n7) {
                    case 94:
                    case 98:
                    case 99:
                    case 101: {
                        break Label_0538;
                    }
                    default: {
                        continue;
                    }
                }
            }
            try {
                permutationNode.addPermItem(i, n3, array);
            }
            catch (IllegalArgumentException ex) {
                throw new ParseException(ex.getMessage(), startPosition, streamPosTokenizer.getEndPosition());
            }
        }
    }
    
    private ScriptNode parseTwist(final StreamPosTokenizer streamPosTokenizer, final ScriptNode scriptNode) throws IOException {
        if (this.DEBUG) {
            this.printVerbose(streamPosTokenizer, "transformation", scriptNode);
        }
        final TwistNode twistNode = new TwistNode();
        scriptNode.add(twistNode);
        if (streamPosTokenizer.nextToken() != -3) {
            throw new ParseException("Twist: Token missing.", streamPosTokenizer.getStartPosition(), streamPosTokenizer.getEndPosition());
        }
        twistNode.setStartPosition(streamPosTokenizer.getStartPosition());
        final String greedy = this.parseGreedy(streamPosTokenizer.sval);
        final Integer n = this.transformationMap.get(greedy);
        final int symbol = (n == null) ? -1 : n;
        if (0 <= symbol && symbol <= 84) {
            twistNode.setSymbol(symbol);
            twistNode.setEndPosition(streamPosTokenizer.getStartPosition() + greedy.length() - 1);
            this.consumeGreedy(streamPosTokenizer, greedy);
            return twistNode;
        }
        throw new ParseException("Twist: Illegal token " + streamPosTokenizer.sval, streamPosTokenizer.getStartPosition(), streamPosTokenizer.getEndPosition());
    }
    
    private ScriptNode parseMacro(final StreamPosTokenizer streamPosTokenizer, final ScriptNode scriptNode) throws IOException {
        if (this.DEBUG) {
            this.printVerbose(streamPosTokenizer, "macro", scriptNode);
        }
        switch (streamPosTokenizer.nextToken()) {
            case -3: {
                final String greedy = this.parseGreedy(streamPosTokenizer.sval);
                final Object value = this.macroMap.get(greedy);
                if (value != null) {
                    MacroNode macroNode;
                    if (value instanceof String) {
                        macroNode = new MacroNode(greedy, (String)value, streamPosTokenizer.getStartPosition(), streamPosTokenizer.getStartPosition() + greedy.length() - 1);
                        this.macroMap.put(greedy, macroNode);
                    }
                    else {
                        macroNode = (MacroNode)((MacroNode)value).cloneSubtree();
                        final Enumeration preorderEnumeration = macroNode.preorderEnumeration();
                        while (preorderEnumeration.hasMoreElements()) {
                            final ScriptNode scriptNode2 = preorderEnumeration.nextElement();
                            scriptNode2.setStartPosition(streamPosTokenizer.getStartPosition());
                            scriptNode2.setEndPosition(streamPosTokenizer.getStartPosition() + greedy.length() - 1);
                        }
                    }
                    scriptNode.add(macroNode);
                    try {
                        macroNode.expand(this);
                    }
                    catch (IOException ex) {
                        if (ex instanceof ParseException) {
                            final ParseException ex2 = (ParseException)ex;
                            throw new ParseException("Macro '" + greedy + "': " + ex.getMessage() + " @" + ex2.getStartPosition() + ".." + ex2.getEndPosition(), streamPosTokenizer.getStartPosition(), streamPosTokenizer.getStartPosition() + greedy.length() - 1);
                        }
                        throw new ParseException("Macro '" + greedy + "': " + ex.getMessage(), streamPosTokenizer.getStartPosition(), streamPosTokenizer.getStartPosition() + greedy.length() - 1);
                    }
                    this.consumeGreedy(streamPosTokenizer, greedy);
                    return macroNode;
                }
                throw new ParseException("Macro: Unexpected or unknown Token.", streamPosTokenizer.getStartPosition(), streamPosTokenizer.getStartPosition() + greedy.length() - 1);
            }
            case -1: {
                throw new ParseException("Macro: Token missing.", streamPosTokenizer.getStartPosition(), streamPosTokenizer.getEndPosition());
            }
            default: {
                throw new ParseException("Macro: Internal error.", streamPosTokenizer.getStartPosition(), streamPosTokenizer.getEndPosition());
            }
        }
    }
    
    private String parseGreedy(final String s) {
        if (this.transformationMap.get(s) != null || this.permutationMap.get(s) != null || this.macroMap.get(s) != null) {
            return s;
        }
        if (s.length() > 1) {
            return this.parseGreedy(s.substring(0, s.length() - 1));
        }
        return "\u0000";
    }
    
    private String parseGreedyInt(final String s) {
        try {
            Integer.parseInt(s);
            return s;
        }
        catch (NumberFormatException ex) {
            if (s.length() > 1) {
                return this.parseGreedyInt(s.substring(0, s.length() - 1));
            }
            return "\u0000";
        }
    }
    
    private void consumeGreedy(final StreamPosTokenizer streamPosTokenizer, final String s) {
        if (s.length() < streamPosTokenizer.sval.length()) {
            streamPosTokenizer.pushBack();
            streamPosTokenizer.setStartPosition(streamPosTokenizer.getStartPosition() + s.length());
            streamPosTokenizer.sval = streamPosTokenizer.sval.substring(s.length());
        }
    }
    
    public String getFirstToken(final int n) {
        return (0 <= n && n < this.tokens.length && this.tokens[n] != null && this.tokens[n].length > 0) ? this.tokens[n][0] : null;
    }
    
    public int getSymbol(final String s) {
        Integer n = this.transformationMap.get(s);
        if (n == null) {
            n = this.permutationMap.get(s);
        }
        return (n == null) ? -1 : n;
    }
    
    public static void applyTo(final RubiksCubeCore rubiksCubeCore, final int n, final boolean b) {
        if (0 <= n && n < 84) {
            final int angle = getAngle(n);
            rubiksCubeCore.transform(getAxis(n), getLayerMask(n), b ? (-angle) : angle);
        }
    }
    
    public static int getAxis(final int n) {
        if (0 <= n && n < 84) {
            return n % 3;
        }
        return -1;
    }
    
    public static int getAngle(final int n) {
        if (0 <= n && n < 84) {
            int n2 = (n / 3 % 2 == 0) ? 1 : -1;
            if (n <= 47) {
                n2 = ((n / 6 % 2 == 0) ? n2 : (-n2));
            }
            if ((12 <= n && n <= 23) || (36 <= n && n <= 47) || (54 <= n && n <= 59) || (66 <= n && n <= 71) || (78 <= n && n <= 83)) {
                n2 *= 2;
            }
            return n2;
        }
        return 0;
    }
    
    public static int getLayerMask(final int n) {
        if (0 > n || n > 83) {
            return 0;
        }
        if (n <= 23) {
            return (n / 3 % 2 == 1) ? 1 : 4;
        }
        if (n <= 47) {
            return (n / 3 % 2 == 1) ? 3 : 6;
        }
        if (n <= 59) {
            return 2;
        }
        if (n <= 71) {
            return 5;
        }
        return 7;
    }
    
    public static int getSymbol(final int n, final int n2, final int n3) {
        if (n == -1 || n2 == 0 || n3 == 0) {
            return 84;
        }
        final int n4 = (n3 == 2 || n3 == -2) ? 1 : 0;
        final int n5 = (n3 < 0) ? 1 : 0;
        switch (n2) {
            case 1: {
                return 3 + n + n4 * 12 + (1 - n5) * 6;
            }
            case 2: {
                return 48 + n + n4 * 6 + n5 * 3;
            }
            case 3: {
                return 27 + n + n4 * 12 + (1 - n5) * 6;
            }
            case 4: {
                return 0 + n + n4 * 12 + n5 * 6;
            }
            case 5: {
                return 60 + n + n4 * 12 + n5 * 6;
            }
            case 6: {
                return 24 + n + n4 * 12 + n5 * 6;
            }
            case 7: {
                return 72 + n + n4 * 6 + n5 * 3;
            }
            default: {
                return 84;
            }
        }
    }
    
    public static int inverseSymbol(final int n) {
        if (n >= 0) {
            if (n <= 47) {
                if (n / 6 % 2 == 0) {
                    return n + 6;
                }
                return n - 6;
            }
            else if (n <= 83) {
                if ((n - 48) / 3 % 2 == 0) {
                    return n + 3;
                }
                return n - 3;
            }
        }
        return n;
    }
    
    public static int reflectSymbol(final int n) {
        if (0 <= n && n <= 84) {
            final int axis = getAxis(n);
            final int layerMask = getLayerMask(n);
            return getSymbol(axis, (layerMask & 0x4) >>> 2 | (layerMask & 0x2) | (layerMask & 0x1) << 2, getAngle(n));
        }
        return n;
    }
    
    public static boolean isRotationSymbol(final int n) {
        return 72 <= n && n <= 83;
    }
    
    public static boolean isMidlayerSymbol(final int n) {
        return 48 <= n && n <= 59;
    }
    
    public static boolean isSliceSymbol(final int n) {
        return 60 <= n && n <= 71;
    }
    
    public static int transformSymbol(final int n, final int n2) {
        if (n == 96) {
            return reflectSymbol(n2);
        }
        if (n == 95) {
            return inverseSymbol(n2);
        }
        if (n2 >= 84 || n >= 84) {
            return n2;
        }
        final int axis = getAxis(n2);
        final int axis2 = getAxis(n);
        final int angle = getAngle(n2);
        final int angle2 = getAngle(n);
        final int layerMask = getLayerMask(n2);
        int n3 = -1;
        int n4 = -1;
        int n5 = -1;
        final int n6 = (layerMask & 0x4) >>> 2 | (layerMask & 0x2) | (layerMask & 0x1) << 2;
        if (axis == axis2 || angle2 == 0) {
            return n2;
        }
        Label_0541: {
            switch (axis2) {
                case 0: {
                    switch (angle2) {
                        case 1: {
                            n3 = ((axis == 1) ? 2 : 1);
                            n5 = ((axis == 1) ? n6 : layerMask);
                            n4 = ((axis == 1) ? (-angle) : angle);
                            break;
                        }
                        case -1: {
                            n3 = ((axis == 2) ? 1 : 2);
                            n5 = ((axis == 2) ? n6 : layerMask);
                            n4 = ((axis == 2) ? (-angle) : angle);
                            break;
                        }
                        case -2:
                        case 2: {
                            n3 = axis;
                            n4 = -angle;
                            n5 = n6;
                            break;
                        }
                    }
                    break;
                }
                case 1: {
                    switch (angle2) {
                        case 1: {
                            n3 = ((axis == 2) ? 0 : 2);
                            n5 = ((axis == 2) ? n6 : layerMask);
                            n4 = ((axis == 2) ? (-angle) : angle);
                            break;
                        }
                        case -1: {
                            n3 = ((axis == 0) ? 2 : 0);
                            n5 = ((axis == 0) ? n6 : layerMask);
                            n4 = ((axis == 0) ? (-angle) : angle);
                            break;
                        }
                        case -2:
                        case 2: {
                            n3 = axis;
                            n5 = n6;
                            n4 = -angle;
                            break;
                        }
                    }
                    break;
                }
                case 2: {
                    switch (angle2) {
                        case 1: {
                            n3 = ((axis == 0) ? 1 : 0);
                            n5 = ((axis == 0) ? n6 : layerMask);
                            n4 = ((axis == 0) ? (-angle) : angle);
                            break Label_0541;
                        }
                        case -1: {
                            n3 = ((axis != 1) ? 1 : 0);
                            n5 = ((axis == 1) ? n6 : layerMask);
                            n4 = ((axis == 1) ? (-angle) : angle);
                            break Label_0541;
                        }
                        case -2:
                        case 2: {
                            n3 = axis;
                            n5 = n6;
                            n4 = -angle;
                            break Label_0541;
                        }
                    }
                    break;
                }
            }
        }
        return getSymbol(n3, n5, n4);
    }
    
    public String toString() {
        final StringBuffer sb = new StringBuffer();
        sb.append(this.getClass().getName());
        sb.append('\n');
        for (int i = 0; i < this.tokens.length; ++i) {
            sb.append(i + ":");
            for (int j = 0; j < this.tokens[i].length; ++j) {
                if (j != 0) {
                    sb.append(",");
                }
                sb.append(this.tokens[i][j]);
            }
            sb.append('\n');
        }
        return sb.toString();
    }
    
    private static String[] getDefaultTokens() {
        if (ScriptParser.defaultTokens == null) {
            ScriptParser.defaultTokens = new String[113];
            final StringTokenizer stringTokenizer = new StringTokenizer("R;U;F;L;D;B;Ri;Ui;Fi;Li;Di;Bi;R2;U2;F2;L2;D2;B2;R2i;U2i;F2i;L2i;D2i;B2i;TR;TU;TF;TL;TD;TB;TRi;TUi;TFi;TLi;TDi;TBi;TR2;TU2;TF2;TL2;TD2;TB2;TR2i;TU2i;TF2i;TL2i;TD2i;TB2i;MR;MU;MF;ML;MD;MB;MR2;MU2;MF2;ML2;MD2;MB2;SR;SU;SF;SL;SD;SB;SR2;SU2;SF2;SL2;SD2;SB2;CR;CU;CF;CL;CD;CB;CR2;CU2;CF2;CL2;CD2;CB2;NOP;permR;permU;permB;permL;permD;permF;permPlus;permMinus;permPlusPlus;statementDelimiter;invertor;reflector;sequenceBegin;sequenceEnd;permutationDelimiter;permutationBegin;permutationEnd;repetitorBegin;repetitorEnd;commutatorBegin;commutatorEnd;commutatorDelimiter;conjugatorBegin;conjugatorEnd;conjugatorDelimiter;commentBegin;commentEnd;singleLineCommentBegin;", ";", false);
            int n = 0;
            while (stringTokenizer.hasMoreTokens()) {
                final String nextToken = stringTokenizer.nextToken();
                final int index;
                ScriptParser.defaultTokens[n++] = (((index = nextToken.indexOf(32)) == -1) ? nextToken : nextToken.substring(0, index));
            }
        }
        return ScriptParser.defaultTokens;
    }
}

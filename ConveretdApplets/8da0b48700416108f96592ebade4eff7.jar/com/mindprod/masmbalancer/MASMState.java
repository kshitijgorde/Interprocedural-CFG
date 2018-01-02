// 
// Decompiled by Procyon v0.5.30
// 

package com.mindprod.masmbalancer;

import java.io.ObjectInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import com.mindprod.entities.EntifyStrings;
import com.mindprod.fastcat.FastCat;
import com.mindprod.common11.ST;
import java.util.regex.Pattern;

public enum MASMState
{
    AT_FIRST_COLUMN {
        void consume(final char c) {
        }
        
        void leaving() {
        }
        
        MASMState next(final MASMCharCategory category, final char nextChar, final boolean first) {
            switch (category) {
                case EOL: {
                    MASMState.how = HowToProcess.FORWARD;
                    return MASMState$1.AT_END_OF_LINE;
                }
                case SPACE: {
                    MASMState.how = HowToProcess.DISCARD;
                    return MASMState$1.SEEKING_OPERATOR;
                }
                case PLAIN: {
                    if (isComing("comment ")) {
                        MASMState.how = HowToProcess.FORWARD;
                        return MASMState$1.IN_MULTILINE_COMMENT;
                    }
                    MASMState.how = HowToProcess.FORWARD;
                    return MASMState$1.IN_LABEL;
                }
                case QUOTE:
                case TICK: {
                    MASMState.how = HowToProcess.FORWARD;
                    return MASMState$1.IN_LABEL;
                }
                case SEMICOLON: {
                    MASMState.how = HowToProcess.FORWARD;
                    return MASMState$1.IN_LINE_COMMENT;
                }
                default: {
                    assert false : "bad state " + category + " " + nextChar;
                    return null;
                }
            }
        }
    }, 
    IN_LABEL {
        void consume(final char c) {
            MASMState.accumulatedLabel.append(c);
        }
        
        void leaving() {
        }
        
        MASMState next(final MASMCharCategory category, final char nextChar, final boolean first) {
            switch (category) {
                case EOL: {
                    MASMState.how = HowToProcess.FORWARD;
                    return MASMState$2.AT_END_OF_LINE;
                }
                case SPACE: {
                    MASMState.how = HowToProcess.DISCARD;
                    return MASMState$2.SEEKING_OPERATOR;
                }
                case PLAIN:
                case QUOTE:
                case TICK: {
                    MASMState.how = HowToProcess.CONSUME;
                    return MASMState$2.IN_LABEL;
                }
                case SEMICOLON: {
                    MASMState.how = HowToProcess.FORWARD;
                    return MASMState$2.IN_TAIL_COMMENT;
                }
                default: {
                    assert false : "bad state " + category + " " + nextChar;
                    return null;
                }
            }
        }
    }, 
    SEEKING_OPERATOR {
        void consume(final char c) {
        }
        
        void leaving() {
        }
        
        MASMState next(final MASMCharCategory category, final char nextChar, final boolean first) {
            switch (category) {
                case EOL: {
                    MASMState.how = HowToProcess.FORWARD;
                    return MASMState$3.AT_END_OF_LINE;
                }
                case SPACE: {
                    MASMState.how = HowToProcess.DISCARD;
                    return MASMState$3.SEEKING_OPERATOR;
                }
                case PLAIN:
                case QUOTE:
                case TICK: {
                    MASMState.how = HowToProcess.FORWARD;
                    return MASMState$3.IN_OPERATOR;
                }
                case SEMICOLON: {
                    MASMState.how = HowToProcess.FORWARD;
                    return MASMState$3.IN_TAIL_COMMENT;
                }
                default: {
                    assert false : "bad state " + category + " " + nextChar;
                    return null;
                }
            }
        }
    }, 
    IN_OPERATOR {
        void consume(final char c) {
            MASMState.accumulatedOperator.append(c);
        }
        
        void leaving() {
        }
        
        MASMState next(final MASMCharCategory category, final char nextChar, final boolean first) {
            switch (category) {
                case EOL: {
                    MASMState.how = HowToProcess.FORWARD;
                    return MASMState$4.AT_END_OF_LINE;
                }
                case SPACE: {
                    MASMState.how = HowToProcess.DISCARD;
                    return MASMState$4.SEEKING_OPERAND;
                }
                case PLAIN:
                case QUOTE:
                case TICK: {
                    MASMState.how = HowToProcess.CONSUME;
                    return MASMState$4.IN_OPERATOR;
                }
                case SEMICOLON: {
                    MASMState.how = HowToProcess.FORWARD;
                    return MASMState$4.IN_TAIL_COMMENT;
                }
                default: {
                    assert false : "bad state " + category + " " + nextChar;
                    return null;
                }
            }
        }
    }, 
    SEEKING_OPERAND {
        void consume(final char c) {
        }
        
        void leaving() {
        }
        
        MASMState next(final MASMCharCategory category, final char nextChar, final boolean first) {
            switch (category) {
                case EOL: {
                    MASMState.how = HowToProcess.FORWARD;
                    return MASMState$5.AT_END_OF_LINE;
                }
                case SPACE: {
                    MASMState.how = HowToProcess.DISCARD;
                    return MASMState$5.SEEKING_OPERAND;
                }
                case PLAIN:
                case QUOTE:
                case TICK: {
                    MASMState.how = HowToProcess.FORWARD;
                    return MASMState$5.IN_OPERAND;
                }
                case SEMICOLON: {
                    MASMState.how = HowToProcess.FORWARD;
                    return MASMState$5.IN_TAIL_COMMENT;
                }
                default: {
                    assert false : "bad state " + category + " " + nextChar;
                    return null;
                }
            }
        }
    }, 
    IN_OPERAND {
        void consume(final char c) {
            MASMState.accumulatedOperand.append(c);
        }
        
        void leaving() {
        }
        
        MASMState next(final MASMCharCategory category, final char nextChar, final boolean first) {
            switch (category) {
                case EOL: {
                    MASMState.how = HowToProcess.FORWARD;
                    return MASMState$6.AT_END_OF_LINE;
                }
                case SPACE:
                case PLAIN: {
                    MASMState.how = HowToProcess.CONSUME;
                    return MASMState$6.IN_OPERAND;
                }
                case QUOTE: {
                    MASMState.how = HowToProcess.FORWARD;
                    return MASMState$6.IN_QUOTE_OPERAND;
                }
                case TICK: {
                    MASMState.how = HowToProcess.FORWARD;
                    return MASMState$6.IN_TICK_OPERAND;
                }
                case SEMICOLON: {
                    MASMState.how = HowToProcess.FORWARD;
                    return MASMState$6.IN_TAIL_COMMENT;
                }
                default: {
                    assert false : "bad state " + category + " " + nextChar;
                    return null;
                }
            }
        }
    }, 
    IN_TICK_OPERAND {
        void consume(final char c) {
            MASMState.accumulatedOperand.append(c);
        }
        
        void leaving() {
        }
        
        MASMState next(final MASMCharCategory category, final char nextChar, final boolean first) {
            switch (category) {
                case EOL: {
                    MASMState.how = HowToProcess.FORWARD;
                    return MASMState$7.AT_END_OF_LINE;
                }
                case SPACE:
                case PLAIN:
                case QUOTE:
                case SEMICOLON: {
                    MASMState.how = HowToProcess.CONSUME;
                    return MASMState$7.IN_TICK_OPERAND;
                }
                case TICK: {
                    MASMState.how = HowToProcess.CONSUME;
                    return MASMState$7.IN_OPERAND;
                }
                default: {
                    assert false : "bad state " + category + " " + nextChar;
                    return null;
                }
            }
        }
    }, 
    IN_QUOTE_OPERAND {
        void consume(final char c) {
            MASMState.accumulatedOperand.append(c);
        }
        
        void leaving() {
        }
        
        MASMState next(final MASMCharCategory category, final char nextChar, final boolean first) {
            switch (category) {
                case EOL: {
                    MASMState.how = HowToProcess.FORWARD;
                    return MASMState$8.AT_END_OF_LINE;
                }
                case SPACE:
                case PLAIN:
                case TICK:
                case SEMICOLON: {
                    MASMState.how = HowToProcess.CONSUME;
                    return MASMState$8.IN_QUOTE_OPERAND;
                }
                case QUOTE: {
                    MASMState.how = HowToProcess.CONSUME;
                    return MASMState$8.IN_OPERAND;
                }
                default: {
                    assert false : "bad state " + category + " " + nextChar;
                    return null;
                }
            }
        }
    }, 
    IN_LINE_COMMENT {
        void consume(final char c) {
            MASMState.accumulatedLineComment.append(c);
        }
        
        void leaving() {
        }
        
        MASMState next(final MASMCharCategory category, final char nextChar, final boolean first) {
            switch (category) {
                case EOL: {
                    MASMState.how = HowToProcess.FORWARD;
                    return MASMState$9.AT_END_OF_LINE;
                }
                case SPACE:
                case PLAIN:
                case QUOTE:
                case TICK:
                case SEMICOLON: {
                    MASMState.how = HowToProcess.CONSUME;
                    return MASMState$9.IN_LINE_COMMENT;
                }
                default: {
                    assert false : "bad state " + category + " " + nextChar;
                    return null;
                }
            }
        }
    }, 
    IN_TAIL_COMMENT {
        void consume(final char c) {
            MASMState.accumulatedTailComment.append(c);
        }
        
        void leaving() {
        }
        
        MASMState next(final MASMCharCategory category, final char nextChar, final boolean first) {
            switch (category) {
                case EOL: {
                    MASMState.how = HowToProcess.FORWARD;
                    return MASMState$10.AT_END_OF_LINE;
                }
                case SPACE:
                case PLAIN:
                case QUOTE:
                case TICK:
                case SEMICOLON: {
                    MASMState.how = HowToProcess.CONSUME;
                    return MASMState$10.IN_TAIL_COMMENT;
                }
                default: {
                    assert false : "bad state " + category + " " + nextChar;
                    return null;
                }
            }
        }
    }, 
    IN_MULTILINE_COMMENT {
        char delimiter;
        
        void consume(final char c) {
            MASMState.accumulatedMultilineComment.append(c);
        }
        
        void leaving() {
        }
        
        MASMState next(final MASMCharCategory category, final char nextChar, final boolean first) {
            if (first) {
                this.delimiter = '\0';
            }
            switch (category) {
                case EOL: {
                    MASMState.how = HowToProcess.CONSUME;
                    return MASMState$11.IN_MULTILINE_COMMENT;
                }
                case PLAIN: {
                    if (this.delimiter == '\0' && !ST.isLegal(nextChar, "coment COMENT")) {
                        this.delimiter = nextChar;
                    }
                    else if (nextChar == this.delimiter) {
                        MASMState.how = HowToProcess.CONSUME;
                        return MASMState$11.AT_FIRST_COLUMN;
                    }
                    MASMState.how = HowToProcess.CONSUME;
                    return MASMState$11.IN_MULTILINE_COMMENT;
                }
                case SPACE:
                case QUOTE:
                case TICK:
                case SEMICOLON: {
                    MASMState.how = HowToProcess.CONSUME;
                    return MASMState$11.IN_MULTILINE_COMMENT;
                }
                default: {
                    assert false : "bad state " + category + " " + nextChar;
                    return null;
                }
            }
        }
    }, 
    AT_END_OF_LINE {
        void consume(final char c) {
        }
        
        void leaving() {
            buildLine();
        }
        
        MASMState next(final MASMCharCategory category, final char nextChar, final boolean first) {
            switch (category) {
                case EOL: {
                    MASMState.how = HowToProcess.DISCARD;
                    return MASMState$12.AT_FIRST_COLUMN;
                }
                case SPACE:
                case PLAIN:
                case QUOTE:
                case TICK:
                case SEMICOLON: {
                    MASMState.how = HowToProcess.FORWARD;
                    return MASMState$12.AT_FIRST_COLUMN;
                }
                default: {
                    assert false : "bad state " + category + " " + nextChar;
                    return null;
                }
            }
        }
    };
    
    private static final boolean DEBUGGING = false;
    private static final int COL_FOR_OPERAND = 25;
    private static final int COL_FOR_OPERATOR = 15;
    private static final int COL_FOR_TAIL_COMMENT = 40;
    private static final String DOCTYPE = "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">";
    private static final String lineSeparator;
    private static final String styleSheet = "<style type=\"text/css\">\nbody{font-family:\"Bitstream Vera Sans Mono\",\"Lucida Console\",\"Lucida Sans\",\"Lucida Sans Unicode\",\"Segoe UI\",monospace;}\nspan.boundary{color:#000000;font-weight:bold;}\nspan.label{color:#ce0f14;}\nspan.line{color:#5c9548;}\nspan.multi{color:#415fbf;}\nspan.operand{color:#8b7355;}\nspan.operator{color:#000000;}\nspan.tail{color:#5c9548;}\nspan.spanner{color:#ff7256;font-family:monospace;font-weight:bold;}\n</style>\n";
    private static final Pattern SPLITTER;
    private static final StringBuilder accumulatedLabel;
    private static final StringBuilder accumulatedLineComment;
    private static final StringBuilder accumulatedMultilineComment;
    private static final StringBuilder accumulatedOperand;
    private static final StringBuilder accumulatedOperator;
    private static final StringBuilder accumulatedTailComment;
    private static Balancer balancer;
    private static int charIndex;
    private static int deepestNestingDepthForIf;
    private static int deepestNestingDepthForMacro;
    private static int deepestNestingDepthForProc;
    private static int deepestNestingDepthForSegment;
    private static HowToProcess how;
    private static int nestingDepthForIf;
    private static int nestingDepthForMacro;
    private static int nestingDepthForProc;
    private static int nestingDepthForSegment;
    private static boolean prevLineEmpty;
    private static String program;
    private static StringBuilder sb;
    private static int size;
    private static String spanner;
    
    public static String colorise(final String program, final Balancer balancer) {
        MASMState.program = program;
        MASMState.size = program.length();
        MASMState.balancer = balancer;
        resetDeepestNestingDepths();
        calcNestingDepths(program);
        return parseProgram(program);
    }
    
    private static void buildLine() {
        final String multiLineComment = MASMState.accumulatedMultilineComment.toString();
        if (multiLineComment.length() > 0) {
            buildMultiCommentLine(multiLineComment);
        }
        final String lineComment = MASMState.accumulatedLineComment.toString().trim();
        if (lineComment.length() > 0) {
            buildLineCommentLine(lineComment);
        }
        else {
            buildTupleLine();
        }
    }
    
    private static void buildLineCommentLine(final String lineComment) {
        MASMState.spanner = buildSpanner();
        if (MASMState.sb != null) {
            MASMState.sb.append(mark(MASMState.spanner, "spanner"));
            MASMState.sb.append(mark(lineComment, "line"));
            MASMState.sb.append(MASMState.lineSeparator);
        }
        MASMState.accumulatedLineComment.setLength(0);
        assert MASMState.accumulatedLabel.length() + MASMState.accumulatedOperator.length() + MASMState.accumulatedOperand.length() == 0 : "bug: line comment with label/operator/operand.";
    }
    
    private static void buildMultiCommentLine(final String multiLineComment) {
        MASMState.spanner = buildSpanner();
        if (MASMState.sb != null) {
            final String[] arr$;
            final String[] mls = arr$ = MASMState.SPLITTER.split(multiLineComment);
            for (final String ml : arr$) {
                MASMState.sb.append(mark(MASMState.spanner, "spanner"));
                MASMState.sb.append(mark(ml, "multi"));
                MASMState.sb.append(MASMState.lineSeparator);
            }
        }
        MASMState.accumulatedMultilineComment.setLength(0);
    }
    
    private static String buildOneSpanner(final int nestingDepth, final int deepestNestingDepth) {
        final StringBuffer sb = new StringBuffer(deepestNestingDepth);
        for (int i = 0; i < nestingDepth; ++i) {
            sb.append('|');
        }
        sb.append(ST.spaces(deepestNestingDepth - nestingDepth));
        return sb.toString();
    }
    
    private static String buildSpanner() {
        switch (MASMState.balancer) {
            case ALL: {
                return "";
            }
            case SEGMENT: {
                return buildOneSpanner(MASMState.nestingDepthForSegment, MASMState.deepestNestingDepthForSegment);
            }
            case MACRO: {
                return buildOneSpanner(MASMState.nestingDepthForMacro, MASMState.deepestNestingDepthForMacro);
            }
            case PROC: {
                return buildOneSpanner(MASMState.nestingDepthForProc, MASMState.deepestNestingDepthForProc);
            }
            case IF: {
                return buildOneSpanner(MASMState.nestingDepthForIf, MASMState.deepestNestingDepthForIf);
            }
            default: {
                throw new IllegalArgumentException("MASMState program bug. unknown Balancer: " + MASMState.balancer);
            }
        }
    }
    
    private static void buildTupleLine() {
        final String label = MASMState.accumulatedLabel.toString().trim();
        MASMState.accumulatedLabel.setLength(0);
        String operator = MASMState.accumulatedOperator.toString().trim();
        MASMState.accumulatedOperator.setLength(0);
        operator = operator.toLowerCase();
        final String markedOperator = markOperator(operator);
        final String operand = MASMState.accumulatedOperand.toString().trim();
        MASMState.accumulatedOperand.setLength(0);
        final String tailComment = MASMState.accumulatedTailComment.toString();
        MASMState.accumulatedTailComment.setLength(0);
        int col = label.length();
        final int spacesBeforeOperator = Math.max(1, 15 - col);
        col += spacesBeforeOperator;
        col += operator.length();
        final int spacesBeforeOperand = Math.max(1, 25 - col);
        col += spacesBeforeOperand;
        col += operand.length();
        final int spacesBeforeTailComment = Math.max(1, 40 - col);
        final FastCat fb = new FastCat(8);
        if (label.length() > 0 || operator.length() > 0 || operand.length() > 0 || tailComment.length() > 0) {
            fb.append(mark(label, "label"));
            if (operator.length() > 0 || operand.length() > 0 || tailComment.length() > 0) {
                fb.append(ST.spaces(spacesBeforeOperator));
                fb.append(markedOperator);
                if (operand.length() > 0 || tailComment.length() > 0) {
                    fb.append(ST.spaces(spacesBeforeOperand));
                    fb.append(mark(operand, "operand"));
                    if (tailComment.length() > 0) {
                        fb.append(ST.spaces(spacesBeforeTailComment));
                        fb.append(mark(tailComment, "tail"));
                    }
                }
            }
        }
        final String theLine = fb.toString();
        if (theLine.length() == 0) {
            if (!MASMState.prevLineEmpty) {
                if (MASMState.sb != null) {
                    MASMState.spanner = buildSpanner();
                    MASMState.sb.append(mark(MASMState.spanner, "spanner"));
                    MASMState.sb.append(MASMState.lineSeparator);
                }
                MASMState.prevLineEmpty = true;
            }
        }
        else {
            if (MASMState.sb != null) {
                MASMState.sb.append(mark(MASMState.spanner, "spanner"));
                MASMState.sb.append(theLine);
                MASMState.sb.append(MASMState.lineSeparator);
            }
            MASMState.prevLineEmpty = false;
        }
    }
    
    private static int calcNestingBackgroundColor() {
        int rgb = 0;
        switch (MASMState.balancer) {
            case ALL: {
                if (MASMState.nestingDepthForSegment < 0 || MASMState.nestingDepthForMacro < 0 || MASMState.nestingDepthForProc < 0 || MASMState.nestingDepthForIf < 0) {
                    rgb = 16776960;
                    break;
                }
                final int depths = 100 / Math.max(1, MASMState.deepestNestingDepthForSegment) * MASMState.nestingDepthForSegment;
                final int depthi = 40 / Math.max(1, MASMState.deepestNestingDepthForIf) * MASMState.nestingDepthForIf;
                final int depthm = 100 / Math.max(1, MASMState.deepestNestingDepthForMacro) * MASMState.nestingDepthForMacro;
                final int depthp = 50 / Math.max(1, MASMState.deepestNestingDepthForProc) * MASMState.nestingDepthForProc;
                final int r = (510 - depthm + 255 - depthp + 255 - depthi) / 4;
                final int g = (255 - depths + 255 + 255 - depthp + 255 - depthi) / 4;
                final int b = (255 - depths + 255 - depthm + 255 + 255 - depthi) / 4;
                rgb = (r << 16 | g << 8 | b);
                break;
            }
            case SEGMENT: {
                if (MASMState.nestingDepthForSegment < 0) {
                    rgb = 16776960;
                    break;
                }
                final int depths = 100 / Math.max(1, MASMState.deepestNestingDepthForSegment) * MASMState.nestingDepthForSegment;
                final int r2 = 255;
                final int g2 = 255 - depths;
                final int b2 = 255 - depths;
                rgb = (0xFF0000 | g2 << 8 | b2);
                break;
            }
            case MACRO: {
                if (MASMState.nestingDepthForMacro < 0) {
                    rgb = 16776960;
                    break;
                }
                final int depthm2 = 100 / Math.max(1, MASMState.deepestNestingDepthForMacro) * MASMState.nestingDepthForMacro;
                final int r2 = 255 - depthm2;
                final int g2 = 255;
                final int b2 = 255 - depthm2;
                rgb = (r2 << 16 | 0xFF00 | b2);
                break;
            }
            case PROC: {
                if (MASMState.nestingDepthForProc < 0) {
                    rgb = 16776960;
                    break;
                }
                final int depthp2 = 50 / Math.max(1, MASMState.deepestNestingDepthForProc) * MASMState.nestingDepthForProc;
                final int r2 = 255 - depthp2;
                final int g2 = 255 - depthp2;
                final int b2 = 255;
                rgb = (r2 << 16 | g2 << 8 | b2);
                break;
            }
            case IF: {
                if (MASMState.nestingDepthForIf < 0) {
                    rgb = 16776960;
                    break;
                }
                final int depthi2 = 40 / Math.max(1, MASMState.deepestNestingDepthForIf) * MASMState.nestingDepthForIf;
                final int g2;
                final int b2;
                final int r2 = b2 = (g2 = 255 - depthi2);
                rgb = (r2 << 16 | g2 << 8 | b2);
                break;
            }
            default: {
                throw new IllegalArgumentException("program bug: unknown Balancer " + MASMState.balancer);
            }
        }
        return rgb;
    }
    
    private static void calcNestingDepths(final String program) {
        reset();
        MASMState oldState = MASMState.AT_FIRST_COLUMN;
        MASMState state = MASMState.AT_FIRST_COLUMN;
        MASMState.how = null;
        MASMState.charIndex = 0;
        while (MASMState.charIndex < MASMState.size) {
            final char theChar = program.charAt(MASMState.charIndex);
            final MASMCharCategory category = MASMCharCategory.categorise(theChar);
            if (category != MASMCharCategory.IGNORE) {
                int times = 0;
            Label_0253:
                while (times < 3) {
                    final boolean first = state != oldState;
                    MASMState.how = null;
                    final MASMState newState = state.next(category, theChar, first);
                    oldState = state;
                    state = newState;
                    if (MASMState.how == null) {
                        throw new NullPointerException("MASMState bug: how not set. OldState=" + oldState + " newState=" + state + " next() must not be private.");
                    }
                    switch (MASMState.how) {
                        case CONSUME: {
                            oldState.consume(theChar);
                            if (state != oldState) {
                                oldState.leaving();
                                break Label_0253;
                            }
                            break Label_0253;
                        }
                        case DISCARD: {
                            if (state != oldState) {
                                oldState.leaving();
                                break Label_0253;
                            }
                            break Label_0253;
                        }
                        default: {
                            assert false : "MASMState failed to set how variable.";
                            break Label_0253;
                        }
                        case FORWARD: {
                            assert state != oldState : "MASMState attempted to forward a char to the same state.";
                            oldState.leaving();
                            ++times;
                            continue;
                        }
                    }
                }
                assert MASMState.how == HowToProcess.DISCARD : "MASMState failed to consume char in three state forwarding attempts.";
            }
            ++MASMState.charIndex;
        }
    }
    
    private static void dumpState(final char theChar, final MASMCharCategory category, final boolean first, final MASMState oldState, final MASMState state, final MASMState newState, final HowToProcess how) {
        if (how == HowToProcess.CONSUME) {
            System.out.println(theChar + " " + category + " " + first + " o:" + oldState + " s:" + state + " n:" + newState);
        }
        else {
            System.err.println(theChar + " " + category + " " + first + " o:" + oldState + " s:" + state + " n:" + newState + " " + how);
        }
    }
    
    private static boolean isComing(final String expected) {
        return MASMState.charIndex + expected.length() < MASMState.size && MASMState.program.substring(MASMState.charIndex, MASMState.charIndex + expected.length()).equalsIgnoreCase(expected);
    }
    
    private static String mark(final String s, final String cssClass) {
        if (s.length() == 0) {
            return "";
        }
        assert cssClass != null && cssClass.length() > 0 : "empty css class";
        final FastCat sb = new FastCat(5);
        sb.append("<span class=\"");
        sb.append(cssClass);
        sb.append("\">");
        sb.append(EntifyStrings.entifyHTML(s));
        sb.append("</span>");
        return sb.toString();
    }
    
    private static String markOperator(final String operator) {
        if (operator.length() == 0) {
            return "";
        }
        boolean boundary = false;
        if (operator.equals("segment") || operator.equals("struc")) {
            ++MASMState.nestingDepthForSegment;
            if (MASMState.nestingDepthForSegment > MASMState.deepestNestingDepthForSegment) {
                MASMState.deepestNestingDepthForSegment = MASMState.nestingDepthForSegment;
            }
            boundary = true;
        }
        else if (operator.equals("macro")) {
            ++MASMState.nestingDepthForMacro;
            if (MASMState.nestingDepthForMacro > MASMState.deepestNestingDepthForMacro) {
                MASMState.deepestNestingDepthForMacro = MASMState.nestingDepthForMacro;
            }
            boundary = true;
        }
        else if (operator.equals("proc")) {
            ++MASMState.nestingDepthForProc;
            if (MASMState.nestingDepthForProc > MASMState.deepestNestingDepthForProc) {
                MASMState.deepestNestingDepthForProc = MASMState.nestingDepthForProc;
            }
            boundary = true;
        }
        else if (operator.equals("if")) {
            ++MASMState.nestingDepthForIf;
            if (MASMState.nestingDepthForIf > MASMState.deepestNestingDepthForIf) {
                MASMState.deepestNestingDepthForIf = MASMState.nestingDepthForIf;
            }
            boundary = true;
        }
        else if (operator.equals("else")) {
            if (MASMState.nestingDepthForIf == 0) {
                MASMState.nestingDepthForIf = -1;
            }
            boundary = true;
        }
        final int rgb = calcNestingBackgroundColor();
        MASMState.spanner = buildSpanner();
        if (operator.equals("ends")) {
            --MASMState.nestingDepthForSegment;
            boundary = true;
        }
        else if (operator.equals("endm")) {
            --MASMState.nestingDepthForMacro;
            boundary = true;
        }
        else if (operator.equals("endp")) {
            --MASMState.nestingDepthForProc;
            boundary = true;
        }
        else if (operator.equals("endif")) {
            --MASMState.nestingDepthForIf;
            boundary = true;
        }
        else if (operator.equals("else") && MASMState.nestingDepthForIf == -1) {
            MASMState.nestingDepthForIf = 0;
        }
        final FastCat sb = new FastCat(7);
        sb.append("<span class=\"");
        sb.append(boundary ? "boundary" : "operator");
        sb.append("\" style=\"background-color:#");
        sb.append(ST.toLZHexString(rgb, 6));
        sb.append(";\">");
        sb.append(EntifyStrings.entifyHTML(operator));
        sb.append("</span>");
        return sb.toString();
    }
    
    private static String parseProgram(final String program) {
        reset();
        MASMState oldState = MASMState.AT_FIRST_COLUMN;
        MASMState state = MASMState.AT_FIRST_COLUMN;
        MASMState.how = null;
        (MASMState.sb = new StringBuilder(MASMState.size * 3 + 600)).append("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">");
        MASMState.sb.append("<html><head>");
        MASMState.sb.append("<style type=\"text/css\">\nbody{font-family:\"Bitstream Vera Sans Mono\",\"Lucida Console\",\"Lucida Sans\",\"Lucida Sans Unicode\",\"Segoe UI\",monospace;}\nspan.boundary{color:#000000;font-weight:bold;}\nspan.label{color:#ce0f14;}\nspan.line{color:#5c9548;}\nspan.multi{color:#415fbf;}\nspan.operand{color:#8b7355;}\nspan.operator{color:#000000;}\nspan.tail{color:#5c9548;}\nspan.spanner{color:#ff7256;font-family:monospace;font-weight:bold;}\n</style>\n");
        MASMState.sb.append("</head><body><pre>");
        MASMState.charIndex = 0;
        while (MASMState.charIndex < MASMState.size) {
            final char theChar = program.charAt(MASMState.charIndex);
            final MASMCharCategory category = MASMCharCategory.categorise(theChar);
            if (category != MASMCharCategory.IGNORE) {
                int times = 0;
            Label_0309:
                while (times < 3) {
                    final boolean first = state != oldState;
                    MASMState.how = null;
                    final MASMState newState = state.next(category, theChar, first);
                    oldState = state;
                    state = newState;
                    if (MASMState.how == null) {
                        throw new NullPointerException("MASMState bug: how not set. OldState=" + oldState + " newState=" + state + " next() must not be private.");
                    }
                    switch (MASMState.how) {
                        case CONSUME: {
                            oldState.consume(theChar);
                            if (state != oldState) {
                                oldState.leaving();
                                break Label_0309;
                            }
                            break Label_0309;
                        }
                        case DISCARD: {
                            if (state != oldState) {
                                oldState.leaving();
                                break Label_0309;
                            }
                            break Label_0309;
                        }
                        default: {
                            assert false : "MASMState failed to set how variable.";
                            break Label_0309;
                        }
                        case FORWARD: {
                            assert state != oldState : "MASMState attempted to forward a char to the same state.";
                            oldState.leaving();
                            ++times;
                            continue;
                        }
                    }
                }
                assert MASMState.how == HowToProcess.DISCARD : "MASMState failed to consume char in three state forwarding attempts.";
            }
            ++MASMState.charIndex;
        }
        if (state != MASMState.AT_FIRST_COLUMN) {
            buildLine();
        }
        MASMState.sb.append("</pre></body></html>");
        final String result = MASMState.sb.toString();
        MASMState.sb = null;
        return result;
    }
    
    private static void reset() {
        MASMState.nestingDepthForIf = 0;
        MASMState.nestingDepthForMacro = 0;
        MASMState.nestingDepthForProc = 0;
        MASMState.nestingDepthForSegment = 0;
        MASMState.accumulatedLabel.setLength(0);
        MASMState.accumulatedOperator.setLength(0);
        MASMState.accumulatedOperand.setLength(0);
        MASMState.accumulatedTailComment.setLength(0);
        MASMState.accumulatedLineComment.setLength(0);
        MASMState.accumulatedMultilineComment.setLength(0);
        MASMState.how = null;
        MASMState.charIndex = 0;
        MASMState.spanner = "";
    }
    
    private static void resetDeepestNestingDepths() {
        MASMState.deepestNestingDepthForIf = 0;
        MASMState.deepestNestingDepthForMacro = 0;
        MASMState.deepestNestingDepthForProc = 0;
        MASMState.deepestNestingDepthForSegment = 0;
    }
    
    abstract void consume(final char p0);
    
    abstract void leaving();
    
    abstract MASMState next(final MASMCharCategory p0, final char p1, final boolean p2);
    
    static {
        lineSeparator = System.getProperty("line.separator");
        SPLITTER = Pattern.compile("\n");
        accumulatedLabel = new StringBuilder(20);
        accumulatedLineComment = new StringBuilder(150);
        accumulatedMultilineComment = new StringBuilder(1024);
        accumulatedOperand = new StringBuilder(80);
        accumulatedOperator = new StringBuilder(20);
        accumulatedTailComment = new StringBuilder(10);
        MASMState.prevLineEmpty = true;
    }
}

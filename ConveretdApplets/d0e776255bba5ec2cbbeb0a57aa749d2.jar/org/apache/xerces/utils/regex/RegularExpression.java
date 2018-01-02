// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.utils.regex;

import java.text.CharacterIterator;
import java.io.Serializable;

public class RegularExpression implements Serializable
{
    static final boolean DEBUG = false;
    String regex;
    int options;
    int nofparen;
    Token tokentree;
    boolean hasBackReferences;
    transient int minlength;
    transient Op operations;
    transient int numberOfClosures;
    transient Context context;
    transient RangeToken firstChar;
    transient String fixedString;
    transient int fixedStringOptions;
    transient BMPattern fixedStringTable;
    transient boolean fixedStringOnly;
    static final int IGNORE_CASE = 2;
    static final int SINGLE_LINE = 4;
    static final int MULTIPLE_LINES = 8;
    static final int EXTENDED_COMMENT = 16;
    static final int USE_UNICODE_CATEGORY = 32;
    static final int UNICODE_WORD_BOUNDARY = 64;
    static final int PROHIBIT_HEAD_CHARACTER_OPTIMIZATION = 128;
    static final int PROHIBIT_FIXED_STRING_OPTIMIZATION = 256;
    static final int XMLSCHEMA_MODE = 512;
    static final int SPECIAL_COMMA = 1024;
    private static final int WT_IGNORE = 0;
    private static final int WT_LETTER = 1;
    private static final int WT_OTHER = 2;
    static transient Token wordchar;
    static final int LINE_FEED = 10;
    static final int CARRIAGE_RETURN = 13;
    static final int LINE_SEPARATOR = 8232;
    static final int PARAGRAPH_SEPARATOR = 8233;
    
    private synchronized void compile(final Token token) {
        if (this.operations != null) {
            return;
        }
        this.numberOfClosures = 0;
        this.operations = this.compile(token, null, false);
    }
    
    private Op compile(final Token token, Op op, final boolean b) {
        Op op2 = null;
        switch (token.type) {
            case 11: {
                op2 = Op.createDot();
                op2.next = op;
                break;
            }
            case 0: {
                op2 = Op.createChar(token.getChar());
                op2.next = op;
                break;
            }
            case 8: {
                op2 = Op.createAnchor(token.getChar());
                op2.next = op;
                break;
            }
            case 4:
            case 5: {
                op2 = Op.createRange(token);
                op2.next = op;
                break;
            }
            case 1: {
                op2 = op;
                if (!b) {
                    for (int i = token.size() - 1; i >= 0; --i) {
                        op2 = this.compile(token.getChild(i), op2, false);
                    }
                    break;
                }
                for (int j = 0; j < token.size(); ++j) {
                    op2 = this.compile(token.getChild(j), op2, true);
                }
                break;
            }
            case 2: {
                final Op.UnionOp union = Op.createUnion(token.size());
                for (int k = 0; k < token.size(); ++k) {
                    union.addElement(this.compile(token.getChild(k), op, b));
                }
                op2 = union;
                break;
            }
            case 3:
            case 9: {
                final Token child = token.getChild(0);
                final int min = token.getMin();
                int max = token.getMax();
                if (min >= 0 && min == max) {
                    op2 = op;
                    for (int l = 0; l < min; ++l) {
                        op2 = this.compile(child, op2, b);
                    }
                    break;
                }
                if (min > 0 && max > 0) {
                    max -= min;
                }
                if (max > 0) {
                    op2 = op;
                    for (int n = 0; n < max; ++n) {
                        final Op.ChildOp question = Op.createQuestion(token.type == 9);
                        question.next = op;
                        question.setChild(this.compile(child, op2, b));
                        op2 = question;
                    }
                }
                else {
                    Op.ChildOp childOp;
                    if (token.type == 9) {
                        childOp = Op.createNonGreedyClosure();
                    }
                    else if (child.getMinLength() == 0) {
                        childOp = Op.createClosure(this.numberOfClosures++);
                    }
                    else {
                        childOp = Op.createClosure(-1);
                    }
                    childOp.next = op;
                    childOp.setChild(this.compile(child, childOp, b));
                    op2 = childOp;
                }
                if (min > 0) {
                    for (int n2 = 0; n2 < min; ++n2) {
                        op2 = this.compile(child, op2, b);
                    }
                    break;
                }
                break;
            }
            case 7: {
                op2 = op;
                break;
            }
            case 10: {
                op2 = Op.createString(token.getString());
                op2.next = op;
                break;
            }
            case 12: {
                op2 = Op.createBackReference(token.getReferenceNumber());
                op2.next = op;
                break;
            }
            case 6: {
                if (token.getParenNumber() == 0) {
                    op2 = this.compile(token.getChild(0), op, b);
                    break;
                }
                if (b) {
                    op = this.compile(token.getChild(0), Op.createCapture(token.getParenNumber(), op), b);
                    op2 = Op.createCapture(-token.getParenNumber(), op);
                    break;
                }
                op = this.compile(token.getChild(0), Op.createCapture(-token.getParenNumber(), op), b);
                op2 = Op.createCapture(token.getParenNumber(), op);
                break;
            }
            case 20: {
                op2 = Op.createLook(20, op, this.compile(token.getChild(0), null, false));
                break;
            }
            case 21: {
                op2 = Op.createLook(21, op, this.compile(token.getChild(0), null, false));
                break;
            }
            case 22: {
                op2 = Op.createLook(22, op, this.compile(token.getChild(0), null, true));
                break;
            }
            case 23: {
                op2 = Op.createLook(23, op, this.compile(token.getChild(0), null, true));
                break;
            }
            case 24: {
                op2 = Op.createIndependent(op, this.compile(token.getChild(0), null, b));
                break;
            }
            case 25: {
                op2 = Op.createModifier(op, this.compile(token.getChild(0), null, b), ((Token.ModifierToken)token).getOptions(), ((Token.ModifierToken)token).getOptionsMask());
                break;
            }
            case 26: {
                final Token.ConditionToken conditionToken = (Token.ConditionToken)token;
                op2 = Op.createCondition(op, conditionToken.refNumber, (conditionToken.condition == null) ? null : this.compile(conditionToken.condition, null, b), this.compile(conditionToken.yes, op, b), (conditionToken.no == null) ? null : this.compile(conditionToken.no, op, b));
                break;
            }
            default: {
                throw new RuntimeException("Unknown token type: " + token.type);
            }
        }
        return op2;
    }
    
    public boolean matches(final char[] array) {
        return this.matches(array, 0, array.length, null);
    }
    
    public boolean matches(final char[] array, final int n, final int n2) {
        return this.matches(array, n, n2, null);
    }
    
    public boolean matches(final char[] array, final Match match) {
        return this.matches(array, 0, array.length, match);
    }
    
    public boolean matches(final char[] source, final int n, final int n2, Match match) {
        synchronized (this) {
            if (this.operations == null) {
                this.prepare();
            }
            if (this.context == null) {
                this.context = new Context();
            }
        }
        Context context = null;
        synchronized (this.context) {
            context = (this.context.inuse ? new Context() : this.context);
            context.reset(source, n, n2, this.numberOfClosures);
        }
        if (match != null) {
            match.setNumberOfGroups(this.nofparen);
            match.setSource(source);
        }
        else if (this.hasBackReferences) {
            match = new Match();
            match.setNumberOfGroups(this.nofparen);
        }
        context.match = match;
        if (isSet(this.options, 512)) {
            final int matchCharArray = this.matchCharArray(context, this.operations, context.start, 1, this.options);
            if (matchCharArray == context.limit) {
                if (context.match != null) {
                    context.match.setBeginning(0, context.start);
                    context.match.setEnd(0, matchCharArray);
                }
                context.inuse = false;
                return true;
            }
            return false;
        }
        else if (this.fixedStringOnly) {
            final int matches = this.fixedStringTable.matches(source, context.start, context.limit);
            if (matches >= 0) {
                if (context.match != null) {
                    context.match.setBeginning(0, matches);
                    context.match.setEnd(0, matches + this.fixedString.length());
                }
                context.inuse = false;
                return true;
            }
            return context.inuse = false;
        }
        else {
            if (this.fixedString != null && this.fixedStringTable.matches(source, context.start, context.limit) < 0) {
                return context.inuse = false;
            }
            final int n3 = context.limit - this.minlength;
            int n4 = -1;
            int i;
            if (this.operations != null && this.operations.type == 7 && this.operations.getChild().type == 0) {
                if (isSet(this.options, 4)) {
                    i = context.start;
                    n4 = this.matchCharArray(context, this.operations, context.start, 1, this.options);
                }
                else {
                    int n5 = 1;
                    for (i = context.start; i <= n3; ++i) {
                        if (isEOLChar(source[i])) {
                            n5 = 1;
                        }
                        else {
                            if (n5 != 0 && 0 <= (n4 = this.matchCharArray(context, this.operations, i, 1, this.options))) {
                                break;
                            }
                            n5 = 0;
                        }
                    }
                }
            }
            else if (this.firstChar != null) {
                final RangeToken firstChar = this.firstChar;
                if (isSet(this.options, 2)) {
                    final RangeToken caseInsensitiveToken = this.firstChar.getCaseInsensitiveToken();
                    for (i = context.start; i <= n3; ++i) {
                        final char c = source[i];
                        if (REUtil.isHighSurrogate(c) && i + 1 < context.limit) {
                            if (!caseInsensitiveToken.match(REUtil.composeFromSurrogates(c, source[i + 1]))) {
                                continue;
                            }
                        }
                        else if (!caseInsensitiveToken.match(c)) {
                            final char upperCase = Character.toUpperCase(c);
                            if (!caseInsensitiveToken.match(upperCase) && !caseInsensitiveToken.match(Character.toLowerCase(upperCase))) {
                                continue;
                            }
                        }
                        if (0 <= (n4 = this.matchCharArray(context, this.operations, i, 1, this.options))) {
                            break;
                        }
                    }
                }
                else {
                    for (i = context.start; i <= n3; ++i) {
                        int composeFromSurrogates = source[i];
                        if (REUtil.isHighSurrogate(composeFromSurrogates) && i + 1 < context.limit) {
                            composeFromSurrogates = REUtil.composeFromSurrogates(composeFromSurrogates, source[i + 1]);
                        }
                        if (firstChar.match(composeFromSurrogates)) {
                            if (0 <= (n4 = this.matchCharArray(context, this.operations, i, 1, this.options))) {
                                break;
                            }
                        }
                    }
                }
            }
            else {
                for (i = context.start; i <= n3; ++i) {
                    if (0 <= (n4 = this.matchCharArray(context, this.operations, i, 1, this.options))) {
                        break;
                    }
                }
            }
            if (n4 >= 0) {
                if (context.match != null) {
                    context.match.setBeginning(0, i);
                    context.match.setEnd(0, n4);
                }
                context.inuse = false;
                return true;
            }
            return context.inuse = false;
        }
    }
    
    private int matchCharArray(final Context context, Op op, int n, final int n2, final int n3) {
        final char[] charTarget = context.charTarget;
        while (op != null) {
            if (n > context.limit || n < context.start) {
                return -1;
            }
            switch (op.type) {
                case 1: {
                    if (isSet(n3, 2)) {
                        final int data = op.getData();
                        if (n2 > 0) {
                            if (n >= context.limit || !matchIgnoreCase(data, charTarget[n])) {
                                return -1;
                            }
                            ++n;
                        }
                        else {
                            final int n4 = n - 1;
                            if (n4 >= context.limit || n4 < 0 || !matchIgnoreCase(data, charTarget[n4])) {
                                return -1;
                            }
                            n = n4;
                        }
                    }
                    else {
                        final int data2 = op.getData();
                        if (n2 > 0) {
                            if (n >= context.limit || data2 != charTarget[n]) {
                                return -1;
                            }
                            ++n;
                        }
                        else {
                            final int n5 = n - 1;
                            if (n5 >= context.limit || n5 < 0 || data2 != charTarget[n5]) {
                                return -1;
                            }
                            n = n5;
                        }
                    }
                    op = op.next;
                    continue;
                }
                case 0: {
                    if (n2 > 0) {
                        if (n >= context.limit) {
                            return -1;
                        }
                        int composeFromSurrogates = charTarget[n];
                        if (isSet(n3, 4)) {
                            if (REUtil.isHighSurrogate(composeFromSurrogates) && n + 1 < context.limit) {
                                ++n;
                            }
                        }
                        else {
                            if (REUtil.isHighSurrogate(composeFromSurrogates) && n + 1 < context.limit) {
                                composeFromSurrogates = REUtil.composeFromSurrogates(composeFromSurrogates, charTarget[++n]);
                            }
                            if (isEOLChar(composeFromSurrogates)) {
                                return -1;
                            }
                        }
                        ++n;
                    }
                    else {
                        int n6 = n - 1;
                        if (n6 >= context.limit || n6 < 0) {
                            return -1;
                        }
                        int composeFromSurrogates2 = charTarget[n6];
                        if (isSet(n3, 4)) {
                            if (REUtil.isLowSurrogate(composeFromSurrogates2) && n6 - 1 >= 0) {
                                --n6;
                            }
                        }
                        else {
                            if (REUtil.isLowSurrogate(composeFromSurrogates2) && n6 - 1 >= 0) {
                                composeFromSurrogates2 = REUtil.composeFromSurrogates(charTarget[--n6], composeFromSurrogates2);
                            }
                            if (!isEOLChar(composeFromSurrogates2)) {
                                return -1;
                            }
                        }
                        n = n6;
                    }
                    op = op.next;
                    continue;
                }
                case 3:
                case 4: {
                    if (n2 > 0) {
                        if (n >= context.limit) {
                            return -1;
                        }
                        int composeFromSurrogates3 = charTarget[n];
                        if (REUtil.isHighSurrogate(composeFromSurrogates3) && n + 1 < context.limit) {
                            composeFromSurrogates3 = REUtil.composeFromSurrogates(composeFromSurrogates3, charTarget[++n]);
                        }
                        final RangeToken token = op.getToken();
                        if (isSet(n3, 2)) {
                            final RangeToken caseInsensitiveToken = token.getCaseInsensitiveToken();
                            if (!caseInsensitiveToken.match(composeFromSurrogates3)) {
                                if (composeFromSurrogates3 >= 65536) {
                                    return -1;
                                }
                                final char upperCase;
                                if (!caseInsensitiveToken.match(upperCase = Character.toUpperCase((char)composeFromSurrogates3)) && !caseInsensitiveToken.match(Character.toLowerCase(upperCase))) {
                                    return -1;
                                }
                            }
                        }
                        else if (!token.match(composeFromSurrogates3)) {
                            return -1;
                        }
                        ++n;
                    }
                    else {
                        int n7 = n - 1;
                        if (n7 >= context.limit || n7 < 0) {
                            return -1;
                        }
                        int composeFromSurrogates4 = charTarget[n7];
                        if (REUtil.isLowSurrogate(composeFromSurrogates4) && n7 - 1 >= 0) {
                            composeFromSurrogates4 = REUtil.composeFromSurrogates(charTarget[--n7], composeFromSurrogates4);
                        }
                        final RangeToken token2 = op.getToken();
                        if (isSet(n3, 2)) {
                            final RangeToken caseInsensitiveToken2 = token2.getCaseInsensitiveToken();
                            if (!caseInsensitiveToken2.match(composeFromSurrogates4)) {
                                if (composeFromSurrogates4 >= 65536) {
                                    return -1;
                                }
                                final char upperCase2;
                                if (!caseInsensitiveToken2.match(upperCase2 = Character.toUpperCase((char)composeFromSurrogates4)) && !caseInsensitiveToken2.match(Character.toLowerCase(upperCase2))) {
                                    return -1;
                                }
                            }
                        }
                        else if (!token2.match(composeFromSurrogates4)) {
                            return -1;
                        }
                        n = n7;
                    }
                    op = op.next;
                    continue;
                }
                case 5: {
                    switch (op.getData()) {
                        case 94: {
                            if (isSet(n3, 8)) {
                                if (n != context.start && (n <= context.start || !isEOLChar(charTarget[n - 1]))) {
                                    return -1;
                                }
                                break;
                            }
                            else {
                                if (n != context.start) {
                                    return -1;
                                }
                                break;
                            }
                            break;
                        }
                        case 64: {
                            if (n != context.start && (n <= context.start || !isEOLChar(charTarget[n - 1]))) {
                                return -1;
                            }
                            break;
                        }
                        case 36: {
                            if (isSet(n3, 8)) {
                                if (n != context.limit && (n >= context.limit || !isEOLChar(charTarget[n]))) {
                                    return -1;
                                }
                                break;
                            }
                            else {
                                if (n != context.limit && (n + 1 != context.limit || !isEOLChar(charTarget[n])) && (n + 2 != context.limit || charTarget[n] != '\r' || charTarget[n + 1] != '\n')) {
                                    return -1;
                                }
                                break;
                            }
                            break;
                        }
                        case 65: {
                            if (n != context.start) {
                                return -1;
                            }
                            break;
                        }
                        case 90: {
                            if (n != context.limit && (n + 1 != context.limit || !isEOLChar(charTarget[n])) && (n + 2 != context.limit || charTarget[n] != '\r' || charTarget[n + 1] != '\n')) {
                                return -1;
                            }
                            break;
                        }
                        case 122: {
                            if (n != context.limit) {
                                return -1;
                            }
                            break;
                        }
                        case 98: {
                            if (context.length == 0) {
                                return -1;
                            }
                            final int wordType = getWordType(charTarget, context.start, context.limit, n, n3);
                            if (wordType == 0) {
                                return -1;
                            }
                            if (wordType == getPreviousWordType(charTarget, context.start, context.limit, n, n3)) {
                                return -1;
                            }
                            break;
                        }
                        case 66: {
                            int n8;
                            if (context.length == 0) {
                                n8 = 1;
                            }
                            else {
                                final int wordType2 = getWordType(charTarget, context.start, context.limit, n, n3);
                                n8 = ((wordType2 == 0 || wordType2 == getPreviousWordType(charTarget, context.start, context.limit, n, n3)) ? 1 : 0);
                            }
                            if (n8 == 0) {
                                return -1;
                            }
                            break;
                        }
                        case 60: {
                            if (context.length == 0 || n == context.limit) {
                                return -1;
                            }
                            if (getWordType(charTarget, context.start, context.limit, n, n3) != 1 || getPreviousWordType(charTarget, context.start, context.limit, n, n3) != 2) {
                                return -1;
                            }
                            break;
                        }
                        case 62: {
                            if (context.length == 0 || n == context.start) {
                                return -1;
                            }
                            if (getWordType(charTarget, context.start, context.limit, n, n3) != 2 || getPreviousWordType(charTarget, context.start, context.limit, n, n3) != 1) {
                                return -1;
                            }
                            break;
                        }
                    }
                    op = op.next;
                    continue;
                }
                case 16: {
                    final int data3 = op.getData();
                    if (data3 <= 0 || data3 >= this.nofparen) {
                        throw new RuntimeException("Internal Error: Reference number must be more than zero: " + data3);
                    }
                    if (context.match.getBeginning(data3) < 0 || context.match.getEnd(data3) < 0) {
                        return -1;
                    }
                    final int beginning = context.match.getBeginning(data3);
                    final int n9 = context.match.getEnd(data3) - beginning;
                    if (!isSet(n3, 2)) {
                        if (n2 > 0) {
                            if (!regionMatches(charTarget, n, context.limit, beginning, n9)) {
                                return -1;
                            }
                            n += n9;
                        }
                        else {
                            if (!regionMatches(charTarget, n - n9, context.limit, beginning, n9)) {
                                return -1;
                            }
                            n -= n9;
                        }
                    }
                    else if (n2 > 0) {
                        if (!regionMatchesIgnoreCase(charTarget, n, context.limit, beginning, n9)) {
                            return -1;
                        }
                        n += n9;
                    }
                    else {
                        if (!regionMatchesIgnoreCase(charTarget, n - n9, context.limit, beginning, n9)) {
                            return -1;
                        }
                        n -= n9;
                    }
                    op = op.next;
                    continue;
                }
                case 6: {
                    final String string = op.getString();
                    final int length = string.length();
                    if (!isSet(n3, 2)) {
                        if (n2 > 0) {
                            if (!regionMatches(charTarget, n, context.limit, string, length)) {
                                return -1;
                            }
                            n += length;
                        }
                        else {
                            if (!regionMatches(charTarget, n - length, context.limit, string, length)) {
                                return -1;
                            }
                            n -= length;
                        }
                    }
                    else if (n2 > 0) {
                        if (!regionMatchesIgnoreCase(charTarget, n, context.limit, string, length)) {
                            return -1;
                        }
                        n += length;
                    }
                    else {
                        if (!regionMatchesIgnoreCase(charTarget, n - length, context.limit, string, length)) {
                            return -1;
                        }
                        n -= length;
                    }
                    op = op.next;
                    continue;
                }
                case 7: {
                    final int data4 = op.getData();
                    if (data4 >= 0) {
                        final int n10 = context.offsets[data4];
                        if (n10 >= 0 && n10 == n) {
                            context.offsets[data4] = -1;
                            op = op.next;
                            continue;
                        }
                        context.offsets[data4] = n;
                    }
                    final int matchCharArray = this.matchCharArray(context, op.getChild(), n, n2, n3);
                    if (data4 >= 0) {
                        context.offsets[data4] = -1;
                    }
                    if (matchCharArray >= 0) {
                        return matchCharArray;
                    }
                    op = op.next;
                    continue;
                }
                case 9: {
                    final int matchCharArray2 = this.matchCharArray(context, op.getChild(), n, n2, n3);
                    if (matchCharArray2 >= 0) {
                        return matchCharArray2;
                    }
                    op = op.next;
                    continue;
                }
                case 8:
                case 10: {
                    final int matchCharArray3 = this.matchCharArray(context, op.next, n, n2, n3);
                    if (matchCharArray3 >= 0) {
                        return matchCharArray3;
                    }
                    op = op.getChild();
                    continue;
                }
                case 11: {
                    for (int i = 0; i < op.size(); ++i) {
                        final int matchCharArray4 = this.matchCharArray(context, op.elementAt(i), n, n2, n3);
                        if (matchCharArray4 >= 0) {
                            return matchCharArray4;
                        }
                    }
                    return -1;
                }
                case 15: {
                    final int data5 = op.getData();
                    if (context.match != null && data5 > 0) {
                        final int beginning2 = context.match.getBeginning(data5);
                        context.match.setBeginning(data5, n);
                        final int matchCharArray5 = this.matchCharArray(context, op.next, n, n2, n3);
                        if (matchCharArray5 < 0) {
                            context.match.setBeginning(data5, beginning2);
                        }
                        return matchCharArray5;
                    }
                    if (context.match != null && data5 < 0) {
                        final int n11 = -data5;
                        final int end = context.match.getEnd(n11);
                        context.match.setEnd(n11, n);
                        final int matchCharArray6 = this.matchCharArray(context, op.next, n, n2, n3);
                        if (matchCharArray6 < 0) {
                            context.match.setEnd(n11, end);
                        }
                        return matchCharArray6;
                    }
                    op = op.next;
                    continue;
                }
                case 20: {
                    if (0 > this.matchCharArray(context, op.getChild(), n, 1, n3)) {
                        return -1;
                    }
                    op = op.next;
                    continue;
                }
                case 21: {
                    if (0 <= this.matchCharArray(context, op.getChild(), n, 1, n3)) {
                        return -1;
                    }
                    op = op.next;
                    continue;
                }
                case 22: {
                    if (0 > this.matchCharArray(context, op.getChild(), n, -1, n3)) {
                        return -1;
                    }
                    op = op.next;
                    continue;
                }
                case 23: {
                    if (0 <= this.matchCharArray(context, op.getChild(), n, -1, n3)) {
                        return -1;
                    }
                    op = op.next;
                    continue;
                }
                case 24: {
                    final int matchCharArray7 = this.matchCharArray(context, op.getChild(), n, n2, n3);
                    if (matchCharArray7 < 0) {
                        return matchCharArray7;
                    }
                    n = matchCharArray7;
                    op = op.next;
                    continue;
                }
                case 25: {
                    final int matchCharArray8 = this.matchCharArray(context, op.getChild(), n, n2, (n3 | op.getData()) & ~op.getData2());
                    if (matchCharArray8 < 0) {
                        return matchCharArray8;
                    }
                    n = matchCharArray8;
                    op = op.next;
                    continue;
                }
                case 26: {
                    final Op.ConditionOp conditionOp = (Op.ConditionOp)op;
                    boolean b;
                    if (conditionOp.refNumber > 0) {
                        if (conditionOp.refNumber >= this.nofparen) {
                            throw new RuntimeException("Internal Error: Reference number must be more than zero: " + conditionOp.refNumber);
                        }
                        b = (context.match.getBeginning(conditionOp.refNumber) >= 0 && context.match.getEnd(conditionOp.refNumber) >= 0);
                    }
                    else {
                        b = (0 <= this.matchCharArray(context, conditionOp.condition, n, n2, n3));
                    }
                    if (b) {
                        op = conditionOp.yes;
                        continue;
                    }
                    if (conditionOp.no != null) {
                        op = conditionOp.no;
                        continue;
                    }
                    op = conditionOp.next;
                    continue;
                }
                default: {
                    throw new RuntimeException("Unknown operation type: " + op.type);
                }
            }
        }
        return n;
    }
    
    private static final int getPreviousWordType(final char[] array, final int n, final int n2, int n3, final int n4) {
        int i;
        for (i = getWordType(array, n, n2, --n3, n4); i == 0; i = getWordType(array, n, n2, --n3, n4)) {}
        return i;
    }
    
    private static final int getWordType(final char[] array, final int n, final int n2, final int n3, final int n4) {
        if (n3 < n || n3 >= n2) {
            return 2;
        }
        return getWordType0(array[n3], n4);
    }
    
    private static final boolean regionMatches(final char[] array, int n, final int n2, final String s, int n3) {
        if (n < 0) {
            return false;
        }
        if (n2 - n < n3) {
            return false;
        }
        int n4 = 0;
        while (n3-- > 0) {
            if (array[n++] != s.charAt(n4++)) {
                return false;
            }
        }
        return true;
    }
    
    private static final boolean regionMatches(final char[] array, int n, final int n2, final int n3, int n4) {
        if (n < 0) {
            return false;
        }
        if (n2 - n < n4) {
            return false;
        }
        int n5 = n3;
        while (n4-- > 0) {
            if (array[n++] != array[n5++]) {
                return false;
            }
        }
        return true;
    }
    
    private static final boolean regionMatchesIgnoreCase(final char[] array, int n, final int n2, final String s, int n3) {
        if (n < 0) {
            return false;
        }
        if (n2 - n < n3) {
            return false;
        }
        int n4 = 0;
        while (n3-- > 0) {
            final char c = array[n++];
            final char char1 = s.charAt(n4++);
            if (c == char1) {
                continue;
            }
            final char upperCase = Character.toUpperCase(c);
            final char upperCase2 = Character.toUpperCase(char1);
            if (upperCase == upperCase2) {
                continue;
            }
            if (Character.toLowerCase(upperCase) != Character.toLowerCase(upperCase2)) {
                return false;
            }
        }
        return true;
    }
    
    private static final boolean regionMatchesIgnoreCase(final char[] array, int n, final int n2, final int n3, int n4) {
        if (n < 0) {
            return false;
        }
        if (n2 - n < n4) {
            return false;
        }
        int n5 = n3;
        while (n4-- > 0) {
            final char c = array[n++];
            final char c2 = array[n5++];
            if (c == c2) {
                continue;
            }
            final char upperCase = Character.toUpperCase(c);
            final char upperCase2 = Character.toUpperCase(c2);
            if (upperCase == upperCase2) {
                continue;
            }
            if (Character.toLowerCase(upperCase) != Character.toLowerCase(upperCase2)) {
                return false;
            }
        }
        return true;
    }
    
    public boolean matches(final String s) {
        return this.matches(s, 0, s.length(), null);
    }
    
    public boolean matches(final String s, final int n, final int n2) {
        return this.matches(s, n, n2, null);
    }
    
    public boolean matches(final String s, final Match match) {
        return this.matches(s, 0, s.length(), match);
    }
    
    public boolean matches(final String source, final int n, final int n2, Match match) {
        synchronized (this) {
            if (this.operations == null) {
                this.prepare();
            }
            if (this.context == null) {
                this.context = new Context();
            }
        }
        Context context = null;
        synchronized (this.context) {
            context = (this.context.inuse ? new Context() : this.context);
            context.reset(source, n, n2, this.numberOfClosures);
        }
        if (match != null) {
            match.setNumberOfGroups(this.nofparen);
            match.setSource(source);
        }
        else if (this.hasBackReferences) {
            match = new Match();
            match.setNumberOfGroups(this.nofparen);
        }
        context.match = match;
        if (isSet(this.options, 512)) {
            final int matchString = this.matchString(context, this.operations, context.start, 1, this.options);
            if (matchString == context.limit) {
                if (context.match != null) {
                    context.match.setBeginning(0, context.start);
                    context.match.setEnd(0, matchString);
                }
                context.inuse = false;
                return true;
            }
            return false;
        }
        else if (this.fixedStringOnly) {
            final int matches = this.fixedStringTable.matches(source, context.start, context.limit);
            if (matches >= 0) {
                if (context.match != null) {
                    context.match.setBeginning(0, matches);
                    context.match.setEnd(0, matches + this.fixedString.length());
                }
                context.inuse = false;
                return true;
            }
            return context.inuse = false;
        }
        else {
            if (this.fixedString != null && this.fixedStringTable.matches(source, context.start, context.limit) < 0) {
                return context.inuse = false;
            }
            final int n3 = context.limit - this.minlength;
            int n4 = -1;
            int i;
            if (this.operations != null && this.operations.type == 7 && this.operations.getChild().type == 0) {
                if (isSet(this.options, 4)) {
                    i = context.start;
                    n4 = this.matchString(context, this.operations, context.start, 1, this.options);
                }
                else {
                    int n5 = 1;
                    for (i = context.start; i <= n3; ++i) {
                        if (isEOLChar(source.charAt(i))) {
                            n5 = 1;
                        }
                        else {
                            if (n5 != 0 && 0 <= (n4 = this.matchString(context, this.operations, i, 1, this.options))) {
                                break;
                            }
                            n5 = 0;
                        }
                    }
                }
            }
            else if (this.firstChar != null) {
                final RangeToken firstChar = this.firstChar;
                if (isSet(this.options, 2)) {
                    final RangeToken caseInsensitiveToken = this.firstChar.getCaseInsensitiveToken();
                    for (i = context.start; i <= n3; ++i) {
                        final char char1 = source.charAt(i);
                        if (REUtil.isHighSurrogate(char1) && i + 1 < context.limit) {
                            if (!caseInsensitiveToken.match(REUtil.composeFromSurrogates(char1, source.charAt(i + 1)))) {
                                continue;
                            }
                        }
                        else if (!caseInsensitiveToken.match(char1)) {
                            final char upperCase = Character.toUpperCase(char1);
                            if (!caseInsensitiveToken.match(upperCase) && !caseInsensitiveToken.match(Character.toLowerCase(upperCase))) {
                                continue;
                            }
                        }
                        if (0 <= (n4 = this.matchString(context, this.operations, i, 1, this.options))) {
                            break;
                        }
                    }
                }
                else {
                    for (i = context.start; i <= n3; ++i) {
                        int n6 = source.charAt(i);
                        if (REUtil.isHighSurrogate(n6) && i + 1 < context.limit) {
                            n6 = REUtil.composeFromSurrogates(n6, source.charAt(i + 1));
                        }
                        if (firstChar.match(n6)) {
                            if (0 <= (n4 = this.matchString(context, this.operations, i, 1, this.options))) {
                                break;
                            }
                        }
                    }
                }
            }
            else {
                for (i = context.start; i <= n3; ++i) {
                    if (0 <= (n4 = this.matchString(context, this.operations, i, 1, this.options))) {
                        break;
                    }
                }
            }
            if (n4 >= 0) {
                if (context.match != null) {
                    context.match.setBeginning(0, i);
                    context.match.setEnd(0, n4);
                }
                context.inuse = false;
                return true;
            }
            return context.inuse = false;
        }
    }
    
    private int matchString(final Context context, Op op, int n, final int n2, final int n3) {
        final String strTarget = context.strTarget;
        while (op != null) {
            if (n > context.limit || n < context.start) {
                return -1;
            }
            switch (op.type) {
                case 1: {
                    if (isSet(n3, 2)) {
                        final int data = op.getData();
                        if (n2 > 0) {
                            if (n >= context.limit || !matchIgnoreCase(data, strTarget.charAt(n))) {
                                return -1;
                            }
                            ++n;
                        }
                        else {
                            final int n4 = n - 1;
                            if (n4 >= context.limit || n4 < 0 || !matchIgnoreCase(data, strTarget.charAt(n4))) {
                                return -1;
                            }
                            n = n4;
                        }
                    }
                    else {
                        final int data2 = op.getData();
                        if (n2 > 0) {
                            if (n >= context.limit || data2 != strTarget.charAt(n)) {
                                return -1;
                            }
                            ++n;
                        }
                        else {
                            final int n5 = n - 1;
                            if (n5 >= context.limit || n5 < 0 || data2 != strTarget.charAt(n5)) {
                                return -1;
                            }
                            n = n5;
                        }
                    }
                    op = op.next;
                    continue;
                }
                case 0: {
                    if (n2 > 0) {
                        if (n >= context.limit) {
                            return -1;
                        }
                        int n6 = strTarget.charAt(n);
                        if (isSet(n3, 4)) {
                            if (REUtil.isHighSurrogate(n6) && n + 1 < context.limit) {
                                ++n;
                            }
                        }
                        else {
                            if (REUtil.isHighSurrogate(n6) && n + 1 < context.limit) {
                                n6 = REUtil.composeFromSurrogates(n6, strTarget.charAt(++n));
                            }
                            if (isEOLChar(n6)) {
                                return -1;
                            }
                        }
                        ++n;
                    }
                    else {
                        int n7 = n - 1;
                        if (n7 >= context.limit || n7 < 0) {
                            return -1;
                        }
                        int n8 = strTarget.charAt(n7);
                        if (isSet(n3, 4)) {
                            if (REUtil.isLowSurrogate(n8) && n7 - 1 >= 0) {
                                --n7;
                            }
                        }
                        else {
                            if (REUtil.isLowSurrogate(n8) && n7 - 1 >= 0) {
                                n8 = REUtil.composeFromSurrogates(strTarget.charAt(--n7), n8);
                            }
                            if (!isEOLChar(n8)) {
                                return -1;
                            }
                        }
                        n = n7;
                    }
                    op = op.next;
                    continue;
                }
                case 3:
                case 4: {
                    if (n2 > 0) {
                        if (n >= context.limit) {
                            return -1;
                        }
                        int n9 = strTarget.charAt(n);
                        if (REUtil.isHighSurrogate(n9) && n + 1 < context.limit) {
                            n9 = REUtil.composeFromSurrogates(n9, strTarget.charAt(++n));
                        }
                        final RangeToken token = op.getToken();
                        if (isSet(n3, 2)) {
                            final RangeToken caseInsensitiveToken = token.getCaseInsensitiveToken();
                            if (!caseInsensitiveToken.match(n9)) {
                                if (n9 >= 65536) {
                                    return -1;
                                }
                                final char upperCase;
                                if (!caseInsensitiveToken.match(upperCase = Character.toUpperCase((char)n9)) && !caseInsensitiveToken.match(Character.toLowerCase(upperCase))) {
                                    return -1;
                                }
                            }
                        }
                        else if (!token.match(n9)) {
                            return -1;
                        }
                        ++n;
                    }
                    else {
                        int n10 = n - 1;
                        if (n10 >= context.limit || n10 < 0) {
                            return -1;
                        }
                        int n11 = strTarget.charAt(n10);
                        if (REUtil.isLowSurrogate(n11) && n10 - 1 >= 0) {
                            n11 = REUtil.composeFromSurrogates(strTarget.charAt(--n10), n11);
                        }
                        final RangeToken token2 = op.getToken();
                        if (isSet(n3, 2)) {
                            final RangeToken caseInsensitiveToken2 = token2.getCaseInsensitiveToken();
                            if (!caseInsensitiveToken2.match(n11)) {
                                if (n11 >= 65536) {
                                    return -1;
                                }
                                final char upperCase2;
                                if (!caseInsensitiveToken2.match(upperCase2 = Character.toUpperCase((char)n11)) && !caseInsensitiveToken2.match(Character.toLowerCase(upperCase2))) {
                                    return -1;
                                }
                            }
                        }
                        else if (!token2.match(n11)) {
                            return -1;
                        }
                        n = n10;
                    }
                    op = op.next;
                    continue;
                }
                case 5: {
                    switch (op.getData()) {
                        case 94: {
                            if (isSet(n3, 8)) {
                                if (n != context.start && (n <= context.start || !isEOLChar(strTarget.charAt(n - 1)))) {
                                    return -1;
                                }
                                break;
                            }
                            else {
                                if (n != context.start) {
                                    return -1;
                                }
                                break;
                            }
                            break;
                        }
                        case 64: {
                            if (n != context.start && (n <= context.start || !isEOLChar(strTarget.charAt(n - 1)))) {
                                return -1;
                            }
                            break;
                        }
                        case 36: {
                            if (isSet(n3, 8)) {
                                if (n != context.limit && (n >= context.limit || !isEOLChar(strTarget.charAt(n)))) {
                                    return -1;
                                }
                                break;
                            }
                            else {
                                if (n != context.limit && (n + 1 != context.limit || !isEOLChar(strTarget.charAt(n))) && (n + 2 != context.limit || strTarget.charAt(n) != '\r' || strTarget.charAt(n + 1) != '\n')) {
                                    return -1;
                                }
                                break;
                            }
                            break;
                        }
                        case 65: {
                            if (n != context.start) {
                                return -1;
                            }
                            break;
                        }
                        case 90: {
                            if (n != context.limit && (n + 1 != context.limit || !isEOLChar(strTarget.charAt(n))) && (n + 2 != context.limit || strTarget.charAt(n) != '\r' || strTarget.charAt(n + 1) != '\n')) {
                                return -1;
                            }
                            break;
                        }
                        case 122: {
                            if (n != context.limit) {
                                return -1;
                            }
                            break;
                        }
                        case 98: {
                            if (context.length == 0) {
                                return -1;
                            }
                            final int wordType = getWordType(strTarget, context.start, context.limit, n, n3);
                            if (wordType == 0) {
                                return -1;
                            }
                            if (wordType == getPreviousWordType(strTarget, context.start, context.limit, n, n3)) {
                                return -1;
                            }
                            break;
                        }
                        case 66: {
                            int n12;
                            if (context.length == 0) {
                                n12 = 1;
                            }
                            else {
                                final int wordType2 = getWordType(strTarget, context.start, context.limit, n, n3);
                                n12 = ((wordType2 == 0 || wordType2 == getPreviousWordType(strTarget, context.start, context.limit, n, n3)) ? 1 : 0);
                            }
                            if (n12 == 0) {
                                return -1;
                            }
                            break;
                        }
                        case 60: {
                            if (context.length == 0 || n == context.limit) {
                                return -1;
                            }
                            if (getWordType(strTarget, context.start, context.limit, n, n3) != 1 || getPreviousWordType(strTarget, context.start, context.limit, n, n3) != 2) {
                                return -1;
                            }
                            break;
                        }
                        case 62: {
                            if (context.length == 0 || n == context.start) {
                                return -1;
                            }
                            if (getWordType(strTarget, context.start, context.limit, n, n3) != 2 || getPreviousWordType(strTarget, context.start, context.limit, n, n3) != 1) {
                                return -1;
                            }
                            break;
                        }
                    }
                    op = op.next;
                    continue;
                }
                case 16: {
                    final int data3 = op.getData();
                    if (data3 <= 0 || data3 >= this.nofparen) {
                        throw new RuntimeException("Internal Error: Reference number must be more than zero: " + data3);
                    }
                    if (context.match.getBeginning(data3) < 0 || context.match.getEnd(data3) < 0) {
                        return -1;
                    }
                    final int beginning = context.match.getBeginning(data3);
                    final int n13 = context.match.getEnd(data3) - beginning;
                    if (!isSet(n3, 2)) {
                        if (n2 > 0) {
                            if (!regionMatches(strTarget, n, context.limit, beginning, n13)) {
                                return -1;
                            }
                            n += n13;
                        }
                        else {
                            if (!regionMatches(strTarget, n - n13, context.limit, beginning, n13)) {
                                return -1;
                            }
                            n -= n13;
                        }
                    }
                    else if (n2 > 0) {
                        if (!regionMatchesIgnoreCase(strTarget, n, context.limit, beginning, n13)) {
                            return -1;
                        }
                        n += n13;
                    }
                    else {
                        if (!regionMatchesIgnoreCase(strTarget, n - n13, context.limit, beginning, n13)) {
                            return -1;
                        }
                        n -= n13;
                    }
                    op = op.next;
                    continue;
                }
                case 6: {
                    final String string = op.getString();
                    final int length = string.length();
                    if (!isSet(n3, 2)) {
                        if (n2 > 0) {
                            if (!regionMatches(strTarget, n, context.limit, string, length)) {
                                return -1;
                            }
                            n += length;
                        }
                        else {
                            if (!regionMatches(strTarget, n - length, context.limit, string, length)) {
                                return -1;
                            }
                            n -= length;
                        }
                    }
                    else if (n2 > 0) {
                        if (!regionMatchesIgnoreCase(strTarget, n, context.limit, string, length)) {
                            return -1;
                        }
                        n += length;
                    }
                    else {
                        if (!regionMatchesIgnoreCase(strTarget, n - length, context.limit, string, length)) {
                            return -1;
                        }
                        n -= length;
                    }
                    op = op.next;
                    continue;
                }
                case 7: {
                    final int data4 = op.getData();
                    if (data4 >= 0) {
                        final int n14 = context.offsets[data4];
                        if (n14 >= 0 && n14 == n) {
                            context.offsets[data4] = -1;
                            op = op.next;
                            continue;
                        }
                        context.offsets[data4] = n;
                    }
                    final int matchString = this.matchString(context, op.getChild(), n, n2, n3);
                    if (data4 >= 0) {
                        context.offsets[data4] = -1;
                    }
                    if (matchString >= 0) {
                        return matchString;
                    }
                    op = op.next;
                    continue;
                }
                case 9: {
                    final int matchString2 = this.matchString(context, op.getChild(), n, n2, n3);
                    if (matchString2 >= 0) {
                        return matchString2;
                    }
                    op = op.next;
                    continue;
                }
                case 8:
                case 10: {
                    final int matchString3 = this.matchString(context, op.next, n, n2, n3);
                    if (matchString3 >= 0) {
                        return matchString3;
                    }
                    op = op.getChild();
                    continue;
                }
                case 11: {
                    for (int i = 0; i < op.size(); ++i) {
                        final int matchString4 = this.matchString(context, op.elementAt(i), n, n2, n3);
                        if (matchString4 >= 0) {
                            return matchString4;
                        }
                    }
                    return -1;
                }
                case 15: {
                    final int data5 = op.getData();
                    if (context.match != null && data5 > 0) {
                        final int beginning2 = context.match.getBeginning(data5);
                        context.match.setBeginning(data5, n);
                        final int matchString5 = this.matchString(context, op.next, n, n2, n3);
                        if (matchString5 < 0) {
                            context.match.setBeginning(data5, beginning2);
                        }
                        return matchString5;
                    }
                    if (context.match != null && data5 < 0) {
                        final int n15 = -data5;
                        final int end = context.match.getEnd(n15);
                        context.match.setEnd(n15, n);
                        final int matchString6 = this.matchString(context, op.next, n, n2, n3);
                        if (matchString6 < 0) {
                            context.match.setEnd(n15, end);
                        }
                        return matchString6;
                    }
                    op = op.next;
                    continue;
                }
                case 20: {
                    if (0 > this.matchString(context, op.getChild(), n, 1, n3)) {
                        return -1;
                    }
                    op = op.next;
                    continue;
                }
                case 21: {
                    if (0 <= this.matchString(context, op.getChild(), n, 1, n3)) {
                        return -1;
                    }
                    op = op.next;
                    continue;
                }
                case 22: {
                    if (0 > this.matchString(context, op.getChild(), n, -1, n3)) {
                        return -1;
                    }
                    op = op.next;
                    continue;
                }
                case 23: {
                    if (0 <= this.matchString(context, op.getChild(), n, -1, n3)) {
                        return -1;
                    }
                    op = op.next;
                    continue;
                }
                case 24: {
                    final int matchString7 = this.matchString(context, op.getChild(), n, n2, n3);
                    if (matchString7 < 0) {
                        return matchString7;
                    }
                    n = matchString7;
                    op = op.next;
                    continue;
                }
                case 25: {
                    final int matchString8 = this.matchString(context, op.getChild(), n, n2, (n3 | op.getData()) & ~op.getData2());
                    if (matchString8 < 0) {
                        return matchString8;
                    }
                    n = matchString8;
                    op = op.next;
                    continue;
                }
                case 26: {
                    final Op.ConditionOp conditionOp = (Op.ConditionOp)op;
                    boolean b;
                    if (conditionOp.refNumber > 0) {
                        if (conditionOp.refNumber >= this.nofparen) {
                            throw new RuntimeException("Internal Error: Reference number must be more than zero: " + conditionOp.refNumber);
                        }
                        b = (context.match.getBeginning(conditionOp.refNumber) >= 0 && context.match.getEnd(conditionOp.refNumber) >= 0);
                    }
                    else {
                        b = (0 <= this.matchString(context, conditionOp.condition, n, n2, n3));
                    }
                    if (b) {
                        op = conditionOp.yes;
                        continue;
                    }
                    if (conditionOp.no != null) {
                        op = conditionOp.no;
                        continue;
                    }
                    op = conditionOp.next;
                    continue;
                }
                default: {
                    throw new RuntimeException("Unknown operation type: " + op.type);
                }
            }
        }
        return n;
    }
    
    private static final int getPreviousWordType(final String s, final int n, final int n2, int n3, final int n4) {
        int i;
        for (i = getWordType(s, n, n2, --n3, n4); i == 0; i = getWordType(s, n, n2, --n3, n4)) {}
        return i;
    }
    
    private static final int getWordType(final String s, final int n, final int n2, final int n3, final int n4) {
        if (n3 < n || n3 >= n2) {
            return 2;
        }
        return getWordType0(s.charAt(n3), n4);
    }
    
    private static final boolean regionMatches(final String s, final int n, final int n2, final String s2, final int n3) {
        return n2 - n >= n3 && s.regionMatches(n, s2, 0, n3);
    }
    
    private static final boolean regionMatches(final String s, final int n, final int n2, final int n3, final int n4) {
        return n2 - n >= n4 && s.regionMatches(n, s, n3, n4);
    }
    
    private static final boolean regionMatchesIgnoreCase(final String s, final int n, final int n2, final String s2, final int n3) {
        return s.regionMatches(true, n, s2, 0, n3);
    }
    
    private static final boolean regionMatchesIgnoreCase(final String s, final int n, final int n2, final int n3, final int n4) {
        return n2 - n >= n4 && s.regionMatches(true, n, s, n3, n4);
    }
    
    public boolean matches(final CharacterIterator characterIterator) {
        return this.matches(characterIterator, null);
    }
    
    public boolean matches(final CharacterIterator source, Match match) {
        final int beginIndex = source.getBeginIndex();
        final int endIndex = source.getEndIndex();
        synchronized (this) {
            if (this.operations == null) {
                this.prepare();
            }
            if (this.context == null) {
                this.context = new Context();
            }
        }
        Context context = null;
        synchronized (this.context) {
            context = (this.context.inuse ? new Context() : this.context);
            context.reset(source, beginIndex, endIndex, this.numberOfClosures);
        }
        if (match != null) {
            match.setNumberOfGroups(this.nofparen);
            match.setSource(source);
        }
        else if (this.hasBackReferences) {
            match = new Match();
            match.setNumberOfGroups(this.nofparen);
        }
        context.match = match;
        if (isSet(this.options, 512)) {
            final int matchCharacterIterator = this.matchCharacterIterator(context, this.operations, context.start, 1, this.options);
            if (matchCharacterIterator == context.limit) {
                if (context.match != null) {
                    context.match.setBeginning(0, context.start);
                    context.match.setEnd(0, matchCharacterIterator);
                }
                context.inuse = false;
                return true;
            }
            return false;
        }
        else if (this.fixedStringOnly) {
            final int matches = this.fixedStringTable.matches(source, context.start, context.limit);
            if (matches >= 0) {
                if (context.match != null) {
                    context.match.setBeginning(0, matches);
                    context.match.setEnd(0, matches + this.fixedString.length());
                }
                context.inuse = false;
                return true;
            }
            return context.inuse = false;
        }
        else {
            if (this.fixedString != null && this.fixedStringTable.matches(source, context.start, context.limit) < 0) {
                return context.inuse = false;
            }
            final int n = context.limit - this.minlength;
            int n2 = -1;
            int i;
            if (this.operations != null && this.operations.type == 7 && this.operations.getChild().type == 0) {
                if (isSet(this.options, 4)) {
                    i = context.start;
                    n2 = this.matchCharacterIterator(context, this.operations, context.start, 1, this.options);
                }
                else {
                    int n3 = 1;
                    for (i = context.start; i <= n; ++i) {
                        if (isEOLChar(source.setIndex(i))) {
                            n3 = 1;
                        }
                        else {
                            if (n3 != 0 && 0 <= (n2 = this.matchCharacterIterator(context, this.operations, i, 1, this.options))) {
                                break;
                            }
                            n3 = 0;
                        }
                    }
                }
            }
            else if (this.firstChar != null) {
                final RangeToken firstChar = this.firstChar;
                if (isSet(this.options, 2)) {
                    final RangeToken caseInsensitiveToken = this.firstChar.getCaseInsensitiveToken();
                    for (i = context.start; i <= n; ++i) {
                        final char setIndex = source.setIndex(i);
                        if (REUtil.isHighSurrogate(setIndex) && i + 1 < context.limit) {
                            if (!caseInsensitiveToken.match(REUtil.composeFromSurrogates(setIndex, source.setIndex(i + 1)))) {
                                continue;
                            }
                        }
                        else if (!caseInsensitiveToken.match(setIndex)) {
                            final char upperCase = Character.toUpperCase(setIndex);
                            if (!caseInsensitiveToken.match(upperCase) && !caseInsensitiveToken.match(Character.toLowerCase(upperCase))) {
                                continue;
                            }
                        }
                        if (0 <= (n2 = this.matchCharacterIterator(context, this.operations, i, 1, this.options))) {
                            break;
                        }
                    }
                }
                else {
                    for (i = context.start; i <= n; ++i) {
                        int n4 = source.setIndex(i);
                        if (REUtil.isHighSurrogate(n4) && i + 1 < context.limit) {
                            n4 = REUtil.composeFromSurrogates(n4, source.setIndex(i + 1));
                        }
                        if (firstChar.match(n4)) {
                            if (0 <= (n2 = this.matchCharacterIterator(context, this.operations, i, 1, this.options))) {
                                break;
                            }
                        }
                    }
                }
            }
            else {
                for (i = context.start; i <= n; ++i) {
                    if (0 <= (n2 = this.matchCharacterIterator(context, this.operations, i, 1, this.options))) {
                        break;
                    }
                }
            }
            if (n2 >= 0) {
                if (context.match != null) {
                    context.match.setBeginning(0, i);
                    context.match.setEnd(0, n2);
                }
                context.inuse = false;
                return true;
            }
            return context.inuse = false;
        }
    }
    
    private int matchCharacterIterator(final Context context, Op op, int index, final int n, final int n2) {
        final CharacterIterator ciTarget = context.ciTarget;
        while (op != null) {
            if (index > context.limit || index < context.start) {
                return -1;
            }
            switch (op.type) {
                case 1: {
                    if (isSet(n2, 2)) {
                        final int data = op.getData();
                        if (n > 0) {
                            if (index >= context.limit || !matchIgnoreCase(data, ciTarget.setIndex(index))) {
                                return -1;
                            }
                            ++index;
                        }
                        else {
                            final int index2 = index - 1;
                            if (index2 >= context.limit || index2 < 0 || !matchIgnoreCase(data, ciTarget.setIndex(index2))) {
                                return -1;
                            }
                            index = index2;
                        }
                    }
                    else {
                        final int data2 = op.getData();
                        if (n > 0) {
                            if (index >= context.limit || data2 != ciTarget.setIndex(index)) {
                                return -1;
                            }
                            ++index;
                        }
                        else {
                            final int index3 = index - 1;
                            if (index3 >= context.limit || index3 < 0 || data2 != ciTarget.setIndex(index3)) {
                                return -1;
                            }
                            index = index3;
                        }
                    }
                    op = op.next;
                    continue;
                }
                case 0: {
                    if (n > 0) {
                        if (index >= context.limit) {
                            return -1;
                        }
                        int n3 = ciTarget.setIndex(index);
                        if (isSet(n2, 4)) {
                            if (REUtil.isHighSurrogate(n3) && index + 1 < context.limit) {
                                ++index;
                            }
                        }
                        else {
                            if (REUtil.isHighSurrogate(n3) && index + 1 < context.limit) {
                                n3 = REUtil.composeFromSurrogates(n3, ciTarget.setIndex(++index));
                            }
                            if (isEOLChar(n3)) {
                                return -1;
                            }
                        }
                        ++index;
                    }
                    else {
                        int index4 = index - 1;
                        if (index4 >= context.limit || index4 < 0) {
                            return -1;
                        }
                        int n4 = ciTarget.setIndex(index4);
                        if (isSet(n2, 4)) {
                            if (REUtil.isLowSurrogate(n4) && index4 - 1 >= 0) {
                                --index4;
                            }
                        }
                        else {
                            if (REUtil.isLowSurrogate(n4) && index4 - 1 >= 0) {
                                n4 = REUtil.composeFromSurrogates(ciTarget.setIndex(--index4), n4);
                            }
                            if (!isEOLChar(n4)) {
                                return -1;
                            }
                        }
                        index = index4;
                    }
                    op = op.next;
                    continue;
                }
                case 3:
                case 4: {
                    if (n > 0) {
                        if (index >= context.limit) {
                            return -1;
                        }
                        int n5 = ciTarget.setIndex(index);
                        if (REUtil.isHighSurrogate(n5) && index + 1 < context.limit) {
                            n5 = REUtil.composeFromSurrogates(n5, ciTarget.setIndex(++index));
                        }
                        final RangeToken token = op.getToken();
                        if (isSet(n2, 2)) {
                            final RangeToken caseInsensitiveToken = token.getCaseInsensitiveToken();
                            if (!caseInsensitiveToken.match(n5)) {
                                if (n5 >= 65536) {
                                    return -1;
                                }
                                final char upperCase;
                                if (!caseInsensitiveToken.match(upperCase = Character.toUpperCase((char)n5)) && !caseInsensitiveToken.match(Character.toLowerCase(upperCase))) {
                                    return -1;
                                }
                            }
                        }
                        else if (!token.match(n5)) {
                            return -1;
                        }
                        ++index;
                    }
                    else {
                        int index5 = index - 1;
                        if (index5 >= context.limit || index5 < 0) {
                            return -1;
                        }
                        int n6 = ciTarget.setIndex(index5);
                        if (REUtil.isLowSurrogate(n6) && index5 - 1 >= 0) {
                            n6 = REUtil.composeFromSurrogates(ciTarget.setIndex(--index5), n6);
                        }
                        final RangeToken token2 = op.getToken();
                        if (isSet(n2, 2)) {
                            final RangeToken caseInsensitiveToken2 = token2.getCaseInsensitiveToken();
                            if (!caseInsensitiveToken2.match(n6)) {
                                if (n6 >= 65536) {
                                    return -1;
                                }
                                final char upperCase2;
                                if (!caseInsensitiveToken2.match(upperCase2 = Character.toUpperCase((char)n6)) && !caseInsensitiveToken2.match(Character.toLowerCase(upperCase2))) {
                                    return -1;
                                }
                            }
                        }
                        else if (!token2.match(n6)) {
                            return -1;
                        }
                        index = index5;
                    }
                    op = op.next;
                    continue;
                }
                case 5: {
                    switch (op.getData()) {
                        case 94: {
                            if (isSet(n2, 8)) {
                                if (index != context.start && (index <= context.start || !isEOLChar(ciTarget.setIndex(index - 1)))) {
                                    return -1;
                                }
                                break;
                            }
                            else {
                                if (index != context.start) {
                                    return -1;
                                }
                                break;
                            }
                            break;
                        }
                        case 64: {
                            if (index != context.start && (index <= context.start || !isEOLChar(ciTarget.setIndex(index - 1)))) {
                                return -1;
                            }
                            break;
                        }
                        case 36: {
                            if (isSet(n2, 8)) {
                                if (index != context.limit && (index >= context.limit || !isEOLChar(ciTarget.setIndex(index)))) {
                                    return -1;
                                }
                                break;
                            }
                            else {
                                if (index != context.limit && (index + 1 != context.limit || !isEOLChar(ciTarget.setIndex(index))) && (index + 2 != context.limit || ciTarget.setIndex(index) != '\r' || ciTarget.setIndex(index + 1) != '\n')) {
                                    return -1;
                                }
                                break;
                            }
                            break;
                        }
                        case 65: {
                            if (index != context.start) {
                                return -1;
                            }
                            break;
                        }
                        case 90: {
                            if (index != context.limit && (index + 1 != context.limit || !isEOLChar(ciTarget.setIndex(index))) && (index + 2 != context.limit || ciTarget.setIndex(index) != '\r' || ciTarget.setIndex(index + 1) != '\n')) {
                                return -1;
                            }
                            break;
                        }
                        case 122: {
                            if (index != context.limit) {
                                return -1;
                            }
                            break;
                        }
                        case 98: {
                            if (context.length == 0) {
                                return -1;
                            }
                            final int wordType = getWordType(ciTarget, context.start, context.limit, index, n2);
                            if (wordType == 0) {
                                return -1;
                            }
                            if (wordType == getPreviousWordType(ciTarget, context.start, context.limit, index, n2)) {
                                return -1;
                            }
                            break;
                        }
                        case 66: {
                            int n7;
                            if (context.length == 0) {
                                n7 = 1;
                            }
                            else {
                                final int wordType2 = getWordType(ciTarget, context.start, context.limit, index, n2);
                                n7 = ((wordType2 == 0 || wordType2 == getPreviousWordType(ciTarget, context.start, context.limit, index, n2)) ? 1 : 0);
                            }
                            if (n7 == 0) {
                                return -1;
                            }
                            break;
                        }
                        case 60: {
                            if (context.length == 0 || index == context.limit) {
                                return -1;
                            }
                            if (getWordType(ciTarget, context.start, context.limit, index, n2) != 1 || getPreviousWordType(ciTarget, context.start, context.limit, index, n2) != 2) {
                                return -1;
                            }
                            break;
                        }
                        case 62: {
                            if (context.length == 0 || index == context.start) {
                                return -1;
                            }
                            if (getWordType(ciTarget, context.start, context.limit, index, n2) != 2 || getPreviousWordType(ciTarget, context.start, context.limit, index, n2) != 1) {
                                return -1;
                            }
                            break;
                        }
                    }
                    op = op.next;
                    continue;
                }
                case 16: {
                    final int data3 = op.getData();
                    if (data3 <= 0 || data3 >= this.nofparen) {
                        throw new RuntimeException("Internal Error: Reference number must be more than zero: " + data3);
                    }
                    if (context.match.getBeginning(data3) < 0 || context.match.getEnd(data3) < 0) {
                        return -1;
                    }
                    final int beginning = context.match.getBeginning(data3);
                    final int n8 = context.match.getEnd(data3) - beginning;
                    if (!isSet(n2, 2)) {
                        if (n > 0) {
                            if (!regionMatches(ciTarget, index, context.limit, beginning, n8)) {
                                return -1;
                            }
                            index += n8;
                        }
                        else {
                            if (!regionMatches(ciTarget, index - n8, context.limit, beginning, n8)) {
                                return -1;
                            }
                            index -= n8;
                        }
                    }
                    else if (n > 0) {
                        if (!regionMatchesIgnoreCase(ciTarget, index, context.limit, beginning, n8)) {
                            return -1;
                        }
                        index += n8;
                    }
                    else {
                        if (!regionMatchesIgnoreCase(ciTarget, index - n8, context.limit, beginning, n8)) {
                            return -1;
                        }
                        index -= n8;
                    }
                    op = op.next;
                    continue;
                }
                case 6: {
                    final String string = op.getString();
                    final int length = string.length();
                    if (!isSet(n2, 2)) {
                        if (n > 0) {
                            if (!regionMatches(ciTarget, index, context.limit, string, length)) {
                                return -1;
                            }
                            index += length;
                        }
                        else {
                            if (!regionMatches(ciTarget, index - length, context.limit, string, length)) {
                                return -1;
                            }
                            index -= length;
                        }
                    }
                    else if (n > 0) {
                        if (!regionMatchesIgnoreCase(ciTarget, index, context.limit, string, length)) {
                            return -1;
                        }
                        index += length;
                    }
                    else {
                        if (!regionMatchesIgnoreCase(ciTarget, index - length, context.limit, string, length)) {
                            return -1;
                        }
                        index -= length;
                    }
                    op = op.next;
                    continue;
                }
                case 7: {
                    final int data4 = op.getData();
                    if (data4 >= 0) {
                        final int n9 = context.offsets[data4];
                        if (n9 >= 0 && n9 == index) {
                            context.offsets[data4] = -1;
                            op = op.next;
                            continue;
                        }
                        context.offsets[data4] = index;
                    }
                    final int matchCharacterIterator = this.matchCharacterIterator(context, op.getChild(), index, n, n2);
                    if (data4 >= 0) {
                        context.offsets[data4] = -1;
                    }
                    if (matchCharacterIterator >= 0) {
                        return matchCharacterIterator;
                    }
                    op = op.next;
                    continue;
                }
                case 9: {
                    final int matchCharacterIterator2 = this.matchCharacterIterator(context, op.getChild(), index, n, n2);
                    if (matchCharacterIterator2 >= 0) {
                        return matchCharacterIterator2;
                    }
                    op = op.next;
                    continue;
                }
                case 8:
                case 10: {
                    final int matchCharacterIterator3 = this.matchCharacterIterator(context, op.next, index, n, n2);
                    if (matchCharacterIterator3 >= 0) {
                        return matchCharacterIterator3;
                    }
                    op = op.getChild();
                    continue;
                }
                case 11: {
                    for (int i = 0; i < op.size(); ++i) {
                        final int matchCharacterIterator4 = this.matchCharacterIterator(context, op.elementAt(i), index, n, n2);
                        if (matchCharacterIterator4 >= 0) {
                            return matchCharacterIterator4;
                        }
                    }
                    return -1;
                }
                case 15: {
                    final int data5 = op.getData();
                    if (context.match != null && data5 > 0) {
                        final int beginning2 = context.match.getBeginning(data5);
                        context.match.setBeginning(data5, index);
                        final int matchCharacterIterator5 = this.matchCharacterIterator(context, op.next, index, n, n2);
                        if (matchCharacterIterator5 < 0) {
                            context.match.setBeginning(data5, beginning2);
                        }
                        return matchCharacterIterator5;
                    }
                    if (context.match != null && data5 < 0) {
                        final int n10 = -data5;
                        final int end = context.match.getEnd(n10);
                        context.match.setEnd(n10, index);
                        final int matchCharacterIterator6 = this.matchCharacterIterator(context, op.next, index, n, n2);
                        if (matchCharacterIterator6 < 0) {
                            context.match.setEnd(n10, end);
                        }
                        return matchCharacterIterator6;
                    }
                    op = op.next;
                    continue;
                }
                case 20: {
                    if (0 > this.matchCharacterIterator(context, op.getChild(), index, 1, n2)) {
                        return -1;
                    }
                    op = op.next;
                    continue;
                }
                case 21: {
                    if (0 <= this.matchCharacterIterator(context, op.getChild(), index, 1, n2)) {
                        return -1;
                    }
                    op = op.next;
                    continue;
                }
                case 22: {
                    if (0 > this.matchCharacterIterator(context, op.getChild(), index, -1, n2)) {
                        return -1;
                    }
                    op = op.next;
                    continue;
                }
                case 23: {
                    if (0 <= this.matchCharacterIterator(context, op.getChild(), index, -1, n2)) {
                        return -1;
                    }
                    op = op.next;
                    continue;
                }
                case 24: {
                    final int matchCharacterIterator7 = this.matchCharacterIterator(context, op.getChild(), index, n, n2);
                    if (matchCharacterIterator7 < 0) {
                        return matchCharacterIterator7;
                    }
                    index = matchCharacterIterator7;
                    op = op.next;
                    continue;
                }
                case 25: {
                    final int matchCharacterIterator8 = this.matchCharacterIterator(context, op.getChild(), index, n, (n2 | op.getData()) & ~op.getData2());
                    if (matchCharacterIterator8 < 0) {
                        return matchCharacterIterator8;
                    }
                    index = matchCharacterIterator8;
                    op = op.next;
                    continue;
                }
                case 26: {
                    final Op.ConditionOp conditionOp = (Op.ConditionOp)op;
                    boolean b;
                    if (conditionOp.refNumber > 0) {
                        if (conditionOp.refNumber >= this.nofparen) {
                            throw new RuntimeException("Internal Error: Reference number must be more than zero: " + conditionOp.refNumber);
                        }
                        b = (context.match.getBeginning(conditionOp.refNumber) >= 0 && context.match.getEnd(conditionOp.refNumber) >= 0);
                    }
                    else {
                        b = (0 <= this.matchCharacterIterator(context, conditionOp.condition, index, n, n2));
                    }
                    if (b) {
                        op = conditionOp.yes;
                        continue;
                    }
                    if (conditionOp.no != null) {
                        op = conditionOp.no;
                        continue;
                    }
                    op = conditionOp.next;
                    continue;
                }
                default: {
                    throw new RuntimeException("Unknown operation type: " + op.type);
                }
            }
        }
        return index;
    }
    
    private static final int getPreviousWordType(final CharacterIterator characterIterator, final int n, final int n2, int n3, final int n4) {
        int i;
        for (i = getWordType(characterIterator, n, n2, --n3, n4); i == 0; i = getWordType(characterIterator, n, n2, --n3, n4)) {}
        return i;
    }
    
    private static final int getWordType(final CharacterIterator characterIterator, final int n, final int n2, final int index, final int n3) {
        if (index < n || index >= n2) {
            return 2;
        }
        return getWordType0(characterIterator.setIndex(index), n3);
    }
    
    private static final boolean regionMatches(final CharacterIterator characterIterator, int n, final int n2, final String s, int n3) {
        if (n < 0) {
            return false;
        }
        if (n2 - n < n3) {
            return false;
        }
        int n4 = 0;
        while (n3-- > 0) {
            if (characterIterator.setIndex(n++) != s.charAt(n4++)) {
                return false;
            }
        }
        return true;
    }
    
    private static final boolean regionMatches(final CharacterIterator characterIterator, int n, final int n2, final int n3, int n4) {
        if (n < 0) {
            return false;
        }
        if (n2 - n < n4) {
            return false;
        }
        int n5 = n3;
        while (n4-- > 0) {
            if (characterIterator.setIndex(n++) != characterIterator.setIndex(n5++)) {
                return false;
            }
        }
        return true;
    }
    
    private static final boolean regionMatchesIgnoreCase(final CharacterIterator characterIterator, int n, final int n2, final String s, int n3) {
        if (n < 0) {
            return false;
        }
        if (n2 - n < n3) {
            return false;
        }
        int n4 = 0;
        while (n3-- > 0) {
            final char setIndex = characterIterator.setIndex(n++);
            final char char1 = s.charAt(n4++);
            if (setIndex == char1) {
                continue;
            }
            final char upperCase = Character.toUpperCase(setIndex);
            final char upperCase2 = Character.toUpperCase(char1);
            if (upperCase == upperCase2) {
                continue;
            }
            if (Character.toLowerCase(upperCase) != Character.toLowerCase(upperCase2)) {
                return false;
            }
        }
        return true;
    }
    
    private static final boolean regionMatchesIgnoreCase(final CharacterIterator characterIterator, int n, final int n2, final int n3, int n4) {
        if (n < 0) {
            return false;
        }
        if (n2 - n < n4) {
            return false;
        }
        int n5 = n3;
        while (n4-- > 0) {
            final char setIndex = characterIterator.setIndex(n++);
            final char setIndex2 = characterIterator.setIndex(n5++);
            if (setIndex == setIndex2) {
                continue;
            }
            final char upperCase = Character.toUpperCase(setIndex);
            final char upperCase2 = Character.toUpperCase(setIndex2);
            if (upperCase == upperCase2) {
                continue;
            }
            if (Character.toLowerCase(upperCase) != Character.toLowerCase(upperCase2)) {
                return false;
            }
        }
        return true;
    }
    
    void prepare() {
        this.compile(this.tokentree);
        this.minlength = this.tokentree.getMinLength();
        this.firstChar = null;
        if (!isSet(this.options, 128) && !isSet(this.options, 512)) {
            final RangeToken range = Token.createRange();
            if (this.tokentree.analyzeFirstCharacter(range, this.options) == 1) {
                range.compactRanges();
                this.firstChar = range;
            }
        }
        if (this.operations != null && (this.operations.type == 6 || this.operations.type == 1) && this.operations.next == null) {
            this.fixedStringOnly = true;
            if (this.operations.type == 6) {
                this.fixedString = this.operations.getString();
            }
            else if (this.operations.getData() >= 65536) {
                this.fixedString = REUtil.decomposeToSurrogates(this.operations.getData());
            }
            else {
                this.fixedString = new String(new char[] { (char)this.operations.getData() });
            }
            this.fixedStringOptions = this.options;
            this.fixedStringTable = new BMPattern(this.fixedString, 256, isSet(this.fixedStringOptions, 2));
        }
        else if (!isSet(this.options, 256) && !isSet(this.options, 512)) {
            final Token.FixedStringContainer fixedStringContainer = new Token.FixedStringContainer();
            this.tokentree.findFixedString(fixedStringContainer, this.options);
            this.fixedString = ((fixedStringContainer.token == null) ? null : fixedStringContainer.token.getString());
            this.fixedStringOptions = fixedStringContainer.options;
            if (this.fixedString != null && this.fixedString.length() < 2) {
                this.fixedString = null;
            }
            if (this.fixedString != null) {
                this.fixedStringTable = new BMPattern(this.fixedString, 256, isSet(this.fixedStringOptions, 2));
            }
        }
    }
    
    private static final boolean isSet(final int n, final int n2) {
        return (n & n2) == n2;
    }
    
    public RegularExpression(final String s) throws ParseException {
        this.hasBackReferences = false;
        this.operations = null;
        this.context = null;
        this.firstChar = null;
        this.fixedString = null;
        this.fixedStringTable = null;
        this.fixedStringOnly = false;
        this.setPattern(s, null);
    }
    
    public RegularExpression(final String s, final String s2) throws ParseException {
        this.hasBackReferences = false;
        this.operations = null;
        this.context = null;
        this.firstChar = null;
        this.fixedString = null;
        this.fixedStringTable = null;
        this.fixedStringOnly = false;
        this.setPattern(s, s2);
    }
    
    RegularExpression(final String regex, final Token tokentree, final int nofparen, final boolean hasBackReferences, final int options) {
        this.hasBackReferences = false;
        this.operations = null;
        this.context = null;
        this.firstChar = null;
        this.fixedString = null;
        this.fixedStringTable = null;
        this.fixedStringOnly = false;
        this.regex = regex;
        this.tokentree = tokentree;
        this.nofparen = nofparen;
        this.options = options;
        this.hasBackReferences = hasBackReferences;
    }
    
    public void setPattern(final String s) throws ParseException {
        this.setPattern(s, this.options);
    }
    
    private void setPattern(final String regex, final int options) throws ParseException {
        this.regex = regex;
        this.options = options;
        final RegexParser regexParser = isSet(this.options, 512) ? new ParserForXMLSchema() : new RegexParser();
        this.tokentree = regexParser.parse(this.regex, this.options);
        this.nofparen = regexParser.parennumber;
        this.hasBackReferences = regexParser.hasBackReferences;
        this.operations = null;
        this.context = null;
    }
    
    public void setPattern(final String s, final String s2) throws ParseException {
        this.setPattern(s, REUtil.parseOptions(s2));
    }
    
    public String getPattern() {
        return this.regex;
    }
    
    public String toString() {
        return this.tokentree.toString(this.options);
    }
    
    public String getOptions() {
        return REUtil.createOptionString(this.options);
    }
    
    public boolean equals(final Object o) {
        if (o == null) {
            return false;
        }
        if (!(o instanceof RegularExpression)) {
            return false;
        }
        final RegularExpression regularExpression = (RegularExpression)o;
        return this.regex.equals(regularExpression.regex) && this.options == regularExpression.options;
    }
    
    boolean equals(final String s, final int n) {
        return this.regex.equals(s) && this.options == n;
    }
    
    public int hashCode() {
        return (this.regex + "/" + this.getOptions()).hashCode();
    }
    
    public int getNumberOfGroups() {
        return this.nofparen;
    }
    
    private static final int getWordType0(final char c, final int n) {
        if (!isSet(n, 64)) {
            if (isSet(n, 32)) {
                if (RegularExpression.wordchar == null) {
                    RegularExpression.wordchar = Token.getRange("IsWord", true);
                }
                return RegularExpression.wordchar.match(c) ? 1 : 2;
            }
            return isWordChar(c) ? 1 : 2;
        }
        else {
            switch (Character.getType(c)) {
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                case 8:
                case 9:
                case 10:
                case 11: {
                    return 1;
                }
                case 6:
                case 7:
                case 16: {
                    return 0;
                }
                case 15: {
                    switch (c) {
                        case '\t':
                        case '\n':
                        case '\u000b':
                        case '\f':
                        case '\r': {
                            return 2;
                        }
                        default: {
                            return 0;
                        }
                    }
                    break;
                }
                default: {
                    return 2;
                }
            }
        }
    }
    
    private static final boolean isEOLChar(final int n) {
        return n == 10 || n == 13 || n == 8232 || n == 8233;
    }
    
    private static final boolean isWordChar(final int n) {
        return n == 95 || (n >= 48 && n <= 122 && (n <= 57 || (n >= 65 && (n <= 90 || n >= 97))));
    }
    
    private static final boolean matchIgnoreCase(final int n, final int n2) {
        if (n == n2) {
            return true;
        }
        if (n > 65535 || n2 > 65535) {
            return false;
        }
        final char upperCase = Character.toUpperCase((char)n);
        final char upperCase2 = Character.toUpperCase((char)n2);
        return upperCase == upperCase2 || Character.toLowerCase(upperCase) == Character.toLowerCase(upperCase2);
    }
    
    static {
        RegularExpression.wordchar = null;
    }
    
    static final class Context
    {
        CharacterIterator ciTarget;
        String strTarget;
        char[] charTarget;
        int start;
        int limit;
        int length;
        Match match;
        boolean inuse;
        int[] offsets;
        
        Context() {
            this.inuse = false;
        }
        
        private void resetCommon(final int n) {
            this.length = this.limit - this.start;
            this.inuse = true;
            this.match = null;
            if (this.offsets == null || this.offsets.length != n) {
                this.offsets = new int[n];
            }
            for (int i = 0; i < n; ++i) {
                this.offsets[i] = -1;
            }
        }
        
        void reset(final CharacterIterator ciTarget, final int start, final int limit, final int n) {
            this.ciTarget = ciTarget;
            this.start = start;
            this.limit = limit;
            this.resetCommon(n);
        }
        
        void reset(final String strTarget, final int start, final int limit, final int n) {
            this.strTarget = strTarget;
            this.start = start;
            this.limit = limit;
            this.resetCommon(n);
        }
        
        void reset(final char[] charTarget, final int start, final int limit, final int n) {
            this.charTarget = charTarget;
            this.start = start;
            this.limit = limit;
            this.resetCommon(n);
        }
    }
}

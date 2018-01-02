// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.selector;

import java.util.Collections;
import org.apache.activemq.util.LRUCache;
import java.io.InputStream;
import org.apache.activemq.filter.ConstantExpression;
import org.apache.activemq.filter.ArithmeticExpression;
import java.util.List;
import java.util.ArrayList;
import org.apache.activemq.filter.ComparisonExpression;
import org.apache.activemq.filter.LogicExpression;
import org.apache.activemq.filter.UnaryExpression;
import org.apache.activemq.filter.PropertyExpression;
import org.apache.activemq.filter.Expression;
import java.io.Reader;
import java.io.StringReader;
import javax.jms.InvalidSelectorException;
import org.apache.activemq.filter.BooleanExpression;
import java.util.Map;

public class SelectorParser implements SelectorParserConstants
{
    private static final Map cache;
    private String sql;
    public SelectorParserTokenManager token_source;
    SimpleCharStream jj_input_stream;
    public Token token;
    public Token jj_nt;
    private int jj_ntk;
    private Token jj_scanpos;
    private Token jj_lastpos;
    private int jj_la;
    public boolean lookingAhead;
    private boolean jj_semLA;
    private final LookaheadSuccess jj_ls;
    
    public static BooleanExpression parse(final String sql) throws InvalidSelectorException {
        final Object result = SelectorParser.cache.get(sql);
        if (result instanceof InvalidSelectorException) {
            throw (InvalidSelectorException)result;
        }
        if (result instanceof BooleanExpression) {
            return (BooleanExpression)result;
        }
        try {
            final BooleanExpression e = new SelectorParser(sql).parse();
            SelectorParser.cache.put(sql, e);
            return e;
        }
        catch (InvalidSelectorException t) {
            SelectorParser.cache.put(sql, t);
            throw t;
        }
    }
    
    public static void clearCache() {
        SelectorParser.cache.clear();
    }
    
    protected SelectorParser(final String sql) {
        this(new StringReader(sql));
        this.sql = sql;
    }
    
    protected BooleanExpression parse() throws InvalidSelectorException {
        try {
            return this.JmsSelector();
        }
        catch (Throwable e) {
            throw (InvalidSelectorException)new InvalidSelectorException(this.sql).initCause(e);
        }
    }
    
    private BooleanExpression asBooleanExpression(final Expression value) throws ParseException {
        if (value instanceof BooleanExpression) {
            return (BooleanExpression)value;
        }
        if (value instanceof PropertyExpression) {
            return UnaryExpression.createBooleanCast(value);
        }
        throw new ParseException("Expression will not result in a boolean value: " + value);
    }
    
    public final BooleanExpression JmsSelector() throws ParseException {
        Expression left = null;
        left = this.orExpression();
        return this.asBooleanExpression(left);
    }
    
    public final Expression orExpression() throws ParseException {
        Expression left = this.andExpression();
        while (true) {
            switch ((this.jj_ntk == -1) ? this.jj_ntk() : this.jj_ntk) {
                case 10: {
                    this.jj_consume_token(10);
                    final Expression right = this.andExpression();
                    left = LogicExpression.createOR(this.asBooleanExpression(left), this.asBooleanExpression(right));
                    continue;
                }
                default: {
                    return left;
                }
            }
        }
    }
    
    public final Expression andExpression() throws ParseException {
        Expression left = this.equalityExpression();
        while (true) {
            switch ((this.jj_ntk == -1) ? this.jj_ntk() : this.jj_ntk) {
                case 9: {
                    this.jj_consume_token(9);
                    final Expression right = this.equalityExpression();
                    left = LogicExpression.createAND(this.asBooleanExpression(left), this.asBooleanExpression(right));
                    continue;
                }
                default: {
                    return left;
                }
            }
        }
    }
    
    public final Expression equalityExpression() throws ParseException {
        Expression left = this.comparisonExpression();
        while (true) {
            switch ((this.jj_ntk == -1) ? this.jj_ntk() : this.jj_ntk) {
                case 15:
                case 28:
                case 29: {
                    switch ((this.jj_ntk == -1) ? this.jj_ntk() : this.jj_ntk) {
                        case 28: {
                            this.jj_consume_token(28);
                            final Expression right = this.comparisonExpression();
                            left = ComparisonExpression.createEqual(left, right);
                            continue;
                        }
                        case 29: {
                            this.jj_consume_token(29);
                            final Expression right = this.comparisonExpression();
                            left = ComparisonExpression.createNotEqual(left, right);
                            continue;
                        }
                        default: {
                            if (this.jj_2_1(2)) {
                                this.jj_consume_token(15);
                                this.jj_consume_token(18);
                                left = ComparisonExpression.createIsNull(left);
                                continue;
                            }
                            switch ((this.jj_ntk == -1) ? this.jj_ntk() : this.jj_ntk) {
                                case 15: {
                                    this.jj_consume_token(15);
                                    this.jj_consume_token(8);
                                    this.jj_consume_token(18);
                                    left = ComparisonExpression.createIsNotNull(left);
                                    continue;
                                }
                                default: {
                                    this.jj_consume_token(-1);
                                    throw new ParseException();
                                }
                            }
                            break;
                        }
                    }
                    break;
                }
                default: {
                    return left;
                }
            }
        }
    }
    
    public final Expression comparisonExpression() throws ParseException {
        Expression left = this.addExpression();
    Label_0005:
        while (true) {
            while (true) {
                switch ((this.jj_ntk == -1) ? this.jj_ntk() : this.jj_ntk) {
                    case 8:
                    case 11:
                    case 12:
                    case 14:
                    case 30:
                    case 31:
                    case 32:
                    case 33: {
                        switch ((this.jj_ntk == -1) ? this.jj_ntk() : this.jj_ntk) {
                            case 30: {
                                this.jj_consume_token(30);
                                final Expression right = this.addExpression();
                                left = ComparisonExpression.createGreaterThan(left, right);
                                continue;
                            }
                            case 31: {
                                this.jj_consume_token(31);
                                final Expression right = this.addExpression();
                                left = ComparisonExpression.createGreaterThanEqual(left, right);
                                continue;
                            }
                            case 32: {
                                this.jj_consume_token(32);
                                final Expression right = this.addExpression();
                                left = ComparisonExpression.createLessThan(left, right);
                                continue;
                            }
                            case 33: {
                                this.jj_consume_token(33);
                                final Expression right = this.addExpression();
                                left = ComparisonExpression.createLessThanEqual(left, right);
                                continue;
                            }
                            case 12: {
                                String u = null;
                                this.jj_consume_token(12);
                                final String t = this.stringLitteral();
                                switch ((this.jj_ntk == -1) ? this.jj_ntk() : this.jj_ntk) {
                                    case 13: {
                                        this.jj_consume_token(13);
                                        u = this.stringLitteral();
                                        break;
                                    }
                                }
                                left = ComparisonExpression.createLike(left, t, u);
                                continue;
                            }
                            default: {
                                if (this.jj_2_2(2)) {
                                    String u = null;
                                    this.jj_consume_token(8);
                                    this.jj_consume_token(12);
                                    final String t = this.stringLitteral();
                                    switch ((this.jj_ntk == -1) ? this.jj_ntk() : this.jj_ntk) {
                                        case 13: {
                                            this.jj_consume_token(13);
                                            u = this.stringLitteral();
                                            break;
                                        }
                                    }
                                    left = ComparisonExpression.createNotLike(left, t, u);
                                    continue;
                                }
                                switch ((this.jj_ntk == -1) ? this.jj_ntk() : this.jj_ntk) {
                                    case 11: {
                                        this.jj_consume_token(11);
                                        final Expression low = this.addExpression();
                                        this.jj_consume_token(9);
                                        final Expression high = this.addExpression();
                                        left = ComparisonExpression.createBetween(left, low, high);
                                        continue;
                                    }
                                    default: {
                                        if (this.jj_2_3(2)) {
                                            this.jj_consume_token(8);
                                            this.jj_consume_token(11);
                                            final Expression low = this.addExpression();
                                            this.jj_consume_token(9);
                                            final Expression high = this.addExpression();
                                            left = ComparisonExpression.createNotBetween(left, low, high);
                                            continue;
                                        }
                                        switch ((this.jj_ntk == -1) ? this.jj_ntk() : this.jj_ntk) {
                                            case 14: {
                                                this.jj_consume_token(14);
                                                this.jj_consume_token(34);
                                                String t = this.stringLitteral();
                                                final ArrayList list = new ArrayList();
                                                list.add(t);
                                                while (true) {
                                                    switch ((this.jj_ntk == -1) ? this.jj_ntk() : this.jj_ntk) {
                                                        case 35: {
                                                            this.jj_consume_token(35);
                                                            t = this.stringLitteral();
                                                            list.add(t);
                                                            continue;
                                                        }
                                                        default: {
                                                            this.jj_consume_token(36);
                                                            left = ComparisonExpression.createInFilter(left, list);
                                                            continue Label_0005;
                                                        }
                                                    }
                                                }
                                                break;
                                            }
                                            default: {
                                                if (!this.jj_2_4(2)) {
                                                    this.jj_consume_token(-1);
                                                    throw new ParseException();
                                                }
                                                this.jj_consume_token(8);
                                                this.jj_consume_token(14);
                                                this.jj_consume_token(34);
                                                String t = this.stringLitteral();
                                                final ArrayList list = new ArrayList();
                                                list.add(t);
                                                while (true) {
                                                    switch ((this.jj_ntk == -1) ? this.jj_ntk() : this.jj_ntk) {
                                                        case 35: {
                                                            this.jj_consume_token(35);
                                                            t = this.stringLitteral();
                                                            list.add(t);
                                                            continue;
                                                        }
                                                        default: {
                                                            this.jj_consume_token(36);
                                                            left = ComparisonExpression.createNotInFilter(left, list);
                                                            continue Label_0005;
                                                        }
                                                    }
                                                }
                                                break;
                                            }
                                        }
                                        break;
                                    }
                                }
                                break;
                            }
                        }
                        break;
                    }
                    default: {
                        return left;
                    }
                }
            }
            break;
        }
    }
    
    public final Expression addExpression() throws ParseException {
        Expression left = this.multExpr();
        while (this.jj_2_5(Integer.MAX_VALUE)) {
            switch ((this.jj_ntk == -1) ? this.jj_ntk() : this.jj_ntk) {
                case 37: {
                    this.jj_consume_token(37);
                    final Expression right = this.multExpr();
                    left = ArithmeticExpression.createPlus(left, right);
                    continue;
                }
                case 38: {
                    this.jj_consume_token(38);
                    final Expression right = this.multExpr();
                    left = ArithmeticExpression.createMinus(left, right);
                    continue;
                }
                default: {
                    this.jj_consume_token(-1);
                    throw new ParseException();
                }
            }
        }
        return left;
    }
    
    public final Expression multExpr() throws ParseException {
        Expression left = this.unaryExpr();
        while (true) {
            switch ((this.jj_ntk == -1) ? this.jj_ntk() : this.jj_ntk) {
                case 39:
                case 40:
                case 41: {
                    switch ((this.jj_ntk == -1) ? this.jj_ntk() : this.jj_ntk) {
                        case 39: {
                            this.jj_consume_token(39);
                            final Expression right = this.unaryExpr();
                            left = ArithmeticExpression.createMultiply(left, right);
                            continue;
                        }
                        case 40: {
                            this.jj_consume_token(40);
                            final Expression right = this.unaryExpr();
                            left = ArithmeticExpression.createDivide(left, right);
                            continue;
                        }
                        case 41: {
                            this.jj_consume_token(41);
                            final Expression right = this.unaryExpr();
                            left = ArithmeticExpression.createMod(left, right);
                            continue;
                        }
                        default: {
                            this.jj_consume_token(-1);
                            throw new ParseException();
                        }
                    }
                    break;
                }
                default: {
                    return left;
                }
            }
        }
    }
    
    public final Expression unaryExpr() throws ParseException {
        String s = null;
        Expression left = null;
        if (this.jj_2_6(Integer.MAX_VALUE)) {
            this.jj_consume_token(37);
            left = this.unaryExpr();
        }
        else {
            switch ((this.jj_ntk == -1) ? this.jj_ntk() : this.jj_ntk) {
                case 38: {
                    this.jj_consume_token(38);
                    left = this.unaryExpr();
                    left = UnaryExpression.createNegate(left);
                    break;
                }
                case 8: {
                    this.jj_consume_token(8);
                    left = this.unaryExpr();
                    left = UnaryExpression.createNOT(this.asBooleanExpression(left));
                    break;
                }
                case 19: {
                    this.jj_consume_token(19);
                    s = this.stringLitteral();
                    left = UnaryExpression.createXPath(s);
                    break;
                }
                case 20: {
                    this.jj_consume_token(20);
                    s = this.stringLitteral();
                    left = UnaryExpression.createXQuery(s);
                    break;
                }
                case 16:
                case 17:
                case 18:
                case 21:
                case 22:
                case 23:
                case 24:
                case 26:
                case 27:
                case 34: {
                    left = this.primaryExpr();
                    break;
                }
                default: {
                    this.jj_consume_token(-1);
                    throw new ParseException();
                }
            }
        }
        return left;
    }
    
    public final Expression primaryExpr() throws ParseException {
        Expression left = null;
        switch ((this.jj_ntk == -1) ? this.jj_ntk() : this.jj_ntk) {
            case 16:
            case 17:
            case 18:
            case 21:
            case 22:
            case 23:
            case 24:
            case 26: {
                left = this.literal();
                break;
            }
            case 27: {
                left = this.variable();
                break;
            }
            case 34: {
                this.jj_consume_token(34);
                left = this.orExpression();
                this.jj_consume_token(36);
                break;
            }
            default: {
                this.jj_consume_token(-1);
                throw new ParseException();
            }
        }
        return left;
    }
    
    public final ConstantExpression literal() throws ParseException {
        ConstantExpression left = null;
        switch ((this.jj_ntk == -1) ? this.jj_ntk() : this.jj_ntk) {
            case 26: {
                final String s = this.stringLitteral();
                left = new ConstantExpression(s);
                break;
            }
            case 21: {
                final Token t = this.jj_consume_token(21);
                left = ConstantExpression.createFromDecimal(t.image);
                break;
            }
            case 22: {
                final Token t = this.jj_consume_token(22);
                left = ConstantExpression.createFromHex(t.image);
                break;
            }
            case 23: {
                final Token t = this.jj_consume_token(23);
                left = ConstantExpression.createFromOctal(t.image);
                break;
            }
            case 24: {
                final Token t = this.jj_consume_token(24);
                left = ConstantExpression.createFloat(t.image);
                break;
            }
            case 16: {
                this.jj_consume_token(16);
                left = ConstantExpression.TRUE;
                break;
            }
            case 17: {
                this.jj_consume_token(17);
                left = ConstantExpression.FALSE;
                break;
            }
            case 18: {
                this.jj_consume_token(18);
                left = ConstantExpression.NULL;
                break;
            }
            default: {
                this.jj_consume_token(-1);
                throw new ParseException();
            }
        }
        return left;
    }
    
    public final String stringLitteral() throws ParseException {
        final StringBuffer rc = new StringBuffer();
        final boolean first = true;
        final Token t = this.jj_consume_token(26);
        final String image = t.image;
        for (int i = 1; i < image.length() - 1; ++i) {
            final char c = image.charAt(i);
            if (c == '\'') {
                ++i;
            }
            rc.append(c);
        }
        return rc.toString();
    }
    
    public final PropertyExpression variable() throws ParseException {
        PropertyExpression left = null;
        final Token t = this.jj_consume_token(27);
        left = new PropertyExpression(t.image);
        return left;
    }
    
    private final boolean jj_2_1(final int xla) {
        this.jj_la = xla;
        final Token token = this.token;
        this.jj_scanpos = token;
        this.jj_lastpos = token;
        try {
            return !this.jj_3_1();
        }
        catch (LookaheadSuccess ls) {
            return true;
        }
    }
    
    private final boolean jj_2_2(final int xla) {
        this.jj_la = xla;
        final Token token = this.token;
        this.jj_scanpos = token;
        this.jj_lastpos = token;
        try {
            return !this.jj_3_2();
        }
        catch (LookaheadSuccess ls) {
            return true;
        }
    }
    
    private final boolean jj_2_3(final int xla) {
        this.jj_la = xla;
        final Token token = this.token;
        this.jj_scanpos = token;
        this.jj_lastpos = token;
        try {
            return !this.jj_3_3();
        }
        catch (LookaheadSuccess ls) {
            return true;
        }
    }
    
    private final boolean jj_2_4(final int xla) {
        this.jj_la = xla;
        final Token token = this.token;
        this.jj_scanpos = token;
        this.jj_lastpos = token;
        try {
            return !this.jj_3_4();
        }
        catch (LookaheadSuccess ls) {
            return true;
        }
    }
    
    private final boolean jj_2_5(final int xla) {
        this.jj_la = xla;
        final Token token = this.token;
        this.jj_scanpos = token;
        this.jj_lastpos = token;
        try {
            return !this.jj_3_5();
        }
        catch (LookaheadSuccess ls) {
            return true;
        }
    }
    
    private final boolean jj_2_6(final int xla) {
        this.jj_la = xla;
        final Token token = this.token;
        this.jj_scanpos = token;
        this.jj_lastpos = token;
        try {
            return !this.jj_3_6();
        }
        catch (LookaheadSuccess ls) {
            return true;
        }
    }
    
    private final boolean jj_3R_12() {
        return this.jj_scan_token(37) || this.jj_3R_10();
    }
    
    private final boolean jj_3R_55() {
        if (this.jj_scan_token(14)) {
            return true;
        }
        if (this.jj_scan_token(34)) {
            return true;
        }
        if (this.jj_3R_21()) {
            return true;
        }
        Token xsp;
        do {
            xsp = this.jj_scanpos;
        } while (!this.jj_3R_60());
        this.jj_scanpos = xsp;
        return this.jj_scan_token(36);
    }
    
    private final boolean jj_3R_47() {
        return this.jj_scan_token(15) || this.jj_scan_token(8) || this.jj_scan_token(18);
    }
    
    private final boolean jj_3R_13() {
        return this.jj_scan_token(38) || this.jj_3R_10();
    }
    
    private final boolean jj_3R_35() {
        return this.jj_scan_token(17);
    }
    
    private final boolean jj_3_1() {
        return this.jj_scan_token(15) || this.jj_scan_token(18);
    }
    
    private final boolean jj_3R_10() {
        final Token xsp = this.jj_scanpos;
        if (this.jj_3R_12()) {
            this.jj_scanpos = xsp;
            if (this.jj_3R_13()) {
                this.jj_scanpos = xsp;
                if (this.jj_3R_14()) {
                    this.jj_scanpos = xsp;
                    if (this.jj_3R_15()) {
                        this.jj_scanpos = xsp;
                        if (this.jj_3R_16()) {
                            this.jj_scanpos = xsp;
                            if (this.jj_3R_17()) {
                                return true;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }
    
    private final boolean jj_3R_34() {
        return this.jj_scan_token(16);
    }
    
    private final boolean jj_3R_46() {
        return this.jj_scan_token(29) || this.jj_3R_41();
    }
    
    private final boolean jj_3_3() {
        return this.jj_scan_token(8) || this.jj_scan_token(11) || this.jj_3R_43() || this.jj_scan_token(9) || this.jj_3R_43();
    }
    
    private final boolean jj_3R_45() {
        return this.jj_scan_token(28) || this.jj_3R_41();
    }
    
    private final boolean jj_3R_42() {
        final Token xsp = this.jj_scanpos;
        if (this.jj_3R_45()) {
            this.jj_scanpos = xsp;
            if (this.jj_3R_46()) {
                this.jj_scanpos = xsp;
                if (this.jj_3_1()) {
                    this.jj_scanpos = xsp;
                    if (this.jj_3R_47()) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    
    private final boolean jj_3R_33() {
        return this.jj_scan_token(24);
    }
    
    private final boolean jj_3R_54() {
        return this.jj_scan_token(11) || this.jj_3R_43() || this.jj_scan_token(9) || this.jj_3R_43();
    }
    
    private final boolean jj_3R_58() {
        return this.jj_scan_token(13) || this.jj_3R_21();
    }
    
    private final boolean jj_3R_32() {
        return this.jj_scan_token(23);
    }
    
    private final boolean jj_3R_20() {
        return this.jj_scan_token(41) || this.jj_3R_10();
    }
    
    private final boolean jj_3R_39() {
        if (this.jj_3R_41()) {
            return true;
        }
        Token xsp;
        do {
            xsp = this.jj_scanpos;
        } while (!this.jj_3R_42());
        this.jj_scanpos = xsp;
        return false;
    }
    
    private final boolean jj_3_2() {
        if (this.jj_scan_token(8)) {
            return true;
        }
        if (this.jj_scan_token(12)) {
            return true;
        }
        if (this.jj_3R_21()) {
            return true;
        }
        final Token xsp = this.jj_scanpos;
        if (this.jj_3R_59()) {
            this.jj_scanpos = xsp;
        }
        return false;
    }
    
    private final boolean jj_3R_53() {
        if (this.jj_scan_token(12)) {
            return true;
        }
        if (this.jj_3R_21()) {
            return true;
        }
        final Token xsp = this.jj_scanpos;
        if (this.jj_3R_58()) {
            this.jj_scanpos = xsp;
        }
        return false;
    }
    
    private final boolean jj_3R_31() {
        return this.jj_scan_token(22);
    }
    
    private final boolean jj_3R_19() {
        return this.jj_scan_token(40) || this.jj_3R_10();
    }
    
    private final boolean jj_3R_18() {
        return this.jj_scan_token(39) || this.jj_3R_10();
    }
    
    private final boolean jj_3R_11() {
        final Token xsp = this.jj_scanpos;
        if (this.jj_3R_18()) {
            this.jj_scanpos = xsp;
            if (this.jj_3R_19()) {
                this.jj_scanpos = xsp;
                if (this.jj_3R_20()) {
                    return true;
                }
            }
        }
        return false;
    }
    
    private final boolean jj_3R_40() {
        return this.jj_scan_token(9) || this.jj_3R_39();
    }
    
    private final boolean jj_3R_30() {
        return this.jj_scan_token(21);
    }
    
    private final boolean jj_3R_9() {
        if (this.jj_3R_10()) {
            return true;
        }
        Token xsp;
        do {
            xsp = this.jj_scanpos;
        } while (!this.jj_3R_11());
        this.jj_scanpos = xsp;
        return false;
    }
    
    private final boolean jj_3R_29() {
        return this.jj_3R_21();
    }
    
    private final boolean jj_3R_57() {
        return this.jj_scan_token(38) || this.jj_3R_9();
    }
    
    private final boolean jj_3_5() {
        final Token xsp = this.jj_scanpos;
        if (this.jj_scan_token(37)) {
            this.jj_scanpos = xsp;
            if (this.jj_scan_token(38)) {
                return true;
            }
        }
        return this.jj_3R_9();
    }
    
    private final boolean jj_3R_37() {
        if (this.jj_3R_39()) {
            return true;
        }
        Token xsp;
        do {
            xsp = this.jj_scanpos;
        } while (!this.jj_3R_40());
        this.jj_scanpos = xsp;
        return false;
    }
    
    private final boolean jj_3R_26() {
        final Token xsp = this.jj_scanpos;
        if (this.jj_3R_29()) {
            this.jj_scanpos = xsp;
            if (this.jj_3R_30()) {
                this.jj_scanpos = xsp;
                if (this.jj_3R_31()) {
                    this.jj_scanpos = xsp;
                    if (this.jj_3R_32()) {
                        this.jj_scanpos = xsp;
                        if (this.jj_3R_33()) {
                            this.jj_scanpos = xsp;
                            if (this.jj_3R_34()) {
                                this.jj_scanpos = xsp;
                                if (this.jj_3R_35()) {
                                    this.jj_scanpos = xsp;
                                    if (this.jj_3R_36()) {
                                        return true;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return false;
    }
    
    private final boolean jj_3R_52() {
        return this.jj_scan_token(33) || this.jj_3R_43();
    }
    
    private final boolean jj_3R_56() {
        return this.jj_scan_token(37) || this.jj_3R_9();
    }
    
    private final boolean jj_3R_51() {
        return this.jj_scan_token(32) || this.jj_3R_43();
    }
    
    private final boolean jj_3R_27() {
        return this.jj_scan_token(27);
    }
    
    private final boolean jj_3R_61() {
        return this.jj_scan_token(35) || this.jj_3R_21();
    }
    
    private final boolean jj_3R_48() {
        final Token xsp = this.jj_scanpos;
        if (this.jj_3R_56()) {
            this.jj_scanpos = xsp;
            if (this.jj_3R_57()) {
                return true;
            }
        }
        return false;
    }
    
    private final boolean jj_3R_38() {
        return this.jj_scan_token(10) || this.jj_3R_37();
    }
    
    private final boolean jj_3R_50() {
        return this.jj_scan_token(31) || this.jj_3R_43();
    }
    
    private final boolean jj_3R_25() {
        return this.jj_scan_token(34) || this.jj_3R_28() || this.jj_scan_token(36);
    }
    
    private final boolean jj_3R_24() {
        return this.jj_3R_27();
    }
    
    private final boolean jj_3R_49() {
        return this.jj_scan_token(30) || this.jj_3R_43();
    }
    
    private final boolean jj_3R_44() {
        final Token xsp = this.jj_scanpos;
        if (this.jj_3R_49()) {
            this.jj_scanpos = xsp;
            if (this.jj_3R_50()) {
                this.jj_scanpos = xsp;
                if (this.jj_3R_51()) {
                    this.jj_scanpos = xsp;
                    if (this.jj_3R_52()) {
                        this.jj_scanpos = xsp;
                        if (this.jj_3R_53()) {
                            this.jj_scanpos = xsp;
                            if (this.jj_3_2()) {
                                this.jj_scanpos = xsp;
                                if (this.jj_3R_54()) {
                                    this.jj_scanpos = xsp;
                                    if (this.jj_3_3()) {
                                        this.jj_scanpos = xsp;
                                        if (this.jj_3R_55()) {
                                            this.jj_scanpos = xsp;
                                            if (this.jj_3_4()) {
                                                return true;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return false;
    }
    
    private final boolean jj_3R_23() {
        return this.jj_3R_26();
    }
    
    private final boolean jj_3R_43() {
        if (this.jj_3R_9()) {
            return true;
        }
        Token xsp;
        do {
            xsp = this.jj_scanpos;
        } while (!this.jj_3R_48());
        this.jj_scanpos = xsp;
        return false;
    }
    
    private final boolean jj_3R_28() {
        if (this.jj_3R_37()) {
            return true;
        }
        Token xsp;
        do {
            xsp = this.jj_scanpos;
        } while (!this.jj_3R_38());
        this.jj_scanpos = xsp;
        return false;
    }
    
    private final boolean jj_3R_22() {
        final Token xsp = this.jj_scanpos;
        if (this.jj_3R_23()) {
            this.jj_scanpos = xsp;
            if (this.jj_3R_24()) {
                this.jj_scanpos = xsp;
                if (this.jj_3R_25()) {
                    return true;
                }
            }
        }
        return false;
    }
    
    private final boolean jj_3R_17() {
        return this.jj_3R_22();
    }
    
    private final boolean jj_3R_21() {
        return this.jj_scan_token(26);
    }
    
    private final boolean jj_3R_60() {
        return this.jj_scan_token(35) || this.jj_3R_21();
    }
    
    private final boolean jj_3R_16() {
        return this.jj_scan_token(20) || this.jj_3R_21();
    }
    
    private final boolean jj_3R_41() {
        if (this.jj_3R_43()) {
            return true;
        }
        Token xsp;
        do {
            xsp = this.jj_scanpos;
        } while (!this.jj_3R_44());
        this.jj_scanpos = xsp;
        return false;
    }
    
    private final boolean jj_3R_59() {
        return this.jj_scan_token(13) || this.jj_3R_21();
    }
    
    private final boolean jj_3_4() {
        if (this.jj_scan_token(8)) {
            return true;
        }
        if (this.jj_scan_token(14)) {
            return true;
        }
        if (this.jj_scan_token(34)) {
            return true;
        }
        if (this.jj_3R_21()) {
            return true;
        }
        Token xsp;
        do {
            xsp = this.jj_scanpos;
        } while (!this.jj_3R_61());
        this.jj_scanpos = xsp;
        return this.jj_scan_token(36);
    }
    
    private final boolean jj_3_6() {
        return this.jj_scan_token(37) || this.jj_3R_10();
    }
    
    private final boolean jj_3R_15() {
        return this.jj_scan_token(19) || this.jj_3R_21();
    }
    
    private final boolean jj_3R_14() {
        return this.jj_scan_token(8) || this.jj_3R_10();
    }
    
    private final boolean jj_3R_36() {
        return this.jj_scan_token(18);
    }
    
    public SelectorParser(final InputStream stream) {
        this.lookingAhead = false;
        this.jj_ls = new LookaheadSuccess();
        this.jj_input_stream = new SimpleCharStream(stream, 1, 1);
        this.token_source = new SelectorParserTokenManager(this.jj_input_stream);
        this.token = new Token();
        this.jj_ntk = -1;
    }
    
    public void ReInit(final InputStream stream) {
        this.jj_input_stream.ReInit(stream, 1, 1);
        this.token_source.ReInit(this.jj_input_stream);
        this.token = new Token();
        this.jj_ntk = -1;
    }
    
    public SelectorParser(final Reader stream) {
        this.lookingAhead = false;
        this.jj_ls = new LookaheadSuccess();
        this.jj_input_stream = new SimpleCharStream(stream, 1, 1);
        this.token_source = new SelectorParserTokenManager(this.jj_input_stream);
        this.token = new Token();
        this.jj_ntk = -1;
    }
    
    public void ReInit(final Reader stream) {
        this.jj_input_stream.ReInit(stream, 1, 1);
        this.token_source.ReInit(this.jj_input_stream);
        this.token = new Token();
        this.jj_ntk = -1;
    }
    
    public SelectorParser(final SelectorParserTokenManager tm) {
        this.lookingAhead = false;
        this.jj_ls = new LookaheadSuccess();
        this.token_source = tm;
        this.token = new Token();
        this.jj_ntk = -1;
    }
    
    public void ReInit(final SelectorParserTokenManager tm) {
        this.token_source = tm;
        this.token = new Token();
        this.jj_ntk = -1;
    }
    
    private final Token jj_consume_token(final int kind) throws ParseException {
        final Token oldToken;
        if ((oldToken = this.token).next != null) {
            this.token = this.token.next;
        }
        else {
            final Token token = this.token;
            final Token nextToken = this.token_source.getNextToken();
            token.next = nextToken;
            this.token = nextToken;
        }
        this.jj_ntk = -1;
        if (this.token.kind == kind) {
            return this.token;
        }
        this.token = oldToken;
        throw this.generateParseException();
    }
    
    private final boolean jj_scan_token(final int kind) {
        if (this.jj_scanpos == this.jj_lastpos) {
            --this.jj_la;
            if (this.jj_scanpos.next == null) {
                final Token jj_scanpos = this.jj_scanpos;
                final Token nextToken = this.token_source.getNextToken();
                jj_scanpos.next = nextToken;
                this.jj_scanpos = nextToken;
                this.jj_lastpos = nextToken;
            }
            else {
                final Token next = this.jj_scanpos.next;
                this.jj_scanpos = next;
                this.jj_lastpos = next;
            }
        }
        else {
            this.jj_scanpos = this.jj_scanpos.next;
        }
        if (this.jj_scanpos.kind != kind) {
            return true;
        }
        if (this.jj_la == 0 && this.jj_scanpos == this.jj_lastpos) {
            throw this.jj_ls;
        }
        return false;
    }
    
    public final Token getNextToken() {
        if (this.token.next != null) {
            this.token = this.token.next;
        }
        else {
            final Token token = this.token;
            final Token nextToken = this.token_source.getNextToken();
            token.next = nextToken;
            this.token = nextToken;
        }
        this.jj_ntk = -1;
        return this.token;
    }
    
    public final Token getToken(final int index) {
        Token t = this.lookingAhead ? this.jj_scanpos : this.token;
        for (int i = 0; i < index; ++i) {
            if (t.next != null) {
                t = t.next;
            }
            else {
                final Token token = t;
                final Token nextToken = this.token_source.getNextToken();
                token.next = nextToken;
                t = nextToken;
            }
        }
        return t;
    }
    
    private final int jj_ntk() {
        final Token next = this.token.next;
        this.jj_nt = next;
        if (next == null) {
            final Token token = this.token;
            final Token nextToken = this.token_source.getNextToken();
            token.next = nextToken;
            return this.jj_ntk = nextToken.kind;
        }
        return this.jj_ntk = this.jj_nt.kind;
    }
    
    public ParseException generateParseException() {
        final Token errortok = this.token.next;
        final int line = errortok.beginLine;
        final int column = errortok.beginColumn;
        final String mess = (errortok.kind == 0) ? SelectorParser.tokenImage[0] : errortok.image;
        return new ParseException("Parse error at line " + line + ", column " + column + ".  Encountered: " + mess);
    }
    
    public final void enable_tracing() {
    }
    
    public final void disable_tracing() {
    }
    
    static {
        cache = Collections.synchronizedMap(new LRUCache<Object, Object>(100));
    }
    
    private static final class LookaheadSuccess extends Error
    {
    }
}

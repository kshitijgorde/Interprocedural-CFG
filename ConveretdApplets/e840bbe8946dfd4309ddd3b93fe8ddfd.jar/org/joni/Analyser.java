// 
// Decompiled by Procyon v0.5.30
// 

package org.joni;

import org.jcodings.CaseFoldCodeItem;
import org.jcodings.IntHolder;
import org.joni.ast.AnchorNode;
import org.joni.ast.CClassNode;
import org.joni.ast.CTypeNode;
import org.joni.ast.StringNode;
import org.joni.ast.CallNode;
import org.joni.ast.BackRefNode;
import org.joni.ast.EncloseNode;
import org.joni.ast.QuantifierNode;
import org.joni.ast.ConsAltNode;
import org.joni.ast.Node;

final class Analyser extends Parser
{
    private static final int GET_CHAR_LEN_VARLEN = -1;
    private static final int GET_CHAR_LEN_TOP_ALT_VARLEN = -2;
    private static final int RECURSION_EXIST = 1;
    private static final int RECURSION_INFINITE = 2;
    private static final int FOUND_CALLED_NODE = 1;
    private static final int THRESHOLD_CASE_FOLD_ALT_FOR_EXPANSION = 8;
    private static final int CEC_THRES_NUM_BIG_REPEAT = 512;
    private static final int CEC_INFINITE_NUM = Integer.MAX_VALUE;
    private static final int CEC_IN_INFINITE_REPEAT = 1;
    private static final int CEC_IN_FINITE_REPEAT = 2;
    private static final int CEC_CONT_BIG_REPEAT = 4;
    private static final int IN_ALT = 1;
    private static final int IN_NOT = 2;
    private static final int IN_REPEAT = 4;
    private static final int IN_VAR_REPEAT = 8;
    private static final int EXPAND_STRING_MAX_LENGTH = 100;
    private static final int MAX_NODE_OPT_INFO_REF_COUNT = 5;
    
    protected Analyser(final ScanEnvironment env, final byte[] bytes, final int p, final int end) {
        super(env, bytes, p, end);
    }
    
    protected final void compile() {
        this.regex.state = -1;
        this.reset();
        this.regex.numMem = 0;
        this.regex.numRepeat = 0;
        this.regex.numNullCheck = 0;
        this.regex.repeatRangeLo = null;
        this.regex.repeatRangeHi = null;
        this.regex.numCombExpCheck = 0;
        this.parse();
        if (this.env.numNamed > 0 && this.syntax.captureOnlyNamedGroup() && !Option.isCaptureGroup(this.regex.options)) {
            if (this.env.numNamed != this.env.numMem) {
                this.root = this.disableNoNameGroupCapture(this.root);
            }
            else {
                this.numberedRefCheck(this.root);
            }
        }
        if (this.env.numCall > 0) {
            this.env.unsetAddrList = new UnsetAddrList(this.env.numCall);
            this.setupSubExpCall(this.root);
            this.subexpRecursiveCheckTrav(this.root);
            this.subexpInfRecursiveCheckTrav(this.root);
            this.regex.numCall = this.env.numCall;
        }
        else {
            this.regex.numCall = 0;
        }
        this.setupTree(this.root, 0);
        this.regex.captureHistory = this.env.captureHistory;
        this.regex.btMemStart = this.env.btMemStart;
        this.regex.btMemEnd = this.env.btMemEnd;
        if (Option.isFindCondition(this.regex.options)) {
            this.regex.btMemEnd = BitStatus.bsAll();
        }
        else {
            this.regex.btMemEnd = this.env.btMemEnd;
            final Regex regex = this.regex;
            regex.btMemEnd |= this.regex.captureHistory;
        }
        this.regex.clearOptimizeInfo();
        this.setOptimizedInfoFromTree(this.root);
        this.env.memNodes = null;
        new ArrayCompiler(this).compile();
        if (this.regex.numRepeat != 0 || this.regex.btMemEnd != 0) {
            this.regex.stackPopLevel = 2;
        }
        else if (this.regex.btMemStart != 0) {
            this.regex.stackPopLevel = 1;
        }
        else {
            this.regex.stackPopLevel = 0;
        }
        this.regex.state = 0;
    }
    
    private Node noNameDisableMap(Node node, final int[] map, final int[] counter) {
        switch (node.getType()) {
            case 8:
            case 9: {
                ConsAltNode can = (ConsAltNode)node;
                do {
                    can.setCar(this.noNameDisableMap(can.car, map, counter));
                } while ((can = can.cdr) != null);
                break;
            }
            case 5: {
                final QuantifierNode qn = (QuantifierNode)node;
                final Node old;
                Node target = old = qn.target;
                target = this.noNameDisableMap(target, map, counter);
                if (target == old) {
                    break;
                }
                qn.setTarget(target);
                if (target.getType() == 5) {
                    qn.reduceNestedQuantifier((QuantifierNode)target);
                    break;
                }
                break;
            }
            case 6: {
                final EncloseNode en = (EncloseNode)node;
                if (en.type != 1) {
                    en.setTarget(this.noNameDisableMap(en.target, map, counter));
                    break;
                }
                if (en.isNamedGroup()) {
                    final int n = 0;
                    ++counter[n];
                    map[en.regNum] = counter[0];
                    en.regNum = counter[0];
                    en.setTarget(this.noNameDisableMap(en.target, map, counter));
                    break;
                }
                node = en.target;
                en.target = null;
                node = this.noNameDisableMap(node, map, counter);
                break;
            }
        }
        return node;
    }
    
    private void renumberByMap(final Node node, final int[] map) {
        switch (node.getType()) {
            case 8:
            case 9: {
                ConsAltNode can = (ConsAltNode)node;
                do {
                    this.renumberByMap(can.car, map);
                } while ((can = can.cdr) != null);
                break;
            }
            case 5: {
                this.renumberByMap(((QuantifierNode)node).target, map);
                break;
            }
            case 6: {
                this.renumberByMap(((EncloseNode)node).target, map);
                break;
            }
            case 4: {
                ((BackRefNode)node).renumber(map);
                break;
            }
        }
    }
    
    protected final void numberedRefCheck(final Node node) {
        switch (node.getType()) {
            case 8:
            case 9: {
                ConsAltNode can = (ConsAltNode)node;
                do {
                    this.numberedRefCheck(can.car);
                } while ((can = can.cdr) != null);
                break;
            }
            case 5: {
                this.numberedRefCheck(((QuantifierNode)node).target);
                break;
            }
            case 6: {
                this.numberedRefCheck(((EncloseNode)node).target);
                break;
            }
            case 4: {
                final BackRefNode br = (BackRefNode)node;
                if (!br.isNameRef()) {
                    this.newValueException("numbered backref/call is not allowed. (use name)");
                    break;
                }
                break;
            }
        }
    }
    
    protected final Node disableNoNameGroupCapture(Node root) {
        final int[] map = new int[this.env.numMem + 1];
        for (int i = 1; i <= this.env.numMem; ++i) {
            map[i] = 0;
        }
        final int[] counter = { 0 };
        root = this.noNameDisableMap(root, map, counter);
        this.renumberByMap(root, map);
        int j = 1;
        int pos = 1;
        while (j <= this.env.numMem) {
            if (map[j] > 0) {
                this.env.memNodes[pos] = this.env.memNodes[j];
                ++pos;
            }
            ++j;
        }
        final int loc = this.env.captureHistory;
        this.env.captureHistory = BitStatus.bsClear();
        for (int k = 1; k <= 31; ++k) {
            if (BitStatus.bsAt(loc, k)) {
                this.env.captureHistory = BitStatus.bsOnAtSimple(this.env.captureHistory, map[k]);
            }
        }
        this.env.numMem = this.env.numNamed;
        this.regex.numMem = this.env.numNamed;
        this.regex.renumberNameTable(map);
        return root;
    }
    
    private void swap(final Node a, final Node b) {
        a.swap(b);
        if (this.root == b) {
            this.root = a;
        }
        else if (this.root == a) {
            this.root = b;
        }
    }
    
    private int quantifiersMemoryInfo(final Node node) {
        int info = 0;
        Label_0214: {
            switch (node.getType()) {
                case 8:
                case 9: {
                    ConsAltNode can = (ConsAltNode)node;
                    do {
                        final int v = this.quantifiersMemoryInfo(can.car);
                        if (v > info) {
                            info = v;
                        }
                    } while ((can = can.cdr) != null);
                    break;
                }
                case 10: {
                    final CallNode cn = (CallNode)node;
                    if (cn.isRecursion()) {
                        return 3;
                    }
                    info = this.quantifiersMemoryInfo(cn.target);
                    break;
                }
                case 5: {
                    final QuantifierNode qn = (QuantifierNode)node;
                    if (qn.upper != 0) {
                        info = this.quantifiersMemoryInfo(qn.target);
                        break;
                    }
                    break;
                }
                case 6: {
                    final EncloseNode en = (EncloseNode)node;
                    switch (en.type) {
                        case 1: {
                            return 2;
                        }
                        case 2:
                        case 4: {
                            info = this.quantifiersMemoryInfo(en.target);
                            break Label_0214;
                        }
                        default: {
                            break Label_0214;
                        }
                    }
                    break;
                }
            }
        }
        return info;
    }
    
    private int getMinMatchLength(final Node node) {
        int min = 0;
        switch (node.getType()) {
            case 4: {
                final BackRefNode br = (BackRefNode)node;
                if (br.isRecursion()) {
                    break;
                }
                if (br.back[0] > this.env.numMem) {
                    this.newValueException("invalid backref number/name");
                }
                min = this.getMinMatchLength(this.env.memNodes[br.back[0]]);
                for (int i = 1; i < br.backNum; ++i) {
                    if (br.back[i] > this.env.numMem) {
                        this.newValueException("invalid backref number/name");
                    }
                    final int tmin = this.getMinMatchLength(this.env.memNodes[br.back[i]]);
                    if (min > tmin) {
                        min = tmin;
                    }
                }
                break;
            }
            case 10: {
                final CallNode cn = (CallNode)node;
                if (cn.isRecursion()) {
                    final EncloseNode en = (EncloseNode)cn.target;
                    if (en.isMinFixed()) {
                        min = en.minLength;
                    }
                    break;
                }
                min = this.getMinMatchLength(cn.target);
                break;
            }
            case 8: {
                ConsAltNode can = (ConsAltNode)node;
                do {
                    min += this.getMinMatchLength(can.car);
                } while ((can = can.cdr) != null);
                break;
            }
            case 9: {
                ConsAltNode y = (ConsAltNode)node;
                do {
                    final Node x = y.car;
                    final int tmin2 = this.getMinMatchLength(x);
                    if (y == node) {
                        min = tmin2;
                    }
                    else {
                        if (min <= tmin2) {
                            continue;
                        }
                        min = tmin2;
                    }
                } while ((y = y.cdr) != null);
                break;
            }
            case 0: {
                min = ((StringNode)node).length();
                break;
            }
            case 2: {
                min = 1;
                break;
            }
            case 1:
            case 3: {
                min = 1;
                break;
            }
            case 5: {
                final QuantifierNode qn = (QuantifierNode)node;
                if (qn.lower > 0) {
                    min = this.getMinMatchLength(qn.target);
                    min = MinMaxLen.distanceMultiply(min, qn.lower);
                    break;
                }
                break;
            }
            case 6: {
                final EncloseNode en2 = (EncloseNode)node;
                switch (en2.type) {
                    case 1: {
                        if (en2.isMinFixed()) {
                            min = en2.minLength;
                            break;
                        }
                        min = this.getMinMatchLength(en2.target);
                        en2.minLength = min;
                        en2.setMinFixed();
                        break;
                    }
                    case 2:
                    case 4: {
                        min = this.getMinMatchLength(en2.target);
                        break;
                    }
                }
                break;
            }
        }
        return min;
    }
    
    private int getMaxMatchLength(final Node node) {
        int max = 0;
        switch (node.getType()) {
            case 8: {
                ConsAltNode ln = (ConsAltNode)node;
                do {
                    final int tmax = this.getMaxMatchLength(ln.car);
                    max = MinMaxLen.distanceAdd(max, tmax);
                } while ((ln = ln.cdr) != null);
                break;
            }
            case 9: {
                ConsAltNode an = (ConsAltNode)node;
                do {
                    final int tmax2 = this.getMaxMatchLength(an.car);
                    if (max < tmax2) {
                        max = tmax2;
                    }
                } while ((an = an.cdr) != null);
                break;
            }
            case 0: {
                max = ((StringNode)node).length();
                break;
            }
            case 2: {
                max = this.enc.maxLengthDistance();
                break;
            }
            case 1:
            case 3: {
                max = this.enc.maxLengthDistance();
                break;
            }
            case 4: {
                final BackRefNode br = (BackRefNode)node;
                if (br.isRecursion()) {
                    max = Integer.MAX_VALUE;
                    break;
                }
                for (int i = 0; i < br.backNum; ++i) {
                    if (br.back[i] > this.env.numMem) {
                        this.newValueException("invalid backref number/name");
                    }
                    final int tmax3 = this.getMaxMatchLength(this.env.memNodes[br.back[i]]);
                    if (max < tmax3) {
                        max = tmax3;
                    }
                }
                break;
            }
            case 10: {
                final CallNode cn = (CallNode)node;
                if (!cn.isRecursion()) {
                    max = this.getMaxMatchLength(cn.target);
                    break;
                }
                max = Integer.MAX_VALUE;
                break;
            }
            case 5: {
                final QuantifierNode qn = (QuantifierNode)node;
                if (qn.upper == 0) {
                    break;
                }
                max = this.getMaxMatchLength(qn.target);
                if (max == 0) {
                    break;
                }
                if (!QuantifierNode.isRepeatInfinite(qn.upper)) {
                    max = MinMaxLen.distanceMultiply(max, qn.upper);
                    break;
                }
                max = Integer.MAX_VALUE;
                break;
            }
            case 6: {
                final EncloseNode en = (EncloseNode)node;
                switch (en.type) {
                    case 1: {
                        if (en.isMaxFixed()) {
                            max = en.maxLength;
                            break;
                        }
                        max = this.getMaxMatchLength(en.target);
                        en.maxLength = max;
                        en.setMaxFixed();
                        break;
                    }
                    case 2:
                    case 4: {
                        max = this.getMaxMatchLength(en.target);
                        break;
                    }
                }
                break;
            }
        }
        return max;
    }
    
    protected final int getCharLengthTree(final Node node) {
        return this.getCharLengthTree(node, 0);
    }
    
    private int getCharLengthTree(final Node node, int level) {
        ++level;
        int len = 0;
        this.returnCode = 0;
        switch (node.getType()) {
            case 8: {
                ConsAltNode ln = (ConsAltNode)node;
                do {
                    final int tlen = this.getCharLengthTree(ln.car, level);
                    if (this.returnCode == 0) {
                        len = MinMaxLen.distanceAdd(len, tlen);
                    }
                    if (this.returnCode == 0) {
                        continue;
                    }
                    break;
                } while ((ln = ln.cdr) != null);
                break;
            }
            case 9: {
                ConsAltNode an = (ConsAltNode)node;
                boolean varLen = false;
                final int tlen2 = this.getCharLengthTree(an.car, level);
                while (this.returnCode == 0 && (an = an.cdr) != null) {
                    final int tlen3 = this.getCharLengthTree(an.car, level);
                    if (this.returnCode == 0 && tlen2 != tlen3) {
                        varLen = true;
                    }
                }
                if (this.returnCode != 0) {
                    break;
                }
                if (!varLen) {
                    len = tlen2;
                    break;
                }
                if (level == 1) {
                    this.returnCode = -2;
                    break;
                }
                this.returnCode = -1;
                break;
            }
            case 0: {
                final StringNode sn = (StringNode)node;
                len = sn.length(this.enc);
                break;
            }
            case 5: {
                final QuantifierNode qn = (QuantifierNode)node;
                if (qn.lower != qn.upper) {
                    this.returnCode = -1;
                    break;
                }
                final int tlen2 = this.getCharLengthTree(qn.target, level);
                if (this.returnCode == 0) {
                    len = MinMaxLen.distanceMultiply(tlen2, qn.lower);
                    break;
                }
                break;
            }
            case 10: {
                final CallNode cn = (CallNode)node;
                if (!cn.isRecursion()) {
                    len = this.getCharLengthTree(cn.target, level);
                    break;
                }
                this.returnCode = -1;
                break;
            }
            case 2: {
                len = 1;
            }
            case 1:
            case 3: {
                len = 1;
                break;
            }
            case 6: {
                final EncloseNode en = (EncloseNode)node;
                switch (en.type) {
                    case 1: {
                        if (en.isCLenFixed()) {
                            len = en.charLength;
                            break;
                        }
                        len = this.getCharLengthTree(en.target, level);
                        if (this.returnCode == 0) {
                            en.charLength = len;
                            en.setCLenFixed();
                            break;
                        }
                        break;
                    }
                    case 2:
                    case 4: {
                        len = this.getCharLengthTree(en.target, level);
                        break;
                    }
                }
                break;
            }
            case 7: {
                break;
            }
            default: {
                this.returnCode = -1;
                break;
            }
        }
        return len;
    }
    
    private boolean isNotIncluded(Node x, Node y) {
    Label_0486:
        while (true) {
            final int yType = y.getType();
            switch (x.getType()) {
                case 2: {
                    switch (yType) {
                        case 2: {
                            final CTypeNode cny = (CTypeNode)y;
                            final CTypeNode cnx = (CTypeNode)x;
                            return cny.ctype == cnx.ctype && cny.not != cnx.not;
                        }
                        case 1: {
                            final Node tmp = x;
                            x = y;
                            y = tmp;
                            continue;
                        }
                        case 0: {
                            final Node tmp = x;
                            x = y;
                            y = tmp;
                            continue;
                        }
                        default: {
                            break Label_0486;
                        }
                    }
                    break;
                }
                case 1: {
                    final CClassNode xc = (CClassNode)x;
                    switch (yType) {
                        case 2: {
                            switch (((CTypeNode)y).ctype) {
                                case 12: {
                                    if (((CTypeNode)y).not) {
                                        for (int i = 0; i < 256; ++i) {
                                            if (!this.enc.isSbWord(i)) {
                                                if (!xc.isNot()) {
                                                    if (xc.bs.at(i)) {
                                                        return false;
                                                    }
                                                }
                                                else if (!xc.bs.at(i)) {
                                                    return false;
                                                }
                                            }
                                        }
                                        return true;
                                    }
                                    if (xc.mbuf == null && !xc.isNot()) {
                                        for (int i = 0; i < 256; ++i) {
                                            if (xc.bs.at(i) && this.enc.isSbWord(i)) {
                                                return false;
                                            }
                                        }
                                        return true;
                                    }
                                    return false;
                                }
                                default: {
                                    break Label_0486;
                                }
                            }
                            break;
                        }
                        case 1: {
                            final CClassNode yc = (CClassNode)y;
                            for (int j = 0; j < 256; ++j) {
                                boolean v = xc.bs.at(j);
                                if ((v && !xc.isNot()) || (!v && xc.isNot())) {
                                    v = yc.bs.at(j);
                                    if ((v && !yc.isNot()) || (!v && yc.isNot())) {
                                        return false;
                                    }
                                }
                            }
                            return (xc.mbuf == null && !xc.isNot()) || (yc.mbuf == null && !yc.isNot());
                        }
                        case 0: {
                            final Node tmp = x;
                            x = y;
                            y = tmp;
                            continue;
                        }
                        default: {
                            break Label_0486;
                        }
                    }
                    break;
                }
                case 0: {
                    final StringNode xs = (StringNode)x;
                    if (xs.length() == 0) {
                        break Label_0486;
                    }
                    switch (yType) {
                        case 2: {
                            final CTypeNode cy = (CTypeNode)y;
                            switch (cy.ctype) {
                                case 12: {
                                    if (this.enc.isMbcWord(xs.bytes, xs.p, xs.end)) {
                                        return cy.not;
                                    }
                                    return !cy.not;
                                }
                                default: {
                                    break Label_0486;
                                }
                            }
                            break;
                        }
                        case 1: {
                            final CClassNode cc = (CClassNode)y;
                            final int code = this.enc.mbcToCode(xs.bytes, xs.p, xs.p + this.enc.maxLength());
                            return !cc.isCodeInCC(this.enc, code);
                        }
                        case 0: {
                            final StringNode ys = (StringNode)y;
                            int len = xs.length();
                            if (len > ys.length()) {
                                len = ys.length();
                            }
                            if (xs.isAmbig() || ys.isAmbig()) {
                                return false;
                            }
                            for (int k = 0, p = ys.p, q = xs.p; k < len; ++k, ++p, ++q) {
                                if (ys.bytes[p] != xs.bytes[q]) {
                                    return true;
                                }
                            }
                            break Label_0486;
                        }
                        default: {
                            break Label_0486;
                        }
                    }
                    break;
                }
                default: {
                    break Label_0486;
                }
            }
        }
        return false;
    }
    
    private Node getHeadValueNode(final Node node, final boolean exact) {
        Node n = null;
        switch (node.getType()) {
            case 3:
            case 4:
            case 9: {}
            case 1:
            case 2: {
                if (!exact) {
                    n = node;
                    break;
                }
                break;
            }
            case 8: {
                n = this.getHeadValueNode(((ConsAltNode)node).car, exact);
                break;
            }
            case 0: {
                final StringNode sn = (StringNode)node;
                if (sn.end <= sn.p) {
                    break;
                }
                if (exact && !sn.isRaw() && Option.isIgnoreCase(this.regex.options)) {
                    break;
                }
                n = node;
                break;
            }
            case 5: {
                final QuantifierNode qn = (QuantifierNode)node;
                if (qn.lower <= 0) {
                    break;
                }
                if (qn.headExact != null) {
                    n = qn.headExact;
                    break;
                }
                n = this.getHeadValueNode(qn.target, exact);
                break;
            }
            case 6: {
                final EncloseNode en = (EncloseNode)node;
                switch (en.type) {
                    case 2: {
                        final int options = this.regex.options;
                        this.regex.options = en.option;
                        n = this.getHeadValueNode(en.target, exact);
                        this.regex.options = options;
                        break;
                    }
                    case 1:
                    case 4: {
                        n = this.getHeadValueNode(en.target, exact);
                        break;
                    }
                }
                break;
            }
            case 7: {
                final AnchorNode an = (AnchorNode)node;
                if (an.type == 1024) {
                    n = this.getHeadValueNode(an.target, exact);
                    break;
                }
                break;
            }
        }
        return n;
    }
    
    private boolean checkTypeTree(final Node node, final int typeMask, final int encloseMask, final int anchorMask) {
        if ((node.getType2Bit() & typeMask) == 0x0) {
            return true;
        }
        boolean invalid = false;
        switch (node.getType()) {
            case 8:
            case 9: {
                ConsAltNode can = (ConsAltNode)node;
                do {
                    invalid = this.checkTypeTree(can.car, typeMask, encloseMask, anchorMask);
                    if (!invalid) {
                        continue;
                    }
                    break;
                } while ((can = can.cdr) != null);
                break;
            }
            case 5: {
                invalid = this.checkTypeTree(((QuantifierNode)node).target, typeMask, encloseMask, anchorMask);
                break;
            }
            case 6: {
                final EncloseNode en = (EncloseNode)node;
                if ((en.type & encloseMask) == 0x0) {
                    return true;
                }
                invalid = this.checkTypeTree(en.target, typeMask, encloseMask, anchorMask);
                break;
            }
            case 7: {
                final AnchorNode an = (AnchorNode)node;
                if ((an.type & anchorMask) == 0x0) {
                    return true;
                }
                if (an.target != null) {
                    invalid = this.checkTypeTree(an.target, typeMask, encloseMask, anchorMask);
                    break;
                }
                break;
            }
        }
        return invalid;
    }
    
    private int subexpInfRecursiveCheck(final Node node, boolean head) {
        int r = 0;
        switch (node.getType()) {
            case 8: {
                ConsAltNode x = (ConsAltNode)node;
                do {
                    final int ret = this.subexpInfRecursiveCheck(x.car, head);
                    if (ret == 2) {
                        return ret;
                    }
                    r |= ret;
                    if (!head) {
                        continue;
                    }
                    final int min = this.getMinMatchLength(x.car);
                    if (min == 0) {
                        continue;
                    }
                    head = false;
                } while ((x = x.cdr) != null);
                break;
            }
            case 9: {
                ConsAltNode can = (ConsAltNode)node;
                r = 1;
                do {
                    final int ret2 = this.subexpInfRecursiveCheck(can.car, head);
                    if (ret2 == 2) {
                        return ret2;
                    }
                    r &= ret2;
                } while ((can = can.cdr) != null);
                break;
            }
            case 5: {
                final QuantifierNode qn = (QuantifierNode)node;
                r = this.subexpInfRecursiveCheck(qn.target, head);
                if (r == 1 && qn.lower == 0) {
                    r = 0;
                    break;
                }
                break;
            }
            case 7: {
                final AnchorNode an = (AnchorNode)node;
                switch (an.type) {
                    case 1024:
                    case 2048:
                    case 4096:
                    case 8192: {
                        r = this.subexpInfRecursiveCheck(an.target, head);
                        break;
                    }
                }
                break;
            }
            case 10: {
                r = this.subexpInfRecursiveCheck(((CallNode)node).target, head);
                break;
            }
            case 6: {
                final EncloseNode en = (EncloseNode)node;
                if (en.isMark2()) {
                    return 0;
                }
                if (en.isMark1()) {
                    return head ? 2 : 1;
                }
                en.setMark2();
                r = this.subexpInfRecursiveCheck(en.target, head);
                en.clearMark2();
                break;
            }
        }
        return r;
    }
    
    protected final int subexpInfRecursiveCheckTrav(final Node node) {
        int r = 0;
        switch (node.getType()) {
            case 8:
            case 9: {
                ConsAltNode can = (ConsAltNode)node;
                do {
                    r = this.subexpInfRecursiveCheckTrav(can.car);
                    if (r == 0) {
                        continue;
                    }
                    break;
                } while ((can = can.cdr) != null);
                break;
            }
            case 5: {
                r = this.subexpInfRecursiveCheckTrav(((QuantifierNode)node).target);
                break;
            }
            case 7: {
                final AnchorNode an = (AnchorNode)node;
                switch (an.type) {
                    case 1024:
                    case 2048:
                    case 4096:
                    case 8192: {
                        r = this.subexpInfRecursiveCheckTrav(an.target);
                        break;
                    }
                }
                break;
            }
            case 6: {
                final EncloseNode en = (EncloseNode)node;
                if (en.isRecursion()) {
                    en.setMark1();
                    r = this.subexpInfRecursiveCheck(en.target, true);
                    if (r > 0) {
                        this.newValueException("never ending recursion");
                    }
                    en.clearMark1();
                }
                r = this.subexpInfRecursiveCheckTrav(en.target);
                break;
            }
        }
        return r;
    }
    
    private int subexpRecursiveCheck(final Node node) {
        int r = 0;
        switch (node.getType()) {
            case 8:
            case 9: {
                ConsAltNode can = (ConsAltNode)node;
                do {
                    r |= this.subexpRecursiveCheck(can.car);
                } while ((can = can.cdr) != null);
                break;
            }
            case 5: {
                r = this.subexpRecursiveCheck(((QuantifierNode)node).target);
                break;
            }
            case 7: {
                final AnchorNode an = (AnchorNode)node;
                switch (an.type) {
                    case 1024:
                    case 2048:
                    case 4096:
                    case 8192: {
                        r = this.subexpRecursiveCheck(an.target);
                        break;
                    }
                }
                break;
            }
            case 10: {
                final CallNode cn = (CallNode)node;
                r = this.subexpRecursiveCheck(cn.target);
                if (r != 0) {
                    cn.setRecursion();
                    break;
                }
                break;
            }
            case 6: {
                final EncloseNode en = (EncloseNode)node;
                if (en.isMark2()) {
                    return 0;
                }
                if (en.isMark1()) {
                    return 1;
                }
                en.setMark2();
                r = this.subexpRecursiveCheck(en.target);
                en.clearMark2();
                break;
            }
        }
        return r;
    }
    
    protected final int subexpRecursiveCheckTrav(final Node node) {
        int r = 0;
        switch (node.getType()) {
            case 8:
            case 9: {
                ConsAltNode can = (ConsAltNode)node;
                do {
                    final int ret = this.subexpRecursiveCheckTrav(can.car);
                    if (ret == 1) {
                        r = 1;
                    }
                } while ((can = can.cdr) != null);
                break;
            }
            case 5: {
                final QuantifierNode qn = (QuantifierNode)node;
                r = this.subexpRecursiveCheckTrav(qn.target);
                if (qn.upper == 0 && r == 1) {
                    qn.isRefered = true;
                    break;
                }
                break;
            }
            case 7: {
                final AnchorNode an = (AnchorNode)node;
                switch (an.type) {
                    case 1024:
                    case 2048:
                    case 4096:
                    case 8192: {
                        r = this.subexpRecursiveCheckTrav(an.target);
                        break;
                    }
                }
                break;
            }
            case 6: {
                final EncloseNode en = (EncloseNode)node;
                if (!en.isRecursion() && en.isCalled()) {
                    en.setMark1();
                    r = this.subexpRecursiveCheck(en.target);
                    if (r != 0) {
                        en.setRecursion();
                    }
                    en.clearMark1();
                }
                r = this.subexpRecursiveCheckTrav(en.target);
                if (en.isCalled()) {
                    r |= 0x1;
                    break;
                }
                break;
            }
        }
        return r;
    }
    
    protected final void setupSubExpCall(final Node node) {
        Label_0545: {
            switch (node.getType()) {
                case 8: {
                    ConsAltNode ln = (ConsAltNode)node;
                    do {
                        this.setupSubExpCall(ln.car);
                    } while ((ln = ln.cdr) != null);
                    break;
                }
                case 9: {
                    ConsAltNode can = (ConsAltNode)node;
                    do {
                        this.setupSubExpCall(can.car);
                    } while ((can = can.cdr) != null);
                    break;
                }
                case 5: {
                    this.setupSubExpCall(((QuantifierNode)node).target);
                    break;
                }
                case 6: {
                    this.setupSubExpCall(((EncloseNode)node).target);
                    break;
                }
                case 10: {
                    final CallNode cn = (CallNode)node;
                    if (cn.groupNum != 0) {
                        final int gNum = cn.groupNum;
                        if (this.env.numNamed > 0 && this.syntax.captureOnlyNamedGroup() && !Option.isCaptureGroup(this.env.option)) {
                            this.newValueException("numbered backref/call is not allowed. (use name)");
                        }
                        if (gNum > this.env.numMem) {
                            this.newValueException("undefined group <%n> reference", cn.nameP, cn.nameEnd);
                        }
                        cn.target = this.env.memNodes[cn.groupNum];
                        if (cn.target == null) {
                            this.newValueException("undefined name <%n> reference", cn.nameP, cn.nameEnd);
                        }
                        ((EncloseNode)cn.target).setCalled();
                        this.env.btMemStart = BitStatus.bsOnAt(this.env.btMemStart, cn.groupNum);
                        cn.unsetAddrList = this.env.unsetAddrList;
                        break;
                    }
                    final NameEntry ne = this.regex.nameToGroupNumbers(cn.name, cn.nameP, cn.nameEnd);
                    if (ne == null) {
                        this.newValueException("undefined name <%n> reference", cn.nameP, cn.nameEnd);
                    }
                    else if (ne.backNum > 1) {
                        this.newValueException("multiplex definition name <%n> call", cn.nameP, cn.nameEnd);
                    }
                    else {
                        cn.groupNum = ne.backRef1;
                        cn.target = this.env.memNodes[cn.groupNum];
                        if (cn.target == null) {
                            this.newValueException("undefined name <%n> reference", cn.nameP, cn.nameEnd);
                        }
                        ((EncloseNode)cn.target).setCalled();
                        this.env.btMemStart = BitStatus.bsOnAt(this.env.btMemStart, cn.groupNum);
                        cn.unsetAddrList = this.env.unsetAddrList;
                    }
                    break;
                }
                case 7: {
                    final AnchorNode an = (AnchorNode)node;
                    switch (an.type) {
                        case 1024:
                        case 2048:
                        case 4096:
                        case 8192: {
                            this.setupSubExpCall(an.target);
                            break Label_0545;
                        }
                    }
                    break;
                }
            }
        }
    }
    
    private void divideLookBehindAlternatives(Node node) {
        final AnchorNode an = (AnchorNode)node;
        final int anchorType = an.type;
        Node head = an.target;
        Node np = ((ConsAltNode)head).car;
        this.swap(node, head);
        final Node tmp = node;
        node = head;
        head = tmp;
        ((ConsAltNode)node).setCar(head);
        ((AnchorNode)head).setTarget(np);
        np = node;
        while ((np = ((ConsAltNode)np).cdr) != null) {
            final AnchorNode insert = new AnchorNode(anchorType);
            insert.setTarget(((ConsAltNode)np).car);
            ((ConsAltNode)np).setCar(insert);
        }
        if (anchorType == 8192) {
            np = node;
            do {
                ((ConsAltNode)np).toListNode();
            } while ((np = ((ConsAltNode)np).cdr) != null);
        }
    }
    
    private void setupLookBehind(final Node node) {
        final AnchorNode an = (AnchorNode)node;
        final int len = this.getCharLengthTree(an.target);
        switch (this.returnCode) {
            case 0: {
                an.charLength = len;
                break;
            }
            case -1: {
                this.newSyntaxException("invalid pattern in look-behind");
                break;
            }
            case -2: {
                if (this.syntax.differentLengthAltLookBehind()) {
                    this.divideLookBehindAlternatives(node);
                    break;
                }
                this.newSyntaxException("invalid pattern in look-behind");
                break;
            }
        }
    }
    
    private void nextSetup(Node node, final Node nextNode) {
        while (true) {
            final int type = node.getType();
            if (type == 5) {
                final QuantifierNode qn = (QuantifierNode)node;
                if (qn.greedy && QuantifierNode.isRepeatInfinite(qn.upper)) {
                    final StringNode n = (StringNode)this.getHeadValueNode(nextNode, true);
                    if (n != null && n.bytes[n.p] != 0) {
                        qn.nextHeadExact = n;
                    }
                    if (qn.lower <= 1 && qn.target.isSimple()) {
                        final Node x = this.getHeadValueNode(qn.target, false);
                        if (x != null) {
                            final Node y = this.getHeadValueNode(nextNode, false);
                            if (y != null && this.isNotIncluded(x, y)) {
                                final EncloseNode en = new EncloseNode(4);
                                en.setStopBtSimpleRepeat();
                                this.swap(node, en);
                                en.setTarget(node);
                            }
                        }
                    }
                }
                break;
            }
            if (type != 6) {
                break;
            }
            final EncloseNode en2 = (EncloseNode)node;
            if (!en2.isMemory()) {
                break;
            }
            node = en2.target;
        }
    }
    
    private void updateStringNodeCaseFold(final Node node) {
        final StringNode sn = (StringNode)node;
        byte[] sbuf = new byte[sn.length() << 1];
        int sp = 0;
        this.value = sn.p;
        final int end = sn.end;
        final byte[] buf = new byte[18];
        while (this.value < end) {
            for (int len = this.enc.mbcCaseFold(this.regex.caseFoldFlag, sn.bytes, this, end, buf), i = 0; i < len; ++i) {
                if (sp >= sbuf.length) {
                    final byte[] tmp = new byte[sbuf.length << 1];
                    System.arraycopy(sbuf, 0, tmp, 0, sbuf.length);
                    sbuf = tmp;
                }
                sbuf[sp++] = buf[i];
            }
        }
        sn.set(sbuf, 0, sp);
    }
    
    private Node expandCaseFoldMakeRemString(final byte[] bytes, final int p, final int end) {
        final StringNode node = new StringNode(bytes, p, end);
        this.updateStringNodeCaseFold(node);
        node.setAmbig();
        node.setDontGetOptInfo();
        return node;
    }
    
    private boolean expandCaseFoldStringAlt(final int itemNum, final CaseFoldCodeItem[] items, final byte[] bytes, final int p, final int slen, final int end, final Node[] node) {
        boolean varlen = false;
        for (int i = 0; i < itemNum; ++i) {
            if (items[i].byteLen != slen) {
                varlen = true;
                break;
            }
        }
        ConsAltNode varANode = null;
        ConsAltNode anode;
        if (varlen) {
            varANode = (ConsAltNode)(node[0] = ConsAltNode.newAltNode(null, null));
            final ConsAltNode xnode = ConsAltNode.newListNode(null, null);
            varANode.setCar(xnode);
            anode = ConsAltNode.newAltNode(null, null);
            xnode.setCar(anode);
        }
        else {
            anode = (ConsAltNode)(node[0] = ConsAltNode.newAltNode(null, null));
        }
        StringNode snode = new StringNode(bytes, p, p + slen);
        anode.setCar(snode);
        for (int j = 0; j < itemNum; ++j) {
            snode = new StringNode();
            for (int k = 0; k < items[j].codeLen; ++k) {
                snode.ensure(7);
                final StringNode stringNode = snode;
                stringNode.end += this.enc.codeToMbc(items[j].code[k], snode.bytes, snode.end);
            }
            final ConsAltNode an = ConsAltNode.newAltNode(null, null);
            if (items[j].byteLen != slen) {
                final int q = p + items[j].byteLen;
                if (q < end) {
                    final Node rem = this.expandCaseFoldMakeRemString(bytes, q, end);
                    final ConsAltNode xnode = ConsAltNode.listAdd(null, snode);
                    ConsAltNode.listAdd(xnode, rem);
                    an.setCar(xnode);
                }
                else {
                    an.setCar(snode);
                }
                varANode.setCdr(an);
                varANode = an;
            }
            else {
                an.setCar(snode);
                anode.setCdr(an);
                anode = an;
            }
        }
        return varlen;
    }
    
    private void expandCaseFoldString(final Node node) {
        final StringNode sn = (StringNode)node;
        if (sn.isAmbig() || sn.length() <= 0) {
            return;
        }
        final byte[] bytes = sn.bytes;
        int p = sn.p;
        final int end = sn.end;
        int altNum = 1;
        ConsAltNode topRoot = null;
        ConsAltNode root = null;
        final Node[] prevNode = { null };
        StringNode snode = null;
        while (p < end) {
            final CaseFoldCodeItem[] items = this.enc.caseFoldCodesByString(this.regex.caseFoldFlag, bytes, p, end);
            final int len = this.enc.length(bytes, p, end);
            if (items.length == 0) {
                if (snode == null) {
                    if (root == null && prevNode[0] != null) {
                        root = (topRoot = ConsAltNode.listAdd(null, prevNode[0]));
                    }
                    snode = (StringNode)(prevNode[0] = new StringNode());
                    if (root != null) {
                        ConsAltNode.listAdd(root, snode);
                    }
                }
                snode.cat(bytes, p, p + len);
            }
            else {
                altNum *= items.length + 1;
                if (altNum > 8) {
                    break;
                }
                if (root == null && prevNode[0] != null) {
                    root = (topRoot = ConsAltNode.listAdd(null, prevNode[0]));
                }
                final boolean r = this.expandCaseFoldStringAlt(items.length, items, bytes, p, len, end, prevNode);
                if (r) {
                    if (root == null) {
                        topRoot = (ConsAltNode)prevNode[0];
                    }
                    else {
                        ConsAltNode.listAdd(root, prevNode[0]);
                    }
                    root = (ConsAltNode)((ConsAltNode)prevNode[0]).car;
                }
                else if (root != null) {
                    ConsAltNode.listAdd(root, prevNode[0]);
                }
                snode = null;
            }
            p += len;
        }
        if (p < end) {
            final Node srem = this.expandCaseFoldMakeRemString(bytes, p, end);
            if (prevNode[0] != null && root == null) {
                root = (topRoot = ConsAltNode.listAdd(null, prevNode[0]));
            }
            if (root == null) {
                prevNode[0] = srem;
            }
            else {
                ConsAltNode.listAdd(root, srem);
            }
        }
        final Node xnode = (topRoot != null) ? topRoot : prevNode[0];
        this.swap(node, xnode);
    }
    
    protected final int setupCombExpCheck(final Node node, final int state) {
        int r = state;
        Label_0578: {
            switch (node.getType()) {
                case 8: {
                    ConsAltNode ln = (ConsAltNode)node;
                    do {
                        r = this.setupCombExpCheck(ln.car, r);
                        if (r >= 0) {
                            continue;
                        }
                        break;
                    } while ((ln = ln.cdr) != null);
                    break;
                }
                case 9: {
                    ConsAltNode an = (ConsAltNode)node;
                    do {
                        final int ret = this.setupCombExpCheck(an.car, state);
                        r |= ret;
                        if (ret >= 0) {
                            continue;
                        }
                        break;
                    } while ((an = an.cdr) != null);
                    break;
                }
                case 5: {
                    final QuantifierNode qn = (QuantifierNode)node;
                    int childState = state;
                    int addState = 0;
                    if (!QuantifierNode.isRepeatInfinite(qn.upper) && qn.upper > 1) {
                        childState |= 0x2;
                        if (this.env.backrefedMem == 0 && qn.target.getType() == 6) {
                            final EncloseNode en = (EncloseNode)qn.target;
                            if (en.type == 1 && en.target.getType() == 5) {
                                final QuantifierNode q = (QuantifierNode)en.target;
                                if (QuantifierNode.isRepeatInfinite(q.upper) && q.greedy == qn.greedy) {
                                    qn.upper = ((qn.lower == 0) ? 1 : qn.lower);
                                    if (qn.upper == 1) {
                                        childState = state;
                                    }
                                }
                            }
                        }
                    }
                    if ((state & 0x2) != 0x0) {
                        qn.combExpCheckNum = -1;
                    }
                    else {
                        int varNum;
                        if (QuantifierNode.isRepeatInfinite(qn.upper)) {
                            varNum = Integer.MAX_VALUE;
                            childState |= 0x1;
                        }
                        else {
                            varNum = qn.upper - qn.lower;
                        }
                        if (varNum >= 512) {
                            addState |= 0x4;
                        }
                        if ((((state & 0x1) != 0x0 && varNum != 0) || ((state & 0x4) != 0x0 && varNum >= 512)) && qn.combExpCheckNum == 0) {
                            final ScanEnvironment env = this.env;
                            ++env.numCombExpCheck;
                            qn.combExpCheckNum = this.env.numCombExpCheck;
                            if (this.env.currMaxRegNum > this.env.combExpMaxRegNum) {
                                this.env.combExpMaxRegNum = this.env.currMaxRegNum;
                            }
                        }
                    }
                    r = this.setupCombExpCheck(qn.target, childState);
                    r |= addState;
                    break;
                }
                case 6: {
                    final EncloseNode en = (EncloseNode)node;
                    switch (en.type) {
                        case 1: {
                            if (this.env.currMaxRegNum < en.regNum) {
                                this.env.currMaxRegNum = en.regNum;
                            }
                            r = this.setupCombExpCheck(en.target, state);
                            break Label_0578;
                        }
                        default: {
                            r = this.setupCombExpCheck(en.target, state);
                            break Label_0578;
                        }
                    }
                    break;
                }
                case 10: {
                    final CallNode cn = (CallNode)node;
                    if (cn.isRecursion()) {
                        this.env.hasRecursion = true;
                        break;
                    }
                    r = this.setupCombExpCheck(cn.target, state);
                    break;
                }
            }
        }
        return r;
    }
    
    protected final void setupTree(final Node node, int state) {
        switch (node.getType()) {
            case 8: {
                ConsAltNode lin = (ConsAltNode)node;
                Node prev = null;
                do {
                    this.setupTree(lin.car, state);
                    if (prev != null) {
                        this.nextSetup(prev, lin.car);
                    }
                    prev = lin.car;
                } while ((lin = lin.cdr) != null);
                break;
            }
            case 9: {
                ConsAltNode aln = (ConsAltNode)node;
                do {
                    this.setupTree(aln.car, state | 0x1);
                } while ((aln = aln.cdr) != null);
            }
            case 0: {
                if (Option.isIgnoreCase(this.regex.options) && !((StringNode)node).isRaw()) {
                    this.expandCaseFoldString(node);
                    break;
                }
                break;
            }
            case 2:
            case 3: {}
            case 4: {
                final BackRefNode br = (BackRefNode)node;
                for (int i = 0; i < br.backNum; ++i) {
                    if (br.back[i] > this.env.numMem) {
                        this.newValueException("invalid backref number/name");
                    }
                    this.env.backrefedMem = BitStatus.bsOnAt(this.env.backrefedMem, br.back[i]);
                    this.env.btMemStart = BitStatus.bsOnAt(this.env.btMemStart, br.back[i]);
                    if (br.isNestLevel()) {
                        this.env.btMemEnd = BitStatus.bsOnAt(this.env.btMemEnd, br.back[i]);
                    }
                    ((EncloseNode)this.env.memNodes[br.back[i]]).setMemBackrefed();
                }
                break;
            }
            case 5: {
                final QuantifierNode qn = (QuantifierNode)node;
                final Node target = qn.target;
                if ((state & 0x4) != 0x0) {
                    qn.setInRepeat();
                }
                if (QuantifierNode.isRepeatInfinite(qn.upper) || qn.lower >= 1) {
                    final int d = this.getMinMatchLength(target);
                    if (d == 0) {
                        qn.targetEmptyInfo = 1;
                        final int info = this.quantifiersMemoryInfo(target);
                        if (info > 0) {
                            qn.targetEmptyInfo = info;
                        }
                    }
                }
                state |= 0x4;
                if (qn.lower != qn.upper) {
                    state |= 0x8;
                }
                this.setupTree(target, state);
                if (target.getType() == 0 && !QuantifierNode.isRepeatInfinite(qn.lower) && qn.lower == qn.upper && qn.lower > 1 && qn.lower <= 100) {
                    final StringNode sn = (StringNode)target;
                    final int len = sn.length();
                    if (len * qn.lower <= 100) {
                        final StringNode str = qn.convertToString();
                        for (int n = qn.lower, j = 0; j < n; ++j) {
                            str.cat(sn.bytes, sn.p, sn.end);
                        }
                        break;
                    }
                    break;
                }
                else {
                    if (!qn.greedy || qn.targetEmptyInfo == 0) {
                        break;
                    }
                    if (target.getType() == 5) {
                        final QuantifierNode tqn = (QuantifierNode)target;
                        if (tqn.headExact != null) {
                            qn.headExact = tqn.headExact;
                            tqn.headExact = null;
                        }
                        break;
                    }
                    qn.headExact = this.getHeadValueNode(qn.target, true);
                    break;
                }
                break;
            }
            case 6: {
                final EncloseNode en = (EncloseNode)node;
                switch (en.type) {
                    case 2: {
                        final int options = this.regex.options;
                        this.regex.options = en.option;
                        this.setupTree(en.target, state);
                        this.regex.options = options;
                        break;
                    }
                    case 1: {
                        if ((state & 0xB) != 0x0) {
                            this.env.btMemStart = BitStatus.bsOnAt(this.env.btMemStart, en.regNum);
                        }
                        this.setupTree(en.target, state);
                        break;
                    }
                    case 4: {
                        this.setupTree(en.target, state);
                        if (en.target.getType() != 5) {
                            break;
                        }
                        final QuantifierNode tqn2 = (QuantifierNode)en.target;
                        if (QuantifierNode.isRepeatInfinite(tqn2.upper) && tqn2.lower <= 1 && tqn2.greedy && tqn2.target.isSimple()) {
                            en.setStopBtSimpleRepeat();
                            break;
                        }
                        break;
                    }
                }
                break;
            }
            case 7: {
                final AnchorNode an = (AnchorNode)node;
                switch (an.type) {
                    case 1024: {
                        this.setupTree(an.target, state);
                        break;
                    }
                    case 2048: {
                        this.setupTree(an.target, state | 0x2);
                        break;
                    }
                    case 4096: {
                        final boolean lbInvalid = this.checkTypeTree(an.target, 2031, 1, 4135);
                        if (lbInvalid) {
                            this.newSyntaxException("invalid pattern in look-behind");
                        }
                        this.setupLookBehind(node);
                        this.setupTree(an.target, state);
                        break;
                    }
                    case 8192: {
                        final boolean lbnInvalid = this.checkTypeTree(an.target, 2031, 1, 4135);
                        if (lbnInvalid) {
                            this.newSyntaxException("invalid pattern in look-behind");
                        }
                        this.setupLookBehind(node);
                        this.setupTree(an.target, state | 0x2);
                        break;
                    }
                }
                break;
            }
        }
    }
    
    private void optimizeNodeLeft(final Node node, final NodeOptInfo opt, final OptEnvironment oenv) {
        opt.clear();
        opt.setBoundNode(oenv.mmd);
        switch (node.getType()) {
            case 8: {
                final OptEnvironment nenv = new OptEnvironment();
                final NodeOptInfo nopt = new NodeOptInfo();
                nenv.copy(oenv);
                ConsAltNode lin = (ConsAltNode)node;
                do {
                    this.optimizeNodeLeft(lin.car, nopt, nenv);
                    nenv.mmd.add(nopt.length);
                    opt.concatLeftNode(nopt, this.enc);
                } while ((lin = lin.cdr) != null);
                break;
            }
            case 9: {
                final NodeOptInfo nopt2 = new NodeOptInfo();
                ConsAltNode aln = (ConsAltNode)node;
                do {
                    this.optimizeNodeLeft(aln.car, nopt2, oenv);
                    if (aln == node) {
                        opt.copy(nopt2);
                    }
                    else {
                        opt.altMerge(nopt2, oenv);
                    }
                } while ((aln = aln.cdr) != null);
                break;
            }
            case 0: {
                final StringNode sn = (StringNode)node;
                final int slen = sn.length();
                if (!sn.isAmbig()) {
                    opt.exb.concatStr(sn.bytes, sn.p, sn.end, sn.isRaw(), this.enc);
                    if (slen > 0) {
                        opt.map.addChar(sn.bytes[sn.p], this.enc);
                    }
                    opt.length.set(slen, slen);
                }
                else {
                    int max;
                    if (sn.isDontGetOptInfo()) {
                        final int n = sn.length(this.enc);
                        max = this.enc.maxLengthDistance() * n;
                    }
                    else {
                        opt.exb.concatStr(sn.bytes, sn.p, sn.end, sn.isRaw(), this.enc);
                        opt.exb.ignoreCase = true;
                        if (slen > 0) {
                            opt.map.addCharAmb(sn.bytes, sn.p, sn.end, this.enc, oenv.caseFoldFlag);
                        }
                        max = slen;
                    }
                    opt.length.set(slen, max);
                }
                if (opt.exb.length == slen) {
                    opt.exb.reachEnd = true;
                    break;
                }
                break;
            }
            case 1: {
                final CClassNode cc = (CClassNode)node;
                if (cc.mbuf != null || cc.isNot()) {
                    final int min = this.enc.minLength();
                    final int max = this.enc.maxLengthDistance();
                    opt.length.set(min, max);
                    break;
                }
                for (int i = 0; i < 256; ++i) {
                    final boolean z = cc.bs.at(i);
                    if ((z && !cc.isNot()) || (!z && cc.isNot())) {
                        opt.map.addChar((byte)i, this.enc);
                    }
                }
                opt.length.set(1, 1);
                break;
            }
            case 2: {
                final int max2 = this.enc.maxLengthDistance();
                int min2;
                if (max2 == 1) {
                    min2 = 1;
                    final CTypeNode cn = (CTypeNode)node;
                    switch (cn.ctype) {
                        case 12: {
                            if (cn.not) {
                                for (int j = 0; j < 256; ++j) {
                                    if (!this.enc.isWord(j)) {
                                        opt.map.addChar((byte)j, this.enc);
                                    }
                                }
                                break;
                            }
                            for (int j = 0; j < 256; ++j) {
                                if (this.enc.isWord(j)) {
                                    opt.map.addChar((byte)j, this.enc);
                                }
                            }
                            break;
                        }
                    }
                }
                else {
                    min2 = this.enc.minLength();
                }
                opt.length.set(min2, max2);
                break;
            }
            case 3: {
                opt.length.set(this.enc.minLength(), this.enc.maxLengthDistance());
                break;
            }
            case 7: {
                final AnchorNode an = (AnchorNode)node;
                switch (an.type) {
                    case 1:
                    case 2:
                    case 4:
                    case 8:
                    case 16:
                    case 32: {
                        opt.anchor.add(an.type);
                        break;
                    }
                    case 1024: {
                        final NodeOptInfo nopt = new NodeOptInfo();
                        this.optimizeNodeLeft(an.target, nopt, oenv);
                        if (nopt.exb.length > 0) {
                            opt.expr.copy(nopt.exb);
                        }
                        else if (nopt.exm.length > 0) {
                            opt.expr.copy(nopt.exm);
                        }
                        opt.expr.reachEnd = false;
                        if (nopt.map.value > 0) {
                            opt.map.copy(nopt.map);
                            break;
                        }
                        break;
                    }
                }
                break;
            }
            case 4: {
                final BackRefNode br = (BackRefNode)node;
                if (br.isRecursion()) {
                    opt.length.set(0, Integer.MAX_VALUE);
                    break;
                }
                final Node[] nodes = oenv.scanEnv.memNodes;
                int min3 = this.getMinMatchLength(nodes[br.back[0]]);
                int max3 = this.getMaxMatchLength(nodes[br.back[0]]);
                for (int k = 1; k < br.backNum; ++k) {
                    final int tmin = this.getMinMatchLength(nodes[br.back[k]]);
                    final int tmax = this.getMaxMatchLength(nodes[br.back[k]]);
                    if (min3 > tmin) {
                        min3 = tmin;
                    }
                    if (max3 < tmax) {
                        max3 = tmax;
                    }
                }
                opt.length.set(min3, max3);
                break;
            }
            case 10: {
                final CallNode cn2 = (CallNode)node;
                if (cn2.isRecursion()) {
                    opt.length.set(0, Integer.MAX_VALUE);
                    break;
                }
                final int safe = oenv.options;
                oenv.options = ((EncloseNode)cn2.target).option;
                this.optimizeNodeLeft(cn2.target, opt, oenv);
                oenv.options = safe;
                break;
            }
            case 5: {
                final NodeOptInfo nopt2 = new NodeOptInfo();
                final QuantifierNode qn = (QuantifierNode)node;
                this.optimizeNodeLeft(qn.target, nopt2, oenv);
                if (qn.lower == 0 && QuantifierNode.isRepeatInfinite(qn.upper)) {
                    if (oenv.mmd.max == 0 && qn.target.getType() == 3 && qn.greedy) {
                        if (Option.isMultiline(oenv.options)) {
                            opt.anchor.add(32768);
                        }
                        else {
                            opt.anchor.add(16384);
                        }
                    }
                }
                else if (qn.lower > 0) {
                    opt.copy(nopt2);
                    if (nopt2.exb.length > 0 && nopt2.exb.reachEnd) {
                        int l;
                        for (l = 1; l < qn.lower && !opt.exb.isFull(); ++l) {
                            opt.exb.concat(nopt2.exb, this.enc);
                        }
                        if (l < qn.lower) {
                            opt.exb.reachEnd = false;
                        }
                    }
                    if (qn.lower != qn.upper) {
                        opt.exb.reachEnd = false;
                        opt.exm.reachEnd = false;
                    }
                    if (qn.lower > 1) {
                        opt.exm.reachEnd = false;
                    }
                }
                final int min3 = MinMaxLen.distanceMultiply(nopt2.length.min, qn.lower);
                int max3;
                if (QuantifierNode.isRepeatInfinite(qn.upper)) {
                    max3 = ((nopt2.length.max > 0) ? Integer.MAX_VALUE : 0);
                }
                else {
                    max3 = MinMaxLen.distanceMultiply(nopt2.length.max, qn.upper);
                }
                opt.length.set(min3, max3);
                break;
            }
            case 6: {
                final EncloseNode en = (EncloseNode)node;
                switch (en.type) {
                    case 2: {
                        final int save = oenv.options;
                        oenv.options = en.option;
                        this.optimizeNodeLeft(en.target, opt, oenv);
                        oenv.options = save;
                        break;
                    }
                    case 1: {
                        if (++en.optCount > 5) {
                            int min3 = 0;
                            int max3 = Integer.MAX_VALUE;
                            if (en.isMinFixed()) {
                                min3 = en.minLength;
                            }
                            if (en.isMaxFixed()) {
                                max3 = en.maxLength;
                            }
                            opt.length.set(min3, max3);
                            break;
                        }
                        this.optimizeNodeLeft(en.target, opt, oenv);
                        if (opt.anchor.isSet(49152) && BitStatus.bsAt(oenv.scanEnv.backrefedMem, en.regNum)) {
                            opt.anchor.remove(49152);
                            break;
                        }
                        break;
                    }
                    case 4: {
                        this.optimizeNodeLeft(en.target, opt, oenv);
                        break;
                    }
                }
                break;
            }
            default: {
                this.newInternalException("internal parser error (bug)");
                break;
            }
        }
    }
    
    protected final void setOptimizedInfoFromTree(final Node node) {
        final NodeOptInfo opt = new NodeOptInfo();
        final OptEnvironment oenv = new OptEnvironment();
        oenv.enc = this.regex.enc;
        oenv.options = this.regex.options;
        oenv.caseFoldFlag = this.regex.caseFoldFlag;
        oenv.scanEnv = this.env;
        oenv.mmd.clear();
        this.optimizeNodeLeft(node, opt, oenv);
        this.regex.anchor = (opt.anchor.leftAnchor & 0xC005);
        final Regex regex = this.regex;
        regex.anchor |= (opt.anchor.rightAnchor & 0x18);
        if ((this.regex.anchor & 0x18) != 0x0) {
            this.regex.anchorDmin = opt.length.min;
            this.regex.anchorDmax = opt.length.max;
        }
        if (opt.exb.length > 0 || opt.exm.length > 0) {
            opt.exb.select(opt.exm, this.enc);
            if (opt.map.value > 0 && opt.exb.compare(opt.map) > 0) {
                this.regex.setOptimizeMapInfo(opt.map);
                this.regex.setSubAnchor(opt.map.anchor);
            }
            else {
                this.regex.setExactInfo(opt.exb);
                this.regex.setSubAnchor(opt.exb.anchor);
            }
        }
        else if (opt.map.value > 0) {
            this.regex.setOptimizeMapInfo(opt.map);
            this.regex.setSubAnchor(opt.map.anchor);
        }
        else {
            final Regex regex2 = this.regex;
            regex2.subAnchor |= (opt.anchor.leftAnchor & 0x2);
            if (opt.length.max == 0) {
                final Regex regex3 = this.regex;
                regex3.subAnchor |= (opt.anchor.rightAnchor & 0x20);
            }
        }
    }
}

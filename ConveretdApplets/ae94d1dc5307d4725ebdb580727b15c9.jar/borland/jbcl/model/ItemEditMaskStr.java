// 
// Decompiled by Procyon v0.5.30
// 

package borland.jbcl.model;

import java.sql.Timestamp;
import java.sql.Time;
import java.math.BigDecimal;
import borland.jbcl.util.BooleanFormat;
import java.text.DecimalFormat;
import borland.jbcl.util.FastStringBuffer;
import java.util.ResourceBundle;
import java.util.MissingResourceException;
import java.util.Date;
import borland.jbcl.util.InvalidFormatException;
import borland.jbcl.util.Variant;
import borland.jbcl.util.TextFormat;
import java.util.Locale;
import java.util.Vector;

public class ItemEditMaskStr implements ItemEditMask, ItemEditMaskRegionChar
{
    VariantFormatter formatter;
    char blankChar;
    String editMask;
    String trueEditMask;
    Vector editRegions;
    Locale locale;
    int variantType;
    int formatterType;
    char decimalSign;
    char thousandsSign;
    int decimalAt;
    int decimalDigits;
    char replaceBlanksWith;
    char autoSkipLiteral;
    byte[] regionMap;
    int startCursor;
    boolean makeSymbolsLocal;
    int lastEditPos;
    int signPrefixOffset;
    int signSuffixOffset;
    boolean noPattern;
    boolean allowLeftShift;
    
    public ItemEditMaskStr(final String editMask, final VariantFormatter formatter, final int variantType, final Locale locale) {
        this.blankChar = '_';
        this.replaceBlanksWith = '\0';
        this.startCursor = -1;
        this.autoSkipLiteral = '\0';
        this.trueEditMask = null;
        this.makeSymbolsLocal = false;
        this.regionMap = null;
        this.decimalAt = -1;
        this.lastEditPos = 0;
        this.signPrefixOffset = -1;
        this.signSuffixOffset = -1;
        this.noPattern = false;
        this.editMask = editMask;
        this.variantType = variantType;
        this.formatterType = VariantFormatStr.formatTypeFromVariantType(variantType);
        if (editMask != null) {
            this.trueEditMask = ((this.formatterType == 4) ? editMask : VariantFormatStr.buildTrueFormatMask(editMask));
        }
        if (formatter == null) {
            this.formatter = new VariantFormatStr(this.trueEditMask, variantType, locale);
            if (this.formatter == null) {}
        }
        else {
            this.formatter = formatter;
        }
        this.locale = ((locale == null) ? this.formatter.getLocale() : locale);
        this.editMask = ((editMask == null || editMask.length() == 0) ? this.formatter.getPattern() : editMask);
        this.localizeSymbols();
        this.trueEditMask = VariantFormatStr.buildTrueFormatMask(this.editMask);
        this.variantType = ((variantType < 1) ? this.formatter.getVariantType() : variantType);
        this.formatterType = VariantFormatStr.formatTypeFromVariantType(variantType);
        this.editRegions = new Vector(0);
        if (this.editMask == null || this.editMask.length() == 0) {
            this.noPattern = true;
            this.editRegions.addElement(new ItemEditMaskRegionAny(this));
        }
        switch (this.formatterType) {
            case 1:
            case 2: {
                this.makeSymbolsLocal = true;
                this.buildNumericRegions();
                break;
            }
            case 3: {
                this.buildDateTimeRegions();
                break;
            }
            case 4: {
                Character cObj = (Character)this.formatter.getSpecialObject(2);
                char c = (cObj == null) ? '\0' : cObj;
                this.replaceBlanksWith = ((c == '\0' || c == TextFormat.NOT_A_CHAR) ? '\0' : c);
                cObj = (Character)this.formatter.getSpecialObject(1);
                c = ((cObj == null) ? '_' : cObj);
                this.blankChar = ((c == '\0' || c == TextFormat.NOT_A_CHAR) ? '_' : c);
                this.makeSymbolsLocal = true;
                this.buildTextRegions();
                break;
            }
            case 5: {
                this.buildBooleanRegions();
                break;
            }
            default: {
                return;
            }
        }
        this.buildRegionMap();
    }
    
    public ItemEditMaskStr(final String editMask, final VariantFormatter formatter, final int variantType) {
        this(editMask, formatter, variantType, null);
    }
    
    public ItemEditMaskState prepare(final Variant value) {
        if (this.startCursor < 0) {
            this.startCursor = 0;
        }
        this.allowLeftShift = false;
        final ItemEditMaskState state = this.noPattern ? new ItemEditMaskState(0, 0) : new ItemEditMaskState(this.regionMap.length, (this.startCursor < 0) ? (this.regionMap.length - 1) : this.startCursor);
        final ItemEditMaskStrData ems = new ItemEditMaskStrData(state);
        state.cursorPos = this.moveCursorOffLiteral(state.cursorPos);
        if (value == null || value.isNull()) {
            this.buildEmptyEditString(state);
        }
        else {
            this.buildVariantEditString(state, value);
        }
        return state;
    }
    
    public boolean move(final ItemEditMaskState state, final int eventCode) {
        this.allowLeftShift = false;
        return this.internalMove(state, eventCode);
    }
    
    public boolean insert(final ItemEditMaskState state, final char c) {
        if (state.cursorPos < 0) {
            state.cursorPos = 0;
        }
        if (this.decimalAt > 0 && c == this.decimalSign) {
            return this.decimalAlign(state);
        }
        if (!this.noPattern && this.allowLeftShift && state.cursorPos >= this.lastEditPos) {
            this.shiftLeft(state);
            state.cursorPos = this.lastEditPos;
        }
        else if (state.cursorPos >= this.lastEditPos && (this.formatterType == 2 || this.formatterType == 1)) {
            this.allowLeftShift = true;
        }
        if (this.handleSign(state, state.cursorPos, c, true)) {
            return true;
        }
        if (this.isValid(state.cursorPos, c)) {
            this.setCharAt(state.displayString, state.cursorPos, c);
            this.internalMove(state, 39);
            return true;
        }
        if (this.autoSkipLiteral == c) {
            this.autoSkipLiteral = '\0';
            return true;
        }
        if (this.autoSkipLiteral != c) {
            for (int i = state.cursorPos; i <= this.lastEditPos; ++i) {
                if (this.isLiteral(i)) {
                    final ItemEditMaskRegionLiteral eml = (ItemEditMaskRegionLiteral)this.getRegionFromPosition(i);
                    if (eml.capacity == 1) {
                        if (c == eml.literal.charAt(0)) {
                            state.cursorPos = i;
                            this.internalMove(state, 39);
                            return true;
                        }
                        final int ic = eml.literal.charAt(0);
                    }
                }
            }
        }
        return false;
    }
    
    public boolean delete(final ItemEditMaskState state, int startPos, int count) {
        boolean bResult = false;
        if (startPos < 0) {
            startPos = 0;
        }
        for (int pos = startPos; pos <= this.lastEditPos && count > 0; ++pos, --count) {
            if (!this.isLiteral(pos)) {
                if (!this.handleSign(state, pos, this.blankChar, false)) {
                    this.deleteCharAt(state.displayString, pos, this.blankChar);
                }
                this.setLastEditPosition(state.displayString);
                bResult = true;
            }
        }
        return bResult;
    }
    
    public boolean isComplete(final ItemEditMaskState state) {
        for (int i = 0; i < this.editRegions.size(); ++i) {
            if (!this.isRegionComplete(state, i)) {
                return false;
            }
        }
        return true;
    }
    
    public void getFinalValue(final ItemEditMaskState state, final Variant value) throws InvalidFormatException {
        this.getVariantFromString(state, value, this.variantType);
    }
    
    public void getFinalValue(final ItemEditMaskState state, final Variant value, final int variantType) throws InvalidFormatException {
        this.getVariantFromString(state, value, variantType);
    }
    
    public boolean isValid(final int charPosition, final char c) {
        if (this.noPattern) {
            return true;
        }
        if (charPosition < 0 || charPosition > this.lastEditPos) {
            return false;
        }
        final ItemEditMaskRegion emr = this.getRegionFromPosition(charPosition);
        return emr.isValid(charPosition, c);
    }
    
    public boolean isOptional(final int charPosition) {
        if (this.noPattern) {
            return true;
        }
        boolean optional = false;
        if (charPosition < 0 || charPosition >= this.lastEditPos) {
            optional = true;
        }
        else {
            final ItemEditMaskRegion emr = this.getRegionFromPosition(charPosition);
            optional = emr.isOptional(charPosition);
        }
        return optional;
    }
    
    public char setCharAt(final StringBuffer str, final int charPosition, final char c) {
        if (charPosition < 0 || charPosition > this.lastEditPos) {
            return c;
        }
        final ItemEditMaskRegion emr = this.getRegionFromPosition(charPosition);
        return emr.setCharAt(str, charPosition, c);
    }
    
    public char getCharAt(final StringBuffer str, final int charPosition) {
        final ItemEditMaskRegion emr = this.getRegionFromPosition(charPosition);
        return emr.getCharAt(str, charPosition);
    }
    
    public boolean isLiteral(final int charPosition) {
        final ItemEditMaskRegion emr = this.getRegionFromPosition(charPosition);
        return emr.isLiteral(charPosition);
    }
    
    public void deleteCharAt(final StringBuffer str, final int charPosition, final char blankChar) {
        if (charPosition < 0 || charPosition > this.lastEditPos) {
            return;
        }
        final ItemEditMaskRegion emr = this.getRegionFromPosition(charPosition);
        emr.deleteCharAt(str, charPosition, blankChar);
    }
    
    public boolean isPassword(final int charPosition) {
        final ItemEditMaskRegion emr = this.getRegionFromPosition(charPosition);
        if (emr instanceof ItemEditMaskRegionText) {
            final ItemEditMaskRegionText emt = (ItemEditMaskRegionText)emr;
            return emt.password;
        }
        return false;
    }
    
    public char literalAt(final int charPosition) {
        final ItemEditMaskRegion emr = this.getRegionFromPosition(charPosition);
        if (emr instanceof ItemEditMaskRegionLiteral) {
            return ((ItemEditMaskRegionLiteral)emr).literal.charAt(charPosition - emr.offset);
        }
        return this.blankChar;
    }
    
    private ItemEditMaskRegion getRegionFromPosition(final int charPosition) {
        final int pos = this.noPattern ? 0 : this.regionMap[charPosition];
        return this.editRegions.elementAt(pos);
    }
    
    private int getBufferLength(final StringBuffer editString) {
        return this.noPattern ? editString.length() : this.regionMap.length;
    }
    
    int setLastEditPosition(final StringBuffer editString) {
        if (this.noPattern) {
            this.lastEditPos = editString.length();
        }
        return this.lastEditPos;
    }
    
    private boolean handleSign(final ItemEditMaskState state, final int charPos, final char c, final boolean inserting) {
        final ItemEditMaskRegion emr = this.getRegionFromPosition(charPos);
        if (!(emr instanceof ItemEditMaskRegionSign)) {
            return false;
        }
        if (!this.isValid(charPos, c)) {
            if (inserting) {
                this.internalMove(state, 39);
            }
            return false;
        }
        this.setCharAt(state.displayString, charPos, c);
        for (int len = this.getBufferLength(state.displayString), i = 0; i < len; ++i) {
            final ItemEditMaskRegion otherEmr = this.getRegionFromPosition(i);
            if (otherEmr != emr) {
                if (otherEmr instanceof ItemEditMaskRegionSign) {
                    final ItemEditMaskRegionSign ems = (ItemEditMaskRegionSign)otherEmr;
                    this.setCharAt(state.displayString, i, c);
                    break;
                }
            }
        }
        if (inserting) {
            this.internalMove(state, 39);
        }
        return true;
    }
    
    private final String DateTimeFormat(final Date dateVal, final String pattern) {
        this.formatter.setPattern(pattern);
        final Variant v = new Variant();
        switch (this.variantType) {
            case 14: {
                v.setTime(dateVal.getTime());
                break;
            }
            case 15: {
                v.setTimestamp(dateVal.getTime());
                break;
            }
            default: {
                v.setDate(new java.sql.Date(dateVal.getTime()));
                break;
            }
        }
        final String s = this.formatter.format(v);
        return s;
    }
    
    private final boolean DateTimeParse(final String dateString, final String pattern, final Variant value, final int variantType) {
        this.formatter.setPattern(pattern);
        try {
            this.formatter.parse(dateString, value, variantType);
        }
        catch (InvalidFormatException e) {
            return false;
        }
        return true;
    }
    
    private final String NumericFormat(final double doubleValue, final String pattern) {
        this.formatter.setPattern(pattern);
        final Variant v = new Variant();
        v.setDouble(doubleValue);
        return this.formatter.format(v);
    }
    
    private final Number NumericParse(final String numString, final String pattern, final ItemEditMaskState state) {
        Variant v = new Variant();
        v.setDouble(0.0);
        this.formatter.setPattern(pattern);
        try {
            this.formatter.parse(numString, v, 7);
        }
        catch (InvalidFormatException e) {
            v = null;
        }
        if (v != null) {
            return new Double(v.getDouble());
        }
        return null;
    }
    
    private final String TextFormat(final String text, final char fillChar) {
        Character oldFill = null;
        if (fillChar != '\uffff') {
            oldFill = (Character)this.formatter.setSpecialObject(1, new Character(fillChar));
        }
        final Variant v = new Variant();
        v.setString(text);
        final String s = this.formatter.format(v);
        if (fillChar != '\uffff') {
            this.formatter.setSpecialObject(1, oldFill);
        }
        return s;
    }
    
    private final String TextParse(final String textStr, final char fillChar, final char replaceChar) {
        Object oldFill = null;
        Object oldRepl = null;
        Variant v = new Variant();
        if (fillChar != '\uffff') {
            oldFill = this.formatter.setSpecialObject(1, new Character(fillChar));
        }
        if (replaceChar != '\uffff') {
            oldRepl = this.formatter.setSpecialObject(2, new Character(replaceChar));
        }
        try {
            this.formatter.parse(textStr, v, 16);
        }
        catch (InvalidFormatException e) {
            v = null;
        }
        if (fillChar != '\uffff') {
            this.formatter.setSpecialObject(1, oldFill);
        }
        if (replaceChar != '\uffff') {
            this.formatter.setSpecialObject(2, oldRepl);
        }
        return (v == null) ? null : v.getString();
    }
    
    private final String BooleanFormat(final Boolean bool) {
        try {
            final Variant v = new Variant();
            if (bool != null) {
                v.setBoolean(bool);
            }
            else {
                v.setAssignedNull();
            }
            final String s = this.formatter.format(v);
            return s;
        }
        catch (Exception ex) {
            ex.printStackTrace(System.err);
            return null;
        }
    }
    
    private final Boolean BooleanParse(final String text) throws InvalidFormatException {
        final Variant v = new Variant();
        this.formatter.parse(text, v, 11);
        return (v == null || v.isNull()) ? null : new Boolean(v.getBoolean());
    }
    
    private boolean isRegionComplete(final ItemEditMaskState state, final int nRegion) {
        final StringBuffer s = state.displayString;
        final ItemEditMaskRegion emr = this.editRegions.elementAt(nRegion);
        if (emr instanceof ItemEditMaskRegionLiteral) {
            return true;
        }
        if (emr.optional) {
            return true;
        }
        int nBlanks = 0;
        for (int i = emr.offset; i < emr.offset + emr.capacity; ++i) {
            if (s.charAt(i) == this.blankChar) {
                ++nBlanks;
                if (!emr.isOptional(i)) {
                    if (emr.minRequired == 0) {
                        state.cursorPos = i;
                        return false;
                    }
                }
            }
        }
        if (nBlanks == 0) {
            return true;
        }
        if (emr.capacity - nBlanks >= emr.minRequired) {
            return true;
        }
        if (emr.rightToLeft) {
            state.cursorPos = emr.offset + emr.capacity - emr.minRequired;
        }
        else {
            state.cursorPos = emr.offset + (emr.minRequired - nBlanks);
        }
        return false;
    }
    
    protected boolean shiftLeft(final ItemEditMaskState state) {
        final StringBuffer s = state.displayString;
        final int iLen = s.length();
        if (this.noPattern) {
            return false;
        }
        final short[] changeMap = new short[iLen];
        for (int i = 0; i < iLen; ++i) {
            changeMap[i] = 0;
        }
        int pos = state.cursorPos;
        boolean anyChanges = false;
        if (pos > this.lastEditPos) {
            pos = this.lastEditPos;
        }
        while (pos > 0) {
            int posSrc;
            for (posSrc = pos; posSrc > 0 && this.isLiteral(posSrc); --posSrc) {}
            if (posSrc <= 0) {
                break;
            }
            if (s.charAt(posSrc) == this.blankChar) {
                break;
            }
            int posDst;
            for (posDst = posSrc - 1; posDst >= 0 && this.isLiteral(posDst); --posDst) {}
            if (posDst < 0) {
                break;
            }
            if (!this.isValid(posDst, this.getCharAt(s, posSrc))) {
                return false;
            }
            changeMap[posDst] = (short)posSrc;
            anyChanges = true;
            pos = posSrc;
            --pos;
        }
        if (anyChanges) {
            for (int j = 0; j < iLen; ++j) {
                if (changeMap[j] != 0) {
                    final char c = this.getCharAt(s, changeMap[j]);
                    this.setCharAt(state.displayString, j, c);
                }
            }
        }
        pos = state.cursorPos;
        if (pos > this.lastEditPos) {
            pos = this.lastEditPos;
        }
        s.setCharAt(pos, this.blankChar);
        return anyChanges;
    }
    
    private final void localizeSymbols() {
        try {
            final ResourceBundle resource = SystemResourceBundle.getBundle("java.text.resources.LocaleElements", this.locale);
            final String[] numberElements = resource.getStringArray("NumberElements");
            this.decimalSign = numberElements[0].charAt(0);
            this.thousandsSign = numberElements[1].charAt(0);
        }
        catch (MissingResourceException e) {
            this.decimalSign = '.';
            this.thousandsSign = ',';
        }
    }
    
    private final int moveCursorOffLiteral(int cursorPos) {
        final int iLen = this.lastEditPos;
        if (cursorPos >= iLen) {
            cursorPos = iLen;
        }
        if (cursorPos < 0) {
            cursorPos = 0;
        }
        if (!this.isLiteral(cursorPos)) {
            return cursorPos;
        }
        for (int forwardPos = cursorPos + 1, backwardPos = cursorPos - 1; forwardPos < iLen || backwardPos >= 0; ++forwardPos, --backwardPos) {
            if (forwardPos < iLen && !this.isLiteral(forwardPos)) {
                return forwardPos;
            }
            if (backwardPos > 0 && !this.isLiteral(backwardPos)) {
                return backwardPos;
            }
        }
        return cursorPos;
    }
    
    private final int charPositionsToHere(int iEditRegion) {
        int nChars = 0;
        if (iEditRegion >= this.editRegions.size()) {
            iEditRegion = this.editRegions.size() - 1;
        }
        if (iEditRegion >= 0) {
            final ItemEditMaskRegion emr = this.editRegions.elementAt(iEditRegion);
            nChars = emr.offset + emr.capacity;
        }
        return nChars;
    }
    
    private final void buildDateTimeRegions() {
        final FastStringBuffer editChars = new FastStringBuffer(this.editMask);
        final FastStringBuffer workBuffer = new FastStringBuffer(editChars.length());
        boolean optional = false;
        int charCount = 0;
        int litCount = 0;
        for (char c = editChars.firstChar(); c != '\0'; c = editChars.nextChar()) {
            switch (c) {
                case '{': {
                    optional = true;
                    break;
                }
                case '}': {
                    optional = false;
                    break;
                }
                case '^': {
                    this.startCursor = this.charPositionsToHere(this.editRegions.size()) + litCount - 1;
                    break;
                }
                case 'D':
                case 'E':
                case 'F':
                case 'G':
                case 'H':
                case 'K':
                case 'M':
                case 'N':
                case 'S':
                case 'W':
                case 'a':
                case 'd':
                case 'h':
                case 'k':
                case 'm':
                case 's':
                case 'w':
                case 'y':
                case 'z': {
                    if (litCount > 0) {
                        this.editRegions.addElement(new ItemEditMaskRegionLiteral(this, workBuffer));
                        litCount = 0;
                        workBuffer.empty();
                    }
                    charCount = 1;
                    char nextC;
                    while ((nextC = editChars.nextChar()) == c) {
                        ++charCount;
                    }
                    if (nextC != '\0') {
                        editChars.priorChar();
                    }
                    switch (c) {
                        case 'G': {
                            this.editRegions.addElement(new ItemEditMaskRegionERA(this, c, optional));
                            break;
                        }
                        case 'y': {
                            this.editRegions.addElement(new ItemEditMaskRegionYear(this, c, charCount, optional));
                            break;
                        }
                        case 'N': {
                            this.editRegions.addElement(new ItemEditMaskRegionMonthInYear(this, c, charCount, optional));
                            break;
                        }
                        case 'd': {
                            this.editRegions.addElement(new ItemEditMaskRegionDayInMonth(this, c, charCount, optional));
                            break;
                        }
                        case 'h': {
                            this.editRegions.addElement(new ItemEditMaskRegionHour12(this, c, charCount, optional));
                            break;
                        }
                        case 'H': {
                            this.editRegions.addElement(new ItemEditMaskRegionHour23(this, c, charCount, optional));
                            break;
                        }
                        case 'm': {
                            this.editRegions.addElement(new ItemEditMaskRegionMinute(this, c, charCount, optional));
                            break;
                        }
                        case 'M': {
                            this.editRegions.addElement(new ItemEditMaskRegionMonthInYear(this, c, charCount, optional));
                            break;
                        }
                        case 's': {
                            this.editRegions.addElement(new ItemEditMaskRegionSecond(this, c, charCount, optional));
                            break;
                        }
                        case 'S': {
                            this.editRegions.addElement(new ItemEditMaskRegionMillisecond(this, c, charCount, optional));
                            break;
                        }
                        case 'E': {
                            this.editRegions.addElement(new ItemEditMaskRegionDayInWeek(this, c, charCount, true));
                            break;
                        }
                        case 'D': {
                            this.editRegions.addElement(new ItemEditMaskRegionDayInYear(this, c, charCount, optional));
                            break;
                        }
                        case 'F': {
                            this.editRegions.addElement(new ItemEditMaskRegionWeekInMonth(this, c, charCount, optional));
                            break;
                        }
                        case 'w': {
                            this.editRegions.addElement(new ItemEditMaskRegionWeekInYear(this, c, charCount, optional));
                            break;
                        }
                        case 'W': {
                            this.editRegions.addElement(new ItemEditMaskRegionWeekInMonth(this, c, charCount, optional));
                            break;
                        }
                        case 'a': {
                            this.editRegions.addElement(new ItemEditMaskRegionAMPM(this, c, optional));
                            break;
                        }
                        case 'k': {
                            this.editRegions.addElement(new ItemEditMaskRegionHour24(this, c, charCount, optional));
                            break;
                        }
                        case 'K': {
                            this.editRegions.addElement(new ItemEditMaskRegionHour11(this, c, charCount, optional));
                            break;
                        }
                        case 'z': {
                            this.editRegions.addElement(new ItemEditMaskRegionTimeZone(this, c, charCount, optional));
                            break;
                        }
                    }
                    break;
                }
                case '\'': {
                    workBuffer.empty();
                    final FastStringBuffer fsb = editChars.parseLiteral(c, true);
                    workBuffer.append(fsb.value(), 0, fsb.length());
                    this.editRegions.addElement(new ItemEditMaskRegionLiteral(this, workBuffer));
                    workBuffer.empty();
                    litCount = 0;
                    break;
                }
                case '\\': {
                    workBuffer.append(editChars.parseBackSlash());
                    ++litCount;
                    break;
                }
                default: {
                    workBuffer.append(c);
                    ++litCount;
                    break;
                }
            }
        }
        if (litCount > 0) {
            this.editRegions.addElement(new ItemEditMaskRegionLiteral(this, workBuffer));
        }
    }
    
    private final void buildNumericRegions() {
        final FastStringBuffer editChars = new FastStringBuffer(this.editMask);
        final FastStringBuffer workBuffer = new FastStringBuffer(editChars.length());
        int charCount = 0;
        int litCount = 0;
        this.decimalDigits = 0;
        this.decimalAt = -1;
        final DecimalFormat df = (DecimalFormat)this.formatter.getFormatObj();
        String s = df.getNegativePrefix();
        if (df != null && s.length() > 0) {
            this.signPrefixOffset = 0;
            this.editRegions.addElement(new ItemEditMaskRegionSign(this, s.charAt(0), true, this.blankChar));
        }
        for (char c = editChars.firstChar(); c != '\0'; c = editChars.nextChar()) {
            switch (c) {
                case ';': {
                    editChars.lastChar();
                    break;
                }
                case '^': {
                    this.startCursor = this.charPositionsToHere(this.editRegions.size()) + charCount + litCount - 1;
                    break;
                }
                case '#':
                case '0':
                case '{':
                case '}': {
                    if (litCount > 0) {
                        this.editRegions.addElement(new ItemEditMaskRegionLiteral(this, workBuffer));
                        litCount = 0;
                        workBuffer.empty();
                    }
                    workBuffer.append(c);
                    ++charCount;
                    break;
                }
                default: {
                    if (charCount > 0) {
                        this.editRegions.addElement(new ItemEditMaskRegionNumeric(this, workBuffer));
                        workBuffer.empty();
                        if (this.decimalAt >= 0) {
                            this.decimalDigits += charCount;
                        }
                        charCount = 0;
                    }
                    c = this.localizeChar(c, true);
                    if (c == this.decimalSign) {
                        this.decimalAt = this.charPositionsToHere(this.editRegions.size()) + charCount + litCount;
                    }
                    workBuffer.append(c);
                    ++litCount;
                    break;
                }
            }
        }
        if (litCount > 0) {
            this.editRegions.addElement(new ItemEditMaskRegionLiteral(this, workBuffer));
        }
        if (charCount > 0) {
            this.editRegions.addElement(new ItemEditMaskRegionNumeric(this, workBuffer));
        }
        if (this.decimalAt >= 0) {
            this.decimalDigits += charCount;
        }
        s = df.getNegativeSuffix();
        if (df != null && s.length() > 0 && s.length() > litCount) {
            this.signSuffixOffset = this.editRegions.size();
            this.editRegions.addElement(new ItemEditMaskRegionSign(this, s.charAt(s.length() - 1), false, this.blankChar));
            goto Label_0517;
        }
    }
    
    private final boolean internalMove(final ItemEditMaskState state, final int keyCode) {
        this.setLastEditPosition(state.displayString);
        int pos = state.cursorPos;
        int incr = 1;
        this.autoSkipLiteral = '\0';
        switch (keyCode) {
            case 36: {
                pos = 0;
                break;
            }
            case 35: {
                pos = this.lastEditPos;
                incr = -1;
                break;
            }
            case 37: {
                incr = -1;
            }
            case 39: {
                pos += incr;
                break;
            }
            case 500:
            case 501: {
                break;
            }
            default: {
                return false;
            }
        }
        if (pos > this.lastEditPos) {
            pos = this.lastEditPos;
        }
        while (pos >= 0 && pos <= this.lastEditPos) {
            if (pos >= this.lastEditPos || !this.isLiteral(pos)) {
                state.cursorPos = pos;
                return true;
            }
            final ItemEditMaskRegionLiteral emr = (ItemEditMaskRegionLiteral)this.getRegionFromPosition(pos);
            if (emr != null && emr.capacity == 1) {
                this.autoSkipLiteral = emr.literal.charAt(0);
            }
            else {
                this.autoSkipLiteral = '\0';
            }
            pos += incr;
        }
        if (keyCode == 500 || keyCode == 501) {
            pos = state.cursorPos;
            incr = -1;
            if (pos > this.lastEditPos) {
                pos = this.lastEditPos;
            }
            while (pos >= 0 && pos <= this.lastEditPos) {
                if (pos >= this.lastEditPos || !this.isLiteral(pos)) {
                    state.cursorPos = pos;
                    this.autoSkipLiteral = '\0';
                    return true;
                }
                pos += incr;
            }
        }
        return false;
    }
    
    private final void buildTextRegions() {
        final FastStringBuffer editChars = new FastStringBuffer(this.editMask);
        final FastStringBuffer workBuffer = new FastStringBuffer(editChars.length());
        int charCount = 0;
        int litCount = 0;
        boolean password = false;
        int caseConvert = 0;
        boolean optional = false;
        for (char c = editChars.firstChar(); c != '\0' && c != ';'; c = editChars.nextChar()) {
            switch (c) {
                case '{':
                case '}': {
                    if (litCount > 0) {
                        this.editRegions.addElement(new ItemEditMaskRegionLiteral(this, workBuffer));
                    }
                    if (charCount > 0) {
                        this.editRegions.addElement(new ItemEditMaskRegionText(this, workBuffer, caseConvert, password, optional));
                    }
                    optional = (c == '{');
                    litCount = 0;
                    charCount = 0;
                    workBuffer.empty();
                    break;
                }
                case '!':
                case '*':
                case '<':
                case '>':
                case '^': {
                    if (litCount > 0) {
                        this.editRegions.addElement(new ItemEditMaskRegionLiteral(this, workBuffer));
                        litCount = 0;
                    }
                    if (charCount > 0) {
                        this.editRegions.addElement(new ItemEditMaskRegionText(this, workBuffer, caseConvert, password, optional));
                        charCount = 0;
                    }
                    workBuffer.empty();
                    switch (c) {
                        case '^': {
                            this.startCursor = this.charPositionsToHere(this.editRegions.size()) + litCount + charCount - 1;
                            break;
                        }
                        case '<': {
                            caseConvert = -1;
                            break;
                        }
                        case '>': {
                            caseConvert = 1;
                        }
                        case '*': {
                            password = !password;
                            break;
                        }
                    }
                    break;
                }
                case '#':
                case '&':
                case '0':
                case '9':
                case '?':
                case 'A':
                case 'C':
                case 'L':
                case 'a':
                case 'c':
                case 'l': {
                    if (litCount > 0) {
                        this.editRegions.addElement(new ItemEditMaskRegionLiteral(this, workBuffer));
                        litCount = 0;
                        workBuffer.empty();
                    }
                    workBuffer.append(c);
                    ++charCount;
                    break;
                }
                default: {
                    if (charCount > 0) {
                        this.editRegions.addElement(new ItemEditMaskRegionText(this, workBuffer, caseConvert, password, optional));
                        workBuffer.empty();
                        charCount = 0;
                    }
                    switch (c) {
                        case '\\': {
                            workBuffer.append(editChars.parseBackSlash());
                            ++litCount;
                            continue;
                        }
                        case '\'': {
                            final FastStringBuffer litBuffer = editChars.parseLiteral(c, true);
                            workBuffer.append(litBuffer.value(), 0, litBuffer.length());
                            litCount += litBuffer.length();
                            continue;
                        }
                        default: {
                            c = this.localizeChar(c, true);
                            workBuffer.append(c);
                            ++litCount;
                            continue;
                        }
                    }
                    break;
                }
            }
        }
        if (litCount > 0) {
            this.editRegions.addElement(new ItemEditMaskRegionLiteral(this, workBuffer));
        }
        if (charCount > 0) {
            this.editRegions.addElement(new ItemEditMaskRegionText(this, workBuffer, caseConvert, password, optional));
        }
    }
    
    void buildBooleanRegions() {
        final BooleanFormat bf = (BooleanFormat)this.formatter.getFormatObj();
        int len1 = bf.getTrueString().length();
        final int len2 = bf.getFalseString().length();
        final int len3 = bf.getNullString().length();
        if (len2 > len1) {
            len1 = len2;
        }
        if (len3 > len1) {
            len1 = len3;
        }
        this.editRegions.addElement(new ItemEditMaskRegionAny(this, len1, true));
    }
    
    private final char[] buildEmptyEditCharArray() {
        final int buflen = this.noPattern ? 0 : this.regionMap.length;
        final char[] buffer = new char[buflen];
        for (int fillPos = 0, i = 0; i < buflen; i = fillPos - 1, ++i) {
            final ItemEditMaskRegion emr = this.getRegionFromPosition(i);
            if (emr.isLiteral(fillPos)) {
                final ItemEditMaskRegionLiteral eml = (ItemEditMaskRegionLiteral)emr;
                final int j = eml.capacity;
                eml.literal.getChars(0, j, buffer, fillPos);
                fillPos += j;
            }
            else {
                for (int j = 0; j < emr.capacity; ++j) {
                    buffer[fillPos++] = this.blankChar;
                }
            }
        }
        return buffer;
    }
    
    private final void buildEmptyEditString(final ItemEditMaskState state) {
        final char[] buffer = this.buildEmptyEditCharArray();
        state.displayString.setLength(0);
        state.displayString.append(buffer);
    }
    
    private final char[] buildDateTimeEditString(final Date dateValue) {
        final char[] buffer = this.buildEmptyEditCharArray();
        ItemEditMaskRegion emr;
        for (int i = 0; i < this.regionMap.length; i += emr.capacity - 1, ++i) {
            emr = this.getRegionFromPosition(i);
            if (!(emr instanceof ItemEditMaskRegionLiteral)) {
                final FastStringBuffer fs = new FastStringBuffer(emr.c, Math.max(emr.capacity, emr.charCount));
                final String str = this.DateTimeFormat(dateValue, fs.toString());
                int iLen = str.length();
                iLen = Math.min(iLen, emr.capacity);
                str.getChars(0, iLen, buffer, emr.rightToLeft ? (i + emr.capacity - iLen) : i);
            }
        }
        return buffer;
    }
    
    private void buildNumericEditString(final ItemEditMaskState state, final double doubleValue) {
        final boolean negative = doubleValue < 0.0;
        final double absValue = negative ? (-doubleValue) : doubleValue;
        String s = this.NumericFormat(absValue, this.trueEditMask);
        if (this.decimalAt >= 0 && s.indexOf(this.decimalSign) < 0) {
            s = String.valueOf(s).concat(String.valueOf(String.valueOf(this.decimalSign)));
        }
        final FastStringBuffer editString = new FastStringBuffer(this.blankChar, this.regionMap.length);
        int origDecimalAt = this.decimalAt;
        if (origDecimalAt < 0) {
            for (origDecimalAt = this.regionMap.length - 1; origDecimalAt >= 0 && !(this.getRegionFromPosition(origDecimalAt) instanceof ItemEditMaskRegionNumeric); --origDecimalAt) {}
        }
        int thisDecimalAt = s.indexOf(this.decimalSign);
        if (thisDecimalAt < 0) {
            for (thisDecimalAt = s.length() - 1; thisDecimalAt >= 0 && !Character.isDigit(s.charAt(thisDecimalAt)); --thisDecimalAt) {}
        }
        int i = thisDecimalAt;
        for (int j = origDecimalAt; j >= 0; --j) {
            final ItemEditMaskRegion emr = this.getRegionFromPosition(j);
            if (emr instanceof ItemEditMaskRegionNumeric) {
                editString.setCharAt(j, (i >= 0 && Character.isDigit(s.charAt(i))) ? s.charAt(i) : this.blankChar);
            }
            else if (emr instanceof ItemEditMaskRegionSign) {
                editString.setCharAt(j, negative ? ((ItemEditMaskRegionSign)emr).c : this.blankChar);
            }
            else if (emr.isLiteral(j)) {
                editString.setCharAt(j, this.literalAt(j));
            }
            --i;
        }
        int k = thisDecimalAt + 1;
        for (int l = origDecimalAt + 1; l < editString.length(); ++l) {
            final ItemEditMaskRegion emr2 = this.getRegionFromPosition(l);
            if (emr2 instanceof ItemEditMaskRegionNumeric) {
                editString.setCharAt(l, (k < s.length() && Character.isDigit(s.charAt(k))) ? s.charAt(k) : this.blankChar);
            }
            else if (emr2 instanceof ItemEditMaskRegionSign) {
                editString.setCharAt(l, negative ? ((ItemEditMaskRegionSign)emr2).c : this.blankChar);
            }
            else if (emr2.isLiteral(l)) {
                editString.setCharAt(l, this.literalAt(l));
            }
            ++k;
        }
        state.displayString.append(editString.toString());
    }
    
    private final void buildVariantEditString(final ItemEditMaskState state, final Variant value) {
        final int inputVariantType = value.getType();
        state.displayString.setLength(0);
        switch (this.formatterType) {
            case 1:
            case 2: {
                double doubleValue = 0.0;
                switch (inputVariantType) {
                    case 6:
                    case 7: {
                        doubleValue = value.getAsDouble();
                        break;
                    }
                    case 2:
                    case 3:
                    case 4: {
                        final Integer intObj = new Integer(value.getAsInt());
                        doubleValue = intObj;
                        break;
                    }
                    case 5: {
                        final Long longObj = new Long((int)value.getLong());
                        doubleValue = longObj;
                        break;
                    }
                    case 10: {
                        final BigDecimal bigDecimalObj = value.getBigDecimal();
                        doubleValue = bigDecimalObj.doubleValue();
                        break;
                    }
                    default: {
                        this.buildEmptyEditString(state);
                        return;
                    }
                }
                this.buildNumericEditString(state, doubleValue);
                break;
            }
            case 3: {
                Date dateValue = null;
                switch (inputVariantType) {
                    case 5: {
                        dateValue = new Date(value.getLong());
                        break;
                    }
                    case 13: {
                        dateValue = value.getDate();
                        break;
                    }
                    case 14: {
                        final Time timeObj = value.getTime();
                        dateValue = new Date(timeObj.getTime());
                        break;
                    }
                    case 15: {
                        final Timestamp timestampObj = value.getTimestamp();
                        dateValue = new Date(timestampObj.getTime());
                        break;
                    }
                    default: {
                        this.buildEmptyEditString(state);
                        return;
                    }
                }
                final char[] buffer = this.buildDateTimeEditString(dateValue);
                state.displayString.setLength(0);
                state.displayString.append(buffer);
                break;
            }
            case 4: {
                final String resultStr = value.isNull() ? new String("") : value.toString();
                final String res = this.TextFormat(resultStr, this.blankChar);
                state.displayString.append(res);
                while (state.displayString.length() < this.getBufferLength(state.displayString)) {
                    state.displayString.append(this.blankChar);
                }
                break;
            }
            case 5: {
                final String resultStr = this.BooleanFormat(new Boolean(value.getBoolean()));
                state.displayString.append(resultStr);
                int iLen = this.regionMap.length - resultStr.length();
                while (iLen-- > 0) {
                    state.displayString.append(this.blankChar);
                }
                break;
            }
        }
    }
    
    private char localizeChar(char c, final boolean toLocale) {
        if (!this.makeSymbolsLocal) {
            return c;
        }
        if (toLocale) {
            if (c == '.') {
                c = this.decimalSign;
            }
            else if (c == ',') {
                c = this.thousandsSign;
            }
        }
        else if (c == this.decimalSign) {
            c = '.';
        }
        else if (c == this.thousandsSign) {
            c = ',';
        }
        return c;
    }
    
    private final FastStringBuffer getFinalEditString(final ItemEditMaskState state) {
        final StringBuffer s = state.displayString;
        final int iLen = s.length();
        final FastStringBuffer fb = new FastStringBuffer(iLen);
        for (int i = 0; i < iLen; ++i) {
            final char c = this.getCharAt(s, i);
            fb.append(c);
        }
        return fb;
    }
    
    private final FastStringBuffer stripBlanksAndLiterals(final ItemEditMaskState state) {
        final FastStringBuffer dst = this.getFinalEditString(state);
        final char[] value = dst.value();
        final int iLen = value.length;
        for (char c = dst.firstChar(); c != '\0'; c = dst.nextChar()) {
            if (c == this.blankChar) {
                dst.removeChar();
            }
        }
        return dst;
    }
    
    private boolean isEmptyString(final ItemEditMaskState state) {
        for (int pos = 0; pos <= this.lastEditPos; ++pos) {
            if (!this.isLiteral(pos) && this.getCharAt(state.displayString, pos) != this.blankChar) {
                return false;
            }
        }
        return true;
    }
    
    private final void getVariantFromString(final ItemEditMaskState state, final Variant value, int variantType) throws InvalidFormatException {
        if (variantType <= 1) {
            variantType = this.variantType;
        }
        if (!this.isComplete(state)) {
            throw new InvalidFormatException(Res.getString(18), state.cursorPos);
        }
        if (state.displayString.length() == 0 || this.isEmptyString(state)) {
            value.setNull(1);
            return;
        }
        switch (this.formatterType) {
            case 1:
            case 2: {
                final FastStringBuffer str = this.stripBlanksAndLiterals(state);
                final Number resultNum = this.NumericParse(str.toString(), this.trueEditMask, state);
                if (resultNum == null) {
                    throw new InvalidFormatException(Res.getString(18), state.cursorPos);
                }
                VariantFormatStr.doubleToVariant(resultNum.doubleValue(), value, variantType, this.formatter.getScale());
                break;
            }
            case 3: {
                final FastStringBuffer str = this.stripBlanksAndLiterals(state);
                final boolean success = this.DateTimeParse(str.toString(), this.trueEditMask, value, variantType);
                if (!success) {
                    throw new InvalidFormatException(Res.getString(18), state.cursorPos);
                }
                break;
            }
            case 4: {
                final String s = this.getFinalEditString(state).toString();
                final String resultStr = this.TextParse(s, this.blankChar, this.replaceBlanksWith);
                VariantFormatStr.stringToVariant(resultStr, value, variantType, this.formatter.getScale());
                break;
            }
            case 5: {
                final String s = this.getFinalEditString(state).toString();
                final Boolean bool = this.BooleanParse(s);
                VariantFormatStr.booleanToVariant(bool, value, variantType);
                break;
            }
            default: {
                value.setNull(1);
                break;
            }
        }
    }
    
    private void buildRegionMap() {
        if (this.noPattern) {
            return;
        }
        int capacity = 0;
        int fillPos = 0;
        for (int i = 0; i < this.editRegions.size(); ++i) {
            final ItemEditMaskRegion emr = this.editRegions.elementAt(i);
            capacity += emr.capacity;
        }
        this.regionMap = new byte[capacity];
        for (int i = 0; i < this.editRegions.size(); ++i) {
            final ItemEditMaskRegion emr = this.editRegions.elementAt(i);
            if (emr.capacity != 0) {
                for (int j = 0; j < emr.capacity; ++j) {
                    this.regionMap[fillPos++] = (byte)i;
                }
            }
        }
        this.lastEditPos = this.regionMap.length - 1;
        while (this.lastEditPos > 0 && this.isLiteral(this.lastEditPos)) {
            --this.lastEditPos;
        }
    }
    
    private final boolean decimalAlign(final ItemEditMaskState state) {
        final StringBuffer s = state.displayString;
        final int iLen = s.length();
        boolean anyChanges = false;
        if (state.cursorPos == this.decimalAt + 1) {
            return false;
        }
        final short[] changeMap = new short[iLen];
        final FastStringBuffer fsb = new FastStringBuffer(s.toString());
        for (int i = 0; i < iLen; ++i) {
            changeMap[i] = 0;
        }
        int posSrc;
        for (posSrc = ((state.cursorPos == this.lastEditPos) ? this.lastEditPos : (state.cursorPos - 1)); posSrc > 0 && this.isLiteral(posSrc); --posSrc) {}
        int posDst;
        for (posDst = this.decimalAt - 1; posSrc >= 0 && posDst >= 0; --posSrc, --posDst) {
            if (s.charAt(posSrc) == this.blankChar) {
                break;
            }
            if (posSrc == this.decimalAt) {
                ++posDst;
            }
            else {
                ItemEditMaskRegion emn = this.getRegionFromPosition(posSrc);
                final boolean srcIsNumeric = emn instanceof ItemEditMaskRegionNumeric;
                emn = this.getRegionFromPosition(posDst);
                final boolean dstIsNumeric = emn instanceof ItemEditMaskRegionNumeric;
                if (!srcIsNumeric) {
                    if (dstIsNumeric) {
                        ++posDst;
                    }
                }
                else if (!dstIsNumeric) {
                    ++posSrc;
                }
                else {
                    if (!this.isValid(posDst, this.getCharAt(s, posSrc))) {
                        break;
                    }
                    changeMap[posDst] = (short)posSrc;
                    anyChanges = true;
                }
            }
        }
        if (anyChanges) {
            for (int i = 0; i < iLen; ++i) {
                if ((i >= posSrc && i < this.decimalAt) || (i > this.decimalAt && i <= posSrc)) {
                    final ItemEditMaskRegion emn = this.getRegionFromPosition(i);
                    if (emn instanceof ItemEditMaskRegionNumeric) {
                        this.setCharAt(state.displayString, i, this.blankChar);
                    }
                }
                if (changeMap[i] != 0) {
                    final char c = fsb.charAt(changeMap[i]);
                    this.setCharAt(state.displayString, i, c);
                    if ((posSrc < posDst && changeMap[i] <= posDst) || (posSrc > posDst && changeMap[i] >= posSrc)) {
                        this.setCharAt(state.displayString, changeMap[i], this.blankChar);
                    }
                }
            }
        }
        state.cursorPos = this.decimalAt;
        anyChanges |= this.internalMove(state, 39);
        return anyChanges;
    }
}

// 
// Decompiled by Procyon v0.5.30
// 

package borland.jbcl.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.sql.Time;
import java.util.ResourceBundle;
import java.util.MissingResourceException;
import java.text.MessageFormat;
import java.text.Format;
import borland.jbcl.util.FastStringBuffer;
import borland.jbcl.util.InvalidFormatException;
import java.util.Date;
import borland.jbcl.util.Variant;
import java.util.TimeZone;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.util.Locale;
import borland.jbcl.util.BooleanFormat;
import borland.jbcl.util.TextFormat;
import java.text.SimpleDateFormat;
import java.text.DecimalFormat;
import java.text.FieldPosition;
import java.text.ParsePosition;

public class VariantFormatStr extends VariantFormatter
{
    String pattern;
    ParsePosition parsePos;
    FieldPosition fieldPos;
    DecimalFormat decimalFormat;
    SimpleDateFormat timeFormat;
    TextFormat textFormat;
    BooleanFormat booleanFormat;
    Locale locale;
    int variantType;
    int formatterType;
    int scale;
    int precision;
    boolean isCurrency;
    private static boolean BUG6296;
    private static boolean BUG5595;
    
    public VariantFormatStr(final String pattern, int variantType, final Locale locale, final int scale, final int precision, final boolean isCurrency) {
        this.scale = scale;
        this.precision = precision;
        this.isCurrency = isCurrency;
        if (variantType <= 1) {
            variantType = 16;
        }
        this.variantType = variantType;
        this.formatterType = formatTypeFromVariantType(variantType);
        if (locale == null) {
            this.locale = Locale.getDefault();
        }
        else {
            this.locale = locale;
        }
        this.parsePos = new ParsePosition(0);
        this.decimalFormat = null;
        this.timeFormat = null;
        this.textFormat = null;
        this.booleanFormat = null;
        this.pattern = ((pattern == null || pattern.length() == 0) ? this.getDefaultPattern(this.variantType) : ((this.formatterType == 4) ? pattern : buildTrueFormatMask(pattern)));
        switch (this.formatterType) {
            case 1:
            case 2: {
                (this.decimalFormat = (DecimalFormat)NumberFormat.getNumberInstance(this.locale)).applyPattern(this.pattern);
                break;
            }
            case 3: {
                (this.timeFormat = (SimpleDateFormat)DateFormat.getDateTimeInstance(2, 2, this.locale)).setTimeZone(TimeZone.getDefault());
                this.timeFormat.applyPattern(this.pattern);
                break;
            }
            case 5: {
                this.booleanFormat = new BooleanFormat(this.pattern);
                break;
            }
            default: {
                this.textFormat = new TextFormat(this.pattern);
                break;
            }
        }
    }
    
    public VariantFormatStr(final String pattern, final int variantType) {
        this(pattern, variantType, null);
    }
    
    public VariantFormatStr(final String pattern, final int variantType, final Locale locale) {
        this(pattern, variantType, locale, -1, -1, false);
    }
    
    public int getVariantType() {
        return this.variantType;
    }
    
    public String format(final Variant value) {
        StringBuffer result = null;
        if (this.booleanFormat == null && (value == null || value.isNull() || !(value instanceof Variant))) {
            return new String();
        }
        this.fieldPos = new FieldPosition(0);
        if (this.timeFormat != null) {
            final Date dateObj = variantToLongDate(value);
            if (dateObj != null) {
                try {
                    result = new StringBuffer();
                    result = this.timeFormat.format(dateObj, result, this.fieldPos);
                }
                catch (IllegalArgumentException e) {
                    result = null;
                }
            }
        }
        else if (this.decimalFormat != null) {
            final Double doubleObj = variantToDouble(value, this.scale);
            if (doubleObj != null) {
                result = new StringBuffer();
                result = this.decimalFormat.format((double)doubleObj, result, this.fieldPos);
            }
        }
        else if (this.textFormat != null) {
            final String stringObj = variantToString(value);
            if (stringObj != null) {
                result = new StringBuffer();
                result = this.textFormat.format(stringObj, result, this.fieldPos);
            }
        }
        else if (this.booleanFormat != null) {
            final Boolean booleanObj = variantToBoolean(value);
            result = new StringBuffer();
            result = this.booleanFormat.format(booleanObj, result, this.fieldPos);
        }
        return (result == null) ? null : result.toString();
    }
    
    public void parse(final String stringValue, Variant value, int variantType) throws InvalidFormatException {
        if (value == null || !(value instanceof Variant)) {
            if (value != null) {}
            throw new InvalidFormatException(Res.getString(17));
        }
        if (stringValue == null || stringValue.length() == 0) {
            value.setNull(1);
            return;
        }
        if (variantType <= 1) {
            variantType = this.variantType;
        }
        this.parsePos.setIndex(0);
        if (this.timeFormat != null) {
            final Date dateResult = this.timeFormat.parse(stringValue, this.parsePos);
            if (dateResult == null) {
                throw new InvalidFormatException(Res.getString(18), this.parsePos.getIndex());
            }
            value = longDateToVariant(dateResult, value, variantType);
        }
        else if (this.decimalFormat != null) {
            Number nResult = this.decimalFormat.parse(stringValue, this.parsePos);
            if (nResult == null) {
                try {
                    if (VariantFormatStr.BUG6296 && stringValue.charAt(0) == '+') {
                        final FastStringBuffer fs = new FastStringBuffer(stringValue);
                        fs.removeCharAt(0);
                        try {
                            nResult = this.decimalFormat.parse(fs.toString(), this.parsePos);
                        }
                        catch (Exception ex) {}
                    }
                    else if (VariantFormatStr.BUG5595) {
                        final Double d = Double.valueOf(stringValue);
                        if (d == 0.0) {
                            nResult = d;
                            this.parsePos.setIndex(1);
                        }
                    }
                }
                catch (Exception ex2) {}
            }
            if (nResult == null || (nResult instanceof Double && ((Double)nResult).isNaN()) || this.parsePos.getIndex() == 0) {
                value = null;
            }
            if (nResult != null) {}
            if (nResult != null) {
                doubleToVariant(nResult.doubleValue(), value, variantType, this.scale);
            }
        }
        else if (this.textFormat != null) {
            final StringBuffer sResult = this.textFormat.parse(stringValue, this.parsePos);
            if (sResult == null) {
                value = null;
            }
            else {
                value = stringToVariant(sResult.toString(), value, variantType, this.scale);
            }
        }
        else if (this.booleanFormat != null) {
            final Boolean booleanObj = this.booleanFormat.parse(stringValue, this.parsePos);
            if (booleanObj == null && stringValue != null && stringValue.length() > 0 && this.parsePos.getIndex() == 0) {
                throw new InvalidFormatException(Res.getString(18), this.parsePos.getIndex());
            }
            value = booleanToVariant(booleanObj, value, variantType);
        }
        else {
            value = null;
        }
        if (value == null) {
            throw new InvalidFormatException(Res.getString(18), this.parsePos.getIndex());
        }
    }
    
    public void parse(final String stringValue, final Variant value) throws InvalidFormatException {
        this.parse(stringValue, value, this.variantType);
    }
    
    public String getPattern() {
        return this.pattern;
    }
    
    public String setPattern(String pattern) {
        final String oldFormat = this.getPattern();
        if (pattern == null || pattern.length() == 0) {
            pattern = this.getDefaultPattern(this.variantType);
        }
        if (this.decimalFormat != null) {
            this.decimalFormat.applyPattern(pattern);
        }
        else if (this.timeFormat != null) {
            this.timeFormat.applyPattern(pattern);
        }
        else if (this.textFormat != null) {
            this.textFormat.applyPattern(pattern);
        }
        else if (this.booleanFormat != null) {
            this.booleanFormat.applyPattern(pattern);
        }
        this.pattern = pattern;
        return oldFormat;
    }
    
    public Object setSpecialObject(final int objType, final Object obj) {
        Object resultObj = null;
        if (this.textFormat != null) {
            switch (objType) {
                case 1: {
                    resultObj = new Character(this.textFormat.getFillCharacter());
                    this.textFormat.setFillCharacter((char)obj);
                    break;
                }
                case 2: {
                    resultObj = new Character(this.textFormat.getReplaceCharacter());
                    this.textFormat.setReplaceCharacter((char)obj);
                    break;
                }
            }
        }
        return resultObj;
    }
    
    public Object getSpecialObject(final int objType) {
        if (this.textFormat != null) {
            switch (objType) {
                case 1: {
                    return new Character(this.textFormat.getFillCharacter());
                }
                case 2: {
                    return new Character(this.textFormat.getReplaceCharacter());
                }
            }
        }
        return null;
    }
    
    public Locale getLocale() {
        return this.locale;
    }
    
    public Format getFormatObj() {
        return (this.decimalFormat != null) ? this.decimalFormat : ((this.timeFormat != null) ? this.timeFormat : ((this.textFormat != null) ? this.textFormat : ((this.booleanFormat != null) ? this.booleanFormat : null)));
    }
    
    public int getScale() {
        return this.scale;
    }
    
    public static final String buildTrueFormatMask(final String editMask) {
        final int iLen = editMask.length();
        final char[] src = new char[iLen];
        final char[] dst = new char[iLen];
        editMask.getChars(0, iLen, src, 0);
        int iDst = 0;
        for (int i = 0; i < iLen; ++i) {
            final char c = src[i];
            switch (c) {
                case '^':
                case '{':
                case '}': {
                    break;
                }
                default: {
                    dst[iDst++] = src[i];
                    break;
                }
            }
        }
        return new String(dst, 0, iDst);
    }
    
    static final int formatTypeFromVariantType(final int variantType) {
        switch (variantType) {
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7: {
                return 1;
            }
            case 10: {
                return 2;
            }
            case 11: {
                return 5;
            }
            case 13:
            case 14:
            case 15: {
                return 3;
            }
            default: {
                return 4;
            }
        }
    }
    
    protected String getDefaultPattern(final int variantType) {
        String pattern = null;
        int offset = 0;
        try {
            final ResourceBundle resource = SystemResourceBundle.getBundle("java.text.resources.LocaleElements", this.locale);
            switch (this.formatterType) {
                case 1:
                case 2: {
                    switch (variantType) {
                        case 2: {
                            pattern = new String("###");
                            break;
                        }
                        case 3: {
                            pattern = new String("#####");
                            break;
                        }
                        case 4: {
                            pattern = new String("###########");
                            break;
                        }
                        case 5: {
                            pattern = new String("####################");
                            break;
                        }
                        default: {
                            final String[] resourceArray = resource.getStringArray("NumberPatterns");
                            if (this.isCurrency) {
                                ++offset;
                            }
                            pattern = resourceArray[offset];
                            break;
                        }
                    }
                    break;
                }
                case 3: {
                    final String[] resourceArray = resource.getStringArray("DateTimePatterns");
                    switch (variantType) {
                        case 14: {
                            pattern = resourceArray[2];
                            break;
                        }
                        case 15: {
                            pattern = MessageFormat.format(resourceArray[8], resourceArray[2], resourceArray[7]);
                            break;
                        }
                        default: {
                            pattern = resourceArray[7];
                            break;
                        }
                    }
                    break;
                }
                case 5: {
                    pattern = Res.getString(31);
                    break;
                }
                default: {
                    pattern = new String("");
                    break;
                }
            }
        }
        catch (MissingResourceException e) {
            pattern = new String("");
        }
        return pattern;
    }
    
    static final Date variantToLongDate(final Variant value) {
        Date result = null;
        switch (value.getType()) {
            case 5: {
                result = new Date(value.getLong());
                break;
            }
            case 13: {
                final java.sql.Date dateObj = value.getDate();
                result = new Date(dateObj.getTime());
                break;
            }
            case 14: {
                final Time timeObj = value.getTime();
                result = new Date(timeObj.getTime());
                break;
            }
            case 15: {
                final Timestamp timestampObj = value.getTimestamp();
                result = new Date(timestampObj.getTime() + timestampObj.getNanos() / 1000000);
                break;
            }
            default: {
                result = new Date(value.toString());
                break;
            }
        }
        return result;
    }
    
    static final Variant longDateToVariant(final Date dateVal, Variant value, final int variantType) {
        if (value == null) {
            value = new Variant();
        }
        if (dateVal == null) {
            value.setAssignedNull();
            return value;
        }
        switch (variantType) {
            case 13: {
                if (dateVal == null) {
                    value.setNull(1);
                }
                else {
                    value.setDate(dateVal.getTime());
                }
                break;
            }
            case 14: {
                final Time timeObj = new Time(dateVal.getTime());
                value.setTime(timeObj);
                break;
            }
            case 15: {
                final Timestamp timestampObj = new Timestamp(dateVal.getTime());
                value.setTimestamp(timestampObj);
                break;
            }
            case 5: {
                value.setLong(dateVal.getTime());
                break;
            }
            default: {
                return null;
            }
        }
        return value;
    }
    
    static final Boolean variantToBoolean(final Variant value) {
        Boolean result = null;
        switch (value.getType()) {
            case 11: {
                result = new Boolean(value.getBoolean());
                break;
            }
            case 2:
            case 3:
            case 4: {
                result = new Boolean(value.getAsInt() != 0);
                break;
            }
            case 5: {
                result = new Boolean(value.getLong() != 0);
                break;
            }
        }
        return result;
    }
    
    static final Double variantToDouble(final Variant value, final int scale) {
        Double result = null;
        switch (value.getType()) {
            case 6: {
                result = new Double(value.getFloat());
                break;
            }
            case 7: {
                result = new Double(value.getDouble());
                break;
            }
            case 10: {
                BigDecimal bn = value.getBigDecimal();
                if (scale >= 0) {
                    bn = bn.setScale(scale, 4);
                }
                result = new Double(bn.doubleValue());
                break;
            }
            case 2:
            case 3:
            case 4: {
                result = new Double(value.getAsInt());
                break;
            }
            case 5: {
                result = new Double(value.getLong());
                break;
            }
            default: {
                result = new Double(value.toString());
                break;
            }
        }
        return result;
    }
    
    static final Variant booleanToVariant(final Boolean bool, Variant value, final int variantType) {
        if (value == null) {
            value = new Variant();
        }
        if (bool == null) {
            value.setAssignedNull();
        }
        else {
            switch (variantType) {
                case 11: {
                    value.setBoolean(bool);
                    break;
                }
            }
        }
        return value;
    }
    
    static final Variant doubleToVariant(final double doubleValue, Variant value, final int variantType, final int scale) throws InvalidFormatException {
        if (value == null) {
            value = new Variant();
        }
        switch (variantType) {
            case 2: {
                final int i = (int)doubleValue;
                if (i > 127 || i < -128) {
                    throw new InvalidFormatException(Res.getString(33));
                }
                value.setByte((byte)doubleValue);
                break;
            }
            case 3: {
                final int i = (int)doubleValue;
                if (i > 32767 || i < -32768) {
                    throw new InvalidFormatException(Res.getString(32));
                }
                value.setShort((short)doubleValue);
                break;
            }
            case 4: {
                value.setInt((int)doubleValue);
                break;
            }
            case 5: {
                value.setLong((long)doubleValue);
                break;
            }
            case 7: {
                value.setDouble(doubleValue);
                break;
            }
            case 6: {
                value.setFloat((float)doubleValue);
                break;
            }
            case 10: {
                BigDecimal bn = new BigDecimal(doubleValue);
                if (scale >= 0) {
                    bn = bn.setScale(scale, 4);
                }
                value.setBigDecimal(bn);
                break;
            }
            default: {
                return null;
            }
        }
        return value;
    }
    
    static final String variantToString(final Variant value) {
        String result = null;
        switch (value.getType()) {
            case 16: {
                result = value.getString();
                break;
            }
            default: {
                result = value.toString();
                break;
            }
        }
        return result;
    }
    
    static final Variant stringToVariant(final String stringVal, final Variant value, final int variantType, final int scale) {
        switch (variantType) {
            case 16: {
                value.setString(stringVal.toString());
                break;
            }
            case 2: {
                value.setByte((byte)Integer.parseInt(stringVal));
                break;
            }
            case 3: {
                value.setShort((short)Integer.parseInt(stringVal));
                break;
            }
            case 4: {
                value.setInt(Integer.parseInt(stringVal));
                break;
            }
            case 5: {
                value.setLong(Long.parseLong(stringVal));
                break;
            }
            case 6: {
                final Float f = new Float(stringVal);
                value.setFloat(f);
                break;
            }
            case 7: {
                final Double d = new Double(stringVal);
                value.setDouble(d);
                break;
            }
            case 10: {
                final BigDecimal bn = new BigDecimal(stringVal);
                if (scale >= 0) {
                    bn.setScale(scale);
                }
                value.setBigDecimal(bn);
                break;
            }
            case 13: {
                final Date dateObj = new Date(Date.parse(stringVal));
                value.setDate(dateObj.getTime());
                break;
            }
            case 14: {
                final Time timeObj = new Time(Date.parse(stringVal));
                value.setTime(timeObj);
                break;
            }
            case 15: {
                final Timestamp timestampObj = new Timestamp(Date.parse(stringVal));
                value.setTimestamp(timestampObj);
                break;
            }
            default: {
                return null;
            }
        }
        return value;
    }
    
    static {
        VariantFormatStr.BUG6296 = true;
        VariantFormatStr.BUG5595 = true;
    }
}

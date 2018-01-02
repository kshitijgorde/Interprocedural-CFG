// 
// Decompiled by Procyon v0.5.30
// 

package borland.jbcl.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.ByteArrayInputStream;
import java.sql.Timestamp;
import java.sql.Time;
import java.sql.Date;
import java.math.BigDecimal;

public class Variant implements Cloneable
{
    public static final int UNASSIGNED_NULL = 0;
    public static final int ASSIGNED_NULL = 1;
    public static final int NULL_TYPES = 1;
    public static final int BYTE = 2;
    public static final int SHORT = 3;
    public static final int INT = 4;
    public static final int LONG = 5;
    public static final int FLOAT = 6;
    public static final int DOUBLE = 7;
    public static final int BIGDECIMAL = 10;
    public static final int BOOLEAN = 11;
    public static final int BINARY_STREAM = 12;
    public static final int DATE = 13;
    public static final int TIME = 14;
    public static final int TIMESTAMP = 15;
    public static final int STRING = 16;
    public static final int OBJECT = 17;
    public static final int BYTE_ARRAY = 18;
    public static final String AssignedNull_S = "ASSIGNED_NULL";
    public static final String UnassignedNull_S = "UNASSIGNED_NULL";
    public static final String ByteType_S = "BYTE";
    public static final String ShortType_S = "SHORT";
    public static final String IntType_S = "INT";
    public static final String LongType_S = "LONG";
    public static final String FloatType_S = "FLOAT";
    public static final String DoubleType_S = "DOUBLE";
    public static final String BigDecimalType_S = "BIGDECIMAL";
    public static final String BooleanType_S = "BOOLEAN";
    public static final String BinaryStreamType_S = "BINARY_STREAM";
    public static final String DateType_S = "DATE";
    public static final String TimeType_S = "TIME";
    public static final String TimestampType_S = "TIMESTAMP";
    public static final String ByteArrayType_S = "BYTE_ARRAY";
    public static final String StringType_S = "STRING";
    public static final String ObjectType_S = "OBJECT";
    public static final String UnknownType_S = "UNKNOWN";
    public static final Variant nullVariant;
    public static final int MaxTypes = 18;
    private int setType;
    private int type;
    private boolean booleanVal;
    private int intVal;
    private long longVal;
    private float floatVal;
    private double doubleVal;
    private String stringVal;
    private byte[] byteArrayVal;
    private BigDecimal bigDecimalVal;
    private Object objectVal;
    private Date dateVal;
    private Time timeVal;
    private Timestamp timestampVal;
    private static String zeroString;
    private static char[] zeroCharArray;
    private static BigDecimal zeroBIGDECIMAL;
    private static ByteArrayInputStream zeroBinary;
    private static byte[] zeroByteArray;
    
    public Variant(final int dataType) {
        this.setType = dataType;
    }
    
    public Variant() {
    }
    
    public static String typeName(final int type) {
        switch (type) {
            case 1: {
                return "ASSIGNED_NULL";
            }
            case 0: {
                return "UNASSIGNED_NULL";
            }
            case 2: {
                return "BYTE";
            }
            case 3: {
                return "SHORT";
            }
            case 4: {
                return "INT";
            }
            case 5: {
                return "LONG";
            }
            case 6: {
                return "FLOAT";
            }
            case 7: {
                return "DOUBLE";
            }
            case 10: {
                return "BIGDECIMAL";
            }
            case 11: {
                return "BOOLEAN";
            }
            case 12: {
                return "BINARY_STREAM";
            }
            case 13: {
                return "DATE";
            }
            case 14: {
                return "TIME";
            }
            case 15: {
                return "TIMESTAMP";
            }
            case 16: {
                return "STRING";
            }
            case 18: {
                return "BYTE_ARRAY";
            }
            case 17: {
                return "OBJECT";
            }
            default: {
                return "UNKNOWN";
            }
        }
    }
    
    public static int typeOf(final String typeName) {
        if (typeName.equals("STRING")) {
            return 16;
        }
        if (typeName.equals("DATE")) {
            return 13;
        }
        if (typeName.equals("TIME")) {
            return 14;
        }
        if (typeName.equals("TIMESTAMP")) {
            return 15;
        }
        if (typeName.equals("INT")) {
            return 4;
        }
        if (typeName.equals("BIGDECIMAL")) {
            return 10;
        }
        if (typeName.equals("ASSIGNED_NULL")) {
            return 1;
        }
        if (typeName.equals("UNASSIGNED_NULL")) {
            return 0;
        }
        if (typeName.equals("BYTE")) {
            return 2;
        }
        if (typeName.equals("SHORT")) {
            return 3;
        }
        if (typeName.equals("LONG")) {
            return 5;
        }
        if (typeName.equals("DOUBLE")) {
            return 7;
        }
        if (typeName.equals("FLOAT")) {
            return 6;
        }
        if (typeName.equals("BOOLEAN")) {
            return 11;
        }
        if (typeName.equals("BINARY_STREAM")) {
            return 12;
        }
        if (typeName.equals("BYTE_ARRAY")) {
            return 18;
        }
        if (typeName.equals("OBJECT")) {
            return 17;
        }
        VariantException.fire(Res.format(3, new String[] { typeName }));
        return 0;
    }
    
    public static int typeId(final String name) {
        for (int i = 2; i <= 18; ++i) {
            if (name.equals(typeName(i))) {
                return i;
            }
        }
        return 0;
    }
    
    public final Object getDisplayValue() {
        switch (this.type) {
            case 0:
            case 1: {
                return "";
            }
            case 12:
            case 17: {
                return this.objectVal;
            }
            default: {
                return this.toString();
            }
        }
    }
    
    private boolean setZeroValue(final int unexpectedType, final int expectedType) {
        if (Variant.zeroString == null) {
            Variant.zeroString = "";
            Variant.zeroCharArray = new char[0];
            Variant.zeroBIGDECIMAL = new BigDecimal(0.0);
            Variant.zeroByteArray = new byte[0];
            Variant.zeroBinary = new ByteArrayInputStream(Variant.zeroByteArray);
        }
        switch (expectedType) {
            case 2:
            case 3:
            case 4: {
                this.intVal = 0;
                break;
            }
            case 15: {
                this.setTimestamp(0L, 0);
                break;
            }
            case 14: {
                this.setTime(0L);
                break;
            }
            case 13: {
                this.setDate(0L);
                break;
            }
            case 5: {
                this.longVal = 0L;
                break;
            }
            case 11: {
                this.booleanVal = false;
                break;
            }
            case 6: {
                this.floatVal = 0.0f;
                break;
            }
            case 7: {
                this.doubleVal = 0.0;
                break;
            }
            case 16:
            case 17: {
                this.stringVal = Variant.zeroString;
                break;
            }
            case 10: {
                this.bigDecimalVal = Variant.zeroBIGDECIMAL;
                this.booleanVal = false;
                break;
            }
            case 12: {
                this.objectVal = Variant.zeroBinary;
                break;
            }
            case 18: {
                this.objectVal = Variant.zeroByteArray;
                break;
            }
            default: {
                return false;
            }
        }
        this.type = unexpectedType;
        return true;
    }
    
    private void typeProblem(final int unexpectedType, final int expectedType) {
        if (unexpectedType <= 1 && this.setZeroValue(unexpectedType, expectedType)) {
            return;
        }
        VariantException.fire(Res.format(1, new String[] { typeName(unexpectedType), typeName(expectedType) }));
    }
    
    public final int getInt() {
        if (this.type != 4) {
            this.typeProblem(this.type, 4);
        }
        return this.intVal;
    }
    
    public final short getShort() {
        if (this.type != 3) {
            this.typeProblem(this.type, 3);
        }
        return (short)this.intVal;
    }
    
    public final byte getByte() {
        if (this.type != 2) {
            this.typeProblem(this.type, 2);
        }
        return (byte)this.intVal;
    }
    
    public final long getLong() {
        if (this.type != 5) {
            this.typeProblem(this.type, 5);
        }
        return this.longVal;
    }
    
    public final boolean getBoolean() {
        if (this.type != 11) {
            this.typeProblem(this.type, 11);
        }
        return this.booleanVal;
    }
    
    public final double getDouble() {
        if (this.type != 7) {
            this.typeProblem(this.type, 7);
        }
        return this.doubleVal;
    }
    
    public final float getFloat() {
        if (this.type != 6) {
            this.typeProblem(this.type, 6);
        }
        return this.floatVal;
    }
    
    public final String getString() {
        if (this.type != 16) {
            this.typeProblem(this.type, 16);
        }
        return this.stringVal;
    }
    
    public final BigDecimal getBigDecimal() {
        if (this.type != 10) {
            this.typeProblem(this.type, 10);
        }
        if (this.booleanVal) {
            this.bigDecimalVal = new BigDecimal(this.stringVal);
            this.booleanVal = false;
        }
        return this.bigDecimalVal;
    }
    
    public final Date getDate() {
        if (this.type != 13) {
            this.typeProblem(this.type, 13);
        }
        return this.dateVal;
    }
    
    public final Time getTime() {
        if (this.type != 14) {
            this.typeProblem(this.type, 14);
        }
        return this.timeVal;
    }
    
    public final Timestamp getTimestamp() {
        if (this.type != 15) {
            this.typeProblem(this.type, 15);
        }
        return this.timestampVal;
    }
    
    public final byte[] getByteArray() {
        if (this.type != 18) {
            this.typeProblem(this.type, 18);
        }
        return this.byteArrayVal;
    }
    
    public final int getArrayLength() {
        return this.intVal;
    }
    
    public final InputStream getBinaryStream() {
        if (this.type != 12) {
            this.typeProblem(this.type, 12);
        }
        return (InputStream)this.objectVal;
    }
    
    public final void setInt(final int val) {
        if (this.setType != 0 && this.setType != 4) {
            this.typeProblem(this.setType, 4);
        }
        this.type = 4;
        this.intVal = val;
    }
    
    public final void setShort(final short val) {
        if (this.setType != 0 && this.setType != 3) {
            this.typeProblem(this.setType, 2);
        }
        this.type = 3;
        this.intVal = val;
    }
    
    public final void setByte(final byte val) {
        if (this.setType != 0 && this.setType != 2) {
            this.typeProblem(this.setType, 2);
        }
        this.type = 2;
        this.intVal = val;
    }
    
    public final void setAsInt(final int val) {
        switch (this.type) {
            case 2: {
                this.setByte((byte)val);
            }
            case 3: {
                this.setShort((short)val);
            }
            default: {
                this.setInt(val);
            }
        }
    }
    
    public final void setAsInt(final int type, final int val) {
        switch (type) {
            case 2: {
                this.setByte((byte)val);
            }
            case 3: {
                this.setShort((short)val);
            }
            default: {
                this.setInt(val);
            }
        }
    }
    
    public final void setLong(final long val) {
        if (this.setType != 0 && (this.setType < 2 || this.setType > 5)) {
            this.typeProblem(this.setType, 5);
        }
        this.type = 5;
        this.longVal = val;
    }
    
    public final void setBoolean(final boolean val) {
        if (this.setType != 0 && this.setType != 11) {
            this.typeProblem(this.setType, 11);
        }
        this.type = 11;
        this.booleanVal = val;
    }
    
    public final void setDouble(final double val) {
        if (this.setType != 0 && this.setType != 7) {
            this.typeProblem(this.setType, 7);
        }
        this.type = 7;
        this.doubleVal = val;
    }
    
    public final void setFloat(final float val) {
        if (this.setType != 0 && this.setType != 6) {
            this.typeProblem(this.setType, 6);
        }
        this.type = 6;
        this.floatVal = val;
    }
    
    public final void setAsDouble(final double val) {
        switch (this.setType) {
            case 6: {
                this.setFloat((float)val);
            }
            case 7: {
                this.setDouble(val);
            }
            default: {
                this.typeProblem(this.setType, 7);
            }
        }
    }
    
    public final void setAsDouble(final int type, final double val) {
        switch (type) {
            case 6: {
                this.setFloat((float)val);
            }
            default: {
                this.setDouble(val);
            }
        }
    }
    
    public final void setString(final String val) {
        if (this.setType != 16 && this.setType != 0) {
            this.typeProblem(this.setType, 16);
        }
        this.type = ((val == null) ? 1 : 16);
        this.stringVal = val;
    }
    
    public final void setBigDecimal(final BigDecimal val) {
        if (this.setType != 10 && this.setType != 0) {
            this.typeProblem(this.setType, 10);
        }
        this.type = ((val == null) ? 1 : 10);
        this.bigDecimalVal = val;
        this.booleanVal = false;
    }
    
    public final void setBigDecimal(final String val) {
        if (this.setType != 10 && this.setType != 0) {
            this.typeProblem(this.setType, 10);
        }
        this.type = ((val == null) ? 1 : 10);
        this.bigDecimalVal = null;
        this.stringVal = val;
        this.booleanVal = true;
    }
    
    public final void setDate(final Date val) {
        if (this.setType != 13 && this.setType != 0) {
            this.typeProblem(this.setType, 13);
        }
        if (val == null) {
            this.type = 1;
            this.dateVal = null;
        }
        else {
            this.type = 13;
            if (this.dateVal == null) {
                this.dateVal = new Date(val.getTime());
            }
            else {
                this.dateVal.setTime(val.getTime());
            }
        }
    }
    
    public final void setTime(final Time val) {
        if (this.setType != 14 && this.setType != 0) {
            this.typeProblem(this.setType, 14);
        }
        if (val == null) {
            this.type = 1;
            this.timeVal = null;
        }
        else {
            this.type = 14;
            if (this.timeVal == null) {
                this.timeVal = new Time(val.getTime());
            }
            else {
                this.timeVal.setTime(val.getTime());
            }
        }
    }
    
    public final void setTimestamp(final Timestamp val) {
        if (this.setType != 15 && this.setType != 0) {
            this.typeProblem(this.setType, 15);
        }
        if (val == null) {
            this.type = 1;
            this.timestampVal = null;
        }
        else {
            this.type = 15;
            if (this.timestampVal == null) {
                this.timestampVal = new Timestamp(val.getTime());
            }
            else {
                this.timestampVal.setTime(val.getTime());
            }
            this.timestampVal.setNanos(val.getNanos());
        }
    }
    
    public final void setDate(final long val) {
        if (this.setType != 13 && this.setType != 0) {
            this.typeProblem(this.setType, 13);
        }
        this.type = 13;
        if (this.dateVal == null) {
            this.dateVal = new Date(System.currentTimeMillis());
        }
        this.dateVal.setTime(val);
    }
    
    public final void setTime(final long val) {
        if (this.setType != 14 && this.setType != 0) {
            this.typeProblem(this.setType, 14);
        }
        this.type = 14;
        if (this.timeVal == null) {
            this.timeVal = new Time(System.currentTimeMillis());
        }
        this.timeVal.setTime(val);
    }
    
    public final void setTimestamp(final long val, final int nanos) {
        if (this.setType != 15 && this.setType != 0) {
            this.typeProblem(this.setType, 15);
        }
        this.type = 15;
        if (this.timestampVal == null) {
            this.timestampVal = new Timestamp(System.currentTimeMillis());
        }
        this.timestampVal.setTime(val);
        this.timestampVal.setNanos(nanos);
    }
    
    public final void setTimestamp(final long val) {
        if (this.setType != 15 && this.setType != 0) {
            this.typeProblem(this.setType, 15);
        }
        this.type = 15;
        if (this.timestampVal == null) {
            this.timestampVal = new Timestamp(System.currentTimeMillis());
        }
        this.timestampVal.setTime(val / 1000 * 1000);
        int nanos = (int)(val % 1000 * 1000000);
        if (nanos < 0) {
            nanos += 1000000000;
            this.timestampVal.setTime((val / 1000 - 1) * 1000);
        }
        this.timestampVal.setNanos(nanos);
    }
    
    public final void setByteArray(final byte[] val, final int length) {
        if (this.setType != 18 && this.setType != 0) {
            this.typeProblem(this.setType, 18);
        }
        this.type = ((val == null) ? 1 : 18);
        this.byteArrayVal = val;
        this.intVal = length;
    }
    
    public final void setArrayLength(final int length) {
        this.intVal = length;
    }
    
    public final void setBinaryStream(final InputStream val) {
        if (this.setType != 12 && this.setType != 0) {
            this.typeProblem(this.setType, this.setType);
        }
        this.type = ((val == null) ? 1 : 12);
        this.objectVal = val;
    }
    
    public final void setVariant(final Variant value) {
        switch (value.type) {
            case 16: {
                this.setString(value.stringVal);
                break;
            }
            case 2: {
                this.setByte((byte)value.intVal);
                break;
            }
            case 3: {
                this.setShort((short)value.intVal);
                break;
            }
            case 4: {
                this.setInt(value.intVal);
                break;
            }
            case 11: {
                this.setBoolean(value.booleanVal);
                break;
            }
            case 15: {
                this.setTimestamp(value.getTimestamp());
                break;
            }
            case 13: {
                this.setDate(value.getDate());
                break;
            }
            case 14: {
                this.setTime(value.getTime());
                break;
            }
            case 5: {
                this.setLong(value.longVal);
                break;
            }
            case 6: {
                this.setFloat(value.floatVal);
                break;
            }
            case 7: {
                this.setDouble(value.doubleVal);
                break;
            }
            case 10: {
                if (value.booleanVal) {
                    this.setBigDecimal(value.stringVal);
                }
                else {
                    this.setBigDecimal(value.bigDecimalVal);
                }
                break;
            }
            case 12: {
                this.setBinaryStream((InputStream)value.objectVal);
                break;
            }
            case 18: {
                this.setByteArray(value.byteArrayVal, value.intVal);
                break;
            }
            case 0:
            case 1: {
                if (this.setType != value.type && this.setType != 0) {
                    this.typeProblem(value.type, this.setType);
                }
                this.type = value.type;
                break;
            }
            default: {
                VariantException.fire(Res.format(2, new String[] { typeName(this.type) }));
                break;
            }
        }
    }
    
    public final void setObject(final Object val) {
        if (this.setType != 17 && this.setType != 0) {
            this.typeProblem(this.setType, 17);
        }
        this.type = 17;
        this.objectVal = val;
    }
    
    public final Object getObject() {
        if (this.type != 17) {
            this.typeProblem(this.type, 17);
        }
        return this.objectVal;
    }
    
    public final int getAsInt() {
        switch (this.type) {
            case 2:
            case 3:
            case 4: {
                return this.intVal;
            }
            case 5: {
                return (int)this.longVal;
            }
            case 6: {
                return (int)this.floatVal;
            }
            case 7: {
                return (int)this.doubleVal;
            }
            case 10: {
                return this.getBigDecimal().intValue();
            }
            case 0:
            case 1: {
                return 0;
            }
            default: {
                this.typeProblem(this.type, 4);
                return 0;
            }
        }
    }
    
    public final long getAsLong() {
        switch (this.type) {
            case 2:
            case 3:
            case 4: {
                return this.intVal;
            }
            case 5: {
                return this.longVal;
            }
            case 6: {
                return (long)this.floatVal;
            }
            case 7: {
                return (long)this.doubleVal;
            }
            case 10: {
                return this.getBigDecimal().longValue();
            }
            case 0:
            case 1: {
                return 0L;
            }
            default: {
                this.typeProblem(this.type, 5);
                return 0L;
            }
        }
    }
    
    public final double getAsDouble() {
        switch (this.type) {
            case 2:
            case 3:
            case 4: {
                return this.intVal;
            }
            case 5: {
                return this.longVal;
            }
            case 6: {
                return this.floatVal;
            }
            case 7: {
                return this.doubleVal;
            }
            case 10: {
                return this.getBigDecimal().doubleValue();
            }
            case 0:
            case 1: {
                return 0.0;
            }
            default: {
                this.typeProblem(this.type, 7);
                return 0.0;
            }
        }
    }
    
    public final float getAsFloat() {
        switch (this.type) {
            case 2:
            case 3:
            case 4: {
                return this.intVal;
            }
            case 5: {
                return this.longVal;
            }
            case 6: {
                return this.floatVal;
            }
            case 7: {
                return (float)this.doubleVal;
            }
            case 10: {
                return this.getBigDecimal().floatValue();
            }
            case 0:
            case 1: {
                return 0.0f;
            }
            default: {
                this.typeProblem(this.type, 6);
                return 0.0f;
            }
        }
    }
    
    public final BigDecimal getAsBigDecimal() {
        switch (this.type) {
            case 2:
            case 3:
            case 4: {
                return new BigDecimal((double)this.intVal);
            }
            case 5: {
                return new BigDecimal((double)this.longVal);
            }
            case 6: {
                return new BigDecimal(this.floatVal);
            }
            case 7: {
                return new BigDecimal(this.doubleVal);
            }
            case 10: {
                return this.getBigDecimal();
            }
            case 0:
            case 1: {
                return new BigDecimal(0.0);
            }
            default: {
                this.typeProblem(this.type, 10);
                return null;
            }
        }
    }
    
    public final void setNull(final int nullType) {
        if (nullType == 0) {
            this.type = 0;
        }
        else {
            this.type = 1;
        }
    }
    
    public final void setAssignedNull() {
        this.type = 1;
    }
    
    public final void setUnassignedNull() {
        this.type = 0;
    }
    
    public final boolean isAssignedNull() {
        return this.type == 1;
    }
    
    public final boolean isUnassignedNull() {
        return this.type == 0;
    }
    
    public final boolean isNull() {
        return this.type <= 1;
    }
    
    public final int getType() {
        return this.type;
    }
    
    public final int getSetType() {
        return this.setType;
    }
    
    public final String toString() {
        switch (this.type) {
            case 0:
            case 1: {
                return "";
            }
            case 2:
            case 3:
            case 4: {
                return Integer.toString(this.intVal, 10);
            }
            case 6: {
                return Float.toString(this.floatVal);
            }
            case 7: {
                return Double.toString(this.doubleVal);
            }
            case 5: {
                return Long.toString(this.longVal, 10);
            }
            case 10: {
                if (this.booleanVal) {
                    return this.stringVal;
                }
                if (this.bigDecimalVal == null) {
                    return "";
                }
                return this.bigDecimalVal.toString();
            }
            case 11: {
                return this.booleanVal ? "true" : "false";
            }
            case 16: {
                if (this.stringVal == null) {
                    return "";
                }
                return this.stringVal;
            }
            case 13: {
                return this.dateVal.toString();
            }
            case 14: {
                return this.timeVal.toString();
            }
            case 15: {
                return this.timestampVal.toString();
            }
            case 18: {
                if (this.byteArrayVal == null) {
                    return "";
                }
                return new String(this.byteArrayVal, 0, 0, this.intVal);
            }
            case 12:
            case 17: {
                if (this.objectVal == null) {
                    return "";
                }
                return this.objectVal.toString();
            }
            default: {
                return "";
            }
        }
    }
    
    public final boolean equals(final Variant value) {
        if (this.type != value.type) {
            if (this.type <= 1 || value.type <= 1) {
                return false;
            }
            this.typeProblem(value.type, this.type);
        }
        switch (this.type) {
            case 0:
            case 1: {
                return value.type == this.type;
            }
            case 2:
            case 3:
            case 4: {
                return this.intVal == value.intVal;
            }
            case 11: {
                return this.booleanVal == value.booleanVal;
            }
            case 6: {
                return this.floatVal == value.floatVal;
            }
            case 7: {
                return this.doubleVal == value.doubleVal;
            }
            case 15: {
                return this.timestampVal.getNanos() == value.getTimestamp().getNanos() && this.timestampVal.getTime() == value.getTimestamp().getTime();
            }
            case 13: {
                return this.dateVal.getTime() == value.getDate().getTime();
            }
            case 14: {
                return this.timeVal.getTime() == value.getTime().getTime();
            }
            case 5: {
                return this.longVal == value.longVal;
            }
            case 10: {
                return (this.booleanVal && value.booleanVal && this.stringVal == value.stringVal) || this.getBigDecimal() == value.getBigDecimal() || this.bigDecimalVal.compareTo(value.bigDecimalVal) == 0;
            }
            case 16: {
                return this.stringVal == value.stringVal || this.stringVal.equals(value.stringVal);
            }
            case 18: {
                if (this.intVal != value.intVal) {
                    return false;
                }
                if (this.byteArrayVal == value.byteArrayVal) {
                    return true;
                }
                int off;
                for (off = 0; this.byteArrayVal[off] == value.byteArrayVal[off] && off < this.intVal; ++off) {}
                return off == this.intVal;
            }
            case 12: {
                return this.equals((InputStream)this.objectVal, (InputStream)value.objectVal);
            }
            case 17: {
                return this.objectVal == value.objectVal || this.objectVal.equals(value.objectVal);
            }
            default: {
                return false;
            }
        }
    }
    
    private boolean equals(final char[] val1, final char[] val2) {
        final int len = val1.length;
        if (len != val2.length) {
            return false;
        }
        for (int index = 0; index < len; ++index) {
            if (val1[index] != val2[index]) {
                return false;
            }
        }
        return true;
    }
    
    private boolean equals(final InputStream stream1, final InputStream stream2) {
        if (stream1 == stream2) {
            return true;
        }
        if (stream1 == null || stream2 == null) {
            return false;
        }
        if (!stream1.markSupported() || !stream2.markSupported()) {
            return false;
        }
        try {
            stream1.reset();
            stream2.reset();
            int ch = 0;
            while (ch != -1) {
                ch = stream1.read();
                final int ch2 = stream2.read();
                if (ch != ch2) {
                    return false;
                }
            }
        }
        catch (IOException ex) {
            return false;
        }
        return true;
    }
    
    private final int compareLong(final long value1, final long value2) {
        if (value1 < value2) {
            return -1;
        }
        if (value1 > value2) {
            return 1;
        }
        return 0;
    }
    
    private final int compareDouble(final double value1, final double value2) {
        if (value1 < value2) {
            return -1;
        }
        if (value1 > value2) {
            return 1;
        }
        return 0;
    }
    
    private final int compareFloat(final float value1, final float value2) {
        if (value1 < value2) {
            return -1;
        }
        if (value1 > value2) {
            return 1;
        }
        return 0;
    }
    
    private final int compareTimestamp(final Timestamp timestamp1, final Timestamp timestamp2) {
        final int comp = this.compareLong(timestamp1.getTime(), timestamp2.getTime());
        if (comp == 0) {
            return timestamp1.getNanos() - timestamp2.getNanos();
        }
        return comp;
    }
    
    private final int compareBoolean(final boolean bool1, final boolean bool2) {
        if (bool1 == bool2) {
            return 0;
        }
        if (bool1) {
            return 1;
        }
        return -1;
    }
    
    public int compareTo(final Variant value2) {
        if (this.isNull()) {
            return value2.isNull() ? 0 : -1;
        }
        if (value2.isNull()) {
            return 1;
        }
        switch (this.type) {
            case 2:
            case 3:
            case 4: {
                return this.intVal - value2.getAsInt();
            }
            case 5: {
                return this.compareLong(this.longVal, value2.getAsLong());
            }
            case 6: {
                return this.compareFloat(this.floatVal, value2.getAsFloat());
            }
            case 7: {
                return this.compareDouble(this.doubleVal, value2.getAsDouble());
            }
            case 10: {
                return this.getBigDecimal().compareTo(value2.getAsBigDecimal());
            }
            case 13: {
                return this.compareLong(this.dateVal.getTime(), value2.getDate().getTime());
            }
            case 14: {
                return this.compareLong(this.timeVal.getTime(), value2.getTime().getTime());
            }
            case 15: {
                return this.compareTimestamp(this.timestampVal, value2.getTimestamp());
            }
            case 11: {
                return this.compareBoolean(this.booleanVal, value2.getBoolean());
            }
            case 16: {
                return this.stringVal.compareTo(value2.getString());
            }
            default: {
                return 0;
            }
        }
    }
    
    public void add(final Variant value2, final Variant result) {
        if (value2.isNull() && this.isNull()) {
            result.setVariant(this);
        }
        else {
            switch (this.type) {
                case 2:
                case 3:
                case 4: {
                    result.setInt(this.intVal + value2.getAsInt());
                    break;
                }
                case 5: {
                    result.setLong(this.longVal + value2.getAsLong());
                    break;
                }
                case 13: {
                    result.setDate(this.dateVal.getTime() + value2.getDate().getTime());
                }
                case 14: {
                    result.setTime(this.timeVal.getTime() + value2.getTime().getTime());
                }
                case 6: {
                    result.setFloat(this.floatVal + value2.getAsFloat());
                    break;
                }
                case 7: {
                    result.setDouble(this.doubleVal + value2.getDouble());
                    break;
                }
                case 10: {
                    result.setBigDecimal(this.getBigDecimal().add(value2.getAsBigDecimal()));
                    break;
                }
                case 0:
                case 1: {
                    result.setVariant(value2);
                    break;
                }
            }
        }
    }
    
    public void subtract(final Variant value2, final Variant result) {
        if (value2.isNull() && this.isNull()) {
            result.setVariant(this);
        }
        else {
            switch (this.type) {
                case 2:
                case 3:
                case 4: {
                    result.setInt(this.intVal - value2.getAsInt());
                    break;
                }
                case 5: {
                    result.setLong(this.longVal - value2.getAsLong());
                    break;
                }
                case 6: {
                    result.setFloat(this.floatVal - value2.getAsFloat());
                    break;
                }
                case 7: {
                    result.setDouble(this.doubleVal - value2.getAsDouble());
                    break;
                }
                case 10: {
                    result.setBigDecimal(this.getBigDecimal().subtract(value2.getAsBigDecimal()));
                    break;
                }
                case 0:
                case 1: {
                    result.setVariant(value2);
                    break;
                }
            }
        }
    }
    
    public Object clone() {
        final Variant value = new Variant();
        value.setVariant(this);
        return value;
    }
    
    static {
        nullVariant = new Variant(0);
    }
}

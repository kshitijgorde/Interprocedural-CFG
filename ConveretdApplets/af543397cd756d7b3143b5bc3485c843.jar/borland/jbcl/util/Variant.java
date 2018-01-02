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
    
    public Variant(final int setType) {
        this.setType = setType;
    }
    
    public Variant() {
    }
    
    public static String typeName(final int n) {
        switch (n) {
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
    
    public static int typeOf(final String s) {
        if (s.equals("STRING")) {
            return 16;
        }
        if (s.equals("DATE")) {
            return 13;
        }
        if (s.equals("TIME")) {
            return 14;
        }
        if (s.equals("TIMESTAMP")) {
            return 15;
        }
        if (s.equals("INT")) {
            return 4;
        }
        if (s.equals("BIGDECIMAL")) {
            return 10;
        }
        if (s.equals("ASSIGNED_NULL")) {
            return 1;
        }
        if (s.equals("UNASSIGNED_NULL")) {
            return 0;
        }
        if (s.equals("BYTE")) {
            return 2;
        }
        if (s.equals("SHORT")) {
            return 3;
        }
        if (s.equals("LONG")) {
            return 5;
        }
        if (s.equals("DOUBLE")) {
            return 7;
        }
        if (s.equals("FLOAT")) {
            return 6;
        }
        if (s.equals("BOOLEAN")) {
            return 11;
        }
        if (s.equals("BINARY_STREAM")) {
            return 12;
        }
        if (s.equals("BYTE_ARRAY")) {
            return 18;
        }
        if (s.equals("OBJECT")) {
            return 17;
        }
        VariantException.fire(Res.format(3, new String[] { s }));
        return 0;
    }
    
    public static int typeId(final String s) {
        for (int i = 2; i <= 18; ++i) {
            if (s.equals(typeName(i))) {
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
    
    private boolean setZeroValue(final int n) {
        if (Variant.zeroString == null) {
            Variant.zeroString = "";
            Variant.zeroCharArray = new char[0];
            Variant.zeroBIGDECIMAL = new BigDecimal(0.0);
            Variant.zeroByteArray = new byte[0];
            Variant.zeroBinary = new ByteArrayInputStream(Variant.zeroByteArray);
        }
        switch (n) {
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
            case 6:
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
        return true;
    }
    
    private void typeProblem(final int n, final int zeroValue) {
        if (n <= 1 && this.setZeroValue(zeroValue)) {
            return;
        }
        VariantException.fire(Res.format(1, new String[] { typeName(n), typeName(zeroValue) }));
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
        return (float)this.doubleVal;
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
    
    public final void setInt(final int intVal) {
        if (this.setType != 0 && this.setType != 4) {
            this.typeProblem(this.setType, 4);
        }
        this.type = 4;
        this.intVal = intVal;
    }
    
    public final void setShort(final short intVal) {
        if (this.setType != 0 && this.setType != 3) {
            this.typeProblem(this.setType, 2);
        }
        this.type = 3;
        this.intVal = intVal;
    }
    
    public final void setByte(final int intVal) {
        if (this.setType != 0 && this.setType != 2) {
            this.typeProblem(this.setType, 2);
        }
        this.type = 2;
        this.intVal = intVal;
    }
    
    public final void setAsInt(final int n) {
        switch (this.type) {
            case 2: {
                this.setByte(n);
            }
            case 3: {
                this.setShort((short)n);
            }
            default: {
                this.setInt(n);
            }
        }
    }
    
    public final void setAsInt(final int n, final int n2) {
        switch (n) {
            case 2: {
                this.setByte(n2);
            }
            case 3: {
                this.setShort((short)n2);
            }
            default: {
                this.setInt(n2);
            }
        }
    }
    
    public final void setLong(final long longVal) {
        if (this.setType != 0 && (this.setType < 2 || this.setType > 5)) {
            this.typeProblem(this.setType, 5);
        }
        this.type = 5;
        this.longVal = longVal;
    }
    
    public final void setBoolean(final boolean booleanVal) {
        if (this.setType != 0 && this.setType != 11) {
            this.typeProblem(this.setType, 11);
        }
        this.type = 11;
        this.booleanVal = booleanVal;
    }
    
    public final void setDouble(final double doubleVal) {
        if (this.setType != 0 && this.setType != 7) {
            this.typeProblem(this.setType, 7);
        }
        this.type = 7;
        this.doubleVal = doubleVal;
    }
    
    public final void setFloat(final float n) {
        if (this.setType != 0 && this.setType != 6) {
            this.typeProblem(this.setType, 6);
        }
        this.type = 6;
        this.doubleVal = n;
    }
    
    public final void setAsDouble(final double double1) {
        switch (this.setType) {
            case 6: {
                this.setFloat((float)double1);
            }
            case 7: {
                this.setDouble(double1);
            }
            default: {
                this.typeProblem(this.setType, 7);
            }
        }
    }
    
    public final void setAsDouble(final int n, final double double1) {
        switch (n) {
            case 6: {
                this.setFloat((float)double1);
            }
            default: {
                this.setDouble(double1);
            }
        }
    }
    
    public final void setString(final String stringVal) {
        if (this.setType != 16 && this.setType != 0) {
            this.typeProblem(this.setType, 16);
        }
        this.type = 16;
        this.stringVal = stringVal;
    }
    
    public final void setBigDecimal(final BigDecimal bigDecimalVal) {
        if (this.setType != 10 && this.setType != 0) {
            this.typeProblem(this.setType, 10);
        }
        this.type = 10;
        this.bigDecimalVal = bigDecimalVal;
        this.booleanVal = false;
    }
    
    public final void setBigDecimal(final String stringVal) {
        if (this.setType != 10 && this.setType != 0) {
            this.typeProblem(this.setType, 10);
        }
        this.type = 10;
        this.bigDecimalVal = null;
        this.stringVal = stringVal;
        this.booleanVal = true;
    }
    
    public final void setDate(final Date date) {
        if (this.setType != 13 && this.setType != 0) {
            this.typeProblem(this.setType, 13);
        }
        this.type = 13;
        if (this.dateVal == null) {
            this.dateVal = new Date(date.getTime());
        }
        else {
            this.dateVal.setTime(date.getTime());
        }
    }
    
    public final void setTime(final Time time) {
        if (this.setType != 14 && this.setType != 0) {
            this.typeProblem(this.setType, 14);
        }
        this.type = 14;
        if (this.timeVal == null) {
            this.timeVal = new Time(time.getTime());
        }
        else {
            this.timeVal.setTime(time.getTime());
        }
    }
    
    public final void setTimestamp(final Timestamp timestamp) {
        if (this.setType != 15 && this.setType != 0) {
            this.typeProblem(this.setType, 15);
        }
        this.type = 15;
        if (this.timestampVal == null) {
            this.timestampVal = new Timestamp(timestamp.getTime());
        }
        else {
            this.timestampVal.setTime(timestamp.getTime());
        }
        this.timestampVal.setNanos(timestamp.getNanos());
    }
    
    public final void setDate(final long time) {
        if (this.setType != 13 && this.setType != 0) {
            this.typeProblem(this.setType, 13);
        }
        this.type = 13;
        if (this.dateVal == null) {
            this.dateVal = new Date(System.currentTimeMillis());
        }
        this.dateVal.setTime(time);
    }
    
    public final void setTime(final long time) {
        if (this.setType != 14 && this.setType != 0) {
            this.typeProblem(this.setType, 14);
        }
        this.type = 14;
        if (this.timeVal == null) {
            this.timeVal = new Time(System.currentTimeMillis());
        }
        this.timeVal.setTime(time);
    }
    
    public final void setTimestamp(final long time, final int nanos) {
        if (this.setType != 15 && this.setType != 0) {
            this.typeProblem(this.setType, 15);
        }
        this.type = 15;
        if (this.timestampVal == null) {
            this.timestampVal = new Timestamp(System.currentTimeMillis());
        }
        this.timestampVal.setTime(time);
        this.timestampVal.setNanos(nanos);
    }
    
    public final void setTimestamp(final long n) {
        if (this.setType != 15 && this.setType != 0) {
            this.typeProblem(this.setType, 15);
        }
        this.type = 15;
        if (this.timestampVal == null) {
            this.timestampVal = new Timestamp(System.currentTimeMillis());
        }
        this.timestampVal.setTime(n / 1000 * 1000);
        int nanos = (int)(n % 1000 * 1000000);
        if (nanos < 0) {
            nanos += 1000000000;
            this.timestampVal.setTime((n / 1000 - 1) * 1000);
        }
        this.timestampVal.setNanos(nanos);
    }
    
    public final void setByteArray(final byte[] byteArrayVal, final int intVal) {
        if (this.setType != 18 && this.setType != 0) {
            this.typeProblem(this.setType, 18);
        }
        this.type = 18;
        this.byteArrayVal = byteArrayVal;
        this.intVal = intVal;
    }
    
    public final void setArrayLength(final int intVal) {
        this.intVal = intVal;
    }
    
    public final void setBinaryStream(final InputStream objectVal) {
        if (this.setType != 12 && this.setType != 0) {
            this.typeProblem(this.setType, this.setType);
        }
        this.type = 12;
        this.objectVal = objectVal;
    }
    
    public final void setVariant(final Variant variant) {
        switch (variant.type) {
            case 16: {
                this.setString(variant.stringVal);
                break;
            }
            case 2: {
                this.setByte(variant.intVal);
                break;
            }
            case 3: {
                this.setShort((short)variant.intVal);
                break;
            }
            case 4: {
                this.setInt(variant.intVal);
                break;
            }
            case 11: {
                this.setBoolean(variant.booleanVal);
                break;
            }
            case 15: {
                this.setTimestamp(variant.getTimestamp());
                break;
            }
            case 13: {
                this.setDate(variant.getDate());
                break;
            }
            case 14: {
                this.setTime(variant.getTime());
                break;
            }
            case 5: {
                this.setLong(variant.longVal);
                break;
            }
            case 6: {
                this.setFloat((float)variant.doubleVal);
                break;
            }
            case 7: {
                this.setDouble(variant.doubleVal);
                break;
            }
            case 10: {
                if (variant.booleanVal) {
                    this.setBigDecimal(variant.stringVal);
                }
                else {
                    this.setBigDecimal(variant.bigDecimalVal);
                }
                break;
            }
            case 12: {
                this.setBinaryStream((InputStream)variant.objectVal);
                break;
            }
            case 18: {
                this.setByteArray(variant.byteArrayVal, variant.intVal);
                break;
            }
            case 0:
            case 1: {
                if (this.setType != variant.type && this.setType != 0) {
                    this.typeProblem(variant.type, this.setType);
                }
                this.type = variant.type;
                break;
            }
            default: {
                VariantException.fire(Res.format(2, new String[] { typeName(this.type) }));
                break;
            }
        }
    }
    
    public final void setObject(final Object objectVal) {
        if (this.setType != 17 && this.setType != 0) {
            this.typeProblem(this.setType, 17);
        }
        this.type = 17;
        this.objectVal = objectVal;
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
            case 6:
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
            case 6:
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
            case 6:
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
            case 6:
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
    
    public final void setNull(final int n) {
        if (n == 0) {
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
            case 6:
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
    
    public final boolean equals(final Variant variant) {
        if (this.type != variant.type) {
            if (this.type <= 1 || variant.type <= 1) {
                return false;
            }
            this.typeProblem(variant.type, this.type);
        }
        switch (this.type) {
            case 0:
            case 1: {
                return variant.type == this.type;
            }
            case 2:
            case 3:
            case 4: {
                return this.intVal == variant.intVal;
            }
            case 11: {
                return this.booleanVal == variant.booleanVal;
            }
            case 6:
            case 7: {
                return this.doubleVal == variant.doubleVal;
            }
            case 15: {
                return this.timestampVal.getNanos() == variant.getTimestamp().getNanos() && this.timestampVal.getTime() == variant.getTimestamp().getTime();
            }
            case 13: {
                return this.dateVal.getTime() == variant.getDate().getTime();
            }
            case 14: {
                return this.timeVal.getTime() == variant.getTime().getTime();
            }
            case 5: {
                return this.longVal == variant.longVal;
            }
            case 10: {
                return (this.booleanVal && variant.booleanVal && this.stringVal == variant.stringVal) || this.getBigDecimal() == variant.getBigDecimal() || this.bigDecimalVal.compareTo(variant.bigDecimalVal) == 0;
            }
            case 16: {
                return this.stringVal == variant.stringVal || this.stringVal.equals(variant.stringVal);
            }
            case 18: {
                if (this.intVal != variant.intVal) {
                    return false;
                }
                if (this.byteArrayVal == variant.byteArrayVal) {
                    return true;
                }
                int n;
                for (n = 0; this.byteArrayVal[n] == variant.byteArrayVal[n] && n < this.intVal; ++n) {}
                return n == this.intVal;
            }
            case 12: {
                return this.equals((InputStream)this.objectVal, (InputStream)variant.objectVal);
            }
            case 17: {
                return this.objectVal == variant.objectVal || this.objectVal.equals(variant.objectVal);
            }
            default: {
                return false;
            }
        }
    }
    
    private boolean equals(final char[] array, final char[] array2) {
        final int length = array.length;
        if (length != array2.length) {
            return false;
        }
        for (int i = 0; i < length; ++i) {
            if (array[i] != array2[i]) {
                return false;
            }
        }
        return true;
    }
    
    private boolean equals(final InputStream inputStream, final InputStream inputStream2) {
        if (inputStream == inputStream2) {
            return true;
        }
        if (inputStream == null || inputStream2 == null) {
            return false;
        }
        try {
            inputStream.reset();
            inputStream2.reset();
            int i = 0;
            while (i != -1) {
                i = inputStream.read();
                if (i != inputStream2.read()) {
                    return false;
                }
            }
        }
        catch (IOException ex) {
            return false;
        }
        return true;
    }
    
    private final int compareLong(final long n, final long n2) {
        if (n < n2) {
            return -1;
        }
        if (n > n2) {
            return 1;
        }
        return 0;
    }
    
    private final int compareDouble(final double n, final double n2) {
        if (n < n2) {
            return -1;
        }
        if (n > n2) {
            return 1;
        }
        return 0;
    }
    
    private final int compareTimestamp(final Timestamp timestamp, final Timestamp timestamp2) {
        final int compareLong = this.compareLong(timestamp.getTime(), timestamp2.getTime());
        if (compareLong == 0) {
            return timestamp.getNanos() - timestamp2.getNanos();
        }
        return compareLong;
    }
    
    private final int compareBoolean(final boolean b, final boolean b2) {
        if (b == b2) {
            return 0;
        }
        if (b) {
            return 1;
        }
        return -1;
    }
    
    public int compareTo(final Variant variant) {
        if (this.isNull()) {
            return variant.isNull() ? 0 : -1;
        }
        if (variant.isNull()) {
            return 1;
        }
        switch (this.type) {
            case 2:
            case 3:
            case 4: {
                return this.intVal - variant.getAsInt();
            }
            case 5: {
                return this.compareLong(this.longVal, variant.getAsLong());
            }
            case 6:
            case 7: {
                return this.compareDouble(this.doubleVal, variant.getAsDouble());
            }
            case 10: {
                return this.getBigDecimal().compareTo(variant.getAsBigDecimal());
            }
            case 13: {
                return this.compareLong(this.dateVal.getTime(), variant.getDate().getTime());
            }
            case 14: {
                return this.compareLong(this.timeVal.getTime(), variant.getTime().getTime());
            }
            case 15: {
                return this.compareTimestamp(this.timestampVal, variant.getTimestamp());
            }
            case 11: {
                return this.compareBoolean(this.booleanVal, variant.getBoolean());
            }
            case 16: {
                return this.stringVal.compareTo(variant.getString());
            }
            default: {
                return 0;
            }
        }
    }
    
    public void add(final Variant variant, final Variant variant2) {
        if (variant.isNull() && this.isNull()) {
            variant2.setVariant(this);
        }
        else {
            switch (this.type) {
                case 4: {
                    variant2.setInt(this.intVal + variant.getAsInt());
                    break;
                }
                case 5: {
                    variant2.setLong(this.longVal + variant.getAsLong());
                    break;
                }
                case 7: {
                    variant2.setDouble(this.doubleVal + variant.getDouble());
                    break;
                }
                case 10: {
                    variant2.setBigDecimal(this.getBigDecimal().add(variant.getAsBigDecimal()));
                    break;
                }
                case 0:
                case 1: {
                    variant2.setVariant(variant);
                    break;
                }
            }
        }
    }
    
    public void subtract(final Variant variant, final Variant variant2) {
        if (variant.isNull() && this.isNull()) {
            variant2.setVariant(this);
        }
        else {
            switch (this.type) {
                case 4: {
                    variant2.setInt(this.intVal - variant.getAsInt());
                    break;
                }
                case 5: {
                    variant2.setLong(this.longVal - variant.getAsLong());
                    break;
                }
                case 7: {
                    variant2.setDouble(this.doubleVal - variant.getAsDouble());
                    break;
                }
                case 10: {
                    variant2.setBigDecimal(this.getBigDecimal().subtract(variant.getAsBigDecimal()));
                    break;
                }
                case 0:
                case 1: {
                    variant2.setVariant(variant);
                    break;
                }
            }
        }
    }
    
    public Object clone() {
        final Variant variant = new Variant();
        variant.setVariant(this);
        return variant;
    }
    
    static {
        nullVariant = new Variant(0);
    }
}

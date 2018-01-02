// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.util;

import org.apache.activemq.command.ActiveMQDestination;
import java.util.Date;
import java.util.HashMap;

public final class TypeConversionSupport
{
    private static final HashMap<ConversionKey, Converter> CONVERSION_MAP;
    
    public static Object convert(final Object value, final Class clazz) {
        assert value != null && clazz != null;
        if (value.getClass() == clazz) {
            return value;
        }
        final Converter c = TypeConversionSupport.CONVERSION_MAP.get(new ConversionKey(value.getClass(), clazz));
        if (c == null) {
            return null;
        }
        return c.convert(value);
    }
    
    static {
        CONVERSION_MAP = new HashMap<ConversionKey, Converter>();
        final Converter toStringConverter = new Converter() {
            @Override
            public Object convert(final Object value) {
                return value.toString();
            }
        };
        TypeConversionSupport.CONVERSION_MAP.put(new ConversionKey(Boolean.class, String.class), toStringConverter);
        TypeConversionSupport.CONVERSION_MAP.put(new ConversionKey(Byte.class, String.class), toStringConverter);
        TypeConversionSupport.CONVERSION_MAP.put(new ConversionKey(Short.class, String.class), toStringConverter);
        TypeConversionSupport.CONVERSION_MAP.put(new ConversionKey(Integer.class, String.class), toStringConverter);
        TypeConversionSupport.CONVERSION_MAP.put(new ConversionKey(Long.class, String.class), toStringConverter);
        TypeConversionSupport.CONVERSION_MAP.put(new ConversionKey(Float.class, String.class), toStringConverter);
        TypeConversionSupport.CONVERSION_MAP.put(new ConversionKey(Double.class, String.class), toStringConverter);
        TypeConversionSupport.CONVERSION_MAP.put(new ConversionKey(String.class, Boolean.class), new Converter() {
            @Override
            public Object convert(final Object value) {
                return Boolean.valueOf((String)value);
            }
        });
        TypeConversionSupport.CONVERSION_MAP.put(new ConversionKey(String.class, Byte.class), new Converter() {
            @Override
            public Object convert(final Object value) {
                return Byte.valueOf((String)value);
            }
        });
        TypeConversionSupport.CONVERSION_MAP.put(new ConversionKey(String.class, Short.class), new Converter() {
            @Override
            public Object convert(final Object value) {
                return Short.valueOf((String)value);
            }
        });
        TypeConversionSupport.CONVERSION_MAP.put(new ConversionKey(String.class, Integer.class), new Converter() {
            @Override
            public Object convert(final Object value) {
                return Integer.valueOf((String)value);
            }
        });
        TypeConversionSupport.CONVERSION_MAP.put(new ConversionKey(String.class, Long.class), new Converter() {
            @Override
            public Object convert(final Object value) {
                return Long.valueOf((String)value);
            }
        });
        TypeConversionSupport.CONVERSION_MAP.put(new ConversionKey(String.class, Float.class), new Converter() {
            @Override
            public Object convert(final Object value) {
                return Float.valueOf((String)value);
            }
        });
        TypeConversionSupport.CONVERSION_MAP.put(new ConversionKey(String.class, Double.class), new Converter() {
            @Override
            public Object convert(final Object value) {
                return Double.valueOf((String)value);
            }
        });
        final Converter longConverter = new Converter() {
            @Override
            public Object convert(final Object value) {
                return ((Number)value).longValue();
            }
        };
        TypeConversionSupport.CONVERSION_MAP.put(new ConversionKey(Byte.class, Long.class), longConverter);
        TypeConversionSupport.CONVERSION_MAP.put(new ConversionKey(Short.class, Long.class), longConverter);
        TypeConversionSupport.CONVERSION_MAP.put(new ConversionKey(Integer.class, Long.class), longConverter);
        TypeConversionSupport.CONVERSION_MAP.put(new ConversionKey(Date.class, Long.class), new Converter() {
            @Override
            public Object convert(final Object value) {
                return ((Date)value).getTime();
            }
        });
        final Converter intConverter = new Converter() {
            @Override
            public Object convert(final Object value) {
                return ((Number)value).intValue();
            }
        };
        TypeConversionSupport.CONVERSION_MAP.put(new ConversionKey(Byte.class, Integer.class), intConverter);
        TypeConversionSupport.CONVERSION_MAP.put(new ConversionKey(Short.class, Integer.class), intConverter);
        TypeConversionSupport.CONVERSION_MAP.put(new ConversionKey(Byte.class, Short.class), new Converter() {
            @Override
            public Object convert(final Object value) {
                return ((Number)value).shortValue();
            }
        });
        TypeConversionSupport.CONVERSION_MAP.put(new ConversionKey(Float.class, Double.class), new Converter() {
            @Override
            public Object convert(final Object value) {
                return new Double(((Number)value).doubleValue());
            }
        });
        TypeConversionSupport.CONVERSION_MAP.put(new ConversionKey(String.class, ActiveMQDestination.class), new Converter() {
            @Override
            public Object convert(final Object value) {
                return ActiveMQDestination.createDestination((String)value, (byte)1);
            }
        });
    }
    
    static class ConversionKey
    {
        final Class from;
        final Class to;
        final int hashCode;
        
        public ConversionKey(final Class from, final Class to) {
            this.from = from;
            this.to = to;
            this.hashCode = (from.hashCode() ^ to.hashCode() << 1);
        }
        
        @Override
        public boolean equals(final Object o) {
            final ConversionKey x = (ConversionKey)o;
            return x.from == this.from && x.to == this.to;
        }
        
        @Override
        public int hashCode() {
            return this.hashCode;
        }
    }
    
    interface Converter
    {
        Object convert(final Object p0);
    }
}

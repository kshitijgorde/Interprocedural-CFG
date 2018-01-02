// 
// Decompiled by Procyon v0.5.30
// 

package com.kenai.constantine;

import com.kenai.constantine.platform.Errno;
import java.util.Collection;
import java.lang.reflect.Field;
import java.util.Iterator;
import java.util.EnumSet;
import java.util.concurrent.ConcurrentHashMap;
import java.util.Set;
import java.util.concurrent.ConcurrentMap;
import java.util.AbstractSet;

public class ConstantSet extends AbstractSet<Constant>
{
    private final ConcurrentMap<String, Constant> nameToConstant;
    private final ConcurrentMap<Integer, Constant> valueToConstant;
    private final Set<Constant> constants;
    private final Class<Enum> enumClass;
    private volatile Long minValue;
    private volatile Long maxValue;
    private static final ConcurrentMap<String, ConstantSet> constantSets;
    private static final Object lock;
    
    public static ConstantSet getConstantSet(final String name) {
        ConstantSet constants = ConstantSet.constantSets.get(name);
        if (constants == null) {
            synchronized (ConstantSet.lock) {
                if (!ConstantSet.constantSets.containsKey(name)) {
                    final Class<Enum> enumClass = getEnumClass(name);
                    if (enumClass == null) {
                        return null;
                    }
                    if (!Constant.class.isAssignableFrom(enumClass)) {
                        throw new ClassCastException("class for " + name + " does not implement Constant interface");
                    }
                    constants = new ConstantSet(enumClass);
                    ConstantSet.constantSets.put(name, constants);
                }
            }
        }
        return constants;
    }
    
    private static final Class<Enum> getEnumClass(final String name) {
        final String[] arr$;
        final String[] prefixes = arr$ = new String[] { Platform.getPlatform().getPackageName(), Platform.getPlatform().getOSPackageName(), Platform.class.getPackage().getName() + ".platform.fake" };
        final int len$ = arr$.length;
        int i$ = 0;
        while (i$ < len$) {
            final String prefix = arr$[i$];
            try {
                return (Class<Enum>)Class.forName(prefix + "." + name).asSubclass(Enum.class);
            }
            catch (ClassNotFoundException ex) {
                ++i$;
                continue;
            }
            break;
        }
        return null;
    }
    
    private ConstantSet(final Class<Enum> enumClass) {
        this.enumClass = enumClass;
        this.nameToConstant = new ConcurrentHashMap<String, Constant>();
        this.valueToConstant = new ConcurrentHashMap<Integer, Constant>();
        this.constants = (Set<Constant>)EnumSet.allOf(enumClass);
    }
    
    public Constant getConstant(final String name) {
        Constant c = this.nameToConstant.get(name);
        if (c == null) {
            try {
                this.nameToConstant.put(name, c = Constant.class.cast(Enum.valueOf((Class<Object>)this.enumClass, name)));
            }
            catch (IllegalArgumentException ex) {
                return null;
            }
        }
        return c;
    }
    
    public Constant getConstant(final int value) {
        Constant c = this.valueToConstant.get(value);
        if (c == null && c == null) {
            for (final Constant c2 : this.constants) {
                if (c2.value() == value) {
                    c = c2;
                    break;
                }
            }
            if (c != null) {
                this.valueToConstant.put(value, c);
            }
        }
        return c;
    }
    
    public int getValue(final String name) {
        final Constant c = this.getConstant(name);
        return (c != null) ? c.value() : 0;
    }
    
    public String getName(final int value) {
        final Constant c = this.getConstant(value);
        return (c != null) ? c.name() : "unknown";
    }
    
    private Long getLongField(final String name, final long defaultValue) {
        try {
            final Field f = this.enumClass.getField("MIN_VALUE");
            return (Long)f.get(this.enumClass);
        }
        catch (NoSuchFieldException ex3) {
            return defaultValue;
        }
        catch (RuntimeException ex) {
            throw ex;
        }
        catch (Exception ex2) {
            throw new RuntimeException(ex2);
        }
    }
    
    public long minValue() {
        if (this.minValue == null) {
            this.minValue = this.getLongField("MIN_VALUE", -2147483648L);
        }
        return (int)(Object)this.minValue;
    }
    
    public long maxValue() {
        if (this.maxValue == null) {
            this.maxValue = this.getLongField("MAX_VALUE", 2147483647L);
        }
        return (int)(Object)this.maxValue;
    }
    
    public Iterator<Constant> iterator() {
        return new ConstantIterator(this.constants);
    }
    
    public int size() {
        return this.constants.size();
    }
    
    public boolean contains(final Object o) {
        return o != null && o.getClass().equals(this.enumClass);
    }
    
    public static void main(final String[] args) {
        final ConstantSet errnos = getConstantSet("Errno");
        for (final Constant c : errnos) {
            System.out.println(c.name() + "=" + c.value());
        }
        final Errno errno = Errno.valueOf(22);
        System.out.println("errno for 22=" + errno);
        System.out.println("errno for 101=" + Errno.valueOf(101));
        System.out.println("errno for 22=" + Errno.valueOf(22));
        System.out.println("EINVAL.value() = " + Errno.EINVAL.value());
        System.out.println("E2BIG.value() = " + Errno.E2BIG.value());
    }
    
    static {
        constantSets = new ConcurrentHashMap<String, ConstantSet>();
        lock = new Object();
    }
    
    private final class ConstantIterator implements Iterator<Constant>
    {
        private final Iterator<Constant> it;
        
        ConstantIterator(final Collection<Constant> constants) {
            this.it = constants.iterator();
        }
        
        public boolean hasNext() {
            return this.it.hasNext();
        }
        
        public void remove() {
            throw new UnsupportedOperationException();
        }
        
        public Constant next() {
            return this.it.next();
        }
    }
}

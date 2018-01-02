// 
// Decompiled by Procyon v0.5.30
// 

package com.sun.java.util.collections;

import java.io.Serializable;
import java.util.Enumeration;

public class Collections
{
    private static Random r;
    public static final Set EMPTY_SET;
    public static final List EMPTY_LIST;
    private static final Comparator REVERSE_ORDER;
    
    public static void sort(final List list) {
        final Object[] array = list.toArray();
        Arrays.sort(array);
        final ListIterator listIterator = list.listIterator();
        for (int i = 0; i < array.length; ++i) {
            listIterator.next();
            listIterator.set(array[i]);
        }
    }
    
    public static void sort(final List list, final Comparator comparator) {
        final Object[] array = list.toArray();
        Arrays.sort(array, comparator);
        final ListIterator listIterator = list.listIterator();
        for (int i = 0; i < array.length; ++i) {
            listIterator.next();
            listIterator.set(array[i]);
        }
    }
    
    public static int binarySearch(final List list, final Object o) {
        if (list instanceof AbstractSequentialList) {
            final ListIterator listIterator = list.listIterator();
            while (listIterator.hasNext()) {
                final int compareTo = ((Comparable)listIterator.next()).compareTo(o);
                if (compareTo == 0) {
                    return listIterator.previousIndex();
                }
                if (compareTo > 0) {
                    return -listIterator.nextIndex();
                }
            }
            return -listIterator.nextIndex() - 1;
        }
        int i = 0;
        int n = list.size() - 1;
        while (i <= n) {
            final int n2 = (i + n) / 2;
            final int compareTo2 = ((Comparable)list.get(n2)).compareTo(o);
            if (compareTo2 < 0) {
                i = n2 + 1;
            }
            else {
                if (compareTo2 <= 0) {
                    return n2;
                }
                n = n2 - 1;
            }
        }
        return -(i + 1);
    }
    
    public static int binarySearch(final List list, final Object o, final Comparator comparator) {
        if (list instanceof AbstractSequentialList) {
            final ListIterator listIterator = list.listIterator();
            while (listIterator.hasNext()) {
                final int compare = comparator.compare(listIterator.next(), o);
                if (compare == 0) {
                    return listIterator.previousIndex();
                }
                if (compare > 0) {
                    return -listIterator.nextIndex();
                }
            }
            return -listIterator.nextIndex() - 1;
        }
        int i = 0;
        int n = list.size() - 1;
        while (i <= n) {
            final int n2 = (i + n) / 2;
            final int compare2 = comparator.compare(list.get(n2), o);
            if (compare2 < 0) {
                i = n2 + 1;
            }
            else {
                if (compare2 <= 0) {
                    return n2;
                }
                n = n2 - 1;
            }
        }
        return -(i + 1);
    }
    
    public static void reverse(final List list) {
        final ListIterator listIterator = list.listIterator();
        final ListIterator listIterator2 = list.listIterator(list.size());
        for (int i = 0; i < list.size() / 2; ++i) {
            final Object next = listIterator.next();
            listIterator.set(listIterator2.previous());
            listIterator2.set(next);
        }
    }
    
    public static void shuffle(final List list) {
        shuffle(list, Collections.r);
    }
    
    public static void shuffle(final List list, final Random random) {
        for (int i = list.size(); i > 1; --i) {
            swap(list, i - 1, random.nextInt(i));
        }
    }
    
    private static void swap(final List list, final int n, final int n2) {
        final Object value = list.get(n);
        list.set(n, list.get(n2));
        list.set(n2, value);
    }
    
    public static void fill(final List list, final Object o) {
        final ListIterator listIterator = list.listIterator();
        while (listIterator.hasNext()) {
            listIterator.next();
            listIterator.set(o);
        }
    }
    
    public static void copy(final List list, final List list2) {
        try {
            final ListIterator listIterator = list.listIterator();
            final ListIterator listIterator2 = list2.listIterator();
            while (listIterator2.hasNext()) {
                listIterator.next();
                listIterator.set(listIterator2.next());
            }
        }
        catch (NoSuchElementException ex) {
            throw new IndexOutOfBoundsException("Source does not fit in dest.");
        }
    }
    
    public static Object min(final Collection collection) {
        final Iterator iterator = collection.iterator();
        Comparable comparable = (Comparable)iterator.next();
        while (iterator.hasNext()) {
            final Comparable comparable2 = (Comparable)iterator.next();
            if (comparable2.compareTo((Object)comparable) < 0) {
                comparable = comparable2;
            }
        }
        return comparable;
    }
    
    public static Object min(final Collection collection, final Comparator comparator) {
        final Iterator iterator = collection.iterator();
        Object next = iterator.next();
        while (iterator.hasNext()) {
            final Object next2 = iterator.next();
            if (comparator.compare(next2, next) < 0) {
                next = next2;
            }
        }
        return next;
    }
    
    public static Object max(final Collection collection) {
        final Iterator iterator = collection.iterator();
        Comparable comparable = (Comparable)iterator.next();
        while (iterator.hasNext()) {
            final Comparable comparable2 = (Comparable)iterator.next();
            if (comparable2.compareTo((Object)comparable) > 0) {
                comparable = comparable2;
            }
        }
        return comparable;
    }
    
    public static Object max(final Collection collection, final Comparator comparator) {
        final Iterator iterator = collection.iterator();
        Object next = iterator.next();
        while (iterator.hasNext()) {
            final Object next2 = iterator.next();
            if (comparator.compare(next2, next) > 0) {
                next = next2;
            }
        }
        return next;
    }
    
    public static Collection unmodifiableCollection(final Collection collection) {
        return (Collection)new Collections.UnmodifiableCollection(collection);
    }
    
    public static Set unmodifiableSet(final Set set) {
        return (Set)new Collections.UnmodifiableSet(set);
    }
    
    public static SortedSet unmodifiableSortedSet(final SortedSet set) {
        return (SortedSet)new Collections.UnmodifiableSortedSet(set);
    }
    
    public static List unmodifiableList(final List list) {
        return (List)new Collections.UnmodifiableList(list);
    }
    
    public static Map unmodifiableMap(final Map map) {
        return (Map)new Collections.UnmodifiableMap(map);
    }
    
    public static SortedMap unmodifiableSortedMap(final SortedMap sortedMap) {
        return (SortedMap)new Collections.UnmodifiableSortedMap(sortedMap);
    }
    
    public static Collection synchronizedCollection(final Collection collection) {
        return (Collection)new Collections.SynchronizedCollection(collection);
    }
    
    static Collection synchronizedCollection(final Collection collection, final Object o) {
        return (Collection)new Collections.SynchronizedCollection(collection, o);
    }
    
    public static Set synchronizedSet(final Set set) {
        return (Set)new Collections.SynchronizedSet(set);
    }
    
    static Set synchronizedSet(final Set set, final Object o) {
        return (Set)new Collections.SynchronizedSet(set, o);
    }
    
    public static SortedSet synchronizedSortedSet(final SortedSet set) {
        return (SortedSet)new Collections.SynchronizedSortedSet(set);
    }
    
    public static List synchronizedList(final List list) {
        return (List)new Collections.SynchronizedList(list);
    }
    
    static List synchronizedList(final List list, final Object o) {
        return (List)new Collections.SynchronizedList(list, o);
    }
    
    public static Map synchronizedMap(final Map map) {
        return (Map)new Collections.SynchronizedMap(map);
    }
    
    public static SortedMap synchronizedSortedMap(final SortedMap sortedMap) {
        return (SortedMap)new Collections.SynchronizedSortedMap(sortedMap);
    }
    
    public static Set singleton(final Object o) {
        return (Set)new Collections.Collections$7(o);
    }
    
    public static List nCopies(final int n, final Object o) {
        if (n < 0) {
            throw new IllegalArgumentException("List length = " + n);
        }
        return (List)new Collections.Collections$9(n, o);
    }
    
    public static Comparator reverseOrder() {
        return Collections.REVERSE_ORDER;
    }
    
    public static Enumeration enumeration(final Collection collection) {
        return (Enumeration)new Collections.Collections$10(collection);
    }
    
    private static boolean eq(final Object o, final Object o2) {
        if (o == null) {
            return o2 == null;
        }
        return o.equals(o2);
    }
    
    static {
        Collections.r = new Random();
        EMPTY_SET = new AbstractSet() {
            public Iterator iterator() {
                return (Iterator)new Collections$4.Collections$5();
            }
            
            public int size() {
                return 0;
            }
            
            public boolean contains(final Object o) {
                return false;
            }
        };
        EMPTY_LIST = new AbstractList() {
            public int size() {
                return 0;
            }
            
            public boolean contains(final Object o) {
                return false;
            }
            
            public Object get(final int n) {
                throw new IndexOutOfBoundsException("Index: " + n);
            }
        };
        REVERSE_ORDER = new ReverseComparator();
    }
    
    private static class ReverseComparator implements Comparator, Serializable
    {
        public int compare(final Object o, final Object o2) {
            return -((Comparable)o).compareTo((Object)o2);
        }
    }
}

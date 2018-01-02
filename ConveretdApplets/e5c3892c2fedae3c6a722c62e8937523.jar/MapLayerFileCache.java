import java.util.Vector;
import java.util.Enumeration;
import java.util.Hashtable;

// 
// Decompiled by Procyon v0.5.30
// 

public class MapLayerFileCache
{
    private Hashtable cache;
    
    public MapLayerFileCache() {
        this.cache = new Hashtable(4);
    }
    
    public void age() {
        final Enumeration<String> keys = (Enumeration<String>)this.cache.keys();
        while (keys.hasMoreElements()) {
            final CacheEntry cacheEntry = this.cache.get(keys.nextElement());
            if (cacheEntry.age < 100) {
                final CacheEntry cacheEntry2 = cacheEntry;
                ++cacheEntry2.age;
            }
        }
    }
    
    public boolean addFile(final String s) {
        CacheEntry cacheEntry = this.cache.get(s);
        if (cacheEntry != null) {
            cacheEntry.age = 0;
        }
        else {
            cacheEntry = new CacheEntry();
            cacheEntry.data = null;
            cacheEntry.age = 0;
            cacheEntry.loaded = false;
            this.cache.put(s, cacheEntry);
        }
        return !cacheEntry.loaded;
    }
    
    public void purge() {
        while (this.cache.size() > 4) {
            int age = -1;
            Object o = null;
            final Enumeration<String> keys = (Enumeration<String>)this.cache.keys();
            while (keys.hasMoreElements()) {
                final String s = keys.nextElement();
                final CacheEntry cacheEntry = this.cache.get(s);
                if (cacheEntry.age >= age) {
                    age = cacheEntry.age;
                    o = s;
                }
            }
            if (age == 0) {
                break;
            }
            this.cache.remove(o);
        }
    }
    
    String[] getFilesToLoad() {
        final String[] array = new String[this.cache.size()];
        int n = 0;
        final Enumeration<String> keys = this.cache.keys();
        while (keys.hasMoreElements()) {
            final String s = keys.nextElement();
            final CacheEntry cacheEntry = this.cache.get(s);
            if (cacheEntry.age == 0 && !cacheEntry.loaded) {
                array[n] = s;
                ++n;
            }
        }
        if (n == 0) {
            return null;
        }
        final String[] array2 = new String[n];
        for (int i = 0; i < n; ++i) {
            array2[i] = array[i];
        }
        return array2;
    }
    
    void setCacheContents(final String s, final Vector data) {
        final CacheEntry cacheEntry = this.cache.get(s);
        cacheEntry.data = data;
        cacheEntry.loaded = true;
    }
    
    Vector getCachedData() {
        final Vector<Vector> vector = new Vector<Vector>();
        final int size = this.cache.size();
        int n = 0;
        final String[] array = new String[size];
        final Enumeration<String> keys = this.cache.keys();
        while (keys.hasMoreElements()) {
            String s;
            int n2;
            for (s = keys.nextElement(), n2 = 0; n2 < n && s.compareTo(array[n2]) <= 0; ++n2) {}
            for (int i = n; i > n2; --i) {
                array[i] = array[i - 1];
            }
            array[n2] = s;
            ++n;
        }
        for (int j = 0; j < n; ++j) {
            final CacheEntry cacheEntry = this.cache.get(array[j]);
            if (cacheEntry.age == 0 && cacheEntry.loaded && cacheEntry.data != null) {
                vector.addElement(cacheEntry.data);
            }
        }
        return vector;
    }
    
    class CacheEntry
    {
        Vector data;
        int age;
        boolean loaded;
    }
}

// 
// Decompiled by Procyon v0.5.30
// 

public class DateCache
{
    private int size;
    private int[] cache;
    private int[] age;
    private int currentAge;
    
    DateCache(final int size) {
        this.size = size;
        this.cache = new int[this.size * 3];
        this.age = new int[this.size];
        this.currentAge = 0;
        for (int i = 0; i < this.size; ++i) {
            this.age[i] = 0;
        }
    }
    
    private int ageCache() {
        if (this.currentAge > 500000) {
            for (int i = 0; i < this.size; ++i) {
                final int[] age = this.age;
                final int n = i;
                age[n] -= 500000;
            }
            this.currentAge = 0;
        }
        return ++this.currentAge;
    }
    
    private int findCachedIndex(final int n, final int n2) {
        for (int i = 0; i < this.size; ++i) {
            final int n3 = i * 3;
            if (this.cache[n3] == n && this.cache[n3 + 1] == n2) {
                this.age[i] = this.ageCache();
                return i;
            }
        }
        return -1;
    }
    
    public void add(final int n, final int n2, final int n3) {
        int cachedIndex = this.findCachedIndex(n, n2);
        if (cachedIndex == -1) {
            cachedIndex = 0;
            int n4 = this.age[0];
            for (int i = 1; i < this.size; ++i) {
                if (this.age[i] < n4) {
                    n4 = this.age[i];
                    cachedIndex = i;
                }
            }
            this.age[cachedIndex] = this.ageCache();
        }
        this.cache[cachedIndex * 3] = n;
        this.cache[cachedIndex * 3 + 1] = n2;
        this.cache[cachedIndex * 3 + 2] = n3;
    }
    
    public void remove(final int n, final int n2) {
        final int cachedIndex = this.findCachedIndex(n, n2);
        if (cachedIndex != -1) {
            this.cache[cachedIndex * 3] = 0;
            this.cache[cachedIndex * 3 + 1] = 0;
            this.age[cachedIndex] = 0;
        }
    }
    
    public int lookupDate(final TOC toc) {
        final int cachedIndex = this.findCachedIndex(toc.gridCol, toc.gridRow);
        if (cachedIndex != -1) {
            for (int i = 0; i < toc.numImg; ++i) {
                if (toc.scenes[i].date == this.cache[cachedIndex * 3 + 2]) {
                    return i;
                }
            }
        }
        return -1;
    }
    
    public void flush() {
        for (int i = 0; i < this.size; ++i) {
            this.cache[i * 3] = 0;
        }
    }
}

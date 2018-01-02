// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.kaha.impl.index.hash;

import java.io.IOException;
import org.apache.activemq.util.LinkedNode;

class HashBin
{
    private HashIndex hashIndex;
    private int id;
    private int maximumEntries;
    private int size;
    private int numberOfPages;
    private HashPageInfo root;
    private HashPageInfo tail;
    
    HashBin(final HashIndex hashIndex, final int id, final int maximumEntries) {
        this.numberOfPages = 0;
        this.root = null;
        this.tail = null;
        this.hashIndex = hashIndex;
        this.id = id;
        this.maximumEntries = maximumEntries;
    }
    
    @Override
    public String toString() {
        return "HashBin[" + this.getId() + "]";
    }
    
    @Override
    public boolean equals(final Object o) {
        boolean result = false;
        if (o instanceof HashBin) {
            final HashBin other = (HashBin)o;
            result = (other.id == this.id);
        }
        return result;
    }
    
    @Override
    public int hashCode() {
        return this.getId();
    }
    
    int getId() {
        return this.id;
    }
    
    void setId(final int id) {
        this.id = id;
    }
    
    boolean isEmpty() {
        return true;
    }
    
    int getMaximumEntries() {
        return this.maximumEntries;
    }
    
    void setMaximumEntries(final int maximumEntries) {
        this.maximumEntries = maximumEntries;
    }
    
    int size() {
        return this.size;
    }
    
    HashPageInfo addHashPageInfo(final long id, final int size) throws IOException {
        final HashPageInfo info = new HashPageInfo(this.hashIndex);
        info.setId(id);
        info.setSize(size);
        if (this.root == null) {
            this.root = info;
        }
        else {
            this.tail.linkAfter(info);
        }
        this.tail = info;
        ++this.numberOfPages;
        this.size += size;
        return info;
    }
    
    public HashEntry find(final HashEntry key) throws IOException {
        HashEntry result = null;
        try {
            int low = 0;
            int high = this.size() - 1;
            while (low <= high) {
                final int mid = low + high >> 1;
                final HashEntry te = this.getHashEntry(mid);
                final int cmp = te.compareTo(key);
                if (cmp == 0) {
                    result = te;
                    break;
                }
                if (cmp < 0) {
                    low = mid + 1;
                }
                else {
                    high = mid - 1;
                }
            }
        }
        finally {
            this.end();
        }
        return result;
    }
    
    boolean put(final HashEntry newEntry) throws IOException {
        boolean replace = false;
        try {
            int low = 0;
            int high = this.size() - 1;
            while (low <= high) {
                final int mid = low + high >> 1;
                final HashEntry midVal = this.getHashEntry(mid);
                final int cmp = midVal.compareTo(newEntry);
                if (cmp < 0) {
                    low = mid + 1;
                }
                else {
                    if (cmp <= 0) {
                        replace = true;
                        midVal.setIndexOffset(newEntry.getIndexOffset());
                        break;
                    }
                    high = mid - 1;
                }
            }
            if (!replace) {
                this.addHashEntry(low, newEntry);
                ++this.size;
            }
        }
        finally {
            this.end();
        }
        return replace;
    }
    
    HashEntry remove(final HashEntry entry) throws IOException {
        HashEntry result = null;
        try {
            int low = 0;
            int high = this.size() - 1;
            while (low <= high) {
                final int mid = low + high >> 1;
                final HashEntry te = this.getHashEntry(mid);
                final int cmp = te.compareTo(entry);
                if (cmp == 0) {
                    result = te;
                    this.removeHashEntry(mid);
                    --this.size;
                    break;
                }
                if (cmp < 0) {
                    low = mid + 1;
                }
                else {
                    high = mid - 1;
                }
            }
        }
        finally {
            this.end();
        }
        return result;
    }
    
    private void addHashEntry(final int index, final HashEntry entry) throws IOException {
        HashPageInfo pageToUse = null;
        int offset = 0;
        if (index >= this.getMaximumBinSize()) {
            while (index >= this.getMaximumBinSize()) {
                final HashPage hp = this.hashIndex.createPage(this.id);
                pageToUse = this.addHashPageInfo(hp.getId(), 0);
                pageToUse.setPage(hp);
            }
            offset = 0;
        }
        else {
            int count = 0;
            int countSoFar = 0;
            int pageNo = 0;
            for (HashPageInfo page = this.root; page != null; page = (HashPageInfo)page.getNext()) {
                count += page.size();
                pageToUse = page;
                if (index < count) {
                    offset = index - countSoFar;
                    break;
                }
                if (index == count && page.size() + 1 <= this.maximumEntries) {
                    offset = page.size();
                    break;
                }
                countSoFar += page.size();
                ++pageNo;
            }
            while (pageNo >= this.numberOfPages) {
                final HashPage hp2 = this.hashIndex.createPage(this.id);
                pageToUse = this.addHashPageInfo(hp2.getId(), 0);
            }
        }
        pageToUse.begin();
        pageToUse.addHashEntry(offset, entry);
        this.doOverFlow(index);
    }
    
    private HashEntry removeHashEntry(final int index) throws IOException {
        final HashPageInfo page = this.getRetrievePage(index);
        final int offset = this.getRetrieveOffset(index);
        final HashEntry result = page.removeHashEntry(offset);
        if (page.isEmpty()) {
            if (this.root.equals(page)) {
                this.root = (HashPageInfo)this.root.getNext();
            }
            if (this.tail.equals(page)) {
                this.tail = (HashPageInfo)page.getPrevious();
            }
            page.unlink();
            --this.numberOfPages;
            this.hashIndex.releasePage(page.getPage());
        }
        this.doUnderFlow(index);
        return result;
    }
    
    private HashEntry getHashEntry(final int index) throws IOException {
        final HashPageInfo page = this.getRetrievePage(index);
        page.begin();
        final int offset = this.getRetrieveOffset(index);
        final HashEntry result = page.getHashEntry(offset);
        return result;
    }
    
    private int getMaximumBinSize() {
        return this.maximumEntries * this.numberOfPages;
    }
    
    private HashPageInfo getRetrievePage(final int index) throws IOException {
        HashPageInfo result = null;
        int count = 0;
        for (HashPageInfo page = this.root; page != null; page = (HashPageInfo)page.getNext()) {
            count += page.size();
            result = page;
            if (index < count) {
                break;
            }
        }
        result.begin();
        return result;
    }
    
    private int getRetrieveOffset(final int index) throws IOException {
        int result = 0;
        int count = 0;
        for (HashPageInfo page = this.root; page != null; page = (HashPageInfo)page.getNext()) {
            if (index + 1 <= count + page.size()) {
                result = index - count;
                break;
            }
            count += page.size();
        }
        return result;
    }
    
    private void doOverFlow(final int index) throws IOException {
        final HashPageInfo info = this.getRetrievePage(index);
        if (info.size() > this.maximumEntries) {
            info.begin();
            final HashEntry entry = info.removeHashEntry(info.size() - 1);
            this.doOverFlow(this.getNextPage(info), entry);
        }
    }
    
    private void doOverFlow(final HashPageInfo next, final HashEntry entry) throws IOException {
        HashPageInfo info = null;
        if (next == null) {
            final HashPage page = this.hashIndex.createPage(this.id);
            info = this.addHashPageInfo(page.getId(), 0);
            info.setPage(page);
        }
        else {
            info = next;
        }
        info.begin();
        info.addHashEntry(0, entry);
        if (info.size() > this.maximumEntries) {
            final HashEntry overflowed = info.removeHashEntry(info.size() - 1);
            this.doOverFlow(this.getNextPage(info), overflowed);
        }
    }
    
    private HashPageInfo getNextPage(final HashPageInfo start) {
        return (HashPageInfo)start.getNext();
    }
    
    private void doUnderFlow(final int index) {
    }
    
    String dump() throws IOException {
        String str = "[" + this.numberOfPages + "]";
        for (HashPageInfo page = this.root; page != null; page = (HashPageInfo)page.getNext()) {
            page.begin();
            str += page.dump();
            page.end();
        }
        return str;
    }
    
    private void end() throws IOException {
        for (HashPageInfo page = this.root; page != null; page = (HashPageInfo)page.getNext()) {
            page.end();
        }
    }
}

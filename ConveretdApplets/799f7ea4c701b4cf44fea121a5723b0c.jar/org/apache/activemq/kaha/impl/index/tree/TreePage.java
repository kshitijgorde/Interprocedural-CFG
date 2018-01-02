// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.kaha.impl.index.tree;

import org.slf4j.LoggerFactory;
import java.util.Set;
import java.util.HashSet;
import java.util.Collection;
import java.io.DataInput;
import java.io.IOException;
import java.util.Iterator;
import java.io.DataOutput;
import org.apache.activemq.kaha.Marshaller;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;

class TreePage
{
    static final int PAGE_HEADER_SIZE = 18;
    private static final transient Logger LOG;
    private TreeIndex tree;
    private int maximumEntries;
    private long id;
    private long parentId;
    private boolean leaf;
    private List<TreeEntry> treeEntries;
    private long nextFreePageId;
    private boolean active;
    
    TreePage(final TreeIndex tree, final long id, final long parentId, final int maximumEntries) {
        this(maximumEntries);
        this.tree = tree;
        this.id = id;
        this.parentId = parentId;
    }
    
    public TreePage(final int maximumEntries) {
        this.parentId = -1L;
        this.leaf = true;
        this.nextFreePageId = -1L;
        this.active = true;
        this.maximumEntries = maximumEntries;
        this.treeEntries = new ArrayList<TreeEntry>(maximumEntries);
    }
    
    @Override
    public String toString() {
        return "TreePage[" + this.getId() + "]parent=" + this.getParentId();
    }
    
    @Override
    public boolean equals(final Object o) {
        boolean result = false;
        if (o instanceof TreePage) {
            final TreePage other = (TreePage)o;
            result = (other.id == this.id);
        }
        return result;
    }
    
    @Override
    public int hashCode() {
        return (int)this.id;
    }
    
    boolean isActive() {
        return this.active;
    }
    
    void setActive(final boolean active) {
        this.active = active;
    }
    
    long getNextFreePageId() {
        return this.nextFreePageId;
    }
    
    void setNextFreePageId(final long nextPageId) {
        this.nextFreePageId = nextPageId;
    }
    
    long getId() {
        return this.id;
    }
    
    void setId(final long id) {
        this.id = id;
    }
    
    void write(final Marshaller keyMarshaller, final DataOutput dataOut) throws IOException {
        this.writeHeader(dataOut);
        dataOut.writeInt(this.treeEntries.size());
        for (final TreeEntry entry : this.treeEntries) {
            entry.write(keyMarshaller, dataOut);
        }
    }
    
    void read(final Marshaller keyMarshaller, final DataInput dataIn) throws IOException {
        this.readHeader(dataIn);
        final int size = dataIn.readInt();
        this.treeEntries.clear();
        for (int i = 0; i < size; ++i) {
            final TreeEntry entry = new TreeEntry();
            entry.read(keyMarshaller, dataIn);
            this.treeEntries.add(entry);
        }
    }
    
    void readHeader(final DataInput dataIn) throws IOException {
        this.active = dataIn.readBoolean();
        this.leaf = dataIn.readBoolean();
        this.setParentId(dataIn.readLong());
        this.nextFreePageId = dataIn.readLong();
    }
    
    void writeHeader(final DataOutput dataOut) throws IOException {
        dataOut.writeBoolean(this.isActive());
        dataOut.writeBoolean(this.isLeaf());
        dataOut.writeLong(this.getParentId());
        dataOut.writeLong(this.nextFreePageId);
    }
    
    boolean isEmpty() {
        return this.treeEntries.isEmpty();
    }
    
    boolean isFull() {
        return this.treeEntries.size() >= this.maximumEntries;
    }
    
    boolean isRoot() {
        return this.getParentId() < 0L;
    }
    
    boolean isLeaf() {
        if (this.treeEntries.isEmpty()) {
            this.leaf = true;
        }
        return this.leaf;
    }
    
    boolean isUnderflowed() {
        return this.treeEntries.size() < this.maximumEntries / 2;
    }
    
    boolean isOverflowed() {
        return this.treeEntries.size() > this.maximumEntries;
    }
    
    void setLeaf(final boolean newValue) {
        this.leaf = newValue;
    }
    
    TreePage getParent() throws IOException {
        return this.tree.lookupPage(this.parentId);
    }
    
    long getParentId() {
        return this.parentId;
    }
    
    void setParentId(final long newId) throws IOException {
        if (newId == this.id) {
            throw new IllegalStateException("Cannot set page as a child of itself " + this + " trying to set parentId = " + newId);
        }
        this.parentId = newId;
        this.tree.writePage(this);
    }
    
    List<TreeEntry> getEntries() {
        return this.treeEntries;
    }
    
    void setEntries(final List<TreeEntry> newEntries) {
        this.treeEntries = newEntries;
    }
    
    int getMaximumEntries() {
        return this.maximumEntries;
    }
    
    void setMaximumEntries(final int maximumEntries) {
        this.maximumEntries = maximumEntries;
    }
    
    int size() {
        return this.treeEntries.size();
    }
    
    TreeIndex getTree() {
        return this.tree;
    }
    
    void setTree(final TreeIndex tree) {
        this.tree = tree;
    }
    
    void reset() throws IOException {
        this.treeEntries.clear();
        this.setParentId(-1L);
        this.setNextFreePageId(-1L);
        this.setLeaf(true);
    }
    
    public TreeEntry find(final TreeEntry key) throws IOException {
        int low = 0;
        int high = this.size() - 1;
        long pageId = -1L;
        while (low <= high) {
            final int mid = low + high >> 1;
            final TreeEntry te = this.getTreeEntry(mid);
            final int cmp = te.compareTo(key);
            if (cmp == 0) {
                return te;
            }
            if (cmp < 0) {
                low = mid + 1;
                pageId = te.getNextPageId();
            }
            else {
                high = mid - 1;
                pageId = te.getPrevPageId();
            }
        }
        final TreePage page = this.tree.lookupPage(pageId);
        if (page != null) {
            return page.find(key);
        }
        return null;
    }
    
    TreeEntry put(final TreeEntry newEntry) throws IOException {
        TreeEntry result = null;
        if (this.isRoot()) {
            if (this.isEmpty()) {
                this.insertTreeEntry(0, newEntry);
            }
            else {
                result = this.doInsert(null, newEntry);
            }
            return result;
        }
        throw new IllegalStateException("insert() should not be called on non root page - " + this);
    }
    
    TreeEntry remove(final TreeEntry entry) throws IOException {
        TreeEntry result = null;
        if (this.isRoot()) {
            if (!this.isEmpty()) {
                result = this.doRemove(entry);
            }
            return result;
        }
        throw new IllegalStateException("remove() should not be called on non root page");
    }
    
    private TreeEntry doInsert(final Flavour flavour, final TreeEntry newEntry) throws IOException {
        TreeEntry result = null;
        final TreePageEntry closest = this.findClosestEntry(newEntry);
        if (closest != null) {
            final TreeEntry closestEntry = closest.getTreeEntry();
            final TreePage closestPage = closest.getTreePage();
            final int cmp = closestEntry.compareTo(newEntry);
            if (cmp == 0) {
                final long oldValue = closestEntry.getIndexOffset();
                closestEntry.setIndexOffset(newEntry.getIndexOffset());
                newEntry.setIndexOffset(oldValue);
                result = newEntry;
                this.save();
            }
            else if (closestPage != null) {
                result = closestPage.doInsert(closest.getFlavour(), newEntry);
            }
            else if (!this.isFull()) {
                this.insertTreeEntry(closest.getIndex(), newEntry);
                this.save();
            }
            else {
                this.doOverflow(flavour, newEntry);
            }
        }
        else if (!this.isFull()) {
            this.doInsertEntry(newEntry);
            this.save();
        }
        else {
            this.doOverflow(flavour, newEntry);
        }
        return result;
    }
    
    private TreePage doOverflow(final Flavour flavour, final TreeEntry newEntry) throws IOException {
        TreePage result = this;
        if (!this.isFull()) {
            this.doInsertEntry(newEntry);
            this.save();
        }
        else if (!this.isRoot() && flavour != null) {
            this.doInsertEntry(newEntry);
            TreeEntry theEntry;
            if (flavour == Flavour.LESS) {
                theEntry = this.removeTreeEntry(0);
                theEntry.reset();
                theEntry.setNextPageId(this.getId());
            }
            else {
                theEntry = this.removeTreeEntry(this.size() - 1);
                theEntry.reset();
                theEntry.setPrevPageId(this.getId());
            }
            this.save();
            result = this.getParent().doOverflow(flavour, theEntry);
            if (!theEntry.equals(newEntry)) {
                result = this;
            }
        }
        else {
            this.doInsertEntry(newEntry);
            final int midIndex = this.size() / 2;
            final TreeEntry midEntry = this.removeTreeEntry(midIndex);
            final List<TreeEntry> subList = this.getSubList(midIndex, this.size());
            this.removeAllTreeEntries(subList);
            final TreePage newRoot = this.tree.createRoot();
            newRoot.setLeaf(false);
            this.setParentId(newRoot.getId());
            this.save();
            final TreePage rightPage = this.tree.createPage(newRoot.getId());
            rightPage.setEntries(subList);
            rightPage.checkLeaf();
            this.resetParentId(rightPage.getId(), rightPage.getEntries());
            midEntry.setNextPageId(rightPage.getId());
            midEntry.setPrevPageId(this.getId());
            newRoot.insertTreeEntry(0, midEntry);
            this.resetParentId(newRoot.getId(), newRoot.getEntries());
            this.save();
            rightPage.save();
            newRoot.save();
        }
        return result;
    }
    
    private TreeEntry doRemove(final TreeEntry entry) throws IOException {
        TreeEntry result = null;
        final TreePageEntry closest = this.findClosestEntry(entry);
        if (closest != null) {
            final TreeEntry closestEntry = closest.getTreeEntry();
            if (closestEntry != null) {
                final TreePage closestPage = closest.getTreePage();
                final int cmp = closestEntry.compareTo(entry);
                if (cmp == 0) {
                    result = closest.getTreeEntry();
                    final int index = closest.getIndex();
                    this.removeTreeEntry(index);
                    this.save();
                    this.doUnderflow(result, index);
                }
                else if (closestPage != null) {
                    closestPage.doRemove(entry);
                }
            }
        }
        return result;
    }
    
    private boolean doUnderflow() throws IOException {
        boolean result = false;
        int lastIndex;
        TreeEntry entry;
        for (boolean working = true; working && this.isUnderflowed() && !this.isEmpty() && !this.isLeaf(); working = this.doUnderflow(entry, lastIndex)) {
            lastIndex = this.size() - 1;
            entry = this.getTreeEntry(lastIndex);
        }
        if (this.isUnderflowed() && this.isLeaf()) {
            result = this.doUnderflowLeaf();
        }
        return result;
    }
    
    private boolean doUnderflow(final TreeEntry entry, final int index) throws IOException {
        boolean result = false;
        if (entry.getNextPageId() != -1L) {
            final TreePage page = this.tree.lookupPage(entry.getNextPageId());
            if (page != null && !page.isEmpty()) {
                final TreeEntry replacement = page.removeTreeEntry(0);
                final TreeEntry copy = replacement.copy();
                this.checkParentIdForRemovedPageEntry(copy, page.getId(), this.getId());
                if (!page.isEmpty()) {
                    copy.setNextPageId(page.getId());
                    page.setParentId(this.id);
                }
                else {
                    page.setLeaf(true);
                }
                final int replacementIndex = this.doInsertEntry(copy);
                if (page.doUnderflow()) {
                    this.resetPageReference(replacementIndex, copy.getNextPageId());
                    copy.setNextPageId(-1L);
                }
                else {
                    page.save();
                }
                this.save();
                result = true;
            }
        }
        if (entry.getPrevPageId() != -1L) {
            final TreeEntry prevEntry = (index > 0) ? this.getTreeEntry(index - 1) : null;
            if (prevEntry == null || prevEntry.getNextPageId() != entry.getPrevPageId()) {
                final TreePage page2 = this.tree.lookupPage(entry.getPrevPageId());
                if (page2 != null && !page2.isEmpty()) {
                    final TreeEntry replacement2 = page2.removeTreeEntry(page2.getEntries().size() - 1);
                    final TreeEntry copy2 = replacement2.copy();
                    this.checkParentIdForRemovedPageEntry(copy2, page2.getId(), this.getId());
                    if (!page2.isEmpty()) {
                        copy2.setPrevPageId(page2.getId());
                    }
                    else {
                        page2.setLeaf(true);
                    }
                    this.insertTreeEntry(index, copy2);
                    TreePage landed = null;
                    TreeEntry removed = null;
                    if (this.isOverflowed()) {
                        final TreePage parent = this.getParent();
                        if (parent != null) {
                            removed = this.getTreeEntry(0);
                            final Flavour flavour = this.getFlavour(parent, removed);
                            if (flavour == Flavour.LESS) {
                                removed = this.removeTreeEntry(0);
                                landed = parent.doOverflow(flavour, removed);
                            }
                            else {
                                removed = this.removeTreeEntry(this.size() - 1);
                                landed = parent.doOverflow(Flavour.MORE, removed);
                            }
                        }
                    }
                    if (page2.doUnderflow()) {
                        if (landed == null || landed.equals(this)) {
                            landed = this;
                        }
                        this.resetPageReference(copy2.getNextPageId());
                        landed.resetPageReference(copy2.getNextPageId());
                        copy2.setPrevPageId(-1L);
                        landed.save();
                    }
                    else {
                        page2.save();
                    }
                    this.save();
                    result = true;
                }
            }
        }
        if (!result) {
            this.save();
        }
        result |= this.doUnderflowLeaf();
        this.save();
        return result;
    }
    
    private boolean doUnderflowLeaf() throws IOException {
        boolean result = false;
        if (this.isUnderflowed() && this.isLeaf()) {
            final List<TreeEntry> list = new ArrayList<TreeEntry>(this.treeEntries);
            this.treeEntries.clear();
            for (final TreeEntry entry : list) {
                final TreePage parent = this.getParent();
                if (parent != null) {
                    final Flavour flavour = this.getFlavour(parent, entry);
                    final TreePage landedOn = parent.doOverflow(flavour, entry);
                    this.checkParentIdForRemovedPageEntry(entry, this.getId(), landedOn.getId());
                }
            }
            final TreePage parent2 = this.getParent();
            if (parent2 != null) {
                parent2.checkLeaf();
                parent2.removePageId(this.getId());
                parent2.doUnderflow();
                parent2.save();
                this.tree.releasePage(this);
                result = true;
            }
        }
        return result;
    }
    
    private Flavour getFlavour(final TreePage page, final TreeEntry entry) {
        Flavour result = null;
        if (page != null && !page.getEntries().isEmpty()) {
            final TreeEntry last = page.getEntries().get(page.getEntries().size() - 1);
            if (last.compareTo(entry) > 0) {
                result = Flavour.MORE;
            }
            else {
                result = Flavour.LESS;
            }
        }
        return result;
    }
    
    private void checkLeaf() {
        boolean result = false;
        for (final TreeEntry entry : this.treeEntries) {
            if (entry.hasChildPagesReferences()) {
                result = true;
                break;
            }
        }
        this.setLeaf(!result);
    }
    
    private void checkParentIdForRemovedPageEntry(final TreeEntry entry, final long oldPageId, final long newPageId) throws IOException {
        TreePage page = this.tree.lookupPage(entry.getPrevPageId());
        if (page != null && page.getParentId() == oldPageId) {
            page.setParentId(newPageId);
            page.save();
        }
        page = this.tree.lookupPage(entry.getNextPageId());
        if (page != null && page.getParentId() == oldPageId) {
            page.setParentId(newPageId);
            page.save();
        }
    }
    
    private void removePageId(final long pageId) {
        for (final TreeEntry entry : this.treeEntries) {
            if (entry.getNextPageId() == pageId) {
                entry.setNextPageId(-1L);
            }
            if (entry.getPrevPageId() == pageId) {
                entry.setPrevPageId(-1L);
            }
        }
    }
    
    private TreePageEntry findClosestEntry(final TreeEntry key) throws IOException {
        TreePageEntry result = null;
        TreeEntry treeEntry = null;
        Flavour flavour = null;
        long pageId = -1L;
        int low = 0;
        int high = this.size() - 1;
        int mid = low;
        while (low <= high) {
            mid = low + high >> 1;
            treeEntry = this.getTreeEntry(mid);
            final int cmp = treeEntry.compareTo(key);
            if (cmp < 0) {
                low = mid + 1;
                pageId = treeEntry.getNextPageId();
                flavour = Flavour.LESS;
            }
            else {
                if (cmp <= 0) {
                    low = mid;
                    break;
                }
                high = mid - 1;
                pageId = treeEntry.getPrevPageId();
                flavour = Flavour.MORE;
            }
        }
        if (treeEntry != null) {
            final TreePage treePage = this.tree.lookupPage(pageId);
            result = new TreePageEntry(treeEntry, treePage, flavour, low);
        }
        return result;
    }
    
    private int doInsertEntry(final TreeEntry newEntry) throws IOException {
        int low = 0;
        int high = this.size() - 1;
        while (low <= high) {
            final int mid = low + high >> 1;
            final TreeEntry midVal = this.getTreeEntry(mid);
            final int cmp = midVal.compareTo(newEntry);
            if (cmp < 0) {
                low = mid + 1;
            }
            else {
                if (cmp <= 0) {
                    continue;
                }
                high = mid - 1;
            }
        }
        this.insertTreeEntry(low, newEntry);
        return low;
    }
    
    private void insertTreeEntry(final int index, final TreeEntry entry) throws IOException {
        final int p = index - 1;
        final TreeEntry prevEntry = (p >= 0 && p < this.treeEntries.size()) ? this.treeEntries.get(p) : null;
        final TreeEntry nextEntry = (index >= 0 && index < this.treeEntries.size()) ? this.treeEntries.get(index) : null;
        if (prevEntry != null) {
            if (prevEntry.getNextPageId() == entry.getNextPageId()) {
                prevEntry.setNextPageId(-1L);
            }
            if (entry.getPrevPageId() == -1L) {
                entry.setPrevPageId(prevEntry.getNextPageId());
            }
        }
        if (nextEntry != null) {
            if (nextEntry.getPrevPageId() == entry.getPrevPageId()) {
                nextEntry.setPrevPageId(-1L);
            }
            if (entry.getNextPageId() == -1L) {
                entry.setNextPageId(nextEntry.getPrevPageId());
            }
        }
        this.addTreeEntry(index, entry);
    }
    
    private void resetPageReference(final int index, final long pageId) {
        final int p = index - 1;
        final TreeEntry prevEntry = (p >= 0 && p < this.treeEntries.size()) ? this.treeEntries.get(p) : null;
        final TreeEntry nextEntry = (index >= 0 && index < this.treeEntries.size()) ? this.treeEntries.get(index) : null;
        if (prevEntry != null && prevEntry.getNextPageId() == pageId) {
            prevEntry.setNextPageId(-1L);
        }
        if (nextEntry != null && nextEntry.getPrevPageId() == pageId) {
            nextEntry.setPrevPageId(-1L);
        }
    }
    
    private boolean resetPageReference(final long pageId) {
        boolean updated = false;
        for (final TreeEntry entry : this.treeEntries) {
            if (entry.getPrevPageId() == pageId) {
                entry.setPrevPageId(-1L);
                updated = true;
            }
            if (entry.getNextPageId() == pageId) {
                entry.setNextPageId(-1L);
                updated = true;
            }
        }
        return updated;
    }
    
    private void resetParentId(final long newParentId, final List<TreeEntry> entries) throws IOException {
        final Set<Long> set = new HashSet<Long>();
        for (final TreeEntry entry : entries) {
            if (entry != null) {
                set.add(entry.getPrevPageId());
                set.add(entry.getNextPageId());
            }
        }
        for (final Long pageId : set) {
            final TreePage page = this.tree.lookupPage(pageId);
            if (page != null) {
                page.setParentId(newParentId);
            }
        }
    }
    
    private void addTreeEntry(final int index, final TreeEntry entry) throws IOException {
        this.treeEntries.add(index, entry);
    }
    
    private TreeEntry removeTreeEntry(final int index) throws IOException {
        final TreeEntry result = this.treeEntries.remove(index);
        return result;
    }
    
    private void removeAllTreeEntries(final List<TreeEntry> c) {
        this.treeEntries.removeAll(c);
    }
    
    private List<TreeEntry> getSubList(final int from, final int to) {
        return new ArrayList<TreeEntry>(this.treeEntries.subList(from, to));
    }
    
    private TreeEntry getTreeEntry(final int index) {
        final TreeEntry result = this.treeEntries.get(index);
        return result;
    }
    
    void saveHeader() throws IOException {
        this.tree.writePage(this);
    }
    
    void save() throws IOException {
        this.tree.writeFullPage(this);
    }
    
    protected void dump() throws IOException {
        TreePage.LOG.info(this.toString());
        final Set<Long> set = new HashSet<Long>();
        for (final TreeEntry entry : this.treeEntries) {
            if (entry != null) {
                TreePage.LOG.info(entry.toString());
                set.add(entry.getPrevPageId());
                set.add(entry.getNextPageId());
            }
        }
        for (final Long pageId : set) {
            final TreePage page = this.tree.lookupPage(pageId);
            if (page != null) {
                page.dump();
            }
        }
    }
    
    static {
        LOG = LoggerFactory.getLogger(TreePage.class);
    }
    
    enum Flavour
    {
        LESS, 
        MORE;
    }
}

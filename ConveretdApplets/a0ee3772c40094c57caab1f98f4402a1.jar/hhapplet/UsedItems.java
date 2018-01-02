// 
// Decompiled by Procyon v0.5.30
// 

package hhapplet;

public class UsedItems
{
    UsedItem m_head;
    
    public boolean isCovered(final int n, final int n2) {
        return this.m_head.getNext() == null && this.m_head.getBegin() == n && this.m_head.getEnd() != n2;
    }
    
    public boolean isUsed(final int n) {
        if (this.m_head != null) {
            for (UsedItem usedItem = this.m_head; usedItem != null && usedItem.getBegin() <= n; usedItem = usedItem.getNext()) {
                if (usedItem.getEnd() >= n) {
                    return true;
                }
            }
        }
        return false;
    }
    
    public void getLimit(final int[] array, final int[] array2, final int[] array3, final int n) {
        UsedItem usedItem = null;
        Label_0070: {
            if (this.m_head != null) {
                UsedItem usedItem2 = this.m_head;
                while (true) {
                    while (usedItem2.getBegin() <= array[n]) {
                        usedItem = usedItem2;
                        usedItem2 = usedItem2.getNext();
                        if (usedItem2 == null) {
                            if (usedItem != null) {
                                array3[n] = usedItem.getEnd();
                            }
                            break Label_0070;
                        }
                    }
                    array2[n] = usedItem2.getBegin();
                    continue;
                }
            }
        }
        if (array3[n] >= array[n] || array2[n] <= array[n]) {
            array2[n] = (array3[n] = array[n]);
        }
    }
    
    public void addUsedItem(final int n, final int end) {
        if (this.m_head == null) {
            this.m_head = new UsedItem(n, end);
            return;
        }
        UsedItem usedItem = this.m_head;
        UsedItem usedItem2 = null;
        while (true) {
            while (usedItem.getBegin() <= n) {
                usedItem2 = usedItem;
                usedItem = usedItem.getNext();
                if (usedItem == null) {
                    if (usedItem == null && usedItem2 != null) {
                        usedItem2.setNext(new UsedItem(n, end));
                    }
                    if (usedItem2 != null && usedItem2.getEnd() == usedItem2.getNext().getBegin() - 1) {
                        usedItem2.setEnd(usedItem2.getNext().getEnd());
                        usedItem2.setNext(usedItem2.getNext().getNext());
                    }
                    return;
                }
            }
            if (usedItem.getBegin() == end + 1) {
                usedItem.setBegin(n);
                continue;
            }
            final UsedItem next = new UsedItem(usedItem.getBegin(), usedItem.getEnd());
            next.setNext(usedItem.getNext());
            usedItem.setBegin(n);
            usedItem.setEnd(end);
            usedItem.setNext(next);
            continue;
        }
    }
}

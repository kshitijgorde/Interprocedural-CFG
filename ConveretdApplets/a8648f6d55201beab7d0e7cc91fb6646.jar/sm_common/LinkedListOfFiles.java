// 
// Decompiled by Procyon v0.5.30
// 

package sm_common;

public class LinkedListOfFiles
{
    public LinkedListFileItem next;
    public LinkedListFileItem top;
    public LinkedListFileItem bottom;
    
    public LinkedListOfFiles() {
        this.top = new LinkedListFileItem();
        this.bottom = this.top;
    }
    
    public void add(final int newRank, final String newTitle, final String newDescriptionText, final String newLink) {
        final LinkedListFileItem newItem = new LinkedListFileItem();
        newItem.rank = newRank;
        newItem.titleText = newTitle;
        newItem.descriptionText = newDescriptionText;
        newItem.linkText = newLink;
        LinkedListFileItem index = this.top;
        LinkedListFileItem preIndex = this.top;
        while (index.rank > newRank) {
            preIndex = index;
            index = index.next;
        }
        if (index == this.top) {
            newItem.next = this.top;
            this.top = newItem;
        }
        else {
            newItem.next = index;
            preIndex.next = newItem;
        }
    }
    
    public int getItemCount() {
        LinkedListFileItem index;
        int count;
        for (index = this.top, count = 0; index.rank != -1; index = index.next, ++count) {}
        return count;
    }
    
    public void normalise() {
        LinkedListFileItem index = this.top;
        final int maxRank = this.top.rank;
        while (index.rank != -1) {
            index.rank = index.rank * 100 / maxRank;
            index = index.next;
        }
    }
    
    public LinkedListFileItem getItem(final int n) {
        LinkedListFileItem rtn;
        if (n < 1 || n > this.getItemCount()) {
            rtn = new LinkedListFileItem(-1, "Error: List Item requested is outside of list.", "Error: List Item requested is outside of list.", "Error: Item not in list");
        }
        else {
            LinkedListFileItem index = this.top;
            for (int i = 1; i != n; ++i) {
                index = index.next;
            }
            rtn = new LinkedListFileItem(index.rank, index.titleText, index.descriptionText, index.linkText);
        }
        return rtn;
    }
    
    public void printListToConsole() {
        for (LinkedListFileItem index = this.top; index.rank != -1; index = index.next) {
            System.out.println("=============Next Entry ===================");
            System.out.println("ranking:" + index.rank);
            System.out.println("link:" + index.linkText);
            System.out.println("titleText:" + index.titleText);
            System.out.println("descriptionText:" + index.descriptionText);
        }
    }
}

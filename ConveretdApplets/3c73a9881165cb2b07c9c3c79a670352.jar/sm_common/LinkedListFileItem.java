// 
// Decompiled by Procyon v0.5.30
// 

package sm_common;

public class LinkedListFileItem
{
    public LinkedListFileItem next;
    public String titleText;
    public String descriptionText;
    public String linkText;
    public int rank;
    
    public LinkedListFileItem() {
        this.rank = -1;
    }
    
    public LinkedListFileItem(final int newRank, final String newTitle, final String newDescriptionText, final String newLink) {
        this.rank = -1;
        this.titleText = newTitle;
        this.descriptionText = newDescriptionText;
        this.linkText = newLink;
        this.rank = newRank;
        this.next = null;
    }
}

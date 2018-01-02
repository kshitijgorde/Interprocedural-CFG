import java.util.Vector;

// 
// Decompiled by Procyon v0.5.30
// 

class MenuSet
{
    private Vector items;
    private int currentElement;
    private static int itemCount;
    
    public MenuSet() {
        this.items = new Vector();
    }
    
    public void addItem(final Item item) {
        ++MenuSet.itemCount;
        this.items.addElement(item);
    }
    
    public int getSize() {
        return this.items.size();
    }
    
    public Item getNextItem() {
        ++this.currentElement;
        return this.items.elementAt(this.currentElement() - 1);
    }
    
    public boolean hasMoreItems() {
        return this.currentElement < this.getSize();
    }
    
    public int currentElement() {
        return this.currentElement;
    }
    
    public void reset() {
        this.currentElement = 0;
    }
    
    public String toString() {
        return "MenuSet attributes\nSize=" + MenuSet.itemCount;
    }
    
    public void replaceItem(final String s, final Item item) {
        this.reset();
        while (this.hasMoreItems()) {
            final Item nextItem = this.getNextItem();
            if (nextItem.getText().equals(s)) {
                this.items.removeElement(nextItem);
                this.items.insertElementAt(item, this.currentElement - 1);
            }
        }
        this.reset();
    }
    
    public void removeItem(final String s) {
        this.reset();
        while (this.hasMoreItems()) {
            final Item nextItem = this.getNextItem();
            if (nextItem.getText().equals(s)) {
                this.items.removeElement(nextItem);
            }
        }
        this.reset();
    }
}

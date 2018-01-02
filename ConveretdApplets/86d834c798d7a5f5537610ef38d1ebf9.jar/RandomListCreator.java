import java.util.Vector;

// 
// Decompiled by Procyon v0.5.30
// 

class RandomListCreator
{
    int numEl;
    boolean created;
    Vector randomList;
    
    public RandomListCreator() {
        this.numEl = 10;
        this.created = false;
    }
    
    public RandomListCreator(final int numEl) {
        this.numEl = 10;
        this.created = false;
        this.numEl = numEl;
    }
    
    private void create() {
        if (this.randomList == null) {
            this.randomList = new Vector(this.numEl);
        }
        else {
            this.randomList.removeAllElements();
            this.randomList.ensureCapacity(this.numEl);
        }
        final Vector<Integer> vector = new Vector<Integer>(this.numEl);
        for (int i = 0; i < this.numEl; ++i) {
            vector.addElement(new Integer(i));
        }
        for (int j = this.numEl; j > 0; --j) {
            int k;
            for (k = j; k == j; k = (int)(Object)new Double(Math.floor(Math.random() * j))) {}
            this.randomList.addElement(vector.elementAt(k));
            vector.removeElementAt(k);
        }
        this.created = true;
    }
    
    public int getNumEl() {
        return this.numEl;
    }
    
    public Vector getRandomList() {
        if (!this.created) {
            this.create();
        }
        return this.randomList;
    }
    
    public void setNumEl(final int numEl) {
        this.numEl = numEl;
        this.created = false;
    }
}

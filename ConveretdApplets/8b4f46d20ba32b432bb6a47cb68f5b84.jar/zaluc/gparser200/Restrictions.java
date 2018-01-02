// 
// Decompiled by Procyon v0.5.30
// 

package zaluc.gparser200;

class Restrictions
{
    boolean noAlives;
    int cutoffYear;
    int incCount;
    int[] incList;
    int excCount;
    int[] excList;
    
    public Restrictions(final int n) {
        this.noAlives = false;
        if (n > 0) {
            this.incList = new int[n];
            this.excList = new int[n];
        }
    }
    
    public boolean valid() {
        return this.cutoffYear != 0 || (this.incCount == 0 && this.excCount == 0);
    }
    
    public void setCutoff(final int cutoffYear) {
        this.noAlives = true;
        this.cutoffYear = cutoffYear;
    }
    
    public void include(final int n) {
        this.incList[this.incCount++] = n;
    }
    
    public void exclude(final int n) {
        this.excList[this.excCount++] = n;
    }
    
    public boolean hide(final Person person) {
        boolean b;
        if (this.noAlives) {
            if (person.birth != null && person.birth.getYear() >= this.cutoffYear) {
                b = true;
                for (int i = 0; i < this.incCount; ++i) {
                    if (person.id == this.incList[i]) {
                        b = false;
                        break;
                    }
                }
            }
            else {
                b = false;
                for (int j = 0; j < this.excCount; ++j) {
                    if (person.id == this.excList[j]) {
                        b = true;
                        break;
                    }
                }
            }
        }
        else {
            b = false;
        }
        return b;
    }
}

// 
// Decompiled by Procyon v0.5.30
// 

public class suiCheckboxGroup
{
    suiCheckbox[] sch;
    int count;
    suiCheckbox curr;
    
    public void refresh() {
        for (int i = 0; i < this.count; ++i) {
            this.sch[i].check = false;
        }
        if (this.curr != null) {
            this.curr.check = true;
        }
        for (int j = 0; j < this.count; ++j) {
            this.sch[j].repaint();
        }
    }
    
    public suiCheckbox getCurrent() {
        return this.curr;
    }
    
    public void setCurrent(final suiCheckbox curr) {
        if (curr.schg == this) {
            for (int i = 0; i < this.count; ++i) {
                this.sch[i].check = false;
            }
            curr.check = true;
            this.curr = curr;
            for (int j = 0; j < this.count; ++j) {
                this.sch[j].repaint();
            }
        }
    }
    
    public suiCheckboxGroup() {
        this.sch = new suiCheckbox[20];
    }
}

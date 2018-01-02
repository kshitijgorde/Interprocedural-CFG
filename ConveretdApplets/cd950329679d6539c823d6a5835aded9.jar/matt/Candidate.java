// 
// Decompiled by Procyon v0.5.30
// 

package matt;

class Candidate
{
    public float value;
    public int count;
    
    Candidate() {
        this.value = 0.0f;
        this.count = 0;
    }
    
    public String toString() {
        return this.value + "\t" + this.count;
    }
}

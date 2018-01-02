import java.util.Vector;

// 
// Decompiled by Procyon v0.5.30
// 

class SetSolutionList
{
    public Vector solutionList;
    
    public SetSolutionList() {
        this.solutionList = new Vector();
    }
    
    public void setSolution(final SetSolution setSolution) {
        this.solutionList.addElement(setSolution);
    }
    
    public SetSolution getSolution(final int n) {
        if (n >= 0 && n < this.solutionList.size()) {
            return this.solutionList.elementAt(n);
        }
        return null;
    }
    
    public int getSize() {
        return this.solutionList.size();
    }
    
    public boolean isSolution(final int n, final int n2, final int n3) {
        for (int i = 0; i < this.solutionList.size(); ++i) {
            if (((SetSolution)this.solutionList.elementAt(i)).isSolution(n, n2, n3)) {
                return true;
            }
        }
        return false;
    }
}

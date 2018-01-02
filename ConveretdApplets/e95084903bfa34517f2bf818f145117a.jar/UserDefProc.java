import java.util.Vector;

// 
// Decompiled by Procyon v0.5.30
// 

class UserDefProc
{
    public static final String KEYPRESSED = "keypressed";
    public static final String MOUSECLICKED = "mouseclicked";
    public static final String MOUSEMOVED = "mousemoved";
    private boolean isOperator;
    private String identifier;
    private String[] inputIds;
    private InstList instList;
    private Vector localIds;
    
    public UserDefProc(final String identifier, final String[] inputIds, final InstList instList) {
        this.localIds = null;
        this.identifier = identifier;
        this.isOperator = false;
        this.inputIds = inputIds;
        this.instList = instList;
    }
    
    public void addLocalVar(final String s) {
        if (this.localIds == null) {
            this.localIds = new Vector(3, 3);
        }
        for (int size = this.localIds.size(), i = 0; i < size; ++i) {
            if (((String)this.localIds.elementAt(i)).equalsIgnoreCase(s)) {
                return;
            }
        }
        this.localIds.addElement(s);
    }
    
    public String getIdentifier() {
        return this.identifier;
    }
    
    public int getInputIndex(final String s) {
        if (this.inputIds != null) {
            for (int i = 0; i < this.inputIds.length; ++i) {
                if (this.inputIds[i].equalsIgnoreCase(s)) {
                    return i;
                }
            }
        }
        return -1;
    }
    
    public InstList getInstList() {
        return this.instList;
    }
    
    public int getLocalIndex(final String s) {
        if (this.localIds != null) {
            for (int size = this.localIds.size(), i = 0; i < size; ++i) {
                if (((String)this.localIds.elementAt(i)).equalsIgnoreCase(s)) {
                    return (this.inputIds == null) ? i : (this.inputIds.length + i);
                }
            }
        }
        return -1;
    }
    
    public boolean isOperator() {
        return this.isOperator;
    }
    
    public StringBuffer getHeaderText() {
        final StringBuffer sb = new StringBuffer(this.identifier);
        if (this.inputIds != null) {
            for (int i = 0; i < this.inputIds.length; ++i) {
                sb.append(" :");
                sb.append(this.inputIds[i]);
            }
        }
        return sb;
    }
    
    public int numInputs() {
        if (this.inputIds == null) {
            return 0;
        }
        return this.inputIds.length;
    }
    
    public int numVars() {
        int size = 0;
        if (this.localIds != null) {
            size = this.localIds.size();
        }
        return size + this.numInputs();
    }
    
    public void setIsOperator() {
        this.isOperator = true;
    }
    
    public String toString() {
        final StringBuffer sb = new StringBuffer("to ");
        sb.append(this.identifier);
        if (this.inputIds != null) {
            for (int i = 0; i < this.inputIds.length; ++i) {
                sb.append(" \"");
                sb.append(this.inputIds[i]);
            }
        }
        sb.append("\n");
        if (this.localIds != null && this.localIds.size() > 0) {
            sb.append("  local [");
            for (int j = 0; j < this.localIds.size(); ++j) {
                if (j > 0) {
                    sb.append(" ");
                }
                sb.append(this.localIds.elementAt(j));
            }
            sb.append("]\n");
        }
        final String string = this.instList.toString();
        if (string != null) {
            sb.append("  ");
            sb.append(string);
        }
        sb.append("  end\n");
        return sb.toString();
    }
}

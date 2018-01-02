import java.util.Enumeration;
import java.util.Vector;
import java.util.Hashtable;

// 
// Decompiled by Procyon v0.5.30
// 

public class Module
{
    private static final String CLASS_NAME = "Module";
    private Hashtable userDefProcDict;
    private Vector globalsList;
    
    public Module() {
        this.userDefProcDict = new Hashtable(120, 0.8f);
        this.globalsList = new Vector();
    }
    
    public void addGlobalVar(final GlobalVar globalVar) {
        this.globalsList.addElement(globalVar);
    }
    
    public void addUserDefProc(final UserDefProc userDefProc) {
        this.userDefProcDict.put(userDefProc.getIdentifier().toLowerCase(), userDefProc);
    }
    
    public void clear() {
        this.globalsList.removeAllElements();
        this.userDefProcDict.clear();
    }
    
    public GlobalVar getGlobalVar(final String s) {
        for (int size = this.globalsList.size(), i = 0; i < size; ++i) {
            final GlobalVar globalVar = this.globalsList.elementAt(i);
            if (s.equalsIgnoreCase(globalVar.getIdentifier())) {
                return globalVar;
            }
        }
        return null;
    }
    
    public VarRef globalVarRef(final String s) {
        for (int size = this.globalsList.size(), i = 0; i < size; ++i) {
            final GlobalVar globalVar = this.globalsList.elementAt(i);
            if (s.equalsIgnoreCase(globalVar.getIdentifier())) {
                return new VarRef(s, globalVar);
            }
        }
        return null;
    }
    
    public UserDefProc getUserDefProc(final String s) {
        final UserDefProc value = this.userDefProcDict.get(s.toLowerCase());
        if (value instanceof UserDefProc) {
            return value;
        }
        return null;
    }
    
    public StringBuffer[] getUserDefProcHeaders() {
        final StringBuffer[] array = new StringBuffer[this.userDefProcDict.size()];
        int n = 0;
        final Enumeration<String> keys = this.userDefProcDict.keys();
        while (keys.hasMoreElements()) {
            array[n++] = ((UserDefProc)this.userDefProcDict.get(keys.nextElement())).getHeaderText();
        }
        return array;
    }
    
    public boolean isGlobalVar(final String s) {
        return this.getGlobalVar(s) != null;
    }
}

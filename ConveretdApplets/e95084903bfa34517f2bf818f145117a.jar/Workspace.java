// 
// Decompiled by Procyon v0.5.30
// 

public class Workspace
{
    private static final String CLASS_NAME = "Workspace";
    private static Module module;
    
    public Workspace() {
        Workspace.module = new Module();
    }
    
    public static void addGlobalVar(final GlobalVar globalVar) {
        Workspace.module.addGlobalVar(globalVar);
    }
    
    public static void addUserDefProc(final UserDefProc userDefProc) {
        Workspace.module.addUserDefProc(userDefProc);
    }
    
    public static void clear() {
        Workspace.module.clear();
    }
    
    public static GlobalVar getGlobalVar(final String s) {
        return Workspace.module.getGlobalVar(s);
    }
    
    public static VarRef globalVarRef(final String s) {
        return Workspace.module.globalVarRef(s);
    }
    
    public static UserDefProc getUserDefProc(final String s) {
        return Workspace.module.getUserDefProc(s);
    }
    
    public static StringBuffer[] getUserDefProcHeaders() {
        return Workspace.module.getUserDefProcHeaders();
    }
    
    public static boolean isGlobalVar(final String s) {
        return Workspace.module.isGlobalVar(s);
    }
}

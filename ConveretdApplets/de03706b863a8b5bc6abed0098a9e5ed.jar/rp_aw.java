// 
// Decompiled by Procyon v0.5.30
// 

public enum rp_aw
{
    a("FS_PLAN_SAVE", 0), 
    b("FS_PLAN_LOAD", 1), 
    c("FS_GET_TREE", 2), 
    d("FS_GET_FOLDER_TREE", 3), 
    e("FS_GET_FOLDER_CONTENT", 4), 
    f("FS_GET_FAVORITES", 5), 
    g("FS_NEW_FOLDER", 6), 
    h("FS_DELETE", 7), 
    i("FS_RENAME", 8), 
    j("FS_MOVE", 9), 
    k("FS_MAIL", 10), 
    l("FS_REQ_INFO", 11);
    
    private static final /* synthetic */ rp_aw[] a;
    
    public static final rp_aw[] a() {
        return rp_aw.a.clone();
    }
    
    private rp_aw(final String s, final int n) {
    }
    
    static {
        a = new rp_aw[] { rp_aw.a, rp_aw.b, rp_aw.c, rp_aw.d, rp_aw.e, rp_aw.f, rp_aw.g, rp_aw.h, rp_aw.i, rp_aw.j, rp_aw.k, rp_aw.l };
    }
}

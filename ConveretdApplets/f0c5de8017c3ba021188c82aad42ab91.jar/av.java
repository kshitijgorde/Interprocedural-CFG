import java.util.Hashtable;

// 
// Decompiled by Procyon v0.5.30
// 

public class av
{
    public static en a;
    public static Hashtable b;
    
    public static final en a() {
        return av.a;
    }
    
    static {
        (av.a = new en("NetProperties")).a("NAME_HTTP_AE", new eo("NAME_HTTP_AE", "OV-AE", et.o, "String", 3));
        av.a.a("NAME_HTTP_CE", new eo("NAME_HTTP_CE", "OV-CE", et.o, "String", 3));
        av.a.a("NAME_HTTP_CE_LENGTH", new eo("NAME_HTTP_CE_LENGTH", "OV-CE-Length", et.o, "String", 3));
        av.a.a("NAME_HTTP_CL", new eo("NAME_HTTP_CL", "CL", et.o, "String", 3));
        av.a.a("NAME_HTTP_MUI", new eo("NAME_HTTP_MUI", "OV-MUI", et.o, "String", 3));
        av.a.a("NAME_HTTP_DIFF", new eo("NAME_HTTP_DIFF", "OV-DIFF", et.o, "String", 3));
        av.a.a("NAME_HTTP_DIFF_BACK", new eo("NAME_HTTP_DIFF_BACK", "Back", et.o, "String", 3));
        av.a.a("NAME_HTTP_DIFF_FRONT", new eo("NAME_HTTP_DIFF_FRONT", "Front", et.o, "String", 3));
        av.a.a("NAME_HTTP_DIFF_MIN", new eo("NAME_HTTP_DIFF_MIN", "Min", et.o, "String", 3));
        av.a.a("NAME_HTTP_DIFF_KEY", new eo("NAME_HTTP_DIFF_KEY", "Key", et.o, "String", 3));
        av.a.a("NAME_HTTP_UN", new eo("NAME_HTTP_UN", "UN", et.o, "String", 3));
        av.a.a("USE_HEADER_BUFFER", new eo("USE_HEADER_BUFFER", "", et.p, "Boolean", 2));
        av.a.a("MAX_GET_REQUEST_LENGTH", new eo("MAX_GET_REQUEST_LENGTH", "3000", et.j, "Integer", 2));
        av.a.a("RID", new eo("RID", "RID", et.o, "String", 2));
        av.a.a("ENABLE_POST", new eo("ENABLE_POST", "true", et.p, "Boolean", 1));
        (av.b = new Hashtable()).put("NAME_HTTP_AE", "Name of the accept-encoding attribute");
        av.b.put("NAME_HTTP_CE", "Name of the content-encoding attribute");
        av.b.put("NAME_HTTP_CE_LENGTH", "Name of the content-encoding-length attribute");
        av.b.put("NAME_HTTP_CL", "Name of the content-length attribute");
        av.b.put("NAME_HTTP_MUI", "Name of the minimum-update-interval attribute; used by mdg.push");
        av.b.put("NAME_HTTP_DIFF", "Name of the diff-strategy attribute; used by mdg.push");
        av.b.put("NAME_HTTP_DIFF_BACK", "Name of the back-strategy; used by mdg.push");
        av.b.put("NAME_HTTP_DIFF_FRONT", "Name of the front-strategy; used by mdg.push");
        av.b.put("NAME_HTTP_DIFF_MIN", "Name of the minimum-strategy; used by mdg.push");
        av.b.put("NAME_HTTP_DIFF_KEY", "Name of the key-strategy; used by mdg.push");
        av.b.put("NAME_HTTP_UN", "Name of the update-number attribute; used by mdg.push");
        av.b.put("USE_HEADER_BUFFER", "Indicates if HTTP-Headers can be read with (large) buffers");
        av.b.put("MAX_GET_REQUEST_LENGTH", "Maximum length of an URI");
        av.b.put("RID", "The name of the request id");
        av.b.put("ENABLE_POST", "Enables post for array requests");
    }
}

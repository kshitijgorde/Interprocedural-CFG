// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.dm.http;

import java.util.Date;
import java.util.Map;

public class DeviceFunctions
{
    public boolean processButn(final CookieHandler handler, final Map formFields) {
        handler.setCookie("", "SessId", formFields.get("sessId").toString(), "/", null, false);
        if (formFields.get("pass") != null && !formFields.get("pass").equals("")) {
            Authentication.setEncryptSeed(formFields.get("pass").toString(), formFields.get("param2").toString());
            if (!formFields.get("param1").equals("")) {
                formFields.put("digest", Authentication.chapDigest(formFields.get("id").toString(), formFields.get("pass").toString(), formFields.get("param1").toString()));
                formFields.put("pass", "NR");
                formFields.put("pass", "");
            }
            return true;
        }
        return false;
    }
}

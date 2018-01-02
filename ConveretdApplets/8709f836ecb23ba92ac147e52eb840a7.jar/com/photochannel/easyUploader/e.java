// 
// Decompiled by Procyon v0.5.30
// 

package com.photochannel.easyUploader;

import java.security.Permission;
import java.security.AllPermission;
import java.security.Permissions;
import java.security.PermissionCollection;
import java.security.CodeSource;
import java.net.URL;
import java.net.URLClassLoader;

public final class e extends URLClassLoader
{
    public e(final URL[] array) {
        super(array);
    }
    
    protected final PermissionCollection getPermissions(final CodeSource codeSource) {
        final Permissions permissions;
        (permissions = new Permissions()).add(new AllPermission());
        return permissions;
    }
}

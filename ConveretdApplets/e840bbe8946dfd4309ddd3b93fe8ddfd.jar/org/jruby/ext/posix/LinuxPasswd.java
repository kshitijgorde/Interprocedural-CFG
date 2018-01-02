// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.ext.posix;

import com.kenai.jaffl.Pointer;
import com.kenai.jaffl.struct.Struct;

public final class LinuxPasswd extends NativePasswd implements Passwd
{
    public final UTF8StringRef pw_name;
    public final UTF8StringRef pw_passwd;
    public final Signed32 pw_uid;
    public final Signed32 pw_gid;
    public final UTF8StringRef pw_gecos;
    public final UTF8StringRef pw_dir;
    public final UTF8StringRef pw_shell;
    
    public LinuxPasswd() {
        this.pw_name = new UTF8StringRef(this);
        this.pw_passwd = new UTF8StringRef(this);
        this.pw_uid = new Signed32(this);
        this.pw_gid = new Signed32(this);
        this.pw_gecos = new UTF8StringRef(this);
        this.pw_dir = new UTF8StringRef(this);
        this.pw_shell = new UTF8StringRef(this);
    }
    
    LinuxPasswd(final com.kenai.jaffl.Pointer memory) {
        this.pw_name = new UTF8StringRef(this);
        this.pw_passwd = new UTF8StringRef(this);
        this.pw_uid = new Signed32(this);
        this.pw_gid = new Signed32(this);
        this.pw_gecos = new UTF8StringRef(this);
        this.pw_dir = new UTF8StringRef(this);
        this.pw_shell = new UTF8StringRef(this);
        this.useMemory(memory);
    }
    
    public java.lang.String getAccessClass() {
        return "";
    }
    
    public java.lang.String getGECOS() {
        return this.pw_gecos.get();
    }
    
    public long getGID() {
        return this.pw_gid.get();
    }
    
    public java.lang.String getHome() {
        return this.pw_dir.get();
    }
    
    public java.lang.String getLoginName() {
        return this.pw_name.get();
    }
    
    public java.lang.String getPassword() {
        return this.pw_passwd.get();
    }
    
    public java.lang.String getShell() {
        return this.pw_shell.get();
    }
    
    public long getUID() {
        return this.pw_uid.get();
    }
    
    public int getPasswdChangeTime() {
        return 0;
    }
    
    public int getExpire() {
        return Integer.MAX_VALUE;
    }
}

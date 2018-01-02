// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.security;

import java.util.TimeZone;

public class User
{
    final String id;
    String sid;
    public static User defaultUserid;
    static final ThreadLocal<User> threadUser;
    
    static {
        User.defaultUserid = new User("system");
        threadUser = new ThreadLocal<User>();
    }
    
    public User(final String id) {
        this(id, null);
    }
    
    public User(final String id, final String sid) {
        this.sid = null;
        this.id = id;
        this.sid = sid;
    }
    
    public String getId() {
        return this.id;
    }
    
    public String getSessionId() {
        return this.sid;
    }
    
    public void setSessionId(final String sid) {
        this.sid = sid;
    }
    
    public TimeZone getTimeZone() {
        return TimeZone.getDefault();
    }
    
    public static User getCurrent() {
        final User user = User.threadUser.get();
        return (user == null) ? User.defaultUserid : user;
    }
    
    public static void setCurrent(final User user) {
        User.threadUser.set(user);
    }
    
    public static void setDefault() {
        final String user = System.getProperty("user.name");
        setDefault(new User(user));
    }
    
    public static void setDefault(final User user) {
        User.defaultUserid = user;
    }
}

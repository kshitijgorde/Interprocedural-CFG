// 
// Decompiled by Procyon v0.5.30
// 

package jlog.$T5.util;

import jlog.$H4;

public class $PSC implements $H4
{
    String list;
    char $HOD;
    
    public $PSC() {
        this.list = "";
        this.$HOD = ';';
        this.list = String.valueOf(this.list) + this.$HOD;
    }
    
    public $PSC(final String s, final char $hod) {
        this.list = "";
        this.$HOD = $hod;
        this.list = String.valueOf(this.list) + this.$HOD + s + this.$HOD;
    }
    
    public void append(String trim) {
        trim = trim.trim();
        if (trim.length() == 0) {
            return;
        }
        this.list = String.valueOf(this.list) + trim + this.$HOD;
    }
    
    public boolean contains(final String s) {
        return this.list.indexOf(String.valueOf(this.$HOD) + s + this.$HOD) != -1;
    }
    
    public String getList() {
        final int length = this.list.length();
        if (length == 1) {
            return "";
        }
        return this.list.substring(1, length - 1);
    }
    
    public void remove(String string) {
        string = String.valueOf(this.$HOD) + string + this.$HOD;
        final int index = this.list.indexOf(string);
        this.list = String.valueOf(this.list.substring(0, index + 1)) + this.list.substring(index + string.length());
    }
    
    public void replace(final String s, final String s2) {
        while (this.contains(s)) {
            this.remove(s);
            this.append(s2);
        }
    }
}

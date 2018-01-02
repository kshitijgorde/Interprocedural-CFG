// 
// Decompiled by Procyon v0.5.30
// 

package jchatbox.client.local;

import java.util.Vector;

public class ChatroomDesc
{
    private String _$9033;
    private String _$9034;
    private String _$9035;
    private String _$9036;
    private String _$9037;
    private String _$9038;
    private String _$9039;
    private String _$9040;
    private String _$495;
    private Vector _$9041;
    private Vector _$9042;
    
    public ChatroomDesc(final String $9033, final String $9034, final String $9035, final String $9036, final String $9037, final String $9038, final String $9039) {
        this._$9033 = null;
        this._$9034 = null;
        this._$9035 = null;
        this._$9036 = null;
        this._$9037 = null;
        this._$9038 = null;
        this._$9039 = null;
        this._$9040 = null;
        this._$495 = null;
        this._$9041 = null;
        this._$9042 = null;
        this._$9033 = $9033;
        this._$9034 = $9034;
        this._$9035 = $9035;
        this._$9036 = $9036;
        this._$9037 = $9037;
        this._$9038 = $9038;
        this._$9039 = $9039;
        this._$9041 = new Vector();
        this._$9042 = new Vector();
    }
    
    public String getDate() {
        return this._$9033;
    }
    
    public String getID() {
        return this._$9034;
    }
    
    public String getMaxUsers() {
        return this._$9035;
    }
    
    public String getName() {
        return this._$9036;
    }
    
    public String getSubject() {
        return this._$9037;
    }
    
    public String getTotalUsers() {
        return this._$9038;
    }
    
    public String getLanguage() {
        return this._$9039;
    }
    
    public String getPrivateMsg() {
        return this._$9040;
    }
    
    public String getRefresh() {
        return this._$495;
    }
    
    public Vector getMsgs() {
        return this._$9042;
    }
    
    public Vector getUserList() {
        return this._$9041;
    }
    
    public String toString() {
        return new String(String.valueOf(String.valueOf(new StringBuffer("DATE: ").append(this._$9033).append(" ID:").append(this._$9034).append(" MAXUSERS:").append(this._$9035).append(" NAME:").append(this._$9036).append(" SUBJECT:").append(this._$9037).append(" TOTALUSERS:").append(this._$9038).append(" PRIVATEMSG:").append(this._$9040).append(" REFRESH:").append(this._$495).append(" LANGUAGE:").append(this._$9039))));
    }
    
    public void setMaxUsers(final String $9035) {
        this._$9035 = $9035;
    }
    
    public void setTotalUsers(final String $9038) {
        this._$9038 = $9038;
    }
    
    public void setPrivateMsg(final String $9040) {
        this._$9040 = $9040;
    }
    
    public void setRefresh(final String $495) {
        this._$495 = $495;
    }
    
    public void setUserList(final Vector $9041) {
        this._$9041 = $9041;
    }
    
    public void setMsgs(final Vector $9042) {
        this._$9042 = $9042;
    }
}

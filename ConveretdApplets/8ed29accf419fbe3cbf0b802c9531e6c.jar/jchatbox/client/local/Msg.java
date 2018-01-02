// 
// Decompiled by Procyon v0.5.30
// 

package jchatbox.client.local;

import java.io.Serializable;

public class Msg implements Serializable
{
    public static final int SYSTEM = 0;
    public static final int JOIN = 1;
    public static final int LEFT = 2;
    public static final int PRIVATE = 3;
    public static final int KICK = 4;
    public static final int BAN = 5;
    public static final int WARNING = 6;
    public static final int NORMAL = 7;
    private String _$9753;
    private String _$9018;
    private String _$9754;
    private String _$9033;
    private int _$9755;
    
    public Msg(final String s, final String s2, final String s3, final String s4) {
        this(s, s2, s3, s4, 7);
    }
    
    public Msg(final String $9033, final String $9034, final String $9035, final String $9036, final int $9037) {
        this._$9753 = null;
        this._$9018 = null;
        this._$9754 = null;
        this._$9033 = null;
        this._$9755 = -1;
        this._$9033 = $9033;
        this._$9753 = $9034;
        this._$9018 = $9035;
        this._$9754 = $9036;
        this._$9755 = $9037;
    }
    
    public String getDate() {
        return this._$9033;
    }
    
    public String getFrom() {
        return this._$9753;
    }
    
    public String getMsg() {
        return this._$9754;
    }
    
    public String getTo() {
        return this._$9018;
    }
    
    public int getType() {
        return this._$9755;
    }
}

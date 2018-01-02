// 
// Decompiled by Procyon v0.5.30
// 

package COM.NextBus.Predictor2Comm;

import java.io.Serializable;

public final class TitleGroup implements Serializable
{
    private static final long serialVersionUID = 6207444062756779492L;
    private String _name;
    private int _id;
    private static TitleGroup a;
    private static TitleGroup b;
    private static TitleGroup c;
    private static TitleGroup d;
    private static TitleGroup e;
    private static TitleGroup f;
    private static TitleGroup g;
    
    private TitleGroup(final String name, final int id) {
        this._name = name;
        this._id = id;
    }
    
    public final String toString() {
        return "\"" + this._name + "\", " + this._id;
    }
    
    public final String a() {
        return this._name;
    }
    
    public final int hashCode() {
        return this._name.hashCode();
    }
    
    public final boolean equals(final Object o) {
        return o instanceof TitleGroup && this._name.equals(((TitleGroup)o)._name);
    }
    
    static {
        TitleGroup.a = new TitleGroup("Default", 0);
        TitleGroup.b = new TitleGroup("Wireless", 1);
        TitleGroup.c = new TitleGroup("InputPhonemes", 2);
        TitleGroup.d = new TitleGroup("OutputPhoneme", 3);
        TitleGroup.e = new TitleGroup("Long description", 4);
        TitleGroup.f = new TitleGroup("SignTitle", 5);
        TitleGroup.g = new TitleGroup("Nextstop", 6);
        final TitleGroup[] array = { TitleGroup.a, TitleGroup.b, TitleGroup.c, TitleGroup.d, TitleGroup.e, TitleGroup.f, TitleGroup.g };
    }
}

// 
// Decompiled by Procyon v0.5.30
// 

package wordle;

import javax.swing.AbstractButton;
import wordle.core.c.d;
import javax.swing.Action;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.ButtonGroup;
import javax.swing.JMenu;
import java.net.URL;
import java.io.InputStream;
import wordle.core.w;
import java.util.zip.GZIPInputStream;
import java.net.HttpURLConnection;
import javax.swing.JMenuItem;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Arrays;
import wordle.core.b.h;
import java.util.HashMap;
import java.util.Collections;
import java.util.HashSet;
import wordle.core.c.a;
import java.util.Map;
import java.util.Set;
import java.awt.Font;
import wordle.core.c.f;

final class M implements Runnable
{
    private /* synthetic */ R a;
    private final /* synthetic */ String b;
    private final /* synthetic */ String c;
    
    M(final R a, final String b, final String c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }
    
    public final void run() {
        final f a = this.a.h.a();
        final Font a2;
        if ((a2 = R.a(this.a, this.b)).equals(a.b.a)) {
            return;
        }
        R.b(this.a, this.c);
        this.a.h.a(new f("Change Font", a.b.a(a2), a.c, a.d.a(a2)));
    }
}

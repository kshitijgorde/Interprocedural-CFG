// 
// Decompiled by Procyon v0.5.30
// 

package wordle;

import javax.swing.AbstractButton;
import javax.swing.Action;
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
import javax.swing.JRadioButtonMenuItem;
import wordle.core.c.d;

final class O implements d
{
    private /* synthetic */ R a;
    private final /* synthetic */ JRadioButtonMenuItem b;
    private final /* synthetic */ String c;
    
    O(final R a, final JRadioButtonMenuItem b, final String c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }
    
    public final void a(final f f) {
        this.b.setSelected(this.c.equals(R.c(this.a, f.b.a.getName())));
    }
}

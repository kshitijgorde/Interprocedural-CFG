import java.lang.reflect.InvocationTargetException;
import com.daysofwonder.applet.af;
import com.daysofwonder.applet.aw;
import com.daysofwonder.tt.m;
import com.daysofwonder.applet.aq;
import com.daysofwonder.applet.n;
import com.daysofwonder.applet.an;
import com.daysofwonder.applet.e;
import com.daysofwonder.applet.aG;
import java.io.UnsupportedEncodingException;
import com.daysofwonder.util.w;
import com.daysofwonder.util.v;
import com.daysofwonder.applet.aA;
import com.daysofwonder.a.o;
import com.daysofwonder.applet.aE;
import com.daysofwonder.applet.ap;
import java.awt.Graphics;
import java.net.URISyntaxException;
import java.net.URI;
import com.daysofwonder.tt.p;
import javax.swing.JComponent;
import com.daysofwonder.applet.aM;
import javax.swing.SwingUtilities;
import java.util.List;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.Enumeration;
import com.daysofwonder.applet.J;
import com.daysofwonder.applet.c;
import java.io.IOException;
import java.net.MalformedURLException;
import com.daysofwonder.tt.b;
import com.daysofwonder.tt.l;
import java.util.Properties;
import com.daysofwonder.util.t;
import com.daysofwonder.util.i;
import com.daysofwonder.applet.ag;
import java.util.Hashtable;
import com.daysofwonder.util.UIProperties;
import java.net.URL;
import java.awt.Image;
import com.daysofwonder.util.q;
import com.daysofwonder.a.j;
import com.daysofwonder.applet.au;
import java.awt.Component;
import javax.swing.JOptionPane;

// 
// Decompiled by Procyon v0.5.30
// 

class al implements Runnable
{
    final /* synthetic */ StringBuffer a;
    final /* synthetic */ String b;
    final /* synthetic */ TTApplet c;
    
    al(final TTApplet c, final StringBuffer a, final String b) {
        this.c = c;
        this.a = a;
        this.b = b;
    }
    
    public void run() {
        JOptionPane.showMessageDialog(this.c, this.a.toString(), this.c.am.b(this.b), 0);
    }
}

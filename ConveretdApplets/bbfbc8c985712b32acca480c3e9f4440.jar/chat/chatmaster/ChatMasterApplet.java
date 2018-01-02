// 
// Decompiled by Procyon v0.5.30
// 

package chat.chatmaster;

import java.awt.Component;
import chat.aD;
import chat.cs;
import chat.bH;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import chat.y;
import chat.aV;
import java.awt.Color;
import chat.bB;
import java.io.IOException;
import chat.aS;
import java.io.InputStream;
import chat.br;
import java.util.zip.GZIPInputStream;
import java.net.URL;
import chat.bU;

public class ChatMasterApplet extends bU
{
    public final void a() {
        super.b.a();
        System.runFinalization();
        System.gc();
    }
    
    public void init() {
        bU.c = this.getParameter("altHost");
        final URL codeBase = this.getCodeBase();
        if ((bU.b = this.getParameter("language")) == null || bU.b.length() == 0) {
            bU.b = "eng.lang";
        }
        while (true) {
            if (!bU.b.equals("ar.lang")) {
                bU.b = "eng.lang";
                try {
                    try {
                        final aS as = new aS(new br(new GZIPInputStream(new URL(codeBase.toString() + "Resources/languages/" + bU.b + "z").openStream())));
                    }
                    catch (NoClassDefFoundError noClassDefFoundError) {
                        final aS as2 = new aS(new br(new URL(codeBase.toString() + "Resources/languages/" + bU.b).openStream()));
                    }
                    catch (IOException ex2) {
                        final aS as3 = new aS(new br(new URL(codeBase.toString() + "Resources/languages/" + bU.b).openStream()));
                    }
                }
                catch (Exception ex) {
                    ex.printStackTrace();
                }
                super.a = aS.a(548);
                System.out.println(super.a + " " + bB.a());
                Color background = Color.white;
                Color foreground = Color.black;
                final String parameter = this.getParameter("background");
                final String parameter2 = this.getParameter("textcolor");
                final String parameter3 = this.getParameter("themeID");
                final String parameter4 = this.getParameter("preferredPort");
                this.getParameter("COPPA1");
                this.getParameter("COPPA2");
                String s = this.getParameter("password");
                try {
                    if (s != null) {
                        final aV av;
                        (av = new aV()).a(s);
                        s = av.toString();
                    }
                }
                catch (Exception ex3) {}
                try {
                    if (parameter != null) {
                        background = chat.y.a(parameter);
                    }
                }
                catch (NumberFormatException ex4) {}
                try {
                    if (parameter2 != null) {
                        foreground = chat.y.a(parameter2);
                    }
                }
                catch (NumberFormatException ex5) {}
                try {
                    if (parameter3 != null) {
                        bU.c = Integer.parseInt(parameter3);
                    }
                }
                catch (NumberFormatException ex6) {}
                try {
                    if (parameter4 != null) {
                        bU.d = Integer.parseInt(parameter4);
                    }
                }
                catch (NumberFormatException ex7) {}
                this.setBackground(background);
                this.setForeground(foreground);
                this.setLayout(new BorderLayout());
                final String parameter5;
                if ((parameter5 = this.getParameter("embedded")) != null && parameter5.equalsIgnoreCase("true")) {
                    bU.a = true;
                }
                try {
                    final String parameter6;
                    if ((parameter6 = this.getParameter(bU.a ? "height" : "WindowHeight")) != null) {
                        bU.a = Integer.parseInt(parameter6);
                    }
                    final String parameter7;
                    if ((parameter7 = this.getParameter(bU.a ? "width" : "WindowWidth")) != null) {
                        bU.b = Integer.parseInt(parameter7);
                    }
                }
                catch (NumberFormatException ex8) {}
                bU.a = this;
                (super.a = new bH(this)).a(codeBase);
                if (cs.j == null) {
                    cs.j = super.a.a("icon_display.gif", false);
                }
                final String parameter8;
                if ((parameter8 = this.getParameter("RoomsTab")) != null && parameter8.equalsIgnoreCase("false")) {
                    super.a.k();
                }
                final String parameter9;
                if ((parameter9 = this.getParameter("UsersTab")) != null && parameter9.equalsIgnoreCase("false")) {
                    super.a.l();
                }
                super.b = new aD(super.a, super.a.d == null, s == null, false, super.a.v == -999, s);
                bU.a = super.b;
                this.add("Center", super.b);
                super.b.validate();
                return;
            }
            continue;
        }
    }
    
    public void stop() {
        if (super.a != null && (bU.a || "TRUE".equalsIgnoreCase(this.getParameter("BrowserClose")))) {
            super.a.j();
        }
        System.runFinalization();
        System.gc();
    }
}

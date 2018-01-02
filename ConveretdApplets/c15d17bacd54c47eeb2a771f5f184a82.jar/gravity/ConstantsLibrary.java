// 
// Decompiled by Procyon v0.5.30
// 

package gravity;

import java.awt.Color;
import gravity.tools.Vector2D;

public class ConstantsLibrary
{
    public final String APPLETINFO = "Deep Space Voyager GRAVITY for Java - JGravity\n\n(Z) 2000-2001 by Simon Buenzli (zeniko@gmx.ch)";
    public final String LAUNCHTEXT = "Launch!";
    public final String ABORTTEXT = "Abort!";
    public final String STARTTEXT = "Start...";
    public final String WELCOMEMESSAGE = "Welcome aboard D.S.V. Gravity!";
    public final String ABORTMESSAGE = "Game aborted... see you soon back aboard D.S.V. Gravity!";
    public final String INFTYMODE = "Infty-mode enabled...";
    public final Vector2D GRAVITYDIMENSION;
    public final double MAXPLANETSIZE = 7.0;
    public final double MAXPLANETNUMBER = 8.0;
    public final double MINATMOSPHERESIZE = 1.7;
    public final double MINDISTANCE = 17.5;
    public final double TIMEFRACTION = 0.035;
    public final double MINFORCE = 5.0E-4;
    public final double GRAVFIELDPATTERN = 0.8;
    public double PROBEINITIALSPEED;
    public int MAXPROBENUMBER;
    public int NUMBEROFPLANETS;
    public double SHIPSIZE;
    public double UFOSIZE;
    public double TARGETSIZE;
    public double GRAVCONSTANT;
    public boolean ISINFTYMODE;
    public boolean ISGRAVFIELD;
    public Vector2D VISIBLEORIGIN;
    public Vector2D VISIBLEDIMENSION;
    public final Color MAINFORECOLOR;
    public final Color MAINBACKCOLOR;
    public final Color[] PLANETCOLORS;
    public final Color[] TRACEDOTCOLORS;
    public final Color GAMEOBJECTCOLOR;
    public final Color SHIPCOLOR;
    public final Color ATMOSPHERECOLOR;
    public final Color SCANNINGATMOSPHERECOLOR;
    public final Color TARGETCOLOR;
    public final Color UFOCOLOR;
    
    public ConstantsLibrary() {
        this.GRAVITYDIMENSION = new Vector2D(130.0, 63.0);
        this.PROBEINITIALSPEED = 12.0;
        this.MAXPROBENUMBER = 10;
        this.NUMBEROFPLANETS = 4;
        this.SHIPSIZE = 4.0;
        this.UFOSIZE = 4.0;
        this.TARGETSIZE = 3.0;
        this.GRAVCONSTANT = 29.0;
        this.ISINFTYMODE = false;
        this.ISGRAVFIELD = false;
        this.VISIBLEORIGIN = new Vector2D(0.0, 0.0);
        this.VISIBLEDIMENSION = (Vector2D)this.GRAVITYDIMENSION.clone();
        this.MAINFORECOLOR = Color.black;
        this.MAINBACKCOLOR = Color.lightGray;
        this.PLANETCOLORS = new Color[] { new Color(255, 216, 200), new Color(208, 208, 255), new Color(216, 255, 255), new Color(224, 224, 224) };
        this.TRACEDOTCOLORS = new Color[] { new Color(192, 0, 0), new Color(100, 100, 255), new Color(255, 100, 100), new Color(164, 164, 255), new Color(255, 164, 164), new Color(216, 216, 255) };
        this.GAMEOBJECTCOLOR = new Color(192, 192, 192);
        this.SHIPCOLOR = this.GAMEOBJECTCOLOR;
        this.ATMOSPHERECOLOR = new Color(160, 160, 160);
        this.SCANNINGATMOSPHERECOLOR = new Color(0, 144, 255);
        this.TARGETCOLOR = new Color(176, 176, 176);
        this.UFOCOLOR = new Color(200, 184, 184);
    }
}

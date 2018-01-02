import java.awt.Font;
import java.awt.image.ImageObserver;
import java.awt.Color;
import java.awt.Graphics;
import java.util.Date;
import java.awt.Image;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

class Suncanvas extends Canvas
{
    String str;
    final double K = 0.017453292519943295;
    final char deg = '°';
    final int y90 = 114;
    final int y0 = 326;
    final int xS = 330;
    final int xLL = 60;
    final int xL = 30;
    final int unten = 380;
    final int mapOben = 460;
    final int mapRechts = 490;
    final double xFaktor = 1.2555555555555555;
    final double yFaktor = 1.2444444444444445;
    final int x0Map;
    final int y0Map;
    int Radius;
    int moonRadius;
    String s;
    int hInt;
    int azInt;
    int min;
    int iRise;
    int iSet;
    double xx;
    double STD;
    double equation;
    double trans;
    double diff;
    double hRise;
    double hSet;
    double civilRiseTime;
    double civilSetTime;
    double nautRiseTime;
    double nautSetTime;
    double astroRiseTime;
    double astroSetTime;
    double currentHoehe;
    Image myImage;
    Date dat;
    Compute comp;
    int locOffset;
    int year;
    int month;
    int date;
    int hours;
    int minutes;
    int seconds;
    double latitude;
    double longitude;
    double dec;
    double GHA;
    double hoehe;
    double azimut;
    String ghaStr1;
    String eqtStr;
    String riseStr;
    String setStr;
    String transStr;
    String locStr;
    String heightStr;
    String azStr;
    String consoleGHAStr;
    int[] hArray;
    int[] xArray;
    int xSun;
    int hSun;
    int n;
    boolean visible;
    boolean unvisible;
    String versStr;
    String[] hoeheStrArray;
    String[] azStrArray;
    String gmtStr;
    String declinStr;
    double moonH;
    double moonAz;
    int hMoon;
    int xMoon;
    int xMoonMap;
    String moonriseStr;
    String moonsetStr;
    double moon_GSW;
    double moon_Dec;
    String moonHeightStr;
    String moonAzStr;
    String moonGHAStr;
    String moonDeclinStr;
    double diffL;
    String moonAgeStr;
    String illFracStr;
    double illFrac;
    String usrString;
    Moon myMoon;
    MoonDistance myMoonDistance;
    String culmHoeheStr;
    String moonCulmStr;
    String moonCulmHeightStr;
    String lenDayStr;
    double lenDay;
    double moonAge;
    double sunDistance;
    double moonDistance;
    String sunDistanceStr;
    String sunDiameterStr;
    String moonDistanceStr;
    String moonDiameterStr;
    Date moonDate;
    String[] moonStrArray;
    String[] moonAzStrArray;
    double zWinkel;
    double q;
    double hourAngle;
    String sunriseAzStr;
    String sunsetAzStr;
    String moonriseAzStr;
    String moonsetAzStr;
    int altMouse;
    int azMouse;
    int xMouse;
    int yMouse;
    boolean hClick;
    double moonRiseTime;
    double moonSetTime;
    String diffLenDayStr;
    String civilRiseStr;
    String civilSetStr;
    String sunRAStr;
    String sunLStr;
    String moonLStr;
    String LMSTStr;
    String GMSTStr;
    String twilightRiseStr;
    String twilightSetStr;
    String nautRiseStr;
    String nautSetStr;
    String astroRiseStr;
    String astroSetStr;
    String minTransStr;
    double DEC;
    double currentJD;
    double phase;
    double YE;
    double zero1;
    double zero2;
    double UTRISE;
    double UTSET;
    int NZ;
    boolean ABOVE;
    boolean RISE;
    boolean SETT;
    double Y0;
    double yPlus;
    double yMinus;
    double DX;
    String consoleRiseStr;
    String consoleSetStr;
    String consoleTransStr;
    double civilTwilightDurationRise;
    double nautTwilightDurationRise;
    double astroTwilightDurationRise;
    double civilTwilightDurationSet;
    double nautTwilightDurationSet;
    double astroTwilightDurationSet;
    double moonAboveHorizon;
    int[] moonHArray;
    int[] moonAzArray;
    boolean showMoon;
    double moon_RA;
    String moonRAStr;
    String elongationStr;
    double equation0;
    double equation1;
    String deltaEoTStr;
    boolean moonCulmOK;
    PositionAngle pAngle;
    double appDelta;
    double alpha;
    String consoleMoonDeclinStr;
    String consoleMoonGHAStr;
    String eqtConsoleStr;
    String consoleDeclinStr;
    String[] dataStr;
    int kd;
    String[] monthArray;
    String moonAzAtSunsetStr;
    String moonElevAtSunsetStr;
    double sunSetAz;
    double dazSunset;
    double daltSunset;
    double sunRiseAz;
    double dazSunrise;
    String moonAzAtSunriseStr;
    String moonElevAtSunriseStr;
    double[] newMoonJD;
    double jdLastNew;
    double jdNextNew;
    String hourStr;
    double moonDiameter;
    String DazDaltStr;
    double[] hSetArray;
    int countSet;
    boolean drawFirst;
    double LHA;
    double LMST1;
    double GMST1;
    boolean nmo;
    String waxwanStr;
    String localDateStr;
    boolean analemmaOK;
    int[] analemmaX;
    int[] analemmaY;
    double diffLong;
    boolean showEqu;
    double PA;
    
    public Suncanvas(final Image bild, final Date myDate, final double myLat, final double myLong, final String myLoc, final int myLocOffset, final String vers, final String userString, final int x, final int y, final boolean horClick, final boolean moon, final boolean mydrawFirst, final boolean myNMO, final boolean anaOK, final boolean equ) {
        this.x0Map = (int)Math.round(226.0);
        this.y0Map = (int)Math.round(112.0) + 460;
        this.s = "";
        this.hArray = new int[26];
        this.xArray = new int[26];
        this.visible = false;
        this.unvisible = false;
        this.hoeheStrArray = new String[26];
        this.azStrArray = new String[26];
        this.moonriseStr = "?";
        this.moonsetStr = "?";
        this.moonHeightStr = "?";
        this.moonAzStr = "?";
        this.moonGHAStr = "?";
        this.moonDeclinStr = "?";
        this.moonAgeStr = "?";
        this.illFracStr = "???";
        this.moonStrArray = new String[30];
        this.moonAzStrArray = new String[30];
        this.consoleRiseStr = "?";
        this.consoleSetStr = "?";
        this.consoleTransStr = "?";
        this.moonHArray = new int[30];
        this.moonAzArray = new int[30];
        this.moonCulmOK = true;
        this.dataStr = new String[300];
        this.kd = 52;
        this.monthArray = new String[] { "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec" };
        this.newMoonJD = new double[17];
        this.hSetArray = new double[30];
        this.countSet = 0;
        this.analemmaX = new int[370];
        this.analemmaY = new int[370];
        this.myImage = bild;
        this.versStr = vers;
        this.usrString = userString;
        this.showMoon = moon;
        this.analemmaOK = anaOK;
        this.xMouse = x;
        this.yMouse = y;
        this.hClick = horClick;
        this.drawFirst = mydrawFirst;
        this.nmo = myNMO;
        this.showEqu = equ;
        this.latitude = myLat;
        this.longitude = myLong;
        this.locStr = myLoc;
        this.dat = myDate;
        this.hours = this.dat.getHours();
        this.hours = myDate.getHours();
        this.date = this.dat.getDate();
        this.month = this.dat.getMonth();
        this.minutes = this.dat.getMinutes();
        this.year = this.dat.getYear();
        this.locOffset = myLocOffset;
        this.STD = this.hours - this.locOffset + this.minutes / 60.0;
        this.comp = new Compute();
        this.currentJD = this.comp.JD(this.date, this.month + 1, this.year + 1900, this.STD);
        this.currentJD = this.comp.JD(this.date, this.month + 1, this.year + 1900, this.STD);
        this.dataNewMoon(false);
        this.moonAge = this.currentJD - this.jdLastNew;
        this.moonAgeStr = String.valueOf(this.comp.DaysHoursMinutes(this.moonAge));
        this.myMoon = new Moon(this.date, this.month, this.year, this.hours + this.minutes / 60.0 - this.locOffset, this.latitude, this.longitude, this.locOffset);
        this.moonH = this.myMoon.moon_H();
        this.moonAz = this.myMoon.moon_Az();
        this.moon_GSW = this.myMoon.moonGSW();
        this.moon_Dec = this.myMoon.moonDEC();
        this.moonriseStr = this.myMoon.riseString();
        this.moonsetStr = this.myMoon.setString();
        if (this.moon_GSW < 0.0) {
            this.moon_GSW += 360.0;
        }
        this.moon_RA = this.myMoon.moonRA();
        if (this.moon_RA < 0.0) {
            this.moon_RA += 24.0;
        }
        this.moonRAStr = "Right Asc. RA = " + Math.round(10000.0 * this.moon_RA * 15.0) / 10000.0 + '°' + " = " + this.HourMinSecStr(this.moon_RA * 15.0);
        this.moonRiseTime = this.myMoon.hMoonRise();
        this.moonSetTime = this.myMoon.hMoonSet();
        this.moonAboveHorizon = this.myMoon.hMoonSet() - this.myMoon.hMoonRise();
        if (this.moonAboveHorizon < 0.0) {
            this.moonAboveHorizon += 24.0;
        }
        this.LMST1 = 15.0 * this.myMoon.LMST(this.currentJD - 2400000.5, this.longitude);
        this.GMST1 = 15.0 * this.myMoon.GMST(this.currentJD - 2400000.5);
        this.GHA = this.comp.computeGHA(this.date, this.month + 1, this.year + 1900, this.STD);
        this.PA = Math.asin(Math.cos(0.017453292519943295 * this.latitude) * Math.sin(0.017453292519943295 * (this.GHA + this.longitude)) / Math.sin(0.017453292519943295 * (90.0 - this.currentHoehe))) / 0.017453292519943295;
        this.ghaStr1 = "GHA   = " + this.DegMinStr(this.GHA);
        this.consoleGHAStr = "GHA    = " + Math.round(100.0 * this.GHA) / 100.0 + '°' + " = " + this.DegMinStr(this.GHA);
        this.LHA = this.GHA + this.longitude;
        if (this.LHA < 0.0) {
            this.LHA += 360.0;
        }
        if (this.LHA > 360.0) {
            this.LHA -= 360.0;
        }
        this.equation = this.eot(this.date, this.month + 1, this.year + 1900, this.STD);
        this.equation0 = this.eot(this.date, this.month + 1, this.year + 1900, 12 + this.locOffset);
        this.equation1 = this.eot(this.date, this.month + 1, this.year + 1900, 12 + this.locOffset - 24);
        if (this.equation0 - this.equation1 > 0.0) {
            this.deltaEoTStr = " (+" + Math.round((this.equation0 - this.equation1) * 600.0) / 10.0 + "s)";
        }
        else {
            this.deltaEoTStr = " (" + Math.round((this.equation0 - this.equation1) * 600.0) / 10.0 + "s)";
        }
        this.diff = Math.abs(this.equation) - (int)Math.abs(this.equation);
        this.min = (int)Math.round(this.diff * 60.0);
        if (this.min == 60) {
            this.min = 0;
            if (this.equation >= 0.0) {
                ++this.equation;
            }
            else {
                --this.equation;
            }
        }
        if (this.min > 9) {
            this.s = ":";
        }
        else {
            this.s = ":0";
        }
        this.eqtStr = String.valueOf((int)this.equation) + this.s + this.min + " min ";
        if (this.equation < 0.0 && this.equation > -1.0) {
            this.eqtStr = "-" + this.eqtStr;
        }
        this.eqtConsoleStr = this.eqtStr;
        this.eqtStr = String.valueOf(this.eqtStr) + this.deltaEoTStr;
        final double GHA12 = this.comp.computeGHA(this.date, this.month + 1, this.year + 1900, 12.0);
        this.trans = 12.0 - GHA12 / 15.0 - this.longitude / 15.0 + this.locOffset;
        this.transStr = this.comp.makeTimeString("Transit  ", this.trans);
        this.hoehe = this.comp.computeHeight(this.DEC, this.latitude, this.longitude, this.GHA);
        this.heightStr = "Alt.= " + Math.round(100.0 * this.hoehe) / 100.0 + '°';
        this.currentHoehe = this.hoehe;
        this.azimut = this.comp.computeAzimut(this.DEC, this.latitude, this.longitude, this.GHA, this.hoehe);
        this.azStr = "Az.= " + Math.round(10.0 * this.azimut) / 10.0 + '°' + "  " + this.azDirection(this.azimut);
        this.moonAzStr = "Az.= " + Math.round(10.0 * this.moonAz) / 10.0 + '°' + "  " + this.azDirection(this.moonAz);
        String decNS = " N";
        if (this.moon_Dec <= 0.0) {
            decNS = " S";
        }
        this.moonDeclinStr = "Declin. = " + this.comp.DM(this.moon_Dec);
        this.consoleMoonDeclinStr = "Declin.= " + Math.round(10000.0 * Math.abs(this.moon_Dec)) / 10000.0 + '°' + decNS + " = " + this.comp.DM(this.moon_Dec);
        this.moonGHAStr = "GHA    = " + this.DegMinStr(this.moon_GSW) + " ";
        this.diffL = this.moon_GSW - this.GHA;
        if (this.diffL < 0.0) {
            this.diffL += 360.0;
        }
        this.sunDistance = this.myMoon.radius();
        double winkel = 1392000.0 / (this.sunDistance * 1.4959787E8);
        winkel = 180.0 * Math.atan(winkel) / 3.141592653589793;
        this.sunDiameterStr = ",  Diameter = " + Math.round(6000.0 * winkel) / 100.0 + "'";
        this.Radius = (int)Math.round(20.0 * winkel);
        this.myMoonDistance = new MoonDistance(this.date, this.month + 1, this.year, this.STD);
        this.moonDistance = this.myMoonDistance.compute();
        this.moonDistanceStr = "Distance from Earth = " + Math.round(this.moonDistance) + " km";
        this.moonDiameter = 7.169468E8 / this.moonDistance;
        final double horParal = 8.794 / (this.moonDistance / 1.4959787E8);
        this.moonDiameter *= 1.0 + Math.sin(0.017453292519943295 * this.moonH) * Math.sin(0.017453292519943295 * horParal / 3600.0);
        this.moonDiameter /= 60.0;
        this.moonRadius = (int)Math.round(20.0 * winkel);
        this.moonH -= this.comp.moonParal(this.moonH, this.moonDistance);
        this.moonH += this.comp.refract(this.moonH) / 60.0;
        this.moonHeightStr = "Alt.= " + Math.round(10.0 * this.moonH) / 10.0 + '°';
        final SunMoon sunmoon = new SunMoon(this.currentJD, 6);
        final double dL = sunmoon.lambda() - sunmoon.heliocentricLambda();
        this.diffLong = dL + 180.0;
        double geocentricElong = Math.cos(0.017453292519943295 * sunmoon.BETA()) * Math.cos(0.017453292519943295 * dL);
        geocentricElong = Math.acos(geocentricElong) / 0.017453292519943295;
        this.elongationStr = "Geocentric Elong. = " + Math.round(100.0 * geocentricElong) / 100.0 + '°';
        double DL = dL;
        if (dL < 0.0) {
            DL = dL + 360.0;
        }
        if (DL > 180.0) {
            this.waxwanStr = "(+)";
        }
        else {
            this.waxwanStr = "(-)";
        }
        final PlanetMoon pm = new PlanetMoon(this.currentJD);
        this.illFrac = pm.phase();
        this.illFracStr = "Illum. Frac.  = " + Math.round(1000.0 * this.illFrac) / 10.0 + " % " + this.waxwanStr;
        final double sun_AppL = this.sunAppL((this.currentJD - 2451545.0) / 36525.0);
        this.sunLStr = "Ecl. Long. L  = " + Math.round(10000.0 * sun_AppL) / 10000.0 + '°' + " = " + this.DegMinStr(sun_AppL) + " (" + this.tierkreisStr(sun_AppL) + " " + this.DegMinStr(sun_AppL % 30.0) + ")";
        if (this.appDelta > 0.0) {
            decNS = " N";
        }
        else {
            decNS = " S";
        }
        this.consoleDeclinStr = "Declin.= " + Math.round(10000.0 * Math.abs(this.appDelta)) / 10000.0 + '°' + decNS + " = " + this.DegMinStr(Math.abs(this.appDelta)) + decNS;
        this.declinStr = "Declin.= " + this.DegMinStr(Math.abs(this.appDelta)) + decNS;
        this.sunRAStr = "Right Asc. RA = " + Math.round(10000.0 * this.alpha) / 10000.0 + '°' + " = " + this.HourMinSecStr(this.alpha);
        this.pAngle = new PositionAngle(this.dat, this.latitude, this.longitude, this.locOffset);
        final double moonL = this.pAngle.MoonL();
        final double posAngle = this.pAngle.p_angle();
        this.moonLStr = "Ecl. Long. L  = " + Math.round(10000.0 * moonL) / 10000.0 + '°' + " = " + this.DegMinStr(moonL) + " (" + this.tierkreisStr(moonL) + " " + this.DegMinStr(moonL % 30.0) + ")";
        this.hourAngle = this.moon_GSW + this.longitude;
        this.q = Math.atan2(Math.sin(0.017453292519943295 * this.hourAngle), Math.tan(0.017453292519943295 * this.latitude) * Math.cos(0.017453292519943295 * this.moon_Dec) - Math.sin(0.017453292519943295 * this.moon_Dec) * Math.cos(0.017453292519943295 * this.hourAngle)) / 0.017453292519943295;
        this.zWinkel = posAngle - this.q;
        this.hSun = (int)Math.round(this.hoehe * 212.0 / 90.0);
        if (this.latitude >= this.DEC) {
            this.xx = (180.0 - this.azimut) * 270.0 / 180.0;
        }
        else if (this.azimut < 180.0) {
            this.xx = this.azimut * 270.0 / 180.0;
        }
        else {
            this.xx = -(360.0 - this.azimut) * 270.0 / 180.0;
        }
        if (this.latitude >= this.DEC) {
            this.xx = 330.0 - this.xx;
        }
        else {
            this.xx += 330.0;
        }
        this.xSun = (int)Math.round(this.xx);
        this.hMoon = (int)Math.round(this.moonH * 212.0 / 90.0);
        if (this.latitude >= 0.0) {
            this.xx = (180.0 - this.moonAz) * 270.0 / 180.0;
            this.xx = 330.0 - this.xx;
        }
        else {
            if (this.moonAz < 180.0) {
                this.xx = this.moonAz * 270.0 / 180.0;
            }
            else {
                this.xx = -(360.0 - this.moonAz) * 270.0 / 180.0;
            }
            this.xx += 330.0;
        }
        this.xMoon = (int)Math.round(this.xx);
        for (int what = 1; what < 5; ++what) {
            this.RISE = false;
            this.SETT = false;
            for (int i = -this.locOffset; i < -this.locOffset + 24; ++i) {
                this.riseset(this.date, this.month + 1, this.year + 1900, i, what);
                if (this.RISE && this.SETT) {
                    break;
                }
            }
            if (this.RISE || this.SETT) {
                if (this.RISE) {
                    this.UTRISE += this.locOffset;
                    if (this.UTRISE > 24.0) {
                        this.UTRISE -= 24.0;
                    }
                    if (this.UTRISE < 0.0) {
                        this.UTRISE += 24.0;
                    }
                }
                if (this.SETT) {
                    this.UTSET += this.locOffset;
                    if (this.UTSET > 24.0) {
                        this.UTSET -= 24.0;
                    }
                    if (this.UTSET < 0.0) {
                        this.UTSET += 24.0;
                    }
                }
                switch (what) {
                    case 1: {
                        if (this.RISE) {
                            this.hRise = this.UTRISE;
                        }
                        if (this.SETT) {
                            this.hSet = this.UTSET;
                        }
                        if (this.RISE) {
                            this.GHA = this.comp.computeGHA(this.date, this.month + 1, this.year + 1900, this.hRise - this.locOffset);
                            final double rsDec = this.comp.computeDeclination(this.date, this.month + 1, this.year + 1900, this.hRise - this.locOffset);
                            this.hoehe = this.comp.computeHeight(rsDec, this.latitude, this.longitude, this.GHA);
                            this.azimut = this.comp.computeAzimut(rsDec, this.latitude, this.longitude, this.GHA, this.hoehe);
                            this.sunriseAzStr = "Azim. = " + Math.round(10.0 * this.azimut) / 10.0 + '°';
                            this.consoleRiseStr = String.valueOf(this.comp.makeTimeString("Sunrise               ", this.hRise)) + "   " + this.sunriseAzStr;
                            this.consoleRiseStr = String.valueOf(this.consoleRiseStr) + this.azDirection(this.azimut);
                            this.riseStr = this.comp.makeTimeString("Rise    ", this.hRise);
                            if (this.nmo) {
                                this.sunRiseAz = this.azimut;
                                final double mazim = this.moon_Azim_Elev(1, this.comp.JD(this.date, this.month + 1, this.year + 1900, this.hRise - this.locOffset), this.latitude, this.longitude);
                                double melev = this.moon_Azim_Elev(2, this.comp.JD(this.date, this.month + 1, this.year + 1900, this.hRise - this.locOffset), this.latitude, this.longitude);
                                melev -= 0.5 * this.moonDiameter / 60.0;
                                melev -= this.comp.moonParal(melev, this.moonDistance);
                                melev += this.comp.refract(melev) / 60.0;
                                this.dazSunrise = mazim - this.sunRiseAz;
                                this.moonAzAtSunriseStr = new StringBuffer().append(Math.round(100.0 * mazim) / 100.0).append('°').toString();
                                this.moonElevAtSunriseStr = new StringBuffer().append(Math.round(100.0 * melev) / 100.0).append('°').toString();
                            }
                        }
                        else if (this.ABOVE) {
                            this.riseStr = "Visible all day";
                            this.setStr = "Visible all day";
                        }
                        else {
                            this.riseStr = "Unvisible all day";
                            this.setStr = "Unvisible all day";
                        }
                        if (this.SETT) {
                            this.GHA = this.comp.computeGHA(this.date, this.month + 1, this.year + 1900, this.hSet - this.locOffset);
                            final double rsDec = this.comp.computeDeclination(this.date, this.month + 1, this.year + 1900, this.hSet - this.locOffset);
                            this.hoehe = this.comp.computeHeight(rsDec, this.latitude, this.longitude, this.GHA);
                            this.azimut = this.comp.computeAzimut(rsDec, this.latitude, this.longitude, this.GHA, this.hoehe);
                            this.sunsetAzStr = "Azim. = " + Math.round(10.0 * this.azimut) / 10.0 + '°';
                            this.consoleSetStr = String.valueOf(this.comp.makeTimeString("Sunset                ", this.hSet)) + "   " + this.sunsetAzStr;
                            this.consoleSetStr = String.valueOf(this.consoleSetStr) + " " + this.azDirection(this.azimut);
                            this.setStr = this.comp.makeTimeString("Set     ", this.hSet);
                            if (this.nmo) {
                                this.sunSetAz = this.azimut;
                                final double mazim = this.moon_Azim_Elev(1, this.comp.JD(this.date, this.month + 1, this.year + 1900, this.hSet - this.locOffset), this.latitude, this.longitude);
                                double melev = this.moon_Azim_Elev(2, this.comp.JD(this.date, this.month + 1, this.year + 1900, this.hSet - this.locOffset), this.latitude, this.longitude);
                                melev -= 0.5 * this.moonDiameter / 60.0;
                                melev -= this.comp.moonParal(melev, this.moonDistance);
                                melev += this.comp.refract(melev) / 60.0;
                                this.daltSunset = melev;
                                this.dazSunset = mazim - this.sunSetAz;
                                this.moonAzAtSunsetStr = new StringBuffer().append(Math.round(100.0 * mazim) / 100.0).append('°').toString();
                                this.moonElevAtSunsetStr = new StringBuffer().append(Math.round(100.0 * melev) / 100.0).append('°').toString();
                                break;
                            }
                            break;
                        }
                        else {
                            if (this.ABOVE) {
                                this.riseStr = "Visible all day";
                                this.setStr = "Visible all day";
                                break;
                            }
                            this.riseStr = "Unvisible all day";
                            this.setStr = "Unvisible all day";
                            break;
                        }
                        break;
                    }
                    case 2: {
                        if (this.RISE) {
                            this.civilRiseTime = this.UTRISE;
                            this.civilTwilightDurationRise = this.hRise - this.UTRISE;
                        }
                        if (this.SETT) {
                            this.civilSetTime = this.UTSET;
                            this.civilTwilightDurationSet = this.UTSET - this.hSet;
                            break;
                        }
                        break;
                    }
                    case 3: {
                        if (this.RISE) {
                            this.nautRiseTime = this.UTRISE;
                            this.nautTwilightDurationRise = this.hRise - this.UTRISE;
                        }
                        if (this.SETT) {
                            this.nautSetTime = this.UTSET;
                            this.nautTwilightDurationSet = this.UTSET - this.hSet;
                            break;
                        }
                        break;
                    }
                    case 4: {
                        if (this.RISE) {
                            this.astroRiseTime = this.UTRISE;
                            this.astroTwilightDurationRise = this.hRise - this.UTRISE;
                        }
                        if (this.SETT) {
                            this.astroSetTime = this.UTSET;
                            this.astroTwilightDurationSet = this.UTSET - this.hSet;
                            break;
                        }
                        break;
                    }
                }
            }
            else {
                switch (what) {
                    case 1: {
                        if (this.ABOVE) {
                            this.riseStr = "Visible all day";
                            this.setStr = "Visible all day";
                            this.visible = true;
                        }
                        else {
                            this.riseStr = "Unvisible all day";
                            this.setStr = "Unvisible all day";
                            this.unvisible = true;
                        }
                        this.consoleRiseStr = this.riseStr;
                        this.consoleSetStr = this.setStr;
                        break;
                    }
                    case 2: {
                        if (this.ABOVE) {
                            this.civilRiseTime = 100.0;
                            this.civilSetTime = 100.0;
                            break;
                        }
                        this.civilRiseTime = 100.0;
                        this.civilSetTime = 100.0;
                        break;
                    }
                    case 3: {
                        if (this.ABOVE) {
                            this.nautRiseTime = 100.0;
                            this.nautSetTime = 100.0;
                            break;
                        }
                        this.nautRiseTime = 100.0;
                        this.nautSetTime = 100.0;
                        break;
                    }
                    case 4: {
                        if (this.ABOVE) {
                            this.astroRiseTime = 100.0;
                            this.astroSetTime = 100.0;
                            break;
                        }
                        this.astroRiseTime = 100.0;
                        this.astroSetTime = 100.0;
                        break;
                    }
                }
            }
        }
        for (int j = -this.locOffset; j < -this.locOffset + 25; ++j) {
            final double JD = this.comp.JD(this.date, this.month + 1, this.year + 1900, j);
            final double rsDec = this.comp.sunDecRA(1, JD);
            final double rsRA = this.comp.sunDecRA(2, JD);
            this.hoehe = this.comp.sun_elev(JD, this.latitude, -this.longitude, rsDec, rsRA);
            this.GHA = this.comp.sun_GHA(JD, rsRA);
            this.hArray[j + this.locOffset] = (int)Math.round(this.hoehe * 212.0 / 90.0);
            this.azimut = this.comp.computeAzimut(rsDec, this.latitude, this.longitude, this.GHA, this.hoehe);
            if (this.latitude >= rsDec) {
                this.xx = (180.0 - this.azimut) * 270.0 / 180.0;
            }
            else if (this.azimut < 180.0) {
                this.xx = this.azimut * 270.0 / 180.0;
            }
            else {
                this.xx = -(360.0 - this.azimut) * 270.0 / 180.0;
            }
            if (this.latitude >= rsDec) {
                this.xx = 330.0 - this.xx;
            }
            else {
                this.xx += 330.0;
            }
            this.xArray[j + this.locOffset] = (int)Math.round(this.xx);
            this.hoeheStrArray[j + this.locOffset] = String.valueOf(Math.round(10.0 * this.hoehe) / 10.0);
            this.azStrArray[j + this.locOffset] = String.valueOf(Math.round(10.0 * this.azimut) / 10.0);
        }
        this.GHA = this.comp.computeGHA(this.date, this.month + 1, this.year + 1900, this.trans - this.locOffset);
        final double culm_hoehe = this.comp.computeHeight(this.DEC, this.latitude, this.longitude, this.GHA);
        this.culmHoeheStr = "Altit.= " + Math.round(10.0 * culm_hoehe) / 10.0 + '°';
        double minTrans = this.trans + 12.0;
        if (minTrans > 24.0) {
            minTrans -= 24.0;
        }
        this.GHA = this.comp.computeGHA(this.date, this.month + 1, this.year + 1900, minTrans - this.locOffset);
        final double min_hoehe = this.comp.computeHeight(this.DEC, this.latitude, this.longitude, this.GHA);
        this.minTransStr = String.valueOf(this.comp.makeTimeString("Minimum altitude at   ", minTrans)) + "   " + Math.round(10.0 * min_hoehe) / 10.0 + '°';
        if (this.civilRiseTime == 100.0) {
            this.civilRiseStr = "Begin civil twilight";
        }
        else {
            this.civilRiseStr = this.comp.makeTimeString("Begin civil twilight  ", this.civilRiseTime);
        }
        if (this.civilSetTime == 100.0) {
            this.civilSetStr = "End civil twilight";
        }
        else {
            this.civilSetStr = this.comp.makeTimeString("End civil twilight    ", this.civilSetTime);
        }
        if (this.nautRiseTime == 100.0) {
            this.nautRiseStr = "Begin naut. twilight";
        }
        else {
            this.nautRiseStr = this.comp.makeTimeString("Begin naut. twilight  ", this.nautRiseTime);
        }
        if (this.nautSetTime == 100.0) {
            this.nautSetStr = "End naut. twilight";
        }
        else {
            this.nautSetStr = this.comp.makeTimeString("End naut. twilight    ", this.nautSetTime);
        }
        if (this.astroRiseTime == 100.0) {
            this.astroRiseStr = "Begin astr. twilight";
        }
        else {
            this.astroRiseStr = this.comp.makeTimeString("Begin astr. twilight  ", this.astroRiseTime);
        }
        if (this.astroSetTime == 100.0) {
            this.astroSetStr = "End astr. twilight  ";
        }
        else {
            this.astroSetStr = this.comp.makeTimeString("End astr. twilight    ", this.astroSetTime);
        }
        final double moonCulmTime = this.myMoon.transTime() + this.locOffset;
        this.moonCulmStr = this.comp.makeTimeString("Transit  ", moonCulmTime);
        if (moonCulmTime > 24.0 || moonCulmTime < 0.0) {
            this.moonCulmStr = "Transit    --:--";
            this.moonCulmOK = false;
        }
        double moonCulmH = this.myMoon.culmHeight();
        moonCulmH -= this.comp.moonParal(moonCulmH, this.moonDistance);
        if (this.moonCulmOK) {
            this.moonCulmHeightStr = "Altit.= " + Math.round(10.0 * moonCulmH) / 10.0 + '°';
        }
        else {
            this.moonCulmHeightStr = "Altit.= --";
        }
        double JD = this.comp.JD(this.date - 1, this.month + 1, this.year + 1900, 12.0);
        double declin = this.comp.sunDecRA(1, JD);
        double visible = Math.sin(0.017453292519943295 * declin) / Math.cos(0.017453292519943295 * this.latitude);
        if (Math.abs(visible) < 1.0) {
            this.lenDay = this.hSet - this.hRise;
        }
        else if (this.latitude > 0.0) {
            if (visible > 1.0) {
                this.lenDay = 24.0;
            }
            if (visible < -1.0) {
                this.lenDay = 0.0;
            }
        }
        else {
            if (visible > 1.0) {
                this.lenDay = 0.0;
            }
            if (visible < -1.0) {
                this.lenDay = 24.0;
            }
        }
        this.diff = 60.0 * (this.lenDay - (int)this.lenDay);
        this.lenDayStr = "Length of Day:   " + (int)this.lenDay + " h " + Math.round(10.0 * this.diff) / 10.0 + " min";
        this.RISE = false;
        this.SETT = false;
        for (int k = -this.locOffset; k < -this.locOffset + 24; ++k) {
            this.riseset(this.date - 1, this.month + 1, this.year + 1900, k, 1);
            if (this.RISE && this.SETT) {
                break;
            }
        }
        double prevDayLength = 0.0;
        JD = this.comp.JD(this.date - 1, this.month + 1, this.year + 1900, 12.0);
        declin = this.comp.sunDecRA(1, JD);
        visible = Math.sin(0.017453292519943295 * declin) / Math.cos(0.017453292519943295 * this.latitude);
        if (Math.abs(visible) < 1.0) {
            prevDayLength = this.UTSET - this.UTRISE;
        }
        else if (this.latitude > 0.0) {
            if (visible > 1.0) {
                prevDayLength = 24.0;
            }
            if (visible < -1.0) {
                prevDayLength = 0.0;
            }
        }
        else {
            if (visible > 1.0) {
                prevDayLength = 0.0;
            }
            if (visible < -1.0) {
                prevDayLength = 24.0;
            }
        }
        this.diff = 60.0 * (this.lenDay - prevDayLength);
        this.s = String.valueOf(Math.round(10.0 * Math.abs(this.diff)) / 10.0);
        if (this.diff > 0.0) {
            this.str = " + " + this.s + " min";
        }
        else {
            this.str = " - " + this.s + " min";
        }
        this.diffLenDayStr = " = length of prev. day " + this.str;
        if (this.myMoon.isMoonRise()) {
            (this.moonDate = new Date()).setHours((int)this.moonRiseTime);
            this.diff = this.moonRiseTime - (int)this.moonRiseTime;
            this.min = (int)Math.round(this.diff * 60.0);
            this.moonDate.setMinutes(this.min);
            this.moonDate.setSeconds(0);
            this.moonDate.setDate(this.date);
            this.moonDate.setMonth(this.month);
            this.moonDate.setYear(this.year);
            this.myMoon = new Moon(this.date, this.month, this.year, (int)this.moonRiseTime + this.min / 60.0 - this.locOffset, this.latitude, this.longitude, this.locOffset);
            this.moonH = this.myMoon.moon_H();
            this.moonAz = this.myMoon.moon_Az();
            this.moonriseAzStr = "Azim. = " + Math.round(10.0 * this.moonAz) / 10.0 + '°' + " " + this.azDirection(this.moonAz);
        }
        else {
            this.moonriseAzStr = "Azim. = --";
        }
        if (this.myMoon.isMoonSet()) {
            (this.moonDate = new Date()).setHours((int)this.moonSetTime);
            this.diff = this.moonSetTime - (int)this.moonSetTime;
            this.min = (int)Math.round(this.diff * 60.0);
            this.moonDate.setMinutes(this.min);
            this.moonDate.setSeconds(0);
            this.moonDate.setDate(this.date);
            this.moonDate.setMonth(this.month);
            this.moonDate.setYear(this.year);
            this.myMoon = new Moon(this.date, this.month, this.year, (int)this.moonSetTime + this.min / 60.0 - this.locOffset, this.latitude, this.longitude, this.locOffset);
            this.moonH = this.myMoon.moon_H();
            this.moonAz = this.myMoon.moon_Az();
            this.moonsetAzStr = "Azim. = " + Math.round(10.0 * this.moonAz) / 10.0 + '°' + " " + this.azDirection(this.moonAz);
        }
        else {
            this.moonsetAzStr = "Azim. =  --";
        }
        for (int l = -this.locOffset; l < -this.locOffset + 25 + 5; ++l) {
            (this.moonDate = new Date()).setHours(l + this.locOffset);
            this.moonDate.setMinutes(0);
            this.moonDate.setSeconds(0);
            this.moonDate.setDate(this.date);
            this.moonDate.setMonth(this.month);
            this.moonDate.setYear(this.year);
            this.myMoon = new Moon(this.date, this.month, this.year, l, this.latitude, this.longitude, this.locOffset);
            this.moonH = this.myMoon.moon_H();
            this.moonH -= this.comp.moonParal(this.moonH, this.moonDistance);
            this.moonStrArray[l + this.locOffset] = String.valueOf(Math.round(10.0 * this.moonH) / 10.0);
            this.moonHArray[l + this.locOffset] = (int)Math.round(this.moonH * 212.0 / 90.0);
            this.moonAz = this.myMoon.moon_Az();
            final double moonDec = this.myMoon.moonDEC();
            if (this.latitude >= moonDec) {
                this.xx = (180.0 - this.moonAz) * 270.0 / 180.0;
            }
            else if (this.moonAz < 180.0) {
                this.xx = this.moonAz * 270.0 / 180.0;
            }
            else {
                this.xx = -(360.0 - this.moonAz) * 270.0 / 180.0;
            }
            if (this.latitude >= moonDec) {
                this.xx = 330.0 - this.xx;
            }
            else {
                this.xx += 330.0;
            }
            this.moonAzArray[l + this.locOffset] = (int)Math.round(this.xx);
            this.moonAzStrArray[l + this.locOffset] = String.valueOf(Math.round(10.0 * this.moonAz) / 10.0);
        }
    }
    
    public String DegMinStr(double x) {
        final double diff = x - (int)x;
        double min = diff * 60.0;
        min = Math.round(min * 10.0) / 10.0;
        if (min == 60.0) {
            ++x;
            min = 0.0;
        }
        String str = new StringBuffer().append((int)x).append('°').toString();
        if (min < 10.0) {
            str = String.valueOf(str) + " 0" + min + "'";
        }
        else {
            str = String.valueOf(str) + " " + min + "'";
        }
        return str;
    }
    
    public String HourMinSecStr(final double x) {
        final double h = 24.0 * x / 360.0;
        final double diff = h - (int)h;
        final double min = diff * 60.0;
        double sec = (min - (int)min) * 60.0;
        String str = (int)h + "h";
        if (min < 10.0) {
            str = String.valueOf(str) + " 0" + (int)min + "m";
        }
        else {
            str = String.valueOf(str) + " " + (int)min + "m";
        }
        sec = Math.round(10.0 * sec) / 10.0;
        return String.valueOf(str) + " " + sec + "s";
    }
    
    public String tierkreisStr(final double longitudo) {
        final String[] tk = { "Aries", "Taurus", "Gemini", "Cancer", "Leo", "Virgo", "Libra", "Scorpio", "Sagittarius", "Capricornus", "Aquarius", "Pisces" };
        return tk[(int)(longitudo / 30.0)];
    }
    
    public void sunRiseSet(final int date, final int month, final int year) {
        this.RISE = false;
        this.SETT = false;
        double hRise = 0.0;
        double hSet = 0.0;
        double GHA = 0.0;
        for (int i = -this.locOffset; i < -this.locOffset + 24; ++i) {
            this.riseset(date, month + 1, year + 1900, i, 1);
            if (this.RISE && this.SETT) {
                break;
            }
        }
        if (this.RISE || this.SETT) {
            if (this.RISE) {
                this.UTRISE += this.locOffset;
                if (this.UTRISE > 24.0) {
                    this.UTRISE -= 24.0;
                }
                if (this.UTRISE < 0.0) {
                    this.UTRISE += 24.0;
                }
            }
            if (this.SETT) {
                this.UTSET += this.locOffset;
                if (this.UTSET > 24.0) {
                    this.UTSET -= 24.0;
                }
                if (this.UTSET < 0.0) {
                    this.UTSET += 24.0;
                }
            }
            if (this.RISE) {
                hRise = this.UTRISE;
            }
            if (this.SETT) {
                hSet = this.UTSET;
            }
            if (this.RISE) {
                GHA = this.comp.computeGHA(date, month + 1, year + 1900, hRise - this.locOffset);
                final double rsDec = this.comp.computeDeclination(date, month + 1, year + 1900, hRise - this.locOffset);
                final double hoehe = this.comp.computeHeight(rsDec, this.latitude, this.longitude, GHA);
                final double azimut = this.comp.computeAzimut(rsDec, this.latitude, this.longitude, GHA, hoehe);
                final double mazim = this.moon_Azim_Elev(1, this.comp.JD(date, month + 1, year + 1900, hRise - this.locOffset), this.latitude, this.longitude);
                double melev = this.moon_Azim_Elev(2, this.comp.JD(date, month + 1, year + 1900, hRise - this.locOffset), this.latitude, this.longitude);
                melev -= 0.5 * this.moonDiameter / 60.0;
                melev -= this.comp.moonParal(melev, this.moonDistance);
                melev += this.comp.refract(melev) / 60.0;
                final double dazSunrise = mazim - azimut;
            }
            else if (this.ABOVE) {
                this.riseStr = "Visible all day";
                this.setStr = "Visible all day";
            }
            else {
                this.riseStr = "Unvisible all day";
                this.setStr = "Unvisible all day";
            }
            if (this.SETT) {
                GHA = this.comp.computeGHA(date, month + 1, year + 1900, hSet - this.locOffset);
                final double rsDec = this.comp.computeDeclination(date, month + 1, year + 1900, hSet - this.locOffset);
                final double hoehe = this.comp.computeHeight(rsDec, this.latitude, this.longitude, GHA);
                final double azimut = this.comp.computeAzimut(rsDec, this.latitude, this.longitude, GHA, hoehe);
                this.hSetArray[this.countSet] = hSet - this.locOffset;
                this.setStr = this.comp.makeTimeString("Sunset  ", hSet);
                final double mazim = this.moon_Azim_Elev(1, this.comp.JD(date, month + 1, year + 1900, hSet - this.locOffset), this.latitude, this.longitude);
                final double moonAlt;
                double melev = moonAlt = this.moon_Azim_Elev(2, this.comp.JD(date, month + 1, year + 1900, hSet - this.locOffset), this.latitude, this.longitude);
                melev -= 0.5 * this.moonDiameter / 60.0;
                melev -= this.comp.moonParal(melev, this.moonDistance);
                final double daltSunset;
                melev = (daltSunset = melev + this.comp.refract(melev) / 60.0);
                final double dazSunset = mazim - azimut;
                String str1;
                for (str1 = new StringBuffer().append(Math.round(100.0 * dazSunset) / 100.0).append('°').toString(); str1.length() < 7; str1 = String.valueOf(str1) + " ") {}
                String str2;
                for (str2 = new StringBuffer().append(Math.round(100.0 * daltSunset) / 100.0).append('°').toString(); str2.length() < 7; str2 = String.valueOf(str2) + " ") {}
                final Date moonSetDat = new Date();
                moonSetDat.setDate(date);
                moonSetDat.setMonth(month);
                moonSetDat.setYear(year);
                this.myMoon = new Moon(date, month, year, 0.0, this.latitude, this.longitude, this.locOffset);
                this.moonsetStr = this.myMoon.setString();
                final double h_MoonSet = this.myMoon.hMoonSet();
                final String mSetStr = this.moonsetStr.substring(6, this.moonsetStr.length());
                final PlanetMoon pm = new PlanetMoon(this.comp.JD(date, month + 1, year + 1900, hSet - this.locOffset));
                this.illFrac = pm.phase();
                String str3;
                for (str3 = Math.round(10000.0 * this.illFrac) / 100.0 + "%"; str3.length() < 6; str3 = String.valueOf(str3) + " ") {}
                String str4;
                if (this.setStr.indexOf("i") == -1) {
                    for (str4 = Math.round((h_MoonSet - hSet) * 60.0) + "m"; str4.length() < 6; str4 = " " + str4) {}
                    str4 = "   lag" + str4;
                }
                else {
                    str4 = "    lag   -:-";
                }
                final double arcv = moonAlt - hoehe;
                String str5;
                for (str5 = new StringBuffer().append(Math.round(100.0 * arcv) / 100.0).append('°').toString(); str5.length() < 6; str5 = " " + str5) {}
                str5 = "  arcv " + str5;
                String str6;
                if (arcv > 10.3746 - 0.0137 * Math.abs(dazSunset) - 0.0097 * dazSunset * dazSunset) {
                    str6 = "   In +";
                }
                else {
                    str6 = "   In -";
                }
                this.DazDaltStr = "  " + this.setStr + "   " + str3 + "   Moon" + mSetStr + str4 + "   daz " + str1 + "  dalt " + str2 + str5 + str6;
            }
            else if (this.ABOVE) {
                this.riseStr = "Visible all day";
                this.setStr = "Visible all day";
            }
            else {
                this.riseStr = "Invisible all day";
                this.setStr = "Invisible all day";
            }
        }
        else {
            if (this.ABOVE) {
                this.riseStr = "Visible all day";
                this.setStr = "Visible all day";
                this.visible = true;
            }
            else {
                this.riseStr = "Invisible all day";
                this.setStr = "Invisible all day";
                this.unvisible = true;
            }
            if (this.setStr.indexOf("i") != -1 || this.setStr.indexOf("i") != -1) {
                this.DazDaltStr = "  Sunset   -:- ";
            }
        }
    }
    
    public double sunRise() {
        return this.UTRISE;
    }
    
    public double sunSet() {
        return this.UTSET;
    }
    
    public int calDat(final int what, final double jd) {
        final double JD0 = (int)(jd + 0.5);
        final int B = (int)((JD0 - 1867216.25) / 36524.25);
        final double C = JD0 + B - B / 4 + 1525.0;
        final int D = (int)((C - 122.1) / 365.25);
        final double E = 365.0 * D + D / 4;
        final int F = (int)((C - E) / 30.6001);
        final int day = (int)(C - E + 0.5) - (int)(30.6001 * F);
        final int month = F - 1 - 12 * (F / 14);
        final int year = D - 4715 - (7 + month) / 10;
        double hour = 24.0 * (jd + 0.5 - JD0);
        final double diff = Math.abs(hour) - (int)Math.abs(hour);
        int min = (int)Math.round(diff * 60.0);
        if (min == 60) {
            min = 0;
            ++hour;
        }
        String str;
        if (min > 9) {
            str = ":";
        }
        else {
            str = ":0";
        }
        this.hourStr = String.valueOf((int)hour) + str + min;
        if ((int)hour < 10) {
            this.hourStr = "0" + this.hourStr;
        }
        this.hourStr = String.valueOf(this.hourStr) + " UT";
        switch (what) {
            case 1: {
                return day;
            }
            case 2: {
                return month - 1;
            }
            case 3: {
                return year;
            }
            case 4: {
                return (int)Math.round(hour);
            }
            default: {
                return 0;
            }
        }
    }
    
    public double frac(double X) {
        X -= (int)X;
        if (X < 0.0) {
            ++X;
        }
        return X;
    }
    
    public double sunL(final double T) {
        double L = 280.46645 + 36000.76983 * T + 3.032E-4 * T * T;
        final double tau = T / 10.0;
        L = 280.4664567 + 360007.6982779 * tau + 0.03032028 * tau * tau + tau * tau * tau / 49931.0 - tau * tau * tau * tau / 15299.0 + tau * tau * tau * tau * tau / 1988000.0;
        L %= 360.0;
        if (L < 0.0) {
            L += 360.0;
        }
        return L;
    }
    
    public double sunAppL(final double T) {
        final double M = 357.5291 + 35999.0503 * T - 1.559E-4 * T * T - 4.8E-7 * T * T * T;
        final double C = (1.9146 - 0.004817 * T - 1.4E-5 * T * T) * Math.sin(0.017453292519943295 * M) + (0.019993 - 1.01E-4 * T) * Math.sin(0.03490658503988659 * M) + 2.9E-4 * Math.sin(0.05235987755982989 * M);
        final double L = 280.46645 + 36000.76983 * T + 3.032E-4 * T * T;
        double Theta = L + C;
        final double omega = 125.04 - 1934.136 * T;
        Theta = Theta - 0.00569 - 0.00478 * Math.sin(0.017453292519943295 * omega);
        Theta %= 360.0;
        if (Theta < 0.0) {
            Theta += 360.0;
        }
        double eps = 23.433333333333334 + (21.448 - 46.815 * T - 5.9E-4 * T * T + 0.001813 * T * T * T) / 3600.0;
        eps += 0.00256 * Math.cos(0.017453292519943295 * omega);
        final double sinDelta = Math.sin(0.017453292519943295 * eps) * Math.sin(0.017453292519943295 * Theta);
        this.appDelta = Math.asin(sinDelta) / 0.017453292519943295;
        this.alpha = Math.atan2(Math.cos(0.017453292519943295 * eps) * Math.sin(0.017453292519943295 * Theta), Math.cos(0.017453292519943295 * Theta)) / 0.017453292519943295;
        this.alpha %= 360.0;
        if (this.alpha < 0.0) {
            this.alpha += 360.0;
        }
        return Theta;
    }
    
    public double deltaPSI(final double T) {
        final double LS = this.sunAppL(T);
        double LM = 218.3165 + 481267.8813 * T;
        LM %= 360.0;
        if (LM < 0.0) {
            LM += 360.0;
        }
        final double omega = 125.04452 - 1934.136261 * T + 0.0020708 * T * T + T * T * T / 450000.0;
        final double deltaPsi = -17.2 * Math.sin(0.017453292519943295 * omega) - 1.32 * Math.sin(0.03490658503988659 * LS) - 0.23 * Math.sin(0.03490658503988659 * LM) + 0.21 * Math.sin(0.03490658503988659 * omega);
        return deltaPsi / 3600.0;
    }
    
    public double EPS(final double T) {
        final double LS = this.sunAppL(T);
        final double LM = 218.3165 + 481267.8813 * T;
        final double eps0 = 23.433333333333334 + (21.448 - 46.815 * T - 5.9E-4 * T * T + 0.001813 * T * T * T) / 3600.0;
        final double omega = 125.04452 - 1934.136261 * T + 0.0020708 * T * T + T * T * T / 450000.0;
        final double deltaEps = (9.2 * Math.cos(0.017453292519943295 * omega) + 0.57 * Math.cos(0.03490658503988659 * LS) + 0.1 * Math.cos(0.03490658503988659 * LM) - 0.09 * Math.cos(0.03490658503988659 * omega)) / 3600.0;
        return eps0 + deltaEps;
    }
    
    public double eot(final int date, final int month, final int year, final double UT) {
        final double T = (this.comp.JD(date, month, year, UT) - 2451545.0) / 36525.0;
        final double RA = this.RightAscension(T);
        double E = this.sunL(T) - 0.0057183 - RA + this.deltaPSI(T) * Math.cos(0.017453292519943295 * this.EPS(T));
        if (E > 5.0) {
            E -= 360.0;
        }
        return E * 4.0;
    }
    
    public double moon_Azim_Elev(final int what, final double JD, final double LAT, final double LONG) {
        final double P2 = 6.283185307179586;
        final double ARC = 206264.8062;
        final double coseps = 0.917482062;
        final double sineps = 0.397777156;
        final double T = (JD - 2451545.0) / 36525.0;
        final double L0 = this.frac(0.606433 + 1336.855225 * T);
        final double L2 = P2 * this.frac(0.374897 + 1325.55241 * T);
        final double LS = P2 * this.frac(0.993133 + 99.997361 * T);
        final double D = P2 * this.frac(0.827361 + 1236.853086 * T);
        final double F = P2 * this.frac(0.259086 + 1342.227825 * T);
        final double DL = 22640.0 * Math.sin(L2) - 4586.0 * Math.sin(L2 - 2.0 * D) + 2370.0 * Math.sin(2.0 * D) + 769.0 * Math.sin(2.0 * L2) - 668.0 * Math.sin(LS) - 412.0 * Math.sin(2.0 * F) - 212.0 * Math.sin(2.0 * L2 - 2.0 * D) - 206.0 * Math.sin(L2 + LS - 2.0 * D) + 192.0 * Math.sin(L2 + 2.0 * D) - 165.0 * Math.sin(LS - 2.0 * D) - 125.0 * Math.sin(D) - 110.0 * Math.sin(L2 + LS) + 148.0 * Math.sin(L2 - LS) - 55.0 * Math.sin(2.0 * F - 2.0 * D);
        final double S = F + (DL + 412.0 * Math.sin(2.0 * F) + 541.0 * Math.sin(LS)) / ARC;
        final double H = F - 2.0 * D;
        final double N = -526.0 * Math.sin(H) + 44.0 * Math.sin(L2 + H) - 31.0 * Math.sin(-L2 + H) - 23.0 * Math.sin(LS + H) + 11.0 * Math.sin(-LS + H) - 25.0 * Math.sin(-2.0 * L2 + F) + 21.0 * Math.sin(-L2 + F);
        final double L_Moon = P2 * this.frac(L0 + DL / 1296000.0);
        final double B_Moon = (18520.0 * Math.sin(S) + N) / ARC;
        final double CB = Math.cos(B_Moon);
        final double X = CB * Math.cos(L_Moon);
        final double V = CB * Math.sin(L_Moon);
        final double W = Math.sin(B_Moon);
        final double Y = coseps * V - sineps * W;
        final double Z = sineps * V + coseps * W;
        final double rho = Math.sqrt(1.0 - Z * Z);
        final double DEC = 57.29577951308232 * Math.atan(Z / rho);
        final double RA = 7.639437268410976 * Math.atan(Y / (X + rho));
        double THETA0 = 280.46061837 + 360.98564736629 * (JD - 2451545.0) + 3.87933E-4 * T * T - T * T * T / 3.871E7;
        THETA0 %= 360.0;
        if (THETA0 < 0.0) {
            THETA0 += 360.0;
        }
        final double h = THETA0 + LONG - 15.0 * RA;
        final double tau = 15.0 * (this.LMST(JD - 2400000.5, LONG) - RA);
        final double sinH = Math.cos(0.017453292519943295 * LAT) * Math.cos(0.017453292519943295 * DEC) * Math.cos(0.017453292519943295 * tau) + Math.sin(0.017453292519943295 * LAT) * Math.sin(0.017453292519943295 * DEC);
        if (what == 1) {
            return 180.0 + Math.atan2(Math.sin(0.017453292519943295 * h), Math.cos(0.017453292519943295 * h) * Math.sin(0.017453292519943295 * LAT) - Math.tan(0.017453292519943295 * DEC) * Math.cos(0.017453292519943295 * LAT)) / 0.017453292519943295;
        }
        return Math.asin(sinH) / 0.017453292519943295;
    }
    
    double LMST(final double MJD, final double LAMBDA) {
        final double MJD2 = (int)MJD;
        final double UT = (MJD - (int)MJD) * 24.0;
        final double T = (MJD2 - 51544.5) / 36525.0;
        final double gmst = 6.6973745583 + 1.0027379093 * UT + (8640184.812866 + (0.093104 - 6.2E-6 * T) * T) * T / 3600.0;
        return 24.0 * this.frac((gmst + LAMBDA / 15.0) / 24.0);
    }
    
    public double RightAscension(final double T) {
        final double L = this.sunL(T);
        double M = 357.5291 + 35999.0503 * T - 1.559E-4 * T * T - 4.8E-7 * T * T * T;
        M %= 360.0;
        if (M < 0.0) {
            M += 360.0;
        }
        double C = (1.9146 - 0.004817 * T - 1.4E-5 * T * T) * Math.sin(0.017453292519943295 * M);
        C += (0.019993 - 1.01E-4 * T) * Math.sin(0.03490658503988659 * M);
        C += 2.9E-4 * Math.sin(0.05235987755982989 * M);
        final double theta = L + C;
        double eps = this.EPS(T);
        eps += 0.00256 * Math.cos(0.017453292519943295 * (125.04 - 1934.136 * T));
        final double lambda = theta - 0.00569 - 0.00478 * Math.sin(0.017453292519943295 * (125.04 - 1934.136 * T));
        double RA = Math.atan2(Math.cos(0.017453292519943295 * eps) * Math.sin(0.017453292519943295 * lambda), Math.cos(0.017453292519943295 * lambda));
        RA /= 0.017453292519943295;
        if (RA < 0.0) {
            RA += 360.0;
        }
        double delta = Math.asin(Math.sin(0.017453292519943295 * eps) * Math.sin(0.017453292519943295 * lambda));
        delta /= 0.017453292519943295;
        this.DEC = delta;
        return RA;
    }
    
    public void paint(final Graphics g) {
        g.setColor(Color.black);
        g.drawString(this.elongationStr, 490, 625);
        final Font font = g.getFont();
        g.drawImage(this.myImage, 60, 90, this);
        if (this.showEqu) {
            g.setColor(Color.blue);
            final double w = 2.3555555555555556;
            for (int i = -90; i < 90; ++i) {
                final int A = (int)Math.round(1.5 * Math.atan(Math.tan(0.017453292519943295 * i) / Math.sin(0.017453292519943295 * this.latitude)) / 0.017453292519943295);
                final int A2 = (int)Math.round(1.5 * Math.atan(Math.tan(0.017453292519943295 * (i + 1)) / Math.sin(0.017453292519943295 * this.latitude)) / 0.017453292519943295);
                final int H = (int)Math.round(134.96339174192724 * Math.asin(Math.cos(0.017453292519943295 * this.latitude) * Math.cos(0.017453292519943295 * i)));
                final int H2 = (int)Math.round(134.96339174192724 * Math.asin(Math.cos(0.017453292519943295 * this.latitude) * Math.cos(0.017453292519943295 * (i + 1))));
                g.drawLine(330 - A, 326 - H, 330 - A2, 326 - H2);
            }
        }
        g.setFont(new Font("Helvetica", 0, 12));
        g.setColor(Color.red);
        g.drawLine(60, 90, 600, 90);
        if (this.latitude >= this.DEC) {
            g.drawString("S", 327, 108);
            this.n = (int)Math.round(135.0);
            g.drawString("E", 330 - this.n - 3, 108);
            g.drawString("W", 330 + this.n - 5, 108);
        }
        else {
            g.drawString("N", 327, 108);
            this.n = (int)Math.round(135.0);
            g.drawString("W", 330 - this.n - 5, 108);
            g.drawString("E", 330 + this.n - 3, 108);
        }
        g.setFont(font);
        this.gmtStr = this.myGMT();
        g.setColor(Color.blue);
        g.drawString(this.gmtStr, 175, 697);
        this.localDateStr = String.valueOf(this.comp.dayName(this.dat.getDay())) + " " + this.monthArray[this.month];
        if (this.date < 10) {
            this.localDateStr = String.valueOf(this.localDateStr) + " 0" + this.date + "  " + (this.year + 1900);
        }
        else {
            this.localDateStr = String.valueOf(this.localDateStr) + " " + this.date + "  " + (this.year + 1900);
        }
        if (this.dat.getHours() < 10) {
            this.str = "0" + this.dat.getHours();
        }
        else {
            this.str = String.valueOf(this.dat.getHours());
        }
        if (this.minutes < 10) {
            this.str = String.valueOf(this.str) + ":0" + this.minutes;
        }
        else {
            this.str = String.valueOf(this.str) + ":" + this.minutes;
        }
        if (this.seconds < 10) {
            this.str = String.valueOf(this.str) + ":0" + this.seconds;
        }
        else {
            this.str = String.valueOf(this.str) + ":" + this.seconds;
        }
        g.drawString(this.localDateStr, 335, 420);
        g.drawString(this.str, 445, 420);
        g.setColor(Color.red);
        g.drawString("SUN:", 490, 470);
        g.setColor(Color.black);
        g.drawString(this.declinStr, 490, 485);
        g.drawString(this.ghaStr1, 490, 500);
        g.drawString("Eq. of Time = " + this.eqtStr, 490, 515);
        if (this.currentHoehe > 0.0) {
            g.drawString("Shadow = " + Math.round(100.0 / Math.tan(0.017453292519943295 * this.currentHoehe)) / 100.0 + " m", 490, 530);
        }
        else {
            g.drawString("No Shadow", 490, 530);
        }
        g.setColor(Color.blue);
        g.drawString(this.locStr, 30, 420);
        g.setColor(Color.black);
        g.setColor(Color.red);
        g.drawString("SUN:    ", 30, 435);
        g.setColor(Color.black);
        g.drawString(this.riseStr, 75, 435);
        g.drawString(this.transStr, 155, 435);
        g.drawString(this.setStr, 240, 435);
        g.drawString(this.heightStr, 335, 435);
        g.drawString(this.azStr, 410, 435);
        g.drawString("PA= " + Math.round(10.0 * this.PA) / 10.0 + '°', 510, 435);
        if (326 - this.hSun < 400) {
            Color c = new Color(255, 255, 0);
            if (this.currentHoehe > -0.8) {
                c = new Color(255, 255, 0);
            }
            else if (this.currentHoehe < 0.0 && this.currentHoehe > -6.0) {
                c = new Color(255, 200, 0);
            }
            else if (this.currentHoehe < -6.0 && this.currentHoehe > -12.0) {
                c = new Color(255, 150, 0);
            }
            else if (this.currentHoehe < -12.0 && this.currentHoehe > -18.0) {
                c = new Color(255, 100, 0);
            }
            else if (this.currentHoehe < -18.0) {
                c = new Color(255, 50, 0);
            }
            g.setColor(c);
            g.fillOval(this.xSun - this.Radius, 326 - this.hSun - this.Radius, 2 * this.Radius, 2 * this.Radius);
            g.setColor(Color.red);
            g.drawOval(this.xSun - this.Radius, 326 - this.hSun - this.Radius, 2 * this.Radius, 2 * this.Radius);
            g.drawOval(this.xSun - 1, 326 - this.hSun - 1, 2, 2);
        }
        else {
            g.setColor(Color.white);
            g.drawString("SUN", this.xSun - 10, 390);
            g.drawString("V", this.xSun - 4, 400);
        }
        if (this.hClick && this.yMouse <= 400) {
            g.setColor(Color.green);
            g.drawLine(this.xMouse - 3, this.yMouse, this.xMouse + 3, this.yMouse);
            g.drawLine(this.xMouse, this.yMouse - 3, this.xMouse, this.yMouse + 3);
            g.setColor(Color.black);
            this.altMouse = (int)Math.round(90.0 * (326 - this.yMouse) / 212.0);
            this.azMouse = Math.round(180 + 180 * (this.xMouse - 330) / 270);
            g.drawString("Alt.=" + this.altMouse + '°', 10, this.yMouse - 7);
            if (this.latitude <= this.dec) {
                this.azMouse -= 180;
                if (this.azMouse < 0) {
                    this.azMouse += 360;
                }
            }
            g.drawString("Az. =" + this.azMouse + '°', 10, this.yMouse + 7);
        }
        g.setColor(Color.black);
        g.drawString(this.illFracStr, 490, 640);
        g.drawString(this.moonDistanceStr, 490, 655);
        g.setFont(new Font("Helvetica", 0, 9));
        for (int ii = -this.locOffset; ii < -this.locOffset + 25; ++ii) {
            final int j = ii + this.locOffset;
            this.n = j;
            if (326 - this.hArray[j] < 400) {
                g.setColor(Color.red);
                g.drawOval(this.xArray[j] - 2, 326 - this.hArray[j] - 2, 4, 4);
                if (this.currentHoehe > -6.0 && this.hArray[j] > 0) {
                    g.setColor(Color.black);
                }
                else if (this.hArray[j] > 0) {
                    g.setColor(Color.white);
                }
                if (j % 2 == 0 && j < 24) {
                    g.drawString(String.valueOf(this.n), this.xArray[j] + 7, 326 - this.hArray[j] + 3);
                }
            }
        }
        if (this.showMoon) {
            for (int ii2 = -this.locOffset; ii2 < -this.locOffset + 25 - 1; ++ii2) {
                final int k = ii2 + this.locOffset;
                if (326 - this.moonHArray[k] < 400 && 326 - this.moonHArray[k + 1] < 400) {
                    g.setColor(Color.green);
                    if (Math.abs(this.moonAzArray[k] - this.moonAzArray[k + 1]) < 50) {
                        g.drawLine(this.moonAzArray[k], 326 - this.moonHArray[k], this.moonAzArray[k + 1], 326 - this.moonHArray[k + 1]);
                    }
                    if (k % 2 == 0) {
                        g.drawString(String.valueOf(k % 24), this.moonAzArray[k] - 2, 326 - this.moonHArray[k] + 13);
                    }
                    g.drawLine(this.moonAzArray[k], 326 - this.moonHArray[k] - 3, this.moonAzArray[k], 326 - this.moonHArray[k] + 3);
                }
            }
        }
        if (326 - this.hMoon < 400) {
            g.setColor(Color.white);
            g.fillOval(this.xMoon - this.moonRadius, 326 - this.hMoon - this.moonRadius, 2 * this.moonRadius, 2 * this.moonRadius);
            if (this.hSun < 0) {
                g.setColor(Color.white);
                g.drawOval(this.xMoon - this.moonRadius, 326 - this.hMoon - this.moonRadius, 2 * this.moonRadius, 2 * this.moonRadius);
            }
        }
        else {
            g.setColor(Color.white);
            g.drawString("MOON", this.xMoon - 15, 390);
            g.drawString("V", this.xMoon - 4, 400);
        }
        if (326 - this.hMoon < 400) {
            g.setColor(Color.black);
            double dLun = 2.0 * this.moonRadius * this.diffL / 180.0;
            if (this.diffL > 180.0) {
                dLun = 2 * this.moonRadius * (this.diffL - 180.0) / 180.0;
            }
            double PHASE = 180.0 - this.diffLong;
            if (PHASE < 0.0) {
                PHASE += 360.0;
            }
            double ma = PHASE / 360.0;
            if (this.latitude < 0.0) {
                ma = 1.0 - ma;
            }
            int DW;
            if (this.latitude >= 0.0) {
                DW = 270;
            }
            else {
                DW = 90;
            }
            final double sin_termAngle = Math.sin(0.017453292519943295 * (this.zWinkel - DW));
            final double cos_termAngle = Math.cos(0.017453292519943295 * (this.zWinkel - DW));
            if (ma <= 0.25) {
                g.fillArc(this.xMoon - this.moonRadius, 326 - this.hMoon - this.moonRadius, 2 * this.moonRadius, 2 * this.moonRadius, (int)this.zWinkel + 178, 184);
                for (int l = 89; l < 271; ++l) {
                    final double xEll = (this.moonRadius - dLun) * Math.cos(0.017453292519943295 * l);
                    final double yEll = this.moonRadius * Math.sin(0.017453292519943295 * l);
                    final int xE = this.xMoon + (int)Math.round(xEll * cos_termAngle + yEll * sin_termAngle);
                    final int yE = 326 - this.hMoon + (int)Math.round(-xEll * sin_termAngle + yEll * cos_termAngle);
                    g.drawLine(this.xMoon, 326 - this.hMoon, xE, yE);
                }
            }
            if (ma > 0.25 && ma <= 0.5) {
                g.setColor(Color.black);
                g.fillArc(this.xMoon - this.moonRadius, 326 - this.hMoon - this.moonRadius, 2 * this.moonRadius, 2 * this.moonRadius, (int)this.zWinkel + 180, 180);
                g.setColor(Color.white);
                for (int l = 91; l < 270; ++l) {
                    final double xEll = (this.moonRadius - dLun) * Math.cos(0.017453292519943295 * l);
                    final double yEll = this.moonRadius * Math.sin(0.017453292519943295 * l);
                    final int xE = this.xMoon + (int)Math.round(xEll * cos_termAngle + yEll * sin_termAngle);
                    final int yE = 326 - this.hMoon + (int)Math.round(-xEll * sin_termAngle + yEll * cos_termAngle);
                    g.drawLine(this.xMoon, 326 - this.hMoon, xE, yE);
                }
                g.setColor(Color.black);
            }
            if (ma > 0.5 && ma <= 0.75) {
                g.setColor(Color.black);
                g.fillArc(this.xMoon - this.moonRadius, 326 - this.hMoon - this.moonRadius, 2 * this.moonRadius, 2 * this.moonRadius, (int)this.zWinkel + 180, 180);
                g.setColor(Color.white);
                for (int l = 91; l < 271; ++l) {
                    final double xEll = (dLun - this.moonRadius) * Math.cos(0.017453292519943295 * l);
                    final double yEll = this.moonRadius * Math.sin(0.017453292519943295 * l);
                    final int xE = this.xMoon + (int)Math.round(xEll * cos_termAngle + yEll * sin_termAngle);
                    final int yE = 326 - this.hMoon + (int)Math.round(-xEll * sin_termAngle + yEll * cos_termAngle);
                    g.drawLine(this.xMoon, 326 - this.hMoon, xE, yE);
                }
                g.setColor(Color.black);
            }
            if (ma > 0.75) {
                g.setColor(Color.black);
                g.fillArc(this.xMoon - this.moonRadius, 326 - this.hMoon - this.moonRadius, 2 * this.moonRadius, 2 * this.moonRadius, (int)this.zWinkel + 180, 180);
                for (int l = 270; l < 450; ++l) {
                    final double xEll = (-dLun + this.moonRadius) * Math.cos(0.017453292519943295 * l);
                    final double yEll = this.moonRadius * Math.sin(0.017453292519943295 * l);
                    final int xE = this.xMoon + (int)Math.round(xEll * cos_termAngle + yEll * sin_termAngle);
                    final int yE = 326 - this.hMoon + (int)Math.round(-xEll * sin_termAngle + yEll * cos_termAngle);
                    g.drawLine(this.xMoon, 326 - this.hMoon, xE, yE);
                }
            }
            if (100.0 * this.illFrac > 99.0) {
                g.setColor(Color.white);
                g.fillOval(this.xMoon - this.moonRadius, 326 - this.hMoon - this.moonRadius, 2 * this.moonRadius, 2 * this.moonRadius);
                g.setColor(Color.black);
                g.drawOval(this.xMoon - this.moonRadius, 326 - this.hMoon - this.moonRadius, 2 * this.moonRadius, 2 * this.moonRadius);
            }
            if (100.0 * this.illFrac < 1.0) {
                g.setColor(Color.black);
                g.fillOval(this.xMoon - this.moonRadius, 326 - this.hMoon - this.moonRadius, 2 * this.moonRadius, 2 * this.moonRadius);
                g.setColor(Color.white);
                g.drawOval(this.xMoon - this.moonRadius, 326 - this.hMoon - this.moonRadius, 2 * this.moonRadius, 2 * this.moonRadius);
            }
            final int xTerm = (int)Math.round(15.0 * Math.sin(0.017453292519943295 * (this.zWinkel - 90.0)));
            final int yTerm = (int)Math.round(15.0 * Math.cos(0.017453292519943295 * (this.zWinkel - 90.0)));
            g.setColor(Color.red);
            g.drawLine(this.xMoon - xTerm, 326 - this.hMoon - yTerm, this.xMoon + xTerm, 326 - this.hMoon + yTerm);
            g.setColor(Color.black);
            g.drawOval(this.xMoon - this.moonRadius, 326 - this.hMoon - this.moonRadius, 2 * this.moonRadius, 2 * this.moonRadius);
        }
        g.setFont(font);
        g.setColor(Color.blue);
        g.drawString("MOON:", 490, 550);
        g.setColor(Color.black);
        g.drawString(this.moonDeclinStr, 490, 565);
        g.drawString(this.moonGHAStr, 490, 580);
        if (this.nmo) {
            g.drawString("Sunset: daz= " + Math.round(10.0 * this.dazSunset) / 10.0 + '°' + "  dalt= " + Math.round(10.0 * this.daltSunset) / 10.0 + '°', 490, 610);
        }
        g.setColor(Color.blue);
        g.drawString("MOON:", 30, 450);
        g.setColor(Color.black);
        g.drawString(this.moonriseStr, 75, 450);
        g.drawString(this.moonCulmStr, 155, 450);
        g.drawString(this.moonsetStr, 240, 450);
        g.drawString(this.moonHeightStr, 335, 450);
        g.drawString(this.moonAzStr, 410, 450);
        this.GHA = this.comp.computeGHA(this.date, this.month + 1, this.year + 1900, this.STD);
        int xSunMap = 30 + this.x0Map - (int)Math.round(1.2555555555555555 * this.GHA);
        if (xSunMap < 30) {
            xSunMap = 30 + this.x0Map - (int)Math.round(1.2555555555555555 * (this.GHA - 360.0));
        }
        if (!this.drawFirst) {
            final int moonMapRadius = (int)Math.round(this.moonRadius * 0.8);
            final int yMoonMap = this.y0Map - (int)Math.round(1.2444444444444445 * this.moon_Dec);
            this.xMoonMap = 30 + this.x0Map - (int)Math.round(1.2555555555555555 * this.moon_GSW);
            if (this.xMoonMap < 30) {
                this.xMoonMap = 30 + this.x0Map - (int)Math.round(1.2555555555555555 * (this.moon_GSW - 360.0));
            }
            g.setColor(Color.white);
            g.fillOval(this.xMoonMap - moonMapRadius, yMoonMap - moonMapRadius, 2 * moonMapRadius, 2 * moonMapRadius);
            g.setColor(Color.black);
            g.drawOval(this.xMoonMap - moonMapRadius, yMoonMap - moonMapRadius, 2 * moonMapRadius, 2 * moonMapRadius);
            int startAngle;
            if (this.xMoonMap < xSunMap) {
                startAngle = 270;
            }
            else {
                startAngle = 90;
            }
            g.setColor(Color.black);
            double dLun = 2.0 * moonMapRadius * this.diffL / 180.0;
            if (this.diffL > 180.0) {
                dLun = 2 * moonMapRadius * (this.diffL - 180.0) / 180.0;
            }
            final int xHA = (int)Math.round(Math.abs(moonMapRadius - dLun));
            if (this.moonAge <= 7.3825) {
                g.setColor(Color.white);
                g.drawOval(this.xMoonMap - moonMapRadius, yMoonMap - moonMapRadius, 2 * moonMapRadius, 2 * moonMapRadius);
                g.setColor(Color.black);
                g.fillArc(this.xMoonMap - xHA, yMoonMap - moonMapRadius, 2 * xHA, 2 * moonMapRadius, startAngle, 180);
                g.fillArc(this.xMoonMap - moonMapRadius, yMoonMap - moonMapRadius, 2 * moonMapRadius, 2 * moonMapRadius, startAngle + 180, 180);
                g.setColor(Color.white);
                g.drawOval(this.xMoonMap - moonMapRadius, yMoonMap - moonMapRadius, 2 * moonMapRadius, 2 * moonMapRadius);
                g.setColor(Color.black);
            }
            if (this.moonAge > 7.3825 && this.moonAge <= 14.765) {
                g.setColor(Color.black);
                g.fillOval(this.xMoonMap - moonMapRadius, yMoonMap - moonMapRadius, 2 * moonMapRadius, 2 * moonMapRadius);
                g.setColor(Color.white);
                g.fillArc(this.xMoonMap - moonMapRadius, yMoonMap - moonMapRadius, 2 * moonMapRadius, 2 * moonMapRadius, 90, 180);
                g.fillArc(this.xMoonMap - xHA, yMoonMap - moonMapRadius, 2 * xHA, 2 * moonMapRadius, 270, 180);
                g.drawOval(this.xMoonMap - moonMapRadius, yMoonMap - moonMapRadius, 2 * moonMapRadius, 2 * moonMapRadius);
                g.setColor(Color.black);
            }
            if (this.moonAge > 14.765 && this.moonAge < 22.1475) {
                g.setColor(Color.black);
                g.fillOval(this.xMoonMap - moonMapRadius, yMoonMap - moonMapRadius, 2 * moonMapRadius, 2 * moonMapRadius);
                g.setColor(Color.white);
                g.fillArc(this.xMoonMap - moonMapRadius, yMoonMap - moonMapRadius, 2 * moonMapRadius, 2 * moonMapRadius, 270, 180);
                g.fillArc(this.xMoonMap - xHA, yMoonMap - moonMapRadius, 2 * xHA, 2 * moonMapRadius, 90, 180);
                g.drawOval(this.xMoonMap - moonMapRadius, yMoonMap - moonMapRadius, 2 * moonMapRadius, 2 * moonMapRadius);
                g.setColor(Color.black);
            }
            if (this.moonAge > 22.1475 && this.moonAge < 29.53) {
                g.setColor(Color.white);
                g.fillOval(this.xMoonMap - moonMapRadius, yMoonMap - moonMapRadius, 2 * moonMapRadius, 2 * moonMapRadius);
                g.setColor(Color.black);
                g.fillArc(this.xMoonMap - moonMapRadius, yMoonMap - moonMapRadius, 2 * moonMapRadius, 2 * moonMapRadius, 90, 180);
                g.fillArc(this.xMoonMap - xHA, yMoonMap - moonMapRadius, 2 * xHA, 2 * moonMapRadius, 270, 180);
                g.setColor(Color.white);
                g.drawOval(this.xMoonMap - moonMapRadius, yMoonMap - moonMapRadius, 2 * moonMapRadius, 2 * moonMapRadius);
                g.setColor(Color.black);
            }
            g.setColor(Color.black);
            g.drawOval(this.xMoonMap - moonMapRadius, yMoonMap - moonMapRadius, 2 * moonMapRadius, 2 * moonMapRadius);
        }
        if (!this.drawFirst) {
            g.setColor(Color.red);
            final int mapRadius = (int)Math.round(0.8 * this.Radius);
            final int ySunMap = this.y0Map - (int)Math.round(this.DEC * 1.2444444444444445);
            g.setColor(Color.yellow);
            g.fillOval(xSunMap - mapRadius, ySunMap - mapRadius, 2 * mapRadius, 2 * mapRadius);
            g.setColor(Color.red);
            g.drawOval(xSunMap - mapRadius, ySunMap - mapRadius, 2 * mapRadius, 2 * mapRadius);
        }
        final int xM = 599;
        final int yM = 445;
        final int r = 30;
        g.setFont(new Font("Helvetiva", 0, 9));
        final int xRise = (int)(xM - Math.round(r * Math.sin(3.141592653589793 * this.hRise / 12.0)));
        final int yRise = (int)(yM + Math.round(r * Math.cos(3.141592653589793 * this.hRise / 12.0)));
        final int xSet = (int)(xM - Math.round(r * Math.sin(3.141592653589793 * this.hSet / 12.0)));
        final int ySet = (int)(yM + Math.round(r * Math.cos(3.141592653589793 * this.hSet / 12.0)));
        g.setColor(Color.red);
        g.drawString("6", xM - r - 9, yM + 5);
        g.drawString("12", xM - 5, yM - r - 3);
        g.drawString("18", xM + r + 3, yM + 5);
        g.drawString("0", xM - 2, yM + r + 12);
        g.setColor(Color.black);
        g.fillArc(xM - r, yM - r, 2 * r, 2 * r, 270 - (int)(this.nautRiseTime * 15.0), (int)(360.0 - 15.0 * (this.nautSetTime - this.nautRiseTime)));
        g.setColor(Color.lightGray);
        g.fillArc(xM - r, yM - r, 2 * r, 2 * r, 270 - (int)(this.civilRiseTime * 15.0), (int)(-15.0 * (this.hRise - this.civilRiseTime)));
        g.fillArc(xM - r, yM - r, 2 * r, 2 * r, 270 - (int)(this.civilSetTime * 15.0), (int)(-15.0 * (this.hSet - this.civilSetTime)));
        g.setColor(Color.gray);
        g.fillArc(xM - r, yM - r, 2 * r, 2 * r, 270 - (int)(this.nautRiseTime * 15.0), (int)(-15.0 * (this.civilRiseTime - this.nautRiseTime)));
        g.fillArc(xM - r, yM - r, 2 * r, 2 * r, 270 - (int)(this.nautSetTime * 15.0), (int)(-15.0 * (this.civilSetTime - this.nautSetTime)));
        g.setColor(Color.yellow);
        if (!this.unvisible) {
            if (this.hSet > this.hRise) {
                g.fillArc(xM - r, yM - r, 2 * r, 2 * r, 270 - (int)(this.hRise * 15.0), (int)(-15.0 * (this.hSet - this.hRise)));
            }
            else {
                g.fillArc(xM - r, yM - r, 2 * r, 2 * r, 270 - (int)(this.hSet * 15.0), (int)(15.0 * (24.0 - (this.hRise - this.hSet))));
            }
        }
        if (this.visible) {
            g.fillArc(xM - r, yM - r, 2 * r, 2 * r, 0, 360);
        }
        g.setColor(Color.black);
        g.drawLine(xM, yM, xRise, yRise);
        g.drawLine(xM, yM, xSet, ySet);
        g.setColor(Color.lightGray);
        g.drawLine(xM - r, yM, xM + r, yM);
        g.drawLine(xM, yM - r, xM, yM + r);
        final int x9 = (int)Math.round(r * Math.sin(0.7853981633974483));
        g.drawLine(xM, yM, xM - x9, yM - x9);
        g.drawLine(xM, yM, xM + x9, yM + x9);
        g.drawLine(xM, yM, xM + x9, yM - x9);
        g.drawLine(xM, yM, xM - x9, yM + x9);
        final int xUT = (int)(xM - Math.round(r * Math.sin(3.141592653589793 * (this.STD + this.locOffset) / 12.0)));
        final int yUT = (int)(yM + Math.round(r * Math.cos(3.141592653589793 * (this.STD + this.locOffset) / 12.0)));
        g.setColor(Color.red);
        g.drawLine(xM, yM, xUT, yUT);
        g.setColor(Color.black);
        g.drawOval(xM - r, yM - r, 2 * r, 2 * r);
        this.drawFirst = false;
        if (this.analemmaOK) {
            int m = 0;
            for (int m2 = 0; m2 < 12; ++m2) {
                for (int d = 1; d <= this.comp.daysInMonth(m2, this.year); ++d) {
                    final double decAna = this.comp.computeDeclination(d, m2 + 1, this.year + 1900, this.STD);
                    this.analemmaY[m] = this.y0Map - (int)Math.round(decAna * 1.2444444444444445);
                    final double ghaAna = this.comp.computeGHA(d, m2 + 1, this.year + 1900, this.STD);
                    int X = 30 + this.x0Map - (int)Math.round(1.2555555555555555 * ghaAna);
                    if (X < 30) {
                        X = 30 + this.x0Map - (int)Math.round(1.2555555555555555 * (ghaAna - 360.0));
                    }
                    this.analemmaX[m] = X;
                    ++m;
                }
            }
            g.setColor(Color.red);
            for (int i2 = 0; i2 < m - 1; ++i2) {
                if (Math.abs(this.analemmaX[i2] - this.analemmaX[i2 + 1]) < 50) {
                    g.drawLine(this.analemmaX[i2], this.analemmaY[i2], this.analemmaX[i2 + 1], this.analemmaY[i2 + 1]);
                }
            }
            m = 0;
            for (int m3 = 0; m3 < 12; ++m3) {
                for (int d2 = 1; d2 <= this.comp.daysInMonth(m3, this.year); ++d2) {
                    final double decAna = this.comp.computeDeclination(d2, m3 + 1, this.year + 1900, this.STD);
                    final double ghaAna = this.comp.computeGHA(d2, m3 + 1, this.year + 1900, this.STD);
                    final double hAna = this.comp.computeHeight(decAna, this.latitude, this.longitude, ghaAna);
                    this.analemmaY[m] = 326 - (int)Math.round(hAna * 212.0 / 90.0);
                    final double azAna = this.comp.computeAzimut(decAna, this.latitude, this.longitude, ghaAna, hAna);
                    double XX;
                    if (this.latitude >= decAna) {
                        XX = (180.0 - azAna) * 270.0 / 180.0;
                    }
                    else if (azAna < 180.0) {
                        XX = azAna * 270.0 / 180.0;
                    }
                    else {
                        XX = -(360.0 - azAna) * 270.0 / 180.0;
                    }
                    if (this.latitude >= decAna) {
                        XX = 330.0 - XX;
                    }
                    else {
                        XX += 330.0;
                    }
                    this.analemmaX[m] = (int)Math.round(XX);
                    ++m;
                }
            }
            for (int i3 = 0; i3 < m - 1; ++i3) {
                if (this.analemmaY[i3] < 396 && Math.abs(this.analemmaX[i3] - this.analemmaX[i3 + 1]) < 50) {
                    g.drawLine(this.analemmaX[i3], this.analemmaY[i3], this.analemmaX[i3 + 1], this.analemmaY[i3 + 1]);
                }
            }
        }
    }
    
    public String azDirection(final double az) {
        final String[] dirStrArray = { "(N)", "(NNE)", "(NE)", "(ENE)", "(E)", "(ESE)", "(SE)", "(SSE)", "(S)", "(SSW)", "(SW)", "(WSW)", "(W)", "(WNW)", "(NW)", "(NNW)" };
        if (this.latitude < 0.0 && (az < 11.25 || az > 348.75)) {
            return "(S)";
        }
        final double d = (az - 11.25) / 22.5;
        final int n = (int)d;
        if (az < 11.25 || az > 348.75) {
            return "(N)";
        }
        return dirStrArray[n + 1];
    }
    
    public String WaxWanMoon(final double phase) {
        String str = "";
        if (phase < 2.0) {
            str = "new";
        }
        else if (phase >= 2.0 && phase < 23.0) {
            str = "waxing crescent";
        }
        else if (phase >= 23.0 && phase < 27.0) {
            str = "first quarter";
        }
        else if (phase >= 27.0 && phase < 48.0) {
            str = "waxing gibbous";
        }
        else if (phase >= 48.0 && phase < 52.0) {
            str = "full";
        }
        else if (phase >= 52.0 && phase < 73.0) {
            str = "waning gibbous";
        }
        else if (phase >= 73.0 && phase < 77.0) {
            str = "third quarter";
        }
        else if (phase >= 77.0 && phase < 98.0) {
            str = "waning crescent";
        }
        else if (phase >= 98.0 && phase <= 100.0) {
            str = "new";
        }
        return str;
    }
    
    public void riseset(final int DATE, final int MONTH, final int YEAR, final double HOUR, final int what) {
        double sh = 0.0;
        switch (what) {
            case 1: {
                sh = Math.sin(-0.014543828656868749);
                break;
            }
            case 2: {
                sh = Math.sin(-0.10471975511965978);
                break;
            }
            case 3: {
                sh = Math.sin(-0.20943951023931956);
                break;
            }
            case 4: {
                sh = Math.sin(-0.3141592653589793);
                break;
            }
        }
        final double JD = this.comp.JD(DATE, MONTH, YEAR, HOUR);
        double dec = this.comp.sunDecRA(1, JD);
        double ra = this.comp.sunDecRA(2, JD);
        this.Y0 = this.comp.sin_elev(JD, this.latitude, -this.longitude, dec, ra) - sh;
        final double jdPlus = this.comp.JD(DATE, MONTH, YEAR, HOUR + 1.0);
        dec = this.comp.sunDecRA(1, jdPlus);
        ra = this.comp.sunDecRA(2, jdPlus);
        this.yPlus = this.comp.sin_elev(jdPlus, this.latitude, -this.longitude, dec, ra) - sh;
        final double jdMinus = this.comp.JD(DATE, MONTH, YEAR, HOUR - 1.0);
        dec = this.comp.sunDecRA(1, jdMinus);
        ra = this.comp.sunDecRA(2, jdMinus);
        this.yMinus = this.comp.sin_elev(jdMinus, this.latitude, -this.longitude, dec, ra) - sh;
        this.ABOVE = (this.yMinus > 0.0);
        this.QUAD();
        switch (this.NZ) {
            case 1: {
                if (this.yMinus < 0.0) {
                    this.UTRISE = HOUR + this.zero1;
                    this.RISE = true;
                    break;
                }
                this.UTSET = HOUR + this.zero1;
                this.SETT = true;
                break;
            }
            case 2: {
                if (this.YE < 0.0) {
                    this.UTRISE = HOUR + this.zero2;
                    this.UTSET = HOUR + this.zero1;
                }
                else {
                    this.UTRISE = HOUR + this.zero1;
                    this.UTSET = HOUR + this.zero2;
                }
                this.RISE = true;
                this.SETT = true;
                break;
            }
        }
    }
    
    public void QUAD() {
        this.NZ = 0;
        final double A = 0.5 * (this.yMinus + this.yPlus) - this.Y0;
        final double B = 0.5 * (this.yPlus - this.yMinus);
        final double C = this.Y0;
        final double XE = -B / (2.0 * A);
        this.YE = (A * XE + B) * XE + C;
        final double DIS = B * B - 4.0 * A * C;
        if (DIS >= 0.0) {
            this.DX = 0.5 * Math.sqrt(DIS) / Math.abs(A);
            this.zero1 = XE - this.DX;
            this.zero2 = XE + this.DX;
            if (Math.abs(this.zero1) <= 1.0) {
                ++this.NZ;
            }
            if (Math.abs(this.zero2) <= 1.0) {
                ++this.NZ;
            }
            if (this.zero1 < -1.0) {
                this.zero1 = this.zero2;
            }
        }
    }
    
    public void data() {
        final String space = "       ";
        for (int ii = -this.locOffset; ii < -this.locOffset + 25; ++ii) {
            final int i = ii + this.locOffset;
            this.n = i;
            if (this.n < 10) {
                this.str = "  ";
            }
            else {
                this.str = " ";
            }
            while (this.hoeheStrArray[i].length() < 6) {
                this.hoeheStrArray[i] = " " + this.hoeheStrArray[i];
            }
            while (this.azStrArray[i].length() < 6) {
                this.azStrArray[i] = " " + this.azStrArray[i];
            }
            while (this.moonStrArray[i].length() < 6) {
                this.moonStrArray[i] = " " + this.moonStrArray[i];
            }
            while (this.moonAzStrArray[i].length() < 6) {
                this.moonAzStrArray[i] = " " + this.moonAzStrArray[i];
            }
            this.dataStr[this.kd] = String.valueOf(this.str) + "  " + i + ":00" + space + this.hoeheStrArray[i] + space + this.azStrArray[i] + space + this.moonStrArray[i] + space + this.moonAzStrArray[i];
            ++this.kd;
        }
    }
    
    public String caldat(final double JD) {
        final double JD2 = (int)(JD + 0.5);
        final int B = (int)((JD2 - 1867216.25) / 36524.25);
        final double C = JD2 + B - B / 4 + 1525.0;
        final int D = (int)((C - 122.1) / 365.25);
        final double E = 365.0 * D + D / 4;
        final int F = (int)((C - E) / 30.6001);
        final int day = (int)(C - E + 0.5) - (int)(30.6001 * F);
        String dayStr = String.valueOf(day);
        if (day < 10) {
            dayStr = " " + dayStr;
        }
        final int month = F - 1 - 12 * (F / 14);
        final int year = D - 4715 - (7 + month) / 10;
        double hour = 24.0 * (JD + 0.5 - JD2);
        int min = (int)Math.round(60.0 * this.frac(hour));
        if (min == 60) {
            min = 0;
            ++hour;
        }
        if (min > 9) {
            this.str = ":";
        }
        else {
            this.str = ":0";
        }
        String hourStr = String.valueOf((int)hour) + this.str + min;
        if ((int)hour < 10) {
            hourStr = " " + hourStr;
        }
        return year + " " + this.monthArray[month - 1] + " " + dayStr + " at " + hourStr + " UT";
    }
    
    void writeConsole() {
        final String consoleMoonriseStr = String.valueOf(this.moonriseStr.substring(0, 4)) + "  " + this.moonriseStr.substring(5, this.moonriseStr.length());
        final String consoleMoonsetStr = String.valueOf(this.moonsetStr.substring(0, 4)) + "  " + this.moonsetStr.substring(5, this.moonsetStr.length());
        final String consoleLHAStr = "LHA    = " + Math.round(100.0 * this.LHA) / 100.0 + '°' + " = " + this.DegMinStr(this.LHA);
        this.consoleTransStr = this.comp.makeTimeString("Transit               ", this.trans);
        final String consoleHeightStr = String.valueOf(this.heightStr) + " = " + (int)this.currentHoehe + '°' + " " + Math.round(10.0 * this.frac(Math.abs(this.currentHoehe)) * 60.0) / 10.0 + "'";
        this.consoleMoonGHAStr = "GHA    = " + Math.round(100.0 * this.moon_GSW) / 100.0 + '°' + " = " + this.DegMinStr(this.moon_GSW);
        this.sunDistanceStr = "Distance from Earth = " + Math.round(1000000.0 * this.sunDistance) / 1000000.0 + " AU";
        this.moonDiameterStr = ",  Diameter = " + Math.round(100.0 * this.moonDiameter) / 100.0 + "'";
        final String brightLimbStr = "Bright limb angle   = " + Math.round(this.zWinkel) + '°' + " to zenith";
        this.LMSTStr = new StringBuffer().append(Math.round(10000.0 * this.LMST1) / 10000.0).append('°').append(" = ").append(this.DegMinStr(this.LMST1)).append(" = ").append(this.HourMinSecStr(this.LMST1)).toString();
        this.GMSTStr = new StringBuffer().append(Math.round(10000.0 * this.GMST1) / 10000.0).append('°').append(" = ").append(this.DegMinStr(this.GMST1)).append(" = ").append(this.HourMinSecStr(this.GMST1)).toString();
        final String nextNewMoonStr = String.valueOf(this.comp.dayString(this.jdNextNew)) + " " + this.caldat(this.jdNextNew);
        final String prevNewMoonStr = String.valueOf(this.comp.dayString(this.jdLastNew)) + " " + this.caldat(this.jdLastNew);
        final String consoleMoonLHAStr = "LHA    = " + Math.round(100.0 * (this.moon_GSW + this.longitude)) / 100.0 + '°' + " = " + this.DegMinStr(this.moon_GSW + this.longitude);
        this.dataStr[0] = " ";
        this.dataStr[1] = String.valueOf(this.versStr) + "     © 1998-2011 Juergen Giesen";
        this.dataStr[2] = "User: " + this.usrString;
        this.dataStr[3] = "  Location:  " + this.locStr;
        this.str = new StringBuffer().append(Math.abs(this.latitude)).append('°').toString();
        if (this.latitude >= 0.0) {
            this.str = String.valueOf(this.str) + " N";
        }
        else {
            this.str = String.valueOf(this.str) + " S";
        }
        this.str = String.valueOf(this.str) + " = " + this.DegMinStr(Math.abs(this.latitude));
        if (this.latitude >= 0.0) {
            this.str = String.valueOf(this.str) + " N";
        }
        else {
            this.str = String.valueOf(this.str) + " S";
        }
        this.dataStr[4] = "  Latitude:  " + this.str;
        this.str = new StringBuffer().append(Math.abs(this.longitude)).append('°').toString();
        if (this.longitude >= 0.0) {
            this.str = String.valueOf(this.str) + " E";
        }
        else {
            this.str = String.valueOf(this.str) + " W";
        }
        this.str = String.valueOf(this.str) + " = " + this.DegMinStr(Math.abs(this.longitude));
        if (this.longitude >= 0.0) {
            this.str = String.valueOf(this.str) + " E";
        }
        else {
            this.str = String.valueOf(this.str) + " W";
        }
        this.dataStr[5] = "  Longitude: " + this.str;
        this.str = this.dat.toString();
        this.dataStr[6] = "  Date/Time: " + this.str;
        this.dataStr[7] = "  UT:        " + this.gmtStr;
        this.dataStr[8] = "  Time Zone: ";
        if (this.locOffset > 0) {
            final String[] dataStr = this.dataStr;
            final int n2 = 8;
            dataStr[n2] = String.valueOf(dataStr[n2]) + "UT + " + this.locOffset + ":00 h";
        }
        if (this.locOffset < 0) {
            final String[] dataStr2 = this.dataStr;
            final int n3 = 8;
            dataStr2[n3] = String.valueOf(dataStr2[n3]) + "UT - " + Math.abs(this.locOffset) + ":00 h";
        }
        if (this.locOffset == 0) {
            this.dataStr[8] = "  Time Zone: UT +/- 0:00 h";
        }
        this.dataStr[9] = "  Jul. Day:  " + this.currentJD;
        this.deltaEoTStr = Math.round((this.equation0 - this.equation1) * 600.0) / 10.0 + " s";
        if (this.equation0 - this.equation1 > 0.0) {
            this.eqtStr = String.valueOf(this.eqtConsoleStr) + "  = prev. day  +" + this.deltaEoTStr;
        }
        else {
            this.eqtStr = String.valueOf(this.eqtConsoleStr) + " = prev. day  " + this.deltaEoTStr;
        }
        this.dataStr[10] = "  Equation of Time        = " + this.eqtStr;
        this.dataStr[11] = "  Local Sidereal Time     = " + this.LMSTStr;
        this.dataStr[12] = "  Greenwich Sidereal Time = " + this.GMSTStr;
        this.dataStr[13] = "  SUN:";
        this.s = "    ";
        this.dataStr[14] = String.valueOf(this.s) + this.sunRAStr;
        this.dataStr[15] = String.valueOf(this.s) + this.sunLStr;
        this.dataStr[16] = String.valueOf(this.s) + this.sunDistanceStr + this.sunDiameterStr;
        this.dataStr[17] = String.valueOf(this.s) + this.consoleDeclinStr;
        this.dataStr[18] = String.valueOf(this.s) + this.consoleGHAStr;
        this.dataStr[19] = String.valueOf(this.s) + consoleLHAStr;
        this.dataStr[20] = String.valueOf(this.s) + consoleHeightStr;
        this.dataStr[21] = String.valueOf(this.s) + this.azStr;
        if (this.astroRiseTime != 100.0) {
            this.dataStr[22] = String.valueOf(this.s) + this.astroRiseStr + this.comp.makeTimeString("   ", this.astroTwilightDurationRise) + " h before rise";
        }
        else {
            this.dataStr[22] = String.valueOf(this.s) + this.astroRiseStr;
        }
        if (this.nautRiseTime != 100.0) {
            this.dataStr[23] = String.valueOf(this.s) + this.nautRiseStr + this.comp.makeTimeString("   ", this.nautTwilightDurationRise) + " h before rise";
        }
        else {
            this.dataStr[23] = String.valueOf(this.s) + this.nautRiseStr;
        }
        if (this.civilRiseTime != 100.0) {
            this.dataStr[24] = String.valueOf(this.s) + this.civilRiseStr + this.comp.makeTimeString("   ", this.civilTwilightDurationRise) + " h before rise";
        }
        else {
            this.dataStr[24] = String.valueOf(this.s) + this.civilRiseStr;
        }
        this.dataStr[25] = String.valueOf(this.s) + this.consoleRiseStr + ", Moon Azim. " + this.moonAzAtSunriseStr + "\n";
        final String[] dataStr3 = this.dataStr;
        final int n4 = 25;
        dataStr3[n4] = String.valueOf(dataStr3[n4]) + this.s + "                              daz = " + Math.round(100.0 * this.dazSunrise) / 100.0 + '°' + ",  dalt " + this.moonElevAtSunriseStr;
        this.dataStr[26] = String.valueOf(this.s) + this.consoleTransStr + "   " + this.culmHoeheStr;
        this.dataStr[27] = String.valueOf(this.s) + this.consoleSetStr + ", Moon Azim. " + this.moonAzAtSunsetStr + "\n";
        final String[] dataStr4 = this.dataStr;
        final int n5 = 27;
        dataStr4[n5] = String.valueOf(dataStr4[n5]) + this.s + "                              daz = " + Math.round(100.0 * this.dazSunset) / 100.0 + '°' + ",  dalt " + this.moonElevAtSunsetStr;
        if (this.civilSetTime != 100.0) {
            this.dataStr[28] = String.valueOf(this.s) + this.civilSetStr + this.comp.makeTimeString("   ", this.civilTwilightDurationSet) + " h after set";
        }
        else {
            this.dataStr[28] = String.valueOf(this.s) + this.civilSetStr;
        }
        if (this.nautSetTime != 100.0) {
            this.dataStr[29] = String.valueOf(this.s) + this.nautSetStr + this.comp.makeTimeString("   ", this.nautTwilightDurationSet) + " h after set";
        }
        else {
            this.dataStr[29] = String.valueOf(this.s) + this.nautSetStr;
        }
        if (this.astroSetTime != 100.0) {
            this.dataStr[30] = String.valueOf(this.s) + this.astroSetStr + this.comp.makeTimeString("   ", this.astroTwilightDurationSet) + " h after set";
        }
        else {
            this.dataStr[30] = String.valueOf(this.s) + this.astroSetStr;
        }
        this.dataStr[31] = String.valueOf(this.s) + this.minTransStr;
        this.dataStr[32] = String.valueOf(this.s) + this.lenDayStr + this.diffLenDayStr;
        this.dataStr[33] = "  MOON:";
        this.dataStr[34] = String.valueOf(this.s) + "Prev. New Moon: " + prevNewMoonStr + "\n";
        final String[] dataStr5 = this.dataStr;
        final int n6 = 34;
        dataStr5[n6] = String.valueOf(dataStr5[n6]) + this.s + "Moon Age " + Math.round(10000.0 * this.moonAge) / 10000.0 + " d = " + this.comp.DaysHoursMinutes(this.moonAge);
        this.dataStr[35] = String.valueOf(this.s) + this.WaxWanMoon(100.0 * this.moonAge / 29.53);
        final String[] dataStr6 = this.dataStr;
        final int n7 = 35;
        dataStr6[n7] = String.valueOf(dataStr6[n7]) + "\n" + this.s + "Next New Moon:  " + nextNewMoonStr;
        this.dataStr[36] = String.valueOf(this.s) + this.illFracStr;
        this.dataStr[37] = String.valueOf(this.s) + brightLimbStr;
        this.dataStr[38] = String.valueOf(this.s) + this.moonDistanceStr + this.moonDiameterStr;
        this.dataStr[39] = String.valueOf(this.s) + this.consoleMoonDeclinStr;
        this.dataStr[40] = String.valueOf(this.s) + this.moonRAStr;
        this.dataStr[41] = String.valueOf(this.s) + this.moonLStr;
        this.dataStr[42] = String.valueOf(this.s) + this.consoleMoonGHAStr + "\n";
        final String[] dataStr7 = this.dataStr;
        final int n8 = 42;
        dataStr7[n8] = String.valueOf(dataStr7[n8]) + this.s + consoleMoonLHAStr;
        this.dataStr[43] = String.valueOf(this.s) + this.moonHeightStr;
        this.dataStr[44] = String.valueOf(this.s) + this.moonAzStr;
        this.dataStr[45] = String.valueOf(this.s) + consoleMoonriseStr + "   " + this.moonriseAzStr;
        this.dataStr[46] = String.valueOf(this.s) + this.moonCulmStr + "   " + this.moonCulmHeightStr;
        this.dataStr[47] = String.valueOf(this.s) + consoleMoonsetStr + "   " + this.moonsetAzStr + "   Above horizon: " + this.comp.makeTimeString("  ", this.moonAboveHorizon) + " hours";
        this.dataStr[48] = String.valueOf(this.s) + this.elongationStr;
        this.dataStr[49] = "";
        this.dataStr[50] = "    Local         SUN          SUN         MOON         MOON";
        this.dataStr[51] = "    Time        Altit.     Azimuth       Altit.      Azimuth";
        this.data();
        for (int i = 0; i <= this.kd; ++i) {
            this.dataStr[i] = String.valueOf(this.dataStr[i]) + "\n";
        }
        String STR = "";
        if (this.usrString.equals("Demo running")) {
            for (int j = 0; j <= this.kd; ++j) {
                int L = this.dataStr[j].length();
                L -= 10;
                STR = "";
                for (int n = 0; n <= L; ++n) {
                    final char C = this.dataStr[j].charAt(n);
                    STR = String.valueOf(STR) + C;
                }
                this.dataStr[j] = String.valueOf(STR) + " D E M O" + "\n";
            }
        }
        final scrollFrame sf = new scrollFrame("Data " + (this.year + 1900) + " " + this.monthArray[this.month] + " " + this.date, this.kd - 1, this.dataStr);
        sf.resize(500, 480);
        sf.show();
    }
    
    public String myGMT() {
        final Date datGMT = new Date();
        int hGMT = this.hours - this.locOffset;
        datGMT.setMonth(this.month);
        datGMT.setYear(this.year);
        int monthGMT = this.month;
        int yearGMT = this.year;
        int dateGMT = this.date;
        datGMT.setDate(dateGMT);
        if (hGMT >= 24) {
            hGMT -= 24;
            ++dateGMT;
            if (dateGMT > this.comp.daysInMonth(monthGMT, yearGMT)) {
                dateGMT = 1;
                ++monthGMT;
            }
            if (monthGMT == 12) {
                monthGMT = 0;
                ++yearGMT;
            }
            datGMT.setMonth(monthGMT);
            datGMT.setYear(yearGMT);
            datGMT.setDate(dateGMT);
        }
        if (hGMT <= 0) {
            hGMT += 24;
            --dateGMT;
            if (dateGMT == 0) {
                --monthGMT;
                dateGMT = this.comp.daysInMonth(monthGMT, yearGMT);
            }
            if (monthGMT == -1) {
                monthGMT = 11;
                --yearGMT;
            }
            datGMT.setMonth(monthGMT);
            datGMT.setYear(yearGMT);
            datGMT.setDate(dateGMT);
        }
        datGMT.setHours(hGMT);
        datGMT.setMinutes(this.minutes);
        datGMT.setSeconds(this.seconds);
        this.gmtStr = datGMT.toString();
        return String.valueOf(this.gmtStr.substring(0, 20)) + "UT " + (1900 + datGMT.getYear());
    }
    
    public void dataNewMoon(final boolean showFrame) {
        final double[] jD = new double[20];
        int minNewMoon = 0;
        int hourNM = 0;
        final double[] delta = new double[122];
        int N = 0;
        int nmDate = 0;
        int nmMonth = 0;
        this.countSet = 0;
        String strD = "";
        String strM = "";
        final double curJD = this.comp.JD(this.date, this.month + 1, this.year + 1900, this.STD);
        double jd = this.comp.JD(1, 1, this.year + 1900, 0.0);
        for (int n = -300; n < 750; ++n) {
            if (Math.abs(2449128.59 + 29.53058867 * n - 0.3 - jd) < 30.0) {
                N = n;
                break;
            }
        }
        int nn = 0;
        for (int n2 = N; n2 <= N + 14; ++n2) {
            jd = 2449128.59 + 29.53058867 * n2 - 1.0;
            if (!showFrame && jd > curJD + 31.0) {
                break;
            }
            for (int i = 0; i <= 48; ++i) {
                final SunMoon sunmoon = new SunMoon(jd + i / 24.0, 6);
                double SunMoonExcess = 360.0 - (sunmoon.lambda() - sunmoon.heliocentricLambda());
                if (SunMoonExcess > 360.0) {
                    SunMoonExcess -= 360.0;
                }
                delta[i] = SunMoonExcess;
            }
            hourNM = 0;
            for (int j = 0; j < 48; ++j) {
                if (delta[j] > 359.0 && delta[j + 1] < 1.0) {
                    hourNM = j;
                    break;
                }
            }
            if (Math.abs(delta[hourNM + 1]) < Math.abs(delta[hourNM] - 360.0)) {
                ++hourNM;
            }
            --hourNM;
            for (int k = 0; k <= 120; ++k) {
                final SunMoon sunmoon = new SunMoon(jd + hourNM / 24.0 + k / 1440.0, 6);
                double SunMoonExcess = 360.0 - (sunmoon.lambda() - sunmoon.heliocentricLambda());
                if (SunMoonExcess > 360.0) {
                    SunMoonExcess -= 360.0;
                }
                delta[k] = SunMoonExcess;
            }
            minNewMoon = 0;
            for (int l = 0; l < 120; ++l) {
                if (delta[l] > 359.0 && delta[l + 1] < 1.0) {
                    minNewMoon = l;
                    break;
                }
            }
            final double delt = (360.0 - delta[minNewMoon]) / (360.0 - delta[minNewMoon] + delta[minNewMoon + 1]);
            double jdNM = jd + hourNM / 24.0 + (minNewMoon + delt) / 1440.0;
            if (showFrame) {
                strM = this.monthArray[this.calDat(2, jdNM)];
                nmMonth = this.calDat(2, jdNM);
                final int D = this.calDat(1, jdNM);
                if ((nmDate = D) < 10) {
                    strD = "0" + D;
                }
                else {
                    strD = String.valueOf(D);
                }
                this.dataStr[n2 - N] = String.valueOf(this.calDat(3, jdNM)) + " " + strM + " " + strD + "  " + this.hourStr;
            }
            jD[n2 - N] = jd + hourNM / 24.0 + minNewMoon / 1440.0;
            if (jD[n2 - N] < curJD) {
                this.jdLastNew = jD[n2 - N];
                nn = n2;
            }
            this.newMoonJD[n2 - N] = jD[n2 - N];
            if (showFrame) {
                ++this.countSet;
                this.sunRiseSet(nmDate, nmMonth, this.calDat(3, jd + hourNM / 24.0 + minNewMoon / 1440.0) - 1900);
                final String[] dataStr = this.dataStr;
                final int n4 = n2 - N;
                dataStr[n4] = String.valueOf(dataStr[n4]) + this.DazDaltStr;
                final double jdDARCL0 = this.comp.JD(nmDate, nmMonth + 1, this.calDat(3, jd + hourNM / 24.0 + minNewMoon / 1440.0), this.hSetArray[this.countSet]);
                SunMoon sm = new SunMoon(jdDARCL0, 6);
                double geocentricElong = Math.cos(0.017453292519943295 * sm.BETA()) * Math.cos(0.017453292519943295 * (sm.lambda() - sm.heliocentricLambda()));
                geocentricElong = Math.acos(geocentricElong) / 0.017453292519943295;
                String str1;
                for (str1 = new StringBuffer().append(Math.round(100.0 * geocentricElong) / 100.0).append('°').toString(); str1.length() < 7; str1 = String.valueOf(str1) + " ") {}
                double mAge = Math.round(240.0 * (jdDARCL0 - jD[n2 - N])) / 10.0;
                final String[] dataStr2 = this.dataStr;
                final int n5 = n2 - N;
                dataStr2[n5] = String.valueOf(dataStr2[n5]) + "   arcl " + str1 + "  age " + mAge + "h" + "\n";
                jdNM = 1.0 + jd + hourNM / 24.0 + minNewMoon / 1440.0;
                int theDay = this.calDat(1, jdNM);
                String str2 = String.valueOf(theDay);
                if (theDay < 10) {
                    str2 = " " + str2;
                }
                final String[] dataStr3 = this.dataStr;
                final int n6 = n2 - N;
                dataStr3[n6] = String.valueOf(dataStr3[n6]) + this.calDat(3, jdNM) + " " + this.monthArray[this.calDat(2, jdNM)] + " " + str2 + "         ";
                final double jd2 = 1.0 + jd + hourNM / 24.0 + minNewMoon / 1440.0;
                this.sunRiseSet(this.calDat(1, jd2), this.calDat(2, jd2), this.calDat(3, jd2) - 1900);
                final String[] dataStr4 = this.dataStr;
                final int n7 = n2 - N;
                dataStr4[n7] = String.valueOf(dataStr4[n7]) + " " + this.DazDaltStr;
                final double jdDARCL2 = 1.0 + this.comp.JD(nmDate, nmMonth + 1, this.calDat(3, jd + hourNM / 24.0 + minNewMoon / 1440.0), this.hSetArray[this.countSet]);
                sm = new SunMoon(jdDARCL2, 6);
                double geocentricElong2 = Math.cos(0.017453292519943295 * sm.BETA()) * Math.cos(0.017453292519943295 * (sm.lambda() - sm.heliocentricLambda()));
                geocentricElong2 = Math.acos(geocentricElong2) / 0.017453292519943295;
                for (str1 = new StringBuffer().append(Math.round(100.0 * geocentricElong2) / 100.0).append('°').toString(); str1.length() < 7; str1 = String.valueOf(str1) + " ") {}
                mAge = Math.round(240.0 * (jdDARCL2 - jD[n2 - N])) / 10.0;
                final String[] dataStr5 = this.dataStr;
                final int n8 = n2 - N;
                dataStr5[n8] = String.valueOf(dataStr5[n8]) + "   arcl " + str1 + "  age " + mAge + "h" + "\n";
                final double jd3 = 2.0 + jd + hourNM / 24.0 + minNewMoon / 1440.0;
                this.sunRiseSet(this.calDat(1, jd3), this.calDat(2, jd3), this.calDat(3, jd3) - 1900);
                jdNM = 2.0 + jd + hourNM / 24.0 + minNewMoon / 1440.0;
                theDay = this.calDat(1, jdNM);
                str2 = String.valueOf(theDay);
                if (theDay < 10) {
                    str2 = " " + str2;
                }
                final String[] dataStr6 = this.dataStr;
                final int n9 = n2 - N;
                dataStr6[n9] = String.valueOf(dataStr6[n9]) + this.calDat(3, jdNM) + " " + this.monthArray[this.calDat(2, jdNM)] + " " + str2 + "         ";
                final String[] dataStr7 = this.dataStr;
                final int n10 = n2 - N;
                dataStr7[n10] = String.valueOf(dataStr7[n10]) + " " + this.DazDaltStr;
                final double jdDARCL3 = 2.0 + this.comp.JD(nmDate, nmMonth + 1, this.calDat(3, jd + hourNM / 24.0 + minNewMoon / 1440.0), this.hSetArray[this.countSet]);
                sm = new SunMoon(jdDARCL3, 6);
                double geocentricElong3 = Math.cos(0.017453292519943295 * sm.BETA()) * Math.cos(0.017453292519943295 * (sm.lambda() - sm.heliocentricLambda()));
                geocentricElong3 = Math.acos(geocentricElong3) / 0.017453292519943295;
                for (str1 = new StringBuffer().append(Math.round(100.0 * geocentricElong3) / 100.0).append('°').toString(); str1.length() < 7; str1 = String.valueOf(str1) + " ") {}
                mAge = Math.round(240.0 * (jdDARCL3 - jD[n2 - N])) / 10.0;
                final String[] dataStr8 = this.dataStr;
                final int n11 = n2 - N;
                dataStr8[n11] = String.valueOf(dataStr8[n11]) + "   arcl " + str1 + "  age " + mAge + "h" + "\n";
            }
        }
        this.jdNextNew = jD[nn - N + 1];
        if (showFrame) {
            this.jdNextNew = jD[nn - N + 1];
            for (int n3 = 0; n3 <= 14; ++n3) {
                final String[] dataStr9 = this.dataStr;
                final int n12 = n3;
                dataStr9[n12] = String.valueOf(dataStr9[n12]) + "\n";
            }
            this.dataStr[15] = "Current date : " + this.dat.toString() + "\n";
            this.dataStr[16] = "After prev. New Moon : " + this.comp.DaysHoursMinutes(curJD - this.jdLastNew) + "\n";
            this.dataStr[17] = "Before next New Moon : " + this.comp.DaysHoursMinutes(this.jdNextNew - curJD) + "\n";
            this.dataStr[18] = "Current Moon age     : " + this.moonAgeStr + "\n";
            String LatLongStr = String.valueOf(Math.abs(Math.round(1000.0 * this.latitude) / 1000.0));
            if (this.latitude >= 0.0) {
                LatLongStr = String.valueOf(LatLongStr) + "N";
            }
            else {
                LatLongStr = String.valueOf(LatLongStr) + "N";
            }
            LatLongStr = String.valueOf(LatLongStr) + " " + Math.abs(Math.round(1000.0 * this.longitude) / 1000.0);
            if (this.longitude >= 0.0) {
                LatLongStr = String.valueOf(LatLongStr) + "E";
            }
            else {
                LatLongStr = String.valueOf(LatLongStr) + "W";
            }
            if (this.usrString.equals("Demo running")) {
                for (int k = 0; k <= 14; ++k) {
                    if (k % 3 == 0) {
                        this.dataStr[k] = " D E M O\n";
                    }
                }
            }
            final scrollFrame sf = new scrollFrame("Crescent Moon Visibility " + (this.year + 1900) + " at " + LatLongStr, 18, this.dataStr);
            sf.resize(900, 280);
            sf.show();
        }
    }
}

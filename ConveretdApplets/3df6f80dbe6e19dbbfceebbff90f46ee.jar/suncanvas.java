import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.Date;
import java.awt.Image;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

class suncanvas extends Canvas
{
    String str;
    final int y90 = 124;
    final int y0 = 336;
    final int xS = 330;
    final int xL = 60;
    final int xOben = 60;
    final int xLL = 60;
    final char deg = '°';
    final int unten = 530;
    final int Radius = 8;
    String s;
    int hInt;
    int azInt;
    int hBildInt;
    int min;
    double hBild;
    double xx;
    double STD;
    double equation;
    double trans;
    double diff;
    double hRise;
    double hSet;
    Image myImage;
    Date dat;
    public compute comp;
    int browserOffset;
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
    String ghaStr;
    String eqtStr;
    String riseStr;
    String setStr;
    String transStr;
    String locStr;
    String heightStr;
    String azStr;
    double[] riseArray;
    double[] setArray;
    double[] transArray;
    double[] lengthArray;
    double[] lengthMonth;
    double[] lengthCivilMonth;
    double[] lengthNautMonth;
    double[] lengthAstroMonth;
    double lengthYear;
    double lengthCivilYear;
    double lengthNautYear;
    double lengthAstroYear;
    double[] civilRiseArray;
    double[] civilSetArray;
    double[] nautRiseArray;
    double[] nautSetArray;
    double[] astroRiseArray;
    double[] astroSetArray;
    double[] isVisible;
    double[] lenDayArray;
    int[] xArray;
    int[] hSunBild;
    int xSun;
    int hSun;
    String versStr;
    String gmtStr;
    String declinStr;
    boolean myConsole;
    String dirStr;
    int currentCount;
    boolean mySunboxState;
    int daysInYear;
    double currentDec;
    double currentHoehe;
    String[] monthArray;
    double K;
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
    double lenDay;
    double lenCivilNight;
    double lenNautNight;
    double lenAstroNight;
    double currentDayLen;
    String astroNightString;
    String currentAstroNightString;
    double UT;
    String[] dataStr;
    boolean online;
    double diffLenDay;
    String diffLenDayStr;
    horizonFrame hf;
    boolean horVisible;
    Image appleMap;
    mapFrame mf;
    hoursMonthFrame hrsf;
    hoursDayFrame hrsdf;
    boolean drawOK;
    boolean minmaxOK;
    boolean sunOK;
    boolean hoursOK;
    double[] riseUT;
    double[] setUT;
    double utR;
    double utS;
    int maxD;
    int maxM;
    int minD;
    int minM;
    double maxL;
    double minL;
    int maxN;
    int minN;
    boolean summary;
    semidurationFrame sdf;
    
    public suncanvas(final Image MAP, final Image bild, final Date myDate, final double myLat, final double myLong, final String myLoc, final int myLocOffset, final String versionStr, final boolean console, final boolean sunboxState, final boolean write, final boolean isYear, final boolean sum, final boolean myOnline, final boolean showSun, final boolean showHours) {
        this.s = "";
        this.ghaStr = "?";
        this.eqtStr = "?";
        this.riseStr = "?";
        this.setStr = "?";
        this.transStr = "?";
        this.locStr = "?";
        this.heightStr = "?";
        this.azStr = "?";
        this.riseArray = new double[370];
        this.setArray = new double[370];
        this.transArray = new double[370];
        this.lengthArray = new double[370];
        this.lengthMonth = new double[12];
        this.lengthCivilMonth = new double[12];
        this.lengthNautMonth = new double[12];
        this.lengthAstroMonth = new double[12];
        this.lengthYear = 0.0;
        this.lengthCivilYear = 0.0;
        this.lengthNautYear = 0.0;
        this.lengthAstroYear = 0.0;
        this.civilRiseArray = new double[370];
        this.civilSetArray = new double[370];
        this.nautRiseArray = new double[370];
        this.nautSetArray = new double[370];
        this.astroRiseArray = new double[370];
        this.astroSetArray = new double[370];
        this.isVisible = new double[370];
        this.lenDayArray = new double[370];
        this.xArray = new int[25];
        this.hSunBild = new int[25];
        this.currentCount = 0;
        this.monthArray = new String[12];
        this.K = 0.017453292519943295;
        this.lenDay = 0.0;
        this.lenCivilNight = 0.0;
        this.lenNautNight = 0.0;
        this.lenAstroNight = 0.0;
        this.currentDayLen = 0.0;
        this.currentAstroNightString = "--:-- ";
        this.dataStr = new String[460];
        this.diffLenDay = 0.0;
        this.diffLenDayStr = "";
        this.horVisible = false;
        this.drawOK = true;
        this.riseUT = new double[370];
        this.setUT = new double[370];
        this.maxL = 0.0;
        this.minL = 30.0;
        this.maxN = 0;
        this.minN = 0;
        this.monthArray = new String[] { "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec" };
        this.appleMap = MAP;
        this.summary = sum;
        this.myImage = bild;
        this.versStr = versionStr;
        this.myConsole = console;
        this.mySunboxState = sunboxState;
        this.online = myOnline;
        this.sunOK = showSun;
        this.hoursOK = showHours;
        this.latitude = myLat;
        this.longitude = myLong;
        this.locStr = myLoc;
        this.dat = myDate;
        this.hours = this.dat.getHours();
        this.date = this.dat.getDate();
        this.month = this.dat.getMonth();
        this.minutes = this.dat.getMinutes();
        this.year = this.dat.getYear();
        this.browserOffset = myLocOffset;
        this.locOffset = myLocOffset;
        this.STD = this.hours - this.locOffset + this.minutes / 60.0 + this.seconds / 3600.0;
        this.UT = this.hours + this.minutes / 60.0 + this.seconds / 3600.0 + this.dat.getTimezoneOffset() / 60;
        this.comp = new compute();
        double JD = this.comp.JD(this.date, this.month + 1, this.year + 1900, this.STD);
        this.dec = this.comp.sunDecRA(1, JD);
        this.currentDec = this.dec;
        double RA = this.comp.sunDecRA(2, JD);
        this.GHA = this.comp.sun_GHA(JD, RA);
        this.GHA %= 360.0;
        if (this.GHA < 0.0) {
            this.GHA += 360.0;
        }
        this.str = String.valueOf(Math.round(10.0 * this.GHA) / 10.0);
        this.s = this.str.substring(0, this.str.indexOf(".") + 2);
        this.ghaStr = "GHA      = " + this.s + '°';
        this.equation = this.eot(this.date, this.month + 1, this.year + 1900, this.STD);
        this.diff = Math.abs(this.equation - (int)this.equation);
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
            this.str = ":";
        }
        else {
            this.str = ":0";
        }
        this.eqtStr = "Equation of Time = " + (int)this.equation + this.str + this.min + " min";
        if (this.equation < 0.0 && (int)this.equation == 0) {
            this.eqtStr = "Equation of Time = -" + (int)this.equation + this.str + this.min + " min";
        }
        this.trans = 12.0 - this.equation / 60.0 - this.longitude / 15.0;
        this.trans += this.locOffset;
        this.transStr = this.makeTimeString("Culm.       ", this.trans);
        this.STD = this.hours - this.locOffset + this.minutes / 60.0 + this.seconds / 3600.0;
        JD = this.comp.JD(this.date, this.month + 1, this.year + 1900, this.STD);
        this.dec = this.comp.sunDecRA(1, JD);
        RA = this.comp.sunDecRA(2, JD);
        this.hoehe = this.comp.sun_elev(JD, this.latitude, -this.longitude, this.dec, RA);
        this.currentHoehe = this.hoehe;
        this.str = String.valueOf(Math.round(10.0 * this.hoehe) / 10.0);
        this.s = this.str.substring(0, this.str.indexOf(".") + 2);
        this.heightStr = "Altitude   = " + this.s + '°';
        if (this.mySunboxState) {
            for (int i = -this.locOffset; i < -this.locOffset + 25; ++i) {
                JD = this.comp.JD(this.date, this.month + 1, this.year + 1900, i);
                this.dec = this.comp.sunDecRA(1, JD);
                RA = this.comp.sunDecRA(2, JD);
                final double h = this.comp.sun_elev(JD, this.latitude, -this.longitude, this.dec, RA);
                this.hBild = h * 212.0 / 90.0;
                this.hBildInt = (int)Math.round(this.hBild);
                this.hSunBild[i + this.locOffset] = 336 - this.hBildInt;
                final double gha = this.comp.sun_GHA(JD, RA);
                this.azimut = this.comp.computeAzimut(this.dec, this.latitude, this.longitude, gha, h);
                if (this.latitude >= this.dec) {
                    this.xx = (180.0 - this.azimut) * 270.0 / 180.0;
                }
                else if (this.azimut < 180.0) {
                    this.xx = this.azimut * 270.0 / 180.0;
                }
                else {
                    this.xx = -(360.0 - this.azimut) * 270.0 / 180.0;
                }
                if (this.latitude >= this.dec) {
                    this.xx = 330.0 - this.xx;
                }
                else {
                    this.xx += 330.0;
                }
                this.xSun = (int)Math.round(this.xx);
                this.xArray[i + this.locOffset] = this.xSun;
            }
        }
        this.azimut = this.comp.computeAzimut(this.currentDec, this.latitude, this.longitude, this.GHA, this.hoehe);
        this.str = String.valueOf(Math.round(10.0 * this.azimut) / 10.0);
        this.s = this.str.substring(0, this.str.indexOf(".") + 2);
        this.azStr = "Azimuth  = " + this.s + '°';
        this.dirStr = this.comp.azDirection(this.azimut);
        this.hBild = this.hoehe * 212.0 / 90.0;
        this.hBildInt = (int)Math.round(this.hBild);
        this.hSun = this.hBildInt;
        if (this.latitude >= this.dec) {
            this.xx = (180.0 - this.azimut) * 270.0 / 180.0;
        }
        else if (this.azimut < 180.0) {
            this.xx = this.azimut * 270.0 / 180.0;
        }
        else {
            this.xx = -(360.0 - this.azimut) * 270.0 / 180.0;
        }
        if (this.latitude >= this.dec) {
            this.xx = 330.0 - this.xx;
        }
        else {
            this.xx += 330.0;
        }
        this.xSun = (int)Math.round(this.xx);
        String consoleString = "";
        String riseString = "";
        String setString = "";
        String civilRiseString = "";
        String civilSetString = "";
        String nautRiseString = "";
        String nautSetString = "";
        String astroRiseString = "";
        String astroSetString = "";
        String transString = "";
        String eotString = "";
        this.astroNightString = "";
        int count = 0;
        final int currentDate = this.date;
        final int currentMonth = this.month;
        final String strich = " --:--";
        String nsStr;
        if (this.latitude < 0.0) {
            nsStr = "S";
        }
        else {
            nsStr = "N";
        }
        String ewStr;
        if (this.longitude < 0.0) {
            ewStr = "W";
        }
        else {
            ewStr = "E";
        }
        String tzString = "";
        tzString = String.valueOf(Math.abs(this.locOffset));
        if (this.locOffset > 0) {
            this.str = " + " + this.locOffset;
        }
        else {
            this.str = " - " + Math.abs(this.locOffset);
        }
        this.dataStr[0] = "\n";
        this.dataStr[1] = String.valueOf(this.locStr) + "    " + Math.abs(this.latitude) + " " + nsStr + "   " + Math.abs(this.longitude) + " " + ewStr + "   Time Zone = UT" + this.str + " h" + "\n";
        int kData = 4;
        if (!this.mySunboxState) {
            for (int m = 0; m < 12; ++m) {
                this.month = m;
                if (!isYear) {
                    this.dataStr[2] = "                   astr   naut  civil   RISE           Trans        SET         civil   naut   astr     daylight       diff    Culm    EoT  astr night\n";
                    this.dataStr[3] = "\t\t\t\t                     h:m    h                h:m     h                             h:m      h        m:s     deg     min   hours\n";
                }
                else {
                    this.dataStr[kData] = "\n";
                    final String[] dataStr = this.dataStr;
                    final int n = kData;
                    dataStr[n] = String.valueOf(dataStr[n]) + "                   astr   naut  civil   RISE           Trans        SET         civil   naut   astr     daylight       diff    Culm    EoT  astr night\n";
                    final String[] dataStr2 = this.dataStr;
                    final int n2 = kData;
                    dataStr2[n2] = String.valueOf(dataStr2[n2]) + "\t\t\t\t                     h:m    h                h:m     h                             h:m      h        m:s     deg     min   hours\n";
                    ++kData;
                }
                for (int d = 1; d <= this.daysInMonth(this.month, this.year + 1900); ++d) {
                    this.date = d;
                    ++count;
                    this.equation = this.eot(this.date, this.month + 1, this.year + 1900, 12.0 - this.locOffset);
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
                        this.str = ":";
                    }
                    else {
                        this.str = ":0";
                    }
                    eotString = String.valueOf((int)this.equation) + this.str + this.min;
                    if (this.equation >= 0.0) {
                        eotString = "+" + eotString;
                    }
                    if (Math.abs(this.equation) < 10.0) {
                        eotString = " " + eotString;
                    }
                    if (this.equation < 0.0 && (int)this.equation == 0) {
                        eotString = " -" + (int)this.equation + this.str + this.min;
                    }
                    this.trans = 12.0 - this.equation / 60.0 - this.longitude / 15.0;
                    JD = this.comp.JD(this.date, this.month + 1, this.year + 1900, this.trans);
                    final double hRound = Math.round(this.comp.sun_elev(JD, this.latitude, -this.longitude, this.comp.sunDecRA(1, JD), this.comp.sunDecRA(2, JD)) * 10.0) / 10.0;
                    this.trans += this.locOffset;
                    if (this.trans < 0.0) {
                        this.trans += 24.0;
                    }
                    this.transArray[count] = this.trans;
                    if (this.month == currentMonth && this.date == currentDate) {
                        this.currentCount = count;
                    }
                    JD = this.comp.JD(this.date, this.month + 1, this.year + 1900, 12.0 - this.locOffset);
                    final double sunDecRA = this.comp.sunDecRA(1, JD);
                    this.dec = sunDecRA;
                    final double declin = sunDecRA;
                    this.isVisible[count] = Math.sin(this.K * declin) / Math.cos(this.K * this.latitude);
                    for (int what = 1; what < 5; ++what) {
                        this.RISE = false;
                        this.SETT = false;
                        for (int j = -this.locOffset; j < -this.locOffset + 24; ++j) {
                            this.riseset(d, m + 1, this.year + 1900, j, what);
                            if (this.RISE && this.SETT) {
                                break;
                            }
                        }
                        if (this.RISE || this.SETT) {
                            if (this.RISE) {
                                this.utR = this.UTRISE;
                                this.UTRISE += this.locOffset;
                                if (this.UTRISE > 24.0) {
                                    this.UTRISE -= 24.0;
                                }
                                if (this.UTRISE < 0.0) {
                                    this.UTRISE += 24.0;
                                }
                            }
                            if (this.SETT) {
                                this.utS = this.UTSET;
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
                                        this.riseArray[count] = this.UTRISE;
                                        riseString = this.makeTimeString("", this.UTRISE);
                                        this.riseUT[count] = this.utR;
                                    }
                                    if (this.SETT) {
                                        this.setArray[count] = this.UTSET;
                                        setString = this.makeTimeString("", this.UTSET);
                                        this.setUT[count] = this.utS;
                                    }
                                    if (this.RISE && this.SETT) {
                                        this.lenDay = this.UTSET - this.UTRISE;
                                        if (this.lenDay < 0.0) {
                                            this.lenDay += 24.0;
                                        }
                                        transString = this.makeTimeString("", this.transArray[count]);
                                        this.lenDayArray[count] = this.lenDay;
                                    }
                                    if (m != currentMonth || d != currentDate) {
                                        break;
                                    }
                                    if (this.RISE) {
                                        this.hRise = this.riseArray[count];
                                        this.riseStr = this.makeTimeString("Sunrise   ", this.hRise);
                                        this.currentDayLen = this.UTSET - this.UTRISE;
                                    }
                                    else if (this.ABOVE) {
                                        this.riseStr = "Sun visible all day";
                                        this.setStr = "Sun visible all day";
                                    }
                                    else {
                                        this.riseStr = "Sun invisible all day";
                                        this.setStr = "Sun invisible all day";
                                    }
                                    if (this.SETT) {
                                        this.hSet = this.setArray[count];
                                        this.setStr = this.makeTimeString("Sunset   ", this.hSet);
                                        break;
                                    }
                                    if (this.ABOVE) {
                                        this.riseStr = "Sun visible all day";
                                        this.setStr = "Sun visible all day";
                                        break;
                                    }
                                    this.riseStr = "Sun invisible all day";
                                    this.setStr = "Sun invisible all day";
                                    break;
                                }
                                case 2: {
                                    if (this.RISE) {
                                        this.civilRiseArray[count] = this.UTRISE;
                                        civilRiseString = this.makeTimeString("", this.UTRISE);
                                    }
                                    if (this.SETT) {
                                        this.civilSetArray[count] = this.UTSET;
                                        civilSetString = this.makeTimeString("", this.UTSET);
                                    }
                                    if (this.RISE && this.SETT) {
                                        this.lenCivilNight = 24.0 - (this.UTSET - this.UTRISE);
                                        break;
                                    }
                                    break;
                                }
                                case 3: {
                                    if (this.RISE) {
                                        this.nautRiseArray[count] = this.UTRISE;
                                        nautRiseString = this.makeTimeString("", this.UTRISE);
                                    }
                                    if (this.SETT) {
                                        this.nautSetArray[count] = this.UTSET;
                                        nautSetString = this.makeTimeString("", this.UTSET);
                                    }
                                    if (this.RISE && this.SETT) {
                                        this.lenNautNight = 24.0 - (this.UTSET - this.UTRISE);
                                        break;
                                    }
                                    break;
                                }
                                case 4: {
                                    if (this.RISE) {
                                        this.astroRiseArray[count] = this.UTRISE;
                                        astroRiseString = this.makeTimeString("", this.UTRISE);
                                    }
                                    if (this.SETT) {
                                        this.astroSetArray[count] = this.UTSET;
                                        astroSetString = this.makeTimeString("", this.UTSET);
                                    }
                                    if (!this.RISE || !this.SETT) {
                                        break;
                                    }
                                    this.lenAstroNight = 24.0 - (this.UTSET - this.UTRISE);
                                    this.astroNightString = this.makeTimeString("", this.lenAstroNight);
                                    if (m == currentMonth && d == currentDate) {
                                        this.currentAstroNightString = this.astroNightString;
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
                                        this.riseArray[count] = 0.0;
                                        this.setArray[count] = 24.0;
                                        this.lenDay = 24.0;
                                        transString = this.makeTimeString("", this.transArray[count]);
                                        this.lenDayArray[count] = this.lenDay;
                                    }
                                    else {
                                        this.riseArray[count] = this.transArray[count];
                                        this.setArray[count] = this.transArray[count];
                                        this.lenDay = 0.0;
                                        transString = this.makeTimeString("", this.transArray[count]);
                                        this.lenDayArray[count] = this.lenDay;
                                    }
                                    riseString = strich;
                                    setString = strich;
                                    if (m != currentMonth || d != currentDate) {
                                        break;
                                    }
                                    if (this.ABOVE) {
                                        this.riseStr = "Sun visible all day";
                                        this.setStr = "Sun visible all day";
                                        break;
                                    }
                                    this.riseStr = "Sun unvisible all day";
                                    this.setStr = "Sun unvisible all day";
                                    break;
                                }
                                case 2: {
                                    if (this.ABOVE) {
                                        this.civilRiseArray[count] = 0.0;
                                        this.civilSetArray[count] = 24.0;
                                    }
                                    else {
                                        this.civilRiseArray[count] = this.transArray[count];
                                        this.civilSetArray[count] = this.transArray[count];
                                    }
                                    civilRiseString = strich;
                                    civilSetString = strich;
                                    this.lenCivilNight = 0.0;
                                    break;
                                }
                                case 3: {
                                    if (this.ABOVE) {
                                        this.nautRiseArray[count] = 0.0;
                                        this.nautSetArray[count] = 24.0;
                                    }
                                    else {
                                        this.nautRiseArray[count] = this.transArray[count];
                                        this.nautSetArray[count] = this.transArray[count];
                                    }
                                    nautRiseString = strich;
                                    nautSetString = strich;
                                    this.lenNautNight = 0.0;
                                    break;
                                }
                                case 4: {
                                    if (this.ABOVE) {
                                        this.astroRiseArray[count] = 0.0;
                                        this.astroSetArray[count] = 24.0;
                                    }
                                    else {
                                        this.astroRiseArray[count] = this.transArray[count];
                                        this.astroSetArray[count] = this.transArray[count];
                                    }
                                    astroRiseString = strich;
                                    astroSetString = strich;
                                    this.astroNightString = strich;
                                    this.lenAstroNight = 0.0;
                                    break;
                                }
                            }
                        }
                    }
                    this.lengthMonth[m] += this.lenDay;
                    this.lengthCivilMonth[m] += this.lenCivilNight;
                    this.lengthNautMonth[m] += this.lenNautNight;
                    this.lengthAstroMonth[m] += this.lenAstroNight;
                    this.diffLenDayStr = this.comp.MS(this.lenDayArray[count] - this.lenDayArray[count - 1]);
                    if (count == 1) {
                        this.diffLenDayStr = "--:--";
                    }
                    if (m == currentMonth && write && !isYear) {
                        if (this.date < 10) {
                            consoleString = String.valueOf(this.dayString(JD)) + " " + this.monthArray[m] + " 0";
                        }
                        else {
                            consoleString = String.valueOf(this.dayString(JD)) + " " + this.monthArray[m] + " ";
                        }
                        String lStr = String.valueOf(this.trim(this.lenDay, 4));
                        if (this.lenDay < 10.0) {
                            lStr = String.valueOf(lStr);
                        }
                        final String rStr = String.valueOf(this.trim(this.riseArray[count], 4));
                        final String sStr = String.valueOf(this.trim(this.setArray[count], 4));
                        this.dataStr[kData] = String.valueOf(consoleString) + this.date + " " + (this.year + 1900) + "  " + astroRiseString + " " + nautRiseString + " " + civilRiseString + " " + riseString + "  " + rStr + "  " + transString + "  " + setString + "  " + sStr + "  " + civilSetString + " " + nautSetString + " " + astroSetString + " " + this.makeTimeString("", this.lenDay) + "   " + lStr + "   " + this.diffLenDayStr + "    " + hRound + "  " + eotString + "  " + this.astroNightString + "\n";
                        ++kData;
                    }
                    if (write && isYear) {
                        if (this.date < 10) {
                            consoleString = String.valueOf(this.dayString(JD)) + " " + this.monthArray[m] + " 0";
                        }
                        else {
                            consoleString = String.valueOf(this.dayString(JD)) + " " + this.monthArray[m] + " ";
                        }
                        final String lStr = String.valueOf(this.trim(this.lenDay, 4));
                        final String rStr = String.valueOf(this.trim(this.riseArray[count], 4));
                        final String sStr = String.valueOf(this.trim(this.setArray[count], 4));
                        this.dataStr[kData] = String.valueOf(consoleString) + this.date + " " + (this.year + 1900) + "  " + astroRiseString + " " + nautRiseString + " " + civilRiseString + " " + riseString + "  " + rStr + "  " + transString + "  " + setString + "  " + sStr + "  " + civilSetString + " " + nautSetString + " " + astroSetString + " " + this.makeTimeString("", this.lenDay) + "   " + lStr + "   " + this.diffLenDayStr + "    " + hRound + "  " + eotString + "  " + this.astroNightString + "\n";
                        ++kData;
                    }
                }
                if ((m == currentMonth && write) || isYear) {
                    final int days = this.daysInMonth(m, this.year + 1900);
                    final String str1 = String.valueOf(Math.round(100.0 * this.lengthMonth[m]) / 100.0);
                    final String str2 = String.valueOf(Math.round(100.0 * this.lengthCivilMonth[m]) / 100.0);
                    final String str3 = String.valueOf(Math.round(100.0 * this.lengthNautMonth[m]) / 100.0);
                    final String str4 = String.valueOf(Math.round(100.0 * this.lengthAstroMonth[m]) / 100.0);
                    this.dataStr[kData] = String.valueOf(this.monthArray[m]) + " " + (this.year + 1900) + "   daylight hours      = " + str1 + " = " + Math.round(10000.0 * this.lengthMonth[m] / (days * 24)) / 100.0 + " %" + "\n";
                    ++kData;
                    this.dataStr[kData] = String.valueOf(this.monthArray[m]) + " " + (this.year + 1900) + "   mean daylight hours = " + Math.round(100.0 * this.lengthMonth[m] / days) / 100.0 + " h" + "\n";
                    ++kData;
                    this.dataStr[kData] = String.valueOf(this.monthArray[m]) + " " + (this.year + 1900) + "   civil night hours   = " + str2 + " = " + Math.round(10000.0 * this.lengthCivilMonth[m] / (days * 24)) / 100.0 + " %" + "\n";
                    ++kData;
                    this.dataStr[kData] = String.valueOf(this.monthArray[m]) + " " + (this.year + 1900) + "   naut. night hours   = " + str3 + " = " + Math.round(10000.0 * this.lengthNautMonth[m] / (days * 24)) / 100.0 + " %" + "\n";
                    ++kData;
                    this.dataStr[kData] = String.valueOf(this.monthArray[m]) + " " + (this.year + 1900) + "   astron. night hours = " + str4 + " = " + Math.round(10000.0 * this.lengthAstroMonth[m] / (days * 24)) / 100.0 + " %" + "\n";
                    ++kData;
                }
                this.lengthYear += this.lengthMonth[m];
                this.lengthCivilYear += this.lengthCivilMonth[m];
                this.lengthNautYear += this.lengthNautMonth[m];
                this.lengthAstroYear += this.lengthAstroMonth[m];
            }
            if (write && !isYear) {
                final scrollFrame sf = new scrollFrame("Sun " + (this.year + 1900) + "  " + this.monthArray[currentMonth] + ",  " + Math.abs(this.latitude) + '°' + " " + nsStr + "   " + Math.abs(this.longitude) + '°', kData, this.dataStr, this.online);
                sf.resize(920, 490);
                sf.show();
            }
        }
        else {
            this.currentRiseSet();
        }
        int z = 0;
        for (int k = 0; k < 12; ++k) {
            for (int d2 = 1; d2 <= this.daysInMonth(k, this.year + 1900); ++d2) {
                if (k == currentMonth && d2 == currentDate) {
                    this.currentCount = z;
                    break;
                }
                ++z;
            }
        }
        this.daysInYear = count;
        if (write && isYear) {
            final String str5 = String.valueOf(Math.round(100.0 * this.lengthYear) / 100.0);
            final String str6 = String.valueOf(Math.round(100.0 * this.lengthCivilYear) / 100.0);
            final String str7 = String.valueOf(Math.round(100.0 * this.lengthNautYear) / 100.0);
            final String str8 = String.valueOf(Math.round(100.0 * this.lengthAstroYear) / 100.0);
            final String str9 = String.valueOf(Math.round(100.0 * (this.NumberOfDays() * 24 - this.lengthYear)) / 100.0);
            this.dataStr[kData] = "\n";
            ++kData;
            this.dataStr[kData] = "Year " + (this.year + 1900) + "  daylight hours      = " + str5 + " = " + Math.round(10000.0 * this.lengthYear / (this.NumberOfDays() * 24)) / 100.0 + " %" + "\n";
            ++kData;
            this.dataStr[kData] = "Year " + (this.year + 1900) + "  mean daylight hours = " + Math.round(100.0 * this.lengthYear / this.NumberOfDays()) / 100.0 + " h" + "\n";
            ++kData;
            this.dataStr[kData] = "Year " + (this.year + 1900) + "  night hours         = " + str9 + " = " + Math.round(10000.0 * (this.NumberOfDays() * 24 - this.lengthYear) / (this.NumberOfDays() * 24)) / 100.0 + " %" + "\n";
            ++kData;
            this.dataStr[kData] = "Year " + (this.year + 1900) + "  civil night hours   = " + str6 + " = " + Math.round(10000.0 * this.lengthCivilYear / (this.NumberOfDays() * 24)) / 100.0 + " %" + "\n";
            ++kData;
            this.dataStr[kData] = "Year " + (this.year + 1900) + "  naut. night hours   = " + str7 + " = " + Math.round(10000.0 * this.lengthNautYear / (this.NumberOfDays() * 24)) / 100.0 + " %" + "\n";
            ++kData;
            this.dataStr[kData] = "Year " + (this.year + 1900) + "  astron. night hours = " + str8 + " = " + Math.round(10000.0 * this.lengthAstroYear / (this.NumberOfDays() * 24)) / 100.0 + " %" + "\n";
            ++kData;
        }
        if (write && isYear) {
            int min_N = 0;
            int max_N = 100;
            double earlyRise = 23.99;
            double lateRise = 0.1;
            double earlySet = 23.99;
            double lateSet = 0.1;
            int earlyR = 0;
            int lateR = 0;
            int earlyS = 0;
            int lateS = 0;
            int dEarlyR = 0;
            int mEarlyR = 0;
            int dLateR = 0;
            int mLateR = 0;
            int dEarlyS = 0;
            int mEarlyS = 0;
            int dLateS = 0;
            int mLateS = 0;
            for (int l = 1; l <= this.daysInYear; ++l) {
                if (this.lenDayArray[l] > this.maxL) {
                    this.maxL = this.lenDayArray[l];
                    max_N = l;
                }
                if (this.lenDayArray[l] < this.minL) {
                    this.minL = this.lenDayArray[l];
                    min_N = l;
                }
                if (this.riseUT[l] < earlyRise) {
                    earlyRise = this.riseUT[l];
                    earlyR = l;
                }
                if (this.riseUT[l] > lateRise) {
                    lateRise = this.riseUT[l];
                    lateR = l;
                }
                if (this.setUT[l] < earlySet) {
                    earlySet = this.setUT[l];
                    earlyS = l;
                }
                if (this.setUT[l] > lateSet) {
                    lateSet = this.setUT[l];
                    lateS = l;
                }
            }
            int SUM = 0;
            for (int m2 = 0; m2 < 12; ++m2) {
                for (int d3 = 1; d3 <= this.daysInMonth(m2, this.year + 1900); ++d3) {
                    if (++SUM == max_N) {
                        this.maxD = d3;
                        this.maxM = m2;
                    }
                    if (SUM == min_N) {
                        this.minD = d3;
                        this.minM = m2;
                    }
                    if (SUM == earlyR) {
                        dEarlyR = d3;
                        mEarlyR = m2;
                    }
                    if (SUM == lateR) {
                        dLateR = d3;
                        mLateR = m2;
                    }
                    if (SUM == earlyS) {
                        dEarlyS = d3;
                        mEarlyS = m2;
                    }
                    if (SUM == lateS) {
                        dLateS = d3;
                        mLateS = m2;
                    }
                }
            }
            final int kSum = kData;
            this.dataStr[kData] = "\nLongest Day:  " + this.comp.hms(this.maxL, " h  ") + this.monthArray[this.maxM] + " " + this.maxD + ", Rise " + this.comp.hms(this.riseUT[max_N], " UT") + ", Set " + this.comp.hms(this.setUT[max_N], " UT") + "\n";
            ++kData;
            this.dataStr[kData] = "Shortest Day: " + this.comp.hms(this.minL, " h  ") + this.monthArray[this.minM] + " " + this.minD + ", Rise " + this.comp.hms(this.riseUT[min_N], " UT") + ", Set " + this.comp.hms(this.setUT[min_N], " UT") + "\n" + "\n";
            ++kData;
            this.dataStr[kData] = "Earliest Sunrise:  " + this.comp.hms(this.riseUT[earlyR], " UT") + "  " + this.monthArray[mEarlyR] + " " + dEarlyR + ", Set " + this.comp.hms(this.setUT[earlyR], " UT") + "\n";
            ++kData;
            this.dataStr[kData] = "Latest Sunrise:    " + this.comp.hms(this.riseUT[lateR], " UT") + "  " + this.monthArray[mLateR] + " " + dLateR + ", Set " + this.comp.hms(this.setUT[lateR], " UT") + "\n";
            ++kData;
            this.dataStr[kData] = "Earliest Sunset:   " + this.comp.hms(this.setUT[earlyS], " UT") + "  " + this.monthArray[mEarlyS] + " " + dEarlyS + ", Rise " + this.comp.hms(this.riseUT[earlyS], " UT") + "\n";
            ++kData;
            this.dataStr[kData] = "Latest Sunset:     " + this.comp.hms(this.setUT[lateS], " UT") + "  " + this.monthArray[mLateS] + " " + dLateS + ", Rise " + this.comp.hms(this.riseUT[lateS], " UT") + "\n";
            ++kData;
            if (this.summary) {
                for (int i2 = 0; i2 <= 6; ++i2) {
                    this.dataStr[3 + i2] = this.dataStr[kSum + i2];
                }
                final String[] dataStr3 = this.dataStr;
                final int n3 = 8;
                dataStr3[n3] = String.valueOf(dataStr3[n3]) + "\n";
                for (int i3 = 1; i3 <= 6; ++i3) {
                    this.dataStr[9 + i3] = this.dataStr[kSum - 7 + i3];
                }
                final scrollFrame sf2 = new scrollFrame("Summary " + (this.year + 1900), 15, this.dataStr, this.online);
                sf2.resize(450, 260);
                sf2.show();
            }
            else {
                final scrollFrame sf3 = new scrollFrame("Sun " + (this.year + 1900) + ",  " + Math.abs(this.latitude) + '°' + " " + nsStr + "   " + Math.abs(this.longitude) + '°' + " " + ewStr, kData, this.dataStr, this.online);
                sf3.resize(920, 490);
                sf3.show();
            }
        }
        if (this.mySunboxState) {
            String stdString = "";
            if (this.hours >= 0) {
                stdString = String.valueOf(this.hours);
            }
            if (this.hours < 0) {
                stdString = String.valueOf(this.hours + 24);
            }
            if (this.hours > 24) {
                stdString = String.valueOf(this.hours - 24);
            }
            String latStr = new StringBuffer().append(Math.abs(this.latitude)).append('°').toString();
            if (this.latitude >= 0.0) {
                latStr = String.valueOf(latStr) + " N";
            }
            else {
                latStr = String.valueOf(latStr) + " S";
            }
            String longStr = new StringBuffer().append(Math.abs(this.longitude)).append('°').toString();
            if (this.longitude >= 0.0) {
                longStr = String.valueOf(longStr) + " E";
            }
            else {
                longStr = String.valueOf(longStr) + " W";
            }
            String horStr = String.valueOf(this.locStr) + ", " + latStr + "  " + longStr + ",  " + (this.year + 1900) + " " + this.monthArray[this.month] + " " + this.date + ", " + stdString + ":";
            if (this.minutes < 10) {
                horStr = String.valueOf(horStr) + "0" + this.minutes;
            }
            else {
                horStr = String.valueOf(horStr) + this.minutes;
            }
            if (this.hf == null) {
                (this.hf = new horizonFrame("The Sun at " + horStr, this.myImage, this.online, this.currentHoehe, this.xSun, this.hSun, this.latitude, this.currentDec, this.xArray, this.hSunBild)).resize(580, 370);
                this.hf.show();
            }
        }
    }
    
    public void doMap() {
        String stdString = "";
        if (this.hours + this.locOffset >= 0) {
            stdString = String.valueOf(this.hours + this.locOffset);
        }
        if (this.hours + this.locOffset < 0) {
            stdString = String.valueOf(this.hours + this.locOffset + 24);
        }
        if (this.hours + this.locOffset > 24) {
            stdString = String.valueOf(this.hours + this.locOffset - 24);
        }
        String datetimeStr = String.valueOf(this.year + 1900) + " " + this.monthArray[this.month] + " " + this.date + ", at " + stdString + ":";
        if (this.minutes < 10) {
            datetimeStr = String.valueOf(datetimeStr) + "0" + this.minutes;
        }
        else {
            datetimeStr = String.valueOf(datetimeStr) + this.minutes;
        }
        (this.mf = new mapFrame(datetimeStr, this.appleMap, this.dec, this.GHA, this.latitude, this.longitude, this.online)).resize(510, 290);
        this.mf.show();
    }
    
    public void doDay() {
        (this.hrsdf = new hoursDayFrame("Daylight Hours " + (this.year + 1900) + " per Day", this.year, this.latitude, this.longitude, this.lenDayArray, this.online)).resize(575, 450);
        this.hrsdf.show();
    }
    
    public void doMonth() {
        (this.hrsf = new hoursMonthFrame("Daylight Hours " + (this.year + 1900) + " per Month", this.latitude, this.longitude, this.lengthMonth, this.online)).resize(550, 420);
        this.hrsf.show();
    }
    
    public void doSemiduration() {
        (this.sdf = new semidurationFrame("Duration of Sunlight ", this.year, this.online)).resize(730, 630);
        this.sdf.show();
    }
    
    public String trim(final double x, final int t) {
        String str = "";
        switch (t) {
            case 5: {
                str = String.valueOf(str) + Math.round(100000.0 * x) / 100000.0;
                break;
            }
            case 4: {
                str = String.valueOf(str) + Math.round(10000.0 * x) / 10000.0;
                break;
            }
            case 3: {
                str = String.valueOf(str) + Math.round(1000.0 * x) / 1000.0;
                break;
            }
            case 2: {
                str = String.valueOf(str) + Math.round(100.0 * x) / 100.0;
                break;
            }
            case 1: {
                str = String.valueOf(str) + Math.round(10.0 * x) / 10.0;
                break;
            }
        }
        for (int n = str.lastIndexOf("."); n > str.length() - (t + 1); str = String.valueOf(str) + "0") {}
        return str;
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
    
    public double deltaPSI(final double T) {
        final double K = 0.017453292519943295;
        final double LS = this.sunL(T);
        double LM = 218.3165 + 481267.8813 * T;
        LM %= 360.0;
        if (LM < 0.0) {
            LM += 360.0;
        }
        final double omega = 125.04452 - 1934.136261 * T + 0.0020708 * T * T + T * T * T / 450000.0;
        double deltaPsi = -17.2 * Math.sin(0.017453292519943295 * omega) - 1.32 * Math.sin(0.03490658503988659 * LS) - 0.23 * Math.sin(0.03490658503988659 * LM) + 0.21 * Math.sin(0.03490658503988659 * omega);
        deltaPsi /= 3600.0;
        return deltaPsi;
    }
    
    public double EPS(final double T) {
        final double K = 0.017453292519943295;
        final double LS = this.sunL(T);
        final double LM = 218.3165 + 481267.8813 * T;
        final double eps0 = 23.43929111111111 - (46.815 * T + 5.9E-4 * T * T - 0.001813 * T * T * T) / 3600.0;
        final double omega = 125.04452 - 1934.136261 * T + 0.0020708 * T * T + T * T * T / 450000.0;
        final double deltaEps = (9.2 * Math.cos(0.017453292519943295 * omega) + 0.57 * Math.cos(0.03490658503988659 * LS) + 0.1 * Math.cos(0.03490658503988659 * LM) - 0.09 * Math.cos(0.03490658503988659 * omega)) / 3600.0;
        return eps0 + deltaEps;
    }
    
    public double eot(final int date, final int month, final int year, final double UT) {
        final double K = 0.017453292519943295;
        final double T = (this.comp.JD(date, month, year, UT) - 2451545.0) / 36525.0;
        final double eps = this.EPS(T);
        final double RA = this.RightAscension(T);
        final double LS = this.sunL(T);
        final double deltaPsi = this.deltaPSI(T);
        double E = LS - 0.0057183 - RA + deltaPsi * Math.cos(0.017453292519943295 * eps);
        if (E > 5.0) {
            E -= 360.0;
        }
        E *= 4.0;
        return E;
    }
    
    public double RightAscension(final double T) {
        final double K = 0.017453292519943295;
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
        return RA;
    }
    
    public String makeTimeString(final String whatStr, double time) {
        String str = "?";
        if (time < 0.0) {
            time += 24.0;
        }
        if (time > 24.0) {
            time -= 24.0;
        }
        final double diff = time - (int)time;
        int min = (int)Math.round(diff * 60.0);
        if (min == 60) {
            min = 0;
            ++time;
        }
        if (min > 9) {
            str = ":";
        }
        else {
            str = ":0";
        }
        str = String.valueOf(String.valueOf((int)time) + str + min);
        if (time < 10.0) {
            str = "0" + str;
        }
        return String.valueOf(whatStr) + " " + str;
    }
    
    public void riseset(final int DATE, final int MONTH, final int YEAR, final double HOUR, final int what) {
        this.comp = new compute();
        final double K = 0.017453292519943295;
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
    
    public int daysInMonth(final int m, int y) {
        int n = 31;
        y = y;
        if (m == 0 || m == 2 || m == 4 || m == 6 || m == 7 || m == 9 || m == 11) {
            n = 31;
        }
        if (m == 3 || m == 5 || m == 8 || m == 10) {
            n = 30;
        }
        if (m == 1) {
            n = 28;
            if (y % 4 == 0) {
                n = 29;
            }
            if (y % 100 == 0) {
                n = 28;
            }
            if (y % 400 == 0) {
                n = 29;
            }
        }
        return n;
    }
    
    public int NumberOfDays() {
        return this.daysInYear;
    }
    
    public String dayString(final double jd) {
        final String[] dayArray = { "", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun" };
        long num = (long)(jd + 0.5);
        num = num - num / 7L * 7L + 1L;
        return dayArray[(int)num];
    }
    
    public void currentRiseSet() {
        this.RISE = false;
        this.SETT = false;
        for (int i = -this.locOffset; i < -this.locOffset + 24; ++i) {
            this.riseset(this.date, this.month + 1, this.year + 1900, i, 1);
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
                this.riseStr = this.makeTimeString("", this.UTRISE);
            }
            if (this.SETT) {
                this.setStr = this.makeTimeString("", this.UTSET);
            }
            if (this.RISE && this.SETT) {
                this.lenDay = this.UTSET - this.UTRISE;
                this.currentDayLen = this.UTSET - this.UTRISE;
            }
        }
        if (this.RISE) {
            this.hRise = this.UTRISE;
            this.riseStr = this.makeTimeString("Sunrise   ", this.hRise);
        }
        else if (this.ABOVE) {
            this.riseStr = "Sun visible all day";
            this.setStr = "Sun visible all day";
        }
        else {
            this.riseStr = "Sun unvisible all day";
            this.setStr = "Sun unvisible all day";
        }
        if (this.SETT) {
            this.hSet = this.UTSET;
            this.setStr = this.makeTimeString("Sunset   ", this.hSet);
        }
        else if (this.ABOVE) {
            this.riseStr = "Sun visible all day";
            this.setStr = "Sun visible all day";
        }
        else {
            this.riseStr = "Sun unvisible all day";
            this.setStr = "Sun unvisible all day";
        }
    }
    
    public void paint(final Graphics g) {
        Font f = g.getFont();
        g.setFont(new Font("Helvetica", 0, 10));
        g.setColor(Color.red);
        g.drawString("© 1999-2011  J. Giesen -- " + this.versStr, 450, 93);
        this.hours = this.dat.getHours();
        this.minutes = this.dat.getMinutes();
        this.seconds = this.dat.getSeconds();
        this.date = this.dat.getDate();
        this.month = this.dat.getMonth();
        this.year = this.dat.getYear();
        this.hours -= this.locOffset;
        if (this.hours < 0) {
            this.hours += 24;
        }
        if (this.hours >= 24) {
            this.hours -= 24;
        }
        if (this.minutes > 9) {
            this.str = ":";
        }
        else {
            this.str = ":0";
        }
        this.str = String.valueOf(this.hours) + this.str + this.minutes;
        if (this.seconds > 9) {
            this.str = String.valueOf(this.str) + ":";
        }
        else {
            this.str = String.valueOf(this.str) + ":0";
        }
        this.str = String.valueOf(this.str) + this.seconds;
        g.setColor(Color.blue);
        this.gmtStr = this.str;
        g.drawString(String.valueOf(this.str) + " UT", 215, 93);
        this.str = new StringBuffer().append(Math.abs(this.latitude)).append('°').toString();
        if (this.latitude > 0.0) {
            this.str = String.valueOf(this.str) + " N";
        }
        else {
            this.str = String.valueOf(this.str) + " S";
        }
        g.drawString(this.str, 310, 93);
        this.str = new StringBuffer().append(Math.abs(this.longitude)).append('°').toString();
        if (this.longitude > 0.0) {
            this.str = String.valueOf(this.str) + " E";
        }
        else {
            this.str = String.valueOf(this.str) + " W";
        }
        g.drawString(this.str, 370, 93);
        g.setColor(Color.black);
        String str = this.dat.toString();
        g.drawString(str, 60, 570);
        str = String.valueOf(this.currentDec);
        this.s = str.substring(0, str.indexOf(".") + 2);
        this.declinStr = "Declin.  = " + this.s + '°';
        g.drawString("Declin. = " + this.s + '°', 60, 585);
        g.drawString(this.ghaStr, 60, 600);
        g.drawString(this.eqtStr, 60, 615);
        g.setColor(Color.red);
        g.drawString(this.locStr, 230, 570);
        g.setColor(Color.black);
        g.drawString(this.riseStr, 230, 585);
        g.drawString(this.transStr, 230, 600);
        g.drawString("Daylight:      " + this.makeTimeString("", this.currentDayLen) + " hrs", 350, 600);
        if (!this.mySunboxState) {
            g.drawString("Astron. Night: " + this.currentAstroNightString + " hrs", 350, 615);
        }
        g.drawString(this.setStr, 230, 615);
        g.setColor(Color.black);
        g.drawString(this.heightStr, 350, 570);
        g.drawString(String.valueOf(this.azStr) + " (" + this.dirStr + ")", 350, 585);
        f = g.getFont();
        g.setFont(new Font("Helvetica", 0, 9));
        g.setColor(Color.black);
        int x2 = 0;
        final int yMitte = 330;
        final int yFaktor = 18;
        final int left = 60;
        final int xRight = 60 + this.daysInYear * 2;
        if (!this.mySunboxState) {
            for (int i = 0; i < 25; ++i) {
                final int y1 = 330 + Math.round(18 * (-12 + i));
                g.drawLine(60, y1, 55, y1);
            }
            for (int j = 0; j < 9; ++j) {
                final int y1 = 330 + Math.round(18 * (12 - 3 * j));
                g.setColor(Color.lightGray);
                g.drawLine(60, y1, xRight, y1);
                str = String.valueOf(String.valueOf(j * 3)) + " h";
                g.setColor(Color.black);
                g.drawString(str, 37, y1 + 4);
            }
            for (int k = 1; k < this.daysInYear; ++k) {
                final int x3 = 60 + k * 2;
                x2 = 60 + (k + 1) * 2;
                g.setColor(Color.pink);
                int y1 = 330 + (int)Math.round(18.0 * (12.0 - this.riseArray[k]));
                int y2 = 330 + (int)Math.round(18.0 * (12.0 - this.riseArray[k + 1]));
                g.drawLine(x3, y1, x2, y2);
                final double LD = this.lenDayArray[k];
                if (LD > this.maxL) {
                    this.maxL = LD;
                    this.maxN = k;
                }
                if (LD < this.minL) {
                    this.minL = LD;
                    this.minN = k;
                }
                if (this.myConsole) {
                    g.setColor(Color.blue);
                    y1 = 330 + (int)Math.round(18.0 * (12.0 - this.civilRiseArray[k]));
                    y2 = 330 + (int)Math.round(18.0 * (12.0 - this.civilRiseArray[k + 1]));
                    if (this.civilRiseArray[k] <= this.transArray[k] && this.civilRiseArray[k + 1] <= this.transArray[k + 1]) {
                        g.drawLine(x3, y1, x2, y2);
                    }
                    final Color c = new Color(0, 0, 200);
                    g.setColor(c);
                    y1 = 330 + (int)Math.round(18.0 * (12.0 - this.nautRiseArray[k]));
                    y2 = 330 + (int)Math.round(18.0 * (12.0 - this.nautRiseArray[k + 1]));
                    if (this.nautRiseArray[k] <= this.transArray[k] && this.nautRiseArray[k + 1] <= this.transArray[k + 1]) {
                        g.drawLine(x3, y1, x2, y2);
                    }
                    g.setColor(Color.black);
                    y2 = 330 + Math.round(216.0f);
                    y1 = 330 + (int)Math.round(18.0 * (12.0 - this.astroRiseArray[k]));
                    if (this.astroRiseArray[k] <= this.transArray[k] && this.astroRiseArray[k + 1] <= this.transArray[k + 1]) {
                        g.drawLine(x3, y1, x3, y2);
                        g.drawLine(x3 + 1, y1, x3 + 1, y2);
                    }
                }
                else {
                    g.setColor(Color.blue);
                    y2 = 330 + Math.round(216.0f);
                    if (this.riseArray[k] <= this.transArray[k] && this.riseArray[k + 1] <= this.transArray[k + 1]) {
                        g.drawLine(x3, y1, x3, y2);
                        g.drawLine(x3 + 1, y1, x3 + 1, y2);
                    }
                    if (this.isVisible[k] < -1.0 && this.isVisible[k + 1] < -1.0) {
                        y1 = 330 - Math.round(216.0f);
                        y2 = 330 + Math.round(216.0f);
                        g.drawLine(x3, y1, x3, y2);
                        g.drawLine(x3 + 1, y1, x3 + 1, y2);
                    }
                }
                g.setColor(Color.pink);
                y1 = 330 - (int)Math.round(18.0 * (this.setArray[k] - 12.0));
                y2 = 330 - (int)Math.round(18.0 * (this.setArray[k + 1] - 12.0));
                g.drawLine(x3, y1, x2, y2);
                if (this.myConsole) {
                    g.setColor(Color.blue);
                    y1 = 330 - (int)Math.round(18.0 * (this.civilSetArray[k] - 12.0));
                    y2 = 330 - (int)Math.round(18.0 * (this.civilSetArray[k + 1] - 12.0));
                    if (this.civilSetArray[k] >= this.transArray[k] && this.civilSetArray[k + 1] >= this.transArray[k + 1]) {
                        g.drawLine(x3, y1, x2, y2);
                    }
                    final Color c = new Color(0, 0, 200);
                    g.setColor(c);
                    y1 = 330 - (int)Math.round(18.0 * (this.nautSetArray[k] - 12.0));
                    y2 = 330 - (int)Math.round(18.0 * (this.nautSetArray[k + 1] - 12.0));
                    if (this.nautSetArray[k] >= this.transArray[k] && this.nautSetArray[k + 1] >= this.transArray[k]) {
                        g.drawLine(x3, y1, x2, y2);
                    }
                    g.setColor(Color.black);
                    y2 = 330 - Math.round(216.0f);
                    y1 = 330 - (int)Math.round(18.0 * (this.astroSetArray[k] - 12.0));
                    if (this.astroSetArray[k] >= this.transArray[k] && this.astroSetArray[k + 1] >= this.transArray[k + 1]) {
                        g.drawLine(x3, y1, x3, y2);
                        g.drawLine(x3 + 1, y1, x3 + 1, y2);
                    }
                }
                else {
                    g.setColor(Color.blue);
                    y2 = 330 - Math.round(216.0f);
                    if (this.setArray[k] >= this.transArray[k] && this.setArray[k + 1] >= this.transArray[k + 1]) {
                        g.drawLine(x3, y1, x3, y2);
                        g.drawLine(x3 + 1, y1, x3 + 1, y2);
                    }
                }
                g.setColor(Color.black);
                y1 = 330 - (int)Math.round(18.0 * (this.transArray[k] - 12.0));
                y2 = 330 - (int)Math.round(18.0 * (this.transArray[k + 1] - 12.0));
                g.drawLine(x3, y1, x2, y2);
            }
            int SUM = 0;
            for (int m = 0; m < 12; ++m) {
                for (int d = 1; d <= this.daysInMonth(m, this.year + 1900); ++d) {
                    if (++SUM == this.maxN) {
                        this.maxD = d;
                        this.maxM = m;
                    }
                    if (SUM == this.minN) {
                        this.minD = d;
                        this.minM = m;
                    }
                }
            }
            String st = "";
            if ((int)this.minL < 10) {
                st = " 0";
            }
            g.setFont(new Font("Helvetica", 0, 10));
            g.drawString("Long. Day:  " + (int)this.maxL + "h " + (int)Math.round(60.0 * this.comp.frac(this.maxL)) + "m  " + this.monthArray[this.maxM] + " " + this.maxD, 350, 630);
            g.drawString("Short. Day: " + st + (int)this.minL + "h " + (int)Math.round(60.0 * this.comp.frac(this.minL)) + "m  " + this.monthArray[this.minM] + " " + this.minD, 350, 645);
            g.setFont(new Font("Helvetica", 0, 9));
            int sum = 0;
            int y1 = 330 - Math.round(216.0f);
            int y2 = 330 + Math.round(216.0f);
            g.drawLine(60, y1, 60, y2);
            for (int l = 0; l < 12; ++l) {
                sum += this.daysInMonth(l, this.year + 1900);
                final int x3 = 60 + 2 * sum;
                g.setColor(Color.lightGray);
                g.drawLine(x3, y1, x3, y2);
                g.setColor(Color.black);
                g.drawString(this.monthArray[l], 75 + l * 69, y1 - 5);
                if (this.hoursOK) {
                    g.drawString(String.valueOf(Math.round(this.lengthMonth[l])), 75 + l * 69, 315);
                }
            }
            if (this.hoursOK) {
                g.drawString(" h", 852, 315);
            }
            g.setColor(Color.red);
            g.setFont(new Font("Helvetica", 0, 10));
            g.drawString("Year Daylight: " + String.valueOf(Math.round(this.lengthYear)) + " h", 505, 570);
            if (this.sunOK) {
                y1 = 330 + (int)Math.round(18.0 * (12.0 - (this.STD + this.locOffset)));
                g.setColor(Color.red);
                g.drawLine(60, y1, xRight, y1);
                final int x3 = 60 + this.currentCount * 2;
                y1 = 330 - Math.round(216.0f);
                y2 = 330 + Math.round(216.0f);
                g.drawLine(x3, y1, x3, y2);
                g.setColor(Color.red);
                final double time = this.STD + this.locOffset;
                y1 = 330 + (int)Math.round(18.0 * (12.0 - time));
                if (time > this.riseArray[this.currentCount] && time < this.setArray[this.currentCount]) {
                    g.setColor(Color.yellow);
                }
                if ((time > this.civilRiseArray[this.currentCount] && time < this.riseArray[this.currentCount]) || (time > this.setArray[this.currentCount] && time < this.civilSetArray[this.currentCount])) {
                    g.setColor(new Color(255, 200, 0));
                }
                if ((time > this.nautRiseArray[this.currentCount] && time < this.civilRiseArray[this.currentCount]) || (time > this.civilSetArray[this.currentCount] && time < this.nautSetArray[this.currentCount])) {
                    g.setColor(new Color(255, 150, 0));
                }
                if ((time > this.astroRiseArray[this.currentCount] && time < this.nautRiseArray[this.currentCount]) || (time > this.nautSetArray[this.currentCount] && time < this.astroSetArray[this.currentCount])) {
                    g.setColor(new Color(255, 100, 0));
                }
                g.fillOval(x3 - 8, y1 - 8, 16, 16);
                g.setColor(Color.red);
                g.drawOval(x3 - 8, y1 - 8, 16, 16);
            }
        }
        g.setColor(Color.black);
        g.setFont(f);
        g.clearRect(1, 1, this.size().width, this.size().height);
    }
}

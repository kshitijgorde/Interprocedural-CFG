import java.awt.Color;
import java.awt.Font;
import java.util.Map;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Graphics;
import java.util.Date;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

class suncanvas extends Canvas
{
    String str;
    final int xOben = 60;
    final int xL = 60;
    final char deg = '°';
    String s;
    int min;
    double STD;
    double equation;
    double trans;
    double diff;
    double hRise;
    double hSet;
    Date dat;
    public compute comp;
    int browserOffset;
    int locOffset;
    int year;
    int month;
    int date;
    int hours;
    int minutes;
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
    double[] isVisible;
    String versStr;
    String gmtStr;
    String declinStr;
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
    double UT;
    String[] dataStr;
    boolean online;
    double jd;
    double currentDayLen;
    int monat;
    int MONAT;
    int dy;
    double yFaktor;
    String aboveStr;
    int daysAbove;
    int daysBelow;
    
    public suncanvas(final double zoom, final int startMonth, final Date myDate, final double myLat, final double myLong, final String myLoc, final int myLocOffset, final String versionStr, final boolean myOnline) {
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
        this.isVisible = new double[370];
        this.monthArray = new String[12];
        this.K = 0.017453292519943295;
        this.dataStr = new String[460];
        this.monthArray = new String[] { "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec" };
        this.MONAT = startMonth;
        this.yFaktor = zoom;
        this.versStr = versionStr;
        this.online = myOnline;
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
        this.STD = this.hours - this.locOffset + this.minutes / 60.0;
        this.UT = this.hours + this.minutes / 60.0 + this.dat.getTimezoneOffset() / 60.0;
        this.comp = new compute();
        this.jd = this.comp.JD(this.date, this.month + 1, this.year + 1900, 12.0);
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
        this.equation = this.comp.EOT(JD);
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
        this.eqtStr = "EoT " + (int)this.equation + this.str + this.min + " min";
        if (this.equation < 0.0 && (int)this.equation == 0) {
            this.eqtStr = "EoT -" + (int)this.equation + this.str + this.min + " min";
        }
        this.trans = 12.0 - this.equation / 60.0 - this.longitude / 15.0;
        this.trans += this.locOffset;
        this.transStr = this.comp.makeTimeString("Transit  ", this.trans);
        this.STD = this.hours - this.locOffset + this.minutes / 60.0;
        JD = this.comp.JD(this.date, this.month + 1, this.year + 1900, this.STD);
        this.dec = this.comp.sunDecRA(1, JD);
        RA = this.comp.sunDecRA(2, JD);
        this.hoehe = this.comp.sun_elev(JD, this.latitude, -this.longitude, this.dec, RA);
        this.currentHoehe = this.hoehe;
        this.str = String.valueOf(Math.round(10.0 * this.hoehe) / 10.0);
        this.s = this.str.substring(0, this.str.indexOf(".") + 2);
        this.heightStr = "Altitude   = " + this.s + '°';
        this.azimut = this.comp.computeAzimut(this.currentDec, this.latitude, this.longitude, this.GHA, this.hoehe);
        this.str = String.valueOf(Math.round(10.0 * this.azimut) / 10.0);
        this.s = this.str.substring(0, this.str.indexOf(".") + 2);
        this.azStr = "Azimuth  = " + this.s + '°';
        String riseString = "";
        String setString = "";
        String transString = "";
        String eotString = "";
        int count = 0;
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
        this.dataStr[1] = String.valueOf(this.locStr) + "    " + Math.abs(this.latitude) + " " + nsStr + ",  " + Math.abs(this.longitude) + " " + ewStr + "   Time Zone = UT" + this.str + " h" + "\n";
        final int kData = 4;
        if (this.MONAT == 2) {
            for (int M = 2; M <= 4; ++M) {
                for (int d = 1; d <= this.daysInMonth(M - 1, this.year + 1900); ++d) {
                    ++count;
                    this.equation = this.comp.EOT(this.comp.JD(d, M, this.year + 1900, 12.0 - this.locOffset));
                    this.trans = 12.0 - this.equation / 60.0 - this.longitude / 15.0;
                    this.equation = this.comp.EOT(this.comp.JD(d, M, this.year + 1900, this.trans - this.locOffset));
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
                    this.trans += this.locOffset;
                    if (this.trans < 0.0) {
                        this.trans += 24.0;
                    }
                    this.transArray[count] = this.trans;
                    JD = this.comp.JD(d, M, this.year + 1900, 12.0 - this.locOffset);
                    final double sunDecRA = this.comp.sunDecRA(1, JD);
                    this.dec = sunDecRA;
                    final double declin = sunDecRA;
                    this.isVisible[count] = Math.sin(this.K * declin) / Math.cos(this.K * this.latitude);
                    this.RISE = false;
                    this.SETT = false;
                    for (int i = -this.locOffset; i < -this.locOffset + 24; ++i) {
                        this.riseset(d, M, this.year + 1900, i);
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
                            this.riseArray[count] = this.UTRISE;
                            riseString = this.comp.makeTimeString("", this.UTRISE);
                        }
                        if (this.SETT) {
                            this.setArray[count] = this.UTSET;
                            setString = this.comp.makeTimeString("", this.UTSET);
                        }
                        if (this.RISE && this.SETT) {
                            transString = this.comp.makeTimeString("", this.transArray[count]);
                        }
                    }
                    else {
                        if (this.ABOVE) {
                            this.riseArray[count] = 0.0;
                            this.setArray[count] = 24.0;
                            transString = this.comp.makeTimeString("", this.transArray[count]);
                        }
                        else {
                            this.riseArray[count] = this.transArray[count];
                            this.setArray[count] = this.transArray[count];
                            transString = this.comp.makeTimeString("", this.transArray[count]);
                        }
                        riseString = strich;
                        setString = strich;
                    }
                }
            }
        }
        else if (this.MONAT == 8) {
            count = 0;
            for (int M = 8; M <= 10; ++M) {
                for (int d = 1; d <= this.daysInMonth(M - 1, this.year + 1900); ++d) {
                    ++count;
                    this.equation = this.comp.EOT(this.comp.JD(d, M, this.year + 1900, 12.0 - this.locOffset));
                    this.trans = 12.0 - this.equation / 60.0 - this.longitude / 15.0;
                    this.equation = this.comp.EOT(this.comp.JD(d, M, this.year + 1900, this.trans - this.locOffset));
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
                    this.trans += this.locOffset;
                    if (this.trans < 0.0) {
                        this.trans += 24.0;
                    }
                    this.transArray[count] = this.trans;
                    JD = this.comp.JD(d, M, this.year + 1900, 12.0 - this.locOffset);
                    final double sunDecRA2 = this.comp.sunDecRA(1, JD);
                    this.dec = sunDecRA2;
                    final double declin = sunDecRA2;
                    this.isVisible[count] = Math.sin(this.K * declin) / Math.cos(this.K * this.latitude);
                    this.RISE = false;
                    this.SETT = false;
                    for (int i = -this.locOffset; i < -this.locOffset + 24; ++i) {
                        this.riseset(d, M, this.year + 1900, i);
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
                            this.riseArray[count] = this.UTRISE;
                            riseString = this.comp.makeTimeString("", this.UTRISE);
                        }
                        if (this.SETT) {
                            this.setArray[count] = this.UTSET;
                            setString = this.comp.makeTimeString("", this.UTSET);
                        }
                        if (this.RISE && this.SETT) {
                            transString = this.comp.makeTimeString("", this.transArray[count]);
                        }
                    }
                    else {
                        if (this.ABOVE) {
                            this.riseArray[count] = 0.0;
                            this.setArray[count] = 24.0;
                            transString = this.comp.makeTimeString("", this.transArray[count]);
                        }
                        else {
                            this.riseArray[count] = this.transArray[count];
                            this.setArray[count] = this.transArray[count];
                            transString = this.comp.makeTimeString("", this.transArray[count]);
                        }
                        riseString = strich;
                        setString = strich;
                    }
                }
            }
        }
        else {
            count = 0;
            this.monat = this.MONAT;
            for (int d2 = 10; d2 <= this.daysInMonth(this.monat, this.year + 1900); ++d2) {
                ++count;
                this.equation = this.comp.EOT(this.comp.JD(d2, this.monat + 1, this.year + 1900, 12.0 - this.locOffset));
                this.trans = 12.0 - this.equation / 60.0 - this.longitude / 15.0;
                this.equation = this.comp.EOT(this.comp.JD(d2, this.monat + 1, this.year + 1900, this.trans - this.locOffset));
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
                this.trans += this.locOffset;
                if (this.trans < 0.0) {
                    this.trans += 24.0;
                }
                this.transArray[count] = this.trans;
                JD = this.comp.JD(d2, this.monat + 1, this.year + 1900, 12.0 - this.locOffset);
                final double sunDecRA3 = this.comp.sunDecRA(1, JD);
                this.dec = sunDecRA3;
                final double declin = sunDecRA3;
                this.isVisible[count] = Math.sin(this.K * declin) / Math.cos(this.K * this.latitude);
                this.RISE = false;
                this.SETT = false;
                for (int j = -this.locOffset; j < -this.locOffset + 24; ++j) {
                    this.riseset(d2, this.monat + 1, this.year + 1900, j);
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
                        this.riseArray[count] = this.UTRISE;
                        riseString = this.comp.makeTimeString("", this.UTRISE);
                    }
                    if (this.SETT) {
                        this.setArray[count] = this.UTSET;
                        setString = this.comp.makeTimeString("", this.UTSET);
                    }
                    if (this.RISE && this.SETT) {
                        transString = this.comp.makeTimeString("", this.transArray[count]);
                    }
                }
                else {
                    if (this.ABOVE) {
                        this.riseArray[count] = 0.0;
                        this.setArray[count] = 24.0;
                        transString = this.comp.makeTimeString("", this.transArray[count]);
                    }
                    else {
                        this.riseArray[count] = this.transArray[count];
                        this.setArray[count] = this.transArray[count];
                        transString = this.comp.makeTimeString("", this.transArray[count]);
                    }
                    riseString = strich;
                    setString = strich;
                }
            }
            this.monat = this.MONAT + 1;
            for (int d = 1; d <= this.daysInMonth(this.monat, this.year + 1900); ++d) {
                ++count;
                this.equation = this.comp.EOT(this.comp.JD(d, this.monat + 1, this.year + 1900, 12.0 - this.locOffset));
                this.trans = 12.0 - this.equation / 60.0 - this.longitude / 15.0;
                this.equation = this.comp.EOT(this.comp.JD(d, this.monat + 1, this.year + 1900, this.trans - this.locOffset));
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
                JD = this.comp.JD(d, this.monat + 1, this.year + 1900, this.trans);
                this.trans += this.locOffset;
                if (this.trans < 0.0) {
                    this.trans += 24.0;
                }
                this.transArray[count] = this.trans;
                JD = this.comp.JD(d, this.monat + 1, this.year + 1900, 12.0 - this.locOffset);
                final double sunDecRA4 = this.comp.sunDecRA(1, JD);
                this.dec = sunDecRA4;
                final double declin = sunDecRA4;
                this.isVisible[count] = Math.sin(this.K * declin) / Math.cos(this.K * this.latitude);
                this.RISE = false;
                this.SETT = false;
                for (int i = -this.locOffset; i < -this.locOffset + 24; ++i) {
                    this.riseset(d, this.monat + 1, this.year + 1900, i);
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
                        this.riseArray[count] = this.UTRISE;
                        riseString = this.comp.makeTimeString("", this.UTRISE);
                    }
                    if (this.SETT) {
                        this.setArray[count] = this.UTSET;
                        setString = this.comp.makeTimeString("", this.UTSET);
                    }
                    if (this.RISE && this.SETT) {
                        transString = this.comp.makeTimeString("", this.transArray[count]);
                    }
                }
                else {
                    if (this.ABOVE) {
                        this.riseArray[count] = 0.0;
                        this.setArray[count] = 24.0;
                        transString = this.comp.makeTimeString("", this.transArray[count]);
                    }
                    else {
                        this.riseArray[count] = this.transArray[count];
                        this.setArray[count] = this.transArray[count];
                        transString = this.comp.makeTimeString("", this.transArray[count]);
                    }
                    riseString = strich;
                    setString = strich;
                }
            }
            this.monat = this.MONAT + 2;
            if (this.monat == 12) {
                this.monat = 0;
            }
            if (this.monat == 0) {
                this.dy = 1;
            }
            else {
                this.dy = 0;
            }
            for (int d3 = 1; d3 <= this.daysInMonth(this.monat, this.year + 1900 + this.dy); ++d3) {
                ++count;
                this.equation = this.comp.EOT(this.comp.JD(d3, this.monat + 1, this.year + 1900 + this.dy, 12.0 - this.locOffset));
                this.trans = 12.0 - this.equation / 60.0 - this.longitude / 15.0;
                this.equation = this.comp.EOT(this.comp.JD(d3, this.monat + 1, this.year + 1900 + this.dy, this.trans - this.locOffset));
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
                this.trans += this.locOffset;
                if (this.trans < 0.0) {
                    this.trans += 24.0;
                }
                this.transArray[count] = this.trans;
                JD = this.comp.JD(d3, this.monat + 1, this.year + 1900 + this.dy, 12.0 - this.locOffset);
                final double sunDecRA5 = this.comp.sunDecRA(1, JD);
                this.dec = sunDecRA5;
                final double declin = sunDecRA5;
                this.isVisible[count] = Math.sin(this.K * declin) / Math.cos(this.K * this.latitude);
                this.RISE = false;
                this.SETT = false;
                for (int k = -this.locOffset; k < -this.locOffset + 24; ++k) {
                    this.riseset(d3, this.monat + 1, this.year + 1900 + this.dy, k);
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
                        this.riseArray[count] = this.UTRISE;
                        riseString = this.comp.makeTimeString("", this.UTRISE);
                    }
                    if (this.SETT) {
                        this.setArray[count] = this.UTSET;
                        setString = this.comp.makeTimeString("", this.UTSET);
                    }
                    if (this.RISE && this.SETT) {
                        transString = this.comp.makeTimeString("", this.transArray[count]);
                    }
                }
                else {
                    if (this.ABOVE) {
                        this.riseArray[count] = 0.0;
                        this.setArray[count] = 24.0;
                        transString = this.comp.makeTimeString("", this.transArray[count]);
                    }
                    else {
                        this.riseArray[count] = this.transArray[count];
                        this.setArray[count] = this.transArray[count];
                        transString = this.comp.makeTimeString("", this.transArray[count]);
                    }
                    riseString = strich;
                    setString = strich;
                }
            }
        }
        this.RiseSet(this.year);
    }
    
    public void riseset(final int DATE, final int MONTH, final int YEAR, final double HOUR) {
        this.comp = new compute();
        final double K = 0.017453292519943295;
        final double sh = Math.sin(-0.014543828656868749);
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
    
    public String dayString(final double jd) {
        final String[] dayArray = { "", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun" };
        long num = (long)(jd + 0.5);
        num = num - num / 7L * 7L + 1L;
        return dayArray[(int)num];
    }
    
    public void currentRiseSet(final int date, final int month, final int year) {
        this.RISE = false;
        this.SETT = false;
        for (int i = -this.locOffset; i < -this.locOffset + 24; ++i) {
            this.riseset(date, month + 1, year + 1900, i);
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
                this.riseStr = this.comp.hms(this.UTRISE);
            }
            if (this.SETT) {
                this.setStr = this.comp.hms(this.UTSET);
            }
            this.currentDayLen = this.UTSET - this.UTRISE;
        }
        if (this.RISE) {
            this.hRise = this.UTRISE;
            this.riseStr = this.comp.hms(this.hRise);
        }
        else if (this.ABOVE) {
            this.riseStr = "Sun above";
            this.setStr = "Sun above";
            this.currentDayLen = 24.0;
        }
        else {
            this.riseStr = "Sun invisible";
            this.setStr = "Sun invisible";
            this.currentDayLen = 0.0;
        }
        if (this.SETT) {
            this.hSet = this.UTSET;
            this.setStr = this.comp.hms(this.hSet);
        }
        else if (this.ABOVE) {
            this.riseStr = "Sun above";
            this.setStr = "Sun above";
            this.currentDayLen = 24.0;
        }
        else {
            this.riseStr = "Sun invisible";
            this.setStr = "Sun invisible";
            this.currentDayLen = 0.0;
        }
    }
    
    public void RiseSet(final int year) {
        final double jd1 = this.comp.JD(1, 1, year + 1900, 12.0);
        int count = 0;
        final int[] aboveFirst = new int[750];
        final int[] aboveLast = new int[750];
        final int[] belowFirst = new int[750];
        final int[] belowLast = new int[750];
        for (int m = 0; m < 12; ++m) {
            for (int d = 1; d <= this.daysInMonth(m, year); ++d) {
                this.RISE = false;
                this.SETT = false;
                for (int i = -this.locOffset; i < -this.locOffset + 24; ++i) {
                    this.riseset(d, m + 1, year + 1900, i);
                    if (this.RISE && this.SETT) {
                        break;
                    }
                }
                if (!this.RISE) {
                    if (this.ABOVE) {
                        aboveFirst[count] = 1;
                        belowFirst[count] = 0;
                    }
                    else {
                        belowFirst[count] = 1;
                        aboveFirst[count] = 0;
                    }
                }
                if (!this.SETT) {
                    if (this.ABOVE) {
                        aboveLast[count] = 1;
                        belowLast[count] = 0;
                    }
                    else {
                        belowLast[count] = 1;
                        aboveLast[count] = 0;
                    }
                }
                ++count;
            }
        }
        int firstAbove = 0;
        for (int i = 1; i <= count; ++i) {
            if (aboveFirst[i - 1] == 0 && aboveFirst[i] == 1) {
                firstAbove = i;
            }
        }
        final double jdFirst = jd1 + firstAbove;
        int lastAbove = 0;
        for (int j = 1; j <= count; ++j) {
            if (aboveLast[j - 1] == 1 && aboveLast[j] == 0) {
                lastAbove = j;
            }
        }
        final double jdLast = jd1 + lastAbove;
        for (int k = 0; k < 4; ++k) {
            for (int d2 = 1; d2 <= this.daysInMonth(k, year + 1); ++d2) {
                this.RISE = false;
                this.SETT = false;
                for (int l = -this.locOffset; l < -this.locOffset + 24; ++l) {
                    this.riseset(d2, k + 1, year + 1900 + 1, l);
                    if (this.RISE && this.SETT) {
                        break;
                    }
                }
                if (!this.RISE) {
                    if (this.ABOVE) {
                        aboveFirst[count] = 1;
                        belowFirst[count] = 0;
                    }
                    else {
                        belowFirst[count] = 1;
                        aboveFirst[count] = 0;
                    }
                }
                if (!this.SETT) {
                    if (this.ABOVE) {
                        aboveLast[count] = 1;
                        belowLast[count] = 0;
                    }
                    else {
                        belowLast[count] = 1;
                        aboveLast[count] = 0;
                    }
                }
                ++count;
            }
        }
        int firstBelow = 0;
        for (int l = 1; l <= count; ++l) {
            if (belowFirst[l - 1] == 0 && belowFirst[l] == 1) {
                firstBelow = l;
            }
        }
        final double jdFirst2 = jd1 + firstBelow;
        int lastBelow = 0;
        for (int i2 = 1; i2 <= count; ++i2) {
            if (belowLast[i2 - 1] == 1 && belowLast[i2] == 0) {
                lastBelow = i2;
            }
        }
        final double jdLast2 = jd1 + lastBelow;
        if (firstAbove > 0 && lastAbove > 0 && firstBelow > 0 && lastBelow > 0) {
            this.daysAbove = (int)(jdLast - jdFirst);
            this.daysBelow = (int)(jdLast2 - jdFirst2);
            this.aboveStr = "Sun visble all day from " + this.monthArray[this.comp.caldat(2, jdFirst) - 1] + "  " + this.comp.caldat(1, jdFirst) + "  until  " + this.monthArray[this.comp.caldat(2, jdLast) - 1] + "  " + this.comp.caldat(1, jdLast) + " (" + this.daysAbove + " d)";
            this.aboveStr = String.valueOf(this.aboveStr) + "      Sun invisble all day from " + this.monthArray[this.comp.caldat(2, jdFirst2) - 1] + "  " + this.comp.caldat(1, jdFirst2) + "  until  " + this.monthArray[this.comp.caldat(2, jdLast2) - 1] + "  " + this.comp.caldat(1, jdLast2) + " (" + (int)(jdLast2 - jdFirst2) + " d)";
        }
        else {
            this.aboveStr = "";
        }
    }
    
    public void paint(final Graphics g) {
        final RenderingHints renderHints = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        renderHints.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        final Graphics2D g2d = (Graphics2D)g;
        g2d.setRenderingHints(renderHints);
        Font f = g.getFont();
        g.setFont(new Font("Helvetica", 0, 10));
        g.drawString("Solstice Daylight " + this.versStr + "  © 2011  J. Giesen -- www.GeoAstro.de", 300, 652);
        g.setFont(new Font("Helvetica", 0, 11));
        this.hours = this.dat.getHours();
        this.minutes = this.dat.getMinutes();
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
        g.drawString(String.valueOf(this.str) + " UT", 150, 93);
        this.str = new StringBuffer().append(Math.abs(this.latitude)).append('°').toString();
        if (this.latitude > 0.0) {
            this.str = String.valueOf(this.str) + " N";
        }
        else {
            this.str = String.valueOf(this.str) + " S";
        }
        g.drawString(this.str, 225, 93);
        this.str = new StringBuffer().append(Math.abs(this.longitude)).append('°').toString();
        if (this.longitude > 0.0) {
            this.str = String.valueOf(this.str) + " E";
        }
        else {
            this.str = String.valueOf(this.str) + " W";
        }
        g.drawString(this.str, 275, 93);
        g.setColor(Color.black);
        g.drawString(this.year + 1900 + "  " + this.dayString(this.jd) + " " + this.monthArray[this.month] + " " + this.date, 50, 93);
        this.currentRiseSet(this.date, this.month, this.year);
        final double currentRise = this.hRise;
        final double currentSet = this.hSet;
        g.drawString("Rise  " + this.riseStr, 345, 93);
        g.drawString(this.transStr, 450, 93);
        g.drawString("Daylight  " + this.comp.hms(this.currentDayLen) + " h", 610, 93);
        String PM = this.eqtStr;
        if (this.date < this.daysInMonth(this.month, this.year)) {
            String pm = "+";
            this.currentRiseSet(this.date + 1, this.month, this.year);
            final double DayLen = this.currentDayLen;
            this.currentRiseSet(this.date, this.month, this.year);
            final double delta = DayLen - this.currentDayLen;
            if (delta < 0.0) {
                pm = " ";
            }
            PM = " " + pm + Math.round(600.0 * delta) / 10.0 + " min/day" + "   " + this.eqtStr;
        }
        g.drawString(PM, 720, 93);
        g.drawString("Set  " + this.setStr, 535, 93);
        g.setColor(Color.black);
        f = g.getFont();
        g.setColor(Color.black);
        int x2 = 0;
        final int yMitte = 370;
        final int left = 50;
        int N = 0;
        int right = 0;
        double rise31 = 0.0;
        double set31 = 0.0;
        double F = 0.0;
        if (this.MONAT == 4 || this.MONAT == 10) {
            rise31 = 0.5 * Math.round(2.0 * this.riseArray[25]);
            set31 = 0.5 * Math.round(2.0 * this.setArray[10]);
            F = 15.0;
            right = 788;
            N = 82;
        }
        if (this.MONAT == 2) {
            F = 5.0;
            N = this.daysInMonth(1, this.year + 1900) + this.daysInMonth(2, this.year + 1900) + this.daysInMonth(3, this.year + 1900);
            right = 50 + N * 9;
            rise31 = 0.5 * Math.round(2.0 * this.riseArray[1]);
            set31 = 0.5 * Math.round(2.0 * this.setArray[1]);
        }
        if (this.MONAT == 8) {
            F = 5.0;
            N = this.daysInMonth(7, this.year + 1900) + this.daysInMonth(8, this.year + 1900) + this.daysInMonth(9, this.year + 1900);
            right = 50 + N * 9;
            rise31 = 0.5 * Math.round(2.0 * this.riseArray[N]);
            set31 = 0.5 * Math.round(2.0 * this.setArray[N]);
        }
        g.drawLine(50, 370, right, 370);
        int y1 = 370 + (int)Math.round(F * this.yFaktor);
        g.setColor(Color.gray);
        g.drawLine(50, y1, right, y1);
        g.setColor(Color.blue);
        g.drawString(this.comp.makeTimeString("", rise31), 15, y1 + 5);
        g.setColor(Color.gray);
        y1 = 370 - (int)Math.round(F * this.yFaktor);
        g.drawLine(50, y1, right, y1);
        g.setColor(Color.red);
        g.drawString(this.comp.makeTimeString("", set31), 15, y1 + 5);
        g.setColor(Color.gray);
        y1 = 370 - (int)Math.round(F * this.yFaktor * 0.5);
        if (y1 > 120 && y1 < 620) {
            g.drawLine(50, y1, right, y1);
            g.setColor(Color.red);
            g.drawString(this.comp.makeTimeString("", set31 - 0.5), 15, y1 + 5);
        }
        g.setColor(Color.gray);
        y1 = 370 + (int)Math.round(F * this.yFaktor * 0.5);
        if (y1 > 120 && y1 < 620) {
            g.drawLine(50, y1, right, y1);
            g.setColor(Color.blue);
            g.drawString(this.comp.makeTimeString("", rise31 + 0.5), 15, y1 + 5);
        }
        if (this.MONAT == 4 || this.MONAT == 10) {
            g.setColor(Color.lightGray);
            y1 = 370 - (int)Math.round(F * this.yFaktor * 0.75);
            g.drawLine(50, y1, right, y1);
            if (y1 > 120 && y1 < 620) {
                g.setColor(Color.red);
                g.drawString(this.comp.makeTimeString("", set31 - 0.25), 15, y1 + 5);
            }
            g.setColor(Color.lightGray);
            y1 = 370 + (int)Math.round(F * this.yFaktor * 0.75);
            if (y1 > 120 && y1 < 620) {
                g.drawLine(50, y1, right, y1);
                g.setColor(Color.blue);
                g.drawString(this.comp.makeTimeString("", rise31 + 0.25), 15, y1 + 5);
            }
        }
        if (this.MONAT == 4 || this.MONAT == 10) {
            g.setColor(Color.lightGray);
            y1 = 370 - (int)Math.round(F * this.yFaktor * 1.25);
            if (y1 > 120 && y1 < 620) {
                g.drawLine(50, y1, right, y1);
                g.setColor(Color.red);
                g.drawString(this.comp.makeTimeString("", set31 + 0.25), 15, y1 + 5);
            }
            g.setColor(Color.lightGray);
            y1 = 370 + (int)Math.round(F * this.yFaktor * 1.25);
            if (y1 > 120 && y1 < 620) {
                g.drawLine(50, y1, right, y1);
                g.setColor(Color.blue);
                g.drawString(this.comp.makeTimeString("", rise31 - 0.25), 15, y1 + 5);
            }
        }
        g.setColor(Color.gray);
        y1 = 370 + (int)Math.round(F * this.yFaktor * 1.5);
        if (y1 > 120 && y1 < 620) {
            g.drawLine(50, y1, right, y1);
            g.setColor(Color.blue);
            g.drawString(this.comp.makeTimeString("", rise31 - 0.5), 15, y1 + 5);
        }
        if (this.MONAT == 4 || this.MONAT == 10) {
            g.setColor(Color.lightGray);
            y1 = 370 + (int)Math.round(F * this.yFaktor * 0.25);
            if (y1 > 120 && y1 < 620) {
                g.drawLine(50, y1, right, y1);
                g.setColor(Color.blue);
                g.drawString(this.comp.makeTimeString("", rise31 + 0.75), 15, y1 + 5);
            }
        }
        g.setColor(Color.gray);
        y1 = 370 + (int)Math.round(F * this.yFaktor * 1.5);
        if (y1 > 120 && y1 < 620) {
            g.drawLine(50, y1, right, y1);
            g.setColor(Color.blue);
            g.drawString(this.comp.makeTimeString("", rise31 - 0.5), 15, y1 + 5);
        }
        if (this.MONAT == 4 || this.MONAT == 10) {
            g.setColor(Color.lightGray);
            y1 = 370 - (int)Math.round(F * this.yFaktor * 0.25);
            if (y1 > 120 && y1 < 620) {
                g.drawLine(50, y1, right, y1);
                g.setColor(Color.red);
                g.drawString(this.comp.makeTimeString("", set31 - 0.75), 15, y1 + 5);
            }
        }
        g.setColor(Color.gray);
        y1 = 370 - (int)Math.round(F * this.yFaktor * 1.5);
        if (y1 > 120 && y1 < 620) {
            g.drawLine(50, y1, right, y1);
            g.setColor(Color.red);
            g.drawString(this.comp.makeTimeString("", set31 + 0.5), 15, y1 + 5);
        }
        if (this.MONAT == 2 || this.MONAT == 8) {
            for (int i = 0; i <= 6; ++i) {
                g.setColor(Color.gray);
                y1 = 370 - (int)Math.round(F * this.yFaktor * (2.0 + i * 0.5));
                if (y1 > 120 && y1 < 620) {
                    g.drawLine(50, y1, right, y1);
                    g.setColor(Color.red);
                    g.drawString(this.comp.makeTimeString("", set31 + 1.0 + i * 0.5), 15, y1 + 5);
                }
            }
            for (int j = 0; j <= 6; ++j) {
                g.setColor(Color.gray);
                y1 = 370 + (int)Math.round(F * this.yFaktor * (2.0 + j * 0.5));
                if (y1 > 120 && y1 < 620) {
                    g.drawLine(50, y1, right, y1);
                    g.setColor(Color.blue);
                    g.drawString(this.comp.makeTimeString("", rise31 - 1.0 - j * 0.5), 15, y1 + 5);
                }
            }
        }
        if (this.daysAbove == 0 && this.daysBelow == 0) {
            for (int i = 1; i <= N; ++i) {
                final int x3 = 50 + (i - 1) * 9;
                x2 = 50 + i * 9;
                g.setColor(Color.blue);
                y1 = 370 + (int)Math.round(F * this.yFaktor * (rise31 + 1.0 - this.riseArray[i]));
                int y2 = 370 + (int)Math.round(F * this.yFaktor * (rise31 + 1.0 - this.riseArray[i + 1]));
                if (y2 < 620 && y1 < 620 && y2 > 120 && y1 > 120 && Math.abs(this.isVisible[i]) < 1.0) {
                    g.drawLine(x3, y1, x2, y2);
                }
                g.setColor(Color.red);
                y1 = 370 - (int)Math.round(F * this.yFaktor * (this.setArray[i] + 1.0 - set31));
                y2 = 370 - (int)Math.round(F * this.yFaktor * (this.setArray[i + 1] + 1.0 - set31));
                if (y2 < 620 && y1 < 620 && y2 > 120 && y1 > 120 && Math.abs(this.isVisible[i]) < 1.0) {
                    g.drawLine(x3, y1, x2, y2);
                }
            }
        }
        int y2 = 0;
        int ir = 0;
        int is = 0;
        int id = 0;
        double jd1;
        if (this.MONAT == 10) {
            ir = 0;
            is = 0;
            id = 0;
            jd1 = this.comp.JD(9, 11, this.year + 1900, 12.0);
            for (int k = 2; k <= 81; ++k) {
                if (this.riseArray[k] > this.riseArray[k - 1] && this.riseArray[k] > this.riseArray[k + 1]) {
                    ir = k;
                }
                if (this.setArray[k] < this.setArray[k - 1] && this.setArray[k] < this.setArray[k + 1]) {
                    is = k;
                }
                if (this.setArray[k] - this.riseArray[k] < this.setArray[k - 1] - this.riseArray[k - 1] && this.setArray[k] - this.riseArray[k] < this.setArray[k + 1] - this.riseArray[k + 1]) {
                    id = k;
                }
            }
            if (this.daysAbove == 0 && this.daysBelow == 0) {
                g.setColor(Color.red);
                if (is > 0) {
                    g.drawString("Earliest Set  " + this.monthArray[this.comp.caldat(2, jd1 + is) - 1] + " " + this.comp.caldat(1, jd1 + is) + "  " + this.comp.hms(this.setArray[is]) + "  (" + this.comp.hms(this.setArray[is] - this.riseArray[is]) + " h)", 300, 108);
                }
                g.setColor(Color.blue);
                if (ir > 0) {
                    g.drawString("Latest Rise  " + this.monthArray[this.comp.caldat(2, jd1 + ir) - 1] + " " + this.comp.caldat(1, jd1 + ir) + "  " + this.comp.hms(this.riseArray[ir]) + "  (" + this.comp.hms(this.setArray[ir] - this.riseArray[ir]) + " h)", 50, 108);
                }
                if (id != 0) {
                    g.setColor(Color.magenta);
                    g.drawString("Shortest Day  " + this.monthArray[this.comp.caldat(2, jd1 + id) - 1] + " " + this.comp.caldat(1, jd1 + id) + "  " + this.comp.hms(this.setArray[id] - this.riseArray[id]) + "  (" + this.comp.hms(this.setArray[id] - this.riseArray[id]) + " h)", 550, 108);
                }
                else {
                    g.setColor(Color.red);
                    g.drawString(this.aboveStr, 50, 108);
                }
            }
            else {
                g.setColor(Color.red);
                g.drawString(this.aboveStr, 50, 108);
            }
        }
        else {
            ir = 0;
            is = 0;
            id = 0;
            jd1 = this.comp.JD(9, 5, this.year + 1900, 12.0);
            for (int k = 2; k <= 81; ++k) {
                if (this.riseArray[k] < this.riseArray[k - 1] && this.riseArray[k] < this.riseArray[k + 1]) {
                    ir = k;
                }
                if (this.setArray[k] > this.setArray[k - 1] && this.setArray[k] > this.setArray[k + 1]) {
                    is = k;
                }
                if (this.setArray[k] - this.riseArray[k] > this.setArray[k - 1] - this.riseArray[k - 1] && this.setArray[k] - this.riseArray[k] > this.setArray[k + 1] - this.riseArray[k + 1]) {
                    id = k;
                }
            }
            if (this.daysAbove == 0 && this.daysBelow == 0) {
                if (ir > 0) {
                    g.setColor(Color.blue);
                    g.drawString("Earliest Rise  " + this.monthArray[this.comp.caldat(2, jd1 + ir) - 1] + " " + this.comp.caldat(1, jd1 + ir) + "  " + this.comp.hms(this.riseArray[ir]) + "  (" + this.comp.hms(this.setArray[ir] - this.riseArray[ir]) + " h)", 50, 108);
                }
                if (is > 0) {
                    g.setColor(Color.red);
                    g.drawString("Latest Set  " + this.monthArray[this.comp.caldat(2, jd1 + is) - 1] + " " + this.comp.caldat(1, jd1 + is) + "  " + this.comp.hms(this.setArray[is]) + "  (" + this.comp.hms(this.setArray[is] - this.riseArray[is]) + " h)", 300, 108);
                }
                if (id != 0) {
                    g.setColor(Color.magenta);
                    g.drawString("Longest Day  " + this.monthArray[this.comp.caldat(2, jd1 + id) - 1] + " " + this.comp.caldat(1, jd1 + id) + "  " + this.comp.hms(this.setArray[id] - this.riseArray[id]) + "  (" + this.comp.hms(this.setArray[id] - this.riseArray[id]) + " h)", 550, 108);
                }
            }
            else {
                g.setColor(Color.red);
                g.drawString(this.aboveStr, 50, 108);
            }
        }
        if (this.MONAT == 10) {
            System.out.println("Rise earlier than latest: " + this.comp.hms(this.riseArray[ir] - currentRise));
            System.out.println("Set later than earliest: " + this.comp.hms(currentSet - this.setArray[is]));
        }
        if (this.MONAT == 4) {
            System.out.println("Rise later than earliest: " + this.comp.hms(currentRise - this.riseArray[ir]));
            System.out.println("Set earlier than latest: " + this.comp.hms(this.setArray[is] - currentSet));
        }
        g.setColor(Color.blue);
        int x3 = 50 + (ir - 1) * 9;
        y1 = 370 + (int)Math.round(F * this.yFaktor * (rise31 + 1.0 - this.riseArray[ir]));
        if (y1 < 620 && y1 > 120 && Math.abs(this.isVisible[ir]) < 1.0) {
            g.drawOval(x3 - 3, y1 - 3, 6, 6);
        }
        g.setColor(Color.red);
        x3 = 50 + (is - 1) * 9;
        y1 = 370 - (int)Math.round(F * this.yFaktor * (this.setArray[is] + 1.0 - set31));
        if (y1 < 620 && y1 > 120 && Math.abs(this.isVisible[is]) < 1.0) {
            g.drawOval(x3 - 3, y1 - 3, 6, 6);
        }
        g.setColor(Color.magenta);
        x3 = 50 + (id - 1) * 9;
        y1 = 370 - (int)Math.round(F * this.yFaktor * (this.setArray[id] + 1.0 - set31));
        y2 = 370 + (int)Math.round(F * this.yFaktor * (rise31 + 1.0 - this.riseArray[id]));
        if (y2 < 620 && y1 < 620 && y2 > 120 && y1 > 120) {
            g.drawOval(x3 - 3, y1 - 3, 6, 6);
            g.drawOval(x3 - 3, y2 - 3, 6, 6);
            g.drawLine(x3, y1, x3, y2);
        }
        g.setColor(Color.green);
        if (this.MONAT == 10 && this.jd < this.comp.JD(14, 12, this.year + 1900, 12.0)) {
            jd1 = this.comp.JD(9, 11, this.year + 1900 - 1, 12.0);
        }
        if (this.MONAT == 2) {
            jd1 = this.comp.JD(1, 2, this.year + 1900, 12.0) - 1.0;
        }
        if (this.MONAT == 8) {
            jd1 = this.comp.JD(1, 8, this.year + 1900, 12.0) - 1.0;
        }
        id = (int)this.jd - (int)jd1;
        x3 = 50 + (id - 1) * 9;
        if (id * 9 <= 747 && this.MONAT == 10) {
            g.drawLine(x3, 120, x3, 620);
        }
        if (id * 9 <= 801 && this.MONAT == 2) {
            g.drawLine(x3, 120, x3, 620);
        }
        if (id * 9 <= 738 && this.MONAT == 8) {
            g.drawLine(x3, 120, x3, 620);
        }
        g.setFont(f);
        g.clearRect(1, 1, this.size().width, this.size().height);
    }
}

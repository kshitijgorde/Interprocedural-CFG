import java.awt.image.ImageObserver;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

class Santeria extends Canvas
{
    Image offscreen;
    int showwhat;
    Graphics offgraphics;
    Dimension offscreensize;
    Dimension d;
    String cbendstring;
    String sbendstring;
    String forkstring;
    double factor;
    double factorx;
    double factory;
    int k;
    double i;
    double maxheight;
    double jonestab;
    double relfrontproj;
    double chainx;
    double chainy;
    double chainz;
    double seatx;
    double seaty;
    double seatz;
    double sdropoutd;
    double sdropouta;
    double cdropoutd;
    double cdropouta;
    double axlauxss;
    double axlauxcs;
    double sebackdia;
    double chbackdia;
    double chbackpointX;
    double sebackpointX;
    double chbackpointY;
    double sebackpointY;
    double sebackpointZ;
    double chbackpointZ;
    double chfrontpointZ;
    double sefrontpointX;
    double sefrontpointY;
    double sefrontpointZ;
    double chfrontpointX;
    double chfrontpointY;
    double dropoutspace;
    double BBlen;
    double stayang;
    double staypos;
    double frontwd;
    double rearwd;
    double rearww;
    double frontww;
    double headangle;
    double seatangle;
    double BBd;
    double BBh;
    double stlength;
    double stlength2;
    double dtlength;
    double ttlength;
    double ttlength2;
    double htlength;
    double htlowext;
    double htupext;
    double CSlen;
    double CSlen2;
    double CSlen3;
    double SSlen2;
    double FCdist;
    double rakelen;
    double forklen;
    double ftravel;
    double sag;
    double lowerstack;
    double stdiameter;
    double ttdiameter;
    double dtdiameter;
    double htdiameter;
    double chhdia;
    double chvdia;
    double cradius;
    double chtaper;
    double chpos;
    double tttcut;
    double ttbcut;
    double stcut;
    double dttcut;
    double dtbcut;
    double dttcuttemp;
    double dtbcuttemp;
    double stttangle2;
    double ttangle2;
    double tthtangle2;
    double htdtangle2;
    double dtangle2;
    double ssstangle;
    double ssstangle2;
    double wheelbase;
    double bbcentrX;
    double stmidtopX;
    double stmidtopY;
    double frontaxlX;
    double frontaxlY;
    double rearaxlX;
    double rearaxlY;
    double forkbottomX;
    double forkbottomY;
    double hbfX;
    double hbfY;
    double hbrX;
    double hbrY;
    double htfX;
    double htfY;
    double htrX;
    double htrY;
    double dbfX;
    double dbfY;
    double dtfX;
    double dtfY;
    double dbrX;
    double dbrY;
    double dtrX;
    double dtrY;
    double sbfX;
    double sbfY;
    double sbrX;
    double sbrY;
    double stfX;
    double stfY;
    double strX;
    double strY;
    double ttrX;
    double ttrY;
    double ttfX;
    double ttfY;
    double tbrX;
    double tbrY;
    double tbfX;
    double tbfY;
    double downtrueangle;
    double toptrueangle;
    double dthtangle;
    double tthtangle;
    double ttstangle;
    double stextension;
    double dtfpX;
    double dtfpY;
    double ttfpX;
    double ttfpY;
    double ttrpX;
    double ttrpY;
    double chtaperang;
    double tapercomp;
    double BBext;
    double ftrav;
    double ttdtgap;
    double csgap;
    double sslowlen;
    double cslowlen;
    double trailmm;
    double sscsangle2;
    double csangle;
    double csangle2;
    double ssangle;
    double ssangle2;
    double wgap;
    double stcsangvirtual;
    double sstopshow;
    double cstopshow;
    double spointZ;
    double sstoplenj;
    double cpointZ;
    double cpointj;
    double sbendang;
    double cbendang;
    double sstoplen;
    double csfrontlen;
    double SSlen3;
    double sslowang;
    double ssupang;
    double ssupangcom;
    double sbendext;
    double sradius;
    double sradcenZ;
    double sradcenj;
    double stayoffset;
    double csupang;
    double csupangcom;
    double cslowang;
    double cradcenj;
    double cradcenZ;
    double csfrontlenj;
    double cbendext;
    boolean sbendy;
    boolean cbendy;
    boolean fanimy;
    int baseline;
    Color mygray;
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void paint(final Graphics graphics) {
        this.d = this.size();
        this.baseline = this.size().height;
        if (this.d.width <= 0 || this.d.height <= 0) {
            return;
        }
        if (this.offscreen == null || this.d.width != this.offscreensize.width || this.d.height != this.offscreensize.height) {
            this.offscreen = this.createImage(this.d.width, this.d.height);
            this.offscreensize = this.d;
            this.offgraphics = this.offscreen.getGraphics();
        }
        if (this.fanimy) {
            this.ftrav = Math.abs(this.k / 5.0 * (this.ftravel - this.sag));
        }
        else {
            this.ftrav = 0.0;
        }
        this.rearaxlX = this.rearwd / 2.0;
        this.rearaxlY = this.rearwd / 2.0;
        this.chbackpointX = this.rearaxlX + this.chainx;
        this.chbackpointY = this.rearaxlY + this.chainy;
        this.chbackpointZ = this.dropoutspace / 2.0 + this.chainz;
        this.frontaxlY = this.frontwd / 2.0;
        this.wheelbase = Math.sqrt(Math.pow(this.CSlen, 2.0) - Math.pow(this.rearaxlY - this.BBh, 2.0)) + Math.sqrt(Math.pow(this.FCdist, 2.0) - Math.pow(this.frontaxlY - this.BBh, 2.0));
        this.frontaxlX = this.rearaxlX + this.wheelbase;
        this.trailmm = (this.frontwd / 2.0 * Math.cos(this.headangle / 180.0 * 3.141592653589793) - this.rakelen) / Math.sin(this.headangle / 180.0 * 3.141592653589793);
        this.bbcentrX = this.rearaxlX + Math.sqrt(Math.pow(this.CSlen, 2.0) - Math.pow(this.rearaxlY - this.BBh, 2.0));
        this.stmidtopX = this.bbcentrX - this.stlength * Math.cos(this.seatangle / 180.0 * 3.141592653589793);
        this.stmidtopY = this.BBh + this.stlength * Math.sin(this.seatangle / 180.0 * 3.141592653589793);
        this.forkbottomX = this.frontaxlX + this.rakelen * Math.cos((270.0 - this.headangle) / 180.0 * 3.141592653589793);
        this.forkbottomY = this.frontaxlY + this.rakelen * Math.sin((270.0 - this.headangle) / 180.0 * 3.141592653589793);
        this.hbfX = this.forkbottomX - Math.sqrt(Math.pow(this.forklen - this.sag + this.lowerstack, 2.0) + Math.pow(this.htdiameter / 2.0, 2.0)) * Math.cos(this.headangle / 180.0 * 3.141592653589793 + Math.atan(this.htdiameter / 2.0 / (this.forklen - this.sag + this.lowerstack)));
        this.hbfY = this.forkbottomY + Math.sqrt(Math.pow(this.forklen - this.sag + this.lowerstack, 2.0) + Math.pow(this.htdiameter / 2.0, 2.0)) * Math.sin(this.headangle / 180.0 * 3.141592653589793 + Math.atan(this.htdiameter / 2.0 / (this.forklen - this.sag + this.lowerstack)));
        this.hbrX = this.hbfX + this.htdiameter * Math.cos((270.0 - this.headangle) / 180.0 * 3.141592653589793);
        this.hbrY = this.hbfY + this.htdiameter * Math.sin((270.0 - this.headangle) / 180.0 * 3.141592653589793);
        this.htfX = this.hbfX - this.htlength * Math.cos(this.headangle / 180.0 * 3.141592653589793);
        this.htfY = this.hbfY + this.htlength * Math.sin(this.headangle / 180.0 * 3.141592653589793);
        this.htrX = this.hbrX - this.htlength * Math.cos(this.headangle / 180.0 * 3.141592653589793);
        this.htrY = this.hbrY + this.htlength * Math.sin(this.headangle / 180.0 * 3.141592653589793);
        this.dbfX = this.hbrX - this.htlowext * Math.cos(this.headangle / 180.0 * 3.141592653589793);
        this.dbfY = this.hbrY + this.htlowext * Math.sin(this.headangle / 180.0 * 3.141592653589793);
        this.downtrueangle = Math.atan((this.dbfY - this.BBh) / (this.dbfX - this.bbcentrX)) + Math.asin(this.dtdiameter / 2.0 / Math.sqrt(Math.pow(this.dbfY - this.BBh, 2.0) + Math.pow(this.dbfX - this.bbcentrX, 2.0)));
        this.dtangle2 = this.downtrueangle / 3.141592653589793 * 180.0;
        if (this.dtdiameter > this.BBd) {
            this.dbrX = this.bbcentrX + this.dtdiameter / 2.0 * Math.cos(this.downtrueangle - 1.5707963267948966);
            this.dbrY = this.BBh + this.dtdiameter / 2.0 * Math.sin(this.downtrueangle - 1.5707963267948966);
            this.dtrX = this.bbcentrX + this.dtdiameter / 2.0 * Math.cos(this.downtrueangle + 1.5707963267948966);
            this.dtrY = this.BBh + this.dtdiameter / 2.0 * Math.sin(this.downtrueangle + 1.5707963267948966);
        }
        else {
            this.dbrX = this.bbcentrX + this.BBd / 2.0 * Math.cos(this.downtrueangle - Math.asin(this.dtdiameter / this.BBd));
            this.dbrY = this.BBh + this.BBd / 2.0 * Math.sin(this.downtrueangle - Math.asin(this.dtdiameter / this.BBd));
            this.dtrX = this.bbcentrX + this.BBd / 2.0 * Math.cos(this.downtrueangle + Math.asin(this.dtdiameter / this.BBd));
            this.dtrY = this.BBh + this.BBd / 2.0 * Math.sin(this.downtrueangle + Math.asin(this.dtdiameter / this.BBd));
        }
        this.dthtangle = this.downtrueangle + this.headangle / 180.0 * 3.141592653589793;
        this.htdtangle2 = this.dthtangle / 3.141592653589793 * 180.0;
        this.dtfX = this.hbrX - (this.htlowext + this.dtdiameter / Math.cos(this.dthtangle - 1.5707963267948966)) * Math.cos(this.headangle / 180.0 * 3.141592653589793);
        this.dtfY = this.hbrY + (this.htlowext + this.dtdiameter / Math.cos(this.dthtangle - 1.5707963267948966)) * Math.sin(this.headangle / 180.0 * 3.141592653589793);
        this.dttcuttemp = Math.sqrt(Math.pow(this.dtfX - this.bbcentrX, 2.0) + Math.pow(this.dtfY - this.BBh, 2.0));
        this.dttcut = Math.sqrt(Math.pow(this.dttcuttemp, 2.0) - Math.pow(this.dtdiameter, 2.0));
        this.dtbcuttemp = Math.sqrt(Math.pow(this.dbfX - this.bbcentrX, 2.0) + Math.pow(this.dbfY - this.BBh, 2.0));
        this.dtbcut = Math.sqrt(Math.pow(this.dtbcuttemp, 2.0) - Math.pow(this.dtdiameter, 2.0));
        if (this.stdiameter > this.BBd) {
            this.sbfX = this.bbcentrX - this.stdiameter / 2.0 * Math.cos(this.seatangle / 180.0 * 3.141592653589793 + 1.5707963267948966);
            this.sbfY = this.BBh + this.stdiameter / 2.0 * Math.sin(this.seatangle / 180.0 * 3.141592653589793 + 1.5707963267948966);
            this.sbrX = this.bbcentrX - this.stdiameter / 2.0 * Math.cos(this.seatangle / 180.0 * 3.141592653589793 - 1.5707963267948966);
            this.sbrY = this.BBh + this.stdiameter / 2.0 * Math.sin(this.seatangle / 180.0 * 3.141592653589793 - 1.5707963267948966);
        }
        else {
            this.sbfX = this.bbcentrX - this.BBd / 2.0 * Math.cos(this.seatangle / 180.0 * 3.141592653589793 + Math.asin(this.stdiameter / this.BBd));
            this.sbfY = this.BBh + this.BBd / 2.0 * Math.sin(this.seatangle / 180.0 * 3.141592653589793 + Math.asin(this.stdiameter / this.BBd));
            this.sbrX = this.bbcentrX - this.BBd / 2.0 * Math.cos(this.seatangle / 180.0 * 3.141592653589793 - Math.asin(this.stdiameter / this.BBd));
            this.sbrY = this.BBh + this.BBd / 2.0 * Math.sin(this.seatangle / 180.0 * 3.141592653589793 - Math.asin(this.stdiameter / this.BBd));
        }
        this.stfX = this.bbcentrX - Math.sqrt(Math.pow(this.stdiameter / 2.0, 2.0) + Math.pow(this.stlength, 2.0)) * Math.cos(this.seatangle / 180.0 * 3.141592653589793 + Math.atan(this.stdiameter / 2.0 / this.stlength));
        this.stfY = this.BBh + Math.sqrt(Math.pow(this.stdiameter / 2.0, 2.0) + Math.pow(this.stlength, 2.0)) * Math.sin(this.seatangle / 180.0 * 3.141592653589793 + Math.atan(this.stdiameter / 2.0 / this.stlength));
        this.strX = this.bbcentrX - Math.sqrt(Math.pow(this.stdiameter / 2.0, 2.0) + Math.pow(this.stlength, 2.0)) * Math.cos(this.seatangle / 180.0 * 3.141592653589793 - Math.atan(this.stdiameter / 2.0 / this.stlength));
        this.strY = this.BBh + Math.sqrt(Math.pow(this.stdiameter / 2.0, 2.0) + Math.pow(this.stlength, 2.0)) * Math.sin(this.seatangle / 180.0 * 3.141592653589793 - Math.atan(this.stdiameter / 2.0 / this.stlength));
        this.stcut = this.stlength - this.BBd / 2.0;
        this.ttrX = this.stfX + this.stextension * Math.cos(this.seatangle / 180.0 * 3.141592653589793);
        this.ttrY = this.stfY - this.stextension * Math.sin(this.seatangle / 180.0 * 3.141592653589793);
        this.ttfX = this.htrX + this.htupext * Math.cos(this.headangle / 180.0 * 3.141592653589793);
        this.ttfY = this.htrY - this.htupext * Math.sin(this.headangle / 180.0 * 3.141592653589793);
        this.toptrueangle = Math.atan((this.ttfY - this.ttrY) / (this.ttfX - this.ttrX));
        this.tttcut = Math.sqrt(Math.pow(this.ttfX - this.ttrX, 2.0) + Math.pow(this.ttfY - this.ttrY, 2.0));
        this.ttangle2 = this.toptrueangle / 3.141592653589793 * 180.0;
        this.tthtangle = 3.141592653589793 - this.headangle / 180.0 * 3.141592653589793 - this.toptrueangle;
        this.tthtangle2 = this.tthtangle / 3.141592653589793 * 180.0;
        this.ttstangle = this.seatangle / 180.0 * 3.141592653589793 + this.toptrueangle;
        this.stttangle2 = this.ttstangle / 3.141592653589793 * 180.0;
        this.tbfX = this.ttfX + this.ttdiameter / Math.cos(this.tthtangle - 1.5707963267948966) * Math.cos(this.headangle / 180.0 * 3.141592653589793);
        this.tbfY = this.ttfY - this.ttdiameter / Math.cos(this.tthtangle - 1.5707963267948966) * Math.sin(this.headangle / 180.0 * 3.141592653589793);
        this.tbrX = this.ttrX + this.ttdiameter / Math.cos(this.ttstangle - 1.5707963267948966) * Math.cos(this.seatangle / 180.0 * 3.141592653589793);
        this.tbrY = this.ttrY - this.ttdiameter / Math.cos(this.ttstangle - 1.5707963267948966) * Math.sin(this.seatangle / 180.0 * 3.141592653589793);
        this.ttbcut = Math.sqrt(Math.pow(this.tbfX - this.tbrX, 2.0) + Math.pow(this.tbfY - this.tbrY, 2.0));
        this.dtfpX = this.dbfX - this.dtdiameter / 2.0 / Math.cos(this.dthtangle - 1.5707963267948966) * Math.cos(this.headangle / 180.0 * 3.141592653589793) + this.htdiameter / 2.0 / Math.cos(this.dthtangle - 1.5707963267948966) * Math.cos(this.downtrueangle);
        this.dtfpY = this.dbfY + this.dtdiameter / 2.0 / Math.cos(this.dthtangle - 1.5707963267948966) * Math.sin(this.headangle / 180.0 * 3.141592653589793) + this.htdiameter / 2.0 / Math.cos(this.dthtangle - 1.5707963267948966) * Math.sin(this.downtrueangle);
        this.ttfpX = this.ttfX + this.ttdiameter / 2.0 / Math.cos(this.tthtangle - 1.5707963267948966) * Math.cos(this.headangle / 180.0 * 3.141592653589793) + this.htdiameter / 2.0 / Math.cos(this.tthtangle - 1.5707963267948966) * Math.cos(this.toptrueangle);
        this.ttfpY = this.ttfY - this.ttdiameter / 2.0 / Math.cos(this.tthtangle - 1.5707963267948966) * Math.sin(this.headangle / 180.0 * 3.141592653589793) + this.htdiameter / 2.0 / Math.cos(this.tthtangle - 1.5707963267948966) * Math.sin(this.toptrueangle);
        this.ttrpX = this.ttrX + this.ttdiameter / 2.0 / Math.cos(this.ttstangle - 1.5707963267948966) * Math.cos(this.seatangle / 180.0 * 3.141592653589793) - this.stdiameter / 2.0 / Math.cos(this.ttstangle - 1.5707963267948966) * Math.cos(this.toptrueangle);
        this.ttrpY = this.ttrY - this.ttdiameter / 2.0 / Math.cos(this.ttstangle - 1.5707963267948966) * Math.sin(this.seatangle / 180.0 * 3.141592653589793) - this.stdiameter / 2.0 / Math.cos(this.ttstangle - 1.5707963267948966) * Math.sin(this.toptrueangle);
        this.ttdtgap = Math.sqrt(Math.pow(this.tbfX - this.dtfX, 2.0) + Math.pow(this.tbfY - this.dtfY, 2.0));
        this.stlength2 = Math.sqrt(Math.pow(this.ttrpX - this.bbcentrX, 2.0) + Math.pow(this.ttrpY - this.BBh, 2.0));
        this.dtlength = Math.sqrt(Math.pow(this.dtfpX - this.bbcentrX, 2.0) + Math.pow(this.dtfpY - this.BBh, 2.0));
        this.ttlength2 = Math.sqrt(Math.pow(this.ttfpX - this.ttrpX, 2.0) + Math.pow(this.ttfpY - this.ttrpY, 2.0));
        this.ttlength = this.ttfpX - this.bbcentrX + (this.ttfpY - this.BBh) / Math.tan(this.seatangle / 180.0 * 3.141592653589793);
        this.jonestab = this.rakelen / this.frontwd / 4.0 / 0.00917 - (90.0 - this.headangle) * Math.sin(this.headangle / 180.0 * 3.141592653589793) / 4.0;
        this.relfrontproj = this.rakelen / Math.sin(this.headangle / 180.0 * 3.141592653589793) / this.frontwd;
        this.sefrontpointZ = this.stdiameter / 2.0 * Math.sin(this.stayang / 180.0 * 3.141592653589793);
        this.sefrontpointX = this.bbcentrX - Math.sqrt(Math.pow(this.stdiameter / 2.0 * Math.cos(this.stayang / 180.0 * 3.141592653589793), 2.0) + Math.pow(this.stlength - this.staypos, 2.0)) * Math.cos(this.seatangle / 180.0 * 3.141592653589793 - Math.atan(this.stdiameter / 2.0 * Math.cos(this.stayang / 180.0 * 3.141592653589793) / (this.stlength - this.staypos)));
        this.sefrontpointY = this.BBh + Math.sqrt(Math.pow(this.stdiameter / 2.0 * Math.cos(this.stayang / 180.0 * 3.141592653589793), 2.0) + Math.pow(this.stlength - this.staypos, 2.0)) * Math.sin(this.seatangle / 180.0 * 3.141592653589793 - Math.atan(this.stdiameter / 2.0 * Math.cos(this.stayang / 180.0 * 3.141592653589793) / (this.stlength - this.staypos)));
        this.sebackpointX = this.rearaxlX + this.seatx;
        this.sebackpointY = this.rearaxlY + this.seaty;
        this.sebackpointZ = this.dropoutspace / 2.0 + this.seatz;
        this.sdropoutd = Math.sqrt(Math.pow(this.seatx, 2.0) + Math.pow(this.seaty, 2.0));
        this.cdropoutd = Math.sqrt(Math.pow(this.chainx, 2.0) + Math.pow(this.chainy, 2.0));
        if (this.sdropoutd == 0.0) {
            this.sdropouta = 1.5707963267948966;
        }
        else if (this.seaty < 0.0) {
            this.sdropouta = -Math.acos(this.seatx / this.sdropoutd);
        }
        else {
            this.sdropouta = Math.atan(this.seatx / this.sdropoutd);
        }
        if (this.cdropoutd == 0.0) {
            this.cdropouta = 1.5707963267948966;
        }
        else if (this.chainy < 0.0) {
            this.cdropouta = -Math.acos(this.chainx / this.cdropoutd);
        }
        else {
            this.cdropouta = Math.acos(this.chainx / this.cdropoutd);
        }
        this.ssangle = Math.atan((this.sefrontpointY - this.sebackpointY) / (this.sefrontpointX - this.sebackpointX));
        this.ssangle2 = this.ssangle / 3.141592653589793 * 180.0;
        this.csangle = Math.atan((this.chbackpointY - this.BBh) / (this.bbcentrX - this.chbackpointX));
        this.csangle2 = this.csangle / 3.141592653589793 * 180.0;
        this.chfrontpointZ = this.BBlen / 2.0 - this.chpos;
        this.chfrontpointX = this.bbcentrX - this.BBd * Math.cos(this.csangle);
        this.chfrontpointY = this.BBh + this.BBd * Math.sin(this.csangle);
        this.sscsangle2 = this.csangle2 + this.ssangle2;
        this.SSlen2 = Math.sqrt(Math.pow(this.sefrontpointX - this.sebackpointX, 2.0) + Math.pow(this.sefrontpointY - this.sebackpointY, 2.0));
        this.SSlen3 = Math.sqrt(Math.pow(this.SSlen2, 2.0) + Math.pow(this.sebackpointZ - this.sefrontpointZ, 2.0));
        this.CSlen2 = Math.sqrt(Math.pow(this.bbcentrX - this.chbackpointX, 2.0) + Math.pow(this.BBh - this.chbackpointY, 2.0)) - this.BBd / 2.0;
        this.CSlen3 = Math.sqrt(Math.pow(this.CSlen2, 2.0) + Math.pow(this.chbackpointZ - this.chfrontpointZ, 2.0));
        this.ssupangcom = Math.atan((this.sebackpointZ - this.sefrontpointZ) / this.SSlen2);
        this.csupangcom = Math.atan((this.chbackpointZ - this.chfrontpointZ) / this.CSlen2);
        this.stcsangvirtual = this.seatangle / 180.0 * 3.141592653589793 - Math.atan((this.rearaxlY - this.BBh) / (this.bbcentrX - this.rearaxlX));
        this.wgap = this.CSlen * Math.sin(this.stcsangvirtual) - (this.rearwd + this.stdiameter) / 2.0;
        this.axlauxss = this.sdropoutd * Math.cos(this.sdropouta - this.ssangle);
        this.axlauxcs = this.cdropoutd * Math.cos(this.cdropouta + this.csangle);
        if (this.sbendy) {
            this.sslowang = Math.asin(this.sstoplen * Math.sin((180.0 - this.sbendang) / 180.0 * 3.141592653589793) / this.SSlen3);
            this.ssupang = 3.141592653589793 - this.sslowang - (180.0 - this.sbendang) / 180.0 * 3.141592653589793;
            this.sslowlen = this.sstoplen / Math.sin(this.sslowang) * Math.sin(this.ssupang);
            this.spointZ = this.sefrontpointZ + this.sstoplen * Math.sin(this.ssupang + this.ssupangcom);
            this.sstoplenj = this.sstoplen * Math.cos(this.ssupang + this.ssupangcom);
            this.sbendext = this.sradius / Math.tan((180.0 - this.sbendang) / 180.0 * 3.141592653589793 / 2.0);
            this.sradcenZ = this.sefrontpointZ + Math.sqrt(Math.pow(this.sstoplen - this.sbendext, 2.0) + Math.pow(this.sradius, 2.0)) * Math.sin(this.ssupangcom + this.ssupang - Math.atan(this.sradius / (this.sstoplen - this.sbendext)));
            this.sradcenj = Math.sqrt(Math.pow(this.sstoplen - this.sbendext, 2.0) + Math.pow(this.sradius, 2.0)) * Math.cos(this.ssupangcom + this.ssupang - Math.atan(this.sradius / (this.sstoplen - this.sbendext)));
            this.stayoffset = this.stdiameter / 2.0 * Math.cos(1.5707963267948966 - this.stayang / 180.0 * 3.141592653589793 + this.ssupang + this.ssupangcom);
            this.sbendstring = "*Seatstay bend angle\t = " + String.valueOf(this.sbendang) + "°\n\t*Seatstay bend radius\t = " + String.valueOf(this.sradius) + " mm\n\t*Seatstay length above bend\t = " + String.valueOf(this.sstoplen) + " mm\n\tSeatstay length below bend\t = " + String.valueOf((float)this.sslowlen) + " mm\n\tSideways angle of top section of seatstay\t= " + String.valueOf((float)((this.ssupang + this.ssupangcom) / 3.141592653589793 * 180.0)) + "°\n\tSideways angle of bottom section of seatstay\t= " + String.valueOf((float)((this.ssupangcom - this.sslowang) / 3.141592653589793 * 180.0)) + "°";
        }
        else {
            this.stayoffset = this.stdiameter / 2.0 * Math.cos(1.5707963267948966 - this.stayang / 180.0 * 3.141592653589793 + this.ssupangcom);
            this.sbendstring = "Sideways angle of seatstay\t= " + String.valueOf((float)(this.ssupangcom / 3.141592653589793 * 180.0)) + "°\n\tSeatstays are straight";
        }
        if (this.cbendy) {
            this.cslowang = Math.asin(this.csfrontlen * Math.sin((180.0 - this.cbendang) / 180.0 * 3.141592653589793) / this.CSlen3);
            this.csupang = 3.141592653589793 - this.cslowang - (180.0 - this.cbendang) / 180.0 * 3.141592653589793;
            this.cslowlen = this.csfrontlen / Math.sin(this.cslowang) * Math.sin(this.csupang);
            this.cpointZ = this.chfrontpointZ + this.csfrontlen * Math.sin(this.csupang + this.csupangcom);
            this.csfrontlenj = this.csfrontlen * Math.cos(this.csupang + this.csupangcom);
            this.cbendext = this.cradius / Math.tan((180.0 - this.cbendang) / 180.0 * 3.141592653589793 / 2.0);
            this.cradcenZ = this.chfrontpointZ + Math.sqrt(Math.pow(this.csfrontlen - this.cbendext, 2.0) + Math.pow(this.cradius, 2.0)) * Math.sin(this.csupangcom + this.csupang - Math.atan(this.cradius / (this.csfrontlen - this.cbendext)));
            this.cradcenj = Math.sqrt(Math.pow(this.csfrontlen - this.cbendext, 2.0) + Math.pow(this.cradius, 2.0)) * Math.cos(this.csupangcom + this.csupang - Math.atan(this.cradius / (this.csfrontlen - this.cbendext)));
            this.tapercomp = this.chtaper * Math.cos(this.csupangcom - this.cslowang);
            this.BBext = this.chpos - this.chhdia / 2.0 / Math.sin(1.5707963267948966 - this.csupangcom - this.csupang);
            this.csgap = this.BBlen - this.chpos * 2.0 - this.chhdia / Math.sin(1.5707963267948966 - this.csupangcom - this.csupang);
            this.cbendstring = "*Chainstay bend angle\t = " + String.valueOf(this.cbendang) + "°\n\t*Chainstay bend radius\t = " + String.valueOf(this.cradius) + " mm\n\t*Chainstay length in front of bend\t = " + String.valueOf(this.csfrontlen) + " mm\n\tChainstay length behind bend\t = " + String.valueOf((float)this.cslowlen) + " mm\n\tSideways angle of front section of chainstay\t= " + String.valueOf((float)((this.csupang + this.csupangcom) / 3.141592653589793 * 180.0)) + "°\n\tSideways angle of rear section of chainstay\t= " + String.valueOf((float)((this.csupangcom - this.cslowang) / 3.141592653589793 * 180.0)) + "°";
        }
        else {
            this.tapercomp = this.chtaper * Math.cos(this.csupangcom);
            this.BBext = this.chpos - this.chhdia / 2.0 / Math.sin(1.5707963267948966 - this.csupangcom);
            this.csgap = this.BBlen - this.chpos * 2.0 - this.chhdia / Math.sin(1.5707963267948966 - this.csupangcom);
            this.cbendstring = "Sideways angle of chainstay\t= " + String.valueOf((float)(this.csupangcom / 3.141592653589793 * 180.0)) + "°\n\tChainstays are straight";
        }
        this.chtaperang = Math.atan((this.chhdia - this.chbackdia) / 2.0 / this.chtaper);
        this.cstopshow = this.CSlen2 + this.axlauxcs + 40.0;
        this.sstopshow = this.SSlen2 + this.axlauxss + 40.0;
        this.ssstangle = 3.141592653589793 - this.ssangle - this.seatangle / 180.0 * 3.141592653589793;
        this.ssstangle2 = this.ssstangle / 3.141592653589793 * 180.0;
        if (this.ftravel == 0.0) {
            this.forkstring = "*Fork length\t= " + String.valueOf((float)this.forklen) + " mm\n\tThis is a rigid fork";
        }
        else {
            this.forkstring = "*Fork travel\t= " + String.valueOf((float)this.ftravel) + " mm\n\t*Uncompressed fork length\t= " + String.valueOf((float)this.forklen) + " mm\n\t*Fork sag\t= " + String.valueOf((float)this.sag) + " mm\n\tEffective fork length (uncompressed-sag)\t= " + String.valueOf((float)(this.forklen - this.sag)) + " mm";
        }
        this.offgraphics.setColor(Color.white);
        this.offgraphics.fillRect(0, 0, this.size().width, this.baseline);
        if (this.showwhat == 1) {
            this.maxheight = Math.max(this.stfY, this.htfY);
            if (this.maxheight / this.size().height > (this.wheelbase + this.rearaxlX + this.frontaxlY) / this.size().width) {
                this.factor = this.size().height / this.maxheight;
            }
            else {
                this.factor = this.size().width / (this.wheelbase + this.rearaxlX + this.frontaxlY);
            }
            this.offgraphics.setColor(this.mygray);
            this.offgraphics.drawLine((int)(this.bbcentrX * this.factor), (int)(this.baseline - this.BBh * this.factor), (int)(this.stmidtopX * this.factor), (int)(this.baseline - this.stmidtopY * this.factor));
            this.offgraphics.drawLine((int)((this.frontaxlX - this.ftrav * Math.cos(this.headangle / 180.0 * 3.141592653589793)) * this.factor), (int)(this.baseline - (this.frontaxlY + this.ftrav * Math.sin(this.headangle / 180.0 * 3.141592653589793)) * this.factor), (int)((this.frontaxlX - (this.ftrav + this.forklen / 2.0) * Math.cos(this.headangle / 180.0 * 3.141592653589793)) * this.factor), (int)(this.baseline - (this.frontaxlY + (this.ftrav + this.forklen / 2.0) * Math.sin(this.headangle / 180.0 * 3.141592653589793)) * this.factor));
            this.offgraphics.drawLine((int)((this.forkbottomX - this.ftrav * Math.cos(this.headangle / 180.0 * 3.141592653589793)) * this.factor), (int)(this.baseline - (this.forkbottomY + this.ftrav * Math.sin(this.headangle / 180.0 * 3.141592653589793)) * this.factor), (int)((this.forkbottomX - (this.forklen - this.sag + this.lowerstack + this.htlength) * Math.cos(this.headangle / 180.0 * 3.141592653589793)) * this.factor), (int)(this.baseline - (this.forkbottomY + (this.forklen - this.sag + this.lowerstack + this.htlength) * Math.sin(this.headangle / 180.0 * 3.141592653589793)) * this.factor));
            this.offgraphics.drawLine((int)(this.bbcentrX * this.factor), (int)(this.baseline - this.BBh * this.factor), (int)(this.dtfpX * this.factor), (int)(this.baseline - this.dtfpY * this.factor));
            this.offgraphics.drawLine((int)(this.ttrpX * this.factor), (int)(this.baseline - this.ttrpY * this.factor), (int)(this.ttfpX * this.factor), (int)(this.baseline - this.ttfpY * this.factor));
            this.offgraphics.drawLine((int)(this.chbackpointX * this.factor), (int)(this.baseline - this.chbackpointY * this.factor), (int)(this.bbcentrX * this.factor), (int)(this.baseline - this.BBh * this.factor));
            this.offgraphics.drawLine((int)(this.sebackpointX * this.factor), (int)(this.baseline - this.sebackpointY * this.factor), (int)(this.sefrontpointX * this.factor), (int)(this.baseline - this.sefrontpointY * this.factor));
            this.offgraphics.setColor(Color.red);
            this.offgraphics.drawOval((int)((this.bbcentrX - this.BBd / 2.0) * this.factor), (int)(this.baseline - (this.BBh + this.BBd / 2.0) * this.factor), (int)(this.BBd * this.factor), (int)(this.BBd * this.factor));
            this.offgraphics.drawLine((int)(this.hbrX * this.factor), (int)(this.baseline - this.hbrY * this.factor), (int)(this.hbfX * this.factor), (int)(this.baseline - this.hbfY * this.factor));
            this.offgraphics.drawLine((int)(this.hbfX * this.factor), (int)(this.baseline - this.hbfY * this.factor), (int)(this.htfX * this.factor), (int)(this.baseline - this.htfY * this.factor));
            this.offgraphics.drawLine((int)(this.htfX * this.factor), (int)(this.baseline - this.htfY * this.factor), (int)(this.htrX * this.factor), (int)(this.baseline - this.htrY * this.factor));
            this.offgraphics.drawLine((int)(this.hbrX * this.factor), (int)(this.baseline - this.hbrY * this.factor), (int)(this.dbfX * this.factor), (int)(this.baseline - this.dbfY * this.factor));
            this.offgraphics.drawLine((int)(this.dbfX * this.factor), (int)(this.baseline - this.dbfY * this.factor), (int)(this.dbrX * this.factor), (int)(this.baseline - this.dbrY * this.factor));
            this.offgraphics.drawLine((int)(this.dtfX * this.factor), (int)(this.baseline - this.dtfY * this.factor), (int)(this.dtrX * this.factor), (int)(this.baseline - this.dtrY * this.factor));
            this.offgraphics.drawLine((int)(this.sbrX * this.factor), (int)(this.baseline - this.sbrY * this.factor), (int)(this.strX * this.factor), (int)(this.baseline - this.strY * this.factor));
            this.offgraphics.drawLine((int)(this.strX * this.factor), (int)(this.baseline - this.strY * this.factor), (int)(this.stfX * this.factor), (int)(this.baseline - this.stfY * this.factor));
            this.offgraphics.drawLine((int)(this.stfX * this.factor), (int)(this.baseline - this.stfY * this.factor), (int)(this.ttrX * this.factor), (int)(this.baseline - this.ttrY * this.factor));
            this.offgraphics.drawLine((int)(this.tbrX * this.factor), (int)(this.baseline - this.tbrY * this.factor), (int)(this.sbfX * this.factor), (int)(this.baseline - this.sbfY * this.factor));
            this.offgraphics.drawLine((int)(this.ttrX * this.factor), (int)(this.baseline - this.ttrY * this.factor), (int)(this.ttfX * this.factor), (int)(this.baseline - this.ttfY * this.factor));
            this.offgraphics.drawLine((int)(this.tbrX * this.factor), (int)(this.baseline - this.tbrY * this.factor), (int)(this.tbfX * this.factor), (int)(this.baseline - this.tbfY * this.factor));
            this.offgraphics.drawLine((int)(this.htrX * this.factor), (int)(this.baseline - this.htrY * this.factor), (int)(this.ttfX * this.factor), (int)(this.baseline - this.ttfY * this.factor));
            this.offgraphics.drawLine((int)(this.tbfX * this.factor), (int)(this.baseline - this.tbfY * this.factor), (int)(this.dtfX * this.factor), (int)(this.baseline - this.dtfY * this.factor));
            this.offgraphics.drawLine((int)((this.chbackpointX + this.chbackdia / 2.0 * Math.sin(this.csangle)) * this.factor), (int)(this.baseline - (this.chbackpointY + this.chbackdia / 2.0 * Math.cos(this.csangle)) * this.factor), (int)((this.chbackpointX - this.chbackdia / 2.0 * Math.sin(this.csangle)) * this.factor), (int)(this.baseline - (this.chbackpointY - this.chbackdia / 2.0 * Math.cos(this.csangle)) * this.factor));
            this.offgraphics.drawLine((int)((this.chbackpointX + this.chbackdia / 2.0 * Math.sin(this.csangle)) * this.factor), (int)(this.baseline - (this.chbackpointY + this.chbackdia / 2.0 * Math.cos(this.csangle)) * this.factor), (int)((this.chbackpointX + this.chvdia / 2.0 * Math.sin(this.csangle) + this.tapercomp * Math.cos(this.csangle)) * this.factor), (int)(this.baseline - (this.chbackpointY + this.chvdia / 2.0 * Math.cos(this.csangle) - this.tapercomp * Math.sin(this.csangle)) * this.factor));
            this.offgraphics.drawLine((int)((this.chbackpointX - this.chbackdia / 2.0 * Math.sin(this.csangle)) * this.factor), (int)(this.baseline - (this.chbackpointY - this.chbackdia / 2.0 * Math.cos(this.csangle)) * this.factor), (int)((this.chbackpointX - this.chvdia / 2.0 * Math.sin(this.csangle) + this.tapercomp * Math.cos(this.csangle)) * this.factor), (int)(this.baseline - (this.chbackpointY - this.chvdia / 2.0 * Math.cos(this.csangle) - this.tapercomp * Math.sin(this.csangle)) * this.factor));
            if (this.chvdia < this.BBd) {
                this.offgraphics.drawLine((int)((this.chbackpointX + this.chvdia / 2.0 * Math.sin(this.csangle) + this.tapercomp * Math.cos(this.csangle)) * this.factor), (int)(this.baseline - (this.chbackpointY + this.chvdia / 2.0 * Math.cos(this.csangle) - this.tapercomp * Math.sin(this.csangle)) * this.factor), (int)((this.bbcentrX - this.BBd / 2.0 * Math.cos(this.csangle + Math.asin(this.chvdia / this.BBd))) * this.factor), (int)(this.baseline - (this.BBh + this.BBd / 2.0 * Math.sin(this.csangle + Math.asin(this.chvdia / this.BBd))) * this.factor));
                this.offgraphics.drawLine((int)((this.chbackpointX - this.chvdia / 2.0 * Math.sin(this.csangle) + this.tapercomp * Math.cos(this.csangle)) * this.factor), (int)(this.baseline - (this.chbackpointY - this.chvdia / 2.0 * Math.cos(this.csangle) - this.tapercomp * Math.sin(this.csangle)) * this.factor), (int)((this.bbcentrX - this.BBd / 2.0 * Math.cos(this.csangle - Math.asin(this.chvdia / this.BBd))) * this.factor), (int)(this.baseline - (this.BBh + this.BBd / 2.0 * Math.sin(this.csangle - Math.asin(this.chvdia / this.BBd))) * this.factor));
            }
            else {
                this.offgraphics.drawLine((int)((this.chbackpointX + this.chvdia / 2.0 * Math.sin(this.csangle) + this.tapercomp * Math.cos(this.csangle)) * this.factor), (int)(this.baseline - (this.chbackpointY + this.chvdia / 2.0 * Math.cos(this.csangle) - this.tapercomp * Math.sin(this.csangle)) * this.factor), (int)((this.bbcentrX + this.chvdia / 2.0 * Math.sin(this.csangle)) * this.factor), (int)(this.baseline - (this.BBh + this.chvdia / 2.0 * Math.cos(this.csangle)) * this.factor));
                this.offgraphics.drawLine((int)((this.chbackpointX - this.chvdia / 2.0 * Math.sin(this.csangle) + this.tapercomp * Math.cos(this.csangle)) * this.factor), (int)(this.baseline - (this.chbackpointY - this.chvdia / 2.0 * Math.cos(this.csangle) - this.tapercomp * Math.sin(this.csangle)) * this.factor), (int)((this.bbcentrX - this.chvdia / 2.0 * Math.sin(this.csangle)) * this.factor), (int)(this.baseline - (this.BBh - this.chvdia / 2.0 * Math.cos(this.csangle)) * this.factor));
            }
            this.offgraphics.drawLine((int)((this.sebackpointX - this.sebackdia / 2.0 * Math.sin(this.ssangle)) * this.factor), (int)(this.baseline - (this.sebackpointY + this.sebackdia / 2.0 * Math.cos(this.ssangle)) * this.factor), (int)((this.sebackpointX + this.sebackdia / 2.0 * Math.sin(this.ssangle)) * this.factor), (int)(this.baseline - (this.sebackpointY - this.sebackdia / 2.0 * Math.cos(this.ssangle)) * this.factor));
            this.offgraphics.drawLine((int)((this.sefrontpointX - this.sebackdia / 2.0 / Math.sin(this.ssstangle) * Math.cos(this.seatangle / 180.0 * 3.141592653589793)) * this.factor), (int)(this.baseline - (this.sefrontpointY + this.sebackdia / 2.0 / Math.sin(this.ssstangle) * Math.sin(this.seatangle / 180.0 * 3.141592653589793)) * this.factor), (int)((this.sebackpointX - this.sebackdia / 2.0 * Math.sin(this.ssangle)) * this.factor), (int)(this.baseline - (this.sebackpointY + this.sebackdia / 2.0 * Math.cos(this.ssangle)) * this.factor));
            this.offgraphics.drawLine((int)((this.sefrontpointX + this.sebackdia / 2.0 / Math.sin(this.ssstangle) * Math.cos(this.seatangle / 180.0 * 3.141592653589793)) * this.factor), (int)(this.baseline - (this.sefrontpointY - this.sebackdia / 2.0 / Math.sin(this.ssstangle) * Math.sin(this.seatangle / 180.0 * 3.141592653589793)) * this.factor), (int)((this.sebackpointX + this.sebackdia / 2.0 * Math.sin(this.ssangle)) * this.factor), (int)(this.baseline - (this.sebackpointY - this.sebackdia / 2.0 * Math.cos(this.ssangle)) * this.factor));
            this.offgraphics.setColor(Color.black);
            this.offgraphics.drawOval((int)((this.wheelbase + this.rearaxlX - this.frontaxlY - this.ftrav * Math.cos(this.headangle / 180.0 * 3.141592653589793)) * this.factor), (int)(this.baseline - (this.frontwd + this.ftrav * Math.sin(this.headangle / 180.0 * 3.141592653589793)) * this.factor), (int)(this.frontwd * this.factor), (int)(this.frontwd * this.factor));
            this.offgraphics.drawOval(0, (int)(this.baseline - this.rearwd * this.factor), (int)(this.rearwd * this.factor), (int)(this.rearwd * this.factor));
            this.offgraphics.setColor(Color.blue);
            this.offgraphics.drawString("Effective top tube = " + (int)this.ttlength + " mm", 10, 20);
        }
        if (this.showwhat == 2) {
            this.maxheight = this.dropoutspace + this.chainz * 2.0 + this.chbackdia + 10.0;
            if (this.maxheight / this.size().height > (this.CSlen2 + this.axlauxcs + this.BBd + 40.0) / this.size().width) {
                this.factor = this.size().height / this.maxheight;
            }
            else {
                this.factor = this.size().width / (this.CSlen2 + this.axlauxcs + this.BBd + 40.0);
            }
            this.offgraphics.setColor(this.mygray);
            this.offgraphics.drawLine((int)(40.0 * this.factor), 0, (int)(40.0 * this.factor), this.baseline);
            if (this.cbendy) {
                this.offgraphics.drawLine((int)(this.cstopshow * this.factor), (int)(this.baseline / 2 - this.chfrontpointZ * this.factor), (int)((this.cstopshow - this.csfrontlenj) * this.factor), (int)(this.baseline / 2 - this.cpointZ * this.factor));
                this.offgraphics.drawLine((int)((this.cstopshow - this.csfrontlenj) * this.factor), (int)(this.baseline / 2 - this.cpointZ * this.factor), (int)((40.0 + this.axlauxcs) * this.factor), (int)(this.baseline / 2 - this.chbackpointZ * this.factor));
                this.offgraphics.drawLine((int)(this.cstopshow * this.factor), (int)(this.baseline / 2 + this.chfrontpointZ * this.factor), (int)((this.cstopshow - this.csfrontlenj) * this.factor), (int)(this.baseline / 2 + this.cpointZ * this.factor));
                this.offgraphics.drawLine((int)((this.cstopshow - this.csfrontlenj) * this.factor), (int)(this.baseline / 2 + this.cpointZ * this.factor), (int)((40.0 + this.axlauxcs) * this.factor), (int)(this.baseline / 2 + this.chbackpointZ * this.factor));
                this.offgraphics.drawArc((int)((this.cstopshow - this.cradcenj - this.cradius) * this.factor), (int)(this.baseline / 2 - (this.cradcenZ + this.cradius) * this.factor), (int)(this.cradius * 2.0 * this.factor), (int)(this.cradius * 2.0 * this.factor), (int)(90.0 - (this.csupangcom + this.csupang) / 3.141592653589793 * 180.0), (int)this.cbendang);
                this.offgraphics.drawArc((int)((this.cstopshow - this.cradcenj - this.cradius) * this.factor), (int)(this.baseline / 2 - (-this.cradcenZ + this.cradius) * this.factor), (int)(this.cradius * 2.0 * this.factor), (int)(this.cradius * 2.0 * this.factor), (int)(-90.0 + (this.csupangcom + this.csupang) / 3.141592653589793 * 180.0), (int)(-this.cbendang));
            }
            else {
                this.offgraphics.drawLine((int)(this.cstopshow * this.factor), (int)(this.baseline / 2 - this.chfrontpointZ * this.factor), (int)((40.0 + this.axlauxcs) * this.factor), (int)(this.baseline / 2 - this.chbackpointZ * this.factor));
                this.offgraphics.drawLine((int)(this.cstopshow * this.factor), (int)(this.baseline / 2 + this.chfrontpointZ * this.factor), (int)((40.0 + this.axlauxcs) * this.factor), (int)(this.baseline / 2 + this.chbackpointZ * this.factor));
            }
            this.offgraphics.setColor(Color.black);
            this.offgraphics.drawRect((int)((40.0 - this.rearwd / 2.0) * this.factor), (int)(this.baseline / 2 - this.rearww / 2.0 * this.factor), (int)(this.rearwd * this.factor), (int)(this.rearww * this.factor));
            this.offgraphics.setColor(Color.red);
            this.offgraphics.drawRect((int)((40.0 + this.axlauxcs + this.CSlen2) * this.factor), (int)(this.baseline / 2 - this.BBlen / 2.0 * this.factor), (int)(this.BBd * this.factor), (int)(this.BBlen * this.factor));
            if (this.cbendy) {
                this.offgraphics.drawLine((int)((this.cstopshow - this.cradcenj + (this.cradius - this.chhdia / 2.0) * Math.cos(1.5707963267948966 - this.csupangcom - this.csupang)) * this.factor), (int)(this.baseline / 2 - (this.cradcenZ + (this.cradius - this.chhdia / 2.0) * Math.sin(1.5707963267948966 - this.csupangcom - this.csupang)) * this.factor), (int)(this.cstopshow * this.factor), (int)(this.baseline / 2 - this.csgap / 2.0 * this.factor));
                this.offgraphics.drawLine((int)((this.cstopshow - this.cradcenj + (this.cradius - this.chhdia / 2.0) * Math.cos(1.5707963267948966 - this.csupangcom - this.csupang)) * this.factor), (int)(this.baseline / 2 - (-this.cradcenZ - (this.cradius - this.chhdia / 2.0) * Math.sin(1.5707963267948966 - this.csupangcom - this.csupang)) * this.factor), (int)(this.cstopshow * this.factor), (int)(this.baseline / 2 + this.csgap / 2.0 * this.factor));
                this.offgraphics.drawLine((int)((this.cstopshow - this.cradcenj + (this.cradius + this.chhdia / 2.0) * Math.cos(1.5707963267948966 - this.csupangcom - this.csupang)) * this.factor), (int)(this.baseline / 2 - (this.cradcenZ + (this.cradius + this.chhdia / 2.0) * Math.sin(1.5707963267948966 - this.csupangcom - this.csupang)) * this.factor), (int)(this.cstopshow * this.factor), (int)(this.baseline / 2 - (this.BBlen / 2.0 - this.BBext) * this.factor));
                this.offgraphics.drawLine((int)((this.cstopshow - this.cradcenj + (this.cradius + this.chhdia / 2.0) * Math.cos(1.5707963267948966 - this.csupangcom - this.csupang)) * this.factor), (int)(this.baseline / 2 - (-this.cradcenZ - (this.cradius + this.chhdia / 2.0) * Math.sin(1.5707963267948966 - this.csupangcom - this.csupang)) * this.factor), (int)(this.cstopshow * this.factor), (int)(this.baseline / 2 + (this.BBlen / 2.0 - this.BBext) * this.factor));
                this.offgraphics.drawLine((int)((this.cstopshow - this.cradcenj + (this.cradius - this.chhdia / 2.0) * Math.cos(1.5707963267948966 + this.cbendang / 180.0 * 3.141592653589793 - this.csupangcom - this.csupang)) * this.factor), (int)(this.baseline / 2 - (this.cradcenZ + (this.cradius - this.chhdia / 2.0) * Math.sin(1.5707963267948966 + this.cbendang / 180.0 * 3.141592653589793 - this.csupangcom - this.csupang)) * this.factor), (int)((this.cstopshow - this.CSlen2 - this.chbackdia / 2.0 * Math.sin(this.csupangcom - this.cslowang) + this.chtaper / Math.cos(this.chtaperang) * Math.cos(this.csupangcom - this.cslowang + this.chtaperang)) * this.factor), (int)(this.baseline / 2 - (this.chbackpointZ - this.chbackdia / 2.0 * Math.cos(this.csupangcom - this.cslowang) - this.chtaper / Math.cos(this.chtaperang) * Math.sin(this.csupangcom - this.cslowang + this.chtaperang)) * this.factor));
                this.offgraphics.drawLine((int)((this.cstopshow - this.cradcenj + (this.cradius + this.chhdia / 2.0) * Math.cos(1.5707963267948966 + this.cbendang / 180.0 * 3.141592653589793 - this.csupangcom - this.csupang)) * this.factor), (int)(this.baseline / 2 - (this.cradcenZ + (this.cradius + this.chhdia / 2.0) * Math.sin(1.5707963267948966 + this.cbendang / 180.0 * 3.141592653589793 - this.csupangcom - this.csupang)) * this.factor), (int)((this.cstopshow - this.CSlen2 + this.chbackdia / 2.0 * Math.sin(this.csupangcom - this.cslowang) + this.chtaper / Math.cos(this.chtaperang) * Math.cos(this.csupangcom - this.cslowang - this.chtaperang)) * this.factor), (int)(this.baseline / 2 - (this.chbackpointZ + this.chbackdia / 2.0 * Math.cos(this.csupangcom - this.cslowang) - this.chtaper / Math.cos(this.chtaperang) * Math.sin(this.csupangcom - this.cslowang - this.chtaperang)) * this.factor));
                this.offgraphics.drawLine((int)((this.cstopshow - this.cradcenj + (this.cradius - this.chhdia / 2.0) * Math.cos(1.5707963267948966 + this.cbendang / 180.0 * 3.141592653589793 - this.csupangcom - this.csupang)) * this.factor), (int)(this.baseline / 2 - (-this.cradcenZ - (this.cradius - this.chhdia / 2.0) * Math.sin(1.5707963267948966 + this.cbendang / 180.0 * 3.141592653589793 - this.csupangcom - this.csupang)) * this.factor), (int)((this.cstopshow - this.CSlen2 - this.chbackdia / 2.0 * Math.sin(this.csupangcom - this.cslowang) + this.chtaper / Math.cos(this.chtaperang) * Math.cos(this.csupangcom - this.cslowang + this.chtaperang)) * this.factor), (int)(this.baseline / 2 - (-this.chbackpointZ + this.chbackdia / 2.0 * Math.cos(this.csupangcom - this.cslowang) + this.chtaper / Math.cos(this.chtaperang) * Math.sin(this.csupangcom - this.cslowang + this.chtaperang)) * this.factor));
                this.offgraphics.drawLine((int)((this.cstopshow - this.cradcenj + (this.cradius + this.chhdia / 2.0) * Math.cos(1.5707963267948966 + this.cbendang / 180.0 * 3.141592653589793 - this.csupangcom - this.csupang)) * this.factor), (int)(this.baseline / 2 - (-this.cradcenZ - (this.cradius + this.chhdia / 2.0) * Math.sin(1.5707963267948966 + this.cbendang / 180.0 * 3.141592653589793 - this.csupangcom - this.csupang)) * this.factor), (int)((this.cstopshow - this.CSlen2 + this.chbackdia / 2.0 * Math.sin(this.csupangcom - this.cslowang) + this.chtaper / Math.cos(this.chtaperang) * Math.cos(this.csupangcom - this.cslowang - this.chtaperang)) * this.factor), (int)(this.baseline / 2 - (-this.chbackpointZ - this.chbackdia / 2.0 * Math.cos(this.csupangcom - this.cslowang) + this.chtaper / Math.cos(this.chtaperang) * Math.sin(this.csupangcom - this.cslowang - this.chtaperang)) * this.factor));
                this.offgraphics.drawLine((int)((this.cstopshow - this.CSlen2 - this.chbackdia / 2.0 * Math.sin(this.csupangcom - this.cslowang)) * this.factor), (int)(this.baseline / 2 - (this.chbackpointZ - this.chbackdia / 2.0 * Math.cos(this.csupangcom)) * this.factor), (int)((this.cstopshow - this.CSlen2 - this.chbackdia / 2.0 * Math.sin(this.csupangcom - this.cslowang) + this.chtaper / Math.cos(this.chtaperang) * Math.cos(this.csupangcom - this.cslowang + this.chtaperang)) * this.factor), (int)(this.baseline / 2 - (this.chbackpointZ - this.chbackdia / 2.0 * Math.cos(this.csupangcom - this.cslowang) - this.chtaper / Math.cos(this.chtaperang) * Math.sin(this.csupangcom - this.cslowang + this.chtaperang)) * this.factor));
                this.offgraphics.drawLine((int)((this.cstopshow - this.CSlen2 - this.chbackdia / 2.0 * Math.sin(this.csupangcom - this.cslowang)) * this.factor), (int)(this.baseline / 2 - (-this.chbackpointZ + this.chbackdia / 2.0 * Math.cos(this.csupangcom)) * this.factor), (int)((this.cstopshow - this.CSlen2 - this.chbackdia / 2.0 * Math.sin(this.csupangcom - this.cslowang) + this.chtaper / Math.cos(this.chtaperang) * Math.cos(this.csupangcom - this.cslowang + this.chtaperang)) * this.factor), (int)(this.baseline / 2 - (-this.chbackpointZ + this.chbackdia / 2.0 * Math.cos(this.csupangcom - this.cslowang) + this.chtaper / Math.cos(this.chtaperang) * Math.sin(this.csupangcom - this.cslowang + this.chtaperang)) * this.factor));
                this.offgraphics.drawLine((int)((this.cstopshow - this.CSlen2 + this.chbackdia / 2.0 * Math.sin(this.csupangcom - this.cslowang)) * this.factor), (int)(this.baseline / 2 - (this.chbackpointZ + this.chbackdia / 2.0 * Math.cos(this.csupangcom)) * this.factor), (int)((this.cstopshow - this.CSlen2 + this.chbackdia / 2.0 * Math.sin(this.csupangcom - this.cslowang) + this.chtaper / Math.cos(this.chtaperang) * Math.cos(this.csupangcom - this.cslowang - this.chtaperang)) * this.factor), (int)(this.baseline / 2 - (this.chbackpointZ + this.chbackdia / 2.0 * Math.cos(this.csupangcom - this.cslowang) - this.chtaper / Math.cos(this.chtaperang) * Math.sin(this.csupangcom - this.cslowang - this.chtaperang)) * this.factor));
                this.offgraphics.drawLine((int)((this.cstopshow - this.CSlen2 + this.chbackdia / 2.0 * Math.sin(this.csupangcom - this.cslowang)) * this.factor), (int)(this.baseline / 2 - (-this.chbackpointZ - this.chbackdia / 2.0 * Math.cos(this.csupangcom)) * this.factor), (int)((this.cstopshow - this.CSlen2 + this.chbackdia / 2.0 * Math.sin(this.csupangcom - this.cslowang) + this.chtaper / Math.cos(this.chtaperang) * Math.cos(this.csupangcom - this.cslowang - this.chtaperang)) * this.factor), (int)(this.baseline / 2 - (-this.chbackpointZ - this.chbackdia / 2.0 * Math.cos(this.csupangcom - this.cslowang) + this.chtaper / Math.cos(this.chtaperang) * Math.sin(this.csupangcom - this.cslowang - this.chtaperang)) * this.factor));
                this.offgraphics.drawLine((int)((this.cstopshow - this.CSlen2 - this.chbackdia / 2.0 * Math.sin(this.csupangcom - this.cslowang)) * this.factor), (int)(this.baseline / 2 - (this.chbackpointZ - this.chbackdia / 2.0 * Math.cos(this.csupangcom - this.cslowang)) * this.factor), (int)((this.cstopshow - this.CSlen2 + this.chbackdia / 2.0 * Math.sin(this.csupangcom - this.cslowang)) * this.factor), (int)(this.baseline / 2 - (this.chbackpointZ + this.chbackdia / 2.0 * Math.cos(this.csupangcom - this.cslowang)) * this.factor));
                this.offgraphics.drawLine((int)((this.cstopshow - this.CSlen2 - this.chbackdia / 2.0 * Math.sin(this.csupangcom - this.cslowang)) * this.factor), (int)(this.baseline / 2 - (-this.chbackpointZ + this.chbackdia / 2.0 * Math.cos(this.csupangcom - this.cslowang)) * this.factor), (int)((this.cstopshow - this.CSlen2 + this.chbackdia / 2.0 * Math.sin(this.csupangcom - this.cslowang)) * this.factor), (int)(this.baseline / 2 - (-this.chbackpointZ - this.chbackdia / 2.0 * Math.cos(this.csupangcom - this.cslowang)) * this.factor));
                this.offgraphics.drawArc((int)((this.cstopshow - this.cradcenj - (this.cradius - this.chhdia / 2.0)) * this.factor), (int)(this.baseline / 2 - (this.cradcenZ + (this.cradius - this.chhdia / 2.0)) * this.factor), (int)((this.cradius * 2.0 - this.chhdia) * this.factor), (int)((this.cradius * 2.0 - this.chhdia) * this.factor), (int)(90.0 - (this.csupangcom + this.csupang) / 3.141592653589793 * 180.0), (int)this.cbendang);
                this.offgraphics.drawArc((int)((this.cstopshow - this.cradcenj - (this.cradius + this.chhdia / 2.0)) * this.factor), (int)(this.baseline / 2 - (this.cradcenZ + (this.cradius + this.chhdia / 2.0)) * this.factor), (int)((this.cradius * 2.0 + this.chhdia) * this.factor), (int)((this.cradius * 2.0 + this.chhdia) * this.factor), (int)(90.0 - (this.csupangcom + this.csupang) / 3.141592653589793 * 180.0), (int)this.cbendang);
                this.offgraphics.drawArc((int)((this.cstopshow - this.cradcenj - (this.cradius - this.chhdia / 2.0)) * this.factor), (int)(this.baseline / 2 - (-this.cradcenZ + (this.cradius - this.chhdia / 2.0)) * this.factor), (int)((this.cradius * 2.0 - this.chhdia) * this.factor), (int)((this.cradius * 2.0 - this.chhdia) * this.factor), (int)(-90.0 + (this.csupangcom + this.csupang) / 3.141592653589793 * 180.0), (int)(-this.cbendang));
                this.offgraphics.drawArc((int)((this.cstopshow - this.cradcenj - (this.cradius + this.chhdia / 2.0)) * this.factor), (int)(this.baseline / 2 - (-this.cradcenZ + (this.cradius + this.chhdia / 2.0)) * this.factor), (int)((this.cradius * 2.0 + this.chhdia) * this.factor), (int)((this.cradius * 2.0 + this.chhdia) * this.factor), (int)(-90.0 + (this.csupangcom + this.csupang) / 3.141592653589793 * 180.0), (int)(-this.cbendang));
            }
            else {
                this.offgraphics.drawLine((int)(this.cstopshow * this.factor), (int)(this.baseline / 2 - this.csgap / 2.0 * this.factor), (int)((this.cstopshow - this.CSlen2 - this.chbackdia / 2.0 * Math.sin(this.csupangcom) + this.chtaper / Math.cos(this.chtaperang) * Math.cos(this.csupangcom + this.chtaperang)) * this.factor), (int)(this.baseline / 2 - (this.chbackpointZ - this.chbackdia / 2.0 * Math.cos(this.csupangcom) - this.chtaper / Math.cos(this.chtaperang) * Math.sin(this.csupangcom + this.chtaperang)) * this.factor));
                this.offgraphics.drawLine((int)((this.cstopshow - this.CSlen2 - this.chbackdia / 2.0 * Math.sin(this.csupangcom)) * this.factor), (int)(this.baseline / 2 - (this.chbackpointZ - this.chbackdia / 2.0 * Math.cos(this.csupangcom)) * this.factor), (int)((this.cstopshow - this.CSlen2 - this.chbackdia / 2.0 * Math.sin(this.csupangcom) + this.chtaper / Math.cos(this.chtaperang) * Math.cos(this.csupangcom + this.chtaperang)) * this.factor), (int)(this.baseline / 2 - (this.chbackpointZ - this.chbackdia / 2.0 * Math.cos(this.csupangcom) - this.chtaper / Math.cos(this.chtaperang) * Math.sin(this.csupangcom + this.chtaperang)) * this.factor));
                this.offgraphics.drawLine((int)(this.cstopshow * this.factor), (int)(this.baseline / 2 + this.csgap / 2.0 * this.factor), (int)((this.cstopshow - this.CSlen2 - this.chbackdia / 2.0 * Math.sin(this.csupangcom) + this.chtaper / Math.cos(this.chtaperang) * Math.cos(this.csupangcom + this.chtaperang)) * this.factor), (int)(this.baseline / 2 - (-this.chbackpointZ + this.chbackdia / 2.0 * Math.cos(this.csupangcom) + this.chtaper / Math.cos(this.chtaperang) * Math.sin(this.csupangcom + this.chtaperang)) * this.factor));
                this.offgraphics.drawLine((int)((this.cstopshow - this.CSlen2 - this.chbackdia / 2.0 * Math.sin(this.csupangcom)) * this.factor), (int)(this.baseline / 2 - (-this.chbackpointZ + this.chbackdia / 2.0 * Math.cos(this.csupangcom)) * this.factor), (int)((this.cstopshow - this.CSlen2 - this.chbackdia / 2.0 * Math.sin(this.csupangcom) + this.chtaper / Math.cos(this.chtaperang) * Math.cos(this.csupangcom + this.chtaperang)) * this.factor), (int)(this.baseline / 2 - (-this.chbackpointZ + this.chbackdia / 2.0 * Math.cos(this.csupangcom) + this.chtaper / Math.cos(this.chtaperang) * Math.sin(this.csupangcom + this.chtaperang)) * this.factor));
                this.offgraphics.drawLine((int)(this.cstopshow * this.factor), (int)(this.baseline / 2 - (this.BBlen / 2.0 - this.BBext) * this.factor), (int)((this.cstopshow - this.CSlen2 + this.chbackdia / 2.0 * Math.sin(this.csupangcom) + this.chtaper / Math.cos(this.chtaperang) * Math.cos(this.csupangcom - this.chtaperang)) * this.factor), (int)(this.baseline / 2 - (this.chbackpointZ + this.chbackdia / 2.0 * Math.cos(this.csupangcom) - this.chtaper / Math.cos(this.chtaperang) * Math.sin(this.csupangcom - this.chtaperang)) * this.factor));
                this.offgraphics.drawLine((int)((this.cstopshow - this.CSlen2 + this.chbackdia / 2.0 * Math.sin(this.csupangcom)) * this.factor), (int)(this.baseline / 2 - (this.chbackpointZ + this.chbackdia / 2.0 * Math.cos(this.csupangcom)) * this.factor), (int)((this.cstopshow - this.CSlen2 + this.chbackdia / 2.0 * Math.sin(this.csupangcom) + this.chtaper / Math.cos(this.chtaperang) * Math.cos(this.csupangcom - this.chtaperang)) * this.factor), (int)(this.baseline / 2 - (this.chbackpointZ + this.chbackdia / 2.0 * Math.cos(this.csupangcom) - this.chtaper / Math.cos(this.chtaperang) * Math.sin(this.csupangcom - this.chtaperang)) * this.factor));
                this.offgraphics.drawLine((int)(this.cstopshow * this.factor), (int)(this.baseline / 2 + (this.BBlen / 2.0 - this.BBext) * this.factor), (int)((this.cstopshow - this.CSlen2 + this.chbackdia / 2.0 * Math.sin(this.csupangcom) + this.chtaper / Math.cos(this.chtaperang) * Math.cos(this.csupangcom - this.chtaperang)) * this.factor), (int)(this.baseline / 2 - (-this.chbackpointZ - this.chbackdia / 2.0 * Math.cos(this.csupangcom) + this.chtaper / Math.cos(this.chtaperang) * Math.sin(this.csupangcom - this.chtaperang)) * this.factor));
                this.offgraphics.drawLine((int)((this.cstopshow - this.CSlen2 + this.chbackdia / 2.0 * Math.sin(this.csupangcom)) * this.factor), (int)(this.baseline / 2 - (-this.chbackpointZ - this.chbackdia / 2.0 * Math.cos(this.csupangcom)) * this.factor), (int)((this.cstopshow - this.CSlen2 + this.chbackdia / 2.0 * Math.sin(this.csupangcom) + this.chtaper / Math.cos(this.chtaperang) * Math.cos(this.csupangcom - this.chtaperang)) * this.factor), (int)(this.baseline / 2 - (-this.chbackpointZ - this.chbackdia / 2.0 * Math.cos(this.csupangcom) + this.chtaper / Math.cos(this.chtaperang) * Math.sin(this.csupangcom - this.chtaperang)) * this.factor));
                this.offgraphics.drawLine((int)((this.cstopshow - this.CSlen2 - this.chbackdia / 2.0 * Math.sin(this.csupangcom)) * this.factor), (int)(this.baseline / 2 - (this.chbackpointZ - this.chbackdia / 2.0 * Math.cos(this.csupangcom)) * this.factor), (int)((this.cstopshow - this.CSlen2 + this.chbackdia / 2.0 * Math.sin(this.csupangcom)) * this.factor), (int)(this.baseline / 2 - (this.chbackpointZ + this.chbackdia / 2.0 * Math.cos(this.csupangcom)) * this.factor));
                this.offgraphics.drawLine((int)((this.cstopshow - this.CSlen2 - this.chbackdia / 2.0 * Math.sin(this.csupangcom)) * this.factor), (int)(this.baseline / 2 - (-this.chbackpointZ + this.chbackdia / 2.0 * Math.cos(this.csupangcom)) * this.factor), (int)((this.cstopshow - this.CSlen2 + this.chbackdia / 2.0 * Math.sin(this.csupangcom)) * this.factor), (int)(this.baseline / 2 - (-this.chbackpointZ - this.chbackdia / 2.0 * Math.cos(this.csupangcom)) * this.factor));
            }
        }
        if (this.showwhat == 3) {
            this.maxheight = this.dropoutspace + this.seatz * 2.0 + this.sebackdia + 10.0;
            if (this.maxheight / this.size().height > (this.sstopshow + this.stdiameter) / this.size().width) {
                this.factor = this.size().height / this.maxheight;
            }
            else {
                this.factor = this.size().width / (this.sstopshow + this.stdiameter);
            }
            this.offgraphics.setColor(this.mygray);
            this.offgraphics.drawLine((int)(40.0 * this.factor), 0, (int)(40.0 * this.factor), this.baseline);
            if (this.sbendy) {
                this.offgraphics.drawLine((int)(this.sstopshow * this.factor), (int)(this.baseline / 2 - this.sefrontpointZ * this.factor), (int)((this.sstopshow - this.sstoplenj) * this.factor), (int)(this.baseline / 2 - this.spointZ * this.factor));
                this.offgraphics.drawLine((int)((this.sstopshow - this.sstoplenj) * this.factor), (int)(this.baseline / 2 - this.spointZ * this.factor), (int)((40.0 + this.axlauxss) * this.factor), (int)(this.baseline / 2 - this.sebackpointZ * this.factor));
                this.offgraphics.drawLine((int)(this.sstopshow * this.factor), (int)(this.baseline / 2 + this.sefrontpointZ * this.factor), (int)((this.sstopshow - this.sstoplenj) * this.factor), (int)(this.baseline / 2 + this.spointZ * this.factor));
                this.offgraphics.drawLine((int)((this.sstopshow - this.sstoplenj) * this.factor), (int)(this.baseline / 2 + this.spointZ * this.factor), (int)((40.0 + this.axlauxss) * this.factor), (int)(this.baseline / 2 + this.sebackpointZ * this.factor));
                this.offgraphics.drawArc((int)((this.sstopshow - this.sradcenj - this.sradius) * this.factor), (int)(this.baseline / 2 - (this.sradcenZ + this.sradius) * this.factor), (int)(this.sradius * 2.0 * this.factor), (int)(this.sradius * 2.0 * this.factor), (int)(90.0 - (this.ssupangcom + this.ssupang) / 3.141592653589793 * 180.0), (int)this.sbendang);
                this.offgraphics.drawArc((int)((this.sstopshow - this.sradcenj - this.sradius) * this.factor), (int)(this.baseline / 2 - (-this.sradcenZ + this.sradius) * this.factor), (int)(this.sradius * 2.0 * this.factor), (int)(this.sradius * 2.0 * this.factor), (int)(-90.0 + (this.ssupangcom + this.ssupang) / 3.141592653589793 * 180.0), (int)(-this.sbendang));
            }
            else {
                this.offgraphics.drawLine((int)(this.sstopshow * this.factor), (int)(this.baseline / 2 - this.sefrontpointZ * this.factor), (int)((40.0 + this.axlauxss) * this.factor), (int)(this.baseline / 2 - this.sebackpointZ * this.factor));
                this.offgraphics.drawLine((int)(this.sstopshow * this.factor), (int)(this.baseline / 2 + this.sefrontpointZ * this.factor), (int)((40.0 + this.axlauxss) * this.factor), (int)(this.baseline / 2 + this.sebackpointZ * this.factor));
            }
            this.offgraphics.setColor(Color.black);
            this.offgraphics.drawRect((int)((40.0 - this.rearwd / 2.0) * this.factor), (int)(this.baseline / 2 - this.rearww / 2.0 * this.factor), (int)(this.rearwd * this.factor), (int)(this.rearww * this.factor));
            this.offgraphics.setColor(Color.red);
            this.offgraphics.drawOval((int)((this.sstopshow - this.stdiameter / 2.0 * (1.0 - Math.cos(this.stayang / 180.0 * 3.141592653589793))) * this.factor), (int)(this.baseline / 2 - this.stdiameter / 2.0 * this.factor), (int)(this.stdiameter * this.factor), (int)(this.stdiameter * this.factor));
            if (this.sbendy) {
                if (-this.stdiameter / 2.0 < this.stayoffset - this.sebackdia / 2.0) {
                    this.offgraphics.drawLine((int)((this.sstopshow - this.sradcenj + (this.sradius - this.sebackdia / 2.0) * Math.cos(1.5707963267948966 - this.ssupangcom - this.ssupang)) * this.factor), (int)(this.baseline / 2 - (this.sradcenZ + (this.sradius - this.sebackdia / 2.0) * Math.sin(1.5707963267948966 - this.ssupangcom - this.ssupang)) * this.factor), (int)((this.sstopshow + this.stdiameter / 2.0 * Math.cos(this.stayang / 180.0 * 3.141592653589793) - this.stdiameter / 2.0 * Math.cos(this.ssupang + this.ssupangcom + Math.asin((this.stayoffset - this.sebackdia / 2.0) / (this.stdiameter / 2.0)))) * this.factor), (int)(this.baseline / 2 - this.stdiameter / 2.0 * Math.sin(this.ssupang + this.ssupangcom + Math.asin((this.stayoffset - this.sebackdia / 2.0) / (this.stdiameter / 2.0))) * this.factor));
                    this.offgraphics.drawLine((int)((this.sstopshow - this.sradcenj + (this.sradius - this.sebackdia / 2.0) * Math.cos(1.5707963267948966 - this.ssupangcom - this.ssupang)) * this.factor), (int)(this.baseline / 2 - (-this.sradcenZ - (this.sradius - this.sebackdia / 2.0) * Math.sin(1.5707963267948966 - this.ssupangcom - this.ssupang)) * this.factor), (int)((this.sstopshow + this.stdiameter / 2.0 * Math.cos(this.stayang / 180.0 * 3.141592653589793) - this.stdiameter / 2.0 * Math.cos(this.ssupang + this.ssupangcom + Math.asin((this.stayoffset - this.sebackdia / 2.0) / (this.stdiameter / 2.0)))) * this.factor), (int)(this.baseline / 2 - -this.stdiameter / 2.0 * Math.sin(this.ssupang + this.ssupangcom + Math.asin((this.stayoffset - this.sebackdia / 2.0) / (this.stdiameter / 2.0))) * this.factor));
                }
                else {
                    this.offgraphics.drawLine((int)((this.sstopshow - this.sradcenj + (this.sradius - this.sebackdia / 2.0) * Math.cos(1.5707963267948966 - this.ssupangcom - this.ssupang)) * this.factor), (int)(this.baseline / 2 - (this.sradcenZ + (this.sradius - this.sebackdia / 2.0) * Math.sin(1.5707963267948966 - this.ssupangcom - this.ssupang)) * this.factor), (int)((this.sstopshow + this.stdiameter / 2.0 * Math.cos(this.stayang / 180.0 * 3.141592653589793) - (this.stayoffset - this.sebackdia / 2.0) * Math.cos(this.ssupang + this.ssupangcom + 1.5707963267948966)) * this.factor), (int)(this.baseline / 2 - (this.stayoffset - this.sebackdia / 2.0) * Math.sin(this.ssupang + this.ssupangcom + 1.5707963267948966) * this.factor));
                    this.offgraphics.drawLine((int)((this.sstopshow - this.sradcenj + (this.sradius - this.sebackdia / 2.0) * Math.cos(1.5707963267948966 - this.ssupangcom - this.ssupang)) * this.factor), (int)(this.baseline / 2 - (-this.sradcenZ - (this.sradius - this.sebackdia / 2.0) * Math.sin(1.5707963267948966 - this.ssupangcom - this.ssupang)) * this.factor), (int)((this.sstopshow + this.stdiameter / 2.0 * Math.cos(this.stayang / 180.0 * 3.141592653589793) - (this.stayoffset - this.sebackdia / 2.0) * Math.cos(this.ssupang + this.ssupangcom + 1.5707963267948966)) * this.factor), (int)(this.baseline / 2 - -(this.stayoffset - this.sebackdia / 2.0) * Math.sin(this.ssupang + this.ssupangcom + 1.5707963267948966) * this.factor));
                }
                if (this.stdiameter / 2.0 > this.stayoffset + this.sebackdia / 2.0) {
                    this.offgraphics.drawLine((int)((this.sstopshow - this.sradcenj + (this.sradius + this.sebackdia / 2.0) * Math.cos(1.5707963267948966 - this.ssupangcom - this.ssupang)) * this.factor), (int)(this.baseline / 2 - (this.sradcenZ + (this.sradius + this.sebackdia / 2.0) * Math.sin(1.5707963267948966 - this.ssupangcom - this.ssupang)) * this.factor), (int)((this.sstopshow + this.stdiameter / 2.0 * Math.cos(this.stayang / 180.0 * 3.141592653589793) - this.stdiameter / 2.0 * Math.cos(this.ssupang + this.ssupangcom + Math.asin((this.stayoffset + this.sebackdia / 2.0) / (this.stdiameter / 2.0)))) * this.factor), (int)(this.baseline / 2 - this.stdiameter / 2.0 * Math.sin(this.ssupang + this.ssupangcom + Math.asin((this.stayoffset + this.sebackdia / 2.0) / (this.stdiameter / 2.0))) * this.factor));
                    this.offgraphics.drawLine((int)((this.sstopshow - this.sradcenj + (this.sradius + this.sebackdia / 2.0) * Math.cos(1.5707963267948966 - this.ssupangcom - this.ssupang)) * this.factor), (int)(this.baseline / 2 - (-this.sradcenZ - (this.sradius + this.sebackdia / 2.0) * Math.sin(1.5707963267948966 - this.ssupangcom - this.ssupang)) * this.factor), (int)((this.sstopshow + this.stdiameter / 2.0 * Math.cos(this.stayang / 180.0 * 3.141592653589793) - this.stdiameter / 2.0 * Math.cos(this.ssupang + this.ssupangcom + Math.asin((this.stayoffset + this.sebackdia / 2.0) / (this.stdiameter / 2.0)))) * this.factor), (int)(this.baseline / 2 - -this.stdiameter / 2.0 * Math.sin(this.ssupang + this.ssupangcom + Math.asin((this.stayoffset + this.sebackdia / 2.0) / (this.stdiameter / 2.0))) * this.factor));
                }
                else {
                    this.offgraphics.drawLine((int)((this.sstopshow - this.sradcenj + (this.sradius + this.sebackdia / 2.0) * Math.cos(1.5707963267948966 - this.ssupangcom - this.ssupang)) * this.factor), (int)(this.baseline / 2 - (this.sradcenZ + (this.sradius + this.sebackdia / 2.0) * Math.sin(1.5707963267948966 - this.ssupangcom - this.ssupang)) * this.factor), (int)((this.sstopshow + this.stdiameter / 2.0 * Math.cos(this.stayang / 180.0 * 3.141592653589793) - (this.stayoffset + this.sebackdia / 2.0) * Math.cos(this.ssupang + this.ssupangcom + 1.5707963267948966)) * this.factor), (int)(this.baseline / 2 - (this.stayoffset + this.sebackdia / 2.0) * Math.sin(this.ssupang + this.ssupangcom + 1.5707963267948966) * this.factor));
                    this.offgraphics.drawLine((int)((this.sstopshow - this.sradcenj + (this.sradius + this.sebackdia / 2.0) * Math.cos(1.5707963267948966 - this.ssupangcom - this.ssupang)) * this.factor), (int)(this.baseline / 2 - (-this.sradcenZ - (this.sradius + this.sebackdia / 2.0) * Math.sin(1.5707963267948966 - this.ssupangcom - this.ssupang)) * this.factor), (int)((this.sstopshow + this.stdiameter / 2.0 * Math.cos(this.stayang / 180.0 * 3.141592653589793) - (this.stayoffset + this.sebackdia / 2.0) * Math.cos(this.ssupang + this.ssupangcom + 1.5707963267948966)) * this.factor), (int)(this.baseline / 2 - -(this.stayoffset + this.sebackdia / 2.0) * Math.sin(this.ssupang + this.ssupangcom + 1.5707963267948966) * this.factor));
                }
                this.offgraphics.drawLine((int)((this.sstopshow - this.SSlen2 - this.sebackdia / 2.0 * Math.sin(this.ssupangcom - this.sslowang)) * this.factor), (int)(this.baseline / 2 - (this.sebackpointZ - this.sebackdia / 2.0 * Math.cos(this.ssupangcom - this.sslowang)) * this.factor), (int)((this.sstopshow - this.sradcenj + (this.sradius - this.sebackdia / 2.0) * Math.cos(1.5707963267948966 + this.sbendang / 180.0 * 3.141592653589793 - this.ssupangcom - this.ssupang)) * this.factor), (int)(this.baseline / 2 - (this.sradcenZ + (this.sradius - this.sebackdia / 2.0) * Math.sin(1.5707963267948966 + this.sbendang / 180.0 * 3.141592653589793 - this.ssupangcom - this.ssupang)) * this.factor));
                this.offgraphics.drawLine((int)((this.sstopshow - this.SSlen2 + this.sebackdia / 2.0 * Math.sin(this.ssupangcom - this.sslowang)) * this.factor), (int)(this.baseline / 2 - (this.sebackpointZ + this.sebackdia / 2.0 * Math.cos(this.ssupangcom - this.sslowang)) * this.factor), (int)((this.sstopshow - this.sradcenj + (this.sradius + this.sebackdia / 2.0) * Math.cos(1.5707963267948966 + this.sbendang / 180.0 * 3.141592653589793 - this.ssupangcom - this.ssupang)) * this.factor), (int)(this.baseline / 2 - (this.sradcenZ + (this.sradius + this.sebackdia / 2.0) * Math.sin(1.5707963267948966 + this.sbendang / 180.0 * 3.141592653589793 - this.ssupangcom - this.ssupang)) * this.factor));
                this.offgraphics.drawLine((int)((this.sstopshow - this.SSlen2 - this.sebackdia / 2.0 * Math.sin(this.ssupangcom - this.sslowang)) * this.factor), (int)(this.baseline / 2 - (-this.sebackpointZ + this.sebackdia / 2.0 * Math.cos(this.ssupangcom - this.sslowang)) * this.factor), (int)((this.sstopshow - this.sradcenj + (this.sradius - this.sebackdia / 2.0) * Math.cos(1.5707963267948966 + this.sbendang / 180.0 * 3.141592653589793 - this.ssupangcom - this.ssupang)) * this.factor), (int)(this.baseline / 2 - (-this.sradcenZ - (this.sradius - this.sebackdia / 2.0) * Math.sin(1.5707963267948966 + this.sbendang / 180.0 * 3.141592653589793 - this.ssupangcom - this.ssupang)) * this.factor));
                this.offgraphics.drawLine((int)((this.sstopshow - this.SSlen2 + this.sebackdia / 2.0 * Math.sin(this.ssupangcom - this.sslowang)) * this.factor), (int)(this.baseline / 2 - (-this.sebackpointZ - this.sebackdia / 2.0 * Math.cos(this.ssupangcom - this.sslowang)) * this.factor), (int)((this.sstopshow - this.sradcenj + (this.sradius + this.sebackdia / 2.0) * Math.cos(1.5707963267948966 + this.sbendang / 180.0 * 3.141592653589793 - this.ssupangcom - this.ssupang)) * this.factor), (int)(this.baseline / 2 - (-this.sradcenZ - (this.sradius + this.sebackdia / 2.0) * Math.sin(1.5707963267948966 + this.sbendang / 180.0 * 3.141592653589793 - this.ssupangcom - this.ssupang)) * this.factor));
                this.offgraphics.drawLine((int)((this.sstopshow - this.SSlen2 - this.sebackdia / 2.0 * Math.sin(this.ssupangcom - this.sslowang)) * this.factor), (int)(this.baseline / 2 - (this.sebackpointZ - this.sebackdia / 2.0 * Math.cos(this.ssupangcom - this.sslowang)) * this.factor), (int)((this.sstopshow - this.SSlen2 + this.sebackdia / 2.0 * Math.sin(this.ssupangcom - this.sslowang)) * this.factor), (int)(this.baseline / 2 - (this.sebackpointZ + this.sebackdia / 2.0 * Math.cos(this.ssupangcom - this.sslowang)) * this.factor));
                this.offgraphics.drawLine((int)((this.sstopshow - this.SSlen2 - this.sebackdia / 2.0 * Math.sin(this.ssupangcom - this.sslowang)) * this.factor), (int)(this.baseline / 2 - (-this.sebackpointZ + this.sebackdia / 2.0 * Math.cos(this.ssupangcom - this.sslowang)) * this.factor), (int)((this.sstopshow - this.SSlen2 + this.sebackdia / 2.0 * Math.sin(this.ssupangcom - this.sslowang)) * this.factor), (int)(this.baseline / 2 - (-this.sebackpointZ - this.sebackdia / 2.0 * Math.cos(this.ssupangcom - this.sslowang)) * this.factor));
                this.offgraphics.drawArc((int)((this.sstopshow - this.sradcenj - (this.sradius - this.sebackdia / 2.0)) * this.factor), (int)(this.baseline / 2 - (this.sradcenZ + (this.sradius - this.sebackdia / 2.0)) * this.factor), (int)((this.sradius * 2.0 - this.sebackdia) * this.factor), (int)((this.sradius * 2.0 - this.sebackdia) * this.factor), (int)(90.0 - (this.ssupangcom + this.ssupang) / 3.141592653589793 * 180.0), (int)this.sbendang);
                this.offgraphics.drawArc((int)((this.sstopshow - this.sradcenj - (this.sradius + this.sebackdia / 2.0)) * this.factor), (int)(this.baseline / 2 - (this.sradcenZ + (this.sradius + this.sebackdia / 2.0)) * this.factor), (int)((this.sradius * 2.0 + this.sebackdia) * this.factor), (int)((this.sradius * 2.0 + this.sebackdia) * this.factor), (int)(90.0 - (this.ssupangcom + this.ssupang) / 3.141592653589793 * 180.0), (int)this.sbendang);
                this.offgraphics.drawArc((int)((this.sstopshow - this.sradcenj - (this.sradius - this.sebackdia / 2.0)) * this.factor), (int)(this.baseline / 2 - (-this.sradcenZ + (this.sradius - this.sebackdia / 2.0)) * this.factor), (int)((this.sradius * 2.0 - this.sebackdia) * this.factor), (int)((this.sradius * 2.0 - this.sebackdia) * this.factor), (int)(-90.0 + (this.ssupangcom + this.ssupang) / 3.141592653589793 * 180.0), (int)(-this.sbendang));
                this.offgraphics.drawArc((int)((this.sstopshow - this.sradcenj - (this.sradius + this.sebackdia / 2.0)) * this.factor), (int)(this.baseline / 2 - (-this.sradcenZ + (this.sradius + this.sebackdia / 2.0)) * this.factor), (int)((this.sradius * 2.0 + this.sebackdia) * this.factor), (int)((this.sradius * 2.0 + this.sebackdia) * this.factor), (int)(-90.0 + (this.ssupangcom + this.ssupang) / 3.141592653589793 * 180.0), (int)(-this.sbendang));
            }
            else {
                if (-this.stdiameter / 2.0 < this.stayoffset - this.sebackdia / 2.0) {
                    this.offgraphics.drawLine((int)((this.sstopshow + this.stdiameter / 2.0 * Math.cos(this.stayang / 180.0 * 3.141592653589793) - this.stdiameter / 2.0 * Math.cos(this.ssupangcom + Math.asin((this.stayoffset - this.sebackdia / 2.0) / (this.stdiameter / 2.0)))) * this.factor), (int)(this.baseline / 2 - this.stdiameter / 2.0 * Math.sin(this.ssupangcom + Math.asin((this.stayoffset - this.sebackdia / 2.0) / (this.stdiameter / 2.0))) * this.factor), (int)((this.sstopshow - this.SSlen2 - this.sebackdia / 2.0 * Math.sin(this.ssupangcom)) * this.factor), (int)(this.baseline / 2 - (this.sebackpointZ - this.sebackdia / 2.0 * Math.cos(this.ssupangcom)) * this.factor));
                    this.offgraphics.drawLine((int)((this.sstopshow + this.stdiameter / 2.0 * Math.cos(this.stayang / 180.0 * 3.141592653589793) - this.stdiameter / 2.0 * Math.cos(this.ssupangcom + Math.asin((this.stayoffset - this.sebackdia / 2.0) / (this.stdiameter / 2.0)))) * this.factor), (int)(this.baseline / 2 - -this.stdiameter / 2.0 * Math.sin(this.ssupangcom + Math.asin((this.stayoffset - this.sebackdia / 2.0) / (this.stdiameter / 2.0))) * this.factor), (int)((this.sstopshow - this.SSlen2 - this.sebackdia / 2.0 * Math.sin(this.ssupangcom)) * this.factor), (int)(this.baseline / 2 - (-this.sebackpointZ + this.sebackdia / 2.0 * Math.cos(this.ssupangcom)) * this.factor));
                }
                else {
                    this.offgraphics.drawLine((int)((this.sstopshow + this.stdiameter / 2.0 * Math.cos(this.stayang / 180.0 * 3.141592653589793) - (this.stayoffset - this.sebackdia / 2.0) * Math.cos(this.ssupangcom + 1.5707963267948966)) * this.factor), (int)(this.baseline / 2 - (this.stayoffset - this.sebackdia / 2.0) * Math.sin(this.ssupangcom + 1.5707963267948966) * this.factor), (int)((this.sstopshow - this.SSlen2 - this.sebackdia / 2.0 * Math.sin(this.ssupangcom)) * this.factor), (int)(this.baseline / 2 - (this.sebackpointZ - this.sebackdia / 2.0 * Math.cos(this.ssupangcom)) * this.factor));
                    this.offgraphics.drawLine((int)((this.sstopshow + this.stdiameter / 2.0 * Math.cos(this.stayang / 180.0 * 3.141592653589793) - (this.stayoffset - this.sebackdia / 2.0) * Math.cos(this.ssupangcom + 1.5707963267948966)) * this.factor), (int)(this.baseline / 2 - -(this.stayoffset - this.sebackdia / 2.0) * Math.sin(this.ssupangcom + 1.5707963267948966) * this.factor), (int)((this.sstopshow - this.SSlen2 - this.sebackdia / 2.0 * Math.sin(this.ssupangcom)) * this.factor), (int)(this.baseline / 2 - (-this.sebackpointZ + this.sebackdia / 2.0 * Math.cos(this.ssupangcom)) * this.factor));
                }
                if (this.stdiameter / 2.0 > this.stayoffset + this.sebackdia / 2.0) {
                    this.offgraphics.drawLine((int)((this.sstopshow + this.stdiameter / 2.0 * Math.cos(this.stayang / 180.0 * 3.141592653589793) - this.stdiameter / 2.0 * Math.cos(this.ssupang + this.ssupangcom + Math.asin((this.stayoffset + this.sebackdia / 2.0) / (this.stdiameter / 2.0)))) * this.factor), (int)(this.baseline / 2 - this.stdiameter / 2.0 * Math.sin(this.ssupang + this.ssupangcom + Math.asin((this.stayoffset + this.sebackdia / 2.0) / (this.stdiameter / 2.0))) * this.factor), (int)((this.sstopshow - this.SSlen2 + this.sebackdia / 2.0 * Math.sin(this.ssupangcom)) * this.factor), (int)(this.baseline / 2 - (this.sebackpointZ + this.sebackdia / 2.0 * Math.cos(this.ssupangcom)) * this.factor));
                    this.offgraphics.drawLine((int)((this.sstopshow + this.stdiameter / 2.0 * Math.cos(this.stayang / 180.0 * 3.141592653589793) - this.stdiameter / 2.0 * Math.cos(this.ssupang + this.ssupangcom + Math.asin((this.stayoffset + this.sebackdia / 2.0) / (this.stdiameter / 2.0)))) * this.factor), (int)(this.baseline / 2 - -this.stdiameter / 2.0 * Math.sin(this.ssupang + this.ssupangcom + Math.asin((this.stayoffset + this.sebackdia / 2.0) / (this.stdiameter / 2.0))) * this.factor), (int)((this.sstopshow - this.SSlen2 + this.sebackdia / 2.0 * Math.sin(this.ssupangcom)) * this.factor), (int)(this.baseline / 2 - (-this.sebackpointZ - this.sebackdia / 2.0 * Math.cos(this.ssupangcom)) * this.factor));
                }
                else {
                    this.offgraphics.drawLine((int)((this.sstopshow + this.stdiameter / 2.0 * Math.cos(this.stayang / 180.0 * 3.141592653589793) - (this.stayoffset + this.sebackdia / 2.0) * Math.cos(this.ssupang + this.ssupangcom + 1.5707963267948966)) * this.factor), (int)(this.baseline / 2 - (this.stayoffset + this.sebackdia / 2.0) * Math.sin(this.ssupang + this.ssupangcom + 1.5707963267948966) * this.factor), (int)((this.sstopshow - this.SSlen2 + this.sebackdia / 2.0 * Math.sin(this.ssupangcom)) * this.factor), (int)(this.baseline / 2 - (this.sebackpointZ + this.sebackdia / 2.0 * Math.cos(this.ssupangcom)) * this.factor));
                    this.offgraphics.drawLine((int)((this.sstopshow + this.stdiameter / 2.0 * Math.cos(this.stayang / 180.0 * 3.141592653589793) - (this.stayoffset + this.sebackdia / 2.0) * Math.cos(this.ssupang + this.ssupangcom + 1.5707963267948966)) * this.factor), (int)(this.baseline / 2 - -(this.stayoffset + this.sebackdia / 2.0) * Math.sin(this.ssupang + this.ssupangcom + 1.5707963267948966) * this.factor), (int)((this.sstopshow - this.SSlen2 + this.sebackdia / 2.0 * Math.sin(this.ssupangcom)) * this.factor), (int)(this.baseline / 2 - (-this.sebackpointZ - this.sebackdia / 2.0 * Math.cos(this.ssupangcom)) * this.factor));
                }
                this.offgraphics.drawLine((int)((this.sstopshow - this.SSlen2 - this.sebackdia / 2.0 * Math.sin(this.ssupangcom)) * this.factor), (int)(this.baseline / 2 - (this.sebackpointZ - this.sebackdia / 2.0 * Math.cos(this.ssupangcom)) * this.factor), (int)((this.sstopshow - this.SSlen2 + this.sebackdia / 2.0 * Math.sin(this.ssupangcom)) * this.factor), (int)(this.baseline / 2 - (this.sebackpointZ + this.sebackdia / 2.0 * Math.cos(this.ssupangcom)) * this.factor));
                this.offgraphics.drawLine((int)((this.sstopshow - this.SSlen2 - this.sebackdia / 2.0 * Math.sin(this.ssupangcom)) * this.factor), (int)(this.baseline / 2 - (-this.sebackpointZ + this.sebackdia / 2.0 * Math.cos(this.ssupangcom)) * this.factor), (int)((this.sstopshow - this.SSlen2 + this.sebackdia / 2.0 * Math.sin(this.ssupangcom)) * this.factor), (int)(this.baseline / 2 - (-this.sebackpointZ - this.sebackdia / 2.0 * Math.cos(this.ssupangcom)) * this.factor));
            }
        }
        if (this.showwhat == 4) {
            this.factory = this.size().height / 55;
            this.factorx = this.size().width / 0.17;
            final int n = (int)this.headangle / 10 * 10;
            final double n2 = (int)((float)this.relfrontproj * 100.0f) / 100.0;
            double n3 = -6.0;
            this.offgraphics.setColor(Color.black);
            while (n3 < 7.0) {
                this.i = 0.0;
                while (this.i < 5.0) {
                    this.offgraphics.drawLine((int)((0.00917 * ((90.0 - (n + 30 - this.i * 10.0)) * Math.sin((n + 30 - this.i * 10.0) / 180.0 * 3.141592653589793) + 4.0 * n3) / Math.sin((n + 30 - this.i * 10.0) / 180.0 * 3.141592653589793) - n2 + 0.08) * this.factorx), (int)(this.factory * 10.0 * this.i), (int)((0.00917 * ((90.0 - (n + 20 - this.i * 10.0)) * Math.sin((n + 20 - this.i * 10.0) / 180.0 * 3.141592653589793) + 4.0 * n3) / Math.sin((n + 20 - this.i * 10.0) / 180.0 * 3.141592653589793) - n2 + 0.08) * this.factorx), (int)(this.factory * 10.0 * (this.i + 1.0)));
                    this.offgraphics.drawString("u = " + String.valueOf((int)n3), (int)((0.00917 * ((90.0 - (n + 25 - this.i * 10.0)) * Math.sin((n + 25 - this.i * 10.0) / 180.0 * 3.141592653589793) + 4.0 * n3) / Math.sin((n + 25 - this.i * 10.0) / 180.0 * 3.141592653589793) - n2 + 0.08) * this.factorx), (int)(this.factory * 10.0 * (this.i + 0.5)));
                    ++this.i;
                }
                n3 += 3.0;
            }
            this.offgraphics.setColor(this.mygray);
            this.offgraphics.drawRect((int)(0.02 * this.factorx), (int)((30.0 - this.headangle + n) * this.factory), (int)((this.relfrontproj - n2 + 0.06) * this.factorx), (int)((this.headangle - n + 20.0) * this.factory));
            this.offgraphics.setColor(Color.white);
            this.offgraphics.fillRect(0, 0, (int)(0.02 * this.factorx), (int)(55.0 * this.factory));
            this.offgraphics.fillRect((int)(0.14 * this.factorx), 0, (int)(0.032 * this.factorx), (int)(55.0 * this.factory));
            this.offgraphics.setColor(Color.green);
            this.offgraphics.fillOval((int)((0.0671429 - n2 + 0.08) * this.factorx - 3.0), (int)((-41 + n) * this.factory - 3.0), 6, 6);
            this.offgraphics.drawString("Typical mountain", (int)(0.141 * this.factorx), 20);
            this.offgraphics.setColor(Color.magenta);
            this.offgraphics.fillOval((int)((0.0709746 - n2 + 0.08) * this.factorx - 3.0), (int)((-43 + n) * this.factory - 3.0), 6, 6);
            this.offgraphics.drawString("Typical road", (int)(0.141 * this.factorx), 35);
            this.offgraphics.setColor(Color.orange);
            this.offgraphics.fillOval((int)((0.0691243 - n2 + 0.08) * this.factorx - 3.0), (int)((-38 + n) * this.factory - 3.0), 6, 6);
            this.offgraphics.drawString("Typical downhill", (int)(0.141 * this.factorx), 50);
            this.offgraphics.setColor(Color.pink);
            this.offgraphics.fillOval((int)((0.0828257 - n2 + 0.08) * this.factorx - 3.0), (int)((-41 + n) * this.factory - 3.0), 6, 6);
            this.offgraphics.drawString("Typical touring", (int)(0.141 * this.factorx), 65);
            this.offgraphics.setColor(Color.blue);
            this.offgraphics.drawString("u = Jones' stability", (int)(0.141 * this.factorx), 80);
            this.offgraphics.drawString("    criterion", (int)(0.141 * this.factorx), 95);
            this.offgraphics.drawString("Trail = " + String.valueOf((int)this.trailmm) + " mm", (int)(0.141 * this.factorx), 110);
            this.offgraphics.setColor(Color.black);
            this.offgraphics.drawRect((int)(0.02 * this.factorx), 0, (int)(0.12 * this.factorx), (int)(this.factory * 50.0));
            this.i = 1.0;
            while (this.i < 5.0) {
                this.offgraphics.drawLine((int)(0.02 * this.factorx), (int)(this.factory * 10.0 * this.i), (int)(0.02 * this.factorx + 6.0), (int)(this.factory * 10.0 * this.i));
                this.offgraphics.drawString(String.valueOf(String.valueOf(n + 30 - this.i * 10.0)) + "°", (int)(0.01 * this.factorx), (int)(this.factory * 10.0 * this.i + 5.0));
                ++this.i;
            }
            this.offgraphics.drawString("Head", 1, (int)(this.factory * 25.0 - 7.0));
            this.offgraphics.drawString("Angle", 0, (int)(this.factory * 25.0 + 7.0));
            this.i = 0.0;
            while (this.i < 5.0) {
                this.offgraphics.drawLine((int)((0.04 + this.i * 0.02) * this.factorx), (int)(this.factory * 50.0), (int)((0.04 + this.i * 0.02) * this.factorx), (int)(this.factory * 50.0 - 6.0));
                this.offgraphics.drawString(String.valueOf((float)(n2 + 0.04 - this.i * 0.02)), (int)((0.12 - this.i * 0.02) * this.factorx - 10.0), (int)(this.factory * 55.0 - 12.0));
                ++this.i;
            }
            this.offgraphics.drawString("Relative front projection", (int)(this.factorx * 0.07), (int)(this.factory * 55.0));
            this.offgraphics.setColor(Color.red);
            this.offgraphics.fillOval((int)((this.relfrontproj - n2 + 0.08) * this.factorx - 3.0), (int)((30.0 - this.headangle + n) * this.factory - 3.0), 6, 6);
        }
        graphics.drawImage(this.offscreen, 0, 0, this);
    }
    
    public void destroy() {
        this.offgraphics.dispose();
    }
    
    Santeria() {
        this.showwhat = 1;
        this.d = this.size();
        this.chainx = 20.0;
        this.chainy = -2.0;
        this.chainz = 6.0;
        this.seatx = 10.0;
        this.seaty = 15.0;
        this.seatz = 6.0;
        this.sebackdia = 12.0;
        this.chbackdia = 13.0;
        this.dropoutspace = 135.0;
        this.BBlen = 68.0;
        this.stayang = 45.0;
        this.staypos = 40.0;
        this.frontwd = 660.0;
        this.rearwd = 660.0;
        this.rearww = 55.8;
        this.frontww = 55.8;
        this.headangle = 71.0;
        this.seatangle = 73.0;
        this.BBd = 38.1;
        this.BBh = 298.0;
        this.stlength = 482.0;
        this.htlength = 111.0;
        this.htlowext = 10.0;
        this.htupext = 8.0;
        this.CSlen = 425.0;
        this.FCdist = 637.0;
        this.rakelen = 42.3;
        this.forklen = 443.0;
        this.ftravel = 80.0;
        this.sag = 16.0;
        this.lowerstack = 11.0;
        this.stdiameter = 28.6;
        this.ttdiameter = 28.6;
        this.dtdiameter = 34.9;
        this.htdiameter = 36.4;
        this.chhdia = 20.8;
        this.chvdia = 29.7;
        this.cradius = 50.0;
        this.chtaper = 220.0;
        this.chpos = 15.0;
        this.stextension = 40.0;
        this.sbendang = 10.0;
        this.cbendang = 12.0;
        this.sstoplen = 135.0;
        this.csfrontlen = 70.0;
        this.sradius = 50.0;
        this.sbendy = true;
        this.cbendy = true;
        this.mygray = new Color(200, 200, 200);
    }
}

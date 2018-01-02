// 
// Decompiled by Procyon v0.5.30
// 

package com.mindprod.submitter;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.net.URISyntaxException;
import java.net.URI;
import java.io.IOException;
import java.net.URLConnection;
import java.net.HttpURLConnection;
import java.nio.charset.Charset;
import com.mindprod.http.Http;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.Writer;
import java.io.BufferedWriter;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.FileOutputStream;
import java.util.Comparator;
import java.util.Arrays;
import com.mindprod.http.Post;
import com.mindprod.http.Get;
import java.net.MalformedURLException;
import java.net.URL;

enum SubmissionSite
{
    ONETWOTHREEFREESOFT("123FreeSoft", "http://www.123freesoft.com/submit.php", "/checkadd.php") {
        String submit(final String pad) {
            return this.submitViaPost("padurl", pad, "psubmit", "Submit PAD File");
        }
    }, 
    FOURSOFTWARE2DOWNLOAD("4Software2Download", "http://www.4software2download.com/submit_pad_file.php", "/submit_pad_file") {
        String submit(final String pad) {
            return this.submitViaPost("XMLURL", "", "CmdBtn", "Submit PAD");
        }
    }, 
    ABABA_SOFT("AbabaSoft", "http://www.ababasoft.com/catalog/submit.php", "checkadd.php") {
        String submit(final String pad) {
            return this.submitViaPost("padurl", pad, "psubmit", "Submit PAD File");
        }
    }, 
    ABCDATOS("ABCDatos", "http://www.abcdatos.com/programas/padform.html", "/cgi-bin/pad.cgi") {
        String submit(final String pad) {
            return this.submitViaGet("padurl", pad);
        }
    }, 
    ABDOWNLOADS("ABDownloads", "http://www.ab-downloads.com/english/Submit/", "/english/Submit/submit.htm") {
        String submit(final String pad) {
            return this.submitViaPost("padfile", pad);
        }
    }, 
    ABOUTVIDEOCONVERTER("AboutVideoConverter", " http://www.aboutvideoconverter.com", "/submit-pad") {
        String submit(final String pad) {
            return this.submitViaPost("padfile", pad);
        }
    }, 
    ABSOLUTELY_FREE_SHAREWARE("AbsolutelyFreeSoftware", "http://freeware.hs-lab.com.ua/submit.php", "/checkadd.php") {
        String submit(final String pad) {
            return this.submitViaPost("padurl", pad, "psubmit", "Submit PAD File");
        }
    }, 
    ABSOLUTE_SHAREWARE("AbsoluteShareware", "http://absoluteshareware.com/submit.php", "/submitcategory.php") {
        String submit(final String pad) {
            return this.submitViaPost("padurl", pad, "RegNow", "", "ShareIt", "", "eSellerate", "", "Emetrix", "", "RegSoft", "", "SWREG", "", "ClixGalore", "", "DigitalCandle", "", "Kagi", "", "LinkShare", "", "Shareasale", "", "Qwerks", "", "psubmit", "Submit PAD File");
        }
    }, 
    ACID_FILES("AcidFiles", "http://www.acidfiles.com/submit.html", "/dynamic/submit_pad") {
        String submit(final String pad) {
            return this.submitViaPost("pad_url", pad, "redir_url", "/submit.html");
        }
    }, 
    ACTIVEMERGE("ActiveMerge", "http://www.activemerge.com/submit/", "/checkadd") {
        String submit(final String pad) {
            return this.submitViaPost("padurl", pad, "psubmit", "Submit PAD File");
        }
    }, 
    AF_DOWN("AfDown", "http://afdown.com/submit-software.html", "/submit-software-action.php") {
        String submit(final String pad) {
            return this.submitViaPost("padurl", pad, "regnow", "");
        }
    }, 
    AIV_SOFT("AivSoft", "http://www.aivsoft.com/submit.php", "/checkadd.php") {
        String submit(final String pad) {
            return this.submitViaPost("padurl", pad, "psubmit", "Submit PAD File");
        }
    }, 
    ALPHA_DOWNLOADS("AlphaDownloads", "http://alphadownloads.com/submit-home.html", "/submit.cgi") {
        String submit(final String pad) {
            return this.submitViaPost("pad_url", pad);
        }
    }, 
    AMAZING_DOWNLOADS("AmazingDownloads", "http://www.amazingdownloads.com/submit/", "/submit/") {
        String submit(final String pad) {
            return this.submitViaPost("padurl", pad, "psubmit", "Submit PAD File");
        }
    }, 
    AMAZING_FILES("AmazingFiles", "http://amazingfiles.com", "/checkadd.php") {
        String submit(final String pad) {
            return this.submitViaPost("padurl", pad, "psubmit", "Submit PAD File");
        }
    }, 
    ANDRA("Andra", "http://andra.uv.ro/submit.php", "/checkadd") {
        String submit(final String pad) {
            return this.submitViaPost("padurl", pad, "psubmit", "Submit PAD File");
        }
    }, 
    ANEWDOWNLOAD("ANewDownload", "http://anewdownload.com/submit/form/", "/submit/form/") {
        String submit(final String pad) {
            return this.submitViaPost("act", "submit_do", "pad", pad, "Submit", "Submit");
        }
    }, 
    APBSPOTNNET84("ApbspotNnet84", "http://apbspot.net84.net/submit.php", "/checkadd") {
        String submit(final String pad) {
            return this.submitViaPost("padurl", pad, "psubmit", "Submit PAD File");
        }
    }, 
    APP_DOWN("AppDown", "http://appdown.com/submit.php", "/checkadd.php") {
        String submit(final String pad) {
            return this.submitViaPost("padurl", pad, "regNowID", "", "shareItID", "", "psubmit", "Submit PAD File");
        }
    }, 
    APPLETVCONVERTER("AppleTVConverter", "http://www.appletvconverter.net/submission.html", "/submission-thanks") {
        String submit(final String pad) {
            return this.submitViaPost("padurl", pad, "psubmit", "");
        }
    }, 
    ASFCONVERTER("AsfConverter", "http://www.asf-converter.net/submission.html", "/submission-thanks") {
        String submit(final String pad) {
            return this.submitViaPost("padurl", pad, "psubmit", "");
        }
    }, 
    ASKEDFILES("AskedFiles", "http://www.askedfiles.com/submit.php", "/checkadd") {
        String submit(final String pad) {
            return this.submitViaPost("padurl", pad, "psubmit", "Submit PAD File");
        }
    }, 
    AUGESOFT("Augesoft", "http://www.augesoft.com/submit.php", "/addpad") {
        String submit(final String pad) {
            return this.submitViaPost("padurl", pad, "productid", "", "psubmit", "Submit PAD File");
        }
    }, 
    B3HOSTINGS("B3Hostings", "http://www.b3hostings.com/projects/shareware_directory//submit.php", "/projects/shareware_directory//checkadd") {
        String submit(final String pad) {
            return this.submitViaPost("padurl", pad, "psubmit", "Submit PAD File");
        }
    }, 
    BEST_FREEWARE_DOWNLOAD("BestFreewareDownload", "http://www.bestfreewaredownload.com/submit-pad-file.html", "/submit-pad-file.html") {
        String submit(final String pad) {
            return this.submitViaGet("padurl", pad);
        }
    }, 
    BEST_SECURITY_TIPS("BestSecurityTips", "http://www.bestsecuritytips.com/wfdownloads+submit.htm", "/modules/wfdownloads/submit.php") {
        String submit(final String pad) {
            return this.submitViaPost("title", "", "url", pad, "mirror", "http://", "homepagetitle", "", "homepage", "http://", "version", "", "publisher", "", "size", "0", "platform", "", "license", "", "limitations", "", "price", "0", "MAX_FILE_SIZE", "0", "screenshot", "", "notifypub", "1", "submit", "Submit", "lid", "0", "regnow_vendor_id", "", "regnow_product_id", "", "cid", "pid");
        }
    }, 
    BESTSOFTWARE4DOWNLOAD("BestSoftware4Download", "http://www.bestsoftware4download.com/submit-pad-file.html", "/submit-pad-file.html") {
        String submit(final String pad) {
            return this.submitViaGet("padurl", pad, "regnow", "");
        }
    }, 
    BESTVISTADOWNLOADS("BestVistaDownloads", "http://www.bestvistadownloads.com/submit-pad-file.html", "/submit-pad-file.html") {
        String submit(final String pad) {
            return this.submitViaGet("padurl", pad, "regnow", "");
        }
    }, 
    BGSOFT("BgSoft", "http://bg-soft.net/product/submit/", "/product/Submit") {
        String submit(final String pad) {
            return this.submitViaGet("PadUrl", pad);
        }
    }, 
    BOBSOFT("BobSoft", "http://soft.bobsoft.com/submit.php", "/submit.php") {
        String submit(final String pad) {
            return this.submitViaPost("PadUrl", pad, "Submit", "Update");
        }
    }, 
    BUYALLSOFT("BuyAllSoft", "http://www.buyallsoft.com/submit.html", "/submit") {
        String submit(final String pad) {
            return this.submitViaPost("post", "1", "notes", pad);
        }
    }, 
    BYTEFLOW("ByteFlow", "http://byte-flow.com/submit.html", "/dynamic/submit_pad") {
        String submit(final String pad) {
            return this.submitViaPost("pad_url", pad, "redir_url", "/submit.html");
        }
    }, 
    CRITICALFILES("CriticalFiles", "http://criticalfiles.com/submit.html", "/dynamic/submit_pad") {
        String submit(final String pad) {
            return this.submitViaPost("pad_url", pad, "redir_url", "/submit.html");
        }
    }, 
    CYBPERMETHEXIS("CyberMethexis", "http://cybermethexis.org/submit-software", "/submit-software") {
        String submit(final String pad) {
            return this.submitViaPost("submit", "1", "padfilename", pad, "submiPadFile", "SubmitHTTP");
        }
    }, 
    CYHNET("CyhNet", "http://www.cyhnet.com/submit.php", "/submit.php") {
        String submit(final String pad) {
            return this.submitViaGet("padurl", pad, "psubmit", "Submit PAD File");
        }
    }, 
    DESKTOPSOFTWARE("DesktopSoftware", "http://desktopsoftware.info/submit.php", "/checkadd") {
        String submit(final String pad) {
            return this.submitViaPost("padurl", pad, "psubmit", "Submit PAD File");
        }
    }, 
    DIGIMODES("Digimodes", "http://digimodes.com/submit.php", "/submit.php") {
        String submit(final String pad) {
            return this.submitViaPost("padurl", pad, "psubmit", "Submit PAD File");
        }
    }, 
    DISCOVERES("Discoveres", "http://discoveres.com/submitres", "/submit") {
        String submit(final String pad) {
            return this.submitViaPost("padurl", pad, "submittype", "software", "submit", "Submit", "regnowid", "");
        }
    }, 
    DIVX("DivX", "http://www.divx.ws/submit-pad/", "/submit-pad/") {
        String submit(final String pad) {
            return this.submitViaPost("padfile", pad);
        }
    }, 
    DIVXCONVERTER("DivXConverter", "http://www.divx-converter.net/submission.html", "/submission-thanks") {
        String submit(final String pad) {
            return this.submitViaPost("padurl", pad, "padcat", "-", "psubmit", "Submit PAD File >>>");
        }
    }, 
    DLDADDY("DLDaddy", "http://www.affiliate-referrals.net/submit.php", "/checkadd.php") {
        String submit(final String pad) {
            return this.submitViaPost("padurl", pad, "psubmit", "Submit PAD File");
        }
    }, 
    DLTUBE("dlTube", "http://www.dltube.net/submitsoft.php", "/submitsoft.php") {
        String submit(final String pad) {
            return this.submitViaPost("url", pad, "submit", "Submit");
        }
    }, 
    DOWNBROAD("DownBroad", "http://www.downbroad.com/submit-software", "/com/soft/admin_ex/index.php") {
        String submit(final String pad) {
            return this.submitViaPost("dz", "import_pad", "id", "", "url", pad);
        }
    }, 
    DOWNLOAD11("Download11", "http://download11.com/submit/", "/submit/submit.php") {
        String submit(final String pad) {
            return this.submitViaGet("url", "");
        }
    }, 
    DOWNLOAD_3K("Download3K", "http://www.download3k.com/submit.php", "/submit.php") {
        String submit(final String pad) {
            return this.submitViaPost("url", pad, "id_regnow", "");
        }
    }, 
    DOWNLOAD4A("Download4a", "http://www.download4a.com/submit.php", "/submit.php") {
        String submit(final String pad) {
            return this.submitViaPost("padurl", pad);
        }
    }, 
    DOWNLOAD4SURE("Download4Sure", "http://www.download4sure.com/submit-software", "/submit-software") {
        String submit(final String pad) {
            return this.submitViaPost("padfile", pad);
        }
    }, 
    DOWNLOAD_4_YOU("Download4You", "http://download4you.com/php/add.php", "/php/add_software.php") {
        String submit(final String pad) {
            return this.submitViaPost("LOAD_PAD_URL", pad, "action", "loaded", "submit", "Load PAD file!");
        }
    }, 
    DOWNLOAD5000("Download5000", "http://download5000.com/AddPAD.aspx", "/AddPAD.aspx") {
        String submit(final String pad) {
            return this.submitViaPost("template$m_PADURL", pad, "template$m_AddButton", "Add Program", "m_U0", "pzO0oO", "botcheck", "22");
        }
    }, 
    DOWNLOAD_ARSIVI("DownloadArsivi", "http://www.downloadarsivi.com/asp/engprogram_ekle.asp", "/asp/ENGprogram_ekle3.asp") {
        String submit(final String pad) {
            return this.submitViaPost("altkategoriname", "Bir kategori se\u00e7iniz", "padurl", pad, "regnow", "", "shareit", "", "doit", "Submit");
        }
    }, 
    DOWNLOAD_BY("DownloadBy", "http://www.download-by.net/submit.html", "/checkadd.html") {
        String submit(final String pad) {
            return this.submitViaPost("padurl", pad, "psubmit", "Submit PAD File");
        }
    }, 
    DOWNLOAD_CHOICE("DownloadChoice", "http://www.downloadchoice.com/submit/index.php", "/submit/process.php") {
        String submit(final String pad) {
            return this.submitViaPost("padurl", pad, "regnow", "", "psubmit", "Submit PAD File");
        }
    }, 
    DOWNLOADDIR("DownloadDir", "http://www.downloaddir.com/submit/", "/submit/") {
        String submit(final String pad) {
            return this.submitViaPost("padurl", pad, "button", "Submit", "process", "Y", "MM_insert", "submit");
        }
    }, 
    DOWNLOADERY("Downloadery", "http://www.downloadery.com/submit.php", "/submitcategory.php") {
        String submit(final String pad) {
            return this.submitViaPost("padurl", pad, "RegNow", "", "ShareIt", "", "eSellerate", "", "Emetrix", "", "RegSoft", "", "SWREG", "", "ClixGalore", "", "DigitalCandle", "", "Kagi", "", "LinkShare", "", "Shareasale", "", "Qwerks", "", "psubmit", "Submit PAD File");
        }
    }, 
    DOWNLOAD_FRENZY("DownloadFrenzy", "http://downloadfrenzy.com/addpad.aspx", "/AddPAD.aspx") {
        String submit(final String pad) {
            return this.submitViaPost("template$m_PADURL", pad, "template$m_AddButton", "Add Program", "m_U0", "pzO0oO", "botcheck", "22");
        }
    }, 
    DOWNLOAD_IT_NOW("DownloadItNow", "http://www.download-it-now.net/submit.php", "/checkadd.php") {
        String submit(final String pad) {
            return this.submitViaPost("padurl", pad, "backlink", "http://", "submit", " Submit PAD File ");
        }
    }, 
    DOWNLOADKALEIDOSCOPE("DownloadKaleidoscope", "http://dnka.com/submit.php", "/submit.php") {
        String submit(final String pad) {
            return this.submitViaGet("padurl", pad, "psubmit", "Submit PAD File >>>");
        }
    }, 
    DOWNLOADNEW("DownloadNew", "http://downloadnew.biz/submit/form/", "/submit/form/") {
        String submit(final String pad) {
            return this.submitViaPost("act", "submit_do", "pad", pad, "Submit", "Submit");
        }
    }, 
    DOWNLOAD_O_MANIAC("DownloadoManiac", "http://download.omani.ac/submit.php", "/submit.php") {
        String submit(final String pad) {
            return this.submitViaPost("pad", pad, "form", "submitting");
        }
    }, 
    DOWNLOADPILE("DownloadPile", "http://download-pile.info/submit-pad/", "/submit-pad/") {
        String submit(final String pad) {
            return this.submitViaPost("padfile", pad);
        }
    }, 
    DOWNLOADPOCKET("DownloadPocket", "http://downloadpocket.com/submit-software/", "/submit-software/") {
        String submit(final String pad) {
            return this.submitViaPost("URL", pad, "submit_pad", "Prefill");
        }
    }, 
    DOWNLOAD_READY("DownloadReady", "http://www.downloadready.com/submit.php", "/checkadd.php") {
        String submit(final String pad) {
            return this.submitViaPost("padurl", pad, "psubmit", "Submit");
        }
    }, 
    DOWNLOADRY("Downloadry", "http://downloadry.com/submit.php", "/submitcategory.php") {
        String submit(final String pad) {
            return this.submitViaPost("padurl", pad, "psubmit", "Submit PAD File");
        }
    }, 
    DOWNLOADS2("Downloads2", "http://www.downloads2.com/submit.php", "/checkadd.php") {
        String submit(final String pad) {
            return this.submitViaPost("padurl", pad, "psubmit", "PAD File Submit");
        }
    }, 
    DOWNLOADSAREA("DownloadsArea", "http://www.downloadsarea.com/Submit.html", "/Submit.html") {
        String submit(final String pad) {
            return this.submitViaPost("padurl", pad);
        }
    }, 
    DOWNLOAD_SOFTWARE_SEARCH("DownloadSoftwareSearch", "http://www.download-software-search.com/submit.html", "/cgi-sys/formmail.pl") {
        String submit(final String pad) {
            return this.submitViaPost("subject", "SM PAD Submission", "redirect", "/submit-thank-you.html", "required", "pad-url", "pad-url", pad);
        }
    }, 
    DOWNLOAD_SPIN("DownloadSpin", "http://www.downloadspin.com/submit.php", "/checkadd.php") {
        String submit(final String pad) {
            return this.submitViaPost("padurl", pad, "psubmit", "Submit PAD File");
        }
    }, 
    DOWNLOAD_STATION("DownloadStation", "http://downloadstation.net/pad/submit.php", "/pad/checkadd.php") {
        String submit(final String pad) {
            return this.submitViaPost("padurl", pad, "psubmit", "Submit PAD File");
        }
    }, 
    DOWNLOADTYPHOON("DownloadTyphoon", "http://www.downloadtyphoon.com/submit-pad-file", "/submit-pad-file.html") {
        String submit(final String pad) {
            return this.submitViaGet("padurl", pad, "regnow", "");
        }
    }, 
    DOWNLOADUP("DownloadUp", "http://download-up.com/submit.php", "/submit.php") {
        String submit(final String pad) {
            return this.submitViaGet("urlpad", pad);
        }
    }, 
    DOWNLOADYOURSOFTWARE("DownloadYourSoftware", "http://www.download-your-software.com/submit.php", "/checkadd.php") {
        String submit(final String pad) {
            return this.submitViaPost("padurl", pad, "psubmit", "Submit PAD File");
        }
    }, 
    DOWNLOADZNOW("DownloadZNow", "http://www.downloadznow.com/submit-pad", "/submit-pad") {
        String submit(final String pad) {
            return this.submitViaGet("padurl", pad);
        }
    }, 
    EASYFILEDOWNLOADS("EasyFileDownloads", "http://www.easyfiledownloads.com/submit-soft.html", "/submit-soft.html") {
        String submit(final String pad) {
            return this.submitViaPost("pad_file", pad);
        }
    }, 
    EFRESHWARE("EFreshware", "http://www.e-freshware.com/submit-software/pad-submission/", "/submit-software/pad-submission/") {
        String submit(final String pad) {
            return this.submitViaPost("url", pad, "regnow_id", "", "op", "sendpadsubmission", "efw694849ad5f8bec7b8bd66ed59bec17d6", "1");
        }
    }, 
    ESPANOLSOFTWARE("EspanolSoftware", "http://espanol-software.info/tools.php/submit_pad", "/tools.php/submit") {
        String submit(final String pad) {
            return this.submitViaPost("action", "post", "pad_url", pad);
        }
    }, 
    EURO_DOWNLOAD("EuroDownload", "http://www.eurodownload.com/pad_submit.php", "/pad_check.php") {
        String submit(final String pad) {
            return this.submitViaPost("padurl", pad, "regnow", "", "sellshareware", "", "esellerate", "", "shareit", "", "bmtmicro", "", "plimus", "", "psubmit", "Submit PAD File");
        }
    }, 
    EZYSOFT("EzySoft", "http://www.ezy-soft.com/submit.php", "/checkadd.php") {
        String submit(final String pad) {
            return this.submitViaPost("padurl", pad, "psubmit", "Submit PAD File");
        }
    }, 
    FASTSHAREWARE("FastShareware", "http://www.fastshareware.com/submit.php", "/addpad.php") {
        String submit(final String pad) {
            return this.submitViaPost("padurl", pad, "psubmit", "Submit PAD File");
        }
    }, 
    FILE_CLUSTER("FileCluster", "http://www.filecluster.com/submit-software.html", "/submit-software.html") {
        String submit(final String pad) {
            return this.submitViaPost("padurl", pad, "action", "ADD");
        }
    }, 
    FILE_DOMAIN("FileDomain", "http://www.filedomain.com/submit.php", "/checkadd.php") {
        String submit(final String pad) {
            return this.submitViaPost("padurl", pad, "psubmit", "Submit PAD File");
        }
    }, 
    FILEEDGE("FileEdge", "http://www.fileedge.com/submit.html", "/dynamic/submit_pad") {
        String submit(final String pad) {
            return this.submitViaPost("pad_url", pad, "redir_url", "/submit.html");
        }
    }, 
    FILEPICKS("FilePicks", "http://www.filepicks.com/submit.php", "/checkadd.php") {
        String submit(final String pad) {
            return this.submitViaPost("padurl", pad, "psubmit", "Submit PAD File");
        }
    }, 
    FILEPROFILE("FileProfile", "http://www.fileprofile.com/submit.htm", "/submitdone.php") {
        String submit(final String pad) {
            return this.submitViaGet("xmlpath", pad);
        }
    }, 
    FILES_GUARD("FilesGuard", "http://filesguard.com/submit.php", "/checkadd.php") {
        String submit(final String pad) {
            return this.submitViaPost("padurl", pad, "psubmit", "Submit PAD File");
        }
    }, 
    FINDBESTSOFT("FindBestSoft", "http://www.findbestsoft.com/submit.php", "/checkadd.php") {
        String submit(final String pad) {
            return this.submitViaPost("regnow", "", "shareit", "", "padurl", pad, "psubmit", "Submit PAD File");
        }
    }, 
    FINDSOFT("FindSoftNet", "http://www.findsoft.net/Submit.html", "/") {
        String submit(final String pad) {
            return this.submitViaPost("padurl", pad, "psubmit", "Submit");
        }
    }, 
    FREE_FILE_SEEK("FreeFileSeek", "http://freefileseek.com/submit.php", "/checkadd.php") {
        String submit(final String pad) {
            return this.submitViaPost("padurl", pad, "regnow", "", "psubmit", "Submit PAD File");
        }
    }, 
    FREE_SOFTWARE_PROGRAMS("FreeSoftwarePrograms", "http://www.free-software-programs.com/submit.html", "/cgi-sys/formmail.pl") {
        String submit(final String pad) {
            return this.submitViaPost("recipient", "submit@software-matrix.com", "subject", "SM PAD Submission", "redirect", "/submit-thank-you.html", "email", "fsp@software-matrix.com", "required", "pad-url", "pad-url", pad);
        }
    }, 
    FREE_SOFTWARE_SHAREWARE_DOWNLOADS("FreeSoftwareSharewareDownloads", "http://free-software-shareware-downloads.com/submit.php", "/checkadd.php") {
        String submit(final String pad) {
            return this.submitViaPost("padurl", pad, "psubmit", "Submit PAD File");
        }
    }, 
    FREEWARE1("Freeware1", "http://www.freeware1.com/submit.php", "/checkadd.php") {
        String submit(final String pad) {
            return this.submitViaPost("padurl", pad, "psubmit", "Submit PAD File");
        }
    }, 
    FREEWARE_ARCHIV("FreewareArchiv", "http://www.freeware-archiv.de/melden", "/padsupport.php") {
        String submit(final String pad) {
            return this.submitViaPost("q", pad);
        }
    }, 
    FREEWARESOFT("FreewareSoft", "http://freeware-soft.info/submit-pad/", "/") {
        String submit(final String pad) {
            return this.submitViaPost("padfile", pad);
        }
    }, 
    FREEWARESOFTWARELINKS("FreewareSoftwareLinks", "http://www.freewaresoftwarelinks.com/submit.php", "/submitpad") {
        String submit(final String pad) {
            return this.submitViaPost("URL", pad);
        }
    }, 
    FREEWARETOWN("FreewareTown", "http://freewaretown.com/padupload.aspx", "/padupload.aspx") {
        String submit(final String pad) {
            return this.submitViaPost("__VIEWSTATE", "dDwxMDA4ODg4NDk1Ozs+Jq3KttZFbstStWn0RgEjkVn11cY=", "TextBox1", pad, "Button1", "Submit Pad");
        }
    }, 
    GEEEZ("Geeez", "http://geeez.com/software/submit.php", "/software/checkadd.php") {
        String submit(final String pad) {
            return this.submitViaPost("padurl", pad, "psubmit", "Submit PAD File");
        }
    }, 
    GENERALFREEWARE("GeneralFreeware", "http://generalfreeware.com/submit.php", "/submitcategory.php") {
        String submit(final String pad) {
            return this.submitViaPost("padurl", pad, "psubmit", "Submit PAD File");
        }
    }, 
    GETALLSOFT("GetAllSoft", "http://getallsoft.com/submit.php", "/checkadd.php") {
        String submit(final String pad) {
            return this.submitViaPost("padurl", pad, "psubmit", "Submit PAD File");
        }
    }, 
    GETSHAREWAREFORFREE("GetSharewareForFree", "http://getsharewareforfree.com/tools.php/submit_pad", "/tools.php/submit") {
        String submit(final String pad) {
            return this.submitViaPost("action", "post", "pad_url", pad, "pad_file", "", "regnow_id", "", "regsoft_id", "");
        }
    }, 
    HAMSOFTWARE("HamSoftware", "http://www.ham-software.com/submit.php", "/checkadd.php") {
        String submit(final String pad) {
            return this.submitViaPost("padurl", pad, "psubmit", "Submit PAD File");
        }
    }, 
    IFREEWAREDOWNLOAD("iFreewareDownload", "http://i-freeware-download.com/SubmitNew.aspx", "/SubmitNew.aspx") {
        String submit(final String pad) {
            return this.submitViaPost("ctl00$ContentPlaceHolder2$tbPADUrl", pad, "ctl00$ContentPlaceHolder2$btnSubmit", "Submit");
        }
    }, 
    IMFREEWARE("ImFreeware", "http://www.imfreeware.com/submitres", "/submitres") {
        String submit(final String pad) {
            return this.submitViaPost("padurl", pad, "submittype", "software", "submit", "Submit");
        }
    }, 
    MAYZER("Mayzer", "http://mayzer.com/submit.php", "/checkadd.php") {
        String submit(final String pad) {
            return this.submitViaPost("padurl", pad, "psubmit", "Submit PAD File");
        }
    }, 
    METADOWNLOADS("MetaDownloads", "http://meta-downloads.info/submit-pad/", "/") {
        String submit(final String pad) {
            return this.submitViaPost("padurl", pad);
        }
    }, 
    MVBBB("Mvbbb", "http://www.mvbbb.com/submit-software.html", "/submit-software-action.php") {
        String submit(final String pad) {
            return this.submitViaPost("padurl", pad, "regnow", "");
        }
    }, 
    MYSHAREWARES("MySharewares", "http://pad.mysharewares.com/submit.php", "/insertpad.php") {
        String submit(final String pad) {
            return this.submitViaPost("padurl", pad, "psubmit", "Submit PAD File");
        }
    }, 
    MYSOFTWARELIST("MySoftwareList", "http://www.mysoftwarelist.com/submit-1/", "/submit.php") {
        String submit(final String pad) {
            return this.submitViaPost("pad", pad);
        }
    }, 
    NEWDOWNLOAD("NewDownload", "http://www.newdownload.com/authors.php", "/authors.php") {
        String submit(final String pad) {
            return this.submitViaPost("action", "check", "padurl", pad);
        }
    }, 
    NEWSOFT4YOU("NewSoft4You", "http://www.newsoft4you.com/", "/product/Submit") {
        String submit(final String pad) {
            return this.submitViaPost("padurl", pad, "X-Requested-With", "XMLHttpRequest");
        }
    }, 
    OBTAINSOFT("ObtainSoft", "http://www.obtainsoft.com/submit.php", "/addpad.php") {
        String submit(final String pad) {
            return this.submitViaPost("padurl", pad, "psubmit", "Submit PAD File");
        }
    }, 
    OZYSOFTWARE("OzySoftware", "http://ozysoftware.com/submit-PAD-files/PADsubmit.php", "/submit-PAD-files/checkadd.php") {
        String submit(final String pad) {
            return this.submitViaPost("padurl", pad, "psubmit", "Submit PAD File");
        }
    }, 
    PADFM("PadFM", "http://padfm.com/padfm/user/submit.php", "/padfm/user/submit.php") {
        String submit(final String pad) {
            return this.submitViaGet("monitored", "set", "padlocation", pad);
        }
    }, 
    PADREPOSITORY("PadRepository", "http://www.padrepository.com/SubmitPAD.htm", "/SubmitPAD.htm") {
        String submit(final String pad) {
            final Post post = new Post();
            post.setParms(new String[] { "action", "submit" });
            post.setPostParms("padURL", pad, "siteTC", "1", "Submit", "Submit");
            final URL actionURL = this.getActionURL();
            final String result = post.send(actionURL.getHost(), -1, actionURL.getPath(), Post.UTF8Charset);
            SubmissionSite.responseCode = post.getResponseCode();
            SubmissionSite.responseMessage = post.getResponseMessage();
            return result;
        }
    }, 
    PADRING("PadRing", "http://padring.com/submit.html", "/cgi-bin/submit.cgi") {
        String submit(final String pad) {
            return this.submitViaGet("pad", pad);
        }
    }, 
    PAULSPICKS("PaulsPicks", "http://paulspicks.com/submit-software.aspx", "/submit-software.aspx") {
        String submit(final String pad) {
            return this.submitViaPost("ctl00$ctl00$body$body$txtPADLocation", pad, "ctl00$ctl00$body$body$giveawayPromotionInterested", "2");
        }
    }, 
    PC24HOURS("PC24Hours", "http://www.pc24hours.net/submit.php", "/checkadd.php") {
        String submit(final String pad) {
            return this.submitViaPost("padurl", pad, "picklist", "", "featured", "0", "days", "9", "psubmit", "Submit PAD File");
        }
    }, 
    PEACHSEED("PeachSeed", "http://peachseed.com/submit.html", "/approve.html") {
        String submit(final String pad) {
            return this.submitViaPost("padurl", pad, "psubmit", "I AGREE WITH THE SUBMISSION POLICY");
        }
    }, 
    PETERBURGESS("PeterBurgess", "http://www.peterburgess.net/software/submit.php", "/software/checkadd.php") {
        String submit(final String pad) {
            return this.submitViaPost("padurl", pad, "psubmit", "Submit PAD File");
        }
    }, 
    PLUSPRO("PlusPro", "http://www.pluspro.net/prosoft/submit.php", "/prosoft/checkadd.php") {
        String submit(final String pad) {
            return this.submitViaPost("padurl", pad, "psubmit", "Submit PAD File to ProSoft ShareSpot");
        }
    }, 
    POCKETPCSOFTWAREDOWNLOADS("PocketPCSoftwareDownloads", "http://www.pocketpc-software-downloads.com/submit-pad-file.html", "/submit-pad-file.html") {
        String submit(final String pad) {
            return this.submitViaGet("padurl", pad, "regnow", "");
        }
    }, 
    PROGRAMMERSHEAVEN("ProgrammersHeaven", "http://www.programmersheaven.com/submit/pad/PADSubmission.aspx", "/app/submission/PADSubmission.aspx") {
        String submit(final String pad) {
            return this.submitViaGet("PADURL", pad);
        }
    }, 
    QDYU("Qdyu", "http://www.qdyu.com/submit.php", "/checkadd.php") {
        String submit(final String pad) {
            return this.submitViaPost("padurl", pad, "psubmit", "Submit PAD File");
        }
    }, 
    RESOURCEDB("ResourceDB", "http://resourcedb.com/submit.php", "/add.php") {
        String submit(final String pad) {
            return this.submitViaPost("type", "Software", "so", pad, "review", "true", "catsubmit", "Submit");
        }
    }, 
    RESOURCEFILL("ResourceFill", "http://www.resourcefill.com/submit.php", "/checkadd.php") {
        String submit(final String pad) {
            return this.submitViaPost("padurl", pad, "linkbackurl", "", "psubmit", "Submit PAD File");
        }
    }, 
    RETAILERDEALS("RetailerDeals", "http://retailerdeals.com/software/submit.php", "/software/checkadd.php") {
        String submit(final String pad) {
            return this.submitViaPost("padurl", pad, "psubmit", "Submit PAD File");
        }
    }, 
    REVIEWWORLD("ReviewWorld", "http://www.review-world.com/submit.html", "/submit.html") {
        String submit(final String pad) {
            return this.submitViaPost("post", "1", "notes", pad);
        }
    }, 
    RUN2("Run2", "http://downloads.run2.ws/submit.php", "/checkadd.php") {
        String submit(final String pad) {
            return this.submitViaPost("padurl", pad, "psubmit", "Submit PAD File");
        }
    }, 
    SEARCHALLSOFT("SearchAllSoft", "http://www.searchallsoft.com/submit.html", "/submit.html") {
        String submit(final String pad) {
            return this.submitViaPost("post", "1", "notes", pad);
        }
    }, 
    SEARCHSOMESOFT("SearchSomeSoft", "http://www.searchsomesoft.com/submit.html", "/submit.html") {
        String submit(final String pad) {
            return this.submitViaPost("post", "1", "notes", pad);
        }
    }, 
    SHARE32("Share32", "http://share32.com/submit.php", "/submit.php") {
        String submit(final String pad) {
            return this.submitViaPost("padurl", pad, "submit", "Submit PAD address");
        }
    }, 
    SHAREAPPLE("ShareApple", "http://shareapple.com/submit.php", "/submit.php") {
        String submit(final String pad) {
            return this.submitViaPost("ValidateURL", pad);
        }
    }, 
    SHARESOFTWARE24("ShareSoftware24", "http://www.sharesoftware24.com/submit-software.asp", "/submit-software.asp") {
        String submit(final String pad) {
            return this.submitViaPost("action", "submit", "url", pad, "regnow_id", "", "shareit_id", "", "plimus_id", "", "esellerate_id", "", "submit", "Submit Software");
        }
    }, 
    SHAREWAREBAY("SharewareBay", "http://sharewarebay.com/utils/docs/pad_submit.php", "/utils/docs/pad_submit.php") {
        String submit(final String pad) {
            return this.submitViaPost("pad_file", pad, "MAX_FILE_SIZE", "102400", "regnow_pid", "", "regnow_vid", "");
        }
    }, 
    SHAREWARECHEAP("SharewareCheap", "http://www.sharewarecheap.com/submit.html", "/submitok.php") {
        String submit(final String pad) {
            return this.submitViaPost("PADurl", pad, "sbbtn", "Submit");
        }
    }, 
    SHAREWAREKING("SharewareKing", "http://sharewareking.com/listings/submitpadurl.php", "/listings/padurlforward.php") {
        String submit(final String pad) {
            return this.submitViaPost("PADFILE", pad);
        }
    }, 
    SHAREWAREVILLE("Sharewareville", "http://sharewareville.com/submit.php", "/submit_confirm.php") {
        String submit(final String pad) {
            return this.submitViaPost("padurl", pad, "psubmit", "Submit PAD File");
        }
    }, 
    SHOPLAGOM("ShopLagom", "http://www.shoplagom.com/submit-pad.php", "/submit-pad.php") {
        String submit(final String pad) {
            return this.submitViaPost("padurl", pad, "submit", "Submit", "MM_insert", "padfile");
        }
    }, 
    SMALLFREEWARE("SmallFreeware", "http://www.smallfreeware.com/submit.php", "/submitcategory.php") {
        String submit(final String pad) {
            return this.submitViaPost("padurl", pad, "psubmit", "Submit PAD File");
        }
    }, 
    SOFT321("Soft321", "http://www.soft321.com/submit.php", "/checkadd.php") {
        String submit(final String pad) {
            return this.submitViaPost("padurl", pad, "linkbackurl", "", "psubmit", "Submit PAD File");
        }
    }, 
    SOFT4SALE("Soft4Sale", "http://www.soft4sale.com/index.php?act=submit", "/submit.php") {
        String submit(final String pad) {
            return this.submitViaPost("padurl", pad, "psubmit", "Submit PAD File");
        }
    }, 
    SOFTALLWARE("SoftAllWare", "http://www.soft-all-ware.com/submit.html", "/submit.html") {
        String submit(final String pad) {
            return this.submitViaPost("post", "1", "notes", pad);
        }
    }, 
    SOFTCAB("SoftCab", "http://softcab.net/submit.html", "/cgi-bin/submit.cgi") {
        String submit(final String pad) {
            return this.submitViaGet("pad", pad);
        }
    }, 
    SOFTDB("SoftDB", "http://soft-db.com/addsoft/", "/addsoft/") {
        String submit(final String pad) {
            return this.submitViaPost("pad_url", pad, "reset", "Reset form", "submit", "Submit");
        }
    }, 
    SOFTLISTDE("SoftListDe", "http://de.softlist.ws/addsoft/", "/addsoft/") {
        String submit(final String pad) {
            return this.submitViaPost("pad_url", pad, "sid", "-1", "submit", "Submit");
        }
    }, 
    SOFTLISTRU("SoftListRu", "http://ru.softlist.ws/addsoft/", "/addsoft/") {
        String submit(final String pad) {
            return this.submitViaPost("pad_url", pad, "sid", "-1", "submit", "Submit");
        }
    }, 
    SOFTLISTWS("SoftListWs", "http://softlist.ws/addsoft/", "/addsoft/") {
        String submit(final String pad) {
            return this.submitViaPost("pad_url", pad, "sid", "-1", "submit", "Submit");
        }
    }, 
    SOFTLOOKUP("SoftLookup", "http://softlookup.com/submit.asp", "/checkadd.php") {
        String submit(final String pad) {
            return this.submitViaPost("url", pad, "psubmit", "Submit PAD File");
        }
    }, 
    SOFTLOW("Softlow", "http://www.softlow.com/submit.html", "/submit.html") {
        String submit(final String pad) {
            return this.submitViaPost("post", "1", "notes", pad);
        }
    }, 
    SOFTMOBILE("SoftMobile", "http://www.soft-mobile.com/submit.html", "/submit.html") {
        String submit(final String pad) {
            return this.submitViaPost("post", "1", "notes", pad);
        }
    }, 
    SOFTWARECROWN("SoftwareCrown", "http://www.softwarecrown.com/submit.php", "/submitcategory.php") {
        String submit(final String pad) {
            return this.submitViaPost("padurl", pad, "RegNow", "", "ShareIt", "", "eSellerate", "", "Emetrix", "", "RegSoft", "", "SWREG", "", "ClixGalore", "", "DigitalCandle", "", "Kagi", "", "LinkShare", "", "Shareasale", "", "Qwerks", "", "psubmit", "Submit PAD File");
        }
    }, 
    SOFTWAREHORIZON("SoftwareHorizon", "http://www.softwarehorizon.com/submit.php", "/checkadd.php") {
        String submit(final String pad) {
            return this.submitViaPost("padurl", pad, "psubmit", "Submit PAD File");
        }
    }, 
    SOFTWAREKB("SoftwareKB", "http://www.softwarekb.com/submit547.php", "/checkadd.php") {
        String submit(final String pad) {
            return this.submitViaPost("padurl8652", pad, "psubmit", "Submit PAD File");
        }
    }, 
    SOFTWARELOCATOR("SoftwareLocator", "http://www.software-locator.com/submit.html", "/cgi-sys/formmail.pl") {
        String submit(final String pad) {
            return this.submitViaPost("subject", "SM PAD Submission", "redirect", "http://www.software-locator.com/submit-thank-you.html", "required", "pad-url", "pad-url", pad);
        }
    }, 
    SOFTWARELODE("SoftwareLode", "http://www.softwarelode.com/submitpadfile.php", "/addpad.php") {
        String submit(final String pad) {
            return this.submitViaPost("padurl", pad, "backlink", "http://", "submit", " Submit PAD File ");
        }
    }, 
    SOFTWAREMATRIX("SoftwareMatrix", "http://www.software-matrix.com/submit.html", "/cgi-sys/formmail.pl") {
        String submit(final String pad) {
            return this.submitViaPost("subject", "SM PAD Submission", "redirect", "/submit-thank-you.html", "required", "pad-url", "pad-url", pad);
        }
    }, 
    SOFTWARESIZZLE("SoftwareSizzle", "http://softwaresizzle.com/submit.php", "/checkadd.php") {
        String submit(final String pad) {
            return this.submitViaPost("padurl", pad, "regnow", "", "psubmit", "Submit PAD File");
        }
    }, 
    SOFTWAREWAGON("SoftwareWagon", "http://softwarewagon.com/submit-software.php", "/submit-software.php") {
        String submit(final String pad) {
            return this.submitViaPost("submit_soft", "1", "hlc", "", "pad_url", pad, "regnow_id", "");
        }
    }, 
    SUPERDOWNLOADS("SuperDownloads", "http://www.superdownloads.com.br/dev/inenglish.cfm", "/info/inenglish.cfm") {
        String submit(final String pad) {
            return this.submitViaPost("padurls", pad, "go", "1", "submitx", "Submit your PADs!");
        }
    }, 
    SYSTEMUTILS("SystemUtils", "http://www.systemutils.net/add/", "/add/") {
        String submit(final String pad) {
            return this.submitViaPost("url", pad, "regnowid", "");
        }
    }, 
    TELECHARGER("Telecharger", "http://telecharger.for.free.fr/soumission_logiciel_shareware_freeware.php", "/checkadd.php") {
        String submit(final String pad) {
            return this.submitViaPost("padurl", pad, "regnow", "", "psubmit", "Soumettre le fichier PAD");
        }
    }, 
    TOP4DOWNLOAD("Top4Download", "http://www.top4download.com/submit-pad-file.html", "/submit-pad-file.html") {
        String submit(final String pad) {
            return this.submitViaGet("padurl", pad, "regnow", "");
        }
    }, 
    TOPSHAREWAREDOWNLOADS("TopSharewareDownloads", "http://topsharewaredownloads.com/submit.php", "/checkadd.php") {
        String submit(final String pad) {
            return this.submitViaPost("padurl", pad, "psubmit", "Submit PAD File", "id", "1");
        }
    }, 
    TRIALWARE("Trialware", "http://trialware.biz/submit.php", "/checkadd.php") {
        String submit(final String pad) {
            return this.submitViaPost("padurl", pad, "regnow", "", "psubmit", "Submit PAD File");
        }
    }, 
    TRINITYFILES("TrinityFiles", "http://www.trinityfiles.com/submit.php", "/checkadd.php") {
        String submit(final String pad) {
            return this.submitViaGet("padurl", pad, "psubmit", "Submit PAD File");
        }
    }, 
    TRYINGBUYING("TryingBuying", "http://tryingbuying.com/submit.php", "/submitcategory.php") {
        String submit(final String pad) {
            return this.submitViaPost("padurl", pad, "RegNow", "", "ShareIt", "", "eSellerate", "", "Emetrix", "", "RegSoft", "", "SWREG", "", "ClixGalore", "", "DigitalCandle", "", "Kagi", "", "LinkShare", "", "Shareasale", "", "Qwerks", "", "psubmit", "Submit PAD File");
        }
    }, 
    TWOBROTHERS("TwoBrothers", "http://shareware.twobrotherssoftware.com/submit.php", "/checkadd.php") {
        String submit(final String pad) {
            return this.submitViaPost("padurl", pad, "psubmit", "Submit PAD File");
        }
    }, 
    UNIQUEIDEA("UniqueIdea", "http://www.uniqueidea.net/download/submit.asp", "/download/submit.php") {
        String submit(final String pad) {
            return this.submitViaPost("padUrl", pad, "B1", "Submit", "B2", "Reset");
        }
    }, 
    VADINO("Vadino", "http://www.vadino.com/submit.html", "/submit.html") {
        String submit(final String pad) {
            return this.submitViaPost("post", "1", "notes", pad);
        }
    }, 
    VIDEOSOFTWAREDIRECT("VideoSoftwareDirect", "http://videosoftwaredirect.com/submit.php", "/checkadd.php") {
        String submit(final String pad) {
            return this.submitViaPost("padurl", pad, "psubmit", "Submit PAD File");
        }
    }, 
    VONNA("Vonna", "http://download.vonna.com/submit.php", "/checkadd.php") {
        String submit(final String pad) {
            return this.submitViaPost("padurl", pad, "psubmit", "Submit PAD File");
        }
    }, 
    WARE23("Ware23", "http://ware23.com/submit.php", "/index.php") {
        String submit(final String pad) {
            final Post post = new Post();
            post.setParms(new String[] { "d", "submit", "p", "padurl" });
            post.setPostParms("p2", pad, "Submit", "");
            final URL actionURL = this.getActionURL();
            final String result = post.send(actionURL.getHost(), -1, actionURL.getPath(), Post.UTF8Charset);
            SubmissionSite.responseCode = post.getResponseCode();
            SubmissionSite.responseMessage = post.getResponseMessage();
            return result;
        }
    }, 
    WEBZF("Webzf", "http://www.webzf.com/padsite/submit-pad/", "/padsite/submit-pad/") {
        String submit(final String pad) {
            return this.submitViaPost("padfile", pad, "submit", "Submit pad");
        }
    }, 
    WESTDOWNLOAD("WestDownload", "http://westdownload.com/submit.html", "/checkadd.php") {
        String submit(final String pad) {
            return this.submitViaPost("padurl", pad, "psubmit", "Submit PAD File");
        }
    }, 
    WINCOLORS("WinColors", "http://www.wincolors.com/submit.php", "/checkadd.php") {
        String submit(final String pad) {
            return this.submitViaPost("padurl", pad, "psubmit", "Submit PAD File");
        }
    }, 
    WINDFILE("WindFile", "http://www.windfile.com/submit.html", "/dynamic/submit_pad") {
        String submit(final String pad) {
            return this.submitViaPost("pad_url", pad, "force_cat_id", "< Autodetect >", "redir_url", "/submit.html");
        }
    }, 
    WINDOWS7DOWNLOAD("Windows7Download", "http://www.windows7download.com/submit-pad-file.html", "/submit-pad-file.html") {
        String submit(final String pad) {
            return this.submitViaGet("padurl", pad, "regnow", "");
        }
    }, 
    WORLD_SOFTWARE_ARCHIVE("WorldSoftwareArchive", "http://www.world-software-archive.com/submit.html", "/submit.html") {
        String submit(final String pad) {
            return this.submitViaPost("post", "1", "notes", pad);
        }
    }, 
    YANKEEDOWNLOAD("YankeeDownload", "http://www.yankeedownload.com/pad_submit.php", "/pad_check.php") {
        String submit(final String pad) {
            return this.submitViaPost("padurl", pad, "regnow", "", "shareit", "", "sellshareware", "", "esellerate", "", "bmtmicro", "", "plimus", "", "psubmit", "Submit PAD File");
        }
    }, 
    ZDOWN("ZDown", "http://www.z-down.com/submit-softwares.php", "/checkadd.php") {
        String submit(final String pad) {
            return this.submitViaPost("padurl", pad, "regnow", "", "psubmit", "Submit PAD File");
        }
    }, 
    ZOOMLOAD("ZoomLoad", "http://www.zoomload.com/submit.php", "/checkadd.php") {
        String submit(final String pad) {
            return this.submitViaPost("padurl", pad, "psubmit", "Submit PAD File");
        }
    };
    
    private static final boolean DEBUGGING = false;
    private static int responseCode;
    private static String responseMessage;
    private final String action;
    private final String name;
    private final URL manualSubmissionURL;
    
    public static int getResponseCode() {
        return SubmissionSite.responseCode;
    }
    
    public static String getResponseMessage() {
        return SubmissionSite.responseMessage;
    }
    
    private SubmissionSite(final String name, final String manualSubmissionURL, final String action) {
        this.name = name;
        try {
            this.manualSubmissionURL = new URL(manualSubmissionURL);
        }
        catch (MalformedURLException e) {
            throw new IllegalArgumentException("Program bug: Invalid manualSubmissionURL: " + manualSubmissionURL);
        }
        this.action = action;
    }
    
    String getName() {
        return this.name;
    }
    
    URL getActionURL() {
        try {
            return new URL(this.manualSubmissionURL, this.action);
        }
        catch (MalformedURLException e) {
            throw new IllegalArgumentException("Program bug: Invalid site action URL " + this.toString());
        }
    }
    
    URL getBaseURL() {
        try {
            final String dummyURL = new URL(this.manualSubmissionURL, "dummy.txt").toString();
            return new URL(dummyURL.substring(0, dummyURL.length() - "dummy.txt".length()));
        }
        catch (MalformedURLException e) {
            throw new IllegalArgumentException("Program bug: Invalid site base " + this.toString());
        }
    }
    
    abstract String submit(final String p0);
    
    String submitViaGet(final String... parms) {
        final Get get = new Get();
        get.setParms(parms);
        final URL actionURL = this.getActionURL();
        final String result = get.send(actionURL.getHost(), -1, actionURL.getPath(), Get.UTF8Charset);
        SubmissionSite.responseCode = get.getResponseCode();
        SubmissionSite.responseMessage = get.getResponseMessage();
        return result;
    }
    
    String submitViaPost(final String... postParms) {
        final Post post = new Post();
        post.setPostParms(postParms);
        final URL actionURL = this.getActionURL();
        final String result = post.send(actionURL.getHost(), -1, actionURL.getPath(), Post.UTF8Charset);
        SubmissionSite.responseCode = post.getResponseCode();
        SubmissionSite.responseMessage = post.getResponseMessage();
        return result;
    }
    
    public static void main(final String[] args) {
        int j = 0;
        final String[] names = new String[values().length];
        for (final SubmissionSite submissionSite : values()) {
            names[j++] = submissionSite.name;
        }
        String prev = null;
        for (final String name : names) {
            if (prev != null && name.compareToIgnoreCase(prev) <= 0) {
                System.err.println("sites out of order near " + name);
            }
            prev = name;
        }
        System.out.println("numbered site names alpha order >>>>");
        Arrays.sort(names, String.CASE_INSENSITIVE_ORDER);
        int i = 1;
        for (final String name2 : names) {
            System.out.printf("%3d. %s%n", i++, name2);
        }
        try {
            final FileOutputStream fos = new FileOutputStream("E:/com/mindprod/submitter/allsites.list", false);
            final OutputStreamWriter osw = new OutputStreamWriter(fos);
            final BufferedWriter bw = new BufferedWriter(osw, 4096);
            final PrintWriter prw = new PrintWriter(bw, false);
            for (final String name3 : names) {
                prw.println(name3);
            }
            prw.close();
        }
        catch (FileNotFoundException e) {
            System.err.println("unable to create allsites.list");
        }
    }
}

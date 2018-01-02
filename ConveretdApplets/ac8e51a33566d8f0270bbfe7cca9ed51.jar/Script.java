import java.io.InputStream;
import java.io.IOException;
import java.io.DataInputStream;
import java.util.Date;
import java.net.MalformedURLException;
import java.net.URL;

// 
// Decompiled by Procyon v0.5.30
// 

public class Script
{
    linkList list;
    linkList ptr;
    linkList start;
    int ok;
    String scrpt;
    URL documentURL;
    
    public Script(final URL documentURL, final String scrpt) {
        this.scrpt = scrpt;
        this.documentURL = documentURL;
        if (this.initScript() == -1) {
            this.ok = -1;
            return;
        }
        this.ok = 1;
    }
    
    String getParam(final String s, final String s2) {
        final int index = s.indexOf(s2);
        final int index2 = s.indexOf("text");
        if (index2 != -1 && index > index2) {
            return null;
        }
        if (index == -1) {
            return null;
        }
        final String substring = s.substring(index);
        int index3 = substring.indexOf("=");
        if (index3 == -1) {
            System.out.println("Error in '" + s2 + "' parameter in " + s);
            return null;
        }
        ++index3;
        String s3;
        if (s2.compareTo("text") == 0) {
            s3 = substring.substring(index3);
        }
        else {
            s3 = substring.substring(index3);
            if (s3.indexOf(" ") != -1) {
                s3 = s3.substring(0, s3.indexOf(" "));
            }
        }
        s3.trim();
        return s3;
    }
    
    FuncInfo getFunc(String trim) {
        final FuncInfo funcInfo = new FuncInfo();
        funcInfo.func = -1;
        funcInfo.delay = 40;
        funcInfo.startspace = 10;
        funcInfo.endspace = 20;
        funcInfo.times = -1;
        funcInfo.remaining = 0;
        funcInfo.centered = false;
        funcInfo.color = new String("");
        funcInfo.text = new String("No text specified");
        funcInfo.url = null;
        funcInfo.ret = null;
        trim = trim.trim();
        final String param = this.getParam(trim, "delay");
        if (param != null) {
            funcInfo.delay = new Integer(param);
        }
        final String param2 = this.getParam(trim, "clear");
        if (param2 != null && param2.compareTo("true") == 0) {
            funcInfo.centered = true;
            funcInfo.text = new String("");
        }
        else {
            final String param3 = this.getParam(trim, "center");
            if (param3 != null && param3.compareTo("true") == 0) {
                funcInfo.centered = true;
            }
            else {
                funcInfo.centered = false;
                final String param4 = this.getParam(trim, "startspace");
                if (param4 != null) {
                    funcInfo.startspace = new Integer(param4);
                }
                final String param5 = this.getParam(trim, "endspace");
                if (param5 != null) {
                    funcInfo.endspace = new Integer(param5);
                }
            }
            final String param6 = this.getParam(trim, "text");
            if (param6 != null) {
                funcInfo.text = param6;
            }
        }
        final String param7 = this.getParam(trim, "times");
        if (param7 != null) {
            funcInfo.times = new Integer(param7);
            funcInfo.remaining = funcInfo.times;
        }
        final String param8 = this.getParam(trim, "pixels");
        if (param8 != null) {
            funcInfo.times = new Integer(param8);
            funcInfo.remaining = funcInfo.times;
        }
        String s = this.getParam(trim, "URL");
        if (s != null) {
            if (s.indexOf(44) != -1) {
                funcInfo.target = s.substring(s.indexOf(44) + 1);
                s = s.substring(0, s.indexOf(44));
            }
            else {
                funcInfo.target = new String("");
            }
            try {
                funcInfo.url = new URL(s);
            }
            catch (MalformedURLException ex) {
                System.out.println("Bad URL: " + s);
                funcInfo.url = null;
            }
        }
        else {
            funcInfo.url = null;
        }
        final int index = trim.indexOf(" ");
        String substring;
        if (index != -1) {
            substring = trim.substring(0, index);
        }
        else {
            substring = trim;
        }
        if (substring.compareTo("Appear") == 0) {
            funcInfo.func = 0;
        }
        else if (substring.compareTo("Sleep") == 0) {
            funcInfo.func = 1;
        }
        else if (substring.compareTo("ScrollLeft") == 0) {
            funcInfo.func = 2;
        }
        else if (substring.compareTo("ScrollRight") == 0) {
            funcInfo.func = 3;
        }
        else if (substring.compareTo("ScrollUp") == 0) {
            funcInfo.func = 4;
        }
        else if (substring.compareTo("ScrollDown") == 0) {
            funcInfo.func = 5;
        }
        else if (substring.compareTo("Pixel") == 0) {
            funcInfo.func = 6;
            if (funcInfo.delay < 1) {
                funcInfo.delay = 1;
            }
            if (funcInfo.times < 1) {
                funcInfo.times = 15;
            }
        }
        else if (substring.compareTo("Blink") == 0) {
            funcInfo.func = 7;
            if (funcInfo.times < 1) {
                funcInfo.times = 2;
            }
        }
        else if (substring.compareTo("OverRight") == 0) {
            funcInfo.func = 8;
        }
        else if (substring.compareTo("ScrollCenter") == 0) {
            funcInfo.func = 9;
        }
        else if (substring.compareTo("OverCenter") == 0) {
            funcInfo.func = 10;
        }
        else if (substring.compareTo("OverLeft") == 0) {
            funcInfo.func = 11;
        }
        else if (substring.compareTo("OverUp") == 0) {
            funcInfo.func = 12;
        }
        else if (substring.compareTo("OverDown") == 0) {
            funcInfo.func = 13;
        }
        else if (substring.compareTo("Do") == 0) {
            funcInfo.func = 97;
        }
        else if (substring.compareTo("Repeat") == 0) {
            funcInfo.func = 98;
        }
        else if (substring.compareTo("Reload") == 0) {
            funcInfo.func = 99;
        }
        funcInfo.store = funcInfo.text;
        return funcInfo;
    }
    
    FuncInfo nextFunc() {
        FuncInfo funcInfo = this.ptr.fi;
        this.ptr = this.ptr.next;
        switch (funcInfo.func) {
            case 97: {
                funcInfo = this.nextFunc();
                break;
            }
            case 98: {
                if (funcInfo.times < 0) {
                    this.ptr = funcInfo.ret;
                    funcInfo = this.nextFunc();
                    break;
                }
                final FuncInfo funcInfo2 = funcInfo;
                --funcInfo2.remaining;
                if (funcInfo.remaining <= 0) {
                    funcInfo.remaining = funcInfo.times;
                    funcInfo = this.nextFunc();
                    break;
                }
                this.ptr = funcInfo.ret;
                funcInfo = this.nextFunc();
                break;
            }
            case 99: {
                if (this.initScript() == -1) {
                    funcInfo = null;
                    break;
                }
                funcInfo = this.nextFunc();
                break;
            }
        }
        return funcInfo;
    }
    
    boolean isColor(final char c) {
        return c == 'r' || c == 'g' || c == 'b' || c == 'y' || c == 'o' || c == 'p' || c == 'w' || c == 'c';
    }
    
    String getVar(final String s, final int n) {
        String s2;
        if (s.charAt(n) == '{') {
            final String substring = s.substring(n + 1);
            s2 = substring.substring(0, substring.indexOf(125));
        }
        else {
            s2 = String.valueOf(s.charAt(n));
        }
        return s2;
    }
    
    FuncInfo parseLine(final FuncInfo funcInfo) {
        final String[] array = { "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sept", "Oct", "Nov", "Dec" };
        final String[] array2 = { "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December" };
        final String[] array3 = { "Sun", "Mon", "Tues", "Wed", "Thur", "Fri", "Sat" };
        final String[] array4 = { "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday" };
        final Date date = new Date();
        String text = funcInfo.store;
        funcInfo.color = "";
        if (funcInfo.func == 0 || (funcInfo.func >= 2 && funcInfo.func <= 97)) {
            char char1 = 'r';
            int i = 0;
            while (i < text.length()) {
                if (text.charAt(i) == '\\') {
                    ++i;
                    String s;
                    if (text.charAt(i) == '{') {
                        final String substring = text.substring(i + 1);
                        text = text.substring(0, i - 1).concat(substring.substring(substring.indexOf(125) + 1));
                        s = substring.substring(0, substring.indexOf(125));
                        --i;
                    }
                    else {
                        s = text.substring(i, i + 1);
                        text = text.substring(0, i - 1).concat(text.substring(i + 1));
                        --i;
                    }
                    if (s.length() == 1 && this.isColor(s.charAt(0))) {
                        char1 = s.charAt(0);
                    }
                    else if (s.compareTo("tt") == 0) {
                        final boolean b = date.getHours() >= 12;
                        String s2;
                        if (b) {
                            if (date.getHours() == 12) {
                                s2 = String.valueOf(12);
                            }
                            else {
                                s2 = String.valueOf(date.getHours() - 12);
                            }
                        }
                        else {
                            final int hours = date.getHours();
                            if (hours == 0) {
                                s2 = String.valueOf(12);
                            }
                            else {
                                s2 = String.valueOf(hours);
                            }
                        }
                        final String concat = s2.concat(":");
                        final int minutes = date.getMinutes();
                        String s3;
                        if (minutes >= 10) {
                            s3 = concat.concat(String.valueOf(minutes));
                        }
                        else {
                            s3 = concat.concat("0").concat(String.valueOf(minutes));
                        }
                        String s4;
                        if (b) {
                            s4 = s3.concat(" pm");
                        }
                        else {
                            s4 = s3.concat(" am");
                        }
                        text = text.substring(0, i).concat(s4).concat(text.substring(i));
                        i += s4.length();
                        for (int j = 0; j < s4.length(); ++j) {
                            funcInfo.color = funcInfo.color.concat(new Character(char1).toString());
                        }
                    }
                    else if (s.compareTo("dd") == 0 || s.compareTo("DD") == 0) {
                        String s5;
                        if (s.compareTo("dd") == 0) {
                            s5 = array3[date.getDay()];
                        }
                        else {
                            s5 = array4[date.getDay()];
                        }
                        for (int k = 0; k < s5.length(); ++k) {
                            funcInfo.color = funcInfo.color.concat(new Character(char1).toString());
                        }
                        text = text.substring(0, i).concat(s5).concat(text.substring(i));
                        i += s5.length();
                    }
                    else if (s.compareTo("dn") == 0) {
                        final String value = String.valueOf(date.getDate());
                        for (int l = 0; l < value.length(); ++l) {
                            funcInfo.color = funcInfo.color.concat(new Character(char1).toString());
                        }
                        text = text.substring(0, i).concat(value).concat(text.substring(i));
                        i += value.length();
                    }
                    else if (s.compareTo("mm") == 0 || s.compareTo("MM") == 0) {
                        String s6;
                        if (s.compareTo("mm") == 0) {
                            s6 = array[date.getMonth()];
                        }
                        else {
                            s6 = array2[date.getMonth()];
                        }
                        for (int n = 0; n < s6.length(); ++n) {
                            funcInfo.color = funcInfo.color.concat(new Character(char1).toString());
                        }
                        text = text.substring(0, i).concat(s6).concat(text.substring(i));
                        i += s6.length();
                    }
                    else if (s.compareTo("mn") == 0) {
                        final String value2 = String.valueOf(date.getMonth() + 1);
                        for (int n2 = 0; n2 < value2.length(); ++n2) {
                            funcInfo.color = funcInfo.color.concat(new Character(char1).toString());
                        }
                        text = text.substring(0, i).concat(value2).concat(text.substring(i));
                        i += value2.length();
                    }
                    else if (s.compareTo("yy") == 0 || s.compareTo("YY") == 0) {
                        String s7;
                        if (s.compareTo("YY") == 0) {
                            s7 = String.valueOf(date.getYear() + 1900);
                        }
                        else {
                            s7 = String.valueOf(date.getYear() % 100);
                        }
                        for (int n3 = 0; n3 < s7.length(); ++n3) {
                            funcInfo.color = funcInfo.color.concat(new Character(char1).toString());
                        }
                        text = text.substring(0, i).concat(s7).concat(text.substring(i));
                        i += s7.length();
                    }
                    else if (s.compareTo("\\") == 0) {
                        text = text.substring(0, i).concat(text.substring(i + 1));
                        --i;
                    }
                    else {
                        System.out.println("Backslash (\\) error in text line: " + funcInfo.store);
                    }
                }
                else {
                    ++i;
                    funcInfo.color = funcInfo.color.concat(new Character(char1).toString());
                }
            }
        }
        funcInfo.text = text;
        return funcInfo;
    }
    
    int initScript() {
        InputStream openStream;
        DataInputStream dataInputStream;
        try {
            openStream = new URL(this.documentURL, this.scrpt).openStream();
            dataInputStream = new DataInputStream(openStream);
        }
        catch (IOException ex) {
            ex.printStackTrace();
            return -1;
        }
        try {
            this.list = new linkList();
            this.start = this.list;
            this.ptr = this.list;
            int n = 0;
            int n2 = 0;
            String line;
            while ((line = dataInputStream.readLine()) != null) {
                final String trim = line.trim();
                if (!trim.startsWith("!!") && trim.length() != 0) {
                    ++n;
                    this.ptr.fi = this.getFunc(trim);
                    if (this.ptr.fi.func == 97) {
                        ++n2;
                    }
                    this.ptr.next = new linkList();
                    this.ptr = this.ptr.next;
                }
            }
            this.ptr = this.start;
            final linkList[] array = new linkList[n2];
            int n3 = 0;
            for (int i = 0; i < n; ++i) {
                if (this.ptr.fi.func == 97) {
                    array[n3] = new linkList();
                    array[n3] = this.ptr;
                    ++n3;
                }
                else if (this.ptr.fi.func == 98) {
                    if (n3 > 0) {
                        --n3;
                        this.ptr.fi.ret = array[n3];
                    }
                    else {
                        System.out.println("Repeat error in line : Repeat times=" + this.ptr.fi.times);
                        System.out.println("     Mismatched Do/Repeats?");
                    }
                }
                this.ptr = this.ptr.next;
            }
            this.ptr = this.start;
            openStream.close();
            dataInputStream.close();
        }
        catch (IOException ex2) {
            return -1;
        }
        return 1;
    }
}

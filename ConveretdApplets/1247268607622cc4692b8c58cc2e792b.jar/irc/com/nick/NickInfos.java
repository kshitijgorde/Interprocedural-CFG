// 
// Decompiled by Procyon v0.5.30
// 

package irc.com.nick;

import irc.managers.avatar;
import java.awt.Graphics2D;
import java.awt.image.ImageObserver;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.util.Enumeration;
import java.util.StringTokenizer;
import irc.managers.Resources;
import javax.swing.ImageIcon;
import java.awt.Image;
import irc.EIRC;
import java.awt.Color;
import java.util.Vector;
import java.util.Hashtable;

public class NickInfos
{
    public static Hashtable users;
    public static Hashtable locations;
    public static Hashtable regions;
    public static Hashtable codedepfr;
    public static Hashtable smallAvatar;
    public static Hashtable smallAvatar35;
    public static Vector talk;
    public static final int SEX_MALE = 1;
    public static final int SEX_FEMALE = 2;
    public static final Color COLOR_MALE;
    public static final Color COLOR_FEMALE;
    
    public static void addUserInfos(final String s, final String s2, final String s3) {
        String replaceAll = s2;
        if (replaceAll.indexOf("xxx") != -1) {
            replaceAll = replaceAll.replaceAll("xxx", EIRC.monLoc);
        }
        if (replaceAll.length() >= 10) {
            boolean register = false;
            boolean tof = false;
            boolean cam = false;
            boolean nomessage = false;
            int int1;
            try {
                int1 = Integer.parseInt(replaceAll.substring(0, 2));
            }
            catch (Exception ex4) {
                int1 = 0;
            }
            int sexe;
            if (replaceAll.substring(2, 3).equalsIgnoreCase("h")) {
                sexe = 1;
            }
            else {
                sexe = 2;
            }
            if (replaceAll.substring(2, 3).equals("H") || replaceAll.substring(2, 3).equals("F")) {
                tof = true;
            }
            String location = NickInfos.locations.get(replaceAll.substring(3, 6));
            if (location == null) {
                location = "nulle part";
            }
            int int2;
            try {
                int2 = Integer.parseInt("" + replaceAll.charAt(6));
            }
            catch (Exception ex5) {
                int2 = 0;
            }
            String humeur;
            if (isint(replaceAll.charAt(6))) {
                try {
                    final String substring = replaceAll.substring(7, 9);
                    try {
                        final int int3 = Integer.parseInt(substring);
                        if (int3 == 0) {
                            humeur = null;
                        }
                        else {
                            humeur = "" + int3;
                        }
                    }
                    catch (Exception ex6) {
                        humeur = null;
                    }
                    if (humeur != null && humeur.equals("00")) {
                        humeur = null;
                    }
                }
                catch (Exception ex7) {
                    humeur = null;
                }
            }
            else {
                humeur = replaceAll.substring(7, 8);
            }
            try {
                register = (replaceAll.charAt(9) == 'r' || replaceAll.charAt(9) == 'R');
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
            try {
                cam = (replaceAll.charAt(9) == 'I' || replaceAll.charAt(9) == 'R');
            }
            catch (Exception ex2) {
                ex2.printStackTrace();
            }
            if (replaceAll.length() > 10) {
                try {
                    nomessage = (replaceAll.charAt(10) == '1');
                }
                catch (Exception ex3) {
                    ex3.printStackTrace();
                }
            }
            final NickInfoBean nickInfoBean = new NickInfoBean();
            nickInfoBean.setNick(s);
            nickInfoBean.setAge(int1);
            nickInfoBean.setSexe(sexe);
            nickInfoBean.setLocation(location);
            nickInfoBean.setLocationcode(replaceAll.substring(3, 6));
            nickInfoBean.setNosex(int2);
            nickInfoBean.setHumeur(humeur);
            nickInfoBean.setRegister(register);
            nickInfoBean.setTof(tof);
            nickInfoBean.setCam(cam);
            nickInfoBean.setNomessage(nomessage);
            if (replaceAll.endsWith("G")) {
                nickInfoBean.setAway("Absent(e)");
                nickInfoBean.setIdent(replaceAll.substring(0, replaceAll.length()));
            }
            else {
                nickInfoBean.setIdent(replaceAll);
            }
            nickInfoBean.setInetaddres(s3);
            NickInfos.users.put(s.toLowerCase(), nickInfoBean);
        }
        else if (replaceAll.equalsIgnoreCase("m")) {
            final NickInfoBean nickInfoBean2 = new NickInfoBean();
            nickInfoBean2.setNick(s);
            nickInfoBean2.setLocation("m");
            nickInfoBean2.setIdent("m");
            nickInfoBean2.setAge(0);
            nickInfoBean2.setSexe(0);
            nickInfoBean2.setLocationcode("aaa");
            nickInfoBean2.setNosex(0);
            nickInfoBean2.setHumeur("");
            nickInfoBean2.setRegister(true);
            nickInfoBean2.setTof(false);
            nickInfoBean2.setInetaddres(s3);
            NickInfos.users.put(s.toLowerCase(), nickInfoBean2);
        }
    }
    
    public static boolean changeNick(final String s, final String nick) {
        final NickInfoBean nickInfoBean = NickInfos.users.get(s.toLowerCase());
        if (nickInfoBean != null) {
            nickInfoBean.setNick(nick);
            NickInfos.users.remove(s.toLowerCase());
            NickInfos.users.put(nick.toLowerCase(), nickInfoBean);
            return true;
        }
        return false;
    }
    
    public static void free() {
        NickInfos.users.clear();
        NickInfos.smallAvatar.clear();
        NickInfos.smallAvatar35.clear();
    }
    
    public static int getAge(String lowerCase) {
        if (lowerCase != null) {
            lowerCase = lowerCase.toLowerCase();
            final NickInfoBean nickInfoBean = NickInfos.users.get(lowerCase);
            if (nickInfoBean != null) {
                return nickInfoBean.getAge();
            }
        }
        return 0;
    }
    
    public static Image getAvatar(String lowerCase) {
        lowerCase = lowerCase.toLowerCase();
        final NickInfoBean nickInfoBean = NickInfos.users.get(lowerCase);
        if (nickInfoBean != null) {
            return nickInfoBean.getAvatar();
        }
        return null;
    }
    
    public static String getAway(String lowerCase) {
        lowerCase = lowerCase.toLowerCase();
        final NickInfoBean nickInfoBean = NickInfos.users.get(lowerCase);
        if (nickInfoBean != null) {
            return nickInfoBean.getAway();
        }
        return null;
    }
    
    public static Image getBigAvatar(String lowerCase) {
        lowerCase = lowerCase.toLowerCase();
        final NickInfoBean nickInfoBean = NickInfos.users.get(lowerCase);
        if (nickInfoBean != null) {
            return nickInfoBean.getBigavatar();
        }
        return null;
    }
    
    public static String getChan(String lowerCase) {
        lowerCase = lowerCase.toLowerCase();
        final NickInfoBean nickInfoBean = NickInfos.users.get(lowerCase);
        if (nickInfoBean != null) {
            return nickInfoBean.getChan();
        }
        return null;
    }
    
    public static String getHumeur(String lowerCase) {
        lowerCase = lowerCase.toLowerCase();
        final NickInfoBean nickInfoBean = NickInfos.users.get(lowerCase);
        if (nickInfoBean != null) {
            return nickInfoBean.getHumeur();
        }
        return null;
    }
    
    public static String getInetAddr(String lowerCase) {
        if (lowerCase == null) {
            return null;
        }
        lowerCase = lowerCase.toLowerCase();
        final NickInfoBean nickInfoBean = NickInfos.users.get(lowerCase);
        if (nickInfoBean != null) {
            return nickInfoBean.getInetaddres();
        }
        return null;
    }
    
    public static String getLocation(String lowerCase) {
        lowerCase = lowerCase.toLowerCase();
        final NickInfoBean nickInfoBean = NickInfos.users.get(lowerCase);
        if (nickInfoBean != null) {
            return nickInfoBean.getLocation();
        }
        return "nulle part";
    }
    
    public static String getLocationCode(String lowerCase) {
        lowerCase = lowerCase.toLowerCase();
        final NickInfoBean nickInfoBean = NickInfos.users.get(lowerCase);
        if (nickInfoBean != null) {
            return nickInfoBean.getLocationcode();
        }
        return null;
    }
    
    public static int getSex(String lowerCase) {
        lowerCase = lowerCase.toLowerCase();
        final NickInfoBean nickInfoBean = NickInfos.users.get(lowerCase);
        if (nickInfoBean != null) {
            return nickInfoBean.getSexe();
        }
        return 3;
    }
    
    public static String getSexChar(String lowerCase) {
        lowerCase = lowerCase.toLowerCase();
        final NickInfoBean nickInfoBean = NickInfos.users.get(lowerCase);
        if (nickInfoBean == null) {
            return "Femme";
        }
        if (nickInfoBean.getSexe() == 1) {
            return "Homme";
        }
        return "Femme";
    }
    
    public static ImageIcon getSmall_avatar(final String s) {
        return NickInfos.smallAvatar35.get(s.toLowerCase());
    }
    
    public static ImageIcon getSmall_avatar20(final String s) {
        final ImageIcon value = NickInfos.smallAvatar.get(s.toLowerCase());
        ImageIcon imageIcon = null;
        if (value instanceof ImageIcon) {
            imageIcon = value;
        }
        if (imageIcon != null) {
            return new ImageIcon(scale(imageIcon.getImage(), 20, 20));
        }
        return null;
    }
    
    public static ImageIcon getSmall_avatar35(final String s) {
        return NickInfos.smallAvatar35.get(s.toLowerCase());
    }
    
    public static Image getSmall_avatar50(final String s) {
        final ImageIcon value = NickInfos.smallAvatar.get(s.toLowerCase());
        ImageIcon imageIcon = null;
        if (value instanceof ImageIcon) {
            imageIcon = value;
        }
        if (imageIcon != null) {
            return scale(imageIcon.getImage(), 55, 55);
        }
        return null;
    }
    
    public static String getUser(String lowerCase) {
        lowerCase = lowerCase.toLowerCase();
        final NickInfoBean nickInfoBean = NickInfos.users.get(lowerCase);
        if (nickInfoBean != null) {
            return nickInfoBean.getIdent();
        }
        return null;
    }
    
    public static boolean hasInfos(String lowerCase) {
        lowerCase = lowerCase.toLowerCase();
        return NickInfos.users.containsKey(lowerCase);
    }
    
    public static void init() {
        NickInfos.users = new Hashtable();
        NickInfos.locations = new Hashtable();
        NickInfos.regions = new Hashtable();
        NickInfos.smallAvatar = new Hashtable();
        NickInfos.smallAvatar35 = new Hashtable();
        NickInfos.talk = new Vector();
        final Enumeration<String> keys = Resources.location_Ressources.keys();
        while (keys.hasMoreElements()) {
            final String s = keys.nextElement();
            if (s.indexOf("dep.") == 0 && s.substring(4).length() == 3) {
                NickInfos.locations.put(s.substring(4), Resources.getLocation(s));
            }
        }
        final StringTokenizer stringTokenizer = new StringTokenizer(Resources.getLocation("dep.list"), "/");
        while (stringTokenizer.hasMoreTokens()) {
            final String nextToken = stringTokenizer.nextToken();
            NickInfos.regions.put(Resources.getLocation("dep." + nextToken + ".name"), nextToken);
        }
    }
    
    public static boolean isCam(String lowerCase) {
        lowerCase = lowerCase.toLowerCase();
        final NickInfoBean nickInfoBean = NickInfos.users.get(lowerCase);
        return nickInfoBean != null && nickInfoBean.isCam();
    }
    
    public static boolean isint(final char c) {
        return c == '1' || c == '2' || c == '3' || c == '4' || c == '5' || c == '6' || c == '7' || c == '8' || c == '9' || c == '0';
    }
    
    public static boolean isNomessage(String lowerCase) {
        lowerCase = lowerCase.toLowerCase();
        final NickInfoBean nickInfoBean = NickInfos.users.get(lowerCase);
        return nickInfoBean != null && nickInfoBean.isNomessage();
    }
    
    public static int isNosex(String lowerCase) {
        lowerCase = lowerCase.toLowerCase();
        final NickInfoBean nickInfoBean = NickInfos.users.get(lowerCase);
        if (nickInfoBean != null) {
            return nickInfoBean.isNosex();
        }
        return 1;
    }
    
    public static boolean isRegister(String lowerCase) {
        lowerCase = lowerCase.toLowerCase();
        final NickInfoBean nickInfoBean = NickInfos.users.get(lowerCase);
        return nickInfoBean == null || nickInfoBean.isRegister();
    }
    
    public static boolean isTalk(String lowerCase) {
        lowerCase = lowerCase.toLowerCase();
        return NickInfos.talk.contains(lowerCase);
    }
    
    public static boolean isTof(String lowerCase) {
        lowerCase = lowerCase.toLowerCase();
        final NickInfoBean nickInfoBean = NickInfos.users.get(lowerCase);
        return nickInfoBean != null && nickInfoBean.isTof();
    }
    
    public static Color nickToColor(String lowerCase) {
        lowerCase = lowerCase.trim().toLowerCase();
        return sexToColor(getSex(lowerCase));
    }
    
    public static void remove(String lowerCase) {
        lowerCase = lowerCase.toLowerCase();
        NickInfos.users.remove(lowerCase);
        NickInfos.smallAvatar.remove(lowerCase);
        NickInfos.smallAvatar35.remove(lowerCase);
    }
    
    public static Image scale(final Image image, final int n, final int n2) {
        final BufferedImage bufferedImage = new BufferedImage(n, n2, 2);
        final Graphics2D graphics = bufferedImage.createGraphics();
        graphics.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        graphics.drawImage(image, 0, 0, n, n2, null);
        graphics.dispose();
        return bufferedImage;
    }
    
    public static void setAvatar(String lowerCase, final Image avatar) {
        lowerCase = lowerCase.toLowerCase();
        final NickInfoBean nickInfoBean = NickInfos.users.get(lowerCase);
        if (nickInfoBean != null) {
            nickInfoBean.setAvatar(avatar);
        }
    }
    
    public static void setAway(String lowerCase, final String away) {
        lowerCase = lowerCase.toLowerCase();
        final NickInfoBean nickInfoBean = NickInfos.users.get(lowerCase);
        if (nickInfoBean != null) {
            nickInfoBean.setAway(away);
        }
    }
    
    public static void setBigAvatar(String lowerCase, final Image bigavatar) {
        lowerCase = lowerCase.toLowerCase();
        final NickInfoBean nickInfoBean = NickInfos.users.get(lowerCase);
        if (nickInfoBean != null) {
            nickInfoBean.setBigavatar(bigavatar);
        }
    }
    
    public static void setChan(String lowerCase, final String chan) {
        lowerCase = lowerCase.toLowerCase();
        final NickInfoBean nickInfoBean = NickInfos.users.get(lowerCase);
        if (nickInfoBean != null) {
            nickInfoBean.setChan(chan);
        }
    }
    
    public static void setHumeur(String lowerCase, final String humeur) {
        lowerCase = lowerCase.toLowerCase();
        final NickInfoBean nickInfoBean = NickInfos.users.get(lowerCase);
        if (nickInfoBean != null) {
            nickInfoBean.setHumeur(humeur);
        }
    }
    
    public static void setNoSex(String lowerCase, final int nosex) {
        lowerCase = lowerCase.toLowerCase();
        final NickInfoBean nickInfoBean = NickInfos.users.get(lowerCase);
        if (nickInfoBean != null) {
            nickInfoBean.setNosex(nosex);
        }
    }
    
    public static void setRegister(String lowerCase) {
        lowerCase = lowerCase.toLowerCase();
        final NickInfoBean nickInfoBean = NickInfos.users.get(lowerCase);
        if (nickInfoBean != null) {
            nickInfoBean.setRegister(true);
        }
    }
    
    public static void setSall_avatar(final String s) {
        if (!NickInfos.smallAvatar.containsKey(s.toLowerCase())) {
            if (isRegister(s)) {
                if (isTof(s)) {
                    final ImageIcon smallAvatar = avatar.getSmallAvatar(s);
                    if (smallAvatar != null) {
                        NickInfos.smallAvatar.put(s.toLowerCase(), smallAvatar);
                        NickInfos.smallAvatar35.put(s.toLowerCase(), new ImageIcon(scale(smallAvatar.getImage(), 35, 35)));
                    }
                    else {
                        NickInfos.smallAvatar.put(s.toLowerCase(), "");
                    }
                }
            }
            else {
                NickInfos.smallAvatar.put(s.toLowerCase(), "");
            }
        }
    }
    
    public static void setTalk(String lowerCase) {
        lowerCase = lowerCase.toLowerCase();
        NickInfos.talk.addElement(lowerCase);
    }
    
    public static Color sexToColor(final int n) {
        switch (n) {
            case 1: {
                return NickInfos.COLOR_MALE;
            }
            default: {
                return NickInfos.COLOR_FEMALE;
            }
        }
    }
    
    public static void updateInfos(String lowerCase, final String s, String s2) {
        lowerCase = lowerCase.toLowerCase();
        final NickInfoBean nickInfoBean = NickInfos.users.get(lowerCase);
        if (nickInfoBean != null) {
            if (!nickInfoBean.getIdent().equals(s) || !nickInfoBean.getInetaddres().equals(s2)) {
                final String away = nickInfoBean.getAway();
                final String inetaddres = nickInfoBean.getInetaddres();
                if (inetaddres != null && !inetaddres.equals("")) {
                    s2 = inetaddres;
                }
                NickInfos.users.remove(lowerCase);
                addUserInfos(lowerCase, s, s2);
                setAway(lowerCase, away);
            }
        }
        else {
            addUserInfos(lowerCase, s, s2);
        }
    }
    
    static {
        COLOR_MALE = Color.BLUE;
        COLOR_FEMALE = new Color(170, 0, 170);
    }
}

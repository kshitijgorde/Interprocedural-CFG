// 
// Decompiled by Procyon v0.5.30
// 

package irc.managers;

import java.util.Enumeration;
import java.util.StringTokenizer;
import irc.com.utils.MySQL;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import irc.com.nick.NickInfos;
import irc.com.utils.MD5Nick;
import irc.main;
import java.awt.Image;
import irc.EIRC;

public class avatar
{
    static EIRC eirc;
    public static final Image avatarInconnuH;
    public static final Image avatarInconnuF;
    public static final Image avatarloading;
    public static final Image avatarnonvip;
    public static final Image MybackGround;
    
    public static Image getAvatar(final String s) {
        Image image = null;
        try {
            final String string = "http://" + main.http + ".chat-land.org/modules/sun/images/show.php?a=" + s.toLowerCase() + "&b=" + MD5Nick.getMD5_3(s.toLowerCase()).substring(14, 21);
            new StringBuilder().append("http://").append(main.http).append(".chat-land.org/modules/sun/images/show.php?a=").append(s.toLowerCase()).append("&b=").append(MD5Nick.getMD5_3(s.toLowerCase()).substring(14, 21)).append("&c=xx").toString();
            image = Resources.GetBufferedImage(string);
            if (image == null) {
                if (NickInfos.getSex(s.toLowerCase()) == 1) {
                    image = avatar.avatarInconnuH;
                }
                else {
                    image = avatar.avatarInconnuF;
                }
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        NickInfos.setAvatar(s, image);
        return image;
    }
    
    public static ImageIcon getSmallAvatar(final String s) {
        final BufferedImage bufferedImage = Resources.getBufferedImage(("http://" + main.httpavatr + "/" + s + ".png").toLowerCase());
        if (bufferedImage != null) {
            return new ImageIcon(bufferedImage);
        }
        return null;
    }
    
    public static void init(final EIRC eirc) {
        avatar.eirc = eirc;
    }
    
    public static void loadMyAvatrs() {
        final String md5_3 = MD5Nick.getMD5_3(avatar.eirc.getUsednick().toLowerCase());
        final String string = "http://" + main.http + ".chat-land.org/modules/sun/images/listing.php?a=" + avatar.eirc.getUsednick().toLowerCase() + "&b=" + md5_3.substring(14, 21);
        try {
            final MySQL mySQL = new MySQL(string);
            mySQL.execute();
            if (!mySQL.getReturn().trim().equals("") && mySQL.getReturn() != null) {
                final StringTokenizer stringTokenizer = new StringTokenizer(mySQL.getReturn(), "\n");
                avatar.eirc.initphoto();
                while (stringTokenizer.hasMoreTokens()) {
                    final String nextToken = stringTokenizer.nextToken();
                    if (!nextToken.trim().equals("")) {
                        avatar.eirc.getMyphotos().put(nextToken, Resources.GetBufferedImage("http://" + main.http + ".chat-land.org/modules/sun/images/show.php?a=" + avatar.eirc.getUsednick().toLowerCase() + "&b=" + md5_3.substring(14, 21) + "&c=" + nextToken.toLowerCase() + "&d=" + MD5Nick.getMD5_3(nextToken.toLowerCase()).substring(14, 21)));
                    }
                }
                final MySQL mySQL2 = new MySQL("http://" + main.http + ".chat-land.org/modules/sun/images/get_default.php?a=" + avatar.eirc.getUsednick().toLowerCase() + "&b=" + md5_3.substring(14, 21));
                mySQL2.execute();
                final String trim = mySQL2.getReturn().trim();
                avatar.eirc.setMyimage((Image)avatar.eirc.getMyphotos().get(trim));
                avatar.eirc.setDefaultavatar(trim);
            }
            else if (NickInfos.getSex(avatar.eirc.getNick()) == 1) {
                avatar.eirc.setMyimage(avatar.avatarInconnuH);
            }
            else {
                avatar.eirc.setMyimage(avatar.avatarInconnuF);
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public static void setMyDefaultAvatar(final String s) {
        new MySQL("http://" + main.http + ".chat-land.org/modules/sun/images/set_default.php?a=" + avatar.eirc.getUsednick().toLowerCase() + "&b=" + MD5Nick.getMD5_3(avatar.eirc.getUsednick().toLowerCase()).substring(14, 21) + "&c=" + s.toLowerCase() + "&d=" + MD5Nick.getMD5_3(s.toLowerCase()).substring(14, 21)).execute();
        avatar.eirc.setMyimage(avatar.eirc.getMyphotos().get(s));
        final String[] array = { "", "[PhotoChange]" };
        final Enumeration<String> keys = avatar.eirc.getPrivates().privates.keys();
        while (keys.hasMoreElements()) {
            array[0] = keys.nextElement();
            avatar.eirc.sendMessage("NOTICE", array);
        }
    }
    
    static {
        avatarInconnuH = Resources.getImages("avatar.inconnuh");
        avatarInconnuF = Resources.getImages("avatar.inconnuf");
        avatarloading = Resources.getImages("loading_image");
        avatarnonvip = Resources.getImages("avatar.nonvip");
        MybackGround = Resources.getImages("panel.fond");
    }
}

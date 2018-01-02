import java.net.URLConnection;
import java.io.File;
import java.io.FileOutputStream;
import java.io.BufferedInputStream;
import java.net.UnknownHostException;
import java.net.MalformedURLException;
import java.net.URL;
import java.awt.event.ActionListener;
import java.awt.Component;
import java.awt.Container;
import java.awt.LayoutManager;
import java.io.UnsupportedEncodingException;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.FileNotFoundException;
import com.ms.security.PolicyEngine;
import com.ms.security.PermissionID;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.Label;
import java.awt.TextArea;
import java.awt.Image;
import java.awt.Button;
import java.awt.GridBagLayout;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class Euromaps extends Applet
{
    private GridBagLayout repartiteur;
    Button telecharger;
    private Image image;
    private MonLabel label;
    private TextArea txt;
    Label lab1;
    Label lab2;
    Label lab3;
    Label lab4;
    Label lab5;
    private Label lab6;
    private Label lab7;
    Label lb_telechargement;
    graphic_app Repartir;
    private Thread d_SVGThread;
    String environment;
    String OS_used;
    String OS_Language;
    String Home_directory;
    String Url_download;
    String Commande_system;
    String Commande_registre;
    String Commande_delete;
    String host;
    String VM;
    String NomNavigateur;
    String url_redirection;
    String Chemin_Install;
    String Chemin_SVG;
    String HKey;
    
    public Euromaps() {
        this.repartiteur = new GridBagLayout();
        this.telecharger = new Button("Installer le Plugin");
        this.image = null;
        this.label = null;
        this.txt = new TextArea("", 6, 50, 3);
        this.lab1 = new Label("Le g\u00e9n\u00e9rateur d'itin\u00e9raires Maporientation vous permet de cr\u00e9er ");
        this.lab2 = new Label("et d'enregistrer des parcours emploi/formation.");
        this.lab3 = new Label("Son utilisation n\u00e9cessite l'installation du plugin Adobe SVG viewer");
        this.lab4 = new Label("pour votre navigateur.");
        this.lab5 = new Label("L'installation est automatique et rapide (moins d'une minute avec l'ADSL).");
        this.lab6 = new Label("");
        this.lab7 = new Label("");
        this.lb_telechargement = new Label("");
        this.Repartir = new graphic_app();
        this.environment = "";
        this.OS_used = "";
        this.OS_Language = "";
        this.Home_directory = "";
        this.Url_download = "";
        this.Commande_system = "";
        this.Commande_registre = "";
        this.Commande_delete = "";
        this.host = "";
        this.VM = "";
        this.NomNavigateur = "";
        this.url_redirection = "";
        this.Chemin_Install = "";
        this.Chemin_SVG = "";
        this.HKey = "";
    }
    
    void startBActionPerformed(final ActionEvent actionEvent) {
        this.telecharger.setVisible(false);
        this.lb_telechargement.setAlignment(1);
        (this.d_SVGThread = new Thread(new download_SVG())).start();
    }
    
    public void initm() throws FileNotFoundException, IOException {
        this.label = new MonLabel(this.getImage(this.getCodeBase(), "./gifdroitreseau.gif"));
        final String parameter = this.getParameter("couleur");
        if (parameter != null) {
            try {
                this.setBackground(new Color(Integer.parseInt(parameter.substring(1), 16)));
            }
            catch (NumberFormatException ex3) {
                System.out.println("Invalid format for bgcolor: " + parameter);
            }
        }
        if (System.getProperty("java.vendor").startsWith("Microsoft")) {
            System.out.println("On est sur une jvm Microsoft");
            this.VM = "MicrosoftVM";
            try {
                if (Class.forName("com.ms.security.PermissionID") == null) {
                    return;
                }
            }
            catch (Exception ex4) {
                return;
            }
            try {
                PolicyEngine.assertPermission(PermissionID.SYSTEM);
                System.out.println("droits obtenus");
            }
            catch (Exception ex5) {
                System.out.println("Impossible d'obtenir la permission demandee");
                return;
            }
        }
        this.OS_used = System.getProperty("os.name");
        if (this.OS_used.equals("Windows NT") || this.OS_used.equals("Windows 2000") || this.OS_used.equals("Windows 2003") || this.OS_used.equals("Windows XP")) {
            this.Commande_system = "cmd.exe /c";
        }
        else if (this.OS_used.equals("Windows 95") || this.OS_used.equals("Windows 98") || this.OS_used.equals("Windows Me")) {
            this.Commande_system = "command.exe /c";
        }
        else {
            this.Commande_system = "";
        }
        this.NomNavigateur = this.getParameter("NomBrowser");
        System.out.println("Navigateur = " + this.NomNavigateur);
        if (this.NomNavigateur.equals("Firefox")) {
            this.environment = "Firefox";
            if (this.OS_used.startsWith("Win")) {
                System.out.println("environment " + this.environment);
                try {
                    this.Commande_registre = this.Commande_system + " regedit.exe /e .\\moz_reg_file.txt  \"HKEY_LOCAL_MACHINE\\SOFTWARE\\Mozilla\\Mozilla Firefox\"";
                    Runtime.getRuntime().exec(this.Commande_registre).waitFor();
                }
                catch (Exception ex) {
                    ex.printStackTrace();
                }
                this.Chemin_Install = this.detect_chemin_install(this.environment, ".\\moz_reg_file.txt");
            }
            else if (this.OS_used.startsWith("Lin")) {}
        }
        else if (this.NomNavigateur.startsWith("Netscape")) {
            if (this.NomNavigateur.equals("Netscape6")) {
                this.environment = "Netscape6";
                this.HKey = "HKEY_LOCAL_MACHINE\\SOFTWARE\\Netscape\\Netscape 6";
            }
            else if (this.NomNavigateur.equals("Netscape7")) {
                this.environment = "Netscape7";
                this.HKey = "HKEY_LOCAL_MACHINE\\SOFTWARE\\Netscape\\Netscape";
            }
            else if (this.NomNavigateur.equals("Netscape")) {
                this.environment = "Netscape";
                this.HKey = "HKEY_LOCAL_MACHINE\\SOFTWARE\\Netscape\\Netscape Browser";
            }
            if (this.OS_used.startsWith("Win")) {
                try {
                    this.Commande_registre = this.Commande_system + " regedit.exe /e .\\ns_reg_file.txt \"" + this.HKey + "\"";
                    System.out.println("registre " + this.Commande_registre);
                    Runtime.getRuntime().exec(this.Commande_registre).waitFor();
                }
                catch (Exception ex2) {
                    ex2.printStackTrace();
                }
                this.Chemin_Install = this.detect_chemin_install(this.environment, ".\\ns_reg_file.txt");
            }
            else if (this.OS_used.startsWith("Lin")) {}
        }
        else if (this.NomNavigateur.equals("MicrosoftIE")) {
            this.environment = "MicrosoftIE";
        }
        else {
            this.environment = "UNKNOWN";
        }
        this.url_redirection = this.getParameter("url_de_base");
        this.Home_directory = System.getProperty("user.home");
        this.OS_Language = System.getProperty("user.language");
        this.initComponents();
    }
    
    public String detect_chemin_install(final String s, final String s2) throws IOException {
        final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(s2)));
        String s3;
        for (String line = s3 = bufferedReader.readLine(); line != null; line = bufferedReader.readLine(), s3 += line) {}
        bufferedReader.close();
        try {
            final byte[] bytes = s3.getBytes("ASCII");
            s3 = "";
            for (int i = 0; i < bytes.length; ++i) {
                if (bytes[i] != 0) {
                    s3 += (char)bytes[i];
                }
            }
        }
        catch (UnsupportedEncodingException ex) {
            ex.printStackTrace();
        }
        if (s.equals("Firefox")) {
            s3 = s3.substring(s3.lastIndexOf("\"Install Directory\"=\"") + 21, s3.lastIndexOf("\"PathToExe\"=\"") - 1).concat("plugins");
        }
        else if (s.equals("Netscape")) {
            s3 = s3.substring(s3.lastIndexOf("\"PathToExe\"=\"") + 13, s3.lastIndexOf("Netscape Browser")).concat("Netscape Browser\\plugins");
            System.out.println("chemin plugin " + s3);
        }
        else if (s.equals("Netscape7")) {
            s3 = s3.substring(s3.lastIndexOf("\"Install Directory\"=\"") + 21, s3.lastIndexOf("\"PathToExe\"=\"") - 1).concat("plugins");
            System.out.println("chemin plugin " + s3);
        }
        else if (s.equals("Netscape6")) {
            s3 = s3.substring(s3.lastIndexOf("\"Uninstall Log Folder\"=\"") + 24, s3.lastIndexOf("Uninstall\"")).concat("plugins");
            System.out.println("chemin plugin " + s3);
        }
        else if (s.equals("SVG_Plugin")) {
            s3 = s3.substring(s3.indexOf("\"dir\"=\"") + 7, s3.length() - 5);
        }
        try {
            this.Commande_delete = this.Commande_system + " DEL " + s2;
            Runtime.getRuntime().exec(this.Commande_delete).waitFor();
        }
        catch (Exception ex2) {
            ex2.printStackTrace();
        }
        return s3;
    }
    
    private void initComponents() {
        this.setLayout(this.repartiteur);
        this.lab1.setAlignment(0);
        this.lab2.setAlignment(0);
        this.lab3.setAlignment(0);
        this.lab4.setAlignment(0);
        this.lab5.setAlignment(0);
        graphic_app.ajouter(this, this.lab1, 0, 0, 2, 1, 0.0, 0.0);
        graphic_app.ajouter(this, this.lab2, 0, -1, 2, 1, 0.0, 0.0);
        graphic_app.ajouter(this, this.lab3, 0, -1, 2, 1, 0.0, 0.0);
        graphic_app.ajouter(this, this.lab4, 0, -1, 2, 1, 0.0, 0.0);
        graphic_app.ajouter(this, this.lab5, 0, -1, 2, 1, 0.0, 0.0);
        graphic_app.ajouter(this, this.telecharger, 1, -1, 1, 1, 0, 15, 0, 0, 0, 0, 0, 0, 0.0, 0.0);
        graphic_app.ajouter(this, this.lb_telechargement, 0, 5, 2, 1, 0.0, 0.0);
        graphic_app.ajouter(this, this.lab6, 0, -1, 2, 1, 0.0, 0.0);
        graphic_app.ajouter(this, this.lab7, 0, -1, 2, 1, 0.0, 0.0);
        graphic_app.ajouter(this, this.label, 0, -1, 2, 1, 0.0, 0.0);
        this.telecharger.addActionListener(new ActionListener() {
            {
                Euromaps.this.getClass();
            }
            
            public void actionPerformed(final ActionEvent actionEvent) {
                Euromaps.this.startBActionPerformed(actionEvent);
            }
        });
        this.setBounds(0, 0, 500, 330);
    }
    
    public void init() {
        try {
            this.initm();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public class download_SVG implements Runnable
    {
        private URL racine;
        
        public download_SVG() {
            Euromaps.this.getClass();
            if (Euromaps.this.OS_used.startsWith("Win")) {
                if (Euromaps.this.environment.equals("MicrosoftIE")) {
                    Euromaps.this.host = "http://download.adobe.com/pub/adobe/magic/svgviewer/win/3.x/3.01/en/SVGView.exe";
                }
                else {
                    Euromaps.this.host = "http://download.adobe.com/pub/adobe/magic/svgviewer/win/6.x/6.0x38363/en/SVGView.exe";
                }
            }
            else if (Euromaps.this.OS_used.startsWith("Lin")) {
                Euromaps.this.host = "http://download.adobe.com/pub/adobe/magic/svgviewer/linux/3.x/3.01x88/en/adobesvg-3.01x88-linux-i386.tar.gz";
            }
            else if (Euromaps.this.OS_used.startsWith("Mac OS X")) {
                Euromaps.this.host = "http://download.adobe.com/pub/adobe/magic/svgviewer/mac/3.x/3.0/fr/SVGViewCarbon.bin";
            }
            try {
                this.racine = new URL(Euromaps.this.host);
            }
            catch (MalformedURLException ex) {
                System.err.println(Euromaps.this.host + " : URL non compris.");
            }
        }
        
        public void run() {
            try {
                this.connecting(this.racine);
            }
            catch (MalformedURLException ex2) {
                System.err.println("URL non compris.");
            }
            catch (IOException ex) {
                System.err.println(ex);
            }
        }
        
        public void connecting(final URL url) throws IOException {
            final URLConnection openConnection = url.openConnection();
            openConnection.setAllowUserInteraction(true);
            openConnection.setDoInput(true);
            openConnection.setDoOutput(false);
            openConnection.setRequestProperty("Content-Type", "application/octet-stream");
            openConnection.setUseCaches(false);
            openConnection.setDefaultUseCaches(false);
            try {
                openConnection.connect();
            }
            catch (UnknownHostException ex) {
                ex.printStackTrace();
            }
            openConnection.getContentType();
            final int contentLength = openConnection.getContentLength();
            System.out.println("la taille du fichier est :" + contentLength + " octets");
            if (contentLength == -1) {
                throw new IOException("Fichier endommag\u00e9.");
            }
            Euromaps.this.lb_telechargement.setText("t\u00e9l\u00e9chargement en cours...");
            final BufferedInputStream bufferedInputStream = new BufferedInputStream(openConnection.getInputStream());
            final byte[] array = new byte[contentLength];
            int i = 0;
            while (i < contentLength) {
                final int read = bufferedInputStream.read(array, i, array.length - i);
                if (read == -1) {
                    break;
                }
                i += read;
                final int n = i * 100 / contentLength;
                System.out.println("pourcentage = " + n);
                Euromaps.this.lb_telechargement.setText(n + " % t\u00e9l\u00e9charg\u00e9s");
            }
            bufferedInputStream.close();
            if (i != contentLength) {
                throw new IOException("Erreur lors du t\u00e9l\u00e9chargement du fichier. Seuleument " + i / contentLength * 100 + "% du fichier t\u00e9l\u00e9charg\u00e9");
            }
            final String file = url.getFile();
            System.out.println("nom du fichier " + file);
            String s = file.substring(file.lastIndexOf(47) + 1);
            if (Euromaps.this.OS_used.startsWith("Win")) {
                s = Euromaps.this.Home_directory.concat("\\" + s);
            }
            else if (Euromaps.this.OS_used.startsWith("Lin") || Euromaps.this.OS_used.startsWith("Mac OS X")) {
                s = Euromaps.this.Home_directory.concat("/" + s);
            }
            final FileOutputStream fileOutputStream = new FileOutputStream(s);
            System.out.println("le fichier est " + s);
            fileOutputStream.write(array);
            fileOutputStream.close();
            final String property = System.getProperty("os.version");
            if (Euromaps.this.OS_used.startsWith("Win")) {
                try {
                    Runtime.getRuntime().exec(s).waitFor();
                    System.out.println("installation");
                }
                catch (Exception ex2) {
                    ex2.printStackTrace();
                }
                Euromaps.this.lb_telechargement.setText("Mise \u00e0 jour de votre syst\u00e8me");
                try {
                    Euromaps.this.Commande_delete = Euromaps.this.Commande_system + " DEL " + s;
                    System.out.println(Euromaps.this.Commande_delete);
                    Runtime.getRuntime().exec(Euromaps.this.Commande_delete).waitFor();
                    System.out.println("suppression");
                }
                catch (Exception ex12) {
                    System.out.println("pas de suppression");
                }
                URL url2 = null;
                System.out.println("environment " + Euromaps.this.environment + " OS_used " + Euromaps.this.OS_used);
                System.out.println("OS_Language " + Euromaps.this.OS_Language + " Home_directory " + Euromaps.this.Home_directory);
                final byte[] array2 = new byte[4096];
                if (Euromaps.this.environment.equals("Firefox") || Euromaps.this.environment.startsWith("Netscape")) {
                    System.out.println("copier coller");
                    try {
                        Euromaps.this.Commande_registre = Euromaps.this.Commande_system + " regedit.exe /e .\\svg_reg_file.txt  \"HKEY_LOCAL_MACHINE\\SOFTWARE\\Adobe\\Adobe SVG Viewer\"";
                        Runtime.getRuntime().exec(Euromaps.this.Commande_registre).waitFor();
                    }
                    catch (Exception ex3) {
                        ex3.printStackTrace();
                    }
                    Euromaps.this.Chemin_SVG = Euromaps.this.detect_chemin_install("SVG_Plugin", ".\\svg_reg_file.txt");
                    try {
                        final FileInputStream fileInputStream = new FileInputStream(Euromaps.this.Chemin_SVG + "\\Plugins\\NPSVG6.zip");
                        final FileOutputStream fileOutputStream2 = new FileOutputStream(Euromaps.this.Chemin_Install + "\\NPSVG6.zip");
                        for (int j = fileInputStream.read(array2); j > 0; j = fileInputStream.read(array2)) {
                            fileOutputStream2.write(array2, 0, j);
                        }
                        fileInputStream.close();
                        fileOutputStream2.close();
                    }
                    catch (IOException ex4) {
                        ex4.printStackTrace();
                    }
                    try {
                        final FileInputStream fileInputStream2 = new FileInputStream(Euromaps.this.Chemin_SVG + "\\Plugins\\NPSVG6.dll");
                        final FileOutputStream fileOutputStream3 = new FileOutputStream(Euromaps.this.Chemin_Install + "\\NPSVG6.dll");
                        for (int k = fileInputStream2.read(array2); k > 0; k = fileInputStream2.read(array2)) {
                            fileOutputStream3.write(array2, 0, k);
                        }
                        fileInputStream2.close();
                        fileOutputStream3.close();
                        System.out.println("fin du copier coller");
                    }
                    catch (IOException ex5) {
                        ex5.printStackTrace();
                    }
                    Euromaps.this.telecharger.setVisible(false);
                    Euromaps.this.lab1.setText("L'installation s'est d\u00e9roul\u00e9e avec succ\u00e8s.");
                    Euromaps.this.lab2.setText("Pour acc\u00e9der \u00e0 Maporientation, vous devez fermer toutes les fen\u00eatres");
                    Euromaps.this.lab3.setText("de votre navigateur et le relancer.");
                    Euromaps.this.lab4.setText("");
                    Euromaps.this.lab5.setText("Bonne navigation.");
                    Euromaps.this.lb_telechargement.setText("");
                }
                else {
                    try {
                        url2 = new URL(Euromaps.this.url_redirection);
                        Euromaps.this.getAppletContext().showDocument(url2);
                    }
                    catch (MalformedURLException ex13) {
                        System.out.println("??? " + url2);
                    }
                }
            }
            else if (Euromaps.this.OS_used.startsWith("Lin")) {
                try {
                    Runtime.getRuntime().exec(new String[] { "/bin/sh ", "-c", " ls " + Euromaps.this.Home_directory }).waitFor();
                }
                catch (Exception ex6) {
                    ex6.printStackTrace();
                }
                try {
                    Runtime.getRuntime().exec(new String[] { "/bin/sh ", "-c", " cd " + Euromaps.this.Home_directory }).waitFor();
                    System.out.println("se mettre sur le repertoire home");
                    Euromaps.this.lab2.setText("cd ok");
                }
                catch (Exception ex7) {
                    ex7.printStackTrace();
                }
                try {
                    final String string = "tar xzvf " + Euromaps.this.Home_directory + "/adobesvg-3.01x88-linux-i386.tar.gz";
                    Runtime.getRuntime().exec(string).waitFor();
                    System.out.println("decompression du fichier");
                    Euromaps.this.lab3.setText("tar : " + string);
                }
                catch (Exception ex14) {
                    Euromaps.this.lab4.setText("erreur de decompression");
                }
                try {
                    Runtime.getRuntime().exec(Euromaps.this.Home_directory + "/adobesvg-3.01/install.sh").waitFor();
                    System.out.println("lancement de l'installation");
                    Euromaps.this.lab5.setText("install ok");
                }
                catch (Exception ex15) {
                    Euromaps.this.lb_telechargement.setText("erreur dans install.sh");
                }
            }
            else if (Euromaps.this.OS_used.equals("Mac OS X") && !property.startsWith("10.0.")) {
                try {
                    Euromaps.this.lb_telechargement.setText("Lancement de l'installation");
                    Runtime.getRuntime().exec(new String[] { "open", Euromaps.this.Home_directory + "/SVGViewCarbon.bin" }).waitFor();
                }
                catch (Exception ex8) {
                    ex8.printStackTrace();
                }
                Euromaps.this.lb_telechargement.setText("Decompression du fichier d'installation...");
                while (!new File(Euromaps.this.Home_directory + "/SVGViewCarbon").exists()) {}
                try {
                    Thread.currentThread();
                    Thread.sleep(1500L);
                }
                catch (InterruptedException ex9) {
                    ex9.printStackTrace();
                }
                try {
                    Euromaps.this.lb_telechargement.setText("Installation en cours ...");
                    Runtime.getRuntime().exec(new String[] { "open", Euromaps.this.Home_directory + "/SVGViewCarbon" }).waitFor();
                }
                catch (Exception ex10) {
                    ex10.printStackTrace();
                }
                while (!new File(Euromaps.this.Home_directory + "/Adobe SVG 3.0 Installer Log").exists()) {}
                try {
                    Thread.currentThread();
                    Thread.sleep(1500L);
                }
                catch (InterruptedException ex11) {
                    ex11.printStackTrace();
                }
                Euromaps.this.telecharger.setVisible(false);
                Euromaps.this.lab1.setText("L'installation s'est d\u00e9roul\u00e9e avec succ\u00e8s.");
                Euromaps.this.lab2.setText("Pour acc\u00e9der \u00e0 Maporientation, vous devez fermer toutes les fen\u00eatres ");
                Euromaps.this.lab3.setText("de votre navigateur et le relancer.");
                Euromaps.this.lab4.setText("");
                Euromaps.this.lab5.setText("Bonne navigation.");
                Euromaps.this.lb_telechargement.setText("");
            }
            else if (property.startsWith("10.0.")) {
                Euromaps.this.telecharger.setVisible(false);
                Euromaps.this.lab1.setText("Maporientation a d\u00e9tect\u00e9 que votre syst\u00e8me d'exploitation est Mac OS 10.0.");
                Euromaps.this.lab2.setText("L'installation du plugin Adobe SVG Viewer requise pour Maporientation");
                Euromaps.this.lab3.setText("n\u00e9cessite Mac OS X 10.1 ou une version sup\u00e9rieure.");
                Euromaps.this.lab4.setText("");
                Euromaps.this.lab5.setText("");
                Euromaps.this.lb_telechargement.setText("");
            }
            else {
                URL url3 = null;
                Euromaps.this.url_redirection += "8_9";
                try {
                    url3 = new URL(Euromaps.this.url_redirection);
                    Euromaps.this.getAppletContext().showDocument(url3);
                }
                catch (MalformedURLException ex16) {
                    System.out.println("??? " + url3);
                }
            }
        }
    }
}

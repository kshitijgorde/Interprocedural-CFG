import java.io.FileNotFoundException;
import org.jdom.Document;
import java.io.InputStream;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

// 
// Decompiled by Procyon v0.5.30
// 

public class JDOMAbout
{
    public static void main(final String[] args) throws Exception {
        final Info info = new Info();
        final String title = info.title;
        System.out.println(title + " version " + info.version);
        System.out.println("Copyright " + info.copyright);
        System.out.println();
        System.out.println(info.description);
        System.out.println();
        System.out.println("Authors:");
        for (final Author author : info.authors) {
            System.out.print("  " + author.name);
            if (author.email == null) {
                System.out.println();
            }
            else {
                System.out.println(" <" + author.email + ">");
            }
        }
        System.out.println();
        System.out.println(title + " license:");
        System.out.println(info.license);
        System.out.println();
        System.out.println(title + " support:");
        System.out.println(info.support);
        System.out.println();
        System.out.println(title + " web site: " + info.website);
        System.out.println();
    }
    
    private static class Info
    {
        private static final String INFO_FILENAME = "META-INF/jdom-info.xml";
        final String title;
        final String version;
        final String copyright;
        final String description;
        final String license;
        final String support;
        final String website;
        final List authors;
        
        Info() throws Exception {
            this.authors = new ArrayList();
            final InputStream inputStream = this.getInfoFileStream();
            final SAXBuilder builder = new SAXBuilder();
            final Document doc = builder.build(inputStream);
            final Element root = doc.getRootElement();
            this.title = root.getChildTextTrim("title");
            this.version = root.getChildTextTrim("version");
            this.copyright = root.getChildTextTrim("copyright");
            this.description = root.getChildTextTrim("description");
            this.license = root.getChildTextTrim("license");
            this.support = root.getChildTextTrim("support");
            this.website = root.getChildTextTrim("web-site");
            final List authorElements = root.getChildren("author");
            for (final Element element : authorElements) {
                final Author author = new Author(element.getChildTextTrim("name"), element.getChildTextTrim("e-mail"));
                this.authors.add(author);
            }
        }
        
        private InputStream getInfoFileStream() throws FileNotFoundException {
            final InputStream inputStream = this.getClass().getResourceAsStream("META-INF/jdom-info.xml");
            if (inputStream == null) {
                throw new FileNotFoundException("META-INF/jdom-info.xml not found; it should be within the JDOM JAR but wasn't found on the classpath");
            }
            return inputStream;
        }
    }
    
    private static class Author
    {
        final String name;
        final String email;
        
        Author(final String name, final String email) {
            this.name = name;
            this.email = email;
        }
    }
}

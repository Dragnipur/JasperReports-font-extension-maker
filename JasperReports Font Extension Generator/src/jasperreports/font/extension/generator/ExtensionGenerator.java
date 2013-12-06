package jasperreports.font.extension.generator;

import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.jar.Attributes;
import java.util.jar.JarEntry;
import java.util.jar.JarOutputStream;
import java.util.jar.Manifest;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 *
 * @author Pieter
 */
public class ExtensionGenerator {

    private Fonts fonts;
    private String folderName;
    private ArrayList<String> fontFamilies;
    private Document doc;
    private Element rootElement;

    public ExtensionGenerator(Fonts fonts) {
        this.fonts = fonts;
    }

    public void setupFontFamilies() {
        fontFamilies = new ArrayList<>();
        int fontCount = fonts.getFontCount();

        for (int i = 0; i < fontCount; i++) {
            Font font = fonts.getFont(i);
            String fontFamily = font.getFamily();

            if (!fontFamilies.contains(fontFamily)) {
                fontFamilies.add(fontFamily);
            }
        }
    }

    public boolean setupExtensionFolders() {
        generateRandomFolderName();
        File dir = new File(folderName + "/src/fonts");
        boolean success = dir.mkdirs();
        return success;
    }

    private void generateRandomFolderName() {
        folderName = RandomStringUtils.randomAlphanumeric(20);

    }

    public boolean importFontFiles() {
        int fontCount = fonts.getFontCount();

        for (int i = 0; i < fontCount; i++) {
            Font font = fonts.getFont(i);
            String filePath = font.getFilePath();
            String fileName = font.getFileName();
            boolean success = importFile(filePath, fileName);

            if (success == false) {
                return false;
            }
        }
        return true;
    }

    public boolean importFile(String filePath, String fileName) {
        try {
            File sourceFile = new File(filePath);
            File destinationFile = new File(folderName + "/src/fonts/" + fileName);
            FileUtils.copyFile(sourceFile, destinationFile);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean createPropertiesFile() {
        try {
            File properties = new File(folderName + "/src/jasperreports_extension.properties");
            properties.createNewFile();

            FileWriter fw = new FileWriter(properties.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write("net.sf.jasperreports.extension.registry.factory.simple.font.families=net.sf.jasperreports.engine.fonts.SimpleFontExtensionsRegistryFactory\n");
            bw.write("net.sf.jasperreports.extension.simple.font.families.gen_fonts=fonts.xml");
            bw.close();

            return true;

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean createFontsXml() {

        try {

            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            doc = docBuilder.newDocument();
            rootElement = doc.createElement("fontFamilies");
            doc.appendChild(rootElement);

            addFontFamiliesToXml();
            addPdfVariablesToXml();
            addExportFontsToXml();

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(folderName + "/src/fonts/fonts.xml"));
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.transform(source, result);
            return true;

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }
        return false;
    }

    private String getFontByFamily(String family, String type) {
        for (int i = 0; i < fonts.getFontCount(); i++) {
            Font font = fonts.getFont(i);
            String fontFamily = font.getFamily();
            String fontType = font.getType();

            if (fontFamily.equals(family) && fontType.equals(type)) {
                String fontName = font.getFileName();
                return fontName;
            }
        }
        return null;
    }

    private void addFontFamiliesToXml() {

        for (int i = 0; i < fontFamilies.size(); i++) {
            Element fontFamily = doc.createElement("fontFamily");
            rootElement.appendChild(fontFamily);
            String familyName = fontFamilies.get(i);
            fontFamily.setAttribute("name", fontFamilies.get(i));

            String normalFont = getFontByFamily(familyName, "Normal");
            String italicFont = getFontByFamily(familyName, "Italic");
            String boldFont = getFontByFamily(familyName, "Bold");
            String boldItalicFont = getFontByFamily(familyName, "Bold Italic");

            if (normalFont != null) {
                Element normal = doc.createElement("normal");
                normal.appendChild(doc.createTextNode(normalFont));
                fontFamily.appendChild(normal);
            }

            if (italicFont != null) {
                Element italic = doc.createElement("italic");
                italic.appendChild(doc.createTextNode(italicFont));
                fontFamily.appendChild(italic);
            }

            if (boldFont != null) {
                Element bold = doc.createElement("bold");
                bold.appendChild(doc.createTextNode(boldFont));
                fontFamily.appendChild(bold);
            }

            if (boldItalicFont != null) {
                Element boldItalic = doc.createElement("boldItalic");
                boldItalic.appendChild(doc.createTextNode(boldItalicFont));
                fontFamily.appendChild(boldItalic);
            }
        }
    }

    private void addPdfVariablesToXml() {
        Element pdfEncoding = doc.createElement("pdfEncoding");
        pdfEncoding.appendChild(doc.createTextNode("Identity-H"));
        rootElement.appendChild(pdfEncoding);

        Element pdfEmbedded = doc.createElement("pdfEmbedded");
        pdfEmbedded.appendChild(doc.createTextNode("true"));
        rootElement.appendChild(pdfEmbedded);
    }

    private void addExportFontsToXml() {
        Element exportFonts = doc.createElement("exportFonts");
        rootElement.appendChild(exportFonts);

        Element exportHtml = doc.createElement("export");
        exportHtml.appendChild(doc.createTextNode("'Times New Roman', Times, serif"));
        exportHtml.setAttribute("key", "net.sf.jasperreports.html");
        exportFonts.appendChild(exportHtml);

        Element exportXhtml = doc.createElement("export");
        exportXhtml.appendChild(doc.createTextNode("'Times New Roman', Times, serif"));
        exportXhtml.setAttribute("key", "net.sf.jasperreports.xhtml");
        exportFonts.appendChild(exportXhtml);
    }

    public boolean generateJar() {

        try {
            Manifest manifest = new Manifest();
            manifest.getMainAttributes().put(Attributes.Name.MANIFEST_VERSION, "1.0");
            FileOutputStream jarStream = new FileOutputStream("fontExtension.jar");
            JarOutputStream target = new JarOutputStream(jarStream, manifest);
            File source = new File(folderName + "/src/");
            boolean success = addFileToJar(source, target);
            target.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return true;
    }

    private boolean addFileToJar(File source, JarOutputStream target) {
        try {
            BufferedInputStream in = null;
            try {
                if (source.isDirectory()) {
                    String name = source.getPath().replace("\\", "/");
                    if (!name.isEmpty()) {
                        if (!name.endsWith("/")) {
                            name += "/";
                        }
                    }

                    for (File nestedFile : source.listFiles()) {
                        addFileToJar(nestedFile, target);
                    }
                    return false;
                }

                String path = source.getPath().replace("\\", "/");
                String fileName;

                if (path.contains("fonts/")) {
                    String split[] = path.split("fonts/");
                    fileName = split[1];
                } else {
                    String split[] = path.split("src/");
                    fileName = split[1];
                }

                JarEntry entry = new JarEntry(fileName);
                entry.setTime(source.lastModified());
                target.putNextEntry(entry);
                in = new BufferedInputStream(new FileInputStream(source));

                byte[] buffer = new byte[1024];
                while (true) {
                    int count = in.read(buffer);
                    if (count == -1) {
                        break;
                    }
                    target.write(buffer, 0, count);
                }
                target.closeEntry();
            } finally {
                if (in != null) {
                    in.close();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean cleanUpTemporaryFiles() {
        try {
            recursiveDelete(new File(folderName));
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public void recursiveDelete(File file) throws IOException {

        if (file.isDirectory()) {

            //directory is empty, then delete it
            if (file.list().length == 0) {
                file.delete();
            } else {

                //list all the directory contents
                String files[] = file.list();

                for (String temp : files) {
                    //construct the file structure
                    File fileDelete = new File(file, temp);

                    //recursive delete
                    recursiveDelete(fileDelete);
                }

                //check the directory again, if empty then delete it
                if (file.list().length == 0) {
                    file.delete();
                }
            }

        } else {
            //if file, then delete it
            file.delete();
        }
    }
}

package com.genetic.file;

import java.nio.file.Paths;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class SettingFile {
    public void updateAppConfig() {

    }
    public void loadFilePath() {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(Paths.get("fileconfig.xml").toFile());
            Element languageList = document.getDocumentElement();
            NodeList languages = languageList.getElementsByTagName("FilePath");
            for(int i = 0; i < languages.getLength(); i++) {
                Element language = (Element) languages.item(i);
                String id = language.getAttribute("id");
                String name = language.getAttribute("name");
                String content = language.getTextContent();
                System.out.println("id = " + id);
                System.out.println("name = " + name);
                System.out.println("text = " + content);
                System.out.println();
            }
        } catch(Exception e2) {

        }
    }
}

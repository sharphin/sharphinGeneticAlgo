package com.genetic.file;

import java.nio.file.Paths;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.genetic.entity.AppParam;

public class SettingFile {
    public AppParam loadFilePath() {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(Paths.get("fileconfig.xml").toFile());
            Element elements = document.getDocumentElement();
            NodeList nodes = elements.getChildNodes();
            return new AppParam(nodes.item(1).getTextContent(),
                                nodes.item(3).getTextContent(),
                                Integer.parseInt(nodes.item(5).getTextContent()),
                                Integer.parseInt(nodes.item(7).getTextContent()));

        } catch(Exception e) {
            return null;
        }
    }
}

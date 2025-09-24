package com.barco.service1.util;

import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;

/**
 * @author Nabeel Ahmed
 */
@Component
public class XmlOutTagInfoUtil {

    public Logger logger = LoggerFactory.getLogger(XmlOutTagInfoUtil.class);

    /**
     * Method use to convert xml to object
     * @param xmlString
     * @param type
     * @return <T> T
     * */
    public <T> T convertXMLToObject(String xmlString, Class<T> type) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(type);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            return type.cast(jaxbUnmarshaller.unmarshal(new StringReader(xmlString)));
        } catch (JAXBException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public String toString() { return new Gson().toJson(this); }

}
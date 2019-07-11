package com.accenture.flowershop.marshallingservice;

import org.springframework.oxm.Marshaller;
import org.springframework.oxm.Unmarshaller;
import org.springframework.stereotype.Component;

import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

@Component
public class UserMarshallingServiceImpl implements UserMarshallingService {
    private Marshaller marshaller;
    private Unmarshaller unmarshaller;

    public Marshaller getMarshaller() {
        return marshaller;
    }

    public void setMarshaller(Marshaller marshaller) {
        this.marshaller = marshaller;
    }

    public Unmarshaller getUnmarshaller() {
        return unmarshaller;
    }

    public void setUnmarshaller(Unmarshaller unmarshaller) {
        this.unmarshaller = unmarshaller;
    }

    public void convertFromObjectToXML(Object object, String filepath) throws IOException {
        File file = new File(filepath);
        String path = file.getAbsolutePath();
        FileOutputStream os = new FileOutputStream(filepath);

        try  {
            getMarshaller().marshal(object, new StreamResult(os));
        } finally {
            os.close();
        }
    }

    public Object convertFromXMLToObject(String xmlfile) throws IOException {

        try (FileInputStream is = new FileInputStream(xmlfile)) {
            return getUnmarshaller().unmarshal(new StreamSource(is));
        }
    }
}

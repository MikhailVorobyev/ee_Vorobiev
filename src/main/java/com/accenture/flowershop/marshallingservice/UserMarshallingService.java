package com.accenture.flowershop.marshallingservice;

import java.io.IOException;

public interface UserMarshallingService {
    void convertFromObjectToXML(Object object, String filepath) throws IOException;

    Object convertFromXMLToObject(String xmlfile) throws IOException;
}

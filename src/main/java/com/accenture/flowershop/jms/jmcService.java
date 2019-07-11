package com.accenture.flowershop.jms;

import javax.jms.JMSException;

public interface jmcService {
    void sendJmsMessage(String message);

    String receiveJmsMessage();
}

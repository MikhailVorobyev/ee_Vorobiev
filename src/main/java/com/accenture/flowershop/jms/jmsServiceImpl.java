package com.accenture.flowershop.jms;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class jmsServiceImpl implements jmcService {
    private static String url = ActiveMQConnection.DEFAULT_BROKER_URL;
    private static String subject = "OUT_QUEUE";

    private Connection connection;

    {
        try {
            connection = new ActiveMQConnectionFactory(url).createConnection();
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void sendJmsMessage(String message) {
        try {
            Session session = createSession();
            Destination destination = session.createQueue(subject);
            MessageProducer producer = session.createProducer(destination);
            TextMessage newMessage = session.createTextMessage(message);
            producer.send(newMessage);

            connection.close();
        } catch (JMSException e) {
            e.getStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public String receiveJmsMessage() {
        try {
            Session session = createSession();
            Destination destination = session.createQueue(subject);
            MessageConsumer consumer = session.createConsumer(destination);
            Message message = consumer.receive();

            if (message instanceof TextMessage) {
                TextMessage textMessage = (TextMessage) message;
                return textMessage.getText();
            }
            connection.close();
        } catch (JMSException e) {
            e.getStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    private Session createSession() throws JMSException {
        connection.start();
        return connection.createSession(false,
                Session.AUTO_ACKNOWLEDGE);
    }
}

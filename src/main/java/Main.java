import CarShop.CarNotFoundException;
import CarShop.Store;
import models.Car;
import models.Client;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * Created by sa on 08.02.17.
 */
public class Main {
    public static final Logger logger = Logger.getLogger(Main.class);

    static {
        DOMConfigurator.configure("src/main/resources/log4j.xml");
    }

    public static void main(String[] args) throws CarNotFoundException {



        final String username = "e.svetozarov.stc@innopolis.ru";
        final String password = "sTu8UrEqaf";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "mail.innopolis.ru");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("e.svetozarov.stc@innopolis.ru"));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse("evgenij.svetozarov@yandex.ru"));
            message.setSubject("Testing Subject");
            message.setText("Dear Mail Crawler,"
                    + "\n\n No spam to my email, please!");

            Transport.send(message);

            System.out.println("Done");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }


/*
       Store store = new Store();
        logger.error(new Client("Ivan","Pupkin","123456"));
        store.createCar(500000, "kia-rio",
                "B146AA");

       /* store.sellCar("kia-reva",
                "Jhon",
                "Konner" ,
                "+79126241898");
*/
      //  store.save();
    }
}

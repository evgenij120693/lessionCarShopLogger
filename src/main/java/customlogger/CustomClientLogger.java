package customlogger;


import models.Client;
import org.apache.log4j.PatternLayout;
import org.apache.log4j.spi.LoggingEvent;

/**
 * Created by Шмыга on 14.02.2017.
 */
public class CustomClientLogger extends PatternLayout {
    public String format(LoggingEvent event){
        Client object=(Client) event.getMessage();
        StringBuffer stringBuffer=new StringBuffer();

        String firstName=object.getFirstName();
        String lastName=object.getLastName();
        String phoneNumber=object.getPhoneNumber();

        stringBuffer.append("<client>");
        stringBuffer.append("<firstName>").append(firstName).append("</firstName>");
        stringBuffer.append("<lastName>").append(lastName).append("</lastName>");
        stringBuffer.append("<phoneNumber>").append(phoneNumber).append("</phoneNumber>");
        stringBuffer.append("</client>");
        return stringBuffer.toString();

    }
}

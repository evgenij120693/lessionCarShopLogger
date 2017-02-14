package customlogger;


import models.Client;
import org.apache.log4j.PatternLayout;
import org.apache.log4j.spi.LoggingEvent;

/**
 * Created by Шмыга on 14.02.2017.
 */
public class CustomClientLayout extends PatternLayout {



    public String format(LoggingEvent event){
        Client object=(Client) event.getMessage();
        StringBuffer stringBuffer=new StringBuffer();

        String firstName=object.getFirstName();
        String lastName=object.getLastName();
        String phoneNumber=object.getPhoneNumber();

        stringBuffer.append("Object:client, ");
        stringBuffer.append("firstName: ").append(firstName).append(", ");
        stringBuffer.append("lastName: ").append(lastName).append(", ");
        stringBuffer.append("phoneNumber: ").append(phoneNumber).append("");
        return stringBuffer.toString();
    }
    public String getTheme(LoggingEvent event){
        return event.getLevel()+" in ["+event.getLoggerName()+"]";

    }
}

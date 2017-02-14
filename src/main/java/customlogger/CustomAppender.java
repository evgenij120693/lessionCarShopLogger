package customlogger;

import org.apache.log4j.AppenderSkeleton;
import org.apache.log4j.Level;
import org.apache.log4j.spi.LoggingEvent;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.FileWriter;
import java.sql.PreparedStatement;
import java.sql.Time;
import java.util.Date;
import java.util.Properties;

/**
 * Created by Шмыга on 14.02.2017.
 */
public class CustomAppender extends AppenderSkeleton{
    private String user;
    private String pass;
    private String host;
    private String port;
    private String addresFrom;
    private String addresTo;
    private String fileName;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getAddresFrom() {
        return addresFrom;
    }

    public void setAddresFrom(String addresFrom) {
        this.addresFrom = addresFrom;
    }

    public String getAddresTo() {
        return addresTo;
    }

    public void setAddresTo(String addresTo) {
        this.addresTo = addresTo;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void close() {
    }

    @Override
    public boolean requiresLayout() {
        return false;
    }


    @Override
    protected void append(LoggingEvent loggingEvent) {

            CustomClientLayout layout=(CustomClientLayout) this.getLayout();
            String str=loggingEvent.getLevel().toString()+": ["+loggingEvent.getLoggerName()+"] "+ new Date()+" "+layout.format(loggingEvent);
            try(FileWriter writer = new FileWriter(fileName, true)){
                writer.append(str);
                writer.append("\n");
                writer.flush();
            } catch(Exception e)
            {
                System.out.println("Error: not found file "+fileName);
            }

            System.out.println(str);

            String theme=layout.getTheme(loggingEvent);

            sendMessage(theme, str);
            //Class.forName(driver);
            /*Connection conn = DriverManager.getConnection(url, user, pass);

            pst= conn.prepareStatement(sql);
            pst.setString(1, event.getLevel().toString());

            pst.setString(2, layout.format(event));

            pst.executeUpdate();
            pst.close();
            conn.close();*/


    }

    public void  sendMessage( String theme,
                                   String text){

        String pass=this.pass;
        String user=this.user;
        Properties props = new Properties();
        props.put("mail.smtp.host",this.host );
        props.put("mail.smtp.socketFactory.port", this.port);
        props.put("mail.smtp.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", this.port);

        Session session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(user,pass);
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(addresFrom));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(addresTo));
            message.setSubject(theme);
            message.setText(text);

            Transport.send(message);

            System.out.println("Done");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}

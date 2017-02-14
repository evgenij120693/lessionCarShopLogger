package customlogger;

import org.apache.log4j.AppenderSkeleton;
import org.apache.log4j.Level;
import org.apache.log4j.spi.LoggingEvent;

import java.sql.PreparedStatement;

/**
 * Created by Шмыга on 14.02.2017.
 */
public class CustomAppender extends AppenderSkeleton{
    private String user;
    private String pass;
    private String driver="Client";
    private String url;

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
    public String getDriver() {
        return driver;
    }
    public void setDriver(String driver) {
        this.driver = driver;
    }
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    @Override
    public void close() {
    }

    @Override
    public boolean requiresLayout() {
        return false;
    }


    private PreparedStatement pst;
    private String sql="insert into user_records values(?,?)";

    @Override
    protected void append(LoggingEvent loggingEvent) {
        try
        {
            //if(loggingEvent.getLevel()== Level.ALL){
                System.out.println("All:"+layout.format(loggingEvent));
           // }

            //Class.forName(driver);
            /*Connection conn = DriverManager.getConnection(url, user, pass);

            pst= conn.prepareStatement(sql);
            pst.setString(1, event.getLevel().toString());

            pst.setString(2, layout.format(event));

            pst.executeUpdate();
            pst.close();
            conn.close();*/
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}

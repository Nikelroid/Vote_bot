import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class config {
    String name;
    int _id;
    String password;
    String bio;
    String pic;

        File configFile = new File("addressConfig.properties");
        public config() {
            try {
                FileReader reader = new FileReader(configFile);
                Properties props = new Properties();
                props.load(reader);

                name = props.getProperty("bot_name");
                _id = Integer.parseInt(props.getProperty("bot_id"));
                password =props.getProperty("bot_password");
                bio =props.getProperty("bot_bio");
                pic =props.getProperty("bot_pic");

                reader.close();
            } catch (FileNotFoundException ex) {
            } catch (IOException ex) {
            }
    }
}

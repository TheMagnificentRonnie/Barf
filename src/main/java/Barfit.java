import Database.DBObjects.Database;
import Database.Data.Utils.DBType;
import Database.SchemaReader;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;
import Database.VelocityGenerator;

public class Barfit {

    public static void main(String[] args) throws SQLException, IOException {


        String rootPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
        String appConfigPath = rootPath + "application.properties";


        Properties appProps = new Properties();
        appProps.load(new FileInputStream(appConfigPath));


        String schemas = appProps.getProperty("Schemas");
        String dbName = appProps.getProperty("DBName");
        String packageName = appProps.getProperty("PackageName");
        DBType dbType = (DBType.valueOf(appProps.getProperty("DBType")));
        String baseDir= appProps.getProperty("velocityTemplates");

        Database database = SchemaReader.getSchema(schemas, dbType, dbName, packageName);

        ObjectMapper objectMapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);

        objectMapper.writeValue(new File("target/database.json"), database);

        VelocityGenerator.Generate(database, baseDir);

    }


}

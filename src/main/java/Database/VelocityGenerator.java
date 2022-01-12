package Database;

import Database.DBObjects.Database;
import Database.DBObjects.Schema;
import Database.DBObjects.Table;

import Database.Data.Utils.TemplateWithGenerationClassification;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import java.io.*;
import java.util.*;

public class VelocityGenerator {

    static final String REPOSITORY = "repository";
    static final String OUTPUT_MODEL_LOCATION = "C:\\Output\\src\\main\\java\\springboot\\service\\model\\";
    public static final String SWAGGER = "swagger";
    static String POJO = "pojo";

    public static void Generate(Database database, String BaseDir) throws IOException {

        IterateSchemas(database, BaseDir);
    }


    static void IterateSchemas(Database database, String BaseDir) throws IOException {

        Template swaggerTemplate = velocityEngine.getTemplate(String.format("Templates/%s.vm", SWAGGER));

        FileWriter swaggerWriter = new FileWriter(new File(String.format(OUTPUT_MODEL_LOCATION + "Swagger\\%s.yaml", "database");

        TemplateWithGenerationClassification templateWithGenerationClassificationSwagger = new TemplateWithGenerationClassification(context, POJO, pojoTemplate, ta, pojoWriter);

        VelocityContext context = new VelocityContext();
        context.put("packageName", table.getSchema().getDatabase().getPackageName());


        for (Schema schema : database.getSchemas()
        ) {
            Generatetables(schema, BaseDir);
        }

    }


    static void Generatetables(Schema schema, String BaseDir) throws IOException {

        Properties properties = new Properties();

        properties.setProperty("resource.loader", "class");
        properties.setProperty("class.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");

        VelocityEngine velocityEngine = new VelocityEngine(properties);
        velocityEngine.init();

        velocityEngine.init();


        List<TemplateWithGenerationClassification> contextWriterList = new ArrayList<TemplateWithGenerationClassification>();


        for (Table ta : schema.getTables()
        ) {

            VelocityContext context = getTableContext(ta);

            Template pojoTemplate = velocityEngine.getTemplate(String.format("Templates/%s.vm", POJO));
            Template repoTemplate = velocityEngine.getTemplate(String.format("Templates/%s.vm", REPOSITORY));


            FileWriter pojoWriter = new FileWriter(new File(String.format(OUTPUT_MODEL_LOCATION + "Pojo\\%s.java", ta.getSchema().getName() + "_"  + ta.getName())));
            FileWriter repoWriter = new FileWriter(new File(String.format(OUTPUT_MODEL_LOCATION + "repository\\%s.java", ta.getSchema().getName() + "_"  + ta.getName()+"Repository")));
            //template.merge(context, pojoWriter);


            TemplateWithGenerationClassification templateWithGenerationClassificationPojo = new TemplateWithGenerationClassification(context, POJO, pojoTemplate, ta, pojoWriter);
            TemplateWithGenerationClassification templateWithGenerationClassificationRepo = new TemplateWithGenerationClassification(context, POJO, repoTemplate, ta, repoWriter);

            contextWriterList.add(templateWithGenerationClassificationPojo);
            contextWriterList.add(templateWithGenerationClassificationRepo);


        }

        Generate(contextWriterList);

    }

    static VelocityContext getTableContext(Table table) {

        VelocityContext context = new VelocityContext();
        context.put("packageName", table.getSchema().getDatabase().getPackageName());
        context.put("schemaName", table.getSchema().getName());
        context.put("className", table.getName());
        context.put("columns", table.getColumns());
        context.put("table", table);
        return context;


    }


    static void Generate(List<TemplateWithGenerationClassification> templateWithGenerationClassifications) throws IOException {

        for (TemplateWithGenerationClassification templateWithGenerationClassification : templateWithGenerationClassifications
        ) {
            Table ta = templateWithGenerationClassification.getTable();

            templateWithGenerationClassification.getTemplate().merge(templateWithGenerationClassification.getContext(), templateWithGenerationClassification.getFileWriter());
            templateWithGenerationClassification.getFileWriter().flush();

            templateWithGenerationClassification.getFileWriter().close();

        }

    }
}



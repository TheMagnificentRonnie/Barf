package Database.Data.Utils;


import Database.DBObjects.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.apache.velocity.Template;
import org.apache.velocity.context.Context;

import java.io.FileWriter;

@Getter
@Setter
@AllArgsConstructor
public class TemplateWithGenerationClassification {

    private Context context;
    private String typeClassification;
    private Template template;
    private Table table;
    private FileWriter fileWriter;


}

package Database.DBObjects;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter


public class Schema {
    private String name;
    @JsonBackReference
    private Database database;
    @JsonManagedReference
    ArrayList<Table> tables;


    public Schema(String SchemaName, Database database) {
        this.name = SchemaName;
        this.database = database;
        tables = new ArrayList<Table>();
    }


}

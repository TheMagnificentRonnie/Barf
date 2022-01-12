package Database.DBObjects;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;


@Getter
@Setter

public class Table {

    String name;
    Boolean PK;
    @JsonBackReference
    Schema schema;
    @JsonManagedReference
    private ArrayList<Column> columns;
    private ArrayList<Relationship> relationships;

    public Table(Schema schema, String name) {
        this.name = name;
        this.schema = schema;
        columns = new ArrayList<Column>();
        relationships = new ArrayList<Relationship>();
    }


}

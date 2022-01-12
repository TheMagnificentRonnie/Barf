package Database.DBObjects;




import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Column {
    String name;
    String type;
    String scale;
    String precision;
    String nullable;
    Boolean identity;
    String javatype;
    @JsonBackReference
    Table table;
}

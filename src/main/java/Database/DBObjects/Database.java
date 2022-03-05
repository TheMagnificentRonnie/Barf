package Database.DBObjects;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
public class Database {
    String packageName;
    String name;
    @JsonManagedReference
    private ArrayList<Table> Tables;

    public Database(String packageName, String name) {

        this.name = name;
        this.packageName = packageName;


        Tables = new ArrayList<Table>();

    }


}

package Database;


import Database.DBObjects.*;
import Database.Data.Utils.DBType;
import Database.Data.Utils.TypeUtils;

import java.sql.*;
import java.util.*;

public class SchemaReader {

    public static Database getSchema(String Schemas, DBType dbType, String name, String packageName) throws SQLException {


        Set<String> mySchemas = new HashSet<>(Arrays.asList(Schemas.split(",")));

        Database database = new Database(packageName, name);
        String connectionUrl = "jdbc:sqlserver://localhost;user=sa;password=Banana12;databaseName=AdventureWorks2019";


        Connection conn = null;
        Properties connectionProps = new Properties();
        connectionProps.put("user", "sa");
        connectionProps.put("password", "Banana12");

        conn = DriverManager.getConnection(connectionUrl,
                connectionProps);

        System.out.println("Connected to database");


        DatabaseMetaData databaseMetaData = conn.getMetaData();


        ResultSet schemas = databaseMetaData.getSchemas();

        HashMap<String, Table> tableMap = new HashMap<String, Table>();

        while (schemas.next()) {

            String theSchemaName = schemas.getString("TABLE_SCHEM");
            Schema schema = new Schema(theSchemaName, database);

            if (!mySchemas.contains(theSchemaName)) {
                continue;
            }


            ResultSet tablesRS = databaseMetaData.getTables(null, theSchemaName, "%", new String[]{"TABLE"});

            database.getSchemas().add(schema);


            while (tablesRS.next()) {
                Table table = new Table(schema, tablesRS.getString("TABLE_NAME"));
                schema.getTables().add(table);

                tableMap.put(table.getSchema().getName().concat(".").concat(table.getName()), table);

                ResultSet columns = databaseMetaData.getColumns(null, null, table.getName(), null);

                HashMap<String, Column> columnMap = new HashMap<String, Column>();

                while (columns.next()) {
                    String columnName = columns.getString("COLUMN_NAME");
                    Boolean isIdentity;
                    String isNullable = columns.getString("IS_NULLABLE");
                    ;
                    String typeName = columns.getString("TYPE_NAME");
                    if (typeName.contains(" identity")) {
                        isIdentity = true;
                    } else {
                        isIdentity = false;
                    }
                    typeName = typeName.replace(" identity", "");

                    int dataType = columns.getInt("DATA_TYPE");

                    int decimalDigits = columns.getInt("DECIMAL_DIGITS");
                    String javaType;

                    switch (dbType) {
                        case ORACLE:
                            javaType = TypeUtils.convertOracleType(typeName);
                        case SQL_SERVER:
                            javaType = TypeUtils.convertSQLServerType(typeName);
                        default:
                            javaType = TypeUtils.convertSQLServerType(typeName);
                    }


                    Column column = new Column(columnName, typeName, null, null, isNullable, isIdentity, javaType, table);
                    table.getColumns().add(column);

                    columnMap.put()

                }

                ResultSet   rs1=databaseMetaData.getPrimaryKeys(null, table.getSchema().getName(), table.getName());
                while(rs1.next())
                    System.out.println("Primary Key :"+rs1.getString(4));

            }

        }
        for (Schema schema : database.getSchemas()
        ) {

            for (Table t : schema.getTables()
            ) {
                ResultSet relations = databaseMetaData.getExportedKeys(conn.getCatalog(), t.getSchema().getName(), t.getName());


                while (relations.next()) {

                    Relationship r = new Relationship();
                    r.keypairs.add(new Keypair(relations.getString("PKCOLUMN_NAME"), relations.getString("FKCOLUMN_NAME")));
                    r.childtable = tableMap.get(relations.getString("FKTABLE_SCHEM").concat(".").concat(relations.getString("FKTABLE_NAME")));
                    t.getRelationships().add(r);


                }


            }

        }

        return database;

    }


}

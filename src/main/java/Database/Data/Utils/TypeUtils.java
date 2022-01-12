package Database.Data.Utils;

public class TypeUtils {
    public static String convertSQLServerType(String SQLType) {



        switch (SQLType) {
            case "bigint			":
                return "long";
            case "binary":
            case "geography":
            case "geometry":
            case "image			":
            case "timestamp		":
            case "udt			":
            case "varbinary		":
            case "varbinary(max)":
                return "byte[]";
            case "bit":
            case "Flag":
                return "boolean";
            case "date":
                return "java.sql.Date";
            case "datetime3":
            case "smalldatetime":
            case "datetime2":
                return "java.sql.Timestamp";
            case "datetimeoffset2":
                return "microsoft.sql.DateTimeOffset";
            case "decimal":
            case "smallmoney":
            case "numeric":
            case "money":
                return "java.math.BigDecimal";
            case "float":
                return "double";
            case "int":
                return "int";
            case "real":
                return "float";
            case "smallint":
            case "tinyint":
                return "short";
            case "time":
                return "java.sql.Time";
            case "sqlvariant":
                return "Object";
            case "NVARCHAR":
            case "nvarchar(max)":
            case "nvarchar":
            case "ntext":
            case "LONGNVARCHAR":
            case "nchar":
            case "NCHAR":
            case "text":
            case "uniqueidentifie":
            case "varchar":
            case "varchar(max)":
            case "char":
            case "xml":
            default:
                return "String";
        }
    }

    public static String convertOracleType(String SQLType) {
        switch (SQLType) {
            case "CHAR":
            case "CHARACTER":
            case "LONG":
            case "STRING":
            case "VARCHAR":
            case "VARCHAR2":
                return "java.lang.String";
            case "NCHAR":
            case "NVARCHAR2":
                return "java.lang.String";
            case "NCLOB":
                return "oracle.sql.NCLOB";
//                case "RAW":
//                    nm
            case "LONG RAW":
                return "byte[]";
            case "BINARY_INTEGER":
            case "NATURAL":
            case "NATURALN":
            case "PLS_INTEGER":
            case "POSITIVE":
            case "POSITIVEN":
            case "SIGNTYPE":
            case "INT":
            case "INTEGER":
            case "SMALLINT":
                return "int";
            case "DEC":
            case "DECIMAL":
            case "NUMBER":
            case "NUMERIC":
                return "java.math.BigDecimal";
            case "DOUBLE PRECISION":
            case "FLOAT":
                return "double";
            case "REAL":
                return "float";
            case "DATE":
                return "java.sql.Timestamp";
            case "ROWID":
            case "UROWID":
                return "oracle.sql.ROWID";
            case "BOOLEAN":
                return "boolean (note 3)";
            case "CLOB":
                return "java.sql.Clob";
            case "BLOB":
                return "java.sql.Blob";
            case "BFILE":
                return "oracle.sql.BFILE";
            default:
                return "String";

        }
    }
}

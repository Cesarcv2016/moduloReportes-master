package conexion;
public class configConexion extends conexion{
    public configConexion()
    {
        super();
    }
    public void conectar()throws Exception
        {
            //Direccion IP del servidor MySQL
            String servidor = "localhost";
            //Nombre del usuario
            String usuario = "root";
            //Contraseña
            String clave = "123456";
            //Puerto
            String puerto = "3306";
            //Nombre de la base de datos
            String nombreBaseDatos = "dbrestaurante";
            //Driver
            String driver = "com.mysql.jdbc.Driver";
            
            String url = "jdbc:mysql://" + servidor +":"+puerto +"/" + nombreBaseDatos;
            super.open( url, driver,  usuario, clave );
        }
}

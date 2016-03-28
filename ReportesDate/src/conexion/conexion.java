package conexion;
import java.sql.*;
public class conexion {
    
    private Connection con;
    private Statement cmd;
    private ResultSet rec;
    
    public conexion()
    {
        con = null;
        rec = null;
        cmd = null;
    }
    public Connection getConnection() {
        return con;
    }
    public void open(String ip, String driver, String user, String password) throws conException
    {        
        String url;
        try
        {
            url =  ip;
            Class.forName( driver );
            con = DriverManager.getConnection( url, user, password );
        }
        catch( ClassNotFoundException ex )
        {
            throw new conException( ex.toString( ) );
        }
        catch ( SQLException ex )
        {
            throw new conException( ex.toString( ) );
        }
    }
    /*
     *Método para abrir  conexión con la bd
     *
     */
    public void open ( Connection sCon  )throws conException
    {
        if( sCon ==  null) {
            throw new conException(" Error conn ");
        }
        con = sCon;
    }    
    /*
    *Método para ejecutar un query
    */
    public void execQuery( String sql )throws conException
    {

        if ( con == null ) {
            throw  new conException( "No conectado" );
        }
        try
        {
            cmd = con.createStatement();
            rec = cmd.executeQuery( sql );
        }
        catch( SQLException ex )
        {
            throw new conException ( ex.toString( ) );
        }
    }

    /*
    *Método para ejecutar una actualización a la BD
    */
    public int execSql( String sql )throws conException
    {
     	if ( con == null ) {
            throw  new conException( "No conectado" );
        }
        try
        {
            cmd = con.createStatement();
            return  cmd.executeUpdate( sql );
        }
        catch( SQLException ex )
        {
            throw new conException ( ex.toString( ) );
        }
    }

     /*
    *Método para mover el apuntador de result Set
    */
    public boolean next( )throws conException
    {
        if ( con == null || rec == null ) {
            throw  new conException( "No conectado" );
        }
        try
        {
            return rec.next( );
        }
        catch( SQLException ex )
        {
            throw new conException ( ex.toString( ) );
        }
    }
    /*
    *Método para obtener una columna de tipo String
    */
    public String getColString( int col )throws conException
    {
        if ( con == null || rec == null ) {
            throw  new conException( "No conectado" );
        }
        try
        {
            return rec.getString( col );
        }
        catch( SQLException ex )
        {
            throw new conException ( ex.toString( ) );
        }
    }
    /*
    *Método para obtener una columna de tipo double
    */
      public double getColDouble( int col )throws conException{
        if ( con == null || rec == null ) {
            throw  new conException( "No conectado" );
        }
        try
        {
            return rec.getDouble( col );

        }
        catch( SQLException ex )
        {
            throw new conException ( ex.toString( ) );
        }
    }
    
    /*
    *Método para obtener una columna de tipo int
    */
    public long getColLog( int col )throws conException
    {
        if ( con == null || rec == null ) {
            throw  new conException( "No conectado" );
        }
        try
        {
            return rec.getLong( col );
        }
        catch( SQLException ex )
        {
            throw new conException ( ex.toString( ) );
        }
     }
     /*
    *Método para obtener una columna de tipo int
    */
    public int getColInt( int col )throws conException
    {
        if ( con == null || rec == null ) {
            throw  new conException( "No conectado" );
        }
        try
        {
            return rec.getInt( col );
        }
        catch( SQLException ex )
        {
            throw new conException ( ex.toString( ) );
        }

    }

    /*
    *Método para obtener una columna de tipo Object
    */
    public Date getColDate( int col )throws conException
    {
        if ( con == null || rec == null ) {
            throw  new conException( "No conectado" );
        }
        try
        {
            
            return rec.getDate(col);
            
        }
        catch( SQLException ex )
        {
            throw new conException ( ex.toString( ) );
        }

    }

    public ResultSetMetaData getMetaData()throws conException
    {
        if ( con == null || rec == null ) {
            throw  new conException( "No conectado" );
        }
        try
        {
            return rec.getMetaData();

        }
        catch( SQLException ex )
        {
            throw new conException ( ex.toString( ) );
        }

    }
	/*
	*Método cerrar el query
	*/
    public void closeQuery( )throws conException
    {
        if ( con == null || rec == null ) {
            throw  new conException( "No conectado" );
        }
        try
        {
            rec.close( );
        }
        catch( SQLException ex )
        {
            throw new conException ( ex.toString( ) );
        }
    }
    /*
    *Método para cerrar la conexión
    */
	public void close( )throws conException{
        if ( con == null ) {
            throw  new conException( "No conectado" );
        }
        try
        {
            con.close( );
        }
        catch( SQLException ex )
        {
            throw new conException ( ex.toString( ) );
        }
    }
    
}
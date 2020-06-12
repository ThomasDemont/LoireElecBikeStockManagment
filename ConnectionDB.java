import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ConnectionDB {
	
	static String URL = "jdbc:mysql://localhost:3306/loireelecbikestockmanagament";
	static String user = "root";
	static String pwd = "";
	
	public ConnectionDB()
	{		
		try
		{
		//load and register JDBC Driver
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		}	
		
		
		catch (Exception e)
		{
			e.printStackTrace();
		}

	}
	
	public static ArrayList<String> listBikes(String urlString, String username, String password)
	{
    	ArrayList<String> listBikesString = new ArrayList<String>();

		//open a connection to database
		try(Connection con = DriverManager.getConnection(URL,user, pwd))
		{
		//create statement object
		Statement statment = con.createStatement();
	
		//execute statement object and return result to ResultSet
		ResultSet rs = statment.executeQuery("select * from bikes");
		while (rs.next())
		{
			listBikesString.add(rs.getInt("id_Bike") + "\t");			
		}
	
		//close the connection
		rs.close();
		statment.close();
		con.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	
		return listBikesString;
	}
	
	public static ArrayList<String> listBikesStatus(String urlString, String username, String password)
	{
    	ArrayList<String> listBikesStatusString = new ArrayList<String>();

		//open a connection to database
		try(Connection con = DriverManager.getConnection(URL,user, pwd))
		{
		//create statement object
		Statement statment = con.createStatement();
	
		//execute statement object and return result to ResultSet
		ResultSet rs = statment.executeQuery("select * from bikes");
		while (rs.next()) {
			if(rs.getBoolean("statusBike") == true)
			{
				listBikesStatusString.add("Available");
			}
			if(rs.getBoolean("statusBike") == false)
			{
				listBikesStatusString.add("Taken");
			}
		}
	
		//close the connection
		rs.close();
		statment.close();
		con.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	
		return listBikesStatusString;
	}
	
	public static ArrayList<String> listBikesAvailable(String urlString, String username, String password)
	{
		ArrayList<String> listAvailableBikesString = new ArrayList<String>();
		/*
		open a connection to database
		getConnection(url,username,password);
		 */
		try(Connection con = DriverManager.getConnection(URL,user, pwd))
		{
		//create statement object
		Statement statment = con.createStatement();
	
		//execute statement object and return result to ResultSet
		ResultSet rs = statment.executeQuery("select * from bikes Where bikes.statusBike LIKE true");
		while (rs.next()) {
			listAvailableBikesString.add(rs.getInt("id_Bike") + "\t");
			
			System.out.print(rs.getInt("id_Bike") + "\t");
		}
	
		//close the connection
		rs.close();
		statment.close();
		con.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	
		return listAvailableBikesString;
	}
	
	public static ArrayList<String> listBikesStatusAvailable(String urlString, String username, String password)
	{
    	ArrayList<String> listBikesStatusString = new ArrayList<String>();

		//open a connection to database
		try(Connection con = DriverManager.getConnection(URL,user, pwd))
		{
		//create statement object
		Statement statment = con.createStatement();
	
		//execute statement object and return result to ResultSet
		ResultSet rs = statment.executeQuery("select * from bikes Where bikes.statusBike LIKE true");
		while (rs.next()) {
			if(rs.getBoolean("statusBike") == true)
			{
				listBikesStatusString.add("Available");
			}
			if(rs.getBoolean("statusBike") == false)
			{
				listBikesStatusString.add("Taken");
			}
		}
	
		//close the connection
		rs.close();
		statment.close();
		con.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	
		return listBikesStatusString;
	}
	
	public static int countBikes(String urlString, String username, String password)
	{
		int numberBike = 0;   	
		try (Connection connection = DriverManager.getConnection(urlString, username, password)){

				Statement s = connection.createStatement();
				ResultSet rs = s.executeQuery("select COUNT(dbo.Bikes.Id_B) AS 'Available/Taken' from dbo.Bikes GROUP BY dbo.Bikes.Status_B");
				while (rs.next()) {
					numberBike = rs.getInt("Available/Taken");
					System.out.print(rs.getInt("Available/Taken") + "\t");

				}
				
				rs.close();
				s.close();
				
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return numberBike;
	}
	
    public static void addBikes(String urlString, String username, String password, int ID) {
		
	try (Connection connection = DriverManager.getConnection(urlString, username, password)){
				
		Statement s = connection.createStatement();
		ResultSet rs = s.executeQuery("Insert Into dbo.Bikes values (" + ID + " , true");
		rs.close();
		s.close();
				
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
    
    public static void deleteBikes(String urlString, String username, String password, int ID)
    {	
		try (Connection connection = DriverManager.getConnection(urlString, username, password)){				
				
				Statement s = connection.createStatement();
				ResultSet rs = s.executeQuery("Delete from dbo.Bikes Where dbo.Bikes.id_Bike = " + ID);
				rs.close();
				s.close();
				
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
    
    public static void changeBikesStatus(String urlString, String username, String password, int ID, String status)
    {	
		try (Connection connection = DriverManager.getConnection(urlString, username, password)){
				
				Statement s = connection.createStatement();
				if(status == "available")
				{
					ResultSet rs = s.executeQuery("Update dbo.Bikes SET dbo.Bikes.Status_B = 'true' Where dbo.Bikes.Id_B = " + ID);
					rs.close();
					s.close();
				}else { 			
				ResultSet rs = s.executeQuery("Update dbo.Bikes SET dbo.Bikes.Status_B = 'false' Where dbo.Bikes.Id_B = " + ID);
				rs.close();
				s.close();
				}

				
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
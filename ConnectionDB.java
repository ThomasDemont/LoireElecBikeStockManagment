import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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
	
	public static ArrayList<String> listBikes()
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
	
	public static ArrayList<String> listBikesStatus()
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
	
	public static ArrayList<String> listBikesAvailable()
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
	
	public static ArrayList<String> listBikesStatusAvailable()
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
	
    public static ArrayList<String> searchBike(String BikeId)
    {
    	ArrayList<String> listBikesSearchString = new ArrayList<String>();

		//open a connection to database
		try(Connection con = DriverManager.getConnection(URL,user, pwd))
		{
			//create statement object
			PreparedStatement stmt = con.prepareStatement("SELECT * FROM bikes WHERE bikes.id_Bike LIKE ?");
			stmt.setString(1, "%" + BikeId + "%");
			ResultSet rs = stmt.executeQuery();
			
			//execute statement object and return result to ResultSet
			while (rs.next())
			{
				listBikesSearchString.add(rs.getInt("id_Bike") + "\t");
			}
			//close the connection
			rs.close();
			con.close();
		}
		
		catch (SQLException e) {
			e.printStackTrace();
		}
		return listBikesSearchString;
	}
    public static ArrayList<String> searchBikeStatus(String BikeId)
    {
    	ArrayList<String> listBikesSearchString = new ArrayList<String>();

		//open a connection to database
		try(Connection con = DriverManager.getConnection(URL,user, pwd))
		{
			//create statement object
			PreparedStatement stmt = con.prepareStatement("SELECT * FROM bikes WHERE bikes.id_Bike LIKE ?");
			stmt.setString(1, "%" + BikeId + "%");
			ResultSet rs = stmt.executeQuery();
			
			//execute statement object and return result to ResultSet
			//ResultSet rs = statment.executeQuery("select * from bikes Where bikes.id_Bike LIKE " + BikeId);
			while (rs.next())
			{
				if(rs.getInt("statusBike") == 1)
				{
					listBikesSearchString.add("Available");
				}else listBikesSearchString.add("Taken");
			}
			//close the connection
			rs.close();
			con.close();
		}
		
		catch (SQLException e) {
			e.printStackTrace();
		}
		return listBikesSearchString;
	}

	
    public static void addBikes(int ID) {
		
	try (Connection connection = DriverManager.getConnection(URL,user, pwd)){
				
		Statement s = connection.createStatement();
		String sql = "INSERT INTO bikes VALUES (" + ID + " , true)";
		int updateCount = s.executeUpdate(sql);
				
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
    
    public static void deleteBikes(String ID)
    {	
		try (Connection connection = DriverManager.getConnection(URL, user, pwd)){				
				
				Statement s = connection.createStatement();
				
				String sql = "DELETE FROM bikes WHERE bikes.id_Bike = " + ID;
				int updateCount = s.executeUpdate(sql);				
				
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
    
    public static void changeBikesStatus(String ID, String status)
    {	
		try (Connection connection = DriverManager.getConnection(URL,user, pwd)){
				
				String sql ="";
				Statement s = connection.createStatement();
				if(status == "Available")
				{
					sql = "UPDATE bikes SET bikes.statusBike = '1' WHERE bikes.id_Bike = " + ID;
				}else
				{ 
					sql = "UPDATE bikes SET bikes.statusBike = '0' WHERE bikes.id_Bike = " + ID;
				}
				int updateCount = s.executeUpdate(sql);

				
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
    
    
    
    public static ArrayList<Article> listArticles()
	{
    	ArrayList<Article> listArticle = new ArrayList<Article>();

		//open a connection to database
		try(Connection con = DriverManager.getConnection(URL,user, pwd))
		{
		//create statement object
		Statement statment = con.createStatement();
	
		//execute statement object and return result to ResultSet
		ResultSet rs = statment.executeQuery("select * from shop");
		while (rs.next())
		{
			int id = rs.getInt("id_Item");
			String name = rs.getString("name_Item");
			listArticle.add(new Article(id, name));
		}
	
		//close the connection
		rs.close();
		statment.close();
		con.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	
		return listArticle;
	}
    
    public static ArrayList<Article> searchArticle(String Article)
    {
    	ArrayList<Article> listArticleSearch = new ArrayList<Article>();

		//open a connection to database
		try(Connection con = DriverManager.getConnection(URL,user, pwd))
		{
			//create statement object
			System.out.println(Article);
			PreparedStatement statmt = con.prepareStatement("SELECT * FROM shop WHERE shop.id_Item LIKE ? OR shop.name_Item LIKE ?");
			statmt.setString(1, "%" + Article + "%");
			statmt.setString(2, "%" + Article + "%");
			ResultSet result = statmt.executeQuery();
			
			//execute statement object and return result to ResultSet
			while (result.next())
			{
				int id = result.getInt("id_Item");
				String name = result.getString("name_Item");
				System.out.println(id + "  " + name);
				listArticleSearch.add(new Article(id, name));
			}
			//close the connection
			result.close();
			con.close();
		}
		
		catch (SQLException e) {
			e.printStackTrace();
		}
		return listArticleSearch;
	}
    
    public static void deleteArticle(int ID)
    {	
		try (Connection connection = DriverManager.getConnection(URL, user, pwd)){				
				
				Statement s = connection.createStatement();
				
				String sql = "DELETE FROM shop WHERE shop.id_Item = " + ID;
				int updateCount = s.executeUpdate(sql);				
				
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
    
    public static void addArticle(int id, String name) {
		
    	try (Connection connection = DriverManager.getConnection(URL,user, pwd)){
    				
    		PreparedStatement statmt = connection.prepareStatement("INSERT INTO shop VALUES ( ?, ?)");
			statmt.setInt(1, id);
			statmt.setString(2, name);
			statmt.execute();
			
    		
    		/*Statement s = connection.createStatement();
    		String sql = "INSERT INTO shop VALUES (" + id + ", " + name + ")";
    		int updateCount = s.executeUpdate(sql);
    		*/		
    		} catch (SQLException e) {
    			e.printStackTrace();
    		}
    	}
   
}
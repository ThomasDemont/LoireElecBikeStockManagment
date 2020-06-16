import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Class managing the Connection and retrieval of data from the database
 */
public class ConnectionDB {
	
	static String URL = "jdbc:mysql://localhost:3306/loireelecbikestockmanagament";
	static String user = "root";
	static String pwd = "";
	
	/**
	 * Constructor
	 */
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
	
	/**
	 * Method that returns an arrayList of String containing the id of each bikes
	 * @return ArrayList<String>
	 */
	public static ArrayList<String> listBikes()
	{
    	ArrayList<String> listBikesString = new ArrayList<String>();

		//open a connection to database
		try(Connection con = DriverManager.getConnection(URL,user, pwd))
		{
		//create statement object
		Statement statment = con.createStatement();
	
		//execute statement object and return result to ResultSet rs
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

	/**
	 * Method that returns an arrayList of String containing the status of each bikes
	 * @return ArrayList<String>
	 */
	public static ArrayList<String> listBikesStatus()
	{
    	ArrayList<String> listBikesStatusString = new ArrayList<String>();

		try(Connection con = DriverManager.getConnection(URL,user, pwd))
		{

			Statement statment = con.createStatement();
			ResultSet rs = statment.executeQuery("select * from bikes");
			while (rs.next())
			{
				if(rs.getBoolean("statusBike") == true)
				{
					listBikesStatusString.add("Available");
				}
				if(rs.getBoolean("statusBike") == false)
				{
					listBikesStatusString.add("Taken");
				}
			}
			rs.close();
			statment.close();
			con.close();
		}
		catch (SQLException e) {
		e.printStackTrace();
		}
	
		return listBikesStatusString;
	}

	/**
	 * Method that returns an arrayList of String containing the id of each bikes currently available
	 * @return ArrayList<String>
	 */
	public static ArrayList<String> listBikesAvailable()
	{
		ArrayList<String> listAvailableBikesString = new ArrayList<String>();
		
		try(Connection con = DriverManager.getConnection(URL,user, pwd))
		{
			
			Statement statment = con.createStatement();
			ResultSet rs = statment.executeQuery("select * from bikes Where bikes.statusBike LIKE true");
			while (rs.next())
			{
				listAvailableBikesString.add(rs.getInt("id_Bike") + "\t");
				
				System.out.print(rs.getInt("id_Bike") + "\t");
			}
		
			rs.close();
			statment.close();
			con.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	
		return listAvailableBikesString;
	}

	/**
	 * Method that returns an arrayList of String containing the status of each bikes currently available
	 * @return ArrayList<String>
	 */
	public static ArrayList<String> listBikesStatusAvailable()
	{
    	ArrayList<String> listBikesStatusString = new ArrayList<String>();

		try(Connection con = DriverManager.getConnection(URL,user, pwd))
		{
			
			Statement statment = con.createStatement();
			ResultSet rs = statment.executeQuery("select * from bikes Where bikes.statusBike LIKE true");
			while (rs.next())
			{
				if(rs.getBoolean("statusBike") == true)
				{
					listBikesStatusString.add("Available");
				}
				if(rs.getBoolean("statusBike") == false)
				{
					listBikesStatusString.add("Taken");
				}
			}
			rs.close();
			statment.close();
			con.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	
		return listBikesStatusString;
	}

	/**
	 * Method that returns an arrayList of String containing the id of the bikes with an id that contains the String in parameter
	 * @return ArrayList<String>
	 */
    public static ArrayList<String> searchBike(String BikeId)
    {
    	ArrayList<String> listBikesSearchString = new ArrayList<String>();

		try(Connection con = DriverManager.getConnection(URL,user, pwd))
		{
			PreparedStatement stmt = con.prepareStatement("SELECT * FROM bikes WHERE bikes.id_Bike LIKE ?");
			stmt.setString(1, "%" + BikeId + "%");
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next())
			{
				listBikesSearchString.add(rs.getInt("id_Bike") + "\t");
			}
			rs.close();
			con.close();
		}
		
		catch (SQLException e) {
			e.printStackTrace();
		}
		return listBikesSearchString;
	}

	/**
	 * Method that returns an arrayList of String containing the status of the bikes with an id that contains the String in parameter
	 * @return ArrayList<String>
	 */
    public static ArrayList<String> searchBikeStatus(String BikeId)
    {
    	ArrayList<String> listBikesSearchString = new ArrayList<String>();

		try(Connection con = DriverManager.getConnection(URL,user, pwd))
		{
			PreparedStatement stmt = con.prepareStatement("SELECT * FROM bikes WHERE bikes.id_Bike LIKE ?");
			stmt.setString(1, "%" + BikeId + "%");
			ResultSet rs = stmt.executeQuery();
			while (rs.next())
			{
				if(rs.getInt("statusBike") == 1)
				{
					listBikesSearchString.add("Available");
				}else listBikesSearchString.add("Taken");
			}
			rs.close();
			con.close();
		}
		
		catch (SQLException e) {
			e.printStackTrace();
		}
		return listBikesSearchString;
	}

	/**
	 * Method that adds a bike into the database by adding it's id
	 * @return void
	 */
    public static void addBikes(int ID) {
		
	try (Connection connection = DriverManager.getConnection(URL,user, pwd))
	{
				
		Statement s = connection.createStatement();
		String sql = "INSERT INTO bikes VALUES (" + ID + " , true)";
		int updateCount = s.executeUpdate(sql);
				
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Method that deletes the bike with the id in parameter
	 * @return void
	 */
    public static void deleteBikes(String ID)
    {	
		try (Connection connection = DriverManager.getConnection(URL, user, pwd))
		{				
				
				Statement s = connection.createStatement();
				
				String sql = "DELETE FROM bikes WHERE bikes.id_Bike = " + ID;
				int updateCount = s.executeUpdate(sql);				
				
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Method that changes the bike status with the id in parameter
	 * @return void
	 */
    public static void changeBikesStatus(String ID, String status)
    {	
		try (Connection connection = DriverManager.getConnection(URL,user, pwd))
		{
				
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
    

	/**
	 * Method that return an array with all articles in the database
	 * @return ArrayList<Article>
	 */
    public static ArrayList<Article> listArticles()
	{
    	ArrayList<Article> listArticle = new ArrayList<Article>();

		try(Connection con = DriverManager.getConnection(URL,user, pwd))
		{
			Statement statment = con.createStatement();
			ResultSet rs = statment.executeQuery("select * from shop");
			while (rs.next())
			{
				int id = rs.getInt("id_Item");
				String name = rs.getString("name_Item");
				listArticle.add(new Article(id, name));
			}
		
			rs.close();
			statment.close();
			con.close();
		}
			catch (SQLException e) {
			e.printStackTrace();
		}
	
		return listArticle;
	}


	/**
	 * Method that return an array with all articles in the database who contains the id in parameter
	 * @return ArrayList<Article>
	 */
    public static ArrayList<Article> searchArticle(String Article)
    {
    	ArrayList<Article> listArticleSearch = new ArrayList<Article>();

		try(Connection con = DriverManager.getConnection(URL,user, pwd))
		{
			System.out.println(Article);
			PreparedStatement statmt = con.prepareStatement("SELECT * FROM shop WHERE shop.id_Item LIKE ? OR shop.name_Item LIKE ?");
			statmt.setString(1, "%" + Article + "%");
			statmt.setString(2, "%" + Article + "%");
			ResultSet result = statmt.executeQuery();
			
			while (result.next())
			{
				int id = result.getInt("id_Item");
				String name = result.getString("name_Item");
				System.out.println(id + "  " + name);
				listArticleSearch.add(new Article(id, name));
			}
			result.close();
			con.close();
		}
		
		catch (SQLException e) {
			e.printStackTrace();
		}
		return listArticleSearch;
	}

	/**
	 * Method that deletes an article with  its id in parameter
	 * @return void
	 */
    public static void deleteArticle(int ID)
    {	
		try (Connection connection = DriverManager.getConnection(URL, user, pwd))
		{				
				
				Statement s = connection.createStatement();
				
				String sql = "DELETE FROM shop WHERE shop.id_Item = " + ID;
				int updateCount = s.executeUpdate(sql);				
				
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Method that adds an article with its id and name in parameter
	 * @return void
	 */
    public static void addArticle(int id, String name) {
		
    	try (Connection connection = DriverManager.getConnection(URL,user, pwd))
    	{
    		PreparedStatement statmt = connection.prepareStatement("INSERT INTO shop VALUES ( ?, ?)");
			statmt.setInt(1, id);
			statmt.setString(2, name);
			statmt.execute();
				
    	} catch (SQLException e) {
    			e.printStackTrace();
    	}
    }
   
}
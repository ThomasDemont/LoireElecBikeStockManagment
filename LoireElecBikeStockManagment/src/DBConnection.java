import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.sql.*;

public class DBConnection {   
	//Constants used to get access to the database   
        
        private static final String  driver = "jdbc:sqlserver://localhost:1433";;
        private static final String  databaseName = ";databaseName=Bikes";
        
        private static String  userName = ";user=sa";
        private static String password = ";password=azerty";
        
        
        private static final String URL =  driver + databaseName + userName + password;
     
     
   
   
    private DatabaseMetaData dma;
    private static Connection con;
    
    // an instance of the class is generated
    private static DBConnection  instance = null;

    
    
     public static void main(String[] args) {

      listBikes(URL, userName, password);
      closeConnection();
        }
     
     
    // the constructor is private to ensure that only one object of this class is created
    private DBConnection()
    {

        try{
            //load of driver
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            System.out.println("Driver class loaded ok");
          
        }
        catch(Exception e){
            System.out.println("Cannot find the driver");
            System.out.println(e.getMessage());
        }
        try{
            //connection to the database
            con = DriverManager.getConnection(URL, userName, password);
            con.setAutoCommit(true);
            dma = con.getMetaData(); // get meta data
            System.out.println("Connection to " + dma.getURL());
            System.out.println("Driver " + dma.getDriverName());
            System.out.println("Database product name " + dma.getDatabaseProductName());
        }//end try
        catch(Exception e){
            System.out.println("Problems with the connection to the database:");
            System.out.println(e.getMessage());
            System.out.println(URL);
        }//end catch
    }//end  constructor
	   
  //closeDb: closes the connection to the database
    public static void closeConnection()
    {
       	try{
            con.close();
            instance= null;
            System.out.println("The connection is closed");
        }
         catch (Exception e){
            System.out.println("Error trying to close the database " +  e.getMessage());
         }
    }//end closeDB
		
    //getDBcon: returns the singleton instance of the DB connection
    public Connection getDBcon()
    {
       return con;
    }
    //getDBcon: returns the singleton instance of the DB connection
    public static boolean instanceIsNull()
    {
       return (instance == null);
    }    
    //this method is used to get the instance of the connection
    public static DBConnection getInstance()
    {
        if (instance == null)
        {
          instance = new DBConnection();
        }
        return instance;
    }

    /**
     * Method that returns an ArrayList<String> filled with the informations of each bike
     * @param urlString
     * @param username
     * @param password
     * @return
     */
    public static ArrayList<String> listBikes(String urlString, String username, String password) {
    	ArrayList<String> listBikesString = null;
		
		try (Connection connection = DriverManager.getConnection(urlString, username, password)){
				DatabaseMetaData metadata = connection.getMetaData();
				
				
				Statement s = connection.createStatement();
				ResultSet rs = s.executeQuery("select * from dbo.Bikes");

				while (rs.next()) {
					listBikesString.add(rs.getInt("Id_B") + "\t");
					listBikesString.add(rs.getString("Status_B"));
					
					System.out.print(rs.getInt("Id_B") + "\t");
					System.out.println(rs.getString("Status_B"));
				}
				
				rs.close();
				s.close();
				
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listBikesString;
	}
    
    /**
     * Method that returns the number of bike in the database
     * @param urlString
     * @param username
     * @param password
     * @return
     */
    public static int countBikes(String urlString, String username, String password) {
		int numberBike = 0;   	
		try (Connection connection = DriverManager.getConnection(urlString, username, password)){
				DatabaseMetaData metadata = connection.getMetaData();
				
				
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
    
    /**
     * Method to add bike into the database
     * @param urlString
     * @param username
     * @param password
     * @param ID
     */
    public static void addBikes(String urlString, String username, String password, int ID) {
		
	try (Connection connection = DriverManager.getConnection(urlString, username, password)){
		DatabaseMetaData metadata = connection.getMetaData();		
				
		Statement s = connection.createStatement();
		ResultSet rs = s.executeQuery("Insert Into dbo.Bikes values (" + ID + " , 'available'");
		rs.close();
		s.close();
				
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
    
    /**
     * Method that returns an array filled with the informations of the bikes
     * @param urlString
     * @param username
     * @param password
     * @return
     */
    public static ArrayList<String> listBikesA(String urlString, String username, String password) {
    	ArrayList<String> listAvailableBikesString = null;
    	   
		try (Connection connection = DriverManager.getConnection(urlString, username, password)){
				DatabaseMetaData metadata = connection.getMetaData();
				
				
				Statement s = connection.createStatement();
				ResultSet rs = s.executeQuery("Select * from dbo.Bikes Where dbo.Bikes.Status_B LIKE 'available'");
                while (rs.next()) {
                    listAvailableBikesString.add(rs.getInt("ID_B") + "\t");
                    listAvailableBikesString.add(rs.getString("Status_B"));
                    
					System.out.print(rs.getInt("ID_B") + "\t");
					System.out.println(rs.getString("Status_B"));

				}
				rs.close();
				s.close();
				
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listAvailableBikesString;
	}
    
    /**
     * Method that return an ArrayList<String> filled with the items of the shop
     * @param urlString
     * @param username
     * @param password
     * @return
     */
    public static ArrayList<String> listShop(String urlString, String username, String password) {
    ArrayList<String> listArticle = null;
		
	try (Connection connection = DriverManager.getConnection(urlString, username, password)){
		DatabaseMetaData metadata = connection.getMetaData();
				
				
		Statement s = connection.createStatement();
		ResultSet rs = s.executeQuery("Select * from dbo.Shop ORDER BY dbo.Shop.nameItem ASC");
		while (rs.next()) {
			listArticle.add(rs.getInt("Id_Item") + "\t");
            listArticle.add(rs.getString("name_Item")+ "\t");
            listArticle.add(rs.getInt("quantity_Item") + "\t");
                                	
            System.out.print(rs.getInt("Id_Item") + "\t");
            System.out.println(rs.getString("name_Item")+ "\t");
            System.out.print(rs.getInt("quantity_Item") );

			}
		rs.close();
		s.close();
				
		} catch (SQLException e) {
			e.printStackTrace();
		}
	return listArticle;
	}
    
    /**
     * Method that return an ArrayList<String> filled with the items of the shop according to the search field passed into parameter
     * @param urlString
     * @param username
     * @param password
     * @param textToSearch
     * @return
     */
    public static ArrayList<String> searchShop(String urlString, String username, String password, String textToSearch) {
    	ArrayList<String> listArticleSearch = null;
		try (Connection connection = DriverManager.getConnection(urlString, username, password)){
				DatabaseMetaData metadata = connection.getMetaData();
						
				Statement s = connection.createStatement();
				ResultSet rs = s.executeQuery("Select * from dbo.Shop Where dbo.Shop.name_Item LIKE '" + textToSearch + "'");
					while (rs.next()) {
						listArticleSearch.add(rs.getInt("Id_Item") + "\t");
						listArticleSearch.add(rs.getString("name_Item")+ "\t");
						listArticleSearch.add(rs.getInt("quantity_Item") + "\t");
	                    
						System.out.print(rs.getInt("Id_Item") + "\t");
                        System.out.println(rs.getString("name_Item")+ "\t");
                        System.out.print(rs.getInt("quantity_Item") );

				}
				rs.close();
				s.close();
				
		} catch (SQLException e) {
			e.printStackTrace();
		}
	return listArticleSearch;
	}
       
       
    /**
     * Method that adds an article whose informations are passed in parameter to the database
     * @param urlString
     * @param username
     * @param password
     * @param ID
     * @param name
     * @param quantity
     */
    public static void addArticle(String urlString, String username, String password, int ID, String name, int quantity) {	
    	try (Connection connection = DriverManager.getConnection(urlString, username, password)){
    		DatabaseMetaData metadata = connection.getMetaData();
				
				
    		Statement s = connection.createStatement();
    		ResultSet rs = s.executeQuery("Insert Into dbo.Shop values (" + ID + " , '" + name + "' , " + quantity + ")");
    		rs.close();
    		s.close();
				
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
    
    /**
     * Method to delete an item from the table Shop
     * @param urlString
     * @param username
     * @param password
     * @param ID
     */
    public static void deleteShop(String urlString, String username, String password, int ID) {	
		try (Connection connection = DriverManager.getConnection(urlString, username, password)){
				DatabaseMetaData metadata = connection.getMetaData();
				
				
				Statement s = connection.createStatement();
				ResultSet rs = s.executeQuery("Delete from dbo.Shop Where dbo.Shop.Id_Item = " + ID);
				rs.close();
				s.close();
				
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
    
       
    /**
     * Method that deletes the bike corresponding to the informations passed in parameter
     * @param urlString
     * @param username
     * @param password
     * @param ID
     */
    public static void deleteBikes(String urlString, String username, String password, int ID) {	
		try (Connection connection = DriverManager.getConnection(urlString, username, password)){
				DatabaseMetaData metadata = connection.getMetaData();
				
				
				Statement s = connection.createStatement();
				ResultSet rs = s.executeQuery("Delete from dbo.Bikes Where dbo.Bikes.Id_Item = " + ID);
				rs.close();
				s.close();
				
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
       
    /**
     * Method to change the bike Status
     * @param urlString
     * @param username
     * @param password
     * @param ID
     * @param status
     */
    public static void changeBikesStatus(String urlString, String username, String password, int ID, String status) {	
		try (Connection connection = DriverManager.getConnection(urlString, username, password)){
				DatabaseMetaData metadata = connection.getMetaData();
				
				
				Statement s = connection.createStatement();
				ResultSet rs = s.executeQuery("Update dbo.Bikes SET dbo.Bikes.Status_B = '" + status + "' Where dbo.Bikes.Id_B = " + ID);
				rs.close();
				s.close();
				
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}//end DbConnection
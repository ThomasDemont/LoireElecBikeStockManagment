public class Article 
    {
    	int idArticle;
    	String nameArticle;
    	
    	public Article(int id, String name)
    	{
    		this.idArticle = id;
    		this.nameArticle = name;
    	}
    	
    	public String toString()
    	{
    		String ArticleToString = this.nameArticle + " \t " + this.idArticle;
    		return ArticleToString;
    	}
    }
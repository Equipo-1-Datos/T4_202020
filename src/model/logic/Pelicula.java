package model.logic;

public class Pelicula implements Comparable <Pelicula>  {
	

	
	String id,actor1,actor2, actor3, actor4, actor5, genero, title, releaseDate, prodCompany;
	
	float voteAverage;
	int voteCount;
	
	
	
	
	public Pelicula (String pId, String pActor1, String pActor2, String pActor3, String pActor4, String pActor5, String pGenero, String pTitle, 
			String pReleaseDate, String pProdCompany)
	{
		
		id = pId;
		actor1 = pActor1;
		actor2 = pActor2;
		actor3 = pActor3;
		actor4 = pActor4;
		actor5 = pActor5;
		genero = pGenero;
		title = pTitle;
		releaseDate = pReleaseDate;
		prodCompany = pProdCompany;
	
	}
	
	public String getId() {
		return id;
	}
	
	public String getActor1() {
		return actor1;
	}
	
	public String getActor2() {
		return actor2;
	}
	
	public String getActor3() {
		return actor3;
	}
	
	public String getActor4() {
		return actor4;
	}
	
	public String getActor5() {
		return actor5;
	}
	
	public String getGenero() {
		return genero;
	}
	
	public String getReleaseDate() {
		return releaseDate;
	}
	
	public String getTitle() {
		return title;
	}
	
	/**
	 * @return the prodCompany
	 */
	public String getProdCompany() {
		return prodCompany;
	}
	
	@Override
	public int compareTo(Pelicula o) {
		int rta = 0;
		
		if(this.voteAverage < o.voteAverage)
			rta = -1;
		else if(this.voteAverage > o.voteAverage)
			rta = 1;
		
		return rta;
	}
	
	
	
	
	
	
	

}

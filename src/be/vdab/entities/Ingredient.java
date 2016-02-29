package be.vdab.entities;

public class Ingredient {
	
	private long id;
	private String naam;
	
	public Ingredient(long id, String naam) {
		this.id = id;
		this.naam = naam;
	}
	
	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}
	/**
	 * @return the naam
	 */
	public String getNaam() {
		return naam;
	}
	/**
	 * @param naam the naam to set
	 */
	public void setNaam(String naam) {
		this.naam = naam;
	}
	
	@Override
	public String toString() {
		return this.naam;
	}

}

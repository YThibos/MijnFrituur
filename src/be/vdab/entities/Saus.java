package be.vdab.entities;

import java.util.LinkedList;
import java.util.List;

public class Saus {

	private long id;
	private String naam;
	private List<Ingredient> ingredienten;
	
	public Saus() {
		ingredienten = new LinkedList<>();
	}

	public Saus(long id, String naam, List<Ingredient> ingredienten) {
		this.id = id;
		this.naam = naam;
		this.ingredienten = ingredienten;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNaam() {
		return naam;
	}

	public void setNaam(String naam) {
		this.naam = naam;
	}

	public List<Ingredient> getIngredienten() {
		return ingredienten;
	}

	public void setIngredienten(List<Ingredient> ingredienten) {
		this.ingredienten = ingredienten;
	}	
	
	public void addIngredient(Ingredient ingredient) {
		ingredienten.add(ingredient);
	}
	
	@Override
	public String toString() {
		return this.naam.substring(0, 1).toUpperCase() + this.naam.substring(1);
	}
	
}

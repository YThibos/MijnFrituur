package be.vdab.entities;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Gbentry implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private long id;
	private String naam;
	private String bericht;
	private LocalDateTime datum;
	
	public Gbentry(long id, String naam, String bericht, LocalDateTime datum) {
		this.id = id;
		this.naam = naam;
		this.bericht = bericht;
		this.datum = datum;
	}
	
	public Gbentry(String naam, String bericht) {
		// dummy id, database has autoincremented id
		this.id = -1;
		this.naam = naam;
		this.bericht = bericht;
		this.datum = LocalDateTime.now();
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
	/**
	 * @return the bericht
	 */
	public String getBericht() {
		return bericht;
	}
	/**
	 * @param bericht the bericht to set
	 */
	public void setBericht(String bericht) {
		this.bericht = bericht;
	}
	/**
	 * @return the datum
	 */
	public LocalDateTime getDatum() {
		return datum;
	}
	/**
	 * @param datum the datum to set
	 */
	public void setDatum(LocalDateTime datum) {
		this.datum = datum;
	}
	
	@Override
	public String toString() {
		return naam + "\n" + bericht;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Gbentry other = (Gbentry) obj;
		if (id != other.id)
			return false;
		return true;
	}
	

}

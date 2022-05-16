package edu.intech.popback.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "figures")
@NamedQueries({ @NamedQuery(name = "figures.getAllFigures", query = "SELECT f FROM Figure f"),
		@NamedQuery(name = "figures.getFigureById", query = "SELECT f FROM Figure f WHERE f.id=:id"),
		@NamedQuery(name = "figures.getFiguresByIdUniverse", query = "SELECT f FROM Figure f WHERE f.idUniverse = :id") })
public class Figure {

	private int id;
	private String name;
	private String imageURL;
	private int idUniverse;

	public Figure() {
	}

	/**
	 * @param name
	 * @param imageURL
	 * @param idUniverse
	 */
	public Figure(String name, String imageURL, int idUniverse) {
		super();
		this.name = name;
		this.imageURL = imageURL;
		this.idUniverse = idUniverse;
	}

	/**
	 * Clef primaire
	 * 
	 * @return the id
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "id")
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	@Column(name = "name")
	public String getName() {
		return name;
	}

	/**
	 * @return the imageURL
	 */
	@Column(name = "image_url")
	public String getImageURL() {
		return imageURL;
	}

	/**
	 * @param imageURL the imageURL to set
	 */
	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the idUniverse
	 */
	@Column(name = "id_universe")
	public int getIdUniverse() {
		return idUniverse;
	}

	/**
	 * @param idUniverse the idUniverse to set
	 */
	public void setIdUniverse(int idUniverse) {
		this.idUniverse = idUniverse;
	}
}

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
@NamedQueries({
	@NamedQuery(name = "figures.getAllFigures", query = "SELECT f FROM Figure f"),
	@NamedQuery(name = "figures.getByFigureId", query = "SELECT f FROM Figure f WHERE f.id=:id"),
	@NamedQuery(name = "figures.getFiguresByIdUniverse", query = "SELECT f FROM Figure f JOIN Universe u WHERE u.id=:id")
})
public class Figure {

	private int id;
	private String name;
	private int idUniverse;

	public Figure() {
	}

	/**
	 * @param name
	 * @param idUniverse
	 */
	public Figure(String name, int idUniverse) {
		super();
		this.name = name;
		this.idUniverse = idUniverse;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "id")
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	@Column(name = "name")
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "id_universe")
	/**
	 * @return the idUniverse
	 */
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

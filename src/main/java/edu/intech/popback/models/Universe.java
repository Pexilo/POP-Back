package edu.intech.popback.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "universes")
@NamedQueries({
	@NamedQuery(name = "universes.getAllUniverses", query = "SELECT u FROM Universe u"),
	@NamedQuery(name = "universes.getUniverseById", query = "SELECT u FROM Universe u WHERE u.id=:id"),
})
public class Universe {

	private int id;
	private String name;
	private List<Figure> figures;

	public Universe() {
	}

	/**
	 * @param id
	 * @param name
	 * @param figures
	 */
	public Universe(String name, List<Figure> figures) {
		super();
		this.name = name;
		this.figures = figures;
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

	@OneToMany(fetch = FetchType.EAGER, orphanRemoval = true)
	@JoinColumn(name = "id_universe", referencedColumnName = "id")
	/**
	 * @return the figures
	 */
	public List<Figure> getFigures() {
		return figures;
	}

	/**
	 * @param figures the figures to set
	 */
	public void setFigures(List<Figure> figures) {
		this.figures = figures;
	}
	
	//permet d'update la liste Figure
	public void addFigure(Figure f) {
		this.figures.add(f);
	}
	
	public void removeFigure(Figure f) {
		this.figures.remove(f);
	}


}

package edu.intech.popback.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "universes")
public class Universe {
	
	private int id;
	private String name;
	private List<Figure> figures;
	
	public Universe() {}
	
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




}

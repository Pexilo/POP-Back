package edu.intech.popback.models;

import java.util.ArrayList;
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
@NamedQueries({ @NamedQuery(name = "universes.getAllUniverses", query = "SELECT u FROM Universe u JOIN FETCH Figure f"),
		@NamedQuery(name = "universes.getUniverseById", query = "SELECT u FROM Universe u WHERE u.id=:id"), })
public class Universe {

	private int id;
	private String name;
	private String imageURL;
	private List<Figure> figures = new ArrayList<>();

	public Universe() {
	}

	/**
	 * @param name
	 */
	public Universe(String name, String imageURL) {
		super();
		this.name = name;
		this.imageURL = imageURL;
	}

	/**
	 * clef primaire
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
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
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

	/*
	 * L'univers a une liste de figurines, ces figurines doivent être recupérées
	 * avec impatience (c'est-à-dire que lorsque l'univers est recupéré, les
	 * figurines doivent également être récupérées).
	 */
	@OneToMany(fetch = FetchType.EAGER, orphanRemoval = true)
	// La colonne `id_universe` dans la table `figures` est une clef etrangere qui
	// fait reference à la colonne `id` dans la table `universes`.
	@JoinColumn(name = "id_universe", referencedColumnName = "id")
	/**
	 * Liste parce que l'univers peut avoir plusieurs figurines (relation one to
	 * many)
	 * 
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

	/**
	 * Ajoute une figurine à la liste des figurines.
	 * 
	 * @param f La figurine à ajouter a la liste.
	 */
	public void addFigure(Figure f) {
		this.figures.add(f);
	}

	/**
	 * Supprime une figurine de la liste des figurines
	 * 
	 * @param f Figurine à supprimer.
	 */
	public void removeFigure(Figure f) {
		this.figures.remove(f);
	}

	public Universe compareUpdate(Universe u) {
		if (u.name == null) {
			u.name = this.name;
		}

		if (u.figures == null || (u.figures.isEmpty() && !(this.figures.isEmpty()))) {
			u.figures = this.figures;
		}
		return u;
	}
}

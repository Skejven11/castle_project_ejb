package com.castle.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the architectural_style database table.
 * 
 */
@Entity
@Table(name="architectural_style")
@NamedQuery(name="ArchitecturalStyle.findAll", query="SELECT a FROM ArchitecturalStyle a")
public class ArchitecturalStyle implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="idarchitectural_style")
	private int idarchitecturalStyle;

	private int century;

	private String name;

	//bi-directional many-to-many association to Castle
	@ManyToMany(mappedBy="architecturalStyles")
	private List<Castle> castles;

	public ArchitecturalStyle() {
	}

	public int getIdarchitecturalStyle() {
		return this.idarchitecturalStyle;
	}

	public void setIdarchitecturalStyle(int idarchitecturalStyle) {
		this.idarchitecturalStyle = idarchitecturalStyle;
	}

	public int getCentury() {
		return this.century;
	}

	public void setCentury(int century) {
		this.century = century;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Castle> getCastles() {
		return this.castles;
	}

	public void setCastles(List<Castle> castles) {
		this.castles = castles;
	}

}
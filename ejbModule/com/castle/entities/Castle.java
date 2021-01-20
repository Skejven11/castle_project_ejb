package com.castle.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the castle database table.
 * 
 */
@Entity
@NamedQuery(name="Castle.findAll", query="SELECT c FROM Castle c")
public class Castle implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idcastle;

	@Column(name="construction_century")
	private int constructionCentury;

	private String country;

	@Column(name="current_owner")
	private String currentOwner;

	private String isRuin;

	private String isVerified;

	private String name;

	@Column(name="original_owner")
	private String originalOwner;

	private double score;

	//bi-directional many-to-one association to CastleScore
	@OneToMany(mappedBy="castle")
	private List<CastleScore> castleScores;

	//bi-directional many-to-many association to ArchitecturalStyle
	@ManyToMany
	@JoinTable(
		name="castle_styles"
		, joinColumns={
			@JoinColumn(name="castle_idcastle")
			}
		, inverseJoinColumns={
			@JoinColumn(name="architectural_style_idarchitectural_style")
			}
		)
	private List<ArchitecturalStyle> architecturalStyles;

	public Castle() {
	}

	public int getIdcastle() {
		return this.idcastle;
	}

	public void setIdcastle(int idcastle) {
		this.idcastle = idcastle;
	}

	public int getConstructionCentury() {
		return this.constructionCentury;
	}

	public void setConstructionCentury(int constructionCentury) {
		this.constructionCentury = constructionCentury;
	}

	public String getCountry() {
		return this.country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCurrentOwner() {
		return this.currentOwner;
	}

	public void setCurrentOwner(String currentOwner) {
		this.currentOwner = currentOwner;
	}

	public String getIsRuin() {
		return this.isRuin;
	}

	public void setIsRuin(String isRuin) {
		this.isRuin = isRuin;
	}

	public String getIsVerified() {
		return this.isVerified;
	}

	public void setIsVerified(String isVerified) {
		this.isVerified = isVerified;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOriginalOwner() {
		return this.originalOwner;
	}

	public void setOriginalOwner(String originalOwner) {
		this.originalOwner = originalOwner;
	}

	public double getScore() {
		return this.score;
	}

	public void setScore(double score) {
		this.score = score;
	}

	public List<CastleScore> getCastleScores() {
		return this.castleScores;
	}

	public void setCastleScores(List<CastleScore> castleScores) {
		this.castleScores = castleScores;
	}

	public CastleScore addCastleScore(CastleScore castleScore) {
		getCastleScores().add(castleScore);
		castleScore.setCastle(this);

		return castleScore;
	}

	public CastleScore removeCastleScore(CastleScore castleScore) {
		getCastleScores().remove(castleScore);
		castleScore.setCastle(null);

		return castleScore;
	}

	public List<ArchitecturalStyle> getArchitecturalStyles() {
		return this.architecturalStyles;
	}

	public void setArchitecturalStyles(List<ArchitecturalStyle> architecturalStyles) {
		this.architecturalStyles = architecturalStyles;
	}

}
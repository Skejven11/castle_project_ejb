package com.castle.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the castle_score database table.
 * 
 */
@Entity
@Table(name="castle_score")
@NamedQuery(name="CastleScore.findAll", query="SELECT c FROM CastleScore c")
public class CastleScore implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_castle_score")
	private int idCastleScore;

	@Temporal(TemporalType.DATE)
	private Date date;

	private int rating;

	//bi-directional many-to-one association to Castle
	@ManyToOne
	private Castle castle;

	//bi-directional many-to-one association to User
	@ManyToOne
	private User user;

	public CastleScore() {
	}

	public int getIdCastleScore() {
		return this.idCastleScore;
	}

	public void setIdCastleScore(int idCastleScore) {
		this.idCastleScore = idCastleScore;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getRating() {
		return this.rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public Castle getCastle() {
		return this.castle;
	}

	public void setCastle(Castle castle) {
		this.castle = castle;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
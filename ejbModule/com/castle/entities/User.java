package com.castle.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the user database table.
 * 
 */
@Entity
@NamedQuery(name="User.findAll", query="SELECT u FROM User u")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int iduser;

	private String email;

	private String login;

	private String password;

	private String role;

	//bi-directional many-to-one association to CastleScore
	@OneToMany(mappedBy="user")
	private List<CastleScore> castleScores;

	public User() {
	}

	public int getIduser() {
		return this.iduser;
	}

	public void setIduser(int iduser) {
		this.iduser = iduser;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLogin() {
		return this.login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return this.role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public List<CastleScore> getCastleScores() {
		return this.castleScores;
	}

	public void setCastleScores(List<CastleScore> castleScores) {
		this.castleScores = castleScores;
	}

	public CastleScore addCastleScore(CastleScore castleScore) {
		getCastleScores().add(castleScore);
		castleScore.setUser(this);

		return castleScore;
	}

	public CastleScore removeCastleScore(CastleScore castleScore) {
		getCastleScores().remove(castleScore);
		castleScore.setUser(null);

		return castleScore;
	}

}
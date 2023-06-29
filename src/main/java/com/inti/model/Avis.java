package com.inti.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table @Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class Avis {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String commentaire;

	@ManyToOne @JoinColumn(name = "idHotel")
	private Hotel hotel;


	public Avis(String commentaire) {
	super();
	this.commentaire = commentaire;
}

}
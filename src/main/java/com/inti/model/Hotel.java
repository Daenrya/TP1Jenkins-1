package com.inti.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString.Exclude;

@Entity
@Table
@Data @AllArgsConstructor @NoArgsConstructor
public class Hotel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idHotel;
	private String nom; 
	private int nbEtoile;
	
	@Exclude
	@ManyToOne @JoinColumn(name="id_des")
	private Destination destination;

	@OneToMany(mappedBy = "hotel")
	private List<Reservation> reservation;
	
	
	@Exclude
	@OneToMany (mappedBy = "hotel")
	private List<Avis> listeAvis;
	
	public Hotel(String nom, int nbEtoile) {
		super();
		this.nom = nom;
		this.nbEtoile = nbEtoile;
	} 
	
	
	

}

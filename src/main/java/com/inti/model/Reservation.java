package com.inti.model;

import java.time.LocalDate;

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

@Entity
@Table
@Data
@NoArgsConstructor @AllArgsConstructor
public class Reservation {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idReservation;
	private LocalDate dateReservation;
	private int nbJours;
	
	@ManyToOne
	@JoinColumn(name="idH")
	private Hotel hotel;
	
	@ManyToOne
	@JoinColumn(name="idV")
	private Voyageur voyageur;
	
	public Reservation(LocalDate dateReservation, int nbJours) {
		super();
		this.dateReservation = dateReservation;
		this.nbJours = nbJours;
	}
	
}

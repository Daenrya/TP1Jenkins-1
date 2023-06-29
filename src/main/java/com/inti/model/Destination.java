package com.inti.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString.Exclude;

@Entity
@Table 
@Data @AllArgsConstructor @NoArgsConstructor
public class Destination {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idDestination;
	private long longitude;
	private long latitude;
	
	@Exclude
	@OneToMany (mappedBy = "destination")
	private List<Hotel> listeHotel;
	
	public Destination(long longitude, long latitude) {
		super();
		this.longitude = longitude;
		this.latitude = latitude;
	} 
	

}

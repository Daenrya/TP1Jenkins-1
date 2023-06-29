package com.inti.controller;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.inti.model.Destination;
import com.inti.repository.IDestinationRepository;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class DestinationRepositoryTest {
	
	
	Destination d1,savedDestination;
	
	@Autowired
	IDestinationRepository idr;
	
	
	@BeforeEach
	public void setUp()
	{
		d1= new Destination(125,244);
		savedDestination=idr.save(d1);
	}
	
	@Test @DisplayName("test pour l'enregistrement d'une destination")
	public void saveDestination() {
		assertThat(savedDestination).isNotNull();
		assertThat(savedDestination.getIdDestination()).isGreaterThan(0);
	}
	
	@Test @DisplayName("test d'affichage d'une Destination")
	public void getDestination()
	{
		Destination d2=idr.getReferenceById(savedDestination.getIdDestination());
		
		assertThat(d2).isNotNull();
		assertThat(d2.getIdDestination()).isEqualTo(savedDestination.getIdDestination());
		assertThat(d2.getLatitude()).isEqualTo(savedDestination.getLatitude());
		assertThat(d2.getLongitude()).isEqualTo(savedDestination.getLongitude());
		assertThat(d2).isEqualTo(savedDestination);
		
		
	}
	
	@Test
	public void deleteDestination()
	{
		idr.delete(savedDestination);
		
		Assertions.assertThrows(Exception.class, ()-> idr.getReferenceById(savedDestination.getIdDestination()));
	}
	
	@Test
	public void updateDestination()
	{
		savedDestination.setLatitude(16);
		savedDestination.setLongitude(50);
		
		Destination dmodified= idr.save(savedDestination);
		assertThat(dmodified).isNotNull();
		assertThat(dmodified.getLatitude()).isEqualTo(16);
		assertThat(dmodified.getLongitude()).isEqualTo(50);
		
	}
	
	@Test
	public void getAllDestination()
	{
		Destination d3=new Destination(32,75);
		Destination d4= new Destination(90,60);
		
		idr.save(d3);
		idr.save(d4);
		
		List<Destination>listeD=idr.findAll();
	
		
		assertThat(listeD).isNotEmpty();
		assertThat(listeD).hasSize(4);
		assertThat(listeD.get(0).getClass()).hasSameClassAs(Destination.class);
		assertThat(listeD.get(2).toString()).hasToString(d3.toString());
	}

}

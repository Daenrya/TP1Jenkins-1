package com.inti.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.inti.model.Hotel;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class HotelRepositoryTest {
	
	private Hotel h1, savedHotel;
	
	@Autowired
	IHotelRepository ihr;

	@BeforeEach
	public void setUp() {
		h1=new Hotel ("Ritz",3);
		savedHotel = ihr.save(h1);
	}
	
	@Test
	public void saveHotel() {
		assertThat(savedHotel).isNotNull();
		assertThat(savedHotel.getIdHotel()).isNotNull();
	}
	
	@Test
	public void deleteHotelTest()
	{
		// GIVEN
		Hotel h1 = new Hotel("Georges V", 5);
		Hotel savedHotel = ihr.save(h1);
		
		// WHEN
		ihr.delete(savedHotel);
		
		// THEN
		Assertions.assertThrows(Exception.class, () -> ihr.getReferenceById(h1.getIdHotel()));
	}
	
	@Test
	public void updateHotelTest()
	{
		// GIVEN
		Hotel h1 = new Hotel("Georges V", 5);
		Hotel savedHotel = ihr.save(h1);
				
		// WHEN
		savedHotel.setNom("Hôtel de Crillon");
		Hotel modifiedHotel = ihr.save(savedHotel);
		
		// THEN
		assertThat(modifiedHotel).isNotNull();
		assertThat(modifiedHotel.getNom()).isEqualTo("Hôtel de Crillon");
	}
	
	@Test
	public void updateHotelNullTest()
	{
		// GIVEN
		Hotel h2 = null;
				
		// WHEN
		
		// THEN
		Assertions.assertThrows(Exception.class, () -> h2.setNom("test"));
	}
	
	@Test
	public void getHotelTest()
	{
		// GIVEN
		Hotel h1 = new Hotel("Georges V", 5);
		Hotel savedHotel = ihr.save(h1);
				
		// WHEN
		Hotel hotel = ihr.getReferenceById(savedHotel.getIdHotel());
		
		// THEN
		assertThat(hotel).isNotNull();
		assertThat(hotel.getNom()).isEqualTo("Georges V");
		assertThat(hotel).isEqualTo(savedHotel);
	}
	
	@Test
	public void getAllHotelsTest()
	{
		// GIVEN
		Hotel h1 = new Hotel("Georges V", 5);
		Hotel h2 = new Hotel("Meurice", 5);
		Hotel savedHotel1 = ihr.save(h1);
		Hotel savedHotel2 = ihr.save(h2);
				
		// WHEN
		List<Hotel> listeH = ihr.findAll();
		
		// THEN
		assertThat(listeH).isNotEmpty();
		assertThat(listeH).hasSize(2);
		assertThat(listeH.get(0).getClass()).hasSameClassAs(Hotel.class);
	}
	
	@Test
	public void getHotelListeVideTest()
	{
		// GIVEN
				
		// WHEN
		List<Hotel> listeH = ihr.findAll();
		
		// THEN
		assertThat(listeH).isEmpty();
		assertThat(listeH).hasSize(0);
	}
	
}

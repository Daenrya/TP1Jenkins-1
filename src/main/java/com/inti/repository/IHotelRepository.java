package com.inti.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.inti.model.Hotel;

@Repository
public interface IHotelRepository extends JpaRepository<Hotel, Integer>{


}

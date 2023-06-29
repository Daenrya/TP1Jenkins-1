package com.inti.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.inti.model.Hotel;
import com.inti.repository.IHotelRepository;

@Controller
@RequestMapping("hotel")
public class HotelController {
	
	@Autowired
	IHotelRepository ihr;
	
	@GetMapping("createHotel")
	public String formHotel() {
		return "createHotelForm";
	}
	
	@PostMapping("saveHotel")
	public String saveHotel(@ModelAttribute("hotel") Hotel h) {
		ihr.save(h);
		return "redirect:/hotel/createHotel";
	}
	
	@GetMapping("listeHotels")
	public String listeHotels(Model m)
	{
		
		m.addAttribute("listeH", ihr.findAll());
		return "listeHotels";
	}
	
	@GetMapping("deleteHotel/{idHotel}")
	public String deleteHotel(@PathVariable("idHotel") int idHotel) {
	
		ihr.deleteById(idHotel);
		
		return "redirect:/hotel/listeHotels";
	}
	
	@GetMapping("updateHotel/{idHotel}")
	public String updateHotel(@PathVariable("idHotel") int idHotel, Model m) 
	{
		m.addAttribute("hotel", ihr.getReferenceById(idHotel));
		System.out.println(m);
		return "updateHotel";
	}
	
	@PostMapping("updateHotel/modifHotel")
	public String modifHotel(@ModelAttribute("hotel") Hotel h) {
		
		ihr.save(h);

		return "redirect:/hotel/listeHotels";
	}
	
	
	

}

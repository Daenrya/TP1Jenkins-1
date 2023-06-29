package com.inti.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.inti.model.Hotel;
import com.inti.repository.IHotelRepository;

@WebMvcTest(controllers = HotelController.class)
public class HotelController {
	
	@Autowired
	private MockMvc mock;
	
	@MockBean
	private IHotelRepository ihr;
	
	@Test
	public void saveHotel() throws Exception
	{
		mock.perform(get("/createHotel"))
		.andExpect(status().isOk())
		.andDo(print());
	}
	
	@Test
	public void listeHotel() throws Exception
	{
		mock.perform(get("/listeHotels"))
		.andExpect(status().isOk())
		.andDo(print());
	}
	
	@Test
	public void deleteHotel() throws Exception
	{
		mock.perform(get("/deleteHotel/1"))
		.andExpect(status().is3xxRedirection())
		.andExpect(redirectedUrl("/listeHotels"))
		.andDo(print());
	}
	
	@Test
	public void saveHotelPost() throws Exception
	{
		mock.perform(post("/createHotel").sessionAttr("hotel", new Hotel("Waldorf", 4)))
		.andExpect(status().is3xxRedirection())
		.andExpect(redirectedUrl("/listeHotels"))
		.andDo(print());
	}
	
//	@Test
//	public void updateHotelPost() throws Exception
//	{
//		mock.perform(post("/modifierClient/1").sessionAttr("client", new Client("dupont", "louis", "test@test.fr")))
//		.andExpect(status().is3xxRedirection())
//		.andExpect(redirectedUrl("/listeHotels"))
//		.andDo(print());
//	}

}

package com.example.hcl.flagpicker.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.hcl.flagpicker.model.Continent;
import com.example.hcl.flagpicker.model.Country;
import com.example.hcl.flagpicker.repository.IFlagRepository;

@RunWith(SpringRunner.class)
public class FlagServiceTest {

	

	@MockBean
	private FlagService flagService;

	@MockBean
	private IFlagRepository flagRepository;

	@Before
	public void setUp() {
		Continent continent = new Continent();
		continent.setContinent("Asia");
		Country india = new Country();
		india.setName("India");
		india.setFlag("tricolor");
		Country china = new Country();
		china.setName("China");
		china.setFlag("nocolor");
		List<Country> countries = new ArrayList<Country>();
		countries.add(india);
		countries.add(china);
		continent.setCountries(countries);

		List<Continent> continents = new ArrayList<Continent>();
		continents.add(continent);

		Mockito.when(flagService.getAllFlags()).thenReturn(continents);

		// Mockito.when(employeeRepository.findById(-99L)).thenReturn(Optional.empty());
	}

	@Test
	public void whenValidContinent_thenCountryShouldBeFound() {
		List<Continent> found = flagService.getAllFlags();

		assert (found.get(0).getContinent()).equalsIgnoreCase("Asia");
		assert (found.get(0).getCountries().get(0).getName()).equalsIgnoreCase("India");
		assert (found.get(0).getCountries().get(0).getFlag()).equalsIgnoreCase("tricolor");

	}

}

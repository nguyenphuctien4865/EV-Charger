package com.evcharger.architecture;

import com.evcharger.architecture.entity.Location;
import com.evcharger.architecture.entity.OperatingHours;
import com.evcharger.architecture.exception.common.InvalidParamException;
import com.evcharger.architecture.model.EVChargerDTO;
import com.evcharger.architecture.model.LocationDTO;
import com.evcharger.architecture.model.PowerOutputDTO;
import com.evcharger.architecture.model.PowerPlugTypeDTO;
import com.evcharger.architecture.service.EVChargerService;
import com.evcharger.architecture.service.LocationService;
import com.evcharger.architecture.service.PowerOutputService;
import com.evcharger.architecture.service.PowerPlugTypeService;
import com.evcharger.architecture.util.enums.Availability;
import com.evcharger.architecture.util.enums.ChargingSpeed;

import lombok.extern.slf4j.Slf4j;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Date;

@Slf4j
@EnableScheduling
@SpringBootApplication
public class ArchitectureApplication {

	@Autowired
	private PowerOutputService powerOutputService;

	@Autowired
	private EVChargerService evChargerService;

	@Autowired
	private LocationService locationService;

	@Autowired
	private PowerPlugTypeService powerPlugTypeService;

	private void createPowerPlugTypeDummy() throws InvalidParamException {
		List<PowerPlugTypeDTO> powerPlugTypes = List.of(
				new PowerPlugTypeDTO(1L, "AC", "Type 1", "type1.png", "North America, Japan", "Common in older EVs",
						null),
				new PowerPlugTypeDTO(2L, "AC", "Type 2", "type2.png", "Europe, Australia", "Standard in Europe", null),
				new PowerPlugTypeDTO(3L, "DC", "CCS2", "ccs2.png", "Europe, North America", "Combined Charging System",
						null),
				new PowerPlugTypeDTO(4L, "DC", "CHAdeMO", "chademo.png", "Japan, some European countries",
						"Decreasing in popularity", null),
				new PowerPlugTypeDTO(5L, "AC", "Tesla", "tesla.png", "North America", "Proprietary Tesla connector",
						null));

		for (PowerPlugTypeDTO dto : powerPlugTypes) {
			powerPlugTypeService.createPowerPlugType(dto);
		}
	}

	private void createPowerOutputDummy() {
		List<PowerOutputDTO> powerOutputs = List.of(
				new PowerOutputDTO(1L, 7.4, ChargingSpeed.SLOW, 230, "Standard home charging"),
				new PowerOutputDTO(2L, 22.0, ChargingSpeed.FAST, 400, "Three-phase AC charging"),
				new PowerOutputDTO(3L, 50.0, ChargingSpeed.FAST, 400, "DC fast charging"),
				new PowerOutputDTO(4L, 150.0, ChargingSpeed.ULTRA_FAST, 800, "Ultra-fast DC charging"),
				new PowerOutputDTO(5L, 350.0, ChargingSpeed.ULTRA_FAST, 800, "Highest power DC charging"));

		for (PowerOutputDTO dto : powerOutputs) {
			powerOutputService.createPowerOutput(dto);
		}
	}

	private void createLocationDummy() {
		List<LocationDTO> locations = List.of(
				new LocationDTO(1L, "Central Station", "123 Main St", "Downtown", "Metropolis", "USA", "12345", 40.7128,
						-74.0060,
						List.of(new OperatingHours(DayOfWeek.MONDAY, LocalTime.of(0, 0), LocalTime.of(23, 59))),
						"Free for first hour", "+1234567890", "Ground Floor"),
				new LocationDTO(2L, "Shopping Mall", "456 High St", "Uptown", "Megacity", "Canada", "M5V 2T6", 43.6532,
						-79.3832,
						List.of(new OperatingHours(DayOfWeek.MONDAY, LocalTime.of(9, 0), LocalTime.of(21, 0))),
						"$2/hour", "+9876543210", "P2"));

		for (LocationDTO dto : locations) {
			locationService.createLocation(dto);
		}
	}

	private void createEVChargerDummy() throws InvalidParamException {
		LocationDTO location1 = locationService.getLocationById(1L);
		LocationDTO location2 = locationService.getLocationById(2L);
		PowerOutputDTO powerOutput1 = powerOutputService.getPowerOutputById(1L);
		PowerOutputDTO powerOutput2 = powerOutputService.getPowerOutputById(3L);
		PowerPlugTypeDTO plugType1 = powerPlugTypeService.getPowerPlugTypeById(1L);
		PowerPlugTypeDTO plugType2 = powerPlugTypeService.getPowerPlugTypeById(3L);

		List<EVChargerDTO> chargers = List.of(
				new EVChargerDTO("CHG001", location1, powerOutput1, plugType1, 2, Availability.AVAILABLE,
						LocalDate.of(2022, 1, 1), LocalDate.of(2023, 6, 1)),
				new EVChargerDTO("CHG002", location2, powerOutput2, plugType2, 4, Availability.IN_USE,
						LocalDate.of(2021, 7, 15), LocalDate.of(2023, 5, 30)));

		for (EVChargerDTO dto : chargers) {
			evChargerService.createEVCharger(dto);
		}
	}

	public static void main(String[] args) {
		SpringApplication.run(ArchitectureApplication.class, args);
		System.out.println(new Date(System.currentTimeMillis()));
	}

	@Bean
	CommandLineRunner init() {
		return args -> {
			createPowerPlugTypeDummy();
			createPowerOutputDummy();
			createLocationDummy();
			createEVChargerDummy();
		};
	}

}

package com.evcharger.architecture;

import com.evcharger.architecture.entity.PowerOutput;
import com.evcharger.architecture.entity.PowerPlugType;
import com.evcharger.architecture.model.EVChargerDTO;
import com.evcharger.architecture.model.PowerOutputModel;
import com.evcharger.architecture.repository.PowerPlugTypeRepository;
import com.evcharger.architecture.service.EVChargerService;
import com.evcharger.architecture.service.PowerOutputService;
import com.evcharger.architecture.service.PowerPlugTypeService;
import com.evcharger.architecture.util.validator.Availability;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.time.LocalDate;
import java.util.Date;
import java.util.List; // Add this line to import the List class

@EnableSwagger2
@EnableScheduling
@SpringBootApplication
public class ArchitectureApplication {

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	@Autowired
	private PowerOutputService powerOutputService;

	@Autowired
	private EVChargerService evChargerService;

	private PowerPlugType createDummyPowerPlugType1() {
		PowerPlugType dummy = new PowerPlugType();
		dummy.setId(1L);
		dummy.setPowerModel("Model X120");
		dummy.setPlugType("Type-C");
		dummy.setPlugImage("https://example.com/images/plug-type-c.png");
		dummy.setUsedInRegions("North America, Europe, Asia");
		dummy.setAdditionalNotes("Commonly used in modern electronics, especially smartphones and laptops.");
		return dummy;
	}

	private static PowerPlugType createDummyPowerPlugType2() {
		PowerPlugType dummy = new PowerPlugType();
		dummy.setId(2L);
		dummy.setPowerModel("Model Y230");
		dummy.setPlugType("Type-B");
		dummy.setPlugImage("https://example.com/images/plug-type-b.png");
		dummy.setUsedInRegions("South America, Africa");
		dummy.setAdditionalNotes("Widely used in older devices, such as household appliances.");
		return dummy;
	}

	private PowerPlugType createDummyPowerPlugType3() {
		PowerPlugType dummy = new PowerPlugType();
		dummy.setId(3L);
		dummy.setPowerModel("Model Z450");
		dummy.setPlugType("Type-A");
		dummy.setPlugImage("https://example.com/images/plug-type-a.png");
		dummy.setUsedInRegions("Japan, USA");
		dummy.setAdditionalNotes("Type-A is used primarily in countries with a 120V power system.");
		return dummy;
	}

	private PowerPlugType createDummyPowerPlugType4() {
		PowerPlugType dummy = new PowerPlugType();
		dummy.setId(4L);
		dummy.setPowerModel("Model W500");
		dummy.setPlugType("Type-F");
		dummy.setPlugImage("https://example.com/images/plug-type-f.png");
		dummy.setUsedInRegions("Europe, Russia");
		dummy.setAdditionalNotes("This plug is compatible with the European standard for household plugs.");
		return dummy;
	}

	private PowerPlugType createDummyPowerPlugType5() {
		PowerPlugType dummy = new PowerPlugType();
		dummy.setId(5L);
		dummy.setPowerModel("Model Q600");
		dummy.setPlugType("Type-G");
		dummy.setPlugImage("https://example.com/images/plug-type-g.png");
		dummy.setUsedInRegions("UK, Ireland, Hong Kong");
		dummy.setAdditionalNotes("Standard plug type used in the UK and other former British colonies.");
		return dummy;
	}

	private void createPowerOutputDummy() {

		PowerOutputModel validPowerOutput1 = new PowerOutputModel(
				1L,
				100.0,
				"Slow",
				220,
				"Standard slow charging option for small electric vehicles.");

		PowerOutputModel validPowerOutput2 = new PowerOutputModel(
				2L,
				150.0,
				"Fast",
				230,
				"Fast charging station suitable for medium-sized electric cars.");

		PowerOutputModel validPowerOutput3 = new PowerOutputModel(
				3L,
				200.0,
				"Ultra-Fast",
				400,
				"Ultra-fast charging for large vehicles with high-capacity batteries.");

		// Edge Case Examples
		PowerOutputModel edgeCasePowerOutput1 = new PowerOutputModel(
				4L,
				0.01, // Minimum positive value
				"Fast",
				110, // Lower voltage limit
				null // Null description (valid)
		);

		PowerOutputModel edgeCasePowerOutput2 = new PowerOutputModel(
				5L,
				999.99, // Upper bound for a plausible output value
				"Ultra-Fast",
				500, // Higher voltage value
				"Ultra-fast charging station with the highest efficiency.");

		List<PowerOutputModel> powerPlugTypeModelList = List.of(validPowerOutput1, validPowerOutput2, validPowerOutput3,
				edgeCasePowerOutput1, edgeCasePowerOutput2);
		for (PowerOutputModel powerOutputModel : powerPlugTypeModelList) {
			powerOutputService.createPowerOutput(powerOutputModel);
		}
	}

	private void createEVChargerDummy() {
		EVChargerDTO charger1 = new EVChargerDTO(
				"CHG-001",
				"LOC-001",
				"PO-001",
				"PPT-001",
				4,
				Availability.AVAILABLE,
				LocalDate.of(2020, 1, 15),
				LocalDate.of(2021, 6, 10));

		EVChargerDTO charger2 = new EVChargerDTO(
				"CHG-002",
				"LOC-002",
				"PO-002",
				"PPT-002",
				2,
				Availability.IN_USE,
				LocalDate.of(2019, 3, 20),
				LocalDate.of(2021, 5, 5));

		EVChargerDTO charger3 = new EVChargerDTO(
				"CHG-003",
				"LOC-003",
				"PO-003",
				"PPT-003",
				6,
				Availability.OUT_OF_ORDER,
				LocalDate.of(2018, 7, 25),
				LocalDate.of(2021, 4, 15));

		List<EVChargerDTO> listEVCharger = List.of(charger1, charger2, charger3);

		for (EVChargerDTO powerOutputModel : listEVCharger) {
			evChargerService.createEVCharger(powerOutputModel);
		}
	}

	public static void main(String[] args) {
		SpringApplication.run(ArchitectureApplication.class, args);
		System.out.println(new Date(System.currentTimeMillis()));
	}

	@Bean
	CommandLineRunner init(PowerPlugTypeRepository powerPlugTypeRepository) {
		return args -> {
			PowerPlugType testPlugType1 = createDummyPowerPlugType1();
			PowerPlugType testPlugType2 = createDummyPowerPlugType2();
			PowerPlugType testPlugType3 = createDummyPowerPlugType3();
			PowerPlugType testPlugType4 = createDummyPowerPlugType4();
			PowerPlugType testPlugType5 = createDummyPowerPlugType5();
			List<PowerPlugType> powerPlugTypeModelList = List.of(testPlugType1, testPlugType2, testPlugType3,
					testPlugType4, testPlugType5);
			powerPlugTypeRepository.saveAll(powerPlugTypeModelList);

			createPowerOutputDummy();
			createEVChargerDummy();
		};
	}

}

package com.paradigma.graphql.cars.provider.car.query;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.paradigma.graphql.schema.car.brand.Brand;
import com.paradigma.graphql.schema.car.car.Car;
import com.paradigma.graphql.schema.car.car.Car.Unresolved;
import com.paradigma.graphql.schema.car.car.query.QueryCar;
import com.paradigma.graphql.schema.car.model.Model;
import com.paradigma.graphql.schema.mastertable.country.Country;
import com.paradigma.persistence.service.car.CarService;

/**
 * Consultas sobre el elemento raiz
 * 
 * @author manuel
 *
 */
@Component
public class QueryCarImpl implements QueryCar {

	@Autowired
	CarService carService;

	@Override
	public List<Car> getCars() {
		/* Obtenemos la lista */
		List<Car> unresolvedCars = carService.findAll();
		return unresolvedCars;
	}

	@Override
	public Car car(final CarArgs args) {
		return new Unresolved(args.getId());
	}

	@Override
	public List<Brand> brands(BrandsArgs args) {
		return Collections.emptyList();
	}

	@Override
	public List<Country> countries(CountriesArgs args) {
		return Collections.emptyList();
	}

	@Override
	public List<Model> models(ModelsArgs args) {
		return Collections.emptyList();
	}

}

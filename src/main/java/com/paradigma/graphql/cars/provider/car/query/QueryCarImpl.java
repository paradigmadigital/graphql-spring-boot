package com.paradigma.graphql.cars.provider.car.query;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.paradigma.graphql.schema.car.brand.Brand;
import com.paradigma.graphql.schema.car.car.Car;
import com.paradigma.graphql.schema.car.car.query.QueryCar;
import com.paradigma.graphql.schema.car.model.Model;
import com.paradigma.graphql.schema.mastertable.country.Country;
import com.paradigma.persistence.service.car.CarService;

/**
 * Implementamos la obtención de todas las consultas que se aùedan realizar , serán los elementos raiz
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
		return carService.findAll();
	}
	

	@Override
	public List<Brand> brands(BrandsArgs args) {
		// TODO Auto-generated method stub
		return QueryCar.super.brands(args);
	}

	@Override
	public List<Country> countries(CountriesArgs args) {
		// TODO Auto-generated method stub
		return QueryCar.super.countries(args);
	}

	@Override
	public List<Model> models(ModelsArgs args) {
		// TODO Auto-generated method stub
		return QueryCar.super.models(args);
	}

}

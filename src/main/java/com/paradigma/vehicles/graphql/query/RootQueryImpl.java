package com.paradigma.vehicles.graphql.query;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.paradigma.graphql.schema.car.brand.Brand;
import com.paradigma.graphql.schema.car.car.Car;
import com.paradigma.graphql.schema.car.model.Model;
import com.paradigma.graphql.schema.car.root.query.RootQuery;
import com.paradigma.vehicles.persistence.service.brand.BrandService;
import com.paradigma.vehicles.persistence.service.car.CarService;
import com.paradigma.vehicles.persistence.service.model.ModelService;

/**
 * Consultas sobre el elemento raiz
 * 
 * @author manuel
 *
 */
@Component
public class RootQueryImpl implements RootQuery {

	@Autowired
	CarService carService;

	@Autowired
	BrandService brandService;

	@Autowired
	ModelService modelService;

	@Override
	public List<Car> getCars() {
		return carService.findAll();
	}

	@Override
	public Car car(final CarArgs args) {
		return new Car.Unresolved(args.getId());
	}

	@Override
	public List<Brand> brands(BrandsArgs args) {
		return brandService.findAll();
	}

	@Override
	public List<Model> models(ModelsArgs args) {
		return modelService.findAll();
	}

	@Override
	public Model model(ModelArgs args) {
		return new Model.Unresolved(args.getId());
	}

	@Override
	public Brand brand(BrandArgs args) {
		return new Brand.Unresolved(args.getId());
	}
}

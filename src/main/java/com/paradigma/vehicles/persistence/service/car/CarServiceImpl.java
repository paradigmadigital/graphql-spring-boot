package com.paradigma.vehicles.persistence.service.car;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paradigma.graphql.schema.car.car.Car;
import com.paradigma.graphql.schema.car.car.create.InputCreateCar;
import com.paradigma.vehicles.persistence.model.CarMO;
import com.paradigma.vehicles.persistence.model.ModelMO;
import com.paradigma.vehicles.persistence.repository.car.CarRepository;
import com.paradigma.vehicles.persistence.repository.model.ModelRepository;
import com.paradigma.vehicles.persistence.service.car.transformer.CarTransformer;

/**
 * Service con todos los métodos referentes a la entidad Car
 * 
 * @author manuel
 *
 */
@Service
public class CarServiceImpl implements CarService {

	@Autowired
	CarRepository carRepository;

	@Autowired
	ModelRepository modelRepository;

	@Autowired
	CarTransformer carTransformer;

	/**
	 * Obtenemos el elemento por el Id
	 * 
	 * @param id
	 * @return
	 */
	public Car findById(String id) {
		CarMO carMO = carRepository.findOne(id);
		Car carMOToCar = null;
		if (carMO != null) {
			carMOToCar = carTransformer.carMOToCar(carMO);
		}
		return carMOToCar;
	}

	/**
	 * Retornamos todos los datos sin filtros
	 * 
	 * @return
	 */
	public List<Car> findAll() {
		List<CarMO> carsMO = carRepository.findAll();
		List<Car> cars = carsMO.stream().map(mapper -> carTransformer.carMOToCar(mapper)).collect(Collectors.toList());
		return cars;
	}

	/**
	 * Creamos un coche con los datos de entrada adecuados
	 * 
	 * @param input
	 * @return
	 */
	public Car createCar(InputCreateCar input) {
		CarMO carMO = new CarMO();
		carMO.setColor(input.getColor());

		/* Comprobamos si existe el modelo del cual queremos crear el coche */
		ModelMO modelMO = modelRepository.findOne(input.getModelId());
		if (modelMO == null) {
			throw new NoSuchElementException("El modelo no existe en la base de datos ");
		}
		carMO.setModel(modelMO);
		/* Almacenamos el elemento */
		carRepository.save(carMO);
		return findById(carMO.getId());
	}

	
	/**
	 * Borrado de un coche 
	 * 
	 * @param id
	 * @return
	 */
	public String deleteCar(String id) {
		CarMO carToDelete = carRepository.findOne(id);
		if (carToDelete == null) {
			throw new NoSuchElementException();
		}
		carRepository.delete(carToDelete);
		return carToDelete.getId();

	}

}

package com.paradigma.persistence.service.brand;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paradigma.graphql.schema.car.brand.Brand;
import com.paradigma.persistence.model.BrandMO;
import com.paradigma.persistence.repository.brand.BrandRepository;
import com.paradigma.persistence.service.brand.transformer.BrandTransformer;

/**
 * Service con todos los métodos referentes a la entidad Brand
 * 
 * @author manuel
 *
 */
@Service
public class BrandServiceImpl implements BrandService {

	@Autowired
	BrandRepository brandRepository;

	@Autowired
	BrandTransformer brandTransformer;

	/**
	 * Obtenemos el elemento por el Id
	 * 
	 * @param id
	 * @return
	 */
	public Brand findById(String id) {
		BrandMO brandMO = brandRepository.findOne(id);
		Brand brandMOToBrand = null;
		if (brandMO != null) {
			brandMOToBrand = brandTransformer.brandMOToBrand(brandMO);
		}
		return brandMOToBrand;
	}

	/**
	 * Retornamos todos los datos sin filtros
	 * 
	 * @return
	 */
	public List<Brand> findAll() {
		List<BrandMO> brandsMO = brandRepository.findAll();
		List<Brand> brands = brandsMO.stream().map(mapper -> brandTransformer.brandMOToBrand(mapper))
				.collect(Collectors.toList());
		return brands;
	}

}

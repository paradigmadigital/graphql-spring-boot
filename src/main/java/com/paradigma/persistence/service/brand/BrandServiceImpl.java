package com.paradigma.persistence.service.brand;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paradigma.persistence.repository.brand.BrandRepository;

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
	
}

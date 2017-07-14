package com.paradigma.vehicles.persistence.service.populate;

import java.net.URL;

import javax.annotation.PostConstruct;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.CharEncoding;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.ScriptOperations;
import org.springframework.data.mongodb.core.script.ExecutableMongoScript;
import org.springframework.stereotype.Service;

import com.google.common.io.Resources;
import com.paradigma.vehicles.persistence.service.populate.config.PopulateConfig;

/**
 * Clase de carga de nuestros datos de prueba por entornos
 * 
 * @author manuel
 *
 */
@Service
public class PopulateServiceImpl implements PopulateService {

	/** Ruta dentro del classpath del fichero */
	private static final String SCRIPT_PATH = "data/mongo_model.js";
	
	@Autowired
	MongoTemplate mongoTemplate;
	
	@Autowired
	PopulateConfig populateConfig;

	
	
	@PostConstruct
	public void postConstructBean() throws Exception {
		if (BooleanUtils.isTrue(populateConfig.getActive())){
			databasePopulate();
		}
	}
	
	/**
	 * Generamos datos de prueba cargando el script 
	 */
	public void databasePopulate() throws Exception {
		/* Ejecutamos el script */
		ScriptOperations scriptOps = mongoTemplate.scriptOps();
		URL url = Resources.getResource(SCRIPT_PATH);
		
		/* Leemos del fichero */
		String mongoScriptContent = IOUtils.toString(url, CharEncoding.UTF_8);		
		ExecutableMongoScript echoScript = new ExecutableMongoScript(mongoScriptContent);
		scriptOps.execute(echoScript);     		
	}

}

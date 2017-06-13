package com.paradigma.vehicles.persistence.service.populate;

import java.io.File;
import java.net.URL;

import javax.annotation.PostConstruct;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.CharEncoding;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.ScriptOperations;
import org.springframework.data.mongodb.core.script.ExecutableMongoScript;
import org.springframework.stereotype.Service;

import com.google.common.io.Resources;

/**
 * Clase de carga de nuestros datos de prueba por entornos
 * 
 * @author manuel
 *
 */
@Service
public class PopulateServiceImpl implements PopulateService {

	/** */
	private static final String SCRIPT_PATH = "data/mongo_model.js";
	
	@Autowired
	MongoTemplate mongoTemplate;

	

	/**
	 * Generamos datos de prueba cargando el script 
	 */
	@PostConstruct
	public void databasePopulate() throws Exception {
		
		/* Ejecutamos el script */
		ScriptOperations scriptOps = mongoTemplate.scriptOps();
		URL url = Resources.getResource(SCRIPT_PATH);
		/* Leemos del fichero */
		String mongoScriptContent = FileUtils.readFileToString(new File(url.toURI()), CharEncoding.UTF_8);		
		ExecutableMongoScript echoScript = new ExecutableMongoScript(mongoScriptContent);
		scriptOps.execute(echoScript);     		
	}

}

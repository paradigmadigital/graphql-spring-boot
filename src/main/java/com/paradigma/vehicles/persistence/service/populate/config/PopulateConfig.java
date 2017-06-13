package com.paradigma.vehicles.persistence.service.populate.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Clase con la configuración del populate si creciera más adelante 
 * 
 * @author manuel
 *
 */
@ConfigurationProperties("populate")
@Component
public class PopulateConfig {

	private Boolean active;

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}
	
}

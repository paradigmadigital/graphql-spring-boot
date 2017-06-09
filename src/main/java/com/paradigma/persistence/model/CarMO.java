package com.paradigma.persistence.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Modelado de un coche. Ojo se ha realizado normalizado totalmente 
 * 
 * @author manuel
 *
 */
@Document
public class CarMO {

	@Id
	private String id;

	@DBRef
	private ModelMO model;

	private String color;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public ModelMO getModel() {
		return model;
	}

	public void setModel(ModelMO model) {
		this.model = model;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}
}

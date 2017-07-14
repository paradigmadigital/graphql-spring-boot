package com.paradigma.vehicles.graphql.base;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase base para los resolvers con la implementación por defecto
 * 
 * @author manuel
 *
 */
public abstract class BaseResolver<T>  {

	/**
	 * Resuelve la obtención de los datos con la implementación por defecto  
	 * 
	 * @param unresolvedList
	 * @return
	 */
	public List<T> resolve(List<T> unresolvedList) {
		List<T> resolvedList = new ArrayList<>();
		/* Miramos si no es null y nos viene con valor para obtenerlo si es necesario */
		if (!requireNonNull(unresolvedList).isEmpty()) {
			for (T element : unresolvedList) {
				if (element != null) {
					resolvedList.add(processElement(element));
				}
			}
		}
		return resolvedList;
	}

	/**
	 * Comprobaremos si está resuelta la entidad y si no es así obtenemos de la persistencia el "completo"
	 * 
	 * @param unresolverdEntity
	 * @return
	 */
	private T processElement(final T unresolved) {
		/* Obtenemos el elemto filtrado por lo que corresponda */
		if (unresolved.getClass().equals(unresolvedClass())) {
			T findElement = findById(unresolved);
			return findElement;
		}
		return unresolved;
	}

	/**
	 * Clase a implementar para la obtencion de los elementos relacionados
	 * 
	 * @param unresolved
	 * @return
	 */
	protected abstract T findById(T unresolved);
	
	/**
	 * Clase la cual indicará wue una entidad no está resuelta 
	 * 
	 * @return
	 */
	protected abstract Class<?> unresolvedClass();
	
}

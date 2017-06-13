# POC Graphql


La idea es realizar una prueba de concepto con java para Graphql. 


## Stack tecnológico:

```
  Spring Boot + MongoDB + Graphql + Logback
```

Para la integración con Graphql he estado mirando varios frameworks diferentes que faciliten la integración entre ellos y fácil implementación. Algunas de los argumentos para desechar muchos de ellos serían:


  - Declaración de esquema muy "verboso". Declarar un schema de Graphql en Java es poco natural aun usando frameworks que lo facilitan con anotaciones, la declaración pasa a ser compleja ,tediosa, nada reutilizable ... y sobre todo sobre un entorno fácil de cometer errores. ([Spring common](https://github.com/oembedler/spring-graphql-common) ...)


  - Los que se refieren a código generado (Schema First), algunos no soportan tipos básicos, otros obligan a generarte tu mismo los pojos [Graphql tools](https://github.com/graphql-java/graphql-java-tools) aunque sí ofrecen parseo de esquema ...


  - En el caso de la integración con Spring-JPA creo que es demasiado rígida en cuanto a la filosofía de Graphql y se asume que schema = modelo, y bajo
  dicha premisa sería mejor usar un Spring Data Rest


  - Por último otros cuantos acusan la falta de documentación y pocas actualizaciones del repo ..


## Frameworks utilizados:

- [Graphql para java](https://github.com/graphql-java/graphql-java)
- [Graphql-apigen] (https://github.com/Distelli/graphql-apigen) 
      *** Nota: posee un bug en la generación que ya tienen un Merge request (no incluye un impor, basta hacerlo a mano para que funcione de momento)
      
	Filosofía Schema first :
	- Genera todos los POJOS (Input, Entidades …) 
	- Permite estructuración en paquetes de un modo sencillo
	- Genera armazón de clases para la inclusión nuestra programación, y es extensible, usa intefaces para todo.
	- Genera clases @fluent
	- Integración básica con Guice y Spring
	- Plugin de maven de generación de los fuentes "mvn clean compile" 

- [Graphql-Spring-Boot](https://github.com/graphql-java/graphql-spring-boot) , este es el oficial , no obstante existe una aproximación muy buena también que sería
     [Spring Boot starter Graphql](https://github.com/merapar/spring-boot-starter-graphql)

	- Se usa configuración por convención
	- Implementa la exposición mediante endpoint Http del Graphql Server completa
	- Permite varias opciones de configuración, entre ellas las estrategias de ejecución de consultas, prefijos de los roles de los objetos ...
	- Incluye herramienta oficial para consultas [Graphiql] (https://github.com/graphql/graphiql), accesible desde el navegador para la construcción de consultas y mutaciones.



## Requisitos

Java 1.8
Maven 3.x
MongoDB 3.X

## Construcción

```
mvn clean compile
```


**** Nota: posee un bug en la generación que ya tienen un Merge request (no incluye un impor, basta hacerlo a mano para que funcione de momento) por lo tanto
se deberá tocar a mano desde tu IDE la clase que no compila añadiendo el import que falta . Esto es temporal


## Ejecución

**** Ver nota anterior
```
java -jar target/poc-grapql-api-gen-fat.jar
```


## Esquema

Accede tu mismo al [editor de consultas](http://localhost:8080/) del proyecto, o emplea si deseas [uno externo](https://lucasconstantino.github.io/graphiql-online/)


```graphql
# Coche
type Car @java(package:"com.paradigma.graphql.schema.car.car") {
    id: String! # ! significa obligatorio
    model: Model!
    color: String
    
}

# Root query
type QueryRoot @java(package:"com.paradigma.graphql.schema.car.root.query") {
    cars: [Car] # Listado de los coches
    car(id: String!): Car # Coche por id 
    models(id: String): [Model] # Listado de los modelos
    model(id: String!): Model # Model por id 
    brands(id: String): [Brand] # Listado de las marcas
    brand(id: String!): Brand # Marca por id 
}

# Modelo
type Model @java(package:"com.paradigma.graphql.schema.car.model") {
    id: String!
    name: String
    brand: Brand!
    year: Int
}

# Modelo
type ModelQuery @java(package:"com.paradigma.graphql.schema.car.model") {
    models(id: String): [Model] # Listado de los modelos
    model(id: String!): Model # Model por id 
}


# Brand
type BrandQuery @java(package:"com.paradigma.graphql.schema.car.brand") {
    brands(id: String): [Brand] # Listado de las marcas
    brand(id: String!): Brand # Marca por id 
}


# Marca
type Brand @java(package:"com.paradigma.graphql.schema.car.brand") {
    id: String!
    name: String
}

# Objeto de entrada para agrupar las propiedades de un coche y crearlo
input InputCreateCar @java(package:"com.paradigma.graphql.schema.car.car.create") {
    modelId: String!
    color: String!
}

# Operaciones sobre los coches:
type MutateCars @java(package:"com.paradigma.graphql.schema.car.car") {
     createCar(car:InputCreateCar): Car
     deleteCar(id: String!): String
}


```

Ejemplos de consultas:

```graphql

{
  cars {
    id
  }
}

```

```graphql

{
mutation {
  createCar(car: {modelId: "593ebb10674d4c0bef6c4c2a", color: "Green"}) {
    id
    model{
      id
    }
    color
  }
 
}

```


```graphql


```graphql

 {
  cars {
    id
    color
    model {
      id
      name
    }
  }
 
  models{
    id
  }
  
  car(id: "asdfadfas"){
    id
  }
}

```

```



## Por hacer

 - Integrar cache
 - Incorporar ejemplo de paginación
 - Incorporar relaciones más complejas
 - Medir rendimiento









# Vert.x GraphQL Example

![VG](https://raw.githubusercontent.com/bmsantos/vertx-graphql-example/master/vertx-graphql-mic-drop.png) 

When it comes to performance and scalability, Vert.x has always been hard to beat and version 3 just made it much easier to develop and deploy. 

This simple application is used to demonstrate:

- that Java CompletableFuture, Vert.x Futures and RxJava can be easily combined
- that Vert.x micro-services are easy to develop and deploy through Docker containers

The goal of this application is to exercise graphql-java async (non-blocking) with Vert.x.

In addition it also uses:

- [graphql-apigen](https://github.com/bmsantos/graphql-apigen/tree/async) - to facilitate the graphql schema generation
- [vertx-dataloader](https://github.com/engagingspaces/vertx-dataloader) - to ensure a consistent API data fetching between the different resources


## System Architecture 

```text
                    .---------.       .-----------.
  POST /graphql --> | GraphQL |       | Customer  |
                    | Service | ----> | Service   |
                    '---------'   |   '-----------'
                                  |   .-----------.   
                                  |   | Vehicle   |
                                  |-> | Service   |
                                  |   '-----------'
                                  |   .-----------.
                                  |   | Rental    |
                                  '-> | Service   |
                                      '-----------'
```


## Before you start

```graphql-java-async``` is not out yet. In order to build this project you need to:

 1. ```graphql-java``` - Checkout and build Dmitry's [async branch](https://github.com/dminkovsky/graphql-java/tree/async)
 1. ```graphql-apigen``` - Checkout and build the [eb_graphql branch](https://github.com/bmsantos/graphql-apigen/tree/eb_graphql) of my fork of [Distelli/graphql-apigen](https://github.com/Distelli/graphql-apigen)
 
## Build:

After building the async branches of both graphql-java and graphql-apigen do:

```sh
mvn clean package
```


## Execute:

```sh
./docker/run.sh
```


## Test

The graphql-service exposes a POST endpoint. You can use CURL but it is recommended to use [Graphiql App](https://github.com/skevy/graphiql-app).

Sample queries to use on a POST to http://localhost:8080/graphql.


### Querying for a single rental entry:
```graphql
{
  rental(id: 1) {
    id
    customer {
      id
      name
      address
      city
      state
      country
      contact {
        phone
        type
      }
    }
    vehicle {
      id
      brand
      model
      type
      year
      mileage
      extras
    }
  }
}
```


### Querying for all active rentals:
```graphql
{
  rentals {
    id
    customer {
      id
      name
      address
      city
      state
      country
      contact {
        phone
        type
      }
    }
    vehicle {
      id
      brand
      model
      type
      year
      mileage
      extras
    }
  }
}
```


### Example using CURL:
```bash
curl -k -X POST -d '{ "operationName": null, "query": "{ rentals { customer { name } vehicle { brand model } } }", "variables": "{}" }' http://localhost:8080/graphql
```

/* Eliminamos todo lo que hubiera */
db.getCollection('brandMO').drop();
db.getCollection('modelMO').drop();
db.getCollection('carMO').drop();

db.getCollection('brandMO').insert(
[
{
    "_id" : ObjectId("593ebb10674d4c0bef6c4c29"),
    "_class" : "com.paradigma.vehicles.persistence.model.BrandMO",
    "name" : "Seat"
}
]
);

db.getCollection('modelMO').insert(
[
{
    "_id" : ObjectId("593ebb10674d4c0bef6c4c2a"),
    "_class" : "com.paradigma.vehicles.persistence.model.ModelMO",
    "name" : "Ibiza",
    "year" : 2011,
    "brand" : {
        "$ref" : "brandMO",
        "$id" : ObjectId("593ebb10674d4c0bef6c4c29")
    }
}
]
);

db.getCollection('carMO').insert(
[
{
    "_id" : ObjectId("593ebb10674d4c0bef6c4c2b"),
    "_class" : "com.paradigma.vehicles.persistence.model.CarMO",
    "color" : "Rojo",
    "model" : {
        "$ref" : "modelMO",
        "$id" : ObjectId("593ebb10674d4c0bef6c4c2a")
    }
}
,
/* 2 */
{
    "_id" : ObjectId("593ebb42674d4c0bef6c4c2c"),
    "_class" : "com.paradigma.vehicles.persistence.model.CarMO",
    "color" : "Blue",
    "model" : {
        "$ref" : "modelMO",
        "$id" : ObjectId("593ebb10674d4c0bef6c4c2a")
    }
}
,
/* 3 */
{
    "_id" : ObjectId("593ec00e674d4c0c26f0e120"),
    "_class" : "com.paradigma.vehicles.persistence.model.CarMO",
    "color" : "Green",
    "model" : {
        "$ref" : "modelMO",
        "$id" : ObjectId("593ebb10674d4c0bef6c4c2a")
    }
}
]
);

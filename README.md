# Introducción 
Ejemplo de proyecto modular basado en Spring Boot

La arquitectura del proyecto está inspirado en arquitectura hexagonal:
https://alistair.cockburn.us/hexagonal-architecture/

Requerimientos mínimos:
- Java JDK 17
- Maven 3.9.6
- Base de datos postgres o cualquier otro motor.

Script de creación de tablas de ejemplo:
```sql
CREATE TABLE public.categories (
	id int8 GENERATED ALWAYS AS IDENTITY NOT NULL,
	"name" varchar NULL
);
CREATE TABLE public.products (
	id int4 GENERATED ALWAYS AS IDENTITY NOT NULL,
	"name" varchar NULL,
	stock int4 NULL,
	price float8 NULL,
	pvp float8 NULL,
	has_discount bool NULL,
	category_id int4 NULL
);
INSERT INTO public.categories ("name") VALUES
	 ('Categoria');
INSERT INTO public.products ("name",stock,price,pvp,has_discount,category_id) VALUES
	 ('Mi producto',2,50.0,55.0,true,1);


```

# Construir y levantar el proyecto:
```cmd
mvn clean install
mvn spring-boot:run -pl bootstrap
```

# Probar su funcionamiento
```
curl http://localhost:8080/products/1
```



CREATE OR REPLACE DATABASE daw1;
USE daw1;

CREATE TABLE alumno (
    nombre VARCHAR(30) NULL DEFAULT NULL,
    intervenciones INT NULL DEFAULT NULL,
    fecha_intervencion DATE DEFAULT CURRENT_TIMESTAMP
);
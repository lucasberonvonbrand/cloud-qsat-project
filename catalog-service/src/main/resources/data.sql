-- =================================================================================
-- 1. CARACTERÍSTICAS (Las 8 de ISO 25010)
-- =================================================================================
INSERT IGNORE INTO characteristic (id, name, description) VALUES
(1, 'Adecuación Funcional', 'Grado en el que el producto proporciona funciones que satisfacen las necesidades declaradas.'),
(2, 'Eficiencia de Desempeño', 'Desempeño relativo a la cantidad de recursos utilizados bajo condiciones establecidas.'),
(3, 'Compatibilidad', 'Capacidad de intercambiar información con otros productos o componentes.'),
(4, 'Usabilidad', 'Capacidad de ser entendido, aprendido y usado por el usuario.'),
(5, 'Fiabilidad', 'Capacidad de desempeñar las funciones especificadas bajo condiciones específicas.'),
(6, 'Seguridad', 'Protección de la información y los datos.'),
(7, 'Mantenibilidad', 'Capacidad de ser modificado efectiva y eficientemente.'),
(8, 'Portabilidad', 'Capacidad de ser transferido de un entorno a otro.');

-- =================================================================================
-- 2. SUBCARACTERÍSTICAS (Selección Estratégica)
-- =================================================================================

-- Adecuación Funcional
INSERT IGNORE INTO sub_characteristic (id, name, characteristic_id) VALUES (11, 'Completitud Funcional', 1);

-- Performance
INSERT IGNORE INTO sub_characteristic (id, name, characteristic_id) VALUES (21, 'Comportamiento Temporal', 2);
INSERT IGNORE INTO sub_characteristic (id, name, characteristic_id) VALUES (22, 'Utilización de Recursos', 2);

-- Usabilidad
INSERT IGNORE INTO sub_characteristic (id, name, characteristic_id) VALUES (41, 'Protección contra errores', 4);
INSERT IGNORE INTO sub_characteristic (id, name, characteristic_id) VALUES (42, 'Operabilidad', 4);

-- Fiabilidad (SRE Focus)
INSERT IGNORE INTO sub_characteristic (id, name, characteristic_id) VALUES (51, 'Disponibilidad', 5);
INSERT IGNORE INTO sub_characteristic (id, name, characteristic_id) VALUES (52, 'Recuperabilidad', 5);

-- Seguridad (OWASP Focus)
INSERT IGNORE INTO sub_characteristic (id, name, characteristic_id) VALUES (61, 'Confidencialidad', 6);
INSERT IGNORE INTO sub_characteristic (id, name, characteristic_id) VALUES (62, 'Integridad', 6);

-- Mantenibilidad (Code Quality Focus)
INSERT IGNORE INTO sub_characteristic (id, name, characteristic_id) VALUES (71, 'Modularidad', 7);
INSERT IGNORE INTO sub_characteristic (id, name, characteristic_id) VALUES (72, 'Analizabilidad', 7);

-- =================================================================================
-- 3. MÉTRICAS AVANZADAS (MOTOR DE REGLAS)
-- Estas métricas usan tus Enums: MetricType y ComparisonOperator
-- =================================================================================

-- 1. PERFORMANCE: Apdex Score (Satisfacción de Usuario)
-- Umbral: Debe ser mayor a 0.85 (Excelente)
INSERT IGNORE INTO metric (id, code, name, description, type, operator, threshold, sub_characteristic_id)
VALUES (201, 'PERF-APDEX', 'Apdex Score', 'Índice de rendimiento de aplicaciones (0 a 1). Target > 0.85', 'NUMERIC', 'GREATER_THAN', 0.85, 21);

-- 2. PERFORMANCE: Latencia de API
-- Umbral: Menor a 500ms
INSERT IGNORE INTO metric (id, code, name, description, type, operator, threshold, sub_characteristic_id)
VALUES (202, 'PERF-LATENCY', 'Latencia Media API', 'Tiempo promedio de respuesta del backend en ms.', 'NUMERIC', 'LESS_THAN', 500.0, 21);

-- 3. FIABILIDAD: MTTR (Mean Time To Recovery)
-- Umbral: Menor a 30 minutos
INSERT IGNORE INTO metric (id, code, name, description, type, operator, threshold, sub_characteristic_id)
VALUES (501, 'REL-MTTR', 'MTTR (Recovery Time)', 'Tiempo promedio para restaurar el servicio tras caída (minutos).', 'NUMERIC', 'LESS_THAN', 30.0, 52);

-- 4. FIABILIDAD: Disponibilidad Mensual
-- Umbral: 99.9% (Tres nueves)
INSERT IGNORE INTO metric (id, code, name, description, type, operator, threshold, sub_characteristic_id)
VALUES (502, 'REL-AVAIL', 'Disponibilidad (SLA)', 'Porcentaje de tiempo online mensual.', 'PERCENTAGE', 'GREATER_THAN_OR_EQUAL', 99.9, 51);

-- 5. SEGURIDAD: Vulnerabilidades Críticas (SAST)
-- Umbral: 0 (Tolerancia Cero)
INSERT IGNORE INTO metric (id, code, name, description, type, operator, threshold, sub_characteristic_id)
VALUES (601, 'SEC-SAST', 'Vulnerabilidades Críticas', 'Cantidad de hallazgos críticos en análisis estático de código.', 'NUMERIC', 'EQUALS', 0.0, 62);

-- 6. SEGURIDAD: Encriptación en Reposo
-- Umbral: 1 (True/Si)
INSERT IGNORE INTO metric (id, code, name, description, type, operator, threshold, sub_characteristic_id)
VALUES (602, 'SEC-ENC', 'Encriptación DB', '¿La base de datos está encriptada en reposo? (1=Si, 0=No)', 'BOOLEAN', 'EQUALS', 1.0, 61);

-- 7. MANTENIBILIDAD: Deuda Técnica
-- Umbral: Menor al 5%
INSERT IGNORE INTO metric (id, code, name, description, type, operator, threshold, sub_characteristic_id)
VALUES (701, 'MAINT-DEBT', 'Technical Debt Ratio', 'Ratio de deuda técnica según SonarQube.', 'PERCENTAGE', 'LESS_THAN', 5.0, 72);

-- 8. MANTENIBILIDAD: Code Coverage
-- Umbral: Mayor a 80%
INSERT IGNORE INTO metric (id, code, name, description, type, operator, threshold, sub_characteristic_id)
VALUES (702, 'MAINT-COV', 'Code Coverage', 'Cobertura de pruebas unitarias.', 'PERCENTAGE', 'GREATER_THAN_OR_EQUAL', 80.0, 71);
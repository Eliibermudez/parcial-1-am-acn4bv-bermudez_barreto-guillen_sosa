# CAMPUS ESTUDIANTIL - PARCIAL 1 APLICACIONES MÓVILES

---

# INFORMACIÓN DE LAS ALUMNAS

## Alumnas

* **Nombre:** Eliana Bermúdez
* **Nombre:** Llubisay Guillén

## Carrera

* **Analista de Sistemas**

## Comisión

* **ACN1BV**

---

# APP MOBILE: CAMPUS ESTUDIANTIL

Aplicación móvil desarrollada en Android que permite a los estudiantes gestionar su información académica, materias, grupos de trabajo, novedades, calendario de parciales y perfil de usuario de forma simple e intuitiva.

---

# CONCEPTOS APLICADOS

El proyecto implementa:

* ConstraintLayout
* LinearLayout (vertical y horizontal)
* ScrollView
* TextView
* Button
* ImageView
* BottomNavigationView
* Uso de recursos (strings, colores, dimensiones)
* Uso de drawables personalizados (shapes, backgrounds)
* Manejo de eventos con setOnClickListener
* Navegación entre pantallas mediante Intent
* Ciclo de vida de Activities (onCreate, onResume)
* Creación dinámica de elementos desde Java
* Uso de repositorios en memoria (Patrón Repository)
* Carga de imágenes desde URL utilizando Glide

---

# PANTALLAS IMPLEMENTADAS

El flujo actual de la aplicación incluye las siguientes pantallas:

## Login
Pantalla de acceso inicial que presenta:

* Interfaz centrada
* Logo institucional
* Campo email
* Campo contraseña
* Botón de ingreso a la aplicación

---

## Home

Pantalla principal con menú de navegación que permite acceder a:

* Materias
* Grupos
* Calendario
* Novedades
* Perfil

---

## Materias

La pantalla Materias permite al usuario:

* Visualizar las materias en las que está inscripto
* Mostrar las materias dentro de un ScrollView
* Agregar nuevas materias dinámicamente

Agregar Materias

* Formulario para carga de nueva materia
* Generación dinámica de cards en la pantalla de materias

---

## Grupos

La pantalla Grupos permite al usuario:

* Visualizar el listado de grupos disponibles
* Identificar el estado de cada grupo (abierto/cerrado)
* Visualizar grupos propios

Agregar Grupos

* Creación de nuevos grupos mediante formulario
* Renderizado dinámico en la lista

## Novedades

* Visualización de novedades institucionales
* Marcado de novedades como leídas
* Contador dinámico de novedades pendientes
* Uso de imágenes remotas con Glide

## Calendario

* Visualización de calendario
* Visualización de parciales

## Perfil

* Imagen de perfil cargada desde URL
* Visualización de datos del usuario

Editar Perfil

* Edición de información personal
* Persistencia temporal mediante Repository
* Actualización automática al volver a la pantalla de perfil

---
# MOCKUPS

Los mockups de las pantallas se encuentran en la carpeta:

docs/mockups

Pantallas incluidas:

* Login
* Home
* Materias
* Agregar materia
* Grupos de trabajo
* Crear grupo
* Fechas de parciales
* Novedades
* Perfil
* Editar perfil

---

# ARQUITECTURA

La aplicación utiliza una arquitecturabasada en:

* Activities como controladores de UI
* Repositorios en memoria para persistencia temporal
* Modelos de datos

Esto permite una implementación clara y alineada con los objetivos del MVP.

# COMPORTAMIENTO DINÁMICO

Se implementan múltiples comportamientos dinámicos:

* Creación de materias, grupos y parciales desde Java
* Actualización de UI en tiempo real
* Eliminación de elementos mediante interacción del usuario
* Estado de novedades (leídas / no leídas)
* Actualización automática de datos al volver a pantalla (onResume)

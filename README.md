# CAMPUS ESTUDIANTIL - PARCIAL 2 APLICACIONES MÓVILES
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

# CREDENCIALES TEST 
  - Email: juan@gmail.com 
  - Contraseña: 1234  

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
* Validación simple de campos obligatorios
* Validación básica de formato de email
* Botón de ingreso a la aplicación

<img width="300" src="https://github.com/user-attachments/assets/f117d997-2229-41db-b6bc-0c3280caeb18" />

---

## Home

Pantalla principal de la aplicación presenta:

* Mensaje de bienvenida
* Banner informativo
* Accesos rápidos mediante cards visuales
* Navegación hacia Materias, Grupos, Calendario y Novedades
* Resumen de actividad académica
* BottomNavigationView para acceder a las secciones principales

<img width="300" src="https://github.com/user-attachments/assets/4968a2bc-62ca-4c4b-b959-1196941ae9fd" />

---

## Materias

La pantalla Materias permite al usuario:

* Visualizar las materias en las que está inscripto
* Mostrar las materias dentro de un ScrollView
* Agregar nuevas materias dinámicamente
* Eliminar materias de la lista

<img width="300" src="https://github.com/user-attachments/assets/00bcd448-e777-4169-a46d-9c947376356e" />


Agregar Materias

* Formulario para carga de nueva materia
* Generación dinámica de cards en la pantalla de materias

<img width="300" src="https://github.com/user-attachments/assets/6ff96aba-a2db-4bb9-9bee-6d86ed4d4e22" />

---

## Grupos

La pantalla Grupos permite al usuario:

* Visualizar el listado de grupos disponibles
* Identificar el estado de cada grupo (abierto/cerrado)
* Visualizar grupos propios
  
<img width="300" src="https://github.com/user-attachments/assets/2b7d5e13-1b03-44d4-801e-f43f2e4e1db6" />

<img width="300" src="https://github.com/user-attachments/assets/348d42c9-7b15-44ba-b0f7-fa5361769867" />

Agregar Grupos

* Creación de nuevos grupos mediante formulario
* Renderizado dinámico en la lista
  
<img width="300" src="https://github.com/user-attachments/assets/6e34933f-8daa-46cc-8486-2c7e289a316d" />

## Novedades

* Visualización de novedades institucionales
* Marcado de novedades como leídas
* Contador dinámico de novedades pendientes
* Uso de imágenes remotas con Glide

<img width="300" src="https://github.com/user-attachments/assets/6e674dbb-98a7-4da0-add9-d1bc4b7c7ebf" />

## Calendario

* Visualización de calendario
* Visualización de parciales

<img width="300" src="https://github.com/user-attachments/assets/6989a215-bea3-4050-b1ed-b37c31340800" />

## Perfil

* Imagen de perfil cargada desde URL
* Visualización de datos del usuario
  
<img width="300" src="https://github.com/user-attachments/assets/c7f65323-000e-40c2-b6ba-b555e7f7023d" />

Editar Perfil

* Edición de información personal
* Persistencia temporal mediante Repository
* Actualización automática al volver a la pantalla de perfil

<img width="300" src="https://github.com/user-attachments/assets/1f26304a-8264-4bdf-89ee-4b6d91d80d93" />

---
# MOCKUPS

Los mockups de las pantallas se encuentran en la carpeta:

docs/mockups

Estos mockups fueron utilizados como referencia visual y funcional durante la primera etapa del desarrollo
Durante la implementación se realizaron ajustes de diseño, paleta de colores y estructura de algunas pantallas para mejorar la experiencia de usuario y adaptar la aplicación al resultado final

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

Aclaración: en los mockups iniciales se había planteado una pantalla de Chat. Durante el desarrollo, esta funcionalidad fue redefinida como Novedades, ya que resultaba más adecuada para el alcance del proyecto y permitía incorporar avisos institucionales, contador dinámico e imagen remota mediante Glide

---

# CAPTURAS DE LA APP FINAL

Además de los mockups iniciales, se incluyen capturas de la aplicación final implementada en Android Studio dentro de la carpeta:

docs/screenshots

Estas capturas permiten visualizar el resultado final de las pantallas, incluyendo los cambios aplicados en la paleta de colores, accesos rápidos, navegación inferior, cards dinámicas y componentes visuales

---
# FLUJO DE NAVEGACIÓN

El flujo principal de la aplicación es:

Login -> Home

Desde Home el usuario puede acceder a:

* Materias
* Grupos
* Calendario
* Novedades
* Perfil

Además, la aplicación cuenta con una BottomNavigationView que permite navegar entre las secciones principales desde distintas pantallas

---

# ARQUITECTURA

La aplicación utiliza una arquitectura basada en:

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

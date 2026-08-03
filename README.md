# CAMPUS ESTUDIANTIL - FINAL APLICACIONES MÓVILES
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

Aplicación móvil desarrollada en Android que permite a los estudiantes gestionar su información académica, materias, grupos de trabajo, novedades, calendario de parciales, perfil de usuario y contacto de forma simple e intuitiva.

---

# CREDENCIALES TEST

Usuario de prueba:

- Email: estudiante@campus.com
- Contraseña: 123456

También se puede crear una cuenta nueva desde la pantalla de Registro  

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
* Firebase Authentication para login, registro y cierre de sesión
* Firebase Firestore para guardar y consultar datos de usuarios
* Firebase Firestore para cargar novedades dinámicas
* Lectura de datos del usuario autenticado mediante UID
* Actualización de datos del perfil en Firestore
* Google Maps SDK para Android (visualización de mapa y marcador)
* Intents implícitos para navegación con Google Maps
* Reutilización de lógica mediante clase utilitaria (AuthUtils)
* Cierre de sesión global desde múltiples pantallas

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

<img width="300" alt="image" src="https://github.com/user-attachments/assets/2e842c7d-3a18-49ab-8327-9136d5e38fdf" />


---
## Registro

Pantalla que permite crear una cuenta nueva dentro de la aplicación

Incluye:

* Campo nombre
* Campo email
* Campo contraseña
* Campo confirmar contraseña
* Validación de campos obligatorios
* Validación básica de email
* Validación de coincidencia de contraseñas
* Registro mediante Firebase Authentication
* Creación automática del perfil inicial en Firestore

Al registrarse correctamente, se crea un documento en la colección `usuarios`, utilizando el UID generado por Firebase Authentication

<img width="300" alt="image" src="https://github.com/user-attachments/assets/49efae0f-fbd3-4a18-89ff-ccf51bdc1cbf" />

---

## Home

Pantalla principal de la aplicación presenta:

* Mensaje de bienvenida personalizado con el nombre del usuario logueado
* Banner informativo
* Accesos rápidos mediante cards visuales
* Navegación hacia Materias, Grupos, Perfil, Calendario,  Novedades y Contacto
* Resumen de actividad académica
* BottomNavigationView para acceder a las secciones principales
* Lectura del nombre del usuario desde Firestore

<img width="300" alt="image" src="https://github.com/user-attachments/assets/9ca6f088-01b4-47fc-a8dd-6cb44a251685" />


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
* Carga de novedades desde Firebase Firestore
* Visualización de título, descripción, categoría e imagen
* Marcado de novedades como leídas
* Contador dinámico de novedades pendientes
* Uso de imágenes remotas con Glide

La colección utilizada en Firestore se llama `novedades`.

Campos utilizados:

* titulo
* descripcion
* categoria
* fecha
* imagenUrl

<img width="300" alt="image" src="https://github.com/user-attachments/assets/3d5ec5e2-5d7d-45a7-82c4-68ae33a820b6" />


## Calendario

La pantalla Calendario permite al usuario:

* Visualizar parciales académicos
* Renderizar cards dinámicamente desde Java
* Mostrar materia, fecha y detalle del parcial
* Uso de colores personalizados por materia
* Eliminación de parciales mediante long press
* Persistencia temporal utilizando un Repository en memoria

El contenido se actualiza automáticamente en `onResume`, permitiendo reflejar cambios dinámicos en la UI.

<img width="300" alt="image" src="https://github.com/user-attachments/assets/26a50079-6b99-4abd-846d-f0fe52032990" />


## Contacto

Pantalla que permite visualizar la ubicación del campus y acceder a navegación externa.

Incluye:

* Integración con Google Maps SDK
* Visualización de mapa interactivo
* Marcador con ubicación del campus
* Zoom automático sobre la ubicación
* Visualización de nombre y dirección al seleccionar el marcador

Funcionalidades:

* Botón "Ir" que abre Google Maps con navegación directa mediante Intent implícito
* Uso de URI `google.navigation` para guiar al usuario
* Integración con BottomNavigationView y menú "Más"

Tecnologías utilizadas:

* Google Maps SDK
* Intent ACTION_VIEW
* Coordenadas geográficas (LatLng)
  
<img width="300" alt="image" src="https://github.com/user-attachments/assets/b5a8b314-a775-444c-8a04-428c29be3f80" />


## Perfil

* Imagen de perfil cargada desde URL
* Visualización de datos del usuario autenticado
* Lectura de datos desde Firebase Firestore
* Visualización de nombre, email, carrera, comisión, turno y teléfono
* Botón para editar perfil
* Cierre de sesión mediante Firebase Authentication

La información del perfil se obtiene desde la colección `usuarios`, utilizando el UID del usuario logueado.
  
<img width="300" alt="image" src="https://github.com/user-attachments/assets/8c459f5c-a14f-4d41-a644-54798ee02dbe" />


Editar Perfil

* Edición de información personal
* Modificación de nombre, teléfono, email, carrera, comisión y turno
* Actualización de datos en Firebase Firestore
* Actualización automática al volver a la pantalla de perfil

<img width="300" src="https://github.com/user-attachments/assets/1f26304a-8264-4bdf-89ee-4b6d91d80d93" />

---
# FIREBASE

El proyecto integra Firebase para incorporar autenticación y persistencia de datos.

## Firebase Authentication

Se utiliza Firebase Authentication para:

* Registrar usuarios con email y contraseña
* Iniciar sesión
* Mantener la sesión activa
* Cerrar sesión desde la pantalla de perfil

## Firebase Firestore

Se utiliza Firebase Firestore para guardar y consultar información dinámica.

Colecciones utilizadas:

### usuarios

Cada usuario registrado genera un documento en la colección `usuarios`.

El ID del documento corresponde al UID generado por Firebase Authentication.

Campos principales:

* nombre
* email
* telefono
* carrera
* comision
* turno
* imagenUrl

Esta información se utiliza para mostrar el perfil del usuario y el saludo personalizado en Home.

### novedades

La colección `novedades` permite mostrar avisos académicos e institucionales dentro de la aplicación.

Campos principales:

* titulo
* descripcion
* categoria
* fecha
* imagenUrl

Las imágenes se cargan desde URL utilizando la librería Glide

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

Login / Registro -> Home

Desde Home el usuario puede acceder a:

* Materias
* Grupos
* Perfil


Además, desde la opción "Más" de la navegación inferior se puede acceder a:

* Novedades
* Calendario
* Contacto

---

# ARQUITECTURA

La aplicación utiliza una arquitectura basada en:

* Activities como controladores de UI
* Modelos de datos
* Repositorios en memoria para algunas funcionalidades dinámicas
* Firebase Authentication para autenticación de usuarios
* Firebase Firestore como base de datos remota

Esto permite combinar persistencia temporal local con datos dinámicos almacenados en Firebase

# COMPORTAMIENTO DINÁMICO

Se implementan múltiples comportamientos dinámicos:

* Creación de materias, grupos y parciales desde Java
* Actualización de UI en tiempo real
* Eliminación de elementos mediante interacción del usuario
* Estado de novedades leídas / no leídas
* Contador dinámico de novedades pendientes
* Actualización automática de datos al volver a pantalla mediante onResume
* Saludo personalizado en Home según el usuario logueado
* Carga de novedades desde Firebase Firestore
* Actualización del perfil del usuario en Firestore
* Carga de imágenes desde URL utilizando Glide

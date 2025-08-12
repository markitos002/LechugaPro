# Guía de Arquitectura de la App LechugaPro

Este documento sirve como una guía de referencia rápida sobre la arquitectura utilizada en esta aplicación. El objetivo es entender el rol de cada componente para facilitar el desarrollo y la depuración.

---

## 1. La Capa de Datos (`/data`) - El Almacén

Esta capa es el corazón de los datos de la aplicación. Se encarga de definir qué son los datos, cómo se guardan y cómo se accede a ellos.

### a) La Entidad (`@Entity` - Ej: `CicloProduccion.kt`)
- **Qué es:** Es la **plantilla** o el **plano** de un objeto que queremos guardar en la base de datos (Ej: un ciclo, un cliente).
- **Rol:** Define la estructura de una tabla en la base de datos. Cada variable de la clase (`nombreCiclo`, `estado`) se convierte en una columna de esa tabla.

### b) El DAO (Data Access Object - Ej: `CicloProduccionDao.kt`)
- **Qué es:** Es la **caja de herramientas** para interactuar con una tabla específica.
- **Rol:** Define las operaciones permitidas: `insertar`, `actualizar`, `borrar`, `obtenerTodos`, etc. No se escribe el código SQL complejo aquí, solo se usan anotaciones (`@Insert`, `@Query(...)`) y la librería Room genera el código necesario por nosotros.

### c) El Repositorio (Ej: `CicloProduccionRepository.kt`)
- **Qué es:** Es un **mediador** entre la capa de datos (DAO) y el resto de la app (los ViewModels).
- **Rol:** Proporciona una API limpia para acceder a los datos. Oculta el origen de los datos (¿vienen de la base de datos local o de un servidor remoto?). El resto de la app solo le pide datos al Repositorio, sin preocuparse por los detalles.

---

## 2. La Capa de UI (`/ui`) - La Tienda

Esta es la parte que el usuario ve y con la que interactúa.

### a) El `Fragment` (Ej: `ClienteListaFragment.kt`)
- **Qué es:** El **arquitecto de una pantalla** o de una porción de ella.
- **Rol:**
    1.  Dibuja la interfaz cargando un layout XML.
    2.  Escucha las interacciones del usuario (clics, gestos).
    3.  Debe ser "tonto" respecto a los datos. Su única misión es mostrar lo que el `ViewModel` le dice y reportarle las acciones del usuario.

### b) El `ViewModel` (Ej: `ClienteListaViewModel.kt`)
- **Qué es:** El **cerebro** que trabaja para el `Fragment`.
- **Rol:**
    1.  **Prepara y gestiona los datos** para la interfaz. Pide los datos al `Repositorio`.
    2.  **Sobrevive a cambios de configuración** (como girar la pantalla), protegiendo los datos de ser recargados innecesariamente.
    3.  Contiene la lógica de la interfaz. **Nunca debe tener referencias directas a Vistas o Fragments.**

### c) El `Adapter` (Ej: `ClienteListaAdapter.kt`)
- **Qué es:** Un **obrero especializado** para construir listas (`RecyclerView`).
- **Rol:** Toma una lista de objetos (Ej: `List<Cliente>`) y un layout de fila (`item_cliente.xml`) y se encarga de crear y reciclar las vistas de manera ultra eficiente para mostrar cada elemento.

### Flujo de Información en la UI:
1.  **`Fragment` se crea** -> Pide datos a su `ViewModel`.
2.  **`ViewModel`** -> Pide los datos al `Repository`.
3.  **`Repository`** -> Obtiene los datos del `DAO`.
4.  Los datos viajan de vuelta y el `ViewModel` los expone (usando `LiveData` o `Flow`).
5.  **`Fragment` recibe los datos** -> Se los pasa al `Adapter`.
6.  **`Adapter`** -> Dibuja las filas de la lista.

---

## 3. Componentes de Alto Nivel - La Estructura

Son los componentes que engloban toda la aplicación.

### a) `MainActivity.kt`
- **Qué es:** El **contenedor principal** o la "carcasa" de la app.
- **Rol:** Es el punto de entrada de la interfaz. Su labor principal es **albergar el `NavHostFragment`**, que es el "escenario" donde todos los `Fragments` se muestran y se intercambian. En una app moderna, a menudo solo hay una `Activity`.

### b) `LechugaProApplication.kt`
- **Qué es:** Los **cimientos** de la aplicación.
- **Rol:** Es lo **primero** que se ejecuta cuando se inicia el proceso de la app. Su función es **inicializar objetos globales que deben existir una sola vez**, como la conexión a la base de datos, librerías de analíticas, etc. Esto garantiza que toda la app comparta la misma instancia de estos componentes clave.

---

## 4. La Carpeta de Recursos (`/res`) - El Departamento de Diseño

Esta carpeta contiene todo lo que no es código Kotlin/Java. Es el centro de todos los "recursos" visuales y de configuración de la app.

### a) `res/drawable` - El Almacén de Gráficos
- **Qué es:** Cualquier cosa que se pueda "dibujar" en pantalla.
- **Contenido:** Iconos, imágenes (PNG, JPG), y sobre todo, XML Drawables, que permiten definir formas, colores y efectos (como un rectángulo con bordes redondeados para un botón) directamente en XML, lo cual es muy eficiente.

### b) `res/layout` - Los Planos Arquitectónicos
- **Qué es:** Los planos para cada pantalla o componente visual.
- **Contenido:**
    - **`fragment_...xml`**: El plano de una pantalla completa.
    - **`item_...xml`**: El plano de un solo elemento de una lista (como una fila de cliente).

### c) `res/menu` - Las Listas de Opciones
- **Qué es:** Define los botones que aparecen en las barras de herramientas superiores (Toolbar).
- **Rol:** Especifica el icono, texto y ID de cada opción de menú. El código del `Fragment` usa ese ID para saber qué botón se ha pulsado.

### d) `res/navigation` - El Mapa de la App
- **Qué es:** Un archivo (ej: `nav_graph.xml`) que representa visualmente todas las pantallas (`Fragments`) y cómo se conectan.
- **Rol:** Define los "destinos" (pantallas) y las "acciones" (las flechas o rutas entre ellas). Permite gestionar la navegación de forma centralizada y visual, simplificando el código.

---

## 5. Flujo de Navegación: "De la Lista al Detalle"

Este es un ejemplo práctico de cómo colaboran todos los componentes al pulsar un elemento en una lista.

1.  **El Clic (en el `Adapter`):**
    - El usuario pulsa una fila en el `RecyclerView`.
    - El `Adapter` detecta el clic, obtiene el objeto de datos de esa fila (ej: el `Cliente` con ID 42) y notifica al `Fragment` a través de una función o "listener" que le pasa ese ID.

2.  **La Acción de Navegación (en el `Fragment`):**
    - El `ClienteListaFragment` recibe la notificación: "¡El usuario quiere ver el cliente con ID 42!".
    - El `Fragment` **no abre directamente el otro fragment**. En su lugar, usa el componente de Navegación y le dice: "Activa la acción para ir al detalle, y llévate este ID como argumento".

3.  **El Mapa (`nav_graph.xml`):**
    - El componente de Navegación busca en el mapa (`nav_graph.xml`) la acción que sale de `ClienteListaFragment`.
    - El mapa le dice: "Esta acción va a `ClienteDetalleFragment` y espera recibir un argumento llamado `clienteId`".
    - El componente de Navegación crea la nueva pantalla de detalle y le entrega el ID 42.

4.  **La Llegada (el `ClienteDetalleFragment`):**
    - El `ClienteDetalleFragment` se crea.
    - En su inicio, recoge el argumento que le ha llegado: "Ah, tengo que mostrar la información del ID 42".
    - Le pide a su `ViewModel` que cargue los datos de ese cliente específico.
    - El `ViewModel` pide los datos al `Repository`, este al `DAO`, y el ciclo de datos comienza de nuevo para mostrar el detalle.

# Plan de Desarrollo: LechugaPro App

## Fase 1: Configuración del Proyecto y Primer Módulo (Producción - Ciclos)

*   [x] **Tarea 1: Creación del Proyecto y Configuración de Dependencias Esenciales**
*   [x] **Tarea 2: Definir Entidad `CicloProduccion` y su DAO**
*   [x] **Tarea 3: Crear la Clase `AppDatabase`**
*   [x] **Tarea 4: Crear `CicloProduccionRepository`**
*   [x] **Tarea 5: Crear ViewModel para la Lista de Ciclos (`ProduccionListaViewModel`)**
*   [x] **Tarea 6: Diseño UI para Listar Ciclos de Producción**
*   [x] **Tarea 7: Implementar `ProduccionListaAdapter` para el RecyclerView**
*   [x] **Tarea 8: Implementar `ProduccionListaFragment.kt`**
*   [x] **Tarea 9: Configurar el Gráfico de Navegación Básico (`nav_graph.xml`)**
*   [x] **Tarea 10: Modificar `MainActivity` para alojar `NavHostFragment` y configurar ActionBar**
*   [x] **Tarea 11: Crear ViewModel para Añadir/Editar Ciclo (`ProduccionEditViewModel`)**
*   [x] **Tarea 12: Diseño UI para Añadir/Editar Ciclo (`fragment_produccion_edit.xml`)**
*   [x] **Tarea 13: Implementar Creación de Nuevos Ciclos**
*   [x] **Tarea 14: Implementar Edición de Ciclos Existentes**
*   [x] **Tarea 15: Implementar Cambio de Estado del Ciclo**

## Fase 2: Módulo de Comercialización (Clientes e Ingresos)

*   [x] **Tarea 16: Definir Entidad `Cliente` y su DAO**
*   [x] **Tarea 17: Definir Entidad `Ingreso` y su DAO**
*   [x] **Tarea 18: Actualizar `AppDatabase`**
*   [x] **Tarea 19: Crear Repositorios para `Cliente` e `Ingreso`**.
*   [x] **Tarea 20: Crear ViewModels para gestión de Clientes** (`ClienteListaViewModel`, `ClienteEditViewModel`, `ClienteDetalleViewModel`).
*   [x] **Tarea 21: UI y Fragment para Lista de Clientes** (`ClienteListaFragment`).
*   [x] **Tarea 22: UI y Fragments para CRUD de Clientes**
    *   [x] Crear layout `fragment_cliente_edit.xml` para añadir/editar.
    *   [x] Crear layout `fragment_cliente_detalle.xml` para ver detalles.
    *   [x] Implementar `ClienteEditFragment` para la lógica de inserción y actualización.
    *   [x] Implementar `ClienteDetalleFragment` para mostrar la información del cliente.
    *   [x] Configurar la navegación completa: Lista -> Detalle -> Edición.
*   [ ] **Tarea 23: Módulo de Comercialización - Ingresos (CRUD)**
    *   [x] **Mostrar Lista de Ingresos en Detalle del Cliente:**
        *   [x] Modificar `fragment_cliente_detalle.xml` para añadir RecyclerView de ingresos y FAB para "Añadir Ingreso".
        *   [x] Crear layout `item_ingreso.xml` para cada fila de la lista de ingresos.
        *   [x] Crear `IngresoListaAdapter` (y moverlo al paquete `...ui.comercializacion.ingreso`).
        *   [x] Actualizar `ClienteDetalleViewModel` para cargar y exponer la lista de ingresos del cliente.
        *   [x] Actualizar `ClienteDetalleFragment` para mostrar la lista de ingresos y manejar el menú de opciones (Editar/Borrar Cliente) y el FAB (Añadir Ingreso).
        *   [x] Crear `menu_detalle.xml` para las opciones de Editar/Borrar en la ActionBar.
    *   [ ] **Añadir/Editar Ingresos:**
        *   [ ] Crear layout `fragment_ingreso_edit.xml`.
        *   [ ] Crear `IngresoEditViewModel`.
        *   [ ] Crear `IngresoEditFragment`.
        *   [x] Actualizar `nav_graph.xml` para incluir `IngresoEditFragment` y la acción de navegación.
    *   [ ] **Borrar Ingresos** (desde la lista en `ClienteDetalleFragment`).
*   [ ] **Tarea 24: (Opcional) Implementar `BottomNavigationView` para alternar entre módulos**.

## Fase 3: Módulo de Comercialización (Gastos y Ganancias)
*(Tareas futuras sin cambios)*

## Fase 4: Sincronización de Datos (Local a Servidor) - Funcionalidad Avanzada
*(Tareas futuras sin cambios)*

## Fase 5: Mejoras, Pruebas y Toques Finales
*(Tareas futuras sin cambios)*
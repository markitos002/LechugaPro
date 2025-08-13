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
*   [x] **Tarea 16: Refactor UI Producción (reset de módulo y reconstrucción lista + edición + detalle + date pickers)**

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
*   [x] **Tarea 23: Módulo de Comercialización - Ingresos (CRUD)**
    *   [x] Mostrar Lista de Ingresos en Detalle de Cliente
    *   [x] Layout `item_ingreso.xml`
    *   [x] `IngresoListaAdapter`
    *   [x] Integración en `ClienteDetalleFragment`
    *   [x] Menú contextual (editar / borrar cliente)
    *   [x] Layout `fragment_ingreso_edit.xml`
    *   [x] `IngresoEditViewModel`
    *   [x] `IngresoEditFragment`
    *   [x] Navegación a editor de ingreso (Safe Args)
    *   [x] Borrado con swipe + undo de ingresos
*   [x] **Tarea 24: Implementar `BottomNavigationView`**
*   [ ] **Tarea 25: Mejoras UX Ingresos** (date picker moderno, validaciones extra, formato moneda)

## Fase 3: Módulo de Comercialización (Gastos y Ganancias)
*(Tareas futuras sin cambios)*

## Fase 4: Sincronización de Datos (Local a Servidor) - Funcionalidad Avanzada
*(Tareas futuras sin cambios)*

## Fase 5: Mejoras, Pruebas y Toques Finales
*(Tareas futuras sin cambios)*

---

## Backlog Técnico / Pendiente Detallado

### Producción
1. Mostrar y editar fechas adicionales (preparación tierra, abono, suplemento, cosecha real) con MaterialDatePicker.
2. Validar secuencia lógica de fechas (preparación <= siembra <= estimada <= real).
3. Acciones rápidas para cambiar estado (chips o menú contextual) y registrar timestamps de transición de estado.
4. Convertir adapter inline de lista de ciclos a `ProduccionListaAdapter` con DiffUtil (optimización).
5. Soporte de borrado de ciclo (confirmación + undo Snackbar).
6. Internacionalizar textos hardcode (strings.xml) y formato de fechas localizable.
7. Tests unitarios ViewModels (insert/update/estado) y test instrumentado de navegación básica.

### Comercialización - Ingresos
1. (Hecho) Swipe delete con undo.
2. Date picker Material para fecha de ingreso (sustituir input manual).
3. Filtro rango fechas + total sumario (detalle cliente).
4. Validar importe (mínimo >0, opcional máximo configurable) y formato regional.
5. Tests repositorio/flujo (insertar, actualizar, borrar).

### Cross-cutting
1. Extraer formatos de fecha a util central y permitir configuración futura.
2. Tema oscuro y revisión de contraste / accesibilidad.
3. Manejo de errores global (Room, parseo) con Result wrapper o sealed classes.
4. Migrar LiveData a Flow donde aplique para consistencia.
5. Habilitar configuración de idioma / localización.

### Infraestructura / Calidad
1. Añadir Ktlint o detekt + configuración básica.
2. Añadir script de CI (GitHub Actions) para build + tests.
3. Configurar dependabot / version catalog updates automatizadas.
4. Generar README con capturas e instrucciones de build.

---

## Próximo Sprint Propuesto (actualizado)
1. Date picker en IngresoEditFragment (MaterialDatePicker) y bloqueo de entrada manual.
2. Date pickers adicionales en Producción + validación de secuencia fechas.
3. Adapter Producción con DiffUtil.
4. Internacionalización textos críticos + strings.xml.
5. Tests unitarios básicos (ProduccionDetalleViewModel, IngresoEditViewModel).
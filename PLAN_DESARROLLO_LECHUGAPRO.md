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
*   [x] **Tarea 17: Simplificación del formulario de Producción**
    * Eliminados: Inicio/Fin de preparación, Fecha de abono, Fecha real de cosecha.
    * Añadidos (derivados de siembra): Antifúngico (+5 días), K1 (+7), K2 (+14), K3 (+21) y Estimada de cosecha (+56).
    * Persistencia: fechas derivadas guardadas en BD (fechaAntifungico/fechaK1/fechaK2/fechaK3).
    * Migración Room 8→9 para agregar nuevas columnas.
    * Recordatorios ajustados: Antifúngico (+5), K1/K2/K3 (+7/+14/+21) y Cosecha estimada (+56).
*   [x] **Tarea 18: Archivado e Historial de ciclos**
    * Lista principal muestra solo ciclos activos (no archivados).
    * Nuevo Historial: lista de ciclos archivados con acciones Restaurar y Eliminar.
    * Acción Archivar disponible cuando el ciclo está en estado “Terminado” (en Detalle) y también con pulsación larga desde la lista.
    * Al archivar desde la lista se cancelan los recordatorios del ciclo.

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
1. Cancelar recordatorios también al archivar desde la pantalla de Detalle.
2. Añadir acción visible “Archivar” en cada ítem de la lista (botón de acción secundaria) además del long-press.
3. Mostrar toasts de confirmación para archivar/restaurar/eliminar en Historial y Lista.
4. Limpiar columnas obsoletas en una migración futura (inicio/fin preparación, abono, real) o reutilizarlas si se reintroducen.
5. Convertir adapter inline de lista de ciclos a `ProduccionListaAdapter` con DiffUtil (optimización).
6. Internacionalizar textos nuevos (Archivar/Restaurar/Eliminar/Historial/recordatorios) y formato de fechas localizable.
7. Tests unitarios: archivar/restaurar/eliminar, generación de fechas derivadas, programación y cancelación de recordatorios.
8. Acciones rápidas para cambiar estado (chips o menú contextual) y registrar timestamps de transición de estado.

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
1. Cancelar recordatorios al archivar desde Detalle + toasts de confirmación (archivar/restaurar/eliminar).
2. Acción “Archivar” visible en los ítems de la lista de Producción.
3. Adapter Producción con DiffUtil.
4. Date picker en IngresoEditFragment (MaterialDatePicker) y bloqueo de entrada manual.
5. Internacionalización de textos nuevos (Producción/Historial) + strings.xml.
6. Tests unitarios básicos y de integración: archivado/historial/recordatorios.
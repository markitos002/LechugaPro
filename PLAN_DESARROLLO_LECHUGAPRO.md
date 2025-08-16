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
*   [x] **Tarea 25: Mejoras UX Ingresos**
    *   Date picker Material y bloqueo de entrada manual en `IngresoEditFragment`.
    *   Validaciones extra de importe (> 0) con mensajes de error.
    *   Formato de moneda: símbolo $ con separador de miles (punto), p. ej. $ 1.000.
    *   Utilidad `CurrencyFormatter` para formato consistente.
*   [x] **Tarea 26: Historial por Cliente y Exportación**
    *   Mostrar "Historial de ventas" en detalle de cliente con todos los ingresos del cliente.
    *   Mostrar "Total vendido" por cliente (formateado con $) arriba del historial.
    *   Exportar/Compartir CSV desde el detalle (con cabecera que incluye el nombre del cliente).
    *   Configuración de `FileProvider` y `file_paths.xml` para compartir desde caché.
*   [x] **Tarea 27: Estado de Pago y Deudores**
    *   Dropdown en Ingreso (Pagado / En deuda) como lista desplegable.
    *   Migración Room 9→10: nueva columna `estado_pago` en `ingresos` (backfill desde `notas` si coincide).
    *   Resaltado de clientes con deuda en la lista de clientes.
*   [x] **Tarea 28: Ingreso Total Global en Lista de Clientes**
    *   Encabezado "Ingreso Total: $ XXX.XXX" sumando todos los ingresos.

## Fase 3: Módulo de Comercialización (Gastos y Ganancias)
*   [x] **Balance**: TabLayout + ViewPager2 con 3 pestañas (Ingresos, Gastos, Ganancia)
*   [x] **Gastos**: Tabla editable global (RecyclerView) con guardado automático de importe/fecha
*   [x] **Gastos - UX**: DatePicker en campo fecha (bloqueando entrada manual)
*   [x] **Datos iniciales**: Semilla automática de tipos fijos (Preparación terreno, Potasio, Fungicida, Cal, Abono; incluye "Plantulas") si la tabla está vacía
*   [x] **Gastos - Mejora**: Filtrar los gastos por ciclo activo (fallback a global si no hay activo)
*   [x] **Gastos - Mejora**: Añadir botón (FAB) para agregar nuevos tipos a la tabla
*   [x] **Gastos - Validación**: Evitar duplicados por ámbito (ciclo/global) y toasts de estado (sin ciclo activo / lista vacía)
*   [x] **Ingresos - Resumen**: Pestaña Ingresos muestra totales por cliente (lista ordenada desc.)
*   [x] **Ganancia**: Cálculo y UI (ingresos totales − gastos del ciclo activo o globales), botón Refrescar

## Fase 4: Sincronización de Datos (Local a Servidor) - Funcionalidad Avanzada
*(Tareas futuras sin cambios)*

## Fase 5: Mejoras, Pruebas y Toques Finales
*(Tareas futuras sin cambios)*

---

## Backlog Técnico / Pendiente Detallado

### Producción
1. (Hecho) Cancelar recordatorios también al archivar desde la pantalla de Detalle.
2. (Hecho) Añadir acción visible “Archivar” en cada ítem de la lista además del long-press.
3. (Hecho) Mostrar toasts de confirmación para archivar/restaurar/eliminar en Historial y Lista.
4. Limpiar columnas obsoletas en una migración futura (inicio/fin preparación, abono, real) o reutilizarlas si se reintroducen.
5. Convertir adapter inline de lista de ciclos a `ProduccionListaAdapter` con DiffUtil (optimización).
6. Internacionalizar textos nuevos (Archivar/Restaurar/Eliminar/Historial/recordatorios) y formato de fechas localizable.
7. Tests unitarios: archivar/restaurar/eliminar, generación de fechas derivadas, programación y cancelación de recordatorios.
8. Acciones rápidas para cambiar estado (chips o menú contextual) y registrar timestamps de transición de estado.

### Comercialización - Ingresos
1. (Hecho) Swipe delete con undo.
2. (Hecho) Date picker Material para fecha de ingreso (sustituir input manual) y bloqueo de teclado.
3. Filtro por rango de fechas + total sumario por rango (detalle cliente) y exportación filtrada.
4. Filtro por estado de pago (Pagado / En deuda) en historial.
5. Validar importe (mínimo >0, opcional máximo configurable) y formato regional.
6. Tests repositorio/flujo (insertar, actualizar, borrar) y migración `estado_pago`.
7. Navegación desde Balance → pestaña Ingresos: al tocar un cliente, abrir `ClienteDetalleFragment` pasando `idCliente` (Safe Args).

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
1. Filtros en historial de ingresos: por fechas y por estado (Pagado/En deuda) + totales por filtro.
2. Adapter Producción con DiffUtil y pequeñas optimizaciones de UI.
3. Migración de limpieza: retirar columnas obsoletas en `ciclos_produccion` o documentar su deprecación.
4. Internacionalización de textos (Clientes/Incomes/Export) y strings.xml; revisar accesibilidad/contrastes.
5. Tests unitarios y de integración: ingresos (crud, filtros, migración `estado_pago`), producción (archivado y recordatorios).
6. CI básico (build + tests) con GitHub Actions y linter (ktlint o detekt).
 7. Balance - Ingresos: onClick en el resumen por cliente para navegar al detalle del cliente (Safe Args con `idCliente`).

---

## Pendientes para mañana
1. Balance → pestaña Ingresos: al tocar un cliente en el resumen, navegar a `ClienteDetalleFragment` pasando `idCliente` (Navigation + Safe Args).
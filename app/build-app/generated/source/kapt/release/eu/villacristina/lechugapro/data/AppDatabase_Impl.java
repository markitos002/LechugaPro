package eu.villacristina.lechugapro.data;

import androidx.annotation.NonNull;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomDatabase;
import androidx.room.RoomOpenHelper;
import androidx.room.migration.AutoMigrationSpec;
import androidx.room.migration.Migration;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.processing.Generated;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class AppDatabase_Impl extends AppDatabase {
  private volatile CicloProduccionDao _cicloProduccionDao;

  private volatile ClienteDao _clienteDao;

  private volatile IngresoDao _ingresoDao;

  @Override
  @NonNull
  protected SupportSQLiteOpenHelper createOpenHelper(@NonNull final DatabaseConfiguration config) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(config, new RoomOpenHelper.Delegate(8) {
      @Override
      public void createAllTables(@NonNull final SupportSQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS `ciclos_produccion` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `numeroCiclo` INTEGER, `variedad` TEXT, `numeroPlantas` INTEGER NOT NULL, `fechaInicioPreparacionTierra` INTEGER, `fechaFinPreparacionTierra` INTEGER, `fechaAbono` INTEGER, `fechaSiembra` INTEGER, `fechaSuplementoMinerales` INTEGER, `fechaEstimadaCosecha` INTEGER, `fechaRealCosecha` INTEGER, `notas` TEXT, `estado` TEXT NOT NULL)");
        db.execSQL("CREATE UNIQUE INDEX IF NOT EXISTS `index_ciclos_produccion_numeroCiclo` ON `ciclos_produccion` (`numeroCiclo`)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `clientes` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `nombreCompleto` TEXT NOT NULL, `telefono` TEXT, `email` TEXT, `direccion` TEXT, `notasCliente` TEXT)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `ingresos` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `id_cliente` INTEGER NOT NULL, `fecha` INTEGER NOT NULL, `concepto` TEXT NOT NULL, `importe` REAL NOT NULL, `notas` TEXT, FOREIGN KEY(`id_cliente`) REFERENCES `clientes`(`id`) ON UPDATE NO ACTION ON DELETE CASCADE )");
        db.execSQL("CREATE INDEX IF NOT EXISTS `index_ingresos_id_cliente` ON `ingresos` (`id_cliente`)");
        db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, '4f0508904ae9330034d01d5f1404610c')");
      }

      @Override
      public void dropAllTables(@NonNull final SupportSQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS `ciclos_produccion`");
        db.execSQL("DROP TABLE IF EXISTS `clientes`");
        db.execSQL("DROP TABLE IF EXISTS `ingresos`");
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onDestructiveMigration(db);
          }
        }
      }

      @Override
      public void onCreate(@NonNull final SupportSQLiteDatabase db) {
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onCreate(db);
          }
        }
      }

      @Override
      public void onOpen(@NonNull final SupportSQLiteDatabase db) {
        mDatabase = db;
        db.execSQL("PRAGMA foreign_keys = ON");
        internalInitInvalidationTracker(db);
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onOpen(db);
          }
        }
      }

      @Override
      public void onPreMigrate(@NonNull final SupportSQLiteDatabase db) {
        DBUtil.dropFtsSyncTriggers(db);
      }

      @Override
      public void onPostMigrate(@NonNull final SupportSQLiteDatabase db) {
      }

      @Override
      @NonNull
      public RoomOpenHelper.ValidationResult onValidateSchema(
          @NonNull final SupportSQLiteDatabase db) {
        final HashMap<String, TableInfo.Column> _columnsCiclosProduccion = new HashMap<String, TableInfo.Column>(13);
        _columnsCiclosProduccion.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCiclosProduccion.put("numeroCiclo", new TableInfo.Column("numeroCiclo", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCiclosProduccion.put("variedad", new TableInfo.Column("variedad", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCiclosProduccion.put("numeroPlantas", new TableInfo.Column("numeroPlantas", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCiclosProduccion.put("fechaInicioPreparacionTierra", new TableInfo.Column("fechaInicioPreparacionTierra", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCiclosProduccion.put("fechaFinPreparacionTierra", new TableInfo.Column("fechaFinPreparacionTierra", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCiclosProduccion.put("fechaAbono", new TableInfo.Column("fechaAbono", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCiclosProduccion.put("fechaSiembra", new TableInfo.Column("fechaSiembra", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCiclosProduccion.put("fechaSuplementoMinerales", new TableInfo.Column("fechaSuplementoMinerales", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCiclosProduccion.put("fechaEstimadaCosecha", new TableInfo.Column("fechaEstimadaCosecha", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCiclosProduccion.put("fechaRealCosecha", new TableInfo.Column("fechaRealCosecha", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCiclosProduccion.put("notas", new TableInfo.Column("notas", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCiclosProduccion.put("estado", new TableInfo.Column("estado", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysCiclosProduccion = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesCiclosProduccion = new HashSet<TableInfo.Index>(1);
        _indicesCiclosProduccion.add(new TableInfo.Index("index_ciclos_produccion_numeroCiclo", true, Arrays.asList("numeroCiclo"), Arrays.asList("ASC")));
        final TableInfo _infoCiclosProduccion = new TableInfo("ciclos_produccion", _columnsCiclosProduccion, _foreignKeysCiclosProduccion, _indicesCiclosProduccion);
        final TableInfo _existingCiclosProduccion = TableInfo.read(db, "ciclos_produccion");
        if (!_infoCiclosProduccion.equals(_existingCiclosProduccion)) {
          return new RoomOpenHelper.ValidationResult(false, "ciclos_produccion(eu.villacristina.lechugapro.data.CicloProduccion).\n"
                  + " Expected:\n" + _infoCiclosProduccion + "\n"
                  + " Found:\n" + _existingCiclosProduccion);
        }
        final HashMap<String, TableInfo.Column> _columnsClientes = new HashMap<String, TableInfo.Column>(6);
        _columnsClientes.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsClientes.put("nombreCompleto", new TableInfo.Column("nombreCompleto", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsClientes.put("telefono", new TableInfo.Column("telefono", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsClientes.put("email", new TableInfo.Column("email", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsClientes.put("direccion", new TableInfo.Column("direccion", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsClientes.put("notasCliente", new TableInfo.Column("notasCliente", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysClientes = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesClientes = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoClientes = new TableInfo("clientes", _columnsClientes, _foreignKeysClientes, _indicesClientes);
        final TableInfo _existingClientes = TableInfo.read(db, "clientes");
        if (!_infoClientes.equals(_existingClientes)) {
          return new RoomOpenHelper.ValidationResult(false, "clientes(eu.villacristina.lechugapro.data.Cliente).\n"
                  + " Expected:\n" + _infoClientes + "\n"
                  + " Found:\n" + _existingClientes);
        }
        final HashMap<String, TableInfo.Column> _columnsIngresos = new HashMap<String, TableInfo.Column>(6);
        _columnsIngresos.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsIngresos.put("id_cliente", new TableInfo.Column("id_cliente", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsIngresos.put("fecha", new TableInfo.Column("fecha", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsIngresos.put("concepto", new TableInfo.Column("concepto", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsIngresos.put("importe", new TableInfo.Column("importe", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsIngresos.put("notas", new TableInfo.Column("notas", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysIngresos = new HashSet<TableInfo.ForeignKey>(1);
        _foreignKeysIngresos.add(new TableInfo.ForeignKey("clientes", "CASCADE", "NO ACTION", Arrays.asList("id_cliente"), Arrays.asList("id")));
        final HashSet<TableInfo.Index> _indicesIngresos = new HashSet<TableInfo.Index>(1);
        _indicesIngresos.add(new TableInfo.Index("index_ingresos_id_cliente", false, Arrays.asList("id_cliente"), Arrays.asList("ASC")));
        final TableInfo _infoIngresos = new TableInfo("ingresos", _columnsIngresos, _foreignKeysIngresos, _indicesIngresos);
        final TableInfo _existingIngresos = TableInfo.read(db, "ingresos");
        if (!_infoIngresos.equals(_existingIngresos)) {
          return new RoomOpenHelper.ValidationResult(false, "ingresos(eu.villacristina.lechugapro.data.Ingreso).\n"
                  + " Expected:\n" + _infoIngresos + "\n"
                  + " Found:\n" + _existingIngresos);
        }
        return new RoomOpenHelper.ValidationResult(true, null);
      }
    }, "4f0508904ae9330034d01d5f1404610c", "124420343a53f01db420cec2fa6d7d19");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(config.context).name(config.name).callback(_openCallback).build();
    final SupportSQLiteOpenHelper _helper = config.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  @NonNull
  protected InvalidationTracker createInvalidationTracker() {
    final HashMap<String, String> _shadowTablesMap = new HashMap<String, String>(0);
    final HashMap<String, Set<String>> _viewTables = new HashMap<String, Set<String>>(0);
    return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "ciclos_produccion","clientes","ingresos");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    final boolean _supportsDeferForeignKeys = android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP;
    try {
      if (!_supportsDeferForeignKeys) {
        _db.execSQL("PRAGMA foreign_keys = FALSE");
      }
      super.beginTransaction();
      if (_supportsDeferForeignKeys) {
        _db.execSQL("PRAGMA defer_foreign_keys = TRUE");
      }
      _db.execSQL("DELETE FROM `ciclos_produccion`");
      _db.execSQL("DELETE FROM `clientes`");
      _db.execSQL("DELETE FROM `ingresos`");
      super.setTransactionSuccessful();
    } finally {
      super.endTransaction();
      if (!_supportsDeferForeignKeys) {
        _db.execSQL("PRAGMA foreign_keys = TRUE");
      }
      _db.query("PRAGMA wal_checkpoint(FULL)").close();
      if (!_db.inTransaction()) {
        _db.execSQL("VACUUM");
      }
    }
  }

  @Override
  @NonNull
  protected Map<Class<?>, List<Class<?>>> getRequiredTypeConverters() {
    final HashMap<Class<?>, List<Class<?>>> _typeConvertersMap = new HashMap<Class<?>, List<Class<?>>>();
    _typeConvertersMap.put(CicloProduccionDao.class, CicloProduccionDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(ClienteDao.class, ClienteDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(IngresoDao.class, IngresoDao_Impl.getRequiredConverters());
    return _typeConvertersMap;
  }

  @Override
  @NonNull
  public Set<Class<? extends AutoMigrationSpec>> getRequiredAutoMigrationSpecs() {
    final HashSet<Class<? extends AutoMigrationSpec>> _autoMigrationSpecsSet = new HashSet<Class<? extends AutoMigrationSpec>>();
    return _autoMigrationSpecsSet;
  }

  @Override
  @NonNull
  public List<Migration> getAutoMigrations(
      @NonNull final Map<Class<? extends AutoMigrationSpec>, AutoMigrationSpec> autoMigrationSpecs) {
    final List<Migration> _autoMigrations = new ArrayList<Migration>();
    return _autoMigrations;
  }

  @Override
  public CicloProduccionDao cicloProduccionDao() {
    if (_cicloProduccionDao != null) {
      return _cicloProduccionDao;
    } else {
      synchronized(this) {
        if(_cicloProduccionDao == null) {
          _cicloProduccionDao = new CicloProduccionDao_Impl(this);
        }
        return _cicloProduccionDao;
      }
    }
  }

  @Override
  public ClienteDao clienteDao() {
    if (_clienteDao != null) {
      return _clienteDao;
    } else {
      synchronized(this) {
        if(_clienteDao == null) {
          _clienteDao = new ClienteDao_Impl(this);
        }
        return _clienteDao;
      }
    }
  }

  @Override
  public IngresoDao ingresoDao() {
    if (_ingresoDao != null) {
      return _ingresoDao;
    } else {
      synchronized(this) {
        if(_ingresoDao == null) {
          _ingresoDao = new IngresoDao_Impl(this);
        }
        return _ingresoDao;
      }
    }
  }
}

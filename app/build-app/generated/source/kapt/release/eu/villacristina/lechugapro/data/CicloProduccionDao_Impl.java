package eu.villacristina.lechugapro.data;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.lang.Boolean;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Integer;
import java.lang.Long;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import javax.annotation.processing.Generated;
import kotlin.Unit;
import kotlin.coroutines.Continuation;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class CicloProduccionDao_Impl implements CicloProduccionDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<CicloProduccion> __insertionAdapterOfCicloProduccion;

  private final EntityDeletionOrUpdateAdapter<CicloProduccion> __updateAdapterOfCicloProduccion;

  private final SharedSQLiteStatement __preparedStmtOfArchivar;

  private final SharedSQLiteStatement __preparedStmtOfDeleteById;

  public CicloProduccionDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfCicloProduccion = new EntityInsertionAdapter<CicloProduccion>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `ciclos_produccion` (`id`,`numeroCiclo`,`variedad`,`numeroPlantas`,`fechaInicioPreparacionTierra`,`fechaFinPreparacionTierra`,`fechaAbono`,`fechaSiembra`,`fechaSuplementoMinerales`,`fechaEstimadaCosecha`,`fechaRealCosecha`,`fechaAntifungico`,`fechaK1`,`fechaK2`,`fechaK3`,`notas`,`estado`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final CicloProduccion entity) {
        statement.bindLong(1, entity.getId());
        if (entity.getNumeroCiclo() == null) {
          statement.bindNull(2);
        } else {
          statement.bindLong(2, entity.getNumeroCiclo());
        }
        if (entity.getVariedad() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getVariedad());
        }
        statement.bindLong(4, entity.getNumeroPlantas());
        if (entity.getFechaInicioPreparacionTierra() == null) {
          statement.bindNull(5);
        } else {
          statement.bindLong(5, entity.getFechaInicioPreparacionTierra());
        }
        if (entity.getFechaFinPreparacionTierra() == null) {
          statement.bindNull(6);
        } else {
          statement.bindLong(6, entity.getFechaFinPreparacionTierra());
        }
        if (entity.getFechaAbono() == null) {
          statement.bindNull(7);
        } else {
          statement.bindLong(7, entity.getFechaAbono());
        }
        if (entity.getFechaSiembra() == null) {
          statement.bindNull(8);
        } else {
          statement.bindLong(8, entity.getFechaSiembra());
        }
        if (entity.getFechaSuplementoMinerales() == null) {
          statement.bindNull(9);
        } else {
          statement.bindLong(9, entity.getFechaSuplementoMinerales());
        }
        if (entity.getFechaEstimadaCosecha() == null) {
          statement.bindNull(10);
        } else {
          statement.bindLong(10, entity.getFechaEstimadaCosecha());
        }
        if (entity.getFechaRealCosecha() == null) {
          statement.bindNull(11);
        } else {
          statement.bindLong(11, entity.getFechaRealCosecha());
        }
        if (entity.getFechaAntifungico() == null) {
          statement.bindNull(12);
        } else {
          statement.bindLong(12, entity.getFechaAntifungico());
        }
        if (entity.getFechaK1() == null) {
          statement.bindNull(13);
        } else {
          statement.bindLong(13, entity.getFechaK1());
        }
        if (entity.getFechaK2() == null) {
          statement.bindNull(14);
        } else {
          statement.bindLong(14, entity.getFechaK2());
        }
        if (entity.getFechaK3() == null) {
          statement.bindNull(15);
        } else {
          statement.bindLong(15, entity.getFechaK3());
        }
        if (entity.getNotas() == null) {
          statement.bindNull(16);
        } else {
          statement.bindString(16, entity.getNotas());
        }
        if (entity.getEstado() == null) {
          statement.bindNull(17);
        } else {
          statement.bindString(17, entity.getEstado());
        }
      }
    };
    this.__updateAdapterOfCicloProduccion = new EntityDeletionOrUpdateAdapter<CicloProduccion>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `ciclos_produccion` SET `id` = ?,`numeroCiclo` = ?,`variedad` = ?,`numeroPlantas` = ?,`fechaInicioPreparacionTierra` = ?,`fechaFinPreparacionTierra` = ?,`fechaAbono` = ?,`fechaSiembra` = ?,`fechaSuplementoMinerales` = ?,`fechaEstimadaCosecha` = ?,`fechaRealCosecha` = ?,`fechaAntifungico` = ?,`fechaK1` = ?,`fechaK2` = ?,`fechaK3` = ?,`notas` = ?,`estado` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final CicloProduccion entity) {
        statement.bindLong(1, entity.getId());
        if (entity.getNumeroCiclo() == null) {
          statement.bindNull(2);
        } else {
          statement.bindLong(2, entity.getNumeroCiclo());
        }
        if (entity.getVariedad() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getVariedad());
        }
        statement.bindLong(4, entity.getNumeroPlantas());
        if (entity.getFechaInicioPreparacionTierra() == null) {
          statement.bindNull(5);
        } else {
          statement.bindLong(5, entity.getFechaInicioPreparacionTierra());
        }
        if (entity.getFechaFinPreparacionTierra() == null) {
          statement.bindNull(6);
        } else {
          statement.bindLong(6, entity.getFechaFinPreparacionTierra());
        }
        if (entity.getFechaAbono() == null) {
          statement.bindNull(7);
        } else {
          statement.bindLong(7, entity.getFechaAbono());
        }
        if (entity.getFechaSiembra() == null) {
          statement.bindNull(8);
        } else {
          statement.bindLong(8, entity.getFechaSiembra());
        }
        if (entity.getFechaSuplementoMinerales() == null) {
          statement.bindNull(9);
        } else {
          statement.bindLong(9, entity.getFechaSuplementoMinerales());
        }
        if (entity.getFechaEstimadaCosecha() == null) {
          statement.bindNull(10);
        } else {
          statement.bindLong(10, entity.getFechaEstimadaCosecha());
        }
        if (entity.getFechaRealCosecha() == null) {
          statement.bindNull(11);
        } else {
          statement.bindLong(11, entity.getFechaRealCosecha());
        }
        if (entity.getFechaAntifungico() == null) {
          statement.bindNull(12);
        } else {
          statement.bindLong(12, entity.getFechaAntifungico());
        }
        if (entity.getFechaK1() == null) {
          statement.bindNull(13);
        } else {
          statement.bindLong(13, entity.getFechaK1());
        }
        if (entity.getFechaK2() == null) {
          statement.bindNull(14);
        } else {
          statement.bindLong(14, entity.getFechaK2());
        }
        if (entity.getFechaK3() == null) {
          statement.bindNull(15);
        } else {
          statement.bindLong(15, entity.getFechaK3());
        }
        if (entity.getNotas() == null) {
          statement.bindNull(16);
        } else {
          statement.bindString(16, entity.getNotas());
        }
        if (entity.getEstado() == null) {
          statement.bindNull(17);
        } else {
          statement.bindString(17, entity.getEstado());
        }
        statement.bindLong(18, entity.getId());
      }
    };
    this.__preparedStmtOfArchivar = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "UPDATE ciclos_produccion SET estado = 'Archivado' WHERE id = ?";
        return _query;
      }
    };
    this.__preparedStmtOfDeleteById = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM ciclos_produccion WHERE id = ?";
        return _query;
      }
    };
  }

  @Override
  public Object insert(final CicloProduccion ciclo, final Continuation<? super Long> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Long>() {
      @Override
      @NonNull
      public Long call() throws Exception {
        __db.beginTransaction();
        try {
          final Long _result = __insertionAdapterOfCicloProduccion.insertAndReturnId(ciclo);
          __db.setTransactionSuccessful();
          return _result;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object update(final CicloProduccion ciclo, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfCicloProduccion.handle(ciclo);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object archivar(final long id, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfArchivar.acquire();
        int _argIndex = 1;
        _stmt.bindLong(_argIndex, id);
        try {
          __db.beginTransaction();
          try {
            _stmt.executeUpdateDelete();
            __db.setTransactionSuccessful();
            return Unit.INSTANCE;
          } finally {
            __db.endTransaction();
          }
        } finally {
          __preparedStmtOfArchivar.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteById(final long id, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteById.acquire();
        int _argIndex = 1;
        _stmt.bindLong(_argIndex, id);
        try {
          __db.beginTransaction();
          try {
            _stmt.executeUpdateDelete();
            __db.setTransactionSuccessful();
            return Unit.INSTANCE;
          } finally {
            __db.endTransaction();
          }
        } finally {
          __preparedStmtOfDeleteById.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public LiveData<List<CicloProduccion>> getAllActivos() {
    final String _sql = "SELECT * FROM ciclos_produccion WHERE estado != 'Archivado' OR estado IS NULL";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[] {"ciclos_produccion"}, false, new Callable<List<CicloProduccion>>() {
      @Override
      @Nullable
      public List<CicloProduccion> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfNumeroCiclo = CursorUtil.getColumnIndexOrThrow(_cursor, "numeroCiclo");
          final int _cursorIndexOfVariedad = CursorUtil.getColumnIndexOrThrow(_cursor, "variedad");
          final int _cursorIndexOfNumeroPlantas = CursorUtil.getColumnIndexOrThrow(_cursor, "numeroPlantas");
          final int _cursorIndexOfFechaInicioPreparacionTierra = CursorUtil.getColumnIndexOrThrow(_cursor, "fechaInicioPreparacionTierra");
          final int _cursorIndexOfFechaFinPreparacionTierra = CursorUtil.getColumnIndexOrThrow(_cursor, "fechaFinPreparacionTierra");
          final int _cursorIndexOfFechaAbono = CursorUtil.getColumnIndexOrThrow(_cursor, "fechaAbono");
          final int _cursorIndexOfFechaSiembra = CursorUtil.getColumnIndexOrThrow(_cursor, "fechaSiembra");
          final int _cursorIndexOfFechaSuplementoMinerales = CursorUtil.getColumnIndexOrThrow(_cursor, "fechaSuplementoMinerales");
          final int _cursorIndexOfFechaEstimadaCosecha = CursorUtil.getColumnIndexOrThrow(_cursor, "fechaEstimadaCosecha");
          final int _cursorIndexOfFechaRealCosecha = CursorUtil.getColumnIndexOrThrow(_cursor, "fechaRealCosecha");
          final int _cursorIndexOfFechaAntifungico = CursorUtil.getColumnIndexOrThrow(_cursor, "fechaAntifungico");
          final int _cursorIndexOfFechaK1 = CursorUtil.getColumnIndexOrThrow(_cursor, "fechaK1");
          final int _cursorIndexOfFechaK2 = CursorUtil.getColumnIndexOrThrow(_cursor, "fechaK2");
          final int _cursorIndexOfFechaK3 = CursorUtil.getColumnIndexOrThrow(_cursor, "fechaK3");
          final int _cursorIndexOfNotas = CursorUtil.getColumnIndexOrThrow(_cursor, "notas");
          final int _cursorIndexOfEstado = CursorUtil.getColumnIndexOrThrow(_cursor, "estado");
          final List<CicloProduccion> _result = new ArrayList<CicloProduccion>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final CicloProduccion _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final Integer _tmpNumeroCiclo;
            if (_cursor.isNull(_cursorIndexOfNumeroCiclo)) {
              _tmpNumeroCiclo = null;
            } else {
              _tmpNumeroCiclo = _cursor.getInt(_cursorIndexOfNumeroCiclo);
            }
            final String _tmpVariedad;
            if (_cursor.isNull(_cursorIndexOfVariedad)) {
              _tmpVariedad = null;
            } else {
              _tmpVariedad = _cursor.getString(_cursorIndexOfVariedad);
            }
            final int _tmpNumeroPlantas;
            _tmpNumeroPlantas = _cursor.getInt(_cursorIndexOfNumeroPlantas);
            final Long _tmpFechaInicioPreparacionTierra;
            if (_cursor.isNull(_cursorIndexOfFechaInicioPreparacionTierra)) {
              _tmpFechaInicioPreparacionTierra = null;
            } else {
              _tmpFechaInicioPreparacionTierra = _cursor.getLong(_cursorIndexOfFechaInicioPreparacionTierra);
            }
            final Long _tmpFechaFinPreparacionTierra;
            if (_cursor.isNull(_cursorIndexOfFechaFinPreparacionTierra)) {
              _tmpFechaFinPreparacionTierra = null;
            } else {
              _tmpFechaFinPreparacionTierra = _cursor.getLong(_cursorIndexOfFechaFinPreparacionTierra);
            }
            final Long _tmpFechaAbono;
            if (_cursor.isNull(_cursorIndexOfFechaAbono)) {
              _tmpFechaAbono = null;
            } else {
              _tmpFechaAbono = _cursor.getLong(_cursorIndexOfFechaAbono);
            }
            final Long _tmpFechaSiembra;
            if (_cursor.isNull(_cursorIndexOfFechaSiembra)) {
              _tmpFechaSiembra = null;
            } else {
              _tmpFechaSiembra = _cursor.getLong(_cursorIndexOfFechaSiembra);
            }
            final Long _tmpFechaSuplementoMinerales;
            if (_cursor.isNull(_cursorIndexOfFechaSuplementoMinerales)) {
              _tmpFechaSuplementoMinerales = null;
            } else {
              _tmpFechaSuplementoMinerales = _cursor.getLong(_cursorIndexOfFechaSuplementoMinerales);
            }
            final Long _tmpFechaEstimadaCosecha;
            if (_cursor.isNull(_cursorIndexOfFechaEstimadaCosecha)) {
              _tmpFechaEstimadaCosecha = null;
            } else {
              _tmpFechaEstimadaCosecha = _cursor.getLong(_cursorIndexOfFechaEstimadaCosecha);
            }
            final Long _tmpFechaRealCosecha;
            if (_cursor.isNull(_cursorIndexOfFechaRealCosecha)) {
              _tmpFechaRealCosecha = null;
            } else {
              _tmpFechaRealCosecha = _cursor.getLong(_cursorIndexOfFechaRealCosecha);
            }
            final Long _tmpFechaAntifungico;
            if (_cursor.isNull(_cursorIndexOfFechaAntifungico)) {
              _tmpFechaAntifungico = null;
            } else {
              _tmpFechaAntifungico = _cursor.getLong(_cursorIndexOfFechaAntifungico);
            }
            final Long _tmpFechaK1;
            if (_cursor.isNull(_cursorIndexOfFechaK1)) {
              _tmpFechaK1 = null;
            } else {
              _tmpFechaK1 = _cursor.getLong(_cursorIndexOfFechaK1);
            }
            final Long _tmpFechaK2;
            if (_cursor.isNull(_cursorIndexOfFechaK2)) {
              _tmpFechaK2 = null;
            } else {
              _tmpFechaK2 = _cursor.getLong(_cursorIndexOfFechaK2);
            }
            final Long _tmpFechaK3;
            if (_cursor.isNull(_cursorIndexOfFechaK3)) {
              _tmpFechaK3 = null;
            } else {
              _tmpFechaK3 = _cursor.getLong(_cursorIndexOfFechaK3);
            }
            final String _tmpNotas;
            if (_cursor.isNull(_cursorIndexOfNotas)) {
              _tmpNotas = null;
            } else {
              _tmpNotas = _cursor.getString(_cursorIndexOfNotas);
            }
            final String _tmpEstado;
            if (_cursor.isNull(_cursorIndexOfEstado)) {
              _tmpEstado = null;
            } else {
              _tmpEstado = _cursor.getString(_cursorIndexOfEstado);
            }
            _item = new CicloProduccion(_tmpId,_tmpNumeroCiclo,_tmpVariedad,_tmpNumeroPlantas,_tmpFechaInicioPreparacionTierra,_tmpFechaFinPreparacionTierra,_tmpFechaAbono,_tmpFechaSiembra,_tmpFechaSuplementoMinerales,_tmpFechaEstimadaCosecha,_tmpFechaRealCosecha,_tmpFechaAntifungico,_tmpFechaK1,_tmpFechaK2,_tmpFechaK3,_tmpNotas,_tmpEstado);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public LiveData<List<CicloProduccion>> getArchivados() {
    final String _sql = "SELECT * FROM ciclos_produccion WHERE estado = 'Archivado'";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[] {"ciclos_produccion"}, false, new Callable<List<CicloProduccion>>() {
      @Override
      @Nullable
      public List<CicloProduccion> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfNumeroCiclo = CursorUtil.getColumnIndexOrThrow(_cursor, "numeroCiclo");
          final int _cursorIndexOfVariedad = CursorUtil.getColumnIndexOrThrow(_cursor, "variedad");
          final int _cursorIndexOfNumeroPlantas = CursorUtil.getColumnIndexOrThrow(_cursor, "numeroPlantas");
          final int _cursorIndexOfFechaInicioPreparacionTierra = CursorUtil.getColumnIndexOrThrow(_cursor, "fechaInicioPreparacionTierra");
          final int _cursorIndexOfFechaFinPreparacionTierra = CursorUtil.getColumnIndexOrThrow(_cursor, "fechaFinPreparacionTierra");
          final int _cursorIndexOfFechaAbono = CursorUtil.getColumnIndexOrThrow(_cursor, "fechaAbono");
          final int _cursorIndexOfFechaSiembra = CursorUtil.getColumnIndexOrThrow(_cursor, "fechaSiembra");
          final int _cursorIndexOfFechaSuplementoMinerales = CursorUtil.getColumnIndexOrThrow(_cursor, "fechaSuplementoMinerales");
          final int _cursorIndexOfFechaEstimadaCosecha = CursorUtil.getColumnIndexOrThrow(_cursor, "fechaEstimadaCosecha");
          final int _cursorIndexOfFechaRealCosecha = CursorUtil.getColumnIndexOrThrow(_cursor, "fechaRealCosecha");
          final int _cursorIndexOfFechaAntifungico = CursorUtil.getColumnIndexOrThrow(_cursor, "fechaAntifungico");
          final int _cursorIndexOfFechaK1 = CursorUtil.getColumnIndexOrThrow(_cursor, "fechaK1");
          final int _cursorIndexOfFechaK2 = CursorUtil.getColumnIndexOrThrow(_cursor, "fechaK2");
          final int _cursorIndexOfFechaK3 = CursorUtil.getColumnIndexOrThrow(_cursor, "fechaK3");
          final int _cursorIndexOfNotas = CursorUtil.getColumnIndexOrThrow(_cursor, "notas");
          final int _cursorIndexOfEstado = CursorUtil.getColumnIndexOrThrow(_cursor, "estado");
          final List<CicloProduccion> _result = new ArrayList<CicloProduccion>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final CicloProduccion _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final Integer _tmpNumeroCiclo;
            if (_cursor.isNull(_cursorIndexOfNumeroCiclo)) {
              _tmpNumeroCiclo = null;
            } else {
              _tmpNumeroCiclo = _cursor.getInt(_cursorIndexOfNumeroCiclo);
            }
            final String _tmpVariedad;
            if (_cursor.isNull(_cursorIndexOfVariedad)) {
              _tmpVariedad = null;
            } else {
              _tmpVariedad = _cursor.getString(_cursorIndexOfVariedad);
            }
            final int _tmpNumeroPlantas;
            _tmpNumeroPlantas = _cursor.getInt(_cursorIndexOfNumeroPlantas);
            final Long _tmpFechaInicioPreparacionTierra;
            if (_cursor.isNull(_cursorIndexOfFechaInicioPreparacionTierra)) {
              _tmpFechaInicioPreparacionTierra = null;
            } else {
              _tmpFechaInicioPreparacionTierra = _cursor.getLong(_cursorIndexOfFechaInicioPreparacionTierra);
            }
            final Long _tmpFechaFinPreparacionTierra;
            if (_cursor.isNull(_cursorIndexOfFechaFinPreparacionTierra)) {
              _tmpFechaFinPreparacionTierra = null;
            } else {
              _tmpFechaFinPreparacionTierra = _cursor.getLong(_cursorIndexOfFechaFinPreparacionTierra);
            }
            final Long _tmpFechaAbono;
            if (_cursor.isNull(_cursorIndexOfFechaAbono)) {
              _tmpFechaAbono = null;
            } else {
              _tmpFechaAbono = _cursor.getLong(_cursorIndexOfFechaAbono);
            }
            final Long _tmpFechaSiembra;
            if (_cursor.isNull(_cursorIndexOfFechaSiembra)) {
              _tmpFechaSiembra = null;
            } else {
              _tmpFechaSiembra = _cursor.getLong(_cursorIndexOfFechaSiembra);
            }
            final Long _tmpFechaSuplementoMinerales;
            if (_cursor.isNull(_cursorIndexOfFechaSuplementoMinerales)) {
              _tmpFechaSuplementoMinerales = null;
            } else {
              _tmpFechaSuplementoMinerales = _cursor.getLong(_cursorIndexOfFechaSuplementoMinerales);
            }
            final Long _tmpFechaEstimadaCosecha;
            if (_cursor.isNull(_cursorIndexOfFechaEstimadaCosecha)) {
              _tmpFechaEstimadaCosecha = null;
            } else {
              _tmpFechaEstimadaCosecha = _cursor.getLong(_cursorIndexOfFechaEstimadaCosecha);
            }
            final Long _tmpFechaRealCosecha;
            if (_cursor.isNull(_cursorIndexOfFechaRealCosecha)) {
              _tmpFechaRealCosecha = null;
            } else {
              _tmpFechaRealCosecha = _cursor.getLong(_cursorIndexOfFechaRealCosecha);
            }
            final Long _tmpFechaAntifungico;
            if (_cursor.isNull(_cursorIndexOfFechaAntifungico)) {
              _tmpFechaAntifungico = null;
            } else {
              _tmpFechaAntifungico = _cursor.getLong(_cursorIndexOfFechaAntifungico);
            }
            final Long _tmpFechaK1;
            if (_cursor.isNull(_cursorIndexOfFechaK1)) {
              _tmpFechaK1 = null;
            } else {
              _tmpFechaK1 = _cursor.getLong(_cursorIndexOfFechaK1);
            }
            final Long _tmpFechaK2;
            if (_cursor.isNull(_cursorIndexOfFechaK2)) {
              _tmpFechaK2 = null;
            } else {
              _tmpFechaK2 = _cursor.getLong(_cursorIndexOfFechaK2);
            }
            final Long _tmpFechaK3;
            if (_cursor.isNull(_cursorIndexOfFechaK3)) {
              _tmpFechaK3 = null;
            } else {
              _tmpFechaK3 = _cursor.getLong(_cursorIndexOfFechaK3);
            }
            final String _tmpNotas;
            if (_cursor.isNull(_cursorIndexOfNotas)) {
              _tmpNotas = null;
            } else {
              _tmpNotas = _cursor.getString(_cursorIndexOfNotas);
            }
            final String _tmpEstado;
            if (_cursor.isNull(_cursorIndexOfEstado)) {
              _tmpEstado = null;
            } else {
              _tmpEstado = _cursor.getString(_cursorIndexOfEstado);
            }
            _item = new CicloProduccion(_tmpId,_tmpNumeroCiclo,_tmpVariedad,_tmpNumeroPlantas,_tmpFechaInicioPreparacionTierra,_tmpFechaFinPreparacionTierra,_tmpFechaAbono,_tmpFechaSiembra,_tmpFechaSuplementoMinerales,_tmpFechaEstimadaCosecha,_tmpFechaRealCosecha,_tmpFechaAntifungico,_tmpFechaK1,_tmpFechaK2,_tmpFechaK3,_tmpNotas,_tmpEstado);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public LiveData<CicloProduccion> getById(final long id) {
    final String _sql = "SELECT * FROM ciclos_produccion WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, id);
    return __db.getInvalidationTracker().createLiveData(new String[] {"ciclos_produccion"}, false, new Callable<CicloProduccion>() {
      @Override
      @Nullable
      public CicloProduccion call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfNumeroCiclo = CursorUtil.getColumnIndexOrThrow(_cursor, "numeroCiclo");
          final int _cursorIndexOfVariedad = CursorUtil.getColumnIndexOrThrow(_cursor, "variedad");
          final int _cursorIndexOfNumeroPlantas = CursorUtil.getColumnIndexOrThrow(_cursor, "numeroPlantas");
          final int _cursorIndexOfFechaInicioPreparacionTierra = CursorUtil.getColumnIndexOrThrow(_cursor, "fechaInicioPreparacionTierra");
          final int _cursorIndexOfFechaFinPreparacionTierra = CursorUtil.getColumnIndexOrThrow(_cursor, "fechaFinPreparacionTierra");
          final int _cursorIndexOfFechaAbono = CursorUtil.getColumnIndexOrThrow(_cursor, "fechaAbono");
          final int _cursorIndexOfFechaSiembra = CursorUtil.getColumnIndexOrThrow(_cursor, "fechaSiembra");
          final int _cursorIndexOfFechaSuplementoMinerales = CursorUtil.getColumnIndexOrThrow(_cursor, "fechaSuplementoMinerales");
          final int _cursorIndexOfFechaEstimadaCosecha = CursorUtil.getColumnIndexOrThrow(_cursor, "fechaEstimadaCosecha");
          final int _cursorIndexOfFechaRealCosecha = CursorUtil.getColumnIndexOrThrow(_cursor, "fechaRealCosecha");
          final int _cursorIndexOfFechaAntifungico = CursorUtil.getColumnIndexOrThrow(_cursor, "fechaAntifungico");
          final int _cursorIndexOfFechaK1 = CursorUtil.getColumnIndexOrThrow(_cursor, "fechaK1");
          final int _cursorIndexOfFechaK2 = CursorUtil.getColumnIndexOrThrow(_cursor, "fechaK2");
          final int _cursorIndexOfFechaK3 = CursorUtil.getColumnIndexOrThrow(_cursor, "fechaK3");
          final int _cursorIndexOfNotas = CursorUtil.getColumnIndexOrThrow(_cursor, "notas");
          final int _cursorIndexOfEstado = CursorUtil.getColumnIndexOrThrow(_cursor, "estado");
          final CicloProduccion _result;
          if (_cursor.moveToFirst()) {
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final Integer _tmpNumeroCiclo;
            if (_cursor.isNull(_cursorIndexOfNumeroCiclo)) {
              _tmpNumeroCiclo = null;
            } else {
              _tmpNumeroCiclo = _cursor.getInt(_cursorIndexOfNumeroCiclo);
            }
            final String _tmpVariedad;
            if (_cursor.isNull(_cursorIndexOfVariedad)) {
              _tmpVariedad = null;
            } else {
              _tmpVariedad = _cursor.getString(_cursorIndexOfVariedad);
            }
            final int _tmpNumeroPlantas;
            _tmpNumeroPlantas = _cursor.getInt(_cursorIndexOfNumeroPlantas);
            final Long _tmpFechaInicioPreparacionTierra;
            if (_cursor.isNull(_cursorIndexOfFechaInicioPreparacionTierra)) {
              _tmpFechaInicioPreparacionTierra = null;
            } else {
              _tmpFechaInicioPreparacionTierra = _cursor.getLong(_cursorIndexOfFechaInicioPreparacionTierra);
            }
            final Long _tmpFechaFinPreparacionTierra;
            if (_cursor.isNull(_cursorIndexOfFechaFinPreparacionTierra)) {
              _tmpFechaFinPreparacionTierra = null;
            } else {
              _tmpFechaFinPreparacionTierra = _cursor.getLong(_cursorIndexOfFechaFinPreparacionTierra);
            }
            final Long _tmpFechaAbono;
            if (_cursor.isNull(_cursorIndexOfFechaAbono)) {
              _tmpFechaAbono = null;
            } else {
              _tmpFechaAbono = _cursor.getLong(_cursorIndexOfFechaAbono);
            }
            final Long _tmpFechaSiembra;
            if (_cursor.isNull(_cursorIndexOfFechaSiembra)) {
              _tmpFechaSiembra = null;
            } else {
              _tmpFechaSiembra = _cursor.getLong(_cursorIndexOfFechaSiembra);
            }
            final Long _tmpFechaSuplementoMinerales;
            if (_cursor.isNull(_cursorIndexOfFechaSuplementoMinerales)) {
              _tmpFechaSuplementoMinerales = null;
            } else {
              _tmpFechaSuplementoMinerales = _cursor.getLong(_cursorIndexOfFechaSuplementoMinerales);
            }
            final Long _tmpFechaEstimadaCosecha;
            if (_cursor.isNull(_cursorIndexOfFechaEstimadaCosecha)) {
              _tmpFechaEstimadaCosecha = null;
            } else {
              _tmpFechaEstimadaCosecha = _cursor.getLong(_cursorIndexOfFechaEstimadaCosecha);
            }
            final Long _tmpFechaRealCosecha;
            if (_cursor.isNull(_cursorIndexOfFechaRealCosecha)) {
              _tmpFechaRealCosecha = null;
            } else {
              _tmpFechaRealCosecha = _cursor.getLong(_cursorIndexOfFechaRealCosecha);
            }
            final Long _tmpFechaAntifungico;
            if (_cursor.isNull(_cursorIndexOfFechaAntifungico)) {
              _tmpFechaAntifungico = null;
            } else {
              _tmpFechaAntifungico = _cursor.getLong(_cursorIndexOfFechaAntifungico);
            }
            final Long _tmpFechaK1;
            if (_cursor.isNull(_cursorIndexOfFechaK1)) {
              _tmpFechaK1 = null;
            } else {
              _tmpFechaK1 = _cursor.getLong(_cursorIndexOfFechaK1);
            }
            final Long _tmpFechaK2;
            if (_cursor.isNull(_cursorIndexOfFechaK2)) {
              _tmpFechaK2 = null;
            } else {
              _tmpFechaK2 = _cursor.getLong(_cursorIndexOfFechaK2);
            }
            final Long _tmpFechaK3;
            if (_cursor.isNull(_cursorIndexOfFechaK3)) {
              _tmpFechaK3 = null;
            } else {
              _tmpFechaK3 = _cursor.getLong(_cursorIndexOfFechaK3);
            }
            final String _tmpNotas;
            if (_cursor.isNull(_cursorIndexOfNotas)) {
              _tmpNotas = null;
            } else {
              _tmpNotas = _cursor.getString(_cursorIndexOfNotas);
            }
            final String _tmpEstado;
            if (_cursor.isNull(_cursorIndexOfEstado)) {
              _tmpEstado = null;
            } else {
              _tmpEstado = _cursor.getString(_cursorIndexOfEstado);
            }
            _result = new CicloProduccion(_tmpId,_tmpNumeroCiclo,_tmpVariedad,_tmpNumeroPlantas,_tmpFechaInicioPreparacionTierra,_tmpFechaFinPreparacionTierra,_tmpFechaAbono,_tmpFechaSiembra,_tmpFechaSuplementoMinerales,_tmpFechaEstimadaCosecha,_tmpFechaRealCosecha,_tmpFechaAntifungico,_tmpFechaK1,_tmpFechaK2,_tmpFechaK3,_tmpNotas,_tmpEstado);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Object existsByNumeroCiclo(final int numero, final Long excludeId,
      final Continuation<? super Boolean> $completion) {
    final String _sql = "SELECT EXISTS(SELECT 1 FROM ciclos_produccion WHERE numeroCiclo = ? AND (? IS NULL OR id != ?))";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 3);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, numero);
    _argIndex = 2;
    if (excludeId == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindLong(_argIndex, excludeId);
    }
    _argIndex = 3;
    if (excludeId == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindLong(_argIndex, excludeId);
    }
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<Boolean>() {
      @Override
      @NonNull
      public Boolean call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final Boolean _result;
          if (_cursor.moveToFirst()) {
            final Integer _tmp;
            if (_cursor.isNull(0)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getInt(0);
            }
            _result = _tmp == null ? null : _tmp != 0;
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}

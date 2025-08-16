package eu.villacristina.lechugapro.data;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.annotation.NonNull;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.lang.Class;
import java.lang.Exception;
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
public final class GastoDao_Impl implements GastoDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Gasto> __insertionAdapterOfGasto;

  private final EntityDeletionOrUpdateAdapter<Gasto> __deletionAdapterOfGasto;

  private final EntityDeletionOrUpdateAdapter<Gasto> __updateAdapterOfGasto;

  public GastoDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfGasto = new EntityInsertionAdapter<Gasto>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `gastos` (`id`,`id_ciclo`,`tipo`,`importe`,`fecha`) VALUES (nullif(?, 0),?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Gasto entity) {
        statement.bindLong(1, entity.getId());
        statement.bindLong(2, entity.getIdCiclo());
        if (entity.getTipo() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getTipo());
        }
        statement.bindDouble(4, entity.getImporte());
        statement.bindLong(5, entity.getFecha());
      }
    };
    this.__deletionAdapterOfGasto = new EntityDeletionOrUpdateAdapter<Gasto>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `gastos` WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Gasto entity) {
        statement.bindLong(1, entity.getId());
      }
    };
    this.__updateAdapterOfGasto = new EntityDeletionOrUpdateAdapter<Gasto>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `gastos` SET `id` = ?,`id_ciclo` = ?,`tipo` = ?,`importe` = ?,`fecha` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Gasto entity) {
        statement.bindLong(1, entity.getId());
        statement.bindLong(2, entity.getIdCiclo());
        if (entity.getTipo() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getTipo());
        }
        statement.bindDouble(4, entity.getImporte());
        statement.bindLong(5, entity.getFecha());
        statement.bindLong(6, entity.getId());
      }
    };
  }

  @Override
  public Object insertGasto(final Gasto gasto, final Continuation<? super Long> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Long>() {
      @Override
      @NonNull
      public Long call() throws Exception {
        __db.beginTransaction();
        try {
          final Long _result = __insertionAdapterOfGasto.insertAndReturnId(gasto);
          __db.setTransactionSuccessful();
          return _result;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteGasto(final Gasto gasto, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __deletionAdapterOfGasto.handle(gasto);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object updateGasto(final Gasto gasto, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfGasto.handle(gasto);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object getGastosPorCiclo(final long cicloId,
      final Continuation<? super List<Gasto>> $completion) {
    final String _sql = "SELECT * FROM gastos WHERE id_ciclo = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, cicloId);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<Gasto>>() {
      @Override
      @NonNull
      public List<Gasto> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfIdCiclo = CursorUtil.getColumnIndexOrThrow(_cursor, "id_ciclo");
          final int _cursorIndexOfTipo = CursorUtil.getColumnIndexOrThrow(_cursor, "tipo");
          final int _cursorIndexOfImporte = CursorUtil.getColumnIndexOrThrow(_cursor, "importe");
          final int _cursorIndexOfFecha = CursorUtil.getColumnIndexOrThrow(_cursor, "fecha");
          final List<Gasto> _result = new ArrayList<Gasto>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Gasto _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final long _tmpIdCiclo;
            _tmpIdCiclo = _cursor.getLong(_cursorIndexOfIdCiclo);
            final String _tmpTipo;
            if (_cursor.isNull(_cursorIndexOfTipo)) {
              _tmpTipo = null;
            } else {
              _tmpTipo = _cursor.getString(_cursorIndexOfTipo);
            }
            final double _tmpImporte;
            _tmpImporte = _cursor.getDouble(_cursorIndexOfImporte);
            final long _tmpFecha;
            _tmpFecha = _cursor.getLong(_cursorIndexOfFecha);
            _item = new Gasto(_tmpId,_tmpIdCiclo,_tmpTipo,_tmpImporte,_tmpFecha);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @Override
  public Object getTodosGastos(final Continuation<? super List<Gasto>> $completion) {
    final String _sql = "SELECT * FROM gastos";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<Gasto>>() {
      @Override
      @NonNull
      public List<Gasto> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfIdCiclo = CursorUtil.getColumnIndexOrThrow(_cursor, "id_ciclo");
          final int _cursorIndexOfTipo = CursorUtil.getColumnIndexOrThrow(_cursor, "tipo");
          final int _cursorIndexOfImporte = CursorUtil.getColumnIndexOrThrow(_cursor, "importe");
          final int _cursorIndexOfFecha = CursorUtil.getColumnIndexOrThrow(_cursor, "fecha");
          final List<Gasto> _result = new ArrayList<Gasto>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Gasto _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final long _tmpIdCiclo;
            _tmpIdCiclo = _cursor.getLong(_cursorIndexOfIdCiclo);
            final String _tmpTipo;
            if (_cursor.isNull(_cursorIndexOfTipo)) {
              _tmpTipo = null;
            } else {
              _tmpTipo = _cursor.getString(_cursorIndexOfTipo);
            }
            final double _tmpImporte;
            _tmpImporte = _cursor.getDouble(_cursorIndexOfImporte);
            final long _tmpFecha;
            _tmpFecha = _cursor.getLong(_cursorIndexOfFecha);
            _item = new Gasto(_tmpId,_tmpIdCiclo,_tmpTipo,_tmpImporte,_tmpFecha);
            _result.add(_item);
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

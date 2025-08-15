package eu.villacristina.lechugapro.data;

import android.database.Cursor;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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
import kotlinx.coroutines.flow.Flow;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class IngresoDao_Impl implements IngresoDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Ingreso> __insertionAdapterOfIngreso;

  private final EntityDeletionOrUpdateAdapter<Ingreso> __deletionAdapterOfIngreso;

  private final EntityDeletionOrUpdateAdapter<Ingreso> __updateAdapterOfIngreso;

  public IngresoDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfIngreso = new EntityInsertionAdapter<Ingreso>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `ingresos` (`id`,`id_cliente`,`fecha`,`concepto`,`importe`,`estado_pago`,`notas`) VALUES (nullif(?, 0),?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Ingreso entity) {
        statement.bindLong(1, entity.getId());
        statement.bindLong(2, entity.getIdCliente());
        statement.bindLong(3, entity.getFecha());
        if (entity.getConcepto() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getConcepto());
        }
        statement.bindDouble(5, entity.getImporte());
        if (entity.getEstadoPago() == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, entity.getEstadoPago());
        }
        if (entity.getNotas() == null) {
          statement.bindNull(7);
        } else {
          statement.bindString(7, entity.getNotas());
        }
      }
    };
    this.__deletionAdapterOfIngreso = new EntityDeletionOrUpdateAdapter<Ingreso>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `ingresos` WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Ingreso entity) {
        statement.bindLong(1, entity.getId());
      }
    };
    this.__updateAdapterOfIngreso = new EntityDeletionOrUpdateAdapter<Ingreso>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `ingresos` SET `id` = ?,`id_cliente` = ?,`fecha` = ?,`concepto` = ?,`importe` = ?,`estado_pago` = ?,`notas` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Ingreso entity) {
        statement.bindLong(1, entity.getId());
        statement.bindLong(2, entity.getIdCliente());
        statement.bindLong(3, entity.getFecha());
        if (entity.getConcepto() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getConcepto());
        }
        statement.bindDouble(5, entity.getImporte());
        if (entity.getEstadoPago() == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, entity.getEstadoPago());
        }
        if (entity.getNotas() == null) {
          statement.bindNull(7);
        } else {
          statement.bindString(7, entity.getNotas());
        }
        statement.bindLong(8, entity.getId());
      }
    };
  }

  @Override
  public Object insert(final Ingreso ingreso, final Continuation<? super Unit> arg1) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfIngreso.insert(ingreso);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, arg1);
  }

  @Override
  public Object delete(final Ingreso ingreso, final Continuation<? super Unit> arg1) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __deletionAdapterOfIngreso.handle(ingreso);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, arg1);
  }

  @Override
  public Object update(final Ingreso ingreso, final Continuation<? super Unit> arg1) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfIngreso.handle(ingreso);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, arg1);
  }

  @Override
  public Flow<Ingreso> getIngresoById(final long id) {
    final String _sql = "SELECT * FROM ingresos WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, id);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"ingresos"}, new Callable<Ingreso>() {
      @Override
      @Nullable
      public Ingreso call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfIdCliente = CursorUtil.getColumnIndexOrThrow(_cursor, "id_cliente");
          final int _cursorIndexOfFecha = CursorUtil.getColumnIndexOrThrow(_cursor, "fecha");
          final int _cursorIndexOfConcepto = CursorUtil.getColumnIndexOrThrow(_cursor, "concepto");
          final int _cursorIndexOfImporte = CursorUtil.getColumnIndexOrThrow(_cursor, "importe");
          final int _cursorIndexOfEstadoPago = CursorUtil.getColumnIndexOrThrow(_cursor, "estado_pago");
          final int _cursorIndexOfNotas = CursorUtil.getColumnIndexOrThrow(_cursor, "notas");
          final Ingreso _result;
          if (_cursor.moveToFirst()) {
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final long _tmpIdCliente;
            _tmpIdCliente = _cursor.getLong(_cursorIndexOfIdCliente);
            final long _tmpFecha;
            _tmpFecha = _cursor.getLong(_cursorIndexOfFecha);
            final String _tmpConcepto;
            if (_cursor.isNull(_cursorIndexOfConcepto)) {
              _tmpConcepto = null;
            } else {
              _tmpConcepto = _cursor.getString(_cursorIndexOfConcepto);
            }
            final double _tmpImporte;
            _tmpImporte = _cursor.getDouble(_cursorIndexOfImporte);
            final String _tmpEstadoPago;
            if (_cursor.isNull(_cursorIndexOfEstadoPago)) {
              _tmpEstadoPago = null;
            } else {
              _tmpEstadoPago = _cursor.getString(_cursorIndexOfEstadoPago);
            }
            final String _tmpNotas;
            if (_cursor.isNull(_cursorIndexOfNotas)) {
              _tmpNotas = null;
            } else {
              _tmpNotas = _cursor.getString(_cursorIndexOfNotas);
            }
            _result = new Ingreso(_tmpId,_tmpIdCliente,_tmpFecha,_tmpConcepto,_tmpImporte,_tmpEstadoPago,_tmpNotas);
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
  public Flow<List<Ingreso>> getIngresosByClienteId(final long clienteId) {
    final String _sql = "SELECT * FROM ingresos WHERE id_cliente = ? ORDER BY fecha DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, clienteId);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"ingresos"}, new Callable<List<Ingreso>>() {
      @Override
      @NonNull
      public List<Ingreso> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfIdCliente = CursorUtil.getColumnIndexOrThrow(_cursor, "id_cliente");
          final int _cursorIndexOfFecha = CursorUtil.getColumnIndexOrThrow(_cursor, "fecha");
          final int _cursorIndexOfConcepto = CursorUtil.getColumnIndexOrThrow(_cursor, "concepto");
          final int _cursorIndexOfImporte = CursorUtil.getColumnIndexOrThrow(_cursor, "importe");
          final int _cursorIndexOfEstadoPago = CursorUtil.getColumnIndexOrThrow(_cursor, "estado_pago");
          final int _cursorIndexOfNotas = CursorUtil.getColumnIndexOrThrow(_cursor, "notas");
          final List<Ingreso> _result = new ArrayList<Ingreso>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Ingreso _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final long _tmpIdCliente;
            _tmpIdCliente = _cursor.getLong(_cursorIndexOfIdCliente);
            final long _tmpFecha;
            _tmpFecha = _cursor.getLong(_cursorIndexOfFecha);
            final String _tmpConcepto;
            if (_cursor.isNull(_cursorIndexOfConcepto)) {
              _tmpConcepto = null;
            } else {
              _tmpConcepto = _cursor.getString(_cursorIndexOfConcepto);
            }
            final double _tmpImporte;
            _tmpImporte = _cursor.getDouble(_cursorIndexOfImporte);
            final String _tmpEstadoPago;
            if (_cursor.isNull(_cursorIndexOfEstadoPago)) {
              _tmpEstadoPago = null;
            } else {
              _tmpEstadoPago = _cursor.getString(_cursorIndexOfEstadoPago);
            }
            final String _tmpNotas;
            if (_cursor.isNull(_cursorIndexOfNotas)) {
              _tmpNotas = null;
            } else {
              _tmpNotas = _cursor.getString(_cursorIndexOfNotas);
            }
            _item = new Ingreso(_tmpId,_tmpIdCliente,_tmpFecha,_tmpConcepto,_tmpImporte,_tmpEstadoPago,_tmpNotas);
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
  public Flow<List<Ingreso>> getAllIngresos() {
    final String _sql = "SELECT * FROM ingresos ORDER BY fecha DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"ingresos"}, new Callable<List<Ingreso>>() {
      @Override
      @NonNull
      public List<Ingreso> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfIdCliente = CursorUtil.getColumnIndexOrThrow(_cursor, "id_cliente");
          final int _cursorIndexOfFecha = CursorUtil.getColumnIndexOrThrow(_cursor, "fecha");
          final int _cursorIndexOfConcepto = CursorUtil.getColumnIndexOrThrow(_cursor, "concepto");
          final int _cursorIndexOfImporte = CursorUtil.getColumnIndexOrThrow(_cursor, "importe");
          final int _cursorIndexOfEstadoPago = CursorUtil.getColumnIndexOrThrow(_cursor, "estado_pago");
          final int _cursorIndexOfNotas = CursorUtil.getColumnIndexOrThrow(_cursor, "notas");
          final List<Ingreso> _result = new ArrayList<Ingreso>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Ingreso _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final long _tmpIdCliente;
            _tmpIdCliente = _cursor.getLong(_cursorIndexOfIdCliente);
            final long _tmpFecha;
            _tmpFecha = _cursor.getLong(_cursorIndexOfFecha);
            final String _tmpConcepto;
            if (_cursor.isNull(_cursorIndexOfConcepto)) {
              _tmpConcepto = null;
            } else {
              _tmpConcepto = _cursor.getString(_cursorIndexOfConcepto);
            }
            final double _tmpImporte;
            _tmpImporte = _cursor.getDouble(_cursorIndexOfImporte);
            final String _tmpEstadoPago;
            if (_cursor.isNull(_cursorIndexOfEstadoPago)) {
              _tmpEstadoPago = null;
            } else {
              _tmpEstadoPago = _cursor.getString(_cursorIndexOfEstadoPago);
            }
            final String _tmpNotas;
            if (_cursor.isNull(_cursorIndexOfNotas)) {
              _tmpNotas = null;
            } else {
              _tmpNotas = _cursor.getString(_cursorIndexOfNotas);
            }
            _item = new Ingreso(_tmpId,_tmpIdCliente,_tmpFecha,_tmpConcepto,_tmpImporte,_tmpEstadoPago,_tmpNotas);
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

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}

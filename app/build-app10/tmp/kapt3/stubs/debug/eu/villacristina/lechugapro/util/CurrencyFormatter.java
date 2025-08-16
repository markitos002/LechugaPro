package eu.villacristina.lechugapro.util;

/**
 * Utilidad para formatear importes con símbolo peso y separador de miles con punto.
 * Ejemplos: $ 1.000, $ 5.000, $ 20.000
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\u000b\n\u0000\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0018\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\fR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\r"}, d2 = {"Leu/villacristina/lechugapro/util/CurrencyFormatter;", "", "()V", "df", "Ljava/text/DecimalFormat;", "symbols", "Ljava/text/DecimalFormatSymbols;", "formatPeso", "", "value", "", "spaceAfterSymbol", "", "app_debug"})
public final class CurrencyFormatter {
    @org.jetbrains.annotations.NotNull()
    private static final java.text.DecimalFormatSymbols symbols = null;
    @org.jetbrains.annotations.NotNull()
    private static final java.text.DecimalFormat df = null;
    @org.jetbrains.annotations.NotNull()
    public static final eu.villacristina.lechugapro.util.CurrencyFormatter INSTANCE = null;
    
    private CurrencyFormatter() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String formatPeso(double value, boolean spaceAfterSymbol) {
        return null;
    }
}
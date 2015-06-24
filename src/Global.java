import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;


public class Global {
		public static Map<Character, AtomicInteger> cantidadLetras = new HashMap<Character, AtomicInteger>();
		public static Map<Character, BigDecimal> porcentajeLetras = new HashMap<Character, BigDecimal>();
		public static Map<String, Integer> contadorCoincidencias = new HashMap<String, Integer>();
		public static Map<String, Map> listaIdiomas = new HashMap<String, Map>();
		public static Map<String,BigDecimal> cantidadLetrasIdioma = new HashMap<String, BigDecimal>();
		public static long cantidadLetrasTotales = 0;
}

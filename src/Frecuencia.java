import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class Frecuencia {

	BigDecimal margenSuperior, margenInferior, numeroMargen = new BigDecimal("2.5");
	
	public void calcularFrecuencia() {
		String porcentajeAux;
		BigDecimal porcentaje;
		DecimalFormat formatoDecimal = new DecimalFormat("#.##");
		Iterator<Character> iterator = Global.cantidadLetras.keySet().iterator();

		// Se itera el map de cantidad de letras
		while (iterator.hasNext()) {
			char key = iterator.next();
			AtomicInteger value = Global.cantidadLetras.get(key);
			
			// Se calcula el % de aparicion de cada letra
			porcentajeAux = formatoDecimal.format((value.get() * 100.00) / Global.cantidadLetrasTotales);
			porcentaje = new BigDecimal(porcentajeAux.replace(",","."));
			
			// Se almacena en un nuevo map < LETRA > < % DE APARICION >
			Global.porcentajeLetras.put(key, porcentaje);
		}
	}
	
	public void compararIdiomas() {
		Iterator<String> iteratorIdiomas = Global.listaIdiomas.keySet().iterator();
		Map<String, Map> mapTmp = new HashMap<String, Map>();
		int contCoincidencias, max = 0;
		String idiomaTexto = null;

		while (iteratorIdiomas.hasNext()) {
			// Se itera cada idioma
			contCoincidencias = 0;
			String key = iteratorIdiomas.next();
			mapTmp = Global.listaIdiomas.get(key);

			// Se itera la frecuencia de cada idioma
			Iterator<String> iteratorFrec = mapTmp.keySet().iterator();
			while (iteratorFrec.hasNext()) {
				// Se itera cada frecuencia
				String key2 = iteratorFrec.next();
				BigDecimal value = (BigDecimal) mapTmp.get(key2);

				char[] charArray = key2.toCharArray();
				char letra = charArray[0];

				margenSuperior = value.add(numeroMargen);
				margenInferior = value.subtract(numeroMargen);

				BigDecimal porcentajeLetra = Global.porcentajeLetras.get(letra);

				if (porcentajeLetra != null) {
					if ((porcentajeLetra.compareTo(margenInferior) >= 0)
							&& (porcentajeLetra.compareTo(margenSuperior) <= 0)) {
						contCoincidencias++;
					}
				}
				Global.contadorCoincidencias.put(key, contCoincidencias);
				if ((max == 0) || (contCoincidencias > max)) {
					idiomaTexto = key;
					max = contCoincidencias;
				}
			}
		}

		System.out.println("El idioma del texto es: " + idiomaTexto);
	}
	
}

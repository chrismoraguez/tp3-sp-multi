import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ProcesadorTexto {

	public static void main(String[] arg) throws Throwable {

		String linea; 
		String ruta = "";
		Frecuencia frec = new Frecuencia();
		Idioma idioma = new Idioma();

		FileReader fr = null;
		BufferedReader br = null;
		File texto = null;

		/**
		 * Se lee el TXT
		 */
		System.out.println("<----------------------------------|   Versión MultiHilo   |----------------------------------->");
		// Se solicita que se ingrese la ruta del TXT que va a ser leido
		System.out.println("Por favor, ingresar la ruta del directorio en donde se encuentra el archivo de texto a procesar:");
		Scanner scanner = new Scanner(System.in);
		ruta = scanner.nextLine();
		
		// Guardamos el tiempo de inicio
		long startTime = System.currentTimeMillis();
		
		texto = new File(ruta);
		fr = new FileReader(texto);
		br = new BufferedReader(fr);
		
		ExecutorService threadPool = Executors.newFixedThreadPool(10);

		while ((linea = br.readLine()) != null) {

			Archivo tarea = new Archivo(linea);
			threadPool.execute(tarea);
		}

		threadPool.shutdown();

		while (!threadPool.isTerminated()) {
		}

		// Se ejecuta metodo para calcular la frecuencia de letras para el texto leido
		frec.calcularFrecuencia();
		
		// Se ejecuta metodo para leer la base de idiomas y guardar en el map
		idioma.leerIdiomas();
		// Se ejecuta metodo para identificar el idioma del txt leido
		frec.compararIdiomas();

		// Se obtiene y se imprime por pantalla el tiempo transcurrido
		long endTime = System.currentTimeMillis();
		long totalTime = (endTime - startTime);
		System.out.println("Tiempo de ejecución: " + totalTime + " milisegs.");
	}
}
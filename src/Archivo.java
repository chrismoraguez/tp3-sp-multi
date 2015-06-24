import java.util.concurrent.atomic.AtomicInteger;

public class Archivo implements Runnable {
	String linea;
	
	public Archivo(String linea){
		this.linea = linea;
	}
 
	@Override
	public void run() {
		char caracter;
		// Para la linea actual se recorre cada caracter
		for (int i = 0; i < this.linea.length(); i++) {
			caracter = Character.toLowerCase(this.linea.charAt(i));
			// Solo ingresa al if si el caracter es una letra
			if (Character.isLetter(caracter)) {
				if (Global.cantidadLetras.containsKey(caracter)) {
					// La letra ya existe en el map
					Global.cantidadLetras.get(caracter).incrementAndGet();
					Global.cantidadLetrasTotales++;
				} else {
					// La letra no existe en el map
					Global.cantidadLetras.put(caracter, new AtomicInteger(1));
					Global.cantidadLetrasTotales++;
				}
			}

		}
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
			}
		
	}
}

// Clase principal para la implementación de QuickSort concurrente utilizando hilos
public class QuicksortConcurrente {

    private int[] array; // Array a ordenar
    private int low; // Índice inicial
    private int high; // Índice final

    // Constructor de la clase
    public QuicksortConcurrente(int[] array, int low, int high) {
        this.array = array; // Inicializa el array
        this.low = low; // Inicializa el índice bajo
        this.high = high; // Inicializa el índice alto
    }

    // Función de partición similar a la versión secuencial
    private int partition(int[] array, int low, int high) {
        int pivot = array[high]; // Selecciona el último elemento como pivote
        int i = (low - 1); // Índice del elemento más pequeño
        for (int j = low; j < high; j++) { // Itera desde el índice bajo hasta el alto - 1
            if (array[j] < pivot) { // Si el elemento actual es menor que el pivote
                i++; // Incrementa el índice del elemento más pequeño
                // Intercambia arr[i] y arr[j]
                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
            }
        }
        // Intercambia arr[i+1] y arr[high] (o pivote)
        int temp = array[i + 1];
        array[i + 1] = array[high];
        array[high] = temp;
        return i + 1; // Retorna el índice de partición
    }

    // Método que se ejecuta cuando se invoca la tarea
    public void sort() {
    	
    	// Comprueba si el subarray tiene más de un elemento
        if (low < high) { 
        	
        	// Particiona el array y obtiene el índice del pivote
            int pi = partition(array, low, high); 
            
            // Crea y ejecuta el hilo para ordenar la parte izquierda
            Thread leftThread = new Thread(new Runnable() {
                @Override
                public void run() {
                    new QuicksortConcurrente(array, low, pi - 1).sort();
                }
            });
            
            // Crea y ejecuta el hilo para ordenar la parte derecha
            Thread rightThread = new Thread(new Runnable() {
                @Override
                public void run() {
                    new QuicksortConcurrente(array, pi + 1, high).sort();
                }
            });
            
            leftThread.start();
            rightThread.start();
            
            // Espera a que ambos hilos terminen
            try {
                leftThread.join();
                rightThread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    // Método principal para probar el código
    public static void main(String[] args) {
        int[] array = {10, 7, 8, 9, 1, 5, 4, 2, 6, 3}; // Array de ejemplo
        
        // Crea una instancia de QuicksortConcurrente
        QuicksortConcurrente sorter = new QuicksortConcurrente(array, 0, array.length - 1); 
        
        // Mide el tiempo de ejecución
        long startTime = System.nanoTime(); // Obtiene el tiempo de inicio en nanosegundos
        sorter.sort(); // Ejecuta el método sort
        long endTime = System.nanoTime(); // Obtiene el tiempo de finalización en nanosegundos
        
        // Calcula el tiempo de ejecución
        long duration = (endTime - startTime); // Tiempo de ejecución en nanosegundos
        for (int i : array) { // Itera sobre el array ordenado
            System.out.print(i + " "); // Imprime cada elemento
        }
        System.out.println(); // Nueva línea
        System.out.println("Tiempo de ejecución (nanosegundos): " + duration); // Imprime el tiempo de ejecución
    }
}


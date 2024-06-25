//Clase Quicksort

import java.util.Random;

public class Quicksort {
    
    // Función que realiza la partición del array y devuelve el índice de partición
    int partition(int arr[], int low, int high) {
 
        int pivot = arr[high]; 	// Selecciona el último elemento como pivote
        int i = (low - 1); // Índice del elemento más pequeño
        
        // Itera desde el índice bajo hasta el alto - 1
        for (int j = low; j < high; j++) {
        	
            // Si el elemento actual es menor o igual al pivote
            if (arr[j] <= pivot) {
                i++; // Incrementa el índice del elemento más pequeño
                // Intercambia arr[i] y arr[j]
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        
        // Intercambia arr[i+1] y arr[high] (o pivote)
        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;
        
        return i + 1; // Retorna el índice de partición
    }

    // Función principal que implementa QuickSort
    // arr[] --> Array a ordenar,
    // low  --> Índice inicial,
    // high  --> Índice final
    void sort(int arr[], int low, int high) {
        if (low < high) {
        	
            // pi es el índice de partición, arr[pi] está en la posición correcta
            int pi = partition(arr, low, high);
            
            // Ordena recursivamente los elementos antes y después de la partición
            sort(arr, low, pi - 1);
            sort(arr, pi + 1, high);
        }
    }

    // Función principal (driver) 
    public static void main(String args[]) {
    	int n = 100; // Número de elementos en el array
        int array[] = new int[n];
        Random random = new Random();

        // Llenar el array con números aleatorios
        for (int i = 0; i < n; i++) {
            array[i] = random.nextInt(100); // Números aleatorios entre 0 y el número que reciba por parámetro
        }
        
        // Crea una instancia de QuickSort
        Quicksort ob = new Quicksort(); 
        
        // Mide el tiempo de ejecución
        long startTime = System.nanoTime(); // Obtiene el tiempo de inicio en nanosegundos
        
        ob.sort(array, 0, n - 1); // Llama a la función sort
        
        long endTime = System.nanoTime(); // Obtiene el tiempo de finalización en nanosegundos

        // Calcula el tiempo de ejecución
        long duration = (endTime - startTime); // Tiempo de ejecución en nanosegundos
        System.out.println("Array ordenado"); // Imprime mensaje
        
        // Itera sobre el array ordenado
        for (int i = 0; i < n; ++i) {
            System.out.print(array[i] + " "); 
            System.out.println(); 
            
            // Imprime el tiempo de ejecución
            System.out.println("Tiempo de ejecución (nanosegundos): " + duration); 
        }
    }
}
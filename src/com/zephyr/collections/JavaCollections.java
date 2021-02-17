package com.zephyr.collections;

import java.util.*;
import java.util.stream.Collectors;

public class JavaCollections {


    // List
    private ArrayList<Integer> arrayList = new ArrayList<>();

    // Deque interface
    ArrayDeque<Integer> arrayDeque = new ArrayDeque<>();

    // List and Deque interface
    LinkedList<Integer> linkedList = new LinkedList<>();

    // Queue interface
    PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();

    // Set interface
    HashSet<Integer> hashSet = new HashSet<>();
    LinkedHashSet<Integer> linkedHashSet = new LinkedHashSet<>();

    // Sorted Set interface
    TreeSet<Integer> treeSet = new TreeSet<>();

    // Map interface
    HashMap<Integer, String> hashMap = new HashMap<>();
    TreeMap<Integer, String> treeMap = new TreeMap<>();

    public void test() {
        //testArrayList();
        testStreamArrayList();
    }


    /**
     * Un Stream es una secuencia de objetos que soportan varios métodos
     * para poder devolver una salida deseada.
     *
     *  - No es una estructura de datos.
     *  - No cambian los datos originales.
     *  - Cada operación intermedia es realizada en modo Lazy.
     *
     *  Algunas de las operaciones intermedias soportadas son:
     *
     *   -- map: retorna un Stream con el resultado de aplicar la función especificada.
     *   -- filter: Selecciona elementos en base a un predicado.
     *   -- sorted: Se usa para ordenar el Stream.
     *
     *   Algunas de las operaciones finales son:
     *
     *   -- collect: se utiliza para devolver el resultado de las
     *   operaciones intermedias realizadas en la secuencia.
     *
     *   -- forEach: se usa para iterar en cada elemento del Stream.
     *
     *   -- reduce: El método de reducción se utiliza para reducir los elementos
     *   de una secuencia a un solo valor. El método reduce toma un BinaryOperator
     *   como parámetro.
     */
    private void testStreamArrayList() {
        ArrayList<Integer> arrayList = new ArrayList<>(List.of(1,2,3,4,5,6,7,8,9,10));

        // Ejemplo de map y pasarlo a una lista modificable
        List<Integer> res = arrayList.stream()
                .map(elemento -> elemento + 10)
                .collect(Collectors.toList());
        System.out.println(res);
        res.add(-1);

        // Ejemplo de map y pasarlo a una lista no modificable
        List<Integer> res2 = arrayList.stream()
                .map(elemento -> elemento + 10)
                .collect(Collectors.toUnmodifiableList());
        System.out.println(res2);
        // res2.add(-1); Da una excepción porque no se puede modificar

        // Retorna una lista marcando true o false según la condición
        List<Boolean> res3 = arrayList.stream()
                .map(elemento -> elemento > 5)
                .collect(Collectors.toList());
        System.out.println(res3);

        // Retorna una lista con los elementos que cumplen la condición
        List<Integer> res4 = arrayList.stream()
                .filter(elemento -> elemento > 5)
                .collect(Collectors.toList());
        System.out.println(res4);

        // Retorna una lista con los elementos que cumplen la condición y les realiza una operación
        Set<Integer> res5 = arrayList.stream()
                .filter(elemento -> elemento > 5)
                .map(elemento -> elemento + 10)
                .collect(Collectors.toSet());
        System.out.println(res5);

        // Ejemplo de encadenar Streams. 1º Filtra + operación. 2º Filtra y devuelve la cuenta.
        long res6 = arrayList.stream()
                .filter(elemento -> elemento > 5)
                .map(elemento -> elemento + 10)
                .collect(Collectors.toSet())
                .stream().
                filter(elemento -> elemento > 18)
                .count();
        System.out.println(res6);


        // Ejemplo con clases
        Producto p1 = new Producto("Azúcar", 10);
        Producto p2 = new Producto("Pollo", 20);
        Producto p6 = new Producto("Pollo 2", 20);
        Producto p3 = new Producto("Pasta", 30);
        Producto p4 = new Producto("Servilletas", 40);
        Producto p5 = new Producto("Leche", 50);

        ArrayList<Producto> productoArrayList = new ArrayList<>(List.of(p1, p4, p3, p2, p5));
        System.out.println(productoArrayList);

        // Ordenar por peso
        List<Producto> productosPorPeso = productoArrayList.stream()
                .filter(producto -> producto.getPeso() > 20)
                .sorted(Comparator.comparing(Producto::getPeso))
                // equivalente .sorted((o1, o2) -> o1.getPeso().compareTo(o2.getPeso()))
                .collect(Collectors.toList());
        productosPorPeso.stream()
                .forEach(producto -> System.out.println(producto.getNombre()));

        System.out.println(productosPorPeso);

        Optional<Producto> productoMasPesado = productosPorPeso.stream()
                .reduce((prod1, prod2) -> prod1.getPeso() > prod2.getPeso() ? prod1 : prod2);
        System.out.println(productoMasPesado);

        Integer sumaDePesos = productosPorPeso.stream()
                .reduce(0, (sum, prod) -> sum + prod.getPeso(), Integer::sum);
        System.out.println(sumaDePesos);

        Integer sumaDePesos2 = productosPorPeso.stream()
                .mapToInt(Producto::getPeso)
                .sum();
        System.out.println(sumaDePesos2);

        Integer sumaDePesos3 = productosPorPeso.stream()
                .collect(Collectors.summingInt(Producto::getPeso));
        System.out.println(sumaDePesos3);

        String productosString = productosPorPeso.stream()
                .reduce("", (sum, prod) -> sum + prod.getNombre(), String::concat);
        System.out.println(productosString);

    }

    /**
     * Lista dinámica que va ajustando su tamaño en función de los elementos que tenga.
     */
    private void testArrayList() {

        // Añadir elementos
        arrayList.add(1);
        arrayList.add(2);
        arrayList.add(3);
        arrayList.add(4);
        arrayList.add(5);
        System.out.println(arrayList);

        // Añadir desde una colección existente
        ArrayList<Integer> arrayListPrueba = new ArrayList<>(List.of(10,20,30));
        arrayList.addAll(arrayListPrueba);
        System.out.println(arrayList);

        // Comprobar si una colección tiene elementos
        System.out.println(arrayList.isEmpty());

        // Inicializar desde otra colección
        ArrayList<Integer> arrayListPrueba2 = new ArrayList<>(arrayListPrueba);
        System.out.println(arrayListPrueba2);

        // Eliminar elemento en un índice en concreto
        arrayList.remove(0);
        System.out.println(arrayList);

        // Comprobar si una colección contiene ciertos elementos
        boolean contains = arrayList.contains(20);
        System.out.println(contains);

        boolean containsAll = arrayList.containsAll(arrayListPrueba);
        System.out.println(containsAll);

        // Eliminar de una colección los que se encuentran en otra.
        arrayList.removeAll(arrayListPrueba);
        System.out.println(arrayList);

        // Eliminar todos los elementos
        arrayList.clear();
        System.out.println(arrayList);

    }
}

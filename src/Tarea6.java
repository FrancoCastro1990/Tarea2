import java.util.ArrayList;
import java.util.Scanner;

class Entrada {


    private String tipo;
    private int valor;
    Entrada(String tipo,int valor){
        this.tipo=tipo;
        this.valor=valor;
    }

    public String getTipo() {
        return tipo;
    }

    public int getValor() {
        return valor;
    }
}

class EntradasTipo extends Entrada{



    private int cantidad=0;
    EntradasTipo(String tipo, int valor) {
        super(tipo, valor);
    }
    EntradasTipo(String tipo, int valor, int cantidad){
        super(tipo, valor);
        this.cantidad = cantidad;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}

public class Tarea6 {
    static int VALOR_VIP = 20000;
    static int VALOR_PLATEA_BAJA = 15000;
    static int VALOR_PLATEA_ALTA = 10000;
    static int VALOR_PALCO = 5000;
    public static void main(String[] args) {


        ArrayList<EntradasTipo> entradasDisponibles=new ArrayList<>();
        entradasDisponibles.add(new EntradasTipo("VIP", VALOR_VIP,2));
        entradasDisponibles.add(new EntradasTipo("PLATEA_ALTA", VALOR_PLATEA_BAJA,4));
        entradasDisponibles.add(new EntradasTipo("PLATEA_BAJA", VALOR_PLATEA_ALTA,6));
        entradasDisponibles.add(new EntradasTipo("PALCO", VALOR_PALCO,8));

        ArrayList<Entrada> entradasCliente=new ArrayList<>();

        Scanner scanner = new Scanner(System.in);

        int opcion;
        int ubicacion;
        int cantidad;

        System.out.println("---------------------");

        System.out.println("Bienvenido al sistema de gestion de entradas del teatro.");
        System.out.println("Seleccione alguna opcion disponible ingresando el numero correspondiente en todo el flujo");

        do{
            System.out.println("---------------------");
            System.out.println("[1] Agregar entrada");
            System.out.println("[2] Eliminar entrada");
            System.out.println("[3] Imprimir boleta y salir");

            opcion = scanner.nextInt();
            if(opcion<1 || opcion>3){
                System.out.println("Ingrese una opcion valida");
            } else if (opcion == 1) {
                //logica de agregar entrada

                //ubicacion
                do {
                    System.out.println("---------------------");
                    System.out.println("Ingrese la ubicacion");
                    System.out.print("[1] VIP, [2] Platea baja, [3] Platea alta, [4] palcos:");
                    ubicacion= scanner.nextInt();
                    if(ubicacion<1 || ubicacion>4){
                        System.out.println("Ingrese una opcion valida");
                    }
                } while(ubicacion<1 || ubicacion>4);
                if(entradasDisponibles.get(ubicacion-1).getCantidad()<=0) {
                    System.out.println("No quedan del tipo de entrada "+entradasDisponibles.get(ubicacion-1).getTipo());
                } else {
                    //CANTIDAD DE ENTRADAS
                    do {
                        System.out.println("---------------------");
                        System.out.print("Ingrese la cantidad de entradas a comprar:");
                        cantidad= scanner.nextInt();
                        if(cantidad<1){
                            System.out.println("Ingrese un numero mayor a 0");
                        }
                        if(cantidad > entradasDisponibles.get(ubicacion-1).getCantidad()){
                            System.out.println("Quedan solo "+entradasDisponibles.get(ubicacion-1).getCantidad()+" entradas.");
                        }


                    } while(cantidad<1 || cantidad > entradasDisponibles.get(ubicacion-1).getCantidad());

                    //AGREGAMOS LA CANTIDAD DE ENTRADAS A LA VENTA
                    int totalAux=0;
                    for (int i = 0; i < cantidad; i++) {
                        totalAux=totalAux+entradasDisponibles.get(ubicacion-1).getValor();
                        entradasCliente.add(new Entrada(entradasDisponibles.get(ubicacion-1).getTipo(),entradasDisponibles.get(ubicacion-1).getValor()));
                    }
                    System.out.println("Se agregaron "+cantidad+" del tipo "+entradasDisponibles.get(ubicacion-1).getTipo());
                    System.out.println("Por un valor de :$" +totalAux);
                    //ELIMINAMOS LA CANTIDAD SELECCIONADA
                    entradasDisponibles.get(ubicacion-1).setCantidad(entradasDisponibles.get(ubicacion-1).getCantidad()-cantidad);
                }

            } else if (opcion==2){
                //logica de eliminar alguna entrada
                int entradaAEliminar=0;
                do{
                    System.out.println("---------------------");
                    System.out.println("Ingrese el numero de la entrada a eliminar:");
                    System.out.println(entradasCliente.size());
                    for (int i = 0; i < entradasCliente.size(); i++) {
                        System.out.println("["+(i+1)+"] Entrada "+entradasCliente.get(i).getTipo()+" valor:$ "+entradasCliente.get(i).getValor());
                    }
                    entradaAEliminar= scanner.nextInt();
                    if(entradaAEliminar<1 || entradaAEliminar>entradasCliente.size()){
                        System.out.println("Ingrese un numero valido");
                    } else {
                        int tipoEntrada=-1;
                        switch (entradasCliente.get(entradaAEliminar-1).getTipo()) {
                            case "VIP": tipoEntrada=0;
                                        break;
                            case "PLATEA_ALTA": tipoEntrada=1;
                                        break;
                            case "PLATEA_BAJA": tipoEntrada=2;
                                        break;
                            case "PALCO": tipoEntrada = 3;
                                        break;

                        }

                        if(tipoEntrada!=-1){
                            entradasDisponibles.get(tipoEntrada).setCantidad(entradasDisponibles.get(tipoEntrada).getCantidad()+1);
                            entradasCliente.remove(entradaAEliminar-1);
                            System.out.println("Entrada eliminada correctamente");
                            break;
                        }

                    }
                }while(entradaAEliminar<1 || entradaAEliminar>entradasCliente.size());
            }
        } while(opcion!=3);

        if(entradasCliente.isEmpty()) {
            System.out.println("No ingreso ninguna entrada.");
        } else {
            System.out.println("---------------------");
            System.out.println("Detalles de su boleta");
            System.out.println("---------------------");
            int total=0;
            for (int i = 0; i < entradasCliente.size(); i++) {
                total=total+entradasCliente.get(i).getValor();
                System.out.println((i+1)+" Entrada "+entradasCliente.get(i).getTipo()+" valor =$"+entradasCliente.get(i).getValor());
            }
            System.out.println("Total sin iva =$"+total);
            System.out.println("Total mas iva(19%) =$"+Math.round(total+(total*0.19)));
        }

        System.out.println("Muchas gracias por ocupar el sistema de gestion de entradas.");
        System.out.println("---------------------");
    }
}
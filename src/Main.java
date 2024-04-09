import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static int VALOR_VIP = 25000;
    static int VALOR_PLATEA_BAJA = 19000;
    static int VALOR_PLATEA_ALTA = 11000;
    static int VALOR_PALCO = 7200;
    static double DESC_ESTUDIANTE = 0.10;
    static double DESC_3RA_EDAD = 0.15;
    static int EDAD_MINIMA_3RA_EDAD = 60;
    public static void main(String[] args) {


        int opcion = 0;
        int ubicacion = 0;
        int edad = 0;
        int esEstudiante = 0;
        double descuento = 0;
        double total = 0;

        ArrayList<Double> entradas=new ArrayList<>();

        Scanner scanner = new Scanner(System.in);

        System.out.println("Bienvenido al Teatro Moro.");
        System.out.println("Sistema de venta de entradas.");

        do{
            descuento = 0;
            System.out.println("----------");
            if(!entradas.isEmpty()) {
                System.out.println("[1] Agregar otra entrada");
                System.out.println("[2] Ver promociones disponibles");
                System.out.println("[3] Salir y ver total");
            } else {
                System.out.println("[1] Agregar una entrada");
                System.out.println("[2] Ver promociones disponibles");
                System.out.println("[3] Salir");
            }

            opcion = scanner.nextInt();
            if(opcion<1 || opcion>3){
                System.out.println("Ingrese una opcion valida");
            } else if (opcion == 1) {

                //ubicacion
                do {
                    System.out.println("Ingrese la ubicacion");
                    System.out.print("[1] VIP, [2] Platea baja, [3] Platea alta, [4] palcos:");
                    ubicacion= scanner.nextInt();
                    if(ubicacion<1 || ubicacion>4){
                        System.out.println("Ingrese una opcion valida");
                    }
                } while(ubicacion<1 || ubicacion>4);

                //edad
                do {
                    System.out.print("Ingrese Su edad:");
                    edad= scanner.nextInt();
                    if(edad<1){
                        System.out.println("Ingrese una edad valida (mayor que 1)");
                    }
                } while(edad<1);

                if(edad>=EDAD_MINIMA_3RA_EDAD) {
                    descuento = DESC_3RA_EDAD;
                }

                //es estudiante
                do {
                    System.out.println("Es estudiante?");
                    System.out.print("[1] Si, [2] no:");
                    esEstudiante= scanner.nextInt();
                    if(esEstudiante<1 || esEstudiante>2){
                        System.out.println("Ingrese una opcion valida");
                    }
                } while(esEstudiante<1 || esEstudiante>2);


                if(esEstudiante==1){
                    descuento = DESC_ESTUDIANTE;
                }

                switch (ubicacion){
                    case 1:
                        System.out.println("Se agrego una entrada VIP de valor: $"+VALOR_VIP);
                        System.out.println("El descuento es de: $"+((VALOR_VIP*descuento)));
                        System.out.println("Valor final: $"+(VALOR_VIP-(VALOR_VIP*descuento)));
                        entradas.add(VALOR_VIP-(VALOR_VIP*descuento));
                        break;
                    case 2:
                        System.out.println("Se agrego una entrada PLATEA BAJA de valor: $"+VALOR_PLATEA_BAJA);
                        System.out.println("El descuento es de: $"+((VALOR_PLATEA_BAJA*descuento)));
                        System.out.println("Valor final: $"+(VALOR_PLATEA_BAJA-(VALOR_PLATEA_BAJA*descuento)));
                        entradas.add(VALOR_PLATEA_BAJA-(VALOR_PLATEA_BAJA*descuento));
                        break;
                    case 3:
                        System.out.println("Se agrego una entrada PLATEA ALTA de valor: $"+VALOR_PLATEA_ALTA);
                        System.out.println("El descuento es de: $"+((VALOR_PLATEA_ALTA*descuento)));
                        System.out.println("Valor final: $"+(VALOR_PLATEA_ALTA-(VALOR_PLATEA_ALTA*descuento)));
                        entradas.add(VALOR_PLATEA_ALTA-(VALOR_PLATEA_ALTA*descuento));
                        break;
                    case 4:
                        System.out.println("Se agrego una entrada PALCO de valor: $"+VALOR_PALCO);
                        System.out.println("El descuento es de: $"+((VALOR_PALCO*descuento)));
                        System.out.println("Valor final: $"+(VALOR_PALCO-(VALOR_PALCO*descuento)));
                        entradas.add(VALOR_PALCO-(VALOR_PALCO*descuento));
                        break;
                }
            } else if (opcion==2){
                System.out.println("Tenemos las siguientes promociones:");
                System.out.println("Promocion estudiante: Si eres estudiante tienes un 10% de descuento!");
                System.out.println("Promocion 3ra edad: Si tienes mas de 65 a;os tienes un 15% de descuento!");
                System.out.println("Promocion numero de entradas: Si compras mas de 3 entradas, tienes un descuento extra del 5% en tu total!");
            }

        }while(opcion!=3);

        //mostrar todos los valores
        for (int i = 0; i < entradas.size(); i++) {
            System.out.println("Entrada numero "+(i+1)+" = $"+entradas.get(i));
            total = total+entradas.get(i);
        }
        if(entradas.size()>3){
            System.out.println("Tienes un 5% de descuento en tu total");
            total=total-(total*0.05);
            System.out.println("El total a pagar es: $"+total);
        } else {
            System.out.println("El total a pagar es: $"+total);

        }
        System.out.println("....");
    }
}
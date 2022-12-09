/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package prueba;

import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class ComisionVentas {
    // Variable para llevar el control del bucle WHILE
    static boolean condicionBucle  = true;
    // Variable que permitira manejar la condicion del bucle WHILE
    static String opcion = "";
    static String texto = "";
    public static void main(String[] args) {
        // Importe de la Cuota mensual de Vendedores Junior
        int cuotaMensualJunior          = 400000;
        // Importe de la Cuota mensual de Vendedores Senior
        int cuotaMensualSenior          = 750000;
        // Importe de lo vendido en el mes por vendedores JUNIOR
        double ventasDelMesJunior       = 0.0;
        // Importe de lo vendido en el mes por vendedores SENIOR
        double ventasDelMesSenior       = 0.0;
        // Variable donde se guardara el importe del calculo de comision de cada vendedor
        double importeDeComision        = 0.0;
        // ARREGLO donde se guaddaran los datos de DNI y COMSION de cada vendedor para exportarlo
        // Es de 20 elementos pq sabemos que son 20 vededores
        String [] comisionesVendedores    = new String[20];
        // Contador para el ARREGLO
        int contador                    = 0;
        // Variable que guardara el DNI de cada vendedor conforme se van ingresando
        String dniVendedor              = "";
        // Variable que guardara el TIPO DE VENDEDOR (JUNIOR / SENIOR) de cada vendedor conforme se van ingresando
        String tipoVendedor             = "";
        // Variable donde se ingresara el importe de lo vendido por cada vendedor conforme se va ingresando
        Double ventasPersonal           = 0.0;

        Scanner input = new Scanner(System.in);
        System.out.println("--- DATOS GENERALES DE LAS VENTAS DEL MES ---\n");
        System.out.println("---------------------------------------------");
        System.out.println("Ingrese las ventas totales del mes de Vendedores JUNIOR :");
        ventasDelMesJunior = input.nextDouble();
        System.out.println("Ingrese las ventas totales del mes de Vendedores SENIOR :");
        ventasDelMesSenior = input.nextDouble();
        System.out.println("--- Ahora ingresar datos de los vendedores ---\n");
        System.out.println("----------------------------------------------");

        while (condicionBucle == true) {
            System.out.println("Ingrese el DNI del Vendedor :");
            dniVendedor = input.next();
            System.out.println("Ingrese el tipo de vendedor (JUNIOR / SENIOR) : ");
            tipoVendedor = input.next();
            System.out.println("Ingrese el importe de ventas del mes de Vendedor :");
            ventasPersonal = input.nextDouble();

            System.out.println("Ventas del mes del vendedor : " + ventasPersonal);
            System.out.println("Cuota mensual senior : " + cuotaMensualSenior);

            switch (tipoVendedor.toUpperCase()) {
                case "JUNIOR" :
                    //System.out.println("Si entro a JUNIOR");
                    if (ventasDelMesJunior >= cuotaMensualJunior) {
                        importeDeComision = importeDeComision + (0.01 * ventasDelMesJunior);
                        if (ventasPersonal > 40000 ) {
                            //System.out.println("paso por JUNIOR");
                            importeDeComision = importeDeComision +  (ventasPersonal - 40000) * 0.005;
                        }
                    }
                    break;
                case "SENIOR" :
                    if (ventasDelMesSenior >= cuotaMensualSenior) {  // ventasDelMesSenior
                        //System.out.println("si entro a SENIOR");
                        importeDeComision = importeDeComision + (0.015 * ventasDelMesSenior);
                        if (ventasPersonal > 75000) {
                            //System.out.println("paso por SENIOR");
                            importeDeComision = importeDeComision + (ventasPersonal - 75000) * 0.0075;
                        }
                    }
                    break;
                default:
                    importeDeComision = 0;

            }

            // Pasar el importe de comision a solo 2 decimales
            importeDeComision = Math.round(importeDeComision*100)/100.0;

            System.out.println("Guardar en el arreglo");
            comisionesVendedores[contador] = dniVendedor + "," + Double.toString(importeDeComision);
            contador = contador + 1;

            importeDeComision   = 0.0;
            ventasPersonal      = 0.0 ;

            Scanner lector = new Scanner(System.in);
            //boolean opcion ; // = true;
            System.out.println("¿Desea continuar registrando datos de los vendedores?, ingrese SI o NO");
            opcion = input.next();  // nextLine();
            // opcion = lector.nextLine();
            opcion = opcion.toUpperCase(); // converto a mayusculas

            // boolean opcionBoolean;
            if (opcion.equals("SI")) condicionBucle = true;
            else condicionBucle = false;

        }

        //System.out.println("El arreglo final es : " + Arrays.toString(comisionesVendedores));

        // Generar el archivo comisiones.txt
        generararchivotxt(comisionesVendedores);
        System.out.println("Se ha generado el archivo comisiones.txt");
        System.out.println("Verificar el texto generado en la carpeta JAVA/PRUEBA");
    }

    public static void generararchivotxt(String [] comisionesVendedores) {
        String linea = "";
        try {
            PrintWriter writer = new PrintWriter("comisiones.txt", "UTF-8");
            // grabar las DNI y Comisines en el archivo tecto
            for (int i=0; i<comisionesVendedores.length; i++){
                linea = comisionesVendedores[i];
                writer.println(linea);
            }
            //writer.println("Primera línea");
            //writer.println("Segunda línea");
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

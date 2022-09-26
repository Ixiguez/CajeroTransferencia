
package cajeroauto;
import java.awt.BorderLayout;
import javax.swing.*;       //Para importar todos los swing
import java.util.*;


public class Cajeroauto extends JFrame {
    public String nombre, password;
    float saldo;
    int posActualUser;
    int numCuenta;
    String[] names = {"Luis Carlos", "Julian", "El Macho"};
    String[] passwords = {"PagenRedesMuneco", "Phone701", "Papi123"}; //Creamos nuestras cuentas usuario saldo y contraseña para ya sea ingresar o transferir
    float[] saldos = {1000, 2000, 3000};
    int[] cuentas = {111111,222222,333333};
    int size = names.length;


    
    public void authentifi(){
        boolean caso = false;
        int posData = 0;
        for (int i = 0; i < size; i++) {
            if(nombre.equals(names[i]) && password.equals(passwords[i])){
                caso = true;
                posData = i;
                posActualUser = i;
            }
        }
        
        if(caso){        
            System.out.println("****  Usuario autentificado correctamente  ****");
            saldo = saldos[posData];
        } else {
            System.out.println("****  Su usuario se ha creado satisfactoriamente  ****");
            createUser();
        }
    }
    
    public void createUser() {
        int sizeArray = (size+1);
        String[] names2 = new String[sizeArray];
        String[] passwords2 = new String[sizeArray];
        float[] saldo2 = new float[sizeArray];
        int[] cuentas2 = new int[sizeArray];
        
        names2[size] = nombre;
        passwords2[size] = password;
        saldo2[size] = 0;
        
        cuentas2[size] = numCuenta;
                
        for (int i = 0; i < size; i++) {
            names2[i] = names[i];
            passwords2[i] = passwords[i];
            saldo2[i] = saldos[i];
        }
        
        size++;
        
        names = new String[size];
        passwords = new String[size];
        saldos = new float[size];
        
        for (int i = 0; i < size; i++) {
            names[i] = names2[i];
            passwords[i] = passwords2[i];
            saldos[i] = saldo2[i];
        }
        posActualUser = (size-1);
        saldo = saldos[(size - 1)];
    }
    
    public void transfer() {
        Scanner entrada = new Scanner(System.in);
        float saldoAnt = 0;
        float saldoCuentaPrin = saldo;
        boolean find = false;
        
        System.out.print("Ingrese el nombre de la cuenta destino a transferir: ");
        String nombreDest = entrada.next();

        
        
        for (int i = 0; i < size; i++) {
            if(nombreDest.equals(names[i])){
                saldo = saldos[i];
                saldoAnt = saldo;
                find = true;
                posActualUser = i;
            }
        }
        
        if(!find){
            System.out.println("==== Cuenta no encontrada ====");
        } else {
            System.out.print("Ingrese la cantidad que desea transferir: ");
            float cant = entrada.nextFloat();
            
            if(saldoCuentaPrin == 0.0f) {
                try{
                    System.out.println("\n!!!! ERROR DE TRANSFERNCIA\nCUENTA CON SALDO: " + saldoCuentaPrin + "\n\n");
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                saldo = saldoCuentaPrin;
            } else {
                inMoney(cant);
            
                if(saldoAnt != saldo){
                    System.out.println("\n=====  TRANSFERENCIA REALIZADA EXITOSAMENTE  =====\n\n");
                    saldo = saldoCuentaPrin;
                } else {
                    System.out.println("\n!!!! NO SE PUDO REALIZAR LA TRANSFERENCIA !!!!\n\n");
                }
            }
            
        }
        
        
    }
    public float inMoney(float cantidad) {
        saldo += cantidad;
        saldos[posActualUser] = saldo;
        return saldo;
    }
    
    public void inCuenta(){
        Scanner entrada = new Scanner(System.in);
        System.out.println("=====  Crear usuario  =====");
        
        System.out.print("Ingrese su nombre: ");
        nombre = entrada.next();
        System.out.print("Ingrese su contraseña: ");
        password = entrada.next();
        
        authentifi();
    }
    
    public float outMoney(float cantidad ){
        saldo -= cantidad;
        saldos[posActualUser] = saldo;
        return saldo;
    }
    public void consultSaldo() {
        Scanner entrada = new Scanner(System.in);

        try {
            if(saldo < 0) {
                System.out.println("\n----  Su saldo presenta saldo negativo: $" + saldo + " ****\n");
            } else {
                System.out.println("\n****  Su saldo es: $" + saldo + " ****\n");           
            }
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
    }
    
    public float inDatos(int opcion) {
        Scanner entrada = new Scanner(System.in);
        float money;
        
        switch(opcion){
            case 3:
                System.out.print("\nDigite la cantidad que desea ingresar: ");
                money =  entrada.nextFloat();
                System.out.print("\n");
                inMoney(money);
                break;
            case 4:
                System.out.print("\nIngrese la cantidad que desea retirar: ");
                money =  entrada.nextFloat();
                System.out.print("\n");
                outMoney(money);
                break;
            default:
                JOptionPane.showMessageDialog(null, "Elija una opcion correcta", "Incorrect Election", JOptionPane.INFORMATION_MESSAGE);
                break;
        }
        
        return saldo;
    } 
    
   
    public static void main (String[] ar){
        Scanner entrada = new Scanner(System.in);
        Cajeroauto cuenta = new Cajeroauto();
        boolean opcion = true;

        //Metodo para ingresar datos, autentificar y crear usuario, en dado caso que no este agregado.
        cuenta.inCuenta();
        
        //Almacenamos los datos de los datos que se tienen de la clase en variable para despues ser usadas. 
        String nombre = cuenta.nombre;
        String password = cuenta.password;
        float saldo = cuenta.saldo;
        
        while(opcion == true){
            int m;

            System.out.println("=====  BANCO  =====");
            System.out.println("\nBienvenido: " + nombre + "\n");
            System.out.println("1. Crear nueva cuenta o Ingresar a cuenta existente");
            System.out.println("2. Transferir a cuenta");
            System.out.println("3. Ingresar dinero");
            System.out.println("4. Retirar dinero");
            System.out.println("5. Consultar saldo");
            System.out.println("6. Salir");
            System.out.print(">:");
            String menuElection = entrada.next();
            
            m = Integer.parseInt(menuElection);
            
            switch(m){
                case 1:
                    cuenta.inCuenta();     
                    nombre = cuenta.nombre;
                    saldo = cuenta.saldo;
                    break;
                case 2:
                    cuenta.transfer();
                    break;
                case 3:
                    saldo = cuenta.inDatos(m);
                    break;
                case 4:

                    saldo = cuenta.inDatos(m);
                    break;
                case 5:
                    cuenta.consultSaldo();
                    break;
                case 6:
                    opcion = false;
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Elija una opcion correcta", "Incorrect Election", JOptionPane.INFORMATION_MESSAGE);
                    break;
            }
        }
    }
}

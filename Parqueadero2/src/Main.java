/**
 * Parqueadero: Programa que simula un parqueadero
 * 
 * @author Alberto Bermudez y Nestor Durán
 * @version 1.0
 * @since 26/02/2016
 * 
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main 
{
	/*
	 * False -> hay cupo 
	 * True -> no hay cupo 
	 */

		public static final int cupos = 87; /* Numero de parqueaderos */
		
		public static int Tarifa=8000;   /* Precio de la tarifa por hora */
		
	    public static final int hora_inicio = 6;  /* Hora de apertura del parqueadero */

	    public static final int hora_cierre = 20;  /* Hora de cierre del parqueadero */
	    
	    public static int asignarCupo(boolean parkingSpots[])
	    
	    {
	    	int ans=-1;
	    	for (int i = 0; i < parkingSpots.length; i++) 
	    	{
	    		if (!parkingSpots[i])
	    		{
	    			ans=i;
	    		}
			}
	    	if(ans != -1) parkingSpots[ans]=true;
	    	return ans; /* Los parqueaderos disponibles se llenan con su respectivo número, los ocupados se llenan con un 0 */
	    }
	    public static int sacarCarro(int puesto, int horaSalida, String placas[], int horaEntrada[],boolean parkingSpots[])
	    {
	    	if (parkingSpots[puesto])
	    	{
	    		int entrada = horaEntrada[puesto-1];
		    	return (horaSalida-entrada)*Main.Tarifa;
	    	}
	    	return -1; /* Se calcula el costo a pagar de acuerdo a las horas que se estuvo en el parqueadero */
	    }
	    
	    public static void main (String args [])
	    {
	    	int mapa[] = new int [87];
	    		for(int i=0; i<87; i++)
	    		{
	    				    			
	    				mapa[i] = i;
	    			
	    		} /* Imprime gráficamente los parqueaderos, indicando los ocupados y los libres */
	    	boolean running=true;
	    	System.out.println("Bienvenido, elija la opción que desee: \n 1). Deseo entrar al parqueadero."
	    			+ " \n 2). Deseo salir del parqueadero) \n 3). Deseo saber cuánto dinero se recogió hoy."
	    			+ "\n 4). Deseo saber cuántos cupos libres quedan. + \n 5). Deseo ver una representación gráfica de los parqueaderos.");
	    	/* Menú de interacción con el usuario y el administrador */
	    	
	    	boolean parkingSpots[]=new boolean [Main.cupos];
	    	Arrays.fill(parkingSpots, false);
	    	
	    	int gananciasTotales=0;
	    	
	    	String placas[]= new String[Main.cupos];
	    	int horaEntrada[]= new int [Main.cupos];
	    	
	    	Scanner scan = new Scanner(System.in); 
	    	
	    	while (running)
	    	{
	    		
	    		int key=Integer.parseInt(scan.nextLine());
		    	
		    	switch (key)
		    	{
		    	case 1:
		    		//Asignar puesto
		    		int cupo = asignarCupo(parkingSpots);
		    		if (cupo ==-1)System.out.println("No hay cupo en el parqueadero");
		    		else
		    		{
		    			System.out.println("Ingrese la placa del carro");
		    			placas[cupo]= scan.nextLine();
		    			System.out.println("Ingrese hora de entrada");
		    			try 
		    			{
		    				horaEntrada[cupo]= Integer.parseInt(scan.nextLine());
		    				mapa[cupo]=-1;
		    				if (horaEntrada[cupo]<6 || horaEntrada[cupo] > 20)
		    				{
		    					System.out.println("Lo sentimos, el parqueadero está cerrado.");
		    					break;
		    				}
		    					
		    			}
		    			catch (Exception e)
		    			{
		    				System.out.println("No es una hora aceptada vuelva a ingresar una opción del menu");
		    				break;
		    			}
		    			/* Try y Catch se encargan de tratar con las excepciones para que el programa no se caiga si encuentra algo incongruente */
		    			
		    			System.out.printf("Su parqueadero es el numero %d \n",(cupo+1) );
		    		}
		    		break;
		    	case 2:
		    		//salida carro 
		    		System.out.println("Ingrese su puesto");
		    		int puesto = Integer.parseInt(scan.nextLine());
		    		System.out.println("Ingrese la hora de salida");
		    		int horaSalida=Integer.parseInt(scan.nextLine());
		    		
		    		parkingSpots[puesto-1]=false;
		    		int costoParqueadero=sacarCarro(puesto,horaSalida,placas, horaEntrada, parkingSpots);
		    		if(costoParqueadero!=-1)
		    		{
		    			gananciasTotales+= costoParqueadero;
			    		System.out.printf("Usted debe pagar %d \n",costoParqueadero);
			    		mapa[puesto]=puesto;
		    		}
		    		/* Se calcula cuánto debe pagarse y se desocupa el parqueadero asignado */
		    		else System.out.println("El puesto seleccionado esta vacio");
		    		break;
		    	case 3:
		    		// Ganancias en un día
		    		System.out.println(gananciasTotales);
		    		break; /* Muestra las ganancias en un día */
		    	case 4:
		    		// Cupos libres
		    		int cuposLibres=0;
		    		for (int i = 0; i < parkingSpots.length; i++) 
		    		{
						if(!parkingSpots[i])
						{
							cuposLibres+=1;
						}
					}
		    		System.out.printf("Quedan %d cupos libres", cuposLibres );
		    		break; /* Muestra la cantidad de puestos libres */
		    		
		    	case 5:
		    		//Gráfico del parqueadero
		    			for(int i=0; i<87; i++)
		    			{
		    				if(i%10==0)
		    					System.out.println();
		    				if (i <9)
		    					System.out.print(" " + "0"+(mapa[i]+1)+ " ");
		    				else
		    					System.out.print(" " + (mapa[i]+1)+ " ");
		    			}
		    			System.out.println();
		    			/* Muestra gráficamente el parqueadero con los puestos asignados y los puestos libres */
		    		break;
		    	default:
		    		running=false;
		    		System.out.println("Cerrando programa");
		    		break;
		    	
		    	}

	    	}
	    	
	    	scan.close();   	
	    }
}

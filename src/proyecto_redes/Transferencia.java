package proyecto_redes;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class Transferencia	 {
	private Socket socket;

	private Scanner mensaje;
	private BufferedReader lector;
	private BufferedWriter escritor;
	private String respuesta;
	
	public Transferencia(Socket pSocket)
	{
		try {
		
			socket=pSocket;
			mensaje = new Scanner(new InputStreamReader(System.in));
			lector=new BufferedReader(new InputStreamReader(socket.getInputStream()));
			escritor= new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			respuesta= lector.readLine();
			System.out.println(respuesta);
			autenticar();
		} catch (IOException e) {

			e.printStackTrace();
		}


	}
	public void autenticar()
	{
		try
		{

			System.out.println("Ingrese su usuario");
			enviar("USER " + mensaje.nextLine());
			respuesta= lector.readLine();

			System.out.println("Ingrese su contraseña");
			enviar("PASS " + mensaje.nextLine());
			respuesta= lector.readLine();

			if(respuesta.startsWith("230"))
			{
				System.out.println("Autenticación exitosa");
				opciones();
			}
			else
			{
				System.out.println("usuario y/o contraseña incorrecto");
				autenticar();
			}

		}
		catch(Exception e)
		{
			e.printStackTrace();
		}        
	}

	public void opciones() {

		System.out.println(" \n\n Ingrese 1. Para mandar un archivo \n Ingrese 2. Para descargar un archivo \n Ingrese 3. Para desconectarse");

		String opcion = mensaje.nextLine();
		if(opcion.equals("1"))
		{
			stor();
			opciones();
		}
		if(opcion.equals("2"))

		{
			retr();
			opciones();
		}
		if(opcion.equals("3"))
		{
			desconectar();
		}
	}
	public void enviar(String mensaje)
	{
		try
		{
			escritor.write(mensaje + "\r\n");
			escritor.flush();
		}
		catch(Exception e)
		{
			e.toString();
		}
	}

	public void retr()
	{
		try
		{

			enviar("PASV");
			respuesta=lector.readLine();
			System.out.println("Enviando en modo pasivo" +  respuesta);

			System.out.println("Ingrese la ruta del archivo que quiere descargar");
			String ruta=mensaje.nextLine();
			enviar("RETR "+ ruta);
			System.out.println("Esperando respuesta....");
			respuesta=lector.readLine();
			System.out.println(respuesta);
		}
		catch(Exception e )
		{
			e.printStackTrace();
		}

	}
	public void stor()
	{
		try
		{

			enviar("PASV");
			respuesta=lector.readLine();
			System.out.println("Enviando en modo pasivo" +  respuesta);

			System.out.println("Ingrese la ruta del archivo que quiere enviar");
			String ruta=mensaje.nextLine();
			enviar("STOR "+ ruta);
			System.out.println("Esperando respuesta....");
			respuesta=lector.readLine();
			System.out.println(respuesta);

		}
		catch(Exception e )
		{
			e.printStackTrace();
		}


	}
	public void desconectar()
	{
		try
		{
			System.out.println("Desconectando...");
			enviar("QUIT");
			System.out.println("Hasta luego.");
			socket.close();
			mensaje.close();
			lector.close();
			escritor.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			socket=null;

		}
	}



}



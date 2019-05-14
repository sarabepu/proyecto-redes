package proyecto_redes;
// FTP Client

import java.net.*;
import java.io.*;
import java.util.*;


public class FTPClient
{
	private static DataInputStream entrada;
	private static DataOutputStream salida;
	private static Socket socket;
    public static void main(String args[]) throws Exception
    {
        socket=new Socket("192.168.0.14",21);
        
       Transferencia ftp =new Transferencia(socket);
        
        
    }
}
package src.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.Vector;

public class Listener extends Thread {
   ServerSocket ss;
   public static Vector vaux = new Vector();
   private static ArrayList listaSockets;
   static ServidorMIDI abSat;
   UIAdministrador ad;
   private static Listener list = null;

   public Listener() {
      try {
         this.ad = new UIAdministrador();
         this.ad.show();
         this.ss = new ServerSocket(9999);
         listaSockets = new ArrayList();
      } catch (IOException var2) {
         System.err.println(var2);
      }

   }

   public void run() {
      while(true) {
         try {
            SocketManager sm = new SocketManager(this.ss.accept());
            abSat = new ServidorMIDI(sm);
            abSat.actualizar(this.ad);
            listaSockets.add(sm);
            abSat.enviarListaConexiones(listaSockets);
            sm.Escribir("Conectado a Servidor\r\n");
            abSat.start();
         } catch (IOException var2) {
            System.err.println(var2);
         }
      }
   }

   public static void desconectar() {
      System.out.println("entrrar" + listaSockets.size());
      int num = listaSockets.size();

      for(int i = 0; i < num; ++i) {
         System.out.println("entra");
         abSat.stop();

         try {
            ((SocketManager)listaSockets.get(i)).Escribir("DESCONECTAR ");
            ((SocketManager)listaSockets.get(i)).CerrarSocket();
            listaSockets.remove(i);
         } catch (IOException var3) {
            var3.printStackTrace();
         }
      }

      System.out.println("aqui n llega");
   }
}

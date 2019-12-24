package src.server;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class ServidorMIDI extends Thread {
   float CantidadCompra;
   String CodAb;
   Socket so;
   ArrayList lista;
   ArrayList listaSockets;
   SocketManager sm;
   String usuarioCurso;
   int estado;
   private UIAdministrador ad;

   public ServidorMIDI(SocketManager s) {
      try {
         this.listaSockets = new ArrayList();
         this.sm = s;
         this.sm.InicializaStreams();
         this.estado = 0;
         this.lista = new ArrayList();
      } catch (IOException var3) {
         System.err.println(var3);
      }

   }

   public void desconectar() {
      try {
         this.so.close();
         this.estado = 2;
      } catch (IOException var2) {
         System.err.println(var2);
      }

   }

   public void run() {
      while(this.estado != 4) {
         try {
            String linea = this.sm.Leer();
            if (linea == null) {
               return;
            }

            System.out.println("Recibido desde: " + this.sm.mySocket.getInetAddress().getHostAddress() + " --> " + linea);
            StringTokenizer sTok = new StringTokenizer(linea, " ");
            String comando = sTok.nextToken();
            comando = comando.toUpperCase();
            System.out.println("estado" + this.estado + comando);
            switch(this.estado) {
            case 0:
               if (comando.equals("REGISTRAR")) {
                  String enviar = "";
                  comando = sTok.nextToken();
                  System.out.println("asdf" + comando);
                  this.lista.add(comando);
                  Listener.vaux.add(comando);

                  int i;
                  for(i = 0; i < Listener.vaux.size(); ++i) {
                     enviar = enviar + Listener.vaux.get(i) + " ";
                  }

                  System.out.println("enviar" + enviar);
                  System.out.println("tamaño lista" + this.listaSockets.size());

                  for(i = 0; i < this.listaSockets.size(); ++i) {
                     ((SocketManager)this.listaSockets.get(i)).Escribir("PROPALTA " + enviar + "\r\n");
                  }

                  this.ad.jList1.setListData(Listener.vaux);
                  System.out.println("pasa");
               }

               if (comando.equals("SALIR")) {
                  this.estado = 4;
                  System.out.println("eeeeentra");
                  comando = sTok.nextToken();
                  System.out.println("asdf" + comando);
                  Listener.vaux.removeElement(comando);
                  this.listaSockets.remove(this.sm);
                  this.sm.CerrarSocket();
                  System.out.println("tamaño lista" + this.listaSockets.size());

                  for(int i = 0; i < this.listaSockets.size(); ++i) {
                     ((SocketManager)this.listaSockets.get(i)).Escribir("PROPBAJA " + comando + "\r\n");
                  }

                  this.ad.jList1.setListData(Listener.vaux);
               }

               System.out.println("estado" + this.estado + comando);
            case 1:
            }
         } catch (IOException var6) {
            System.err.println(var6);
         }
      }

   }

   public void enviarListaConexiones(ArrayList listaSockets2) {
      this.listaSockets = listaSockets2;
   }

   public void actualizar(UIAdministrador ad) {
      this.ad = ad;
   }
}

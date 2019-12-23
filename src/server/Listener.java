/*    */ package src.server;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.net.ServerSocket;
/*    */ import java.util.ArrayList;
/*    */ import java.util.Vector;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Listener
/*    */   extends Thread
/*    */ {
/*    */   ServerSocket ss;
/* 16 */   public static Vector<Object> vaux = new Vector();
/*    */   
/*    */   private static ArrayList<SocketManager> listaSockets;
/*    */   
/*    */   static ServidorMIDI abSat;
/*    */   UIAdministrador ad;
/* 22 */   private static Listener list = null;
/*    */   
/*    */   public Listener() {
/*    */     try {
/* 26 */       this.ad = new UIAdministrador();
/* 27 */       this.ad.show();
/* 28 */       this.ss = new ServerSocket(9999);
/* 29 */       listaSockets = new ArrayList<SocketManager>();
/*    */     } catch (IOException ioe) {
/* 31 */       System.err.println(ioe);
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void run() {
/*    */     while (true) {
/*    */       try {
/*    */         while (true) {
/* 42 */           SocketManager sm = new SocketManager(this.ss.accept());
/*    */           
/* 44 */           abSat = new ServidorMIDI(sm);
/* 45 */           abSat.actualizar(this.ad);
/* 46 */           listaSockets.add(sm);
/* 47 */           abSat.enviarListaConexiones(listaSockets);
/* 48 */           sm.Escribir("Conectado a Servidor\r\n");
/* 49 */           abSat.start();
/*    */         } 
/*    */         break;
/* 52 */       } catch (IOException ioe) {
/* 53 */         System.err.println(ioe);
/*    */       } 
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public static void desconectar() {
/* 60 */     System.out.println("entrrar" + listaSockets.size());
/* 61 */     int num = listaSockets.size();
/* 62 */     for (int i = 0; i < num; i++) {
/* 63 */       System.out.println("entra");
/*    */       
/* 65 */       abSat.stop();
/*    */       
/*    */       try {
/* 68 */         ((SocketManager)listaSockets.get(i)).Escribir("DESCONECTAR ");
/*    */         
/* 70 */         ((SocketManager)listaSockets.get(i)).CerrarSocket();
/* 71 */         listaSockets.remove(i);
/* 72 */       } catch (IOException e) {
/*    */         
/* 74 */         e.printStackTrace();
/*    */       } 
/*    */     } 
/* 77 */     System.out.println("aqui n llega");
/*    */   }
/*    */ }


/* Location:              /home/gus/Desktop/Documentos/documetos/EjecutarServer.jar!/src/server/Listener.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.2
 */
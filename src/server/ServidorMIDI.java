/*     */ package src.server;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.net.Socket;
/*     */ import java.util.ArrayList;
/*     */ import java.util.StringTokenizer;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ServidorMIDI
/*     */   extends Thread
/*     */ {
/*     */   float CantidadCompra;
/*     */   String CodAb;
/*     */   Socket so;
/*     */   ArrayList<String> lista;
/*     */   ArrayList<SocketManager> listaSockets;
/*     */   SocketManager sm;
/*     */   String usuarioCurso;
/*     */   int estado;
/*     */   private UIAdministrador ad;
/*     */   
/*     */   public ServidorMIDI(SocketManager s) {
/*     */     try {
/*  34 */       this.listaSockets = new ArrayList<SocketManager>();
/*  35 */       this.sm = s;
/*  36 */       this.sm.InicializaStreams();
/*  37 */       this.estado = 0;
/*  38 */       this.lista = new ArrayList<String>();
/*  39 */     } catch (IOException ioe) {
/*     */       
/*  41 */       System.err.println(ioe);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void desconectar() {
/*     */     try {
/*  48 */       this.so.close();
/*  49 */       this.estado = 2;
/*  50 */     } catch (IOException ioe) {
/*     */       
/*  52 */       System.err.println(ioe);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void run() {
/*  58 */     while (this.estado != 4) {
/*     */       try {
/*  60 */         String linea = this.sm.Leer();
/*  61 */         if (linea == null)
/*  62 */           return;  System.out.println("Recibido desde: " + this.sm.mySocket.getInetAddress().getHostAddress() + " --> " + linea);
/*  63 */         StringTokenizer sTok = new StringTokenizer(linea, " ");
/*  64 */         String comando = sTok.nextToken();
/*  65 */         comando = comando.toUpperCase();
/*  66 */         System.out.println("estado" + this.estado + comando);
/*  67 */         switch (this.estado) {
/*     */           case 0:
/*  69 */             if (comando.equals("REGISTRAR")) {
/*  70 */               String enviar = "";
/*  71 */               comando = sTok.nextToken();
/*  72 */               System.out.println("asdf" + comando);
/*  73 */               this.lista.add(comando);
/*  74 */               Listener.vaux.add(comando);
/*     */               
/*  76 */               for (int i = 0; i < Listener.vaux.size(); i++)
/*  77 */                 enviar = String.valueOf(enviar) + Listener.vaux.get(i) + " "; 
/*  78 */               System.out.println("enviar" + enviar);
/*  79 */               System.out.println("tamaño lista" + this.listaSockets.size());
/*     */               
/*  81 */               for (int i = 0; i < this.listaSockets.size(); i++)
/*     */               {
/*  83 */                 ((SocketManager)this.listaSockets.get(i)).Escribir("PROPALTA " + enviar + "\r\n");
/*     */               }
/*  85 */               this.ad.jList1.setListData(Listener.vaux);
/*     */               
/*  87 */               System.out.println("pasa");
/*     */             } 
/*     */             
/*  90 */             if (comando.equals("SALIR")) {
/*  91 */               this.estado = 4;
/*  92 */               System.out.println("eeeeentra");
/*  93 */               comando = sTok.nextToken();
/*  94 */               System.out.println("asdf" + comando);
/*  95 */               Listener.vaux.removeElement(comando);
/*     */ 
/*     */               
/*  98 */               this.listaSockets.remove(this.sm);
/*  99 */               this.sm.CerrarSocket();
/*     */ 
/*     */               
/* 102 */               System.out.println("tamaño lista" + this.listaSockets.size());
/*     */               
/* 104 */               for (int i = 0; i < this.listaSockets.size(); i++)
/*     */               {
/* 106 */                 ((SocketManager)this.listaSockets.get(i)).Escribir("PROPBAJA " + comando + "\r\n");
/*     */               }
/* 108 */               this.ad.jList1.setListData(Listener.vaux);
/*     */             } 
/*     */             
/* 111 */             System.out.println("estado" + this.estado + comando);
/*     */         } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 121 */       } catch (IOException ioe) {
/* 122 */         System.err.println(ioe);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 130 */   public void enviarListaConexiones(ArrayList<SocketManager> listaSockets2) { this.listaSockets = listaSockets2; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 135 */   public void actualizar(UIAdministrador ad) { this.ad = ad; }
/*     */ }


/* Location:              /home/gus/Desktop/Documentos/documetos/EjecutarServer.jar!/src/server/ServidorMIDI.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.2
 */
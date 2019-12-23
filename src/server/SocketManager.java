/*    */ package src.server;
/*    */ import java.io.BufferedReader;
/*    */ import java.io.DataOutputStream;
/*    */ import java.io.IOException;
/*    */ import java.io.InputStreamReader;
/*    */ import java.net.InetAddress;
/*    */ import java.net.Socket;
/*    */ 
/*    */ public class SocketManager {
/*    */   public Socket mySocket;
/*    */   
/*    */   public SocketManager(Socket sock) throws IOException {
/* 13 */     this.mySocket = sock;
/* 14 */     InicializaStreams();
/*    */   }
/*    */   private DataOutputStream bufferEscritura; private BufferedReader bufferLectura;
/*    */   
/*    */   public SocketManager(InetAddress address, int port) throws IOException {
/* 19 */     this.mySocket = new Socket(address, port);
/* 20 */     InicializaStreams();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public SocketManager(String host, int port) throws IOException {
/* 30 */     this.mySocket = new Socket(host, port);
/* 31 */     InicializaStreams();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void InicializaStreams() throws IOException {
/* 39 */     this.bufferEscritura = new DataOutputStream(this.mySocket.getOutputStream());
/* 40 */     this.bufferLectura = new BufferedReader(new InputStreamReader(
/* 41 */           this.mySocket.getInputStream()));
/*    */   }
/*    */   
/*    */   public void CerrarStreams() throws IOException {
/* 45 */     this.bufferEscritura.close();
/* 46 */     this.bufferLectura.close();
/*    */   }
/*    */ 
/*    */   
/* 50 */   public void CerrarSocket() throws IOException { this.mySocket.close(); }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 59 */   public String Leer() throws IOException { return this.bufferLectura.readLine(); }
/*    */ 
/*    */ 
/*    */   
/* 63 */   public void Escribir(String contenido) throws IOException { this.bufferEscritura.writeBytes(contenido); }
/*    */ 
/*    */ 
/*    */   
/* 67 */   public void Escribir(byte[] buffer, int bytes) throws IOException { this.bufferEscritura.write(buffer, 0, bytes); }
/*    */ }


/* Location:              /home/gus/Desktop/Documentos/documetos/EjecutarServer.jar!/src/server/SocketManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.2
 */
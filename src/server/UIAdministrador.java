/*     */ package src.server;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.awt.Font;
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.awt.event.WindowAdapter;
/*     */ import java.awt.event.WindowEvent;
/*     */ import java.util.Vector;
/*     */ import javax.swing.JFrame;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JList;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.border.Border;
/*     */ import javax.swing.border.EtchedBorder;
/*     */ import javax.swing.border.TitledBorder;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class UIAdministrador
/*     */   extends JFrame
/*     */   implements ActionListener
/*     */ {
/*  31 */   JList jList1 = new JList();
/*     */   
/*  33 */   JLabel jLabel1 = new JLabel();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  38 */   JPanel jPanel1 = new JPanel();
/*     */   
/*     */   Border border1;
/*     */   
/*     */   TitledBorder titledBorder1;
/*     */   Vector v;
/*     */   Vector vaux1;
/*     */   private ServidorMIDI abSat;
/*     */   
/*     */   public UIAdministrador() {
/*  48 */     this.v = new Vector();
/*  49 */     this.vaux1 = new Vector();
/*  50 */     this.v = Listener.vaux;
/*     */     
/*  52 */     for (int i = 0; i < this.v.size(); i++) {
/*     */       
/*  54 */       String f = this.v.get(i);
/*  55 */       this.vaux1.add(f);
/*  56 */       System.out.println(f);
/*     */     } 
/*     */     
/*  59 */     this.jList1 = new JList(this.vaux1);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  67 */     this.border1 = new EtchedBorder(0, Color.white, new Color(148, 145, 140));
/*  68 */     this.titledBorder1 = new TitledBorder(this.border1, "Servidor");
/*  69 */     setTitle("Servicios Administrador");
/*  70 */     getContentPane().setLayout(null);
/*  71 */     this.jList1.setBounds(new Rectangle(39, 69, 204, 211));
/*  72 */     this.jLabel1.setFont(new Font("Dialog", 1, 12));
/*  73 */     this.jLabel1.setText("Clientes Conectados");
/*  74 */     this.jLabel1.setBounds(new Rectangle(53, 36, 156, 17));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  81 */     this.jPanel1.setBorder(this.titledBorder1);
/*  82 */     this.jPanel1.setBounds(21, 7, 329, 364);
/*  83 */     getContentPane().add(this.jLabel1, null);
/*     */ 
/*     */     
/*  86 */     getContentPane().add(this.jList1, null);
/*     */     
/*  88 */     getContentPane().add(this.jPanel1, null);
/*  89 */     setBounds(0, 0, 405, 450);
/*  90 */     setVisible(true);
/*  91 */     addWindowListener(new WindowAdapter() {
/*     */           public void windowClosing(WindowEvent e) {
/*  93 */             Listener.desconectar();
/*  94 */             System.exit(0);
/*     */           }
/*     */         });
/*     */   }
/*     */   
/*     */   public void actionPerformed(ActionEvent e) {
/* 100 */     String arg = e.getActionCommand();
/* 101 */     arg.equals("Desconectar");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 109 */     if (arg.equals("Actualizar"))
/*     */     {
/* 111 */       this.jList1.setListData(Listener.vaux);
/*     */     }
/*     */   }
/*     */ }


/* Location:              /home/gus/Desktop/Documentos/documetos/EjecutarServer.jar!/src/server/UIAdministrador.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.2
 */
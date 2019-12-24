package src.server;

import java.awt.Color;
import java.awt.Font;
import java.awt.LayoutManager;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Vector;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class UIAdministrador extends JFrame implements ActionListener {
   JList jList1 = new JList();
   JLabel jLabel1 = new JLabel();
   JPanel jPanel1 = new JPanel();
   Border border1;
   TitledBorder titledBorder1;
   Vector v = new Vector();
   Vector vaux1 = new Vector();
   private ServidorMIDI abSat;

   public UIAdministrador() {
      this.v = Listener.vaux;

      for(int i = 0; i < this.v.size(); ++i) {
         String f = (String)this.v.get(i);
         this.vaux1.add(f);
         System.out.println(f);
      }

      this.jList1 = new JList(this.vaux1);
      this.border1 = new EtchedBorder(0, Color.white, new Color(148, 145, 140));
      this.titledBorder1 = new TitledBorder(this.border1, "Servidor");
      this.setTitle("Servicios Administrador");
      this.getContentPane().setLayout((LayoutManager)null);
      this.jList1.setBounds(new Rectangle(39, 69, 204, 211));
      this.jLabel1.setFont(new Font("Dialog", 1, 12));
      this.jLabel1.setText("Clientes Conectados");
      this.jLabel1.setBounds(new Rectangle(53, 36, 156, 17));
      this.jPanel1.setBorder(this.titledBorder1);
      this.jPanel1.setBounds(21, 7, 329, 364);
      this.getContentPane().add(this.jLabel1, (Object)null);
      this.getContentPane().add(this.jList1, (Object)null);
      this.getContentPane().add(this.jPanel1, (Object)null);
      this.setBounds(0, 0, 405, 450);
      this.setVisible(true);
      this.addWindowListener(new WindowAdapter() {
         public void windowClosing(WindowEvent e) {
            Listener.desconectar();
            System.exit(0);
         }
      });
   }

   public void actionPerformed(ActionEvent e) {
      String arg = e.getActionCommand();
      arg.equals("Desconectar");
      if (arg.equals("Actualizar")) {
         this.jList1.setListData(Listener.vaux);
      }

   }
}

package interfaz;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.omg.CORBA.ORB;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;

import Logic.Reproductor;
import SendFileServer.SendFile;
import SendFileServer.SendFileHelper;
import SqlMusica.Cancion;

import javax.swing.SpringLayout;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.SourceDataLine;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;

import java.awt.FlowLayout;
import java.awt.CardLayout;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.GridBagLayout;
import java.awt.Button;

public class InterfazReproductor extends JFrame {
	static SendFile sendFileImpl;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try{
	        // create and initialize the ORB
	        ORB orb = ORB.init(args, null);

	        // get the root naming context
	        org.omg.CORBA.Object objRef = 
	            orb.resolve_initial_references("NameService");
	        // Use NamingContextExt instead of NamingContext. This is 
	        // part of the Interoperable naming Service.  
	        NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);
	 
	        // resolve the Object Reference in Naming
	        String name = "SendFile";
	        sendFileImpl = SendFileHelper.narrow(ncRef.resolve_str(name));
	        
	        EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						InterfazReproductor frame = new InterfazReproductor();
						frame.setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});

	        } catch (Exception e) {
	          System.out.println("ERROR : " + e) ;
	          e.printStackTrace(System.out);
	        }
	}

	/**
	 * Create the frame.
	 */
	public InterfazReproductor() {
		setResizable(false);
		setTitle("Reproductor");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 645, 319);
		getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.X_AXIS));
		
		PanelPrincipal panelPrincipal = new PanelPrincipal(sendFileImpl);
		panelPrincipal.setBounds(179, 11, 450, 134);
		
		PlayList playList = new PlayList();
		DefaultListModel<Cancion> listModel = new DefaultListModel<Cancion>();
		try {
			List<Cancion> canciones = Cancion.getAll();
			panelPrincipal.setCanciones(canciones);
			for (Cancion cancion : canciones) {
				listModel.addElement(cancion);
			}
			playList.list.setModel(listModel);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		getContentPane().add(playList);
		
		playList.add(panelPrincipal);
		panelPrincipal.playList = playList;
		
		
		playList.list.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				JList list = (JList)evt.getSource();
				if (evt.getClickCount() == 2) {
					if (panelPrincipal.rep != null && !panelPrincipal.rep.getComplete()) {
						panelPrincipal.rep.stop();
						panelPrincipal.rep = null;
					}
					Cancion cancion = (Cancion) playList.list.getModel().getElementAt(playList.list.getSelectedIndex());
					panelPrincipal.setCancion(cancion);
					panelPrincipal.index = playList.list.getSelectedIndex();
					panelPrincipal.lb_Title.setText(cancion.getTitulo());
					panelPrincipal.lb_Duration.setText(cancion.getDuracion());
					panelPrincipal.play();
				}
			}
		});
	}
}

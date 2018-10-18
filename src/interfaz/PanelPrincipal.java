package interfaz;

import javax.swing.JPanel;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JSlider;
import javax.swing.SpringLayout;

import Logic.Reproductor;
import SendFileServer.SendFile;
import SqlMusica.Cancion;

import javax.swing.JLabel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class PanelPrincipal extends JPanel {

	private List<Cancion> canciones;
	private Cancion cancion;
	public int index = 0;
	public JLabel lb_Title;
	public JLabel lb_Duration;
	public JSlider slider;
	public PlayList playList;
	
	public Reproductor rep;
	private PanelPrincipal me = this;
	
	private SendFile sendFileImpl;
	
	public Cancion getCancion() {
		return cancion;
	}

	public void setCancion(Cancion cancion) {
		this.cancion = cancion;
	}
	
	public List<Cancion> getCanciones() {
		return canciones;
	}

	public void setCanciones(List<Cancion> canciones) {
		this.canciones = canciones;
	}
	
	public void play() {
		if (rep != null) {
			if (rep.getComplete()) {
				rep = new Reproductor(me, cancion.getId(), this.sendFileImpl);
			}
		} else {
			rep = new Reproductor(me, cancion.getId(), this.sendFileImpl);
		}
		playList.list.setSelectedIndex(index);
		rep.play();
	}
	/**
	 * Create the panel.
	 */
	public PanelPrincipal(SendFile sendFileImpl) {
		this.sendFileImpl = sendFileImpl;
		JButton btn_Next = new JButton(">>");
		btn_Next.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (cancion != null) {
					if (index + 1 > canciones.size()-1) {
						index = 0;
					} else {
						index++;
					}
					cancion = canciones.get(index);
					rep.stop();
					rep = null;
					play();
				}
			}
		});
		
		JButton btn_Pause = new JButton("Pause");
		btn_Pause.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (rep != null) {
					rep.pause();
				}
			}
		});
		
		JButton btn_Play = new JButton("Play");
		btn_Play.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				play();
			}
		});
		
		JButton btn_Back = new JButton("<<");
		btn_Back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (cancion != null) {
					if (index - 1 < 0) {
						index = canciones.size()-1;
					} else {
						index--;
					}
					cancion = canciones.get(index);
					rep.stop();
					rep = null;
					play();
				}
			}
		});
		
		lb_Duration = new JLabel("00:00:00");
		
		JLabel label_1 = new JLabel("Duration:");
		
		slider = new JSlider();
		slider.setValue(0);
		
		this.lb_Title = new JLabel("Title song");
		lb_Title.setHorizontalAlignment(SwingConstants.CENTER);
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(label_1, GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(52)
							.addComponent(btn_Back, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
							.addGap(6)
							.addComponent(btn_Play, GroupLayout.DEFAULT_SIZE, 56, Short.MAX_VALUE)
							.addGap(6)
							.addComponent(btn_Pause, GroupLayout.DEFAULT_SIZE, 63, Short.MAX_VALUE)
							.addGap(6)
							.addComponent(btn_Next, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
							.addGap(113))
						.addGroup(groupLayout.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lb_Duration, GroupLayout.DEFAULT_SIZE, 384, Short.MAX_VALUE)
							.addContainerGap())))
				.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
					.addGap(37)
					.addComponent(lb_Title, GroupLayout.PREFERRED_SIZE, 385, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(38, Short.MAX_VALUE))
				.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(slider, GroupLayout.PREFERRED_SIZE, 432, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(18, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addGap(20)
					.addComponent(lb_Title, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(slider, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_1)
						.addComponent(lb_Duration))
					.addGap(8)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(btn_Back)
						.addComponent(btn_Play)
						.addComponent(btn_Pause)
						.addComponent(btn_Next))
					.addGap(17))
		);
		setLayout(groupLayout);

	}
}

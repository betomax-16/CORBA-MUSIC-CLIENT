package Logic;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import interfaz.PanelPrincipal;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

public class Reproductor {
	
	private PanelPrincipal gui;
	private Thread hilo;
	private Thread hiloGui;
	private String path;
	private int suspend = 1;
	private boolean complete = false;
	private Reproductor me = this;
	
	public Reproductor(PanelPrincipal gui, String path) {
		this.gui = gui;
		this.path = path;
		this.hilo = init();
	}
	
	private Thread init() {
		return new Thread() {
			@Override
			public void run() {
				Player apl;
				try {
					apl = new Player(new FileInputStream(path));
					startTimeLine(gui.getCancion().getDuracion());
					apl.play();
					if (complete = apl.isComplete()) {
						System.out.println("The song is over");
						apl.close();
						suspend = 2;
						gui.lb_Duration.setText(gui.getCancion().getDuracion());
						gui.slider.setValue(0);
						apl = null;
					}
				} catch (FileNotFoundException | JavaLayerException e) {
					e.printStackTrace();
				}
			}
		};
	}
	
	public boolean getComplete() {
		return complete;
	}
	
	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
	
	public void play() {
		if (!complete) {
			if (this.suspend == 1) {
				this.hilo.start();
			} else if (this.suspend >= 2) {
				this.hilo.resume();
				this.hiloGui.resume();
			}
			this.suspend = 3;
		} 
	}
	
	public void pause() {
		this.hilo.suspend();
		this.hiloGui.suspend();
		this.suspend = 2;
	}
	
	public void stop() {
		this.hilo.stop();
		this.hiloGui.stop();
		gui.lb_Duration.setText(gui.getCancion().getDuracion());
		gui.slider.setValue(0);
	}
	
	private void startTimeLine(String t) {
		hiloGui = new Thread() {
			@Override
			public void run() {
				int hrs = Integer.parseInt(t.split(":")[0]);
				int min = Integer.parseInt(t.split(":")[1]);
				int seg = Integer.parseInt(t.split(":")[2]);
				int time = hrs*3600 + min*60 + seg;
				int current = time;
				
				while (suspend != 2) {
					gui.slider.setValue(100 - (int)((current*100)/time));
					int h = current / 3600;
					int m = (current - (h*3600)) / 60;
					int s = (current - (h*3600)) - m * 60;
					String dur = h < 10 ? "0"+h+":" : h+":";
					dur = m < 10 ? dur + "0"+m+":" : dur + m+":";
					dur = s < 10 ? dur + "0"+s : dur + s;
					gui.lb_Duration.setText(dur);
					current = current - 1;
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			} 
		};
		
		hiloGui.start();
	}
}

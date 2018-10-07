package interfaz;

import javax.swing.JPanel;
import javax.swing.ListModel;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;

import net.miginfocom.swing.MigLayout;
import javax.swing.SpringLayout;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JList;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.sql.SQLException;
import java.util.List;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import javax.swing.JEditorPane;
import javax.swing.AbstractListModel;

import SqlMusica.Cancion;

public class PlayList extends JPanel {

	/**
	 * Create the panel.
	 */
	public JList list;
	
	public PlayList() {
		setLayout(null);
		this.list = new JList();
		list.setModel(new AbstractListModel() {
			String[] values = new String[] {"hola"};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		
		this.list.setBounds(10, 11, 153, 265);
		
		add(this.list);
	}
}

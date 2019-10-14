import java.util.ArrayList;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/*Based on the promt of MVC problem, I decide to use a String and keep append more line to it*/
public class Model {
	private ArrayList<String> listText;
	private ArrayList<ChangeListener> listeners;
	
	public Model() {//constructor inisuazlize 2 arraylist
		listText= new ArrayList<String>();
		listeners = new ArrayList<ChangeListener>();
	}
	
	public void addChangeListener(ChangeListener listen) {
		listeners.add(listen);
	}
	
	public void addText(String x) {
		listText.add(x);
		
		ChangeEvent e = new ChangeEvent(this);
		for(int i=0;i<listeners.size();i++) {
			listeners.get(i).stateChanged(e);
		}
	}
	
	public String printLine() {
		String text = "";
		for(String aText: listText) {
			text += aText +"\n"; //\n will help to display line 1 line below 
		}
		return text;
	}
}

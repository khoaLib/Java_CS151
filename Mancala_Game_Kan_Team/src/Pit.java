/*
 * Block is the ellipse on the Mancala game Board which contain 4 stones at the beginning
 * The number of stones can be modified
 * */
public class Pit {
	private int stones;
	private boolean gotChosen;
	
	public Pit() {

	}
	
	public Pit(int num) {
		stones = num;
		gotChosen = true; // now the block has a number of stones designed by user
	}
	
	public boolean isEmpty() {
		if(stones ==0) {
			return true;
		}else {
			return false;
		}
	}
	
	public boolean gotPicked() {
		if(gotChosen == true) {
			return true;
		}else {
			return false;
		}
	}
	
	public void setChosen(boolean decision) {
		gotChosen = decision;
	}
	

}

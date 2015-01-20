package dk.itu.eyedroid.io.protocols;

import java.io.IOException;

import dk.itu.spcl.jlpf.io.IOProtocolWriter;
/**
 * Network protocol writter abstraction
 */
public abstract class OutputNetProtocol implements IOProtocolWriter{

	protected final OutputNetProtocolController  mController; 	//Message controlle
	public Object lock;											// Lock used to provide atomic access to sampled coordinates
	public int X;												// Sampled X coordinate
	public int Y;												// Sampled Y coordinate
	
	/**
	 * Deafult constructor
	 * @param controller Communication controller
	 */
	public OutputNetProtocol(OutputNetProtocolController controller) {
		mController = controller;
		lock = new Object();
	}
	
	/**
	 * Get X and Y sampeld values
	 * @return X and Y coordinates
	 */
	public int[] getXY(){
		synchronized(lock){
			return new int[]{X,Y};
		}
	}
	
	/**
	 * Set X and Y sampeld values
	 * @param x X-coordinate
	 * @param y	Y-coordinate
	 */
	protected void setXY(int x, int y){
		synchronized(lock){
			X = x;
			Y = y;
		}
	}
	
	/**
	 * Send coordinates to client along with HMGT or RGT format message.
	 * No mapping is done in this method.
	 * @param message Message type
	 * @param x X-coordinate
	 * @param y Y-coordinate
	 * @throws IOException 
	 */
	protected abstract void sendCoordinates(int message, int x, int y) throws IOException;
}
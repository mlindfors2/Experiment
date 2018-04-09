//
//package bort;
//
//import java.io.File;
//import java.io.IOException;
//import java.net.URL;
//import javax.sound.sampled.AudioFormat;
//import javax.sound.sampled.AudioInputStream;
//import javax.sound.sampled.AudioSystem;
//import javax.sound.sampled.LineUnavailableException;
//import javax.sound.sampled.SourceDataLine;
//import javax.sound.sampled.UnsupportedAudioFileException;
//
//public class PlayerThread implements Runnable {
//	private SourceDataLine line;
//	private AudioInputStream currentDecoded;
//	private AudioInputStream encoded;
//	private AudioFormat encodedFormat;
//	private AudioFormat decodedFormat;
//	private boolean started = false;
//	private boolean stopped = false;
//	private boolean paused = false;
//	private final int BUFFERSIZE = 4096;
//
//	/**
//	 * Constructs a Sound object specified by the url argument
//	 * 
//	 * @param url
//	 *            giving the location of the sound file
//	 */
//	public PlayerThread(URL url) throws IOException, UnsupportedAudioFileException, LineUnavailableException {
//		encoded = AudioSystem.getAudioInputStream(url);
//		encodedFormat = encoded.getFormat();
//		decodedFormat = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, // Encoding to use
//				encodedFormat.getSampleRate(), // sample rate (same as base format)
//				16, // sample size in bits (thx to Javazoom)
//				encodedFormat.getChannels(), // # of Channels
//				encodedFormat.getChannels() * 2, // Frame Size
//				encodedFormat.getSampleRate(), // Frame Rate
//				false // Big Endian
//		);
//		currentDecoded = AudioSystem.getAudioInputStream(decodedFormat, encoded);
//		line = AudioSystem.getSourceDataLine(decodedFormat);
//		line.open(decodedFormat);
//		setPaused(true);
//		setStopped(false);
//	}
//
//	/**
//	 * Begins to play the sound / resumes playback of a sound that is paused. A
//	 * sound can only be started once.
//	 */
//	public synchronized void play() {
//		if (!started) {
//			new Thread(this).start();
//			started = true;
//		}
//		setPaused(false);
//		notify();
//	}
//
//	/**
//	 * Stops playback of the sound.
//	 */
//	public synchronized void stop() {
//		setStopped(true);
//		setPaused(false);
//		notify();
//	}
//
//	/**
//	 * Pauses playback of the sound.
//	 */
//	public synchronized void pause() {
//		setPaused(true);
//	}
//
//	private boolean getStopped() {
//		return stopped;
//	}
//
//	private void setStopped(boolean stopped) {
//		this.stopped = stopped;
//	}
//
//	private boolean getPaused() {
//		return paused;
//	}
//
//	private void setPaused(boolean paused) {
//		this.paused = paused;
//	}
//
//	public void run() {
//		line.start();
//		byte[] b = new byte[BUFFERSIZE];
//		int i = 0;
//		setStopped(false);
//		setPaused(false);
//		while (!getStopped()) {
//			while (getPaused()) {
//				synchronized (this) {
//					try {
//						this.wait();
//					} catch (InterruptedException e) {
//						setStopped(true);
//						setPaused(false);
//					}
//				}
//			}
//			try {
//				i = currentDecoded.read(b, 0, b.length);
//				if (i == -1) {
//					setStopped(true);
//				} else
//					line.write(b, 0, i);
//			} catch (IOException e) {
//				System.err.println(e);
//			}
//		}
//		line.drain();
//		line.stop();
//		line.close();
//		try {
//			currentDecoded.close();
//			encoded.close();
//		} catch (IOException e) {
//			System.err.println(e);
//		}
//		System.out.println("Sound ended");
//	}
//
//}*************************************************************main*******************************************************************
//
//	/**
//	 * starts the PlayerThread if not started
//	 */
//	public void start(File fileArg) {
//		File file = fileArg;
//		try {
//			URL url = file.toURI().toURL();
//			thread = new PlayerThread(url);
//			thread.play();
//		} catch (Exception e) {
//			System.out.println(e);
//		}
//	}
//
//	/**
//	 * stops the thread if started
//	 */
//	public void stop() {
//		if (thread != null) {
//			thread.stop();
//		}
//}***********************************
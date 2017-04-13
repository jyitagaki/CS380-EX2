//Joshua Itagaki
//CS 380

import java.io.InputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.zip.CRC32;
import java.util.zip.Checksum;

public class Ex2Client {
	public static void main(String[] args) throws Exception {
		byte[] ready = new byte[200];
		try (Socket socket = new Socket("codebank.xyz", 38102)){
			PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
			InputStream is = socket.getInputStream();
			is.read(ready);
			for(byte b:ready){
				char bc = (char) b;
				System.out.print(bc);
			}
			System.out.println();
			Checksum checksum = new CRC32();
			checksum.update(ready, 0, ready.length);
			double checksumValue = checksum.getValue();
			System.out.println("CRC value: " + checksumValue);
			out.print(checksumValue);
		}
	}
}

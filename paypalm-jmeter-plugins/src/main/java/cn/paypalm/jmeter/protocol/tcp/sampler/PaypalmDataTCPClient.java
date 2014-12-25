package cn.paypalm.jmeter.protocol.tcp.sampler;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.jmeter.protocol.tcp.sampler.ReadException;
import org.apache.jmeter.protocol.tcp.sampler.TCPClientImpl;

public class PaypalmDataTCPClient extends TCPClientImpl
{

  public PaypalmDataTCPClient()
  {
	  super();
  }
  
	public String read(InputStream is) throws ReadException {
		ByteArrayOutputStream w = new ByteArrayOutputStream();
		
		try {
			byte[] bufferLen = new byte[6];
			if (is.read(bufferLen, 0, 6) == 6) {
				w.write(bufferLen, 0, 6);
				int dataLength = Integer.valueOf(new String(bufferLen))-6;
				
				byte[] buffer = new byte[dataLength];
				
				is.read(buffer, 0, dataLength);
				w.write(buffer);
			}
			return w.toString(super.getCharset());
		} catch (IOException e) {
			throw new ReadException("", e, w.toString());
		}
	}
}

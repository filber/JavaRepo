package cn.paypalm.jmeter.protocol.tcp.sampler;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.jmeter.protocol.tcp.sampler.ReadException;
import org.apache.jmeter.protocol.tcp.sampler.TCPClientImpl;

public class PaypalmDisTCPClient extends TCPClientImpl
{

  public PaypalmDisTCPClient()
  {
	  super();
  }
  
	public String read(InputStream is) throws ReadException {
		ByteArrayOutputStream w = new ByteArrayOutputStream();
		
		try {
			byte[] bufferLen = new byte[8];
			if (is.read(bufferLen, 0, 8) == 8) {
				w.write(bufferLen, 0, 8);
				int dataLength = Integer.valueOf(new String(bufferLen))-8;
				
				byte[] buffer = new byte[dataLength];
				int x = 0;
				int count = 0;
				while ((x = is.read(buffer)) > -1) {
					count += x;
					w.write(buffer, 0, x);
					if ((this.useEolByte) && (buffer[(x - 1)] == this.eolByte)) {
						break;
					} else if (count == (dataLength + 8)) {
						break;
					}
				}
			}
			return w.toString(super.getCharset());
		} catch (IOException e) {
			throw new ReadException("", e, w.toString());
		}
	}
}

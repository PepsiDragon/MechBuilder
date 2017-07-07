package com.dragontec.util;

public class StreamCloserUtil {
	public static void closeStream(AutoCloseable stream)
	{
		if(stream != null)
		{
			try {
				stream.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}

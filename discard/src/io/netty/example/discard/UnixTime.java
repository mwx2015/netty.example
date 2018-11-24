package io.netty.example.discard;

import java.time.ZoneOffset;

import java.time.LocalDateTime;
public class UnixTime {
	private final long secs;
	
	public UnixTime() {
		secs  = (System.currentTimeMillis()/1000L + 0);
	}
	public UnixTime(long value) {
		secs = value;
	}
	public long value() {
		return secs;
	}
	@Override
	public String toString() {
		
		return LocalDateTime.ofEpochSecond(secs,0,ZoneOffset.ofHours(8)).toString();
	}
}



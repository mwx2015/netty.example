package io.netty.example.discard;
import io.netty.handler.codec.MessageToByteEncoder;
import io.netty.channel.ChannelHandlerContext;
import io.netty.buffer.ByteBuf;
public class encodeTime extends MessageToByteEncoder<UnixTime> {

	@Override
	public void encode(ChannelHandlerContext ctx, UnixTime time, ByteBuf out) {
		out.writeInt((int)time.value());
	}
}

package io.netty.example.discard;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;


public class TimeServerHandler extends ChannelInboundHandlerAdapter {

	@Override
	public void channelActive(final ChannelHandlerContext ctx) {
		//final ByteBuf time = ctx.alloc().buffer(4);
		//time.writeInt((int)(System.currentTimeMillis()/1000L + 2208988800L));
		
		final ChannelFuture f = ctx.writeAndFlush(new UnixTime());
		
		System.out.println("write data to encode.");
		f.addListener(ChannelFutureListener.CLOSE);
	}
	
	@Override 
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
		cause.printStackTrace();
		ctx.close();
	}
}

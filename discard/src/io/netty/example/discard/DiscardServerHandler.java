package io.netty.example.discard;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
* Handles a server-side channel
*/
public class DiscardServerHandler extends ChannelInboundHandlerAdapter {
	
	@Override 
	public void channelRead(ChannelHandlerContext ctx, Object msg) {
		//discard the recieved data silently
		//((ByteBuf)msg).release();// decrease the ref_count
		
		ByteBuf in = (ByteBuf)msg;
		System.out.println(in.toString(io.netty.util.CharsetUtil.US_ASCII));
		
		//ctx.write("hello  ");
		ctx.write(msg);	// ctx will release msg
		ctx.flush();
		
	}
	
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx , Throwable cause) {
		// close the connection when an exception is raised
		cause.printStackTrace();
		ctx.close();
	}
	
	
}
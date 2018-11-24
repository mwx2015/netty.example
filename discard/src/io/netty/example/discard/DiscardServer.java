package io.netty.example.discard;

import io.netty.bootstrap.ServerBootstrap;

import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
* Discard any incoming data
*/

public class DiscardServer{
	
	private int port;
	
	public DiscardServer(int port) {
		this.port = port;
	}
	
	public void run() throws Exception {
		EventLoopGroup bossGroup = new NioEventLoopGroup();
		
		EventLoopGroup workerGroup = new NioEventLoopGroup();
		
		try {
			
			ServerBootstrap b = new ServerBootstrap();
			b.group(bossGroup, workerGroup)
			 .channel(NioServerSocketChannel.class)
			 .childHandler( new ChannelInitializer<SocketChannel>() {
				 
				 @Override
				 public void initChannel( SocketChannel ch) throws Exception {
					 ch.pipeline().addLast( new encodeTime(),new TimeServerHandler());
				 }
			 })
			 .option(ChannelOption.SO_BACKLOG, 128)
			 .childOption(ChannelOption.SO_KEEPALIVE,true);
			 
			 //bind and start to accept incoming connectionns
			 ChannelFuture f = b.bind(port).sync();
			 //wait util the server socket is closed
			 f.channel().closeFuture().sync();
		} finally {
			workerGroup.shutdownGracefully();
			bossGroup.shutdownGracefully();
			
		}
		
	}
	
	
	public static void main(String[] args) throws Exception {
		int port = 8083;
		if(args.length > 0) {
			port = Integer.parseInt(args[0]);
		}
		System.out.println("begin to run.");
		new DiscardServer(port).run();
	}
};
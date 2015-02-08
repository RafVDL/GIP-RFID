package be.rafvdl.rfid.ws;

import java.net.InetSocketAddress;
import java.util.Collections;
import java.util.List;

import org.java_websocket.WebSocket;
import org.java_websocket.WebSocketImpl;
import org.java_websocket.drafts.Draft;
import org.java_websocket.drafts.Draft_17;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;

public class Main extends WebSocketServer {

	public Main(int port, Draft draft) {
		this(new InetSocketAddress(port), Collections.singletonList(draft));
	}

	public Main(InetSocketAddress address, List<Draft> drafts) {
		super(address, drafts);
	}

	public static void main(String[] args) {
		System.out.println("Starting");
		WebSocketImpl.DEBUG = false;
		new Main(9001, new Draft_17()).start();
	}

	@Override
	public void onOpen(WebSocket conn, ClientHandshake handshake) {
		System.out.println("OPENED - " + conn.getRemoteSocketAddress());
	}

	@Override
	public void onClose(WebSocket conn, int code, String reason, boolean remote) {
		System.out.println("CLOSED - " + conn.getRemoteSocketAddress());
	}

	@Override
	public void onMessage(WebSocket conn, String message) {
		System.out.println("MESSAGE (" + conn.getRemoteSocketAddress() + "): "
				+ message);
	}

	@Override
	public void onError(WebSocket conn, Exception ex) {

	}

}

package com.xored.x5.shared.tcp;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.BinaryResourceImpl;

public class TcpUtil {

	public static void emfToSocket(Socket socket, EObject obj)
			throws IOException {
		bytesToSocket(socket, emfToBytes(obj));
	}

	public static void bytesToSocket(Socket socket, byte[] data)
			throws IOException {
		DataOutputStream out = new DataOutputStream(socket.getOutputStream());
		out.writeInt(data.length);
		out.write(data);
		out.flush();
	}

	public static EObject emfFromSocket(Socket socket) throws IOException {
		return bytesToEmf(bytesFromSocket(socket));
	}

	public static byte[] bytesFromSocket(Socket socket) throws IOException {
		DataInputStream in = new DataInputStream(socket.getInputStream());
		int len = in.readInt();
		byte[] result = new byte[len];
		in.readFully(result);
		return result;
	}

	public static byte[] emfToBytes(EObject obj) throws IOException {
		Resource r = new BinaryResourceImpl();
		r.getContents().add(obj);
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		r.save(out, null);
		return out.toByteArray();
	}

	public static EObject bytesToEmf(byte[] bytes) throws IOException {
		Resource r = new BinaryResourceImpl();
		ByteArrayInputStream bin = new ByteArrayInputStream(bytes);
		r.load(bin, null);
		EObject obj = r.getContents().get(0);
		return obj;

	}
}

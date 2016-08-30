package classesMulticast;

// Bibliotecas
import java.net.*;
import java.io.*;

public final class MulticastGroup {
    private String mIP = "224.4.4.4";   // 224.XX.XX.XX - 239.XX.XX.XX
    private final int mPort;                  // XXXX > 1024
    private final byte mTTL;
    private MulticastSocket mSocket;
    private InetAddress mGroupAddrs = null;
    private final String validacao;

    public MulticastGroup(int mPort, String mIP, String validacao) {
        this.mTTL = 1;
        this.mPort = mPort;
        this.mIP = mIP;
        this.validacao = validacao;
        if (mJoinGroup()) {
            System.out.println("OK");
        }
    }

    public boolean mJoinGroup() {
        System.out.print("\nJoining group: ");
        try {
            mGroupAddrs = InetAddress.getByName(mIP);
        } catch (UnknownHostException e) {
            System.err.println("ERR: Can't resolve \"" + mIP + "\": " + e);
            return false;
        }
        System.out.print(mGroupAddrs + ":" + mPort + "... ");
        try {
            mSocket = new MulticastSocket(mPort);
        } catch (IOException e) {
            System.err.println("FAIL");
            System.err.println("Can't creat MulticasSocket:" + mPort + ": " + e);
            return false;
        }
        try {
            mSocket.joinGroup(mGroupAddrs);
        } catch (IOException e) {
            System.err.println("ERR: Can't join group " + mGroupAddrs + ": " + e);
            return false;
        }
        return true;
    }

    public boolean mLeaveGroup() {
        System.out.print("\nLeaving group...");
        try {
            mSocket.leaveGroup(mGroupAddrs);
        } catch (IOException e) {
            System.out.println("ERR: Can't leave group: IO Exception:" + e);
            return false;
        }
        System.out.println("OK");
        return true;
    }

    public MulticastSocket getSocket() {
        return mSocket;
    }

    public int getPort() {
        return mPort;
    }

    public InetAddress getGroupAddrs() {
        return mGroupAddrs;
    }

    public String getValidacao() {
        return validacao;
    }

    public void Close() {
        mSocket.close();
    }
}
